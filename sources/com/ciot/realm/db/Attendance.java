package com.ciot.realm.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_AttendanceRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class Attendance extends RealmObject implements com_ciot_realm_db_AttendanceRealmProxyInterface {
    private long checkTime;
    private String clockInTime;
    private String company;
    private String department;
    private String description;
    private String employeeId;
    private String employeeName;
    @PrimaryKey
    private String id;
    private String idcard;
    private boolean isCommit;
    private RobotBean robot;
    private String staffno;
    private int status;
    private float temperature;

    public long realmGet$checkTime() {
        return this.checkTime;
    }

    public String realmGet$clockInTime() {
        return this.clockInTime;
    }

    public String realmGet$company() {
        return this.company;
    }

    public String realmGet$department() {
        return this.department;
    }

    public String realmGet$description() {
        return this.description;
    }

    public String realmGet$employeeId() {
        return this.employeeId;
    }

    public String realmGet$employeeName() {
        return this.employeeName;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$idcard() {
        return this.idcard;
    }

    public boolean realmGet$isCommit() {
        return this.isCommit;
    }

    public RobotBean realmGet$robot() {
        return this.robot;
    }

    public String realmGet$staffno() {
        return this.staffno;
    }

    public int realmGet$status() {
        return this.status;
    }

    public float realmGet$temperature() {
        return this.temperature;
    }

    public void realmSet$checkTime(long j) {
        this.checkTime = j;
    }

    public void realmSet$clockInTime(String str) {
        this.clockInTime = str;
    }

    public void realmSet$company(String str) {
        this.company = str;
    }

    public void realmSet$department(String str) {
        this.department = str;
    }

    public void realmSet$description(String str) {
        this.description = str;
    }

    public void realmSet$employeeId(String str) {
        this.employeeId = str;
    }

    public void realmSet$employeeName(String str) {
        this.employeeName = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$idcard(String str) {
        this.idcard = str;
    }

    public void realmSet$isCommit(boolean z) {
        this.isCommit = z;
    }

    public void realmSet$robot(RobotBean robotBean) {
        this.robot = robotBean;
    }

    public void realmSet$staffno(String str) {
        this.staffno = str;
    }

    public void realmSet$status(int i) {
        this.status = i;
    }

    public void realmSet$temperature(float f) {
        this.temperature = f;
    }

    public Attendance() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getEmployeeId() {
        return realmGet$employeeId();
    }

    public void setEmployeeId(String str) {
        realmSet$employeeId(str);
    }

    public String getStaffno() {
        return realmGet$staffno();
    }

    public void setStaffno(String str) {
        realmSet$staffno(str);
    }

    public String getCompany() {
        return realmGet$company();
    }

    public void setCompany(String str) {
        realmSet$company(str);
    }

    public String getDepartment() {
        return realmGet$department();
    }

    public void setDepartment(String str) {
        realmSet$department(str);
    }

    public String getEmployeeName() {
        return realmGet$employeeName();
    }

    public void setEmployeeName(String str) {
        realmSet$employeeName(str);
    }

    public RobotBean getRobot() {
        return realmGet$robot();
    }

    public void setRobot(RobotBean robotBean) {
        realmSet$robot(robotBean);
    }

    public long getCheckTime() {
        return realmGet$checkTime();
    }

    public void setCheckTime(long j) {
        realmSet$checkTime(j);
    }

    public String getDescription() {
        return realmGet$description();
    }

    public void setDescription(String str) {
        realmSet$description(str);
    }

    public String getIdcard() {
        return realmGet$idcard();
    }

    public void setIdcard(String str) {
        realmSet$idcard(str);
    }

    public int getStatus() {
        return realmGet$status();
    }

    public void setStatus(int i) {
        realmSet$status(i);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String str) {
        realmSet$id(str);
    }

    public boolean isCommit() {
        return realmGet$isCommit();
    }

    public void setCommit(boolean z) {
        realmSet$isCommit(z);
    }

    public String getClockInTime() {
        return realmGet$clockInTime();
    }

    public void setClockInTime(String str) {
        realmSet$clockInTime(str);
    }

    public float getTemperature() {
        return realmGet$temperature();
    }

    public void setTemperature(float f) {
        realmSet$temperature(f);
    }

    public String toString() {
        return "Attendance{employeeId='" + realmGet$employeeId() + '\'' + ", staffno='" + realmGet$staffno() + '\'' + ", company='" + realmGet$company() + '\'' + ", department='" + realmGet$department() + '\'' + ", employeeName='" + realmGet$employeeName() + '\'' + ", robot=" + realmGet$robot() + ", checkTime=" + realmGet$checkTime() + ", description='" + realmGet$description() + '\'' + ", idcard='" + realmGet$idcard() + '\'' + ", status=" + realmGet$status() + ", id='" + realmGet$id() + '\'' + '}';
    }
}
