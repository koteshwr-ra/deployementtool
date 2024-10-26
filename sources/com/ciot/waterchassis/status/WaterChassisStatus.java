package com.ciot.waterchassis.status;

public class WaterChassisStatus {
    private static volatile WaterChassisStatus sWaterChassisStatus;
    private float mAngle;
    private volatile int mBattCapacity;
    private volatile int mBattState;
    private volatile boolean mCharging = false;
    private double mCoordinateX;
    private double mCoordinateY;
    private String mCurMapName;
    private volatile int mCurrentFloor;
    private volatile boolean mEStop = false;
    private volatile boolean mInElevator = false;
    private volatile int mNavState;
    private double mResolution;
    private int mSpeed = 3;
    private double offsetX;
    private double offsetY;

    public boolean isEStop() {
        return this.mEStop;
    }

    public void setEStop(boolean z) {
        this.mEStop = z;
    }

    public synchronized String getCurMapName() {
        return this.mCurMapName;
    }

    public synchronized void setCurMapName(String str) {
        this.mCurMapName = str;
    }

    public synchronized float getAngle() {
        return (float) (((double) (this.mAngle * 180.0f)) / 3.141592653589793d);
    }

    public synchronized void setAngle(float f) {
        this.mAngle = f;
    }

    public synchronized double getOffsetX() {
        return this.offsetX;
    }

    public synchronized void setOffsetX(double d) {
        this.offsetX = d;
    }

    public synchronized double getOffsetY() {
        return this.offsetY;
    }

    public synchronized void setOffsetY(double d) {
        this.offsetY = d;
    }

    public int getBattCapacity() {
        return this.mBattCapacity;
    }

    public void setBattCapacity(int i) {
        this.mBattCapacity = i;
    }

    public int getBattState() {
        return this.mBattState;
    }

    public void setBattState(int i) {
        this.mBattState = i;
    }

    public synchronized int getCoordinateX() {
        if (this.mResolution != 0.0d) {
            return (int) ((this.mCoordinateX - this.offsetX) / this.mResolution);
        }
        return (int) ((this.mCoordinateX - this.offsetX) / 0.04d);
    }

    public synchronized void setCoordinateX(double d) {
        this.mCoordinateX = d;
    }

    public synchronized int getCoordinateY() {
        if (this.mResolution != 0.0d) {
            return (int) ((this.mCoordinateY - this.offsetY) / this.mResolution);
        }
        return (int) ((this.mCoordinateY - this.offsetY) / 0.04d);
    }

    public synchronized void setCoordinateY(double d) {
        this.mCoordinateY = d;
    }

    public int getSpeed() {
        return this.mSpeed;
    }

    public void setSpeed(int i) {
        this.mSpeed = i;
    }

    public int getNavState() {
        return this.mNavState;
    }

    public void setNavState(int i) {
        this.mNavState = i;
    }

    public static WaterChassisStatus getInstance() {
        if (sWaterChassisStatus == null) {
            synchronized (WaterChassisStatus.class) {
                if (sWaterChassisStatus == null) {
                    sWaterChassisStatus = new WaterChassisStatus();
                }
            }
        }
        return sWaterChassisStatus;
    }

    public boolean isCharging() {
        return this.mCharging;
    }

    public void setCharging(boolean z) {
        if (this.mCharging != z) {
            this.mCharging = z;
        }
    }

    public synchronized void setResolution(double d) {
        this.mResolution = d;
    }

    public synchronized float getResolution() {
        return (float) this.mResolution;
    }

    public void setCurFloor(int i) {
        this.mCurrentFloor = i;
    }

    public int getCurFloor() {
        return this.mCurrentFloor;
    }

    public synchronized int coordinateXConversion2Pixel(double d) {
        if (this.mResolution == 0.0d) {
            return 0;
        }
        return (int) ((d - this.offsetX) / this.mResolution);
    }

    public synchronized int coordinateXConversion2Pixel(double d, double d2) {
        if (this.mResolution == 0.0d) {
            return 0;
        }
        return (int) ((d - d2) / this.mResolution);
    }

    public synchronized int coordinateYConversion2Pixel(double d) {
        if (this.mResolution == 0.0d) {
            return 0;
        }
        return (int) ((d - this.offsetY) / this.mResolution);
    }

    public synchronized int coordinateYConversion2Pixel(double d, double d2) {
        if (this.mResolution == 0.0d) {
            return 0;
        }
        return (int) ((d - d2) / this.mResolution);
    }

    public synchronized double pixelConversion2CoordinateX(int i) {
        return (((double) i) * this.mResolution) + this.offsetX;
    }

    public synchronized double pixelConversion2CoordinateY(int i) {
        return (((double) i) * this.mResolution) + this.offsetY;
    }

    public boolean isInElevator() {
        return this.mInElevator;
    }

    public void setInElevator(boolean z) {
        this.mInElevator = z;
    }
}
