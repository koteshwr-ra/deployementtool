package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.stat.RobotPersonStat;
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

public class com_ciot_realm_db_stat_RobotPersonStatRealmProxy extends RobotPersonStat implements RealmObjectProxy, com_ciot_realm_db_stat_RobotPersonStatRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private RobotPersonStatColumnInfo columnInfo;
    private ProxyState<RobotPersonStat> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "RobotPersonStat";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class RobotPersonStatColumnInfo extends ColumnInfo {
        long accountColKey;
        long inferAgeMaxColKey;
        long inferAgeMinColKey;
        long inferSexColKey;
        long robotIdColKey;
        long timestampColKey;
        long trafficColKey;

        RobotPersonStatColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.robotIdColKey = addColumnDetails("robotId", "robotId", objectSchemaInfo);
            this.accountColKey = addColumnDetails("account", "account", objectSchemaInfo);
            this.trafficColKey = addColumnDetails("traffic", "traffic", objectSchemaInfo);
            this.timestampColKey = addColumnDetails("timestamp", "timestamp", objectSchemaInfo);
            this.inferSexColKey = addColumnDetails("inferSex", "inferSex", objectSchemaInfo);
            this.inferAgeMaxColKey = addColumnDetails("inferAgeMax", "inferAgeMax", objectSchemaInfo);
            this.inferAgeMinColKey = addColumnDetails("inferAgeMin", "inferAgeMin", objectSchemaInfo);
        }

        RobotPersonStatColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new RobotPersonStatColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            RobotPersonStatColumnInfo robotPersonStatColumnInfo = (RobotPersonStatColumnInfo) columnInfo;
            RobotPersonStatColumnInfo robotPersonStatColumnInfo2 = (RobotPersonStatColumnInfo) columnInfo2;
            robotPersonStatColumnInfo2.robotIdColKey = robotPersonStatColumnInfo.robotIdColKey;
            robotPersonStatColumnInfo2.accountColKey = robotPersonStatColumnInfo.accountColKey;
            robotPersonStatColumnInfo2.trafficColKey = robotPersonStatColumnInfo.trafficColKey;
            robotPersonStatColumnInfo2.timestampColKey = robotPersonStatColumnInfo.timestampColKey;
            robotPersonStatColumnInfo2.inferSexColKey = robotPersonStatColumnInfo.inferSexColKey;
            robotPersonStatColumnInfo2.inferAgeMaxColKey = robotPersonStatColumnInfo.inferAgeMaxColKey;
            robotPersonStatColumnInfo2.inferAgeMinColKey = robotPersonStatColumnInfo.inferAgeMinColKey;
        }
    }

    com_ciot_realm_db_stat_RobotPersonStatRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (RobotPersonStatColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<RobotPersonStat> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$robotId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.robotIdColKey);
    }

    public void realmSet$robotId(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.robotIdColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.robotIdColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.robotIdColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.robotIdColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$account() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.accountColKey);
    }

    public void realmSet$account(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.accountColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.accountColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.accountColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.accountColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public int realmGet$traffic() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.trafficColKey);
    }

    public void realmSet$traffic(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.trafficColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.trafficColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public long realmGet$timestamp() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.timestampColKey);
    }

    public void realmSet$timestamp(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'timestamp' cannot be changed after object was created.");
        }
    }

    public String realmGet$inferSex() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.inferSexColKey);
    }

    public void realmSet$inferSex(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.inferSexColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.inferSexColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.inferSexColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.inferSexColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public int realmGet$inferAgeMax() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.inferAgeMaxColKey);
    }

    public void realmSet$inferAgeMax(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.inferAgeMaxColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.inferAgeMaxColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public int realmGet$inferAgeMin() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.inferAgeMinColKey);
    }

    public void realmSet$inferAgeMin(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.inferAgeMinColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.inferAgeMinColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 7, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("robotId", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("account", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("traffic", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("timestamp", RealmFieldType.INTEGER, true, true, true);
        builder2.addPersistedProperty("inferSex", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("inferAgeMax", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("inferAgeMin", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static RobotPersonStatColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new RobotPersonStatColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v7, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v8, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0127  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.stat.RobotPersonStat createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            java.lang.String r1 = "timestamp"
            r2 = 0
            if (r14 == 0) goto L_0x0061
            java.lang.Class<com.ciot.realm.db.stat.RobotPersonStat> r14 = com.ciot.realm.db.stat.RobotPersonStat.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.stat.RobotPersonStat> r4 = com.ciot.realm.db.stat.RobotPersonStat.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxy$RobotPersonStatColumnInfo r3 = (io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxy.RobotPersonStatColumnInfo) r3
            long r3 = r3.timestampColKey
            boolean r5 = r13.isNull(r1)
            r6 = -1
            if (r5 != 0) goto L_0x002e
            long r8 = r13.getLong(r1)
            long r3 = r14.findFirstLong(r3, r8)
            goto L_0x002f
        L_0x002e:
            r3 = r6
        L_0x002f:
            int r5 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r5 == 0) goto L_0x0061
            io.realm.BaseRealm$ThreadLocalRealmObjectContext r5 = io.realm.BaseRealm.objectContext
            java.lang.Object r5 = r5.get()
            io.realm.BaseRealm$RealmObjectContext r5 = (io.realm.BaseRealm.RealmObjectContext) r5
            io.realm.internal.UncheckedRow r8 = r14.getUncheckedRow(r3)     // Catch:{ all -> 0x005c }
            io.realm.RealmSchema r14 = r12.getSchema()     // Catch:{ all -> 0x005c }
            java.lang.Class<com.ciot.realm.db.stat.RobotPersonStat> r3 = com.ciot.realm.db.stat.RobotPersonStat.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005c }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005c }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005c }
            io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxy r14 = new io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxy     // Catch:{ all -> 0x005c }
            r14.<init>()     // Catch:{ all -> 0x005c }
            r5.clear()
            goto L_0x0062
        L_0x005c:
            r12 = move-exception
            r5.clear()
            throw r12
        L_0x0061:
            r14 = r2
        L_0x0062:
            if (r14 != 0) goto L_0x0095
            boolean r14 = r13.has(r1)
            if (r14 == 0) goto L_0x008d
            boolean r14 = r13.isNull(r1)
            r3 = 1
            if (r14 == 0) goto L_0x007b
            java.lang.Class<com.ciot.realm.db.stat.RobotPersonStat> r14 = com.ciot.realm.db.stat.RobotPersonStat.class
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxy r14 = (io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxy) r14
            goto L_0x0095
        L_0x007b:
            java.lang.Class<com.ciot.realm.db.stat.RobotPersonStat> r14 = com.ciot.realm.db.stat.RobotPersonStat.class
            long r4 = r13.getLong(r1)
            java.lang.Long r1 = java.lang.Long.valueOf(r4)
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxy r14 = (io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxy) r14
            goto L_0x0095
        L_0x008d:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'timestamp'."
            r12.<init>(r13)
            throw r12
        L_0x0095:
            r12 = r14
            io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxyInterface) r12
            java.lang.String r0 = "robotId"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00b1
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00aa
            r12.realmSet$robotId(r2)
            goto L_0x00b1
        L_0x00aa:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$robotId(r0)
        L_0x00b1:
            java.lang.String r0 = "account"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00ca
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00c3
            r12.realmSet$account(r2)
            goto L_0x00ca
        L_0x00c3:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$account(r0)
        L_0x00ca:
            java.lang.String r0 = "traffic"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00e8
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x00e0
            int r0 = r13.getInt(r0)
            r12.realmSet$traffic(r0)
            goto L_0x00e8
        L_0x00e0:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'traffic' to null."
            r12.<init>(r13)
            throw r12
        L_0x00e8:
            java.lang.String r0 = "inferSex"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0101
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00fa
            r12.realmSet$inferSex(r2)
            goto L_0x0101
        L_0x00fa:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$inferSex(r0)
        L_0x0101:
            java.lang.String r0 = "inferAgeMax"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x011f
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0117
            int r0 = r13.getInt(r0)
            r12.realmSet$inferAgeMax(r0)
            goto L_0x011f
        L_0x0117:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'inferAgeMax' to null."
            r12.<init>(r13)
            throw r12
        L_0x011f:
            java.lang.String r0 = "inferAgeMin"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x013d
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0135
            int r13 = r13.getInt(r0)
            r12.realmSet$inferAgeMin(r13)
            goto L_0x013d
        L_0x0135:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'inferAgeMin' to null."
            r12.<init>(r13)
            throw r12
        L_0x013d:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.stat.RobotPersonStat");
    }

    public static RobotPersonStat createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        RobotPersonStat robotPersonStat = new RobotPersonStat();
        com_ciot_realm_db_stat_RobotPersonStatRealmProxyInterface com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface = robotPersonStat;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("robotId")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmSet$robotId(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmSet$robotId((String) null);
                }
            } else if (nextName.equals("account")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmSet$account(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmSet$account((String) null);
                }
            } else if (nextName.equals("traffic")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmSet$traffic(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'traffic' to null.");
                }
            } else if (nextName.equals("timestamp")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmSet$timestamp(jsonReader.nextLong());
                    z = true;
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'timestamp' to null.");
                }
            } else if (nextName.equals("inferSex")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmSet$inferSex(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmSet$inferSex((String) null);
                }
            } else if (nextName.equals("inferAgeMax")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmSet$inferAgeMax(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'inferAgeMax' to null.");
                }
            } else if (!nextName.equals("inferAgeMin")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmSet$inferAgeMin(jsonReader.nextInt());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'inferAgeMin' to null.");
            }
        }
        jsonReader.endObject();
        if (z) {
            return (RobotPersonStat) realm.copyToRealm(robotPersonStat, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'timestamp'.");
    }

    private static com_ciot_realm_db_stat_RobotPersonStatRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) RobotPersonStat.class), false, Collections.emptyList());
        com_ciot_realm_db_stat_RobotPersonStatRealmProxy com_ciot_realm_db_stat_robotpersonstatrealmproxy = new com_ciot_realm_db_stat_RobotPersonStatRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_stat_robotpersonstatrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.stat.RobotPersonStat copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxy.RobotPersonStatColumnInfo r9, com.ciot.realm.db.stat.RobotPersonStat r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.stat.RobotPersonStat r1 = (com.ciot.realm.db.stat.RobotPersonStat) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0092
            java.lang.Class<com.ciot.realm.db.stat.RobotPersonStat> r2 = com.ciot.realm.db.stat.RobotPersonStat.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.timestampColKey
            r5 = r10
            io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxyInterface) r5
            long r5 = r5.realmGet$timestamp()
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
            io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxy r1 = new io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxy     // Catch:{ all -> 0x008d }
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
            com.ciot.realm.db.stat.RobotPersonStat r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00a4
        L_0x00a0:
            com.ciot.realm.db.stat.RobotPersonStat r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00a4:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_stat_RobotPersonStatRealmProxy$RobotPersonStatColumnInfo, com.ciot.realm.db.stat.RobotPersonStat, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.stat.RobotPersonStat");
    }

    public static RobotPersonStat copy(Realm realm, RobotPersonStatColumnInfo robotPersonStatColumnInfo, RobotPersonStat robotPersonStat, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(robotPersonStat);
        if (realmObjectProxy != null) {
            return (RobotPersonStat) realmObjectProxy;
        }
        com_ciot_realm_db_stat_RobotPersonStatRealmProxyInterface com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface = robotPersonStat;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(RobotPersonStat.class), set);
        osObjectBuilder.addString(robotPersonStatColumnInfo.robotIdColKey, com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$robotId());
        osObjectBuilder.addString(robotPersonStatColumnInfo.accountColKey, com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$account());
        osObjectBuilder.addInteger(robotPersonStatColumnInfo.trafficColKey, Integer.valueOf(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$traffic()));
        osObjectBuilder.addInteger(robotPersonStatColumnInfo.timestampColKey, Long.valueOf(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$timestamp()));
        osObjectBuilder.addString(robotPersonStatColumnInfo.inferSexColKey, com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$inferSex());
        osObjectBuilder.addInteger(robotPersonStatColumnInfo.inferAgeMaxColKey, Integer.valueOf(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$inferAgeMax()));
        osObjectBuilder.addInteger(robotPersonStatColumnInfo.inferAgeMinColKey, Integer.valueOf(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$inferAgeMin()));
        com_ciot_realm_db_stat_RobotPersonStatRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(robotPersonStat, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, RobotPersonStat robotPersonStat, Map<RealmModel, Long> map) {
        long j;
        RobotPersonStat robotPersonStat2 = robotPersonStat;
        if ((robotPersonStat2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(robotPersonStat)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) robotPersonStat2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(RobotPersonStat.class);
        long nativePtr = table.getNativePtr();
        RobotPersonStatColumnInfo robotPersonStatColumnInfo = (RobotPersonStatColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RobotPersonStat.class);
        long j2 = robotPersonStatColumnInfo.timestampColKey;
        com_ciot_realm_db_stat_RobotPersonStatRealmProxyInterface com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface = robotPersonStat2;
        Long valueOf = Long.valueOf(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$timestamp());
        if (valueOf != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$timestamp());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$timestamp()));
        } else {
            Table.throwDuplicatePrimaryKeyException(valueOf);
        }
        long j3 = j;
        map.put(robotPersonStat2, Long.valueOf(j3));
        String realmGet$robotId = com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$robotId();
        if (realmGet$robotId != null) {
            Table.nativeSetString(nativePtr, robotPersonStatColumnInfo.robotIdColKey, j3, realmGet$robotId, false);
        }
        String realmGet$account = com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$account();
        if (realmGet$account != null) {
            Table.nativeSetString(nativePtr, robotPersonStatColumnInfo.accountColKey, j3, realmGet$account, false);
        }
        Table.nativeSetLong(nativePtr, robotPersonStatColumnInfo.trafficColKey, j3, (long) com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$traffic(), false);
        String realmGet$inferSex = com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$inferSex();
        if (realmGet$inferSex != null) {
            Table.nativeSetString(nativePtr, robotPersonStatColumnInfo.inferSexColKey, j3, realmGet$inferSex, false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetLong(j4, robotPersonStatColumnInfo.inferAgeMaxColKey, j5, (long) com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$inferAgeMax(), false);
        Table.nativeSetLong(j4, robotPersonStatColumnInfo.inferAgeMinColKey, j5, (long) com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$inferAgeMin(), false);
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(RobotPersonStat.class);
        long nativePtr = table.getNativePtr();
        RobotPersonStatColumnInfo robotPersonStatColumnInfo = (RobotPersonStatColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RobotPersonStat.class);
        long j3 = robotPersonStatColumnInfo.timestampColKey;
        while (it.hasNext()) {
            RobotPersonStat robotPersonStat = (RobotPersonStat) it.next();
            if (!map2.containsKey(robotPersonStat)) {
                if ((robotPersonStat instanceof RealmObjectProxy) && !RealmObject.isFrozen(robotPersonStat)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) robotPersonStat;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(robotPersonStat, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_stat_RobotPersonStatRealmProxyInterface com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface = robotPersonStat;
                Long valueOf = Long.valueOf(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$timestamp());
                if (valueOf != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j3, com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$timestamp());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j3, Long.valueOf(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$timestamp()));
                } else {
                    Table.throwDuplicatePrimaryKeyException(valueOf);
                }
                long j4 = j;
                map2.put(robotPersonStat, Long.valueOf(j4));
                String realmGet$robotId = com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$robotId();
                if (realmGet$robotId != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, robotPersonStatColumnInfo.robotIdColKey, j4, realmGet$robotId, false);
                } else {
                    j2 = j3;
                }
                String realmGet$account = com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$account();
                if (realmGet$account != null) {
                    Table.nativeSetString(nativePtr, robotPersonStatColumnInfo.accountColKey, j4, realmGet$account, false);
                }
                Table.nativeSetLong(nativePtr, robotPersonStatColumnInfo.trafficColKey, j4, (long) com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$traffic(), false);
                String realmGet$inferSex = com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$inferSex();
                if (realmGet$inferSex != null) {
                    Table.nativeSetString(nativePtr, robotPersonStatColumnInfo.inferSexColKey, j4, realmGet$inferSex, false);
                }
                long j5 = j4;
                Table.nativeSetLong(nativePtr, robotPersonStatColumnInfo.inferAgeMaxColKey, j5, (long) com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$inferAgeMax(), false);
                Table.nativeSetLong(nativePtr, robotPersonStatColumnInfo.inferAgeMinColKey, j5, (long) com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$inferAgeMin(), false);
                j3 = j2;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, RobotPersonStat robotPersonStat, Map<RealmModel, Long> map) {
        long j;
        RobotPersonStat robotPersonStat2 = robotPersonStat;
        if ((robotPersonStat2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(robotPersonStat)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) robotPersonStat2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(RobotPersonStat.class);
        long nativePtr = table.getNativePtr();
        RobotPersonStatColumnInfo robotPersonStatColumnInfo = (RobotPersonStatColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RobotPersonStat.class);
        long j2 = robotPersonStatColumnInfo.timestampColKey;
        com_ciot_realm_db_stat_RobotPersonStatRealmProxyInterface com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface = robotPersonStat2;
        if (Long.valueOf(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$timestamp()) != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$timestamp());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$timestamp()));
        }
        long j3 = j;
        map.put(robotPersonStat2, Long.valueOf(j3));
        String realmGet$robotId = com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$robotId();
        if (realmGet$robotId != null) {
            Table.nativeSetString(nativePtr, robotPersonStatColumnInfo.robotIdColKey, j3, realmGet$robotId, false);
        } else {
            Table.nativeSetNull(nativePtr, robotPersonStatColumnInfo.robotIdColKey, j3, false);
        }
        String realmGet$account = com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$account();
        if (realmGet$account != null) {
            Table.nativeSetString(nativePtr, robotPersonStatColumnInfo.accountColKey, j3, realmGet$account, false);
        } else {
            Table.nativeSetNull(nativePtr, robotPersonStatColumnInfo.accountColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, robotPersonStatColumnInfo.trafficColKey, j3, (long) com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$traffic(), false);
        String realmGet$inferSex = com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$inferSex();
        if (realmGet$inferSex != null) {
            Table.nativeSetString(nativePtr, robotPersonStatColumnInfo.inferSexColKey, j3, realmGet$inferSex, false);
        } else {
            Table.nativeSetNull(nativePtr, robotPersonStatColumnInfo.inferSexColKey, j3, false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetLong(j4, robotPersonStatColumnInfo.inferAgeMaxColKey, j5, (long) com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$inferAgeMax(), false);
        Table.nativeSetLong(j4, robotPersonStatColumnInfo.inferAgeMinColKey, j5, (long) com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$inferAgeMin(), false);
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(RobotPersonStat.class);
        long nativePtr = table.getNativePtr();
        RobotPersonStatColumnInfo robotPersonStatColumnInfo = (RobotPersonStatColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RobotPersonStat.class);
        long j3 = robotPersonStatColumnInfo.timestampColKey;
        while (it.hasNext()) {
            RobotPersonStat robotPersonStat = (RobotPersonStat) it.next();
            if (!map2.containsKey(robotPersonStat)) {
                if ((robotPersonStat instanceof RealmObjectProxy) && !RealmObject.isFrozen(robotPersonStat)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) robotPersonStat;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(robotPersonStat, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_stat_RobotPersonStatRealmProxyInterface com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface = robotPersonStat;
                if (Long.valueOf(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$timestamp()) != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j3, com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$timestamp());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j3, Long.valueOf(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$timestamp()));
                }
                long j4 = j;
                map2.put(robotPersonStat, Long.valueOf(j4));
                String realmGet$robotId = com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$robotId();
                if (realmGet$robotId != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, robotPersonStatColumnInfo.robotIdColKey, j4, realmGet$robotId, false);
                } else {
                    j2 = j3;
                    Table.nativeSetNull(nativePtr, robotPersonStatColumnInfo.robotIdColKey, j4, false);
                }
                String realmGet$account = com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$account();
                if (realmGet$account != null) {
                    Table.nativeSetString(nativePtr, robotPersonStatColumnInfo.accountColKey, j4, realmGet$account, false);
                } else {
                    Table.nativeSetNull(nativePtr, robotPersonStatColumnInfo.accountColKey, j4, false);
                }
                Table.nativeSetLong(nativePtr, robotPersonStatColumnInfo.trafficColKey, j4, (long) com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$traffic(), false);
                String realmGet$inferSex = com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$inferSex();
                if (realmGet$inferSex != null) {
                    Table.nativeSetString(nativePtr, robotPersonStatColumnInfo.inferSexColKey, j4, realmGet$inferSex, false);
                } else {
                    Table.nativeSetNull(nativePtr, robotPersonStatColumnInfo.inferSexColKey, j4, false);
                }
                long j5 = j4;
                Table.nativeSetLong(nativePtr, robotPersonStatColumnInfo.inferAgeMaxColKey, j5, (long) com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$inferAgeMax(), false);
                Table.nativeSetLong(nativePtr, robotPersonStatColumnInfo.inferAgeMinColKey, j5, (long) com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmGet$inferAgeMin(), false);
                j3 = j2;
            }
        }
    }

    public static RobotPersonStat createDetachedCopy(RobotPersonStat robotPersonStat, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        RobotPersonStat robotPersonStat2;
        if (i > i2 || robotPersonStat == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(robotPersonStat);
        if (cacheData == null) {
            robotPersonStat2 = new RobotPersonStat();
            map.put(robotPersonStat, new RealmObjectProxy.CacheData(i, robotPersonStat2));
        } else if (i >= cacheData.minDepth) {
            return (RobotPersonStat) cacheData.object;
        } else {
            cacheData.minDepth = i;
            robotPersonStat2 = (RobotPersonStat) cacheData.object;
        }
        com_ciot_realm_db_stat_RobotPersonStatRealmProxyInterface com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface = robotPersonStat2;
        com_ciot_realm_db_stat_RobotPersonStatRealmProxyInterface com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface2 = robotPersonStat;
        com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmSet$robotId(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface2.realmGet$robotId());
        com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmSet$account(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface2.realmGet$account());
        com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmSet$traffic(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface2.realmGet$traffic());
        com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmSet$timestamp(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface2.realmGet$timestamp());
        com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmSet$inferSex(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface2.realmGet$inferSex());
        com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmSet$inferAgeMax(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface2.realmGet$inferAgeMax());
        com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface.realmSet$inferAgeMin(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface2.realmGet$inferAgeMin());
        return robotPersonStat2;
    }

    static RobotPersonStat update(Realm realm, RobotPersonStatColumnInfo robotPersonStatColumnInfo, RobotPersonStat robotPersonStat, RobotPersonStat robotPersonStat2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_stat_RobotPersonStatRealmProxyInterface com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface = robotPersonStat;
        com_ciot_realm_db_stat_RobotPersonStatRealmProxyInterface com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface2 = robotPersonStat2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(RobotPersonStat.class), set);
        osObjectBuilder.addString(robotPersonStatColumnInfo.robotIdColKey, com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface2.realmGet$robotId());
        osObjectBuilder.addString(robotPersonStatColumnInfo.accountColKey, com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface2.realmGet$account());
        osObjectBuilder.addInteger(robotPersonStatColumnInfo.trafficColKey, Integer.valueOf(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface2.realmGet$traffic()));
        osObjectBuilder.addInteger(robotPersonStatColumnInfo.timestampColKey, Long.valueOf(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface2.realmGet$timestamp()));
        osObjectBuilder.addString(robotPersonStatColumnInfo.inferSexColKey, com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface2.realmGet$inferSex());
        osObjectBuilder.addInteger(robotPersonStatColumnInfo.inferAgeMaxColKey, Integer.valueOf(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface2.realmGet$inferAgeMax()));
        osObjectBuilder.addInteger(robotPersonStatColumnInfo.inferAgeMinColKey, Integer.valueOf(com_ciot_realm_db_stat_robotpersonstatrealmproxyinterface2.realmGet$inferAgeMin()));
        osObjectBuilder.updateExistingObject();
        return robotPersonStat;
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
        com_ciot_realm_db_stat_RobotPersonStatRealmProxy com_ciot_realm_db_stat_robotpersonstatrealmproxy = (com_ciot_realm_db_stat_RobotPersonStatRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_stat_robotpersonstatrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_stat_robotpersonstatrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_stat_robotpersonstatrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
