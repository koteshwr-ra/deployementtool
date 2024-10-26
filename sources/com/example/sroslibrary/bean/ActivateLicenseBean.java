package com.example.sroslibrary.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class ActivateLicenseBean {
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
    @SerializedName("mac")
    @Expose(deserialize = true, serialize = true)
    private String mac;

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

    public String getMac() {
        return this.mac;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public String toString() {
        return "ActivateLicenseBean{RobotType=" + this.RobotType + ", RobotIP='" + this.RobotIP + '\'' + ", RobotID='" + this.RobotID + '\'' + ", Nowtime=" + this.Nowtime + ", mac='" + this.mac + '\'' + '}';
    }
}
