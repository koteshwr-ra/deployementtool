package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.recommantation.DataBean;
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

public class com_ciot_realm_db_recommantation_DataBeanRealmProxy extends DataBean implements RealmObjectProxy, com_ciot_realm_db_recommantation_DataBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private DataBeanColumnInfo columnInfo;
    private ProxyState<DataBean> proxyState;
    private RealmList<String> recommendationsRealmList;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "DataBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class DataBeanColumnInfo extends ColumnInfo {
        long idColKey;
        long recommendationsColKey;

        DataBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.recommendationsColKey = addColumnDetails("recommendations", "recommendations", objectSchemaInfo);
        }

        DataBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new DataBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            DataBeanColumnInfo dataBeanColumnInfo = (DataBeanColumnInfo) columnInfo;
            DataBeanColumnInfo dataBeanColumnInfo2 = (DataBeanColumnInfo) columnInfo2;
            dataBeanColumnInfo2.idColKey = dataBeanColumnInfo.idColKey;
            dataBeanColumnInfo2.recommendationsColKey = dataBeanColumnInfo.recommendationsColKey;
        }
    }

    com_ciot_realm_db_recommantation_DataBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (DataBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<DataBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public long realmGet$id() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.idColKey);
    }

    public void realmSet$id(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'id' cannot be changed after object was created.");
        }
    }

    public RealmList<String> realmGet$recommendations() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<String> realmList = this.recommendationsRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<String> realmList2 = new RealmList<>(String.class, this.proxyState.getRow$realm().getValueList(this.columnInfo.recommendationsColKey, RealmFieldType.STRING_LIST), this.proxyState.getRealm$realm());
        this.recommendationsRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$recommendations(RealmList<String> realmList) {
        if (!this.proxyState.isUnderConstruction() || (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("recommendations"))) {
            this.proxyState.getRealm$realm().checkIfValid();
            OsList valueList = this.proxyState.getRow$realm().getValueList(this.columnInfo.recommendationsColKey, RealmFieldType.STRING_LIST);
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
        builder.addPersistedValueListProperty("recommendations", RealmFieldType.STRING_LIST, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static DataBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new DataBeanColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r13v5, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r13v6, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0068  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.recommantation.DataBean createOrUpdateUsingJsonObject(io.realm.Realm r13, org.json.JSONObject r14, boolean r15) throws org.json.JSONException {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 1
            r0.<init>(r1)
            r2 = 0
            java.lang.String r3 = "id"
            if (r15 == 0) goto L_0x0063
            java.lang.Class<com.ciot.realm.db.recommantation.DataBean> r15 = com.ciot.realm.db.recommantation.DataBean.class
            io.realm.internal.Table r15 = r13.getTable(r15)
            io.realm.RealmSchema r4 = r13.getSchema()
            java.lang.Class<com.ciot.realm.db.recommantation.DataBean> r5 = com.ciot.realm.db.recommantation.DataBean.class
            io.realm.internal.ColumnInfo r4 = r4.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r5)
            io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxy$DataBeanColumnInfo r4 = (io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxy.DataBeanColumnInfo) r4
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
            java.lang.Class<com.ciot.realm.db.recommantation.DataBean> r4 = com.ciot.realm.db.recommantation.DataBean.class
            io.realm.internal.ColumnInfo r10 = r15.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)     // Catch:{ all -> 0x005e }
            r11 = 0
            java.util.List r12 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005e }
            r7 = r6
            r8 = r13
            r7.set(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x005e }
            io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxy r15 = new io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxy     // Catch:{ all -> 0x005e }
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
            java.lang.String r4 = "recommendations"
            if (r15 != 0) goto L_0x00a1
            boolean r15 = r14.has(r4)
            if (r15 == 0) goto L_0x0071
            r0.add(r4)
        L_0x0071:
            boolean r15 = r14.has(r3)
            if (r15 == 0) goto L_0x0099
            boolean r15 = r14.isNull(r3)
            if (r15 == 0) goto L_0x0087
            java.lang.Class<com.ciot.realm.db.recommantation.DataBean> r15 = com.ciot.realm.db.recommantation.DataBean.class
            io.realm.RealmModel r13 = r13.createObjectInternal(r15, r2, r1, r0)
            r15 = r13
            io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxy r15 = (io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxy) r15
            goto L_0x00a1
        L_0x0087:
            java.lang.Class<com.ciot.realm.db.recommantation.DataBean> r15 = com.ciot.realm.db.recommantation.DataBean.class
            long r2 = r14.getLong(r3)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            io.realm.RealmModel r13 = r13.createObjectInternal(r15, r2, r1, r0)
            r15 = r13
            io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxy r15 = (io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxy) r15
            goto L_0x00a1
        L_0x0099:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "JSON object doesn't have the primary key field 'id'."
            r13.<init>(r14)
            throw r13
        L_0x00a1:
            r13 = r15
            io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxyInterface r13 = (io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxyInterface) r13
            io.realm.RealmList r13 = r13.realmGet$recommendations()
            io.realm.ProxyUtils.setRealmListWithJsonObject(r13, r14, r4)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.recommantation.DataBean");
    }

    public static DataBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        DataBean dataBean = new DataBean();
        com_ciot_realm_db_recommantation_DataBeanRealmProxyInterface com_ciot_realm_db_recommantation_databeanrealmproxyinterface = dataBean;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmSet$id(jsonReader.nextLong());
                    z = true;
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                }
            } else if (nextName.equals("recommendations")) {
                com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmSet$recommendations(ProxyUtils.createRealmListWithJsonStream(String.class, jsonReader));
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        if (z) {
            return (DataBean) realm.copyToRealm(dataBean, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_recommantation_DataBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) DataBean.class), false, Collections.emptyList());
        com_ciot_realm_db_recommantation_DataBeanRealmProxy com_ciot_realm_db_recommantation_databeanrealmproxy = new com_ciot_realm_db_recommantation_DataBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_recommantation_databeanrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.recommantation.DataBean copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxy.DataBeanColumnInfo r9, com.ciot.realm.db.recommantation.DataBean r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.recommantation.DataBean r1 = (com.ciot.realm.db.recommantation.DataBean) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0092
            java.lang.Class<com.ciot.realm.db.recommantation.DataBean> r2 = com.ciot.realm.db.recommantation.DataBean.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxyInterface) r5
            long r5 = r5.realmGet$id()
            long r3 = r2.findFirstLong(r3, r5)
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x006f
            r0 = 0
            goto L_0x0093
        L_0x006f:
            io.realm.internal.UncheckedRow r3 = r2.getUncheckedRow(r3)     // Catch:{ all -> 0x008d }
            r5 = 0
            java.util.List r6 = java.util.Collections.emptyList()     // Catch:{ all -> 0x008d }
            r1 = r0
            r2 = r8
            r4 = r9
            r1.set(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x008d }
            io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxy r1 = new io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxy     // Catch:{ all -> 0x008d }
            r1.<init>()     // Catch:{ all -> 0x008d }
            r2 = r1
            io.realm.internal.RealmObjectProxy r2 = (io.realm.internal.RealmObjectProxy) r2     // Catch:{ all -> 0x008d }
            r12.put(r10, r2)     // Catch:{ all -> 0x008d }
            r0.clear()
            goto L_0x0092
        L_0x008d:
            r8 = move-exception
            r0.clear()
            throw r8
        L_0x0092:
            r0 = r11
        L_0x0093:
            r3 = r1
            if (r0 == 0) goto L_0x00a0
            r1 = r8
            r2 = r9
            r4 = r10
            r5 = r12
            r6 = r13
            com.ciot.realm.db.recommantation.DataBean r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00a4
        L_0x00a0:
            com.ciot.realm.db.recommantation.DataBean r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00a4:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_recommantation_DataBeanRealmProxy$DataBeanColumnInfo, com.ciot.realm.db.recommantation.DataBean, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.recommantation.DataBean");
    }

    public static DataBean copy(Realm realm, DataBeanColumnInfo dataBeanColumnInfo, DataBean dataBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(dataBean);
        if (realmObjectProxy != null) {
            return (DataBean) realmObjectProxy;
        }
        com_ciot_realm_db_recommantation_DataBeanRealmProxyInterface com_ciot_realm_db_recommantation_databeanrealmproxyinterface = dataBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(DataBean.class), set);
        osObjectBuilder.addInteger(dataBeanColumnInfo.idColKey, Long.valueOf(com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$id()));
        osObjectBuilder.addStringList(dataBeanColumnInfo.recommendationsColKey, com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$recommendations());
        com_ciot_realm_db_recommantation_DataBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(dataBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, DataBean dataBean, Map<RealmModel, Long> map) {
        long j;
        if ((dataBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(dataBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) dataBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(DataBean.class);
        long nativePtr = table.getNativePtr();
        DataBeanColumnInfo dataBeanColumnInfo = (DataBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) DataBean.class);
        long j2 = dataBeanColumnInfo.idColKey;
        com_ciot_realm_db_recommantation_DataBeanRealmProxyInterface com_ciot_realm_db_recommantation_databeanrealmproxyinterface = dataBean;
        Long valueOf = Long.valueOf(com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$id());
        if (valueOf != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$id());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$id()));
        } else {
            Table.throwDuplicatePrimaryKeyException(valueOf);
        }
        map.put(dataBean, Long.valueOf(j));
        RealmList<String> realmGet$recommendations = com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$recommendations();
        if (realmGet$recommendations != null) {
            OsList osList = new OsList(table.getUncheckedRow(j), dataBeanColumnInfo.recommendationsColKey);
            Iterator<String> it = realmGet$recommendations.iterator();
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
        Table table = realm.getTable(DataBean.class);
        long nativePtr = table.getNativePtr();
        DataBeanColumnInfo dataBeanColumnInfo = (DataBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) DataBean.class);
        long j2 = dataBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            DataBean dataBean = (DataBean) it.next();
            if (!map2.containsKey(dataBean)) {
                if ((dataBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(dataBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) dataBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(dataBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_recommantation_DataBeanRealmProxyInterface com_ciot_realm_db_recommantation_databeanrealmproxyinterface = dataBean;
                Long valueOf = Long.valueOf(com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$id());
                if (valueOf != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$id());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$id()));
                } else {
                    Table.throwDuplicatePrimaryKeyException(valueOf);
                }
                map2.put(dataBean, Long.valueOf(j));
                RealmList<String> realmGet$recommendations = com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$recommendations();
                if (realmGet$recommendations != null) {
                    OsList osList = new OsList(table.getUncheckedRow(j), dataBeanColumnInfo.recommendationsColKey);
                    Iterator<String> it2 = realmGet$recommendations.iterator();
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

    public static long insertOrUpdate(Realm realm, DataBean dataBean, Map<RealmModel, Long> map) {
        long j;
        if ((dataBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(dataBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) dataBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(DataBean.class);
        long nativePtr = table.getNativePtr();
        DataBeanColumnInfo dataBeanColumnInfo = (DataBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) DataBean.class);
        long j2 = dataBeanColumnInfo.idColKey;
        com_ciot_realm_db_recommantation_DataBeanRealmProxyInterface com_ciot_realm_db_recommantation_databeanrealmproxyinterface = dataBean;
        if (Long.valueOf(com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$id()) != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$id());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$id()));
        }
        map.put(dataBean, Long.valueOf(j));
        OsList osList = new OsList(table.getUncheckedRow(j), dataBeanColumnInfo.recommendationsColKey);
        osList.removeAll();
        RealmList<String> realmGet$recommendations = com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$recommendations();
        if (realmGet$recommendations != null) {
            Iterator<String> it = realmGet$recommendations.iterator();
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
        Table table = realm.getTable(DataBean.class);
        long nativePtr = table.getNativePtr();
        DataBeanColumnInfo dataBeanColumnInfo = (DataBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) DataBean.class);
        long j2 = dataBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            DataBean dataBean = (DataBean) it.next();
            if (!map2.containsKey(dataBean)) {
                if ((dataBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(dataBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) dataBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(dataBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_recommantation_DataBeanRealmProxyInterface com_ciot_realm_db_recommantation_databeanrealmproxyinterface = dataBean;
                if (Long.valueOf(com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$id()) != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$id());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$id()));
                }
                map2.put(dataBean, Long.valueOf(j));
                OsList osList = new OsList(table.getUncheckedRow(j), dataBeanColumnInfo.recommendationsColKey);
                osList.removeAll();
                RealmList<String> realmGet$recommendations = com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$recommendations();
                if (realmGet$recommendations != null) {
                    Iterator<String> it2 = realmGet$recommendations.iterator();
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

    public static DataBean createDetachedCopy(DataBean dataBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        DataBean dataBean2;
        if (i > i2 || dataBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(dataBean);
        if (cacheData == null) {
            dataBean2 = new DataBean();
            map.put(dataBean, new RealmObjectProxy.CacheData(i, dataBean2));
        } else if (i >= cacheData.minDepth) {
            return (DataBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            dataBean2 = (DataBean) cacheData.object;
        }
        com_ciot_realm_db_recommantation_DataBeanRealmProxyInterface com_ciot_realm_db_recommantation_databeanrealmproxyinterface = dataBean2;
        com_ciot_realm_db_recommantation_DataBeanRealmProxyInterface com_ciot_realm_db_recommantation_databeanrealmproxyinterface2 = dataBean;
        com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmSet$id(com_ciot_realm_db_recommantation_databeanrealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmSet$recommendations(new RealmList());
        com_ciot_realm_db_recommantation_databeanrealmproxyinterface.realmGet$recommendations().addAll(com_ciot_realm_db_recommantation_databeanrealmproxyinterface2.realmGet$recommendations());
        return dataBean2;
    }

    static DataBean update(Realm realm, DataBeanColumnInfo dataBeanColumnInfo, DataBean dataBean, DataBean dataBean2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_recommantation_DataBeanRealmProxyInterface com_ciot_realm_db_recommantation_databeanrealmproxyinterface = dataBean;
        com_ciot_realm_db_recommantation_DataBeanRealmProxyInterface com_ciot_realm_db_recommantation_databeanrealmproxyinterface2 = dataBean2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(DataBean.class), set);
        osObjectBuilder.addInteger(dataBeanColumnInfo.idColKey, Long.valueOf(com_ciot_realm_db_recommantation_databeanrealmproxyinterface2.realmGet$id()));
        osObjectBuilder.addStringList(dataBeanColumnInfo.recommendationsColKey, com_ciot_realm_db_recommantation_databeanrealmproxyinterface2.realmGet$recommendations());
        osObjectBuilder.updateExistingObject();
        return dataBean;
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
        com_ciot_realm_db_recommantation_DataBeanRealmProxy com_ciot_realm_db_recommantation_databeanrealmproxy = (com_ciot_realm_db_recommantation_DataBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_recommantation_databeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_recommantation_databeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_recommantation_databeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
