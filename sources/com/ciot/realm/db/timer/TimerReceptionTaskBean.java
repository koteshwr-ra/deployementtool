package com.ciot.realm.db.timer;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class TimerReceptionTaskBean extends RealmObject implements com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxyInterface {
    public static final String DAY = "day";
    public static final String MONTH = "month";
    public static final String RECEPTION_TASK_TYPE = "receive";
    public static final String WEEK = "week";
    private String cycletype;
    private String description;
    private boolean enable;
    @PrimaryKey
    private String id;
    private String taskdatas;
    private String taskend;
    private String taskstart;
    private String tasktype;

    public String realmGet$cycletype() {
        return this.cycletype;
    }

    public String realmGet$description() {
        return this.description;
    }

    public boolean realmGet$enable() {
        return this.enable;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$taskdatas() {
        return this.taskdatas;
    }

    public String realmGet$taskend() {
        return this.taskend;
    }

    public String realmGet$taskstart() {
        return this.taskstart;
    }

    public String realmGet$tasktype() {
        return this.tasktype;
    }

    public void realmSet$cycletype(String str) {
        this.cycletype = str;
    }

    public void realmSet$description(String str) {
        this.description = str;
    }

    public void realmSet$enable(boolean z) {
        this.enable = z;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$taskdatas(String str) {
        this.taskdatas = str;
    }

    public void realmSet$taskend(String str) {
        this.taskend = str;
    }

    public void realmSet$taskstart(String str) {
        this.taskstart = str;
    }

    public void realmSet$tasktype(String str) {
        this.tasktype = str;
    }

    public TimerReceptionTaskBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public TimerReceptionTaskBean(String str, String str2, String str3, String str4, String str5, String str6, boolean z, String str7) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$id(str);
        realmSet$tasktype(str2);
        realmSet$cycletype(str3);
        realmSet$taskdatas(str4);
        realmSet$taskstart(str5);
        realmSet$taskend(str6);
        realmSet$enable(z);
        realmSet$description(str7);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String str) {
        realmSet$id(str);
    }

    public String getTasktype() {
        return realmGet$tasktype();
    }

    public void setTasktype(String str) {
        realmSet$tasktype(str);
    }

    public String getCycletype() {
        return realmGet$cycletype();
    }

    public void setCycletype(String str) {
        realmSet$cycletype(str);
    }

    public String getTaskdatas() {
        return realmGet$taskdatas();
    }

    public void setTaskdatas(String str) {
        realmSet$taskdatas(str);
    }

    public String getTaskstart() {
        return realmGet$taskstart();
    }

    public void setTaskstart(String str) {
        realmSet$taskstart(str);
    }

    public String getTaskend() {
        return realmGet$taskend();
    }

    public void setTaskend(String str) {
        realmSet$taskend(str);
    }

    public boolean isEnable() {
        return realmGet$enable();
    }

    public void setEnable(boolean z) {
        realmSet$enable(z);
    }

    public String getDescription() {
        return realmGet$description();
    }

    public void setDescription(String str) {
        realmSet$description(str);
    }

    public String toString() {
        return "TimerReceptionTaskBean{id=" + realmGet$id() + ", tasktype='" + realmGet$tasktype() + '\'' + ", cycletype='" + realmGet$cycletype() + '\'' + ", taskdatas='" + realmGet$taskdatas() + '\'' + ", taskstart='" + realmGet$taskstart() + '\'' + ", taskend='" + realmGet$taskend() + '\'' + ", enable=" + realmGet$enable() + ", description='" + realmGet$description() + '\'' + '}';
    }
}
