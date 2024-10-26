package com.ciot.diagnosis.bean;

public class Status {
    protected int code;
    protected String message;

    public Status(int i, String str) {
        this.code = i;
        this.message = str;
    }

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

    public String toString() {
        return this.message;
    }
}
