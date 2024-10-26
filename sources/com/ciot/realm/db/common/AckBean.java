package com.ciot.realm.db.common;

import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_common_AckBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class AckBean extends RealmObject implements com_ciot_realm_db_common_AckBeanRealmProxyInterface {
    private int time;
    private String user;

    public int realmGet$time() {
        return this.time;
    }

    public String realmGet$user() {
        return this.user;
    }

    public void realmSet$time(int i) {
        this.time = i;
    }

    public void realmSet$user(String str) {
        this.user = str;
    }

    public AckBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public int getTime() {
        return realmGet$time();
    }

    public void setTime(int i) {
        realmSet$time(i);
    }

    public String getUser() {
        return realmGet$user();
    }

    public void setUser(String str) {
        realmSet$user(str);
    }
}
