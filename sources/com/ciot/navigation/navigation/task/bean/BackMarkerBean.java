package com.ciot.navigation.navigation.task.bean;

public class BackMarkerBean {
    private float angle;
    private int floor;
    private String markerName;
    private int x;
    private int y;

    public int getX() {
        return this.x;
    }

    public void setX(int i) {
        this.x = i;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int i) {
        this.y = i;
    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float f) {
        this.angle = f;
    }

    public String getMarkerName() {
        return this.markerName;
    }

    public void setMarkerName(String str) {
        this.markerName = str;
    }

    public int getFloor() {
        return this.floor;
    }

    public void setFloor(int i) {
        this.floor = i;
    }

    public String toString() {
        return "BackMarkerBean{x=" + this.x + ", y=" + this.y + ", floor=" + this.floor + ", angle=" + this.angle + ", markerName='" + this.markerName + '\'' + '}';
    }
}
