package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.interfaces.IX5DateSorter;

public class DateSorter {
    public static int DAY_COUNT = 5;
    private android.webkit.DateSorter a;
    private IX5DateSorter b;

    static {
        boolean a2 = a();
    }

    public DateSorter(Context context) {
        w a2 = w.a();
        if (a2 == null || !a2.b()) {
            this.a = new android.webkit.DateSorter(context);
        } else {
            this.b = a2.c().h(context);
        }
    }

    private static boolean a() {
        w a2 = w.a();
        return a2 != null && a2.b();
    }

    public long getBoundary(int i) {
        w a2 = w.a();
        return (a2 == null || !a2.b()) ? this.a.getBoundary(i) : this.b.getBoundary(i);
    }

    public int getIndex(long j) {
        w a2 = w.a();
        return (a2 == null || !a2.b()) ? this.a.getIndex(j) : this.b.getIndex(j);
    }

    public String getLabel(int i) {
        w a2 = w.a();
        return (a2 == null || !a2.b()) ? this.a.getLabel(i) : this.b.getLabel(i);
    }
}
