package com.ciot.realm.db.patrol;

import com.ciot.realm.db.PointF;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class TurnstileBean extends RealmObject implements Serializable, com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface {
    private static final long serialVersionUID = -7565571071256796192L;
    private RealmList<PointF> area;
    private int floor;
    @PrimaryKey
    private String id;
    private float inAngle;
    private PointF inPoint;
    private float outAngle;
    private PointF outPoint;
    private String turnstileHost;
    private String turnstileId;
    private int turnstilePort;
    private int turnstileType;

    public RealmList realmGet$area() {
        return this.area;
    }

    public int realmGet$floor() {
        return this.floor;
    }

    public String realmGet$id() {
        return this.id;
    }

    public float realmGet$inAngle() {
        return this.inAngle;
    }

    public PointF realmGet$inPoint() {
        return this.inPoint;
    }

    public float realmGet$outAngle() {
        return this.outAngle;
    }

    public PointF realmGet$outPoint() {
        return this.outPoint;
    }

    public String realmGet$turnstileHost() {
        return this.turnstileHost;
    }

    public String realmGet$turnstileId() {
        return this.turnstileId;
    }

    public int realmGet$turnstilePort() {
        return this.turnstilePort;
    }

    public int realmGet$turnstileType() {
        return this.turnstileType;
    }

    public void realmSet$area(RealmList realmList) {
        this.area = realmList;
    }

    public void realmSet$floor(int i) {
        this.floor = i;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$inAngle(float f) {
        this.inAngle = f;
    }

    public void realmSet$inPoint(PointF pointF) {
        this.inPoint = pointF;
    }

    public void realmSet$outAngle(float f) {
        this.outAngle = f;
    }

    public void realmSet$outPoint(PointF pointF) {
        this.outPoint = pointF;
    }

    public void realmSet$turnstileHost(String str) {
        this.turnstileHost = str;
    }

    public void realmSet$turnstileId(String str) {
        this.turnstileId = str;
    }

    public void realmSet$turnstilePort(int i) {
        this.turnstilePort = i;
    }

    public void realmSet$turnstileType(int i) {
        this.turnstileType = i;
    }

    public TurnstileBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$turnstileType(1);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String str) {
        realmSet$id(str);
    }

    public RealmList<PointF> getArea() {
        return realmGet$area();
    }

    public int getFloor() {
        return realmGet$floor();
    }

    public PointF getInPoint() {
        return realmGet$inPoint();
    }

    public float getInAngle() {
        return realmGet$inAngle();
    }

    public PointF getOutPoint() {
        return realmGet$outPoint();
    }

    public float getOutAngle() {
        return realmGet$outAngle();
    }

    public String getTurnstileHost() {
        return realmGet$turnstileHost();
    }

    public String getTurnstileId() {
        return realmGet$turnstileId();
    }

    public int getTurnstilePort() {
        return realmGet$turnstilePort();
    }

    public void setArea(RealmList<PointF> realmList) {
        realmSet$area(realmList);
    }

    public void setFloor(int i) {
        realmSet$floor(i);
    }

    public void setInPoint(PointF pointF) {
        realmSet$inPoint(pointF);
    }

    public void setInAngle(float f) {
        realmSet$inAngle(f);
    }

    public void setOutPoint(PointF pointF) {
        realmSet$outPoint(pointF);
    }

    public void setOutAngle(float f) {
        realmSet$outAngle(f);
    }

    public void setTurnstileHost(String str) {
        realmSet$turnstileHost(str);
    }

    public void setTurnstileId(String str) {
        realmSet$turnstileId(str);
    }

    public void setTurnstilePort(int i) {
        realmSet$turnstilePort(i);
    }

    public int getTurnstileType() {
        return realmGet$turnstileType();
    }

    public void setTurnstileType(int i) {
        realmSet$turnstileType(i);
    }
}
