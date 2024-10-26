package com.ciot.navigation.navigation.water;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/ciot/navigation/navigation/water/Results;", "", "enable_update", "", "version_current", "", "version_latest", "(ZLjava/lang/String;Ljava/lang/String;)V", "getEnable_update", "()Z", "getVersion_current", "()Ljava/lang/String;", "getVersion_latest", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: WaterCheckUpdataBean.kt */
public final class Results {
    private final boolean enable_update;
    private final String version_current;
    private final String version_latest;

    public static /* synthetic */ Results copy$default(Results results, boolean z, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = results.enable_update;
        }
        if ((i & 2) != 0) {
            str = results.version_current;
        }
        if ((i & 4) != 0) {
            str2 = results.version_latest;
        }
        return results.copy(z, str, str2);
    }

    public final boolean component1() {
        return this.enable_update;
    }

    public final String component2() {
        return this.version_current;
    }

    public final String component3() {
        return this.version_latest;
    }

    public final Results copy(boolean z, String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "version_current");
        Intrinsics.checkNotNullParameter(str2, "version_latest");
        return new Results(z, str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Results)) {
            return false;
        }
        Results results = (Results) obj;
        return this.enable_update == results.enable_update && Intrinsics.areEqual((Object) this.version_current, (Object) results.version_current) && Intrinsics.areEqual((Object) this.version_latest, (Object) results.version_latest);
    }

    public int hashCode() {
        boolean z = this.enable_update;
        if (z) {
            z = true;
        }
        return ((((z ? 1 : 0) * true) + this.version_current.hashCode()) * 31) + this.version_latest.hashCode();
    }

    public String toString() {
        return "Results(enable_update=" + this.enable_update + ", version_current=" + this.version_current + ", version_latest=" + this.version_latest + ')';
    }

    public Results(boolean z, String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "version_current");
        Intrinsics.checkNotNullParameter(str2, "version_latest");
        this.enable_update = z;
        this.version_current = str;
        this.version_latest = str2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Results(boolean z, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? "" : str, str2);
    }

    public final boolean getEnable_update() {
        return this.enable_update;
    }

    public final String getVersion_current() {
        return this.version_current;
    }

    public final String getVersion_latest() {
        return this.version_latest;
    }
}
