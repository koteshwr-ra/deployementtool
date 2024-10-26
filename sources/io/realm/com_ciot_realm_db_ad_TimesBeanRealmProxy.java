package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.ad.BeginBean;
import com.ciot.realm.db.ad.EndBean;
import com.ciot.realm.db.ad.TimesBean;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_ad_BeginBeanRealmProxy;
import io.realm.com_ciot_realm_db_ad_EndBeanRealmProxy;
import io.realm.internal.ColumnInfo;
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
import org.apache.log4j.spi.Configurator;
import org.json.JSONException;
import org.json.JSONObject;

public class com_ciot_realm_db_ad_TimesBeanRealmProxy extends TimesBean implements RealmObjectProxy, com_ciot_realm_db_ad_TimesBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private TimesBeanColumnInfo columnInfo;
    private ProxyState<TimesBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "TimesBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class TimesBeanColumnInfo extends ColumnInfo {
        long beginColKey;
        long endColKey;

        TimesBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.beginColKey = addColumnDetails("begin", "begin", objectSchemaInfo);
            this.endColKey = addColumnDetails("end", "end", objectSchemaInfo);
        }

        TimesBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new TimesBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            TimesBeanColumnInfo timesBeanColumnInfo = (TimesBeanColumnInfo) columnInfo;
            TimesBeanColumnInfo timesBeanColumnInfo2 = (TimesBeanColumnInfo) columnInfo2;
            timesBeanColumnInfo2.beginColKey = timesBeanColumnInfo.beginColKey;
            timesBeanColumnInfo2.endColKey = timesBeanColumnInfo.endColKey;
        }
    }

    com_ciot_realm_db_ad_TimesBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (TimesBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<TimesBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public BeginBean realmGet$begin() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.beginColKey)) {
            return null;
        }
        return (BeginBean) this.proxyState.getRealm$realm().get(BeginBean.class, this.proxyState.getRow$realm().getLink(this.columnInfo.beginColKey), false, Collections.emptyList());
    }

    public void realmSet$begin(BeginBean beginBean) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (beginBean == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.beginColKey);
                return;
            }
            this.proxyState.checkValidObject(beginBean);
            this.proxyState.getRow$realm().setLink(this.columnInfo.beginColKey, ((RealmObjectProxy) beginBean).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("begin")) {
            if (beginBean != null && !RealmObject.isManaged(beginBean)) {
                beginBean = (BeginBean) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(beginBean, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (beginBean == null) {
                row$realm.nullifyLink(this.columnInfo.beginColKey);
                return;
            }
            this.proxyState.checkValidObject(beginBean);
            row$realm.getTable().setLink(this.columnInfo.beginColKey, row$realm.getObjectKey(), ((RealmObjectProxy) beginBean).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public EndBean realmGet$end() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.endColKey)) {
            return null;
        }
        return (EndBean) this.proxyState.getRealm$realm().get(EndBean.class, this.proxyState.getRow$realm().getLink(this.columnInfo.endColKey), false, Collections.emptyList());
    }

    public void realmSet$end(EndBean endBean) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (endBean == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.endColKey);
                return;
            }
            this.proxyState.checkValidObject(endBean);
            this.proxyState.getRow$realm().setLink(this.columnInfo.endColKey, ((RealmObjectProxy) endBean).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("end")) {
            if (endBean != null && !RealmObject.isManaged(endBean)) {
                endBean = (EndBean) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(endBean, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (endBean == null) {
                row$realm.nullifyLink(this.columnInfo.endColKey);
                return;
            }
            this.proxyState.checkValidObject(endBean);
            row$realm.getTable().setLink(this.columnInfo.endColKey, row$realm.getObjectKey(), ((RealmObjectProxy) endBean).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 2, 0);
        builder.addPersistedLinkProperty("begin", RealmFieldType.OBJECT, com_ciot_realm_db_ad_BeginBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedLinkProperty("end", RealmFieldType.OBJECT, com_ciot_realm_db_ad_EndBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TimesBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new TimesBeanColumnInfo(osSchemaInfo);
    }

    public static TimesBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        ArrayList arrayList = new ArrayList(2);
        if (jSONObject.has("begin")) {
            arrayList.add("begin");
        }
        if (jSONObject.has("end")) {
            arrayList.add("end");
        }
        TimesBean timesBean = (TimesBean) realm.createObjectInternal(TimesBean.class, true, arrayList);
        com_ciot_realm_db_ad_TimesBeanRealmProxyInterface com_ciot_realm_db_ad_timesbeanrealmproxyinterface = timesBean;
        if (jSONObject.has("begin")) {
            if (jSONObject.isNull("begin")) {
                com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmSet$begin((BeginBean) null);
            } else {
                com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmSet$begin(com_ciot_realm_db_ad_BeginBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject.getJSONObject("begin"), z));
            }
        }
        if (jSONObject.has("end")) {
            if (jSONObject.isNull("end")) {
                com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmSet$end((EndBean) null);
            } else {
                com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmSet$end(com_ciot_realm_db_ad_EndBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject.getJSONObject("end"), z));
            }
        }
        return timesBean;
    }

    public static TimesBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        TimesBean timesBean = new TimesBean();
        com_ciot_realm_db_ad_TimesBeanRealmProxyInterface com_ciot_realm_db_ad_timesbeanrealmproxyinterface = timesBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("begin")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmSet$begin((BeginBean) null);
                } else {
                    com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmSet$begin(com_ciot_realm_db_ad_BeginBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (!nextName.equals("end")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.skipValue();
                com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmSet$end((EndBean) null);
            } else {
                com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmSet$end(com_ciot_realm_db_ad_EndBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
            }
        }
        jsonReader.endObject();
        return (TimesBean) realm.copyToRealm(timesBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_ad_TimesBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimesBean.class), false, Collections.emptyList());
        com_ciot_realm_db_ad_TimesBeanRealmProxy com_ciot_realm_db_ad_timesbeanrealmproxy = new com_ciot_realm_db_ad_TimesBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_ad_timesbeanrealmproxy;
    }

    public static TimesBean copyOrUpdate(Realm realm, TimesBeanColumnInfo timesBeanColumnInfo, TimesBean timesBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((timesBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(timesBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) timesBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return timesBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(timesBean);
        if (realmObjectProxy2 != null) {
            return (TimesBean) realmObjectProxy2;
        }
        return copy(realm, timesBeanColumnInfo, timesBean, z, map, set);
    }

    public static TimesBean copy(Realm realm, TimesBeanColumnInfo timesBeanColumnInfo, TimesBean timesBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(timesBean);
        if (realmObjectProxy != null) {
            return (TimesBean) realmObjectProxy;
        }
        com_ciot_realm_db_ad_TimesBeanRealmProxyInterface com_ciot_realm_db_ad_timesbeanrealmproxyinterface = timesBean;
        com_ciot_realm_db_ad_TimesBeanRealmProxy newProxyInstance = newProxyInstance(realm, new OsObjectBuilder(realm.getTable(TimesBean.class), set).createNewObject());
        map.put(timesBean, newProxyInstance);
        BeginBean realmGet$begin = com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmGet$begin();
        if (realmGet$begin == null) {
            newProxyInstance.realmSet$begin((BeginBean) null);
        } else {
            BeginBean beginBean = (BeginBean) map.get(realmGet$begin);
            if (beginBean != null) {
                newProxyInstance.realmSet$begin(beginBean);
            } else {
                newProxyInstance.realmSet$begin(com_ciot_realm_db_ad_BeginBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_BeginBeanRealmProxy.BeginBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) BeginBean.class), realmGet$begin, z, map, set));
            }
        }
        EndBean realmGet$end = com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmGet$end();
        if (realmGet$end == null) {
            newProxyInstance.realmSet$end((EndBean) null);
        } else {
            EndBean endBean = (EndBean) map.get(realmGet$end);
            if (endBean != null) {
                newProxyInstance.realmSet$end(endBean);
            } else {
                newProxyInstance.realmSet$end(com_ciot_realm_db_ad_EndBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_EndBeanRealmProxy.EndBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EndBean.class), realmGet$end, z, map, set));
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, TimesBean timesBean, Map<RealmModel, Long> map) {
        Realm realm2 = realm;
        TimesBean timesBean2 = timesBean;
        Map<RealmModel, Long> map2 = map;
        if ((timesBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(timesBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) timesBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(TimesBean.class);
        long nativePtr = table.getNativePtr();
        TimesBeanColumnInfo timesBeanColumnInfo = (TimesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimesBean.class);
        long createRow = OsObject.createRow(table);
        map2.put(timesBean2, Long.valueOf(createRow));
        com_ciot_realm_db_ad_TimesBeanRealmProxyInterface com_ciot_realm_db_ad_timesbeanrealmproxyinterface = timesBean2;
        BeginBean realmGet$begin = com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmGet$begin();
        if (realmGet$begin != null) {
            Long l = map2.get(realmGet$begin);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_ad_BeginBeanRealmProxy.insert(realm2, realmGet$begin, map2));
            }
            Table.nativeSetLink(nativePtr, timesBeanColumnInfo.beginColKey, createRow, l.longValue(), false);
        }
        EndBean realmGet$end = com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmGet$end();
        if (realmGet$end != null) {
            Long l2 = map2.get(realmGet$end);
            if (l2 == null) {
                l2 = Long.valueOf(com_ciot_realm_db_ad_EndBeanRealmProxy.insert(realm2, realmGet$end, map2));
            }
            Table.nativeSetLink(nativePtr, timesBeanColumnInfo.endColKey, createRow, l2.longValue(), false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Table table = realm.getTable(TimesBean.class);
        table.getNativePtr();
        TimesBeanColumnInfo timesBeanColumnInfo = (TimesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimesBean.class);
        while (it.hasNext()) {
            TimesBean timesBean = (TimesBean) it.next();
            if (!map.containsKey(timesBean)) {
                if ((timesBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(timesBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) timesBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map.put(timesBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map.put(timesBean, Long.valueOf(createRow));
                com_ciot_realm_db_ad_TimesBeanRealmProxyInterface com_ciot_realm_db_ad_timesbeanrealmproxyinterface = timesBean;
                BeginBean realmGet$begin = com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmGet$begin();
                if (realmGet$begin != null) {
                    Long l = map.get(realmGet$begin);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_ad_BeginBeanRealmProxy.insert(realm, realmGet$begin, map));
                    }
                    table.setLink(timesBeanColumnInfo.beginColKey, createRow, l.longValue(), false);
                }
                EndBean realmGet$end = com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmGet$end();
                if (realmGet$end != null) {
                    Long l2 = map.get(realmGet$end);
                    if (l2 == null) {
                        l2 = Long.valueOf(com_ciot_realm_db_ad_EndBeanRealmProxy.insert(realm, realmGet$end, map));
                    }
                    table.setLink(timesBeanColumnInfo.endColKey, createRow, l2.longValue(), false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, TimesBean timesBean, Map<RealmModel, Long> map) {
        Realm realm2 = realm;
        TimesBean timesBean2 = timesBean;
        Map<RealmModel, Long> map2 = map;
        if ((timesBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(timesBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) timesBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(TimesBean.class);
        long nativePtr = table.getNativePtr();
        TimesBeanColumnInfo timesBeanColumnInfo = (TimesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimesBean.class);
        long createRow = OsObject.createRow(table);
        map2.put(timesBean2, Long.valueOf(createRow));
        com_ciot_realm_db_ad_TimesBeanRealmProxyInterface com_ciot_realm_db_ad_timesbeanrealmproxyinterface = timesBean2;
        BeginBean realmGet$begin = com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmGet$begin();
        if (realmGet$begin != null) {
            Long l = map2.get(realmGet$begin);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_ad_BeginBeanRealmProxy.insertOrUpdate(realm2, realmGet$begin, map2));
            }
            Table.nativeSetLink(nativePtr, timesBeanColumnInfo.beginColKey, createRow, l.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, timesBeanColumnInfo.beginColKey, createRow);
        }
        EndBean realmGet$end = com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmGet$end();
        if (realmGet$end != null) {
            Long l2 = map2.get(realmGet$end);
            if (l2 == null) {
                l2 = Long.valueOf(com_ciot_realm_db_ad_EndBeanRealmProxy.insertOrUpdate(realm2, realmGet$end, map2));
            }
            Table.nativeSetLink(nativePtr, timesBeanColumnInfo.endColKey, createRow, l2.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, timesBeanColumnInfo.endColKey, createRow);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(TimesBean.class);
        long nativePtr = table.getNativePtr();
        TimesBeanColumnInfo timesBeanColumnInfo = (TimesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimesBean.class);
        while (it.hasNext()) {
            TimesBean timesBean = (TimesBean) it.next();
            if (!map2.containsKey(timesBean)) {
                if ((timesBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(timesBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) timesBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(timesBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(timesBean, Long.valueOf(createRow));
                com_ciot_realm_db_ad_TimesBeanRealmProxyInterface com_ciot_realm_db_ad_timesbeanrealmproxyinterface = timesBean;
                BeginBean realmGet$begin = com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmGet$begin();
                if (realmGet$begin != null) {
                    Long l = map2.get(realmGet$begin);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_ad_BeginBeanRealmProxy.insertOrUpdate(realm2, realmGet$begin, map2));
                    }
                    Table.nativeSetLink(nativePtr, timesBeanColumnInfo.beginColKey, createRow, l.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, timesBeanColumnInfo.beginColKey, createRow);
                }
                EndBean realmGet$end = com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmGet$end();
                if (realmGet$end != null) {
                    Long l2 = map2.get(realmGet$end);
                    if (l2 == null) {
                        l2 = Long.valueOf(com_ciot_realm_db_ad_EndBeanRealmProxy.insertOrUpdate(realm2, realmGet$end, map2));
                    }
                    Table.nativeSetLink(nativePtr, timesBeanColumnInfo.endColKey, createRow, l2.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, timesBeanColumnInfo.endColKey, createRow);
                }
            }
        }
    }

    public static TimesBean createDetachedCopy(TimesBean timesBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        TimesBean timesBean2;
        if (i > i2 || timesBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(timesBean);
        if (cacheData == null) {
            timesBean2 = new TimesBean();
            map.put(timesBean, new RealmObjectProxy.CacheData(i, timesBean2));
        } else if (i >= cacheData.minDepth) {
            return (TimesBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            timesBean2 = (TimesBean) cacheData.object;
        }
        com_ciot_realm_db_ad_TimesBeanRealmProxyInterface com_ciot_realm_db_ad_timesbeanrealmproxyinterface = timesBean2;
        com_ciot_realm_db_ad_TimesBeanRealmProxyInterface com_ciot_realm_db_ad_timesbeanrealmproxyinterface2 = timesBean;
        int i3 = i + 1;
        com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmSet$begin(com_ciot_realm_db_ad_BeginBeanRealmProxy.createDetachedCopy(com_ciot_realm_db_ad_timesbeanrealmproxyinterface2.realmGet$begin(), i3, i2, map));
        com_ciot_realm_db_ad_timesbeanrealmproxyinterface.realmSet$end(com_ciot_realm_db_ad_EndBeanRealmProxy.createDetachedCopy(com_ciot_realm_db_ad_timesbeanrealmproxyinterface2.realmGet$end(), i3, i2, map));
        return timesBean2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("TimesBean = proxy[");
        sb.append("{begin:");
        BeginBean realmGet$begin = realmGet$begin();
        String str = Configurator.NULL;
        sb.append(realmGet$begin != null ? com_ciot_realm_db_ad_BeginBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME : str);
        sb.append("}");
        sb.append(",");
        sb.append("{end:");
        if (realmGet$end() != null) {
            str = com_ciot_realm_db_ad_EndBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
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
        com_ciot_realm_db_ad_TimesBeanRealmProxy com_ciot_realm_db_ad_timesbeanrealmproxy = (com_ciot_realm_db_ad_TimesBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_ad_timesbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_ad_timesbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_ad_timesbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
