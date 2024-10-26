package io.realm;

import com.ciot.realm.db.patrol.Operation;

public interface com_ciot_realm_db_patrol_ProcessRealmProxyInterface {
    RealmList<Operation> realmGet$oper();

    int realmGet$repeat();

    void realmSet$oper(RealmList<Operation> realmList);

    void realmSet$repeat(int i);
}
