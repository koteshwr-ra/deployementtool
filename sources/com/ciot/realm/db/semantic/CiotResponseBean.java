package com.ciot.realm.db.semantic;

import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_semantic_CiotResponseBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class CiotResponseBean extends RealmObject implements com_ciot_realm_db_semantic_CiotResponseBeanRealmProxyInterface {
    private int code;
    private AnswerBean data;
    private String message;
    private int ttl;

    public int realmGet$code() {
        return this.code;
    }

    public AnswerBean realmGet$data() {
        return this.data;
    }

    public String realmGet$message() {
        return this.message;
    }

    public int realmGet$ttl() {
        return this.ttl;
    }

    public void realmSet$code(int i) {
        this.code = i;
    }

    public void realmSet$data(AnswerBean answerBean) {
        this.data = answerBean;
    }

    public void realmSet$message(String str) {
        this.message = str;
    }

    public void realmSet$ttl(int i) {
        this.ttl = i;
    }

    public CiotResponseBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$code(0);
        realmSet$ttl(0);
    }

    public int getCode() {
        return realmGet$code();
    }

    public void setCode(int i) {
        realmSet$code(i);
    }

    public AnswerBean getData() {
        return realmGet$data();
    }

    public void setData(AnswerBean answerBean) {
        realmSet$data(answerBean);
    }

    public String getMessage() {
        return realmGet$message();
    }

    public void setMessage(String str) {
        realmSet$message(str);
    }

    public int getTtl() {
        return realmGet$ttl();
    }

    public void setTtl(int i) {
        realmSet$ttl(i);
    }

    public String toString() {
        return "CiotResponseBean{code=" + realmGet$code() + ", data=" + realmGet$data() + ", message='" + realmGet$message() + '\'' + ", ttl=" + realmGet$ttl() + '}';
    }
}
