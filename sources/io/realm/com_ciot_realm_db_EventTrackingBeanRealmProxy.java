package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.EventTrackingBean;
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

public class com_ciot_realm_db_EventTrackingBeanRealmProxy extends EventTrackingBean implements RealmObjectProxy, com_ciot_realm_db_EventTrackingBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private EventTrackingBeanColumnInfo columnInfo;
    private ProxyState<EventTrackingBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "EventTrackingBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class EventTrackingBeanColumnInfo extends ColumnInfo {
        long begintimestampColKey;
        long deviceaddrColKey;
        long deviceidColKey;
        long devicetypeColKey;
        long durationColKey;
        long eventnameColKey;
        long eventtypeColKey;
        long industryColKey;
        long keyColKey;
        long reporttimestampColKey;
        long tokenColKey;
        long userColKey;
        long versionColKey;

        EventTrackingBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(13);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.deviceidColKey = addColumnDetails("deviceid", "deviceid", objectSchemaInfo);
            this.tokenColKey = addColumnDetails("token", "token", objectSchemaInfo);
            this.keyColKey = addColumnDetails("key", "key", objectSchemaInfo);
            this.userColKey = addColumnDetails("user", "user", objectSchemaInfo);
            this.industryColKey = addColumnDetails("industry", "industry", objectSchemaInfo);
            this.deviceaddrColKey = addColumnDetails("deviceaddr", "deviceaddr", objectSchemaInfo);
            this.versionColKey = addColumnDetails("version", "version", objectSchemaInfo);
            this.devicetypeColKey = addColumnDetails("devicetype", "devicetype", objectSchemaInfo);
            this.reporttimestampColKey = addColumnDetails("reporttimestamp", "reporttimestamp", objectSchemaInfo);
            this.eventnameColKey = addColumnDetails("eventname", "eventname", objectSchemaInfo);
            this.eventtypeColKey = addColumnDetails("eventtype", "eventtype", objectSchemaInfo);
            this.durationColKey = addColumnDetails("duration", "duration", objectSchemaInfo);
            this.begintimestampColKey = addColumnDetails("begintimestamp", "begintimestamp", objectSchemaInfo);
        }

        EventTrackingBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new EventTrackingBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            EventTrackingBeanColumnInfo eventTrackingBeanColumnInfo = (EventTrackingBeanColumnInfo) columnInfo;
            EventTrackingBeanColumnInfo eventTrackingBeanColumnInfo2 = (EventTrackingBeanColumnInfo) columnInfo2;
            eventTrackingBeanColumnInfo2.deviceidColKey = eventTrackingBeanColumnInfo.deviceidColKey;
            eventTrackingBeanColumnInfo2.tokenColKey = eventTrackingBeanColumnInfo.tokenColKey;
            eventTrackingBeanColumnInfo2.keyColKey = eventTrackingBeanColumnInfo.keyColKey;
            eventTrackingBeanColumnInfo2.userColKey = eventTrackingBeanColumnInfo.userColKey;
            eventTrackingBeanColumnInfo2.industryColKey = eventTrackingBeanColumnInfo.industryColKey;
            eventTrackingBeanColumnInfo2.deviceaddrColKey = eventTrackingBeanColumnInfo.deviceaddrColKey;
            eventTrackingBeanColumnInfo2.versionColKey = eventTrackingBeanColumnInfo.versionColKey;
            eventTrackingBeanColumnInfo2.devicetypeColKey = eventTrackingBeanColumnInfo.devicetypeColKey;
            eventTrackingBeanColumnInfo2.reporttimestampColKey = eventTrackingBeanColumnInfo.reporttimestampColKey;
            eventTrackingBeanColumnInfo2.eventnameColKey = eventTrackingBeanColumnInfo.eventnameColKey;
            eventTrackingBeanColumnInfo2.eventtypeColKey = eventTrackingBeanColumnInfo.eventtypeColKey;
            eventTrackingBeanColumnInfo2.durationColKey = eventTrackingBeanColumnInfo.durationColKey;
            eventTrackingBeanColumnInfo2.begintimestampColKey = eventTrackingBeanColumnInfo.begintimestampColKey;
        }
    }

    com_ciot_realm_db_EventTrackingBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (EventTrackingBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<EventTrackingBean> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$deviceid() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.deviceidColKey);
    }

    public void realmSet$deviceid(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.deviceidColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.deviceidColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.deviceidColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.deviceidColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$token() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.tokenColKey);
    }

    public void realmSet$token(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.tokenColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.tokenColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.tokenColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.tokenColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$key() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.keyColKey);
    }

    public void realmSet$key(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.keyColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.keyColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.keyColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.keyColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$user() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.userColKey);
    }

    public void realmSet$user(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.userColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.userColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.userColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.userColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$industry() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.industryColKey);
    }

    public void realmSet$industry(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.industryColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.industryColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.industryColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.industryColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$deviceaddr() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.deviceaddrColKey);
    }

    public void realmSet$deviceaddr(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.deviceaddrColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.deviceaddrColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.deviceaddrColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.deviceaddrColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$version() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.versionColKey);
    }

    public void realmSet$version(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.versionColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.versionColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.versionColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.versionColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$devicetype() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.devicetypeColKey);
    }

    public void realmSet$devicetype(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.devicetypeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.devicetypeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.devicetypeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.devicetypeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public long realmGet$reporttimestamp() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.reporttimestampColKey);
    }

    public void realmSet$reporttimestamp(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.reporttimestampColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.reporttimestampColKey, row$realm.getObjectKey(), j, true);
        }
    }

    public String realmGet$eventname() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.eventnameColKey);
    }

    public void realmSet$eventname(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.eventnameColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.eventnameColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.eventnameColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.eventnameColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$eventtype() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.eventtypeColKey);
    }

    public void realmSet$eventtype(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.eventtypeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.eventtypeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.eventtypeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.eventtypeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public long realmGet$duration() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.durationColKey);
    }

    public void realmSet$duration(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.durationColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.durationColKey, row$realm.getObjectKey(), j, true);
        }
    }

    public long realmGet$begintimestamp() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.begintimestampColKey);
    }

    public void realmSet$begintimestamp(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'begintimestamp' cannot be changed after object was created.");
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 13, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("deviceid", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("token", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("key", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("user", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("industry", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("deviceaddr", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("version", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("devicetype", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("reporttimestamp", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("eventname", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("eventtype", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("duration", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("begintimestamp", RealmFieldType.INTEGER, true, true, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static EventTrackingBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new EventTrackingBeanColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r12v6, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r12v7, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x014f  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01b8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.EventTrackingBean createOrUpdateUsingJsonObject(io.realm.Realm r12, org.json.JSONObject r13, boolean r14) throws org.json.JSONException {
        /*
            java.util.List r0 = java.util.Collections.emptyList()
            java.lang.String r1 = "begintimestamp"
            r2 = 0
            if (r14 == 0) goto L_0x0061
            java.lang.Class<com.ciot.realm.db.EventTrackingBean> r14 = com.ciot.realm.db.EventTrackingBean.class
            io.realm.internal.Table r14 = r12.getTable(r14)
            io.realm.RealmSchema r3 = r12.getSchema()
            java.lang.Class<com.ciot.realm.db.EventTrackingBean> r4 = com.ciot.realm.db.EventTrackingBean.class
            io.realm.internal.ColumnInfo r3 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)
            io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxy$EventTrackingBeanColumnInfo r3 = (io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxy.EventTrackingBeanColumnInfo) r3
            long r3 = r3.begintimestampColKey
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
            java.lang.Class<com.ciot.realm.db.EventTrackingBean> r3 = com.ciot.realm.db.EventTrackingBean.class
            io.realm.internal.ColumnInfo r9 = r14.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)     // Catch:{ all -> 0x005c }
            r10 = 0
            java.util.List r11 = java.util.Collections.emptyList()     // Catch:{ all -> 0x005c }
            r6 = r5
            r7 = r12
            r6.set(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x005c }
            io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxy r14 = new io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxy     // Catch:{ all -> 0x005c }
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
            java.lang.Class<com.ciot.realm.db.EventTrackingBean> r14 = com.ciot.realm.db.EventTrackingBean.class
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r2, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxy r14 = (io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxy) r14
            goto L_0x0095
        L_0x007b:
            java.lang.Class<com.ciot.realm.db.EventTrackingBean> r14 = com.ciot.realm.db.EventTrackingBean.class
            long r4 = r13.getLong(r1)
            java.lang.Long r1 = java.lang.Long.valueOf(r4)
            io.realm.RealmModel r12 = r12.createObjectInternal(r14, r1, r3, r0)
            r14 = r12
            io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxy r14 = (io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxy) r14
            goto L_0x0095
        L_0x008d:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "JSON object doesn't have the primary key field 'begintimestamp'."
            r12.<init>(r13)
            throw r12
        L_0x0095:
            r12 = r14
            io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxyInterface r12 = (io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxyInterface) r12
            java.lang.String r0 = "deviceid"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00b1
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00aa
            r12.realmSet$deviceid(r2)
            goto L_0x00b1
        L_0x00aa:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$deviceid(r0)
        L_0x00b1:
            java.lang.String r0 = "token"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00ca
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00c3
            r12.realmSet$token(r2)
            goto L_0x00ca
        L_0x00c3:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$token(r0)
        L_0x00ca:
            java.lang.String r0 = "key"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00e3
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00dc
            r12.realmSet$key(r2)
            goto L_0x00e3
        L_0x00dc:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$key(r0)
        L_0x00e3:
            java.lang.String r0 = "user"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x00fc
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x00f5
            r12.realmSet$user(r2)
            goto L_0x00fc
        L_0x00f5:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$user(r0)
        L_0x00fc:
            java.lang.String r0 = "industry"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0115
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x010e
            r12.realmSet$industry(r2)
            goto L_0x0115
        L_0x010e:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$industry(r0)
        L_0x0115:
            java.lang.String r0 = "deviceaddr"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x012e
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0127
            r12.realmSet$deviceaddr(r2)
            goto L_0x012e
        L_0x0127:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$deviceaddr(r0)
        L_0x012e:
            java.lang.String r0 = "version"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0147
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0140
            r12.realmSet$version(r2)
            goto L_0x0147
        L_0x0140:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$version(r0)
        L_0x0147:
            java.lang.String r0 = "devicetype"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0160
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0159
            r12.realmSet$devicetype(r2)
            goto L_0x0160
        L_0x0159:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$devicetype(r0)
        L_0x0160:
            java.lang.String r0 = "reporttimestamp"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x017e
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x0176
            long r0 = r13.getLong(r0)
            r12.realmSet$reporttimestamp(r0)
            goto L_0x017e
        L_0x0176:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'reporttimestamp' to null."
            r12.<init>(r13)
            throw r12
        L_0x017e:
            java.lang.String r0 = "eventname"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x0197
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x0190
            r12.realmSet$eventname(r2)
            goto L_0x0197
        L_0x0190:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$eventname(r0)
        L_0x0197:
            java.lang.String r0 = "eventtype"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01b0
            boolean r1 = r13.isNull(r0)
            if (r1 == 0) goto L_0x01a9
            r12.realmSet$eventtype(r2)
            goto L_0x01b0
        L_0x01a9:
            java.lang.String r0 = r13.getString(r0)
            r12.realmSet$eventtype(r0)
        L_0x01b0:
            java.lang.String r0 = "duration"
            boolean r1 = r13.has(r0)
            if (r1 == 0) goto L_0x01ce
            boolean r1 = r13.isNull(r0)
            if (r1 != 0) goto L_0x01c6
            long r0 = r13.getLong(r0)
            r12.realmSet$duration(r0)
            goto L_0x01ce
        L_0x01c6:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Trying to set non-nullable field 'duration' to null."
            r12.<init>(r13)
            throw r12
        L_0x01ce:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.EventTrackingBean");
    }

    public static EventTrackingBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        EventTrackingBean eventTrackingBean = new EventTrackingBean();
        com_ciot_realm_db_EventTrackingBeanRealmProxyInterface com_ciot_realm_db_eventtrackingbeanrealmproxyinterface = eventTrackingBean;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("deviceid")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$deviceid(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$deviceid((String) null);
                }
            } else if (nextName.equals("token")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$token(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$token((String) null);
                }
            } else if (nextName.equals("key")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$key(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$key((String) null);
                }
            } else if (nextName.equals("user")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$user(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$user((String) null);
                }
            } else if (nextName.equals("industry")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$industry(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$industry((String) null);
                }
            } else if (nextName.equals("deviceaddr")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$deviceaddr(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$deviceaddr((String) null);
                }
            } else if (nextName.equals("version")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$version(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$version((String) null);
                }
            } else if (nextName.equals("devicetype")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$devicetype(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$devicetype((String) null);
                }
            } else if (nextName.equals("reporttimestamp")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$reporttimestamp(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'reporttimestamp' to null.");
                }
            } else if (nextName.equals("eventname")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$eventname(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$eventname((String) null);
                }
            } else if (nextName.equals("eventtype")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$eventtype(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$eventtype((String) null);
                }
            } else if (nextName.equals("duration")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$duration(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'duration' to null.");
                }
            } else if (!nextName.equals("begintimestamp")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$begintimestamp(jsonReader.nextLong());
                z = true;
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'begintimestamp' to null.");
            }
        }
        jsonReader.endObject();
        if (z) {
            return (EventTrackingBean) realm.copyToRealm(eventTrackingBean, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'begintimestamp'.");
    }

    private static com_ciot_realm_db_EventTrackingBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) EventTrackingBean.class), false, Collections.emptyList());
        com_ciot_realm_db_EventTrackingBeanRealmProxy com_ciot_realm_db_eventtrackingbeanrealmproxy = new com_ciot_realm_db_EventTrackingBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_eventtrackingbeanrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.EventTrackingBean copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxy.EventTrackingBeanColumnInfo r9, com.ciot.realm.db.EventTrackingBean r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.EventTrackingBean r1 = (com.ciot.realm.db.EventTrackingBean) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0092
            java.lang.Class<com.ciot.realm.db.EventTrackingBean> r2 = com.ciot.realm.db.EventTrackingBean.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.begintimestampColKey
            r5 = r10
            io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxyInterface) r5
            long r5 = r5.realmGet$begintimestamp()
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
            io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxy r1 = new io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxy     // Catch:{ all -> 0x008d }
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
            com.ciot.realm.db.EventTrackingBean r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00a4
        L_0x00a0:
            com.ciot.realm.db.EventTrackingBean r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00a4:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_EventTrackingBeanRealmProxy$EventTrackingBeanColumnInfo, com.ciot.realm.db.EventTrackingBean, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.EventTrackingBean");
    }

    public static EventTrackingBean copy(Realm realm, EventTrackingBeanColumnInfo eventTrackingBeanColumnInfo, EventTrackingBean eventTrackingBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(eventTrackingBean);
        if (realmObjectProxy != null) {
            return (EventTrackingBean) realmObjectProxy;
        }
        com_ciot_realm_db_EventTrackingBeanRealmProxyInterface com_ciot_realm_db_eventtrackingbeanrealmproxyinterface = eventTrackingBean;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(EventTrackingBean.class), set);
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.deviceidColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$deviceid());
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.tokenColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$token());
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.keyColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$key());
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.userColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$user());
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.industryColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$industry());
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.deviceaddrColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$deviceaddr());
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.versionColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$version());
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.devicetypeColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$devicetype());
        osObjectBuilder.addInteger(eventTrackingBeanColumnInfo.reporttimestampColKey, Long.valueOf(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$reporttimestamp()));
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.eventnameColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$eventname());
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.eventtypeColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$eventtype());
        osObjectBuilder.addInteger(eventTrackingBeanColumnInfo.durationColKey, Long.valueOf(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$duration()));
        osObjectBuilder.addInteger(eventTrackingBeanColumnInfo.begintimestampColKey, Long.valueOf(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$begintimestamp()));
        com_ciot_realm_db_EventTrackingBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(eventTrackingBean, newProxyInstance);
        return newProxyInstance;
    }

    public static long insert(Realm realm, EventTrackingBean eventTrackingBean, Map<RealmModel, Long> map) {
        long j;
        EventTrackingBean eventTrackingBean2 = eventTrackingBean;
        if ((eventTrackingBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(eventTrackingBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) eventTrackingBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(EventTrackingBean.class);
        long nativePtr = table.getNativePtr();
        EventTrackingBeanColumnInfo eventTrackingBeanColumnInfo = (EventTrackingBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EventTrackingBean.class);
        long j2 = eventTrackingBeanColumnInfo.begintimestampColKey;
        com_ciot_realm_db_EventTrackingBeanRealmProxyInterface com_ciot_realm_db_eventtrackingbeanrealmproxyinterface = eventTrackingBean2;
        Long valueOf = Long.valueOf(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$begintimestamp());
        if (valueOf != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$begintimestamp());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$begintimestamp()));
        } else {
            Table.throwDuplicatePrimaryKeyException(valueOf);
        }
        long j3 = j;
        map.put(eventTrackingBean2, Long.valueOf(j3));
        String realmGet$deviceid = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$deviceid();
        if (realmGet$deviceid != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.deviceidColKey, j3, realmGet$deviceid, false);
        }
        String realmGet$token = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$token();
        if (realmGet$token != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.tokenColKey, j3, realmGet$token, false);
        }
        String realmGet$key = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$key();
        if (realmGet$key != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.keyColKey, j3, realmGet$key, false);
        }
        String realmGet$user = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$user();
        if (realmGet$user != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.userColKey, j3, realmGet$user, false);
        }
        String realmGet$industry = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$industry();
        if (realmGet$industry != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.industryColKey, j3, realmGet$industry, false);
        }
        String realmGet$deviceaddr = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$deviceaddr();
        if (realmGet$deviceaddr != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.deviceaddrColKey, j3, realmGet$deviceaddr, false);
        }
        String realmGet$version = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$version();
        if (realmGet$version != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.versionColKey, j3, realmGet$version, false);
        }
        String realmGet$devicetype = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$devicetype();
        if (realmGet$devicetype != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.devicetypeColKey, j3, realmGet$devicetype, false);
        }
        Table.nativeSetLong(nativePtr, eventTrackingBeanColumnInfo.reporttimestampColKey, j3, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$reporttimestamp(), false);
        String realmGet$eventname = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$eventname();
        if (realmGet$eventname != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.eventnameColKey, j3, realmGet$eventname, false);
        }
        String realmGet$eventtype = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$eventtype();
        if (realmGet$eventtype != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.eventtypeColKey, j3, realmGet$eventtype, false);
        }
        Table.nativeSetLong(nativePtr, eventTrackingBeanColumnInfo.durationColKey, j3, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$duration(), false);
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(EventTrackingBean.class);
        long nativePtr = table.getNativePtr();
        EventTrackingBeanColumnInfo eventTrackingBeanColumnInfo = (EventTrackingBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EventTrackingBean.class);
        long j3 = eventTrackingBeanColumnInfo.begintimestampColKey;
        while (it.hasNext()) {
            EventTrackingBean eventTrackingBean = (EventTrackingBean) it.next();
            if (!map2.containsKey(eventTrackingBean)) {
                if ((eventTrackingBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(eventTrackingBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) eventTrackingBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(eventTrackingBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_EventTrackingBeanRealmProxyInterface com_ciot_realm_db_eventtrackingbeanrealmproxyinterface = eventTrackingBean;
                Long valueOf = Long.valueOf(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$begintimestamp());
                if (valueOf != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j3, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$begintimestamp());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j3, Long.valueOf(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$begintimestamp()));
                } else {
                    Table.throwDuplicatePrimaryKeyException(valueOf);
                }
                long j4 = j;
                map2.put(eventTrackingBean, Long.valueOf(j4));
                String realmGet$deviceid = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$deviceid();
                if (realmGet$deviceid != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.deviceidColKey, j4, realmGet$deviceid, false);
                } else {
                    j2 = j3;
                }
                String realmGet$token = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$token();
                if (realmGet$token != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.tokenColKey, j4, realmGet$token, false);
                }
                String realmGet$key = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$key();
                if (realmGet$key != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.keyColKey, j4, realmGet$key, false);
                }
                String realmGet$user = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$user();
                if (realmGet$user != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.userColKey, j4, realmGet$user, false);
                }
                String realmGet$industry = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$industry();
                if (realmGet$industry != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.industryColKey, j4, realmGet$industry, false);
                }
                String realmGet$deviceaddr = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$deviceaddr();
                if (realmGet$deviceaddr != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.deviceaddrColKey, j4, realmGet$deviceaddr, false);
                }
                String realmGet$version = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$version();
                if (realmGet$version != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.versionColKey, j4, realmGet$version, false);
                }
                String realmGet$devicetype = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$devicetype();
                if (realmGet$devicetype != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.devicetypeColKey, j4, realmGet$devicetype, false);
                }
                Table.nativeSetLong(nativePtr, eventTrackingBeanColumnInfo.reporttimestampColKey, j4, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$reporttimestamp(), false);
                String realmGet$eventname = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$eventname();
                if (realmGet$eventname != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.eventnameColKey, j4, realmGet$eventname, false);
                }
                String realmGet$eventtype = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$eventtype();
                if (realmGet$eventtype != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.eventtypeColKey, j4, realmGet$eventtype, false);
                }
                Table.nativeSetLong(nativePtr, eventTrackingBeanColumnInfo.durationColKey, j4, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$duration(), false);
                j3 = j2;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, EventTrackingBean eventTrackingBean, Map<RealmModel, Long> map) {
        long j;
        EventTrackingBean eventTrackingBean2 = eventTrackingBean;
        if ((eventTrackingBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(eventTrackingBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) eventTrackingBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm.getTable(EventTrackingBean.class);
        long nativePtr = table.getNativePtr();
        EventTrackingBeanColumnInfo eventTrackingBeanColumnInfo = (EventTrackingBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EventTrackingBean.class);
        long j2 = eventTrackingBeanColumnInfo.begintimestampColKey;
        com_ciot_realm_db_EventTrackingBeanRealmProxyInterface com_ciot_realm_db_eventtrackingbeanrealmproxyinterface = eventTrackingBean2;
        if (Long.valueOf(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$begintimestamp()) != null) {
            j = Table.nativeFindFirstInt(nativePtr, j2, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$begintimestamp());
        } else {
            j = -1;
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, Long.valueOf(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$begintimestamp()));
        }
        long j3 = j;
        map.put(eventTrackingBean2, Long.valueOf(j3));
        String realmGet$deviceid = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$deviceid();
        if (realmGet$deviceid != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.deviceidColKey, j3, realmGet$deviceid, false);
        } else {
            Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.deviceidColKey, j3, false);
        }
        String realmGet$token = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$token();
        if (realmGet$token != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.tokenColKey, j3, realmGet$token, false);
        } else {
            Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.tokenColKey, j3, false);
        }
        String realmGet$key = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$key();
        if (realmGet$key != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.keyColKey, j3, realmGet$key, false);
        } else {
            Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.keyColKey, j3, false);
        }
        String realmGet$user = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$user();
        if (realmGet$user != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.userColKey, j3, realmGet$user, false);
        } else {
            Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.userColKey, j3, false);
        }
        String realmGet$industry = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$industry();
        if (realmGet$industry != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.industryColKey, j3, realmGet$industry, false);
        } else {
            Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.industryColKey, j3, false);
        }
        String realmGet$deviceaddr = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$deviceaddr();
        if (realmGet$deviceaddr != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.deviceaddrColKey, j3, realmGet$deviceaddr, false);
        } else {
            Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.deviceaddrColKey, j3, false);
        }
        String realmGet$version = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$version();
        if (realmGet$version != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.versionColKey, j3, realmGet$version, false);
        } else {
            Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.versionColKey, j3, false);
        }
        String realmGet$devicetype = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$devicetype();
        if (realmGet$devicetype != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.devicetypeColKey, j3, realmGet$devicetype, false);
        } else {
            Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.devicetypeColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, eventTrackingBeanColumnInfo.reporttimestampColKey, j3, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$reporttimestamp(), false);
        String realmGet$eventname = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$eventname();
        if (realmGet$eventname != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.eventnameColKey, j3, realmGet$eventname, false);
        } else {
            Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.eventnameColKey, j3, false);
        }
        String realmGet$eventtype = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$eventtype();
        if (realmGet$eventtype != null) {
            Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.eventtypeColKey, j3, realmGet$eventtype, false);
        } else {
            Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.eventtypeColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, eventTrackingBeanColumnInfo.durationColKey, j3, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$duration(), false);
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Map<RealmModel, Long> map2 = map;
        Table table = realm.getTable(EventTrackingBean.class);
        long nativePtr = table.getNativePtr();
        EventTrackingBeanColumnInfo eventTrackingBeanColumnInfo = (EventTrackingBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) EventTrackingBean.class);
        long j3 = eventTrackingBeanColumnInfo.begintimestampColKey;
        while (it.hasNext()) {
            EventTrackingBean eventTrackingBean = (EventTrackingBean) it.next();
            if (!map2.containsKey(eventTrackingBean)) {
                if ((eventTrackingBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(eventTrackingBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) eventTrackingBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(eventTrackingBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_EventTrackingBeanRealmProxyInterface com_ciot_realm_db_eventtrackingbeanrealmproxyinterface = eventTrackingBean;
                if (Long.valueOf(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$begintimestamp()) != null) {
                    j = Table.nativeFindFirstInt(nativePtr, j3, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$begintimestamp());
                } else {
                    j = -1;
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j3, Long.valueOf(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$begintimestamp()));
                }
                long j4 = j;
                map2.put(eventTrackingBean, Long.valueOf(j4));
                String realmGet$deviceid = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$deviceid();
                if (realmGet$deviceid != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.deviceidColKey, j4, realmGet$deviceid, false);
                } else {
                    j2 = j3;
                    Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.deviceidColKey, j4, false);
                }
                String realmGet$token = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$token();
                if (realmGet$token != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.tokenColKey, j4, realmGet$token, false);
                } else {
                    Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.tokenColKey, j4, false);
                }
                String realmGet$key = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$key();
                if (realmGet$key != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.keyColKey, j4, realmGet$key, false);
                } else {
                    Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.keyColKey, j4, false);
                }
                String realmGet$user = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$user();
                if (realmGet$user != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.userColKey, j4, realmGet$user, false);
                } else {
                    Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.userColKey, j4, false);
                }
                String realmGet$industry = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$industry();
                if (realmGet$industry != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.industryColKey, j4, realmGet$industry, false);
                } else {
                    Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.industryColKey, j4, false);
                }
                String realmGet$deviceaddr = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$deviceaddr();
                if (realmGet$deviceaddr != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.deviceaddrColKey, j4, realmGet$deviceaddr, false);
                } else {
                    Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.deviceaddrColKey, j4, false);
                }
                String realmGet$version = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$version();
                if (realmGet$version != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.versionColKey, j4, realmGet$version, false);
                } else {
                    Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.versionColKey, j4, false);
                }
                String realmGet$devicetype = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$devicetype();
                if (realmGet$devicetype != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.devicetypeColKey, j4, realmGet$devicetype, false);
                } else {
                    Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.devicetypeColKey, j4, false);
                }
                Table.nativeSetLong(nativePtr, eventTrackingBeanColumnInfo.reporttimestampColKey, j4, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$reporttimestamp(), false);
                String realmGet$eventname = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$eventname();
                if (realmGet$eventname != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.eventnameColKey, j4, realmGet$eventname, false);
                } else {
                    Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.eventnameColKey, j4, false);
                }
                String realmGet$eventtype = com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$eventtype();
                if (realmGet$eventtype != null) {
                    Table.nativeSetString(nativePtr, eventTrackingBeanColumnInfo.eventtypeColKey, j4, realmGet$eventtype, false);
                } else {
                    Table.nativeSetNull(nativePtr, eventTrackingBeanColumnInfo.eventtypeColKey, j4, false);
                }
                Table.nativeSetLong(nativePtr, eventTrackingBeanColumnInfo.durationColKey, j4, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmGet$duration(), false);
                j3 = j2;
            }
        }
    }

    public static EventTrackingBean createDetachedCopy(EventTrackingBean eventTrackingBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        EventTrackingBean eventTrackingBean2;
        if (i > i2 || eventTrackingBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(eventTrackingBean);
        if (cacheData == null) {
            eventTrackingBean2 = new EventTrackingBean();
            map.put(eventTrackingBean, new RealmObjectProxy.CacheData(i, eventTrackingBean2));
        } else if (i >= cacheData.minDepth) {
            return (EventTrackingBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            eventTrackingBean2 = (EventTrackingBean) cacheData.object;
        }
        com_ciot_realm_db_EventTrackingBeanRealmProxyInterface com_ciot_realm_db_eventtrackingbeanrealmproxyinterface = eventTrackingBean2;
        com_ciot_realm_db_EventTrackingBeanRealmProxyInterface com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2 = eventTrackingBean;
        com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$deviceid(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$deviceid());
        com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$token(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$token());
        com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$key(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$key());
        com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$user(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$user());
        com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$industry(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$industry());
        com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$deviceaddr(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$deviceaddr());
        com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$version(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$version());
        com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$devicetype(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$devicetype());
        com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$reporttimestamp(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$reporttimestamp());
        com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$eventname(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$eventname());
        com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$eventtype(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$eventtype());
        com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$duration(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$duration());
        com_ciot_realm_db_eventtrackingbeanrealmproxyinterface.realmSet$begintimestamp(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$begintimestamp());
        return eventTrackingBean2;
    }

    static EventTrackingBean update(Realm realm, EventTrackingBeanColumnInfo eventTrackingBeanColumnInfo, EventTrackingBean eventTrackingBean, EventTrackingBean eventTrackingBean2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_EventTrackingBeanRealmProxyInterface com_ciot_realm_db_eventtrackingbeanrealmproxyinterface = eventTrackingBean;
        com_ciot_realm_db_EventTrackingBeanRealmProxyInterface com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2 = eventTrackingBean2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(EventTrackingBean.class), set);
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.deviceidColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$deviceid());
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.tokenColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$token());
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.keyColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$key());
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.userColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$user());
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.industryColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$industry());
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.deviceaddrColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$deviceaddr());
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.versionColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$version());
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.devicetypeColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$devicetype());
        osObjectBuilder.addInteger(eventTrackingBeanColumnInfo.reporttimestampColKey, Long.valueOf(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$reporttimestamp()));
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.eventnameColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$eventname());
        osObjectBuilder.addString(eventTrackingBeanColumnInfo.eventtypeColKey, com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$eventtype());
        osObjectBuilder.addInteger(eventTrackingBeanColumnInfo.durationColKey, Long.valueOf(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$duration()));
        osObjectBuilder.addInteger(eventTrackingBeanColumnInfo.begintimestampColKey, Long.valueOf(com_ciot_realm_db_eventtrackingbeanrealmproxyinterface2.realmGet$begintimestamp()));
        osObjectBuilder.updateExistingObject();
        return eventTrackingBean;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("EventTrackingBean = proxy[");
        sb.append("{deviceid:");
        String realmGet$deviceid = realmGet$deviceid();
        String str = Configurator.NULL;
        sb.append(realmGet$deviceid != null ? realmGet$deviceid() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{token:");
        sb.append(realmGet$token() != null ? realmGet$token() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{key:");
        sb.append(realmGet$key() != null ? realmGet$key() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{user:");
        sb.append(realmGet$user() != null ? realmGet$user() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{industry:");
        sb.append(realmGet$industry() != null ? realmGet$industry() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{deviceaddr:");
        sb.append(realmGet$deviceaddr() != null ? realmGet$deviceaddr() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{version:");
        sb.append(realmGet$version() != null ? realmGet$version() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{devicetype:");
        sb.append(realmGet$devicetype() != null ? realmGet$devicetype() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{reporttimestamp:");
        sb.append(realmGet$reporttimestamp());
        sb.append("}");
        sb.append(",");
        sb.append("{eventname:");
        sb.append(realmGet$eventname() != null ? realmGet$eventname() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{eventtype:");
        if (realmGet$eventtype() != null) {
            str = realmGet$eventtype();
        }
        sb.append(str);
        sb.append("}");
        sb.append(",");
        sb.append("{duration:");
        sb.append(realmGet$duration());
        sb.append("}");
        sb.append(",");
        sb.append("{begintimestamp:");
        sb.append(realmGet$begintimestamp());
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
        com_ciot_realm_db_EventTrackingBeanRealmProxy com_ciot_realm_db_eventtrackingbeanrealmproxy = (com_ciot_realm_db_EventTrackingBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_eventtrackingbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_eventtrackingbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_eventtrackingbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
