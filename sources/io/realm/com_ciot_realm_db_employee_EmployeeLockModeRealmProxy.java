package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.employee.EmployeeLockMode;
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

public class com_ciot_realm_db_employee_EmployeeLockModeRealmProxy extends EmployeeLockMode implements RealmObjectProxy, com_ciot_realm_db_employee_EmployeeLockModeRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private EmployeeLockModeColumnInfo columnInfo;
    private ProxyState<EmployeeLockMode> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "EmployeeLockMode";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class EmployeeLockModeColumnInfo extends ColumnInfo {
        long cardColKey;
        long faceColKey;
        long iccardColKey;
        long idcardcodeColKey;

        EmployeeLockModeColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(4);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.faceColKey = addColumnDetails("face", "face", objectSchemaInfo);
            this.iccardColKey = addColumnDetails("iccard", "iccard", objectSchemaInfo);
            this.idcardcodeColKey = addColumnDetails("idcardcode", "idcardcode", objectSchemaInfo);
            this.cardColKey = addColumnDetails("card", "card", objectSchemaInfo);
        }

        EmployeeLockModeColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new EmployeeLockModeColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            EmployeeLockModeColumnInfo employeeLockModeColumnInfo = (EmployeeLockModeColumnInfo) columnInfo;
            EmployeeLockModeColumnInfo employeeLockModeColumnInfo2 = (EmployeeLockModeColumnInfo) columnInfo2;
            employeeLockModeColumnInfo2.faceColKey = employeeLockModeColumnInfo.faceColKey;
            employeeLockModeColumnInfo2.iccardColKey = employeeLockModeColumnInfo.iccardColKey;
            employeeLockModeColumnInfo2.idcardcodeColKey = employeeLockModeColumnInfo.idcardcodeColKey;
            employeeLockModeColumnInfo2.cardColKey = employeeLockModeColumnInfo.cardColKey;
        }
    }

    com_ciot_realm_db_employee_EmployeeLockModeRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (EmployeeLockModeColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<EmployeeLockMode> proxyState2 = new ProxyState<>(this);
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

    public static EmployeeLockModeColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new EmployeeLockModeColumnInfo(osSchemaInfo);
    }

    public static EmployeeLockMode createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        EmployeeLockMode employeeLockMode = (EmployeeLockMode) realm.createObjectInternal(EmployeeLockMode.class, true, Collections.emptyList());
        com_ciot_realm_db_employee_EmployeeLockModeRealmProxyInterface com_ciot_realm_db_employee_employeelockmoderealmproxyinterface = employeeLockMode;
        if (jSONObject.has("face")) {
            if (jSONObject.isNull("face")) {
                com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$face((String) null);
            } else {
                com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$face(jSONObject.getString("face"));
            }
        }
        if (jSONObject.has("iccard")) {
            if (jSONObject.isNull("iccard")) {
                com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$iccard((String) null);
            } else {
                com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$iccard(jSONObject.getString("iccard"));
            }
        }
        if (jSONObject.has("idcardcode")) {
            if (jSONObject.isNull("idcardcode")) {
                com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$idcardcode((String) null);
            } else {
                com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$idcardcode(jSONObject.getString("idcardcode"));
            }
        }
        if (jSONObject.has("card")) {
            if (jSONObject.isNull("card")) {
                com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$card((String) null);
            } else {
                com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$card(jSONObject.getString("card"));
            }
        }
        return employeeLockMode;
    }

    public static EmployeeLockMode createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        EmployeeLockMode employeeLockMode = new EmployeeLockMode();
        com_ciot_realm_db_employee_EmployeeLockModeRealmProxyInterface com_ciot_realm_db_employee_employeelockmoderealmproxyinterface = employeeLockMode;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("face")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$face(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$face((String) null);
                }
            } else if (nextName.equals("iccard")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$iccard(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$iccard((String) null);
                }
            } else if (nextName.equals("idcardcode")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$idcardcode(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$idcardcode((String) null);
                }
            } else if (!nextName.equals("card")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$card(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$card((String) null);
            }
        }
        jsonReader.endObject();
        return (EmployeeLockMode) realm.copyToRealm(employeeLockMode, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_employee_EmployeeLockModeRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeLockMode.class), false, Collections.emptyList());
        com_ciot_realm_db_employee_EmployeeLockModeRealmProxy com_ciot_realm_db_employee_employeelockmoderealmproxy = new com_ciot_realm_db_employee_EmployeeLockModeRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_employee_employeelockmoderealmproxy;
    }

    public static EmployeeLockMode copyOrUpdate(Realm realm, EmployeeLockModeColumnInfo employeeLockModeColumnInfo, EmployeeLockMode employeeLockMode, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((employeeLockMode instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeLockMode)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeLockMode;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return employeeLockMode;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(employeeLockMode);
        if (realmObjectProxy2 != null) {
            return (EmployeeLockMode) realmObjectProxy2;
        }
        return copy(realm, employeeLockModeColumnInfo, employeeLockMode, z, map, set);
    }

    public static EmployeeLockMode copy(Realm realm, EmployeeLockModeColumnInfo employeeLockModeColumnInfo, EmployeeLockMode employeeLockMode, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(employeeLockMode);
        if (realmObjectProxy != null) {
            return (EmployeeLockMode) realmObjectProxy;
        }
        com_ciot_realm_db_employee_EmployeeLockModeRealmProxyInterface com_ciot_realm_db_employee_employeelockmoderealmproxyinterface = employeeLockMode;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(EmployeeLockMode.class), set);
        osObjectBuilder.addString(employeeLockModeColumnInfo.faceColKey, com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$face());
        osObjectBuilder.addString(employeeLockModeColumnInfo.iccardColKey, com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$iccard());
        osObjectBuilder.addString(employeeLockModeColumnInfo.idcardcodeColKey, com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$idcardcode());
        osObjectBuilder.addString(employeeLockModeColumnInfo.cardColKey, com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$card());
        com_ciot_realm_db_employee_EmployeeLockModeRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(employeeLockMode, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, EmployeeLockMode employeeLockMode, Map<RealmModel, Long> map) {
        if ((employeeLockMode instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeLockMode)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeLockMode;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(EmployeeLockMode.class);
        long nativePtr = table.getNativePtr();
        EmployeeLockModeColumnInfo employeeLockModeColumnInfo = (EmployeeLockModeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeLockMode.class);
        long createRow = OsObject.createRow(table);
        map.put(employeeLockMode, Long.valueOf(createRow));
        com_ciot_realm_db_employee_EmployeeLockModeRealmProxyInterface com_ciot_realm_db_employee_employeelockmoderealmproxyinterface = employeeLockMode;
        String realmGet$face = com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$face();
        if (realmGet$face != null) {
            Table.nativeSetString(nativePtr, employeeLockModeColumnInfo.faceColKey, createRow, realmGet$face, false);
        }
        String realmGet$iccard = com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$iccard();
        if (realmGet$iccard != null) {
            Table.nativeSetString(nativePtr, employeeLockModeColumnInfo.iccardColKey, createRow, realmGet$iccard, false);
        }
        String realmGet$idcardcode = com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$idcardcode();
        if (realmGet$idcardcode != null) {
            Table.nativeSetString(nativePtr, employeeLockModeColumnInfo.idcardcodeColKey, createRow, realmGet$idcardcode, false);
        }
        String realmGet$card = com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$card();
        if (realmGet$card != null) {
            Table.nativeSetString(nativePtr, employeeLockModeColumnInfo.cardColKey, createRow, realmGet$card, false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(EmployeeLockMode.class);
        long nativePtr = table.getNativePtr();
        EmployeeLockModeColumnInfo employeeLockModeColumnInfo = (EmployeeLockModeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeLockMode.class);
        while (it.hasNext()) {
            EmployeeLockMode employeeLockMode = (EmployeeLockMode) it.next();
            if (!map2.containsKey(employeeLockMode)) {
                if ((employeeLockMode instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeLockMode)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeLockMode;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(employeeLockMode, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(employeeLockMode, Long.valueOf(createRow));
                com_ciot_realm_db_employee_EmployeeLockModeRealmProxyInterface com_ciot_realm_db_employee_employeelockmoderealmproxyinterface = employeeLockMode;
                String realmGet$face = com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$face();
                if (realmGet$face != null) {
                    Table.nativeSetString(nativePtr, employeeLockModeColumnInfo.faceColKey, createRow, realmGet$face, false);
                }
                String realmGet$iccard = com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$iccard();
                if (realmGet$iccard != null) {
                    Table.nativeSetString(nativePtr, employeeLockModeColumnInfo.iccardColKey, createRow, realmGet$iccard, false);
                }
                String realmGet$idcardcode = com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$idcardcode();
                if (realmGet$idcardcode != null) {
                    Table.nativeSetString(nativePtr, employeeLockModeColumnInfo.idcardcodeColKey, createRow, realmGet$idcardcode, false);
                }
                String realmGet$card = com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$card();
                if (realmGet$card != null) {
                    Table.nativeSetString(nativePtr, employeeLockModeColumnInfo.cardColKey, createRow, realmGet$card, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, EmployeeLockMode employeeLockMode, Map<RealmModel, Long> map) {
        if ((employeeLockMode instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeLockMode)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeLockMode;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(EmployeeLockMode.class);
        long nativePtr = table.getNativePtr();
        EmployeeLockModeColumnInfo employeeLockModeColumnInfo = (EmployeeLockModeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeLockMode.class);
        long createRow = OsObject.createRow(table);
        map.put(employeeLockMode, Long.valueOf(createRow));
        com_ciot_realm_db_employee_EmployeeLockModeRealmProxyInterface com_ciot_realm_db_employee_employeelockmoderealmproxyinterface = employeeLockMode;
        String realmGet$face = com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$face();
        if (realmGet$face != null) {
            Table.nativeSetString(nativePtr, employeeLockModeColumnInfo.faceColKey, createRow, realmGet$face, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeLockModeColumnInfo.faceColKey, createRow, false);
        }
        String realmGet$iccard = com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$iccard();
        if (realmGet$iccard != null) {
            Table.nativeSetString(nativePtr, employeeLockModeColumnInfo.iccardColKey, createRow, realmGet$iccard, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeLockModeColumnInfo.iccardColKey, createRow, false);
        }
        String realmGet$idcardcode = com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$idcardcode();
        if (realmGet$idcardcode != null) {
            Table.nativeSetString(nativePtr, employeeLockModeColumnInfo.idcardcodeColKey, createRow, realmGet$idcardcode, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeLockModeColumnInfo.idcardcodeColKey, createRow, false);
        }
        String realmGet$card = com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$card();
        if (realmGet$card != null) {
            Table.nativeSetString(nativePtr, employeeLockModeColumnInfo.cardColKey, createRow, realmGet$card, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeLockModeColumnInfo.cardColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(EmployeeLockMode.class);
        long nativePtr = table.getNativePtr();
        EmployeeLockModeColumnInfo employeeLockModeColumnInfo = (EmployeeLockModeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeLockMode.class);
        while (it.hasNext()) {
            EmployeeLockMode employeeLockMode = (EmployeeLockMode) it.next();
            if (!map2.containsKey(employeeLockMode)) {
                if ((employeeLockMode instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeLockMode)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeLockMode;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(employeeLockMode, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(employeeLockMode, Long.valueOf(createRow));
                com_ciot_realm_db_employee_EmployeeLockModeRealmProxyInterface com_ciot_realm_db_employee_employeelockmoderealmproxyinterface = employeeLockMode;
                String realmGet$face = com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$face();
                if (realmGet$face != null) {
                    Table.nativeSetString(nativePtr, employeeLockModeColumnInfo.faceColKey, createRow, realmGet$face, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeLockModeColumnInfo.faceColKey, createRow, false);
                }
                String realmGet$iccard = com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$iccard();
                if (realmGet$iccard != null) {
                    Table.nativeSetString(nativePtr, employeeLockModeColumnInfo.iccardColKey, createRow, realmGet$iccard, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeLockModeColumnInfo.iccardColKey, createRow, false);
                }
                String realmGet$idcardcode = com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$idcardcode();
                if (realmGet$idcardcode != null) {
                    Table.nativeSetString(nativePtr, employeeLockModeColumnInfo.idcardcodeColKey, createRow, realmGet$idcardcode, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeLockModeColumnInfo.idcardcodeColKey, createRow, false);
                }
                String realmGet$card = com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmGet$card();
                if (realmGet$card != null) {
                    Table.nativeSetString(nativePtr, employeeLockModeColumnInfo.cardColKey, createRow, realmGet$card, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeLockModeColumnInfo.cardColKey, createRow, false);
                }
            }
        }
    }

    public static EmployeeLockMode createDetachedCopy(EmployeeLockMode employeeLockMode, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        EmployeeLockMode employeeLockMode2;
        if (i > i2 || employeeLockMode == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(employeeLockMode);
        if (cacheData == null) {
            employeeLockMode2 = new EmployeeLockMode();
            map.put(employeeLockMode, new RealmObjectProxy.CacheData(i, employeeLockMode2));
        } else if (i >= cacheData.minDepth) {
            return (EmployeeLockMode) cacheData.object;
        } else {
            cacheData.minDepth = i;
            employeeLockMode2 = (EmployeeLockMode) cacheData.object;
        }
        com_ciot_realm_db_employee_EmployeeLockModeRealmProxyInterface com_ciot_realm_db_employee_employeelockmoderealmproxyinterface = employeeLockMode2;
        com_ciot_realm_db_employee_EmployeeLockModeRealmProxyInterface com_ciot_realm_db_employee_employeelockmoderealmproxyinterface2 = employeeLockMode;
        com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$face(com_ciot_realm_db_employee_employeelockmoderealmproxyinterface2.realmGet$face());
        com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$iccard(com_ciot_realm_db_employee_employeelockmoderealmproxyinterface2.realmGet$iccard());
        com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$idcardcode(com_ciot_realm_db_employee_employeelockmoderealmproxyinterface2.realmGet$idcardcode());
        com_ciot_realm_db_employee_employeelockmoderealmproxyinterface.realmSet$card(com_ciot_realm_db_employee_employeelockmoderealmproxyinterface2.realmGet$card());
        return employeeLockMode2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("EmployeeLockMode = proxy[");
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
        com_ciot_realm_db_employee_EmployeeLockModeRealmProxy com_ciot_realm_db_employee_employeelockmoderealmproxy = (com_ciot_realm_db_employee_EmployeeLockModeRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_employee_employeelockmoderealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_employee_employeelockmoderealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_employee_employeelockmoderealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
