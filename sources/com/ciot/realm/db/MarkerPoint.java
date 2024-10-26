package com.ciot.realm.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_MarkerPointRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class MarkerPoint extends RealmObject implements Serializable, com_ciot_realm_db_MarkerPointRealmProxyInterface {
    private static final long serialVersionUID = -5730603080486122051L;
    @SerializedName("angle")
    @Expose
    private float angle;
    @SerializedName("mapinfo")
    @Expose
    private String mapInfo;
    @SerializedName("mapname")
    @Expose
    private String mapName;
    @SerializedName("positionname")
    @PrimaryKey
    @Expose
    private String positionName;
    @SerializedName("x")
    @Expose
    private int x;
    @SerializedName("y")
    @Expose
    private int y;
    @SerializedName("z")
    @Expose
    private int z;

    public float realmGet$angle() {
        return this.angle;
    }

    public String realmGet$mapInfo() {
        return this.mapInfo;
    }

    public String realmGet$mapName() {
        return this.mapName;
    }

    public String realmGet$positionName() {
        return this.positionName;
    }

    public int realmGet$x() {
        return this.x;
    }

    public int realmGet$y() {
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

    public void realmSet$mapName(String str) {
        this.mapName = str;
    }

    public void realmSet$positionName(String str) {
        this.positionName = str;
    }

    public void realmSet$x(int i) {
        this.x = i;
    }

    public void realmSet$y(int i) {
        this.y = i;
    }

    public void realmSet$z(int i) {
        this.z = i;
    }

    public MarkerPoint() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getPositionName() {
        return realmGet$positionName();
    }

    public void setPositionName(String str) {
        realmSet$positionName(str);
    }

    public int getX() {
        return realmGet$x();
    }

    public void setX(int i) {
        realmSet$x(i);
    }

    public int getY() {
        return realmGet$y();
    }

    public void setY(int i) {
        realmSet$y(i);
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

    public String getMapName() {
        return realmGet$mapName();
    }

    public void setMapName(String str) {
        realmSet$mapName(str);
    }

    public int getZ() {
        return realmGet$z();
    }

    public void setZ(int i) {
        realmSet$z(i);
    }

    public String toString() {
        return "MarkerPoint{positionName='" + realmGet$positionName() + '\'' + ", x=" + realmGet$x() + ", y=" + realmGet$y() + ", z=" + realmGet$z() + ", angle=" + realmGet$angle() + ", mapInfo=" + realmGet$mapInfo() + ", mapName='" + realmGet$mapName() + '\'' + '}';
    }
}
