package com.ciot.realm.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_RecordRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class Record extends RealmObject implements com_ciot_realm_db_RecordRealmProxyInterface {
    private double begin;
    private double end;
    private String healthCode;
    private int healthColor;
    @PrimaryKey
    private long id;
    private String qrCode;
    private String recordId;

    public double realmGet$begin() {
        return this.begin;
    }

    public double realmGet$end() {
        return this.end;
    }

    public String realmGet$healthCode() {
        return this.healthCode;
    }

    public int realmGet$healthColor() {
        return this.healthColor;
    }

    public long realmGet$id() {
        return this.id;
    }

    public String realmGet$qrCode() {
        return this.qrCode;
    }

    public String realmGet$recordId() {
        return this.recordId;
    }

    public void realmSet$begin(double d) {
        this.begin = d;
    }

    public void realmSet$end(double d) {
        this.end = d;
    }

    public void realmSet$healthCode(String str) {
        this.healthCode = str;
    }

    public void realmSet$healthColor(int i) {
        this.healthColor = i;
    }

    public void realmSet$id(long j) {
        this.id = j;
    }

    public void realmSet$qrCode(String str) {
        this.qrCode = str;
    }

    public void realmSet$recordId(String str) {
        this.recordId = str;
    }

    public Record() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$healthColor(1);
    }

    public String getHealthCode() {
        return realmGet$healthCode();
    }

    public void setHealthCode(String str) {
        realmSet$healthCode(str);
    }

    public int getHealthColor() {
        return realmGet$healthColor();
    }

    public void setHealthColor(int i) {
        realmSet$healthColor(i);
    }

    public String getRecordId() {
        return realmGet$recordId();
    }

    public void setRecordId(String str) {
        realmSet$recordId(str);
    }

    public double getBegin() {
        return realmGet$begin();
    }

    public void setBegin(double d) {
        realmSet$begin(d);
    }

    public double getEnd() {
        return realmGet$end();
    }

    public void setEnd(double d) {
        realmSet$end(d);
    }

    public String getQrCode() {
        return realmGet$qrCode();
    }

    public void setQrCode(String str) {
        realmSet$qrCode(str);
    }

    public long getId() {
        return realmGet$id();
    }

    public void setId(long j) {
        realmSet$id(j);
    }
}
