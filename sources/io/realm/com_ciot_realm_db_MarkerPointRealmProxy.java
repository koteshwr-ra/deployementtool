package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.MarkerPoint;
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

public class com_ciot_realm_db_MarkerPointRealmProxy extends MarkerPoint implements RealmObjectProxy, com_ciot_realm_db_MarkerPointRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private MarkerPointColumnInfo columnInfo;
    private ProxyState<MarkerPoint> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "MarkerPoint";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class MarkerPointColumnInfo extends ColumnInfo {
        long angleColKey;
        long mapInfoColKey;
        long mapNameColKey;
        long positionNameColKey;
        long xColKey;
        long yColKey;
        long zColKey;

        MarkerPointColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.positionNameColKey = addColumnDetails("positionName", "positionName", objectSchemaInfo);
            this.xColKey = addColumnDetails("x", "x", objectSchemaInfo);
            this.yColKey = addColumnDetails("y", "y", objectSchemaInfo);
            this.zColKey = addColumnDetails("z", "z", objectSchemaInfo);
            this.angleColKey = addColumnDetails("angle", "angle", objectSchemaInfo);
            this.mapInfoColKey = addColumnDetails("mapInfo", "mapInfo", objectSchemaInfo);
            this.mapNameColKey = addColumnDetails("mapName", "mapName", objectSchemaInfo);
        }

        MarkerPointColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new MarkerPointColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            MarkerPointColumnInfo markerPointColumnInfo = (MarkerPointColumnInfo) columnInfo;
            MarkerPointColumnInfo markerPointColumnInfo2 = (MarkerPointColumnInfo) columnInfo2;
            markerPointColumnInfo2.positionNameColKey = markerPointColumnInfo.positionNameColKey;
            markerPointColumnInfo2.xColKey = markerPointColumnInfo.xColKey;
            markerPointColumnInfo2.yColKey = markerPointColumnInfo.yColKey;
            markerPointColumnInfo2.zColKey = markerPointColumnInfo.zColKey;
            markerPointColumnInfo2.angleColKey = markerPointColumnInfo.angleColKey;
            markerPointColumnInfo2.mapInfoColKey = markerPointColumnInfo.mapInfoColKey;
            markerPointColumnInfo2.mapNameColKey = markerPointColumnInfo.mapNameColKey;
        }
    }

    com_ciot_realm_db_MarkerPointRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (MarkerPointColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<MarkerPoint> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$positionName() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.positionNameColKey);
    }

    public void realmSet$positionName(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'positionName' cannot be changed after object was created.");
        }
    }

    public int realmGet$x() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.xColKey);
    }

    public void realmSet$x(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.xColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.xColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public int realmGet$y() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.yColKey);
    }

    public void realmSet$y(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.yColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.yColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public int realmGet$z() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.zColKey);
    }

    public void realmSet$z(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.zColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.zColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public float realmGet$angle() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getFloat(this.columnInfo.angleColKey);
    }

    public void realmSet$angle(float f) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setFloat(this.columnInfo.angleColKey, f);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setFloat(this.columnInfo.angleColKey, row$realm.getObjectKey(), f, true);
        }
    }

    public String realmGet$mapInfo() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.mapInfoColKey);
    }

    public void realmSet$mapInfo(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.mapInfoColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.mapInfoColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.mapInfoColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.mapInfoColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$mapName() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.mapNameColKey);
    }

    public void realmSet$mapName(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.mapNameColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.mapNameColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.mapNameColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.mapNameColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 7, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("positionName", RealmFieldType.STRING, true, false, false);
        builder2.addPersistedProperty("x", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("y", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("z", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("angle", RealmFieldType.FLOAT, false, false, true);
        builder2.addPersistedProperty("mapInfo", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("mapName", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static MarkerPointColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new MarkerPointColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v8, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v9, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0131  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.MarkerPoint createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            r1 = 0
            java.lang.String r2 = "positionName"
            if (r14 == 0) goto L_0x0064
            java.lang.Class<com.ciot.realm.db.MarkerPoint> r14 = com.ciot.realm.db.MarkerPoint.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.MarkerPoint> r4 = com.ciot.realm.db.MarkerPoint.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_MarkerPointRealmProxy$MarkerPointColumnInfo r3 = (io.realm.com_ciot_realm_db_MarkerPointRealmProxy.MarkerPointColumnInfo) r3
            long r3 = r3.positionNameColKey
            boolean r5 = r13.isNull(r2)
            if (r5 == 0) goto L_0x0028
            long r3 = r14.findFirstNull(r3)
            goto L_0x0030
        L_0x0028:
            java.lang.String r5 = r13.getString(r2)
            long r3 = r14.findFirstString(r3, r5)
        L_0x0030:
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0064
            io.realm.BaseRealm$ThreadLocalRealmObjectContext r5 = io.realm.BaseRealm.objectContext
            java.lang.Object r5 = r5.get()
            io.realm.BaseRealm$RealmObjectContext r5 = (io.realm.BaseRealm.RealmObjectContext) r5
            io.realm.internal.UncheckedRow r8 = r14.getUncheckedRow(r3)     // Catch:{ all -> 0x005f }
            io.realm.RealmSchema r14 = r12.getSchema()     // Catch:{ all -> 0x005f }
            java.lang.Class<com.ciot.realm.db.MarkerPoint> r3 = com.ciot.realm.db.MarkerPoint.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005f }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005f }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005f }
            io.realm.com_ciot_realm_db_MarkerPointRealmProxy r14 = new io.realm.com_ciot_realm_db_MarkerPointRealmProxy     // Catch:{ all -> 0x005f }
            r14.<init>()     // Catch:{ all -> 0x005f }
            r5.clear()
            goto L_0x0065
        L_0x005f:
            r12 = move-exception
            r5.clear()
            throw r12
        L_0x0064:
            r14 = r1
        L_0x0065:
            if (r14 != 0) goto L_0x0094
            boolean r14 = r13.has(r2)
            if (r14 == 0) goto L_0x008c
            boolean r14 = r13.isNull(r2)
            r3 = 1
            if (r14 == 0) goto L_0x007e
            java.lang.Class<com.ciot.realm.db.MarkerPoint> r14 = com.ciot.realm.db.MarkerPoint.class
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_MarkerPointRealmProxy r14 = (io.realm.com_ciot_realm_db_MarkerPointRealmProxy) r14
            goto L_0x0094
        L_0x007e:
            java.lang.Class<com.ciot.realm.db.MarkerPoint> r14 = com.ciot.realm.db.MarkerPoint.class
            java.lang.String r2 = r13.getString(r2)
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_MarkerPointRealmProxy r14 = (io.realm.com_ciot_realm_db_MarkerPointRealmProxy) r14
            goto L_0x0094
        L_0x008c:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'positionName'."
            r12.<init>(r13)
            throw r12
        L_0x0094:
            r12 = r14
            io.realm.com_ciot_realm_db_MarkerPointRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_MarkerPointRealmProxyInterface) r12
            java.lang.String r0 = "x"
            boolean r2 = r13.has(r0)
            if (r2 == 0) goto L_0x00b5
            boolean r2 = r13.isNull(r0)
            if (r2 != 0) goto L_0x00ad
            int r0 = r13.getInt(r0)
            r12.realmSet$x(r0)
            goto L_0x00b5
        L_0x00ad:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'x' to null."
            r12.<init>(r13)
            throw r12
        L_0x00b5:
            java.lang.String r0 = "y"
            boolean r2 = r13.has(r0)
            if (r2 == 0) goto L_0x00d3
            boolean r2 = r13.isNull(r0)
            if (r2 != 0) goto L_0x00cb
            int r0 = r13.getInt(r0)
            r12.realmSet$y(r0)
            goto L_0x00d3
        L_0x00cb:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'y' to null."
            r12.<init>(r13)
            throw r12
        L_0x00d3:
            java.lang.String r0 = "z"
            boolean r2 = r13.has(r0)
            if (r2 == 0) goto L_0x00f1
            boolean r2 = r13.isNull(r0)
            if (r2 != 0) goto L_0x00e9
            int r0 = r13.getInt(r0)
            r12.realmSet$z(r0)
            goto L_0x00f1
        L_0x00e9:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'z' to null."
            r12.<init>(r13)
            throw r12
        L_0x00f1:
            java.lang.String r0 = "angle"
            boolean r2 = r13.has(r0)
            if (r2 == 0) goto L_0x0110
            boolean r2 = r13.isNull(r0)
            if (r2 != 0) goto L_0x0108
            double r2 = r13.getDouble(r0)
            float r0 = (float) r2
            r12.realmSet$angle(r0)
            goto L_0x0110
        L_0x0108:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'angle' to null."
            r12.<init>(r13)
            throw r12
        L_0x0110:
            java.lang.String r0 = "mapInfo"
            boolean r2 = r13.has(r0)
            if (r2 == 0) goto L_0x0129
            boolean r2 = r13.isNull(r0)
            if (r2 == 0) goto L_0x0122
            r12.realmSet$mapInfo(r1)
            goto L_0x0129
        L_0x0122:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$mapInfo(r0)
        L_0x0129:
            java.lang.String r0 = "mapName"
            boolean r2 = r13.has(r0)
            if (r2 == 0) goto L_0x0142
            boolean r2 = r13.isNull(r0)
            if (r2 == 0) goto L_0x013b
            r12.realmSet$mapName(r1)
            goto L_0x0142
        L_0x013b:
            java.lang.String r13 = r13.getString(r0)
            r12.realmSet$mapName(r13)
        L_0x0142:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_MarkerPointRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.MarkerPoint");
    }

    public static MarkerPoint createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        MarkerPoint markerPoint = new MarkerPoint();
        com_ciot_realm_db_MarkerPointRealmProxyInterface com_ciot_realm_db_markerpointrealmproxyinterface = markerPoint;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("positionName")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_markerpointrealmproxyinterface.realmSet$positionName(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_markerpointrealmproxyinterface.realmSet$positionName((String) null);
                }
                z = true;
            } else if (nextName.equals("x")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_markerpointrealmproxyinterface.realmSet$x(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'x' to null.");
                }
            } else if (nextName.equals("y")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_markerpointrealmproxyinterface.realmSet$y(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'y' to null.");
                }
            } else if (nextName.equals("z")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_markerpointrealmproxyinterface.realmSet$z(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'z' to null.");
                }
            } else if (nextName.equals("angle")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_markerpointrealmproxyinterface.realmSet$angle((float) jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'angle' to null.");
                }
            } else if (nextName.equals("mapInfo")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_markerpointrealmproxyinterface.realmSet$mapInfo(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_markerpointrealmproxyinterface.realmSet$mapInfo((String) null);
                }
            } else if (!nextName.equals("mapName")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_markerpointrealmproxyinterface.realmSet$mapName(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_markerpointrealmproxyinterface.realmSet$mapName((String) null);
            }
        }
        jsonReader.endObject();
        if (z) {
            return (MarkerPoint) realm.copyToRealm(markerPoint, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'positionName'.");
    }

    private static com_ciot_realm_db_MarkerPointRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) MarkerPoint.class), false, Collections.emptyList());
        com_ciot_realm_db_MarkerPointRealmProxy com_ciot_realm_db_markerpointrealmproxy = new com_ciot_realm_db_MarkerPointRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_markerpointrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.MarkerPoint copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_MarkerPointRealmProxy.MarkerPointColumnInfo r9, com.ciot.realm.db.MarkerPoint r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.MarkerPoint r1 = (com.ciot.realm.db.MarkerPoint) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.MarkerPoint> r2 = com.ciot.realm.db.MarkerPoint.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.positionNameColKey
            r5 = r10
            io.realm.com_ciot_realm_db_MarkerPointRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_MarkerPointRealmProxyInterface) r5
            java.lang.String r5 = r5.realmGet$positionName()
            if (r5 != 0) goto L_0x006a
            long r3 = r2.findFirstNull(r3)
            goto L_0x006e
        L_0x006a:
            long r3 = r2.findFirstString(r3, r5)
        L_0x006e:
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0076
            r0 = 0
            goto L_0x009a
        L_0x0076:
            io.realm.internal.UncheckedRow r3 = r2.getUncheckedRow(r3)     // Catch:{ all -> 0x0094 }
            r5 = 0
            java.util.List r6 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0094 }
            r1 = r0
            r2 = r8
            r4 = r9
            r1.set(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0094 }
            io.realm.com_ciot_realm_db_MarkerPointRealmProxy r1 = new io.realm.com_ciot_realm_db_MarkerPointRealmProxy     // Catch:{ all -> 0x0094 }
            r1.<init>()     // Catch:{ all -> 0x0094 }
            r2 = r1
            io.realm.internal.RealmObjectProxy r2 = (io.realm.internal.RealmObjectProxy) r2     // Catch:{ all -> 0x0094 }
            r12.put(r10, r2)     // Catch:{ all -> 0x0094 }
            r0.clear()
            goto L_0x0099
        L_0x0094:
            r8 = move-exception
            r0.clear()
            throw r8
        L_0x0099:
            r0 = r11
        L_0x009a:
            r3 = r1
            if (r0 == 0) goto L_0x00a7
            r1 = r8
            r2 = r9
            r4 = r10
            r5 = r12
            r6 = r13
            com.ciot.realm.db.MarkerPoint r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.MarkerPoint r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_MarkerPointRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_MarkerPointRealmProxy$MarkerPointColumnInfo, com.ciot.realm.db.MarkerPoint, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.MarkerPoint");
    }

    public static MarkerPoint copy(Realm realm, MarkerPointColumnInfo markerPointColumnInfo, MarkerPoint markerPoint, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(markerPoint);
        if (realmObjectProxy != null) {
            return (MarkerPoint) realmObjectProxy;
        }
        com_ciot_realm_db_MarkerPointRealmProxyInterface com_ciot_realm_db_markerpointrealmproxyinterface = markerPoint;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(MarkerPoint.class), set);
        osObjectBuilder.addString(markerPointColumnInfo.positionNameColKey, com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$positionName());
        osObjectBuilder.addInteger(markerPointColumnInfo.xColKey, Integer.valueOf(com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$x()));
        osObjectBuilder.addInteger(markerPointColumnInfo.yColKey, Integer.valueOf(com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$y()));
        osObjectBuilder.addInteger(markerPointColumnInfo.zColKey, Integer.valueOf(com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$z()));
        osObjectBuilder.addFloat(markerPointColumnInfo.angleColKey, Float.valueOf(com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$angle()));
        osObjectBuilder.addString(markerPointColumnInfo.mapInfoColKey, com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$mapInfo());
        osObjectBuilder.addString(markerPointColumnInfo.mapNameColKey, com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$mapName());
        com_ciot_realm_db_MarkerPointRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(markerPoint, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, MarkerPoint markerPoint, Map<RealmModel, Long> map) {
        long j;
        MarkerPoint markerPoint2 = markerPoint;
        if ((markerPoint2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(markerPoint)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) markerPoint2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(MarkerPoint.class);
        long nativePtr = table.getNativePtr();
        MarkerPointColumnInfo markerPointColumnInfo = (MarkerPointColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MarkerPoint.class);
        long j2 = markerPointColumnInfo.positionNameColKey;
        com_ciot_realm_db_MarkerPointRealmProxyInterface com_ciot_realm_db_markerpointrealmproxyinterface = markerPoint2;
        String realmGet$positionName = com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$positionName();
        if (realmGet$positionName == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$positionName);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$positionName);
        } else {
            Table.throwDuplicatePrimaryKeyException(realmGet$positionName);
        }
        long j3 = j;
        map.put(markerPoint2, Long.valueOf(j3));
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetLong(j4, markerPointColumnInfo.xColKey, j5, (long) com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$x(), false);
        Table.nativeSetLong(j4, markerPointColumnInfo.yColKey, j5, (long) com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$y(), false);
        Table.nativeSetLong(j4, markerPointColumnInfo.zColKey, j5, (long) com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$z(), false);
        Table.nativeSetFloat(j4, markerPointColumnInfo.angleColKey, j5, com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$angle(), false);
        String realmGet$mapInfo = com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$mapInfo();
        if (realmGet$mapInfo != null) {
            Table.nativeSetString(nativePtr, markerPointColumnInfo.mapInfoColKey, j3, realmGet$mapInfo, false);
        }
        String realmGet$mapName = com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$mapName();
        if (realmGet$mapName != null) {
            Table.nativeSetString(nativePtr, markerPointColumnInfo.mapNameColKey, j3, realmGet$mapName, false);
        }
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(MarkerPoint.class);
        long nativePtr = table.getNativePtr();
        MarkerPointColumnInfo markerPointColumnInfo = (MarkerPointColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MarkerPoint.class);
        long j3 = markerPointColumnInfo.positionNameColKey;
        while (it.hasNext()) {
            MarkerPoint markerPoint = (MarkerPoint) it.next();
            if (!map2.containsKey(markerPoint)) {
                if ((markerPoint instanceof RealmObjectProxy) && !RealmObject.isFrozen(markerPoint)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) markerPoint;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(markerPoint, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_MarkerPointRealmProxyInterface com_ciot_realm_db_markerpointrealmproxyinterface = markerPoint;
                String realmGet$positionName = com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$positionName();
                if (realmGet$positionName == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j3);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j3, realmGet$positionName);
                }
                if (j == -1) {
                    j2 = OsObject.createRowWithPrimaryKey(table, j3, realmGet$positionName);
                } else {
                    Table.throwDuplicatePrimaryKeyException(realmGet$positionName);
                    j2 = j;
                }
                map2.put(markerPoint, Long.valueOf(j2));
                long j4 = markerPointColumnInfo.xColKey;
                long realmGet$x = (long) com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$x();
                long j5 = j2;
                long j6 = j3;
                Table.nativeSetLong(nativePtr, j4, j5, realmGet$x, false);
                Table.nativeSetLong(nativePtr, markerPointColumnInfo.yColKey, j5, (long) com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$y(), false);
                long j7 = nativePtr;
                Table.nativeSetLong(j7, markerPointColumnInfo.zColKey, j5, (long) com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$z(), false);
                Table.nativeSetFloat(j7, markerPointColumnInfo.angleColKey, j5, com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$angle(), false);
                String realmGet$mapInfo = com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$mapInfo();
                if (realmGet$mapInfo != null) {
                    Table.nativeSetString(nativePtr, markerPointColumnInfo.mapInfoColKey, j2, realmGet$mapInfo, false);
                }
                String realmGet$mapName = com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$mapName();
                if (realmGet$mapName != null) {
                    Table.nativeSetString(nativePtr, markerPointColumnInfo.mapNameColKey, j2, realmGet$mapName, false);
                }
                j3 = j6;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, MarkerPoint markerPoint, Map<RealmModel, Long> map) {
        long j;
        MarkerPoint markerPoint2 = markerPoint;
        if ((markerPoint2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(markerPoint)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) markerPoint2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(MarkerPoint.class);
        long nativePtr = table.getNativePtr();
        MarkerPointColumnInfo markerPointColumnInfo = (MarkerPointColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MarkerPoint.class);
        long j2 = markerPointColumnInfo.positionNameColKey;
        com_ciot_realm_db_MarkerPointRealmProxyInterface com_ciot_realm_db_markerpointrealmproxyinterface = markerPoint2;
        String realmGet$positionName = com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$positionName();
        if (realmGet$positionName == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$positionName);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$positionName);
        }
        long j3 = j;
        map.put(markerPoint2, Long.valueOf(j3));
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetLong(j4, markerPointColumnInfo.xColKey, j5, (long) com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$x(), false);
        Table.nativeSetLong(j4, markerPointColumnInfo.yColKey, j5, (long) com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$y(), false);
        Table.nativeSetLong(j4, markerPointColumnInfo.zColKey, j5, (long) com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$z(), false);
        Table.nativeSetFloat(j4, markerPointColumnInfo.angleColKey, j5, com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$angle(), false);
        String realmGet$mapInfo = com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$mapInfo();
        if (realmGet$mapInfo != null) {
            Table.nativeSetString(nativePtr, markerPointColumnInfo.mapInfoColKey, j3, realmGet$mapInfo, false);
        } else {
            Table.nativeSetNull(nativePtr, markerPointColumnInfo.mapInfoColKey, j3, false);
        }
        String realmGet$mapName = com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$mapName();
        if (realmGet$mapName != null) {
            Table.nativeSetString(nativePtr, markerPointColumnInfo.mapNameColKey, j3, realmGet$mapName, false);
        } else {
            Table.nativeSetNull(nativePtr, markerPointColumnInfo.mapNameColKey, j3, false);
        }
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(MarkerPoint.class);
        long nativePtr = table.getNativePtr();
        MarkerPointColumnInfo markerPointColumnInfo = (MarkerPointColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MarkerPoint.class);
        long j2 = markerPointColumnInfo.positionNameColKey;
        while (it.hasNext()) {
            MarkerPoint markerPoint = (MarkerPoint) it.next();
            if (!map2.containsKey(markerPoint)) {
                if ((markerPoint instanceof RealmObjectProxy) && !RealmObject.isFrozen(markerPoint)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) markerPoint;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(markerPoint, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_MarkerPointRealmProxyInterface com_ciot_realm_db_markerpointrealmproxyinterface = markerPoint;
                String realmGet$positionName = com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$positionName();
                if (realmGet$positionName == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j2);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j2, realmGet$positionName);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j2, realmGet$positionName) : j;
                map2.put(markerPoint, Long.valueOf(createRowWithPrimaryKey));
                long j3 = createRowWithPrimaryKey;
                long j4 = j2;
                Table.nativeSetLong(nativePtr, markerPointColumnInfo.xColKey, j3, (long) com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$x(), false);
                Table.nativeSetLong(nativePtr, markerPointColumnInfo.yColKey, j3, (long) com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$y(), false);
                long j5 = nativePtr;
                Table.nativeSetLong(j5, markerPointColumnInfo.zColKey, j3, (long) com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$z(), false);
                Table.nativeSetFloat(j5, markerPointColumnInfo.angleColKey, j3, com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$angle(), false);
                String realmGet$mapInfo = com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$mapInfo();
                if (realmGet$mapInfo != null) {
                    Table.nativeSetString(nativePtr, markerPointColumnInfo.mapInfoColKey, createRowWithPrimaryKey, realmGet$mapInfo, false);
                } else {
                    Table.nativeSetNull(nativePtr, markerPointColumnInfo.mapInfoColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$mapName = com_ciot_realm_db_markerpointrealmproxyinterface.realmGet$mapName();
                if (realmGet$mapName != null) {
                    Table.nativeSetString(nativePtr, markerPointColumnInfo.mapNameColKey, createRowWithPrimaryKey, realmGet$mapName, false);
                } else {
                    Table.nativeSetNull(nativePtr, markerPointColumnInfo.mapNameColKey, createRowWithPrimaryKey, false);
                }
                j2 = j4;
            }
        }
    }

    public static MarkerPoint createDetachedCopy(MarkerPoint markerPoint, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        MarkerPoint markerPoint2;
        if (i > i2 || markerPoint == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(markerPoint);
        if (cacheData == null) {
            markerPoint2 = new MarkerPoint();
            map.put(markerPoint, new RealmObjectProxy.CacheData(i, markerPoint2));
        } else if (i >= cacheData.minDepth) {
            return (MarkerPoint) cacheData.object;
        } else {
            cacheData.minDepth = i;
            markerPoint2 = (MarkerPoint) cacheData.object;
        }
        com_ciot_realm_db_MarkerPointRealmProxyInterface com_ciot_realm_db_markerpointrealmproxyinterface = markerPoint2;
        com_ciot_realm_db_MarkerPointRealmProxyInterface com_ciot_realm_db_markerpointrealmproxyinterface2 = markerPoint;
        com_ciot_realm_db_markerpointrealmproxyinterface.realmSet$positionName(com_ciot_realm_db_markerpointrealmproxyinterface2.realmGet$positionName());
        com_ciot_realm_db_markerpointrealmproxyinterface.realmSet$x(com_ciot_realm_db_markerpointrealmproxyinterface2.realmGet$x());
        com_ciot_realm_db_markerpointrealmproxyinterface.realmSet$y(com_ciot_realm_db_markerpointrealmproxyinterface2.realmGet$y());
        com_ciot_realm_db_markerpointrealmproxyinterface.realmSet$z(com_ciot_realm_db_markerpointrealmproxyinterface2.realmGet$z());
        com_ciot_realm_db_markerpointrealmproxyinterface.realmSet$angle(com_ciot_realm_db_markerpointrealmproxyinterface2.realmGet$angle());
        com_ciot_realm_db_markerpointrealmproxyinterface.realmSet$mapInfo(com_ciot_realm_db_markerpointrealmproxyinterface2.realmGet$mapInfo());
        com_ciot_realm_db_markerpointrealmproxyinterface.realmSet$mapName(com_ciot_realm_db_markerpointrealmproxyinterface2.realmGet$mapName());
        return markerPoint2;
    }

    static MarkerPoint update(Realm realm, MarkerPointColumnInfo markerPointColumnInfo, MarkerPoint markerPoint, MarkerPoint markerPoint2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_MarkerPointRealmProxyInterface com_ciot_realm_db_markerpointrealmproxyinterface = markerPoint;
        com_ciot_realm_db_MarkerPointRealmProxyInterface com_ciot_realm_db_markerpointrealmproxyinterface2 = markerPoint2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(MarkerPoint.class), set);
        osObjectBuilder.addString(markerPointColumnInfo.positionNameColKey, com_ciot_realm_db_markerpointrealmproxyinterface2.realmGet$positionName());
        osObjectBuilder.addInteger(markerPointColumnInfo.xColKey, Integer.valueOf(com_ciot_realm_db_markerpointrealmproxyinterface2.realmGet$x()));
        osObjectBuilder.addInteger(markerPointColumnInfo.yColKey, Integer.valueOf(com_ciot_realm_db_markerpointrealmproxyinterface2.realmGet$y()));
        osObjectBuilder.addInteger(markerPointColumnInfo.zColKey, Integer.valueOf(com_ciot_realm_db_markerpointrealmproxyinterface2.realmGet$z()));
        osObjectBuilder.addFloat(markerPointColumnInfo.angleColKey, Float.valueOf(com_ciot_realm_db_markerpointrealmproxyinterface2.realmGet$angle()));
        osObjectBuilder.addString(markerPointColumnInfo.mapInfoColKey, com_ciot_realm_db_markerpointrealmproxyinterface2.realmGet$mapInfo());
        osObjectBuilder.addString(markerPointColumnInfo.mapNameColKey, com_ciot_realm_db_markerpointrealmproxyinterface2.realmGet$mapName());
        osObjectBuilder.updateExistingObject();
        return markerPoint;
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
        com_ciot_realm_db_MarkerPointRealmProxy com_ciot_realm_db_markerpointrealmproxy = (com_ciot_realm_db_MarkerPointRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_markerpointrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_markerpointrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_markerpointrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
