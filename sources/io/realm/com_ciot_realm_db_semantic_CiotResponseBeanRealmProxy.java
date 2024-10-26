package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.semantic.AnswerBean;
import com.ciot.realm.db.semantic.CiotResponseBean;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_semantic_AnswerBeanRealmProxy;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.objectstore.OsObjectBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import xcrash.TombstoneParser;

public class com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy extends CiotResponseBean implements RealmObjectProxy, com_ciot_realm_db_semantic_CiotResponseBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private CiotResponseBeanColumnInfo columnInfo;
    private ProxyState<CiotResponseBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "CiotResponseBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class CiotResponseBeanColumnInfo extends ColumnInfo {
        long codeColKey;
        long dataColKey;
        long messageColKey;
        long ttlColKey;

        CiotResponseBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(4);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.codeColKey = addColumnDetails(TombstoneParser.keyCode, TombstoneParser.keyCode, objectSchemaInfo);
            this.dataColKey = addColumnDetails("data", "data", objectSchemaInfo);
            this.messageColKey = addColumnDetails("message", "message", objectSchemaInfo);
            this.ttlColKey = addColumnDetails("ttl", "ttl", objectSchemaInfo);
        }

        CiotResponseBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new CiotResponseBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            CiotResponseBeanColumnInfo ciotResponseBeanColumnInfo = (CiotResponseBeanColumnInfo) columnInfo;
            CiotResponseBeanColumnInfo ciotResponseBeanColumnInfo2 = (CiotResponseBeanColumnInfo) columnInfo2;
            ciotResponseBeanColumnInfo2.codeColKey = ciotResponseBeanColumnInfo.codeColKey;
            ciotResponseBeanColumnInfo2.dataColKey = ciotResponseBeanColumnInfo.dataColKey;
            ciotResponseBeanColumnInfo2.messageColKey = ciotResponseBeanColumnInfo.messageColKey;
            ciotResponseBeanColumnInfo2.ttlColKey = ciotResponseBeanColumnInfo.ttlColKey;
        }
    }

    com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (CiotResponseBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<CiotResponseBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public int realmGet$code() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.codeColKey);
    }

    public void realmSet$code(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.codeColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.codeColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public AnswerBean realmGet$data() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.dataColKey)) {
            return null;
        }
        return (AnswerBean) this.proxyState.getRealm$realm().get(AnswerBean.class, this.proxyState.getRow$realm().getLink(this.columnInfo.dataColKey), false, Collections.emptyList());
    }

    public void realmSet$data(AnswerBean answerBean) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (answerBean == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.dataColKey);
                return;
            }
            this.proxyState.checkValidObject(answerBean);
            this.proxyState.getRow$realm().setLink(this.columnInfo.dataColKey, ((RealmObjectProxy) answerBean).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("data")) {
            if (answerBean != null && !RealmObject.isManaged(answerBean)) {
                answerBean = (AnswerBean) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(answerBean, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (answerBean == null) {
                row$realm.nullifyLink(this.columnInfo.dataColKey);
                return;
            }
            this.proxyState.checkValidObject(answerBean);
            row$realm.getTable().setLink(this.columnInfo.dataColKey, row$realm.getObjectKey(), ((RealmObjectProxy) answerBean).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public String realmGet$message() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.messageColKey);
    }

    public void realmSet$message(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.messageColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.messageColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.messageColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.messageColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public int realmGet$ttl() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.ttlColKey);
    }

    public void realmSet$ttl(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.ttlColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.ttlColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 4, 0);
        builder.addPersistedProperty(TombstoneParser.keyCode, RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedLinkProperty("data", RealmFieldType.OBJECT, com_ciot_realm_db_semantic_AnswerBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("message", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("ttl", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static CiotResponseBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new CiotResponseBeanColumnInfo(osSchemaInfo);
    }

    public static CiotResponseBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        ArrayList arrayList = new ArrayList(1);
        if (jSONObject.has("data")) {
            arrayList.add("data");
        }
        CiotResponseBean ciotResponseBean = (CiotResponseBean) realm.createObjectInternal(CiotResponseBean.class, true, arrayList);
        com_ciot_realm_db_semantic_CiotResponseBeanRealmProxyInterface com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface = ciotResponseBean;
        if (jSONObject.has(TombstoneParser.keyCode)) {
            if (!jSONObject.isNull(TombstoneParser.keyCode)) {
                com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmSet$code(jSONObject.getInt(TombstoneParser.keyCode));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'code' to null.");
            }
        }
        if (jSONObject.has("data")) {
            if (jSONObject.isNull("data")) {
                com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmSet$data((AnswerBean) null);
            } else {
                com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmSet$data(com_ciot_realm_db_semantic_AnswerBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject.getJSONObject("data"), z));
            }
        }
        if (jSONObject.has("message")) {
            if (jSONObject.isNull("message")) {
                com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmSet$message((String) null);
            } else {
                com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmSet$message(jSONObject.getString("message"));
            }
        }
        if (jSONObject.has("ttl")) {
            if (!jSONObject.isNull("ttl")) {
                com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmSet$ttl(jSONObject.getInt("ttl"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'ttl' to null.");
            }
        }
        return ciotResponseBean;
    }

    public static CiotResponseBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        CiotResponseBean ciotResponseBean = new CiotResponseBean();
        com_ciot_realm_db_semantic_CiotResponseBeanRealmProxyInterface com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface = ciotResponseBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(TombstoneParser.keyCode)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmSet$code(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'code' to null.");
                }
            } else if (nextName.equals("data")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmSet$data((AnswerBean) null);
                } else {
                    com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmSet$data(com_ciot_realm_db_semantic_AnswerBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("message")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmSet$message(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmSet$message((String) null);
                }
            } else if (!nextName.equals("ttl")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmSet$ttl(jsonReader.nextInt());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'ttl' to null.");
            }
        }
        jsonReader.endObject();
        return (CiotResponseBean) realm.copyToRealm(ciotResponseBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) CiotResponseBean.class), false, Collections.emptyList());
        com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy com_ciot_realm_db_semantic_ciotresponsebeanrealmproxy = new com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_semantic_ciotresponsebeanrealmproxy;
    }

    public static CiotResponseBean copyOrUpdate(Realm realm, CiotResponseBeanColumnInfo ciotResponseBeanColumnInfo, CiotResponseBean ciotResponseBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((ciotResponseBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(ciotResponseBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) ciotResponseBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return ciotResponseBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(ciotResponseBean);
        if (realmObjectProxy2 != null) {
            return (CiotResponseBean) realmObjectProxy2;
        }
        return copy(realm, ciotResponseBeanColumnInfo, ciotResponseBean, z, map, set);
    }

    public static CiotResponseBean copy(Realm realm, CiotResponseBeanColumnInfo ciotResponseBeanColumnInfo, CiotResponseBean ciotResponseBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(ciotResponseBean);
        if (realmObjectProxy != null) {
            return (CiotResponseBean) realmObjectProxy;
        }
        com_ciot_realm_db_semantic_CiotResponseBeanRealmProxyInterface com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface = ciotResponseBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(CiotResponseBean.class), set);
        osObjectBuilder.addInteger(ciotResponseBeanColumnInfo.codeColKey, Integer.valueOf(com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$code()));
        osObjectBuilder.addString(ciotResponseBeanColumnInfo.messageColKey, com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$message());
        osObjectBuilder.addInteger(ciotResponseBeanColumnInfo.ttlColKey, Integer.valueOf(com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$ttl()));
        com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(ciotResponseBean, newProxyInstance);
        AnswerBean realmGet$data = com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$data();
        if (realmGet$data == null) {
            newProxyInstance.realmSet$data((AnswerBean) null);
        } else {
            AnswerBean answerBean = (AnswerBean) map.get(realmGet$data);
            if (answerBean != null) {
                newProxyInstance.realmSet$data(answerBean);
            } else {
                newProxyInstance.realmSet$data(com_ciot_realm_db_semantic_AnswerBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_semantic_AnswerBeanRealmProxy.AnswerBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AnswerBean.class), realmGet$data, z, map, set));
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, CiotResponseBean ciotResponseBean, Map<RealmModel, Long> map) {
        Realm realm2 = realm;
        CiotResponseBean ciotResponseBean2 = ciotResponseBean;
        Map<RealmModel, Long> map2 = map;
        if ((ciotResponseBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(ciotResponseBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) ciotResponseBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(CiotResponseBean.class);
        long nativePtr = table.getNativePtr();
        CiotResponseBeanColumnInfo ciotResponseBeanColumnInfo = (CiotResponseBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CiotResponseBean.class);
        long createRow = OsObject.createRow(table);
        map2.put(ciotResponseBean2, Long.valueOf(createRow));
        com_ciot_realm_db_semantic_CiotResponseBeanRealmProxyInterface com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface = ciotResponseBean2;
        Table.nativeSetLong(nativePtr, ciotResponseBeanColumnInfo.codeColKey, createRow, (long) com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$code(), false);
        AnswerBean realmGet$data = com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$data();
        if (realmGet$data != null) {
            Long l = map2.get(realmGet$data);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_semantic_AnswerBeanRealmProxy.insert(realm2, realmGet$data, map2));
            }
            Table.nativeSetLink(nativePtr, ciotResponseBeanColumnInfo.dataColKey, createRow, l.longValue(), false);
        }
        String realmGet$message = com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$message();
        if (realmGet$message != null) {
            Table.nativeSetString(nativePtr, ciotResponseBeanColumnInfo.messageColKey, createRow, realmGet$message, false);
        }
        Table.nativeSetLong(nativePtr, ciotResponseBeanColumnInfo.ttlColKey, createRow, (long) com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$ttl(), false);
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(CiotResponseBean.class);
        long nativePtr = table.getNativePtr();
        CiotResponseBeanColumnInfo ciotResponseBeanColumnInfo = (CiotResponseBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CiotResponseBean.class);
        while (it.hasNext()) {
            CiotResponseBean ciotResponseBean = (CiotResponseBean) it.next();
            if (!map2.containsKey(ciotResponseBean)) {
                if ((ciotResponseBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(ciotResponseBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) ciotResponseBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(ciotResponseBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(ciotResponseBean, Long.valueOf(createRow));
                com_ciot_realm_db_semantic_CiotResponseBeanRealmProxyInterface com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface = ciotResponseBean;
                Table.nativeSetLong(nativePtr, ciotResponseBeanColumnInfo.codeColKey, createRow, (long) com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$code(), false);
                AnswerBean realmGet$data = com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$data();
                if (realmGet$data != null) {
                    Long l = map2.get(realmGet$data);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_semantic_AnswerBeanRealmProxy.insert(realm2, realmGet$data, map2));
                    }
                    table.setLink(ciotResponseBeanColumnInfo.dataColKey, createRow, l.longValue(), false);
                }
                String realmGet$message = com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$message();
                if (realmGet$message != null) {
                    Table.nativeSetString(nativePtr, ciotResponseBeanColumnInfo.messageColKey, createRow, realmGet$message, false);
                }
                Table.nativeSetLong(nativePtr, ciotResponseBeanColumnInfo.ttlColKey, createRow, (long) com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$ttl(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, CiotResponseBean ciotResponseBean, Map<RealmModel, Long> map) {
        Realm realm2 = realm;
        CiotResponseBean ciotResponseBean2 = ciotResponseBean;
        Map<RealmModel, Long> map2 = map;
        if ((ciotResponseBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(ciotResponseBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) ciotResponseBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(CiotResponseBean.class);
        long nativePtr = table.getNativePtr();
        CiotResponseBeanColumnInfo ciotResponseBeanColumnInfo = (CiotResponseBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CiotResponseBean.class);
        long createRow = OsObject.createRow(table);
        map2.put(ciotResponseBean2, Long.valueOf(createRow));
        com_ciot_realm_db_semantic_CiotResponseBeanRealmProxyInterface com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface = ciotResponseBean2;
        Table.nativeSetLong(nativePtr, ciotResponseBeanColumnInfo.codeColKey, createRow, (long) com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$code(), false);
        AnswerBean realmGet$data = com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$data();
        if (realmGet$data != null) {
            Long l = map2.get(realmGet$data);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_semantic_AnswerBeanRealmProxy.insertOrUpdate(realm2, realmGet$data, map2));
            }
            Table.nativeSetLink(nativePtr, ciotResponseBeanColumnInfo.dataColKey, createRow, l.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, ciotResponseBeanColumnInfo.dataColKey, createRow);
        }
        String realmGet$message = com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$message();
        if (realmGet$message != null) {
            Table.nativeSetString(nativePtr, ciotResponseBeanColumnInfo.messageColKey, createRow, realmGet$message, false);
        } else {
            Table.nativeSetNull(nativePtr, ciotResponseBeanColumnInfo.messageColKey, createRow, false);
        }
        Table.nativeSetLong(nativePtr, ciotResponseBeanColumnInfo.ttlColKey, createRow, (long) com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$ttl(), false);
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(CiotResponseBean.class);
        long nativePtr = table.getNativePtr();
        CiotResponseBeanColumnInfo ciotResponseBeanColumnInfo = (CiotResponseBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) CiotResponseBean.class);
        while (it.hasNext()) {
            CiotResponseBean ciotResponseBean = (CiotResponseBean) it.next();
            if (!map2.containsKey(ciotResponseBean)) {
                if ((ciotResponseBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(ciotResponseBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) ciotResponseBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(ciotResponseBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(ciotResponseBean, Long.valueOf(createRow));
                com_ciot_realm_db_semantic_CiotResponseBeanRealmProxyInterface com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface = ciotResponseBean;
                Table.nativeSetLong(nativePtr, ciotResponseBeanColumnInfo.codeColKey, createRow, (long) com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$code(), false);
                AnswerBean realmGet$data = com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$data();
                if (realmGet$data != null) {
                    Long l = map2.get(realmGet$data);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_semantic_AnswerBeanRealmProxy.insertOrUpdate(realm2, realmGet$data, map2));
                    }
                    Table.nativeSetLink(nativePtr, ciotResponseBeanColumnInfo.dataColKey, createRow, l.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, ciotResponseBeanColumnInfo.dataColKey, createRow);
                }
                String realmGet$message = com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$message();
                if (realmGet$message != null) {
                    Table.nativeSetString(nativePtr, ciotResponseBeanColumnInfo.messageColKey, createRow, realmGet$message, false);
                } else {
                    Table.nativeSetNull(nativePtr, ciotResponseBeanColumnInfo.messageColKey, createRow, false);
                }
                Table.nativeSetLong(nativePtr, ciotResponseBeanColumnInfo.ttlColKey, createRow, (long) com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmGet$ttl(), false);
            }
        }
    }

    public static CiotResponseBean createDetachedCopy(CiotResponseBean ciotResponseBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        CiotResponseBean ciotResponseBean2;
        if (i > i2 || ciotResponseBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(ciotResponseBean);
        if (cacheData == null) {
            ciotResponseBean2 = new CiotResponseBean();
            map.put(ciotResponseBean, new RealmObjectProxy.CacheData(i, ciotResponseBean2));
        } else if (i >= cacheData.minDepth) {
            return (CiotResponseBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            ciotResponseBean2 = (CiotResponseBean) cacheData.object;
        }
        com_ciot_realm_db_semantic_CiotResponseBeanRealmProxyInterface com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface = ciotResponseBean2;
        com_ciot_realm_db_semantic_CiotResponseBeanRealmProxyInterface com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface2 = ciotResponseBean;
        com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmSet$code(com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface2.realmGet$code());
        com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmSet$data(com_ciot_realm_db_semantic_AnswerBeanRealmProxy.createDetachedCopy(com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface2.realmGet$data(), i + 1, i2, map));
        com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmSet$message(com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface2.realmGet$message());
        com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface.realmSet$ttl(com_ciot_realm_db_semantic_ciotresponsebeanrealmproxyinterface2.realmGet$ttl());
        return ciotResponseBean2;
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
        com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy com_ciot_realm_db_semantic_ciotresponsebeanrealmproxy = (com_ciot_realm_db_semantic_CiotResponseBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_semantic_ciotresponsebeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_semantic_ciotresponsebeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_semantic_ciotresponsebeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
