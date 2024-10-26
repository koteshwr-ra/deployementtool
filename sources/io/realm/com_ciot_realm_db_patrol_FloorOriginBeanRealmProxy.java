package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.patrol.FloorOriginBean;
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
import mc.csst.com.selfchassis.ui.fragment.set.schedule.ScheduleFragment;

public class com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy extends FloorOriginBean implements RealmObjectProxy, com_ciot_realm_db_patrol_FloorOriginBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private FloorOriginBeanColumnInfo columnInfo;
    private ProxyState<FloorOriginBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "FloorOriginBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class FloorOriginBeanColumnInfo extends ColumnInfo {
        long floorColKey;
        long originXColKey;
        long originYColKey;

        FloorOriginBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.floorColKey = addColumnDetails(ScheduleFragment.FLOOR, ScheduleFragment.FLOOR, objectSchemaInfo);
            this.originXColKey = addColumnDetails("originX", "originX", objectSchemaInfo);
            this.originYColKey = addColumnDetails("originY", "originY", objectSchemaInfo);
        }

        FloorOriginBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new FloorOriginBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            FloorOriginBeanColumnInfo floorOriginBeanColumnInfo = (FloorOriginBeanColumnInfo) columnInfo;
            FloorOriginBeanColumnInfo floorOriginBeanColumnInfo2 = (FloorOriginBeanColumnInfo) columnInfo2;
            floorOriginBeanColumnInfo2.floorColKey = floorOriginBeanColumnInfo.floorColKey;
            floorOriginBeanColumnInfo2.originXColKey = floorOriginBeanColumnInfo.originXColKey;
            floorOriginBeanColumnInfo2.originYColKey = floorOriginBeanColumnInfo.originYColKey;
        }
    }

    com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (FloorOriginBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<FloorOriginBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$floor() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.floorColKey);
    }

    public void realmSet$floor(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'floor' cannot be changed after object was created.");
        }
    }

    public double realmGet$originX() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getDouble(this.columnInfo.originXColKey);
    }

    public void realmSet$originX(double d) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setDouble(this.columnInfo.originXColKey, d);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setDouble(this.columnInfo.originXColKey, row$realm.getObjectKey(), d, true);
        }
    }

    public double realmGet$originY() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getDouble(this.columnInfo.originYColKey);
    }

    public void realmSet$originY(double d) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setDouble(this.columnInfo.originYColKey, d);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setDouble(this.columnInfo.originYColKey, row$realm.getObjectKey(), d, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 3, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty(ScheduleFragment.FLOOR, RealmFieldType.STRING, true, false, false);
        builder2.addPersistedProperty("originX", RealmFieldType.DOUBLE, false, false, true);
        builder2.addPersistedProperty("originY", RealmFieldType.DOUBLE, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static FloorOriginBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new FloorOriginBeanColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v6, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v7, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.FloorOriginBean createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            r1 = 0
            java.lang.String r2 = "floor"
            if (r14 == 0) goto L_0x0064
            java.lang.Class<com.ciot.realm.db.patrol.FloorOriginBean> r14 = com.ciot.realm.db.patrol.FloorOriginBean.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.patrol.FloorOriginBean> r4 = com.ciot.realm.db.patrol.FloorOriginBean.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy$FloorOriginBeanColumnInfo r3 = (io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.FloorOriginBeanColumnInfo) r3
            long r3 = r3.floorColKey
            boolean r5 = r13.isNull(r2)
            if (r5 == 0) goto L_0x0028
            long r3 = r14.findFirstNull(r3)
            goto L_0x0030
        L_0x0028:
            java.lang.String r5 = r13.getString(r2)
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
            java.lang.Class<com.ciot.realm.db.patrol.FloorOriginBean> r3 = com.ciot.realm.db.patrol.FloorOriginBean.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005f }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005f }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005f }
            io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy r14 = new io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy     // Catch:{ all -> 0x005f }
            r14.<init>()     // Catch:{ all -> 0x005f }
            r5.clear()
            goto L_0x0065
        L_0x005f:
            r12 = move-exception
            r5.clear()
            throw r12
        L_0x0064:
            r14 = r1
        L_0x0065:
            if (r14 != 0) goto L_0x0094
            boolean r14 = r13.has(r2)
            if (r14 == 0) goto L_0x008c
            boolean r14 = r13.isNull(r2)
            r3 = 1
            if (r14 == 0) goto L_0x007e
            java.lang.Class<com.ciot.realm.db.patrol.FloorOriginBean> r14 = com.ciot.realm.db.patrol.FloorOriginBean.class
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy r14 = (io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy) r14
            goto L_0x0094
        L_0x007e:
            java.lang.Class<com.ciot.realm.db.patrol.FloorOriginBean> r14 = com.ciot.realm.db.patrol.FloorOriginBean.class
            java.lang.String r1 = r13.getString(r2)
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy r14 = (io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy) r14
            goto L_0x0094
        L_0x008c:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'floor'."
            r12.<init>(r13)
            throw r12
        L_0x0094:
            r12 = r14
            io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxyInterface) r12
            java.lang.String r0 = "originX"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00b5
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x00ad
            double r0 = r13.getDouble(r0)
            r12.realmSet$originX(r0)
            goto L_0x00b5
        L_0x00ad:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'originX' to null."
            r12.<init>(r13)
            throw r12
        L_0x00b5:
            java.lang.String r0 = "originY"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00d3
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x00cb
            double r0 = r13.getDouble(r0)
            r12.realmSet$originY(r0)
            goto L_0x00d3
        L_0x00cb:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'originY' to null."
            r12.<init>(r13)
            throw r12
        L_0x00d3:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.patrol.FloorOriginBean");
    }

    public static FloorOriginBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        FloorOriginBean floorOriginBean = new FloorOriginBean();
        com_ciot_realm_db_patrol_FloorOriginBeanRealmProxyInterface com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface = floorOriginBean;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(ScheduleFragment.FLOOR)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmSet$floor(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmSet$floor((String) null);
                }
                z = true;
            } else if (nextName.equals("originX")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmSet$originX(jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'originX' to null.");
                }
            } else if (!nextName.equals("originY")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmSet$originY(jsonReader.nextDouble());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'originY' to null.");
            }
        }
        jsonReader.endObject();
        if (z) {
            return (FloorOriginBean) realm.copyToRealm(floorOriginBean, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'floor'.");
    }

    private static com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) FloorOriginBean.class), false, Collections.emptyList());
        com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy com_ciot_realm_db_patrol_floororiginbeanrealmproxy = new com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_patrol_floororiginbeanrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.FloorOriginBean copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.FloorOriginBeanColumnInfo r9, com.ciot.realm.db.patrol.FloorOriginBean r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.patrol.FloorOriginBean r1 = (com.ciot.realm.db.patrol.FloorOriginBean) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.patrol.FloorOriginBean> r2 = com.ciot.realm.db.patrol.FloorOriginBean.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.floorColKey
            r5 = r10
            io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxyInterface) r5
            java.lang.String r5 = r5.realmGet$floor()
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
            io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy r1 = new io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.patrol.FloorOriginBean r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.patrol.FloorOriginBean r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy$FloorOriginBeanColumnInfo, com.ciot.realm.db.patrol.FloorOriginBean, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.patrol.FloorOriginBean");
    }

    public static FloorOriginBean copy(Realm realm, FloorOriginBeanColumnInfo floorOriginBeanColumnInfo, FloorOriginBean floorOriginBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(floorOriginBean);
        if (realmObjectProxy != null) {
            return (FloorOriginBean) realmObjectProxy;
        }
        com_ciot_realm_db_patrol_FloorOriginBeanRealmProxyInterface com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface = floorOriginBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(FloorOriginBean.class), set);
        osObjectBuilder.addString(floorOriginBeanColumnInfo.floorColKey, com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmGet$floor());
        osObjectBuilder.addDouble(floorOriginBeanColumnInfo.originXColKey, Double.valueOf(com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmGet$originX()));
        osObjectBuilder.addDouble(floorOriginBeanColumnInfo.originYColKey, Double.valueOf(com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmGet$originY()));
        com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(floorOriginBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, FloorOriginBean floorOriginBean, Map<RealmModel, Long> map) {
        long j;
        FloorOriginBean floorOriginBean2 = floorOriginBean;
        if ((floorOriginBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(floorOriginBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) floorOriginBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(FloorOriginBean.class);
        long nativePtr = table.getNativePtr();
        FloorOriginBeanColumnInfo floorOriginBeanColumnInfo = (FloorOriginBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) FloorOriginBean.class);
        long j2 = floorOriginBeanColumnInfo.floorColKey;
        com_ciot_realm_db_patrol_FloorOriginBeanRealmProxyInterface com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface = floorOriginBean2;
        String realmGet$floor = com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmGet$floor();
        if (realmGet$floor == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$floor);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$floor);
        } else {
            Table.throwDuplicatePrimaryKeyException(realmGet$floor);
        }
        long j3 = j;
        map.put(floorOriginBean2, Long.valueOf(j3));
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetDouble(j4, floorOriginBeanColumnInfo.originXColKey, j5, com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmGet$originX(), false);
        Table.nativeSetDouble(j4, floorOriginBeanColumnInfo.originYColKey, j5, com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmGet$originY(), false);
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(FloorOriginBean.class);
        long nativePtr = table.getNativePtr();
        FloorOriginBeanColumnInfo floorOriginBeanColumnInfo = (FloorOriginBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) FloorOriginBean.class);
        long j3 = floorOriginBeanColumnInfo.floorColKey;
        while (it.hasNext()) {
            FloorOriginBean floorOriginBean = (FloorOriginBean) it.next();
            if (!map2.containsKey(floorOriginBean)) {
                if ((floorOriginBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(floorOriginBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) floorOriginBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(floorOriginBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_FloorOriginBeanRealmProxyInterface com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface = floorOriginBean;
                String realmGet$floor = com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmGet$floor();
                if (realmGet$floor == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j3);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j3, realmGet$floor);
                }
                if (j == -1) {
                    j2 = OsObject.createRowWithPrimaryKey(table, j3, realmGet$floor);
                } else {
                    Table.throwDuplicatePrimaryKeyException(realmGet$floor);
                    j2 = j;
                }
                map2.put(floorOriginBean, Long.valueOf(j2));
                long j4 = nativePtr;
                long j5 = j2;
                Table.nativeSetDouble(j4, floorOriginBeanColumnInfo.originXColKey, j5, com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmGet$originX(), false);
                Table.nativeSetDouble(j4, floorOriginBeanColumnInfo.originYColKey, j5, com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmGet$originY(), false);
                j3 = j3;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, FloorOriginBean floorOriginBean, Map<RealmModel, Long> map) {
        long j;
        FloorOriginBean floorOriginBean2 = floorOriginBean;
        if ((floorOriginBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(floorOriginBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) floorOriginBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(FloorOriginBean.class);
        long nativePtr = table.getNativePtr();
        FloorOriginBeanColumnInfo floorOriginBeanColumnInfo = (FloorOriginBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) FloorOriginBean.class);
        long j2 = floorOriginBeanColumnInfo.floorColKey;
        com_ciot_realm_db_patrol_FloorOriginBeanRealmProxyInterface com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface = floorOriginBean2;
        String realmGet$floor = com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmGet$floor();
        if (realmGet$floor == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$floor);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$floor);
        }
        long j3 = j;
        map.put(floorOriginBean2, Long.valueOf(j3));
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetDouble(j4, floorOriginBeanColumnInfo.originXColKey, j5, com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmGet$originX(), false);
        Table.nativeSetDouble(j4, floorOriginBeanColumnInfo.originYColKey, j5, com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmGet$originY(), false);
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(FloorOriginBean.class);
        long nativePtr = table.getNativePtr();
        FloorOriginBeanColumnInfo floorOriginBeanColumnInfo = (FloorOriginBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) FloorOriginBean.class);
        long j2 = floorOriginBeanColumnInfo.floorColKey;
        while (it.hasNext()) {
            FloorOriginBean floorOriginBean = (FloorOriginBean) it.next();
            if (!map2.containsKey(floorOriginBean)) {
                if ((floorOriginBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(floorOriginBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) floorOriginBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(floorOriginBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_FloorOriginBeanRealmProxyInterface com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface = floorOriginBean;
                String realmGet$floor = com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmGet$floor();
                if (realmGet$floor == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j2);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j2, realmGet$floor);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j2, realmGet$floor) : j;
                map2.put(floorOriginBean, Long.valueOf(createRowWithPrimaryKey));
                long j3 = nativePtr;
                long j4 = createRowWithPrimaryKey;
                Table.nativeSetDouble(j3, floorOriginBeanColumnInfo.originXColKey, j4, com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmGet$originX(), false);
                Table.nativeSetDouble(j3, floorOriginBeanColumnInfo.originYColKey, j4, com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmGet$originY(), false);
                j2 = j2;
            }
        }
    }

    public static FloorOriginBean createDetachedCopy(FloorOriginBean floorOriginBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        FloorOriginBean floorOriginBean2;
        if (i > i2 || floorOriginBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(floorOriginBean);
        if (cacheData == null) {
            floorOriginBean2 = new FloorOriginBean();
            map.put(floorOriginBean, new RealmObjectProxy.CacheData(i, floorOriginBean2));
        } else if (i >= cacheData.minDepth) {
            return (FloorOriginBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            floorOriginBean2 = (FloorOriginBean) cacheData.object;
        }
        com_ciot_realm_db_patrol_FloorOriginBeanRealmProxyInterface com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface = floorOriginBean2;
        com_ciot_realm_db_patrol_FloorOriginBeanRealmProxyInterface com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface2 = floorOriginBean;
        com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmSet$floor(com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface2.realmGet$floor());
        com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmSet$originX(com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface2.realmGet$originX());
        com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface.realmSet$originY(com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface2.realmGet$originY());
        return floorOriginBean2;
    }

    static FloorOriginBean update(Realm realm, FloorOriginBeanColumnInfo floorOriginBeanColumnInfo, FloorOriginBean floorOriginBean, FloorOriginBean floorOriginBean2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_patrol_FloorOriginBeanRealmProxyInterface com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface = floorOriginBean;
        com_ciot_realm_db_patrol_FloorOriginBeanRealmProxyInterface com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface2 = floorOriginBean2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(FloorOriginBean.class), set);
        osObjectBuilder.addString(floorOriginBeanColumnInfo.floorColKey, com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface2.realmGet$floor());
        osObjectBuilder.addDouble(floorOriginBeanColumnInfo.originXColKey, Double.valueOf(com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface2.realmGet$originX()));
        osObjectBuilder.addDouble(floorOriginBeanColumnInfo.originYColKey, Double.valueOf(com_ciot_realm_db_patrol_floororiginbeanrealmproxyinterface2.realmGet$originY()));
        osObjectBuilder.updateExistingObject();
        return floorOriginBean;
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
        com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy com_ciot_realm_db_patrol_floororiginbeanrealmproxy = (com_ciot_realm_db_patrol_FloorOriginBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_patrol_floororiginbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_patrol_floororiginbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_patrol_floororiginbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
