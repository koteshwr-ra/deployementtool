package com.ciot.waterchassis.bean;

public class WaterNavigationStatusBean {
    private boolean mArriveMarker;
    private boolean mComplete = true;
    private int mMarkerIndex;

    public int getMarkerIndex() {
        return this.mMarkerIndex;
    }

    public void setMarkerIndex(int i) {
        this.mMarkerIndex = i;
    }

    public boolean isArriveMarker() {
        return this.mArriveMarker;
    }

    public void setArriveMarker(boolean z) {
        this.mArriveMarker = z;
    }

    public boolean isComplete() {
        return this.mComplete;
    }

    public void setComplete(boolean z) {
        this.mComplete = z;
    }

    public String toString() {
        return "WaterNavigationStatusBean{mMarkerIndex=" + this.mMarkerIndex + ", mArriveMarker=" + this.mArriveMarker + ", mComplete=" + this.mComplete + '}';
    }
}
