package com.ciot.realm.db.recommantation;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class DataBean extends RealmObject implements com_ciot_realm_db_recommantation_DataBeanRealmProxyInterface {
    @PrimaryKey
    private long id;
    private RealmList<String> recommendations;

    public long realmGet$id() {
        return this.id;
    }

    public RealmList realmGet$recommendations() {
        return this.recommendations;
    }

    public void realmSet$id(long j) {
        this.id = j;
    }

    public void realmSet$recommendations(RealmList realmList) {
        this.recommendations = realmList;
    }

    public DataBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public RealmList<String> getRecommendations() {
        return realmGet$recommendations();
    }

    public void setRecommendations(RealmList<String> realmList) {
        realmSet$recommendations(realmList);
    }

    public String toString() {
        return "DataBean{recommendations=" + realmGet$recommendations() + '}';
    }
}
