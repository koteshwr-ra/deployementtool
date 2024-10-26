package com.example.sroslibrary.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;

public class AlarmStatusBeanR implements Serializable {
    private static final long serialVersionUID = -7788818584721307420L;
    @SerializedName("alarmcode")
    @Expose
    private int alarmcode;
    @SerializedName("alarmid")
    @Expose
    private String alarmid;
    @SerializedName("alarmtype")
    @Expose
    private int alarmtype;
    @SerializedName("angle")
    @Expose
    private float angle;
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
    @SerializedName("mapinfo")
    @Expose
    private String mapInfo;
    @SerializedName("p")
    @Expose
    private int p;
    @SerializedName("seflag")
    @Expose
    private int seflag;
    @SerializedName("tick")
    @Expose
    private int tick;
    @SerializedName("x")
    @Expose
    private int x;
    @SerializedName("y")
    @Expose
    private int y;
    @SerializedName("z")
    @Expose
    private int z;

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

    public int getTick() {
        return this.tick;
    }

    public void setTick(int i) {
        this.tick = i;
    }

    public int getSeflag() {
        return this.seflag;
    }

    public void setSeflag(int i) {
        this.seflag = i;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int i) {
        this.x = i;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int i) {
        this.y = i;
    }

    public int getP() {
        return this.p;
    }

    public void setP(int i) {
        this.p = i;
    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float f) {
        this.angle = f;
    }

    public String getAlarmid() {
        return this.alarmid;
    }

    public void setAlarmid(String str) {
        this.alarmid = str;
    }

    public int getAlarmtype() {
        return this.alarmtype;
    }

    public void setAlarmtype(int i) {
        this.alarmtype = i;
    }

    public int getAlarmcode() {
        return this.alarmcode;
    }

    public void setAlarmcode(int i) {
        this.alarmcode = i;
    }

    public String getMapInfo() {
        return this.mapInfo;
    }

    public void setMapInfo(String str) {
        this.mapInfo = str;
    }

    public int getZ() {
        return this.z;
    }

    public void setZ(int i) {
        this.z = i;
    }

    public String toString() {
        return "AlarmStatusBeanR{mRobotType=" + this.mRobotType + ", mRobotIP='" + this.mRobotIP + '\'' + ", mRobotID='" + this.mRobotID + '\'' + ", mNowTime=" + this.mNowTime + ", tick=" + this.tick + ", seflag=" + this.seflag + ", x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", angle=" + this.angle + ", mapInfo=" + this.mapInfo + ", p=" + this.p + ", alarmid='" + this.alarmid + '\'' + ", alarmtype=" + this.alarmtype + ", alarmcode=" + this.alarmcode + '}';
    }
}
