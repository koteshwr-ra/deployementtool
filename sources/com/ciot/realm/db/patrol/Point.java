package com.ciot.realm.db.patrol;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_patrol_PointRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Point extends RealmObject implements Serializable, com_ciot_realm_db_patrol_PointRealmProxyInterface {
    private static final long serialVersionUID = 7781589580517330191L;
    @SerializedName("oper")
    @Expose
    private RealmList<Operation> oper;
    @SerializedName("repeat")
    @Expose
    private int repeat;
    @SerializedName("time")
    @Expose
    private int time;
    @SerializedName("transition")
    @Expose
    private String transition;

    public RealmList realmGet$oper() {
        return this.oper;
    }

    public int realmGet$repeat() {
        return this.repeat;
    }

    public int realmGet$time() {
        return this.time;
    }

    public String realmGet$transition() {
        return this.transition;
    }

    public void realmSet$oper(RealmList realmList) {
        this.oper = realmList;
    }

    public void realmSet$repeat(int i) {
        this.repeat = i;
    }

    public void realmSet$time(int i) {
        this.time = i;
    }

    public void realmSet$transition(String str) {
        this.transition = str;
    }

    public Point() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public int getTime() {
        return realmGet$time();
    }

    public void setTime(int i) {
        realmSet$time(i);
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

    public String getTransition() {
        return realmGet$transition();
    }

    public void setTransition(String str) {
        realmSet$transition(str);
    }

    public String toString() {
        return "Point{time=" + realmGet$time() + ", repeat=" + realmGet$repeat() + ", oper=" + realmGet$oper() + ", transition='" + realmGet$transition() + '\'' + '}';
    }

    private String getString(RealmList<Operation> realmList) {
        StringBuilder sb = new StringBuilder();
        if (realmList == null) {
            return null;
        }
        for (int i = 0; i < realmList.size(); i++) {
            sb.append(realmList);
            sb.append(",");
        }
        return sb.toString();
    }
}
