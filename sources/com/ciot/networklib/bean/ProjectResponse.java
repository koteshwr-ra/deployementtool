package com.ciot.networklib.bean;

import com.ciot.realm.db.common.Contact;
import com.ciot.realm.db.common.LocationBean;

public class ProjectResponse {
    private String account;
    private String address;
    private String area;
    private String city;
    private String company;
    private Contact contact;
    private int createtime;
    private String description;
    private String district;
    private String id;
    private LocationBean loc;
    private String name;
    private String province;
    private String robot_account;
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public int getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(int i) {
        this.createtime = i;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String str) {
        this.district = str;
    }

    public LocationBean getLoc() {
        return this.loc;
    }

    public void setLoc(LocationBean locationBean) {
        this.loc = locationBean;
    }

    public Contact getContact() {
        return this.contact;
    }

    public void setContact(Contact contact2) {
        this.contact = contact2;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String str) {
        this.account = str;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String str) {
        this.area = str;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String str) {
        this.company = str;
    }

    public String getRobot_account() {
        return this.robot_account;
    }

    public void setRobot_account(String str) {
        this.robot_account = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String toString() {
        return "ProjectResponse{name='" + this.name + '\'' + ", address='" + this.address + '\'' + ", createtime=" + this.createtime + ", province='" + this.province + '\'' + ", city='" + this.city + '\'' + ", district='" + this.district + '\'' + ", loc=" + this.loc + ", contact=" + this.contact + ", description='" + this.description + '\'' + ", account='" + this.account + '\'' + ", area='" + this.area + '\'' + ", company='" + this.company + '\'' + ", robot_account='" + this.robot_account + '\'' + ", id='" + this.id + '\'' + '}';
    }
}
