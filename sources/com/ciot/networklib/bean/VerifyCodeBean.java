package com.ciot.networklib.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.ciot.realm.db.common.CompanyResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001:\u0006MNOPQRB\u0005¢\u0006\u0002\u0010\u0002J\t\u0010!\u001a\u00020\rHÖ\u0001J\b\u0010\"\u001a\u0004\u0018\u00010\u0004J\b\u0010#\u001a\u0004\u0018\u00010\u0006J\u0006\u0010$\u001a\u00020\bJ\b\u0010%\u001a\u0004\u0018\u00010\u0006J\b\u0010&\u001a\u0004\u0018\u00010\u000bJ\u0006\u0010'\u001a\u00020\rJ\b\u0010(\u001a\u0004\u0018\u00010\u0006J\b\u0010)\u001a\u0004\u0018\u00010\u0010J\u0006\u0010*\u001a\u00020\u0012J\b\u0010+\u001a\u0004\u0018\u00010\u0006J\b\u0010,\u001a\u0004\u0018\u00010\u0006J\b\u0010-\u001a\u0004\u0018\u00010\u0016J\u0006\u0010.\u001a\u00020\rJ\b\u0010/\u001a\u0004\u0018\u00010\u0019J\b\u00100\u001a\u0004\u0018\u00010\u0006J\b\u00101\u001a\u0004\u0018\u00010\u0006J\u0006\u00102\u001a\u00020\rJ\b\u00103\u001a\u0004\u0018\u00010\u001eJ\b\u00104\u001a\u0004\u0018\u00010 J\u0010\u00105\u001a\u0002062\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u0010\u00107\u001a\u0002062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u000e\u00108\u001a\u0002062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u00109\u001a\u0002062\b\u0010\t\u001a\u0004\u0018\u00010\u0006J\u0010\u0010:\u001a\u0002062\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u000e\u0010;\u001a\u0002062\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010<\u001a\u0002062\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006J\u0010\u0010=\u001a\u0002062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u000e\u0010>\u001a\u0002062\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010?\u001a\u0002062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0006J\u0010\u0010@\u001a\u0002062\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006J\u0010\u0010A\u001a\u0002062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u000e\u0010B\u001a\u0002062\u0006\u0010\u0017\u001a\u00020\rJ\u0010\u0010C\u001a\u0002062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u0010\u0010D\u001a\u0002062\b\u0010\u001a\u001a\u0004\u0018\u00010\u0006J\u0010\u0010E\u001a\u0002062\b\u0010\u001b\u001a\u0004\u0018\u00010\u0006J\u000e\u0010F\u001a\u0002062\u0006\u0010\u001c\u001a\u00020\rJ\u0010\u0010G\u001a\u0002062\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u0010\u0010H\u001a\u0002062\b\u0010\u001f\u001a\u0004\u0018\u00010 J\u0019\u0010I\u001a\u0002062\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\rHÖ\u0001R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u000e¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"Lcom/ciot/networklib/bean/VerifyCodeBean;", "Landroid/os/Parcelable;", "()V", "ack", "Lcom/ciot/networklib/bean/VerifyCodeBean$AckBean;", "auditnotes", "", "begin", "", "code", "company", "Lcom/ciot/realm/db/common/CompanyResponse;", "createtime", "", "description", "employee", "Lcom/ciot/networklib/bean/VerifyCodeBean$EmployeeBean;", "end", "", "haspostpone", "id", "lockmode", "Lcom/ciot/networklib/bean/VerifyCodeBean$LockmodeBean;", "proofNum", "settings", "Lcom/ciot/networklib/bean/VerifyCodeBean$SettingsBean;", "sms", "status", "type", "validate", "Lcom/ciot/networklib/bean/VerifyCodeBean$ValidateBean;", "visitor", "Lcom/ciot/networklib/bean/VerifyCodeBean$VisitorBean;", "describeContents", "getAck", "getAuditnotes", "getBegin", "getCode", "getCompany", "getCreatetime", "getDescription", "getEmployee", "getEnd", "getHaspostpone", "getId", "getLockmode", "getProofNum", "getSettings", "getSms", "getStatus", "getType", "getValidate", "getVisitor", "setAck", "", "setAuditnotes", "setBegin", "setCode", "setCompany", "setCreatetime", "setDescription", "setEmployee", "setEnd", "setHaspostpone", "setId", "setLockmode", "setProofNum", "setSettings", "setSms", "setStatus", "setType", "setValidate", "setVisitor", "writeToParcel", "parcel", "Landroid/os/Parcel;", "flags", "AckBean", "EmployeeBean", "LockmodeBean", "SettingsBean", "ValidateBean", "VisitorBean", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: VerifyCodeBean.kt */
public final class VerifyCodeBean implements Parcelable {
    public static final Parcelable.Creator<VerifyCodeBean> CREATOR = new Creator();
    private AckBean ack;
    private String auditnotes;
    private double begin;
    private String code;
    private CompanyResponse company;
    private int createtime;
    private String description;
    private EmployeeBean employee;
    private long end;
    private String haspostpone;
    private String id;
    private LockmodeBean lockmode;
    private int proofNum;
    private SettingsBean settings;
    private String sms;
    private String status;
    private int type;
    private ValidateBean validate;
    private VisitorBean visitor;

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* compiled from: VerifyCodeBean.kt */
    public static final class Creator implements Parcelable.Creator<VerifyCodeBean> {
        public final VerifyCodeBean createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.readInt();
            return new VerifyCodeBean();
        }

        public final VerifyCodeBean[] newArray(int i) {
            return new VerifyCodeBean[i];
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeInt(1);
    }

    public final VisitorBean getVisitor() {
        return this.visitor;
    }

    public final void setVisitor(VisitorBean visitorBean) {
        this.visitor = visitorBean;
    }

    public final LockmodeBean getLockmode() {
        return this.lockmode;
    }

    public final void setLockmode(LockmodeBean lockmodeBean) {
        this.lockmode = lockmodeBean;
    }

    public final double getBegin() {
        return this.begin;
    }

    public final void setBegin(double d) {
        this.begin = d;
    }

    public final int getProofNum() {
        return this.proofNum;
    }

    public final void setProofNum(int i) {
        this.proofNum = i;
    }

    public final String getCode() {
        return this.code;
    }

    public final void setCode(String str) {
        this.code = str;
    }

    public final ValidateBean getValidate() {
        return this.validate;
    }

    public final void setValidate(ValidateBean validateBean) {
        this.validate = validateBean;
    }

    public final SettingsBean getSettings() {
        return this.settings;
    }

    public final void setSettings(SettingsBean settingsBean) {
        this.settings = settingsBean;
    }

    public final long getEnd() {
        return this.end;
    }

    public final void setEnd(long j) {
        this.end = j;
    }

    public final String getSms() {
        return this.sms;
    }

    public final void setSms(String str) {
        this.sms = str;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public final EmployeeBean getEmployee() {
        return this.employee;
    }

    public final void setEmployee(EmployeeBean employeeBean) {
        this.employee = employeeBean;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(String str) {
        this.description = str;
    }

    public final String getStatus() {
        return this.status;
    }

    public final void setStatus(String str) {
        this.status = str;
    }

    public final String getHaspostpone() {
        return this.haspostpone;
    }

    public final void setHaspostpone(String str) {
        this.haspostpone = str;
    }

    public final int getCreatetime() {
        return this.createtime;
    }

    public final void setCreatetime(int i) {
        this.createtime = i;
    }

    public final CompanyResponse getCompany() {
        return this.company;
    }

    public final void setCompany(CompanyResponse companyResponse) {
        this.company = companyResponse;
    }

    public final AckBean getAck() {
        return this.ack;
    }

    public final void setAck(AckBean ackBean) {
        this.ack = ackBean;
    }

    public final String getAuditnotes() {
        return this.auditnotes;
    }

    public final void setAuditnotes(String str) {
        this.auditnotes = str;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001a\u0010\u001e\u001a\u00020\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Lcom/ciot/networklib/bean/VerifyCodeBean$VisitorBean;", "", "()V", "company", "", "getCompany", "()Ljava/lang/String;", "setCompany", "(Ljava/lang/String;)V", "face", "getFace", "setFace", "id", "getId", "setId", "idcard", "getIdcard", "setIdcard", "lanpath", "getLanpath", "setLanpath", "name", "getName", "setName", "path", "getPath", "setPath", "phone", "getPhone", "setPhone", "type", "", "getType", "()I", "setType", "(I)V", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: VerifyCodeBean.kt */
    public static final class VisitorBean {
        private String company;
        private String face;
        private String id;
        private String idcard;
        private String lanpath;
        private String name;
        private String path;
        private String phone;
        private int type;

        public final String getId() {
            return this.id;
        }

        public final void setId(String str) {
            this.id = str;
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            this.name = str;
        }

        public final String getCompany() {
            return this.company;
        }

        public final void setCompany(String str) {
            this.company = str;
        }

        public final String getIdcard() {
            return this.idcard;
        }

        public final void setIdcard(String str) {
            this.idcard = str;
        }

        public final String getPhone() {
            return this.phone;
        }

        public final void setPhone(String str) {
            this.phone = str;
        }

        public final String getFace() {
            return this.face;
        }

        public final void setFace(String str) {
            this.face = str;
        }

        public final int getType() {
            return this.type;
        }

        public final void setType(int i) {
            this.type = i;
        }

        public final String getPath() {
            return this.path;
        }

        public final void setPath(String str) {
            this.path = str;
        }

        public final String getLanpath() {
            return this.lanpath;
        }

        public final void setLanpath(String str) {
            this.lanpath = str;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/ciot/networklib/bean/VerifyCodeBean$LockmodeBean;", "", "()V", "card", "", "getCard", "()Ljava/lang/String;", "setCard", "(Ljava/lang/String;)V", "face", "getFace", "setFace", "iccard", "getIccard", "setIccard", "idcardcode", "getIdcardcode", "setIdcardcode", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: VerifyCodeBean.kt */
    public static final class LockmodeBean {
        private String card;
        private String face;
        private String iccard;
        private String idcardcode;

        public final String getIccard() {
            return this.iccard;
        }

        public final void setIccard(String str) {
            this.iccard = str;
        }

        public final String getIdcardcode() {
            return this.idcardcode;
        }

        public final void setIdcardcode(String str) {
            this.idcardcode = str;
        }

        public final String getFace() {
            return this.face;
        }

        public final void setFace(String str) {
            this.face = str;
        }

        public final String getCard() {
            return this.card;
        }

        public final void setCard(String str) {
            this.card = str;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\b¨\u0006\u001b"}, d2 = {"Lcom/ciot/networklib/bean/VerifyCodeBean$ValidateBean;", "", "()V", "face", "", "getFace", "()Ljava/lang/String;", "setFace", "(Ljava/lang/String;)V", "idcard", "getIdcard", "setIdcard", "locale", "", "getLocale", "()I", "setLocale", "(I)V", "name", "getName", "setName", "phone", "getPhone", "setPhone", "result", "getResult", "setResult", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: VerifyCodeBean.kt */
    public static final class ValidateBean {
        private String face;
        private String idcard;
        private int locale;
        private String name;
        private String phone;
        private String result;

        public final String getPhone() {
            return this.phone;
        }

        public final void setPhone(String str) {
            this.phone = str;
        }

        public final String getResult() {
            return this.result;
        }

        public final void setResult(String str) {
            this.result = str;
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            this.name = str;
        }

        public final int getLocale() {
            return this.locale;
        }

        public final void setLocale(int i) {
            this.locale = i;
        }

        public final String getIdcard() {
            return this.idcard;
        }

        public final void setIdcard(String str) {
            this.idcard = str;
        }

        public final String getFace() {
            return this.face;
        }

        public final void setFace(String str) {
            this.face = str;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\b¨\u0006\u001e"}, d2 = {"Lcom/ciot/networklib/bean/VerifyCodeBean$SettingsBean;", "", "()V", "company", "", "getCompany", "()Ljava/lang/String;", "setCompany", "(Ljava/lang/String;)V", "contact", "getContact", "setContact", "id", "getId", "setId", "name", "getName", "setName", "phone", "getPhone", "setPhone", "security", "", "getSecurity", "()I", "setSecurity", "(I)V", "type", "getType", "setType", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: VerifyCodeBean.kt */
    public static final class SettingsBean {
        private String company;
        private String contact;
        private String id;
        private String name;
        private String phone;
        private int security;
        private String type;

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            this.name = str;
        }

        public final String getType() {
            return this.type;
        }

        public final void setType(String str) {
            this.type = str;
        }

        public final String getCompany() {
            return this.company;
        }

        public final void setCompany(String str) {
            this.company = str;
        }

        public final String getContact() {
            return this.contact;
        }

        public final void setContact(String str) {
            this.contact = str;
        }

        public final String getPhone() {
            return this.phone;
        }

        public final void setPhone(String str) {
            this.phone = str;
        }

        public final int getSecurity() {
            return this.security;
        }

        public final void setSecurity(int i) {
            this.security = i;
        }

        public final String getId() {
            return this.id;
        }

        public final void setId(String str) {
            this.id = str;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/ciot/networklib/bean/VerifyCodeBean$EmployeeBean;", "", "()V", "company", "", "getCompany", "()Ljava/lang/String;", "setCompany", "(Ljava/lang/String;)V", "name", "getName", "setName", "phone", "getPhone", "setPhone", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: VerifyCodeBean.kt */
    public static final class EmployeeBean {
        private String company;
        private String name;
        private String phone;

        public final String getPhone() {
            return this.phone;
        }

        public final void setPhone(String str) {
            this.phone = str;
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            this.name = str;
        }

        public final String getCompany() {
            return this.company;
        }

        public final void setCompany(String str) {
            this.company = str;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/ciot/networklib/bean/VerifyCodeBean$AckBean;", "", "()V", "time", "", "getTime", "()J", "setTime", "(J)V", "user", "", "getUser", "()Ljava/lang/String;", "setUser", "(Ljava/lang/String;)V", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: VerifyCodeBean.kt */
    public static final class AckBean {
        private long time;
        private String user;

        public final long getTime() {
            return this.time;
        }

        public final void setTime(long j) {
            this.time = j;
        }

        public final String getUser() {
            return this.user;
        }

        public final void setUser(String str) {
            this.user = str;
        }
    }
}
