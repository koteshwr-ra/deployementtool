package com.ciot.realm.db.stat;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_stat_AdStatRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class AdStat extends RealmObject implements com_ciot_realm_db_stat_AdStatRealmProxyInterface {
    private String account;
    private String adId;
    private String adList;
    private String adRes;
    private long duration;
    private String robotId;
    private int screen;
    @PrimaryKey
    private long timestamp;

    public String realmGet$account() {
        return this.account;
    }

    public String realmGet$adId() {
        return this.adId;
    }

    public String realmGet$adList() {
        return this.adList;
    }

    public String realmGet$adRes() {
        return this.adRes;
    }

    public long realmGet$duration() {
        return this.duration;
    }

    public String realmGet$robotId() {
        return this.robotId;
    }

    public int realmGet$screen() {
        return this.screen;
    }

    public long realmGet$timestamp() {
        return this.timestamp;
    }

    public void realmSet$account(String str) {
        this.account = str;
    }

    public void realmSet$adId(String str) {
        this.adId = str;
    }

    public void realmSet$adList(String str) {
        this.adList = str;
    }

    public void realmSet$adRes(String str) {
        this.adRes = str;
    }

    public void realmSet$duration(long j) {
        this.duration = j;
    }

    public void realmSet$robotId(String str) {
        this.robotId = str;
    }

    public void realmSet$screen(int i) {
        this.screen = i;
    }

    public void realmSet$timestamp(long j) {
        this.timestamp = j;
    }

    public AdStat() {
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

    public long getDuration() {
        return realmGet$duration();
    }

    public void setDuration(long j) {
        realmSet$duration(j);
    }

    public String getAdList() {
        return realmGet$adList();
    }

    public void setAdList(String str) {
        realmSet$adList(str);
    }

    public String getAdId() {
        return realmGet$adId();
    }

    public void setAdId(String str) {
        realmSet$adId(str);
    }

    public String getAdRes() {
        return realmGet$adRes();
    }

    public void setAdRes(String str) {
        realmSet$adRes(str);
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

    public int getScreen() {
        return realmGet$screen();
    }

    public void setScreen(int i) {
        realmSet$screen(i);
    }

    public String toString() {
        return "AdStat{robotId='" + realmGet$robotId() + '\'' + ", account='" + realmGet$account() + '\'' + ", duration=" + realmGet$duration() + ", adList='" + realmGet$adList() + '\'' + ", adId='" + realmGet$adId() + '\'' + ", adRes='" + realmGet$adRes() + '\'' + ", timestamp=" + realmGet$timestamp() + ", screen=" + realmGet$screen() + '}';
    }
}
