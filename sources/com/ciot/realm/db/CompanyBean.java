package com.ciot.realm.db;

import java.io.Serializable;
import java.util.List;

public class CompanyBean implements Comparable<CompanyBean>, Serializable {
    private static final long serialVersionUID = -7174648984192675741L;
    private List<String> accesslist;
    private String address;
    private String area;
    private String concatPhone;
    private String contact;
    private String description;
    private String employeeId;
    private String employeeName;
    private String id;
    private String isHumanIdentityCard;
    private String iscall;
    private String lockagetype;
    private String name;
    private String phone;
    private String process;
    private String qrcode;

    public String getQrcode() {
        return this.qrcode;
    }

    public void setQrcode(String str) {
        this.qrcode = str;
    }

    public String toString() {
        return "CompanyBean{id='" + this.id + '\'' + ", name='" + this.name + '\'' + ", contact='" + this.contact + '\'' + ", concatPhone='" + this.concatPhone + '\'' + ", phone='" + this.phone + '\'' + ", area='" + this.area + '\'' + ", address='" + this.address + '\'' + ", process='" + this.process + '\'' + ", description='" + this.description + '\'' + ", employeeId='" + this.employeeId + '\'' + ", employeeName='" + this.employeeName + '\'' + ", accesslist=" + this.accesslist + ", lockagetype='" + this.lockagetype + '\'' + ", qrcode='" + this.qrcode + '\'' + '}';
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String str) {
        this.contact = str;
    }

    public String getConcatPhone() {
        return this.concatPhone;
    }

    public void setConcatPhone(String str) {
        this.concatPhone = str;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String str) {
        this.area = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getProcess() {
        return this.process;
    }

    public void setProcess(String str) {
        this.process = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(String str) {
        this.employeeId = str;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public void setEmployeeName(String str) {
        this.employeeName = str;
    }

    public List<String> getAccesslist() {
        return this.accesslist;
    }

    public void setAccesslist(List<String> list) {
        this.accesslist = list;
    }

    public int compareTo(CompanyBean companyBean) {
        return this.id.compareTo(companyBean.getId());
    }

    public boolean equals(Object obj) {
        if (obj instanceof CompanyBean) {
            String id2 = ((CompanyBean) obj).getId();
            String str = this.id;
            if (str != null && str.equals(id2)) {
                return true;
            }
        }
        return super.equals(obj);
    }

    public String getLockagetype() {
        return this.lockagetype;
    }

    public void setLockagetype(String str) {
        this.lockagetype = str;
    }

    public String getIsHumanIdentityCard() {
        return this.isHumanIdentityCard;
    }

    public void setIsHumanIdentityCard(String str) {
        this.isHumanIdentityCard = str;
    }

    public String getIscall() {
        return this.iscall;
    }

    public void setIscall(String str) {
        this.iscall = str;
    }
}
