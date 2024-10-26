package io.realm;

import com.ciot.realm.db.Person;

public interface com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface {
    String realmGet$imageName();

    boolean realmGet$isUpload();

    String realmGet$mediaId();

    Person realmGet$person();

    float realmGet$temperature();

    long realmGet$time();

    String realmGet$url();

    void realmSet$imageName(String str);

    void realmSet$isUpload(boolean z);

    void realmSet$mediaId(String str);

    void realmSet$person(Person person);

    void realmSet$temperature(float f);

    void realmSet$time(long j);

    void realmSet$url(String str);
}
