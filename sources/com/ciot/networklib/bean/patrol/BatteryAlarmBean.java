package com.ciot.networklib.bean.patrol;

public class BatteryAlarmBean {
    private String detail;
    private int power;

    public BatteryAlarmBean(int i, String str) {
        this.power = i;
        this.detail = str;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String str) {
        this.detail = str;
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int i) {
        this.power = i;
    }
}
