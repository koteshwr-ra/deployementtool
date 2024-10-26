package com.ciot.realm.db.common;

import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_common_ContactRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class Contact extends RealmObject implements com_ciot_realm_db_common_ContactRealmProxyInterface {
    private String id;
    private String name;
    private String phone;

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$phone() {
        return this.phone;
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

    public Contact() {
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
}
