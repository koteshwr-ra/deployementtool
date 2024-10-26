package com.ciot.realm.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class EventTrackingBean extends RealmObject implements com_ciot_realm_db_EventTrackingBeanRealmProxyInterface {
    @PrimaryKey
    private long begintimestamp;
    private String deviceaddr;
    private String deviceid;
    private String devicetype;
    private long duration;
    private String eventname;
    private String eventtype;
    private String industry;
    private String key;
    private long reporttimestamp;
    private String token;
    private String user;
    private String version;

    public long realmGet$begintimestamp() {
        return this.begintimestamp;
    }

    public String realmGet$deviceaddr() {
        return this.deviceaddr;
    }

    public String realmGet$deviceid() {
        return this.deviceid;
    }

    public String realmGet$devicetype() {
        return this.devicetype;
    }

    public long realmGet$duration() {
        return this.duration;
    }

    public String realmGet$eventname() {
        return this.eventname;
    }

    public String realmGet$eventtype() {
        return this.eventtype;
    }

    public String realmGet$industry() {
        return this.industry;
    }

    public String realmGet$key() {
        return this.key;
    }

    public long realmGet$reporttimestamp() {
        return this.reporttimestamp;
    }

    public String realmGet$token() {
        return this.token;
    }

    public String realmGet$user() {
        return this.user;
    }

    public String realmGet$version() {
        return this.version;
    }

    public void realmSet$begintimestamp(long j) {
        this.begintimestamp = j;
    }

    public void realmSet$deviceaddr(String str) {
        this.deviceaddr = str;
    }

    public void realmSet$deviceid(String str) {
        this.deviceid = str;
    }

    public void realmSet$devicetype(String str) {
        this.devicetype = str;
    }

    public void realmSet$duration(long j) {
        this.duration = j;
    }

    public void realmSet$eventname(String str) {
        this.eventname = str;
    }

    public void realmSet$eventtype(String str) {
        this.eventtype = str;
    }

    public void realmSet$industry(String str) {
        this.industry = str;
    }

    public void realmSet$key(String str) {
        this.key = str;
    }

    public void realmSet$reporttimestamp(long j) {
        this.reporttimestamp = j;
    }

    public void realmSet$token(String str) {
        this.token = str;
    }

    public void realmSet$user(String str) {
        this.user = str;
    }

    public void realmSet$version(String str) {
        this.version = str;
    }

    public EventTrackingBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getDeviceid() {
        return realmGet$deviceid();
    }

    public void setDeviceid(String str) {
        realmSet$deviceid(str);
    }

    public String getToken() {
        return realmGet$token();
    }

    public void setToken(String str) {
        realmSet$token(str);
    }

    public String getKey() {
        return realmGet$key();
    }

    public void setKey(String str) {
        realmSet$key(str);
    }

    public String getUser() {
        return realmGet$user();
    }

    public void setUser(String str) {
        realmSet$user(str);
    }

    public String getIndustry() {
        return realmGet$industry();
    }

    public void setIndustry(String str) {
        realmSet$industry(str);
    }

    public String getDeviceaddr() {
        return realmGet$deviceaddr();
    }

    public void setDeviceaddr(String str) {
        realmSet$deviceaddr(str);
    }

    public String getVersion() {
        return realmGet$version();
    }

    public void setVersion(String str) {
        realmSet$version(str);
    }

    public String getDevicetype() {
        return realmGet$devicetype();
    }

    public void setDevicetype(String str) {
        realmSet$devicetype(str);
    }

    public long getReporttimestamp() {
        return realmGet$reporttimestamp();
    }

    public void setReporttimestamp(long j) {
        realmSet$reporttimestamp(j);
    }

    public String getEventname() {
        return realmGet$eventname();
    }

    public void setEventname(String str) {
        realmSet$eventname(str);
    }

    public String getEventtype() {
        return realmGet$eventtype();
    }

    public void setEventtype(String str) {
        realmSet$eventtype(str);
    }

    public long getDuration() {
        return realmGet$duration();
    }

    public void setDuration(long j) {
        realmSet$duration(j);
    }

    public long getBegintimestamp() {
        return realmGet$begintimestamp();
    }

    public void setBegintimestamp(long j) {
        realmSet$begintimestamp(j);
    }
}
