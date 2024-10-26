package com.ciot.realm.db.semantic;

import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_semantic_ActionBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class ActionBean extends RealmObject implements com_ciot_realm_db_semantic_ActionBeanRealmProxyInterface {
    private int actionId;
    private String ttl;

    public int realmGet$actionId() {
        return this.actionId;
    }

    public String realmGet$ttl() {
        return this.ttl;
    }

    public void realmSet$actionId(int i) {
        this.actionId = i;
    }

    public void realmSet$ttl(String str) {
        this.ttl = str;
    }

    public ActionBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public int getActionId() {
        return realmGet$actionId();
    }

    public void setActionId(int i) {
        realmSet$actionId(i);
    }

    public String getTtl() {
        return realmGet$ttl();
    }

    public void setTtl(String str) {
        realmSet$ttl(str);
    }

    public String toString() {
        return "ActionBean{actionId=" + realmGet$actionId() + ", ttl='" + realmGet$ttl() + '\'' + '}';
    }
}
