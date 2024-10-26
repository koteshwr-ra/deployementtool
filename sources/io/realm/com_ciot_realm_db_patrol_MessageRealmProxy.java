package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.alibaba.android.arouter.compiler.utils.Consts;
import com.ciot.realm.db.patrol.Message;
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

public class com_ciot_realm_db_patrol_MessageRealmProxy extends Message implements RealmObjectProxy, com_ciot_realm_db_patrol_MessageRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private MessageColumnInfo columnInfo;
    private ProxyState<Message> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Message";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class MessageColumnInfo extends ColumnInfo {
        long enableColKey;
        long phoneColKey;

        MessageColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.enableColKey = addColumnDetails(Consts.VALUE_ENABLE, Consts.VALUE_ENABLE, objectSchemaInfo);
            this.phoneColKey = addColumnDetails("phone", "phone", objectSchemaInfo);
        }

        MessageColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new MessageColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            MessageColumnInfo messageColumnInfo = (MessageColumnInfo) columnInfo;
            MessageColumnInfo messageColumnInfo2 = (MessageColumnInfo) columnInfo2;
            messageColumnInfo2.enableColKey = messageColumnInfo.enableColKey;
            messageColumnInfo2.phoneColKey = messageColumnInfo.phoneColKey;
        }
    }

    com_ciot_realm_db_patrol_MessageRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (MessageColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<Message> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public boolean realmGet$enable() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.enableColKey);
    }

    public void realmSet$enable(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.enableColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.enableColKey, row$realm.getObjectKey(), z, true);
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 2, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty(Consts.VALUE_ENABLE, RealmFieldType.BOOLEAN, false, false, true);
        builder2.addPersistedProperty("phone", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static MessageColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new MessageColumnInfo(osSchemaInfo);
    }

    public static Message createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        Message message = (Message) realm.createObjectInternal(Message.class, true, Collections.emptyList());
        com_ciot_realm_db_patrol_MessageRealmProxyInterface com_ciot_realm_db_patrol_messagerealmproxyinterface = message;
        if (jSONObject.has(Consts.VALUE_ENABLE)) {
            if (!jSONObject.isNull(Consts.VALUE_ENABLE)) {
                com_ciot_realm_db_patrol_messagerealmproxyinterface.realmSet$enable(jSONObject.getBoolean(Consts.VALUE_ENABLE));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'enable' to null.");
            }
        }
        if (jSONObject.has("phone")) {
            if (jSONObject.isNull("phone")) {
                com_ciot_realm_db_patrol_messagerealmproxyinterface.realmSet$phone((String) null);
            } else {
                com_ciot_realm_db_patrol_messagerealmproxyinterface.realmSet$phone(jSONObject.getString("phone"));
            }
        }
        return message;
    }

    public static Message createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        Message message = new Message();
        com_ciot_realm_db_patrol_MessageRealmProxyInterface com_ciot_realm_db_patrol_messagerealmproxyinterface = message;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(Consts.VALUE_ENABLE)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_messagerealmproxyinterface.realmSet$enable(jsonReader.nextBoolean());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'enable' to null.");
                }
            } else if (!nextName.equals("phone")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_patrol_messagerealmproxyinterface.realmSet$phone(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_patrol_messagerealmproxyinterface.realmSet$phone((String) null);
            }
        }
        jsonReader.endObject();
        return (Message) realm.copyToRealm(message, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_patrol_MessageRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) Message.class), false, Collections.emptyList());
        com_ciot_realm_db_patrol_MessageRealmProxy com_ciot_realm_db_patrol_messagerealmproxy = new com_ciot_realm_db_patrol_MessageRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_patrol_messagerealmproxy;
    }

    public static Message copyOrUpdate(Realm realm, MessageColumnInfo messageColumnInfo, Message message, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((message instanceof RealmObjectProxy) && !RealmObject.isFrozen(message)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) message;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return message;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(message);
        if (realmObjectProxy2 != null) {
            return (Message) realmObjectProxy2;
        }
        return copy(realm, messageColumnInfo, message, z, map, set);
    }

    public static Message copy(Realm realm, MessageColumnInfo messageColumnInfo, Message message, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(message);
        if (realmObjectProxy != null) {
            return (Message) realmObjectProxy;
        }
        com_ciot_realm_db_patrol_MessageRealmProxyInterface com_ciot_realm_db_patrol_messagerealmproxyinterface = message;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Message.class), set);
        osObjectBuilder.addBoolean(messageColumnInfo.enableColKey, Boolean.valueOf(com_ciot_realm_db_patrol_messagerealmproxyinterface.realmGet$enable()));
        osObjectBuilder.addString(messageColumnInfo.phoneColKey, com_ciot_realm_db_patrol_messagerealmproxyinterface.realmGet$phone());
        com_ciot_realm_db_patrol_MessageRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(message, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, Message message, Map<RealmModel, Long> map) {
        if ((message instanceof RealmObjectProxy) && !RealmObject.isFrozen(message)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) message;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Message.class);
        long nativePtr = table.getNativePtr();
        MessageColumnInfo messageColumnInfo = (MessageColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Message.class);
        long createRow = OsObject.createRow(table);
        map.put(message, Long.valueOf(createRow));
        com_ciot_realm_db_patrol_MessageRealmProxyInterface com_ciot_realm_db_patrol_messagerealmproxyinterface = message;
        Table.nativeSetBoolean(nativePtr, messageColumnInfo.enableColKey, createRow, com_ciot_realm_db_patrol_messagerealmproxyinterface.realmGet$enable(), false);
        String realmGet$phone = com_ciot_realm_db_patrol_messagerealmproxyinterface.realmGet$phone();
        if (realmGet$phone != null) {
            Table.nativeSetString(nativePtr, messageColumnInfo.phoneColKey, createRow, realmGet$phone, false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Message.class);
        long nativePtr = table.getNativePtr();
        MessageColumnInfo messageColumnInfo = (MessageColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Message.class);
        while (it.hasNext()) {
            Message message = (Message) it.next();
            if (!map2.containsKey(message)) {
                if ((message instanceof RealmObjectProxy) && !RealmObject.isFrozen(message)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) message;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(message, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(message, Long.valueOf(createRow));
                com_ciot_realm_db_patrol_MessageRealmProxyInterface com_ciot_realm_db_patrol_messagerealmproxyinterface = message;
                Table.nativeSetBoolean(nativePtr, messageColumnInfo.enableColKey, createRow, com_ciot_realm_db_patrol_messagerealmproxyinterface.realmGet$enable(), false);
                String realmGet$phone = com_ciot_realm_db_patrol_messagerealmproxyinterface.realmGet$phone();
                if (realmGet$phone != null) {
                    Table.nativeSetString(nativePtr, messageColumnInfo.phoneColKey, createRow, realmGet$phone, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Message message, Map<RealmModel, Long> map) {
        if ((message instanceof RealmObjectProxy) && !RealmObject.isFrozen(message)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) message;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Message.class);
        long nativePtr = table.getNativePtr();
        MessageColumnInfo messageColumnInfo = (MessageColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Message.class);
        long createRow = OsObject.createRow(table);
        map.put(message, Long.valueOf(createRow));
        com_ciot_realm_db_patrol_MessageRealmProxyInterface com_ciot_realm_db_patrol_messagerealmproxyinterface = message;
        Table.nativeSetBoolean(nativePtr, messageColumnInfo.enableColKey, createRow, com_ciot_realm_db_patrol_messagerealmproxyinterface.realmGet$enable(), false);
        String realmGet$phone = com_ciot_realm_db_patrol_messagerealmproxyinterface.realmGet$phone();
        if (realmGet$phone != null) {
            Table.nativeSetString(nativePtr, messageColumnInfo.phoneColKey, createRow, realmGet$phone, false);
        } else {
            Table.nativeSetNull(nativePtr, messageColumnInfo.phoneColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Message.class);
        long nativePtr = table.getNativePtr();
        MessageColumnInfo messageColumnInfo = (MessageColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Message.class);
        while (it.hasNext()) {
            Message message = (Message) it.next();
            if (!map2.containsKey(message)) {
                if ((message instanceof RealmObjectProxy) && !RealmObject.isFrozen(message)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) message;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(message, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(message, Long.valueOf(createRow));
                com_ciot_realm_db_patrol_MessageRealmProxyInterface com_ciot_realm_db_patrol_messagerealmproxyinterface = message;
                Table.nativeSetBoolean(nativePtr, messageColumnInfo.enableColKey, createRow, com_ciot_realm_db_patrol_messagerealmproxyinterface.realmGet$enable(), false);
                String realmGet$phone = com_ciot_realm_db_patrol_messagerealmproxyinterface.realmGet$phone();
                if (realmGet$phone != null) {
                    Table.nativeSetString(nativePtr, messageColumnInfo.phoneColKey, createRow, realmGet$phone, false);
                } else {
                    Table.nativeSetNull(nativePtr, messageColumnInfo.phoneColKey, createRow, false);
                }
            }
        }
    }

    public static Message createDetachedCopy(Message message, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        Message message2;
        if (i > i2 || message == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(message);
        if (cacheData == null) {
            message2 = new Message();
            map.put(message, new RealmObjectProxy.CacheData(i, message2));
        } else if (i >= cacheData.minDepth) {
            return (Message) cacheData.object;
        } else {
            cacheData.minDepth = i;
            message2 = (Message) cacheData.object;
        }
        com_ciot_realm_db_patrol_MessageRealmProxyInterface com_ciot_realm_db_patrol_messagerealmproxyinterface = message2;
        com_ciot_realm_db_patrol_MessageRealmProxyInterface com_ciot_realm_db_patrol_messagerealmproxyinterface2 = message;
        com_ciot_realm_db_patrol_messagerealmproxyinterface.realmSet$enable(com_ciot_realm_db_patrol_messagerealmproxyinterface2.realmGet$enable());
        com_ciot_realm_db_patrol_messagerealmproxyinterface.realmSet$phone(com_ciot_realm_db_patrol_messagerealmproxyinterface2.realmGet$phone());
        return message2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("Message = proxy[");
        sb.append("{enable:");
        sb.append(realmGet$enable());
        sb.append("}");
        sb.append(",");
        sb.append("{phone:");
        sb.append(realmGet$phone() != null ? realmGet$phone() : Configurator.NULL);
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
        com_ciot_realm_db_patrol_MessageRealmProxy com_ciot_realm_db_patrol_messagerealmproxy = (com_ciot_realm_db_patrol_MessageRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_patrol_messagerealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_patrol_messagerealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_patrol_messagerealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
