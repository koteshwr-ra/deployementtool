package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.Person;
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

public class com_ciot_realm_db_PersonRealmProxy extends Person implements RealmObjectProxy, com_ciot_realm_db_PersonRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private PersonColumnInfo columnInfo;
    private ProxyState<Person> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Person";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class PersonColumnInfo extends ColumnInfo {
        long healthcodeColKey;
        long healthcolorColKey;
        long idColKey;
        long idcardColKey;
        long nameColKey;
        long typeColKey;

        PersonColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(6);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.typeColKey = addColumnDetails("type", "type", objectSchemaInfo);
            this.idcardColKey = addColumnDetails("idcard", "idcard", objectSchemaInfo);
            this.healthcodeColKey = addColumnDetails("healthcode", "healthcode", objectSchemaInfo);
            this.healthcolorColKey = addColumnDetails("healthcolor", "healthcolor", objectSchemaInfo);
        }

        PersonColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new PersonColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            PersonColumnInfo personColumnInfo = (PersonColumnInfo) columnInfo;
            PersonColumnInfo personColumnInfo2 = (PersonColumnInfo) columnInfo2;
            personColumnInfo2.nameColKey = personColumnInfo.nameColKey;
            personColumnInfo2.idColKey = personColumnInfo.idColKey;
            personColumnInfo2.typeColKey = personColumnInfo.typeColKey;
            personColumnInfo2.idcardColKey = personColumnInfo.idcardColKey;
            personColumnInfo2.healthcodeColKey = personColumnInfo.healthcodeColKey;
            personColumnInfo2.healthcolorColKey = personColumnInfo.healthcolorColKey;
        }
    }

    com_ciot_realm_db_PersonRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (PersonColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<Person> proxyState2 = new ProxyState<>(this);
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

    public String realmGet$type() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.typeColKey);
    }

    public void realmSet$type(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.typeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.typeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.typeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.typeColKey, row$realm.getObjectKey(), str, true);
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

    public String realmGet$healthcode() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.healthcodeColKey);
    }

    public void realmSet$healthcode(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.healthcodeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.healthcodeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.healthcodeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.healthcodeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$healthcolor() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.healthcolorColKey);
    }

    public void realmSet$healthcolor(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.healthcolorColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.healthcolorColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.healthcolorColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.healthcolorColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 6, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("id", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("type", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("idcard", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("healthcode", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("healthcolor", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static PersonColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new PersonColumnInfo(osSchemaInfo);
    }

    public static Person createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        Person person = (Person) realm.createObjectInternal(Person.class, true, Collections.emptyList());
        com_ciot_realm_db_PersonRealmProxyInterface com_ciot_realm_db_personrealmproxyinterface = person;
        if (jSONObject.has(ServiceProvider.NAME)) {
            if (jSONObject.isNull(ServiceProvider.NAME)) {
                com_ciot_realm_db_personrealmproxyinterface.realmSet$name((String) null);
            } else {
                com_ciot_realm_db_personrealmproxyinterface.realmSet$name(jSONObject.getString(ServiceProvider.NAME));
            }
        }
        if (jSONObject.has("id")) {
            if (jSONObject.isNull("id")) {
                com_ciot_realm_db_personrealmproxyinterface.realmSet$id((String) null);
            } else {
                com_ciot_realm_db_personrealmproxyinterface.realmSet$id(jSONObject.getString("id"));
            }
        }
        if (jSONObject.has("type")) {
            if (jSONObject.isNull("type")) {
                com_ciot_realm_db_personrealmproxyinterface.realmSet$type((String) null);
            } else {
                com_ciot_realm_db_personrealmproxyinterface.realmSet$type(jSONObject.getString("type"));
            }
        }
        if (jSONObject.has("idcard")) {
            if (jSONObject.isNull("idcard")) {
                com_ciot_realm_db_personrealmproxyinterface.realmSet$idcard((String) null);
            } else {
                com_ciot_realm_db_personrealmproxyinterface.realmSet$idcard(jSONObject.getString("idcard"));
            }
        }
        if (jSONObject.has("healthcode")) {
            if (jSONObject.isNull("healthcode")) {
                com_ciot_realm_db_personrealmproxyinterface.realmSet$healthcode((String) null);
            } else {
                com_ciot_realm_db_personrealmproxyinterface.realmSet$healthcode(jSONObject.getString("healthcode"));
            }
        }
        if (jSONObject.has("healthcolor")) {
            if (jSONObject.isNull("healthcolor")) {
                com_ciot_realm_db_personrealmproxyinterface.realmSet$healthcolor((String) null);
            } else {
                com_ciot_realm_db_personrealmproxyinterface.realmSet$healthcolor(jSONObject.getString("healthcolor"));
            }
        }
        return person;
    }

    public static Person createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        Person person = new Person();
        com_ciot_realm_db_PersonRealmProxyInterface com_ciot_realm_db_personrealmproxyinterface = person;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(ServiceProvider.NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personrealmproxyinterface.realmSet$name(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_personrealmproxyinterface.realmSet$name((String) null);
                }
            } else if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personrealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_personrealmproxyinterface.realmSet$id((String) null);
                }
            } else if (nextName.equals("type")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personrealmproxyinterface.realmSet$type(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_personrealmproxyinterface.realmSet$type((String) null);
                }
            } else if (nextName.equals("idcard")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personrealmproxyinterface.realmSet$idcard(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_personrealmproxyinterface.realmSet$idcard((String) null);
                }
            } else if (nextName.equals("healthcode")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_personrealmproxyinterface.realmSet$healthcode(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_personrealmproxyinterface.realmSet$healthcode((String) null);
                }
            } else if (!nextName.equals("healthcolor")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_personrealmproxyinterface.realmSet$healthcolor(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_personrealmproxyinterface.realmSet$healthcolor((String) null);
            }
        }
        jsonReader.endObject();
        return (Person) realm.copyToRealm(person, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_PersonRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) Person.class), false, Collections.emptyList());
        com_ciot_realm_db_PersonRealmProxy com_ciot_realm_db_personrealmproxy = new com_ciot_realm_db_PersonRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_personrealmproxy;
    }

    public static Person copyOrUpdate(Realm realm, PersonColumnInfo personColumnInfo, Person person, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((person instanceof RealmObjectProxy) && !RealmObject.isFrozen(person)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) person;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return person;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(person);
        if (realmObjectProxy2 != null) {
            return (Person) realmObjectProxy2;
        }
        return copy(realm, personColumnInfo, person, z, map, set);
    }

    public static Person copy(Realm realm, PersonColumnInfo personColumnInfo, Person person, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(person);
        if (realmObjectProxy != null) {
            return (Person) realmObjectProxy;
        }
        com_ciot_realm_db_PersonRealmProxyInterface com_ciot_realm_db_personrealmproxyinterface = person;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Person.class), set);
        osObjectBuilder.addString(personColumnInfo.nameColKey, com_ciot_realm_db_personrealmproxyinterface.realmGet$name());
        osObjectBuilder.addString(personColumnInfo.idColKey, com_ciot_realm_db_personrealmproxyinterface.realmGet$id());
        osObjectBuilder.addString(personColumnInfo.typeColKey, com_ciot_realm_db_personrealmproxyinterface.realmGet$type());
        osObjectBuilder.addString(personColumnInfo.idcardColKey, com_ciot_realm_db_personrealmproxyinterface.realmGet$idcard());
        osObjectBuilder.addString(personColumnInfo.healthcodeColKey, com_ciot_realm_db_personrealmproxyinterface.realmGet$healthcode());
        osObjectBuilder.addString(personColumnInfo.healthcolorColKey, com_ciot_realm_db_personrealmproxyinterface.realmGet$healthcolor());
        com_ciot_realm_db_PersonRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(person, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, Person person, Map<RealmModel, Long> map) {
        if ((person instanceof RealmObjectProxy) && !RealmObject.isFrozen(person)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) person;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Person.class);
        long nativePtr = table.getNativePtr();
        PersonColumnInfo personColumnInfo = (PersonColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Person.class);
        long createRow = OsObject.createRow(table);
        map.put(person, Long.valueOf(createRow));
        com_ciot_realm_db_PersonRealmProxyInterface com_ciot_realm_db_personrealmproxyinterface = person;
        String realmGet$name = com_ciot_realm_db_personrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, personColumnInfo.nameColKey, createRow, realmGet$name, false);
        }
        String realmGet$id = com_ciot_realm_db_personrealmproxyinterface.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(nativePtr, personColumnInfo.idColKey, createRow, realmGet$id, false);
        }
        String realmGet$type = com_ciot_realm_db_personrealmproxyinterface.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(nativePtr, personColumnInfo.typeColKey, createRow, realmGet$type, false);
        }
        String realmGet$idcard = com_ciot_realm_db_personrealmproxyinterface.realmGet$idcard();
        if (realmGet$idcard != null) {
            Table.nativeSetString(nativePtr, personColumnInfo.idcardColKey, createRow, realmGet$idcard, false);
        }
        String realmGet$healthcode = com_ciot_realm_db_personrealmproxyinterface.realmGet$healthcode();
        if (realmGet$healthcode != null) {
            Table.nativeSetString(nativePtr, personColumnInfo.healthcodeColKey, createRow, realmGet$healthcode, false);
        }
        String realmGet$healthcolor = com_ciot_realm_db_personrealmproxyinterface.realmGet$healthcolor();
        if (realmGet$healthcolor != null) {
            Table.nativeSetString(nativePtr, personColumnInfo.healthcolorColKey, createRow, realmGet$healthcolor, false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Person.class);
        long nativePtr = table.getNativePtr();
        PersonColumnInfo personColumnInfo = (PersonColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Person.class);
        while (it.hasNext()) {
            Person person = (Person) it.next();
            if (!map2.containsKey(person)) {
                if ((person instanceof RealmObjectProxy) && !RealmObject.isFrozen(person)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) person;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(person, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(person, Long.valueOf(createRow));
                com_ciot_realm_db_PersonRealmProxyInterface com_ciot_realm_db_personrealmproxyinterface = person;
                String realmGet$name = com_ciot_realm_db_personrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, personColumnInfo.nameColKey, createRow, realmGet$name, false);
                }
                String realmGet$id = com_ciot_realm_db_personrealmproxyinterface.realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(nativePtr, personColumnInfo.idColKey, createRow, realmGet$id, false);
                }
                String realmGet$type = com_ciot_realm_db_personrealmproxyinterface.realmGet$type();
                if (realmGet$type != null) {
                    Table.nativeSetString(nativePtr, personColumnInfo.typeColKey, createRow, realmGet$type, false);
                }
                String realmGet$idcard = com_ciot_realm_db_personrealmproxyinterface.realmGet$idcard();
                if (realmGet$idcard != null) {
                    Table.nativeSetString(nativePtr, personColumnInfo.idcardColKey, createRow, realmGet$idcard, false);
                }
                String realmGet$healthcode = com_ciot_realm_db_personrealmproxyinterface.realmGet$healthcode();
                if (realmGet$healthcode != null) {
                    Table.nativeSetString(nativePtr, personColumnInfo.healthcodeColKey, createRow, realmGet$healthcode, false);
                }
                String realmGet$healthcolor = com_ciot_realm_db_personrealmproxyinterface.realmGet$healthcolor();
                if (realmGet$healthcolor != null) {
                    Table.nativeSetString(nativePtr, personColumnInfo.healthcolorColKey, createRow, realmGet$healthcolor, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Person person, Map<RealmModel, Long> map) {
        if ((person instanceof RealmObjectProxy) && !RealmObject.isFrozen(person)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) person;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Person.class);
        long nativePtr = table.getNativePtr();
        PersonColumnInfo personColumnInfo = (PersonColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Person.class);
        long createRow = OsObject.createRow(table);
        map.put(person, Long.valueOf(createRow));
        com_ciot_realm_db_PersonRealmProxyInterface com_ciot_realm_db_personrealmproxyinterface = person;
        String realmGet$name = com_ciot_realm_db_personrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, personColumnInfo.nameColKey, createRow, realmGet$name, false);
        } else {
            Table.nativeSetNull(nativePtr, personColumnInfo.nameColKey, createRow, false);
        }
        String realmGet$id = com_ciot_realm_db_personrealmproxyinterface.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(nativePtr, personColumnInfo.idColKey, createRow, realmGet$id, false);
        } else {
            Table.nativeSetNull(nativePtr, personColumnInfo.idColKey, createRow, false);
        }
        String realmGet$type = com_ciot_realm_db_personrealmproxyinterface.realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(nativePtr, personColumnInfo.typeColKey, createRow, realmGet$type, false);
        } else {
            Table.nativeSetNull(nativePtr, personColumnInfo.typeColKey, createRow, false);
        }
        String realmGet$idcard = com_ciot_realm_db_personrealmproxyinterface.realmGet$idcard();
        if (realmGet$idcard != null) {
            Table.nativeSetString(nativePtr, personColumnInfo.idcardColKey, createRow, realmGet$idcard, false);
        } else {
            Table.nativeSetNull(nativePtr, personColumnInfo.idcardColKey, createRow, false);
        }
        String realmGet$healthcode = com_ciot_realm_db_personrealmproxyinterface.realmGet$healthcode();
        if (realmGet$healthcode != null) {
            Table.nativeSetString(nativePtr, personColumnInfo.healthcodeColKey, createRow, realmGet$healthcode, false);
        } else {
            Table.nativeSetNull(nativePtr, personColumnInfo.healthcodeColKey, createRow, false);
        }
        String realmGet$healthcolor = com_ciot_realm_db_personrealmproxyinterface.realmGet$healthcolor();
        if (realmGet$healthcolor != null) {
            Table.nativeSetString(nativePtr, personColumnInfo.healthcolorColKey, createRow, realmGet$healthcolor, false);
        } else {
            Table.nativeSetNull(nativePtr, personColumnInfo.healthcolorColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Person.class);
        long nativePtr = table.getNativePtr();
        PersonColumnInfo personColumnInfo = (PersonColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Person.class);
        while (it.hasNext()) {
            Person person = (Person) it.next();
            if (!map2.containsKey(person)) {
                if ((person instanceof RealmObjectProxy) && !RealmObject.isFrozen(person)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) person;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(person, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(person, Long.valueOf(createRow));
                com_ciot_realm_db_PersonRealmProxyInterface com_ciot_realm_db_personrealmproxyinterface = person;
                String realmGet$name = com_ciot_realm_db_personrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, personColumnInfo.nameColKey, createRow, realmGet$name, false);
                } else {
                    Table.nativeSetNull(nativePtr, personColumnInfo.nameColKey, createRow, false);
                }
                String realmGet$id = com_ciot_realm_db_personrealmproxyinterface.realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(nativePtr, personColumnInfo.idColKey, createRow, realmGet$id, false);
                } else {
                    Table.nativeSetNull(nativePtr, personColumnInfo.idColKey, createRow, false);
                }
                String realmGet$type = com_ciot_realm_db_personrealmproxyinterface.realmGet$type();
                if (realmGet$type != null) {
                    Table.nativeSetString(nativePtr, personColumnInfo.typeColKey, createRow, realmGet$type, false);
                } else {
                    Table.nativeSetNull(nativePtr, personColumnInfo.typeColKey, createRow, false);
                }
                String realmGet$idcard = com_ciot_realm_db_personrealmproxyinterface.realmGet$idcard();
                if (realmGet$idcard != null) {
                    Table.nativeSetString(nativePtr, personColumnInfo.idcardColKey, createRow, realmGet$idcard, false);
                } else {
                    Table.nativeSetNull(nativePtr, personColumnInfo.idcardColKey, createRow, false);
                }
                String realmGet$healthcode = com_ciot_realm_db_personrealmproxyinterface.realmGet$healthcode();
                if (realmGet$healthcode != null) {
                    Table.nativeSetString(nativePtr, personColumnInfo.healthcodeColKey, createRow, realmGet$healthcode, false);
                } else {
                    Table.nativeSetNull(nativePtr, personColumnInfo.healthcodeColKey, createRow, false);
                }
                String realmGet$healthcolor = com_ciot_realm_db_personrealmproxyinterface.realmGet$healthcolor();
                if (realmGet$healthcolor != null) {
                    Table.nativeSetString(nativePtr, personColumnInfo.healthcolorColKey, createRow, realmGet$healthcolor, false);
                } else {
                    Table.nativeSetNull(nativePtr, personColumnInfo.healthcolorColKey, createRow, false);
                }
            }
        }
    }

    public static Person createDetachedCopy(Person person, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        Person person2;
        if (i > i2 || person == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(person);
        if (cacheData == null) {
            person2 = new Person();
            map.put(person, new RealmObjectProxy.CacheData(i, person2));
        } else if (i >= cacheData.minDepth) {
            return (Person) cacheData.object;
        } else {
            cacheData.minDepth = i;
            person2 = (Person) cacheData.object;
        }
        com_ciot_realm_db_PersonRealmProxyInterface com_ciot_realm_db_personrealmproxyinterface = person2;
        com_ciot_realm_db_PersonRealmProxyInterface com_ciot_realm_db_personrealmproxyinterface2 = person;
        com_ciot_realm_db_personrealmproxyinterface.realmSet$name(com_ciot_realm_db_personrealmproxyinterface2.realmGet$name());
        com_ciot_realm_db_personrealmproxyinterface.realmSet$id(com_ciot_realm_db_personrealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_personrealmproxyinterface.realmSet$type(com_ciot_realm_db_personrealmproxyinterface2.realmGet$type());
        com_ciot_realm_db_personrealmproxyinterface.realmSet$idcard(com_ciot_realm_db_personrealmproxyinterface2.realmGet$idcard());
        com_ciot_realm_db_personrealmproxyinterface.realmSet$healthcode(com_ciot_realm_db_personrealmproxyinterface2.realmGet$healthcode());
        com_ciot_realm_db_personrealmproxyinterface.realmSet$healthcolor(com_ciot_realm_db_personrealmproxyinterface2.realmGet$healthcolor());
        return person2;
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
        com_ciot_realm_db_PersonRealmProxy com_ciot_realm_db_personrealmproxy = (com_ciot_realm_db_PersonRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_personrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_personrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_personrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
