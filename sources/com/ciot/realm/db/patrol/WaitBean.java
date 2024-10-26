package com.ciot.realm.db.patrol;

import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_patrol_WaitBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class WaitBean extends RealmObject implements Serializable, com_ciot_realm_db_patrol_WaitBeanRealmProxyInterface {
    private int data;
    private boolean enable;

    public int realmGet$data() {
        return this.data;
    }

    public boolean realmGet$enable() {
        return this.enable;
    }

    public void realmSet$data(int i) {
        this.data = i;
    }

    public void realmSet$enable(boolean z) {
        this.enable = z;
    }

    public WaitBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public boolean getEnable() {
        return realmGet$enable();
    }

    public void setEnable(boolean z) {
        realmSet$enable(z);
    }

    public int getData() {
        return realmGet$data();
    }

    public void setData(int i) {
        realmSet$data(i);
    }
}
