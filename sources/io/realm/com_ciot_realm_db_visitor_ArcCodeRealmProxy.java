package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.visitor.ArcCode;
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
import org.json.JSONException;
import org.json.JSONObject;
import xcrash.TombstoneParser;

public class com_ciot_realm_db_visitor_ArcCodeRealmProxy extends ArcCode implements RealmObjectProxy, com_ciot_realm_db_visitor_ArcCodeRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ArcCodeColumnInfo columnInfo;
    private ProxyState<ArcCode> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "ArcCode";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class ArcCodeColumnInfo extends ColumnInfo {
        long codeColKey;
        long typeColKey;

        ArcCodeColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.typeColKey = addColumnDetails("type", "type", objectSchemaInfo);
            this.codeColKey = addColumnDetails(TombstoneParser.keyCode, TombstoneParser.keyCode, objectSchemaInfo);
        }

        ArcCodeColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new ArcCodeColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            ArcCodeColumnInfo arcCodeColumnInfo = (ArcCodeColumnInfo) columnInfo;
            ArcCodeColumnInfo arcCodeColumnInfo2 = (ArcCodeColumnInfo) columnInfo2;
            arcCodeColumnInfo2.typeColKey = arcCodeColumnInfo.typeColKey;
            arcCodeColumnInfo2.codeColKey = arcCodeColumnInfo.codeColKey;
        }
    }

    com_ciot_realm_db_visitor_ArcCodeRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ArcCodeColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<ArcCode> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$type() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.typeColKey);
    }

    public void realmSet$type(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.typeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.typeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.typeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.typeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$code() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.codeColKey);
    }

    public void realmSet$code(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.codeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.codeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.codeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.codeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 2, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("type", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(TombstoneParser.keyCode, RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ArcCodeColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new ArcCodeColumnInfo(osSchemaInfo);
    }

    public static ArcCode createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        ArcCode arcCode = (ArcCode) realm.createObjectInternal(ArcCode.class, true, Collections.emptyList());
        com_ciot_realm_db_visitor_ArcCodeRealmProxyInterface com_ciot_realm_db_visitor_arccoderealmproxyinterface = arcCode;
        if (jSONObject.has("type")) {
            if (jSONObject.isNull("type")) {
                com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmSet$type((String) null);
            } else {
                com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmSet$type(jSONObject.getString("type"));
            }
        }
        if (jSONObject.has(TombstoneParser.keyCode)) {
            if (jSONObject.isNull(TombstoneParser.keyCode)) {
                com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmSet$code((String) null);
            } else {
                com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmSet$code(jSONObject.getString(TombstoneParser.keyCode));
            }
        }
        return arcCode;
    }

    public static ArcCode createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        ArcCode arcCode = new ArcCode();
        com_ciot_realm_db_visitor_ArcCodeRealmProxyInterface com_ciot_realm_db_visitor_arccoderealmproxyinterface = arcCode;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("type")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmSet$type(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmSet$type((String) null);
                }
            } else if (!nextName.equals(TombstoneParser.keyCode)) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmSet$code(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmSet$code((String) null);
            }
        }
        jsonReader.endObject();
        return (ArcCode) realm.copyToRealm(arcCode, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_visitor_ArcCodeRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) ArcCode.class), false, Collections.emptyList());
        com_ciot_realm_db_visitor_ArcCodeRealmProxy com_ciot_realm_db_visitor_arccoderealmproxy = new com_ciot_realm_db_visitor_ArcCodeRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_visitor_arccoderealmproxy;
    }

    public static ArcCode copyOrUpdate(Realm realm, ArcCodeColumnInfo arcCodeColumnInfo, ArcCode arcCode, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((arcCode instanceof RealmObjectProxy) && !RealmObject.isFrozen(arcCode)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) arcCode;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return arcCode;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(arcCode);
        if (realmObjectProxy2 != null) {
            return (ArcCode) realmObjectProxy2;
        }
        return copy(realm, arcCodeColumnInfo, arcCode, z, map, set);
    }

    public static ArcCode copy(Realm realm, ArcCodeColumnInfo arcCodeColumnInfo, ArcCode arcCode, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(arcCode);
        if (realmObjectProxy != null) {
            return (ArcCode) realmObjectProxy;
        }
        com_ciot_realm_db_visitor_ArcCodeRealmProxyInterface com_ciot_realm_db_visitor_arccoderealmproxyinterface = arcCode;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(ArcCode.class), set);
        osObjectBuilder.addString(arcCodeColumnInfo.typeColKey, com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmGet$type());
        osObjectBuilder.addString(arcCodeColumnInfo.codeColKey, com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmGet$code());
        com_ciot_realm_db_visitor_ArcCodeRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(arcCode, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, ArcCode arcCode, Map<RealmModel, Long> map) {
        if ((arcCode instanceof RealmObjectProxy) && !RealmObject.isFrozen(arcCode)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) arcCode;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(ArcCode.class);
        long nativePtr = table.getNativePtr();
        ArcCodeColumnInfo arcCodeColumnInfo = (ArcCodeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ArcCode.class);
        long createRow = OsObject.createRow(table);
        map.put(arcCode, Long.valueOf(createRow));
        com_ciot_realm_db_visitor_ArcCodeRealmProxyInterface com_ciot_realm_db_visitor_arccoderealmproxyinterface = arcCode;
        String realmGet$type = com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(nativePtr, arcCodeColumnInfo.typeColKey, createRow, realmGet$type, false);
        }
        String realmGet$code = com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmGet$code();
        if (realmGet$code != null) {
            Table.nativeSetString(nativePtr, arcCodeColumnInfo.codeColKey, createRow, realmGet$code, false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(ArcCode.class);
        long nativePtr = table.getNativePtr();
        ArcCodeColumnInfo arcCodeColumnInfo = (ArcCodeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ArcCode.class);
        while (it.hasNext()) {
            ArcCode arcCode = (ArcCode) it.next();
            if (!map2.containsKey(arcCode)) {
                if ((arcCode instanceof RealmObjectProxy) && !RealmObject.isFrozen(arcCode)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) arcCode;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(arcCode, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(arcCode, Long.valueOf(createRow));
                com_ciot_realm_db_visitor_ArcCodeRealmProxyInterface com_ciot_realm_db_visitor_arccoderealmproxyinterface = arcCode;
                String realmGet$type = com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmGet$type();
                if (realmGet$type != null) {
                    Table.nativeSetString(nativePtr, arcCodeColumnInfo.typeColKey, createRow, realmGet$type, false);
                }
                String realmGet$code = com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmGet$code();
                if (realmGet$code != null) {
                    Table.nativeSetString(nativePtr, arcCodeColumnInfo.codeColKey, createRow, realmGet$code, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, ArcCode arcCode, Map<RealmModel, Long> map) {
        if ((arcCode instanceof RealmObjectProxy) && !RealmObject.isFrozen(arcCode)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) arcCode;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(ArcCode.class);
        long nativePtr = table.getNativePtr();
        ArcCodeColumnInfo arcCodeColumnInfo = (ArcCodeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ArcCode.class);
        long createRow = OsObject.createRow(table);
        map.put(arcCode, Long.valueOf(createRow));
        com_ciot_realm_db_visitor_ArcCodeRealmProxyInterface com_ciot_realm_db_visitor_arccoderealmproxyinterface = arcCode;
        String realmGet$type = com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(nativePtr, arcCodeColumnInfo.typeColKey, createRow, realmGet$type, false);
        } else {
            Table.nativeSetNull(nativePtr, arcCodeColumnInfo.typeColKey, createRow, false);
        }
        String realmGet$code = com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmGet$code();
        if (realmGet$code != null) {
            Table.nativeSetString(nativePtr, arcCodeColumnInfo.codeColKey, createRow, realmGet$code, false);
        } else {
            Table.nativeSetNull(nativePtr, arcCodeColumnInfo.codeColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(ArcCode.class);
        long nativePtr = table.getNativePtr();
        ArcCodeColumnInfo arcCodeColumnInfo = (ArcCodeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ArcCode.class);
        while (it.hasNext()) {
            ArcCode arcCode = (ArcCode) it.next();
            if (!map2.containsKey(arcCode)) {
                if ((arcCode instanceof RealmObjectProxy) && !RealmObject.isFrozen(arcCode)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) arcCode;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(arcCode, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(arcCode, Long.valueOf(createRow));
                com_ciot_realm_db_visitor_ArcCodeRealmProxyInterface com_ciot_realm_db_visitor_arccoderealmproxyinterface = arcCode;
                String realmGet$type = com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmGet$type();
                if (realmGet$type != null) {
                    Table.nativeSetString(nativePtr, arcCodeColumnInfo.typeColKey, createRow, realmGet$type, false);
                } else {
                    Table.nativeSetNull(nativePtr, arcCodeColumnInfo.typeColKey, createRow, false);
                }
                String realmGet$code = com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmGet$code();
                if (realmGet$code != null) {
                    Table.nativeSetString(nativePtr, arcCodeColumnInfo.codeColKey, createRow, realmGet$code, false);
                } else {
                    Table.nativeSetNull(nativePtr, arcCodeColumnInfo.codeColKey, createRow, false);
                }
            }
        }
    }

    public static ArcCode createDetachedCopy(ArcCode arcCode, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        ArcCode arcCode2;
        if (i > i2 || arcCode == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(arcCode);
        if (cacheData == null) {
            arcCode2 = new ArcCode();
            map.put(arcCode, new RealmObjectProxy.CacheData(i, arcCode2));
        } else if (i >= cacheData.minDepth) {
            return (ArcCode) cacheData.object;
        } else {
            cacheData.minDepth = i;
            arcCode2 = (ArcCode) cacheData.object;
        }
        com_ciot_realm_db_visitor_ArcCodeRealmProxyInterface com_ciot_realm_db_visitor_arccoderealmproxyinterface = arcCode2;
        com_ciot_realm_db_visitor_ArcCodeRealmProxyInterface com_ciot_realm_db_visitor_arccoderealmproxyinterface2 = arcCode;
        com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmSet$type(com_ciot_realm_db_visitor_arccoderealmproxyinterface2.realmGet$type());
        com_ciot_realm_db_visitor_arccoderealmproxyinterface.realmSet$code(com_ciot_realm_db_visitor_arccoderealmproxyinterface2.realmGet$code());
        return arcCode2;
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
        com_ciot_realm_db_visitor_ArcCodeRealmProxy com_ciot_realm_db_visitor_arccoderealmproxy = (com_ciot_realm_db_visitor_ArcCodeRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_visitor_arccoderealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_visitor_arccoderealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_visitor_arccoderealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
