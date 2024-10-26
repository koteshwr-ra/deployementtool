package io.realm;

import com.ciot.realm.db.patrol.Point;
import com.ciot.realm.db.patrol.Process;

public interface com_ciot_realm_db_ChildTaskRealmProxyInterface {
    float realmGet$angle();

    String realmGet$firstSentence();

    boolean realmGet$isPathEdited();

    boolean realmGet$isPointEdited();

    Boolean realmGet$isSelected();

    String realmGet$mapinfo();

    int realmGet$p_type();

    Point realmGet$point();

    String realmGet$pointName();

    Process realmGet$process();

    String realmGet$taskNodeID();

    String realmGet$transition();

    boolean realmGet$waitPlayMedia();

    float realmGet$x();

    float realmGet$y();

    int realmGet$z();

    void realmSet$angle(float f);

    void realmSet$firstSentence(String str);

    void realmSet$isPathEdited(boolean z);

    void realmSet$isPointEdited(boolean z);

    void realmSet$isSelected(Boolean bool);

    void realmSet$mapinfo(String str);

    void realmSet$p_type(int i);

    void realmSet$point(Point point);

    void realmSet$pointName(String str);

    void realmSet$process(Process process);

    void realmSet$taskNodeID(String str);

    void realmSet$transition(String str);

    void realmSet$waitPlayMedia(boolean z);

    void realmSet$x(float f);

    void realmSet$y(float f);

    void realmSet$z(int i);
}
