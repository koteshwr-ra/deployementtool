package com.ciot.realm.db.ad;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_ad_AdvertisementsBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class AdvertisementsBean extends RealmObject implements com_ciot_realm_db_ad_AdvertisementsBeanRealmProxyInterface {
    private int begin;
    private int createtime;
    private String description;
    private int end;
    private RealmList<HorseRaceLampsBean> horseRaceLamps;
    private String id;
    private String kind;
    private String name;
    private RealmList<ResourcesBean> resources;

    public int realmGet$begin() {
        return this.begin;
    }

    public int realmGet$createtime() {
        return this.createtime;
    }

    public String realmGet$description() {
        return this.description;
    }

    public int realmGet$end() {
        return this.end;
    }

    public RealmList realmGet$horseRaceLamps() {
        return this.horseRaceLamps;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$kind() {
        return this.kind;
    }

    public String realmGet$name() {
        return this.name;
    }

    public RealmList realmGet$resources() {
        return this.resources;
    }

    public void realmSet$begin(int i) {
        this.begin = i;
    }

    public void realmSet$createtime(int i) {
        this.createtime = i;
    }

    public void realmSet$description(String str) {
        this.description = str;
    }

    public void realmSet$end(int i) {
        this.end = i;
    }

    public void realmSet$horseRaceLamps(RealmList realmList) {
        this.horseRaceLamps = realmList;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$kind(String str) {
        this.kind = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$resources(RealmList realmList) {
        this.resources = realmList;
    }

    public AdvertisementsBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String str) {
        realmSet$name(str);
    }

    public String getKind() {
        return realmGet$kind();
    }

    public void setKind(String str) {
        realmSet$kind(str);
    }

    public int getBegin() {
        return realmGet$begin();
    }

    public void setBegin(int i) {
        realmSet$begin(i);
    }

    public int getEnd() {
        return realmGet$end();
    }

    public void setEnd(int i) {
        realmSet$end(i);
    }

    public String getDescription() {
        return realmGet$description();
    }

    public void setDescription(String str) {
        realmSet$description(str);
    }

    public int getCreatetime() {
        return realmGet$createtime();
    }

    public void setCreatetime(int i) {
        realmSet$createtime(i);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String str) {
        realmSet$id(str);
    }

    public RealmList<ResourcesBean> getResources() {
        return realmGet$resources();
    }

    public void setResources(RealmList<ResourcesBean> realmList) {
        realmSet$resources(realmList);
    }

    public RealmList<HorseRaceLampsBean> getHorseRaceLamps() {
        return realmGet$horseRaceLamps();
    }

    public void setHorseRaceLamps(RealmList<HorseRaceLampsBean> realmList) {
        realmSet$horseRaceLamps(realmList);
    }
}
