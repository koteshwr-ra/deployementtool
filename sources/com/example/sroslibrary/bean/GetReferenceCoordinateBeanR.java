package com.example.sroslibrary.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class GetReferenceCoordinateBeanR implements Serializable {
    private static final long serialVersionUID = -2611912587077498662L;
    @SerializedName("angle")
    @Expose
    private float mAngle;
    @SerializedName("mapinfo")
    @Expose
    private String mMapInfo;
    @SerializedName("mapname")
    @Expose
    private String mMapName;
    @SerializedName("reason")
    @Expose
    private String mReason;
    @SerializedName("result")
    @Expose
    private boolean mResult;
    @SerializedName("x")
    @Expose
    private float mX;
    @SerializedName("y")
    @Expose
    private float mY;
    @SerializedName("z")
    @Expose
    private int mZ;

    public boolean isResult() {
        return this.mResult;
    }

    public void setResult(boolean z) {
        this.mResult = z;
    }

    public String getReason() {
        return this.mReason;
    }

    public void setReason(String str) {
        this.mReason = str;
    }

    public float getX() {
        return this.mX;
    }

    public void setX(float f) {
        this.mX = f;
    }

    public float getY() {
        return this.mY;
    }

    public void setY(float f) {
        this.mY = f;
    }

    public String getMapName() {
        return this.mMapName;
    }

    public void setMapName(String str) {
        this.mMapName = str;
    }

    public float getAngle() {
        return this.mAngle;
    }

    public void setAngle(float f) {
        this.mAngle = f;
    }

    public String getMapInfo() {
        return this.mMapInfo;
    }

    public void setMapInfo(String str) {
        this.mMapInfo = str;
    }

    public int getZ() {
        return this.mZ;
    }

    public void setZ(int i) {
        this.mZ = i;
    }

    public String toString() {
        return "GetReferenceCoordinateBeanR{mResult=" + this.mResult + ", mReason='" + this.mReason + '\'' + ", mX=" + this.mX + ", mY=" + this.mY + ", mZ=" + this.mZ + ", mAngle=" + this.mAngle + ", mMapName='" + this.mMapName + '\'' + ", mMapInfo='" + this.mMapInfo + '\'' + '}';
    }
}
