package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.patrol.Line;
import com.ciot.realm.db.patrol.Message;
import com.ciot.realm.db.patrol.PatrolTaskBean;
import com.ciot.realm.db.patrol.Place;
import com.ciot.realm.db.patrol.Plan;
import com.ciot.realm.db.patrol.Resource;
import com.ciot.realm.db.patrol.TransitionBean;
import com.limpoxe.support.servicemanager.ServiceProvider;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_patrol_LineRealmProxy;
import io.realm.com_ciot_realm_db_patrol_MessageRealmProxy;
import io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy;
import io.realm.com_ciot_realm_db_patrol_PlanRealmProxy;
import io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy;
import io.realm.com_ciot_realm_db_patrol_TransitionBeanRealmProxy;
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

public class com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy extends PatrolTaskBean implements RealmObjectProxy, com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private PatrolTaskBeanColumnInfo columnInfo;
    private RealmList<Line> linesRealmList;
    private ProxyState<PatrolTaskBean> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "PatrolTaskBean";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class PatrolTaskBeanColumnInfo extends ColumnInfo {
        long areaColKey;
        long backplaceColKey;
        long briefColKey;
        long countColKey;
        long idColKey;
        long linesColKey;
        long mapColKey;
        long messageColKey;
        long nameColKey;
        long planColKey;
        long prologudColKey;
        long resourceColKey;
        long robotidColKey;

        PatrolTaskBeanColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(13);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.idColKey = addColumnDetails("id", "id", objectSchemaInfo);
            this.robotidColKey = addColumnDetails("robotid", "robotid", objectSchemaInfo);
            this.areaColKey = addColumnDetails("area", "area", objectSchemaInfo);
            this.nameColKey = addColumnDetails(ServiceProvider.NAME, ServiceProvider.NAME, objectSchemaInfo);
            this.briefColKey = addColumnDetails("brief", "brief", objectSchemaInfo);
            this.resourceColKey = addColumnDetails("resource", "resource", objectSchemaInfo);
            this.planColKey = addColumnDetails("plan", "plan", objectSchemaInfo);
            this.countColKey = addColumnDetails("count", "count", objectSchemaInfo);
            this.mapColKey = addColumnDetails(ScheduleFragment.MAP, ScheduleFragment.MAP, objectSchemaInfo);
            this.backplaceColKey = addColumnDetails("backplace", "backplace", objectSchemaInfo);
            this.prologudColKey = addColumnDetails("prologud", "prologud", objectSchemaInfo);
            this.linesColKey = addColumnDetails("lines", "lines", objectSchemaInfo);
            this.messageColKey = addColumnDetails("message", "message", objectSchemaInfo);
        }

        PatrolTaskBeanColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new PatrolTaskBeanColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            PatrolTaskBeanColumnInfo patrolTaskBeanColumnInfo = (PatrolTaskBeanColumnInfo) columnInfo;
            PatrolTaskBeanColumnInfo patrolTaskBeanColumnInfo2 = (PatrolTaskBeanColumnInfo) columnInfo2;
            patrolTaskBeanColumnInfo2.idColKey = patrolTaskBeanColumnInfo.idColKey;
            patrolTaskBeanColumnInfo2.robotidColKey = patrolTaskBeanColumnInfo.robotidColKey;
            patrolTaskBeanColumnInfo2.areaColKey = patrolTaskBeanColumnInfo.areaColKey;
            patrolTaskBeanColumnInfo2.nameColKey = patrolTaskBeanColumnInfo.nameColKey;
            patrolTaskBeanColumnInfo2.briefColKey = patrolTaskBeanColumnInfo.briefColKey;
            patrolTaskBeanColumnInfo2.resourceColKey = patrolTaskBeanColumnInfo.resourceColKey;
            patrolTaskBeanColumnInfo2.planColKey = patrolTaskBeanColumnInfo.planColKey;
            patrolTaskBeanColumnInfo2.countColKey = patrolTaskBeanColumnInfo.countColKey;
            patrolTaskBeanColumnInfo2.mapColKey = patrolTaskBeanColumnInfo.mapColKey;
            patrolTaskBeanColumnInfo2.backplaceColKey = patrolTaskBeanColumnInfo.backplaceColKey;
            patrolTaskBeanColumnInfo2.prologudColKey = patrolTaskBeanColumnInfo.prologudColKey;
            patrolTaskBeanColumnInfo2.linesColKey = patrolTaskBeanColumnInfo.linesColKey;
            patrolTaskBeanColumnInfo2.messageColKey = patrolTaskBeanColumnInfo.messageColKey;
        }
    }

    com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (PatrolTaskBeanColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<PatrolTaskBean> proxyState2 = new ProxyState<>(this);
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

    public String realmGet$robotid() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.robotidColKey);
    }

    public void realmSet$robotid(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.robotidColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.robotidColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.robotidColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.robotidColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public String realmGet$area() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.areaColKey);
    }

    public void realmSet$area(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.areaColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.areaColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.areaColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.areaColKey, row$realm.getObjectKey(), str, true);
            }
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

    public String realmGet$brief() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.briefColKey);
    }

    public void realmSet$brief(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.briefColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.briefColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.briefColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.briefColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public Resource realmGet$resource() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.resourceColKey)) {
            return null;
        }
        return (Resource) this.proxyState.getRealm$realm().get(Resource.class, this.proxyState.getRow$realm().getLink(this.columnInfo.resourceColKey), false, Collections.emptyList());
    }

    public void realmSet$resource(Resource resource) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (resource == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.resourceColKey);
                return;
            }
            this.proxyState.checkValidObject(resource);
            this.proxyState.getRow$realm().setLink(this.columnInfo.resourceColKey, ((RealmObjectProxy) resource).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("resource")) {
            if (resource != null && !RealmObject.isManaged(resource)) {
                resource = (Resource) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(resource, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (resource == null) {
                row$realm.nullifyLink(this.columnInfo.resourceColKey);
                return;
            }
            this.proxyState.checkValidObject(resource);
            row$realm.getTable().setLink(this.columnInfo.resourceColKey, row$realm.getObjectKey(), ((RealmObjectProxy) resource).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public Plan realmGet$plan() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.planColKey)) {
            return null;
        }
        return (Plan) this.proxyState.getRealm$realm().get(Plan.class, this.proxyState.getRow$realm().getLink(this.columnInfo.planColKey), false, Collections.emptyList());
    }

    public void realmSet$plan(Plan plan) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (plan == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.planColKey);
                return;
            }
            this.proxyState.checkValidObject(plan);
            this.proxyState.getRow$realm().setLink(this.columnInfo.planColKey, ((RealmObjectProxy) plan).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("plan")) {
            if (plan != null && !RealmObject.isManaged(plan)) {
                plan = (Plan) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(plan, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (plan == null) {
                row$realm.nullifyLink(this.columnInfo.planColKey);
                return;
            }
            this.proxyState.checkValidObject(plan);
            row$realm.getTable().setLink(this.columnInfo.planColKey, row$realm.getObjectKey(), ((RealmObjectProxy) plan).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public int realmGet$count() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.countColKey);
    }

    public void realmSet$count(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.countColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.countColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public String realmGet$map() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.mapColKey);
    }

    public void realmSet$map(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.mapColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.mapColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.mapColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.mapColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public Place realmGet$backplace() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.backplaceColKey)) {
            return null;
        }
        return (Place) this.proxyState.getRealm$realm().get(Place.class, this.proxyState.getRow$realm().getLink(this.columnInfo.backplaceColKey), false, Collections.emptyList());
    }

    public void realmSet$backplace(Place place) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (place == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.backplaceColKey);
                return;
            }
            this.proxyState.checkValidObject(place);
            this.proxyState.getRow$realm().setLink(this.columnInfo.backplaceColKey, ((RealmObjectProxy) place).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("backplace")) {
            if (place != null && !RealmObject.isManaged(place)) {
                place = (Place) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(place, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (place == null) {
                row$realm.nullifyLink(this.columnInfo.backplaceColKey);
                return;
            }
            this.proxyState.checkValidObject(place);
            row$realm.getTable().setLink(this.columnInfo.backplaceColKey, row$realm.getObjectKey(), ((RealmObjectProxy) place).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public TransitionBean realmGet$prologud() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.prologudColKey)) {
            return null;
        }
        return (TransitionBean) this.proxyState.getRealm$realm().get(TransitionBean.class, this.proxyState.getRow$realm().getLink(this.columnInfo.prologudColKey), false, Collections.emptyList());
    }

    public void realmSet$prologud(TransitionBean transitionBean) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (transitionBean == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.prologudColKey);
                return;
            }
            this.proxyState.checkValidObject(transitionBean);
            this.proxyState.getRow$realm().setLink(this.columnInfo.prologudColKey, ((RealmObjectProxy) transitionBean).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("prologud")) {
            if (transitionBean != null && !RealmObject.isManaged(transitionBean)) {
                transitionBean = (TransitionBean) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(transitionBean, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (transitionBean == null) {
                row$realm.nullifyLink(this.columnInfo.prologudColKey);
                return;
            }
            this.proxyState.checkValidObject(transitionBean);
            row$realm.getTable().setLink(this.columnInfo.prologudColKey, row$realm.getObjectKey(), ((RealmObjectProxy) transitionBean).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public RealmList<Line> realmGet$lines() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<Line> realmList = this.linesRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<Line> realmList2 = new RealmList<>(Line.class, this.proxyState.getRow$realm().getModelList(this.columnInfo.linesColKey), this.proxyState.getRealm$realm());
        this.linesRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$lines(RealmList<Line> realmList) {
        int i = 0;
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("lines")) {
                return;
            }
            if (realmList != null && !realmList.isManaged()) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<Line> realmList2 = new RealmList<>();
                Iterator<Line> it = realmList.iterator();
                while (it.hasNext()) {
                    Line next = it.next();
                    if (next == null || RealmObject.isManaged(next)) {
                        realmList2.add(next);
                    } else {
                        realmList2.add((Line) realm.copyToRealm(next, new ImportFlag[0]));
                    }
                }
                realmList = realmList2;
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList modelList = this.proxyState.getRow$realm().getModelList(this.columnInfo.linesColKey);
        if (realmList == null || ((long) realmList.size()) != modelList.size()) {
            modelList.removeAll();
            if (realmList != null) {
                int size = realmList.size();
                while (i < size) {
                    Line line = realmList.get(i);
                    this.proxyState.checkValidObject(line);
                    modelList.addRow(((RealmObjectProxy) line).realmGet$proxyState().getRow$realm().getObjectKey());
                    i++;
                }
                return;
            }
            return;
        }
        int size2 = realmList.size();
        while (i < size2) {
            Line line2 = realmList.get(i);
            this.proxyState.checkValidObject(line2);
            modelList.setRow((long) i, ((RealmObjectProxy) line2).realmGet$proxyState().getRow$realm().getObjectKey());
            i++;
        }
    }

    public Message realmGet$message() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.messageColKey)) {
            return null;
        }
        return (Message) this.proxyState.getRealm$realm().get(Message.class, this.proxyState.getRow$realm().getLink(this.columnInfo.messageColKey), false, Collections.emptyList());
    }

    public void realmSet$message(Message message) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (message == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.messageColKey);
                return;
            }
            this.proxyState.checkValidObject(message);
            this.proxyState.getRow$realm().setLink(this.columnInfo.messageColKey, ((RealmObjectProxy) message).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("message")) {
            if (message != null && !RealmObject.isManaged(message)) {
                message = (Message) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(message, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (message == null) {
                row$realm.nullifyLink(this.columnInfo.messageColKey);
                return;
            }
            this.proxyState.checkValidObject(message);
            row$realm.getTable().setLink(this.columnInfo.messageColKey, row$realm.getObjectKey(), ((RealmObjectProxy) message).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 13, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("id", RealmFieldType.STRING, true, false, false);
        builder2.addPersistedProperty("robotid", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("area", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty(ServiceProvider.NAME, RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("brief", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("resource", RealmFieldType.OBJECT, com_ciot_realm_db_patrol_ResourceRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedLinkProperty("plan", RealmFieldType.OBJECT, com_ciot_realm_db_patrol_PlanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder3 = builder;
        builder3.addPersistedProperty("count", RealmFieldType.INTEGER, false, false, true);
        builder3.addPersistedProperty(ScheduleFragment.MAP, RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("backplace", RealmFieldType.OBJECT, com_ciot_realm_db_patrol_PlaceRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedLinkProperty("prologud", RealmFieldType.OBJECT, com_ciot_realm_db_patrol_TransitionBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedLinkProperty("lines", RealmFieldType.LIST, com_ciot_realm_db_patrol_LineRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedLinkProperty("message", RealmFieldType.OBJECT, com_ciot_realm_db_patrol_MessageRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static PatrolTaskBeanColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new PatrolTaskBeanColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r0v4, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x01cd  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x01e8  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x021c  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x012e  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0145  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0160  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x017d  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x019b  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.PatrolTaskBean createOrUpdateUsingJsonObject(io.realm.Realm r13, org.json.JSONObject r14, boolean r15) throws org.json.JSONException {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 6
            r0.<init>(r1)
            java.lang.String r1 = "id"
            r2 = 0
            if (r15 == 0) goto L_0x0066
            java.lang.Class<com.ciot.realm.db.patrol.PatrolTaskBean> r3 = com.ciot.realm.db.patrol.PatrolTaskBean.class
            io.realm.internal.Table r3 = r13.getTable(r3)
            io.realm.RealmSchema r4 = r13.getSchema()
            java.lang.Class<com.ciot.realm.db.patrol.PatrolTaskBean> r5 = com.ciot.realm.db.patrol.PatrolTaskBean.class
            io.realm.internal.ColumnInfo r4 = r4.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r5)
            io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy$PatrolTaskBeanColumnInfo r4 = (io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.PatrolTaskBeanColumnInfo) r4
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
            java.lang.Class<com.ciot.realm.db.patrol.PatrolTaskBean> r4 = com.ciot.realm.db.patrol.PatrolTaskBean.class
            io.realm.internal.ColumnInfo r10 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)     // Catch:{ all -> 0x0061 }
            r11 = 0
            java.util.List r12 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0061 }
            r7 = r6
            r8 = r13
            r7.set(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0061 }
            io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy r3 = new io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy     // Catch:{ all -> 0x0061 }
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
            java.lang.String r4 = "message"
            java.lang.String r5 = "lines"
            java.lang.String r6 = "prologud"
            java.lang.String r7 = "backplace"
            java.lang.String r8 = "plan"
            java.lang.String r9 = "resource"
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
            java.lang.Class<com.ciot.realm.db.patrol.PatrolTaskBean> r1 = com.ciot.realm.db.patrol.PatrolTaskBean.class
            io.realm.RealmModel r0 = r13.createObjectInternal(r1, r2, r10, r0)
            r3 = r0
            io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy r3 = (io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy) r3
            goto L_0x00d8
        L_0x00c2:
            java.lang.Class<com.ciot.realm.db.patrol.PatrolTaskBean> r3 = com.ciot.realm.db.patrol.PatrolTaskBean.class
            java.lang.String r1 = r14.getString(r1)
            io.realm.RealmModel r0 = r13.createObjectInternal(r3, r1, r10, r0)
            r3 = r0
            io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy r3 = (io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy) r3
            goto L_0x00d8
        L_0x00d0:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "JSON object doesn't have the primary key field 'id'."
            r13.<init>(r14)
            throw r13
        L_0x00d8:
            r0 = r3
            io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface r0 = (io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface) r0
            java.lang.String r1 = "robotid"
            boolean r10 = r14.has(r1)
            if (r10 == 0) goto L_0x00f4
            boolean r10 = r14.isNull(r1)
            if (r10 == 0) goto L_0x00ed
            r0.realmSet$robotid(r2)
            goto L_0x00f4
        L_0x00ed:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$robotid(r1)
        L_0x00f4:
            java.lang.String r1 = "area"
            boolean r10 = r14.has(r1)
            if (r10 == 0) goto L_0x010d
            boolean r10 = r14.isNull(r1)
            if (r10 == 0) goto L_0x0106
            r0.realmSet$area(r2)
            goto L_0x010d
        L_0x0106:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$area(r1)
        L_0x010d:
            java.lang.String r1 = "name"
            boolean r10 = r14.has(r1)
            if (r10 == 0) goto L_0x0126
            boolean r10 = r14.isNull(r1)
            if (r10 == 0) goto L_0x011f
            r0.realmSet$name(r2)
            goto L_0x0126
        L_0x011f:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$name(r1)
        L_0x0126:
            java.lang.String r1 = "brief"
            boolean r10 = r14.has(r1)
            if (r10 == 0) goto L_0x013f
            boolean r10 = r14.isNull(r1)
            if (r10 == 0) goto L_0x0138
            r0.realmSet$brief(r2)
            goto L_0x013f
        L_0x0138:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$brief(r1)
        L_0x013f:
            boolean r1 = r14.has(r9)
            if (r1 == 0) goto L_0x015a
            boolean r1 = r14.isNull(r9)
            if (r1 == 0) goto L_0x014f
            r0.realmSet$resource(r2)
            goto L_0x015a
        L_0x014f:
            org.json.JSONObject r1 = r14.getJSONObject(r9)
            com.ciot.realm.db.patrol.Resource r1 = io.realm.com_ciot_realm_db_patrol_ResourceRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$resource(r1)
        L_0x015a:
            boolean r1 = r14.has(r8)
            if (r1 == 0) goto L_0x0175
            boolean r1 = r14.isNull(r8)
            if (r1 == 0) goto L_0x016a
            r0.realmSet$plan(r2)
            goto L_0x0175
        L_0x016a:
            org.json.JSONObject r1 = r14.getJSONObject(r8)
            com.ciot.realm.db.patrol.Plan r1 = io.realm.com_ciot_realm_db_patrol_PlanRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$plan(r1)
        L_0x0175:
            java.lang.String r1 = "count"
            boolean r8 = r14.has(r1)
            if (r8 == 0) goto L_0x0193
            boolean r8 = r14.isNull(r1)
            if (r8 != 0) goto L_0x018b
            int r1 = r14.getInt(r1)
            r0.realmSet$count(r1)
            goto L_0x0193
        L_0x018b:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'count' to null."
            r13.<init>(r14)
            throw r13
        L_0x0193:
            java.lang.String r1 = "map"
            boolean r8 = r14.has(r1)
            if (r8 == 0) goto L_0x01ac
            boolean r8 = r14.isNull(r1)
            if (r8 == 0) goto L_0x01a5
            r0.realmSet$map(r2)
            goto L_0x01ac
        L_0x01a5:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$map(r1)
        L_0x01ac:
            boolean r1 = r14.has(r7)
            if (r1 == 0) goto L_0x01c7
            boolean r1 = r14.isNull(r7)
            if (r1 == 0) goto L_0x01bc
            r0.realmSet$backplace(r2)
            goto L_0x01c7
        L_0x01bc:
            org.json.JSONObject r1 = r14.getJSONObject(r7)
            com.ciot.realm.db.patrol.Place r1 = io.realm.com_ciot_realm_db_patrol_PlaceRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$backplace(r1)
        L_0x01c7:
            boolean r1 = r14.has(r6)
            if (r1 == 0) goto L_0x01e2
            boolean r1 = r14.isNull(r6)
            if (r1 == 0) goto L_0x01d7
            r0.realmSet$prologud(r2)
            goto L_0x01e2
        L_0x01d7:
            org.json.JSONObject r1 = r14.getJSONObject(r6)
            com.ciot.realm.db.patrol.TransitionBean r1 = io.realm.com_ciot_realm_db_patrol_TransitionBeanRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$prologud(r1)
        L_0x01e2:
            boolean r1 = r14.has(r5)
            if (r1 == 0) goto L_0x0216
            boolean r1 = r14.isNull(r5)
            if (r1 == 0) goto L_0x01f2
            r0.realmSet$lines(r2)
            goto L_0x0216
        L_0x01f2:
            io.realm.RealmList r1 = r0.realmGet$lines()
            r1.clear()
            org.json.JSONArray r1 = r14.getJSONArray(r5)
            r5 = 0
        L_0x01fe:
            int r6 = r1.length()
            if (r5 >= r6) goto L_0x0216
            org.json.JSONObject r6 = r1.getJSONObject(r5)
            com.ciot.realm.db.patrol.Line r6 = io.realm.com_ciot_realm_db_patrol_LineRealmProxy.createOrUpdateUsingJsonObject(r13, r6, r15)
            io.realm.RealmList r7 = r0.realmGet$lines()
            r7.add(r6)
            int r5 = r5 + 1
            goto L_0x01fe
        L_0x0216:
            boolean r1 = r14.has(r4)
            if (r1 == 0) goto L_0x0231
            boolean r1 = r14.isNull(r4)
            if (r1 == 0) goto L_0x0226
            r0.realmSet$message(r2)
            goto L_0x0231
        L_0x0226:
            org.json.JSONObject r14 = r14.getJSONObject(r4)
            com.ciot.realm.db.patrol.Message r13 = io.realm.com_ciot_realm_db_patrol_MessageRealmProxy.createOrUpdateUsingJsonObject(r13, r14, r15)
            r0.realmSet$message(r13)
        L_0x0231:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.patrol.PatrolTaskBean");
    }

    public static PatrolTaskBean createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        PatrolTaskBean patrolTaskBean = new PatrolTaskBean();
        com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface = patrolTaskBean;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("id")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$id(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$id((String) null);
                }
                z = true;
            } else if (nextName.equals("robotid")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$robotid(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$robotid((String) null);
                }
            } else if (nextName.equals("area")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$area(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$area((String) null);
                }
            } else if (nextName.equals(ServiceProvider.NAME)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$name(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$name((String) null);
                }
            } else if (nextName.equals("brief")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$brief(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$brief((String) null);
                }
            } else if (nextName.equals("resource")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$resource((Resource) null);
                } else {
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$resource(com_ciot_realm_db_patrol_ResourceRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("plan")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$plan((Plan) null);
                } else {
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$plan(com_ciot_realm_db_patrol_PlanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("count")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$count(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'count' to null.");
                }
            } else if (nextName.equals(ScheduleFragment.MAP)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$map(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$map((String) null);
                }
            } else if (nextName.equals("backplace")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$backplace((Place) null);
                } else {
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$backplace(com_ciot_realm_db_patrol_PlaceRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("prologud")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$prologud((TransitionBean) null);
                } else {
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$prologud(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals("lines")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$lines((RealmList<Line>) null);
                } else {
                    com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$lines(new RealmList());
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$lines().add(com_ciot_realm_db_patrol_LineRealmProxy.createUsingJsonStream(realm, jsonReader));
                    }
                    jsonReader.endArray();
                }
            } else if (!nextName.equals("message")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.skipValue();
                com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$message((Message) null);
            } else {
                com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$message(com_ciot_realm_db_patrol_MessageRealmProxy.createUsingJsonStream(realm, jsonReader));
            }
        }
        jsonReader.endObject();
        if (z) {
            return (PatrolTaskBean) realm.copyToRealm(patrolTaskBean, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
    }

    private static com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) PatrolTaskBean.class), false, Collections.emptyList());
        com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy com_ciot_realm_db_patrol_patroltaskbeanrealmproxy = new com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_patrol_patroltaskbeanrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.patrol.PatrolTaskBean copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.PatrolTaskBeanColumnInfo r9, com.ciot.realm.db.patrol.PatrolTaskBean r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.patrol.PatrolTaskBean r1 = (com.ciot.realm.db.patrol.PatrolTaskBean) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.patrol.PatrolTaskBean> r2 = com.ciot.realm.db.patrol.PatrolTaskBean.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.idColKey
            r5 = r10
            io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface) r5
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
            io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy r1 = new io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.patrol.PatrolTaskBean r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.patrol.PatrolTaskBean r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy$PatrolTaskBeanColumnInfo, com.ciot.realm.db.patrol.PatrolTaskBean, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.patrol.PatrolTaskBean");
    }

    public static PatrolTaskBean copy(Realm realm, PatrolTaskBeanColumnInfo patrolTaskBeanColumnInfo, PatrolTaskBean patrolTaskBean, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        Realm realm2 = realm;
        PatrolTaskBeanColumnInfo patrolTaskBeanColumnInfo2 = patrolTaskBeanColumnInfo;
        PatrolTaskBean patrolTaskBean2 = patrolTaskBean;
        Map<RealmModel, RealmObjectProxy> map2 = map;
        RealmObjectProxy realmObjectProxy = map2.get(patrolTaskBean2);
        if (realmObjectProxy != null) {
            return (PatrolTaskBean) realmObjectProxy;
        }
        com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface = patrolTaskBean2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(PatrolTaskBean.class), set);
        osObjectBuilder.addString(patrolTaskBeanColumnInfo2.idColKey, com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$id());
        osObjectBuilder.addString(patrolTaskBeanColumnInfo2.robotidColKey, com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$robotid());
        osObjectBuilder.addString(patrolTaskBeanColumnInfo2.areaColKey, com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$area());
        osObjectBuilder.addString(patrolTaskBeanColumnInfo2.nameColKey, com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$name());
        osObjectBuilder.addString(patrolTaskBeanColumnInfo2.briefColKey, com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$brief());
        osObjectBuilder.addInteger(patrolTaskBeanColumnInfo2.countColKey, Integer.valueOf(com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$count()));
        osObjectBuilder.addString(patrolTaskBeanColumnInfo2.mapColKey, com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$map());
        com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map2.put(patrolTaskBean2, newProxyInstance);
        Resource realmGet$resource = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$resource();
        if (realmGet$resource == null) {
            newProxyInstance.realmSet$resource((Resource) null);
        } else {
            Resource resource = (Resource) map2.get(realmGet$resource);
            if (resource != null) {
                newProxyInstance.realmSet$resource(resource);
            } else {
                newProxyInstance.realmSet$resource(com_ciot_realm_db_patrol_ResourceRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ResourceRealmProxy.ResourceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Resource.class), realmGet$resource, z, map, set));
            }
        }
        Plan realmGet$plan = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$plan();
        if (realmGet$plan == null) {
            newProxyInstance.realmSet$plan((Plan) null);
        } else {
            Plan plan = (Plan) map2.get(realmGet$plan);
            if (plan != null) {
                newProxyInstance.realmSet$plan(plan);
            } else {
                newProxyInstance.realmSet$plan(com_ciot_realm_db_patrol_PlanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_PlanRealmProxy.PlanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Plan.class), realmGet$plan, z, map, set));
            }
        }
        Place realmGet$backplace = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$backplace();
        if (realmGet$backplace == null) {
            newProxyInstance.realmSet$backplace((Place) null);
        } else {
            Place place = (Place) map2.get(realmGet$backplace);
            if (place != null) {
                newProxyInstance.realmSet$backplace(place);
            } else {
                newProxyInstance.realmSet$backplace(com_ciot_realm_db_patrol_PlaceRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_PlaceRealmProxy.PlaceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Place.class), realmGet$backplace, z, map, set));
            }
        }
        TransitionBean realmGet$prologud = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$prologud();
        if (realmGet$prologud == null) {
            newProxyInstance.realmSet$prologud((TransitionBean) null);
        } else {
            TransitionBean transitionBean = (TransitionBean) map2.get(realmGet$prologud);
            if (transitionBean != null) {
                newProxyInstance.realmSet$prologud(transitionBean);
            } else {
                newProxyInstance.realmSet$prologud(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_TransitionBeanRealmProxy.TransitionBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TransitionBean.class), realmGet$prologud, z, map, set));
            }
        }
        RealmList<Line> realmGet$lines = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$lines();
        if (realmGet$lines != null) {
            RealmList<Line> realmGet$lines2 = newProxyInstance.realmGet$lines();
            realmGet$lines2.clear();
            for (int i = 0; i < realmGet$lines.size(); i++) {
                Line line = realmGet$lines.get(i);
                Line line2 = (Line) map2.get(line);
                if (line2 != null) {
                    realmGet$lines2.add(line2);
                } else {
                    realmGet$lines2.add(com_ciot_realm_db_patrol_LineRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_LineRealmProxy.LineColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Line.class), line, z, map, set));
                }
            }
        }
        Message realmGet$message = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$message();
        if (realmGet$message == null) {
            newProxyInstance.realmSet$message((Message) null);
        } else {
            Message message = (Message) map2.get(realmGet$message);
            if (message != null) {
                newProxyInstance.realmSet$message(message);
            } else {
                newProxyInstance.realmSet$message(com_ciot_realm_db_patrol_MessageRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_MessageRealmProxy.MessageColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Message.class), realmGet$message, z, map, set));
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, PatrolTaskBean patrolTaskBean, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Realm realm2 = realm;
        PatrolTaskBean patrolTaskBean2 = patrolTaskBean;
        Map<RealmModel, Long> map2 = map;
        if ((patrolTaskBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(patrolTaskBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) patrolTaskBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(PatrolTaskBean.class);
        long nativePtr = table.getNativePtr();
        PatrolTaskBeanColumnInfo patrolTaskBeanColumnInfo = (PatrolTaskBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PatrolTaskBean.class);
        long j4 = patrolTaskBeanColumnInfo.idColKey;
        com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface = patrolTaskBean2;
        String realmGet$id = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$id();
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
        map2.put(patrolTaskBean2, Long.valueOf(j5));
        String realmGet$robotid = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$robotid();
        if (realmGet$robotid != null) {
            j2 = j5;
            Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.robotidColKey, j5, realmGet$robotid, false);
        } else {
            j2 = j5;
        }
        String realmGet$area = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$area();
        if (realmGet$area != null) {
            Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.areaColKey, j2, realmGet$area, false);
        }
        String realmGet$name = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.nameColKey, j2, realmGet$name, false);
        }
        String realmGet$brief = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$brief();
        if (realmGet$brief != null) {
            Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.briefColKey, j2, realmGet$brief, false);
        }
        Resource realmGet$resource = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$resource();
        if (realmGet$resource != null) {
            Long l = map2.get(realmGet$resource);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insert(realm2, realmGet$resource, map2));
            }
            Table.nativeSetLink(nativePtr, patrolTaskBeanColumnInfo.resourceColKey, j2, l.longValue(), false);
        }
        Plan realmGet$plan = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$plan();
        if (realmGet$plan != null) {
            Long l2 = map2.get(realmGet$plan);
            if (l2 == null) {
                l2 = Long.valueOf(com_ciot_realm_db_patrol_PlanRealmProxy.insert(realm2, realmGet$plan, map2));
            }
            Table.nativeSetLink(nativePtr, patrolTaskBeanColumnInfo.planColKey, j2, l2.longValue(), false);
        }
        Table.nativeSetLong(nativePtr, patrolTaskBeanColumnInfo.countColKey, j2, (long) com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$count(), false);
        String realmGet$map = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$map();
        if (realmGet$map != null) {
            Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.mapColKey, j2, realmGet$map, false);
        }
        Place realmGet$backplace = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$backplace();
        if (realmGet$backplace != null) {
            Long l3 = map2.get(realmGet$backplace);
            if (l3 == null) {
                l3 = Long.valueOf(com_ciot_realm_db_patrol_PlaceRealmProxy.insert(realm2, realmGet$backplace, map2));
            }
            Table.nativeSetLink(nativePtr, patrolTaskBeanColumnInfo.backplaceColKey, j2, l3.longValue(), false);
        }
        TransitionBean realmGet$prologud = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$prologud();
        if (realmGet$prologud != null) {
            Long l4 = map2.get(realmGet$prologud);
            if (l4 == null) {
                l4 = Long.valueOf(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.insert(realm2, realmGet$prologud, map2));
            }
            Table.nativeSetLink(nativePtr, patrolTaskBeanColumnInfo.prologudColKey, j2, l4.longValue(), false);
        }
        RealmList<Line> realmGet$lines = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$lines();
        if (realmGet$lines != null) {
            j3 = j2;
            OsList osList = new OsList(table.getUncheckedRow(j3), patrolTaskBeanColumnInfo.linesColKey);
            Iterator<Line> it = realmGet$lines.iterator();
            while (it.hasNext()) {
                Line next = it.next();
                Long l5 = map2.get(next);
                if (l5 == null) {
                    l5 = Long.valueOf(com_ciot_realm_db_patrol_LineRealmProxy.insert(realm2, next, map2));
                }
                osList.addRow(l5.longValue());
            }
        } else {
            j3 = j2;
        }
        Message realmGet$message = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$message();
        if (realmGet$message == null) {
            return j3;
        }
        Long l6 = map2.get(realmGet$message);
        if (l6 == null) {
            l6 = Long.valueOf(com_ciot_realm_db_patrol_MessageRealmProxy.insert(realm2, realmGet$message, map2));
        }
        long j6 = j3;
        Table.nativeSetLink(nativePtr, patrolTaskBeanColumnInfo.messageColKey, j3, l6.longValue(), false);
        return j6;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(PatrolTaskBean.class);
        long nativePtr = table.getNativePtr();
        PatrolTaskBeanColumnInfo patrolTaskBeanColumnInfo = (PatrolTaskBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PatrolTaskBean.class);
        long j6 = patrolTaskBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            PatrolTaskBean patrolTaskBean = (PatrolTaskBean) it.next();
            if (!map2.containsKey(patrolTaskBean)) {
                if ((patrolTaskBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(patrolTaskBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) patrolTaskBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(patrolTaskBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface = patrolTaskBean;
                String realmGet$id = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j6);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j6, realmGet$id);
                }
                if (j == -1) {
                    j2 = OsObject.createRowWithPrimaryKey(table, j6, realmGet$id);
                } else {
                    Table.throwDuplicatePrimaryKeyException(realmGet$id);
                    j2 = j;
                }
                map2.put(patrolTaskBean, Long.valueOf(j2));
                String realmGet$robotid = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$robotid();
                if (realmGet$robotid != null) {
                    j4 = j2;
                    j3 = j6;
                    Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.robotidColKey, j2, realmGet$robotid, false);
                } else {
                    j4 = j2;
                    j3 = j6;
                }
                String realmGet$area = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$area();
                if (realmGet$area != null) {
                    Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.areaColKey, j4, realmGet$area, false);
                }
                String realmGet$name = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.nameColKey, j4, realmGet$name, false);
                }
                String realmGet$brief = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$brief();
                if (realmGet$brief != null) {
                    Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.briefColKey, j4, realmGet$brief, false);
                }
                Resource realmGet$resource = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$resource();
                if (realmGet$resource != null) {
                    Long l = map2.get(realmGet$resource);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insert(realm2, realmGet$resource, map2));
                    }
                    table.setLink(patrolTaskBeanColumnInfo.resourceColKey, j4, l.longValue(), false);
                }
                Plan realmGet$plan = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$plan();
                if (realmGet$plan != null) {
                    Long l2 = map2.get(realmGet$plan);
                    if (l2 == null) {
                        l2 = Long.valueOf(com_ciot_realm_db_patrol_PlanRealmProxy.insert(realm2, realmGet$plan, map2));
                    }
                    table.setLink(patrolTaskBeanColumnInfo.planColKey, j4, l2.longValue(), false);
                }
                Table.nativeSetLong(nativePtr, patrolTaskBeanColumnInfo.countColKey, j4, (long) com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$count(), false);
                String realmGet$map = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$map();
                if (realmGet$map != null) {
                    Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.mapColKey, j4, realmGet$map, false);
                }
                Place realmGet$backplace = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$backplace();
                if (realmGet$backplace != null) {
                    Long l3 = map2.get(realmGet$backplace);
                    if (l3 == null) {
                        l3 = Long.valueOf(com_ciot_realm_db_patrol_PlaceRealmProxy.insert(realm2, realmGet$backplace, map2));
                    }
                    table.setLink(patrolTaskBeanColumnInfo.backplaceColKey, j4, l3.longValue(), false);
                }
                TransitionBean realmGet$prologud = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$prologud();
                if (realmGet$prologud != null) {
                    Long l4 = map2.get(realmGet$prologud);
                    if (l4 == null) {
                        l4 = Long.valueOf(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.insert(realm2, realmGet$prologud, map2));
                    }
                    table.setLink(patrolTaskBeanColumnInfo.prologudColKey, j4, l4.longValue(), false);
                }
                RealmList<Line> realmGet$lines = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$lines();
                if (realmGet$lines != null) {
                    j5 = j4;
                    OsList osList = new OsList(table.getUncheckedRow(j5), patrolTaskBeanColumnInfo.linesColKey);
                    Iterator<Line> it2 = realmGet$lines.iterator();
                    while (it2.hasNext()) {
                        Line next = it2.next();
                        Long l5 = map2.get(next);
                        if (l5 == null) {
                            l5 = Long.valueOf(com_ciot_realm_db_patrol_LineRealmProxy.insert(realm2, next, map2));
                        }
                        osList.addRow(l5.longValue());
                    }
                } else {
                    j5 = j4;
                }
                Message realmGet$message = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$message();
                if (realmGet$message != null) {
                    Long l6 = map2.get(realmGet$message);
                    if (l6 == null) {
                        l6 = Long.valueOf(com_ciot_realm_db_patrol_MessageRealmProxy.insert(realm2, realmGet$message, map2));
                    }
                    table.setLink(patrolTaskBeanColumnInfo.messageColKey, j5, l6.longValue(), false);
                }
                j6 = j3;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, PatrolTaskBean patrolTaskBean, Map<RealmModel, Long> map) {
        long j;
        long j2;
        Realm realm2 = realm;
        PatrolTaskBean patrolTaskBean2 = patrolTaskBean;
        Map<RealmModel, Long> map2 = map;
        if ((patrolTaskBean2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(patrolTaskBean)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) patrolTaskBean2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(PatrolTaskBean.class);
        long nativePtr = table.getNativePtr();
        PatrolTaskBeanColumnInfo patrolTaskBeanColumnInfo = (PatrolTaskBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PatrolTaskBean.class);
        long j3 = patrolTaskBeanColumnInfo.idColKey;
        com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface = patrolTaskBean2;
        String realmGet$id = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$id();
        if (realmGet$id == null) {
            j = Table.nativeFindFirstNull(nativePtr, j3);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j3, realmGet$id);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j3, realmGet$id);
        }
        long j4 = j;
        map2.put(patrolTaskBean2, Long.valueOf(j4));
        String realmGet$robotid = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$robotid();
        if (realmGet$robotid != null) {
            j2 = j4;
            Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.robotidColKey, j4, realmGet$robotid, false);
        } else {
            j2 = j4;
            Table.nativeSetNull(nativePtr, patrolTaskBeanColumnInfo.robotidColKey, j2, false);
        }
        String realmGet$area = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$area();
        if (realmGet$area != null) {
            Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.areaColKey, j2, realmGet$area, false);
        } else {
            Table.nativeSetNull(nativePtr, patrolTaskBeanColumnInfo.areaColKey, j2, false);
        }
        String realmGet$name = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.nameColKey, j2, realmGet$name, false);
        } else {
            Table.nativeSetNull(nativePtr, patrolTaskBeanColumnInfo.nameColKey, j2, false);
        }
        String realmGet$brief = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$brief();
        if (realmGet$brief != null) {
            Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.briefColKey, j2, realmGet$brief, false);
        } else {
            Table.nativeSetNull(nativePtr, patrolTaskBeanColumnInfo.briefColKey, j2, false);
        }
        Resource realmGet$resource = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$resource();
        if (realmGet$resource != null) {
            Long l = map2.get(realmGet$resource);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, realmGet$resource, map2));
            }
            Table.nativeSetLink(nativePtr, patrolTaskBeanColumnInfo.resourceColKey, j2, l.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, patrolTaskBeanColumnInfo.resourceColKey, j2);
        }
        Plan realmGet$plan = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$plan();
        if (realmGet$plan != null) {
            Long l2 = map2.get(realmGet$plan);
            if (l2 == null) {
                l2 = Long.valueOf(com_ciot_realm_db_patrol_PlanRealmProxy.insertOrUpdate(realm2, realmGet$plan, map2));
            }
            Table.nativeSetLink(nativePtr, patrolTaskBeanColumnInfo.planColKey, j2, l2.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, patrolTaskBeanColumnInfo.planColKey, j2);
        }
        Table.nativeSetLong(nativePtr, patrolTaskBeanColumnInfo.countColKey, j2, (long) com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$count(), false);
        String realmGet$map = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$map();
        if (realmGet$map != null) {
            Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.mapColKey, j2, realmGet$map, false);
        } else {
            Table.nativeSetNull(nativePtr, patrolTaskBeanColumnInfo.mapColKey, j2, false);
        }
        Place realmGet$backplace = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$backplace();
        if (realmGet$backplace != null) {
            Long l3 = map2.get(realmGet$backplace);
            if (l3 == null) {
                l3 = Long.valueOf(com_ciot_realm_db_patrol_PlaceRealmProxy.insertOrUpdate(realm2, realmGet$backplace, map2));
            }
            Table.nativeSetLink(nativePtr, patrolTaskBeanColumnInfo.backplaceColKey, j2, l3.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, patrolTaskBeanColumnInfo.backplaceColKey, j2);
        }
        TransitionBean realmGet$prologud = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$prologud();
        if (realmGet$prologud != null) {
            Long l4 = map2.get(realmGet$prologud);
            if (l4 == null) {
                l4 = Long.valueOf(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.insertOrUpdate(realm2, realmGet$prologud, map2));
            }
            Table.nativeSetLink(nativePtr, patrolTaskBeanColumnInfo.prologudColKey, j2, l4.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, patrolTaskBeanColumnInfo.prologudColKey, j2);
        }
        long j5 = j2;
        OsList osList = new OsList(table.getUncheckedRow(j5), patrolTaskBeanColumnInfo.linesColKey);
        RealmList<Line> realmGet$lines = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$lines();
        if (realmGet$lines == null || ((long) realmGet$lines.size()) != osList.size()) {
            osList.removeAll();
            if (realmGet$lines != null) {
                Iterator<Line> it = realmGet$lines.iterator();
                while (it.hasNext()) {
                    Line next = it.next();
                    Long l5 = map2.get(next);
                    if (l5 == null) {
                        l5 = Long.valueOf(com_ciot_realm_db_patrol_LineRealmProxy.insertOrUpdate(realm2, next, map2));
                    }
                    osList.addRow(l5.longValue());
                }
            }
        } else {
            int size = realmGet$lines.size();
            for (int i = 0; i < size; i++) {
                Line line = realmGet$lines.get(i);
                Long l6 = map2.get(line);
                if (l6 == null) {
                    l6 = Long.valueOf(com_ciot_realm_db_patrol_LineRealmProxy.insertOrUpdate(realm2, line, map2));
                }
                osList.setRow((long) i, l6.longValue());
            }
        }
        Message realmGet$message = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$message();
        if (realmGet$message != null) {
            Long l7 = map2.get(realmGet$message);
            if (l7 == null) {
                l7 = Long.valueOf(com_ciot_realm_db_patrol_MessageRealmProxy.insertOrUpdate(realm2, realmGet$message, map2));
            }
            long j6 = j5;
            Table.nativeSetLink(nativePtr, patrolTaskBeanColumnInfo.messageColKey, j5, l7.longValue(), false);
            return j6;
        }
        long j7 = j5;
        Table.nativeNullifyLink(nativePtr, patrolTaskBeanColumnInfo.messageColKey, j7);
        return j7;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        long j4;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(PatrolTaskBean.class);
        long nativePtr = table.getNativePtr();
        PatrolTaskBeanColumnInfo patrolTaskBeanColumnInfo = (PatrolTaskBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PatrolTaskBean.class);
        long j5 = patrolTaskBeanColumnInfo.idColKey;
        while (it.hasNext()) {
            PatrolTaskBean patrolTaskBean = (PatrolTaskBean) it.next();
            if (!map2.containsKey(patrolTaskBean)) {
                if ((patrolTaskBean instanceof RealmObjectProxy) && !RealmObject.isFrozen(patrolTaskBean)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) patrolTaskBean;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(patrolTaskBean, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface = patrolTaskBean;
                String realmGet$id = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$id();
                if (realmGet$id == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j5);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j5, realmGet$id);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j5, realmGet$id) : j;
                map2.put(patrolTaskBean, Long.valueOf(createRowWithPrimaryKey));
                String realmGet$robotid = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$robotid();
                if (realmGet$robotid != null) {
                    j3 = createRowWithPrimaryKey;
                    j2 = j5;
                    Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.robotidColKey, createRowWithPrimaryKey, realmGet$robotid, false);
                } else {
                    j3 = createRowWithPrimaryKey;
                    j2 = j5;
                    Table.nativeSetNull(nativePtr, patrolTaskBeanColumnInfo.robotidColKey, createRowWithPrimaryKey, false);
                }
                String realmGet$area = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$area();
                if (realmGet$area != null) {
                    Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.areaColKey, j3, realmGet$area, false);
                } else {
                    Table.nativeSetNull(nativePtr, patrolTaskBeanColumnInfo.areaColKey, j3, false);
                }
                String realmGet$name = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.nameColKey, j3, realmGet$name, false);
                } else {
                    Table.nativeSetNull(nativePtr, patrolTaskBeanColumnInfo.nameColKey, j3, false);
                }
                String realmGet$brief = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$brief();
                if (realmGet$brief != null) {
                    Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.briefColKey, j3, realmGet$brief, false);
                } else {
                    Table.nativeSetNull(nativePtr, patrolTaskBeanColumnInfo.briefColKey, j3, false);
                }
                Resource realmGet$resource = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$resource();
                if (realmGet$resource != null) {
                    Long l = map2.get(realmGet$resource);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_patrol_ResourceRealmProxy.insertOrUpdate(realm2, realmGet$resource, map2));
                    }
                    Table.nativeSetLink(nativePtr, patrolTaskBeanColumnInfo.resourceColKey, j3, l.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, patrolTaskBeanColumnInfo.resourceColKey, j3);
                }
                Plan realmGet$plan = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$plan();
                if (realmGet$plan != null) {
                    Long l2 = map2.get(realmGet$plan);
                    if (l2 == null) {
                        l2 = Long.valueOf(com_ciot_realm_db_patrol_PlanRealmProxy.insertOrUpdate(realm2, realmGet$plan, map2));
                    }
                    Table.nativeSetLink(nativePtr, patrolTaskBeanColumnInfo.planColKey, j3, l2.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, patrolTaskBeanColumnInfo.planColKey, j3);
                }
                Table.nativeSetLong(nativePtr, patrolTaskBeanColumnInfo.countColKey, j3, (long) com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$count(), false);
                String realmGet$map = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$map();
                if (realmGet$map != null) {
                    Table.nativeSetString(nativePtr, patrolTaskBeanColumnInfo.mapColKey, j3, realmGet$map, false);
                } else {
                    Table.nativeSetNull(nativePtr, patrolTaskBeanColumnInfo.mapColKey, j3, false);
                }
                Place realmGet$backplace = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$backplace();
                if (realmGet$backplace != null) {
                    Long l3 = map2.get(realmGet$backplace);
                    if (l3 == null) {
                        l3 = Long.valueOf(com_ciot_realm_db_patrol_PlaceRealmProxy.insertOrUpdate(realm2, realmGet$backplace, map2));
                    }
                    Table.nativeSetLink(nativePtr, patrolTaskBeanColumnInfo.backplaceColKey, j3, l3.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, patrolTaskBeanColumnInfo.backplaceColKey, j3);
                }
                TransitionBean realmGet$prologud = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$prologud();
                if (realmGet$prologud != null) {
                    Long l4 = map2.get(realmGet$prologud);
                    if (l4 == null) {
                        l4 = Long.valueOf(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.insertOrUpdate(realm2, realmGet$prologud, map2));
                    }
                    Table.nativeSetLink(nativePtr, patrolTaskBeanColumnInfo.prologudColKey, j3, l4.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, patrolTaskBeanColumnInfo.prologudColKey, j3);
                }
                long j6 = j3;
                OsList osList = new OsList(table.getUncheckedRow(j6), patrolTaskBeanColumnInfo.linesColKey);
                RealmList<Line> realmGet$lines = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$lines();
                if (realmGet$lines == null || ((long) realmGet$lines.size()) != osList.size()) {
                    j4 = j6;
                    osList.removeAll();
                    if (realmGet$lines != null) {
                        Iterator<Line> it2 = realmGet$lines.iterator();
                        while (it2.hasNext()) {
                            Line next = it2.next();
                            Long l5 = map2.get(next);
                            if (l5 == null) {
                                l5 = Long.valueOf(com_ciot_realm_db_patrol_LineRealmProxy.insertOrUpdate(realm2, next, map2));
                            }
                            osList.addRow(l5.longValue());
                        }
                    }
                } else {
                    int size = realmGet$lines.size();
                    int i = 0;
                    while (i < size) {
                        Line line = realmGet$lines.get(i);
                        Long l6 = map2.get(line);
                        if (l6 == null) {
                            l6 = Long.valueOf(com_ciot_realm_db_patrol_LineRealmProxy.insertOrUpdate(realm2, line, map2));
                        }
                        osList.setRow((long) i, l6.longValue());
                        i++;
                        j6 = j6;
                    }
                    j4 = j6;
                }
                Message realmGet$message = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmGet$message();
                if (realmGet$message != null) {
                    Long l7 = map2.get(realmGet$message);
                    if (l7 == null) {
                        l7 = Long.valueOf(com_ciot_realm_db_patrol_MessageRealmProxy.insertOrUpdate(realm2, realmGet$message, map2));
                    }
                    Table.nativeSetLink(nativePtr, patrolTaskBeanColumnInfo.messageColKey, j4, l7.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, patrolTaskBeanColumnInfo.messageColKey, j4);
                }
                j5 = j2;
            }
        }
    }

    public static PatrolTaskBean createDetachedCopy(PatrolTaskBean patrolTaskBean, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        PatrolTaskBean patrolTaskBean2;
        if (i > i2 || patrolTaskBean == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(patrolTaskBean);
        if (cacheData == null) {
            patrolTaskBean2 = new PatrolTaskBean();
            map.put(patrolTaskBean, new RealmObjectProxy.CacheData(i, patrolTaskBean2));
        } else if (i >= cacheData.minDepth) {
            return (PatrolTaskBean) cacheData.object;
        } else {
            cacheData.minDepth = i;
            patrolTaskBean2 = (PatrolTaskBean) cacheData.object;
        }
        com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface = patrolTaskBean2;
        com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2 = patrolTaskBean;
        com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$id(com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$id());
        com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$robotid(com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$robotid());
        com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$area(com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$area());
        com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$name(com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$name());
        com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$brief(com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$brief());
        int i3 = i + 1;
        com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$resource(com_ciot_realm_db_patrol_ResourceRealmProxy.createDetachedCopy(com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$resource(), i3, i2, map));
        com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$plan(com_ciot_realm_db_patrol_PlanRealmProxy.createDetachedCopy(com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$plan(), i3, i2, map));
        com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$count(com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$count());
        com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$map(com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$map());
        com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$backplace(com_ciot_realm_db_patrol_PlaceRealmProxy.createDetachedCopy(com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$backplace(), i3, i2, map));
        com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$prologud(com_ciot_realm_db_patrol_TransitionBeanRealmProxy.createDetachedCopy(com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$prologud(), i3, i2, map));
        if (i == i2) {
            com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$lines((RealmList<Line>) null);
        } else {
            RealmList<Line> realmGet$lines = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$lines();
            RealmList realmList = new RealmList();
            com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$lines(realmList);
            int size = realmGet$lines.size();
            for (int i4 = 0; i4 < size; i4++) {
                realmList.add(com_ciot_realm_db_patrol_LineRealmProxy.createDetachedCopy(realmGet$lines.get(i4), i3, i2, map));
            }
        }
        com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface.realmSet$message(com_ciot_realm_db_patrol_MessageRealmProxy.createDetachedCopy(com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$message(), i3, i2, map));
        return patrolTaskBean2;
    }

    static PatrolTaskBean update(Realm realm, PatrolTaskBeanColumnInfo patrolTaskBeanColumnInfo, PatrolTaskBean patrolTaskBean, PatrolTaskBean patrolTaskBean2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        PatrolTaskBeanColumnInfo patrolTaskBeanColumnInfo2 = patrolTaskBeanColumnInfo;
        Map<RealmModel, RealmObjectProxy> map2 = map;
        com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface = patrolTaskBean;
        com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxyInterface com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2 = patrolTaskBean2;
        Realm realm2 = realm;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(PatrolTaskBean.class), set);
        osObjectBuilder.addString(patrolTaskBeanColumnInfo2.idColKey, com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$id());
        osObjectBuilder.addString(patrolTaskBeanColumnInfo2.robotidColKey, com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$robotid());
        osObjectBuilder.addString(patrolTaskBeanColumnInfo2.areaColKey, com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$area());
        osObjectBuilder.addString(patrolTaskBeanColumnInfo2.nameColKey, com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$name());
        osObjectBuilder.addString(patrolTaskBeanColumnInfo2.briefColKey, com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$brief());
        Resource realmGet$resource = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$resource();
        if (realmGet$resource == null) {
            osObjectBuilder.addNull(patrolTaskBeanColumnInfo2.resourceColKey);
        } else {
            Resource resource = (Resource) map2.get(realmGet$resource);
            if (resource != null) {
                osObjectBuilder.addObject(patrolTaskBeanColumnInfo2.resourceColKey, resource);
            } else {
                osObjectBuilder.addObject(patrolTaskBeanColumnInfo2.resourceColKey, com_ciot_realm_db_patrol_ResourceRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_ResourceRealmProxy.ResourceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Resource.class), realmGet$resource, true, map, set));
            }
        }
        Plan realmGet$plan = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$plan();
        if (realmGet$plan == null) {
            osObjectBuilder.addNull(patrolTaskBeanColumnInfo2.planColKey);
        } else {
            Plan plan = (Plan) map2.get(realmGet$plan);
            if (plan != null) {
                osObjectBuilder.addObject(patrolTaskBeanColumnInfo2.planColKey, plan);
            } else {
                osObjectBuilder.addObject(patrolTaskBeanColumnInfo2.planColKey, com_ciot_realm_db_patrol_PlanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_PlanRealmProxy.PlanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Plan.class), realmGet$plan, true, map, set));
            }
        }
        osObjectBuilder.addInteger(patrolTaskBeanColumnInfo2.countColKey, Integer.valueOf(com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$count()));
        osObjectBuilder.addString(patrolTaskBeanColumnInfo2.mapColKey, com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$map());
        Place realmGet$backplace = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$backplace();
        if (realmGet$backplace == null) {
            osObjectBuilder.addNull(patrolTaskBeanColumnInfo2.backplaceColKey);
        } else {
            Place place = (Place) map2.get(realmGet$backplace);
            if (place != null) {
                osObjectBuilder.addObject(patrolTaskBeanColumnInfo2.backplaceColKey, place);
            } else {
                osObjectBuilder.addObject(patrolTaskBeanColumnInfo2.backplaceColKey, com_ciot_realm_db_patrol_PlaceRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_PlaceRealmProxy.PlaceColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Place.class), realmGet$backplace, true, map, set));
            }
        }
        TransitionBean realmGet$prologud = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$prologud();
        if (realmGet$prologud == null) {
            osObjectBuilder.addNull(patrolTaskBeanColumnInfo2.prologudColKey);
        } else {
            TransitionBean transitionBean = (TransitionBean) map2.get(realmGet$prologud);
            if (transitionBean != null) {
                osObjectBuilder.addObject(patrolTaskBeanColumnInfo2.prologudColKey, transitionBean);
            } else {
                osObjectBuilder.addObject(patrolTaskBeanColumnInfo2.prologudColKey, com_ciot_realm_db_patrol_TransitionBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_TransitionBeanRealmProxy.TransitionBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) TransitionBean.class), realmGet$prologud, true, map, set));
            }
        }
        RealmList<Line> realmGet$lines = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$lines();
        if (realmGet$lines != null) {
            RealmList realmList = new RealmList();
            for (int i = 0; i < realmGet$lines.size(); i++) {
                Line line = realmGet$lines.get(i);
                Line line2 = (Line) map2.get(line);
                if (line2 != null) {
                    realmList.add(line2);
                } else {
                    realmList.add(com_ciot_realm_db_patrol_LineRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_LineRealmProxy.LineColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Line.class), line, true, map, set));
                }
            }
            osObjectBuilder.addObjectList(patrolTaskBeanColumnInfo2.linesColKey, realmList);
        } else {
            osObjectBuilder.addObjectList(patrolTaskBeanColumnInfo2.linesColKey, new RealmList());
        }
        Message realmGet$message = com_ciot_realm_db_patrol_patroltaskbeanrealmproxyinterface2.realmGet$message();
        if (realmGet$message == null) {
            osObjectBuilder.addNull(patrolTaskBeanColumnInfo2.messageColKey);
        } else {
            Message message = (Message) map2.get(realmGet$message);
            if (message != null) {
                osObjectBuilder.addObject(patrolTaskBeanColumnInfo2.messageColKey, message);
            } else {
                osObjectBuilder.addObject(patrolTaskBeanColumnInfo2.messageColKey, com_ciot_realm_db_patrol_MessageRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_MessageRealmProxy.MessageColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Message.class), realmGet$message, true, map, set));
            }
        }
        osObjectBuilder.updateExistingObject();
        return patrolTaskBean;
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
        com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy com_ciot_realm_db_patrol_patroltaskbeanrealmproxy = (com_ciot_realm_db_patrol_PatrolTaskBeanRealmProxy) obj;
        BaseRealm realm$realm = this.proxyState.getRealm$realm();
        BaseRealm realm$realm2 = com_ciot_realm_db_patrol_patroltaskbeanrealmproxy.proxyState.getRealm$realm();
        String path = realm$realm.getPath();
        String path2 = realm$realm2.getPath();
        if (path == null ? path2 != null : !path.equals(path2)) {
            return false;
        }
        if (realm$realm.isFrozen() != realm$realm2.isFrozen() || !realm$realm.sharedRealm.getVersionID().equals(realm$realm2.sharedRealm.getVersionID())) {
            return false;
        }
        String name = this.proxyState.getRow$realm().getTable().getName();
        String name2 = com_ciot_realm_db_patrol_patroltaskbeanrealmproxy.proxyState.getRow$realm().getTable().getName();
        if (name == null ? name2 == null : name.equals(name2)) {
            return this.proxyState.getRow$realm().getObjectKey() == com_ciot_realm_db_patrol_patroltaskbeanrealmproxy.proxyState.getRow$realm().getObjectKey();
        }
        return false;
    }
}
