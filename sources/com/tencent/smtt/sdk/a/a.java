package com.tencent.smtt.sdk.a;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class a {
    public static String a(long j) {
        return String.format(Locale.getDefault(), "%d(%s)", new Object[]{Long.valueOf(j), new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.getDefault()).format(new Date(j))});
    }
}
