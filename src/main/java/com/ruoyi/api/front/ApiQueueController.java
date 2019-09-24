package com.ruoyi.api.front;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.google.gson.JsonObject;
import com.ruoyi.api.ApiTasksCarsController;
import com.ruoyi.api.PushUtils;
import com.ruoyi.api.RedisDomainUtils;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.cars.driver.domain.Driver;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cars.driverCar.service.IDriverCarService;
import com.ruoyi.project.cemslink.preproduction.domain.Preproduction;
import com.ruoyi.project.cemslink.preproduction.service.IPreproductionService;
import com.ruoyi.project.duties.jobs.TaskJob;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.project.lines.productLine.domain.ProductLine;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.CachedRowSet;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/web")
public class ApiQueueController extends BaseController {
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
    private IPreproductionService preproductionService;
    @Autowired
    private ApiTasksCarsController apiTasksCarsController;
    @Autowired
    private IDriverCarService driverCarService;

    private Logger logger = LoggerFactory.getLogger(ApiQueueController.class);

    /**获取可用车辆列表*/
    @RequestMapping("/getCarList")
    public AjaxResult masterCarList(HttpServletRequest request,String taskId){
        return AjaxResult.success().put("mastList",masterList()).put("slaveList",slaveList());
    }

    /**
     * 根据类型判断车辆是主班还是副班
     * */
    private List<Car> masterList(){
        List<Car> retList = new ArrayList<>();
        List<Car> carList = redisUtils.getLists(Constants.CAR_PATTEN,Car.class);
        redisUtils.delete(Constants.RUNINGCAR_PATTEN+"null");
        List<RuningCar> runingCars = redisUtils.getLists(Constants.RUNINGCAR_PATTEN,RuningCar.class);
//        ListIterator<RuningCar> listIterator = runingCars.listIterator();
//        ListIterator<Car> carIterator = carList.listIterator();
        for (Car car:carList) {
            for (RuningCar rncar:runingCars) {
                if(null==rncar){
                    continue;
                }
//                logger.debug("车辆的id是[{}],runingcar的carid是:[{}]",car.getId(),rncar.getCarId());
                if(null!=car&&null!=rncar&&car.getId().equals(Long.valueOf(rncar.getCarId()))){
                    retList.add(car);
                }
            }
        }
        return retList;
    }

    /**
     * 根据类型判断车辆是主班还是副班
     * */
    private List<Car> slaveList(){
        List<Car> retList = new ArrayList<>();
        List<Car> carList = redisUtils.getLists(Constants.CAR_PATTEN,Car.class);
        List<RuningCar> runingCarList = redisUtils.getLists(Constants.RUNINGCAR_PATTEN,RuningCar.class);
        List<String> collect  = runingCarList.stream().map(x->x.getCarId()).collect(Collectors.toList());
        String str_collect = String.join(",",collect);
        str_collect=","+str_collect+",";
        for (Car car:carList) {
            String s = ","+car.getId()+",";
            if(!str_collect.contains(s)){
                retList.add(car);
            }
        }
        return retList;
    }



    /**计划用车数量*/
    @RequestMapping("/plancaingList")
    public AjaxResult plancaingList(HttpServletRequest request){
        List<Tasks> tasksList = tasksService.selectTasksList(new Tasks(){{setSqlWhere(" and status = 1 and plan_end_time > now() ");}});
        //预计结束时间    预计用车数量     当前时间开始
        Long cHour=0L;
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String hours = sdf.format(new Date());
        cHour = Long.valueOf(hours);
        Map<String,Integer> map = new HashMap<>();
        for (cHour = Long.valueOf(hours);cHour<24;cHour++){
            //[0,1,2,3,4,5,6]
            for (Tasks task:tasksList) {
                Integer value = null==map.get(cHour+"")?0:map.get(cHour+"");
                //如果你落在了这个区间内
                SimpleDateFormat tsdf = new SimpleDateFormat("");
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, cHour.intValue());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //这个时间要在 任务开始时间和任务计划结束时间之间
                if(calendar.getTime().getTime()<task.getPlanEndTime().getTime()&&calendar.getTime().getTime()>task.getStartTime().getTime()){
                    value+=task.getPlanCarCnt();
                    map.put(cHour+"",value);
                }
            }
        }
        return AjaxResult.success().put("map",map);
    }



    /**产线生产排队情况表*/
    @RequestMapping("/productList")
    public AjaxResult productList(HttpServletRequest request){
        List<ProductLine> productLines = redisUtils.getLists(Constants.PRODUCT_LINE_PATTERN,ProductLine.class);
        JSONObject json = new JSONObject();
        for(ProductLine line: productLines){
            if(null==line.getState()||line.getState()==1){
                continue;
            }
            List<Preproduction> list = preproductionService.selectPreproductionList(new Preproduction(){{
                setProductLine(line.getProductLine());
                setSqlWhere(" and (sort is null or sort > 0 ) and (SyncStatus=1 or syncStatus2 =1) and (state is null or state = 1) ");
            }});
            TasksCars tc = redisUtils.get(Constants.WAITE_QUEUE_PATTERN+line.getProductLine(),TasksCars.class);
            if(null!=tc){
                Preproduction ptc= new Preproduction();
                ptc.setProductLine(line.getProductLine());
                ptc.setInnerNumber(tc.getCarNo().toString());
                ptc.setRemark(tc.getDoorNo());
                ptc.setState(2);
                list.add(ptc);
            }
            json.put("list_line"+line.getProductLine(),list);
        }
        return AjaxResult.success().put("data",json);
    }


    /**通知副班车量*/
    @RequestMapping("/notify_freecar")
    public AjaxResult notify_freecar(HttpServletRequest request,String carNos,String content){
        Arrays.stream(Convert.toStrArray(carNos)).forEach(item->{
            doNotify(item,content);
        });
        return AjaxResult.success();
    }



    /**下线车辆*/
    @RequestMapping("/offlineDriver")
    public AjaxResult offlineDriver(HttpServletRequest request,String carNos){
        int ret = 0;
        for (String carNo:Convert.toStrArray(carNos)) {
            ret+=doOffline(carNo);
        }
        return toAjax(ret);
    }

    private int doOffline(String carNo){
        int ret=0;
        List<DriverCar> dlist = driverCarService.selectDriverCarList(new DriverCar(){{setCarNo(Integer.valueOf(carNo));}});
        if(null==dlist||dlist.size()==0){
            logger.debug("没有登记该车辆，无法下线");
            return ret;
        }
        for(DriverCar driverCar:dlist){
            RuningCar runingCar = redisUtils.get(Constants.RUNINGCAR_PATTEN+driverCar.getNotifyId(),RuningCar.class);
            TasksCars tasksCars = redisUtils.get(Constants.TASKINGCAR_PATTEN+driverCar.getNotifyId(),TasksCars.class);
            if(null!=tasksCars&&null!=tasksCars.getCarNo()&&tasksCars.getCarNo().equals(Integer.valueOf(carNo))){
                if(tasksCars.getStatus()==Constants.TASK_STATUS_ING){
                    logger.debug("该车号正在执行任务，"+tasksCars.getTaskName());
                    return ret;
                }
                RedisDomainUtils.setRedisTaskCarDomain(tasksCars,true);
                /*登出之后从redis缓存里面释放*/
                redisUtils.delete(Constants.RUNINGCAR_PATTEN+driverCar.getNotifyId());
                //重新设置正在上班的车辆信息
                redisUtils.delete(Constants.CARUSING_PATTEN+driverCar.getCarId());
                if(null!=runingCar){
                    redisUtils.delete(Constants.RUNINGCAR_PATTEN+runingCar.getNotifyId());
                }
                logger.debug("下线成功");
                ret++;
            }else if(null==tasksCars){
                //重新设置正在上班的车辆信息
                redisUtils.delete(Constants.CARUSING_PATTEN+driverCar.getCarId());
                if(null!=runingCar){
                    redisUtils.delete(Constants.RUNINGCAR_PATTEN+runingCar.getNotifyId());
                }
                logger.debug("下线成功");
                ret++;
            }
        }
        return ret;
    }



    /**
     * 具体通知
     * */
    private void doNotify(String carNo,String content){
        List<DriverCar> driversList = redisUtils.getLists(Constants.DRVCAR_PATTEN,DriverCar.class);
        if(null!=driversList&&driversList.size()>0){
            driversList.forEach(item->{
                if(item.getCarNo().equals(Integer.valueOf(carNo))){
                    //通知司机
                    PushUtils.pushSendTasking(content,"freecar",item.getDriverMobile());
                    logger.debug("通知副班车辆成功车号[{}],司机号码[{}]",carNo,item.getDriverMobile());
                }
            });
        }
    }





}
