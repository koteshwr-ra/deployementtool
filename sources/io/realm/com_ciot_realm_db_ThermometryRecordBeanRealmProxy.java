package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.Person;
import com.ciot.realm.db.ThermometryRecordBean;
import com.ciot.realm.db.ad.CycleBean;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_PersonRealmProxy;
import io.realm.exceptions.RealmException;
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

public class com_ciot_realm_db_ThermometryRecordBeanRealmProxy extends ThermometryRecordBean implements RealmObjectProxy, com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ThermometryRecordBeanColumnInfo columnInfo;
    private ProxyState<ThermometryRecordBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "ThermometryRecordBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class ThermometryRecordBeanColumnInfo extends ColumnInfo {
        long imageNameColKey;
        long isUploadColKey;
        long mediaIdColKey;
        long personColKey;
        long temperatureColKey;
        long timeColKey;
        long urlColKey;

        ThermometryRecordBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.personColKey = addColumnDetails("person", "person", objectSchemaInfo);
            this.timeColKey = addColumnDetails(CycleBean.TIME_TYPE, CycleBean.TIME_TYPE, objectSchemaInfo);
            this.mediaIdColKey = addColumnDetails("mediaId", "mediaId", objectSchemaInfo);
            this.urlColKey = addColumnDetails(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL, TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL, objectSchemaInfo);
            this.temperatureColKey = addColumnDetails("temperature", "temperature", objectSchemaInfo);
            this.imageNameColKey = addColumnDetails("imageName", "imageName", objectSchemaInfo);
            this.isUploadColKey = addColumnDetails("isUpload", "isUpload", objectSchemaInfo);
        }

        ThermometryRecordBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new ThermometryRecordBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            ThermometryRecordBeanColumnInfo thermometryRecordBeanColumnInfo = (ThermometryRecordBeanColumnInfo) columnInfo;
            ThermometryRecordBeanColumnInfo thermometryRecordBeanColumnInfo2 = (ThermometryRecordBeanColumnInfo) columnInfo2;
            thermometryRecordBeanColumnInfo2.personColKey = thermometryRecordBeanColumnInfo.personColKey;
            thermometryRecordBeanColumnInfo2.timeColKey = thermometryRecordBeanColumnInfo.timeColKey;
            thermometryRecordBeanColumnInfo2.mediaIdColKey = thermometryRecordBeanColumnInfo.mediaIdColKey;
            thermometryRecordBeanColumnInfo2.urlColKey = thermometryRecordBeanColumnInfo.urlColKey;
            thermometryRecordBeanColumnInfo2.temperatureColKey = thermometryRecordBeanColumnInfo.temperatureColKey;
            thermometryRecordBeanColumnInfo2.imageNameColKey = thermometryRecordBeanColumnInfo.imageNameColKey;
            thermometryRecordBeanColumnInfo2.isUploadColKey = thermometryRecordBeanColumnInfo.isUploadColKey;
        }
    }

    com_ciot_realm_db_ThermometryRecordBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ThermometryRecordBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<ThermometryRecordBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public Person realmGet$person() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.personColKey)) {
            return null;
        }
        return (Person) this.proxyState.getRealm$realm().get(Person.class, this.proxyState.getRow$realm().getLink(this.columnInfo.personColKey), false, Collections.emptyList());
    }

    public void realmSet$person(Person person) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (person == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.personColKey);
                return;
            }
            this.proxyState.checkValidObject(person);
            this.proxyState.getRow$realm().setLink(this.columnInfo.personColKey, ((RealmObjectProxy) person).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("person")) {
            if (person != null && !RealmObject.isManaged(person)) {
                person = (Person) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(person, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (person == null) {
                row$realm.nullifyLink(this.columnInfo.personColKey);
                return;
            }
            this.proxyState.checkValidObject(person);
            row$realm.getTable().setLink(this.columnInfo.personColKey, row$realm.getObjectKey(), ((RealmObjectProxy) person).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public long realmGet$time() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.timeColKey);
    }

    public void realmSet$time(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'time' cannot be changed after object was created.");
        }
    }

    public String realmGet$mediaId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.mediaIdColKey);
    }

    public void realmSet$mediaId(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.mediaIdColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.mediaIdColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.mediaIdColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.mediaIdColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$url() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.urlColKey);
    }

    public void realmSet$url(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.urlColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.urlColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.urlColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.urlColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public float realmGet$temperature() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getFloat(this.columnInfo.temperatureColKey);
    }

    public void realmSet$temperature(float f) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setFloat(this.columnInfo.temperatureColKey, f);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setFloat(this.columnInfo.temperatureColKey, row$realm.getObjectKey(), f, true);
        }
    }

    public String realmGet$imageName() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.imageNameColKey);
    }

    public void realmSet$imageName(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.imageNameColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.imageNameColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.imageNameColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.imageNameColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public boolean realmGet$isUpload() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isUploadColKey);
    }

    public void realmSet$isUpload(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isUploadColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.isUploadColKey, row$realm.getObjectKey(), z, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 7, 0);
        builder.addPersistedLinkProperty("person", RealmFieldType.OBJECT, com_ciot_realm_db_PersonRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty(CycleBean.TIME_TYPE, RealmFieldType.INTEGER, true, true, true);
        builder2.addPersistedProperty("mediaId", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("temperature", RealmFieldType.FLOAT, false, false, true);
        builder2.addPersistedProperty("imageName", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("isUpload", RealmFieldType.BOOLEAN, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ThermometryRecordBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new ThermometryRecordBeanColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0135  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.ThermometryRecordBean createOrUpdateUsingJsonObject(io.realm.Realm r15, org.json.JSONObject r16, boolean r17) throws org.json.JSONException {
        /*
            r0 = r15
            r7 = r16
            r8 = r17
            java.util.ArrayList r9 = new java.util.ArrayList
            r10 = 1
            r9.<init>(r10)
            java.lang.String r11 = "time"
            r12 = 0
            if (r8 == 0) goto L_0x0069
            java.lang.Class<com.ciot.realm.db.ThermometryRecordBean> r1 = com.ciot.realm.db.ThermometryRecordBean.class
            io.realm.internal.Table r1 = r15.getTable(r1)
            io.realm.RealmSchema r2 = r15.getSchema()
            java.lang.Class<com.ciot.realm.db.ThermometryRecordBean> r3 = com.ciot.realm.db.ThermometryRecordBean.class
            io.realm.internal.ColumnInfo r2 = r2.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)
            io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxy$ThermometryRecordBeanColumnInfo r2 = (io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxy.ThermometryRecordBeanColumnInfo) r2
            long r2 = r2.timeColKey
            boolean r4 = r7.isNull(r11)
            r5 = -1
            if (r4 != 0) goto L_0x0035
            long r13 = r7.getLong(r11)
            long r2 = r1.findFirstLong(r2, r13)
            goto L_0x0036
        L_0x0035:
            r2 = r5
        L_0x0036:
            int r4 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r4 == 0) goto L_0x0069
            io.realm.BaseRealm$ThreadLocalRealmObjectContext r4 = io.realm.BaseRealm.objectContext
            java.lang.Object r4 = r4.get()
            r13 = r4
            io.realm.BaseRealm$RealmObjectContext r13 = (io.realm.BaseRealm.RealmObjectContext) r13
            io.realm.internal.UncheckedRow r3 = r1.getUncheckedRow(r2)     // Catch:{ all -> 0x0064 }
            io.realm.RealmSchema r1 = r15.getSchema()     // Catch:{ all -> 0x0064 }
            java.lang.Class<com.ciot.realm.db.ThermometryRecordBean> r2 = com.ciot.realm.db.ThermometryRecordBean.class
            io.realm.internal.ColumnInfo r4 = r1.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r2)     // Catch:{ all -> 0x0064 }
            r5 = 0
            java.util.List r6 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0064 }
            r1 = r13
            r2 = r15
            r1.set(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0064 }
            io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxy r1 = new io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxy     // Catch:{ all -> 0x0064 }
            r1.<init>()     // Catch:{ all -> 0x0064 }
            r13.clear()
            goto L_0x006a
        L_0x0064:
            r0 = move-exception
            r13.clear()
            throw r0
        L_0x0069:
            r1 = r12
        L_0x006a:
            java.lang.String r2 = "person"
            if (r1 != 0) goto L_0x00a5
            boolean r1 = r7.has(r2)
            if (r1 == 0) goto L_0x0077
            r9.add(r2)
        L_0x0077:
            boolean r1 = r7.has(r11)
            if (r1 == 0) goto L_0x009d
            boolean r1 = r7.isNull(r11)
            if (r1 == 0) goto L_0x008c
            java.lang.Class<com.ciot.realm.db.ThermometryRecordBean> r1 = com.ciot.realm.db.ThermometryRecordBean.class
            io.realm.RealmModel r1 = r15.createObjectInternal(r1, r12, r10, r9)
            io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxy r1 = (io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxy) r1
            goto L_0x00a5
        L_0x008c:
            java.lang.Class<com.ciot.realm.db.ThermometryRecordBean> r1 = com.ciot.realm.db.ThermometryRecordBean.class
            long r3 = r7.getLong(r11)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            io.realm.RealmModel r1 = r15.createObjectInternal(r1, r3, r10, r9)
            io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxy r1 = (io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxy) r1
            goto L_0x00a5
        L_0x009d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "JSON object doesn't have the primary key field 'time'."
            r0.<init>(r1)
            throw r0
        L_0x00a5:
            r3 = r1
            io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface r3 = (io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface) r3
            boolean r4 = r7.has(r2)
            if (r4 == 0) goto L_0x00c3
            boolean r4 = r7.isNull(r2)
            if (r4 == 0) goto L_0x00b8
            r3.realmSet$person(r12)
            goto L_0x00c3
        L_0x00b8:
            org.json.JSONObject r2 = r7.getJSONObject(r2)
            com.ciot.realm.db.Person r0 = io.realm.com_ciot_realm_db_PersonRealmProxy.createOrUpdateUsingJsonObject(r15, r2, r8)
            r3.realmSet$person(r0)
        L_0x00c3:
            java.lang.String r0 = "mediaId"
            boolean r2 = r7.has(r0)
            if (r2 == 0) goto L_0x00dc
            boolean r2 = r7.isNull(r0)
            if (r2 == 0) goto L_0x00d5
            r3.realmSet$mediaId(r12)
            goto L_0x00dc
        L_0x00d5:
            java.lang.String r0 = r7.getString(r0)
            r3.realmSet$mediaId(r0)
        L_0x00dc:
            java.lang.String r0 = "url"
            boolean r2 = r7.has(r0)
            if (r2 == 0) goto L_0x00f5
            boolean r2 = r7.isNull(r0)
            if (r2 == 0) goto L_0x00ee
            r3.realmSet$url(r12)
            goto L_0x00f5
        L_0x00ee:
            java.lang.String r0 = r7.getString(r0)
            r3.realmSet$url(r0)
        L_0x00f5:
            java.lang.String r0 = "temperature"
            boolean r2 = r7.has(r0)
            if (r2 == 0) goto L_0x0114
            boolean r2 = r7.isNull(r0)
            if (r2 != 0) goto L_0x010c
            double r4 = r7.getDouble(r0)
            float r0 = (float) r4
            r3.realmSet$temperature(r0)
            goto L_0x0114
        L_0x010c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Trying to set non-nullable field 'temperature' to null."
            r0.<init>(r1)
            throw r0
        L_0x0114:
            java.lang.String r0 = "imageName"
            boolean r2 = r7.has(r0)
            if (r2 == 0) goto L_0x012d
            boolean r2 = r7.isNull(r0)
            if (r2 == 0) goto L_0x0126
            r3.realmSet$imageName(r12)
            goto L_0x012d
        L_0x0126:
            java.lang.String r0 = r7.getString(r0)
            r3.realmSet$imageName(r0)
        L_0x012d:
            java.lang.String r0 = "isUpload"
            boolean r2 = r7.has(r0)
            if (r2 == 0) goto L_0x014b
            boolean r2 = r7.isNull(r0)
            if (r2 != 0) goto L_0x0143
            boolean r0 = r7.getBoolean(r0)
            r3.realmSet$isUpload(r0)
            goto L_0x014b
        L_0x0143:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Trying to set non-nullable field 'isUpload' to null."
            r0.<init>(r1)
            throw r0
        L_0x014b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.ThermometryRecordBean");
    }

    public static ThermometryRecordBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        ThermometryRecordBean thermometryRecordBean = new ThermometryRecordBean();
        com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface = thermometryRecordBean;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("person")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$person((Person) null);
                } else {
                    com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$person(com_ciot_realm_db_PersonRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals(CycleBean.TIME_TYPE)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$time(jsonReader.nextLong());
                    z = true;
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'time' to null.");
                }
            } else if (nextName.equals("mediaId")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$mediaId(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$mediaId((String) null);
                }
            } else if (nextName.equals(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$url(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$url((String) null);
                }
            } else if (nextName.equals("temperature")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$temperature((float) jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'temperature' to null.");
                }
            } else if (nextName.equals("imageName")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$imageName(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$imageName((String) null);
                }
            } else if (!nextName.equals("isUpload")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$isUpload(jsonReader.nextBoolean());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'isUpload' to null.");
            }
        }
        jsonReader.endObject();
        if (z) {
            return (ThermometryRecordBean) realm.copyToRealm(thermometryRecordBean, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'time'.");
    }

    private static com_ciot_realm_db_ThermometryRecordBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) ThermometryRecordBean.class), false, Collections.emptyList());
        com_ciot_realm_db_ThermometryRecordBeanRealmProxy com_ciot_realm_db_thermometryrecordbeanrealmproxy = new com_ciot_realm_db_ThermometryRecordBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_thermometryrecordbeanrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.ThermometryRecordBean copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxy.ThermometryRecordBeanColumnInfo r9, com.ciot.realm.db.ThermometryRecordBean r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.ThermometryRecordBean r1 = (com.ciot.realm.db.ThermometryRecordBean) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0092
            java.lang.Class<com.ciot.realm.db.ThermometryRecordBean> r2 = com.ciot.realm.db.ThermometryRecordBean.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.timeColKey
            r5 = r10
            io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface) r5
            long r5 = r5.realmGet$time()
            long r3 = r2.findFirstLong(r3, r5)
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x006f
            r0 = 0
            goto L_0x0093
        L_0x006f:
            io.realm.internal.UncheckedRow r3 = r2.getUncheckedRow(r3)     // Catch:{ all -> 0x008d }
            r5 = 0
            java.util.List r6 = java.util.Collections.emptyList()     // Catch:{ all -> 0x008d }
            r1 = r0
            r2 = r8
            r4 = r9
            r1.set(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x008d }
            io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxy r1 = new io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxy     // Catch:{ all -> 0x008d }
            r1.<init>()     // Catch:{ all -> 0x008d }
            r2 = r1
            io.realm.internal.RealmObjectProxy r2 = (io.realm.internal.RealmObjectProxy) r2     // Catch:{ all -> 0x008d }
            r12.put(r10, r2)     // Catch:{ all -> 0x008d }
            r0.clear()
            goto L_0x0092
        L_0x008d:
            r8 = move-exception
            r0.clear()
            throw r8
        L_0x0092:
            r0 = r11
        L_0x0093:
            r3 = r1
            if (r0 == 0) goto L_0x00a0
            r1 = r8
            r2 = r9
            r4 = r10
            r5 = r12
            r6 = r13
            com.ciot.realm.db.ThermometryRecordBean r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00a4
        L_0x00a0:
            com.ciot.realm.db.ThermometryRecordBean r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00a4:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_ThermometryRecordBeanRealmProxy$ThermometryRecordBeanColumnInfo, com.ciot.realm.db.ThermometryRecordBean, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.ThermometryRecordBean");
    }

    public static ThermometryRecordBean copy(Realm realm, ThermometryRecordBeanColumnInfo thermometryRecordBeanColumnInfo, ThermometryRecordBean thermometryRecordBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(thermometryRecordBean);
        if (realmObjectProxy != null) {
            return (ThermometryRecordBean) realmObjectProxy;
        }
        com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface = thermometryRecordBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(ThermometryRecordBean.class), set);
        osObjectBuilder.addInteger(thermometryRecordBeanColumnInfo.timeColKey, Long.valueOf(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$time()));
        osObjectBuilder.addString(thermometryRecordBeanColumnInfo.mediaIdColKey, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$mediaId());
        osObjectBuilder.addString(thermometryRecordBeanColumnInfo.urlColKey, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$url());
        osObjectBuilder.addFloat(thermometryRecordBeanColumnInfo.temperatureColKey, Float.valueOf(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$temperature()));
        osObjectBuilder.addString(thermometryRecordBeanColumnInfo.imageNameColKey, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$imageName());
        osObjectBuilder.addBoolean(thermometryRecordBeanColumnInfo.isUploadColKey, Boolean.valueOf(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$isUpload()));
        com_ciot_realm_db_ThermometryRecordBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(thermometryRecordBean, newProxyInstance);
        Person realmGet$person = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$person();
        if (realmGet$person == null) {
            newProxyInstance.realmSet$person((Person) null);
        } else {
            Person person = (Person) map.get(realmGet$person);
            if (person != null) {
                newProxyInstance.realmSet$person(person);
            } else {
                newProxyInstance.realmSet$person(com_ciot_realm_db_PersonRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_PersonRealmProxy.PersonColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Person.class), realmGet$person, z, map, set));
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, ThermometryRecordBean thermometryRecordBean, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        ThermometryRecordBean thermometryRecordBean2 = thermometryRecordBean;
        Map<RealmModel, Long> map2 = map;
        if ((thermometryRecordBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(thermometryRecordBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) thermometryRecordBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(ThermometryRecordBean.class);
        long nativePtr = table.getNativePtr();
        ThermometryRecordBeanColumnInfo thermometryRecordBeanColumnInfo = (ThermometryRecordBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ThermometryRecordBean.class);
        long j2 = thermometryRecordBeanColumnInfo.timeColKey;
        com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface = thermometryRecordBean2;
        Long valueOf = Long.valueOf(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$time());
        if (valueOf != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$time());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$time()));
        } else {
            Table.throwDuplicatePrimaryKeyException(valueOf);
        }
        long j3 = j;
        map2.put(thermometryRecordBean2, Long.valueOf(j3));
        Person realmGet$person = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$person();
        if (realmGet$person != null) {
            Long l = map2.get(realmGet$person);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_PersonRealmProxy.insert(realm2, realmGet$person, map2));
            }
            Table.nativeSetLink(nativePtr, thermometryRecordBeanColumnInfo.personColKey, j3, l.longValue(), false);
        }
        String realmGet$mediaId = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$mediaId();
        if (realmGet$mediaId != null) {
            Table.nativeSetString(nativePtr, thermometryRecordBeanColumnInfo.mediaIdColKey, j3, realmGet$mediaId, false);
        }
        String realmGet$url = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$url();
        if (realmGet$url != null) {
            Table.nativeSetString(nativePtr, thermometryRecordBeanColumnInfo.urlColKey, j3, realmGet$url, false);
        }
        Table.nativeSetFloat(nativePtr, thermometryRecordBeanColumnInfo.temperatureColKey, j3, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$temperature(), false);
        String realmGet$imageName = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$imageName();
        if (realmGet$imageName != null) {
            Table.nativeSetString(nativePtr, thermometryRecordBeanColumnInfo.imageNameColKey, j3, realmGet$imageName, false);
        }
        Table.nativeSetBoolean(nativePtr, thermometryRecordBeanColumnInfo.isUploadColKey, j3, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$isUpload(), false);
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(ThermometryRecordBean.class);
        long nativePtr = table.getNativePtr();
        ThermometryRecordBeanColumnInfo thermometryRecordBeanColumnInfo = (ThermometryRecordBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ThermometryRecordBean.class);
        long j2 = thermometryRecordBeanColumnInfo.timeColKey;
        while (it.hasNext()) {
            ThermometryRecordBean thermometryRecordBean = (ThermometryRecordBean) it.next();
            if (!map2.containsKey(thermometryRecordBean)) {
                if ((thermometryRecordBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(thermometryRecordBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) thermometryRecordBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(thermometryRecordBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface = thermometryRecordBean;
                Long valueOf = Long.valueOf(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$time());
                if (valueOf != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$time());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$time()));
                } else {
                    Table.throwDuplicatePrimaryKeyException(valueOf);
                }
                long j3 = j;
                map2.put(thermometryRecordBean, Long.valueOf(j3));
                Person realmGet$person = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$person();
                if (realmGet$person != null) {
                    Long l = map2.get(realmGet$person);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_PersonRealmProxy.insert(realm2, realmGet$person, map2));
                    }
                    table.setLink(thermometryRecordBeanColumnInfo.personColKey, j3, l.longValue(), false);
                }
                String realmGet$mediaId = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$mediaId();
                if (realmGet$mediaId != null) {
                    Table.nativeSetString(nativePtr, thermometryRecordBeanColumnInfo.mediaIdColKey, j3, realmGet$mediaId, false);
                }
                String realmGet$url = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$url();
                if (realmGet$url != null) {
                    Table.nativeSetString(nativePtr, thermometryRecordBeanColumnInfo.urlColKey, j3, realmGet$url, false);
                }
                Table.nativeSetFloat(nativePtr, thermometryRecordBeanColumnInfo.temperatureColKey, j3, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$temperature(), false);
                String realmGet$imageName = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$imageName();
                if (realmGet$imageName != null) {
                    Table.nativeSetString(nativePtr, thermometryRecordBeanColumnInfo.imageNameColKey, j3, realmGet$imageName, false);
                }
                Table.nativeSetBoolean(nativePtr, thermometryRecordBeanColumnInfo.isUploadColKey, j3, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$isUpload(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, ThermometryRecordBean thermometryRecordBean, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        ThermometryRecordBean thermometryRecordBean2 = thermometryRecordBean;
        Map<RealmModel, Long> map2 = map;
        if ((thermometryRecordBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(thermometryRecordBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) thermometryRecordBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(ThermometryRecordBean.class);
        long nativePtr = table.getNativePtr();
        ThermometryRecordBeanColumnInfo thermometryRecordBeanColumnInfo = (ThermometryRecordBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ThermometryRecordBean.class);
        long j2 = thermometryRecordBeanColumnInfo.timeColKey;
        com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface = thermometryRecordBean2;
        if (Long.valueOf(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$time()) != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$time());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$time()));
        }
        long j3 = j;
        map2.put(thermometryRecordBean2, Long.valueOf(j3));
        Person realmGet$person = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$person();
        if (realmGet$person != null) {
            Long l = map2.get(realmGet$person);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_PersonRealmProxy.insertOrUpdate(realm2, realmGet$person, map2));
            }
            Table.nativeSetLink(nativePtr, thermometryRecordBeanColumnInfo.personColKey, j3, l.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, thermometryRecordBeanColumnInfo.personColKey, j3);
        }
        String realmGet$mediaId = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$mediaId();
        if (realmGet$mediaId != null) {
            Table.nativeSetString(nativePtr, thermometryRecordBeanColumnInfo.mediaIdColKey, j3, realmGet$mediaId, false);
        } else {
            Table.nativeSetNull(nativePtr, thermometryRecordBeanColumnInfo.mediaIdColKey, j3, false);
        }
        String realmGet$url = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$url();
        if (realmGet$url != null) {
            Table.nativeSetString(nativePtr, thermometryRecordBeanColumnInfo.urlColKey, j3, realmGet$url, false);
        } else {
            Table.nativeSetNull(nativePtr, thermometryRecordBeanColumnInfo.urlColKey, j3, false);
        }
        Table.nativeSetFloat(nativePtr, thermometryRecordBeanColumnInfo.temperatureColKey, j3, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$temperature(), false);
        String realmGet$imageName = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$imageName();
        if (realmGet$imageName != null) {
            Table.nativeSetString(nativePtr, thermometryRecordBeanColumnInfo.imageNameColKey, j3, realmGet$imageName, false);
        } else {
            Table.nativeSetNull(nativePtr, thermometryRecordBeanColumnInfo.imageNameColKey, j3, false);
        }
        Table.nativeSetBoolean(nativePtr, thermometryRecordBeanColumnInfo.isUploadColKey, j3, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$isUpload(), false);
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(ThermometryRecordBean.class);
        long nativePtr = table.getNativePtr();
        ThermometryRecordBeanColumnInfo thermometryRecordBeanColumnInfo = (ThermometryRecordBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ThermometryRecordBean.class);
        long j3 = thermometryRecordBeanColumnInfo.timeColKey;
        while (it.hasNext()) {
            ThermometryRecordBean thermometryRecordBean = (ThermometryRecordBean) it.next();
            if (!map2.containsKey(thermometryRecordBean)) {
                if ((thermometryRecordBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(thermometryRecordBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) thermometryRecordBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(thermometryRecordBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface = thermometryRecordBean;
                if (Long.valueOf(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$time()) != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j3, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$time());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j3, Long.valueOf(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$time()));
                }
                long j4 = j;
                map2.put(thermometryRecordBean, Long.valueOf(j4));
                Person realmGet$person = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$person();
                if (realmGet$person != null) {
                    Long l = map2.get(realmGet$person);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_PersonRealmProxy.insertOrUpdate(realm2, realmGet$person, map2));
                    }
                    j2 = j3;
                    Table.nativeSetLink(nativePtr, thermometryRecordBeanColumnInfo.personColKey, j4, l.longValue(), false);
                } else {
                    j2 = j3;
                    Table.nativeNullifyLink(nativePtr, thermometryRecordBeanColumnInfo.personColKey, j4);
                }
                String realmGet$mediaId = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$mediaId();
                if (realmGet$mediaId != null) {
                    Table.nativeSetString(nativePtr, thermometryRecordBeanColumnInfo.mediaIdColKey, j4, realmGet$mediaId, false);
                } else {
                    Table.nativeSetNull(nativePtr, thermometryRecordBeanColumnInfo.mediaIdColKey, j4, false);
                }
                String realmGet$url = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$url();
                if (realmGet$url != null) {
                    Table.nativeSetString(nativePtr, thermometryRecordBeanColumnInfo.urlColKey, j4, realmGet$url, false);
                } else {
                    Table.nativeSetNull(nativePtr, thermometryRecordBeanColumnInfo.urlColKey, j4, false);
                }
                Table.nativeSetFloat(nativePtr, thermometryRecordBeanColumnInfo.temperatureColKey, j4, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$temperature(), false);
                String realmGet$imageName = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$imageName();
                if (realmGet$imageName != null) {
                    Table.nativeSetString(nativePtr, thermometryRecordBeanColumnInfo.imageNameColKey, j4, realmGet$imageName, false);
                } else {
                    Table.nativeSetNull(nativePtr, thermometryRecordBeanColumnInfo.imageNameColKey, j4, false);
                }
                Table.nativeSetBoolean(nativePtr, thermometryRecordBeanColumnInfo.isUploadColKey, j4, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmGet$isUpload(), false);
                j3 = j2;
            }
        }
    }

    public static ThermometryRecordBean createDetachedCopy(ThermometryRecordBean thermometryRecordBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        ThermometryRecordBean thermometryRecordBean2;
        if (i > i2 || thermometryRecordBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(thermometryRecordBean);
        if (cacheData == null) {
            thermometryRecordBean2 = new ThermometryRecordBean();
            map.put(thermometryRecordBean, new RealmObjectProxy.CacheData(i, thermometryRecordBean2));
        } else if (i >= cacheData.minDepth) {
            return (ThermometryRecordBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            thermometryRecordBean2 = (ThermometryRecordBean) cacheData.object;
        }
        com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface = thermometryRecordBean2;
        com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface2 = thermometryRecordBean;
        com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$person(com_ciot_realm_db_PersonRealmProxy.createDetachedCopy(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface2.realmGet$person(), i + 1, i2, map));
        com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$time(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface2.realmGet$time());
        com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$mediaId(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface2.realmGet$mediaId());
        com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$url(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface2.realmGet$url());
        com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$temperature(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface2.realmGet$temperature());
        com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$imageName(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface2.realmGet$imageName());
        com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface.realmSet$isUpload(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface2.realmGet$isUpload());
        return thermometryRecordBean2;
    }

    static ThermometryRecordBean update(Realm realm, ThermometryRecordBeanColumnInfo thermometryRecordBeanColumnInfo, ThermometryRecordBean thermometryRecordBean, ThermometryRecordBean thermometryRecordBean2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface = thermometryRecordBean;
        com_ciot_realm_db_ThermometryRecordBeanRealmProxyInterface com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface2 = thermometryRecordBean2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(ThermometryRecordBean.class), set);
        Person realmGet$person = com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface2.realmGet$person();
        if (realmGet$person == null) {
            osObjectBuilder.addNull(thermometryRecordBeanColumnInfo.personColKey);
        } else {
            Person person = (Person) map.get(realmGet$person);
            if (person != null) {
                osObjectBuilder.addObject(thermometryRecordBeanColumnInfo.personColKey, person);
            } else {
                osObjectBuilder.addObject(thermometryRecordBeanColumnInfo.personColKey, com_ciot_realm_db_PersonRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_PersonRealmProxy.PersonColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Person.class), realmGet$person, true, map, set));
            }
        }
        osObjectBuilder.addInteger(thermometryRecordBeanColumnInfo.timeColKey, Long.valueOf(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface2.realmGet$time()));
        osObjectBuilder.addString(thermometryRecordBeanColumnInfo.mediaIdColKey, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface2.realmGet$mediaId());
        osObjectBuilder.addString(thermometryRecordBeanColumnInfo.urlColKey, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface2.realmGet$url());
        osObjectBuilder.addFloat(thermometryRecordBeanColumnInfo.temperatureColKey, Float.valueOf(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface2.realmGet$temperature()));
        osObjectBuilder.addString(thermometryRecordBeanColumnInfo.imageNameColKey, com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface2.realmGet$imageName());
        osObjectBuilder.addBoolean(thermometryRecordBeanColumnInfo.isUploadColKey, Boolean.valueOf(com_ciot_realm_db_thermometryrecordbeanrealmproxyinterface2.realmGet$isUpload()));
        osObjectBuilder.updateExistingObject();
        return thermometryRecordBean;
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
        com_ciot_realm_db_ThermometryRecordBeanRealmProxy com_ciot_realm_db_thermometryrecordbeanrealmproxy = (com_ciot_realm_db_ThermometryRecordBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_thermometryrecordbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_thermometryrecordbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_thermometryrecordbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
