package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.HotelActivitesBean;
import com.ciot.realm.db.TimeBean;
import com.limpoxe.support.servicemanager.ServiceProvider;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_TimeBeanRealmProxy;
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

public class com_ciot_realm_db_HotelActivitesBeanRealmProxy extends HotelActivitesBean implements RealmObjectProxy, com_ciot_realm_db_HotelActivitesBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private HotelActivitesBeanColumnInfo columnInfo;
    private ProxyState<HotelActivitesBean> proxyState;
    private RealmList<TimeBean> timesRealmList;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "HotelActivitesBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class HotelActivitesBeanColumnInfo extends ColumnInfo {
        long addressColKey;
        long beginDateColKey;
        long descriptionColKey;
        long endDateColKey;
        long hotspotColKey;
        long idColKey;
        long imgUrlColKey;
        long isCheckedColKey;
        long nameColKey;
        long peopleNumberColKey;
        long priceColKey;
        long timesColKey;

        HotelActivitesBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(12);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
            this.imgUrlColKey = addColumnDetails("imgUrl", "imgUrl", objectSchemaInfo);
            this.beginDateColKey = addColumnDetails("beginDate", "beginDate", objectSchemaInfo);
            this.endDateColKey = addColumnDetails("endDate", "endDate", objectSchemaInfo);
            this.addressColKey = addColumnDetails("address", "address", objectSchemaInfo);
            this.peopleNumberColKey = addColumnDetails("peopleNumber", "peopleNumber", objectSchemaInfo);
            this.hotspotColKey = addColumnDetails("hotspot", "hotspot", objectSchemaInfo);
            this.priceColKey = addColumnDetails("price", "price", objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
            this.timesColKey = addColumnDetails("times", "times", objectSchemaInfo);
            this.isCheckedColKey = addColumnDetails("isChecked", "isChecked", objectSchemaInfo);
        }

        HotelActivitesBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new HotelActivitesBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            HotelActivitesBeanColumnInfo hotelActivitesBeanColumnInfo = (HotelActivitesBeanColumnInfo) columnInfo;
            HotelActivitesBeanColumnInfo hotelActivitesBeanColumnInfo2 = (HotelActivitesBeanColumnInfo) columnInfo2;
            hotelActivitesBeanColumnInfo2.idColKey = hotelActivitesBeanColumnInfo.idColKey;
            hotelActivitesBeanColumnInfo2.nameColKey = hotelActivitesBeanColumnInfo.nameColKey;
            hotelActivitesBeanColumnInfo2.imgUrlColKey = hotelActivitesBeanColumnInfo.imgUrlColKey;
            hotelActivitesBeanColumnInfo2.beginDateColKey = hotelActivitesBeanColumnInfo.beginDateColKey;
            hotelActivitesBeanColumnInfo2.endDateColKey = hotelActivitesBeanColumnInfo.endDateColKey;
            hotelActivitesBeanColumnInfo2.addressColKey = hotelActivitesBeanColumnInfo.addressColKey;
            hotelActivitesBeanColumnInfo2.peopleNumberColKey = hotelActivitesBeanColumnInfo.peopleNumberColKey;
            hotelActivitesBeanColumnInfo2.hotspotColKey = hotelActivitesBeanColumnInfo.hotspotColKey;
            hotelActivitesBeanColumnInfo2.priceColKey = hotelActivitesBeanColumnInfo.priceColKey;
            hotelActivitesBeanColumnInfo2.descriptionColKey = hotelActivitesBeanColumnInfo.descriptionColKey;
            hotelActivitesBeanColumnInfo2.timesColKey = hotelActivitesBeanColumnInfo.timesColKey;
            hotelActivitesBeanColumnInfo2.isCheckedColKey = hotelActivitesBeanColumnInfo.isCheckedColKey;
        }
    }

    com_ciot_realm_db_HotelActivitesBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (HotelActivitesBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<HotelActivitesBean> proxyState2 = new ProxyState<>(this);
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

    public String realmGet$imgUrl() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.imgUrlColKey);
    }

    public void realmSet$imgUrl(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.imgUrlColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.imgUrlColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.imgUrlColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.imgUrlColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public long realmGet$beginDate() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.beginDateColKey);
    }

    public void realmSet$beginDate(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.beginDateColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.beginDateColKey, row$realm.getObjectKey(), j, true);
        }
    }

    public long realmGet$endDate() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.endDateColKey);
    }

    public void realmSet$endDate(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.endDateColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.endDateColKey, row$realm.getObjectKey(), j, true);
        }
    }

    public String realmGet$address() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.addressColKey);
    }

    public void realmSet$address(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.addressColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.addressColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.addressColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.addressColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$peopleNumber() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.peopleNumberColKey);
    }

    public void realmSet$peopleNumber(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.peopleNumberColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.peopleNumberColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.peopleNumberColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.peopleNumberColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public boolean realmGet$hotspot() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.hotspotColKey);
    }

    public void realmSet$hotspot(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.hotspotColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.hotspotColKey, row$realm.getObjectKey(), z, true);
        }
    }

    public String realmGet$price() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.priceColKey);
    }

    public void realmSet$price(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.priceColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.priceColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.priceColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.priceColKey, row$realm.getObjectKey(), str, true);
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

    public RealmList<TimeBean> realmGet$times() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<TimeBean> realmList = this.timesRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<TimeBean> realmList2 = new RealmList<>(TimeBean.class, this.proxyState.getRow$realm().getModelList(this.columnInfo.timesColKey), this.proxyState.getRealm$realm());
        this.timesRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$times(RealmList<TimeBean> realmList) {
        int i = 0;
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("times")) {
                return;
            }
            if (realmList != null && !realmList.isManaged()) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<TimeBean> realmList2 = new RealmList<>();
                Iterator<TimeBean> it = realmList.iterator();
                while (it.hasNext()) {
                    TimeBean next = it.next();
                    if (next == null || RealmObject.isManaged(next)) {
                        realmList2.add(next);
                    } else {
                        realmList2.add((TimeBean) realm.copyToRealm(next, new ImportFlag[0]));
                    }
                }
                realmList = realmList2;
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList modelList = this.proxyState.getRow$realm().getModelList(this.columnInfo.timesColKey);
        if (realmList == null || ((long) realmList.size()) != modelList.size()) {
            modelList.removeAll();
            if (realmList != null) {
                int size = realmList.size();
                while (i < size) {
                    TimeBean timeBean = realmList.get(i);
                    this.proxyState.checkValidObject(timeBean);
                    modelList.addRow(((RealmObjectProxy) timeBean).realmGet$proxyState().getRow$realm().getObjectKey());
                    i++;
                }
                return;
            }
            return;
        }
        int size2 = realmList.size();
        while (i < size2) {
            TimeBean timeBean2 = realmList.get(i);
            this.proxyState.checkValidObject(timeBean2);
            modelList.setRow((long) i, ((RealmObjectProxy) timeBean2).realmGet$proxyState().getRow$realm().getObjectKey());
            i++;
        }
    }

    public boolean realmGet$isChecked() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isCheckedColKey);
    }

    public void realmSet$isChecked(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isCheckedColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.isCheckedColKey, row$realm.getObjectKey(), z, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 12, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("id", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("imgUrl", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("beginDate", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("endDate", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("address", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("peopleNumber", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("hotspot", RealmFieldType.BOOLEAN, false, false, true);
        builder2.addPersistedProperty("price", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("times", RealmFieldType.LIST, com_ciot_realm_db_TimeBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedProperty("isChecked", RealmFieldType.BOOLEAN, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static HotelActivitesBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new HotelActivitesBeanColumnInfo(osSchemaInfo);
    }

    public static HotelActivitesBean createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        ArrayList arrayList = new ArrayList(1);
        if (jSONObject.has("times")) {
            arrayList.add("times");
        }
        HotelActivitesBean hotelActivitesBean = (HotelActivitesBean) realm.createObjectInternal(HotelActivitesBean.class, true, arrayList);
        com_ciot_realm_db_HotelActivitesBeanRealmProxyInterface com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface = hotelActivitesBean;
        if (jSONObject.has("id")) {
            if (jSONObject.isNull("id")) {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$id((String) null);
            } else {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$id(jSONObject.getString("id"));
            }
        }
        if (jSONObject.has(ServiceProvider.NAME)) {
            if (jSONObject.isNull(ServiceProvider.NAME)) {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$name((String) null);
            } else {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$name(jSONObject.getString(ServiceProvider.NAME));
            }
        }
        if (jSONObject.has("imgUrl")) {
            if (jSONObject.isNull("imgUrl")) {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$imgUrl((String) null);
            } else {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$imgUrl(jSONObject.getString("imgUrl"));
            }
        }
        if (jSONObject.has("beginDate")) {
            if (!jSONObject.isNull("beginDate")) {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$beginDate(jSONObject.getLong("beginDate"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'beginDate' to null.");
            }
        }
        if (jSONObject.has("endDate")) {
            if (!jSONObject.isNull("endDate")) {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$endDate(jSONObject.getLong("endDate"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'endDate' to null.");
            }
        }
        if (jSONObject.has("address")) {
            if (jSONObject.isNull("address")) {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$address((String) null);
            } else {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$address(jSONObject.getString("address"));
            }
        }
        if (jSONObject.has("peopleNumber")) {
            if (jSONObject.isNull("peopleNumber")) {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$peopleNumber((String) null);
            } else {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$peopleNumber(jSONObject.getString("peopleNumber"));
            }
        }
        if (jSONObject.has("hotspot")) {
            if (!jSONObject.isNull("hotspot")) {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$hotspot(jSONObject.getBoolean("hotspot"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'hotspot' to null.");
            }
        }
        if (jSONObject.has("price")) {
            if (jSONObject.isNull("price")) {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$price((String) null);
            } else {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$price(jSONObject.getString("price"));
            }
        }
        if (jSONObject.has("description")) {
            if (jSONObject.isNull("description")) {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$description((String) null);
            } else {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$description(jSONObject.getString("description"));
            }
        }
        if (jSONObject.has("times")) {
            if (jSONObject.isNull("times")) {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$times((RealmList<TimeBean>) null);
            } else {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$times().clear();
                JSONArray jSONArray = jSONObject.getJSONArray("times");
                for (int i = 0; i < jSONArray.length(); i++) {
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$times().add(com_ciot_realm_db_TimeBeanRealmProxy.createOrUpdateUsingJsonObject(realm, jSONArray.getJSONObject(i), z));
                }
            }
        }
        if (jSONObject.has("isChecked")) {
            if (!jSONObject.isNull("isChecked")) {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$isChecked(jSONObject.getBoolean("isChecked"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'isChecked' to null.");
            }
        }
        return hotelActivitesBean;
    }

    public static HotelActivitesBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        HotelActivitesBean hotelActivitesBean = new HotelActivitesBean();
        com_ciot_realm_db_HotelActivitesBeanRealmProxyInterface com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface = hotelActivitesBean;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$id((String) null);
                }
            } else if (nextName.equals(ServiceProvider.NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$name(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$name((String) null);
                }
            } else if (nextName.equals("imgUrl")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$imgUrl(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$imgUrl((String) null);
                }
            } else if (nextName.equals("beginDate")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$beginDate(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'beginDate' to null.");
                }
            } else if (nextName.equals("endDate")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$endDate(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'endDate' to null.");
                }
            } else if (nextName.equals("address")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$address(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$address((String) null);
                }
            } else if (nextName.equals("peopleNumber")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$peopleNumber(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$peopleNumber((String) null);
                }
            } else if (nextName.equals("hotspot")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$hotspot(jsonReader.nextBoolean());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'hotspot' to null.");
                }
            } else if (nextName.equals("price")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$price(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$price((String) null);
                }
            } else if (nextName.equals("description")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$description(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$description((String) null);
                }
            } else if (nextName.equals("times")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$times((RealmList<TimeBean>) null);
                } else {
                    com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$times(new RealmList());
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$times().add(com_ciot_realm_db_TimeBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                    }
                    jsonReader.endArray();
                }
            } else if (!nextName.equals("isChecked")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$isChecked(jsonReader.nextBoolean());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'isChecked' to null.");
            }
        }
        jsonReader.endObject();
        return (HotelActivitesBean) realm.copyToRealm(hotelActivitesBean, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_HotelActivitesBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) HotelActivitesBean.class), false, Collections.emptyList());
        com_ciot_realm_db_HotelActivitesBeanRealmProxy com_ciot_realm_db_hotelactivitesbeanrealmproxy = new com_ciot_realm_db_HotelActivitesBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_hotelactivitesbeanrealmproxy;
    }

    public static HotelActivitesBean copyOrUpdate(Realm realm, HotelActivitesBeanColumnInfo hotelActivitesBeanColumnInfo, HotelActivitesBean hotelActivitesBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((hotelActivitesBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(hotelActivitesBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) hotelActivitesBean;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return hotelActivitesBean;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(hotelActivitesBean);
        if (realmObjectProxy2 != null) {
            return (HotelActivitesBean) realmObjectProxy2;
        }
        return copy(realm, hotelActivitesBeanColumnInfo, hotelActivitesBean, z, map, set);
    }

    public static HotelActivitesBean copy(Realm realm, HotelActivitesBeanColumnInfo hotelActivitesBeanColumnInfo, HotelActivitesBean hotelActivitesBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(hotelActivitesBean);
        if (realmObjectProxy != null) {
            return (HotelActivitesBean) realmObjectProxy;
        }
        com_ciot_realm_db_HotelActivitesBeanRealmProxyInterface com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface = hotelActivitesBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(HotelActivitesBean.class), set);
        osObjectBuilder.addString(hotelActivitesBeanColumnInfo.idColKey, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$id());
        osObjectBuilder.addString(hotelActivitesBeanColumnInfo.nameColKey, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$name());
        osObjectBuilder.addString(hotelActivitesBeanColumnInfo.imgUrlColKey, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$imgUrl());
        osObjectBuilder.addInteger(hotelActivitesBeanColumnInfo.beginDateColKey, Long.valueOf(com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$beginDate()));
        osObjectBuilder.addInteger(hotelActivitesBeanColumnInfo.endDateColKey, Long.valueOf(com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$endDate()));
        osObjectBuilder.addString(hotelActivitesBeanColumnInfo.addressColKey, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$address());
        osObjectBuilder.addString(hotelActivitesBeanColumnInfo.peopleNumberColKey, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$peopleNumber());
        osObjectBuilder.addBoolean(hotelActivitesBeanColumnInfo.hotspotColKey, Boolean.valueOf(com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$hotspot()));
        osObjectBuilder.addString(hotelActivitesBeanColumnInfo.priceColKey, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$price());
        osObjectBuilder.addString(hotelActivitesBeanColumnInfo.descriptionColKey, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$description());
        osObjectBuilder.addBoolean(hotelActivitesBeanColumnInfo.isCheckedColKey, Boolean.valueOf(com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$isChecked()));
        com_ciot_realm_db_HotelActivitesBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(hotelActivitesBean, newProxyInstance);
        RealmList<TimeBean> realmGet$times = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$times();
        if (realmGet$times != null) {
            RealmList<TimeBean> realmGet$times2 = newProxyInstance.realmGet$times();
            realmGet$times2.clear();
            for (int i = 0; i < realmGet$times.size(); i++) {
                TimeBean timeBean = realmGet$times.get(i);
                TimeBean timeBean2 = (TimeBean) map.get(timeBean);
                if (timeBean2 != null) {
                    realmGet$times2.add(timeBean2);
                } else {
                    realmGet$times2.add(com_ciot_realm_db_TimeBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_TimeBeanRealmProxy.TimeBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimeBean.class), timeBean, z, map, set));
                }
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, HotelActivitesBean hotelActivitesBean, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Realm realm2 = realm;
        HotelActivitesBean hotelActivitesBean2 = hotelActivitesBean;
        Map<RealmModel, Long> map2 = map;
        if ((hotelActivitesBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(hotelActivitesBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) hotelActivitesBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(HotelActivitesBean.class);
        long nativePtr = table.getNativePtr();
        HotelActivitesBeanColumnInfo hotelActivitesBeanColumnInfo = (HotelActivitesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) HotelActivitesBean.class);
        long createRow = OsObject.createRow(table);
        map2.put(hotelActivitesBean2, Long.valueOf(createRow));
        com_ciot_realm_db_HotelActivitesBeanRealmProxyInterface com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface = hotelActivitesBean2;
        String realmGet$id = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$id();
        if (realmGet$id != null) {
            j = createRow;
            Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.idColKey, createRow, realmGet$id, false);
        } else {
            j = createRow;
        }
        String realmGet$name = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.nameColKey, j, realmGet$name, false);
        }
        String realmGet$imgUrl = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$imgUrl();
        if (realmGet$imgUrl != null) {
            Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.imgUrlColKey, j, realmGet$imgUrl, false);
        }
        long j3 = nativePtr;
        long j4 = j;
        Table.nativeSetLong(j3, hotelActivitesBeanColumnInfo.beginDateColKey, j4, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$beginDate(), false);
        Table.nativeSetLong(j3, hotelActivitesBeanColumnInfo.endDateColKey, j4, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$endDate(), false);
        String realmGet$address = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$address();
        if (realmGet$address != null) {
            Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.addressColKey, j, realmGet$address, false);
        }
        String realmGet$peopleNumber = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$peopleNumber();
        if (realmGet$peopleNumber != null) {
            Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.peopleNumberColKey, j, realmGet$peopleNumber, false);
        }
        Table.nativeSetBoolean(nativePtr, hotelActivitesBeanColumnInfo.hotspotColKey, j, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$hotspot(), false);
        String realmGet$price = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$price();
        if (realmGet$price != null) {
            Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.priceColKey, j, realmGet$price, false);
        }
        String realmGet$description = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.descriptionColKey, j, realmGet$description, false);
        }
        RealmList<TimeBean> realmGet$times = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$times();
        if (realmGet$times != null) {
            j2 = j;
            OsList osList = new OsList(table.getUncheckedRow(j2), hotelActivitesBeanColumnInfo.timesColKey);
            Iterator<TimeBean> it = realmGet$times.iterator();
            while (it.hasNext()) {
                TimeBean next = it.next();
                Long l = map2.get(next);
                if (l == null) {
                    l = Long.valueOf(com_ciot_realm_db_TimeBeanRealmProxy.insert(realm2, next, map2));
                }
                osList.addRow(l.longValue());
            }
        } else {
            j2 = j;
        }
        long j5 = hotelActivitesBeanColumnInfo.isCheckedColKey;
        boolean realmGet$isChecked = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$isChecked();
        long j6 = j2;
        Table.nativeSetBoolean(nativePtr, j5, j2, realmGet$isChecked, false);
        return j6;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(HotelActivitesBean.class);
        long nativePtr = table.getNativePtr();
        HotelActivitesBeanColumnInfo hotelActivitesBeanColumnInfo = (HotelActivitesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) HotelActivitesBean.class);
        while (it.hasNext()) {
            HotelActivitesBean hotelActivitesBean = (HotelActivitesBean) it.next();
            if (!map2.containsKey(hotelActivitesBean)) {
                if ((hotelActivitesBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(hotelActivitesBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) hotelActivitesBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(hotelActivitesBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(hotelActivitesBean, Long.valueOf(createRow));
                com_ciot_realm_db_HotelActivitesBeanRealmProxyInterface com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface = hotelActivitesBean;
                String realmGet$id = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$id();
                if (realmGet$id != null) {
                    j = createRow;
                    Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.idColKey, createRow, realmGet$id, false);
                } else {
                    j = createRow;
                }
                String realmGet$name = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.nameColKey, j, realmGet$name, false);
                }
                String realmGet$imgUrl = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$imgUrl();
                if (realmGet$imgUrl != null) {
                    Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.imgUrlColKey, j, realmGet$imgUrl, false);
                }
                long j3 = nativePtr;
                long j4 = j;
                Table.nativeSetLong(j3, hotelActivitesBeanColumnInfo.beginDateColKey, j4, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$beginDate(), false);
                Table.nativeSetLong(j3, hotelActivitesBeanColumnInfo.endDateColKey, j4, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$endDate(), false);
                String realmGet$address = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$address();
                if (realmGet$address != null) {
                    Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.addressColKey, j, realmGet$address, false);
                }
                String realmGet$peopleNumber = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$peopleNumber();
                if (realmGet$peopleNumber != null) {
                    Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.peopleNumberColKey, j, realmGet$peopleNumber, false);
                }
                Table.nativeSetBoolean(nativePtr, hotelActivitesBeanColumnInfo.hotspotColKey, j, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$hotspot(), false);
                String realmGet$price = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$price();
                if (realmGet$price != null) {
                    Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.priceColKey, j, realmGet$price, false);
                }
                String realmGet$description = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.descriptionColKey, j, realmGet$description, false);
                }
                RealmList<TimeBean> realmGet$times = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$times();
                if (realmGet$times != null) {
                    j2 = j;
                    OsList osList = new OsList(table.getUncheckedRow(j2), hotelActivitesBeanColumnInfo.timesColKey);
                    Iterator<TimeBean> it2 = realmGet$times.iterator();
                    while (it2.hasNext()) {
                        TimeBean next = it2.next();
                        Long l = map2.get(next);
                        if (l == null) {
                            l = Long.valueOf(com_ciot_realm_db_TimeBeanRealmProxy.insert(realm2, next, map2));
                        }
                        osList.addRow(l.longValue());
                    }
                } else {
                    j2 = j;
                }
                Table.nativeSetBoolean(nativePtr, hotelActivitesBeanColumnInfo.isCheckedColKey, j2, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$isChecked(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, HotelActivitesBean hotelActivitesBean, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        HotelActivitesBean hotelActivitesBean2 = hotelActivitesBean;
        Map<RealmModel, Long> map2 = map;
        if ((hotelActivitesBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(hotelActivitesBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) hotelActivitesBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(HotelActivitesBean.class);
        long nativePtr = table.getNativePtr();
        HotelActivitesBeanColumnInfo hotelActivitesBeanColumnInfo = (HotelActivitesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) HotelActivitesBean.class);
        long createRow = OsObject.createRow(table);
        map2.put(hotelActivitesBean2, Long.valueOf(createRow));
        com_ciot_realm_db_HotelActivitesBeanRealmProxyInterface com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface = hotelActivitesBean2;
        String realmGet$id = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$id();
        if (realmGet$id != null) {
            j = createRow;
            Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.idColKey, createRow, realmGet$id, false);
        } else {
            j = createRow;
            Table.nativeSetNull(nativePtr, hotelActivitesBeanColumnInfo.idColKey, j, false);
        }
        String realmGet$name = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.nameColKey, j, realmGet$name, false);
        } else {
            Table.nativeSetNull(nativePtr, hotelActivitesBeanColumnInfo.nameColKey, j, false);
        }
        String realmGet$imgUrl = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$imgUrl();
        if (realmGet$imgUrl != null) {
            Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.imgUrlColKey, j, realmGet$imgUrl, false);
        } else {
            Table.nativeSetNull(nativePtr, hotelActivitesBeanColumnInfo.imgUrlColKey, j, false);
        }
        long j2 = nativePtr;
        long j3 = j;
        Table.nativeSetLong(j2, hotelActivitesBeanColumnInfo.beginDateColKey, j3, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$beginDate(), false);
        Table.nativeSetLong(j2, hotelActivitesBeanColumnInfo.endDateColKey, j3, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$endDate(), false);
        String realmGet$address = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$address();
        if (realmGet$address != null) {
            Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.addressColKey, j, realmGet$address, false);
        } else {
            Table.nativeSetNull(nativePtr, hotelActivitesBeanColumnInfo.addressColKey, j, false);
        }
        String realmGet$peopleNumber = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$peopleNumber();
        if (realmGet$peopleNumber != null) {
            Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.peopleNumberColKey, j, realmGet$peopleNumber, false);
        } else {
            Table.nativeSetNull(nativePtr, hotelActivitesBeanColumnInfo.peopleNumberColKey, j, false);
        }
        Table.nativeSetBoolean(nativePtr, hotelActivitesBeanColumnInfo.hotspotColKey, j, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$hotspot(), false);
        String realmGet$price = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$price();
        if (realmGet$price != null) {
            Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.priceColKey, j, realmGet$price, false);
        } else {
            Table.nativeSetNull(nativePtr, hotelActivitesBeanColumnInfo.priceColKey, j, false);
        }
        String realmGet$description = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.descriptionColKey, j, realmGet$description, false);
        } else {
            Table.nativeSetNull(nativePtr, hotelActivitesBeanColumnInfo.descriptionColKey, j, false);
        }
        long j4 = j;
        OsList osList = new OsList(table.getUncheckedRow(j4), hotelActivitesBeanColumnInfo.timesColKey);
        RealmList<TimeBean> realmGet$times = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$times();
        if (realmGet$times == null || ((long) realmGet$times.size()) != osList.size()) {
            osList.removeAll();
            if (realmGet$times != null) {
                Iterator<TimeBean> it = realmGet$times.iterator();
                while (it.hasNext()) {
                    TimeBean next = it.next();
                    Long l = map2.get(next);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_TimeBeanRealmProxy.insertOrUpdate(realm2, next, map2));
                    }
                    osList.addRow(l.longValue());
                }
            }
        } else {
            int size = realmGet$times.size();
            for (int i = 0; i < size; i++) {
                TimeBean timeBean = realmGet$times.get(i);
                Long l2 = map2.get(timeBean);
                if (l2 == null) {
                    l2 = Long.valueOf(com_ciot_realm_db_TimeBeanRealmProxy.insertOrUpdate(realm2, timeBean, map2));
                }
                osList.setRow((long) i, l2.longValue());
            }
        }
        long j5 = hotelActivitesBeanColumnInfo.isCheckedColKey;
        boolean realmGet$isChecked = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$isChecked();
        long j6 = j4;
        Table.nativeSetBoolean(nativePtr, j5, j4, realmGet$isChecked, false);
        return j6;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(HotelActivitesBean.class);
        long nativePtr = table.getNativePtr();
        HotelActivitesBeanColumnInfo hotelActivitesBeanColumnInfo = (HotelActivitesBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) HotelActivitesBean.class);
        while (it.hasNext()) {
            HotelActivitesBean hotelActivitesBean = (HotelActivitesBean) it.next();
            if (!map2.containsKey(hotelActivitesBean)) {
                if ((hotelActivitesBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(hotelActivitesBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) hotelActivitesBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(hotelActivitesBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(hotelActivitesBean, Long.valueOf(createRow));
                com_ciot_realm_db_HotelActivitesBeanRealmProxyInterface com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface = hotelActivitesBean;
                String realmGet$id = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$id();
                if (realmGet$id != null) {
                    j = createRow;
                    Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.idColKey, createRow, realmGet$id, false);
                } else {
                    j = createRow;
                    Table.nativeSetNull(nativePtr, hotelActivitesBeanColumnInfo.idColKey, j, false);
                }
                String realmGet$name = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.nameColKey, j, realmGet$name, false);
                } else {
                    Table.nativeSetNull(nativePtr, hotelActivitesBeanColumnInfo.nameColKey, j, false);
                }
                String realmGet$imgUrl = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$imgUrl();
                if (realmGet$imgUrl != null) {
                    Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.imgUrlColKey, j, realmGet$imgUrl, false);
                } else {
                    Table.nativeSetNull(nativePtr, hotelActivitesBeanColumnInfo.imgUrlColKey, j, false);
                }
                long j3 = nativePtr;
                long j4 = j;
                Table.nativeSetLong(j3, hotelActivitesBeanColumnInfo.beginDateColKey, j4, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$beginDate(), false);
                Table.nativeSetLong(j3, hotelActivitesBeanColumnInfo.endDateColKey, j4, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$endDate(), false);
                String realmGet$address = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$address();
                if (realmGet$address != null) {
                    Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.addressColKey, j, realmGet$address, false);
                } else {
                    Table.nativeSetNull(nativePtr, hotelActivitesBeanColumnInfo.addressColKey, j, false);
                }
                String realmGet$peopleNumber = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$peopleNumber();
                if (realmGet$peopleNumber != null) {
                    Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.peopleNumberColKey, j, realmGet$peopleNumber, false);
                } else {
                    Table.nativeSetNull(nativePtr, hotelActivitesBeanColumnInfo.peopleNumberColKey, j, false);
                }
                Table.nativeSetBoolean(nativePtr, hotelActivitesBeanColumnInfo.hotspotColKey, j, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$hotspot(), false);
                String realmGet$price = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$price();
                if (realmGet$price != null) {
                    Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.priceColKey, j, realmGet$price, false);
                } else {
                    Table.nativeSetNull(nativePtr, hotelActivitesBeanColumnInfo.priceColKey, j, false);
                }
                String realmGet$description = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, hotelActivitesBeanColumnInfo.descriptionColKey, j, realmGet$description, false);
                } else {
                    Table.nativeSetNull(nativePtr, hotelActivitesBeanColumnInfo.descriptionColKey, j, false);
                }
                long j5 = j;
                OsList osList = new OsList(table.getUncheckedRow(j5), hotelActivitesBeanColumnInfo.timesColKey);
                RealmList<TimeBean> realmGet$times = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$times();
                if (realmGet$times == null || ((long) realmGet$times.size()) != osList.size()) {
                    j2 = j5;
                    osList.removeAll();
                    if (realmGet$times != null) {
                        Iterator<TimeBean> it2 = realmGet$times.iterator();
                        while (it2.hasNext()) {
                            TimeBean next = it2.next();
                            Long l = map2.get(next);
                            if (l == null) {
                                l = Long.valueOf(com_ciot_realm_db_TimeBeanRealmProxy.insertOrUpdate(realm2, next, map2));
                            }
                            osList.addRow(l.longValue());
                        }
                    }
                } else {
                    int size = realmGet$times.size();
                    int i = 0;
                    while (i < size) {
                        TimeBean timeBean = realmGet$times.get(i);
                        Long l2 = map2.get(timeBean);
                        if (l2 == null) {
                            l2 = Long.valueOf(com_ciot_realm_db_TimeBeanRealmProxy.insertOrUpdate(realm2, timeBean, map2));
                        }
                        osList.setRow((long) i, l2.longValue());
                        i++;
                        j5 = j5;
                    }
                    j2 = j5;
                }
                Table.nativeSetBoolean(nativePtr, hotelActivitesBeanColumnInfo.isCheckedColKey, j2, com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmGet$isChecked(), false);
            }
        }
    }

    public static HotelActivitesBean createDetachedCopy(HotelActivitesBean hotelActivitesBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        HotelActivitesBean hotelActivitesBean2;
        if (i > i2 || hotelActivitesBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(hotelActivitesBean);
        if (cacheData == null) {
            hotelActivitesBean2 = new HotelActivitesBean();
            map.put(hotelActivitesBean, new RealmObjectProxy.CacheData(i, hotelActivitesBean2));
        } else if (i >= cacheData.minDepth) {
            return (HotelActivitesBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            hotelActivitesBean2 = (HotelActivitesBean) cacheData.object;
        }
        com_ciot_realm_db_HotelActivitesBeanRealmProxyInterface com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface = hotelActivitesBean2;
        com_ciot_realm_db_HotelActivitesBeanRealmProxyInterface com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface2 = hotelActivitesBean;
        com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$id(com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$name(com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface2.realmGet$name());
        com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$imgUrl(com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface2.realmGet$imgUrl());
        com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$beginDate(com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface2.realmGet$beginDate());
        com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$endDate(com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface2.realmGet$endDate());
        com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$address(com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface2.realmGet$address());
        com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$peopleNumber(com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface2.realmGet$peopleNumber());
        com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$hotspot(com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface2.realmGet$hotspot());
        com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$price(com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface2.realmGet$price());
        com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$description(com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface2.realmGet$description());
        if (i == i2) {
            com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$times((RealmList<TimeBean>) null);
        } else {
            RealmList<TimeBean> realmGet$times = com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface2.realmGet$times();
            RealmList realmList = new RealmList();
            com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$times(realmList);
            int i3 = i + 1;
            int size = realmGet$times.size();
            for (int i4 = 0; i4 < size; i4++) {
                realmList.add(com_ciot_realm_db_TimeBeanRealmProxy.createDetachedCopy(realmGet$times.get(i4), i3, i2, map));
            }
        }
        com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface.realmSet$isChecked(com_ciot_realm_db_hotelactivitesbeanrealmproxyinterface2.realmGet$isChecked());
        return hotelActivitesBean2;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("HotelActivitesBean = proxy[");
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
        sb.append("{imgUrl:");
        sb.append(realmGet$imgUrl() != null ? realmGet$imgUrl() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{beginDate:");
        sb.append(realmGet$beginDate());
        sb.append("}");
        sb.append(",");
        sb.append("{endDate:");
        sb.append(realmGet$endDate());
        sb.append("}");
        sb.append(",");
        sb.append("{address:");
        sb.append(realmGet$address() != null ? realmGet$address() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{peopleNumber:");
        sb.append(realmGet$peopleNumber() != null ? realmGet$peopleNumber() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{hotspot:");
        sb.append(realmGet$hotspot());
        sb.append("}");
        sb.append(",");
        sb.append("{price:");
        sb.append(realmGet$price() != null ? realmGet$price() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{description:");
        if (realmGet$description() != null) {
            str = realmGet$description();
        }
        sb.append(str);
        sb.append("}");
        sb.append(",");
        sb.append("{times:");
        sb.append("RealmList<TimeBean>[");
        sb.append(realmGet$times().size());
        sb.append("]");
        sb.append("}");
        sb.append(",");
        sb.append("{isChecked:");
        sb.append(realmGet$isChecked());
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
        com_ciot_realm_db_HotelActivitesBeanRealmProxy com_ciot_realm_db_hotelactivitesbeanrealmproxy = (com_ciot_realm_db_HotelActivitesBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_hotelactivitesbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_hotelactivitesbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_hotelactivitesbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
