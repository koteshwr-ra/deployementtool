package com.ciot.realm.db.patrol;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_patrol_OperationRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Operation extends RealmObject implements Serializable, com_ciot_realm_db_patrol_OperationRealmProxyInterface {
    public static final int ALARM_LIGHT = 4;
    public static final int BROADCAST = 1;
    public static final int NO_OPERATION = 0;
    public static final int RECORD = 3;
    public static final int SPRAY = 5;
    public static final int TAKE_PHOTO = 2;
    private static final long serialVersionUID = -2869687824295174023L;
    @SerializedName("operp1")
    @Expose
    private int operP1;
    @SerializedName("operrepeat")
    @Expose
    private int operRepeat;
    @SerializedName("opertext")
    @Expose
    private String operText;
    @SerializedName("opertime")
    @Expose
    private int operTime;
    @SerializedName("opertype")
    @Expose
    private int operType;
    @SerializedName("sprayModelPos")
    @Expose
    private int sprayModelPos;

    public int realmGet$operP1() {
        return this.operP1;
    }

    public int realmGet$operRepeat() {
        return this.operRepeat;
    }

    public String realmGet$operText() {
        return this.operText;
    }

    public int realmGet$operTime() {
        return this.operTime;
    }

    public int realmGet$operType() {
        return this.operType;
    }

    public int realmGet$sprayModelPos() {
        return this.sprayModelPos;
    }

    public void realmSet$operP1(int i) {
        this.operP1 = i;
    }

    public void realmSet$operRepeat(int i) {
        this.operRepeat = i;
    }

    public void realmSet$operText(String str) {
        this.operText = str;
    }

    public void realmSet$operTime(int i) {
        this.operTime = i;
    }

    public void realmSet$operType(int i) {
        this.operType = i;
    }

    public void realmSet$sprayModelPos(int i) {
        this.sprayModelPos = i;
    }

    public Operation() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public int getOperType() {
        return realmGet$operType();
    }

    public void setOperType(int i) {
        realmSet$operType(i);
    }

    public String getOperText() {
        return realmGet$operText();
    }

    public void setOperText(String str) {
        realmSet$operText(str);
    }

    public int getOperP1() {
        return realmGet$operP1();
    }

    public void setOperP1(int i) {
        realmSet$operP1(i);
    }

    public int getOperTime() {
        return realmGet$operTime();
    }

    public void setOperTime(int i) {
        realmSet$operTime(i);
    }

    public int getOperRepeat() {
        return realmGet$operRepeat();
    }

    public void setOperRepeat(int i) {
        realmSet$operRepeat(i);
    }

    public int getSprayModelPos() {
        return realmGet$sprayModelPos();
    }

    public void setSprayModelPos(int i) {
        realmSet$sprayModelPos(i);
    }

    public String toString() {
        return "Operation{operType=" + realmGet$operType() + ", operText='" + realmGet$operText() + '\'' + ", operP1=" + realmGet$operP1() + ", operTime=" + realmGet$operTime() + ", operRepeat=" + realmGet$operRepeat() + ", sprayModelPos=" + realmGet$sprayModelPos() + '}';
    }
}
