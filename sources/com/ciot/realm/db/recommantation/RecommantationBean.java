package com.ciot.realm.db.recommantation;

public class RecommantationBean {
    private int code;
    private DataBean data;
    private String message;
    private int ttl;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public int getTtl() {
        return this.ttl;
    }

    public void setTtl(int i) {
        this.ttl = i;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public String toString() {
        return "RecommantationBean{code=" + this.code + ", message='" + this.message + '\'' + ", ttl=" + this.ttl + ", data=" + this.data + '}';
    }
}
