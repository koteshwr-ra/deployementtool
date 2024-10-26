package com.ciot.realm.db.visitor;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_visitor_VisitorResponseRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.util.Objects;

public class VisitorResponse extends RealmObject implements com_ciot_realm_db_visitor_VisitorResponseRealmProxyInterface {
    private static int faceType;
    private int arcexresult;
    private String birthPrompt;
    private byte[] byfea_hr31;
    private String check;
    private String company;
    private long createtime;
    private String description;
    private String email;
    private String face;
    boolean hasfea_st;
    @PrimaryKey
    private String id;
    private String idcard;
    private boolean isHasfea_hr;
    private String name;
    private String path;
    private String phone;
    private String prompt;
    private String sex;
    private String stfea_hr31;
    private String stfea_st;
    private int stresult;
    private int type;
    private Integer uuid;
    private String vipPrompt;
    private String wechat;

    public int realmGet$arcexresult() {
        return this.arcexresult;
    }

    public String realmGet$birthPrompt() {
        return this.birthPrompt;
    }

    public byte[] realmGet$byfea_hr31() {
        return this.byfea_hr31;
    }

    public String realmGet$check() {
        return this.check;
    }

    public String realmGet$company() {
        return this.company;
    }

    public long realmGet$createtime() {
        return this.createtime;
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

    public boolean realmGet$hasfea_st() {
        return this.hasfea_st;
    }

    public String realmGet$id() {
        return this.id;
    }

    public String realmGet$idcard() {
        return this.idcard;
    }

    public boolean realmGet$isHasfea_hr() {
        return this.isHasfea_hr;
    }

    public String realmGet$name() {
        return this.name;
    }

    public String realmGet$path() {
        return this.path;
    }

    public String realmGet$phone() {
        return this.phone;
    }

    public String realmGet$prompt() {
        return this.prompt;
    }

    public String realmGet$sex() {
        return this.sex;
    }

    public String realmGet$stfea_hr31() {
        return this.stfea_hr31;
    }

    public String realmGet$stfea_st() {
        return this.stfea_st;
    }

    public int realmGet$stresult() {
        return this.stresult;
    }

    public int realmGet$type() {
        return this.type;
    }

    public Integer realmGet$uuid() {
        return this.uuid;
    }

    public String realmGet$vipPrompt() {
        return this.vipPrompt;
    }

    public String realmGet$wechat() {
        return this.wechat;
    }

    public void realmSet$arcexresult(int i) {
        this.arcexresult = i;
    }

    public void realmSet$birthPrompt(String str) {
        this.birthPrompt = str;
    }

    public void realmSet$byfea_hr31(byte[] bArr) {
        this.byfea_hr31 = bArr;
    }

    public void realmSet$check(String str) {
        this.check = str;
    }

    public void realmSet$company(String str) {
        this.company = str;
    }

    public void realmSet$createtime(long j) {
        this.createtime = j;
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

    public void realmSet$hasfea_st(boolean z) {
        this.hasfea_st = z;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$idcard(String str) {
        this.idcard = str;
    }

    public void realmSet$isHasfea_hr(boolean z) {
        this.isHasfea_hr = z;
    }

    public void realmSet$name(String str) {
        this.name = str;
    }

    public void realmSet$path(String str) {
        this.path = str;
    }

    public void realmSet$phone(String str) {
        this.phone = str;
    }

    public void realmSet$prompt(String str) {
        this.prompt = str;
    }

    public void realmSet$sex(String str) {
        this.sex = str;
    }

    public void realmSet$stfea_hr31(String str) {
        this.stfea_hr31 = str;
    }

    public void realmSet$stfea_st(String str) {
        this.stfea_st = str;
    }

    public void realmSet$stresult(int i) {
        this.stresult = i;
    }

    public void realmSet$type(int i) {
        this.type = i;
    }

    public void realmSet$uuid(Integer num) {
        this.uuid = num;
    }

    public void realmSet$vipPrompt(String str) {
        this.vipPrompt = str;
    }

    public void realmSet$wechat(String str) {
        this.wechat = str;
    }

    public VisitorResponse() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$isHasfea_hr(false);
    }

    public int getStresult() {
        return realmGet$stresult();
    }

    public void setStresult(int i) {
        realmSet$stresult(i);
    }

    public Integer getUuid() {
        return realmGet$uuid();
    }

    public void setUuid(Integer num) {
        realmSet$uuid(num);
    }

    public String getStfea_st() {
        return realmGet$stfea_st();
    }

    public void setStfea_st(String str) {
        realmSet$stfea_st(str);
    }

    public boolean isHasfea_st() {
        return realmGet$hasfea_st();
    }

    public void setHasfea_st(boolean z) {
        realmSet$hasfea_st(z);
    }

    public boolean isHasfea_hr() {
        return realmGet$isHasfea_hr();
    }

    public void setHasfea_hr(boolean z) {
        realmSet$isHasfea_hr(z);
    }

    public String getStfea_hr31() {
        return realmGet$stfea_hr31();
    }

    public void setStfea_hr31(String str) {
        realmSet$stfea_hr31(str);
    }

    public byte[] getByfea_hr31() {
        return realmGet$byfea_hr31();
    }

    public void setByfea_hr31(byte[] bArr) {
        realmSet$byfea_hr31(bArr);
    }

    public int getArcexresult() {
        return realmGet$arcexresult();
    }

    public void setArcexresult(int i) {
        realmSet$arcexresult(i);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String str) {
        realmSet$name(str);
    }

    public String getCompany() {
        return realmGet$company();
    }

    public void setCompany(String str) {
        realmSet$company(str);
    }

    public String getPhone() {
        return realmGet$phone();
    }

    public void setPhone(String str) {
        realmSet$phone(str);
    }

    public String getIdcard() {
        return realmGet$idcard();
    }

    public void setIdcard(String str) {
        realmSet$idcard(str);
    }

    public int getType() {
        return realmGet$type();
    }

    public void setType(int i) {
        realmSet$type(i);
    }

    public String getSex() {
        return realmGet$sex();
    }

    public void setSex(String str) {
        realmSet$sex(str);
    }

    public String getFace() {
        return realmGet$face();
    }

    public void setFace(String str) {
        realmSet$face(str);
    }

    public String getEmail() {
        return realmGet$email();
    }

    public void setEmail(String str) {
        realmSet$email(str);
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

    public String getBirthPrompt() {
        return realmGet$birthPrompt();
    }

    public void setBirthPrompt(String str) {
        realmSet$birthPrompt(str);
    }

    public String getVipPrompt() {
        return realmGet$vipPrompt();
    }

    public void setVipPrompt(String str) {
        realmSet$vipPrompt(str);
    }

    public String getPrompt() {
        return realmGet$prompt();
    }

    public void setPrompt(String str) {
        realmSet$prompt(str);
    }

    public String getCheck() {
        return realmGet$check();
    }

    public void setCheck(String str) {
        realmSet$check(str);
    }

    public long getCreatetime() {
        return realmGet$createtime();
    }

    public void setCreatetime(int i) {
        realmSet$createtime((long) i);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String str) {
        realmSet$id(str);
    }

    public String getPath() {
        return realmGet$path();
    }

    public void setPath(String str) {
        realmSet$path(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VisitorResponse visitorResponse = (VisitorResponse) obj;
        if (realmGet$type() != visitorResponse.realmGet$type() || realmGet$createtime() != visitorResponse.realmGet$createtime() || realmGet$stresult() != visitorResponse.realmGet$stresult()) {
            return false;
        }
        if (faceType == 0) {
            if (realmGet$hasfea_st() == visitorResponse.realmGet$hasfea_st()) {
                return false;
            }
        } else if (realmGet$isHasfea_hr() == visitorResponse.realmGet$isHasfea_hr()) {
            return false;
        }
        if (Objects.equals(realmGet$name(), visitorResponse.realmGet$name()) && Objects.equals(realmGet$company(), visitorResponse.realmGet$company()) && Objects.equals(realmGet$phone(), visitorResponse.realmGet$phone()) && Objects.equals(realmGet$idcard(), visitorResponse.realmGet$idcard()) && Objects.equals(realmGet$sex(), visitorResponse.realmGet$sex()) && Objects.equals(realmGet$face(), visitorResponse.realmGet$face()) && Objects.equals(realmGet$email(), visitorResponse.realmGet$email()) && Objects.equals(realmGet$wechat(), visitorResponse.realmGet$wechat()) && Objects.equals(realmGet$description(), visitorResponse.realmGet$description()) && Objects.equals(realmGet$prompt(), visitorResponse.realmGet$prompt()) && Objects.equals(realmGet$vipPrompt(), visitorResponse.realmGet$vipPrompt()) && Objects.equals(realmGet$birthPrompt(), visitorResponse.realmGet$birthPrompt()) && Objects.equals(realmGet$check(), visitorResponse.realmGet$check()) && Objects.equals(realmGet$path(), visitorResponse.realmGet$path())) {
            return Objects.equals(realmGet$id(), visitorResponse.realmGet$id());
        }
        return false;
    }

    public int hashCode() {
        boolean z;
        int i;
        int i2 = 0;
        int hashCode = ((((((int) (((long) ((((((((((((((((((((((((((((realmGet$name() != null ? realmGet$name().hashCode() : 0) * 31) + (realmGet$company() != null ? realmGet$company().hashCode() : 0)) * 31) + (realmGet$phone() != null ? realmGet$phone().hashCode() : 0)) * 31) + (realmGet$idcard() != null ? realmGet$idcard().hashCode() : 0)) * 31) + realmGet$type()) * 31) + (realmGet$sex() != null ? realmGet$sex().hashCode() : 0)) * 31) + (realmGet$face() != null ? realmGet$face().hashCode() : 0)) * 31) + (realmGet$email() != null ? realmGet$email().hashCode() : 0)) * 31) + (realmGet$wechat() != null ? realmGet$wechat().hashCode() : 0)) * 31) + (realmGet$description() != null ? realmGet$description().hashCode() : 0)) * 31) + (realmGet$prompt() != null ? realmGet$prompt().hashCode() : 0)) * 31) + (realmGet$vipPrompt() != null ? realmGet$vipPrompt().hashCode() : 0)) * 31) + (realmGet$birthPrompt() != null ? realmGet$birthPrompt().hashCode() : 0)) * 31) + (realmGet$check() != null ? realmGet$check().hashCode() : 0)) * 31)) + realmGet$createtime())) * 31) + (realmGet$path() != null ? realmGet$path().hashCode() : 0)) * 31) + realmGet$stresult()) * 31;
        if (realmGet$id() != null) {
            i2 = realmGet$id().hashCode();
        }
        int i3 = hashCode + i2;
        if (faceType == 0) {
            i = i3 * 31;
            z = realmGet$hasfea_st();
        } else {
            i = i3 * 31;
            z = realmGet$isHasfea_hr();
        }
        return i + (z ? 1 : 0);
    }

    public String toString() {
        return "VisitorResponse{name='" + realmGet$name() + '\'' + ", company='" + realmGet$company() + '\'' + ", phone='" + realmGet$phone() + '\'' + ", idcard='" + realmGet$idcard() + '\'' + ", type=" + realmGet$type() + ", sex='" + realmGet$sex() + '\'' + ", face='" + realmGet$face() + '\'' + ", email='" + realmGet$email() + '\'' + ", wechat='" + realmGet$wechat() + '\'' + ", description='" + realmGet$description() + '\'' + ", prompt='" + realmGet$prompt() + '\'' + ", vipPrompt='" + realmGet$vipPrompt() + '\'' + ", birthPrompt='" + realmGet$birthPrompt() + '\'' + ", check='" + realmGet$check() + '\'' + ", createtime=" + realmGet$createtime() + ", path='" + realmGet$path() + '\'' + ", stresult=" + realmGet$stresult() + ", hasfea_st=" + realmGet$hasfea_st() + ", id='" + realmGet$id() + '\'' + '}';
    }

    public String GetStrFromJson() {
        return "VisitorResponse{name='" + realmGet$name() + '\'' + "type='" + realmGet$type() + '\'' + ", company='" + realmGet$company() + '\'' + ", phone='" + realmGet$phone() + '\'' + ", idcard='" + realmGet$idcard() + '\'' + ",  sex='" + realmGet$sex() + '\'' + ", face='" + realmGet$face() + '\'' + ", email='" + realmGet$email() + '\'' + ", wechat='" + realmGet$wechat() + '\'' + ",  description='" + realmGet$description() + '\'' + ", prompt='" + realmGet$prompt() + '\'' + ", check='" + realmGet$check() + '\'' + ", createtime=" + realmGet$createtime() + ", id='" + realmGet$id() + '\'' + "stresult=" + realmGet$stresult() + "arcexresult=" + realmGet$arcexresult() + '}';
    }

    public static void setFaceType(int i) {
        faceType = i;
    }

    public static int getFaceType() {
        return faceType;
    }
}
