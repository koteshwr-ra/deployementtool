package com.ciot.waterchassis.bean;

public class MarkerBean implements Comparable<MarkerBean> {
    private int floor;
    private boolean isClick = false;
    private boolean isPoint = true;
    private WaterMarkerBean marker;

    public MarkerBean() {
    }

    public MarkerBean(int i, WaterMarkerBean waterMarkerBean) {
        this.floor = i;
        this.marker = waterMarkerBean;
    }

    public int getFloor() {
        return this.floor;
    }

    public void setFloor(int i) {
        this.floor = i;
    }

    public WaterMarkerBean getMarker() {
        return this.marker;
    }

    public void setMarker(WaterMarkerBean waterMarkerBean) {
        this.marker = waterMarkerBean;
    }

    public boolean isClick() {
        return this.isClick;
    }

    public void setClick(boolean z) {
        this.isClick = z;
    }

    public boolean isPoint() {
        return this.isPoint;
    }

    public void setPoint(boolean z) {
        this.isPoint = z;
    }

    public String toString() {
        return "MarkerBean{floor='" + this.floor + '\'' + ", marker=" + this.marker + ", isClick=" + this.isClick + ", isPoint=" + this.isPoint + '}';
    }

    public int compareTo(MarkerBean markerBean) {
        return this.floor - markerBean.getFloor();
    }
}
