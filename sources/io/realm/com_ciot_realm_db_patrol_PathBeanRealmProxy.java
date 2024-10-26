package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.patrol.PathBean;
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

public class com_ciot_realm_db_patrol_PathBeanRealmProxy extends PathBean implements RealmObjectProxy, com_ciot_realm_db_patrol_PathBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private PathBeanColumnInfo columnInfo;
    private ProxyState<PathBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "PathBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class PathBeanColumnInfo extends ColumnInfo {
        long isSelectedColKey;
        long pathColKey;
        long pathIdColKey;
        long pathNameColKey;

        PathBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(4);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.pathIdColKey = addColumnDetails("pathId", "pathId", objectSchemaInfo);
            this.pathNameColKey = addColumnDetails("pathName", "pathName", objectSchemaInfo);
            this.pathColKey = addColumnDetails("path", "path", objectSchemaInfo);
            this.isSelectedColKey = addColumnDetails("isSelected", "isSelected", objectSchemaInfo);
        }

        PathBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new PathBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            PathBeanColumnInfo pathBeanColumnInfo = (PathBeanColumnInfo) columnInfo;
            PathBeanColumnInfo pathBeanColumnInfo2 = (PathBeanColumnInfo) columnInfo2;
            pathBeanColumnInfo2.pathIdColKey = pathBeanColumnInfo.pathIdColKey;
            pathBeanColumnInfo2.pathNameColKey = pathBeanColumnInfo.pathNameColKey;
            pathBeanColumnInfo2.pathColKey = pathBeanColumnInfo.pathColKey;
            pathBeanColumnInfo2.isSelectedColKey = pathBeanColumnInfo.isSelectedColKey;
        }
    }

    com_ciot_realm_db_patrol_PathBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (PathBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<PathBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$pathId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.pathIdColKey);
    }

    public void realmSet$pathId(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'pathId' cannot be changed after object was created.");
        }
    }

    public String realmGet$pathName() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.pathNameColKey);
    }

    public void realmSet$pathName(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.pathNameColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.pathNameColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.pathNameColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.pathNameColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$path() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.pathColKey);
    }

    public void realmSet$path(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.pathColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.pathColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.pathColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.pathColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public Boolean realmGet$isSelected() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNull(this.columnInfo.isSelectedColKey)) {
            return null;
        }
        return Boolean.valueOf(this.proxyState.getRow$realm().getBoolean(this.columnInfo.isSelectedColKey));
    }

    public void realmSet$isSelected(Boolean bool) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (bool == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.isSelectedColKey);
            } else {
                this.proxyState.getRow$realm().setBoolean(this.columnInfo.isSelectedColKey, bool.booleanValue());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (bool == null) {
                row$realm.getTable().setNull(this.columnInfo.isSelectedColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setBoolean(this.columnInfo.isSelectedColKey, row$realm.getObjectKey(), bool.booleanValue(), true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 4, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("pathId", RealmFieldType.STRING, true, false, false);
        builder2.addPersistedProperty("pathName", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("path", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("isSelected", RealmFieldType.BOOLEAN, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static PathBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new PathBeanColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v4, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v5, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.PathBean createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            java.lang.String r1 = "pathId"
            r2 = 0
            if (r14 == 0) goto L_0x0064
            java.lang.Class<com.ciot.realm.db.patrol.PathBean> r14 = com.ciot.realm.db.patrol.PathBean.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.patrol.PathBean> r4 = com.ciot.realm.db.patrol.PathBean.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxy$PathBeanColumnInfo r3 = (io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxy.PathBeanColumnInfo) r3
            long r3 = r3.pathIdColKey
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
            java.lang.Class<com.ciot.realm.db.patrol.PathBean> r3 = com.ciot.realm.db.patrol.PathBean.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005f }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005f }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005f }
            io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxy r14 = new io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxy     // Catch:{ all -> 0x005f }
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
            java.lang.Class<com.ciot.realm.db.patrol.PathBean> r14 = com.ciot.realm.db.patrol.PathBean.class
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxy r14 = (io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxy) r14
            goto L_0x0094
        L_0x007e:
            java.lang.Class<com.ciot.realm.db.patrol.PathBean> r14 = com.ciot.realm.db.patrol.PathBean.class
            java.lang.String r1 = r13.getString(r1)
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxy r14 = (io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxy) r14
            goto L_0x0094
        L_0x008c:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'pathId'."
            r12.<init>(r13)
            throw r12
        L_0x0094:
            r12 = r14
            io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxyInterface) r12
            java.lang.String r0 = "pathName"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00b0
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00a9
            r12.realmSet$pathName(r2)
            goto L_0x00b0
        L_0x00a9:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$pathName(r0)
        L_0x00b0:
            java.lang.String r0 = "path"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00c9
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00c2
            r12.realmSet$path(r2)
            goto L_0x00c9
        L_0x00c2:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$path(r0)
        L_0x00c9:
            java.lang.String r0 = "isSelected"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00e6
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00db
            r12.realmSet$isSelected(r2)
            goto L_0x00e6
        L_0x00db:
            boolean r13 = r13.getBoolean(r0)
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r13)
            r12.realmSet$isSelected(r13)
        L_0x00e6:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.patrol.PathBean");
    }

    public static PathBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        PathBean pathBean = new PathBean();
        com_ciot_realm_db_patrol_PathBeanRealmProxyInterface com_ciot_realm_db_patrol_pathbeanrealmproxyinterface = pathBean;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("pathId")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmSet$pathId(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmSet$pathId((String) null);
                }
                z = true;
            } else if (nextName.equals("pathName")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmSet$pathName(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmSet$pathName((String) null);
                }
            } else if (nextName.equals("path")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmSet$path(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmSet$path((String) null);
                }
            } else if (!nextName.equals("isSelected")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmSet$isSelected(Boolean.valueOf(jsonReader.nextBoolean()));
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmSet$isSelected((Boolean) null);
            }
        }
        jsonReader.endObject();
        if (z) {
            return (PathBean) realm.copyToRealm(pathBean, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'pathId'.");
    }

    private static com_ciot_realm_db_patrol_PathBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) PathBean.class), false, Collections.emptyList());
        com_ciot_realm_db_patrol_PathBeanRealmProxy com_ciot_realm_db_patrol_pathbeanrealmproxy = new com_ciot_realm_db_patrol_PathBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_patrol_pathbeanrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.PathBean copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxy.PathBeanColumnInfo r9, com.ciot.realm.db.patrol.PathBean r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.patrol.PathBean r1 = (com.ciot.realm.db.patrol.PathBean) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.patrol.PathBean> r2 = com.ciot.realm.db.patrol.PathBean.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.pathIdColKey
            r5 = r10
            io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxyInterface) r5
            java.lang.String r5 = r5.realmGet$pathId()
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
            io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxy r1 = new io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.patrol.PathBean r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.patrol.PathBean r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxy$PathBeanColumnInfo, com.ciot.realm.db.patrol.PathBean, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.patrol.PathBean");
    }

    public static PathBean copy(Realm realm, PathBeanColumnInfo pathBeanColumnInfo, PathBean pathBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(pathBean);
        if (realmObjectProxy != null) {
            return (PathBean) realmObjectProxy;
        }
        com_ciot_realm_db_patrol_PathBeanRealmProxyInterface com_ciot_realm_db_patrol_pathbeanrealmproxyinterface = pathBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(PathBean.class), set);
        osObjectBuilder.addString(pathBeanColumnInfo.pathIdColKey, com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$pathId());
        osObjectBuilder.addString(pathBeanColumnInfo.pathNameColKey, com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$pathName());
        osObjectBuilder.addString(pathBeanColumnInfo.pathColKey, com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$path());
        osObjectBuilder.addBoolean(pathBeanColumnInfo.isSelectedColKey, com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$isSelected());
        com_ciot_realm_db_patrol_PathBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(pathBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, PathBean pathBean, Map<RealmModel, Long> map) {
        long j;
        PathBean pathBean2 = pathBean;
        if ((pathBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(pathBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) pathBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(PathBean.class);
        long nativePtr = table.getNativePtr();
        PathBeanColumnInfo pathBeanColumnInfo = (PathBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PathBean.class);
        long j2 = pathBeanColumnInfo.pathIdColKey;
        com_ciot_realm_db_patrol_PathBeanRealmProxyInterface com_ciot_realm_db_patrol_pathbeanrealmproxyinterface = pathBean2;
        String realmGet$pathId = com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$pathId();
        if (realmGet$pathId == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$pathId);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$pathId);
        } else {
            Table.throwDuplicatePrimaryKeyException(realmGet$pathId);
        }
        long j3 = j;
        map.put(pathBean2, Long.valueOf(j3));
        String realmGet$pathName = com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$pathName();
        if (realmGet$pathName != null) {
            Table.nativeSetString(nativePtr, pathBeanColumnInfo.pathNameColKey, j3, realmGet$pathName, false);
        }
        String realmGet$path = com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$path();
        if (realmGet$path != null) {
            Table.nativeSetString(nativePtr, pathBeanColumnInfo.pathColKey, j3, realmGet$path, false);
        }
        Boolean realmGet$isSelected = com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$isSelected();
        if (realmGet$isSelected != null) {
            Table.nativeSetBoolean(nativePtr, pathBeanColumnInfo.isSelectedColKey, j3, realmGet$isSelected.booleanValue(), false);
        }
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(PathBean.class);
        long nativePtr = table.getNativePtr();
        PathBeanColumnInfo pathBeanColumnInfo = (PathBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PathBean.class);
        long j3 = pathBeanColumnInfo.pathIdColKey;
        while (it.hasNext()) {
            PathBean pathBean = (PathBean) it.next();
            if (!map2.containsKey(pathBean)) {
                if ((pathBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(pathBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) pathBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(pathBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_PathBeanRealmProxyInterface com_ciot_realm_db_patrol_pathbeanrealmproxyinterface = pathBean;
                String realmGet$pathId = com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$pathId();
                if (realmGet$pathId == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j3);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j3, realmGet$pathId);
                }
                if (j == -1) {
                    j2 = OsObject.createRowWithPrimaryKey(table, j3, realmGet$pathId);
                } else {
                    Table.throwDuplicatePrimaryKeyException(realmGet$pathId);
                    j2 = j;
                }
                map2.put(pathBean, Long.valueOf(j2));
                String realmGet$pathName = com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$pathName();
                if (realmGet$pathName != null) {
                    Table.nativeSetString(nativePtr, pathBeanColumnInfo.pathNameColKey, j2, realmGet$pathName, false);
                }
                String realmGet$path = com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$path();
                if (realmGet$path != null) {
                    Table.nativeSetString(nativePtr, pathBeanColumnInfo.pathColKey, j2, realmGet$path, false);
                }
                Boolean realmGet$isSelected = com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$isSelected();
                if (realmGet$isSelected != null) {
                    Table.nativeSetBoolean(nativePtr, pathBeanColumnInfo.isSelectedColKey, j2, realmGet$isSelected.booleanValue(), false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, PathBean pathBean, Map<RealmModel, Long> map) {
        long j;
        PathBean pathBean2 = pathBean;
        if ((pathBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(pathBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) pathBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(PathBean.class);
        long nativePtr = table.getNativePtr();
        PathBeanColumnInfo pathBeanColumnInfo = (PathBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PathBean.class);
        long j2 = pathBeanColumnInfo.pathIdColKey;
        com_ciot_realm_db_patrol_PathBeanRealmProxyInterface com_ciot_realm_db_patrol_pathbeanrealmproxyinterface = pathBean2;
        String realmGet$pathId = com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$pathId();
        if (realmGet$pathId == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$pathId);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$pathId);
        }
        long j3 = j;
        map.put(pathBean2, Long.valueOf(j3));
        String realmGet$pathName = com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$pathName();
        if (realmGet$pathName != null) {
            Table.nativeSetString(nativePtr, pathBeanColumnInfo.pathNameColKey, j3, realmGet$pathName, false);
        } else {
            Table.nativeSetNull(nativePtr, pathBeanColumnInfo.pathNameColKey, j3, false);
        }
        String realmGet$path = com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$path();
        if (realmGet$path != null) {
            Table.nativeSetString(nativePtr, pathBeanColumnInfo.pathColKey, j3, realmGet$path, false);
        } else {
            Table.nativeSetNull(nativePtr, pathBeanColumnInfo.pathColKey, j3, false);
        }
        Boolean realmGet$isSelected = com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$isSelected();
        if (realmGet$isSelected != null) {
            Table.nativeSetBoolean(nativePtr, pathBeanColumnInfo.isSelectedColKey, j3, realmGet$isSelected.booleanValue(), false);
        } else {
            Table.nativeSetNull(nativePtr, pathBeanColumnInfo.isSelectedColKey, j3, false);
        }
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(PathBean.class);
        long nativePtr = table.getNativePtr();
        PathBeanColumnInfo pathBeanColumnInfo = (PathBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PathBean.class);
        long j2 = pathBeanColumnInfo.pathIdColKey;
        while (it.hasNext()) {
            PathBean pathBean = (PathBean) it.next();
            if (!map2.containsKey(pathBean)) {
                if ((pathBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(pathBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) pathBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(pathBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_PathBeanRealmProxyInterface com_ciot_realm_db_patrol_pathbeanrealmproxyinterface = pathBean;
                String realmGet$pathId = com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$pathId();
                if (realmGet$pathId == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j2);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j2, realmGet$pathId);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j2, realmGet$pathId) : j;
                map2.put(pathBean, Long.valueOf(createRowWithPrimaryKey));
                String realmGet$pathName = com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$pathName();
                if (realmGet$pathName != null) {
                    Table.nativeSetString(nativePtr, pathBeanColumnInfo.pathNameColKey, createRowWithPrimaryKey, realmGet$pathName, false);
                } else {
                    Table.nativeSetNull(nativePtr, pathBeanColumnInfo.pathNameColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$path = com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$path();
                if (realmGet$path != null) {
                    Table.nativeSetString(nativePtr, pathBeanColumnInfo.pathColKey, createRowWithPrimaryKey, realmGet$path, false);
                } else {
                    Table.nativeSetNull(nativePtr, pathBeanColumnInfo.pathColKey, createRowWithPrimaryKey, false);
                }
                Boolean realmGet$isSelected = com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmGet$isSelected();
                if (realmGet$isSelected != null) {
                    Table.nativeSetBoolean(nativePtr, pathBeanColumnInfo.isSelectedColKey, createRowWithPrimaryKey, realmGet$isSelected.booleanValue(), false);
                } else {
                    Table.nativeSetNull(nativePtr, pathBeanColumnInfo.isSelectedColKey, createRowWithPrimaryKey, false);
                }
            }
        }
    }

    public static PathBean createDetachedCopy(PathBean pathBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        PathBean pathBean2;
        if (i > i2 || pathBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(pathBean);
        if (cacheData == null) {
            pathBean2 = new PathBean();
            map.put(pathBean, new RealmObjectProxy.CacheData(i, pathBean2));
        } else if (i >= cacheData.minDepth) {
            return (PathBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            pathBean2 = (PathBean) cacheData.object;
        }
        com_ciot_realm_db_patrol_PathBeanRealmProxyInterface com_ciot_realm_db_patrol_pathbeanrealmproxyinterface = pathBean2;
        com_ciot_realm_db_patrol_PathBeanRealmProxyInterface com_ciot_realm_db_patrol_pathbeanrealmproxyinterface2 = pathBean;
        com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmSet$pathId(com_ciot_realm_db_patrol_pathbeanrealmproxyinterface2.realmGet$pathId());
        com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmSet$pathName(com_ciot_realm_db_patrol_pathbeanrealmproxyinterface2.realmGet$pathName());
        com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmSet$path(com_ciot_realm_db_patrol_pathbeanrealmproxyinterface2.realmGet$path());
        com_ciot_realm_db_patrol_pathbeanrealmproxyinterface.realmSet$isSelected(com_ciot_realm_db_patrol_pathbeanrealmproxyinterface2.realmGet$isSelected());
        return pathBean2;
    }

    static PathBean update(Realm realm, PathBeanColumnInfo pathBeanColumnInfo, PathBean pathBean, PathBean pathBean2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_patrol_PathBeanRealmProxyInterface com_ciot_realm_db_patrol_pathbeanrealmproxyinterface = pathBean;
        com_ciot_realm_db_patrol_PathBeanRealmProxyInterface com_ciot_realm_db_patrol_pathbeanrealmproxyinterface2 = pathBean2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(PathBean.class), set);
        osObjectBuilder.addString(pathBeanColumnInfo.pathIdColKey, com_ciot_realm_db_patrol_pathbeanrealmproxyinterface2.realmGet$pathId());
        osObjectBuilder.addString(pathBeanColumnInfo.pathNameColKey, com_ciot_realm_db_patrol_pathbeanrealmproxyinterface2.realmGet$pathName());
        osObjectBuilder.addString(pathBeanColumnInfo.pathColKey, com_ciot_realm_db_patrol_pathbeanrealmproxyinterface2.realmGet$path());
        osObjectBuilder.addBoolean(pathBeanColumnInfo.isSelectedColKey, com_ciot_realm_db_patrol_pathbeanrealmproxyinterface2.realmGet$isSelected());
        osObjectBuilder.updateExistingObject();
        return pathBean;
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
        com_ciot_realm_db_patrol_PathBeanRealmProxy com_ciot_realm_db_patrol_pathbeanrealmproxy = (com_ciot_realm_db_patrol_PathBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_patrol_pathbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_patrol_pathbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_patrol_pathbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
