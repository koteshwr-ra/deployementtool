package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.ad.CycleBean;
import com.ciot.realm.db.patrol.Operation;
import com.ciot.realm.db.patrol.Point;
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

public class com_ciot_realm_db_patrol_PointRealmProxy extends Point implements RealmObjectProxy, com_ciot_realm_db_patrol_PointRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private PointColumnInfo columnInfo;
    private RealmList<Operation> operRealmList;
    private ProxyState<Point> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Point";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class PointColumnInfo extends ColumnInfo {
        long operColKey;
        long repeatColKey;
        long timeColKey;
        long transitionColKey;

        PointColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(4);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.timeColKey = addColumnDetails(CycleBean.TIME_TYPE, CycleBean.TIME_TYPE, objectSchemaInfo);
            this.repeatColKey = addColumnDetails("repeat", "repeat", objectSchemaInfo);
            this.operColKey = addColumnDetails("oper", "oper", objectSchemaInfo);
            this.transitionColKey = addColumnDetails("transition", "transition", objectSchemaInfo);
        }

        PointColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new PointColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            PointColumnInfo pointColumnInfo = (PointColumnInfo) columnInfo;
            PointColumnInfo pointColumnInfo2 = (PointColumnInfo) columnInfo2;
            pointColumnInfo2.timeColKey = pointColumnInfo.timeColKey;
            pointColumnInfo2.repeatColKey = pointColumnInfo.repeatColKey;
            pointColumnInfo2.operColKey = pointColumnInfo.operColKey;
            pointColumnInfo2.transitionColKey = pointColumnInfo.transitionColKey;
        }
    }

    com_ciot_realm_db_patrol_PointRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (PointColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<Point> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public int realmGet$time() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.timeColKey);
    }

    public void realmSet$time(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.timeColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.timeColKey, row$realm.getObjectKey(), (long) i, true);
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

    public String realmGet$transition() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.transitionColKey);
    }

    public void realmSet$transition(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.transitionColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.transitionColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.transitionColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.transitionColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 4, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty(CycleBean.TIME_TYPE, RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("repeat", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedLinkProperty("oper", RealmFieldType.LIST, com_ciot_realm_db_patrol_OperationRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedProperty("transition", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static PointColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new PointColumnInfo(osSchemaInfo);
    }

    public static Point createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        ArrayList arrayList = new ArrayList(1);
        if (jSONObject.has("oper")) {
            arrayList.add("oper");
        }
        Point point = (Point) realm.createObjectInternal(Point.class, true, arrayList);
        com_ciot_realm_db_patrol_PointRealmProxyInterface com_ciot_realm_db_patrol_pointrealmproxyinterface = point;
        if (jSONObject.has(CycleBean.TIME_TYPE)) {
            if (!jSONObject.isNull(CycleBean.TIME_TYPE)) {
                com_ciot_realm_db_patrol_pointrealmproxyinterface.realmSet$time(jSONObject.getInt(CycleBean.TIME_TYPE));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'time' to null.");
            }
        }
        if (jSONObject.has("repeat")) {
            if (!jSONObject.isNull("repeat")) {
                com_ciot_realm_db_patrol_pointrealmproxyinterface.realmSet$repeat(jSONObject.getInt("repeat"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'repeat' to null.");
            }
        }
        if (jSONObject.has("oper")) {
            if (jSONObject.isNull("oper")) {
                com_ciot_realm_db_patrol_pointrealmproxyinterface.realmSet$oper((RealmList<Operation>) null);
            } else {
                com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$oper().clear();
                JSONArray jSONArray = jSONObject.getJSONArray("oper");
                for (int i = 0; i < jSONArray.length(); i++) {
                    com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$oper().add(com_ciot_realm_db_patrol_OperationRealmProxy.createOrUpdateUsingJsonObject(realm, jSONArray.getJSONObject(i), z));
                }
            }
        }
        if (jSONObject.has("transition")) {
            if (jSONObject.isNull("transition")) {
                com_ciot_realm_db_patrol_pointrealmproxyinterface.realmSet$transition((String) null);
            } else {
                com_ciot_realm_db_patrol_pointrealmproxyinterface.realmSet$transition(jSONObject.getString("transition"));
            }
        }
        return point;
    }

    public static Point createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        Point point = new Point();
        com_ciot_realm_db_patrol_PointRealmProxyInterface com_ciot_realm_db_patrol_pointrealmproxyinterface = point;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(CycleBean.TIME_TYPE)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_pointrealmproxyinterface.realmSet$time(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'time' to null.");
                }
            } else if (nextName.equals("repeat")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_pointrealmproxyinterface.realmSet$repeat(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'repeat' to null.");
                }
            } else if (nextName.equals("oper")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_pointrealmproxyinterface.realmSet$oper((RealmList<Operation>) null);
                } else {
                    com_ciot_realm_db_patrol_pointrealmproxyinterface.realmSet$oper(new RealmList());
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$oper().add(com_ciot_realm_db_patrol_OperationRealmProxy.createUsingJsonStream(realm, jsonReader));
                    }
                    jsonReader.endArray();
                }
            } else if (!nextName.equals("transition")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_patrol_pointrealmproxyinterface.realmSet$transition(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_patrol_pointrealmproxyinterface.realmSet$transition((String) null);
            }
        }
        jsonReader.endObject();
        return (Point) realm.copyToRealm(point, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_patrol_PointRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) Point.class), false, Collections.emptyList());
        com_ciot_realm_db_patrol_PointRealmProxy com_ciot_realm_db_patrol_pointrealmproxy = new com_ciot_realm_db_patrol_PointRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_patrol_pointrealmproxy;
    }

    public static Point copyOrUpdate(Realm realm, PointColumnInfo pointColumnInfo, Point point, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((point instanceof RealmObjectProxy) && !RealmObject.isFrozen(point)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) point;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return point;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(point);
        if (realmObjectProxy2 != null) {
            return (Point) realmObjectProxy2;
        }
        return copy(realm, pointColumnInfo, point, z, map, set);
    }

    public static Point copy(Realm realm, PointColumnInfo pointColumnInfo, Point point, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(point);
        if (realmObjectProxy != null) {
            return (Point) realmObjectProxy;
        }
        com_ciot_realm_db_patrol_PointRealmProxyInterface com_ciot_realm_db_patrol_pointrealmproxyinterface = point;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Point.class), set);
        osObjectBuilder.addInteger(pointColumnInfo.timeColKey, Integer.valueOf(com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$time()));
        osObjectBuilder.addInteger(pointColumnInfo.repeatColKey, Integer.valueOf(com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$repeat()));
        osObjectBuilder.addString(pointColumnInfo.transitionColKey, com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$transition());
        com_ciot_realm_db_patrol_PointRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(point, newProxyInstance);
        RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$oper();
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

    public static long insert(Realm realm, Point point, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        Point point2 = point;
        Map<RealmModel, Long> map2 = map;
        if ((point2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(point)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) point2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(Point.class);
        long nativePtr = table.getNativePtr();
        PointColumnInfo pointColumnInfo = (PointColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Point.class);
        long createRow = OsObject.createRow(table);
        map2.put(point2, Long.valueOf(createRow));
        com_ciot_realm_db_patrol_PointRealmProxyInterface com_ciot_realm_db_patrol_pointrealmproxyinterface = point2;
        long j2 = createRow;
        Table.nativeSetLong(nativePtr, pointColumnInfo.timeColKey, createRow, (long) com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$time(), false);
        Table.nativeSetLong(nativePtr, pointColumnInfo.repeatColKey, j2, (long) com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$repeat(), false);
        RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$oper();
        if (realmGet$oper != null) {
            j = j2;
            OsList osList = new OsList(table.getUncheckedRow(j), pointColumnInfo.operColKey);
            Iterator<Operation> it = realmGet$oper.iterator();
            while (it.hasNext()) {
                Operation next = it.next();
                Long l = map2.get(next);
                if (l == null) {
                    l = Long.valueOf(com_ciot_realm_db_patrol_OperationRealmProxy.insert(realm2, next, map2));
                }
                osList.addRow(l.longValue());
            }
        } else {
            j = j2;
        }
        String realmGet$transition = com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$transition();
        if (realmGet$transition == null) {
            return j;
        }
        long j3 = j;
        Table.nativeSetString(nativePtr, pointColumnInfo.transitionColKey, j, realmGet$transition, false);
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(Point.class);
        long nativePtr = table.getNativePtr();
        PointColumnInfo pointColumnInfo = (PointColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Point.class);
        while (it.hasNext()) {
            Point point = (Point) it.next();
            if (!map2.containsKey(point)) {
                if ((point instanceof RealmObjectProxy) && !RealmObject.isFrozen(point)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) point;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(point, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(point, Long.valueOf(createRow));
                com_ciot_realm_db_patrol_PointRealmProxyInterface com_ciot_realm_db_patrol_pointrealmproxyinterface = point;
                long j2 = createRow;
                Table.nativeSetLong(nativePtr, pointColumnInfo.timeColKey, createRow, (long) com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$time(), false);
                Table.nativeSetLong(nativePtr, pointColumnInfo.repeatColKey, j2, (long) com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$repeat(), false);
                RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$oper();
                if (realmGet$oper != null) {
                    j = j2;
                    OsList osList = new OsList(table.getUncheckedRow(j), pointColumnInfo.operColKey);
                    Iterator<Operation> it2 = realmGet$oper.iterator();
                    while (it2.hasNext()) {
                        Operation next = it2.next();
                        Long l = map2.get(next);
                        if (l == null) {
                            l = Long.valueOf(com_ciot_realm_db_patrol_OperationRealmProxy.insert(realm2, next, map2));
                        }
                        osList.addRow(l.longValue());
                    }
                } else {
                    j = j2;
                }
                String realmGet$transition = com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$transition();
                if (realmGet$transition != null) {
                    Table.nativeSetString(nativePtr, pointColumnInfo.transitionColKey, j, realmGet$transition, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Point point, Map<RealmModel, Long> map) {
        Realm realm2 = realm;
        Point point2 = point;
        Map<RealmModel, Long> map2 = map;
        if ((point2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(point)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) point2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(Point.class);
        long nativePtr = table.getNativePtr();
        PointColumnInfo pointColumnInfo = (PointColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Point.class);
        long createRow = OsObject.createRow(table);
        map2.put(point2, Long.valueOf(createRow));
        com_ciot_realm_db_patrol_PointRealmProxyInterface com_ciot_realm_db_patrol_pointrealmproxyinterface = point2;
        long j = createRow;
        Table.nativeSetLong(nativePtr, pointColumnInfo.timeColKey, createRow, (long) com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$time(), false);
        Table.nativeSetLong(nativePtr, pointColumnInfo.repeatColKey, j, (long) com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$repeat(), false);
        long j2 = j;
        OsList osList = new OsList(table.getUncheckedRow(j2), pointColumnInfo.operColKey);
        RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$oper();
        if (realmGet$oper == null || ((long) realmGet$oper.size()) != osList.size()) {
            osList.removeAll();
            if (realmGet$oper != null) {
                Iterator<Operation> it = realmGet$oper.iterator();
                while (it.hasNext()) {
                    Operation next = it.next();
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
        String realmGet$transition = com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$transition();
        if (realmGet$transition != null) {
            long j3 = j2;
            Table.nativeSetString(nativePtr, pointColumnInfo.transitionColKey, j2, realmGet$transition, false);
            return j3;
        }
        long j4 = j2;
        Table.nativeSetNull(nativePtr, pointColumnInfo.transitionColKey, j4, false);
        return j4;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(Point.class);
        long nativePtr = table.getNativePtr();
        PointColumnInfo pointColumnInfo = (PointColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Point.class);
        while (it.hasNext()) {
            Point point = (Point) it.next();
            if (!map2.containsKey(point)) {
                if ((point instanceof RealmObjectProxy) && !RealmObject.isFrozen(point)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) point;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(point, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(point, Long.valueOf(createRow));
                com_ciot_realm_db_patrol_PointRealmProxyInterface com_ciot_realm_db_patrol_pointrealmproxyinterface = point;
                long j2 = createRow;
                Table.nativeSetLong(nativePtr, pointColumnInfo.timeColKey, createRow, (long) com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$time(), false);
                long j3 = j2;
                Table.nativeSetLong(nativePtr, pointColumnInfo.repeatColKey, j3, (long) com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$repeat(), false);
                OsList osList = new OsList(table.getUncheckedRow(j3), pointColumnInfo.operColKey);
                RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$oper();
                if (realmGet$oper == null || ((long) realmGet$oper.size()) != osList.size()) {
                    j = j3;
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
                    int i = 0;
                    while (i < size) {
                        Operation operation = realmGet$oper.get(i);
                        Long l2 = map2.get(operation);
                        if (l2 == null) {
                            l2 = Long.valueOf(com_ciot_realm_db_patrol_OperationRealmProxy.insertOrUpdate(realm2, operation, map2));
                        }
                        osList.setRow((long) i, l2.longValue());
                        i++;
                        j3 = j3;
                    }
                    j = j3;
                }
                String realmGet$transition = com_ciot_realm_db_patrol_pointrealmproxyinterface.realmGet$transition();
                if (realmGet$transition != null) {
                    Table.nativeSetString(nativePtr, pointColumnInfo.transitionColKey, j, realmGet$transition, false);
                } else {
                    Table.nativeSetNull(nativePtr, pointColumnInfo.transitionColKey, j, false);
                }
            }
        }
    }

    public static Point createDetachedCopy(Point point, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        Point point2;
        if (i > i2 || point == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(point);
        if (cacheData == null) {
            point2 = new Point();
            map.put(point, new RealmObjectProxy.CacheData(i, point2));
        } else if (i >= cacheData.minDepth) {
            return (Point) cacheData.object;
        } else {
            cacheData.minDepth = i;
            point2 = (Point) cacheData.object;
        }
        com_ciot_realm_db_patrol_PointRealmProxyInterface com_ciot_realm_db_patrol_pointrealmproxyinterface = point2;
        com_ciot_realm_db_patrol_PointRealmProxyInterface com_ciot_realm_db_patrol_pointrealmproxyinterface2 = point;
        com_ciot_realm_db_patrol_pointrealmproxyinterface.realmSet$time(com_ciot_realm_db_patrol_pointrealmproxyinterface2.realmGet$time());
        com_ciot_realm_db_patrol_pointrealmproxyinterface.realmSet$repeat(com_ciot_realm_db_patrol_pointrealmproxyinterface2.realmGet$repeat());
        if (i == i2) {
            com_ciot_realm_db_patrol_pointrealmproxyinterface.realmSet$oper((RealmList<Operation>) null);
        } else {
            RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_pointrealmproxyinterface2.realmGet$oper();
            RealmList realmList = new RealmList();
            com_ciot_realm_db_patrol_pointrealmproxyinterface.realmSet$oper(realmList);
            int i3 = i + 1;
            int size = realmGet$oper.size();
            for (int i4 = 0; i4 < size; i4++) {
                realmList.add(com_ciot_realm_db_patrol_OperationRealmProxy.createDetachedCopy(realmGet$oper.get(i4), i3, i2, map));
            }
        }
        com_ciot_realm_db_patrol_pointrealmproxyinterface.realmSet$transition(com_ciot_realm_db_patrol_pointrealmproxyinterface2.realmGet$transition());
        return point2;
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
        com_ciot_realm_db_patrol_PointRealmProxy com_ciot_realm_db_patrol_pointrealmproxy = (com_ciot_realm_db_patrol_PointRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_patrol_pointrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_patrol_pointrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_patrol_pointrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
