package com.ciot.base.util;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"keepDecim", "", "", "num", "", "library-base_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* compiled from: CommonExts.kt */
public final class CommonExtsKt {
    public static final String keepDecim(double d, int i) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%." + i + 'f', Arrays.copyOf(new Object[]{Double.valueOf(d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }
}
