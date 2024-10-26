package com.ciot.networklib.bean;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J9\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\b\u0010\u0015\u001a\u00020\u0006H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/ciot/networklib/bean/CoapBean;", "", "gatewayHost", "", "pushHost", "recordHost", "", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getGatewayHost", "()Ljava/util/List;", "getPushHost", "getRecordHost", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: CoapBean.kt */
public final class CoapBean {
    private final List<Object> gatewayHost;
    private final List<Object> pushHost;
    private final List<String> recordHost;

    public static /* synthetic */ CoapBean copy$default(CoapBean coapBean, List<Object> list, List<Object> list2, List<String> list3, int i, Object obj) {
        if ((i & 1) != 0) {
            list = coapBean.gatewayHost;
        }
        if ((i & 2) != 0) {
            list2 = coapBean.pushHost;
        }
        if ((i & 4) != 0) {
            list3 = coapBean.recordHost;
        }
        return coapBean.copy(list, list2, list3);
    }

    public final List<Object> component1() {
        return this.gatewayHost;
    }

    public final List<Object> component2() {
        return this.pushHost;
    }

    public final List<String> component3() {
        return this.recordHost;
    }

    public final CoapBean copy(List<? extends Object> list, List<? extends Object> list2, List<String> list3) {
        Intrinsics.checkNotNullParameter(list, "gatewayHost");
        Intrinsics.checkNotNullParameter(list2, "pushHost");
        Intrinsics.checkNotNullParameter(list3, "recordHost");
        return new CoapBean(list, list2, list3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CoapBean)) {
            return false;
        }
        CoapBean coapBean = (CoapBean) obj;
        return Intrinsics.areEqual((Object) this.gatewayHost, (Object) coapBean.gatewayHost) && Intrinsics.areEqual((Object) this.pushHost, (Object) coapBean.pushHost) && Intrinsics.areEqual((Object) this.recordHost, (Object) coapBean.recordHost);
    }

    public int hashCode() {
        return (((this.gatewayHost.hashCode() * 31) + this.pushHost.hashCode()) * 31) + this.recordHost.hashCode();
    }

    public CoapBean(List<? extends Object> list, List<? extends Object> list2, List<String> list3) {
        Intrinsics.checkNotNullParameter(list, "gatewayHost");
        Intrinsics.checkNotNullParameter(list2, "pushHost");
        Intrinsics.checkNotNullParameter(list3, "recordHost");
        this.gatewayHost = list;
        this.pushHost = list2;
        this.recordHost = list3;
    }

    public final List<Object> getGatewayHost() {
        return this.gatewayHost;
    }

    public final List<Object> getPushHost() {
        return this.pushHost;
    }

    public final List<String> getRecordHost() {
        return this.recordHost;
    }

    public String toString() {
        return "CoapBean(gatewayHost=" + this.gatewayHost + ", pushHost=" + this.pushHost + ", recordHost=" + this.recordHost + ')';
    }
}
