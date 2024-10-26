package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.patrol.Resource;
import io.realm.BaseRealm;
import io.realm.exceptions.RealmException;
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

public class com_ciot_realm_db_patrol_ResourceRealmProxy extends Resource implements RealmObjectProxy, com_ciot_realm_db_patrol_ResourceRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ResourceColumnInfo columnInfo;
    private ProxyState<Resource> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Resource";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class ResourceColumnInfo extends ColumnInfo {
        long idColKey;
        long lanUrlColKey;
        long resColKey;
        long wanUrlColKey;

        ResourceColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(4);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.resColKey = addColumnDetails("res", "res", objectSchemaInfo);
            this.wanUrlColKey = addColumnDetails("wanUrl", "wanUrl", objectSchemaInfo);
            this.lanUrlColKey = addColumnDetails("lanUrl", "lanUrl", objectSchemaInfo);
        }

        ResourceColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new ResourceColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            ResourceColumnInfo resourceColumnInfo = (ResourceColumnInfo) columnInfo;
            ResourceColumnInfo resourceColumnInfo2 = (ResourceColumnInfo) columnInfo2;
            resourceColumnInfo2.idColKey = resourceColumnInfo.idColKey;
            resourceColumnInfo2.resColKey = resourceColumnInfo.resColKey;
            resourceColumnInfo2.wanUrlColKey = resourceColumnInfo.wanUrlColKey;
            resourceColumnInfo2.lanUrlColKey = resourceColumnInfo.lanUrlColKey;
        }
    }

    com_ciot_realm_db_patrol_ResourceRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ResourceColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<Resource> proxyState2 = new ProxyState<>(this);
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
            throw new RealmException("Primary key field 'id' cannot be changed after object was created.");
        }
    }

    public String realmGet$res() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.resColKey);
    }

    public void realmSet$res(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.resColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.resColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.resColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.resColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$wanUrl() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.wanUrlColKey);
    }

    public void realmSet$wanUrl(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.wanUrlColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.wanUrlColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.wanUrlColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.wanUrlColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$lanUrl() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.lanUrlColKey);
    }

    public void realmSet$lanUrl(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.lanUrlColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.lanUrlColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.lanUrlColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.lanUrlColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 4, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("id", RealmFieldType.STRING, true, false, false);
        builder2.addPersistedProperty("res", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("wanUrl", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("lanUrl", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ResourceColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new ResourceColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v4, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v5, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.Resource createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            java.lang.String r1 = "id"
            r2 = 0
            if (r14 == 0) goto L_0x0064
            java.lang.Class<com.ciot.realm.db.patrol.Resource> r14 = com.ciot.realm.db.patrol.Resource.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.patrol.Resource> r4 = com.ciot.realm.db.patrol.Resource.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy$ResourceColumnInfo r3 = (io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy.ResourceColumnInfo) r3
            long r3 = r3.idColKey
            boolean r5 = r13.isNull(r1)
            if (r5 == 0) goto L_0x0028
            long r3 = r14.findFirstNull(r3)
            goto L_0x0030
        L_0x0028:
            java.lang.String r5 = r13.getString(r1)
            long r3 = r14.findFirstString(r3, r5)
        L_0x0030:
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0064
            io.realm.BaseRealm$ThreadLocalRealmObjectContext r5 = io.realm.BaseRealm.objectContext
            java.lang.Object r5 = r5.get()
            io.realm.BaseRealm$RealmObjectContext r5 = (io.realm.BaseRealm.RealmObjectContext) r5
            io.realm.internal.UncheckedRow r8 = r14.getUncheckedRow(r3)     // Catch:{ all -> 0x005f }
            io.realm.RealmSchema r14 = r12.getSchema()     // Catch:{ all -> 0x005f }
            java.lang.Class<com.ciot.realm.db.patrol.Resource> r3 = com.ciot.realm.db.patrol.Resource.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005f }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005f }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005f }
            io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy r14 = new io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy     // Catch:{ all -> 0x005f }
            r14.<init>()     // Catch:{ all -> 0x005f }
            r5.clear()
            goto L_0x0065
        L_0x005f:
            r12 = move-exception
            r5.clear()
            throw r12
        L_0x0064:
            r14 = r2
        L_0x0065:
            if (r14 != 0) goto L_0x0094
            boolean r14 = r13.has(r1)
            if (r14 == 0) goto L_0x008c
            boolean r14 = r13.isNull(r1)
            r3 = 1
            if (r14 == 0) goto L_0x007e
            java.lang.Class<com.ciot.realm.db.patrol.Resource> r14 = com.ciot.realm.db.patrol.Resource.class
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy r14 = (io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy) r14
            goto L_0x0094
        L_0x007e:
            java.lang.Class<com.ciot.realm.db.patrol.Resource> r14 = com.ciot.realm.db.patrol.Resource.class
            java.lang.String r1 = r13.getString(r1)
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy r14 = (io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy) r14
            goto L_0x0094
        L_0x008c:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'id'."
            r12.<init>(r13)
            throw r12
        L_0x0094:
            r12 = r14
            io.realm.com_ciot_realm_db_patrol_ResourceRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_patrol_ResourceRealmProxyInterface) r12
            java.lang.String r0 = "res"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00b0
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00a9
            r12.realmSet$res(r2)
            goto L_0x00b0
        L_0x00a9:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$res(r0)
        L_0x00b0:
            java.lang.String r0 = "wanUrl"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00c9
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00c2
            r12.realmSet$wanUrl(r2)
            goto L_0x00c9
        L_0x00c2:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$wanUrl(r0)
        L_0x00c9:
            java.lang.String r0 = "lanUrl"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00e2
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00db
            r12.realmSet$lanUrl(r2)
            goto L_0x00e2
        L_0x00db:
            java.lang.String r13 = r13.getString(r0)
            r12.realmSet$lanUrl(r13)
        L_0x00e2:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.patrol.Resource");
    }

    public static Resource createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        Resource resource = new Resource();
        com_ciot_realm_db_patrol_ResourceRealmProxyInterface com_ciot_realm_db_patrol_resourcerealmproxyinterface = resource;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmSet$id((String) null);
                }
                z = true;
            } else if (nextName.equals("res")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmSet$res(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmSet$res((String) null);
                }
            } else if (nextName.equals("wanUrl")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmSet$wanUrl(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmSet$wanUrl((String) null);
                }
            } else if (!nextName.equals("lanUrl")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmSet$lanUrl(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmSet$lanUrl((String) null);
            }
        }
        jsonReader.endObject();
        if (z) {
            return (Resource) realm.copyToRealm(resource, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_patrol_ResourceRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) Resource.class), false, Collections.emptyList());
        com_ciot_realm_db_patrol_ResourceRealmProxy com_ciot_realm_db_patrol_resourcerealmproxy = new com_ciot_realm_db_patrol_ResourceRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_patrol_resourcerealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.Resource copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy.ResourceColumnInfo r9, com.ciot.realm.db.patrol.Resource r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
        /*
            boolean r0 = r10 instanceof io.realm.internal.RealmObjectProxy
            if (r0 == 0) goto L_0x003e
            boolean r0 = io.realm.RealmObject.isFrozen(r10)
            if (r0 != 0) goto L_0x003e
            r0 = r10
            io.realm.internal.RealmObjectProxy r0 = (io.realm.internal.RealmObjectProxy) r0
            io.realm.ProxyState r1 = r0.realmGet$proxyState()
            io.realm.BaseRealm r1 = r1.getRealm$realm()
            if (r1 == 0) goto L_0x003e
            io.realm.ProxyState r0 = r0.realmGet$proxyState()
            io.realm.BaseRealm r0 = r0.getRealm$realm()
            long r1 = r0.threadId
            long r3 = r8.threadId
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x0036
            java.lang.String r0 = r0.getPath()
            java.lang.String r1 = r8.getPath()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003e
            return r10
        L_0x0036:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "Objects which belong to Realm instances in other threads cannot be copied into this Realm instance."
            r8.<init>(r9)
            throw r8
        L_0x003e:
            io.realm.BaseRealm$ThreadLocalRealmObjectContext r0 = io.realm.BaseRealm.objectContext
            java.lang.Object r0 = r0.get()
            io.realm.BaseRealm$RealmObjectContext r0 = (io.realm.BaseRealm.RealmObjectContext) r0
            java.lang.Object r1 = r12.get(r10)
            io.realm.internal.RealmObjectProxy r1 = (io.realm.internal.RealmObjectProxy) r1
            if (r1 == 0) goto L_0x0051
            com.ciot.realm.db.patrol.Resource r1 = (com.ciot.realm.db.patrol.Resource) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.patrol.Resource> r2 = com.ciot.realm.db.patrol.Resource.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_patrol_ResourceRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_patrol_ResourceRealmProxyInterface) r5
            java.lang.String r5 = r5.realmGet$id()
            if (r5 != 0) goto L_0x006a
            long r3 = r2.findFirstNull(r3)
            goto L_0x006e
        L_0x006a:
            long r3 = r2.findFirstString(r3, r5)
        L_0x006e:
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0076
            r0 = 0
            goto L_0x009a
        L_0x0076:
            io.realm.internal.UncheckedRow r3 = r2.getUncheckedRow(r3)     // Catch:{ all -> 0x0094 }
            r5 = 0
            java.util.List r6 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0094 }
            r1 = r0
            r2 = r8
            r4 = r9
            r1.set(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0094 }
            io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy r1 = new io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy     // Catch:{ all -> 0x0094 }
            r1.<init>()     // Catch:{ all -> 0x0094 }
            r2 = r1
            io.realm.internal.RealmObjectProxy r2 = (io.realm.internal.RealmObjectProxy) r2     // Catch:{ all -> 0x0094 }
            r12.put(r10, r2)     // Catch:{ all -> 0x0094 }
            r0.clear()
            goto L_0x0099
        L_0x0094:
            r8 = move-exception
            r0.clear()
            throw r8
        L_0x0099:
            r0 = r11
        L_0x009a:
            r3 = r1
            if (r0 == 0) goto L_0x00a7
            r1 = r8
            r2 = r9
            r4 = r10
            r5 = r12
            r6 = r13
            com.ciot.realm.db.patrol.Resource r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.patrol.Resource r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy$ResourceColumnInfo, com.ciot.realm.db.patrol.Resource, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.patrol.Resource");
    }

    public static Resource copy(Realm realm, ResourceColumnInfo resourceColumnInfo, Resource resource, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(resource);
        if (realmObjectProxy != null) {
            return (Resource) realmObjectProxy;
        }
        com_ciot_realm_db_patrol_ResourceRealmProxyInterface com_ciot_realm_db_patrol_resourcerealmproxyinterface = resource;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Resource.class), set);
        osObjectBuilder.addString(resourceColumnInfo.idColKey, com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$id());
        osObjectBuilder.addString(resourceColumnInfo.resColKey, com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$res());
        osObjectBuilder.addString(resourceColumnInfo.wanUrlColKey, com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$wanUrl());
        osObjectBuilder.addString(resourceColumnInfo.lanUrlColKey, com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$lanUrl());
        com_ciot_realm_db_patrol_ResourceRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(resource, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, Resource resource, Map<RealmModel, Long> map) {
        long j;
        Resource resource2 = resource;
        if ((resource2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(resource)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) resource2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Resource.class);
        long nativePtr = table.getNativePtr();
        ResourceColumnInfo resourceColumnInfo = (ResourceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Resource.class);
        long j2 = resourceColumnInfo.idColKey;
        com_ciot_realm_db_patrol_ResourceRealmProxyInterface com_ciot_realm_db_patrol_resourcerealmproxyinterface = resource2;
        String realmGet$id = com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$id);
        } else {
            Table.throwDuplicatePrimaryKeyException(realmGet$id);
        }
        long j3 = j;
        map.put(resource2, Long.valueOf(j3));
        String realmGet$res = com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$res();
        if (realmGet$res != null) {
            Table.nativeSetString(nativePtr, resourceColumnInfo.resColKey, j3, realmGet$res, false);
        }
        String realmGet$wanUrl = com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$wanUrl();
        if (realmGet$wanUrl != null) {
            Table.nativeSetString(nativePtr, resourceColumnInfo.wanUrlColKey, j3, realmGet$wanUrl, false);
        }
        String realmGet$lanUrl = com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$lanUrl();
        if (realmGet$lanUrl != null) {
            Table.nativeSetString(nativePtr, resourceColumnInfo.lanUrlColKey, j3, realmGet$lanUrl, false);
        }
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Resource.class);
        long nativePtr = table.getNativePtr();
        ResourceColumnInfo resourceColumnInfo = (ResourceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Resource.class);
        long j3 = resourceColumnInfo.idColKey;
        while (it.hasNext()) {
            Resource resource = (Resource) it.next();
            if (!map2.containsKey(resource)) {
                if ((resource instanceof RealmObjectProxy) && !RealmObject.isFrozen(resource)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) resource;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(resource, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_ResourceRealmProxyInterface com_ciot_realm_db_patrol_resourcerealmproxyinterface = resource;
                String realmGet$id = com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j3);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j3, realmGet$id);
                }
                if (j == -1) {
                    j2 = OsObject.createRowWithPrimaryKey(table, j3, realmGet$id);
                } else {
                    Table.throwDuplicatePrimaryKeyException(realmGet$id);
                    j2 = j;
                }
                map2.put(resource, Long.valueOf(j2));
                String realmGet$res = com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$res();
                if (realmGet$res != null) {
                    Table.nativeSetString(nativePtr, resourceColumnInfo.resColKey, j2, realmGet$res, false);
                }
                String realmGet$wanUrl = com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$wanUrl();
                if (realmGet$wanUrl != null) {
                    Table.nativeSetString(nativePtr, resourceColumnInfo.wanUrlColKey, j2, realmGet$wanUrl, false);
                }
                String realmGet$lanUrl = com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$lanUrl();
                if (realmGet$lanUrl != null) {
                    Table.nativeSetString(nativePtr, resourceColumnInfo.lanUrlColKey, j2, realmGet$lanUrl, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Resource resource, Map<RealmModel, Long> map) {
        long j;
        Resource resource2 = resource;
        if ((resource2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(resource)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) resource2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Resource.class);
        long nativePtr = table.getNativePtr();
        ResourceColumnInfo resourceColumnInfo = (ResourceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Resource.class);
        long j2 = resourceColumnInfo.idColKey;
        com_ciot_realm_db_patrol_ResourceRealmProxyInterface com_ciot_realm_db_patrol_resourcerealmproxyinterface = resource2;
        String realmGet$id = com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$id);
        }
        long j3 = j;
        map.put(resource2, Long.valueOf(j3));
        String realmGet$res = com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$res();
        if (realmGet$res != null) {
            Table.nativeSetString(nativePtr, resourceColumnInfo.resColKey, j3, realmGet$res, false);
        } else {
            Table.nativeSetNull(nativePtr, resourceColumnInfo.resColKey, j3, false);
        }
        String realmGet$wanUrl = com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$wanUrl();
        if (realmGet$wanUrl != null) {
            Table.nativeSetString(nativePtr, resourceColumnInfo.wanUrlColKey, j3, realmGet$wanUrl, false);
        } else {
            Table.nativeSetNull(nativePtr, resourceColumnInfo.wanUrlColKey, j3, false);
        }
        String realmGet$lanUrl = com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$lanUrl();
        if (realmGet$lanUrl != null) {
            Table.nativeSetString(nativePtr, resourceColumnInfo.lanUrlColKey, j3, realmGet$lanUrl, false);
        } else {
            Table.nativeSetNull(nativePtr, resourceColumnInfo.lanUrlColKey, j3, false);
        }
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Resource.class);
        long nativePtr = table.getNativePtr();
        ResourceColumnInfo resourceColumnInfo = (ResourceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Resource.class);
        long j2 = resourceColumnInfo.idColKey;
        while (it.hasNext()) {
            Resource resource = (Resource) it.next();
            if (!map2.containsKey(resource)) {
                if ((resource instanceof RealmObjectProxy) && !RealmObject.isFrozen(resource)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) resource;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(resource, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_ResourceRealmProxyInterface com_ciot_realm_db_patrol_resourcerealmproxyinterface = resource;
                String realmGet$id = com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j2);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j2, realmGet$id) : j;
                map2.put(resource, Long.valueOf(createRowWithPrimaryKey));
                String realmGet$res = com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$res();
                if (realmGet$res != null) {
                    Table.nativeSetString(nativePtr, resourceColumnInfo.resColKey, createRowWithPrimaryKey, realmGet$res, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourceColumnInfo.resColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$wanUrl = com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$wanUrl();
                if (realmGet$wanUrl != null) {
                    Table.nativeSetString(nativePtr, resourceColumnInfo.wanUrlColKey, createRowWithPrimaryKey, realmGet$wanUrl, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourceColumnInfo.wanUrlColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$lanUrl = com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmGet$lanUrl();
                if (realmGet$lanUrl != null) {
                    Table.nativeSetString(nativePtr, resourceColumnInfo.lanUrlColKey, createRowWithPrimaryKey, realmGet$lanUrl, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourceColumnInfo.lanUrlColKey, createRowWithPrimaryKey, false);
                }
            }
        }
    }

    public static Resource createDetachedCopy(Resource resource, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        Resource resource2;
        if (i > i2 || resource == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(resource);
        if (cacheData == null) {
            resource2 = new Resource();
            map.put(resource, new RealmObjectProxy.CacheData(i, resource2));
        } else if (i >= cacheData.minDepth) {
            return (Resource) cacheData.object;
        } else {
            cacheData.minDepth = i;
            resource2 = (Resource) cacheData.object;
        }
        com_ciot_realm_db_patrol_ResourceRealmProxyInterface com_ciot_realm_db_patrol_resourcerealmproxyinterface = resource2;
        com_ciot_realm_db_patrol_ResourceRealmProxyInterface com_ciot_realm_db_patrol_resourcerealmproxyinterface2 = resource;
        com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmSet$id(com_ciot_realm_db_patrol_resourcerealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmSet$res(com_ciot_realm_db_patrol_resourcerealmproxyinterface2.realmGet$res());
        com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmSet$wanUrl(com_ciot_realm_db_patrol_resourcerealmproxyinterface2.realmGet$wanUrl());
        com_ciot_realm_db_patrol_resourcerealmproxyinterface.realmSet$lanUrl(com_ciot_realm_db_patrol_resourcerealmproxyinterface2.realmGet$lanUrl());
        return resource2;
    }

    static Resource update(Realm realm, ResourceColumnInfo resourceColumnInfo, Resource resource, Resource resource2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_patrol_ResourceRealmProxyInterface com_ciot_realm_db_patrol_resourcerealmproxyinterface = resource;
        com_ciot_realm_db_patrol_ResourceRealmProxyInterface com_ciot_realm_db_patrol_resourcerealmproxyinterface2 = resource2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Resource.class), set);
        osObjectBuilder.addString(resourceColumnInfo.idColKey, com_ciot_realm_db_patrol_resourcerealmproxyinterface2.realmGet$id());
        osObjectBuilder.addString(resourceColumnInfo.resColKey, com_ciot_realm_db_patrol_resourcerealmproxyinterface2.realmGet$res());
        osObjectBuilder.addString(resourceColumnInfo.wanUrlColKey, com_ciot_realm_db_patrol_resourcerealmproxyinterface2.realmGet$wanUrl());
        osObjectBuilder.addString(resourceColumnInfo.lanUrlColKey, com_ciot_realm_db_patrol_resourcerealmproxyinterface2.realmGet$lanUrl());
        osObjectBuilder.updateExistingObject();
        return resource;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("Resource = proxy[");
        sb.append("{id:");
        String realmGet$id = realmGet$id();
        String str = Configurator.NULL;
        sb.append(realmGet$id != null ? realmGet$id() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{res:");
        sb.append(realmGet$res() != null ? realmGet$res() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{wanUrl:");
        sb.append(realmGet$wanUrl() != null ? realmGet$wanUrl() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{lanUrl:");
        if (realmGet$lanUrl() != null) {
            str = realmGet$lanUrl();
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
        com_ciot_realm_db_patrol_ResourceRealmProxy com_ciot_realm_db_patrol_resourcerealmproxy = (com_ciot_realm_db_patrol_ResourceRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_patrol_resourcerealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_patrol_resourcerealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_patrol_resourcerealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
