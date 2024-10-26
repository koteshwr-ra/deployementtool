package com.ciot.realm.db.stat;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_stat_AdWatchStatRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class AdWatchStat extends RealmObject implements com_ciot_realm_db_stat_AdWatchStatRealmProxyInterface {
    private String account;
    private String adId;
    private String adList;
    private String adRes;
    private int inferAgeMax;
    private int inferAgeMin;
    private String inferSex;
    private String robotId;
    private int screen;
    private long standingTimeMs;
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

    public int realmGet$screen() {
        return this.screen;
    }

    public long realmGet$standingTimeMs() {
        return this.standingTimeMs;
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

    public void realmSet$screen(int i) {
        this.screen = i;
    }

    public void realmSet$standingTimeMs(long j) {
        this.standingTimeMs = j;
    }

    public void realmSet$timestamp(long j) {
        this.timestamp = j;
    }

    public AdWatchStat() {
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

    public String getAccount() {
        return realmGet$account();
    }

    public void setAccount(String str) {
        realmSet$account(str);
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

    public String getInferSex() {
        return realmGet$inferSex();
    }

    public void setInferSex(String str) {
        realmSet$inferSex(str);
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

    public long getStandingTimeMs() {
        return realmGet$standingTimeMs();
    }

    public void setStandingTimeMs(long j) {
        realmSet$standingTimeMs(j);
    }

    public int getInferAgeMin() {
        return realmGet$inferAgeMin();
    }

    public void setInferAgeMin(int i) {
        if (realmGet$inferAgeMin() != 0) {
            i = Math.min(realmGet$inferAgeMin(), i);
        }
        realmSet$inferAgeMin(i);
    }

    public int getInferAgeMax() {
        return realmGet$inferAgeMax();
    }

    public void setInferAgeMax(int i) {
        realmSet$inferAgeMax(Math.max(realmGet$inferAgeMax(), i));
    }

    public int getScreen() {
        return realmGet$screen();
    }

    public void setScreen(int i) {
        realmSet$screen(i);
    }

    public String toString() {
        return "AdWatchStat{robotId='" + realmGet$robotId() + '\'' + ", account='" + realmGet$account() + '\'' + ", adList='" + realmGet$adList() + '\'' + ", adId='" + realmGet$adId() + '\'' + ", inferSex='" + realmGet$inferSex() + '\'' + ", adRes='" + realmGet$adRes() + '\'' + ", timestamp=" + realmGet$timestamp() + ", standingTimeMs=" + realmGet$standingTimeMs() + ", inferAgeMin=" + realmGet$inferAgeMin() + ", inferAgeMax=" + realmGet$inferAgeMax() + ", screen=" + realmGet$screen() + '}';
    }
}
