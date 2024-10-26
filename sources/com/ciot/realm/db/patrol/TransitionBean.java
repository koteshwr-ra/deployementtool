package com.ciot.realm.db.patrol;

import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_patrol_TransitionBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class TransitionBean extends RealmObject implements Serializable, com_ciot_realm_db_patrol_TransitionBeanRealmProxyInterface {
    private String data;
    private boolean enable;

    public String realmGet$data() {
        return this.data;
    }

    public boolean realmGet$enable() {
        return this.enable;
    }

    public void realmSet$data(String str) {
        this.data = str;
    }

    public void realmSet$enable(boolean z) {
        this.enable = z;
    }

    public TransitionBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public boolean isEnable() {
        return realmGet$enable();
    }

    public void setEnable(boolean z) {
        realmSet$enable(z);
    }

    public String getData() {
        return realmGet$data();
    }

    public void setData(String str) {
        realmSet$data(str);
    }
}
