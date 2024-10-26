package com.example.sroslibrary.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;

public class CoordinateBean implements Serializable {
    private static final long serialVersionUID = 7936985540939229544L;
    @SerializedName("angle")
    @Expose
    private float mAngle;
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
    @SerializedName("speed")
    @Expose
    private int mSpeed;
    @SerializedName("x")
    @Expose
    private int mX;
    @SerializedName("y")
    @Expose
    private int mY;
    @SerializedName("z")
    @Expose
    private int mZ;
    @SerializedName("mapinfo")
    @Expose
    private String mapInfo;

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

    public int getSpeed() {
        return this.mSpeed;
    }

    public void setSpeed(int i) {
        this.mSpeed = i;
    }

    public int getX() {
        return this.mX;
    }

    public void setX(int i) {
        this.mX = i;
    }

    public int getY() {
        return this.mY;
    }

    public void setY(int i) {
        this.mY = i;
    }

    public float getAngle() {
        return this.mAngle;
    }

    public void setAngle(float f) {
        this.mAngle = f;
    }

    public String getMapInfo() {
        return this.mapInfo;
    }

    public void setMapInfo(String str) {
        this.mapInfo = str;
    }

    public int getZ() {
        return this.mZ;
    }

    public void setZ(int i) {
        this.mZ = i;
    }

    public String toString() {
        return "CoordinateBean{mRobotType=" + this.mRobotType + ", mRobotIP='" + this.mRobotIP + '\'' + ", mRobotID=" + this.mRobotID + ", mNowTime=" + this.mNowTime + ", mSpeed=" + this.mSpeed + ", mX=" + this.mX + ", mY=" + this.mY + ", mZ=" + this.mZ + ", mAngle=" + this.mAngle + ", mapInfo=" + this.mapInfo + '}';
    }
}
