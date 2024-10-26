package com.example.sroslibrary.monitor.monitor.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartPlaybackRequestBean {
    @Expose
    private long begin;
    @Expose
    private int channel;
    @Expose
    private String client;
    @Expose
    private long end;
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

    public long getBegin() {
        return this.begin;
    }

    public long getEnd() {
        return this.end;
    }

    public String toString() {
        return "StartVideoRequestBean{robottype='" + this.robotType + '\'' + ", robotip='" + this.robotIp + '\'' + ", robotid='" + this.robotId + '\'' + ", robotport='" + this.robotPort + '\'' + ", nowtime='" + this.nowTime + '\'' + ", channel=" + this.channel + ", client='" + this.client + '\'' + '}';
    }
}
