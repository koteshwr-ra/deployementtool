package io.realm;

import com.ciot.realm.db.semantic.AnswerBean;

public interface com_ciot_realm_db_semantic_CiotResponseBeanRealmProxyInterface {
    int realmGet$code();

    AnswerBean realmGet$data();

    String realmGet$message();

    int realmGet$ttl();

    void realmSet$code(int i);

    void realmSet$data(AnswerBean answerBean);

    void realmSet$message(String str);

    void realmSet$ttl(int i);
}
