package com.example.sroslibrary.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;

public class HeartBeatBeanR implements Serializable {
    private static final long serialVersionUID = 1923982573710518193L;
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
    @SerializedName("devstate")
    @Expose(deserialize = true, serialize = true)
    private String devState;
    @SerializedName("version")
    @Expose(deserialize = true, serialize = true)
    private String version;

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

    public String getDevState() {
        return this.devState;
    }

    public void setDevState(String str) {
        this.devState = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String toString() {
        return "HeartBeatBeanR{RobotType=" + this.RobotType + ", RobotIP='" + this.RobotIP + '\'' + ", RobotID='" + this.RobotID + '\'' + ", Nowtime=" + this.Nowtime + ", devState='" + this.devState + '\'' + ", version='" + this.version + '\'' + '}';
    }
}
