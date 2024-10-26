package com.ciot.realm.db.patrol;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_patrol_ProcessRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Process extends RealmObject implements Serializable, com_ciot_realm_db_patrol_ProcessRealmProxyInterface {
    private static final long serialVersionUID = -1558046963828129488L;
    @SerializedName("oper")
    @Expose
    private RealmList<Operation> oper;
    @SerializedName("repeat")
    @Expose
    private int repeat;

    public RealmList realmGet$oper() {
        return this.oper;
    }

    public int realmGet$repeat() {
        return this.repeat;
    }

    public void realmSet$oper(RealmList realmList) {
        this.oper = realmList;
    }

    public void realmSet$repeat(int i) {
        this.repeat = i;
    }

    public Process() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
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

    public String toString() {
        return "Line{oper=" + getString(realmGet$oper()) + "repeat=" + realmGet$repeat() + '}';
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
