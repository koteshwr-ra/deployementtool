package com.example.sroslibrary.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;

public class QueryNavigationBean implements Serializable {
    private static final long serialVersionUID = -4330903891815511071L;
    @SerializedName("nowtime")
    @Expose
    private Date mNowTime;
    @SerializedName("robotid")
    @Expose
    private String mRobotID;
    @SerializedName("robotip")
    @Expose
    private String mRobotIP;
    @SerializedName("robottype")
    @Expose
    private int mRobotType;
    @SerializedName("mapname")
    @Expose
    private String mapName;

    public int getRobotType() {
        return this.mRobotType;
    }

    public void setRobotType(int i) {
        this.mRobotType = i;
    }

    public String getRobotIP() {
        return this.mRobotIP;
    }

    public void setRobotIP(String str) {
        this.mRobotIP = str;
    }

    public String getRobotID() {
        return this.mRobotID;
    }

    public void setRobotID(String str) {
        this.mRobotID = str;
    }

    public Date getNowTime() {
        return this.mNowTime;
    }

    public void setNowTime(Date date) {
        this.mNowTime = date;
    }

    public String getMapName() {
        return this.mapName;
    }

    public void setMapName(String str) {
        this.mapName = str;
    }

    public String toString() {
        return "QueryNavigationBean{mRobotType=" + this.mRobotType + ", mRobotIP='" + this.mRobotIP + '\'' + ", mRobotID=" + this.mRobotID + ", mNowTime=" + this.mNowTime + ", mapName='" + this.mapName + '\'' + '}';
    }
}
