package io.realm;

import com.ciot.realm.db.patrol.Action;
import com.ciot.realm.db.patrol.Operation;
import com.ciot.realm.db.patrol.Place;
import com.ciot.realm.db.patrol.TransitionBean;
import com.ciot.realm.db.patrol.WaitBean;

public interface com_ciot_realm_db_patrol_LineRealmProxyInterface {
    Action realmGet$action();

    String realmGet$description();

    String realmGet$id();

    Action realmGet$onway();

    RealmList<Operation> realmGet$oper();

    Place realmGet$placeInfo();

    int realmGet$repeat();

    String realmGet$taskId();

    TransitionBean realmGet$transition();

    WaitBean realmGet$wait();

    void realmSet$action(Action action);

    void realmSet$description(String str);

    void realmSet$id(String str);

    void realmSet$onway(Action action);

    void realmSet$oper(RealmList<Operation> realmList);

    void realmSet$placeInfo(Place place);

    void realmSet$repeat(int i);

    void realmSet$taskId(String str);

    void realmSet$transition(TransitionBean transitionBean);

    void realmSet$wait(WaitBean waitBean);
}
