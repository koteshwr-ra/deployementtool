package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.RobotBean;
import com.limpoxe.support.servicemanager.ServiceProvider;
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

public class com_ciot_realm_db_RobotBeanRealmProxy extends RobotBean implements RealmObjectProxy, com_ciot_realm_db_RobotBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private RobotBeanColumnInfo columnInfo;
    private ProxyState<RobotBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "RobotBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class RobotBeanColumnInfo extends ColumnInfo {
        long idColKey;
        long nameColKey;

        RobotBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
        }

        RobotBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new RobotBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            RobotBeanColumnInfo robotBeanColumnInfo = (RobotBeanColumnInfo) columnInfo;
            RobotBeanColumnInfo robotBeanColumnInfo2 = (RobotBeanColumnInfo) columnInfo2;
            robotBeanColumnInfo2.idColKey = robotBeanColumnInfo.idColKey;
            robotBeanColumnInfo2.nameColKey = robotBeanColumnInfo.nameColKey;
        }
    }

    com_ciot_realm_db_RobotBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (RobotBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<RobotBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$id() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.idColKey);
    }

    public void realmSet$id(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.idColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.idColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.idColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.idColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$name() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.nameColKey);
    }

    public void realmSet$name(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.nameColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.nameColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.nameColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.nameColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 2, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("id", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static RobotBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new RobotBeanColumnInfo(osSchemaInfo);
    }

    public static RobotBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        RobotBean robotBean = (RobotBean) realm.createObjectInternal(RobotBean.class, true, Collections.emptyList());
        com_ciot_realm_db_RobotBeanRealmProxyInterface com_ciot_realm_db_robotbeanrealmproxyinterface = robotBean;
        if (jSONObject.has("id")) {
            if (jSONObject.isNull("id")) {
                com_ciot_realm_db_robotbeanrealmproxyinterface.realmSet$id((String) null);
            } else {
                com_ciot_realm_db_robotbeanrealmproxyinterface.realmSet$id(jSONObject.getString("id"));
            }
        }
        if (jSONObject.has(ServiceProvider.NAME)) {
            if (jSONObject.isNull(ServiceProvider.NAME)) {
                com_ciot_realm_db_robotbeanrealmproxyinterface.realmSet$name((String) null);
            } else {
                com_ciot_realm_db_robotbeanrealmproxyinterface.realmSet$name(jSONObject.getString(ServiceProvider.NAME));
            }
        }
        return robotBean;
    }

    public static RobotBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        RobotBean robotBean = new RobotBean();
        com_ciot_realm_db_RobotBeanRealmProxyInterface com_ciot_realm_db_robotbeanrealmproxyinterface = robotBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_robotbeanrealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_robotbeanrealmproxyinterface.realmSet$id((String) null);
                }
            } else if (!nextName.equals(ServiceProvider.NAME)) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_robotbeanrealmproxyinterface.realmSet$name(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_robotbeanrealmproxyinterface.realmSet$name((String) null);
            }
        }
        jsonReader.endObject();
        return (RobotBean) realm.copyToRealm(robotBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_RobotBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) RobotBean.class), false, Collections.emptyList());
        com_ciot_realm_db_RobotBeanRealmProxy com_ciot_realm_db_robotbeanrealmproxy = new com_ciot_realm_db_RobotBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_robotbeanrealmproxy;
    }

    public static RobotBean copyOrUpdate(Realm realm, RobotBeanColumnInfo robotBeanColumnInfo, RobotBean robotBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((robotBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(robotBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) robotBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return robotBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(robotBean);
        if (realmObjectProxy2 != null) {
            return (RobotBean) realmObjectProxy2;
        }
        return copy(realm, robotBeanColumnInfo, robotBean, z, map, set);
    }

    public static RobotBean copy(Realm realm, RobotBeanColumnInfo robotBeanColumnInfo, RobotBean robotBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(robotBean);
        if (realmObjectProxy != null) {
            return (RobotBean) realmObjectProxy;
        }
        com_ciot_realm_db_RobotBeanRealmProxyInterface com_ciot_realm_db_robotbeanrealmproxyinterface = robotBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(RobotBean.class), set);
        osObjectBuilder.addString(robotBeanColumnInfo.idColKey, com_ciot_realm_db_robotbeanrealmproxyinterface.realmGet$id());
        osObjectBuilder.addString(robotBeanColumnInfo.nameColKey, com_ciot_realm_db_robotbeanrealmproxyinterface.realmGet$name());
        com_ciot_realm_db_RobotBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(robotBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, RobotBean robotBean, Map<RealmModel, Long> map) {
        if ((robotBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(robotBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) robotBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(RobotBean.class);
        long nativePtr = table.getNativePtr();
        RobotBeanColumnInfo robotBeanColumnInfo = (RobotBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RobotBean.class);
        long createRow = OsObject.createRow(table);
        map.put(robotBean, Long.valueOf(createRow));
        com_ciot_realm_db_RobotBeanRealmProxyInterface com_ciot_realm_db_robotbeanrealmproxyinterface = robotBean;
        String realmGet$id = com_ciot_realm_db_robotbeanrealmproxyinterface.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(nativePtr, robotBeanColumnInfo.idColKey, createRow, realmGet$id, false);
        }
        String realmGet$name = com_ciot_realm_db_robotbeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, robotBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(RobotBean.class);
        long nativePtr = table.getNativePtr();
        RobotBeanColumnInfo robotBeanColumnInfo = (RobotBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RobotBean.class);
        while (it.hasNext()) {
            RobotBean robotBean = (RobotBean) it.next();
            if (!map2.containsKey(robotBean)) {
                if ((robotBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(robotBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) robotBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(robotBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(robotBean, Long.valueOf(createRow));
                com_ciot_realm_db_RobotBeanRealmProxyInterface com_ciot_realm_db_robotbeanrealmproxyinterface = robotBean;
                String realmGet$id = com_ciot_realm_db_robotbeanrealmproxyinterface.realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(nativePtr, robotBeanColumnInfo.idColKey, createRow, realmGet$id, false);
                }
                String realmGet$name = com_ciot_realm_db_robotbeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, robotBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, RobotBean robotBean, Map<RealmModel, Long> map) {
        if ((robotBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(robotBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) robotBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(RobotBean.class);
        long nativePtr = table.getNativePtr();
        RobotBeanColumnInfo robotBeanColumnInfo = (RobotBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RobotBean.class);
        long createRow = OsObject.createRow(table);
        map.put(robotBean, Long.valueOf(createRow));
        com_ciot_realm_db_RobotBeanRealmProxyInterface com_ciot_realm_db_robotbeanrealmproxyinterface = robotBean;
        String realmGet$id = com_ciot_realm_db_robotbeanrealmproxyinterface.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(nativePtr, robotBeanColumnInfo.idColKey, createRow, realmGet$id, false);
        } else {
            Table.nativeSetNull(nativePtr, robotBeanColumnInfo.idColKey, createRow, false);
        }
        String realmGet$name = com_ciot_realm_db_robotbeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, robotBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
        } else {
            Table.nativeSetNull(nativePtr, robotBeanColumnInfo.nameColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(RobotBean.class);
        long nativePtr = table.getNativePtr();
        RobotBeanColumnInfo robotBeanColumnInfo = (RobotBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RobotBean.class);
        while (it.hasNext()) {
            RobotBean robotBean = (RobotBean) it.next();
            if (!map2.containsKey(robotBean)) {
                if ((robotBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(robotBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) robotBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(robotBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(robotBean, Long.valueOf(createRow));
                com_ciot_realm_db_RobotBeanRealmProxyInterface com_ciot_realm_db_robotbeanrealmproxyinterface = robotBean;
                String realmGet$id = com_ciot_realm_db_robotbeanrealmproxyinterface.realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(nativePtr, robotBeanColumnInfo.idColKey, createRow, realmGet$id, false);
                } else {
                    Table.nativeSetNull(nativePtr, robotBeanColumnInfo.idColKey, createRow, false);
                }
                String realmGet$name = com_ciot_realm_db_robotbeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, robotBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
                } else {
                    Table.nativeSetNull(nativePtr, robotBeanColumnInfo.nameColKey, createRow, false);
                }
            }
        }
    }

    public static RobotBean createDetachedCopy(RobotBean robotBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        RobotBean robotBean2;
        if (i > i2 || robotBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(robotBean);
        if (cacheData == null) {
            robotBean2 = new RobotBean();
            map.put(robotBean, new RealmObjectProxy.CacheData(i, robotBean2));
        } else if (i >= cacheData.minDepth) {
            return (RobotBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            robotBean2 = (RobotBean) cacheData.object;
        }
        com_ciot_realm_db_RobotBeanRealmProxyInterface com_ciot_realm_db_robotbeanrealmproxyinterface = robotBean2;
        com_ciot_realm_db_RobotBeanRealmProxyInterface com_ciot_realm_db_robotbeanrealmproxyinterface2 = robotBean;
        com_ciot_realm_db_robotbeanrealmproxyinterface.realmSet$id(com_ciot_realm_db_robotbeanrealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_robotbeanrealmproxyinterface.realmSet$name(com_ciot_realm_db_robotbeanrealmproxyinterface2.realmGet$name());
        return robotBean2;
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
        com_ciot_realm_db_RobotBeanRealmProxy com_ciot_realm_db_robotbeanrealmproxy = (com_ciot_realm_db_RobotBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_robotbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_robotbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_robotbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
