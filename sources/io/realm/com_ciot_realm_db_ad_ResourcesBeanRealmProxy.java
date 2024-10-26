package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.ciot.realm.db.ad.ResourcesBean;
import com.limpoxe.support.servicemanager.ServiceProvider;
import io.realm.BaseRealm;
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
import org.json.JSONException;
import org.json.JSONObject;

public class com_ciot_realm_db_ad_ResourcesBeanRealmProxy extends ResourcesBean implements RealmObjectProxy, com_ciot_realm_db_ad_ResourcesBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ResourcesBeanColumnInfo columnInfo;
    private ProxyState<ResourcesBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "ResourcesBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class ResourcesBeanColumnInfo extends ColumnInfo {
        long adListColKey;
        long adListTypeColKey;
        long colorColKey;
        long contentColKey;
        long createtimeColKey;
        long descriptionColKey;
        long directionColKey;
        long durationColKey;
        long fontColKey;
        long idColKey;
        long isUsedColKey;
        long kindColKey;
        long nameColKey;
        long patternColKey;
        long positionColKey;
        long qrLocationColKey;
        long qrUrlColKey;
        long resolutionColKey;
        long resourceIdColKey;
        long sizeColKey;
        long srceenColKey;
        long styleColKey;
        long videoCoverUrlColKey;

        ResourcesBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(23);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.fontColKey = addColumnDetails("font", "font", objectSchemaInfo);
            this.sizeColKey = addColumnDetails("size", "size", objectSchemaInfo);
            this.colorColKey = addColumnDetails("color", "color", objectSchemaInfo);
            this.patternColKey = addColumnDetails("pattern", "pattern", objectSchemaInfo);
            this.resolutionColKey = addColumnDetails("resolution", "resolution", objectSchemaInfo);
            this.positionColKey = addColumnDetails(RequestParameters.POSITION, RequestParameters.POSITION, objectSchemaInfo);
            this.styleColKey = addColumnDetails("style", "style", objectSchemaInfo);
            this.directionColKey = addColumnDetails("direction", "direction", objectSchemaInfo);
            this.contentColKey = addColumnDetails("content", "content", objectSchemaInfo);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
            this.resourceIdColKey = addColumnDetails("resourceId", "resourceId", objectSchemaInfo);
            this.kindColKey = addColumnDetails("kind", "kind", objectSchemaInfo);
            this.isUsedColKey = addColumnDetails("isUsed", "isUsed", objectSchemaInfo);
            this.createtimeColKey = addColumnDetails("createtime", "createtime", objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.srceenColKey = addColumnDetails("srceen", "srceen", objectSchemaInfo);
            this.qrLocationColKey = addColumnDetails("qrLocation", "qrLocation", objectSchemaInfo);
            this.qrUrlColKey = addColumnDetails("qrUrl", "qrUrl", objectSchemaInfo);
            this.durationColKey = addColumnDetails("duration", "duration", objectSchemaInfo);
            this.adListColKey = addColumnDetails("adList", "adList", objectSchemaInfo);
            this.adListTypeColKey = addColumnDetails("adListType", "adListType", objectSchemaInfo);
            this.videoCoverUrlColKey = addColumnDetails("videoCoverUrl", "videoCoverUrl", objectSchemaInfo);
        }

        ResourcesBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new ResourcesBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            ResourcesBeanColumnInfo resourcesBeanColumnInfo = (ResourcesBeanColumnInfo) columnInfo;
            ResourcesBeanColumnInfo resourcesBeanColumnInfo2 = (ResourcesBeanColumnInfo) columnInfo2;
            resourcesBeanColumnInfo2.fontColKey = resourcesBeanColumnInfo.fontColKey;
            resourcesBeanColumnInfo2.sizeColKey = resourcesBeanColumnInfo.sizeColKey;
            resourcesBeanColumnInfo2.colorColKey = resourcesBeanColumnInfo.colorColKey;
            resourcesBeanColumnInfo2.patternColKey = resourcesBeanColumnInfo.patternColKey;
            resourcesBeanColumnInfo2.resolutionColKey = resourcesBeanColumnInfo.resolutionColKey;
            resourcesBeanColumnInfo2.positionColKey = resourcesBeanColumnInfo.positionColKey;
            resourcesBeanColumnInfo2.styleColKey = resourcesBeanColumnInfo.styleColKey;
            resourcesBeanColumnInfo2.directionColKey = resourcesBeanColumnInfo.directionColKey;
            resourcesBeanColumnInfo2.contentColKey = resourcesBeanColumnInfo.contentColKey;
            resourcesBeanColumnInfo2.nameColKey = resourcesBeanColumnInfo.nameColKey;
            resourcesBeanColumnInfo2.resourceIdColKey = resourcesBeanColumnInfo.resourceIdColKey;
            resourcesBeanColumnInfo2.kindColKey = resourcesBeanColumnInfo.kindColKey;
            resourcesBeanColumnInfo2.isUsedColKey = resourcesBeanColumnInfo.isUsedColKey;
            resourcesBeanColumnInfo2.createtimeColKey = resourcesBeanColumnInfo.createtimeColKey;
            resourcesBeanColumnInfo2.descriptionColKey = resourcesBeanColumnInfo.descriptionColKey;
            resourcesBeanColumnInfo2.idColKey = resourcesBeanColumnInfo.idColKey;
            resourcesBeanColumnInfo2.srceenColKey = resourcesBeanColumnInfo.srceenColKey;
            resourcesBeanColumnInfo2.qrLocationColKey = resourcesBeanColumnInfo.qrLocationColKey;
            resourcesBeanColumnInfo2.qrUrlColKey = resourcesBeanColumnInfo.qrUrlColKey;
            resourcesBeanColumnInfo2.durationColKey = resourcesBeanColumnInfo.durationColKey;
            resourcesBeanColumnInfo2.adListColKey = resourcesBeanColumnInfo.adListColKey;
            resourcesBeanColumnInfo2.adListTypeColKey = resourcesBeanColumnInfo.adListTypeColKey;
            resourcesBeanColumnInfo2.videoCoverUrlColKey = resourcesBeanColumnInfo.videoCoverUrlColKey;
        }
    }

    com_ciot_realm_db_ad_ResourcesBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ResourcesBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<ResourcesBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$font() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.fontColKey);
    }

    public void realmSet$font(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.fontColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.fontColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.fontColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.fontColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$size() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.sizeColKey);
    }

    public void realmSet$size(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.sizeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.sizeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.sizeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.sizeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$color() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.colorColKey);
    }

    public void realmSet$color(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.colorColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.colorColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.colorColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.colorColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$pattern() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.patternColKey);
    }

    public void realmSet$pattern(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.patternColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.patternColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.patternColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.patternColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$resolution() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.resolutionColKey);
    }

    public void realmSet$resolution(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.resolutionColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.resolutionColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.resolutionColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.resolutionColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$position() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.positionColKey);
    }

    public void realmSet$position(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.positionColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.positionColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.positionColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.positionColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$style() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.styleColKey);
    }

    public void realmSet$style(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.styleColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.styleColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.styleColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.styleColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$direction() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.directionColKey);
    }

    public void realmSet$direction(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.directionColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.directionColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.directionColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.directionColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$content() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.contentColKey);
    }

    public void realmSet$content(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.contentColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.contentColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.contentColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.contentColKey, row$realm.getObjectKey(), str, true);
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

    public String realmGet$resourceId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.resourceIdColKey);
    }

    public void realmSet$resourceId(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.resourceIdColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.resourceIdColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.resourceIdColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.resourceIdColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$kind() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.kindColKey);
    }

    public void realmSet$kind(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.kindColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.kindColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.kindColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.kindColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$isUsed() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.isUsedColKey);
    }

    public void realmSet$isUsed(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.isUsedColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.isUsedColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.isUsedColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.isUsedColKey, row$realm.getObjectKey(), str, true);
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

    public int realmGet$srceen() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.srceenColKey);
    }

    public void realmSet$srceen(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.srceenColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.srceenColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public String realmGet$qrLocation() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.qrLocationColKey);
    }

    public void realmSet$qrLocation(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.qrLocationColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.qrLocationColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.qrLocationColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.qrLocationColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$qrUrl() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.qrUrlColKey);
    }

    public void realmSet$qrUrl(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.qrUrlColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.qrUrlColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.qrUrlColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.qrUrlColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public int realmGet$duration() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.durationColKey);
    }

    public void realmSet$duration(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.durationColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.durationColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public String realmGet$adList() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.adListColKey);
    }

    public void realmSet$adList(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.adListColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.adListColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.adListColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.adListColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public int realmGet$adListType() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.adListTypeColKey);
    }

    public void realmSet$adListType(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.adListTypeColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.adListTypeColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public String realmGet$videoCoverUrl() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.videoCoverUrlColKey);
    }

    public void realmSet$videoCoverUrl(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.videoCoverUrlColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.videoCoverUrlColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.videoCoverUrlColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.videoCoverUrlColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 23, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("font", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("size", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("color", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("pattern", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("resolution", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(RequestParameters.POSITION, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("style", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("direction", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("content", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("resourceId", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("kind", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("isUsed", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("createtime", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("id", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("srceen", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("qrLocation", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("qrUrl", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("duration", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("adList", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("adListType", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("videoCoverUrl", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ResourcesBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new ResourcesBeanColumnInfo(osSchemaInfo);
    }

    public static ResourcesBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        ResourcesBean resourcesBean = (ResourcesBean) realm.createObjectInternal(ResourcesBean.class, true, Collections.emptyList());
        com_ciot_realm_db_ad_ResourcesBeanRealmProxyInterface com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface = resourcesBean;
        if (jSONObject.has("font")) {
            if (jSONObject.isNull("font")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$font((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$font(jSONObject.getString("font"));
            }
        }
        if (jSONObject.has("size")) {
            if (jSONObject.isNull("size")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$size((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$size(jSONObject.getString("size"));
            }
        }
        if (jSONObject.has("color")) {
            if (jSONObject.isNull("color")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$color((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$color(jSONObject.getString("color"));
            }
        }
        if (jSONObject.has("pattern")) {
            if (jSONObject.isNull("pattern")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$pattern((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$pattern(jSONObject.getString("pattern"));
            }
        }
        if (jSONObject.has("resolution")) {
            if (jSONObject.isNull("resolution")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$resolution((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$resolution(jSONObject.getString("resolution"));
            }
        }
        if (jSONObject.has(RequestParameters.POSITION)) {
            if (jSONObject.isNull(RequestParameters.POSITION)) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$position((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$position(jSONObject.getString(RequestParameters.POSITION));
            }
        }
        if (jSONObject.has("style")) {
            if (jSONObject.isNull("style")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$style((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$style(jSONObject.getString("style"));
            }
        }
        if (jSONObject.has("direction")) {
            if (jSONObject.isNull("direction")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$direction((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$direction(jSONObject.getString("direction"));
            }
        }
        if (jSONObject.has("content")) {
            if (jSONObject.isNull("content")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$content((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$content(jSONObject.getString("content"));
            }
        }
        if (jSONObject.has(ServiceProvider.NAME)) {
            if (jSONObject.isNull(ServiceProvider.NAME)) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$name((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$name(jSONObject.getString(ServiceProvider.NAME));
            }
        }
        if (jSONObject.has("resourceId")) {
            if (jSONObject.isNull("resourceId")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$resourceId((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$resourceId(jSONObject.getString("resourceId"));
            }
        }
        if (jSONObject.has("kind")) {
            if (jSONObject.isNull("kind")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$kind((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$kind(jSONObject.getString("kind"));
            }
        }
        if (jSONObject.has("isUsed")) {
            if (jSONObject.isNull("isUsed")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$isUsed((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$isUsed(jSONObject.getString("isUsed"));
            }
        }
        if (jSONObject.has("createtime")) {
            if (!jSONObject.isNull("createtime")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$createtime(jSONObject.getInt("createtime"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'createtime' to null.");
            }
        }
        if (jSONObject.has("description")) {
            if (jSONObject.isNull("description")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$description((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$description(jSONObject.getString("description"));
            }
        }
        if (jSONObject.has("id")) {
            if (jSONObject.isNull("id")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$id((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$id(jSONObject.getString("id"));
            }
        }
        if (jSONObject.has("srceen")) {
            if (!jSONObject.isNull("srceen")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$srceen(jSONObject.getInt("srceen"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'srceen' to null.");
            }
        }
        if (jSONObject.has("qrLocation")) {
            if (jSONObject.isNull("qrLocation")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$qrLocation((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$qrLocation(jSONObject.getString("qrLocation"));
            }
        }
        if (jSONObject.has("qrUrl")) {
            if (jSONObject.isNull("qrUrl")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$qrUrl((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$qrUrl(jSONObject.getString("qrUrl"));
            }
        }
        if (jSONObject.has("duration")) {
            if (!jSONObject.isNull("duration")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$duration(jSONObject.getInt("duration"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'duration' to null.");
            }
        }
        if (jSONObject.has("adList")) {
            if (jSONObject.isNull("adList")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$adList((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$adList(jSONObject.getString("adList"));
            }
        }
        if (jSONObject.has("adListType")) {
            if (!jSONObject.isNull("adListType")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$adListType(jSONObject.getInt("adListType"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'adListType' to null.");
            }
        }
        if (jSONObject.has("videoCoverUrl")) {
            if (jSONObject.isNull("videoCoverUrl")) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$videoCoverUrl((String) null);
            } else {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$videoCoverUrl(jSONObject.getString("videoCoverUrl"));
            }
        }
        return resourcesBean;
    }

    public static ResourcesBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        ResourcesBean resourcesBean = new ResourcesBean();
        com_ciot_realm_db_ad_ResourcesBeanRealmProxyInterface com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface = resourcesBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("font")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$font(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$font((String) null);
                }
            } else if (nextName.equals("size")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$size(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$size((String) null);
                }
            } else if (nextName.equals("color")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$color(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$color((String) null);
                }
            } else if (nextName.equals("pattern")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$pattern(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$pattern((String) null);
                }
            } else if (nextName.equals("resolution")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$resolution(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$resolution((String) null);
                }
            } else if (nextName.equals(RequestParameters.POSITION)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$position(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$position((String) null);
                }
            } else if (nextName.equals("style")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$style(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$style((String) null);
                }
            } else if (nextName.equals("direction")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$direction(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$direction((String) null);
                }
            } else if (nextName.equals("content")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$content(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$content((String) null);
                }
            } else if (nextName.equals(ServiceProvider.NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$name(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$name((String) null);
                }
            } else if (nextName.equals("resourceId")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$resourceId(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$resourceId((String) null);
                }
            } else if (nextName.equals("kind")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$kind(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$kind((String) null);
                }
            } else if (nextName.equals("isUsed")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$isUsed(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$isUsed((String) null);
                }
            } else if (nextName.equals("createtime")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$createtime(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'createtime' to null.");
                }
            } else if (nextName.equals("description")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$description(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$description((String) null);
                }
            } else if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$id((String) null);
                }
            } else if (nextName.equals("srceen")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$srceen(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'srceen' to null.");
                }
            } else if (nextName.equals("qrLocation")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$qrLocation(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$qrLocation((String) null);
                }
            } else if (nextName.equals("qrUrl")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$qrUrl(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$qrUrl((String) null);
                }
            } else if (nextName.equals("duration")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$duration(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'duration' to null.");
                }
            } else if (nextName.equals("adList")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$adList(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$adList((String) null);
                }
            } else if (nextName.equals("adListType")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$adListType(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'adListType' to null.");
                }
            } else if (!nextName.equals("videoCoverUrl")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$videoCoverUrl(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$videoCoverUrl((String) null);
            }
        }
        jsonReader.endObject();
        return (ResourcesBean) realm.copyToRealm(resourcesBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_ad_ResourcesBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) ResourcesBean.class), false, Collections.emptyList());
        com_ciot_realm_db_ad_ResourcesBeanRealmProxy com_ciot_realm_db_ad_resourcesbeanrealmproxy = new com_ciot_realm_db_ad_ResourcesBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_ad_resourcesbeanrealmproxy;
    }

    public static ResourcesBean copyOrUpdate(Realm realm, ResourcesBeanColumnInfo resourcesBeanColumnInfo, ResourcesBean resourcesBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((resourcesBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(resourcesBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) resourcesBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return resourcesBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(resourcesBean);
        if (realmObjectProxy2 != null) {
            return (ResourcesBean) realmObjectProxy2;
        }
        return copy(realm, resourcesBeanColumnInfo, resourcesBean, z, map, set);
    }

    public static ResourcesBean copy(Realm realm, ResourcesBeanColumnInfo resourcesBeanColumnInfo, ResourcesBean resourcesBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(resourcesBean);
        if (realmObjectProxy != null) {
            return (ResourcesBean) realmObjectProxy;
        }
        com_ciot_realm_db_ad_ResourcesBeanRealmProxyInterface com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface = resourcesBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(ResourcesBean.class), set);
        osObjectBuilder.addString(resourcesBeanColumnInfo.fontColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$font());
        osObjectBuilder.addString(resourcesBeanColumnInfo.sizeColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$size());
        osObjectBuilder.addString(resourcesBeanColumnInfo.colorColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$color());
        osObjectBuilder.addString(resourcesBeanColumnInfo.patternColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$pattern());
        osObjectBuilder.addString(resourcesBeanColumnInfo.resolutionColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$resolution());
        osObjectBuilder.addString(resourcesBeanColumnInfo.positionColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$position());
        osObjectBuilder.addString(resourcesBeanColumnInfo.styleColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$style());
        osObjectBuilder.addString(resourcesBeanColumnInfo.directionColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$direction());
        osObjectBuilder.addString(resourcesBeanColumnInfo.contentColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$content());
        osObjectBuilder.addString(resourcesBeanColumnInfo.nameColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$name());
        osObjectBuilder.addString(resourcesBeanColumnInfo.resourceIdColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$resourceId());
        osObjectBuilder.addString(resourcesBeanColumnInfo.kindColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$kind());
        osObjectBuilder.addString(resourcesBeanColumnInfo.isUsedColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$isUsed());
        osObjectBuilder.addInteger(resourcesBeanColumnInfo.createtimeColKey, Integer.valueOf(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$createtime()));
        osObjectBuilder.addString(resourcesBeanColumnInfo.descriptionColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$description());
        osObjectBuilder.addString(resourcesBeanColumnInfo.idColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$id());
        osObjectBuilder.addInteger(resourcesBeanColumnInfo.srceenColKey, Integer.valueOf(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$srceen()));
        osObjectBuilder.addString(resourcesBeanColumnInfo.qrLocationColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$qrLocation());
        osObjectBuilder.addString(resourcesBeanColumnInfo.qrUrlColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$qrUrl());
        osObjectBuilder.addInteger(resourcesBeanColumnInfo.durationColKey, Integer.valueOf(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$duration()));
        osObjectBuilder.addString(resourcesBeanColumnInfo.adListColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$adList());
        osObjectBuilder.addInteger(resourcesBeanColumnInfo.adListTypeColKey, Integer.valueOf(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$adListType()));
        osObjectBuilder.addString(resourcesBeanColumnInfo.videoCoverUrlColKey, com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$videoCoverUrl());
        com_ciot_realm_db_ad_ResourcesBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(resourcesBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, ResourcesBean resourcesBean, Map<RealmModel, Long> map) {
        ResourcesBean resourcesBean2 = resourcesBean;
        if ((resourcesBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(resourcesBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) resourcesBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(ResourcesBean.class);
        long nativePtr = table.getNativePtr();
        ResourcesBeanColumnInfo resourcesBeanColumnInfo = (ResourcesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ResourcesBean.class);
        long createRow = OsObject.createRow(table);
        map.put(resourcesBean2, Long.valueOf(createRow));
        com_ciot_realm_db_ad_ResourcesBeanRealmProxyInterface com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface = resourcesBean2;
        String realmGet$font = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$font();
        if (realmGet$font != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.fontColKey, createRow, realmGet$font, false);
        }
        String realmGet$size = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$size();
        if (realmGet$size != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.sizeColKey, createRow, realmGet$size, false);
        }
        String realmGet$color = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$color();
        if (realmGet$color != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.colorColKey, createRow, realmGet$color, false);
        }
        String realmGet$pattern = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$pattern();
        if (realmGet$pattern != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.patternColKey, createRow, realmGet$pattern, false);
        }
        String realmGet$resolution = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$resolution();
        if (realmGet$resolution != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.resolutionColKey, createRow, realmGet$resolution, false);
        }
        String realmGet$position = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$position();
        if (realmGet$position != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.positionColKey, createRow, realmGet$position, false);
        }
        String realmGet$style = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$style();
        if (realmGet$style != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.styleColKey, createRow, realmGet$style, false);
        }
        String realmGet$direction = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$direction();
        if (realmGet$direction != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.directionColKey, createRow, realmGet$direction, false);
        }
        String realmGet$content = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$content();
        if (realmGet$content != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.contentColKey, createRow, realmGet$content, false);
        }
        String realmGet$name = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
        }
        String realmGet$resourceId = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$resourceId();
        if (realmGet$resourceId != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.resourceIdColKey, createRow, realmGet$resourceId, false);
        }
        String realmGet$kind = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$kind();
        if (realmGet$kind != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.kindColKey, createRow, realmGet$kind, false);
        }
        String realmGet$isUsed = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$isUsed();
        if (realmGet$isUsed != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.isUsedColKey, createRow, realmGet$isUsed, false);
        }
        Table.nativeSetLong(nativePtr, resourcesBeanColumnInfo.createtimeColKey, createRow, (long) com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$createtime(), false);
        String realmGet$description = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.descriptionColKey, createRow, realmGet$description, false);
        }
        String realmGet$id = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.idColKey, createRow, realmGet$id, false);
        }
        Table.nativeSetLong(nativePtr, resourcesBeanColumnInfo.srceenColKey, createRow, (long) com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$srceen(), false);
        String realmGet$qrLocation = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$qrLocation();
        if (realmGet$qrLocation != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.qrLocationColKey, createRow, realmGet$qrLocation, false);
        }
        String realmGet$qrUrl = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$qrUrl();
        if (realmGet$qrUrl != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.qrUrlColKey, createRow, realmGet$qrUrl, false);
        }
        Table.nativeSetLong(nativePtr, resourcesBeanColumnInfo.durationColKey, createRow, (long) com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$duration(), false);
        String realmGet$adList = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$adList();
        if (realmGet$adList != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.adListColKey, createRow, realmGet$adList, false);
        }
        Table.nativeSetLong(nativePtr, resourcesBeanColumnInfo.adListTypeColKey, createRow, (long) com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$adListType(), false);
        String realmGet$videoCoverUrl = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$videoCoverUrl();
        if (realmGet$videoCoverUrl != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.videoCoverUrlColKey, createRow, realmGet$videoCoverUrl, false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(ResourcesBean.class);
        long nativePtr = table.getNativePtr();
        ResourcesBeanColumnInfo resourcesBeanColumnInfo = (ResourcesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ResourcesBean.class);
        while (it.hasNext()) {
            ResourcesBean resourcesBean = (ResourcesBean) it.next();
            if (!map2.containsKey(resourcesBean)) {
                if ((resourcesBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(resourcesBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) resourcesBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(resourcesBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(resourcesBean, Long.valueOf(createRow));
                com_ciot_realm_db_ad_ResourcesBeanRealmProxyInterface com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface = resourcesBean;
                String realmGet$font = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$font();
                if (realmGet$font != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.fontColKey, createRow, realmGet$font, false);
                }
                String realmGet$size = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$size();
                if (realmGet$size != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.sizeColKey, createRow, realmGet$size, false);
                }
                String realmGet$color = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$color();
                if (realmGet$color != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.colorColKey, createRow, realmGet$color, false);
                }
                String realmGet$pattern = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$pattern();
                if (realmGet$pattern != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.patternColKey, createRow, realmGet$pattern, false);
                }
                String realmGet$resolution = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$resolution();
                if (realmGet$resolution != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.resolutionColKey, createRow, realmGet$resolution, false);
                }
                String realmGet$position = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$position();
                if (realmGet$position != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.positionColKey, createRow, realmGet$position, false);
                }
                String realmGet$style = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$style();
                if (realmGet$style != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.styleColKey, createRow, realmGet$style, false);
                }
                String realmGet$direction = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$direction();
                if (realmGet$direction != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.directionColKey, createRow, realmGet$direction, false);
                }
                String realmGet$content = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$content();
                if (realmGet$content != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.contentColKey, createRow, realmGet$content, false);
                }
                String realmGet$name = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
                }
                String realmGet$resourceId = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$resourceId();
                if (realmGet$resourceId != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.resourceIdColKey, createRow, realmGet$resourceId, false);
                }
                String realmGet$kind = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$kind();
                if (realmGet$kind != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.kindColKey, createRow, realmGet$kind, false);
                }
                String realmGet$isUsed = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$isUsed();
                if (realmGet$isUsed != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.isUsedColKey, createRow, realmGet$isUsed, false);
                }
                Table.nativeSetLong(nativePtr, resourcesBeanColumnInfo.createtimeColKey, createRow, (long) com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$createtime(), false);
                String realmGet$description = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.descriptionColKey, createRow, realmGet$description, false);
                }
                String realmGet$id = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.idColKey, createRow, realmGet$id, false);
                }
                Table.nativeSetLong(nativePtr, resourcesBeanColumnInfo.srceenColKey, createRow, (long) com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$srceen(), false);
                String realmGet$qrLocation = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$qrLocation();
                if (realmGet$qrLocation != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.qrLocationColKey, createRow, realmGet$qrLocation, false);
                }
                String realmGet$qrUrl = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$qrUrl();
                if (realmGet$qrUrl != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.qrUrlColKey, createRow, realmGet$qrUrl, false);
                }
                Table.nativeSetLong(nativePtr, resourcesBeanColumnInfo.durationColKey, createRow, (long) com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$duration(), false);
                String realmGet$adList = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$adList();
                if (realmGet$adList != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.adListColKey, createRow, realmGet$adList, false);
                }
                Table.nativeSetLong(nativePtr, resourcesBeanColumnInfo.adListTypeColKey, createRow, (long) com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$adListType(), false);
                String realmGet$videoCoverUrl = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$videoCoverUrl();
                if (realmGet$videoCoverUrl != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.videoCoverUrlColKey, createRow, realmGet$videoCoverUrl, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, ResourcesBean resourcesBean, Map<RealmModel, Long> map) {
        ResourcesBean resourcesBean2 = resourcesBean;
        if ((resourcesBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(resourcesBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) resourcesBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(ResourcesBean.class);
        long nativePtr = table.getNativePtr();
        ResourcesBeanColumnInfo resourcesBeanColumnInfo = (ResourcesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ResourcesBean.class);
        long createRow = OsObject.createRow(table);
        map.put(resourcesBean2, Long.valueOf(createRow));
        com_ciot_realm_db_ad_ResourcesBeanRealmProxyInterface com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface = resourcesBean2;
        String realmGet$font = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$font();
        if (realmGet$font != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.fontColKey, createRow, realmGet$font, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.fontColKey, createRow, false);
        }
        String realmGet$size = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$size();
        if (realmGet$size != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.sizeColKey, createRow, realmGet$size, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.sizeColKey, createRow, false);
        }
        String realmGet$color = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$color();
        if (realmGet$color != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.colorColKey, createRow, realmGet$color, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.colorColKey, createRow, false);
        }
        String realmGet$pattern = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$pattern();
        if (realmGet$pattern != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.patternColKey, createRow, realmGet$pattern, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.patternColKey, createRow, false);
        }
        String realmGet$resolution = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$resolution();
        if (realmGet$resolution != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.resolutionColKey, createRow, realmGet$resolution, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.resolutionColKey, createRow, false);
        }
        String realmGet$position = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$position();
        if (realmGet$position != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.positionColKey, createRow, realmGet$position, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.positionColKey, createRow, false);
        }
        String realmGet$style = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$style();
        if (realmGet$style != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.styleColKey, createRow, realmGet$style, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.styleColKey, createRow, false);
        }
        String realmGet$direction = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$direction();
        if (realmGet$direction != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.directionColKey, createRow, realmGet$direction, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.directionColKey, createRow, false);
        }
        String realmGet$content = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$content();
        if (realmGet$content != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.contentColKey, createRow, realmGet$content, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.contentColKey, createRow, false);
        }
        String realmGet$name = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.nameColKey, createRow, false);
        }
        String realmGet$resourceId = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$resourceId();
        if (realmGet$resourceId != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.resourceIdColKey, createRow, realmGet$resourceId, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.resourceIdColKey, createRow, false);
        }
        String realmGet$kind = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$kind();
        if (realmGet$kind != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.kindColKey, createRow, realmGet$kind, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.kindColKey, createRow, false);
        }
        String realmGet$isUsed = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$isUsed();
        if (realmGet$isUsed != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.isUsedColKey, createRow, realmGet$isUsed, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.isUsedColKey, createRow, false);
        }
        Table.nativeSetLong(nativePtr, resourcesBeanColumnInfo.createtimeColKey, createRow, (long) com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$createtime(), false);
        String realmGet$description = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.descriptionColKey, createRow, realmGet$description, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.descriptionColKey, createRow, false);
        }
        String realmGet$id = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.idColKey, createRow, realmGet$id, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.idColKey, createRow, false);
        }
        Table.nativeSetLong(nativePtr, resourcesBeanColumnInfo.srceenColKey, createRow, (long) com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$srceen(), false);
        String realmGet$qrLocation = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$qrLocation();
        if (realmGet$qrLocation != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.qrLocationColKey, createRow, realmGet$qrLocation, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.qrLocationColKey, createRow, false);
        }
        String realmGet$qrUrl = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$qrUrl();
        if (realmGet$qrUrl != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.qrUrlColKey, createRow, realmGet$qrUrl, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.qrUrlColKey, createRow, false);
        }
        Table.nativeSetLong(nativePtr, resourcesBeanColumnInfo.durationColKey, createRow, (long) com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$duration(), false);
        String realmGet$adList = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$adList();
        if (realmGet$adList != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.adListColKey, createRow, realmGet$adList, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.adListColKey, createRow, false);
        }
        Table.nativeSetLong(nativePtr, resourcesBeanColumnInfo.adListTypeColKey, createRow, (long) com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$adListType(), false);
        String realmGet$videoCoverUrl = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$videoCoverUrl();
        if (realmGet$videoCoverUrl != null) {
            Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.videoCoverUrlColKey, createRow, realmGet$videoCoverUrl, false);
        } else {
            Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.videoCoverUrlColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(ResourcesBean.class);
        long nativePtr = table.getNativePtr();
        ResourcesBeanColumnInfo resourcesBeanColumnInfo = (ResourcesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ResourcesBean.class);
        while (it.hasNext()) {
            ResourcesBean resourcesBean = (ResourcesBean) it.next();
            if (!map2.containsKey(resourcesBean)) {
                if ((resourcesBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(resourcesBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) resourcesBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(resourcesBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(resourcesBean, Long.valueOf(createRow));
                com_ciot_realm_db_ad_ResourcesBeanRealmProxyInterface com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface = resourcesBean;
                String realmGet$font = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$font();
                if (realmGet$font != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.fontColKey, createRow, realmGet$font, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.fontColKey, createRow, false);
                }
                String realmGet$size = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$size();
                if (realmGet$size != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.sizeColKey, createRow, realmGet$size, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.sizeColKey, createRow, false);
                }
                String realmGet$color = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$color();
                if (realmGet$color != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.colorColKey, createRow, realmGet$color, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.colorColKey, createRow, false);
                }
                String realmGet$pattern = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$pattern();
                if (realmGet$pattern != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.patternColKey, createRow, realmGet$pattern, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.patternColKey, createRow, false);
                }
                String realmGet$resolution = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$resolution();
                if (realmGet$resolution != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.resolutionColKey, createRow, realmGet$resolution, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.resolutionColKey, createRow, false);
                }
                String realmGet$position = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$position();
                if (realmGet$position != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.positionColKey, createRow, realmGet$position, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.positionColKey, createRow, false);
                }
                String realmGet$style = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$style();
                if (realmGet$style != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.styleColKey, createRow, realmGet$style, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.styleColKey, createRow, false);
                }
                String realmGet$direction = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$direction();
                if (realmGet$direction != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.directionColKey, createRow, realmGet$direction, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.directionColKey, createRow, false);
                }
                String realmGet$content = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$content();
                if (realmGet$content != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.contentColKey, createRow, realmGet$content, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.contentColKey, createRow, false);
                }
                String realmGet$name = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.nameColKey, createRow, false);
                }
                String realmGet$resourceId = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$resourceId();
                if (realmGet$resourceId != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.resourceIdColKey, createRow, realmGet$resourceId, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.resourceIdColKey, createRow, false);
                }
                String realmGet$kind = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$kind();
                if (realmGet$kind != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.kindColKey, createRow, realmGet$kind, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.kindColKey, createRow, false);
                }
                String realmGet$isUsed = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$isUsed();
                if (realmGet$isUsed != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.isUsedColKey, createRow, realmGet$isUsed, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.isUsedColKey, createRow, false);
                }
                Table.nativeSetLong(nativePtr, resourcesBeanColumnInfo.createtimeColKey, createRow, (long) com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$createtime(), false);
                String realmGet$description = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.descriptionColKey, createRow, realmGet$description, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.descriptionColKey, createRow, false);
                }
                String realmGet$id = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.idColKey, createRow, realmGet$id, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.idColKey, createRow, false);
                }
                Table.nativeSetLong(nativePtr, resourcesBeanColumnInfo.srceenColKey, createRow, (long) com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$srceen(), false);
                String realmGet$qrLocation = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$qrLocation();
                if (realmGet$qrLocation != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.qrLocationColKey, createRow, realmGet$qrLocation, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.qrLocationColKey, createRow, false);
                }
                String realmGet$qrUrl = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$qrUrl();
                if (realmGet$qrUrl != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.qrUrlColKey, createRow, realmGet$qrUrl, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.qrUrlColKey, createRow, false);
                }
                Table.nativeSetLong(nativePtr, resourcesBeanColumnInfo.durationColKey, createRow, (long) com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$duration(), false);
                String realmGet$adList = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$adList();
                if (realmGet$adList != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.adListColKey, createRow, realmGet$adList, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.adListColKey, createRow, false);
                }
                Table.nativeSetLong(nativePtr, resourcesBeanColumnInfo.adListTypeColKey, createRow, (long) com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$adListType(), false);
                String realmGet$videoCoverUrl = com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmGet$videoCoverUrl();
                if (realmGet$videoCoverUrl != null) {
                    Table.nativeSetString(nativePtr, resourcesBeanColumnInfo.videoCoverUrlColKey, createRow, realmGet$videoCoverUrl, false);
                } else {
                    Table.nativeSetNull(nativePtr, resourcesBeanColumnInfo.videoCoverUrlColKey, createRow, false);
                }
            }
        }
    }

    public static ResourcesBean createDetachedCopy(ResourcesBean resourcesBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        ResourcesBean resourcesBean2;
        if (i > i2 || resourcesBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(resourcesBean);
        if (cacheData == null) {
            resourcesBean2 = new ResourcesBean();
            map.put(resourcesBean, new RealmObjectProxy.CacheData(i, resourcesBean2));
        } else if (i >= cacheData.minDepth) {
            return (ResourcesBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            resourcesBean2 = (ResourcesBean) cacheData.object;
        }
        com_ciot_realm_db_ad_ResourcesBeanRealmProxyInterface com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface = resourcesBean2;
        com_ciot_realm_db_ad_ResourcesBeanRealmProxyInterface com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2 = resourcesBean;
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$font(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$font());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$size(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$size());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$color(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$color());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$pattern(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$pattern());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$resolution(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$resolution());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$position(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$position());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$style(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$style());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$direction(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$direction());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$content(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$content());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$name(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$name());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$resourceId(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$resourceId());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$kind(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$kind());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$isUsed(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$isUsed());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$createtime(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$createtime());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$description(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$description());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$id(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$srceen(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$srceen());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$qrLocation(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$qrLocation());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$qrUrl(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$qrUrl());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$duration(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$duration());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$adList(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$adList());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$adListType(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$adListType());
        com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface.realmSet$videoCoverUrl(com_ciot_realm_db_ad_resourcesbeanrealmproxyinterface2.realmGet$videoCoverUrl());
        return resourcesBean2;
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
        com_ciot_realm_db_ad_ResourcesBeanRealmProxy com_ciot_realm_db_ad_resourcesbeanrealmproxy = (com_ciot_realm_db_ad_ResourcesBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_ad_resourcesbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_ad_resourcesbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_ad_resourcesbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
