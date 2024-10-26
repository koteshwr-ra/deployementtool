package com.ciot.realm.db;

import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_RobotBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class RobotBean extends RealmObject implements com_ciot_realm_db_RobotBeanRealmProxyInterface {
    private String id;
    private String name;

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$name() {
        return this.name;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public RobotBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public RobotBean(String str, String str2) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$id(str);
        realmSet$name(str2);
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

    public String toString() {
        return "RobotBean{id='" + realmGet$id() + '\'' + ", name='" + realmGet$name() + '\'' + '}';
    }
}
