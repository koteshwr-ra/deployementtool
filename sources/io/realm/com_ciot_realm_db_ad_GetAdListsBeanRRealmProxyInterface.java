package io.realm;

import com.ciot.realm.db.ad.AdvertisementsBean;
import com.ciot.realm.db.ad.CycleBean;
import com.ciot.realm.db.ad.TimesBean;

public interface com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface {
    RealmList<AdvertisementsBean> realmGet$advertisements();

    long realmGet$createtime();

    CycleBean realmGet$cycle();

    String realmGet$description();

    String realmGet$id();

    String realmGet$name();

    String realmGet$playingMode();

    int realmGet$screen();

    RealmList<TimesBean> realmGet$times();

    void realmSet$advertisements(RealmList<AdvertisementsBean> realmList);

    void realmSet$createtime(long j);

    void realmSet$cycle(CycleBean cycleBean);

    void realmSet$description(String str);

    void realmSet$id(String str);

    void realmSet$name(String str);

    void realmSet$playingMode(String str);

    void realmSet$screen(int i);

    void realmSet$times(RealmList<TimesBean> realmList);
}
