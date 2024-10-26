package com.example.sroslibrary.bean;

public class TranspondProtocolBean {
    private String body;
    private byte flow;
    private short head = -21588;
    private int length;
    private short type;

    public short getHead() {
        return this.head;
    }

    public void setHead(short s) {
        this.head = s;
    }

    public short getType() {
        return this.type;
    }

    public void setType(short s) {
        this.type = s;
    }

    public byte getFlow() {
        return this.flow;
    }

    public void setFlow(byte b) {
        this.flow = b;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int i) {
        this.length = i;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public String toString() {
        return "TranspondProtocolBean{head=" + this.head + ", type=" + this.type + ", flow=" + this.flow + ", length=" + this.length + ", body=" + this.body + '}';
    }
}
