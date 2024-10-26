package com.ciot.realm.db.patrol;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class FloorOriginBean extends RealmObject implements com_ciot_realm_db_patrol_FloorOriginBeanRealmProxyInterface {
    @PrimaryKey
    private String floor;
    private double originX;
    private double originY;

    public String realmGet$floor() {
        return this.floor;
    }

    public double realmGet$originX() {
        return this.originX;
    }

    public double realmGet$originY() {
        return this.originY;
    }

    public void realmSet$floor(String str) {
        this.floor = str;
    }

    public void realmSet$originX(double d) {
        this.originX = d;
    }

    public void realmSet$originY(double d) {
        this.originY = d;
    }

    public FloorOriginBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public FloorOriginBean(String str, double d, double d2) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$floor(str);
        realmSet$originX(d);
        realmSet$originY(d2);
    }

    public String getFloor() {
        return realmGet$floor();
    }

    public void setFloor(String str) {
        realmSet$floor(str);
    }

    public double getOriginX() {
        return realmGet$originX();
    }

    public void setOriginX(double d) {
        realmSet$originX(d);
    }

    public double getOriginY() {
        return realmGet$originY();
    }

    public void setOriginY(double d) {
        realmSet$originY(d);
    }

    public String toString() {
        return "FloorOriginBean{floor='" + realmGet$floor() + '\'' + ", originX=" + realmGet$originX() + ", originY=" + realmGet$originY() + '}';
    }
}
