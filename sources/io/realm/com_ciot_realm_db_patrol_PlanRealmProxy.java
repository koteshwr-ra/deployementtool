package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.patrol.Plan;
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

public class com_ciot_realm_db_patrol_PlanRealmProxy extends Plan implements RealmObjectProxy, com_ciot_realm_db_patrol_PlanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private PlanColumnInfo columnInfo;
    private RealmList<Integer> dateRealmList;
    private ProxyState<Plan> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Plan";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class PlanColumnInfo extends ColumnInfo {
        long createColKey;
        long cycleColKey;
        long dateColKey;
        long endColKey;
        long startColKey;

        PlanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(5);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.cycleColKey = addColumnDetails("cycle", "cycle", objectSchemaInfo);
            this.startColKey = addColumnDetails("start", "start", objectSchemaInfo);
            this.endColKey = addColumnDetails("end", "end", objectSchemaInfo);
            this.createColKey = addColumnDetails("create", "create", objectSchemaInfo);
            this.dateColKey = addColumnDetails("date", "date", objectSchemaInfo);
        }

        PlanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new PlanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            PlanColumnInfo planColumnInfo = (PlanColumnInfo) columnInfo;
            PlanColumnInfo planColumnInfo2 = (PlanColumnInfo) columnInfo2;
            planColumnInfo2.cycleColKey = planColumnInfo.cycleColKey;
            planColumnInfo2.startColKey = planColumnInfo.startColKey;
            planColumnInfo2.endColKey = planColumnInfo.endColKey;
            planColumnInfo2.createColKey = planColumnInfo.createColKey;
            planColumnInfo2.dateColKey = planColumnInfo.dateColKey;
        }
    }

    com_ciot_realm_db_patrol_PlanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (PlanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<Plan> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$cycle() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.cycleColKey);
    }

    public void realmSet$cycle(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.cycleColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.cycleColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.cycleColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.cycleColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$start() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.startColKey);
    }

    public void realmSet$start(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.startColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.startColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.startColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.startColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$end() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.endColKey);
    }

    public void realmSet$end(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.endColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.endColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.endColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.endColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public long realmGet$create() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.createColKey);
    }

    public void realmSet$create(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'create' cannot be changed after object was created.");
        }
    }

    public RealmList<Integer> realmGet$date() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<Integer> realmList = this.dateRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<Integer> realmList2 = new RealmList<>(Integer.class, this.proxyState.getRow$realm().getValueList(this.columnInfo.dateColKey, RealmFieldType.INTEGER_LIST), this.proxyState.getRealm$realm());
        this.dateRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$date(RealmList<Integer> realmList) {
        if (!this.proxyState.isUnderConstruction() || (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("date"))) {
            this.proxyState.getRealm$realm().checkIfValid();
            OsList valueList = this.proxyState.getRow$realm().getValueList(this.columnInfo.dateColKey, RealmFieldType.INTEGER_LIST);
            valueList.removeAll();
            if (realmList != null) {
                Iterator<Integer> it = realmList.iterator();
                while (it.hasNext()) {
                    Integer next = it.next();
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
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 5, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("cycle", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("start", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("end", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("create", RealmFieldType.INTEGER, true, true, true);
        builder.addPersistedValueListProperty("date", RealmFieldType.INTEGER_LIST, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static PlanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new PlanColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r13v5, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r13v6, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00de  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.Plan createOrUpdateUsingJsonObject(io.realm.Realm r13, org.json.JSONObject r14, boolean r15) throws org.json.JSONException {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 1
            r0.<init>(r1)
            java.lang.String r2 = "create"
            r3 = 0
            if (r15 == 0) goto L_0x0063
            java.lang.Class<com.ciot.realm.db.patrol.Plan> r15 = com.ciot.realm.db.patrol.Plan.class
            io.realm.internal.Table r15 = r13.getTable(r15)
            io.realm.RealmSchema r4 = r13.getSchema()
            java.lang.Class<com.ciot.realm.db.patrol.Plan> r5 = com.ciot.realm.db.patrol.Plan.class
            io.realm.internal.ColumnInfo r4 = r4.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r5)
            io.realm.com_ciot_realm_db_patrol_PlanRealmProxy$PlanColumnInfo r4 = (io.realm.com_ciot_realm_db_patrol_PlanRealmProxy.PlanColumnInfo) r4
            long r4 = r4.createColKey
            boolean r6 = r14.isNull(r2)
            r7 = -1
            if (r6 != 0) goto L_0x0030
            long r9 = r14.getLong(r2)
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
            java.lang.Class<com.ciot.realm.db.patrol.Plan> r4 = com.ciot.realm.db.patrol.Plan.class
            io.realm.internal.ColumnInfo r10 = r15.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)     // Catch:{ all -> 0x005e }
            r11 = 0
            java.util.List r12 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005e }
            r7 = r6
            r8 = r13
            r7.set(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x005e }
            io.realm.com_ciot_realm_db_patrol_PlanRealmProxy r15 = new io.realm.com_ciot_realm_db_patrol_PlanRealmProxy     // Catch:{ all -> 0x005e }
            r15.<init>()     // Catch:{ all -> 0x005e }
            r6.clear()
            goto L_0x0064
        L_0x005e:
            r13 = move-exception
            r6.clear()
            throw r13
        L_0x0063:
            r15 = r3
        L_0x0064:
            java.lang.String r4 = "date"
            if (r15 != 0) goto L_0x00a1
            boolean r15 = r14.has(r4)
            if (r15 == 0) goto L_0x0071
            r0.add(r4)
        L_0x0071:
            boolean r15 = r14.has(r2)
            if (r15 == 0) goto L_0x0099
            boolean r15 = r14.isNull(r2)
            if (r15 == 0) goto L_0x0087
            java.lang.Class<com.ciot.realm.db.patrol.Plan> r15 = com.ciot.realm.db.patrol.Plan.class
            io.realm.RealmModel r13 = r13.createObjectInternal(r15, r3, r1, r0)
            r15 = r13
            io.realm.com_ciot_realm_db_patrol_PlanRealmProxy r15 = (io.realm.com_ciot_realm_db_patrol_PlanRealmProxy) r15
            goto L_0x00a1
        L_0x0087:
            java.lang.Class<com.ciot.realm.db.patrol.Plan> r15 = com.ciot.realm.db.patrol.Plan.class
            long r5 = r14.getLong(r2)
            java.lang.Long r2 = java.lang.Long.valueOf(r5)
            io.realm.RealmModel r13 = r13.createObjectInternal(r15, r2, r1, r0)
            r15 = r13
            io.realm.com_ciot_realm_db_patrol_PlanRealmProxy r15 = (io.realm.com_ciot_realm_db_patrol_PlanRealmProxy) r15
            goto L_0x00a1
        L_0x0099:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "JSON object doesn't have the primary key field 'create'."
            r13.<init>(r14)
            throw r13
        L_0x00a1:
            r13 = r15
            io.realm.com_ciot_realm_db_patrol_PlanRealmProxyInterface r13 = (io.realm.com_ciot_realm_db_patrol_PlanRealmProxyInterface) r13
            java.lang.String r0 = "cycle"
            boolean r1 = r14.has(r0)
            if (r1 == 0) goto L_0x00bd
            boolean r1 = r14.isNull(r0)
            if (r1 == 0) goto L_0x00b6
            r13.realmSet$cycle(r3)
            goto L_0x00bd
        L_0x00b6:
            java.lang.String r0 = r14.getString(r0)
            r13.realmSet$cycle(r0)
        L_0x00bd:
            java.lang.String r0 = "start"
            boolean r1 = r14.has(r0)
            if (r1 == 0) goto L_0x00d6
            boolean r1 = r14.isNull(r0)
            if (r1 == 0) goto L_0x00cf
            r13.realmSet$start(r3)
            goto L_0x00d6
        L_0x00cf:
            java.lang.String r0 = r14.getString(r0)
            r13.realmSet$start(r0)
        L_0x00d6:
            java.lang.String r0 = "end"
            boolean r1 = r14.has(r0)
            if (r1 == 0) goto L_0x00ef
            boolean r1 = r14.isNull(r0)
            if (r1 == 0) goto L_0x00e8
            r13.realmSet$end(r3)
            goto L_0x00ef
        L_0x00e8:
            java.lang.String r0 = r14.getString(r0)
            r13.realmSet$end(r0)
        L_0x00ef:
            io.realm.RealmList r13 = r13.realmGet$date()
            io.realm.ProxyUtils.setRealmListWithJsonObject(r13, r14, r4)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_PlanRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.patrol.Plan");
    }

    public static Plan createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        Plan plan = new Plan();
        com_ciot_realm_db_patrol_PlanRealmProxyInterface com_ciot_realm_db_patrol_planrealmproxyinterface = plan;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("cycle")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_planrealmproxyinterface.realmSet$cycle(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_planrealmproxyinterface.realmSet$cycle((String) null);
                }
            } else if (nextName.equals("start")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_planrealmproxyinterface.realmSet$start(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_planrealmproxyinterface.realmSet$start((String) null);
                }
            } else if (nextName.equals("end")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_planrealmproxyinterface.realmSet$end(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_planrealmproxyinterface.realmSet$end((String) null);
                }
            } else if (nextName.equals("create")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_planrealmproxyinterface.realmSet$create(jsonReader.nextLong());
                    z = true;
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'create' to null.");
                }
            } else if (nextName.equals("date")) {
                com_ciot_realm_db_patrol_planrealmproxyinterface.realmSet$date(ProxyUtils.createRealmListWithJsonStream(Integer.class, jsonReader));
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        if (z) {
            return (Plan) realm.copyToRealm(plan, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'create'.");
    }

    private static com_ciot_realm_db_patrol_PlanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) Plan.class), false, Collections.emptyList());
        com_ciot_realm_db_patrol_PlanRealmProxy com_ciot_realm_db_patrol_planrealmproxy = new com_ciot_realm_db_patrol_PlanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_patrol_planrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.Plan copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_patrol_PlanRealmProxy.PlanColumnInfo r9, com.ciot.realm.db.patrol.Plan r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.patrol.Plan r1 = (com.ciot.realm.db.patrol.Plan) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0092
            java.lang.Class<com.ciot.realm.db.patrol.Plan> r2 = com.ciot.realm.db.patrol.Plan.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.createColKey
            r5 = r10
            io.realm.com_ciot_realm_db_patrol_PlanRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_patrol_PlanRealmProxyInterface) r5
            long r5 = r5.realmGet$create()
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
            io.realm.com_ciot_realm_db_patrol_PlanRealmProxy r1 = new io.realm.com_ciot_realm_db_patrol_PlanRealmProxy     // Catch:{ all -> 0x008d }
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
            com.ciot.realm.db.patrol.Plan r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00a4
        L_0x00a0:
            com.ciot.realm.db.patrol.Plan r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00a4:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_PlanRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_patrol_PlanRealmProxy$PlanColumnInfo, com.ciot.realm.db.patrol.Plan, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.patrol.Plan");
    }

    public static Plan copy(Realm realm, PlanColumnInfo planColumnInfo, Plan plan, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(plan);
        if (realmObjectProxy != null) {
            return (Plan) realmObjectProxy;
        }
        com_ciot_realm_db_patrol_PlanRealmProxyInterface com_ciot_realm_db_patrol_planrealmproxyinterface = plan;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Plan.class), set);
        osObjectBuilder.addString(planColumnInfo.cycleColKey, com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$cycle());
        osObjectBuilder.addString(planColumnInfo.startColKey, com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$start());
        osObjectBuilder.addString(planColumnInfo.endColKey, com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$end());
        osObjectBuilder.addInteger(planColumnInfo.createColKey, Long.valueOf(com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$create()));
        osObjectBuilder.addIntegerList(planColumnInfo.dateColKey, com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$date());
        com_ciot_realm_db_patrol_PlanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(plan, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, Plan plan, Map<RealmModel, Long> map) {
        long j;
        Plan plan2 = plan;
        if ((plan2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(plan)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) plan2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Plan.class);
        long nativePtr = table.getNativePtr();
        PlanColumnInfo planColumnInfo = (PlanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Plan.class);
        long j2 = planColumnInfo.createColKey;
        com_ciot_realm_db_patrol_PlanRealmProxyInterface com_ciot_realm_db_patrol_planrealmproxyinterface = plan2;
        Long valueOf = Long.valueOf(com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$create());
        if (valueOf != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$create());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$create()));
        } else {
            Table.throwDuplicatePrimaryKeyException(valueOf);
        }
        long j3 = j;
        map.put(plan2, Long.valueOf(j3));
        String realmGet$cycle = com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$cycle();
        if (realmGet$cycle != null) {
            Table.nativeSetString(nativePtr, planColumnInfo.cycleColKey, j3, realmGet$cycle, false);
        }
        String realmGet$start = com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$start();
        if (realmGet$start != null) {
            Table.nativeSetString(nativePtr, planColumnInfo.startColKey, j3, realmGet$start, false);
        }
        String realmGet$end = com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$end();
        if (realmGet$end != null) {
            Table.nativeSetString(nativePtr, planColumnInfo.endColKey, j3, realmGet$end, false);
        }
        RealmList<Integer> realmGet$date = com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$date();
        if (realmGet$date != null) {
            OsList osList = new OsList(table.getUncheckedRow(j3), planColumnInfo.dateColKey);
            Iterator<Integer> it = realmGet$date.iterator();
            while (it.hasNext()) {
                Integer next = it.next();
                if (next == null) {
                    osList.addNull();
                } else {
                    osList.addLong(next.longValue());
                }
            }
        }
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Plan.class);
        long nativePtr = table.getNativePtr();
        PlanColumnInfo planColumnInfo = (PlanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Plan.class);
        long j3 = planColumnInfo.createColKey;
        while (it.hasNext()) {
            Plan plan = (Plan) it.next();
            if (!map2.containsKey(plan)) {
                if ((plan instanceof RealmObjectProxy) && !RealmObject.isFrozen(plan)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) plan;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(plan, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_PlanRealmProxyInterface com_ciot_realm_db_patrol_planrealmproxyinterface = plan;
                Long valueOf = Long.valueOf(com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$create());
                if (valueOf != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j3, com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$create());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j3, Long.valueOf(com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$create()));
                } else {
                    Table.throwDuplicatePrimaryKeyException(valueOf);
                }
                long j4 = j;
                map2.put(plan, Long.valueOf(j4));
                String realmGet$cycle = com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$cycle();
                if (realmGet$cycle != null) {
                    j2 = j4;
                    Table.nativeSetString(nativePtr, planColumnInfo.cycleColKey, j4, realmGet$cycle, false);
                } else {
                    j2 = j4;
                }
                String realmGet$start = com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$start();
                if (realmGet$start != null) {
                    Table.nativeSetString(nativePtr, planColumnInfo.startColKey, j2, realmGet$start, false);
                }
                String realmGet$end = com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$end();
                if (realmGet$end != null) {
                    Table.nativeSetString(nativePtr, planColumnInfo.endColKey, j2, realmGet$end, false);
                }
                RealmList<Integer> realmGet$date = com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$date();
                if (realmGet$date != null) {
                    OsList osList = new OsList(table.getUncheckedRow(j2), planColumnInfo.dateColKey);
                    Iterator<Integer> it2 = realmGet$date.iterator();
                    while (it2.hasNext()) {
                        Integer next = it2.next();
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

    public static long insertOrUpdate(Realm realm, Plan plan, Map<RealmModel, Long> map) {
        long j;
        Plan plan2 = plan;
        if ((plan2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(plan)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) plan2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Plan.class);
        long nativePtr = table.getNativePtr();
        PlanColumnInfo planColumnInfo = (PlanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Plan.class);
        long j2 = planColumnInfo.createColKey;
        com_ciot_realm_db_patrol_PlanRealmProxyInterface com_ciot_realm_db_patrol_planrealmproxyinterface = plan2;
        if (Long.valueOf(com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$create()) != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$create());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$create()));
        }
        long j3 = j;
        map.put(plan2, Long.valueOf(j3));
        String realmGet$cycle = com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$cycle();
        if (realmGet$cycle != null) {
            Table.nativeSetString(nativePtr, planColumnInfo.cycleColKey, j3, realmGet$cycle, false);
        } else {
            Table.nativeSetNull(nativePtr, planColumnInfo.cycleColKey, j3, false);
        }
        String realmGet$start = com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$start();
        if (realmGet$start != null) {
            Table.nativeSetString(nativePtr, planColumnInfo.startColKey, j3, realmGet$start, false);
        } else {
            Table.nativeSetNull(nativePtr, planColumnInfo.startColKey, j3, false);
        }
        String realmGet$end = com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$end();
        if (realmGet$end != null) {
            Table.nativeSetString(nativePtr, planColumnInfo.endColKey, j3, realmGet$end, false);
        } else {
            Table.nativeSetNull(nativePtr, planColumnInfo.endColKey, j3, false);
        }
        OsList osList = new OsList(table.getUncheckedRow(j3), planColumnInfo.dateColKey);
        osList.removeAll();
        RealmList<Integer> realmGet$date = com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$date();
        if (realmGet$date != null) {
            Iterator<Integer> it = realmGet$date.iterator();
            while (it.hasNext()) {
                Integer next = it.next();
                if (next == null) {
                    osList.addNull();
                } else {
                    osList.addLong(next.longValue());
                }
            }
        }
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Plan.class);
        long nativePtr = table.getNativePtr();
        PlanColumnInfo planColumnInfo = (PlanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Plan.class);
        long j3 = planColumnInfo.createColKey;
        while (it.hasNext()) {
            Plan plan = (Plan) it.next();
            if (!map2.containsKey(plan)) {
                if ((plan instanceof RealmObjectProxy) && !RealmObject.isFrozen(plan)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) plan;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(plan, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_PlanRealmProxyInterface com_ciot_realm_db_patrol_planrealmproxyinterface = plan;
                if (Long.valueOf(com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$create()) != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j3, com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$create());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j3, Long.valueOf(com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$create()));
                }
                long j4 = j;
                map2.put(plan, Long.valueOf(j4));
                String realmGet$cycle = com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$cycle();
                if (realmGet$cycle != null) {
                    j2 = j4;
                    Table.nativeSetString(nativePtr, planColumnInfo.cycleColKey, j4, realmGet$cycle, false);
                } else {
                    j2 = j4;
                    Table.nativeSetNull(nativePtr, planColumnInfo.cycleColKey, j4, false);
                }
                String realmGet$start = com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$start();
                if (realmGet$start != null) {
                    Table.nativeSetString(nativePtr, planColumnInfo.startColKey, j2, realmGet$start, false);
                } else {
                    Table.nativeSetNull(nativePtr, planColumnInfo.startColKey, j2, false);
                }
                String realmGet$end = com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$end();
                if (realmGet$end != null) {
                    Table.nativeSetString(nativePtr, planColumnInfo.endColKey, j2, realmGet$end, false);
                } else {
                    Table.nativeSetNull(nativePtr, planColumnInfo.endColKey, j2, false);
                }
                OsList osList = new OsList(table.getUncheckedRow(j2), planColumnInfo.dateColKey);
                osList.removeAll();
                RealmList<Integer> realmGet$date = com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$date();
                if (realmGet$date != null) {
                    Iterator<Integer> it2 = realmGet$date.iterator();
                    while (it2.hasNext()) {
                        Integer next = it2.next();
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

    public static Plan createDetachedCopy(Plan plan, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        Plan plan2;
        if (i > i2 || plan == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(plan);
        if (cacheData == null) {
            plan2 = new Plan();
            map.put(plan, new RealmObjectProxy.CacheData(i, plan2));
        } else if (i >= cacheData.minDepth) {
            return (Plan) cacheData.object;
        } else {
            cacheData.minDepth = i;
            plan2 = (Plan) cacheData.object;
        }
        com_ciot_realm_db_patrol_PlanRealmProxyInterface com_ciot_realm_db_patrol_planrealmproxyinterface = plan2;
        com_ciot_realm_db_patrol_PlanRealmProxyInterface com_ciot_realm_db_patrol_planrealmproxyinterface2 = plan;
        com_ciot_realm_db_patrol_planrealmproxyinterface.realmSet$cycle(com_ciot_realm_db_patrol_planrealmproxyinterface2.realmGet$cycle());
        com_ciot_realm_db_patrol_planrealmproxyinterface.realmSet$start(com_ciot_realm_db_patrol_planrealmproxyinterface2.realmGet$start());
        com_ciot_realm_db_patrol_planrealmproxyinterface.realmSet$end(com_ciot_realm_db_patrol_planrealmproxyinterface2.realmGet$end());
        com_ciot_realm_db_patrol_planrealmproxyinterface.realmSet$create(com_ciot_realm_db_patrol_planrealmproxyinterface2.realmGet$create());
        com_ciot_realm_db_patrol_planrealmproxyinterface.realmSet$date(new RealmList());
        com_ciot_realm_db_patrol_planrealmproxyinterface.realmGet$date().addAll(com_ciot_realm_db_patrol_planrealmproxyinterface2.realmGet$date());
        return plan2;
    }

    static Plan update(Realm realm, PlanColumnInfo planColumnInfo, Plan plan, Plan plan2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_patrol_PlanRealmProxyInterface com_ciot_realm_db_patrol_planrealmproxyinterface = plan;
        com_ciot_realm_db_patrol_PlanRealmProxyInterface com_ciot_realm_db_patrol_planrealmproxyinterface2 = plan2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Plan.class), set);
        osObjectBuilder.addString(planColumnInfo.cycleColKey, com_ciot_realm_db_patrol_planrealmproxyinterface2.realmGet$cycle());
        osObjectBuilder.addString(planColumnInfo.startColKey, com_ciot_realm_db_patrol_planrealmproxyinterface2.realmGet$start());
        osObjectBuilder.addString(planColumnInfo.endColKey, com_ciot_realm_db_patrol_planrealmproxyinterface2.realmGet$end());
        osObjectBuilder.addInteger(planColumnInfo.createColKey, Long.valueOf(com_ciot_realm_db_patrol_planrealmproxyinterface2.realmGet$create()));
        osObjectBuilder.addIntegerList(planColumnInfo.dateColKey, com_ciot_realm_db_patrol_planrealmproxyinterface2.realmGet$date());
        osObjectBuilder.updateExistingObject();
        return plan;
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
        com_ciot_realm_db_patrol_PlanRealmProxy com_ciot_realm_db_patrol_planrealmproxy = (com_ciot_realm_db_patrol_PlanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_patrol_planrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_patrol_planrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_patrol_planrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
