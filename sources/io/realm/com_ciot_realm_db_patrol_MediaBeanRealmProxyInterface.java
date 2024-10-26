package io.realm;

import com.ciot.realm.db.patrol.Resource;

public interface com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface {
    int realmGet$action();

    RealmList<Resource> realmGet$audio();

    String realmGet$broadcast();

    int realmGet$count();

    String realmGet$id();

    String realmGet$kind();

    RealmList<Resource> realmGet$picture();

    String realmGet$placeid();

    String realmGet$text();

    String realmGet$title();

    String realmGet$type();

    RealmList<Resource> realmGet$video();

    RealmList<Resource> realmGet$videoCover();

    void realmSet$action(int i);

    void realmSet$audio(RealmList<Resource> realmList);

    void realmSet$broadcast(String str);

    void realmSet$count(int i);

    void realmSet$id(String str);

    void realmSet$kind(String str);

    void realmSet$picture(RealmList<Resource> realmList);

    void realmSet$placeid(String str);

    void realmSet$text(String str);

    void realmSet$title(String str);

    void realmSet$type(String str);

    void realmSet$video(RealmList<Resource> realmList);

    void realmSet$videoCover(RealmList<Resource> realmList);
}
