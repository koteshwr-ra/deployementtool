package io.realm;

import com.ciot.realm.db.semantic.CiotResponseBean;

public interface com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface {
    CiotResponseBean realmGet$data();

    String realmGet$question();

    long realmGet$time();

    void realmSet$data(CiotResponseBean ciotResponseBean);

    void realmSet$question(String str);

    void realmSet$time(long j);
}
