package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import androidx.core.app.NotificationCompat;
import com.ciot.realm.db.EmployeeBean;
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

public class com_ciot_realm_db_EmployeeBeanRealmProxy extends EmployeeBean implements RealmObjectProxy, com_ciot_realm_db_EmployeeBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private EmployeeBeanColumnInfo columnInfo;
    private ProxyState<EmployeeBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "EmployeeBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class EmployeeBeanColumnInfo extends ColumnInfo {
        long arccodeColKey;
        long arccodebytesColKey;
        long birthdayColKey;
        long cardColKey;
        long companyColKey;
        long companyIdColKey;
        long companyNameColKey;
        long departmentColKey;
        long descriptionColKey;
        long emailColKey;
        long faceColKey;
        long fingerprintColKey;
        long greetingsColKey;
        long iccardColKey;
        long idColKey;
        long idcardColKey;
        long idcardcodeColKey;
        long jobColKey;
        long nameColKey;
        long phoneColKey;
        long senscodeColKey;
        long sexColKey;
        long staffnoColKey;
        long wechatColKey;

        EmployeeBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(24);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
            this.cardColKey = addColumnDetails("card", "card", objectSchemaInfo);
            this.idcardColKey = addColumnDetails("idcard", "idcard", objectSchemaInfo);
            this.faceColKey = addColumnDetails("face", "face", objectSchemaInfo);
            this.fingerprintColKey = addColumnDetails("fingerprint", "fingerprint", objectSchemaInfo);
            this.phoneColKey = addColumnDetails("phone", "phone", objectSchemaInfo);
            this.emailColKey = addColumnDetails(NotificationCompat.CATEGORY_EMAIL, NotificationCompat.CATEGORY_EMAIL, objectSchemaInfo);
            this.sexColKey = addColumnDetails("sex", "sex", objectSchemaInfo);
            this.departmentColKey = addColumnDetails("department", "department", objectSchemaInfo);
            this.companyColKey = addColumnDetails("company", "company", objectSchemaInfo);
            this.jobColKey = addColumnDetails("job", "job", objectSchemaInfo);
            this.arccodeColKey = addColumnDetails("arccode", "arccode", objectSchemaInfo);
            this.arccodebytesColKey = addColumnDetails("arccodebytes", "arccodebytes", objectSchemaInfo);
            this.staffnoColKey = addColumnDetails("staffno", "staffno", objectSchemaInfo);
            this.greetingsColKey = addColumnDetails("greetings", "greetings", objectSchemaInfo);
            this.senscodeColKey = addColumnDetails("senscode", "senscode", objectSchemaInfo);
            this.wechatColKey = addColumnDetails("wechat", "wechat", objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
            this.birthdayColKey = addColumnDetails("birthday", "birthday", objectSchemaInfo);
            this.companyIdColKey = addColumnDetails("companyId", "companyId", objectSchemaInfo);
            this.companyNameColKey = addColumnDetails("companyName", "companyName", objectSchemaInfo);
            this.idcardcodeColKey = addColumnDetails("idcardcode", "idcardcode", objectSchemaInfo);
            this.iccardColKey = addColumnDetails("iccard", "iccard", objectSchemaInfo);
        }

        EmployeeBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new EmployeeBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            EmployeeBeanColumnInfo employeeBeanColumnInfo = (EmployeeBeanColumnInfo) columnInfo;
            EmployeeBeanColumnInfo employeeBeanColumnInfo2 = (EmployeeBeanColumnInfo) columnInfo2;
            employeeBeanColumnInfo2.idColKey = employeeBeanColumnInfo.idColKey;
            employeeBeanColumnInfo2.nameColKey = employeeBeanColumnInfo.nameColKey;
            employeeBeanColumnInfo2.cardColKey = employeeBeanColumnInfo.cardColKey;
            employeeBeanColumnInfo2.idcardColKey = employeeBeanColumnInfo.idcardColKey;
            employeeBeanColumnInfo2.faceColKey = employeeBeanColumnInfo.faceColKey;
            employeeBeanColumnInfo2.fingerprintColKey = employeeBeanColumnInfo.fingerprintColKey;
            employeeBeanColumnInfo2.phoneColKey = employeeBeanColumnInfo.phoneColKey;
            employeeBeanColumnInfo2.emailColKey = employeeBeanColumnInfo.emailColKey;
            employeeBeanColumnInfo2.sexColKey = employeeBeanColumnInfo.sexColKey;
            employeeBeanColumnInfo2.departmentColKey = employeeBeanColumnInfo.departmentColKey;
            employeeBeanColumnInfo2.companyColKey = employeeBeanColumnInfo.companyColKey;
            employeeBeanColumnInfo2.jobColKey = employeeBeanColumnInfo.jobColKey;
            employeeBeanColumnInfo2.arccodeColKey = employeeBeanColumnInfo.arccodeColKey;
            employeeBeanColumnInfo2.arccodebytesColKey = employeeBeanColumnInfo.arccodebytesColKey;
            employeeBeanColumnInfo2.staffnoColKey = employeeBeanColumnInfo.staffnoColKey;
            employeeBeanColumnInfo2.greetingsColKey = employeeBeanColumnInfo.greetingsColKey;
            employeeBeanColumnInfo2.senscodeColKey = employeeBeanColumnInfo.senscodeColKey;
            employeeBeanColumnInfo2.wechatColKey = employeeBeanColumnInfo.wechatColKey;
            employeeBeanColumnInfo2.descriptionColKey = employeeBeanColumnInfo.descriptionColKey;
            employeeBeanColumnInfo2.birthdayColKey = employeeBeanColumnInfo.birthdayColKey;
            employeeBeanColumnInfo2.companyIdColKey = employeeBeanColumnInfo.companyIdColKey;
            employeeBeanColumnInfo2.companyNameColKey = employeeBeanColumnInfo.companyNameColKey;
            employeeBeanColumnInfo2.idcardcodeColKey = employeeBeanColumnInfo.idcardcodeColKey;
            employeeBeanColumnInfo2.iccardColKey = employeeBeanColumnInfo.iccardColKey;
        }
    }

    com_ciot_realm_db_EmployeeBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (EmployeeBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<EmployeeBean> proxyState2 = new ProxyState<>(this);
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

    public String realmGet$card() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.cardColKey);
    }

    public void realmSet$card(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.cardColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.cardColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.cardColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.cardColKey, row$realm.getObjectKey(), str, true);
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

    public String realmGet$fingerprint() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.fingerprintColKey);
    }

    public void realmSet$fingerprint(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.fingerprintColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.fingerprintColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.fingerprintColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.fingerprintColKey, row$realm.getObjectKey(), str, true);
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

    public String realmGet$email() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.emailColKey);
    }

    public void realmSet$email(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.emailColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.emailColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.emailColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.emailColKey, row$realm.getObjectKey(), str, true);
            }
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

    public String realmGet$department() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.departmentColKey);
    }

    public void realmSet$department(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.departmentColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.departmentColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.departmentColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.departmentColKey, row$realm.getObjectKey(), str, true);
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

    public String realmGet$job() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.jobColKey);
    }

    public void realmSet$job(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.jobColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.jobColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.jobColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.jobColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$arccode() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.arccodeColKey);
    }

    public void realmSet$arccode(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.arccodeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.arccodeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.arccodeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.arccodeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public byte[] realmGet$arccodebytes() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBinaryByteArray(this.columnInfo.arccodebytesColKey);
    }

    public void realmSet$arccodebytes(byte[] bArr) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (bArr == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.arccodebytesColKey);
            } else {
                this.proxyState.getRow$realm().setBinaryByteArray(this.columnInfo.arccodebytesColKey, bArr);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (bArr == null) {
                row$realm.getTable().setNull(this.columnInfo.arccodebytesColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setBinaryByteArray(this.columnInfo.arccodebytesColKey, row$realm.getObjectKey(), bArr, true);
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

    public String realmGet$senscode() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.senscodeColKey);
    }

    public void realmSet$senscode(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.senscodeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.senscodeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.senscodeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.senscodeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$wechat() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.wechatColKey);
    }

    public void realmSet$wechat(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.wechatColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.wechatColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.wechatColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.wechatColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$description() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.descriptionColKey);
    }

    public void realmSet$description(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.descriptionColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.descriptionColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.descriptionColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.descriptionColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$birthday() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.birthdayColKey);
    }

    public void realmSet$birthday(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.birthdayColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.birthdayColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.birthdayColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.birthdayColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$companyId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.companyIdColKey);
    }

    public void realmSet$companyId(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.companyIdColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.companyIdColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.companyIdColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.companyIdColKey, row$realm.getObjectKey(), str, true);
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

    public String realmGet$idcardcode() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.idcardcodeColKey);
    }

    public void realmSet$idcardcode(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.idcardcodeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.idcardcodeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.idcardcodeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.idcardcodeColKey, row$realm.getObjectKey(), str, true);
            }
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 24, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("id", RealmFieldType.STRING, true, false, false);
        builder2.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("card", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("idcard", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("face", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("fingerprint", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("phone", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(NotificationCompat.CATEGORY_EMAIL, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("sex", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("department", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("company", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("job", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("arccode", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("arccodebytes", RealmFieldType.BINARY, false, false, false);
        builder2.addPersistedProperty("staffno", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("greetings", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("senscode", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("wechat", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("birthday", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("companyId", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("companyName", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("idcardcode", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("iccard", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static EmployeeBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new EmployeeBeanColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v4, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v5, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0202  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x021b  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0234  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x024d  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x0266  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x027f  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0298  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x02b5  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x02d2  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x014f  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0181  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01cc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.EmployeeBean createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            java.lang.String r1 = "id"
            r2 = 0
            if (r14 == 0) goto L_0x0064
            java.lang.Class<com.ciot.realm.db.EmployeeBean> r14 = com.ciot.realm.db.EmployeeBean.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.EmployeeBean> r4 = com.ciot.realm.db.EmployeeBean.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy$EmployeeBeanColumnInfo r3 = (io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy.EmployeeBeanColumnInfo) r3
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
            java.lang.Class<com.ciot.realm.db.EmployeeBean> r3 = com.ciot.realm.db.EmployeeBean.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005f }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005f }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005f }
            io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy r14 = new io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy     // Catch:{ all -> 0x005f }
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
            if (r14 != 0) goto L_0x0095
            boolean r14 = r13.has(r1)
            if (r14 == 0) goto L_0x008d
            boolean r14 = r13.isNull(r1)
            if (r14 == 0) goto L_0x007e
            java.lang.Class<com.ciot.realm.db.EmployeeBean> r14 = com.ciot.realm.db.EmployeeBean.class
            r1 = 1
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r1, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy r14 = (io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy) r14
            goto L_0x0095
        L_0x007e:
            java.lang.Class<com.ciot.realm.db.EmployeeBean> r14 = com.ciot.realm.db.EmployeeBean.class
            java.lang.String r1 = r13.getString(r1)
            r3 = 1
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy r14 = (io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy) r14
            goto L_0x0095
        L_0x008d:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'id'."
            r12.<init>(r13)
            throw r12
        L_0x0095:
            r12 = r14
            io.realm.com_ciot_realm_db_EmployeeBeanRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_EmployeeBeanRealmProxyInterface) r12
            java.lang.String r0 = "name"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00b1
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00aa
            r12.realmSet$name(r2)
            goto L_0x00b1
        L_0x00aa:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$name(r0)
        L_0x00b1:
            java.lang.String r0 = "card"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00ca
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00c3
            r12.realmSet$card(r2)
            goto L_0x00ca
        L_0x00c3:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$card(r0)
        L_0x00ca:
            java.lang.String r0 = "idcard"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00e3
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00dc
            r12.realmSet$idcard(r2)
            goto L_0x00e3
        L_0x00dc:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$idcard(r0)
        L_0x00e3:
            java.lang.String r0 = "face"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00fc
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00f5
            r12.realmSet$face(r2)
            goto L_0x00fc
        L_0x00f5:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$face(r0)
        L_0x00fc:
            java.lang.String r0 = "fingerprint"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0115
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x010e
            r12.realmSet$fingerprint(r2)
            goto L_0x0115
        L_0x010e:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$fingerprint(r0)
        L_0x0115:
            java.lang.String r0 = "phone"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x012e
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0127
            r12.realmSet$phone(r2)
            goto L_0x012e
        L_0x0127:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$phone(r0)
        L_0x012e:
            java.lang.String r0 = "email"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0147
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0140
            r12.realmSet$email(r2)
            goto L_0x0147
        L_0x0140:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$email(r0)
        L_0x0147:
            java.lang.String r0 = "sex"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0160
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0159
            r12.realmSet$sex(r2)
            goto L_0x0160
        L_0x0159:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$sex(r0)
        L_0x0160:
            java.lang.String r0 = "department"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0179
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0172
            r12.realmSet$department(r2)
            goto L_0x0179
        L_0x0172:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$department(r0)
        L_0x0179:
            java.lang.String r0 = "company"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0192
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x018b
            r12.realmSet$company(r2)
            goto L_0x0192
        L_0x018b:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$company(r0)
        L_0x0192:
            java.lang.String r0 = "job"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01ab
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01a4
            r12.realmSet$job(r2)
            goto L_0x01ab
        L_0x01a4:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$job(r0)
        L_0x01ab:
            java.lang.String r0 = "arccode"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01c4
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01bd
            r12.realmSet$arccode(r2)
            goto L_0x01c4
        L_0x01bd:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$arccode(r0)
        L_0x01c4:
            java.lang.String r0 = "arccodebytes"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01e1
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01d6
            r12.realmSet$arccodebytes(r2)
            goto L_0x01e1
        L_0x01d6:
            java.lang.String r0 = r13.getString(r0)
            byte[] r0 = io.realm.internal.android.JsonUtils.stringToBytes(r0)
            r12.realmSet$arccodebytes(r0)
        L_0x01e1:
            java.lang.String r0 = "staffno"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01fa
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01f3
            r12.realmSet$staffno(r2)
            goto L_0x01fa
        L_0x01f3:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$staffno(r0)
        L_0x01fa:
            java.lang.String r0 = "greetings"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0213
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x020c
            r12.realmSet$greetings(r2)
            goto L_0x0213
        L_0x020c:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$greetings(r0)
        L_0x0213:
            java.lang.String r0 = "senscode"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x022c
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0225
            r12.realmSet$senscode(r2)
            goto L_0x022c
        L_0x0225:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$senscode(r0)
        L_0x022c:
            java.lang.String r0 = "wechat"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0245
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x023e
            r12.realmSet$wechat(r2)
            goto L_0x0245
        L_0x023e:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$wechat(r0)
        L_0x0245:
            java.lang.String r0 = "description"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x025e
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0257
            r12.realmSet$description(r2)
            goto L_0x025e
        L_0x0257:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$description(r0)
        L_0x025e:
            java.lang.String r0 = "birthday"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0277
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0270
            r12.realmSet$birthday(r2)
            goto L_0x0277
        L_0x0270:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$birthday(r0)
        L_0x0277:
            java.lang.String r0 = "companyId"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0290
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0289
            r12.realmSet$companyId(r2)
            goto L_0x0290
        L_0x0289:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$companyId(r0)
        L_0x0290:
            java.lang.String r0 = "companyName"
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L_0x02ad
            java.lang.String r0 = "companyName"
            boolean r0 = r13.isNull(r0)
            if (r0 == 0) goto L_0x02a4
            r12.realmSet$companyName(r2)
            goto L_0x02ad
        L_0x02a4:
            java.lang.String r0 = "companyName"
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$companyName(r0)
        L_0x02ad:
            java.lang.String r0 = "idcardcode"
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L_0x02ca
            java.lang.String r0 = "idcardcode"
            boolean r0 = r13.isNull(r0)
            if (r0 == 0) goto L_0x02c1
            r12.realmSet$idcardcode(r2)
            goto L_0x02ca
        L_0x02c1:
            java.lang.String r0 = "idcardcode"
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$idcardcode(r0)
        L_0x02ca:
            java.lang.String r0 = "iccard"
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L_0x02e7
            java.lang.String r0 = "iccard"
            boolean r0 = r13.isNull(r0)
            if (r0 == 0) goto L_0x02de
            r12.realmSet$iccard(r2)
            goto L_0x02e7
        L_0x02de:
            java.lang.String r0 = "iccard"
            java.lang.String r13 = r13.getString(r0)
            r12.realmSet$iccard(r13)
        L_0x02e7:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.EmployeeBean");
    }

    public static EmployeeBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        EmployeeBean employeeBean = new EmployeeBean();
        com_ciot_realm_db_EmployeeBeanRealmProxyInterface com_ciot_realm_db_employeebeanrealmproxyinterface = employeeBean;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$id((String) null);
                }
                z = true;
            } else if (nextName.equals(ServiceProvider.NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$name(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$name((String) null);
                }
            } else if (nextName.equals("card")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$card(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$card((String) null);
                }
            } else if (nextName.equals("idcard")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$idcard(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$idcard((String) null);
                }
            } else if (nextName.equals("face")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$face(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$face((String) null);
                }
            } else if (nextName.equals("fingerprint")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$fingerprint(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$fingerprint((String) null);
                }
            } else if (nextName.equals("phone")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$phone(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$phone((String) null);
                }
            } else if (nextName.equals(NotificationCompat.CATEGORY_EMAIL)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$email(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$email((String) null);
                }
            } else if (nextName.equals("sex")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$sex(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$sex((String) null);
                }
            } else if (nextName.equals("department")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$department(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$department((String) null);
                }
            } else if (nextName.equals("company")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$company(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$company((String) null);
                }
            } else if (nextName.equals("job")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$job(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$job((String) null);
                }
            } else if (nextName.equals("arccode")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$arccode(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$arccode((String) null);
                }
            } else if (nextName.equals("arccodebytes")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$arccodebytes(JsonUtils.stringToBytes(jsonReader.nextString()));
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$arccodebytes((byte[]) null);
                }
            } else if (nextName.equals("staffno")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$staffno(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$staffno((String) null);
                }
            } else if (nextName.equals("greetings")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$greetings(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$greetings((String) null);
                }
            } else if (nextName.equals("senscode")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$senscode(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$senscode((String) null);
                }
            } else if (nextName.equals("wechat")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$wechat(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$wechat((String) null);
                }
            } else if (nextName.equals("description")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$description(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$description((String) null);
                }
            } else if (nextName.equals("birthday")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$birthday(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$birthday((String) null);
                }
            } else if (nextName.equals("companyId")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$companyId(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$companyId((String) null);
                }
            } else if (nextName.equals("companyName")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$companyName(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$companyName((String) null);
                }
            } else if (nextName.equals("idcardcode")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$idcardcode(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$idcardcode((String) null);
                }
            } else if (!nextName.equals("iccard")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$iccard(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$iccard((String) null);
            }
        }
        jsonReader.endObject();
        if (z) {
            return (EmployeeBean) realm.copyToRealm(employeeBean, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_EmployeeBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeBean.class), false, Collections.emptyList());
        com_ciot_realm_db_EmployeeBeanRealmProxy com_ciot_realm_db_employeebeanrealmproxy = new com_ciot_realm_db_EmployeeBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_employeebeanrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.EmployeeBean copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy.EmployeeBeanColumnInfo r9, com.ciot.realm.db.EmployeeBean r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.EmployeeBean r1 = (com.ciot.realm.db.EmployeeBean) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.EmployeeBean> r2 = com.ciot.realm.db.EmployeeBean.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_EmployeeBeanRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_EmployeeBeanRealmProxyInterface) r5
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
            io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy r1 = new io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.EmployeeBean r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.EmployeeBean r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy$EmployeeBeanColumnInfo, com.ciot.realm.db.EmployeeBean, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.EmployeeBean");
    }

    public static EmployeeBean copy(Realm realm, EmployeeBeanColumnInfo employeeBeanColumnInfo, EmployeeBean employeeBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(employeeBean);
        if (realmObjectProxy != null) {
            return (EmployeeBean) realmObjectProxy;
        }
        com_ciot_realm_db_EmployeeBeanRealmProxyInterface com_ciot_realm_db_employeebeanrealmproxyinterface = employeeBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(EmployeeBean.class), set);
        osObjectBuilder.addString(employeeBeanColumnInfo.idColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$id());
        osObjectBuilder.addString(employeeBeanColumnInfo.nameColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$name());
        osObjectBuilder.addString(employeeBeanColumnInfo.cardColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$card());
        osObjectBuilder.addString(employeeBeanColumnInfo.idcardColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$idcard());
        osObjectBuilder.addString(employeeBeanColumnInfo.faceColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$face());
        osObjectBuilder.addString(employeeBeanColumnInfo.fingerprintColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$fingerprint());
        osObjectBuilder.addString(employeeBeanColumnInfo.phoneColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$phone());
        osObjectBuilder.addString(employeeBeanColumnInfo.emailColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$email());
        osObjectBuilder.addString(employeeBeanColumnInfo.sexColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$sex());
        osObjectBuilder.addString(employeeBeanColumnInfo.departmentColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$department());
        osObjectBuilder.addString(employeeBeanColumnInfo.companyColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$company());
        osObjectBuilder.addString(employeeBeanColumnInfo.jobColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$job());
        osObjectBuilder.addString(employeeBeanColumnInfo.arccodeColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$arccode());
        osObjectBuilder.addByteArray(employeeBeanColumnInfo.arccodebytesColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$arccodebytes());
        osObjectBuilder.addString(employeeBeanColumnInfo.staffnoColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$staffno());
        osObjectBuilder.addString(employeeBeanColumnInfo.greetingsColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$greetings());
        osObjectBuilder.addString(employeeBeanColumnInfo.senscodeColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$senscode());
        osObjectBuilder.addString(employeeBeanColumnInfo.wechatColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$wechat());
        osObjectBuilder.addString(employeeBeanColumnInfo.descriptionColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$description());
        osObjectBuilder.addString(employeeBeanColumnInfo.birthdayColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$birthday());
        osObjectBuilder.addString(employeeBeanColumnInfo.companyIdColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$companyId());
        osObjectBuilder.addString(employeeBeanColumnInfo.companyNameColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$companyName());
        osObjectBuilder.addString(employeeBeanColumnInfo.idcardcodeColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$idcardcode());
        osObjectBuilder.addString(employeeBeanColumnInfo.iccardColKey, com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$iccard());
        com_ciot_realm_db_EmployeeBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(employeeBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, EmployeeBean employeeBean, Map<RealmModel, Long> map) {
        long j;
        EmployeeBean employeeBean2 = employeeBean;
        if ((employeeBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(EmployeeBean.class);
        long nativePtr = table.getNativePtr();
        EmployeeBeanColumnInfo employeeBeanColumnInfo = (EmployeeBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeBean.class);
        long j2 = employeeBeanColumnInfo.idColKey;
        com_ciot_realm_db_EmployeeBeanRealmProxyInterface com_ciot_realm_db_employeebeanrealmproxyinterface = employeeBean2;
        String realmGet$id = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$id();
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
        map.put(employeeBean2, Long.valueOf(j3));
        String realmGet$name = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.nameColKey, j3, realmGet$name, false);
        }
        String realmGet$card = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$card();
        if (realmGet$card != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.cardColKey, j3, realmGet$card, false);
        }
        String realmGet$idcard = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$idcard();
        if (realmGet$idcard != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.idcardColKey, j3, realmGet$idcard, false);
        }
        String realmGet$face = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$face();
        if (realmGet$face != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.faceColKey, j3, realmGet$face, false);
        }
        String realmGet$fingerprint = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$fingerprint();
        if (realmGet$fingerprint != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.fingerprintColKey, j3, realmGet$fingerprint, false);
        }
        String realmGet$phone = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$phone();
        if (realmGet$phone != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.phoneColKey, j3, realmGet$phone, false);
        }
        String realmGet$email = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.emailColKey, j3, realmGet$email, false);
        }
        String realmGet$sex = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$sex();
        if (realmGet$sex != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.sexColKey, j3, realmGet$sex, false);
        }
        String realmGet$department = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$department();
        if (realmGet$department != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.departmentColKey, j3, realmGet$department, false);
        }
        String realmGet$company = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$company();
        if (realmGet$company != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.companyColKey, j3, realmGet$company, false);
        }
        String realmGet$job = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$job();
        if (realmGet$job != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.jobColKey, j3, realmGet$job, false);
        }
        String realmGet$arccode = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$arccode();
        if (realmGet$arccode != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.arccodeColKey, j3, realmGet$arccode, false);
        }
        byte[] realmGet$arccodebytes = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$arccodebytes();
        if (realmGet$arccodebytes != null) {
            Table.nativeSetByteArray(nativePtr, employeeBeanColumnInfo.arccodebytesColKey, j3, realmGet$arccodebytes, false);
        }
        String realmGet$staffno = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$staffno();
        if (realmGet$staffno != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.staffnoColKey, j3, realmGet$staffno, false);
        }
        String realmGet$greetings = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$greetings();
        if (realmGet$greetings != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.greetingsColKey, j3, realmGet$greetings, false);
        }
        String realmGet$senscode = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$senscode();
        if (realmGet$senscode != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.senscodeColKey, j3, realmGet$senscode, false);
        }
        String realmGet$wechat = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$wechat();
        if (realmGet$wechat != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.wechatColKey, j3, realmGet$wechat, false);
        }
        String realmGet$description = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.descriptionColKey, j3, realmGet$description, false);
        }
        String realmGet$birthday = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$birthday();
        if (realmGet$birthday != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.birthdayColKey, j3, realmGet$birthday, false);
        }
        String realmGet$companyId = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$companyId();
        if (realmGet$companyId != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.companyIdColKey, j3, realmGet$companyId, false);
        }
        String realmGet$companyName = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$companyName();
        if (realmGet$companyName != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.companyNameColKey, j3, realmGet$companyName, false);
        }
        String realmGet$idcardcode = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$idcardcode();
        if (realmGet$idcardcode != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.idcardcodeColKey, j3, realmGet$idcardcode, false);
        }
        String realmGet$iccard = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$iccard();
        if (realmGet$iccard != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.iccardColKey, j3, realmGet$iccard, false);
        }
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(EmployeeBean.class);
        long nativePtr = table.getNativePtr();
        EmployeeBeanColumnInfo employeeBeanColumnInfo = (EmployeeBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeBean.class);
        long j3 = employeeBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            EmployeeBean employeeBean = (EmployeeBean) it.next();
            if (!map2.containsKey(employeeBean)) {
                if ((employeeBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(employeeBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_EmployeeBeanRealmProxyInterface com_ciot_realm_db_employeebeanrealmproxyinterface = employeeBean;
                String realmGet$id = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j3);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j3, realmGet$id);
                }
                if (j == -1) {
                    j2 = OsObject.createRowWithPrimaryKey(table, j3, realmGet$id);
                } else {
                    Table.throwDuplicatePrimaryKeyException(realmGet$id);
                    j2 = j;
                }
                map2.put(employeeBean, Long.valueOf(j2));
                String realmGet$name = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.nameColKey, j2, realmGet$name, false);
                }
                String realmGet$card = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$card();
                if (realmGet$card != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.cardColKey, j2, realmGet$card, false);
                }
                String realmGet$idcard = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$idcard();
                if (realmGet$idcard != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.idcardColKey, j2, realmGet$idcard, false);
                }
                String realmGet$face = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$face();
                if (realmGet$face != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.faceColKey, j2, realmGet$face, false);
                }
                String realmGet$fingerprint = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$fingerprint();
                if (realmGet$fingerprint != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.fingerprintColKey, j2, realmGet$fingerprint, false);
                }
                String realmGet$phone = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$phone();
                if (realmGet$phone != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.phoneColKey, j2, realmGet$phone, false);
                }
                String realmGet$email = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$email();
                if (realmGet$email != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.emailColKey, j2, realmGet$email, false);
                }
                String realmGet$sex = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$sex();
                if (realmGet$sex != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.sexColKey, j2, realmGet$sex, false);
                }
                String realmGet$department = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$department();
                if (realmGet$department != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.departmentColKey, j2, realmGet$department, false);
                }
                String realmGet$company = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$company();
                if (realmGet$company != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.companyColKey, j2, realmGet$company, false);
                }
                String realmGet$job = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$job();
                if (realmGet$job != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.jobColKey, j2, realmGet$job, false);
                }
                String realmGet$arccode = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$arccode();
                if (realmGet$arccode != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.arccodeColKey, j2, realmGet$arccode, false);
                }
                byte[] realmGet$arccodebytes = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$arccodebytes();
                if (realmGet$arccodebytes != null) {
                    Table.nativeSetByteArray(nativePtr, employeeBeanColumnInfo.arccodebytesColKey, j2, realmGet$arccodebytes, false);
                }
                String realmGet$staffno = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$staffno();
                if (realmGet$staffno != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.staffnoColKey, j2, realmGet$staffno, false);
                }
                String realmGet$greetings = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$greetings();
                if (realmGet$greetings != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.greetingsColKey, j2, realmGet$greetings, false);
                }
                String realmGet$senscode = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$senscode();
                if (realmGet$senscode != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.senscodeColKey, j2, realmGet$senscode, false);
                }
                String realmGet$wechat = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$wechat();
                if (realmGet$wechat != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.wechatColKey, j2, realmGet$wechat, false);
                }
                String realmGet$description = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.descriptionColKey, j2, realmGet$description, false);
                }
                String realmGet$birthday = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$birthday();
                if (realmGet$birthday != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.birthdayColKey, j2, realmGet$birthday, false);
                }
                String realmGet$companyId = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$companyId();
                if (realmGet$companyId != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.companyIdColKey, j2, realmGet$companyId, false);
                }
                String realmGet$companyName = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$companyName();
                if (realmGet$companyName != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.companyNameColKey, j2, realmGet$companyName, false);
                }
                String realmGet$idcardcode = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$idcardcode();
                if (realmGet$idcardcode != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.idcardcodeColKey, j2, realmGet$idcardcode, false);
                }
                String realmGet$iccard = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$iccard();
                if (realmGet$iccard != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.iccardColKey, j2, realmGet$iccard, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, EmployeeBean employeeBean, Map<RealmModel, Long> map) {
        long j;
        EmployeeBean employeeBean2 = employeeBean;
        if ((employeeBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(EmployeeBean.class);
        long nativePtr = table.getNativePtr();
        EmployeeBeanColumnInfo employeeBeanColumnInfo = (EmployeeBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeBean.class);
        long j2 = employeeBeanColumnInfo.idColKey;
        com_ciot_realm_db_EmployeeBeanRealmProxyInterface com_ciot_realm_db_employeebeanrealmproxyinterface = employeeBean2;
        String realmGet$id = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$id);
        }
        long j3 = j;
        map.put(employeeBean2, Long.valueOf(j3));
        String realmGet$name = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.nameColKey, j3, realmGet$name, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.nameColKey, j3, false);
        }
        String realmGet$card = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$card();
        if (realmGet$card != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.cardColKey, j3, realmGet$card, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.cardColKey, j3, false);
        }
        String realmGet$idcard = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$idcard();
        if (realmGet$idcard != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.idcardColKey, j3, realmGet$idcard, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.idcardColKey, j3, false);
        }
        String realmGet$face = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$face();
        if (realmGet$face != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.faceColKey, j3, realmGet$face, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.faceColKey, j3, false);
        }
        String realmGet$fingerprint = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$fingerprint();
        if (realmGet$fingerprint != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.fingerprintColKey, j3, realmGet$fingerprint, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.fingerprintColKey, j3, false);
        }
        String realmGet$phone = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$phone();
        if (realmGet$phone != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.phoneColKey, j3, realmGet$phone, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.phoneColKey, j3, false);
        }
        String realmGet$email = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.emailColKey, j3, realmGet$email, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.emailColKey, j3, false);
        }
        String realmGet$sex = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$sex();
        if (realmGet$sex != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.sexColKey, j3, realmGet$sex, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.sexColKey, j3, false);
        }
        String realmGet$department = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$department();
        if (realmGet$department != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.departmentColKey, j3, realmGet$department, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.departmentColKey, j3, false);
        }
        String realmGet$company = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$company();
        if (realmGet$company != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.companyColKey, j3, realmGet$company, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.companyColKey, j3, false);
        }
        String realmGet$job = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$job();
        if (realmGet$job != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.jobColKey, j3, realmGet$job, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.jobColKey, j3, false);
        }
        String realmGet$arccode = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$arccode();
        if (realmGet$arccode != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.arccodeColKey, j3, realmGet$arccode, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.arccodeColKey, j3, false);
        }
        byte[] realmGet$arccodebytes = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$arccodebytes();
        if (realmGet$arccodebytes != null) {
            Table.nativeSetByteArray(nativePtr, employeeBeanColumnInfo.arccodebytesColKey, j3, realmGet$arccodebytes, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.arccodebytesColKey, j3, false);
        }
        String realmGet$staffno = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$staffno();
        if (realmGet$staffno != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.staffnoColKey, j3, realmGet$staffno, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.staffnoColKey, j3, false);
        }
        String realmGet$greetings = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$greetings();
        if (realmGet$greetings != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.greetingsColKey, j3, realmGet$greetings, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.greetingsColKey, j3, false);
        }
        String realmGet$senscode = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$senscode();
        if (realmGet$senscode != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.senscodeColKey, j3, realmGet$senscode, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.senscodeColKey, j3, false);
        }
        String realmGet$wechat = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$wechat();
        if (realmGet$wechat != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.wechatColKey, j3, realmGet$wechat, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.wechatColKey, j3, false);
        }
        String realmGet$description = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.descriptionColKey, j3, realmGet$description, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.descriptionColKey, j3, false);
        }
        String realmGet$birthday = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$birthday();
        if (realmGet$birthday != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.birthdayColKey, j3, realmGet$birthday, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.birthdayColKey, j3, false);
        }
        String realmGet$companyId = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$companyId();
        if (realmGet$companyId != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.companyIdColKey, j3, realmGet$companyId, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.companyIdColKey, j3, false);
        }
        String realmGet$companyName = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$companyName();
        if (realmGet$companyName != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.companyNameColKey, j3, realmGet$companyName, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.companyNameColKey, j3, false);
        }
        String realmGet$idcardcode = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$idcardcode();
        if (realmGet$idcardcode != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.idcardcodeColKey, j3, realmGet$idcardcode, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.idcardcodeColKey, j3, false);
        }
        String realmGet$iccard = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$iccard();
        if (realmGet$iccard != null) {
            Table.nativeSetString(nativePtr, employeeBeanColumnInfo.iccardColKey, j3, realmGet$iccard, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.iccardColKey, j3, false);
        }
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(EmployeeBean.class);
        long nativePtr = table.getNativePtr();
        EmployeeBeanColumnInfo employeeBeanColumnInfo = (EmployeeBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeBean.class);
        long j2 = employeeBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            EmployeeBean employeeBean = (EmployeeBean) it.next();
            if (!map2.containsKey(employeeBean)) {
                if ((employeeBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(employeeBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_EmployeeBeanRealmProxyInterface com_ciot_realm_db_employeebeanrealmproxyinterface = employeeBean;
                String realmGet$id = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j2);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j2, realmGet$id) : j;
                map2.put(employeeBean, Long.valueOf(createRowWithPrimaryKey));
                String realmGet$name = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.nameColKey, createRowWithPrimaryKey, realmGet$name, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.nameColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$card = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$card();
                if (realmGet$card != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.cardColKey, createRowWithPrimaryKey, realmGet$card, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.cardColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$idcard = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$idcard();
                if (realmGet$idcard != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.idcardColKey, createRowWithPrimaryKey, realmGet$idcard, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.idcardColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$face = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$face();
                if (realmGet$face != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.faceColKey, createRowWithPrimaryKey, realmGet$face, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.faceColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$fingerprint = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$fingerprint();
                if (realmGet$fingerprint != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.fingerprintColKey, createRowWithPrimaryKey, realmGet$fingerprint, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.fingerprintColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$phone = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$phone();
                if (realmGet$phone != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.phoneColKey, createRowWithPrimaryKey, realmGet$phone, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.phoneColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$email = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$email();
                if (realmGet$email != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.emailColKey, createRowWithPrimaryKey, realmGet$email, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.emailColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$sex = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$sex();
                if (realmGet$sex != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.sexColKey, createRowWithPrimaryKey, realmGet$sex, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.sexColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$department = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$department();
                if (realmGet$department != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.departmentColKey, createRowWithPrimaryKey, realmGet$department, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.departmentColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$company = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$company();
                if (realmGet$company != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.companyColKey, createRowWithPrimaryKey, realmGet$company, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.companyColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$job = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$job();
                if (realmGet$job != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.jobColKey, createRowWithPrimaryKey, realmGet$job, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.jobColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$arccode = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$arccode();
                if (realmGet$arccode != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.arccodeColKey, createRowWithPrimaryKey, realmGet$arccode, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.arccodeColKey, createRowWithPrimaryKey, false);
                }
                byte[] realmGet$arccodebytes = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$arccodebytes();
                if (realmGet$arccodebytes != null) {
                    Table.nativeSetByteArray(nativePtr, employeeBeanColumnInfo.arccodebytesColKey, createRowWithPrimaryKey, realmGet$arccodebytes, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.arccodebytesColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$staffno = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$staffno();
                if (realmGet$staffno != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.staffnoColKey, createRowWithPrimaryKey, realmGet$staffno, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.staffnoColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$greetings = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$greetings();
                if (realmGet$greetings != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.greetingsColKey, createRowWithPrimaryKey, realmGet$greetings, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.greetingsColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$senscode = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$senscode();
                if (realmGet$senscode != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.senscodeColKey, createRowWithPrimaryKey, realmGet$senscode, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.senscodeColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$wechat = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$wechat();
                if (realmGet$wechat != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.wechatColKey, createRowWithPrimaryKey, realmGet$wechat, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.wechatColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$description = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.descriptionColKey, createRowWithPrimaryKey, realmGet$description, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.descriptionColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$birthday = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$birthday();
                if (realmGet$birthday != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.birthdayColKey, createRowWithPrimaryKey, realmGet$birthday, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.birthdayColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$companyId = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$companyId();
                if (realmGet$companyId != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.companyIdColKey, createRowWithPrimaryKey, realmGet$companyId, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.companyIdColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$companyName = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$companyName();
                if (realmGet$companyName != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.companyNameColKey, createRowWithPrimaryKey, realmGet$companyName, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.companyNameColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$idcardcode = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$idcardcode();
                if (realmGet$idcardcode != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.idcardcodeColKey, createRowWithPrimaryKey, realmGet$idcardcode, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.idcardcodeColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$iccard = com_ciot_realm_db_employeebeanrealmproxyinterface.realmGet$iccard();
                if (realmGet$iccard != null) {
                    Table.nativeSetString(nativePtr, employeeBeanColumnInfo.iccardColKey, createRowWithPrimaryKey, realmGet$iccard, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeBeanColumnInfo.iccardColKey, createRowWithPrimaryKey, false);
                }
            }
        }
    }

    public static EmployeeBean createDetachedCopy(EmployeeBean employeeBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        EmployeeBean employeeBean2;
        if (i > i2 || employeeBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(employeeBean);
        if (cacheData == null) {
            employeeBean2 = new EmployeeBean();
            map.put(employeeBean, new RealmObjectProxy.CacheData(i, employeeBean2));
        } else if (i >= cacheData.minDepth) {
            return (EmployeeBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            employeeBean2 = (EmployeeBean) cacheData.object;
        }
        com_ciot_realm_db_EmployeeBeanRealmProxyInterface com_ciot_realm_db_employeebeanrealmproxyinterface = employeeBean2;
        com_ciot_realm_db_EmployeeBeanRealmProxyInterface com_ciot_realm_db_employeebeanrealmproxyinterface2 = employeeBean;
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$id(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$name(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$name());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$card(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$card());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$idcard(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$idcard());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$face(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$face());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$fingerprint(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$fingerprint());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$phone(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$phone());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$email(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$email());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$sex(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$sex());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$department(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$department());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$company(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$company());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$job(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$job());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$arccode(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$arccode());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$arccodebytes(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$arccodebytes());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$staffno(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$staffno());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$greetings(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$greetings());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$senscode(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$senscode());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$wechat(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$wechat());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$description(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$description());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$birthday(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$birthday());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$companyId(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$companyId());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$companyName(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$companyName());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$idcardcode(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$idcardcode());
        com_ciot_realm_db_employeebeanrealmproxyinterface.realmSet$iccard(com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$iccard());
        return employeeBean2;
    }

    static EmployeeBean update(Realm realm, EmployeeBeanColumnInfo employeeBeanColumnInfo, EmployeeBean employeeBean, EmployeeBean employeeBean2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_EmployeeBeanRealmProxyInterface com_ciot_realm_db_employeebeanrealmproxyinterface = employeeBean;
        com_ciot_realm_db_EmployeeBeanRealmProxyInterface com_ciot_realm_db_employeebeanrealmproxyinterface2 = employeeBean2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(EmployeeBean.class), set);
        osObjectBuilder.addString(employeeBeanColumnInfo.idColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$id());
        osObjectBuilder.addString(employeeBeanColumnInfo.nameColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$name());
        osObjectBuilder.addString(employeeBeanColumnInfo.cardColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$card());
        osObjectBuilder.addString(employeeBeanColumnInfo.idcardColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$idcard());
        osObjectBuilder.addString(employeeBeanColumnInfo.faceColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$face());
        osObjectBuilder.addString(employeeBeanColumnInfo.fingerprintColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$fingerprint());
        osObjectBuilder.addString(employeeBeanColumnInfo.phoneColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$phone());
        osObjectBuilder.addString(employeeBeanColumnInfo.emailColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$email());
        osObjectBuilder.addString(employeeBeanColumnInfo.sexColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$sex());
        osObjectBuilder.addString(employeeBeanColumnInfo.departmentColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$department());
        osObjectBuilder.addString(employeeBeanColumnInfo.companyColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$company());
        osObjectBuilder.addString(employeeBeanColumnInfo.jobColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$job());
        osObjectBuilder.addString(employeeBeanColumnInfo.arccodeColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$arccode());
        osObjectBuilder.addByteArray(employeeBeanColumnInfo.arccodebytesColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$arccodebytes());
        osObjectBuilder.addString(employeeBeanColumnInfo.staffnoColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$staffno());
        osObjectBuilder.addString(employeeBeanColumnInfo.greetingsColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$greetings());
        osObjectBuilder.addString(employeeBeanColumnInfo.senscodeColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$senscode());
        osObjectBuilder.addString(employeeBeanColumnInfo.wechatColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$wechat());
        osObjectBuilder.addString(employeeBeanColumnInfo.descriptionColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$description());
        osObjectBuilder.addString(employeeBeanColumnInfo.birthdayColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$birthday());
        osObjectBuilder.addString(employeeBeanColumnInfo.companyIdColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$companyId());
        osObjectBuilder.addString(employeeBeanColumnInfo.companyNameColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$companyName());
        osObjectBuilder.addString(employeeBeanColumnInfo.idcardcodeColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$idcardcode());
        osObjectBuilder.addString(employeeBeanColumnInfo.iccardColKey, com_ciot_realm_db_employeebeanrealmproxyinterface2.realmGet$iccard());
        osObjectBuilder.updateExistingObject();
        return employeeBean;
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
