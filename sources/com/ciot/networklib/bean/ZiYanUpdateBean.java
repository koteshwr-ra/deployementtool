package com.ciot.networklib.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003JY\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u0007HÖ\u0001J\b\u0010$\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006%"}, d2 = {"Lcom/ciot/networklib/bean/ZiYanUpdateBean;", "", "area", "", "deviceid", "project", "robottype", "", "timestamp", "request", "ciot_response", "yunji_response", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getArea", "()Ljava/lang/String;", "getCiot_response", "getDeviceid", "getProject", "getRequest", "getRobottype", "()I", "getTimestamp", "getYunji_response", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: ZiYanUpdateBean.kt */
public final class ZiYanUpdateBean {
    private final String area;
    private final String ciot_response;
    private final String deviceid;
    private final String project;
    private final String request;
    private final int robottype;
    private final String timestamp;
    private final String yunji_response;

    public static /* synthetic */ ZiYanUpdateBean copy$default(ZiYanUpdateBean ziYanUpdateBean, String str, String str2, String str3, int i, String str4, String str5, String str6, String str7, int i2, Object obj) {
        ZiYanUpdateBean ziYanUpdateBean2 = ziYanUpdateBean;
        int i3 = i2;
        return ziYanUpdateBean.copy((i3 & 1) != 0 ? ziYanUpdateBean2.area : str, (i3 & 2) != 0 ? ziYanUpdateBean2.deviceid : str2, (i3 & 4) != 0 ? ziYanUpdateBean2.project : str3, (i3 & 8) != 0 ? ziYanUpdateBean2.robottype : i, (i3 & 16) != 0 ? ziYanUpdateBean2.timestamp : str4, (i3 & 32) != 0 ? ziYanUpdateBean2.request : str5, (i3 & 64) != 0 ? ziYanUpdateBean2.ciot_response : str6, (i3 & 128) != 0 ? ziYanUpdateBean2.yunji_response : str7);
    }

    public final String component1() {
        return this.area;
    }

    public final String component2() {
        return this.deviceid;
    }

    public final String component3() {
        return this.project;
    }

    public final int component4() {
        return this.robottype;
    }

    public final String component5() {
        return this.timestamp;
    }

    public final String component6() {
        return this.request;
    }

    public final String component7() {
        return this.ciot_response;
    }

    public final String component8() {
        return this.yunji_response;
    }

    public final ZiYanUpdateBean copy(String str, String str2, String str3, int i, String str4, String str5, String str6, String str7) {
        Intrinsics.checkNotNullParameter(str, "area");
        Intrinsics.checkNotNullParameter(str2, "deviceid");
        Intrinsics.checkNotNullParameter(str3, "project");
        Intrinsics.checkNotNullParameter(str4, "timestamp");
        String str8 = str5;
        Intrinsics.checkNotNullParameter(str8, "request");
        String str9 = str6;
        Intrinsics.checkNotNullParameter(str9, "ciot_response");
        String str10 = str7;
        Intrinsics.checkNotNullParameter(str10, "yunji_response");
        return new ZiYanUpdateBean(str, str2, str3, i, str4, str8, str9, str10);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ZiYanUpdateBean)) {
            return false;
        }
        ZiYanUpdateBean ziYanUpdateBean = (ZiYanUpdateBean) obj;
        return Intrinsics.areEqual((Object) this.area, (Object) ziYanUpdateBean.area) && Intrinsics.areEqual((Object) this.deviceid, (Object) ziYanUpdateBean.deviceid) && Intrinsics.areEqual((Object) this.project, (Object) ziYanUpdateBean.project) && this.robottype == ziYanUpdateBean.robottype && Intrinsics.areEqual((Object) this.timestamp, (Object) ziYanUpdateBean.timestamp) && Intrinsics.areEqual((Object) this.request, (Object) ziYanUpdateBean.request) && Intrinsics.areEqual((Object) this.ciot_response, (Object) ziYanUpdateBean.ciot_response) && Intrinsics.areEqual((Object) this.yunji_response, (Object) ziYanUpdateBean.yunji_response);
    }

    public int hashCode() {
        return (((((((((((((this.area.hashCode() * 31) + this.deviceid.hashCode()) * 31) + this.project.hashCode()) * 31) + this.robottype) * 31) + this.timestamp.hashCode()) * 31) + this.request.hashCode()) * 31) + this.ciot_response.hashCode()) * 31) + this.yunji_response.hashCode();
    }

    public ZiYanUpdateBean(String str, String str2, String str3, int i, String str4, String str5, String str6, String str7) {
        Intrinsics.checkNotNullParameter(str, "area");
        Intrinsics.checkNotNullParameter(str2, "deviceid");
        Intrinsics.checkNotNullParameter(str3, "project");
        Intrinsics.checkNotNullParameter(str4, "timestamp");
        Intrinsics.checkNotNullParameter(str5, "request");
        Intrinsics.checkNotNullParameter(str6, "ciot_response");
        Intrinsics.checkNotNullParameter(str7, "yunji_response");
        this.area = str;
        this.deviceid = str2;
        this.project = str3;
        this.robottype = i;
        this.timestamp = str4;
        this.request = str5;
        this.ciot_response = str6;
        this.yunji_response = str7;
    }

    public final String getArea() {
        return this.area;
    }

    public final String getDeviceid() {
        return this.deviceid;
    }

    public final String getProject() {
        return this.project;
    }

    public final int getRobottype() {
        return this.robottype;
    }

    public final String getTimestamp() {
        return this.timestamp;
    }

    public final String getRequest() {
        return this.request;
    }

    public final String getCiot_response() {
        return this.ciot_response;
    }

    public final String getYunji_response() {
        return this.yunji_response;
    }

    public String toString() {
        return "ZiYanUpdateBean(area='" + this.area + "', deviceid='" + this.deviceid + "', project='" + this.project + "', robottype=" + this.robottype + ", timestamp=" + this.timestamp + ", request='" + this.request + "', ciot_response='" + this.ciot_response + "', yunji_response='" + this.yunji_response + "')";
    }
}
