package io.realm;

import com.ciot.realm.db.Record;

public interface com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface {
    long realmGet$id();

    String realmGet$imageFilePath();

    String realmGet$ossFace();

    Record realmGet$record();

    String realmGet$registerId();

    boolean realmGet$uploadedFaceImage();

    void realmSet$id(long j);

    void realmSet$imageFilePath(String str);

    void realmSet$ossFace(String str);

    void realmSet$record(Record record);

    void realmSet$registerId(String str);

    void realmSet$uploadedFaceImage(boolean z);
}
