package io.realm;

import com.ciot.realm.db.ad.HorseRaceLampsBean;
import com.ciot.realm.db.ad.ResourcesBean;

public interface com_ciot_realm_db_ad_AdvertisementsBeanRealmProxyInterface {
    int realmGet$begin();

    int realmGet$createtime();

    String realmGet$description();

    int realmGet$end();

    RealmList<HorseRaceLampsBean> realmGet$horseRaceLamps();

    String realmGet$id();

    String realmGet$kind();

    String realmGet$name();

    RealmList<ResourcesBean> realmGet$resources();

    void realmSet$begin(int i);

    void realmSet$createtime(int i);

    void realmSet$description(String str);

    void realmSet$end(int i);

    void realmSet$horseRaceLamps(RealmList<HorseRaceLampsBean> realmList);

    void realmSet$id(String str);

    void realmSet$kind(String str);

    void realmSet$name(String str);

    void realmSet$resources(RealmList<ResourcesBean> realmList);
}
