package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.ad.EndBean;
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

public class com_ciot_realm_db_ad_EndBeanRealmProxy extends EndBean implements RealmObjectProxy, com_ciot_realm_db_ad_EndBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private EndBeanColumnInfo columnInfo;
    private ProxyState<EndBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "EndBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class EndBeanColumnInfo extends ColumnInfo {
        long hourColKey;
        long minuteColKey;
        long minutesColKey;

        EndBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.hourColKey = addColumnDetails("hour", "hour", objectSchemaInfo);
            this.minuteColKey = addColumnDetails("minute", "minute", objectSchemaInfo);
            this.minutesColKey = addColumnDetails("minutes", "minutes", objectSchemaInfo);
        }

        EndBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new EndBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            EndBeanColumnInfo endBeanColumnInfo = (EndBeanColumnInfo) columnInfo;
            EndBeanColumnInfo endBeanColumnInfo2 = (EndBeanColumnInfo) columnInfo2;
            endBeanColumnInfo2.hourColKey = endBeanColumnInfo.hourColKey;
            endBeanColumnInfo2.minuteColKey = endBeanColumnInfo.minuteColKey;
            endBeanColumnInfo2.minutesColKey = endBeanColumnInfo.minutesColKey;
        }
    }

    com_ciot_realm_db_ad_EndBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (EndBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<EndBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$hour() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.hourColKey);
    }

    public void realmSet$hour(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.hourColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.hourColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.hourColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.hourColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$minute() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.minuteColKey);
    }

    public void realmSet$minute(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.minuteColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.minuteColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.minuteColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.minuteColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public long realmGet$minutes() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.minutesColKey);
    }

    public void realmSet$minutes(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.minutesColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.minutesColKey, row$realm.getObjectKey(), j, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 3, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("hour", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("minute", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("minutes", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static EndBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new EndBeanColumnInfo(osSchemaInfo);
    }

    public static EndBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        EndBean endBean = (EndBean) realm.createObjectInternal(EndBean.class, true, Collections.emptyList());
        com_ciot_realm_db_ad_EndBeanRealmProxyInterface com_ciot_realm_db_ad_endbeanrealmproxyinterface = endBean;
        if (jSONObject.has("hour")) {
            if (jSONObject.isNull("hour")) {
                com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmSet$hour((String) null);
            } else {
                com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmSet$hour(jSONObject.getString("hour"));
            }
        }
        if (jSONObject.has("minute")) {
            if (jSONObject.isNull("minute")) {
                com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmSet$minute((String) null);
            } else {
                com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmSet$minute(jSONObject.getString("minute"));
            }
        }
        if (jSONObject.has("minutes")) {
            if (!jSONObject.isNull("minutes")) {
                com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmSet$minutes(jSONObject.getLong("minutes"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'minutes' to null.");
            }
        }
        return endBean;
    }

    public static EndBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        EndBean endBean = new EndBean();
        com_ciot_realm_db_ad_EndBeanRealmProxyInterface com_ciot_realm_db_ad_endbeanrealmproxyinterface = endBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("hour")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmSet$hour(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmSet$hour((String) null);
                }
            } else if (nextName.equals("minute")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmSet$minute(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmSet$minute((String) null);
                }
            } else if (!nextName.equals("minutes")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmSet$minutes(jsonReader.nextLong());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'minutes' to null.");
            }
        }
        jsonReader.endObject();
        return (EndBean) realm.copyToRealm(endBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_ad_EndBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) EndBean.class), false, Collections.emptyList());
        com_ciot_realm_db_ad_EndBeanRealmProxy com_ciot_realm_db_ad_endbeanrealmproxy = new com_ciot_realm_db_ad_EndBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_ad_endbeanrealmproxy;
    }

    public static EndBean copyOrUpdate(Realm realm, EndBeanColumnInfo endBeanColumnInfo, EndBean endBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((endBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(endBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) endBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return endBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(endBean);
        if (realmObjectProxy2 != null) {
            return (EndBean) realmObjectProxy2;
        }
        return copy(realm, endBeanColumnInfo, endBean, z, map, set);
    }

    public static EndBean copy(Realm realm, EndBeanColumnInfo endBeanColumnInfo, EndBean endBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(endBean);
        if (realmObjectProxy != null) {
            return (EndBean) realmObjectProxy;
        }
        com_ciot_realm_db_ad_EndBeanRealmProxyInterface com_ciot_realm_db_ad_endbeanrealmproxyinterface = endBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(EndBean.class), set);
        osObjectBuilder.addString(endBeanColumnInfo.hourColKey, com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmGet$hour());
        osObjectBuilder.addString(endBeanColumnInfo.minuteColKey, com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmGet$minute());
        osObjectBuilder.addInteger(endBeanColumnInfo.minutesColKey, Long.valueOf(com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmGet$minutes()));
        com_ciot_realm_db_ad_EndBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(endBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, EndBean endBean, Map<RealmModel, Long> map) {
        if ((endBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(endBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) endBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(EndBean.class);
        long nativePtr = table.getNativePtr();
        EndBeanColumnInfo endBeanColumnInfo = (EndBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EndBean.class);
        long createRow = OsObject.createRow(table);
        map.put(endBean, Long.valueOf(createRow));
        com_ciot_realm_db_ad_EndBeanRealmProxyInterface com_ciot_realm_db_ad_endbeanrealmproxyinterface = endBean;
        String realmGet$hour = com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmGet$hour();
        if (realmGet$hour != null) {
            Table.nativeSetString(nativePtr, endBeanColumnInfo.hourColKey, createRow, realmGet$hour, false);
        }
        String realmGet$minute = com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmGet$minute();
        if (realmGet$minute != null) {
            Table.nativeSetString(nativePtr, endBeanColumnInfo.minuteColKey, createRow, realmGet$minute, false);
        }
        Table.nativeSetLong(nativePtr, endBeanColumnInfo.minutesColKey, createRow, com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmGet$minutes(), false);
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(EndBean.class);
        long nativePtr = table.getNativePtr();
        EndBeanColumnInfo endBeanColumnInfo = (EndBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EndBean.class);
        while (it.hasNext()) {
            EndBean endBean = (EndBean) it.next();
            if (!map2.containsKey(endBean)) {
                if ((endBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(endBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) endBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(endBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(endBean, Long.valueOf(createRow));
                com_ciot_realm_db_ad_EndBeanRealmProxyInterface com_ciot_realm_db_ad_endbeanrealmproxyinterface = endBean;
                String realmGet$hour = com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmGet$hour();
                if (realmGet$hour != null) {
                    Table.nativeSetString(nativePtr, endBeanColumnInfo.hourColKey, createRow, realmGet$hour, false);
                }
                String realmGet$minute = com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmGet$minute();
                if (realmGet$minute != null) {
                    Table.nativeSetString(nativePtr, endBeanColumnInfo.minuteColKey, createRow, realmGet$minute, false);
                }
                Table.nativeSetLong(nativePtr, endBeanColumnInfo.minutesColKey, createRow, com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmGet$minutes(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, EndBean endBean, Map<RealmModel, Long> map) {
        if ((endBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(endBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) endBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(EndBean.class);
        long nativePtr = table.getNativePtr();
        EndBeanColumnInfo endBeanColumnInfo = (EndBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EndBean.class);
        long createRow = OsObject.createRow(table);
        map.put(endBean, Long.valueOf(createRow));
        com_ciot_realm_db_ad_EndBeanRealmProxyInterface com_ciot_realm_db_ad_endbeanrealmproxyinterface = endBean;
        String realmGet$hour = com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmGet$hour();
        if (realmGet$hour != null) {
            Table.nativeSetString(nativePtr, endBeanColumnInfo.hourColKey, createRow, realmGet$hour, false);
        } else {
            Table.nativeSetNull(nativePtr, endBeanColumnInfo.hourColKey, createRow, false);
        }
        String realmGet$minute = com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmGet$minute();
        if (realmGet$minute != null) {
            Table.nativeSetString(nativePtr, endBeanColumnInfo.minuteColKey, createRow, realmGet$minute, false);
        } else {
            Table.nativeSetNull(nativePtr, endBeanColumnInfo.minuteColKey, createRow, false);
        }
        Table.nativeSetLong(nativePtr, endBeanColumnInfo.minutesColKey, createRow, com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmGet$minutes(), false);
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(EndBean.class);
        long nativePtr = table.getNativePtr();
        EndBeanColumnInfo endBeanColumnInfo = (EndBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EndBean.class);
        while (it.hasNext()) {
            EndBean endBean = (EndBean) it.next();
            if (!map2.containsKey(endBean)) {
                if ((endBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(endBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) endBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(endBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(endBean, Long.valueOf(createRow));
                com_ciot_realm_db_ad_EndBeanRealmProxyInterface com_ciot_realm_db_ad_endbeanrealmproxyinterface = endBean;
                String realmGet$hour = com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmGet$hour();
                if (realmGet$hour != null) {
                    Table.nativeSetString(nativePtr, endBeanColumnInfo.hourColKey, createRow, realmGet$hour, false);
                } else {
                    Table.nativeSetNull(nativePtr, endBeanColumnInfo.hourColKey, createRow, false);
                }
                String realmGet$minute = com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmGet$minute();
                if (realmGet$minute != null) {
                    Table.nativeSetString(nativePtr, endBeanColumnInfo.minuteColKey, createRow, realmGet$minute, false);
                } else {
                    Table.nativeSetNull(nativePtr, endBeanColumnInfo.minuteColKey, createRow, false);
                }
                Table.nativeSetLong(nativePtr, endBeanColumnInfo.minutesColKey, createRow, com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmGet$minutes(), false);
            }
        }
    }

    public static EndBean createDetachedCopy(EndBean endBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        EndBean endBean2;
        if (i > i2 || endBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(endBean);
        if (cacheData == null) {
            endBean2 = new EndBean();
            map.put(endBean, new RealmObjectProxy.CacheData(i, endBean2));
        } else if (i >= cacheData.minDepth) {
            return (EndBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            endBean2 = (EndBean) cacheData.object;
        }
        com_ciot_realm_db_ad_EndBeanRealmProxyInterface com_ciot_realm_db_ad_endbeanrealmproxyinterface = endBean2;
        com_ciot_realm_db_ad_EndBeanRealmProxyInterface com_ciot_realm_db_ad_endbeanrealmproxyinterface2 = endBean;
        com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmSet$hour(com_ciot_realm_db_ad_endbeanrealmproxyinterface2.realmGet$hour());
        com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmSet$minute(com_ciot_realm_db_ad_endbeanrealmproxyinterface2.realmGet$minute());
        com_ciot_realm_db_ad_endbeanrealmproxyinterface.realmSet$minutes(com_ciot_realm_db_ad_endbeanrealmproxyinterface2.realmGet$minutes());
        return endBean2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("EndBean = proxy[");
        sb.append("{hour:");
        String realmGet$hour = realmGet$hour();
        String str = Configurator.NULL;
        sb.append(realmGet$hour != null ? realmGet$hour() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{minute:");
        if (realmGet$minute() != null) {
            str = realmGet$minute();
        }
        sb.append(str);
        sb.append("}");
        sb.append(",");
        sb.append("{minutes:");
        sb.append(realmGet$minutes());
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
        com_ciot_realm_db_ad_EndBeanRealmProxy com_ciot_realm_db_ad_endbeanrealmproxy = (com_ciot_realm_db_ad_EndBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_ad_endbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_ad_endbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_ad_endbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
