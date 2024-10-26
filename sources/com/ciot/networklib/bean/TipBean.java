package com.ciot.networklib.bean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\u0018\u0000 \u00162\u00020\u0001:\u0002\u0016\u0017B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0015\u001a\u00020\u0005H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0018"}, d2 = {"Lcom/ciot/networklib/bean/TipBean;", "", "isShow", "", "tip", "", "(ZLjava/lang/String;)V", "type", "", "(ZLjava/lang/String;I)V", "()Z", "setShow", "(Z)V", "getTip", "()Ljava/lang/String;", "setTip", "(Ljava/lang/String;)V", "getType", "()I", "setType", "(I)V", "toString", "Companion", "TipType", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: TipBean.kt */
public final class TipBean {
    public static final int ABNOR_CONNECT = 1;
    public static final int ABNOR_RECHARGE = 2;
    public static final int CHASSIS_UPGRADE = 4;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int INFRARED_ANOMALY = 5;
    public static final int MAP_ABNORMA = 6;
    public static final int NON_CHARGING_STAR = 3;
    public static final int NO_CHARGR_NUM = 7;
    private boolean isShow;
    private String tip = "";
    private int type = 1;

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lcom/ciot/networklib/bean/TipBean$TipType;", "", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: TipBean.kt */
    public @interface TipType {
    }

    public final boolean isShow() {
        return this.isShow;
    }

    public final void setShow(boolean z) {
        this.isShow = z;
    }

    public final String getTip() {
        return this.tip;
    }

    public final void setTip(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tip = str;
    }

    public final int getType() {
        return this.type;
    }

    public final void setType(int i) {
        this.type = i;
    }

    public TipBean(boolean z, String str) {
        Intrinsics.checkNotNullParameter(str, "tip");
        this.isShow = z;
        this.tip = str;
    }

    public TipBean(boolean z, String str, int i) {
        Intrinsics.checkNotNullParameter(str, "tip");
        this.isShow = z;
        this.tip = str;
        this.type = i;
    }

    public String toString() {
        return "TipBean(isShow=" + this.isShow + ", tip='" + this.tip + "')";
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/ciot/networklib/bean/TipBean$Companion;", "", "()V", "ABNOR_CONNECT", "", "ABNOR_RECHARGE", "CHASSIS_UPGRADE", "INFRARED_ANOMALY", "MAP_ABNORMA", "NON_CHARGING_STAR", "NO_CHARGR_NUM", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: TipBean.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
