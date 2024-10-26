package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.PersonInfoBean;
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
import io.realm.internal.android.JsonUtils;
import io.realm.internal.objectstore.OsObjectBuilder;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class com_ciot_realm_db_PersonInfoBeanRealmProxy extends PersonInfoBean implements RealmObjectProxy, com_ciot_realm_db_PersonInfoBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private PersonInfoBeanColumnInfo columnInfo;
    private ProxyState<PersonInfoBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "PersonInfoBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class PersonInfoBeanColumnInfo extends ColumnInfo {
        long arcBytesColKey;
        long companyNameColKey;
        long companyNameStringColKey;
        long faceIdColKey;
        long greetingsColKey;
        long iccardColKey;
        long idColKey;
        long idcardColKey;
        long isVipColKey;
        long nameColKey;
        long nickNameColKey;
        long sexColKey;
        long staffnoColKey;
        long typeColKey;
        long uniqueIdColKey;

        PersonInfoBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(15);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.uniqueIdColKey = addColumnDetails("uniqueId", "uniqueId", objectSchemaInfo);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
            this.nickNameColKey = addColumnDetails("nickName", "nickName", objectSchemaInfo);
            this.arcBytesColKey = addColumnDetails("arcBytes", "arcBytes", objectSchemaInfo);
            this.greetingsColKey = addColumnDetails("greetings", "greetings", objectSchemaInfo);
            this.typeColKey = addColumnDetails("type", "type", objectSchemaInfo);
            this.isVipColKey = addColumnDetails("isVip", "isVip", objectSchemaInfo);
            this.sexColKey = addColumnDetails("sex", "sex", objectSchemaInfo);
            this.idcardColKey = addColumnDetails("idcard", "idcard", objectSchemaInfo);
            this.faceIdColKey = addColumnDetails("faceId", "faceId", objectSchemaInfo);
            this.iccardColKey = addColumnDetails("iccard", "iccard", objectSchemaInfo);
            this.staffnoColKey = addColumnDetails("staffno", "staffno", objectSchemaInfo);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.companyNameColKey = addColumnDetails("companyName", "companyName", objectSchemaInfo);
            this.companyNameStringColKey = addColumnDetails("companyNameString", "companyNameString", objectSchemaInfo);
        }

        PersonInfoBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new PersonInfoBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            PersonInfoBeanColumnInfo personInfoBeanColumnInfo = (PersonInfoBeanColumnInfo) columnInfo;
            PersonInfoBeanColumnInfo personInfoBeanColumnInfo2 = (PersonInfoBeanColumnInfo) columnInfo2;
            personInfoBeanColumnInfo2.uniqueIdColKey = personInfoBeanColumnInfo.uniqueIdColKey;
            personInfoBeanColumnInfo2.nameColKey = personInfoBeanColumnInfo.nameColKey;
            personInfoBeanColumnInfo2.nickNameColKey = personInfoBeanColumnInfo.nickNameColKey;
            personInfoBeanColumnInfo2.arcBytesColKey = personInfoBeanColumnInfo.arcBytesColKey;
            personInfoBeanColumnInfo2.greetingsColKey = personInfoBeanColumnInfo.greetingsColKey;
            personInfoBeanColumnInfo2.typeColKey = personInfoBeanColumnInfo.typeColKey;
            personInfoBeanColumnInfo2.isVipColKey = personInfoBeanColumnInfo.isVipColKey;
            personInfoBeanColumnInfo2.sexColKey = personInfoBeanColumnInfo.sexColKey;
            personInfoBeanColumnInfo2.idcardColKey = personInfoBeanColumnInfo.idcardColKey;
            personInfoBeanColumnInfo2.faceIdColKey = personInfoBeanColumnInfo.faceIdColKey;
            personInfoBeanColumnInfo2.iccardColKey = personInfoBeanColumnInfo.iccardColKey;
            personInfoBeanColumnInfo2.staffnoColKey = personInfoBeanColumnInfo.staffnoColKey;
            personInfoBeanColumnInfo2.idColKey = personInfoBeanColumnInfo.idColKey;
            personInfoBeanColumnInfo2.companyNameColKey = personInfoBeanColumnInfo.companyNameColKey;
            personInfoBeanColumnInfo2.companyNameStringColKey = personInfoBeanColumnInfo.companyNameStringColKey;
        }
    }

    com_ciot_realm_db_PersonInfoBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (PersonInfoBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<PersonInfoBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$uniqueId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.uniqueIdColKey);
    }

    public void realmSet$uniqueId(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'uniqueId' cannot be changed after object was created.");
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

    public String realmGet$nickName() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.nickNameColKey);
    }

    public void realmSet$nickName(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.nickNameColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.nickNameColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.nickNameColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.nickNameColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public byte[] realmGet$arcBytes() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBinaryByteArray(this.columnInfo.arcBytesColKey);
    }

    public void realmSet$arcBytes(byte[] bArr) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (bArr == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.arcBytesColKey);
            } else {
                this.proxyState.getRow$realm().setBinaryByteArray(this.columnInfo.arcBytesColKey, bArr);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (bArr == null) {
                row$realm.getTable().setNull(this.columnInfo.arcBytesColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setBinaryByteArray(this.columnInfo.arcBytesColKey, row$realm.getObjectKey(), bArr, true);
            }
        }
    }

    public String realmGet$greetings() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.greetingsColKey);
    }

    public void realmSet$greetings(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.greetingsColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.greetingsColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.greetingsColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.greetingsColKey, row$realm.getObjectKey(), str, true);
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

    public boolean realmGet$isVip() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isVipColKey);
    }

    public void realmSet$isVip(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isVipColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.isVipColKey, row$realm.getObjectKey(), z, true);
        }
    }

    public String realmGet$sex() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.sexColKey);
    }

    public void realmSet$sex(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.sexColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.sexColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.sexColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.sexColKey, row$realm.getObjectKey(), str, true);
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

    public int realmGet$faceId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.faceIdColKey);
    }

    public void realmSet$faceId(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.faceIdColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.faceIdColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public String realmGet$iccard() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.iccardColKey);
    }

    public void realmSet$iccard(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.iccardColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.iccardColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.iccardColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.iccardColKey, row$realm.getObjectKey(), str, true);
            }
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

    public String realmGet$id() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.idColKey);
    }

    public void realmSet$id(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.idColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.idColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.idColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.idColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$companyName() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.companyNameColKey);
    }

    public void realmSet$companyName(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.companyNameColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.companyNameColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.companyNameColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.companyNameColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$companyNameString() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.companyNameStringColKey);
    }

    public void realmSet$companyNameString(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.companyNameStringColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.companyNameStringColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.companyNameStringColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.companyNameStringColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 15, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("uniqueId", RealmFieldType.STRING, true, false, false);
        builder2.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("nickName", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("arcBytes", RealmFieldType.BINARY, false, false, false);
        builder2.addPersistedProperty("greetings", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("type", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("isVip", RealmFieldType.BOOLEAN, false, false, true);
        builder2.addPersistedProperty("sex", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("idcard", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("faceId", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("iccard", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("staffno", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("id", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("companyName", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("companyNameString", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static PersonInfoBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new PersonInfoBeanColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v7, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v8, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x01f7  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0193  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01ac  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01c5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.PersonInfoBean createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            java.lang.String r1 = "uniqueId"
            r2 = 0
            if (r14 == 0) goto L_0x0064
            java.lang.Class<com.ciot.realm.db.PersonInfoBean> r14 = com.ciot.realm.db.PersonInfoBean.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.PersonInfoBean> r4 = com.ciot.realm.db.PersonInfoBean.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxy$PersonInfoBeanColumnInfo r3 = (io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxy.PersonInfoBeanColumnInfo) r3
            long r3 = r3.uniqueIdColKey
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
            java.lang.Class<com.ciot.realm.db.PersonInfoBean> r3 = com.ciot.realm.db.PersonInfoBean.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005f }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005f }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005f }
            io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxy r14 = new io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxy     // Catch:{ all -> 0x005f }
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
            java.lang.Class<com.ciot.realm.db.PersonInfoBean> r14 = com.ciot.realm.db.PersonInfoBean.class
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxy r14 = (io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxy) r14
            goto L_0x0094
        L_0x007e:
            java.lang.Class<com.ciot.realm.db.PersonInfoBean> r14 = com.ciot.realm.db.PersonInfoBean.class
            java.lang.String r1 = r13.getString(r1)
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxy r14 = (io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxy) r14
            goto L_0x0094
        L_0x008c:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'uniqueId'."
            r12.<init>(r13)
            throw r12
        L_0x0094:
            r12 = r14
            io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxyInterface) r12
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
            java.lang.String r0 = "nickName"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00c9
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00c2
            r12.realmSet$nickName(r2)
            goto L_0x00c9
        L_0x00c2:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$nickName(r0)
        L_0x00c9:
            java.lang.String r0 = "arcBytes"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00e6
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00db
            r12.realmSet$arcBytes(r2)
            goto L_0x00e6
        L_0x00db:
            java.lang.String r0 = r13.getString(r0)
            byte[] r0 = io.realm.internal.android.JsonUtils.stringToBytes(r0)
            r12.realmSet$arcBytes(r0)
        L_0x00e6:
            java.lang.String r0 = "greetings"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00ff
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00f8
            r12.realmSet$greetings(r2)
            goto L_0x00ff
        L_0x00f8:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$greetings(r0)
        L_0x00ff:
            java.lang.String r0 = "type"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x011d
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0115
            int r0 = r13.getInt(r0)
            r12.realmSet$type(r0)
            goto L_0x011d
        L_0x0115:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'type' to null."
            r12.<init>(r13)
            throw r12
        L_0x011d:
            java.lang.String r0 = "isVip"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x013b
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0133
            boolean r0 = r13.getBoolean(r0)
            r12.realmSet$isVip(r0)
            goto L_0x013b
        L_0x0133:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'isVip' to null."
            r12.<init>(r13)
            throw r12
        L_0x013b:
            java.lang.String r0 = "sex"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0154
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x014d
            r12.realmSet$sex(r2)
            goto L_0x0154
        L_0x014d:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$sex(r0)
        L_0x0154:
            java.lang.String r0 = "idcard"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x016d
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0166
            r12.realmSet$idcard(r2)
            goto L_0x016d
        L_0x0166:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$idcard(r0)
        L_0x016d:
            java.lang.String r0 = "faceId"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x018b
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0183
            int r0 = r13.getInt(r0)
            r12.realmSet$faceId(r0)
            goto L_0x018b
        L_0x0183:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'faceId' to null."
            r12.<init>(r13)
            throw r12
        L_0x018b:
            java.lang.String r0 = "iccard"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01a4
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x019d
            r12.realmSet$iccard(r2)
            goto L_0x01a4
        L_0x019d:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$iccard(r0)
        L_0x01a4:
            java.lang.String r0 = "staffno"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01bd
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01b6
            r12.realmSet$staffno(r2)
            goto L_0x01bd
        L_0x01b6:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$staffno(r0)
        L_0x01bd:
            java.lang.String r0 = "id"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01d6
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01cf
            r12.realmSet$id(r2)
            goto L_0x01d6
        L_0x01cf:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$id(r0)
        L_0x01d6:
            java.lang.String r0 = "companyName"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01ef
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01e8
            r12.realmSet$companyName(r2)
            goto L_0x01ef
        L_0x01e8:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$companyName(r0)
        L_0x01ef:
            java.lang.String r0 = "companyNameString"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0208
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0201
            r12.realmSet$companyNameString(r2)
            goto L_0x0208
        L_0x0201:
            java.lang.String r13 = r13.getString(r0)
            r12.realmSet$companyNameString(r13)
        L_0x0208:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.PersonInfoBean");
    }

    public static PersonInfoBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        PersonInfoBean personInfoBean = new PersonInfoBean();
        com_ciot_realm_db_PersonInfoBeanRealmProxyInterface com_ciot_realm_db_personinfobeanrealmproxyinterface = personInfoBean;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("uniqueId")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$uniqueId(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$uniqueId((String) null);
                }
                z = true;
            } else if (nextName.equals(ServiceProvider.NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$name(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$name((String) null);
                }
            } else if (nextName.equals("nickName")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$nickName(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$nickName((String) null);
                }
            } else if (nextName.equals("arcBytes")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$arcBytes(JsonUtils.stringToBytes(jsonReader.nextString()));
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$arcBytes((byte[]) null);
                }
            } else if (nextName.equals("greetings")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$greetings(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$greetings((String) null);
                }
            } else if (nextName.equals("type")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$type(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'type' to null.");
                }
            } else if (nextName.equals("isVip")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$isVip(jsonReader.nextBoolean());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isVip' to null.");
                }
            } else if (nextName.equals("sex")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$sex(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$sex((String) null);
                }
            } else if (nextName.equals("idcard")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$idcard(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$idcard((String) null);
                }
            } else if (nextName.equals("faceId")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$faceId(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'faceId' to null.");
                }
            } else if (nextName.equals("iccard")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$iccard(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$iccard((String) null);
                }
            } else if (nextName.equals("staffno")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$staffno(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$staffno((String) null);
                }
            } else if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$id((String) null);
                }
            } else if (nextName.equals("companyName")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$companyName(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$companyName((String) null);
                }
            } else if (!nextName.equals("companyNameString")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$companyNameString(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$companyNameString((String) null);
            }
        }
        jsonReader.endObject();
        if (z) {
            return (PersonInfoBean) realm.copyToRealm(personInfoBean, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'uniqueId'.");
    }

    private static com_ciot_realm_db_PersonInfoBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) PersonInfoBean.class), false, Collections.emptyList());
        com_ciot_realm_db_PersonInfoBeanRealmProxy com_ciot_realm_db_personinfobeanrealmproxy = new com_ciot_realm_db_PersonInfoBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_personinfobeanrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.PersonInfoBean copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxy.PersonInfoBeanColumnInfo r9, com.ciot.realm.db.PersonInfoBean r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.PersonInfoBean r1 = (com.ciot.realm.db.PersonInfoBean) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.PersonInfoBean> r2 = com.ciot.realm.db.PersonInfoBean.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.uniqueIdColKey
            r5 = r10
            io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxyInterface) r5
            java.lang.String r5 = r5.realmGet$uniqueId()
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
            io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxy r1 = new io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.PersonInfoBean r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.PersonInfoBean r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxy$PersonInfoBeanColumnInfo, com.ciot.realm.db.PersonInfoBean, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.PersonInfoBean");
    }

    public static PersonInfoBean copy(Realm realm, PersonInfoBeanColumnInfo personInfoBeanColumnInfo, PersonInfoBean personInfoBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(personInfoBean);
        if (realmObjectProxy != null) {
            return (PersonInfoBean) realmObjectProxy;
        }
        com_ciot_realm_db_PersonInfoBeanRealmProxyInterface com_ciot_realm_db_personinfobeanrealmproxyinterface = personInfoBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(PersonInfoBean.class), set);
        osObjectBuilder.addString(personInfoBeanColumnInfo.uniqueIdColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$uniqueId());
        osObjectBuilder.addString(personInfoBeanColumnInfo.nameColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$name());
        osObjectBuilder.addString(personInfoBeanColumnInfo.nickNameColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$nickName());
        osObjectBuilder.addByteArray(personInfoBeanColumnInfo.arcBytesColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$arcBytes());
        osObjectBuilder.addString(personInfoBeanColumnInfo.greetingsColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$greetings());
        osObjectBuilder.addInteger(personInfoBeanColumnInfo.typeColKey, Integer.valueOf(com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$type()));
        osObjectBuilder.addBoolean(personInfoBeanColumnInfo.isVipColKey, Boolean.valueOf(com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$isVip()));
        osObjectBuilder.addString(personInfoBeanColumnInfo.sexColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$sex());
        osObjectBuilder.addString(personInfoBeanColumnInfo.idcardColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$idcard());
        osObjectBuilder.addInteger(personInfoBeanColumnInfo.faceIdColKey, Integer.valueOf(com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$faceId()));
        osObjectBuilder.addString(personInfoBeanColumnInfo.iccardColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$iccard());
        osObjectBuilder.addString(personInfoBeanColumnInfo.staffnoColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$staffno());
        osObjectBuilder.addString(personInfoBeanColumnInfo.idColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$id());
        osObjectBuilder.addString(personInfoBeanColumnInfo.companyNameColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$companyName());
        osObjectBuilder.addString(personInfoBeanColumnInfo.companyNameStringColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$companyNameString());
        com_ciot_realm_db_PersonInfoBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(personInfoBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, PersonInfoBean personInfoBean, Map<RealmModel, Long> map) {
        long j;
        PersonInfoBean personInfoBean2 = personInfoBean;
        if ((personInfoBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(personInfoBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) personInfoBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(PersonInfoBean.class);
        long nativePtr = table.getNativePtr();
        PersonInfoBeanColumnInfo personInfoBeanColumnInfo = (PersonInfoBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PersonInfoBean.class);
        long j2 = personInfoBeanColumnInfo.uniqueIdColKey;
        com_ciot_realm_db_PersonInfoBeanRealmProxyInterface com_ciot_realm_db_personinfobeanrealmproxyinterface = personInfoBean2;
        String realmGet$uniqueId = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$uniqueId();
        if (realmGet$uniqueId == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$uniqueId);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$uniqueId);
        } else {
            Table.throwDuplicatePrimaryKeyException(realmGet$uniqueId);
        }
        long j3 = j;
        map.put(personInfoBean2, Long.valueOf(j3));
        String realmGet$name = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.nameColKey, j3, realmGet$name, false);
        }
        String realmGet$nickName = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$nickName();
        if (realmGet$nickName != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.nickNameColKey, j3, realmGet$nickName, false);
        }
        byte[] realmGet$arcBytes = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$arcBytes();
        if (realmGet$arcBytes != null) {
            Table.nativeSetByteArray(nativePtr, personInfoBeanColumnInfo.arcBytesColKey, j3, realmGet$arcBytes, false);
        }
        String realmGet$greetings = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$greetings();
        if (realmGet$greetings != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.greetingsColKey, j3, realmGet$greetings, false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetLong(j4, personInfoBeanColumnInfo.typeColKey, j5, (long) com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$type(), false);
        Table.nativeSetBoolean(j4, personInfoBeanColumnInfo.isVipColKey, j5, com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$isVip(), false);
        String realmGet$sex = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$sex();
        if (realmGet$sex != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.sexColKey, j3, realmGet$sex, false);
        }
        String realmGet$idcard = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$idcard();
        if (realmGet$idcard != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.idcardColKey, j3, realmGet$idcard, false);
        }
        Table.nativeSetLong(nativePtr, personInfoBeanColumnInfo.faceIdColKey, j3, (long) com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$faceId(), false);
        String realmGet$iccard = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$iccard();
        if (realmGet$iccard != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.iccardColKey, j3, realmGet$iccard, false);
        }
        String realmGet$staffno = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$staffno();
        if (realmGet$staffno != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.staffnoColKey, j3, realmGet$staffno, false);
        }
        String realmGet$id = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.idColKey, j3, realmGet$id, false);
        }
        String realmGet$companyName = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$companyName();
        if (realmGet$companyName != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.companyNameColKey, j3, realmGet$companyName, false);
        }
        String realmGet$companyNameString = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$companyNameString();
        if (realmGet$companyNameString != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.companyNameStringColKey, j3, realmGet$companyNameString, false);
        }
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(PersonInfoBean.class);
        long nativePtr = table.getNativePtr();
        PersonInfoBeanColumnInfo personInfoBeanColumnInfo = (PersonInfoBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PersonInfoBean.class);
        long j4 = personInfoBeanColumnInfo.uniqueIdColKey;
        while (it.hasNext()) {
            PersonInfoBean personInfoBean = (PersonInfoBean) it.next();
            if (!map2.containsKey(personInfoBean)) {
                if ((personInfoBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(personInfoBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) personInfoBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(personInfoBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_PersonInfoBeanRealmProxyInterface com_ciot_realm_db_personinfobeanrealmproxyinterface = personInfoBean;
                String realmGet$uniqueId = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$uniqueId();
                if (realmGet$uniqueId == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j4);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j4, realmGet$uniqueId);
                }
                if (j == -1) {
                    j2 = OsObject.createRowWithPrimaryKey(table, j4, realmGet$uniqueId);
                } else {
                    Table.throwDuplicatePrimaryKeyException(realmGet$uniqueId);
                    j2 = j;
                }
                map2.put(personInfoBean, Long.valueOf(j2));
                String realmGet$name = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    j3 = j4;
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.nameColKey, j2, realmGet$name, false);
                } else {
                    j3 = j4;
                }
                String realmGet$nickName = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$nickName();
                if (realmGet$nickName != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.nickNameColKey, j2, realmGet$nickName, false);
                }
                byte[] realmGet$arcBytes = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$arcBytes();
                if (realmGet$arcBytes != null) {
                    Table.nativeSetByteArray(nativePtr, personInfoBeanColumnInfo.arcBytesColKey, j2, realmGet$arcBytes, false);
                }
                String realmGet$greetings = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$greetings();
                if (realmGet$greetings != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.greetingsColKey, j2, realmGet$greetings, false);
                }
                long j5 = nativePtr;
                long j6 = j2;
                Table.nativeSetLong(j5, personInfoBeanColumnInfo.typeColKey, j6, (long) com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$type(), false);
                Table.nativeSetBoolean(j5, personInfoBeanColumnInfo.isVipColKey, j6, com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$isVip(), false);
                String realmGet$sex = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$sex();
                if (realmGet$sex != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.sexColKey, j2, realmGet$sex, false);
                }
                String realmGet$idcard = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$idcard();
                if (realmGet$idcard != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.idcardColKey, j2, realmGet$idcard, false);
                }
                Table.nativeSetLong(nativePtr, personInfoBeanColumnInfo.faceIdColKey, j2, (long) com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$faceId(), false);
                String realmGet$iccard = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$iccard();
                if (realmGet$iccard != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.iccardColKey, j2, realmGet$iccard, false);
                }
                String realmGet$staffno = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$staffno();
                if (realmGet$staffno != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.staffnoColKey, j2, realmGet$staffno, false);
                }
                String realmGet$id = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.idColKey, j2, realmGet$id, false);
                }
                String realmGet$companyName = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$companyName();
                if (realmGet$companyName != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.companyNameColKey, j2, realmGet$companyName, false);
                }
                String realmGet$companyNameString = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$companyNameString();
                if (realmGet$companyNameString != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.companyNameStringColKey, j2, realmGet$companyNameString, false);
                }
                j4 = j3;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, PersonInfoBean personInfoBean, Map<RealmModel, Long> map) {
        long j;
        PersonInfoBean personInfoBean2 = personInfoBean;
        if ((personInfoBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(personInfoBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) personInfoBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(PersonInfoBean.class);
        long nativePtr = table.getNativePtr();
        PersonInfoBeanColumnInfo personInfoBeanColumnInfo = (PersonInfoBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PersonInfoBean.class);
        long j2 = personInfoBeanColumnInfo.uniqueIdColKey;
        com_ciot_realm_db_PersonInfoBeanRealmProxyInterface com_ciot_realm_db_personinfobeanrealmproxyinterface = personInfoBean2;
        String realmGet$uniqueId = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$uniqueId();
        if (realmGet$uniqueId == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$uniqueId);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$uniqueId);
        }
        long j3 = j;
        map.put(personInfoBean2, Long.valueOf(j3));
        String realmGet$name = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.nameColKey, j3, realmGet$name, false);
        } else {
            Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.nameColKey, j3, false);
        }
        String realmGet$nickName = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$nickName();
        if (realmGet$nickName != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.nickNameColKey, j3, realmGet$nickName, false);
        } else {
            Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.nickNameColKey, j3, false);
        }
        byte[] realmGet$arcBytes = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$arcBytes();
        if (realmGet$arcBytes != null) {
            Table.nativeSetByteArray(nativePtr, personInfoBeanColumnInfo.arcBytesColKey, j3, realmGet$arcBytes, false);
        } else {
            Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.arcBytesColKey, j3, false);
        }
        String realmGet$greetings = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$greetings();
        if (realmGet$greetings != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.greetingsColKey, j3, realmGet$greetings, false);
        } else {
            Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.greetingsColKey, j3, false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetLong(j4, personInfoBeanColumnInfo.typeColKey, j5, (long) com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$type(), false);
        Table.nativeSetBoolean(j4, personInfoBeanColumnInfo.isVipColKey, j5, com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$isVip(), false);
        String realmGet$sex = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$sex();
        if (realmGet$sex != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.sexColKey, j3, realmGet$sex, false);
        } else {
            Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.sexColKey, j3, false);
        }
        String realmGet$idcard = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$idcard();
        if (realmGet$idcard != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.idcardColKey, j3, realmGet$idcard, false);
        } else {
            Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.idcardColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, personInfoBeanColumnInfo.faceIdColKey, j3, (long) com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$faceId(), false);
        String realmGet$iccard = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$iccard();
        if (realmGet$iccard != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.iccardColKey, j3, realmGet$iccard, false);
        } else {
            Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.iccardColKey, j3, false);
        }
        String realmGet$staffno = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$staffno();
        if (realmGet$staffno != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.staffnoColKey, j3, realmGet$staffno, false);
        } else {
            Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.staffnoColKey, j3, false);
        }
        String realmGet$id = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.idColKey, j3, realmGet$id, false);
        } else {
            Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.idColKey, j3, false);
        }
        String realmGet$companyName = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$companyName();
        if (realmGet$companyName != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.companyNameColKey, j3, realmGet$companyName, false);
        } else {
            Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.companyNameColKey, j3, false);
        }
        String realmGet$companyNameString = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$companyNameString();
        if (realmGet$companyNameString != null) {
            Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.companyNameStringColKey, j3, realmGet$companyNameString, false);
        } else {
            Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.companyNameStringColKey, j3, false);
        }
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(PersonInfoBean.class);
        long nativePtr = table.getNativePtr();
        PersonInfoBeanColumnInfo personInfoBeanColumnInfo = (PersonInfoBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PersonInfoBean.class);
        long j3 = personInfoBeanColumnInfo.uniqueIdColKey;
        while (it.hasNext()) {
            PersonInfoBean personInfoBean = (PersonInfoBean) it.next();
            if (!map2.containsKey(personInfoBean)) {
                if ((personInfoBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(personInfoBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) personInfoBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(personInfoBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_PersonInfoBeanRealmProxyInterface com_ciot_realm_db_personinfobeanrealmproxyinterface = personInfoBean;
                String realmGet$uniqueId = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$uniqueId();
                if (realmGet$uniqueId == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j3);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j3, realmGet$uniqueId);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j3, realmGet$uniqueId) : j;
                map2.put(personInfoBean, Long.valueOf(createRowWithPrimaryKey));
                String realmGet$name = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.nameColKey, createRowWithPrimaryKey, realmGet$name, false);
                } else {
                    j2 = j3;
                    Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.nameColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$nickName = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$nickName();
                if (realmGet$nickName != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.nickNameColKey, createRowWithPrimaryKey, realmGet$nickName, false);
                } else {
                    Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.nickNameColKey, createRowWithPrimaryKey, false);
                }
                byte[] realmGet$arcBytes = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$arcBytes();
                if (realmGet$arcBytes != null) {
                    Table.nativeSetByteArray(nativePtr, personInfoBeanColumnInfo.arcBytesColKey, createRowWithPrimaryKey, realmGet$arcBytes, false);
                } else {
                    Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.arcBytesColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$greetings = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$greetings();
                if (realmGet$greetings != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.greetingsColKey, createRowWithPrimaryKey, realmGet$greetings, false);
                } else {
                    Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.greetingsColKey, createRowWithPrimaryKey, false);
                }
                long j4 = nativePtr;
                long j5 = createRowWithPrimaryKey;
                Table.nativeSetLong(j4, personInfoBeanColumnInfo.typeColKey, j5, (long) com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$type(), false);
                Table.nativeSetBoolean(j4, personInfoBeanColumnInfo.isVipColKey, j5, com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$isVip(), false);
                String realmGet$sex = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$sex();
                if (realmGet$sex != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.sexColKey, createRowWithPrimaryKey, realmGet$sex, false);
                } else {
                    Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.sexColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$idcard = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$idcard();
                if (realmGet$idcard != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.idcardColKey, createRowWithPrimaryKey, realmGet$idcard, false);
                } else {
                    Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.idcardColKey, createRowWithPrimaryKey, false);
                }
                Table.nativeSetLong(nativePtr, personInfoBeanColumnInfo.faceIdColKey, createRowWithPrimaryKey, (long) com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$faceId(), false);
                String realmGet$iccard = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$iccard();
                if (realmGet$iccard != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.iccardColKey, createRowWithPrimaryKey, realmGet$iccard, false);
                } else {
                    Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.iccardColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$staffno = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$staffno();
                if (realmGet$staffno != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.staffnoColKey, createRowWithPrimaryKey, realmGet$staffno, false);
                } else {
                    Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.staffnoColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$id = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.idColKey, createRowWithPrimaryKey, realmGet$id, false);
                } else {
                    Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.idColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$companyName = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$companyName();
                if (realmGet$companyName != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.companyNameColKey, createRowWithPrimaryKey, realmGet$companyName, false);
                } else {
                    Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.companyNameColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$companyNameString = com_ciot_realm_db_personinfobeanrealmproxyinterface.realmGet$companyNameString();
                if (realmGet$companyNameString != null) {
                    Table.nativeSetString(nativePtr, personInfoBeanColumnInfo.companyNameStringColKey, createRowWithPrimaryKey, realmGet$companyNameString, false);
                } else {
                    Table.nativeSetNull(nativePtr, personInfoBeanColumnInfo.companyNameStringColKey, createRowWithPrimaryKey, false);
                }
                j3 = j2;
            }
        }
    }

    public static PersonInfoBean createDetachedCopy(PersonInfoBean personInfoBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        PersonInfoBean personInfoBean2;
        if (i > i2 || personInfoBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(personInfoBean);
        if (cacheData == null) {
            personInfoBean2 = new PersonInfoBean();
            map.put(personInfoBean, new RealmObjectProxy.CacheData(i, personInfoBean2));
        } else if (i >= cacheData.minDepth) {
            return (PersonInfoBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            personInfoBean2 = (PersonInfoBean) cacheData.object;
        }
        com_ciot_realm_db_PersonInfoBeanRealmProxyInterface com_ciot_realm_db_personinfobeanrealmproxyinterface = personInfoBean2;
        com_ciot_realm_db_PersonInfoBeanRealmProxyInterface com_ciot_realm_db_personinfobeanrealmproxyinterface2 = personInfoBean;
        com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$uniqueId(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$uniqueId());
        com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$name(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$name());
        com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$nickName(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$nickName());
        com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$arcBytes(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$arcBytes());
        com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$greetings(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$greetings());
        com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$type(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$type());
        com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$isVip(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$isVip());
        com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$sex(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$sex());
        com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$idcard(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$idcard());
        com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$faceId(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$faceId());
        com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$iccard(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$iccard());
        com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$staffno(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$staffno());
        com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$id(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$companyName(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$companyName());
        com_ciot_realm_db_personinfobeanrealmproxyinterface.realmSet$companyNameString(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$companyNameString());
        return personInfoBean2;
    }

    static PersonInfoBean update(Realm realm, PersonInfoBeanColumnInfo personInfoBeanColumnInfo, PersonInfoBean personInfoBean, PersonInfoBean personInfoBean2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_PersonInfoBeanRealmProxyInterface com_ciot_realm_db_personinfobeanrealmproxyinterface = personInfoBean;
        com_ciot_realm_db_PersonInfoBeanRealmProxyInterface com_ciot_realm_db_personinfobeanrealmproxyinterface2 = personInfoBean2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(PersonInfoBean.class), set);
        osObjectBuilder.addString(personInfoBeanColumnInfo.uniqueIdColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$uniqueId());
        osObjectBuilder.addString(personInfoBeanColumnInfo.nameColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$name());
        osObjectBuilder.addString(personInfoBeanColumnInfo.nickNameColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$nickName());
        osObjectBuilder.addByteArray(personInfoBeanColumnInfo.arcBytesColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$arcBytes());
        osObjectBuilder.addString(personInfoBeanColumnInfo.greetingsColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$greetings());
        osObjectBuilder.addInteger(personInfoBeanColumnInfo.typeColKey, Integer.valueOf(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$type()));
        osObjectBuilder.addBoolean(personInfoBeanColumnInfo.isVipColKey, Boolean.valueOf(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$isVip()));
        osObjectBuilder.addString(personInfoBeanColumnInfo.sexColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$sex());
        osObjectBuilder.addString(personInfoBeanColumnInfo.idcardColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$idcard());
        osObjectBuilder.addInteger(personInfoBeanColumnInfo.faceIdColKey, Integer.valueOf(com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$faceId()));
        osObjectBuilder.addString(personInfoBeanColumnInfo.iccardColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$iccard());
        osObjectBuilder.addString(personInfoBeanColumnInfo.staffnoColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$staffno());
        osObjectBuilder.addString(personInfoBeanColumnInfo.idColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$id());
        osObjectBuilder.addString(personInfoBeanColumnInfo.companyNameColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$companyName());
        osObjectBuilder.addString(personInfoBeanColumnInfo.companyNameStringColKey, com_ciot_realm_db_personinfobeanrealmproxyinterface2.realmGet$companyNameString());
        osObjectBuilder.updateExistingObject();
        return personInfoBean;
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
}
