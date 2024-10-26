package com.ciot.realm.db;

import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_RecordLockModeRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class RecordLockMode extends RealmObject implements com_ciot_realm_db_RecordLockModeRealmProxyInterface {
    private String card;
    private String face;
    private String iccard;
    private String idcardcode;

    public String realmGet$card() {
        return this.card;
    }

    public String realmGet$face() {
        return this.face;
    }

    public String realmGet$iccard() {
        return this.iccard;
    }

    public String realmGet$idcardcode() {
        return this.idcardcode;
    }

    public void realmSet$card(String str) {
        this.card = str;
    }

    public void realmSet$face(String str) {
        this.face = str;
    }

    public void realmSet$iccard(String str) {
        this.iccard = str;
    }

    public void realmSet$idcardcode(String str) {
        this.idcardcode = str;
    }

    public RecordLockMode() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getFace() {
        return realmGet$face();
    }

    public void setFace(String str) {
        realmSet$face(str);
    }

    public String getIccard() {
        return realmGet$iccard();
    }

    public void setIccard(String str) {
        realmSet$iccard(str);
    }

    public String getIdcardcode() {
        return realmGet$idcardcode();
    }

    public void setIdcardcode(String str) {
        realmSet$idcardcode(str);
    }

    public String getCard() {
        return realmGet$card();
    }

    public void setCard(String str) {
        realmSet$card(str);
    }
}
