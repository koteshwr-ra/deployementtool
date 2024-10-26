package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.patrol.Action;
import com.ciot.realm.db.patrol.Line;
import com.ciot.realm.db.patrol.Operation;
import com.ciot.realm.db.patrol.Place;
import com.ciot.realm.db.patrol.TransitionBean;
import com.ciot.realm.db.patrol.WaitBean;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_patrol_ActionRealmProxy;
import io.realm.com_ciot_realm_db_patrol_OperationRealmProxy;
import io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy;
import io.realm.com_ciot_realm_db_patrol_TransitionBeanRealmProxy;
import io.realm.com_ciot_realm_db_patrol_WaitBeanRealmProxy;
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

public class com_ciot_realm_db_patrol_LineRealmProxy extends Line implements RealmObjectProxy, com_ciot_realm_db_patrol_LineRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private LineColumnInfo columnInfo;
    private RealmList<Operation> operRealmList;
    private ProxyState<Line> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Line";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class LineColumnInfo extends ColumnInfo {
        long actionColKey;
        long descriptionColKey;
        long idColKey;
        long onwayColKey;
        long operColKey;
        long placeInfoColKey;
        long repeatColKey;
        long taskIdColKey;
        long transitionColKey;
        long waitColKey;

        LineColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(10);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.taskIdColKey = addColumnDetails("taskId", "taskId", objectSchemaInfo);
            this.placeInfoColKey = addColumnDetails("placeInfo", "placeInfo", objectSchemaInfo);
            this.onwayColKey = addColumnDetails("onway", "onway", objectSchemaInfo);
            this.actionColKey = addColumnDetails("action", "action", objectSchemaInfo);
            this.transitionColKey = addColumnDetails("transition", "transition", objectSchemaInfo);
            this.waitColKey = addColumnDetails("wait", "wait", objectSchemaInfo);
            this.descriptionColKey = addColumnDetails("description", "description", objectSchemaInfo);
            this.repeatColKey = addColumnDetails("repeat", "repeat", objectSchemaInfo);
            this.operColKey = addColumnDetails("oper", "oper", objectSchemaInfo);
        }

        LineColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new LineColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            LineColumnInfo lineColumnInfo = (LineColumnInfo) columnInfo;
            LineColumnInfo lineColumnInfo2 = (LineColumnInfo) columnInfo2;
            lineColumnInfo2.idColKey = lineColumnInfo.idColKey;
            lineColumnInfo2.taskIdColKey = lineColumnInfo.taskIdColKey;
            lineColumnInfo2.placeInfoColKey = lineColumnInfo.placeInfoColKey;
            lineColumnInfo2.onwayColKey = lineColumnInfo.onwayColKey;
            lineColumnInfo2.actionColKey = lineColumnInfo.actionColKey;
            lineColumnInfo2.transitionColKey = lineColumnInfo.transitionColKey;
            lineColumnInfo2.waitColKey = lineColumnInfo.waitColKey;
            lineColumnInfo2.descriptionColKey = lineColumnInfo.descriptionColKey;
            lineColumnInfo2.repeatColKey = lineColumnInfo.repeatColKey;
            lineColumnInfo2.operColKey = lineColumnInfo.operColKey;
        }
    }

    com_ciot_realm_db_patrol_LineRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (LineColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<Line> proxyState2 = new ProxyState<>(this);
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

    public String realmGet$taskId() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.taskIdColKey);
    }

    public void realmSet$taskId(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.taskIdColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.taskIdColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.taskIdColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.taskIdColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public Place realmGet$placeInfo() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.placeInfoColKey)) {
            return null;
        }
        return (Place) this.proxyState.getRealm$realm().get(Place.class, this.proxyState.getRow$realm().getLink(this.columnInfo.placeInfoColKey), false, Collections.emptyList());
    }

    public void realmSet$placeInfo(Place place) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (place == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.placeInfoColKey);
                return;
            }
            this.proxyState.checkValidObject(place);
            this.proxyState.getRow$realm().setLink(this.columnInfo.placeInfoColKey, ((RealmObjectProxy) place).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("placeInfo")) {
            if (place != null && !RealmObject.isManaged(place)) {
                place = (Place) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(place, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (place == null) {
                row$realm.nullifyLink(this.columnInfo.placeInfoColKey);
                return;
            }
            this.proxyState.checkValidObject(place);
            row$realm.getTable().setLink(this.columnInfo.placeInfoColKey, row$realm.getObjectKey(), ((RealmObjectProxy) place).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public Action realmGet$onway() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.onwayColKey)) {
            return null;
        }
        return (Action) this.proxyState.getRealm$realm().get(Action.class, this.proxyState.getRow$realm().getLink(this.columnInfo.onwayColKey), false, Collections.emptyList());
    }

    public void realmSet$onway(Action action) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (action == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.onwayColKey);
                return;
            }
            this.proxyState.checkValidObject(action);
            this.proxyState.getRow$realm().setLink(this.columnInfo.onwayColKey, ((RealmObjectProxy) action).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("onway")) {
            if (action != null && !RealmObject.isManaged(action)) {
                action = (Action) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(action, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (action == null) {
                row$realm.nullifyLink(this.columnInfo.onwayColKey);
                return;
            }
            this.proxyState.checkValidObject(action);
            row$realm.getTable().setLink(this.columnInfo.onwayColKey, row$realm.getObjectKey(), ((RealmObjectProxy) action).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public Action realmGet$action() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.actionColKey)) {
            return null;
        }
        return (Action) this.proxyState.getRealm$realm().get(Action.class, this.proxyState.getRow$realm().getLink(this.columnInfo.actionColKey), false, Collections.emptyList());
    }

    public void realmSet$action(Action action) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (action == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.actionColKey);
                return;
            }
            this.proxyState.checkValidObject(action);
            this.proxyState.getRow$realm().setLink(this.columnInfo.actionColKey, ((RealmObjectProxy) action).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("action")) {
            if (action != null && !RealmObject.isManaged(action)) {
                action = (Action) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(action, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (action == null) {
                row$realm.nullifyLink(this.columnInfo.actionColKey);
                return;
            }
            this.proxyState.checkValidObject(action);
            row$realm.getTable().setLink(this.columnInfo.actionColKey, row$realm.getObjectKey(), ((RealmObjectProxy) action).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public TransitionBean realmGet$transition() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.transitionColKey)) {
            return null;
        }
        return (TransitionBean) this.proxyState.getRealm$realm().get(TransitionBean.class, this.proxyState.getRow$realm().getLink(this.columnInfo.transitionColKey), false, Collections.emptyList());
    }

    public void realmSet$transition(TransitionBean transitionBean) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (transitionBean == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.transitionColKey);
                return;
            }
            this.proxyState.checkValidObject(transitionBean);
            this.proxyState.getRow$realm().setLink(this.columnInfo.transitionColKey, ((RealmObjectProxy) transitionBean).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("transition")) {
            if (transitionBean != null && !RealmObject.isManaged(transitionBean)) {
                transitionBean = (TransitionBean) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(transitionBean, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (transitionBean == null) {
                row$realm.nullifyLink(this.columnInfo.transitionColKey);
                return;
            }
            this.proxyState.checkValidObject(transitionBean);
            row$realm.getTable().setLink(this.columnInfo.transitionColKey, row$realm.getObjectKey(), ((RealmObjectProxy) transitionBean).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public WaitBean realmGet$wait() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.waitColKey)) {
            return null;
        }
        return (WaitBean) this.proxyState.getRealm$realm().get(WaitBean.class, this.proxyState.getRow$realm().getLink(this.columnInfo.waitColKey), false, Collections.emptyList());
    }

    public void realmSet$wait(WaitBean waitBean) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (waitBean == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.waitColKey);
                return;
            }
            this.proxyState.checkValidObject(waitBean);
            this.proxyState.getRow$realm().setLink(this.columnInfo.waitColKey, ((RealmObjectProxy) waitBean).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("wait")) {
            if (waitBean != null && !RealmObject.isManaged(waitBean)) {
                waitBean = (WaitBean) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(waitBean, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (waitBean == null) {
                row$realm.nullifyLink(this.columnInfo.waitColKey);
                return;
            }
            this.proxyState.checkValidObject(waitBean);
            row$realm.getTable().setLink(this.columnInfo.waitColKey, row$realm.getObjectKey(), ((RealmObjectProxy) waitBean).realmGet$proxyState().getRow$realm().getObjectKey(), true);
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

    public int realmGet$repeat() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.repeatColKey);
    }

    public void realmSet$repeat(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.repeatColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.repeatColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public RealmList<Operation> realmGet$oper() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<Operation> realmList = this.operRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<Operation> realmList2 = new RealmList<>(Operation.class, this.proxyState.getRow$realm().getModelList(this.columnInfo.operColKey), this.proxyState.getRealm$realm());
        this.operRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$oper(RealmList<Operation> realmList) {
        int i = 0;
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("oper")) {
                return;
            }
            if (realmList != null && !realmList.isManaged()) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<Operation> realmList2 = new RealmList<>();
                Iterator<Operation> it = realmList.iterator();
                while (it.hasNext()) {
                    Operation next = it.next();
                    if (next == null || RealmObject.isManaged(next)) {
                        realmList2.add(next);
                    } else {
                        realmList2.add((Operation) realm.copyToRealm(next, new ImportFlag[0]));
                    }
                }
                realmList = realmList2;
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList modelList = this.proxyState.getRow$realm().getModelList(this.columnInfo.operColKey);
        if (realmList == null || ((long) realmList.size()) != modelList.size()) {
            modelList.removeAll();
            if (realmList != null) {
                int size = realmList.size();
                while (i < size) {
                    Operation operation = realmList.get(i);
                    this.proxyState.checkValidObject(operation);
                    modelList.addRow(((RealmObjectProxy) operation).realmGet$proxyState().getRow$realm().getObjectKey());
                    i++;
                }
                return;
            }
            return;
        }
        int size2 = realmList.size();
        while (i < size2) {
            Operation operation2 = realmList.get(i);
            this.proxyState.checkValidObject(operation2);
            modelList.setRow((long) i, ((RealmObjectProxy) operation2).realmGet$proxyState().getRow$realm().getObjectKey());
            i++;
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 10, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("id", RealmFieldType.STRING, true, false, false);
        builder2.addPersistedProperty("taskId", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("placeInfo", RealmFieldType.OBJECT, com_ciot_realm_db_patrol_PlaceRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedLinkProperty("onway", RealmFieldType.OBJECT, com_ciot_realm_db_patrol_ActionRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedLinkProperty("action", RealmFieldType.OBJECT, com_ciot_realm_db_patrol_ActionRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedLinkProperty("transition", RealmFieldType.OBJECT, com_ciot_realm_db_patrol_TransitionBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedLinkProperty("wait", RealmFieldType.OBJECT, "WaitBean");
        OsObjectSchemaInfo.Builder builder3 = builder;
        builder3.addPersistedProperty("description", RealmFieldType.STRING, false, false, false);
        builder3.addPersistedProperty("repeat", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedLinkProperty("oper", RealmFieldType.LIST, com_ciot_realm_db_patrol_OperationRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static LineColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new LineColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r0v4, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0166  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0183  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01b8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.Line createOrUpdateUsingJsonObject(io.realm.Realm r13, org.json.JSONObject r14, boolean r15) throws org.json.JSONException {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 6
            r0.<init>(r1)
            java.lang.String r1 = "id"
            r2 = 0
            if (r15 == 0) goto L_0x0066
            java.lang.Class<com.ciot.realm.db.patrol.Line> r3 = com.ciot.realm.db.patrol.Line.class
            io.realm.internal.Table r3 = r13.getTable(r3)
            io.realm.RealmSchema r4 = r13.getSchema()
            java.lang.Class<com.ciot.realm.db.patrol.Line> r5 = com.ciot.realm.db.patrol.Line.class
            io.realm.internal.ColumnInfo r4 = r4.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r5)
            io.realm.com_ciot_realm_db_patrol_LineRealmProxy$LineColumnInfo r4 = (io.realm.com_ciot_realm_db_patrol_LineRealmProxy.LineColumnInfo) r4
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
            java.lang.Class<com.ciot.realm.db.patrol.Line> r4 = com.ciot.realm.db.patrol.Line.class
            io.realm.internal.ColumnInfo r10 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)     // Catch:{ all -> 0x0061 }
            r11 = 0
            java.util.List r12 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0061 }
            r7 = r6
            r8 = r13
            r7.set(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0061 }
            io.realm.com_ciot_realm_db_patrol_LineRealmProxy r3 = new io.realm.com_ciot_realm_db_patrol_LineRealmProxy     // Catch:{ all -> 0x0061 }
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
            java.lang.String r4 = "oper"
            java.lang.String r5 = "wait"
            java.lang.String r6 = "transition"
            java.lang.String r7 = "action"
            java.lang.String r8 = "onway"
            java.lang.String r9 = "placeInfo"
            if (r3 != 0) goto L_0x00d8
            boolean r3 = r14.has(r9)
            if (r3 == 0) goto L_0x007e
            r0.add(r9)
        L_0x007e:
            boolean r3 = r14.has(r8)
            if (r3 == 0) goto L_0x0087
            r0.add(r8)
        L_0x0087:
            boolean r3 = r14.has(r7)
            if (r3 == 0) goto L_0x0090
            r0.add(r7)
        L_0x0090:
            boolean r3 = r14.has(r6)
            if (r3 == 0) goto L_0x0099
            r0.add(r6)
        L_0x0099:
            boolean r3 = r14.has(r5)
            if (r3 == 0) goto L_0x00a2
            r0.add(r5)
        L_0x00a2:
            boolean r3 = r14.has(r4)
            if (r3 == 0) goto L_0x00ab
            r0.add(r4)
        L_0x00ab:
            boolean r3 = r14.has(r1)
            if (r3 == 0) goto L_0x00d0
            boolean r3 = r14.isNull(r1)
            r10 = 1
            if (r3 == 0) goto L_0x00c2
            java.lang.Class<com.ciot.realm.db.patrol.Line> r1 = com.ciot.realm.db.patrol.Line.class
            io.realm.RealmModel r0 = r13.createObjectInternal(r1, r2, r10, r0)
            r3 = r0
            io.realm.com_ciot_realm_db_patrol_LineRealmProxy r3 = (io.realm.com_ciot_realm_db_patrol_LineRealmProxy) r3
            goto L_0x00d8
        L_0x00c2:
            java.lang.Class<com.ciot.realm.db.patrol.Line> r3 = com.ciot.realm.db.patrol.Line.class
            java.lang.String r1 = r14.getString(r1)
            io.realm.RealmModel r0 = r13.createObjectInternal(r3, r1, r10, r0)
            r3 = r0
            io.realm.com_ciot_realm_db_patrol_LineRealmProxy r3 = (io.realm.com_ciot_realm_db_patrol_LineRealmProxy) r3
            goto L_0x00d8
        L_0x00d0:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "JSON object doesn't have the primary key field 'id'."
            r13.<init>(r14)
            throw r13
        L_0x00d8:
            r0 = r3
            io.realm.com_ciot_realm_db_patrol_LineRealmProxyInterface r0 = (io.realm.com_ciot_realm_db_patrol_LineRealmProxyInterface) r0
            java.lang.String r1 = "taskId"
            boolean r10 = r14.has(r1)
            if (r10 == 0) goto L_0x00f4
            boolean r10 = r14.isNull(r1)
            if (r10 == 0) goto L_0x00ed
            r0.realmSet$taskId(r2)
            goto L_0x00f4
        L_0x00ed:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$taskId(r1)
        L_0x00f4:
            boolean r1 = r14.has(r9)
            if (r1 == 0) goto L_0x010f
            boolean r1 = r14.isNull(r9)
            if (r1 == 0) goto L_0x0104
            r0.realmSet$placeInfo(r2)
            goto L_0x010f
        L_0x0104:
            org.json.JSONObject r1 = r14.getJSONObject(r9)
            com.ciot.realm.db.patrol.Place r1 = io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$placeInfo(r1)
        L_0x010f:
            boolean r1 = r14.has(r8)
            if (r1 == 0) goto L_0x012a
            boolean r1 = r14.isNull(r8)
            if (r1 == 0) goto L_0x011f
            r0.realmSet$onway(r2)
            goto L_0x012a
        L_0x011f:
            org.json.JSONObject r1 = r14.getJSONObject(r8)
            com.ciot.realm.db.patrol.Action r1 = io.realm.com_ciot_realm_db_patrol_ActionRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$onway(r1)
        L_0x012a:
            boolean r1 = r14.has(r7)
            if (r1 == 0) goto L_0x0145
            boolean r1 = r14.isNull(r7)
            if (r1 == 0) goto L_0x013a
            r0.realmSet$action(r2)
            goto L_0x0145
        L_0x013a:
            org.json.JSONObject r1 = r14.getJSONObject(r7)
            com.ciot.realm.db.patrol.Action r1 = io.realm.com_ciot_realm_db_patrol_ActionRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$action(r1)
        L_0x0145:
            boolean r1 = r14.has(r6)
            if (r1 == 0) goto L_0x0160
            boolean r1 = r14.isNull(r6)
            if (r1 == 0) goto L_0x0155
            r0.realmSet$transition(r2)
            goto L_0x0160
        L_0x0155:
            org.json.JSONObject r1 = r14.getJSONObject(r6)
            com.ciot.realm.db.patrol.TransitionBean r1 = io.realm.com_ciot_realm_db_patrol_TransitionBeanRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$transition(r1)
        L_0x0160:
            boolean r1 = r14.has(r5)
            if (r1 == 0) goto L_0x017b
            boolean r1 = r14.isNull(r5)
            if (r1 == 0) goto L_0x0170
            r0.realmSet$wait(r2)
            goto L_0x017b
        L_0x0170:
            org.json.JSONObject r1 = r14.getJSONObject(r5)
            com.ciot.realm.db.patrol.WaitBean r1 = io.realm.com_ciot_realm_db_patrol_WaitBeanRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$wait(r1)
        L_0x017b:
            java.lang.String r1 = "description"
            boolean r5 = r14.has(r1)
            if (r5 == 0) goto L_0x0194
            boolean r5 = r14.isNull(r1)
            if (r5 == 0) goto L_0x018d
            r0.realmSet$description(r2)
            goto L_0x0194
        L_0x018d:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$description(r1)
        L_0x0194:
            java.lang.String r1 = "repeat"
            boolean r5 = r14.has(r1)
            if (r5 == 0) goto L_0x01b2
            boolean r5 = r14.isNull(r1)
            if (r5 != 0) goto L_0x01aa
            int r1 = r14.getInt(r1)
            r0.realmSet$repeat(r1)
            goto L_0x01b2
        L_0x01aa:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'repeat' to null."
            r13.<init>(r14)
            throw r13
        L_0x01b2:
            boolean r1 = r14.has(r4)
            if (r1 == 0) goto L_0x01e6
            boolean r1 = r14.isNull(r4)
            if (r1 == 0) goto L_0x01c2
            r0.realmSet$oper(r2)
            goto L_0x01e6
        L_0x01c2:
            io.realm.RealmList r1 = r0.realmGet$oper()
            r1.clear()
            org.json.JSONArray r14 = r14.getJSONArray(r4)
            r1 = 0
        L_0x01ce:
            int r2 = r14.length()
            if (r1 >= r2) goto L_0x01e6
            org.json.JSONObject r2 = r14.getJSONObject(r1)
            com.ciot.realm.db.patrol.Operation r2 = io.realm.com_ciot_realm_db_patrol_OperationRealmProxy.createOrUpdateUsingJsonObject(r13, r2, r15)
            io.realm.RealmList r4 = r0.realmGet$oper()
            r4.add(r2)
            int r1 = r1 + 1
            goto L_0x01ce
        L_0x01e6:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_LineRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.patrol.Line");
    }

    public static Line createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        Line line = new Line();
        com_ciot_realm_db_patrol_LineRealmProxyInterface com_ciot_realm_db_patrol_linerealmproxyinterface = line;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$id((String) null);
                }
                z = true;
            } else if (nextName.equals("taskId")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$taskId(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$taskId((String) null);
                }
            } else if (nextName.equals("placeInfo")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$placeInfo((Place) null);
                } else {
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$placeInfo(com_ciot_realm_db_patrol_PlaceRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("onway")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$onway((Action) null);
                } else {
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$onway(com_ciot_realm_db_patrol_ActionRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("action")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$action((Action) null);
                } else {
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$action(com_ciot_realm_db_patrol_ActionRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("transition")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$transition((TransitionBean) null);
                } else {
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$transition(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("wait")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$wait((WaitBean) null);
                } else {
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$wait(com_ciot_realm_db_patrol_WaitBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("description")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$description(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$description((String) null);
                }
            } else if (nextName.equals("repeat")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$repeat(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'repeat' to null.");
                }
            } else if (!nextName.equals("oper")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.skipValue();
                com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$oper((RealmList<Operation>) null);
            } else {
                com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$oper(new RealmList());
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$oper().add(com_ciot_realm_db_patrol_OperationRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
                jsonReader.endArray();
            }
        }
        jsonReader.endObject();
        if (z) {
            return (Line) realm.copyToRealm(line, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_patrol_LineRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) Line.class), false, Collections.emptyList());
        com_ciot_realm_db_patrol_LineRealmProxy com_ciot_realm_db_patrol_linerealmproxy = new com_ciot_realm_db_patrol_LineRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_patrol_linerealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.Line copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_patrol_LineRealmProxy.LineColumnInfo r9, com.ciot.realm.db.patrol.Line r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.patrol.Line r1 = (com.ciot.realm.db.patrol.Line) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.patrol.Line> r2 = com.ciot.realm.db.patrol.Line.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_patrol_LineRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_patrol_LineRealmProxyInterface) r5
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
            io.realm.com_ciot_realm_db_patrol_LineRealmProxy r1 = new io.realm.com_ciot_realm_db_patrol_LineRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.patrol.Line r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.patrol.Line r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_LineRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_patrol_LineRealmProxy$LineColumnInfo, com.ciot.realm.db.patrol.Line, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.patrol.Line");
    }

    public static Line copy(Realm realm, LineColumnInfo lineColumnInfo, Line line, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        RealmObjectProxy realmObjectProxy = map.get(line);
        if (realmObjectProxy != null) {
            return (Line) realmObjectProxy;
        }
        com_ciot_realm_db_patrol_LineRealmProxyInterface com_ciot_realm_db_patrol_linerealmproxyinterface = line;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Line.class), set);
        osObjectBuilder.addString(lineColumnInfo.idColKey, com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$id());
        osObjectBuilder.addString(lineColumnInfo.taskIdColKey, com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$taskId());
        osObjectBuilder.addString(lineColumnInfo.descriptionColKey, com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$description());
        osObjectBuilder.addInteger(lineColumnInfo.repeatColKey, Integer.valueOf(com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$repeat()));
        com_ciot_realm_db_patrol_LineRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map.put(line, newProxyInstance);
        Place realmGet$placeInfo = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$placeInfo();
        if (realmGet$placeInfo == null) {
            newProxyInstance.realmSet$placeInfo((Place) null);
        } else {
            Place place = (Place) map.get(realmGet$placeInfo);
            if (place != null) {
                newProxyInstance.realmSet$placeInfo(place);
            } else {
                newProxyInstance.realmSet$placeInfo(com_ciot_realm_db_patrol_PlaceRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_PlaceRealmProxy.PlaceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Place.class), realmGet$placeInfo, z, map, set));
            }
        }
        Action realmGet$onway = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$onway();
        if (realmGet$onway == null) {
            newProxyInstance.realmSet$onway((Action) null);
        } else {
            Action action = (Action) map.get(realmGet$onway);
            if (action != null) {
                newProxyInstance.realmSet$onway(action);
            } else {
                newProxyInstance.realmSet$onway(com_ciot_realm_db_patrol_ActionRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ActionRealmProxy.ActionColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Action.class), realmGet$onway, z, map, set));
            }
        }
        Action realmGet$action = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$action();
        if (realmGet$action == null) {
            newProxyInstance.realmSet$action((Action) null);
        } else {
            Action action2 = (Action) map.get(realmGet$action);
            if (action2 != null) {
                newProxyInstance.realmSet$action(action2);
            } else {
                newProxyInstance.realmSet$action(com_ciot_realm_db_patrol_ActionRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ActionRealmProxy.ActionColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Action.class), realmGet$action, z, map, set));
            }
        }
        TransitionBean realmGet$transition = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$transition();
        if (realmGet$transition == null) {
            newProxyInstance.realmSet$transition((TransitionBean) null);
        } else {
            TransitionBean transitionBean = (TransitionBean) map.get(realmGet$transition);
            if (transitionBean != null) {
                newProxyInstance.realmSet$transition(transitionBean);
            } else {
                newProxyInstance.realmSet$transition(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_TransitionBeanRealmProxy.TransitionBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TransitionBean.class), realmGet$transition, z, map, set));
            }
        }
        WaitBean realmGet$wait = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$wait();
        if (realmGet$wait == null) {
            newProxyInstance.realmSet$wait((WaitBean) null);
        } else {
            WaitBean waitBean = (WaitBean) map.get(realmGet$wait);
            if (waitBean != null) {
                newProxyInstance.realmSet$wait(waitBean);
            } else {
                newProxyInstance.realmSet$wait(com_ciot_realm_db_patrol_WaitBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_WaitBeanRealmProxy.WaitBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) WaitBean.class), realmGet$wait, z, map, set));
            }
        }
        RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$oper();
        if (realmGet$oper != null) {
            RealmList<Operation> realmGet$oper2 = newProxyInstance.realmGet$oper();
            realmGet$oper2.clear();
            for (int i = 0; i < realmGet$oper.size(); i++) {
                Operation operation = realmGet$oper.get(i);
                Operation operation2 = (Operation) map.get(operation);
                if (operation2 != null) {
                    realmGet$oper2.add(operation2);
                } else {
                    realmGet$oper2.add(com_ciot_realm_db_patrol_OperationRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_OperationRealmProxy.OperationColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Operation.class), operation, z, map, set));
                }
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, Line line, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Realm realm2 = realm;
        Line line2 = line;
        Map<RealmModel, Long> map2 = map;
        if ((line2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(line)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) line2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(Line.class);
        long nativePtr = table.getNativePtr();
        LineColumnInfo lineColumnInfo = (LineColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Line.class);
        long j3 = lineColumnInfo.idColKey;
        com_ciot_realm_db_patrol_LineRealmProxyInterface com_ciot_realm_db_patrol_linerealmproxyinterface = line2;
        String realmGet$id = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j3);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j3, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j3, realmGet$id);
        } else {
            Table.throwDuplicatePrimaryKeyException(realmGet$id);
        }
        long j4 = j;
        map2.put(line2, Long.valueOf(j4));
        String realmGet$taskId = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$taskId();
        if (realmGet$taskId != null) {
            j2 = j4;
            Table.nativeSetString(nativePtr, lineColumnInfo.taskIdColKey, j4, realmGet$taskId, false);
        } else {
            j2 = j4;
        }
        Place realmGet$placeInfo = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$placeInfo();
        if (realmGet$placeInfo != null) {
            Long l = map2.get(realmGet$placeInfo);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_patrol_PlaceRealmProxy.insert(realm2, realmGet$placeInfo, map2));
            }
            Table.nativeSetLink(nativePtr, lineColumnInfo.placeInfoColKey, j2, l.longValue(), false);
        }
        Action realmGet$onway = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$onway();
        if (realmGet$onway != null) {
            Long l2 = map2.get(realmGet$onway);
            if (l2 == null) {
                l2 = Long.valueOf(com_ciot_realm_db_patrol_ActionRealmProxy.insert(realm2, realmGet$onway, map2));
            }
            Table.nativeSetLink(nativePtr, lineColumnInfo.onwayColKey, j2, l2.longValue(), false);
        }
        Action realmGet$action = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$action();
        if (realmGet$action != null) {
            Long l3 = map2.get(realmGet$action);
            if (l3 == null) {
                l3 = Long.valueOf(com_ciot_realm_db_patrol_ActionRealmProxy.insert(realm2, realmGet$action, map2));
            }
            Table.nativeSetLink(nativePtr, lineColumnInfo.actionColKey, j2, l3.longValue(), false);
        }
        TransitionBean realmGet$transition = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$transition();
        if (realmGet$transition != null) {
            Long l4 = map2.get(realmGet$transition);
            if (l4 == null) {
                l4 = Long.valueOf(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.insert(realm2, realmGet$transition, map2));
            }
            Table.nativeSetLink(nativePtr, lineColumnInfo.transitionColKey, j2, l4.longValue(), false);
        }
        WaitBean realmGet$wait = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$wait();
        if (realmGet$wait != null) {
            Long l5 = map2.get(realmGet$wait);
            if (l5 == null) {
                l5 = Long.valueOf(com_ciot_realm_db_patrol_WaitBeanRealmProxy.insert(realm2, realmGet$wait, map2));
            }
            Table.nativeSetLink(nativePtr, lineColumnInfo.waitColKey, j2, l5.longValue(), false);
        }
        String realmGet$description = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, lineColumnInfo.descriptionColKey, j2, realmGet$description, false);
        }
        Table.nativeSetLong(nativePtr, lineColumnInfo.repeatColKey, j2, (long) com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$repeat(), false);
        RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$oper();
        if (realmGet$oper == null) {
            return j2;
        }
        long j5 = j2;
        OsList osList = new OsList(table.getUncheckedRow(j5), lineColumnInfo.operColKey);
        Iterator<Operation> it = realmGet$oper.iterator();
        while (it.hasNext()) {
            Operation next = it.next();
            Long l6 = map2.get(next);
            if (l6 == null) {
                l6 = Long.valueOf(com_ciot_realm_db_patrol_OperationRealmProxy.insert(realm2, next, map2));
            }
            osList.addRow(l6.longValue());
        }
        return j5;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(Line.class);
        long nativePtr = table.getNativePtr();
        LineColumnInfo lineColumnInfo = (LineColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Line.class);
        long j4 = lineColumnInfo.idColKey;
        while (it.hasNext()) {
            Line line = (Line) it.next();
            if (!map2.containsKey(line)) {
                if ((line instanceof RealmObjectProxy) && !RealmObject.isFrozen(line)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) line;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(line, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_LineRealmProxyInterface com_ciot_realm_db_patrol_linerealmproxyinterface = line;
                String realmGet$id = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j4);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j4, realmGet$id);
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j4, realmGet$id);
                } else {
                    Table.throwDuplicatePrimaryKeyException(realmGet$id);
                }
                long j5 = j;
                map2.put(line, Long.valueOf(j5));
                String realmGet$taskId = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$taskId();
                if (realmGet$taskId != null) {
                    j3 = j5;
                    j2 = j4;
                    Table.nativeSetString(nativePtr, lineColumnInfo.taskIdColKey, j5, realmGet$taskId, false);
                } else {
                    j3 = j5;
                    j2 = j4;
                }
                Place realmGet$placeInfo = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$placeInfo();
                if (realmGet$placeInfo != null) {
                    Long l = map2.get(realmGet$placeInfo);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_patrol_PlaceRealmProxy.insert(realm2, realmGet$placeInfo, map2));
                    }
                    table.setLink(lineColumnInfo.placeInfoColKey, j3, l.longValue(), false);
                }
                Action realmGet$onway = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$onway();
                if (realmGet$onway != null) {
                    Long l2 = map2.get(realmGet$onway);
                    if (l2 == null) {
                        l2 = Long.valueOf(com_ciot_realm_db_patrol_ActionRealmProxy.insert(realm2, realmGet$onway, map2));
                    }
                    table.setLink(lineColumnInfo.onwayColKey, j3, l2.longValue(), false);
                }
                Action realmGet$action = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$action();
                if (realmGet$action != null) {
                    Long l3 = map2.get(realmGet$action);
                    if (l3 == null) {
                        l3 = Long.valueOf(com_ciot_realm_db_patrol_ActionRealmProxy.insert(realm2, realmGet$action, map2));
                    }
                    table.setLink(lineColumnInfo.actionColKey, j3, l3.longValue(), false);
                }
                TransitionBean realmGet$transition = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$transition();
                if (realmGet$transition != null) {
                    Long l4 = map2.get(realmGet$transition);
                    if (l4 == null) {
                        l4 = Long.valueOf(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.insert(realm2, realmGet$transition, map2));
                    }
                    table.setLink(lineColumnInfo.transitionColKey, j3, l4.longValue(), false);
                }
                WaitBean realmGet$wait = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$wait();
                if (realmGet$wait != null) {
                    Long l5 = map2.get(realmGet$wait);
                    if (l5 == null) {
                        l5 = Long.valueOf(com_ciot_realm_db_patrol_WaitBeanRealmProxy.insert(realm2, realmGet$wait, map2));
                    }
                    table.setLink(lineColumnInfo.waitColKey, j3, l5.longValue(), false);
                }
                String realmGet$description = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, lineColumnInfo.descriptionColKey, j3, realmGet$description, false);
                }
                Table.nativeSetLong(nativePtr, lineColumnInfo.repeatColKey, j3, (long) com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$repeat(), false);
                RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$oper();
                if (realmGet$oper != null) {
                    OsList osList = new OsList(table.getUncheckedRow(j3), lineColumnInfo.operColKey);
                    Iterator<Operation> it2 = realmGet$oper.iterator();
                    while (it2.hasNext()) {
                        Operation next = it2.next();
                        Long l6 = map2.get(next);
                        if (l6 == null) {
                            l6 = Long.valueOf(com_ciot_realm_db_patrol_OperationRealmProxy.insert(realm2, next, map2));
                        }
                        osList.addRow(l6.longValue());
                    }
                }
                j4 = j2;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Line line, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Realm realm2 = realm;
        Line line2 = line;
        Map<RealmModel, Long> map2 = map;
        if ((line2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(line)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) line2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(Line.class);
        long nativePtr = table.getNativePtr();
        LineColumnInfo lineColumnInfo = (LineColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Line.class);
        long j3 = lineColumnInfo.idColKey;
        com_ciot_realm_db_patrol_LineRealmProxyInterface com_ciot_realm_db_patrol_linerealmproxyinterface = line2;
        String realmGet$id = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j3);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j3, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j3, realmGet$id);
        }
        long j4 = j;
        map2.put(line2, Long.valueOf(j4));
        String realmGet$taskId = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$taskId();
        if (realmGet$taskId != null) {
            j2 = j4;
            Table.nativeSetString(nativePtr, lineColumnInfo.taskIdColKey, j4, realmGet$taskId, false);
        } else {
            j2 = j4;
            Table.nativeSetNull(nativePtr, lineColumnInfo.taskIdColKey, j2, false);
        }
        Place realmGet$placeInfo = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$placeInfo();
        if (realmGet$placeInfo != null) {
            Long l = map2.get(realmGet$placeInfo);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_patrol_PlaceRealmProxy.insertOrUpdate(realm2, realmGet$placeInfo, map2));
            }
            Table.nativeSetLink(nativePtr, lineColumnInfo.placeInfoColKey, j2, l.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, lineColumnInfo.placeInfoColKey, j2);
        }
        Action realmGet$onway = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$onway();
        if (realmGet$onway != null) {
            Long l2 = map2.get(realmGet$onway);
            if (l2 == null) {
                l2 = Long.valueOf(com_ciot_realm_db_patrol_ActionRealmProxy.insertOrUpdate(realm2, realmGet$onway, map2));
            }
            Table.nativeSetLink(nativePtr, lineColumnInfo.onwayColKey, j2, l2.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, lineColumnInfo.onwayColKey, j2);
        }
        Action realmGet$action = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$action();
        if (realmGet$action != null) {
            Long l3 = map2.get(realmGet$action);
            if (l3 == null) {
                l3 = Long.valueOf(com_ciot_realm_db_patrol_ActionRealmProxy.insertOrUpdate(realm2, realmGet$action, map2));
            }
            Table.nativeSetLink(nativePtr, lineColumnInfo.actionColKey, j2, l3.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, lineColumnInfo.actionColKey, j2);
        }
        TransitionBean realmGet$transition = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$transition();
        if (realmGet$transition != null) {
            Long l4 = map2.get(realmGet$transition);
            if (l4 == null) {
                l4 = Long.valueOf(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.insertOrUpdate(realm2, realmGet$transition, map2));
            }
            Table.nativeSetLink(nativePtr, lineColumnInfo.transitionColKey, j2, l4.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, lineColumnInfo.transitionColKey, j2);
        }
        WaitBean realmGet$wait = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$wait();
        if (realmGet$wait != null) {
            Long l5 = map2.get(realmGet$wait);
            if (l5 == null) {
                l5 = Long.valueOf(com_ciot_realm_db_patrol_WaitBeanRealmProxy.insertOrUpdate(realm2, realmGet$wait, map2));
            }
            Table.nativeSetLink(nativePtr, lineColumnInfo.waitColKey, j2, l5.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, lineColumnInfo.waitColKey, j2);
        }
        String realmGet$description = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(nativePtr, lineColumnInfo.descriptionColKey, j2, realmGet$description, false);
        } else {
            Table.nativeSetNull(nativePtr, lineColumnInfo.descriptionColKey, j2, false);
        }
        Table.nativeSetLong(nativePtr, lineColumnInfo.repeatColKey, j2, (long) com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$repeat(), false);
        long j5 = j2;
        OsList osList = new OsList(table.getUncheckedRow(j5), lineColumnInfo.operColKey);
        RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$oper();
        if (realmGet$oper == null || ((long) realmGet$oper.size()) != osList.size()) {
            osList.removeAll();
            if (realmGet$oper != null) {
                Iterator<Operation> it = realmGet$oper.iterator();
                while (it.hasNext()) {
                    Operation next = it.next();
                    Long l6 = map2.get(next);
                    if (l6 == null) {
                        l6 = Long.valueOf(com_ciot_realm_db_patrol_OperationRealmProxy.insertOrUpdate(realm2, next, map2));
                    }
                    osList.addRow(l6.longValue());
                }
            }
        } else {
            int size = realmGet$oper.size();
            for (int i = 0; i < size; i++) {
                Operation operation = realmGet$oper.get(i);
                Long l7 = map2.get(operation);
                if (l7 == null) {
                    l7 = Long.valueOf(com_ciot_realm_db_patrol_OperationRealmProxy.insertOrUpdate(realm2, operation, map2));
                }
                osList.setRow((long) i, l7.longValue());
            }
        }
        return j5;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(Line.class);
        long nativePtr = table.getNativePtr();
        LineColumnInfo lineColumnInfo = (LineColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Line.class);
        long j4 = lineColumnInfo.idColKey;
        while (it.hasNext()) {
            Line line = (Line) it.next();
            if (!map2.containsKey(line)) {
                if ((line instanceof RealmObjectProxy) && !RealmObject.isFrozen(line)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) line;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(line, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_LineRealmProxyInterface com_ciot_realm_db_patrol_linerealmproxyinterface = line;
                String realmGet$id = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j4);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j4, realmGet$id);
                }
                if (j == -1) {
                    j = OsObject.createRowWithPrimaryKey(table, j4, realmGet$id);
                }
                long j5 = j;
                map2.put(line, Long.valueOf(j5));
                String realmGet$taskId = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$taskId();
                if (realmGet$taskId != null) {
                    j3 = j5;
                    j2 = j4;
                    Table.nativeSetString(nativePtr, lineColumnInfo.taskIdColKey, j5, realmGet$taskId, false);
                } else {
                    j3 = j5;
                    j2 = j4;
                    Table.nativeSetNull(nativePtr, lineColumnInfo.taskIdColKey, j5, false);
                }
                Place realmGet$placeInfo = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$placeInfo();
                if (realmGet$placeInfo != null) {
                    Long l = map2.get(realmGet$placeInfo);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_patrol_PlaceRealmProxy.insertOrUpdate(realm2, realmGet$placeInfo, map2));
                    }
                    Table.nativeSetLink(nativePtr, lineColumnInfo.placeInfoColKey, j3, l.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, lineColumnInfo.placeInfoColKey, j3);
                }
                Action realmGet$onway = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$onway();
                if (realmGet$onway != null) {
                    Long l2 = map2.get(realmGet$onway);
                    if (l2 == null) {
                        l2 = Long.valueOf(com_ciot_realm_db_patrol_ActionRealmProxy.insertOrUpdate(realm2, realmGet$onway, map2));
                    }
                    Table.nativeSetLink(nativePtr, lineColumnInfo.onwayColKey, j3, l2.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, lineColumnInfo.onwayColKey, j3);
                }
                Action realmGet$action = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$action();
                if (realmGet$action != null) {
                    Long l3 = map2.get(realmGet$action);
                    if (l3 == null) {
                        l3 = Long.valueOf(com_ciot_realm_db_patrol_ActionRealmProxy.insertOrUpdate(realm2, realmGet$action, map2));
                    }
                    Table.nativeSetLink(nativePtr, lineColumnInfo.actionColKey, j3, l3.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, lineColumnInfo.actionColKey, j3);
                }
                TransitionBean realmGet$transition = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$transition();
                if (realmGet$transition != null) {
                    Long l4 = map2.get(realmGet$transition);
                    if (l4 == null) {
                        l4 = Long.valueOf(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.insertOrUpdate(realm2, realmGet$transition, map2));
                    }
                    Table.nativeSetLink(nativePtr, lineColumnInfo.transitionColKey, j3, l4.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, lineColumnInfo.transitionColKey, j3);
                }
                WaitBean realmGet$wait = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$wait();
                if (realmGet$wait != null) {
                    Long l5 = map2.get(realmGet$wait);
                    if (l5 == null) {
                        l5 = Long.valueOf(com_ciot_realm_db_patrol_WaitBeanRealmProxy.insertOrUpdate(realm2, realmGet$wait, map2));
                    }
                    Table.nativeSetLink(nativePtr, lineColumnInfo.waitColKey, j3, l5.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, lineColumnInfo.waitColKey, j3);
                }
                String realmGet$description = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(nativePtr, lineColumnInfo.descriptionColKey, j3, realmGet$description, false);
                } else {
                    Table.nativeSetNull(nativePtr, lineColumnInfo.descriptionColKey, j3, false);
                }
                Table.nativeSetLong(nativePtr, lineColumnInfo.repeatColKey, j3, (long) com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$repeat(), false);
                OsList osList = new OsList(table.getUncheckedRow(j3), lineColumnInfo.operColKey);
                RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_linerealmproxyinterface.realmGet$oper();
                if (realmGet$oper == null || ((long) realmGet$oper.size()) != osList.size()) {
                    osList.removeAll();
                    if (realmGet$oper != null) {
                        Iterator<Operation> it2 = realmGet$oper.iterator();
                        while (it2.hasNext()) {
                            Operation next = it2.next();
                            Long l6 = map2.get(next);
                            if (l6 == null) {
                                l6 = Long.valueOf(com_ciot_realm_db_patrol_OperationRealmProxy.insertOrUpdate(realm2, next, map2));
                            }
                            osList.addRow(l6.longValue());
                        }
                    }
                } else {
                    int size = realmGet$oper.size();
                    for (int i = 0; i < size; i++) {
                        Operation operation = realmGet$oper.get(i);
                        Long l7 = map2.get(operation);
                        if (l7 == null) {
                            l7 = Long.valueOf(com_ciot_realm_db_patrol_OperationRealmProxy.insertOrUpdate(realm2, operation, map2));
                        }
                        osList.setRow((long) i, l7.longValue());
                    }
                }
                j4 = j2;
            }
        }
    }

    public static Line createDetachedCopy(Line line, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        Line line2;
        if (i > i2 || line == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(line);
        if (cacheData == null) {
            line2 = new Line();
            map.put(line, new RealmObjectProxy.CacheData(i, line2));
        } else if (i >= cacheData.minDepth) {
            return (Line) cacheData.object;
        } else {
            cacheData.minDepth = i;
            line2 = (Line) cacheData.object;
        }
        com_ciot_realm_db_patrol_LineRealmProxyInterface com_ciot_realm_db_patrol_linerealmproxyinterface = line2;
        com_ciot_realm_db_patrol_LineRealmProxyInterface com_ciot_realm_db_patrol_linerealmproxyinterface2 = line;
        com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$id(com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$taskId(com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$taskId());
        int i3 = i + 1;
        com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$placeInfo(com_ciot_realm_db_patrol_PlaceRealmProxy.createDetachedCopy(com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$placeInfo(), i3, i2, map));
        com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$onway(com_ciot_realm_db_patrol_ActionRealmProxy.createDetachedCopy(com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$onway(), i3, i2, map));
        com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$action(com_ciot_realm_db_patrol_ActionRealmProxy.createDetachedCopy(com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$action(), i3, i2, map));
        com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$transition(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.createDetachedCopy(com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$transition(), i3, i2, map));
        com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$wait(com_ciot_realm_db_patrol_WaitBeanRealmProxy.createDetachedCopy(com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$wait(), i3, i2, map));
        com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$description(com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$description());
        com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$repeat(com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$repeat());
        if (i == i2) {
            com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$oper((RealmList<Operation>) null);
        } else {
            RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$oper();
            RealmList realmList = new RealmList();
            com_ciot_realm_db_patrol_linerealmproxyinterface.realmSet$oper(realmList);
            int size = realmGet$oper.size();
            for (int i4 = 0; i4 < size; i4++) {
                realmList.add(com_ciot_realm_db_patrol_OperationRealmProxy.createDetachedCopy(realmGet$oper.get(i4), i3, i2, map));
            }
        }
        return line2;
    }

    static Line update(Realm realm, LineColumnInfo lineColumnInfo, Line line, Line line2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        com_ciot_realm_db_patrol_LineRealmProxyInterface com_ciot_realm_db_patrol_linerealmproxyinterface = line;
        com_ciot_realm_db_patrol_LineRealmProxyInterface com_ciot_realm_db_patrol_linerealmproxyinterface2 = line2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Line.class), set);
        osObjectBuilder.addString(lineColumnInfo.idColKey, com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$id());
        osObjectBuilder.addString(lineColumnInfo.taskIdColKey, com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$taskId());
        Place realmGet$placeInfo = com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$placeInfo();
        if (realmGet$placeInfo == null) {
            osObjectBuilder.addNull(lineColumnInfo.placeInfoColKey);
        } else {
            Place place = (Place) map.get(realmGet$placeInfo);
            if (place != null) {
                osObjectBuilder.addObject(lineColumnInfo.placeInfoColKey, place);
            } else {
                osObjectBuilder.addObject(lineColumnInfo.placeInfoColKey, com_ciot_realm_db_patrol_PlaceRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_PlaceRealmProxy.PlaceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Place.class), realmGet$placeInfo, true, map, set));
            }
        }
        Action realmGet$onway = com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$onway();
        if (realmGet$onway == null) {
            osObjectBuilder.addNull(lineColumnInfo.onwayColKey);
        } else {
            Action action = (Action) map.get(realmGet$onway);
            if (action != null) {
                osObjectBuilder.addObject(lineColumnInfo.onwayColKey, action);
            } else {
                osObjectBuilder.addObject(lineColumnInfo.onwayColKey, com_ciot_realm_db_patrol_ActionRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ActionRealmProxy.ActionColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Action.class), realmGet$onway, true, map, set));
            }
        }
        Action realmGet$action = com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$action();
        if (realmGet$action == null) {
            osObjectBuilder.addNull(lineColumnInfo.actionColKey);
        } else {
            Action action2 = (Action) map.get(realmGet$action);
            if (action2 != null) {
                osObjectBuilder.addObject(lineColumnInfo.actionColKey, action2);
            } else {
                osObjectBuilder.addObject(lineColumnInfo.actionColKey, com_ciot_realm_db_patrol_ActionRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ActionRealmProxy.ActionColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Action.class), realmGet$action, true, map, set));
            }
        }
        TransitionBean realmGet$transition = com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$transition();
        if (realmGet$transition == null) {
            osObjectBuilder.addNull(lineColumnInfo.transitionColKey);
        } else {
            TransitionBean transitionBean = (TransitionBean) map.get(realmGet$transition);
            if (transitionBean != null) {
                osObjectBuilder.addObject(lineColumnInfo.transitionColKey, transitionBean);
            } else {
                osObjectBuilder.addObject(lineColumnInfo.transitionColKey, com_ciot_realm_db_patrol_TransitionBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_TransitionBeanRealmProxy.TransitionBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TransitionBean.class), realmGet$transition, true, map, set));
            }
        }
        WaitBean realmGet$wait = com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$wait();
        if (realmGet$wait == null) {
            osObjectBuilder.addNull(lineColumnInfo.waitColKey);
        } else {
            WaitBean waitBean = (WaitBean) map.get(realmGet$wait);
            if (waitBean != null) {
                osObjectBuilder.addObject(lineColumnInfo.waitColKey, waitBean);
            } else {
                osObjectBuilder.addObject(lineColumnInfo.waitColKey, com_ciot_realm_db_patrol_WaitBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_WaitBeanRealmProxy.WaitBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) WaitBean.class), realmGet$wait, true, map, set));
            }
        }
        osObjectBuilder.addString(lineColumnInfo.descriptionColKey, com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$description());
        osObjectBuilder.addInteger(lineColumnInfo.repeatColKey, Integer.valueOf(com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$repeat()));
        RealmList<Operation> realmGet$oper = com_ciot_realm_db_patrol_linerealmproxyinterface2.realmGet$oper();
        if (realmGet$oper != null) {
            RealmList realmList = new RealmList();
            for (int i = 0; i < realmGet$oper.size(); i++) {
                Operation operation = realmGet$oper.get(i);
                Operation operation2 = (Operation) map.get(operation);
                if (operation2 != null) {
                    realmList.add(operation2);
                } else {
                    realmList.add(com_ciot_realm_db_patrol_OperationRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_OperationRealmProxy.OperationColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Operation.class), operation, true, map, set));
                }
            }
            osObjectBuilder.addObjectList(lineColumnInfo.operColKey, realmList);
        } else {
            osObjectBuilder.addObjectList(lineColumnInfo.operColKey, new RealmList());
        }
        osObjectBuilder.updateExistingObject();
        return line;
    }

    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("Line = proxy[");
        sb.append("{id:");
        String realmGet$id = realmGet$id();
        String str = Configurator.NULL;
        sb.append(realmGet$id != null ? realmGet$id() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{taskId:");
        sb.append(realmGet$taskId() != null ? realmGet$taskId() : str);
        sb.append("}");
        sb.append(",");
        sb.append("{placeInfo:");
        sb.append(realmGet$placeInfo() != null ? com_ciot_realm_db_patrol_PlaceRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME : str);
        sb.append("}");
        sb.append(",");
        sb.append("{onway:");
        Action realmGet$onway = realmGet$onway();
        String str2 = com_ciot_realm_db_patrol_ActionRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME;
        sb.append(realmGet$onway != null ? str2 : str);
        sb.append("}");
        sb.append(",");
        sb.append("{action:");
        if (realmGet$action() == null) {
            str2 = str;
        }
        sb.append(str2);
        sb.append("}");
        sb.append(",");
        sb.append("{transition:");
        sb.append(realmGet$transition() != null ? com_ciot_realm_db_patrol_TransitionBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME : str);
        sb.append("}");
        sb.append(",");
        sb.append("{wait:");
        sb.append(realmGet$wait() != null ? "WaitBean" : str);
        sb.append("}");
        sb.append(",");
        sb.append("{description:");
        if (realmGet$description() != null) {
            str = realmGet$description();
        }
        sb.append(str);
        sb.append("}");
        sb.append(",");
        sb.append("{repeat:");
        sb.append(realmGet$repeat());
        sb.append("}");
        sb.append(",");
        sb.append("{oper:");
        sb.append("RealmList<Operation>[");
        sb.append(realmGet$oper().size());
        sb.append("]");
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
        com_ciot_realm_db_patrol_LineRealmProxy com_ciot_realm_db_patrol_linerealmproxy = (com_ciot_realm_db_patrol_LineRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_patrol_linerealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_patrol_linerealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_patrol_linerealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
