package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import androidx.core.app.NotificationCompat;
import com.ciot.realm.db.employee.EmployeeResponse;
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

public class com_ciot_realm_db_employee_EmployeeResponseRealmProxy extends EmployeeResponse implements RealmObjectProxy, com_ciot_realm_db_employee_EmployeeResponseRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private EmployeeResponseColumnInfo columnInfo;
    private ProxyState<EmployeeResponse> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "EmployeeResponse";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class EmployeeResponseColumnInfo extends ColumnInfo {
        long arcCodeColKey;
        long arcexresultColKey;
        long birthPromptColKey;
        long birthdayColKey;
        long byfea_hr31ColKey;
        long companyColKey;
        long createtimeColKey;
        long departmentColKey;
        long descriptionColKey;
        long emailColKey;
        long faceColKey;
        long fingerprintColKey;
        long iccardColKey;
        long idColKey;
        long idcardColKey;
        long isHasfea_hrColKey;
        long isHasfea_stColKey;
        long jobColKey;
        long nameColKey;
        long pathColKey;
        long phoneColKey;
        long promptColKey;
        long sexColKey;
        long staffnoColKey;
        long statusColKey;
        long stfea_hr31ColKey;
        long stfea_stColKey;
        long typeColKey;
        long uuidColKey;
        long vipPromptColKey;
        long wechatColKey;

        EmployeeResponseColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(31);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.staffnoColKey = addColumnDetails("staffno", "staffno", objectSchemaInfo);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
            this.idcardColKey = addColumnDetails("idcard", "idcard", objectSchemaInfo);
            this.faceColKey = addColumnDetails("face", "face", objectSchemaInfo);
            this.iccardColKey = addColumnDetails("iccard", "iccard", objectSchemaInfo);
            this.phoneColKey = addColumnDetails("phone", "phone", objectSchemaInfo);
            this.emailColKey = addColumnDetails(NotificationCompat.CATEGORY_EMAIL, NotificationCompat.CATEGORY_EMAIL, objectSchemaInfo);
            this.typeColKey = addColumnDetails("type", "type", objectSchemaInfo);
            this.sexColKey = addColumnDetails("sex", "sex", objectSchemaInfo);
            this.departmentColKey = addColumnDetails("department", "department", objectSchemaInfo);
            this.companyColKey = addColumnDetails("company", "company", objectSchemaInfo);
            this.jobColKey = addColumnDetails("job", "job", objectSchemaInfo);
            this.fingerprintColKey = addColumnDetails("fingerprint", "fingerprint", objectSchemaInfo);
            this.wechatColKey = addColumnDetails("wechat", "wechat", objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
            this.birthdayColKey = addColumnDetails("birthday", "birthday", objectSchemaInfo);
            this.arcCodeColKey = addColumnDetails("arcCode", "arcCode", objectSchemaInfo);
            this.createtimeColKey = addColumnDetails("createtime", "createtime", objectSchemaInfo);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.birthPromptColKey = addColumnDetails("birthPrompt", "birthPrompt", objectSchemaInfo);
            this.vipPromptColKey = addColumnDetails("vipPrompt", "vipPrompt", objectSchemaInfo);
            this.promptColKey = addColumnDetails("prompt", "prompt", objectSchemaInfo);
            this.statusColKey = addColumnDetails(NotificationCompat.CATEGORY_STATUS, NotificationCompat.CATEGORY_STATUS, objectSchemaInfo);
            this.pathColKey = addColumnDetails("path", "path", objectSchemaInfo);
            this.uuidColKey = addColumnDetails("uuid", "uuid", objectSchemaInfo);
            this.isHasfea_stColKey = addColumnDetails("isHasfea_st", "isHasfea_st", objectSchemaInfo);
            this.stfea_stColKey = addColumnDetails("stfea_st", "stfea_st", objectSchemaInfo);
            this.isHasfea_hrColKey = addColumnDetails("isHasfea_hr", "isHasfea_hr", objectSchemaInfo);
            this.stfea_hr31ColKey = addColumnDetails("stfea_hr31", "stfea_hr31", objectSchemaInfo);
            this.byfea_hr31ColKey = addColumnDetails("byfea_hr31", "byfea_hr31", objectSchemaInfo);
            this.arcexresultColKey = addColumnDetails("arcexresult", "arcexresult", objectSchemaInfo);
        }

        EmployeeResponseColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new EmployeeResponseColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            EmployeeResponseColumnInfo employeeResponseColumnInfo = (EmployeeResponseColumnInfo) columnInfo;
            EmployeeResponseColumnInfo employeeResponseColumnInfo2 = (EmployeeResponseColumnInfo) columnInfo2;
            employeeResponseColumnInfo2.staffnoColKey = employeeResponseColumnInfo.staffnoColKey;
            employeeResponseColumnInfo2.nameColKey = employeeResponseColumnInfo.nameColKey;
            employeeResponseColumnInfo2.idcardColKey = employeeResponseColumnInfo.idcardColKey;
            employeeResponseColumnInfo2.faceColKey = employeeResponseColumnInfo.faceColKey;
            employeeResponseColumnInfo2.iccardColKey = employeeResponseColumnInfo.iccardColKey;
            employeeResponseColumnInfo2.phoneColKey = employeeResponseColumnInfo.phoneColKey;
            employeeResponseColumnInfo2.emailColKey = employeeResponseColumnInfo.emailColKey;
            employeeResponseColumnInfo2.typeColKey = employeeResponseColumnInfo.typeColKey;
            employeeResponseColumnInfo2.sexColKey = employeeResponseColumnInfo.sexColKey;
            employeeResponseColumnInfo2.departmentColKey = employeeResponseColumnInfo.departmentColKey;
            employeeResponseColumnInfo2.companyColKey = employeeResponseColumnInfo.companyColKey;
            employeeResponseColumnInfo2.jobColKey = employeeResponseColumnInfo.jobColKey;
            employeeResponseColumnInfo2.fingerprintColKey = employeeResponseColumnInfo.fingerprintColKey;
            employeeResponseColumnInfo2.wechatColKey = employeeResponseColumnInfo.wechatColKey;
            employeeResponseColumnInfo2.descriptionColKey = employeeResponseColumnInfo.descriptionColKey;
            employeeResponseColumnInfo2.birthdayColKey = employeeResponseColumnInfo.birthdayColKey;
            employeeResponseColumnInfo2.arcCodeColKey = employeeResponseColumnInfo.arcCodeColKey;
            employeeResponseColumnInfo2.createtimeColKey = employeeResponseColumnInfo.createtimeColKey;
            employeeResponseColumnInfo2.idColKey = employeeResponseColumnInfo.idColKey;
            employeeResponseColumnInfo2.birthPromptColKey = employeeResponseColumnInfo.birthPromptColKey;
            employeeResponseColumnInfo2.vipPromptColKey = employeeResponseColumnInfo.vipPromptColKey;
            employeeResponseColumnInfo2.promptColKey = employeeResponseColumnInfo.promptColKey;
            employeeResponseColumnInfo2.statusColKey = employeeResponseColumnInfo.statusColKey;
            employeeResponseColumnInfo2.pathColKey = employeeResponseColumnInfo.pathColKey;
            employeeResponseColumnInfo2.uuidColKey = employeeResponseColumnInfo.uuidColKey;
            employeeResponseColumnInfo2.isHasfea_stColKey = employeeResponseColumnInfo.isHasfea_stColKey;
            employeeResponseColumnInfo2.stfea_stColKey = employeeResponseColumnInfo.stfea_stColKey;
            employeeResponseColumnInfo2.isHasfea_hrColKey = employeeResponseColumnInfo.isHasfea_hrColKey;
            employeeResponseColumnInfo2.stfea_hr31ColKey = employeeResponseColumnInfo.stfea_hr31ColKey;
            employeeResponseColumnInfo2.byfea_hr31ColKey = employeeResponseColumnInfo.byfea_hr31ColKey;
            employeeResponseColumnInfo2.arcexresultColKey = employeeResponseColumnInfo.arcexresultColKey;
        }
    }

    com_ciot_realm_db_employee_EmployeeResponseRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (EmployeeResponseColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<EmployeeResponse> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
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

    public String realmGet$arcCode() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.arcCodeColKey);
    }

    public void realmSet$arcCode(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.arcCodeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.arcCodeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.arcCodeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.arcCodeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public long realmGet$createtime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.createtimeColKey);
    }

    public void realmSet$createtime(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.createtimeColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.createtimeColKey, row$realm.getObjectKey(), j, true);
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

    public String realmGet$birthPrompt() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.birthPromptColKey);
    }

    public void realmSet$birthPrompt(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.birthPromptColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.birthPromptColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.birthPromptColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.birthPromptColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$vipPrompt() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.vipPromptColKey);
    }

    public void realmSet$vipPrompt(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.vipPromptColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.vipPromptColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.vipPromptColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.vipPromptColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$prompt() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.promptColKey);
    }

    public void realmSet$prompt(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.promptColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.promptColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.promptColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.promptColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public int realmGet$status() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.statusColKey);
    }

    public void realmSet$status(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.statusColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.statusColKey, row$realm.getObjectKey(), (long) i, true);
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

    public Integer realmGet$uuid() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNull(this.columnInfo.uuidColKey)) {
            return null;
        }
        return Integer.valueOf((int) this.proxyState.getRow$realm().getLong(this.columnInfo.uuidColKey));
    }

    public void realmSet$uuid(Integer num) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (num == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.uuidColKey);
            } else {
                this.proxyState.getRow$realm().setLong(this.columnInfo.uuidColKey, (long) num.intValue());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (num == null) {
                row$realm.getTable().setNull(this.columnInfo.uuidColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setLong(this.columnInfo.uuidColKey, row$realm.getObjectKey(), (long) num.intValue(), true);
            }
        }
    }

    public boolean realmGet$isHasfea_st() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isHasfea_stColKey);
    }

    public void realmSet$isHasfea_st(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isHasfea_stColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.isHasfea_stColKey, row$realm.getObjectKey(), z, true);
        }
    }

    public String realmGet$stfea_st() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.stfea_stColKey);
    }

    public void realmSet$stfea_st(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.stfea_stColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.stfea_stColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.stfea_stColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.stfea_stColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public boolean realmGet$isHasfea_hr() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isHasfea_hrColKey);
    }

    public void realmSet$isHasfea_hr(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isHasfea_hrColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.isHasfea_hrColKey, row$realm.getObjectKey(), z, true);
        }
    }

    public String realmGet$stfea_hr31() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.stfea_hr31ColKey);
    }

    public void realmSet$stfea_hr31(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.stfea_hr31ColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.stfea_hr31ColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.stfea_hr31ColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.stfea_hr31ColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public byte[] realmGet$byfea_hr31() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBinaryByteArray(this.columnInfo.byfea_hr31ColKey);
    }

    public void realmSet$byfea_hr31(byte[] bArr) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (bArr == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.byfea_hr31ColKey);
            } else {
                this.proxyState.getRow$realm().setBinaryByteArray(this.columnInfo.byfea_hr31ColKey, bArr);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (bArr == null) {
                row$realm.getTable().setNull(this.columnInfo.byfea_hr31ColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setBinaryByteArray(this.columnInfo.byfea_hr31ColKey, row$realm.getObjectKey(), bArr, true);
            }
        }
    }

    public int realmGet$arcexresult() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.arcexresultColKey);
    }

    public void realmSet$arcexresult(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.arcexresultColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.arcexresultColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 31, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("staffno", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("idcard", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("face", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("iccard", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("phone", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(NotificationCompat.CATEGORY_EMAIL, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("type", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("sex", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("department", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("company", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("job", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("fingerprint", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("wechat", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("birthday", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("arcCode", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("createtime", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("id", RealmFieldType.STRING, true, false, false);
        builder2.addPersistedProperty("birthPrompt", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("vipPrompt", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("prompt", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(NotificationCompat.CATEGORY_STATUS, RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("path", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("uuid", RealmFieldType.INTEGER, false, false, false);
        builder2.addPersistedProperty("isHasfea_st", RealmFieldType.BOOLEAN, false, false, true);
        builder2.addPersistedProperty("stfea_st", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("isHasfea_hr", RealmFieldType.BOOLEAN, false, false, true);
        builder2.addPersistedProperty("stfea_hr31", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("byfea_hr31", RealmFieldType.BINARY, false, false, false);
        builder2.addPersistedProperty("arcexresult", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static EmployeeResponseColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new EmployeeResponseColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v10, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v11, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x01d1  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01ea  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0203  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x021c  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0235  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x024e  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x026c  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0285  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x029e  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02bb  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x02dd  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x02fa  */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x031b  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x033d  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x035a  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x037c  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x0399  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x03ba  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x014f  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x016d  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01b8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.employee.EmployeeResponse createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            java.lang.String r1 = "id"
            r2 = 0
            if (r14 == 0) goto L_0x0064
            java.lang.Class<com.ciot.realm.db.employee.EmployeeResponse> r14 = com.ciot.realm.db.employee.EmployeeResponse.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.employee.EmployeeResponse> r4 = com.ciot.realm.db.employee.EmployeeResponse.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxy$EmployeeResponseColumnInfo r3 = (io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxy.EmployeeResponseColumnInfo) r3
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
            java.lang.Class<com.ciot.realm.db.employee.EmployeeResponse> r3 = com.ciot.realm.db.employee.EmployeeResponse.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005f }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005f }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005f }
            io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxy r14 = new io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxy     // Catch:{ all -> 0x005f }
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
            java.lang.Class<com.ciot.realm.db.employee.EmployeeResponse> r14 = com.ciot.realm.db.employee.EmployeeResponse.class
            r1 = 1
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r1, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxy r14 = (io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxy) r14
            goto L_0x0095
        L_0x007e:
            java.lang.Class<com.ciot.realm.db.employee.EmployeeResponse> r14 = com.ciot.realm.db.employee.EmployeeResponse.class
            java.lang.String r1 = r13.getString(r1)
            r3 = 1
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxy r14 = (io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxy) r14
            goto L_0x0095
        L_0x008d:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'id'."
            r12.<init>(r13)
            throw r12
        L_0x0095:
            r12 = r14
            io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxyInterface) r12
            java.lang.String r0 = "staffno"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00b1
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00aa
            r12.realmSet$staffno(r2)
            goto L_0x00b1
        L_0x00aa:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$staffno(r0)
        L_0x00b1:
            java.lang.String r0 = "name"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00ca
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00c3
            r12.realmSet$name(r2)
            goto L_0x00ca
        L_0x00c3:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$name(r0)
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
            java.lang.String r0 = "iccard"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0115
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x010e
            r12.realmSet$iccard(r2)
            goto L_0x0115
        L_0x010e:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$iccard(r0)
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
            java.lang.String r0 = "type"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0165
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x015d
            int r0 = r13.getInt(r0)
            r12.realmSet$type(r0)
            goto L_0x0165
        L_0x015d:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'type' to null."
            r12.<init>(r13)
            throw r12
        L_0x0165:
            java.lang.String r0 = "sex"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x017e
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0177
            r12.realmSet$sex(r2)
            goto L_0x017e
        L_0x0177:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$sex(r0)
        L_0x017e:
            java.lang.String r0 = "department"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0197
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0190
            r12.realmSet$department(r2)
            goto L_0x0197
        L_0x0190:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$department(r0)
        L_0x0197:
            java.lang.String r0 = "company"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01b0
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01a9
            r12.realmSet$company(r2)
            goto L_0x01b0
        L_0x01a9:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$company(r0)
        L_0x01b0:
            java.lang.String r0 = "job"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01c9
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01c2
            r12.realmSet$job(r2)
            goto L_0x01c9
        L_0x01c2:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$job(r0)
        L_0x01c9:
            java.lang.String r0 = "fingerprint"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01e2
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01db
            r12.realmSet$fingerprint(r2)
            goto L_0x01e2
        L_0x01db:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$fingerprint(r0)
        L_0x01e2:
            java.lang.String r0 = "wechat"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01fb
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01f4
            r12.realmSet$wechat(r2)
            goto L_0x01fb
        L_0x01f4:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$wechat(r0)
        L_0x01fb:
            java.lang.String r0 = "description"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0214
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x020d
            r12.realmSet$description(r2)
            goto L_0x0214
        L_0x020d:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$description(r0)
        L_0x0214:
            java.lang.String r0 = "birthday"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x022d
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0226
            r12.realmSet$birthday(r2)
            goto L_0x022d
        L_0x0226:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$birthday(r0)
        L_0x022d:
            java.lang.String r0 = "arcCode"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0246
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x023f
            r12.realmSet$arcCode(r2)
            goto L_0x0246
        L_0x023f:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$arcCode(r0)
        L_0x0246:
            java.lang.String r0 = "createtime"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0264
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x025c
            long r0 = r13.getLong(r0)
            r12.realmSet$createtime(r0)
            goto L_0x0264
        L_0x025c:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'createtime' to null."
            r12.<init>(r13)
            throw r12
        L_0x0264:
            java.lang.String r0 = "birthPrompt"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x027d
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0276
            r12.realmSet$birthPrompt(r2)
            goto L_0x027d
        L_0x0276:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$birthPrompt(r0)
        L_0x027d:
            java.lang.String r0 = "vipPrompt"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0296
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x028f
            r12.realmSet$vipPrompt(r2)
            goto L_0x0296
        L_0x028f:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$vipPrompt(r0)
        L_0x0296:
            java.lang.String r0 = "prompt"
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L_0x02b3
            java.lang.String r0 = "prompt"
            boolean r0 = r13.isNull(r0)
            if (r0 == 0) goto L_0x02aa
            r12.realmSet$prompt(r2)
            goto L_0x02b3
        L_0x02aa:
            java.lang.String r0 = "prompt"
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$prompt(r0)
        L_0x02b3:
            java.lang.String r0 = "status"
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L_0x02d5
            java.lang.String r0 = "status"
            boolean r0 = r13.isNull(r0)
            if (r0 != 0) goto L_0x02cd
            java.lang.String r0 = "status"
            int r0 = r13.getInt(r0)
            r12.realmSet$status(r0)
            goto L_0x02d5
        L_0x02cd:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'status' to null."
            r12.<init>(r13)
            throw r12
        L_0x02d5:
            java.lang.String r0 = "path"
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L_0x02f2
            java.lang.String r0 = "path"
            boolean r0 = r13.isNull(r0)
            if (r0 == 0) goto L_0x02e9
            r12.realmSet$path(r2)
            goto L_0x02f2
        L_0x02e9:
            java.lang.String r0 = "path"
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$path(r0)
        L_0x02f2:
            java.lang.String r0 = "uuid"
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L_0x0313
            java.lang.String r0 = "uuid"
            boolean r0 = r13.isNull(r0)
            if (r0 == 0) goto L_0x0306
            r12.realmSet$uuid(r2)
            goto L_0x0313
        L_0x0306:
            java.lang.String r0 = "uuid"
            int r0 = r13.getInt(r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r12.realmSet$uuid(r0)
        L_0x0313:
            java.lang.String r0 = "isHasfea_st"
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L_0x0335
            java.lang.String r0 = "isHasfea_st"
            boolean r0 = r13.isNull(r0)
            if (r0 != 0) goto L_0x032d
            java.lang.String r0 = "isHasfea_st"
            boolean r0 = r13.getBoolean(r0)
            r12.realmSet$isHasfea_st(r0)
            goto L_0x0335
        L_0x032d:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'isHasfea_st' to null."
            r12.<init>(r13)
            throw r12
        L_0x0335:
            java.lang.String r0 = "stfea_st"
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L_0x0352
            java.lang.String r0 = "stfea_st"
            boolean r0 = r13.isNull(r0)
            if (r0 == 0) goto L_0x0349
            r12.realmSet$stfea_st(r2)
            goto L_0x0352
        L_0x0349:
            java.lang.String r0 = "stfea_st"
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$stfea_st(r0)
        L_0x0352:
            java.lang.String r0 = "isHasfea_hr"
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L_0x0374
            java.lang.String r0 = "isHasfea_hr"
            boolean r0 = r13.isNull(r0)
            if (r0 != 0) goto L_0x036c
            java.lang.String r0 = "isHasfea_hr"
            boolean r0 = r13.getBoolean(r0)
            r12.realmSet$isHasfea_hr(r0)
            goto L_0x0374
        L_0x036c:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'isHasfea_hr' to null."
            r12.<init>(r13)
            throw r12
        L_0x0374:
            java.lang.String r0 = "stfea_hr31"
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L_0x0391
            java.lang.String r0 = "stfea_hr31"
            boolean r0 = r13.isNull(r0)
            if (r0 == 0) goto L_0x0388
            r12.realmSet$stfea_hr31(r2)
            goto L_0x0391
        L_0x0388:
            java.lang.String r0 = "stfea_hr31"
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$stfea_hr31(r0)
        L_0x0391:
            java.lang.String r0 = "byfea_hr31"
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L_0x03b2
            java.lang.String r0 = "byfea_hr31"
            boolean r0 = r13.isNull(r0)
            if (r0 == 0) goto L_0x03a5
            r12.realmSet$byfea_hr31(r2)
            goto L_0x03b2
        L_0x03a5:
            java.lang.String r0 = "byfea_hr31"
            java.lang.String r0 = r13.getString(r0)
            byte[] r0 = io.realm.internal.android.JsonUtils.stringToBytes(r0)
            r12.realmSet$byfea_hr31(r0)
        L_0x03b2:
            java.lang.String r0 = "arcexresult"
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L_0x03d4
            java.lang.String r0 = "arcexresult"
            boolean r0 = r13.isNull(r0)
            if (r0 != 0) goto L_0x03cc
            java.lang.String r0 = "arcexresult"
            int r13 = r13.getInt(r0)
            r12.realmSet$arcexresult(r13)
            goto L_0x03d4
        L_0x03cc:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'arcexresult' to null."
            r12.<init>(r13)
            throw r12
        L_0x03d4:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.employee.EmployeeResponse");
    }

    public static EmployeeResponse createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        com_ciot_realm_db_employee_EmployeeResponseRealmProxyInterface com_ciot_realm_db_employee_employeeresponserealmproxyinterface = employeeResponse;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("staffno")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$staffno(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$staffno((String) null);
                }
            } else if (nextName.equals(ServiceProvider.NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$name(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$name((String) null);
                }
            } else if (nextName.equals("idcard")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$idcard(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$idcard((String) null);
                }
            } else if (nextName.equals("face")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$face(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$face((String) null);
                }
            } else if (nextName.equals("iccard")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$iccard(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$iccard((String) null);
                }
            } else if (nextName.equals("phone")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$phone(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$phone((String) null);
                }
            } else if (nextName.equals(NotificationCompat.CATEGORY_EMAIL)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$email(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$email((String) null);
                }
            } else if (nextName.equals("type")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$type(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'type' to null.");
                }
            } else if (nextName.equals("sex")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$sex(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$sex((String) null);
                }
            } else if (nextName.equals("department")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$department(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$department((String) null);
                }
            } else if (nextName.equals("company")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$company(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$company((String) null);
                }
            } else if (nextName.equals("job")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$job(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$job((String) null);
                }
            } else if (nextName.equals("fingerprint")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$fingerprint(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$fingerprint((String) null);
                }
            } else if (nextName.equals("wechat")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$wechat(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$wechat((String) null);
                }
            } else if (nextName.equals("description")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$description(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$description((String) null);
                }
            } else if (nextName.equals("birthday")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$birthday(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$birthday((String) null);
                }
            } else if (nextName.equals("arcCode")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$arcCode(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$arcCode((String) null);
                }
            } else if (nextName.equals("createtime")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$createtime(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'createtime' to null.");
                }
            } else if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$id((String) null);
                }
                z = true;
            } else if (nextName.equals("birthPrompt")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$birthPrompt(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$birthPrompt((String) null);
                }
            } else if (nextName.equals("vipPrompt")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$vipPrompt(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$vipPrompt((String) null);
                }
            } else if (nextName.equals("prompt")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$prompt(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$prompt((String) null);
                }
            } else if (nextName.equals(NotificationCompat.CATEGORY_STATUS)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$status(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'status' to null.");
                }
            } else if (nextName.equals("path")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$path(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$path((String) null);
                }
            } else if (nextName.equals("uuid")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$uuid(Integer.valueOf(jsonReader.nextInt()));
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$uuid((Integer) null);
                }
            } else if (nextName.equals("isHasfea_st")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$isHasfea_st(jsonReader.nextBoolean());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isHasfea_st' to null.");
                }
            } else if (nextName.equals("stfea_st")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$stfea_st(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$stfea_st((String) null);
                }
            } else if (nextName.equals("isHasfea_hr")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$isHasfea_hr(jsonReader.nextBoolean());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isHasfea_hr' to null.");
                }
            } else if (nextName.equals("stfea_hr31")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$stfea_hr31(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$stfea_hr31((String) null);
                }
            } else if (nextName.equals("byfea_hr31")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$byfea_hr31(JsonUtils.stringToBytes(jsonReader.nextString()));
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$byfea_hr31((byte[]) null);
                }
            } else if (!nextName.equals("arcexresult")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$arcexresult(jsonReader.nextInt());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'arcexresult' to null.");
            }
        }
        jsonReader.endObject();
        if (z) {
            return (EmployeeResponse) realm.copyToRealm(employeeResponse, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_employee_EmployeeResponseRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeResponse.class), false, Collections.emptyList());
        com_ciot_realm_db_employee_EmployeeResponseRealmProxy com_ciot_realm_db_employee_employeeresponserealmproxy = new com_ciot_realm_db_employee_EmployeeResponseRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_employee_employeeresponserealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.employee.EmployeeResponse copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxy.EmployeeResponseColumnInfo r9, com.ciot.realm.db.employee.EmployeeResponse r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.employee.EmployeeResponse r1 = (com.ciot.realm.db.employee.EmployeeResponse) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.employee.EmployeeResponse> r2 = com.ciot.realm.db.employee.EmployeeResponse.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxyInterface) r5
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
            io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxy r1 = new io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.employee.EmployeeResponse r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.employee.EmployeeResponse r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxy$EmployeeResponseColumnInfo, com.ciot.realm.db.employee.EmployeeResponse, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.employee.EmployeeResponse");
    }

    public static EmployeeResponse copy(Realm realm, EmployeeResponseColumnInfo employeeResponseColumnInfo, EmployeeResponse employeeResponse, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(employeeResponse);
        if (realmObjectProxy != null) {
            return (EmployeeResponse) realmObjectProxy;
        }
        com_ciot_realm_db_employee_EmployeeResponseRealmProxyInterface com_ciot_realm_db_employee_employeeresponserealmproxyinterface = employeeResponse;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(EmployeeResponse.class), set);
        osObjectBuilder.addString(employeeResponseColumnInfo.staffnoColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$staffno());
        osObjectBuilder.addString(employeeResponseColumnInfo.nameColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$name());
        osObjectBuilder.addString(employeeResponseColumnInfo.idcardColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$idcard());
        osObjectBuilder.addString(employeeResponseColumnInfo.faceColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$face());
        osObjectBuilder.addString(employeeResponseColumnInfo.iccardColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$iccard());
        osObjectBuilder.addString(employeeResponseColumnInfo.phoneColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$phone());
        osObjectBuilder.addString(employeeResponseColumnInfo.emailColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$email());
        osObjectBuilder.addInteger(employeeResponseColumnInfo.typeColKey, Integer.valueOf(com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$type()));
        osObjectBuilder.addString(employeeResponseColumnInfo.sexColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$sex());
        osObjectBuilder.addString(employeeResponseColumnInfo.departmentColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$department());
        osObjectBuilder.addString(employeeResponseColumnInfo.companyColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$company());
        osObjectBuilder.addString(employeeResponseColumnInfo.jobColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$job());
        osObjectBuilder.addString(employeeResponseColumnInfo.fingerprintColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$fingerprint());
        osObjectBuilder.addString(employeeResponseColumnInfo.wechatColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$wechat());
        osObjectBuilder.addString(employeeResponseColumnInfo.descriptionColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$description());
        osObjectBuilder.addString(employeeResponseColumnInfo.birthdayColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$birthday());
        osObjectBuilder.addString(employeeResponseColumnInfo.arcCodeColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$arcCode());
        osObjectBuilder.addInteger(employeeResponseColumnInfo.createtimeColKey, Long.valueOf(com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$createtime()));
        osObjectBuilder.addString(employeeResponseColumnInfo.idColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$id());
        osObjectBuilder.addString(employeeResponseColumnInfo.birthPromptColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$birthPrompt());
        osObjectBuilder.addString(employeeResponseColumnInfo.vipPromptColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$vipPrompt());
        osObjectBuilder.addString(employeeResponseColumnInfo.promptColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$prompt());
        osObjectBuilder.addInteger(employeeResponseColumnInfo.statusColKey, Integer.valueOf(com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$status()));
        osObjectBuilder.addString(employeeResponseColumnInfo.pathColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$path());
        osObjectBuilder.addInteger(employeeResponseColumnInfo.uuidColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$uuid());
        osObjectBuilder.addBoolean(employeeResponseColumnInfo.isHasfea_stColKey, Boolean.valueOf(com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$isHasfea_st()));
        osObjectBuilder.addString(employeeResponseColumnInfo.stfea_stColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$stfea_st());
        osObjectBuilder.addBoolean(employeeResponseColumnInfo.isHasfea_hrColKey, Boolean.valueOf(com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$isHasfea_hr()));
        osObjectBuilder.addString(employeeResponseColumnInfo.stfea_hr31ColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$stfea_hr31());
        osObjectBuilder.addByteArray(employeeResponseColumnInfo.byfea_hr31ColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$byfea_hr31());
        osObjectBuilder.addInteger(employeeResponseColumnInfo.arcexresultColKey, Integer.valueOf(com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$arcexresult()));
        com_ciot_realm_db_employee_EmployeeResponseRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(employeeResponse, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, EmployeeResponse employeeResponse, Map<RealmModel, Long> map) {
        long j;
        EmployeeResponse employeeResponse2 = employeeResponse;
        if ((employeeResponse2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeResponse)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeResponse2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(EmployeeResponse.class);
        long nativePtr = table.getNativePtr();
        EmployeeResponseColumnInfo employeeResponseColumnInfo = (EmployeeResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeResponse.class);
        long j2 = employeeResponseColumnInfo.idColKey;
        com_ciot_realm_db_employee_EmployeeResponseRealmProxyInterface com_ciot_realm_db_employee_employeeresponserealmproxyinterface = employeeResponse2;
        String realmGet$id = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$id();
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
        map.put(employeeResponse2, Long.valueOf(j3));
        String realmGet$staffno = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$staffno();
        if (realmGet$staffno != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.staffnoColKey, j3, realmGet$staffno, false);
        }
        String realmGet$name = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.nameColKey, j3, realmGet$name, false);
        }
        String realmGet$idcard = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$idcard();
        if (realmGet$idcard != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.idcardColKey, j3, realmGet$idcard, false);
        }
        String realmGet$face = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$face();
        if (realmGet$face != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.faceColKey, j3, realmGet$face, false);
        }
        String realmGet$iccard = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$iccard();
        if (realmGet$iccard != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.iccardColKey, j3, realmGet$iccard, false);
        }
        String realmGet$phone = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$phone();
        if (realmGet$phone != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.phoneColKey, j3, realmGet$phone, false);
        }
        String realmGet$email = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.emailColKey, j3, realmGet$email, false);
        }
        Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.typeColKey, j3, (long) com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$type(), false);
        String realmGet$sex = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$sex();
        if (realmGet$sex != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.sexColKey, j3, realmGet$sex, false);
        }
        String realmGet$department = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$department();
        if (realmGet$department != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.departmentColKey, j3, realmGet$department, false);
        }
        String realmGet$company = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$company();
        if (realmGet$company != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.companyColKey, j3, realmGet$company, false);
        }
        String realmGet$job = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$job();
        if (realmGet$job != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.jobColKey, j3, realmGet$job, false);
        }
        String realmGet$fingerprint = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$fingerprint();
        if (realmGet$fingerprint != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.fingerprintColKey, j3, realmGet$fingerprint, false);
        }
        String realmGet$wechat = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$wechat();
        if (realmGet$wechat != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.wechatColKey, j3, realmGet$wechat, false);
        }
        String realmGet$description = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.descriptionColKey, j3, realmGet$description, false);
        }
        String realmGet$birthday = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$birthday();
        if (realmGet$birthday != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.birthdayColKey, j3, realmGet$birthday, false);
        }
        String realmGet$arcCode = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$arcCode();
        if (realmGet$arcCode != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.arcCodeColKey, j3, realmGet$arcCode, false);
        }
        Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.createtimeColKey, j3, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$createtime(), false);
        String realmGet$birthPrompt = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$birthPrompt();
        if (realmGet$birthPrompt != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.birthPromptColKey, j3, realmGet$birthPrompt, false);
        }
        String realmGet$vipPrompt = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$vipPrompt();
        if (realmGet$vipPrompt != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.vipPromptColKey, j3, realmGet$vipPrompt, false);
        }
        String realmGet$prompt = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$prompt();
        if (realmGet$prompt != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.promptColKey, j3, realmGet$prompt, false);
        }
        Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.statusColKey, j3, (long) com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$status(), false);
        String realmGet$path = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$path();
        if (realmGet$path != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.pathColKey, j3, realmGet$path, false);
        }
        Integer realmGet$uuid = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$uuid();
        if (realmGet$uuid != null) {
            Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.uuidColKey, j3, realmGet$uuid.longValue(), false);
        }
        Table.nativeSetBoolean(nativePtr, employeeResponseColumnInfo.isHasfea_stColKey, j3, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$isHasfea_st(), false);
        String realmGet$stfea_st = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$stfea_st();
        if (realmGet$stfea_st != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.stfea_stColKey, j3, realmGet$stfea_st, false);
        }
        Table.nativeSetBoolean(nativePtr, employeeResponseColumnInfo.isHasfea_hrColKey, j3, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$isHasfea_hr(), false);
        String realmGet$stfea_hr31 = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$stfea_hr31();
        if (realmGet$stfea_hr31 != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.stfea_hr31ColKey, j3, realmGet$stfea_hr31, false);
        }
        byte[] realmGet$byfea_hr31 = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$byfea_hr31();
        if (realmGet$byfea_hr31 != null) {
            Table.nativeSetByteArray(nativePtr, employeeResponseColumnInfo.byfea_hr31ColKey, j3, realmGet$byfea_hr31, false);
        }
        Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.arcexresultColKey, j3, (long) com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$arcexresult(), false);
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(EmployeeResponse.class);
        long nativePtr = table.getNativePtr();
        EmployeeResponseColumnInfo employeeResponseColumnInfo = (EmployeeResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeResponse.class);
        long j4 = employeeResponseColumnInfo.idColKey;
        while (it.hasNext()) {
            EmployeeResponse employeeResponse = (EmployeeResponse) it.next();
            if (!map2.containsKey(employeeResponse)) {
                if ((employeeResponse instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeResponse)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeResponse;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(employeeResponse, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_employee_EmployeeResponseRealmProxyInterface com_ciot_realm_db_employee_employeeresponserealmproxyinterface = employeeResponse;
                String realmGet$id = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$id();
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
                map2.put(employeeResponse, Long.valueOf(j2));
                String realmGet$staffno = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$staffno();
                if (realmGet$staffno != null) {
                    j3 = j4;
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.staffnoColKey, j2, realmGet$staffno, false);
                } else {
                    j3 = j4;
                }
                String realmGet$name = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.nameColKey, j2, realmGet$name, false);
                }
                String realmGet$idcard = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$idcard();
                if (realmGet$idcard != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.idcardColKey, j2, realmGet$idcard, false);
                }
                String realmGet$face = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$face();
                if (realmGet$face != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.faceColKey, j2, realmGet$face, false);
                }
                String realmGet$iccard = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$iccard();
                if (realmGet$iccard != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.iccardColKey, j2, realmGet$iccard, false);
                }
                String realmGet$phone = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$phone();
                if (realmGet$phone != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.phoneColKey, j2, realmGet$phone, false);
                }
                String realmGet$email = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$email();
                if (realmGet$email != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.emailColKey, j2, realmGet$email, false);
                }
                Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.typeColKey, j2, (long) com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$type(), false);
                String realmGet$sex = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$sex();
                if (realmGet$sex != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.sexColKey, j2, realmGet$sex, false);
                }
                String realmGet$department = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$department();
                if (realmGet$department != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.departmentColKey, j2, realmGet$department, false);
                }
                String realmGet$company = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$company();
                if (realmGet$company != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.companyColKey, j2, realmGet$company, false);
                }
                String realmGet$job = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$job();
                if (realmGet$job != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.jobColKey, j2, realmGet$job, false);
                }
                String realmGet$fingerprint = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$fingerprint();
                if (realmGet$fingerprint != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.fingerprintColKey, j2, realmGet$fingerprint, false);
                }
                String realmGet$wechat = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$wechat();
                if (realmGet$wechat != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.wechatColKey, j2, realmGet$wechat, false);
                }
                String realmGet$description = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.descriptionColKey, j2, realmGet$description, false);
                }
                String realmGet$birthday = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$birthday();
                if (realmGet$birthday != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.birthdayColKey, j2, realmGet$birthday, false);
                }
                String realmGet$arcCode = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$arcCode();
                if (realmGet$arcCode != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.arcCodeColKey, j2, realmGet$arcCode, false);
                }
                Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.createtimeColKey, j2, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$createtime(), false);
                String realmGet$birthPrompt = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$birthPrompt();
                if (realmGet$birthPrompt != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.birthPromptColKey, j2, realmGet$birthPrompt, false);
                }
                String realmGet$vipPrompt = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$vipPrompt();
                if (realmGet$vipPrompt != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.vipPromptColKey, j2, realmGet$vipPrompt, false);
                }
                String realmGet$prompt = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$prompt();
                if (realmGet$prompt != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.promptColKey, j2, realmGet$prompt, false);
                }
                Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.statusColKey, j2, (long) com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$status(), false);
                String realmGet$path = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$path();
                if (realmGet$path != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.pathColKey, j2, realmGet$path, false);
                }
                Integer realmGet$uuid = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$uuid();
                if (realmGet$uuid != null) {
                    Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.uuidColKey, j2, realmGet$uuid.longValue(), false);
                }
                Table.nativeSetBoolean(nativePtr, employeeResponseColumnInfo.isHasfea_stColKey, j2, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$isHasfea_st(), false);
                String realmGet$stfea_st = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$stfea_st();
                if (realmGet$stfea_st != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.stfea_stColKey, j2, realmGet$stfea_st, false);
                }
                Table.nativeSetBoolean(nativePtr, employeeResponseColumnInfo.isHasfea_hrColKey, j2, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$isHasfea_hr(), false);
                String realmGet$stfea_hr31 = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$stfea_hr31();
                if (realmGet$stfea_hr31 != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.stfea_hr31ColKey, j2, realmGet$stfea_hr31, false);
                }
                byte[] realmGet$byfea_hr31 = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$byfea_hr31();
                if (realmGet$byfea_hr31 != null) {
                    Table.nativeSetByteArray(nativePtr, employeeResponseColumnInfo.byfea_hr31ColKey, j2, realmGet$byfea_hr31, false);
                }
                Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.arcexresultColKey, j2, (long) com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$arcexresult(), false);
                j4 = j3;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, EmployeeResponse employeeResponse, Map<RealmModel, Long> map) {
        long j;
        EmployeeResponse employeeResponse2 = employeeResponse;
        if ((employeeResponse2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeResponse)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeResponse2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(EmployeeResponse.class);
        long nativePtr = table.getNativePtr();
        EmployeeResponseColumnInfo employeeResponseColumnInfo = (EmployeeResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeResponse.class);
        long j2 = employeeResponseColumnInfo.idColKey;
        com_ciot_realm_db_employee_EmployeeResponseRealmProxyInterface com_ciot_realm_db_employee_employeeresponserealmproxyinterface = employeeResponse2;
        String realmGet$id = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$id);
        }
        long j3 = j;
        map.put(employeeResponse2, Long.valueOf(j3));
        String realmGet$staffno = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$staffno();
        if (realmGet$staffno != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.staffnoColKey, j3, realmGet$staffno, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.staffnoColKey, j3, false);
        }
        String realmGet$name = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.nameColKey, j3, realmGet$name, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.nameColKey, j3, false);
        }
        String realmGet$idcard = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$idcard();
        if (realmGet$idcard != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.idcardColKey, j3, realmGet$idcard, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.idcardColKey, j3, false);
        }
        String realmGet$face = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$face();
        if (realmGet$face != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.faceColKey, j3, realmGet$face, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.faceColKey, j3, false);
        }
        String realmGet$iccard = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$iccard();
        if (realmGet$iccard != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.iccardColKey, j3, realmGet$iccard, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.iccardColKey, j3, false);
        }
        String realmGet$phone = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$phone();
        if (realmGet$phone != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.phoneColKey, j3, realmGet$phone, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.phoneColKey, j3, false);
        }
        String realmGet$email = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.emailColKey, j3, realmGet$email, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.emailColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.typeColKey, j3, (long) com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$type(), false);
        String realmGet$sex = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$sex();
        if (realmGet$sex != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.sexColKey, j3, realmGet$sex, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.sexColKey, j3, false);
        }
        String realmGet$department = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$department();
        if (realmGet$department != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.departmentColKey, j3, realmGet$department, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.departmentColKey, j3, false);
        }
        String realmGet$company = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$company();
        if (realmGet$company != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.companyColKey, j3, realmGet$company, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.companyColKey, j3, false);
        }
        String realmGet$job = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$job();
        if (realmGet$job != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.jobColKey, j3, realmGet$job, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.jobColKey, j3, false);
        }
        String realmGet$fingerprint = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$fingerprint();
        if (realmGet$fingerprint != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.fingerprintColKey, j3, realmGet$fingerprint, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.fingerprintColKey, j3, false);
        }
        String realmGet$wechat = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$wechat();
        if (realmGet$wechat != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.wechatColKey, j3, realmGet$wechat, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.wechatColKey, j3, false);
        }
        String realmGet$description = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.descriptionColKey, j3, realmGet$description, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.descriptionColKey, j3, false);
        }
        String realmGet$birthday = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$birthday();
        if (realmGet$birthday != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.birthdayColKey, j3, realmGet$birthday, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.birthdayColKey, j3, false);
        }
        String realmGet$arcCode = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$arcCode();
        if (realmGet$arcCode != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.arcCodeColKey, j3, realmGet$arcCode, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.arcCodeColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.createtimeColKey, j3, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$createtime(), false);
        String realmGet$birthPrompt = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$birthPrompt();
        if (realmGet$birthPrompt != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.birthPromptColKey, j3, realmGet$birthPrompt, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.birthPromptColKey, j3, false);
        }
        String realmGet$vipPrompt = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$vipPrompt();
        if (realmGet$vipPrompt != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.vipPromptColKey, j3, realmGet$vipPrompt, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.vipPromptColKey, j3, false);
        }
        String realmGet$prompt = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$prompt();
        if (realmGet$prompt != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.promptColKey, j3, realmGet$prompt, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.promptColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.statusColKey, j3, (long) com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$status(), false);
        String realmGet$path = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$path();
        if (realmGet$path != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.pathColKey, j3, realmGet$path, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.pathColKey, j3, false);
        }
        Integer realmGet$uuid = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$uuid();
        if (realmGet$uuid != null) {
            Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.uuidColKey, j3, realmGet$uuid.longValue(), false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.uuidColKey, j3, false);
        }
        Table.nativeSetBoolean(nativePtr, employeeResponseColumnInfo.isHasfea_stColKey, j3, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$isHasfea_st(), false);
        String realmGet$stfea_st = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$stfea_st();
        if (realmGet$stfea_st != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.stfea_stColKey, j3, realmGet$stfea_st, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.stfea_stColKey, j3, false);
        }
        Table.nativeSetBoolean(nativePtr, employeeResponseColumnInfo.isHasfea_hrColKey, j3, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$isHasfea_hr(), false);
        String realmGet$stfea_hr31 = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$stfea_hr31();
        if (realmGet$stfea_hr31 != null) {
            Table.nativeSetString(nativePtr, employeeResponseColumnInfo.stfea_hr31ColKey, j3, realmGet$stfea_hr31, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.stfea_hr31ColKey, j3, false);
        }
        byte[] realmGet$byfea_hr31 = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$byfea_hr31();
        if (realmGet$byfea_hr31 != null) {
            Table.nativeSetByteArray(nativePtr, employeeResponseColumnInfo.byfea_hr31ColKey, j3, realmGet$byfea_hr31, false);
        } else {
            Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.byfea_hr31ColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.arcexresultColKey, j3, (long) com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$arcexresult(), false);
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(EmployeeResponse.class);
        long nativePtr = table.getNativePtr();
        EmployeeResponseColumnInfo employeeResponseColumnInfo = (EmployeeResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeResponse.class);
        long j3 = employeeResponseColumnInfo.idColKey;
        while (it.hasNext()) {
            EmployeeResponse employeeResponse = (EmployeeResponse) it.next();
            if (!map2.containsKey(employeeResponse)) {
                if ((employeeResponse instanceof RealmObjectProxy) && !RealmObject.isFrozen(employeeResponse)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) employeeResponse;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(employeeResponse, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_employee_EmployeeResponseRealmProxyInterface com_ciot_realm_db_employee_employeeresponserealmproxyinterface = employeeResponse;
                String realmGet$id = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j3);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j3, realmGet$id);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j3, realmGet$id) : j;
                map2.put(employeeResponse, Long.valueOf(createRowWithPrimaryKey));
                String realmGet$staffno = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$staffno();
                if (realmGet$staffno != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.staffnoColKey, createRowWithPrimaryKey, realmGet$staffno, false);
                } else {
                    j2 = j3;
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.staffnoColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$name = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.nameColKey, createRowWithPrimaryKey, realmGet$name, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.nameColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$idcard = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$idcard();
                if (realmGet$idcard != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.idcardColKey, createRowWithPrimaryKey, realmGet$idcard, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.idcardColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$face = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$face();
                if (realmGet$face != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.faceColKey, createRowWithPrimaryKey, realmGet$face, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.faceColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$iccard = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$iccard();
                if (realmGet$iccard != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.iccardColKey, createRowWithPrimaryKey, realmGet$iccard, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.iccardColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$phone = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$phone();
                if (realmGet$phone != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.phoneColKey, createRowWithPrimaryKey, realmGet$phone, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.phoneColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$email = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$email();
                if (realmGet$email != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.emailColKey, createRowWithPrimaryKey, realmGet$email, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.emailColKey, createRowWithPrimaryKey, false);
                }
                Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.typeColKey, createRowWithPrimaryKey, (long) com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$type(), false);
                String realmGet$sex = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$sex();
                if (realmGet$sex != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.sexColKey, createRowWithPrimaryKey, realmGet$sex, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.sexColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$department = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$department();
                if (realmGet$department != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.departmentColKey, createRowWithPrimaryKey, realmGet$department, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.departmentColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$company = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$company();
                if (realmGet$company != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.companyColKey, createRowWithPrimaryKey, realmGet$company, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.companyColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$job = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$job();
                if (realmGet$job != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.jobColKey, createRowWithPrimaryKey, realmGet$job, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.jobColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$fingerprint = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$fingerprint();
                if (realmGet$fingerprint != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.fingerprintColKey, createRowWithPrimaryKey, realmGet$fingerprint, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.fingerprintColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$wechat = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$wechat();
                if (realmGet$wechat != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.wechatColKey, createRowWithPrimaryKey, realmGet$wechat, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.wechatColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$description = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.descriptionColKey, createRowWithPrimaryKey, realmGet$description, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.descriptionColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$birthday = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$birthday();
                if (realmGet$birthday != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.birthdayColKey, createRowWithPrimaryKey, realmGet$birthday, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.birthdayColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$arcCode = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$arcCode();
                if (realmGet$arcCode != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.arcCodeColKey, createRowWithPrimaryKey, realmGet$arcCode, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.arcCodeColKey, createRowWithPrimaryKey, false);
                }
                Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.createtimeColKey, createRowWithPrimaryKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$createtime(), false);
                String realmGet$birthPrompt = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$birthPrompt();
                if (realmGet$birthPrompt != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.birthPromptColKey, createRowWithPrimaryKey, realmGet$birthPrompt, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.birthPromptColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$vipPrompt = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$vipPrompt();
                if (realmGet$vipPrompt != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.vipPromptColKey, createRowWithPrimaryKey, realmGet$vipPrompt, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.vipPromptColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$prompt = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$prompt();
                if (realmGet$prompt != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.promptColKey, createRowWithPrimaryKey, realmGet$prompt, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.promptColKey, createRowWithPrimaryKey, false);
                }
                Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.statusColKey, createRowWithPrimaryKey, (long) com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$status(), false);
                String realmGet$path = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$path();
                if (realmGet$path != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.pathColKey, createRowWithPrimaryKey, realmGet$path, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.pathColKey, createRowWithPrimaryKey, false);
                }
                Integer realmGet$uuid = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$uuid();
                if (realmGet$uuid != null) {
                    Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.uuidColKey, createRowWithPrimaryKey, realmGet$uuid.longValue(), false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.uuidColKey, createRowWithPrimaryKey, false);
                }
                Table.nativeSetBoolean(nativePtr, employeeResponseColumnInfo.isHasfea_stColKey, createRowWithPrimaryKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$isHasfea_st(), false);
                String realmGet$stfea_st = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$stfea_st();
                if (realmGet$stfea_st != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.stfea_stColKey, createRowWithPrimaryKey, realmGet$stfea_st, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.stfea_stColKey, createRowWithPrimaryKey, false);
                }
                Table.nativeSetBoolean(nativePtr, employeeResponseColumnInfo.isHasfea_hrColKey, createRowWithPrimaryKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$isHasfea_hr(), false);
                String realmGet$stfea_hr31 = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$stfea_hr31();
                if (realmGet$stfea_hr31 != null) {
                    Table.nativeSetString(nativePtr, employeeResponseColumnInfo.stfea_hr31ColKey, createRowWithPrimaryKey, realmGet$stfea_hr31, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.stfea_hr31ColKey, createRowWithPrimaryKey, false);
                }
                byte[] realmGet$byfea_hr31 = com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$byfea_hr31();
                if (realmGet$byfea_hr31 != null) {
                    Table.nativeSetByteArray(nativePtr, employeeResponseColumnInfo.byfea_hr31ColKey, createRowWithPrimaryKey, realmGet$byfea_hr31, false);
                } else {
                    Table.nativeSetNull(nativePtr, employeeResponseColumnInfo.byfea_hr31ColKey, createRowWithPrimaryKey, false);
                }
                Table.nativeSetLong(nativePtr, employeeResponseColumnInfo.arcexresultColKey, createRowWithPrimaryKey, (long) com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmGet$arcexresult(), false);
                j3 = j2;
            }
        }
    }

    public static EmployeeResponse createDetachedCopy(EmployeeResponse employeeResponse, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        EmployeeResponse employeeResponse2;
        if (i > i2 || employeeResponse == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(employeeResponse);
        if (cacheData == null) {
            employeeResponse2 = new EmployeeResponse();
            map.put(employeeResponse, new RealmObjectProxy.CacheData(i, employeeResponse2));
        } else if (i >= cacheData.minDepth) {
            return (EmployeeResponse) cacheData.object;
        } else {
            cacheData.minDepth = i;
            employeeResponse2 = (EmployeeResponse) cacheData.object;
        }
        com_ciot_realm_db_employee_EmployeeResponseRealmProxyInterface com_ciot_realm_db_employee_employeeresponserealmproxyinterface = employeeResponse2;
        com_ciot_realm_db_employee_EmployeeResponseRealmProxyInterface com_ciot_realm_db_employee_employeeresponserealmproxyinterface2 = employeeResponse;
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$staffno(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$staffno());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$name(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$name());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$idcard(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$idcard());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$face(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$face());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$iccard(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$iccard());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$phone(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$phone());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$email(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$email());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$type(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$type());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$sex(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$sex());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$department(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$department());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$company(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$company());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$job(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$job());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$fingerprint(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$fingerprint());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$wechat(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$wechat());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$description(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$description());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$birthday(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$birthday());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$arcCode(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$arcCode());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$createtime(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$createtime());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$id(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$birthPrompt(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$birthPrompt());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$vipPrompt(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$vipPrompt());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$prompt(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$prompt());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$status(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$status());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$path(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$path());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$uuid(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$uuid());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$isHasfea_st(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$isHasfea_st());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$stfea_st(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$stfea_st());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$isHasfea_hr(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$isHasfea_hr());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$stfea_hr31(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$stfea_hr31());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$byfea_hr31(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$byfea_hr31());
        com_ciot_realm_db_employee_employeeresponserealmproxyinterface.realmSet$arcexresult(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$arcexresult());
        return employeeResponse2;
    }

    static EmployeeResponse update(Realm realm, EmployeeResponseColumnInfo employeeResponseColumnInfo, EmployeeResponse employeeResponse, EmployeeResponse employeeResponse2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_employee_EmployeeResponseRealmProxyInterface com_ciot_realm_db_employee_employeeresponserealmproxyinterface = employeeResponse;
        com_ciot_realm_db_employee_EmployeeResponseRealmProxyInterface com_ciot_realm_db_employee_employeeresponserealmproxyinterface2 = employeeResponse2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(EmployeeResponse.class), set);
        osObjectBuilder.addString(employeeResponseColumnInfo.staffnoColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$staffno());
        osObjectBuilder.addString(employeeResponseColumnInfo.nameColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$name());
        osObjectBuilder.addString(employeeResponseColumnInfo.idcardColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$idcard());
        osObjectBuilder.addString(employeeResponseColumnInfo.faceColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$face());
        osObjectBuilder.addString(employeeResponseColumnInfo.iccardColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$iccard());
        osObjectBuilder.addString(employeeResponseColumnInfo.phoneColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$phone());
        osObjectBuilder.addString(employeeResponseColumnInfo.emailColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$email());
        osObjectBuilder.addInteger(employeeResponseColumnInfo.typeColKey, Integer.valueOf(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$type()));
        osObjectBuilder.addString(employeeResponseColumnInfo.sexColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$sex());
        osObjectBuilder.addString(employeeResponseColumnInfo.departmentColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$department());
        osObjectBuilder.addString(employeeResponseColumnInfo.companyColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$company());
        osObjectBuilder.addString(employeeResponseColumnInfo.jobColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$job());
        osObjectBuilder.addString(employeeResponseColumnInfo.fingerprintColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$fingerprint());
        osObjectBuilder.addString(employeeResponseColumnInfo.wechatColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$wechat());
        osObjectBuilder.addString(employeeResponseColumnInfo.descriptionColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$description());
        osObjectBuilder.addString(employeeResponseColumnInfo.birthdayColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$birthday());
        osObjectBuilder.addString(employeeResponseColumnInfo.arcCodeColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$arcCode());
        osObjectBuilder.addInteger(employeeResponseColumnInfo.createtimeColKey, Long.valueOf(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$createtime()));
        osObjectBuilder.addString(employeeResponseColumnInfo.idColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$id());
        osObjectBuilder.addString(employeeResponseColumnInfo.birthPromptColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$birthPrompt());
        osObjectBuilder.addString(employeeResponseColumnInfo.vipPromptColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$vipPrompt());
        osObjectBuilder.addString(employeeResponseColumnInfo.promptColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$prompt());
        osObjectBuilder.addInteger(employeeResponseColumnInfo.statusColKey, Integer.valueOf(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$status()));
        osObjectBuilder.addString(employeeResponseColumnInfo.pathColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$path());
        osObjectBuilder.addInteger(employeeResponseColumnInfo.uuidColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$uuid());
        osObjectBuilder.addBoolean(employeeResponseColumnInfo.isHasfea_stColKey, Boolean.valueOf(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$isHasfea_st()));
        osObjectBuilder.addString(employeeResponseColumnInfo.stfea_stColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$stfea_st());
        osObjectBuilder.addBoolean(employeeResponseColumnInfo.isHasfea_hrColKey, Boolean.valueOf(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$isHasfea_hr()));
        osObjectBuilder.addString(employeeResponseColumnInfo.stfea_hr31ColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$stfea_hr31());
        osObjectBuilder.addByteArray(employeeResponseColumnInfo.byfea_hr31ColKey, com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$byfea_hr31());
        osObjectBuilder.addInteger(employeeResponseColumnInfo.arcexresultColKey, Integer.valueOf(com_ciot_realm_db_employee_employeeresponserealmproxyinterface2.realmGet$arcexresult()));
        osObjectBuilder.updateExistingObject();
        return employeeResponse;
    }

    public ProxyState<?> realmGet$proxyState() {
        return this.proxyState;
    }
}
