package com.ciot.navigation.navigation.water;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003JE\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001e"}, d2 = {"Lcom/ciot/navigation/navigation/water/WaterVersionBean;", "", "command", "", "error_message", "results", "status", "type", "uuid", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCommand", "()Ljava/lang/String;", "getError_message", "getResults", "getStatus", "getType", "getUuid", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: WaterVersionBean.kt */
public final class WaterVersionBean {
    private final String command;
    private final String error_message;
    private final String results;
    private final String status;
    private final String type;
    private final String uuid;

    public static /* synthetic */ WaterVersionBean copy$default(WaterVersionBean waterVersionBean, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = waterVersionBean.command;
        }
        if ((i & 2) != 0) {
            str2 = waterVersionBean.error_message;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = waterVersionBean.results;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = waterVersionBean.status;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = waterVersionBean.type;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = waterVersionBean.uuid;
        }
        return waterVersionBean.copy(str, str7, str8, str9, str10, str6);
    }

    public final String component1() {
        return this.command;
    }

    public final String component2() {
        return this.error_message;
    }

    public final String component3() {
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

    public final WaterVersionBean copy(String str, String str2, String str3, String str4, String str5, String str6) {
        Intrinsics.checkNotNullParameter(str, "command");
        Intrinsics.checkNotNullParameter(str2, "error_message");
        Intrinsics.checkNotNullParameter(str3, "results");
        Intrinsics.checkNotNullParameter(str4, NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkNotNullParameter(str5, "type");
        Intrinsics.checkNotNullParameter(str6, "uuid");
        return new WaterVersionBean(str, str2, str3, str4, str5, str6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WaterVersionBean)) {
            return false;
        }
        WaterVersionBean waterVersionBean = (WaterVersionBean) obj;
        return Intrinsics.areEqual((Object) this.command, (Object) waterVersionBean.command) && Intrinsics.areEqual((Object) this.error_message, (Object) waterVersionBean.error_message) && Intrinsics.areEqual((Object) this.results, (Object) waterVersionBean.results) && Intrinsics.areEqual((Object) this.status, (Object) waterVersionBean.status) && Intrinsics.areEqual((Object) this.type, (Object) waterVersionBean.type) && Intrinsics.areEqual((Object) this.uuid, (Object) waterVersionBean.uuid);
    }

    public int hashCode() {
        return (((((((((this.command.hashCode() * 31) + this.error_message.hashCode()) * 31) + this.results.hashCode()) * 31) + this.status.hashCode()) * 31) + this.type.hashCode()) * 31) + this.uuid.hashCode();
    }

    public String toString() {
        return "WaterVersionBean(command=" + this.command + ", error_message=" + this.error_message + ", results=" + this.results + ", status=" + this.status + ", type=" + this.type + ", uuid=" + this.uuid + ')';
    }

    public WaterVersionBean(String str, String str2, String str3, String str4, String str5, String str6) {
        Intrinsics.checkNotNullParameter(str, "command");
        Intrinsics.checkNotNullParameter(str2, "error_message");
        Intrinsics.checkNotNullParameter(str3, "results");
        Intrinsics.checkNotNullParameter(str4, NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkNotNullParameter(str5, "type");
        Intrinsics.checkNotNullParameter(str6, "uuid");
        this.command = str;
        this.error_message = str2;
        this.results = str3;
        this.status = str4;
        this.type = str5;
        this.uuid = str6;
    }

    public final String getCommand() {
        return this.command;
    }

    public final String getError_message() {
        return this.error_message;
    }

    public final String getResults() {
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
