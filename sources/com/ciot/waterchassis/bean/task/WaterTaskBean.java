package com.ciot.waterchassis.bean.task;

import com.ciot.waterchassis.bean.WaterMarkerBean;

public class WaterTaskBean {
    public static final int PATROL_TASK = 3;
    public static final int SINGLE_CRUISE_TASK = 2;
    public static final int SINGLE_POINT_TASK = 1;
    private Broadcast mBroadcast;
    private boolean mInitialPoint = false;
    private WaterMarkerBean mMarker;
    private Record mRecord;
    private TakePhoto mTakePhoto;
    private int mTaskType = 1;
    private boolean mWarningLight;

    public WaterTaskBean() {
    }

    public WaterTaskBean(boolean z, int i, WaterMarkerBean waterMarkerBean, boolean z2, TakePhoto takePhoto, Record record, Broadcast broadcast) {
        this.mInitialPoint = z;
        this.mTaskType = i;
        this.mMarker = waterMarkerBean;
        this.mWarningLight = z2;
        this.mTakePhoto = takePhoto;
        this.mRecord = record;
        this.mBroadcast = broadcast;
    }

    public boolean isInitialPoint() {
        return this.mInitialPoint;
    }

    public void setInitialPoint(boolean z) {
        this.mInitialPoint = z;
    }

    public int getTaskType() {
        return this.mTaskType;
    }

    public void setTaskType(int i) {
        this.mTaskType = i;
    }

    public WaterMarkerBean getMarker() {
        return this.mMarker;
    }

    public void setMarker(WaterMarkerBean waterMarkerBean) {
        this.mMarker = waterMarkerBean;
    }

    public boolean isWarningLight() {
        return this.mWarningLight;
    }

    public void setWarningLight(boolean z) {
        this.mWarningLight = z;
    }

    public TakePhoto getTakePhoto() {
        return this.mTakePhoto;
    }

    public void setTakePhoto(TakePhoto takePhoto) {
        this.mTakePhoto = takePhoto;
    }

    public Record getRecord() {
        return this.mRecord;
    }

    public void setRecord(Record record) {
        this.mRecord = record;
    }

    public Broadcast getBroadcast() {
        return this.mBroadcast;
    }

    public void setBroadcast(Broadcast broadcast) {
        this.mBroadcast = broadcast;
    }

    public static class TakePhoto {
        private int mIntervalTime;
        private int mNumber;
        private int mPresetPoint;

        public TakePhoto() {
        }

        public TakePhoto(int i, int i2, int i3) {
            this.mPresetPoint = i;
            this.mNumber = i2;
            this.mIntervalTime = i3;
        }

        public int getPresetPoint() {
            return this.mPresetPoint;
        }

        public void setPresetPoint(int i) {
            this.mPresetPoint = i;
        }

        public int getNumber() {
            return this.mNumber;
        }

        public void setNumber(int i) {
            this.mNumber = i;
        }

        public int getIntervalTime() {
            return this.mIntervalTime;
        }

        public void setIntervalTime(int i) {
            this.mIntervalTime = i;
        }

        public String toString() {
            return "拍照:[ 预置点: " + this.mPresetPoint + ", 次数: " + this.mNumber + ", 间隔时间: " + this.mIntervalTime + " ]";
        }
    }

    public static class Record {
        private int mDuration;
        private int mPresetPoint;

        public Record() {
        }

        public Record(int i, int i2) {
            this.mPresetPoint = i;
            this.mDuration = i2;
        }

        public int getPresetPoint() {
            return this.mPresetPoint;
        }

        public void setPresetPoint(int i) {
            this.mPresetPoint = i;
        }

        public int getDuration() {
            return this.mDuration;
        }

        public void setDuration(int i) {
            this.mDuration = i;
        }

        public String toString() {
            return "摄像:[ 预置点: " + this.mPresetPoint + ", 持续时间: " + this.mDuration + " ]";
        }
    }

    public static class Broadcast {
        private String mStatement;
        private int number;

        public Broadcast() {
        }

        public Broadcast(String str, int i) {
            this.mStatement = str;
            this.number = i;
        }

        public String getStatement() {
            return this.mStatement;
        }

        public void setStatement(String str) {
            this.mStatement = str;
        }

        public int getNumber() {
            return this.number;
        }

        public void setNumber(int i) {
            this.number = i;
        }

        public String toString() {
            return "播报:[ 语句: " + this.mStatement + ", 次数: " + this.number + " ]";
        }
    }

    public String toString() {
        int i = this.mTaskType;
        String str = i == 2 ? "单一巡航" : i == 3 ? "巡逻任务" : "单点导航";
        StringBuilder sb = new StringBuilder();
        sb.append("任务:[ 点位: ");
        WaterMarkerBean waterMarkerBean = this.mMarker;
        sb.append(waterMarkerBean != null ? waterMarkerBean.getMarker_name() : null);
        sb.append("，起始点: ");
        sb.append(str);
        sb.append("，任务类型: ");
        sb.append(this.mTaskType);
        sb.append(", 警灯: ");
        sb.append(this.mWarningLight);
        sb.append(", 拍照: ");
        sb.append(this.mTakePhoto);
        sb.append(", 摄像: ");
        sb.append(this.mRecord);
        sb.append(", 播报: ");
        sb.append(this.mBroadcast);
        sb.append(" ]");
        return sb.toString();
    }
}
