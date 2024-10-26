package com.ciot.realm.db.visitor;

import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_visitor_MediaResultRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class MediaResult extends RealmObject implements com_ciot_realm_db_visitor_MediaResultRealmProxyInterface {
    private long insTime;

    public long realmGet$insTime() {
        return this.insTime;
    }

    public void realmSet$insTime(long j) {
        this.insTime = j;
    }

    public MediaResult() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public long getInsTime() {
        return realmGet$insTime();
    }

    public void setInsTime(long j) {
        realmSet$insTime(j);
    }

    public String toString() {
        return "MediaResult{insTime=" + realmGet$insTime() + '}';
    }
}
