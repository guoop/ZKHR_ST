package com.ruoyi.project.duties.doweight;

import com.ruoyi.api.ApiBangController;
import com.ruoyi.api.MySeq;
import com.ruoyi.api.PushUtils;
import com.ruoyi.api.RedisDomainUtils;
import com.ruoyi.api.domain.PrintDomain;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.cars.comstrength.domain.Comstrength;
import com.ruoyi.project.cars.comstrength.service.IComstrengthService;
import com.ruoyi.project.cars.driver.domain.Driver;
import com.ruoyi.project.cars.driver.service.IDriverService;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cemslink.preproduction.domain.Preproduction;
import com.ruoyi.project.cemslink.preproduction.service.IPreproductionService;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.project.stock.receiveCar.domain.ReceiveCar;
import com.ruoyi.project.stock.receiveCar.service.IReceiveCarService;
import com.ruoyi.project.stock.receiveGoodsLevel.domain.ReceiveGoodsLevel;
import com.ruoyi.project.stock.receiveGoodsLevel.service.IReceiveGoodsLevelService;
import com.ruoyi.project.stock.receiveOffer.domain.ReceiveOffer;
import com.ruoyi.project.stock.receiveOffer.service.IReceiveOfferService;
import com.ruoyi.project.stock.receiveOffice.domain.ReceiveOffice;
import com.ruoyi.project.stock.receiveOffice.service.IReceiveOfficeService;
import com.ruoyi.project.stock.receivePeople.domain.ReceivePeople;
import com.ruoyi.project.stock.receivePeople.service.IReceivePeopleService;
import com.ruoyi.project.stock.receivePlace.domain.ReceivePlace;
import com.ruoyi.project.stock.receivePlace.service.IReceivePlaceService;
import com.ruoyi.project.stock.receiveRemark.domain.ReceiveRemark;
import com.ruoyi.project.stock.receiveRemark.service.IReceiveRemarkService;
import com.ruoyi.project.stock.receiveStock.domain.ReceiveStock;
import com.ruoyi.project.stock.receiveStock.service.IReceiveStockService;
import io.swagger.models.auth.In;
import net.bytebuddy.asm.Advice;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.constant.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/duties/doweight")
public class DoweightController extends BaseController {
    private String prefix = "duties/doweight";

    @Autowired
    private ITasksCarsService tasksCarsService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ITasksService tasksService;
    @Autowired
    private ICarService carService;
    @Autowired
    private IDriverService driverService;
    private BigDecimal equalRate = new BigDecimal(Global.getConfig("project.equalRate"));
    @Autowired
    private IPreproductionService preproductionService;
    @Autowired
    private ApiBangController apiBangController;
    @Autowired
    private IComstrengthService comstrengthService;
    @Autowired
    private IReceiveStockService receiveStockService;
    /**收货表*/
    //送货车辆
    @Autowired
    private IReceiveCarService receiveCarService;
    //商品列表
    @Autowired
    private IReceiveGoodsLevelService receiveGoodsLevelService;
    @Autowired
    private IReceiveOfficeService receiveOfficeService;
    @Autowired
    private IReceivePeopleService receivePeopleService;
    @Autowired
    private IReceiveRemarkService receiveRemarkService;
    @Autowired
    private IReceivePlaceService receivePlaceService;
    @Autowired
    private IReceiveOfferService receiveOfferService;


    private Logger logger = LoggerFactory.getLogger(DoweightController.class);
    @RequiresPermissions("duties:tasksCars:view")
    @GetMapping
    public String doweight(ModelMap mmp)
    {
        //车号列表
        List<Car> carList = redisUtils.getLists(Constants.CAR_PATTEN,Car.class);
        mmp.put("carList",carList);
        return prefix + "/doweight";
    }
    /**
     * 称重接口
     * */
    @GetMapping("/getWeight/{bangNo}")
    @ResponseBody
    public AjaxResult getWeight(@PathVariable String bangNo,String carNo){
        if(null==redisUtils.get(Constants.BANG_NO+bangNo)){
            return AjaxResult.error("无此磅数据，请检查地磅是否联网");
        }
        BigDecimal weight = new BigDecimal(redisUtils.get(Constants.BANG_NO+bangNo));
        return AjaxResult.success().put("weight",weight);
    }


    /**
     * 操作类型
     * @param carNo 车号
     * @param type  操作类型  CAR_WEIGHT: 存皮;GROSS_WIEGHT:总重；OUT_WEIGHT:出站称重
     * @param weight 重量值
     * */
    @PostMapping("/doMethod/{carNo}/{type}/{weight}")
    @ResponseBody
    public AjaxResult doMethod(@PathVariable String carNo,@PathVariable String type,@PathVariable String weight){
        //存皮
        if(type.equalsIgnoreCase("CAR_WEIGHT")){
            List<Car> carList = carService.selectCarList(new Car(){{setCarNo(Integer.valueOf(carNo));}});
            if(null==carList||carList.size()==0){
                return AjaxResult.error("该车辆没有在ERP系统登记");
            }
            Car car = carList.get(0);
            car.setCarWeight(new BigDecimal(weight));
            carService.updateCarWeight(car);
            RedisDomainUtils.setRedisCarDomain(car);
            return AjaxResult.success().put("weight",weight);
        }else if(type.equalsIgnoreCase("OUT_WEIGHT")){
            //出站
            List<RuningCar> runingCars = redisUtils.getLists(Constants.RUNINGCAR_PATTEN, RuningCar.class);
            RuningCar runcar = null;
            for (RuningCar run:runingCars) {
                if(null==run||run.getCarId()==null||run.getCarNo()==null){
                    continue;
                }
                if(run.getCarNo().equalsIgnoreCase(carNo)){
                    runcar = run;
                }
            }
            if(runcar==null){
                return AjaxResult.error("该车未登陆");
            }
            TasksCars tasksCars = redisUtils.get(Constants.TASKINGCAR_PATTEN+runcar.getNotifyId(),TasksCars.class);
            if(null==tasksCars||(null!=tasksCars.getStatus()&&tasksCars.getStatus()!=Constants.TASK_STATUS_ING)){
                return AjaxResult.error("该车没有任务");
            }
            if(null!=tasksCars.getStationStatus()&&tasksCars.getStationStatus().equalsIgnoreCase("OUT")&&tasksCars.getStatus()==Constants.TASK_STATUS_ING){
                return AjaxResult.error("该车已出站，请勿重复操作");
            }
            return outSite(tasksCars,runcar,carNo,new BigDecimal(weight));
        }
        return AjaxResult.error();
    }
    /**
     * 根据车号获取任务单信息
     * */
    @PostMapping("/taskInfo/{carNo}")
    @ResponseBody
    public AjaxResult taskInfo(@PathVariable("carNo") Long carNo){
        TasksCars tk= new TasksCars();
        List<TasksCars> list = tasksCarsService.selectTasksCarsList(new TasksCars(){{
            setCarNo(carNo.intValue());
            setStatus(1);
        }});
        if(list!=null&&list.size()>1){
            return AjaxResult.error("该车异常，存在没有完成的任务,请先在派单记录里结束一单");
        }else if(list!=null&&list.size()==1){
            tk = list.get(0);
        }else{
            List<Car> clist = carService.selectCarList(new Car(){{
                setCarNo(carNo.intValue());
            }});
            if(null!=clist&&clist.size()>0){
                tk.setCarWeight(clist.get(0).getCarWeight());
            }
        }
        return AjaxResult.success().put("taskInfo",tk);
    }

    /**
     * 出站逻辑
     * */
    public AjaxResult outSite(TasksCars tasksCars,RuningCar runcar,String carNo,BigDecimal weight){
        if(!tasksCars.isSure()){
            return AjaxResult.error("司机没有接单,不能出站");
        }
        //出门逻辑
//        if(null == tasksCars.getCarWeight()){
        Car car = redisUtils.get(Constants.CAR_PATTEN+tasksCars.getCarId(),Car.class);
        if(null!=car&&null!=car.getCarWeight()){
            tasksCars.setCarWeight(car.getCarWeight());
        }else{
            throw new BaseException("请先进站称皮重");
        }
//        }
        //毛重
        tasksCars.setGrossWeight(weight);
        tasksCars.setStationStatus("OUT");
        tasksCars.setIsStart(Constants.YES);
        tasksCars.setEnd(Constants.NO);
        tasksCars.setStartTime(new Date());
        tasksCars.setNetWeight(new BigDecimal(Math.abs(tasksCars.getGrossWeight().subtract(tasksCars.getCarWeight()).setScale(2, RoundingMode.HALF_UP).doubleValue())));
        if(tasksCars.getGrossWeight().compareTo(tasksCars.getCarWeight())<=0){
            return AjaxResult.error("总重量不能小于皮重");
        }
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
        Driver driver = redisUtils.get(Constants.DRIVER_PATTERN+tasksCars.getDriverMobile(),Driver.class);
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
        apiBangController.setOutRunningState(tasksCars);
        String content = "当前任务目的地:"+task.getName()+",业务经理手机号:"+task.getOfficerMobile()+"收货人"+task.getReceiver()+";收货人手机号"+task.getReceivorMobile()+"施工部位"+task.getWaterPart();
        logger.debug(content);
        PushUtils.pushSendTasking(content,tasksCars.getNotifyId(),new HashMap());
        RedisDomainUtils.setRedisDriverDomain(driver);
        PrintDomain printDomain = apiBangController.generatePrintDomain(tasksCars,task);
        Boolean isDemo = Boolean.valueOf(Global.getConfig("project.demo"));
        if(isDemo){
            try{
                Preproduction pram = new Preproduction();
                pram.setTransportOrder(tasksCars.getCarCnt());
                pram.setTaskNumber(tasksCars.getTaskNumber());
                pram.setProductLine(tasksCars.getProductLine());
                List<Preproduction> preproductionList = preproductionService.selectPreproductionList(pram);
                if(null!=preproductionList&&preproductionList.size()>0){
                    Preproduction dbp = preproductionList.get(0);
                    dbp.setState(5);
                    dbp.setSort(0);
                    preproductionService.updatePreproduction(dbp);
                }
            }catch (Exception ex){
                logger.debug(ex.getMessage());
            }
        }
        return AjaxResult.success().put("printDomain",printDomain);
    }

}
