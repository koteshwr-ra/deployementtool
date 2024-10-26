package com.ciot.navigation.navigation.water;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006 "}, d2 = {"Lcom/ciot/navigation/navigation/water/WaterDiagnosisResultBean;", "", "command", "", "errorMessage", "results", "Lcom/ciot/navigation/navigation/water/Results1;", "status", "type", "uuid", "(Ljava/lang/String;Ljava/lang/String;Lcom/ciot/navigation/navigation/water/Results1;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCommand", "()Ljava/lang/String;", "getErrorMessage", "getResults", "()Lcom/ciot/navigation/navigation/water/Results1;", "getStatus", "getType", "getUuid", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: WaterDiagnosisResultBean.kt */
public final class WaterDiagnosisResultBean {
    @SerializedName("command")
    private final String command;
    @SerializedName("error_message")
    private final String errorMessage;
    @SerializedName("results")
    private final Results1 results;
    @SerializedName("status")
    private final String status;
    @SerializedName("type")
    private final String type;
    @SerializedName("uuid")
    private final String uuid;

    public static /* synthetic */ WaterDiagnosisResultBean copy$default(WaterDiagnosisResultBean waterDiagnosisResultBean, String str, String str2, Results1 results1, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = waterDiagnosisResultBean.command;
        }
        if ((i & 2) != 0) {
            str2 = waterDiagnosisResultBean.errorMessage;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            results1 = waterDiagnosisResultBean.results;
        }
        Results1 results12 = results1;
        if ((i & 8) != 0) {
            str3 = waterDiagnosisResultBean.status;
        }
        String str7 = str3;
        if ((i & 16) != 0) {
            str4 = waterDiagnosisResultBean.type;
        }
        String str8 = str4;
        if ((i & 32) != 0) {
            str5 = waterDiagnosisResultBean.uuid;
        }
        return waterDiagnosisResultBean.copy(str, str6, results12, str7, str8, str5);
    }

    public final String component1() {
        return this.command;
    }

    public final String component2() {
        return this.errorMessage;
    }

    public final Results1 component3() {
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

    public final WaterDiagnosisResultBean copy(String str, String str2, Results1 results1, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "command");
        Intrinsics.checkNotNullParameter(str2, "errorMessage");
        Intrinsics.checkNotNullParameter(results1, "results");
        Intrinsics.checkNotNullParameter(str3, NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkNotNullParameter(str4, "type");
        Intrinsics.checkNotNullParameter(str5, "uuid");
        return new WaterDiagnosisResultBean(str, str2, results1, str3, str4, str5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WaterDiagnosisResultBean)) {
            return false;
        }
        WaterDiagnosisResultBean waterDiagnosisResultBean = (WaterDiagnosisResultBean) obj;
        return Intrinsics.areEqual((Object) this.command, (Object) waterDiagnosisResultBean.command) && Intrinsics.areEqual((Object) this.errorMessage, (Object) waterDiagnosisResultBean.errorMessage) && Intrinsics.areEqual((Object) this.results, (Object) waterDiagnosisResultBean.results) && Intrinsics.areEqual((Object) this.status, (Object) waterDiagnosisResultBean.status) && Intrinsics.areEqual((Object) this.type, (Object) waterDiagnosisResultBean.type) && Intrinsics.areEqual((Object) this.uuid, (Object) waterDiagnosisResultBean.uuid);
    }

    public int hashCode() {
        return (((((((((this.command.hashCode() * 31) + this.errorMessage.hashCode()) * 31) + this.results.hashCode()) * 31) + this.status.hashCode()) * 31) + this.type.hashCode()) * 31) + this.uuid.hashCode();
    }

    public String toString() {
        return "WaterDiagnosisResultBean(command=" + this.command + ", errorMessage=" + this.errorMessage + ", results=" + this.results + ", status=" + this.status + ", type=" + this.type + ", uuid=" + this.uuid + ')';
    }

    public WaterDiagnosisResultBean(String str, String str2, Results1 results1, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "command");
        Intrinsics.checkNotNullParameter(str2, "errorMessage");
        Intrinsics.checkNotNullParameter(results1, "results");
        Intrinsics.checkNotNullParameter(str3, NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkNotNullParameter(str4, "type");
        Intrinsics.checkNotNullParameter(str5, "uuid");
        this.command = str;
        this.errorMessage = str2;
        this.results = results1;
        this.status = str3;
        this.type = str4;
        this.uuid = str5;
    }

    public final String getCommand() {
        return this.command;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final Results1 getResults() {
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
}
