package mc.csst.com.selfchassislibrary.chassis;

public class SelfChassisState {
    private static volatile SelfChassisState mInstance;
    private float angularSpeed;
    private int battery;
    private String buildingName;
    private boolean cancelGoalState = false;
    private int charging;
    private int controlStatus;
    private String currentGoalName;
    private boolean downCamDataState = false;
    private volatile String floorName;
    private boolean getPathState = false;
    private boolean getPoseState = false;
    private boolean hardStop = false;
    private boolean isOpenCharge = true;
    private boolean laserDataState = false;
    private float linearSpeed;
    private int lowBatteryValue = 20;
    private volatile boolean mInElevator = false;
    public boolean mRobotIsLowPower = false;
    private int navStatus;
    private int onDock = 0;
    private int patrolStatus;
    private int pointState = 0;
    private boolean sendGoalState = false;
    private boolean setPoseState = false;
    private boolean softStop = false;
    private float targetTheta;
    private float targetX;
    private float targetY;
    private float theta;
    private boolean upCamDataState = false;
    private boolean velocityState = false;
    private float x;
    private float y;

    private SelfChassisState() {
    }

    public static SelfChassisState getInstance() {
        if (mInstance == null) {
            synchronized (SelfChassisState.class) {
                if (mInstance == null) {
                    mInstance = new SelfChassisState();
                }
            }
        }
        return mInstance;
    }

    public int getOnDock() {
        return this.onDock;
    }

    public void setOnDock(int i) {
        this.onDock = i;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float f) {
        this.x = f;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float f) {
        this.y = f;
    }

    public float getTheta() {
        return this.theta;
    }

    public void setTheta(float f) {
        this.theta = f;
    }

    public float getAngularSpeed() {
        return this.angularSpeed;
    }

    public void setAngularSpeed(float f) {
        this.angularSpeed = f;
    }

    public float getLinearSpeed() {
        return this.linearSpeed;
    }

    public void setLinearSpeed(float f) {
        this.linearSpeed = f;
    }

    public int getBattery() {
        return this.battery;
    }

    public void setBattery(int i) {
        this.battery = i;
    }

    public int getCharging() {
        return this.charging;
    }

    public void setCharging(int i) {
        this.charging = i;
    }

    public boolean isVelocityState() {
        return this.velocityState;
    }

    public void setVelocityState(boolean z) {
        this.velocityState = z;
    }

    public boolean isGetPoseState() {
        return this.getPoseState;
    }

    public void setGetPoseState(boolean z) {
        this.getPoseState = z;
    }

    public boolean isLaserDataState() {
        return this.laserDataState;
    }

    public void setLaserDataState(boolean z) {
        this.laserDataState = z;
    }

    public boolean isSendGoalState() {
        return this.sendGoalState;
    }

    public void setSendGoalState(boolean z) {
        this.sendGoalState = z;
    }

    public boolean isCancelGoalState() {
        return this.cancelGoalState;
    }

    public void setCancelGoalState(boolean z) {
        this.cancelGoalState = z;
    }

    public boolean isSetPoseState() {
        return this.setPoseState;
    }

    public void setSetPoseState(boolean z) {
        this.setPoseState = z;
    }

    public boolean isGetPathState() {
        return this.getPathState;
    }

    public void setGetPathState(boolean z) {
        this.getPathState = z;
    }

    public int getPointState() {
        return this.pointState;
    }

    public void setPointState(int i) {
        this.pointState = i;
    }

    public void setNavStatus(int i) {
        this.navStatus = i;
    }

    public int getNavStatus() {
        return this.navStatus;
    }

    public String getBuildingName() {
        return this.buildingName;
    }

    public void setBuildingName(String str) {
        this.buildingName = str;
    }

    public String getFloorName() {
        return this.floorName;
    }

    public void setFloorName(String str) {
        this.floorName = str;
    }

    public boolean isSoftStop() {
        return this.softStop;
    }

    public void setSoftStop(boolean z) {
        this.softStop = z;
    }

    public boolean isHardStop() {
        return this.hardStop;
    }

    public void setHardStop(boolean z) {
        this.hardStop = z;
    }

    public int getPatrolStatus() {
        return this.patrolStatus;
    }

    public void setPatrolStatus(int i) {
        this.patrolStatus = i;
    }

    public int getControlStatus() {
        return this.controlStatus;
    }

    public void setControlStatus(int i) {
        this.controlStatus = i;
    }

    public String getCurrentGoalName() {
        return this.currentGoalName;
    }

    public void setCurrentGoalName(String str) {
        this.currentGoalName = str;
    }

    public float getTargetX() {
        return this.targetX;
    }

    public void setTargetX(float f) {
        this.targetX = f;
    }

    public float getTargetY() {
        return this.targetY;
    }

    public void setTargetY(float f) {
        this.targetY = f;
    }

    public float getTargetTheta() {
        return this.targetTheta;
    }

    public void setTargetTheta(float f) {
        this.targetTheta = f;
    }

    public int getCurFloor() {
        try {
            return Integer.parseInt(this.floorName);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean isCharging() {
        return this.charging == 1;
    }

    public boolean isRobotIsLowPower() {
        return this.mRobotIsLowPower;
    }

    public int getLowBatteryValue() {
        return this.lowBatteryValue;
    }

    public void setLowBatteryValue(int i) {
        this.lowBatteryValue = i;
    }

    public boolean isOpenCharge() {
        return this.isOpenCharge;
    }

    public void setOpenCharge(boolean z) {
        this.isOpenCharge = z;
    }

    public void setRobotIsLowPower(boolean z) {
        this.mRobotIsLowPower = z;
    }

    public boolean isInElevator() {
        return this.mInElevator;
    }

    public void setInElevator(boolean z) {
        this.mInElevator = z;
    }

    public boolean isUpCamDataState() {
        return this.upCamDataState;
    }

    public void setUpCamDataState(boolean z) {
        this.upCamDataState = z;
    }

    public boolean isDownCamDataState() {
        return this.downCamDataState;
    }

    public void setDownCamDataState(boolean z) {
        this.downCamDataState = z;
    }

    public String toString() {
        return "SelfChassisState{buildingName='" + this.buildingName + '\'' + ", floorName='" + this.floorName + '\'' + ", softStop=" + this.softStop + ", hardStop=" + this.hardStop + ", battery=" + this.battery + ", lowBatteryValue=" + this.lowBatteryValue + ", isOpenCharge=" + this.isOpenCharge + ", charging=" + this.charging + ", navStatus=" + this.navStatus + ", patrolStatus=" + this.patrolStatus + ", controlStatus=" + this.controlStatus + ", angularSpeed=" + this.angularSpeed + ", linearSpeed=" + this.linearSpeed + ", currentGoalName='" + this.currentGoalName + '\'' + ", x=" + this.x + ", y=" + this.y + ", theta=" + this.theta + ", targetX=" + this.targetX + ", targetY=" + this.targetY + ", targetTheta=" + this.targetTheta + ", mRobotIsLowPower=" + this.mRobotIsLowPower + ", mInElevator=" + this.mInElevator + ", velocityState=" + this.velocityState + ", getPoseState=" + this.getPoseState + ", laserDataState=" + this.laserDataState + ", sendGoalState=" + this.sendGoalState + ", cancelGoalState=" + this.cancelGoalState + ", setPoseState=" + this.setPoseState + ", getPathState=" + this.getPathState + ", pointState=" + this.pointState + ", onDock=" + this.onDock + ", upCamDataState=" + this.upCamDataState + ", downCamDataState=" + this.downCamDataState + '}';
    }
}
