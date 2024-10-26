package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.stat.AdStat;
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

public class com_ciot_realm_db_stat_AdStatRealmProxy extends AdStat implements RealmObjectProxy, com_ciot_realm_db_stat_AdStatRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private AdStatColumnInfo columnInfo;
    private ProxyState<AdStat> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "AdStat";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class AdStatColumnInfo extends ColumnInfo {
        long accountColKey;
        long adIdColKey;
        long adListColKey;
        long adResColKey;
        long durationColKey;
        long robotIdColKey;
        long screenColKey;
        long timestampColKey;

        AdStatColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(8);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.robotIdColKey = addColumnDetails("robotId", "robotId", objectSchemaInfo);
            this.accountColKey = addColumnDetails("account", "account", objectSchemaInfo);
            this.durationColKey = addColumnDetails("duration", "duration", objectSchemaInfo);
            this.adListColKey = addColumnDetails("adList", "adList", objectSchemaInfo);
            this.adIdColKey = addColumnDetails("adId", "adId", objectSchemaInfo);
            this.adResColKey = addColumnDetails("adRes", "adRes", objectSchemaInfo);
            this.timestampColKey = addColumnDetails("timestamp", "timestamp", objectSchemaInfo);
            this.screenColKey = addColumnDetails("screen", "screen", objectSchemaInfo);
        }

        AdStatColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new AdStatColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            AdStatColumnInfo adStatColumnInfo = (AdStatColumnInfo) columnInfo;
            AdStatColumnInfo adStatColumnInfo2 = (AdStatColumnInfo) columnInfo2;
            adStatColumnInfo2.robotIdColKey = adStatColumnInfo.robotIdColKey;
            adStatColumnInfo2.accountColKey = adStatColumnInfo.accountColKey;
            adStatColumnInfo2.durationColKey = adStatColumnInfo.durationColKey;
            adStatColumnInfo2.adListColKey = adStatColumnInfo.adListColKey;
            adStatColumnInfo2.adIdColKey = adStatColumnInfo.adIdColKey;
            adStatColumnInfo2.adResColKey = adStatColumnInfo.adResColKey;
            adStatColumnInfo2.timestampColKey = adStatColumnInfo.timestampColKey;
            adStatColumnInfo2.screenColKey = adStatColumnInfo.screenColKey;
        }
    }

    com_ciot_realm_db_stat_AdStatRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (AdStatColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<AdStat> proxyState2 = new ProxyState<>(this);
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

    public long realmGet$duration() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.durationColKey);
    }

    public void realmSet$duration(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.durationColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.durationColKey, row$realm.getObjectKey(), j, true);
        }
    }

    public String realmGet$adList() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.adListColKey);
    }

    public void realmSet$adList(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.adListColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.adListColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.adListColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.adListColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$adId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.adIdColKey);
    }

    public void realmSet$adId(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.adIdColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.adIdColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.adIdColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.adIdColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$adRes() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.adResColKey);
    }

    public void realmSet$adRes(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.adResColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.adResColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.adResColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.adResColKey, row$realm.getObjectKey(), str, true);
            }
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

    public int realmGet$screen() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.screenColKey);
    }

    public void realmSet$screen(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.screenColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.screenColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 8, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("robotId", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("account", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("duration", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("adList", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("adId", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("adRes", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("timestamp", RealmFieldType.INTEGER, true, true, true);
        builder2.addPersistedProperty("screen", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static AdStatColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new AdStatColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v6, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v7, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x013b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.stat.AdStat createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            java.lang.String r1 = "timestamp"
            r2 = 0
            if (r14 == 0) goto L_0x0061
            java.lang.Class<com.ciot.realm.db.stat.AdStat> r14 = com.ciot.realm.db.stat.AdStat.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.stat.AdStat> r4 = com.ciot.realm.db.stat.AdStat.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_stat_AdStatRealmProxy$AdStatColumnInfo r3 = (io.realm.com_ciot_realm_db_stat_AdStatRealmProxy.AdStatColumnInfo) r3
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
            java.lang.Class<com.ciot.realm.db.stat.AdStat> r3 = com.ciot.realm.db.stat.AdStat.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005c }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005c }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005c }
            io.realm.com_ciot_realm_db_stat_AdStatRealmProxy r14 = new io.realm.com_ciot_realm_db_stat_AdStatRealmProxy     // Catch:{ all -> 0x005c }
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
            java.lang.Class<com.ciot.realm.db.stat.AdStat> r14 = com.ciot.realm.db.stat.AdStat.class
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_stat_AdStatRealmProxy r14 = (io.realm.com_ciot_realm_db_stat_AdStatRealmProxy) r14
            goto L_0x0095
        L_0x007b:
            java.lang.Class<com.ciot.realm.db.stat.AdStat> r14 = com.ciot.realm.db.stat.AdStat.class
            long r4 = r13.getLong(r1)
            java.lang.Long r1 = java.lang.Long.valueOf(r4)
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_stat_AdStatRealmProxy r14 = (io.realm.com_ciot_realm_db_stat_AdStatRealmProxy) r14
            goto L_0x0095
        L_0x008d:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'timestamp'."
            r12.<init>(r13)
            throw r12
        L_0x0095:
            r12 = r14
            io.realm.com_ciot_realm_db_stat_AdStatRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_stat_AdStatRealmProxyInterface) r12
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
            java.lang.String r0 = "duration"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00e8
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x00e0
            long r0 = r13.getLong(r0)
            r12.realmSet$duration(r0)
            goto L_0x00e8
        L_0x00e0:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'duration' to null."
            r12.<init>(r13)
            throw r12
        L_0x00e8:
            java.lang.String r0 = "adList"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0101
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00fa
            r12.realmSet$adList(r2)
            goto L_0x0101
        L_0x00fa:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$adList(r0)
        L_0x0101:
            java.lang.String r0 = "adId"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x011a
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0113
            r12.realmSet$adId(r2)
            goto L_0x011a
        L_0x0113:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$adId(r0)
        L_0x011a:
            java.lang.String r0 = "adRes"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0133
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x012c
            r12.realmSet$adRes(r2)
            goto L_0x0133
        L_0x012c:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$adRes(r0)
        L_0x0133:
            java.lang.String r0 = "screen"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0151
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0149
            int r13 = r13.getInt(r0)
            r12.realmSet$screen(r13)
            goto L_0x0151
        L_0x0149:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'screen' to null."
            r12.<init>(r13)
            throw r12
        L_0x0151:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_stat_AdStatRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.stat.AdStat");
    }

    public static AdStat createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        AdStat adStat = new AdStat();
        com_ciot_realm_db_stat_AdStatRealmProxyInterface com_ciot_realm_db_stat_adstatrealmproxyinterface = adStat;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("robotId")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$robotId(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$robotId((String) null);
                }
            } else if (nextName.equals("account")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$account(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$account((String) null);
                }
            } else if (nextName.equals("duration")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$duration(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'duration' to null.");
                }
            } else if (nextName.equals("adList")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$adList(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$adList((String) null);
                }
            } else if (nextName.equals("adId")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$adId(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$adId((String) null);
                }
            } else if (nextName.equals("adRes")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$adRes(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$adRes((String) null);
                }
            } else if (nextName.equals("timestamp")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$timestamp(jsonReader.nextLong());
                    z = true;
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'timestamp' to null.");
                }
            } else if (!nextName.equals("screen")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$screen(jsonReader.nextInt());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'screen' to null.");
            }
        }
        jsonReader.endObject();
        if (z) {
            return (AdStat) realm.copyToRealm(adStat, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'timestamp'.");
    }

    private static com_ciot_realm_db_stat_AdStatRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) AdStat.class), false, Collections.emptyList());
        com_ciot_realm_db_stat_AdStatRealmProxy com_ciot_realm_db_stat_adstatrealmproxy = new com_ciot_realm_db_stat_AdStatRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_stat_adstatrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.stat.AdStat copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_stat_AdStatRealmProxy.AdStatColumnInfo r9, com.ciot.realm.db.stat.AdStat r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.stat.AdStat r1 = (com.ciot.realm.db.stat.AdStat) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0092
            java.lang.Class<com.ciot.realm.db.stat.AdStat> r2 = com.ciot.realm.db.stat.AdStat.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.timestampColKey
            r5 = r10
            io.realm.com_ciot_realm_db_stat_AdStatRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_stat_AdStatRealmProxyInterface) r5
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
            io.realm.com_ciot_realm_db_stat_AdStatRealmProxy r1 = new io.realm.com_ciot_realm_db_stat_AdStatRealmProxy     // Catch:{ all -> 0x008d }
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
            com.ciot.realm.db.stat.AdStat r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00a4
        L_0x00a0:
            com.ciot.realm.db.stat.AdStat r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00a4:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_stat_AdStatRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_stat_AdStatRealmProxy$AdStatColumnInfo, com.ciot.realm.db.stat.AdStat, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.stat.AdStat");
    }

    public static AdStat copy(Realm realm, AdStatColumnInfo adStatColumnInfo, AdStat adStat, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(adStat);
        if (realmObjectProxy != null) {
            return (AdStat) realmObjectProxy;
        }
        com_ciot_realm_db_stat_AdStatRealmProxyInterface com_ciot_realm_db_stat_adstatrealmproxyinterface = adStat;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(AdStat.class), set);
        osObjectBuilder.addString(adStatColumnInfo.robotIdColKey, com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$robotId());
        osObjectBuilder.addString(adStatColumnInfo.accountColKey, com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$account());
        osObjectBuilder.addInteger(adStatColumnInfo.durationColKey, Long.valueOf(com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$duration()));
        osObjectBuilder.addString(adStatColumnInfo.adListColKey, com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$adList());
        osObjectBuilder.addString(adStatColumnInfo.adIdColKey, com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$adId());
        osObjectBuilder.addString(adStatColumnInfo.adResColKey, com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$adRes());
        osObjectBuilder.addInteger(adStatColumnInfo.timestampColKey, Long.valueOf(com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$timestamp()));
        osObjectBuilder.addInteger(adStatColumnInfo.screenColKey, Integer.valueOf(com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$screen()));
        com_ciot_realm_db_stat_AdStatRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(adStat, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, AdStat adStat, Map<RealmModel, Long> map) {
        long j;
        AdStat adStat2 = adStat;
        if ((adStat2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(adStat)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) adStat2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(AdStat.class);
        long nativePtr = table.getNativePtr();
        AdStatColumnInfo adStatColumnInfo = (AdStatColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AdStat.class);
        long j2 = adStatColumnInfo.timestampColKey;
        com_ciot_realm_db_stat_AdStatRealmProxyInterface com_ciot_realm_db_stat_adstatrealmproxyinterface = adStat2;
        Long valueOf = Long.valueOf(com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$timestamp());
        if (valueOf != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$timestamp());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$timestamp()));
        } else {
            Table.throwDuplicatePrimaryKeyException(valueOf);
        }
        long j3 = j;
        map.put(adStat2, Long.valueOf(j3));
        String realmGet$robotId = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$robotId();
        if (realmGet$robotId != null) {
            Table.nativeSetString(nativePtr, adStatColumnInfo.robotIdColKey, j3, realmGet$robotId, false);
        }
        String realmGet$account = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$account();
        if (realmGet$account != null) {
            Table.nativeSetString(nativePtr, adStatColumnInfo.accountColKey, j3, realmGet$account, false);
        }
        Table.nativeSetLong(nativePtr, adStatColumnInfo.durationColKey, j3, com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$duration(), false);
        String realmGet$adList = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$adList();
        if (realmGet$adList != null) {
            Table.nativeSetString(nativePtr, adStatColumnInfo.adListColKey, j3, realmGet$adList, false);
        }
        String realmGet$adId = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$adId();
        if (realmGet$adId != null) {
            Table.nativeSetString(nativePtr, adStatColumnInfo.adIdColKey, j3, realmGet$adId, false);
        }
        String realmGet$adRes = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$adRes();
        if (realmGet$adRes != null) {
            Table.nativeSetString(nativePtr, adStatColumnInfo.adResColKey, j3, realmGet$adRes, false);
        }
        Table.nativeSetLong(nativePtr, adStatColumnInfo.screenColKey, j3, (long) com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$screen(), false);
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(AdStat.class);
        long nativePtr = table.getNativePtr();
        AdStatColumnInfo adStatColumnInfo = (AdStatColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AdStat.class);
        long j3 = adStatColumnInfo.timestampColKey;
        while (it.hasNext()) {
            AdStat adStat = (AdStat) it.next();
            if (!map2.containsKey(adStat)) {
                if ((adStat instanceof RealmObjectProxy) && !RealmObject.isFrozen(adStat)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) adStat;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(adStat, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_stat_AdStatRealmProxyInterface com_ciot_realm_db_stat_adstatrealmproxyinterface = adStat;
                Long valueOf = Long.valueOf(com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$timestamp());
                if (valueOf != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j3, com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$timestamp());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j3, Long.valueOf(com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$timestamp()));
                } else {
                    Table.throwDuplicatePrimaryKeyException(valueOf);
                }
                long j4 = j;
                map2.put(adStat, Long.valueOf(j4));
                String realmGet$robotId = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$robotId();
                if (realmGet$robotId != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, adStatColumnInfo.robotIdColKey, j4, realmGet$robotId, false);
                } else {
                    j2 = j3;
                }
                String realmGet$account = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$account();
                if (realmGet$account != null) {
                    Table.nativeSetString(nativePtr, adStatColumnInfo.accountColKey, j4, realmGet$account, false);
                }
                Table.nativeSetLong(nativePtr, adStatColumnInfo.durationColKey, j4, com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$duration(), false);
                String realmGet$adList = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$adList();
                if (realmGet$adList != null) {
                    Table.nativeSetString(nativePtr, adStatColumnInfo.adListColKey, j4, realmGet$adList, false);
                }
                String realmGet$adId = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$adId();
                if (realmGet$adId != null) {
                    Table.nativeSetString(nativePtr, adStatColumnInfo.adIdColKey, j4, realmGet$adId, false);
                }
                String realmGet$adRes = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$adRes();
                if (realmGet$adRes != null) {
                    Table.nativeSetString(nativePtr, adStatColumnInfo.adResColKey, j4, realmGet$adRes, false);
                }
                Table.nativeSetLong(nativePtr, adStatColumnInfo.screenColKey, j4, (long) com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$screen(), false);
                j3 = j2;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, AdStat adStat, Map<RealmModel, Long> map) {
        long j;
        AdStat adStat2 = adStat;
        if ((adStat2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(adStat)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) adStat2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(AdStat.class);
        long nativePtr = table.getNativePtr();
        AdStatColumnInfo adStatColumnInfo = (AdStatColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AdStat.class);
        long j2 = adStatColumnInfo.timestampColKey;
        com_ciot_realm_db_stat_AdStatRealmProxyInterface com_ciot_realm_db_stat_adstatrealmproxyinterface = adStat2;
        if (Long.valueOf(com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$timestamp()) != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$timestamp());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$timestamp()));
        }
        long j3 = j;
        map.put(adStat2, Long.valueOf(j3));
        String realmGet$robotId = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$robotId();
        if (realmGet$robotId != null) {
            Table.nativeSetString(nativePtr, adStatColumnInfo.robotIdColKey, j3, realmGet$robotId, false);
        } else {
            Table.nativeSetNull(nativePtr, adStatColumnInfo.robotIdColKey, j3, false);
        }
        String realmGet$account = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$account();
        if (realmGet$account != null) {
            Table.nativeSetString(nativePtr, adStatColumnInfo.accountColKey, j3, realmGet$account, false);
        } else {
            Table.nativeSetNull(nativePtr, adStatColumnInfo.accountColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, adStatColumnInfo.durationColKey, j3, com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$duration(), false);
        String realmGet$adList = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$adList();
        if (realmGet$adList != null) {
            Table.nativeSetString(nativePtr, adStatColumnInfo.adListColKey, j3, realmGet$adList, false);
        } else {
            Table.nativeSetNull(nativePtr, adStatColumnInfo.adListColKey, j3, false);
        }
        String realmGet$adId = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$adId();
        if (realmGet$adId != null) {
            Table.nativeSetString(nativePtr, adStatColumnInfo.adIdColKey, j3, realmGet$adId, false);
        } else {
            Table.nativeSetNull(nativePtr, adStatColumnInfo.adIdColKey, j3, false);
        }
        String realmGet$adRes = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$adRes();
        if (realmGet$adRes != null) {
            Table.nativeSetString(nativePtr, adStatColumnInfo.adResColKey, j3, realmGet$adRes, false);
        } else {
            Table.nativeSetNull(nativePtr, adStatColumnInfo.adResColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, adStatColumnInfo.screenColKey, j3, (long) com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$screen(), false);
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(AdStat.class);
        long nativePtr = table.getNativePtr();
        AdStatColumnInfo adStatColumnInfo = (AdStatColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AdStat.class);
        long j3 = adStatColumnInfo.timestampColKey;
        while (it.hasNext()) {
            AdStat adStat = (AdStat) it.next();
            if (!map2.containsKey(adStat)) {
                if ((adStat instanceof RealmObjectProxy) && !RealmObject.isFrozen(adStat)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) adStat;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(adStat, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_stat_AdStatRealmProxyInterface com_ciot_realm_db_stat_adstatrealmproxyinterface = adStat;
                if (Long.valueOf(com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$timestamp()) != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j3, com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$timestamp());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j3, Long.valueOf(com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$timestamp()));
                }
                long j4 = j;
                map2.put(adStat, Long.valueOf(j4));
                String realmGet$robotId = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$robotId();
                if (realmGet$robotId != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, adStatColumnInfo.robotIdColKey, j4, realmGet$robotId, false);
                } else {
                    j2 = j3;
                    Table.nativeSetNull(nativePtr, adStatColumnInfo.robotIdColKey, j4, false);
                }
                String realmGet$account = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$account();
                if (realmGet$account != null) {
                    Table.nativeSetString(nativePtr, adStatColumnInfo.accountColKey, j4, realmGet$account, false);
                } else {
                    Table.nativeSetNull(nativePtr, adStatColumnInfo.accountColKey, j4, false);
                }
                Table.nativeSetLong(nativePtr, adStatColumnInfo.durationColKey, j4, com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$duration(), false);
                String realmGet$adList = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$adList();
                if (realmGet$adList != null) {
                    Table.nativeSetString(nativePtr, adStatColumnInfo.adListColKey, j4, realmGet$adList, false);
                } else {
                    Table.nativeSetNull(nativePtr, adStatColumnInfo.adListColKey, j4, false);
                }
                String realmGet$adId = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$adId();
                if (realmGet$adId != null) {
                    Table.nativeSetString(nativePtr, adStatColumnInfo.adIdColKey, j4, realmGet$adId, false);
                } else {
                    Table.nativeSetNull(nativePtr, adStatColumnInfo.adIdColKey, j4, false);
                }
                String realmGet$adRes = com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$adRes();
                if (realmGet$adRes != null) {
                    Table.nativeSetString(nativePtr, adStatColumnInfo.adResColKey, j4, realmGet$adRes, false);
                } else {
                    Table.nativeSetNull(nativePtr, adStatColumnInfo.adResColKey, j4, false);
                }
                Table.nativeSetLong(nativePtr, adStatColumnInfo.screenColKey, j4, (long) com_ciot_realm_db_stat_adstatrealmproxyinterface.realmGet$screen(), false);
                j3 = j2;
            }
        }
    }

    public static AdStat createDetachedCopy(AdStat adStat, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        AdStat adStat2;
        if (i > i2 || adStat == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(adStat);
        if (cacheData == null) {
            adStat2 = new AdStat();
            map.put(adStat, new RealmObjectProxy.CacheData(i, adStat2));
        } else if (i >= cacheData.minDepth) {
            return (AdStat) cacheData.object;
        } else {
            cacheData.minDepth = i;
            adStat2 = (AdStat) cacheData.object;
        }
        com_ciot_realm_db_stat_AdStatRealmProxyInterface com_ciot_realm_db_stat_adstatrealmproxyinterface = adStat2;
        com_ciot_realm_db_stat_AdStatRealmProxyInterface com_ciot_realm_db_stat_adstatrealmproxyinterface2 = adStat;
        com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$robotId(com_ciot_realm_db_stat_adstatrealmproxyinterface2.realmGet$robotId());
        com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$account(com_ciot_realm_db_stat_adstatrealmproxyinterface2.realmGet$account());
        com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$duration(com_ciot_realm_db_stat_adstatrealmproxyinterface2.realmGet$duration());
        com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$adList(com_ciot_realm_db_stat_adstatrealmproxyinterface2.realmGet$adList());
        com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$adId(com_ciot_realm_db_stat_adstatrealmproxyinterface2.realmGet$adId());
        com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$adRes(com_ciot_realm_db_stat_adstatrealmproxyinterface2.realmGet$adRes());
        com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$timestamp(com_ciot_realm_db_stat_adstatrealmproxyinterface2.realmGet$timestamp());
        com_ciot_realm_db_stat_adstatrealmproxyinterface.realmSet$screen(com_ciot_realm_db_stat_adstatrealmproxyinterface2.realmGet$screen());
        return adStat2;
    }

    static AdStat update(Realm realm, AdStatColumnInfo adStatColumnInfo, AdStat adStat, AdStat adStat2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_stat_AdStatRealmProxyInterface com_ciot_realm_db_stat_adstatrealmproxyinterface = adStat;
        com_ciot_realm_db_stat_AdStatRealmProxyInterface com_ciot_realm_db_stat_adstatrealmproxyinterface2 = adStat2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(AdStat.class), set);
        osObjectBuilder.addString(adStatColumnInfo.robotIdColKey, com_ciot_realm_db_stat_adstatrealmproxyinterface2.realmGet$robotId());
        osObjectBuilder.addString(adStatColumnInfo.accountColKey, com_ciot_realm_db_stat_adstatrealmproxyinterface2.realmGet$account());
        osObjectBuilder.addInteger(adStatColumnInfo.durationColKey, Long.valueOf(com_ciot_realm_db_stat_adstatrealmproxyinterface2.realmGet$duration()));
        osObjectBuilder.addString(adStatColumnInfo.adListColKey, com_ciot_realm_db_stat_adstatrealmproxyinterface2.realmGet$adList());
        osObjectBuilder.addString(adStatColumnInfo.adIdColKey, com_ciot_realm_db_stat_adstatrealmproxyinterface2.realmGet$adId());
        osObjectBuilder.addString(adStatColumnInfo.adResColKey, com_ciot_realm_db_stat_adstatrealmproxyinterface2.realmGet$adRes());
        osObjectBuilder.addInteger(adStatColumnInfo.timestampColKey, Long.valueOf(com_ciot_realm_db_stat_adstatrealmproxyinterface2.realmGet$timestamp()));
        osObjectBuilder.addInteger(adStatColumnInfo.screenColKey, Integer.valueOf(com_ciot_realm_db_stat_adstatrealmproxyinterface2.realmGet$screen()));
        osObjectBuilder.updateExistingObject();
        return adStat;
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
        com_ciot_realm_db_stat_AdStatRealmProxy com_ciot_realm_db_stat_adstatrealmproxy = (com_ciot_realm_db_stat_AdStatRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_stat_adstatrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_stat_adstatrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_stat_adstatrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
