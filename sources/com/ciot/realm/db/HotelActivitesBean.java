package com.ciot.realm.db;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.com_ciot_realm_db_HotelActivitesBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class HotelActivitesBean extends RealmObject implements com_ciot_realm_db_HotelActivitesBeanRealmProxyInterface {
    private String address;
    private long beginDate;
    private String description;
    private long endDate;
    private boolean hotspot;
    private String id;
    private String imgUrl;
    private boolean isChecked;
    private String name;
    private String peopleNumber;
    private String price;
    private RealmList<TimeBean> times;

    public String realmGet$address() {
        return this.address;
    }

    public long realmGet$beginDate() {
        return this.beginDate;
    }

    public String realmGet$description() {
        return this.description;
    }

    public long realmGet$endDate() {
        return this.endDate;
    }

    public boolean realmGet$hotspot() {
        return this.hotspot;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$imgUrl() {
        return this.imgUrl;
    }

    public boolean realmGet$isChecked() {
        return this.isChecked;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$peopleNumber() {
        return this.peopleNumber;
    }

    public String realmGet$price() {
        return this.price;
    }

    public RealmList realmGet$times() {
        return this.times;
    }

    public void realmSet$address(String str) {
        this.address = str;
    }

    public void realmSet$beginDate(long j) {
        this.beginDate = j;
    }

    public void realmSet$description(String str) {
        this.description = str;
    }

    public void realmSet$endDate(long j) {
        this.endDate = j;
    }

    public void realmSet$hotspot(boolean z) {
        this.hotspot = z;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$imgUrl(String str) {
        this.imgUrl = str;
    }

    public void realmSet$isChecked(boolean z) {
        this.isChecked = z;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$peopleNumber(String str) {
        this.peopleNumber = str;
    }

    public void realmSet$price(String str) {
        this.price = str;
    }

    public void realmSet$times(RealmList realmList) {
        this.times = realmList;
    }

    public HotelActivitesBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public boolean isChecked() {
        return realmGet$isChecked();
    }

    public void setChecked(boolean z) {
        realmSet$isChecked(z);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String str) {
        realmSet$id(str);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String str) {
        realmSet$name(str);
    }

    public String getImgUlr() {
        return realmGet$imgUrl();
    }

    public void setImgUlr(String str) {
        realmSet$imgUrl(str);
    }

    public long getBeginDate() {
        return realmGet$beginDate();
    }

    public void setBeginDate(long j) {
        realmSet$beginDate(j);
    }

    public long getEndDate() {
        return realmGet$endDate();
    }

    public void setEndDate(long j) {
        realmSet$endDate(j);
    }

    public String getAddress() {
        return realmGet$address();
    }

    public void setAddress(String str) {
        realmSet$address(str);
    }

    public String getPeopleNumber() {
        return realmGet$peopleNumber();
    }

    public void setPeopleNumber(String str) {
        realmSet$peopleNumber(str);
    }

    public boolean isHotspot() {
        return realmGet$hotspot();
    }

    public void setHotspot(boolean z) {
        realmSet$hotspot(z);
    }

    public String getPrice() {
        if (realmGet$price() == null || realmGet$price().contains("元")) {
            return realmGet$price();
        }
        return realmGet$price() + "元";
    }

    public void setPrice(String str) {
        realmSet$price(str);
    }

    public String getDescription() {
        return realmGet$description();
    }

    public void setDescription(String str) {
        realmSet$description(str);
    }

    public RealmList<TimeBean> getTime() {
        return realmGet$times();
    }

    public void setTime(RealmList<TimeBean> realmList) {
        realmSet$times(realmList);
    }
}
