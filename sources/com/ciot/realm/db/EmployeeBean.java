package com.ciot.realm.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_EmployeeBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;
import java.util.Arrays;

public class EmployeeBean extends RealmObject implements Comparable<EmployeeBean>, Serializable, com_ciot_realm_db_EmployeeBeanRealmProxyInterface {
    private static final long serialVersionUID = -3514055561447846909L;
    private String arccode;
    private byte[] arccodebytes;
    private String birthday;
    private String card;
    private String company;
    private String companyId;
    private String companyName;
    private String department;
    private String description;
    private String email;
    private String face;
    private String fingerprint;
    private String greetings;
    private String iccard;
    @PrimaryKey
    private String id;
    private String idcard;
    private String idcardcode;
    private String job;
    private String name;
    private String phone;
    private String senscode;
    private String sex;
    private String staffno;
    private String wechat;

    public String realmGet$arccode() {
        return this.arccode;
    }

    public byte[] realmGet$arccodebytes() {
        return this.arccodebytes;
    }

    public String realmGet$birthday() {
        return this.birthday;
    }

    public String realmGet$card() {
        return this.card;
    }

    public String realmGet$company() {
        return this.company;
    }

    public String realmGet$companyId() {
        return this.companyId;
    }

    public String realmGet$companyName() {
        return this.companyName;
    }

    public String realmGet$department() {
        return this.department;
    }

    public String realmGet$description() {
        return this.description;
    }

    public String realmGet$email() {
        return this.email;
    }

    public String realmGet$face() {
        return this.face;
    }

    public String realmGet$fingerprint() {
        return this.fingerprint;
    }

    public String realmGet$greetings() {
        return this.greetings;
    }

    public String realmGet$iccard() {
        return this.iccard;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$idcard() {
        return this.idcard;
    }

    public String realmGet$idcardcode() {
        return this.idcardcode;
    }

    public String realmGet$job() {
        return this.job;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$phone() {
        return this.phone;
    }

    public String realmGet$senscode() {
        return this.senscode;
    }

    public String realmGet$sex() {
        return this.sex;
    }

    public String realmGet$staffno() {
        return this.staffno;
    }

    public String realmGet$wechat() {
        return this.wechat;
    }

    public void realmSet$arccode(String str) {
        this.arccode = str;
    }

    public void realmSet$arccodebytes(byte[] bArr) {
        this.arccodebytes = bArr;
    }

    public void realmSet$birthday(String str) {
        this.birthday = str;
    }

    public void realmSet$card(String str) {
        this.card = str;
    }

    public void realmSet$company(String str) {
        this.company = str;
    }

    public void realmSet$companyId(String str) {
        this.companyId = str;
    }

    public void realmSet$companyName(String str) {
        this.companyName = str;
    }

    public void realmSet$department(String str) {
        this.department = str;
    }

    public void realmSet$description(String str) {
        this.description = str;
    }

    public void realmSet$email(String str) {
        this.email = str;
    }

    public void realmSet$face(String str) {
        this.face = str;
    }

    public void realmSet$fingerprint(String str) {
        this.fingerprint = str;
    }

    public void realmSet$greetings(String str) {
        this.greetings = str;
    }

    public void realmSet$iccard(String str) {
        this.iccard = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$idcard(String str) {
        this.idcard = str;
    }

    public void realmSet$idcardcode(String str) {
        this.idcardcode = str;
    }

    public void realmSet$job(String str) {
        this.job = str;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$phone(String str) {
        this.phone = str;
    }

    public void realmSet$senscode(String str) {
        this.senscode = str;
    }

    public void realmSet$sex(String str) {
        this.sex = str;
    }

    public void realmSet$staffno(String str) {
        this.staffno = str;
    }

    public void realmSet$wechat(String str) {
        this.wechat = str;
    }

    public EmployeeBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public byte[] getArccodebytes() {
        return realmGet$arccodebytes();
    }

    public void setArccodebytes(byte[] bArr) {
        realmSet$arccodebytes(bArr);
    }

    public String getIdcardcode() {
        return realmGet$idcardcode();
    }

    public void setIdcardcode(String str) {
        realmSet$idcardcode(str);
    }

    public String getIccard() {
        return realmGet$iccard();
    }

    public void setIccard(String str) {
        realmSet$iccard(str);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String str) {
        realmSet$name(str);
    }

    public String getCard() {
        return realmGet$card();
    }

    public void setCard(String str) {
        realmSet$card(str);
    }

    public String getIdcard() {
        return realmGet$idcard();
    }

    public void setIdcard(String str) {
        realmSet$idcard(str);
    }

    public String getFace() {
        return realmGet$face();
    }

    public void setFace(String str) {
        realmSet$face(str);
    }

    public String getFingerprint() {
        return realmGet$fingerprint();
    }

    public void setFingerprint(String str) {
        realmSet$fingerprint(str);
    }

    public String getPhone() {
        return realmGet$phone();
    }

    public void setPhone(String str) {
        realmSet$phone(str);
    }

    public String getEmail() {
        return realmGet$email();
    }

    public void setEmail(String str) {
        realmSet$email(str);
    }

    public String getSex() {
        return realmGet$sex();
    }

    public void setSex(String str) {
        realmSet$sex(str);
    }

    public String getDepartment() {
        return realmGet$department();
    }

    public void setDepartment(String str) {
        realmSet$department(str);
    }

    public String getCompany() {
        return realmGet$company();
    }

    public void setCompany(String str) {
        realmSet$company(str);
    }

    public String getJob() {
        return realmGet$job();
    }

    public void setJob(String str) {
        realmSet$job(str);
    }

    public String getArccode() {
        return realmGet$arccode();
    }

    public void setArccode(String str) {
        realmSet$arccode(str);
    }

    public String getSenscode() {
        return realmGet$senscode();
    }

    public void setSenscode(String str) {
        realmSet$senscode(str);
    }

    public String getWechat() {
        return realmGet$wechat();
    }

    public void setWechat(String str) {
        realmSet$wechat(str);
    }

    public String getDescription() {
        return realmGet$description();
    }

    public void setDescription(String str) {
        realmSet$description(str);
    }

    public String getBirthday() {
        return realmGet$birthday();
    }

    public void setBirthday(String str) {
        realmSet$birthday(str);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String str) {
        realmSet$id(str);
    }

    public String getCompanyId() {
        return realmGet$companyId();
    }

    public void setCompanyId(String str) {
        realmSet$companyId(str);
    }

    public String getCompanyName() {
        return realmGet$companyName();
    }

    public void setCompanyName(String str) {
        realmSet$companyName(str);
    }

    public boolean equals(Object obj) {
        if (obj instanceof EmployeeBean) {
            String id2 = ((EmployeeBean) obj).getId();
            if (realmGet$id() != null && realmGet$id().equals(id2)) {
                return true;
            }
        }
        return super.equals(obj);
    }

    public int compareTo(EmployeeBean employeeBean) {
        return realmGet$id().compareTo(employeeBean.getId());
    }

    public String getStaffno() {
        return realmGet$staffno();
    }

    public void setStaffno(String str) {
        realmSet$staffno(str);
    }

    public String getGreetings() {
        return realmGet$greetings();
    }

    public void setGreetings(String str) {
        realmSet$greetings(str);
    }

    public String toString() {
        return "EmployeeBean{id='" + realmGet$id() + '\'' + ", name='" + realmGet$name() + '\'' + ", card='" + realmGet$card() + '\'' + ", idcard='" + realmGet$idcard() + '\'' + ", face='" + realmGet$face() + '\'' + ", fingerprint='" + realmGet$fingerprint() + '\'' + ", phone='" + realmGet$phone() + '\'' + ", email='" + realmGet$email() + '\'' + ", sex='" + realmGet$sex() + '\'' + ", department='" + realmGet$department() + '\'' + ", company='" + realmGet$company() + '\'' + ", job='" + realmGet$job() + '\'' + ", arccode='" + realmGet$arccode() + '\'' + ", arccodebytes=" + Arrays.toString(realmGet$arccodebytes()) + ", staffno='" + realmGet$staffno() + '\'' + ", greetings='" + realmGet$greetings() + '\'' + ", senscode='" + realmGet$senscode() + '\'' + ", wechat='" + realmGet$wechat() + '\'' + ", description='" + realmGet$description() + '\'' + ", birthday='" + realmGet$birthday() + '\'' + ", companyId='" + realmGet$companyId() + '\'' + ", companyName='" + realmGet$companyName() + '\'' + ", idcardcode='" + realmGet$idcardcode() + '\'' + ", iccard='" + realmGet$iccard() + '\'' + '}';
    }
}
