package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.ad.CycleBean;
import io.realm.BaseRealm;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
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

public class com_ciot_realm_db_ad_CycleBeanRealmProxy extends CycleBean implements RealmObjectProxy, com_ciot_realm_db_ad_CycleBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private CycleBeanColumnInfo columnInfo;
    private RealmList<Long> dataRealmList;
    private ProxyState<CycleBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "CycleBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class CycleBeanColumnInfo extends ColumnInfo {
        long dataColKey;
        long typeColKey;

        CycleBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.typeColKey = addColumnDetails("type", "type", objectSchemaInfo);
            this.dataColKey = addColumnDetails("data", "data", objectSchemaInfo);
        }

        CycleBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new CycleBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            CycleBeanColumnInfo cycleBeanColumnInfo = (CycleBeanColumnInfo) columnInfo;
            CycleBeanColumnInfo cycleBeanColumnInfo2 = (CycleBeanColumnInfo) columnInfo2;
            cycleBeanColumnInfo2.typeColKey = cycleBeanColumnInfo.typeColKey;
            cycleBeanColumnInfo2.dataColKey = cycleBeanColumnInfo.dataColKey;
        }
    }

    com_ciot_realm_db_ad_CycleBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (CycleBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<CycleBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$type() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.typeColKey);
    }

    public void realmSet$type(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.typeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.typeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.typeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.typeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public RealmList<Long> realmGet$data() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<Long> realmList = this.dataRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<Long> realmList2 = new RealmList<>(Long.class, this.proxyState.getRow$realm().getValueList(this.columnInfo.dataColKey, RealmFieldType.INTEGER_LIST), this.proxyState.getRealm$realm());
        this.dataRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$data(RealmList<Long> realmList) {
        if (!this.proxyState.isUnderConstruction() || (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("data"))) {
            this.proxyState.getRealm$realm().checkIfValid();
            OsList valueList = this.proxyState.getRow$realm().getValueList(this.columnInfo.dataColKey, RealmFieldType.INTEGER_LIST);
            valueList.removeAll();
            if (realmList != null) {
                Iterator<Long> it = realmList.iterator();
                while (it.hasNext()) {
                    Long next = it.next();
                    if (next == null) {
                        valueList.addNull();
                    } else {
                        valueList.addLong(next.longValue());
                    }
                }
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 2, 0);
        builder.addPersistedProperty("type", RealmFieldType.STRING, false, false, false);
        builder.addPersistedValueListProperty("data", RealmFieldType.INTEGER_LIST, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static CycleBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new CycleBeanColumnInfo(osSchemaInfo);
    }

    public static CycleBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        ArrayList arrayList = new ArrayList(1);
        if (jSONObject.has("data")) {
            arrayList.add("data");
        }
        CycleBean cycleBean = (CycleBean) realm.createObjectInternal(CycleBean.class, true, arrayList);
        com_ciot_realm_db_ad_CycleBeanRealmProxyInterface com_ciot_realm_db_ad_cyclebeanrealmproxyinterface = cycleBean;
        if (jSONObject.has("type")) {
            if (jSONObject.isNull("type")) {
                com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmSet$type((String) null);
            } else {
                com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmSet$type(jSONObject.getString("type"));
            }
        }
        ProxyUtils.setRealmListWithJsonObject(com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmGet$data(), jSONObject, "data");
        return cycleBean;
    }

    public static CycleBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        CycleBean cycleBean = new CycleBean();
        com_ciot_realm_db_ad_CycleBeanRealmProxyInterface com_ciot_realm_db_ad_cyclebeanrealmproxyinterface = cycleBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("type")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmSet$type(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmSet$type((String) null);
                }
            } else if (nextName.equals("data")) {
                com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmSet$data(ProxyUtils.createRealmListWithJsonStream(Long.class, jsonReader));
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return (CycleBean) realm.copyToRealm(cycleBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_ad_CycleBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) CycleBean.class), false, Collections.emptyList());
        com_ciot_realm_db_ad_CycleBeanRealmProxy com_ciot_realm_db_ad_cyclebeanrealmproxy = new com_ciot_realm_db_ad_CycleBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_ad_cyclebeanrealmproxy;
    }

    public static CycleBean copyOrUpdate(Realm realm, CycleBeanColumnInfo cycleBeanColumnInfo, CycleBean cycleBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((cycleBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(cycleBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) cycleBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return cycleBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(cycleBean);
        if (realmObjectProxy2 != null) {
            return (CycleBean) realmObjectProxy2;
        }
        return copy(realm, cycleBeanColumnInfo, cycleBean, z, map, set);
    }

    public static CycleBean copy(Realm realm, CycleBeanColumnInfo cycleBeanColumnInfo, CycleBean cycleBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(cycleBean);
        if (realmObjectProxy != null) {
            return (CycleBean) realmObjectProxy;
        }
        com_ciot_realm_db_ad_CycleBeanRealmProxyInterface com_ciot_realm_db_ad_cyclebeanrealmproxyinterface = cycleBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(CycleBean.class), set);
        osObjectBuilder.addString(cycleBeanColumnInfo.typeColKey, com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmGet$type());
        osObjectBuilder.addLongList(cycleBeanColumnInfo.dataColKey, com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmGet$data());
        com_ciot_realm_db_ad_CycleBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(cycleBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, CycleBean cycleBean, Map<RealmModel, Long> map) {
        if ((cycleBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(cycleBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) cycleBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(CycleBean.class);
        long nativePtr = table.getNativePtr();
        CycleBeanColumnInfo cycleBeanColumnInfo = (CycleBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CycleBean.class);
        long createRow = OsObject.createRow(table);
        map.put(cycleBean, Long.valueOf(createRow));
        com_ciot_realm_db_ad_CycleBeanRealmProxyInterface com_ciot_realm_db_ad_cyclebeanrealmproxyinterface = cycleBean;
        String realmGet$type = com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(nativePtr, cycleBeanColumnInfo.typeColKey, createRow, realmGet$type, false);
        }
        RealmList<Long> realmGet$data = com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmGet$data();
        if (realmGet$data != null) {
            OsList osList = new OsList(table.getUncheckedRow(createRow), cycleBeanColumnInfo.dataColKey);
            Iterator<Long> it = realmGet$data.iterator();
            while (it.hasNext()) {
                Long next = it.next();
                if (next == null) {
                    osList.addNull();
                } else {
                    osList.addLong(next.longValue());
                }
            }
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(CycleBean.class);
        long nativePtr = table.getNativePtr();
        CycleBeanColumnInfo cycleBeanColumnInfo = (CycleBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CycleBean.class);
        while (it.hasNext()) {
            CycleBean cycleBean = (CycleBean) it.next();
            if (!map2.containsKey(cycleBean)) {
                if ((cycleBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(cycleBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) cycleBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(cycleBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(cycleBean, Long.valueOf(createRow));
                com_ciot_realm_db_ad_CycleBeanRealmProxyInterface com_ciot_realm_db_ad_cyclebeanrealmproxyinterface = cycleBean;
                String realmGet$type = com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmGet$type();
                if (realmGet$type != null) {
                    Table.nativeSetString(nativePtr, cycleBeanColumnInfo.typeColKey, createRow, realmGet$type, false);
                }
                RealmList<Long> realmGet$data = com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmGet$data();
                if (realmGet$data != null) {
                    OsList osList = new OsList(table.getUncheckedRow(createRow), cycleBeanColumnInfo.dataColKey);
                    Iterator<Long> it2 = realmGet$data.iterator();
                    while (it2.hasNext()) {
                        Long next = it2.next();
                        if (next == null) {
                            osList.addNull();
                        } else {
                            osList.addLong(next.longValue());
                        }
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, CycleBean cycleBean, Map<RealmModel, Long> map) {
        if ((cycleBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(cycleBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) cycleBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(CycleBean.class);
        long nativePtr = table.getNativePtr();
        CycleBeanColumnInfo cycleBeanColumnInfo = (CycleBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CycleBean.class);
        long createRow = OsObject.createRow(table);
        map.put(cycleBean, Long.valueOf(createRow));
        com_ciot_realm_db_ad_CycleBeanRealmProxyInterface com_ciot_realm_db_ad_cyclebeanrealmproxyinterface = cycleBean;
        String realmGet$type = com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(nativePtr, cycleBeanColumnInfo.typeColKey, createRow, realmGet$type, false);
        } else {
            Table.nativeSetNull(nativePtr, cycleBeanColumnInfo.typeColKey, createRow, false);
        }
        OsList osList = new OsList(table.getUncheckedRow(createRow), cycleBeanColumnInfo.dataColKey);
        osList.removeAll();
        RealmList<Long> realmGet$data = com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmGet$data();
        if (realmGet$data != null) {
            Iterator<Long> it = realmGet$data.iterator();
            while (it.hasNext()) {
                Long next = it.next();
                if (next == null) {
                    osList.addNull();
                } else {
                    osList.addLong(next.longValue());
                }
            }
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(CycleBean.class);
        long nativePtr = table.getNativePtr();
        CycleBeanColumnInfo cycleBeanColumnInfo = (CycleBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CycleBean.class);
        while (it.hasNext()) {
            CycleBean cycleBean = (CycleBean) it.next();
            if (!map2.containsKey(cycleBean)) {
                if ((cycleBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(cycleBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) cycleBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(cycleBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(cycleBean, Long.valueOf(createRow));
                com_ciot_realm_db_ad_CycleBeanRealmProxyInterface com_ciot_realm_db_ad_cyclebeanrealmproxyinterface = cycleBean;
                String realmGet$type = com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmGet$type();
                if (realmGet$type != null) {
                    Table.nativeSetString(nativePtr, cycleBeanColumnInfo.typeColKey, createRow, realmGet$type, false);
                } else {
                    Table.nativeSetNull(nativePtr, cycleBeanColumnInfo.typeColKey, createRow, false);
                }
                OsList osList = new OsList(table.getUncheckedRow(createRow), cycleBeanColumnInfo.dataColKey);
                osList.removeAll();
                RealmList<Long> realmGet$data = com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmGet$data();
                if (realmGet$data != null) {
                    Iterator<Long> it2 = realmGet$data.iterator();
                    while (it2.hasNext()) {
                        Long next = it2.next();
                        if (next == null) {
                            osList.addNull();
                        } else {
                            osList.addLong(next.longValue());
                        }
                    }
                }
            }
        }
    }

    public static CycleBean createDetachedCopy(CycleBean cycleBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        CycleBean cycleBean2;
        if (i > i2 || cycleBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(cycleBean);
        if (cacheData == null) {
            cycleBean2 = new CycleBean();
            map.put(cycleBean, new RealmObjectProxy.CacheData(i, cycleBean2));
        } else if (i >= cacheData.minDepth) {
            return (CycleBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            cycleBean2 = (CycleBean) cacheData.object;
        }
        com_ciot_realm_db_ad_CycleBeanRealmProxyInterface com_ciot_realm_db_ad_cyclebeanrealmproxyinterface = cycleBean2;
        com_ciot_realm_db_ad_CycleBeanRealmProxyInterface com_ciot_realm_db_ad_cyclebeanrealmproxyinterface2 = cycleBean;
        com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmSet$type(com_ciot_realm_db_ad_cyclebeanrealmproxyinterface2.realmGet$type());
        com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmSet$data(new RealmList());
        com_ciot_realm_db_ad_cyclebeanrealmproxyinterface.realmGet$data().addAll(com_ciot_realm_db_ad_cyclebeanrealmproxyinterface2.realmGet$data());
        return cycleBean2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("CycleBean = proxy[");
        sb.append("{type:");
        sb.append(realmGet$type() != null ? realmGet$type() : Configurator.NULL);
        sb.append("}");
        sb.append(",");
        sb.append("{data:");
        sb.append("RealmList<Long>[");
        sb.append(realmGet$data().size());
        sb.append("]");
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
        com_ciot_realm_db_ad_CycleBeanRealmProxy com_ciot_realm_db_ad_cyclebeanrealmproxy = (com_ciot_realm_db_ad_CycleBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_ad_cyclebeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_ad_cyclebeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_ad_cyclebeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
