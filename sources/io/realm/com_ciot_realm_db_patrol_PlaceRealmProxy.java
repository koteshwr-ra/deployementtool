package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.patrol.Place;
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
import org.apache.log4j.spi.Configurator;

public class com_ciot_realm_db_patrol_PlaceRealmProxy extends Place implements RealmObjectProxy, com_ciot_realm_db_patrol_PlaceRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private PlaceColumnInfo columnInfo;
    private ProxyState<Place> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Place";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class PlaceColumnInfo extends ColumnInfo {
        long angleColKey;
        long mapInfoColKey;
        long positionNameColKey;
        long typeColKey;
        long xColKey;
        long yColKey;
        long zColKey;

        PlaceColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.angleColKey = addColumnDetails("angle", "angle", objectSchemaInfo);
            this.mapInfoColKey = addColumnDetails("mapInfo", "mapInfo", objectSchemaInfo);
            this.positionNameColKey = addColumnDetails("positionName", "positionName", objectSchemaInfo);
            this.typeColKey = addColumnDetails("type", "type", objectSchemaInfo);
            this.xColKey = addColumnDetails("x", "x", objectSchemaInfo);
            this.yColKey = addColumnDetails("y", "y", objectSchemaInfo);
            this.zColKey = addColumnDetails("z", "z", objectSchemaInfo);
        }

        PlaceColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new PlaceColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            PlaceColumnInfo placeColumnInfo = (PlaceColumnInfo) columnInfo;
            PlaceColumnInfo placeColumnInfo2 = (PlaceColumnInfo) columnInfo2;
            placeColumnInfo2.angleColKey = placeColumnInfo.angleColKey;
            placeColumnInfo2.mapInfoColKey = placeColumnInfo.mapInfoColKey;
            placeColumnInfo2.positionNameColKey = placeColumnInfo.positionNameColKey;
            placeColumnInfo2.typeColKey = placeColumnInfo.typeColKey;
            placeColumnInfo2.xColKey = placeColumnInfo.xColKey;
            placeColumnInfo2.yColKey = placeColumnInfo.yColKey;
            placeColumnInfo2.zColKey = placeColumnInfo.zColKey;
        }
    }

    com_ciot_realm_db_patrol_PlaceRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (PlaceColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<Place> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
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

    public int realmGet$type() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.typeColKey);
    }

    public void realmSet$type(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.typeColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.typeColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public float realmGet$x() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getFloat(this.columnInfo.xColKey);
    }

    public void realmSet$x(float f) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setFloat(this.columnInfo.xColKey, f);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setFloat(this.columnInfo.xColKey, row$realm.getObjectKey(), f, true);
        }
    }

    public float realmGet$y() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getFloat(this.columnInfo.yColKey);
    }

    public void realmSet$y(float f) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setFloat(this.columnInfo.yColKey, f);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setFloat(this.columnInfo.yColKey, row$realm.getObjectKey(), f, true);
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 7, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("angle", RealmFieldType.FLOAT, false, false, true);
        builder2.addPersistedProperty("mapInfo", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("positionName", RealmFieldType.STRING, true, false, false);
        builder2.addPersistedProperty("type", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("x", RealmFieldType.FLOAT, false, false, true);
        builder2.addPersistedProperty("y", RealmFieldType.FLOAT, false, false, true);
        builder2.addPersistedProperty("z", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static PlaceColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new PlaceColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v9, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v10, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0133  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.Place createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            r1 = 0
            java.lang.String r2 = "positionName"
            if (r14 == 0) goto L_0x0064
            java.lang.Class<com.ciot.realm.db.patrol.Place> r14 = com.ciot.realm.db.patrol.Place.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.patrol.Place> r4 = com.ciot.realm.db.patrol.Place.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy$PlaceColumnInfo r3 = (io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy.PlaceColumnInfo) r3
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
            java.lang.Class<com.ciot.realm.db.patrol.Place> r3 = com.ciot.realm.db.patrol.Place.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005f }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005f }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005f }
            io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy r14 = new io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy     // Catch:{ all -> 0x005f }
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
            java.lang.Class<com.ciot.realm.db.patrol.Place> r14 = com.ciot.realm.db.patrol.Place.class
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy r14 = (io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy) r14
            goto L_0x0094
        L_0x007e:
            java.lang.Class<com.ciot.realm.db.patrol.Place> r14 = com.ciot.realm.db.patrol.Place.class
            java.lang.String r2 = r13.getString(r2)
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy r14 = (io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy) r14
            goto L_0x0094
        L_0x008c:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'positionName'."
            r12.<init>(r13)
            throw r12
        L_0x0094:
            r12 = r14
            io.realm.com_ciot_realm_db_patrol_PlaceRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_patrol_PlaceRealmProxyInterface) r12
            java.lang.String r0 = "angle"
            boolean r2 = r13.has(r0)
            if (r2 == 0) goto L_0x00b6
            boolean r2 = r13.isNull(r0)
            if (r2 != 0) goto L_0x00ae
            double r2 = r13.getDouble(r0)
            float r0 = (float) r2
            r12.realmSet$angle(r0)
            goto L_0x00b6
        L_0x00ae:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'angle' to null."
            r12.<init>(r13)
            throw r12
        L_0x00b6:
            java.lang.String r0 = "mapInfo"
            boolean r2 = r13.has(r0)
            if (r2 == 0) goto L_0x00cf
            boolean r2 = r13.isNull(r0)
            if (r2 == 0) goto L_0x00c8
            r12.realmSet$mapInfo(r1)
            goto L_0x00cf
        L_0x00c8:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$mapInfo(r0)
        L_0x00cf:
            java.lang.String r0 = "type"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00ed
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x00e5
            int r0 = r13.getInt(r0)
            r12.realmSet$type(r0)
            goto L_0x00ed
        L_0x00e5:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'type' to null."
            r12.<init>(r13)
            throw r12
        L_0x00ed:
            java.lang.String r0 = "x"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x010c
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0104
            double r0 = r13.getDouble(r0)
            float r0 = (float) r0
            r12.realmSet$x(r0)
            goto L_0x010c
        L_0x0104:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'x' to null."
            r12.<init>(r13)
            throw r12
        L_0x010c:
            java.lang.String r0 = "y"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x012b
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0123
            double r0 = r13.getDouble(r0)
            float r0 = (float) r0
            r12.realmSet$y(r0)
            goto L_0x012b
        L_0x0123:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'y' to null."
            r12.<init>(r13)
            throw r12
        L_0x012b:
            java.lang.String r0 = "z"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0149
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0141
            int r13 = r13.getInt(r0)
            r12.realmSet$z(r13)
            goto L_0x0149
        L_0x0141:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'z' to null."
            r12.<init>(r13)
            throw r12
        L_0x0149:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.patrol.Place");
    }

    public static Place createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        Place place = new Place();
        com_ciot_realm_db_patrol_PlaceRealmProxyInterface com_ciot_realm_db_patrol_placerealmproxyinterface = place;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("angle")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_placerealmproxyinterface.realmSet$angle((float) jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'angle' to null.");
                }
            } else if (nextName.equals("mapInfo")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_placerealmproxyinterface.realmSet$mapInfo(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_placerealmproxyinterface.realmSet$mapInfo((String) null);
                }
            } else if (nextName.equals("positionName")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_placerealmproxyinterface.realmSet$positionName(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_placerealmproxyinterface.realmSet$positionName((String) null);
                }
                z = true;
            } else if (nextName.equals("type")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_placerealmproxyinterface.realmSet$type(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'type' to null.");
                }
            } else if (nextName.equals("x")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_placerealmproxyinterface.realmSet$x((float) jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'x' to null.");
                }
            } else if (nextName.equals("y")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_placerealmproxyinterface.realmSet$y((float) jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'y' to null.");
                }
            } else if (!nextName.equals("z")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_patrol_placerealmproxyinterface.realmSet$z(jsonReader.nextInt());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'z' to null.");
            }
        }
        jsonReader.endObject();
        if (z) {
            return (Place) realm.copyToRealm(place, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'positionName'.");
    }

    private static com_ciot_realm_db_patrol_PlaceRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) Place.class), false, Collections.emptyList());
        com_ciot_realm_db_patrol_PlaceRealmProxy com_ciot_realm_db_patrol_placerealmproxy = new com_ciot_realm_db_patrol_PlaceRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_patrol_placerealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.Place copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy.PlaceColumnInfo r9, com.ciot.realm.db.patrol.Place r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.patrol.Place r1 = (com.ciot.realm.db.patrol.Place) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.patrol.Place> r2 = com.ciot.realm.db.patrol.Place.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.positionNameColKey
            r5 = r10
            io.realm.com_ciot_realm_db_patrol_PlaceRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_patrol_PlaceRealmProxyInterface) r5
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
            io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy r1 = new io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.patrol.Place r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.patrol.Place r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy$PlaceColumnInfo, com.ciot.realm.db.patrol.Place, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.patrol.Place");
    }

    public static Place copy(Realm realm, PlaceColumnInfo placeColumnInfo, Place place, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(place);
        if (realmObjectProxy != null) {
            return (Place) realmObjectProxy;
        }
        com_ciot_realm_db_patrol_PlaceRealmProxyInterface com_ciot_realm_db_patrol_placerealmproxyinterface = place;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Place.class), set);
        osObjectBuilder.addFloat(placeColumnInfo.angleColKey, Float.valueOf(com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$angle()));
        osObjectBuilder.addString(placeColumnInfo.mapInfoColKey, com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$mapInfo());
        osObjectBuilder.addString(placeColumnInfo.positionNameColKey, com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$positionName());
        osObjectBuilder.addInteger(placeColumnInfo.typeColKey, Integer.valueOf(com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$type()));
        osObjectBuilder.addFloat(placeColumnInfo.xColKey, Float.valueOf(com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$x()));
        osObjectBuilder.addFloat(placeColumnInfo.yColKey, Float.valueOf(com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$y()));
        osObjectBuilder.addInteger(placeColumnInfo.zColKey, Integer.valueOf(com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$z()));
        com_ciot_realm_db_patrol_PlaceRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(place, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, Place place, Map<RealmModel, Long> map) {
        long j;
        Place place2 = place;
        if ((place2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(place)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) place2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Place.class);
        long nativePtr = table.getNativePtr();
        PlaceColumnInfo placeColumnInfo = (PlaceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Place.class);
        long j2 = placeColumnInfo.positionNameColKey;
        com_ciot_realm_db_patrol_PlaceRealmProxyInterface com_ciot_realm_db_patrol_placerealmproxyinterface = place2;
        String realmGet$positionName = com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$positionName();
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
        map.put(place2, Long.valueOf(j3));
        Table.nativeSetFloat(nativePtr, placeColumnInfo.angleColKey, j3, com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$angle(), false);
        String realmGet$mapInfo = com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$mapInfo();
        if (realmGet$mapInfo != null) {
            Table.nativeSetString(nativePtr, placeColumnInfo.mapInfoColKey, j3, realmGet$mapInfo, false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetLong(j4, placeColumnInfo.typeColKey, j5, (long) com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$type(), false);
        Table.nativeSetFloat(j4, placeColumnInfo.xColKey, j5, com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$x(), false);
        Table.nativeSetFloat(j4, placeColumnInfo.yColKey, j5, com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$y(), false);
        Table.nativeSetLong(j4, placeColumnInfo.zColKey, j5, (long) com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$z(), false);
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Place.class);
        long nativePtr = table.getNativePtr();
        PlaceColumnInfo placeColumnInfo = (PlaceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Place.class);
        long j3 = placeColumnInfo.positionNameColKey;
        while (it.hasNext()) {
            Place place = (Place) it.next();
            if (!map2.containsKey(place)) {
                if ((place instanceof RealmObjectProxy) && !RealmObject.isFrozen(place)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) place;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(place, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_PlaceRealmProxyInterface com_ciot_realm_db_patrol_placerealmproxyinterface = place;
                String realmGet$positionName = com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$positionName();
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
                map2.put(place, Long.valueOf(j2));
                long j4 = j3;
                Table.nativeSetFloat(nativePtr, placeColumnInfo.angleColKey, j2, com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$angle(), false);
                String realmGet$mapInfo = com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$mapInfo();
                if (realmGet$mapInfo != null) {
                    Table.nativeSetString(nativePtr, placeColumnInfo.mapInfoColKey, j2, realmGet$mapInfo, false);
                }
                long j5 = nativePtr;
                long j6 = j2;
                Table.nativeSetLong(j5, placeColumnInfo.typeColKey, j6, (long) com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$type(), false);
                Table.nativeSetFloat(j5, placeColumnInfo.xColKey, j6, com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$x(), false);
                Table.nativeSetFloat(j5, placeColumnInfo.yColKey, j6, com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$y(), false);
                Table.nativeSetLong(nativePtr, placeColumnInfo.zColKey, j6, (long) com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$z(), false);
                j3 = j4;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Place place, Map<RealmModel, Long> map) {
        long j;
        Place place2 = place;
        if ((place2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(place)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) place2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Place.class);
        long nativePtr = table.getNativePtr();
        PlaceColumnInfo placeColumnInfo = (PlaceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Place.class);
        long j2 = placeColumnInfo.positionNameColKey;
        com_ciot_realm_db_patrol_PlaceRealmProxyInterface com_ciot_realm_db_patrol_placerealmproxyinterface = place2;
        String realmGet$positionName = com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$positionName();
        if (realmGet$positionName == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$positionName);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$positionName);
        }
        long j3 = j;
        map.put(place2, Long.valueOf(j3));
        Table.nativeSetFloat(nativePtr, placeColumnInfo.angleColKey, j3, com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$angle(), false);
        String realmGet$mapInfo = com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$mapInfo();
        if (realmGet$mapInfo != null) {
            Table.nativeSetString(nativePtr, placeColumnInfo.mapInfoColKey, j3, realmGet$mapInfo, false);
        } else {
            Table.nativeSetNull(nativePtr, placeColumnInfo.mapInfoColKey, j3, false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetLong(j4, placeColumnInfo.typeColKey, j5, (long) com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$type(), false);
        Table.nativeSetFloat(j4, placeColumnInfo.xColKey, j5, com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$x(), false);
        Table.nativeSetFloat(j4, placeColumnInfo.yColKey, j5, com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$y(), false);
        Table.nativeSetLong(j4, placeColumnInfo.zColKey, j5, (long) com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$z(), false);
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Place.class);
        long nativePtr = table.getNativePtr();
        PlaceColumnInfo placeColumnInfo = (PlaceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Place.class);
        long j2 = placeColumnInfo.positionNameColKey;
        while (it.hasNext()) {
            Place place = (Place) it.next();
            if (!map2.containsKey(place)) {
                if ((place instanceof RealmObjectProxy) && !RealmObject.isFrozen(place)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) place;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(place, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_PlaceRealmProxyInterface com_ciot_realm_db_patrol_placerealmproxyinterface = place;
                String realmGet$positionName = com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$positionName();
                if (realmGet$positionName == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j2);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j2, realmGet$positionName);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j2, realmGet$positionName) : j;
                map2.put(place, Long.valueOf(createRowWithPrimaryKey));
                long j3 = j2;
                Table.nativeSetFloat(nativePtr, placeColumnInfo.angleColKey, createRowWithPrimaryKey, com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$angle(), false);
                String realmGet$mapInfo = com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$mapInfo();
                if (realmGet$mapInfo != null) {
                    Table.nativeSetString(nativePtr, placeColumnInfo.mapInfoColKey, createRowWithPrimaryKey, realmGet$mapInfo, false);
                } else {
                    Table.nativeSetNull(nativePtr, placeColumnInfo.mapInfoColKey, createRowWithPrimaryKey, false);
                }
                long j4 = nativePtr;
                long j5 = createRowWithPrimaryKey;
                Table.nativeSetLong(j4, placeColumnInfo.typeColKey, j5, (long) com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$type(), false);
                Table.nativeSetFloat(j4, placeColumnInfo.xColKey, j5, com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$x(), false);
                Table.nativeSetFloat(j4, placeColumnInfo.yColKey, j5, com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$y(), false);
                Table.nativeSetLong(nativePtr, placeColumnInfo.zColKey, j5, (long) com_ciot_realm_db_patrol_placerealmproxyinterface.realmGet$z(), false);
                j2 = j3;
            }
        }
    }

    public static Place createDetachedCopy(Place place, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        Place place2;
        if (i > i2 || place == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(place);
        if (cacheData == null) {
            place2 = new Place();
            map.put(place, new RealmObjectProxy.CacheData(i, place2));
        } else if (i >= cacheData.minDepth) {
            return (Place) cacheData.object;
        } else {
            cacheData.minDepth = i;
            place2 = (Place) cacheData.object;
        }
        com_ciot_realm_db_patrol_PlaceRealmProxyInterface com_ciot_realm_db_patrol_placerealmproxyinterface = place2;
        com_ciot_realm_db_patrol_PlaceRealmProxyInterface com_ciot_realm_db_patrol_placerealmproxyinterface2 = place;
        com_ciot_realm_db_patrol_placerealmproxyinterface.realmSet$angle(com_ciot_realm_db_patrol_placerealmproxyinterface2.realmGet$angle());
        com_ciot_realm_db_patrol_placerealmproxyinterface.realmSet$mapInfo(com_ciot_realm_db_patrol_placerealmproxyinterface2.realmGet$mapInfo());
        com_ciot_realm_db_patrol_placerealmproxyinterface.realmSet$positionName(com_ciot_realm_db_patrol_placerealmproxyinterface2.realmGet$positionName());
        com_ciot_realm_db_patrol_placerealmproxyinterface.realmSet$type(com_ciot_realm_db_patrol_placerealmproxyinterface2.realmGet$type());
        com_ciot_realm_db_patrol_placerealmproxyinterface.realmSet$x(com_ciot_realm_db_patrol_placerealmproxyinterface2.realmGet$x());
        com_ciot_realm_db_patrol_placerealmproxyinterface.realmSet$y(com_ciot_realm_db_patrol_placerealmproxyinterface2.realmGet$y());
        com_ciot_realm_db_patrol_placerealmproxyinterface.realmSet$z(com_ciot_realm_db_patrol_placerealmproxyinterface2.realmGet$z());
        return place2;
    }

    static Place update(Realm realm, PlaceColumnInfo placeColumnInfo, Place place, Place place2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_patrol_PlaceRealmProxyInterface com_ciot_realm_db_patrol_placerealmproxyinterface = place;
        com_ciot_realm_db_patrol_PlaceRealmProxyInterface com_ciot_realm_db_patrol_placerealmproxyinterface2 = place2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Place.class), set);
        osObjectBuilder.addFloat(placeColumnInfo.angleColKey, Float.valueOf(com_ciot_realm_db_patrol_placerealmproxyinterface2.realmGet$angle()));
        osObjectBuilder.addString(placeColumnInfo.mapInfoColKey, com_ciot_realm_db_patrol_placerealmproxyinterface2.realmGet$mapInfo());
        osObjectBuilder.addString(placeColumnInfo.positionNameColKey, com_ciot_realm_db_patrol_placerealmproxyinterface2.realmGet$positionName());
        osObjectBuilder.addInteger(placeColumnInfo.typeColKey, Integer.valueOf(com_ciot_realm_db_patrol_placerealmproxyinterface2.realmGet$type()));
        osObjectBuilder.addFloat(placeColumnInfo.xColKey, Float.valueOf(com_ciot_realm_db_patrol_placerealmproxyinterface2.realmGet$x()));
        osObjectBuilder.addFloat(placeColumnInfo.yColKey, Float.valueOf(com_ciot_realm_db_patrol_placerealmproxyinterface2.realmGet$y()));
        osObjectBuilder.addInteger(placeColumnInfo.zColKey, Integer.valueOf(com_ciot_realm_db_patrol_placerealmproxyinterface2.realmGet$z()));
        osObjectBuilder.updateExistingObject();
        return place;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("Place = proxy[");
        sb.append("{angle:");
        sb.append(realmGet$angle());
        sb.append("}");
        sb.append(",");
        sb.append("{mapInfo:");
        String realmGet$mapInfo = realmGet$mapInfo();
        String str = Configurator.NULL;
        sb.append(realmGet$mapInfo != null ? realmGet$mapInfo() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{positionName:");
        if (realmGet$positionName() != null) {
            str = realmGet$positionName();
        }
        sb.append(str);
        sb.append("}");
        sb.append(",");
        sb.append("{type:");
        sb.append(realmGet$type());
        sb.append("}");
        sb.append(",");
        sb.append("{x:");
        sb.append(realmGet$x());
        sb.append("}");
        sb.append(",");
        sb.append("{y:");
        sb.append(realmGet$y());
        sb.append("}");
        sb.append(",");
        sb.append("{z:");
        sb.append(realmGet$z());
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
        com_ciot_realm_db_patrol_PlaceRealmProxy com_ciot_realm_db_patrol_placerealmproxy = (com_ciot_realm_db_patrol_PlaceRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_patrol_placerealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_patrol_placerealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_patrol_placerealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
