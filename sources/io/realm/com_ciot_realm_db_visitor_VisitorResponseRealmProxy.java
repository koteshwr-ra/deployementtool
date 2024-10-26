package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import androidx.core.app.NotificationCompat;
import com.ciot.realm.db.visitor.VisitorResponse;
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

public class com_ciot_realm_db_visitor_VisitorResponseRealmProxy extends VisitorResponse implements RealmObjectProxy, com_ciot_realm_db_visitor_VisitorResponseRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private VisitorResponseColumnInfo columnInfo;
    private ProxyState<VisitorResponse> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "VisitorResponse";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class VisitorResponseColumnInfo extends ColumnInfo {
        long arcexresultColKey;
        long birthPromptColKey;
        long byfea_hr31ColKey;
        long checkColKey;
        long companyColKey;
        long createtimeColKey;
        long descriptionColKey;
        long emailColKey;
        long faceColKey;
        long hasfea_stColKey;
        long idColKey;
        long idcardColKey;
        long isHasfea_hrColKey;
        long nameColKey;
        long pathColKey;
        long phoneColKey;
        long promptColKey;
        long sexColKey;
        long stfea_hr31ColKey;
        long stfea_stColKey;
        long stresultColKey;
        long typeColKey;
        long uuidColKey;
        long vipPromptColKey;
        long wechatColKey;

        VisitorResponseColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(25);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
            this.companyColKey = addColumnDetails("company", "company", objectSchemaInfo);
            this.phoneColKey = addColumnDetails("phone", "phone", objectSchemaInfo);
            this.idcardColKey = addColumnDetails("idcard", "idcard", objectSchemaInfo);
            this.typeColKey = addColumnDetails("type", "type", objectSchemaInfo);
            this.sexColKey = addColumnDetails("sex", "sex", objectSchemaInfo);
            this.faceColKey = addColumnDetails("face", "face", objectSchemaInfo);
            this.emailColKey = addColumnDetails(NotificationCompat.CATEGORY_EMAIL, NotificationCompat.CATEGORY_EMAIL, objectSchemaInfo);
            this.wechatColKey = addColumnDetails("wechat", "wechat", objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
            this.birthPromptColKey = addColumnDetails("birthPrompt", "birthPrompt", objectSchemaInfo);
            this.vipPromptColKey = addColumnDetails("vipPrompt", "vipPrompt", objectSchemaInfo);
            this.promptColKey = addColumnDetails("prompt", "prompt", objectSchemaInfo);
            this.checkColKey = addColumnDetails("check", "check", objectSchemaInfo);
            this.createtimeColKey = addColumnDetails("createtime", "createtime", objectSchemaInfo);
            this.pathColKey = addColumnDetails("path", "path", objectSchemaInfo);
            this.stfea_stColKey = addColumnDetails("stfea_st", "stfea_st", objectSchemaInfo);
            this.stresultColKey = addColumnDetails("stresult", "stresult", objectSchemaInfo);
            this.uuidColKey = addColumnDetails("uuid", "uuid", objectSchemaInfo);
            this.hasfea_stColKey = addColumnDetails("hasfea_st", "hasfea_st", objectSchemaInfo);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.isHasfea_hrColKey = addColumnDetails("isHasfea_hr", "isHasfea_hr", objectSchemaInfo);
            this.stfea_hr31ColKey = addColumnDetails("stfea_hr31", "stfea_hr31", objectSchemaInfo);
            this.byfea_hr31ColKey = addColumnDetails("byfea_hr31", "byfea_hr31", objectSchemaInfo);
            this.arcexresultColKey = addColumnDetails("arcexresult", "arcexresult", objectSchemaInfo);
        }

        VisitorResponseColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new VisitorResponseColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            VisitorResponseColumnInfo visitorResponseColumnInfo = (VisitorResponseColumnInfo) columnInfo;
            VisitorResponseColumnInfo visitorResponseColumnInfo2 = (VisitorResponseColumnInfo) columnInfo2;
            visitorResponseColumnInfo2.nameColKey = visitorResponseColumnInfo.nameColKey;
            visitorResponseColumnInfo2.companyColKey = visitorResponseColumnInfo.companyColKey;
            visitorResponseColumnInfo2.phoneColKey = visitorResponseColumnInfo.phoneColKey;
            visitorResponseColumnInfo2.idcardColKey = visitorResponseColumnInfo.idcardColKey;
            visitorResponseColumnInfo2.typeColKey = visitorResponseColumnInfo.typeColKey;
            visitorResponseColumnInfo2.sexColKey = visitorResponseColumnInfo.sexColKey;
            visitorResponseColumnInfo2.faceColKey = visitorResponseColumnInfo.faceColKey;
            visitorResponseColumnInfo2.emailColKey = visitorResponseColumnInfo.emailColKey;
            visitorResponseColumnInfo2.wechatColKey = visitorResponseColumnInfo.wechatColKey;
            visitorResponseColumnInfo2.descriptionColKey = visitorResponseColumnInfo.descriptionColKey;
            visitorResponseColumnInfo2.birthPromptColKey = visitorResponseColumnInfo.birthPromptColKey;
            visitorResponseColumnInfo2.vipPromptColKey = visitorResponseColumnInfo.vipPromptColKey;
            visitorResponseColumnInfo2.promptColKey = visitorResponseColumnInfo.promptColKey;
            visitorResponseColumnInfo2.checkColKey = visitorResponseColumnInfo.checkColKey;
            visitorResponseColumnInfo2.createtimeColKey = visitorResponseColumnInfo.createtimeColKey;
            visitorResponseColumnInfo2.pathColKey = visitorResponseColumnInfo.pathColKey;
            visitorResponseColumnInfo2.stfea_stColKey = visitorResponseColumnInfo.stfea_stColKey;
            visitorResponseColumnInfo2.stresultColKey = visitorResponseColumnInfo.stresultColKey;
            visitorResponseColumnInfo2.uuidColKey = visitorResponseColumnInfo.uuidColKey;
            visitorResponseColumnInfo2.hasfea_stColKey = visitorResponseColumnInfo.hasfea_stColKey;
            visitorResponseColumnInfo2.idColKey = visitorResponseColumnInfo.idColKey;
            visitorResponseColumnInfo2.isHasfea_hrColKey = visitorResponseColumnInfo.isHasfea_hrColKey;
            visitorResponseColumnInfo2.stfea_hr31ColKey = visitorResponseColumnInfo.stfea_hr31ColKey;
            visitorResponseColumnInfo2.byfea_hr31ColKey = visitorResponseColumnInfo.byfea_hr31ColKey;
            visitorResponseColumnInfo2.arcexresultColKey = visitorResponseColumnInfo.arcexresultColKey;
        }
    }

    com_ciot_realm_db_visitor_VisitorResponseRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (VisitorResponseColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<VisitorResponse> proxyState2 = new ProxyState<>(this);
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

    public String realmGet$check() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.checkColKey);
    }

    public void realmSet$check(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.checkColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.checkColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.checkColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.checkColKey, row$realm.getObjectKey(), str, true);
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

    public int realmGet$stresult() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.stresultColKey);
    }

    public void realmSet$stresult(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.stresultColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.stresultColKey, row$realm.getObjectKey(), (long) i, true);
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

    public boolean realmGet$hasfea_st() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.hasfea_stColKey);
    }

    public void realmSet$hasfea_st(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.hasfea_stColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.hasfea_stColKey, row$realm.getObjectKey(), z, true);
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
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 25, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("company", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("phone", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("idcard", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("type", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("sex", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("face", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(NotificationCompat.CATEGORY_EMAIL, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("wechat", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("birthPrompt", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("vipPrompt", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("prompt", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("check", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("createtime", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("path", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("stfea_st", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("stresult", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("uuid", RealmFieldType.INTEGER, false, false, false);
        builder2.addPersistedProperty("hasfea_st", RealmFieldType.BOOLEAN, false, false, true);
        builder2.addPersistedProperty("id", RealmFieldType.STRING, true, false, false);
        builder2.addPersistedProperty("isHasfea_hr", RealmFieldType.BOOLEAN, false, false, true);
        builder2.addPersistedProperty("stfea_hr31", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("byfea_hr31", RealmFieldType.BINARY, false, false, false);
        builder2.addPersistedProperty("arcexresult", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static VisitorResponseColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new VisitorResponseColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v10, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v11, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x01d1  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01ea  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0203  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0221  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x023a  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0253  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0271  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x028e  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x02ac  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x02ce  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x02eb  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x030c  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x016d  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01b8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.visitor.VisitorResponse createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            java.lang.String r1 = "id"
            r2 = 0
            if (r14 == 0) goto L_0x0064
            java.lang.Class<com.ciot.realm.db.visitor.VisitorResponse> r14 = com.ciot.realm.db.visitor.VisitorResponse.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.visitor.VisitorResponse> r4 = com.ciot.realm.db.visitor.VisitorResponse.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxy$VisitorResponseColumnInfo r3 = (io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxy.VisitorResponseColumnInfo) r3
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
            java.lang.Class<com.ciot.realm.db.visitor.VisitorResponse> r3 = com.ciot.realm.db.visitor.VisitorResponse.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005f }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005f }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005f }
            io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxy r14 = new io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxy     // Catch:{ all -> 0x005f }
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
            java.lang.Class<com.ciot.realm.db.visitor.VisitorResponse> r14 = com.ciot.realm.db.visitor.VisitorResponse.class
            r1 = 1
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r1, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxy r14 = (io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxy) r14
            goto L_0x0095
        L_0x007e:
            java.lang.Class<com.ciot.realm.db.visitor.VisitorResponse> r14 = com.ciot.realm.db.visitor.VisitorResponse.class
            java.lang.String r1 = r13.getString(r1)
            r3 = 1
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxy r14 = (io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxy) r14
            goto L_0x0095
        L_0x008d:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'id'."
            r12.<init>(r13)
            throw r12
        L_0x0095:
            r12 = r14
            io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxyInterface) r12
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
            java.lang.String r0 = "company"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00ca
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00c3
            r12.realmSet$company(r2)
            goto L_0x00ca
        L_0x00c3:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$company(r0)
        L_0x00ca:
            java.lang.String r0 = "phone"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00e3
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00dc
            r12.realmSet$phone(r2)
            goto L_0x00e3
        L_0x00dc:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$phone(r0)
        L_0x00e3:
            java.lang.String r0 = "idcard"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00fc
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00f5
            r12.realmSet$idcard(r2)
            goto L_0x00fc
        L_0x00f5:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$idcard(r0)
        L_0x00fc:
            java.lang.String r0 = "type"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x011a
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0112
            int r0 = r13.getInt(r0)
            r12.realmSet$type(r0)
            goto L_0x011a
        L_0x0112:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'type' to null."
            r12.<init>(r13)
            throw r12
        L_0x011a:
            java.lang.String r0 = "sex"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0133
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x012c
            r12.realmSet$sex(r2)
            goto L_0x0133
        L_0x012c:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$sex(r0)
        L_0x0133:
            java.lang.String r0 = "face"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x014c
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0145
            r12.realmSet$face(r2)
            goto L_0x014c
        L_0x0145:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$face(r0)
        L_0x014c:
            java.lang.String r0 = "email"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0165
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x015e
            r12.realmSet$email(r2)
            goto L_0x0165
        L_0x015e:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$email(r0)
        L_0x0165:
            java.lang.String r0 = "wechat"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x017e
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0177
            r12.realmSet$wechat(r2)
            goto L_0x017e
        L_0x0177:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$wechat(r0)
        L_0x017e:
            java.lang.String r0 = "description"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0197
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0190
            r12.realmSet$description(r2)
            goto L_0x0197
        L_0x0190:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$description(r0)
        L_0x0197:
            java.lang.String r0 = "birthPrompt"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01b0
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01a9
            r12.realmSet$birthPrompt(r2)
            goto L_0x01b0
        L_0x01a9:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$birthPrompt(r0)
        L_0x01b0:
            java.lang.String r0 = "vipPrompt"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01c9
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01c2
            r12.realmSet$vipPrompt(r2)
            goto L_0x01c9
        L_0x01c2:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$vipPrompt(r0)
        L_0x01c9:
            java.lang.String r0 = "prompt"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01e2
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01db
            r12.realmSet$prompt(r2)
            goto L_0x01e2
        L_0x01db:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$prompt(r0)
        L_0x01e2:
            java.lang.String r0 = "check"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01fb
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01f4
            r12.realmSet$check(r2)
            goto L_0x01fb
        L_0x01f4:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$check(r0)
        L_0x01fb:
            java.lang.String r0 = "createtime"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0219
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0211
            long r0 = r13.getLong(r0)
            r12.realmSet$createtime(r0)
            goto L_0x0219
        L_0x0211:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'createtime' to null."
            r12.<init>(r13)
            throw r12
        L_0x0219:
            java.lang.String r0 = "path"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0232
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x022b
            r12.realmSet$path(r2)
            goto L_0x0232
        L_0x022b:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$path(r0)
        L_0x0232:
            java.lang.String r0 = "stfea_st"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x024b
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0244
            r12.realmSet$stfea_st(r2)
            goto L_0x024b
        L_0x0244:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$stfea_st(r0)
        L_0x024b:
            java.lang.String r0 = "stresult"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0269
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0261
            int r0 = r13.getInt(r0)
            r12.realmSet$stresult(r0)
            goto L_0x0269
        L_0x0261:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'stresult' to null."
            r12.<init>(r13)
            throw r12
        L_0x0269:
            java.lang.String r0 = "uuid"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0286
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x027b
            r12.realmSet$uuid(r2)
            goto L_0x0286
        L_0x027b:
            int r0 = r13.getInt(r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r12.realmSet$uuid(r0)
        L_0x0286:
            java.lang.String r0 = "hasfea_st"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x02a4
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x029c
            boolean r0 = r13.getBoolean(r0)
            r12.realmSet$hasfea_st(r0)
            goto L_0x02a4
        L_0x029c:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'hasfea_st' to null."
            r12.<init>(r13)
            throw r12
        L_0x02a4:
            java.lang.String r0 = "isHasfea_hr"
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L_0x02c6
            java.lang.String r0 = "isHasfea_hr"
            boolean r0 = r13.isNull(r0)
            if (r0 != 0) goto L_0x02be
            java.lang.String r0 = "isHasfea_hr"
            boolean r0 = r13.getBoolean(r0)
            r12.realmSet$isHasfea_hr(r0)
            goto L_0x02c6
        L_0x02be:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'isHasfea_hr' to null."
            r12.<init>(r13)
            throw r12
        L_0x02c6:
            java.lang.String r0 = "stfea_hr31"
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L_0x02e3
            java.lang.String r0 = "stfea_hr31"
            boolean r0 = r13.isNull(r0)
            if (r0 == 0) goto L_0x02da
            r12.realmSet$stfea_hr31(r2)
            goto L_0x02e3
        L_0x02da:
            java.lang.String r0 = "stfea_hr31"
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$stfea_hr31(r0)
        L_0x02e3:
            java.lang.String r0 = "byfea_hr31"
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L_0x0304
            java.lang.String r0 = "byfea_hr31"
            boolean r0 = r13.isNull(r0)
            if (r0 == 0) goto L_0x02f7
            r12.realmSet$byfea_hr31(r2)
            goto L_0x0304
        L_0x02f7:
            java.lang.String r0 = "byfea_hr31"
            java.lang.String r0 = r13.getString(r0)
            byte[] r0 = io.realm.internal.android.JsonUtils.stringToBytes(r0)
            r12.realmSet$byfea_hr31(r0)
        L_0x0304:
            java.lang.String r0 = "arcexresult"
            boolean r0 = r13.has(r0)
            if (r0 == 0) goto L_0x0326
            java.lang.String r0 = "arcexresult"
            boolean r0 = r13.isNull(r0)
            if (r0 != 0) goto L_0x031e
            java.lang.String r0 = "arcexresult"
            int r13 = r13.getInt(r0)
            r12.realmSet$arcexresult(r13)
            goto L_0x0326
        L_0x031e:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'arcexresult' to null."
            r12.<init>(r13)
            throw r12
        L_0x0326:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.visitor.VisitorResponse");
    }

    public static VisitorResponse createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        VisitorResponse visitorResponse = new VisitorResponse();
        com_ciot_realm_db_visitor_VisitorResponseRealmProxyInterface com_ciot_realm_db_visitor_visitorresponserealmproxyinterface = visitorResponse;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(ServiceProvider.NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$name(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$name((String) null);
                }
            } else if (nextName.equals("company")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$company(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$company((String) null);
                }
            } else if (nextName.equals("phone")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$phone(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$phone((String) null);
                }
            } else if (nextName.equals("idcard")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$idcard(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$idcard((String) null);
                }
            } else if (nextName.equals("type")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$type(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'type' to null.");
                }
            } else if (nextName.equals("sex")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$sex(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$sex((String) null);
                }
            } else if (nextName.equals("face")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$face(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$face((String) null);
                }
            } else if (nextName.equals(NotificationCompat.CATEGORY_EMAIL)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$email(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$email((String) null);
                }
            } else if (nextName.equals("wechat")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$wechat(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$wechat((String) null);
                }
            } else if (nextName.equals("description")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$description(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$description((String) null);
                }
            } else if (nextName.equals("birthPrompt")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$birthPrompt(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$birthPrompt((String) null);
                }
            } else if (nextName.equals("vipPrompt")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$vipPrompt(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$vipPrompt((String) null);
                }
            } else if (nextName.equals("prompt")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$prompt(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$prompt((String) null);
                }
            } else if (nextName.equals("check")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$check(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$check((String) null);
                }
            } else if (nextName.equals("createtime")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$createtime(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'createtime' to null.");
                }
            } else if (nextName.equals("path")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$path(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$path((String) null);
                }
            } else if (nextName.equals("stfea_st")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$stfea_st(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$stfea_st((String) null);
                }
            } else if (nextName.equals("stresult")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$stresult(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'stresult' to null.");
                }
            } else if (nextName.equals("uuid")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$uuid(Integer.valueOf(jsonReader.nextInt()));
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$uuid((Integer) null);
                }
            } else if (nextName.equals("hasfea_st")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$hasfea_st(jsonReader.nextBoolean());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'hasfea_st' to null.");
                }
            } else if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$id((String) null);
                }
                z = true;
            } else if (nextName.equals("isHasfea_hr")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$isHasfea_hr(jsonReader.nextBoolean());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isHasfea_hr' to null.");
                }
            } else if (nextName.equals("stfea_hr31")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$stfea_hr31(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$stfea_hr31((String) null);
                }
            } else if (nextName.equals("byfea_hr31")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$byfea_hr31(JsonUtils.stringToBytes(jsonReader.nextString()));
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$byfea_hr31((byte[]) null);
                }
            } else if (!nextName.equals("arcexresult")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$arcexresult(jsonReader.nextInt());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'arcexresult' to null.");
            }
        }
        jsonReader.endObject();
        if (z) {
            return (VisitorResponse) realm.copyToRealm(visitorResponse, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_visitor_VisitorResponseRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) VisitorResponse.class), false, Collections.emptyList());
        com_ciot_realm_db_visitor_VisitorResponseRealmProxy com_ciot_realm_db_visitor_visitorresponserealmproxy = new com_ciot_realm_db_visitor_VisitorResponseRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_visitor_visitorresponserealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.visitor.VisitorResponse copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxy.VisitorResponseColumnInfo r9, com.ciot.realm.db.visitor.VisitorResponse r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.visitor.VisitorResponse r1 = (com.ciot.realm.db.visitor.VisitorResponse) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.visitor.VisitorResponse> r2 = com.ciot.realm.db.visitor.VisitorResponse.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxyInterface) r5
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
            io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxy r1 = new io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.visitor.VisitorResponse r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.visitor.VisitorResponse r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxy$VisitorResponseColumnInfo, com.ciot.realm.db.visitor.VisitorResponse, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.visitor.VisitorResponse");
    }

    public static VisitorResponse copy(Realm realm, VisitorResponseColumnInfo visitorResponseColumnInfo, VisitorResponse visitorResponse, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(visitorResponse);
        if (realmObjectProxy != null) {
            return (VisitorResponse) realmObjectProxy;
        }
        com_ciot_realm_db_visitor_VisitorResponseRealmProxyInterface com_ciot_realm_db_visitor_visitorresponserealmproxyinterface = visitorResponse;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(VisitorResponse.class), set);
        osObjectBuilder.addString(visitorResponseColumnInfo.nameColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$name());
        osObjectBuilder.addString(visitorResponseColumnInfo.companyColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$company());
        osObjectBuilder.addString(visitorResponseColumnInfo.phoneColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$phone());
        osObjectBuilder.addString(visitorResponseColumnInfo.idcardColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$idcard());
        osObjectBuilder.addInteger(visitorResponseColumnInfo.typeColKey, Integer.valueOf(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$type()));
        osObjectBuilder.addString(visitorResponseColumnInfo.sexColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$sex());
        osObjectBuilder.addString(visitorResponseColumnInfo.faceColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$face());
        osObjectBuilder.addString(visitorResponseColumnInfo.emailColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$email());
        osObjectBuilder.addString(visitorResponseColumnInfo.wechatColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$wechat());
        osObjectBuilder.addString(visitorResponseColumnInfo.descriptionColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$description());
        osObjectBuilder.addString(visitorResponseColumnInfo.birthPromptColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$birthPrompt());
        osObjectBuilder.addString(visitorResponseColumnInfo.vipPromptColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$vipPrompt());
        osObjectBuilder.addString(visitorResponseColumnInfo.promptColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$prompt());
        osObjectBuilder.addString(visitorResponseColumnInfo.checkColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$check());
        osObjectBuilder.addInteger(visitorResponseColumnInfo.createtimeColKey, Long.valueOf(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$createtime()));
        osObjectBuilder.addString(visitorResponseColumnInfo.pathColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$path());
        osObjectBuilder.addString(visitorResponseColumnInfo.stfea_stColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$stfea_st());
        osObjectBuilder.addInteger(visitorResponseColumnInfo.stresultColKey, Integer.valueOf(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$stresult()));
        osObjectBuilder.addInteger(visitorResponseColumnInfo.uuidColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$uuid());
        osObjectBuilder.addBoolean(visitorResponseColumnInfo.hasfea_stColKey, Boolean.valueOf(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$hasfea_st()));
        osObjectBuilder.addString(visitorResponseColumnInfo.idColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$id());
        osObjectBuilder.addBoolean(visitorResponseColumnInfo.isHasfea_hrColKey, Boolean.valueOf(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$isHasfea_hr()));
        osObjectBuilder.addString(visitorResponseColumnInfo.stfea_hr31ColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$stfea_hr31());
        osObjectBuilder.addByteArray(visitorResponseColumnInfo.byfea_hr31ColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$byfea_hr31());
        osObjectBuilder.addInteger(visitorResponseColumnInfo.arcexresultColKey, Integer.valueOf(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$arcexresult()));
        com_ciot_realm_db_visitor_VisitorResponseRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(visitorResponse, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, VisitorResponse visitorResponse, Map<RealmModel, Long> map) {
        long j;
        VisitorResponse visitorResponse2 = visitorResponse;
        if ((visitorResponse2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(visitorResponse)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) visitorResponse2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(VisitorResponse.class);
        long nativePtr = table.getNativePtr();
        VisitorResponseColumnInfo visitorResponseColumnInfo = (VisitorResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) VisitorResponse.class);
        long j2 = visitorResponseColumnInfo.idColKey;
        com_ciot_realm_db_visitor_VisitorResponseRealmProxyInterface com_ciot_realm_db_visitor_visitorresponserealmproxyinterface = visitorResponse2;
        String realmGet$id = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$id();
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
        map.put(visitorResponse2, Long.valueOf(j3));
        String realmGet$name = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.nameColKey, j3, realmGet$name, false);
        }
        String realmGet$company = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$company();
        if (realmGet$company != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.companyColKey, j3, realmGet$company, false);
        }
        String realmGet$phone = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$phone();
        if (realmGet$phone != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.phoneColKey, j3, realmGet$phone, false);
        }
        String realmGet$idcard = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$idcard();
        if (realmGet$idcard != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.idcardColKey, j3, realmGet$idcard, false);
        }
        Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.typeColKey, j3, (long) com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$type(), false);
        String realmGet$sex = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$sex();
        if (realmGet$sex != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.sexColKey, j3, realmGet$sex, false);
        }
        String realmGet$face = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$face();
        if (realmGet$face != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.faceColKey, j3, realmGet$face, false);
        }
        String realmGet$email = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.emailColKey, j3, realmGet$email, false);
        }
        String realmGet$wechat = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$wechat();
        if (realmGet$wechat != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.wechatColKey, j3, realmGet$wechat, false);
        }
        String realmGet$description = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.descriptionColKey, j3, realmGet$description, false);
        }
        String realmGet$birthPrompt = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$birthPrompt();
        if (realmGet$birthPrompt != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.birthPromptColKey, j3, realmGet$birthPrompt, false);
        }
        String realmGet$vipPrompt = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$vipPrompt();
        if (realmGet$vipPrompt != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.vipPromptColKey, j3, realmGet$vipPrompt, false);
        }
        String realmGet$prompt = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$prompt();
        if (realmGet$prompt != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.promptColKey, j3, realmGet$prompt, false);
        }
        String realmGet$check = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$check();
        if (realmGet$check != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.checkColKey, j3, realmGet$check, false);
        }
        Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.createtimeColKey, j3, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$createtime(), false);
        String realmGet$path = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$path();
        if (realmGet$path != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.pathColKey, j3, realmGet$path, false);
        }
        String realmGet$stfea_st = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$stfea_st();
        if (realmGet$stfea_st != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.stfea_stColKey, j3, realmGet$stfea_st, false);
        }
        Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.stresultColKey, j3, (long) com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$stresult(), false);
        Integer realmGet$uuid = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$uuid();
        if (realmGet$uuid != null) {
            Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.uuidColKey, j3, realmGet$uuid.longValue(), false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetBoolean(j4, visitorResponseColumnInfo.hasfea_stColKey, j5, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$hasfea_st(), false);
        Table.nativeSetBoolean(j4, visitorResponseColumnInfo.isHasfea_hrColKey, j5, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$isHasfea_hr(), false);
        String realmGet$stfea_hr31 = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$stfea_hr31();
        if (realmGet$stfea_hr31 != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.stfea_hr31ColKey, j3, realmGet$stfea_hr31, false);
        }
        byte[] realmGet$byfea_hr31 = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$byfea_hr31();
        if (realmGet$byfea_hr31 != null) {
            Table.nativeSetByteArray(nativePtr, visitorResponseColumnInfo.byfea_hr31ColKey, j3, realmGet$byfea_hr31, false);
        }
        Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.arcexresultColKey, j3, (long) com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$arcexresult(), false);
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(VisitorResponse.class);
        long nativePtr = table.getNativePtr();
        VisitorResponseColumnInfo visitorResponseColumnInfo = (VisitorResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) VisitorResponse.class);
        long j4 = visitorResponseColumnInfo.idColKey;
        while (it.hasNext()) {
            VisitorResponse visitorResponse = (VisitorResponse) it.next();
            if (!map2.containsKey(visitorResponse)) {
                if ((visitorResponse instanceof RealmObjectProxy) && !RealmObject.isFrozen(visitorResponse)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) visitorResponse;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(visitorResponse, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_visitor_VisitorResponseRealmProxyInterface com_ciot_realm_db_visitor_visitorresponserealmproxyinterface = visitorResponse;
                String realmGet$id = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$id();
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
                map2.put(visitorResponse, Long.valueOf(j2));
                String realmGet$name = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    j3 = j4;
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.nameColKey, j2, realmGet$name, false);
                } else {
                    j3 = j4;
                }
                String realmGet$company = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$company();
                if (realmGet$company != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.companyColKey, j2, realmGet$company, false);
                }
                String realmGet$phone = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$phone();
                if (realmGet$phone != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.phoneColKey, j2, realmGet$phone, false);
                }
                String realmGet$idcard = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$idcard();
                if (realmGet$idcard != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.idcardColKey, j2, realmGet$idcard, false);
                }
                Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.typeColKey, j2, (long) com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$type(), false);
                String realmGet$sex = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$sex();
                if (realmGet$sex != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.sexColKey, j2, realmGet$sex, false);
                }
                String realmGet$face = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$face();
                if (realmGet$face != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.faceColKey, j2, realmGet$face, false);
                }
                String realmGet$email = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$email();
                if (realmGet$email != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.emailColKey, j2, realmGet$email, false);
                }
                String realmGet$wechat = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$wechat();
                if (realmGet$wechat != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.wechatColKey, j2, realmGet$wechat, false);
                }
                String realmGet$description = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.descriptionColKey, j2, realmGet$description, false);
                }
                String realmGet$birthPrompt = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$birthPrompt();
                if (realmGet$birthPrompt != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.birthPromptColKey, j2, realmGet$birthPrompt, false);
                }
                String realmGet$vipPrompt = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$vipPrompt();
                if (realmGet$vipPrompt != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.vipPromptColKey, j2, realmGet$vipPrompt, false);
                }
                String realmGet$prompt = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$prompt();
                if (realmGet$prompt != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.promptColKey, j2, realmGet$prompt, false);
                }
                String realmGet$check = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$check();
                if (realmGet$check != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.checkColKey, j2, realmGet$check, false);
                }
                Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.createtimeColKey, j2, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$createtime(), false);
                String realmGet$path = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$path();
                if (realmGet$path != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.pathColKey, j2, realmGet$path, false);
                }
                String realmGet$stfea_st = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$stfea_st();
                if (realmGet$stfea_st != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.stfea_stColKey, j2, realmGet$stfea_st, false);
                }
                Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.stresultColKey, j2, (long) com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$stresult(), false);
                Integer realmGet$uuid = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$uuid();
                if (realmGet$uuid != null) {
                    Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.uuidColKey, j2, realmGet$uuid.longValue(), false);
                }
                long j5 = nativePtr;
                long j6 = j2;
                Table.nativeSetBoolean(j5, visitorResponseColumnInfo.hasfea_stColKey, j6, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$hasfea_st(), false);
                Table.nativeSetBoolean(j5, visitorResponseColumnInfo.isHasfea_hrColKey, j6, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$isHasfea_hr(), false);
                String realmGet$stfea_hr31 = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$stfea_hr31();
                if (realmGet$stfea_hr31 != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.stfea_hr31ColKey, j2, realmGet$stfea_hr31, false);
                }
                byte[] realmGet$byfea_hr31 = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$byfea_hr31();
                if (realmGet$byfea_hr31 != null) {
                    Table.nativeSetByteArray(nativePtr, visitorResponseColumnInfo.byfea_hr31ColKey, j2, realmGet$byfea_hr31, false);
                }
                Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.arcexresultColKey, j2, (long) com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$arcexresult(), false);
                j4 = j3;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, VisitorResponse visitorResponse, Map<RealmModel, Long> map) {
        long j;
        VisitorResponse visitorResponse2 = visitorResponse;
        if ((visitorResponse2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(visitorResponse)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) visitorResponse2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(VisitorResponse.class);
        long nativePtr = table.getNativePtr();
        VisitorResponseColumnInfo visitorResponseColumnInfo = (VisitorResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) VisitorResponse.class);
        long j2 = visitorResponseColumnInfo.idColKey;
        com_ciot_realm_db_visitor_VisitorResponseRealmProxyInterface com_ciot_realm_db_visitor_visitorresponserealmproxyinterface = visitorResponse2;
        String realmGet$id = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$id);
        }
        long j3 = j;
        map.put(visitorResponse2, Long.valueOf(j3));
        String realmGet$name = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.nameColKey, j3, realmGet$name, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.nameColKey, j3, false);
        }
        String realmGet$company = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$company();
        if (realmGet$company != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.companyColKey, j3, realmGet$company, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.companyColKey, j3, false);
        }
        String realmGet$phone = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$phone();
        if (realmGet$phone != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.phoneColKey, j3, realmGet$phone, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.phoneColKey, j3, false);
        }
        String realmGet$idcard = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$idcard();
        if (realmGet$idcard != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.idcardColKey, j3, realmGet$idcard, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.idcardColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.typeColKey, j3, (long) com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$type(), false);
        String realmGet$sex = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$sex();
        if (realmGet$sex != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.sexColKey, j3, realmGet$sex, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.sexColKey, j3, false);
        }
        String realmGet$face = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$face();
        if (realmGet$face != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.faceColKey, j3, realmGet$face, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.faceColKey, j3, false);
        }
        String realmGet$email = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$email();
        if (realmGet$email != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.emailColKey, j3, realmGet$email, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.emailColKey, j3, false);
        }
        String realmGet$wechat = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$wechat();
        if (realmGet$wechat != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.wechatColKey, j3, realmGet$wechat, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.wechatColKey, j3, false);
        }
        String realmGet$description = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.descriptionColKey, j3, realmGet$description, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.descriptionColKey, j3, false);
        }
        String realmGet$birthPrompt = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$birthPrompt();
        if (realmGet$birthPrompt != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.birthPromptColKey, j3, realmGet$birthPrompt, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.birthPromptColKey, j3, false);
        }
        String realmGet$vipPrompt = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$vipPrompt();
        if (realmGet$vipPrompt != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.vipPromptColKey, j3, realmGet$vipPrompt, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.vipPromptColKey, j3, false);
        }
        String realmGet$prompt = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$prompt();
        if (realmGet$prompt != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.promptColKey, j3, realmGet$prompt, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.promptColKey, j3, false);
        }
        String realmGet$check = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$check();
        if (realmGet$check != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.checkColKey, j3, realmGet$check, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.checkColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.createtimeColKey, j3, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$createtime(), false);
        String realmGet$path = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$path();
        if (realmGet$path != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.pathColKey, j3, realmGet$path, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.pathColKey, j3, false);
        }
        String realmGet$stfea_st = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$stfea_st();
        if (realmGet$stfea_st != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.stfea_stColKey, j3, realmGet$stfea_st, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.stfea_stColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.stresultColKey, j3, (long) com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$stresult(), false);
        Integer realmGet$uuid = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$uuid();
        if (realmGet$uuid != null) {
            Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.uuidColKey, j3, realmGet$uuid.longValue(), false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.uuidColKey, j3, false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetBoolean(j4, visitorResponseColumnInfo.hasfea_stColKey, j5, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$hasfea_st(), false);
        Table.nativeSetBoolean(j4, visitorResponseColumnInfo.isHasfea_hrColKey, j5, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$isHasfea_hr(), false);
        String realmGet$stfea_hr31 = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$stfea_hr31();
        if (realmGet$stfea_hr31 != null) {
            Table.nativeSetString(nativePtr, visitorResponseColumnInfo.stfea_hr31ColKey, j3, realmGet$stfea_hr31, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.stfea_hr31ColKey, j3, false);
        }
        byte[] realmGet$byfea_hr31 = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$byfea_hr31();
        if (realmGet$byfea_hr31 != null) {
            Table.nativeSetByteArray(nativePtr, visitorResponseColumnInfo.byfea_hr31ColKey, j3, realmGet$byfea_hr31, false);
        } else {
            Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.byfea_hr31ColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.arcexresultColKey, j3, (long) com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$arcexresult(), false);
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(VisitorResponse.class);
        long nativePtr = table.getNativePtr();
        VisitorResponseColumnInfo visitorResponseColumnInfo = (VisitorResponseColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) VisitorResponse.class);
        long j3 = visitorResponseColumnInfo.idColKey;
        while (it.hasNext()) {
            VisitorResponse visitorResponse = (VisitorResponse) it.next();
            if (!map2.containsKey(visitorResponse)) {
                if ((visitorResponse instanceof RealmObjectProxy) && !RealmObject.isFrozen(visitorResponse)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) visitorResponse;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(visitorResponse, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_visitor_VisitorResponseRealmProxyInterface com_ciot_realm_db_visitor_visitorresponserealmproxyinterface = visitorResponse;
                String realmGet$id = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j3);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j3, realmGet$id);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j3, realmGet$id) : j;
                map2.put(visitorResponse, Long.valueOf(createRowWithPrimaryKey));
                String realmGet$name = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.nameColKey, createRowWithPrimaryKey, realmGet$name, false);
                } else {
                    j2 = j3;
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.nameColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$company = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$company();
                if (realmGet$company != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.companyColKey, createRowWithPrimaryKey, realmGet$company, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.companyColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$phone = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$phone();
                if (realmGet$phone != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.phoneColKey, createRowWithPrimaryKey, realmGet$phone, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.phoneColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$idcard = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$idcard();
                if (realmGet$idcard != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.idcardColKey, createRowWithPrimaryKey, realmGet$idcard, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.idcardColKey, createRowWithPrimaryKey, false);
                }
                Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.typeColKey, createRowWithPrimaryKey, (long) com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$type(), false);
                String realmGet$sex = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$sex();
                if (realmGet$sex != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.sexColKey, createRowWithPrimaryKey, realmGet$sex, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.sexColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$face = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$face();
                if (realmGet$face != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.faceColKey, createRowWithPrimaryKey, realmGet$face, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.faceColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$email = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$email();
                if (realmGet$email != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.emailColKey, createRowWithPrimaryKey, realmGet$email, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.emailColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$wechat = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$wechat();
                if (realmGet$wechat != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.wechatColKey, createRowWithPrimaryKey, realmGet$wechat, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.wechatColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$description = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.descriptionColKey, createRowWithPrimaryKey, realmGet$description, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.descriptionColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$birthPrompt = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$birthPrompt();
                if (realmGet$birthPrompt != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.birthPromptColKey, createRowWithPrimaryKey, realmGet$birthPrompt, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.birthPromptColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$vipPrompt = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$vipPrompt();
                if (realmGet$vipPrompt != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.vipPromptColKey, createRowWithPrimaryKey, realmGet$vipPrompt, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.vipPromptColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$prompt = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$prompt();
                if (realmGet$prompt != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.promptColKey, createRowWithPrimaryKey, realmGet$prompt, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.promptColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$check = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$check();
                if (realmGet$check != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.checkColKey, createRowWithPrimaryKey, realmGet$check, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.checkColKey, createRowWithPrimaryKey, false);
                }
                Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.createtimeColKey, createRowWithPrimaryKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$createtime(), false);
                String realmGet$path = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$path();
                if (realmGet$path != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.pathColKey, createRowWithPrimaryKey, realmGet$path, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.pathColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$stfea_st = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$stfea_st();
                if (realmGet$stfea_st != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.stfea_stColKey, createRowWithPrimaryKey, realmGet$stfea_st, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.stfea_stColKey, createRowWithPrimaryKey, false);
                }
                Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.stresultColKey, createRowWithPrimaryKey, (long) com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$stresult(), false);
                Integer realmGet$uuid = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$uuid();
                if (realmGet$uuid != null) {
                    Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.uuidColKey, createRowWithPrimaryKey, realmGet$uuid.longValue(), false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.uuidColKey, createRowWithPrimaryKey, false);
                }
                long j4 = nativePtr;
                long j5 = createRowWithPrimaryKey;
                Table.nativeSetBoolean(j4, visitorResponseColumnInfo.hasfea_stColKey, j5, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$hasfea_st(), false);
                Table.nativeSetBoolean(j4, visitorResponseColumnInfo.isHasfea_hrColKey, j5, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$isHasfea_hr(), false);
                String realmGet$stfea_hr31 = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$stfea_hr31();
                if (realmGet$stfea_hr31 != null) {
                    Table.nativeSetString(nativePtr, visitorResponseColumnInfo.stfea_hr31ColKey, createRowWithPrimaryKey, realmGet$stfea_hr31, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.stfea_hr31ColKey, createRowWithPrimaryKey, false);
                }
                byte[] realmGet$byfea_hr31 = com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$byfea_hr31();
                if (realmGet$byfea_hr31 != null) {
                    Table.nativeSetByteArray(nativePtr, visitorResponseColumnInfo.byfea_hr31ColKey, createRowWithPrimaryKey, realmGet$byfea_hr31, false);
                } else {
                    Table.nativeSetNull(nativePtr, visitorResponseColumnInfo.byfea_hr31ColKey, createRowWithPrimaryKey, false);
                }
                Table.nativeSetLong(nativePtr, visitorResponseColumnInfo.arcexresultColKey, createRowWithPrimaryKey, (long) com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmGet$arcexresult(), false);
                j3 = j2;
            }
        }
    }

    public static VisitorResponse createDetachedCopy(VisitorResponse visitorResponse, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        VisitorResponse visitorResponse2;
        if (i > i2 || visitorResponse == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(visitorResponse);
        if (cacheData == null) {
            visitorResponse2 = new VisitorResponse();
            map.put(visitorResponse, new RealmObjectProxy.CacheData(i, visitorResponse2));
        } else if (i >= cacheData.minDepth) {
            return (VisitorResponse) cacheData.object;
        } else {
            cacheData.minDepth = i;
            visitorResponse2 = (VisitorResponse) cacheData.object;
        }
        com_ciot_realm_db_visitor_VisitorResponseRealmProxyInterface com_ciot_realm_db_visitor_visitorresponserealmproxyinterface = visitorResponse2;
        com_ciot_realm_db_visitor_VisitorResponseRealmProxyInterface com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2 = visitorResponse;
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$name(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$name());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$company(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$company());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$phone(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$phone());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$idcard(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$idcard());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$type(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$type());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$sex(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$sex());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$face(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$face());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$email(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$email());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$wechat(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$wechat());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$description(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$description());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$birthPrompt(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$birthPrompt());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$vipPrompt(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$vipPrompt());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$prompt(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$prompt());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$check(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$check());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$createtime(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$createtime());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$path(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$path());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$stfea_st(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$stfea_st());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$stresult(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$stresult());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$uuid(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$uuid());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$hasfea_st(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$hasfea_st());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$id(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$isHasfea_hr(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$isHasfea_hr());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$stfea_hr31(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$stfea_hr31());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$byfea_hr31(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$byfea_hr31());
        com_ciot_realm_db_visitor_visitorresponserealmproxyinterface.realmSet$arcexresult(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$arcexresult());
        return visitorResponse2;
    }

    static VisitorResponse update(Realm realm, VisitorResponseColumnInfo visitorResponseColumnInfo, VisitorResponse visitorResponse, VisitorResponse visitorResponse2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_visitor_VisitorResponseRealmProxyInterface com_ciot_realm_db_visitor_visitorresponserealmproxyinterface = visitorResponse;
        com_ciot_realm_db_visitor_VisitorResponseRealmProxyInterface com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2 = visitorResponse2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(VisitorResponse.class), set);
        osObjectBuilder.addString(visitorResponseColumnInfo.nameColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$name());
        osObjectBuilder.addString(visitorResponseColumnInfo.companyColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$company());
        osObjectBuilder.addString(visitorResponseColumnInfo.phoneColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$phone());
        osObjectBuilder.addString(visitorResponseColumnInfo.idcardColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$idcard());
        osObjectBuilder.addInteger(visitorResponseColumnInfo.typeColKey, Integer.valueOf(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$type()));
        osObjectBuilder.addString(visitorResponseColumnInfo.sexColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$sex());
        osObjectBuilder.addString(visitorResponseColumnInfo.faceColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$face());
        osObjectBuilder.addString(visitorResponseColumnInfo.emailColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$email());
        osObjectBuilder.addString(visitorResponseColumnInfo.wechatColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$wechat());
        osObjectBuilder.addString(visitorResponseColumnInfo.descriptionColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$description());
        osObjectBuilder.addString(visitorResponseColumnInfo.birthPromptColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$birthPrompt());
        osObjectBuilder.addString(visitorResponseColumnInfo.vipPromptColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$vipPrompt());
        osObjectBuilder.addString(visitorResponseColumnInfo.promptColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$prompt());
        osObjectBuilder.addString(visitorResponseColumnInfo.checkColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$check());
        osObjectBuilder.addInteger(visitorResponseColumnInfo.createtimeColKey, Long.valueOf(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$createtime()));
        osObjectBuilder.addString(visitorResponseColumnInfo.pathColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$path());
        osObjectBuilder.addString(visitorResponseColumnInfo.stfea_stColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$stfea_st());
        osObjectBuilder.addInteger(visitorResponseColumnInfo.stresultColKey, Integer.valueOf(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$stresult()));
        osObjectBuilder.addInteger(visitorResponseColumnInfo.uuidColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$uuid());
        osObjectBuilder.addBoolean(visitorResponseColumnInfo.hasfea_stColKey, Boolean.valueOf(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$hasfea_st()));
        osObjectBuilder.addString(visitorResponseColumnInfo.idColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$id());
        osObjectBuilder.addBoolean(visitorResponseColumnInfo.isHasfea_hrColKey, Boolean.valueOf(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$isHasfea_hr()));
        osObjectBuilder.addString(visitorResponseColumnInfo.stfea_hr31ColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$stfea_hr31());
        osObjectBuilder.addByteArray(visitorResponseColumnInfo.byfea_hr31ColKey, com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$byfea_hr31());
        osObjectBuilder.addInteger(visitorResponseColumnInfo.arcexresultColKey, Integer.valueOf(com_ciot_realm_db_visitor_visitorresponserealmproxyinterface2.realmGet$arcexresult()));
        osObjectBuilder.updateExistingObject();
        return visitorResponse;
    }

    public ProxyState<?> realmGet$proxyState() {
        return this.proxyState;
    }
}
