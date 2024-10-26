package com.example.sroslibrary.bean;

public class SrosEventMsg {
    private String msgContent;
    private String msgType;

    public SrosEventMsg() {
    }

    public SrosEventMsg(String str) {
        this.msgContent = str;
    }

    public SrosEventMsg(String str, String str2) {
        this.msgType = str;
        this.msgContent = str2;
    }

    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgType(String str) {
        this.msgType = str;
    }

    public String getMsgContent() {
        return this.msgContent;
    }

    public void setMsgContent(String str) {
        this.msgContent = str;
    }

    public String toString() {
        return "SrosEventMsg{msgType='" + this.msgType + '\'' + ", msgContent='" + this.msgContent + '\'' + '}';
    }
}
