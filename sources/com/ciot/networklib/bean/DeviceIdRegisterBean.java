package com.ciot.networklib.bean;

public class DeviceIdRegisterBean {
    private String buildingNum;
    private String deviceId;
    private String floorNum;
    private String roomNum;

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getBuildingNum() {
        return this.buildingNum;
    }

    public void setBuildingNum(String str) {
        this.buildingNum = str;
    }

    public String getFloorNum() {
        return this.floorNum;
    }

    public void setFloorNum(String str) {
        this.floorNum = str;
    }

    public String getRoomNum() {
        return this.roomNum;
    }

    public void setRoomNum(String str) {
        this.roomNum = str;
    }
}
