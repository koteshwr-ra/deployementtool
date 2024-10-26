package io.realm;

import com.ciot.realm.db.TemRecord;

public interface com_ciot_realm_db_TemUploadRecordRealmProxyInterface {
    String realmGet$code();

    RealmList<TemRecord> realmGet$datas();

    String realmGet$filepath();

    String realmGet$pathname();

    String realmGet$robot();

    long realmGet$uploadtime();

    String realmGet$url();

    void realmSet$code(String str);

    void realmSet$datas(RealmList<TemRecord> realmList);

    void realmSet$filepath(String str);

    void realmSet$pathname(String str);

    void realmSet$robot(String str);

    void realmSet$uploadtime(long j);

    void realmSet$url(String str);
}
