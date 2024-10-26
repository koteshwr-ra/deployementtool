package com.example.sroslibrary.monitor.monitor.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartLiveRequestBean {
    @Expose
    private int channel;
    @Expose
    private String client;
    @Expose
    private int mode;
    @SerializedName("nowtime")
    @Expose
    private String nowTime;
    @SerializedName("robotid")
    @Expose
    private String robotId;
    @SerializedName("robotip")
    @Expose
    private String robotIp;
    @SerializedName("robotport")
    @Expose
    private String robotPort;
    @SerializedName("robottype")
    @Expose
    private String robotType;

    public int getChannel() {
        return this.channel;
    }

    public String getClient() {
        return this.client;
    }

    public int getMode() {
        return this.mode;
    }

    public String toString() {
        return "StartLiveRequestBean{robotType='" + this.robotType + '\'' + ", robotIp='" + this.robotIp + '\'' + ", robotId='" + this.robotId + '\'' + ", robotPort='" + this.robotPort + '\'' + ", nowTime='" + this.nowTime + '\'' + ", channel=" + this.channel + ", client='" + this.client + '\'' + ", mode=" + this.mode + '}';
    }
}
