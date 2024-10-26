package com.ciot.navigation.navigation.task;

import com.ciot.realm.db.Task;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;

public class SetTaskBean implements Serializable {
    public static final int ADD_TASK = 1;
    public static final int CLEAR_TASK = 3;
    public static final int DELETE_TASK = 2;
    public static final int TEMPORARY_TASK = 4;
    private static final long serialVersionUID = 1839964944174298721L;
    @SerializedName("data")
    @Expose
    private Task data;
    @SerializedName("nowtime")
    @Expose
    private Date nowTime;
    @SerializedName("robotid")
    @Expose
    private String robotId;
    @SerializedName("robotip")
    @Expose
    private String robotIp;
    @SerializedName("robottype")
    @Expose
    private int robotType;
    @SerializedName("type")
    @Expose
    private int type;

    public int getRobotType() {
        return this.robotType;
    }

    public void setRobotType(int i) {
        this.robotType = i;
    }

    public String getRobotIp() {
        return this.robotIp;
    }

    public void setRobotIp(String str) {
        this.robotIp = str;
    }

    public String getRobotId() {
        return this.robotId;
    }

    public void setRobotId(String str) {
        this.robotId = str;
    }

    public Date getNowTime() {
        return this.nowTime;
    }

    public void setNowTime(Date date) {
        this.nowTime = date;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public Task getData() {
        return this.data;
    }

    public void setData(Task task) {
        this.data = task;
    }

    public String toString() {
        return "SetTaskBean{robotType=" + this.robotType + ", robotIp='" + this.robotIp + '\'' + ", robotId='" + this.robotId + '\'' + ", nowTime=" + this.nowTime + ", type=" + this.type + ", data=" + this.data + '}';
    }
}
