package com.ciot.navigation.navigation.water;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u000bHÆ\u0003JO\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010 \u001a\u00020\u000b2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u001a\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000e¨\u0006%"}, d2 = {"Lcom/ciot/navigation/navigation/water/WaterCheckUpdataBean;", "", "command", "", "error_message", "results", "Lcom/ciot/navigation/navigation/water/Results;", "status", "type", "uuid", "isUpdataing", "", "(Ljava/lang/String;Ljava/lang/String;Lcom/ciot/navigation/navigation/water/Results;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getCommand", "()Ljava/lang/String;", "getError_message", "()Z", "setUpdataing", "(Z)V", "getResults", "()Lcom/ciot/navigation/navigation/water/Results;", "getStatus", "getType", "getUuid", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: WaterCheckUpdataBean.kt */
public final class WaterCheckUpdataBean {
    private final String command;
    private final String error_message;
    private boolean isUpdataing;
    private final Results results;
    private final String status;
    private final String type;
    private final String uuid;

    public static /* synthetic */ WaterCheckUpdataBean copy$default(WaterCheckUpdataBean waterCheckUpdataBean, String str, String str2, Results results2, String str3, String str4, String str5, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = waterCheckUpdataBean.command;
        }
        if ((i & 2) != 0) {
            str2 = waterCheckUpdataBean.error_message;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            results2 = waterCheckUpdataBean.results;
        }
        Results results3 = results2;
        if ((i & 8) != 0) {
            str3 = waterCheckUpdataBean.status;
        }
        String str7 = str3;
        if ((i & 16) != 0) {
            str4 = waterCheckUpdataBean.type;
        }
        String str8 = str4;
        if ((i & 32) != 0) {
            str5 = waterCheckUpdataBean.uuid;
        }
        String str9 = str5;
        if ((i & 64) != 0) {
            z = waterCheckUpdataBean.isUpdataing;
        }
        return waterCheckUpdataBean.copy(str, str6, results3, str7, str8, str9, z);
    }

    public final String component1() {
        return this.command;
    }

    public final String component2() {
        return this.error_message;
    }

    public final Results component3() {
        return this.results;
    }

    public final String component4() {
        return this.status;
    }

    public final String component5() {
        return this.type;
    }

    public final String component6() {
        return this.uuid;
    }

    public final boolean component7() {
        return this.isUpdataing;
    }

    public final WaterCheckUpdataBean copy(String str, String str2, Results results2, String str3, String str4, String str5, boolean z) {
        Intrinsics.checkNotNullParameter(str, "command");
        Intrinsics.checkNotNullParameter(str2, "error_message");
        Intrinsics.checkNotNullParameter(results2, "results");
        Intrinsics.checkNotNullParameter(str3, NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkNotNullParameter(str4, "type");
        Intrinsics.checkNotNullParameter(str5, "uuid");
        return new WaterCheckUpdataBean(str, str2, results2, str3, str4, str5, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WaterCheckUpdataBean)) {
            return false;
        }
        WaterCheckUpdataBean waterCheckUpdataBean = (WaterCheckUpdataBean) obj;
        return Intrinsics.areEqual((Object) this.command, (Object) waterCheckUpdataBean.command) && Intrinsics.areEqual((Object) this.error_message, (Object) waterCheckUpdataBean.error_message) && Intrinsics.areEqual((Object) this.results, (Object) waterCheckUpdataBean.results) && Intrinsics.areEqual((Object) this.status, (Object) waterCheckUpdataBean.status) && Intrinsics.areEqual((Object) this.type, (Object) waterCheckUpdataBean.type) && Intrinsics.areEqual((Object) this.uuid, (Object) waterCheckUpdataBean.uuid) && this.isUpdataing == waterCheckUpdataBean.isUpdataing;
    }

    public int hashCode() {
        int hashCode = ((((((((((this.command.hashCode() * 31) + this.error_message.hashCode()) * 31) + this.results.hashCode()) * 31) + this.status.hashCode()) * 31) + this.type.hashCode()) * 31) + this.uuid.hashCode()) * 31;
        boolean z = this.isUpdataing;
        if (z) {
            z = true;
        }
        return hashCode + (z ? 1 : 0);
    }

    public String toString() {
        return "WaterCheckUpdataBean(command=" + this.command + ", error_message=" + this.error_message + ", results=" + this.results + ", status=" + this.status + ", type=" + this.type + ", uuid=" + this.uuid + ", isUpdataing=" + this.isUpdataing + ')';
    }

    public WaterCheckUpdataBean(String str, String str2, Results results2, String str3, String str4, String str5, boolean z) {
        Intrinsics.checkNotNullParameter(str, "command");
        Intrinsics.checkNotNullParameter(str2, "error_message");
        Intrinsics.checkNotNullParameter(results2, "results");
        Intrinsics.checkNotNullParameter(str3, NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkNotNullParameter(str4, "type");
        Intrinsics.checkNotNullParameter(str5, "uuid");
        this.command = str;
        this.error_message = str2;
        this.results = results2;
        this.status = str3;
        this.type = str4;
        this.uuid = str5;
        this.isUpdataing = z;
    }

    public final String getCommand() {
        return this.command;
    }

    public final String getError_message() {
        return this.error_message;
    }

    public final Results getResults() {
        return this.results;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getType() {
        return this.type;
    }

    public final String getUuid() {
        return this.uuid;
    }

    public final boolean isUpdataing() {
        return this.isUpdataing;
    }

    public final void setUpdataing(boolean z) {
        this.isUpdataing = z;
    }
}
