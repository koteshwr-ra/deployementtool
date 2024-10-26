package com.ciot.realm.db;

import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_TimeBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class TimeBean extends RealmObject implements com_ciot_realm_db_TimeBeanRealmProxyInterface {
    private String beginTime;
    private String endTime;

    public String realmGet$beginTime() {
        return this.beginTime;
    }

    public String realmGet$endTime() {
        return this.endTime;
    }

    public void realmSet$beginTime(String str) {
        this.beginTime = str;
    }

    public void realmSet$endTime(String str) {
        this.endTime = str;
    }

    public TimeBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getBeginTime() {
        return realmGet$beginTime();
    }

    public void setBeginTime(String str) {
        realmSet$beginTime(str);
    }

    public String getEndTime() {
        return realmGet$endTime();
    }

    public void setEndTime(String str) {
        realmSet$endTime(str);
    }
}
