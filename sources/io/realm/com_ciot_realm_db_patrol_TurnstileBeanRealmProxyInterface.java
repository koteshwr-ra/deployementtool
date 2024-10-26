package io.realm;

import com.ciot.realm.db.PointF;

public interface com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface {
    RealmList<PointF> realmGet$area();

    int realmGet$floor();

    String realmGet$id();

    float realmGet$inAngle();

    PointF realmGet$inPoint();

    float realmGet$outAngle();

    PointF realmGet$outPoint();

    String realmGet$turnstileHost();

    String realmGet$turnstileId();

    int realmGet$turnstilePort();

    int realmGet$turnstileType();

    void realmSet$area(RealmList<PointF> realmList);

    void realmSet$floor(int i);

    void realmSet$id(String str);

    void realmSet$inAngle(float f);

    void realmSet$inPoint(PointF pointF);

    void realmSet$outAngle(float f);

    void realmSet$outPoint(PointF pointF);

    void realmSet$turnstileHost(String str);

    void realmSet$turnstileId(String str);

    void realmSet$turnstilePort(int i);

    void realmSet$turnstileType(int i);
}
