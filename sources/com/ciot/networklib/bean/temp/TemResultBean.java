package com.ciot.networklib.bean.temp;

public class TemResultBean {
    private long createtime;
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public long getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(int i) {
        this.createtime = (long) i;
    }

    public String toString() {
        return "TemResultBean{id='" + this.id + '\'' + ", createtime=" + this.createtime + '}';
    }
}
