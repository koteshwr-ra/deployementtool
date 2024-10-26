package com.example.sroslibrary.monitor.monitor.request;

import com.google.gson.annotations.Expose;

public class ControlPlaybackBean {
    @Expose
    private int cmd;
    @Expose
    private String handle;
    @Expose
    private String nowtime;
    @Expose
    private String robotid;
    @Expose
    private String robotip;
    @Expose
    private String robotport;
    @Expose
    private String robottype;
    @Expose
    private int speed;

    public String getHandle() {
        return this.handle;
    }

    public int getCmd() {
        return this.cmd;
    }

    public int getSpeed() {
        return this.speed;
    }
}
