package com.ciot.networklib.bean;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J-\u0010\u0011\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0006HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/ciot/networklib/bean/CoapUpdateBean;", "", "records", "", "Lcom/ciot/networklib/bean/Record;", "token", "", "user", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getRecords", "()Ljava/util/List;", "getToken", "()Ljava/lang/String;", "getUser", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: CoapUpdateBean.kt */
public final class CoapUpdateBean {
    private final List<Record> records;
    private final String token;
    private final String user;

    public static /* synthetic */ CoapUpdateBean copy$default(CoapUpdateBean coapUpdateBean, List<Record> list, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = coapUpdateBean.records;
        }
        if ((i & 2) != 0) {
            str = coapUpdateBean.token;
        }
        if ((i & 4) != 0) {
            str2 = coapUpdateBean.user;
        }
        return coapUpdateBean.copy(list, str, str2);
    }

    public final List<Record> component1() {
        return this.records;
    }

    public final String component2() {
        return this.token;
    }

    public final String component3() {
        return this.user;
    }

    public final CoapUpdateBean copy(List<Record> list, String str, String str2) {
        Intrinsics.checkNotNullParameter(list, "records");
        Intrinsics.checkNotNullParameter(str, "token");
        Intrinsics.checkNotNullParameter(str2, "user");
        return new CoapUpdateBean(list, str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CoapUpdateBean)) {
            return false;
        }
        CoapUpdateBean coapUpdateBean = (CoapUpdateBean) obj;
        return Intrinsics.areEqual((Object) this.records, (Object) coapUpdateBean.records) && Intrinsics.areEqual((Object) this.token, (Object) coapUpdateBean.token) && Intrinsics.areEqual((Object) this.user, (Object) coapUpdateBean.user);
    }

    public int hashCode() {
        return (((this.records.hashCode() * 31) + this.token.hashCode()) * 31) + this.user.hashCode();
    }

    public String toString() {
        return "CoapUpdateBean(records=" + this.records + ", token=" + this.token + ", user=" + this.user + ')';
    }

    public CoapUpdateBean(List<Record> list, String str, String str2) {
        Intrinsics.checkNotNullParameter(list, "records");
        Intrinsics.checkNotNullParameter(str, "token");
        Intrinsics.checkNotNullParameter(str2, "user");
        this.records = list;
        this.token = str;
        this.user = str2;
    }

    public final List<Record> getRecords() {
        return this.records;
    }

    public final String getToken() {
        return this.token;
    }

    public final String getUser() {
        return this.user;
    }
}
