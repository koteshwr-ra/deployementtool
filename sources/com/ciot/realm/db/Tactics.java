package com.ciot.realm.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_TacticsRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class Tactics extends RealmObject implements com_ciot_realm_db_TacticsRealmProxyInterface {
    private String company;
    private String description;
    private String effect;
    private String end;
    private long endTimeClose;
    private long endTimeOpen;
    @PrimaryKey
    private String id;
    private String person;
    private String ruletype;
    private String start;
    private long startTimeClose;
    private long startTimeOpen;
    private int type;

    public String realmGet$company() {
        return this.company;
    }

    public String realmGet$description() {
        return this.description;
    }

    public String realmGet$effect() {
        return this.effect;
    }

    public String realmGet$end() {
        return this.end;
    }

    public long realmGet$endTimeClose() {
        return this.endTimeClose;
    }

    public long realmGet$endTimeOpen() {
        return this.endTimeOpen;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$person() {
        return this.person;
    }

    public String realmGet$ruletype() {
        return this.ruletype;
    }

    public String realmGet$start() {
        return this.start;
    }

    public long realmGet$startTimeClose() {
        return this.startTimeClose;
    }

    public long realmGet$startTimeOpen() {
        return this.startTimeOpen;
    }

    public int realmGet$type() {
        return this.type;
    }

    public void realmSet$company(String str) {
        this.company = str;
    }

    public void realmSet$description(String str) {
        this.description = str;
    }

    public void realmSet$effect(String str) {
        this.effect = str;
    }

    public void realmSet$end(String str) {
        this.end = str;
    }

    public void realmSet$endTimeClose(long j) {
        this.endTimeClose = j;
    }

    public void realmSet$endTimeOpen(long j) {
        this.endTimeOpen = j;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$person(String str) {
        this.person = str;
    }

    public void realmSet$ruletype(String str) {
        this.ruletype = str;
    }

    public void realmSet$start(String str) {
        this.start = str;
    }

    public void realmSet$startTimeClose(long j) {
        this.startTimeClose = j;
    }

    public void realmSet$startTimeOpen(long j) {
        this.startTimeOpen = j;
    }

    public void realmSet$type(int i) {
        this.type = i;
    }

    public Tactics() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getCompany() {
        return realmGet$company();
    }

    public void setCompany(String str) {
        realmSet$company(str);
    }

    public String getStart() {
        return realmGet$start();
    }

    public void setStart(String str) {
        realmSet$start(str);
    }

    public String getEnd() {
        return realmGet$end();
    }

    public void setEnd(String str) {
        realmSet$end(str);
    }

    public int getType() {
        return realmGet$type();
    }

    public void setType(int i) {
        realmSet$type(i);
    }

    public String getRuletype() {
        return realmGet$ruletype();
    }

    public void setRuletype(String str) {
        realmSet$ruletype(str);
    }

    public String getDescription() {
        return realmGet$description();
    }

    public void setDescription(String str) {
        realmSet$description(str);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String str) {
        realmSet$id(str);
    }

    public long getStartTimeOpen() {
        return realmGet$startTimeOpen();
    }

    public void setStartTimeOpen(long j) {
        realmSet$startTimeOpen(j);
    }

    public long getStartTimeClose() {
        return realmGet$startTimeClose();
    }

    public void setStartTimeClose(long j) {
        realmSet$startTimeClose(j);
    }

    public long getEndTimeOpen() {
        return realmGet$endTimeOpen();
    }

    public void setEndTimeOpen(long j) {
        realmSet$endTimeOpen(j);
    }

    public long getEndTimeClose() {
        return realmGet$endTimeClose();
    }

    public void setEndTimeClose(long j) {
        realmSet$endTimeClose(j);
    }

    public String getPerson() {
        return realmGet$person();
    }

    public void setPerson(String str) {
        realmSet$person(str);
    }

    public String getEffect() {
        return realmGet$effect();
    }

    public void setEffect(String str) {
        realmSet$effect(str);
    }
}
