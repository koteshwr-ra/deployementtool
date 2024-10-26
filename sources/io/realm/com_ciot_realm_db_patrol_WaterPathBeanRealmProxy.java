package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.patrol.WaterPathBean;
import io.realm.BaseRealm;
import io.realm.exceptions.RealmException;
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
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class com_ciot_realm_db_patrol_WaterPathBeanRealmProxy extends WaterPathBean implements RealmObjectProxy, com_ciot_realm_db_patrol_WaterPathBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private WaterPathBeanColumnInfo columnInfo;
    private RealmList<String> mPathRealmList;
    private ProxyState<WaterPathBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "WaterPathBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class WaterPathBeanColumnInfo extends ColumnInfo {
        long idColKey;
        long mPathColKey;

        WaterPathBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.mPathColKey = addColumnDetails("mPath", "mPath", objectSchemaInfo);
        }

        WaterPathBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new WaterPathBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            WaterPathBeanColumnInfo waterPathBeanColumnInfo = (WaterPathBeanColumnInfo) columnInfo;
            WaterPathBeanColumnInfo waterPathBeanColumnInfo2 = (WaterPathBeanColumnInfo) columnInfo2;
            waterPathBeanColumnInfo2.idColKey = waterPathBeanColumnInfo.idColKey;
            waterPathBeanColumnInfo2.mPathColKey = waterPathBeanColumnInfo.mPathColKey;
        }
    }

    com_ciot_realm_db_patrol_WaterPathBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (WaterPathBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<WaterPathBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public int realmGet$id() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.idColKey);
    }

    public void realmSet$id(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'id' cannot be changed after object was created.");
        }
    }

    public RealmList<String> realmGet$mPath() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<String> realmList = this.mPathRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<String> realmList2 = new RealmList<>(String.class, this.proxyState.getRow$realm().getValueList(this.columnInfo.mPathColKey, RealmFieldType.STRING_LIST), this.proxyState.getRealm$realm());
        this.mPathRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$mPath(RealmList<String> realmList) {
        if (!this.proxyState.isUnderConstruction() || (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("mPath"))) {
            this.proxyState.getRealm$realm().checkIfValid();
            OsList valueList = this.proxyState.getRow$realm().getValueList(this.columnInfo.mPathColKey, RealmFieldType.STRING_LIST);
            valueList.removeAll();
            if (realmList != null) {
                Iterator<String> it = realmList.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    if (next == null) {
                        valueList.addNull();
                    } else {
                        valueList.addString(next);
                    }
                }
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 2, 0);
        builder.addPersistedProperty("id", RealmFieldType.INTEGER, true, true, true);
        builder.addPersistedValueListProperty("mPath", RealmFieldType.STRING_LIST, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static WaterPathBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new WaterPathBeanColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r13v5, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r13v6, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0068  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.WaterPathBean createOrUpdateUsingJsonObject(io.realm.Realm r13, org.json.JSONObject r14, boolean r15) throws org.json.JSONException {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 1
            r0.<init>(r1)
            r2 = 0
            java.lang.String r3 = "id"
            if (r15 == 0) goto L_0x0063
            java.lang.Class<com.ciot.realm.db.patrol.WaterPathBean> r15 = com.ciot.realm.db.patrol.WaterPathBean.class
            io.realm.internal.Table r15 = r13.getTable(r15)
            io.realm.RealmSchema r4 = r13.getSchema()
            java.lang.Class<com.ciot.realm.db.patrol.WaterPathBean> r5 = com.ciot.realm.db.patrol.WaterPathBean.class
            io.realm.internal.ColumnInfo r4 = r4.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r5)
            io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxy$WaterPathBeanColumnInfo r4 = (io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.WaterPathBeanColumnInfo) r4
            long r4 = r4.idColKey
            boolean r6 = r14.isNull(r3)
            r7 = -1
            if (r6 != 0) goto L_0x0030
            long r9 = r14.getLong(r3)
            long r4 = r15.findFirstLong(r4, r9)
            goto L_0x0031
        L_0x0030:
            r4 = r7
        L_0x0031:
            int r6 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r6 == 0) goto L_0x0063
            io.realm.BaseRealm$ThreadLocalRealmObjectContext r6 = io.realm.BaseRealm.objectContext
            java.lang.Object r6 = r6.get()
            io.realm.BaseRealm$RealmObjectContext r6 = (io.realm.BaseRealm.RealmObjectContext) r6
            io.realm.internal.UncheckedRow r9 = r15.getUncheckedRow(r4)     // Catch:{ all -> 0x005e }
            io.realm.RealmSchema r15 = r13.getSchema()     // Catch:{ all -> 0x005e }
            java.lang.Class<com.ciot.realm.db.patrol.WaterPathBean> r4 = com.ciot.realm.db.patrol.WaterPathBean.class
            io.realm.internal.ColumnInfo r10 = r15.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)     // Catch:{ all -> 0x005e }
            r11 = 0
            java.util.List r12 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005e }
            r7 = r6
            r8 = r13
            r7.set(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x005e }
            io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxy r15 = new io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxy     // Catch:{ all -> 0x005e }
            r15.<init>()     // Catch:{ all -> 0x005e }
            r6.clear()
            goto L_0x0064
        L_0x005e:
            r13 = move-exception
            r6.clear()
            throw r13
        L_0x0063:
            r15 = r2
        L_0x0064:
            java.lang.String r4 = "mPath"
            if (r15 != 0) goto L_0x00a1
            boolean r15 = r14.has(r4)
            if (r15 == 0) goto L_0x0071
            r0.add(r4)
        L_0x0071:
            boolean r15 = r14.has(r3)
            if (r15 == 0) goto L_0x0099
            boolean r15 = r14.isNull(r3)
            if (r15 == 0) goto L_0x0087
            java.lang.Class<com.ciot.realm.db.patrol.WaterPathBean> r15 = com.ciot.realm.db.patrol.WaterPathBean.class
            io.realm.RealmModel r13 = r13.createObjectInternal(r15, r2, r1, r0)
            r15 = r13
            io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxy r15 = (io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxy) r15
            goto L_0x00a1
        L_0x0087:
            java.lang.Class<com.ciot.realm.db.patrol.WaterPathBean> r15 = com.ciot.realm.db.patrol.WaterPathBean.class
            int r2 = r14.getInt(r3)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            io.realm.RealmModel r13 = r13.createObjectInternal(r15, r2, r1, r0)
            r15 = r13
            io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxy r15 = (io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxy) r15
            goto L_0x00a1
        L_0x0099:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "JSON object doesn't have the primary key field 'id'."
            r13.<init>(r14)
            throw r13
        L_0x00a1:
            r13 = r15
            io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxyInterface r13 = (io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxyInterface) r13
            io.realm.RealmList r13 = r13.realmGet$mPath()
            io.realm.ProxyUtils.setRealmListWithJsonObject(r13, r14, r4)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.patrol.WaterPathBean");
    }

    public static WaterPathBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        WaterPathBean waterPathBean = new WaterPathBean();
        com_ciot_realm_db_patrol_WaterPathBeanRealmProxyInterface com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface = waterPathBean;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmSet$id(jsonReader.nextInt());
                    z = true;
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                }
            } else if (nextName.equals("mPath")) {
                com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmSet$mPath(ProxyUtils.createRealmListWithJsonStream(String.class, jsonReader));
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        if (z) {
            return (WaterPathBean) realm.copyToRealm(waterPathBean, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_patrol_WaterPathBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) WaterPathBean.class), false, Collections.emptyList());
        com_ciot_realm_db_patrol_WaterPathBeanRealmProxy com_ciot_realm_db_patrol_waterpathbeanrealmproxy = new com_ciot_realm_db_patrol_WaterPathBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_patrol_waterpathbeanrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.WaterPathBean copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.WaterPathBeanColumnInfo r9, com.ciot.realm.db.patrol.WaterPathBean r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.patrol.WaterPathBean r1 = (com.ciot.realm.db.patrol.WaterPathBean) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0093
            java.lang.Class<com.ciot.realm.db.patrol.WaterPathBean> r2 = com.ciot.realm.db.patrol.WaterPathBean.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxyInterface) r5
            int r5 = r5.realmGet$id()
            long r5 = (long) r5
            long r3 = r2.findFirstLong(r3, r5)
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0070
            r0 = 0
            goto L_0x0094
        L_0x0070:
            io.realm.internal.UncheckedRow r3 = r2.getUncheckedRow(r3)     // Catch:{ all -> 0x008e }
            r5 = 0
            java.util.List r6 = java.util.Collections.emptyList()     // Catch:{ all -> 0x008e }
            r1 = r0
            r2 = r8
            r4 = r9
            r1.set(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x008e }
            io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxy r1 = new io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxy     // Catch:{ all -> 0x008e }
            r1.<init>()     // Catch:{ all -> 0x008e }
            r2 = r1
            io.realm.internal.RealmObjectProxy r2 = (io.realm.internal.RealmObjectProxy) r2     // Catch:{ all -> 0x008e }
            r12.put(r10, r2)     // Catch:{ all -> 0x008e }
            r0.clear()
            goto L_0x0093
        L_0x008e:
            r8 = move-exception
            r0.clear()
            throw r8
        L_0x0093:
            r0 = r11
        L_0x0094:
            r3 = r1
            if (r0 == 0) goto L_0x00a1
            r1 = r8
            r2 = r9
            r4 = r10
            r5 = r12
            r6 = r13
            com.ciot.realm.db.patrol.WaterPathBean r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00a5
        L_0x00a1:
            com.ciot.realm.db.patrol.WaterPathBean r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00a5:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_patrol_WaterPathBeanRealmProxy$WaterPathBeanColumnInfo, com.ciot.realm.db.patrol.WaterPathBean, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.patrol.WaterPathBean");
    }

    public static WaterPathBean copy(Realm realm, WaterPathBeanColumnInfo waterPathBeanColumnInfo, WaterPathBean waterPathBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(waterPathBean);
        if (realmObjectProxy != null) {
            return (WaterPathBean) realmObjectProxy;
        }
        com_ciot_realm_db_patrol_WaterPathBeanRealmProxyInterface com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface = waterPathBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(WaterPathBean.class), set);
        osObjectBuilder.addInteger(waterPathBeanColumnInfo.idColKey, Integer.valueOf(com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$id()));
        osObjectBuilder.addStringList(waterPathBeanColumnInfo.mPathColKey, com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$mPath());
        com_ciot_realm_db_patrol_WaterPathBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(waterPathBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, WaterPathBean waterPathBean, Map<RealmModel, Long> map) {
        long j;
        if ((waterPathBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(waterPathBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) waterPathBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(WaterPathBean.class);
        long nativePtr = table.getNativePtr();
        WaterPathBeanColumnInfo waterPathBeanColumnInfo = (WaterPathBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) WaterPathBean.class);
        long j2 = waterPathBeanColumnInfo.idColKey;
        com_ciot_realm_db_patrol_WaterPathBeanRealmProxyInterface com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface = waterPathBean;
        Integer valueOf = Integer.valueOf(com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$id());
        if (valueOf != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, (long) com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$id());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Integer.valueOf(com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$id()));
        } else {
            Table.throwDuplicatePrimaryKeyException(valueOf);
        }
        map.put(waterPathBean, Long.valueOf(j));
        RealmList<String> realmGet$mPath = com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$mPath();
        if (realmGet$mPath != null) {
            OsList osList = new OsList(table.getUncheckedRow(j), waterPathBeanColumnInfo.mPathColKey);
            Iterator<String> it = realmGet$mPath.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next == null) {
                    osList.addNull();
                } else {
                    osList.addString(next);
                }
            }
        }
        return j;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(WaterPathBean.class);
        long nativePtr = table.getNativePtr();
        WaterPathBeanColumnInfo waterPathBeanColumnInfo = (WaterPathBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) WaterPathBean.class);
        long j2 = waterPathBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            WaterPathBean waterPathBean = (WaterPathBean) it.next();
            if (!map2.containsKey(waterPathBean)) {
                if ((waterPathBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(waterPathBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) waterPathBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(waterPathBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_WaterPathBeanRealmProxyInterface com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface = waterPathBean;
                Integer valueOf = Integer.valueOf(com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$id());
                if (valueOf != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j2, (long) com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$id());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j2, Integer.valueOf(com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$id()));
                } else {
                    Table.throwDuplicatePrimaryKeyException(valueOf);
                }
                map2.put(waterPathBean, Long.valueOf(j));
                RealmList<String> realmGet$mPath = com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$mPath();
                if (realmGet$mPath != null) {
                    OsList osList = new OsList(table.getUncheckedRow(j), waterPathBeanColumnInfo.mPathColKey);
                    Iterator<String> it2 = realmGet$mPath.iterator();
                    while (it2.hasNext()) {
                        String next = it2.next();
                        if (next == null) {
                            osList.addNull();
                        } else {
                            osList.addString(next);
                        }
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, WaterPathBean waterPathBean, Map<RealmModel, Long> map) {
        long j;
        if ((waterPathBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(waterPathBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) waterPathBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(WaterPathBean.class);
        long nativePtr = table.getNativePtr();
        WaterPathBeanColumnInfo waterPathBeanColumnInfo = (WaterPathBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) WaterPathBean.class);
        long j2 = waterPathBeanColumnInfo.idColKey;
        com_ciot_realm_db_patrol_WaterPathBeanRealmProxyInterface com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface = waterPathBean;
        if (Integer.valueOf(com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$id()) != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, (long) com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$id());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Integer.valueOf(com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$id()));
        }
        map.put(waterPathBean, Long.valueOf(j));
        OsList osList = new OsList(table.getUncheckedRow(j), waterPathBeanColumnInfo.mPathColKey);
        osList.removeAll();
        RealmList<String> realmGet$mPath = com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$mPath();
        if (realmGet$mPath != null) {
            Iterator<String> it = realmGet$mPath.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next == null) {
                    osList.addNull();
                } else {
                    osList.addString(next);
                }
            }
        }
        return j;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(WaterPathBean.class);
        long nativePtr = table.getNativePtr();
        WaterPathBeanColumnInfo waterPathBeanColumnInfo = (WaterPathBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) WaterPathBean.class);
        long j2 = waterPathBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            WaterPathBean waterPathBean = (WaterPathBean) it.next();
            if (!map2.containsKey(waterPathBean)) {
                if ((waterPathBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(waterPathBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) waterPathBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(waterPathBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_WaterPathBeanRealmProxyInterface com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface = waterPathBean;
                if (Integer.valueOf(com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$id()) != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j2, (long) com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$id());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j2, Integer.valueOf(com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$id()));
                }
                map2.put(waterPathBean, Long.valueOf(j));
                OsList osList = new OsList(table.getUncheckedRow(j), waterPathBeanColumnInfo.mPathColKey);
                osList.removeAll();
                RealmList<String> realmGet$mPath = com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$mPath();
                if (realmGet$mPath != null) {
                    Iterator<String> it2 = realmGet$mPath.iterator();
                    while (it2.hasNext()) {
                        String next = it2.next();
                        if (next == null) {
                            osList.addNull();
                        } else {
                            osList.addString(next);
                        }
                    }
                }
            }
        }
    }

    public static WaterPathBean createDetachedCopy(WaterPathBean waterPathBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        WaterPathBean waterPathBean2;
        if (i > i2 || waterPathBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(waterPathBean);
        if (cacheData == null) {
            waterPathBean2 = new WaterPathBean();
            map.put(waterPathBean, new RealmObjectProxy.CacheData(i, waterPathBean2));
        } else if (i >= cacheData.minDepth) {
            return (WaterPathBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            waterPathBean2 = (WaterPathBean) cacheData.object;
        }
        com_ciot_realm_db_patrol_WaterPathBeanRealmProxyInterface com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface = waterPathBean2;
        com_ciot_realm_db_patrol_WaterPathBeanRealmProxyInterface com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface2 = waterPathBean;
        com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmSet$id(com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmSet$mPath(new RealmList());
        com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface.realmGet$mPath().addAll(com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface2.realmGet$mPath());
        return waterPathBean2;
    }

    static WaterPathBean update(Realm realm, WaterPathBeanColumnInfo waterPathBeanColumnInfo, WaterPathBean waterPathBean, WaterPathBean waterPathBean2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_patrol_WaterPathBeanRealmProxyInterface com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface = waterPathBean;
        com_ciot_realm_db_patrol_WaterPathBeanRealmProxyInterface com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface2 = waterPathBean2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(WaterPathBean.class), set);
        osObjectBuilder.addInteger(waterPathBeanColumnInfo.idColKey, Integer.valueOf(com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface2.realmGet$id()));
        osObjectBuilder.addStringList(waterPathBeanColumnInfo.mPathColKey, com_ciot_realm_db_patrol_waterpathbeanrealmproxyinterface2.realmGet$mPath());
        osObjectBuilder.updateExistingObject();
        return waterPathBean;
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
        com_ciot_realm_db_patrol_WaterPathBeanRealmProxy com_ciot_realm_db_patrol_waterpathbeanrealmproxy = (com_ciot_realm_db_patrol_WaterPathBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_patrol_waterpathbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_patrol_waterpathbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_patrol_waterpathbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
