package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.common.VisitorBean;
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

public class com_ciot_realm_db_common_VisitorBeanRealmProxy extends VisitorBean implements RealmObjectProxy, com_ciot_realm_db_common_VisitorBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private VisitorBeanColumnInfo columnInfo;
    private ProxyState<VisitorBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "VisitorBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class VisitorBeanColumnInfo extends ColumnInfo {
        long companyColKey;
        long faceColKey;
        long idColKey;
        long idcardColKey;
        long lanpathColKey;
        long nameColKey;
        long pathColKey;
        long phoneColKey;
        long typeColKey;

        VisitorBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(9);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
            this.companyColKey = addColumnDetails("company", "company", objectSchemaInfo);
            this.idcardColKey = addColumnDetails("idcard", "idcard", objectSchemaInfo);
            this.phoneColKey = addColumnDetails("phone", "phone", objectSchemaInfo);
            this.faceColKey = addColumnDetails("face", "face", objectSchemaInfo);
            this.typeColKey = addColumnDetails("type", "type", objectSchemaInfo);
            this.pathColKey = addColumnDetails("path", "path", objectSchemaInfo);
            this.lanpathColKey = addColumnDetails("lanpath", "lanpath", objectSchemaInfo);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
        }

        VisitorBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new VisitorBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            VisitorBeanColumnInfo visitorBeanColumnInfo = (VisitorBeanColumnInfo) columnInfo;
            VisitorBeanColumnInfo visitorBeanColumnInfo2 = (VisitorBeanColumnInfo) columnInfo2;
            visitorBeanColumnInfo2.nameColKey = visitorBeanColumnInfo.nameColKey;
            visitorBeanColumnInfo2.companyColKey = visitorBeanColumnInfo.companyColKey;
            visitorBeanColumnInfo2.idcardColKey = visitorBeanColumnInfo.idcardColKey;
            visitorBeanColumnInfo2.phoneColKey = visitorBeanColumnInfo.phoneColKey;
            visitorBeanColumnInfo2.faceColKey = visitorBeanColumnInfo.faceColKey;
            visitorBeanColumnInfo2.typeColKey = visitorBeanColumnInfo.typeColKey;
            visitorBeanColumnInfo2.pathColKey = visitorBeanColumnInfo.pathColKey;
            visitorBeanColumnInfo2.lanpathColKey = visitorBeanColumnInfo.lanpathColKey;
            visitorBeanColumnInfo2.idColKey = visitorBeanColumnInfo.idColKey;
        }
    }

    com_ciot_realm_db_common_VisitorBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (VisitorBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<VisitorBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
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

    public String realmGet$idcard() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.idcardColKey);
    }

    public void realmSet$idcard(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.idcardColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.idcardColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.idcardColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.idcardColKey, row$realm.getObjectKey(), str, true);
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

    public String realmGet$face() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.faceColKey);
    }

    public void realmSet$face(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.faceColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.faceColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.faceColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.faceColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public int realmGet$type() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.typeColKey);
    }

    public void realmSet$type(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.typeColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.typeColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public String realmGet$path() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.pathColKey);
    }

    public void realmSet$path(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.pathColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.pathColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.pathColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.pathColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$lanpath() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.lanpathColKey);
    }

    public void realmSet$lanpath(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.lanpathColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.lanpathColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.lanpathColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.lanpathColKey, row$realm.getObjectKey(), str, true);
            }
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 9, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("company", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("idcard", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("phone", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("face", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("type", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("path", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("lanpath", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("id", RealmFieldType.STRING, true, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static VisitorBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new VisitorBeanColumnInfo(osSchemaInfo);
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
    /* JADX WARNING: Removed duplicated region for block: B:64:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0153  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.common.VisitorBean createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            java.lang.String r1 = "id"
            r2 = 0
            if (r14 == 0) goto L_0x0064
            java.lang.Class<com.ciot.realm.db.common.VisitorBean> r14 = com.ciot.realm.db.common.VisitorBean.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.common.VisitorBean> r4 = com.ciot.realm.db.common.VisitorBean.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxy$VisitorBeanColumnInfo r3 = (io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxy.VisitorBeanColumnInfo) r3
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
            java.lang.Class<com.ciot.realm.db.common.VisitorBean> r3 = com.ciot.realm.db.common.VisitorBean.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005f }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005f }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005f }
            io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxy r14 = new io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxy     // Catch:{ all -> 0x005f }
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
            java.lang.Class<com.ciot.realm.db.common.VisitorBean> r14 = com.ciot.realm.db.common.VisitorBean.class
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxy r14 = (io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxy) r14
            goto L_0x0094
        L_0x007e:
            java.lang.Class<com.ciot.realm.db.common.VisitorBean> r14 = com.ciot.realm.db.common.VisitorBean.class
            java.lang.String r1 = r13.getString(r1)
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxy r14 = (io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxy) r14
            goto L_0x0094
        L_0x008c:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'id'."
            r12.<init>(r13)
            throw r12
        L_0x0094:
            r12 = r14
            io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxyInterface) r12
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
            java.lang.String r0 = "company"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00c9
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00c2
            r12.realmSet$company(r2)
            goto L_0x00c9
        L_0x00c2:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$company(r0)
        L_0x00c9:
            java.lang.String r0 = "idcard"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00e2
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00db
            r12.realmSet$idcard(r2)
            goto L_0x00e2
        L_0x00db:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$idcard(r0)
        L_0x00e2:
            java.lang.String r0 = "phone"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00fb
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00f4
            r12.realmSet$phone(r2)
            goto L_0x00fb
        L_0x00f4:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$phone(r0)
        L_0x00fb:
            java.lang.String r0 = "face"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0114
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x010d
            r12.realmSet$face(r2)
            goto L_0x0114
        L_0x010d:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$face(r0)
        L_0x0114:
            java.lang.String r0 = "type"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0132
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x012a
            int r0 = r13.getInt(r0)
            r12.realmSet$type(r0)
            goto L_0x0132
        L_0x012a:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'type' to null."
            r12.<init>(r13)
            throw r12
        L_0x0132:
            java.lang.String r0 = "path"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x014b
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0144
            r12.realmSet$path(r2)
            goto L_0x014b
        L_0x0144:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$path(r0)
        L_0x014b:
            java.lang.String r0 = "lanpath"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0164
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x015d
            r12.realmSet$lanpath(r2)
            goto L_0x0164
        L_0x015d:
            java.lang.String r13 = r13.getString(r0)
            r12.realmSet$lanpath(r13)
        L_0x0164:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.common.VisitorBean");
    }

    public static VisitorBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        VisitorBean visitorBean = new VisitorBean();
        com_ciot_realm_db_common_VisitorBeanRealmProxyInterface com_ciot_realm_db_common_visitorbeanrealmproxyinterface = visitorBean;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(ServiceProvider.NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$name(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$name((String) null);
                }
            } else if (nextName.equals("company")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$company(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$company((String) null);
                }
            } else if (nextName.equals("idcard")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$idcard(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$idcard((String) null);
                }
            } else if (nextName.equals("phone")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$phone(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$phone((String) null);
                }
            } else if (nextName.equals("face")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$face(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$face((String) null);
                }
            } else if (nextName.equals("type")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$type(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'type' to null.");
                }
            } else if (nextName.equals("path")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$path(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$path((String) null);
                }
            } else if (nextName.equals("lanpath")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$lanpath(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$lanpath((String) null);
                }
            } else if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$id((String) null);
                }
                z = true;
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        if (z) {
            return (VisitorBean) realm.copyToRealm(visitorBean, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_common_VisitorBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) VisitorBean.class), false, Collections.emptyList());
        com_ciot_realm_db_common_VisitorBeanRealmProxy com_ciot_realm_db_common_visitorbeanrealmproxy = new com_ciot_realm_db_common_VisitorBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_common_visitorbeanrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.common.VisitorBean copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxy.VisitorBeanColumnInfo r9, com.ciot.realm.db.common.VisitorBean r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.common.VisitorBean r1 = (com.ciot.realm.db.common.VisitorBean) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.common.VisitorBean> r2 = com.ciot.realm.db.common.VisitorBean.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxyInterface) r5
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
            io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxy r1 = new io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.common.VisitorBean r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.common.VisitorBean r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxy$VisitorBeanColumnInfo, com.ciot.realm.db.common.VisitorBean, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.common.VisitorBean");
    }

    public static VisitorBean copy(Realm realm, VisitorBeanColumnInfo visitorBeanColumnInfo, VisitorBean visitorBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(visitorBean);
        if (realmObjectProxy != null) {
            return (VisitorBean) realmObjectProxy;
        }
        com_ciot_realm_db_common_VisitorBeanRealmProxyInterface com_ciot_realm_db_common_visitorbeanrealmproxyinterface = visitorBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(VisitorBean.class), set);
        osObjectBuilder.addString(visitorBeanColumnInfo.nameColKey, com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$name());
        osObjectBuilder.addString(visitorBeanColumnInfo.companyColKey, com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$company());
        osObjectBuilder.addString(visitorBeanColumnInfo.idcardColKey, com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$idcard());
        osObjectBuilder.addString(visitorBeanColumnInfo.phoneColKey, com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$phone());
        osObjectBuilder.addString(visitorBeanColumnInfo.faceColKey, com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$face());
        osObjectBuilder.addInteger(visitorBeanColumnInfo.typeColKey, Integer.valueOf(com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$type()));
        osObjectBuilder.addString(visitorBeanColumnInfo.pathColKey, com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$path());
        osObjectBuilder.addString(visitorBeanColumnInfo.lanpathColKey, com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$lanpath());
        osObjectBuilder.addString(visitorBeanColumnInfo.idColKey, com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$id());
        com_ciot_realm_db_common_VisitorBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(visitorBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, VisitorBean visitorBean, Map<RealmModel, Long> map) {
        long j;
        VisitorBean visitorBean2 = visitorBean;
        if ((visitorBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(visitorBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) visitorBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(VisitorBean.class);
        long nativePtr = table.getNativePtr();
        VisitorBeanColumnInfo visitorBeanColumnInfo = (VisitorBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) VisitorBean.class);
        long j2 = visitorBeanColumnInfo.idColKey;
        com_ciot_realm_db_common_VisitorBeanRealmProxyInterface com_ciot_realm_db_common_visitorbeanrealmproxyinterface = visitorBean2;
        String realmGet$id = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$id();
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
        map.put(visitorBean2, Long.valueOf(j3));
        String realmGet$name = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, visitorBeanColumnInfo.nameColKey, j3, realmGet$name, false);
        }
        String realmGet$company = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$company();
        if (realmGet$company != null) {
            Table.nativeSetString(nativePtr, visitorBeanColumnInfo.companyColKey, j3, realmGet$company, false);
        }
        String realmGet$idcard = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$idcard();
        if (realmGet$idcard != null) {
            Table.nativeSetString(nativePtr, visitorBeanColumnInfo.idcardColKey, j3, realmGet$idcard, false);
        }
        String realmGet$phone = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$phone();
        if (realmGet$phone != null) {
            Table.nativeSetString(nativePtr, visitorBeanColumnInfo.phoneColKey, j3, realmGet$phone, false);
        }
        String realmGet$face = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$face();
        if (realmGet$face != null) {
            Table.nativeSetString(nativePtr, visitorBeanColumnInfo.faceColKey, j3, realmGet$face, false);
        }
        Table.nativeSetLong(nativePtr, visitorBeanColumnInfo.typeColKey, j3, (long) com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$type(), false);
        String realmGet$path = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$path();
        if (realmGet$path != null) {
            Table.nativeSetString(nativePtr, visitorBeanColumnInfo.pathColKey, j3, realmGet$path, false);
        }
        String realmGet$lanpath = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$lanpath();
        if (realmGet$lanpath != null) {
            Table.nativeSetString(nativePtr, visitorBeanColumnInfo.lanpathColKey, j3, realmGet$lanpath, false);
        }
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(VisitorBean.class);
        long nativePtr = table.getNativePtr();
        VisitorBeanColumnInfo visitorBeanColumnInfo = (VisitorBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) VisitorBean.class);
        long j4 = visitorBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            VisitorBean visitorBean = (VisitorBean) it.next();
            if (!map2.containsKey(visitorBean)) {
                if ((visitorBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(visitorBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) visitorBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(visitorBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_common_VisitorBeanRealmProxyInterface com_ciot_realm_db_common_visitorbeanrealmproxyinterface = visitorBean;
                String realmGet$id = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$id();
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
                map2.put(visitorBean, Long.valueOf(j2));
                String realmGet$name = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    j3 = j4;
                    Table.nativeSetString(nativePtr, visitorBeanColumnInfo.nameColKey, j2, realmGet$name, false);
                } else {
                    j3 = j4;
                }
                String realmGet$company = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$company();
                if (realmGet$company != null) {
                    Table.nativeSetString(nativePtr, visitorBeanColumnInfo.companyColKey, j2, realmGet$company, false);
                }
                String realmGet$idcard = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$idcard();
                if (realmGet$idcard != null) {
                    Table.nativeSetString(nativePtr, visitorBeanColumnInfo.idcardColKey, j2, realmGet$idcard, false);
                }
                String realmGet$phone = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$phone();
                if (realmGet$phone != null) {
                    Table.nativeSetString(nativePtr, visitorBeanColumnInfo.phoneColKey, j2, realmGet$phone, false);
                }
                String realmGet$face = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$face();
                if (realmGet$face != null) {
                    Table.nativeSetString(nativePtr, visitorBeanColumnInfo.faceColKey, j2, realmGet$face, false);
                }
                Table.nativeSetLong(nativePtr, visitorBeanColumnInfo.typeColKey, j2, (long) com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$type(), false);
                String realmGet$path = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$path();
                if (realmGet$path != null) {
                    Table.nativeSetString(nativePtr, visitorBeanColumnInfo.pathColKey, j2, realmGet$path, false);
                }
                String realmGet$lanpath = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$lanpath();
                if (realmGet$lanpath != null) {
                    Table.nativeSetString(nativePtr, visitorBeanColumnInfo.lanpathColKey, j2, realmGet$lanpath, false);
                }
                j4 = j3;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, VisitorBean visitorBean, Map<RealmModel, Long> map) {
        long j;
        VisitorBean visitorBean2 = visitorBean;
        if ((visitorBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(visitorBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) visitorBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(VisitorBean.class);
        long nativePtr = table.getNativePtr();
        VisitorBeanColumnInfo visitorBeanColumnInfo = (VisitorBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) VisitorBean.class);
        long j2 = visitorBeanColumnInfo.idColKey;
        com_ciot_realm_db_common_VisitorBeanRealmProxyInterface com_ciot_realm_db_common_visitorbeanrealmproxyinterface = visitorBean2;
        String realmGet$id = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$id);
        }
        long j3 = j;
        map.put(visitorBean2, Long.valueOf(j3));
        String realmGet$name = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, visitorBeanColumnInfo.nameColKey, j3, realmGet$name, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorBeanColumnInfo.nameColKey, j3, false);
        }
        String realmGet$company = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$company();
        if (realmGet$company != null) {
            Table.nativeSetString(nativePtr, visitorBeanColumnInfo.companyColKey, j3, realmGet$company, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorBeanColumnInfo.companyColKey, j3, false);
        }
        String realmGet$idcard = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$idcard();
        if (realmGet$idcard != null) {
            Table.nativeSetString(nativePtr, visitorBeanColumnInfo.idcardColKey, j3, realmGet$idcard, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorBeanColumnInfo.idcardColKey, j3, false);
        }
        String realmGet$phone = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$phone();
        if (realmGet$phone != null) {
            Table.nativeSetString(nativePtr, visitorBeanColumnInfo.phoneColKey, j3, realmGet$phone, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorBeanColumnInfo.phoneColKey, j3, false);
        }
        String realmGet$face = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$face();
        if (realmGet$face != null) {
            Table.nativeSetString(nativePtr, visitorBeanColumnInfo.faceColKey, j3, realmGet$face, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorBeanColumnInfo.faceColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, visitorBeanColumnInfo.typeColKey, j3, (long) com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$type(), false);
        String realmGet$path = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$path();
        if (realmGet$path != null) {
            Table.nativeSetString(nativePtr, visitorBeanColumnInfo.pathColKey, j3, realmGet$path, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorBeanColumnInfo.pathColKey, j3, false);
        }
        String realmGet$lanpath = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$lanpath();
        if (realmGet$lanpath != null) {
            Table.nativeSetString(nativePtr, visitorBeanColumnInfo.lanpathColKey, j3, realmGet$lanpath, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorBeanColumnInfo.lanpathColKey, j3, false);
        }
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(VisitorBean.class);
        long nativePtr = table.getNativePtr();
        VisitorBeanColumnInfo visitorBeanColumnInfo = (VisitorBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) VisitorBean.class);
        long j3 = visitorBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            VisitorBean visitorBean = (VisitorBean) it.next();
            if (!map2.containsKey(visitorBean)) {
                if ((visitorBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(visitorBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) visitorBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(visitorBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_common_VisitorBeanRealmProxyInterface com_ciot_realm_db_common_visitorbeanrealmproxyinterface = visitorBean;
                String realmGet$id = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j3);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j3, realmGet$id);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j3, realmGet$id) : j;
                map2.put(visitorBean, Long.valueOf(createRowWithPrimaryKey));
                String realmGet$name = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, visitorBeanColumnInfo.nameColKey, createRowWithPrimaryKey, realmGet$name, false);
                } else {
                    j2 = j3;
                    Table.nativeSetNull(nativePtr, visitorBeanColumnInfo.nameColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$company = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$company();
                if (realmGet$company != null) {
                    Table.nativeSetString(nativePtr, visitorBeanColumnInfo.companyColKey, createRowWithPrimaryKey, realmGet$company, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorBeanColumnInfo.companyColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$idcard = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$idcard();
                if (realmGet$idcard != null) {
                    Table.nativeSetString(nativePtr, visitorBeanColumnInfo.idcardColKey, createRowWithPrimaryKey, realmGet$idcard, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorBeanColumnInfo.idcardColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$phone = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$phone();
                if (realmGet$phone != null) {
                    Table.nativeSetString(nativePtr, visitorBeanColumnInfo.phoneColKey, createRowWithPrimaryKey, realmGet$phone, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorBeanColumnInfo.phoneColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$face = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$face();
                if (realmGet$face != null) {
                    Table.nativeSetString(nativePtr, visitorBeanColumnInfo.faceColKey, createRowWithPrimaryKey, realmGet$face, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorBeanColumnInfo.faceColKey, createRowWithPrimaryKey, false);
                }
                Table.nativeSetLong(nativePtr, visitorBeanColumnInfo.typeColKey, createRowWithPrimaryKey, (long) com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$type(), false);
                String realmGet$path = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$path();
                if (realmGet$path != null) {
                    Table.nativeSetString(nativePtr, visitorBeanColumnInfo.pathColKey, createRowWithPrimaryKey, realmGet$path, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorBeanColumnInfo.pathColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$lanpath = com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmGet$lanpath();
                if (realmGet$lanpath != null) {
                    Table.nativeSetString(nativePtr, visitorBeanColumnInfo.lanpathColKey, createRowWithPrimaryKey, realmGet$lanpath, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorBeanColumnInfo.lanpathColKey, createRowWithPrimaryKey, false);
                }
                j3 = j2;
            }
        }
    }

    public static VisitorBean createDetachedCopy(VisitorBean visitorBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        VisitorBean visitorBean2;
        if (i > i2 || visitorBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(visitorBean);
        if (cacheData == null) {
            visitorBean2 = new VisitorBean();
            map.put(visitorBean, new RealmObjectProxy.CacheData(i, visitorBean2));
        } else if (i >= cacheData.minDepth) {
            return (VisitorBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            visitorBean2 = (VisitorBean) cacheData.object;
        }
        com_ciot_realm_db_common_VisitorBeanRealmProxyInterface com_ciot_realm_db_common_visitorbeanrealmproxyinterface = visitorBean2;
        com_ciot_realm_db_common_VisitorBeanRealmProxyInterface com_ciot_realm_db_common_visitorbeanrealmproxyinterface2 = visitorBean;
        com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$name(com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$name());
        com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$company(com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$company());
        com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$idcard(com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$idcard());
        com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$phone(com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$phone());
        com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$face(com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$face());
        com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$type(com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$type());
        com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$path(com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$path());
        com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$lanpath(com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$lanpath());
        com_ciot_realm_db_common_visitorbeanrealmproxyinterface.realmSet$id(com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$id());
        return visitorBean2;
    }

    static VisitorBean update(Realm realm, VisitorBeanColumnInfo visitorBeanColumnInfo, VisitorBean visitorBean, VisitorBean visitorBean2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_common_VisitorBeanRealmProxyInterface com_ciot_realm_db_common_visitorbeanrealmproxyinterface = visitorBean;
        com_ciot_realm_db_common_VisitorBeanRealmProxyInterface com_ciot_realm_db_common_visitorbeanrealmproxyinterface2 = visitorBean2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(VisitorBean.class), set);
        osObjectBuilder.addString(visitorBeanColumnInfo.nameColKey, com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$name());
        osObjectBuilder.addString(visitorBeanColumnInfo.companyColKey, com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$company());
        osObjectBuilder.addString(visitorBeanColumnInfo.idcardColKey, com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$idcard());
        osObjectBuilder.addString(visitorBeanColumnInfo.phoneColKey, com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$phone());
        osObjectBuilder.addString(visitorBeanColumnInfo.faceColKey, com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$face());
        osObjectBuilder.addInteger(visitorBeanColumnInfo.typeColKey, Integer.valueOf(com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$type()));
        osObjectBuilder.addString(visitorBeanColumnInfo.pathColKey, com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$path());
        osObjectBuilder.addString(visitorBeanColumnInfo.lanpathColKey, com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$lanpath());
        osObjectBuilder.addString(visitorBeanColumnInfo.idColKey, com_ciot_realm_db_common_visitorbeanrealmproxyinterface2.realmGet$id());
        osObjectBuilder.updateExistingObject();
        return visitorBean;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("VisitorBean = proxy[");
        sb.append("{name:");
        String realmGet$name = realmGet$name();
        String str = Configurator.NULL;
        sb.append(realmGet$name != null ? realmGet$name() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{company:");
        sb.append(realmGet$company() != null ? realmGet$company() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{idcard:");
        sb.append(realmGet$idcard() != null ? realmGet$idcard() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{phone:");
        sb.append(realmGet$phone() != null ? realmGet$phone() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{face:");
        sb.append(realmGet$face() != null ? realmGet$face() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{type:");
        sb.append(realmGet$type());
        sb.append("}");
        sb.append(",");
        sb.append("{path:");
        sb.append(realmGet$path() != null ? realmGet$path() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{lanpath:");
        sb.append(realmGet$lanpath() != null ? realmGet$lanpath() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{id:");
        if (realmGet$id() != null) {
            str = realmGet$id();
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
        com_ciot_realm_db_common_VisitorBeanRealmProxy com_ciot_realm_db_common_visitorbeanrealmproxy = (com_ciot_realm_db_common_VisitorBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_common_visitorbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_common_visitorbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_common_visitorbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
