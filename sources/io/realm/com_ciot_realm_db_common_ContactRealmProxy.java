package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.common.Contact;
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

public class com_ciot_realm_db_common_ContactRealmProxy extends Contact implements RealmObjectProxy, com_ciot_realm_db_common_ContactRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ContactColumnInfo columnInfo;
    private ProxyState<Contact> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Contact";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class ContactColumnInfo extends ColumnInfo {
        long idColKey;
        long nameColKey;
        long phoneColKey;

        ContactColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(3);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
            this.phoneColKey = addColumnDetails("phone", "phone", objectSchemaInfo);
        }

        ContactColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new ContactColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            ContactColumnInfo contactColumnInfo = (ContactColumnInfo) columnInfo;
            ContactColumnInfo contactColumnInfo2 = (ContactColumnInfo) columnInfo2;
            contactColumnInfo2.idColKey = contactColumnInfo.idColKey;
            contactColumnInfo2.nameColKey = contactColumnInfo.nameColKey;
            contactColumnInfo2.phoneColKey = contactColumnInfo.phoneColKey;
        }
    }

    com_ciot_realm_db_common_ContactRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ContactColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<Contact> proxyState2 = new ProxyState<>(this);
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 3, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("id", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("phone", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ContactColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new ContactColumnInfo(osSchemaInfo);
    }

    public static Contact createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        Contact contact = (Contact) realm.createObjectInternal(Contact.class, true, Collections.emptyList());
        com_ciot_realm_db_common_ContactRealmProxyInterface com_ciot_realm_db_common_contactrealmproxyinterface = contact;
        if (jSONObject.has("id")) {
            if (jSONObject.isNull("id")) {
                com_ciot_realm_db_common_contactrealmproxyinterface.realmSet$id((String) null);
            } else {
                com_ciot_realm_db_common_contactrealmproxyinterface.realmSet$id(jSONObject.getString("id"));
            }
        }
        if (jSONObject.has(ServiceProvider.NAME)) {
            if (jSONObject.isNull(ServiceProvider.NAME)) {
                com_ciot_realm_db_common_contactrealmproxyinterface.realmSet$name((String) null);
            } else {
                com_ciot_realm_db_common_contactrealmproxyinterface.realmSet$name(jSONObject.getString(ServiceProvider.NAME));
            }
        }
        if (jSONObject.has("phone")) {
            if (jSONObject.isNull("phone")) {
                com_ciot_realm_db_common_contactrealmproxyinterface.realmSet$phone((String) null);
            } else {
                com_ciot_realm_db_common_contactrealmproxyinterface.realmSet$phone(jSONObject.getString("phone"));
            }
        }
        return contact;
    }

    public static Contact createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        Contact contact = new Contact();
        com_ciot_realm_db_common_ContactRealmProxyInterface com_ciot_realm_db_common_contactrealmproxyinterface = contact;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_contactrealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_contactrealmproxyinterface.realmSet$id((String) null);
                }
            } else if (nextName.equals(ServiceProvider.NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_common_contactrealmproxyinterface.realmSet$name(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_common_contactrealmproxyinterface.realmSet$name((String) null);
                }
            } else if (!nextName.equals("phone")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_common_contactrealmproxyinterface.realmSet$phone(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_common_contactrealmproxyinterface.realmSet$phone((String) null);
            }
        }
        jsonReader.endObject();
        return (Contact) realm.copyToRealm(contact, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_common_ContactRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) Contact.class), false, Collections.emptyList());
        com_ciot_realm_db_common_ContactRealmProxy com_ciot_realm_db_common_contactrealmproxy = new com_ciot_realm_db_common_ContactRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_common_contactrealmproxy;
    }

    public static Contact copyOrUpdate(Realm realm, ContactColumnInfo contactColumnInfo, Contact contact, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((contact instanceof RealmObjectProxy) && !RealmObject.isFrozen(contact)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) contact;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return contact;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(contact);
        if (realmObjectProxy2 != null) {
            return (Contact) realmObjectProxy2;
        }
        return copy(realm, contactColumnInfo, contact, z, map, set);
    }

    public static Contact copy(Realm realm, ContactColumnInfo contactColumnInfo, Contact contact, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(contact);
        if (realmObjectProxy != null) {
            return (Contact) realmObjectProxy;
        }
        com_ciot_realm_db_common_ContactRealmProxyInterface com_ciot_realm_db_common_contactrealmproxyinterface = contact;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Contact.class), set);
        osObjectBuilder.addString(contactColumnInfo.idColKey, com_ciot_realm_db_common_contactrealmproxyinterface.realmGet$id());
        osObjectBuilder.addString(contactColumnInfo.nameColKey, com_ciot_realm_db_common_contactrealmproxyinterface.realmGet$name());
        osObjectBuilder.addString(contactColumnInfo.phoneColKey, com_ciot_realm_db_common_contactrealmproxyinterface.realmGet$phone());
        com_ciot_realm_db_common_ContactRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(contact, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, Contact contact, Map<RealmModel, Long> map) {
        if ((contact instanceof RealmObjectProxy) && !RealmObject.isFrozen(contact)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) contact;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Contact.class);
        long nativePtr = table.getNativePtr();
        ContactColumnInfo contactColumnInfo = (ContactColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Contact.class);
        long createRow = OsObject.createRow(table);
        map.put(contact, Long.valueOf(createRow));
        com_ciot_realm_db_common_ContactRealmProxyInterface com_ciot_realm_db_common_contactrealmproxyinterface = contact;
        String realmGet$id = com_ciot_realm_db_common_contactrealmproxyinterface.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(nativePtr, contactColumnInfo.idColKey, createRow, realmGet$id, false);
        }
        String realmGet$name = com_ciot_realm_db_common_contactrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, contactColumnInfo.nameColKey, createRow, realmGet$name, false);
        }
        String realmGet$phone = com_ciot_realm_db_common_contactrealmproxyinterface.realmGet$phone();
        if (realmGet$phone != null) {
            Table.nativeSetString(nativePtr, contactColumnInfo.phoneColKey, createRow, realmGet$phone, false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Contact.class);
        long nativePtr = table.getNativePtr();
        ContactColumnInfo contactColumnInfo = (ContactColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Contact.class);
        while (it.hasNext()) {
            Contact contact = (Contact) it.next();
            if (!map2.containsKey(contact)) {
                if ((contact instanceof RealmObjectProxy) && !RealmObject.isFrozen(contact)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) contact;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(contact, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(contact, Long.valueOf(createRow));
                com_ciot_realm_db_common_ContactRealmProxyInterface com_ciot_realm_db_common_contactrealmproxyinterface = contact;
                String realmGet$id = com_ciot_realm_db_common_contactrealmproxyinterface.realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(nativePtr, contactColumnInfo.idColKey, createRow, realmGet$id, false);
                }
                String realmGet$name = com_ciot_realm_db_common_contactrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, contactColumnInfo.nameColKey, createRow, realmGet$name, false);
                }
                String realmGet$phone = com_ciot_realm_db_common_contactrealmproxyinterface.realmGet$phone();
                if (realmGet$phone != null) {
                    Table.nativeSetString(nativePtr, contactColumnInfo.phoneColKey, createRow, realmGet$phone, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Contact contact, Map<RealmModel, Long> map) {
        if ((contact instanceof RealmObjectProxy) && !RealmObject.isFrozen(contact)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) contact;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Contact.class);
        long nativePtr = table.getNativePtr();
        ContactColumnInfo contactColumnInfo = (ContactColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Contact.class);
        long createRow = OsObject.createRow(table);
        map.put(contact, Long.valueOf(createRow));
        com_ciot_realm_db_common_ContactRealmProxyInterface com_ciot_realm_db_common_contactrealmproxyinterface = contact;
        String realmGet$id = com_ciot_realm_db_common_contactrealmproxyinterface.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(nativePtr, contactColumnInfo.idColKey, createRow, realmGet$id, false);
        } else {
            Table.nativeSetNull(nativePtr, contactColumnInfo.idColKey, createRow, false);
        }
        String realmGet$name = com_ciot_realm_db_common_contactrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, contactColumnInfo.nameColKey, createRow, realmGet$name, false);
        } else {
            Table.nativeSetNull(nativePtr, contactColumnInfo.nameColKey, createRow, false);
        }
        String realmGet$phone = com_ciot_realm_db_common_contactrealmproxyinterface.realmGet$phone();
        if (realmGet$phone != null) {
            Table.nativeSetString(nativePtr, contactColumnInfo.phoneColKey, createRow, realmGet$phone, false);
        } else {
            Table.nativeSetNull(nativePtr, contactColumnInfo.phoneColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Contact.class);
        long nativePtr = table.getNativePtr();
        ContactColumnInfo contactColumnInfo = (ContactColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Contact.class);
        while (it.hasNext()) {
            Contact contact = (Contact) it.next();
            if (!map2.containsKey(contact)) {
                if ((contact instanceof RealmObjectProxy) && !RealmObject.isFrozen(contact)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) contact;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(contact, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(contact, Long.valueOf(createRow));
                com_ciot_realm_db_common_ContactRealmProxyInterface com_ciot_realm_db_common_contactrealmproxyinterface = contact;
                String realmGet$id = com_ciot_realm_db_common_contactrealmproxyinterface.realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(nativePtr, contactColumnInfo.idColKey, createRow, realmGet$id, false);
                } else {
                    Table.nativeSetNull(nativePtr, contactColumnInfo.idColKey, createRow, false);
                }
                String realmGet$name = com_ciot_realm_db_common_contactrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, contactColumnInfo.nameColKey, createRow, realmGet$name, false);
                } else {
                    Table.nativeSetNull(nativePtr, contactColumnInfo.nameColKey, createRow, false);
                }
                String realmGet$phone = com_ciot_realm_db_common_contactrealmproxyinterface.realmGet$phone();
                if (realmGet$phone != null) {
                    Table.nativeSetString(nativePtr, contactColumnInfo.phoneColKey, createRow, realmGet$phone, false);
                } else {
                    Table.nativeSetNull(nativePtr, contactColumnInfo.phoneColKey, createRow, false);
                }
            }
        }
    }

    public static Contact createDetachedCopy(Contact contact, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        Contact contact2;
        if (i > i2 || contact == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(contact);
        if (cacheData == null) {
            contact2 = new Contact();
            map.put(contact, new RealmObjectProxy.CacheData(i, contact2));
        } else if (i >= cacheData.minDepth) {
            return (Contact) cacheData.object;
        } else {
            cacheData.minDepth = i;
            contact2 = (Contact) cacheData.object;
        }
        com_ciot_realm_db_common_ContactRealmProxyInterface com_ciot_realm_db_common_contactrealmproxyinterface = contact2;
        com_ciot_realm_db_common_ContactRealmProxyInterface com_ciot_realm_db_common_contactrealmproxyinterface2 = contact;
        com_ciot_realm_db_common_contactrealmproxyinterface.realmSet$id(com_ciot_realm_db_common_contactrealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_common_contactrealmproxyinterface.realmSet$name(com_ciot_realm_db_common_contactrealmproxyinterface2.realmGet$name());
        com_ciot_realm_db_common_contactrealmproxyinterface.realmSet$phone(com_ciot_realm_db_common_contactrealmproxyinterface2.realmGet$phone());
        return contact2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("Contact = proxy[");
        sb.append("{id:");
        String realmGet$id = realmGet$id();
        String str = Configurator.NULL;
        sb.append(realmGet$id != null ? realmGet$id() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{name:");
        sb.append(realmGet$name() != null ? realmGet$name() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{phone:");
        if (realmGet$phone() != null) {
            str = realmGet$phone();
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
        com_ciot_realm_db_common_ContactRealmProxy com_ciot_realm_db_common_contactrealmproxy = (com_ciot_realm_db_common_ContactRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_common_contactrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_common_contactrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_common_contactrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
