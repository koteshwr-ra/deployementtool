package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.alibaba.android.arouter.compiler.utils.Consts;
import com.ciot.realm.db.patrol.TransitionBean;
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

public class com_ciot_realm_db_patrol_TransitionBeanRealmProxy extends TransitionBean implements RealmObjectProxy, com_ciot_realm_db_patrol_TransitionBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private TransitionBeanColumnInfo columnInfo;
    private ProxyState<TransitionBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "TransitionBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class TransitionBeanColumnInfo extends ColumnInfo {
        long dataColKey;
        long enableColKey;

        TransitionBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.enableColKey = addColumnDetails(Consts.VALUE_ENABLE, Consts.VALUE_ENABLE, objectSchemaInfo);
            this.dataColKey = addColumnDetails("data", "data", objectSchemaInfo);
        }

        TransitionBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new TransitionBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            TransitionBeanColumnInfo transitionBeanColumnInfo = (TransitionBeanColumnInfo) columnInfo;
            TransitionBeanColumnInfo transitionBeanColumnInfo2 = (TransitionBeanColumnInfo) columnInfo2;
            transitionBeanColumnInfo2.enableColKey = transitionBeanColumnInfo.enableColKey;
            transitionBeanColumnInfo2.dataColKey = transitionBeanColumnInfo.dataColKey;
        }
    }

    com_ciot_realm_db_patrol_TransitionBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (TransitionBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<TransitionBean> proxyState2 = new ProxyState<>(this);
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

    public String realmGet$data() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.dataColKey);
    }

    public void realmSet$data(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.dataColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.dataColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.dataColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.dataColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 2, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty(Consts.VALUE_ENABLE, RealmFieldType.BOOLEAN, false, false, true);
        builder2.addPersistedProperty("data", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TransitionBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new TransitionBeanColumnInfo(osSchemaInfo);
    }

    public static TransitionBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        TransitionBean transitionBean = (TransitionBean) realm.createObjectInternal(TransitionBean.class, true, Collections.emptyList());
        com_ciot_realm_db_patrol_TransitionBeanRealmProxyInterface com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface = transitionBean;
        if (jSONObject.has(Consts.VALUE_ENABLE)) {
            if (!jSONObject.isNull(Consts.VALUE_ENABLE)) {
                com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmSet$enable(jSONObject.getBoolean(Consts.VALUE_ENABLE));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'enable' to null.");
            }
        }
        if (jSONObject.has("data")) {
            if (jSONObject.isNull("data")) {
                com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmSet$data((String) null);
            } else {
                com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmSet$data(jSONObject.getString("data"));
            }
        }
        return transitionBean;
    }

    public static TransitionBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        TransitionBean transitionBean = new TransitionBean();
        com_ciot_realm_db_patrol_TransitionBeanRealmProxyInterface com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface = transitionBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(Consts.VALUE_ENABLE)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmSet$enable(jsonReader.nextBoolean());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'enable' to null.");
                }
            } else if (!nextName.equals("data")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmSet$data(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmSet$data((String) null);
            }
        }
        jsonReader.endObject();
        return (TransitionBean) realm.copyToRealm(transitionBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_patrol_TransitionBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) TransitionBean.class), false, Collections.emptyList());
        com_ciot_realm_db_patrol_TransitionBeanRealmProxy com_ciot_realm_db_patrol_transitionbeanrealmproxy = new com_ciot_realm_db_patrol_TransitionBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_patrol_transitionbeanrealmproxy;
    }

    public static TransitionBean copyOrUpdate(Realm realm, TransitionBeanColumnInfo transitionBeanColumnInfo, TransitionBean transitionBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((transitionBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(transitionBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) transitionBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return transitionBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(transitionBean);
        if (realmObjectProxy2 != null) {
            return (TransitionBean) realmObjectProxy2;
        }
        return copy(realm, transitionBeanColumnInfo, transitionBean, z, map, set);
    }

    public static TransitionBean copy(Realm realm, TransitionBeanColumnInfo transitionBeanColumnInfo, TransitionBean transitionBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(transitionBean);
        if (realmObjectProxy != null) {
            return (TransitionBean) realmObjectProxy;
        }
        com_ciot_realm_db_patrol_TransitionBeanRealmProxyInterface com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface = transitionBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(TransitionBean.class), set);
        osObjectBuilder.addBoolean(transitionBeanColumnInfo.enableColKey, Boolean.valueOf(com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmGet$enable()));
        osObjectBuilder.addString(transitionBeanColumnInfo.dataColKey, com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmGet$data());
        com_ciot_realm_db_patrol_TransitionBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(transitionBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, TransitionBean transitionBean, Map<RealmModel, Long> map) {
        if ((transitionBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(transitionBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) transitionBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(TransitionBean.class);
        long nativePtr = table.getNativePtr();
        TransitionBeanColumnInfo transitionBeanColumnInfo = (TransitionBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TransitionBean.class);
        long createRow = OsObject.createRow(table);
        map.put(transitionBean, Long.valueOf(createRow));
        com_ciot_realm_db_patrol_TransitionBeanRealmProxyInterface com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface = transitionBean;
        Table.nativeSetBoolean(nativePtr, transitionBeanColumnInfo.enableColKey, createRow, com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmGet$enable(), false);
        String realmGet$data = com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmGet$data();
        if (realmGet$data != null) {
            Table.nativeSetString(nativePtr, transitionBeanColumnInfo.dataColKey, createRow, realmGet$data, false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(TransitionBean.class);
        long nativePtr = table.getNativePtr();
        TransitionBeanColumnInfo transitionBeanColumnInfo = (TransitionBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TransitionBean.class);
        while (it.hasNext()) {
            TransitionBean transitionBean = (TransitionBean) it.next();
            if (!map2.containsKey(transitionBean)) {
                if ((transitionBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(transitionBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) transitionBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(transitionBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(transitionBean, Long.valueOf(createRow));
                com_ciot_realm_db_patrol_TransitionBeanRealmProxyInterface com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface = transitionBean;
                Table.nativeSetBoolean(nativePtr, transitionBeanColumnInfo.enableColKey, createRow, com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmGet$enable(), false);
                String realmGet$data = com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmGet$data();
                if (realmGet$data != null) {
                    Table.nativeSetString(nativePtr, transitionBeanColumnInfo.dataColKey, createRow, realmGet$data, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, TransitionBean transitionBean, Map<RealmModel, Long> map) {
        if ((transitionBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(transitionBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) transitionBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(TransitionBean.class);
        long nativePtr = table.getNativePtr();
        TransitionBeanColumnInfo transitionBeanColumnInfo = (TransitionBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TransitionBean.class);
        long createRow = OsObject.createRow(table);
        map.put(transitionBean, Long.valueOf(createRow));
        com_ciot_realm_db_patrol_TransitionBeanRealmProxyInterface com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface = transitionBean;
        Table.nativeSetBoolean(nativePtr, transitionBeanColumnInfo.enableColKey, createRow, com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmGet$enable(), false);
        String realmGet$data = com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmGet$data();
        if (realmGet$data != null) {
            Table.nativeSetString(nativePtr, transitionBeanColumnInfo.dataColKey, createRow, realmGet$data, false);
        } else {
            Table.nativeSetNull(nativePtr, transitionBeanColumnInfo.dataColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(TransitionBean.class);
        long nativePtr = table.getNativePtr();
        TransitionBeanColumnInfo transitionBeanColumnInfo = (TransitionBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TransitionBean.class);
        while (it.hasNext()) {
            TransitionBean transitionBean = (TransitionBean) it.next();
            if (!map2.containsKey(transitionBean)) {
                if ((transitionBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(transitionBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) transitionBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(transitionBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(transitionBean, Long.valueOf(createRow));
                com_ciot_realm_db_patrol_TransitionBeanRealmProxyInterface com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface = transitionBean;
                Table.nativeSetBoolean(nativePtr, transitionBeanColumnInfo.enableColKey, createRow, com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmGet$enable(), false);
                String realmGet$data = com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmGet$data();
                if (realmGet$data != null) {
                    Table.nativeSetString(nativePtr, transitionBeanColumnInfo.dataColKey, createRow, realmGet$data, false);
                } else {
                    Table.nativeSetNull(nativePtr, transitionBeanColumnInfo.dataColKey, createRow, false);
                }
            }
        }
    }

    public static TransitionBean createDetachedCopy(TransitionBean transitionBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        TransitionBean transitionBean2;
        if (i > i2 || transitionBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(transitionBean);
        if (cacheData == null) {
            transitionBean2 = new TransitionBean();
            map.put(transitionBean, new RealmObjectProxy.CacheData(i, transitionBean2));
        } else if (i >= cacheData.minDepth) {
            return (TransitionBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            transitionBean2 = (TransitionBean) cacheData.object;
        }
        com_ciot_realm_db_patrol_TransitionBeanRealmProxyInterface com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface = transitionBean2;
        com_ciot_realm_db_patrol_TransitionBeanRealmProxyInterface com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface2 = transitionBean;
        com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmSet$enable(com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface2.realmGet$enable());
        com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface.realmSet$data(com_ciot_realm_db_patrol_transitionbeanrealmproxyinterface2.realmGet$data());
        return transitionBean2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("TransitionBean = proxy[");
        sb.append("{enable:");
        sb.append(realmGet$enable());
        sb.append("}");
        sb.append(",");
        sb.append("{data:");
        sb.append(realmGet$data() != null ? realmGet$data() : Configurator.NULL);
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
        com_ciot_realm_db_patrol_TransitionBeanRealmProxy com_ciot_realm_db_patrol_transitionbeanrealmproxy = (com_ciot_realm_db_patrol_TransitionBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_patrol_transitionbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_patrol_transitionbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_patrol_transitionbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
