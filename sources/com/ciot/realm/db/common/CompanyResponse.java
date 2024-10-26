package com.ciot.realm.db.common;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_common_CompanyResponseRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class CompanyResponse extends RealmObject implements com_ciot_realm_db_common_CompanyResponseRealmProxyInterface {
    private String address;
    private String area;
    private Contact contact;
    private String description;
    @PrimaryKey
    private String id;
    private LocationBean location;
    private String name;
    private String pinyin;
    private String project;
    private String qrcode;
    private double x;
    private double y;

    public String realmGet$address() {
        return this.address;
    }

    public String realmGet$area() {
        return this.area;
    }

    public Contact realmGet$contact() {
        return this.contact;
    }

    public String realmGet$description() {
        return this.description;
    }

    public String realmGet$id() {
        return this.id;
    }

    public LocationBean realmGet$location() {
        return this.location;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$pinyin() {
        return this.pinyin;
    }

    public String realmGet$project() {
        return this.project;
    }

    public String realmGet$qrcode() {
        return this.qrcode;
    }

    public double realmGet$x() {
        return this.x;
    }

    public double realmGet$y() {
        return this.y;
    }

    public void realmSet$address(String str) {
        this.address = str;
    }

    public void realmSet$area(String str) {
        this.area = str;
    }

    public void realmSet$contact(Contact contact2) {
        this.contact = contact2;
    }

    public void realmSet$description(String str) {
        this.description = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$location(LocationBean locationBean) {
        this.location = locationBean;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$pinyin(String str) {
        this.pinyin = str;
    }

    public void realmSet$project(String str) {
        this.project = str;
    }

    public void realmSet$qrcode(String str) {
        this.qrcode = str;
    }

    public void realmSet$x(double d) {
        this.x = d;
    }

    public void realmSet$y(double d) {
        this.y = d;
    }

    public CompanyResponse() {
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

    public String getPinyin() {
        return realmGet$pinyin();
    }

    public void setPinyin(String str) {
        realmSet$pinyin(str);
    }

    public Contact getContact() {
        return realmGet$contact();
    }

    public void setContact(Contact contact2) {
        realmSet$contact(contact2);
    }

    public String getArea() {
        return realmGet$area();
    }

    public void setArea(String str) {
        realmSet$area(str);
    }

    public String getAddress() {
        return realmGet$address();
    }

    public void setAddress(String str) {
        realmSet$address(str);
    }

    public String getQrcode() {
        return realmGet$qrcode();
    }

    public void setQrcode(String str) {
        realmSet$qrcode(str);
    }

    public String getProject() {
        return realmGet$project();
    }

    public void setProject(String str) {
        realmSet$project(str);
    }

    public double getX() {
        return realmGet$x();
    }

    public void setX(double d) {
        realmSet$x(d);
    }

    public double getY() {
        return realmGet$y();
    }

    public void setY(double d) {
        realmSet$y(d);
    }

    public LocationBean getLocation() {
        return realmGet$location();
    }

    public void setLocation(LocationBean locationBean) {
        realmSet$location(locationBean);
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

    public String toString() {
        return "CompanyResponse{name='" + realmGet$name() + '\'' + ", pinyin='" + realmGet$pinyin() + '\'' + ", contact=" + realmGet$contact() + ", area='" + realmGet$area() + '\'' + ", address='" + realmGet$address() + '\'' + ", qrcode='" + realmGet$qrcode() + '\'' + ", project='" + realmGet$project() + '\'' + ", x=" + realmGet$x() + ", y=" + realmGet$y() + ", location=" + realmGet$location() + ", description='" + realmGet$description() + '\'' + ", id='" + realmGet$id() + '\'' + '}';
    }
}
