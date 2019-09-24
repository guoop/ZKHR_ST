package com.ruoyi.api;

import com.ruoyi.api.domain.ApiTasksCars;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.Jpush.JPushService;
import com.ruoyi.project.Jpush.impl.JPushServiceImpl;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.driver.domain.Driver;
import com.ruoyi.project.cars.driver.service.IDriverService;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cars.driverCar.service.IDriverCarService;
import com.ruoyi.project.cemslink.preproduction.domain.Preproduction;
import com.ruoyi.project.cemslink.preproduction.domain.PreproductionQueue;
import com.ruoyi.project.cemslink.preproduction.service.IPreproductionService;
import com.ruoyi.project.duties.jobs.TaskJob;
import com.ruoyi.project.duties.taskNotification.domain.TaskNotification;
import com.ruoyi.project.duties.taskNotification.service.ITaskNotificationService;
import com.ruoyi.project.duties.tasks.controller.TasksController;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.project.lines.productLine.domain.ProductLine;
import com.ruoyi.project.redisEntry.CarsLonLat;
import com.sun.jna.platform.win32.COM.RunningObjectTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/arrive")
public class ApiTasksCarsController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(ApiTasksCarsController.class);
    @Autowired
    private ITasksCarsService tasksCarsService;
    @Autowired
    private ITasksService tasksService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ITaskNotificationService taskNotificationService;
    @Autowired
    private JPushService jPushService;
    @Autowired
    private IPreproductionService preproductionService;
    @Autowired
    private TasksController tasksController;
    @Autowired
    private TaskJob taskJob;
    @Autowired
    private IDriverService driverService;

    /**
     * 获取当前司机正在进行的任务
     * */
    @PostMapping("/getProductLineQueue")
    public AjaxResult getProductLineQueue(){
        DriverCar driverCar = redisUtils.getLoginDriverCar();
        Driver driver = getDriver(driverCar);
        if(driver==null){
            return AjaxResult.error("无此司机");
        }
        RuningCar runingCar = redisUtils.get(Constants.RUNINGCAR_PATTEN+driverCar.getNotifyId(),RuningCar.class);
        TasksCars item = redisUtils.get(Constants.TASKINGCAR_PATTEN+driverCar.getNotifyId(),TasksCars.class);
        List<Preproduction> list=null;
        if(null==runingCar||!runingCar.isInner()){
            if(item!=null&&null!=item.getStatus()&&item.getStatus()==Constants.TASK_STATUS_ING){
                list = new ArrayList<>();
                list.add(getPreproduction(item));
            }
            logger.debug("[{}]号车现在不在站内，不提供主机楼接料信息",driverCar.getCarNo());
            return AjaxResult.success().put("list",list).put("privilege",driver.getPrivilegeCnt());
        }
        //如果没有任务就返回1号线任务列表
        if(null!=item){
            Integer productLine =item.getProductLine();
            list = preproductionService.selectPreproductionList(new Preproduction(){{setProductLine(productLine);setSqlWhere(" and (state is null or state =1)  and (sort>0 or sort is null)  and (SyncStatus=1 or SyncStatus2=1) order by state desc ");}});
            TasksCars redisItem = redisUtils.get(Constants.WAITE_QUEUE_PATTERN+productLine,TasksCars.class);
            if(null!=redisItem){
                list.add(getPreproduction(redisItem));
            }
            if(null!=item.getCarCnt()){
                List<Preproduction> newplist = list.stream().filter(p->p.getTaskNumber().equalsIgnoreCase(item.getTaskNumber())&&null!=p.getTransportOrder()&&p.getTransportOrder().equals(item.getCarCnt())&&p.getInnerNumber().equalsIgnoreCase(item.getCarNo().toString())).collect(Collectors.toList());
                if(newplist==null||newplist.size()==0){
                    //完成了不显示
                    if(!item.getStatus().equals(Constants.TASK_STATUS_COMPLATE)){
                        list.add(getPreproduction(item));
                    }
                }
            }else{
                logger.debug("车次为空");
            }
            /*if(item.getStatus()!=null&&item.getStatus()==Constants.TASK_STATUS_ING&&null!=item.getStationStatus()&&item.getStationStatus().equalsIgnoreCase("OUT")){
                list.add(getPreproduction(item));
            }*/
            return AjaxResult.success().put("list",list).put("privilege",driver.getPrivilegeCnt());
        }else{
            return AjaxResult.success().put("list",new ArrayList<>()).put("privilege",driver.getPrivilegeCnt());
        }
    }

    /**
     * 获取当前司机正在进行的任务
     * */
    @PostMapping("/getAtleastCars")
    public AjaxResult getAtleastCars(){
        DriverCar  dc = redisUtils.getLoginDriverCar();
        List<RuningCar> rlist = redisUtils.getLists(Constants.RUNINGCAR_PATTEN,RuningCar.class);
        List<Long> ids = new ArrayList<>();
        for (RuningCar rn:rlist) {
            if(rn!=null&&rn.getCarId()!=null){
                ids.add(Long.valueOf(rn.getCarId()));
            }
        }
        List<TasksCars> list = new ArrayList<>();
        if(ids!=null&&ids.size()>0){
           list = tasksCarsService.selectTodayLog(ids);
        }
        int cnt = 0;
        for(RuningCar r:rlist){
            if(list!=null&&list.size()>0){
                for(int i=0;i<list.size();i++){
                    TasksCars tc = list.get(i);
                    if(tc!=null&&tc.getCarNo()!=null&&r!=null&&r.getCarNo()!=null&&tc.getCarNo().equals(Integer.valueOf(r.getCarNo()))&&tc.getCarId().equals(dc.getCarId())){
                        logger.debug("当前车号:[{}]",tc.getCarNo());
                        cnt = i;
                        logger.debug("前面有[{}]辆车",i);
                        return AjaxResult.success(String.format("您前面至少还有%d辆车在等待",cnt));
                    }
                }
            }
        }
        return AjaxResult.success(String.format("您前面至少还有%d辆车在等待",cnt));
    }


    /**
     * 特权车辆任务列表
     * 1. 站内车辆必须大于3辆车
     * 2. 只能选择最近的该排单的任务单的前6个。可以插入到redis队列，redis队列需要判断下时间间隔。如果到了，再派发
     * **/
    @PostMapping("/getPrivilegeTaskList")
    public AjaxResult getPrivilegeTaskList(){
        DriverCar driverCar = redisUtils.getLoginDriverCar();
        if(null==driverCar){
            return AjaxResult.error("请先登录");
        }
        Driver driver = getDriver(driverCar);
        if(driver==null){
            return AjaxResult.error("无此司机");
        }
        if(driver.getPrivilegeCnt()<=0){
            return AjaxResult.error("无优先权次数");
        }
        //判断站内车辆
        List<RuningCar> rlist = redisUtils.getLists(Constants.RUNINGCAR_PATTEN,RuningCar.class);
        int cnt =0;
        for (RuningCar rc:rlist){
            if(rc.isInner()){
                cnt++;
                if(cnt>4){
                    break;
                }
            }
        }
        //站内车辆小于4辆车，不给特权车辆选择
        if(cnt<=4){
            return AjaxResult.success(new ArrayList<>());
        }
        //选出来最近的该派发任务的前6个任务单
        List<Tasks> tlist = tasksService.selectFirstTasksToDispath(Constants.TASK_CNT_TO_DISPATH);
        return AjaxResult.success().put("list",tlist);
    }

    /**
     * 使用特权
     * **/
    @PostMapping("/doPrivilegeTask")
    public AjaxResult doPrivilegeTask(@RequestParam String taskId){
        DriverCar driverCar = redisUtils.getLoginDriverCar();
        TasksCars tasksCars = redisUtils.get(Constants.TASKINGCAR_PATTEN+driverCar.getNotifyId(),TasksCars.class);
        if(null!=tasksCars&&null!=tasksCars.getStatus()&&tasksCars.getStatus()==Constants.TASK_STATUS_ING&&tasksCars.isPrivilegeTask()){
            logger.debug("您已经有任务，并且是特权任务,请先完成");
            return AjaxResult.error("您已经有任务，并且是特权任务,请先完成");
        }
        if(null!=tasksCars&&null!=tasksCars.getTaskId()&&tasksCars.getTaskId().equals(Long.valueOf(taskId))&&tasksCars.getStatus()!=null&&tasksCars.getStatus()==Constants.TASK_STATUS_ING){
            logger.debug("您已经有当前任务");
            return AjaxResult.error("您已经有当前任务");
        }
        //已经出站并且任务没完成的情况不能使用特权
        if(null!=tasksCars&&null!=tasksCars.getStatus()&&tasksCars.getStatus()==Constants.TASK_STATUS_ING&&null!=tasksCars.getStationStatus()&&tasksCars.getStationStatus().equalsIgnoreCase("OUT")){
            return AjaxResult.error("请先完成当前的派单任务:任务名称"+tasksCars.getTaskName());
        }
        Driver driver = getDriver(driverCar);
        if(null==driver){
            return AjaxResult.error("无此司机");
        }
        if(null == driver.getPrivilegeCnt()||driver.getPrivilegeCnt()<=0){
            return AjaxResult.error("无优先权次数");
        }
        if(null==driverCar){
            return AjaxResult.error("请签到").put("code",-100);
        }
        RuningCar runingCar = redisUtils.get(Constants.RUNINGCAR_PATTEN+driverCar.getNotifyId(),RuningCar.class);//
        if(runingCar == null||!runingCar.isInner()){
            return AjaxResult.error("【{}】号车未签到或不在站内,无法使用优先权",driverCar.getCarNo());
        }
        Tasks tasks = redisUtils.get(Constants.TASK_PATTEN+taskId,Tasks.class);
        if(null==tasks){
            tasks = tasksService.selectTasksById(taskId);
        }
        Car car = redisUtils.get(Constants.CAR_PATTEN+runingCar.getCarId(),Car.class);
        if(null==car){
            return AjaxResult.error("没有该车辆信息");
        }
        if(tasks.getLjfangliang().add(car.getFangl()).compareTo(tasks.getPinchFl())>0){
            if(tasks.getLjfangliang().add(car.getFangl()).compareTo(tasks.getTotalFl())>0){
                tasks.setPause(Constants.YES);
                logger.debug("再发一车就超过掐方的方量了，所以暂停");
                tasksService.updateTasks(tasks);
                return AjaxResult.error("等待掐方");
            }
        }
        //检查是否已经排队到生产主机了
        Preproduction pre = new Preproduction();
        pre.setInnerNumber(driverCar.getCarNo()+"");
        pre.setSqlWhere(" and (state = 1 or state is null) ");
        List<Preproduction> prelist = preproductionService.selectPreproductionList(pre);
        if(prelist!=null&&prelist.size()>0){
            return AjaxResult.error("您的任务已经准备生产，不能使用优先权");
        }
        if(null!=tasks){
            //加锁抢单
            synchronized (this){
                if(tasks.getPause().equalsIgnoreCase(Constants.YES)||
                        tasks.getIsSchedule().equalsIgnoreCase(Constants.NO)||
                        tasks.getIsMixture().equalsIgnoreCase(Constants.NO)||
                        tasks.getFinancePause().equalsIgnoreCase(Constants.YES)||
                        tasks.getStatus()==Constants.TASK_STATUS_COMPLATE||
                        tasks.getStatus() == Constants.TASK_STATUS_CANCEL){
                    return AjaxResult.error("此任务未在排单状态，不能排单");
                }
                //过滤单子
                if(tasks.getIsJcdou().equalsIgnoreCase(Constants.YES)){
                    if(car.getJcdou()!=null&&car.getJcdou().equalsIgnoreCase(Constants.NO)){
                        return AjaxResult.error("该任务单需要加长斗");
                    }
                }
                /**3.非指定车辆remove begin*/
                String tskNos = tasks.getCarList();
                if(StringUtils.isNotEmpty(tskNos)){
                    tskNos=","+tskNos+",";//由 1，3，4，14变成",1,3,4,14,"这样就不会有4,&14,相同的情况
                    if(!tskNos.contains(","+car.getCarNo()+",")&&StringUtils.isNotEmpty(car.getCarNo()+"")){
                        logger.debug("该任务指定的有车辆，[{}]号车辆不是指定用車",car.getCarNo());
                        return AjaxResult.error("该任务指定的有车辆");
                    }
                }
                if(car.getHeight()>tasks.getHeight()){
                    logger.debug("该任务限高"+tasks.getHeight()+"米");
                    return AjaxResult.error("该任务限高"+tasks.getHeight()+"米");
                }
                if(tasks.getIsOtherArea().equalsIgnoreCase(Constants.YES)&&car.getIsOtherCar().equalsIgnoreCase(Constants.NO)){
                    logger.debug("车辆不满足跨县属性");
                    return AjaxResult.error("车辆不满足跨县属性");
                }
                if(new BigDecimal(tasks.getMixCar()).compareTo(car.getFangl())>0||new BigDecimal(tasks.getMaxCar()).compareTo(car.getFangl())<0){
                    logger.debug("车辆不满足任务需要的容量限制");
                    return AjaxResult.error("车辆不满足任务需要的容量限制");
                }
                TasksCars converted = redisUtils.get(Constants.WAITE_QUEUE_PATTERN+tasks.getProductLine(),TasksCars.class);
                boolean flag = false;//插队标识
                if(null!=converted&&!converted.isPrivilegeTask()){
                    flag = true;
                    //先把原来的删除掉,更改标志为为DEL标志为
                    converted.setStatus(Constants.DELETE_FLAG);
                    tasksCarsService.updateTasksCars(converted);
                    RedisDomainUtils.setRedisLineWaitQueueDomain(converted,true);
                }else if(null!=converted&&converted.isPrivilegeTask()){
                    return AjaxResult.error("优先权车辆在排队，无法插队");
                }
                if(!taskJob.isTimeToSendTask(tasks)){
                    return AjaxResult.error("不到派车时间");
                }
                //下单---运行时异常
                taskJob.beginDispathCars(tasks,true,driverCar.getNotifyId(),"手动使用优先权",tasks.getPrivilege()>0,flag,null);
                driver.setPrivilegeCnt(driver.getPrivilegeCnt()-1);
                driverService.updateDriver(driver);
            }
        }
        return AjaxResult.success();
    }

    private Preproduction getPreproduction(TasksCars tc) {
        Preproduction preproduction = new Preproduction();
        preproduction.setProductLine(tc.getProductLine());
        SimpleDateFormat sfd = new SimpleDateFormat("yyMMddHHmmssS");
        preproduction.setProductNumber(sfd.format(new Date())+"-"+tc.getId());//同车生产编号
        preproduction.setConcreteLabel(tc.getProductKind());///标号
        preproduction.setIsMortar(Constants.ON);//润泵砂浆否
        preproduction.setTaskNumber(tc.getTaskNumber());
        preproduction.setMixNumber(tc.getMixNumber());
        preproduction.setProjectName(tc.getTaskName());//任务名称
        preproduction.setConstructionPart(tc.getWaterPart());//浇筑部位
        preproduction.setPouringWay(tc.getWaterMethod());
        preproduction.setInnerNumber(tc.getCarNo()+"");
        if(!tc.isSure()){
            preproduction.setRemark("暂时未知");
        }else{
            preproduction.setRemark(tc.getDoorNo());
        }
        preproduction.setLat(tc.getLat());
        preproduction.setLng(tc.getLng());
        if(StringUtils.isNotBlank(tc.getStationStatus())&&tc.getStationStatus().equalsIgnoreCase("OUT")){
            preproduction.setState(2);//已经出站
        }
        preproduction.setProductAmount(tc.getPlanFangliang().setScale(2, RoundingMode.HALF_UP).doubleValue());//本车生产方量
        //
        if(null!=tc.getStatus()&&tc.getStatus()==3){
            preproduction.setSyncStatus(1);
        }else{
            preproduction.setSyncStatus(0);
        }
        preproduction.setTransportOrder(tc.getCarCnt());
        return preproduction;
    }
    /**
     * 获取当前司机正在进行的任务
     * */
    @PostMapping("/getCurrentCarTask")
    public AjaxResult getCurrentCarTask(){
        DriverCar driverCar = redisUtils.getLoginDriverCar();
        TasksCars item = redisUtils.get(Constants.TASKINGCAR_PATTEN+driverCar.getNotifyId(),TasksCars.class);
        if(null==item){
            List<TasksCars> tasksCars = tasksCarsService.selectTasksCarsList(new TasksCars(){{setNotifyId(driverCar.getNotifyId());}});
            if(tasksCars!=null&&tasksCars.size()>0){
                item = tasksCars.get(0);
            }
        }
        if(null!=item&&item.getStatus() == Constants.TASK_STATUS_ING){
            return AjaxResult.success().put("task",item).put("flag",true);
        }
        return AjaxResult.success().put("task",new Tasks()).put("flag",false);
    }

    /**
     * 获取通知列表
     * */
    @GetMapping("/getMsgList")
    public AjaxResult getMsgList(String page){
        DriverCar driverCar = redisUtils.getLoginDriverCar();
        int start =0;
        int end = Constants.pagesize;
        if(StringUtils.isNotEmpty(page)){
            start = (Integer.valueOf(page)-1)*Constants.pagesize;
            end = Integer.valueOf(page)*Constants.pagesize;
        }
        String sqlWhere = " order by create_time desc limit "+start+","+end;
        List<TaskNotification> list = taskNotificationService.selectTaskNotificationList(new TaskNotification(){{setNotifyId(Long.valueOf(driverCar.getNotifyId()));setSqlWhere(sqlWhere);}});
        return AjaxResult.success().put("list",list);
    }


    /**
     * 选出来一个主机进行生产
     * 此方法只用于【特权者】和【调度员】手工调度使用，其他通用规则不适用
     * */
    /*public Integer getProductLine2Task(Tasks task,boolean... isPrivilege) {
        PreproductionQueue que = null;
        *//**
         * 分配主机号，
         * 1. 检查主机状态，开机才能打料
         * 2. 检查主机当前石子类型，如果任务单是卵石，只能在能打卵石的主机生产
         * 3. 检查主机生产排队状态，哪个少去哪个。
         * 4. 如果两个主机都满了，就检查redis队列情况
         * 如果是卵石只能在1号主机
         * **//*
        List<ProductLine> productLines = redisUtils.getLists(Constants.PRODUCT_LINE_PATTERN,ProductLine.class);
        if(null==productLines)
            return null;
        String ids = tasksController.generateIds(productLines,task);//.getIdsFromProductline(productLines,task);
        if(StringUtils.isBlank(ids)){
            return null;
        }
        ids=StringUtils.substringBeforeLast(ids,"-");
        if(StringUtils.isBlank(ids)){
            return null;
        }
        List<String> idsArray = Arrays.asList(ids.split("-"));
        //检查产线是否可用====只检查redis队列情况就可以了，等下发的时候再检查主机楼的排队情况
        for (String productlineid :idsArray){
            TasksCars tasksCars = redisUtils.get(Constants.WAITE_QUEUE_PATTERN+productlineid,TasksCars.class);
            //这条线没有任务
            if(tasksCars==null){
                return Integer.valueOf(productlineid);
            }else{
                //不是特权车辆就替换
                if(!tasksCars.isPrivilegeTask()){
                    return  Integer.valueOf(productlineid);
                }
                continue;
            }
        }
        return null;
    }*/


    public Driver getDriver(DriverCar driverCar){
        Driver driver = redisUtils.get(Constants.DRIVER_PATTERN+driverCar.getDriverMobile(),Driver.class);
        if(null==driver){
            List<Driver> list = driverService.selectDriverList(new Driver(){{setDMobile(driverCar.getDriverMobile());}});
            if(list==null||list.size()==0){
                return null;
            }
            driver = list.get(0);
        }
        return driver;
    }

    /**
     * 回复通知车辆信息
     * **/
    @RequestMapping("/replayNotify")
    public AjaxResult replayNotify(HttpServletRequest request,String tnid,String content){
        TaskNotification taskNotification = taskNotificationService.selectTaskNotificationById(Long.valueOf(tnid));
        if(null!=taskNotification){
            taskNotification.setRemark(content);
            taskNotificationService.updateTaskNotification(taskNotification);
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }



    /**
     * 车辆到达目的地手动
     * **/
    @RequestMapping("/arrived")
    @Transactional(transactionManager = "masterTransactionManager")
    public AjaxResult arrived(HttpServletRequest request){
        DriverCar dc= redisUtils.getLoginDriverCar();
        if(null==dc){
            return AjaxResult.error("无法到达，没签到").put("code",-100);
        }
        RuningCar runingCar = redisUtils.get(Constants.RUNINGCAR_PATTEN+dc.getNotifyId(),RuningCar.class);
        if(null==runingCar||runingCar.getCarId()==null||runingCar.getCarNo()==null){
            return AjaxResult.error("无法到达，没签到").put("code",-100);
        }
        TasksCars rtc = redisUtils.get(Constants.TASKINGCAR_PATTEN+dc.getNotifyId(),TasksCars.class);
        if(rtc==null||rtc.getId()==null){
            return AjaxResult.error("无任务，不能到达");
        }
        if(rtc.getStatus()==Constants.TASK_STATUS_COMPLATE){
            return AjaxResult.error("已到达，请勿重复点击");
        }
        if(null==rtc.getStationStatus()||!rtc.getStationStatus().equalsIgnoreCase("OUT")){
            return AjaxResult.error("您尚未出站称重");
        }
        if(runingCar.isInner()){
            return AjaxResult.error("您尚未到达目的地");
        }
        TasksCars tasksCars = tasksCarsService.selectTasksCarsById(rtc.getId());
        runingCar.setEnding(true);
        runingCar.setTaskStatus(Constants.TASK_CAR_STATUS_COMPLATE);//完成
        //终点标志Y
        tasksCars.setIsEnd(Constants.YES);
        tasksCars.setInEnd(true);
        //终点时间
        tasksCars.setEndTime(new Date());
        //任务标志-完成
        tasksCars.setStatus(Constants.TASK_STATUS_COMPLATE);
        //车辆不在站内并且是第一辆车的情况下更新task的终点坐标
//        if(tasksCars.getCarCnt()!=null&&tasksCars.getCarCnt()==1&&!runingCar.isInner()){
        if(!runingCar.isInner()){
            if(null!=runingCar.getLat()&&null!=runingCar.getLng()){
                tasksCars.setLat(runingCar.getLat().toString());
                tasksCars.setLng(runingCar.getLng().toString());
                //更新其余车辆坐标信息
                updateOtherCarEndLatLng(tasksCars);
            }else{
                return AjaxResult.error("定位信息出错,请稍后重试");
            }
            //如果到达目的地，重新定位目的地坐标
            Tasks tasks = tasksService.selectTasksById(tasksCars.getTaskId()+"");
            tasks.setLat(runingCar.getLat()+"");
            tasks.setLon(runingCar.getLng()+"");
            tasksService.updateTasks(tasks);
            RedisDomainUtils.setRedisTaskDomain(tasks);
        }
        tasksCarsService.updateTasksCars(tasksCars);
        RedisDomainUtils.setRedisTaskCarDomain(tasksCars);
        redisUtils.set(Constants.RUNINGCAR_PATTEN+runingCar.getNotifyId(),runingCar,-1);
        return AjaxResult.success();
    }


    /**
     * 第一辆车到达，更改每一辆车的终点坐标
     * **/
    private void updateOtherCarEndLatLng(TasksCars tasksCars) {
        List<TasksCars> list = redisUtils.getLists(Constants.TASKINGCAR_PATTEN,TasksCars.class);
        for (TasksCars tc:list
             ) {
            //更改每一辆车的终点任务坐标
            if(null!=tc&&tc.getStatus()!=null&&tc.getTaskId()!=null&&tc.getTaskId().equals(tasksCars.getTaskId())&&tc.getStatus()==Constants.TASK_STATUS_ING){
                tc.setLat(tasksCars.getLat());
                tc.setLng(tasksCars.getLng());
                TasksCars dbtc = tasksCarsService.selectTasksCarsById(tc.getId());
                if(null!=dbtc){
                    dbtc.setLat(tc.getLat());
                    dbtc.setLng(tc.getLng());
                    tasksCarsService.updateTasksCars(dbtc);
                }
                RedisDomainUtils.setRedisTaskCarDomain(tc);
            }
        }
    }


    /**
     * 收到任务，给服务器反馈
     * **/
    @RequestMapping("/acceptedTask")
    public AjaxResult acceptedTask(HttpServletRequest request,@RequestParam(required = true) String taskCarId){
        if(StringUtils.isEmpty(taskCarId)){
            return AjaxResult.error("没有任务单号");
        }
        DriverCar driverCar = redisUtils.getLoginDriverCar();
        if(null==driverCar){
            return AjaxResult.error("请先登录").put("code",-100);
        }
        TasksCars tasksCars = redisUtils.get(Constants.TASKINGCAR_PATTEN+driverCar.getNotifyId(),TasksCars.class);
        if(null==tasksCars||tasksCars.getStatus()!=Constants.TASK_STATUS_ING||null==tasksCars.getTaskId()){
            return AjaxResult.error("您当前没有任务");
        }
        if(tasksCars.isSure()){
            return AjaxResult.error("您已经接收，请勿重复点击");
        }
        if(!tasksCars.getId().equals(Long.valueOf(taskCarId))){
            logger.debug("当前司机的任务单[{}]，和传值过来的任务单taskcarid:[{}]不匹配",tasksCars.getTaskName(),taskCarId);
            return AjaxResult.error("任务单已过期");
        }
        TasksCars dbtcar = tasksCarsService.selectTasksCarsById(tasksCars.getId());
        if(null!=dbtcar&&dbtcar.getStatus()==Constants.TASK_STATUS_ING){
            dbtcar.setSure(true);
            tasksCarsService.updateTasksCars(dbtcar);
        }else if(null!=dbtcar&&dbtcar.getStatus()==Constants.DELETE_FLAG){
            return AjaxResult.error("任务单已过期");
        }
        tasksCars.setSure(true);
        // 点击确定的售后再更新车次
        Tasks t = tasksService.selectTasksById(tasksCars.getTaskId().toString());
        t.setCarCnt(t.getCarCnt()+1);
        tasksService.updateTasks(t);
        tasksCars.setCarCnt(t.getCarCnt());
        tasksCarsService.updateTasksCars(tasksCars);
        RedisDomainUtils.setRedisTaskCarDomain(tasksCars);
        RedisDomainUtils.setRedisTaskDomain(t);
        TasksCars waitquere = redisUtils.get(Constants.WAITE_QUEUE_PATTERN+tasksCars.getProductLine(),TasksCars.class);
        if(null!=waitquere&&waitquere.getNotifyId().equalsIgnoreCase(tasksCars.getNotifyId())
                &&waitquere.getCarNo().equals(tasksCars.getCarNo())
                &&waitquere.getCarId().equals(tasksCars.getCarId())&&waitquere.getTaskId().equals(tasksCars.getTaskId())){
            waitquere.setSure(true);
        }
        PushUtils.pushSendTasking(String.format("任务已确认,接料口%s", tasksCars.getDoorNo()), tasksCars.getNotifyId(),null);
        return AjaxResult.success();
    }

}
