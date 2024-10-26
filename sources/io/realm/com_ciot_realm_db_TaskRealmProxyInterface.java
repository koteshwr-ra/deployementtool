package io.realm;

import com.ciot.realm.db.ChildTask;
import com.ciot.realm.db.MusicBean;
import com.ciot.realm.db.patrol.PathBean;

public interface com_ciot_realm_db_TaskRealmProxyInterface {
    int realmGet$flag();

    Boolean realmGet$isExpand();

    RealmList<ChildTask> realmGet$mTaskdata();

    MusicBean realmGet$music();

    int realmGet$oneTaskStatus();

    RealmList<PathBean> realmGet$pathBeanList();

    String realmGet$prologue();

    String realmGet$startTime();

    long realmGet$statisticRunEndTime();

    float realmGet$statisticRunKm();

    long realmGet$statisticRunStartTime();

    String realmGet$taskName();

    boolean realmGet$taskStatus();

    int realmGet$taskType();

    String realmGet$taskid();

    String realmGet$weekly();

    void realmSet$flag(int i);

    void realmSet$isExpand(Boolean bool);

    void realmSet$mTaskdata(RealmList<ChildTask> realmList);

    void realmSet$music(MusicBean musicBean);

    void realmSet$oneTaskStatus(int i);

    void realmSet$pathBeanList(RealmList<PathBean> realmList);

    void realmSet$prologue(String str);

    void realmSet$startTime(String str);

    void realmSet$statisticRunEndTime(long j);

    void realmSet$statisticRunKm(float f);

    void realmSet$statisticRunStartTime(long j);

    void realmSet$taskName(String str);

    void realmSet$taskStatus(boolean z);

    void realmSet$taskType(int i);

    void realmSet$taskid(String str);

    void realmSet$weekly(String str);
}
