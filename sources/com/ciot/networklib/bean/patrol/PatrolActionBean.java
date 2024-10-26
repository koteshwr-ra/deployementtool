package com.ciot.networklib.bean.patrol;

public class PatrolActionBean {
    private String path;
    private Long time;
    private int type;

    public PatrolActionBean(int i, Long l) {
        this.type = i;
        this.time = l;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long l) {
        this.time = l;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }
}
