package com.ciot.navigation.navigation.water;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/ciot/navigation/navigation/water/DNS8888;", "", "status", "", "successCount", "", "timestamp", "totalCount", "(ZIII)V", "getStatus", "()Z", "getSuccessCount", "()I", "getTimestamp", "getTotalCount", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "library-navigation_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: WaterDiagnosisResultBean.kt */
public final class DNS8888 {
    @SerializedName("status")
    private final boolean status;
    @SerializedName("success_count")
    private final int successCount;
    @SerializedName("timestamp")
    private final int timestamp;
    @SerializedName("total_count")
    private final int totalCount;

    public static /* synthetic */ DNS8888 copy$default(DNS8888 dns8888, boolean z, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            z = dns8888.status;
        }
        if ((i4 & 2) != 0) {
            i = dns8888.successCount;
        }
        if ((i4 & 4) != 0) {
            i2 = dns8888.timestamp;
        }
        if ((i4 & 8) != 0) {
            i3 = dns8888.totalCount;
        }
        return dns8888.copy(z, i, i2, i3);
    }

    public final boolean component1() {
        return this.status;
    }

    public final int component2() {
        return this.successCount;
    }

    public final int component3() {
        return this.timestamp;
    }

    public final int component4() {
        return this.totalCount;
    }

    public final DNS8888 copy(boolean z, int i, int i2, int i3) {
        return new DNS8888(z, i, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DNS8888)) {
            return false;
        }
        DNS8888 dns8888 = (DNS8888) obj;
        return this.status == dns8888.status && this.successCount == dns8888.successCount && this.timestamp == dns8888.timestamp && this.totalCount == dns8888.totalCount;
    }

    public int hashCode() {
        boolean z = this.status;
        if (z) {
            z = true;
        }
        return ((((((z ? 1 : 0) * true) + this.successCount) * 31) + this.timestamp) * 31) + this.totalCount;
    }

    public String toString() {
        return "DNS8888(status=" + this.status + ", successCount=" + this.successCount + ", timestamp=" + this.timestamp + ", totalCount=" + this.totalCount + ')';
    }

    public DNS8888(boolean z, int i, int i2, int i3) {
        this.status = z;
        this.successCount = i;
        this.timestamp = i2;
        this.totalCount = i3;
    }

    public final boolean getStatus() {
        return this.status;
    }

    public final int getSuccessCount() {
        return this.successCount;
    }

    public final int getTimestamp() {
        return this.timestamp;
    }

    public final int getTotalCount() {
        return this.totalCount;
    }
}
