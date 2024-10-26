package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.patrol.Operation;
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

public class com_ciot_realm_db_patrol_OperationRealmProxy extends Operation implements RealmObjectProxy, com_ciot_realm_db_patrol_OperationRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private OperationColumnInfo columnInfo;
    private ProxyState<Operation> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Operation";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class OperationColumnInfo extends ColumnInfo {
        long operP1ColKey;
        long operRepeatColKey;
        long operTextColKey;
        long operTimeColKey;
        long operTypeColKey;
        long sprayModelPosColKey;

        OperationColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(6);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.operTypeColKey = addColumnDetails("operType", "operType", objectSchemaInfo);
            this.operTextColKey = addColumnDetails("operText", "operText", objectSchemaInfo);
            this.operP1ColKey = addColumnDetails("operP1", "operP1", objectSchemaInfo);
            this.operTimeColKey = addColumnDetails("operTime", "operTime", objectSchemaInfo);
            this.operRepeatColKey = addColumnDetails("operRepeat", "operRepeat", objectSchemaInfo);
            this.sprayModelPosColKey = addColumnDetails("sprayModelPos", "sprayModelPos", objectSchemaInfo);
        }

        OperationColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new OperationColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            OperationColumnInfo operationColumnInfo = (OperationColumnInfo) columnInfo;
            OperationColumnInfo operationColumnInfo2 = (OperationColumnInfo) columnInfo2;
            operationColumnInfo2.operTypeColKey = operationColumnInfo.operTypeColKey;
            operationColumnInfo2.operTextColKey = operationColumnInfo.operTextColKey;
            operationColumnInfo2.operP1ColKey = operationColumnInfo.operP1ColKey;
            operationColumnInfo2.operTimeColKey = operationColumnInfo.operTimeColKey;
            operationColumnInfo2.operRepeatColKey = operationColumnInfo.operRepeatColKey;
            operationColumnInfo2.sprayModelPosColKey = operationColumnInfo.sprayModelPosColKey;
        }
    }

    com_ciot_realm_db_patrol_OperationRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (OperationColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<Operation> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public int realmGet$operType() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.operTypeColKey);
    }

    public void realmSet$operType(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.operTypeColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.operTypeColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public String realmGet$operText() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.operTextColKey);
    }

    public void realmSet$operText(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.operTextColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.operTextColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.operTextColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.operTextColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public int realmGet$operP1() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.operP1ColKey);
    }

    public void realmSet$operP1(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.operP1ColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.operP1ColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public int realmGet$operTime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.operTimeColKey);
    }

    public void realmSet$operTime(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.operTimeColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.operTimeColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public int realmGet$operRepeat() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.operRepeatColKey);
    }

    public void realmSet$operRepeat(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.operRepeatColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.operRepeatColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public int realmGet$sprayModelPos() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.sprayModelPosColKey);
    }

    public void realmSet$sprayModelPos(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.sprayModelPosColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.sprayModelPosColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 6, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("operType", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("operText", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("operP1", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("operTime", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("operRepeat", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("sprayModelPos", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static OperationColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new OperationColumnInfo(osSchemaInfo);
    }

    public static Operation createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        Operation operation = (Operation) realm.createObjectInternal(Operation.class, true, Collections.emptyList());
        com_ciot_realm_db_patrol_OperationRealmProxyInterface com_ciot_realm_db_patrol_operationrealmproxyinterface = operation;
        if (jSONObject.has("operType")) {
            if (!jSONObject.isNull("operType")) {
                com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$operType(jSONObject.getInt("operType"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'operType' to null.");
            }
        }
        if (jSONObject.has("operText")) {
            if (jSONObject.isNull("operText")) {
                com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$operText((String) null);
            } else {
                com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$operText(jSONObject.getString("operText"));
            }
        }
        if (jSONObject.has("operP1")) {
            if (!jSONObject.isNull("operP1")) {
                com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$operP1(jSONObject.getInt("operP1"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'operP1' to null.");
            }
        }
        if (jSONObject.has("operTime")) {
            if (!jSONObject.isNull("operTime")) {
                com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$operTime(jSONObject.getInt("operTime"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'operTime' to null.");
            }
        }
        if (jSONObject.has("operRepeat")) {
            if (!jSONObject.isNull("operRepeat")) {
                com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$operRepeat(jSONObject.getInt("operRepeat"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'operRepeat' to null.");
            }
        }
        if (jSONObject.has("sprayModelPos")) {
            if (!jSONObject.isNull("sprayModelPos")) {
                com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$sprayModelPos(jSONObject.getInt("sprayModelPos"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'sprayModelPos' to null.");
            }
        }
        return operation;
    }

    public static Operation createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        Operation operation = new Operation();
        com_ciot_realm_db_patrol_OperationRealmProxyInterface com_ciot_realm_db_patrol_operationrealmproxyinterface = operation;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("operType")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$operType(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'operType' to null.");
                }
            } else if (nextName.equals("operText")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$operText(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$operText((String) null);
                }
            } else if (nextName.equals("operP1")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$operP1(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'operP1' to null.");
                }
            } else if (nextName.equals("operTime")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$operTime(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'operTime' to null.");
                }
            } else if (nextName.equals("operRepeat")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$operRepeat(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'operRepeat' to null.");
                }
            } else if (!nextName.equals("sprayModelPos")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$sprayModelPos(jsonReader.nextInt());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'sprayModelPos' to null.");
            }
        }
        jsonReader.endObject();
        return (Operation) realm.copyToRealm(operation, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_patrol_OperationRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) Operation.class), false, Collections.emptyList());
        com_ciot_realm_db_patrol_OperationRealmProxy com_ciot_realm_db_patrol_operationrealmproxy = new com_ciot_realm_db_patrol_OperationRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_patrol_operationrealmproxy;
    }

    public static Operation copyOrUpdate(Realm realm, OperationColumnInfo operationColumnInfo, Operation operation, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((operation instanceof RealmObjectProxy) && !RealmObject.isFrozen(operation)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) operation;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return operation;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(operation);
        if (realmObjectProxy2 != null) {
            return (Operation) realmObjectProxy2;
        }
        return copy(realm, operationColumnInfo, operation, z, map, set);
    }

    public static Operation copy(Realm realm, OperationColumnInfo operationColumnInfo, Operation operation, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(operation);
        if (realmObjectProxy != null) {
            return (Operation) realmObjectProxy;
        }
        com_ciot_realm_db_patrol_OperationRealmProxyInterface com_ciot_realm_db_patrol_operationrealmproxyinterface = operation;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Operation.class), set);
        osObjectBuilder.addInteger(operationColumnInfo.operTypeColKey, Integer.valueOf(com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operType()));
        osObjectBuilder.addString(operationColumnInfo.operTextColKey, com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operText());
        osObjectBuilder.addInteger(operationColumnInfo.operP1ColKey, Integer.valueOf(com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operP1()));
        osObjectBuilder.addInteger(operationColumnInfo.operTimeColKey, Integer.valueOf(com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operTime()));
        osObjectBuilder.addInteger(operationColumnInfo.operRepeatColKey, Integer.valueOf(com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operRepeat()));
        osObjectBuilder.addInteger(operationColumnInfo.sprayModelPosColKey, Integer.valueOf(com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$sprayModelPos()));
        com_ciot_realm_db_patrol_OperationRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(operation, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, Operation operation, Map<RealmModel, Long> map) {
        Operation operation2 = operation;
        if ((operation2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(operation)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) operation2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Operation.class);
        long nativePtr = table.getNativePtr();
        OperationColumnInfo operationColumnInfo = (OperationColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Operation.class);
        long createRow = OsObject.createRow(table);
        map.put(operation2, Long.valueOf(createRow));
        com_ciot_realm_db_patrol_OperationRealmProxyInterface com_ciot_realm_db_patrol_operationrealmproxyinterface = operation2;
        Table.nativeSetLong(nativePtr, operationColumnInfo.operTypeColKey, createRow, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operType(), false);
        String realmGet$operText = com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operText();
        if (realmGet$operText != null) {
            Table.nativeSetString(nativePtr, operationColumnInfo.operTextColKey, createRow, realmGet$operText, false);
        }
        long j = nativePtr;
        long j2 = createRow;
        Table.nativeSetLong(j, operationColumnInfo.operP1ColKey, j2, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operP1(), false);
        Table.nativeSetLong(j, operationColumnInfo.operTimeColKey, j2, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operTime(), false);
        Table.nativeSetLong(j, operationColumnInfo.operRepeatColKey, j2, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operRepeat(), false);
        Table.nativeSetLong(j, operationColumnInfo.sprayModelPosColKey, j2, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$sprayModelPos(), false);
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Operation.class);
        long nativePtr = table.getNativePtr();
        OperationColumnInfo operationColumnInfo = (OperationColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Operation.class);
        while (it.hasNext()) {
            Operation operation = (Operation) it.next();
            if (!map2.containsKey(operation)) {
                if ((operation instanceof RealmObjectProxy) && !RealmObject.isFrozen(operation)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) operation;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(operation, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(operation, Long.valueOf(createRow));
                com_ciot_realm_db_patrol_OperationRealmProxyInterface com_ciot_realm_db_patrol_operationrealmproxyinterface = operation;
                Table.nativeSetLong(nativePtr, operationColumnInfo.operTypeColKey, createRow, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operType(), false);
                String realmGet$operText = com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operText();
                if (realmGet$operText != null) {
                    Table.nativeSetString(nativePtr, operationColumnInfo.operTextColKey, createRow, realmGet$operText, false);
                }
                long j = createRow;
                Table.nativeSetLong(nativePtr, operationColumnInfo.operP1ColKey, j, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operP1(), false);
                Table.nativeSetLong(nativePtr, operationColumnInfo.operTimeColKey, j, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operTime(), false);
                Table.nativeSetLong(nativePtr, operationColumnInfo.operRepeatColKey, j, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operRepeat(), false);
                Table.nativeSetLong(nativePtr, operationColumnInfo.sprayModelPosColKey, j, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$sprayModelPos(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Operation operation, Map<RealmModel, Long> map) {
        Operation operation2 = operation;
        if ((operation2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(operation)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) operation2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Operation.class);
        long nativePtr = table.getNativePtr();
        OperationColumnInfo operationColumnInfo = (OperationColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Operation.class);
        long createRow = OsObject.createRow(table);
        map.put(operation2, Long.valueOf(createRow));
        com_ciot_realm_db_patrol_OperationRealmProxyInterface com_ciot_realm_db_patrol_operationrealmproxyinterface = operation2;
        Table.nativeSetLong(nativePtr, operationColumnInfo.operTypeColKey, createRow, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operType(), false);
        String realmGet$operText = com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operText();
        if (realmGet$operText != null) {
            Table.nativeSetString(nativePtr, operationColumnInfo.operTextColKey, createRow, realmGet$operText, false);
        } else {
            Table.nativeSetNull(nativePtr, operationColumnInfo.operTextColKey, createRow, false);
        }
        long j = nativePtr;
        long j2 = createRow;
        Table.nativeSetLong(j, operationColumnInfo.operP1ColKey, j2, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operP1(), false);
        Table.nativeSetLong(j, operationColumnInfo.operTimeColKey, j2, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operTime(), false);
        Table.nativeSetLong(j, operationColumnInfo.operRepeatColKey, j2, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operRepeat(), false);
        Table.nativeSetLong(j, operationColumnInfo.sprayModelPosColKey, j2, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$sprayModelPos(), false);
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Operation.class);
        long nativePtr = table.getNativePtr();
        OperationColumnInfo operationColumnInfo = (OperationColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Operation.class);
        while (it.hasNext()) {
            Operation operation = (Operation) it.next();
            if (!map2.containsKey(operation)) {
                if ((operation instanceof RealmObjectProxy) && !RealmObject.isFrozen(operation)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) operation;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(operation, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(operation, Long.valueOf(createRow));
                com_ciot_realm_db_patrol_OperationRealmProxyInterface com_ciot_realm_db_patrol_operationrealmproxyinterface = operation;
                Table.nativeSetLong(nativePtr, operationColumnInfo.operTypeColKey, createRow, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operType(), false);
                String realmGet$operText = com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operText();
                if (realmGet$operText != null) {
                    Table.nativeSetString(nativePtr, operationColumnInfo.operTextColKey, createRow, realmGet$operText, false);
                } else {
                    Table.nativeSetNull(nativePtr, operationColumnInfo.operTextColKey, createRow, false);
                }
                long j = createRow;
                Table.nativeSetLong(nativePtr, operationColumnInfo.operP1ColKey, j, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operP1(), false);
                Table.nativeSetLong(nativePtr, operationColumnInfo.operTimeColKey, j, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operTime(), false);
                Table.nativeSetLong(nativePtr, operationColumnInfo.operRepeatColKey, j, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$operRepeat(), false);
                Table.nativeSetLong(nativePtr, operationColumnInfo.sprayModelPosColKey, j, (long) com_ciot_realm_db_patrol_operationrealmproxyinterface.realmGet$sprayModelPos(), false);
            }
        }
    }

    public static Operation createDetachedCopy(Operation operation, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        Operation operation2;
        if (i > i2 || operation == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(operation);
        if (cacheData == null) {
            operation2 = new Operation();
            map.put(operation, new RealmObjectProxy.CacheData(i, operation2));
        } else if (i >= cacheData.minDepth) {
            return (Operation) cacheData.object;
        } else {
            cacheData.minDepth = i;
            operation2 = (Operation) cacheData.object;
        }
        com_ciot_realm_db_patrol_OperationRealmProxyInterface com_ciot_realm_db_patrol_operationrealmproxyinterface = operation2;
        com_ciot_realm_db_patrol_OperationRealmProxyInterface com_ciot_realm_db_patrol_operationrealmproxyinterface2 = operation;
        com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$operType(com_ciot_realm_db_patrol_operationrealmproxyinterface2.realmGet$operType());
        com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$operText(com_ciot_realm_db_patrol_operationrealmproxyinterface2.realmGet$operText());
        com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$operP1(com_ciot_realm_db_patrol_operationrealmproxyinterface2.realmGet$operP1());
        com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$operTime(com_ciot_realm_db_patrol_operationrealmproxyinterface2.realmGet$operTime());
        com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$operRepeat(com_ciot_realm_db_patrol_operationrealmproxyinterface2.realmGet$operRepeat());
        com_ciot_realm_db_patrol_operationrealmproxyinterface.realmSet$sprayModelPos(com_ciot_realm_db_patrol_operationrealmproxyinterface2.realmGet$sprayModelPos());
        return operation2;
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
        com_ciot_realm_db_patrol_OperationRealmProxy com_ciot_realm_db_patrol_operationrealmproxy = (com_ciot_realm_db_patrol_OperationRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_patrol_operationrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_patrol_operationrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_patrol_operationrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
