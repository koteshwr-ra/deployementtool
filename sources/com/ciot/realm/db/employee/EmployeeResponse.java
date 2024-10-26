package com.ciot.realm.db.employee;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_employee_EmployeeResponseRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;
import java.util.Objects;

public class EmployeeResponse extends RealmObject implements com_ciot_realm_db_employee_EmployeeResponseRealmProxyInterface {
    private static int faceType;
    private String arcCode;
    private int arcexresult;
    private String birthPrompt;
    private String birthday;
    private byte[] byfea_hr31;
    private String company;
    private long createtime;
    private String department;
    private String description;
    private String email;
    private String face;
    private String fingerprint;
    private String iccard;
    @PrimaryKey
    private String id;
    private String idcard;
    private boolean isHasfea_hr;
    private boolean isHasfea_st;
    private String job;
    private String name;
    private String path;
    private String phone;
    private String prompt;
    private String sex;
    private String staffno;
    private int status;
    private String stfea_hr31;
    private String stfea_st;
    private int type;
    private Integer uuid;
    private String vipPrompt;
    private String wechat;

    public String realmGet$arcCode() {
        return this.arcCode;
    }

    public int realmGet$arcexresult() {
        return this.arcexresult;
    }

    public String realmGet$birthPrompt() {
        return this.birthPrompt;
    }

    public String realmGet$birthday() {
        return this.birthday;
    }

    public byte[] realmGet$byfea_hr31() {
        return this.byfea_hr31;
    }

    public String realmGet$company() {
        return this.company;
    }

    public long realmGet$createtime() {
        return this.createtime;
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

    public String realmGet$iccard() {
        return this.iccard;
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

    public boolean realmGet$isHasfea_st() {
        return this.isHasfea_st;
    }

    public String realmGet$job() {
        return this.job;
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

    public String realmGet$staffno() {
        return this.staffno;
    }

    public int realmGet$status() {
        return this.status;
    }

    public String realmGet$stfea_hr31() {
        return this.stfea_hr31;
    }

    public String realmGet$stfea_st() {
        return this.stfea_st;
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

    public void realmSet$arcCode(String str) {
        this.arcCode = str;
    }

    public void realmSet$arcexresult(int i) {
        this.arcexresult = i;
    }

    public void realmSet$birthPrompt(String str) {
        this.birthPrompt = str;
    }

    public void realmSet$birthday(String str) {
        this.birthday = str;
    }

    public void realmSet$byfea_hr31(byte[] bArr) {
        this.byfea_hr31 = bArr;
    }

    public void realmSet$company(String str) {
        this.company = str;
    }

    public void realmSet$createtime(long j) {
        this.createtime = j;
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

    public void realmSet$iccard(String str) {
        this.iccard = str;
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

    public void realmSet$isHasfea_st(boolean z) {
        this.isHasfea_st = z;
    }

    public void realmSet$job(String str) {
        this.job = str;
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

    public void realmSet$staffno(String str) {
        this.staffno = str;
    }

    public void realmSet$status(int i) {
        this.status = i;
    }

    public void realmSet$stfea_hr31(String str) {
        this.stfea_hr31 = str;
    }

    public void realmSet$stfea_st(String str) {
        this.stfea_st = str;
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

    public EmployeeResponse() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$isHasfea_hr(false);
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

    public boolean isHasfea_hr() {
        return realmGet$isHasfea_hr();
    }

    public void setHasfea_hr(boolean z) {
        realmSet$isHasfea_hr(z);
    }

    public String getStaffno() {
        return realmGet$staffno();
    }

    public void setStaffno(String str) {
        realmSet$staffno(str);
    }

    public String getName() {
        return realmGet$name();
    }

    public void setName(String str) {
        realmSet$name(str);
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

    public String getIccard() {
        return realmGet$iccard();
    }

    public void setIccard(String str) {
        realmSet$iccard(str);
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

    public String getFingerprint() {
        return realmGet$fingerprint();
    }

    public void setFingerprint(String str) {
        realmSet$fingerprint(str);
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

    public String getArcCode() {
        return realmGet$arcCode();
    }

    public void setArcCode(String str) {
        realmSet$arcCode(str);
    }

    public long getCreatetime() {
        return realmGet$createtime();
    }

    public void setCreatetime(long j) {
        realmSet$createtime(j);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String str) {
        realmSet$id(str);
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

    public int getStatus() {
        return realmGet$status();
    }

    public void setStatus(int i) {
        realmSet$status(i);
    }

    public String getPath() {
        return realmGet$path();
    }

    public void setPath(String str) {
        realmSet$path(str);
    }

    public Integer getUuid() {
        return realmGet$uuid();
    }

    public void setUuid(Integer num) {
        realmSet$uuid(num);
    }

    public boolean isHasfea_st() {
        return realmGet$isHasfea_st();
    }

    public void setHasfea_st(boolean z) {
        realmSet$isHasfea_st(z);
    }

    public String getStfea_st() {
        return realmGet$stfea_st();
    }

    public void setStfea_st(String str) {
        realmSet$stfea_st(str);
    }

    public String toString() {
        return "EmployeeResponse{name='" + realmGet$name() + '\'' + ", idcard='" + realmGet$idcard() + '\'' + ", iccard='" + realmGet$iccard() + '\'' + ", phone='" + realmGet$phone() + '\'' + ", type=" + realmGet$type() + ", uuid=" + realmGet$uuid() + ",arcexresult=" + realmGet$arcexresult() + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EmployeeResponse employeeResponse = (EmployeeResponse) obj;
        if (realmGet$type() != employeeResponse.realmGet$type() || realmGet$createtime() != employeeResponse.realmGet$createtime() || realmGet$status() != employeeResponse.realmGet$status()) {
            return false;
        }
        if (faceType == 0) {
            if (realmGet$isHasfea_st() == employeeResponse.realmGet$isHasfea_st()) {
                return false;
            }
        } else if (realmGet$isHasfea_hr() == employeeResponse.realmGet$isHasfea_hr()) {
            return false;
        }
        if (Objects.equals(realmGet$staffno(), employeeResponse.realmGet$staffno()) && Objects.equals(realmGet$name(), employeeResponse.realmGet$name()) && Objects.equals(realmGet$idcard(), employeeResponse.realmGet$idcard()) && Objects.equals(realmGet$face(), employeeResponse.realmGet$face()) && Objects.equals(realmGet$iccard(), employeeResponse.realmGet$iccard()) && Objects.equals(realmGet$phone(), employeeResponse.realmGet$phone()) && Objects.equals(realmGet$email(), employeeResponse.realmGet$email()) && Objects.equals(realmGet$sex(), employeeResponse.realmGet$sex()) && Objects.equals(realmGet$department(), employeeResponse.realmGet$department()) && Objects.equals(realmGet$company(), employeeResponse.realmGet$company()) && Objects.equals(realmGet$job(), employeeResponse.realmGet$job()) && Objects.equals(realmGet$fingerprint(), employeeResponse.realmGet$fingerprint()) && Objects.equals(realmGet$wechat(), employeeResponse.realmGet$wechat()) && Objects.equals(realmGet$description(), employeeResponse.realmGet$description()) && Objects.equals(realmGet$birthday(), employeeResponse.realmGet$birthday()) && Objects.equals(realmGet$arcCode(), employeeResponse.realmGet$arcCode()) && Objects.equals(realmGet$id(), employeeResponse.realmGet$id()) && Objects.equals(realmGet$prompt(), employeeResponse.realmGet$prompt()) && Objects.equals(realmGet$vipPrompt(), employeeResponse.realmGet$vipPrompt()) && Objects.equals(realmGet$birthPrompt(), employeeResponse.realmGet$birthPrompt())) {
            return Objects.equals(realmGet$path(), employeeResponse.realmGet$path());
        }
        return false;
    }

    public int hashCode() {
        boolean z;
        int i;
        int i2 = 0;
        int hashCode = ((((((((((((int) (((long) ((((((((((((((((((((((((((((((((((realmGet$staffno() != null ? realmGet$staffno().hashCode() : 0) * 31) + (realmGet$name() != null ? realmGet$name().hashCode() : 0)) * 31) + (realmGet$idcard() != null ? realmGet$idcard().hashCode() : 0)) * 31) + (realmGet$face() != null ? realmGet$face().hashCode() : 0)) * 31) + (realmGet$iccard() != null ? realmGet$iccard().hashCode() : 0)) * 31) + (realmGet$phone() != null ? realmGet$phone().hashCode() : 0)) * 31) + (realmGet$email() != null ? realmGet$email().hashCode() : 0)) * 31) + realmGet$type()) * 31) + (realmGet$sex() != null ? realmGet$sex().hashCode() : 0)) * 31) + (realmGet$department() != null ? realmGet$department().hashCode() : 0)) * 31) + (realmGet$company() != null ? realmGet$company().hashCode() : 0)) * 31) + (realmGet$job() != null ? realmGet$job().hashCode() : 0)) * 31) + (realmGet$fingerprint() != null ? realmGet$fingerprint().hashCode() : 0)) * 31) + (realmGet$wechat() != null ? realmGet$wechat().hashCode() : 0)) * 31) + (realmGet$description() != null ? realmGet$description().hashCode() : 0)) * 31) + (realmGet$birthday() != null ? realmGet$birthday().hashCode() : 0)) * 31) + (realmGet$arcCode() != null ? realmGet$arcCode().hashCode() : 0)) * 31)) + realmGet$createtime())) * 31) + (realmGet$id() != null ? realmGet$id().hashCode() : 0)) * 31) + (realmGet$prompt() != null ? realmGet$prompt().hashCode() : 0)) * 31) + (realmGet$vipPrompt() != null ? realmGet$vipPrompt().hashCode() : 0)) * 31) + (realmGet$birthPrompt() != null ? realmGet$birthPrompt().hashCode() : 0)) * 31) + realmGet$status()) * 31;
        if (realmGet$path() != null) {
            i2 = realmGet$path().hashCode();
        }
        int i3 = hashCode + i2;
        if (faceType == 0) {
            i = i3 * 31;
            z = realmGet$isHasfea_st();
        } else {
            i = i3 * 31;
            z = realmGet$isHasfea_hr();
        }
        return i + (z ? 1 : 0);
    }

    public static void setFaceType(int i) {
        faceType = i;
    }

    public static int getFaceType() {
        return faceType;
    }

    public String GetStrFromJson() {
        return "staffno='" + realmGet$staffno() + '\'' + ", name='" + realmGet$name() + '\'' + ", type='" + realmGet$type() + '\'' + ", idcard='" + realmGet$idcard() + '\'' + ", face='" + realmGet$face() + '\'' + "   , iccard='" + realmGet$iccard() + '\'' + ", phone='" + realmGet$phone() + '\'' + ", email='" + realmGet$email() + '\'' + ", type=" + realmGet$type() + ", sex='" + realmGet$sex() + '\'' + ", department='" + realmGet$department() + '\'' + ", company='" + realmGet$company() + '\'' + ", job='" + realmGet$job() + '\'' + ", fingerprint='" + realmGet$fingerprint() + '\'' + ",  description='" + realmGet$description() + '\'' + ", birthday='" + realmGet$birthday() + '\'' + ",   createtime=" + realmGet$createtime() + ", id='" + realmGet$id() + '\'' + ", prompt='" + realmGet$prompt() + '\'' + ", status=" + realmGet$status() + "arcexresult=" + realmGet$arcexresult() + '}';
    }
}
