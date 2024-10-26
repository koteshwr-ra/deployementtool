package com.ciot.realm.db;

import com.blankj.utilcode.util.TimeUtils;
import com.ciot.realm.db.patrol.PathBean;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_TaskRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Task extends RealmObject implements Serializable, Comparable<Task>, com_ciot_realm_db_TaskRealmProxyInterface {
    public static final int COMPLETE_STATUE = 3;
    public static final int DOING_STATUE = 2;
    public static final int NOT_START_STATUE = 1;
    public static final int STOP_STATUE = 1;
    private static final long serialVersionUID = 1879129406395299482L;
    @SerializedName("flag")
    @Expose
    private int flag;
    @SerializedName("isExpand")
    @Expose
    private Boolean isExpand;
    @SerializedName("taskdata")
    @Expose
    private RealmList<ChildTask> mTaskdata;
    private MusicBean music;
    @SerializedName("oneTaskStatus")
    @Expose
    private int oneTaskStatus;
    @SerializedName("pathBeanList")
    @Expose
    private RealmList<PathBean> pathBeanList;
    @SerializedName("prologue")
    @Expose
    private String prologue;
    @SerializedName("starttime")
    @Expose
    private String startTime;
    @SerializedName("statisticRunEndTime")
    @Expose
    private long statisticRunEndTime;
    @SerializedName("statisticRunKm")
    @Expose
    private float statisticRunKm;
    @SerializedName("statisticRunStartTime")
    @Expose
    private long statisticRunStartTime;
    @SerializedName("taskname")
    @Expose
    private String taskName;
    @SerializedName("taskstatus")
    @Expose
    private boolean taskStatus;
    @SerializedName("tasktype")
    @Expose
    private int taskType;
    @SerializedName("taskid")
    @PrimaryKey
    @Expose
    private String taskid;
    @SerializedName("weekly")
    @Expose
    private String weekly;

    public int realmGet$flag() {
        return this.flag;
    }

    public Boolean realmGet$isExpand() {
        return this.isExpand;
    }

    public RealmList realmGet$mTaskdata() {
        return this.mTaskdata;
    }

    public MusicBean realmGet$music() {
        return this.music;
    }

    public int realmGet$oneTaskStatus() {
        return this.oneTaskStatus;
    }

    public RealmList realmGet$pathBeanList() {
        return this.pathBeanList;
    }

    public String realmGet$prologue() {
        return this.prologue;
    }

    public String realmGet$startTime() {
        return this.startTime;
    }

    public long realmGet$statisticRunEndTime() {
        return this.statisticRunEndTime;
    }

    public float realmGet$statisticRunKm() {
        return this.statisticRunKm;
    }

    public long realmGet$statisticRunStartTime() {
        return this.statisticRunStartTime;
    }

    public String realmGet$taskName() {
        return this.taskName;
    }

    public boolean realmGet$taskStatus() {
        return this.taskStatus;
    }

    public int realmGet$taskType() {
        return this.taskType;
    }

    public String realmGet$taskid() {
        return this.taskid;
    }

    public String realmGet$weekly() {
        return this.weekly;
    }

    public void realmSet$flag(int i) {
        this.flag = i;
    }

    public void realmSet$isExpand(Boolean bool) {
        this.isExpand = bool;
    }

    public void realmSet$mTaskdata(RealmList realmList) {
        this.mTaskdata = realmList;
    }

    public void realmSet$music(MusicBean musicBean) {
        this.music = musicBean;
    }

    public void realmSet$oneTaskStatus(int i) {
        this.oneTaskStatus = i;
    }

    public void realmSet$pathBeanList(RealmList realmList) {
        this.pathBeanList = realmList;
    }

    public void realmSet$prologue(String str) {
        this.prologue = str;
    }

    public void realmSet$startTime(String str) {
        this.startTime = str;
    }

    public void realmSet$statisticRunEndTime(long j) {
        this.statisticRunEndTime = j;
    }

    public void realmSet$statisticRunKm(float f) {
        this.statisticRunKm = f;
    }

    public void realmSet$statisticRunStartTime(long j) {
        this.statisticRunStartTime = j;
    }

    public void realmSet$taskName(String str) {
        this.taskName = str;
    }

    public void realmSet$taskStatus(boolean z) {
        this.taskStatus = z;
    }

    public void realmSet$taskType(int i) {
        this.taskType = i;
    }

    public void realmSet$taskid(String str) {
        this.taskid = str;
    }

    public void realmSet$weekly(String str) {
        this.weekly = str;
    }

    public Task() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$isExpand(true);
        realmSet$oneTaskStatus(-1);
    }

    public long getStatisticRunStartTime() {
        return realmGet$statisticRunStartTime();
    }

    public void setStatisticRunStartTime(Long l) {
        realmSet$statisticRunStartTime(l.longValue());
    }

    public long getStatisticRunEndTime() {
        return realmGet$statisticRunEndTime();
    }

    public void setStatisticRunEndTime(Long l) {
        realmSet$statisticRunEndTime(l.longValue());
    }

    public float getStatisticRunKm() {
        return realmGet$statisticRunKm();
    }

    public void setStatisticRunKm(float f) {
        realmSet$statisticRunKm(f);
    }

    public RealmList<PathBean> getPathBeanList() {
        return realmGet$pathBeanList();
    }

    public void setPathBeanList(RealmList<PathBean> realmList) {
        realmSet$pathBeanList(realmList);
    }

    public int getOneTaskStatus() {
        return realmGet$oneTaskStatus();
    }

    public void setOneTaskStatus(int i) {
        realmSet$oneTaskStatus(i);
    }

    public Boolean getExpand() {
        return realmGet$isExpand();
    }

    public void setExpand(Boolean bool) {
        realmSet$isExpand(bool);
    }

    public String getTaskid() {
        return realmGet$taskid();
    }

    public void setTaskid(String str) {
        realmSet$taskid(str);
    }

    public String getTaskName() {
        return realmGet$taskName();
    }

    public void setTaskName(String str) {
        realmSet$taskName(str);
    }

    public int getTaskType() {
        return realmGet$taskType();
    }

    public void setTaskType(int i) {
        realmSet$taskType(i);
    }

    public boolean isTaskStatus() {
        return realmGet$taskStatus();
    }

    public void setTaskStatus(boolean z) {
        realmSet$taskStatus(z);
    }

    public int getFlag() {
        return realmGet$flag();
    }

    public void setFlag(int i) {
        realmSet$flag(i);
    }

    public String getStartTime() {
        return realmGet$startTime();
    }

    public void setStartTime(String str) {
        realmSet$startTime(str);
    }

    public void setMusic(MusicBean musicBean) {
        realmSet$music(musicBean);
    }

    public MusicBean getMusic() {
        return realmGet$music();
    }

    public RealmList<ChildTask> getTaskdata() {
        return realmGet$mTaskdata();
    }

    public void setTaskdata(RealmList<ChildTask> realmList) {
        realmSet$mTaskdata(realmList);
    }

    public String getWeekly() {
        return realmGet$weekly();
    }

    public void setWeekly(String str) {
        realmSet$weekly(str);
    }

    public String toString() {
        return "Task{taskid='" + realmGet$taskid() + '\'' + ", taskName='" + realmGet$taskName() + '\'' + ", taskType=" + realmGet$taskType() + ", taskStatus=" + realmGet$taskStatus() + ", flag=" + realmGet$flag() + ", startTime='" + realmGet$startTime() + '\'' + ", music=" + realmGet$music() + ", weekly='" + realmGet$weekly() + '\'' + ", mTaskdata=" + realmGet$mTaskdata() + ", prologue='" + realmGet$prologue() + '\'' + ", isExpand=" + realmGet$isExpand() + ", oneTaskStatus=" + realmGet$oneTaskStatus() + ", pathBeanList=" + realmGet$pathBeanList() + '}';
    }

    private String getString(RealmList<ChildTask> realmList) {
        StringBuilder sb = new StringBuilder();
        if (realmList == null) {
            return null;
        }
        for (int i = 0; i < realmList.size(); i++) {
            sb.append(realmList.get(i));
            sb.append(",");
        }
        return sb.toString();
    }

    public int compareTo(Task task) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        int i = ((TimeUtils.string2Millis(task.getStartTime(), (DateFormat) simpleDateFormat) - TimeUtils.string2Millis(getStartTime(), (DateFormat) simpleDateFormat)) > 0 ? 1 : ((TimeUtils.string2Millis(task.getStartTime(), (DateFormat) simpleDateFormat) - TimeUtils.string2Millis(getStartTime(), (DateFormat) simpleDateFormat)) == 0 ? 0 : -1));
        if (i > 0) {
            return -1;
        }
        if (i < 0) {
            return 1;
        }
        return getTaskid().compareTo(task.getTaskid());
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof String) {
            return obj.equals(realmGet$taskid());
        }
        return super.equals(obj);
    }

    public String getPrologue() {
        return realmGet$prologue();
    }

    public void setPrologue(String str) {
        realmSet$prologue(str);
    }
}
