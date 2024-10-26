package com.ciot.realm.db.patrol;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_patrol_LineRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Line extends RealmObject implements Serializable, com_ciot_realm_db_patrol_LineRealmProxyInterface {
    private Action action;
    private String description;
    @PrimaryKey
    private String id;
    private Action onway;
    @SerializedName("oper")
    @Expose
    private RealmList<Operation> oper;
    private Place placeInfo;
    @SerializedName("repeat")
    @Expose
    private int repeat;
    private String taskId;
    private TransitionBean transition;
    private WaitBean wait;

    public Action realmGet$action() {
        return this.action;
    }

    public String realmGet$description() {
        return this.description;
    }

    public String realmGet$id() {
        return this.id;
    }

    public Action realmGet$onway() {
        return this.onway;
    }

    public RealmList realmGet$oper() {
        return this.oper;
    }

    public Place realmGet$placeInfo() {
        return this.placeInfo;
    }

    public int realmGet$repeat() {
        return this.repeat;
    }

    public String realmGet$taskId() {
        return this.taskId;
    }

    public TransitionBean realmGet$transition() {
        return this.transition;
    }

    public WaitBean realmGet$wait() {
        return this.wait;
    }

    public void realmSet$action(Action action2) {
        this.action = action2;
    }

    public void realmSet$description(String str) {
        this.description = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$onway(Action action2) {
        this.onway = action2;
    }

    public void realmSet$oper(RealmList realmList) {
        this.oper = realmList;
    }

    public void realmSet$placeInfo(Place place) {
        this.placeInfo = place;
    }

    public void realmSet$repeat(int i) {
        this.repeat = i;
    }

    public void realmSet$taskId(String str) {
        this.taskId = str;
    }

    public void realmSet$transition(TransitionBean transitionBean) {
        this.transition = transitionBean;
    }

    public void realmSet$wait(WaitBean waitBean) {
        this.wait = waitBean;
    }

    public RealmList<Operation> getOper() {
        return realmGet$oper();
    }

    public void setOper(RealmList<Operation> realmList) {
        realmSet$oper(realmList);
    }

    public int getRepeat() {
        return realmGet$repeat();
    }

    public void setRepeat(int i) {
        realmSet$repeat(i);
    }

    public Line(String str, String str2, Place place, Action action2, Action action3, TransitionBean transitionBean, WaitBean waitBean, String str3) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$id(str);
        realmSet$taskId(str2);
        realmSet$placeInfo(place);
        realmSet$onway(action2);
        realmSet$action(action3);
        realmSet$transition(transitionBean);
        realmSet$wait(waitBean);
        realmSet$description(str3);
    }

    public Line() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String str) {
        realmSet$id(str);
    }

    public String getTaskId() {
        return realmGet$taskId();
    }

    public void setTaskId(String str) {
        realmSet$taskId(str);
    }

    public Place getPlaceInfo() {
        return realmGet$placeInfo();
    }

    public void setPlaceInfo(Place place) {
        realmSet$placeInfo(place);
    }

    public Action getOnway() {
        return realmGet$onway();
    }

    public void setOnway(Action action2) {
        realmSet$onway(action2);
    }

    public Action getAction() {
        return realmGet$action();
    }

    public void setAction(Action action2) {
        realmSet$action(action2);
    }

    public TransitionBean getTransition() {
        return realmGet$transition();
    }

    public void setTransition(TransitionBean transitionBean) {
        realmSet$transition(transitionBean);
    }

    public WaitBean getWait() {
        return realmGet$wait();
    }

    public void setWait(WaitBean waitBean) {
        realmSet$wait(waitBean);
    }

    public String getDescription() {
        return realmGet$description();
    }

    public void setDescription(String str) {
        realmSet$description(str);
    }
}
