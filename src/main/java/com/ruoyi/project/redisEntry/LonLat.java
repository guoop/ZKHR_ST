package com.ruoyi.project.redisEntry;

import java.util.Date;

/**
 * @Author 苑福建
 * @Description
 * @Version 1.0
 * @Date 2019/4/25 19:18
 */
public class LonLat {
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
    private Date createTime = null;

    private Long initTimes;

    private boolean isStop = false;

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public LonLat(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
        this.initTimes = System.currentTimeMillis();
    }

    public LonLat(Double lon, Double lat, Date createTime) {
        this.lon = lon;
        this.lat = lat;
        this.createTime = createTime;
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

    public Date getCreateTime() {
        return createTime;
    }

    public Long getInitTimes() {
        return initTimes;
    }

    public void setInitTimes(Long initTimes) {
        this.initTimes = initTimes;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
