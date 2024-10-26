package com.ciot.networklib.bean.patrol;

public class EquipmentAlarmBean {
    private String detail;
    private String type;

    public EquipmentAlarmBean(String str, String str2) {
        this.type = str;
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
}
