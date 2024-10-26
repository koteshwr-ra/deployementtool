package com.ciot.realm.db.patrol;

import com.blankj.utilcode.util.GsonUtils;
import com.ciot.realm.db.ChildTask;
import com.google.gson.reflect.TypeToken;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_patrol_PathBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;
import java.util.List;

public class PathBean extends RealmObject implements Serializable, Cloneable, com_ciot_realm_db_patrol_PathBeanRealmProxyInterface {
    private Boolean isSelected;
    private String path;
    @PrimaryKey
    private String pathId;
    private String pathName;

    public Boolean realmGet$isSelected() {
        return this.isSelected;
    }

    public String realmGet$path() {
        return this.path;
    }

    public String realmGet$pathId() {
        return this.pathId;
    }

    public String realmGet$pathName() {
        return this.pathName;
    }

    public void realmSet$isSelected(Boolean bool) {
        this.isSelected = bool;
    }

    public void realmSet$path(String str) {
        this.path = str;
    }

    public void realmSet$pathId(String str) {
        this.pathId = str;
    }

    public void realmSet$pathName(String str) {
        this.pathName = str;
    }

    public Boolean getSelected() {
        return realmGet$isSelected();
    }

    public void setSelected(Boolean bool) {
        realmSet$isSelected(bool);
    }

    public PathBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$isSelected(false);
    }

    public PathBean(String str, String str2, List<Line> list) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$isSelected(false);
        realmSet$pathId(str);
        realmSet$pathName(str2);
        realmSet$path(GsonUtils.toJson(list));
    }

    public String getPathId() {
        return realmGet$pathId();
    }

    public void setPathId(String str) {
        realmSet$pathId(str);
    }

    public String getPathName() {
        return realmGet$pathName();
    }

    public void setPathName(String str) {
        realmSet$pathName(str);
    }

    public List<Line> getPath() {
        return (List) GsonUtils.fromJson(realmGet$path(), new TypeToken<List<Line>>() {
        }.getType());
    }

    public void setPath(List<Line> list) {
        realmSet$path(GsonUtils.toJson(list));
    }

    public List<ChildTask> getTaskPath() {
        return (List) GsonUtils.fromJson(realmGet$path(), new TypeToken<List<ChildTask>>() {
        }.getType());
    }

    public void setTaskPath(List<ChildTask> list) {
        realmSet$path(GsonUtils.toJson(list));
    }

    public String toString() {
        return "PathBean{pathId='" + realmGet$pathId() + '\'' + "pathName='" + realmGet$pathName() + '\'' + ", path=" + realmGet$path() + '}';
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
