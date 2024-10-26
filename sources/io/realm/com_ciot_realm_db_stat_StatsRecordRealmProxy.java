package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.ciot.realm.db.stat.StatsRecord;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
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

public class com_ciot_realm_db_stat_StatsRecordRealmProxy extends StatsRecord implements RealmObjectProxy, com_ciot_realm_db_stat_StatsRecordRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private StatsRecordColumnInfo columnInfo;
    private ProxyState<StatsRecord> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "StatsRecord";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class StatsRecordColumnInfo extends ColumnInfo {
        long accountColKey;
        long beginColKey;
        long endColKey;
        long filenameColKey;
        long ossColKey;
        long pathColKey;
        long robotIdColKey;
        long typeColKey;

        StatsRecordColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(8);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.typeColKey = addColumnDetails("type", "type", objectSchemaInfo);
            this.robotIdColKey = addColumnDetails("robotId", "robotId", objectSchemaInfo);
            this.accountColKey = addColumnDetails("account", "account", objectSchemaInfo);
            this.ossColKey = addColumnDetails(OSSConstants.RESOURCE_NAME_OSS, OSSConstants.RESOURCE_NAME_OSS, objectSchemaInfo);
            this.beginColKey = addColumnDetails("begin", "begin", objectSchemaInfo);
            this.endColKey = addColumnDetails("end", "end", objectSchemaInfo);
            this.pathColKey = addColumnDetails("path", "path", objectSchemaInfo);
            this.filenameColKey = addColumnDetails(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_FILENAME, TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_FILENAME, objectSchemaInfo);
        }

        StatsRecordColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new StatsRecordColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            StatsRecordColumnInfo statsRecordColumnInfo = (StatsRecordColumnInfo) columnInfo;
            StatsRecordColumnInfo statsRecordColumnInfo2 = (StatsRecordColumnInfo) columnInfo2;
            statsRecordColumnInfo2.typeColKey = statsRecordColumnInfo.typeColKey;
            statsRecordColumnInfo2.robotIdColKey = statsRecordColumnInfo.robotIdColKey;
            statsRecordColumnInfo2.accountColKey = statsRecordColumnInfo.accountColKey;
            statsRecordColumnInfo2.ossColKey = statsRecordColumnInfo.ossColKey;
            statsRecordColumnInfo2.beginColKey = statsRecordColumnInfo.beginColKey;
            statsRecordColumnInfo2.endColKey = statsRecordColumnInfo.endColKey;
            statsRecordColumnInfo2.pathColKey = statsRecordColumnInfo.pathColKey;
            statsRecordColumnInfo2.filenameColKey = statsRecordColumnInfo.filenameColKey;
        }
    }

    com_ciot_realm_db_stat_StatsRecordRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (StatsRecordColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<StatsRecord> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$type() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.typeColKey);
    }

    public void realmSet$type(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.typeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.typeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.typeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.typeColKey, row$realm.getObjectKey(), str, true);
            }
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

    public String realmGet$oss() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.ossColKey);
    }

    public void realmSet$oss(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.ossColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.ossColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.ossColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.ossColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public long realmGet$begin() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.beginColKey);
    }

    public void realmSet$begin(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.beginColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.beginColKey, row$realm.getObjectKey(), j, true);
        }
    }

    public long realmGet$end() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.endColKey);
    }

    public void realmSet$end(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.endColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.endColKey, row$realm.getObjectKey(), j, true);
        }
    }

    public String realmGet$path() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.pathColKey);
    }

    public void realmSet$path(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'path' cannot be changed after object was created.");
        }
    }

    public String realmGet$filename() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.filenameColKey);
    }

    public void realmSet$filename(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.filenameColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.filenameColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.filenameColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.filenameColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 8, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("type", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("robotId", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("account", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(OSSConstants.RESOURCE_NAME_OSS, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("begin", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("end", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("path", RealmFieldType.STRING, true, false, false);
        builder2.addPersistedProperty(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_FILENAME, RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static StatsRecordColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new StatsRecordColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v6, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v7, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x013f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.stat.StatsRecord createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            java.lang.String r1 = "path"
            r2 = 0
            if (r14 == 0) goto L_0x0064
            java.lang.Class<com.ciot.realm.db.stat.StatsRecord> r14 = com.ciot.realm.db.stat.StatsRecord.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.stat.StatsRecord> r4 = com.ciot.realm.db.stat.StatsRecord.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxy$StatsRecordColumnInfo r3 = (io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxy.StatsRecordColumnInfo) r3
            long r3 = r3.pathColKey
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
            java.lang.Class<com.ciot.realm.db.stat.StatsRecord> r3 = com.ciot.realm.db.stat.StatsRecord.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005f }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005f }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005f }
            io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxy r14 = new io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxy     // Catch:{ all -> 0x005f }
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
            java.lang.Class<com.ciot.realm.db.stat.StatsRecord> r14 = com.ciot.realm.db.stat.StatsRecord.class
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxy r14 = (io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxy) r14
            goto L_0x0094
        L_0x007e:
            java.lang.Class<com.ciot.realm.db.stat.StatsRecord> r14 = com.ciot.realm.db.stat.StatsRecord.class
            java.lang.String r1 = r13.getString(r1)
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxy r14 = (io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxy) r14
            goto L_0x0094
        L_0x008c:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'path'."
            r12.<init>(r13)
            throw r12
        L_0x0094:
            r12 = r14
            io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxyInterface) r12
            java.lang.String r0 = "type"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00b0
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00a9
            r12.realmSet$type(r2)
            goto L_0x00b0
        L_0x00a9:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$type(r0)
        L_0x00b0:
            java.lang.String r0 = "robotId"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00c9
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00c2
            r12.realmSet$robotId(r2)
            goto L_0x00c9
        L_0x00c2:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$robotId(r0)
        L_0x00c9:
            java.lang.String r0 = "account"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00e2
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00db
            r12.realmSet$account(r2)
            goto L_0x00e2
        L_0x00db:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$account(r0)
        L_0x00e2:
            java.lang.String r0 = "oss"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00fb
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00f4
            r12.realmSet$oss(r2)
            goto L_0x00fb
        L_0x00f4:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$oss(r0)
        L_0x00fb:
            java.lang.String r0 = "begin"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0119
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0111
            long r0 = r13.getLong(r0)
            r12.realmSet$begin(r0)
            goto L_0x0119
        L_0x0111:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'begin' to null."
            r12.<init>(r13)
            throw r12
        L_0x0119:
            java.lang.String r0 = "end"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0137
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x012f
            long r0 = r13.getLong(r0)
            r12.realmSet$end(r0)
            goto L_0x0137
        L_0x012f:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'end' to null."
            r12.<init>(r13)
            throw r12
        L_0x0137:
            java.lang.String r0 = "filename"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0150
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0149
            r12.realmSet$filename(r2)
            goto L_0x0150
        L_0x0149:
            java.lang.String r13 = r13.getString(r0)
            r12.realmSet$filename(r13)
        L_0x0150:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.stat.StatsRecord");
    }

    public static StatsRecord createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        StatsRecord statsRecord = new StatsRecord();
        com_ciot_realm_db_stat_StatsRecordRealmProxyInterface com_ciot_realm_db_stat_statsrecordrealmproxyinterface = statsRecord;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("type")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$type(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$type((String) null);
                }
            } else if (nextName.equals("robotId")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$robotId(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$robotId((String) null);
                }
            } else if (nextName.equals("account")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$account(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$account((String) null);
                }
            } else if (nextName.equals(OSSConstants.RESOURCE_NAME_OSS)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$oss(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$oss((String) null);
                }
            } else if (nextName.equals("begin")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$begin(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'begin' to null.");
                }
            } else if (nextName.equals("end")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$end(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'end' to null.");
                }
            } else if (nextName.equals("path")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$path(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$path((String) null);
                }
                z = true;
            } else if (!nextName.equals(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_FILENAME)) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$filename(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$filename((String) null);
            }
        }
        jsonReader.endObject();
        if (z) {
            return (StatsRecord) realm.copyToRealm(statsRecord, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'path'.");
    }

    private static com_ciot_realm_db_stat_StatsRecordRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) StatsRecord.class), false, Collections.emptyList());
        com_ciot_realm_db_stat_StatsRecordRealmProxy com_ciot_realm_db_stat_statsrecordrealmproxy = new com_ciot_realm_db_stat_StatsRecordRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_stat_statsrecordrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.stat.StatsRecord copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxy.StatsRecordColumnInfo r9, com.ciot.realm.db.stat.StatsRecord r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.stat.StatsRecord r1 = (com.ciot.realm.db.stat.StatsRecord) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.stat.StatsRecord> r2 = com.ciot.realm.db.stat.StatsRecord.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.pathColKey
            r5 = r10
            io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxyInterface) r5
            java.lang.String r5 = r5.realmGet$path()
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
            io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxy r1 = new io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.stat.StatsRecord r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.stat.StatsRecord r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_stat_StatsRecordRealmProxy$StatsRecordColumnInfo, com.ciot.realm.db.stat.StatsRecord, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.stat.StatsRecord");
    }

    public static StatsRecord copy(Realm realm, StatsRecordColumnInfo statsRecordColumnInfo, StatsRecord statsRecord, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(statsRecord);
        if (realmObjectProxy != null) {
            return (StatsRecord) realmObjectProxy;
        }
        com_ciot_realm_db_stat_StatsRecordRealmProxyInterface com_ciot_realm_db_stat_statsrecordrealmproxyinterface = statsRecord;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(StatsRecord.class), set);
        osObjectBuilder.addString(statsRecordColumnInfo.typeColKey, com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$type());
        osObjectBuilder.addString(statsRecordColumnInfo.robotIdColKey, com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$robotId());
        osObjectBuilder.addString(statsRecordColumnInfo.accountColKey, com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$account());
        osObjectBuilder.addString(statsRecordColumnInfo.ossColKey, com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$oss());
        osObjectBuilder.addInteger(statsRecordColumnInfo.beginColKey, Long.valueOf(com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$begin()));
        osObjectBuilder.addInteger(statsRecordColumnInfo.endColKey, Long.valueOf(com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$end()));
        osObjectBuilder.addString(statsRecordColumnInfo.pathColKey, com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$path());
        osObjectBuilder.addString(statsRecordColumnInfo.filenameColKey, com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$filename());
        com_ciot_realm_db_stat_StatsRecordRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(statsRecord, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, StatsRecord statsRecord, Map<RealmModel, Long> map) {
        long j;
        StatsRecord statsRecord2 = statsRecord;
        if ((statsRecord2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(statsRecord)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) statsRecord2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(StatsRecord.class);
        long nativePtr = table.getNativePtr();
        StatsRecordColumnInfo statsRecordColumnInfo = (StatsRecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) StatsRecord.class);
        long j2 = statsRecordColumnInfo.pathColKey;
        com_ciot_realm_db_stat_StatsRecordRealmProxyInterface com_ciot_realm_db_stat_statsrecordrealmproxyinterface = statsRecord2;
        String realmGet$path = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$path();
        if (realmGet$path == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$path);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$path);
        } else {
            Table.throwDuplicatePrimaryKeyException(realmGet$path);
        }
        long j3 = j;
        map.put(statsRecord2, Long.valueOf(j3));
        String realmGet$type = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(nativePtr, statsRecordColumnInfo.typeColKey, j3, realmGet$type, false);
        }
        String realmGet$robotId = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$robotId();
        if (realmGet$robotId != null) {
            Table.nativeSetString(nativePtr, statsRecordColumnInfo.robotIdColKey, j3, realmGet$robotId, false);
        }
        String realmGet$account = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$account();
        if (realmGet$account != null) {
            Table.nativeSetString(nativePtr, statsRecordColumnInfo.accountColKey, j3, realmGet$account, false);
        }
        String realmGet$oss = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$oss();
        if (realmGet$oss != null) {
            Table.nativeSetString(nativePtr, statsRecordColumnInfo.ossColKey, j3, realmGet$oss, false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetLong(j4, statsRecordColumnInfo.beginColKey, j5, com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$begin(), false);
        Table.nativeSetLong(j4, statsRecordColumnInfo.endColKey, j5, com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$end(), false);
        String realmGet$filename = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$filename();
        if (realmGet$filename != null) {
            Table.nativeSetString(nativePtr, statsRecordColumnInfo.filenameColKey, j3, realmGet$filename, false);
        }
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(StatsRecord.class);
        long nativePtr = table.getNativePtr();
        StatsRecordColumnInfo statsRecordColumnInfo = (StatsRecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) StatsRecord.class);
        long j4 = statsRecordColumnInfo.pathColKey;
        while (it.hasNext()) {
            StatsRecord statsRecord = (StatsRecord) it.next();
            if (!map2.containsKey(statsRecord)) {
                if ((statsRecord instanceof RealmObjectProxy) && !RealmObject.isFrozen(statsRecord)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) statsRecord;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(statsRecord, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_stat_StatsRecordRealmProxyInterface com_ciot_realm_db_stat_statsrecordrealmproxyinterface = statsRecord;
                String realmGet$path = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$path();
                if (realmGet$path == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j4);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j4, realmGet$path);
                }
                if (j == -1) {
                    j2 = OsObject.createRowWithPrimaryKey(table, j4, realmGet$path);
                } else {
                    Table.throwDuplicatePrimaryKeyException(realmGet$path);
                    j2 = j;
                }
                map2.put(statsRecord, Long.valueOf(j2));
                String realmGet$type = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$type();
                if (realmGet$type != null) {
                    j3 = j4;
                    Table.nativeSetString(nativePtr, statsRecordColumnInfo.typeColKey, j2, realmGet$type, false);
                } else {
                    j3 = j4;
                }
                String realmGet$robotId = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$robotId();
                if (realmGet$robotId != null) {
                    Table.nativeSetString(nativePtr, statsRecordColumnInfo.robotIdColKey, j2, realmGet$robotId, false);
                }
                String realmGet$account = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$account();
                if (realmGet$account != null) {
                    Table.nativeSetString(nativePtr, statsRecordColumnInfo.accountColKey, j2, realmGet$account, false);
                }
                String realmGet$oss = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$oss();
                if (realmGet$oss != null) {
                    Table.nativeSetString(nativePtr, statsRecordColumnInfo.ossColKey, j2, realmGet$oss, false);
                }
                long j5 = nativePtr;
                long j6 = j2;
                Table.nativeSetLong(j5, statsRecordColumnInfo.beginColKey, j6, com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$begin(), false);
                Table.nativeSetLong(j5, statsRecordColumnInfo.endColKey, j6, com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$end(), false);
                String realmGet$filename = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$filename();
                if (realmGet$filename != null) {
                    Table.nativeSetString(nativePtr, statsRecordColumnInfo.filenameColKey, j2, realmGet$filename, false);
                }
                j4 = j3;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, StatsRecord statsRecord, Map<RealmModel, Long> map) {
        long j;
        StatsRecord statsRecord2 = statsRecord;
        if ((statsRecord2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(statsRecord)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) statsRecord2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(StatsRecord.class);
        long nativePtr = table.getNativePtr();
        StatsRecordColumnInfo statsRecordColumnInfo = (StatsRecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) StatsRecord.class);
        long j2 = statsRecordColumnInfo.pathColKey;
        com_ciot_realm_db_stat_StatsRecordRealmProxyInterface com_ciot_realm_db_stat_statsrecordrealmproxyinterface = statsRecord2;
        String realmGet$path = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$path();
        if (realmGet$path == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$path);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$path);
        }
        long j3 = j;
        map.put(statsRecord2, Long.valueOf(j3));
        String realmGet$type = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(nativePtr, statsRecordColumnInfo.typeColKey, j3, realmGet$type, false);
        } else {
            Table.nativeSetNull(nativePtr, statsRecordColumnInfo.typeColKey, j3, false);
        }
        String realmGet$robotId = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$robotId();
        if (realmGet$robotId != null) {
            Table.nativeSetString(nativePtr, statsRecordColumnInfo.robotIdColKey, j3, realmGet$robotId, false);
        } else {
            Table.nativeSetNull(nativePtr, statsRecordColumnInfo.robotIdColKey, j3, false);
        }
        String realmGet$account = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$account();
        if (realmGet$account != null) {
            Table.nativeSetString(nativePtr, statsRecordColumnInfo.accountColKey, j3, realmGet$account, false);
        } else {
            Table.nativeSetNull(nativePtr, statsRecordColumnInfo.accountColKey, j3, false);
        }
        String realmGet$oss = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$oss();
        if (realmGet$oss != null) {
            Table.nativeSetString(nativePtr, statsRecordColumnInfo.ossColKey, j3, realmGet$oss, false);
        } else {
            Table.nativeSetNull(nativePtr, statsRecordColumnInfo.ossColKey, j3, false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetLong(j4, statsRecordColumnInfo.beginColKey, j5, com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$begin(), false);
        Table.nativeSetLong(j4, statsRecordColumnInfo.endColKey, j5, com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$end(), false);
        String realmGet$filename = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$filename();
        if (realmGet$filename != null) {
            Table.nativeSetString(nativePtr, statsRecordColumnInfo.filenameColKey, j3, realmGet$filename, false);
        } else {
            Table.nativeSetNull(nativePtr, statsRecordColumnInfo.filenameColKey, j3, false);
        }
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(StatsRecord.class);
        long nativePtr = table.getNativePtr();
        StatsRecordColumnInfo statsRecordColumnInfo = (StatsRecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) StatsRecord.class);
        long j3 = statsRecordColumnInfo.pathColKey;
        while (it.hasNext()) {
            StatsRecord statsRecord = (StatsRecord) it.next();
            if (!map2.containsKey(statsRecord)) {
                if ((statsRecord instanceof RealmObjectProxy) && !RealmObject.isFrozen(statsRecord)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) statsRecord;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(statsRecord, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_stat_StatsRecordRealmProxyInterface com_ciot_realm_db_stat_statsrecordrealmproxyinterface = statsRecord;
                String realmGet$path = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$path();
                if (realmGet$path == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j3);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j3, realmGet$path);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j3, realmGet$path) : j;
                map2.put(statsRecord, Long.valueOf(createRowWithPrimaryKey));
                String realmGet$type = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$type();
                if (realmGet$type != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, statsRecordColumnInfo.typeColKey, createRowWithPrimaryKey, realmGet$type, false);
                } else {
                    j2 = j3;
                    Table.nativeSetNull(nativePtr, statsRecordColumnInfo.typeColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$robotId = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$robotId();
                if (realmGet$robotId != null) {
                    Table.nativeSetString(nativePtr, statsRecordColumnInfo.robotIdColKey, createRowWithPrimaryKey, realmGet$robotId, false);
                } else {
                    Table.nativeSetNull(nativePtr, statsRecordColumnInfo.robotIdColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$account = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$account();
                if (realmGet$account != null) {
                    Table.nativeSetString(nativePtr, statsRecordColumnInfo.accountColKey, createRowWithPrimaryKey, realmGet$account, false);
                } else {
                    Table.nativeSetNull(nativePtr, statsRecordColumnInfo.accountColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$oss = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$oss();
                if (realmGet$oss != null) {
                    Table.nativeSetString(nativePtr, statsRecordColumnInfo.ossColKey, createRowWithPrimaryKey, realmGet$oss, false);
                } else {
                    Table.nativeSetNull(nativePtr, statsRecordColumnInfo.ossColKey, createRowWithPrimaryKey, false);
                }
                long j4 = nativePtr;
                long j5 = createRowWithPrimaryKey;
                Table.nativeSetLong(j4, statsRecordColumnInfo.beginColKey, j5, com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$begin(), false);
                Table.nativeSetLong(j4, statsRecordColumnInfo.endColKey, j5, com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$end(), false);
                String realmGet$filename = com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmGet$filename();
                if (realmGet$filename != null) {
                    Table.nativeSetString(nativePtr, statsRecordColumnInfo.filenameColKey, createRowWithPrimaryKey, realmGet$filename, false);
                } else {
                    Table.nativeSetNull(nativePtr, statsRecordColumnInfo.filenameColKey, createRowWithPrimaryKey, false);
                }
                j3 = j2;
            }
        }
    }

    public static StatsRecord createDetachedCopy(StatsRecord statsRecord, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        StatsRecord statsRecord2;
        if (i > i2 || statsRecord == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(statsRecord);
        if (cacheData == null) {
            statsRecord2 = new StatsRecord();
            map.put(statsRecord, new RealmObjectProxy.CacheData(i, statsRecord2));
        } else if (i >= cacheData.minDepth) {
            return (StatsRecord) cacheData.object;
        } else {
            cacheData.minDepth = i;
            statsRecord2 = (StatsRecord) cacheData.object;
        }
        com_ciot_realm_db_stat_StatsRecordRealmProxyInterface com_ciot_realm_db_stat_statsrecordrealmproxyinterface = statsRecord2;
        com_ciot_realm_db_stat_StatsRecordRealmProxyInterface com_ciot_realm_db_stat_statsrecordrealmproxyinterface2 = statsRecord;
        com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$type(com_ciot_realm_db_stat_statsrecordrealmproxyinterface2.realmGet$type());
        com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$robotId(com_ciot_realm_db_stat_statsrecordrealmproxyinterface2.realmGet$robotId());
        com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$account(com_ciot_realm_db_stat_statsrecordrealmproxyinterface2.realmGet$account());
        com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$oss(com_ciot_realm_db_stat_statsrecordrealmproxyinterface2.realmGet$oss());
        com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$begin(com_ciot_realm_db_stat_statsrecordrealmproxyinterface2.realmGet$begin());
        com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$end(com_ciot_realm_db_stat_statsrecordrealmproxyinterface2.realmGet$end());
        com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$path(com_ciot_realm_db_stat_statsrecordrealmproxyinterface2.realmGet$path());
        com_ciot_realm_db_stat_statsrecordrealmproxyinterface.realmSet$filename(com_ciot_realm_db_stat_statsrecordrealmproxyinterface2.realmGet$filename());
        return statsRecord2;
    }

    static StatsRecord update(Realm realm, StatsRecordColumnInfo statsRecordColumnInfo, StatsRecord statsRecord, StatsRecord statsRecord2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_stat_StatsRecordRealmProxyInterface com_ciot_realm_db_stat_statsrecordrealmproxyinterface = statsRecord;
        com_ciot_realm_db_stat_StatsRecordRealmProxyInterface com_ciot_realm_db_stat_statsrecordrealmproxyinterface2 = statsRecord2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(StatsRecord.class), set);
        osObjectBuilder.addString(statsRecordColumnInfo.typeColKey, com_ciot_realm_db_stat_statsrecordrealmproxyinterface2.realmGet$type());
        osObjectBuilder.addString(statsRecordColumnInfo.robotIdColKey, com_ciot_realm_db_stat_statsrecordrealmproxyinterface2.realmGet$robotId());
        osObjectBuilder.addString(statsRecordColumnInfo.accountColKey, com_ciot_realm_db_stat_statsrecordrealmproxyinterface2.realmGet$account());
        osObjectBuilder.addString(statsRecordColumnInfo.ossColKey, com_ciot_realm_db_stat_statsrecordrealmproxyinterface2.realmGet$oss());
        osObjectBuilder.addInteger(statsRecordColumnInfo.beginColKey, Long.valueOf(com_ciot_realm_db_stat_statsrecordrealmproxyinterface2.realmGet$begin()));
        osObjectBuilder.addInteger(statsRecordColumnInfo.endColKey, Long.valueOf(com_ciot_realm_db_stat_statsrecordrealmproxyinterface2.realmGet$end()));
        osObjectBuilder.addString(statsRecordColumnInfo.pathColKey, com_ciot_realm_db_stat_statsrecordrealmproxyinterface2.realmGet$path());
        osObjectBuilder.addString(statsRecordColumnInfo.filenameColKey, com_ciot_realm_db_stat_statsrecordrealmproxyinterface2.realmGet$filename());
        osObjectBuilder.updateExistingObject();
        return statsRecord;
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
        com_ciot_realm_db_stat_StatsRecordRealmProxy com_ciot_realm_db_stat_statsrecordrealmproxy = (com_ciot_realm_db_stat_StatsRecordRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_stat_statsrecordrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_stat_statsrecordrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_stat_statsrecordrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
