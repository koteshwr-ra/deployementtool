package com.ciot.realm.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class RegisteredRecordInfo extends RealmObject implements com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface {
    @PrimaryKey
    private long id;
    private String imageFilePath;
    private String ossFace;
    private Record record;
    private String registerId;
    private boolean uploadedFaceImage;

    public long realmGet$id() {
        return this.id;
    }

    public String realmGet$imageFilePath() {
        return this.imageFilePath;
    }

    public String realmGet$ossFace() {
        return this.ossFace;
    }

    public Record realmGet$record() {
        return this.record;
    }

    public String realmGet$registerId() {
        return this.registerId;
    }

    public boolean realmGet$uploadedFaceImage() {
        return this.uploadedFaceImage;
    }

    public void realmSet$id(long j) {
        this.id = j;
    }

    public void realmSet$imageFilePath(String str) {
        this.imageFilePath = str;
    }

    public void realmSet$ossFace(String str) {
        this.ossFace = str;
    }

    public void realmSet$record(Record record2) {
        this.record = record2;
    }

    public void realmSet$registerId(String str) {
        this.registerId = str;
    }

    public void realmSet$uploadedFaceImage(boolean z) {
        this.uploadedFaceImage = z;
    }

    public RegisteredRecordInfo() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$uploadedFaceImage(false);
    }

    public long getId() {
        return realmGet$id();
    }

    public void setId(long j) {
        realmSet$id(j);
    }

    public String getImageFilePath() {
        return realmGet$imageFilePath();
    }

    public void setImageFilePath(String str) {
        realmSet$imageFilePath(str);
    }

    public Record getRecord() {
        return realmGet$record();
    }

    public void setRecord(Record record2) {
        realmSet$record(record2);
    }

    public String getOssFace() {
        return realmGet$ossFace();
    }

    public void setOssFace(String str) {
        realmSet$ossFace(str);
    }

    public boolean isUploadedFaceImage() {
        return realmGet$uploadedFaceImage();
    }

    public void setUploadedFaceImage(boolean z) {
        realmSet$uploadedFaceImage(z);
    }

    public String getRegisterId() {
        return realmGet$registerId();
    }

    public void setRegisterId(String str) {
        realmSet$registerId(str);
    }
}
