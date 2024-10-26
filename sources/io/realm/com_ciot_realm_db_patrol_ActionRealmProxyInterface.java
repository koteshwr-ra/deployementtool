package io.realm;

import com.ciot.realm.db.patrol.MediaBean;

public interface com_ciot_realm_db_patrol_ActionRealmProxyInterface {
    RealmList<String> realmGet$data();

    boolean realmGet$enable();

    RealmList<MediaBean> realmGet$media();

    void realmSet$data(RealmList<String> realmList);

    void realmSet$enable(boolean z);

    void realmSet$media(RealmList<MediaBean> realmList);
}
