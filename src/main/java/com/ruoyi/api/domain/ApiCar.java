package com.ruoyi.api.domain;

/**
 * @Author 苑福建
 * @Description
 * @Version 1.0
 * @Date 2019/4/26 10:37
 */
public class ApiCar {
    /***汽车ID*/
    private String carId = null;
    /***汽车信息 eg:1号/30分钟*/
    private String info = null;
    /***汽车运行进度百分比*/
    private String progress = null;
    /***汽车运行状态*/
    private String status = null;
    /***是否超出停止时间*/
    private String isOver=null;

    public ApiCar(String carId, String info, String progress, String status, String isOver) {
        this.carId = carId;
        this.info = info;
        this.progress = progress;
        this.status = status;
        this.isOver = isOver;
    }

    public String getIsOver() {
        return isOver;
    }

    public void setIsOver(String isOver) {
        this.isOver = isOver;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
