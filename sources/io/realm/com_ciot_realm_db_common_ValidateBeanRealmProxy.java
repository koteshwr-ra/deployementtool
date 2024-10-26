package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.common.ValidateBean;
import com.limpoxe.support.servicemanager.ServiceProvider;
import com.limpoxe.support.servicemanager.util.ParamUtil;
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

public class com_ciot_realm_db_common_ValidateBeanRealmProxy extends ValidateBean implements RealmObjectProxy, com_ciot_realm_db_common_ValidateBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ValidateBeanColumnInfo columnInfo;
    private ProxyState<ValidateBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "ValidateBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class ValidateBeanColumnInfo extends ColumnInfo {
        long faceColKey;
        long idcardColKey;
        long localeColKey;
        long nameColKey;
        long phoneColKey;
        long resultColKey;

        ValidateBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(6);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.faceColKey = addColumnDetails("face", "face", objectSchemaInfo);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
            this.phoneColKey = addColumnDetails("phone", "phone", objectSchemaInfo);
            this.idcardColKey = addColumnDetails("idcard", "idcard", objectSchemaInfo);
            this.localeColKey = addColumnDetails("locale", "locale", objectSchemaInfo);
            this.resultColKey = addColumnDetails(ParamUtil.result, ParamUtil.result, objectSchemaInfo);
        }

        ValidateBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new ValidateBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            ValidateBeanColumnInfo validateBeanColumnInfo = (ValidateBeanColumnInfo) columnInfo;
            ValidateBeanColumnInfo validateBeanColumnInfo2 = (ValidateBeanColumnInfo) columnInfo2;
            validateBeanColumnInfo2.faceColKey = validateBeanColumnInfo.faceColKey;
            validateBeanColumnInfo2.nameColKey = validateBeanColumnInfo.nameColKey;
            validateBeanColumnInfo2.phoneColKey = validateBeanColumnInfo.phoneColKey;
            validateBeanColumnInfo2.idcardColKey = validateBeanColumnInfo.idcardColKey;
            validateBeanColumnInfo2.localeColKey = validateBeanColumnInfo.localeColKey;
            validateBeanColumnInfo2.resultColKey = validateBeanColumnInfo.resultColKey;
        }
    }

    com_ciot_realm_db_common_ValidateBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ValidateBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<ValidateBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
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

    public int realmGet$locale() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.localeColKey);
    }

    public void realmSet$locale(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.localeColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.localeColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public String realmGet$result() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.resultColKey);
    }

    public void realmSet$result(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.resultColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.resultColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.resultColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.resultColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 6, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("face", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("phone", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("idcard", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("locale", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty(ParamUtil.result, RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ValidateBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new ValidateBeanColumnInfo(osSchemaInfo);
    }

    public static ValidateBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        ValidateBean validateBean = (ValidateBean) realm.createObjectInternal(ValidateBean.class, true, Collections.emptyList());
        com_ciot_realm_db_common_ValidateBeanRealmProxyInterface com_ciot_realm_db_common_validatebeanrealmproxyinterface = validateBean;
        if (jSONObject.has("face")) {
            if (jSONObject.isNull("face")) {
                com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$face((String) null);
            } else {
                com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$face(jSONObject.getString("face"));
            }
        }
        if (jSONObject.has(ServiceProvider.NAME)) {
            if (jSONObject.isNull(ServiceProvider.NAME)) {
                com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$name((String) null);
            } else {
                com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$name(jSONObject.getString(ServiceProvider.NAME));
            }
        }
        if (jSONObject.has("phone")) {
            if (jSONObject.isNull("phone")) {
                com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$phone((String) null);
            } else {
                com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$phone(jSONObject.getString("phone"));
            }
        }
        if (jSONObject.has("idcard")) {
            if (jSONObject.isNull("idcard")) {
                com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$idcard((String) null);
            } else {
                com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$idcard(jSONObject.getString("idcard"));
            }
        }
        if (jSONObject.has("locale")) {
            if (!jSONObject.isNull("locale")) {
                com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$locale(jSONObject.getInt("locale"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'locale' to null.");
            }
        }
        if (jSONObject.has(ParamUtil.result)) {
            if (jSONObject.isNull(ParamUtil.result)) {
                com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$result((String) null);
            } else {
                com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$result(jSONObject.getString(ParamUtil.result));
            }
        }
        return validateBean;
    }

    public static ValidateBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        ValidateBean validateBean = new ValidateBean();
        com_ciot_realm_db_common_ValidateBeanRealmProxyInterface com_ciot_realm_db_common_validatebeanrealmproxyinterface = validateBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("face")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$face(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$face((String) null);
                }
            } else if (nextName.equals(ServiceProvider.NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$name(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$name((String) null);
                }
            } else if (nextName.equals("phone")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$phone(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$phone((String) null);
                }
            } else if (nextName.equals("idcard")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$idcard(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$idcard((String) null);
                }
            } else if (nextName.equals("locale")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$locale(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'locale' to null.");
                }
            } else if (!nextName.equals(ParamUtil.result)) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$result(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$result((String) null);
            }
        }
        jsonReader.endObject();
        return (ValidateBean) realm.copyToRealm(validateBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_common_ValidateBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) ValidateBean.class), false, Collections.emptyList());
        com_ciot_realm_db_common_ValidateBeanRealmProxy com_ciot_realm_db_common_validatebeanrealmproxy = new com_ciot_realm_db_common_ValidateBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_common_validatebeanrealmproxy;
    }

    public static ValidateBean copyOrUpdate(Realm realm, ValidateBeanColumnInfo validateBeanColumnInfo, ValidateBean validateBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((validateBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(validateBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) validateBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return validateBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(validateBean);
        if (realmObjectProxy2 != null) {
            return (ValidateBean) realmObjectProxy2;
        }
        return copy(realm, validateBeanColumnInfo, validateBean, z, map, set);
    }

    public static ValidateBean copy(Realm realm, ValidateBeanColumnInfo validateBeanColumnInfo, ValidateBean validateBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(validateBean);
        if (realmObjectProxy != null) {
            return (ValidateBean) realmObjectProxy;
        }
        com_ciot_realm_db_common_ValidateBeanRealmProxyInterface com_ciot_realm_db_common_validatebeanrealmproxyinterface = validateBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(ValidateBean.class), set);
        osObjectBuilder.addString(validateBeanColumnInfo.faceColKey, com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$face());
        osObjectBuilder.addString(validateBeanColumnInfo.nameColKey, com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$name());
        osObjectBuilder.addString(validateBeanColumnInfo.phoneColKey, com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$phone());
        osObjectBuilder.addString(validateBeanColumnInfo.idcardColKey, com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$idcard());
        osObjectBuilder.addInteger(validateBeanColumnInfo.localeColKey, Integer.valueOf(com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$locale()));
        osObjectBuilder.addString(validateBeanColumnInfo.resultColKey, com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$result());
        com_ciot_realm_db_common_ValidateBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(validateBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, ValidateBean validateBean, Map<RealmModel, Long> map) {
        ValidateBean validateBean2 = validateBean;
        if ((validateBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(validateBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) validateBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(ValidateBean.class);
        long nativePtr = table.getNativePtr();
        ValidateBeanColumnInfo validateBeanColumnInfo = (ValidateBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ValidateBean.class);
        long createRow = OsObject.createRow(table);
        map.put(validateBean2, Long.valueOf(createRow));
        com_ciot_realm_db_common_ValidateBeanRealmProxyInterface com_ciot_realm_db_common_validatebeanrealmproxyinterface = validateBean2;
        String realmGet$face = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$face();
        if (realmGet$face != null) {
            Table.nativeSetString(nativePtr, validateBeanColumnInfo.faceColKey, createRow, realmGet$face, false);
        }
        String realmGet$name = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, validateBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
        }
        String realmGet$phone = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$phone();
        if (realmGet$phone != null) {
            Table.nativeSetString(nativePtr, validateBeanColumnInfo.phoneColKey, createRow, realmGet$phone, false);
        }
        String realmGet$idcard = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$idcard();
        if (realmGet$idcard != null) {
            Table.nativeSetString(nativePtr, validateBeanColumnInfo.idcardColKey, createRow, realmGet$idcard, false);
        }
        Table.nativeSetLong(nativePtr, validateBeanColumnInfo.localeColKey, createRow, (long) com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$locale(), false);
        String realmGet$result = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$result();
        if (realmGet$result != null) {
            Table.nativeSetString(nativePtr, validateBeanColumnInfo.resultColKey, createRow, realmGet$result, false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(ValidateBean.class);
        long nativePtr = table.getNativePtr();
        ValidateBeanColumnInfo validateBeanColumnInfo = (ValidateBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ValidateBean.class);
        while (it.hasNext()) {
            ValidateBean validateBean = (ValidateBean) it.next();
            if (!map2.containsKey(validateBean)) {
                if ((validateBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(validateBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) validateBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(validateBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(validateBean, Long.valueOf(createRow));
                com_ciot_realm_db_common_ValidateBeanRealmProxyInterface com_ciot_realm_db_common_validatebeanrealmproxyinterface = validateBean;
                String realmGet$face = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$face();
                if (realmGet$face != null) {
                    Table.nativeSetString(nativePtr, validateBeanColumnInfo.faceColKey, createRow, realmGet$face, false);
                }
                String realmGet$name = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, validateBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
                }
                String realmGet$phone = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$phone();
                if (realmGet$phone != null) {
                    Table.nativeSetString(nativePtr, validateBeanColumnInfo.phoneColKey, createRow, realmGet$phone, false);
                }
                String realmGet$idcard = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$idcard();
                if (realmGet$idcard != null) {
                    Table.nativeSetString(nativePtr, validateBeanColumnInfo.idcardColKey, createRow, realmGet$idcard, false);
                }
                Table.nativeSetLong(nativePtr, validateBeanColumnInfo.localeColKey, createRow, (long) com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$locale(), false);
                String realmGet$result = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$result();
                if (realmGet$result != null) {
                    Table.nativeSetString(nativePtr, validateBeanColumnInfo.resultColKey, createRow, realmGet$result, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, ValidateBean validateBean, Map<RealmModel, Long> map) {
        ValidateBean validateBean2 = validateBean;
        if ((validateBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(validateBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) validateBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(ValidateBean.class);
        long nativePtr = table.getNativePtr();
        ValidateBeanColumnInfo validateBeanColumnInfo = (ValidateBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ValidateBean.class);
        long createRow = OsObject.createRow(table);
        map.put(validateBean2, Long.valueOf(createRow));
        com_ciot_realm_db_common_ValidateBeanRealmProxyInterface com_ciot_realm_db_common_validatebeanrealmproxyinterface = validateBean2;
        String realmGet$face = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$face();
        if (realmGet$face != null) {
            Table.nativeSetString(nativePtr, validateBeanColumnInfo.faceColKey, createRow, realmGet$face, false);
        } else {
            Table.nativeSetNull(nativePtr, validateBeanColumnInfo.faceColKey, createRow, false);
        }
        String realmGet$name = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, validateBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
        } else {
            Table.nativeSetNull(nativePtr, validateBeanColumnInfo.nameColKey, createRow, false);
        }
        String realmGet$phone = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$phone();
        if (realmGet$phone != null) {
            Table.nativeSetString(nativePtr, validateBeanColumnInfo.phoneColKey, createRow, realmGet$phone, false);
        } else {
            Table.nativeSetNull(nativePtr, validateBeanColumnInfo.phoneColKey, createRow, false);
        }
        String realmGet$idcard = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$idcard();
        if (realmGet$idcard != null) {
            Table.nativeSetString(nativePtr, validateBeanColumnInfo.idcardColKey, createRow, realmGet$idcard, false);
        } else {
            Table.nativeSetNull(nativePtr, validateBeanColumnInfo.idcardColKey, createRow, false);
        }
        Table.nativeSetLong(nativePtr, validateBeanColumnInfo.localeColKey, createRow, (long) com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$locale(), false);
        String realmGet$result = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$result();
        if (realmGet$result != null) {
            Table.nativeSetString(nativePtr, validateBeanColumnInfo.resultColKey, createRow, realmGet$result, false);
        } else {
            Table.nativeSetNull(nativePtr, validateBeanColumnInfo.resultColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(ValidateBean.class);
        long nativePtr = table.getNativePtr();
        ValidateBeanColumnInfo validateBeanColumnInfo = (ValidateBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ValidateBean.class);
        while (it.hasNext()) {
            ValidateBean validateBean = (ValidateBean) it.next();
            if (!map2.containsKey(validateBean)) {
                if ((validateBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(validateBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) validateBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(validateBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(validateBean, Long.valueOf(createRow));
                com_ciot_realm_db_common_ValidateBeanRealmProxyInterface com_ciot_realm_db_common_validatebeanrealmproxyinterface = validateBean;
                String realmGet$face = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$face();
                if (realmGet$face != null) {
                    Table.nativeSetString(nativePtr, validateBeanColumnInfo.faceColKey, createRow, realmGet$face, false);
                } else {
                    Table.nativeSetNull(nativePtr, validateBeanColumnInfo.faceColKey, createRow, false);
                }
                String realmGet$name = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, validateBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
                } else {
                    Table.nativeSetNull(nativePtr, validateBeanColumnInfo.nameColKey, createRow, false);
                }
                String realmGet$phone = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$phone();
                if (realmGet$phone != null) {
                    Table.nativeSetString(nativePtr, validateBeanColumnInfo.phoneColKey, createRow, realmGet$phone, false);
                } else {
                    Table.nativeSetNull(nativePtr, validateBeanColumnInfo.phoneColKey, createRow, false);
                }
                String realmGet$idcard = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$idcard();
                if (realmGet$idcard != null) {
                    Table.nativeSetString(nativePtr, validateBeanColumnInfo.idcardColKey, createRow, realmGet$idcard, false);
                } else {
                    Table.nativeSetNull(nativePtr, validateBeanColumnInfo.idcardColKey, createRow, false);
                }
                Table.nativeSetLong(nativePtr, validateBeanColumnInfo.localeColKey, createRow, (long) com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$locale(), false);
                String realmGet$result = com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmGet$result();
                if (realmGet$result != null) {
                    Table.nativeSetString(nativePtr, validateBeanColumnInfo.resultColKey, createRow, realmGet$result, false);
                } else {
                    Table.nativeSetNull(nativePtr, validateBeanColumnInfo.resultColKey, createRow, false);
                }
            }
        }
    }

    public static ValidateBean createDetachedCopy(ValidateBean validateBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        ValidateBean validateBean2;
        if (i > i2 || validateBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(validateBean);
        if (cacheData == null) {
            validateBean2 = new ValidateBean();
            map.put(validateBean, new RealmObjectProxy.CacheData(i, validateBean2));
        } else if (i >= cacheData.minDepth) {
            return (ValidateBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            validateBean2 = (ValidateBean) cacheData.object;
        }
        com_ciot_realm_db_common_ValidateBeanRealmProxyInterface com_ciot_realm_db_common_validatebeanrealmproxyinterface = validateBean2;
        com_ciot_realm_db_common_ValidateBeanRealmProxyInterface com_ciot_realm_db_common_validatebeanrealmproxyinterface2 = validateBean;
        com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$face(com_ciot_realm_db_common_validatebeanrealmproxyinterface2.realmGet$face());
        com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$name(com_ciot_realm_db_common_validatebeanrealmproxyinterface2.realmGet$name());
        com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$phone(com_ciot_realm_db_common_validatebeanrealmproxyinterface2.realmGet$phone());
        com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$idcard(com_ciot_realm_db_common_validatebeanrealmproxyinterface2.realmGet$idcard());
        com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$locale(com_ciot_realm_db_common_validatebeanrealmproxyinterface2.realmGet$locale());
        com_ciot_realm_db_common_validatebeanrealmproxyinterface.realmSet$result(com_ciot_realm_db_common_validatebeanrealmproxyinterface2.realmGet$result());
        return validateBean2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("ValidateBean = proxy[");
        sb.append("{face:");
        String realmGet$face = realmGet$face();
        String str = Configurator.NULL;
        sb.append(realmGet$face != null ? realmGet$face() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{name:");
        sb.append(realmGet$name() != null ? realmGet$name() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{phone:");
        sb.append(realmGet$phone() != null ? realmGet$phone() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{idcard:");
        sb.append(realmGet$idcard() != null ? realmGet$idcard() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{locale:");
        sb.append(realmGet$locale());
        sb.append("}");
        sb.append(",");
        sb.append("{result:");
        if (realmGet$result() != null) {
            str = realmGet$result();
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
        com_ciot_realm_db_common_ValidateBeanRealmProxy com_ciot_realm_db_common_validatebeanrealmproxy = (com_ciot_realm_db_common_ValidateBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_common_validatebeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_common_validatebeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_common_validatebeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
