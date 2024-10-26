package com.ciot.networklib.bean;

public class DataUpdateEvent {
    private String id;
    private int type;

    public DataUpdateEvent(int i, String str) {
        this.type = i;
        this.id = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }
}
