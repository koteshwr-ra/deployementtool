package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.Tactics;
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

public class com_ciot_realm_db_TacticsRealmProxy extends Tactics implements RealmObjectProxy, com_ciot_realm_db_TacticsRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private TacticsColumnInfo columnInfo;
    private ProxyState<Tactics> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Tactics";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class TacticsColumnInfo extends ColumnInfo {
        long companyColKey;
        long descriptionColKey;
        long effectColKey;
        long endColKey;
        long endTimeCloseColKey;
        long endTimeOpenColKey;
        long idColKey;
        long personColKey;
        long ruletypeColKey;
        long startColKey;
        long startTimeCloseColKey;
        long startTimeOpenColKey;
        long typeColKey;

        TacticsColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(13);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.companyColKey = addColumnDetails("company", "company", objectSchemaInfo);
            this.startColKey = addColumnDetails("start", "start", objectSchemaInfo);
            this.endColKey = addColumnDetails("end", "end", objectSchemaInfo);
            this.startTimeOpenColKey = addColumnDetails("startTimeOpen", "startTimeOpen", objectSchemaInfo);
            this.startTimeCloseColKey = addColumnDetails("startTimeClose", "startTimeClose", objectSchemaInfo);
            this.endTimeOpenColKey = addColumnDetails("endTimeOpen", "endTimeOpen", objectSchemaInfo);
            this.endTimeCloseColKey = addColumnDetails("endTimeClose", "endTimeClose", objectSchemaInfo);
            this.typeColKey = addColumnDetails("type", "type", objectSchemaInfo);
            this.ruletypeColKey = addColumnDetails("ruletype", "ruletype", objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
            this.personColKey = addColumnDetails("person", "person", objectSchemaInfo);
            this.effectColKey = addColumnDetails("effect", "effect", objectSchemaInfo);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
        }

        TacticsColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new TacticsColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            TacticsColumnInfo tacticsColumnInfo = (TacticsColumnInfo) columnInfo;
            TacticsColumnInfo tacticsColumnInfo2 = (TacticsColumnInfo) columnInfo2;
            tacticsColumnInfo2.companyColKey = tacticsColumnInfo.companyColKey;
            tacticsColumnInfo2.startColKey = tacticsColumnInfo.startColKey;
            tacticsColumnInfo2.endColKey = tacticsColumnInfo.endColKey;
            tacticsColumnInfo2.startTimeOpenColKey = tacticsColumnInfo.startTimeOpenColKey;
            tacticsColumnInfo2.startTimeCloseColKey = tacticsColumnInfo.startTimeCloseColKey;
            tacticsColumnInfo2.endTimeOpenColKey = tacticsColumnInfo.endTimeOpenColKey;
            tacticsColumnInfo2.endTimeCloseColKey = tacticsColumnInfo.endTimeCloseColKey;
            tacticsColumnInfo2.typeColKey = tacticsColumnInfo.typeColKey;
            tacticsColumnInfo2.ruletypeColKey = tacticsColumnInfo.ruletypeColKey;
            tacticsColumnInfo2.descriptionColKey = tacticsColumnInfo.descriptionColKey;
            tacticsColumnInfo2.personColKey = tacticsColumnInfo.personColKey;
            tacticsColumnInfo2.effectColKey = tacticsColumnInfo.effectColKey;
            tacticsColumnInfo2.idColKey = tacticsColumnInfo.idColKey;
        }
    }

    com_ciot_realm_db_TacticsRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (TacticsColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<Tactics> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$company() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.companyColKey);
    }

    public void realmSet$company(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.companyColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.companyColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.companyColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.companyColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$start() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.startColKey);
    }

    public void realmSet$start(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.startColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.startColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.startColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.startColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$end() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.endColKey);
    }

    public void realmSet$end(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.endColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.endColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.endColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.endColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public long realmGet$startTimeOpen() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.startTimeOpenColKey);
    }

    public void realmSet$startTimeOpen(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.startTimeOpenColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.startTimeOpenColKey, row$realm.getObjectKey(), j, true);
        }
    }

    public long realmGet$startTimeClose() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.startTimeCloseColKey);
    }

    public void realmSet$startTimeClose(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.startTimeCloseColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.startTimeCloseColKey, row$realm.getObjectKey(), j, true);
        }
    }

    public long realmGet$endTimeOpen() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.endTimeOpenColKey);
    }

    public void realmSet$endTimeOpen(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.endTimeOpenColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.endTimeOpenColKey, row$realm.getObjectKey(), j, true);
        }
    }

    public long realmGet$endTimeClose() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.endTimeCloseColKey);
    }

    public void realmSet$endTimeClose(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.endTimeCloseColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.endTimeCloseColKey, row$realm.getObjectKey(), j, true);
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

    public String realmGet$ruletype() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.ruletypeColKey);
    }

    public void realmSet$ruletype(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.ruletypeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.ruletypeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.ruletypeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.ruletypeColKey, row$realm.getObjectKey(), str, true);
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

    public String realmGet$person() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.personColKey);
    }

    public void realmSet$person(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.personColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.personColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.personColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.personColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$effect() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.effectColKey);
    }

    public void realmSet$effect(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.effectColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.effectColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.effectColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.effectColKey, row$realm.getObjectKey(), str, true);
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
            throw new RealmException("Primary key field 'id' cannot be changed after object was created.");
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 13, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("company", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("start", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("end", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("startTimeOpen", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("startTimeClose", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("endTimeOpen", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("endTimeClose", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("type", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("ruletype", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("person", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("effect", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("id", RealmFieldType.STRING, true, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TacticsColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new TacticsColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v9, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v10, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01b2  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01cb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.Tactics createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            java.lang.String r1 = "id"
            r2 = 0
            if (r14 == 0) goto L_0x0064
            java.lang.Class<com.ciot.realm.db.Tactics> r14 = com.ciot.realm.db.Tactics.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.Tactics> r4 = com.ciot.realm.db.Tactics.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_TacticsRealmProxy$TacticsColumnInfo r3 = (io.realm.com_ciot_realm_db_TacticsRealmProxy.TacticsColumnInfo) r3
            long r3 = r3.idColKey
            boolean r5 = r13.isNull(r1)
            if (r5 == 0) goto L_0x0028
            long r3 = r14.findFirstNull(r3)
            goto L_0x0030
        L_0x0028:
            java.lang.String r5 = r13.getString(r1)
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
            java.lang.Class<com.ciot.realm.db.Tactics> r3 = com.ciot.realm.db.Tactics.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005f }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005f }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005f }
            io.realm.com_ciot_realm_db_TacticsRealmProxy r14 = new io.realm.com_ciot_realm_db_TacticsRealmProxy     // Catch:{ all -> 0x005f }
            r14.<init>()     // Catch:{ all -> 0x005f }
            r5.clear()
            goto L_0x0065
        L_0x005f:
            r12 = move-exception
            r5.clear()
            throw r12
        L_0x0064:
            r14 = r2
        L_0x0065:
            if (r14 != 0) goto L_0x0094
            boolean r14 = r13.has(r1)
            if (r14 == 0) goto L_0x008c
            boolean r14 = r13.isNull(r1)
            r3 = 1
            if (r14 == 0) goto L_0x007e
            java.lang.Class<com.ciot.realm.db.Tactics> r14 = com.ciot.realm.db.Tactics.class
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_TacticsRealmProxy r14 = (io.realm.com_ciot_realm_db_TacticsRealmProxy) r14
            goto L_0x0094
        L_0x007e:
            java.lang.Class<com.ciot.realm.db.Tactics> r14 = com.ciot.realm.db.Tactics.class
            java.lang.String r1 = r13.getString(r1)
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_TacticsRealmProxy r14 = (io.realm.com_ciot_realm_db_TacticsRealmProxy) r14
            goto L_0x0094
        L_0x008c:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'id'."
            r12.<init>(r13)
            throw r12
        L_0x0094:
            r12 = r14
            io.realm.com_ciot_realm_db_TacticsRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_TacticsRealmProxyInterface) r12
            java.lang.String r0 = "company"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00b0
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00a9
            r12.realmSet$company(r2)
            goto L_0x00b0
        L_0x00a9:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$company(r0)
        L_0x00b0:
            java.lang.String r0 = "start"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00c9
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00c2
            r12.realmSet$start(r2)
            goto L_0x00c9
        L_0x00c2:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$start(r0)
        L_0x00c9:
            java.lang.String r0 = "end"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00e2
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00db
            r12.realmSet$end(r2)
            goto L_0x00e2
        L_0x00db:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$end(r0)
        L_0x00e2:
            java.lang.String r0 = "startTimeOpen"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0100
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x00f8
            long r0 = r13.getLong(r0)
            r12.realmSet$startTimeOpen(r0)
            goto L_0x0100
        L_0x00f8:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'startTimeOpen' to null."
            r12.<init>(r13)
            throw r12
        L_0x0100:
            java.lang.String r0 = "startTimeClose"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x011e
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0116
            long r0 = r13.getLong(r0)
            r12.realmSet$startTimeClose(r0)
            goto L_0x011e
        L_0x0116:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'startTimeClose' to null."
            r12.<init>(r13)
            throw r12
        L_0x011e:
            java.lang.String r0 = "endTimeOpen"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x013c
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0134
            long r0 = r13.getLong(r0)
            r12.realmSet$endTimeOpen(r0)
            goto L_0x013c
        L_0x0134:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'endTimeOpen' to null."
            r12.<init>(r13)
            throw r12
        L_0x013c:
            java.lang.String r0 = "endTimeClose"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x015a
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0152
            long r0 = r13.getLong(r0)
            r12.realmSet$endTimeClose(r0)
            goto L_0x015a
        L_0x0152:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'endTimeClose' to null."
            r12.<init>(r13)
            throw r12
        L_0x015a:
            java.lang.String r0 = "type"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0178
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0170
            int r0 = r13.getInt(r0)
            r12.realmSet$type(r0)
            goto L_0x0178
        L_0x0170:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'type' to null."
            r12.<init>(r13)
            throw r12
        L_0x0178:
            java.lang.String r0 = "ruletype"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0191
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x018a
            r12.realmSet$ruletype(r2)
            goto L_0x0191
        L_0x018a:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$ruletype(r0)
        L_0x0191:
            java.lang.String r0 = "description"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01aa
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01a3
            r12.realmSet$description(r2)
            goto L_0x01aa
        L_0x01a3:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$description(r0)
        L_0x01aa:
            java.lang.String r0 = "person"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01c3
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01bc
            r12.realmSet$person(r2)
            goto L_0x01c3
        L_0x01bc:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$person(r0)
        L_0x01c3:
            java.lang.String r0 = "effect"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01dc
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01d5
            r12.realmSet$effect(r2)
            goto L_0x01dc
        L_0x01d5:
            java.lang.String r13 = r13.getString(r0)
            r12.realmSet$effect(r13)
        L_0x01dc:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_TacticsRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.Tactics");
    }

    public static Tactics createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        Tactics tactics = new Tactics();
        com_ciot_realm_db_TacticsRealmProxyInterface com_ciot_realm_db_tacticsrealmproxyinterface = tactics;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("company")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$company(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$company((String) null);
                }
            } else if (nextName.equals("start")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$start(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$start((String) null);
                }
            } else if (nextName.equals("end")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$end(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$end((String) null);
                }
            } else if (nextName.equals("startTimeOpen")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$startTimeOpen(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'startTimeOpen' to null.");
                }
            } else if (nextName.equals("startTimeClose")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$startTimeClose(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'startTimeClose' to null.");
                }
            } else if (nextName.equals("endTimeOpen")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$endTimeOpen(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'endTimeOpen' to null.");
                }
            } else if (nextName.equals("endTimeClose")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$endTimeClose(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'endTimeClose' to null.");
                }
            } else if (nextName.equals("type")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$type(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'type' to null.");
                }
            } else if (nextName.equals("ruletype")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$ruletype(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$ruletype((String) null);
                }
            } else if (nextName.equals("description")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$description(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$description((String) null);
                }
            } else if (nextName.equals("person")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$person(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$person((String) null);
                }
            } else if (nextName.equals("effect")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$effect(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$effect((String) null);
                }
            } else if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$id((String) null);
                }
                z = true;
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        if (z) {
            return (Tactics) realm.copyToRealm(tactics, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_TacticsRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) Tactics.class), false, Collections.emptyList());
        com_ciot_realm_db_TacticsRealmProxy com_ciot_realm_db_tacticsrealmproxy = new com_ciot_realm_db_TacticsRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_tacticsrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.Tactics copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_TacticsRealmProxy.TacticsColumnInfo r9, com.ciot.realm.db.Tactics r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.Tactics r1 = (com.ciot.realm.db.Tactics) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.Tactics> r2 = com.ciot.realm.db.Tactics.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_TacticsRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_TacticsRealmProxyInterface) r5
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
            io.realm.com_ciot_realm_db_TacticsRealmProxy r1 = new io.realm.com_ciot_realm_db_TacticsRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.Tactics r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.Tactics r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_TacticsRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_TacticsRealmProxy$TacticsColumnInfo, com.ciot.realm.db.Tactics, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.Tactics");
    }

    public static Tactics copy(Realm realm, TacticsColumnInfo tacticsColumnInfo, Tactics tactics, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(tactics);
        if (realmObjectProxy != null) {
            return (Tactics) realmObjectProxy;
        }
        com_ciot_realm_db_TacticsRealmProxyInterface com_ciot_realm_db_tacticsrealmproxyinterface = tactics;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Tactics.class), set);
        osObjectBuilder.addString(tacticsColumnInfo.companyColKey, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$company());
        osObjectBuilder.addString(tacticsColumnInfo.startColKey, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$start());
        osObjectBuilder.addString(tacticsColumnInfo.endColKey, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$end());
        osObjectBuilder.addInteger(tacticsColumnInfo.startTimeOpenColKey, Long.valueOf(com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$startTimeOpen()));
        osObjectBuilder.addInteger(tacticsColumnInfo.startTimeCloseColKey, Long.valueOf(com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$startTimeClose()));
        osObjectBuilder.addInteger(tacticsColumnInfo.endTimeOpenColKey, Long.valueOf(com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$endTimeOpen()));
        osObjectBuilder.addInteger(tacticsColumnInfo.endTimeCloseColKey, Long.valueOf(com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$endTimeClose()));
        osObjectBuilder.addInteger(tacticsColumnInfo.typeColKey, Integer.valueOf(com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$type()));
        osObjectBuilder.addString(tacticsColumnInfo.ruletypeColKey, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$ruletype());
        osObjectBuilder.addString(tacticsColumnInfo.descriptionColKey, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$description());
        osObjectBuilder.addString(tacticsColumnInfo.personColKey, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$person());
        osObjectBuilder.addString(tacticsColumnInfo.effectColKey, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$effect());
        osObjectBuilder.addString(tacticsColumnInfo.idColKey, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$id());
        com_ciot_realm_db_TacticsRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(tactics, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, Tactics tactics, Map<RealmModel, Long> map) {
        long j;
        Tactics tactics2 = tactics;
        if ((tactics2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(tactics)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) tactics2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Tactics.class);
        long nativePtr = table.getNativePtr();
        TacticsColumnInfo tacticsColumnInfo = (TacticsColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Tactics.class);
        long j2 = tacticsColumnInfo.idColKey;
        com_ciot_realm_db_TacticsRealmProxyInterface com_ciot_realm_db_tacticsrealmproxyinterface = tactics2;
        String realmGet$id = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$id();
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
        map.put(tactics2, Long.valueOf(j3));
        String realmGet$company = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$company();
        if (realmGet$company != null) {
            Table.nativeSetString(nativePtr, tacticsColumnInfo.companyColKey, j3, realmGet$company, false);
        }
        String realmGet$start = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$start();
        if (realmGet$start != null) {
            Table.nativeSetString(nativePtr, tacticsColumnInfo.startColKey, j3, realmGet$start, false);
        }
        String realmGet$end = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$end();
        if (realmGet$end != null) {
            Table.nativeSetString(nativePtr, tacticsColumnInfo.endColKey, j3, realmGet$end, false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetLong(j4, tacticsColumnInfo.startTimeOpenColKey, j5, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$startTimeOpen(), false);
        Table.nativeSetLong(j4, tacticsColumnInfo.startTimeCloseColKey, j5, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$startTimeClose(), false);
        Table.nativeSetLong(j4, tacticsColumnInfo.endTimeOpenColKey, j5, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$endTimeOpen(), false);
        Table.nativeSetLong(j4, tacticsColumnInfo.endTimeCloseColKey, j5, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$endTimeClose(), false);
        Table.nativeSetLong(j4, tacticsColumnInfo.typeColKey, j5, (long) com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$type(), false);
        String realmGet$ruletype = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$ruletype();
        if (realmGet$ruletype != null) {
            Table.nativeSetString(nativePtr, tacticsColumnInfo.ruletypeColKey, j3, realmGet$ruletype, false);
        }
        String realmGet$description = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, tacticsColumnInfo.descriptionColKey, j3, realmGet$description, false);
        }
        String realmGet$person = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$person();
        if (realmGet$person != null) {
            Table.nativeSetString(nativePtr, tacticsColumnInfo.personColKey, j3, realmGet$person, false);
        }
        String realmGet$effect = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$effect();
        if (realmGet$effect != null) {
            Table.nativeSetString(nativePtr, tacticsColumnInfo.effectColKey, j3, realmGet$effect, false);
        }
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Tactics.class);
        long nativePtr = table.getNativePtr();
        TacticsColumnInfo tacticsColumnInfo = (TacticsColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Tactics.class);
        long j4 = tacticsColumnInfo.idColKey;
        while (it.hasNext()) {
            Tactics tactics = (Tactics) it.next();
            if (!map2.containsKey(tactics)) {
                if ((tactics instanceof RealmObjectProxy) && !RealmObject.isFrozen(tactics)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) tactics;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(tactics, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_TacticsRealmProxyInterface com_ciot_realm_db_tacticsrealmproxyinterface = tactics;
                String realmGet$id = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$id();
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
                map2.put(tactics, Long.valueOf(j2));
                String realmGet$company = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$company();
                if (realmGet$company != null) {
                    j3 = j4;
                    Table.nativeSetString(nativePtr, tacticsColumnInfo.companyColKey, j2, realmGet$company, false);
                } else {
                    j3 = j4;
                }
                String realmGet$start = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$start();
                if (realmGet$start != null) {
                    Table.nativeSetString(nativePtr, tacticsColumnInfo.startColKey, j2, realmGet$start, false);
                }
                String realmGet$end = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$end();
                if (realmGet$end != null) {
                    Table.nativeSetString(nativePtr, tacticsColumnInfo.endColKey, j2, realmGet$end, false);
                }
                long j5 = nativePtr;
                long j6 = j2;
                Table.nativeSetLong(j5, tacticsColumnInfo.startTimeOpenColKey, j6, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$startTimeOpen(), false);
                Table.nativeSetLong(j5, tacticsColumnInfo.startTimeCloseColKey, j6, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$startTimeClose(), false);
                Table.nativeSetLong(j5, tacticsColumnInfo.endTimeOpenColKey, j6, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$endTimeOpen(), false);
                Table.nativeSetLong(j5, tacticsColumnInfo.endTimeCloseColKey, j6, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$endTimeClose(), false);
                Table.nativeSetLong(nativePtr, tacticsColumnInfo.typeColKey, j6, (long) com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$type(), false);
                String realmGet$ruletype = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$ruletype();
                if (realmGet$ruletype != null) {
                    Table.nativeSetString(nativePtr, tacticsColumnInfo.ruletypeColKey, j2, realmGet$ruletype, false);
                }
                String realmGet$description = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, tacticsColumnInfo.descriptionColKey, j2, realmGet$description, false);
                }
                String realmGet$person = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$person();
                if (realmGet$person != null) {
                    Table.nativeSetString(nativePtr, tacticsColumnInfo.personColKey, j2, realmGet$person, false);
                }
                String realmGet$effect = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$effect();
                if (realmGet$effect != null) {
                    Table.nativeSetString(nativePtr, tacticsColumnInfo.effectColKey, j2, realmGet$effect, false);
                }
                j4 = j3;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Tactics tactics, Map<RealmModel, Long> map) {
        long j;
        Tactics tactics2 = tactics;
        if ((tactics2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(tactics)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) tactics2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(Tactics.class);
        long nativePtr = table.getNativePtr();
        TacticsColumnInfo tacticsColumnInfo = (TacticsColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Tactics.class);
        long j2 = tacticsColumnInfo.idColKey;
        com_ciot_realm_db_TacticsRealmProxyInterface com_ciot_realm_db_tacticsrealmproxyinterface = tactics2;
        String realmGet$id = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$id);
        }
        long j3 = j;
        map.put(tactics2, Long.valueOf(j3));
        String realmGet$company = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$company();
        if (realmGet$company != null) {
            Table.nativeSetString(nativePtr, tacticsColumnInfo.companyColKey, j3, realmGet$company, false);
        } else {
            Table.nativeSetNull(nativePtr, tacticsColumnInfo.companyColKey, j3, false);
        }
        String realmGet$start = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$start();
        if (realmGet$start != null) {
            Table.nativeSetString(nativePtr, tacticsColumnInfo.startColKey, j3, realmGet$start, false);
        } else {
            Table.nativeSetNull(nativePtr, tacticsColumnInfo.startColKey, j3, false);
        }
        String realmGet$end = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$end();
        if (realmGet$end != null) {
            Table.nativeSetString(nativePtr, tacticsColumnInfo.endColKey, j3, realmGet$end, false);
        } else {
            Table.nativeSetNull(nativePtr, tacticsColumnInfo.endColKey, j3, false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetLong(j4, tacticsColumnInfo.startTimeOpenColKey, j5, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$startTimeOpen(), false);
        Table.nativeSetLong(j4, tacticsColumnInfo.startTimeCloseColKey, j5, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$startTimeClose(), false);
        Table.nativeSetLong(j4, tacticsColumnInfo.endTimeOpenColKey, j5, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$endTimeOpen(), false);
        Table.nativeSetLong(j4, tacticsColumnInfo.endTimeCloseColKey, j5, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$endTimeClose(), false);
        Table.nativeSetLong(j4, tacticsColumnInfo.typeColKey, j5, (long) com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$type(), false);
        String realmGet$ruletype = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$ruletype();
        if (realmGet$ruletype != null) {
            Table.nativeSetString(nativePtr, tacticsColumnInfo.ruletypeColKey, j3, realmGet$ruletype, false);
        } else {
            Table.nativeSetNull(nativePtr, tacticsColumnInfo.ruletypeColKey, j3, false);
        }
        String realmGet$description = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, tacticsColumnInfo.descriptionColKey, j3, realmGet$description, false);
        } else {
            Table.nativeSetNull(nativePtr, tacticsColumnInfo.descriptionColKey, j3, false);
        }
        String realmGet$person = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$person();
        if (realmGet$person != null) {
            Table.nativeSetString(nativePtr, tacticsColumnInfo.personColKey, j3, realmGet$person, false);
        } else {
            Table.nativeSetNull(nativePtr, tacticsColumnInfo.personColKey, j3, false);
        }
        String realmGet$effect = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$effect();
        if (realmGet$effect != null) {
            Table.nativeSetString(nativePtr, tacticsColumnInfo.effectColKey, j3, realmGet$effect, false);
        } else {
            Table.nativeSetNull(nativePtr, tacticsColumnInfo.effectColKey, j3, false);
        }
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(Tactics.class);
        long nativePtr = table.getNativePtr();
        TacticsColumnInfo tacticsColumnInfo = (TacticsColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Tactics.class);
        long j3 = tacticsColumnInfo.idColKey;
        while (it.hasNext()) {
            Tactics tactics = (Tactics) it.next();
            if (!map2.containsKey(tactics)) {
                if ((tactics instanceof RealmObjectProxy) && !RealmObject.isFrozen(tactics)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) tactics;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(tactics, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_TacticsRealmProxyInterface com_ciot_realm_db_tacticsrealmproxyinterface = tactics;
                String realmGet$id = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j3);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j3, realmGet$id);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j3, realmGet$id) : j;
                map2.put(tactics, Long.valueOf(createRowWithPrimaryKey));
                String realmGet$company = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$company();
                if (realmGet$company != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, tacticsColumnInfo.companyColKey, createRowWithPrimaryKey, realmGet$company, false);
                } else {
                    j2 = j3;
                    Table.nativeSetNull(nativePtr, tacticsColumnInfo.companyColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$start = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$start();
                if (realmGet$start != null) {
                    Table.nativeSetString(nativePtr, tacticsColumnInfo.startColKey, createRowWithPrimaryKey, realmGet$start, false);
                } else {
                    Table.nativeSetNull(nativePtr, tacticsColumnInfo.startColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$end = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$end();
                if (realmGet$end != null) {
                    Table.nativeSetString(nativePtr, tacticsColumnInfo.endColKey, createRowWithPrimaryKey, realmGet$end, false);
                } else {
                    Table.nativeSetNull(nativePtr, tacticsColumnInfo.endColKey, createRowWithPrimaryKey, false);
                }
                long j4 = nativePtr;
                long j5 = createRowWithPrimaryKey;
                Table.nativeSetLong(j4, tacticsColumnInfo.startTimeOpenColKey, j5, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$startTimeOpen(), false);
                Table.nativeSetLong(j4, tacticsColumnInfo.startTimeCloseColKey, j5, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$startTimeClose(), false);
                Table.nativeSetLong(j4, tacticsColumnInfo.endTimeOpenColKey, j5, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$endTimeOpen(), false);
                Table.nativeSetLong(j4, tacticsColumnInfo.endTimeCloseColKey, j5, com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$endTimeClose(), false);
                Table.nativeSetLong(nativePtr, tacticsColumnInfo.typeColKey, j5, (long) com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$type(), false);
                String realmGet$ruletype = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$ruletype();
                if (realmGet$ruletype != null) {
                    Table.nativeSetString(nativePtr, tacticsColumnInfo.ruletypeColKey, createRowWithPrimaryKey, realmGet$ruletype, false);
                } else {
                    Table.nativeSetNull(nativePtr, tacticsColumnInfo.ruletypeColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$description = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, tacticsColumnInfo.descriptionColKey, createRowWithPrimaryKey, realmGet$description, false);
                } else {
                    Table.nativeSetNull(nativePtr, tacticsColumnInfo.descriptionColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$person = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$person();
                if (realmGet$person != null) {
                    Table.nativeSetString(nativePtr, tacticsColumnInfo.personColKey, createRowWithPrimaryKey, realmGet$person, false);
                } else {
                    Table.nativeSetNull(nativePtr, tacticsColumnInfo.personColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$effect = com_ciot_realm_db_tacticsrealmproxyinterface.realmGet$effect();
                if (realmGet$effect != null) {
                    Table.nativeSetString(nativePtr, tacticsColumnInfo.effectColKey, createRowWithPrimaryKey, realmGet$effect, false);
                } else {
                    Table.nativeSetNull(nativePtr, tacticsColumnInfo.effectColKey, createRowWithPrimaryKey, false);
                }
                j3 = j2;
            }
        }
    }

    public static Tactics createDetachedCopy(Tactics tactics, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        Tactics tactics2;
        if (i > i2 || tactics == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(tactics);
        if (cacheData == null) {
            tactics2 = new Tactics();
            map.put(tactics, new RealmObjectProxy.CacheData(i, tactics2));
        } else if (i >= cacheData.minDepth) {
            return (Tactics) cacheData.object;
        } else {
            cacheData.minDepth = i;
            tactics2 = (Tactics) cacheData.object;
        }
        com_ciot_realm_db_TacticsRealmProxyInterface com_ciot_realm_db_tacticsrealmproxyinterface = tactics2;
        com_ciot_realm_db_TacticsRealmProxyInterface com_ciot_realm_db_tacticsrealmproxyinterface2 = tactics;
        com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$company(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$company());
        com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$start(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$start());
        com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$end(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$end());
        com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$startTimeOpen(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$startTimeOpen());
        com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$startTimeClose(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$startTimeClose());
        com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$endTimeOpen(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$endTimeOpen());
        com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$endTimeClose(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$endTimeClose());
        com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$type(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$type());
        com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$ruletype(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$ruletype());
        com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$description(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$description());
        com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$person(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$person());
        com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$effect(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$effect());
        com_ciot_realm_db_tacticsrealmproxyinterface.realmSet$id(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$id());
        return tactics2;
    }

    static Tactics update(Realm realm, TacticsColumnInfo tacticsColumnInfo, Tactics tactics, Tactics tactics2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_TacticsRealmProxyInterface com_ciot_realm_db_tacticsrealmproxyinterface = tactics;
        com_ciot_realm_db_TacticsRealmProxyInterface com_ciot_realm_db_tacticsrealmproxyinterface2 = tactics2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Tactics.class), set);
        osObjectBuilder.addString(tacticsColumnInfo.companyColKey, com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$company());
        osObjectBuilder.addString(tacticsColumnInfo.startColKey, com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$start());
        osObjectBuilder.addString(tacticsColumnInfo.endColKey, com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$end());
        osObjectBuilder.addInteger(tacticsColumnInfo.startTimeOpenColKey, Long.valueOf(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$startTimeOpen()));
        osObjectBuilder.addInteger(tacticsColumnInfo.startTimeCloseColKey, Long.valueOf(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$startTimeClose()));
        osObjectBuilder.addInteger(tacticsColumnInfo.endTimeOpenColKey, Long.valueOf(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$endTimeOpen()));
        osObjectBuilder.addInteger(tacticsColumnInfo.endTimeCloseColKey, Long.valueOf(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$endTimeClose()));
        osObjectBuilder.addInteger(tacticsColumnInfo.typeColKey, Integer.valueOf(com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$type()));
        osObjectBuilder.addString(tacticsColumnInfo.ruletypeColKey, com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$ruletype());
        osObjectBuilder.addString(tacticsColumnInfo.descriptionColKey, com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$description());
        osObjectBuilder.addString(tacticsColumnInfo.personColKey, com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$person());
        osObjectBuilder.addString(tacticsColumnInfo.effectColKey, com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$effect());
        osObjectBuilder.addString(tacticsColumnInfo.idColKey, com_ciot_realm_db_tacticsrealmproxyinterface2.realmGet$id());
        osObjectBuilder.updateExistingObject();
        return tactics;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("Tactics = proxy[");
        sb.append("{company:");
        String realmGet$company = realmGet$company();
        String str = Configurator.NULL;
        sb.append(realmGet$company != null ? realmGet$company() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{start:");
        sb.append(realmGet$start() != null ? realmGet$start() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{end:");
        sb.append(realmGet$end() != null ? realmGet$end() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{startTimeOpen:");
        sb.append(realmGet$startTimeOpen());
        sb.append("}");
        sb.append(",");
        sb.append("{startTimeClose:");
        sb.append(realmGet$startTimeClose());
        sb.append("}");
        sb.append(",");
        sb.append("{endTimeOpen:");
        sb.append(realmGet$endTimeOpen());
        sb.append("}");
        sb.append(",");
        sb.append("{endTimeClose:");
        sb.append(realmGet$endTimeClose());
        sb.append("}");
        sb.append(",");
        sb.append("{type:");
        sb.append(realmGet$type());
        sb.append("}");
        sb.append(",");
        sb.append("{ruletype:");
        sb.append(realmGet$ruletype() != null ? realmGet$ruletype() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{description:");
        sb.append(realmGet$description() != null ? realmGet$description() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{person:");
        sb.append(realmGet$person() != null ? realmGet$person() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{effect:");
        sb.append(realmGet$effect() != null ? realmGet$effect() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{id:");
        if (realmGet$id() != null) {
            str = realmGet$id();
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
        com_ciot_realm_db_TacticsRealmProxy com_ciot_realm_db_tacticsrealmproxy = (com_ciot_realm_db_TacticsRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_tacticsrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_tacticsrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_tacticsrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
