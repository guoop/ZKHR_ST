package com.ruoyi.project.redisEntry;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.LocationUtils;
import com.ruoyi.project.monitor.server.domain.Sys;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Author 苑福建
 * @Description
 * @Version 1.0
 * @Date 2019/4/25 19:18
 */
public class CarsLonLat {
    private static final Long minute = 60*1000L;
    /**
     * 经度
     */
    private Double lon = null;
    /**
     * 纬度
     */
    private Double lat = null;
    /**
     * 汽车id
     */
    private String carId = null;
    private Long taskId;
    private int taskStatus = 0;

    private Long truckId;

    /**是否在同一个地点停留了5min*/
    private boolean isStop = true;

    private String carNo;

    private String carBrand;

    private String mobile;
    /**是否登出**/
    private boolean isLogout = true;
    /**是否返程*/
    private boolean isBacking = false;
    /**是否在站内*/
    private boolean isInner = false;
    /**是否到达终点，进站时设置**/
    private boolean isEnding = false;
    /**是否出发出站时，点击后设置*/
    private boolean isStart = false;
    /**是否在任务中*/
    private boolean isTasking = false;
    /**当前任务的开始时间*/
    private long startTime = System.currentTimeMillis();
    /**当前任务的结束时间*/
    private long endTime = System.currentTimeMillis();
    /**总耗时开始时间-结束时间*/
    private Long totoalTime;
    /**当前时间距离出发时间*/
    private Long beginStartTime;
    /**当前时间距离终点出发的时间*/
    private Long beginEndTime;

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public boolean isBacking() {
        return isBacking;
    }

    public void setBacking(boolean backing) {
        isBacking = backing;
    }

    public Long getTruckId() {
        return truckId;
    }

    public void setTruckId(Long truckId) {
        this.truckId = truckId;
    }

    public boolean isEnding() {
        return isEnding;
    }

    public void setEnding(boolean ending) {
        isEnding = ending;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public boolean isTasking() {
        return isTasking;
    }

    public void setTasking(boolean tasking) {
        isTasking = tasking;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Long getTotoalTime() {
        return (this.getStartTime() - this.getEndTime())/minute;
    }

    public void setTotoalTime(Long totoalTime) {
        this.totoalTime = totoalTime;
    }

    public Long getBeginStartTime() {
        return (System.currentTimeMillis() - this.getStartTime())/minute;
    }

    public void setBeginStartTime(Long beginStartTime) {
        this.beginStartTime = beginStartTime;
    }

    public Long getBeginEndTime() {
        return (System.currentTimeMillis() - this.getEndTime())/minute;
    }

    public void setBeginEndTime(Long beginEndTime) {
        this.beginEndTime = beginEndTime;
    }

    public boolean isLogout() {
        return isLogout;
    }

    public void setLogout(boolean logout) {
        isLogout = logout;
    }

    public boolean isInner() {
        return Double.valueOf(Global.getConfig("project.startDistance")).compareTo(LocationUtils.getDistance(Double.valueOf(Global.getConfig("project.lat")),Double.valueOf(Global.getConfig("project.lon")),lat,lon)) >=0;
    }

    public void setInner(boolean inner) {
        isInner = inner;
    }

    private List<LonLat> lonLatList=null;

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public CarsLonLat(){}

    public CarsLonLat(Double lon, Double lat, String carId, List<LonLat> lonLatList) {
        this.lon = lon;
        this.lat = lat;
        this.carId = carId;
        this.lonLatList = lonLatList;
    }

    public List<LonLat> getLonLatList() {
        return lonLatList;
    }

    public void setLonLatList(List<LonLat> lonLatList) {
        this.lonLatList = lonLatList;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

}
