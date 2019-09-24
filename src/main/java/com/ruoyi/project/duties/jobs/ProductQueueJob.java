package com.ruoyi.project.duties.jobs;

import com.ruoyi.api.PushUtils;
import com.ruoyi.api.RedisDomainUtils;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cemslink.preproduction.domain.Preproduction;
import com.ruoyi.project.cemslink.preproduction.domain.PreproductionQueue;
import com.ruoyi.project.cemslink.preproduction.domain.ProductLineQueue;
import com.ruoyi.project.cemslink.preproduction.service.IPreproductionService;
import com.ruoyi.project.cemslink.task.domain.Task;
import com.ruoyi.project.cemslink.task.service.ITaskService;
import com.ruoyi.project.duties.tasks.controller.TasksController;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.project.heavlywork.entity.Product;
import com.ruoyi.project.heavlywork.service.IProductService;
import com.ruoyi.project.lines.productLine.domain.ProductLine;
import com.ruoyi.project.lines.productLine.service.IProductLineService;
import com.ruoyi.project.monitor.job.service.IJobLogService;
import com.ruoyi.project.monitor.server.domain.Sys;
import com.ruoyi.project.system.config.service.IConfigService;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.scanner.Constant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
@Component("productQueueJob")
//@EnableScheduling
public class ProductQueueJob implements SchedulingConfigurer
{
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ITasksService tasksService;
    @Autowired
    private IProductLineService productLineService;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IPreproductionService preproductionService;
    @Autowired
    private TasksController tasksController;
    @Autowired
    private ITasksCarsService tasksCarsService;
    @Autowired
    private ICarService carService;
    @Autowired
    private TaskJob taskJob;
    @Autowired
    private IConfigService iConfigService;

    @Autowired
    private IProductService productService;
    private Logger logger = LoggerFactory.getLogger(ProductQueueJob.class);
    /**9秒钟下发一次生产任务单*/
//    @Scheduled(cron = "0/9 * * * * ? ")
    public void dispathTask(){
        logger.debug("同步任务Task表开始");
        Long startTime = System.currentTimeMillis();
        //selectTasks4Dispath and SyncStatus is null and SyncStatus2 is null and is_schedule='Y' and  is_mixture ='Y'
        List<Tasks> list = tasksService.selectTasks4Dispath();
        for (Tasks t: list){
            try{
                tasksController.dispathOne(Long.valueOf(t.getId()));
            }catch (Exception ex){
                logger.debug(ex.getMessage());
            }
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("同步耗时:[{}]ms",endTime-startTime);
    }
    /**10秒钟查询一次redis排队队列情况，放到preproduction中间库
     * 这里面会校验任务单是否下发，然后更改状态
     * */
//    @Scheduled(cron = "0/10 * * * * ? ")
    public void dispathPreproduction(){
        Long startTime = System.currentTimeMillis();
//        List<Preproduction> list = preproductionService.selectPreproductionList(new Preproduction(){{setSqlWhere(" and (state is null or state = 1) order by ProductLine asc,state desc");}});
        List<ProductLine> productLines = productLineService.selectProductLineList(new ProductLine(){{setState(0);}});
        logger.debug("自动执行写入主机楼任务");
        if(productLines!=null&&productLines.size()>0){
            for(ProductLine line :productLines){                //
                try{
                    if(checkProductLine(line.getProductLine())){
                        insertPreproduct(line);
                    }
                }catch (Exception ex){
                    //出错后继续下一条线
                    ex.printStackTrace();
                    continue;
                }
            }
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("此任务总共执行了[{}]ms",endTime-startTime);
    }


    /**
     * 更改主机楼往erp同步的手工数据，避免报错而拥堵
     * 53秒同步一次，是为了防止跟上面的定时器重复，而并发
     * */
//    @Scheduled(cron = "0/53 * * * * ? ")
    public void updateTaskSyncStatus(){
        logger.debug("更改任务tb_task的Syncstatus开始");
        Long startTime = System.currentTimeMillis();
        preproductionService.updateMasterBuildingStatus();
        logger.debug("此任务总共执行了[{}]ms",System.currentTimeMillis()-startTime);
    }


    /**
     * 更改主机楼往erp同步的手工数据，避免报错而拥堵
     * 53秒同步一次，是为了防止跟上面的定时器重复，而并发
     * */
//    @Scheduled(cron = "0/16 * * * * ? ")
    public void updateTaskCarsDeleted(){
        logger.debug("更改任务tb_task_car的Syncstatus开始");
        Long startTime = System.currentTimeMillis();
        boolean flag = true;
        List<TasksCars> tclist = tasksCarsService.selectTasksCarsList(new TasksCars(){{
            setStatus(-1);
            setSyncStatus(0);
        }});
        for (TasksCars tc:tclist
             ) {
            List<Preproduction> exits = preproductionService.selectPreproductionList(new Preproduction(){{
                setInnerNumber(tc.getCarNo()+"");
                setTaskNumber(tc.getTaskNumber());
                setState(6);//已经删除的标志位
                setSyncStatus(1);
            }});
            if(exits!=null&&exits.size()>0){
                tc.setSyncStatus(1);
                tasksCarsService.updateTasksCars(tc);
            }
        }
        logger.debug("此任务总共执行了[{}]ms",System.currentTimeMillis()-startTime);
    }


    /**检查主机楼某一条产线的生的排队状况*/
    private boolean checkProductLine(Integer productLine) {
        //sort >0 是排队中 state=1是生产中 ，生产完了就sort=0了；syncstatus<>1 or syncstatus is null 是主机用来选要排单的
        List<Preproduction> list = preproductionService.selectPreproductionList(new Preproduction(){{setProductLine(productLine);setSqlWhere(" and ((state is null or state =1)  and (sort>0 or sort is null)) or (SyncStatus<>1  or SyncStatus is null) order by ProductLine asc,state desc");}});
        if(null!=list&&list.size()>1){
            logger.debug("redis插入生产队列时候:[{}]号线排队已满",productLine);
            return false;
        }
        return true;
    }


    /**
     * 任务单转换成预生产订单并且持久化
     * 下发主机楼时候要检查主机楼的排队情况
     * **/
    @Transactional(transactionManager = "slaveTransactionManager")
    public boolean insertPreproduct(ProductLine line){
        Long startTime = System.currentTimeMillis();
        Long endTime =0L;
        TasksCars tc = redisUtils.get(Constants.WAITE_QUEUE_PATTERN+line.getProductLine(),TasksCars.class);
        if(null==tc){
            logger.debug("下发[{}]号主机楼时，REDIS排队队列没有找到排队的车辆",line.getProductLine());
            endTime = System.currentTimeMillis();
            logger.debug("未插入任务执行了[{}]ms",endTime-startTime);
            return false;
        }
        TasksCars rdriverTaskcar = redisUtils.get(Constants.TASKINGCAR_PATTEN+tc.getNotifyId(),TasksCars.class);
        if(null==rdriverTaskcar){
            logger.debug("没有找到该任务[{}],[{}]",tc.getTaskName(),tc.getNotifyId());
            return false;
        }
        Tasks task = redisUtils.get(Constants.TASK_PATTEN+tc.getTaskId(),Tasks.class);
        if(task==null){
            task = tasksService.selectTasksById(tc.getTaskId()+"");
        }
        if(task==null){
            logger.debug("下发[{}]号主机楼时，任务单没有找到",line.getProductLine());
            endTime = System.currentTimeMillis();
            logger.debug("未插入任务执行了[{}]ms",endTime-startTime);
            return false;
        }
        //add by xuehf begin 2019-07-05 需要司机确认订单才能下发
        if(!rdriverTaskcar.isSure()){
            return checkTaskcarTimeOut(rdriverTaskcar);
        }
        //add by xuehf end 2019-07-05 需要司机确认订单才能下发
        //校验任务是否已经在中间表
        if(!checkExistTask(tc.getTaskNumber(),line.getProductLine().toString())){
            logger.debug("[{}]任务在中间表的[{}]产线中不存在，或者被人为删除,无法下发该指令",tc.getTaskNumber(),tc.getProductLine());
            return false;
        }

        //查询配置表中的主机楼
        String value = iConfigService.selectConfigByKey("mainBuild");
        //根据主机楼插入到不同的中间库中
        switch (value){
            case "0":
                Boolean isDemo = Boolean.valueOf(Global.getConfig("project.demo"));
                Preproduction preproduction = new Preproduction();
                preproduction.setProductLine(line.getProductLine());
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
                if(tc.getShajiangfl()!=null&&tc.getShajiangfl()>0){
                    preproduction.setProductAmount(tc.getPlanFangliang().setScale(2, RoundingMode.HALF_UP).doubleValue()-tc.getShajiangfl());//本车生产方量
                }else{
                    preproduction.setProductAmount(tc.getPlanFangliang().setScale(2, RoundingMode.HALF_UP).doubleValue());//本车生产方量
                }

                preproduction.setTransportOrder(tc.getCarCnt());
                preproduction.setRemark(tc.getDoorNo());
                if(isDemo){
                    preproduction.setSyncStatus(1);
                    preproduction.setSyncStatus2(1);
                    preproduction.setState(5);
                    preproduction.setSort(0);
                }
//        preproduction.setCustomerName(tc.getOfficerMobile());//换成收货人电话
                preproduction.setCustomerName(tc.getReceivePhone());//收货人电话
                preproductionService.insertPreproduction(preproduction);
                //砂浆订单
                if(null!=tc.getShajiangfl()&&tc.getShajiangfl().doubleValue()>0){
                    preproduction.setProductLine(line.getProductLine());
                    preproduction.setProductNumber(tc.getId()+"shajiang");//同车生产编号
                    preproduction.setConcreteLabel("砂浆");///标号
                    preproduction.setIsMortar(Constants.ON);//润泵砂浆否
                    preproduction.setTaskNumber(Constants.TASK_NUMBER_SHANGJIANG);
                    preproduction.setMixNumber(Constants.TASK_NUMBER_SHANGJIANG);
                    preproduction.setProjectName(tc.getTaskName());//任务名称
                    preproduction.setConstructionPart(tc.getWaterPart());//浇筑部位
                    preproduction.setPouringWay(tc.getWaterMethod());
                    preproduction.setInnerNumber(tc.getCarNo()+"");
                    preproduction.setProductAmount(tc.getShajiangfl());//本车生产方量
                    preproduction.setTransportOrder(tc.getCarCnt());
                    preproduction.setRemark(tc.getDoorNo());
                    preproduction.setCustomerName(tc.getReceivePhone());
                    if(isDemo){
                        preproduction.setSyncStatus(1);
                        preproduction.setSyncStatus2(1);
                        preproduction.setState(5);
                        preproduction.setSort(0);
                    }
                    preproductionService.insertPreproduction(preproduction);
                }
                /**重要 从redis队列里面删除此任务*/
                RedisDomainUtils.setRedisLineWaitQueueDomain(tc,true);
                logger.debug("从redis队列下发[{}]号主机楼成功；车号[{}],任务单号:[{}],当前车次[{}]------删除redis队列成功",line.getProductLine(),tc.getCarNo(),task.getId(),tc.getCarCnt());
                endTime = System.currentTimeMillis();
                logger.debug("\n\n插入任务执行了[{}]ms\n\n",endTime-startTime);
                break;
            case "1":
                logger.debug("主机楼三一重工逻辑");
                Product product = Product.getInstance();
                product.setMark(tc.getId()+"shajiang");
                product.setProjName(tc.getTaskName());
                if(tc.getShajiangfl()!=null&&tc.getShajiangfl()>0){
                    product.setProdMete(tc.getPlanFangliang().setScale(2, RoundingMode.HALF_UP).doubleValue()-tc.getShajiangfl());//本车生产方量
                }else{
                    product.setProdMete(tc.getPlanFangliang().setScale(2, RoundingMode.HALF_UP).doubleValue());//本车生产方量
                }
                productService.insertProduct(product);
                logger.debug("\n\n插入到三一重工主机楼生产指令任务执行了[{}]ms\n\n");
                break;
            case "2":
                logger.debug("主机楼中联重科逻辑");
                break;
            case "3" :
                logger.debug("主机楼遂工机械逻辑");
        }
        return true;
    }

    /**
     * 检查该条任务是否过期
     * */
    private boolean checkTaskcarTimeOut(TasksCars tc) {
        if(null!=tc.getDispatchTime()){
            //1.超时，2.没有确定 3.没有被删除
            if((System.currentTimeMillis()-tc.getDispatchTime())>Constants.TIME_TO_DROPTASK&&!tc.isSure()&&tc.getStatus()!=null&&tc.getStatus()!=Constants.DELETE_FLAG){
                //更新db
                Tasks dbt = tasksService.selectTasksById(tc.getTaskId().toString());
                if(null!=dbt){
                    logger.debug("超时，重置标志位timetotask=true");
//                    dbt.setCarCnt(dbt.getCarCnt()>0?dbt.getCarCnt()-1:0);
                    dbt.setTimeToTask(true);//重新派发该任务单
                    tasksService.updateTasks(dbt);
                }
                TasksCars dbtcar = tasksCarsService.selectTasksCarsById(tc.getId());
                if(null!=dbtcar){
                    dbtcar.setStatus(-1);//删除
                    dbtcar.setRemark("有任务没有点击确定而被删除掉了");
                    tasksCarsService.updateTasksCars(dbtcar);
                }
                //更改签到时间,排到下一轮
                List<Car> carlist = carService.selectCarList(new Car(){{setCarNo(tc.getCarNo());}});
                if(null!=carlist&&carlist.size()>0){
                    Car c = carlist.get(0);
                    c.setSignTime(new Date());
                    carService.updateCarApi(c);
                }
                //删除任务
                /**最重要的一个环节，放到队列中==删除*/
                RedisDomainUtils.setRedisLineWaitQueueDomain(tc,true);
                RuningCar runingCar = redisUtils.get(Constants.RUNINGCAR_PATTEN+tc.getNotifyId(),RuningCar.class);
                if(null!=runingCar&&runingCar.getCarId()!=null&&runingCar.getCarNo()!=null&&runingCar.getNotifyId()!=null){
                    runingCar.setTasking(false);
                    runingCar.setTaskStatus(Constants.TASK_STATUS_INIT);
                    redisUtils.set(Constants.RUNINGCAR_PATTEN+runingCar.getNotifyId(),runingCar,-1);
                }
                //更新任务到缓存
                /*Tasks task = redisUtils.get(Constants.TASK_PATTEN+tc.getTaskId(),Tasks.class);
                if(null!=task){
                    task.setCarCnt(task.getCarCnt()-1);
                    RedisDomainUtils.setRedisTaskDomain(task);
                }*/
                //更新db
                //重置redis
                RedisDomainUtils.setRedisTaskCarDomain(tc,true);
                logger.debug("[{}]号车的任务单[{}]没有确认，已被删除",tc.getCarNo(),tc.getTaskName());
                PushUtils.pushSendTasking("您的订单已失效",tc.getNotifyId(),null);
            }
        }
        return false;
    }


    /**检查主机是否有任务单号*/
    private boolean checkExistTask(String taskNumber,String line) {
        HashMap<String,String> para = new HashMap<>();
        para.put("TaskNumber",taskNumber);
        para.put("ProductLine",line);
        Task preTask= taskService.checkExistTask(para);
        if(null==preTask||(null==preTask.getSyncStatus()&&null==preTask.getSyncStatus2())){
            return false;
        }
        return true;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(setTaskExecutors());
    }
    @Bean
    public Executor setTaskExecutors(){
        return Executors.newScheduledThreadPool(3); // 3个线程来处理。
    }
}
