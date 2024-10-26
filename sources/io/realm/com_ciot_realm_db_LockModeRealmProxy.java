package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.LockMode;
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

public class com_ciot_realm_db_LockModeRealmProxy extends LockMode implements RealmObjectProxy, com_ciot_realm_db_LockModeRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private LockModeColumnInfo columnInfo;
    private ProxyState<LockMode> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "LockMode";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class LockModeColumnInfo extends ColumnInfo {
        long cardColKey;
        long faceColKey;
        long iccardColKey;
        long idcardcodeColKey;

        LockModeColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(4);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.faceColKey = addColumnDetails("face", "face", objectSchemaInfo);
            this.iccardColKey = addColumnDetails("iccard", "iccard", objectSchemaInfo);
            this.idcardcodeColKey = addColumnDetails("idcardcode", "idcardcode", objectSchemaInfo);
            this.cardColKey = addColumnDetails("card", "card", objectSchemaInfo);
        }

        LockModeColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new LockModeColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            LockModeColumnInfo lockModeColumnInfo = (LockModeColumnInfo) columnInfo;
            LockModeColumnInfo lockModeColumnInfo2 = (LockModeColumnInfo) columnInfo2;
            lockModeColumnInfo2.faceColKey = lockModeColumnInfo.faceColKey;
            lockModeColumnInfo2.iccardColKey = lockModeColumnInfo.iccardColKey;
            lockModeColumnInfo2.idcardcodeColKey = lockModeColumnInfo.idcardcodeColKey;
            lockModeColumnInfo2.cardColKey = lockModeColumnInfo.cardColKey;
        }
    }

    com_ciot_realm_db_LockModeRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (LockModeColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<LockMode> proxyState2 = new ProxyState<>(this);
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

    public static LockModeColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new LockModeColumnInfo(osSchemaInfo);
    }

    public static LockMode createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        LockMode lockMode = (LockMode) realm.createObjectInternal(LockMode.class, true, Collections.emptyList());
        com_ciot_realm_db_LockModeRealmProxyInterface com_ciot_realm_db_lockmoderealmproxyinterface = lockMode;
        if (jSONObject.has("face")) {
            if (jSONObject.isNull("face")) {
                com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$face((String) null);
            } else {
                com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$face(jSONObject.getString("face"));
            }
        }
        if (jSONObject.has("iccard")) {
            if (jSONObject.isNull("iccard")) {
                com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$iccard((String) null);
            } else {
                com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$iccard(jSONObject.getString("iccard"));
            }
        }
        if (jSONObject.has("idcardcode")) {
            if (jSONObject.isNull("idcardcode")) {
                com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$idcardcode((String) null);
            } else {
                com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$idcardcode(jSONObject.getString("idcardcode"));
            }
        }
        if (jSONObject.has("card")) {
            if (jSONObject.isNull("card")) {
                com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$card((String) null);
            } else {
                com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$card(jSONObject.getString("card"));
            }
        }
        return lockMode;
    }

    public static LockMode createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        LockMode lockMode = new LockMode();
        com_ciot_realm_db_LockModeRealmProxyInterface com_ciot_realm_db_lockmoderealmproxyinterface = lockMode;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("face")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$face(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$face((String) null);
                }
            } else if (nextName.equals("iccard")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$iccard(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$iccard((String) null);
                }
            } else if (nextName.equals("idcardcode")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$idcardcode(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$idcardcode((String) null);
                }
            } else if (!nextName.equals("card")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$card(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$card((String) null);
            }
        }
        jsonReader.endObject();
        return (LockMode) realm.copyToRealm(lockMode, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_LockModeRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) LockMode.class), false, Collections.emptyList());
        com_ciot_realm_db_LockModeRealmProxy com_ciot_realm_db_lockmoderealmproxy = new com_ciot_realm_db_LockModeRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_lockmoderealmproxy;
    }

    public static LockMode copyOrUpdate(Realm realm, LockModeColumnInfo lockModeColumnInfo, LockMode lockMode, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((lockMode instanceof RealmObjectProxy) && !RealmObject.isFrozen(lockMode)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) lockMode;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return lockMode;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(lockMode);
        if (realmObjectProxy2 != null) {
            return (LockMode) realmObjectProxy2;
        }
        return copy(realm, lockModeColumnInfo, lockMode, z, map, set);
    }

    public static LockMode copy(Realm realm, LockModeColumnInfo lockModeColumnInfo, LockMode lockMode, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(lockMode);
        if (realmObjectProxy != null) {
            return (LockMode) realmObjectProxy;
        }
        com_ciot_realm_db_LockModeRealmProxyInterface com_ciot_realm_db_lockmoderealmproxyinterface = lockMode;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(LockMode.class), set);
        osObjectBuilder.addString(lockModeColumnInfo.faceColKey, com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$face());
        osObjectBuilder.addString(lockModeColumnInfo.iccardColKey, com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$iccard());
        osObjectBuilder.addString(lockModeColumnInfo.idcardcodeColKey, com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$idcardcode());
        osObjectBuilder.addString(lockModeColumnInfo.cardColKey, com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$card());
        com_ciot_realm_db_LockModeRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(lockMode, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, LockMode lockMode, Map<RealmModel, Long> map) {
        if ((lockMode instanceof RealmObjectProxy) && !RealmObject.isFrozen(lockMode)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) lockMode;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(LockMode.class);
        long nativePtr = table.getNativePtr();
        LockModeColumnInfo lockModeColumnInfo = (LockModeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) LockMode.class);
        long createRow = OsObject.createRow(table);
        map.put(lockMode, Long.valueOf(createRow));
        com_ciot_realm_db_LockModeRealmProxyInterface com_ciot_realm_db_lockmoderealmproxyinterface = lockMode;
        String realmGet$face = com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$face();
        if (realmGet$face != null) {
            Table.nativeSetString(nativePtr, lockModeColumnInfo.faceColKey, createRow, realmGet$face, false);
        }
        String realmGet$iccard = com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$iccard();
        if (realmGet$iccard != null) {
            Table.nativeSetString(nativePtr, lockModeColumnInfo.iccardColKey, createRow, realmGet$iccard, false);
        }
        String realmGet$idcardcode = com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$idcardcode();
        if (realmGet$idcardcode != null) {
            Table.nativeSetString(nativePtr, lockModeColumnInfo.idcardcodeColKey, createRow, realmGet$idcardcode, false);
        }
        String realmGet$card = com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$card();
        if (realmGet$card != null) {
            Table.nativeSetString(nativePtr, lockModeColumnInfo.cardColKey, createRow, realmGet$card, false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(LockMode.class);
        long nativePtr = table.getNativePtr();
        LockModeColumnInfo lockModeColumnInfo = (LockModeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) LockMode.class);
        while (it.hasNext()) {
            LockMode lockMode = (LockMode) it.next();
            if (!map2.containsKey(lockMode)) {
                if ((lockMode instanceof RealmObjectProxy) && !RealmObject.isFrozen(lockMode)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) lockMode;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(lockMode, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(lockMode, Long.valueOf(createRow));
                com_ciot_realm_db_LockModeRealmProxyInterface com_ciot_realm_db_lockmoderealmproxyinterface = lockMode;
                String realmGet$face = com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$face();
                if (realmGet$face != null) {
                    Table.nativeSetString(nativePtr, lockModeColumnInfo.faceColKey, createRow, realmGet$face, false);
                }
                String realmGet$iccard = com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$iccard();
                if (realmGet$iccard != null) {
                    Table.nativeSetString(nativePtr, lockModeColumnInfo.iccardColKey, createRow, realmGet$iccard, false);
                }
                String realmGet$idcardcode = com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$idcardcode();
                if (realmGet$idcardcode != null) {
                    Table.nativeSetString(nativePtr, lockModeColumnInfo.idcardcodeColKey, createRow, realmGet$idcardcode, false);
                }
                String realmGet$card = com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$card();
                if (realmGet$card != null) {
                    Table.nativeSetString(nativePtr, lockModeColumnInfo.cardColKey, createRow, realmGet$card, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, LockMode lockMode, Map<RealmModel, Long> map) {
        if ((lockMode instanceof RealmObjectProxy) && !RealmObject.isFrozen(lockMode)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) lockMode;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(LockMode.class);
        long nativePtr = table.getNativePtr();
        LockModeColumnInfo lockModeColumnInfo = (LockModeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) LockMode.class);
        long createRow = OsObject.createRow(table);
        map.put(lockMode, Long.valueOf(createRow));
        com_ciot_realm_db_LockModeRealmProxyInterface com_ciot_realm_db_lockmoderealmproxyinterface = lockMode;
        String realmGet$face = com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$face();
        if (realmGet$face != null) {
            Table.nativeSetString(nativePtr, lockModeColumnInfo.faceColKey, createRow, realmGet$face, false);
        } else {
            Table.nativeSetNull(nativePtr, lockModeColumnInfo.faceColKey, createRow, false);
        }
        String realmGet$iccard = com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$iccard();
        if (realmGet$iccard != null) {
            Table.nativeSetString(nativePtr, lockModeColumnInfo.iccardColKey, createRow, realmGet$iccard, false);
        } else {
            Table.nativeSetNull(nativePtr, lockModeColumnInfo.iccardColKey, createRow, false);
        }
        String realmGet$idcardcode = com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$idcardcode();
        if (realmGet$idcardcode != null) {
            Table.nativeSetString(nativePtr, lockModeColumnInfo.idcardcodeColKey, createRow, realmGet$idcardcode, false);
        } else {
            Table.nativeSetNull(nativePtr, lockModeColumnInfo.idcardcodeColKey, createRow, false);
        }
        String realmGet$card = com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$card();
        if (realmGet$card != null) {
            Table.nativeSetString(nativePtr, lockModeColumnInfo.cardColKey, createRow, realmGet$card, false);
        } else {
            Table.nativeSetNull(nativePtr, lockModeColumnInfo.cardColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(LockMode.class);
        long nativePtr = table.getNativePtr();
        LockModeColumnInfo lockModeColumnInfo = (LockModeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) LockMode.class);
        while (it.hasNext()) {
            LockMode lockMode = (LockMode) it.next();
            if (!map2.containsKey(lockMode)) {
                if ((lockMode instanceof RealmObjectProxy) && !RealmObject.isFrozen(lockMode)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) lockMode;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(lockMode, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(lockMode, Long.valueOf(createRow));
                com_ciot_realm_db_LockModeRealmProxyInterface com_ciot_realm_db_lockmoderealmproxyinterface = lockMode;
                String realmGet$face = com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$face();
                if (realmGet$face != null) {
                    Table.nativeSetString(nativePtr, lockModeColumnInfo.faceColKey, createRow, realmGet$face, false);
                } else {
                    Table.nativeSetNull(nativePtr, lockModeColumnInfo.faceColKey, createRow, false);
                }
                String realmGet$iccard = com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$iccard();
                if (realmGet$iccard != null) {
                    Table.nativeSetString(nativePtr, lockModeColumnInfo.iccardColKey, createRow, realmGet$iccard, false);
                } else {
                    Table.nativeSetNull(nativePtr, lockModeColumnInfo.iccardColKey, createRow, false);
                }
                String realmGet$idcardcode = com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$idcardcode();
                if (realmGet$idcardcode != null) {
                    Table.nativeSetString(nativePtr, lockModeColumnInfo.idcardcodeColKey, createRow, realmGet$idcardcode, false);
                } else {
                    Table.nativeSetNull(nativePtr, lockModeColumnInfo.idcardcodeColKey, createRow, false);
                }
                String realmGet$card = com_ciot_realm_db_lockmoderealmproxyinterface.realmGet$card();
                if (realmGet$card != null) {
                    Table.nativeSetString(nativePtr, lockModeColumnInfo.cardColKey, createRow, realmGet$card, false);
                } else {
                    Table.nativeSetNull(nativePtr, lockModeColumnInfo.cardColKey, createRow, false);
                }
            }
        }
    }

    public static LockMode createDetachedCopy(LockMode lockMode, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        LockMode lockMode2;
        if (i > i2 || lockMode == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(lockMode);
        if (cacheData == null) {
            lockMode2 = new LockMode();
            map.put(lockMode, new RealmObjectProxy.CacheData(i, lockMode2));
        } else if (i >= cacheData.minDepth) {
            return (LockMode) cacheData.object;
        } else {
            cacheData.minDepth = i;
            lockMode2 = (LockMode) cacheData.object;
        }
        com_ciot_realm_db_LockModeRealmProxyInterface com_ciot_realm_db_lockmoderealmproxyinterface = lockMode2;
        com_ciot_realm_db_LockModeRealmProxyInterface com_ciot_realm_db_lockmoderealmproxyinterface2 = lockMode;
        com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$face(com_ciot_realm_db_lockmoderealmproxyinterface2.realmGet$face());
        com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$iccard(com_ciot_realm_db_lockmoderealmproxyinterface2.realmGet$iccard());
        com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$idcardcode(com_ciot_realm_db_lockmoderealmproxyinterface2.realmGet$idcardcode());
        com_ciot_realm_db_lockmoderealmproxyinterface.realmSet$card(com_ciot_realm_db_lockmoderealmproxyinterface2.realmGet$card());
        return lockMode2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("LockMode = proxy[");
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
        com_ciot_realm_db_LockModeRealmProxy com_ciot_realm_db_lockmoderealmproxy = (com_ciot_realm_db_LockModeRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_lockmoderealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_lockmoderealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_lockmoderealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
