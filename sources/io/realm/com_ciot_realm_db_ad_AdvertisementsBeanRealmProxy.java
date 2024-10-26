package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.ad.AdvertisementsBean;
import com.ciot.realm.db.ad.HorseRaceLampsBean;
import com.ciot.realm.db.ad.ResourcesBean;
import com.limpoxe.support.servicemanager.ServiceProvider;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy;
import io.realm.com_ciot_realm_db_ad_ResourcesBeanRealmProxy;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
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
import org.apache.log4j.spi.Configurator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy extends AdvertisementsBean implements RealmObjectProxy, com_ciot_realm_db_ad_AdvertisementsBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private AdvertisementsBeanColumnInfo columnInfo;
    private RealmList<HorseRaceLampsBean> horseRaceLampsRealmList;
    private ProxyState<AdvertisementsBean> proxyState;
    private RealmList<ResourcesBean> resourcesRealmList;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "AdvertisementsBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class AdvertisementsBeanColumnInfo extends ColumnInfo {
        long beginColKey;
        long createtimeColKey;
        long descriptionColKey;
        long endColKey;
        long horseRaceLampsColKey;
        long idColKey;
        long kindColKey;
        long nameColKey;
        long resourcesColKey;

        AdvertisementsBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(9);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
            this.kindColKey = addColumnDetails("kind", "kind", objectSchemaInfo);
            this.beginColKey = addColumnDetails("begin", "begin", objectSchemaInfo);
            this.endColKey = addColumnDetails("end", "end", objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
            this.createtimeColKey = addColumnDetails("createtime", "createtime", objectSchemaInfo);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.resourcesColKey = addColumnDetails("resources", "resources", objectSchemaInfo);
            this.horseRaceLampsColKey = addColumnDetails("horseRaceLamps", "horseRaceLamps", objectSchemaInfo);
        }

        AdvertisementsBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new AdvertisementsBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            AdvertisementsBeanColumnInfo advertisementsBeanColumnInfo = (AdvertisementsBeanColumnInfo) columnInfo;
            AdvertisementsBeanColumnInfo advertisementsBeanColumnInfo2 = (AdvertisementsBeanColumnInfo) columnInfo2;
            advertisementsBeanColumnInfo2.nameColKey = advertisementsBeanColumnInfo.nameColKey;
            advertisementsBeanColumnInfo2.kindColKey = advertisementsBeanColumnInfo.kindColKey;
            advertisementsBeanColumnInfo2.beginColKey = advertisementsBeanColumnInfo.beginColKey;
            advertisementsBeanColumnInfo2.endColKey = advertisementsBeanColumnInfo.endColKey;
            advertisementsBeanColumnInfo2.descriptionColKey = advertisementsBeanColumnInfo.descriptionColKey;
            advertisementsBeanColumnInfo2.createtimeColKey = advertisementsBeanColumnInfo.createtimeColKey;
            advertisementsBeanColumnInfo2.idColKey = advertisementsBeanColumnInfo.idColKey;
            advertisementsBeanColumnInfo2.resourcesColKey = advertisementsBeanColumnInfo.resourcesColKey;
            advertisementsBeanColumnInfo2.horseRaceLampsColKey = advertisementsBeanColumnInfo.horseRaceLampsColKey;
        }
    }

    com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (AdvertisementsBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<AdvertisementsBean> proxyState2 = new ProxyState<>(this);
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

    public int realmGet$begin() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.beginColKey);
    }

    public void realmSet$begin(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.beginColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.beginColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public int realmGet$end() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.endColKey);
    }

    public void realmSet$end(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.endColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.endColKey, row$realm.getObjectKey(), (long) i, true);
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

    public RealmList<ResourcesBean> realmGet$resources() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<ResourcesBean> realmList = this.resourcesRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<ResourcesBean> realmList2 = new RealmList<>(ResourcesBean.class, this.proxyState.getRow$realm().getModelList(this.columnInfo.resourcesColKey), this.proxyState.getRealm$realm());
        this.resourcesRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$resources(RealmList<ResourcesBean> realmList) {
        int i = 0;
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("resources")) {
                return;
            }
            if (realmList != null && !realmList.isManaged()) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<ResourcesBean> realmList2 = new RealmList<>();
                Iterator<ResourcesBean> it = realmList.iterator();
                while (it.hasNext()) {
                    ResourcesBean next = it.next();
                    if (next == null || RealmObject.isManaged(next)) {
                        realmList2.add(next);
                    } else {
                        realmList2.add((ResourcesBean) realm.copyToRealm(next, new ImportFlag[0]));
                    }
                }
                realmList = realmList2;
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList modelList = this.proxyState.getRow$realm().getModelList(this.columnInfo.resourcesColKey);
        if (realmList == null || ((long) realmList.size()) != modelList.size()) {
            modelList.removeAll();
            if (realmList != null) {
                int size = realmList.size();
                while (i < size) {
                    ResourcesBean resourcesBean = realmList.get(i);
                    this.proxyState.checkValidObject(resourcesBean);
                    modelList.addRow(((RealmObjectProxy) resourcesBean).realmGet$proxyState().getRow$realm().getObjectKey());
                    i++;
                }
                return;
            }
            return;
        }
        int size2 = realmList.size();
        while (i < size2) {
            ResourcesBean resourcesBean2 = realmList.get(i);
            this.proxyState.checkValidObject(resourcesBean2);
            modelList.setRow((long) i, ((RealmObjectProxy) resourcesBean2).realmGet$proxyState().getRow$realm().getObjectKey());
            i++;
        }
    }

    public RealmList<HorseRaceLampsBean> realmGet$horseRaceLamps() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<HorseRaceLampsBean> realmList = this.horseRaceLampsRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<HorseRaceLampsBean> realmList2 = new RealmList<>(HorseRaceLampsBean.class, this.proxyState.getRow$realm().getModelList(this.columnInfo.horseRaceLampsColKey), this.proxyState.getRealm$realm());
        this.horseRaceLampsRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$horseRaceLamps(RealmList<HorseRaceLampsBean> realmList) {
        int i = 0;
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("horseRaceLamps")) {
                return;
            }
            if (realmList != null && !realmList.isManaged()) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<HorseRaceLampsBean> realmList2 = new RealmList<>();
                Iterator<HorseRaceLampsBean> it = realmList.iterator();
                while (it.hasNext()) {
                    HorseRaceLampsBean next = it.next();
                    if (next == null || RealmObject.isManaged(next)) {
                        realmList2.add(next);
                    } else {
                        realmList2.add((HorseRaceLampsBean) realm.copyToRealm(next, new ImportFlag[0]));
                    }
                }
                realmList = realmList2;
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList modelList = this.proxyState.getRow$realm().getModelList(this.columnInfo.horseRaceLampsColKey);
        if (realmList == null || ((long) realmList.size()) != modelList.size()) {
            modelList.removeAll();
            if (realmList != null) {
                int size = realmList.size();
                while (i < size) {
                    HorseRaceLampsBean horseRaceLampsBean = realmList.get(i);
                    this.proxyState.checkValidObject(horseRaceLampsBean);
                    modelList.addRow(((RealmObjectProxy) horseRaceLampsBean).realmGet$proxyState().getRow$realm().getObjectKey());
                    i++;
                }
                return;
            }
            return;
        }
        int size2 = realmList.size();
        while (i < size2) {
            HorseRaceLampsBean horseRaceLampsBean2 = realmList.get(i);
            this.proxyState.checkValidObject(horseRaceLampsBean2);
            modelList.setRow((long) i, ((RealmObjectProxy) horseRaceLampsBean2).realmGet$proxyState().getRow$realm().getObjectKey());
            i++;
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 9, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("kind", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("begin", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("end", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("createtime", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("id", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("resources", RealmFieldType.LIST, com_ciot_realm_db_ad_ResourcesBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedLinkProperty("horseRaceLamps", RealmFieldType.LIST, com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static AdvertisementsBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new AdvertisementsBeanColumnInfo(osSchemaInfo);
    }

    public static AdvertisementsBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        ArrayList arrayList = new ArrayList(2);
        if (jSONObject.has("resources")) {
            arrayList.add("resources");
        }
        if (jSONObject.has("horseRaceLamps")) {
            arrayList.add("horseRaceLamps");
        }
        AdvertisementsBean advertisementsBean = (AdvertisementsBean) realm.createObjectInternal(AdvertisementsBean.class, true, arrayList);
        com_ciot_realm_db_ad_AdvertisementsBeanRealmProxyInterface com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface = advertisementsBean;
        if (jSONObject.has(ServiceProvider.NAME)) {
            if (jSONObject.isNull(ServiceProvider.NAME)) {
                com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$name((String) null);
            } else {
                com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$name(jSONObject.getString(ServiceProvider.NAME));
            }
        }
        if (jSONObject.has("kind")) {
            if (jSONObject.isNull("kind")) {
                com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$kind((String) null);
            } else {
                com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$kind(jSONObject.getString("kind"));
            }
        }
        if (jSONObject.has("begin")) {
            if (!jSONObject.isNull("begin")) {
                com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$begin(jSONObject.getInt("begin"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'begin' to null.");
            }
        }
        if (jSONObject.has("end")) {
            if (!jSONObject.isNull("end")) {
                com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$end(jSONObject.getInt("end"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'end' to null.");
            }
        }
        if (jSONObject.has("description")) {
            if (jSONObject.isNull("description")) {
                com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$description((String) null);
            } else {
                com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$description(jSONObject.getString("description"));
            }
        }
        if (jSONObject.has("createtime")) {
            if (!jSONObject.isNull("createtime")) {
                com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$createtime(jSONObject.getInt("createtime"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'createtime' to null.");
            }
        }
        if (jSONObject.has("id")) {
            if (jSONObject.isNull("id")) {
                com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$id((String) null);
            } else {
                com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$id(jSONObject.getString("id"));
            }
        }
        if (jSONObject.has("resources")) {
            if (jSONObject.isNull("resources")) {
                com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$resources((RealmList<ResourcesBean>) null);
            } else {
                com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$resources().clear();
                JSONArray jSONArray = jSONObject.getJSONArray("resources");
                for (int i = 0; i < jSONArray.length(); i++) {
                    com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$resources().add(com_ciot_realm_db_ad_ResourcesBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONArray.getJSONObject(i), z));
                }
            }
        }
        if (jSONObject.has("horseRaceLamps")) {
            if (jSONObject.isNull("horseRaceLamps")) {
                com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$horseRaceLamps((RealmList<HorseRaceLampsBean>) null);
            } else {
                com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$horseRaceLamps().clear();
                JSONArray jSONArray2 = jSONObject.getJSONArray("horseRaceLamps");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$horseRaceLamps().add(com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONArray2.getJSONObject(i2), z));
                }
            }
        }
        return advertisementsBean;
    }

    public static AdvertisementsBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        AdvertisementsBean advertisementsBean = new AdvertisementsBean();
        com_ciot_realm_db_ad_AdvertisementsBeanRealmProxyInterface com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface = advertisementsBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(ServiceProvider.NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$name(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$name((String) null);
                }
            } else if (nextName.equals("kind")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$kind(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$kind((String) null);
                }
            } else if (nextName.equals("begin")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$begin(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'begin' to null.");
                }
            } else if (nextName.equals("end")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$end(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'end' to null.");
                }
            } else if (nextName.equals("description")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$description(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$description((String) null);
                }
            } else if (nextName.equals("createtime")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$createtime(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'createtime' to null.");
                }
            } else if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$id((String) null);
                }
            } else if (nextName.equals("resources")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$resources((RealmList<ResourcesBean>) null);
                } else {
                    com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$resources(new RealmList());
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$resources().add(com_ciot_realm_db_ad_ResourcesBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                    }
                    jsonReader.endArray();
                }
            } else if (!nextName.equals("horseRaceLamps")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.skipValue();
                com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$horseRaceLamps((RealmList<HorseRaceLampsBean>) null);
            } else {
                com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$horseRaceLamps(new RealmList());
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$horseRaceLamps().add(com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
                jsonReader.endArray();
            }
        }
        jsonReader.endObject();
        return (AdvertisementsBean) realm.copyToRealm(advertisementsBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) AdvertisementsBean.class), false, Collections.emptyList());
        com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy com_ciot_realm_db_ad_advertisementsbeanrealmproxy = new com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_ad_advertisementsbeanrealmproxy;
    }

    public static AdvertisementsBean copyOrUpdate(Realm realm, AdvertisementsBeanColumnInfo advertisementsBeanColumnInfo, AdvertisementsBean advertisementsBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((advertisementsBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(advertisementsBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) advertisementsBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return advertisementsBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(advertisementsBean);
        if (realmObjectProxy2 != null) {
            return (AdvertisementsBean) realmObjectProxy2;
        }
        return copy(realm, advertisementsBeanColumnInfo, advertisementsBean, z, map, set);
    }

    public static AdvertisementsBean copy(Realm realm, AdvertisementsBeanColumnInfo advertisementsBeanColumnInfo, AdvertisementsBean advertisementsBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        Realm realm2 = realm;
        AdvertisementsBeanColumnInfo advertisementsBeanColumnInfo2 = advertisementsBeanColumnInfo;
        AdvertisementsBean advertisementsBean2 = advertisementsBean;
        Map<RealmModel, RealmObjectProxy> map2 = map;
        RealmObjectProxy realmObjectProxy = map2.get(advertisementsBean2);
        if (realmObjectProxy != null) {
            return (AdvertisementsBean) realmObjectProxy;
        }
        com_ciot_realm_db_ad_AdvertisementsBeanRealmProxyInterface com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface = advertisementsBean2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(AdvertisementsBean.class), set);
        osObjectBuilder.addString(advertisementsBeanColumnInfo2.nameColKey, com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$name());
        osObjectBuilder.addString(advertisementsBeanColumnInfo2.kindColKey, com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$kind());
        osObjectBuilder.addInteger(advertisementsBeanColumnInfo2.beginColKey, Integer.valueOf(com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$begin()));
        osObjectBuilder.addInteger(advertisementsBeanColumnInfo2.endColKey, Integer.valueOf(com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$end()));
        osObjectBuilder.addString(advertisementsBeanColumnInfo2.descriptionColKey, com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$description());
        osObjectBuilder.addInteger(advertisementsBeanColumnInfo2.createtimeColKey, Integer.valueOf(com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$createtime()));
        osObjectBuilder.addString(advertisementsBeanColumnInfo2.idColKey, com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$id());
        com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map2.put(advertisementsBean2, newProxyInstance);
        RealmList<ResourcesBean> realmGet$resources = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$resources();
        if (realmGet$resources != null) {
            RealmList<ResourcesBean> realmGet$resources2 = newProxyInstance.realmGet$resources();
            realmGet$resources2.clear();
            for (int i = 0; i < realmGet$resources.size(); i++) {
                ResourcesBean resourcesBean = realmGet$resources.get(i);
                ResourcesBean resourcesBean2 = (ResourcesBean) map2.get(resourcesBean);
                if (resourcesBean2 != null) {
                    realmGet$resources2.add(resourcesBean2);
                } else {
                    realmGet$resources2.add(com_ciot_realm_db_ad_ResourcesBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_ResourcesBeanRealmProxy.ResourcesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ResourcesBean.class), resourcesBean, z, map, set));
                }
            }
        }
        RealmList<HorseRaceLampsBean> realmGet$horseRaceLamps = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$horseRaceLamps();
        if (realmGet$horseRaceLamps != null) {
            RealmList<HorseRaceLampsBean> realmGet$horseRaceLamps2 = newProxyInstance.realmGet$horseRaceLamps();
            realmGet$horseRaceLamps2.clear();
            for (int i2 = 0; i2 < realmGet$horseRaceLamps.size(); i2++) {
                HorseRaceLampsBean horseRaceLampsBean = realmGet$horseRaceLamps.get(i2);
                HorseRaceLampsBean horseRaceLampsBean2 = (HorseRaceLampsBean) map2.get(horseRaceLampsBean);
                if (horseRaceLampsBean2 != null) {
                    realmGet$horseRaceLamps2.add(horseRaceLampsBean2);
                } else {
                    realmGet$horseRaceLamps2.add(com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.HorseRaceLampsBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) HorseRaceLampsBean.class), horseRaceLampsBean, z, map, set));
                }
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, AdvertisementsBean advertisementsBean, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Realm realm2 = realm;
        AdvertisementsBean advertisementsBean2 = advertisementsBean;
        Map<RealmModel, Long> map2 = map;
        if ((advertisementsBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(advertisementsBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) advertisementsBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(AdvertisementsBean.class);
        long nativePtr = table.getNativePtr();
        AdvertisementsBeanColumnInfo advertisementsBeanColumnInfo = (AdvertisementsBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AdvertisementsBean.class);
        long createRow = OsObject.createRow(table);
        map2.put(advertisementsBean2, Long.valueOf(createRow));
        com_ciot_realm_db_ad_AdvertisementsBeanRealmProxyInterface com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface = advertisementsBean2;
        String realmGet$name = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            j = createRow;
            Table.nativeSetString(nativePtr, advertisementsBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
        } else {
            j = createRow;
        }
        String realmGet$kind = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$kind();
        if (realmGet$kind != null) {
            Table.nativeSetString(nativePtr, advertisementsBeanColumnInfo.kindColKey, j, realmGet$kind, false);
        }
        long j3 = j;
        Table.nativeSetLong(nativePtr, advertisementsBeanColumnInfo.beginColKey, j3, (long) com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$begin(), false);
        Table.nativeSetLong(nativePtr, advertisementsBeanColumnInfo.endColKey, j3, (long) com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$end(), false);
        String realmGet$description = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, advertisementsBeanColumnInfo.descriptionColKey, j, realmGet$description, false);
        }
        Table.nativeSetLong(nativePtr, advertisementsBeanColumnInfo.createtimeColKey, j, (long) com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$createtime(), false);
        String realmGet$id = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(nativePtr, advertisementsBeanColumnInfo.idColKey, j, realmGet$id, false);
        }
        RealmList<ResourcesBean> realmGet$resources = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$resources();
        if (realmGet$resources != null) {
            j2 = j;
            OsList osList = new OsList(table.getUncheckedRow(j2), advertisementsBeanColumnInfo.resourcesColKey);
            Iterator<ResourcesBean> it = realmGet$resources.iterator();
            while (it.hasNext()) {
                ResourcesBean next = it.next();
                Long l = map2.get(next);
                if (l == null) {
                    l = Long.valueOf(com_ciot_realm_db_ad_ResourcesBeanRealmProxy.insert(realm2, next, map2));
                }
                osList.addRow(l.longValue());
            }
        } else {
            j2 = j;
        }
        RealmList<HorseRaceLampsBean> realmGet$horseRaceLamps = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$horseRaceLamps();
        if (realmGet$horseRaceLamps != null) {
            OsList osList2 = new OsList(table.getUncheckedRow(j2), advertisementsBeanColumnInfo.horseRaceLampsColKey);
            Iterator<HorseRaceLampsBean> it2 = realmGet$horseRaceLamps.iterator();
            while (it2.hasNext()) {
                HorseRaceLampsBean next2 = it2.next();
                Long l2 = map2.get(next2);
                if (l2 == null) {
                    l2 = Long.valueOf(com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.insert(realm2, next2, map2));
                }
                osList2.addRow(l2.longValue());
            }
        }
        return j2;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(AdvertisementsBean.class);
        long nativePtr = table.getNativePtr();
        AdvertisementsBeanColumnInfo advertisementsBeanColumnInfo = (AdvertisementsBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AdvertisementsBean.class);
        while (it.hasNext()) {
            AdvertisementsBean advertisementsBean = (AdvertisementsBean) it.next();
            if (!map2.containsKey(advertisementsBean)) {
                if ((advertisementsBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(advertisementsBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) advertisementsBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(advertisementsBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(advertisementsBean, Long.valueOf(createRow));
                com_ciot_realm_db_ad_AdvertisementsBeanRealmProxyInterface com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface = advertisementsBean;
                String realmGet$name = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    j = createRow;
                    Table.nativeSetString(nativePtr, advertisementsBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
                } else {
                    j = createRow;
                }
                String realmGet$kind = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$kind();
                if (realmGet$kind != null) {
                    Table.nativeSetString(nativePtr, advertisementsBeanColumnInfo.kindColKey, j, realmGet$kind, false);
                }
                long j3 = j;
                Table.nativeSetLong(nativePtr, advertisementsBeanColumnInfo.beginColKey, j3, (long) com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$begin(), false);
                Table.nativeSetLong(nativePtr, advertisementsBeanColumnInfo.endColKey, j3, (long) com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$end(), false);
                String realmGet$description = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, advertisementsBeanColumnInfo.descriptionColKey, j, realmGet$description, false);
                }
                Table.nativeSetLong(nativePtr, advertisementsBeanColumnInfo.createtimeColKey, j, (long) com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$createtime(), false);
                String realmGet$id = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(nativePtr, advertisementsBeanColumnInfo.idColKey, j, realmGet$id, false);
                }
                RealmList<ResourcesBean> realmGet$resources = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$resources();
                if (realmGet$resources != null) {
                    j2 = j;
                    OsList osList = new OsList(table.getUncheckedRow(j2), advertisementsBeanColumnInfo.resourcesColKey);
                    Iterator<ResourcesBean> it2 = realmGet$resources.iterator();
                    while (it2.hasNext()) {
                        ResourcesBean next = it2.next();
                        Long l = map2.get(next);
                        if (l == null) {
                            l = Long.valueOf(com_ciot_realm_db_ad_ResourcesBeanRealmProxy.insert(realm2, next, map2));
                        }
                        osList.addRow(l.longValue());
                    }
                } else {
                    j2 = j;
                }
                RealmList<HorseRaceLampsBean> realmGet$horseRaceLamps = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$horseRaceLamps();
                if (realmGet$horseRaceLamps != null) {
                    OsList osList2 = new OsList(table.getUncheckedRow(j2), advertisementsBeanColumnInfo.horseRaceLampsColKey);
                    Iterator<HorseRaceLampsBean> it3 = realmGet$horseRaceLamps.iterator();
                    while (it3.hasNext()) {
                        HorseRaceLampsBean next2 = it3.next();
                        Long l2 = map2.get(next2);
                        if (l2 == null) {
                            l2 = Long.valueOf(com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.insert(realm2, next2, map2));
                        }
                        osList2.addRow(l2.longValue());
                    }
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, AdvertisementsBean advertisementsBean, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        AdvertisementsBean advertisementsBean2 = advertisementsBean;
        Map<RealmModel, Long> map2 = map;
        if ((advertisementsBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(advertisementsBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) advertisementsBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(AdvertisementsBean.class);
        long nativePtr = table.getNativePtr();
        AdvertisementsBeanColumnInfo advertisementsBeanColumnInfo = (AdvertisementsBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AdvertisementsBean.class);
        long createRow = OsObject.createRow(table);
        map2.put(advertisementsBean2, Long.valueOf(createRow));
        com_ciot_realm_db_ad_AdvertisementsBeanRealmProxyInterface com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface = advertisementsBean2;
        String realmGet$name = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            j = createRow;
            Table.nativeSetString(nativePtr, advertisementsBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
        } else {
            j = createRow;
            Table.nativeSetNull(nativePtr, advertisementsBeanColumnInfo.nameColKey, j, false);
        }
        String realmGet$kind = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$kind();
        if (realmGet$kind != null) {
            Table.nativeSetString(nativePtr, advertisementsBeanColumnInfo.kindColKey, j, realmGet$kind, false);
        } else {
            Table.nativeSetNull(nativePtr, advertisementsBeanColumnInfo.kindColKey, j, false);
        }
        long j2 = j;
        Table.nativeSetLong(nativePtr, advertisementsBeanColumnInfo.beginColKey, j2, (long) com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$begin(), false);
        Table.nativeSetLong(nativePtr, advertisementsBeanColumnInfo.endColKey, j2, (long) com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$end(), false);
        String realmGet$description = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, advertisementsBeanColumnInfo.descriptionColKey, j, realmGet$description, false);
        } else {
            Table.nativeSetNull(nativePtr, advertisementsBeanColumnInfo.descriptionColKey, j, false);
        }
        Table.nativeSetLong(nativePtr, advertisementsBeanColumnInfo.createtimeColKey, j, (long) com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$createtime(), false);
        String realmGet$id = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(nativePtr, advertisementsBeanColumnInfo.idColKey, j, realmGet$id, false);
        } else {
            Table.nativeSetNull(nativePtr, advertisementsBeanColumnInfo.idColKey, j, false);
        }
        long j3 = j;
        OsList osList = new OsList(table.getUncheckedRow(j3), advertisementsBeanColumnInfo.resourcesColKey);
        RealmList<ResourcesBean> realmGet$resources = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$resources();
        if (realmGet$resources == null || ((long) realmGet$resources.size()) != osList.size()) {
            osList.removeAll();
            if (realmGet$resources != null) {
                Iterator<ResourcesBean> it = realmGet$resources.iterator();
                while (it.hasNext()) {
                    ResourcesBean next = it.next();
                    Long l = map2.get(next);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_ad_ResourcesBeanRealmProxy.insertOrUpdate(realm2, next, map2));
                    }
                    osList.addRow(l.longValue());
                }
            }
        } else {
            int size = realmGet$resources.size();
            for (int i = 0; i < size; i++) {
                ResourcesBean resourcesBean = realmGet$resources.get(i);
                Long l2 = map2.get(resourcesBean);
                if (l2 == null) {
                    l2 = Long.valueOf(com_ciot_realm_db_ad_ResourcesBeanRealmProxy.insertOrUpdate(realm2, resourcesBean, map2));
                }
                osList.setRow((long) i, l2.longValue());
            }
        }
        OsList osList2 = new OsList(table.getUncheckedRow(j3), advertisementsBeanColumnInfo.horseRaceLampsColKey);
        RealmList<HorseRaceLampsBean> realmGet$horseRaceLamps = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$horseRaceLamps();
        if (realmGet$horseRaceLamps == null || ((long) realmGet$horseRaceLamps.size()) != osList2.size()) {
            osList2.removeAll();
            if (realmGet$horseRaceLamps != null) {
                Iterator<HorseRaceLampsBean> it2 = realmGet$horseRaceLamps.iterator();
                while (it2.hasNext()) {
                    HorseRaceLampsBean next2 = it2.next();
                    Long l3 = map2.get(next2);
                    if (l3 == null) {
                        l3 = Long.valueOf(com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.insertOrUpdate(realm2, next2, map2));
                    }
                    osList2.addRow(l3.longValue());
                }
            }
        } else {
            int size2 = realmGet$horseRaceLamps.size();
            for (int i2 = 0; i2 < size2; i2++) {
                HorseRaceLampsBean horseRaceLampsBean = realmGet$horseRaceLamps.get(i2);
                Long l4 = map2.get(horseRaceLampsBean);
                if (l4 == null) {
                    l4 = Long.valueOf(com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.insertOrUpdate(realm2, horseRaceLampsBean, map2));
                }
                osList2.setRow((long) i2, l4.longValue());
            }
        }
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(AdvertisementsBean.class);
        long nativePtr = table.getNativePtr();
        AdvertisementsBeanColumnInfo advertisementsBeanColumnInfo = (AdvertisementsBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) AdvertisementsBean.class);
        while (it.hasNext()) {
            AdvertisementsBean advertisementsBean = (AdvertisementsBean) it.next();
            if (!map2.containsKey(advertisementsBean)) {
                if ((advertisementsBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(advertisementsBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) advertisementsBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(advertisementsBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(advertisementsBean, Long.valueOf(createRow));
                com_ciot_realm_db_ad_AdvertisementsBeanRealmProxyInterface com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface = advertisementsBean;
                String realmGet$name = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    j = createRow;
                    Table.nativeSetString(nativePtr, advertisementsBeanColumnInfo.nameColKey, createRow, realmGet$name, false);
                } else {
                    j = createRow;
                    Table.nativeSetNull(nativePtr, advertisementsBeanColumnInfo.nameColKey, j, false);
                }
                String realmGet$kind = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$kind();
                if (realmGet$kind != null) {
                    Table.nativeSetString(nativePtr, advertisementsBeanColumnInfo.kindColKey, j, realmGet$kind, false);
                } else {
                    Table.nativeSetNull(nativePtr, advertisementsBeanColumnInfo.kindColKey, j, false);
                }
                long j2 = j;
                Table.nativeSetLong(nativePtr, advertisementsBeanColumnInfo.beginColKey, j2, (long) com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$begin(), false);
                Table.nativeSetLong(nativePtr, advertisementsBeanColumnInfo.endColKey, j2, (long) com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$end(), false);
                String realmGet$description = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, advertisementsBeanColumnInfo.descriptionColKey, j, realmGet$description, false);
                } else {
                    Table.nativeSetNull(nativePtr, advertisementsBeanColumnInfo.descriptionColKey, j, false);
                }
                Table.nativeSetLong(nativePtr, advertisementsBeanColumnInfo.createtimeColKey, j, (long) com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$createtime(), false);
                String realmGet$id = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$id();
                if (realmGet$id != null) {
                    Table.nativeSetString(nativePtr, advertisementsBeanColumnInfo.idColKey, j, realmGet$id, false);
                } else {
                    Table.nativeSetNull(nativePtr, advertisementsBeanColumnInfo.idColKey, j, false);
                }
                long j3 = j;
                OsList osList = new OsList(table.getUncheckedRow(j3), advertisementsBeanColumnInfo.resourcesColKey);
                RealmList<ResourcesBean> realmGet$resources = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$resources();
                if (realmGet$resources == null || ((long) realmGet$resources.size()) != osList.size()) {
                    osList.removeAll();
                    if (realmGet$resources != null) {
                        Iterator<ResourcesBean> it2 = realmGet$resources.iterator();
                        while (it2.hasNext()) {
                            ResourcesBean next = it2.next();
                            Long l = map2.get(next);
                            if (l == null) {
                                l = Long.valueOf(com_ciot_realm_db_ad_ResourcesBeanRealmProxy.insertOrUpdate(realm2, next, map2));
                            }
                            osList.addRow(l.longValue());
                        }
                    }
                } else {
                    int i = 0;
                    for (int size = realmGet$resources.size(); i < size; size = size) {
                        ResourcesBean resourcesBean = realmGet$resources.get(i);
                        Long l2 = map2.get(resourcesBean);
                        if (l2 == null) {
                            l2 = Long.valueOf(com_ciot_realm_db_ad_ResourcesBeanRealmProxy.insertOrUpdate(realm2, resourcesBean, map2));
                        }
                        osList.setRow((long) i, l2.longValue());
                        i++;
                    }
                }
                OsList osList2 = new OsList(table.getUncheckedRow(j3), advertisementsBeanColumnInfo.horseRaceLampsColKey);
                RealmList<HorseRaceLampsBean> realmGet$horseRaceLamps = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmGet$horseRaceLamps();
                if (realmGet$horseRaceLamps == null || ((long) realmGet$horseRaceLamps.size()) != osList2.size()) {
                    osList2.removeAll();
                    if (realmGet$horseRaceLamps != null) {
                        Iterator<HorseRaceLampsBean> it3 = realmGet$horseRaceLamps.iterator();
                        while (it3.hasNext()) {
                            HorseRaceLampsBean next2 = it3.next();
                            Long l3 = map2.get(next2);
                            if (l3 == null) {
                                l3 = Long.valueOf(com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.insertOrUpdate(realm2, next2, map2));
                            }
                            osList2.addRow(l3.longValue());
                        }
                    }
                } else {
                    int i2 = 0;
                    for (int size2 = realmGet$horseRaceLamps.size(); i2 < size2; size2 = size2) {
                        HorseRaceLampsBean horseRaceLampsBean = realmGet$horseRaceLamps.get(i2);
                        Long l4 = map2.get(horseRaceLampsBean);
                        if (l4 == null) {
                            l4 = Long.valueOf(com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.insertOrUpdate(realm2, horseRaceLampsBean, map2));
                        }
                        osList2.setRow((long) i2, l4.longValue());
                        i2++;
                    }
                }
            }
        }
    }

    public static AdvertisementsBean createDetachedCopy(AdvertisementsBean advertisementsBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        AdvertisementsBean advertisementsBean2;
        if (i > i2 || advertisementsBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(advertisementsBean);
        if (cacheData == null) {
            advertisementsBean2 = new AdvertisementsBean();
            map.put(advertisementsBean, new RealmObjectProxy.CacheData(i, advertisementsBean2));
        } else if (i >= cacheData.minDepth) {
            return (AdvertisementsBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            advertisementsBean2 = (AdvertisementsBean) cacheData.object;
        }
        com_ciot_realm_db_ad_AdvertisementsBeanRealmProxyInterface com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface = advertisementsBean2;
        com_ciot_realm_db_ad_AdvertisementsBeanRealmProxyInterface com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface2 = advertisementsBean;
        com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$name(com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface2.realmGet$name());
        com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$kind(com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface2.realmGet$kind());
        com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$begin(com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface2.realmGet$begin());
        com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$end(com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface2.realmGet$end());
        com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$description(com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface2.realmGet$description());
        com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$createtime(com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface2.realmGet$createtime());
        com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$id(com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface2.realmGet$id());
        if (i == i2) {
            com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$resources((RealmList<ResourcesBean>) null);
        } else {
            RealmList<ResourcesBean> realmGet$resources = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface2.realmGet$resources();
            RealmList realmList = new RealmList();
            com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$resources(realmList);
            int i3 = i + 1;
            int size = realmGet$resources.size();
            for (int i4 = 0; i4 < size; i4++) {
                realmList.add(com_ciot_realm_db_ad_ResourcesBeanRealmProxy.createDetachedCopy(realmGet$resources.get(i4), i3, i2, map));
            }
        }
        if (i == i2) {
            com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$horseRaceLamps((RealmList<HorseRaceLampsBean>) null);
        } else {
            RealmList<HorseRaceLampsBean> realmGet$horseRaceLamps = com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface2.realmGet$horseRaceLamps();
            RealmList realmList2 = new RealmList();
            com_ciot_realm_db_ad_advertisementsbeanrealmproxyinterface.realmSet$horseRaceLamps(realmList2);
            int i5 = i + 1;
            int size2 = realmGet$horseRaceLamps.size();
            for (int i6 = 0; i6 < size2; i6++) {
                realmList2.add(com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxy.createDetachedCopy(realmGet$horseRaceLamps.get(i6), i5, i2, map));
            }
        }
        return advertisementsBean2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("AdvertisementsBean = proxy[");
        sb.append("{name:");
        String realmGet$name = realmGet$name();
        String str = Configurator.NULL;
        sb.append(realmGet$name != null ? realmGet$name() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{kind:");
        sb.append(realmGet$kind() != null ? realmGet$kind() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{begin:");
        sb.append(realmGet$begin());
        sb.append("}");
        sb.append(",");
        sb.append("{end:");
        sb.append(realmGet$end());
        sb.append("}");
        sb.append(",");
        sb.append("{description:");
        sb.append(realmGet$description() != null ? realmGet$description() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{createtime:");
        sb.append(realmGet$createtime());
        sb.append("}");
        sb.append(",");
        sb.append("{id:");
        if (realmGet$id() != null) {
            str = realmGet$id();
        }
        sb.append(str);
        sb.append("}");
        sb.append(",");
        sb.append("{resources:");
        sb.append("RealmList<ResourcesBean>[");
        sb.append(realmGet$resources().size());
        sb.append("]");
        sb.append("}");
        sb.append(",");
        sb.append("{horseRaceLamps:");
        sb.append("RealmList<HorseRaceLampsBean>[");
        sb.append(realmGet$horseRaceLamps().size());
        sb.append("]");
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
        com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy com_ciot_realm_db_ad_advertisementsbeanrealmproxy = (com_ciot_realm_db_ad_AdvertisementsBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_ad_advertisementsbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_ad_advertisementsbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_ad_advertisementsbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
