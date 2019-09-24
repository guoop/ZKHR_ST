package com.ruoyi.project.redisEntry;

import java.util.Date;

/**
 * @Author 苑福建
 * @Description
 * @Version 1.0
 * @Date 2019/4/25 19:18
 */
public class LonLatEntity {
    /**
     * 经度
     */
    private Double lon;
    /**
     * 纬度
     */
    private Double lat;
    private String carNo;
    private String fangliang;
    private String ljfangliang;

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

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getFangliang() {
        return fangliang;
    }

    public void setFangliang(String fangliang) {
        this.fangliang = fangliang;
    }

    public String getLjfangliang() {
        return ljfangliang;
    }

    public void setLjfangliang(String ljfangliang) {
        this.ljfangliang = ljfangliang;
    }
}
