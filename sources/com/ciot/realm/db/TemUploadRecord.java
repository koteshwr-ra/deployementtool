package com.ciot.realm.db;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_TemUploadRecordRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class TemUploadRecord extends RealmObject implements com_ciot_realm_db_TemUploadRecordRealmProxyInterface {
    private String code;
    @Ignore
    private Long createtime;
    private RealmList<TemRecord> datas;
    private String filepath;
    private String pathname;
    @Ignore
    private Person person;
    private String robot;
    @Ignore
    private Float temperature;
    @PrimaryKey
    private long uploadtime;
    private String url;

    public String realmGet$code() {
        return this.code;
    }

    public RealmList realmGet$datas() {
        return this.datas;
    }

    public String realmGet$filepath() {
        return this.filepath;
    }

    public String realmGet$pathname() {
        return this.pathname;
    }

    public String realmGet$robot() {
        return this.robot;
    }

    public long realmGet$uploadtime() {
        return this.uploadtime;
    }

    public String realmGet$url() {
        return this.url;
    }

    public void realmSet$code(String str) {
        this.code = str;
    }

    public void realmSet$datas(RealmList realmList) {
        this.datas = realmList;
    }

    public void realmSet$filepath(String str) {
        this.filepath = str;
    }

    public void realmSet$pathname(String str) {
        this.pathname = str;
    }

    public void realmSet$robot(String str) {
        this.robot = str;
    }

    public void realmSet$uploadtime(long j) {
        this.uploadtime = j;
    }

    public void realmSet$url(String str) {
        this.url = str;
    }

    public TemUploadRecord() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public long getUploadtime() {
        return realmGet$uploadtime();
    }

    public void setUploadtime(long j) {
        realmSet$uploadtime(j);
    }

    public long getCreatetime() {
        return this.createtime.longValue();
    }

    public void setCreatetime(long j) {
        this.createtime = Long.valueOf(j);
    }

    public String getCode() {
        return realmGet$code();
    }

    public void setCode(String str) {
        realmSet$code(str);
    }

    public RealmList<TemRecord> getDatas() {
        return realmGet$datas();
    }

    public void setDatas(RealmList<TemRecord> realmList) {
        realmSet$datas(realmList);
    }

    public String getUrl() {
        return realmGet$url();
    }

    public void setUrl(String str) {
        realmSet$url(str);
    }

    public String getRobot() {
        return realmGet$robot();
    }

    public void setRobot(String str) {
        realmSet$robot(str);
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person2) {
        this.person = person2;
    }

    public Float getTemperature() {
        return this.temperature;
    }

    public void setTemperature(Float f) {
        this.temperature = f;
    }

    public String getFilepath() {
        return realmGet$filepath();
    }

    public void setFilepath(String str) {
        realmSet$filepath(str);
    }

    public String getPathname() {
        return realmGet$pathname();
    }

    public void setPathname(String str) {
        realmSet$pathname(str);
    }
}
