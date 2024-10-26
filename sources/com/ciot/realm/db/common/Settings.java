package com.ciot.realm.db.common;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_common_SettingsRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class Settings extends RealmObject implements com_ciot_realm_db_common_SettingsRealmProxyInterface {
    private String company;
    private String contact;
    @PrimaryKey
    private String id;
    private String name;
    private String phone;
    private int security;
    private String type;

    public String realmGet$company() {
        return this.company;
    }

    public String realmGet$contact() {
        return this.contact;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$phone() {
        return this.phone;
    }

    public int realmGet$security() {
        return this.security;
    }

    public String realmGet$type() {
        return this.type;
    }

    public void realmSet$company(String str) {
        this.company = str;
    }

    public void realmSet$contact(String str) {
        this.contact = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$phone(String str) {
        this.phone = str;
    }

    public void realmSet$security(int i) {
        this.security = i;
    }

    public void realmSet$type(String str) {
        this.type = str;
    }

    public Settings() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getType() {
        return realmGet$type();
    }

    public void setType(String str) {
        realmSet$type(str);
    }

    public String getCompany() {
        return realmGet$company();
    }

    public void setCompany(String str) {
        realmSet$company(str);
    }

    public String getContact() {
        return realmGet$contact();
    }

    public void setContact(String str) {
        realmSet$contact(str);
    }

    public String getPhone() {
        return realmGet$phone();
    }

    public void setPhone(String str) {
        realmSet$phone(str);
    }

    public int getSecurity() {
        return realmGet$security();
    }

    public void setSecurity(int i) {
        realmSet$security(i);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String str) {
        realmSet$id(str);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String str) {
        realmSet$name(str);
    }
}
