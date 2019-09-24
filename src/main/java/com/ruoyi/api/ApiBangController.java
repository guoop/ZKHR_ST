package com.ruoyi.api;


import com.ruoyi.api.domain.PrintDomain;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.msg.SmsUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.cars.driver.domain.Driver;
import com.ruoyi.project.cars.driver.service.IDriverService;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cars.driverCar.service.IDriverCarService;
import com.ruoyi.project.cemslink.task.domain.Task;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.project.redisEntry.CarsLonLat;
import org.aspectj.weaver.loadtime.Aj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.scanner.Constant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@RequestMapping("/api")
@RestController
public class ApiBangController {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IDriverCarService driverCarService;
    @Autowired
    private ITasksCarsService tasksCarsService;
    @Autowired
    private ITasksService tasksService;
    private BigDecimal equalRate = new BigDecimal(Global.getConfig("project.equalRate"));
    @Autowired
    private ICarService carService;
    @Autowired
    private IDriverService driverService;
    private Logger logger = LoggerFactory.getLogger(ApiBangController.class);

    @RequestMapping("/getDoorWeight/{doorNo}")
    @Transactional(transactionManager = "masterTransactionManager")
    public AjaxResult getDoorWeight(HttpServletRequest request,@PathVariable String doorNo){
        RuningCar runingCar = redisUtils.get(Constants.RUNINGCAR_PATTEN+redisUtils.getLoginDriverCar().getNotifyId(),RuningCar.class);
        if(null==runingCar){
            return AjaxResult.error("未登录");
        }
        Boolean isDemo = Boolean.valueOf(Global.getConfig("project.demo"));//演示系统
        if(!runingCar.isInner()&&!isDemo){
            return AjaxResult.error("请行驶到地磅在进行称重操作");
        }
        if(null==redisUtils.get(Constants.BANG_NO+doorNo)){
            return AjaxResult.error("无地磅数据,请联系磅房检查地磅系统是否联网");
        }
        BigDecimal weight = new BigDecimal(redisUtils.get(Constants.BANG_NO+doorNo));

        return AjaxResult.success().put("data",weight.doubleValue());
    }



    /**
     * app登陆接口
     * @param doorNo: S:南磅   N:北磅
     * @param type  IN: 进门   OUT: 出门
     * */
    @RequestMapping("/setWeight/{doorNo}/{type}/{carNo}")
    @Transactional(transactionManager = "masterTransactionManager")
    public AjaxResult setWeight(HttpServletRequest request,@PathVariable String doorNo,@PathVariable String type,@PathVariable String carNo,String weight){
        RuningCar runingCar = redisUtils.get(Constants.RUNINGCAR_PATTEN+redisUtils.getLoginDriverCar().getNotifyId(),RuningCar.class);
        DriverCar user = redisUtils.getLoginDriverCar();
        Driver driver = redisUtils.get(Constants.DRIVER_PATTERN+user.getDriverMobile(),Driver.class);
        if(!runingCar.getCarNo().equals(carNo)){
            return AjaxResult.error("车号不对");
        }
        if(null==runingCar){
            return AjaxResult.error("未登录");
        }
        Boolean isDemo = Boolean.valueOf(Global.getConfig("project.demo"));//演示系统
        if(!runingCar.isInner()&&!isDemo){
            return AjaxResult.error("请行驶到地磅在进行称重操作");
        }
        String notifyId = user.getNotifyId();
        Car car = redisUtils.get(Constants.CAR_PATTEN+user.getCarId(),Car.class);
        if(car == null){
            car = carService.selectCarById(user.getCarId());
        }
        TasksCars tasksCars = redisUtils.get(Constants.TASKINGCAR_PATTEN+notifyId,TasksCars.class);
        /**进门逻辑*/
        if(type.equalsIgnoreCase("IN")){
            /**1.如果是任务完成回来的时候*/
            if(null!=tasksCars){
                //没有任务
                if(tasksCars.getStatus()!=null&&(tasksCars.getStatus()==Constants.TASK_STATUS_COMPLATE||tasksCars.getStatus()==Constants.TASK_STATUS_INIT)){
                    if(null!=car){
                        car.setCarWeight(new BigDecimal(weight));
                        carService.updateCarApi(car);
                    }
                    return AjaxResult.success();
                }

                /**2.有任务的进站-称毛重*/
                if(null!=tasksCars.getStationStatus()&&tasksCars.getStationStatus().equalsIgnoreCase("IN")){
                    return AjaxResult.error("请勿重复点击");
                }
                if(null!=tasksCars.getStationStatus()&&tasksCars.getStationStatus().equalsIgnoreCase("OUT")&&tasksCars.getStatus() == Constants.TASK_STATUS_ING){
                    return AjaxResult.error("您已出站，请勿重复点击");
                }
                tasksCars.setCarWeight(new BigDecimal(weight));
                tasksCars.setStationStatus("IN");
                tasksCars.setTaskName(tasksCars.getTaskName());
                tasksCarsService.updateTasksCars(tasksCars);
                RedisDomainUtils.setRedisTaskCarDomain(tasksCars);//更新redis
            }else{
                //没有任务进站的情况，更新车辆的皮重
                if(null!=car){
                    car.setCarWeight(new BigDecimal(weight));
                    carService.updateCarApi(car);
                }
                return AjaxResult.success();
            }
        }else if(type.equalsIgnoreCase("OUT")){
            if(null==tasksCars||tasksCars.getStatus()!=Constants.TASK_STATUS_ING){
                return AjaxResult.error("您还没有任务");
            }
            if(tasksCars.getStatus() == Constants.TASK_STATUS_ING&&null!=tasksCars.getStationStatus()&&tasksCars.getStationStatus().equals("OUT")){
                return AjaxResult.error("请勿重复点击");
            }
            //如果进站没有称重
            if(null == tasksCars.getCarWeight()){
                if(null!=car&&null!=car.getCarWeight()){
                    tasksCars.setCarWeight(car.getCarWeight());
                }else{
                    throw new BaseException("请先进站称重");
                }
            }
            String weightWg = redisUtils.get(Constants.BANG_NO+doorNo);
            if(StringUtils.isBlank(weightWg)){
                return AjaxResult.error("无地磅数据,请联系磅房检查地磅系统是否联网");
            }
            BigDecimal wgt = new BigDecimal(redisUtils.get(Constants.BANG_NO+doorNo));
            //毛重
            tasksCars.setGrossWeight(wgt);
            tasksCars.setStationStatus("OUT");
            tasksCars.setIsStart(Constants.YES);
            tasksCars.setEnd(Constants.NO);
            tasksCars.setStartTime(new Date());
            tasksCars.setNetWeight(new BigDecimal(Math.abs(tasksCars.getGrossWeight().subtract(tasksCars.getCarWeight()).setScale(2, RoundingMode.HALF_UP).doubleValue())));
            tasksCars.setEqualFangliang(tasksCars.getNetWeight().multiply(equalRate).setScale(2,RoundingMode.HALF_UP));

            //获取任务，更新累计方量
            Tasks task = redisUtils.get(Constants.TASK_PATTEN+tasksCars.getTaskId(),Tasks.class);
            if(null == task){
                task = tasksService.selectTasksById(tasksCars.getTaskId()+"");
            }
            task.setLastCarTime(new Date());
            tasksCars.setTaskName(task.getName());
            BigDecimal ljfl = task.getLjfangliang()==null?new BigDecimal(0):task.getLjfangliang();
            BigDecimal zhfl = tasksCars.getEqualFangliang()==null? new BigDecimal(0):tasksCars.getEqualFangliang();
            task.setLjfangliang(ljfl.add(zhfl).setScale(2,RoundingMode.HALF_UP));
            tasksCars.setLjfangliang(task.getLjfangliang());
            //更新优先权次数
            if(task.getPrivilege()>0){
                driver.setPrivilegeCnt(driver.getPrivilegeCnt()+task.getPrivilege());
                driverService.updateDriver(driver);
            }
            //更新执行中的任务
            tasksCarsService.updateTasksCars(tasksCars);
            tasksService.updateTasks(task);
            RedisDomainUtils.setRedisTaskCarDomain(tasksCars);//更新redis
            RedisDomainUtils.setRedisTaskDomain(task);
            //设置运行中的车辆状态
            setOutRunningState(tasksCars);
            String content = "当前任务目的地:"+task.getName()+",业务经理手机号:"+task.getOfficerMobile()+"收货人"+task.getReceiver()+";收货人手机号"+task.getReceivorMobile()+"施工部位"+task.getWaterPart();
            logger.debug(content);
            PushUtils.pushSendTasking(content,tasksCars.getNotifyId(),new HashMap<>());
            RedisDomainUtils.setRedisDriverDomain(driver);
            PrintDomain printDomain = generatePrintDomain(tasksCars,task);
            if(null!=printDomain){
                RedisDomainUtils.setRedisPrintDomain(printDomain);
            }
        }
        /**出门逻辑*/
       return AjaxResult.success();
    }


    public PrintDomain generatePrintDomain(TasksCars tasksCars, Tasks tasks){
        if(null==tasks){
            tasks = tasksService.selectTasksById(tasksCars.getTaskId()+"");
            if(tasks==null){
                return null;
            }
        }
        PrintDomain print = new PrintDomain();
        print.setTaskCarId(tasksCars.getId());
        print.setAccumulatedVolume(tasksCars.getLjfangliang());
        print.setDateTime(new Date());
        print.setEngineeringName(tasks.getName());
        print.setConsignee(null);
        print.setLicenseNumber(tasksCars.getCarNo());//车号
        print.setOrderNo(tasksCars.getTaskNumber());//序号
        print.setUnitPrice(tasks.getPrice());//单价
        print.setTrainNumber(tasksCars.getCarCnt());//车次
        print.setTare(tasksCars.getCarWeight());//皮重
        print.setStdWeight(tasksCars.getNetWeight());//净重
        print.setGrossWeight(tasksCars.getGrossWeight());//毛重
        print.setStrengthGrade(tasks.getProductKind());//强度等级
        print.setSquareQuantity(tasksCars.getEqualFangliang());//本次方量
        print.setRemarks(tasks.getIsCarMoney().equalsIgnoreCase(Constants.YES)?"车带款":"");//备注
        print.setReceivingCompany(tasks.getReceiver());//收货人
        print.setTelephone(tasks.getReceivorMobile());//收货人电话
        print.setPouringSite(tasks.getWaterPart());//浇筑部位
        print.setPouringMethod(tasks.getWaterMethod());//浇筑方式
        print.setServiceManager(tasks.getCreateBy());//业务经理
        print.setManagerTelephone(tasks.getOfficerMobile());//业务经理电话
        return print;
    }

    /**
     * 设置运行中的车辆状态
     * */
    public void setOutRunningState(TasksCars tasksCars){
        RuningCar runingCar = redisUtils.get(Constants.RUNINGCAR_PATTEN+tasksCars.getNotifyId(),RuningCar.class);
        if(runingCar!=null){
            runingCar.setStart(true);
            runingCar.setTaskStatus(Constants.TASK_STATUS_ING);
            redisUtils.set(Constants.RUNINGCAR_PATTEN+tasksCars.getNotifyId(),runingCar,-1);
        }
    }
}
