package com.ciot.realm.db.patrol;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_patrol_PlaceRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Place extends RealmObject implements Serializable, com_ciot_realm_db_patrol_PlaceRealmProxyInterface {
    private float angle;
    private String mapInfo;
    @PrimaryKey
    private String positionName;
    private int type;
    private float x;
    private float y;
    private int z;

    public float realmGet$angle() {
        return this.angle;
    }

    public String realmGet$mapInfo() {
        return this.mapInfo;
    }

    public String realmGet$positionName() {
        return this.positionName;
    }

    public int realmGet$type() {
        return this.type;
    }

    public float realmGet$x() {
        return this.x;
    }

    public float realmGet$y() {
        return this.y;
    }

    public int realmGet$z() {
        return this.z;
    }

    public void realmSet$angle(float f) {
        this.angle = f;
    }

    public void realmSet$mapInfo(String str) {
        this.mapInfo = str;
    }

    public void realmSet$positionName(String str) {
        this.positionName = str;
    }

    public void realmSet$type(int i) {
        this.type = i;
    }

    public void realmSet$x(float f) {
        this.x = f;
    }

    public void realmSet$y(float f) {
        this.y = f;
    }

    public void realmSet$z(int i) {
        this.z = i;
    }

    public Place() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public Place(float f, String str, String str2, int i, float f2, float f3, int i2) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$angle(f);
        realmSet$mapInfo(str);
        realmSet$positionName(str2);
        realmSet$type(i);
        realmSet$x(f2);
        realmSet$y(f3);
        realmSet$z(i2);
    }

    public float getAngle() {
        return realmGet$angle();
    }

    public void setAngle(float f) {
        realmSet$angle(f);
    }

    public String getMapInfo() {
        return realmGet$mapInfo();
    }

    public void setMapInfo(String str) {
        realmSet$mapInfo(str);
    }

    public String getPositionName() {
        return realmGet$positionName();
    }

    public void setPositionName(String str) {
        realmSet$positionName(str);
    }

    public int getType() {
        return realmGet$type();
    }

    public void setType(int i) {
        realmSet$type(i);
    }

    public float getX() {
        return realmGet$x();
    }

    public void setX(float f) {
        realmSet$x(f);
    }

    public float getY() {
        return realmGet$y();
    }

    public void setY(float f) {
        realmSet$y(f);
    }

    public int getZ() {
        return realmGet$z();
    }

    public void setZ(int i) {
        realmSet$z(i);
    }
}
