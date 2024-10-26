package com.ciot.realm.db;

import android.text.TextUtils;
import androidx.core.os.EnvironmentCompat;
import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_PersonRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class Person extends RealmObject implements com_ciot_realm_db_PersonRealmProxyInterface {
    public static final String EMPLOYEE = "employee";
    public static final String STRANGER = "stranger";
    public static final String VISITOR = "visitor";
    private String healthcode;
    private String healthcolor;
    private String id;
    private String idcard;
    private String name;
    private String type;

    public String realmGet$healthcode() {
        return this.healthcode;
    }

    public String realmGet$healthcolor() {
        return this.healthcolor;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$idcard() {
        return this.idcard;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$type() {
        return this.type;
    }

    public void realmSet$healthcode(String str) {
        this.healthcode = str;
    }

    public void realmSet$healthcolor(String str) {
        this.healthcolor = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$idcard(String str) {
        this.idcard = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$type(String str) {
        this.type = str;
    }

    public Person() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$name(EnvironmentCompat.MEDIA_UNKNOWN);
        realmSet$id(EnvironmentCompat.MEDIA_UNKNOWN);
        realmSet$type(STRANGER);
        realmSet$healthcolor("");
    }

    public String getHealthcolor() {
        return realmGet$healthcolor();
    }

    public boolean healthAberrant() {
        return TextUtils.equals(realmGet$healthcolor(), "红") || TextUtils.equals(realmGet$healthcolor(), "黄");
    }

    public void setHealthcolor(String str) {
        realmSet$healthcolor(str);
    }

    public String getHealthcode() {
        return realmGet$healthcode();
    }

    public void setHealthcode(String str) {
        realmSet$healthcode(str);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String str) {
        realmSet$name(str);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String str) {
        realmSet$id(str);
    }

    public String getType() {
        return realmGet$type();
    }

    public void setType(String str) {
        realmSet$type(str);
    }

    public String getIdcard() {
        return realmGet$idcard();
    }

    public void setIdcard(String str) {
        realmSet$idcard(str);
    }

    public String toString() {
        return "Person{name='" + realmGet$name() + '\'' + ", id='" + realmGet$id() + '\'' + ", type='" + realmGet$type() + '\'' + ", idcard='" + realmGet$idcard() + '\'' + ", healthcode='" + realmGet$healthcode() + '\'' + ", healthcolor=" + realmGet$healthcolor() + '}';
    }
}
