package com.ciot.networklib.bean.patrol;

public class PatrolReportBean {
    private String id;
    private String name;
    private Long time;
    private String type;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long l) {
        this.time = l;
    }
}
