package com.ruoyi.project.duties.tasks.domain;

import java.io.Serializable;

/**
 * Tasks实体类字段太多
 */
public class TasksData implements Serializable {

    public static final TasksData  tasksData = new TasksData();

    public static TasksData getInstance(){
        return tasksData;
    }
    private String targetAddr;
    private String lon ;
    private String lat;
    private String startAddr;
    private String startLng;
    private String startLat;
    public String getTargetAddr() {
        return targetAddr;
    }

    public void setTargetAddr(String targetAddr) {
        this.targetAddr = targetAddr;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getStartAddr() {
        return startAddr;
    }

    public void setStartAddr(String startAddr) {
        this.startAddr = startAddr;
    }

    public String getStartLng() {
        return startLng;
    }

    public void setStartLng(String startLng) {
        this.startLng = startLng;
    }

    public String getStartLat() {
        return startLat;
    }

    public void setStartLat(String startLat) {
        this.startLat = startLat;
    }
}
