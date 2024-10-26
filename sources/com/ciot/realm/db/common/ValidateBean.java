package com.ciot.realm.db.common;

import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_common_ValidateBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class ValidateBean extends RealmObject implements com_ciot_realm_db_common_ValidateBeanRealmProxyInterface {
    private String face;
    private String idcard;
    private int locale;
    private String name;
    private String phone;
    private String result;

    public String realmGet$face() {
        return this.face;
    }

    public String realmGet$idcard() {
        return this.idcard;
    }

    public int realmGet$locale() {
        return this.locale;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$phone() {
        return this.phone;
    }

    public String realmGet$result() {
        return this.result;
    }

    public void realmSet$face(String str) {
        this.face = str;
    }

    public void realmSet$idcard(String str) {
        this.idcard = str;
    }

    public void realmSet$locale(int i) {
        this.locale = i;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$phone(String str) {
        this.phone = str;
    }

    public void realmSet$result(String str) {
        this.result = str;
    }

    public ValidateBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getFace() {
        return realmGet$face();
    }

    public void setFace(String str) {
        realmSet$face(str);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String str) {
        realmSet$name(str);
    }

    public String getPhone() {
        return realmGet$phone();
    }

    public void setPhone(String str) {
        realmSet$phone(str);
    }

    public String getIdcard() {
        return realmGet$idcard();
    }

    public void setIdcard(String str) {
        realmSet$idcard(str);
    }

    public int getLocale() {
        return realmGet$locale();
    }

    public void setLocale(int i) {
        realmSet$locale(i);
    }

    public String getResult() {
        return realmGet$result();
    }

    public void setResult(String str) {
        realmSet$result(str);
    }
}
