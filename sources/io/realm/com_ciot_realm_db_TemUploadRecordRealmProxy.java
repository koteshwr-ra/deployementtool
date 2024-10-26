package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.TemRecord;
import com.ciot.realm.db.TemUploadRecord;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_TemRecordRealmProxy;
import io.realm.exceptions.RealmException;
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
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.spi.Configurator;
import xcrash.TombstoneParser;

public class com_ciot_realm_db_TemUploadRecordRealmProxy extends TemUploadRecord implements RealmObjectProxy, com_ciot_realm_db_TemUploadRecordRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private TemUploadRecordColumnInfo columnInfo;
    private RealmList<TemRecord> datasRealmList;
    private ProxyState<TemUploadRecord> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "TemUploadRecord";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class TemUploadRecordColumnInfo extends ColumnInfo {
        long codeColKey;
        long datasColKey;
        long filepathColKey;
        long pathnameColKey;
        long robotColKey;
        long uploadtimeColKey;
        long urlColKey;

        TemUploadRecordColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.uploadtimeColKey = addColumnDetails("uploadtime", "uploadtime", objectSchemaInfo);
            this.codeColKey = addColumnDetails(TombstoneParser.keyCode, TombstoneParser.keyCode, objectSchemaInfo);
            this.datasColKey = addColumnDetails("datas", "datas", objectSchemaInfo);
            this.urlColKey = addColumnDetails(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL, TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL, objectSchemaInfo);
            this.robotColKey = addColumnDetails("robot", "robot", objectSchemaInfo);
            this.filepathColKey = addColumnDetails("filepath", "filepath", objectSchemaInfo);
            this.pathnameColKey = addColumnDetails("pathname", "pathname", objectSchemaInfo);
        }

        TemUploadRecordColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new TemUploadRecordColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            TemUploadRecordColumnInfo temUploadRecordColumnInfo = (TemUploadRecordColumnInfo) columnInfo;
            TemUploadRecordColumnInfo temUploadRecordColumnInfo2 = (TemUploadRecordColumnInfo) columnInfo2;
            temUploadRecordColumnInfo2.uploadtimeColKey = temUploadRecordColumnInfo.uploadtimeColKey;
            temUploadRecordColumnInfo2.codeColKey = temUploadRecordColumnInfo.codeColKey;
            temUploadRecordColumnInfo2.datasColKey = temUploadRecordColumnInfo.datasColKey;
            temUploadRecordColumnInfo2.urlColKey = temUploadRecordColumnInfo.urlColKey;
            temUploadRecordColumnInfo2.robotColKey = temUploadRecordColumnInfo.robotColKey;
            temUploadRecordColumnInfo2.filepathColKey = temUploadRecordColumnInfo.filepathColKey;
            temUploadRecordColumnInfo2.pathnameColKey = temUploadRecordColumnInfo.pathnameColKey;
        }
    }

    com_ciot_realm_db_TemUploadRecordRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (TemUploadRecordColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<TemUploadRecord> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public long realmGet$uploadtime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.uploadtimeColKey);
    }

    public void realmSet$uploadtime(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'uploadtime' cannot be changed after object was created.");
        }
    }

    public String realmGet$code() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.codeColKey);
    }

    public void realmSet$code(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.codeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.codeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.codeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.codeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public RealmList<TemRecord> realmGet$datas() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<TemRecord> realmList = this.datasRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<TemRecord> realmList2 = new RealmList<>(TemRecord.class, this.proxyState.getRow$realm().getModelList(this.columnInfo.datasColKey), this.proxyState.getRealm$realm());
        this.datasRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$datas(RealmList<TemRecord> realmList) {
        int i = 0;
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("datas")) {
                return;
            }
            if (realmList != null && !realmList.isManaged()) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<TemRecord> realmList2 = new RealmList<>();
                Iterator<TemRecord> it = realmList.iterator();
                while (it.hasNext()) {
                    TemRecord next = it.next();
                    if (next == null || RealmObject.isManaged(next)) {
                        realmList2.add(next);
                    } else {
                        realmList2.add((TemRecord) realm.copyToRealm(next, new ImportFlag[0]));
                    }
                }
                realmList = realmList2;
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList modelList = this.proxyState.getRow$realm().getModelList(this.columnInfo.datasColKey);
        if (realmList == null || ((long) realmList.size()) != modelList.size()) {
            modelList.removeAll();
            if (realmList != null) {
                int size = realmList.size();
                while (i < size) {
                    TemRecord temRecord = realmList.get(i);
                    this.proxyState.checkValidObject(temRecord);
                    modelList.addRow(((RealmObjectProxy) temRecord).realmGet$proxyState().getRow$realm().getObjectKey());
                    i++;
                }
                return;
            }
            return;
        }
        int size2 = realmList.size();
        while (i < size2) {
            TemRecord temRecord2 = realmList.get(i);
            this.proxyState.checkValidObject(temRecord2);
            modelList.setRow((long) i, ((RealmObjectProxy) temRecord2).realmGet$proxyState().getRow$realm().getObjectKey());
            i++;
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

    public String realmGet$robot() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.robotColKey);
    }

    public void realmSet$robot(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.robotColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.robotColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.robotColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.robotColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$filepath() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.filepathColKey);
    }

    public void realmSet$filepath(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.filepathColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.filepathColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.filepathColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.filepathColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$pathname() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.pathnameColKey);
    }

    public void realmSet$pathname(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.pathnameColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.pathnameColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.pathnameColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.pathnameColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 7, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("uploadtime", RealmFieldType.INTEGER, true, true, true);
        builder2.addPersistedProperty(TombstoneParser.keyCode, RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("datas", RealmFieldType.LIST, com_ciot_realm_db_TemRecordRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder3 = builder;
        builder3.addPersistedProperty(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL, RealmFieldType.STRING, false, false, false);
        builder3.addPersistedProperty("robot", RealmFieldType.STRING, false, false, false);
        builder3.addPersistedProperty("filepath", RealmFieldType.STRING, false, false, false);
        builder3.addPersistedProperty("pathname", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TemUploadRecordColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new TemUploadRecordColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0148  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.TemUploadRecord createOrUpdateUsingJsonObject(io.realm.Realm r15, org.json.JSONObject r16, boolean r17) throws org.json.JSONException {
        /*
            r0 = r15
            r7 = r16
            r8 = r17
            java.util.ArrayList r9 = new java.util.ArrayList
            r10 = 1
            r9.<init>(r10)
            java.lang.String r11 = "uploadtime"
            r12 = 0
            if (r8 == 0) goto L_0x0069
            java.lang.Class<com.ciot.realm.db.TemUploadRecord> r1 = com.ciot.realm.db.TemUploadRecord.class
            io.realm.internal.Table r1 = r15.getTable(r1)
            io.realm.RealmSchema r2 = r15.getSchema()
            java.lang.Class<com.ciot.realm.db.TemUploadRecord> r3 = com.ciot.realm.db.TemUploadRecord.class
            io.realm.internal.ColumnInfo r2 = r2.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)
            io.realm.com_ciot_realm_db_TemUploadRecordRealmProxy$TemUploadRecordColumnInfo r2 = (io.realm.com_ciot_realm_db_TemUploadRecordRealmProxy.TemUploadRecordColumnInfo) r2
            long r2 = r2.uploadtimeColKey
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
            java.lang.Class<com.ciot.realm.db.TemUploadRecord> r2 = com.ciot.realm.db.TemUploadRecord.class
            io.realm.internal.ColumnInfo r4 = r1.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r2)     // Catch:{ all -> 0x0064 }
            r5 = 0
            java.util.List r6 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0064 }
            r1 = r13
            r2 = r15
            r1.set(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0064 }
            io.realm.com_ciot_realm_db_TemUploadRecordRealmProxy r1 = new io.realm.com_ciot_realm_db_TemUploadRecordRealmProxy     // Catch:{ all -> 0x0064 }
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
            java.lang.String r2 = "datas"
            if (r1 != 0) goto L_0x00a5
            boolean r1 = r7.has(r2)
            if (r1 == 0) goto L_0x0077
            r9.add(r2)
        L_0x0077:
            boolean r1 = r7.has(r11)
            if (r1 == 0) goto L_0x009d
            boolean r1 = r7.isNull(r11)
            if (r1 == 0) goto L_0x008c
            java.lang.Class<com.ciot.realm.db.TemUploadRecord> r1 = com.ciot.realm.db.TemUploadRecord.class
            io.realm.RealmModel r1 = r15.createObjectInternal(r1, r12, r10, r9)
            io.realm.com_ciot_realm_db_TemUploadRecordRealmProxy r1 = (io.realm.com_ciot_realm_db_TemUploadRecordRealmProxy) r1
            goto L_0x00a5
        L_0x008c:
            java.lang.Class<com.ciot.realm.db.TemUploadRecord> r1 = com.ciot.realm.db.TemUploadRecord.class
            long r3 = r7.getLong(r11)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            io.realm.RealmModel r1 = r15.createObjectInternal(r1, r3, r10, r9)
            io.realm.com_ciot_realm_db_TemUploadRecordRealmProxy r1 = (io.realm.com_ciot_realm_db_TemUploadRecordRealmProxy) r1
            goto L_0x00a5
        L_0x009d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "JSON object doesn't have the primary key field 'uploadtime'."
            r0.<init>(r1)
            throw r0
        L_0x00a5:
            r3 = r1
            io.realm.com_ciot_realm_db_TemUploadRecordRealmProxyInterface r3 = (io.realm.com_ciot_realm_db_TemUploadRecordRealmProxyInterface) r3
            java.lang.String r4 = "code"
            boolean r5 = r7.has(r4)
            if (r5 == 0) goto L_0x00c1
            boolean r5 = r7.isNull(r4)
            if (r5 == 0) goto L_0x00ba
            r3.realmSet$code(r12)
            goto L_0x00c1
        L_0x00ba:
            java.lang.String r4 = r7.getString(r4)
            r3.realmSet$code(r4)
        L_0x00c1:
            boolean r4 = r7.has(r2)
            if (r4 == 0) goto L_0x00f5
            boolean r4 = r7.isNull(r2)
            if (r4 == 0) goto L_0x00d1
            r3.realmSet$datas(r12)
            goto L_0x00f5
        L_0x00d1:
            io.realm.RealmList r4 = r3.realmGet$datas()
            r4.clear()
            org.json.JSONArray r2 = r7.getJSONArray(r2)
            r4 = 0
        L_0x00dd:
            int r5 = r2.length()
            if (r4 >= r5) goto L_0x00f5
            org.json.JSONObject r5 = r2.getJSONObject(r4)
            com.ciot.realm.db.TemRecord r5 = io.realm.com_ciot_realm_db_TemRecordRealmProxy.createOrUpdateUsingJsonObject(r15, r5, r8)
            io.realm.RealmList r6 = r3.realmGet$datas()
            r6.add(r5)
            int r4 = r4 + 1
            goto L_0x00dd
        L_0x00f5:
            java.lang.String r0 = "url"
            boolean r2 = r7.has(r0)
            if (r2 == 0) goto L_0x010e
            boolean r2 = r7.isNull(r0)
            if (r2 == 0) goto L_0x0107
            r3.realmSet$url(r12)
            goto L_0x010e
        L_0x0107:
            java.lang.String r0 = r7.getString(r0)
            r3.realmSet$url(r0)
        L_0x010e:
            java.lang.String r0 = "robot"
            boolean r2 = r7.has(r0)
            if (r2 == 0) goto L_0x0127
            boolean r2 = r7.isNull(r0)
            if (r2 == 0) goto L_0x0120
            r3.realmSet$robot(r12)
            goto L_0x0127
        L_0x0120:
            java.lang.String r0 = r7.getString(r0)
            r3.realmSet$robot(r0)
        L_0x0127:
            java.lang.String r0 = "filepath"
            boolean r2 = r7.has(r0)
            if (r2 == 0) goto L_0x0140
            boolean r2 = r7.isNull(r0)
            if (r2 == 0) goto L_0x0139
            r3.realmSet$filepath(r12)
            goto L_0x0140
        L_0x0139:
            java.lang.String r0 = r7.getString(r0)
            r3.realmSet$filepath(r0)
        L_0x0140:
            java.lang.String r0 = "pathname"
            boolean r2 = r7.has(r0)
            if (r2 == 0) goto L_0x0159
            boolean r2 = r7.isNull(r0)
            if (r2 == 0) goto L_0x0152
            r3.realmSet$pathname(r12)
            goto L_0x0159
        L_0x0152:
            java.lang.String r0 = r7.getString(r0)
            r3.realmSet$pathname(r0)
        L_0x0159:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_TemUploadRecordRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.TemUploadRecord");
    }

    public static TemUploadRecord createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        TemUploadRecord temUploadRecord = new TemUploadRecord();
        com_ciot_realm_db_TemUploadRecordRealmProxyInterface com_ciot_realm_db_temuploadrecordrealmproxyinterface = temUploadRecord;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("uploadtime")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$uploadtime(jsonReader.nextLong());
                    z = true;
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'uploadtime' to null.");
                }
            } else if (nextName.equals(TombstoneParser.keyCode)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$code(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$code((String) null);
                }
            } else if (nextName.equals("datas")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$datas((RealmList<TemRecord>) null);
                } else {
                    com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$datas(new RealmList());
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$datas().add(com_ciot_realm_db_TemRecordRealmProxy.createUsingJsonStream(realm, jsonReader));
                    }
                    jsonReader.endArray();
                }
            } else if (nextName.equals(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$url(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$url((String) null);
                }
            } else if (nextName.equals("robot")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$robot(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$robot((String) null);
                }
            } else if (nextName.equals("filepath")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$filepath(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$filepath((String) null);
                }
            } else if (!nextName.equals("pathname")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$pathname(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$pathname((String) null);
            }
        }
        jsonReader.endObject();
        if (z) {
            return (TemUploadRecord) realm.copyToRealm(temUploadRecord, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'uploadtime'.");
    }

    private static com_ciot_realm_db_TemUploadRecordRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) TemUploadRecord.class), false, Collections.emptyList());
        com_ciot_realm_db_TemUploadRecordRealmProxy com_ciot_realm_db_temuploadrecordrealmproxy = new com_ciot_realm_db_TemUploadRecordRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_temuploadrecordrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.TemUploadRecord copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_TemUploadRecordRealmProxy.TemUploadRecordColumnInfo r9, com.ciot.realm.db.TemUploadRecord r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.TemUploadRecord r1 = (com.ciot.realm.db.TemUploadRecord) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0092
            java.lang.Class<com.ciot.realm.db.TemUploadRecord> r2 = com.ciot.realm.db.TemUploadRecord.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.uploadtimeColKey
            r5 = r10
            io.realm.com_ciot_realm_db_TemUploadRecordRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_TemUploadRecordRealmProxyInterface) r5
            long r5 = r5.realmGet$uploadtime()
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
            io.realm.com_ciot_realm_db_TemUploadRecordRealmProxy r1 = new io.realm.com_ciot_realm_db_TemUploadRecordRealmProxy     // Catch:{ all -> 0x008d }
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
            com.ciot.realm.db.TemUploadRecord r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00a4
        L_0x00a0:
            com.ciot.realm.db.TemUploadRecord r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00a4:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_TemUploadRecordRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_TemUploadRecordRealmProxy$TemUploadRecordColumnInfo, com.ciot.realm.db.TemUploadRecord, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.TemUploadRecord");
    }

    public static TemUploadRecord copy(Realm realm, TemUploadRecordColumnInfo temUploadRecordColumnInfo, TemUploadRecord temUploadRecord, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(temUploadRecord);
        if (realmObjectProxy != null) {
            return (TemUploadRecord) realmObjectProxy;
        }
        com_ciot_realm_db_TemUploadRecordRealmProxyInterface com_ciot_realm_db_temuploadrecordrealmproxyinterface = temUploadRecord;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(TemUploadRecord.class), set);
        osObjectBuilder.addInteger(temUploadRecordColumnInfo.uploadtimeColKey, Long.valueOf(com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$uploadtime()));
        osObjectBuilder.addString(temUploadRecordColumnInfo.codeColKey, com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$code());
        osObjectBuilder.addString(temUploadRecordColumnInfo.urlColKey, com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$url());
        osObjectBuilder.addString(temUploadRecordColumnInfo.robotColKey, com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$robot());
        osObjectBuilder.addString(temUploadRecordColumnInfo.filepathColKey, com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$filepath());
        osObjectBuilder.addString(temUploadRecordColumnInfo.pathnameColKey, com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$pathname());
        com_ciot_realm_db_TemUploadRecordRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(temUploadRecord, newProxyInstance);
        RealmList<TemRecord> realmGet$datas = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$datas();
        if (realmGet$datas != null) {
            RealmList<TemRecord> realmGet$datas2 = newProxyInstance.realmGet$datas();
            realmGet$datas2.clear();
            for (int i = 0; i < realmGet$datas.size(); i++) {
                TemRecord temRecord = realmGet$datas.get(i);
                TemRecord temRecord2 = (TemRecord) map.get(temRecord);
                if (temRecord2 != null) {
                    realmGet$datas2.add(temRecord2);
                } else {
                    realmGet$datas2.add(com_ciot_realm_db_TemRecordRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_TemRecordRealmProxy.TemRecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TemRecord.class), temRecord, z, map, set));
                }
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, TemUploadRecord temUploadRecord, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Realm realm2 = realm;
        TemUploadRecord temUploadRecord2 = temUploadRecord;
        Map<RealmModel, Long> map2 = map;
        if ((temUploadRecord2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(temUploadRecord)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) temUploadRecord2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(TemUploadRecord.class);
        long nativePtr = table.getNativePtr();
        TemUploadRecordColumnInfo temUploadRecordColumnInfo = (TemUploadRecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TemUploadRecord.class);
        long j4 = temUploadRecordColumnInfo.uploadtimeColKey;
        com_ciot_realm_db_TemUploadRecordRealmProxyInterface com_ciot_realm_db_temuploadrecordrealmproxyinterface = temUploadRecord2;
        Long valueOf = Long.valueOf(com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$uploadtime());
        if (valueOf != null) {
            j = Table.nativeFindFirstInt(nativePtr, j4, com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$uploadtime());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j4, Long.valueOf(com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$uploadtime()));
        } else {
            Table.throwDuplicatePrimaryKeyException(valueOf);
        }
        long j5 = j;
        map2.put(temUploadRecord2, Long.valueOf(j5));
        String realmGet$code = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$code();
        if (realmGet$code != null) {
            long j6 = nativePtr;
            j2 = nativePtr;
            j3 = j5;
            Table.nativeSetString(j6, temUploadRecordColumnInfo.codeColKey, j5, realmGet$code, false);
        } else {
            j2 = nativePtr;
            j3 = j5;
        }
        RealmList<TemRecord> realmGet$datas = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$datas();
        if (realmGet$datas != null) {
            OsList osList = new OsList(table.getUncheckedRow(j3), temUploadRecordColumnInfo.datasColKey);
            Iterator<TemRecord> it = realmGet$datas.iterator();
            while (it.hasNext()) {
                TemRecord next = it.next();
                Long l = map2.get(next);
                if (l == null) {
                    l = Long.valueOf(com_ciot_realm_db_TemRecordRealmProxy.insert(realm2, next, map2));
                }
                osList.addRow(l.longValue());
            }
        }
        String realmGet$url = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$url();
        if (realmGet$url != null) {
            Table.nativeSetString(j2, temUploadRecordColumnInfo.urlColKey, j3, realmGet$url, false);
        }
        String realmGet$robot = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$robot();
        if (realmGet$robot != null) {
            Table.nativeSetString(j2, temUploadRecordColumnInfo.robotColKey, j3, realmGet$robot, false);
        }
        String realmGet$filepath = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$filepath();
        if (realmGet$filepath != null) {
            Table.nativeSetString(j2, temUploadRecordColumnInfo.filepathColKey, j3, realmGet$filepath, false);
        }
        String realmGet$pathname = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$pathname();
        if (realmGet$pathname != null) {
            Table.nativeSetString(j2, temUploadRecordColumnInfo.pathnameColKey, j3, realmGet$pathname, false);
        }
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        long j4;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(TemUploadRecord.class);
        long nativePtr = table.getNativePtr();
        TemUploadRecordColumnInfo temUploadRecordColumnInfo = (TemUploadRecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TemUploadRecord.class);
        long j5 = temUploadRecordColumnInfo.uploadtimeColKey;
        while (it.hasNext()) {
            TemUploadRecord temUploadRecord = (TemUploadRecord) it.next();
            if (!map2.containsKey(temUploadRecord)) {
                if ((temUploadRecord instanceof RealmObjectProxy) && !RealmObject.isFrozen(temUploadRecord)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) temUploadRecord;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(temUploadRecord, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_TemUploadRecordRealmProxyInterface com_ciot_realm_db_temuploadrecordrealmproxyinterface = temUploadRecord;
                Long valueOf = Long.valueOf(com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$uploadtime());
                if (valueOf != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j5, com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$uploadtime());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j5, Long.valueOf(com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$uploadtime()));
                } else {
                    Table.throwDuplicatePrimaryKeyException(valueOf);
                }
                long j6 = j;
                map2.put(temUploadRecord, Long.valueOf(j6));
                String realmGet$code = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$code();
                if (realmGet$code != null) {
                    j2 = j6;
                    Table.nativeSetString(nativePtr, temUploadRecordColumnInfo.codeColKey, j6, realmGet$code, false);
                } else {
                    j2 = j6;
                }
                RealmList<TemRecord> realmGet$datas = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$datas();
                if (realmGet$datas != null) {
                    j3 = j2;
                    OsList osList = new OsList(table.getUncheckedRow(j3), temUploadRecordColumnInfo.datasColKey);
                    Iterator<TemRecord> it2 = realmGet$datas.iterator();
                    while (it2.hasNext()) {
                        TemRecord next = it2.next();
                        Long l = map2.get(next);
                        if (l == null) {
                            l = Long.valueOf(com_ciot_realm_db_TemRecordRealmProxy.insert(realm2, next, map2));
                        }
                        osList.addRow(l.longValue());
                    }
                } else {
                    j3 = j2;
                }
                String realmGet$url = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$url();
                if (realmGet$url != null) {
                    j4 = j3;
                    Table.nativeSetString(nativePtr, temUploadRecordColumnInfo.urlColKey, j3, realmGet$url, false);
                } else {
                    j4 = j3;
                }
                String realmGet$robot = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$robot();
                if (realmGet$robot != null) {
                    Table.nativeSetString(nativePtr, temUploadRecordColumnInfo.robotColKey, j4, realmGet$robot, false);
                }
                String realmGet$filepath = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$filepath();
                if (realmGet$filepath != null) {
                    Table.nativeSetString(nativePtr, temUploadRecordColumnInfo.filepathColKey, j4, realmGet$filepath, false);
                }
                String realmGet$pathname = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$pathname();
                if (realmGet$pathname != null) {
                    Table.nativeSetString(nativePtr, temUploadRecordColumnInfo.pathnameColKey, j4, realmGet$pathname, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, TemUploadRecord temUploadRecord, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Realm realm2 = realm;
        TemUploadRecord temUploadRecord2 = temUploadRecord;
        Map<RealmModel, Long> map2 = map;
        if ((temUploadRecord2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(temUploadRecord)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) temUploadRecord2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(TemUploadRecord.class);
        long nativePtr = table.getNativePtr();
        TemUploadRecordColumnInfo temUploadRecordColumnInfo = (TemUploadRecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TemUploadRecord.class);
        long j4 = temUploadRecordColumnInfo.uploadtimeColKey;
        com_ciot_realm_db_TemUploadRecordRealmProxyInterface com_ciot_realm_db_temuploadrecordrealmproxyinterface = temUploadRecord2;
        if (Long.valueOf(com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$uploadtime()) != null) {
            j = Table.nativeFindFirstInt(nativePtr, j4, com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$uploadtime());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j4, Long.valueOf(com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$uploadtime()));
        }
        long j5 = j;
        map2.put(temUploadRecord2, Long.valueOf(j5));
        String realmGet$code = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$code();
        if (realmGet$code != null) {
            j2 = j5;
            Table.nativeSetString(nativePtr, temUploadRecordColumnInfo.codeColKey, j5, realmGet$code, false);
        } else {
            j2 = j5;
            Table.nativeSetNull(nativePtr, temUploadRecordColumnInfo.codeColKey, j2, false);
        }
        long j6 = j2;
        OsList osList = new OsList(table.getUncheckedRow(j6), temUploadRecordColumnInfo.datasColKey);
        RealmList<TemRecord> realmGet$datas = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$datas();
        if (realmGet$datas == null || ((long) realmGet$datas.size()) != osList.size()) {
            osList.removeAll();
            if (realmGet$datas != null) {
                Iterator<TemRecord> it = realmGet$datas.iterator();
                while (it.hasNext()) {
                    TemRecord next = it.next();
                    Long l = map2.get(next);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_TemRecordRealmProxy.insertOrUpdate(realm2, next, map2));
                    }
                    osList.addRow(l.longValue());
                }
            }
        } else {
            int size = realmGet$datas.size();
            for (int i = 0; i < size; i++) {
                TemRecord temRecord = realmGet$datas.get(i);
                Long l2 = map2.get(temRecord);
                if (l2 == null) {
                    l2 = Long.valueOf(com_ciot_realm_db_TemRecordRealmProxy.insertOrUpdate(realm2, temRecord, map2));
                }
                osList.setRow((long) i, l2.longValue());
            }
        }
        String realmGet$url = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$url();
        if (realmGet$url != null) {
            j3 = j6;
            Table.nativeSetString(nativePtr, temUploadRecordColumnInfo.urlColKey, j6, realmGet$url, false);
        } else {
            j3 = j6;
            Table.nativeSetNull(nativePtr, temUploadRecordColumnInfo.urlColKey, j3, false);
        }
        String realmGet$robot = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$robot();
        if (realmGet$robot != null) {
            Table.nativeSetString(nativePtr, temUploadRecordColumnInfo.robotColKey, j3, realmGet$robot, false);
        } else {
            Table.nativeSetNull(nativePtr, temUploadRecordColumnInfo.robotColKey, j3, false);
        }
        String realmGet$filepath = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$filepath();
        if (realmGet$filepath != null) {
            Table.nativeSetString(nativePtr, temUploadRecordColumnInfo.filepathColKey, j3, realmGet$filepath, false);
        } else {
            Table.nativeSetNull(nativePtr, temUploadRecordColumnInfo.filepathColKey, j3, false);
        }
        String realmGet$pathname = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$pathname();
        if (realmGet$pathname != null) {
            Table.nativeSetString(nativePtr, temUploadRecordColumnInfo.pathnameColKey, j3, realmGet$pathname, false);
        } else {
            Table.nativeSetNull(nativePtr, temUploadRecordColumnInfo.pathnameColKey, j3, false);
        }
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        long j4;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(TemUploadRecord.class);
        long nativePtr = table.getNativePtr();
        TemUploadRecordColumnInfo temUploadRecordColumnInfo = (TemUploadRecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TemUploadRecord.class);
        long j5 = temUploadRecordColumnInfo.uploadtimeColKey;
        while (it.hasNext()) {
            TemUploadRecord temUploadRecord = (TemUploadRecord) it.next();
            if (!map2.containsKey(temUploadRecord)) {
                if ((temUploadRecord instanceof RealmObjectProxy) && !RealmObject.isFrozen(temUploadRecord)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) temUploadRecord;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(temUploadRecord, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_TemUploadRecordRealmProxyInterface com_ciot_realm_db_temuploadrecordrealmproxyinterface = temUploadRecord;
                if (Long.valueOf(com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$uploadtime()) != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j5, com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$uploadtime());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j5, Long.valueOf(com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$uploadtime()));
                }
                long j6 = j;
                map2.put(temUploadRecord, Long.valueOf(j6));
                String realmGet$code = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$code();
                if (realmGet$code != null) {
                    j2 = j6;
                    Table.nativeSetString(nativePtr, temUploadRecordColumnInfo.codeColKey, j6, realmGet$code, false);
                } else {
                    j2 = j6;
                    Table.nativeSetNull(nativePtr, temUploadRecordColumnInfo.codeColKey, j6, false);
                }
                long j7 = j2;
                OsList osList = new OsList(table.getUncheckedRow(j7), temUploadRecordColumnInfo.datasColKey);
                RealmList<TemRecord> realmGet$datas = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$datas();
                if (realmGet$datas == null || ((long) realmGet$datas.size()) != osList.size()) {
                    j3 = j5;
                    osList.removeAll();
                    if (realmGet$datas != null) {
                        Iterator<TemRecord> it2 = realmGet$datas.iterator();
                        while (it2.hasNext()) {
                            TemRecord next = it2.next();
                            Long l = map2.get(next);
                            if (l == null) {
                                l = Long.valueOf(com_ciot_realm_db_TemRecordRealmProxy.insertOrUpdate(realm2, next, map2));
                            }
                            osList.addRow(l.longValue());
                        }
                    }
                } else {
                    int size = realmGet$datas.size();
                    int i = 0;
                    while (i < size) {
                        TemRecord temRecord = realmGet$datas.get(i);
                        Long l2 = map2.get(temRecord);
                        if (l2 == null) {
                            l2 = Long.valueOf(com_ciot_realm_db_TemRecordRealmProxy.insertOrUpdate(realm2, temRecord, map2));
                        }
                        osList.setRow((long) i, l2.longValue());
                        i++;
                        j5 = j5;
                    }
                    j3 = j5;
                }
                String realmGet$url = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$url();
                if (realmGet$url != null) {
                    j4 = j7;
                    Table.nativeSetString(nativePtr, temUploadRecordColumnInfo.urlColKey, j7, realmGet$url, false);
                } else {
                    j4 = j7;
                    Table.nativeSetNull(nativePtr, temUploadRecordColumnInfo.urlColKey, j4, false);
                }
                String realmGet$robot = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$robot();
                if (realmGet$robot != null) {
                    Table.nativeSetString(nativePtr, temUploadRecordColumnInfo.robotColKey, j4, realmGet$robot, false);
                } else {
                    Table.nativeSetNull(nativePtr, temUploadRecordColumnInfo.robotColKey, j4, false);
                }
                String realmGet$filepath = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$filepath();
                if (realmGet$filepath != null) {
                    Table.nativeSetString(nativePtr, temUploadRecordColumnInfo.filepathColKey, j4, realmGet$filepath, false);
                } else {
                    Table.nativeSetNull(nativePtr, temUploadRecordColumnInfo.filepathColKey, j4, false);
                }
                String realmGet$pathname = com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmGet$pathname();
                if (realmGet$pathname != null) {
                    Table.nativeSetString(nativePtr, temUploadRecordColumnInfo.pathnameColKey, j4, realmGet$pathname, false);
                } else {
                    Table.nativeSetNull(nativePtr, temUploadRecordColumnInfo.pathnameColKey, j4, false);
                }
                j5 = j3;
            }
        }
    }

    public static TemUploadRecord createDetachedCopy(TemUploadRecord temUploadRecord, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        TemUploadRecord temUploadRecord2;
        if (i > i2 || temUploadRecord == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(temUploadRecord);
        if (cacheData == null) {
            temUploadRecord2 = new TemUploadRecord();
            map.put(temUploadRecord, new RealmObjectProxy.CacheData(i, temUploadRecord2));
        } else if (i >= cacheData.minDepth) {
            return (TemUploadRecord) cacheData.object;
        } else {
            cacheData.minDepth = i;
            temUploadRecord2 = (TemUploadRecord) cacheData.object;
        }
        com_ciot_realm_db_TemUploadRecordRealmProxyInterface com_ciot_realm_db_temuploadrecordrealmproxyinterface = temUploadRecord2;
        com_ciot_realm_db_TemUploadRecordRealmProxyInterface com_ciot_realm_db_temuploadrecordrealmproxyinterface2 = temUploadRecord;
        com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$uploadtime(com_ciot_realm_db_temuploadrecordrealmproxyinterface2.realmGet$uploadtime());
        com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$code(com_ciot_realm_db_temuploadrecordrealmproxyinterface2.realmGet$code());
        if (i == i2) {
            com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$datas((RealmList<TemRecord>) null);
        } else {
            RealmList<TemRecord> realmGet$datas = com_ciot_realm_db_temuploadrecordrealmproxyinterface2.realmGet$datas();
            RealmList realmList = new RealmList();
            com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$datas(realmList);
            int i3 = i + 1;
            int size = realmGet$datas.size();
            for (int i4 = 0; i4 < size; i4++) {
                realmList.add(com_ciot_realm_db_TemRecordRealmProxy.createDetachedCopy(realmGet$datas.get(i4), i3, i2, map));
            }
        }
        com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$url(com_ciot_realm_db_temuploadrecordrealmproxyinterface2.realmGet$url());
        com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$robot(com_ciot_realm_db_temuploadrecordrealmproxyinterface2.realmGet$robot());
        com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$filepath(com_ciot_realm_db_temuploadrecordrealmproxyinterface2.realmGet$filepath());
        com_ciot_realm_db_temuploadrecordrealmproxyinterface.realmSet$pathname(com_ciot_realm_db_temuploadrecordrealmproxyinterface2.realmGet$pathname());
        return temUploadRecord2;
    }

    static TemUploadRecord update(Realm realm, TemUploadRecordColumnInfo temUploadRecordColumnInfo, TemUploadRecord temUploadRecord, TemUploadRecord temUploadRecord2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        TemUploadRecordColumnInfo temUploadRecordColumnInfo2 = temUploadRecordColumnInfo;
        com_ciot_realm_db_TemUploadRecordRealmProxyInterface com_ciot_realm_db_temuploadrecordrealmproxyinterface = temUploadRecord;
        com_ciot_realm_db_TemUploadRecordRealmProxyInterface com_ciot_realm_db_temuploadrecordrealmproxyinterface2 = temUploadRecord2;
        Realm realm2 = realm;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(TemUploadRecord.class), set);
        osObjectBuilder.addInteger(temUploadRecordColumnInfo2.uploadtimeColKey, Long.valueOf(com_ciot_realm_db_temuploadrecordrealmproxyinterface2.realmGet$uploadtime()));
        osObjectBuilder.addString(temUploadRecordColumnInfo2.codeColKey, com_ciot_realm_db_temuploadrecordrealmproxyinterface2.realmGet$code());
        RealmList<TemRecord> realmGet$datas = com_ciot_realm_db_temuploadrecordrealmproxyinterface2.realmGet$datas();
        if (realmGet$datas != null) {
            RealmList realmList = new RealmList();
            for (int i = 0; i < realmGet$datas.size(); i++) {
                TemRecord temRecord = realmGet$datas.get(i);
                TemRecord temRecord2 = (TemRecord) map.get(temRecord);
                if (temRecord2 != null) {
                    realmList.add(temRecord2);
                } else {
                    realmList.add(com_ciot_realm_db_TemRecordRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_TemRecordRealmProxy.TemRecordColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TemRecord.class), temRecord, true, map, set));
                }
            }
            osObjectBuilder.addObjectList(temUploadRecordColumnInfo2.datasColKey, realmList);
        } else {
            osObjectBuilder.addObjectList(temUploadRecordColumnInfo2.datasColKey, new RealmList());
        }
        osObjectBuilder.addString(temUploadRecordColumnInfo2.urlColKey, com_ciot_realm_db_temuploadrecordrealmproxyinterface2.realmGet$url());
        osObjectBuilder.addString(temUploadRecordColumnInfo2.robotColKey, com_ciot_realm_db_temuploadrecordrealmproxyinterface2.realmGet$robot());
        osObjectBuilder.addString(temUploadRecordColumnInfo2.filepathColKey, com_ciot_realm_db_temuploadrecordrealmproxyinterface2.realmGet$filepath());
        osObjectBuilder.addString(temUploadRecordColumnInfo2.pathnameColKey, com_ciot_realm_db_temuploadrecordrealmproxyinterface2.realmGet$pathname());
        osObjectBuilder.updateExistingObject();
        return temUploadRecord;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("TemUploadRecord = proxy[");
        sb.append("{uploadtime:");
        sb.append(realmGet$uploadtime());
        sb.append("}");
        sb.append(",");
        sb.append("{code:");
        String realmGet$code = realmGet$code();
        String str = Configurator.NULL;
        sb.append(realmGet$code != null ? realmGet$code() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{datas:");
        sb.append("RealmList<TemRecord>[");
        sb.append(realmGet$datas().size());
        sb.append("]");
        sb.append("}");
        sb.append(",");
        sb.append("{url:");
        sb.append(realmGet$url() != null ? realmGet$url() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{robot:");
        sb.append(realmGet$robot() != null ? realmGet$robot() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{filepath:");
        sb.append(realmGet$filepath() != null ? realmGet$filepath() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{pathname:");
        if (realmGet$pathname() != null) {
            str = realmGet$pathname();
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
        com_ciot_realm_db_TemUploadRecordRealmProxy com_ciot_realm_db_temuploadrecordrealmproxy = (com_ciot_realm_db_TemUploadRecordRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_temuploadrecordrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_temuploadrecordrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_temuploadrecordrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
