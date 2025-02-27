package com.example.sroslibrary.monitor.monitor.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class StartLocalPhoneCallBean {
    @SerializedName("nowtime")
    @Expose(deserialize = true, serialize = true)
    private Date Nowtime;
    @SerializedName("robotid")
    @Expose(deserialize = true, serialize = true)
    private String RobotID;
    @SerializedName("robotip")
    @Expose(deserialize = true, serialize = true)
    private String RobotIP;
    @SerializedName("robottype")
    @Expose(deserialize = true, serialize = true)
    private int RobotType;
    private int channel;
    private int source;
    @Expose(deserialize = true, serialize = true)
    private int stream;

    public int getRobotType() {
        return this.RobotType;
    }

    public void setRobotType(int i) {
        this.RobotType = i;
    }

    public String getRobotIP() {
        return this.RobotIP;
    }

    public void setRobotIP(String str) {
        this.RobotIP = str;
    }

    public String getRobotID() {
        return this.RobotID;
    }

    public void setRobotID(String str) {
        this.RobotID = str;
    }

    public Date getNowtime() {
        return this.Nowtime;
    }

    public void setNowtime(Date date) {
        this.Nowtime = date;
    }

    public int getStream() {
        return this.stream;
    }

    public void setStream(int i) {
        this.stream = i;
    }

    public int getChannel() {
        return this.channel;
    }

    public void setChannel(int i) {
        this.channel = i;
    }

    public int getSource() {
        return this.source;
    }

    public void setSource(int i) {
        this.source = i;
    }
}
