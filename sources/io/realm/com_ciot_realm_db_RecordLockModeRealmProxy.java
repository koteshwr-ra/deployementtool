package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.RecordLockMode;
import io.realm.BaseRealm;
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
import org.json.JSONException;
import org.json.JSONObject;

public class com_ciot_realm_db_RecordLockModeRealmProxy extends RecordLockMode implements RealmObjectProxy, com_ciot_realm_db_RecordLockModeRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private RecordLockModeColumnInfo columnInfo;
    private ProxyState<RecordLockMode> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "RecordLockMode";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class RecordLockModeColumnInfo extends ColumnInfo {
        long cardColKey;
        long faceColKey;
        long iccardColKey;
        long idcardcodeColKey;

        RecordLockModeColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(4);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.faceColKey = addColumnDetails("face", "face", objectSchemaInfo);
            this.iccardColKey = addColumnDetails("iccard", "iccard", objectSchemaInfo);
            this.idcardcodeColKey = addColumnDetails("idcardcode", "idcardcode", objectSchemaInfo);
            this.cardColKey = addColumnDetails("card", "card", objectSchemaInfo);
        }

        RecordLockModeColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new RecordLockModeColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            RecordLockModeColumnInfo recordLockModeColumnInfo = (RecordLockModeColumnInfo) columnInfo;
            RecordLockModeColumnInfo recordLockModeColumnInfo2 = (RecordLockModeColumnInfo) columnInfo2;
            recordLockModeColumnInfo2.faceColKey = recordLockModeColumnInfo.faceColKey;
            recordLockModeColumnInfo2.iccardColKey = recordLockModeColumnInfo.iccardColKey;
            recordLockModeColumnInfo2.idcardcodeColKey = recordLockModeColumnInfo.idcardcodeColKey;
            recordLockModeColumnInfo2.cardColKey = recordLockModeColumnInfo.cardColKey;
        }
    }

    com_ciot_realm_db_RecordLockModeRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (RecordLockModeColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<RecordLockMode> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$face() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.faceColKey);
    }

    public void realmSet$face(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.faceColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.faceColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.faceColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.faceColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$iccard() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.iccardColKey);
    }

    public void realmSet$iccard(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.iccardColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.iccardColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.iccardColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.iccardColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$idcardcode() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.idcardcodeColKey);
    }

    public void realmSet$idcardcode(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.idcardcodeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.idcardcodeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.idcardcodeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.idcardcodeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$card() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.cardColKey);
    }

    public void realmSet$card(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.cardColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.cardColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.cardColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.cardColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 4, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("face", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("iccard", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("idcardcode", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("card", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static RecordLockModeColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new RecordLockModeColumnInfo(osSchemaInfo);
    }

    public static RecordLockMode createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        RecordLockMode recordLockMode = (RecordLockMode) realm.createObjectInternal(RecordLockMode.class, true, Collections.emptyList());
        com_ciot_realm_db_RecordLockModeRealmProxyInterface com_ciot_realm_db_recordlockmoderealmproxyinterface = recordLockMode;
        if (jSONObject.has("face")) {
            if (jSONObject.isNull("face")) {
                com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$face((String) null);
            } else {
                com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$face(jSONObject.getString("face"));
            }
        }
        if (jSONObject.has("iccard")) {
            if (jSONObject.isNull("iccard")) {
                com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$iccard((String) null);
            } else {
                com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$iccard(jSONObject.getString("iccard"));
            }
        }
        if (jSONObject.has("idcardcode")) {
            if (jSONObject.isNull("idcardcode")) {
                com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$idcardcode((String) null);
            } else {
                com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$idcardcode(jSONObject.getString("idcardcode"));
            }
        }
        if (jSONObject.has("card")) {
            if (jSONObject.isNull("card")) {
                com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$card((String) null);
            } else {
                com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$card(jSONObject.getString("card"));
            }
        }
        return recordLockMode;
    }

    public static RecordLockMode createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        RecordLockMode recordLockMode = new RecordLockMode();
        com_ciot_realm_db_RecordLockModeRealmProxyInterface com_ciot_realm_db_recordlockmoderealmproxyinterface = recordLockMode;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("face")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$face(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$face((String) null);
                }
            } else if (nextName.equals("iccard")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$iccard(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$iccard((String) null);
                }
            } else if (nextName.equals("idcardcode")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$idcardcode(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$idcardcode((String) null);
                }
            } else if (!nextName.equals("card")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$card(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$card((String) null);
            }
        }
        jsonReader.endObject();
        return (RecordLockMode) realm.copyToRealm(recordLockMode, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_RecordLockModeRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) RecordLockMode.class), false, Collections.emptyList());
        com_ciot_realm_db_RecordLockModeRealmProxy com_ciot_realm_db_recordlockmoderealmproxy = new com_ciot_realm_db_RecordLockModeRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_recordlockmoderealmproxy;
    }

    public static RecordLockMode copyOrUpdate(Realm realm, RecordLockModeColumnInfo recordLockModeColumnInfo, RecordLockMode recordLockMode, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((recordLockMode instanceof RealmObjectProxy) && !RealmObject.isFrozen(recordLockMode)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) recordLockMode;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return recordLockMode;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(recordLockMode);
        if (realmObjectProxy2 != null) {
            return (RecordLockMode) realmObjectProxy2;
        }
        return copy(realm, recordLockModeColumnInfo, recordLockMode, z, map, set);
    }

    public static RecordLockMode copy(Realm realm, RecordLockModeColumnInfo recordLockModeColumnInfo, RecordLockMode recordLockMode, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(recordLockMode);
        if (realmObjectProxy != null) {
            return (RecordLockMode) realmObjectProxy;
        }
        com_ciot_realm_db_RecordLockModeRealmProxyInterface com_ciot_realm_db_recordlockmoderealmproxyinterface = recordLockMode;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(RecordLockMode.class), set);
        osObjectBuilder.addString(recordLockModeColumnInfo.faceColKey, com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$face());
        osObjectBuilder.addString(recordLockModeColumnInfo.iccardColKey, com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$iccard());
        osObjectBuilder.addString(recordLockModeColumnInfo.idcardcodeColKey, com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$idcardcode());
        osObjectBuilder.addString(recordLockModeColumnInfo.cardColKey, com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$card());
        com_ciot_realm_db_RecordLockModeRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(recordLockMode, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, RecordLockMode recordLockMode, Map<RealmModel, Long> map) {
        if ((recordLockMode instanceof RealmObjectProxy) && !RealmObject.isFrozen(recordLockMode)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) recordLockMode;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(RecordLockMode.class);
        long nativePtr = table.getNativePtr();
        RecordLockModeColumnInfo recordLockModeColumnInfo = (RecordLockModeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RecordLockMode.class);
        long createRow = OsObject.createRow(table);
        map.put(recordLockMode, Long.valueOf(createRow));
        com_ciot_realm_db_RecordLockModeRealmProxyInterface com_ciot_realm_db_recordlockmoderealmproxyinterface = recordLockMode;
        String realmGet$face = com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$face();
        if (realmGet$face != null) {
            Table.nativeSetString(nativePtr, recordLockModeColumnInfo.faceColKey, createRow, realmGet$face, false);
        }
        String realmGet$iccard = com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$iccard();
        if (realmGet$iccard != null) {
            Table.nativeSetString(nativePtr, recordLockModeColumnInfo.iccardColKey, createRow, realmGet$iccard, false);
        }
        String realmGet$idcardcode = com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$idcardcode();
        if (realmGet$idcardcode != null) {
            Table.nativeSetString(nativePtr, recordLockModeColumnInfo.idcardcodeColKey, createRow, realmGet$idcardcode, false);
        }
        String realmGet$card = com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$card();
        if (realmGet$card != null) {
            Table.nativeSetString(nativePtr, recordLockModeColumnInfo.cardColKey, createRow, realmGet$card, false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(RecordLockMode.class);
        long nativePtr = table.getNativePtr();
        RecordLockModeColumnInfo recordLockModeColumnInfo = (RecordLockModeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RecordLockMode.class);
        while (it.hasNext()) {
            RecordLockMode recordLockMode = (RecordLockMode) it.next();
            if (!map2.containsKey(recordLockMode)) {
                if ((recordLockMode instanceof RealmObjectProxy) && !RealmObject.isFrozen(recordLockMode)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) recordLockMode;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(recordLockMode, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(recordLockMode, Long.valueOf(createRow));
                com_ciot_realm_db_RecordLockModeRealmProxyInterface com_ciot_realm_db_recordlockmoderealmproxyinterface = recordLockMode;
                String realmGet$face = com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$face();
                if (realmGet$face != null) {
                    Table.nativeSetString(nativePtr, recordLockModeColumnInfo.faceColKey, createRow, realmGet$face, false);
                }
                String realmGet$iccard = com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$iccard();
                if (realmGet$iccard != null) {
                    Table.nativeSetString(nativePtr, recordLockModeColumnInfo.iccardColKey, createRow, realmGet$iccard, false);
                }
                String realmGet$idcardcode = com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$idcardcode();
                if (realmGet$idcardcode != null) {
                    Table.nativeSetString(nativePtr, recordLockModeColumnInfo.idcardcodeColKey, createRow, realmGet$idcardcode, false);
                }
                String realmGet$card = com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$card();
                if (realmGet$card != null) {
                    Table.nativeSetString(nativePtr, recordLockModeColumnInfo.cardColKey, createRow, realmGet$card, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, RecordLockMode recordLockMode, Map<RealmModel, Long> map) {
        if ((recordLockMode instanceof RealmObjectProxy) && !RealmObject.isFrozen(recordLockMode)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) recordLockMode;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(RecordLockMode.class);
        long nativePtr = table.getNativePtr();
        RecordLockModeColumnInfo recordLockModeColumnInfo = (RecordLockModeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RecordLockMode.class);
        long createRow = OsObject.createRow(table);
        map.put(recordLockMode, Long.valueOf(createRow));
        com_ciot_realm_db_RecordLockModeRealmProxyInterface com_ciot_realm_db_recordlockmoderealmproxyinterface = recordLockMode;
        String realmGet$face = com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$face();
        if (realmGet$face != null) {
            Table.nativeSetString(nativePtr, recordLockModeColumnInfo.faceColKey, createRow, realmGet$face, false);
        } else {
            Table.nativeSetNull(nativePtr, recordLockModeColumnInfo.faceColKey, createRow, false);
        }
        String realmGet$iccard = com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$iccard();
        if (realmGet$iccard != null) {
            Table.nativeSetString(nativePtr, recordLockModeColumnInfo.iccardColKey, createRow, realmGet$iccard, false);
        } else {
            Table.nativeSetNull(nativePtr, recordLockModeColumnInfo.iccardColKey, createRow, false);
        }
        String realmGet$idcardcode = com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$idcardcode();
        if (realmGet$idcardcode != null) {
            Table.nativeSetString(nativePtr, recordLockModeColumnInfo.idcardcodeColKey, createRow, realmGet$idcardcode, false);
        } else {
            Table.nativeSetNull(nativePtr, recordLockModeColumnInfo.idcardcodeColKey, createRow, false);
        }
        String realmGet$card = com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$card();
        if (realmGet$card != null) {
            Table.nativeSetString(nativePtr, recordLockModeColumnInfo.cardColKey, createRow, realmGet$card, false);
        } else {
            Table.nativeSetNull(nativePtr, recordLockModeColumnInfo.cardColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(RecordLockMode.class);
        long nativePtr = table.getNativePtr();
        RecordLockModeColumnInfo recordLockModeColumnInfo = (RecordLockModeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RecordLockMode.class);
        while (it.hasNext()) {
            RecordLockMode recordLockMode = (RecordLockMode) it.next();
            if (!map2.containsKey(recordLockMode)) {
                if ((recordLockMode instanceof RealmObjectProxy) && !RealmObject.isFrozen(recordLockMode)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) recordLockMode;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(recordLockMode, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(recordLockMode, Long.valueOf(createRow));
                com_ciot_realm_db_RecordLockModeRealmProxyInterface com_ciot_realm_db_recordlockmoderealmproxyinterface = recordLockMode;
                String realmGet$face = com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$face();
                if (realmGet$face != null) {
                    Table.nativeSetString(nativePtr, recordLockModeColumnInfo.faceColKey, createRow, realmGet$face, false);
                } else {
                    Table.nativeSetNull(nativePtr, recordLockModeColumnInfo.faceColKey, createRow, false);
                }
                String realmGet$iccard = com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$iccard();
                if (realmGet$iccard != null) {
                    Table.nativeSetString(nativePtr, recordLockModeColumnInfo.iccardColKey, createRow, realmGet$iccard, false);
                } else {
                    Table.nativeSetNull(nativePtr, recordLockModeColumnInfo.iccardColKey, createRow, false);
                }
                String realmGet$idcardcode = com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$idcardcode();
                if (realmGet$idcardcode != null) {
                    Table.nativeSetString(nativePtr, recordLockModeColumnInfo.idcardcodeColKey, createRow, realmGet$idcardcode, false);
                } else {
                    Table.nativeSetNull(nativePtr, recordLockModeColumnInfo.idcardcodeColKey, createRow, false);
                }
                String realmGet$card = com_ciot_realm_db_recordlockmoderealmproxyinterface.realmGet$card();
                if (realmGet$card != null) {
                    Table.nativeSetString(nativePtr, recordLockModeColumnInfo.cardColKey, createRow, realmGet$card, false);
                } else {
                    Table.nativeSetNull(nativePtr, recordLockModeColumnInfo.cardColKey, createRow, false);
                }
            }
        }
    }

    public static RecordLockMode createDetachedCopy(RecordLockMode recordLockMode, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        RecordLockMode recordLockMode2;
        if (i > i2 || recordLockMode == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(recordLockMode);
        if (cacheData == null) {
            recordLockMode2 = new RecordLockMode();
            map.put(recordLockMode, new RealmObjectProxy.CacheData(i, recordLockMode2));
        } else if (i >= cacheData.minDepth) {
            return (RecordLockMode) cacheData.object;
        } else {
            cacheData.minDepth = i;
            recordLockMode2 = (RecordLockMode) cacheData.object;
        }
        com_ciot_realm_db_RecordLockModeRealmProxyInterface com_ciot_realm_db_recordlockmoderealmproxyinterface = recordLockMode2;
        com_ciot_realm_db_RecordLockModeRealmProxyInterface com_ciot_realm_db_recordlockmoderealmproxyinterface2 = recordLockMode;
        com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$face(com_ciot_realm_db_recordlockmoderealmproxyinterface2.realmGet$face());
        com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$iccard(com_ciot_realm_db_recordlockmoderealmproxyinterface2.realmGet$iccard());
        com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$idcardcode(com_ciot_realm_db_recordlockmoderealmproxyinterface2.realmGet$idcardcode());
        com_ciot_realm_db_recordlockmoderealmproxyinterface.realmSet$card(com_ciot_realm_db_recordlockmoderealmproxyinterface2.realmGet$card());
        return recordLockMode2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("RecordLockMode = proxy[");
        sb.append("{face:");
        String realmGet$face = realmGet$face();
        String str = Configurator.NULL;
        sb.append(realmGet$face != null ? realmGet$face() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{iccard:");
        sb.append(realmGet$iccard() != null ? realmGet$iccard() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{idcardcode:");
        sb.append(realmGet$idcardcode() != null ? realmGet$idcardcode() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{card:");
        if (realmGet$card() != null) {
            str = realmGet$card();
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
        com_ciot_realm_db_RecordLockModeRealmProxy com_ciot_realm_db_recordlockmoderealmproxy = (com_ciot_realm_db_RecordLockModeRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_recordlockmoderealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_recordlockmoderealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_recordlockmoderealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
