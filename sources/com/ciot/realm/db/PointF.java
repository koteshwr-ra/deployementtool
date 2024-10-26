package com.ciot.realm.db;

import android.graphics.Point;
import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_PointFRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class PointF extends RealmObject implements Serializable, com_ciot_realm_db_PointFRealmProxyInterface {
    private static final long serialVersionUID = 5724680480396034081L;
    public float x;
    public float y;

    public float realmGet$x() {
        return this.x;
    }

    public float realmGet$y() {
        return this.y;
    }

    public void realmSet$x(float f) {
        this.x = f;
    }

    public void realmSet$y(float f) {
        this.y = f;
    }

    public PointF() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public PointF(float f, float f2) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$x(f);
        realmSet$y(f2);
    }

    public PointF(Point point) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$x((float) point.x);
        realmSet$y((float) point.y);
    }

    public final void set(float f, float f2) {
        realmSet$x(f);
        realmSet$y(f2);
    }

    public final void set(PointF pointF) {
        realmSet$x(pointF.realmGet$x());
        realmSet$y(pointF.realmGet$y());
    }

    public final void negate() {
        realmSet$x(-realmGet$x());
        realmSet$y(-realmGet$y());
    }

    public final void offset(float f, float f2) {
        realmSet$x(realmGet$x() + f);
        realmSet$y(realmGet$y() + f2);
    }
}
