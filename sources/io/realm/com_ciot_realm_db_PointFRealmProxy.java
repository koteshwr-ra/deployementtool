package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.PointF;
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

public class com_ciot_realm_db_PointFRealmProxy extends PointF implements RealmObjectProxy, com_ciot_realm_db_PointFRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private PointFColumnInfo columnInfo;
    private ProxyState<PointF> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "PointF";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class PointFColumnInfo extends ColumnInfo {
        long xColKey;
        long yColKey;

        PointFColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.xColKey = addColumnDetails("x", "x", objectSchemaInfo);
            this.yColKey = addColumnDetails("y", "y", objectSchemaInfo);
        }

        PointFColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new PointFColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            PointFColumnInfo pointFColumnInfo = (PointFColumnInfo) columnInfo;
            PointFColumnInfo pointFColumnInfo2 = (PointFColumnInfo) columnInfo2;
            pointFColumnInfo2.xColKey = pointFColumnInfo.xColKey;
            pointFColumnInfo2.yColKey = pointFColumnInfo.yColKey;
        }
    }

    com_ciot_realm_db_PointFRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (PointFColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<PointF> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public float realmGet$x() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getFloat(this.columnInfo.xColKey);
    }

    public void realmSet$x(float f) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setFloat(this.columnInfo.xColKey, f);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setFloat(this.columnInfo.xColKey, row$realm.getObjectKey(), f, true);
        }
    }

    public float realmGet$y() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getFloat(this.columnInfo.yColKey);
    }

    public void realmSet$y(float f) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setFloat(this.columnInfo.yColKey, f);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setFloat(this.columnInfo.yColKey, row$realm.getObjectKey(), f, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 2, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("x", RealmFieldType.FLOAT, false, false, true);
        builder2.addPersistedProperty("y", RealmFieldType.FLOAT, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static PointFColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new PointFColumnInfo(osSchemaInfo);
    }

    public static PointF createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        PointF pointF = (PointF) realm.createObjectInternal(PointF.class, true, Collections.emptyList());
        com_ciot_realm_db_PointFRealmProxyInterface com_ciot_realm_db_pointfrealmproxyinterface = pointF;
        if (jSONObject.has("x")) {
            if (!jSONObject.isNull("x")) {
                com_ciot_realm_db_pointfrealmproxyinterface.realmSet$x((float) jSONObject.getDouble("x"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'x' to null.");
            }
        }
        if (jSONObject.has("y")) {
            if (!jSONObject.isNull("y")) {
                com_ciot_realm_db_pointfrealmproxyinterface.realmSet$y((float) jSONObject.getDouble("y"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'y' to null.");
            }
        }
        return pointF;
    }

    public static PointF createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        PointF pointF = new PointF();
        com_ciot_realm_db_PointFRealmProxyInterface com_ciot_realm_db_pointfrealmproxyinterface = pointF;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("x")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_pointfrealmproxyinterface.realmSet$x((float) jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'x' to null.");
                }
            } else if (!nextName.equals("y")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_pointfrealmproxyinterface.realmSet$y((float) jsonReader.nextDouble());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'y' to null.");
            }
        }
        jsonReader.endObject();
        return (PointF) realm.copyToRealm(pointF, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_PointFRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) PointF.class), false, Collections.emptyList());
        com_ciot_realm_db_PointFRealmProxy com_ciot_realm_db_pointfrealmproxy = new com_ciot_realm_db_PointFRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_pointfrealmproxy;
    }

    public static PointF copyOrUpdate(Realm realm, PointFColumnInfo pointFColumnInfo, PointF pointF, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((pointF instanceof RealmObjectProxy) && !RealmObject.isFrozen(pointF)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) pointF;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return pointF;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(pointF);
        if (realmObjectProxy2 != null) {
            return (PointF) realmObjectProxy2;
        }
        return copy(realm, pointFColumnInfo, pointF, z, map, set);
    }

    public static PointF copy(Realm realm, PointFColumnInfo pointFColumnInfo, PointF pointF, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(pointF);
        if (realmObjectProxy != null) {
            return (PointF) realmObjectProxy;
        }
        com_ciot_realm_db_PointFRealmProxyInterface com_ciot_realm_db_pointfrealmproxyinterface = pointF;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(PointF.class), set);
        osObjectBuilder.addFloat(pointFColumnInfo.xColKey, Float.valueOf(com_ciot_realm_db_pointfrealmproxyinterface.realmGet$x()));
        osObjectBuilder.addFloat(pointFColumnInfo.yColKey, Float.valueOf(com_ciot_realm_db_pointfrealmproxyinterface.realmGet$y()));
        com_ciot_realm_db_PointFRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(pointF, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, PointF pointF, Map<RealmModel, Long> map) {
        if ((pointF instanceof RealmObjectProxy) && !RealmObject.isFrozen(pointF)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) pointF;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(PointF.class);
        long nativePtr = table.getNativePtr();
        PointFColumnInfo pointFColumnInfo = (PointFColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PointF.class);
        long createRow = OsObject.createRow(table);
        map.put(pointF, Long.valueOf(createRow));
        com_ciot_realm_db_PointFRealmProxyInterface com_ciot_realm_db_pointfrealmproxyinterface = pointF;
        long j = nativePtr;
        long j2 = createRow;
        Table.nativeSetFloat(j, pointFColumnInfo.xColKey, j2, com_ciot_realm_db_pointfrealmproxyinterface.realmGet$x(), false);
        Table.nativeSetFloat(j, pointFColumnInfo.yColKey, j2, com_ciot_realm_db_pointfrealmproxyinterface.realmGet$y(), false);
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(PointF.class);
        long nativePtr = table.getNativePtr();
        PointFColumnInfo pointFColumnInfo = (PointFColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PointF.class);
        while (it.hasNext()) {
            PointF pointF = (PointF) it.next();
            if (!map2.containsKey(pointF)) {
                if ((pointF instanceof RealmObjectProxy) && !RealmObject.isFrozen(pointF)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) pointF;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(pointF, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(pointF, Long.valueOf(createRow));
                com_ciot_realm_db_PointFRealmProxyInterface com_ciot_realm_db_pointfrealmproxyinterface = pointF;
                long j = nativePtr;
                long j2 = createRow;
                Table.nativeSetFloat(j, pointFColumnInfo.xColKey, j2, com_ciot_realm_db_pointfrealmproxyinterface.realmGet$x(), false);
                Table.nativeSetFloat(j, pointFColumnInfo.yColKey, j2, com_ciot_realm_db_pointfrealmproxyinterface.realmGet$y(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, PointF pointF, Map<RealmModel, Long> map) {
        if ((pointF instanceof RealmObjectProxy) && !RealmObject.isFrozen(pointF)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) pointF;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(PointF.class);
        long nativePtr = table.getNativePtr();
        PointFColumnInfo pointFColumnInfo = (PointFColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PointF.class);
        long createRow = OsObject.createRow(table);
        map.put(pointF, Long.valueOf(createRow));
        com_ciot_realm_db_PointFRealmProxyInterface com_ciot_realm_db_pointfrealmproxyinterface = pointF;
        long j = nativePtr;
        long j2 = createRow;
        Table.nativeSetFloat(j, pointFColumnInfo.xColKey, j2, com_ciot_realm_db_pointfrealmproxyinterface.realmGet$x(), false);
        Table.nativeSetFloat(j, pointFColumnInfo.yColKey, j2, com_ciot_realm_db_pointfrealmproxyinterface.realmGet$y(), false);
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(PointF.class);
        long nativePtr = table.getNativePtr();
        PointFColumnInfo pointFColumnInfo = (PointFColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PointF.class);
        while (it.hasNext()) {
            PointF pointF = (PointF) it.next();
            if (!map2.containsKey(pointF)) {
                if ((pointF instanceof RealmObjectProxy) && !RealmObject.isFrozen(pointF)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) pointF;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(pointF, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(pointF, Long.valueOf(createRow));
                com_ciot_realm_db_PointFRealmProxyInterface com_ciot_realm_db_pointfrealmproxyinterface = pointF;
                long j = nativePtr;
                long j2 = createRow;
                Table.nativeSetFloat(j, pointFColumnInfo.xColKey, j2, com_ciot_realm_db_pointfrealmproxyinterface.realmGet$x(), false);
                Table.nativeSetFloat(j, pointFColumnInfo.yColKey, j2, com_ciot_realm_db_pointfrealmproxyinterface.realmGet$y(), false);
            }
        }
    }

    public static PointF createDetachedCopy(PointF pointF, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        PointF pointF2;
        if (i > i2 || pointF == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(pointF);
        if (cacheData == null) {
            pointF2 = new PointF();
            map.put(pointF, new RealmObjectProxy.CacheData(i, pointF2));
        } else if (i >= cacheData.minDepth) {
            return (PointF) cacheData.object;
        } else {
            cacheData.minDepth = i;
            pointF2 = (PointF) cacheData.object;
        }
        com_ciot_realm_db_PointFRealmProxyInterface com_ciot_realm_db_pointfrealmproxyinterface = pointF2;
        com_ciot_realm_db_PointFRealmProxyInterface com_ciot_realm_db_pointfrealmproxyinterface2 = pointF;
        com_ciot_realm_db_pointfrealmproxyinterface.realmSet$x(com_ciot_realm_db_pointfrealmproxyinterface2.realmGet$x());
        com_ciot_realm_db_pointfrealmproxyinterface.realmSet$y(com_ciot_realm_db_pointfrealmproxyinterface2.realmGet$y());
        return pointF2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        return "PointF = proxy[" + "{x:" + realmGet$x() + "}" + "," + "{y:" + realmGet$y() + "}" + "]";
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
        com_ciot_realm_db_PointFRealmProxy com_ciot_realm_db_pointfrealmproxy = (com_ciot_realm_db_PointFRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_pointfrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_pointfrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_pointfrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
