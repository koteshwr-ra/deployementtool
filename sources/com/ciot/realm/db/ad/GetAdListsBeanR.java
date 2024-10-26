package com.ciot.realm.db.ad;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.util.List;
import java.util.Objects;

public class GetAdListsBeanR extends RealmObject implements com_ciot_realm_db_ad_GetAdListsBeanRRealmProxyInterface {
    private RealmList<AdvertisementsBean> advertisements;
    private long createtime;
    private CycleBean cycle;
    private String description;
    @PrimaryKey
    private String id;
    private String name;
    private String playingMode;
    private int screen;
    private RealmList<TimesBean> times;

    public RealmList realmGet$advertisements() {
        return this.advertisements;
    }

    public long realmGet$createtime() {
        return this.createtime;
    }

    public CycleBean realmGet$cycle() {
        return this.cycle;
    }

    public String realmGet$description() {
        return this.description;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$playingMode() {
        return this.playingMode;
    }

    public int realmGet$screen() {
        return this.screen;
    }

    public RealmList realmGet$times() {
        return this.times;
    }

    public void realmSet$advertisements(RealmList realmList) {
        this.advertisements = realmList;
    }

    public void realmSet$createtime(long j) {
        this.createtime = j;
    }

    public void realmSet$cycle(CycleBean cycleBean) {
        this.cycle = cycleBean;
    }

    public void realmSet$description(String str) {
        this.description = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$playingMode(String str) {
        this.playingMode = str;
    }

    public void realmSet$screen(int i) {
        this.screen = i;
    }

    public void realmSet$times(RealmList realmList) {
        this.times = realmList;
    }

    public GetAdListsBeanR() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$screen(0);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String str) {
        realmSet$name(str);
    }

    public CycleBean getCycle() {
        return realmGet$cycle();
    }

    public void setCycle(CycleBean cycleBean) {
        realmSet$cycle(cycleBean);
    }

    public String getPlayingMode() {
        return realmGet$playingMode();
    }

    public void setPlayingMode(String str) {
        realmSet$playingMode(str);
    }

    public long getCreateTime() {
        return realmGet$createtime();
    }

    public void setCreateTime(long j) {
        realmSet$createtime(j);
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

    public List<TimesBean> getTimes() {
        return realmGet$times();
    }

    public void setTimes(RealmList<TimesBean> realmList) {
        realmSet$times(realmList);
    }

    public RealmList<AdvertisementsBean> getAdvertisements() {
        return realmGet$advertisements();
    }

    public void setAdvertisements(RealmList<AdvertisementsBean> realmList) {
        realmSet$advertisements(realmList);
    }

    public int getScreen() {
        return realmGet$screen();
    }

    public void setScreen(int i) {
        realmSet$screen(i);
    }

    public String toString() {
        return "GetAdListsBeanR{name='" + realmGet$name() + '\'' + ", cycle=" + realmGet$cycle() + ", playingMode='" + realmGet$playingMode() + '\'' + ", createtime=" + realmGet$createtime() + ", description='" + realmGet$description() + '\'' + ", id='" + realmGet$id() + '\'' + ", times=" + realmGet$times() + ", advertisements=" + realmGet$advertisements() + ", screen =" + realmGet$screen() + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return realmGet$id().equals(((GetAdListsBeanR) obj).realmGet$id());
    }

    public int hashCode() {
        return Objects.hash(new Object[]{realmGet$id()});
    }
}
