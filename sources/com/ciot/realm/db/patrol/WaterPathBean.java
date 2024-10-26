package com.ciot.realm.db.patrol;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class WaterPathBean extends RealmObject implements com_ciot_realm_db_patrol_WaterPathBeanRealmProxyInterface {
    @PrimaryKey
    private int id;
    private RealmList<String> mPath;

    public int realmGet$id() {
        return this.id;
    }

    public RealmList realmGet$mPath() {
        return this.mPath;
    }

    public void realmSet$id(int i) {
        this.id = i;
    }

    public void realmSet$mPath(RealmList realmList) {
        this.mPath = realmList;
    }

    public WaterPathBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public WaterPathBean(int i, RealmList<String> realmList) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$id(i);
        realmSet$mPath(realmList);
    }

    public int getId() {
        return realmGet$id();
    }

    public void setId(int i) {
        realmSet$id(i);
    }

    public RealmList<String> getPath() {
        return realmGet$mPath();
    }

    public void setPath(RealmList<String> realmList) {
        realmSet$mPath(realmList);
    }

    public String toString() {
        return "WaterPathBean{id=" + realmGet$id() + ", mPath=" + realmGet$mPath() + '}';
    }
}
