package com.ciot.realm.db.ad;

import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class HorseRaceLampsBean extends RealmObject implements com_ciot_realm_db_ad_HorseRaceLampsBeanRealmProxyInterface {
    String color;
    String content;
    String description;
    String direction;
    String id;
    String isUsed;
    String kind;
    String name;
    String position;
    String resourceId;
    String style;

    public String realmGet$color() {
        return this.color;
    }

    public String realmGet$content() {
        return this.content;
    }

    public String realmGet$description() {
        return this.description;
    }

    public String realmGet$direction() {
        return this.direction;
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

    public String realmGet$position() {
        return this.position;
    }

    public String realmGet$resourceId() {
        return this.resourceId;
    }

    public String realmGet$style() {
        return this.style;
    }

    public void realmSet$color(String str) {
        this.color = str;
    }

    public void realmSet$content(String str) {
        this.content = str;
    }

    public void realmSet$description(String str) {
        this.description = str;
    }

    public void realmSet$direction(String str) {
        this.direction = str;
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

    public void realmSet$position(String str) {
        this.position = str;
    }

    public void realmSet$resourceId(String str) {
        this.resourceId = str;
    }

    public void realmSet$style(String str) {
        this.style = str;
    }

    public HorseRaceLampsBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
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

    public String getColor() {
        return realmGet$color();
    }

    public void setColor(String str) {
        realmSet$color(str);
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
}
