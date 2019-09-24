package com.ruoyi.api.front;


import com.ruoyi.api.domain.FrontDispatherEntity;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.api.domain.TasksCarsEntity;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.LocationUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.common.config.Global;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.driverCar.service.IDriverCarService;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.project.monitor.server.domain.Sys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@RequestMapping("/web")
@RestController
public class ApiTaskDispatherController {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IDriverCarService driverCarService;
    @Autowired
    private ITasksCarsService tasksCarsService;
    private String startLat = Global.getConfig("project.lat");
    private String startLng = Global.getConfig("project.lon");
    private Logger logger = LoggerFactory.getLogger(ApiTaskDispatherController.class);
    @Autowired
    private ITasksService tasksService;
    private BigDecimal equalRate = new BigDecimal(Global.getConfig("project.equalRate"));
    @RequestMapping("/dispath")
    public AjaxResult dispath(HttpServletRequest request){
        List<FrontDispatherEntity> list = new ArrayList<>();

        //两种方法1.从redis取   2.
//        List<Tasks> tasksList = redisUtils.getLists(Constants.TASK_PATTEN,Tasks.class);//tasksService.selectTasksList(new Tasks(){{setStatus(Constants.TASK_STATUS_ING);}});
        List<Tasks> tasksList = tasksService.selectDispathList();
        if(null!=tasksList){
            ListIterator<Tasks> listIterator = tasksList.listIterator();
            if(null!=listIterator){
                for (ListIterator<Tasks> iter = listIterator;iter.hasNext();){
                    Tasks tmtask = iter.next();
                    if(null==tmtask){
                        iter.remove();
                        continue;
                    }
                    if(tmtask.getStatus()!=null&&tmtask.getStatus()>Constants.TASK_STATUS_ING){
                        iter.remove();
                        continue;
                    }
                }
            }
        }else{
            return AjaxResult.success().put("cars",new ArrayList<>());
        }
        List<FrontDispatherEntity> returnList = new ArrayList<>();
        /**拼接值*/
        for (Tasks tasks : tasksList) {
            FrontDispatherEntity ft = new FrontDispatherEntity();
            ft.setTaskId(Long.valueOf(tasks.getId()));
            ft.setTaskName(tasks.getName());
            ft.setTaskStatus(tasks.getPause());
            ft.setWarning(getWaring(tasks));
            List<TasksCars> tasksCars = tasksCarsService.selectTasksCarsList(new TasksCars(){{
                setStatus(3);
                setTaskId(Long.valueOf(tasks.getId()));
                setSqlWhere(" and 1=1 order by car_cnt desc ");
            }});
            ft.setRunList(tasksCars);//设置派单记录
            if(tasks.getLjfangliang()!=null&&tasks.getTotalFl()!=null){
                BigDecimal d = tasks.getLjfangliang().divide(tasks.getTotalFl(),6,RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(3,RoundingMode.HALF_UP);
                ft.setTotalProgress(tasks.getLjfangliang().doubleValue()+"/"+tasks.getTotalFl());
            }else{
                ft.setTotalProgress("0/"+tasks.getTotalFl());
            }
            List<TasksCarsEntity> cars = new ArrayList<>();
            List<TasksCars> carsList = redisUtils.getLists(Constants.TASKINGCAR_PATTEN,TasksCars.class);
            if(null!=carsList&&carsList.size()>0){
                for(TasksCars item: carsList){
                    RuningCar runingCar = redisUtils.get(Constants.RUNINGCAR_PATTEN+item.getNotifyId(),RuningCar.class);
                    if(runingCar==null){
                        continue;
                    }
                    //过滤车辆--已经完成并且返回站内了
                    if(null==item.getTaskId()||ft.getTaskId()!=item.getTaskId()||((item.getStatus()!=null&&item.getStatus() == Constants.TASK_STATUS_COMPLATE)&&runingCar.isInner())){
                        continue;
                    }
                    //过滤车辆--已经调单了
                    if(item.getStatus()!=null&&item.getStatus() == Constants.DELETE_FLAG){
                        continue;
                    }
                    TasksCarsEntity c = new TasksCarsEntity();
                    c.setId(item.getId());
                    c.setCarId(item.getCarId());
                    c.setCarNo(Long.valueOf(item.getCarNo()));
                    c.setSure(item.isSure());
                    //
                    long minte = 1;
                    if(runingCar.isStop()){
                        if(runingCar.getStopTime()!=null&&!runingCar.isInner()){
                            minte = (System.currentTimeMillis() - runingCar.getStopTime())/(1000*60);
                            logger.debug("非站内停留[{}]",getTimeString(minte));
                            c.setInfo(c.getCarNo()+"号/停留"+getTimeString(minte));
                        }else if(runingCar.isInner()){
                            if(item.getStartTime()!=null){
                                minte = (System.currentTimeMillis()-item.getStartTime().getTime())/(1000*60);
                            }
                            if(minte>1){
                                logger.debug("已经点击【出站】,站内停留[{}]",getTimeString(minte));
                            }else{
                                logger.debug("没有点击【出站】但是坐标在站内，停留[{}]",getTimeString(minte));
                            }
                            c.setInfo(c.getCarNo()+"号/"+getTimeString(minte));
                        }
                    }else{
                        logger.debug("行进中");
                        //返程
                        if(null!=item.getEndTime()&&null!=item.isEnd()&&item.isEnd().equals(Constants.YES)){
                            minte = (System.currentTimeMillis() - item.getEndTime().getTime())/(1000*60);
                            logger.debug("返程的行驶时间[{}]",getTimeString(minte));
                        }else if(item.getStartTime()!=null){
                            //去程
                            minte = (System.currentTimeMillis() - item.getStartTime().getTime())/(1000*60);
                            logger.debug("去程的行驶时间[{}]",getTimeString(minte));
                        }
                        c.setInfo(c.getCarNo()+"号/"+getTimeString(minte));
                    }
                    //获取经纬度的百分比
                    c.setProcess(getDistanceRate(item));
                    c.setStatus(null!=item.getIsEnd()&&item.getIsEnd().equalsIgnoreCase(Constants.YES)?"back":"go");
                    RuningCar rc = redisUtils.get(Constants.RUNINGCAR_PATTEN+item.getNotifyId(),RuningCar.class);
                    if(null!=rc){
                        c.setBreakdown(rc.isStop()?Constants.YES:Constants.NO);
                    }
                    cars.add(c);
                };
            }
            ft.setCars(cars);
            returnList.add(ft);
        }
       return AjaxResult.success().put("data",returnList);
    }


    /**
     * 获取在终点的车辆数量算出预警信息
     * **/
    private String getWaring(Tasks tasks) {
        if(tasks==null){
            return "G";
        }
        List<TasksCars> list = redisUtils.getLists(Constants.TASKINGCAR_PATTEN,TasksCars.class);
        if(list.size()>0){
            int cnt = 0;
            for (TasksCars item:list){
                if(item.getTaskId()!=null&&item.getTaskId().equals(Long.valueOf(tasks.getId()))){
                    if(item.isInEnd()){
                        cnt++;
                    }
                }
            }
            //黄色预警
            if(cnt < Constants.YELLOW_WARNING_VALUE){
                return "R";
            }else if(cnt == Constants.YELLOW_WARNING_VALUE){
                return "Y";
            }else if(cnt>Constants.YELLOW_WARNING_VALUE){
                return "G";
            }
        }
        return "R";
    }


    private String getDistanceRate(TasksCars tasksCars){
        RuningCar runingCar = redisUtils.get(Constants.RUNINGCAR_PATTEN+tasksCars.getNotifyId(),RuningCar.class);
        if(null == runingCar){
            return "%";
        }
        logger.debug(runingCar.getCarNo()+"号车的当前经纬度坐标为lat:"+runingCar.getLat()+";lng:"+runingCar.getLng());
        if(runingCar!=null){
            /**当前距离起点*/
            double sdistance = LocationUtils.getDistance(Double.valueOf(startLat),Double.valueOf(startLng),runingCar.getLat(),runingCar.getLng());
            logger.debug("已经行驶的距离为"+sdistance);
            /**当前距离终点*/
            double edistance = LocationUtils.getDistance(Double.valueOf(tasksCars.getLat()),Double.valueOf(tasksCars.getLng()),runingCar.getLat(),runingCar.getLng());
            logger.debug("距离终点的距离为"+edistance);
            //总长度
            double totaldistances = LocationUtils.getDistance(Double.valueOf(startLat),Double.valueOf(startLng),Double.valueOf(tasksCars.getLat()),Double.valueOf(tasksCars.getLng()));
            double realLats = 0.00;
            if(null!=tasksCars&&null!=tasksCars.getIsEnd()&&(tasksCars.getIsEnd().equalsIgnoreCase(Constants.YES)||tasksCars.getStatus() == Constants.TASK_STATUS_COMPLATE)){
                logger.debug("已经到达终点");
                realLats = edistance / totaldistances;
            }else {
                logger.debug("未到达终点");
                realLats = sdistance / totaldistances;
            }
            String ret = new BigDecimal(realLats+"").multiply(new BigDecimal(100)).setScale(3,RoundingMode.HALF_UP).doubleValue()+"%";
            logger.debug("综合%比:"+ret);
            return ret;
        }
        return "%";
    }


    public String getTimeString(long minte){
        long nd = 24 * 60;
        long nh = 60;
        long nm = 1;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = minte;
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        if(day>0){
            return day + "天" + hour + "小时" + min + "分钟";
        }else if(hour>0){
            return hour + "小时" + min + "分钟";
        }else if(min>0){
            return min + "分钟";
        }
        return "0分钟";
    }
}
