package io.realm;

import com.ciot.realm.db.Person;

public interface com_ciot_realm_db_TemRecordRealmProxyInterface {
    long realmGet$createtime();

    String realmGet$pathname();

    Person realmGet$person();

    String realmGet$staffno();

    String realmGet$status();

    float realmGet$temperature();

    void realmSet$createtime(long j);

    void realmSet$pathname(String str);

    void realmSet$person(Person person);

    void realmSet$staffno(String str);

    void realmSet$status(String str);

    void realmSet$temperature(float f);
}
