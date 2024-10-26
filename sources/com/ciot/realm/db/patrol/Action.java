package com.ciot.realm.db.patrol;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_patrol_ActionRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Action extends RealmObject implements Serializable, com_ciot_realm_db_patrol_ActionRealmProxyInterface {
    private RealmList<String> data;
    private boolean enable;
    private RealmList<MediaBean> media;

    public RealmList realmGet$data() {
        return this.data;
    }

    public boolean realmGet$enable() {
        return this.enable;
    }

    public RealmList realmGet$media() {
        return this.media;
    }

    public void realmSet$data(RealmList realmList) {
        this.data = realmList;
    }

    public void realmSet$enable(boolean z) {
        this.enable = z;
    }

    public void realmSet$media(RealmList realmList) {
        this.media = realmList;
    }

    public Action() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public boolean getEnable() {
        return realmGet$enable();
    }

    public void setEnable(boolean z) {
        realmSet$enable(z);
    }

    public RealmList<String> getData() {
        return realmGet$data();
    }

    public void setData(RealmList<String> realmList) {
        realmSet$data(realmList);
    }

    public RealmList<MediaBean> getMedia() {
        return realmGet$media();
    }

    public void setMedia(RealmList<MediaBean> realmList) {
        realmSet$media(realmList);
    }
}
