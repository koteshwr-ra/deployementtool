package com.ciot.realm.db.patrol;

import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_patrol_MessageRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Message extends RealmObject implements Serializable, com_ciot_realm_db_patrol_MessageRealmProxyInterface {
    private boolean enable;
    private String phone;

    public boolean realmGet$enable() {
        return this.enable;
    }

    public String realmGet$phone() {
        return this.phone;
    }

    public void realmSet$enable(boolean z) {
        this.enable = z;
    }

    public void realmSet$phone(String str) {
        this.phone = str;
    }

    public Message() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public boolean isEnable() {
        return realmGet$enable();
    }

    public void setEnable(boolean z) {
        realmSet$enable(z);
    }

    public String getPhone() {
        return realmGet$phone();
    }

    public void setPhone(String str) {
        realmSet$phone(str);
    }
}
