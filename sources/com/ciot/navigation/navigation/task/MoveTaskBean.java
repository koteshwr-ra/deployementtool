package com.ciot.navigation.navigation.task;

public class MoveTaskBean {
    private float angle;
    private int floor;
    private String markerName;
    private boolean outTurnstile;
    private int pointType;
    private float x;
    private float y;

    private MoveTaskBean(Builder builder) {
        this.markerName = builder.mMarkerName;
        this.pointType = builder.mPointType;
        this.x = builder.mX;
        this.y = builder.mY;
        this.floor = builder.mFloor;
        this.angle = builder.mAngle;
        this.outTurnstile = builder.mOutTurnstile;
    }

    public String getMarkerName() {
        return this.markerName;
    }

    public int getPointType() {
        return this.pointType;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getAngle() {
        return this.angle;
    }

    public int getFloor() {
        return this.floor;
    }

    public boolean isOutTurnstile() {
        return this.outTurnstile;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public float mAngle;
        /* access modifiers changed from: private */
        public int mFloor;
        /* access modifiers changed from: private */
        public String mMarkerName;
        /* access modifiers changed from: private */
        public boolean mOutTurnstile;
        /* access modifiers changed from: private */
        public int mPointType;
        /* access modifiers changed from: private */
        public float mX;
        /* access modifiers changed from: private */
        public float mY;

        public Builder markerName(String str) {
            this.mMarkerName = str;
            return this;
        }

        public Builder pointType(int i) {
            this.mPointType = i;
            return this;
        }

        public Builder x(float f) {
            this.mX = f;
            return this;
        }

        public Builder y(float f) {
            this.mY = f;
            return this;
        }

        public Builder floor(int i) {
            this.mFloor = i;
            return this;
        }

        public Builder angle(float f) {
            this.mAngle = f;
            return this;
        }

        public Builder outTurnstile(boolean z) {
            this.mOutTurnstile = z;
            return this;
        }

        public MoveTaskBean build() {
            return new MoveTaskBean(this);
        }
    }

    public String toString() {
        return "MoveTaskBean{markerName='" + this.markerName + '\'' + ", pointType=" + this.pointType + ", x=" + this.x + ", y=" + this.y + ", angle=" + this.angle + ", floor=" + this.floor + ", outTurnstile=" + this.outTurnstile + '}';
    }
}
