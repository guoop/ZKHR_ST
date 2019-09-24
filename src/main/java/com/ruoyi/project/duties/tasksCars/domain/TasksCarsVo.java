package com.ruoyi.project.duties.tasksCars.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 任务车辆表 tb_tasks_cars
 *
 * @author admin
 * @date 2019-04-25
 */
public class TasksCarsVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * taskscars id
     */
    private Long id;
    /**
     * 任务id
     */
    private String taskId;
    /**
     * 任务名称
     */
    private String name;
    /**
     * 是否暂停
     */
    private String pause;
    /**
     * 汽车ID
     */
    private String carId;
    /**
     * 车牌
     */
    private String carNo;
    /**
     * 运用砂浆方量
     */
    private Double shajiangfl;
    /**
     * 是否到达终点
     */
    private String isEnd;
    /**
     * 是否出发
     */
    private String isStart;

    /**
     * 终点经度
     */
    private String lon;
    /**
     * 终点纬度
     */
    private String lat;
    /**
     * 结束时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

    private Double totalFl;

    public Double getTotalFl() {
        return totalFl;
    }

    public void setTotalFl(Double totalFl) {
        this.totalFl = totalFl;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getShajiangfl() {
        return shajiangfl;
    }

    public void setShajiangfl(Double shajiangfl) {
        this.shajiangfl = shajiangfl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setIsEnd(String isEnd) {
        this.isEnd = isEnd;
    }

    public String getIsEnd() {
        return isEnd;
    }

    public void setIsStart(String isStart) {
        this.isStart = isStart;
    }

    public String getIsStart() {
        return isStart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPause() {
        return pause;
    }

    public void setPause(String pause) {
        this.pause = pause;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }
}
