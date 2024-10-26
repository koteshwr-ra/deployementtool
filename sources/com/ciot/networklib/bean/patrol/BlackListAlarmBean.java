package com.ciot.networklib.bean.patrol;

public class BlackListAlarmBean {
    private String detail;
    private String id;
    private String resource;
    private String type;

    public BlackListAlarmBean(String str, String str2, String str3) {
        this.id = str;
        this.type = str2;
        this.detail = str3;
    }

    public String getResource() {
        return this.resource;
    }

    public void setResource(String str) {
        this.resource = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
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
