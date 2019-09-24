package com.ruoyi.project.duties.jobs;

import com.mchange.v2.collection.MapEntry;
import com.mysql.cj.log.LogFactory;
import com.ruoyi.api.ApiTasksCarsController;
import com.ruoyi.api.PushUtils;
import com.ruoyi.api.RedisDomainUtils;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.LocationUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.cars.driver.domain.Driver;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cars.tasksMixformula.domain.TasksMixformula;
import com.ruoyi.project.cars.tasksMixformula.service.ITasksMixformulaService;
import com.ruoyi.project.cemslink.preproduction.domain.Preproduction;
import com.ruoyi.project.cemslink.preproduction.domain.PreproductionQueue;
import com.ruoyi.project.cemslink.preproduction.service.IPreproductionService;
import com.ruoyi.project.cemslink.task.domain.Task;
import com.ruoyi.project.cemslink.task.service.ITaskService;
import com.ruoyi.project.duties.tasks.controller.TasksController;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.project.lines.productLine.domain.ProductLine;
import com.ruoyi.project.lines.productLine.service.IProductLineService;
import com.ruoyi.project.monitor.job.service.IJobLogService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tomcat.util.bcel.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.scanner.Constant;

import java.math.BigDecimal;
import java.net.CacheRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 定时任务调度测试
 * 1.任务优先级别  跨县车辆 > 大小车 > 好赖活
 * 2.卵石只能在北门和1号门
 * 3.优先权问题：
 *    一、不管有没有优先权都统一分配任务，如果有优先权的车辆对分配的任务不满意，可以使用优先权进行任务调正。
 *    二、主机楼只有两辆车 ①正在打灰   ②准备中
 *    三、队列里面排队的只有一辆车，【正常eg:1号车】或者【优先权88号车】，如果此时队列是【1号】车，来了【88号】车，【1号】车删除任务，【88号】车插入，再有优先权车进来提示需要等待。
 * @author admin
 */
@Component("autoTaskJob")
//@EnableScheduling
public class TaskJob
{
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ITasksService tasksService;
    @Autowired
    private ICarService carService;
    @Autowired
    private ITasksCarsService tasksCarsService;
    @Autowired
    private IJobLogService logService;
    @Autowired
    private TasksController tasksController;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IProductLineService productLineService;
    @Autowired
    private ITasksMixformulaService tasksMixformulaService;
    @Autowired
    private ApiTasksCarsController apiTasksCarsController;

    //生产指令
    @Autowired
    private IPreproductionService preproductionService;
    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    private static final Logger logger = LoggerFactory.getLogger(TaskJob.class);

    /**
     * 清理log日志
     * */
    public void clearLogs(){
        logger.debug("清理15天前的job日志");
        logService.deleteJobLogs();
    }


    /**
     * 自动执行任务分配方法
     * @author xuehf
     * @return void
     * @param
     * */
//    @Scheduled(cron = "0 0/1 * * * ? ")
    public void autoSendTasks(){
        System.out.println("执行自动调度的方法");
        //选择出来，所有的准备执行或者正在执行的单子,并且是非暂停状态的单子
        Map<String,String> prams = new HashMap<>();
        prams.put("pause",Constants.NO);
        prams.put("tcStatus",Constants.TASK_STATUS_ING+"");
        //选出一个要分配的任务
        /**
         * 1.置顶一个任务单，这个任务单分配不了其他的都分配不了
         * 2.其他的都平等
         * */
//      Tasks tasks = tasksService.selectOneToTask(prams);
        List<Tasks> list = tasksService.selectTasksList(new Tasks(){{
            setPause(Constants.NO);// 暂停
            setFinancePause(Constants.NO); //财务暂停
            setIsMixture(Constants.YES); //是否配比
            setIsSchedule(Constants.YES); //是否调度审核
            setSqlWhere(" and status < 3 order by topping desc,create_time desc"); // 任务中的
        }});
        if(null==list||list.size()==0){
            return;
        }
        for (Tasks tasks:list) {
            if(null == tasks){
                continue;
            }
            //如果该掐方了，就暂停
            if(isPinch(tasks))continue;
            //到时间后就开始分配
            if(isTimeToSendTask(tasks)){
                boolean flag=false;
                flag= doSendTask(tasks); // 韩峰临时注释
                //置顶任务失败
                if(!flag){
                    break;
                }
            }
        }
    }

    /** 掐方 */
    private boolean isPinch(Tasks tasks){
        //掐方标志为N，直接返回
        if(tasks.getIsPinch().equals(Constants.NO)){
            return false;
        }
        if(null!=tasks.getLjfangliang()&&tasks.getTotalFl()!=null){
            boolean ispinch = tasks.getPinchFl().compareTo(tasks.getLjfangliang())<=0 ? true:false;
            if(ispinch){
                if(tasks.getPause().equalsIgnoreCase(Constants.NO)){
                    tasks.setPause(Constants.YES);
                    tasksService.updateTasks(tasks);
                    Tasks t = redisUtils.get(Constants.TASK_PATTEN+tasks.getId(),Tasks.class);
                    t.setPause(Constants.YES);
                    RedisDomainUtils.setRedisTaskDomain(t);
                }
                return true;
            }
        }
        return false;
    }


    /** 判断间隔时间
     * 1. 根据终点车辆判断是否发料
     * 2. <2车 按照派车间隔 ，==2车 ，出来一辆，派发一辆  >2车不派发
     * 3. 出来一辆，派发一辆的操作只能在手机端上传坐标的时候派发
     * */
    public boolean isTimeToSendTask(Tasks tasks){
        int endPointCar = redisUtils.getEndPointCarCnt(tasks);
        if(null!=tasks.getTargetCarcnt()&&endPointCar==tasks.getTargetCarcnt()&&tasks.getTargetCarcnt()>0){
            logger.debug("这个任务终点车辆数目是[{}],只有出来一辆车才会派任务",endPointCar);
            return  false;
        }else if(null!=tasks.getTargetCarcnt()&&endPointCar>tasks.getTargetCarcnt()&&tasks.getTargetCarcnt()>0){
            logger.debug("[{}]任务的终点车辆数目大于[{}]辆车，不进行派发任务的操作",tasks.getName(),tasks.getTargetCarcnt());
            return false;
        }
        if((System.currentTimeMillis() - tasks.getStartTime().getTime())<0){
            logger.debug("[{}]任务还没到开始时间，不进行派发任务的操作",tasks.getName());
            return false;
        }
        //暂停状态的任务不自动
        if(tasks.getPause().equalsIgnoreCase(Constants.YES)){
            return false;
        }
        //选出当前任务的最后一辆车的出发时间，计算派车间隔
        List<TasksCars> tasksCars = tasksCarsService.selectTasksCarsList(new TasksCars(){{
            setTaskId(Long.valueOf(tasks.getId()));
            setSqlWhere(" and status in (1,3) and is_sure =1 order by id desc limit 1");}});
        if(null!=tasksCars&&tasksCars.size()>0){
            TasksCars last = tasksCars.get(0);
            Date startTime = last.getStartTime();
            //此辆车还没出发,不派任务
            if(null==startTime){
                //如果上一车没有出发，并且此车派发间隔小于10分钟
                if(tasks.getSubTime()!=null&&tasks.getSubTime()<10){
                    //比较上一车的分派时间跟当前时间比较
                    startTime = last.getCreateTime();
                }else{
                    //如果上一车没有出站，并且到了到了发车时间，那么跟上一车的创建时间相比较，如果超过间隔并且+10分钟，就发派车
                    Long minite=((System.currentTimeMillis()-last.getCreateTime().getTime()))/(1000*60);
                    if(minite>(tasks.getSubTime()+10)){
                        return true;
                    }else{
                        logger.debug("\n上一车灰还没有出站，不派发任务,任务单[{}]\n",tasks.getName());
                        return false;
                    }
                }
            }
            Long s = ((System.currentTimeMillis()-startTime.getTime())/(1000*60));
            if(tasks.getSubTime().compareTo(s.intValue())<=0){
                return true;
            }else if(tasks.getTimeToTask()){
                //如果派车标志位改了也进行派车
                logger.debug("\n终点剩下一辆车了，派车间隔没有到，派发任务\n");
                return true;
            }
        }else{
            //第一辆车
            return true;
        }
        logger.debug("\n时间不到，不派发任务\n");
        return false;
    }


    /**
     * 自动执行任务暂停的方法
     * */
    public void autoPause()
    {
        System.out.println("执行无参方法");
        //选择出来，所有的正在执行的单子,并且是非暂停状态的单子
        List<Tasks> taskingList = tasksService.selectTasksList(new Tasks(){{
            setStatus(Constants.TASK_STATUS_ING);
            setPause(Constants.NO);
            setSqlWhere(" and (ljfangliang + pinch_fl >= total_fl)");
        }});
        if(null!=taskingList){
            taskingList.forEach(item->{
                item.setPause(Constants.YES);
                tasksService.updateTasks(item);
                RedisDomainUtils.setRedisTaskDomain(item);
            });
        }
    }


    /**
     * 自动执行任务完成的方法
     * */
    public void autoComplete()
    {
        System.out.println("执行无参autoComplete方法");
        //选择出来，所有的正在执行的单子,并且是非暂停状态的单子
        List<Tasks> taskingList = tasksService.selectTasksList(new Tasks(){{
            setStatus(Constants.TASK_STATUS_ING);
            setSqlWhere(" and ljfangliang >= total_fl ");
        }});
        if(null!=taskingList){
            taskingList.forEach(item->{
                item.setStatus(Constants.TASK_STATUS_COMPLATE);
                tasksService.updateTasks(item);
                RedisDomainUtils.setRedisTaskDomain(item);
            });
        }
    }


    /**
     * 自动删除已经完成的任务,清理缓存
     * */
    public void autoClearRedis(){
        List<Tasks> taskList = redisUtils.getLists(Constants.TASK_PATTEN+"*",Tasks.class);
        if(taskList.size()>0){
            taskList.forEach(item->{
                if(item.getStatus()==Constants.TASK_STATUS_COMPLATE){
                    redisUtils.delete(Constants.TASK_PATTEN+item.getId());
                }
            });
        }
    }

    /**
     * 对一条任务进行分配
     *
     * 首先给一个单子排任务:
     * 1.选出空闲车辆，空闲车辆怎么选择？
     * runing里面的task状态
     * 2.选出符合条件的车辆，大小车等，加长斗，鹅卵石等（鹅卵石只能在1号住几楼，也就是北门和1号门，派单通知的时候的属性，不是车辆的属性)
     * 3.选出以上条件的上一单数据的好赖活跟当前任务做比对，还是说，如果系统判断当前是赖活，就扫描以上车辆今天的赖活最少的一个分给他，如果是好活，就选择好活最少的一个给他
     * 4.长垣的任务单没完成任务的话，车辆不能接活
     * select cnt ，carid from taskcar where creattime between and isgoodsjob =Y and carid in groupby car id oeder by cnt asc limit 1
     * */
    public boolean doSendTask(Tasks task){
        boolean flag = false;
        if(null!=task.getTopping()&&task.getTopping()==1){
            flag = true;
        }
        //0.判断是否有没有接单的单子，如果有，就不派发
        List<TasksCars> dbcList = tasksCarsService.selectTasksCarsList(new TasksCars(){{
            setTaskId(Long.valueOf(task.getId()));
            setSqlWhere(" and is_sure = 0 and status = 1 ");
        }});
        if(dbcList!=null&&dbcList.size()>0){
            logger.debug("[{}]任务单含有没有接受任务的车辆，不进行派单。车辆：[{}]号车",task.getName(),dbcList.get(0).getCarNo());
            return true;
        }
        //1. 选择出来
        List<RuningCar> list = redisUtils.getLists(Constants.RUNINGCAR_PATTEN,RuningCar.class);
        //如果没有值班车辆，就等下一次继续分配
        if(null==list){
            logger.debug("没有签到车辆，任务派发失败");
            if(flag){
                //置顶车辆没完成，其他都不派活
                logger.debug("置顶任务【{}】没有找到车辆，其他任务不派车",task.getName());
                return false;
            }
            return true;
        }
        //过滤执行中的车辆,匹配车辆类型
        list = filterCars(list,task);
        //过滤完了，没有车辆就继续下一个
        if(null == list||list.size()==0){
            logger.debug("没有筛选到可用的车辆列表，任务派发失败");
            if(flag){
                //置顶车辆没完成，其他都不派活
                logger.debug("置顶任务【{}】没有找到车辆，其他任务不派车",task.getName());
                return false;
            }
            return true;
        }

        //拼接sql的in
        List<Long> ids_arr = new ArrayList<>();
        List<Long> notifyIds = new ArrayList<>();
        List<Car> carlists = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        for (RuningCar car:list){
            if(StringUtils.isBlank(car.getCarId())||StringUtils.isBlank(car.getCarNo())){
                continue;
            }
            ids.add(Long.valueOf(car.getCarId()));
//            ids_arr.add(Long.valueOf(car.getCarId()));
            notifyIds.add(Long.valueOf(car.getNotifyId()));
            //判断掐方 end
//            carlists.add(redisUtils.get(Constants.CAR_PATTEN+car.getCarId(),Car.class));
        }
        if(null==ids||ids.size()==0){
            return true;//下一次
        }
        //好活
        TasksCars seleced = null;
        if(null==task.getPrivilege()||task.getPrivilege()<=0){
            seleced = tasksCarsService.selectOneGoodCar(ids);
        }else{
            //赖活
            seleced = tasksCarsService.selectOneBadCar(ids);
        }
        if(null==seleced){
            seleced = new TasksCars();
            seleced.setNotifyId(notifyIds.get(0)+"");
            for (RuningCar car:list){
                if(null!=car&&car.getCarId()!=null&&Long.valueOf(car.getCarId()).equals(seleced.getCarId())){
                    seleced.setNotifyId(car.getNotifyId());
                    break;
                }
            }
            DriverCar driverCar = redisUtils.get(Constants.DRVCAR_PATTEN+seleced.getNotifyId(),DriverCar.class);
            seleced.setCarId(driverCar.getCarId());
        }else{
            for (RuningCar car:list){
                if(null!=car&&car.getCarId()!=null&&Long.valueOf(car.getCarId()).equals(seleced.getCarId())){
                    seleced.setNotifyId(car.getNotifyId());
                    break;
                }
            }
        }
        if(StringUtils.isBlank(seleced.getNotifyId())){
            logger.debug("筛选出来的车辆没有登陆，派发不了任务");
            return true;//下一个任务
        }
        //开始分配车辆
        return beginDispathCars(task,flag,seleced.getNotifyId(),"系统自动派单",false,false,null);
    }


    /**
     * 开始分配任务
     *
     * @param task 任务单
     * @param flag 是否置顶任务(自动调度用，如果特权任务直接设置为true)
     * @param notifyId 通知id
     * @param privilege 是否特权任务
     * @param insertQueue 是否是插队任务
     * @param sjfl 砂浆方量
     * **/
    public boolean beginDispathCars(Tasks task,boolean flag,String notifyId,String remark,boolean privilege,boolean insertQueue,BigDecimal sjfl){
        //0.判断是否有没有接单的单子，如果有，就不派发
        if(!"系统自动派单".equalsIgnoreCase(remark)){
            List<TasksCars> dbcList = tasksCarsService.selectTasksCarsList(new TasksCars(){{
                setTaskId(Long.valueOf(task.getId()));
                setSqlWhere(" and is_sure = 0 and status = 1 ");
            }});
            if(dbcList!=null&&dbcList.size()>0){
                logger.debug("[{}]任务单含有没有接受任务的车辆，不进行派单。车辆：[{}]号车",task.getName(),dbcList.get(0).getCarNo());
                throw new BaseException("任务单含有没有接受任务的车辆");
            }
        }
        Integer productLine = getOneProduct2Dispath(task);
        if(null==productLine){
            logger.debug("没有产线可以使用[{}]",task.getName());
            throw new BaseException("没有产线可以使用");
        }
        List<Task> cemstlist = taskService.selectTaskList(new Task(){{
            setTaskNumber(task.getPlanOrderNo());
            setProductLine(productLine);
        }});
        if(null==cemstlist||cemstlist.size()==0){
            logger.debug("产线没有任务单");
            return true;
        }
        Task cemsTask = cemstlist.get(0);
        DriverCar driverCar = redisUtils.get(Constants.DRVCAR_PATTEN+notifyId,DriverCar.class);
        //判断是否主班车辆
        String carnos = redisUtils.getTodayCarNos();
        if(StringUtils.isBlank(carnos)){
            logger.debug("没有主班车辆");
            return false;
        }
        if(!(","+carnos+",").contains(driverCar.getCarNo().toString())){
            logger.debug("此车辆不在主班车辆范围内");
            throw new BaseException("此车辆不在主班车辆范围内");
        }
        Driver driver = apiTasksCarsController.getDriver(driverCar);
        TasksCars caring = new TasksCars();
        caring.setCarBrand(driverCar.getCarBrand());
        caring.setCarNo(driverCar.getCarNo());
        caring.setCarId(driverCar.getCarId());
        caring.setStatus(Constants.TASK_STATUS_ING);//收到任务
        caring.setTaskId(Long.valueOf(task.getId()));
        caring.setNotifyId(driverCar.getNotifyId());
        caring.setReceiver(task.getReceiver());//接收单位
        caring.setReceivePhone(task.getReceivorMobile());////收货人电话
        caring.setOfficer(task.getCreateBy());//业务经理
        caring.setOfficerMobile(task.getOfficerMobile());//业务经理电话
        caring.setTaskTime(task.getArriveTime());//到达时间
        caring.setProductKind(task.getProductKind());//砼标号
        caring.setMixNumber(cemsTask.getMixNumber());//配合比
        caring.setTaskNumber(task.getPlanOrderNo());//任务编号\
        caring.setDriverMobile(driverCar.getDriverMobile());//司机电话
        caring.setRemark(remark);
        caring.setPrivilegeTask(privilege);
        //获取一个主机
        //modify by xuehf 2019-06-28 去掉所有产线都生产的情况，一个任务单只下发给一个产线；
//        Integer productLineId = getProductLine2Task(task);
        Integer productLineId = checkProductIfNomal(productLine+"");
        //modify by xuehf 2019-06-28 去掉所有产线都生产的情况，一个任务单只下发给一个产线；
        if(null==productLineId){
            logger.debug("没有满足要求的产线或者REDIS队列已满没有空余位置可用");
            if(flag){
                //置顶车辆没完成，其他都不派活
                if(privilege){
                    logger.debug("手工调度任务【{}】没有找到可用产线，其他任务不派车",task.getName());
                    return false;
                }else if(insertQueue){
                    logger.debug("司机使用特权：任务【{}】没有找到车辆，其他任务不派车",task.getName());
                    return false;
                }else{
                    logger.debug("置顶任务【{}】没有找到车辆，其他任务不派车",task.getName());
                    return false;
                }
            }
            return  true;
        }
        ProductLine pLine = redisUtils.get(Constants.PRODUCT_LINE_PATTERN+productLine,ProductLine.class);
        //设置主机号
        caring.setProductLine(productLineId);
        Car curcar = redisUtils.get(Constants.CAR_PATTEN+caring.getCarId(),Car.class);
        //设置门号
        if(curcar.getHeight().compareTo(pLine.getMixheight())<=0){
            caring.setDoorNo(pLine.getMixDoorName());
        }else{
            caring.setDoorNo(pLine.getMaxDoorName());
        }
        caring.setQueueStatus(Constants.QUEUE_INIT);//排队
        //任务的终点经纬度
        caring.setLat(task.getLat());
        caring.setLng(task.getLon());
        Car car = redisUtils.get(Constants.CAR_PATTEN+driverCar.getCarId(),Car.class);
        //背砂浆需要减去多少方量
        caring.setPlanFangliang(car.getFangl());
        //设置皮重为车重
        caring.setCarWeight(car.getCarWeight());
        caring.setWaterMethod(task.getWaterMethod());
        caring.setWaterPart(task.getWaterPart());
        //派发时间，用来计算3分钟内司机是否确认订单
        caring.setDispatchTime(System.currentTimeMillis());
        if(car == null){
            logger.debug("【id={}】的车在redis缓存里面不存在，派发失败",driverCar.getCarId());
            if(flag){
                //置顶车辆没完成，其他都不派活
                logger.debug("置顶任务【{}】没有找到车辆，其他任务不派车",task.getName());
                return false;
            }
            return true;
        }
        //第一车润泵砂浆，等任务下发的时候进行单独下发砂浆配比
//        if (task.getTaskId()==null){
        if (task.getCarCnt()==null||task.getCarCnt()==0){
            if(null!=task.getShajiangfl()&&task.getShajiangfl().compareTo(new BigDecimal(0))>0){
                caring.setPlanFangliang(car.getFangl().subtract(car.getShajiangfl()));
                if(null!=task.getMaxcarfl()&&task.getMaxcarfl().compareTo(new BigDecimal(0))>0){
                    //如果当前车载容量大于任务单的允许的最大方量，则重新设置本车计划方量
                    if(caring.getPlanFangliang().compareTo(task.getMaxcarfl())>0){
                        caring.setPlanFangliang(task.getMaxcarfl());
                    }
                }
                caring.setShajiangfl(null!=task.getShajiangfl()?task.getShajiangfl().setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue():0);
            }
        }else if(null!=sjfl&&sjfl.compareTo(new BigDecimal(0))>0){
            caring.setPlanFangliang(car.getFangl().subtract(car.getShajiangfl()));
            if(null!=task.getMaxcarfl()&&task.getMaxcarfl().compareTo(new BigDecimal(0))>0){
                //如果当前车载容量大于任务单的允许的最大方量，则重新设置本车计划方量
                if(caring.getPlanFangliang().compareTo(task.getMaxcarfl())>0){
                    caring.setPlanFangliang(task.getMaxcarfl());
                }
            }
            caring.setShajiangfl(sjfl.doubleValue());
        }
        if(null!=task.getMaxcarfl()&&task.getMaxcarfl().compareTo(new BigDecimal(0))>0){
            //如果当前车载容量大于任务单的允许的最大方量，则重新设置本车计划方量
            if(caring.getPlanFangliang().compareTo(task.getMaxcarfl())>0){
                caring.setPlanFangliang(task.getMaxcarfl());
            }
        }
        //任务单每车减少的方量
        if(null!=task.getCarsubfl()&&task.getCarsubfl().compareTo(new BigDecimal(0))>0){
            caring.setPlanFangliang(caring.getPlanFangliang().subtract(task.getCarsubfl()));
        }
        // del by xuehf 2019-07-23 begin 调单bug修复：如果系统自动派单已经派了2车，此时累计carcnt是2，又派了一车，累计是3，但是第三车车主没有接收，此时从别的地方调了一单过来，车次就变为了4，但是3车车主仍然没有接收，导致自动删除了。那么就会形成两个4车的情况
        /*if(!insertQueue){
            int carCnt = task.getCarCnt()+1;
            caring.setCarCnt(carCnt);
        }*/
        // del by xuehf 2019-07-23 end
        caring.setTaskName(task.getName());
        /*if(null!=driver&&null!=driver.getPrivilegeCnt()&&driver.getPrivilegeCnt()>0){
            caring.setTaskName(task.getName());
        }else{
            caring.setTaskName("暂时未知");
        }*/
        //判断是否到掐方放量
        if(task.getLjfangliang().add(caring.getPlanFangliang()).compareTo(task.getPinchFl())>0){
            if(task.getLjfangliang().add(caring.getPlanFangliang()).compareTo(task.getTotalFl())>0){
                task.setPause(Constants.YES);
                logger.debug("再发一车就超过掐方的方量了，所以暂停");
                tasksService.updateTasks(task);
                return true;
            }
            task.setPause(Constants.YES);
        }
        //保存信息
        tasksCarsService.insertTasksCars(caring);
        task.setStatus(Constants.TASK_STATUS_ING);
        //如果不是插队的就更新累计车次
        if(!insertQueue){
//            task.setCarCnt(task.getCarCnt()+1);
            task.setTimeToTask(false);//重置派车标志位置
        }
        //更新任务状态
        tasksService.updateTasks(task);
        String content = "您有新的派车任务,请点击确定查看接料口";
        //更新正在运行的车辆信息
        setReceiveRunningState(caring,task);
        /**最重要的一个环节，放到队列中*/
        RedisDomainUtils.setRedisLineWaitQueueDomain(caring);
        //通知司机
        Map<String,String> pa = new HashMap<>();
        pa.put("taskType","POP");
        pa.put("taskCarId",caring.getId()+"");
        PushUtils.pushSendTasking(content,caring.getNotifyId(),pa);
        logger.debug("\n系统派车成功！【{}】号车，接料口[{}]",caring.getCarNo(),caring.getDoorNo());
        return true;
    }


    /**
     * 根据任务单获取一条产线去生产
     * */
    private Integer getOneProduct2Dispath(Tasks tasks) {
        //1.首先选择出所有的开启的主机
        List<ProductLine> productLines = productLineService.selectProductLineList(new ProductLine(){{setState(0);}});
        if(null==productLines||productLines.size()==0){
            logger.debug("主机楼没有可用主机");
            return null;
        }
        List<Integer> productList = new ArrayList<>();
        for (ProductLine line:productLines
             ) {
            //2. 检查此条产线是否有此任务单
            TasksMixformula tmp = tasksMixformulaService.selectOne(new TasksMixformula(){{
                setTaskId(Long.valueOf(tasks.getId()));
                setProductLine(line.getProductLine());
                setSyncstatus(1);
            }});
            if(tmp==null){
                continue;
            }
            //2. 检查此条产线是否有此任务单
            List<Task> tmpTaskList = taskService.selectTaskList(new Task(){{
                setTaskNumber(tasks.getPlanOrderNo());
                setProductLine(line.getProductLine());
            }});
            if(tmpTaskList==null||tmpTaskList.size()==0){
                continue;
            }
            //检查鹅卵石
            if(null!=tasks.getIsCobblestone()&&tasks.getIsCobblestone().equalsIgnoreCase(Constants.YES)){
                if(!line.getStoneType().equals(Constants.COBBLESTONE)){
                    logger.debug("该条[{}]产线不打卵石",line.getProductLine());
                    continue;
                }
            }
            productList.add(line.getProductLine());
            /*TasksCars tasksCars = redisUtils.get(Constants.WAITE_QUEUE_PATTERN+line.getProductLine(),TasksCars.class);
            //排队队列为空，直接return
            if(null==tasksCars){
                return line.getProductLine();
            }*/
        }
        if(productList.size()==0){
            logger.debug("产线没有该任务单【{}】，不能下发",tasks.getName());
            return null;
        }

        //选择较少的一条生产线
        Preproduction productLine = preproductionService.selectOneProductLine2do(productList);
        if(null!=productLine){
            TasksCars tasksCars = redisUtils.get(Constants.WAITE_QUEUE_PATTERN+productLine.getProductLine(),TasksCars.class);
            //排队队列为空，直接return
            if(null==tasksCars){
                return productLine.getProductLine();
            }
        }
        //如果里面没有，就随机一条
        for (Integer proLine:productList
        ) {
            //生产线谁的少就派给谁
            TasksCars tcar = redisUtils.get(Constants.WAITE_QUEUE_PATTERN+productLine.getProductLine(),TasksCars.class);
            if(null==tcar){
                return proLine;
            }
        }
        logger.debug("【{}】任务单没有找到可用的产线，不进行派发",tasks.getName());
        return null;
    }


    /**
     * 检查产线是否生产正常
     * @param productLine 产线id
     * */
    public Integer checkProductIfNomal(String productLine) {
        //1.检查产线的生产关闭状态
        ProductLine productLine1 = redisUtils.get(Constants.PRODUCT_LINE_PATTERN+productLine,ProductLine.class);
        if(null==productLine1){
            List<ProductLine> list= productLineService.selectProductLineList(new ProductLine(){{
                setProductLine(Integer.valueOf(productLine));
            }});
            if(list==null||list.size()==0){
                logger.debug("[{}]号产线在redis或者数据库中没有找到，不能派发任务",productLine);
                return null;
            }
            productLine1 = list.get(0);
            if(productLine1.getState()!=0){
                logger.debug("[{}]号产线已经关闭生产，不能派发任务",productLine);
                return null;
            }
        }
        TasksCars tasksCars = redisUtils.get(Constants.WAITE_QUEUE_PATTERN+productLine,TasksCars.class);
        //这条线没有任务
        if(tasksCars==null){
            logger.debug("[{}]号产线的redis队列允许插入",productLine);
            return Integer.valueOf(productLine);
        }
        logger.debug("[{}]号产线的redis队列已满,等待生产完成再派",productLine);
        return null;
    }


    /**选出来一个主机进行生产*/
    /*private Integer getProductLine2Task(Tasks task) {
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
        String ids = tasksController.generateIds(productLines,task);
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
            }
        }
        return null;
    }*/

    /**
     * @author xuehf
     * 过滤执行中的车辆,匹配车辆类型,是否加长斗
     * 1.业务员指定车辆如果有任务单，那么提前2个小时在站内等待，不给分配任务
     * */
    public List<RuningCar>  filterCars(List<RuningCar> list,Tasks task){
        logger.debug("筛选车辆开始");
        //过滤车辆
        for(ListIterator<RuningCar> iter = list.listIterator();iter.hasNext();){
            RuningCar runcar = iter.next();
            if(null==runcar){
                logger.debug("runcar 为空");
                continue;
            }
            if(StringUtils.isBlank(runcar.getCarId())||StringUtils.isBlank(runcar.getCarNo())){
                logger.debug("该登陆车辆没有车号[{}]",runcar.getNotifyId());
                continue;
            }

            //--add by xuehf bgin 2019-07-15 主班车辆增加
            String carnos = redisUtils.getTodayCarNos();
            if(StringUtils.isBlank(carnos)){
                logger.debug("主班车队没有维护,不能派车");
                continue;
            }
            if(!(","+carnos+",").contains(runcar.getCarNo())){
                logger.debug("[{}]号车-该车辆不在主班车辆范围[{]]",runcar.getCarNo(),carnos);
                iter.remove();
                continue;
            }
            //--add by xuehf end 2019-07-15
            //任务车辆
//            TasksCars tasksCars = redisUtils.get(Constants.TASKINGCAR_PATTEN+runcar.getNotifyId(),TasksCars.class);
            //具体车号
            Car car = redisUtils.get(Constants.CAR_PATTEN+runcar.getCarId(),Car.class);
            Boolean isDemo = Boolean.valueOf(Global.getConfig("project.demo"));//演示系统
            /**1.王文博王总要求 站附近300米 派任务 开始**/
            if(!runcar.isInner()&&!isDemo){
                logger.debug("【{}】车辆不在站内",car.getCarNo());
                iter.remove();
                continue;
            }
            /**2.车辆必须不能有任务在身*/
            if(runcar.getTaskStatus()==Constants.TASK_STATUS_ING){
                //移除正在执行任务的车辆
                iter.remove();
                continue;
            }
            TasksCars runingTcar = redisUtils.get(Constants.TASKINGCAR_PATTEN+runcar.getNotifyId(),TasksCars.class);
            if(null!=runingTcar&&null!=runingTcar.getCarId()&&runingTcar.getCarNo()!=null&&runingTcar.getStatus()!=null){
                if(runingTcar.getStatus()==Constants.TASK_STATUS_ING){
                    logger.debug("[{}]此车辆有任务[{}]在身，不能接单",runingTcar.getCarNo(),runingTcar.getTaskName());
                    iter.remove();
                    continue;
                }
            }

            /**3.非指定车辆remove begin*/
            String tskNos = task.getCarList();
            if(StringUtils.isNotEmpty(tskNos)){
                tskNos=","+tskNos+",";//由 1，3，4，14变成",1,3,4,14,"这样就不会有4,&14,相同的情况
                if(!tskNos.contains(","+car.getCarNo()+",")&&StringUtils.isNotEmpty(car.getCarNo()+"")){
                    logger.debug("该任务指定的有车辆，[{}]号车辆不是指定用車",car.getCarNo());
                    iter.remove();
                    continue;
                }
            }

            /**4.如果等待的不是这个任务，就移除该车辆 begin*/
            Map<String,String> waitMap = getWaitingList();//获取提前一个小时等待车列表
            if(null!=waitMap&&waitMap.size()>0){
                boolean flag = false;
                for(String key:waitMap.keySet()){
                    String waitingTaskIds = waitMap.get(key);
                    //如果当前车辆在等待队列并且等待的不是这个task，就移除此车辆
                    if(!waitingTaskIds.contains("-"+task.getId()+"-")&&key.equalsIgnoreCase(runcar.getCarId())){
                        logger.debug("【{}】号车辆有等待的任务，不能接活",car.getCarNo());
                        iter.remove();
                        flag = true;
                        break;
                    }
                }
                if(flag)continue;
            }
            /**5.因为累计放量没有达到掐方方量，需要判断剩余的方量够不够一车。
            BigDecimal yuLiang = task.getTotalFl().subtract(task.getLjfangliang());*/
            /**5.车辆高低筛选*/
            if(car.getHeight().compareTo(task.getHeight())>0||car.getHeight()==0){
                logger.debug("[{}]号车高度是[{}]米,任务单限高[{}]米",car.getCarNo(),car.getHeight(),task.getHeight());
                iter.remove();
                continue;
            }
            /**6.车辆方量筛选*/
            if(car.getFangl().compareTo(new BigDecimal(task.getMixCar()))<0||car.getFangl().compareTo(new BigDecimal(task.getMaxCar()))>0){
                logger.debug("任务单限方量[{}]~[{}]方,[{}]号车方量[{}]",task.getMixCar(),task.getMaxCar(),car.getCarNo(),car.getFangl());
                iter.remove();
                continue;
            }
            /**7.跨县任务单*/
            if(task.getIsOtherArea().equalsIgnoreCase(Constants.YES)){
                if(car.getIsOtherCar().equalsIgnoreCase(Constants.NO)){
                    logger.debug("跨县任务单，[{}]号车不是跨县车辆",car.getCarNo());
                    iter.remove();
                    continue;
                }
            }
            //是否加长斗
            if(task.getIsJcdou().equalsIgnoreCase(Constants.YES)){
                if(car.getJcdou().equalsIgnoreCase(Constants.NO)){
                    logger.debug("任务单需要加长抖,[{}]号车没有加长抖",car.getCarNo());
                    iter.remove();
                    continue;
                }
            }
            //长垣车辆不拉本地的赖活
            if(task.getPrivilege()!=null&&task.getPrivilege()>0&&task.getIsOtherArea().equalsIgnoreCase(Constants.NO)){
                if(car.getIsOtherCar().equalsIgnoreCase(Constants.YES)){
                    logger.debug("跨县车辆不拉本地赖活");
                    iter.remove();
                    continue;
                }
            }
        }
        return list;
    }


    /**正在执行的有没有跨县任务*/
    private boolean isOtherTasking(){
        List<Tasks> list = redisUtils.getLists(Constants.TASK_PATTEN,Tasks.class);
        boolean flag=false;
        for (Tasks item:list){
            if(item.getIsOtherArea().equalsIgnoreCase(Constants.YES)&&item.getStatus()!=Constants.TASK_STATUS_COMPLATE){
                flag =true;
                break;
            }
        };
        return flag;
    }




    /**
     * 获取车辆列表
     * */
    private Map<String,String> getWaitingList() {
        Map<String,String> map = new HashMap<>();
//        List<RuningCar> list = redisUtils.getLists(Constants.RUNINGCAR_PATTEN,RuningCar.class);
        List<Tasks> tasksList = redisUtils.getLists(Constants.TASK_PATTEN,Tasks.class);
        tasksList.forEach(item->{
            if(StringUtils.isNotEmpty(item.getCartypeList())){
                //规定时间内的
                if(item.getStartTime().getTime()-System.currentTimeMillis()<= Constants.PRE_WAITE_TIME.doubleValue()*60*60*1000){
                    String[] tm = item.getCartypeList().split(",");
                    Arrays.stream(tm).forEach(x->{
                        String xm = StringUtils.isBlank(map.get(x))?"-":map.get(x);
                        map.put(x,xm+item.getTaskId()+"-");//("3号车:-task1-task2-task3-")
                    });
                }
            }
        });
        return map;
    }

    /**
     * 设置运行中的车辆状态
     * */
    public void setReceiveRunningState(TasksCars caring,Tasks task){
        //自动任务获取不到当前登陆状态
//        DriverCar driverCar = redisUtils.getLoginDriverCar();
        if(null == caring){
            throw new BaseException("");
        }
        RuningCar runingCar = redisUtils.get(Constants.RUNINGCAR_PATTEN+caring.getNotifyId(),RuningCar.class);
        if(null==runingCar){
            runingCar = new RuningCar();
            runingCar.setCarNo(caring.getCarNo()+"");
            runingCar.setCarId(caring.getCarId()+"");
            runingCar.setTaskId(caring.getTaskId());
            runingCar.setNotifyId(caring.getNotifyId());
        }
        runingCar.setTasking(true);
        runingCar.setTaskStatus(Constants.TASK_STATUS_ING);
        //重置redis
        RedisDomainUtils.setRedisTaskCarDomain(caring);
        //更新任务到缓存
        RedisDomainUtils.setRedisTaskDomain(task);
        redisUtils.set(Constants.RUNINGCAR_PATTEN+caring.getNotifyId(),runingCar,-1);
    }




}
