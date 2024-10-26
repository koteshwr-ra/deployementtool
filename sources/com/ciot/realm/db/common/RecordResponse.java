package com.ciot.realm.db.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.ciot.realm.db.EmployeeBean;
import com.ciot.realm.db.RecordLockMode;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.com_ciot_realm_db_common_RecordResponseRealmProxyInterface;
import io.realm.internal.RealmObjectProxy;

public class RecordResponse extends RealmObject implements Parcelable, com_ciot_realm_db_common_RecordResponseRealmProxyInterface {
    public static final Parcelable.Creator<RecordResponse> CREATOR = new Parcelable.Creator<RecordResponse>() {
        public RecordResponse createFromParcel(Parcel parcel) {
            return new RecordResponse(parcel);
        }

        public RecordResponse[] newArray(int i) {
            return new RecordResponse[i];
        }
    };
    private AckBean ack;
    private String auditnotes;
    private double begin;
    private String code;
    private CompanyResponse company;
    private int createtime;
    private String description;
    private EmployeeBean employee;
    private double end;
    private String haspostpone;
    @PrimaryKey
    private String id;
    private RecordLockMode lockmode;
    private int proofNum;
    private Settings settings;
    private String sms;
    private String status;
    private int type;
    private ValidateBean validate;
    private VisitorBean visitor;

    public int describeContents() {
        return 0;
    }

    public AckBean realmGet$ack() {
        return this.ack;
    }

    public String realmGet$auditnotes() {
        return this.auditnotes;
    }

    public double realmGet$begin() {
        return this.begin;
    }

    public String realmGet$code() {
        return this.code;
    }

    public CompanyResponse realmGet$company() {
        return this.company;
    }

    public int realmGet$createtime() {
        return this.createtime;
    }

    public String realmGet$description() {
        return this.description;
    }

    public EmployeeBean realmGet$employee() {
        return this.employee;
    }

    public double realmGet$end() {
        return this.end;
    }

    public String realmGet$haspostpone() {
        return this.haspostpone;
    }

    public String realmGet$id() {
        return this.id;
    }

    public RecordLockMode realmGet$lockmode() {
        return this.lockmode;
    }

    public int realmGet$proofNum() {
        return this.proofNum;
    }

    public Settings realmGet$settings() {
        return this.settings;
    }

    public String realmGet$sms() {
        return this.sms;
    }

    public String realmGet$status() {
        return this.status;
    }

    public int realmGet$type() {
        return this.type;
    }

    public ValidateBean realmGet$validate() {
        return this.validate;
    }

    public VisitorBean realmGet$visitor() {
        return this.visitor;
    }

    public void realmSet$ack(AckBean ackBean) {
        this.ack = ackBean;
    }

    public void realmSet$auditnotes(String str) {
        this.auditnotes = str;
    }

    public void realmSet$begin(double d) {
        this.begin = d;
    }

    public void realmSet$code(String str) {
        this.code = str;
    }

    public void realmSet$company(CompanyResponse companyResponse) {
        this.company = companyResponse;
    }

    public void realmSet$createtime(int i) {
        this.createtime = i;
    }

    public void realmSet$description(String str) {
        this.description = str;
    }

    public void realmSet$employee(EmployeeBean employeeBean) {
        this.employee = employeeBean;
    }

    public void realmSet$end(double d) {
        this.end = d;
    }

    public void realmSet$haspostpone(String str) {
        this.haspostpone = str;
    }

    public void realmSet$id(String str) {
        this.id = str;
    }

    public void realmSet$lockmode(RecordLockMode recordLockMode) {
        this.lockmode = recordLockMode;
    }

    public void realmSet$proofNum(int i) {
        this.proofNum = i;
    }

    public void realmSet$settings(Settings settings2) {
        this.settings = settings2;
    }

    public void realmSet$sms(String str) {
        this.sms = str;
    }

    public void realmSet$status(String str) {
        this.status = str;
    }

    public void realmSet$type(int i) {
        this.type = i;
    }

    public void realmSet$validate(ValidateBean validateBean) {
        this.validate = validateBean;
    }

    public void realmSet$visitor(VisitorBean visitorBean) {
        this.visitor = visitorBean;
    }

    public RecordResponse() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    protected RecordResponse(Parcel parcel) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
        realmSet$haspostpone(parcel.readString());
        realmSet$begin((double) parcel.readLong());
        realmSet$proofNum(parcel.readInt());
        realmSet$code(parcel.readString());
        realmSet$end(parcel.readDouble());
        realmSet$sms(parcel.readString());
        realmSet$type(parcel.readInt());
        realmSet$description(parcel.readString());
        realmSet$status(parcel.readString());
        realmSet$createtime(parcel.readInt());
        realmSet$auditnotes(parcel.readString());
        realmSet$id(parcel.readString());
    }

    public VisitorBean getVisitor() {
        return realmGet$visitor();
    }

    public void setVisitor(VisitorBean visitorBean) {
        realmSet$visitor(visitorBean);
    }

    public double getBegin() {
        return realmGet$begin();
    }

    public void setBegin(long j) {
        realmSet$begin((double) j);
    }

    public int getProofNum() {
        return realmGet$proofNum();
    }

    public void setProofNum(int i) {
        realmSet$proofNum(i);
    }

    public String getCode() {
        return realmGet$code();
    }

    public void setCode(String str) {
        realmSet$code(str);
    }

    public ValidateBean getValidate() {
        return realmGet$validate();
    }

    public void setValidate(ValidateBean validateBean) {
        realmSet$validate(validateBean);
    }

    public Settings getSettings() {
        return realmGet$settings();
    }

    public void setSettings(Settings settings2) {
        realmSet$settings(settings2);
    }

    public double getEnd() {
        return realmGet$end();
    }

    public void setEnd(long j) {
        realmSet$end((double) j);
    }

    public String getSms() {
        return realmGet$sms();
    }

    public void setSms(String str) {
        realmSet$sms(str);
    }

    public int getType() {
        return realmGet$type();
    }

    public void setType(int i) {
        realmSet$type(i);
    }

    public EmployeeBean getEmployee() {
        return realmGet$employee();
    }

    public void setEmployee(EmployeeBean employeeBean) {
        realmSet$employee(employeeBean);
    }

    public String getDescription() {
        return realmGet$description();
    }

    public void setDescription(String str) {
        realmSet$description(str);
    }

    public String getStatus() {
        return realmGet$status();
    }

    public void setStatus(String str) {
        realmSet$status(str);
    }

    public int getCreatetime() {
        return realmGet$createtime();
    }

    public void setCreatetime(int i) {
        realmSet$createtime(i);
    }

    public CompanyResponse getCompany() {
        return realmGet$company();
    }

    public void setCompany(CompanyResponse companyResponse) {
        realmSet$company(companyResponse);
    }

    public AckBean getAck() {
        return realmGet$ack();
    }

    public void setAck(AckBean ackBean) {
        realmSet$ack(ackBean);
    }

    public String getAuditnotes() {
        return realmGet$auditnotes();
    }

    public void setAuditnotes(String str) {
        realmSet$auditnotes(str);
    }

    public RecordLockMode getLockmode() {
        return realmGet$lockmode();
    }

    public void setLockmode(RecordLockMode recordLockMode) {
        realmSet$lockmode(recordLockMode);
    }

    public String getId() {
        return realmGet$id();
    }

    public void setId(String str) {
        realmSet$id(str);
    }

    public String getHaspostpone() {
        return realmGet$haspostpone();
    }

    public void setHaspostpone(String str) {
        realmSet$haspostpone(str);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(realmGet$haspostpone());
        parcel.writeDouble(realmGet$begin());
        parcel.writeInt(realmGet$proofNum());
        parcel.writeString(realmGet$code());
        parcel.writeDouble(realmGet$end());
        parcel.writeString(realmGet$sms());
        parcel.writeInt(realmGet$type());
        parcel.writeString(realmGet$description());
        parcel.writeString(realmGet$status());
        parcel.writeInt(realmGet$createtime());
        parcel.writeString(realmGet$auditnotes());
        parcel.writeString(realmGet$id());
    }

    public String toString() {
        return "RecordResponse{haspostpone='" + realmGet$haspostpone() + '\'' + ", visitor=" + realmGet$visitor() + ", begin=" + realmGet$begin() + ", proofNum=" + realmGet$proofNum() + ", code='" + realmGet$code() + '\'' + ", validate=" + realmGet$validate() + ", settings=" + realmGet$settings() + ", end=" + realmGet$end() + ", sms='" + realmGet$sms() + '\'' + ", type=" + realmGet$type() + ", employee=" + realmGet$employee() + ", description='" + realmGet$description() + '\'' + ", status='" + realmGet$status() + '\'' + ", createtime=" + realmGet$createtime() + ", company=" + realmGet$company() + ", ack=" + realmGet$ack() + ", auditnotes='" + realmGet$auditnotes() + '\'' + ", lockmode=" + realmGet$lockmode() + ", id='" + realmGet$id() + '\'' + '}';
    }
}
