package com.ciot.realm.db.stat;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class StatsRecord extends RealmObject implements com_ciot_realm_db_stat_StatsRecordRealmProxyInterface {
    private String account;
    private long begin;
    private long end;
    private String filename;
    private String oss;
    @PrimaryKey
    private String path;
    private String robotId;
    private String type;

    public String realmGet$account() {
        return this.account;
    }

    public long realmGet$begin() {
        return this.begin;
    }

    public long realmGet$end() {
        return this.end;
    }

    public String realmGet$filename() {
        return this.filename;
    }

    public String realmGet$oss() {
        return this.oss;
    }

    public String realmGet$path() {
        return this.path;
    }

    public String realmGet$robotId() {
        return this.robotId;
    }

    public String realmGet$type() {
        return this.type;
    }

    public void realmSet$account(String str) {
        this.account = str;
    }

    public void realmSet$begin(long j) {
        this.begin = j;
    }

    public void realmSet$end(long j) {
        this.end = j;
    }

    public void realmSet$filename(String str) {
        this.filename = str;
    }

    public void realmSet$oss(String str) {
        this.oss = str;
    }

    public void realmSet$path(String str) {
        this.path = str;
    }

    public void realmSet$robotId(String str) {
        this.robotId = str;
    }

    public void realmSet$type(String str) {
        this.type = str;
    }

    public StatsRecord() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public StatsRecord(String str, String str2, String str3, String str4) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$type(str);
        realmSet$robotId(str2);
        realmSet$account(str3);
        realmSet$path(str4);
    }

    public String getType() {
        return realmGet$type();
    }

    public void setType(String str) {
        realmSet$type(str);
    }

    public String getRobotId() {
        return realmGet$robotId();
    }

    public void setRobotId(String str) {
        realmSet$robotId(str);
    }

    public String getAccount() {
        return realmGet$account();
    }

    public void setAccount(String str) {
        realmSet$account(str);
    }

    public String getPath() {
        return realmGet$path();
    }

    public void setPath(String str) {
        realmSet$path(str);
    }

    public String getFilename() {
        return realmGet$filename();
    }

    public void setFilename(String str) {
        realmSet$filename(str);
    }

    public String getOss() {
        return realmGet$oss();
    }

    public void setOss(String str) {
        realmSet$oss(str);
    }

    public long getBegin() {
        return realmGet$begin();
    }

    public void setBegin(long j) {
        realmSet$begin(j);
    }

    public long getEnd() {
        return realmGet$end();
    }

    public void setEnd(long j) {
        realmSet$end(j);
    }

    public String toString() {
        return "StatsRecord{type='" + realmGet$type() + '\'' + ", robotId='" + realmGet$robotId() + '\'' + ", account='" + realmGet$account() + '\'' + ", oss='" + realmGet$oss() + '\'' + ", begin=" + realmGet$begin() + ", end=" + realmGet$end() + ", path='" + realmGet$path() + '\'' + ", filename='" + realmGet$filename() + '\'' + '}';
    }
}
