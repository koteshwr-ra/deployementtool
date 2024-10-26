package io.realm;

import com.ciot.realm.db.semantic.ActionBean;

public interface com_ciot_realm_db_semantic_AnswerBeanRealmProxyInterface {
    ActionBean realmGet$action();

    String realmGet$ans();

    RealmList<String> realmGet$buttons();

    RealmList<String> realmGet$list();

    String realmGet$music();

    String realmGet$picture();

    RealmList<String> realmGet$recommendations();

    String realmGet$sessionState();

    String realmGet$showans();

    String realmGet$text();

    String realmGet$title();

    int realmGet$type();

    String realmGet$url();

    String realmGet$video();

    String realmGet$videocover();

    void realmSet$action(ActionBean actionBean);

    void realmSet$ans(String str);

    void realmSet$buttons(RealmList<String> realmList);

    void realmSet$list(RealmList<String> realmList);

    void realmSet$music(String str);

    void realmSet$picture(String str);

    void realmSet$recommendations(RealmList<String> realmList);

    void realmSet$sessionState(String str);

    void realmSet$showans(String str);

    void realmSet$text(String str);

    void realmSet$title(String str);

    void realmSet$type(int i);

    void realmSet$url(String str);

    void realmSet$video(String str);

    void realmSet$videocover(String str);
}
