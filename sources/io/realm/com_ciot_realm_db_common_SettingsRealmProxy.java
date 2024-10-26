package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.common.Settings;
import com.limpoxe.support.servicemanager.ServiceProvider;
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

public class com_ciot_realm_db_common_SettingsRealmProxy extends Settings implements RealmObjectProxy, com_ciot_realm_db_common_SettingsRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private SettingsColumnInfo columnInfo;
    private ProxyState<Settings> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Settings";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class SettingsColumnInfo extends ColumnInfo {
        long companyColKey;
        long contactColKey;
        long idColKey;
        long nameColKey;
        long phoneColKey;
        long securityColKey;
        long typeColKey;

        SettingsColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
            this.typeColKey = addColumnDetails("type", "type", objectSchemaInfo);
            this.companyColKey = addColumnDetails("company", "company", objectSchemaInfo);
            this.contactColKey = addColumnDetails("contact", "contact", objectSchemaInfo);
            this.phoneColKey = addColumnDetails("phone", "phone", objectSchemaInfo);
            this.securityColKey = addColumnDetails("security", "security", objectSchemaInfo);
        }

        SettingsColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new SettingsColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            SettingsColumnInfo settingsColumnInfo = (SettingsColumnInfo) columnInfo;
            SettingsColumnInfo settingsColumnInfo2 = (SettingsColumnInfo) columnInfo2;
            settingsColumnInfo2.idColKey = settingsColumnInfo.idColKey;
            settingsColumnInfo2.nameColKey = settingsColumnInfo.nameColKey;
            settingsColumnInfo2.typeColKey = settingsColumnInfo.typeColKey;
            settingsColumnInfo2.companyColKey = settingsColumnInfo.companyColKey;
            settingsColumnInfo2.contactColKey = settingsColumnInfo.contactColKey;
            settingsColumnInfo2.phoneColKey = settingsColumnInfo.phoneColKey;
            settingsColumnInfo2.securityColKey = settingsColumnInfo.securityColKey;
        }
    }

    com_ciot_realm_db_common_SettingsRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (SettingsColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<Settings> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$id() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.idColKey);
    }

    public void realmSet$id(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'id' cannot be changed after object was created.");
        }
    }

    public String realmGet$name() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.nameColKey);
    }

    public void realmSet$name(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.nameColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.nameColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.nameColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.nameColKey, row$realm.getObjectKey(), str, true);
            }
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

    public String realmGet$company() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.companyColKey);
    }

    public void realmSet$company(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.companyColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.companyColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.companyColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.companyColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$contact() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.contactColKey);
    }

    public void realmSet$contact(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.contactColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.contactColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.contactColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.contactColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$phone() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.phoneColKey);
    }

    public void realmSet$phone(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.phoneColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.phoneColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.phoneColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.phoneColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public int realmGet$security() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.securityColKey);
    }

    public void realmSet$security(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.securityColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.securityColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 7, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("id", RealmFieldType.STRING, true, false, false);
        builder2.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("type", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("company", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("contact", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("phone", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("security", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static SettingsColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new SettingsColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v5, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v6, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x011c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.common.Settings createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            java.lang.String r1 = "id"
            r2 = 0
            if (r14 == 0) goto L_0x0064
            java.lang.Class<com.ciot.realm.db.common.Settings> r14 = com.ciot.realm.db.common.Settings.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.common.Settings> r4 = com.ciot.realm.db.common.Settings.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_common_SettingsRealmProxy$SettingsColumnInfo r3 = (io.realm.com_ciot_realm_db_common_SettingsRealmProxy.SettingsColumnInfo) r3
            long r3 = r3.idColKey
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
            java.lang.Class<com.ciot.realm.db.common.Settings> r3 = com.ciot.realm.db.common.Settings.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005f }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005f }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005f }
            io.realm.com_ciot_realm_db_common_SettingsRealmProxy r14 = new io.realm.com_ciot_realm_db_common_SettingsRealmProxy     // Catch:{ all -> 0x005f }
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
            java.lang.Class<com.ciot.realm.db.common.Settings> r14 = com.ciot.realm.db.common.Settings.class
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_common_SettingsRealmProxy r14 = (io.realm.com_ciot_realm_db_common_SettingsRealmProxy) r14
            goto L_0x0094
        L_0x007e:
            java.lang.Class<com.ciot.realm.db.common.Settings> r14 = com.ciot.realm.db.common.Settings.class
            java.lang.String r1 = r13.getString(r1)
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_common_SettingsRealmProxy r14 = (io.realm.com_ciot_realm_db_common_SettingsRealmProxy) r14
            goto L_0x0094
        L_0x008c:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'id'."
            r12.<init>(r13)
            throw r12
        L_0x0094:
            r12 = r14
            io.realm.com_ciot_realm_db_common_SettingsRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_common_SettingsRealmProxyInterface) r12
            java.lang.String r0 = "name"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00b0
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00a9
            r12.realmSet$name(r2)
            goto L_0x00b0
        L_0x00a9:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$name(r0)
        L_0x00b0:
            java.lang.String r0 = "type"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00c9
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00c2
            r12.realmSet$type(r2)
            goto L_0x00c9
        L_0x00c2:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$type(r0)
        L_0x00c9:
            java.lang.String r0 = "company"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00e2
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00db
            r12.realmSet$company(r2)
            goto L_0x00e2
        L_0x00db:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$company(r0)
        L_0x00e2:
            java.lang.String r0 = "contact"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00fb
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00f4
            r12.realmSet$contact(r2)
            goto L_0x00fb
        L_0x00f4:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$contact(r0)
        L_0x00fb:
            java.lang.String r0 = "phone"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0114
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x010d
            r12.realmSet$phone(r2)
            goto L_0x0114
        L_0x010d:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$phone(r0)
        L_0x0114:
            java.lang.String r0 = "security"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0132
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x012a
            int r13 = r13.getInt(r0)
            r12.realmSet$security(r13)
            goto L_0x0132
        L_0x012a:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'security' to null."
            r12.<init>(r13)
            throw r12
        L_0x0132:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_common_SettingsRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.common.Settings");
    }

    public static Settings createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        Settings settings = new Settings();
        com_ciot_realm_db_common_SettingsRealmProxyInterface com_ciot_realm_db_common_settingsrealmproxyinterface = settings;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$id((String) null);
                }
                z = true;
            } else if (nextName.equals(ServiceProvider.NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$name(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$name((String) null);
                }
            } else if (nextName.equals("type")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$type(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$type((String) null);
                }
            } else if (nextName.equals("company")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$company(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$company((String) null);
                }
            } else if (nextName.equals("contact")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$contact(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$contact((String) null);
                }
            } else if (nextName.equals("phone")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$phone(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$phone((String) null);
                }
            } else if (!nextName.equals("security")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$security(jsonReader.nextInt());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'security' to null.");
            }
        }
        jsonReader.endObject();
        if (z) {
            return (Settings) realm.copyToRealm(settings, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_common_SettingsRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) Settings.class), false, Collections.emptyList());
        com_ciot_realm_db_common_SettingsRealmProxy com_ciot_realm_db_common_settingsrealmproxy = new com_ciot_realm_db_common_SettingsRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_common_settingsrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.common.Settings copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_common_SettingsRealmProxy.SettingsColumnInfo r9, com.ciot.realm.db.common.Settings r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.common.Settings r1 = (com.ciot.realm.db.common.Settings) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.common.Settings> r2 = com.ciot.realm.db.common.Settings.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_common_SettingsRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_common_SettingsRealmProxyInterface) r5
            java.lang.String r5 = r5.realmGet$id()
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
            io.realm.com_ciot_realm_db_common_SettingsRealmProxy r1 = new io.realm.com_ciot_realm_db_common_SettingsRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.common.Settings r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.common.Settings r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_common_SettingsRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_common_SettingsRealmProxy$SettingsColumnInfo, com.ciot.realm.db.common.Settings, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.common.Settings");
    }

    public static Settings copy(Realm realm, SettingsColumnInfo settingsColumnInfo, Settings settings, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(settings);
        if (realmObjectProxy != null) {
            return (Settings) realmObjectProxy;
        }
        com_ciot_realm_db_common_SettingsRealmProxyInterface com_ciot_realm_db_common_settingsrealmproxyinterface = settings;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Settings.class), set);
        osObjectBuilder.addString(settingsColumnInfo.idColKey, com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$id());
        osObjectBuilder.addString(settingsColumnInfo.nameColKey, com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$name());
        osObjectBuilder.addString(settingsColumnInfo.typeColKey, com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$type());
        osObjectBuilder.addString(settingsColumnInfo.companyColKey, com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$company());
        osObjectBuilder.addString(settingsColumnInfo.contactColKey, com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$contact());
        osObjectBuilder.addString(settingsColumnInfo.phoneColKey, com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$phone());
        osObjectBuilder.addInteger(settingsColumnInfo.securityColKey, Integer.valueOf(com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$security()));
        com_ciot_realm_db_common_SettingsRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(settings, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, Settings settings, Map<RealmModel, Long> map) {
        long j;
        Settings settings2 = settings;
        if ((settings2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(settings)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) settings2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Settings.class);
        long nativePtr = table.getNativePtr();
        SettingsColumnInfo settingsColumnInfo = (SettingsColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Settings.class);
        long j2 = settingsColumnInfo.idColKey;
        com_ciot_realm_db_common_SettingsRealmProxyInterface com_ciot_realm_db_common_settingsrealmproxyinterface = settings2;
        String realmGet$id = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$id);
        } else {
            Table.throwDuplicatePrimaryKeyException(realmGet$id);
        }
        long j3 = j;
        map.put(settings2, Long.valueOf(j3));
        String realmGet$name = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, settingsColumnInfo.nameColKey, j3, realmGet$name, false);
        }
        String realmGet$type = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(nativePtr, settingsColumnInfo.typeColKey, j3, realmGet$type, false);
        }
        String realmGet$company = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$company();
        if (realmGet$company != null) {
            Table.nativeSetString(nativePtr, settingsColumnInfo.companyColKey, j3, realmGet$company, false);
        }
        String realmGet$contact = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$contact();
        if (realmGet$contact != null) {
            Table.nativeSetString(nativePtr, settingsColumnInfo.contactColKey, j3, realmGet$contact, false);
        }
        String realmGet$phone = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$phone();
        if (realmGet$phone != null) {
            Table.nativeSetString(nativePtr, settingsColumnInfo.phoneColKey, j3, realmGet$phone, false);
        }
        Table.nativeSetLong(nativePtr, settingsColumnInfo.securityColKey, j3, (long) com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$security(), false);
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Settings.class);
        long nativePtr = table.getNativePtr();
        SettingsColumnInfo settingsColumnInfo = (SettingsColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Settings.class);
        long j4 = settingsColumnInfo.idColKey;
        while (it.hasNext()) {
            Settings settings = (Settings) it.next();
            if (!map2.containsKey(settings)) {
                if ((settings instanceof RealmObjectProxy) && !RealmObject.isFrozen(settings)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) settings;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(settings, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_common_SettingsRealmProxyInterface com_ciot_realm_db_common_settingsrealmproxyinterface = settings;
                String realmGet$id = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j4);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j4, realmGet$id);
                }
                if (j == -1) {
                    j2 = OsObject.createRowWithPrimaryKey(table, j4, realmGet$id);
                } else {
                    Table.throwDuplicatePrimaryKeyException(realmGet$id);
                    j2 = j;
                }
                map2.put(settings, Long.valueOf(j2));
                String realmGet$name = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    j3 = j4;
                    Table.nativeSetString(nativePtr, settingsColumnInfo.nameColKey, j2, realmGet$name, false);
                } else {
                    j3 = j4;
                }
                String realmGet$type = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$type();
                if (realmGet$type != null) {
                    Table.nativeSetString(nativePtr, settingsColumnInfo.typeColKey, j2, realmGet$type, false);
                }
                String realmGet$company = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$company();
                if (realmGet$company != null) {
                    Table.nativeSetString(nativePtr, settingsColumnInfo.companyColKey, j2, realmGet$company, false);
                }
                String realmGet$contact = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$contact();
                if (realmGet$contact != null) {
                    Table.nativeSetString(nativePtr, settingsColumnInfo.contactColKey, j2, realmGet$contact, false);
                }
                String realmGet$phone = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$phone();
                if (realmGet$phone != null) {
                    Table.nativeSetString(nativePtr, settingsColumnInfo.phoneColKey, j2, realmGet$phone, false);
                }
                Table.nativeSetLong(nativePtr, settingsColumnInfo.securityColKey, j2, (long) com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$security(), false);
                j4 = j3;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Settings settings, Map<RealmModel, Long> map) {
        long j;
        Settings settings2 = settings;
        if ((settings2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(settings)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) settings2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Settings.class);
        long nativePtr = table.getNativePtr();
        SettingsColumnInfo settingsColumnInfo = (SettingsColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Settings.class);
        long j2 = settingsColumnInfo.idColKey;
        com_ciot_realm_db_common_SettingsRealmProxyInterface com_ciot_realm_db_common_settingsrealmproxyinterface = settings2;
        String realmGet$id = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$id);
        }
        long j3 = j;
        map.put(settings2, Long.valueOf(j3));
        String realmGet$name = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, settingsColumnInfo.nameColKey, j3, realmGet$name, false);
        } else {
            Table.nativeSetNull(nativePtr, settingsColumnInfo.nameColKey, j3, false);
        }
        String realmGet$type = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(nativePtr, settingsColumnInfo.typeColKey, j3, realmGet$type, false);
        } else {
            Table.nativeSetNull(nativePtr, settingsColumnInfo.typeColKey, j3, false);
        }
        String realmGet$company = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$company();
        if (realmGet$company != null) {
            Table.nativeSetString(nativePtr, settingsColumnInfo.companyColKey, j3, realmGet$company, false);
        } else {
            Table.nativeSetNull(nativePtr, settingsColumnInfo.companyColKey, j3, false);
        }
        String realmGet$contact = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$contact();
        if (realmGet$contact != null) {
            Table.nativeSetString(nativePtr, settingsColumnInfo.contactColKey, j3, realmGet$contact, false);
        } else {
            Table.nativeSetNull(nativePtr, settingsColumnInfo.contactColKey, j3, false);
        }
        String realmGet$phone = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$phone();
        if (realmGet$phone != null) {
            Table.nativeSetString(nativePtr, settingsColumnInfo.phoneColKey, j3, realmGet$phone, false);
        } else {
            Table.nativeSetNull(nativePtr, settingsColumnInfo.phoneColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, settingsColumnInfo.securityColKey, j3, (long) com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$security(), false);
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Settings.class);
        long nativePtr = table.getNativePtr();
        SettingsColumnInfo settingsColumnInfo = (SettingsColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Settings.class);
        long j3 = settingsColumnInfo.idColKey;
        while (it.hasNext()) {
            Settings settings = (Settings) it.next();
            if (!map2.containsKey(settings)) {
                if ((settings instanceof RealmObjectProxy) && !RealmObject.isFrozen(settings)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) settings;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(settings, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_common_SettingsRealmProxyInterface com_ciot_realm_db_common_settingsrealmproxyinterface = settings;
                String realmGet$id = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j3);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j3, realmGet$id);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j3, realmGet$id) : j;
                map2.put(settings, Long.valueOf(createRowWithPrimaryKey));
                String realmGet$name = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, settingsColumnInfo.nameColKey, createRowWithPrimaryKey, realmGet$name, false);
                } else {
                    j2 = j3;
                    Table.nativeSetNull(nativePtr, settingsColumnInfo.nameColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$type = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$type();
                if (realmGet$type != null) {
                    Table.nativeSetString(nativePtr, settingsColumnInfo.typeColKey, createRowWithPrimaryKey, realmGet$type, false);
                } else {
                    Table.nativeSetNull(nativePtr, settingsColumnInfo.typeColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$company = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$company();
                if (realmGet$company != null) {
                    Table.nativeSetString(nativePtr, settingsColumnInfo.companyColKey, createRowWithPrimaryKey, realmGet$company, false);
                } else {
                    Table.nativeSetNull(nativePtr, settingsColumnInfo.companyColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$contact = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$contact();
                if (realmGet$contact != null) {
                    Table.nativeSetString(nativePtr, settingsColumnInfo.contactColKey, createRowWithPrimaryKey, realmGet$contact, false);
                } else {
                    Table.nativeSetNull(nativePtr, settingsColumnInfo.contactColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$phone = com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$phone();
                if (realmGet$phone != null) {
                    Table.nativeSetString(nativePtr, settingsColumnInfo.phoneColKey, createRowWithPrimaryKey, realmGet$phone, false);
                } else {
                    Table.nativeSetNull(nativePtr, settingsColumnInfo.phoneColKey, createRowWithPrimaryKey, false);
                }
                Table.nativeSetLong(nativePtr, settingsColumnInfo.securityColKey, createRowWithPrimaryKey, (long) com_ciot_realm_db_common_settingsrealmproxyinterface.realmGet$security(), false);
                j3 = j2;
            }
        }
    }

    public static Settings createDetachedCopy(Settings settings, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        Settings settings2;
        if (i > i2 || settings == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(settings);
        if (cacheData == null) {
            settings2 = new Settings();
            map.put(settings, new RealmObjectProxy.CacheData(i, settings2));
        } else if (i >= cacheData.minDepth) {
            return (Settings) cacheData.object;
        } else {
            cacheData.minDepth = i;
            settings2 = (Settings) cacheData.object;
        }
        com_ciot_realm_db_common_SettingsRealmProxyInterface com_ciot_realm_db_common_settingsrealmproxyinterface = settings2;
        com_ciot_realm_db_common_SettingsRealmProxyInterface com_ciot_realm_db_common_settingsrealmproxyinterface2 = settings;
        com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$id(com_ciot_realm_db_common_settingsrealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$name(com_ciot_realm_db_common_settingsrealmproxyinterface2.realmGet$name());
        com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$type(com_ciot_realm_db_common_settingsrealmproxyinterface2.realmGet$type());
        com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$company(com_ciot_realm_db_common_settingsrealmproxyinterface2.realmGet$company());
        com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$contact(com_ciot_realm_db_common_settingsrealmproxyinterface2.realmGet$contact());
        com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$phone(com_ciot_realm_db_common_settingsrealmproxyinterface2.realmGet$phone());
        com_ciot_realm_db_common_settingsrealmproxyinterface.realmSet$security(com_ciot_realm_db_common_settingsrealmproxyinterface2.realmGet$security());
        return settings2;
    }

    static Settings update(Realm realm, SettingsColumnInfo settingsColumnInfo, Settings settings, Settings settings2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_common_SettingsRealmProxyInterface com_ciot_realm_db_common_settingsrealmproxyinterface = settings;
        com_ciot_realm_db_common_SettingsRealmProxyInterface com_ciot_realm_db_common_settingsrealmproxyinterface2 = settings2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Settings.class), set);
        osObjectBuilder.addString(settingsColumnInfo.idColKey, com_ciot_realm_db_common_settingsrealmproxyinterface2.realmGet$id());
        osObjectBuilder.addString(settingsColumnInfo.nameColKey, com_ciot_realm_db_common_settingsrealmproxyinterface2.realmGet$name());
        osObjectBuilder.addString(settingsColumnInfo.typeColKey, com_ciot_realm_db_common_settingsrealmproxyinterface2.realmGet$type());
        osObjectBuilder.addString(settingsColumnInfo.companyColKey, com_ciot_realm_db_common_settingsrealmproxyinterface2.realmGet$company());
        osObjectBuilder.addString(settingsColumnInfo.contactColKey, com_ciot_realm_db_common_settingsrealmproxyinterface2.realmGet$contact());
        osObjectBuilder.addString(settingsColumnInfo.phoneColKey, com_ciot_realm_db_common_settingsrealmproxyinterface2.realmGet$phone());
        osObjectBuilder.addInteger(settingsColumnInfo.securityColKey, Integer.valueOf(com_ciot_realm_db_common_settingsrealmproxyinterface2.realmGet$security()));
        osObjectBuilder.updateExistingObject();
        return settings;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("Settings = proxy[");
        sb.append("{id:");
        String realmGet$id = realmGet$id();
        String str = Configurator.NULL;
        sb.append(realmGet$id != null ? realmGet$id() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{name:");
        sb.append(realmGet$name() != null ? realmGet$name() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{type:");
        sb.append(realmGet$type() != null ? realmGet$type() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{company:");
        sb.append(realmGet$company() != null ? realmGet$company() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{contact:");
        sb.append(realmGet$contact() != null ? realmGet$contact() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{phone:");
        if (realmGet$phone() != null) {
            str = realmGet$phone();
        }
        sb.append(str);
        sb.append("}");
        sb.append(",");
        sb.append("{security:");
        sb.append(realmGet$security());
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
        com_ciot_realm_db_common_SettingsRealmProxy com_ciot_realm_db_common_settingsrealmproxy = (com_ciot_realm_db_common_SettingsRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_common_settingsrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_common_settingsrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_common_settingsrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
