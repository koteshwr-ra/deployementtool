package com.ciot.realm.db.ad;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_ad_CycleBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class CycleBean extends RealmObject implements com_ciot_realm_db_ad_CycleBeanRealmProxyInterface {
    public static final String DAILY_TYPE = "daily";
    public static final String MONTHLY_TYPE = "monthly";
    public static final String TIME_TYPE = "time";
    public static final String WEEKLY_TYPE = "weekly";
    private RealmList<Long> data;
    private String type;

    public RealmList realmGet$data() {
        return this.data;
    }

    public String realmGet$type() {
        return this.type;
    }

    public void realmSet$data(RealmList realmList) {
        this.data = realmList;
    }

    public void realmSet$type(String str) {
        this.type = str;
    }

    public CycleBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getType() {
        return realmGet$type();
    }

    public void setType(String str) {
        realmSet$type(str);
    }

    public RealmList<Long> getData() {
        return realmGet$data();
    }

    public void setData(RealmList<Long> realmList) {
        realmSet$data(realmList);
    }
}
