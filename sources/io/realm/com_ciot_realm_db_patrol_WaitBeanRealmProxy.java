package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.alibaba.android.arouter.compiler.utils.Consts;
import com.ciot.realm.db.patrol.WaitBean;
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

public class com_ciot_realm_db_patrol_WaitBeanRealmProxy extends WaitBean implements RealmObjectProxy, com_ciot_realm_db_patrol_WaitBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private WaitBeanColumnInfo columnInfo;
    private ProxyState<WaitBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "WaitBean";
    }

    public static String getSimpleClassName() {
        return "WaitBean";
    }

    static final class WaitBeanColumnInfo extends ColumnInfo {
        long dataColKey;
        long enableColKey;

        WaitBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo("WaitBean");
            this.enableColKey = addColumnDetails(Consts.VALUE_ENABLE, Consts.VALUE_ENABLE, objectSchemaInfo);
            this.dataColKey = addColumnDetails("data", "data", objectSchemaInfo);
        }

        WaitBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new WaitBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            WaitBeanColumnInfo waitBeanColumnInfo = (WaitBeanColumnInfo) columnInfo;
            WaitBeanColumnInfo waitBeanColumnInfo2 = (WaitBeanColumnInfo) columnInfo2;
            waitBeanColumnInfo2.enableColKey = waitBeanColumnInfo.enableColKey;
            waitBeanColumnInfo2.dataColKey = waitBeanColumnInfo.dataColKey;
        }
    }

    com_ciot_realm_db_patrol_WaitBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (WaitBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<WaitBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public boolean realmGet$enable() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.enableColKey);
    }

    public void realmSet$enable(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.enableColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.enableColKey, row$realm.getObjectKey(), z, true);
        }
    }

    public int realmGet$data() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.dataColKey);
    }

    public void realmSet$data(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.dataColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.dataColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("WaitBean", 2, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty(Consts.VALUE_ENABLE, RealmFieldType.BOOLEAN, false, false, true);
        builder2.addPersistedProperty("data", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static WaitBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new WaitBeanColumnInfo(osSchemaInfo);
    }

    public static WaitBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        WaitBean waitBean = (WaitBean) realm.createObjectInternal(WaitBean.class, true, Collections.emptyList());
        com_ciot_realm_db_patrol_WaitBeanRealmProxyInterface com_ciot_realm_db_patrol_waitbeanrealmproxyinterface = waitBean;
        if (jSONObject.has(Consts.VALUE_ENABLE)) {
            if (!jSONObject.isNull(Consts.VALUE_ENABLE)) {
                com_ciot_realm_db_patrol_waitbeanrealmproxyinterface.realmSet$enable(jSONObject.getBoolean(Consts.VALUE_ENABLE));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'enable' to null.");
            }
        }
        if (jSONObject.has("data")) {
            if (!jSONObject.isNull("data")) {
                com_ciot_realm_db_patrol_waitbeanrealmproxyinterface.realmSet$data(jSONObject.getInt("data"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'data' to null.");
            }
        }
        return waitBean;
    }

    public static WaitBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        WaitBean waitBean = new WaitBean();
        com_ciot_realm_db_patrol_WaitBeanRealmProxyInterface com_ciot_realm_db_patrol_waitbeanrealmproxyinterface = waitBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(Consts.VALUE_ENABLE)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_waitbeanrealmproxyinterface.realmSet$enable(jsonReader.nextBoolean());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'enable' to null.");
                }
            } else if (!nextName.equals("data")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_patrol_waitbeanrealmproxyinterface.realmSet$data(jsonReader.nextInt());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'data' to null.");
            }
        }
        jsonReader.endObject();
        return (WaitBean) realm.copyToRealm(waitBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_patrol_WaitBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) WaitBean.class), false, Collections.emptyList());
        com_ciot_realm_db_patrol_WaitBeanRealmProxy com_ciot_realm_db_patrol_waitbeanrealmproxy = new com_ciot_realm_db_patrol_WaitBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_patrol_waitbeanrealmproxy;
    }

    public static WaitBean copyOrUpdate(Realm realm, WaitBeanColumnInfo waitBeanColumnInfo, WaitBean waitBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((waitBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(waitBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) waitBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return waitBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(waitBean);
        if (realmObjectProxy2 != null) {
            return (WaitBean) realmObjectProxy2;
        }
        return copy(realm, waitBeanColumnInfo, waitBean, z, map, set);
    }

    public static WaitBean copy(Realm realm, WaitBeanColumnInfo waitBeanColumnInfo, WaitBean waitBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(waitBean);
        if (realmObjectProxy != null) {
            return (WaitBean) realmObjectProxy;
        }
        com_ciot_realm_db_patrol_WaitBeanRealmProxyInterface com_ciot_realm_db_patrol_waitbeanrealmproxyinterface = waitBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(WaitBean.class), set);
        osObjectBuilder.addBoolean(waitBeanColumnInfo.enableColKey, Boolean.valueOf(com_ciot_realm_db_patrol_waitbeanrealmproxyinterface.realmGet$enable()));
        osObjectBuilder.addInteger(waitBeanColumnInfo.dataColKey, Integer.valueOf(com_ciot_realm_db_patrol_waitbeanrealmproxyinterface.realmGet$data()));
        com_ciot_realm_db_patrol_WaitBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(waitBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, WaitBean waitBean, Map<RealmModel, Long> map) {
        if ((waitBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(waitBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) waitBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(WaitBean.class);
        long nativePtr = table.getNativePtr();
        WaitBeanColumnInfo waitBeanColumnInfo = (WaitBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) WaitBean.class);
        long createRow = OsObject.createRow(table);
        map.put(waitBean, Long.valueOf(createRow));
        com_ciot_realm_db_patrol_WaitBeanRealmProxyInterface com_ciot_realm_db_patrol_waitbeanrealmproxyinterface = waitBean;
        long j = nativePtr;
        long j2 = createRow;
        Table.nativeSetBoolean(j, waitBeanColumnInfo.enableColKey, j2, com_ciot_realm_db_patrol_waitbeanrealmproxyinterface.realmGet$enable(), false);
        Table.nativeSetLong(j, waitBeanColumnInfo.dataColKey, j2, (long) com_ciot_realm_db_patrol_waitbeanrealmproxyinterface.realmGet$data(), false);
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(WaitBean.class);
        long nativePtr = table.getNativePtr();
        WaitBeanColumnInfo waitBeanColumnInfo = (WaitBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) WaitBean.class);
        while (it.hasNext()) {
            WaitBean waitBean = (WaitBean) it.next();
            if (!map2.containsKey(waitBean)) {
                if ((waitBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(waitBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) waitBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(waitBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(waitBean, Long.valueOf(createRow));
                com_ciot_realm_db_patrol_WaitBeanRealmProxyInterface com_ciot_realm_db_patrol_waitbeanrealmproxyinterface = waitBean;
                long j = createRow;
                Table.nativeSetBoolean(nativePtr, waitBeanColumnInfo.enableColKey, j, com_ciot_realm_db_patrol_waitbeanrealmproxyinterface.realmGet$enable(), false);
                Table.nativeSetLong(nativePtr, waitBeanColumnInfo.dataColKey, j, (long) com_ciot_realm_db_patrol_waitbeanrealmproxyinterface.realmGet$data(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, WaitBean waitBean, Map<RealmModel, Long> map) {
        if ((waitBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(waitBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) waitBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(WaitBean.class);
        long nativePtr = table.getNativePtr();
        WaitBeanColumnInfo waitBeanColumnInfo = (WaitBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) WaitBean.class);
        long createRow = OsObject.createRow(table);
        map.put(waitBean, Long.valueOf(createRow));
        com_ciot_realm_db_patrol_WaitBeanRealmProxyInterface com_ciot_realm_db_patrol_waitbeanrealmproxyinterface = waitBean;
        long j = nativePtr;
        long j2 = createRow;
        Table.nativeSetBoolean(j, waitBeanColumnInfo.enableColKey, j2, com_ciot_realm_db_patrol_waitbeanrealmproxyinterface.realmGet$enable(), false);
        Table.nativeSetLong(j, waitBeanColumnInfo.dataColKey, j2, (long) com_ciot_realm_db_patrol_waitbeanrealmproxyinterface.realmGet$data(), false);
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(WaitBean.class);
        long nativePtr = table.getNativePtr();
        WaitBeanColumnInfo waitBeanColumnInfo = (WaitBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) WaitBean.class);
        while (it.hasNext()) {
            WaitBean waitBean = (WaitBean) it.next();
            if (!map2.containsKey(waitBean)) {
                if ((waitBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(waitBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) waitBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(waitBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(waitBean, Long.valueOf(createRow));
                com_ciot_realm_db_patrol_WaitBeanRealmProxyInterface com_ciot_realm_db_patrol_waitbeanrealmproxyinterface = waitBean;
                long j = createRow;
                Table.nativeSetBoolean(nativePtr, waitBeanColumnInfo.enableColKey, j, com_ciot_realm_db_patrol_waitbeanrealmproxyinterface.realmGet$enable(), false);
                Table.nativeSetLong(nativePtr, waitBeanColumnInfo.dataColKey, j, (long) com_ciot_realm_db_patrol_waitbeanrealmproxyinterface.realmGet$data(), false);
            }
        }
    }

    public static WaitBean createDetachedCopy(WaitBean waitBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        WaitBean waitBean2;
        if (i > i2 || waitBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(waitBean);
        if (cacheData == null) {
            waitBean2 = new WaitBean();
            map.put(waitBean, new RealmObjectProxy.CacheData(i, waitBean2));
        } else if (i >= cacheData.minDepth) {
            return (WaitBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            waitBean2 = (WaitBean) cacheData.object;
        }
        com_ciot_realm_db_patrol_WaitBeanRealmProxyInterface com_ciot_realm_db_patrol_waitbeanrealmproxyinterface = waitBean2;
        com_ciot_realm_db_patrol_WaitBeanRealmProxyInterface com_ciot_realm_db_patrol_waitbeanrealmproxyinterface2 = waitBean;
        com_ciot_realm_db_patrol_waitbeanrealmproxyinterface.realmSet$enable(com_ciot_realm_db_patrol_waitbeanrealmproxyinterface2.realmGet$enable());
        com_ciot_realm_db_patrol_waitbeanrealmproxyinterface.realmSet$data(com_ciot_realm_db_patrol_waitbeanrealmproxyinterface2.realmGet$data());
        return waitBean2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        return "WaitBean = proxy[" + "{enable:" + realmGet$enable() + "}" + "," + "{data:" + realmGet$data() + "}" + "]";
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
        com_ciot_realm_db_patrol_WaitBeanRealmProxy com_ciot_realm_db_patrol_waitbeanrealmproxy = (com_ciot_realm_db_patrol_WaitBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_patrol_waitbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_patrol_waitbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_patrol_waitbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
