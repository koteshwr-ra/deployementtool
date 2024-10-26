package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.MusicBean;
import com.limpoxe.support.servicemanager.ServiceProvider;
import io.realm.BaseRealm;
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

public class com_ciot_realm_db_MusicBeanRealmProxy extends MusicBean implements RealmObjectProxy, com_ciot_realm_db_MusicBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private MusicBeanColumnInfo columnInfo;
    private ProxyState<MusicBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "MusicBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class MusicBeanColumnInfo extends ColumnInfo {
        long durationColKey;
        long idColKey;
        long isSelectColKey;
        long nameColKey;
        long pathColKey;
        long singerColKey;
        long sizeColKey;

        MusicBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
            this.pathColKey = addColumnDetails("path", "path", objectSchemaInfo);
            this.singerColKey = addColumnDetails("singer", "singer", objectSchemaInfo);
            this.durationColKey = addColumnDetails("duration", "duration", objectSchemaInfo);
            this.sizeColKey = addColumnDetails("size", "size", objectSchemaInfo);
            this.isSelectColKey = addColumnDetails("isSelect", "isSelect", objectSchemaInfo);
        }

        MusicBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new MusicBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            MusicBeanColumnInfo musicBeanColumnInfo = (MusicBeanColumnInfo) columnInfo;
            MusicBeanColumnInfo musicBeanColumnInfo2 = (MusicBeanColumnInfo) columnInfo2;
            musicBeanColumnInfo2.idColKey = musicBeanColumnInfo.idColKey;
            musicBeanColumnInfo2.nameColKey = musicBeanColumnInfo.nameColKey;
            musicBeanColumnInfo2.pathColKey = musicBeanColumnInfo.pathColKey;
            musicBeanColumnInfo2.singerColKey = musicBeanColumnInfo.singerColKey;
            musicBeanColumnInfo2.durationColKey = musicBeanColumnInfo.durationColKey;
            musicBeanColumnInfo2.sizeColKey = musicBeanColumnInfo.sizeColKey;
            musicBeanColumnInfo2.isSelectColKey = musicBeanColumnInfo.isSelectColKey;
        }
    }

    com_ciot_realm_db_MusicBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (MusicBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<MusicBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public int realmGet$id() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.idColKey);
    }

    public void realmSet$id(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'id' cannot be changed after object was created.");
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

    public String realmGet$path() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.pathColKey);
    }

    public void realmSet$path(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.pathColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.pathColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.pathColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.pathColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$singer() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.singerColKey);
    }

    public void realmSet$singer(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.singerColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.singerColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.singerColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.singerColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public int realmGet$duration() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.durationColKey);
    }

    public void realmSet$duration(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.durationColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.durationColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public long realmGet$size() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.sizeColKey);
    }

    public void realmSet$size(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.sizeColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.sizeColKey, row$realm.getObjectKey(), j, true);
        }
    }

    public boolean realmGet$isSelect() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isSelectColKey);
    }

    public void realmSet$isSelect(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isSelectColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.isSelectColKey, row$realm.getObjectKey(), z, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 7, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("id", RealmFieldType.INTEGER, true, true, true);
        builder2.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("path", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("singer", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("duration", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("size", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("isSelect", RealmFieldType.BOOLEAN, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static MusicBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new MusicBeanColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v7, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v8, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0127  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.MusicBean createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            java.lang.String r1 = "id"
            r2 = 0
            if (r14 == 0) goto L_0x0061
            java.lang.Class<com.ciot.realm.db.MusicBean> r14 = com.ciot.realm.db.MusicBean.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.MusicBean> r4 = com.ciot.realm.db.MusicBean.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_MusicBeanRealmProxy$MusicBeanColumnInfo r3 = (io.realm.com_ciot_realm_db_MusicBeanRealmProxy.MusicBeanColumnInfo) r3
            long r3 = r3.idColKey
            boolean r5 = r13.isNull(r1)
            r6 = -1
            if (r5 != 0) goto L_0x002e
            long r8 = r13.getLong(r1)
            long r3 = r14.findFirstLong(r3, r8)
            goto L_0x002f
        L_0x002e:
            r3 = r6
        L_0x002f:
            int r5 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r5 == 0) goto L_0x0061
            io.realm.BaseRealm$ThreadLocalRealmObjectContext r5 = io.realm.BaseRealm.objectContext
            java.lang.Object r5 = r5.get()
            io.realm.BaseRealm$RealmObjectContext r5 = (io.realm.BaseRealm.RealmObjectContext) r5
            io.realm.internal.UncheckedRow r8 = r14.getUncheckedRow(r3)     // Catch:{ all -> 0x005c }
            io.realm.RealmSchema r14 = r12.getSchema()     // Catch:{ all -> 0x005c }
            java.lang.Class<com.ciot.realm.db.MusicBean> r3 = com.ciot.realm.db.MusicBean.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005c }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005c }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005c }
            io.realm.com_ciot_realm_db_MusicBeanRealmProxy r14 = new io.realm.com_ciot_realm_db_MusicBeanRealmProxy     // Catch:{ all -> 0x005c }
            r14.<init>()     // Catch:{ all -> 0x005c }
            r5.clear()
            goto L_0x0062
        L_0x005c:
            r12 = move-exception
            r5.clear()
            throw r12
        L_0x0061:
            r14 = r2
        L_0x0062:
            if (r14 != 0) goto L_0x0095
            boolean r14 = r13.has(r1)
            if (r14 == 0) goto L_0x008d
            boolean r14 = r13.isNull(r1)
            r3 = 1
            if (r14 == 0) goto L_0x007b
            java.lang.Class<com.ciot.realm.db.MusicBean> r14 = com.ciot.realm.db.MusicBean.class
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_MusicBeanRealmProxy r14 = (io.realm.com_ciot_realm_db_MusicBeanRealmProxy) r14
            goto L_0x0095
        L_0x007b:
            java.lang.Class<com.ciot.realm.db.MusicBean> r14 = com.ciot.realm.db.MusicBean.class
            int r1 = r13.getInt(r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_MusicBeanRealmProxy r14 = (io.realm.com_ciot_realm_db_MusicBeanRealmProxy) r14
            goto L_0x0095
        L_0x008d:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'id'."
            r12.<init>(r13)
            throw r12
        L_0x0095:
            r12 = r14
            io.realm.com_ciot_realm_db_MusicBeanRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_MusicBeanRealmProxyInterface) r12
            java.lang.String r0 = "name"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00b1
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00aa
            r12.realmSet$name(r2)
            goto L_0x00b1
        L_0x00aa:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$name(r0)
        L_0x00b1:
            java.lang.String r0 = "path"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00ca
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00c3
            r12.realmSet$path(r2)
            goto L_0x00ca
        L_0x00c3:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$path(r0)
        L_0x00ca:
            java.lang.String r0 = "singer"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00e3
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00dc
            r12.realmSet$singer(r2)
            goto L_0x00e3
        L_0x00dc:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$singer(r0)
        L_0x00e3:
            java.lang.String r0 = "duration"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0101
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x00f9
            int r0 = r13.getInt(r0)
            r12.realmSet$duration(r0)
            goto L_0x0101
        L_0x00f9:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'duration' to null."
            r12.<init>(r13)
            throw r12
        L_0x0101:
            java.lang.String r0 = "size"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x011f
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0117
            long r0 = r13.getLong(r0)
            r12.realmSet$size(r0)
            goto L_0x011f
        L_0x0117:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'size' to null."
            r12.<init>(r13)
            throw r12
        L_0x011f:
            java.lang.String r0 = "isSelect"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x013d
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0135
            boolean r13 = r13.getBoolean(r0)
            r12.realmSet$isSelect(r13)
            goto L_0x013d
        L_0x0135:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'isSelect' to null."
            r12.<init>(r13)
            throw r12
        L_0x013d:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_MusicBeanRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.MusicBean");
    }

    public static MusicBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        MusicBean musicBean = new MusicBean();
        com_ciot_realm_db_MusicBeanRealmProxyInterface com_ciot_realm_db_musicbeanrealmproxyinterface = musicBean;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_musicbeanrealmproxyinterface.realmSet$id(jsonReader.nextInt());
                    z = true;
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                }
            } else if (nextName.equals(ServiceProvider.NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_musicbeanrealmproxyinterface.realmSet$name(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_musicbeanrealmproxyinterface.realmSet$name((String) null);
                }
            } else if (nextName.equals("path")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_musicbeanrealmproxyinterface.realmSet$path(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_musicbeanrealmproxyinterface.realmSet$path((String) null);
                }
            } else if (nextName.equals("singer")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_musicbeanrealmproxyinterface.realmSet$singer(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_musicbeanrealmproxyinterface.realmSet$singer((String) null);
                }
            } else if (nextName.equals("duration")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_musicbeanrealmproxyinterface.realmSet$duration(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'duration' to null.");
                }
            } else if (nextName.equals("size")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_musicbeanrealmproxyinterface.realmSet$size(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'size' to null.");
                }
            } else if (!nextName.equals("isSelect")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_musicbeanrealmproxyinterface.realmSet$isSelect(jsonReader.nextBoolean());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'isSelect' to null.");
            }
        }
        jsonReader.endObject();
        if (z) {
            return (MusicBean) realm.copyToRealm(musicBean, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_MusicBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) MusicBean.class), false, Collections.emptyList());
        com_ciot_realm_db_MusicBeanRealmProxy com_ciot_realm_db_musicbeanrealmproxy = new com_ciot_realm_db_MusicBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_musicbeanrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.MusicBean copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_MusicBeanRealmProxy.MusicBeanColumnInfo r9, com.ciot.realm.db.MusicBean r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.MusicBean r1 = (com.ciot.realm.db.MusicBean) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0093
            java.lang.Class<com.ciot.realm.db.MusicBean> r2 = com.ciot.realm.db.MusicBean.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_MusicBeanRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_MusicBeanRealmProxyInterface) r5
            int r5 = r5.realmGet$id()
            long r5 = (long) r5
            long r3 = r2.findFirstLong(r3, r5)
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0070
            r0 = 0
            goto L_0x0094
        L_0x0070:
            io.realm.internal.UncheckedRow r3 = r2.getUncheckedRow(r3)     // Catch:{ all -> 0x008e }
            r5 = 0
            java.util.List r6 = java.util.Collections.emptyList()     // Catch:{ all -> 0x008e }
            r1 = r0
            r2 = r8
            r4 = r9
            r1.set(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x008e }
            io.realm.com_ciot_realm_db_MusicBeanRealmProxy r1 = new io.realm.com_ciot_realm_db_MusicBeanRealmProxy     // Catch:{ all -> 0x008e }
            r1.<init>()     // Catch:{ all -> 0x008e }
            r2 = r1
            io.realm.internal.RealmObjectProxy r2 = (io.realm.internal.RealmObjectProxy) r2     // Catch:{ all -> 0x008e }
            r12.put(r10, r2)     // Catch:{ all -> 0x008e }
            r0.clear()
            goto L_0x0093
        L_0x008e:
            r8 = move-exception
            r0.clear()
            throw r8
        L_0x0093:
            r0 = r11
        L_0x0094:
            r3 = r1
            if (r0 == 0) goto L_0x00a1
            r1 = r8
            r2 = r9
            r4 = r10
            r5 = r12
            r6 = r13
            com.ciot.realm.db.MusicBean r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00a5
        L_0x00a1:
            com.ciot.realm.db.MusicBean r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00a5:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_MusicBeanRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_MusicBeanRealmProxy$MusicBeanColumnInfo, com.ciot.realm.db.MusicBean, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.MusicBean");
    }

    public static MusicBean copy(Realm realm, MusicBeanColumnInfo musicBeanColumnInfo, MusicBean musicBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(musicBean);
        if (realmObjectProxy != null) {
            return (MusicBean) realmObjectProxy;
        }
        com_ciot_realm_db_MusicBeanRealmProxyInterface com_ciot_realm_db_musicbeanrealmproxyinterface = musicBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(MusicBean.class), set);
        osObjectBuilder.addInteger(musicBeanColumnInfo.idColKey, Integer.valueOf(com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$id()));
        osObjectBuilder.addString(musicBeanColumnInfo.nameColKey, com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$name());
        osObjectBuilder.addString(musicBeanColumnInfo.pathColKey, com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$path());
        osObjectBuilder.addString(musicBeanColumnInfo.singerColKey, com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$singer());
        osObjectBuilder.addInteger(musicBeanColumnInfo.durationColKey, Integer.valueOf(com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$duration()));
        osObjectBuilder.addInteger(musicBeanColumnInfo.sizeColKey, Long.valueOf(com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$size()));
        osObjectBuilder.addBoolean(musicBeanColumnInfo.isSelectColKey, Boolean.valueOf(com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$isSelect()));
        com_ciot_realm_db_MusicBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(musicBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, MusicBean musicBean, Map<RealmModel, Long> map) {
        long j;
        MusicBean musicBean2 = musicBean;
        if ((musicBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(musicBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) musicBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(MusicBean.class);
        long nativePtr = table.getNativePtr();
        MusicBeanColumnInfo musicBeanColumnInfo = (MusicBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MusicBean.class);
        long j2 = musicBeanColumnInfo.idColKey;
        com_ciot_realm_db_MusicBeanRealmProxyInterface com_ciot_realm_db_musicbeanrealmproxyinterface = musicBean2;
        Integer valueOf = Integer.valueOf(com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$id());
        if (valueOf != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, (long) com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$id());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Integer.valueOf(com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$id()));
        } else {
            Table.throwDuplicatePrimaryKeyException(valueOf);
        }
        long j3 = j;
        map.put(musicBean2, Long.valueOf(j3));
        String realmGet$name = com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, musicBeanColumnInfo.nameColKey, j3, realmGet$name, false);
        }
        String realmGet$path = com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$path();
        if (realmGet$path != null) {
            Table.nativeSetString(nativePtr, musicBeanColumnInfo.pathColKey, j3, realmGet$path, false);
        }
        String realmGet$singer = com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$singer();
        if (realmGet$singer != null) {
            Table.nativeSetString(nativePtr, musicBeanColumnInfo.singerColKey, j3, realmGet$singer, false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetLong(j4, musicBeanColumnInfo.durationColKey, j5, (long) com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$duration(), false);
        Table.nativeSetLong(j4, musicBeanColumnInfo.sizeColKey, j5, com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$size(), false);
        Table.nativeSetBoolean(j4, musicBeanColumnInfo.isSelectColKey, j5, com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$isSelect(), false);
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(MusicBean.class);
        long nativePtr = table.getNativePtr();
        MusicBeanColumnInfo musicBeanColumnInfo = (MusicBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MusicBean.class);
        long j3 = musicBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            MusicBean musicBean = (MusicBean) it.next();
            if (!map2.containsKey(musicBean)) {
                if ((musicBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(musicBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) musicBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(musicBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_MusicBeanRealmProxyInterface com_ciot_realm_db_musicbeanrealmproxyinterface = musicBean;
                Integer valueOf = Integer.valueOf(com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$id());
                if (valueOf != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j3, (long) com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$id());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j3, Integer.valueOf(com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$id()));
                } else {
                    Table.throwDuplicatePrimaryKeyException(valueOf);
                }
                long j4 = j;
                map2.put(musicBean, Long.valueOf(j4));
                String realmGet$name = com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, musicBeanColumnInfo.nameColKey, j4, realmGet$name, false);
                } else {
                    j2 = j3;
                }
                String realmGet$path = com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$path();
                if (realmGet$path != null) {
                    Table.nativeSetString(nativePtr, musicBeanColumnInfo.pathColKey, j4, realmGet$path, false);
                }
                String realmGet$singer = com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$singer();
                if (realmGet$singer != null) {
                    Table.nativeSetString(nativePtr, musicBeanColumnInfo.singerColKey, j4, realmGet$singer, false);
                }
                long j5 = nativePtr;
                long j6 = j4;
                Table.nativeSetLong(j5, musicBeanColumnInfo.durationColKey, j6, (long) com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$duration(), false);
                Table.nativeSetLong(j5, musicBeanColumnInfo.sizeColKey, j6, com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$size(), false);
                Table.nativeSetBoolean(j5, musicBeanColumnInfo.isSelectColKey, j6, com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$isSelect(), false);
                j3 = j2;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, MusicBean musicBean, Map<RealmModel, Long> map) {
        long j;
        MusicBean musicBean2 = musicBean;
        if ((musicBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(musicBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) musicBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(MusicBean.class);
        long nativePtr = table.getNativePtr();
        MusicBeanColumnInfo musicBeanColumnInfo = (MusicBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MusicBean.class);
        long j2 = musicBeanColumnInfo.idColKey;
        com_ciot_realm_db_MusicBeanRealmProxyInterface com_ciot_realm_db_musicbeanrealmproxyinterface = musicBean2;
        if (Integer.valueOf(com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$id()) != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, (long) com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$id());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Integer.valueOf(com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$id()));
        }
        long j3 = j;
        map.put(musicBean2, Long.valueOf(j3));
        String realmGet$name = com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, musicBeanColumnInfo.nameColKey, j3, realmGet$name, false);
        } else {
            Table.nativeSetNull(nativePtr, musicBeanColumnInfo.nameColKey, j3, false);
        }
        String realmGet$path = com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$path();
        if (realmGet$path != null) {
            Table.nativeSetString(nativePtr, musicBeanColumnInfo.pathColKey, j3, realmGet$path, false);
        } else {
            Table.nativeSetNull(nativePtr, musicBeanColumnInfo.pathColKey, j3, false);
        }
        String realmGet$singer = com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$singer();
        if (realmGet$singer != null) {
            Table.nativeSetString(nativePtr, musicBeanColumnInfo.singerColKey, j3, realmGet$singer, false);
        } else {
            Table.nativeSetNull(nativePtr, musicBeanColumnInfo.singerColKey, j3, false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetLong(j4, musicBeanColumnInfo.durationColKey, j5, (long) com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$duration(), false);
        Table.nativeSetLong(j4, musicBeanColumnInfo.sizeColKey, j5, com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$size(), false);
        Table.nativeSetBoolean(j4, musicBeanColumnInfo.isSelectColKey, j5, com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$isSelect(), false);
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(MusicBean.class);
        long nativePtr = table.getNativePtr();
        MusicBeanColumnInfo musicBeanColumnInfo = (MusicBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MusicBean.class);
        long j3 = musicBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            MusicBean musicBean = (MusicBean) it.next();
            if (!map2.containsKey(musicBean)) {
                if ((musicBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(musicBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) musicBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(musicBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_MusicBeanRealmProxyInterface com_ciot_realm_db_musicbeanrealmproxyinterface = musicBean;
                if (Integer.valueOf(com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$id()) != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j3, (long) com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$id());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j3, Integer.valueOf(com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$id()));
                }
                long j4 = j;
                map2.put(musicBean, Long.valueOf(j4));
                String realmGet$name = com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, musicBeanColumnInfo.nameColKey, j4, realmGet$name, false);
                } else {
                    j2 = j3;
                    Table.nativeSetNull(nativePtr, musicBeanColumnInfo.nameColKey, j4, false);
                }
                String realmGet$path = com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$path();
                if (realmGet$path != null) {
                    Table.nativeSetString(nativePtr, musicBeanColumnInfo.pathColKey, j4, realmGet$path, false);
                } else {
                    Table.nativeSetNull(nativePtr, musicBeanColumnInfo.pathColKey, j4, false);
                }
                String realmGet$singer = com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$singer();
                if (realmGet$singer != null) {
                    Table.nativeSetString(nativePtr, musicBeanColumnInfo.singerColKey, j4, realmGet$singer, false);
                } else {
                    Table.nativeSetNull(nativePtr, musicBeanColumnInfo.singerColKey, j4, false);
                }
                long j5 = nativePtr;
                long j6 = j4;
                Table.nativeSetLong(j5, musicBeanColumnInfo.durationColKey, j6, (long) com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$duration(), false);
                Table.nativeSetLong(j5, musicBeanColumnInfo.sizeColKey, j6, com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$size(), false);
                Table.nativeSetBoolean(j5, musicBeanColumnInfo.isSelectColKey, j6, com_ciot_realm_db_musicbeanrealmproxyinterface.realmGet$isSelect(), false);
                j3 = j2;
            }
        }
    }

    public static MusicBean createDetachedCopy(MusicBean musicBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        MusicBean musicBean2;
        if (i > i2 || musicBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(musicBean);
        if (cacheData == null) {
            musicBean2 = new MusicBean();
            map.put(musicBean, new RealmObjectProxy.CacheData(i, musicBean2));
        } else if (i >= cacheData.minDepth) {
            return (MusicBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            musicBean2 = (MusicBean) cacheData.object;
        }
        com_ciot_realm_db_MusicBeanRealmProxyInterface com_ciot_realm_db_musicbeanrealmproxyinterface = musicBean2;
        com_ciot_realm_db_MusicBeanRealmProxyInterface com_ciot_realm_db_musicbeanrealmproxyinterface2 = musicBean;
        com_ciot_realm_db_musicbeanrealmproxyinterface.realmSet$id(com_ciot_realm_db_musicbeanrealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_musicbeanrealmproxyinterface.realmSet$name(com_ciot_realm_db_musicbeanrealmproxyinterface2.realmGet$name());
        com_ciot_realm_db_musicbeanrealmproxyinterface.realmSet$path(com_ciot_realm_db_musicbeanrealmproxyinterface2.realmGet$path());
        com_ciot_realm_db_musicbeanrealmproxyinterface.realmSet$singer(com_ciot_realm_db_musicbeanrealmproxyinterface2.realmGet$singer());
        com_ciot_realm_db_musicbeanrealmproxyinterface.realmSet$duration(com_ciot_realm_db_musicbeanrealmproxyinterface2.realmGet$duration());
        com_ciot_realm_db_musicbeanrealmproxyinterface.realmSet$size(com_ciot_realm_db_musicbeanrealmproxyinterface2.realmGet$size());
        com_ciot_realm_db_musicbeanrealmproxyinterface.realmSet$isSelect(com_ciot_realm_db_musicbeanrealmproxyinterface2.realmGet$isSelect());
        return musicBean2;
    }

    static MusicBean update(Realm realm, MusicBeanColumnInfo musicBeanColumnInfo, MusicBean musicBean, MusicBean musicBean2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_MusicBeanRealmProxyInterface com_ciot_realm_db_musicbeanrealmproxyinterface = musicBean;
        com_ciot_realm_db_MusicBeanRealmProxyInterface com_ciot_realm_db_musicbeanrealmproxyinterface2 = musicBean2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(MusicBean.class), set);
        osObjectBuilder.addInteger(musicBeanColumnInfo.idColKey, Integer.valueOf(com_ciot_realm_db_musicbeanrealmproxyinterface2.realmGet$id()));
        osObjectBuilder.addString(musicBeanColumnInfo.nameColKey, com_ciot_realm_db_musicbeanrealmproxyinterface2.realmGet$name());
        osObjectBuilder.addString(musicBeanColumnInfo.pathColKey, com_ciot_realm_db_musicbeanrealmproxyinterface2.realmGet$path());
        osObjectBuilder.addString(musicBeanColumnInfo.singerColKey, com_ciot_realm_db_musicbeanrealmproxyinterface2.realmGet$singer());
        osObjectBuilder.addInteger(musicBeanColumnInfo.durationColKey, Integer.valueOf(com_ciot_realm_db_musicbeanrealmproxyinterface2.realmGet$duration()));
        osObjectBuilder.addInteger(musicBeanColumnInfo.sizeColKey, Long.valueOf(com_ciot_realm_db_musicbeanrealmproxyinterface2.realmGet$size()));
        osObjectBuilder.addBoolean(musicBeanColumnInfo.isSelectColKey, Boolean.valueOf(com_ciot_realm_db_musicbeanrealmproxyinterface2.realmGet$isSelect()));
        osObjectBuilder.updateExistingObject();
        return musicBean;
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
        com_ciot_realm_db_MusicBeanRealmProxy com_ciot_realm_db_musicbeanrealmproxy = (com_ciot_realm_db_MusicBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_musicbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_musicbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_musicbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
