package io.realm;

import com.ciot.realm.db.common.Contact;
import com.ciot.realm.db.common.LocationBean;

public interface com_ciot_realm_db_common_CompanyResponseRealmProxyInterface {
    String realmGet$address();

    String realmGet$area();

    Contact realmGet$contact();

    String realmGet$description();

    String realmGet$id();

    LocationBean realmGet$location();

    String realmGet$name();

    String realmGet$pinyin();

    String realmGet$project();

    String realmGet$qrcode();

    double realmGet$x();

    double realmGet$y();

    void realmSet$address(String str);

    void realmSet$area(String str);

    void realmSet$contact(Contact contact);

    void realmSet$description(String str);

    void realmSet$id(String str);

    void realmSet$location(LocationBean locationBean);

    void realmSet$name(String str);

    void realmSet$pinyin(String str);

    void realmSet$project(String str);

    void realmSet$qrcode(String str);

    void realmSet$x(double d);

    void realmSet$y(double d);
}
