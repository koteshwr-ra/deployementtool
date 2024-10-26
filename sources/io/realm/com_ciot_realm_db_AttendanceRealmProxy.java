package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import androidx.core.app.NotificationCompat;
import com.ciot.realm.db.Attendance;
import com.ciot.realm.db.RobotBean;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_RobotBeanRealmProxy;
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

public class com_ciot_realm_db_AttendanceRealmProxy extends Attendance implements RealmObjectProxy, com_ciot_realm_db_AttendanceRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private AttendanceColumnInfo columnInfo;
    private ProxyState<Attendance> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Attendance";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class AttendanceColumnInfo extends ColumnInfo {
        long checkTimeColKey;
        long clockInTimeColKey;
        long companyColKey;
        long departmentColKey;
        long descriptionColKey;
        long employeeIdColKey;
        long employeeNameColKey;
        long idColKey;
        long idcardColKey;
        long isCommitColKey;
        long robotColKey;
        long staffnoColKey;
        long statusColKey;
        long temperatureColKey;

        AttendanceColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(14);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.employeeIdColKey = addColumnDetails("employeeId", "employeeId", objectSchemaInfo);
            this.staffnoColKey = addColumnDetails("staffno", "staffno", objectSchemaInfo);
            this.companyColKey = addColumnDetails("company", "company", objectSchemaInfo);
            this.departmentColKey = addColumnDetails("department", "department", objectSchemaInfo);
            this.employeeNameColKey = addColumnDetails("employeeName", "employeeName", objectSchemaInfo);
            this.robotColKey = addColumnDetails("robot", "robot", objectSchemaInfo);
            this.checkTimeColKey = addColumnDetails("checkTime", "checkTime", objectSchemaInfo);
            this.temperatureColKey = addColumnDetails("temperature", "temperature", objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
            this.idcardColKey = addColumnDetails("idcard", "idcard", objectSchemaInfo);
            this.clockInTimeColKey = addColumnDetails("clockInTime", "clockInTime", objectSchemaInfo);
            this.statusColKey = addColumnDetails(NotificationCompat.CATEGORY_STATUS, NotificationCompat.CATEGORY_STATUS, objectSchemaInfo);
            this.isCommitColKey = addColumnDetails("isCommit", "isCommit", objectSchemaInfo);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
        }

        AttendanceColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new AttendanceColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            AttendanceColumnInfo attendanceColumnInfo = (AttendanceColumnInfo) columnInfo;
            AttendanceColumnInfo attendanceColumnInfo2 = (AttendanceColumnInfo) columnInfo2;
            attendanceColumnInfo2.employeeIdColKey = attendanceColumnInfo.employeeIdColKey;
            attendanceColumnInfo2.staffnoColKey = attendanceColumnInfo.staffnoColKey;
            attendanceColumnInfo2.companyColKey = attendanceColumnInfo.companyColKey;
            attendanceColumnInfo2.departmentColKey = attendanceColumnInfo.departmentColKey;
            attendanceColumnInfo2.employeeNameColKey = attendanceColumnInfo.employeeNameColKey;
            attendanceColumnInfo2.robotColKey = attendanceColumnInfo.robotColKey;
            attendanceColumnInfo2.checkTimeColKey = attendanceColumnInfo.checkTimeColKey;
            attendanceColumnInfo2.temperatureColKey = attendanceColumnInfo.temperatureColKey;
            attendanceColumnInfo2.descriptionColKey = attendanceColumnInfo.descriptionColKey;
            attendanceColumnInfo2.idcardColKey = attendanceColumnInfo.idcardColKey;
            attendanceColumnInfo2.clockInTimeColKey = attendanceColumnInfo.clockInTimeColKey;
            attendanceColumnInfo2.statusColKey = attendanceColumnInfo.statusColKey;
            attendanceColumnInfo2.isCommitColKey = attendanceColumnInfo.isCommitColKey;
            attendanceColumnInfo2.idColKey = attendanceColumnInfo.idColKey;
        }
    }

    com_ciot_realm_db_AttendanceRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (AttendanceColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<Attendance> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$employeeId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.employeeIdColKey);
    }

    public void realmSet$employeeId(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.employeeIdColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.employeeIdColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.employeeIdColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.employeeIdColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$staffno() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.staffnoColKey);
    }

    public void realmSet$staffno(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.staffnoColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.staffnoColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.staffnoColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.staffnoColKey, row$realm.getObjectKey(), str, true);
            }
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

    public String realmGet$department() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.departmentColKey);
    }

    public void realmSet$department(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.departmentColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.departmentColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.departmentColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.departmentColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$employeeName() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.employeeNameColKey);
    }

    public void realmSet$employeeName(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.employeeNameColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.employeeNameColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.employeeNameColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.employeeNameColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public RobotBean realmGet$robot() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.robotColKey)) {
            return null;
        }
        return (RobotBean) this.proxyState.getRealm$realm().get(RobotBean.class, this.proxyState.getRow$realm().getLink(this.columnInfo.robotColKey), false, Collections.emptyList());
    }

    public void realmSet$robot(RobotBean robotBean) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (robotBean == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.robotColKey);
                return;
            }
            this.proxyState.checkValidObject(robotBean);
            this.proxyState.getRow$realm().setLink(this.columnInfo.robotColKey, ((RealmObjectProxy) robotBean).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("robot")) {
            if (robotBean != null && !RealmObject.isManaged(robotBean)) {
                robotBean = (RobotBean) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(robotBean, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (robotBean == null) {
                row$realm.nullifyLink(this.columnInfo.robotColKey);
                return;
            }
            this.proxyState.checkValidObject(robotBean);
            row$realm.getTable().setLink(this.columnInfo.robotColKey, row$realm.getObjectKey(), ((RealmObjectProxy) robotBean).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public long realmGet$checkTime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.checkTimeColKey);
    }

    public void realmSet$checkTime(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.checkTimeColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.checkTimeColKey, row$realm.getObjectKey(), j, true);
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

    public String realmGet$idcard() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.idcardColKey);
    }

    public void realmSet$idcard(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.idcardColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.idcardColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.idcardColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.idcardColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$clockInTime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.clockInTimeColKey);
    }

    public void realmSet$clockInTime(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.clockInTimeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.clockInTimeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.clockInTimeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.clockInTimeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public int realmGet$status() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.statusColKey);
    }

    public void realmSet$status(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.statusColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.statusColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public boolean realmGet$isCommit() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isCommitColKey);
    }

    public void realmSet$isCommit(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isCommitColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.isCommitColKey, row$realm.getObjectKey(), z, true);
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
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 14, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("employeeId", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("staffno", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("company", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("department", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("employeeName", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("robot", RealmFieldType.OBJECT, com_ciot_realm_db_RobotBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder3 = builder;
        builder3.addPersistedProperty("checkTime", RealmFieldType.INTEGER, false, false, true);
        builder3.addPersistedProperty("temperature", RealmFieldType.FLOAT, false, false, true);
        builder3.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        builder3.addPersistedProperty("idcard", RealmFieldType.STRING, false, false, false);
        builder3.addPersistedProperty("clockInTime", RealmFieldType.STRING, false, false, false);
        builder3.addPersistedProperty(NotificationCompat.CATEGORY_STATUS, RealmFieldType.INTEGER, false, false, true);
        builder3.addPersistedProperty("isCommit", RealmFieldType.BOOLEAN, false, false, true);
        builder3.addPersistedProperty("id", RealmFieldType.STRING, true, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static AttendanceColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new AttendanceColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: Removed duplicated region for block: B:106:0x01ec  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0183  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01b5  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x01ce  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.Attendance createOrUpdateUsingJsonObject(io.realm.Realm r14, org.json.JSONObject r15, boolean r16) throws org.json.JSONException {
        /*
            r0 = r14
            r7 = r15
            r8 = r16
            java.util.ArrayList r9 = new java.util.ArrayList
            r10 = 1
            r9.<init>(r10)
            java.lang.String r11 = "id"
            r12 = 0
            if (r8 == 0) goto L_0x006b
            java.lang.Class<com.ciot.realm.db.Attendance> r1 = com.ciot.realm.db.Attendance.class
            io.realm.internal.Table r1 = r14.getTable(r1)
            io.realm.RealmSchema r2 = r14.getSchema()
            java.lang.Class<com.ciot.realm.db.Attendance> r3 = com.ciot.realm.db.Attendance.class
            io.realm.internal.ColumnInfo r2 = r2.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r3)
            io.realm.com_ciot_realm_db_AttendanceRealmProxy$AttendanceColumnInfo r2 = (io.realm.com_ciot_realm_db_AttendanceRealmProxy.AttendanceColumnInfo) r2
            long r2 = r2.idColKey
            boolean r4 = r15.isNull(r11)
            if (r4 == 0) goto L_0x002e
            long r2 = r1.findFirstNull(r2)
            goto L_0x0036
        L_0x002e:
            java.lang.String r4 = r15.getString(r11)
            long r2 = r1.findFirstString(r2, r4)
        L_0x0036:
            r4 = -1
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x006b
            io.realm.BaseRealm$ThreadLocalRealmObjectContext r4 = io.realm.BaseRealm.objectContext
            java.lang.Object r4 = r4.get()
            r13 = r4
            io.realm.BaseRealm$RealmObjectContext r13 = (io.realm.BaseRealm.RealmObjectContext) r13
            io.realm.internal.UncheckedRow r3 = r1.getUncheckedRow(r2)     // Catch:{ all -> 0x0066 }
            io.realm.RealmSchema r1 = r14.getSchema()     // Catch:{ all -> 0x0066 }
            java.lang.Class<com.ciot.realm.db.Attendance> r2 = com.ciot.realm.db.Attendance.class
            io.realm.internal.ColumnInfo r4 = r1.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r2)     // Catch:{ all -> 0x0066 }
            r5 = 0
            java.util.List r6 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0066 }
            r1 = r13
            r2 = r14
            r1.set(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0066 }
            io.realm.com_ciot_realm_db_AttendanceRealmProxy r1 = new io.realm.com_ciot_realm_db_AttendanceRealmProxy     // Catch:{ all -> 0x0066 }
            r1.<init>()     // Catch:{ all -> 0x0066 }
            r13.clear()
            goto L_0x006c
        L_0x0066:
            r0 = move-exception
            r13.clear()
            throw r0
        L_0x006b:
            r1 = r12
        L_0x006c:
            java.lang.String r2 = "robot"
            if (r1 != 0) goto L_0x00a3
            boolean r1 = r15.has(r2)
            if (r1 == 0) goto L_0x0079
            r9.add(r2)
        L_0x0079:
            boolean r1 = r15.has(r11)
            if (r1 == 0) goto L_0x009b
            boolean r1 = r15.isNull(r11)
            if (r1 == 0) goto L_0x008e
            java.lang.Class<com.ciot.realm.db.Attendance> r1 = com.ciot.realm.db.Attendance.class
            io.realm.RealmModel r1 = r14.createObjectInternal(r1, r12, r10, r9)
            io.realm.com_ciot_realm_db_AttendanceRealmProxy r1 = (io.realm.com_ciot_realm_db_AttendanceRealmProxy) r1
            goto L_0x00a3
        L_0x008e:
            java.lang.Class<com.ciot.realm.db.Attendance> r1 = com.ciot.realm.db.Attendance.class
            java.lang.String r3 = r15.getString(r11)
            io.realm.RealmModel r1 = r14.createObjectInternal(r1, r3, r10, r9)
            io.realm.com_ciot_realm_db_AttendanceRealmProxy r1 = (io.realm.com_ciot_realm_db_AttendanceRealmProxy) r1
            goto L_0x00a3
        L_0x009b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "JSON object doesn't have the primary key field 'id'."
            r0.<init>(r1)
            throw r0
        L_0x00a3:
            r3 = r1
            io.realm.com_ciot_realm_db_AttendanceRealmProxyInterface r3 = (io.realm.com_ciot_realm_db_AttendanceRealmProxyInterface) r3
            java.lang.String r4 = "employeeId"
            boolean r5 = r15.has(r4)
            if (r5 == 0) goto L_0x00bf
            boolean r5 = r15.isNull(r4)
            if (r5 == 0) goto L_0x00b8
            r3.realmSet$employeeId(r12)
            goto L_0x00bf
        L_0x00b8:
            java.lang.String r4 = r15.getString(r4)
            r3.realmSet$employeeId(r4)
        L_0x00bf:
            java.lang.String r4 = "staffno"
            boolean r5 = r15.has(r4)
            if (r5 == 0) goto L_0x00d8
            boolean r5 = r15.isNull(r4)
            if (r5 == 0) goto L_0x00d1
            r3.realmSet$staffno(r12)
            goto L_0x00d8
        L_0x00d1:
            java.lang.String r4 = r15.getString(r4)
            r3.realmSet$staffno(r4)
        L_0x00d8:
            java.lang.String r4 = "company"
            boolean r5 = r15.has(r4)
            if (r5 == 0) goto L_0x00f1
            boolean r5 = r15.isNull(r4)
            if (r5 == 0) goto L_0x00ea
            r3.realmSet$company(r12)
            goto L_0x00f1
        L_0x00ea:
            java.lang.String r4 = r15.getString(r4)
            r3.realmSet$company(r4)
        L_0x00f1:
            java.lang.String r4 = "department"
            boolean r5 = r15.has(r4)
            if (r5 == 0) goto L_0x010a
            boolean r5 = r15.isNull(r4)
            if (r5 == 0) goto L_0x0103
            r3.realmSet$department(r12)
            goto L_0x010a
        L_0x0103:
            java.lang.String r4 = r15.getString(r4)
            r3.realmSet$department(r4)
        L_0x010a:
            java.lang.String r4 = "employeeName"
            boolean r5 = r15.has(r4)
            if (r5 == 0) goto L_0x0123
            boolean r5 = r15.isNull(r4)
            if (r5 == 0) goto L_0x011c
            r3.realmSet$employeeName(r12)
            goto L_0x0123
        L_0x011c:
            java.lang.String r4 = r15.getString(r4)
            r3.realmSet$employeeName(r4)
        L_0x0123:
            boolean r4 = r15.has(r2)
            if (r4 == 0) goto L_0x013e
            boolean r4 = r15.isNull(r2)
            if (r4 == 0) goto L_0x0133
            r3.realmSet$robot(r12)
            goto L_0x013e
        L_0x0133:
            org.json.JSONObject r2 = r15.getJSONObject(r2)
            com.ciot.realm.db.RobotBean r0 = io.realm.com_ciot_realm_db_RobotBeanRealmProxy.createOrUpdateUsingJsonObject(r14, r2, r8)
            r3.realmSet$robot(r0)
        L_0x013e:
            java.lang.String r0 = "checkTime"
            boolean r2 = r15.has(r0)
            if (r2 == 0) goto L_0x015c
            boolean r2 = r15.isNull(r0)
            if (r2 != 0) goto L_0x0154
            long r4 = r15.getLong(r0)
            r3.realmSet$checkTime(r4)
            goto L_0x015c
        L_0x0154:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Trying to set non-nullable field 'checkTime' to null."
            r0.<init>(r1)
            throw r0
        L_0x015c:
            java.lang.String r0 = "temperature"
            boolean r2 = r15.has(r0)
            if (r2 == 0) goto L_0x017b
            boolean r2 = r15.isNull(r0)
            if (r2 != 0) goto L_0x0173
            double r4 = r15.getDouble(r0)
            float r0 = (float) r4
            r3.realmSet$temperature(r0)
            goto L_0x017b
        L_0x0173:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Trying to set non-nullable field 'temperature' to null."
            r0.<init>(r1)
            throw r0
        L_0x017b:
            java.lang.String r0 = "description"
            boolean r2 = r15.has(r0)
            if (r2 == 0) goto L_0x0194
            boolean r2 = r15.isNull(r0)
            if (r2 == 0) goto L_0x018d
            r3.realmSet$description(r12)
            goto L_0x0194
        L_0x018d:
            java.lang.String r0 = r15.getString(r0)
            r3.realmSet$description(r0)
        L_0x0194:
            java.lang.String r0 = "idcard"
            boolean r2 = r15.has(r0)
            if (r2 == 0) goto L_0x01ad
            boolean r2 = r15.isNull(r0)
            if (r2 == 0) goto L_0x01a6
            r3.realmSet$idcard(r12)
            goto L_0x01ad
        L_0x01a6:
            java.lang.String r0 = r15.getString(r0)
            r3.realmSet$idcard(r0)
        L_0x01ad:
            java.lang.String r0 = "clockInTime"
            boolean r2 = r15.has(r0)
            if (r2 == 0) goto L_0x01c6
            boolean r2 = r15.isNull(r0)
            if (r2 == 0) goto L_0x01bf
            r3.realmSet$clockInTime(r12)
            goto L_0x01c6
        L_0x01bf:
            java.lang.String r0 = r15.getString(r0)
            r3.realmSet$clockInTime(r0)
        L_0x01c6:
            java.lang.String r0 = "status"
            boolean r2 = r15.has(r0)
            if (r2 == 0) goto L_0x01e4
            boolean r2 = r15.isNull(r0)
            if (r2 != 0) goto L_0x01dc
            int r0 = r15.getInt(r0)
            r3.realmSet$status(r0)
            goto L_0x01e4
        L_0x01dc:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Trying to set non-nullable field 'status' to null."
            r0.<init>(r1)
            throw r0
        L_0x01e4:
            java.lang.String r0 = "isCommit"
            boolean r2 = r15.has(r0)
            if (r2 == 0) goto L_0x0202
            boolean r2 = r15.isNull(r0)
            if (r2 != 0) goto L_0x01fa
            boolean r0 = r15.getBoolean(r0)
            r3.realmSet$isCommit(r0)
            goto L_0x0202
        L_0x01fa:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Trying to set non-nullable field 'isCommit' to null."
            r0.<init>(r1)
            throw r0
        L_0x0202:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_AttendanceRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.Attendance");
    }

    public static Attendance createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        Attendance attendance = new Attendance();
        com_ciot_realm_db_AttendanceRealmProxyInterface com_ciot_realm_db_attendancerealmproxyinterface = attendance;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("employeeId")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$employeeId(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$employeeId((String) null);
                }
            } else if (nextName.equals("staffno")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$staffno(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$staffno((String) null);
                }
            } else if (nextName.equals("company")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$company(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$company((String) null);
                }
            } else if (nextName.equals("department")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$department(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$department((String) null);
                }
            } else if (nextName.equals("employeeName")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$employeeName(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$employeeName((String) null);
                }
            } else if (nextName.equals("robot")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$robot((RobotBean) null);
                } else {
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$robot(com_ciot_realm_db_RobotBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("checkTime")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$checkTime(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'checkTime' to null.");
                }
            } else if (nextName.equals("temperature")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$temperature((float) jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'temperature' to null.");
                }
            } else if (nextName.equals("description")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$description(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$description((String) null);
                }
            } else if (nextName.equals("idcard")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$idcard(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$idcard((String) null);
                }
            } else if (nextName.equals("clockInTime")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$clockInTime(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$clockInTime((String) null);
                }
            } else if (nextName.equals(NotificationCompat.CATEGORY_STATUS)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$status(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'status' to null.");
                }
            } else if (nextName.equals("isCommit")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$isCommit(jsonReader.nextBoolean());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isCommit' to null.");
                }
            } else if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_attendancerealmproxyinterface.realmSet$id((String) null);
                }
                z = true;
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        if (z) {
            return (Attendance) realm.copyToRealm(attendance, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_AttendanceRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) Attendance.class), false, Collections.emptyList());
        com_ciot_realm_db_AttendanceRealmProxy com_ciot_realm_db_attendancerealmproxy = new com_ciot_realm_db_AttendanceRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_attendancerealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.Attendance copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_AttendanceRealmProxy.AttendanceColumnInfo r9, com.ciot.realm.db.Attendance r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.Attendance r1 = (com.ciot.realm.db.Attendance) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.Attendance> r2 = com.ciot.realm.db.Attendance.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_AttendanceRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_AttendanceRealmProxyInterface) r5
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
            io.realm.com_ciot_realm_db_AttendanceRealmProxy r1 = new io.realm.com_ciot_realm_db_AttendanceRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.Attendance r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.Attendance r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_AttendanceRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_AttendanceRealmProxy$AttendanceColumnInfo, com.ciot.realm.db.Attendance, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.Attendance");
    }

    public static Attendance copy(Realm realm, AttendanceColumnInfo attendanceColumnInfo, Attendance attendance, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(attendance);
        if (realmObjectProxy != null) {
            return (Attendance) realmObjectProxy;
        }
        com_ciot_realm_db_AttendanceRealmProxyInterface com_ciot_realm_db_attendancerealmproxyinterface = attendance;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Attendance.class), set);
        osObjectBuilder.addString(attendanceColumnInfo.employeeIdColKey, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$employeeId());
        osObjectBuilder.addString(attendanceColumnInfo.staffnoColKey, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$staffno());
        osObjectBuilder.addString(attendanceColumnInfo.companyColKey, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$company());
        osObjectBuilder.addString(attendanceColumnInfo.departmentColKey, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$department());
        osObjectBuilder.addString(attendanceColumnInfo.employeeNameColKey, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$employeeName());
        osObjectBuilder.addInteger(attendanceColumnInfo.checkTimeColKey, Long.valueOf(com_ciot_realm_db_attendancerealmproxyinterface.realmGet$checkTime()));
        osObjectBuilder.addFloat(attendanceColumnInfo.temperatureColKey, Float.valueOf(com_ciot_realm_db_attendancerealmproxyinterface.realmGet$temperature()));
        osObjectBuilder.addString(attendanceColumnInfo.descriptionColKey, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$description());
        osObjectBuilder.addString(attendanceColumnInfo.idcardColKey, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$idcard());
        osObjectBuilder.addString(attendanceColumnInfo.clockInTimeColKey, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$clockInTime());
        osObjectBuilder.addInteger(attendanceColumnInfo.statusColKey, Integer.valueOf(com_ciot_realm_db_attendancerealmproxyinterface.realmGet$status()));
        osObjectBuilder.addBoolean(attendanceColumnInfo.isCommitColKey, Boolean.valueOf(com_ciot_realm_db_attendancerealmproxyinterface.realmGet$isCommit()));
        osObjectBuilder.addString(attendanceColumnInfo.idColKey, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$id());
        com_ciot_realm_db_AttendanceRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(attendance, newProxyInstance);
        RobotBean realmGet$robot = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$robot();
        if (realmGet$robot == null) {
            newProxyInstance.realmSet$robot((RobotBean) null);
        } else {
            RobotBean robotBean = (RobotBean) map.get(realmGet$robot);
            if (robotBean != null) {
                newProxyInstance.realmSet$robot(robotBean);
            } else {
                newProxyInstance.realmSet$robot(com_ciot_realm_db_RobotBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_RobotBeanRealmProxy.RobotBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RobotBean.class), realmGet$robot, z, map, set));
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, Attendance attendance, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        Attendance attendance2 = attendance;
        Map<RealmModel, Long> map2 = map;
        if ((attendance2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(attendance)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) attendance2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(Attendance.class);
        long nativePtr = table.getNativePtr();
        AttendanceColumnInfo attendanceColumnInfo = (AttendanceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Attendance.class);
        long j2 = attendanceColumnInfo.idColKey;
        com_ciot_realm_db_AttendanceRealmProxyInterface com_ciot_realm_db_attendancerealmproxyinterface = attendance2;
        String realmGet$id = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$id();
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
        map2.put(attendance2, Long.valueOf(j3));
        String realmGet$employeeId = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$employeeId();
        if (realmGet$employeeId != null) {
            Table.nativeSetString(nativePtr, attendanceColumnInfo.employeeIdColKey, j3, realmGet$employeeId, false);
        }
        String realmGet$staffno = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$staffno();
        if (realmGet$staffno != null) {
            Table.nativeSetString(nativePtr, attendanceColumnInfo.staffnoColKey, j3, realmGet$staffno, false);
        }
        String realmGet$company = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$company();
        if (realmGet$company != null) {
            Table.nativeSetString(nativePtr, attendanceColumnInfo.companyColKey, j3, realmGet$company, false);
        }
        String realmGet$department = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$department();
        if (realmGet$department != null) {
            Table.nativeSetString(nativePtr, attendanceColumnInfo.departmentColKey, j3, realmGet$department, false);
        }
        String realmGet$employeeName = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$employeeName();
        if (realmGet$employeeName != null) {
            Table.nativeSetString(nativePtr, attendanceColumnInfo.employeeNameColKey, j3, realmGet$employeeName, false);
        }
        RobotBean realmGet$robot = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$robot();
        if (realmGet$robot != null) {
            Long l = map2.get(realmGet$robot);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_RobotBeanRealmProxy.insert(realm2, realmGet$robot, map2));
            }
            Table.nativeSetLink(nativePtr, attendanceColumnInfo.robotColKey, j3, l.longValue(), false);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetLong(j4, attendanceColumnInfo.checkTimeColKey, j5, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$checkTime(), false);
        Table.nativeSetFloat(j4, attendanceColumnInfo.temperatureColKey, j5, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$temperature(), false);
        String realmGet$description = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, attendanceColumnInfo.descriptionColKey, j3, realmGet$description, false);
        }
        String realmGet$idcard = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$idcard();
        if (realmGet$idcard != null) {
            Table.nativeSetString(nativePtr, attendanceColumnInfo.idcardColKey, j3, realmGet$idcard, false);
        }
        String realmGet$clockInTime = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$clockInTime();
        if (realmGet$clockInTime != null) {
            Table.nativeSetString(nativePtr, attendanceColumnInfo.clockInTimeColKey, j3, realmGet$clockInTime, false);
        }
        long j6 = nativePtr;
        long j7 = j3;
        Table.nativeSetLong(j6, attendanceColumnInfo.statusColKey, j7, (long) com_ciot_realm_db_attendancerealmproxyinterface.realmGet$status(), false);
        Table.nativeSetBoolean(j6, attendanceColumnInfo.isCommitColKey, j7, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$isCommit(), false);
        return j3;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(Attendance.class);
        long nativePtr = table.getNativePtr();
        AttendanceColumnInfo attendanceColumnInfo = (AttendanceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Attendance.class);
        long j4 = attendanceColumnInfo.idColKey;
        while (it.hasNext()) {
            Attendance attendance = (Attendance) it.next();
            if (!map2.containsKey(attendance)) {
                if ((attendance instanceof RealmObjectProxy) && !RealmObject.isFrozen(attendance)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) attendance;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(attendance, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_AttendanceRealmProxyInterface com_ciot_realm_db_attendancerealmproxyinterface = attendance;
                String realmGet$id = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$id();
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
                map2.put(attendance, Long.valueOf(j2));
                String realmGet$employeeId = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$employeeId();
                if (realmGet$employeeId != null) {
                    j3 = j4;
                    Table.nativeSetString(nativePtr, attendanceColumnInfo.employeeIdColKey, j2, realmGet$employeeId, false);
                } else {
                    j3 = j4;
                }
                String realmGet$staffno = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$staffno();
                if (realmGet$staffno != null) {
                    Table.nativeSetString(nativePtr, attendanceColumnInfo.staffnoColKey, j2, realmGet$staffno, false);
                }
                String realmGet$company = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$company();
                if (realmGet$company != null) {
                    Table.nativeSetString(nativePtr, attendanceColumnInfo.companyColKey, j2, realmGet$company, false);
                }
                String realmGet$department = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$department();
                if (realmGet$department != null) {
                    Table.nativeSetString(nativePtr, attendanceColumnInfo.departmentColKey, j2, realmGet$department, false);
                }
                String realmGet$employeeName = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$employeeName();
                if (realmGet$employeeName != null) {
                    Table.nativeSetString(nativePtr, attendanceColumnInfo.employeeNameColKey, j2, realmGet$employeeName, false);
                }
                RobotBean realmGet$robot = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$robot();
                if (realmGet$robot != null) {
                    Long l = map2.get(realmGet$robot);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_RobotBeanRealmProxy.insert(realm2, realmGet$robot, map2));
                    }
                    table.setLink(attendanceColumnInfo.robotColKey, j2, l.longValue(), false);
                }
                long j5 = nativePtr;
                long j6 = j2;
                Table.nativeSetLong(j5, attendanceColumnInfo.checkTimeColKey, j6, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$checkTime(), false);
                Table.nativeSetFloat(j5, attendanceColumnInfo.temperatureColKey, j6, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$temperature(), false);
                String realmGet$description = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, attendanceColumnInfo.descriptionColKey, j2, realmGet$description, false);
                }
                String realmGet$idcard = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$idcard();
                if (realmGet$idcard != null) {
                    Table.nativeSetString(nativePtr, attendanceColumnInfo.idcardColKey, j2, realmGet$idcard, false);
                }
                String realmGet$clockInTime = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$clockInTime();
                if (realmGet$clockInTime != null) {
                    Table.nativeSetString(nativePtr, attendanceColumnInfo.clockInTimeColKey, j2, realmGet$clockInTime, false);
                }
                long j7 = nativePtr;
                long j8 = j2;
                Table.nativeSetLong(j7, attendanceColumnInfo.statusColKey, j8, (long) com_ciot_realm_db_attendancerealmproxyinterface.realmGet$status(), false);
                Table.nativeSetBoolean(j7, attendanceColumnInfo.isCommitColKey, j8, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$isCommit(), false);
                j4 = j3;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Attendance attendance, Map<RealmModel, Long> map) {
        long j;
        Realm realm2 = realm;
        Attendance attendance2 = attendance;
        Map<RealmModel, Long> map2 = map;
        if ((attendance2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(attendance)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) attendance2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(Attendance.class);
        long nativePtr = table.getNativePtr();
        AttendanceColumnInfo attendanceColumnInfo = (AttendanceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Attendance.class);
        long j2 = attendanceColumnInfo.idColKey;
        com_ciot_realm_db_AttendanceRealmProxyInterface com_ciot_realm_db_attendancerealmproxyinterface = attendance2;
        String realmGet$id = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j2);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j2, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j2, realmGet$id);
        }
        long j3 = j;
        map2.put(attendance2, Long.valueOf(j3));
        String realmGet$employeeId = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$employeeId();
        if (realmGet$employeeId != null) {
            Table.nativeSetString(nativePtr, attendanceColumnInfo.employeeIdColKey, j3, realmGet$employeeId, false);
        } else {
            Table.nativeSetNull(nativePtr, attendanceColumnInfo.employeeIdColKey, j3, false);
        }
        String realmGet$staffno = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$staffno();
        if (realmGet$staffno != null) {
            Table.nativeSetString(nativePtr, attendanceColumnInfo.staffnoColKey, j3, realmGet$staffno, false);
        } else {
            Table.nativeSetNull(nativePtr, attendanceColumnInfo.staffnoColKey, j3, false);
        }
        String realmGet$company = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$company();
        if (realmGet$company != null) {
            Table.nativeSetString(nativePtr, attendanceColumnInfo.companyColKey, j3, realmGet$company, false);
        } else {
            Table.nativeSetNull(nativePtr, attendanceColumnInfo.companyColKey, j3, false);
        }
        String realmGet$department = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$department();
        if (realmGet$department != null) {
            Table.nativeSetString(nativePtr, attendanceColumnInfo.departmentColKey, j3, realmGet$department, false);
        } else {
            Table.nativeSetNull(nativePtr, attendanceColumnInfo.departmentColKey, j3, false);
        }
        String realmGet$employeeName = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$employeeName();
        if (realmGet$employeeName != null) {
            Table.nativeSetString(nativePtr, attendanceColumnInfo.employeeNameColKey, j3, realmGet$employeeName, false);
        } else {
            Table.nativeSetNull(nativePtr, attendanceColumnInfo.employeeNameColKey, j3, false);
        }
        RobotBean realmGet$robot = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$robot();
        if (realmGet$robot != null) {
            Long l = map2.get(realmGet$robot);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_RobotBeanRealmProxy.insertOrUpdate(realm2, realmGet$robot, map2));
            }
            Table.nativeSetLink(nativePtr, attendanceColumnInfo.robotColKey, j3, l.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, attendanceColumnInfo.robotColKey, j3);
        }
        long j4 = nativePtr;
        long j5 = j3;
        Table.nativeSetLong(j4, attendanceColumnInfo.checkTimeColKey, j5, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$checkTime(), false);
        Table.nativeSetFloat(j4, attendanceColumnInfo.temperatureColKey, j5, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$temperature(), false);
        String realmGet$description = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, attendanceColumnInfo.descriptionColKey, j3, realmGet$description, false);
        } else {
            Table.nativeSetNull(nativePtr, attendanceColumnInfo.descriptionColKey, j3, false);
        }
        String realmGet$idcard = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$idcard();
        if (realmGet$idcard != null) {
            Table.nativeSetString(nativePtr, attendanceColumnInfo.idcardColKey, j3, realmGet$idcard, false);
        } else {
            Table.nativeSetNull(nativePtr, attendanceColumnInfo.idcardColKey, j3, false);
        }
        String realmGet$clockInTime = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$clockInTime();
        if (realmGet$clockInTime != null) {
            Table.nativeSetString(nativePtr, attendanceColumnInfo.clockInTimeColKey, j3, realmGet$clockInTime, false);
        } else {
            Table.nativeSetNull(nativePtr, attendanceColumnInfo.clockInTimeColKey, j3, false);
        }
        long j6 = nativePtr;
        long j7 = j3;
        Table.nativeSetLong(j6, attendanceColumnInfo.statusColKey, j7, (long) com_ciot_realm_db_attendancerealmproxyinterface.realmGet$status(), false);
        Table.nativeSetBoolean(j6, attendanceColumnInfo.isCommitColKey, j7, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$isCommit(), false);
        return j3;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(Attendance.class);
        long nativePtr = table.getNativePtr();
        AttendanceColumnInfo attendanceColumnInfo = (AttendanceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Attendance.class);
        long j3 = attendanceColumnInfo.idColKey;
        while (it.hasNext()) {
            Attendance attendance = (Attendance) it.next();
            if (!map2.containsKey(attendance)) {
                if ((attendance instanceof RealmObjectProxy) && !RealmObject.isFrozen(attendance)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) attendance;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(attendance, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_AttendanceRealmProxyInterface com_ciot_realm_db_attendancerealmproxyinterface = attendance;
                String realmGet$id = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j3);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j3, realmGet$id);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j3, realmGet$id) : j;
                map2.put(attendance, Long.valueOf(createRowWithPrimaryKey));
                String realmGet$employeeId = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$employeeId();
                if (realmGet$employeeId != null) {
                    j2 = j3;
                    Table.nativeSetString(nativePtr, attendanceColumnInfo.employeeIdColKey, createRowWithPrimaryKey, realmGet$employeeId, false);
                } else {
                    j2 = j3;
                    Table.nativeSetNull(nativePtr, attendanceColumnInfo.employeeIdColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$staffno = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$staffno();
                if (realmGet$staffno != null) {
                    Table.nativeSetString(nativePtr, attendanceColumnInfo.staffnoColKey, createRowWithPrimaryKey, realmGet$staffno, false);
                } else {
                    Table.nativeSetNull(nativePtr, attendanceColumnInfo.staffnoColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$company = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$company();
                if (realmGet$company != null) {
                    Table.nativeSetString(nativePtr, attendanceColumnInfo.companyColKey, createRowWithPrimaryKey, realmGet$company, false);
                } else {
                    Table.nativeSetNull(nativePtr, attendanceColumnInfo.companyColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$department = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$department();
                if (realmGet$department != null) {
                    Table.nativeSetString(nativePtr, attendanceColumnInfo.departmentColKey, createRowWithPrimaryKey, realmGet$department, false);
                } else {
                    Table.nativeSetNull(nativePtr, attendanceColumnInfo.departmentColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$employeeName = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$employeeName();
                if (realmGet$employeeName != null) {
                    Table.nativeSetString(nativePtr, attendanceColumnInfo.employeeNameColKey, createRowWithPrimaryKey, realmGet$employeeName, false);
                } else {
                    Table.nativeSetNull(nativePtr, attendanceColumnInfo.employeeNameColKey, createRowWithPrimaryKey, false);
                }
                RobotBean realmGet$robot = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$robot();
                if (realmGet$robot != null) {
                    Long l = map2.get(realmGet$robot);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_RobotBeanRealmProxy.insertOrUpdate(realm2, realmGet$robot, map2));
                    }
                    Table.nativeSetLink(nativePtr, attendanceColumnInfo.robotColKey, createRowWithPrimaryKey, l.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, attendanceColumnInfo.robotColKey, createRowWithPrimaryKey);
                }
                long j4 = nativePtr;
                long j5 = createRowWithPrimaryKey;
                Table.nativeSetLong(j4, attendanceColumnInfo.checkTimeColKey, j5, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$checkTime(), false);
                Table.nativeSetFloat(j4, attendanceColumnInfo.temperatureColKey, j5, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$temperature(), false);
                String realmGet$description = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, attendanceColumnInfo.descriptionColKey, createRowWithPrimaryKey, realmGet$description, false);
                } else {
                    Table.nativeSetNull(nativePtr, attendanceColumnInfo.descriptionColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$idcard = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$idcard();
                if (realmGet$idcard != null) {
                    Table.nativeSetString(nativePtr, attendanceColumnInfo.idcardColKey, createRowWithPrimaryKey, realmGet$idcard, false);
                } else {
                    Table.nativeSetNull(nativePtr, attendanceColumnInfo.idcardColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$clockInTime = com_ciot_realm_db_attendancerealmproxyinterface.realmGet$clockInTime();
                if (realmGet$clockInTime != null) {
                    Table.nativeSetString(nativePtr, attendanceColumnInfo.clockInTimeColKey, createRowWithPrimaryKey, realmGet$clockInTime, false);
                } else {
                    Table.nativeSetNull(nativePtr, attendanceColumnInfo.clockInTimeColKey, createRowWithPrimaryKey, false);
                }
                long j6 = nativePtr;
                long j7 = createRowWithPrimaryKey;
                Table.nativeSetLong(j6, attendanceColumnInfo.statusColKey, j7, (long) com_ciot_realm_db_attendancerealmproxyinterface.realmGet$status(), false);
                Table.nativeSetBoolean(j6, attendanceColumnInfo.isCommitColKey, j7, com_ciot_realm_db_attendancerealmproxyinterface.realmGet$isCommit(), false);
                j3 = j2;
            }
        }
    }

    public static Attendance createDetachedCopy(Attendance attendance, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        Attendance attendance2;
        if (i > i2 || attendance == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(attendance);
        if (cacheData == null) {
            attendance2 = new Attendance();
            map.put(attendance, new RealmObjectProxy.CacheData(i, attendance2));
        } else if (i >= cacheData.minDepth) {
            return (Attendance) cacheData.object;
        } else {
            cacheData.minDepth = i;
            attendance2 = (Attendance) cacheData.object;
        }
        com_ciot_realm_db_AttendanceRealmProxyInterface com_ciot_realm_db_attendancerealmproxyinterface = attendance2;
        com_ciot_realm_db_AttendanceRealmProxyInterface com_ciot_realm_db_attendancerealmproxyinterface2 = attendance;
        com_ciot_realm_db_attendancerealmproxyinterface.realmSet$employeeId(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$employeeId());
        com_ciot_realm_db_attendancerealmproxyinterface.realmSet$staffno(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$staffno());
        com_ciot_realm_db_attendancerealmproxyinterface.realmSet$company(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$company());
        com_ciot_realm_db_attendancerealmproxyinterface.realmSet$department(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$department());
        com_ciot_realm_db_attendancerealmproxyinterface.realmSet$employeeName(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$employeeName());
        com_ciot_realm_db_attendancerealmproxyinterface.realmSet$robot(com_ciot_realm_db_RobotBeanRealmProxy.createDetachedCopy(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$robot(), i + 1, i2, map));
        com_ciot_realm_db_attendancerealmproxyinterface.realmSet$checkTime(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$checkTime());
        com_ciot_realm_db_attendancerealmproxyinterface.realmSet$temperature(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$temperature());
        com_ciot_realm_db_attendancerealmproxyinterface.realmSet$description(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$description());
        com_ciot_realm_db_attendancerealmproxyinterface.realmSet$idcard(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$idcard());
        com_ciot_realm_db_attendancerealmproxyinterface.realmSet$clockInTime(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$clockInTime());
        com_ciot_realm_db_attendancerealmproxyinterface.realmSet$status(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$status());
        com_ciot_realm_db_attendancerealmproxyinterface.realmSet$isCommit(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$isCommit());
        com_ciot_realm_db_attendancerealmproxyinterface.realmSet$id(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$id());
        return attendance2;
    }

    static Attendance update(Realm realm, AttendanceColumnInfo attendanceColumnInfo, Attendance attendance, Attendance attendance2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_AttendanceRealmProxyInterface com_ciot_realm_db_attendancerealmproxyinterface = attendance;
        com_ciot_realm_db_AttendanceRealmProxyInterface com_ciot_realm_db_attendancerealmproxyinterface2 = attendance2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Attendance.class), set);
        osObjectBuilder.addString(attendanceColumnInfo.employeeIdColKey, com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$employeeId());
        osObjectBuilder.addString(attendanceColumnInfo.staffnoColKey, com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$staffno());
        osObjectBuilder.addString(attendanceColumnInfo.companyColKey, com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$company());
        osObjectBuilder.addString(attendanceColumnInfo.departmentColKey, com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$department());
        osObjectBuilder.addString(attendanceColumnInfo.employeeNameColKey, com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$employeeName());
        RobotBean realmGet$robot = com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$robot();
        if (realmGet$robot == null) {
            osObjectBuilder.addNull(attendanceColumnInfo.robotColKey);
        } else {
            RobotBean robotBean = (RobotBean) map.get(realmGet$robot);
            if (robotBean != null) {
                osObjectBuilder.addObject(attendanceColumnInfo.robotColKey, robotBean);
            } else {
                osObjectBuilder.addObject(attendanceColumnInfo.robotColKey, com_ciot_realm_db_RobotBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_RobotBeanRealmProxy.RobotBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) RobotBean.class), realmGet$robot, true, map, set));
            }
        }
        osObjectBuilder.addInteger(attendanceColumnInfo.checkTimeColKey, Long.valueOf(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$checkTime()));
        osObjectBuilder.addFloat(attendanceColumnInfo.temperatureColKey, Float.valueOf(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$temperature()));
        osObjectBuilder.addString(attendanceColumnInfo.descriptionColKey, com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$description());
        osObjectBuilder.addString(attendanceColumnInfo.idcardColKey, com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$idcard());
        osObjectBuilder.addString(attendanceColumnInfo.clockInTimeColKey, com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$clockInTime());
        osObjectBuilder.addInteger(attendanceColumnInfo.statusColKey, Integer.valueOf(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$status()));
        osObjectBuilder.addBoolean(attendanceColumnInfo.isCommitColKey, Boolean.valueOf(com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$isCommit()));
        osObjectBuilder.addString(attendanceColumnInfo.idColKey, com_ciot_realm_db_attendancerealmproxyinterface2.realmGet$id());
        osObjectBuilder.updateExistingObject();
        return attendance;
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
        com_ciot_realm_db_AttendanceRealmProxy com_ciot_realm_db_attendancerealmproxy = (com_ciot_realm_db_AttendanceRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_attendancerealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_attendancerealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_attendancerealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
