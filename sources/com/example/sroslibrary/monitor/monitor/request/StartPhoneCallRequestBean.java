package com.example.sroslibrary.monitor.monitor.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartPhoneCallRequestBean {
    @Expose
    private int channel;
    @Expose
    private String client;
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
    @Expose
    private int source;
    @Expose
    private int stream;

    public int getChannel() {
        return this.channel;
    }

    public String getClient() {
        return this.client;
    }

    public int getStream() {
        return this.stream;
    }

    public int getSource() {
        return this.source;
    }

    public String toString() {
        return "StartPhoneCallRequestBean{robotType='" + this.robotType + '\'' + ", robotIp='" + this.robotIp + '\'' + ", robotId='" + this.robotId + '\'' + ", robotPort='" + this.robotPort + '\'' + ", nowTime='" + this.nowTime + '\'' + ", channel=" + this.channel + ", stream=" + this.stream + ", source=" + this.source + ", client='" + this.client + '\'' + '}';
    }
}
