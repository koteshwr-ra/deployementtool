package com.ciot.waterchassis.bean;

public class WaterNotificationDataBean {
    private double distance;
    private String target;

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String str) {
        this.target = str;
    }

    public String toString() {
        return "WaterNotificationDataBean{distance=" + this.distance + ", target='" + this.target + '\'' + '}';
    }
}
