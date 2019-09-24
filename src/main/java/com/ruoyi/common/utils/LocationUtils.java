package com.ruoyi.common.utils;

import com.ruoyi.project.cars.driver.domain.Driver;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.redisEntry.LonLat;

/**
 * 经纬度工具类
 * @Author 苑福建
 * @Description
 * @Version 1.0
 * @Date 2019/4/25 15:04
 */
public class LocationUtils {

    private static double EARTH_RADIUS3 = 6378137;

    private synchronized static double rad3(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 计算两个经纬度之间的距离  结果单位：米
     * @param lat1 第一个点纬度
     * @param lng1 第一个点经度
     * @param lat2 第二个点纬度
     * @param lng2 第二个点经度
     * @return
     */
    public synchronized static double  getDistance(Double lat1, Double lng1, Double lat2, Double lng2) {

        double radLat1 = rad3(lat1);
        double radLat2 = rad3(lat2);
        double a = radLat1 - radLat2;
        double b = rad3(lng1) - rad3(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS3;
        s = Math.round(s * 10000) / 10000;
        return s;
    }


    /**
     * 验证两点是否超出距离
     * @param lat1 第一个点纬度
     * @param lng1 第一个点经度
     * @param lat2 第二个点纬度
     * @param lng2 第二个点经度
     * @param distance  超出距离 单位是米
     * @return
     */
    public synchronized static boolean  getTwoPointIsOver(Double lat1, Double lng1, Double lat2, Double lng2,Double distance) {

        double radLat1 = rad3(lat1);
        double radLat2 = rad3(lat2);
        double a = radLat1 - radLat2;
        double b = rad3(lng1) - rad3(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS3;
        s = Math.round(s * 10000) / 10000;
        if (s>distance) {
            return true;
        }
        return false;
    }

    /**
     * test
     * @param args
     */
    public static void main(String[] args){
        double distance3 = getDistance(121.491909,31.233234,121.411994,31.206134);
        System.out.println("Distance3 is:"+distance3);
    }
}
