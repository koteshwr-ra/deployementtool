package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.PointF;
import com.ciot.realm.db.patrol.TurnstileBean;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_PointFRealmProxy;
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
import mc.csst.com.selfchassis.ui.fragment.set.schedule.ScheduleFragment;
import org.apache.log4j.spi.Configurator;

public class com_ciot_realm_db_patrol_TurnstileBeanRealmProxy extends TurnstileBean implements RealmObjectProxy, com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private RealmList<PointF> areaRealmList;
    private TurnstileBeanColumnInfo columnInfo;
    private ProxyState<TurnstileBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "TurnstileBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class TurnstileBeanColumnInfo extends ColumnInfo {
        long areaColKey;
        long floorColKey;
        long idColKey;
        long inAngleColKey;
        long inPointColKey;
        long outAngleColKey;
        long outPointColKey;
        long turnstileHostColKey;
        long turnstileIdColKey;
        long turnstilePortColKey;
        long turnstileTypeColKey;

        TurnstileBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(11);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.areaColKey = addColumnDetails("area", "area", objectSchemaInfo);
            this.floorColKey = addColumnDetails(ScheduleFragment.FLOOR, ScheduleFragment.FLOOR, objectSchemaInfo);
            this.inPointColKey = addColumnDetails("inPoint", "inPoint", objectSchemaInfo);
            this.inAngleColKey = addColumnDetails("inAngle", "inAngle", objectSchemaInfo);
            this.outPointColKey = addColumnDetails("outPoint", "outPoint", objectSchemaInfo);
            this.outAngleColKey = addColumnDetails("outAngle", "outAngle", objectSchemaInfo);
            this.turnstileHostColKey = addColumnDetails("turnstileHost", "turnstileHost", objectSchemaInfo);
            this.turnstileIdColKey = addColumnDetails("turnstileId", "turnstileId", objectSchemaInfo);
            this.turnstilePortColKey = addColumnDetails("turnstilePort", "turnstilePort", objectSchemaInfo);
            this.turnstileTypeColKey = addColumnDetails("turnstileType", "turnstileType", objectSchemaInfo);
        }

        TurnstileBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new TurnstileBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            TurnstileBeanColumnInfo turnstileBeanColumnInfo = (TurnstileBeanColumnInfo) columnInfo;
            TurnstileBeanColumnInfo turnstileBeanColumnInfo2 = (TurnstileBeanColumnInfo) columnInfo2;
            turnstileBeanColumnInfo2.idColKey = turnstileBeanColumnInfo.idColKey;
            turnstileBeanColumnInfo2.areaColKey = turnstileBeanColumnInfo.areaColKey;
            turnstileBeanColumnInfo2.floorColKey = turnstileBeanColumnInfo.floorColKey;
            turnstileBeanColumnInfo2.inPointColKey = turnstileBeanColumnInfo.inPointColKey;
            turnstileBeanColumnInfo2.inAngleColKey = turnstileBeanColumnInfo.inAngleColKey;
            turnstileBeanColumnInfo2.outPointColKey = turnstileBeanColumnInfo.outPointColKey;
            turnstileBeanColumnInfo2.outAngleColKey = turnstileBeanColumnInfo.outAngleColKey;
            turnstileBeanColumnInfo2.turnstileHostColKey = turnstileBeanColumnInfo.turnstileHostColKey;
            turnstileBeanColumnInfo2.turnstileIdColKey = turnstileBeanColumnInfo.turnstileIdColKey;
            turnstileBeanColumnInfo2.turnstilePortColKey = turnstileBeanColumnInfo.turnstilePortColKey;
            turnstileBeanColumnInfo2.turnstileTypeColKey = turnstileBeanColumnInfo.turnstileTypeColKey;
        }
    }

    com_ciot_realm_db_patrol_TurnstileBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (TurnstileBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<TurnstileBean> proxyState2 = new ProxyState<>(this);
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
            throw new RealmException("Primary key field 'id' cannot be changed after object was created.");
        }
    }

    public RealmList<PointF> realmGet$area() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<PointF> realmList = this.areaRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<PointF> realmList2 = new RealmList<>(PointF.class, this.proxyState.getRow$realm().getModelList(this.columnInfo.areaColKey), this.proxyState.getRealm$realm());
        this.areaRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$area(RealmList<PointF> realmList) {
        int i = 0;
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("area")) {
                return;
            }
            if (realmList != null && !realmList.isManaged()) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<PointF> realmList2 = new RealmList<>();
                Iterator<PointF> it = realmList.iterator();
                while (it.hasNext()) {
                    PointF next = it.next();
                    if (next == null || RealmObject.isManaged(next)) {
                        realmList2.add(next);
                    } else {
                        realmList2.add((PointF) realm.copyToRealm(next, new ImportFlag[0]));
                    }
                }
                realmList = realmList2;
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList modelList = this.proxyState.getRow$realm().getModelList(this.columnInfo.areaColKey);
        if (realmList == null || ((long) realmList.size()) != modelList.size()) {
            modelList.removeAll();
            if (realmList != null) {
                int size = realmList.size();
                while (i < size) {
                    PointF pointF = realmList.get(i);
                    this.proxyState.checkValidObject(pointF);
                    modelList.addRow(((RealmObjectProxy) pointF).realmGet$proxyState().getRow$realm().getObjectKey());
                    i++;
                }
                return;
            }
            return;
        }
        int size2 = realmList.size();
        while (i < size2) {
            PointF pointF2 = realmList.get(i);
            this.proxyState.checkValidObject(pointF2);
            modelList.setRow((long) i, ((RealmObjectProxy) pointF2).realmGet$proxyState().getRow$realm().getObjectKey());
            i++;
        }
    }

    public int realmGet$floor() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.floorColKey);
    }

    public void realmSet$floor(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.floorColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.floorColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public PointF realmGet$inPoint() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.inPointColKey)) {
            return null;
        }
        return (PointF) this.proxyState.getRealm$realm().get(PointF.class, this.proxyState.getRow$realm().getLink(this.columnInfo.inPointColKey), false, Collections.emptyList());
    }

    public void realmSet$inPoint(PointF pointF) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (pointF == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.inPointColKey);
                return;
            }
            this.proxyState.checkValidObject(pointF);
            this.proxyState.getRow$realm().setLink(this.columnInfo.inPointColKey, ((RealmObjectProxy) pointF).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("inPoint")) {
            if (pointF != null && !RealmObject.isManaged(pointF)) {
                pointF = (PointF) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(pointF, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (pointF == null) {
                row$realm.nullifyLink(this.columnInfo.inPointColKey);
                return;
            }
            this.proxyState.checkValidObject(pointF);
            row$realm.getTable().setLink(this.columnInfo.inPointColKey, row$realm.getObjectKey(), ((RealmObjectProxy) pointF).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public float realmGet$inAngle() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getFloat(this.columnInfo.inAngleColKey);
    }

    public void realmSet$inAngle(float f) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setFloat(this.columnInfo.inAngleColKey, f);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setFloat(this.columnInfo.inAngleColKey, row$realm.getObjectKey(), f, true);
        }
    }

    public PointF realmGet$outPoint() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.outPointColKey)) {
            return null;
        }
        return (PointF) this.proxyState.getRealm$realm().get(PointF.class, this.proxyState.getRow$realm().getLink(this.columnInfo.outPointColKey), false, Collections.emptyList());
    }

    public void realmSet$outPoint(PointF pointF) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (pointF == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.outPointColKey);
                return;
            }
            this.proxyState.checkValidObject(pointF);
            this.proxyState.getRow$realm().setLink(this.columnInfo.outPointColKey, ((RealmObjectProxy) pointF).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("outPoint")) {
            if (pointF != null && !RealmObject.isManaged(pointF)) {
                pointF = (PointF) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(pointF, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (pointF == null) {
                row$realm.nullifyLink(this.columnInfo.outPointColKey);
                return;
            }
            this.proxyState.checkValidObject(pointF);
            row$realm.getTable().setLink(this.columnInfo.outPointColKey, row$realm.getObjectKey(), ((RealmObjectProxy) pointF).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public float realmGet$outAngle() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getFloat(this.columnInfo.outAngleColKey);
    }

    public void realmSet$outAngle(float f) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setFloat(this.columnInfo.outAngleColKey, f);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setFloat(this.columnInfo.outAngleColKey, row$realm.getObjectKey(), f, true);
        }
    }

    public String realmGet$turnstileHost() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.turnstileHostColKey);
    }

    public void realmSet$turnstileHost(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.turnstileHostColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.turnstileHostColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.turnstileHostColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.turnstileHostColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$turnstileId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.turnstileIdColKey);
    }

    public void realmSet$turnstileId(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.turnstileIdColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.turnstileIdColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.turnstileIdColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.turnstileIdColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public int realmGet$turnstilePort() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.turnstilePortColKey);
    }

    public void realmSet$turnstilePort(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.turnstilePortColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.turnstilePortColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public int realmGet$turnstileType() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.turnstileTypeColKey);
    }

    public void realmSet$turnstileType(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.turnstileTypeColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.turnstileTypeColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 11, 0);
        builder.addPersistedProperty("id", RealmFieldType.STRING, true, false, false);
        builder.addPersistedLinkProperty("area", RealmFieldType.LIST, com_ciot_realm_db_PointFRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedProperty(ScheduleFragment.FLOOR, RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedLinkProperty("inPoint", RealmFieldType.OBJECT, com_ciot_realm_db_PointFRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedProperty("inAngle", RealmFieldType.FLOAT, false, false, true);
        builder.addPersistedLinkProperty("outPoint", RealmFieldType.OBJECT, com_ciot_realm_db_PointFRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("outAngle", RealmFieldType.FLOAT, false, false, true);
        builder2.addPersistedProperty("turnstileHost", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("turnstileId", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("turnstilePort", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("turnstileType", RealmFieldType.INTEGER, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TurnstileBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new TurnstileBeanColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r0v4, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0188  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01a1  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01ba  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01d8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.TurnstileBean createOrUpdateUsingJsonObject(io.realm.Realm r13, org.json.JSONObject r14, boolean r15) throws org.json.JSONException {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 3
            r0.<init>(r1)
            java.lang.String r1 = "id"
            r2 = 0
            if (r15 == 0) goto L_0x0066
            java.lang.Class<com.ciot.realm.db.patrol.TurnstileBean> r3 = com.ciot.realm.db.patrol.TurnstileBean.class
            io.realm.internal.Table r3 = r13.getTable(r3)
            io.realm.RealmSchema r4 = r13.getSchema()
            java.lang.Class<com.ciot.realm.db.patrol.TurnstileBean> r5 = com.ciot.realm.db.patrol.TurnstileBean.class
            io.realm.internal.ColumnInfo r4 = r4.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r5)
            io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxy$TurnstileBeanColumnInfo r4 = (io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.TurnstileBeanColumnInfo) r4
            long r4 = r4.idColKey
            boolean r6 = r14.isNull(r1)
            if (r6 == 0) goto L_0x002a
            long r4 = r3.findFirstNull(r4)
            goto L_0x0032
        L_0x002a:
            java.lang.String r6 = r14.getString(r1)
            long r4 = r3.findFirstString(r4, r6)
        L_0x0032:
            r6 = -1
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x0066
            io.realm.BaseRealm$ThreadLocalRealmObjectContext r6 = io.realm.BaseRealm.objectContext
            java.lang.Object r6 = r6.get()
            io.realm.BaseRealm$RealmObjectContext r6 = (io.realm.BaseRealm.RealmObjectContext) r6
            io.realm.internal.UncheckedRow r9 = r3.getUncheckedRow(r4)     // Catch:{ all -> 0x0061 }
            io.realm.RealmSchema r3 = r13.getSchema()     // Catch:{ all -> 0x0061 }
            java.lang.Class<com.ciot.realm.db.patrol.TurnstileBean> r4 = com.ciot.realm.db.patrol.TurnstileBean.class
            io.realm.internal.ColumnInfo r10 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)     // Catch:{ all -> 0x0061 }
            r11 = 0
            java.util.List r12 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0061 }
            r7 = r6
            r8 = r13
            r7.set(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0061 }
            io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxy r3 = new io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxy     // Catch:{ all -> 0x0061 }
            r3.<init>()     // Catch:{ all -> 0x0061 }
            r6.clear()
            goto L_0x0067
        L_0x0061:
            r13 = move-exception
            r6.clear()
            throw r13
        L_0x0066:
            r3 = r2
        L_0x0067:
            java.lang.String r4 = "outPoint"
            java.lang.String r5 = "inPoint"
            java.lang.String r6 = "area"
            if (r3 != 0) goto L_0x00b7
            boolean r3 = r14.has(r6)
            if (r3 == 0) goto L_0x0078
            r0.add(r6)
        L_0x0078:
            boolean r3 = r14.has(r5)
            if (r3 == 0) goto L_0x0081
            r0.add(r5)
        L_0x0081:
            boolean r3 = r14.has(r4)
            if (r3 == 0) goto L_0x008a
            r0.add(r4)
        L_0x008a:
            boolean r3 = r14.has(r1)
            if (r3 == 0) goto L_0x00af
            boolean r3 = r14.isNull(r1)
            r7 = 1
            if (r3 == 0) goto L_0x00a1
            java.lang.Class<com.ciot.realm.db.patrol.TurnstileBean> r1 = com.ciot.realm.db.patrol.TurnstileBean.class
            io.realm.RealmModel r0 = r13.createObjectInternal(r1, r2, r7, r0)
            r3 = r0
            io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxy r3 = (io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxy) r3
            goto L_0x00b7
        L_0x00a1:
            java.lang.Class<com.ciot.realm.db.patrol.TurnstileBean> r3 = com.ciot.realm.db.patrol.TurnstileBean.class
            java.lang.String r1 = r14.getString(r1)
            io.realm.RealmModel r0 = r13.createObjectInternal(r3, r1, r7, r0)
            r3 = r0
            io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxy r3 = (io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxy) r3
            goto L_0x00b7
        L_0x00af:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "JSON object doesn't have the primary key field 'id'."
            r13.<init>(r14)
            throw r13
        L_0x00b7:
            r0 = r3
            io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface r0 = (io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface) r0
            boolean r1 = r14.has(r6)
            if (r1 == 0) goto L_0x00ee
            boolean r1 = r14.isNull(r6)
            if (r1 == 0) goto L_0x00ca
            r0.realmSet$area(r2)
            goto L_0x00ee
        L_0x00ca:
            io.realm.RealmList r1 = r0.realmGet$area()
            r1.clear()
            org.json.JSONArray r1 = r14.getJSONArray(r6)
            r6 = 0
        L_0x00d6:
            int r7 = r1.length()
            if (r6 >= r7) goto L_0x00ee
            org.json.JSONObject r7 = r1.getJSONObject(r6)
            com.ciot.realm.db.PointF r7 = io.realm.com_ciot_realm_db_PointFRealmProxy.createOrUpdateUsingJsonObject(r13, r7, r15)
            io.realm.RealmList r8 = r0.realmGet$area()
            r8.add(r7)
            int r6 = r6 + 1
            goto L_0x00d6
        L_0x00ee:
            java.lang.String r1 = "floor"
            boolean r6 = r14.has(r1)
            if (r6 == 0) goto L_0x010c
            boolean r6 = r14.isNull(r1)
            if (r6 != 0) goto L_0x0104
            int r1 = r14.getInt(r1)
            r0.realmSet$floor(r1)
            goto L_0x010c
        L_0x0104:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'floor' to null."
            r13.<init>(r14)
            throw r13
        L_0x010c:
            boolean r1 = r14.has(r5)
            if (r1 == 0) goto L_0x0127
            boolean r1 = r14.isNull(r5)
            if (r1 == 0) goto L_0x011c
            r0.realmSet$inPoint(r2)
            goto L_0x0127
        L_0x011c:
            org.json.JSONObject r1 = r14.getJSONObject(r5)
            com.ciot.realm.db.PointF r1 = io.realm.com_ciot_realm_db_PointFRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$inPoint(r1)
        L_0x0127:
            java.lang.String r1 = "inAngle"
            boolean r5 = r14.has(r1)
            if (r5 == 0) goto L_0x0146
            boolean r5 = r14.isNull(r1)
            if (r5 != 0) goto L_0x013e
            double r5 = r14.getDouble(r1)
            float r1 = (float) r5
            r0.realmSet$inAngle(r1)
            goto L_0x0146
        L_0x013e:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'inAngle' to null."
            r13.<init>(r14)
            throw r13
        L_0x0146:
            boolean r1 = r14.has(r4)
            if (r1 == 0) goto L_0x0161
            boolean r1 = r14.isNull(r4)
            if (r1 == 0) goto L_0x0156
            r0.realmSet$outPoint(r2)
            goto L_0x0161
        L_0x0156:
            org.json.JSONObject r1 = r14.getJSONObject(r4)
            com.ciot.realm.db.PointF r13 = io.realm.com_ciot_realm_db_PointFRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$outPoint(r13)
        L_0x0161:
            java.lang.String r13 = "outAngle"
            boolean r15 = r14.has(r13)
            if (r15 == 0) goto L_0x0180
            boolean r15 = r14.isNull(r13)
            if (r15 != 0) goto L_0x0178
            double r4 = r14.getDouble(r13)
            float r13 = (float) r4
            r0.realmSet$outAngle(r13)
            goto L_0x0180
        L_0x0178:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'outAngle' to null."
            r13.<init>(r14)
            throw r13
        L_0x0180:
            java.lang.String r13 = "turnstileHost"
            boolean r15 = r14.has(r13)
            if (r15 == 0) goto L_0x0199
            boolean r15 = r14.isNull(r13)
            if (r15 == 0) goto L_0x0192
            r0.realmSet$turnstileHost(r2)
            goto L_0x0199
        L_0x0192:
            java.lang.String r13 = r14.getString(r13)
            r0.realmSet$turnstileHost(r13)
        L_0x0199:
            java.lang.String r13 = "turnstileId"
            boolean r15 = r14.has(r13)
            if (r15 == 0) goto L_0x01b2
            boolean r15 = r14.isNull(r13)
            if (r15 == 0) goto L_0x01ab
            r0.realmSet$turnstileId(r2)
            goto L_0x01b2
        L_0x01ab:
            java.lang.String r13 = r14.getString(r13)
            r0.realmSet$turnstileId(r13)
        L_0x01b2:
            java.lang.String r13 = "turnstilePort"
            boolean r15 = r14.has(r13)
            if (r15 == 0) goto L_0x01d0
            boolean r15 = r14.isNull(r13)
            if (r15 != 0) goto L_0x01c8
            int r13 = r14.getInt(r13)
            r0.realmSet$turnstilePort(r13)
            goto L_0x01d0
        L_0x01c8:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'turnstilePort' to null."
            r13.<init>(r14)
            throw r13
        L_0x01d0:
            java.lang.String r13 = "turnstileType"
            boolean r15 = r14.has(r13)
            if (r15 == 0) goto L_0x01ee
            boolean r15 = r14.isNull(r13)
            if (r15 != 0) goto L_0x01e6
            int r13 = r14.getInt(r13)
            r0.realmSet$turnstileType(r13)
            goto L_0x01ee
        L_0x01e6:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'turnstileType' to null."
            r13.<init>(r14)
            throw r13
        L_0x01ee:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.patrol.TurnstileBean");
    }

    public static TurnstileBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        TurnstileBean turnstileBean = new TurnstileBean();
        com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface = turnstileBean;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$id((String) null);
                }
                z = true;
            } else if (nextName.equals("area")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$area((RealmList<PointF>) null);
                } else {
                    com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$area(new RealmList());
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$area().add(com_ciot_realm_db_PointFRealmProxy.createUsingJsonStream(realm, jsonReader));
                    }
                    jsonReader.endArray();
                }
            } else if (nextName.equals(ScheduleFragment.FLOOR)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$floor(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'floor' to null.");
                }
            } else if (nextName.equals("inPoint")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$inPoint((PointF) null);
                } else {
                    com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$inPoint(com_ciot_realm_db_PointFRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("inAngle")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$inAngle((float) jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'inAngle' to null.");
                }
            } else if (nextName.equals("outPoint")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$outPoint((PointF) null);
                } else {
                    com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$outPoint(com_ciot_realm_db_PointFRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("outAngle")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$outAngle((float) jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'outAngle' to null.");
                }
            } else if (nextName.equals("turnstileHost")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$turnstileHost(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$turnstileHost((String) null);
                }
            } else if (nextName.equals("turnstileId")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$turnstileId(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$turnstileId((String) null);
                }
            } else if (nextName.equals("turnstilePort")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$turnstilePort(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'turnstilePort' to null.");
                }
            } else if (!nextName.equals("turnstileType")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$turnstileType(jsonReader.nextInt());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'turnstileType' to null.");
            }
        }
        jsonReader.endObject();
        if (z) {
            return (TurnstileBean) realm.copyToRealm(turnstileBean, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_patrol_TurnstileBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) TurnstileBean.class), false, Collections.emptyList());
        com_ciot_realm_db_patrol_TurnstileBeanRealmProxy com_ciot_realm_db_patrol_turnstilebeanrealmproxy = new com_ciot_realm_db_patrol_TurnstileBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_patrol_turnstilebeanrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.TurnstileBean copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.TurnstileBeanColumnInfo r9, com.ciot.realm.db.patrol.TurnstileBean r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.patrol.TurnstileBean r1 = (com.ciot.realm.db.patrol.TurnstileBean) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.patrol.TurnstileBean> r2 = com.ciot.realm.db.patrol.TurnstileBean.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface) r5
            java.lang.String r5 = r5.realmGet$id()
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
            io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxy r1 = new io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.patrol.TurnstileBean r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.patrol.TurnstileBean r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_patrol_TurnstileBeanRealmProxy$TurnstileBeanColumnInfo, com.ciot.realm.db.patrol.TurnstileBean, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.patrol.TurnstileBean");
    }

    public static TurnstileBean copy(Realm realm, TurnstileBeanColumnInfo turnstileBeanColumnInfo, TurnstileBean turnstileBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(turnstileBean);
        if (realmObjectProxy != null) {
            return (TurnstileBean) realmObjectProxy;
        }
        com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface = turnstileBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(TurnstileBean.class), set);
        osObjectBuilder.addString(turnstileBeanColumnInfo.idColKey, com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$id());
        osObjectBuilder.addInteger(turnstileBeanColumnInfo.floorColKey, Integer.valueOf(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$floor()));
        osObjectBuilder.addFloat(turnstileBeanColumnInfo.inAngleColKey, Float.valueOf(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$inAngle()));
        osObjectBuilder.addFloat(turnstileBeanColumnInfo.outAngleColKey, Float.valueOf(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$outAngle()));
        osObjectBuilder.addString(turnstileBeanColumnInfo.turnstileHostColKey, com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstileHost());
        osObjectBuilder.addString(turnstileBeanColumnInfo.turnstileIdColKey, com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstileId());
        osObjectBuilder.addInteger(turnstileBeanColumnInfo.turnstilePortColKey, Integer.valueOf(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstilePort()));
        osObjectBuilder.addInteger(turnstileBeanColumnInfo.turnstileTypeColKey, Integer.valueOf(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstileType()));
        com_ciot_realm_db_patrol_TurnstileBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(turnstileBean, newProxyInstance);
        RealmList<PointF> realmGet$area = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$area();
        if (realmGet$area != null) {
            RealmList<PointF> realmGet$area2 = newProxyInstance.realmGet$area();
            realmGet$area2.clear();
            for (int i = 0; i < realmGet$area.size(); i++) {
                PointF pointF = realmGet$area.get(i);
                PointF pointF2 = (PointF) map.get(pointF);
                if (pointF2 != null) {
                    realmGet$area2.add(pointF2);
                } else {
                    realmGet$area2.add(com_ciot_realm_db_PointFRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_PointFRealmProxy.PointFColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PointF.class), pointF, z, map, set));
                }
            }
        }
        PointF realmGet$inPoint = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$inPoint();
        if (realmGet$inPoint == null) {
            newProxyInstance.realmSet$inPoint((PointF) null);
        } else {
            PointF pointF3 = (PointF) map.get(realmGet$inPoint);
            if (pointF3 != null) {
                newProxyInstance.realmSet$inPoint(pointF3);
            } else {
                newProxyInstance.realmSet$inPoint(com_ciot_realm_db_PointFRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_PointFRealmProxy.PointFColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PointF.class), realmGet$inPoint, z, map, set));
            }
        }
        PointF realmGet$outPoint = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$outPoint();
        if (realmGet$outPoint == null) {
            newProxyInstance.realmSet$outPoint((PointF) null);
        } else {
            PointF pointF4 = (PointF) map.get(realmGet$outPoint);
            if (pointF4 != null) {
                newProxyInstance.realmSet$outPoint(pointF4);
            } else {
                newProxyInstance.realmSet$outPoint(com_ciot_realm_db_PointFRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_PointFRealmProxy.PointFColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PointF.class), realmGet$outPoint, z, map, set));
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, TurnstileBean turnstileBean, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        TurnstileBean turnstileBean2 = turnstileBean;
        Map<RealmModel, Long> map2 = map;
        if ((turnstileBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(turnstileBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) turnstileBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(TurnstileBean.class);
        long nativePtr = table.getNativePtr();
        TurnstileBeanColumnInfo turnstileBeanColumnInfo = (TurnstileBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TurnstileBean.class);
        long j2 = turnstileBeanColumnInfo.idColKey;
        com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface = turnstileBean2;
        String realmGet$id = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$id);
        } else {
            Table.throwDuplicatePrimaryKeyException(realmGet$id);
        }
        long j3 = j;
        map2.put(turnstileBean2, Long.valueOf(j3));
        RealmList<PointF> realmGet$area = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$area();
        if (realmGet$area != null) {
            OsList osList = new OsList(table.getUncheckedRow(j3), turnstileBeanColumnInfo.areaColKey);
            Iterator<PointF> it = realmGet$area.iterator();
            while (it.hasNext()) {
                PointF next = it.next();
                Long l = map2.get(next);
                if (l == null) {
                    l = Long.valueOf(com_ciot_realm_db_PointFRealmProxy.insert(realm2, next, map2));
                }
                osList.addRow(l.longValue());
            }
        }
        long j4 = j3;
        Table.nativeSetLong(nativePtr, turnstileBeanColumnInfo.floorColKey, j3, (long) com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$floor(), false);
        PointF realmGet$inPoint = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$inPoint();
        if (realmGet$inPoint != null) {
            Long l2 = map2.get(realmGet$inPoint);
            if (l2 == null) {
                l2 = Long.valueOf(com_ciot_realm_db_PointFRealmProxy.insert(realm2, realmGet$inPoint, map2));
            }
            Table.nativeSetLink(nativePtr, turnstileBeanColumnInfo.inPointColKey, j4, l2.longValue(), false);
        }
        Table.nativeSetFloat(nativePtr, turnstileBeanColumnInfo.inAngleColKey, j4, com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$inAngle(), false);
        PointF realmGet$outPoint = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$outPoint();
        if (realmGet$outPoint != null) {
            Long l3 = map2.get(realmGet$outPoint);
            if (l3 == null) {
                l3 = Long.valueOf(com_ciot_realm_db_PointFRealmProxy.insert(realm2, realmGet$outPoint, map2));
            }
            Table.nativeSetLink(nativePtr, turnstileBeanColumnInfo.outPointColKey, j4, l3.longValue(), false);
        }
        Table.nativeSetFloat(nativePtr, turnstileBeanColumnInfo.outAngleColKey, j4, com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$outAngle(), false);
        String realmGet$turnstileHost = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstileHost();
        if (realmGet$turnstileHost != null) {
            Table.nativeSetString(nativePtr, turnstileBeanColumnInfo.turnstileHostColKey, j4, realmGet$turnstileHost, false);
        }
        String realmGet$turnstileId = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstileId();
        if (realmGet$turnstileId != null) {
            Table.nativeSetString(nativePtr, turnstileBeanColumnInfo.turnstileIdColKey, j4, realmGet$turnstileId, false);
        }
        long j5 = nativePtr;
        long j6 = j4;
        Table.nativeSetLong(j5, turnstileBeanColumnInfo.turnstilePortColKey, j6, (long) com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstilePort(), false);
        Table.nativeSetLong(j5, turnstileBeanColumnInfo.turnstileTypeColKey, j6, (long) com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstileType(), false);
        return j4;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(TurnstileBean.class);
        long nativePtr = table.getNativePtr();
        TurnstileBeanColumnInfo turnstileBeanColumnInfo = (TurnstileBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TurnstileBean.class);
        long j4 = turnstileBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            TurnstileBean turnstileBean = (TurnstileBean) it.next();
            if (!map2.containsKey(turnstileBean)) {
                if ((turnstileBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(turnstileBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) turnstileBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(turnstileBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface = turnstileBean;
                String realmGet$id = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j4);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j4, realmGet$id);
                }
                if (j == -1) {
                    j2 = OsObject.createRowWithPrimaryKey(table, j4, realmGet$id);
                } else {
                    Table.throwDuplicatePrimaryKeyException(realmGet$id);
                    j2 = j;
                }
                map2.put(turnstileBean, Long.valueOf(j2));
                RealmList<PointF> realmGet$area = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$area();
                if (realmGet$area != null) {
                    j3 = j2;
                    OsList osList = new OsList(table.getUncheckedRow(j2), turnstileBeanColumnInfo.areaColKey);
                    Iterator<PointF> it2 = realmGet$area.iterator();
                    while (it2.hasNext()) {
                        PointF next = it2.next();
                        Long l = map2.get(next);
                        if (l == null) {
                            l = Long.valueOf(com_ciot_realm_db_PointFRealmProxy.insert(realm2, next, map2));
                        }
                        osList.addRow(l.longValue());
                    }
                } else {
                    j3 = j2;
                }
                long j5 = j4;
                Table.nativeSetLong(nativePtr, turnstileBeanColumnInfo.floorColKey, j3, (long) com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$floor(), false);
                PointF realmGet$inPoint = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$inPoint();
                if (realmGet$inPoint != null) {
                    Long l2 = map2.get(realmGet$inPoint);
                    if (l2 == null) {
                        l2 = Long.valueOf(com_ciot_realm_db_PointFRealmProxy.insert(realm2, realmGet$inPoint, map2));
                    }
                    table.setLink(turnstileBeanColumnInfo.inPointColKey, j3, l2.longValue(), false);
                }
                Table.nativeSetFloat(nativePtr, turnstileBeanColumnInfo.inAngleColKey, j3, com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$inAngle(), false);
                PointF realmGet$outPoint = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$outPoint();
                if (realmGet$outPoint != null) {
                    Long l3 = map2.get(realmGet$outPoint);
                    if (l3 == null) {
                        l3 = Long.valueOf(com_ciot_realm_db_PointFRealmProxy.insert(realm2, realmGet$outPoint, map2));
                    }
                    table.setLink(turnstileBeanColumnInfo.outPointColKey, j3, l3.longValue(), false);
                }
                Table.nativeSetFloat(nativePtr, turnstileBeanColumnInfo.outAngleColKey, j3, com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$outAngle(), false);
                String realmGet$turnstileHost = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstileHost();
                if (realmGet$turnstileHost != null) {
                    Table.nativeSetString(nativePtr, turnstileBeanColumnInfo.turnstileHostColKey, j3, realmGet$turnstileHost, false);
                }
                String realmGet$turnstileId = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstileId();
                if (realmGet$turnstileId != null) {
                    Table.nativeSetString(nativePtr, turnstileBeanColumnInfo.turnstileIdColKey, j3, realmGet$turnstileId, false);
                }
                long j6 = j3;
                Table.nativeSetLong(nativePtr, turnstileBeanColumnInfo.turnstilePortColKey, j6, (long) com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstilePort(), false);
                Table.nativeSetLong(nativePtr, turnstileBeanColumnInfo.turnstileTypeColKey, j6, (long) com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstileType(), false);
                j4 = j5;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, TurnstileBean turnstileBean, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        TurnstileBean turnstileBean2 = turnstileBean;
        Map<RealmModel, Long> map2 = map;
        if ((turnstileBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(turnstileBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) turnstileBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(TurnstileBean.class);
        long nativePtr = table.getNativePtr();
        TurnstileBeanColumnInfo turnstileBeanColumnInfo = (TurnstileBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TurnstileBean.class);
        long j2 = turnstileBeanColumnInfo.idColKey;
        com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface = turnstileBean2;
        String realmGet$id = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$id);
        }
        long j3 = j;
        map2.put(turnstileBean2, Long.valueOf(j3));
        OsList osList = new OsList(table.getUncheckedRow(j3), turnstileBeanColumnInfo.areaColKey);
        RealmList<PointF> realmGet$area = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$area();
        if (realmGet$area == null || ((long) realmGet$area.size()) != osList.size()) {
            osList.removeAll();
            if (realmGet$area != null) {
                Iterator<PointF> it = realmGet$area.iterator();
                while (it.hasNext()) {
                    PointF next = it.next();
                    Long l = map2.get(next);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_PointFRealmProxy.insertOrUpdate(realm2, next, map2));
                    }
                    osList.addRow(l.longValue());
                }
            }
        } else {
            int size = realmGet$area.size();
            for (int i = 0; i < size; i++) {
                PointF pointF = realmGet$area.get(i);
                Long l2 = map2.get(pointF);
                if (l2 == null) {
                    l2 = Long.valueOf(com_ciot_realm_db_PointFRealmProxy.insertOrUpdate(realm2, pointF, map2));
                }
                osList.setRow((long) i, l2.longValue());
            }
        }
        long j4 = j3;
        Table.nativeSetLong(nativePtr, turnstileBeanColumnInfo.floorColKey, j3, (long) com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$floor(), false);
        PointF realmGet$inPoint = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$inPoint();
        if (realmGet$inPoint != null) {
            Long l3 = map2.get(realmGet$inPoint);
            if (l3 == null) {
                l3 = Long.valueOf(com_ciot_realm_db_PointFRealmProxy.insertOrUpdate(realm2, realmGet$inPoint, map2));
            }
            Table.nativeSetLink(nativePtr, turnstileBeanColumnInfo.inPointColKey, j4, l3.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, turnstileBeanColumnInfo.inPointColKey, j4);
        }
        Table.nativeSetFloat(nativePtr, turnstileBeanColumnInfo.inAngleColKey, j4, com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$inAngle(), false);
        PointF realmGet$outPoint = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$outPoint();
        if (realmGet$outPoint != null) {
            Long l4 = map2.get(realmGet$outPoint);
            if (l4 == null) {
                l4 = Long.valueOf(com_ciot_realm_db_PointFRealmProxy.insertOrUpdate(realm2, realmGet$outPoint, map2));
            }
            Table.nativeSetLink(nativePtr, turnstileBeanColumnInfo.outPointColKey, j4, l4.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, turnstileBeanColumnInfo.outPointColKey, j4);
        }
        Table.nativeSetFloat(nativePtr, turnstileBeanColumnInfo.outAngleColKey, j4, com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$outAngle(), false);
        String realmGet$turnstileHost = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstileHost();
        if (realmGet$turnstileHost != null) {
            Table.nativeSetString(nativePtr, turnstileBeanColumnInfo.turnstileHostColKey, j4, realmGet$turnstileHost, false);
        } else {
            Table.nativeSetNull(nativePtr, turnstileBeanColumnInfo.turnstileHostColKey, j4, false);
        }
        String realmGet$turnstileId = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstileId();
        if (realmGet$turnstileId != null) {
            Table.nativeSetString(nativePtr, turnstileBeanColumnInfo.turnstileIdColKey, j4, realmGet$turnstileId, false);
        } else {
            Table.nativeSetNull(nativePtr, turnstileBeanColumnInfo.turnstileIdColKey, j4, false);
        }
        long j5 = nativePtr;
        long j6 = j4;
        Table.nativeSetLong(j5, turnstileBeanColumnInfo.turnstilePortColKey, j6, (long) com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstilePort(), false);
        Table.nativeSetLong(j5, turnstileBeanColumnInfo.turnstileTypeColKey, j6, (long) com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstileType(), false);
        return j4;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(TurnstileBean.class);
        long nativePtr = table.getNativePtr();
        TurnstileBeanColumnInfo turnstileBeanColumnInfo = (TurnstileBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TurnstileBean.class);
        long j4 = turnstileBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            TurnstileBean turnstileBean = (TurnstileBean) it.next();
            if (!map2.containsKey(turnstileBean)) {
                if ((turnstileBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(turnstileBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) turnstileBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(turnstileBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface = turnstileBean;
                String realmGet$id = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j4);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j4, realmGet$id);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j4, realmGet$id) : j;
                map2.put(turnstileBean, Long.valueOf(createRowWithPrimaryKey));
                OsList osList = new OsList(table.getUncheckedRow(createRowWithPrimaryKey), turnstileBeanColumnInfo.areaColKey);
                RealmList<PointF> realmGet$area = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$area();
                if (realmGet$area == null || ((long) realmGet$area.size()) != osList.size()) {
                    j3 = createRowWithPrimaryKey;
                    j2 = j4;
                    osList.removeAll();
                    if (realmGet$area != null) {
                        Iterator<PointF> it2 = realmGet$area.iterator();
                        while (it2.hasNext()) {
                            PointF next = it2.next();
                            Long l = map2.get(next);
                            if (l == null) {
                                l = Long.valueOf(com_ciot_realm_db_PointFRealmProxy.insertOrUpdate(realm2, next, map2));
                            }
                            osList.addRow(l.longValue());
                        }
                    }
                } else {
                    int size = realmGet$area.size();
                    int i = 0;
                    while (i < size) {
                        PointF pointF = realmGet$area.get(i);
                        Long l2 = map2.get(pointF);
                        if (l2 == null) {
                            l2 = Long.valueOf(com_ciot_realm_db_PointFRealmProxy.insertOrUpdate(realm2, pointF, map2));
                        }
                        osList.setRow((long) i, l2.longValue());
                        i++;
                        createRowWithPrimaryKey = createRowWithPrimaryKey;
                        j4 = j4;
                    }
                    j3 = createRowWithPrimaryKey;
                    j2 = j4;
                }
                long j5 = j3;
                long j6 = j2;
                Table.nativeSetLong(nativePtr, turnstileBeanColumnInfo.floorColKey, j5, (long) com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$floor(), false);
                PointF realmGet$inPoint = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$inPoint();
                if (realmGet$inPoint != null) {
                    Long l3 = map2.get(realmGet$inPoint);
                    if (l3 == null) {
                        l3 = Long.valueOf(com_ciot_realm_db_PointFRealmProxy.insertOrUpdate(realm2, realmGet$inPoint, map2));
                    }
                    Table.nativeSetLink(nativePtr, turnstileBeanColumnInfo.inPointColKey, j5, l3.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, turnstileBeanColumnInfo.inPointColKey, j5);
                }
                Table.nativeSetFloat(nativePtr, turnstileBeanColumnInfo.inAngleColKey, j5, com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$inAngle(), false);
                PointF realmGet$outPoint = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$outPoint();
                if (realmGet$outPoint != null) {
                    Long l4 = map2.get(realmGet$outPoint);
                    if (l4 == null) {
                        l4 = Long.valueOf(com_ciot_realm_db_PointFRealmProxy.insertOrUpdate(realm2, realmGet$outPoint, map2));
                    }
                    Table.nativeSetLink(nativePtr, turnstileBeanColumnInfo.outPointColKey, j5, l4.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, turnstileBeanColumnInfo.outPointColKey, j5);
                }
                Table.nativeSetFloat(nativePtr, turnstileBeanColumnInfo.outAngleColKey, j5, com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$outAngle(), false);
                String realmGet$turnstileHost = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstileHost();
                if (realmGet$turnstileHost != null) {
                    Table.nativeSetString(nativePtr, turnstileBeanColumnInfo.turnstileHostColKey, j5, realmGet$turnstileHost, false);
                } else {
                    Table.nativeSetNull(nativePtr, turnstileBeanColumnInfo.turnstileHostColKey, j5, false);
                }
                String realmGet$turnstileId = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstileId();
                if (realmGet$turnstileId != null) {
                    Table.nativeSetString(nativePtr, turnstileBeanColumnInfo.turnstileIdColKey, j5, realmGet$turnstileId, false);
                } else {
                    Table.nativeSetNull(nativePtr, turnstileBeanColumnInfo.turnstileIdColKey, j5, false);
                }
                long j7 = j5;
                Table.nativeSetLong(nativePtr, turnstileBeanColumnInfo.turnstilePortColKey, j7, (long) com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstilePort(), false);
                Table.nativeSetLong(nativePtr, turnstileBeanColumnInfo.turnstileTypeColKey, j7, (long) com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmGet$turnstileType(), false);
                j4 = j6;
            }
        }
    }

    public static TurnstileBean createDetachedCopy(TurnstileBean turnstileBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        TurnstileBean turnstileBean2;
        if (i > i2 || turnstileBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(turnstileBean);
        if (cacheData == null) {
            turnstileBean2 = new TurnstileBean();
            map.put(turnstileBean, new RealmObjectProxy.CacheData(i, turnstileBean2));
        } else if (i >= cacheData.minDepth) {
            return (TurnstileBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            turnstileBean2 = (TurnstileBean) cacheData.object;
        }
        com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface = turnstileBean2;
        com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2 = turnstileBean;
        com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$id(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$id());
        if (i == i2) {
            com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$area((RealmList<PointF>) null);
        } else {
            RealmList<PointF> realmGet$area = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$area();
            RealmList realmList = new RealmList();
            com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$area(realmList);
            int i3 = i + 1;
            int size = realmGet$area.size();
            for (int i4 = 0; i4 < size; i4++) {
                realmList.add(com_ciot_realm_db_PointFRealmProxy.createDetachedCopy(realmGet$area.get(i4), i3, i2, map));
            }
        }
        com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$floor(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$floor());
        int i5 = i + 1;
        com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$inPoint(com_ciot_realm_db_PointFRealmProxy.createDetachedCopy(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$inPoint(), i5, i2, map));
        com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$inAngle(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$inAngle());
        com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$outPoint(com_ciot_realm_db_PointFRealmProxy.createDetachedCopy(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$outPoint(), i5, i2, map));
        com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$outAngle(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$outAngle());
        com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$turnstileHost(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$turnstileHost());
        com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$turnstileId(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$turnstileId());
        com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$turnstilePort(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$turnstilePort());
        com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface.realmSet$turnstileType(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$turnstileType());
        return turnstileBean2;
    }

    static TurnstileBean update(Realm realm, TurnstileBeanColumnInfo turnstileBeanColumnInfo, TurnstileBean turnstileBean, TurnstileBean turnstileBean2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        TurnstileBeanColumnInfo turnstileBeanColumnInfo2 = turnstileBeanColumnInfo;
        Map<RealmModel, RealmObjectProxy> map2 = map;
        com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface = turnstileBean;
        com_ciot_realm_db_patrol_TurnstileBeanRealmProxyInterface com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2 = turnstileBean2;
        Realm realm2 = realm;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(TurnstileBean.class), set);
        osObjectBuilder.addString(turnstileBeanColumnInfo2.idColKey, com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$id());
        RealmList<PointF> realmGet$area = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$area();
        if (realmGet$area != null) {
            RealmList realmList = new RealmList();
            for (int i = 0; i < realmGet$area.size(); i++) {
                PointF pointF = realmGet$area.get(i);
                PointF pointF2 = (PointF) map2.get(pointF);
                if (pointF2 != null) {
                    realmList.add(pointF2);
                } else {
                    realmList.add(com_ciot_realm_db_PointFRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_PointFRealmProxy.PointFColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PointF.class), pointF, true, map, set));
                }
            }
            osObjectBuilder.addObjectList(turnstileBeanColumnInfo2.areaColKey, realmList);
        } else {
            osObjectBuilder.addObjectList(turnstileBeanColumnInfo2.areaColKey, new RealmList());
        }
        osObjectBuilder.addInteger(turnstileBeanColumnInfo2.floorColKey, Integer.valueOf(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$floor()));
        PointF realmGet$inPoint = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$inPoint();
        if (realmGet$inPoint == null) {
            osObjectBuilder.addNull(turnstileBeanColumnInfo2.inPointColKey);
        } else {
            PointF pointF3 = (PointF) map2.get(realmGet$inPoint);
            if (pointF3 != null) {
                osObjectBuilder.addObject(turnstileBeanColumnInfo2.inPointColKey, pointF3);
            } else {
                osObjectBuilder.addObject(turnstileBeanColumnInfo2.inPointColKey, com_ciot_realm_db_PointFRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_PointFRealmProxy.PointFColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PointF.class), realmGet$inPoint, true, map, set));
            }
        }
        osObjectBuilder.addFloat(turnstileBeanColumnInfo2.inAngleColKey, Float.valueOf(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$inAngle()));
        PointF realmGet$outPoint = com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$outPoint();
        if (realmGet$outPoint == null) {
            osObjectBuilder.addNull(turnstileBeanColumnInfo2.outPointColKey);
        } else {
            PointF pointF4 = (PointF) map2.get(realmGet$outPoint);
            if (pointF4 != null) {
                osObjectBuilder.addObject(turnstileBeanColumnInfo2.outPointColKey, pointF4);
            } else {
                osObjectBuilder.addObject(turnstileBeanColumnInfo2.outPointColKey, com_ciot_realm_db_PointFRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_PointFRealmProxy.PointFColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PointF.class), realmGet$outPoint, true, map, set));
            }
        }
        osObjectBuilder.addFloat(turnstileBeanColumnInfo2.outAngleColKey, Float.valueOf(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$outAngle()));
        osObjectBuilder.addString(turnstileBeanColumnInfo2.turnstileHostColKey, com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$turnstileHost());
        osObjectBuilder.addString(turnstileBeanColumnInfo2.turnstileIdColKey, com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$turnstileId());
        osObjectBuilder.addInteger(turnstileBeanColumnInfo2.turnstilePortColKey, Integer.valueOf(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$turnstilePort()));
        osObjectBuilder.addInteger(turnstileBeanColumnInfo2.turnstileTypeColKey, Integer.valueOf(com_ciot_realm_db_patrol_turnstilebeanrealmproxyinterface2.realmGet$turnstileType()));
        osObjectBuilder.updateExistingObject();
        return turnstileBean;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("TurnstileBean = proxy[");
        sb.append("{id:");
        String realmGet$id = realmGet$id();
        String str = Configurator.NULL;
        sb.append(realmGet$id != null ? realmGet$id() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{area:");
        sb.append("RealmList<PointF>[");
        sb.append(realmGet$area().size());
        sb.append("]");
        sb.append("}");
        sb.append(",");
        sb.append("{floor:");
        sb.append(realmGet$floor());
        sb.append("}");
        sb.append(",");
        sb.append("{inPoint:");
        PointF realmGet$inPoint = realmGet$inPoint();
        String str2 = com_ciot_realm_db_PointFRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        sb.append(realmGet$inPoint != null ? str2 : str);
        sb.append("}");
        sb.append(",");
        sb.append("{inAngle:");
        sb.append(realmGet$inAngle());
        sb.append("}");
        sb.append(",");
        sb.append("{outPoint:");
        if (realmGet$outPoint() == null) {
            str2 = str;
        }
        sb.append(str2);
        sb.append("}");
        sb.append(",");
        sb.append("{outAngle:");
        sb.append(realmGet$outAngle());
        sb.append("}");
        sb.append(",");
        sb.append("{turnstileHost:");
        sb.append(realmGet$turnstileHost() != null ? realmGet$turnstileHost() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{turnstileId:");
        if (realmGet$turnstileId() != null) {
            str = realmGet$turnstileId();
        }
        sb.append(str);
        sb.append("}");
        sb.append(",");
        sb.append("{turnstilePort:");
        sb.append(realmGet$turnstilePort());
        sb.append("}");
        sb.append(",");
        sb.append("{turnstileType:");
        sb.append(realmGet$turnstileType());
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
        com_ciot_realm_db_patrol_TurnstileBeanRealmProxy com_ciot_realm_db_patrol_turnstilebeanrealmproxy = (com_ciot_realm_db_patrol_TurnstileBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_patrol_turnstilebeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_patrol_turnstilebeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_patrol_turnstilebeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
