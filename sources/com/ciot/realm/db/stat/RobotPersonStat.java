package com.ciot.realm.db.stat;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class RobotPersonStat extends RealmObject implements com_ciot_realm_db_stat_RobotPersonStatRealmProxyInterface {
    private String account;
    private int inferAgeMax;
    private int inferAgeMin;
    private String inferSex;
    private String robotId;
    @PrimaryKey
    private long timestamp;
    private int traffic;

    public String realmGet$account() {
        return this.account;
    }

    public int realmGet$inferAgeMax() {
        return this.inferAgeMax;
    }

    public int realmGet$inferAgeMin() {
        return this.inferAgeMin;
    }

    public String realmGet$inferSex() {
        return this.inferSex;
    }

    public String realmGet$robotId() {
        return this.robotId;
    }

    public long realmGet$timestamp() {
        return this.timestamp;
    }

    public int realmGet$traffic() {
        return this.traffic;
    }

    public void realmSet$account(String str) {
        this.account = str;
    }

    public void realmSet$inferAgeMax(int i) {
        this.inferAgeMax = i;
    }

    public void realmSet$inferAgeMin(int i) {
        this.inferAgeMin = i;
    }

    public void realmSet$inferSex(String str) {
        this.inferSex = str;
    }

    public void realmSet$robotId(String str) {
        this.robotId = str;
    }

    public void realmSet$timestamp(long j) {
        this.timestamp = j;
    }

    public void realmSet$traffic(int i) {
        this.traffic = i;
    }

    public RobotPersonStat() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getRobotId() {
        return realmGet$robotId();
    }

    public void setRobotId(String str) {
        realmSet$robotId(str);
    }

    public int getTraffic() {
        return realmGet$traffic();
    }

    public void setTraffic(int i) {
        realmSet$traffic(i);
    }

    public long getTimestamp() {
        return realmGet$timestamp();
    }

    public void setTimestamp(long j) {
        realmSet$timestamp(j);
    }

    public String getAccount() {
        return realmGet$account();
    }

    public void setAccount(String str) {
        realmSet$account(str);
    }

    public String getInferSex() {
        return realmGet$inferSex();
    }

    public void setInferSex(String str) {
        realmSet$inferSex(str);
    }

    public int getInferAgeMax() {
        return realmGet$inferAgeMax();
    }

    public void setInferAgeMax(int i) {
        realmSet$inferAgeMax(i);
    }

    public int getInferAgeMin() {
        return realmGet$inferAgeMin();
    }

    public void setInferAgeMin(int i) {
        realmSet$inferAgeMin(i);
    }

    public String toString() {
        return "RobotPersonStat{robotId='" + realmGet$robotId() + '\'' + ", account='" + realmGet$account() + '\'' + ", traffic=" + realmGet$traffic() + ", timestamp=" + realmGet$timestamp() + ", inferSex='" + realmGet$inferSex() + '\'' + ", inferAgeMax=" + realmGet$inferAgeMax() + ", inferAgeMin=" + realmGet$inferAgeMin() + '}';
    }
}
