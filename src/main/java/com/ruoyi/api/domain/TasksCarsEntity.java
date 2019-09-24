package com.ruoyi.api.domain;

public class TasksCarsEntity {
    private Long id;
    private Long carId;//carId
    private Long carNo;//carId
    private String info;//1号/30分钟
    private String process;//8%
    private String status;//go,back
    private String breakdown;//是否停止
    private boolean isSure;

    public boolean isSure() {
        return isSure;
    }

    public void setSure(boolean sure) {
        isSure = sure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBreakdown() {
        return breakdown;
    }

    public void setBreakdown(String breakdown) {
        this.breakdown = breakdown;
    }

    public Long getCarNo() {
        return carNo;
    }

    public void setCarNo(Long carNo) {
        this.carNo = carNo;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
