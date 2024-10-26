package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.common.LocationBean;
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

public class com_ciot_realm_db_common_LocationBeanRealmProxy extends LocationBean implements RealmObjectProxy, com_ciot_realm_db_common_LocationBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private LocationBeanColumnInfo columnInfo;
    private ProxyState<LocationBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "LocationBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class LocationBeanColumnInfo extends ColumnInfo {
        long latitudeColKey;
        long longitudeColKey;

        LocationBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.longitudeColKey = addColumnDetails("longitude", "longitude", objectSchemaInfo);
            this.latitudeColKey = addColumnDetails("latitude", "latitude", objectSchemaInfo);
        }

        LocationBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new LocationBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            LocationBeanColumnInfo locationBeanColumnInfo = (LocationBeanColumnInfo) columnInfo;
            LocationBeanColumnInfo locationBeanColumnInfo2 = (LocationBeanColumnInfo) columnInfo2;
            locationBeanColumnInfo2.longitudeColKey = locationBeanColumnInfo.longitudeColKey;
            locationBeanColumnInfo2.latitudeColKey = locationBeanColumnInfo.latitudeColKey;
        }
    }

    com_ciot_realm_db_common_LocationBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (LocationBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<LocationBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$longitude() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.longitudeColKey);
    }

    public void realmSet$longitude(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.longitudeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.longitudeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.longitudeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.longitudeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$latitude() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.latitudeColKey);
    }

    public void realmSet$latitude(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.latitudeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.latitudeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.latitudeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.latitudeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 2, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("longitude", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("latitude", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static LocationBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new LocationBeanColumnInfo(osSchemaInfo);
    }

    public static LocationBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        LocationBean locationBean = (LocationBean) realm.createObjectInternal(LocationBean.class, true, Collections.emptyList());
        com_ciot_realm_db_common_LocationBeanRealmProxyInterface com_ciot_realm_db_common_locationbeanrealmproxyinterface = locationBean;
        if (jSONObject.has("longitude")) {
            if (jSONObject.isNull("longitude")) {
                com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmSet$longitude((String) null);
            } else {
                com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmSet$longitude(jSONObject.getString("longitude"));
            }
        }
        if (jSONObject.has("latitude")) {
            if (jSONObject.isNull("latitude")) {
                com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmSet$latitude((String) null);
            } else {
                com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmSet$latitude(jSONObject.getString("latitude"));
            }
        }
        return locationBean;
    }

    public static LocationBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        LocationBean locationBean = new LocationBean();
        com_ciot_realm_db_common_LocationBeanRealmProxyInterface com_ciot_realm_db_common_locationbeanrealmproxyinterface = locationBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("longitude")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmSet$longitude(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmSet$longitude((String) null);
                }
            } else if (!nextName.equals("latitude")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmSet$latitude(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmSet$latitude((String) null);
            }
        }
        jsonReader.endObject();
        return (LocationBean) realm.copyToRealm(locationBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_common_LocationBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) LocationBean.class), false, Collections.emptyList());
        com_ciot_realm_db_common_LocationBeanRealmProxy com_ciot_realm_db_common_locationbeanrealmproxy = new com_ciot_realm_db_common_LocationBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_common_locationbeanrealmproxy;
    }

    public static LocationBean copyOrUpdate(Realm realm, LocationBeanColumnInfo locationBeanColumnInfo, LocationBean locationBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((locationBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(locationBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) locationBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return locationBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(locationBean);
        if (realmObjectProxy2 != null) {
            return (LocationBean) realmObjectProxy2;
        }
        return copy(realm, locationBeanColumnInfo, locationBean, z, map, set);
    }

    public static LocationBean copy(Realm realm, LocationBeanColumnInfo locationBeanColumnInfo, LocationBean locationBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(locationBean);
        if (realmObjectProxy != null) {
            return (LocationBean) realmObjectProxy;
        }
        com_ciot_realm_db_common_LocationBeanRealmProxyInterface com_ciot_realm_db_common_locationbeanrealmproxyinterface = locationBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(LocationBean.class), set);
        osObjectBuilder.addString(locationBeanColumnInfo.longitudeColKey, com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmGet$longitude());
        osObjectBuilder.addString(locationBeanColumnInfo.latitudeColKey, com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmGet$latitude());
        com_ciot_realm_db_common_LocationBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(locationBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, LocationBean locationBean, Map<RealmModel, Long> map) {
        if ((locationBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(locationBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) locationBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(LocationBean.class);
        long nativePtr = table.getNativePtr();
        LocationBeanColumnInfo locationBeanColumnInfo = (LocationBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) LocationBean.class);
        long createRow = OsObject.createRow(table);
        map.put(locationBean, Long.valueOf(createRow));
        com_ciot_realm_db_common_LocationBeanRealmProxyInterface com_ciot_realm_db_common_locationbeanrealmproxyinterface = locationBean;
        String realmGet$longitude = com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmGet$longitude();
        if (realmGet$longitude != null) {
            Table.nativeSetString(nativePtr, locationBeanColumnInfo.longitudeColKey, createRow, realmGet$longitude, false);
        }
        String realmGet$latitude = com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmGet$latitude();
        if (realmGet$latitude != null) {
            Table.nativeSetString(nativePtr, locationBeanColumnInfo.latitudeColKey, createRow, realmGet$latitude, false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(LocationBean.class);
        long nativePtr = table.getNativePtr();
        LocationBeanColumnInfo locationBeanColumnInfo = (LocationBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) LocationBean.class);
        while (it.hasNext()) {
            LocationBean locationBean = (LocationBean) it.next();
            if (!map2.containsKey(locationBean)) {
                if ((locationBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(locationBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) locationBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(locationBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(locationBean, Long.valueOf(createRow));
                com_ciot_realm_db_common_LocationBeanRealmProxyInterface com_ciot_realm_db_common_locationbeanrealmproxyinterface = locationBean;
                String realmGet$longitude = com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmGet$longitude();
                if (realmGet$longitude != null) {
                    Table.nativeSetString(nativePtr, locationBeanColumnInfo.longitudeColKey, createRow, realmGet$longitude, false);
                }
                String realmGet$latitude = com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmGet$latitude();
                if (realmGet$latitude != null) {
                    Table.nativeSetString(nativePtr, locationBeanColumnInfo.latitudeColKey, createRow, realmGet$latitude, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, LocationBean locationBean, Map<RealmModel, Long> map) {
        if ((locationBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(locationBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) locationBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(LocationBean.class);
        long nativePtr = table.getNativePtr();
        LocationBeanColumnInfo locationBeanColumnInfo = (LocationBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) LocationBean.class);
        long createRow = OsObject.createRow(table);
        map.put(locationBean, Long.valueOf(createRow));
        com_ciot_realm_db_common_LocationBeanRealmProxyInterface com_ciot_realm_db_common_locationbeanrealmproxyinterface = locationBean;
        String realmGet$longitude = com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmGet$longitude();
        if (realmGet$longitude != null) {
            Table.nativeSetString(nativePtr, locationBeanColumnInfo.longitudeColKey, createRow, realmGet$longitude, false);
        } else {
            Table.nativeSetNull(nativePtr, locationBeanColumnInfo.longitudeColKey, createRow, false);
        }
        String realmGet$latitude = com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmGet$latitude();
        if (realmGet$latitude != null) {
            Table.nativeSetString(nativePtr, locationBeanColumnInfo.latitudeColKey, createRow, realmGet$latitude, false);
        } else {
            Table.nativeSetNull(nativePtr, locationBeanColumnInfo.latitudeColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(LocationBean.class);
        long nativePtr = table.getNativePtr();
        LocationBeanColumnInfo locationBeanColumnInfo = (LocationBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) LocationBean.class);
        while (it.hasNext()) {
            LocationBean locationBean = (LocationBean) it.next();
            if (!map2.containsKey(locationBean)) {
                if ((locationBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(locationBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) locationBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(locationBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(locationBean, Long.valueOf(createRow));
                com_ciot_realm_db_common_LocationBeanRealmProxyInterface com_ciot_realm_db_common_locationbeanrealmproxyinterface = locationBean;
                String realmGet$longitude = com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmGet$longitude();
                if (realmGet$longitude != null) {
                    Table.nativeSetString(nativePtr, locationBeanColumnInfo.longitudeColKey, createRow, realmGet$longitude, false);
                } else {
                    Table.nativeSetNull(nativePtr, locationBeanColumnInfo.longitudeColKey, createRow, false);
                }
                String realmGet$latitude = com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmGet$latitude();
                if (realmGet$latitude != null) {
                    Table.nativeSetString(nativePtr, locationBeanColumnInfo.latitudeColKey, createRow, realmGet$latitude, false);
                } else {
                    Table.nativeSetNull(nativePtr, locationBeanColumnInfo.latitudeColKey, createRow, false);
                }
            }
        }
    }

    public static LocationBean createDetachedCopy(LocationBean locationBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        LocationBean locationBean2;
        if (i > i2 || locationBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(locationBean);
        if (cacheData == null) {
            locationBean2 = new LocationBean();
            map.put(locationBean, new RealmObjectProxy.CacheData(i, locationBean2));
        } else if (i >= cacheData.minDepth) {
            return (LocationBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            locationBean2 = (LocationBean) cacheData.object;
        }
        com_ciot_realm_db_common_LocationBeanRealmProxyInterface com_ciot_realm_db_common_locationbeanrealmproxyinterface = locationBean2;
        com_ciot_realm_db_common_LocationBeanRealmProxyInterface com_ciot_realm_db_common_locationbeanrealmproxyinterface2 = locationBean;
        com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmSet$longitude(com_ciot_realm_db_common_locationbeanrealmproxyinterface2.realmGet$longitude());
        com_ciot_realm_db_common_locationbeanrealmproxyinterface.realmSet$latitude(com_ciot_realm_db_common_locationbeanrealmproxyinterface2.realmGet$latitude());
        return locationBean2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("LocationBean = proxy[");
        sb.append("{longitude:");
        String realmGet$longitude = realmGet$longitude();
        String str = Configurator.NULL;
        sb.append(realmGet$longitude != null ? realmGet$longitude() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{latitude:");
        if (realmGet$latitude() != null) {
            str = realmGet$latitude();
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
        com_ciot_realm_db_common_LocationBeanRealmProxy com_ciot_realm_db_common_locationbeanrealmproxy = (com_ciot_realm_db_common_LocationBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_common_locationbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_common_locationbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_common_locationbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
