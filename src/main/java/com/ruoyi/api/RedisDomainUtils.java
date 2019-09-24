package com.ruoyi.api;


import com.ruoyi.api.domain.PrintDomain;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.carSignlog.domain.CarSignlog;
import com.ruoyi.project.cars.driver.domain.Driver;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cemslink.preproduction.domain.Preproduction;
import com.ruoyi.project.cemslink.preproduction.domain.PreproductionQueue;
import com.ruoyi.project.cemslink.preproduction.service.IPreproductionService;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.lines.productLine.domain.ProductLine;

import java.util.List;

public class RedisDomainUtils {
    private static RedisUtils redisUtils = SpringUtils.getBean(RedisUtils.class);
    private static IPreproductionService preproductionService = SpringUtils.getBean(IPreproductionService.class);

    /**
     * @author xuehf
     * 重置Tasks的redis服务器
     * */
    public static void setRedisTaskDomain(Tasks task,boolean... isDel) {
        if(null==task){
            throw new BaseException("setRedisTaskDomain===== task cant be null!");
        }
        //1.从redis里删除变量
        if(null!=isDel&&isDel.length>0&&isDel[0]){
            redisUtils.delete(Constants.TASK_PATTEN+task.getId());
        }else{
            redisUtils.set(Constants.TASK_PATTEN+task.getId(),task,-1);
        }
    }

    /**
     * @author xuehf
     * 重置taskCars的redis
     * **/
    public static void setRedisTaskCarDomain(TasksCars tasksCars,boolean... isDel){
        if(null==tasksCars){
            throw new BaseException("setRedisTaskCarDomain===== tasksCars cant be null!");
        }
        //1.从redis里删除变量
        if(null!=isDel&&isDel.length>0&&isDel[0]){
            redisUtils.delete(Constants.TASKINGCAR_PATTEN+tasksCars.getNotifyId());
        }else{
            redisUtils.set(Constants.TASKINGCAR_PATTEN+tasksCars.getNotifyId(),tasksCars,-1);
        }
    }

    /**
     * 设置Car的redis服务器
     * @author xuehf
     * **/
    public static void setRedisCarDomain(Car car,boolean... isDel){
        if(null==car){
            throw new BaseException("setRedisCarDomain===== car cant be null!");
        }
        //1.从redis里删除变量
        if(null!=isDel&&isDel.length>0&&isDel[0]){
            redisUtils.delete(Constants.CAR_PATTEN+car.getId());
        }else{
            redisUtils.set(Constants.CAR_PATTEN+car.getId(),car,-1);
        }
    }


    /**
     * 设置DriverCar的redis服务器
     * @author xuehf
     * **/
    public static void setRedisDriverCarDomain(DriverCar driverCar,boolean... isDel){
        if(null==driverCar){
            throw new BaseException("setRedisDriverCarDomain===== driverCar cant be null!");
        }
        //1.从redis里删除变量
        if(null!=isDel&&isDel.length>0&&isDel[0]){
            redisUtils.delete(Constants.DRVCAR_PATTEN+driverCar.getNotifyId());
        }else{
            redisUtils.set(Constants.DRVCAR_PATTEN+driverCar.getNotifyId(),driverCar,-1);
        }
    }
    /**删除drivercar*/
    private static void deleteDriverCar(Car car){
        if(null==car){
            throw new BaseException("deleteDriverCar===== car cant be null!");
        }
        List<DriverCar> dclist = redisUtils.getLists(Constants.DRVCAR_PATTEN+"*",DriverCar.class);
        if(dclist.size()>0){
            dclist.forEach(item->{
                if(null!=car&&item.getCarId()==car.getId()){
                    redisUtils.delete(Constants.DRVCAR_PATTEN+item.getNotifyId());
                }
            });
        }
    }

    /**维护打印列表*/
    public static void setRedisPrintDomain(PrintDomain printDomain,boolean... isDel){
        if(null==printDomain){
            throw new BaseException("setRedisPrintDomain===== printDomain cant be null!");
        }
        //1.从redis里删除变量
        if(null!=isDel&&isDel.length>0&&isDel[0]){
            redisUtils.delete(Constants.PRINT_PATTERN+printDomain.getTaskCarId());
        }else{
            redisUtils.set(Constants.PRINT_PATTERN+printDomain.getTaskCarId(),printDomain,-1);
        }
    }

    /**
     * @author xuehf
     * 重置productlines的redis
     * **/
    public static void setRedisProductLineDomain(ProductLine productLine, boolean... isDel){
        if(null==productLine){
            throw new BaseException("setRedisProductLineDomain===== productLine cant be null!");
        }
        //1.从redis里删除变量
        if(null!=isDel&&isDel.length>0&&isDel[0]){
            redisUtils.delete(Constants.PRODUCT_LINE_PATTERN+productLine.getProductLine());
        }else{
            redisUtils.set(Constants.PRODUCT_LINE_PATTERN+productLine.getProductLine(),productLine,-1);
        }
    }



    /**
     * @author xuehf
     * 重置LineWaitQueue的redis
     * **/
    public static void setRedisLineWaitQueueDomain(TasksCars tasksCars, boolean... isDel){
        if(null==tasksCars){
            throw new BaseException("setRedisLineWaitQueueDomain===== tasksCars cant be null!");
        }
        //1.从redis里删除变量
        if(null!=isDel&&isDel.length>0&&isDel[0]){
            redisUtils.delete(Constants.WAITE_QUEUE_PATTERN+tasksCars.getProductLine().toString());
        }else{
            redisUtils.set(Constants.WAITE_QUEUE_PATTERN+tasksCars.getProductLine().toString(),tasksCars,-1);
        }
    }

    /**
     * @author xuehf
     * 重置LineWaitQueue的redis
     * **/
    public static void setRedisDriverDomain(Driver driver,boolean... isDel) {
        if(null==driver){
            throw new BaseException("setRedisDriverDomain===== driver cant be null!");
        }
        //1.从redis里删除变量
        if(null!=isDel&&isDel.length>0&&isDel[0]){
            redisUtils.delete(Constants.DRIVER_PATTERN+driver.getDMobile());
        }else{
            redisUtils.set(Constants.DRIVER_PATTERN+driver.getDMobile(),driver,-1);
        }
    }

    public static void setRedisCarSignLog(CarSignlog carSignlog,boolean... isDel) {
        if(null==carSignlog){
            throw new BaseException("setRedisCarSignLog===== carSignlog cant be null!");
        }
        //1.从redis里删除变量
        if(null!=isDel&&isDel.length>0&&isDel[0]){
            redisUtils.delete(Constants.CAR_SIGN_LOG_PATTERN+carSignlog.getSuffixTime());
        }else{
            if(StringUtils.isBlank(carSignlog.getSuffixTime())){
                throw new BaseException("今日排班车辆redis主键-SuffixTime不能为空");
            }
            redisUtils.set(Constants.CAR_SIGN_LOG_PATTERN+carSignlog.getSuffixTime(),carSignlog,-1);
        }
    }
}
