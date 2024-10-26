package com.ciot.networklib.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/ciot/networklib/bean/Md5Bean;", "", "fmd5", "", "(Ljava/lang/String;)V", "getFmd5", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "library-networkLib_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: Md5Bean.kt */
public final class Md5Bean {
    private final String fmd5;

    public static /* synthetic */ Md5Bean copy$default(Md5Bean md5Bean, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = md5Bean.fmd5;
        }
        return md5Bean.copy(str);
    }

    public final String component1() {
        return this.fmd5;
    }

    public final Md5Bean copy(String str) {
        Intrinsics.checkNotNullParameter(str, "fmd5");
        return new Md5Bean(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Md5Bean) && Intrinsics.areEqual((Object) this.fmd5, (Object) ((Md5Bean) obj).fmd5);
    }

    public int hashCode() {
        return this.fmd5.hashCode();
    }

    public String toString() {
        return "Md5Bean(fmd5=" + this.fmd5 + ')';
    }

    public Md5Bean(String str) {
        Intrinsics.checkNotNullParameter(str, "fmd5");
        this.fmd5 = str;
    }

    public final String getFmd5() {
        return this.fmd5;
    }
}
