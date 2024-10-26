package com.ciot.networklib.bean.patrol;

public class AlarmRequestBean {
    private Long alarmtime;
    private String alarmtype;
    private AlarmDataBean data;
    private String domain;
    private String project;
    private String robot;

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public String getAlarmtype() {
        return this.alarmtype;
    }

    public void setAlarmtype(String str) {
        this.alarmtype = str;
    }

    public Long getAlarmtime() {
        return this.alarmtime;
    }

    public void setAlarmtime(Long l) {
        this.alarmtime = l;
    }

    public String getProject() {
        return this.project;
    }

    public void setProject(String str) {
        this.project = str;
    }

    public String getRobot() {
        return this.robot;
    }

    public void setRobot(String str) {
        this.robot = str;
    }

    public AlarmDataBean getData() {
        return this.data;
    }

    public void setData(AlarmDataBean alarmDataBean) {
        this.data = alarmDataBean;
    }
}
