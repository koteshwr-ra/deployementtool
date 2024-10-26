package io.realm;

import com.ciot.realm.db.TimeBean;

public interface com_ciot_realm_db_HotelActivitesBeanRealmProxyInterface {
    String realmGet$address();

    long realmGet$beginDate();

    String realmGet$description();

    long realmGet$endDate();

    boolean realmGet$hotspot();

    String realmGet$id();

    String realmGet$imgUrl();

    boolean realmGet$isChecked();

    String realmGet$name();

    String realmGet$peopleNumber();

    String realmGet$price();

    RealmList<TimeBean> realmGet$times();

    void realmSet$address(String str);

    void realmSet$beginDate(long j);

    void realmSet$description(String str);

    void realmSet$endDate(long j);

    void realmSet$hotspot(boolean z);

    void realmSet$id(String str);

    void realmSet$imgUrl(String str);

    void realmSet$isChecked(boolean z);

    void realmSet$name(String str);

    void realmSet$peopleNumber(String str);

    void realmSet$price(String str);

    void realmSet$times(RealmList<TimeBean> realmList);
}
