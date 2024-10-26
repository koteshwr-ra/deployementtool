package com.ciot.realm.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_MusicBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class MusicBean extends RealmObject implements Serializable, com_ciot_realm_db_MusicBeanRealmProxyInterface {
    private static final long serialVersionUID = -5985408206000914049L;
    private int duration;
    @PrimaryKey
    private int id;
    private boolean isSelect;
    private String name;
    private String path;
    private String singer;
    private long size;

    public int realmGet$duration() {
        return this.duration;
    }

    public int realmGet$id() {
        return this.id;
    }

    public boolean realmGet$isSelect() {
        return this.isSelect;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$path() {
        return this.path;
    }

    public String realmGet$singer() {
        return this.singer;
    }

    public long realmGet$size() {
        return this.size;
    }

    public void realmSet$duration(int i) {
        this.duration = i;
    }

    public void realmSet$id(int i) {
        this.id = i;
    }

    public void realmSet$isSelect(boolean z) {
        this.isSelect = z;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$path(String str) {
        this.path = str;
    }

    public void realmSet$singer(String str) {
        this.singer = str;
    }

    public void realmSet$size(long j) {
        this.size = j;
    }

    public MusicBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$isSelect(false);
    }

    public MusicBean(int i, String str) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$isSelect(false);
        realmSet$id(i);
        realmSet$name(str);
    }

    public int getId() {
        return realmGet$id();
    }

    public void setId(int i) {
        realmSet$id(i);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String str) {
        realmSet$name(str);
    }

    public String getPath() {
        return realmGet$path();
    }

    public void setPath(String str) {
        realmSet$path(str);
    }

    public String getSinger() {
        return realmGet$singer();
    }

    public void setSinger(String str) {
        realmSet$singer(str);
    }

    public int getDuration() {
        return realmGet$duration();
    }

    public void setDuration(int i) {
        realmSet$duration(i);
    }

    public long getSize() {
        return realmGet$size();
    }

    public void setSize(long j) {
        realmSet$size(j);
    }

    public boolean isSelect() {
        return realmGet$isSelect();
    }

    public void setSelect(boolean z) {
        realmSet$isSelect(z);
    }

    public String toString() {
        return "MusicBean{id=" + realmGet$id() + ", name='" + realmGet$name() + '\'' + ", path='" + realmGet$path() + '\'' + ", singer='" + realmGet$singer() + '\'' + ", duration=" + realmGet$duration() + ", size=" + realmGet$size() + ", isSelect=" + realmGet$isSelect() + '}';
    }
}
