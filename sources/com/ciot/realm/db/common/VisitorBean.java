package com.ciot.realm.db.common;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class VisitorBean extends RealmObject implements com_ciot_realm_db_common_VisitorBeanRealmProxyInterface {
    private String company;
    private String face;
    @PrimaryKey
    private String id;
    private String idcard;
    private String lanpath;
    private String name;
    private String path;
    private String phone;
    private int type;

    public String realmGet$company() {
        return this.company;
    }

    public String realmGet$face() {
        return this.face;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$idcard() {
        return this.idcard;
    }

    public String realmGet$lanpath() {
        return this.lanpath;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$path() {
        return this.path;
    }

    public String realmGet$phone() {
        return this.phone;
    }

    public int realmGet$type() {
        return this.type;
    }

    public void realmSet$company(String str) {
        this.company = str;
    }

    public void realmSet$face(String str) {
        this.face = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$idcard(String str) {
        this.idcard = str;
    }

    public void realmSet$lanpath(String str) {
        this.lanpath = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$path(String str) {
        this.path = str;
    }

    public void realmSet$phone(String str) {
        this.phone = str;
    }

    public void realmSet$type(int i) {
        this.type = i;
    }

    public VisitorBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String str) {
        realmSet$name(str);
    }

    public String getCompany() {
        return realmGet$company();
    }

    public void setCompany(String str) {
        realmSet$company(str);
    }

    public String getIdcard() {
        return realmGet$idcard();
    }

    public void setIdcard(String str) {
        realmSet$idcard(str);
    }

    public String getPhone() {
        return realmGet$phone();
    }

    public void setPhone(String str) {
        realmSet$phone(str);
    }

    public String getFace() {
        return realmGet$face();
    }

    public void setFace(String str) {
        realmSet$face(str);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String str) {
        realmSet$id(str);
    }

    public int getType() {
        return realmGet$type();
    }

    public void setType(int i) {
        realmSet$type(i);
    }

    public String getPath() {
        return realmGet$path();
    }

    public void setPath(String str) {
        realmSet$path(str);
    }

    public String getLanpath() {
        return realmGet$lanpath();
    }

    public void setLanpath(String str) {
        realmSet$lanpath(str);
    }
}
