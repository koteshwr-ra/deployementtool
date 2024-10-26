package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.patrol.Operation;
import com.ciot.realm.db.patrol.Process;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_patrol_OperationRealmProxy;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.objectstore.OsObjectBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class com_ciot_realm_db_patrol_ProcessRealmProxy extends Process implements RealmObjectProxy, com_ciot_realm_db_patrol_ProcessRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ProcessColumnInfo columnInfo;
    private RealmList<Operation> operRealmList;
    private ProxyState<Process> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Process";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class ProcessColumnInfo extends ColumnInfo {
        long operColKey;
        long repeatColKey;

        ProcessColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.repeatColKey = addColumnDetails("repeat", "repeat", objectSchemaInfo);
            this.operColKey = addColumnDetails("oper", "oper", objectSchemaInfo);
        }

        ProcessColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new ProcessColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            ProcessColumnInfo processColumnInfo = (ProcessColumnInfo) columnInfo;
            ProcessColumnInfo processColumnInfo2 = (ProcessColumnInfo) columnInfo2;
            processColumnInfo2.repeatColKey = processColumnInfo.repeatColKey;
            processColumnInfo2.operColKey = processColumnInfo.operColKey;
        }
    }

    com_ciot_realm_db_patrol_ProcessRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ProcessColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<Process> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public int realmGet$repeat() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.repeatColKey);
    }

    public void realmSet$repeat(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.repeatColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.repeatColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public RealmList<Operation> realmGet$oper() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<Operation> realmList = this.operRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<Operation> realmList2 = new RealmList<>(Operation.class, this.proxyState.getRow$realm().getModelList(this.columnInfo.operColKey), this.proxyState.getRealm$realm());
        this.operRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$oper(RealmList<Operation> realmList) {
        int i = 0;
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("oper")) {
                return;
            }
            if (realmList != null && !realmList.isManaged()) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<Operation> realmList2 = new RealmList<>();
                Iterator<Operation> it = realmList.iterator();
                while (it.hasNext()) {
                    Operation next = it.next();
                    if (next == null || RealmObject.isManaged(next)) {
                        realmList2.add(next);
                    } else {
                        realmList2.add((Operation) realm.copyToRealm(next, new ImportFlag[0]));
                    }
                }
                realmList = realmList2;
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList modelList = this.proxyState.getRow$realm().getModelList(this.columnInfo.operColKey);
        if (realmList == null || ((long) realmList.size()) != modelList.size()) {
            modelList.removeAll();
            if (realmList != null) {
                int size = realmList.size();
                while (i < size) {
                    Operation operation = realmList.get(i);
                    this.proxyState.checkValidObject(operation);
                    modelList.addRow(((RealmObjectProxy) operation).realmGet$proxyState().getRow$realm().getObjectKey());
                    i++;
                }
                return;
            }
            return;
        }
        int size2 = realmList.size();
        while (i < size2) {
            Operation operation2 = realmList.get(i);
            this.proxyState.checkValidObject(operation2);
            modelList.setRow((long) i, ((RealmObjectProxy) operation2).realmGet$proxyState().getRow$realm().getObjectKey());
            i++;
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 2, 0);
        builder.addPersistedProperty("repeat", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedLinkProperty("oper", RealmFieldType.LIST, com_ciot_realm_db_patrol_OperationRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ProcessColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new ProcessColumnInfo(osSchemaInfo);
    }

    public static Process createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        ArrayList arrayList = new ArrayList(1);
        if (jSONObject.has("oper")) {
            arrayList.add("oper");
        }
        Process process = (Process) realm.createObjectInternal(Process.class, true, arrayList);
        com_ciot_realm_db_patrol_ProcessRealmProxyInterface com_ciot_realm_db_patrol_processrealmproxyinterface = process;
        if (jSONObject.has("repeat")) {
            if (!jSONObject.isNull("repeat")) {
                com_ciot_realm_db_patrol_processrealmproxyinterface.realmSet$repeat(jSONObject.getInt("repeat"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'repeat' to null.");
            }
        }
        if (jSONObject.has("oper")) {
            if (jSONObject.isNull("oper")) {
                com_ciot_realm_db_patrol_processrealmproxyinterface.realmSet$oper((RealmList<Operation>) null);
            } else {
                com_ciot_realm_db_patrol_processrealmproxyinterface.realmGet$oper().clear();
                JSONArray jSONArray = jSONObject.getJSONArray("oper");
                for (int i = 0; i < jSONArray.length(); i++) {
                    com_ciot_realm_db_patrol_processrealmproxyinterface.realmGet$oper().add(com_ciot_realm_db_patrol_OperationRealmProxy.createOrUpdateUsingJsonObject(realm, jSONArray.getJSONObject(i), z));
                }
            }
        }
        return process;
    }

    public static Process createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        Process process = new Process();
        com_ciot_realm_db_patrol_ProcessRealmProxyInterface com_ciot_realm_db_patrol_processrealmproxyinterface = process;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("repeat")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_processrealmproxyinterface.realmSet$repeat(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'repeat' to null.");
                }
            } else if (!nextName.equals("oper")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.skipValue();
                com_ciot_realm_db_patrol_processrealmproxyinterface.realmSet$oper((RealmList<Operation>) null);
            } else {
                com_ciot_realm_db_patrol_processrealmproxyinterface.realmSet$oper(new RealmList());
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    com_ciot_realm_db_patrol_processrealmproxyinterface.realmGet$oper().add(com_ciot_realm_db_patrol_OperationRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
                jsonReader.endArray();
            }
        }
        jsonReader.endObject();
        return (Process) realm.copyToRealm(process, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_patrol_ProcessRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) Process.class), false, Collections.emptyList());
        com_ciot_realm_db_patrol_ProcessRealmProxy com_ciot_realm_db_patrol_processrealmproxy = new com_ciot_realm_db_patrol_ProcessRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_patrol_processrealmproxy;
    }

    public static Process copyOrUpdate(Realm realm, ProcessColumnInfo processColumnInfo, Process process, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((process instanceof RealmObjectProxy) && !RealmObject.isFrozen(process)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) process;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return process;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(process);
        if (realmObjectProxy2 != null) {
            return (Process) realmObjectProxy2;
        }
        return copy(realm, processColumnInfo, process, z, map, set);
    }

    public static Process copy(Realm realm, ProcessColumnInfo processColumnInfo, Process process, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(process);
        if (realmObjectProxy != null) {
            return (Process) realmObjectProxy;
        }
        com_ciot_realm_db_patrol_ProcessRealmProxyInterface com_ciot_realm_db_patrol_processrealmproxyinterface = process;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Process.class), set);
        osObjectBuilder.addInteger(processColumnInfo.repeatColKey, Integer.valueOf(com_ciot_realm_db_patrol_processrealmproxyinterface.realmGet$repeat()));
        com_ciot_realm_db_patrol_ProcessRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(process, newProxyInstance);
        RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_processrealmproxyinterface.realmGet$oper();
        if (realmGet$oper != null) {
            RealmList<Operation> realmGet$oper2 = newProxyInstance.realmGet$oper();
            realmGet$oper2.clear();
            for (int i = 0; i < realmGet$oper.size(); i++) {
                Operation operation = realmGet$oper.get(i);
                Operation operation2 = (Operation) map.get(operation);
                if (operation2 != null) {
                    realmGet$oper2.add(operation2);
                } else {
                    realmGet$oper2.add(com_ciot_realm_db_patrol_OperationRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_OperationRealmProxy.OperationColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Operation.class), operation, z, map, set));
                }
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, Process process, Map<RealmModel, Long> map) {
        if ((process instanceof RealmObjectProxy) && !RealmObject.isFrozen(process)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) process;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Process.class);
        long nativePtr = table.getNativePtr();
        ProcessColumnInfo processColumnInfo = (ProcessColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Process.class);
        long createRow = OsObject.createRow(table);
        map.put(process, Long.valueOf(createRow));
        com_ciot_realm_db_patrol_ProcessRealmProxyInterface com_ciot_realm_db_patrol_processrealmproxyinterface = process;
        Table.nativeSetLong(nativePtr, processColumnInfo.repeatColKey, createRow, (long) com_ciot_realm_db_patrol_processrealmproxyinterface.realmGet$repeat(), false);
        RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_processrealmproxyinterface.realmGet$oper();
        if (realmGet$oper != null) {
            OsList osList = new OsList(table.getUncheckedRow(createRow), processColumnInfo.operColKey);
            Iterator<Operation> it = realmGet$oper.iterator();
            while (it.hasNext()) {
                Operation next = it.next();
                Long l = map.get(next);
                if (l == null) {
                    l = Long.valueOf(com_ciot_realm_db_patrol_OperationRealmProxy.insert(realm, next, map));
                }
                osList.addRow(l.longValue());
            }
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(Process.class);
        long nativePtr = table.getNativePtr();
        ProcessColumnInfo processColumnInfo = (ProcessColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Process.class);
        while (it.hasNext()) {
            Process process = (Process) it.next();
            if (!map2.containsKey(process)) {
                if ((process instanceof RealmObjectProxy) && !RealmObject.isFrozen(process)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) process;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(process, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(process, Long.valueOf(createRow));
                com_ciot_realm_db_patrol_ProcessRealmProxyInterface com_ciot_realm_db_patrol_processrealmproxyinterface = process;
                long j = nativePtr;
                long j2 = createRow;
                Table.nativeSetLong(nativePtr, processColumnInfo.repeatColKey, createRow, (long) com_ciot_realm_db_patrol_processrealmproxyinterface.realmGet$repeat(), false);
                RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_processrealmproxyinterface.realmGet$oper();
                if (realmGet$oper != null) {
                    OsList osList = new OsList(table.getUncheckedRow(j2), processColumnInfo.operColKey);
                    Iterator<Operation> it2 = realmGet$oper.iterator();
                    while (it2.hasNext()) {
                        Operation next = it2.next();
                        Long l = map2.get(next);
                        if (l == null) {
                            l = Long.valueOf(com_ciot_realm_db_patrol_OperationRealmProxy.insert(realm2, next, map2));
                        }
                        osList.addRow(l.longValue());
                    }
                }
                nativePtr = j;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Process process, Map<RealmModel, Long> map) {
        if ((process instanceof RealmObjectProxy) && !RealmObject.isFrozen(process)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) process;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Process.class);
        long nativePtr = table.getNativePtr();
        ProcessColumnInfo processColumnInfo = (ProcessColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Process.class);
        long createRow = OsObject.createRow(table);
        map.put(process, Long.valueOf(createRow));
        com_ciot_realm_db_patrol_ProcessRealmProxyInterface com_ciot_realm_db_patrol_processrealmproxyinterface = process;
        Table.nativeSetLong(nativePtr, processColumnInfo.repeatColKey, createRow, (long) com_ciot_realm_db_patrol_processrealmproxyinterface.realmGet$repeat(), false);
        OsList osList = new OsList(table.getUncheckedRow(createRow), processColumnInfo.operColKey);
        RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_processrealmproxyinterface.realmGet$oper();
        if (realmGet$oper == null || ((long) realmGet$oper.size()) != osList.size()) {
            osList.removeAll();
            if (realmGet$oper != null) {
                Iterator<Operation> it = realmGet$oper.iterator();
                while (it.hasNext()) {
                    Operation next = it.next();
                    Long l = map.get(next);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_patrol_OperationRealmProxy.insertOrUpdate(realm, next, map));
                    }
                    osList.addRow(l.longValue());
                }
            }
        } else {
            int size = realmGet$oper.size();
            for (int i = 0; i < size; i++) {
                Operation operation = realmGet$oper.get(i);
                Long l2 = map.get(operation);
                if (l2 == null) {
                    l2 = Long.valueOf(com_ciot_realm_db_patrol_OperationRealmProxy.insertOrUpdate(realm, operation, map));
                }
                osList.setRow((long) i, l2.longValue());
            }
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(Process.class);
        long nativePtr = table.getNativePtr();
        ProcessColumnInfo processColumnInfo = (ProcessColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Process.class);
        while (it.hasNext()) {
            Process process = (Process) it.next();
            if (!map2.containsKey(process)) {
                if ((process instanceof RealmObjectProxy) && !RealmObject.isFrozen(process)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) process;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(process, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(process, Long.valueOf(createRow));
                com_ciot_realm_db_patrol_ProcessRealmProxyInterface com_ciot_realm_db_patrol_processrealmproxyinterface = process;
                long j = nativePtr;
                Table.nativeSetLong(nativePtr, processColumnInfo.repeatColKey, createRow, (long) com_ciot_realm_db_patrol_processrealmproxyinterface.realmGet$repeat(), false);
                OsList osList = new OsList(table.getUncheckedRow(createRow), processColumnInfo.operColKey);
                RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_processrealmproxyinterface.realmGet$oper();
                if (realmGet$oper == null || ((long) realmGet$oper.size()) != osList.size()) {
                    osList.removeAll();
                    if (realmGet$oper != null) {
                        Iterator<Operation> it2 = realmGet$oper.iterator();
                        while (it2.hasNext()) {
                            Operation next = it2.next();
                            Long l = map2.get(next);
                            if (l == null) {
                                l = Long.valueOf(com_ciot_realm_db_patrol_OperationRealmProxy.insertOrUpdate(realm2, next, map2));
                            }
                            osList.addRow(l.longValue());
                        }
                    }
                } else {
                    int size = realmGet$oper.size();
                    for (int i = 0; i < size; i++) {
                        Operation operation = realmGet$oper.get(i);
                        Long l2 = map2.get(operation);
                        if (l2 == null) {
                            l2 = Long.valueOf(com_ciot_realm_db_patrol_OperationRealmProxy.insertOrUpdate(realm2, operation, map2));
                        }
                        osList.setRow((long) i, l2.longValue());
                    }
                }
                nativePtr = j;
            }
        }
    }

    public static Process createDetachedCopy(Process process, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        Process process2;
        if (i > i2 || process == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(process);
        if (cacheData == null) {
            process2 = new Process();
            map.put(process, new RealmObjectProxy.CacheData(i, process2));
        } else if (i >= cacheData.minDepth) {
            return (Process) cacheData.object;
        } else {
            cacheData.minDepth = i;
            process2 = (Process) cacheData.object;
        }
        com_ciot_realm_db_patrol_ProcessRealmProxyInterface com_ciot_realm_db_patrol_processrealmproxyinterface = process2;
        com_ciot_realm_db_patrol_ProcessRealmProxyInterface com_ciot_realm_db_patrol_processrealmproxyinterface2 = process;
        com_ciot_realm_db_patrol_processrealmproxyinterface.realmSet$repeat(com_ciot_realm_db_patrol_processrealmproxyinterface2.realmGet$repeat());
        if (i == i2) {
            com_ciot_realm_db_patrol_processrealmproxyinterface.realmSet$oper((RealmList<Operation>) null);
        } else {
            RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_processrealmproxyinterface2.realmGet$oper();
            RealmList realmList = new RealmList();
            com_ciot_realm_db_patrol_processrealmproxyinterface.realmSet$oper(realmList);
            int i3 = i + 1;
            int size = realmGet$oper.size();
            for (int i4 = 0; i4 < size; i4++) {
                realmList.add(com_ciot_realm_db_patrol_OperationRealmProxy.createDetachedCopy(realmGet$oper.get(i4), i3, i2, map));
            }
        }
        return process2;
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
        com_ciot_realm_db_patrol_ProcessRealmProxy com_ciot_realm_db_patrol_processrealmproxy = (com_ciot_realm_db_patrol_ProcessRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_patrol_processrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_patrol_processrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_patrol_processrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
