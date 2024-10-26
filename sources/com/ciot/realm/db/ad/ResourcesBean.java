package com.ciot.realm.db.ad;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.com_ciot_realm_db_ad_ResourcesBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.util.List;
import java.util.Map;

public class ResourcesBean extends RealmObject implements com_ciot_realm_db_ad_ResourcesBeanRealmProxyInterface {
    private String adList;
    private int adListType;
    private String color;
    private String content;
    private int createtime;
    private String description;
    private String direction;
    @Ignore
    private List<Map<String, String>> downLoadList;
    private int duration;
    @Ignore
    private String filePath;
    private String font;
    private String id;
    private String isUsed;
    private String kind;
    private String name;
    private String pattern;
    private String position;
    private String qrLocation;
    private String qrUrl;
    private String resolution;
    private String resourceId;
    @Ignore
    private String sdcardCoverUrl;
    private String size;
    private int srceen;
    private String style;
    private String videoCoverUrl;

    public String realmGet$adList() {
        return this.adList;
    }

    public int realmGet$adListType() {
        return this.adListType;
    }

    public String realmGet$color() {
        return this.color;
    }

    public String realmGet$content() {
        return this.content;
    }

    public int realmGet$createtime() {
        return this.createtime;
    }

    public String realmGet$description() {
        return this.description;
    }

    public String realmGet$direction() {
        return this.direction;
    }

    public int realmGet$duration() {
        return this.duration;
    }

    public String realmGet$font() {
        return this.font;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$isUsed() {
        return this.isUsed;
    }

    public String realmGet$kind() {
        return this.kind;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$pattern() {
        return this.pattern;
    }

    public String realmGet$position() {
        return this.position;
    }

    public String realmGet$qrLocation() {
        return this.qrLocation;
    }

    public String realmGet$qrUrl() {
        return this.qrUrl;
    }

    public String realmGet$resolution() {
        return this.resolution;
    }

    public String realmGet$resourceId() {
        return this.resourceId;
    }

    public String realmGet$size() {
        return this.size;
    }

    public int realmGet$srceen() {
        return this.srceen;
    }

    public String realmGet$style() {
        return this.style;
    }

    public String realmGet$videoCoverUrl() {
        return this.videoCoverUrl;
    }

    public void realmSet$adList(String str) {
        this.adList = str;
    }

    public void realmSet$adListType(int i) {
        this.adListType = i;
    }

    public void realmSet$color(String str) {
        this.color = str;
    }

    public void realmSet$content(String str) {
        this.content = str;
    }

    public void realmSet$createtime(int i) {
        this.createtime = i;
    }

    public void realmSet$description(String str) {
        this.description = str;
    }

    public void realmSet$direction(String str) {
        this.direction = str;
    }

    public void realmSet$duration(int i) {
        this.duration = i;
    }

    public void realmSet$font(String str) {
        this.font = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$isUsed(String str) {
        this.isUsed = str;
    }

    public void realmSet$kind(String str) {
        this.kind = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$pattern(String str) {
        this.pattern = str;
    }

    public void realmSet$position(String str) {
        this.position = str;
    }

    public void realmSet$qrLocation(String str) {
        this.qrLocation = str;
    }

    public void realmSet$qrUrl(String str) {
        this.qrUrl = str;
    }

    public void realmSet$resolution(String str) {
        this.resolution = str;
    }

    public void realmSet$resourceId(String str) {
        this.resourceId = str;
    }

    public void realmSet$size(String str) {
        this.size = str;
    }

    public void realmSet$srceen(int i) {
        this.srceen = i;
    }

    public void realmSet$style(String str) {
        this.style = str;
    }

    public void realmSet$videoCoverUrl(String str) {
        this.videoCoverUrl = str;
    }

    public ResourcesBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getFont() {
        return realmGet$font();
    }

    public void setFont(String str) {
        realmSet$font(str);
    }

    public String getSize() {
        return realmGet$size();
    }

    public void setSize(String str) {
        realmSet$size(str);
    }

    public String getColor() {
        return realmGet$color();
    }

    public void setColor(String str) {
        realmSet$color(str);
    }

    public String getPattern() {
        return realmGet$pattern();
    }

    public void setPattern(String str) {
        realmSet$pattern(str);
    }

    public String getResolution() {
        return realmGet$resolution();
    }

    public void setResolution(String str) {
        realmSet$resolution(str);
    }

    public String getPosition() {
        return realmGet$position();
    }

    public void setPosition(String str) {
        realmSet$position(str);
    }

    public String getStyle() {
        return realmGet$style();
    }

    public void setStyle(String str) {
        realmSet$style(str);
    }

    public String getDirection() {
        return realmGet$direction();
    }

    public void setDirection(String str) {
        realmSet$direction(str);
    }

    public String getContent() {
        return realmGet$content();
    }

    public void setContent(String str) {
        realmSet$content(str);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String str) {
        realmSet$name(str);
    }

    public String getResourceId() {
        return realmGet$resourceId();
    }

    public void setResourceId(String str) {
        realmSet$resourceId(str);
    }

    public String getKind() {
        return realmGet$kind();
    }

    public void setKind(String str) {
        realmSet$kind(str);
    }

    public String getIsUsed() {
        return realmGet$isUsed();
    }

    public void setIsUsed(String str) {
        realmSet$isUsed(str);
    }

    public int getCreatetime() {
        return realmGet$createtime();
    }

    public void setCreatetime(int i) {
        realmSet$createtime(i);
    }

    public String getDescription() {
        return realmGet$description();
    }

    public void setDescription(String str) {
        realmSet$description(str);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String str) {
        realmSet$id(str);
    }

    public int getSrceen() {
        return realmGet$srceen();
    }

    public void setSrceen(int i) {
        realmSet$srceen(i);
    }

    public String getQrLocation() {
        return realmGet$qrLocation();
    }

    public void setQrLocation(String str) {
        realmSet$qrLocation(str);
    }

    public String getQrUrl() {
        return realmGet$qrUrl();
    }

    public void setQrUrl(String str) {
        realmSet$qrUrl(str);
    }

    public int getDuration() {
        return realmGet$duration();
    }

    public void setDuration(int i) {
        realmSet$duration(i);
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public String getAdList() {
        return realmGet$adList();
    }

    public void setAdList(String str) {
        realmSet$adList(str);
    }

    public int getAdListType() {
        return realmGet$adListType();
    }

    public void setAdListType(int i) {
        realmSet$adListType(i);
    }

    public String getVideoCoverUrl() {
        return realmGet$videoCoverUrl();
    }

    public void setVideoCoverUrl(String str) {
        realmSet$videoCoverUrl(str);
    }

    public String getSdcardCoverUrl() {
        return this.sdcardCoverUrl;
    }

    public void setSdcardCoverUrl(String str) {
        this.sdcardCoverUrl = str;
    }

    public List<Map<String, String>> getDownLoadList() {
        return this.downLoadList;
    }

    public void setDownLoadList(List<Map<String, String>> list) {
        this.downLoadList = list;
    }

    public String toString() {
        return "ResourcesBean{font='" + realmGet$font() + '\'' + ", size='" + realmGet$size() + '\'' + ", color='" + realmGet$color() + '\'' + ", pattern='" + realmGet$pattern() + '\'' + ", resolution='" + realmGet$resolution() + '\'' + ", position='" + realmGet$position() + '\'' + ", style='" + realmGet$style() + '\'' + ", direction='" + realmGet$direction() + '\'' + ", content='" + realmGet$content() + '\'' + ", name='" + realmGet$name() + '\'' + ", resourceId='" + realmGet$resourceId() + '\'' + ", kind='" + realmGet$kind() + '\'' + ", isUsed='" + realmGet$isUsed() + '\'' + ", createtime=" + realmGet$createtime() + ", description='" + realmGet$description() + '\'' + ", id='" + realmGet$id() + '\'' + ", srceen=" + realmGet$srceen() + ", qrLocation='" + realmGet$qrLocation() + '\'' + ", qrUrl='" + realmGet$qrUrl() + '\'' + ", duration=" + realmGet$duration() + ", filePath='" + this.filePath + '\'' + ", adList='" + realmGet$adList() + '\'' + ", adListType=" + realmGet$adListType() + ", videoCoverUrl='" + realmGet$videoCoverUrl() + '\'' + ", sdcardCoverUrl='" + this.sdcardCoverUrl + '\'' + ", downLoadList=" + this.downLoadList + '}';
    }
}
