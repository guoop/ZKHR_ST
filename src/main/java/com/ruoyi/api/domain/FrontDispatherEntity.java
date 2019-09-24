package com.ruoyi.api.domain;

import com.ruoyi.project.duties.tasksCars.domain.TasksCars;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FrontDispatherEntity {
    private Long taskId;//
    private String taskName;//任务名称1
    private String taskStatus;//Y - pause
    private String totalProgress;//8%
    private String warning;
    private List<TasksCarsEntity> cars = new ArrayList<>();
    private List<TasksCars> runList = new ArrayList<>();

    public List<TasksCars> getRunList() {
        return runList;
    }

    public void setRunList(List<TasksCars> runList) {
        this.runList = runList;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
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

    public List<TasksCarsEntity> getCars() {
        return cars;
    }

    public void setCars(List<TasksCarsEntity> cars) {
        this.cars = cars;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
}
