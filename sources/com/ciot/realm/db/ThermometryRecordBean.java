package com.ciot.realm.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class ThermometryRecordBean extends RealmObject implements com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface {
    private String imageName;
    private boolean isUpload;
    private String mediaId;
    private Person person;
    private float temperature;
    @PrimaryKey
    private long time;
    private String url;

    public String realmGet$imageName() {
        return this.imageName;
    }

    public boolean realmGet$isUpload() {
        return this.isUpload;
    }

    public String realmGet$mediaId() {
        return this.mediaId;
    }

    public Person realmGet$person() {
        return this.person;
    }

    public float realmGet$temperature() {
        return this.temperature;
    }

    public long realmGet$time() {
        return this.time;
    }

    public String realmGet$url() {
        return this.url;
    }

    public void realmSet$imageName(String str) {
        this.imageName = str;
    }

    public void realmSet$isUpload(boolean z) {
        this.isUpload = z;
    }

    public void realmSet$mediaId(String str) {
        this.mediaId = str;
    }

    public void realmSet$person(Person person2) {
        this.person = person2;
    }

    public void realmSet$temperature(float f) {
        this.temperature = f;
    }

    public void realmSet$time(long j) {
        this.time = j;
    }

    public void realmSet$url(String str) {
        this.url = str;
    }

    public ThermometryRecordBean(Person person2, long j, float f, String str) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$person(person2);
        realmSet$time(j);
        realmSet$temperature(f);
        realmSet$imageName(str);
    }

    public ThermometryRecordBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public float getTemperature() {
        return realmGet$temperature();
    }

    public void setTemperature(float f) {
        realmSet$temperature(f);
    }

    public Person getPerson() {
        return realmGet$person();
    }

    public void setPerson(Person person2) {
        realmSet$person(person2);
    }

    public long getTime() {
        return realmGet$time();
    }

    public void setTime(long j) {
        realmSet$time(j);
    }

    public String getImageName() {
        return realmGet$imageName();
    }

    public void setImageName(String str) {
        realmSet$imageName(str);
    }

    public boolean isUpload() {
        return realmGet$isUpload();
    }

    public void setUpload(boolean z) {
        realmSet$isUpload(z);
    }

    public String getMediaId() {
        return realmGet$mediaId();
    }

    public void setMediaId(String str) {
        realmSet$mediaId(str);
    }

    public String getUrl() {
        return realmGet$url();
    }

    public void setUrl(String str) {
        realmSet$url(str);
    }

    public String toString() {
        return "ThermometryRecordBean{person=" + realmGet$person() + ", time=" + realmGet$time() + ", mediaId='" + realmGet$mediaId() + '\'' + ", url='" + realmGet$url() + '\'' + ", temperature=" + realmGet$temperature() + ", imageName='" + realmGet$imageName() + '\'' + ", isUpload=" + realmGet$isUpload() + '}';
    }
}
