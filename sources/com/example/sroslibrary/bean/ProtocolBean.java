package com.example.sroslibrary.bean;

public class ProtocolBean {
    private Object body;
    private short cflag;
    private short cmd;
    private int head = -13108;
    private String id;
    private int len;
    private byte qa;
    private short result;
    private short rflag;
    private int seq;
    private short ver;

    public int getHead() {
        return this.head;
    }

    public byte getQa() {
        return this.qa;
    }

    public void setQa(byte b) {
        this.qa = b;
    }

    public int getSeq() {
        return this.seq;
    }

    public void setSeq(int i) {
        this.seq = i;
    }

    public short getCmd() {
        return this.cmd;
    }

    public void setCmd(short s) {
        this.cmd = s;
    }

    public short getVer() {
        return this.ver;
    }

    public void setVer(short s) {
        this.ver = s;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public short getCflag() {
        return this.cflag;
    }

    public void setCflag(short s) {
        this.cflag = s;
    }

    public short getRflag() {
        return this.rflag;
    }

    public void setRflag(short s) {
        this.rflag = s;
    }

    public short getResult() {
        return this.result;
    }

    public void setResult(short s) {
        this.result = s;
    }

    public int getLen() {
        return this.len;
    }

    public void setLen(int i) {
        this.len = i;
    }

    public Object getBody() {
        return this.body;
    }

    public void setBody(Object obj) {
        this.body = obj;
    }

    public String toString() {
        return "ProtocolBean{head=" + this.head + ", qa=" + this.qa + ", seq=" + this.seq + ", cmd=" + this.cmd + ", ver=" + this.ver + ", id='" + this.id + '\'' + ", cflag=" + this.cflag + ", rflag=" + this.rflag + ", result=" + this.result + ", len=" + this.len + ", body=" + this.body + '}';
    }
}
