package com.ruoyi.api;

import com.github.pagehelper.util.StringUtil;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.LocationUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.duties.jobs.TaskJob;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.domain.TasksCarsVo;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.project.duties.tasksCarsLogs.domain.TasksCarsLogs;
import com.ruoyi.project.duties.tasksCarsLogs.service.ITasksCarsLogsService;
import com.ruoyi.project.redisEntry.CarsLonLat;
import com.ruoyi.project.redisEntry.LonLat;
import com.sun.jna.platform.win32.COM.RunningObjectTable;
import org.apache.tomcat.util.bcel.Const;
import org.aspectj.apache.bcel.classfile.ConstantNameAndType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.ConfigurationPropertyState;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping(value = "/api/cars")
public class ApiCarsController {
    Logger logger = LoggerFactory.getLogger(ApiCarsController.class);
    @Autowired
    private TaskJob taskJob;

    private static String getLonLatByMin = null;
    private static final Double ori_lat = Double.valueOf(Global.getConfig("project.lat"));
    private static final Double ori_lng = Double.valueOf(Global.getConfig("project.lon"));
    private static final Double endDistance = Double.valueOf(Global.getConfig("project.endDistance"));
    @Autowired
    private ITasksService tasksService;
    @Autowired
    private ITasksCarsLogsService carsLogsService;

    static {
        Global props = Global.getInstance();
        getLonLatByMin = props.getConfig("project.getLonLatByMin");
    }

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    private ITasksCarsService tasksCarsService;
    /**
     * 往redis中设置 经纬度
     * @return
     */
    @PostMapping(value = "/addCarsLonLat")
    public AjaxResult addCarsLonLat2(@RequestParam String lon, @RequestParam String lat) {
//        logger.debug("经度:"+lon+"维度:"+lat);
        try {
            redisUtils.delete(Constants.RUNINGCAR_PATTEN+"null");
            //获取当前登陆的用户
            DriverCar curDriver = redisUtils.getLoginDriverCar();
            if(null==curDriver){
                logger.debug("未签到");
                return AjaxResult.error("未签到");
            }
            List<LonLat> lonLatList = null;
            LonLat lonLat =null;
            RuningCar runingCar = redisUtils.get(Constants.RUNINGCAR_PATTEN + curDriver.getNotifyId(), RuningCar.class);
            // add by xuehf 2019-06-17 调度员手动下线不能传
            if(null==runingCar){
                logger.debug("未签到或已下线");
                return AjaxResult.error("未签到或已下线");
            }
            // add by xuehf 2019-06-17 调度员手动下线不能传end
            /**刷新坐标*/
            if(null==runingCar){
                LonLat lngLat = new LonLat(Double.valueOf(lon),Double.valueOf(lat));
                List<LonLat> list = new ArrayList<>();
                list.add(lngLat);
                runingCar = new RuningCar(Double.valueOf(lon),Double.valueOf(lat),curDriver.getNotifyId(),list);
                runingCar.setCarNo(curDriver.getCarNo()+"");
                runingCar.setCarId(curDriver.getCarId()+"");
            }else{
                if(StringUtil.isEmpty(runingCar.getCarId())||StringUtil.isEmpty(runingCar.getCarNo())||StringUtil.isEmpty(runingCar.getNotifyId())){
                    return AjaxResult.error("请先签到");
                }
                runingCar.setLat(Double.valueOf(lat));
                runingCar.setLng(Double.valueOf(lon));
            }
            logger.debug("[{}]号车正在上传坐标,({},{})",runingCar.getCarNo(),lat,lon);
            /**设置五分钟内的坐标列表*/
            runingCar = setLatLngList(runingCar,lat,lon,curDriver.getNotifyId());
            /**设置车辆是否到达终点*/
            //del by xuehf 2019-06-12 begin 改成手动到达
            setEndSate(runingCar);
            //del by xuehf 2019-06-12 end
            //设置是否站内
            setInner(runingCar);
            //设置是否缓慢行进的状态
            isStop(runingCar);
            //通知司机是否达到
            notifyDriverIsArrived(runingCar);
            //del by xuehf 2019-06-12 begin 改成手动到达
            inEnd(runingCar);
            //del by xuehf 2019-06-12 end
            redisUtils.set(Constants.RUNINGCAR_PATTEN + runingCar.getNotifyId(), runingCar, -1);
            return AjaxResult.success("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("添加失败！" + e.getMessage());
        }
    }


    /**通知司机是否*/
    private void notifyDriverIsArrived(RuningCar runingCar) {
        TasksCars tc = redisUtils.get(Constants.TASKINGCAR_PATTEN+runingCar.getNotifyId(),TasksCars.class);
        if(tc==null||tc.getStatus()==null){
            return;
        }
        if(runingCar.isStop()&&runingCar.getStopTime()!=null&&(System.currentTimeMillis()-runingCar.getStopTime())/(1000*60)>3&&tc.getStatus()==Constants.TASK_STATUS_ING){
            //第一次暂停或者 距离上次推送时间间隔3分钟了就再次推送。
            if(StringUtils.isBlank(tc.getStationStatus())||!tc.getStationStatus().equalsIgnoreCase("OUT")){
                return;
            }
            if(null==runingCar.getLastNotifyTime()||(System.currentTimeMillis()-runingCar.getLastNotifyTime())/(1000*60)>3){
                Map<String,String> map = new HashMap<>();
                map.put("taskType","car_stop");//附加参数推送类型是【车辆停止类型】
                PushUtils.pushSendTasking("您是否已经到达目的地?",runingCar.getNotifyId(),map);
                runingCar.setLastNotifyTime(System.currentTimeMillis());
                redisUtils.set(Constants.RUNINGCAR_PATTEN+runingCar.getNotifyId(),runingCar);
            }
        }
    }

    /**设置是否在终点范围内*/
    private void inEnd(RuningCar runingCar) {
        TasksCars tasksCars = redisUtils.get(Constants.TASKINGCAR_PATTEN+runingCar.getNotifyId(),TasksCars.class);
        if(null == tasksCars)return;
        double distance = LocationUtils.getDistance(Double.valueOf(tasksCars.getLat()),Double.valueOf(tasksCars.getLng()),runingCar.getLat(),runingCar.getLng());
        boolean in = endDistance-distance>=0;
        /**出站并且此时站内有一辆车
         * 1. 出站状态是出站
         * 2. 任务状态是已经完成
         * 3. 不在终点范围内
         * 4. 没有发过车
         * 5. 更改任务发车标志位置
         * */
        if(StringUtils.isNotEmpty(tasksCars.getStationStatus())&&tasksCars.getStationStatus().equalsIgnoreCase("OUT")
        &&null!=tasksCars.getStatus()&&tasksCars.getStatus()== Constants.TASK_STATUS_COMPLATE&&!tasksCars.isInEnd()){
            if(!tasksCars.isSendTask()){
                Tasks task = tasksService.selectTasksById(tasksCars.getTaskId()+"");
                //任务没有暂停
                if(task.getPause().equalsIgnoreCase(Constants.NO)){
                    int redisCarcnt = redisUtils.getEndPointCarCnt(task);
                    //工地的停车数量<设定的数量-1(也就是保证有几辆车)
                    if(null!=task.getTargetCarcnt()&&redisCarcnt<task.getTargetCarcnt()&&task.getTargetCarcnt()>0){
                        if(redisCarcnt<task.getTargetCarcnt()&&!tasksCars.isSendTask()&&redisCarcnt>0){
                            //设置本次车次出站后派发车辆标志位为true
                            logger.debug("终点车辆少于[{}]辆车，重置标志位",task.getTargetCarcnt());
                            tasksCars.setSendTask(true);
                            //设置派车标志为为true
                            task.setTimeToTask(true);
                            tasksService.updateTasks(task);
//                            RedisDomainUtils.setRedisTaskCarDomain(tasksCars);
                        }
                        //查询最后一次派车的时间
                        /*//选出当前任务的最后一辆车的出发时间，计算派车间隔
                        List<TasksCars> dbtasksCars = tasksCarsService.selectTasksCarsList(new TasksCars(){{
                            setTaskId(Long.valueOf(task.getId()));
                            setSqlWhere(" and status in (1,3) order by id desc limit 1");}});
                        Long subTime = null;
                        if(null!=dbtasksCars&&dbtasksCars.size()>0){
                            TasksCars dbc = dbtasksCars.get(0);
                            subTime = (System.currentTimeMillis()-dbc.getCreateTime().getTime())/(1000*60);
                        }
                        //距离上次派车间隔
                        if(null!=subTime&&subTime>5){
                            //设置本次车次出站后派发车辆标志位为true
                            tasksCars.setSendTask(true);
                            //设置派车标志为为true
                            task.setTimeToTask(true);
                            tasksService.updateTasks(task);
                        }*/
                    }
                }
            }
        }
        tasksCars.setInEnd(in);
        RedisDomainUtils.setRedisTaskCarDomain(tasksCars);
    }

    /**设置运行中车辆信息*/
    private void setCarDriverInfo(RuningCar runingCar, DriverCar curDriver) {
        runingCar.setCarNo(curDriver.getCarNo()+"");
        runingCar.setCarId(curDriver.getCarId()+"");
    }

    /**
     *
     *  判断两个时间相差多少分钟
     * @param date1
     * @param date2
     * @return
     */
    private boolean checkDate(Date date1, Date date2) {
        long minute = (date1.getTime() - date2.getTime()) / 60 / 1000;
        Double iGetLonLatByMin = Double.valueOf(getLonLatByMin);
        if (Math.abs(minute)-iGetLonLatByMin>0) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否在五分钟内,过期了不加入列表
     * */
    private boolean isTimeOk(Long time) {
        Long minute = (System.currentTimeMillis() - time) / 60 / 1000;
        Double iGetLonLatByMin = Double.valueOf(getLonLatByMin);
        return iGetLonLatByMin-minute>0;
    }

    /**设置车辆是否在站内*/
    private void setInner(RuningCar runingCar){
        runingCar.setInner(Double.valueOf(Global.getConfig("project.startDistance")).compareTo(LocationUtils.getDistance(Double.valueOf(Global.getConfig("project.lat")),Double.valueOf(Global.getConfig("project.lon")),runingCar.getLat(),runingCar.getLng())) >=0);
    }

    /**设置是否到达*/
    private void setEndSate(RuningCar runningCar){
        TasksCars tasksCars = redisUtils.get(Constants.TASKINGCAR_PATTEN+runningCar.getNotifyId(),TasksCars.class);
        if(null != tasksCars){
            Double distance = LocationUtils.getDistance(Double.valueOf(tasksCars.getLat()),Double.valueOf(tasksCars.getLng()),runningCar.getLat(),runningCar.getLng());
            //如果在终点范围，并且没有被设置过
            if(endDistance.compareTo(distance)>=0&&tasksCars.getStatus()!=Constants.TASK_STATUS_COMPLATE&&null!=tasksCars.getStationStatus()&&tasksCars.getStationStatus().equalsIgnoreCase("OUT")){
                //第一车排除，手动点击到达
                if(null!=tasksCars.getCarCnt()&&tasksCars.getCarCnt()==1){
                    return;
                }
                runningCar.setEnding(true);
                runningCar.setTaskStatus(Constants.TASK_CAR_STATUS_COMPLATE);
                Tasks task = redisUtils.get(Constants.TASK_PATTEN+tasksCars.getTaskId(),Tasks.class);
                if(null==task){
                    task = tasksService.selectTasksById(tasksCars.getTaskId()+"");
                }
                //终点标志Y
                tasksCars.setIsEnd(Constants.YES);
                //终点时间
                tasksCars.setEndTime(new Date());
                //任务标志-完成
                tasksCars.setStatus(Constants.TASK_CAR_STATUS_COMPLATE);
                tasksCarsService.updateTasksCars(tasksCars);
                RedisDomainUtils.setRedisTaskCarDomain(tasksCars);
            }
        }
    }

    /**
     * 设置暂停状态
     * */
    public void isStop(RuningCar runingCar){
        //不停就一直更新stoptime
        if(!runingCar.isStop()){
            if(null!=runingCar.getStopTime()&&(System.currentTimeMillis()-runingCar.getStopTime())/(1000*60)>3&&runingCar.getTaskStatus()==Constants.TASK_STATUS_ING){
                //任务中的stopTime大于20s就算停留
                TasksCars tasksCars = redisUtils.get(Constants.RUNINGCAR_PATTEN+runingCar.getNotifyId(),TasksCars.class);
                TasksCarsLogs carsLogs = new TasksCarsLogs();
                carsLogs.setTaskCarId(tasksCars.getId());
                carsLogs.setCarNo(tasksCars.getCarNo()+"");
                carsLogs.setCarId(tasksCars.getCarId());
                carsLogs.setMobile(tasksCars.getDriverMobile());
                carsLogs.setCarBrand(tasksCars.getCarBrand());
                carsLogs.setLat(runingCar.getLat());
                carsLogs.setLng(runingCar.getLng());
                Long time = (System.currentTimeMillis()-runingCar.getStopTime())/(1000*60);
                carsLogs.setTimes(time.intValue());
                carsLogs.setStopStartTime(new Timestamp(runingCar.getStopTime()));
                if(null!=carsLogs.getTaskCarId()){
                    carsLogsService.insertTasksCarsLogs(carsLogs);
                }
            }
            runingCar.setStopTime(System.currentTimeMillis());
        }
        if(runingCar.getStopTime()==null){
            runingCar.setStopTime(System.currentTimeMillis());
        }

        List<LonLat> newList = runingCar.getLonLatList();
        /**判断是否暂停或者行驶缓慢 begin*/
        if(null!=newList&&newList.size()>0){
            LonLat last = newList.get(0);
            Double tmdistance = LocationUtils.getDistance(runingCar.getLat(),runingCar.getLng(),last.getLat(),last.getLon());
            if(Double.valueOf(Global.getConfig("project.warningDistance")).compareTo(tmdistance)>=0){
                runingCar.setStop(true);
            }else{
                runingCar.setStop(false);
            }
        }
        /**判断是否暂停或者形势缓慢 end*/
    }

    /**
     * @author xuehf
     * @param runingCar 当前车辆
     * @param notifyId
     * 重新设置经纬度坐标的列表
     * **/
    public RuningCar setLatLngList(RuningCar runingCar,String lat,String lng,String notifyId){
        List<LonLat> lonLatList = null;
        if (runingCar == null) {
            lonLatList = new ArrayList<>();
            LonLat lonLat = new LonLat(Double.valueOf(lng), Double.valueOf(lat));
            lonLatList.add(lonLat);
            runingCar = new RuningCar(Double.valueOf(lng), Double.valueOf(lat), notifyId, lonLatList);
        } else {
            //重新设值开始
            lonLatList = runingCar.getLonLatList();
            //重新设值结束
            List<LonLat> newList = new ArrayList<>();
            if(null!=lonLatList){
                for (LonLat one : lonLatList) {
                    if(isTimeOk(one.getInitTimes())){
                        newList.add(one);
                    }
                }
            }
            LonLat lonLat = new LonLat(Double.valueOf(lng),Double.valueOf(lat));
            newList.add(lonLat);
            runingCar.setLonLatList(newList);
            runingCar.setLng(Double.valueOf(lng));
            runingCar.setLat(Double.valueOf(lat));
        }
        return runingCar;
    }
}
