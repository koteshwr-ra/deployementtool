package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.ciot.realm.db.ad.HorseRaceLampsBean;
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
import org.apache.log4j.spi.Configurator;
import org.json.JSONException;
import org.json.JSONObject;

public class com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy extends HorseRaceLampsBean implements RealmObjectProxy, com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private HorseRaceLampsBeanColumnInfo columnInfo;
    private ProxyState<HorseRaceLampsBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "HorseRaceLampsBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class HorseRaceLampsBeanColumnInfo extends ColumnInfo {
        long colorColKey;
        long contentColKey;
        long descriptionColKey;
        long directionColKey;
        long idColKey;
        long isUsedColKey;
        long kindColKey;
        long nameColKey;
        long positionColKey;
        long resourceIdColKey;
        long styleColKey;

        HorseRaceLampsBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(11);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.positionColKey = addColumnDetails(RequestParameters.POSITION, RequestParameters.POSITION, objectSchemaInfo);
            this.styleColKey = addColumnDetails("style", "style", objectSchemaInfo);
            this.colorColKey = addColumnDetails("color", "color", objectSchemaInfo);
            this.directionColKey = addColumnDetails("direction", "direction", objectSchemaInfo);
            this.contentColKey = addColumnDetails("content", "content", objectSchemaInfo);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
            this.resourceIdColKey = addColumnDetails("resourceId", "resourceId", objectSchemaInfo);
            this.kindColKey = addColumnDetails("kind", "kind", objectSchemaInfo);
            this.isUsedColKey = addColumnDetails("isUsed", "isUsed", objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
        }

        HorseRaceLampsBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new HorseRaceLampsBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            HorseRaceLampsBeanColumnInfo horseRaceLampsBeanColumnInfo = (HorseRaceLampsBeanColumnInfo) columnInfo;
            HorseRaceLampsBeanColumnInfo horseRaceLampsBeanColumnInfo2 = (HorseRaceLampsBeanColumnInfo) columnInfo2;
            horseRaceLampsBeanColumnInfo2.positionColKey = horseRaceLampsBeanColumnInfo.positionColKey;
            horseRaceLampsBeanColumnInfo2.styleColKey = horseRaceLampsBeanColumnInfo.styleColKey;
            horseRaceLampsBeanColumnInfo2.colorColKey = horseRaceLampsBeanColumnInfo.colorColKey;
            horseRaceLampsBeanColumnInfo2.directionColKey = horseRaceLampsBeanColumnInfo.directionColKey;
            horseRaceLampsBeanColumnInfo2.contentColKey = horseRaceLampsBeanColumnInfo.contentColKey;
            horseRaceLampsBeanColumnInfo2.nameColKey = horseRaceLampsBeanColumnInfo.nameColKey;
            horseRaceLampsBeanColumnInfo2.resourceIdColKey = horseRaceLampsBeanColumnInfo.resourceIdColKey;
            horseRaceLampsBeanColumnInfo2.kindColKey = horseRaceLampsBeanColumnInfo.kindColKey;
            horseRaceLampsBeanColumnInfo2.isUsedColKey = horseRaceLampsBeanColumnInfo.isUsedColKey;
            horseRaceLampsBeanColumnInfo2.descriptionColKey = horseRaceLampsBeanColumnInfo.descriptionColKey;
            horseRaceLampsBeanColumnInfo2.idColKey = horseRaceLampsBeanColumnInfo.idColKey;
        }
    }

    com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (HorseRaceLampsBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<HorseRaceLampsBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 11, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty(RequestParameters.POSITION, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("style", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("color", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("direction", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("content", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("resourceId", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("kind", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("isUsed", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("id", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static HorseRaceLampsBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new HorseRaceLampsBeanColumnInfo(osSchemaInfo);
    }

    public static HorseRaceLampsBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        HorseRaceLampsBean horseRaceLampsBean = (HorseRaceLampsBean) realm.createObjectInternal(HorseRaceLampsBean.class, true, Collections.emptyList());
        com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxyInterface com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface = horseRaceLampsBean;
        if (jSONObject.has(RequestParameters.POSITION)) {
            if (jSONObject.isNull(RequestParameters.POSITION)) {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$position((String) null);
            } else {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$position(jSONObject.getString(RequestParameters.POSITION));
            }
        }
        if (jSONObject.has("style")) {
            if (jSONObject.isNull("style")) {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$style((String) null);
            } else {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$style(jSONObject.getString("style"));
            }
        }
        if (jSONObject.has("color")) {
            if (jSONObject.isNull("color")) {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$color((String) null);
            } else {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$color(jSONObject.getString("color"));
            }
        }
        if (jSONObject.has("direction")) {
            if (jSONObject.isNull("direction")) {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$direction((String) null);
            } else {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$direction(jSONObject.getString("direction"));
            }
        }
        if (jSONObject.has("content")) {
            if (jSONObject.isNull("content")) {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$content((String) null);
            } else {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$content(jSONObject.getString("content"));
            }
        }
        if (jSONObject.has(ServiceProvider.NAME)) {
            if (jSONObject.isNull(ServiceProvider.NAME)) {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$name((String) null);
            } else {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$name(jSONObject.getString(ServiceProvider.NAME));
            }
        }
        if (jSONObject.has("resourceId")) {
            if (jSONObject.isNull("resourceId")) {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$resourceId((String) null);
            } else {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$resourceId(jSONObject.getString("resourceId"));
            }
        }
        if (jSONObject.has("kind")) {
            if (jSONObject.isNull("kind")) {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$kind((String) null);
            } else {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$kind(jSONObject.getString("kind"));
            }
        }
        if (jSONObject.has("isUsed")) {
            if (jSONObject.isNull("isUsed")) {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$isUsed((String) null);
            } else {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$isUsed(jSONObject.getString("isUsed"));
            }
        }
        if (jSONObject.has("description")) {
            if (jSONObject.isNull("description")) {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$description((String) null);
            } else {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$description(jSONObject.getString("description"));
            }
        }
        if (jSONObject.has("id")) {
            if (jSONObject.isNull("id")) {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$id((String) null);
            } else {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$id(jSONObject.getString("id"));
            }
        }
        return horseRaceLampsBean;
    }

    public static HorseRaceLampsBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        HorseRaceLampsBean horseRaceLampsBean = new HorseRaceLampsBean();
        com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxyInterface com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface = horseRaceLampsBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(RequestParameters.POSITION)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$position(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$position((String) null);
                }
            } else if (nextName.equals("style")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$style(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$style((String) null);
                }
            } else if (nextName.equals("color")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$color(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$color((String) null);
                }
            } else if (nextName.equals("direction")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$direction(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$direction((String) null);
                }
            } else if (nextName.equals("content")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$content(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$content((String) null);
                }
            } else if (nextName.equals(ServiceProvider.NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$name(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$name((String) null);
                }
            } else if (nextName.equals("resourceId")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$resourceId(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$resourceId((String) null);
                }
            } else if (nextName.equals("kind")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$kind(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$kind((String) null);
                }
            } else if (nextName.equals("isUsed")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$isUsed(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$isUsed((String) null);
                }
            } else if (nextName.equals("description")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$description(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$description((String) null);
                }
            } else if (!nextName.equals("id")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$id(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$id((String) null);
            }
        }
        jsonReader.endObject();
        return (HorseRaceLampsBean) realm.copyToRealm(horseRaceLampsBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) HorseRaceLampsBean.class), false, Collections.emptyList());
        com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy com_ciot_realm_db_ad_horseracelampsbeanrealmproxy = new com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_ad_horseracelampsbeanrealmproxy;
    }

    public static HorseRaceLampsBean copyOrUpdate(Realm realm, HorseRaceLampsBeanColumnInfo horseRaceLampsBeanColumnInfo, HorseRaceLampsBean horseRaceLampsBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((horseRaceLampsBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(horseRaceLampsBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) horseRaceLampsBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return horseRaceLampsBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(horseRaceLampsBean);
        if (realmObjectProxy2 != null) {
            return (HorseRaceLampsBean) realmObjectProxy2;
        }
        return copy(realm, horseRaceLampsBeanColumnInfo, horseRaceLampsBean, z, map, set);
    }

    public static HorseRaceLampsBean copy(Realm realm, HorseRaceLampsBeanColumnInfo horseRaceLampsBeanColumnInfo, HorseRaceLampsBean horseRaceLampsBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(horseRaceLampsBean);
        if (realmObjectProxy != null) {
            return (HorseRaceLampsBean) realmObjectProxy;
        }
        com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxyInterface com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface = horseRaceLampsBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(HorseRaceLampsBean.class), set);
        osObjectBuilder.addString(horseRaceLampsBeanColumnInfo.positionColKey, com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$position());
        osObjectBuilder.addString(horseRaceLampsBeanColumnInfo.styleColKey, com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$style());
        osObjectBuilder.addString(horseRaceLampsBeanColumnInfo.colorColKey, com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$color());
        osObjectBuilder.addString(horseRaceLampsBeanColumnInfo.directionColKey, com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$direction());
        osObjectBuilder.addString(horseRaceLampsBeanColumnInfo.contentColKey, com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$content());
        osObjectBuilder.addString(horseRaceLampsBeanColumnInfo.nameColKey, com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$name());
        osObjectBuilder.addString(horseRaceLampsBeanColumnInfo.resourceIdColKey, com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$resourceId());
        osObjectBuilder.addString(horseRaceLampsBeanColumnInfo.kindColKey, com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$kind());
        osObjectBuilder.addString(horseRaceLampsBeanColumnInfo.isUsedColKey, com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$isUsed());
        osObjectBuilder.addString(horseRaceLampsBeanColumnInfo.descriptionColKey, com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$description());
        osObjectBuilder.addString(horseRaceLampsBeanColumnInfo.idColKey, com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$id());
        com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(horseRaceLampsBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, HorseRaceLampsBean horseRaceLampsBean, Map<RealmModel, Long> map) {
        if ((horseRaceLampsBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(horseRaceLampsBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) horseRaceLampsBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(HorseRaceLampsBean.class);
        long nativePtr = table.getNativePtr();
        HorseRaceLampsBeanColumnInfo horseRaceLampsBeanColumnInfo = (HorseRaceLampsBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) HorseRaceLampsBean.class);
        long createRow = OsObject.createRow(table);
        map.put(horseRaceLampsBean, Long.valueOf(createRow));
        com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxyInterface com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface = horseRaceLampsBean;
        String realmGet$position = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$position();
        if (realmGet$position != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.positionColKey, createRow, realmGet$position, false);
        }
        String realmGet$style = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$style();
        if (realmGet$style != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.styleColKey, createRow, realmGet$style, false);
        }
        String realmGet$color = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$color();
        if (realmGet$color != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.colorColKey, createRow, realmGet$color, false);
        }
        String realmGet$direction = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$direction();
        if (realmGet$direction != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.directionColKey, createRow, realmGet$direction, false);
        }
        String realmGet$content = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$content();
        if (realmGet$content != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.contentColKey, createRow, realmGet$content, false);
        }
        String realmGet$name = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
        }
        String realmGet$resourceId = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$resourceId();
        if (realmGet$resourceId != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.resourceIdColKey, createRow, realmGet$resourceId, false);
        }
        String realmGet$kind = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$kind();
        if (realmGet$kind != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.kindColKey, createRow, realmGet$kind, false);
        }
        String realmGet$isUsed = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$isUsed();
        if (realmGet$isUsed != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.isUsedColKey, createRow, realmGet$isUsed, false);
        }
        String realmGet$description = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.descriptionColKey, createRow, realmGet$description, false);
        }
        String realmGet$id = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.idColKey, createRow, realmGet$id, false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(HorseRaceLampsBean.class);
        long nativePtr = table.getNativePtr();
        HorseRaceLampsBeanColumnInfo horseRaceLampsBeanColumnInfo = (HorseRaceLampsBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) HorseRaceLampsBean.class);
        while (it.hasNext()) {
            HorseRaceLampsBean horseRaceLampsBean = (HorseRaceLampsBean) it.next();
            if (!map2.containsKey(horseRaceLampsBean)) {
                if ((horseRaceLampsBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(horseRaceLampsBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) horseRaceLampsBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(horseRaceLampsBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(horseRaceLampsBean, Long.valueOf(createRow));
                com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxyInterface com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface = horseRaceLampsBean;
                String realmGet$position = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$position();
                if (realmGet$position != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.positionColKey, createRow, realmGet$position, false);
                }
                String realmGet$style = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$style();
                if (realmGet$style != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.styleColKey, createRow, realmGet$style, false);
                }
                String realmGet$color = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$color();
                if (realmGet$color != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.colorColKey, createRow, realmGet$color, false);
                }
                String realmGet$direction = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$direction();
                if (realmGet$direction != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.directionColKey, createRow, realmGet$direction, false);
                }
                String realmGet$content = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$content();
                if (realmGet$content != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.contentColKey, createRow, realmGet$content, false);
                }
                String realmGet$name = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
                }
                String realmGet$resourceId = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$resourceId();
                if (realmGet$resourceId != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.resourceIdColKey, createRow, realmGet$resourceId, false);
                }
                String realmGet$kind = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$kind();
                if (realmGet$kind != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.kindColKey, createRow, realmGet$kind, false);
                }
                String realmGet$isUsed = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$isUsed();
                if (realmGet$isUsed != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.isUsedColKey, createRow, realmGet$isUsed, false);
                }
                String realmGet$description = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.descriptionColKey, createRow, realmGet$description, false);
                }
                String realmGet$id = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.idColKey, createRow, realmGet$id, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, HorseRaceLampsBean horseRaceLampsBean, Map<RealmModel, Long> map) {
        if ((horseRaceLampsBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(horseRaceLampsBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) horseRaceLampsBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(HorseRaceLampsBean.class);
        long nativePtr = table.getNativePtr();
        HorseRaceLampsBeanColumnInfo horseRaceLampsBeanColumnInfo = (HorseRaceLampsBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) HorseRaceLampsBean.class);
        long createRow = OsObject.createRow(table);
        map.put(horseRaceLampsBean, Long.valueOf(createRow));
        com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxyInterface com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface = horseRaceLampsBean;
        String realmGet$position = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$position();
        if (realmGet$position != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.positionColKey, createRow, realmGet$position, false);
        } else {
            Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.positionColKey, createRow, false);
        }
        String realmGet$style = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$style();
        if (realmGet$style != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.styleColKey, createRow, realmGet$style, false);
        } else {
            Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.styleColKey, createRow, false);
        }
        String realmGet$color = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$color();
        if (realmGet$color != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.colorColKey, createRow, realmGet$color, false);
        } else {
            Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.colorColKey, createRow, false);
        }
        String realmGet$direction = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$direction();
        if (realmGet$direction != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.directionColKey, createRow, realmGet$direction, false);
        } else {
            Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.directionColKey, createRow, false);
        }
        String realmGet$content = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$content();
        if (realmGet$content != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.contentColKey, createRow, realmGet$content, false);
        } else {
            Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.contentColKey, createRow, false);
        }
        String realmGet$name = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
        } else {
            Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.nameColKey, createRow, false);
        }
        String realmGet$resourceId = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$resourceId();
        if (realmGet$resourceId != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.resourceIdColKey, createRow, realmGet$resourceId, false);
        } else {
            Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.resourceIdColKey, createRow, false);
        }
        String realmGet$kind = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$kind();
        if (realmGet$kind != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.kindColKey, createRow, realmGet$kind, false);
        } else {
            Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.kindColKey, createRow, false);
        }
        String realmGet$isUsed = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$isUsed();
        if (realmGet$isUsed != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.isUsedColKey, createRow, realmGet$isUsed, false);
        } else {
            Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.isUsedColKey, createRow, false);
        }
        String realmGet$description = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.descriptionColKey, createRow, realmGet$description, false);
        } else {
            Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.descriptionColKey, createRow, false);
        }
        String realmGet$id = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.idColKey, createRow, realmGet$id, false);
        } else {
            Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.idColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(HorseRaceLampsBean.class);
        long nativePtr = table.getNativePtr();
        HorseRaceLampsBeanColumnInfo horseRaceLampsBeanColumnInfo = (HorseRaceLampsBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) HorseRaceLampsBean.class);
        while (it.hasNext()) {
            HorseRaceLampsBean horseRaceLampsBean = (HorseRaceLampsBean) it.next();
            if (!map2.containsKey(horseRaceLampsBean)) {
                if ((horseRaceLampsBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(horseRaceLampsBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) horseRaceLampsBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(horseRaceLampsBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(horseRaceLampsBean, Long.valueOf(createRow));
                com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxyInterface com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface = horseRaceLampsBean;
                String realmGet$position = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$position();
                if (realmGet$position != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.positionColKey, createRow, realmGet$position, false);
                } else {
                    Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.positionColKey, createRow, false);
                }
                String realmGet$style = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$style();
                if (realmGet$style != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.styleColKey, createRow, realmGet$style, false);
                } else {
                    Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.styleColKey, createRow, false);
                }
                String realmGet$color = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$color();
                if (realmGet$color != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.colorColKey, createRow, realmGet$color, false);
                } else {
                    Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.colorColKey, createRow, false);
                }
                String realmGet$direction = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$direction();
                if (realmGet$direction != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.directionColKey, createRow, realmGet$direction, false);
                } else {
                    Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.directionColKey, createRow, false);
                }
                String realmGet$content = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$content();
                if (realmGet$content != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.contentColKey, createRow, realmGet$content, false);
                } else {
                    Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.contentColKey, createRow, false);
                }
                String realmGet$name = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
                } else {
                    Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.nameColKey, createRow, false);
                }
                String realmGet$resourceId = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$resourceId();
                if (realmGet$resourceId != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.resourceIdColKey, createRow, realmGet$resourceId, false);
                } else {
                    Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.resourceIdColKey, createRow, false);
                }
                String realmGet$kind = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$kind();
                if (realmGet$kind != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.kindColKey, createRow, realmGet$kind, false);
                } else {
                    Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.kindColKey, createRow, false);
                }
                String realmGet$isUsed = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$isUsed();
                if (realmGet$isUsed != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.isUsedColKey, createRow, realmGet$isUsed, false);
                } else {
                    Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.isUsedColKey, createRow, false);
                }
                String realmGet$description = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.descriptionColKey, createRow, realmGet$description, false);
                } else {
                    Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.descriptionColKey, createRow, false);
                }
                String realmGet$id = com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(nativePtr, horseRaceLampsBeanColumnInfo.idColKey, createRow, realmGet$id, false);
                } else {
                    Table.nativeSetNull(nativePtr, horseRaceLampsBeanColumnInfo.idColKey, createRow, false);
                }
            }
        }
    }

    public static HorseRaceLampsBean createDetachedCopy(HorseRaceLampsBean horseRaceLampsBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        HorseRaceLampsBean horseRaceLampsBean2;
        if (i > i2 || horseRaceLampsBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(horseRaceLampsBean);
        if (cacheData == null) {
            horseRaceLampsBean2 = new HorseRaceLampsBean();
            map.put(horseRaceLampsBean, new RealmObjectProxy.CacheData(i, horseRaceLampsBean2));
        } else if (i >= cacheData.minDepth) {
            return (HorseRaceLampsBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            horseRaceLampsBean2 = (HorseRaceLampsBean) cacheData.object;
        }
        com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxyInterface com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface = horseRaceLampsBean2;
        com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxyInterface com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface2 = horseRaceLampsBean;
        com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$position(com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface2.realmGet$position());
        com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$style(com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface2.realmGet$style());
        com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$color(com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface2.realmGet$color());
        com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$direction(com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface2.realmGet$direction());
        com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$content(com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface2.realmGet$content());
        com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$name(com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface2.realmGet$name());
        com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$resourceId(com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface2.realmGet$resourceId());
        com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$kind(com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface2.realmGet$kind());
        com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$isUsed(com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface2.realmGet$isUsed());
        com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$description(com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface2.realmGet$description());
        com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface.realmSet$id(com_ciot_realm_db_ad_horseracelampsbeanrealmproxyinterface2.realmGet$id());
        return horseRaceLampsBean2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("HorseRaceLampsBean = proxy[");
        sb.append("{position:");
        String realmGet$position = realmGet$position();
        String str = Configurator.NULL;
        sb.append(realmGet$position != null ? realmGet$position() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{style:");
        sb.append(realmGet$style() != null ? realmGet$style() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{color:");
        sb.append(realmGet$color() != null ? realmGet$color() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{direction:");
        sb.append(realmGet$direction() != null ? realmGet$direction() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{content:");
        sb.append(realmGet$content() != null ? realmGet$content() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{name:");
        sb.append(realmGet$name() != null ? realmGet$name() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{resourceId:");
        sb.append(realmGet$resourceId() != null ? realmGet$resourceId() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{kind:");
        sb.append(realmGet$kind() != null ? realmGet$kind() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{isUsed:");
        sb.append(realmGet$isUsed() != null ? realmGet$isUsed() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{description:");
        sb.append(realmGet$description() != null ? realmGet$description() : str);
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
        com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy com_ciot_realm_db_ad_horseracelampsbeanrealmproxy = (com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_ad_horseracelampsbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_ad_horseracelampsbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_ad_horseracelampsbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
