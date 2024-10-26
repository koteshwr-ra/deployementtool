package com.ciot.realm.db;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_PersonInfoBeanRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.io.Serializable;

public class PersonInfoBean extends RealmObject implements Comparable<PersonInfoBean>, Serializable, com_ciot_realm_db_PersonInfoBeanRealmProxyInterface {
    public static final int EMPLOYEE = 1;
    public static final int STRANGER = 2;
    public static final int VISITOR = 0;
    private static final long serialVersionUID = -3514055561447846909L;
    private byte[] arcBytes;
    private String companyName;
    private String companyNameString;
    public int faceId;
    private String greetings;
    private String iccard;
    public String id;
    private String idcard;
    public boolean isVip;
    @Ignore
    private long lastGreetingTime;
    private String name;
    private String nickName;
    public String sex;
    private String staffno;
    private int type;
    @PrimaryKey
    String uniqueId;

    public byte[] realmGet$arcBytes() {
        return this.arcBytes;
    }

    public String realmGet$companyName() {
        return this.companyName;
    }

    public String realmGet$companyNameString() {
        return this.companyNameString;
    }

    public int realmGet$faceId() {
        return this.faceId;
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

    public boolean realmGet$isVip() {
        return this.isVip;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$nickName() {
        return this.nickName;
    }

    public String realmGet$sex() {
        return this.sex;
    }

    public String realmGet$staffno() {
        return this.staffno;
    }

    public int realmGet$type() {
        return this.type;
    }

    public String realmGet$uniqueId() {
        return this.uniqueId;
    }

    public void realmSet$arcBytes(byte[] bArr) {
        this.arcBytes = bArr;
    }

    public void realmSet$companyName(String str) {
        this.companyName = str;
    }

    public void realmSet$companyNameString(String str) {
        this.companyNameString = str;
    }

    public void realmSet$faceId(int i) {
        this.faceId = i;
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

    public void realmSet$isVip(boolean z) {
        this.isVip = z;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$nickName(String str) {
        this.nickName = str;
    }

    public void realmSet$sex(String str) {
        this.sex = str;
    }

    public void realmSet$staffno(String str) {
        this.staffno = str;
    }

    public void realmSet$type(int i) {
        this.type = i;
    }

    public void realmSet$uniqueId(String str) {
        this.uniqueId = str;
    }

    public PersonInfoBean() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$type(2);
        realmSet$companyName("");
    }

    public String getIccard() {
        return realmGet$iccard();
    }

    public void setIccard(String str) {
        realmSet$iccard(str);
    }

    public String getCompanyName() {
        return realmGet$companyName();
    }

    public void setCompanyName(String str) {
        realmSet$companyName(str);
    }

    public String getCompanyNameString() {
        return realmGet$companyNameString();
    }

    public void setCompanyNameString(String str) {
        realmSet$companyNameString(str);
    }

    public String getUniqueId() {
        return realmGet$uniqueId();
    }

    public void setUniqueId(String str) {
        realmSet$uniqueId(str);
    }

    public long getLastGreetingTime() {
        return this.lastGreetingTime;
    }

    public void setLastGreetingTime(long j) {
        this.lastGreetingTime = j;
    }

    public byte[] getArcBytes() {
        return realmGet$arcBytes();
    }

    public void setArcBytes(byte[] bArr) {
        realmSet$arcBytes(bArr);
    }

    public String getGreetings() {
        return realmGet$greetings();
    }

    public void setGreetings(String str) {
        realmSet$greetings(str);
    }

    public int getType() {
        return realmGet$type();
    }

    public void setType(int i) {
        realmSet$type(i);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String str) {
        realmSet$name(str);
    }

    public String getNickName() {
        return realmGet$nickName();
    }

    public void setNikeName(String str) {
        realmSet$nickName(str);
    }

    public boolean equals(Object obj) {
        if (obj instanceof PersonInfoBean) {
            String realmGet$uniqueId = ((PersonInfoBean) obj).realmGet$uniqueId();
            if (realmGet$uniqueId() != null && realmGet$uniqueId().equals(realmGet$uniqueId)) {
                return true;
            }
        }
        return super.equals(obj);
    }

    public String getIdcard() {
        return realmGet$idcard();
    }

    public void setIdcard(String str) {
        realmSet$idcard(str);
    }

    public int compareTo(PersonInfoBean personInfoBean) {
        return personInfoBean.realmGet$type() - realmGet$type();
    }

    public String getStaffno() {
        return realmGet$staffno();
    }

    public void setStaffno(String str) {
        realmSet$staffno(str);
    }

    public String toString() {
        return "PersonInfoBean{uniqueId='" + realmGet$uniqueId() + '\'' + ", lastGreetingTime=" + this.lastGreetingTime + ", name='" + realmGet$name() + '\'' + ", greetings='" + realmGet$greetings() + '\'' + ", type=" + realmGet$type() + ", isVip=" + realmGet$isVip() + ", sex='" + realmGet$sex() + '\'' + ", idcard='" + realmGet$idcard() + '\'' + ", faceId=" + realmGet$faceId() + ", iccard='" + realmGet$iccard() + '\'' + ", id='" + realmGet$id() + '\'' + '}';
    }
}
