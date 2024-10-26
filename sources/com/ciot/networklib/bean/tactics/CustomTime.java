package com.ciot.networklib.bean.tactics;

public class CustomTime {
    private String end;
    private String start;
    private int type;

    public String getStart() {
        return this.start;
    }

    public void setStart(String str) {
        this.start = str;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String str) {
        this.end = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
