package io.realm;

import com.ciot.realm.db.patrol.Operation;

public interface com_ciot_realm_db_patrol_PointRealmProxyInterface {
    RealmList<Operation> realmGet$oper();

    int realmGet$repeat();

    int realmGet$time();

    String realmGet$transition();

    void realmSet$oper(RealmList<Operation> realmList);

    void realmSet$repeat(int i);

    void realmSet$time(int i);

    void realmSet$transition(String str);
}
