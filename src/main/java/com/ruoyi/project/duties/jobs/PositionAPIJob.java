package com.ruoyi.project.duties.jobs;

import com.ruoyi.api.ApiTasksCarsController;
import com.ruoyi.api.PushUtils;
import com.ruoyi.api.RedisDomainUtils;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtil;
import com.ruoyi.common.utils.Md5Utils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.cars.driver.domain.Driver;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cars.tasksMixformula.domain.TasksMixformula;
import com.ruoyi.project.cars.tasksMixformula.service.ITasksMixformulaService;
import com.ruoyi.project.cemslink.preproduction.domain.Preproduction;
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
import com.ruoyi.project.locator.common.LocatorConstant;
import com.ruoyi.project.monitor.job.service.IJobLogService;
import com.ruoyi.project.system.user.controller.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
@Component("positionAPIJob")
@EnableScheduling
public class PositionAPIJob
{
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired private LoginController loginController;

    ThreadPoolExecutor executor = new ThreadPoolExecutor(1,1,20, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(200));

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void getApiTokenFromThirdPart(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("正在請求token");
                String token = loginController.getToken();
                redisUtils.set(Constants.LOCATION_ACCESS_TOKEN,token,100*60*1000);
                System.out.println("請求結果+token="+token);
            }
        });
    }

}
