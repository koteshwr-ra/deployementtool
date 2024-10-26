package com.ciot.networklib.bean.patrol;

public enum WarningType {
    BLACK_LIST("黑名单"),
    EQUIPMENT_EXCEPTION_CAMERA("设备异常-监控摄像头"),
    EQUIPMENT_EXCEPTION_CHASSIS("设备异常-底盘"),
    POINT_EXCEPTION_SURROUND("点位异常-被围住"),
    POINT_EXCEPTION_OCCUPY("点位异常-被占用"),
    POINT_EXCEPTION_STOP("中途停止巡更"),
    POWER_EXCEPTION_LOW("低电量异常");
    
    private String warningString;

    private WarningType(String str) {
        this.warningString = str;
    }

    public String getWarningString() {
        return this.warningString;
    }
}
