package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import androidx.core.app.NotificationCompat;
import com.ciot.realm.db.EmployeeBean;
import com.ciot.realm.db.LockMode;
import com.ciot.realm.db.Person;
import com.ciot.realm.db.RegisterWithAppointmentResponse;
import com.ciot.realm.db.common.AckBean;
import com.ciot.realm.db.common.CompanyResponse;
import com.ciot.realm.db.common.Settings;
import com.ciot.realm.db.common.ValidateBean;
import com.ciot.realm.db.common.VisitorBean;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy;
import io.realm.com_ciot_realm_db_LockModeRealmProxy;
import io.realm.com_ciot_realm_db_common_AckBeanRealmProxy;
import io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxy;
import io.realm.com_ciot_realm_db_common_SettingsRealmProxy;
import io.realm.com_ciot_realm_db_common_ValidateBeanRealmProxy;
import io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxy;
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
import xcrash.TombstoneParser;

public class com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy extends RegisterWithAppointmentResponse implements RealmObjectProxy, com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private RegisterWithAppointmentResponseColumnInfo columnInfo;
    private ProxyState<RegisterWithAppointmentResponse> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "RegisterWithAppointmentResponse";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class RegisterWithAppointmentResponseColumnInfo extends ColumnInfo {
        long ackColKey;
        long auditnotesColKey;
        long beginColKey;
        long codeColKey;
        long companyColKey;
        long createtimeColKey;
        long descriptionColKey;
        long employeeColKey;
        long endColKey;
        long idColKey;
        long lockmodeColKey;
        long proofNumColKey;
        long settingsColKey;
        long smsColKey;
        long statusColKey;
        long typeColKey;
        long validateColKey;
        long visitorColKey;

        RegisterWithAppointmentResponseColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(18);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.visitorColKey = addColumnDetails(Person.VISITOR, Person.VISITOR, objectSchemaInfo);
            this.beginColKey = addColumnDetails("begin", "begin", objectSchemaInfo);
            this.proofNumColKey = addColumnDetails("proofNum", "proofNum", objectSchemaInfo);
            this.codeColKey = addColumnDetails(TombstoneParser.keyCode, TombstoneParser.keyCode, objectSchemaInfo);
            this.settingsColKey = addColumnDetails("settings", "settings", objectSchemaInfo);
            this.validateColKey = addColumnDetails("validate", "validate", objectSchemaInfo);
            this.endColKey = addColumnDetails("end", "end", objectSchemaInfo);
            this.smsColKey = addColumnDetails("sms", "sms", objectSchemaInfo);
            this.typeColKey = addColumnDetails("type", "type", objectSchemaInfo);
            this.employeeColKey = addColumnDetails(Person.EMPLOYEE, Person.EMPLOYEE, objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
            this.statusColKey = addColumnDetails(NotificationCompat.CATEGORY_STATUS, NotificationCompat.CATEGORY_STATUS, objectSchemaInfo);
            this.createtimeColKey = addColumnDetails("createtime", "createtime", objectSchemaInfo);
            this.companyColKey = addColumnDetails("company", "company", objectSchemaInfo);
            this.ackColKey = addColumnDetails("ack", "ack", objectSchemaInfo);
            this.auditnotesColKey = addColumnDetails("auditnotes", "auditnotes", objectSchemaInfo);
            this.lockmodeColKey = addColumnDetails("lockmode", "lockmode", objectSchemaInfo);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
        }

        RegisterWithAppointmentResponseColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new RegisterWithAppointmentResponseColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            RegisterWithAppointmentResponseColumnInfo registerWithAppointmentResponseColumnInfo = (RegisterWithAppointmentResponseColumnInfo) columnInfo;
            RegisterWithAppointmentResponseColumnInfo registerWithAppointmentResponseColumnInfo2 = (RegisterWithAppointmentResponseColumnInfo) columnInfo2;
            registerWithAppointmentResponseColumnInfo2.visitorColKey = registerWithAppointmentResponseColumnInfo.visitorColKey;
            registerWithAppointmentResponseColumnInfo2.beginColKey = registerWithAppointmentResponseColumnInfo.beginColKey;
            registerWithAppointmentResponseColumnInfo2.proofNumColKey = registerWithAppointmentResponseColumnInfo.proofNumColKey;
            registerWithAppointmentResponseColumnInfo2.codeColKey = registerWithAppointmentResponseColumnInfo.codeColKey;
            registerWithAppointmentResponseColumnInfo2.settingsColKey = registerWithAppointmentResponseColumnInfo.settingsColKey;
            registerWithAppointmentResponseColumnInfo2.validateColKey = registerWithAppointmentResponseColumnInfo.validateColKey;
            registerWithAppointmentResponseColumnInfo2.endColKey = registerWithAppointmentResponseColumnInfo.endColKey;
            registerWithAppointmentResponseColumnInfo2.smsColKey = registerWithAppointmentResponseColumnInfo.smsColKey;
            registerWithAppointmentResponseColumnInfo2.typeColKey = registerWithAppointmentResponseColumnInfo.typeColKey;
            registerWithAppointmentResponseColumnInfo2.employeeColKey = registerWithAppointmentResponseColumnInfo.employeeColKey;
            registerWithAppointmentResponseColumnInfo2.descriptionColKey = registerWithAppointmentResponseColumnInfo.descriptionColKey;
            registerWithAppointmentResponseColumnInfo2.statusColKey = registerWithAppointmentResponseColumnInfo.statusColKey;
            registerWithAppointmentResponseColumnInfo2.createtimeColKey = registerWithAppointmentResponseColumnInfo.createtimeColKey;
            registerWithAppointmentResponseColumnInfo2.companyColKey = registerWithAppointmentResponseColumnInfo.companyColKey;
            registerWithAppointmentResponseColumnInfo2.ackColKey = registerWithAppointmentResponseColumnInfo.ackColKey;
            registerWithAppointmentResponseColumnInfo2.auditnotesColKey = registerWithAppointmentResponseColumnInfo.auditnotesColKey;
            registerWithAppointmentResponseColumnInfo2.lockmodeColKey = registerWithAppointmentResponseColumnInfo.lockmodeColKey;
            registerWithAppointmentResponseColumnInfo2.idColKey = registerWithAppointmentResponseColumnInfo.idColKey;
        }
    }

    com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (RegisterWithAppointmentResponseColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<RegisterWithAppointmentResponse> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public VisitorBean realmGet$visitor() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.visitorColKey)) {
            return null;
        }
        return (VisitorBean) this.proxyState.getRealm$realm().get(VisitorBean.class, this.proxyState.getRow$realm().getLink(this.columnInfo.visitorColKey), false, Collections.emptyList());
    }

    public void realmSet$visitor(VisitorBean visitorBean) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (visitorBean == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.visitorColKey);
                return;
            }
            this.proxyState.checkValidObject(visitorBean);
            this.proxyState.getRow$realm().setLink(this.columnInfo.visitorColKey, ((RealmObjectProxy) visitorBean).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains(Person.VISITOR)) {
            if (visitorBean != null && !RealmObject.isManaged(visitorBean)) {
                visitorBean = (VisitorBean) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(visitorBean, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (visitorBean == null) {
                row$realm.nullifyLink(this.columnInfo.visitorColKey);
                return;
            }
            this.proxyState.checkValidObject(visitorBean);
            row$realm.getTable().setLink(this.columnInfo.visitorColKey, row$realm.getObjectKey(), ((RealmObjectProxy) visitorBean).realmGet$proxyState().getRow$realm().getObjectKey(), true);
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

    public int realmGet$proofNum() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.proofNumColKey);
    }

    public void realmSet$proofNum(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.proofNumColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.proofNumColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public String realmGet$code() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.codeColKey);
    }

    public void realmSet$code(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.codeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.codeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.codeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.codeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public Settings realmGet$settings() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.settingsColKey)) {
            return null;
        }
        return (Settings) this.proxyState.getRealm$realm().get(Settings.class, this.proxyState.getRow$realm().getLink(this.columnInfo.settingsColKey), false, Collections.emptyList());
    }

    public void realmSet$settings(Settings settings) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (settings == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.settingsColKey);
                return;
            }
            this.proxyState.checkValidObject(settings);
            this.proxyState.getRow$realm().setLink(this.columnInfo.settingsColKey, ((RealmObjectProxy) settings).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("settings")) {
            if (settings != null && !RealmObject.isManaged(settings)) {
                settings = (Settings) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(settings, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (settings == null) {
                row$realm.nullifyLink(this.columnInfo.settingsColKey);
                return;
            }
            this.proxyState.checkValidObject(settings);
            row$realm.getTable().setLink(this.columnInfo.settingsColKey, row$realm.getObjectKey(), ((RealmObjectProxy) settings).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public ValidateBean realmGet$validate() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.validateColKey)) {
            return null;
        }
        return (ValidateBean) this.proxyState.getRealm$realm().get(ValidateBean.class, this.proxyState.getRow$realm().getLink(this.columnInfo.validateColKey), false, Collections.emptyList());
    }

    public void realmSet$validate(ValidateBean validateBean) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (validateBean == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.validateColKey);
                return;
            }
            this.proxyState.checkValidObject(validateBean);
            this.proxyState.getRow$realm().setLink(this.columnInfo.validateColKey, ((RealmObjectProxy) validateBean).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("validate")) {
            if (validateBean != null && !RealmObject.isManaged(validateBean)) {
                validateBean = (ValidateBean) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(validateBean, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (validateBean == null) {
                row$realm.nullifyLink(this.columnInfo.validateColKey);
                return;
            }
            this.proxyState.checkValidObject(validateBean);
            row$realm.getTable().setLink(this.columnInfo.validateColKey, row$realm.getObjectKey(), ((RealmObjectProxy) validateBean).realmGet$proxyState().getRow$realm().getObjectKey(), true);
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

    public String realmGet$sms() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.smsColKey);
    }

    public void realmSet$sms(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.smsColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.smsColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.smsColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.smsColKey, row$realm.getObjectKey(), str, true);
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

    public EmployeeBean realmGet$employee() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.employeeColKey)) {
            return null;
        }
        return (EmployeeBean) this.proxyState.getRealm$realm().get(EmployeeBean.class, this.proxyState.getRow$realm().getLink(this.columnInfo.employeeColKey), false, Collections.emptyList());
    }

    public void realmSet$employee(EmployeeBean employeeBean) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (employeeBean == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.employeeColKey);
                return;
            }
            this.proxyState.checkValidObject(employeeBean);
            this.proxyState.getRow$realm().setLink(this.columnInfo.employeeColKey, ((RealmObjectProxy) employeeBean).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains(Person.EMPLOYEE)) {
            if (employeeBean != null && !RealmObject.isManaged(employeeBean)) {
                employeeBean = (EmployeeBean) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(employeeBean, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (employeeBean == null) {
                row$realm.nullifyLink(this.columnInfo.employeeColKey);
                return;
            }
            this.proxyState.checkValidObject(employeeBean);
            row$realm.getTable().setLink(this.columnInfo.employeeColKey, row$realm.getObjectKey(), ((RealmObjectProxy) employeeBean).realmGet$proxyState().getRow$realm().getObjectKey(), true);
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

    public int realmGet$createtime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.createtimeColKey);
    }

    public void realmSet$createtime(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.createtimeColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.createtimeColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public CompanyResponse realmGet$company() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.companyColKey)) {
            return null;
        }
        return (CompanyResponse) this.proxyState.getRealm$realm().get(CompanyResponse.class, this.proxyState.getRow$realm().getLink(this.columnInfo.companyColKey), false, Collections.emptyList());
    }

    public void realmSet$company(CompanyResponse companyResponse) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (companyResponse == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.companyColKey);
                return;
            }
            this.proxyState.checkValidObject(companyResponse);
            this.proxyState.getRow$realm().setLink(this.columnInfo.companyColKey, ((RealmObjectProxy) companyResponse).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("company")) {
            if (companyResponse != null && !RealmObject.isManaged(companyResponse)) {
                companyResponse = (CompanyResponse) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(companyResponse, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (companyResponse == null) {
                row$realm.nullifyLink(this.columnInfo.companyColKey);
                return;
            }
            this.proxyState.checkValidObject(companyResponse);
            row$realm.getTable().setLink(this.columnInfo.companyColKey, row$realm.getObjectKey(), ((RealmObjectProxy) companyResponse).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public AckBean realmGet$ack() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.ackColKey)) {
            return null;
        }
        return (AckBean) this.proxyState.getRealm$realm().get(AckBean.class, this.proxyState.getRow$realm().getLink(this.columnInfo.ackColKey), false, Collections.emptyList());
    }

    public void realmSet$ack(AckBean ackBean) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (ackBean == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.ackColKey);
                return;
            }
            this.proxyState.checkValidObject(ackBean);
            this.proxyState.getRow$realm().setLink(this.columnInfo.ackColKey, ((RealmObjectProxy) ackBean).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("ack")) {
            if (ackBean != null && !RealmObject.isManaged(ackBean)) {
                ackBean = (AckBean) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(ackBean, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (ackBean == null) {
                row$realm.nullifyLink(this.columnInfo.ackColKey);
                return;
            }
            this.proxyState.checkValidObject(ackBean);
            row$realm.getTable().setLink(this.columnInfo.ackColKey, row$realm.getObjectKey(), ((RealmObjectProxy) ackBean).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public String realmGet$auditnotes() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.auditnotesColKey);
    }

    public void realmSet$auditnotes(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.auditnotesColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.auditnotesColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.auditnotesColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.auditnotesColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public LockMode realmGet$lockmode() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.lockmodeColKey)) {
            return null;
        }
        return (LockMode) this.proxyState.getRealm$realm().get(LockMode.class, this.proxyState.getRow$realm().getLink(this.columnInfo.lockmodeColKey), false, Collections.emptyList());
    }

    public void realmSet$lockmode(LockMode lockMode) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (lockMode == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.lockmodeColKey);
                return;
            }
            this.proxyState.checkValidObject(lockMode);
            this.proxyState.getRow$realm().setLink(this.columnInfo.lockmodeColKey, ((RealmObjectProxy) lockMode).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("lockmode")) {
            if (lockMode != null && !RealmObject.isManaged(lockMode)) {
                lockMode = (LockMode) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(lockMode, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (lockMode == null) {
                row$realm.nullifyLink(this.columnInfo.lockmodeColKey);
                return;
            }
            this.proxyState.checkValidObject(lockMode);
            row$realm.getTable().setLink(this.columnInfo.lockmodeColKey, row$realm.getObjectKey(), ((RealmObjectProxy) lockMode).realmGet$proxyState().getRow$realm().getObjectKey(), true);
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
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 18, 0);
        builder.addPersistedLinkProperty(Person.VISITOR, RealmFieldType.OBJECT, com_ciot_realm_db_common_VisitorBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("begin", RealmFieldType.DOUBLE, false, false, true);
        builder2.addPersistedProperty("proofNum", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty(TombstoneParser.keyCode, RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("settings", RealmFieldType.OBJECT, com_ciot_realm_db_common_SettingsRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedLinkProperty("validate", RealmFieldType.OBJECT, com_ciot_realm_db_common_ValidateBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder3 = builder;
        builder3.addPersistedProperty("end", RealmFieldType.DOUBLE, false, false, true);
        builder3.addPersistedProperty("sms", RealmFieldType.STRING, false, false, false);
        builder3.addPersistedProperty("type", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedLinkProperty(Person.EMPLOYEE, RealmFieldType.OBJECT, com_ciot_realm_db_EmployeeBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder4 = builder;
        builder4.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        builder4.addPersistedProperty(NotificationCompat.CATEGORY_STATUS, RealmFieldType.STRING, false, false, false);
        builder4.addPersistedProperty("createtime", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedLinkProperty("company", RealmFieldType.OBJECT, com_ciot_realm_db_common_CompanyResponseRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedLinkProperty("ack", RealmFieldType.OBJECT, com_ciot_realm_db_common_AckBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedProperty("auditnotes", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("lockmode", RealmFieldType.OBJECT, com_ciot_realm_db_LockModeRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedProperty("id", RealmFieldType.STRING, true, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static RegisterWithAppointmentResponseColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new RegisterWithAppointmentResponseColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r0v4, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x01cb  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x01e7  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0204  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x021d  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0236  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0252  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x026d  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x028a  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02a1  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0145  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0177  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.RegisterWithAppointmentResponse createOrUpdateUsingJsonObject(io.realm.Realm r13, org.json.JSONObject r14, boolean r15) throws org.json.JSONException {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 7
            r0.<init>(r1)
            java.lang.String r1 = "id"
            r2 = 0
            if (r15 == 0) goto L_0x0066
            java.lang.Class<com.ciot.realm.db.RegisterWithAppointmentResponse> r3 = com.ciot.realm.db.RegisterWithAppointmentResponse.class
            io.realm.internal.Table r3 = r13.getTable(r3)
            io.realm.RealmSchema r4 = r13.getSchema()
            java.lang.Class<com.ciot.realm.db.RegisterWithAppointmentResponse> r5 = com.ciot.realm.db.RegisterWithAppointmentResponse.class
            io.realm.internal.ColumnInfo r4 = r4.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r5)
            io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy$RegisterWithAppointmentResponseColumnInfo r4 = (io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.RegisterWithAppointmentResponseColumnInfo) r4
            long r4 = r4.idColKey
            boolean r6 = r14.isNull(r1)
            if (r6 == 0) goto L_0x002a
            long r4 = r3.findFirstNull(r4)
            goto L_0x0032
        L_0x002a:
            java.lang.String r6 = r14.getString(r1)
            long r4 = r3.findFirstString(r4, r6)
        L_0x0032:
            r6 = -1
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x0066
            io.realm.BaseRealm$ThreadLocalRealmObjectContext r6 = io.realm.BaseRealm.objectContext
            java.lang.Object r6 = r6.get()
            io.realm.BaseRealm$RealmObjectContext r6 = (io.realm.BaseRealm.RealmObjectContext) r6
            io.realm.internal.UncheckedRow r9 = r3.getUncheckedRow(r4)     // Catch:{ all -> 0x0061 }
            io.realm.RealmSchema r3 = r13.getSchema()     // Catch:{ all -> 0x0061 }
            java.lang.Class<com.ciot.realm.db.RegisterWithAppointmentResponse> r4 = com.ciot.realm.db.RegisterWithAppointmentResponse.class
            io.realm.internal.ColumnInfo r10 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)     // Catch:{ all -> 0x0061 }
            r11 = 0
            java.util.List r12 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0061 }
            r7 = r6
            r8 = r13
            r7.set(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0061 }
            io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy r3 = new io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy     // Catch:{ all -> 0x0061 }
            r3.<init>()     // Catch:{ all -> 0x0061 }
            r6.clear()
            goto L_0x0067
        L_0x0061:
            r13 = move-exception
            r6.clear()
            throw r13
        L_0x0066:
            r3 = r2
        L_0x0067:
            java.lang.String r4 = "lockmode"
            java.lang.String r5 = "ack"
            java.lang.String r6 = "company"
            java.lang.String r7 = "employee"
            java.lang.String r8 = "validate"
            java.lang.String r9 = "settings"
            java.lang.String r10 = "visitor"
            if (r3 != 0) goto L_0x00e3
            boolean r3 = r14.has(r10)
            if (r3 == 0) goto L_0x0080
            r0.add(r10)
        L_0x0080:
            boolean r3 = r14.has(r9)
            if (r3 == 0) goto L_0x0089
            r0.add(r9)
        L_0x0089:
            boolean r3 = r14.has(r8)
            if (r3 == 0) goto L_0x0092
            r0.add(r8)
        L_0x0092:
            boolean r3 = r14.has(r7)
            if (r3 == 0) goto L_0x009b
            r0.add(r7)
        L_0x009b:
            boolean r3 = r14.has(r6)
            if (r3 == 0) goto L_0x00a4
            r0.add(r6)
        L_0x00a4:
            boolean r3 = r14.has(r5)
            if (r3 == 0) goto L_0x00ad
            r0.add(r5)
        L_0x00ad:
            boolean r3 = r14.has(r4)
            if (r3 == 0) goto L_0x00b6
            r0.add(r4)
        L_0x00b6:
            boolean r3 = r14.has(r1)
            if (r3 == 0) goto L_0x00db
            boolean r3 = r14.isNull(r1)
            r11 = 1
            if (r3 == 0) goto L_0x00cd
            java.lang.Class<com.ciot.realm.db.RegisterWithAppointmentResponse> r1 = com.ciot.realm.db.RegisterWithAppointmentResponse.class
            io.realm.RealmModel r0 = r13.createObjectInternal(r1, r2, r11, r0)
            r3 = r0
            io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy r3 = (io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy) r3
            goto L_0x00e3
        L_0x00cd:
            java.lang.Class<com.ciot.realm.db.RegisterWithAppointmentResponse> r3 = com.ciot.realm.db.RegisterWithAppointmentResponse.class
            java.lang.String r1 = r14.getString(r1)
            io.realm.RealmModel r0 = r13.createObjectInternal(r3, r1, r11, r0)
            r3 = r0
            io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy r3 = (io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy) r3
            goto L_0x00e3
        L_0x00db:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "JSON object doesn't have the primary key field 'id'."
            r13.<init>(r14)
            throw r13
        L_0x00e3:
            r0 = r3
            io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxyInterface r0 = (io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxyInterface) r0
            boolean r1 = r14.has(r10)
            if (r1 == 0) goto L_0x0101
            boolean r1 = r14.isNull(r10)
            if (r1 == 0) goto L_0x00f6
            r0.realmSet$visitor(r2)
            goto L_0x0101
        L_0x00f6:
            org.json.JSONObject r1 = r14.getJSONObject(r10)
            com.ciot.realm.db.common.VisitorBean r1 = io.realm.com_ciot_realm_db_common_VisitorBeanRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$visitor(r1)
        L_0x0101:
            java.lang.String r1 = "begin"
            boolean r10 = r14.has(r1)
            if (r10 == 0) goto L_0x011f
            boolean r10 = r14.isNull(r1)
            if (r10 != 0) goto L_0x0117
            double r10 = r14.getDouble(r1)
            r0.realmSet$begin(r10)
            goto L_0x011f
        L_0x0117:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'begin' to null."
            r13.<init>(r14)
            throw r13
        L_0x011f:
            java.lang.String r1 = "proofNum"
            boolean r10 = r14.has(r1)
            if (r10 == 0) goto L_0x013d
            boolean r10 = r14.isNull(r1)
            if (r10 != 0) goto L_0x0135
            int r1 = r14.getInt(r1)
            r0.realmSet$proofNum(r1)
            goto L_0x013d
        L_0x0135:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'proofNum' to null."
            r13.<init>(r14)
            throw r13
        L_0x013d:
            java.lang.String r1 = "code"
            boolean r10 = r14.has(r1)
            if (r10 == 0) goto L_0x0156
            boolean r10 = r14.isNull(r1)
            if (r10 == 0) goto L_0x014f
            r0.realmSet$code(r2)
            goto L_0x0156
        L_0x014f:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$code(r1)
        L_0x0156:
            boolean r1 = r14.has(r9)
            if (r1 == 0) goto L_0x0171
            boolean r1 = r14.isNull(r9)
            if (r1 == 0) goto L_0x0166
            r0.realmSet$settings(r2)
            goto L_0x0171
        L_0x0166:
            org.json.JSONObject r1 = r14.getJSONObject(r9)
            com.ciot.realm.db.common.Settings r1 = io.realm.com_ciot_realm_db_common_SettingsRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$settings(r1)
        L_0x0171:
            boolean r1 = r14.has(r8)
            if (r1 == 0) goto L_0x018c
            boolean r1 = r14.isNull(r8)
            if (r1 == 0) goto L_0x0181
            r0.realmSet$validate(r2)
            goto L_0x018c
        L_0x0181:
            org.json.JSONObject r1 = r14.getJSONObject(r8)
            com.ciot.realm.db.common.ValidateBean r1 = io.realm.com_ciot_realm_db_common_ValidateBeanRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$validate(r1)
        L_0x018c:
            java.lang.String r1 = "end"
            boolean r8 = r14.has(r1)
            if (r8 == 0) goto L_0x01aa
            boolean r8 = r14.isNull(r1)
            if (r8 != 0) goto L_0x01a2
            double r8 = r14.getDouble(r1)
            r0.realmSet$end(r8)
            goto L_0x01aa
        L_0x01a2:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'end' to null."
            r13.<init>(r14)
            throw r13
        L_0x01aa:
            java.lang.String r1 = "sms"
            boolean r8 = r14.has(r1)
            if (r8 == 0) goto L_0x01c3
            boolean r8 = r14.isNull(r1)
            if (r8 == 0) goto L_0x01bc
            r0.realmSet$sms(r2)
            goto L_0x01c3
        L_0x01bc:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$sms(r1)
        L_0x01c3:
            java.lang.String r1 = "type"
            boolean r8 = r14.has(r1)
            if (r8 == 0) goto L_0x01e1
            boolean r8 = r14.isNull(r1)
            if (r8 != 0) goto L_0x01d9
            int r1 = r14.getInt(r1)
            r0.realmSet$type(r1)
            goto L_0x01e1
        L_0x01d9:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'type' to null."
            r13.<init>(r14)
            throw r13
        L_0x01e1:
            boolean r1 = r14.has(r7)
            if (r1 == 0) goto L_0x01fc
            boolean r1 = r14.isNull(r7)
            if (r1 == 0) goto L_0x01f1
            r0.realmSet$employee(r2)
            goto L_0x01fc
        L_0x01f1:
            org.json.JSONObject r1 = r14.getJSONObject(r7)
            com.ciot.realm.db.EmployeeBean r1 = io.realm.com_ciot_realm_db_EmployeeBeanRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$employee(r1)
        L_0x01fc:
            java.lang.String r1 = "description"
            boolean r7 = r14.has(r1)
            if (r7 == 0) goto L_0x0215
            boolean r7 = r14.isNull(r1)
            if (r7 == 0) goto L_0x020e
            r0.realmSet$description(r2)
            goto L_0x0215
        L_0x020e:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$description(r1)
        L_0x0215:
            java.lang.String r1 = "status"
            boolean r7 = r14.has(r1)
            if (r7 == 0) goto L_0x022e
            boolean r7 = r14.isNull(r1)
            if (r7 == 0) goto L_0x0227
            r0.realmSet$status(r2)
            goto L_0x022e
        L_0x0227:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$status(r1)
        L_0x022e:
            java.lang.String r1 = "createtime"
            boolean r7 = r14.has(r1)
            if (r7 == 0) goto L_0x024c
            boolean r7 = r14.isNull(r1)
            if (r7 != 0) goto L_0x0244
            int r1 = r14.getInt(r1)
            r0.realmSet$createtime(r1)
            goto L_0x024c
        L_0x0244:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'createtime' to null."
            r13.<init>(r14)
            throw r13
        L_0x024c:
            boolean r1 = r14.has(r6)
            if (r1 == 0) goto L_0x0267
            boolean r1 = r14.isNull(r6)
            if (r1 == 0) goto L_0x025c
            r0.realmSet$company(r2)
            goto L_0x0267
        L_0x025c:
            org.json.JSONObject r1 = r14.getJSONObject(r6)
            com.ciot.realm.db.common.CompanyResponse r1 = io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$company(r1)
        L_0x0267:
            boolean r1 = r14.has(r5)
            if (r1 == 0) goto L_0x0282
            boolean r1 = r14.isNull(r5)
            if (r1 == 0) goto L_0x0277
            r0.realmSet$ack(r2)
            goto L_0x0282
        L_0x0277:
            org.json.JSONObject r1 = r14.getJSONObject(r5)
            com.ciot.realm.db.common.AckBean r1 = io.realm.com_ciot_realm_db_common_AckBeanRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$ack(r1)
        L_0x0282:
            java.lang.String r1 = "auditnotes"
            boolean r5 = r14.has(r1)
            if (r5 == 0) goto L_0x029b
            boolean r5 = r14.isNull(r1)
            if (r5 == 0) goto L_0x0294
            r0.realmSet$auditnotes(r2)
            goto L_0x029b
        L_0x0294:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$auditnotes(r1)
        L_0x029b:
            boolean r1 = r14.has(r4)
            if (r1 == 0) goto L_0x02b6
            boolean r1 = r14.isNull(r4)
            if (r1 == 0) goto L_0x02ab
            r0.realmSet$lockmode(r2)
            goto L_0x02b6
        L_0x02ab:
            org.json.JSONObject r14 = r14.getJSONObject(r4)
            com.ciot.realm.db.LockMode r13 = io.realm.com_ciot_realm_db_LockModeRealmProxy.createOrUpdateUsingJsonObject(r13, r14, r15)
            r0.realmSet$lockmode(r13)
        L_0x02b6:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.RegisterWithAppointmentResponse");
    }

    public static RegisterWithAppointmentResponse createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        RegisterWithAppointmentResponse registerWithAppointmentResponse = new RegisterWithAppointmentResponse();
        com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxyInterface com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface = registerWithAppointmentResponse;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(Person.VISITOR)) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$visitor((VisitorBean) null);
                } else {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$visitor(com_ciot_realm_db_common_VisitorBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("begin")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$begin(jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'begin' to null.");
                }
            } else if (nextName.equals("proofNum")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$proofNum(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'proofNum' to null.");
                }
            } else if (nextName.equals(TombstoneParser.keyCode)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$code(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$code((String) null);
                }
            } else if (nextName.equals("settings")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$settings((Settings) null);
                } else {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$settings(com_ciot_realm_db_common_SettingsRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("validate")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$validate((ValidateBean) null);
                } else {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$validate(com_ciot_realm_db_common_ValidateBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("end")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$end(jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'end' to null.");
                }
            } else if (nextName.equals("sms")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$sms(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$sms((String) null);
                }
            } else if (nextName.equals("type")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$type(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'type' to null.");
                }
            } else if (nextName.equals(Person.EMPLOYEE)) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$employee((EmployeeBean) null);
                } else {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$employee(com_ciot_realm_db_EmployeeBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("description")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$description(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$description((String) null);
                }
            } else if (nextName.equals(NotificationCompat.CATEGORY_STATUS)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$status(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$status((String) null);
                }
            } else if (nextName.equals("createtime")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$createtime(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'createtime' to null.");
                }
            } else if (nextName.equals("company")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$company((CompanyResponse) null);
                } else {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$company(com_ciot_realm_db_common_CompanyResponseRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("ack")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$ack((AckBean) null);
                } else {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$ack(com_ciot_realm_db_common_AckBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("auditnotes")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$auditnotes(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$auditnotes((String) null);
                }
            } else if (nextName.equals("lockmode")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$lockmode((LockMode) null);
                } else {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$lockmode(com_ciot_realm_db_LockModeRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$id((String) null);
                }
                z = true;
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        if (z) {
            return (RegisterWithAppointmentResponse) realm.copyToRealm(registerWithAppointmentResponse, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) RegisterWithAppointmentResponse.class), false, Collections.emptyList());
        com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy com_ciot_realm_db_registerwithappointmentresponserealmproxy = new com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_registerwithappointmentresponserealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.RegisterWithAppointmentResponse copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.RegisterWithAppointmentResponseColumnInfo r9, com.ciot.realm.db.RegisterWithAppointmentResponse r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.RegisterWithAppointmentResponse r1 = (com.ciot.realm.db.RegisterWithAppointmentResponse) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.RegisterWithAppointmentResponse> r2 = com.ciot.realm.db.RegisterWithAppointmentResponse.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxyInterface) r5
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
            io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy r1 = new io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.RegisterWithAppointmentResponse r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.RegisterWithAppointmentResponse r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy$RegisterWithAppointmentResponseColumnInfo, com.ciot.realm.db.RegisterWithAppointmentResponse, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.RegisterWithAppointmentResponse");
    }

    public static RegisterWithAppointmentResponse copy(Realm realm, RegisterWithAppointmentResponseColumnInfo registerWithAppointmentResponseColumnInfo, RegisterWithAppointmentResponse registerWithAppointmentResponse, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(registerWithAppointmentResponse);
        if (realmObjectProxy != null) {
            return (RegisterWithAppointmentResponse) realmObjectProxy;
        }
        com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxyInterface com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface = registerWithAppointmentResponse;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(RegisterWithAppointmentResponse.class), set);
        osObjectBuilder.addDouble(registerWithAppointmentResponseColumnInfo.beginColKey, Double.valueOf(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$begin()));
        osObjectBuilder.addInteger(registerWithAppointmentResponseColumnInfo.proofNumColKey, Integer.valueOf(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$proofNum()));
        osObjectBuilder.addString(registerWithAppointmentResponseColumnInfo.codeColKey, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$code());
        osObjectBuilder.addDouble(registerWithAppointmentResponseColumnInfo.endColKey, Double.valueOf(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$end()));
        osObjectBuilder.addString(registerWithAppointmentResponseColumnInfo.smsColKey, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$sms());
        osObjectBuilder.addInteger(registerWithAppointmentResponseColumnInfo.typeColKey, Integer.valueOf(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$type()));
        osObjectBuilder.addString(registerWithAppointmentResponseColumnInfo.descriptionColKey, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$description());
        osObjectBuilder.addString(registerWithAppointmentResponseColumnInfo.statusColKey, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$status());
        osObjectBuilder.addInteger(registerWithAppointmentResponseColumnInfo.createtimeColKey, Integer.valueOf(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$createtime()));
        osObjectBuilder.addString(registerWithAppointmentResponseColumnInfo.auditnotesColKey, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$auditnotes());
        osObjectBuilder.addString(registerWithAppointmentResponseColumnInfo.idColKey, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$id());
        com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(registerWithAppointmentResponse, newProxyInstance);
        VisitorBean realmGet$visitor = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$visitor();
        if (realmGet$visitor == null) {
            newProxyInstance.realmSet$visitor((VisitorBean) null);
        } else {
            VisitorBean visitorBean = (VisitorBean) map.get(realmGet$visitor);
            if (visitorBean != null) {
                newProxyInstance.realmSet$visitor(visitorBean);
            } else {
                newProxyInstance.realmSet$visitor(com_ciot_realm_db_common_VisitorBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_VisitorBeanRealmProxy.VisitorBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) VisitorBean.class), realmGet$visitor, z, map, set));
            }
        }
        Settings realmGet$settings = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$settings();
        if (realmGet$settings == null) {
            newProxyInstance.realmSet$settings((Settings) null);
        } else {
            Settings settings = (Settings) map.get(realmGet$settings);
            if (settings != null) {
                newProxyInstance.realmSet$settings(settings);
            } else {
                newProxyInstance.realmSet$settings(com_ciot_realm_db_common_SettingsRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_SettingsRealmProxy.SettingsColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Settings.class), realmGet$settings, z, map, set));
            }
        }
        ValidateBean realmGet$validate = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$validate();
        if (realmGet$validate == null) {
            newProxyInstance.realmSet$validate((ValidateBean) null);
        } else {
            ValidateBean validateBean = (ValidateBean) map.get(realmGet$validate);
            if (validateBean != null) {
                newProxyInstance.realmSet$validate(validateBean);
            } else {
                newProxyInstance.realmSet$validate(com_ciot_realm_db_common_ValidateBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_ValidateBeanRealmProxy.ValidateBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ValidateBean.class), realmGet$validate, z, map, set));
            }
        }
        EmployeeBean realmGet$employee = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$employee();
        if (realmGet$employee == null) {
            newProxyInstance.realmSet$employee((EmployeeBean) null);
        } else {
            EmployeeBean employeeBean = (EmployeeBean) map.get(realmGet$employee);
            if (employeeBean != null) {
                newProxyInstance.realmSet$employee(employeeBean);
            } else {
                newProxyInstance.realmSet$employee(com_ciot_realm_db_EmployeeBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_EmployeeBeanRealmProxy.EmployeeBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeBean.class), realmGet$employee, z, map, set));
            }
        }
        CompanyResponse realmGet$company = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$company();
        if (realmGet$company == null) {
            newProxyInstance.realmSet$company((CompanyResponse) null);
        } else {
            CompanyResponse companyResponse = (CompanyResponse) map.get(realmGet$company);
            if (companyResponse != null) {
                newProxyInstance.realmSet$company(companyResponse);
            } else {
                newProxyInstance.realmSet$company(com_ciot_realm_db_common_CompanyResponseRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_CompanyResponseRealmProxy.CompanyResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CompanyResponse.class), realmGet$company, z, map, set));
            }
        }
        AckBean realmGet$ack = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$ack();
        if (realmGet$ack == null) {
            newProxyInstance.realmSet$ack((AckBean) null);
        } else {
            AckBean ackBean = (AckBean) map.get(realmGet$ack);
            if (ackBean != null) {
                newProxyInstance.realmSet$ack(ackBean);
            } else {
                newProxyInstance.realmSet$ack(com_ciot_realm_db_common_AckBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_AckBeanRealmProxy.AckBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AckBean.class), realmGet$ack, z, map, set));
            }
        }
        LockMode realmGet$lockmode = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$lockmode();
        if (realmGet$lockmode == null) {
            newProxyInstance.realmSet$lockmode((LockMode) null);
        } else {
            LockMode lockMode = (LockMode) map.get(realmGet$lockmode);
            if (lockMode != null) {
                newProxyInstance.realmSet$lockmode(lockMode);
            } else {
                newProxyInstance.realmSet$lockmode(com_ciot_realm_db_LockModeRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_LockModeRealmProxy.LockModeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) LockMode.class), realmGet$lockmode, z, map, set));
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, RegisterWithAppointmentResponse registerWithAppointmentResponse, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        RegisterWithAppointmentResponse registerWithAppointmentResponse2 = registerWithAppointmentResponse;
        Map<RealmModel, Long> map2 = map;
        if ((registerWithAppointmentResponse2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(registerWithAppointmentResponse)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) registerWithAppointmentResponse2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(RegisterWithAppointmentResponse.class);
        long nativePtr = table.getNativePtr();
        RegisterWithAppointmentResponseColumnInfo registerWithAppointmentResponseColumnInfo = (RegisterWithAppointmentResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RegisterWithAppointmentResponse.class);
        long j2 = registerWithAppointmentResponseColumnInfo.idColKey;
        com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxyInterface com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface = registerWithAppointmentResponse2;
        String realmGet$id = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$id();
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
        map2.put(registerWithAppointmentResponse2, Long.valueOf(j3));
        VisitorBean realmGet$visitor = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$visitor();
        if (realmGet$visitor != null) {
            Long l = map2.get(realmGet$visitor);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_common_VisitorBeanRealmProxy.insert(realm2, realmGet$visitor, map2));
            }
            Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.visitorColKey, j3, l.longValue(), false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetDouble(j4, registerWithAppointmentResponseColumnInfo.beginColKey, j5, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$begin(), false);
        Table.nativeSetLong(j4, registerWithAppointmentResponseColumnInfo.proofNumColKey, j5, (long) com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$proofNum(), false);
        String realmGet$code = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$code();
        if (realmGet$code != null) {
            Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.codeColKey, j3, realmGet$code, false);
        }
        Settings realmGet$settings = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$settings();
        if (realmGet$settings != null) {
            Long l2 = map2.get(realmGet$settings);
            if (l2 == null) {
                l2 = Long.valueOf(com_ciot_realm_db_common_SettingsRealmProxy.insert(realm2, realmGet$settings, map2));
            }
            Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.settingsColKey, j3, l2.longValue(), false);
        }
        ValidateBean realmGet$validate = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$validate();
        if (realmGet$validate != null) {
            Long l3 = map2.get(realmGet$validate);
            if (l3 == null) {
                l3 = Long.valueOf(com_ciot_realm_db_common_ValidateBeanRealmProxy.insert(realm2, realmGet$validate, map2));
            }
            Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.validateColKey, j3, l3.longValue(), false);
        }
        Table.nativeSetDouble(nativePtr, registerWithAppointmentResponseColumnInfo.endColKey, j3, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$end(), false);
        String realmGet$sms = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$sms();
        if (realmGet$sms != null) {
            Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.smsColKey, j3, realmGet$sms, false);
        }
        Table.nativeSetLong(nativePtr, registerWithAppointmentResponseColumnInfo.typeColKey, j3, (long) com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$type(), false);
        EmployeeBean realmGet$employee = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$employee();
        if (realmGet$employee != null) {
            Long l4 = map2.get(realmGet$employee);
            if (l4 == null) {
                l4 = Long.valueOf(com_ciot_realm_db_EmployeeBeanRealmProxy.insert(realm2, realmGet$employee, map2));
            }
            Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.employeeColKey, j3, l4.longValue(), false);
        }
        String realmGet$description = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.descriptionColKey, j3, realmGet$description, false);
        }
        String realmGet$status = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.statusColKey, j3, realmGet$status, false);
        }
        Table.nativeSetLong(nativePtr, registerWithAppointmentResponseColumnInfo.createtimeColKey, j3, (long) com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$createtime(), false);
        CompanyResponse realmGet$company = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$company();
        if (realmGet$company != null) {
            Long l5 = map2.get(realmGet$company);
            if (l5 == null) {
                l5 = Long.valueOf(com_ciot_realm_db_common_CompanyResponseRealmProxy.insert(realm2, realmGet$company, map2));
            }
            Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.companyColKey, j3, l5.longValue(), false);
        }
        AckBean realmGet$ack = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$ack();
        if (realmGet$ack != null) {
            Long l6 = map2.get(realmGet$ack);
            if (l6 == null) {
                l6 = Long.valueOf(com_ciot_realm_db_common_AckBeanRealmProxy.insert(realm2, realmGet$ack, map2));
            }
            Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.ackColKey, j3, l6.longValue(), false);
        }
        String realmGet$auditnotes = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$auditnotes();
        if (realmGet$auditnotes != null) {
            Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.auditnotesColKey, j3, realmGet$auditnotes, false);
        }
        LockMode realmGet$lockmode = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$lockmode();
        if (realmGet$lockmode != null) {
            Long l7 = map2.get(realmGet$lockmode);
            if (l7 == null) {
                l7 = Long.valueOf(com_ciot_realm_db_LockModeRealmProxy.insert(realm2, realmGet$lockmode, map2));
            }
            Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.lockmodeColKey, j3, l7.longValue(), false);
        }
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(RegisterWithAppointmentResponse.class);
        long nativePtr = table.getNativePtr();
        RegisterWithAppointmentResponseColumnInfo registerWithAppointmentResponseColumnInfo = (RegisterWithAppointmentResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RegisterWithAppointmentResponse.class);
        long j4 = registerWithAppointmentResponseColumnInfo.idColKey;
        while (it.hasNext()) {
            RegisterWithAppointmentResponse registerWithAppointmentResponse = (RegisterWithAppointmentResponse) it.next();
            if (!map2.containsKey(registerWithAppointmentResponse)) {
                if ((registerWithAppointmentResponse instanceof RealmObjectProxy) && !RealmObject.isFrozen(registerWithAppointmentResponse)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) registerWithAppointmentResponse;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(registerWithAppointmentResponse, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxyInterface com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface = registerWithAppointmentResponse;
                String realmGet$id = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$id();
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
                map2.put(registerWithAppointmentResponse, Long.valueOf(j2));
                VisitorBean realmGet$visitor = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$visitor();
                if (realmGet$visitor != null) {
                    Long l = map2.get(realmGet$visitor);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_common_VisitorBeanRealmProxy.insert(realm2, realmGet$visitor, map2));
                    }
                    j3 = j4;
                    table.setLink(registerWithAppointmentResponseColumnInfo.visitorColKey, j2, l.longValue(), false);
                } else {
                    j3 = j4;
                }
                long j5 = j2;
                Table.nativeSetDouble(nativePtr, registerWithAppointmentResponseColumnInfo.beginColKey, j5, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$begin(), false);
                Table.nativeSetLong(nativePtr, registerWithAppointmentResponseColumnInfo.proofNumColKey, j5, (long) com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$proofNum(), false);
                String realmGet$code = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$code();
                if (realmGet$code != null) {
                    Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.codeColKey, j2, realmGet$code, false);
                }
                Settings realmGet$settings = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$settings();
                if (realmGet$settings != null) {
                    Long l2 = map2.get(realmGet$settings);
                    if (l2 == null) {
                        l2 = Long.valueOf(com_ciot_realm_db_common_SettingsRealmProxy.insert(realm2, realmGet$settings, map2));
                    }
                    table.setLink(registerWithAppointmentResponseColumnInfo.settingsColKey, j2, l2.longValue(), false);
                }
                ValidateBean realmGet$validate = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$validate();
                if (realmGet$validate != null) {
                    Long l3 = map2.get(realmGet$validate);
                    if (l3 == null) {
                        l3 = Long.valueOf(com_ciot_realm_db_common_ValidateBeanRealmProxy.insert(realm2, realmGet$validate, map2));
                    }
                    table.setLink(registerWithAppointmentResponseColumnInfo.validateColKey, j2, l3.longValue(), false);
                }
                Table.nativeSetDouble(nativePtr, registerWithAppointmentResponseColumnInfo.endColKey, j2, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$end(), false);
                String realmGet$sms = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$sms();
                if (realmGet$sms != null) {
                    Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.smsColKey, j2, realmGet$sms, false);
                }
                Table.nativeSetLong(nativePtr, registerWithAppointmentResponseColumnInfo.typeColKey, j2, (long) com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$type(), false);
                EmployeeBean realmGet$employee = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$employee();
                if (realmGet$employee != null) {
                    Long l4 = map2.get(realmGet$employee);
                    if (l4 == null) {
                        l4 = Long.valueOf(com_ciot_realm_db_EmployeeBeanRealmProxy.insert(realm2, realmGet$employee, map2));
                    }
                    table.setLink(registerWithAppointmentResponseColumnInfo.employeeColKey, j2, l4.longValue(), false);
                }
                String realmGet$description = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.descriptionColKey, j2, realmGet$description, false);
                }
                String realmGet$status = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$status();
                if (realmGet$status != null) {
                    Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.statusColKey, j2, realmGet$status, false);
                }
                Table.nativeSetLong(nativePtr, registerWithAppointmentResponseColumnInfo.createtimeColKey, j2, (long) com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$createtime(), false);
                CompanyResponse realmGet$company = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$company();
                if (realmGet$company != null) {
                    Long l5 = map2.get(realmGet$company);
                    if (l5 == null) {
                        l5 = Long.valueOf(com_ciot_realm_db_common_CompanyResponseRealmProxy.insert(realm2, realmGet$company, map2));
                    }
                    table.setLink(registerWithAppointmentResponseColumnInfo.companyColKey, j2, l5.longValue(), false);
                }
                AckBean realmGet$ack = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$ack();
                if (realmGet$ack != null) {
                    Long l6 = map2.get(realmGet$ack);
                    if (l6 == null) {
                        l6 = Long.valueOf(com_ciot_realm_db_common_AckBeanRealmProxy.insert(realm2, realmGet$ack, map2));
                    }
                    table.setLink(registerWithAppointmentResponseColumnInfo.ackColKey, j2, l6.longValue(), false);
                }
                String realmGet$auditnotes = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$auditnotes();
                if (realmGet$auditnotes != null) {
                    Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.auditnotesColKey, j2, realmGet$auditnotes, false);
                }
                LockMode realmGet$lockmode = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$lockmode();
                if (realmGet$lockmode != null) {
                    Long l7 = map2.get(realmGet$lockmode);
                    if (l7 == null) {
                        l7 = Long.valueOf(com_ciot_realm_db_LockModeRealmProxy.insert(realm2, realmGet$lockmode, map2));
                    }
                    table.setLink(registerWithAppointmentResponseColumnInfo.lockmodeColKey, j2, l7.longValue(), false);
                }
                j4 = j3;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, RegisterWithAppointmentResponse registerWithAppointmentResponse, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        RegisterWithAppointmentResponse registerWithAppointmentResponse2 = registerWithAppointmentResponse;
        Map<RealmModel, Long> map2 = map;
        if ((registerWithAppointmentResponse2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(registerWithAppointmentResponse)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) registerWithAppointmentResponse2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(RegisterWithAppointmentResponse.class);
        long nativePtr = table.getNativePtr();
        RegisterWithAppointmentResponseColumnInfo registerWithAppointmentResponseColumnInfo = (RegisterWithAppointmentResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RegisterWithAppointmentResponse.class);
        long j2 = registerWithAppointmentResponseColumnInfo.idColKey;
        com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxyInterface com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface = registerWithAppointmentResponse2;
        String realmGet$id = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$id);
        }
        long j3 = j;
        map2.put(registerWithAppointmentResponse2, Long.valueOf(j3));
        VisitorBean realmGet$visitor = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$visitor();
        if (realmGet$visitor != null) {
            Long l = map2.get(realmGet$visitor);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_common_VisitorBeanRealmProxy.insertOrUpdate(realm2, realmGet$visitor, map2));
            }
            Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.visitorColKey, j3, l.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, registerWithAppointmentResponseColumnInfo.visitorColKey, j3);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetDouble(j4, registerWithAppointmentResponseColumnInfo.beginColKey, j5, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$begin(), false);
        Table.nativeSetLong(j4, registerWithAppointmentResponseColumnInfo.proofNumColKey, j5, (long) com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$proofNum(), false);
        String realmGet$code = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$code();
        if (realmGet$code != null) {
            Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.codeColKey, j3, realmGet$code, false);
        } else {
            Table.nativeSetNull(nativePtr, registerWithAppointmentResponseColumnInfo.codeColKey, j3, false);
        }
        Settings realmGet$settings = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$settings();
        if (realmGet$settings != null) {
            Long l2 = map2.get(realmGet$settings);
            if (l2 == null) {
                l2 = Long.valueOf(com_ciot_realm_db_common_SettingsRealmProxy.insertOrUpdate(realm2, realmGet$settings, map2));
            }
            Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.settingsColKey, j3, l2.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, registerWithAppointmentResponseColumnInfo.settingsColKey, j3);
        }
        ValidateBean realmGet$validate = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$validate();
        if (realmGet$validate != null) {
            Long l3 = map2.get(realmGet$validate);
            if (l3 == null) {
                l3 = Long.valueOf(com_ciot_realm_db_common_ValidateBeanRealmProxy.insertOrUpdate(realm2, realmGet$validate, map2));
            }
            Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.validateColKey, j3, l3.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, registerWithAppointmentResponseColumnInfo.validateColKey, j3);
        }
        Table.nativeSetDouble(nativePtr, registerWithAppointmentResponseColumnInfo.endColKey, j3, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$end(), false);
        String realmGet$sms = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$sms();
        if (realmGet$sms != null) {
            Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.smsColKey, j3, realmGet$sms, false);
        } else {
            Table.nativeSetNull(nativePtr, registerWithAppointmentResponseColumnInfo.smsColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, registerWithAppointmentResponseColumnInfo.typeColKey, j3, (long) com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$type(), false);
        EmployeeBean realmGet$employee = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$employee();
        if (realmGet$employee != null) {
            Long l4 = map2.get(realmGet$employee);
            if (l4 == null) {
                l4 = Long.valueOf(com_ciot_realm_db_EmployeeBeanRealmProxy.insertOrUpdate(realm2, realmGet$employee, map2));
            }
            Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.employeeColKey, j3, l4.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, registerWithAppointmentResponseColumnInfo.employeeColKey, j3);
        }
        String realmGet$description = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.descriptionColKey, j3, realmGet$description, false);
        } else {
            Table.nativeSetNull(nativePtr, registerWithAppointmentResponseColumnInfo.descriptionColKey, j3, false);
        }
        String realmGet$status = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.statusColKey, j3, realmGet$status, false);
        } else {
            Table.nativeSetNull(nativePtr, registerWithAppointmentResponseColumnInfo.statusColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, registerWithAppointmentResponseColumnInfo.createtimeColKey, j3, (long) com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$createtime(), false);
        CompanyResponse realmGet$company = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$company();
        if (realmGet$company != null) {
            Long l5 = map2.get(realmGet$company);
            if (l5 == null) {
                l5 = Long.valueOf(com_ciot_realm_db_common_CompanyResponseRealmProxy.insertOrUpdate(realm2, realmGet$company, map2));
            }
            Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.companyColKey, j3, l5.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, registerWithAppointmentResponseColumnInfo.companyColKey, j3);
        }
        AckBean realmGet$ack = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$ack();
        if (realmGet$ack != null) {
            Long l6 = map2.get(realmGet$ack);
            if (l6 == null) {
                l6 = Long.valueOf(com_ciot_realm_db_common_AckBeanRealmProxy.insertOrUpdate(realm2, realmGet$ack, map2));
            }
            Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.ackColKey, j3, l6.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, registerWithAppointmentResponseColumnInfo.ackColKey, j3);
        }
        String realmGet$auditnotes = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$auditnotes();
        if (realmGet$auditnotes != null) {
            Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.auditnotesColKey, j3, realmGet$auditnotes, false);
        } else {
            Table.nativeSetNull(nativePtr, registerWithAppointmentResponseColumnInfo.auditnotesColKey, j3, false);
        }
        LockMode realmGet$lockmode = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$lockmode();
        if (realmGet$lockmode != null) {
            Long l7 = map2.get(realmGet$lockmode);
            if (l7 == null) {
                l7 = Long.valueOf(com_ciot_realm_db_LockModeRealmProxy.insertOrUpdate(realm2, realmGet$lockmode, map2));
            }
            Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.lockmodeColKey, j3, l7.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, registerWithAppointmentResponseColumnInfo.lockmodeColKey, j3);
        }
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(RegisterWithAppointmentResponse.class);
        long nativePtr = table.getNativePtr();
        RegisterWithAppointmentResponseColumnInfo registerWithAppointmentResponseColumnInfo = (RegisterWithAppointmentResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RegisterWithAppointmentResponse.class);
        long j3 = registerWithAppointmentResponseColumnInfo.idColKey;
        while (it.hasNext()) {
            RegisterWithAppointmentResponse registerWithAppointmentResponse = (RegisterWithAppointmentResponse) it.next();
            if (!map2.containsKey(registerWithAppointmentResponse)) {
                if ((registerWithAppointmentResponse instanceof RealmObjectProxy) && !RealmObject.isFrozen(registerWithAppointmentResponse)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) registerWithAppointmentResponse;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(registerWithAppointmentResponse, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxyInterface com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface = registerWithAppointmentResponse;
                String realmGet$id = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j3);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j3, realmGet$id);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j3, realmGet$id) : j;
                map2.put(registerWithAppointmentResponse, Long.valueOf(createRowWithPrimaryKey));
                VisitorBean realmGet$visitor = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$visitor();
                if (realmGet$visitor != null) {
                    Long l = map2.get(realmGet$visitor);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_common_VisitorBeanRealmProxy.insertOrUpdate(realm2, realmGet$visitor, map2));
                    }
                    j2 = j3;
                    Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.visitorColKey, createRowWithPrimaryKey, l.longValue(), false);
                } else {
                    j2 = j3;
                    Table.nativeNullifyLink(nativePtr, registerWithAppointmentResponseColumnInfo.visitorColKey, createRowWithPrimaryKey);
                }
                long j4 = createRowWithPrimaryKey;
                Table.nativeSetDouble(nativePtr, registerWithAppointmentResponseColumnInfo.beginColKey, j4, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$begin(), false);
                Table.nativeSetLong(nativePtr, registerWithAppointmentResponseColumnInfo.proofNumColKey, j4, (long) com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$proofNum(), false);
                String realmGet$code = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$code();
                if (realmGet$code != null) {
                    Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.codeColKey, createRowWithPrimaryKey, realmGet$code, false);
                } else {
                    Table.nativeSetNull(nativePtr, registerWithAppointmentResponseColumnInfo.codeColKey, createRowWithPrimaryKey, false);
                }
                Settings realmGet$settings = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$settings();
                if (realmGet$settings != null) {
                    Long l2 = map2.get(realmGet$settings);
                    if (l2 == null) {
                        l2 = Long.valueOf(com_ciot_realm_db_common_SettingsRealmProxy.insertOrUpdate(realm2, realmGet$settings, map2));
                    }
                    Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.settingsColKey, createRowWithPrimaryKey, l2.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, registerWithAppointmentResponseColumnInfo.settingsColKey, createRowWithPrimaryKey);
                }
                ValidateBean realmGet$validate = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$validate();
                if (realmGet$validate != null) {
                    Long l3 = map2.get(realmGet$validate);
                    if (l3 == null) {
                        l3 = Long.valueOf(com_ciot_realm_db_common_ValidateBeanRealmProxy.insertOrUpdate(realm2, realmGet$validate, map2));
                    }
                    Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.validateColKey, createRowWithPrimaryKey, l3.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, registerWithAppointmentResponseColumnInfo.validateColKey, createRowWithPrimaryKey);
                }
                Table.nativeSetDouble(nativePtr, registerWithAppointmentResponseColumnInfo.endColKey, createRowWithPrimaryKey, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$end(), false);
                String realmGet$sms = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$sms();
                if (realmGet$sms != null) {
                    Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.smsColKey, createRowWithPrimaryKey, realmGet$sms, false);
                } else {
                    Table.nativeSetNull(nativePtr, registerWithAppointmentResponseColumnInfo.smsColKey, createRowWithPrimaryKey, false);
                }
                Table.nativeSetLong(nativePtr, registerWithAppointmentResponseColumnInfo.typeColKey, createRowWithPrimaryKey, (long) com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$type(), false);
                EmployeeBean realmGet$employee = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$employee();
                if (realmGet$employee != null) {
                    Long l4 = map2.get(realmGet$employee);
                    if (l4 == null) {
                        l4 = Long.valueOf(com_ciot_realm_db_EmployeeBeanRealmProxy.insertOrUpdate(realm2, realmGet$employee, map2));
                    }
                    Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.employeeColKey, createRowWithPrimaryKey, l4.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, registerWithAppointmentResponseColumnInfo.employeeColKey, createRowWithPrimaryKey);
                }
                String realmGet$description = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.descriptionColKey, createRowWithPrimaryKey, realmGet$description, false);
                } else {
                    Table.nativeSetNull(nativePtr, registerWithAppointmentResponseColumnInfo.descriptionColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$status = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$status();
                if (realmGet$status != null) {
                    Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.statusColKey, createRowWithPrimaryKey, realmGet$status, false);
                } else {
                    Table.nativeSetNull(nativePtr, registerWithAppointmentResponseColumnInfo.statusColKey, createRowWithPrimaryKey, false);
                }
                Table.nativeSetLong(nativePtr, registerWithAppointmentResponseColumnInfo.createtimeColKey, createRowWithPrimaryKey, (long) com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$createtime(), false);
                CompanyResponse realmGet$company = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$company();
                if (realmGet$company != null) {
                    Long l5 = map2.get(realmGet$company);
                    if (l5 == null) {
                        l5 = Long.valueOf(com_ciot_realm_db_common_CompanyResponseRealmProxy.insertOrUpdate(realm2, realmGet$company, map2));
                    }
                    Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.companyColKey, createRowWithPrimaryKey, l5.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, registerWithAppointmentResponseColumnInfo.companyColKey, createRowWithPrimaryKey);
                }
                AckBean realmGet$ack = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$ack();
                if (realmGet$ack != null) {
                    Long l6 = map2.get(realmGet$ack);
                    if (l6 == null) {
                        l6 = Long.valueOf(com_ciot_realm_db_common_AckBeanRealmProxy.insertOrUpdate(realm2, realmGet$ack, map2));
                    }
                    Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.ackColKey, createRowWithPrimaryKey, l6.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, registerWithAppointmentResponseColumnInfo.ackColKey, createRowWithPrimaryKey);
                }
                String realmGet$auditnotes = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$auditnotes();
                if (realmGet$auditnotes != null) {
                    Table.nativeSetString(nativePtr, registerWithAppointmentResponseColumnInfo.auditnotesColKey, createRowWithPrimaryKey, realmGet$auditnotes, false);
                } else {
                    Table.nativeSetNull(nativePtr, registerWithAppointmentResponseColumnInfo.auditnotesColKey, createRowWithPrimaryKey, false);
                }
                LockMode realmGet$lockmode = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmGet$lockmode();
                if (realmGet$lockmode != null) {
                    Long l7 = map2.get(realmGet$lockmode);
                    if (l7 == null) {
                        l7 = Long.valueOf(com_ciot_realm_db_LockModeRealmProxy.insertOrUpdate(realm2, realmGet$lockmode, map2));
                    }
                    Table.nativeSetLink(nativePtr, registerWithAppointmentResponseColumnInfo.lockmodeColKey, createRowWithPrimaryKey, l7.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, registerWithAppointmentResponseColumnInfo.lockmodeColKey, createRowWithPrimaryKey);
                }
                j3 = j2;
            }
        }
    }

    public static RegisterWithAppointmentResponse createDetachedCopy(RegisterWithAppointmentResponse registerWithAppointmentResponse, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        RegisterWithAppointmentResponse registerWithAppointmentResponse2;
        if (i > i2 || registerWithAppointmentResponse == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(registerWithAppointmentResponse);
        if (cacheData == null) {
            registerWithAppointmentResponse2 = new RegisterWithAppointmentResponse();
            map.put(registerWithAppointmentResponse, new RealmObjectProxy.CacheData(i, registerWithAppointmentResponse2));
        } else if (i >= cacheData.minDepth) {
            return (RegisterWithAppointmentResponse) cacheData.object;
        } else {
            cacheData.minDepth = i;
            registerWithAppointmentResponse2 = (RegisterWithAppointmentResponse) cacheData.object;
        }
        com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxyInterface com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface = registerWithAppointmentResponse2;
        com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxyInterface com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2 = registerWithAppointmentResponse;
        int i3 = i + 1;
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$visitor(com_ciot_realm_db_common_VisitorBeanRealmProxy.createDetachedCopy(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$visitor(), i3, i2, map));
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$begin(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$begin());
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$proofNum(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$proofNum());
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$code(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$code());
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$settings(com_ciot_realm_db_common_SettingsRealmProxy.createDetachedCopy(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$settings(), i3, i2, map));
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$validate(com_ciot_realm_db_common_ValidateBeanRealmProxy.createDetachedCopy(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$validate(), i3, i2, map));
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$end(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$end());
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$sms(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$sms());
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$type(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$type());
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$employee(com_ciot_realm_db_EmployeeBeanRealmProxy.createDetachedCopy(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$employee(), i3, i2, map));
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$description(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$description());
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$status(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$status());
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$createtime(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$createtime());
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$company(com_ciot_realm_db_common_CompanyResponseRealmProxy.createDetachedCopy(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$company(), i3, i2, map));
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$ack(com_ciot_realm_db_common_AckBeanRealmProxy.createDetachedCopy(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$ack(), i3, i2, map));
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$auditnotes(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$auditnotes());
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$lockmode(com_ciot_realm_db_LockModeRealmProxy.createDetachedCopy(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$lockmode(), i3, i2, map));
        com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface.realmSet$id(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$id());
        return registerWithAppointmentResponse2;
    }

    static RegisterWithAppointmentResponse update(Realm realm, RegisterWithAppointmentResponseColumnInfo registerWithAppointmentResponseColumnInfo, RegisterWithAppointmentResponse registerWithAppointmentResponse, RegisterWithAppointmentResponse registerWithAppointmentResponse2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxyInterface com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface = registerWithAppointmentResponse;
        com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxyInterface com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2 = registerWithAppointmentResponse2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(RegisterWithAppointmentResponse.class), set);
        VisitorBean realmGet$visitor = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$visitor();
        if (realmGet$visitor == null) {
            osObjectBuilder.addNull(registerWithAppointmentResponseColumnInfo.visitorColKey);
        } else {
            VisitorBean visitorBean = (VisitorBean) map.get(realmGet$visitor);
            if (visitorBean != null) {
                osObjectBuilder.addObject(registerWithAppointmentResponseColumnInfo.visitorColKey, visitorBean);
            } else {
                osObjectBuilder.addObject(registerWithAppointmentResponseColumnInfo.visitorColKey, com_ciot_realm_db_common_VisitorBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_VisitorBeanRealmProxy.VisitorBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) VisitorBean.class), realmGet$visitor, true, map, set));
            }
        }
        osObjectBuilder.addDouble(registerWithAppointmentResponseColumnInfo.beginColKey, Double.valueOf(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$begin()));
        osObjectBuilder.addInteger(registerWithAppointmentResponseColumnInfo.proofNumColKey, Integer.valueOf(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$proofNum()));
        osObjectBuilder.addString(registerWithAppointmentResponseColumnInfo.codeColKey, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$code());
        Settings realmGet$settings = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$settings();
        if (realmGet$settings == null) {
            osObjectBuilder.addNull(registerWithAppointmentResponseColumnInfo.settingsColKey);
        } else {
            Settings settings = (Settings) map.get(realmGet$settings);
            if (settings != null) {
                osObjectBuilder.addObject(registerWithAppointmentResponseColumnInfo.settingsColKey, settings);
            } else {
                osObjectBuilder.addObject(registerWithAppointmentResponseColumnInfo.settingsColKey, com_ciot_realm_db_common_SettingsRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_SettingsRealmProxy.SettingsColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Settings.class), realmGet$settings, true, map, set));
            }
        }
        ValidateBean realmGet$validate = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$validate();
        if (realmGet$validate == null) {
            osObjectBuilder.addNull(registerWithAppointmentResponseColumnInfo.validateColKey);
        } else {
            ValidateBean validateBean = (ValidateBean) map.get(realmGet$validate);
            if (validateBean != null) {
                osObjectBuilder.addObject(registerWithAppointmentResponseColumnInfo.validateColKey, validateBean);
            } else {
                osObjectBuilder.addObject(registerWithAppointmentResponseColumnInfo.validateColKey, com_ciot_realm_db_common_ValidateBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_ValidateBeanRealmProxy.ValidateBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ValidateBean.class), realmGet$validate, true, map, set));
            }
        }
        osObjectBuilder.addDouble(registerWithAppointmentResponseColumnInfo.endColKey, Double.valueOf(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$end()));
        osObjectBuilder.addString(registerWithAppointmentResponseColumnInfo.smsColKey, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$sms());
        osObjectBuilder.addInteger(registerWithAppointmentResponseColumnInfo.typeColKey, Integer.valueOf(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$type()));
        EmployeeBean realmGet$employee = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$employee();
        if (realmGet$employee == null) {
            osObjectBuilder.addNull(registerWithAppointmentResponseColumnInfo.employeeColKey);
        } else {
            EmployeeBean employeeBean = (EmployeeBean) map.get(realmGet$employee);
            if (employeeBean != null) {
                osObjectBuilder.addObject(registerWithAppointmentResponseColumnInfo.employeeColKey, employeeBean);
            } else {
                osObjectBuilder.addObject(registerWithAppointmentResponseColumnInfo.employeeColKey, com_ciot_realm_db_EmployeeBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_EmployeeBeanRealmProxy.EmployeeBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EmployeeBean.class), realmGet$employee, true, map, set));
            }
        }
        osObjectBuilder.addString(registerWithAppointmentResponseColumnInfo.descriptionColKey, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$description());
        osObjectBuilder.addString(registerWithAppointmentResponseColumnInfo.statusColKey, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$status());
        osObjectBuilder.addInteger(registerWithAppointmentResponseColumnInfo.createtimeColKey, Integer.valueOf(com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$createtime()));
        CompanyResponse realmGet$company = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$company();
        if (realmGet$company == null) {
            osObjectBuilder.addNull(registerWithAppointmentResponseColumnInfo.companyColKey);
        } else {
            CompanyResponse companyResponse = (CompanyResponse) map.get(realmGet$company);
            if (companyResponse != null) {
                osObjectBuilder.addObject(registerWithAppointmentResponseColumnInfo.companyColKey, companyResponse);
            } else {
                osObjectBuilder.addObject(registerWithAppointmentResponseColumnInfo.companyColKey, com_ciot_realm_db_common_CompanyResponseRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_CompanyResponseRealmProxy.CompanyResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CompanyResponse.class), realmGet$company, true, map, set));
            }
        }
        AckBean realmGet$ack = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$ack();
        if (realmGet$ack == null) {
            osObjectBuilder.addNull(registerWithAppointmentResponseColumnInfo.ackColKey);
        } else {
            AckBean ackBean = (AckBean) map.get(realmGet$ack);
            if (ackBean != null) {
                osObjectBuilder.addObject(registerWithAppointmentResponseColumnInfo.ackColKey, ackBean);
            } else {
                osObjectBuilder.addObject(registerWithAppointmentResponseColumnInfo.ackColKey, com_ciot_realm_db_common_AckBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_common_AckBeanRealmProxy.AckBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AckBean.class), realmGet$ack, true, map, set));
            }
        }
        osObjectBuilder.addString(registerWithAppointmentResponseColumnInfo.auditnotesColKey, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$auditnotes());
        LockMode realmGet$lockmode = com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$lockmode();
        if (realmGet$lockmode == null) {
            osObjectBuilder.addNull(registerWithAppointmentResponseColumnInfo.lockmodeColKey);
        } else {
            LockMode lockMode = (LockMode) map.get(realmGet$lockmode);
            if (lockMode != null) {
                osObjectBuilder.addObject(registerWithAppointmentResponseColumnInfo.lockmodeColKey, lockMode);
            } else {
                osObjectBuilder.addObject(registerWithAppointmentResponseColumnInfo.lockmodeColKey, com_ciot_realm_db_LockModeRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_LockModeRealmProxy.LockModeColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) LockMode.class), realmGet$lockmode, true, map, set));
            }
        }
        osObjectBuilder.addString(registerWithAppointmentResponseColumnInfo.idColKey, com_ciot_realm_db_registerwithappointmentresponserealmproxyinterface2.realmGet$id());
        osObjectBuilder.updateExistingObject();
        return registerWithAppointmentResponse;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("RegisterWithAppointmentResponse = proxy[");
        sb.append("{visitor:");
        VisitorBean realmGet$visitor = realmGet$visitor();
        String str = Configurator.NULL;
        sb.append(realmGet$visitor != null ? com_ciot_realm_db_common_VisitorBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME : str);
        sb.append("}");
        sb.append(",");
        sb.append("{begin:");
        sb.append(realmGet$begin());
        sb.append("}");
        sb.append(",");
        sb.append("{proofNum:");
        sb.append(realmGet$proofNum());
        sb.append("}");
        sb.append(",");
        sb.append("{code:");
        sb.append(realmGet$code() != null ? realmGet$code() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{settings:");
        sb.append(realmGet$settings() != null ? com_ciot_realm_db_common_SettingsRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME : str);
        sb.append("}");
        sb.append(",");
        sb.append("{validate:");
        sb.append(realmGet$validate() != null ? com_ciot_realm_db_common_ValidateBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME : str);
        sb.append("}");
        sb.append(",");
        sb.append("{end:");
        sb.append(realmGet$end());
        sb.append("}");
        sb.append(",");
        sb.append("{sms:");
        sb.append(realmGet$sms() != null ? realmGet$sms() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{type:");
        sb.append(realmGet$type());
        sb.append("}");
        sb.append(",");
        sb.append("{employee:");
        sb.append(realmGet$employee() != null ? com_ciot_realm_db_EmployeeBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME : str);
        sb.append("}");
        sb.append(",");
        sb.append("{description:");
        sb.append(realmGet$description() != null ? realmGet$description() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{status:");
        sb.append(realmGet$status() != null ? realmGet$status() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{createtime:");
        sb.append(realmGet$createtime());
        sb.append("}");
        sb.append(",");
        sb.append("{company:");
        sb.append(realmGet$company() != null ? com_ciot_realm_db_common_CompanyResponseRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME : str);
        sb.append("}");
        sb.append(",");
        sb.append("{ack:");
        sb.append(realmGet$ack() != null ? com_ciot_realm_db_common_AckBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME : str);
        sb.append("}");
        sb.append(",");
        sb.append("{auditnotes:");
        sb.append(realmGet$auditnotes() != null ? realmGet$auditnotes() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{lockmode:");
        sb.append(realmGet$lockmode() != null ? com_ciot_realm_db_LockModeRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME : str);
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
        com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy com_ciot_realm_db_registerwithappointmentresponserealmproxy = (com_ciot_realm_db_RegisterWithAppointmentResponseRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_registerwithappointmentresponserealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_registerwithappointmentresponserealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_registerwithappointmentresponserealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
