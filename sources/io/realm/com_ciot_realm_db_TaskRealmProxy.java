package io.realm;

import android.util.JsonReader;
import android.util.JsonToken;
import com.ciot.realm.db.ChildTask;
import com.ciot.realm.db.MusicBean;
import com.ciot.realm.db.Task;
import com.ciot.realm.db.ad.CycleBean;
import com.ciot.realm.db.patrol.PathBean;
import io.realm.BaseRealm;
import io.realm.com_ciot_realm_db_ChildTaskRealmProxy;
import io.realm.com_ciot_realm_db_MusicBeanRealmProxy;
import io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxy;
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

public class com_ciot_realm_db_TaskRealmProxy extends Task implements RealmObjectProxy, com_ciot_realm_db_TaskRealmProxyInterface {
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private TaskColumnInfo columnInfo;
    private RealmList<ChildTask> mTaskdataRealmList;
    private RealmList<PathBean> pathBeanListRealmList;
    private ProxyState<Task> proxyState;

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Task";
    }

    public static String getSimpleClassName() {
        return ClassNameHelper.INTERNAL_CLASS_NAME;
    }

    static final class TaskColumnInfo extends ColumnInfo {
        long flagColKey;
        long isExpandColKey;
        long mTaskdataColKey;
        long musicColKey;
        long oneTaskStatusColKey;
        long pathBeanListColKey;
        long prologueColKey;
        long startTimeColKey;
        long statisticRunEndTimeColKey;
        long statisticRunKmColKey;
        long statisticRunStartTimeColKey;
        long taskNameColKey;
        long taskStatusColKey;
        long taskTypeColKey;
        long taskidColKey;
        long weeklyColKey;

        TaskColumnInfo(OsSchemaInfo osSchemaInfo) {
            super(16);
            OsObjectSchemaInfo objectSchemaInfo = osSchemaInfo.getObjectSchemaInfo(ClassNameHelper.INTERNAL_CLASS_NAME);
            this.taskidColKey = addColumnDetails("taskid", "taskid", objectSchemaInfo);
            this.taskNameColKey = addColumnDetails("taskName", "taskName", objectSchemaInfo);
            this.taskTypeColKey = addColumnDetails("taskType", "taskType", objectSchemaInfo);
            this.taskStatusColKey = addColumnDetails("taskStatus", "taskStatus", objectSchemaInfo);
            this.flagColKey = addColumnDetails("flag", "flag", objectSchemaInfo);
            this.startTimeColKey = addColumnDetails("startTime", "startTime", objectSchemaInfo);
            this.musicColKey = addColumnDetails("music", "music", objectSchemaInfo);
            this.weeklyColKey = addColumnDetails(CycleBean.WEEKLY_TYPE, CycleBean.WEEKLY_TYPE, objectSchemaInfo);
            this.mTaskdataColKey = addColumnDetails("mTaskdata", "mTaskdata", objectSchemaInfo);
            this.prologueColKey = addColumnDetails("prologue", "prologue", objectSchemaInfo);
            this.isExpandColKey = addColumnDetails("isExpand", "isExpand", objectSchemaInfo);
            this.oneTaskStatusColKey = addColumnDetails("oneTaskStatus", "oneTaskStatus", objectSchemaInfo);
            this.pathBeanListColKey = addColumnDetails("pathBeanList", "pathBeanList", objectSchemaInfo);
            this.statisticRunStartTimeColKey = addColumnDetails("statisticRunStartTime", "statisticRunStartTime", objectSchemaInfo);
            this.statisticRunEndTimeColKey = addColumnDetails("statisticRunEndTime", "statisticRunEndTime", objectSchemaInfo);
            this.statisticRunKmColKey = addColumnDetails("statisticRunKm", "statisticRunKm", objectSchemaInfo);
        }

        TaskColumnInfo(ColumnInfo columnInfo, boolean z) {
            super(columnInfo, z);
            copy(columnInfo, this);
        }

        /* access modifiers changed from: protected */
        public final ColumnInfo copy(boolean z) {
            return new TaskColumnInfo(this, z);
        }

        /* access modifiers changed from: protected */
        public final void copy(ColumnInfo columnInfo, ColumnInfo columnInfo2) {
            TaskColumnInfo taskColumnInfo = (TaskColumnInfo) columnInfo;
            TaskColumnInfo taskColumnInfo2 = (TaskColumnInfo) columnInfo2;
            taskColumnInfo2.taskidColKey = taskColumnInfo.taskidColKey;
            taskColumnInfo2.taskNameColKey = taskColumnInfo.taskNameColKey;
            taskColumnInfo2.taskTypeColKey = taskColumnInfo.taskTypeColKey;
            taskColumnInfo2.taskStatusColKey = taskColumnInfo.taskStatusColKey;
            taskColumnInfo2.flagColKey = taskColumnInfo.flagColKey;
            taskColumnInfo2.startTimeColKey = taskColumnInfo.startTimeColKey;
            taskColumnInfo2.musicColKey = taskColumnInfo.musicColKey;
            taskColumnInfo2.weeklyColKey = taskColumnInfo.weeklyColKey;
            taskColumnInfo2.mTaskdataColKey = taskColumnInfo.mTaskdataColKey;
            taskColumnInfo2.prologueColKey = taskColumnInfo.prologueColKey;
            taskColumnInfo2.isExpandColKey = taskColumnInfo.isExpandColKey;
            taskColumnInfo2.oneTaskStatusColKey = taskColumnInfo.oneTaskStatusColKey;
            taskColumnInfo2.pathBeanListColKey = taskColumnInfo.pathBeanListColKey;
            taskColumnInfo2.statisticRunStartTimeColKey = taskColumnInfo.statisticRunStartTimeColKey;
            taskColumnInfo2.statisticRunEndTimeColKey = taskColumnInfo.statisticRunEndTimeColKey;
            taskColumnInfo2.statisticRunKmColKey = taskColumnInfo.statisticRunKmColKey;
        }
    }

    com_ciot_realm_db_TaskRealmProxy() {
        this.proxyState.setConstructionFinished();
    }

    public void realm$injectObjectContext() {
        if (this.proxyState == null) {
            BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
            this.columnInfo = (TaskColumnInfo) realmObjectContext.getColumnInfo();
            ProxyState<Task> proxyState2 = new ProxyState<>(this);
            this.proxyState = proxyState2;
            proxyState2.setRealm$realm(realmObjectContext.getRealm());
            this.proxyState.setRow$realm(realmObjectContext.getRow());
            this.proxyState.setAcceptDefaultValue$realm(realmObjectContext.getAcceptDefaultValue());
            this.proxyState.setExcludeFields$realm(realmObjectContext.getExcludeFields());
        }
    }

    public String realmGet$taskid() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.taskidColKey);
    }

    public void realmSet$taskid(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            throw new RealmException("Primary key field 'taskid' cannot be changed after object was created.");
        }
    }

    public String realmGet$taskName() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.taskNameColKey);
    }

    public void realmSet$taskName(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.taskNameColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.taskNameColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.taskNameColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.taskNameColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public int realmGet$taskType() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.taskTypeColKey);
    }

    public void realmSet$taskType(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.taskTypeColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.taskTypeColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public boolean realmGet$taskStatus() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getBoolean(this.columnInfo.taskStatusColKey);
    }

    public void realmSet$taskStatus(boolean z) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setBoolean(this.columnInfo.taskStatusColKey, z);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setBoolean(this.columnInfo.taskStatusColKey, row$realm.getObjectKey(), z, true);
        }
    }

    public int realmGet$flag() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.flagColKey);
    }

    public void realmSet$flag(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.flagColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.flagColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public String realmGet$startTime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.startTimeColKey);
    }

    public void realmSet$startTime(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.startTimeColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.startTimeColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.startTimeColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.startTimeColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public MusicBean realmGet$music() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNullLink(this.columnInfo.musicColKey)) {
            return null;
        }
        return (MusicBean) this.proxyState.getRealm$realm().get(MusicBean.class, this.proxyState.getRow$realm().getLink(this.columnInfo.musicColKey), false, Collections.emptyList());
    }

    public void realmSet$music(MusicBean musicBean) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (musicBean == null) {
                this.proxyState.getRow$realm().nullifyLink(this.columnInfo.musicColKey);
                return;
            }
            this.proxyState.checkValidObject(musicBean);
            this.proxyState.getRow$realm().setLink(this.columnInfo.musicColKey, ((RealmObjectProxy) musicBean).realmGet$proxyState().getRow$realm().getObjectKey());
        } else if (this.proxyState.getAcceptDefaultValue$realm() && !this.proxyState.getExcludeFields$realm().contains("music")) {
            if (musicBean != null && !RealmObject.isManaged(musicBean)) {
                musicBean = (MusicBean) ((Realm) this.proxyState.getRealm$realm()).copyToRealm(musicBean, new ImportFlag[0]);
            }
            Row row$realm = this.proxyState.getRow$realm();
            if (musicBean == null) {
                row$realm.nullifyLink(this.columnInfo.musicColKey);
                return;
            }
            this.proxyState.checkValidObject(musicBean);
            row$realm.getTable().setLink(this.columnInfo.musicColKey, row$realm.getObjectKey(), ((RealmObjectProxy) musicBean).realmGet$proxyState().getRow$realm().getObjectKey(), true);
        }
    }

    public String realmGet$weekly() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.weeklyColKey);
    }

    public void realmSet$weekly(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.weeklyColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.weeklyColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.weeklyColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.weeklyColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public RealmList<ChildTask> realmGet$mTaskdata() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<ChildTask> realmList = this.mTaskdataRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<ChildTask> realmList2 = new RealmList<>(ChildTask.class, this.proxyState.getRow$realm().getModelList(this.columnInfo.mTaskdataColKey), this.proxyState.getRealm$realm());
        this.mTaskdataRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$mTaskdata(RealmList<ChildTask> realmList) {
        int i = 0;
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("mTaskdata")) {
                return;
            }
            if (realmList != null && !realmList.isManaged()) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<ChildTask> realmList2 = new RealmList<>();
                Iterator<ChildTask> it = realmList.iterator();
                while (it.hasNext()) {
                    ChildTask next = it.next();
                    if (next == null || RealmObject.isManaged(next)) {
                        realmList2.add(next);
                    } else {
                        realmList2.add((ChildTask) realm.copyToRealm(next, new ImportFlag[0]));
                    }
                }
                realmList = realmList2;
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList modelList = this.proxyState.getRow$realm().getModelList(this.columnInfo.mTaskdataColKey);
        if (realmList == null || ((long) realmList.size()) != modelList.size()) {
            modelList.removeAll();
            if (realmList != null) {
                int size = realmList.size();
                while (i < size) {
                    ChildTask childTask = realmList.get(i);
                    this.proxyState.checkValidObject(childTask);
                    modelList.addRow(((RealmObjectProxy) childTask).realmGet$proxyState().getRow$realm().getObjectKey());
                    i++;
                }
                return;
            }
            return;
        }
        int size2 = realmList.size();
        while (i < size2) {
            ChildTask childTask2 = realmList.get(i);
            this.proxyState.checkValidObject(childTask2);
            modelList.setRow((long) i, ((RealmObjectProxy) childTask2).realmGet$proxyState().getRow$realm().getObjectKey());
            i++;
        }
    }

    public String realmGet$prologue() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getString(this.columnInfo.prologueColKey);
    }

    public void realmSet$prologue(String str) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (str == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.prologueColKey);
            } else {
                this.proxyState.getRow$realm().setString(this.columnInfo.prologueColKey, str);
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (str == null) {
                row$realm.getTable().setNull(this.columnInfo.prologueColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setString(this.columnInfo.prologueColKey, row$realm.getObjectKey(), str, true);
            }
        }
    }

    public Boolean realmGet$isExpand() {
        this.proxyState.getRealm$realm().checkIfValid();
        if (this.proxyState.getRow$realm().isNull(this.columnInfo.isExpandColKey)) {
            return null;
        }
        return Boolean.valueOf(this.proxyState.getRow$realm().getBoolean(this.columnInfo.isExpandColKey));
    }

    public void realmSet$isExpand(Boolean bool) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            if (bool == null) {
                this.proxyState.getRow$realm().setNull(this.columnInfo.isExpandColKey);
            } else {
                this.proxyState.getRow$realm().setBoolean(this.columnInfo.isExpandColKey, bool.booleanValue());
            }
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            if (bool == null) {
                row$realm.getTable().setNull(this.columnInfo.isExpandColKey, row$realm.getObjectKey(), true);
            } else {
                row$realm.getTable().setBoolean(this.columnInfo.isExpandColKey, row$realm.getObjectKey(), bool.booleanValue(), true);
            }
        }
    }

    public int realmGet$oneTaskStatus() {
        this.proxyState.getRealm$realm().checkIfValid();
        return (int) this.proxyState.getRow$realm().getLong(this.columnInfo.oneTaskStatusColKey);
    }

    public void realmSet$oneTaskStatus(int i) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.oneTaskStatusColKey, (long) i);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.oneTaskStatusColKey, row$realm.getObjectKey(), (long) i, true);
        }
    }

    public RealmList<PathBean> realmGet$pathBeanList() {
        this.proxyState.getRealm$realm().checkIfValid();
        RealmList<PathBean> realmList = this.pathBeanListRealmList;
        if (realmList != null) {
            return realmList;
        }
        RealmList<PathBean> realmList2 = new RealmList<>(PathBean.class, this.proxyState.getRow$realm().getModelList(this.columnInfo.pathBeanListColKey), this.proxyState.getRealm$realm());
        this.pathBeanListRealmList = realmList2;
        return realmList2;
    }

    public void realmSet$pathBeanList(RealmList<PathBean> realmList) {
        int i = 0;
        if (this.proxyState.isUnderConstruction()) {
            if (!this.proxyState.getAcceptDefaultValue$realm() || this.proxyState.getExcludeFields$realm().contains("pathBeanList")) {
                return;
            }
            if (realmList != null && !realmList.isManaged()) {
                Realm realm = (Realm) this.proxyState.getRealm$realm();
                RealmList<PathBean> realmList2 = new RealmList<>();
                Iterator<PathBean> it = realmList.iterator();
                while (it.hasNext()) {
                    PathBean next = it.next();
                    if (next == null || RealmObject.isManaged(next)) {
                        realmList2.add(next);
                    } else {
                        realmList2.add((PathBean) realm.copyToRealm(next, new ImportFlag[0]));
                    }
                }
                realmList = realmList2;
            }
        }
        this.proxyState.getRealm$realm().checkIfValid();
        OsList modelList = this.proxyState.getRow$realm().getModelList(this.columnInfo.pathBeanListColKey);
        if (realmList == null || ((long) realmList.size()) != modelList.size()) {
            modelList.removeAll();
            if (realmList != null) {
                int size = realmList.size();
                while (i < size) {
                    PathBean pathBean = realmList.get(i);
                    this.proxyState.checkValidObject(pathBean);
                    modelList.addRow(((RealmObjectProxy) pathBean).realmGet$proxyState().getRow$realm().getObjectKey());
                    i++;
                }
                return;
            }
            return;
        }
        int size2 = realmList.size();
        while (i < size2) {
            PathBean pathBean2 = realmList.get(i);
            this.proxyState.checkValidObject(pathBean2);
            modelList.setRow((long) i, ((RealmObjectProxy) pathBean2).realmGet$proxyState().getRow$realm().getObjectKey());
            i++;
        }
    }

    public long realmGet$statisticRunStartTime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.statisticRunStartTimeColKey);
    }

    public void realmSet$statisticRunStartTime(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.statisticRunStartTimeColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.statisticRunStartTimeColKey, row$realm.getObjectKey(), j, true);
        }
    }

    public long realmGet$statisticRunEndTime() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getLong(this.columnInfo.statisticRunEndTimeColKey);
    }

    public void realmSet$statisticRunEndTime(long j) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setLong(this.columnInfo.statisticRunEndTimeColKey, j);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setLong(this.columnInfo.statisticRunEndTimeColKey, row$realm.getObjectKey(), j, true);
        }
    }

    public float realmGet$statisticRunKm() {
        this.proxyState.getRealm$realm().checkIfValid();
        return this.proxyState.getRow$realm().getFloat(this.columnInfo.statisticRunKmColKey);
    }

    public void realmSet$statisticRunKm(float f) {
        if (!this.proxyState.isUnderConstruction()) {
            this.proxyState.getRealm$realm().checkIfValid();
            this.proxyState.getRow$realm().setFloat(this.columnInfo.statisticRunKmColKey, f);
        } else if (this.proxyState.getAcceptDefaultValue$realm()) {
            Row row$realm = this.proxyState.getRow$realm();
            row$realm.getTable().setFloat(this.columnInfo.statisticRunKmColKey, row$realm.getObjectKey(), f, true);
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder(ClassNameHelper.INTERNAL_CLASS_NAME, 16, 0);
        OsObjectSchemaInfo.Builder builder2 = builder;
        builder2.addPersistedProperty("taskid", RealmFieldType.STRING, true, false, false);
        builder2.addPersistedProperty("taskName", RealmFieldType.STRING, false, false, false);
        builder2.addPersistedProperty("taskType", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("taskStatus", RealmFieldType.BOOLEAN, false, false, true);
        builder2.addPersistedProperty("flag", RealmFieldType.INTEGER, false, false, true);
        builder2.addPersistedProperty("startTime", RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("music", RealmFieldType.OBJECT, com_ciot_realm_db_MusicBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        builder.addPersistedProperty(CycleBean.WEEKLY_TYPE, RealmFieldType.STRING, false, false, false);
        builder.addPersistedLinkProperty("mTaskdata", RealmFieldType.LIST, com_ciot_realm_db_ChildTaskRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder3 = builder;
        builder3.addPersistedProperty("prologue", RealmFieldType.STRING, false, false, false);
        builder3.addPersistedProperty("isExpand", RealmFieldType.BOOLEAN, false, false, false);
        builder3.addPersistedProperty("oneTaskStatus", RealmFieldType.INTEGER, false, false, true);
        builder.addPersistedLinkProperty("pathBeanList", RealmFieldType.LIST, com_ciot_realm_db_patrol_PathBeanRealmProxy.ClassNameHelper.INTERNAL_CLASS_NAME);
        OsObjectSchemaInfo.Builder builder4 = builder;
        builder4.addPersistedProperty("statisticRunStartTime", RealmFieldType.INTEGER, false, false, true);
        builder4.addPersistedProperty("statisticRunEndTime", RealmFieldType.INTEGER, false, false, true);
        builder4.addPersistedProperty("statisticRunKm", RealmFieldType.FLOAT, false, false, true);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TaskColumnInfo createColumnInfo(OsSchemaInfo osSchemaInfo) {
        return new TaskColumnInfo(osSchemaInfo);
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [io.realm.RealmModel] */
    /* JADX WARNING: type inference failed for: r0v4, types: [io.realm.RealmModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x01ed  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0209  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x023e  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x025c  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x027a  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0117  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0181  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01b7  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01d0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.Task createOrUpdateUsingJsonObject(io.realm.Realm r13, org.json.JSONObject r14, boolean r15) throws org.json.JSONException {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 3
            r0.<init>(r1)
            java.lang.String r1 = "taskid"
            r2 = 0
            if (r15 == 0) goto L_0x0066
            java.lang.Class<com.ciot.realm.db.Task> r3 = com.ciot.realm.db.Task.class
            io.realm.internal.Table r3 = r13.getTable(r3)
            io.realm.RealmSchema r4 = r13.getSchema()
            java.lang.Class<com.ciot.realm.db.Task> r5 = com.ciot.realm.db.Task.class
            io.realm.internal.ColumnInfo r4 = r4.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r5)
            io.realm.com_ciot_realm_db_TaskRealmProxy$TaskColumnInfo r4 = (io.realm.com_ciot_realm_db_TaskRealmProxy.TaskColumnInfo) r4
            long r4 = r4.taskidColKey
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
            java.lang.Class<com.ciot.realm.db.Task> r4 = com.ciot.realm.db.Task.class
            io.realm.internal.ColumnInfo r10 = r3.getColumnInfo((java.lang.Class<? extends io.realm.RealmModel>) r4)     // Catch:{ all -> 0x0061 }
            r11 = 0
            java.util.List r12 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0061 }
            r7 = r6
            r8 = r13
            r7.set(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0061 }
            io.realm.com_ciot_realm_db_TaskRealmProxy r3 = new io.realm.com_ciot_realm_db_TaskRealmProxy     // Catch:{ all -> 0x0061 }
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
            java.lang.String r4 = "pathBeanList"
            java.lang.String r5 = "mTaskdata"
            java.lang.String r6 = "music"
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
            java.lang.Class<com.ciot.realm.db.Task> r1 = com.ciot.realm.db.Task.class
            io.realm.RealmModel r0 = r13.createObjectInternal(r1, r2, r7, r0)
            r3 = r0
            io.realm.com_ciot_realm_db_TaskRealmProxy r3 = (io.realm.com_ciot_realm_db_TaskRealmProxy) r3
            goto L_0x00b7
        L_0x00a1:
            java.lang.Class<com.ciot.realm.db.Task> r3 = com.ciot.realm.db.Task.class
            java.lang.String r1 = r14.getString(r1)
            io.realm.RealmModel r0 = r13.createObjectInternal(r3, r1, r7, r0)
            r3 = r0
            io.realm.com_ciot_realm_db_TaskRealmProxy r3 = (io.realm.com_ciot_realm_db_TaskRealmProxy) r3
            goto L_0x00b7
        L_0x00af:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "JSON object doesn't have the primary key field 'taskid'."
            r13.<init>(r14)
            throw r13
        L_0x00b7:
            r0 = r3
            io.realm.com_ciot_realm_db_TaskRealmProxyInterface r0 = (io.realm.com_ciot_realm_db_TaskRealmProxyInterface) r0
            java.lang.String r1 = "taskName"
            boolean r7 = r14.has(r1)
            if (r7 == 0) goto L_0x00d3
            boolean r7 = r14.isNull(r1)
            if (r7 == 0) goto L_0x00cc
            r0.realmSet$taskName(r2)
            goto L_0x00d3
        L_0x00cc:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$taskName(r1)
        L_0x00d3:
            java.lang.String r1 = "taskType"
            boolean r7 = r14.has(r1)
            if (r7 == 0) goto L_0x00f1
            boolean r7 = r14.isNull(r1)
            if (r7 != 0) goto L_0x00e9
            int r1 = r14.getInt(r1)
            r0.realmSet$taskType(r1)
            goto L_0x00f1
        L_0x00e9:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'taskType' to null."
            r13.<init>(r14)
            throw r13
        L_0x00f1:
            java.lang.String r1 = "taskStatus"
            boolean r7 = r14.has(r1)
            if (r7 == 0) goto L_0x010f
            boolean r7 = r14.isNull(r1)
            if (r7 != 0) goto L_0x0107
            boolean r1 = r14.getBoolean(r1)
            r0.realmSet$taskStatus(r1)
            goto L_0x010f
        L_0x0107:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'taskStatus' to null."
            r13.<init>(r14)
            throw r13
        L_0x010f:
            java.lang.String r1 = "flag"
            boolean r7 = r14.has(r1)
            if (r7 == 0) goto L_0x012d
            boolean r7 = r14.isNull(r1)
            if (r7 != 0) goto L_0x0125
            int r1 = r14.getInt(r1)
            r0.realmSet$flag(r1)
            goto L_0x012d
        L_0x0125:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'flag' to null."
            r13.<init>(r14)
            throw r13
        L_0x012d:
            java.lang.String r1 = "startTime"
            boolean r7 = r14.has(r1)
            if (r7 == 0) goto L_0x0146
            boolean r7 = r14.isNull(r1)
            if (r7 == 0) goto L_0x013f
            r0.realmSet$startTime(r2)
            goto L_0x0146
        L_0x013f:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$startTime(r1)
        L_0x0146:
            boolean r1 = r14.has(r6)
            if (r1 == 0) goto L_0x0161
            boolean r1 = r14.isNull(r6)
            if (r1 == 0) goto L_0x0156
            r0.realmSet$music(r2)
            goto L_0x0161
        L_0x0156:
            org.json.JSONObject r1 = r14.getJSONObject(r6)
            com.ciot.realm.db.MusicBean r1 = io.realm.com_ciot_realm_db_MusicBeanRealmProxy.createOrUpdateUsingJsonObject(r13, r1, r15)
            r0.realmSet$music(r1)
        L_0x0161:
            java.lang.String r1 = "weekly"
            boolean r6 = r14.has(r1)
            if (r6 == 0) goto L_0x017a
            boolean r6 = r14.isNull(r1)
            if (r6 == 0) goto L_0x0173
            r0.realmSet$weekly(r2)
            goto L_0x017a
        L_0x0173:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$weekly(r1)
        L_0x017a:
            boolean r1 = r14.has(r5)
            r6 = 0
            if (r1 == 0) goto L_0x01af
            boolean r1 = r14.isNull(r5)
            if (r1 == 0) goto L_0x018b
            r0.realmSet$mTaskdata(r2)
            goto L_0x01af
        L_0x018b:
            io.realm.RealmList r1 = r0.realmGet$mTaskdata()
            r1.clear()
            org.json.JSONArray r1 = r14.getJSONArray(r5)
            r5 = 0
        L_0x0197:
            int r7 = r1.length()
            if (r5 >= r7) goto L_0x01af
            org.json.JSONObject r7 = r1.getJSONObject(r5)
            com.ciot.realm.db.ChildTask r7 = io.realm.com_ciot_realm_db_ChildTaskRealmProxy.createOrUpdateUsingJsonObject(r13, r7, r15)
            io.realm.RealmList r8 = r0.realmGet$mTaskdata()
            r8.add(r7)
            int r5 = r5 + 1
            goto L_0x0197
        L_0x01af:
            java.lang.String r1 = "prologue"
            boolean r5 = r14.has(r1)
            if (r5 == 0) goto L_0x01c8
            boolean r5 = r14.isNull(r1)
            if (r5 == 0) goto L_0x01c1
            r0.realmSet$prologue(r2)
            goto L_0x01c8
        L_0x01c1:
            java.lang.String r1 = r14.getString(r1)
            r0.realmSet$prologue(r1)
        L_0x01c8:
            java.lang.String r1 = "isExpand"
            boolean r5 = r14.has(r1)
            if (r5 == 0) goto L_0x01e5
            boolean r5 = r14.isNull(r1)
            if (r5 == 0) goto L_0x01da
            r0.realmSet$isExpand(r2)
            goto L_0x01e5
        L_0x01da:
            boolean r1 = r14.getBoolean(r1)
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r0.realmSet$isExpand(r1)
        L_0x01e5:
            java.lang.String r1 = "oneTaskStatus"
            boolean r5 = r14.has(r1)
            if (r5 == 0) goto L_0x0203
            boolean r5 = r14.isNull(r1)
            if (r5 != 0) goto L_0x01fb
            int r1 = r14.getInt(r1)
            r0.realmSet$oneTaskStatus(r1)
            goto L_0x0203
        L_0x01fb:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'oneTaskStatus' to null."
            r13.<init>(r14)
            throw r13
        L_0x0203:
            boolean r1 = r14.has(r4)
            if (r1 == 0) goto L_0x0236
            boolean r1 = r14.isNull(r4)
            if (r1 == 0) goto L_0x0213
            r0.realmSet$pathBeanList(r2)
            goto L_0x0236
        L_0x0213:
            io.realm.RealmList r1 = r0.realmGet$pathBeanList()
            r1.clear()
            org.json.JSONArray r1 = r14.getJSONArray(r4)
        L_0x021e:
            int r2 = r1.length()
            if (r6 >= r2) goto L_0x0236
            org.json.JSONObject r2 = r1.getJSONObject(r6)
            com.ciot.realm.db.patrol.PathBean r2 = io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxy.createOrUpdateUsingJsonObject(r13, r2, r15)
            io.realm.RealmList r4 = r0.realmGet$pathBeanList()
            r4.add(r2)
            int r6 = r6 + 1
            goto L_0x021e
        L_0x0236:
            java.lang.String r13 = "statisticRunStartTime"
            boolean r15 = r14.has(r13)
            if (r15 == 0) goto L_0x0254
            boolean r15 = r14.isNull(r13)
            if (r15 != 0) goto L_0x024c
            long r1 = r14.getLong(r13)
            r0.realmSet$statisticRunStartTime(r1)
            goto L_0x0254
        L_0x024c:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'statisticRunStartTime' to null."
            r13.<init>(r14)
            throw r13
        L_0x0254:
            java.lang.String r13 = "statisticRunEndTime"
            boolean r15 = r14.has(r13)
            if (r15 == 0) goto L_0x0272
            boolean r15 = r14.isNull(r13)
            if (r15 != 0) goto L_0x026a
            long r1 = r14.getLong(r13)
            r0.realmSet$statisticRunEndTime(r1)
            goto L_0x0272
        L_0x026a:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'statisticRunEndTime' to null."
            r13.<init>(r14)
            throw r13
        L_0x0272:
            java.lang.String r13 = "statisticRunKm"
            boolean r15 = r14.has(r13)
            if (r15 == 0) goto L_0x0291
            boolean r15 = r14.isNull(r13)
            if (r15 != 0) goto L_0x0289
            double r13 = r14.getDouble(r13)
            float r13 = (float) r13
            r0.realmSet$statisticRunKm(r13)
            goto L_0x0291
        L_0x0289:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "Trying to set non-nullable field 'statisticRunKm' to null."
            r13.<init>(r14)
            throw r13
        L_0x0291:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_TaskRealmProxy.createOrUpdateUsingJsonObject(io.realm.Realm, org.json.JSONObject, boolean):com.ciot.realm.db.Task");
    }

    public static Task createUsingJsonStream(Realm realm, JsonReader jsonReader) throws IOException {
        Task task = new Task();
        com_ciot_realm_db_TaskRealmProxyInterface com_ciot_realm_db_taskrealmproxyinterface = task;
        jsonReader.beginObject();
        boolean z = false;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("taskid")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$taskid(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$taskid((String) null);
                }
                z = true;
            } else if (nextName.equals("taskName")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$taskName(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$taskName((String) null);
                }
            } else if (nextName.equals("taskType")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$taskType(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'taskType' to null.");
                }
            } else if (nextName.equals("taskStatus")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$taskStatus(jsonReader.nextBoolean());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'taskStatus' to null.");
                }
            } else if (nextName.equals("flag")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$flag(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'flag' to null.");
                }
            } else if (nextName.equals("startTime")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$startTime(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$startTime((String) null);
                }
            } else if (nextName.equals("music")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$music((MusicBean) null);
                } else {
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$music(com_ciot_realm_db_MusicBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                }
            } else if (nextName.equals(CycleBean.WEEKLY_TYPE)) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$weekly(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$weekly((String) null);
                }
            } else if (nextName.equals("mTaskdata")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$mTaskdata((RealmList<ChildTask>) null);
                } else {
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$mTaskdata(new RealmList());
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        com_ciot_realm_db_taskrealmproxyinterface.realmGet$mTaskdata().add(com_ciot_realm_db_ChildTaskRealmProxy.createUsingJsonStream(realm, jsonReader));
                    }
                    jsonReader.endArray();
                }
            } else if (nextName.equals("prologue")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$prologue(jsonReader.nextString());
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$prologue((String) null);
                }
            } else if (nextName.equals("isExpand")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$isExpand(Boolean.valueOf(jsonReader.nextBoolean()));
                } else {
                    jsonReader.skipValue();
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$isExpand((Boolean) null);
                }
            } else if (nextName.equals("oneTaskStatus")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$oneTaskStatus(jsonReader.nextInt());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'oneTaskStatus' to null.");
                }
            } else if (nextName.equals("pathBeanList")) {
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.skipValue();
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$pathBeanList((RealmList<PathBean>) null);
                } else {
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$pathBeanList(new RealmList());
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        com_ciot_realm_db_taskrealmproxyinterface.realmGet$pathBeanList().add(com_ciot_realm_db_patrol_PathBeanRealmProxy.createUsingJsonStream(realm, jsonReader));
                    }
                    jsonReader.endArray();
                }
            } else if (nextName.equals("statisticRunStartTime")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$statisticRunStartTime(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'statisticRunStartTime' to null.");
                }
            } else if (nextName.equals("statisticRunEndTime")) {
                if (jsonReader.peek() != JsonToken.NULL) {
                    com_ciot_realm_db_taskrealmproxyinterface.realmSet$statisticRunEndTime(jsonReader.nextLong());
                } else {
                    jsonReader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'statisticRunEndTime' to null.");
                }
            } else if (!nextName.equals("statisticRunKm")) {
                jsonReader.skipValue();
            } else if (jsonReader.peek() != JsonToken.NULL) {
                com_ciot_realm_db_taskrealmproxyinterface.realmSet$statisticRunKm((float) jsonReader.nextDouble());
            } else {
                jsonReader.skipValue();
                throw new IllegalArgumentException("Trying to set non-nullable field 'statisticRunKm' to null.");
            }
        }
        jsonReader.endObject();
        if (z) {
            return (Task) realm.copyToRealm(task, new ImportFlag[0]);
        }
        throw new IllegalArgumentException("JSON object doesn't have the primary key field 'taskid'.");
    }

    private static com_ciot_realm_db_TaskRealmProxy newProxyInstance(BaseRealm baseRealm, Row row) {
        BaseRealm.RealmObjectContext realmObjectContext = (BaseRealm.RealmObjectContext) BaseRealm.objectContext.get();
        realmObjectContext.set(baseRealm, row, baseRealm.getSchema().getColumnInfo((Class<? extends RealmModel>) Task.class), false, Collections.emptyList());
        com_ciot_realm_db_TaskRealmProxy com_ciot_realm_db_taskrealmproxy = new com_ciot_realm_db_TaskRealmProxy();
        realmObjectContext.clear();
        return com_ciot_realm_db_taskrealmproxy;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ciot.realm.db.Task copyOrUpdate(io.realm.Realm r8, io.realm.com_ciot_realm_db_TaskRealmProxy.TaskColumnInfo r9, com.ciot.realm.db.Task r10, boolean r11, java.util.Map<io.realm.RealmModel, io.realm.internal.RealmObjectProxy> r12, java.util.Set<io.realm.ImportFlag> r13) {
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
            com.ciot.realm.db.Task r1 = (com.ciot.realm.db.Task) r1
            return r1
        L_0x0051:
            r1 = 0
            if (r11 == 0) goto L_0x0099
            java.lang.Class<com.ciot.realm.db.Task> r2 = com.ciot.realm.db.Task.class
            io.realm.internal.Table r2 = r8.getTable(r2)
            long r3 = r9.taskidColKey
            r5 = r10
            io.realm.com_ciot_realm_db_TaskRealmProxyInterface r5 = (io.realm.com_ciot_realm_db_TaskRealmProxyInterface) r5
            java.lang.String r5 = r5.realmGet$taskid()
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
            io.realm.com_ciot_realm_db_TaskRealmProxy r1 = new io.realm.com_ciot_realm_db_TaskRealmProxy     // Catch:{ all -> 0x0094 }
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
            com.ciot.realm.db.Task r8 = update(r1, r2, r3, r4, r5, r6)
            goto L_0x00ab
        L_0x00a7:
            com.ciot.realm.db.Task r8 = copy(r8, r9, r10, r11, r12, r13)
        L_0x00ab:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.realm.com_ciot_realm_db_TaskRealmProxy.copyOrUpdate(io.realm.Realm, io.realm.com_ciot_realm_db_TaskRealmProxy$TaskColumnInfo, com.ciot.realm.db.Task, boolean, java.util.Map, java.util.Set):com.ciot.realm.db.Task");
    }

    public static Task copy(Realm realm, TaskColumnInfo taskColumnInfo, Task task, boolean z, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        Realm realm2 = realm;
        TaskColumnInfo taskColumnInfo2 = taskColumnInfo;
        Task task2 = task;
        Map<RealmModel, RealmObjectProxy> map2 = map;
        RealmObjectProxy realmObjectProxy = map2.get(task2);
        if (realmObjectProxy != null) {
            return (Task) realmObjectProxy;
        }
        com_ciot_realm_db_TaskRealmProxyInterface com_ciot_realm_db_taskrealmproxyinterface = task2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Task.class), set);
        osObjectBuilder.addString(taskColumnInfo2.taskidColKey, com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskid());
        osObjectBuilder.addString(taskColumnInfo2.taskNameColKey, com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskName());
        osObjectBuilder.addInteger(taskColumnInfo2.taskTypeColKey, Integer.valueOf(com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskType()));
        osObjectBuilder.addBoolean(taskColumnInfo2.taskStatusColKey, Boolean.valueOf(com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskStatus()));
        osObjectBuilder.addInteger(taskColumnInfo2.flagColKey, Integer.valueOf(com_ciot_realm_db_taskrealmproxyinterface.realmGet$flag()));
        osObjectBuilder.addString(taskColumnInfo2.startTimeColKey, com_ciot_realm_db_taskrealmproxyinterface.realmGet$startTime());
        osObjectBuilder.addString(taskColumnInfo2.weeklyColKey, com_ciot_realm_db_taskrealmproxyinterface.realmGet$weekly());
        osObjectBuilder.addString(taskColumnInfo2.prologueColKey, com_ciot_realm_db_taskrealmproxyinterface.realmGet$prologue());
        osObjectBuilder.addBoolean(taskColumnInfo2.isExpandColKey, com_ciot_realm_db_taskrealmproxyinterface.realmGet$isExpand());
        osObjectBuilder.addInteger(taskColumnInfo2.oneTaskStatusColKey, Integer.valueOf(com_ciot_realm_db_taskrealmproxyinterface.realmGet$oneTaskStatus()));
        osObjectBuilder.addInteger(taskColumnInfo2.statisticRunStartTimeColKey, Long.valueOf(com_ciot_realm_db_taskrealmproxyinterface.realmGet$statisticRunStartTime()));
        osObjectBuilder.addInteger(taskColumnInfo2.statisticRunEndTimeColKey, Long.valueOf(com_ciot_realm_db_taskrealmproxyinterface.realmGet$statisticRunEndTime()));
        osObjectBuilder.addFloat(taskColumnInfo2.statisticRunKmColKey, Float.valueOf(com_ciot_realm_db_taskrealmproxyinterface.realmGet$statisticRunKm()));
        com_ciot_realm_db_TaskRealmProxy newProxyInstance = newProxyInstance(realm, osObjectBuilder.createNewObject());
        map2.put(task2, newProxyInstance);
        MusicBean realmGet$music = com_ciot_realm_db_taskrealmproxyinterface.realmGet$music();
        if (realmGet$music == null) {
            newProxyInstance.realmSet$music((MusicBean) null);
        } else {
            MusicBean musicBean = (MusicBean) map2.get(realmGet$music);
            if (musicBean != null) {
                newProxyInstance.realmSet$music(musicBean);
            } else {
                newProxyInstance.realmSet$music(com_ciot_realm_db_MusicBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_MusicBeanRealmProxy.MusicBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MusicBean.class), realmGet$music, z, map, set));
            }
        }
        RealmList<ChildTask> realmGet$mTaskdata = com_ciot_realm_db_taskrealmproxyinterface.realmGet$mTaskdata();
        if (realmGet$mTaskdata != null) {
            RealmList<ChildTask> realmGet$mTaskdata2 = newProxyInstance.realmGet$mTaskdata();
            realmGet$mTaskdata2.clear();
            for (int i = 0; i < realmGet$mTaskdata.size(); i++) {
                ChildTask childTask = realmGet$mTaskdata.get(i);
                ChildTask childTask2 = (ChildTask) map2.get(childTask);
                if (childTask2 != null) {
                    realmGet$mTaskdata2.add(childTask2);
                } else {
                    realmGet$mTaskdata2.add(com_ciot_realm_db_ChildTaskRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ChildTaskRealmProxy.ChildTaskColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ChildTask.class), childTask, z, map, set));
                }
            }
        }
        RealmList<PathBean> realmGet$pathBeanList = com_ciot_realm_db_taskrealmproxyinterface.realmGet$pathBeanList();
        if (realmGet$pathBeanList != null) {
            RealmList<PathBean> realmGet$pathBeanList2 = newProxyInstance.realmGet$pathBeanList();
            realmGet$pathBeanList2.clear();
            for (int i2 = 0; i2 < realmGet$pathBeanList.size(); i2++) {
                PathBean pathBean = realmGet$pathBeanList.get(i2);
                PathBean pathBean2 = (PathBean) map2.get(pathBean);
                if (pathBean2 != null) {
                    realmGet$pathBeanList2.add(pathBean2);
                } else {
                    realmGet$pathBeanList2.add(com_ciot_realm_db_patrol_PathBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_PathBeanRealmProxy.PathBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PathBean.class), pathBean, z, map, set));
                }
            }
        }
        return newProxyInstance;
    }

    public static long insert(Realm realm, Task task, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        Realm realm2 = realm;
        Task task2 = task;
        Map<RealmModel, Long> map2 = map;
        if ((task2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(task)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) task2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(Task.class);
        long nativePtr = table.getNativePtr();
        TaskColumnInfo taskColumnInfo = (TaskColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Task.class);
        long j6 = taskColumnInfo.taskidColKey;
        com_ciot_realm_db_TaskRealmProxyInterface com_ciot_realm_db_taskrealmproxyinterface = task2;
        String realmGet$taskid = com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskid();
        if (realmGet$taskid == null) {
            j = Table.nativeFindFirstNull(nativePtr, j6);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j6, realmGet$taskid);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j6, realmGet$taskid);
        } else {
            Table.throwDuplicatePrimaryKeyException(realmGet$taskid);
        }
        long j7 = j;
        map2.put(task2, Long.valueOf(j7));
        String realmGet$taskName = com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskName();
        if (realmGet$taskName != null) {
            j2 = j7;
            Table.nativeSetString(nativePtr, taskColumnInfo.taskNameColKey, j7, realmGet$taskName, false);
        } else {
            j2 = j7;
        }
        long j8 = nativePtr;
        long j9 = j2;
        Table.nativeSetLong(j8, taskColumnInfo.taskTypeColKey, j9, (long) com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskType(), false);
        Table.nativeSetBoolean(j8, taskColumnInfo.taskStatusColKey, j9, com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskStatus(), false);
        Table.nativeSetLong(j8, taskColumnInfo.flagColKey, j9, (long) com_ciot_realm_db_taskrealmproxyinterface.realmGet$flag(), false);
        String realmGet$startTime = com_ciot_realm_db_taskrealmproxyinterface.realmGet$startTime();
        if (realmGet$startTime != null) {
            Table.nativeSetString(nativePtr, taskColumnInfo.startTimeColKey, j2, realmGet$startTime, false);
        }
        MusicBean realmGet$music = com_ciot_realm_db_taskrealmproxyinterface.realmGet$music();
        if (realmGet$music != null) {
            Long l = map2.get(realmGet$music);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_MusicBeanRealmProxy.insert(realm2, realmGet$music, map2));
            }
            Table.nativeSetLink(nativePtr, taskColumnInfo.musicColKey, j2, l.longValue(), false);
        }
        String realmGet$weekly = com_ciot_realm_db_taskrealmproxyinterface.realmGet$weekly();
        if (realmGet$weekly != null) {
            Table.nativeSetString(nativePtr, taskColumnInfo.weeklyColKey, j2, realmGet$weekly, false);
        }
        RealmList<ChildTask> realmGet$mTaskdata = com_ciot_realm_db_taskrealmproxyinterface.realmGet$mTaskdata();
        if (realmGet$mTaskdata != null) {
            j3 = j2;
            OsList osList = new OsList(table.getUncheckedRow(j3), taskColumnInfo.mTaskdataColKey);
            Iterator<ChildTask> it = realmGet$mTaskdata.iterator();
            while (it.hasNext()) {
                ChildTask next = it.next();
                Long l2 = map2.get(next);
                if (l2 == null) {
                    l2 = Long.valueOf(com_ciot_realm_db_ChildTaskRealmProxy.insert(realm2, next, map2));
                }
                osList.addRow(l2.longValue());
            }
        } else {
            j3 = j2;
        }
        String realmGet$prologue = com_ciot_realm_db_taskrealmproxyinterface.realmGet$prologue();
        if (realmGet$prologue != null) {
            j4 = j3;
            Table.nativeSetString(nativePtr, taskColumnInfo.prologueColKey, j3, realmGet$prologue, false);
        } else {
            j4 = j3;
        }
        Boolean realmGet$isExpand = com_ciot_realm_db_taskrealmproxyinterface.realmGet$isExpand();
        if (realmGet$isExpand != null) {
            Table.nativeSetBoolean(nativePtr, taskColumnInfo.isExpandColKey, j4, realmGet$isExpand.booleanValue(), false);
        }
        Table.nativeSetLong(nativePtr, taskColumnInfo.oneTaskStatusColKey, j4, (long) com_ciot_realm_db_taskrealmproxyinterface.realmGet$oneTaskStatus(), false);
        RealmList<PathBean> realmGet$pathBeanList = com_ciot_realm_db_taskrealmproxyinterface.realmGet$pathBeanList();
        if (realmGet$pathBeanList != null) {
            j5 = j4;
            OsList osList2 = new OsList(table.getUncheckedRow(j5), taskColumnInfo.pathBeanListColKey);
            Iterator<PathBean> it2 = realmGet$pathBeanList.iterator();
            while (it2.hasNext()) {
                PathBean next2 = it2.next();
                Long l3 = map2.get(next2);
                if (l3 == null) {
                    l3 = Long.valueOf(com_ciot_realm_db_patrol_PathBeanRealmProxy.insert(realm2, next2, map2));
                }
                osList2.addRow(l3.longValue());
            }
        } else {
            j5 = j4;
        }
        long j10 = nativePtr;
        long j11 = j5;
        Table.nativeSetLong(j10, taskColumnInfo.statisticRunStartTimeColKey, j5, com_ciot_realm_db_taskrealmproxyinterface.realmGet$statisticRunStartTime(), false);
        long j12 = j11;
        Table.nativeSetLong(j10, taskColumnInfo.statisticRunEndTimeColKey, j12, com_ciot_realm_db_taskrealmproxyinterface.realmGet$statisticRunEndTime(), false);
        Table.nativeSetFloat(j10, taskColumnInfo.statisticRunKmColKey, j12, com_ciot_realm_db_taskrealmproxyinterface.realmGet$statisticRunKm(), false);
        return j11;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(Task.class);
        long nativePtr = table.getNativePtr();
        TaskColumnInfo taskColumnInfo = (TaskColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Task.class);
        long j8 = taskColumnInfo.taskidColKey;
        while (it.hasNext()) {
            Task task = (Task) it.next();
            if (!map2.containsKey(task)) {
                if ((task instanceof RealmObjectProxy) && !RealmObject.isFrozen(task)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) task;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(task, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_TaskRealmProxyInterface com_ciot_realm_db_taskrealmproxyinterface = task;
                String realmGet$taskid = com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskid();
                if (realmGet$taskid == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j8);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j8, realmGet$taskid);
                }
                if (j == -1) {
                    j2 = OsObject.createRowWithPrimaryKey(table, j8, realmGet$taskid);
                } else {
                    Table.throwDuplicatePrimaryKeyException(realmGet$taskid);
                    j2 = j;
                }
                map2.put(task, Long.valueOf(j2));
                String realmGet$taskName = com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskName();
                if (realmGet$taskName != null) {
                    j4 = j2;
                    j3 = j8;
                    Table.nativeSetString(nativePtr, taskColumnInfo.taskNameColKey, j2, realmGet$taskName, false);
                } else {
                    j4 = j2;
                    j3 = j8;
                }
                long j9 = nativePtr;
                long j10 = j4;
                Table.nativeSetLong(j9, taskColumnInfo.taskTypeColKey, j10, (long) com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskType(), false);
                Table.nativeSetBoolean(j9, taskColumnInfo.taskStatusColKey, j10, com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskStatus(), false);
                Table.nativeSetLong(nativePtr, taskColumnInfo.flagColKey, j10, (long) com_ciot_realm_db_taskrealmproxyinterface.realmGet$flag(), false);
                String realmGet$startTime = com_ciot_realm_db_taskrealmproxyinterface.realmGet$startTime();
                if (realmGet$startTime != null) {
                    Table.nativeSetString(nativePtr, taskColumnInfo.startTimeColKey, j4, realmGet$startTime, false);
                }
                MusicBean realmGet$music = com_ciot_realm_db_taskrealmproxyinterface.realmGet$music();
                if (realmGet$music != null) {
                    Long l = map2.get(realmGet$music);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_MusicBeanRealmProxy.insert(realm2, realmGet$music, map2));
                    }
                    table.setLink(taskColumnInfo.musicColKey, j4, l.longValue(), false);
                }
                String realmGet$weekly = com_ciot_realm_db_taskrealmproxyinterface.realmGet$weekly();
                if (realmGet$weekly != null) {
                    Table.nativeSetString(nativePtr, taskColumnInfo.weeklyColKey, j4, realmGet$weekly, false);
                }
                RealmList<ChildTask> realmGet$mTaskdata = com_ciot_realm_db_taskrealmproxyinterface.realmGet$mTaskdata();
                if (realmGet$mTaskdata != null) {
                    j5 = j4;
                    OsList osList = new OsList(table.getUncheckedRow(j5), taskColumnInfo.mTaskdataColKey);
                    Iterator<ChildTask> it2 = realmGet$mTaskdata.iterator();
                    while (it2.hasNext()) {
                        ChildTask next = it2.next();
                        Long l2 = map2.get(next);
                        if (l2 == null) {
                            l2 = Long.valueOf(com_ciot_realm_db_ChildTaskRealmProxy.insert(realm2, next, map2));
                        }
                        osList.addRow(l2.longValue());
                    }
                } else {
                    j5 = j4;
                }
                String realmGet$prologue = com_ciot_realm_db_taskrealmproxyinterface.realmGet$prologue();
                if (realmGet$prologue != null) {
                    j6 = j5;
                    Table.nativeSetString(nativePtr, taskColumnInfo.prologueColKey, j5, realmGet$prologue, false);
                } else {
                    j6 = j5;
                }
                Boolean realmGet$isExpand = com_ciot_realm_db_taskrealmproxyinterface.realmGet$isExpand();
                if (realmGet$isExpand != null) {
                    Table.nativeSetBoolean(nativePtr, taskColumnInfo.isExpandColKey, j6, realmGet$isExpand.booleanValue(), false);
                }
                Table.nativeSetLong(nativePtr, taskColumnInfo.oneTaskStatusColKey, j6, (long) com_ciot_realm_db_taskrealmproxyinterface.realmGet$oneTaskStatus(), false);
                RealmList<PathBean> realmGet$pathBeanList = com_ciot_realm_db_taskrealmproxyinterface.realmGet$pathBeanList();
                if (realmGet$pathBeanList != null) {
                    j7 = j6;
                    OsList osList2 = new OsList(table.getUncheckedRow(j7), taskColumnInfo.pathBeanListColKey);
                    Iterator<PathBean> it3 = realmGet$pathBeanList.iterator();
                    while (it3.hasNext()) {
                        PathBean next2 = it3.next();
                        Long l3 = map2.get(next2);
                        if (l3 == null) {
                            l3 = Long.valueOf(com_ciot_realm_db_patrol_PathBeanRealmProxy.insert(realm2, next2, map2));
                        }
                        osList2.addRow(l3.longValue());
                    }
                } else {
                    j7 = j6;
                }
                long j11 = nativePtr;
                Table.nativeSetLong(j11, taskColumnInfo.statisticRunStartTimeColKey, j7, com_ciot_realm_db_taskrealmproxyinterface.realmGet$statisticRunStartTime(), false);
                long j12 = j7;
                Table.nativeSetLong(j11, taskColumnInfo.statisticRunEndTimeColKey, j12, com_ciot_realm_db_taskrealmproxyinterface.realmGet$statisticRunEndTime(), false);
                Table.nativeSetFloat(j11, taskColumnInfo.statisticRunKmColKey, j12, com_ciot_realm_db_taskrealmproxyinterface.realmGet$statisticRunKm(), false);
                j8 = j3;
            }
        }
    }

    public static long insertOrUpdate(Realm realm, Task task, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        Realm realm2 = realm;
        Task task2 = task;
        Map<RealmModel, Long> map2 = map;
        if ((task2 instanceof RealmObjectProxy) && !RealmObject.isFrozen(task)) {
            RealmObjectProxy realmObjectProxy = (RealmObjectProxy) task2;
            if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                return realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey();
            }
        }
        Table table = realm2.getTable(Task.class);
        long nativePtr = table.getNativePtr();
        TaskColumnInfo taskColumnInfo = (TaskColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Task.class);
        long j4 = taskColumnInfo.taskidColKey;
        com_ciot_realm_db_TaskRealmProxyInterface com_ciot_realm_db_taskrealmproxyinterface = task2;
        String realmGet$taskid = com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskid();
        if (realmGet$taskid == null) {
            j = Table.nativeFindFirstNull(nativePtr, j4);
        } else {
            j = Table.nativeFindFirstString(nativePtr, j4, realmGet$taskid);
        }
        if (j == -1) {
            j = OsObject.createRowWithPrimaryKey(table, j4, realmGet$taskid);
        }
        long j5 = j;
        map2.put(task2, Long.valueOf(j5));
        String realmGet$taskName = com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskName();
        if (realmGet$taskName != null) {
            j2 = j5;
            Table.nativeSetString(nativePtr, taskColumnInfo.taskNameColKey, j5, realmGet$taskName, false);
        } else {
            j2 = j5;
            Table.nativeSetNull(nativePtr, taskColumnInfo.taskNameColKey, j2, false);
        }
        long j6 = nativePtr;
        long j7 = j2;
        Table.nativeSetLong(j6, taskColumnInfo.taskTypeColKey, j7, (long) com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskType(), false);
        Table.nativeSetBoolean(j6, taskColumnInfo.taskStatusColKey, j7, com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskStatus(), false);
        Table.nativeSetLong(j6, taskColumnInfo.flagColKey, j7, (long) com_ciot_realm_db_taskrealmproxyinterface.realmGet$flag(), false);
        String realmGet$startTime = com_ciot_realm_db_taskrealmproxyinterface.realmGet$startTime();
        if (realmGet$startTime != null) {
            Table.nativeSetString(nativePtr, taskColumnInfo.startTimeColKey, j2, realmGet$startTime, false);
        } else {
            Table.nativeSetNull(nativePtr, taskColumnInfo.startTimeColKey, j2, false);
        }
        MusicBean realmGet$music = com_ciot_realm_db_taskrealmproxyinterface.realmGet$music();
        if (realmGet$music != null) {
            Long l = map2.get(realmGet$music);
            if (l == null) {
                l = Long.valueOf(com_ciot_realm_db_MusicBeanRealmProxy.insertOrUpdate(realm2, realmGet$music, map2));
            }
            Table.nativeSetLink(nativePtr, taskColumnInfo.musicColKey, j2, l.longValue(), false);
        } else {
            Table.nativeNullifyLink(nativePtr, taskColumnInfo.musicColKey, j2);
        }
        String realmGet$weekly = com_ciot_realm_db_taskrealmproxyinterface.realmGet$weekly();
        if (realmGet$weekly != null) {
            Table.nativeSetString(nativePtr, taskColumnInfo.weeklyColKey, j2, realmGet$weekly, false);
        } else {
            Table.nativeSetNull(nativePtr, taskColumnInfo.weeklyColKey, j2, false);
        }
        long j8 = j2;
        OsList osList = new OsList(table.getUncheckedRow(j8), taskColumnInfo.mTaskdataColKey);
        RealmList<ChildTask> realmGet$mTaskdata = com_ciot_realm_db_taskrealmproxyinterface.realmGet$mTaskdata();
        if (realmGet$mTaskdata == null || ((long) realmGet$mTaskdata.size()) != osList.size()) {
            osList.removeAll();
            if (realmGet$mTaskdata != null) {
                Iterator<ChildTask> it = realmGet$mTaskdata.iterator();
                while (it.hasNext()) {
                    ChildTask next = it.next();
                    Long l2 = map2.get(next);
                    if (l2 == null) {
                        l2 = Long.valueOf(com_ciot_realm_db_ChildTaskRealmProxy.insertOrUpdate(realm2, next, map2));
                    }
                    osList.addRow(l2.longValue());
                }
            }
        } else {
            int size = realmGet$mTaskdata.size();
            for (int i = 0; i < size; i++) {
                ChildTask childTask = realmGet$mTaskdata.get(i);
                Long l3 = map2.get(childTask);
                if (l3 == null) {
                    l3 = Long.valueOf(com_ciot_realm_db_ChildTaskRealmProxy.insertOrUpdate(realm2, childTask, map2));
                }
                osList.setRow((long) i, l3.longValue());
            }
        }
        String realmGet$prologue = com_ciot_realm_db_taskrealmproxyinterface.realmGet$prologue();
        if (realmGet$prologue != null) {
            j3 = j8;
            Table.nativeSetString(nativePtr, taskColumnInfo.prologueColKey, j8, realmGet$prologue, false);
        } else {
            j3 = j8;
            Table.nativeSetNull(nativePtr, taskColumnInfo.prologueColKey, j3, false);
        }
        Boolean realmGet$isExpand = com_ciot_realm_db_taskrealmproxyinterface.realmGet$isExpand();
        if (realmGet$isExpand != null) {
            Table.nativeSetBoolean(nativePtr, taskColumnInfo.isExpandColKey, j3, realmGet$isExpand.booleanValue(), false);
        } else {
            Table.nativeSetNull(nativePtr, taskColumnInfo.isExpandColKey, j3, false);
        }
        Table.nativeSetLong(nativePtr, taskColumnInfo.oneTaskStatusColKey, j3, (long) com_ciot_realm_db_taskrealmproxyinterface.realmGet$oneTaskStatus(), false);
        long j9 = j3;
        OsList osList2 = new OsList(table.getUncheckedRow(j9), taskColumnInfo.pathBeanListColKey);
        RealmList<PathBean> realmGet$pathBeanList = com_ciot_realm_db_taskrealmproxyinterface.realmGet$pathBeanList();
        if (realmGet$pathBeanList == null || ((long) realmGet$pathBeanList.size()) != osList2.size()) {
            osList2.removeAll();
            if (realmGet$pathBeanList != null) {
                Iterator<PathBean> it2 = realmGet$pathBeanList.iterator();
                while (it2.hasNext()) {
                    PathBean next2 = it2.next();
                    Long l4 = map2.get(next2);
                    if (l4 == null) {
                        l4 = Long.valueOf(com_ciot_realm_db_patrol_PathBeanRealmProxy.insertOrUpdate(realm2, next2, map2));
                    }
                    osList2.addRow(l4.longValue());
                }
            }
        } else {
            int size2 = realmGet$pathBeanList.size();
            for (int i2 = 0; i2 < size2; i2++) {
                PathBean pathBean = realmGet$pathBeanList.get(i2);
                Long l5 = map2.get(pathBean);
                if (l5 == null) {
                    l5 = Long.valueOf(com_ciot_realm_db_patrol_PathBeanRealmProxy.insertOrUpdate(realm2, pathBean, map2));
                }
                osList2.setRow((long) i2, l5.longValue());
            }
        }
        long j10 = nativePtr;
        long j11 = j9;
        Table.nativeSetLong(j10, taskColumnInfo.statisticRunStartTimeColKey, j9, com_ciot_realm_db_taskrealmproxyinterface.realmGet$statisticRunStartTime(), false);
        long j12 = j11;
        Table.nativeSetLong(j10, taskColumnInfo.statisticRunEndTimeColKey, j12, com_ciot_realm_db_taskrealmproxyinterface.realmGet$statisticRunEndTime(), false);
        Table.nativeSetFloat(j10, taskColumnInfo.statisticRunKmColKey, j12, com_ciot_realm_db_taskrealmproxyinterface.realmGet$statisticRunKm(), false);
        return j11;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> it, Map<RealmModel, Long> map) {
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        Realm realm2 = realm;
        Map<RealmModel, Long> map2 = map;
        Table table = realm2.getTable(Task.class);
        long nativePtr = table.getNativePtr();
        TaskColumnInfo taskColumnInfo = (TaskColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) Task.class);
        long j7 = taskColumnInfo.taskidColKey;
        while (it.hasNext()) {
            Task task = (Task) it.next();
            if (!map2.containsKey(task)) {
                if ((task instanceof RealmObjectProxy) && !RealmObject.isFrozen(task)) {
                    RealmObjectProxy realmObjectProxy = (RealmObjectProxy) task;
                    if (realmObjectProxy.realmGet$proxyState().getRealm$realm() != null && realmObjectProxy.realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                        map2.put(task, Long.valueOf(realmObjectProxy.realmGet$proxyState().getRow$realm().getObjectKey()));
                    }
                }
                com_ciot_realm_db_TaskRealmProxyInterface com_ciot_realm_db_taskrealmproxyinterface = task;
                String realmGet$taskid = com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskid();
                if (realmGet$taskid == null) {
                    j = Table.nativeFindFirstNull(nativePtr, j7);
                } else {
                    j = Table.nativeFindFirstString(nativePtr, j7, realmGet$taskid);
                }
                long createRowWithPrimaryKey = j == -1 ? OsObject.createRowWithPrimaryKey(table, j7, realmGet$taskid) : j;
                map2.put(task, Long.valueOf(createRowWithPrimaryKey));
                String realmGet$taskName = com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskName();
                if (realmGet$taskName != null) {
                    j3 = createRowWithPrimaryKey;
                    j2 = j7;
                    Table.nativeSetString(nativePtr, taskColumnInfo.taskNameColKey, createRowWithPrimaryKey, realmGet$taskName, false);
                } else {
                    j3 = createRowWithPrimaryKey;
                    j2 = j7;
                    Table.nativeSetNull(nativePtr, taskColumnInfo.taskNameColKey, createRowWithPrimaryKey, false);
                }
                long j8 = nativePtr;
                long j9 = j3;
                Table.nativeSetLong(j8, taskColumnInfo.taskTypeColKey, j9, (long) com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskType(), false);
                Table.nativeSetBoolean(j8, taskColumnInfo.taskStatusColKey, j9, com_ciot_realm_db_taskrealmproxyinterface.realmGet$taskStatus(), false);
                Table.nativeSetLong(nativePtr, taskColumnInfo.flagColKey, j9, (long) com_ciot_realm_db_taskrealmproxyinterface.realmGet$flag(), false);
                String realmGet$startTime = com_ciot_realm_db_taskrealmproxyinterface.realmGet$startTime();
                if (realmGet$startTime != null) {
                    Table.nativeSetString(nativePtr, taskColumnInfo.startTimeColKey, j3, realmGet$startTime, false);
                } else {
                    Table.nativeSetNull(nativePtr, taskColumnInfo.startTimeColKey, j3, false);
                }
                MusicBean realmGet$music = com_ciot_realm_db_taskrealmproxyinterface.realmGet$music();
                if (realmGet$music != null) {
                    Long l = map2.get(realmGet$music);
                    if (l == null) {
                        l = Long.valueOf(com_ciot_realm_db_MusicBeanRealmProxy.insertOrUpdate(realm2, realmGet$music, map2));
                    }
                    Table.nativeSetLink(nativePtr, taskColumnInfo.musicColKey, j3, l.longValue(), false);
                } else {
                    Table.nativeNullifyLink(nativePtr, taskColumnInfo.musicColKey, j3);
                }
                String realmGet$weekly = com_ciot_realm_db_taskrealmproxyinterface.realmGet$weekly();
                if (realmGet$weekly != null) {
                    Table.nativeSetString(nativePtr, taskColumnInfo.weeklyColKey, j3, realmGet$weekly, false);
                } else {
                    Table.nativeSetNull(nativePtr, taskColumnInfo.weeklyColKey, j3, false);
                }
                long j10 = j3;
                OsList osList = new OsList(table.getUncheckedRow(j10), taskColumnInfo.mTaskdataColKey);
                RealmList<ChildTask> realmGet$mTaskdata = com_ciot_realm_db_taskrealmproxyinterface.realmGet$mTaskdata();
                if (realmGet$mTaskdata == null || ((long) realmGet$mTaskdata.size()) != osList.size()) {
                    j4 = j10;
                    osList.removeAll();
                    if (realmGet$mTaskdata != null) {
                        Iterator<ChildTask> it2 = realmGet$mTaskdata.iterator();
                        while (it2.hasNext()) {
                            ChildTask next = it2.next();
                            Long l2 = map2.get(next);
                            if (l2 == null) {
                                l2 = Long.valueOf(com_ciot_realm_db_ChildTaskRealmProxy.insertOrUpdate(realm2, next, map2));
                            }
                            osList.addRow(l2.longValue());
                        }
                    }
                } else {
                    int size = realmGet$mTaskdata.size();
                    int i = 0;
                    while (i < size) {
                        ChildTask childTask = realmGet$mTaskdata.get(i);
                        Long l3 = map2.get(childTask);
                        if (l3 == null) {
                            l3 = Long.valueOf(com_ciot_realm_db_ChildTaskRealmProxy.insertOrUpdate(realm2, childTask, map2));
                        }
                        osList.setRow((long) i, l3.longValue());
                        i++;
                        j10 = j10;
                    }
                    j4 = j10;
                }
                String realmGet$prologue = com_ciot_realm_db_taskrealmproxyinterface.realmGet$prologue();
                if (realmGet$prologue != null) {
                    j5 = j4;
                    Table.nativeSetString(nativePtr, taskColumnInfo.prologueColKey, j4, realmGet$prologue, false);
                } else {
                    j5 = j4;
                    Table.nativeSetNull(nativePtr, taskColumnInfo.prologueColKey, j5, false);
                }
                Boolean realmGet$isExpand = com_ciot_realm_db_taskrealmproxyinterface.realmGet$isExpand();
                if (realmGet$isExpand != null) {
                    Table.nativeSetBoolean(nativePtr, taskColumnInfo.isExpandColKey, j5, realmGet$isExpand.booleanValue(), false);
                } else {
                    Table.nativeSetNull(nativePtr, taskColumnInfo.isExpandColKey, j5, false);
                }
                Table.nativeSetLong(nativePtr, taskColumnInfo.oneTaskStatusColKey, j5, (long) com_ciot_realm_db_taskrealmproxyinterface.realmGet$oneTaskStatus(), false);
                long j11 = j5;
                OsList osList2 = new OsList(table.getUncheckedRow(j11), taskColumnInfo.pathBeanListColKey);
                RealmList<PathBean> realmGet$pathBeanList = com_ciot_realm_db_taskrealmproxyinterface.realmGet$pathBeanList();
                if (realmGet$pathBeanList == null || ((long) realmGet$pathBeanList.size()) != osList2.size()) {
                    j6 = j11;
                    osList2.removeAll();
                    if (realmGet$pathBeanList != null) {
                        Iterator<PathBean> it3 = realmGet$pathBeanList.iterator();
                        while (it3.hasNext()) {
                            PathBean next2 = it3.next();
                            Long l4 = map2.get(next2);
                            if (l4 == null) {
                                l4 = Long.valueOf(com_ciot_realm_db_patrol_PathBeanRealmProxy.insertOrUpdate(realm2, next2, map2));
                            }
                            osList2.addRow(l4.longValue());
                        }
                    }
                } else {
                    int size2 = realmGet$pathBeanList.size();
                    int i2 = 0;
                    while (i2 < size2) {
                        PathBean pathBean = realmGet$pathBeanList.get(i2);
                        Long l5 = map2.get(pathBean);
                        if (l5 == null) {
                            l5 = Long.valueOf(com_ciot_realm_db_patrol_PathBeanRealmProxy.insertOrUpdate(realm2, pathBean, map2));
                        }
                        osList2.setRow((long) i2, l5.longValue());
                        i2++;
                        j11 = j11;
                    }
                    j6 = j11;
                }
                long j12 = nativePtr;
                Table.nativeSetLong(j12, taskColumnInfo.statisticRunStartTimeColKey, j6, com_ciot_realm_db_taskrealmproxyinterface.realmGet$statisticRunStartTime(), false);
                long j13 = j6;
                Table.nativeSetLong(j12, taskColumnInfo.statisticRunEndTimeColKey, j13, com_ciot_realm_db_taskrealmproxyinterface.realmGet$statisticRunEndTime(), false);
                Table.nativeSetFloat(j12, taskColumnInfo.statisticRunKmColKey, j13, com_ciot_realm_db_taskrealmproxyinterface.realmGet$statisticRunKm(), false);
                j7 = j2;
            }
        }
    }

    public static Task createDetachedCopy(Task task, int i, int i2, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> map) {
        Task task2;
        if (i > i2 || task == null) {
            return null;
        }
        RealmObjectProxy.CacheData cacheData = map.get(task);
        if (cacheData == null) {
            task2 = new Task();
            map.put(task, new RealmObjectProxy.CacheData(i, task2));
        } else if (i >= cacheData.minDepth) {
            return (Task) cacheData.object;
        } else {
            cacheData.minDepth = i;
            task2 = (Task) cacheData.object;
        }
        com_ciot_realm_db_TaskRealmProxyInterface com_ciot_realm_db_taskrealmproxyinterface = task2;
        com_ciot_realm_db_TaskRealmProxyInterface com_ciot_realm_db_taskrealmproxyinterface2 = task;
        com_ciot_realm_db_taskrealmproxyinterface.realmSet$taskid(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$taskid());
        com_ciot_realm_db_taskrealmproxyinterface.realmSet$taskName(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$taskName());
        com_ciot_realm_db_taskrealmproxyinterface.realmSet$taskType(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$taskType());
        com_ciot_realm_db_taskrealmproxyinterface.realmSet$taskStatus(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$taskStatus());
        com_ciot_realm_db_taskrealmproxyinterface.realmSet$flag(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$flag());
        com_ciot_realm_db_taskrealmproxyinterface.realmSet$startTime(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$startTime());
        int i3 = i + 1;
        com_ciot_realm_db_taskrealmproxyinterface.realmSet$music(com_ciot_realm_db_MusicBeanRealmProxy.createDetachedCopy(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$music(), i3, i2, map));
        com_ciot_realm_db_taskrealmproxyinterface.realmSet$weekly(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$weekly());
        if (i == i2) {
            com_ciot_realm_db_taskrealmproxyinterface.realmSet$mTaskdata((RealmList<ChildTask>) null);
        } else {
            RealmList<ChildTask> realmGet$mTaskdata = com_ciot_realm_db_taskrealmproxyinterface2.realmGet$mTaskdata();
            RealmList realmList = new RealmList();
            com_ciot_realm_db_taskrealmproxyinterface.realmSet$mTaskdata(realmList);
            int size = realmGet$mTaskdata.size();
            for (int i4 = 0; i4 < size; i4++) {
                realmList.add(com_ciot_realm_db_ChildTaskRealmProxy.createDetachedCopy(realmGet$mTaskdata.get(i4), i3, i2, map));
            }
        }
        com_ciot_realm_db_taskrealmproxyinterface.realmSet$prologue(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$prologue());
        com_ciot_realm_db_taskrealmproxyinterface.realmSet$isExpand(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$isExpand());
        com_ciot_realm_db_taskrealmproxyinterface.realmSet$oneTaskStatus(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$oneTaskStatus());
        if (i == i2) {
            com_ciot_realm_db_taskrealmproxyinterface.realmSet$pathBeanList((RealmList<PathBean>) null);
        } else {
            RealmList<PathBean> realmGet$pathBeanList = com_ciot_realm_db_taskrealmproxyinterface2.realmGet$pathBeanList();
            RealmList realmList2 = new RealmList();
            com_ciot_realm_db_taskrealmproxyinterface.realmSet$pathBeanList(realmList2);
            int size2 = realmGet$pathBeanList.size();
            for (int i5 = 0; i5 < size2; i5++) {
                realmList2.add(com_ciot_realm_db_patrol_PathBeanRealmProxy.createDetachedCopy(realmGet$pathBeanList.get(i5), i3, i2, map));
            }
        }
        com_ciot_realm_db_taskrealmproxyinterface.realmSet$statisticRunStartTime(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$statisticRunStartTime());
        com_ciot_realm_db_taskrealmproxyinterface.realmSet$statisticRunEndTime(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$statisticRunEndTime());
        com_ciot_realm_db_taskrealmproxyinterface.realmSet$statisticRunKm(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$statisticRunKm());
        return task2;
    }

    static Task update(Realm realm, TaskColumnInfo taskColumnInfo, Task task, Task task2, Map<RealmModel, RealmObjectProxy> map, Set<ImportFlag> set) {
        TaskColumnInfo taskColumnInfo2 = taskColumnInfo;
        Map<RealmModel, RealmObjectProxy> map2 = map;
        com_ciot_realm_db_TaskRealmProxyInterface com_ciot_realm_db_taskrealmproxyinterface = task;
        com_ciot_realm_db_TaskRealmProxyInterface com_ciot_realm_db_taskrealmproxyinterface2 = task2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(realm.getTable(Task.class), set);
        osObjectBuilder.addString(taskColumnInfo2.taskidColKey, com_ciot_realm_db_taskrealmproxyinterface2.realmGet$taskid());
        osObjectBuilder.addString(taskColumnInfo2.taskNameColKey, com_ciot_realm_db_taskrealmproxyinterface2.realmGet$taskName());
        osObjectBuilder.addInteger(taskColumnInfo2.taskTypeColKey, Integer.valueOf(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$taskType()));
        osObjectBuilder.addBoolean(taskColumnInfo2.taskStatusColKey, Boolean.valueOf(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$taskStatus()));
        osObjectBuilder.addInteger(taskColumnInfo2.flagColKey, Integer.valueOf(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$flag()));
        osObjectBuilder.addString(taskColumnInfo2.startTimeColKey, com_ciot_realm_db_taskrealmproxyinterface2.realmGet$startTime());
        MusicBean realmGet$music = com_ciot_realm_db_taskrealmproxyinterface2.realmGet$music();
        if (realmGet$music == null) {
            osObjectBuilder.addNull(taskColumnInfo2.musicColKey);
        } else {
            MusicBean musicBean = (MusicBean) map2.get(realmGet$music);
            if (musicBean != null) {
                osObjectBuilder.addObject(taskColumnInfo2.musicColKey, musicBean);
            } else {
                osObjectBuilder.addObject(taskColumnInfo2.musicColKey, com_ciot_realm_db_MusicBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_MusicBeanRealmProxy.MusicBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) MusicBean.class), realmGet$music, true, map, set));
            }
        }
        osObjectBuilder.addString(taskColumnInfo2.weeklyColKey, com_ciot_realm_db_taskrealmproxyinterface2.realmGet$weekly());
        RealmList<ChildTask> realmGet$mTaskdata = com_ciot_realm_db_taskrealmproxyinterface2.realmGet$mTaskdata();
        if (realmGet$mTaskdata != null) {
            RealmList realmList = new RealmList();
            for (int i = 0; i < realmGet$mTaskdata.size(); i++) {
                ChildTask childTask = realmGet$mTaskdata.get(i);
                ChildTask childTask2 = (ChildTask) map2.get(childTask);
                if (childTask2 != null) {
                    realmList.add(childTask2);
                } else {
                    realmList.add(com_ciot_realm_db_ChildTaskRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_ChildTaskRealmProxy.ChildTaskColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) ChildTask.class), childTask, true, map, set));
                }
            }
            osObjectBuilder.addObjectList(taskColumnInfo2.mTaskdataColKey, realmList);
        } else {
            osObjectBuilder.addObjectList(taskColumnInfo2.mTaskdataColKey, new RealmList());
        }
        osObjectBuilder.addString(taskColumnInfo2.prologueColKey, com_ciot_realm_db_taskrealmproxyinterface2.realmGet$prologue());
        osObjectBuilder.addBoolean(taskColumnInfo2.isExpandColKey, com_ciot_realm_db_taskrealmproxyinterface2.realmGet$isExpand());
        osObjectBuilder.addInteger(taskColumnInfo2.oneTaskStatusColKey, Integer.valueOf(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$oneTaskStatus()));
        RealmList<PathBean> realmGet$pathBeanList = com_ciot_realm_db_taskrealmproxyinterface2.realmGet$pathBeanList();
        if (realmGet$pathBeanList != null) {
            RealmList realmList2 = new RealmList();
            for (int i2 = 0; i2 < realmGet$pathBeanList.size(); i2++) {
                PathBean pathBean = realmGet$pathBeanList.get(i2);
                PathBean pathBean2 = (PathBean) map2.get(pathBean);
                if (pathBean2 != null) {
                    realmList2.add(pathBean2);
                } else {
                    realmList2.add(com_ciot_realm_db_patrol_PathBeanRealmProxy.copyOrUpdate(realm, (com_ciot_realm_db_patrol_PathBeanRealmProxy.PathBeanColumnInfo) realm.getSchema().getColumnInfo((Class<? extends RealmModel>) PathBean.class), pathBean, true, map, set));
                }
            }
            osObjectBuilder.addObjectList(taskColumnInfo2.pathBeanListColKey, realmList2);
        } else {
            osObjectBuilder.addObjectList(taskColumnInfo2.pathBeanListColKey, new RealmList());
        }
        osObjectBuilder.addInteger(taskColumnInfo2.statisticRunStartTimeColKey, Long.valueOf(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$statisticRunStartTime()));
        osObjectBuilder.addInteger(taskColumnInfo2.statisticRunEndTimeColKey, Long.valueOf(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$statisticRunEndTime()));
        osObjectBuilder.addFloat(taskColumnInfo2.statisticRunKmColKey, Float.valueOf(com_ciot_realm_db_taskrealmproxyinterface2.realmGet$statisticRunKm()));
        osObjectBuilder.updateExistingObject();
        return task;
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
