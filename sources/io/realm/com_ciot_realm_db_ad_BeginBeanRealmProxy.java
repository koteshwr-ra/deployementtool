package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.ad.BeginBean;
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

public class com_ciot_realm_db_ad_BeginBeanRealmProxy extends BeginBean implements RealmObjectProxy, com_ciot_realm_db_ad_BeginBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private BeginBeanColumnInfo columnInfo;
    private ProxyState<BeginBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "BeginBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class BeginBeanColumnInfo extends ColumnInfo {
        long hourColKey;
        long minuteColKey;
        long minutesColKey;

        BeginBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.hourColKey = addColumnDetails("hour", "hour", objectSchemaInfo);
            this.minuteColKey = addColumnDetails("minute", "minute", objectSchemaInfo);
            this.minutesColKey = addColumnDetails("minutes", "minutes", objectSchemaInfo);
        }

        BeginBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new BeginBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            BeginBeanColumnInfo beginBeanColumnInfo = (BeginBeanColumnInfo) columnInfo;
            BeginBeanColumnInfo beginBeanColumnInfo2 = (BeginBeanColumnInfo) columnInfo2;
            beginBeanColumnInfo2.hourColKey = beginBeanColumnInfo.hourColKey;
            beginBeanColumnInfo2.minuteColKey = beginBeanColumnInfo.minuteColKey;
            beginBeanColumnInfo2.minutesColKey = beginBeanColumnInfo.minutesColKey;
        }
    }

    com_ciot_realm_db_ad_BeginBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (BeginBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<BeginBean> proxyState2 = new ProxyState<>(this);
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

    public static BeginBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new BeginBeanColumnInfo(osSchemaInfo);
    }

    public static BeginBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        BeginBean beginBean = (BeginBean) realm.createObjectInternal(BeginBean.class, true, Collections.emptyList());
        com_ciot_realm_db_ad_BeginBeanRealmProxyInterface com_ciot_realm_db_ad_beginbeanrealmproxyinterface = beginBean;
        if (jSONObject.has("hour")) {
            if (jSONObject.isNull("hour")) {
                com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmSet$hour((String) null);
            } else {
                com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmSet$hour(jSONObject.getString("hour"));
            }
        }
        if (jSONObject.has("minute")) {
            if (jSONObject.isNull("minute")) {
                com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmSet$minute((String) null);
            } else {
                com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmSet$minute(jSONObject.getString("minute"));
            }
        }
        if (jSONObject.has("minutes")) {
            if (!jSONObject.isNull("minutes")) {
                com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmSet$minutes(jSONObject.getLong("minutes"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'minutes' to null.");
            }
        }
        return beginBean;
    }

    public static BeginBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        BeginBean beginBean = new BeginBean();
        com_ciot_realm_db_ad_BeginBeanRealmProxyInterface com_ciot_realm_db_ad_beginbeanrealmproxyinterface = beginBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("hour")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmSet$hour(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmSet$hour((String) null);
                }
            } else if (nextName.equals("minute")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmSet$minute(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmSet$minute((String) null);
                }
            } else if (!nextName.equals("minutes")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmSet$minutes(jsonReader.nextLong());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'minutes' to null.");
            }
        }
        jsonReader.endObject();
        return (BeginBean) realm.copyToRealm(beginBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_ad_BeginBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) BeginBean.class), false, Collections.emptyList());
        com_ciot_realm_db_ad_BeginBeanRealmProxy com_ciot_realm_db_ad_beginbeanrealmproxy = new com_ciot_realm_db_ad_BeginBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_ad_beginbeanrealmproxy;
    }

    public static BeginBean copyOrUpdate(Realm realm, BeginBeanColumnInfo beginBeanColumnInfo, BeginBean beginBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((beginBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(beginBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) beginBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return beginBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(beginBean);
        if (realmObjectProxy2 != null) {
            return (BeginBean) realmObjectProxy2;
        }
        return copy(realm, beginBeanColumnInfo, beginBean, z, map, set);
    }

    public static BeginBean copy(Realm realm, BeginBeanColumnInfo beginBeanColumnInfo, BeginBean beginBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(beginBean);
        if (realmObjectProxy != null) {
            return (BeginBean) realmObjectProxy;
        }
        com_ciot_realm_db_ad_BeginBeanRealmProxyInterface com_ciot_realm_db_ad_beginbeanrealmproxyinterface = beginBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(BeginBean.class), set);
        osObjectBuilder.addString(beginBeanColumnInfo.hourColKey, com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmGet$hour());
        osObjectBuilder.addString(beginBeanColumnInfo.minuteColKey, com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmGet$minute());
        osObjectBuilder.addInteger(beginBeanColumnInfo.minutesColKey, Long.valueOf(com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmGet$minutes()));
        com_ciot_realm_db_ad_BeginBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(beginBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, BeginBean beginBean, Map<RealmModel, Long> map) {
        if ((beginBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(beginBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) beginBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(BeginBean.class);
        long nativePtr = table.getNativePtr();
        BeginBeanColumnInfo beginBeanColumnInfo = (BeginBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) BeginBean.class);
        long createRow = OsObject.createRow(table);
        map.put(beginBean, Long.valueOf(createRow));
        com_ciot_realm_db_ad_BeginBeanRealmProxyInterface com_ciot_realm_db_ad_beginbeanrealmproxyinterface = beginBean;
        String realmGet$hour = com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmGet$hour();
        if (realmGet$hour != null) {
            Table.nativeSetString(nativePtr, beginBeanColumnInfo.hourColKey, createRow, realmGet$hour, false);
        }
        String realmGet$minute = com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmGet$minute();
        if (realmGet$minute != null) {
            Table.nativeSetString(nativePtr, beginBeanColumnInfo.minuteColKey, createRow, realmGet$minute, false);
        }
        Table.nativeSetLong(nativePtr, beginBeanColumnInfo.minutesColKey, createRow, com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmGet$minutes(), false);
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(BeginBean.class);
        long nativePtr = table.getNativePtr();
        BeginBeanColumnInfo beginBeanColumnInfo = (BeginBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) BeginBean.class);
        while (it.hasNext()) {
            BeginBean beginBean = (BeginBean) it.next();
            if (!map2.containsKey(beginBean)) {
                if ((beginBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(beginBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) beginBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(beginBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(beginBean, Long.valueOf(createRow));
                com_ciot_realm_db_ad_BeginBeanRealmProxyInterface com_ciot_realm_db_ad_beginbeanrealmproxyinterface = beginBean;
                String realmGet$hour = com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmGet$hour();
                if (realmGet$hour != null) {
                    Table.nativeSetString(nativePtr, beginBeanColumnInfo.hourColKey, createRow, realmGet$hour, false);
                }
                String realmGet$minute = com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmGet$minute();
                if (realmGet$minute != null) {
                    Table.nativeSetString(nativePtr, beginBeanColumnInfo.minuteColKey, createRow, realmGet$minute, false);
                }
                Table.nativeSetLong(nativePtr, beginBeanColumnInfo.minutesColKey, createRow, com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmGet$minutes(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, BeginBean beginBean, Map<RealmModel, Long> map) {
        if ((beginBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(beginBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) beginBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(BeginBean.class);
        long nativePtr = table.getNativePtr();
        BeginBeanColumnInfo beginBeanColumnInfo = (BeginBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) BeginBean.class);
        long createRow = OsObject.createRow(table);
        map.put(beginBean, Long.valueOf(createRow));
        com_ciot_realm_db_ad_BeginBeanRealmProxyInterface com_ciot_realm_db_ad_beginbeanrealmproxyinterface = beginBean;
        String realmGet$hour = com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmGet$hour();
        if (realmGet$hour != null) {
            Table.nativeSetString(nativePtr, beginBeanColumnInfo.hourColKey, createRow, realmGet$hour, false);
        } else {
            Table.nativeSetNull(nativePtr, beginBeanColumnInfo.hourColKey, createRow, false);
        }
        String realmGet$minute = com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmGet$minute();
        if (realmGet$minute != null) {
            Table.nativeSetString(nativePtr, beginBeanColumnInfo.minuteColKey, createRow, realmGet$minute, false);
        } else {
            Table.nativeSetNull(nativePtr, beginBeanColumnInfo.minuteColKey, createRow, false);
        }
        Table.nativeSetLong(nativePtr, beginBeanColumnInfo.minutesColKey, createRow, com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmGet$minutes(), false);
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(BeginBean.class);
        long nativePtr = table.getNativePtr();
        BeginBeanColumnInfo beginBeanColumnInfo = (BeginBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) BeginBean.class);
        while (it.hasNext()) {
            BeginBean beginBean = (BeginBean) it.next();
            if (!map2.containsKey(beginBean)) {
                if ((beginBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(beginBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) beginBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(beginBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(beginBean, Long.valueOf(createRow));
                com_ciot_realm_db_ad_BeginBeanRealmProxyInterface com_ciot_realm_db_ad_beginbeanrealmproxyinterface = beginBean;
                String realmGet$hour = com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmGet$hour();
                if (realmGet$hour != null) {
                    Table.nativeSetString(nativePtr, beginBeanColumnInfo.hourColKey, createRow, realmGet$hour, false);
                } else {
                    Table.nativeSetNull(nativePtr, beginBeanColumnInfo.hourColKey, createRow, false);
                }
                String realmGet$minute = com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmGet$minute();
                if (realmGet$minute != null) {
                    Table.nativeSetString(nativePtr, beginBeanColumnInfo.minuteColKey, createRow, realmGet$minute, false);
                } else {
                    Table.nativeSetNull(nativePtr, beginBeanColumnInfo.minuteColKey, createRow, false);
                }
                Table.nativeSetLong(nativePtr, beginBeanColumnInfo.minutesColKey, createRow, com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmGet$minutes(), false);
            }
        }
    }

    public static BeginBean createDetachedCopy(BeginBean beginBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        BeginBean beginBean2;
        if (i > i2 || beginBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(beginBean);
        if (cacheData == null) {
            beginBean2 = new BeginBean();
            map.put(beginBean, new RealmObjectProxy.CacheData(i, beginBean2));
        } else if (i >= cacheData.minDepth) {
            return (BeginBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            beginBean2 = (BeginBean) cacheData.object;
        }
        com_ciot_realm_db_ad_BeginBeanRealmProxyInterface com_ciot_realm_db_ad_beginbeanrealmproxyinterface = beginBean2;
        com_ciot_realm_db_ad_BeginBeanRealmProxyInterface com_ciot_realm_db_ad_beginbeanrealmproxyinterface2 = beginBean;
        com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmSet$hour(com_ciot_realm_db_ad_beginbeanrealmproxyinterface2.realmGet$hour());
        com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmSet$minute(com_ciot_realm_db_ad_beginbeanrealmproxyinterface2.realmGet$minute());
        com_ciot_realm_db_ad_beginbeanrealmproxyinterface.realmSet$minutes(com_ciot_realm_db_ad_beginbeanrealmproxyinterface2.realmGet$minutes());
        return beginBean2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("BeginBean = proxy[");
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
        com_ciot_realm_db_ad_BeginBeanRealmProxy com_ciot_realm_db_ad_beginbeanrealmproxy = (com_ciot_realm_db_ad_BeginBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_ad_beginbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_ad_beginbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_ad_beginbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
