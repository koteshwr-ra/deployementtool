package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.b;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* compiled from: BUGLY */
public final class d {
    /* access modifiers changed from: private */
    public static d a;
    private a b;
    private com.tencent.bugly.crashreport.common.info.a c;
    private b d;
    private Context e;

    static /* synthetic */ void a(d dVar) {
        x.c("[ExtraCrashManager] Trying to notify Bugly agents.", new Object[0]);
        try {
            Class<?> cls = Class.forName("com.tencent.bugly.agent.GameAgent");
            dVar.c.getClass();
            z.a(cls, "sdkPackageName", "com.tencent.bugly", (Object) null);
            x.c("[ExtraCrashManager] Bugly game agent has been notified.", new Object[0]);
        } catch (Throwable unused) {
            x.a("[ExtraCrashManager] no game agent", new Object[0]);
        }
    }

    static /* synthetic */ void a(d dVar, Thread thread, int i, String str, String str2, String str3, Map map) {
        String str4;
        String str5;
        d dVar2 = dVar;
        int i2 = i;
        String str6 = str;
        String str7 = str2;
        String str8 = str3;
        Map map2 = map;
        Thread currentThread = thread == null ? Thread.currentThread() : thread;
        int i3 = 5;
        if (i2 == 4) {
            str4 = "Unity";
        } else if (i2 == 5 || i2 == 6) {
            str4 = "Cocos";
        } else if (i2 != 8) {
            x.d("[ExtraCrashManager] Unknown extra crash type: %d", Integer.valueOf(i));
            return;
        } else {
            str4 = "H5";
        }
        x.e("[ExtraCrashManager] %s Crash Happen", str4);
        if (!dVar2.b.b()) {
            x.d("[ExtraCrashManager] There is no remote strategy, but still store it.", new Object[0]);
        }
        StrategyBean c2 = dVar2.b.c();
        if (!c2.e) {
            if (dVar2.b.b()) {
                x.e("[ExtraCrashManager] Crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
                String a2 = z.a();
                String str9 = dVar2.c.d;
                String name = currentThread.getName();
                b.a(str4, a2, str9, name, str6 + StringUtils.LF + str7 + StringUtils.LF + str8, (CrashDetailBean) null);
                x.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                return;
            }
        }
        if (i2 == 5 || i2 == 6) {
            try {
                if (!c2.j) {
                    x.e("[ExtraCrashManager] %s report is disabled.", str4);
                    return;
                }
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
                return;
            } finally {
                x.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
            }
        } else if (i2 == 8) {
            if (!c2.k) {
                x.e("[ExtraCrashManager] %s report is disabled.", str4);
                x.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                return;
            }
        }
        if (i2 != 8) {
            i3 = i2;
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.C = b.j();
        crashDetailBean.D = b.h();
        crashDetailBean.E = b.l();
        crashDetailBean.F = dVar2.c.o();
        crashDetailBean.G = dVar2.c.n();
        crashDetailBean.H = dVar2.c.p();
        crashDetailBean.w = z.a(dVar2.e, c.e, (String) null);
        crashDetailBean.b = i3;
        crashDetailBean.e = dVar2.c.h();
        crashDetailBean.f = dVar2.c.k;
        crashDetailBean.g = dVar2.c.u();
        crashDetailBean.m = dVar2.c.g();
        crashDetailBean.n = str6;
        crashDetailBean.o = str7;
        String str10 = "";
        if (str8 != null) {
            String[] split = str8.split(StringUtils.LF);
            if (split.length > 0) {
                str10 = split[0];
            }
            str5 = str8;
        } else {
            str5 = str10;
        }
        crashDetailBean.p = str10;
        crashDetailBean.q = str5;
        crashDetailBean.r = System.currentTimeMillis();
        crashDetailBean.u = z.a(crashDetailBean.q.getBytes());
        crashDetailBean.z = z.a(c.f, false);
        crashDetailBean.A = dVar2.c.d;
        crashDetailBean.B = currentThread.getName() + "(" + currentThread.getId() + ")";
        crashDetailBean.I = dVar2.c.w();
        crashDetailBean.h = dVar2.c.t();
        crashDetailBean.M = dVar2.c.a;
        crashDetailBean.N = dVar2.c.a();
        if (!c.a().o()) {
            dVar2.d.d(crashDetailBean);
        }
        crashDetailBean.Q = dVar2.c.D();
        crashDetailBean.R = dVar2.c.E();
        crashDetailBean.S = dVar2.c.x();
        crashDetailBean.T = dVar2.c.C();
        crashDetailBean.y = y.a();
        if (crashDetailBean.O == null) {
            crashDetailBean.O = new LinkedHashMap();
        }
        if (map2 != null) {
            crashDetailBean.O.putAll(map2);
        }
        String a3 = z.a();
        String str11 = dVar2.c.d;
        String name2 = currentThread.getName();
        b.a(str4, a3, str11, name2, str6 + StringUtils.LF + str7 + StringUtils.LF + str8, crashDetailBean);
        if (!dVar2.d.a(crashDetailBean)) {
            dVar2.d.a(crashDetailBean, 3000, false);
        }
        x.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
    }

    private d(Context context) {
        c a2 = c.a();
        if (a2 != null) {
            this.b = a.a();
            this.c = com.tencent.bugly.crashreport.common.info.a.a(context);
            this.d = a2.p;
            this.e = context;
            w.a().a(new Runnable() {
                public final void run() {
                    d.a(d.this);
                }
            });
        }
    }

    public static d a(Context context) {
        if (a == null) {
            a = new d(context);
        }
        return a;
    }

    public static void a(Thread thread, int i, String str, String str2, String str3, Map<String, String> map) {
        final Thread thread2 = thread;
        final int i2 = i;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final Map<String, String> map2 = map;
        w.a().a(new Runnable() {
            public final void run() {
                try {
                    if (d.a == null) {
                        x.e("[ExtraCrashManager] Extra crash manager has not been initialized.", new Object[0]);
                    } else {
                        d.a(d.a, thread2, i2, str4, str5, str6, map2);
                    }
                } catch (Throwable th) {
                    if (!x.b(th)) {
                        th.printStackTrace();
                    }
                    x.e("[ExtraCrashManager] Crash error %s %s %s", str4, str5, str6);
                }
            }
        });
    }
}
