package com.ruoyi.project.locator.common;

public class GPSLocation {
    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public GPSLocation setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public GPSLocation setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public GPSLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GPSLocation() {

    }
}
