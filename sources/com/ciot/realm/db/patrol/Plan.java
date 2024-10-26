package com.ciot.realm.db.patrol;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_patrol_PlanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class Plan extends RealmObject implements Serializable, com_ciot_realm_db_patrol_PlanRealmProxyInterface {
    public static final String DAY_TYPE = "day";
    public static final String MONTH_TYPE = "month";
    public static final String ONE_TYPE = "one";
    public static final String WEEK_TYPE = "week";
    @PrimaryKey
    private long create;
    private String cycle;
    private RealmList<Integer> date;
    private String end;
    private String start;

    public long realmGet$create() {
        return this.create;
    }

    public String realmGet$cycle() {
        return this.cycle;
    }

    public RealmList realmGet$date() {
        return this.date;
    }

    public String realmGet$end() {
        return this.end;
    }

    public String realmGet$start() {
        return this.start;
    }

    public void realmSet$create(long j) {
        this.create = j;
    }

    public void realmSet$cycle(String str) {
        this.cycle = str;
    }

    public void realmSet$date(RealmList realmList) {
        this.date = realmList;
    }

    public void realmSet$end(String str) {
        this.end = str;
    }

    public void realmSet$start(String str) {
        this.start = str;
    }

    public Plan() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getCycle() {
        return realmGet$cycle();
    }

    public void setCycle(String str) {
        realmSet$cycle(str);
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

    public long getCreate() {
        if (realmGet$create() < 1610100350) {
            return realmGet$create() * 1000;
        }
        return realmGet$create();
    }

    public void setCreate(long j) {
        realmSet$create(j);
    }

    public RealmList<Integer> getDate() {
        return realmGet$date();
    }

    public void setDate(RealmList<Integer> realmList) {
        realmSet$date(realmList);
    }

    public String toString() {
        return "Plan{cycle='" + realmGet$cycle() + '\'' + ", start='" + realmGet$start() + '\'' + ", end='" + realmGet$end() + '\'' + ", create=" + realmGet$create() + ", date=" + realmGet$date() + '}';
    }
}
