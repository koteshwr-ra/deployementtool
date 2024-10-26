package com.ciot.navigation.navigation.task.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ParcelBean implements Serializable {
    private static final long serialVersionUID = 1604925544443156474L;
    @SerializedName("ackcode")
    @Expose
    private int ackCode;
    private boolean filterFrom;
    @SerializedName("from_angle")
    @Expose
    private float fromAngle;
    @SerializedName("from_area")
    @Expose
    private String fromArea;
    @SerializedName("from_description")
    @Expose
    private String fromDescription;
    @SerializedName("from_floor")
    @Expose
    private int fromFloor;
    @SerializedName("from_name")
    @Expose
    private String fromName;
    @SerializedName("from_type")
    @Expose
    private int fromType;
    @SerializedName("from_x")
    @Expose
    private int fromX;
    @SerializedName("from_y")
    @Expose
    private int fromY;
    @SerializedName("nowtime")
    @Expose
    private String nowTime;
    @SerializedName("receiver_name")
    @Expose
    private String receiverName;
    @SerializedName("robotid")
    @Expose
    private String robotId;
    @SerializedName("robotip")
    @Expose
    private String robotIp;
    @SerializedName("robottype")
    @Expose
    private int robotType;
    @SerializedName("sender_name")
    @Expose
    private String senderName;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("to_angle")
    @Expose
    private float toAngle;
    @SerializedName("to_area")
    @Expose
    private String toArea;
    @SerializedName("to_description")
    @Expose
    private String toDescription;
    @SerializedName("to_floor")
    @Expose
    private int toFloor;
    @SerializedName("to_name")
    @Expose
    private String toName;
    @SerializedName("to_type")
    @Expose
    private int toType;
    @SerializedName("to_x")
    @Expose
    private int toX;
    @SerializedName("to_y")
    @Expose
    private int toY;

    private ParcelBean(Builder builder) {
        this.robotType = builder.mRobotType;
        this.robotIp = builder.mRobotIp;
        this.robotId = builder.mRobotId;
        this.nowTime = builder.mNowTime;
        this.ackCode = builder.mAckCode;
        this.senderName = builder.mSenderName;
        this.receiverName = builder.mReceiverName;
        this.fromName = builder.mFromName;
        this.fromArea = builder.mFromArea;
        this.fromType = builder.mFromType;
        this.fromX = builder.mFromX;
        this.fromY = builder.mFromY;
        this.fromAngle = builder.mFromAngle;
        this.fromDescription = builder.mFromDescription;
        this.toName = builder.mToName;
        this.toArea = builder.mToArea;
        this.toType = builder.mToType;
        this.toX = builder.mToX;
        this.toY = builder.mToY;
        this.toAngle = builder.mToAngle;
        this.toDescription = builder.mToDescription;
        this.filterFrom = builder.mFilterFrom;
        this.fromFloor = builder.mFromFloor;
        this.toFloor = builder.mToFloor;
    }

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

    public String getNowTime() {
        return this.nowTime;
    }

    public void setNowTime(String str) {
        this.nowTime = str;
    }

    public int getAckCode() {
        return this.ackCode;
    }

    public void setAckCode(int i) {
        this.ackCode = i;
    }

    public String getSenderName() {
        return this.senderName;
    }

    public void setSenderName(String str) {
        this.senderName = str;
    }

    public String getReceiverName() {
        return this.receiverName;
    }

    public void setReceiverName(String str) {
        this.receiverName = str;
    }

    public String getFromName() {
        return this.fromName;
    }

    public void setFromName(String str) {
        this.fromName = str;
    }

    public String getFromArea() {
        return this.fromArea;
    }

    public void setFromArea(String str) {
        this.fromArea = str;
    }

    public int getFromType() {
        return this.fromType;
    }

    public void setFromType(int i) {
        this.fromType = i;
    }

    public int getFromX() {
        return this.fromX;
    }

    public void setFromX(int i) {
        this.fromX = i;
    }

    public int getFromY() {
        return this.fromY;
    }

    public void setFromY(int i) {
        this.fromY = i;
    }

    public float getFromAngle() {
        return this.fromAngle;
    }

    public void setFromAngle(float f) {
        this.fromAngle = f;
    }

    public String getFromDescription() {
        return this.fromDescription;
    }

    public void setFromDescription(String str) {
        this.fromDescription = str;
    }

    public String getToName() {
        return this.toName;
    }

    public void setToName(String str) {
        this.toName = str;
    }

    public String getToArea() {
        return this.toArea;
    }

    public void setToArea(String str) {
        this.toArea = str;
    }

    public int getToType() {
        return this.toType;
    }

    public void setToType(int i) {
        this.toType = i;
    }

    public int getToX() {
        return this.toX;
    }

    public void setToX(int i) {
        this.toX = i;
    }

    public int getToY() {
        return this.toY;
    }

    public void setToY(int i) {
        this.toY = i;
    }

    public float getToAngle() {
        return this.toAngle;
    }

    public void setToAngle(float f) {
        this.toAngle = f;
    }

    public String getToDescription() {
        return this.toDescription;
    }

    public void setToDescription(String str) {
        this.toDescription = str;
    }

    public boolean isFilterFrom() {
        return this.filterFrom;
    }

    public void setFilterFrom(boolean z) {
        this.filterFrom = z;
    }

    public int getFromFloor() {
        return this.fromFloor;
    }

    public void setFromFloor(int i) {
        this.fromFloor = i;
    }

    public int getToFloor() {
        return this.toFloor;
    }

    public void setToFloor(int i) {
        this.toFloor = i;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public int mAckCode;
        /* access modifiers changed from: private */
        public boolean mFilterFrom;
        /* access modifiers changed from: private */
        public float mFromAngle;
        /* access modifiers changed from: private */
        public String mFromArea;
        /* access modifiers changed from: private */
        public String mFromDescription;
        /* access modifiers changed from: private */
        public int mFromFloor;
        /* access modifiers changed from: private */
        public String mFromName;
        /* access modifiers changed from: private */
        public int mFromType;
        /* access modifiers changed from: private */
        public int mFromX;
        /* access modifiers changed from: private */
        public int mFromY;
        /* access modifiers changed from: private */
        public String mNowTime;
        /* access modifiers changed from: private */
        public String mReceiverName;
        /* access modifiers changed from: private */
        public String mRobotId;
        /* access modifiers changed from: private */
        public String mRobotIp;
        /* access modifiers changed from: private */
        public int mRobotType;
        /* access modifiers changed from: private */
        public String mSenderName;
        /* access modifiers changed from: private */
        public float mToAngle;
        /* access modifiers changed from: private */
        public String mToArea;
        /* access modifiers changed from: private */
        public String mToDescription;
        /* access modifiers changed from: private */
        public int mToFloor;
        /* access modifiers changed from: private */
        public String mToName;
        /* access modifiers changed from: private */
        public int mToType;
        /* access modifiers changed from: private */
        public int mToX;
        /* access modifiers changed from: private */
        public int mToY;

        public Builder robotType(int i) {
            this.mRobotType = i;
            return this;
        }

        public Builder robotIp(String str) {
            this.mRobotIp = str;
            return this;
        }

        public Builder robotId(String str) {
            this.mRobotId = str;
            return this;
        }

        public Builder nowTime(String str) {
            this.mNowTime = str;
            return this;
        }

        public Builder ackCode(int i) {
            this.mAckCode = i;
            return this;
        }

        public Builder senderName(String str) {
            this.mSenderName = str;
            return this;
        }

        public Builder receiverName(String str) {
            this.mReceiverName = str;
            return this;
        }

        public Builder fromName(String str) {
            this.mFromName = str;
            return this;
        }

        public Builder fromArea(String str) {
            this.mFromArea = str;
            return this;
        }

        public Builder fromType(int i) {
            this.mFromType = i;
            return this;
        }

        public Builder fromX(int i) {
            this.mFromX = i;
            return this;
        }

        public Builder fromY(int i) {
            this.mFromY = i;
            return this;
        }

        public Builder fromAngle(float f) {
            this.mFromAngle = f;
            return this;
        }

        public Builder fromDescription(String str) {
            this.mFromDescription = str;
            return this;
        }

        public Builder toName(String str) {
            this.mToName = str;
            return this;
        }

        public Builder toArea(String str) {
            this.mToArea = str;
            return this;
        }

        public Builder toType(int i) {
            this.mToType = i;
            return this;
        }

        public Builder toX(int i) {
            this.mToX = i;
            return this;
        }

        public Builder toY(int i) {
            this.mToY = i;
            return this;
        }

        public Builder toAngle(float f) {
            this.mToAngle = f;
            return this;
        }

        public Builder toDescription(String str) {
            this.mToDescription = str;
            return this;
        }

        public Builder filterFrom(boolean z) {
            this.mFilterFrom = z;
            return this;
        }

        public Builder fromFloor(int i) {
            this.mFromFloor = i;
            return this;
        }

        public Builder toFloor(int i) {
            this.mToFloor = i;
            return this;
        }

        public ParcelBean build() {
            return new ParcelBean(this);
        }
    }
}
