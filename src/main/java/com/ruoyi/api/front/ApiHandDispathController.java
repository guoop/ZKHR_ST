package com.ruoyi.api.front;


import com.ruoyi.api.ApiBangController;
import com.ruoyi.api.ApiTasksCarsController;
import com.ruoyi.api.PushUtils;
import com.ruoyi.api.RedisDomainUtils;
import com.ruoyi.api.domain.PrintDomain;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.duties.jobs.TaskJob;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.project.lines.productLine.domain.ProductLine;
import com.sun.jna.platform.win32.COM.RunningObjectTable;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.loadtime.Aj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/web")
public class ApiHandDispathController extends BaseController {
    @Autowired
    private ITasksService tasksService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private TaskJob taskJob;
    @Autowired
    private ICarService carService;
    @Autowired
    private ITasksCarsService tasksCarsService;
    @Autowired
    private ApiTasksCarsController apiTasksCarsController;
    @Autowired
    private ApiBangController apiBangController;
    private Logger logger = LoggerFactory.getLogger(ApiHandDispathController.class);

    /**获取可用车辆列表*/
    @RequestMapping("/freeCars")
    public AjaxResult freeCars(HttpServletRequest request,String taskId){
        Tasks tasks = redisUtils.get(Constants.TASK_PATTEN+taskId,Tasks.class);
        if(null==tasks){
            tasks = tasksService.selectTasksById(taskId);
        }
        if(null == tasks){
            return AjaxResult.error("任务单不存在");
        }
        if(null == tasks&& tasks.getStatus()==Constants.TASK_STATUS_COMPLATE){
            return AjaxResult.error("任务单已经结束");
        }
        //选择出来，所有的运行中的车辆信息
        //1. 选择出来
        List<RuningCar> list = redisUtils.getLists(Constants.RUNINGCAR_PATTEN,RuningCar.class);
        //如果没有值班车辆，就等下一次继续分配
        if(null==list){
            return AjaxResult.error("没有可用车辆");
        }
        //过滤执行中的车辆,匹配车辆类型
        list = taskJob.filterCars(list,tasks);
        if(null == list){
            return AjaxResult.error("没有可用车辆");
        }
        List<Car> allCarList = carService.selectCarList(null);
        return AjaxResult.success().put("avilibleCarlist",list).put("allCarList",allCarList).put("task",tasks);
    }


    /**
     * 手动派发
     * @param taskId 任务id
     * @param carId 车辆id
     * @param notifyId 通知id
     * @param shajiangfl 砂浆方量
     * */
    @RequestMapping("/handDispath")
    @RequiresPermissions("duties:tasks:handDispath")
    public AjaxResult handDispath(HttpServletRequest request, @RequestParam(required = true) String taskId,@RequestParam(required = true)  String carId,@RequestParam(required = true)  String notifyId,@RequestParam(required = true)  String fangliang,String shajiangfl){
        //砂浆方量 2019-07-18
        BigDecimal sjfl = new BigDecimal(0);
        if(StringUtils.isNotEmpty(shajiangfl)&&!"0".equalsIgnoreCase(shajiangfl)){
            sjfl = new BigDecimal(shajiangfl);
        }
        //砂浆方量 2019-07-18
        Tasks tasks = redisUtils.get(Constants.TASK_PATTEN+taskId,Tasks.class);
        if(null==tasks){
            tasks = tasksService.selectTasksById(taskId);
        }
        if(null==tasks){
            return AjaxResult.error("任务不存在");
        }
        DriverCar driverCar = redisUtils.get(Constants.DRVCAR_PATTEN+notifyId,DriverCar.class);
        Car car = redisUtils.get(Constants.CAR_PATTEN+driverCar.getCarId(),Car.class);
        if(null==car){
            return AjaxResult.error("未找到车辆");
        }
        if(null==driverCar){
            return AjaxResult.error("司机没有签到");
        }
        TasksCars converted = redisUtils.get(Constants.WAITE_QUEUE_PATTERN+tasks.getProductLine(),TasksCars.class);
        boolean flag = false;//插队标识
        //如果队里有车，并且车不是特权的就给删掉
        if(null!=converted&&!converted.isPrivilegeTask()){
            flag = true;
            //先把原来的删除掉,更改标志为为DEL标志为
            converted.setStatus(Constants.DELETE_FLAG);
            tasksCarsService.updateTasksCars(converted);
            RedisDomainUtils.setRedisLineWaitQueueDomain(converted,true);
        }else if(null!=converted){
            return AjaxResult.error("优先权车辆在排队，无法插队");
        }
        //开始分配车辆
        boolean isOk = taskJob.beginDispathCars(tasks,true,driverCar.getNotifyId(),"调度员手工派单",true,flag,sjfl);
        if(isOk){
            return AjaxResult.success();
        }
        return AjaxResult.error();

    }
    @RequestMapping("/cancelTask")
    @RequiresPermissions("duties:tasks:cancelTask")
    public AjaxResult cancelTask(HttpServletRequest request,String taskId){
        Tasks tasks = redisUtils.get(Constants.TASK_PATTEN+taskId,Tasks.class);
        if(tasks==null){
            tasks = tasksService.selectTasksById(taskId);
        }
        tasks.setUpdateBy(this.getSysUser().getUserName());
        tasks.setStatus(4);//任务单取消标志
        tasksService.updateTasks(tasks);
        return AjaxResult.success();
    }

    @RequestMapping("/printInfo")
    @RequiresPermissions("duties:tasks:printinfo")
    public AjaxResult printInfo(HttpServletRequest request,String taskCarId){
        TasksCars tasksCars = tasksCarsService.selectTasksCarsById(Long.valueOf(taskCarId));
        if(null!=taskCarId){
            PrintDomain data = apiBangController.generatePrintDomain(tasksCars,null);
            return AjaxResult.success().put("data",data);
        }
        return AjaxResult.error();
    }
}
