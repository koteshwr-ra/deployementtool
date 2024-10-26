package com.ciot.navigation.navigation.task.bean;

public class TransportProgressBean {
    private boolean mCancel;
    private boolean mEnd;
    private String mProgressInfo;

    public String getProgressInfo() {
        return this.mProgressInfo;
    }

    public void setProgressInfo(String str) {
        this.mProgressInfo = str;
    }

    public boolean isEnd() {
        return this.mEnd;
    }

    public void setEnd(boolean z) {
        this.mEnd = z;
    }

    public boolean isCancel() {
        return this.mCancel;
    }

    public void setCancel(boolean z) {
        this.mCancel = z;
    }

    public String toString() {
        return "TransportProgressBean{mProgressInfo='" + this.mProgressInfo + '\'' + ", mEnd=" + this.mEnd + ", mCancel=" + this.mCancel + '}';
    }
}
