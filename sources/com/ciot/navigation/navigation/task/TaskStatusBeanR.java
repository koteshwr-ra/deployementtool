package com.ciot.navigation.navigation.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;

public class TaskStatusBeanR implements Serializable {
    public static final int COMPLETE = 2;
    public static final int GUIDE_TASK_TYPE = 3;
    public static final int OUT_ALARM_TASK_TYPE = 2;
    public static final int PARCEL_TASK_TYPE = 5;
    public static final int RECEIVE_VISITOR_TASK_TYPE = 4;
    public static final int ROBOT_TASK_TYPE = 1;
    public static final int RUNNING = 0;
    public static final int START = 1;
    public static final int STOP = 3;
    private static final long serialVersionUID = 2886263288470603266L;
    @SerializedName("cruisename")
    @Expose
    private String cruiseName;
    @SerializedName("flag")
    @Expose
    private int mFlag;
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
    @SerializedName("taskid")
    @Expose
    private String mTaskId;
    @SerializedName("tasknodeid")
    @Expose
    private String mTaskNodeID;
    @SerializedName("type")
    @Expose
    private int mType;

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

    public String getTaskNodeID() {
        return this.mTaskNodeID;
    }

    public void setTaskNodeID(String str) {
        this.mTaskNodeID = str;
    }

    public int getFlag() {
        return this.mFlag;
    }

    public void setFlag(int i) {
        this.mFlag = i;
    }

    public String getCruiseName() {
        return this.cruiseName;
    }

    public void setCruiseName(String str) {
        this.cruiseName = str;
    }

    public String getTaskId() {
        return this.mTaskId;
    }

    public void setTaskId(String str) {
        this.mTaskId = str;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public String toString() {
        return "TaskStatusBeanR{mRobotType=" + this.mRobotType + ", mRobotIP='" + this.mRobotIP + '\'' + ", mRobotID=" + this.mRobotID + ", mNowTime=" + this.mNowTime + ", mTaskId=" + this.mTaskId + ", mTaskNodeID=" + this.mTaskNodeID + ", mFlag=" + this.mFlag + ", cruiseName=" + this.cruiseName + '}';
    }
}
