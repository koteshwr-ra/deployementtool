package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.ad.CycleBean;
import com.ciot.realm.db.common.AckBean;
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

public class com_ciot_realm_db_common_AckBeanRealmProxy extends AckBean implements RealmObjectProxy, com_ciot_realm_db_common_AckBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private AckBeanColumnInfo columnInfo;
    private ProxyState<AckBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "AckBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class AckBeanColumnInfo extends ColumnInfo {
        long timeColKey;
        long userColKey;

        AckBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.timeColKey = addColumnDetails(CycleBean.TIME_TYPE, CycleBean.TIME_TYPE, objectSchemaInfo);
            this.userColKey = addColumnDetails("user", "user", objectSchemaInfo);
        }

        AckBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new AckBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            AckBeanColumnInfo ackBeanColumnInfo = (AckBeanColumnInfo) columnInfo;
            AckBeanColumnInfo ackBeanColumnInfo2 = (AckBeanColumnInfo) columnInfo2;
            ackBeanColumnInfo2.timeColKey = ackBeanColumnInfo.timeColKey;
            ackBeanColumnInfo2.userColKey = ackBeanColumnInfo.userColKey;
        }
    }

    com_ciot_realm_db_common_AckBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (AckBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<AckBean> proxyState2 = new ProxyState<>(this);
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

    public String realmGet$user() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.userColKey);
    }

    public void realmSet$user(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.userColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.userColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.userColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.userColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 2, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty(CycleBean.TIME_TYPE, RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("user", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static AckBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new AckBeanColumnInfo(osSchemaInfo);
    }

    public static AckBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        AckBean ackBean = (AckBean) realm.createObjectInternal(AckBean.class, true, Collections.emptyList());
        com_ciot_realm_db_common_AckBeanRealmProxyInterface com_ciot_realm_db_common_ackbeanrealmproxyinterface = ackBean;
        if (jSONObject.has(CycleBean.TIME_TYPE)) {
            if (!jSONObject.isNull(CycleBean.TIME_TYPE)) {
                com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmSet$time(jSONObject.getInt(CycleBean.TIME_TYPE));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'time' to null.");
            }
        }
        if (jSONObject.has("user")) {
            if (jSONObject.isNull("user")) {
                com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmSet$user((String) null);
            } else {
                com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmSet$user(jSONObject.getString("user"));
            }
        }
        return ackBean;
    }

    public static AckBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        AckBean ackBean = new AckBean();
        com_ciot_realm_db_common_AckBeanRealmProxyInterface com_ciot_realm_db_common_ackbeanrealmproxyinterface = ackBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(CycleBean.TIME_TYPE)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmSet$time(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'time' to null.");
                }
            } else if (!nextName.equals("user")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmSet$user(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmSet$user((String) null);
            }
        }
        jsonReader.endObject();
        return (AckBean) realm.copyToRealm(ackBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_common_AckBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) AckBean.class), false, Collections.emptyList());
        com_ciot_realm_db_common_AckBeanRealmProxy com_ciot_realm_db_common_ackbeanrealmproxy = new com_ciot_realm_db_common_AckBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_common_ackbeanrealmproxy;
    }

    public static AckBean copyOrUpdate(Realm realm, AckBeanColumnInfo ackBeanColumnInfo, AckBean ackBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((ackBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(ackBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) ackBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return ackBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(ackBean);
        if (realmObjectProxy2 != null) {
            return (AckBean) realmObjectProxy2;
        }
        return copy(realm, ackBeanColumnInfo, ackBean, z, map, set);
    }

    public static AckBean copy(Realm realm, AckBeanColumnInfo ackBeanColumnInfo, AckBean ackBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(ackBean);
        if (realmObjectProxy != null) {
            return (AckBean) realmObjectProxy;
        }
        com_ciot_realm_db_common_AckBeanRealmProxyInterface com_ciot_realm_db_common_ackbeanrealmproxyinterface = ackBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(AckBean.class), set);
        osObjectBuilder.addInteger(ackBeanColumnInfo.timeColKey, Integer.valueOf(com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmGet$time()));
        osObjectBuilder.addString(ackBeanColumnInfo.userColKey, com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmGet$user());
        com_ciot_realm_db_common_AckBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(ackBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, AckBean ackBean, Map<RealmModel, Long> map) {
        AckBean ackBean2 = ackBean;
        if ((ackBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(ackBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) ackBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(AckBean.class);
        long nativePtr = table.getNativePtr();
        AckBeanColumnInfo ackBeanColumnInfo = (AckBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AckBean.class);
        long createRow = OsObject.createRow(table);
        map.put(ackBean2, Long.valueOf(createRow));
        com_ciot_realm_db_common_AckBeanRealmProxyInterface com_ciot_realm_db_common_ackbeanrealmproxyinterface = ackBean2;
        Table.nativeSetLong(nativePtr, ackBeanColumnInfo.timeColKey, createRow, (long) com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmGet$time(), false);
        String realmGet$user = com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmGet$user();
        if (realmGet$user != null) {
            Table.nativeSetString(nativePtr, ackBeanColumnInfo.userColKey, createRow, realmGet$user, false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(AckBean.class);
        long nativePtr = table.getNativePtr();
        AckBeanColumnInfo ackBeanColumnInfo = (AckBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AckBean.class);
        while (it.hasNext()) {
            AckBean ackBean = (AckBean) it.next();
            if (!map2.containsKey(ackBean)) {
                if ((ackBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(ackBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) ackBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(ackBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(ackBean, Long.valueOf(createRow));
                com_ciot_realm_db_common_AckBeanRealmProxyInterface com_ciot_realm_db_common_ackbeanrealmproxyinterface = ackBean;
                Table.nativeSetLong(nativePtr, ackBeanColumnInfo.timeColKey, createRow, (long) com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmGet$time(), false);
                String realmGet$user = com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmGet$user();
                if (realmGet$user != null) {
                    Table.nativeSetString(nativePtr, ackBeanColumnInfo.userColKey, createRow, realmGet$user, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, AckBean ackBean, Map<RealmModel, Long> map) {
        AckBean ackBean2 = ackBean;
        if ((ackBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(ackBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) ackBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(AckBean.class);
        long nativePtr = table.getNativePtr();
        AckBeanColumnInfo ackBeanColumnInfo = (AckBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AckBean.class);
        long createRow = OsObject.createRow(table);
        map.put(ackBean2, Long.valueOf(createRow));
        com_ciot_realm_db_common_AckBeanRealmProxyInterface com_ciot_realm_db_common_ackbeanrealmproxyinterface = ackBean2;
        Table.nativeSetLong(nativePtr, ackBeanColumnInfo.timeColKey, createRow, (long) com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmGet$time(), false);
        String realmGet$user = com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmGet$user();
        if (realmGet$user != null) {
            Table.nativeSetString(nativePtr, ackBeanColumnInfo.userColKey, createRow, realmGet$user, false);
        } else {
            Table.nativeSetNull(nativePtr, ackBeanColumnInfo.userColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(AckBean.class);
        long nativePtr = table.getNativePtr();
        AckBeanColumnInfo ackBeanColumnInfo = (AckBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AckBean.class);
        while (it.hasNext()) {
            AckBean ackBean = (AckBean) it.next();
            if (!map2.containsKey(ackBean)) {
                if ((ackBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(ackBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) ackBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(ackBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(ackBean, Long.valueOf(createRow));
                com_ciot_realm_db_common_AckBeanRealmProxyInterface com_ciot_realm_db_common_ackbeanrealmproxyinterface = ackBean;
                Table.nativeSetLong(nativePtr, ackBeanColumnInfo.timeColKey, createRow, (long) com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmGet$time(), false);
                String realmGet$user = com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmGet$user();
                if (realmGet$user != null) {
                    Table.nativeSetString(nativePtr, ackBeanColumnInfo.userColKey, createRow, realmGet$user, false);
                } else {
                    Table.nativeSetNull(nativePtr, ackBeanColumnInfo.userColKey, createRow, false);
                }
            }
        }
    }

    public static AckBean createDetachedCopy(AckBean ackBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        AckBean ackBean2;
        if (i > i2 || ackBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(ackBean);
        if (cacheData == null) {
            ackBean2 = new AckBean();
            map.put(ackBean, new RealmObjectProxy.CacheData(i, ackBean2));
        } else if (i >= cacheData.minDepth) {
            return (AckBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            ackBean2 = (AckBean) cacheData.object;
        }
        com_ciot_realm_db_common_AckBeanRealmProxyInterface com_ciot_realm_db_common_ackbeanrealmproxyinterface = ackBean2;
        com_ciot_realm_db_common_AckBeanRealmProxyInterface com_ciot_realm_db_common_ackbeanrealmproxyinterface2 = ackBean;
        com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmSet$time(com_ciot_realm_db_common_ackbeanrealmproxyinterface2.realmGet$time());
        com_ciot_realm_db_common_ackbeanrealmproxyinterface.realmSet$user(com_ciot_realm_db_common_ackbeanrealmproxyinterface2.realmGet$user());
        return ackBean2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("AckBean = proxy[");
        sb.append("{time:");
        sb.append(realmGet$time());
        sb.append("}");
        sb.append(",");
        sb.append("{user:");
        sb.append(realmGet$user() != null ? realmGet$user() : Configurator.NULL);
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
        com_ciot_realm_db_common_AckBeanRealmProxy com_ciot_realm_db_common_ackbeanrealmproxy = (com_ciot_realm_db_common_AckBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_common_ackbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_common_ackbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_common_ackbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
