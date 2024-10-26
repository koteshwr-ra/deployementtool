package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import androidx.core.app.NotificationCompat;
import com.ciot.realm.db.Person;
import com.ciot.realm.db.TemRecord;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_PersonRealmProxy;
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

public class com_ciot_realm_db_TemRecordRealmProxy extends TemRecord implements RealmObjectProxy, com_ciot_realm_db_TemRecordRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private TemRecordColumnInfo columnInfo;
    private ProxyState<TemRecord> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "TemRecord";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class TemRecordColumnInfo extends ColumnInfo {
        long createtimeColKey;
        long pathnameColKey;
        long personColKey;
        long staffnoColKey;
        long statusColKey;
        long temperatureColKey;

        TemRecordColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(6);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.personColKey = addColumnDetails("person", "person", objectSchemaInfo);
            this.createtimeColKey = addColumnDetails("createtime", "createtime", objectSchemaInfo);
            this.temperatureColKey = addColumnDetails("temperature", "temperature", objectSchemaInfo);
            this.staffnoColKey = addColumnDetails("staffno", "staffno", objectSchemaInfo);
            this.pathnameColKey = addColumnDetails("pathname", "pathname", objectSchemaInfo);
            this.statusColKey = addColumnDetails(NotificationCompat.CATEGORY_STATUS, NotificationCompat.CATEGORY_STATUS, objectSchemaInfo);
        }

        TemRecordColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new TemRecordColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            TemRecordColumnInfo temRecordColumnInfo = (TemRecordColumnInfo) columnInfo;
            TemRecordColumnInfo temRecordColumnInfo2 = (TemRecordColumnInfo) columnInfo2;
            temRecordColumnInfo2.personColKey = temRecordColumnInfo.personColKey;
            temRecordColumnInfo2.createtimeColKey = temRecordColumnInfo.createtimeColKey;
            temRecordColumnInfo2.temperatureColKey = temRecordColumnInfo.temperatureColKey;
            temRecordColumnInfo2.staffnoColKey = temRecordColumnInfo.staffnoColKey;
            temRecordColumnInfo2.pathnameColKey = temRecordColumnInfo.pathnameColKey;
            temRecordColumnInfo2.statusColKey = temRecordColumnInfo.statusColKey;
        }
    }

    com_ciot_realm_db_TemRecordRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (TemRecordColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<TemRecord> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public Person realmGet$person() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.personColKey)) {
            return null;
        }
        return (Person) this.proxyState.getRealm$realm().get(Person.class, this.proxyState.getRow$realm().getLink(this.columnInfo.personColKey), false, Collections.emptyList());
    }

    public void realmSet$person(Person person) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (person == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.personColKey);
                return;
            }
            this.proxyState.checkValidObject(person);
            this.proxyState.getRow$realm().setLink(this.columnInfo.personColKey, ((RealmObjectProxy) person).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("person")) {
            if (person != null && !RealmObject.isManaged(person)) {
                person = (Person) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(person, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (person == null) {
                row$realm.nullifyLink(this.columnInfo.personColKey);
                return;
            }
            this.proxyState.checkValidObject(person);
            row$realm.getTable().setLink(this.columnInfo.personColKey, row$realm.getObjectKey(), ((RealmObjectProxy) person).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public long realmGet$createtime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.createtimeColKey);
    }

    public void realmSet$createtime(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'createtime' cannot be changed after object was created.");
        }
    }

    public float realmGet$temperature() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getFloat(this.columnInfo.temperatureColKey);
    }

    public void realmSet$temperature(float f) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setFloat(this.columnInfo.temperatureColKey, f);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setFloat(this.columnInfo.temperatureColKey, row$realm.getObjectKey(), f, true);
        }
    }

    public String realmGet$staffno() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.staffnoColKey);
    }

    public void realmSet$staffno(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.staffnoColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.staffnoColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.staffnoColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.staffnoColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$pathname() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.pathnameColKey);
    }

    public void realmSet$pathname(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.pathnameColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.pathnameColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.pathnameColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.pathnameColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$status() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.statusColKey);
    }

    public void realmSet$status(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.statusColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.statusColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.statusColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.statusColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 6, 0);
        builder.addPersistedLinkProperty("person", RealmFieldType.OBJECT, com_ciot_realm_db_PersonRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("createtime", RealmFieldType.INTEGER, true, true, true);
        builder2.addPersistedProperty("temperature", RealmFieldType.FLOAT, false, false, true);
        builder2.addPersistedProperty("staffno", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("pathname", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(NotificationCompat.CATEGORY_STATUS, RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TemRecordColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new TemRecordColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x011c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.TemRecord createOrUpdateUsingJsonObject(io.realm.Realm r15, org.json.JSONObject r16, boolean r17) throws org.json.JSONException {
        /*
            r0 = r15
            r7 = r16
            r8 = r17
            java.util.ArrayList r9 = new java.util.ArrayList
            r10 = 1
            r9.<init>(r10)
            java.lang.String r11 = "createtime"
            r12 = 0
            if (r8 == 0) goto L_0x0069
            java.lang.Class<com.ciot.realm.db.TemRecord> r1 = com.ciot.realm.db.TemRecord.class
            io.realm.internal.Table r1 = r15.getTable(r1)
            io.realm.RealmSchema r2 = r15.getSchema()
            java.lang.Class<com.ciot.realm.db.TemRecord> r3 = com.ciot.realm.db.TemRecord.class
            io.realm.internal.ColumnInfo r2 = r2.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)
            io.realm.com_ciot_realm_db_TemRecordRealmProxy$TemRecordColumnInfo r2 = (io.realm.com_ciot_realm_db_TemRecordRealmProxy.TemRecordColumnInfo) r2
            long r2 = r2.createtimeColKey
            boolean r4 = r7.isNull(r11)
            r5 = -1
            if (r4 != 0) goto L_0x0035
            long r13 = r7.getLong(r11)
            long r2 = r1.findFirstLong(r2, r13)
            goto L_0x0036
        L_0x0035:
            r2 = r5
        L_0x0036:
            int r4 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r4 == 0) goto L_0x0069
            io.realm.BaseRealm$ThreadLocalRealmObjectContext r4 = io.realm.BaseRealm.objectContext
            java.lang.Object r4 = r4.get()
            r13 = r4
            io.realm.BaseRealm$RealmObjectContext r13 = (io.realm.BaseRealm.RealmObjectContext) r13
            io.realm.internal.UncheckedRow r3 = r1.getUncheckedRow(r2)     // Catch:{ all -> 0x0064 }
            io.realm.RealmSchema r1 = r15.getSchema()     // Catch:{ all -> 0x0064 }
            java.lang.Class<com.ciot.realm.db.TemRecord> r2 = com.ciot.realm.db.TemRecord.class
            io.realm.internal.ColumnInfo r4 = r1.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r2)     // Catch:{ all -> 0x0064 }
            r5 = 0
            java.util.List r6 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0064 }
            r1 = r13
            r2 = r15
            r1.set(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0064 }
            io.realm.com_ciot_realm_db_TemRecordRealmProxy r1 = new io.realm.com_ciot_realm_db_TemRecordRealmProxy     // Catch:{ all -> 0x0064 }
            r1.<init>()     // Catch:{ all -> 0x0064 }
            r13.clear()
            goto L_0x006a
        L_0x0064:
            r0 = move-exception
            r13.clear()
            throw r0
        L_0x0069:
            r1 = r12
        L_0x006a:
            java.lang.String r2 = "person"
            if (r1 != 0) goto L_0x00a5
            boolean r1 = r7.has(r2)
            if (r1 == 0) goto L_0x0077
            r9.add(r2)
        L_0x0077:
            boolean r1 = r7.has(r11)
            if (r1 == 0) goto L_0x009d
            boolean r1 = r7.isNull(r11)
            if (r1 == 0) goto L_0x008c
            java.lang.Class<com.ciot.realm.db.TemRecord> r1 = com.ciot.realm.db.TemRecord.class
            io.realm.RealmModel r1 = r15.createObjectInternal(r1, r12, r10, r9)
            io.realm.com_ciot_realm_db_TemRecordRealmProxy r1 = (io.realm.com_ciot_realm_db_TemRecordRealmProxy) r1
            goto L_0x00a5
        L_0x008c:
            java.lang.Class<com.ciot.realm.db.TemRecord> r1 = com.ciot.realm.db.TemRecord.class
            long r3 = r7.getLong(r11)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            io.realm.RealmModel r1 = r15.createObjectInternal(r1, r3, r10, r9)
            io.realm.com_ciot_realm_db_TemRecordRealmProxy r1 = (io.realm.com_ciot_realm_db_TemRecordRealmProxy) r1
            goto L_0x00a5
        L_0x009d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "JSON object doesn't have the primary key field 'createtime'."
            r0.<init>(r1)
            throw r0
        L_0x00a5:
            r3 = r1
            io.realm.com_ciot_realm_db_TemRecordRealmProxyInterface r3 = (io.realm.com_ciot_realm_db_TemRecordRealmProxyInterface) r3
            boolean r4 = r7.has(r2)
            if (r4 == 0) goto L_0x00c3
            boolean r4 = r7.isNull(r2)
            if (r4 == 0) goto L_0x00b8
            r3.realmSet$person(r12)
            goto L_0x00c3
        L_0x00b8:
            org.json.JSONObject r2 = r7.getJSONObject(r2)
            com.ciot.realm.db.Person r0 = io.realm.com_ciot_realm_db_PersonRealmProxy.createOrUpdateUsingJsonObject(r15, r2, r8)
            r3.realmSet$person(r0)
        L_0x00c3:
            java.lang.String r0 = "temperature"
            boolean r2 = r7.has(r0)
            if (r2 == 0) goto L_0x00e2
            boolean r2 = r7.isNull(r0)
            if (r2 != 0) goto L_0x00da
            double r4 = r7.getDouble(r0)
            float r0 = (float) r4
            r3.realmSet$temperature(r0)
            goto L_0x00e2
        L_0x00da:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Trying to set non-nullable field 'temperature' to null."
            r0.<init>(r1)
            throw r0
        L_0x00e2:
            java.lang.String r0 = "staffno"
            boolean r2 = r7.has(r0)
            if (r2 == 0) goto L_0x00fb
            boolean r2 = r7.isNull(r0)
            if (r2 == 0) goto L_0x00f4
            r3.realmSet$staffno(r12)
            goto L_0x00fb
        L_0x00f4:
            java.lang.String r0 = r7.getString(r0)
            r3.realmSet$staffno(r0)
        L_0x00fb:
            java.lang.String r0 = "pathname"
            boolean r2 = r7.has(r0)
            if (r2 == 0) goto L_0x0114
            boolean r2 = r7.isNull(r0)
            if (r2 == 0) goto L_0x010d
            r3.realmSet$pathname(r12)
            goto L_0x0114
        L_0x010d:
            java.lang.String r0 = r7.getString(r0)
            r3.realmSet$pathname(r0)
        L_0x0114:
            java.lang.String r0 = "status"
            boolean r2 = r7.has(r0)
            if (r2 == 0) goto L_0x012d
            boolean r2 = r7.isNull(r0)
            if (r2 == 0) goto L_0x0126
            r3.realmSet$status(r12)
            goto L_0x012d
        L_0x0126:
            java.lang.String r0 = r7.getString(r0)
            r3.realmSet$status(r0)
        L_0x012d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_TemRecordRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.TemRecord");
    }

    public static TemRecord createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        TemRecord temRecord = new TemRecord();
        com_ciot_realm_db_TemRecordRealmProxyInterface com_ciot_realm_db_temrecordrealmproxyinterface = temRecord;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("person")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_temrecordrealmproxyinterface.realmSet$person((Person) null);
                } else {
                    com_ciot_realm_db_temrecordrealmproxyinterface.realmSet$person(com_ciot_realm_db_PersonRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("createtime")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_temrecordrealmproxyinterface.realmSet$createtime(jsonReader.nextLong());
                    z = true;
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'createtime' to null.");
                }
            } else if (nextName.equals("temperature")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_temrecordrealmproxyinterface.realmSet$temperature((float) jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'temperature' to null.");
                }
            } else if (nextName.equals("staffno")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_temrecordrealmproxyinterface.realmSet$staffno(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_temrecordrealmproxyinterface.realmSet$staffno((String) null);
                }
            } else if (nextName.equals("pathname")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_temrecordrealmproxyinterface.realmSet$pathname(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_temrecordrealmproxyinterface.realmSet$pathname((String) null);
                }
            } else if (!nextName.equals(NotificationCompat.CATEGORY_STATUS)) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_temrecordrealmproxyinterface.realmSet$status(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_temrecordrealmproxyinterface.realmSet$status((String) null);
            }
        }
        jsonReader.endObject();
        if (z) {
            return (TemRecord) realm.copyToRealm(temRecord, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'createtime'.");
    }

    private static com_ciot_realm_db_TemRecordRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) TemRecord.class), false, Collections.emptyList());
        com_ciot_realm_db_TemRecordRealmProxy com_ciot_realm_db_temrecordrealmproxy = new com_ciot_realm_db_TemRecordRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_temrecordrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.TemRecord copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_TemRecordRealmProxy.TemRecordColumnInfo r9, com.ciot.realm.db.TemRecord r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.TemRecord r1 = (com.ciot.realm.db.TemRecord) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0092
            java.lang.Class<com.ciot.realm.db.TemRecord> r2 = com.ciot.realm.db.TemRecord.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.createtimeColKey
            r5 = r10
            io.realm.com_ciot_realm_db_TemRecordRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_TemRecordRealmProxyInterface) r5
            long r5 = r5.realmGet$createtime()
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
            io.realm.com_ciot_realm_db_TemRecordRealmProxy r1 = new io.realm.com_ciot_realm_db_TemRecordRealmProxy     // Catch:{ all -> 0x008d }
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
            com.ciot.realm.db.TemRecord r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00a4
        L_0x00a0:
            com.ciot.realm.db.TemRecord r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00a4:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_TemRecordRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_TemRecordRealmProxy$TemRecordColumnInfo, com.ciot.realm.db.TemRecord, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.TemRecord");
    }

    public static TemRecord copy(Realm realm, TemRecordColumnInfo temRecordColumnInfo, TemRecord temRecord, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(temRecord);
        if (realmObjectProxy != null) {
            return (TemRecord) realmObjectProxy;
        }
        com_ciot_realm_db_TemRecordRealmProxyInterface com_ciot_realm_db_temrecordrealmproxyinterface = temRecord;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(TemRecord.class), set);
        osObjectBuilder.addInteger(temRecordColumnInfo.createtimeColKey, Long.valueOf(com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$createtime()));
        osObjectBuilder.addFloat(temRecordColumnInfo.temperatureColKey, Float.valueOf(com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$temperature()));
        osObjectBuilder.addString(temRecordColumnInfo.staffnoColKey, com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$staffno());
        osObjectBuilder.addString(temRecordColumnInfo.pathnameColKey, com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$pathname());
        osObjectBuilder.addString(temRecordColumnInfo.statusColKey, com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$status());
        com_ciot_realm_db_TemRecordRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(temRecord, newProxyInstance);
        Person realmGet$person = com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$person();
        if (realmGet$person == null) {
            newProxyInstance.realmSet$person((Person) null);
        } else {
            Person person = (Person) map.get(realmGet$person);
            if (person != null) {
                newProxyInstance.realmSet$person(person);
            } else {
                newProxyInstance.realmSet$person(com_ciot_realm_db_PersonRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_PersonRealmProxy.PersonColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Person.class), realmGet$person, z, map, set));
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, TemRecord temRecord, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        TemRecord temRecord2 = temRecord;
        Map<RealmModel, Long> map2 = map;
        if ((temRecord2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(temRecord)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) temRecord2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(TemRecord.class);
        long nativePtr = table.getNativePtr();
        TemRecordColumnInfo temRecordColumnInfo = (TemRecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TemRecord.class);
        long j2 = temRecordColumnInfo.createtimeColKey;
        com_ciot_realm_db_TemRecordRealmProxyInterface com_ciot_realm_db_temrecordrealmproxyinterface = temRecord2;
        Long valueOf = Long.valueOf(com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$createtime());
        if (valueOf != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$createtime());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$createtime()));
        } else {
            Table.throwDuplicatePrimaryKeyException(valueOf);
        }
        long j3 = j;
        map2.put(temRecord2, Long.valueOf(j3));
        Person realmGet$person = com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$person();
        if (realmGet$person != null) {
            Long l = map2.get(realmGet$person);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_PersonRealmProxy.insert(realm2, realmGet$person, map2));
            }
            Table.nativeSetLink(nativePtr, temRecordColumnInfo.personColKey, j3, l.longValue(), false);
        }
        Table.nativeSetFloat(nativePtr, temRecordColumnInfo.temperatureColKey, j3, com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$temperature(), false);
        String realmGet$staffno = com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$staffno();
        if (realmGet$staffno != null) {
            Table.nativeSetString(nativePtr, temRecordColumnInfo.staffnoColKey, j3, realmGet$staffno, false);
        }
        String realmGet$pathname = com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$pathname();
        if (realmGet$pathname != null) {
            Table.nativeSetString(nativePtr, temRecordColumnInfo.pathnameColKey, j3, realmGet$pathname, false);
        }
        String realmGet$status = com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(nativePtr, temRecordColumnInfo.statusColKey, j3, realmGet$status, false);
        }
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(TemRecord.class);
        long nativePtr = table.getNativePtr();
        TemRecordColumnInfo temRecordColumnInfo = (TemRecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TemRecord.class);
        long j2 = temRecordColumnInfo.createtimeColKey;
        while (it.hasNext()) {
            TemRecord temRecord = (TemRecord) it.next();
            if (!map2.containsKey(temRecord)) {
                if ((temRecord instanceof RealmObjectProxy) && !RealmObject.isFrozen(temRecord)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) temRecord;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(temRecord, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_TemRecordRealmProxyInterface com_ciot_realm_db_temrecordrealmproxyinterface = temRecord;
                Long valueOf = Long.valueOf(com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$createtime());
                if (valueOf != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$createtime());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$createtime()));
                } else {
                    Table.throwDuplicatePrimaryKeyException(valueOf);
                }
                long j3 = j;
                map2.put(temRecord, Long.valueOf(j3));
                Person realmGet$person = com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$person();
                if (realmGet$person != null) {
                    Long l = map2.get(realmGet$person);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_PersonRealmProxy.insert(realm2, realmGet$person, map2));
                    }
                    table.setLink(temRecordColumnInfo.personColKey, j3, l.longValue(), false);
                }
                Table.nativeSetFloat(nativePtr, temRecordColumnInfo.temperatureColKey, j3, com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$temperature(), false);
                String realmGet$staffno = com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$staffno();
                if (realmGet$staffno != null) {
                    Table.nativeSetString(nativePtr, temRecordColumnInfo.staffnoColKey, j3, realmGet$staffno, false);
                }
                String realmGet$pathname = com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$pathname();
                if (realmGet$pathname != null) {
                    Table.nativeSetString(nativePtr, temRecordColumnInfo.pathnameColKey, j3, realmGet$pathname, false);
                }
                String realmGet$status = com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$status();
                if (realmGet$status != null) {
                    Table.nativeSetString(nativePtr, temRecordColumnInfo.statusColKey, j3, realmGet$status, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, TemRecord temRecord, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        TemRecord temRecord2 = temRecord;
        Map<RealmModel, Long> map2 = map;
        if ((temRecord2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(temRecord)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) temRecord2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(TemRecord.class);
        long nativePtr = table.getNativePtr();
        TemRecordColumnInfo temRecordColumnInfo = (TemRecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TemRecord.class);
        long j2 = temRecordColumnInfo.createtimeColKey;
        com_ciot_realm_db_TemRecordRealmProxyInterface com_ciot_realm_db_temrecordrealmproxyinterface = temRecord2;
        if (Long.valueOf(com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$createtime()) != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$createtime());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$createtime()));
        }
        long j3 = j;
        map2.put(temRecord2, Long.valueOf(j3));
        Person realmGet$person = com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$person();
        if (realmGet$person != null) {
            Long l = map2.get(realmGet$person);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_PersonRealmProxy.insertOrUpdate(realm2, realmGet$person, map2));
            }
            Table.nativeSetLink(nativePtr, temRecordColumnInfo.personColKey, j3, l.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, temRecordColumnInfo.personColKey, j3);
        }
        Table.nativeSetFloat(nativePtr, temRecordColumnInfo.temperatureColKey, j3, com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$temperature(), false);
        String realmGet$staffno = com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$staffno();
        if (realmGet$staffno != null) {
            Table.nativeSetString(nativePtr, temRecordColumnInfo.staffnoColKey, j3, realmGet$staffno, false);
        } else {
            Table.nativeSetNull(nativePtr, temRecordColumnInfo.staffnoColKey, j3, false);
        }
        String realmGet$pathname = com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$pathname();
        if (realmGet$pathname != null) {
            Table.nativeSetString(nativePtr, temRecordColumnInfo.pathnameColKey, j3, realmGet$pathname, false);
        } else {
            Table.nativeSetNull(nativePtr, temRecordColumnInfo.pathnameColKey, j3, false);
        }
        String realmGet$status = com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(nativePtr, temRecordColumnInfo.statusColKey, j3, realmGet$status, false);
        } else {
            Table.nativeSetNull(nativePtr, temRecordColumnInfo.statusColKey, j3, false);
        }
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(TemRecord.class);
        long nativePtr = table.getNativePtr();
        TemRecordColumnInfo temRecordColumnInfo = (TemRecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TemRecord.class);
        long j3 = temRecordColumnInfo.createtimeColKey;
        while (it.hasNext()) {
            TemRecord temRecord = (TemRecord) it.next();
            if (!map2.containsKey(temRecord)) {
                if ((temRecord instanceof RealmObjectProxy) && !RealmObject.isFrozen(temRecord)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) temRecord;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(temRecord, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_TemRecordRealmProxyInterface com_ciot_realm_db_temrecordrealmproxyinterface = temRecord;
                if (Long.valueOf(com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$createtime()) != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j3, com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$createtime());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j3, Long.valueOf(com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$createtime()));
                }
                long j4 = j;
                map2.put(temRecord, Long.valueOf(j4));
                Person realmGet$person = com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$person();
                if (realmGet$person != null) {
                    Long l = map2.get(realmGet$person);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_PersonRealmProxy.insertOrUpdate(realm2, realmGet$person, map2));
                    }
                    j2 = j3;
                    Table.nativeSetLink(nativePtr, temRecordColumnInfo.personColKey, j4, l.longValue(), false);
                } else {
                    j2 = j3;
                    Table.nativeNullifyLink(nativePtr, temRecordColumnInfo.personColKey, j4);
                }
                Table.nativeSetFloat(nativePtr, temRecordColumnInfo.temperatureColKey, j4, com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$temperature(), false);
                String realmGet$staffno = com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$staffno();
                if (realmGet$staffno != null) {
                    Table.nativeSetString(nativePtr, temRecordColumnInfo.staffnoColKey, j4, realmGet$staffno, false);
                } else {
                    Table.nativeSetNull(nativePtr, temRecordColumnInfo.staffnoColKey, j4, false);
                }
                String realmGet$pathname = com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$pathname();
                if (realmGet$pathname != null) {
                    Table.nativeSetString(nativePtr, temRecordColumnInfo.pathnameColKey, j4, realmGet$pathname, false);
                } else {
                    Table.nativeSetNull(nativePtr, temRecordColumnInfo.pathnameColKey, j4, false);
                }
                String realmGet$status = com_ciot_realm_db_temrecordrealmproxyinterface.realmGet$status();
                if (realmGet$status != null) {
                    Table.nativeSetString(nativePtr, temRecordColumnInfo.statusColKey, j4, realmGet$status, false);
                } else {
                    Table.nativeSetNull(nativePtr, temRecordColumnInfo.statusColKey, j4, false);
                }
                j3 = j2;
            }
        }
    }

    public static TemRecord createDetachedCopy(TemRecord temRecord, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        TemRecord temRecord2;
        if (i > i2 || temRecord == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(temRecord);
        if (cacheData == null) {
            temRecord2 = new TemRecord();
            map.put(temRecord, new RealmObjectProxy.CacheData(i, temRecord2));
        } else if (i >= cacheData.minDepth) {
            return (TemRecord) cacheData.object;
        } else {
            cacheData.minDepth = i;
            temRecord2 = (TemRecord) cacheData.object;
        }
        com_ciot_realm_db_TemRecordRealmProxyInterface com_ciot_realm_db_temrecordrealmproxyinterface = temRecord2;
        com_ciot_realm_db_TemRecordRealmProxyInterface com_ciot_realm_db_temrecordrealmproxyinterface2 = temRecord;
        com_ciot_realm_db_temrecordrealmproxyinterface.realmSet$person(com_ciot_realm_db_PersonRealmProxy.createDetachedCopy(com_ciot_realm_db_temrecordrealmproxyinterface2.realmGet$person(), i + 1, i2, map));
        com_ciot_realm_db_temrecordrealmproxyinterface.realmSet$createtime(com_ciot_realm_db_temrecordrealmproxyinterface2.realmGet$createtime());
        com_ciot_realm_db_temrecordrealmproxyinterface.realmSet$temperature(com_ciot_realm_db_temrecordrealmproxyinterface2.realmGet$temperature());
        com_ciot_realm_db_temrecordrealmproxyinterface.realmSet$staffno(com_ciot_realm_db_temrecordrealmproxyinterface2.realmGet$staffno());
        com_ciot_realm_db_temrecordrealmproxyinterface.realmSet$pathname(com_ciot_realm_db_temrecordrealmproxyinterface2.realmGet$pathname());
        com_ciot_realm_db_temrecordrealmproxyinterface.realmSet$status(com_ciot_realm_db_temrecordrealmproxyinterface2.realmGet$status());
        return temRecord2;
    }

    static TemRecord update(Realm realm, TemRecordColumnInfo temRecordColumnInfo, TemRecord temRecord, TemRecord temRecord2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_TemRecordRealmProxyInterface com_ciot_realm_db_temrecordrealmproxyinterface = temRecord;
        com_ciot_realm_db_TemRecordRealmProxyInterface com_ciot_realm_db_temrecordrealmproxyinterface2 = temRecord2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(TemRecord.class), set);
        Person realmGet$person = com_ciot_realm_db_temrecordrealmproxyinterface2.realmGet$person();
        if (realmGet$person == null) {
            osObjectBuilder.addNull(temRecordColumnInfo.personColKey);
        } else {
            Person person = (Person) map.get(realmGet$person);
            if (person != null) {
                osObjectBuilder.addObject(temRecordColumnInfo.personColKey, person);
            } else {
                osObjectBuilder.addObject(temRecordColumnInfo.personColKey, com_ciot_realm_db_PersonRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_PersonRealmProxy.PersonColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Person.class), realmGet$person, true, map, set));
            }
        }
        osObjectBuilder.addInteger(temRecordColumnInfo.createtimeColKey, Long.valueOf(com_ciot_realm_db_temrecordrealmproxyinterface2.realmGet$createtime()));
        osObjectBuilder.addFloat(temRecordColumnInfo.temperatureColKey, Float.valueOf(com_ciot_realm_db_temrecordrealmproxyinterface2.realmGet$temperature()));
        osObjectBuilder.addString(temRecordColumnInfo.staffnoColKey, com_ciot_realm_db_temrecordrealmproxyinterface2.realmGet$staffno());
        osObjectBuilder.addString(temRecordColumnInfo.pathnameColKey, com_ciot_realm_db_temrecordrealmproxyinterface2.realmGet$pathname());
        osObjectBuilder.addString(temRecordColumnInfo.statusColKey, com_ciot_realm_db_temrecordrealmproxyinterface2.realmGet$status());
        osObjectBuilder.updateExistingObject();
        return temRecord;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("TemRecord = proxy[");
        sb.append("{person:");
        Person realmGet$person = realmGet$person();
        String str = Configurator.NULL;
        sb.append(realmGet$person != null ? com_ciot_realm_db_PersonRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME : str);
        sb.append("}");
        sb.append(",");
        sb.append("{createtime:");
        sb.append(realmGet$createtime());
        sb.append("}");
        sb.append(",");
        sb.append("{temperature:");
        sb.append(realmGet$temperature());
        sb.append("}");
        sb.append(",");
        sb.append("{staffno:");
        sb.append(realmGet$staffno() != null ? realmGet$staffno() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{pathname:");
        sb.append(realmGet$pathname() != null ? realmGet$pathname() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{status:");
        if (realmGet$status() != null) {
            str = realmGet$status();
        }
        sb.append(str);
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
        com_ciot_realm_db_TemRecordRealmProxy com_ciot_realm_db_temrecordrealmproxy = (com_ciot_realm_db_TemRecordRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_temrecordrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_temrecordrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_temrecordrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
