package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.Record;
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

public class com_ciot_realm_db_RecordRealmProxy extends Record implements RealmObjectProxy, com_ciot_realm_db_RecordRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private RecordColumnInfo columnInfo;
    private ProxyState<Record> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Record";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class RecordColumnInfo extends ColumnInfo {
        long beginColKey;
        long endColKey;
        long healthCodeColKey;
        long healthColorColKey;
        long idColKey;
        long qrCodeColKey;
        long recordIdColKey;

        RecordColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.recordIdColKey = addColumnDetails("recordId", "recordId", objectSchemaInfo);
            this.beginColKey = addColumnDetails("begin", "begin", objectSchemaInfo);
            this.endColKey = addColumnDetails("end", "end", objectSchemaInfo);
            this.qrCodeColKey = addColumnDetails("qrCode", "qrCode", objectSchemaInfo);
            this.healthCodeColKey = addColumnDetails("healthCode", "healthCode", objectSchemaInfo);
            this.healthColorColKey = addColumnDetails("healthColor", "healthColor", objectSchemaInfo);
        }

        RecordColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new RecordColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            RecordColumnInfo recordColumnInfo = (RecordColumnInfo) columnInfo;
            RecordColumnInfo recordColumnInfo2 = (RecordColumnInfo) columnInfo2;
            recordColumnInfo2.idColKey = recordColumnInfo.idColKey;
            recordColumnInfo2.recordIdColKey = recordColumnInfo.recordIdColKey;
            recordColumnInfo2.beginColKey = recordColumnInfo.beginColKey;
            recordColumnInfo2.endColKey = recordColumnInfo.endColKey;
            recordColumnInfo2.qrCodeColKey = recordColumnInfo.qrCodeColKey;
            recordColumnInfo2.healthCodeColKey = recordColumnInfo.healthCodeColKey;
            recordColumnInfo2.healthColorColKey = recordColumnInfo.healthColorColKey;
        }
    }

    com_ciot_realm_db_RecordRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (RecordColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<Record> proxyState2 = new ProxyState<>(this);
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

    public String realmGet$recordId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.recordIdColKey);
    }

    public void realmSet$recordId(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.recordIdColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.recordIdColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.recordIdColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.recordIdColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public double realmGet$begin() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getDouble(this.columnInfo.beginColKey);
    }

    public void realmSet$begin(double d) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setDouble(this.columnInfo.beginColKey, d);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setDouble(this.columnInfo.beginColKey, row$realm.getObjectKey(), d, true);
        }
    }

    public double realmGet$end() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getDouble(this.columnInfo.endColKey);
    }

    public void realmSet$end(double d) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setDouble(this.columnInfo.endColKey, d);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setDouble(this.columnInfo.endColKey, row$realm.getObjectKey(), d, true);
        }
    }

    public String realmGet$qrCode() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.qrCodeColKey);
    }

    public void realmSet$qrCode(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.qrCodeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.qrCodeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.qrCodeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.qrCodeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$healthCode() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.healthCodeColKey);
    }

    public void realmSet$healthCode(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.healthCodeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.healthCodeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.healthCodeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.healthCodeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public int realmGet$healthColor() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.healthColorColKey);
    }

    public void realmSet$healthColor(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.healthColorColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.healthColorColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 7, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("id", RealmFieldType.INTEGER, true, true, true);
        builder2.addPersistedProperty("recordId", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("begin", RealmFieldType.DOUBLE, false, false, true);
        builder2.addPersistedProperty("end", RealmFieldType.DOUBLE, false, false, true);
        builder2.addPersistedProperty("qrCode", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("healthCode", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("healthColor", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static RecordColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new RecordColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v7, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v8, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0127  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.Record createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            java.lang.String r1 = "id"
            r2 = 0
            if (r14 == 0) goto L_0x0061
            java.lang.Class<com.ciot.realm.db.Record> r14 = com.ciot.realm.db.Record.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.Record> r4 = com.ciot.realm.db.Record.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_RecordRealmProxy$RecordColumnInfo r3 = (io.realm.com_ciot_realm_db_RecordRealmProxy.RecordColumnInfo) r3
            long r3 = r3.idColKey
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
            java.lang.Class<com.ciot.realm.db.Record> r3 = com.ciot.realm.db.Record.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005c }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005c }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005c }
            io.realm.com_ciot_realm_db_RecordRealmProxy r14 = new io.realm.com_ciot_realm_db_RecordRealmProxy     // Catch:{ all -> 0x005c }
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
            java.lang.Class<com.ciot.realm.db.Record> r14 = com.ciot.realm.db.Record.class
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_RecordRealmProxy r14 = (io.realm.com_ciot_realm_db_RecordRealmProxy) r14
            goto L_0x0095
        L_0x007b:
            java.lang.Class<com.ciot.realm.db.Record> r14 = com.ciot.realm.db.Record.class
            long r4 = r13.getLong(r1)
            java.lang.Long r1 = java.lang.Long.valueOf(r4)
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_RecordRealmProxy r14 = (io.realm.com_ciot_realm_db_RecordRealmProxy) r14
            goto L_0x0095
        L_0x008d:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'id'."
            r12.<init>(r13)
            throw r12
        L_0x0095:
            r12 = r14
            io.realm.com_ciot_realm_db_RecordRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_RecordRealmProxyInterface) r12
            java.lang.String r0 = "recordId"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00b1
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00aa
            r12.realmSet$recordId(r2)
            goto L_0x00b1
        L_0x00aa:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$recordId(r0)
        L_0x00b1:
            java.lang.String r0 = "begin"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00cf
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x00c7
            double r0 = r13.getDouble(r0)
            r12.realmSet$begin(r0)
            goto L_0x00cf
        L_0x00c7:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'begin' to null."
            r12.<init>(r13)
            throw r12
        L_0x00cf:
            java.lang.String r0 = "end"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00ed
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x00e5
            double r0 = r13.getDouble(r0)
            r12.realmSet$end(r0)
            goto L_0x00ed
        L_0x00e5:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'end' to null."
            r12.<init>(r13)
            throw r12
        L_0x00ed:
            java.lang.String r0 = "qrCode"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0106
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00ff
            r12.realmSet$qrCode(r2)
            goto L_0x0106
        L_0x00ff:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$qrCode(r0)
        L_0x0106:
            java.lang.String r0 = "healthCode"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x011f
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0118
            r12.realmSet$healthCode(r2)
            goto L_0x011f
        L_0x0118:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$healthCode(r0)
        L_0x011f:
            java.lang.String r0 = "healthColor"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x013d
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0135
            int r13 = r13.getInt(r0)
            r12.realmSet$healthColor(r13)
            goto L_0x013d
        L_0x0135:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'healthColor' to null."
            r12.<init>(r13)
            throw r12
        L_0x013d:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_RecordRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.Record");
    }

    public static Record createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        Record record = new Record();
        com_ciot_realm_db_RecordRealmProxyInterface com_ciot_realm_db_recordrealmproxyinterface = record;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_recordrealmproxyinterface.realmSet$id(jsonReader.nextLong());
                    z = true;
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                }
            } else if (nextName.equals("recordId")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_recordrealmproxyinterface.realmSet$recordId(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_recordrealmproxyinterface.realmSet$recordId((String) null);
                }
            } else if (nextName.equals("begin")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_recordrealmproxyinterface.realmSet$begin(jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'begin' to null.");
                }
            } else if (nextName.equals("end")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_recordrealmproxyinterface.realmSet$end(jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'end' to null.");
                }
            } else if (nextName.equals("qrCode")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_recordrealmproxyinterface.realmSet$qrCode(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_recordrealmproxyinterface.realmSet$qrCode((String) null);
                }
            } else if (nextName.equals("healthCode")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_recordrealmproxyinterface.realmSet$healthCode(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_recordrealmproxyinterface.realmSet$healthCode((String) null);
                }
            } else if (!nextName.equals("healthColor")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_recordrealmproxyinterface.realmSet$healthColor(jsonReader.nextInt());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'healthColor' to null.");
            }
        }
        jsonReader.endObject();
        if (z) {
            return (Record) realm.copyToRealm(record, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_RecordRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) Record.class), false, Collections.emptyList());
        com_ciot_realm_db_RecordRealmProxy com_ciot_realm_db_recordrealmproxy = new com_ciot_realm_db_RecordRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_recordrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.Record copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_RecordRealmProxy.RecordColumnInfo r9, com.ciot.realm.db.Record r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.Record r1 = (com.ciot.realm.db.Record) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0092
            java.lang.Class<com.ciot.realm.db.Record> r2 = com.ciot.realm.db.Record.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_RecordRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_RecordRealmProxyInterface) r5
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
            io.realm.com_ciot_realm_db_RecordRealmProxy r1 = new io.realm.com_ciot_realm_db_RecordRealmProxy     // Catch:{ all -> 0x008d }
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
            com.ciot.realm.db.Record r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00a4
        L_0x00a0:
            com.ciot.realm.db.Record r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00a4:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_RecordRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_RecordRealmProxy$RecordColumnInfo, com.ciot.realm.db.Record, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.Record");
    }

    public static Record copy(Realm realm, RecordColumnInfo recordColumnInfo, Record record, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(record);
        if (realmObjectProxy != null) {
            return (Record) realmObjectProxy;
        }
        com_ciot_realm_db_RecordRealmProxyInterface com_ciot_realm_db_recordrealmproxyinterface = record;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Record.class), set);
        osObjectBuilder.addInteger(recordColumnInfo.idColKey, Long.valueOf(com_ciot_realm_db_recordrealmproxyinterface.realmGet$id()));
        osObjectBuilder.addString(recordColumnInfo.recordIdColKey, com_ciot_realm_db_recordrealmproxyinterface.realmGet$recordId());
        osObjectBuilder.addDouble(recordColumnInfo.beginColKey, Double.valueOf(com_ciot_realm_db_recordrealmproxyinterface.realmGet$begin()));
        osObjectBuilder.addDouble(recordColumnInfo.endColKey, Double.valueOf(com_ciot_realm_db_recordrealmproxyinterface.realmGet$end()));
        osObjectBuilder.addString(recordColumnInfo.qrCodeColKey, com_ciot_realm_db_recordrealmproxyinterface.realmGet$qrCode());
        osObjectBuilder.addString(recordColumnInfo.healthCodeColKey, com_ciot_realm_db_recordrealmproxyinterface.realmGet$healthCode());
        osObjectBuilder.addInteger(recordColumnInfo.healthColorColKey, Integer.valueOf(com_ciot_realm_db_recordrealmproxyinterface.realmGet$healthColor()));
        com_ciot_realm_db_RecordRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(record, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, Record record, Map<RealmModel, Long> map) {
        long j;
        Record record2 = record;
        if ((record2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(record)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) record2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Record.class);
        long nativePtr = table.getNativePtr();
        RecordColumnInfo recordColumnInfo = (RecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Record.class);
        long j2 = recordColumnInfo.idColKey;
        com_ciot_realm_db_RecordRealmProxyInterface com_ciot_realm_db_recordrealmproxyinterface = record2;
        Long valueOf = Long.valueOf(com_ciot_realm_db_recordrealmproxyinterface.realmGet$id());
        if (valueOf != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_recordrealmproxyinterface.realmGet$id());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_recordrealmproxyinterface.realmGet$id()));
        } else {
            Table.throwDuplicatePrimaryKeyException(valueOf);
        }
        long j3 = j;
        map.put(record2, Long.valueOf(j3));
        String realmGet$recordId = com_ciot_realm_db_recordrealmproxyinterface.realmGet$recordId();
        if (realmGet$recordId != null) {
            Table.nativeSetString(nativePtr, recordColumnInfo.recordIdColKey, j3, realmGet$recordId, false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetDouble(j4, recordColumnInfo.beginColKey, j5, com_ciot_realm_db_recordrealmproxyinterface.realmGet$begin(), false);
        Table.nativeSetDouble(j4, recordColumnInfo.endColKey, j5, com_ciot_realm_db_recordrealmproxyinterface.realmGet$end(), false);
        String realmGet$qrCode = com_ciot_realm_db_recordrealmproxyinterface.realmGet$qrCode();
        if (realmGet$qrCode != null) {
            Table.nativeSetString(nativePtr, recordColumnInfo.qrCodeColKey, j3, realmGet$qrCode, false);
        }
        String realmGet$healthCode = com_ciot_realm_db_recordrealmproxyinterface.realmGet$healthCode();
        if (realmGet$healthCode != null) {
            Table.nativeSetString(nativePtr, recordColumnInfo.healthCodeColKey, j3, realmGet$healthCode, false);
        }
        Table.nativeSetLong(nativePtr, recordColumnInfo.healthColorColKey, j3, (long) com_ciot_realm_db_recordrealmproxyinterface.realmGet$healthColor(), false);
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Record.class);
        long nativePtr = table.getNativePtr();
        RecordColumnInfo recordColumnInfo = (RecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Record.class);
        long j3 = recordColumnInfo.idColKey;
        while (it.hasNext()) {
            Record record = (Record) it.next();
            if (!map2.containsKey(record)) {
                if ((record instanceof RealmObjectProxy) && !RealmObject.isFrozen(record)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) record;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(record, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_RecordRealmProxyInterface com_ciot_realm_db_recordrealmproxyinterface = record;
                Long valueOf = Long.valueOf(com_ciot_realm_db_recordrealmproxyinterface.realmGet$id());
                if (valueOf != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j3, com_ciot_realm_db_recordrealmproxyinterface.realmGet$id());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j3, Long.valueOf(com_ciot_realm_db_recordrealmproxyinterface.realmGet$id()));
                } else {
                    Table.throwDuplicatePrimaryKeyException(valueOf);
                }
                long j4 = j;
                map2.put(record, Long.valueOf(j4));
                String realmGet$recordId = com_ciot_realm_db_recordrealmproxyinterface.realmGet$recordId();
                if (realmGet$recordId != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, recordColumnInfo.recordIdColKey, j4, realmGet$recordId, false);
                } else {
                    j2 = j3;
                }
                long j5 = nativePtr;
                long j6 = j4;
                Table.nativeSetDouble(j5, recordColumnInfo.beginColKey, j6, com_ciot_realm_db_recordrealmproxyinterface.realmGet$begin(), false);
                Table.nativeSetDouble(j5, recordColumnInfo.endColKey, j6, com_ciot_realm_db_recordrealmproxyinterface.realmGet$end(), false);
                String realmGet$qrCode = com_ciot_realm_db_recordrealmproxyinterface.realmGet$qrCode();
                if (realmGet$qrCode != null) {
                    Table.nativeSetString(nativePtr, recordColumnInfo.qrCodeColKey, j4, realmGet$qrCode, false);
                }
                String realmGet$healthCode = com_ciot_realm_db_recordrealmproxyinterface.realmGet$healthCode();
                if (realmGet$healthCode != null) {
                    Table.nativeSetString(nativePtr, recordColumnInfo.healthCodeColKey, j4, realmGet$healthCode, false);
                }
                Table.nativeSetLong(nativePtr, recordColumnInfo.healthColorColKey, j4, (long) com_ciot_realm_db_recordrealmproxyinterface.realmGet$healthColor(), false);
                j3 = j2;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Record record, Map<RealmModel, Long> map) {
        long j;
        Record record2 = record;
        if ((record2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(record)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) record2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Record.class);
        long nativePtr = table.getNativePtr();
        RecordColumnInfo recordColumnInfo = (RecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Record.class);
        long j2 = recordColumnInfo.idColKey;
        com_ciot_realm_db_RecordRealmProxyInterface com_ciot_realm_db_recordrealmproxyinterface = record2;
        if (Long.valueOf(com_ciot_realm_db_recordrealmproxyinterface.realmGet$id()) != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_recordrealmproxyinterface.realmGet$id());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_recordrealmproxyinterface.realmGet$id()));
        }
        long j3 = j;
        map.put(record2, Long.valueOf(j3));
        String realmGet$recordId = com_ciot_realm_db_recordrealmproxyinterface.realmGet$recordId();
        if (realmGet$recordId != null) {
            Table.nativeSetString(nativePtr, recordColumnInfo.recordIdColKey, j3, realmGet$recordId, false);
        } else {
            Table.nativeSetNull(nativePtr, recordColumnInfo.recordIdColKey, j3, false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetDouble(j4, recordColumnInfo.beginColKey, j5, com_ciot_realm_db_recordrealmproxyinterface.realmGet$begin(), false);
        Table.nativeSetDouble(j4, recordColumnInfo.endColKey, j5, com_ciot_realm_db_recordrealmproxyinterface.realmGet$end(), false);
        String realmGet$qrCode = com_ciot_realm_db_recordrealmproxyinterface.realmGet$qrCode();
        if (realmGet$qrCode != null) {
            Table.nativeSetString(nativePtr, recordColumnInfo.qrCodeColKey, j3, realmGet$qrCode, false);
        } else {
            Table.nativeSetNull(nativePtr, recordColumnInfo.qrCodeColKey, j3, false);
        }
        String realmGet$healthCode = com_ciot_realm_db_recordrealmproxyinterface.realmGet$healthCode();
        if (realmGet$healthCode != null) {
            Table.nativeSetString(nativePtr, recordColumnInfo.healthCodeColKey, j3, realmGet$healthCode, false);
        } else {
            Table.nativeSetNull(nativePtr, recordColumnInfo.healthCodeColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, recordColumnInfo.healthColorColKey, j3, (long) com_ciot_realm_db_recordrealmproxyinterface.realmGet$healthColor(), false);
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Record.class);
        long nativePtr = table.getNativePtr();
        RecordColumnInfo recordColumnInfo = (RecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Record.class);
        long j3 = recordColumnInfo.idColKey;
        while (it.hasNext()) {
            Record record = (Record) it.next();
            if (!map2.containsKey(record)) {
                if ((record instanceof RealmObjectProxy) && !RealmObject.isFrozen(record)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) record;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(record, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_RecordRealmProxyInterface com_ciot_realm_db_recordrealmproxyinterface = record;
                if (Long.valueOf(com_ciot_realm_db_recordrealmproxyinterface.realmGet$id()) != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j3, com_ciot_realm_db_recordrealmproxyinterface.realmGet$id());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j3, Long.valueOf(com_ciot_realm_db_recordrealmproxyinterface.realmGet$id()));
                }
                long j4 = j;
                map2.put(record, Long.valueOf(j4));
                String realmGet$recordId = com_ciot_realm_db_recordrealmproxyinterface.realmGet$recordId();
                if (realmGet$recordId != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, recordColumnInfo.recordIdColKey, j4, realmGet$recordId, false);
                } else {
                    j2 = j3;
                    Table.nativeSetNull(nativePtr, recordColumnInfo.recordIdColKey, j4, false);
                }
                long j5 = nativePtr;
                long j6 = j4;
                Table.nativeSetDouble(j5, recordColumnInfo.beginColKey, j6, com_ciot_realm_db_recordrealmproxyinterface.realmGet$begin(), false);
                Table.nativeSetDouble(j5, recordColumnInfo.endColKey, j6, com_ciot_realm_db_recordrealmproxyinterface.realmGet$end(), false);
                String realmGet$qrCode = com_ciot_realm_db_recordrealmproxyinterface.realmGet$qrCode();
                if (realmGet$qrCode != null) {
                    Table.nativeSetString(nativePtr, recordColumnInfo.qrCodeColKey, j4, realmGet$qrCode, false);
                } else {
                    Table.nativeSetNull(nativePtr, recordColumnInfo.qrCodeColKey, j4, false);
                }
                String realmGet$healthCode = com_ciot_realm_db_recordrealmproxyinterface.realmGet$healthCode();
                if (realmGet$healthCode != null) {
                    Table.nativeSetString(nativePtr, recordColumnInfo.healthCodeColKey, j4, realmGet$healthCode, false);
                } else {
                    Table.nativeSetNull(nativePtr, recordColumnInfo.healthCodeColKey, j4, false);
                }
                Table.nativeSetLong(nativePtr, recordColumnInfo.healthColorColKey, j4, (long) com_ciot_realm_db_recordrealmproxyinterface.realmGet$healthColor(), false);
                j3 = j2;
            }
        }
    }

    public static Record createDetachedCopy(Record record, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        Record record2;
        if (i > i2 || record == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(record);
        if (cacheData == null) {
            record2 = new Record();
            map.put(record, new RealmObjectProxy.CacheData(i, record2));
        } else if (i >= cacheData.minDepth) {
            return (Record) cacheData.object;
        } else {
            cacheData.minDepth = i;
            record2 = (Record) cacheData.object;
        }
        com_ciot_realm_db_RecordRealmProxyInterface com_ciot_realm_db_recordrealmproxyinterface = record2;
        com_ciot_realm_db_RecordRealmProxyInterface com_ciot_realm_db_recordrealmproxyinterface2 = record;
        com_ciot_realm_db_recordrealmproxyinterface.realmSet$id(com_ciot_realm_db_recordrealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_recordrealmproxyinterface.realmSet$recordId(com_ciot_realm_db_recordrealmproxyinterface2.realmGet$recordId());
        com_ciot_realm_db_recordrealmproxyinterface.realmSet$begin(com_ciot_realm_db_recordrealmproxyinterface2.realmGet$begin());
        com_ciot_realm_db_recordrealmproxyinterface.realmSet$end(com_ciot_realm_db_recordrealmproxyinterface2.realmGet$end());
        com_ciot_realm_db_recordrealmproxyinterface.realmSet$qrCode(com_ciot_realm_db_recordrealmproxyinterface2.realmGet$qrCode());
        com_ciot_realm_db_recordrealmproxyinterface.realmSet$healthCode(com_ciot_realm_db_recordrealmproxyinterface2.realmGet$healthCode());
        com_ciot_realm_db_recordrealmproxyinterface.realmSet$healthColor(com_ciot_realm_db_recordrealmproxyinterface2.realmGet$healthColor());
        return record2;
    }

    static Record update(Realm realm, RecordColumnInfo recordColumnInfo, Record record, Record record2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_RecordRealmProxyInterface com_ciot_realm_db_recordrealmproxyinterface = record;
        com_ciot_realm_db_RecordRealmProxyInterface com_ciot_realm_db_recordrealmproxyinterface2 = record2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Record.class), set);
        osObjectBuilder.addInteger(recordColumnInfo.idColKey, Long.valueOf(com_ciot_realm_db_recordrealmproxyinterface2.realmGet$id()));
        osObjectBuilder.addString(recordColumnInfo.recordIdColKey, com_ciot_realm_db_recordrealmproxyinterface2.realmGet$recordId());
        osObjectBuilder.addDouble(recordColumnInfo.beginColKey, Double.valueOf(com_ciot_realm_db_recordrealmproxyinterface2.realmGet$begin()));
        osObjectBuilder.addDouble(recordColumnInfo.endColKey, Double.valueOf(com_ciot_realm_db_recordrealmproxyinterface2.realmGet$end()));
        osObjectBuilder.addString(recordColumnInfo.qrCodeColKey, com_ciot_realm_db_recordrealmproxyinterface2.realmGet$qrCode());
        osObjectBuilder.addString(recordColumnInfo.healthCodeColKey, com_ciot_realm_db_recordrealmproxyinterface2.realmGet$healthCode());
        osObjectBuilder.addInteger(recordColumnInfo.healthColorColKey, Integer.valueOf(com_ciot_realm_db_recordrealmproxyinterface2.realmGet$healthColor()));
        osObjectBuilder.updateExistingObject();
        return record;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("Record = proxy[");
        sb.append("{id:");
        sb.append(realmGet$id());
        sb.append("}");
        sb.append(",");
        sb.append("{recordId:");
        String realmGet$recordId = realmGet$recordId();
        String str = Configurator.NULL;
        sb.append(realmGet$recordId != null ? realmGet$recordId() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{begin:");
        sb.append(realmGet$begin());
        sb.append("}");
        sb.append(",");
        sb.append("{end:");
        sb.append(realmGet$end());
        sb.append("}");
        sb.append(",");
        sb.append("{qrCode:");
        sb.append(realmGet$qrCode() != null ? realmGet$qrCode() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{healthCode:");
        if (realmGet$healthCode() != null) {
            str = realmGet$healthCode();
        }
        sb.append(str);
        sb.append("}");
        sb.append(",");
        sb.append("{healthColor:");
        sb.append(realmGet$healthColor());
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
        com_ciot_realm_db_RecordRealmProxy com_ciot_realm_db_recordrealmproxy = (com_ciot_realm_db_RecordRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_recordrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_recordrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_recordrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
