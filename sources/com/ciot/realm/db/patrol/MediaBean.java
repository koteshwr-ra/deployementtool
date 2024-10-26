package com.ciot.realm.db.patrol;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class MediaBean extends RealmObject implements Serializable, com_ciot_realm_db_patrol_MediaBeanRealmProxyInterface {
    public int action;
    private RealmList<Resource> audio;
    private String broadcast;
    public int count;
    @PrimaryKey
    private String id;
    private String kind;
    private RealmList<Resource> picture;
    private String placeid;
    private String text;
    private String title;
    private String type;
    private RealmList<Resource> video;
    private RealmList<Resource> videoCover;

    public int realmGet$action() {
        return this.action;
    }

    public RealmList realmGet$audio() {
        return this.audio;
    }

    public String realmGet$broadcast() {
        return this.broadcast;
    }

    public int realmGet$count() {
        return this.count;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$kind() {
        return this.kind;
    }

    public RealmList realmGet$picture() {
        return this.picture;
    }

    public String realmGet$placeid() {
        return this.placeid;
    }

    public String realmGet$text() {
        return this.text;
    }

    public String realmGet$title() {
        return this.title;
    }

    public String realmGet$type() {
        return this.type;
    }

    public RealmList realmGet$video() {
        return this.video;
    }

    public RealmList realmGet$videoCover() {
        return this.videoCover;
    }

    public void realmSet$action(int i) {
        this.action = i;
    }

    public void realmSet$audio(RealmList realmList) {
        this.audio = realmList;
    }

    public void realmSet$broadcast(String str) {
        this.broadcast = str;
    }

    public void realmSet$count(int i) {
        this.count = i;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$kind(String str) {
        this.kind = str;
    }

    public void realmSet$picture(RealmList realmList) {
        this.picture = realmList;
    }

    public void realmSet$placeid(String str) {
        this.placeid = str;
    }

    public void realmSet$text(String str) {
        this.text = str;
    }

    public void realmSet$title(String str) {
        this.title = str;
    }

    public void realmSet$type(String str) {
        this.type = str;
    }

    public void realmSet$video(RealmList realmList) {
        this.video = realmList;
    }

    public void realmSet$videoCover(RealmList realmList) {
        this.videoCover = realmList;
    }

    public MediaBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String str) {
        realmSet$id(str);
    }

    public String getPlaceid() {
        return realmGet$placeid();
    }

    public void setPlaceid(String str) {
        realmSet$placeid(str);
    }

    public String getKind() {
        return realmGet$kind();
    }

    public void setKind(String str) {
        realmSet$kind(str);
    }

    public String getType() {
        return realmGet$type();
    }

    public void setType(String str) {
        realmSet$type(str);
    }

    public String getTitle() {
        return realmGet$title();
    }

    public void setTitle(String str) {
        realmSet$title(str);
    }

    public String getText() {
        return realmGet$text();
    }

    public void setText(String str) {
        realmSet$text(str);
    }

    public String getBroadcast() {
        return realmGet$broadcast();
    }

    public void setBroadcast(String str) {
        realmSet$broadcast(str);
    }

    public RealmList<Resource> getPicture() {
        return realmGet$picture();
    }

    public void setPicture(RealmList<Resource> realmList) {
        realmSet$picture(realmList);
    }

    public RealmList<Resource> getAudio() {
        return realmGet$audio();
    }

    public void setAudio(RealmList<Resource> realmList) {
        realmSet$audio(realmList);
    }

    public RealmList<Resource> getVideo() {
        return realmGet$video();
    }

    public void setVideo(RealmList<Resource> realmList) {
        realmSet$video(realmList);
    }

    public RealmList<Resource> getVideoCover() {
        return realmGet$videoCover();
    }

    public void setVideoCover(RealmList<Resource> realmList) {
        realmSet$videoCover(realmList);
    }
}
