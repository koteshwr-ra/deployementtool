package com.example.sroslibrary.bean;

public class AlarmTaskBean {
    public static final int BLOCK_CAMERA_ALARM = 3;
    public static final int FALL_DOWN_ALARM = 5;
    public static final int LOW_POWER_ALARM = 2;
    public static final int OVER_TEMPTURE_ALARM = 1;
    public static final int SMOKE_ALARM = 4;
    private String mAlarmId;
    private String mAlarmStatement;
    private int mAlarmType;

    public String getAlarmId() {
        return this.mAlarmId;
    }

    public void setAlarmId(String str) {
        this.mAlarmId = str;
    }

    public String getAlarmStatement() {
        return this.mAlarmStatement;
    }

    public void setAlarmStatement(String str) {
        this.mAlarmStatement = str;
    }

    public int getAlarmType() {
        return this.mAlarmType;
    }

    public void setAlarmType(int i) {
        this.mAlarmType = i;
    }

    public String toString() {
        return "AlarmTaskBean{mAlarmId='" + this.mAlarmId + '\'' + ", mAlarmStatement='" + this.mAlarmStatement + '\'' + ", mAlarmType=" + this.mAlarmType + '}';
    }
}
