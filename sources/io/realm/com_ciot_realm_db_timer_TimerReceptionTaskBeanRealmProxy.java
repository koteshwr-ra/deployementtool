package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.alibaba.android.arouter.compiler.utils.Consts;
import com.ciot.realm.db.timer.TimerReceptionTaskBean;
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

public class com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy extends TimerReceptionTaskBean implements RealmObjectProxy, com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private TimerReceptionTaskBeanColumnInfo columnInfo;
    private ProxyState<TimerReceptionTaskBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "TimerReceptionTaskBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class TimerReceptionTaskBeanColumnInfo extends ColumnInfo {
        long cycletypeColKey;
        long descriptionColKey;
        long enableColKey;
        long idColKey;
        long taskdatasColKey;
        long taskendColKey;
        long taskstartColKey;
        long tasktypeColKey;

        TimerReceptionTaskBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(8);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.tasktypeColKey = addColumnDetails("tasktype", "tasktype", objectSchemaInfo);
            this.cycletypeColKey = addColumnDetails("cycletype", "cycletype", objectSchemaInfo);
            this.taskdatasColKey = addColumnDetails("taskdatas", "taskdatas", objectSchemaInfo);
            this.taskstartColKey = addColumnDetails("taskstart", "taskstart", objectSchemaInfo);
            this.taskendColKey = addColumnDetails("taskend", "taskend", objectSchemaInfo);
            this.enableColKey = addColumnDetails(Consts.VALUE_ENABLE, Consts.VALUE_ENABLE, objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
        }

        TimerReceptionTaskBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new TimerReceptionTaskBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            TimerReceptionTaskBeanColumnInfo timerReceptionTaskBeanColumnInfo = (TimerReceptionTaskBeanColumnInfo) columnInfo;
            TimerReceptionTaskBeanColumnInfo timerReceptionTaskBeanColumnInfo2 = (TimerReceptionTaskBeanColumnInfo) columnInfo2;
            timerReceptionTaskBeanColumnInfo2.idColKey = timerReceptionTaskBeanColumnInfo.idColKey;
            timerReceptionTaskBeanColumnInfo2.tasktypeColKey = timerReceptionTaskBeanColumnInfo.tasktypeColKey;
            timerReceptionTaskBeanColumnInfo2.cycletypeColKey = timerReceptionTaskBeanColumnInfo.cycletypeColKey;
            timerReceptionTaskBeanColumnInfo2.taskdatasColKey = timerReceptionTaskBeanColumnInfo.taskdatasColKey;
            timerReceptionTaskBeanColumnInfo2.taskstartColKey = timerReceptionTaskBeanColumnInfo.taskstartColKey;
            timerReceptionTaskBeanColumnInfo2.taskendColKey = timerReceptionTaskBeanColumnInfo.taskendColKey;
            timerReceptionTaskBeanColumnInfo2.enableColKey = timerReceptionTaskBeanColumnInfo.enableColKey;
            timerReceptionTaskBeanColumnInfo2.descriptionColKey = timerReceptionTaskBeanColumnInfo.descriptionColKey;
        }
    }

    com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (TimerReceptionTaskBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<TimerReceptionTaskBean> proxyState2 = new ProxyState<>(this);
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

    public String realmGet$tasktype() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.tasktypeColKey);
    }

    public void realmSet$tasktype(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.tasktypeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.tasktypeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.tasktypeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.tasktypeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$cycletype() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.cycletypeColKey);
    }

    public void realmSet$cycletype(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.cycletypeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.cycletypeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.cycletypeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.cycletypeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$taskdatas() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.taskdatasColKey);
    }

    public void realmSet$taskdatas(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.taskdatasColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.taskdatasColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.taskdatasColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.taskdatasColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$taskstart() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.taskstartColKey);
    }

    public void realmSet$taskstart(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.taskstartColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.taskstartColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.taskstartColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.taskstartColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$taskend() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.taskendColKey);
    }

    public void realmSet$taskend(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.taskendColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.taskendColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.taskendColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.taskendColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public boolean realmGet$enable() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.enableColKey);
    }

    public void realmSet$enable(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.enableColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.enableColKey, row$realm.getObjectKey(), z, true);
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 8, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("id", RealmFieldType.STRING, true, false, false);
        builder2.addPersistedProperty("tasktype", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("cycletype", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("taskdatas", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("taskstart", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("taskend", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(Consts.VALUE_ENABLE, RealmFieldType.BOOLEAN, false, false, true);
        builder2.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TimerReceptionTaskBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new TimerReceptionTaskBeanColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v5, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v6, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x013a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.timer.TimerReceptionTaskBean createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            java.lang.String r1 = "id"
            r2 = 0
            if (r14 == 0) goto L_0x0064
            java.lang.Class<com.ciot.realm.db.timer.TimerReceptionTaskBean> r14 = com.ciot.realm.db.timer.TimerReceptionTaskBean.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.timer.TimerReceptionTaskBean> r4 = com.ciot.realm.db.timer.TimerReceptionTaskBean.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy$TimerReceptionTaskBeanColumnInfo r3 = (io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.TimerReceptionTaskBeanColumnInfo) r3
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
            java.lang.Class<com.ciot.realm.db.timer.TimerReceptionTaskBean> r3 = com.ciot.realm.db.timer.TimerReceptionTaskBean.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005f }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005f }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005f }
            io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy r14 = new io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy     // Catch:{ all -> 0x005f }
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
            java.lang.Class<com.ciot.realm.db.timer.TimerReceptionTaskBean> r14 = com.ciot.realm.db.timer.TimerReceptionTaskBean.class
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy r14 = (io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy) r14
            goto L_0x0094
        L_0x007e:
            java.lang.Class<com.ciot.realm.db.timer.TimerReceptionTaskBean> r14 = com.ciot.realm.db.timer.TimerReceptionTaskBean.class
            java.lang.String r1 = r13.getString(r1)
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy r14 = (io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy) r14
            goto L_0x0094
        L_0x008c:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'id'."
            r12.<init>(r13)
            throw r12
        L_0x0094:
            r12 = r14
            io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxyInterface) r12
            java.lang.String r0 = "tasktype"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00b0
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00a9
            r12.realmSet$tasktype(r2)
            goto L_0x00b0
        L_0x00a9:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$tasktype(r0)
        L_0x00b0:
            java.lang.String r0 = "cycletype"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00c9
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00c2
            r12.realmSet$cycletype(r2)
            goto L_0x00c9
        L_0x00c2:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$cycletype(r0)
        L_0x00c9:
            java.lang.String r0 = "taskdatas"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00e2
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00db
            r12.realmSet$taskdatas(r2)
            goto L_0x00e2
        L_0x00db:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$taskdatas(r0)
        L_0x00e2:
            java.lang.String r0 = "taskstart"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00fb
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00f4
            r12.realmSet$taskstart(r2)
            goto L_0x00fb
        L_0x00f4:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$taskstart(r0)
        L_0x00fb:
            java.lang.String r0 = "taskend"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0114
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x010d
            r12.realmSet$taskend(r2)
            goto L_0x0114
        L_0x010d:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$taskend(r0)
        L_0x0114:
            java.lang.String r0 = "enable"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0132
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x012a
            boolean r0 = r13.getBoolean(r0)
            r12.realmSet$enable(r0)
            goto L_0x0132
        L_0x012a:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'enable' to null."
            r12.<init>(r13)
            throw r12
        L_0x0132:
            java.lang.String r0 = "description"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x014b
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0144
            r12.realmSet$description(r2)
            goto L_0x014b
        L_0x0144:
            java.lang.String r13 = r13.getString(r0)
            r12.realmSet$description(r13)
        L_0x014b:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.timer.TimerReceptionTaskBean");
    }

    public static TimerReceptionTaskBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        TimerReceptionTaskBean timerReceptionTaskBean = new TimerReceptionTaskBean();
        com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxyInterface com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface = timerReceptionTaskBean;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$id((String) null);
                }
                z = true;
            } else if (nextName.equals("tasktype")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$tasktype(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$tasktype((String) null);
                }
            } else if (nextName.equals("cycletype")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$cycletype(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$cycletype((String) null);
                }
            } else if (nextName.equals("taskdatas")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$taskdatas(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$taskdatas((String) null);
                }
            } else if (nextName.equals("taskstart")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$taskstart(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$taskstart((String) null);
                }
            } else if (nextName.equals("taskend")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$taskend(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$taskend((String) null);
                }
            } else if (nextName.equals(Consts.VALUE_ENABLE)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$enable(jsonReader.nextBoolean());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'enable' to null.");
                }
            } else if (!nextName.equals("description")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$description(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$description((String) null);
            }
        }
        jsonReader.endObject();
        if (z) {
            return (TimerReceptionTaskBean) realm.copyToRealm(timerReceptionTaskBean, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimerReceptionTaskBean.class), false, Collections.emptyList());
        com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxy = new com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.timer.TimerReceptionTaskBean copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.TimerReceptionTaskBeanColumnInfo r9, com.ciot.realm.db.timer.TimerReceptionTaskBean r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.timer.TimerReceptionTaskBean r1 = (com.ciot.realm.db.timer.TimerReceptionTaskBean) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.timer.TimerReceptionTaskBean> r2 = com.ciot.realm.db.timer.TimerReceptionTaskBean.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxyInterface) r5
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
            io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy r1 = new io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.timer.TimerReceptionTaskBean r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.timer.TimerReceptionTaskBean r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy$TimerReceptionTaskBeanColumnInfo, com.ciot.realm.db.timer.TimerReceptionTaskBean, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.timer.TimerReceptionTaskBean");
    }

    public static TimerReceptionTaskBean copy(Realm realm, TimerReceptionTaskBeanColumnInfo timerReceptionTaskBeanColumnInfo, TimerReceptionTaskBean timerReceptionTaskBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(timerReceptionTaskBean);
        if (realmObjectProxy != null) {
            return (TimerReceptionTaskBean) realmObjectProxy;
        }
        com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxyInterface com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface = timerReceptionTaskBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(TimerReceptionTaskBean.class), set);
        osObjectBuilder.addString(timerReceptionTaskBeanColumnInfo.idColKey, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$id());
        osObjectBuilder.addString(timerReceptionTaskBeanColumnInfo.tasktypeColKey, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$tasktype());
        osObjectBuilder.addString(timerReceptionTaskBeanColumnInfo.cycletypeColKey, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$cycletype());
        osObjectBuilder.addString(timerReceptionTaskBeanColumnInfo.taskdatasColKey, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$taskdatas());
        osObjectBuilder.addString(timerReceptionTaskBeanColumnInfo.taskstartColKey, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$taskstart());
        osObjectBuilder.addString(timerReceptionTaskBeanColumnInfo.taskendColKey, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$taskend());
        osObjectBuilder.addBoolean(timerReceptionTaskBeanColumnInfo.enableColKey, Boolean.valueOf(com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$enable()));
        osObjectBuilder.addString(timerReceptionTaskBeanColumnInfo.descriptionColKey, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$description());
        com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(timerReceptionTaskBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, TimerReceptionTaskBean timerReceptionTaskBean, Map<RealmModel, Long> map) {
        long j;
        TimerReceptionTaskBean timerReceptionTaskBean2 = timerReceptionTaskBean;
        if ((timerReceptionTaskBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(timerReceptionTaskBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) timerReceptionTaskBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(TimerReceptionTaskBean.class);
        long nativePtr = table.getNativePtr();
        TimerReceptionTaskBeanColumnInfo timerReceptionTaskBeanColumnInfo = (TimerReceptionTaskBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimerReceptionTaskBean.class);
        long j2 = timerReceptionTaskBeanColumnInfo.idColKey;
        com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxyInterface com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface = timerReceptionTaskBean2;
        String realmGet$id = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$id();
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
        map.put(timerReceptionTaskBean2, Long.valueOf(j3));
        String realmGet$tasktype = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$tasktype();
        if (realmGet$tasktype != null) {
            Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.tasktypeColKey, j3, realmGet$tasktype, false);
        }
        String realmGet$cycletype = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$cycletype();
        if (realmGet$cycletype != null) {
            Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.cycletypeColKey, j3, realmGet$cycletype, false);
        }
        String realmGet$taskdatas = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$taskdatas();
        if (realmGet$taskdatas != null) {
            Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.taskdatasColKey, j3, realmGet$taskdatas, false);
        }
        String realmGet$taskstart = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$taskstart();
        if (realmGet$taskstart != null) {
            Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.taskstartColKey, j3, realmGet$taskstart, false);
        }
        String realmGet$taskend = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$taskend();
        if (realmGet$taskend != null) {
            Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.taskendColKey, j3, realmGet$taskend, false);
        }
        Table.nativeSetBoolean(nativePtr, timerReceptionTaskBeanColumnInfo.enableColKey, j3, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$enable(), false);
        String realmGet$description = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.descriptionColKey, j3, realmGet$description, false);
        }
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(TimerReceptionTaskBean.class);
        long nativePtr = table.getNativePtr();
        TimerReceptionTaskBeanColumnInfo timerReceptionTaskBeanColumnInfo = (TimerReceptionTaskBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimerReceptionTaskBean.class);
        long j3 = timerReceptionTaskBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            TimerReceptionTaskBean timerReceptionTaskBean = (TimerReceptionTaskBean) it.next();
            if (!map2.containsKey(timerReceptionTaskBean)) {
                if ((timerReceptionTaskBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(timerReceptionTaskBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) timerReceptionTaskBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(timerReceptionTaskBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxyInterface com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface = timerReceptionTaskBean;
                String realmGet$id = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j3);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j3, realmGet$id);
                }
                if (j == -1) {
                    j2 = OsObject.createRowWithPrimaryKey(table, j3, realmGet$id);
                } else {
                    Table.throwDuplicatePrimaryKeyException(realmGet$id);
                    j2 = j;
                }
                map2.put(timerReceptionTaskBean, Long.valueOf(j2));
                String realmGet$tasktype = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$tasktype();
                if (realmGet$tasktype != null) {
                    Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.tasktypeColKey, j2, realmGet$tasktype, false);
                }
                String realmGet$cycletype = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$cycletype();
                if (realmGet$cycletype != null) {
                    Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.cycletypeColKey, j2, realmGet$cycletype, false);
                }
                String realmGet$taskdatas = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$taskdatas();
                if (realmGet$taskdatas != null) {
                    Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.taskdatasColKey, j2, realmGet$taskdatas, false);
                }
                String realmGet$taskstart = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$taskstart();
                if (realmGet$taskstart != null) {
                    Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.taskstartColKey, j2, realmGet$taskstart, false);
                }
                String realmGet$taskend = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$taskend();
                if (realmGet$taskend != null) {
                    Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.taskendColKey, j2, realmGet$taskend, false);
                }
                Table.nativeSetBoolean(nativePtr, timerReceptionTaskBeanColumnInfo.enableColKey, j2, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$enable(), false);
                String realmGet$description = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.descriptionColKey, j2, realmGet$description, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, TimerReceptionTaskBean timerReceptionTaskBean, Map<RealmModel, Long> map) {
        long j;
        TimerReceptionTaskBean timerReceptionTaskBean2 = timerReceptionTaskBean;
        if ((timerReceptionTaskBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(timerReceptionTaskBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) timerReceptionTaskBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(TimerReceptionTaskBean.class);
        long nativePtr = table.getNativePtr();
        TimerReceptionTaskBeanColumnInfo timerReceptionTaskBeanColumnInfo = (TimerReceptionTaskBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimerReceptionTaskBean.class);
        long j2 = timerReceptionTaskBeanColumnInfo.idColKey;
        com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxyInterface com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface = timerReceptionTaskBean2;
        String realmGet$id = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$id);
        }
        long j3 = j;
        map.put(timerReceptionTaskBean2, Long.valueOf(j3));
        String realmGet$tasktype = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$tasktype();
        if (realmGet$tasktype != null) {
            Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.tasktypeColKey, j3, realmGet$tasktype, false);
        } else {
            Table.nativeSetNull(nativePtr, timerReceptionTaskBeanColumnInfo.tasktypeColKey, j3, false);
        }
        String realmGet$cycletype = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$cycletype();
        if (realmGet$cycletype != null) {
            Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.cycletypeColKey, j3, realmGet$cycletype, false);
        } else {
            Table.nativeSetNull(nativePtr, timerReceptionTaskBeanColumnInfo.cycletypeColKey, j3, false);
        }
        String realmGet$taskdatas = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$taskdatas();
        if (realmGet$taskdatas != null) {
            Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.taskdatasColKey, j3, realmGet$taskdatas, false);
        } else {
            Table.nativeSetNull(nativePtr, timerReceptionTaskBeanColumnInfo.taskdatasColKey, j3, false);
        }
        String realmGet$taskstart = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$taskstart();
        if (realmGet$taskstart != null) {
            Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.taskstartColKey, j3, realmGet$taskstart, false);
        } else {
            Table.nativeSetNull(nativePtr, timerReceptionTaskBeanColumnInfo.taskstartColKey, j3, false);
        }
        String realmGet$taskend = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$taskend();
        if (realmGet$taskend != null) {
            Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.taskendColKey, j3, realmGet$taskend, false);
        } else {
            Table.nativeSetNull(nativePtr, timerReceptionTaskBeanColumnInfo.taskendColKey, j3, false);
        }
        Table.nativeSetBoolean(nativePtr, timerReceptionTaskBeanColumnInfo.enableColKey, j3, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$enable(), false);
        String realmGet$description = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.descriptionColKey, j3, realmGet$description, false);
        } else {
            Table.nativeSetNull(nativePtr, timerReceptionTaskBeanColumnInfo.descriptionColKey, j3, false);
        }
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(TimerReceptionTaskBean.class);
        long nativePtr = table.getNativePtr();
        TimerReceptionTaskBeanColumnInfo timerReceptionTaskBeanColumnInfo = (TimerReceptionTaskBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TimerReceptionTaskBean.class);
        long j2 = timerReceptionTaskBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            TimerReceptionTaskBean timerReceptionTaskBean = (TimerReceptionTaskBean) it.next();
            if (!map2.containsKey(timerReceptionTaskBean)) {
                if ((timerReceptionTaskBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(timerReceptionTaskBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) timerReceptionTaskBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(timerReceptionTaskBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxyInterface com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface = timerReceptionTaskBean;
                String realmGet$id = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j2);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j2, realmGet$id) : j;
                map2.put(timerReceptionTaskBean, Long.valueOf(createRowWithPrimaryKey));
                String realmGet$tasktype = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$tasktype();
                if (realmGet$tasktype != null) {
                    Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.tasktypeColKey, createRowWithPrimaryKey, realmGet$tasktype, false);
                } else {
                    Table.nativeSetNull(nativePtr, timerReceptionTaskBeanColumnInfo.tasktypeColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$cycletype = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$cycletype();
                if (realmGet$cycletype != null) {
                    Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.cycletypeColKey, createRowWithPrimaryKey, realmGet$cycletype, false);
                } else {
                    Table.nativeSetNull(nativePtr, timerReceptionTaskBeanColumnInfo.cycletypeColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$taskdatas = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$taskdatas();
                if (realmGet$taskdatas != null) {
                    Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.taskdatasColKey, createRowWithPrimaryKey, realmGet$taskdatas, false);
                } else {
                    Table.nativeSetNull(nativePtr, timerReceptionTaskBeanColumnInfo.taskdatasColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$taskstart = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$taskstart();
                if (realmGet$taskstart != null) {
                    Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.taskstartColKey, createRowWithPrimaryKey, realmGet$taskstart, false);
                } else {
                    Table.nativeSetNull(nativePtr, timerReceptionTaskBeanColumnInfo.taskstartColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$taskend = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$taskend();
                if (realmGet$taskend != null) {
                    Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.taskendColKey, createRowWithPrimaryKey, realmGet$taskend, false);
                } else {
                    Table.nativeSetNull(nativePtr, timerReceptionTaskBeanColumnInfo.taskendColKey, createRowWithPrimaryKey, false);
                }
                Table.nativeSetBoolean(nativePtr, timerReceptionTaskBeanColumnInfo.enableColKey, createRowWithPrimaryKey, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$enable(), false);
                String realmGet$description = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, timerReceptionTaskBeanColumnInfo.descriptionColKey, createRowWithPrimaryKey, realmGet$description, false);
                } else {
                    Table.nativeSetNull(nativePtr, timerReceptionTaskBeanColumnInfo.descriptionColKey, createRowWithPrimaryKey, false);
                }
            }
        }
    }

    public static TimerReceptionTaskBean createDetachedCopy(TimerReceptionTaskBean timerReceptionTaskBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        TimerReceptionTaskBean timerReceptionTaskBean2;
        if (i > i2 || timerReceptionTaskBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(timerReceptionTaskBean);
        if (cacheData == null) {
            timerReceptionTaskBean2 = new TimerReceptionTaskBean();
            map.put(timerReceptionTaskBean, new RealmObjectProxy.CacheData(i, timerReceptionTaskBean2));
        } else if (i >= cacheData.minDepth) {
            return (TimerReceptionTaskBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            timerReceptionTaskBean2 = (TimerReceptionTaskBean) cacheData.object;
        }
        com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxyInterface com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface = timerReceptionTaskBean2;
        com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxyInterface com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2 = timerReceptionTaskBean;
        com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$id(com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$tasktype(com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2.realmGet$tasktype());
        com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$cycletype(com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2.realmGet$cycletype());
        com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$taskdatas(com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2.realmGet$taskdatas());
        com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$taskstart(com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2.realmGet$taskstart());
        com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$taskend(com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2.realmGet$taskend());
        com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$enable(com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2.realmGet$enable());
        com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface.realmSet$description(com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2.realmGet$description());
        return timerReceptionTaskBean2;
    }

    static TimerReceptionTaskBean update(Realm realm, TimerReceptionTaskBeanColumnInfo timerReceptionTaskBeanColumnInfo, TimerReceptionTaskBean timerReceptionTaskBean, TimerReceptionTaskBean timerReceptionTaskBean2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxyInterface com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface = timerReceptionTaskBean;
        com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxyInterface com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2 = timerReceptionTaskBean2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(TimerReceptionTaskBean.class), set);
        osObjectBuilder.addString(timerReceptionTaskBeanColumnInfo.idColKey, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2.realmGet$id());
        osObjectBuilder.addString(timerReceptionTaskBeanColumnInfo.tasktypeColKey, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2.realmGet$tasktype());
        osObjectBuilder.addString(timerReceptionTaskBeanColumnInfo.cycletypeColKey, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2.realmGet$cycletype());
        osObjectBuilder.addString(timerReceptionTaskBeanColumnInfo.taskdatasColKey, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2.realmGet$taskdatas());
        osObjectBuilder.addString(timerReceptionTaskBeanColumnInfo.taskstartColKey, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2.realmGet$taskstart());
        osObjectBuilder.addString(timerReceptionTaskBeanColumnInfo.taskendColKey, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2.realmGet$taskend());
        osObjectBuilder.addBoolean(timerReceptionTaskBeanColumnInfo.enableColKey, Boolean.valueOf(com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2.realmGet$enable()));
        osObjectBuilder.addString(timerReceptionTaskBeanColumnInfo.descriptionColKey, com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxyinterface2.realmGet$description());
        osObjectBuilder.updateExistingObject();
        return timerReceptionTaskBean;
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
        com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxy = (com_ciot_realm_db_timer_TimerReceptionTaskBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_timer_timerreceptiontaskbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
