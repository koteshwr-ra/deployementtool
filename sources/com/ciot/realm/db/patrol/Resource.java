package com.ciot.realm.db.patrol;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_patrol_ResourceRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Resource extends RealmObject implements Serializable, com_ciot_realm_db_patrol_ResourceRealmProxyInterface {
    @PrimaryKey
    private String id;
    private String lanUrl;
    private String res;
    private String wanUrl;

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$lanUrl() {
        return this.lanUrl;
    }

    public String realmGet$res() {
        return this.res;
    }

    public String realmGet$wanUrl() {
        return this.wanUrl;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$lanUrl(String str) {
        this.lanUrl = str;
    }

    public void realmSet$res(String str) {
        this.res = str;
    }

    public void realmSet$wanUrl(String str) {
        this.wanUrl = str;
    }

    public Resource() {
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

    public String getRes() {
        return realmGet$res();
    }

    public void setRes(String str) {
        realmSet$res(str);
    }

    public String getWanUrl() {
        return realmGet$wanUrl();
    }

    public void setWanUrl(String str) {
        realmSet$wanUrl(str);
    }

    public String getLanUrl() {
        return realmGet$lanUrl();
    }

    public void setLanUrl(String str) {
        realmSet$lanUrl(str);
    }
}
