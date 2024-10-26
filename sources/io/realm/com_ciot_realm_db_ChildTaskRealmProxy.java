package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.ChildTask;
import com.ciot.realm.db.patrol.Point;
import com.ciot.realm.db.patrol.Process;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_patrol_PointRealmProxy;
import io.realm.com_ciot_realm_db_patrol_ProcessRealmProxy;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.objectstore.OsObjectBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class com_ciot_realm_db_ChildTaskRealmProxy extends ChildTask implements RealmObjectProxy, com_ciot_realm_db_ChildTaskRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private ChildTaskColumnInfo columnInfo;
    private ProxyState<ChildTask> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "ChildTask";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class ChildTaskColumnInfo extends ColumnInfo {
        long angleColKey;
        long firstSentenceColKey;
        long isPathEditedColKey;
        long isPointEditedColKey;
        long isSelectedColKey;
        long mapinfoColKey;
        long p_typeColKey;
        long pointColKey;
        long pointNameColKey;
        long processColKey;
        long taskNodeIDColKey;
        long transitionColKey;
        long waitPlayMediaColKey;
        long xColKey;
        long yColKey;
        long zColKey;

        ChildTaskColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(16);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.isPointEditedColKey = addColumnDetails("isPointEdited", "isPointEdited", objectSchemaInfo);
            this.isPathEditedColKey = addColumnDetails("isPathEdited", "isPathEdited", objectSchemaInfo);
            this.pointNameColKey = addColumnDetails("pointName", "pointName", objectSchemaInfo);
            this.taskNodeIDColKey = addColumnDetails("taskNodeID", "taskNodeID", objectSchemaInfo);
            this.firstSentenceColKey = addColumnDetails("firstSentence", "firstSentence", objectSchemaInfo);
            this.transitionColKey = addColumnDetails("transition", "transition", objectSchemaInfo);
            this.xColKey = addColumnDetails("x", "x", objectSchemaInfo);
            this.yColKey = addColumnDetails("y", "y", objectSchemaInfo);
            this.zColKey = addColumnDetails("z", "z", objectSchemaInfo);
            this.p_typeColKey = addColumnDetails("p_type", "p_type", objectSchemaInfo);
            this.angleColKey = addColumnDetails("angle", "angle", objectSchemaInfo);
            this.mapinfoColKey = addColumnDetails("mapinfo", "mapinfo", objectSchemaInfo);
            this.pointColKey = addColumnDetails("point", "point", objectSchemaInfo);
            this.processColKey = addColumnDetails("process", "process", objectSchemaInfo);
            this.waitPlayMediaColKey = addColumnDetails("waitPlayMedia", "waitPlayMedia", objectSchemaInfo);
            this.isSelectedColKey = addColumnDetails("isSelected", "isSelected", objectSchemaInfo);
        }

        ChildTaskColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new ChildTaskColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            ChildTaskColumnInfo childTaskColumnInfo = (ChildTaskColumnInfo) columnInfo;
            ChildTaskColumnInfo childTaskColumnInfo2 = (ChildTaskColumnInfo) columnInfo2;
            childTaskColumnInfo2.isPointEditedColKey = childTaskColumnInfo.isPointEditedColKey;
            childTaskColumnInfo2.isPathEditedColKey = childTaskColumnInfo.isPathEditedColKey;
            childTaskColumnInfo2.pointNameColKey = childTaskColumnInfo.pointNameColKey;
            childTaskColumnInfo2.taskNodeIDColKey = childTaskColumnInfo.taskNodeIDColKey;
            childTaskColumnInfo2.firstSentenceColKey = childTaskColumnInfo.firstSentenceColKey;
            childTaskColumnInfo2.transitionColKey = childTaskColumnInfo.transitionColKey;
            childTaskColumnInfo2.xColKey = childTaskColumnInfo.xColKey;
            childTaskColumnInfo2.yColKey = childTaskColumnInfo.yColKey;
            childTaskColumnInfo2.zColKey = childTaskColumnInfo.zColKey;
            childTaskColumnInfo2.p_typeColKey = childTaskColumnInfo.p_typeColKey;
            childTaskColumnInfo2.angleColKey = childTaskColumnInfo.angleColKey;
            childTaskColumnInfo2.mapinfoColKey = childTaskColumnInfo.mapinfoColKey;
            childTaskColumnInfo2.pointColKey = childTaskColumnInfo.pointColKey;
            childTaskColumnInfo2.processColKey = childTaskColumnInfo.processColKey;
            childTaskColumnInfo2.waitPlayMediaColKey = childTaskColumnInfo.waitPlayMediaColKey;
            childTaskColumnInfo2.isSelectedColKey = childTaskColumnInfo.isSelectedColKey;
        }
    }

    com_ciot_realm_db_ChildTaskRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (ChildTaskColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<ChildTask> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public boolean realmGet$isPointEdited() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isPointEditedColKey);
    }

    public void realmSet$isPointEdited(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isPointEditedColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.isPointEditedColKey, row$realm.getObjectKey(), z, true);
        }
    }

    public boolean realmGet$isPathEdited() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.isPathEditedColKey);
    }

    public void realmSet$isPathEdited(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.isPathEditedColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.isPathEditedColKey, row$realm.getObjectKey(), z, true);
        }
    }

    public String realmGet$pointName() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.pointNameColKey);
    }

    public void realmSet$pointName(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.pointNameColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.pointNameColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.pointNameColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.pointNameColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$taskNodeID() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.taskNodeIDColKey);
    }

    public void realmSet$taskNodeID(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.taskNodeIDColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.taskNodeIDColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.taskNodeIDColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.taskNodeIDColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$firstSentence() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.firstSentenceColKey);
    }

    public void realmSet$firstSentence(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.firstSentenceColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.firstSentenceColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.firstSentenceColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.firstSentenceColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$transition() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.transitionColKey);
    }

    public void realmSet$transition(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.transitionColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.transitionColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.transitionColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.transitionColKey, row$realm.getObjectKey(), str, true);
            }
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

    public int realmGet$p_type() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.p_typeColKey);
    }

    public void realmSet$p_type(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.p_typeColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.p_typeColKey, row$realm.getObjectKey(), (long) i, true);
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

    public String realmGet$mapinfo() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.mapinfoColKey);
    }

    public void realmSet$mapinfo(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.mapinfoColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.mapinfoColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.mapinfoColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.mapinfoColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public Point realmGet$point() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.pointColKey)) {
            return null;
        }
        return (Point) this.proxyState.getRealm$realm().get(Point.class, this.proxyState.getRow$realm().getLink(this.columnInfo.pointColKey), false, Collections.emptyList());
    }

    public void realmSet$point(Point point) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (point == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.pointColKey);
                return;
            }
            this.proxyState.checkValidObject(point);
            this.proxyState.getRow$realm().setLink(this.columnInfo.pointColKey, ((RealmObjectProxy) point).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("point")) {
            if (point != null && !RealmObject.isManaged(point)) {
                point = (Point) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(point, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (point == null) {
                row$realm.nullifyLink(this.columnInfo.pointColKey);
                return;
            }
            this.proxyState.checkValidObject(point);
            row$realm.getTable().setLink(this.columnInfo.pointColKey, row$realm.getObjectKey(), ((RealmObjectProxy) point).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public Process realmGet$process() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.processColKey)) {
            return null;
        }
        return (Process) this.proxyState.getRealm$realm().get(Process.class, this.proxyState.getRow$realm().getLink(this.columnInfo.processColKey), false, Collections.emptyList());
    }

    public void realmSet$process(Process process) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (process == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.processColKey);
                return;
            }
            this.proxyState.checkValidObject(process);
            this.proxyState.getRow$realm().setLink(this.columnInfo.processColKey, ((RealmObjectProxy) process).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("process")) {
            if (process != null && !RealmObject.isManaged(process)) {
                process = (Process) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(process, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (process == null) {
                row$realm.nullifyLink(this.columnInfo.processColKey);
                return;
            }
            this.proxyState.checkValidObject(process);
            row$realm.getTable().setLink(this.columnInfo.processColKey, row$realm.getObjectKey(), ((RealmObjectProxy) process).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public boolean realmGet$waitPlayMedia() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.waitPlayMediaColKey);
    }

    public void realmSet$waitPlayMedia(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.waitPlayMediaColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.waitPlayMediaColKey, row$realm.getObjectKey(), z, true);
        }
    }

    public Boolean realmGet$isSelected() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNull(this.columnInfo.isSelectedColKey)) {
            return null;
        }
        return Boolean.valueOf(this.proxyState.getRow$realm().getBoolean(this.columnInfo.isSelectedColKey));
    }

    public void realmSet$isSelected(Boolean bool) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (bool == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.isSelectedColKey);
            } else {
                this.proxyState.getRow$realm().setBoolean(this.columnInfo.isSelectedColKey, bool.booleanValue());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (bool == null) {
                row$realm.getTable().setNull(this.columnInfo.isSelectedColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setBoolean(this.columnInfo.isSelectedColKey, row$realm.getObjectKey(), bool.booleanValue(), true);
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 16, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("isPointEdited", RealmFieldType.BOOLEAN, false, false, true);
        builder2.addPersistedProperty("isPathEdited", RealmFieldType.BOOLEAN, false, false, true);
        builder2.addPersistedProperty("pointName", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("taskNodeID", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("firstSentence", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("transition", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("x", RealmFieldType.FLOAT, false, false, true);
        builder2.addPersistedProperty("y", RealmFieldType.FLOAT, false, false, true);
        builder2.addPersistedProperty("z", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("p_type", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("angle", RealmFieldType.FLOAT, false, false, true);
        builder2.addPersistedProperty("mapinfo", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("point", RealmFieldType.OBJECT, com_ciot_realm_db_patrol_PointRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedLinkProperty("process", RealmFieldType.OBJECT, com_ciot_realm_db_patrol_ProcessRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder3 = builder;
        builder3.addPersistedProperty("waitPlayMedia", RealmFieldType.BOOLEAN, false, false, true);
        builder3.addPersistedProperty("isSelected", RealmFieldType.BOOLEAN, false, false, false);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ChildTaskColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new ChildTaskColumnInfo(osSchemaInfo);
    }

    public static ChildTask createOrUpdateUsingJsonObject(Realm realm, JSONObject jSONObject, boolean z) throws JSONException {
        ArrayList arrayList = new ArrayList(2);
        if (jSONObject.has("point")) {
            arrayList.add("point");
        }
        if (jSONObject.has("process")) {
            arrayList.add("process");
        }
        ChildTask childTask = (ChildTask) realm.createObjectInternal(ChildTask.class, true, arrayList);
        com_ciot_realm_db_ChildTaskRealmProxyInterface com_ciot_realm_db_childtaskrealmproxyinterface = childTask;
        if (jSONObject.has("isPointEdited")) {
            if (!jSONObject.isNull("isPointEdited")) {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$isPointEdited(jSONObject.getBoolean("isPointEdited"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'isPointEdited' to null.");
            }
        }
        if (jSONObject.has("isPathEdited")) {
            if (!jSONObject.isNull("isPathEdited")) {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$isPathEdited(jSONObject.getBoolean("isPathEdited"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'isPathEdited' to null.");
            }
        }
        if (jSONObject.has("pointName")) {
            if (jSONObject.isNull("pointName")) {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$pointName((String) null);
            } else {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$pointName(jSONObject.getString("pointName"));
            }
        }
        if (jSONObject.has("taskNodeID")) {
            if (jSONObject.isNull("taskNodeID")) {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$taskNodeID((String) null);
            } else {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$taskNodeID(jSONObject.getString("taskNodeID"));
            }
        }
        if (jSONObject.has("firstSentence")) {
            if (jSONObject.isNull("firstSentence")) {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$firstSentence((String) null);
            } else {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$firstSentence(jSONObject.getString("firstSentence"));
            }
        }
        if (jSONObject.has("transition")) {
            if (jSONObject.isNull("transition")) {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$transition((String) null);
            } else {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$transition(jSONObject.getString("transition"));
            }
        }
        if (jSONObject.has("x")) {
            if (!jSONObject.isNull("x")) {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$x((float) jSONObject.getDouble("x"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'x' to null.");
            }
        }
        if (jSONObject.has("y")) {
            if (!jSONObject.isNull("y")) {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$y((float) jSONObject.getDouble("y"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'y' to null.");
            }
        }
        if (jSONObject.has("z")) {
            if (!jSONObject.isNull("z")) {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$z(jSONObject.getInt("z"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'z' to null.");
            }
        }
        if (jSONObject.has("p_type")) {
            if (!jSONObject.isNull("p_type")) {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$p_type(jSONObject.getInt("p_type"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'p_type' to null.");
            }
        }
        if (jSONObject.has("angle")) {
            if (!jSONObject.isNull("angle")) {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$angle((float) jSONObject.getDouble("angle"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'angle' to null.");
            }
        }
        if (jSONObject.has("mapinfo")) {
            if (jSONObject.isNull("mapinfo")) {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$mapinfo((String) null);
            } else {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$mapinfo(jSONObject.getString("mapinfo"));
            }
        }
        if (jSONObject.has("point")) {
            if (jSONObject.isNull("point")) {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$point((Point) null);
            } else {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$point(com_ciot_realm_db_patrol_PointRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject.getJSONObject("point"), z));
            }
        }
        if (jSONObject.has("process")) {
            if (jSONObject.isNull("process")) {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$process((Process) null);
            } else {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$process(com_ciot_realm_db_patrol_ProcessRealmProxy.createOrUpdateUsingJsonObject(realm, jSONObject.getJSONObject("process"), z));
            }
        }
        if (jSONObject.has("waitPlayMedia")) {
            if (!jSONObject.isNull("waitPlayMedia")) {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$waitPlayMedia(jSONObject.getBoolean("waitPlayMedia"));
            } else {
                throw new IllegalArgumentException("Trying to set non-nullable field 'waitPlayMedia' to null.");
            }
        }
        if (jSONObject.has("isSelected")) {
            if (jSONObject.isNull("isSelected")) {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$isSelected((Boolean) null);
            } else {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$isSelected(Boolean.valueOf(jSONObject.getBoolean("isSelected")));
            }
        }
        return childTask;
    }

    public static ChildTask createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        ChildTask childTask = new ChildTask();
        com_ciot_realm_db_ChildTaskRealmProxyInterface com_ciot_realm_db_childtaskrealmproxyinterface = childTask;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("isPointEdited")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$isPointEdited(jsonReader.nextBoolean());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isPointEdited' to null.");
                }
            } else if (nextName.equals("isPathEdited")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$isPathEdited(jsonReader.nextBoolean());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'isPathEdited' to null.");
                }
            } else if (nextName.equals("pointName")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$pointName(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$pointName((String) null);
                }
            } else if (nextName.equals("taskNodeID")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$taskNodeID(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$taskNodeID((String) null);
                }
            } else if (nextName.equals("firstSentence")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$firstSentence(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$firstSentence((String) null);
                }
            } else if (nextName.equals("transition")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$transition(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$transition((String) null);
                }
            } else if (nextName.equals("x")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$x((float) jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'x' to null.");
                }
            } else if (nextName.equals("y")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$y((float) jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'y' to null.");
                }
            } else if (nextName.equals("z")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$z(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'z' to null.");
                }
            } else if (nextName.equals("p_type")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$p_type(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'p_type' to null.");
                }
            } else if (nextName.equals("angle")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$angle((float) jsonReader.nextDouble());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'angle' to null.");
                }
            } else if (nextName.equals("mapinfo")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$mapinfo(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$mapinfo((String) null);
                }
            } else if (nextName.equals("point")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$point((Point) null);
                } else {
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$point(com_ciot_realm_db_patrol_PointRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("process")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$process((Process) null);
                } else {
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$process(com_ciot_realm_db_patrol_ProcessRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("waitPlayMedia")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$waitPlayMedia(jsonReader.nextBoolean());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'waitPlayMedia' to null.");
                }
            } else if (!nextName.equals("isSelected")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$isSelected(Boolean.valueOf(jsonReader.nextBoolean()));
            } else {
                jsonReader.skipValue();
                com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$isSelected((Boolean) null);
            }
        }
        jsonReader.endObject();
        return (ChildTask) realm.copyToRealm(childTask, new ImportFlag[0]);
    }

    private static com_ciot_realm_db_ChildTaskRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) ChildTask.class), false, Collections.emptyList());
        com_ciot_realm_db_ChildTaskRealmProxy com_ciot_realm_db_childtaskrealmproxy = new com_ciot_realm_db_ChildTaskRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_childtaskrealmproxy;
    }

    public static ChildTask copyOrUpdate(Realm realm, ChildTaskColumnInfo childTaskColumnInfo, ChildTask childTask, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        if ((childTask instanceof RealmObjectProxy) && !RealmObject.isFrozen(childTask)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) childTask;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null) {
                BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
                if (realm$realm.threadId != realm.threadId) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (realm$realm.getPath().equals(realm.getPath())) {
                    return childTask;
                }
            }
        }
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        RealmObjectProxy realmObjectProxy2 = map.get(childTask);
        if (realmObjectProxy2 != null) {
            return (ChildTask) realmObjectProxy2;
        }
        return copy(realm, childTaskColumnInfo, childTask, z, map, set);
    }

    public static ChildTask copy(Realm realm, ChildTaskColumnInfo childTaskColumnInfo, ChildTask childTask, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(childTask);
        if (realmObjectProxy != null) {
            return (ChildTask) realmObjectProxy;
        }
        com_ciot_realm_db_ChildTaskRealmProxyInterface com_ciot_realm_db_childtaskrealmproxyinterface = childTask;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(ChildTask.class), set);
        osObjectBuilder.addBoolean(childTaskColumnInfo.isPointEditedColKey, Boolean.valueOf(com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$isPointEdited()));
        osObjectBuilder.addBoolean(childTaskColumnInfo.isPathEditedColKey, Boolean.valueOf(com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$isPathEdited()));
        osObjectBuilder.addString(childTaskColumnInfo.pointNameColKey, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$pointName());
        osObjectBuilder.addString(childTaskColumnInfo.taskNodeIDColKey, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$taskNodeID());
        osObjectBuilder.addString(childTaskColumnInfo.firstSentenceColKey, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$firstSentence());
        osObjectBuilder.addString(childTaskColumnInfo.transitionColKey, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$transition());
        osObjectBuilder.addFloat(childTaskColumnInfo.xColKey, Float.valueOf(com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$x()));
        osObjectBuilder.addFloat(childTaskColumnInfo.yColKey, Float.valueOf(com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$y()));
        osObjectBuilder.addInteger(childTaskColumnInfo.zColKey, Integer.valueOf(com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$z()));
        osObjectBuilder.addInteger(childTaskColumnInfo.p_typeColKey, Integer.valueOf(com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$p_type()));
        osObjectBuilder.addFloat(childTaskColumnInfo.angleColKey, Float.valueOf(com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$angle()));
        osObjectBuilder.addString(childTaskColumnInfo.mapinfoColKey, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$mapinfo());
        osObjectBuilder.addBoolean(childTaskColumnInfo.waitPlayMediaColKey, Boolean.valueOf(com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$waitPlayMedia()));
        osObjectBuilder.addBoolean(childTaskColumnInfo.isSelectedColKey, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$isSelected());
        com_ciot_realm_db_ChildTaskRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(childTask, newProxyInstance);
        Point realmGet$point = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$point();
        if (realmGet$point == null) {
            newProxyInstance.realmSet$point((Point) null);
        } else {
            Point point = (Point) map.get(realmGet$point);
            if (point != null) {
                newProxyInstance.realmSet$point(point);
            } else {
                newProxyInstance.realmSet$point(com_ciot_realm_db_patrol_PointRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_PointRealmProxy.PointColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Point.class), realmGet$point, z, map, set));
            }
        }
        Process realmGet$process = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$process();
        if (realmGet$process == null) {
            newProxyInstance.realmSet$process((Process) null);
        } else {
            Process process = (Process) map.get(realmGet$process);
            if (process != null) {
                newProxyInstance.realmSet$process(process);
            } else {
                newProxyInstance.realmSet$process(com_ciot_realm_db_patrol_ProcessRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ProcessRealmProxy.ProcessColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Process.class), realmGet$process, z, map, set));
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, ChildTask childTask, Map<RealmModel, Long> map) {
        Realm realm2 = realm;
        ChildTask childTask2 = childTask;
        Map<RealmModel, Long> map2 = map;
        if ((childTask2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(childTask)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) childTask2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(ChildTask.class);
        long nativePtr = table.getNativePtr();
        ChildTaskColumnInfo childTaskColumnInfo = (ChildTaskColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ChildTask.class);
        long createRow = OsObject.createRow(table);
        map2.put(childTask2, Long.valueOf(createRow));
        com_ciot_realm_db_ChildTaskRealmProxyInterface com_ciot_realm_db_childtaskrealmproxyinterface = childTask2;
        long j = nativePtr;
        long j2 = createRow;
        Table.nativeSetBoolean(j, childTaskColumnInfo.isPointEditedColKey, j2, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$isPointEdited(), false);
        Table.nativeSetBoolean(j, childTaskColumnInfo.isPathEditedColKey, j2, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$isPathEdited(), false);
        String realmGet$pointName = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$pointName();
        if (realmGet$pointName != null) {
            Table.nativeSetString(nativePtr, childTaskColumnInfo.pointNameColKey, createRow, realmGet$pointName, false);
        }
        String realmGet$taskNodeID = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$taskNodeID();
        if (realmGet$taskNodeID != null) {
            Table.nativeSetString(nativePtr, childTaskColumnInfo.taskNodeIDColKey, createRow, realmGet$taskNodeID, false);
        }
        String realmGet$firstSentence = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$firstSentence();
        if (realmGet$firstSentence != null) {
            Table.nativeSetString(nativePtr, childTaskColumnInfo.firstSentenceColKey, createRow, realmGet$firstSentence, false);
        }
        String realmGet$transition = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$transition();
        if (realmGet$transition != null) {
            Table.nativeSetString(nativePtr, childTaskColumnInfo.transitionColKey, createRow, realmGet$transition, false);
        }
        long j3 = nativePtr;
        long j4 = createRow;
        Table.nativeSetFloat(j3, childTaskColumnInfo.xColKey, j4, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$x(), false);
        Table.nativeSetFloat(j3, childTaskColumnInfo.yColKey, j4, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$y(), false);
        Table.nativeSetLong(j3, childTaskColumnInfo.zColKey, j4, (long) com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$z(), false);
        Table.nativeSetLong(j3, childTaskColumnInfo.p_typeColKey, j4, (long) com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$p_type(), false);
        Table.nativeSetFloat(j3, childTaskColumnInfo.angleColKey, j4, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$angle(), false);
        String realmGet$mapinfo = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$mapinfo();
        if (realmGet$mapinfo != null) {
            Table.nativeSetString(nativePtr, childTaskColumnInfo.mapinfoColKey, createRow, realmGet$mapinfo, false);
        }
        Point realmGet$point = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$point();
        if (realmGet$point != null) {
            Long l = map2.get(realmGet$point);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_patrol_PointRealmProxy.insert(realm2, realmGet$point, map2));
            }
            Table.nativeSetLink(nativePtr, childTaskColumnInfo.pointColKey, createRow, l.longValue(), false);
        }
        Process realmGet$process = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$process();
        if (realmGet$process != null) {
            Long l2 = map2.get(realmGet$process);
            if (l2 == null) {
                l2 = Long.valueOf(com_ciot_realm_db_patrol_ProcessRealmProxy.insert(realm2, realmGet$process, map2));
            }
            Table.nativeSetLink(nativePtr, childTaskColumnInfo.processColKey, createRow, l2.longValue(), false);
        }
        Table.nativeSetBoolean(nativePtr, childTaskColumnInfo.waitPlayMediaColKey, createRow, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$waitPlayMedia(), false);
        Boolean realmGet$isSelected = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$isSelected();
        if (realmGet$isSelected != null) {
            Table.nativeSetBoolean(nativePtr, childTaskColumnInfo.isSelectedColKey, createRow, realmGet$isSelected.booleanValue(), false);
        }
        return createRow;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(ChildTask.class);
        long nativePtr = table.getNativePtr();
        ChildTaskColumnInfo childTaskColumnInfo = (ChildTaskColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ChildTask.class);
        while (it.hasNext()) {
            ChildTask childTask = (ChildTask) it.next();
            if (!map2.containsKey(childTask)) {
                if ((childTask instanceof RealmObjectProxy) && !RealmObject.isFrozen(childTask)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) childTask;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(childTask, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(childTask, Long.valueOf(createRow));
                com_ciot_realm_db_ChildTaskRealmProxyInterface com_ciot_realm_db_childtaskrealmproxyinterface = childTask;
                long j = nativePtr;
                long j2 = createRow;
                Table.nativeSetBoolean(j, childTaskColumnInfo.isPointEditedColKey, j2, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$isPointEdited(), false);
                Table.nativeSetBoolean(j, childTaskColumnInfo.isPathEditedColKey, j2, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$isPathEdited(), false);
                String realmGet$pointName = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$pointName();
                if (realmGet$pointName != null) {
                    Table.nativeSetString(nativePtr, childTaskColumnInfo.pointNameColKey, createRow, realmGet$pointName, false);
                }
                String realmGet$taskNodeID = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$taskNodeID();
                if (realmGet$taskNodeID != null) {
                    Table.nativeSetString(nativePtr, childTaskColumnInfo.taskNodeIDColKey, createRow, realmGet$taskNodeID, false);
                }
                String realmGet$firstSentence = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$firstSentence();
                if (realmGet$firstSentence != null) {
                    Table.nativeSetString(nativePtr, childTaskColumnInfo.firstSentenceColKey, createRow, realmGet$firstSentence, false);
                }
                String realmGet$transition = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$transition();
                if (realmGet$transition != null) {
                    Table.nativeSetString(nativePtr, childTaskColumnInfo.transitionColKey, createRow, realmGet$transition, false);
                }
                long j3 = nativePtr;
                long j4 = createRow;
                Table.nativeSetFloat(j3, childTaskColumnInfo.xColKey, j4, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$x(), false);
                Table.nativeSetFloat(j3, childTaskColumnInfo.yColKey, j4, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$y(), false);
                Table.nativeSetLong(nativePtr, childTaskColumnInfo.zColKey, j4, (long) com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$z(), false);
                long j5 = nativePtr;
                Table.nativeSetLong(j5, childTaskColumnInfo.p_typeColKey, j4, (long) com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$p_type(), false);
                Table.nativeSetFloat(j5, childTaskColumnInfo.angleColKey, j4, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$angle(), false);
                String realmGet$mapinfo = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$mapinfo();
                if (realmGet$mapinfo != null) {
                    Table.nativeSetString(nativePtr, childTaskColumnInfo.mapinfoColKey, createRow, realmGet$mapinfo, false);
                }
                Point realmGet$point = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$point();
                if (realmGet$point != null) {
                    Long l = map2.get(realmGet$point);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_patrol_PointRealmProxy.insert(realm2, realmGet$point, map2));
                    }
                    table.setLink(childTaskColumnInfo.pointColKey, createRow, l.longValue(), false);
                }
                Process realmGet$process = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$process();
                if (realmGet$process != null) {
                    Long l2 = map2.get(realmGet$process);
                    if (l2 == null) {
                        l2 = Long.valueOf(com_ciot_realm_db_patrol_ProcessRealmProxy.insert(realm2, realmGet$process, map2));
                    }
                    table.setLink(childTaskColumnInfo.processColKey, createRow, l2.longValue(), false);
                }
                Table.nativeSetBoolean(nativePtr, childTaskColumnInfo.waitPlayMediaColKey, createRow, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$waitPlayMedia(), false);
                Boolean realmGet$isSelected = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$isSelected();
                if (realmGet$isSelected != null) {
                    Table.nativeSetBoolean(nativePtr, childTaskColumnInfo.isSelectedColKey, createRow, realmGet$isSelected.booleanValue(), false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, ChildTask childTask, Map<RealmModel, Long> map) {
        Realm realm2 = realm;
        ChildTask childTask2 = childTask;
        Map<RealmModel, Long> map2 = map;
        if ((childTask2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(childTask)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) childTask2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(ChildTask.class);
        long nativePtr = table.getNativePtr();
        ChildTaskColumnInfo childTaskColumnInfo = (ChildTaskColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ChildTask.class);
        long createRow = OsObject.createRow(table);
        map2.put(childTask2, Long.valueOf(createRow));
        com_ciot_realm_db_ChildTaskRealmProxyInterface com_ciot_realm_db_childtaskrealmproxyinterface = childTask2;
        long j = nativePtr;
        long j2 = createRow;
        Table.nativeSetBoolean(j, childTaskColumnInfo.isPointEditedColKey, j2, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$isPointEdited(), false);
        Table.nativeSetBoolean(j, childTaskColumnInfo.isPathEditedColKey, j2, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$isPathEdited(), false);
        String realmGet$pointName = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$pointName();
        if (realmGet$pointName != null) {
            Table.nativeSetString(nativePtr, childTaskColumnInfo.pointNameColKey, createRow, realmGet$pointName, false);
        } else {
            Table.nativeSetNull(nativePtr, childTaskColumnInfo.pointNameColKey, createRow, false);
        }
        String realmGet$taskNodeID = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$taskNodeID();
        if (realmGet$taskNodeID != null) {
            Table.nativeSetString(nativePtr, childTaskColumnInfo.taskNodeIDColKey, createRow, realmGet$taskNodeID, false);
        } else {
            Table.nativeSetNull(nativePtr, childTaskColumnInfo.taskNodeIDColKey, createRow, false);
        }
        String realmGet$firstSentence = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$firstSentence();
        if (realmGet$firstSentence != null) {
            Table.nativeSetString(nativePtr, childTaskColumnInfo.firstSentenceColKey, createRow, realmGet$firstSentence, false);
        } else {
            Table.nativeSetNull(nativePtr, childTaskColumnInfo.firstSentenceColKey, createRow, false);
        }
        String realmGet$transition = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$transition();
        if (realmGet$transition != null) {
            Table.nativeSetString(nativePtr, childTaskColumnInfo.transitionColKey, createRow, realmGet$transition, false);
        } else {
            Table.nativeSetNull(nativePtr, childTaskColumnInfo.transitionColKey, createRow, false);
        }
        long j3 = nativePtr;
        long j4 = createRow;
        Table.nativeSetFloat(j3, childTaskColumnInfo.xColKey, j4, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$x(), false);
        Table.nativeSetFloat(j3, childTaskColumnInfo.yColKey, j4, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$y(), false);
        Table.nativeSetLong(j3, childTaskColumnInfo.zColKey, j4, (long) com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$z(), false);
        Table.nativeSetLong(j3, childTaskColumnInfo.p_typeColKey, j4, (long) com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$p_type(), false);
        Table.nativeSetFloat(j3, childTaskColumnInfo.angleColKey, j4, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$angle(), false);
        String realmGet$mapinfo = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$mapinfo();
        if (realmGet$mapinfo != null) {
            Table.nativeSetString(nativePtr, childTaskColumnInfo.mapinfoColKey, createRow, realmGet$mapinfo, false);
        } else {
            Table.nativeSetNull(nativePtr, childTaskColumnInfo.mapinfoColKey, createRow, false);
        }
        Point realmGet$point = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$point();
        if (realmGet$point != null) {
            Long l = map2.get(realmGet$point);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_patrol_PointRealmProxy.insertOrUpdate(realm2, realmGet$point, map2));
            }
            Table.nativeSetLink(nativePtr, childTaskColumnInfo.pointColKey, createRow, l.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, childTaskColumnInfo.pointColKey, createRow);
        }
        Process realmGet$process = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$process();
        if (realmGet$process != null) {
            Long l2 = map2.get(realmGet$process);
            if (l2 == null) {
                l2 = Long.valueOf(com_ciot_realm_db_patrol_ProcessRealmProxy.insertOrUpdate(realm2, realmGet$process, map2));
            }
            Table.nativeSetLink(nativePtr, childTaskColumnInfo.processColKey, createRow, l2.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, childTaskColumnInfo.processColKey, createRow);
        }
        Table.nativeSetBoolean(nativePtr, childTaskColumnInfo.waitPlayMediaColKey, createRow, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$waitPlayMedia(), false);
        Boolean realmGet$isSelected = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$isSelected();
        if (realmGet$isSelected != null) {
            Table.nativeSetBoolean(nativePtr, childTaskColumnInfo.isSelectedColKey, createRow, realmGet$isSelected.booleanValue(), false);
        } else {
            Table.nativeSetNull(nativePtr, childTaskColumnInfo.isSelectedColKey, createRow, false);
        }
        return createRow;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(ChildTask.class);
        long nativePtr = table.getNativePtr();
        ChildTaskColumnInfo childTaskColumnInfo = (ChildTaskColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ChildTask.class);
        while (it.hasNext()) {
            ChildTask childTask = (ChildTask) it.next();
            if (!map2.containsKey(childTask)) {
                if ((childTask instanceof RealmObjectProxy) && !RealmObject.isFrozen(childTask)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) childTask;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(childTask, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                long createRow = OsObject.createRow(table);
                map2.put(childTask, Long.valueOf(createRow));
                com_ciot_realm_db_ChildTaskRealmProxyInterface com_ciot_realm_db_childtaskrealmproxyinterface = childTask;
                long j = nativePtr;
                long j2 = createRow;
                Table.nativeSetBoolean(j, childTaskColumnInfo.isPointEditedColKey, j2, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$isPointEdited(), false);
                Table.nativeSetBoolean(j, childTaskColumnInfo.isPathEditedColKey, j2, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$isPathEdited(), false);
                String realmGet$pointName = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$pointName();
                if (realmGet$pointName != null) {
                    Table.nativeSetString(nativePtr, childTaskColumnInfo.pointNameColKey, createRow, realmGet$pointName, false);
                } else {
                    Table.nativeSetNull(nativePtr, childTaskColumnInfo.pointNameColKey, createRow, false);
                }
                String realmGet$taskNodeID = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$taskNodeID();
                if (realmGet$taskNodeID != null) {
                    Table.nativeSetString(nativePtr, childTaskColumnInfo.taskNodeIDColKey, createRow, realmGet$taskNodeID, false);
                } else {
                    Table.nativeSetNull(nativePtr, childTaskColumnInfo.taskNodeIDColKey, createRow, false);
                }
                String realmGet$firstSentence = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$firstSentence();
                if (realmGet$firstSentence != null) {
                    Table.nativeSetString(nativePtr, childTaskColumnInfo.firstSentenceColKey, createRow, realmGet$firstSentence, false);
                } else {
                    Table.nativeSetNull(nativePtr, childTaskColumnInfo.firstSentenceColKey, createRow, false);
                }
                String realmGet$transition = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$transition();
                if (realmGet$transition != null) {
                    Table.nativeSetString(nativePtr, childTaskColumnInfo.transitionColKey, createRow, realmGet$transition, false);
                } else {
                    Table.nativeSetNull(nativePtr, childTaskColumnInfo.transitionColKey, createRow, false);
                }
                long j3 = nativePtr;
                long j4 = createRow;
                Table.nativeSetFloat(j3, childTaskColumnInfo.xColKey, j4, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$x(), false);
                Table.nativeSetFloat(j3, childTaskColumnInfo.yColKey, j4, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$y(), false);
                Table.nativeSetLong(nativePtr, childTaskColumnInfo.zColKey, j4, (long) com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$z(), false);
                long j5 = nativePtr;
                Table.nativeSetLong(j5, childTaskColumnInfo.p_typeColKey, j4, (long) com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$p_type(), false);
                Table.nativeSetFloat(j5, childTaskColumnInfo.angleColKey, j4, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$angle(), false);
                String realmGet$mapinfo = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$mapinfo();
                if (realmGet$mapinfo != null) {
                    Table.nativeSetString(nativePtr, childTaskColumnInfo.mapinfoColKey, createRow, realmGet$mapinfo, false);
                } else {
                    Table.nativeSetNull(nativePtr, childTaskColumnInfo.mapinfoColKey, createRow, false);
                }
                Point realmGet$point = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$point();
                if (realmGet$point != null) {
                    Long l = map2.get(realmGet$point);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_patrol_PointRealmProxy.insertOrUpdate(realm2, realmGet$point, map2));
                    }
                    Table.nativeSetLink(nativePtr, childTaskColumnInfo.pointColKey, createRow, l.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, childTaskColumnInfo.pointColKey, createRow);
                }
                Process realmGet$process = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$process();
                if (realmGet$process != null) {
                    Long l2 = map2.get(realmGet$process);
                    if (l2 == null) {
                        l2 = Long.valueOf(com_ciot_realm_db_patrol_ProcessRealmProxy.insertOrUpdate(realm2, realmGet$process, map2));
                    }
                    Table.nativeSetLink(nativePtr, childTaskColumnInfo.processColKey, createRow, l2.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, childTaskColumnInfo.processColKey, createRow);
                }
                Table.nativeSetBoolean(nativePtr, childTaskColumnInfo.waitPlayMediaColKey, createRow, com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$waitPlayMedia(), false);
                Boolean realmGet$isSelected = com_ciot_realm_db_childtaskrealmproxyinterface.realmGet$isSelected();
                if (realmGet$isSelected != null) {
                    Table.nativeSetBoolean(nativePtr, childTaskColumnInfo.isSelectedColKey, createRow, realmGet$isSelected.booleanValue(), false);
                } else {
                    Table.nativeSetNull(nativePtr, childTaskColumnInfo.isSelectedColKey, createRow, false);
                }
            }
        }
    }

    public static ChildTask createDetachedCopy(ChildTask childTask, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        ChildTask childTask2;
        if (i > i2 || childTask == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(childTask);
        if (cacheData == null) {
            childTask2 = new ChildTask();
            map.put(childTask, new RealmObjectProxy.CacheData(i, childTask2));
        } else if (i >= cacheData.minDepth) {
            return (ChildTask) cacheData.object;
        } else {
            cacheData.minDepth = i;
            childTask2 = (ChildTask) cacheData.object;
        }
        com_ciot_realm_db_ChildTaskRealmProxyInterface com_ciot_realm_db_childtaskrealmproxyinterface = childTask2;
        com_ciot_realm_db_ChildTaskRealmProxyInterface com_ciot_realm_db_childtaskrealmproxyinterface2 = childTask;
        com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$isPointEdited(com_ciot_realm_db_childtaskrealmproxyinterface2.realmGet$isPointEdited());
        com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$isPathEdited(com_ciot_realm_db_childtaskrealmproxyinterface2.realmGet$isPathEdited());
        com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$pointName(com_ciot_realm_db_childtaskrealmproxyinterface2.realmGet$pointName());
        com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$taskNodeID(com_ciot_realm_db_childtaskrealmproxyinterface2.realmGet$taskNodeID());
        com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$firstSentence(com_ciot_realm_db_childtaskrealmproxyinterface2.realmGet$firstSentence());
        com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$transition(com_ciot_realm_db_childtaskrealmproxyinterface2.realmGet$transition());
        com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$x(com_ciot_realm_db_childtaskrealmproxyinterface2.realmGet$x());
        com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$y(com_ciot_realm_db_childtaskrealmproxyinterface2.realmGet$y());
        com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$z(com_ciot_realm_db_childtaskrealmproxyinterface2.realmGet$z());
        com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$p_type(com_ciot_realm_db_childtaskrealmproxyinterface2.realmGet$p_type());
        com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$angle(com_ciot_realm_db_childtaskrealmproxyinterface2.realmGet$angle());
        com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$mapinfo(com_ciot_realm_db_childtaskrealmproxyinterface2.realmGet$mapinfo());
        int i3 = i + 1;
        com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$point(com_ciot_realm_db_patrol_PointRealmProxy.createDetachedCopy(com_ciot_realm_db_childtaskrealmproxyinterface2.realmGet$point(), i3, i2, map));
        com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$process(com_ciot_realm_db_patrol_ProcessRealmProxy.createDetachedCopy(com_ciot_realm_db_childtaskrealmproxyinterface2.realmGet$process(), i3, i2, map));
        com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$waitPlayMedia(com_ciot_realm_db_childtaskrealmproxyinterface2.realmGet$waitPlayMedia());
        com_ciot_realm_db_childtaskrealmproxyinterface.realmSet$isSelected(com_ciot_realm_db_childtaskrealmproxyinterface2.realmGet$isSelected());
        return childTask2;
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
}
