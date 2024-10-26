package com.ciot.networklib.bean;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u0004J\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006J\b\u0010\f\u001a\u0004\u0018\u00010\bJ\u0006\u0010\r\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0004J\u0010\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/ciot/networklib/bean/CompanyAdrressBean;", "", "()V", "code", "", "data", "Lcom/ciot/networklib/bean/CompanyAdrressBean$DataBean;", "message", "", "ttl", "getCode", "getData", "getMessage", "getTtl", "setCode", "", "setData", "setMessage", "setTtl", "DataBean", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: CompanyAdrressBean.kt */
public final class CompanyAdrressBean {
    private int code;
    private DataBean data;
    private String message;
    private int ttl;

    public final int getCode() {
        return this.code;
    }

    public final void setCode(int i) {
        this.code = i;
    }

    public final String getMessage() {
        return this.message;
    }

    public final void setMessage(String str) {
        this.message = str;
    }

    public final int getTtl() {
        return this.ttl;
    }

    public final void setTtl(int i) {
        this.ttl = i;
    }

    public final DataBean getData() {
        return this.data;
    }

    public final void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\f"}, d2 = {"Lcom/ciot/networklib/bean/CompanyAdrressBean$DataBean;", "", "()V", "address", "", "getAddress", "()Ljava/lang/String;", "setAddress", "(Ljava/lang/String;)V", "company", "getCompany", "setCompany", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: CompanyAdrressBean.kt */
    public static final class DataBean {
        private String address;
        private String company;

        public final String getCompany() {
            return this.company;
        }

        public final void setCompany(String str) {
            this.company = str;
        }

        public final String getAddress() {
            return this.address;
        }

        public final void setAddress(String str) {
            this.address = str;
        }
    }
}
