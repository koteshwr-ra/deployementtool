package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.TimeBean;
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

public class com_ciot_realm_db_TimeBeanRealmProxy extends TimeBean implements RealmObjectProxy, com_ciot_realm_db_TimeBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private TimeBeanColumnInfo columnInfo;
    private ProxyState<TimeBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "TimeBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class TimeBeanColumnInfo extends ColumnInfo {
        long beginTimeColKey;
        long endTimeColKey;

        TimeBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.beginTimeColKey = addColumnDetails("beginTime", "beginTime", objectSchemaInfo);
            this.endTimeColKey = addColumnDetails("endTime", "endTime", objectSchemaInfo);
        }

        TimeBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new TimeBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            TimeBeanColumnInfo timeBeanColumnInfo = (TimeBeanColumnInfo) columnInfo;
            TimeBeanColumnInfo timeBeanColumnInfo2 = (TimeBeanColumnInfo) columnInfo2;
            timeBeanColumnInfo2.beginTimeColKey = timeBeanColumnInfo.beginTimeColKey;
            timeBeanColumnInfo2.endTimeColKey = timeBeanColumnInfo.endTimeColKey;
        }
    }

    com_ciot_realm_db_TimeBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (TimeBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<TimeBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$beginTime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.beginTimeColKey);
    }

    public void realmSet$beginTime(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.beginTimeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.beginTimeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.beginTimeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.beginTimeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$endTime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.endTimeColKey);
    }

    public void realmSet$endTime(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.endTimeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.endTimeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.endTimeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.endTimeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 2, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("beginTime", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("endTime", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TimeBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new TimeBeanColumnInfo(osSchemaInfo);
    }

    public static TimeBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        TimeBean timeBean = (TimeBean) realm.createObjectInternal(TimeBean.class, true, Collections.emptyList());
        com_ciot_realm_db_TimeBeanRealmProxyInterface com_ciot_realm_db_timebeanrealmproxyinterface = timeBean;
        if (jSONObject.has("beginTime")) {
            if (jSONObject.isNull("beginTime")) {
                com_ciot_realm_db_timebeanrealmproxyinterface.realmSet$beginTime((String) null);
            } else {
                com_ciot_realm_db_timebeanrealmproxyinterface.realmSet$beginTime(jSONObject.getString("beginTime"));
            }
        }
        if (jSONObject.has("endTime")) {
            if (jSONObject.isNull("endTime")) {
                com_ciot_realm_db_timebeanrealmproxyinterface.realmSet$endTime((String) null);
            } else {
                com_ciot_realm_db_timebeanrealmproxyinterface.realmSet$endTime(jSONObject.getString("endTime"));
            }
        }
        return timeBean;
    }

    public static TimeBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        TimeBean timeBean = new TimeBean();
        com_ciot_realm_db_TimeBeanRealmProxyInterface com_ciot_realm_db_timebeanrealmproxyinterface = timeBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("beginTime")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_timebeanrealmproxyinterface.realmSet$beginTime(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_timebeanrealmproxyinterface.realmSet$beginTime((String) null);
                }
            } else if (!nextName.equals("endTime")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_timebeanrealmproxyinterface.realmSet$endTime(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_timebeanrealmproxyinterface.realmSet$endTime((String) null);
            }
        }
        jsonReader.endObject();
        return (TimeBean) realm.copyToRealm(timeBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_TimeBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimeBean.class), false, Collections.emptyList());
        com_ciot_realm_db_TimeBeanRealmProxy com_ciot_realm_db_timebeanrealmproxy = new com_ciot_realm_db_TimeBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_timebeanrealmproxy;
    }

    public static TimeBean copyOrUpdate(Realm realm, TimeBeanColumnInfo timeBeanColumnInfo, TimeBean timeBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((timeBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(timeBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) timeBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return timeBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(timeBean);
        if (realmObjectProxy2 != null) {
            return (TimeBean) realmObjectProxy2;
        }
        return copy(realm, timeBeanColumnInfo, timeBean, z, map, set);
    }

    public static TimeBean copy(Realm realm, TimeBeanColumnInfo timeBeanColumnInfo, TimeBean timeBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(timeBean);
        if (realmObjectProxy != null) {
            return (TimeBean) realmObjectProxy;
        }
        com_ciot_realm_db_TimeBeanRealmProxyInterface com_ciot_realm_db_timebeanrealmproxyinterface = timeBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(TimeBean.class), set);
        osObjectBuilder.addString(timeBeanColumnInfo.beginTimeColKey, com_ciot_realm_db_timebeanrealmproxyinterface.realmGet$beginTime());
        osObjectBuilder.addString(timeBeanColumnInfo.endTimeColKey, com_ciot_realm_db_timebeanrealmproxyinterface.realmGet$endTime());
        com_ciot_realm_db_TimeBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(timeBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, TimeBean timeBean, Map<RealmModel, Long> map) {
        if ((timeBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(timeBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) timeBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(TimeBean.class);
        long nativePtr = table.getNativePtr();
        TimeBeanColumnInfo timeBeanColumnInfo = (TimeBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimeBean.class);
        long createRow = OsObject.createRow(table);
        map.put(timeBean, Long.valueOf(createRow));
        com_ciot_realm_db_TimeBeanRealmProxyInterface com_ciot_realm_db_timebeanrealmproxyinterface = timeBean;
        String realmGet$beginTime = com_ciot_realm_db_timebeanrealmproxyinterface.realmGet$beginTime();
        if (realmGet$beginTime != null) {
            Table.nativeSetString(nativePtr, timeBeanColumnInfo.beginTimeColKey, createRow, realmGet$beginTime, false);
        }
        String realmGet$endTime = com_ciot_realm_db_timebeanrealmproxyinterface.realmGet$endTime();
        if (realmGet$endTime != null) {
            Table.nativeSetString(nativePtr, timeBeanColumnInfo.endTimeColKey, createRow, realmGet$endTime, false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(TimeBean.class);
        long nativePtr = table.getNativePtr();
        TimeBeanColumnInfo timeBeanColumnInfo = (TimeBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimeBean.class);
        while (it.hasNext()) {
            TimeBean timeBean = (TimeBean) it.next();
            if (!map2.containsKey(timeBean)) {
                if ((timeBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(timeBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) timeBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(timeBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(timeBean, Long.valueOf(createRow));
                com_ciot_realm_db_TimeBeanRealmProxyInterface com_ciot_realm_db_timebeanrealmproxyinterface = timeBean;
                String realmGet$beginTime = com_ciot_realm_db_timebeanrealmproxyinterface.realmGet$beginTime();
                if (realmGet$beginTime != null) {
                    Table.nativeSetString(nativePtr, timeBeanColumnInfo.beginTimeColKey, createRow, realmGet$beginTime, false);
                }
                String realmGet$endTime = com_ciot_realm_db_timebeanrealmproxyinterface.realmGet$endTime();
                if (realmGet$endTime != null) {
                    Table.nativeSetString(nativePtr, timeBeanColumnInfo.endTimeColKey, createRow, realmGet$endTime, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, TimeBean timeBean, Map<RealmModel, Long> map) {
        if ((timeBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(timeBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) timeBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(TimeBean.class);
        long nativePtr = table.getNativePtr();
        TimeBeanColumnInfo timeBeanColumnInfo = (TimeBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimeBean.class);
        long createRow = OsObject.createRow(table);
        map.put(timeBean, Long.valueOf(createRow));
        com_ciot_realm_db_TimeBeanRealmProxyInterface com_ciot_realm_db_timebeanrealmproxyinterface = timeBean;
        String realmGet$beginTime = com_ciot_realm_db_timebeanrealmproxyinterface.realmGet$beginTime();
        if (realmGet$beginTime != null) {
            Table.nativeSetString(nativePtr, timeBeanColumnInfo.beginTimeColKey, createRow, realmGet$beginTime, false);
        } else {
            Table.nativeSetNull(nativePtr, timeBeanColumnInfo.beginTimeColKey, createRow, false);
        }
        String realmGet$endTime = com_ciot_realm_db_timebeanrealmproxyinterface.realmGet$endTime();
        if (realmGet$endTime != null) {
            Table.nativeSetString(nativePtr, timeBeanColumnInfo.endTimeColKey, createRow, realmGet$endTime, false);
        } else {
            Table.nativeSetNull(nativePtr, timeBeanColumnInfo.endTimeColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(TimeBean.class);
        long nativePtr = table.getNativePtr();
        TimeBeanColumnInfo timeBeanColumnInfo = (TimeBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimeBean.class);
        while (it.hasNext()) {
            TimeBean timeBean = (TimeBean) it.next();
            if (!map2.containsKey(timeBean)) {
                if ((timeBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(timeBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) timeBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(timeBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(timeBean, Long.valueOf(createRow));
                com_ciot_realm_db_TimeBeanRealmProxyInterface com_ciot_realm_db_timebeanrealmproxyinterface = timeBean;
                String realmGet$beginTime = com_ciot_realm_db_timebeanrealmproxyinterface.realmGet$beginTime();
                if (realmGet$beginTime != null) {
                    Table.nativeSetString(nativePtr, timeBeanColumnInfo.beginTimeColKey, createRow, realmGet$beginTime, false);
                } else {
                    Table.nativeSetNull(nativePtr, timeBeanColumnInfo.beginTimeColKey, createRow, false);
                }
                String realmGet$endTime = com_ciot_realm_db_timebeanrealmproxyinterface.realmGet$endTime();
                if (realmGet$endTime != null) {
                    Table.nativeSetString(nativePtr, timeBeanColumnInfo.endTimeColKey, createRow, realmGet$endTime, false);
                } else {
                    Table.nativeSetNull(nativePtr, timeBeanColumnInfo.endTimeColKey, createRow, false);
                }
            }
        }
    }

    public static TimeBean createDetachedCopy(TimeBean timeBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        TimeBean timeBean2;
        if (i > i2 || timeBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(timeBean);
        if (cacheData == null) {
            timeBean2 = new TimeBean();
            map.put(timeBean, new RealmObjectProxy.CacheData(i, timeBean2));
        } else if (i >= cacheData.minDepth) {
            return (TimeBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            timeBean2 = (TimeBean) cacheData.object;
        }
        com_ciot_realm_db_TimeBeanRealmProxyInterface com_ciot_realm_db_timebeanrealmproxyinterface = timeBean2;
        com_ciot_realm_db_TimeBeanRealmProxyInterface com_ciot_realm_db_timebeanrealmproxyinterface2 = timeBean;
        com_ciot_realm_db_timebeanrealmproxyinterface.realmSet$beginTime(com_ciot_realm_db_timebeanrealmproxyinterface2.realmGet$beginTime());
        com_ciot_realm_db_timebeanrealmproxyinterface.realmSet$endTime(com_ciot_realm_db_timebeanrealmproxyinterface2.realmGet$endTime());
        return timeBean2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("TimeBean = proxy[");
        sb.append("{beginTime:");
        String realmGet$beginTime = realmGet$beginTime();
        String str = Configurator.NULL;
        sb.append(realmGet$beginTime != null ? realmGet$beginTime() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{endTime:");
        if (realmGet$endTime() != null) {
            str = realmGet$endTime();
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
        com_ciot_realm_db_TimeBeanRealmProxy com_ciot_realm_db_timebeanrealmproxy = (com_ciot_realm_db_TimeBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_timebeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_timebeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_timebeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
