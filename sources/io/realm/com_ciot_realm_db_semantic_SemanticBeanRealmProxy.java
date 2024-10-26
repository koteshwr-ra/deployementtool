package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.ad.CycleBean;
import com.ciot.realm.db.semantic.CiotResponseBean;
import com.ciot.realm.db.semantic.SemanticBean;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy;
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

public class com_ciot_realm_db_semantic_SemanticBeanRealmProxy extends SemanticBean implements RealmObjectProxy, com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private SemanticBeanColumnInfo columnInfo;
    private ProxyState<SemanticBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "SemanticBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class SemanticBeanColumnInfo extends ColumnInfo {
        long dataColKey;
        long questionColKey;
        long timeColKey;

        SemanticBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.questionColKey = addColumnDetails("question", "question", objectSchemaInfo);
            this.dataColKey = addColumnDetails("data", "data", objectSchemaInfo);
            this.timeColKey = addColumnDetails(CycleBean.TIME_TYPE, CycleBean.TIME_TYPE, objectSchemaInfo);
        }

        SemanticBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new SemanticBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            SemanticBeanColumnInfo semanticBeanColumnInfo = (SemanticBeanColumnInfo) columnInfo;
            SemanticBeanColumnInfo semanticBeanColumnInfo2 = (SemanticBeanColumnInfo) columnInfo2;
            semanticBeanColumnInfo2.questionColKey = semanticBeanColumnInfo.questionColKey;
            semanticBeanColumnInfo2.dataColKey = semanticBeanColumnInfo.dataColKey;
            semanticBeanColumnInfo2.timeColKey = semanticBeanColumnInfo.timeColKey;
        }
    }

    com_ciot_realm_db_semantic_SemanticBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (SemanticBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<SemanticBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$question() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.questionColKey);
    }

    public void realmSet$question(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'question' cannot be changed after object was created.");
        }
    }

    public CiotResponseBean realmGet$data() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.dataColKey)) {
            return null;
        }
        return (CiotResponseBean) this.proxyState.getRealm$realm().get(CiotResponseBean.class, this.proxyState.getRow$realm().getLink(this.columnInfo.dataColKey), false, Collections.emptyList());
    }

    public void realmSet$data(CiotResponseBean ciotResponseBean) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (ciotResponseBean == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.dataColKey);
                return;
            }
            this.proxyState.checkValidObject(ciotResponseBean);
            this.proxyState.getRow$realm().setLink(this.columnInfo.dataColKey, ((RealmObjectProxy) ciotResponseBean).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("data")) {
            if (ciotResponseBean != null && !RealmObject.isManaged(ciotResponseBean)) {
                ciotResponseBean = (CiotResponseBean) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(ciotResponseBean, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (ciotResponseBean == null) {
                row$realm.nullifyLink(this.columnInfo.dataColKey);
                return;
            }
            this.proxyState.checkValidObject(ciotResponseBean);
            row$realm.getTable().setLink(this.columnInfo.dataColKey, row$realm.getObjectKey(), ((RealmObjectProxy) ciotResponseBean).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public long realmGet$time() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.timeColKey);
    }

    public void realmSet$time(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.timeColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.timeColKey, row$realm.getObjectKey(), j, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 3, 0);
        builder.addPersistedProperty("question", RealmFieldType.STRING, true, false, false);
        builder.addPersistedLinkProperty("data", RealmFieldType.OBJECT, com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedProperty(CycleBean.TIME_TYPE, RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static SemanticBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new SemanticBeanColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.semantic.SemanticBean createOrUpdateUsingJsonObject(io.realm.Realm r14, org.json.JSONObject r15, boolean r16) throws org.json.JSONException {
        /*
            r0 = r14
            r7 = r15
            r8 = r16
            java.util.ArrayList r9 = new java.util.ArrayList
            r10 = 1
            r9.<init>(r10)
            r11 = 0
            java.lang.String r12 = "question"
            if (r8 == 0) goto L_0x006b
            java.lang.Class<com.ciot.realm.db.semantic.SemanticBean> r1 = com.ciot.realm.db.semantic.SemanticBean.class
            io.realm.internal.Table r1 = r14.getTable(r1)
            io.realm.RealmSchema r2 = r14.getSchema()
            java.lang.Class<com.ciot.realm.db.semantic.SemanticBean> r3 = com.ciot.realm.db.semantic.SemanticBean.class
            io.realm.internal.ColumnInfo r2 = r2.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)
            io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxy$SemanticBeanColumnInfo r2 = (io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxy.SemanticBeanColumnInfo) r2
            long r2 = r2.questionColKey
            boolean r4 = r15.isNull(r12)
            if (r4 == 0) goto L_0x002e
            long r2 = r1.findFirstNull(r2)
            goto L_0x0036
        L_0x002e:
            java.lang.String r4 = r15.getString(r12)
            long r2 = r1.findFirstString(r2, r4)
        L_0x0036:
            r4 = -1
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x006b
            io.realm.BaseRealm$ThreadLocalRealmObjectContext r4 = io.realm.BaseRealm.objectContext
            java.lang.Object r4 = r4.get()
            r13 = r4
            io.realm.BaseRealm$RealmObjectContext r13 = (io.realm.BaseRealm.RealmObjectContext) r13
            io.realm.internal.UncheckedRow r3 = r1.getUncheckedRow(r2)     // Catch:{ all -> 0x0066 }
            io.realm.RealmSchema r1 = r14.getSchema()     // Catch:{ all -> 0x0066 }
            java.lang.Class<com.ciot.realm.db.semantic.SemanticBean> r2 = com.ciot.realm.db.semantic.SemanticBean.class
            io.realm.internal.ColumnInfo r4 = r1.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r2)     // Catch:{ all -> 0x0066 }
            r5 = 0
            java.util.List r6 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0066 }
            r1 = r13
            r2 = r14
            r1.set(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0066 }
            io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxy r1 = new io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxy     // Catch:{ all -> 0x0066 }
            r1.<init>()     // Catch:{ all -> 0x0066 }
            r13.clear()
            goto L_0x006c
        L_0x0066:
            r0 = move-exception
            r13.clear()
            throw r0
        L_0x006b:
            r1 = r11
        L_0x006c:
            java.lang.String r2 = "data"
            if (r1 != 0) goto L_0x00a3
            boolean r1 = r15.has(r2)
            if (r1 == 0) goto L_0x0079
            r9.add(r2)
        L_0x0079:
            boolean r1 = r15.has(r12)
            if (r1 == 0) goto L_0x009b
            boolean r1 = r15.isNull(r12)
            if (r1 == 0) goto L_0x008e
            java.lang.Class<com.ciot.realm.db.semantic.SemanticBean> r1 = com.ciot.realm.db.semantic.SemanticBean.class
            io.realm.RealmModel r1 = r14.createObjectInternal(r1, r11, r10, r9)
            io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxy r1 = (io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxy) r1
            goto L_0x00a3
        L_0x008e:
            java.lang.Class<com.ciot.realm.db.semantic.SemanticBean> r1 = com.ciot.realm.db.semantic.SemanticBean.class
            java.lang.String r3 = r15.getString(r12)
            io.realm.RealmModel r1 = r14.createObjectInternal(r1, r3, r10, r9)
            io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxy r1 = (io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxy) r1
            goto L_0x00a3
        L_0x009b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "JSON object doesn't have the primary key field 'question'."
            r0.<init>(r1)
            throw r0
        L_0x00a3:
            r3 = r1
            io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface r3 = (io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface) r3
            boolean r4 = r15.has(r2)
            if (r4 == 0) goto L_0x00c1
            boolean r4 = r15.isNull(r2)
            if (r4 == 0) goto L_0x00b6
            r3.realmSet$data(r11)
            goto L_0x00c1
        L_0x00b6:
            org.json.JSONObject r2 = r15.getJSONObject(r2)
            com.ciot.realm.db.semantic.CiotResponseBean r0 = io.realm.com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.createOrUpdateUsingJsonObject(r14, r2, r8)
            r3.realmSet$data(r0)
        L_0x00c1:
            java.lang.String r0 = "time"
            boolean r2 = r15.has(r0)
            if (r2 == 0) goto L_0x00df
            boolean r2 = r15.isNull(r0)
            if (r2 != 0) goto L_0x00d7
            long r4 = r15.getLong(r0)
            r3.realmSet$time(r4)
            goto L_0x00df
        L_0x00d7:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Trying to set non-nullable field 'time' to null."
            r0.<init>(r1)
            throw r0
        L_0x00df:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.semantic.SemanticBean");
    }

    public static SemanticBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        SemanticBean semanticBean = new SemanticBean();
        com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface = semanticBean;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("question")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmSet$question(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmSet$question((String) null);
                }
                z = true;
            } else if (nextName.equals("data")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmSet$data((CiotResponseBean) null);
                } else {
                    com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmSet$data(com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (!nextName.equals(CycleBean.TIME_TYPE)) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmSet$time(jsonReader.nextLong());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'time' to null.");
            }
        }
        jsonReader.endObject();
        if (z) {
            return (SemanticBean) realm.copyToRealm(semanticBean, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'question'.");
    }

    private static com_ciot_realm_db_semantic_SemanticBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) SemanticBean.class), false, Collections.emptyList());
        com_ciot_realm_db_semantic_SemanticBeanRealmProxy com_ciot_realm_db_semantic_semanticbeanrealmproxy = new com_ciot_realm_db_semantic_SemanticBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_semantic_semanticbeanrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.semantic.SemanticBean copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxy.SemanticBeanColumnInfo r9, com.ciot.realm.db.semantic.SemanticBean r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.semantic.SemanticBean r1 = (com.ciot.realm.db.semantic.SemanticBean) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.semantic.SemanticBean> r2 = com.ciot.realm.db.semantic.SemanticBean.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.questionColKey
            r5 = r10
            io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface) r5
            java.lang.String r5 = r5.realmGet$question()
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
            io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxy r1 = new io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.semantic.SemanticBean r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.semantic.SemanticBean r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_semantic_SemanticBeanRealmProxy$SemanticBeanColumnInfo, com.ciot.realm.db.semantic.SemanticBean, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.semantic.SemanticBean");
    }

    public static SemanticBean copy(Realm realm, SemanticBeanColumnInfo semanticBeanColumnInfo, SemanticBean semanticBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(semanticBean);
        if (realmObjectProxy != null) {
            return (SemanticBean) realmObjectProxy;
        }
        com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface = semanticBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(SemanticBean.class), set);
        osObjectBuilder.addString(semanticBeanColumnInfo.questionColKey, com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmGet$question());
        osObjectBuilder.addInteger(semanticBeanColumnInfo.timeColKey, Long.valueOf(com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmGet$time()));
        com_ciot_realm_db_semantic_SemanticBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(semanticBean, newProxyInstance);
        CiotResponseBean realmGet$data = com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmGet$data();
        if (realmGet$data == null) {
            newProxyInstance.realmSet$data((CiotResponseBean) null);
        } else {
            CiotResponseBean ciotResponseBean = (CiotResponseBean) map.get(realmGet$data);
            if (ciotResponseBean != null) {
                newProxyInstance.realmSet$data(ciotResponseBean);
            } else {
                newProxyInstance.realmSet$data(com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.CiotResponseBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CiotResponseBean.class), realmGet$data, z, map, set));
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, SemanticBean semanticBean, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        SemanticBean semanticBean2 = semanticBean;
        Map<RealmModel, Long> map2 = map;
        if ((semanticBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(semanticBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) semanticBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(SemanticBean.class);
        long nativePtr = table.getNativePtr();
        SemanticBeanColumnInfo semanticBeanColumnInfo = (SemanticBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) SemanticBean.class);
        long j2 = semanticBeanColumnInfo.questionColKey;
        com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface = semanticBean2;
        String realmGet$question = com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmGet$question();
        if (realmGet$question == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$question);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$question);
        } else {
            Table.throwDuplicatePrimaryKeyException(realmGet$question);
        }
        long j3 = j;
        map2.put(semanticBean2, Long.valueOf(j3));
        CiotResponseBean realmGet$data = com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmGet$data();
        if (realmGet$data != null) {
            Long l = map2.get(realmGet$data);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.insert(realm2, realmGet$data, map2));
            }
            Table.nativeSetLink(nativePtr, semanticBeanColumnInfo.dataColKey, j3, l.longValue(), false);
        }
        Table.nativeSetLong(nativePtr, semanticBeanColumnInfo.timeColKey, j3, com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmGet$time(), false);
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(SemanticBean.class);
        long nativePtr = table.getNativePtr();
        SemanticBeanColumnInfo semanticBeanColumnInfo = (SemanticBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) SemanticBean.class);
        long j4 = semanticBeanColumnInfo.questionColKey;
        while (it.hasNext()) {
            SemanticBean semanticBean = (SemanticBean) it.next();
            if (!map2.containsKey(semanticBean)) {
                if ((semanticBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(semanticBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) semanticBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(semanticBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface = semanticBean;
                String realmGet$question = com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmGet$question();
                if (realmGet$question == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j4);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j4, realmGet$question);
                }
                if (j == -1) {
                    j2 = OsObject.createRowWithPrimaryKey(table, j4, realmGet$question);
                } else {
                    Table.throwDuplicatePrimaryKeyException(realmGet$question);
                    j2 = j;
                }
                map2.put(semanticBean, Long.valueOf(j2));
                CiotResponseBean realmGet$data = com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmGet$data();
                if (realmGet$data != null) {
                    Long l = map2.get(realmGet$data);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.insert(realm2, realmGet$data, map2));
                    }
                    j3 = j4;
                    table.setLink(semanticBeanColumnInfo.dataColKey, j2, l.longValue(), false);
                } else {
                    j3 = j4;
                }
                Table.nativeSetLong(nativePtr, semanticBeanColumnInfo.timeColKey, j2, com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmGet$time(), false);
                j4 = j3;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, SemanticBean semanticBean, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        SemanticBean semanticBean2 = semanticBean;
        Map<RealmModel, Long> map2 = map;
        if ((semanticBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(semanticBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) semanticBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(SemanticBean.class);
        long nativePtr = table.getNativePtr();
        SemanticBeanColumnInfo semanticBeanColumnInfo = (SemanticBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) SemanticBean.class);
        long j2 = semanticBeanColumnInfo.questionColKey;
        com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface = semanticBean2;
        String realmGet$question = com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmGet$question();
        if (realmGet$question == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$question);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$question);
        }
        long j3 = j;
        map2.put(semanticBean2, Long.valueOf(j3));
        CiotResponseBean realmGet$data = com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmGet$data();
        if (realmGet$data != null) {
            Long l = map2.get(realmGet$data);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.insertOrUpdate(realm2, realmGet$data, map2));
            }
            Table.nativeSetLink(nativePtr, semanticBeanColumnInfo.dataColKey, j3, l.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, semanticBeanColumnInfo.dataColKey, j3);
        }
        Table.nativeSetLong(nativePtr, semanticBeanColumnInfo.timeColKey, j3, com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmGet$time(), false);
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(SemanticBean.class);
        long nativePtr = table.getNativePtr();
        SemanticBeanColumnInfo semanticBeanColumnInfo = (SemanticBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) SemanticBean.class);
        long j3 = semanticBeanColumnInfo.questionColKey;
        while (it.hasNext()) {
            SemanticBean semanticBean = (SemanticBean) it.next();
            if (!map2.containsKey(semanticBean)) {
                if ((semanticBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(semanticBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) semanticBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(semanticBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface = semanticBean;
                String realmGet$question = com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmGet$question();
                if (realmGet$question == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j3);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j3, realmGet$question);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j3, realmGet$question) : j;
                map2.put(semanticBean, Long.valueOf(createRowWithPrimaryKey));
                CiotResponseBean realmGet$data = com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmGet$data();
                if (realmGet$data != null) {
                    Long l = map2.get(realmGet$data);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.insertOrUpdate(realm2, realmGet$data, map2));
                    }
                    j2 = j3;
                    Table.nativeSetLink(nativePtr, semanticBeanColumnInfo.dataColKey, createRowWithPrimaryKey, l.longValue(), false);
                } else {
                    j2 = j3;
                    Table.nativeNullifyLink(nativePtr, semanticBeanColumnInfo.dataColKey, createRowWithPrimaryKey);
                }
                Table.nativeSetLong(nativePtr, semanticBeanColumnInfo.timeColKey, createRowWithPrimaryKey, com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmGet$time(), false);
                j3 = j2;
            }
        }
    }

    public static SemanticBean createDetachedCopy(SemanticBean semanticBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        SemanticBean semanticBean2;
        if (i > i2 || semanticBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(semanticBean);
        if (cacheData == null) {
            semanticBean2 = new SemanticBean();
            map.put(semanticBean, new RealmObjectProxy.CacheData(i, semanticBean2));
        } else if (i >= cacheData.minDepth) {
            return (SemanticBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            semanticBean2 = (SemanticBean) cacheData.object;
        }
        com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface = semanticBean2;
        com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface2 = semanticBean;
        com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmSet$question(com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface2.realmGet$question());
        com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmSet$data(com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.createDetachedCopy(com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface2.realmGet$data(), i + 1, i2, map));
        com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface.realmSet$time(com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface2.realmGet$time());
        return semanticBean2;
    }

    static SemanticBean update(Realm realm, SemanticBeanColumnInfo semanticBeanColumnInfo, SemanticBean semanticBean, SemanticBean semanticBean2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface = semanticBean;
        com_ciot_realm_db_semantic_SemanticBeanRealmProxyInterface com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface2 = semanticBean2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(SemanticBean.class), set);
        osObjectBuilder.addString(semanticBeanColumnInfo.questionColKey, com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface2.realmGet$question());
        CiotResponseBean realmGet$data = com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface2.realmGet$data();
        if (realmGet$data == null) {
            osObjectBuilder.addNull(semanticBeanColumnInfo.dataColKey);
        } else {
            CiotResponseBean ciotResponseBean = (CiotResponseBean) map.get(realmGet$data);
            if (ciotResponseBean != null) {
                osObjectBuilder.addObject(semanticBeanColumnInfo.dataColKey, ciotResponseBean);
            } else {
                osObjectBuilder.addObject(semanticBeanColumnInfo.dataColKey, com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy.CiotResponseBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CiotResponseBean.class), realmGet$data, true, map, set));
            }
        }
        osObjectBuilder.addInteger(semanticBeanColumnInfo.timeColKey, Long.valueOf(com_ciot_realm_db_semantic_semanticbeanrealmproxyinterface2.realmGet$time()));
        osObjectBuilder.updateExistingObject();
        return semanticBean;
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
        com_ciot_realm_db_semantic_SemanticBeanRealmProxy com_ciot_realm_db_semantic_semanticbeanrealmproxy = (com_ciot_realm_db_semantic_SemanticBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_semantic_semanticbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_semantic_semanticbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_semantic_semanticbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
