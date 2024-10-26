package com.ciot.realm.db;

import android.text.TextUtils;
import com.ciot.realm.db.patrol.Point;
import com.ciot.realm.db.patrol.Process;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_ChildTaskRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class ChildTask extends RealmObject implements Serializable, com_ciot_realm_db_ChildTaskRealmProxyInterface {
    private static final long serialVersionUID = 3022829960174555901L;
    @SerializedName("angle")
    @Expose
    private float angle;
    @SerializedName("firstsentence")
    @Expose
    private String firstSentence;
    @Expose
    public boolean isPathEdited;
    @Expose
    public boolean isPointEdited;
    @SerializedName("isSelected")
    @Expose
    private Boolean isSelected;
    @SerializedName("mapinfo")
    @Expose
    private String mapinfo;
    @SerializedName("p_type")
    @Expose
    private int p_type;
    @SerializedName("point")
    @Expose
    private Point point;
    @SerializedName("pointName")
    @Expose
    private String pointName;
    @SerializedName("process")
    @Expose
    private Process process;
    @SerializedName("tasknodeid")
    @Expose
    private String taskNodeID;
    @SerializedName("transition")
    @Expose
    private String transition;
    @SerializedName("waitPlayMedia")
    @Expose
    private boolean waitPlayMedia;
    @SerializedName("x")
    @Expose
    private float x;
    @SerializedName("y")
    @Expose
    private float y;
    @SerializedName("z")
    @Expose
    private int z;

    public float realmGet$angle() {
        return this.angle;
    }

    public String realmGet$firstSentence() {
        return this.firstSentence;
    }

    public boolean realmGet$isPathEdited() {
        return this.isPathEdited;
    }

    public boolean realmGet$isPointEdited() {
        return this.isPointEdited;
    }

    public Boolean realmGet$isSelected() {
        return this.isSelected;
    }

    public String realmGet$mapinfo() {
        return this.mapinfo;
    }

    public int realmGet$p_type() {
        return this.p_type;
    }

    public Point realmGet$point() {
        return this.point;
    }

    public String realmGet$pointName() {
        return this.pointName;
    }

    public Process realmGet$process() {
        return this.process;
    }

    public String realmGet$taskNodeID() {
        return this.taskNodeID;
    }

    public String realmGet$transition() {
        return this.transition;
    }

    public boolean realmGet$waitPlayMedia() {
        return this.waitPlayMedia;
    }

    public float realmGet$x() {
        return this.x;
    }

    public float realmGet$y() {
        return this.y;
    }

    public int realmGet$z() {
        return this.z;
    }

    public void realmSet$angle(float f) {
        this.angle = f;
    }

    public void realmSet$firstSentence(String str) {
        this.firstSentence = str;
    }

    public void realmSet$isPathEdited(boolean z2) {
        this.isPathEdited = z2;
    }

    public void realmSet$isPointEdited(boolean z2) {
        this.isPointEdited = z2;
    }

    public void realmSet$isSelected(Boolean bool) {
        this.isSelected = bool;
    }

    public void realmSet$mapinfo(String str) {
        this.mapinfo = str;
    }

    public void realmSet$p_type(int i) {
        this.p_type = i;
    }

    public void realmSet$point(Point point2) {
        this.point = point2;
    }

    public void realmSet$pointName(String str) {
        this.pointName = str;
    }

    public void realmSet$process(Process process2) {
        this.process = process2;
    }

    public void realmSet$taskNodeID(String str) {
        this.taskNodeID = str;
    }

    public void realmSet$transition(String str) {
        this.transition = str;
    }

    public void realmSet$waitPlayMedia(boolean z2) {
        this.waitPlayMedia = z2;
    }

    public void realmSet$x(float f) {
        this.x = f;
    }

    public void realmSet$y(float f) {
        this.y = f;
    }

    public void realmSet$z(int i) {
        this.z = i;
    }

    public ChildTask() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$isSelected(false);
    }

    public Boolean getSelected() {
        return realmGet$isSelected();
    }

    public void setSelected(Boolean bool) {
        realmSet$isSelected(bool);
    }

    public String getTaskNodeID() {
        return realmGet$taskNodeID();
    }

    public void setTaskNodeID(String str) {
        realmSet$taskNodeID(str);
    }

    public String getFirstSentence() {
        return realmGet$firstSentence();
    }

    public void setFirstSentence(String str) {
        realmSet$firstSentence(str);
    }

    public String getTransition() {
        return TextUtils.isEmpty(realmGet$transition()) ? "现在大家可以自由参观，稍后启程" : realmGet$transition();
    }

    public void setTransition(String str) {
        realmSet$transition(str);
    }

    public float getX() {
        return realmGet$x();
    }

    public void setX(float f) {
        realmSet$x(f);
    }

    public float getY() {
        return realmGet$y();
    }

    public void setY(float f) {
        realmSet$y(f);
    }

    public float getAngle() {
        return realmGet$angle();
    }

    public void setAngle(float f) {
        realmSet$angle(f);
    }

    public String getMapinfo() {
        return realmGet$mapinfo();
    }

    public void setMapinfo(String str) {
        realmSet$mapinfo(str);
    }

    public Point getPoint() {
        return realmGet$point();
    }

    public void setPoint(Point point2) {
        realmSet$point(point2);
    }

    public Process getProcess() {
        return realmGet$process();
    }

    public void setProcess(Process process2) {
        realmSet$process(process2);
    }

    public int getZ() {
        return realmGet$z();
    }

    public void setZ(int i) {
        realmSet$z(i);
    }

    public int getP_type() {
        return realmGet$p_type();
    }

    public String getPointName() {
        return realmGet$pointName();
    }

    public void setPointName(String str) {
        realmSet$pointName(str);
    }

    public void setPType(int i) {
        realmSet$p_type(i);
    }

    public boolean getWaitPlayMedia() {
        return realmGet$waitPlayMedia();
    }

    public void setWaitPlayMedia(boolean z2) {
        realmSet$waitPlayMedia(z2);
    }

    public boolean equals(Object obj) {
        if (obj instanceof ChildTask) {
            return ((ChildTask) obj).realmGet$pointName().equals(realmGet$pointName());
        }
        return super.equals(obj);
    }

    public String toString() {
        return "ChildTask{pointName=" + realmGet$pointName() + "taskNodeID=" + realmGet$taskNodeID() + ", x=" + realmGet$x() + ", y=" + realmGet$y() + ", z=" + realmGet$z() + ", angle=" + realmGet$angle() + ", mapinfo=" + realmGet$mapinfo() + ", point=" + realmGet$point() + ", process=" + realmGet$process() + '}';
    }
}
