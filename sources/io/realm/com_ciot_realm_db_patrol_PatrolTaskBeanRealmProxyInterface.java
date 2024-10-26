package io.realm;

import com.ciot.realm.db.patrol.Line;
import com.ciot.realm.db.patrol.Message;
import com.ciot.realm.db.patrol.Place;
import com.ciot.realm.db.patrol.Plan;
import com.ciot.realm.db.patrol.Resource;
import com.ciot.realm.db.patrol.TransitionBean;

public interface com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface {
    String realmGet$area();

    Place realmGet$backplace();

    String realmGet$brief();

    int realmGet$count();

    String realmGet$id();

    RealmList<Line> realmGet$lines();

    String realmGet$map();

    Message realmGet$message();

    String realmGet$name();

    Plan realmGet$plan();

    TransitionBean realmGet$prologud();

    Resource realmGet$resource();

    String realmGet$robotid();

    void realmSet$area(String str);

    void realmSet$backplace(Place place);

    void realmSet$brief(String str);

    void realmSet$count(int i);

    void realmSet$id(String str);

    void realmSet$lines(RealmList<Line> realmList);

    void realmSet$map(String str);

    void realmSet$message(Message message);

    void realmSet$name(String str);

    void realmSet$plan(Plan plan);

    void realmSet$prologud(TransitionBean transitionBean);

    void realmSet$resource(Resource resource);

    void realmSet$robotid(String str);
}
