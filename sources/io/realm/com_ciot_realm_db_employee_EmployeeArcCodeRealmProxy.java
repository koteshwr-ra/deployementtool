package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.employee.EmployeeArcCode;
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
import xcrash.TombstoneParser;

public class com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy extends EmployeeArcCode implements RealmObjectProxy, com_ciot_realm_db_employee_EmployeeArcCodeRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private EmployeeArcCodeColumnInfo columnInfo;
    private ProxyState<EmployeeArcCode> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "EmployeeArcCode";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class EmployeeArcCodeColumnInfo extends ColumnInfo {
        long codeColKey;
        long typeColKey;

        EmployeeArcCodeColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.typeColKey = addColumnDetails("type", "type", objectSchemaInfo);
            this.codeColKey = addColumnDetails(TombstoneParser.keyCode, TombstoneParser.keyCode, objectSchemaInfo);
        }

        EmployeeArcCodeColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new EmployeeArcCodeColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            EmployeeArcCodeColumnInfo employeeArcCodeColumnInfo = (EmployeeArcCodeColumnInfo) columnInfo;
            EmployeeArcCodeColumnInfo employeeArcCodeColumnInfo2 = (EmployeeArcCodeColumnInfo) columnInfo2;
            employeeArcCodeColumnInfo2.typeColKey = employeeArcCodeColumnInfo.typeColKey;
            employeeArcCodeColumnInfo2.codeColKey = employeeArcCodeColumnInfo.codeColKey;
        }
    }

    com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (EmployeeArcCodeColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<EmployeeArcCode> proxyState2 = new ProxyState<>(this);
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

    public static EmployeeArcCodeColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new EmployeeArcCodeColumnInfo(osSchemaInfo);
    }

    public static EmployeeArcCode createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        EmployeeArcCode employeeArcCode = (EmployeeArcCode) realm.createObjectInternal(EmployeeArcCode.class, true, Collections.emptyList());
        com_ciot_realm_db_employee_EmployeeArcCodeRealmProxyInterface com_ciot_realm_db_employee_employeearccoderealmproxyinterface = employeeArcCode;
        if (jSONObject.has("type")) {
            if (jSONObject.isNull("type")) {
                com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmSet$type((String) null);
            } else {
                com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmSet$type(jSONObject.getString("type"));
            }
        }
        if (jSONObject.has(TombstoneParser.keyCode)) {
            if (jSONObject.isNull(TombstoneParser.keyCode)) {
                com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmSet$code((String) null);
            } else {
                com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmSet$code(jSONObject.getString(TombstoneParser.keyCode));
            }
        }
        return employeeArcCode;
    }

    public static EmployeeArcCode createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        EmployeeArcCode employeeArcCode = new EmployeeArcCode();
        com_ciot_realm_db_employee_EmployeeArcCodeRealmProxyInterface com_ciot_realm_db_employee_employeearccoderealmproxyinterface = employeeArcCode;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("type")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmSet$type(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmSet$type((String) null);
                }
            } else if (!nextName.equals(TombstoneParser.keyCode)) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmSet$code(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmSet$code((String) null);
            }
        }
        jsonReader.endObject();
        return (EmployeeArcCode) realm.copyToRealm(employeeArcCode, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeArcCode.class), false, Collections.emptyList());
        com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy com_ciot_realm_db_employee_employeearccoderealmproxy = new com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_employee_employeearccoderealmproxy;
    }

    public static EmployeeArcCode copyOrUpdate(Realm realm, EmployeeArcCodeColumnInfo employeeArcCodeColumnInfo, EmployeeArcCode employeeArcCode, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((employeeArcCode instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeArcCode)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeArcCode;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return employeeArcCode;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(employeeArcCode);
        if (realmObjectProxy2 != null) {
            return (EmployeeArcCode) realmObjectProxy2;
        }
        return copy(realm, employeeArcCodeColumnInfo, employeeArcCode, z, map, set);
    }

    public static EmployeeArcCode copy(Realm realm, EmployeeArcCodeColumnInfo employeeArcCodeColumnInfo, EmployeeArcCode employeeArcCode, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(employeeArcCode);
        if (realmObjectProxy != null) {
            return (EmployeeArcCode) realmObjectProxy;
        }
        com_ciot_realm_db_employee_EmployeeArcCodeRealmProxyInterface com_ciot_realm_db_employee_employeearccoderealmproxyinterface = employeeArcCode;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(EmployeeArcCode.class), set);
        osObjectBuilder.addString(employeeArcCodeColumnInfo.typeColKey, com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmGet$type());
        osObjectBuilder.addString(employeeArcCodeColumnInfo.codeColKey, com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmGet$code());
        com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(employeeArcCode, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, EmployeeArcCode employeeArcCode, Map<RealmModel, Long> map) {
        if ((employeeArcCode instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeArcCode)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeArcCode;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(EmployeeArcCode.class);
        long nativePtr = table.getNativePtr();
        EmployeeArcCodeColumnInfo employeeArcCodeColumnInfo = (EmployeeArcCodeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeArcCode.class);
        long createRow = OsObject.createRow(table);
        map.put(employeeArcCode, Long.valueOf(createRow));
        com_ciot_realm_db_employee_EmployeeArcCodeRealmProxyInterface com_ciot_realm_db_employee_employeearccoderealmproxyinterface = employeeArcCode;
        String realmGet$type = com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(nativePtr, employeeArcCodeColumnInfo.typeColKey, createRow, realmGet$type, false);
        }
        String realmGet$code = com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmGet$code();
        if (realmGet$code != null) {
            Table.nativeSetString(nativePtr, employeeArcCodeColumnInfo.codeColKey, createRow, realmGet$code, false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(EmployeeArcCode.class);
        long nativePtr = table.getNativePtr();
        EmployeeArcCodeColumnInfo employeeArcCodeColumnInfo = (EmployeeArcCodeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeArcCode.class);
        while (it.hasNext()) {
            EmployeeArcCode employeeArcCode = (EmployeeArcCode) it.next();
            if (!map2.containsKey(employeeArcCode)) {
                if ((employeeArcCode instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeArcCode)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeArcCode;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(employeeArcCode, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(employeeArcCode, Long.valueOf(createRow));
                com_ciot_realm_db_employee_EmployeeArcCodeRealmProxyInterface com_ciot_realm_db_employee_employeearccoderealmproxyinterface = employeeArcCode;
                String realmGet$type = com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmGet$type();
                if (realmGet$type != null) {
                    Table.nativeSetString(nativePtr, employeeArcCodeColumnInfo.typeColKey, createRow, realmGet$type, false);
                }
                String realmGet$code = com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmGet$code();
                if (realmGet$code != null) {
                    Table.nativeSetString(nativePtr, employeeArcCodeColumnInfo.codeColKey, createRow, realmGet$code, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, EmployeeArcCode employeeArcCode, Map<RealmModel, Long> map) {
        if ((employeeArcCode instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeArcCode)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeArcCode;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(EmployeeArcCode.class);
        long nativePtr = table.getNativePtr();
        EmployeeArcCodeColumnInfo employeeArcCodeColumnInfo = (EmployeeArcCodeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeArcCode.class);
        long createRow = OsObject.createRow(table);
        map.put(employeeArcCode, Long.valueOf(createRow));
        com_ciot_realm_db_employee_EmployeeArcCodeRealmProxyInterface com_ciot_realm_db_employee_employeearccoderealmproxyinterface = employeeArcCode;
        String realmGet$type = com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(nativePtr, employeeArcCodeColumnInfo.typeColKey, createRow, realmGet$type, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeArcCodeColumnInfo.typeColKey, createRow, false);
        }
        String realmGet$code = com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmGet$code();
        if (realmGet$code != null) {
            Table.nativeSetString(nativePtr, employeeArcCodeColumnInfo.codeColKey, createRow, realmGet$code, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeArcCodeColumnInfo.codeColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(EmployeeArcCode.class);
        long nativePtr = table.getNativePtr();
        EmployeeArcCodeColumnInfo employeeArcCodeColumnInfo = (EmployeeArcCodeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeArcCode.class);
        while (it.hasNext()) {
            EmployeeArcCode employeeArcCode = (EmployeeArcCode) it.next();
            if (!map2.containsKey(employeeArcCode)) {
                if ((employeeArcCode instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeArcCode)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeArcCode;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(employeeArcCode, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(employeeArcCode, Long.valueOf(createRow));
                com_ciot_realm_db_employee_EmployeeArcCodeRealmProxyInterface com_ciot_realm_db_employee_employeearccoderealmproxyinterface = employeeArcCode;
                String realmGet$type = com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmGet$type();
                if (realmGet$type != null) {
                    Table.nativeSetString(nativePtr, employeeArcCodeColumnInfo.typeColKey, createRow, realmGet$type, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeArcCodeColumnInfo.typeColKey, createRow, false);
                }
                String realmGet$code = com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmGet$code();
                if (realmGet$code != null) {
                    Table.nativeSetString(nativePtr, employeeArcCodeColumnInfo.codeColKey, createRow, realmGet$code, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeArcCodeColumnInfo.codeColKey, createRow, false);
                }
            }
        }
    }

    public static EmployeeArcCode createDetachedCopy(EmployeeArcCode employeeArcCode, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        EmployeeArcCode employeeArcCode2;
        if (i > i2 || employeeArcCode == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(employeeArcCode);
        if (cacheData == null) {
            employeeArcCode2 = new EmployeeArcCode();
            map.put(employeeArcCode, new RealmObjectProxy.CacheData(i, employeeArcCode2));
        } else if (i >= cacheData.minDepth) {
            return (EmployeeArcCode) cacheData.object;
        } else {
            cacheData.minDepth = i;
            employeeArcCode2 = (EmployeeArcCode) cacheData.object;
        }
        com_ciot_realm_db_employee_EmployeeArcCodeRealmProxyInterface com_ciot_realm_db_employee_employeearccoderealmproxyinterface = employeeArcCode2;
        com_ciot_realm_db_employee_EmployeeArcCodeRealmProxyInterface com_ciot_realm_db_employee_employeearccoderealmproxyinterface2 = employeeArcCode;
        com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmSet$type(com_ciot_realm_db_employee_employeearccoderealmproxyinterface2.realmGet$type());
        com_ciot_realm_db_employee_employeearccoderealmproxyinterface.realmSet$code(com_ciot_realm_db_employee_employeearccoderealmproxyinterface2.realmGet$code());
        return employeeArcCode2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("EmployeeArcCode = proxy[");
        sb.append("{type:");
        String realmGet$type = realmGet$type();
        String str = Configurator.NULL;
        sb.append(realmGet$type != null ? realmGet$type() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{code:");
        if (realmGet$code() != null) {
            str = realmGet$code();
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
        com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy com_ciot_realm_db_employee_employeearccoderealmproxy = (com_ciot_realm_db_employee_EmployeeArcCodeRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_employee_employeearccoderealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_employee_employeearccoderealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_employee_employeearccoderealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
