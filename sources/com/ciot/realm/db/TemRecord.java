package com.ciot.realm.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_TemRecordRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class TemRecord extends RealmObject implements com_ciot_realm_db_TemRecordRealmProxyInterface {
    @PrimaryKey
    private long createtime;
    private String pathname;
    private Person person;
    private String staffno;
    private String status;
    private float temperature;

    public long realmGet$createtime() {
        return this.createtime;
    }

    public String realmGet$pathname() {
        return this.pathname;
    }

    public Person realmGet$person() {
        return this.person;
    }

    public String realmGet$staffno() {
        return this.staffno;
    }

    public String realmGet$status() {
        return this.status;
    }

    public float realmGet$temperature() {
        return this.temperature;
    }

    public void realmSet$createtime(long j) {
        this.createtime = j;
    }

    public void realmSet$pathname(String str) {
        this.pathname = str;
    }

    public void realmSet$person(Person person2) {
        this.person = person2;
    }

    public void realmSet$staffno(String str) {
        this.staffno = str;
    }

    public void realmSet$status(String str) {
        this.status = str;
    }

    public void realmSet$temperature(float f) {
        this.temperature = f;
    }

    public TemRecord() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$status("normal");
    }

    public TemRecord(Person person2, long j, float f, String str) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$status("normal");
        realmSet$person(person2);
        realmSet$createtime(j);
        realmSet$temperature(f);
        realmSet$staffno(str);
    }

    public Person getPerson() {
        return realmGet$person();
    }

    public void setPerson(Person person2) {
        realmSet$person(person2);
    }

    public long getCreatetime() {
        return realmGet$createtime();
    }

    public void setCreatetime(long j) {
        realmSet$createtime(j);
    }

    public String getStaffno() {
        return realmGet$staffno();
    }

    public void setStaffno(String str) {
        realmSet$staffno(str);
    }

    public float getTemperature() {
        return realmGet$temperature();
    }

    public void setTemperature(float f) {
        realmSet$temperature(f);
    }

    public String getPathname() {
        return realmGet$pathname();
    }

    public void setPathname(String str) {
        realmSet$pathname(str);
    }

    public String getStatus() {
        return realmGet$status();
    }

    public void setStatus(String str) {
        realmSet$status(str);
    }
}
