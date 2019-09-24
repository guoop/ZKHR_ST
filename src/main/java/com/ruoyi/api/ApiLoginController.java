package com.ruoyi.api;


import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.msg.SmsUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.Jpush.JPushService;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.cars.driver.domain.Driver;
import com.ruoyi.project.cars.driver.service.IDriverService;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cars.driverCar.service.IDriverCarService;
import com.ruoyi.project.cars.driverloginLog.domain.DriverloginLog;
import com.ruoyi.project.cars.driverloginLog.service.IDriverloginLogService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.project.redisEntry.CarsLonLat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.scanner.Constant;

import javax.servlet.http.HttpServletRequest;
import java.io.DataOutput;
import java.time.LocalDate;
import java.util.*;

@RequestMapping("/api")
@RestController
public class ApiLoginController {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IDriverCarService driverCarService;
    @Autowired
    private ITasksCarsService tasksCarsService;
    @Autowired
    private JPushService jPushService;
    @Autowired
    private ICarService carService;
    @Autowired
    private IDriverService driverService;
    @Autowired
    private IDriverloginLogService driverloginLogService;
    private Logger logger = LoggerFactory.getLogger(ApiLoginController.class);
    /**
     * app登陆接口
     * */
    @RequestMapping("/login")
    public AjaxResult login(HttpServletRequest request, @RequestParam(required = true)  String mobile, @RequestParam(required = true) String code){
        String bcode = redisUtils.get(Constants.MOBILE_CODE+mobile);
        if(StringUtils.isBlank(bcode)||!bcode.equalsIgnoreCase(bcode)){
            return AjaxResult.error("验证码错误");
        }
        List<Car> carList = redisUtils.getLists(Constants.CAR_PATTEN,Car.class);
        if(null==carList||carList.size()==0){
            carList = carService.selectCarList(null);
        }
        List<Driver> drivers = redisUtils.getLists(Constants.DRIVER_PATTERN,Driver.class);
        if(null==drivers||drivers.size()==0){
            drivers = driverService.selectDriverList(null);
        }
        //司机和车主能登陆，如果既是车主又是司机就以司机的身份登陆
        for (Driver driver : drivers) {
            if(driver.getDMobile().equalsIgnoreCase(mobile)){
                String token = UUID.randomUUID().toString();
                redisUtils.set(Constants.APP_TOKEN+token,driver,-1);
//                PushUtils.pushSendTasking("您好，调度在召唤您，站内任务单较多，请您尽快赶到博力商砼站",mobile);
                return AjaxResult.success().put("token",token);
            }
        }
        for (Car car:carList){
            if(car.getOwnerPhone().equalsIgnoreCase(mobile)){
                String token = UUID.randomUUID().toString();
                redisUtils.set(Constants.APP_TOKEN+token,car,-1);
                return AjaxResult.success().put("token",token);
            }
        }
        return AjaxResult.error("请先到调度室登记车辆及司机信息");
    }
    /**
     * app签到接口
     * */
    @RequestMapping("/signIn")
    public AjaxResult signIn(HttpServletRequest request,@RequestParam(required = true)  String carNo, @RequestParam(required = true)  String lat, @RequestParam(required = true)  String lng){
        String mobile ="";
        Driver driver = redisUtils.getCurrentDriver();
        if(driver!=null){
            mobile = driver.getDMobile();
            if(StringUtils.isEmpty(mobile)){
                return AjaxResult.error("没有发现司机信息");
            }
        }else{
            Car car = redisUtils.getCurrentOwner();
            if(car!=null){
                return AjaxResult.error("车主不能签到");
            }
        }
        String carnos = redisUtils.getTodayCarNos();
        if(null==carnos){
            return AjaxResult.error("调度还没有添加今日应上班车辆");
        }
        if(!(","+carnos+",").contains(","+carNo+",")){
            return AjaxResult.error("你不在主班车队，请联系调度");
        }
        /*String bcode = redisUtils.get(Constants.MOBILE_CODE+mobile);
        if(StringUtils.isBlank(bcode)||!bcode.equalsIgnoreCase(bcode)){
            return AjaxResult.error("验证码错误");
        }*/
        RuningCar carsLonLat = new RuningCar();
        carsLonLat.setLat(Double.valueOf(lat));
        carsLonLat.setLng(Double.valueOf(lng));
        carsLonLat.setCarNo(carNo);
        logger.debug("签到坐标:{},{}",lat,lng);
        Boolean isDemo = Boolean.valueOf(Global.getConfig("project.demo"));
        if(!carsLonLat.isInner()&&!isDemo){
            return AjaxResult.error("不在签到范围内");
        }
        DriverCar dcr = new DriverCar();
        dcr.setDriverMobile(mobile);
        dcr.setCarNo(Integer.valueOf(carNo));
        dcr.setSqlWhere(" order by create_time desc limit 1");
        List<DriverCar> list = driverCarService.selectDriverCarList(dcr);
        if(null==list||list.size()==0){
            return AjaxResult.error("无此车辆信息,请先注册司机信息");
        }
        DriverCar driverCar = list.get(0);
        //车辆是否被占用
        Car carRun = redisUtils.get(Constants.CAR_PATTEN+driverCar.getCarId(),Car.class);
        if(null==carRun){
            carRun = carService.selectCarById(driverCar.getCarId());
            RedisDomainUtils.setRedisCarDomain(carRun);
        }
        if(null!=carRun){
            String notifyId = redisUtils.get(Constants.CARUSING_PATTEN+carRun.getId());
            DriverCar dc = redisUtils.get(Constants.DRVCAR_PATTEN+notifyId,DriverCar.class);
            if(null!=dc&&!dc.getNotifyId().equalsIgnoreCase(driverCar.getNotifyId())){
                return AjaxResult.error("此车已经被"+dc.getDriverMobile()+"使用。").put("mobile",dc.getDriverMobile());
            }
        }
        carRun.setTasking(true);
        RuningCar rcar = redisUtils.get(Constants.RUNINGCAR_PATTEN+driverCar.getNotifyId(),RuningCar.class);
        if(rcar == null){
            rcar = new RuningCar();
        }
        rcar.setLng(Double.valueOf(lng));
        rcar.setLat(Double.valueOf(lat));
        rcar.setCarId(driverCar.getCarId()+"");
        rcar.setCarNo(driverCar.getCarNo()+"");
        rcar.setNotifyId(driverCar.getNotifyId());
        setIsTasking(rcar);
        //维护记录
        maintainSingLog(rcar,driverCar,"IN");
        /*取出已经签到的人员列表*/
        List<RuningCar> runingList = redisUtils.getLists(Constants.RUNINGCAR_PATTEN,RuningCar.class);
        carRun.setSignTime(new Date());
        carService.updateCarApi(carRun);
        RedisDomainUtils.setRedisCarDomain(carRun);
//        redisUtils.set(Constants.CAR_PATTEN+driverCar.getCarId(),carRun);
        //设置车辆正在使用中
        redisUtils.set(Constants.CARUSING_PATTEN+driverCar.getCarId(),driverCar.getNotifyId());
        //设置正在使用中的车辆的缓存
        redisUtils.set(Constants.RUNINGCAR_PATTEN+rcar.getNotifyId(),rcar);
        String token = UUID.randomUUID().toString();
        redisUtils.set(Constants.APP_TOKEN+token,driverCar,-1);
        return AjaxResult.success().put("token",token).put("notifyId",driverCar.getNotifyId());
        /*签到之后runningcars里就会有此车辆的信息*/
    }

    private void maintainSingLog(RuningCar runingCar,DriverCar driverCar,String type) {
        Boolean isDemo = Boolean.valueOf(Global.getConfig("project.demo"));//演示系统
        try{
            if(null!=runingCar&&null!=runingCar.getCarId()&&null!=runingCar.getCarNo()){
                DriverloginLog driverloginLog = new DriverloginLog();
                driverloginLog.setDriverMobile(driverCar.getDriverMobile());
                driverloginLog.setLat(runingCar.getLat()+"");
                driverloginLog.setLng(runingCar.getLng()+"");
                if(type.equalsIgnoreCase("IN")){
                    driverloginLog.setRemark("站内签到");
                }else{
                    if(runingCar.isInner()&&!isDemo){
                        driverloginLog.setRemark("站内签退");
                    }else{
                        driverloginLog.setRemark("站外签退");
                    }
                }
                driverloginLogService.insertDriverloginLog(driverloginLog);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


    /**
     * app签退接口
     * */
    @RequestMapping("/signOut")
    public AjaxResult logout(HttpServletRequest request){
        DriverCar drcar = redisUtils.getLoginDriverCar();
        TasksCars tcar = redisUtils.get(Constants.TASKINGCAR_PATTEN+drcar.getNotifyId(),TasksCars.class);
        if(null!=tcar&&null!=tcar.getStatus()&&tcar.getStatus()==Constants.TASK_STATUS_ING){
            logger.debug("有任务在身，不能签退");
            return AjaxResult.error("有任务在身，不能签退");
        }
        if(null==drcar){
            logger.debug("drivercar为空，默认退出成功");
            return AjaxResult.success("登出成功");
        }

        RuningCar runingCar = redisUtils.get(Constants.RUNINGCAR_PATTEN+drcar.getNotifyId(),RuningCar.class);
        if(null==runingCar){
            logger.debug("runingCar==null，登出成功");
            return AjaxResult.error("登出成功").put("code",-100);
        }
        //维护记录
        maintainSingLog(runingCar,drcar,"OUT");
        /*登出之后从redis缓存里面释放*/
        redisUtils.delete(Constants.RUNINGCAR_PATTEN+drcar.getNotifyId());
        //重新设置正在上班的车辆信息
        redisUtils.delete(Constants.CARUSING_PATTEN+drcar.getCarId());
        logger.debug("一切正常，登出成功");
        return AjaxResult.success("登出成功");
    }

    /**
     * 发送短信接口
     * */
    @PostMapping("/login/sendSMS")
    public AjaxResult sendMsg(HttpServletRequest request, @RequestParam(required = true) String mobile){
        String code = SmsUtils.getRandomLong()+"";
        try{
            String data = SmsUtils.send(code,mobile);
        }catch (Exception ex){
            ex.printStackTrace();
            return AjaxResult.error("发送验证码失败");
        }
        List<DriverCar> list = driverCarService.selectDriverCarList(new DriverCar(){{setDriverMobile(mobile);}});
        redisUtils.set(Constants.MOBILE_CODE+mobile,code,60*5);
        return AjaxResult.success().put("data","发送验证码成功").put("carList",list);
    }



    private void setIsTasking(RuningCar runingCar) {
        if(redisUtils.exists(Constants.TASKINGCAR_PATTEN,runingCar.getNotifyId())){
            TasksCars tc = redisUtils.get(Constants.TASKINGCAR_PATTEN+runingCar.getNotifyId(), TasksCars.class);
            if(null!=tc&&tc.getStatus() == Constants.TASK_STATUS_ING){
                runingCar.setTaskId(tc.getTaskId());
                runingCar.setCarNo(tc.getCarNo()+"");
                runingCar.setTasking(true);
                logger.debug("更改了taskstatus:方法【{}】","setIsTasking");
                runingCar.setTaskStatus(Constants.TASK_STATUS_ING);
            }else{
                List<TasksCars> list = tasksCarsService.selectTasksCarsList(new TasksCars(){{setNotifyId(runingCar.getNotifyId());setStatus(Constants.TASK_STATUS_ING);}});
                if(null!=list&&list.size()>0){
                    TasksCars tasksCars = list.get(0);
                    if(null!=tasksCars){
                        runingCar.setTaskId(tasksCars.getTaskId());
                        runingCar.setCarNo(tasksCars.getCarNo()+"");
                        logger.debug("更改了taskstatus:方法【{}】","setIsTasking");
                        runingCar.setTasking(true);
                        runingCar.setTaskStatus(Constants.TASK_STATUS_ING);
                    }
                }else{
                    runingCar.setTaskStatus(Constants.TASK_STATUS_INIT);
                }
            }
        }
    }

}
