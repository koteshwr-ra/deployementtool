package io.realm;

import com.ciot.realm.db.ad.BeginBean;
import com.ciot.realm.db.ad.EndBean;

public interface com_ciot_realm_db_ad_TimesBeanRealmProxyInterface {
    BeginBean realmGet$begin();

    EndBean realmGet$end();

    void realmSet$begin(BeginBean beginBean);

    void realmSet$end(EndBean endBean);
}
