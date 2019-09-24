package com.ruoyi.api.domain;

import com.ruoyi.common.utils.LocationUtils;
import com.ruoyi.common.config.Global;
import com.ruoyi.project.redisEntry.LonLat;

import java.util.List;

public class RuningCar {

    private static final Long minute = 60*1000L;
    /** 经度*/
    private Double lng = null;
    /**纬度*/
    private Double lat = null;
    /** 汽车id*/
    private String carId = null;
    private String notifyId;
    /**车辆当前任务id*/
    private Long taskId;
    /**当前任务状态*/
    private int taskStatus = 0;
    /**是否在同一个地点停留了5min*/
    private boolean isStop = true;
    /**车号*/
    private String carNo;
    /**是否在站内*/
    private boolean isInner = false;
    /**是否到达终点，进站时设置**/
    private boolean isEnding = false;
    /**是否出发出站时，点击后设置*/
    private boolean isStart = false;
    /**是否在任务中*/
    private boolean isTasking = false;
    /**车辆的停止时刻*/
    private Long stopTime;
    //上次推送时间
    private Long lastNotifyTime;
    /**五分钟内的经纬度记录*/
    private List<LonLat> lonLatList=null;


    public Long getLastNotifyTime() {
        return lastNotifyTime;
    }

    public void setLastNotifyTime(Long lastNotifyTime) {
        this.lastNotifyTime = lastNotifyTime;
    }

    public RuningCar(){}

    public RuningCar(Double lng, Double lat, String notifyId, List<LonLat> lonLatList) {
        this.setLat(lat);
        this.setLng(lng);
        this.setNotifyId(notifyId);
        this.setLonLatList(lonLatList);
    }

    public Long getStopTime() {
        return stopTime;
    }

    public void setStopTime(Long stopTime) {
        this.stopTime = stopTime;
    }

    public static Long getMinute() {
        return minute;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public boolean isInner() {
        return Double.valueOf(Global.getConfig("project.startDistance")).compareTo(LocationUtils.getDistance(Double.valueOf(Global.getConfig("project.lat")),Double.valueOf(Global.getConfig("project.lon")),lat,lng)) >=0;
    }

    public void setInner(boolean inner) {
        isInner = inner;
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

    public List<LonLat> getLonLatList() {
        return lonLatList;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public void setLonLatList(List<LonLat> lonLatList) {
        this.lonLatList = lonLatList;
    }
}
