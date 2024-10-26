package com.ciot.waterchassis.bean;

public class MapBean {
    private int floor;
    private String mapName;

    public MapBean() {
    }

    public MapBean(String str, int i) {
        this.mapName = str;
        this.floor = i;
    }

    public String getMapName() {
        return this.mapName;
    }

    public void setMapName(String str) {
        this.mapName = str;
    }

    public int getFloor() {
        return this.floor;
    }

    public void setFloor(int i) {
        this.floor = i;
    }

    public String toString() {
        return "MapBean{mapName='" + this.mapName + '\'' + ", floor=" + this.floor + '}';
    }
}
