package com.example.sroslibrary.monitor.monitor.request;

import com.google.gson.annotations.Expose;

public class StopVideoRequestBean {
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

    public String getHandle() {
        return this.handle;
    }
}
