package com.ruoyi.api.domain;

import java.util.List;

/**
 * 任务车辆表 tb_tasks_cars
 *
 * @author admin
 * @date 2019-04-25
 */
public class ApiTasksCars {
    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    private String taskId;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 是否暂停(任务状态)
     */
    private String taskStatus;
    /**
     * 总进度（百分比）
     */
    private String totalProgress;


    /**
     * 结束时间
     */
    private List<ApiCar> cars;

    public ApiTasksCars(String taskId, String taskName, String taskStatus, String totalProgress, List<ApiCar> cars) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.totalProgress = totalProgress;
        this.cars = cars;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTotalProgress() {
        return totalProgress;
    }

    public void setTotalProgress(String totalProgress) {
        this.totalProgress = totalProgress;
    }

    public List<ApiCar> getCars() {
        return cars;
    }

    public void setCars(List<ApiCar> cars) {
        this.cars = cars;
    }
}
