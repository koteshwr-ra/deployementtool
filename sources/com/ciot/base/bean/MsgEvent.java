package com.ciot.base.bean;

public class MsgEvent<T> {
    private T msgContent;
    private String msgTag;

    private MsgEvent() {
    }

    public MsgEvent(String str) {
        this.msgTag = str;
        this.msgContent = null;
    }

    public MsgEvent(String str, T t) {
        this.msgTag = str;
        this.msgContent = t;
    }

    public String getMsgTag() {
        return this.msgTag;
    }

    public void setMsgTag(String str) {
        this.msgTag = str;
    }

    public T getMsgContent() {
        return this.msgContent;
    }

    public void setMsgContent(T t) {
        this.msgContent = t;
    }

    public String toString() {
        return "MsgEvent{msgTag='" + this.msgTag + '\'' + ", msgContent=" + this.msgContent + '}';
    }
}
