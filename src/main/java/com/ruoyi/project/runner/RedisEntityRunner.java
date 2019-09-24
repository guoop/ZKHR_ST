package com.ruoyi.project.runner;

import com.ruoyi.api.RedisDomainUtils;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.cars.driver.domain.Driver;
import com.ruoyi.project.cars.driver.service.IDriverService;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cars.driverCar.service.IDriverCarService;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.project.lines.productLine.domain.ProductLine;
import com.ruoyi.project.lines.productLine.service.IProductLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Order(10)
@Component
public class RedisEntityRunner implements CommandLineRunner {
    @Autowired
    private IDriverCarService driverCarService;
    @Autowired
    private ICarService carService;
    @Autowired
    private ITasksService tasksService;
    @Autowired
    private ITasksCarsService tasksCarsService;
    @Autowired
    private IProductLineService productLineService;
    @Autowired
    private IDriverService driverService;
    @Autowired
    private RedisUtils redisUtils;
    private static final Logger logger = LoggerFactory.getLogger(RedisEntityRunner.class);
    @Override
    public void run(String... args) throws Exception {
        logger.debug("initalize redis static content.");

        /**
         * 初始化地磅===不用初始化
         * **/
//        Object obj = redisUtils.get(Constants.BANG_NO+"S");
//        Object obj2 = redisUtils.get(Constants.BANG_NO+"N");
//        if(null == obj){
//            BigDecimal weight = new BigDecimal("0");
//            redisUtils.set(Constants.BANG_NO+"S",weight);
//        }
//        if(obj2 == null){
//            BigDecimal weight = new BigDecimal("0");
//            redisUtils.set(Constants.BANG_NO+"N",weight);
//        }
        /**
         * 初始化DriverCar
         * */
        if(!redisUtils.existOne(Constants.DRVCAR_PATTEN)){
            List<DriverCar> driverCarList  = driverCarService.selectDriverCarList(new DriverCar());
            driverCarList.forEach(item->{
                RedisDomainUtils.setRedisDriverCarDomain(item);
            });
        }

        /**
         * 初始化Car
         * */
        if(!redisUtils.existOne(Constants.CAR_PATTEN)){
            List<Car> carList  = carService.selectCarList(null);
            carList.forEach(item->{
                RedisDomainUtils.setRedisCarDomain(item);
            });
        }

        /**
         * 初始化任务
         * */
        if(!redisUtils.existOne(Constants.TASK_PATTEN)){
            List<Tasks> tList  = tasksService.selectTasksList(new Tasks(){{
                setSqlWhere(" status in (0,1) ");
            }});
            tList.forEach(item->{
                RedisDomainUtils.setRedisTaskDomain(item);
            });
        }

        /**
         * 初始化任务-车辆
         * */
        if(!redisUtils.existOne(Constants.TASKINGCAR_PATTEN)){
            //只初始化任务中的车辆信息
            List<TasksCars> tcList  = tasksCarsService.selectTasksCarsList(new TasksCars(){{setStatus(1);}});
            tcList.forEach(item->{
                RedisDomainUtils.setRedisTaskCarDomain(item);
            });
        }

        /**
         * 初始化主机状态
         * */
        if(!redisUtils.existOne(Constants.PRODUCT_LINE_PATTERN)){
            List<ProductLine> pList  = productLineService.selectProductLineList(null);
            pList.forEach(item->{
                RedisDomainUtils.setRedisProductLineDomain(item);
            });
        }


        /**
         * 初始化driver
         * */
        if(!redisUtils.existOne(Constants.DRIVER_PATTERN)){
            List<Driver> pList  = driverService.selectDriverList(null);
            pList.forEach(item->{
                RedisDomainUtils.setRedisDriverDomain(item);
            });
        }
    }

}
