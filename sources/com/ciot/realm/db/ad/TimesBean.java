package com.ciot.realm.db.ad;

import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_ad_TimesBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class TimesBean extends RealmObject implements com_ciot_realm_db_ad_TimesBeanRealmProxyInterface {
    private BeginBean begin;
    private EndBean end;

    public BeginBean realmGet$begin() {
        return this.begin;
    }

    public EndBean realmGet$end() {
        return this.end;
    }

    public void realmSet$begin(BeginBean beginBean) {
        this.begin = beginBean;
    }

    public void realmSet$end(EndBean endBean) {
        this.end = endBean;
    }

    public TimesBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public BeginBean getBegin() {
        return realmGet$begin();
    }

    public void setBegin(BeginBean beginBean) {
        realmSet$begin(beginBean);
    }

    public EndBean getEnd() {
        return realmGet$end();
    }

    public void setEnd(EndBean endBean) {
        realmSet$end(endBean);
    }
}
