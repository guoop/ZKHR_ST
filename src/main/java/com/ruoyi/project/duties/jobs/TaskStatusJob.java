package com.ruoyi.project.duties.jobs;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.project.locator.common.GPSLocation;
import com.ruoyi.project.locator.common.GeoUtil;
import com.ruoyi.project.locator.common.LocatorConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Configurable
//@EnableScheduling
public class TaskStatusJob extends BaseController {
    private Logger logger = LoggerFactory.getLogger(TaskStatusJob.class);
    @Autowired
    private ITasksService tasksService;
    @Autowired
    private ITasksCarsService tasksCarsService;
    @Autowired
    private ICarService carService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisUtils redisUtils;
    ThreadPoolExecutor executor = new ThreadPoolExecutor(1,5,2, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3));

    /**
     * @do 需要一分30秒执行检查任务车辆位置，并判断是否任务执行完成 0/30 0/2 * * * ?
     * @date 2019-08-15 10:48
     * @auth paulo
     */
    @Scheduled(cron = "0/30 0/2 * * * ? ")
    public void  autoTaskCarStatus(){
        String token = redisUtils.get(Constants.LOCATION_ACCESS_TOKEN);
        if(null != token){
            logger.debug("查询可执行任务车辆");
            List<Tasks> listTask = tasksService.selectDispathList();
            listTask.forEach(tasks -> {
                if(tasks.getStatus() == 1){
                    logger.debug("如果当前任务是正在进行中 — >");
                    List<Car> listCar = tasksService.selectCarByTask(tasks.getId());
                    TasksCars tc= new TasksCars();
                    tc.setTaskId(Long.valueOf(tasks.getId()));
                    tc.setStatus(Constants.TASK_STATUS_ING);
                    logger.debug("根据当前任务查询正在执行的任务车辆");
                    List<TasksCars> listTaskCar = tasksCarsService.selectTasksCarsList(tc);
                    if(listTaskCar.size() > 0){

                        listTaskCar.forEach(tasksCars -> {
                            try{
                                Car car = carService.selectCarById(tasksCars.getCarId());
                                logger.debug("正在查询任务车辆的车牌号是：{}",car.getCarNum());
                                Map result = restTemplate.getForObject(LocatorConstant.FKZX_API+
                                        LocatorConstant.LOCATION_INFO+"?accessToken="+token+"&imei="+car.getImei(),Map.class);
                                //大地坐标转百度坐标
                                double[] ll = GeoUtil.gps84_To_Gcj02(Double.valueOf(result.get("lat").toString()),Double.valueOf(result.get("lng").toString()));
                                double startLng = tc.getLng() != null ? Double.valueOf(tc.getStartLat()) : Double.valueOf(Global.getConfig("project.lat"));
                                double startlat = tc.getLat() != null ? Double.valueOf(tc.getStartLng()) : Double.valueOf(Global.getConfig("project.lon"));
                                GPSLocation startLocatin = new GPSLocation(startlat,startLng);
                                GPSLocation endLocation = new GPSLocation(ll[0],ll[1]);
                                double distance = GeoUtil.GetDistance(startLocatin,endLocation);
                                logger.debug("当前车辆距离起始:{}",distance);
                                if(distance > 1000){
                                    tc.setStatus(TasksCars.TASK_SUCCESS_2);
                                    tasksCarsService.updateTasksCars(tasksCars);
                                }
                            }catch(Exception e){
                                return;
                            }
                        });
                    }
                }
            });
        }else{
            redisUtils.set(Constants.LOCATION_ACCESS_TOKEN,getToken(),100*60*1000);
        }
    }


}
