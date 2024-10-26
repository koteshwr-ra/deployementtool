package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.Record;
import com.ciot.realm.db.RegisteredRecordInfo;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_RecordRealmProxy;
import io.realm.exceptions.RealmException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.objectstore.OsObjectBuilder;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.spi.Configurator;

public class com_ciot_realm_db_RegisteredRecordInfoRealmProxy extends RegisteredRecordInfo implements RealmObjectProxy, com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private RegisteredRecordInfoColumnInfo columnInfo;
    private ProxyState<RegisteredRecordInfo> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "RegisteredRecordInfo";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class RegisteredRecordInfoColumnInfo extends ColumnInfo {
        long idColKey;
        long imageFilePathColKey;
        long ossFaceColKey;
        long recordColKey;
        long registerIdColKey;
        long uploadedFaceImageColKey;

        RegisteredRecordInfoColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(6);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.imageFilePathColKey = addColumnDetails("imageFilePath", "imageFilePath", objectSchemaInfo);
            this.recordColKey = addColumnDetails("record", "record", objectSchemaInfo);
            this.ossFaceColKey = addColumnDetails("ossFace", "ossFace", objectSchemaInfo);
            this.uploadedFaceImageColKey = addColumnDetails("uploadedFaceImage", "uploadedFaceImage", objectSchemaInfo);
            this.registerIdColKey = addColumnDetails("registerId", "registerId", objectSchemaInfo);
        }

        RegisteredRecordInfoColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new RegisteredRecordInfoColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            RegisteredRecordInfoColumnInfo registeredRecordInfoColumnInfo = (RegisteredRecordInfoColumnInfo) columnInfo;
            RegisteredRecordInfoColumnInfo registeredRecordInfoColumnInfo2 = (RegisteredRecordInfoColumnInfo) columnInfo2;
            registeredRecordInfoColumnInfo2.idColKey = registeredRecordInfoColumnInfo.idColKey;
            registeredRecordInfoColumnInfo2.imageFilePathColKey = registeredRecordInfoColumnInfo.imageFilePathColKey;
            registeredRecordInfoColumnInfo2.recordColKey = registeredRecordInfoColumnInfo.recordColKey;
            registeredRecordInfoColumnInfo2.ossFaceColKey = registeredRecordInfoColumnInfo.ossFaceColKey;
            registeredRecordInfoColumnInfo2.uploadedFaceImageColKey = registeredRecordInfoColumnInfo.uploadedFaceImageColKey;
            registeredRecordInfoColumnInfo2.registerIdColKey = registeredRecordInfoColumnInfo.registerIdColKey;
        }
    }

    com_ciot_realm_db_RegisteredRecordInfoRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (RegisteredRecordInfoColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<RegisteredRecordInfo> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public long realmGet$id() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.idColKey);
    }

    public void realmSet$id(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'id' cannot be changed after object was created.");
        }
    }

    public String realmGet$imageFilePath() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.imageFilePathColKey);
    }

    public void realmSet$imageFilePath(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.imageFilePathColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.imageFilePathColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.imageFilePathColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.imageFilePathColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public Record realmGet$record() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.recordColKey)) {
            return null;
        }
        return (Record) this.proxyState.getRealm$realm().get(Record.class, this.proxyState.getRow$realm().getLink(this.columnInfo.recordColKey), false, Collections.emptyList());
    }

    public void realmSet$record(Record record) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (record == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.recordColKey);
                return;
            }
            this.proxyState.checkValidObject(record);
            this.proxyState.getRow$realm().setLink(this.columnInfo.recordColKey, ((RealmObjectProxy) record).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("record")) {
            if (record != null && !RealmObject.isManaged(record)) {
                record = (Record) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(record, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (record == null) {
                row$realm.nullifyLink(this.columnInfo.recordColKey);
                return;
            }
            this.proxyState.checkValidObject(record);
            row$realm.getTable().setLink(this.columnInfo.recordColKey, row$realm.getObjectKey(), ((RealmObjectProxy) record).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public String realmGet$ossFace() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.ossFaceColKey);
    }

    public void realmSet$ossFace(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.ossFaceColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.ossFaceColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.ossFaceColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.ossFaceColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public boolean realmGet$uploadedFaceImage() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.uploadedFaceImageColKey);
    }

    public void realmSet$uploadedFaceImage(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.uploadedFaceImageColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.uploadedFaceImageColKey, row$realm.getObjectKey(), z, true);
        }
    }

    public String realmGet$registerId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.registerIdColKey);
    }

    public void realmSet$registerId(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.registerIdColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.registerIdColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.registerIdColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.registerIdColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 6, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("id", RealmFieldType.INTEGER, true, true, true);
        builder2.addPersistedProperty("imageFilePath", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("record", RealmFieldType.OBJECT, com_ciot_realm_db_RecordRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder3 = builder;
        builder3.addPersistedProperty("ossFace", RealmFieldType.STRING, false, false, false);
        builder3.addPersistedProperty("uploadedFaceImage", RealmFieldType.BOOLEAN, false, false, true);
        builder3.addPersistedProperty("registerId", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static RegisteredRecordInfoColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new RegisteredRecordInfoColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x011b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.RegisteredRecordInfo createOrUpdateUsingJsonObject(io.realm.Realm r15, org.json.JSONObject r16, boolean r17) throws org.json.JSONException {
        /*
            r0 = r15
            r7 = r16
            r8 = r17
            java.util.ArrayList r9 = new java.util.ArrayList
            r10 = 1
            r9.<init>(r10)
            java.lang.String r11 = "id"
            r12 = 0
            if (r8 == 0) goto L_0x0069
            java.lang.Class<com.ciot.realm.db.RegisteredRecordInfo> r1 = com.ciot.realm.db.RegisteredRecordInfo.class
            io.realm.internal.Table r1 = r15.getTable(r1)
            io.realm.RealmSchema r2 = r15.getSchema()
            java.lang.Class<com.ciot.realm.db.RegisteredRecordInfo> r3 = com.ciot.realm.db.RegisteredRecordInfo.class
            io.realm.internal.ColumnInfo r2 = r2.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)
            io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxy$RegisteredRecordInfoColumnInfo r2 = (io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxy.RegisteredRecordInfoColumnInfo) r2
            long r2 = r2.idColKey
            boolean r4 = r7.isNull(r11)
            r5 = -1
            if (r4 != 0) goto L_0x0035
            long r13 = r7.getLong(r11)
            long r2 = r1.findFirstLong(r2, r13)
            goto L_0x0036
        L_0x0035:
            r2 = r5
        L_0x0036:
            int r4 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r4 == 0) goto L_0x0069
            io.realm.BaseRealm$ThreadLocalRealmObjectContext r4 = io.realm.BaseRealm.objectContext
            java.lang.Object r4 = r4.get()
            r13 = r4
            io.realm.BaseRealm$RealmObjectContext r13 = (io.realm.BaseRealm.RealmObjectContext) r13
            io.realm.internal.UncheckedRow r3 = r1.getUncheckedRow(r2)     // Catch:{ all -> 0x0064 }
            io.realm.RealmSchema r1 = r15.getSchema()     // Catch:{ all -> 0x0064 }
            java.lang.Class<com.ciot.realm.db.RegisteredRecordInfo> r2 = com.ciot.realm.db.RegisteredRecordInfo.class
            io.realm.internal.ColumnInfo r4 = r1.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r2)     // Catch:{ all -> 0x0064 }
            r5 = 0
            java.util.List r6 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0064 }
            r1 = r13
            r2 = r15
            r1.set(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0064 }
            io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxy r1 = new io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxy     // Catch:{ all -> 0x0064 }
            r1.<init>()     // Catch:{ all -> 0x0064 }
            r13.clear()
            goto L_0x006a
        L_0x0064:
            r0 = move-exception
            r13.clear()
            throw r0
        L_0x0069:
            r1 = r12
        L_0x006a:
            java.lang.String r2 = "record"
            if (r1 != 0) goto L_0x00a5
            boolean r1 = r7.has(r2)
            if (r1 == 0) goto L_0x0077
            r9.add(r2)
        L_0x0077:
            boolean r1 = r7.has(r11)
            if (r1 == 0) goto L_0x009d
            boolean r1 = r7.isNull(r11)
            if (r1 == 0) goto L_0x008c
            java.lang.Class<com.ciot.realm.db.RegisteredRecordInfo> r1 = com.ciot.realm.db.RegisteredRecordInfo.class
            io.realm.RealmModel r1 = r15.createObjectInternal(r1, r12, r10, r9)
            io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxy r1 = (io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxy) r1
            goto L_0x00a5
        L_0x008c:
            java.lang.Class<com.ciot.realm.db.RegisteredRecordInfo> r1 = com.ciot.realm.db.RegisteredRecordInfo.class
            long r3 = r7.getLong(r11)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            io.realm.RealmModel r1 = r15.createObjectInternal(r1, r3, r10, r9)
            io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxy r1 = (io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxy) r1
            goto L_0x00a5
        L_0x009d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "JSON object doesn't have the primary key field 'id'."
            r0.<init>(r1)
            throw r0
        L_0x00a5:
            r3 = r1
            io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface r3 = (io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface) r3
            java.lang.String r4 = "imageFilePath"
            boolean r5 = r7.has(r4)
            if (r5 == 0) goto L_0x00c1
            boolean r5 = r7.isNull(r4)
            if (r5 == 0) goto L_0x00ba
            r3.realmSet$imageFilePath(r12)
            goto L_0x00c1
        L_0x00ba:
            java.lang.String r4 = r7.getString(r4)
            r3.realmSet$imageFilePath(r4)
        L_0x00c1:
            boolean r4 = r7.has(r2)
            if (r4 == 0) goto L_0x00dc
            boolean r4 = r7.isNull(r2)
            if (r4 == 0) goto L_0x00d1
            r3.realmSet$record(r12)
            goto L_0x00dc
        L_0x00d1:
            org.json.JSONObject r2 = r7.getJSONObject(r2)
            com.ciot.realm.db.Record r0 = io.realm.com_ciot_realm_db_RecordRealmProxy.createOrUpdateUsingJsonObject(r15, r2, r8)
            r3.realmSet$record(r0)
        L_0x00dc:
            java.lang.String r0 = "ossFace"
            boolean r2 = r7.has(r0)
            if (r2 == 0) goto L_0x00f5
            boolean r2 = r7.isNull(r0)
            if (r2 == 0) goto L_0x00ee
            r3.realmSet$ossFace(r12)
            goto L_0x00f5
        L_0x00ee:
            java.lang.String r0 = r7.getString(r0)
            r3.realmSet$ossFace(r0)
        L_0x00f5:
            java.lang.String r0 = "uploadedFaceImage"
            boolean r2 = r7.has(r0)
            if (r2 == 0) goto L_0x0113
            boolean r2 = r7.isNull(r0)
            if (r2 != 0) goto L_0x010b
            boolean r0 = r7.getBoolean(r0)
            r3.realmSet$uploadedFaceImage(r0)
            goto L_0x0113
        L_0x010b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Trying to set non-nullable field 'uploadedFaceImage' to null."
            r0.<init>(r1)
            throw r0
        L_0x0113:
            java.lang.String r0 = "registerId"
            boolean r2 = r7.has(r0)
            if (r2 == 0) goto L_0x012c
            boolean r2 = r7.isNull(r0)
            if (r2 == 0) goto L_0x0125
            r3.realmSet$registerId(r12)
            goto L_0x012c
        L_0x0125:
            java.lang.String r0 = r7.getString(r0)
            r3.realmSet$registerId(r0)
        L_0x012c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.RegisteredRecordInfo");
    }

    public static RegisteredRecordInfo createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        RegisteredRecordInfo registeredRecordInfo = new RegisteredRecordInfo();
        com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface com_ciot_realm_db_registeredrecordinforealmproxyinterface = registeredRecordInfo;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmSet$id(jsonReader.nextLong());
                    z = true;
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                }
            } else if (nextName.equals("imageFilePath")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmSet$imageFilePath(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmSet$imageFilePath((String) null);
                }
            } else if (nextName.equals("record")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmSet$record((Record) null);
                } else {
                    com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmSet$record(com_ciot_realm_db_RecordRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("ossFace")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmSet$ossFace(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmSet$ossFace((String) null);
                }
            } else if (nextName.equals("uploadedFaceImage")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmSet$uploadedFaceImage(jsonReader.nextBoolean());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'uploadedFaceImage' to null.");
                }
            } else if (!nextName.equals("registerId")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmSet$registerId(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmSet$registerId((String) null);
            }
        }
        jsonReader.endObject();
        if (z) {
            return (RegisteredRecordInfo) realm.copyToRealm(registeredRecordInfo, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_RegisteredRecordInfoRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) RegisteredRecordInfo.class), false, Collections.emptyList());
        com_ciot_realm_db_RegisteredRecordInfoRealmProxy com_ciot_realm_db_registeredrecordinforealmproxy = new com_ciot_realm_db_RegisteredRecordInfoRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_registeredrecordinforealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.RegisteredRecordInfo copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxy.RegisteredRecordInfoColumnInfo r9, com.ciot.realm.db.RegisteredRecordInfo r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
        /*
            boolean r0 = r10 instanceof io.realm.internal.RealmObjectProxy
            if (r0 == 0) goto L_0x003e
            boolean r0 = io.realm.RealmObject.isFrozen(r10)
            if (r0 != 0) goto L_0x003e
            r0 = r10
            io.realm.internal.RealmObjectProxy r0 = (io.realm.internal.RealmObjectProxy) r0
            io.realm.ProxyState r1 = r0.realmGet$proxyState()
            io.realm.BaseRealm r1 = r1.getRealm$realm()
            if (r1 == 0) goto L_0x003e
            io.realm.ProxyState r0 = r0.realmGet$proxyState()
            io.realm.BaseRealm r0 = r0.getRealm$realm()
            long r1 = r0.threadId
            long r3 = r8.threadId
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x0036
            java.lang.String r0 = r0.getPath()
            java.lang.String r1 = r8.getPath()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003e
            return r10
        L_0x0036:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "Objects which belong to Realm instances in other threads cannot be copied into this Realm instance."
            r8.<init>(r9)
            throw r8
        L_0x003e:
            io.realm.BaseRealm$ThreadLocalRealmObjectContext r0 = io.realm.BaseRealm.objectContext
            java.lang.Object r0 = r0.get()
            io.realm.BaseRealm$RealmObjectContext r0 = (io.realm.BaseRealm.RealmObjectContext) r0
            java.lang.Object r1 = r12.get(r10)
            io.realm.internal.RealmObjectProxy r1 = (io.realm.internal.RealmObjectProxy) r1
            if (r1 == 0) goto L_0x0051
            com.ciot.realm.db.RegisteredRecordInfo r1 = (com.ciot.realm.db.RegisteredRecordInfo) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0092
            java.lang.Class<com.ciot.realm.db.RegisteredRecordInfo> r2 = com.ciot.realm.db.RegisteredRecordInfo.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface) r5
            long r5 = r5.realmGet$id()
            long r3 = r2.findFirstLong(r3, r5)
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x006f
            r0 = 0
            goto L_0x0093
        L_0x006f:
            io.realm.internal.UncheckedRow r3 = r2.getUncheckedRow(r3)     // Catch:{ all -> 0x008d }
            r5 = 0
            java.util.List r6 = java.util.Collections.emptyList()     // Catch:{ all -> 0x008d }
            r1 = r0
            r2 = r8
            r4 = r9
            r1.set(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x008d }
            io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxy r1 = new io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxy     // Catch:{ all -> 0x008d }
            r1.<init>()     // Catch:{ all -> 0x008d }
            r2 = r1
            io.realm.internal.RealmObjectProxy r2 = (io.realm.internal.RealmObjectProxy) r2     // Catch:{ all -> 0x008d }
            r12.put(r10, r2)     // Catch:{ all -> 0x008d }
            r0.clear()
            goto L_0x0092
        L_0x008d:
            r8 = move-exception
            r0.clear()
            throw r8
        L_0x0092:
            r0 = r11
        L_0x0093:
            r3 = r1
            if (r0 == 0) goto L_0x00a0
            r1 = r8
            r2 = r9
            r4 = r10
            r5 = r12
            r6 = r13
            com.ciot.realm.db.RegisteredRecordInfo r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00a4
        L_0x00a0:
            com.ciot.realm.db.RegisteredRecordInfo r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00a4:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_RegisteredRecordInfoRealmProxy$RegisteredRecordInfoColumnInfo, com.ciot.realm.db.RegisteredRecordInfo, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.RegisteredRecordInfo");
    }

    public static RegisteredRecordInfo copy(Realm realm, RegisteredRecordInfoColumnInfo registeredRecordInfoColumnInfo, RegisteredRecordInfo registeredRecordInfo, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(registeredRecordInfo);
        if (realmObjectProxy != null) {
            return (RegisteredRecordInfo) realmObjectProxy;
        }
        com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface com_ciot_realm_db_registeredrecordinforealmproxyinterface = registeredRecordInfo;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(RegisteredRecordInfo.class), set);
        osObjectBuilder.addInteger(registeredRecordInfoColumnInfo.idColKey, Long.valueOf(com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$id()));
        osObjectBuilder.addString(registeredRecordInfoColumnInfo.imageFilePathColKey, com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$imageFilePath());
        osObjectBuilder.addString(registeredRecordInfoColumnInfo.ossFaceColKey, com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$ossFace());
        osObjectBuilder.addBoolean(registeredRecordInfoColumnInfo.uploadedFaceImageColKey, Boolean.valueOf(com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$uploadedFaceImage()));
        osObjectBuilder.addString(registeredRecordInfoColumnInfo.registerIdColKey, com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$registerId());
        com_ciot_realm_db_RegisteredRecordInfoRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(registeredRecordInfo, newProxyInstance);
        Record realmGet$record = com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$record();
        if (realmGet$record == null) {
            newProxyInstance.realmSet$record((Record) null);
        } else {
            Record record = (Record) map.get(realmGet$record);
            if (record != null) {
                newProxyInstance.realmSet$record(record);
            } else {
                newProxyInstance.realmSet$record(com_ciot_realm_db_RecordRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_RecordRealmProxy.RecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Record.class), realmGet$record, z, map, set));
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, RegisteredRecordInfo registeredRecordInfo, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        RegisteredRecordInfo registeredRecordInfo2 = registeredRecordInfo;
        Map<RealmModel, Long> map2 = map;
        if ((registeredRecordInfo2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(registeredRecordInfo)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) registeredRecordInfo2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(RegisteredRecordInfo.class);
        long nativePtr = table.getNativePtr();
        RegisteredRecordInfoColumnInfo registeredRecordInfoColumnInfo = (RegisteredRecordInfoColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RegisteredRecordInfo.class);
        long j2 = registeredRecordInfoColumnInfo.idColKey;
        com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface com_ciot_realm_db_registeredrecordinforealmproxyinterface = registeredRecordInfo2;
        Long valueOf = Long.valueOf(com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$id());
        if (valueOf != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$id());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$id()));
        } else {
            Table.throwDuplicatePrimaryKeyException(valueOf);
        }
        long j3 = j;
        map2.put(registeredRecordInfo2, Long.valueOf(j3));
        String realmGet$imageFilePath = com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$imageFilePath();
        if (realmGet$imageFilePath != null) {
            Table.nativeSetString(nativePtr, registeredRecordInfoColumnInfo.imageFilePathColKey, j3, realmGet$imageFilePath, false);
        }
        Record realmGet$record = com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$record();
        if (realmGet$record != null) {
            Long l = map2.get(realmGet$record);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_RecordRealmProxy.insert(realm2, realmGet$record, map2));
            }
            Table.nativeSetLink(nativePtr, registeredRecordInfoColumnInfo.recordColKey, j3, l.longValue(), false);
        }
        String realmGet$ossFace = com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$ossFace();
        if (realmGet$ossFace != null) {
            Table.nativeSetString(nativePtr, registeredRecordInfoColumnInfo.ossFaceColKey, j3, realmGet$ossFace, false);
        }
        Table.nativeSetBoolean(nativePtr, registeredRecordInfoColumnInfo.uploadedFaceImageColKey, j3, com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$uploadedFaceImage(), false);
        String realmGet$registerId = com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$registerId();
        if (realmGet$registerId != null) {
            Table.nativeSetString(nativePtr, registeredRecordInfoColumnInfo.registerIdColKey, j3, realmGet$registerId, false);
        }
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(RegisteredRecordInfo.class);
        long nativePtr = table.getNativePtr();
        RegisteredRecordInfoColumnInfo registeredRecordInfoColumnInfo = (RegisteredRecordInfoColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RegisteredRecordInfo.class);
        long j2 = registeredRecordInfoColumnInfo.idColKey;
        while (it.hasNext()) {
            RegisteredRecordInfo registeredRecordInfo = (RegisteredRecordInfo) it.next();
            if (!map2.containsKey(registeredRecordInfo)) {
                if ((registeredRecordInfo instanceof RealmObjectProxy) && !RealmObject.isFrozen(registeredRecordInfo)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) registeredRecordInfo;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(registeredRecordInfo, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface com_ciot_realm_db_registeredrecordinforealmproxyinterface = registeredRecordInfo;
                Long valueOf = Long.valueOf(com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$id());
                if (valueOf != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$id());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$id()));
                } else {
                    Table.throwDuplicatePrimaryKeyException(valueOf);
                }
                long j3 = j;
                map2.put(registeredRecordInfo, Long.valueOf(j3));
                String realmGet$imageFilePath = com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$imageFilePath();
                if (realmGet$imageFilePath != null) {
                    Table.nativeSetString(nativePtr, registeredRecordInfoColumnInfo.imageFilePathColKey, j3, realmGet$imageFilePath, false);
                }
                Record realmGet$record = com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$record();
                if (realmGet$record != null) {
                    Long l = map2.get(realmGet$record);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_RecordRealmProxy.insert(realm2, realmGet$record, map2));
                    }
                    table.setLink(registeredRecordInfoColumnInfo.recordColKey, j3, l.longValue(), false);
                }
                String realmGet$ossFace = com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$ossFace();
                if (realmGet$ossFace != null) {
                    Table.nativeSetString(nativePtr, registeredRecordInfoColumnInfo.ossFaceColKey, j3, realmGet$ossFace, false);
                }
                Table.nativeSetBoolean(nativePtr, registeredRecordInfoColumnInfo.uploadedFaceImageColKey, j3, com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$uploadedFaceImage(), false);
                String realmGet$registerId = com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$registerId();
                if (realmGet$registerId != null) {
                    Table.nativeSetString(nativePtr, registeredRecordInfoColumnInfo.registerIdColKey, j3, realmGet$registerId, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, RegisteredRecordInfo registeredRecordInfo, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        RegisteredRecordInfo registeredRecordInfo2 = registeredRecordInfo;
        Map<RealmModel, Long> map2 = map;
        if ((registeredRecordInfo2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(registeredRecordInfo)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) registeredRecordInfo2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(RegisteredRecordInfo.class);
        long nativePtr = table.getNativePtr();
        RegisteredRecordInfoColumnInfo registeredRecordInfoColumnInfo = (RegisteredRecordInfoColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RegisteredRecordInfo.class);
        long j2 = registeredRecordInfoColumnInfo.idColKey;
        com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface com_ciot_realm_db_registeredrecordinforealmproxyinterface = registeredRecordInfo2;
        if (Long.valueOf(com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$id()) != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$id());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$id()));
        }
        long j3 = j;
        map2.put(registeredRecordInfo2, Long.valueOf(j3));
        String realmGet$imageFilePath = com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$imageFilePath();
        if (realmGet$imageFilePath != null) {
            Table.nativeSetString(nativePtr, registeredRecordInfoColumnInfo.imageFilePathColKey, j3, realmGet$imageFilePath, false);
        } else {
            Table.nativeSetNull(nativePtr, registeredRecordInfoColumnInfo.imageFilePathColKey, j3, false);
        }
        Record realmGet$record = com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$record();
        if (realmGet$record != null) {
            Long l = map2.get(realmGet$record);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_RecordRealmProxy.insertOrUpdate(realm2, realmGet$record, map2));
            }
            Table.nativeSetLink(nativePtr, registeredRecordInfoColumnInfo.recordColKey, j3, l.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, registeredRecordInfoColumnInfo.recordColKey, j3);
        }
        String realmGet$ossFace = com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$ossFace();
        if (realmGet$ossFace != null) {
            Table.nativeSetString(nativePtr, registeredRecordInfoColumnInfo.ossFaceColKey, j3, realmGet$ossFace, false);
        } else {
            Table.nativeSetNull(nativePtr, registeredRecordInfoColumnInfo.ossFaceColKey, j3, false);
        }
        Table.nativeSetBoolean(nativePtr, registeredRecordInfoColumnInfo.uploadedFaceImageColKey, j3, com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$uploadedFaceImage(), false);
        String realmGet$registerId = com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$registerId();
        if (realmGet$registerId != null) {
            Table.nativeSetString(nativePtr, registeredRecordInfoColumnInfo.registerIdColKey, j3, realmGet$registerId, false);
        } else {
            Table.nativeSetNull(nativePtr, registeredRecordInfoColumnInfo.registerIdColKey, j3, false);
        }
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(RegisteredRecordInfo.class);
        long nativePtr = table.getNativePtr();
        RegisteredRecordInfoColumnInfo registeredRecordInfoColumnInfo = (RegisteredRecordInfoColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RegisteredRecordInfo.class);
        long j3 = registeredRecordInfoColumnInfo.idColKey;
        while (it.hasNext()) {
            RegisteredRecordInfo registeredRecordInfo = (RegisteredRecordInfo) it.next();
            if (!map2.containsKey(registeredRecordInfo)) {
                if ((registeredRecordInfo instanceof RealmObjectProxy) && !RealmObject.isFrozen(registeredRecordInfo)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) registeredRecordInfo;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(registeredRecordInfo, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface com_ciot_realm_db_registeredrecordinforealmproxyinterface = registeredRecordInfo;
                if (Long.valueOf(com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$id()) != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j3, com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$id());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j3, Long.valueOf(com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$id()));
                }
                long j4 = j;
                map2.put(registeredRecordInfo, Long.valueOf(j4));
                String realmGet$imageFilePath = com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$imageFilePath();
                if (realmGet$imageFilePath != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, registeredRecordInfoColumnInfo.imageFilePathColKey, j4, realmGet$imageFilePath, false);
                } else {
                    j2 = j3;
                    Table.nativeSetNull(nativePtr, registeredRecordInfoColumnInfo.imageFilePathColKey, j4, false);
                }
                Record realmGet$record = com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$record();
                if (realmGet$record != null) {
                    Long l = map2.get(realmGet$record);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_RecordRealmProxy.insertOrUpdate(realm2, realmGet$record, map2));
                    }
                    Table.nativeSetLink(nativePtr, registeredRecordInfoColumnInfo.recordColKey, j4, l.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, registeredRecordInfoColumnInfo.recordColKey, j4);
                }
                String realmGet$ossFace = com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$ossFace();
                if (realmGet$ossFace != null) {
                    Table.nativeSetString(nativePtr, registeredRecordInfoColumnInfo.ossFaceColKey, j4, realmGet$ossFace, false);
                } else {
                    Table.nativeSetNull(nativePtr, registeredRecordInfoColumnInfo.ossFaceColKey, j4, false);
                }
                Table.nativeSetBoolean(nativePtr, registeredRecordInfoColumnInfo.uploadedFaceImageColKey, j4, com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$uploadedFaceImage(), false);
                String realmGet$registerId = com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmGet$registerId();
                if (realmGet$registerId != null) {
                    Table.nativeSetString(nativePtr, registeredRecordInfoColumnInfo.registerIdColKey, j4, realmGet$registerId, false);
                } else {
                    Table.nativeSetNull(nativePtr, registeredRecordInfoColumnInfo.registerIdColKey, j4, false);
                }
                j3 = j2;
            }
        }
    }

    public static RegisteredRecordInfo createDetachedCopy(RegisteredRecordInfo registeredRecordInfo, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        RegisteredRecordInfo registeredRecordInfo2;
        if (i > i2 || registeredRecordInfo == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(registeredRecordInfo);
        if (cacheData == null) {
            registeredRecordInfo2 = new RegisteredRecordInfo();
            map.put(registeredRecordInfo, new RealmObjectProxy.CacheData(i, registeredRecordInfo2));
        } else if (i >= cacheData.minDepth) {
            return (RegisteredRecordInfo) cacheData.object;
        } else {
            cacheData.minDepth = i;
            registeredRecordInfo2 = (RegisteredRecordInfo) cacheData.object;
        }
        com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface com_ciot_realm_db_registeredrecordinforealmproxyinterface = registeredRecordInfo2;
        com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface com_ciot_realm_db_registeredrecordinforealmproxyinterface2 = registeredRecordInfo;
        com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmSet$id(com_ciot_realm_db_registeredrecordinforealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmSet$imageFilePath(com_ciot_realm_db_registeredrecordinforealmproxyinterface2.realmGet$imageFilePath());
        com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmSet$record(com_ciot_realm_db_RecordRealmProxy.createDetachedCopy(com_ciot_realm_db_registeredrecordinforealmproxyinterface2.realmGet$record(), i + 1, i2, map));
        com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmSet$ossFace(com_ciot_realm_db_registeredrecordinforealmproxyinterface2.realmGet$ossFace());
        com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmSet$uploadedFaceImage(com_ciot_realm_db_registeredrecordinforealmproxyinterface2.realmGet$uploadedFaceImage());
        com_ciot_realm_db_registeredrecordinforealmproxyinterface.realmSet$registerId(com_ciot_realm_db_registeredrecordinforealmproxyinterface2.realmGet$registerId());
        return registeredRecordInfo2;
    }

    static RegisteredRecordInfo update(Realm realm, RegisteredRecordInfoColumnInfo registeredRecordInfoColumnInfo, RegisteredRecordInfo registeredRecordInfo, RegisteredRecordInfo registeredRecordInfo2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface com_ciot_realm_db_registeredrecordinforealmproxyinterface = registeredRecordInfo;
        com_ciot_realm_db_RegisteredRecordInfoRealmProxyInterface com_ciot_realm_db_registeredrecordinforealmproxyinterface2 = registeredRecordInfo2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(RegisteredRecordInfo.class), set);
        osObjectBuilder.addInteger(registeredRecordInfoColumnInfo.idColKey, Long.valueOf(com_ciot_realm_db_registeredrecordinforealmproxyinterface2.realmGet$id()));
        osObjectBuilder.addString(registeredRecordInfoColumnInfo.imageFilePathColKey, com_ciot_realm_db_registeredrecordinforealmproxyinterface2.realmGet$imageFilePath());
        Record realmGet$record = com_ciot_realm_db_registeredrecordinforealmproxyinterface2.realmGet$record();
        if (realmGet$record == null) {
            osObjectBuilder.addNull(registeredRecordInfoColumnInfo.recordColKey);
        } else {
            Record record = (Record) map.get(realmGet$record);
            if (record != null) {
                osObjectBuilder.addObject(registeredRecordInfoColumnInfo.recordColKey, record);
            } else {
                osObjectBuilder.addObject(registeredRecordInfoColumnInfo.recordColKey, com_ciot_realm_db_RecordRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_RecordRealmProxy.RecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Record.class), realmGet$record, true, map, set));
            }
        }
        osObjectBuilder.addString(registeredRecordInfoColumnInfo.ossFaceColKey, com_ciot_realm_db_registeredrecordinforealmproxyinterface2.realmGet$ossFace());
        osObjectBuilder.addBoolean(registeredRecordInfoColumnInfo.uploadedFaceImageColKey, Boolean.valueOf(com_ciot_realm_db_registeredrecordinforealmproxyinterface2.realmGet$uploadedFaceImage()));
        osObjectBuilder.addString(registeredRecordInfoColumnInfo.registerIdColKey, com_ciot_realm_db_registeredrecordinforealmproxyinterface2.realmGet$registerId());
        osObjectBuilder.updateExistingObject();
        return registeredRecordInfo;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("RegisteredRecordInfo = proxy[");
        sb.append("{id:");
        sb.append(realmGet$id());
        sb.append("}");
        sb.append(",");
        sb.append("{imageFilePath:");
        String realmGet$imageFilePath = realmGet$imageFilePath();
        String str = Configurator.NULL;
        sb.append(realmGet$imageFilePath != null ? realmGet$imageFilePath() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{record:");
        sb.append(realmGet$record() != null ? com_ciot_realm_db_RecordRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME : str);
        sb.append("}");
        sb.append(",");
        sb.append("{ossFace:");
        sb.append(realmGet$ossFace() != null ? realmGet$ossFace() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{uploadedFaceImage:");
        sb.append(realmGet$uploadedFaceImage());
        sb.append("}");
        sb.append(",");
        sb.append("{registerId:");
        if (realmGet$registerId() != null) {
            str = realmGet$registerId();
        }
        sb.append(str);
        sb.append("}");
        sb.append("]");
        return sb.toString();
    }

    public ProxyState<?> realmGet$proxyState() {
        return this.proxyState;
    }

    public int hashCode() {
        String path = this.proxyState.getRealm$realm().getPath();
        String name = this.proxyState.getRow$realm().getTable().getName();
        long objectKey = this.proxyState.getRow$realm().getObjectKey();
        int i = 0;
        int hashCode = (527 + (path != null ? path.hashCode() : 0)) * 31;
        if (name != null) {
            i = name.hashCode();
        }
        return ((hashCode + i) * 31) + ((int) ((objectKey >>> 32) ^ objectKey));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        com_ciot_realm_db_RegisteredRecordInfoRealmProxy com_ciot_realm_db_registeredrecordinforealmproxy = (com_ciot_realm_db_RegisteredRecordInfoRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_registeredrecordinforealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_registeredrecordinforealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_registeredrecordinforealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
