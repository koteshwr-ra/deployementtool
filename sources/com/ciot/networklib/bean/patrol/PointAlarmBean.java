package com.ciot.networklib.bean.patrol;

public class PointAlarmBean {
    private String detail;
    private int floor;
    private String type;
    private double x;
    private double y;

    public PointAlarmBean(String str, double d, double d2, int i, String str2) {
        this.type = str;
        this.x = d;
        this.y = d2;
        this.floor = i;
        this.detail = str2;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String str) {
        this.detail = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public double getX() {
        return this.x;
    }

    public void setX(float f) {
        this.x = (double) f;
    }

    public double getY() {
        return this.y;
    }

    public void setY(float f) {
        this.y = (double) f;
    }

    public int getFloor() {
        return this.floor;
    }

    public void setFloor(int i) {
        this.floor = i;
    }
}
