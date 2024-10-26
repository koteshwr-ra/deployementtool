package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.visitor.MediaResult;
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

public class com_ciot_realm_db_visitor_MediaResultRealmProxy extends MediaResult implements RealmObjectProxy, com_ciot_realm_db_visitor_MediaResultRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private MediaResultColumnInfo columnInfo;
    private ProxyState<MediaResult> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "MediaResult";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class MediaResultColumnInfo extends ColumnInfo {
        long insTimeColKey;

        MediaResultColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(1);
            this.insTimeColKey = addColumnDetails("insTime", "insTime", osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME));
        }

        MediaResultColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new MediaResultColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            ((MediaResultColumnInfo) columnInfo2).insTimeColKey = ((MediaResultColumnInfo) columnInfo).insTimeColKey;
        }
    }

    com_ciot_realm_db_visitor_MediaResultRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (MediaResultColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<MediaResult> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public long realmGet$insTime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.insTimeColKey);
    }

    public void realmSet$insTime(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.insTimeColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.insTimeColKey, row$realm.getObjectKey(), j, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 1, 0);
        builder.addPersistedProperty("insTime", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static MediaResultColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new MediaResultColumnInfo(osSchemaInfo);
    }

    public static MediaResult createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        MediaResult mediaResult = (MediaResult) realm.createObjectInternal(MediaResult.class, true, Collections.emptyList());
        com_ciot_realm_db_visitor_MediaResultRealmProxyInterface com_ciot_realm_db_visitor_mediaresultrealmproxyinterface = mediaResult;
        if (jSONObject.has("insTime")) {
            if (!jSONObject.isNull("insTime")) {
                com_ciot_realm_db_visitor_mediaresultrealmproxyinterface.realmSet$insTime(jSONObject.getLong("insTime"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'insTime' to null.");
            }
        }
        return mediaResult;
    }

    public static MediaResult createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        MediaResult mediaResult = new MediaResult();
        com_ciot_realm_db_visitor_MediaResultRealmProxyInterface com_ciot_realm_db_visitor_mediaresultrealmproxyinterface = mediaResult;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            if (!jsonReader.nextName().equals("insTime")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_visitor_mediaresultrealmproxyinterface.realmSet$insTime(jsonReader.nextLong());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'insTime' to null.");
            }
        }
        jsonReader.endObject();
        return (MediaResult) realm.copyToRealm(mediaResult, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_visitor_MediaResultRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) MediaResult.class), false, Collections.emptyList());
        com_ciot_realm_db_visitor_MediaResultRealmProxy com_ciot_realm_db_visitor_mediaresultrealmproxy = new com_ciot_realm_db_visitor_MediaResultRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_visitor_mediaresultrealmproxy;
    }

    public static MediaResult copyOrUpdate(Realm realm, MediaResultColumnInfo mediaResultColumnInfo, MediaResult mediaResult, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((mediaResult instanceof RealmObjectProxy) && !RealmObject.isFrozen(mediaResult)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) mediaResult;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return mediaResult;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(mediaResult);
        if (realmObjectProxy2 != null) {
            return (MediaResult) realmObjectProxy2;
        }
        return copy(realm, mediaResultColumnInfo, mediaResult, z, map, set);
    }

    public static MediaResult copy(Realm realm, MediaResultColumnInfo mediaResultColumnInfo, MediaResult mediaResult, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(mediaResult);
        if (realmObjectProxy != null) {
            return (MediaResult) realmObjectProxy;
        }
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(MediaResult.class), set);
        osObjectBuilder.addInteger(mediaResultColumnInfo.insTimeColKey, Long.valueOf(mediaResult.realmGet$insTime()));
        com_ciot_realm_db_visitor_MediaResultRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(mediaResult, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, MediaResult mediaResult, Map<RealmModel, Long> map) {
        if ((mediaResult instanceof RealmObjectProxy) && !RealmObject.isFrozen(mediaResult)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) mediaResult;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(MediaResult.class);
        long nativePtr = table.getNativePtr();
        long createRow = OsObject.createRow(table);
        map.put(mediaResult, Long.valueOf(createRow));
        Table.nativeSetLong(nativePtr, ((MediaResultColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MediaResult.class)).insTimeColKey, createRow, mediaResult.realmGet$insTime(), false);
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Table table = realm.getTable(MediaResult.class);
        long nativePtr = table.getNativePtr();
        MediaResultColumnInfo mediaResultColumnInfo = (MediaResultColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MediaResult.class);
        while (it.hasNext()) {
            MediaResult mediaResult = (MediaResult) it.next();
            if (!map.containsKey(mediaResult)) {
                if ((mediaResult instanceof RealmObjectProxy) && !RealmObject.isFrozen(mediaResult)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) mediaResult;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map.put(mediaResult, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map.put(mediaResult, Long.valueOf(createRow));
                Table.nativeSetLong(nativePtr, mediaResultColumnInfo.insTimeColKey, createRow, mediaResult.realmGet$insTime(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, MediaResult mediaResult, Map<RealmModel, Long> map) {
        if ((mediaResult instanceof RealmObjectProxy) && !RealmObject.isFrozen(mediaResult)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) mediaResult;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(MediaResult.class);
        long nativePtr = table.getNativePtr();
        long createRow = OsObject.createRow(table);
        map.put(mediaResult, Long.valueOf(createRow));
        Table.nativeSetLong(nativePtr, ((MediaResultColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MediaResult.class)).insTimeColKey, createRow, mediaResult.realmGet$insTime(), false);
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Table table = realm.getTable(MediaResult.class);
        long nativePtr = table.getNativePtr();
        MediaResultColumnInfo mediaResultColumnInfo = (MediaResultColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MediaResult.class);
        while (it.hasNext()) {
            MediaResult mediaResult = (MediaResult) it.next();
            if (!map.containsKey(mediaResult)) {
                if ((mediaResult instanceof RealmObjectProxy) && !RealmObject.isFrozen(mediaResult)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) mediaResult;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map.put(mediaResult, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map.put(mediaResult, Long.valueOf(createRow));
                Table.nativeSetLong(nativePtr, mediaResultColumnInfo.insTimeColKey, createRow, mediaResult.realmGet$insTime(), false);
            }
        }
    }

    public static MediaResult createDetachedCopy(MediaResult mediaResult, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        MediaResult mediaResult2;
        if (i > i2 || mediaResult == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(mediaResult);
        if (cacheData == null) {
            mediaResult2 = new MediaResult();
            map.put(mediaResult, new RealmObjectProxy.CacheData(i, mediaResult2));
        } else if (i >= cacheData.minDepth) {
            return (MediaResult) cacheData.object;
        } else {
            cacheData.minDepth = i;
            mediaResult2 = (MediaResult) cacheData.object;
        }
        mediaResult2.realmSet$insTime(mediaResult.realmGet$insTime());
        return mediaResult2;
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
        com_ciot_realm_db_visitor_MediaResultRealmProxy com_ciot_realm_db_visitor_mediaresultrealmproxy = (com_ciot_realm_db_visitor_MediaResultRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_visitor_mediaresultrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_visitor_mediaresultrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_visitor_mediaresultrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
