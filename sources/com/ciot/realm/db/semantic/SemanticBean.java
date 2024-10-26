package com.ciot.realm.db.semantic;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class SemanticBean extends RealmObject implements com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface {
    private CiotResponseBean data;
    @PrimaryKey
    private String question;
    private long time;

    public CiotResponseBean realmGet$data() {
        return this.data;
    }

    public String realmGet$question() {
        return this.question;
    }

    public long realmGet$time() {
        return this.time;
    }

    public void realmSet$data(CiotResponseBean ciotResponseBean) {
        this.data = ciotResponseBean;
    }

    public void realmSet$question(String str) {
        this.question = str;
    }

    public void realmSet$time(long j) {
        this.time = j;
    }

    public SemanticBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$time(System.currentTimeMillis());
    }

    public String getQuestion() {
        return realmGet$question();
    }

    public void setQuestion(String str) {
        realmSet$question(str);
    }

    public CiotResponseBean getData() {
        return realmGet$data();
    }

    public void setData(CiotResponseBean ciotResponseBean) {
        realmSet$data(ciotResponseBean);
    }

    public long getTime() {
        return realmGet$time();
    }

    public void setTime(long j) {
        realmSet$time(j);
    }

    public String toString() {
        return "SemanticBean{question='" + realmGet$question() + '\'' + ", data =" + realmGet$data() + ", time=" + realmGet$time() + '}';
    }
}
