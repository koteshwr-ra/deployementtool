package com.ciot.realm.db.ad;

import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_ad_BeginBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class BeginBean extends RealmObject implements com_ciot_realm_db_ad_BeginBeanRealmProxyInterface {
    private String hour;
    private String minute;
    private long minutes;

    public String realmGet$hour() {
        return this.hour;
    }

    public String realmGet$minute() {
        return this.minute;
    }

    public long realmGet$minutes() {
        return this.minutes;
    }

    public void realmSet$hour(String str) {
        this.hour = str;
    }

    public void realmSet$minute(String str) {
        this.minute = str;
    }

    public void realmSet$minutes(long j) {
        this.minutes = j;
    }

    public BeginBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getHour() {
        return realmGet$hour();
    }

    public void setHour(String str) {
        realmSet$hour(str);
    }

    public String getMinute() {
        return realmGet$minute();
    }

    public void setMinute(String str) {
        realmSet$minute(str);
    }

    public long getMinutes() {
        return realmGet$minutes();
    }

    public void setMinutes(long j) {
        realmSet$minutes(j);
    }
}
