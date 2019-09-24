package com.ruoyi.project.duties.utils;

import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.config.Global;

public class TasksUtils {
    private static RedisUtils redisUtils = SpringUtils.getBean(RedisUtils.class);

    //开始点范围
    private static Long startDistance = Long.valueOf(Global.getConfig("project.startDistance"));
    //结束点的范围
    private static Long endDistance = Long.valueOf(Global.getConfig("project.endDistance"));


    /**
     * 获取空闲车辆
     * */
    /*public static List<DriverCar> findEmtyCars(){
        //获取所有已经登陆的车辆
        List<RuningCar> carsLonLats = redisUtils.getList(Constants.RUNING_CARLIST,RuningCar.class);
        //任务中的车辆列表
        List<TasksCars> taskingList = redisUtils.getList(Constants.TASKS_CARSLIST,TasksCars.class);
        //任务列表
        List<Tasks> tasksList = redisUtils.getList(Constants.TASKLIST,Tasks.class);

        if(null == carsLonLats){
            return null;
        }
        //循环判断是否在空闲
        carsLonLats.forEach(item->{

        });
        return null;
//        double LocationUtils.getDistance(Global.getConfig("project.lon"),Global.getConfig("project.lat"),)
    }*/

}
