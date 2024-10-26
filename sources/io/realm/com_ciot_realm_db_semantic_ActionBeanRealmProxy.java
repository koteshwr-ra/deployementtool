package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.semantic.ActionBean;
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

public class com_ciot_realm_db_semantic_ActionBeanRealmProxy extends ActionBean implements RealmObjectProxy, com_ciot_realm_db_semantic_ActionBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ActionBeanColumnInfo columnInfo;
    private ProxyState<ActionBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "ActionBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class ActionBeanColumnInfo extends ColumnInfo {
        long actionIdColKey;
        long ttlColKey;

        ActionBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.actionIdColKey = addColumnDetails("actionId", "actionId", objectSchemaInfo);
            this.ttlColKey = addColumnDetails("ttl", "ttl", objectSchemaInfo);
        }

        ActionBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new ActionBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            ActionBeanColumnInfo actionBeanColumnInfo = (ActionBeanColumnInfo) columnInfo;
            ActionBeanColumnInfo actionBeanColumnInfo2 = (ActionBeanColumnInfo) columnInfo2;
            actionBeanColumnInfo2.actionIdColKey = actionBeanColumnInfo.actionIdColKey;
            actionBeanColumnInfo2.ttlColKey = actionBeanColumnInfo.ttlColKey;
        }
    }

    com_ciot_realm_db_semantic_ActionBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ActionBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<ActionBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public int realmGet$actionId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.actionIdColKey);
    }

    public void realmSet$actionId(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.actionIdColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.actionIdColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public String realmGet$ttl() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.ttlColKey);
    }

    public void realmSet$ttl(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.ttlColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.ttlColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.ttlColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.ttlColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 2, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("actionId", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("ttl", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ActionBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new ActionBeanColumnInfo(osSchemaInfo);
    }

    public static ActionBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        ActionBean actionBean = (ActionBean) realm.createObjectInternal(ActionBean.class, true, Collections.emptyList());
        com_ciot_realm_db_semantic_ActionBeanRealmProxyInterface com_ciot_realm_db_semantic_actionbeanrealmproxyinterface = actionBean;
        if (jSONObject.has("actionId")) {
            if (!jSONObject.isNull("actionId")) {
                com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmSet$actionId(jSONObject.getInt("actionId"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'actionId' to null.");
            }
        }
        if (jSONObject.has("ttl")) {
            if (jSONObject.isNull("ttl")) {
                com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmSet$ttl((String) null);
            } else {
                com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmSet$ttl(jSONObject.getString("ttl"));
            }
        }
        return actionBean;
    }

    public static ActionBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        ActionBean actionBean = new ActionBean();
        com_ciot_realm_db_semantic_ActionBeanRealmProxyInterface com_ciot_realm_db_semantic_actionbeanrealmproxyinterface = actionBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("actionId")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmSet$actionId(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'actionId' to null.");
                }
            } else if (!nextName.equals("ttl")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmSet$ttl(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmSet$ttl((String) null);
            }
        }
        jsonReader.endObject();
        return (ActionBean) realm.copyToRealm(actionBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_semantic_ActionBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) ActionBean.class), false, Collections.emptyList());
        com_ciot_realm_db_semantic_ActionBeanRealmProxy com_ciot_realm_db_semantic_actionbeanrealmproxy = new com_ciot_realm_db_semantic_ActionBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_semantic_actionbeanrealmproxy;
    }

    public static ActionBean copyOrUpdate(Realm realm, ActionBeanColumnInfo actionBeanColumnInfo, ActionBean actionBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((actionBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(actionBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) actionBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return actionBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(actionBean);
        if (realmObjectProxy2 != null) {
            return (ActionBean) realmObjectProxy2;
        }
        return copy(realm, actionBeanColumnInfo, actionBean, z, map, set);
    }

    public static ActionBean copy(Realm realm, ActionBeanColumnInfo actionBeanColumnInfo, ActionBean actionBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(actionBean);
        if (realmObjectProxy != null) {
            return (ActionBean) realmObjectProxy;
        }
        com_ciot_realm_db_semantic_ActionBeanRealmProxyInterface com_ciot_realm_db_semantic_actionbeanrealmproxyinterface = actionBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(ActionBean.class), set);
        osObjectBuilder.addInteger(actionBeanColumnInfo.actionIdColKey, Integer.valueOf(com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmGet$actionId()));
        osObjectBuilder.addString(actionBeanColumnInfo.ttlColKey, com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmGet$ttl());
        com_ciot_realm_db_semantic_ActionBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(actionBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, ActionBean actionBean, Map<RealmModel, Long> map) {
        ActionBean actionBean2 = actionBean;
        if ((actionBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(actionBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) actionBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(ActionBean.class);
        long nativePtr = table.getNativePtr();
        ActionBeanColumnInfo actionBeanColumnInfo = (ActionBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ActionBean.class);
        long createRow = OsObject.createRow(table);
        map.put(actionBean2, Long.valueOf(createRow));
        com_ciot_realm_db_semantic_ActionBeanRealmProxyInterface com_ciot_realm_db_semantic_actionbeanrealmproxyinterface = actionBean2;
        Table.nativeSetLong(nativePtr, actionBeanColumnInfo.actionIdColKey, createRow, (long) com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmGet$actionId(), false);
        String realmGet$ttl = com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmGet$ttl();
        if (realmGet$ttl != null) {
            Table.nativeSetString(nativePtr, actionBeanColumnInfo.ttlColKey, createRow, realmGet$ttl, false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(ActionBean.class);
        long nativePtr = table.getNativePtr();
        ActionBeanColumnInfo actionBeanColumnInfo = (ActionBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ActionBean.class);
        while (it.hasNext()) {
            ActionBean actionBean = (ActionBean) it.next();
            if (!map2.containsKey(actionBean)) {
                if ((actionBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(actionBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) actionBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(actionBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(actionBean, Long.valueOf(createRow));
                com_ciot_realm_db_semantic_ActionBeanRealmProxyInterface com_ciot_realm_db_semantic_actionbeanrealmproxyinterface = actionBean;
                Table.nativeSetLong(nativePtr, actionBeanColumnInfo.actionIdColKey, createRow, (long) com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmGet$actionId(), false);
                String realmGet$ttl = com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmGet$ttl();
                if (realmGet$ttl != null) {
                    Table.nativeSetString(nativePtr, actionBeanColumnInfo.ttlColKey, createRow, realmGet$ttl, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, ActionBean actionBean, Map<RealmModel, Long> map) {
        ActionBean actionBean2 = actionBean;
        if ((actionBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(actionBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) actionBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(ActionBean.class);
        long nativePtr = table.getNativePtr();
        ActionBeanColumnInfo actionBeanColumnInfo = (ActionBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ActionBean.class);
        long createRow = OsObject.createRow(table);
        map.put(actionBean2, Long.valueOf(createRow));
        com_ciot_realm_db_semantic_ActionBeanRealmProxyInterface com_ciot_realm_db_semantic_actionbeanrealmproxyinterface = actionBean2;
        Table.nativeSetLong(nativePtr, actionBeanColumnInfo.actionIdColKey, createRow, (long) com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmGet$actionId(), false);
        String realmGet$ttl = com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmGet$ttl();
        if (realmGet$ttl != null) {
            Table.nativeSetString(nativePtr, actionBeanColumnInfo.ttlColKey, createRow, realmGet$ttl, false);
        } else {
            Table.nativeSetNull(nativePtr, actionBeanColumnInfo.ttlColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(ActionBean.class);
        long nativePtr = table.getNativePtr();
        ActionBeanColumnInfo actionBeanColumnInfo = (ActionBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ActionBean.class);
        while (it.hasNext()) {
            ActionBean actionBean = (ActionBean) it.next();
            if (!map2.containsKey(actionBean)) {
                if ((actionBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(actionBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) actionBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(actionBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(actionBean, Long.valueOf(createRow));
                com_ciot_realm_db_semantic_ActionBeanRealmProxyInterface com_ciot_realm_db_semantic_actionbeanrealmproxyinterface = actionBean;
                Table.nativeSetLong(nativePtr, actionBeanColumnInfo.actionIdColKey, createRow, (long) com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmGet$actionId(), false);
                String realmGet$ttl = com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmGet$ttl();
                if (realmGet$ttl != null) {
                    Table.nativeSetString(nativePtr, actionBeanColumnInfo.ttlColKey, createRow, realmGet$ttl, false);
                } else {
                    Table.nativeSetNull(nativePtr, actionBeanColumnInfo.ttlColKey, createRow, false);
                }
            }
        }
    }

    public static ActionBean createDetachedCopy(ActionBean actionBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        ActionBean actionBean2;
        if (i > i2 || actionBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(actionBean);
        if (cacheData == null) {
            actionBean2 = new ActionBean();
            map.put(actionBean, new RealmObjectProxy.CacheData(i, actionBean2));
        } else if (i >= cacheData.minDepth) {
            return (ActionBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            actionBean2 = (ActionBean) cacheData.object;
        }
        com_ciot_realm_db_semantic_ActionBeanRealmProxyInterface com_ciot_realm_db_semantic_actionbeanrealmproxyinterface = actionBean2;
        com_ciot_realm_db_semantic_ActionBeanRealmProxyInterface com_ciot_realm_db_semantic_actionbeanrealmproxyinterface2 = actionBean;
        com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmSet$actionId(com_ciot_realm_db_semantic_actionbeanrealmproxyinterface2.realmGet$actionId());
        com_ciot_realm_db_semantic_actionbeanrealmproxyinterface.realmSet$ttl(com_ciot_realm_db_semantic_actionbeanrealmproxyinterface2.realmGet$ttl());
        return actionBean2;
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
        com_ciot_realm_db_semantic_ActionBeanRealmProxy com_ciot_realm_db_semantic_actionbeanrealmproxy = (com_ciot_realm_db_semantic_ActionBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_semantic_actionbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_semantic_actionbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_semantic_actionbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
