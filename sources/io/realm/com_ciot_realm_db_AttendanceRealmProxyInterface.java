package io.realm;

import com.ciot.realm.db.RobotBean;

public interface com_ciot_realm_db_AttendanceRealmProxyInterface {
    long realmGet$checkTime();

    String realmGet$clockInTime();

    String realmGet$company();

    String realmGet$department();

    String realmGet$description();

    String realmGet$employeeId();

    String realmGet$employeeName();

    String realmGet$id();

    String realmGet$idcard();

    boolean realmGet$isCommit();

    RobotBean realmGet$robot();

    String realmGet$staffno();

    int realmGet$status();

    float realmGet$temperature();

    void realmSet$checkTime(long j);

    void realmSet$clockInTime(String str);

    void realmSet$company(String str);

    void realmSet$department(String str);

    void realmSet$description(String str);

    void realmSet$employeeId(String str);

    void realmSet$employeeName(String str);

    void realmSet$id(String str);

    void realmSet$idcard(String str);

    void realmSet$isCommit(boolean z);

    void realmSet$robot(RobotBean robotBean);

    void realmSet$staffno(String str);

    void realmSet$status(int i);

    void realmSet$temperature(float f);
}
