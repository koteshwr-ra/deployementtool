package com.tencent.smtt.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.sdk.a.a;
import com.tencent.smtt.sdk.a.b;
import com.tencent.smtt.sdk.a.c;
import com.tencent.smtt.sdk.a.e;
import com.tencent.smtt.sdk.a.g;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.m;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.time.DateUtils;

public class d {
    private static String a = "EmergencyManager";
    /* access modifiers changed from: private */
    public static int b = 0;
    private static int c = 1;
    /* access modifiers changed from: private */
    public static int d = 2;
    /* access modifiers changed from: private */
    public static int e = 3;
    private static int f = 4;
    private static int g = 5;
    private static d h;
    private long i = 60000;
    private long j = DateUtils.MILLIS_PER_DAY;
    private boolean k = false;

    private d() {
    }

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            if (h == null) {
                h = new d();
            }
            dVar = h;
        }
        return dVar;
    }

    /* access modifiers changed from: private */
    public void a(Context context, int i2, List<b> list) {
        String str;
        StringBuilder sb;
        String a2;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        g a3 = g.a();
        List<String> a4 = a3.a(context, "emergence_ids");
        HashSet hashSet = new HashSet();
        if (a4 != null && !a4.isEmpty()) {
            for (String a5 : a4) {
                String[] a6 = g.a(a5);
                if (a6 != null && a6.length == 4) {
                    hashSet.add(Integer.valueOf(Integer.parseInt(a6[0])));
                }
            }
        }
        for (b next : list) {
            int b2 = next.b();
            int a7 = next.a();
            if (hashSet.contains(Integer.valueOf(a7))) {
                str = a;
                sb = new StringBuilder();
                sb.append("Command has been executed: ");
                sb.append(next.toString());
                a2 = ", ignored";
            } else if (next.e()) {
                str = a;
                sb = new StringBuilder();
                sb.append("Command is out of date: ");
                sb.append(next.toString());
                sb.append(", now: ");
                a2 = a.a(System.currentTimeMillis());
            } else {
                linkedHashMap.put(Integer.valueOf(b2), next.c());
                a3.a(context, "emergence_ids", g.a(new String[]{String.valueOf(a7), String.valueOf(next.b()), String.valueOf(next.c()), String.valueOf(next.d())}));
            }
            sb.append(a2);
            TbsLog.d(str, sb.toString());
        }
        a(context, Integer.valueOf(i2), (Map<Integer, String>) linkedHashMap);
    }

    private void b(final Context context) {
        String[] a2;
        c cVar = new c();
        cVar.a(com.tencent.smtt.utils.b.a(context));
        cVar.b(com.tencent.smtt.utils.b.c(context));
        cVar.a(Integer.valueOf(com.tencent.smtt.utils.b.b(context)));
        cVar.c(com.tencent.smtt.utils.b.a());
        cVar.d("x5webview");
        cVar.b(Integer.valueOf(QbSdk.getTbsSdkVersion()));
        cVar.c(Integer.valueOf(QbSdk.getTbsVersion(context)));
        ArrayList arrayList = new ArrayList();
        for (String next : g.a().a(context, "emergence_ids")) {
            try {
                if (!TextUtils.isEmpty(next) && (a2 = g.a(next)) != null && a2.length == 4) {
                    int parseInt = Integer.parseInt(a2[0]);
                    if (System.currentTimeMillis() < Long.parseLong(a2[3])) {
                        arrayList.add(Integer.valueOf(parseInt));
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        cVar.a((List<Integer>) arrayList);
        new e(context, m.a(context).g(), cVar.a()).a((e.a) new e.a() {
            public void a(String str) {
                d dVar;
                ArrayList arrayList;
                int i;
                Context context;
                com.tencent.smtt.sdk.a.d a2 = com.tencent.smtt.sdk.a.d.a(str);
                if (a2 == null || a2.a() != 0) {
                    dVar = d.this;
                    context = context;
                    i = d.e;
                    arrayList = new ArrayList();
                } else {
                    g.a().a(context, "emergence_req_interval", a2.b());
                    List<b> c = a2.c();
                    if (c != null) {
                        d.this.a(context, d.b, c);
                        return;
                    }
                    dVar = d.this;
                    context = context;
                    i = d.d;
                    arrayList = new ArrayList();
                }
                dVar.a(context, i, (List<b>) arrayList);
            }
        });
    }

    public void a(Context context) {
        if (!this.k) {
            this.k = true;
            g a2 = g.a();
            if (!a2.b()) {
                a2.a(context);
                try {
                    long b2 = g.a().b(context, "emergence_timestamp");
                    long b3 = g.a().b(context, "emergence_req_interval");
                    long currentTimeMillis = System.currentTimeMillis();
                    long j2 = currentTimeMillis - b2;
                    long min = Math.min(Math.max(this.i, b3), this.j);
                    if (j2 > min) {
                        String str = a;
                        TbsLog.d(str, "Emergency configuration is out of date, attempt to query again, " + (j2 / 1000) + " seconds has past");
                        g.a().a(context, "emergence_timestamp", currentTimeMillis);
                        b(context);
                    } else {
                        a(context, c, (List<b>) new ArrayList());
                        String str2 = a;
                        TbsLog.d(str2, "Emergency configuration is up to date, " + (j2 / 1000) + " seconds has past, need " + (Math.abs(j2 - min) / 1000) + " more seconds for an another request");
                    }
                } catch (Exception e2) {
                    a(context, g, (List<b>) new ArrayList());
                    String str3 = a;
                    TbsLog.d(str3, "Unexpected exception happened when query emergency configuration: " + e2.getMessage());
                } catch (Throwable th) {
                    g.a().c();
                    throw th;
                }
                g.a().c();
                return;
            }
            a(context, f, (List<b>) new ArrayList());
        }
    }

    public void a(Context context, Integer num, Map<Integer, String> map) {
        u a2;
        c.a().b(context);
        TbsLog.e(a, "Dispatch emergency commands on tbs extension");
        QbSdk.a(context, num, map);
        f a3 = f.a(true);
        if (a3 != null && (a2 = a3.a()) != null) {
            DexLoader b2 = a2.b();
            if (b2 != null) {
                TbsLog.e(a, "Dispatch emergency commands on tbs shell");
                b2.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "dispatchEmergencyCommand", new Class[]{Integer.class, Map.class}, num, map);
                return;
            }
            TbsLog.e(a, "Dex loader is null, cancel commands dispatching of tbs shell");
        }
    }
}
