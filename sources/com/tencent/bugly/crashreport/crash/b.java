package com.tencent.bugly.crashreport.crash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcelable;
import android.text.TextUtils;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.proguard.ah;
import com.tencent.bugly.proguard.aj;
import com.tencent.bugly.proguard.ak;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.r;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.spi.Configurator;

/* compiled from: BUGLY */
public final class b {
    private static int a;
    private Context b;
    private u c;
    private p d;
    private a e;
    private o f;
    private BuglyStrategy.a g;

    public b(int i, Context context, u uVar, p pVar, a aVar, BuglyStrategy.a aVar2, o oVar) {
        a = i;
        this.b = context;
        this.c = uVar;
        this.d = pVar;
        this.e = aVar;
        this.g = aVar2;
        this.f = oVar;
    }

    private static List<a> a(List<a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (a next : list) {
            if (next.d && next.b <= currentTimeMillis - DateUtils.MILLIS_PER_DAY) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private CrashDetailBean a(List<a> list, CrashDetailBean crashDetailBean) {
        List<CrashDetailBean> b2;
        String[] split;
        if (list == null || list.size() == 0) {
            return crashDetailBean;
        }
        CrashDetailBean crashDetailBean2 = null;
        ArrayList arrayList = new ArrayList(10);
        for (a next : list) {
            if (next.e) {
                arrayList.add(next);
            }
        }
        if (arrayList.size() > 0 && (b2 = b((List<a>) arrayList)) != null && b2.size() > 0) {
            Collections.sort(b2);
            for (int i = 0; i < b2.size(); i++) {
                CrashDetailBean crashDetailBean3 = b2.get(i);
                if (i == 0) {
                    crashDetailBean2 = crashDetailBean3;
                } else if (!(crashDetailBean3.s == null || (split = crashDetailBean3.s.split(StringUtils.LF)) == null)) {
                    for (String str : split) {
                        if (!crashDetailBean2.s.contains(str)) {
                            crashDetailBean2.t++;
                            crashDetailBean2.s += str + StringUtils.LF;
                        }
                    }
                }
            }
        }
        if (crashDetailBean2 == null) {
            crashDetailBean.j = true;
            crashDetailBean.t = 0;
            crashDetailBean.s = "";
            crashDetailBean2 = crashDetailBean;
        }
        for (a next2 : list) {
            if (!next2.e && !next2.d) {
                String str2 = crashDetailBean2.s;
                StringBuilder sb = new StringBuilder();
                sb.append(next2.b);
                if (!str2.contains(sb.toString())) {
                    crashDetailBean2.t++;
                    crashDetailBean2.s += next2.b + StringUtils.LF;
                }
            }
        }
        if (crashDetailBean2.r != crashDetailBean.r) {
            String str3 = crashDetailBean2.s;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(crashDetailBean.r);
            if (!str3.contains(sb2.toString())) {
                crashDetailBean2.t++;
                crashDetailBean2.s += crashDetailBean.r + StringUtils.LF;
            }
        }
        return crashDetailBean2;
    }

    public final boolean a(CrashDetailBean crashDetailBean) {
        return b(crashDetailBean);
    }

    public final boolean b(CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return true;
        }
        if (c.n != null && !c.n.isEmpty()) {
            x.c("Crash filter for crash stack is: %s", c.n);
            if (crashDetailBean.q.contains(c.n)) {
                x.d("This crash contains the filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        if (c.o != null && !c.o.isEmpty()) {
            x.c("Crash regular filter for crash stack is: %s", c.o);
            if (Pattern.compile(c.o).matcher(crashDetailBean.q).find()) {
                x.d("This crash matches the regular filter string set. It will not be record and upload.", new Object[0]);
                return true;
            }
        }
        if (crashDetailBean.b != 2) {
            r rVar = new r();
            rVar.b = 1;
            rVar.c = crashDetailBean.A;
            rVar.d = crashDetailBean.B;
            rVar.e = crashDetailBean.r;
            this.d.b(1);
            this.d.a(rVar);
            x.b("[crash] a crash occur, handling...", new Object[0]);
        } else {
            x.b("[crash] a caught exception occur, handling...", new Object[0]);
        }
        List<a> b2 = b();
        ArrayList arrayList = null;
        if (b2 != null && b2.size() > 0) {
            arrayList = new ArrayList(10);
            ArrayList<a> arrayList2 = new ArrayList<>(10);
            arrayList.addAll(a(b2));
            b2.removeAll(arrayList);
            if (((long) b2.size()) > 20) {
                StringBuilder sb = new StringBuilder();
                sb.append("_id in ");
                sb.append("(");
                sb.append("SELECT _id");
                sb.append(" FROM t_cr");
                sb.append(" order by _id");
                sb.append(" limit 5");
                sb.append(")");
                String sb2 = sb.toString();
                sb.setLength(0);
                try {
                    x.c("deleted first record %s data %d", "t_cr", Integer.valueOf(p.a().a("t_cr", sb2, (String[]) null, (o) null, true)));
                } catch (Throwable th) {
                    if (!x.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
            if (!com.tencent.bugly.b.c && c.d) {
                boolean z = false;
                for (a next : b2) {
                    if (crashDetailBean.u.equals(next.c)) {
                        if (next.e) {
                            z = true;
                        }
                        arrayList2.add(next);
                    }
                }
                if (z || arrayList2.size() >= c.c) {
                    x.a("same crash occur too much do merged!", new Object[0]);
                    CrashDetailBean a2 = a((List<a>) arrayList2, crashDetailBean);
                    for (a aVar : arrayList2) {
                        if (aVar.a != a2.a) {
                            arrayList.add(aVar);
                        }
                    }
                    e(a2);
                    c((List<a>) arrayList);
                    x.b("[crash] save crash success. For this device crash many times, it will not upload crashes immediately", new Object[0]);
                    return true;
                }
            }
        }
        e(crashDetailBean);
        if (arrayList != null && !arrayList.isEmpty()) {
            c((List<a>) arrayList);
        }
        x.b("[crash] save crash success", new Object[0]);
        return false;
    }

    public final List<CrashDetailBean> a() {
        StrategyBean c2 = a.a().c();
        if (c2 == null) {
            x.d("have not synced remote!", new Object[0]);
            return null;
        } else if (!c2.e) {
            x.d("Crashreport remote closed, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            x.b("[init] WARNING! Crashreport closed by server, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            long b2 = z.b();
            List<a> b3 = b();
            x.c("Size of crash list loaded from DB: %s", Integer.valueOf(b3.size()));
            if (b3 == null || b3.size() <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(a(b3));
            b3.removeAll(arrayList);
            Iterator<a> it = b3.iterator();
            while (it.hasNext()) {
                a next = it.next();
                if (next.b < b2 - c.g) {
                    it.remove();
                    arrayList.add(next);
                } else if (next.d) {
                    if (next.b >= currentTimeMillis - DateUtils.MILLIS_PER_DAY) {
                        it.remove();
                    } else if (!next.e) {
                        it.remove();
                        arrayList.add(next);
                    }
                } else if (((long) next.f) >= 3 && next.b < currentTimeMillis - DateUtils.MILLIS_PER_DAY) {
                    it.remove();
                    arrayList.add(next);
                }
            }
            if (arrayList.size() > 0) {
                c((List<a>) arrayList);
            }
            ArrayList arrayList2 = new ArrayList();
            List<CrashDetailBean> b4 = b(b3);
            if (b4 != null && b4.size() > 0) {
                String str = com.tencent.bugly.crashreport.common.info.a.b().k;
                Iterator<CrashDetailBean> it2 = b4.iterator();
                while (it2.hasNext()) {
                    CrashDetailBean next2 = it2.next();
                    if (!str.equals(next2.f)) {
                        it2.remove();
                        arrayList2.add(next2);
                    }
                }
            }
            if (arrayList2.size() > 0) {
                d((List<CrashDetailBean>) arrayList2);
            }
            return b4;
        }
    }

    public final void c(CrashDetailBean crashDetailBean) {
        int i = crashDetailBean.b;
        if (i != 0) {
            if (i != 1) {
                if (i == 3 && !c.a().q()) {
                    return;
                }
            } else if (!c.a().p()) {
                return;
            }
        } else if (!c.a().p()) {
            return;
        }
        if (this.f != null) {
            x.c("Calling 'onCrashHandleEnd' of RQD crash listener.", new Object[0]);
            int i2 = crashDetailBean.b;
        }
    }

    public final void a(CrashDetailBean crashDetailBean, long j, boolean z) {
        if (c.l) {
            x.a("try to upload right now", new Object[0]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(crashDetailBean);
            a(arrayList, 3000, z, crashDetailBean.b == 7, z);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0088 A[Catch:{ all -> 0x00cc }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0090 A[Catch:{ all -> 0x00cc }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(final java.util.List<com.tencent.bugly.crashreport.crash.CrashDetailBean> r15, long r16, boolean r18, boolean r19, boolean r20) {
        /*
            r14 = this;
            r1 = r14
            r0 = r15
            android.content.Context r2 = r1.b
            com.tencent.bugly.crashreport.common.info.a r2 = com.tencent.bugly.crashreport.common.info.a.a((android.content.Context) r2)
            boolean r2 = r2.e
            if (r2 != 0) goto L_0x000d
            return
        L_0x000d:
            com.tencent.bugly.proguard.u r2 = r1.c
            if (r2 != 0) goto L_0x0012
            return
        L_0x0012:
            if (r20 != 0) goto L_0x001d
            int r3 = com.tencent.bugly.crashreport.crash.c.a
            boolean r2 = r2.b((int) r3)
            if (r2 != 0) goto L_0x001d
            return
        L_0x001d:
            com.tencent.bugly.crashreport.common.strategy.a r2 = r1.e
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r2 = r2.c()
            boolean r3 = r2.e
            r4 = 0
            if (r3 != 0) goto L_0x0037
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r2 = "remote report is disable!"
            com.tencent.bugly.proguard.x.d(r2, r0)
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r2 = "[crash] server closed bugly in this app. please check your appid if is correct, and re-install it"
            com.tencent.bugly.proguard.x.b(r2, r0)
            return
        L_0x0037:
            if (r0 == 0) goto L_0x00e4
            int r3 = r15.size()
            if (r3 != 0) goto L_0x0041
            goto L_0x00e4
        L_0x0041:
            java.lang.String r8 = r2.q     // Catch:{ all -> 0x00cc }
            java.lang.String r9 = com.tencent.bugly.crashreport.common.strategy.StrategyBean.b     // Catch:{ all -> 0x00cc }
            android.content.Context r2 = r1.b     // Catch:{ all -> 0x00cc }
            com.tencent.bugly.crashreport.common.info.a r3 = com.tencent.bugly.crashreport.common.info.a.b()     // Catch:{ all -> 0x00cc }
            if (r2 == 0) goto L_0x007e
            if (r0 == 0) goto L_0x007e
            int r5 = r15.size()     // Catch:{ all -> 0x00cc }
            if (r5 == 0) goto L_0x007e
            if (r3 != 0) goto L_0x0058
            goto L_0x007e
        L_0x0058:
            com.tencent.bugly.proguard.al r5 = new com.tencent.bugly.proguard.al     // Catch:{ all -> 0x00cc }
            r5.<init>()     // Catch:{ all -> 0x00cc }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x00cc }
            r6.<init>()     // Catch:{ all -> 0x00cc }
            r5.a = r6     // Catch:{ all -> 0x00cc }
            java.util.Iterator r6 = r15.iterator()     // Catch:{ all -> 0x00cc }
        L_0x0068:
            boolean r7 = r6.hasNext()     // Catch:{ all -> 0x00cc }
            if (r7 == 0) goto L_0x0086
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x00cc }
            com.tencent.bugly.crashreport.crash.CrashDetailBean r7 = (com.tencent.bugly.crashreport.crash.CrashDetailBean) r7     // Catch:{ all -> 0x00cc }
            java.util.ArrayList<com.tencent.bugly.proguard.ak> r10 = r5.a     // Catch:{ all -> 0x00cc }
            com.tencent.bugly.proguard.ak r7 = a((android.content.Context) r2, (com.tencent.bugly.crashreport.crash.CrashDetailBean) r7, (com.tencent.bugly.crashreport.common.info.a) r3)     // Catch:{ all -> 0x00cc }
            r10.add(r7)     // Catch:{ all -> 0x00cc }
            goto L_0x0068
        L_0x007e:
            java.lang.String r2 = "enEXPPkg args == null!"
            java.lang.Object[] r3 = new java.lang.Object[r4]     // Catch:{ all -> 0x00cc }
            com.tencent.bugly.proguard.x.d(r2, r3)     // Catch:{ all -> 0x00cc }
            r5 = 0
        L_0x0086:
            if (r5 != 0) goto L_0x0090
            java.lang.String r0 = "create eupPkg fail!"
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ all -> 0x00cc }
            com.tencent.bugly.proguard.x.d(r0, r2)     // Catch:{ all -> 0x00cc }
            return
        L_0x0090:
            byte[] r2 = com.tencent.bugly.proguard.a.a((com.tencent.bugly.proguard.k) r5)     // Catch:{ all -> 0x00cc }
            if (r2 != 0) goto L_0x009e
            java.lang.String r0 = "send encode fail!"
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ all -> 0x00cc }
            com.tencent.bugly.proguard.x.d(r0, r2)     // Catch:{ all -> 0x00cc }
            return
        L_0x009e:
            android.content.Context r3 = r1.b     // Catch:{ all -> 0x00cc }
            r5 = 830(0x33e, float:1.163E-42)
            com.tencent.bugly.proguard.am r7 = com.tencent.bugly.proguard.a.a(r3, r5, r2)     // Catch:{ all -> 0x00cc }
            if (r7 != 0) goto L_0x00b0
            java.lang.String r0 = "request package is null."
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ all -> 0x00cc }
            com.tencent.bugly.proguard.x.d(r0, r2)     // Catch:{ all -> 0x00cc }
            return
        L_0x00b0:
            com.tencent.bugly.crashreport.crash.b$1 r10 = new com.tencent.bugly.crashreport.crash.b$1     // Catch:{ all -> 0x00cc }
            r10.<init>(r15)     // Catch:{ all -> 0x00cc }
            if (r18 == 0) goto L_0x00c3
            com.tencent.bugly.proguard.u r5 = r1.c     // Catch:{ all -> 0x00cc }
            int r6 = a     // Catch:{ all -> 0x00cc }
            r11 = r16
            r13 = r19
            r5.a(r6, r7, r8, r9, r10, r11, r13)     // Catch:{ all -> 0x00cc }
            goto L_0x00e4
        L_0x00c3:
            com.tencent.bugly.proguard.u r5 = r1.c     // Catch:{ all -> 0x00cc }
            int r6 = a     // Catch:{ all -> 0x00cc }
            r11 = 0
            r5.a(r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x00cc }
            return
        L_0x00cc:
            r0 = move-exception
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r3 = r0.toString()
            r2[r4] = r3
            java.lang.String r3 = "req cr error %s"
            com.tencent.bugly.proguard.x.e(r3, r2)
            boolean r2 = com.tencent.bugly.proguard.x.b(r0)
            if (r2 != 0) goto L_0x00e4
            r0.printStackTrace()
        L_0x00e4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.b.a(java.util.List, long, boolean, boolean, boolean):void");
    }

    public static void a(boolean z, List<CrashDetailBean> list) {
        if (list != null && list.size() > 0) {
            x.c("up finish update state %b", Boolean.valueOf(z));
            for (CrashDetailBean next : list) {
                x.c("pre uid:%s uc:%d re:%b me:%b", next.c, Integer.valueOf(next.l), Boolean.valueOf(next.d), Boolean.valueOf(next.j));
                next.l++;
                next.d = z;
                x.c("set uid:%s uc:%d re:%b me:%b", next.c, Integer.valueOf(next.l), Boolean.valueOf(next.d), Boolean.valueOf(next.j));
            }
            for (CrashDetailBean a2 : list) {
                c.a().a(a2);
            }
            x.c("update state size %d", Integer.valueOf(list.size()));
        }
        if (!z) {
            x.b("[crash] upload fail.", new Object[0]);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ce A[Catch:{ all -> 0x01f0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0175 A[Catch:{ all -> 0x01f0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0183 A[Catch:{ all -> 0x01f0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x019e A[Catch:{ all -> 0x01f0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01cd A[Catch:{ all -> 0x01f0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:88:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void d(com.tencent.bugly.crashreport.crash.CrashDetailBean r13) {
        /*
            r12 = this;
            if (r13 != 0) goto L_0x0003
            return
        L_0x0003:
            com.tencent.bugly.BuglyStrategy$a r0 = r12.g
            if (r0 != 0) goto L_0x000c
            com.tencent.bugly.proguard.o r0 = r12.f
            if (r0 != 0) goto L_0x000c
            return
        L_0x000c:
            r0 = 1
            r1 = 0
            int r2 = r13.b     // Catch:{ all -> 0x01f0 }
            r3 = 2
            switch(r2) {
                case 0: goto L_0x0056;
                case 1: goto L_0x0049;
                case 2: goto L_0x0047;
                case 3: goto L_0x003b;
                case 4: goto L_0x002f;
                case 5: goto L_0x0023;
                case 6: goto L_0x0017;
                case 7: goto L_0x0015;
                default: goto L_0x0014;
            }     // Catch:{ all -> 0x01f0 }
        L_0x0014:
            return
        L_0x0015:
            r2 = 7
            goto L_0x0062
        L_0x0017:
            r2 = 6
            com.tencent.bugly.crashreport.crash.c r4 = com.tencent.bugly.crashreport.crash.c.a()     // Catch:{ all -> 0x01f0 }
            boolean r4 = r4.t()     // Catch:{ all -> 0x01f0 }
            if (r4 != 0) goto L_0x0062
            return
        L_0x0023:
            r2 = 5
            com.tencent.bugly.crashreport.crash.c r4 = com.tencent.bugly.crashreport.crash.c.a()     // Catch:{ all -> 0x01f0 }
            boolean r4 = r4.s()     // Catch:{ all -> 0x01f0 }
            if (r4 != 0) goto L_0x0062
            return
        L_0x002f:
            r2 = 3
            com.tencent.bugly.crashreport.crash.c r4 = com.tencent.bugly.crashreport.crash.c.a()     // Catch:{ all -> 0x01f0 }
            boolean r4 = r4.r()     // Catch:{ all -> 0x01f0 }
            if (r4 != 0) goto L_0x0062
            return
        L_0x003b:
            r2 = 4
            com.tencent.bugly.crashreport.crash.c r4 = com.tencent.bugly.crashreport.crash.c.a()     // Catch:{ all -> 0x01f0 }
            boolean r4 = r4.q()     // Catch:{ all -> 0x01f0 }
            if (r4 != 0) goto L_0x0062
            return
        L_0x0047:
            r2 = 1
            goto L_0x0062
        L_0x0049:
            com.tencent.bugly.crashreport.crash.c r2 = com.tencent.bugly.crashreport.crash.c.a()     // Catch:{ all -> 0x01f0 }
            boolean r2 = r2.p()     // Catch:{ all -> 0x01f0 }
            if (r2 != 0) goto L_0x0054
            return
        L_0x0054:
            r2 = 2
            goto L_0x0062
        L_0x0056:
            com.tencent.bugly.crashreport.crash.c r2 = com.tencent.bugly.crashreport.crash.c.a()     // Catch:{ all -> 0x01f0 }
            boolean r2 = r2.p()     // Catch:{ all -> 0x01f0 }
            if (r2 != 0) goto L_0x0061
            return
        L_0x0061:
            r2 = 0
        L_0x0062:
            int r4 = r13.b     // Catch:{ all -> 0x01f0 }
            java.lang.String r4 = r13.n     // Catch:{ all -> 0x01f0 }
            java.lang.String r4 = r13.p     // Catch:{ all -> 0x01f0 }
            java.lang.String r4 = r13.q     // Catch:{ all -> 0x01f0 }
            long r4 = r13.r     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.o r4 = r12.f     // Catch:{ all -> 0x01f0 }
            r5 = 0
            if (r4 == 0) goto L_0x0092
            java.lang.String r4 = "Calling 'onCrashHandleStart' of RQD crash listener."
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.x.c(r4, r6)     // Catch:{ all -> 0x01f0 }
            java.lang.String r4 = "Calling 'getCrashExtraMessage' of RQD crash listener."
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.x.c(r4, r6)     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.o r4 = r12.f     // Catch:{ all -> 0x01f0 }
            java.lang.String r4 = r4.b()     // Catch:{ all -> 0x01f0 }
            if (r4 == 0) goto L_0x00aa
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ all -> 0x01f0 }
            r6.<init>(r0)     // Catch:{ all -> 0x01f0 }
            java.lang.String r7 = "userData"
            r6.put(r7, r4)     // Catch:{ all -> 0x01f0 }
            goto L_0x00ab
        L_0x0092:
            com.tencent.bugly.BuglyStrategy$a r4 = r12.g     // Catch:{ all -> 0x01f0 }
            if (r4 == 0) goto L_0x00aa
            java.lang.String r4 = "Calling 'onCrashHandleStart' of Bugly crash listener."
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.x.c(r4, r6)     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.BuglyStrategy$a r4 = r12.g     // Catch:{ all -> 0x01f0 }
            java.lang.String r6 = r13.n     // Catch:{ all -> 0x01f0 }
            java.lang.String r7 = r13.o     // Catch:{ all -> 0x01f0 }
            java.lang.String r8 = r13.q     // Catch:{ all -> 0x01f0 }
            java.util.Map r6 = r4.onCrashHandleStart(r2, r6, r7, r8)     // Catch:{ all -> 0x01f0 }
            goto L_0x00ab
        L_0x00aa:
            r6 = r5
        L_0x00ab:
            r4 = 30000(0x7530, float:4.2039E-41)
            if (r6 == 0) goto L_0x016a
            int r7 = r6.size()     // Catch:{ all -> 0x01f0 }
            if (r7 <= 0) goto L_0x016a
            java.util.LinkedHashMap r7 = new java.util.LinkedHashMap     // Catch:{ all -> 0x01f0 }
            int r8 = r6.size()     // Catch:{ all -> 0x01f0 }
            r7.<init>(r8)     // Catch:{ all -> 0x01f0 }
            r13.O = r7     // Catch:{ all -> 0x01f0 }
            java.util.Set r6 = r6.entrySet()     // Catch:{ all -> 0x01f0 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x01f0 }
        L_0x00c8:
            boolean r7 = r6.hasNext()     // Catch:{ all -> 0x01f0 }
            if (r7 == 0) goto L_0x016a
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x01f0 }
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch:{ all -> 0x01f0 }
            java.lang.Object r8 = r7.getKey()     // Catch:{ all -> 0x01f0 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x01f0 }
            boolean r8 = com.tencent.bugly.proguard.z.a((java.lang.String) r8)     // Catch:{ all -> 0x01f0 }
            if (r8 != 0) goto L_0x00c8
            java.lang.Object r8 = r7.getKey()     // Catch:{ all -> 0x01f0 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x01f0 }
            int r9 = r8.length()     // Catch:{ all -> 0x01f0 }
            r10 = 100
            if (r9 <= r10) goto L_0x0101
            java.lang.String r8 = r8.substring(r1, r10)     // Catch:{ all -> 0x01f0 }
            java.lang.String r9 = "setted key length is over limit %d substring to %s"
            java.lang.Object[] r11 = new java.lang.Object[r3]     // Catch:{ all -> 0x01f0 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x01f0 }
            r11[r1] = r10     // Catch:{ all -> 0x01f0 }
            r11[r0] = r8     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.x.d(r9, r11)     // Catch:{ all -> 0x01f0 }
        L_0x0101:
            java.lang.Object r9 = r7.getValue()     // Catch:{ all -> 0x01f0 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x01f0 }
            boolean r9 = com.tencent.bugly.proguard.z.a((java.lang.String) r9)     // Catch:{ all -> 0x01f0 }
            if (r9 != 0) goto L_0x013e
            java.lang.Object r9 = r7.getValue()     // Catch:{ all -> 0x01f0 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x01f0 }
            int r9 = r9.length()     // Catch:{ all -> 0x01f0 }
            if (r9 <= r4) goto L_0x013e
            java.lang.Object r9 = r7.getValue()     // Catch:{ all -> 0x01f0 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x01f0 }
            java.lang.Object r7 = r7.getValue()     // Catch:{ all -> 0x01f0 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x01f0 }
            int r7 = r7.length()     // Catch:{ all -> 0x01f0 }
            int r7 = r7 - r4
            java.lang.String r7 = r9.substring(r7)     // Catch:{ all -> 0x01f0 }
            java.lang.String r9 = "setted %s value length is over limit %d substring"
            java.lang.Object[] r10 = new java.lang.Object[r3]     // Catch:{ all -> 0x01f0 }
            r10[r1] = r8     // Catch:{ all -> 0x01f0 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x01f0 }
            r10[r0] = r11     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.x.d(r9, r10)     // Catch:{ all -> 0x01f0 }
            goto L_0x0150
        L_0x013e:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x01f0 }
            r9.<init>()     // Catch:{ all -> 0x01f0 }
            java.lang.Object r7 = r7.getValue()     // Catch:{ all -> 0x01f0 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x01f0 }
            r9.append(r7)     // Catch:{ all -> 0x01f0 }
            java.lang.String r7 = r9.toString()     // Catch:{ all -> 0x01f0 }
        L_0x0150:
            java.util.Map<java.lang.String, java.lang.String> r9 = r13.O     // Catch:{ all -> 0x01f0 }
            r9.put(r8, r7)     // Catch:{ all -> 0x01f0 }
            java.lang.String r9 = "add setted key %s value size:%d"
            java.lang.Object[] r10 = new java.lang.Object[r3]     // Catch:{ all -> 0x01f0 }
            r10[r1] = r8     // Catch:{ all -> 0x01f0 }
            int r7 = r7.length()     // Catch:{ all -> 0x01f0 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x01f0 }
            r10[r0] = r7     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.x.a(r9, r10)     // Catch:{ all -> 0x01f0 }
            goto L_0x00c8
        L_0x016a:
            java.lang.String r6 = "[crash callback] start user's callback:onCrashHandleStart2GetExtraDatas()"
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.x.a(r6, r7)     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.o r6 = r12.f     // Catch:{ all -> 0x01f0 }
            if (r6 == 0) goto L_0x0183
            java.lang.String r2 = "Calling 'getCrashExtraData' of RQD crash listener."
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.x.c(r2, r5)     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.o r2 = r12.f     // Catch:{ all -> 0x01f0 }
            byte[] r5 = r2.a()     // Catch:{ all -> 0x01f0 }
            goto L_0x019a
        L_0x0183:
            com.tencent.bugly.BuglyStrategy$a r6 = r12.g     // Catch:{ all -> 0x01f0 }
            if (r6 == 0) goto L_0x019a
            java.lang.String r5 = "Calling 'onCrashHandleStart2GetExtraDatas' of Bugly crash listener."
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.x.c(r5, r6)     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.BuglyStrategy$a r5 = r12.g     // Catch:{ all -> 0x01f0 }
            java.lang.String r6 = r13.n     // Catch:{ all -> 0x01f0 }
            java.lang.String r7 = r13.o     // Catch:{ all -> 0x01f0 }
            java.lang.String r8 = r13.q     // Catch:{ all -> 0x01f0 }
            byte[] r5 = r5.onCrashHandleStart2GetExtraDatas(r2, r6, r7, r8)     // Catch:{ all -> 0x01f0 }
        L_0x019a:
            r13.U = r5     // Catch:{ all -> 0x01f0 }
            if (r5 == 0) goto L_0x01c9
            int r2 = r5.length     // Catch:{ all -> 0x01f0 }
            if (r2 <= r4) goto L_0x01bb
            java.lang.String r2 = "extra bytes size %d is over limit %d will drop over part"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x01f0 }
            int r6 = r5.length     // Catch:{ all -> 0x01f0 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x01f0 }
            r3[r1] = r6     // Catch:{ all -> 0x01f0 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x01f0 }
            r3[r0] = r6     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.x.d(r2, r3)     // Catch:{ all -> 0x01f0 }
            byte[] r2 = java.util.Arrays.copyOf(r5, r4)     // Catch:{ all -> 0x01f0 }
            r13.U = r2     // Catch:{ all -> 0x01f0 }
        L_0x01bb:
            java.lang.String r2 = "add extra bytes %d "
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch:{ all -> 0x01f0 }
            int r4 = r5.length     // Catch:{ all -> 0x01f0 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x01f0 }
            r3[r1] = r4     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.x.a(r2, r3)     // Catch:{ all -> 0x01f0 }
        L_0x01c9:
            com.tencent.bugly.proguard.o r2 = r12.f     // Catch:{ all -> 0x01f0 }
            if (r2 == 0) goto L_0x01ef
            java.lang.String r2 = "Calling 'onCrashSaving' of RQD crash listener."
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.x.c(r2, r3)     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.o r2 = r12.f     // Catch:{ all -> 0x01f0 }
            java.lang.String r3 = r13.o     // Catch:{ all -> 0x01f0 }
            java.lang.String r3 = r13.m     // Catch:{ all -> 0x01f0 }
            java.lang.String r3 = r13.e     // Catch:{ all -> 0x01f0 }
            java.lang.String r3 = r13.c     // Catch:{ all -> 0x01f0 }
            java.lang.String r3 = r13.A     // Catch:{ all -> 0x01f0 }
            java.lang.String r13 = r13.B     // Catch:{ all -> 0x01f0 }
            boolean r13 = r2.c()     // Catch:{ all -> 0x01f0 }
            if (r13 != 0) goto L_0x01ef
            java.lang.String r13 = "Crash listener 'onCrashSaving' return 'false' thus will not handle this crash."
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x01f0 }
            com.tencent.bugly.proguard.x.d(r13, r2)     // Catch:{ all -> 0x01f0 }
        L_0x01ef:
            return
        L_0x01f0:
            r13 = move-exception
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.Class r2 = r13.getClass()
            java.lang.String r2 = r2.getName()
            r0[r1] = r2
            java.lang.String r1 = "crash handle callback something wrong! %s"
            com.tencent.bugly.proguard.x.d(r1, r0)
            boolean r0 = com.tencent.bugly.proguard.x.a(r13)
            if (r0 != 0) goto L_0x020b
            r13.printStackTrace()
        L_0x020b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.b.d(com.tencent.bugly.crashreport.crash.CrashDetailBean):void");
    }

    private static ContentValues f(CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.a > 0) {
                contentValues.put("_id", Long.valueOf(crashDetailBean.a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.r));
            contentValues.put("_s1", crashDetailBean.u);
            int i = 1;
            contentValues.put("_up", Integer.valueOf(crashDetailBean.d ? 1 : 0));
            if (!crashDetailBean.j) {
                i = 0;
            }
            contentValues.put("_me", Integer.valueOf(i));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.l));
            contentValues.put("_dt", z.a((Parcelable) crashDetailBean));
            return contentValues;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static CrashDetailBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            CrashDetailBean crashDetailBean = (CrashDetailBean) z.a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean != null) {
                crashDetailBean.a = j;
            }
            return crashDetailBean;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public final void e(CrashDetailBean crashDetailBean) {
        ContentValues f2;
        if (crashDetailBean != null && (f2 = f(crashDetailBean)) != null) {
            long a2 = p.a().a("t_cr", f2, (o) null, true);
            if (a2 >= 0) {
                x.c("insert %s success!", "t_cr");
                crashDetailBean.a = a2;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f8 A[Catch:{ all -> 0x0101 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00fd A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.tencent.bugly.crashreport.crash.CrashDetailBean> b(java.util.List<com.tencent.bugly.crashreport.crash.a> r15) {
        /*
            r14 = this;
            r0 = 0
            if (r15 == 0) goto L_0x0108
            int r1 = r15.size()
            if (r1 != 0) goto L_0x000b
            goto L_0x0108
        L_0x000b:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "_id in "
            r1.append(r2)
            java.lang.String r3 = "("
            r1.append(r3)
            java.util.Iterator r15 = r15.iterator()
        L_0x001e:
            boolean r4 = r15.hasNext()
            java.lang.String r5 = ","
            if (r4 == 0) goto L_0x0035
            java.lang.Object r4 = r15.next()
            com.tencent.bugly.crashreport.crash.a r4 = (com.tencent.bugly.crashreport.crash.a) r4
            long r6 = r4.a
            r1.append(r6)
            r1.append(r5)
            goto L_0x001e
        L_0x0035:
            java.lang.String r15 = r1.toString()
            boolean r15 = r15.contains(r5)
            r4 = 0
            if (r15 == 0) goto L_0x004e
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            int r6 = r1.lastIndexOf(r5)
            java.lang.String r1 = r1.substring(r4, r6)
            r15.<init>(r1)
            r1 = r15
        L_0x004e:
            java.lang.String r15 = ")"
            r1.append(r15)
            java.lang.String r9 = r1.toString()
            r1.setLength(r4)
            com.tencent.bugly.proguard.p r6 = com.tencent.bugly.proguard.p.a()     // Catch:{ all -> 0x00f0 }
            java.lang.String r7 = "t_cr"
            r8 = 0
            r10 = 0
            r11 = 0
            r12 = 1
            android.database.Cursor r6 = r6.a(r7, r8, r9, r10, r11, r12)     // Catch:{ all -> 0x00f0 }
            if (r6 != 0) goto L_0x0070
            if (r6 == 0) goto L_0x006f
            r6.close()
        L_0x006f:
            return r0
        L_0x0070:
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x00ee }
            r7.<init>()     // Catch:{ all -> 0x00ee }
            r1.append(r2)     // Catch:{ all -> 0x00ee }
            r1.append(r3)     // Catch:{ all -> 0x00ee }
            r2 = 0
        L_0x007c:
            boolean r3 = r6.moveToNext()     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x00a7
            com.tencent.bugly.crashreport.crash.CrashDetailBean r3 = a((android.database.Cursor) r6)     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x008c
            r7.add(r3)     // Catch:{ all -> 0x00ee }
            goto L_0x007c
        L_0x008c:
            java.lang.String r3 = "_id"
            int r3 = r6.getColumnIndex(r3)     // Catch:{ all -> 0x009f }
            long r8 = r6.getLong(r3)     // Catch:{ all -> 0x009f }
            r1.append(r8)     // Catch:{ all -> 0x009f }
            r1.append(r5)     // Catch:{ all -> 0x009f }
            int r2 = r2 + 1
            goto L_0x007c
        L_0x009f:
            java.lang.String r3 = "unknown id!"
            java.lang.Object[] r8 = new java.lang.Object[r4]     // Catch:{ all -> 0x00ee }
            com.tencent.bugly.proguard.x.d(r3, r8)     // Catch:{ all -> 0x00ee }
            goto L_0x007c
        L_0x00a7:
            java.lang.String r3 = r1.toString()     // Catch:{ all -> 0x00ee }
            boolean r3 = r3.contains(r5)     // Catch:{ all -> 0x00ee }
            if (r3 == 0) goto L_0x00bf
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ee }
            int r5 = r1.lastIndexOf(r5)     // Catch:{ all -> 0x00ee }
            java.lang.String r1 = r1.substring(r4, r5)     // Catch:{ all -> 0x00ee }
            r3.<init>(r1)     // Catch:{ all -> 0x00ee }
            r1 = r3
        L_0x00bf:
            r1.append(r15)     // Catch:{ all -> 0x00ee }
            java.lang.String r10 = r1.toString()     // Catch:{ all -> 0x00ee }
            if (r2 <= 0) goto L_0x00e8
            com.tencent.bugly.proguard.p r8 = com.tencent.bugly.proguard.p.a()     // Catch:{ all -> 0x00ee }
            java.lang.String r9 = "t_cr"
            r11 = 0
            r12 = 0
            r13 = 1
            int r15 = r8.a((java.lang.String) r9, (java.lang.String) r10, (java.lang.String[]) r11, (com.tencent.bugly.proguard.o) r12, (boolean) r13)     // Catch:{ all -> 0x00ee }
            java.lang.String r1 = "deleted %s illegal data %d"
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x00ee }
            java.lang.String r3 = "t_cr"
            r2[r4] = r3     // Catch:{ all -> 0x00ee }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)     // Catch:{ all -> 0x00ee }
            r3 = 1
            r2[r3] = r15     // Catch:{ all -> 0x00ee }
            com.tencent.bugly.proguard.x.d(r1, r2)     // Catch:{ all -> 0x00ee }
        L_0x00e8:
            if (r6 == 0) goto L_0x00ed
            r6.close()
        L_0x00ed:
            return r7
        L_0x00ee:
            r15 = move-exception
            goto L_0x00f2
        L_0x00f0:
            r15 = move-exception
            r6 = r0
        L_0x00f2:
            boolean r1 = com.tencent.bugly.proguard.x.a(r15)     // Catch:{ all -> 0x0101 }
            if (r1 != 0) goto L_0x00fb
            r15.printStackTrace()     // Catch:{ all -> 0x0101 }
        L_0x00fb:
            if (r6 == 0) goto L_0x0100
            r6.close()
        L_0x0100:
            return r0
        L_0x0101:
            r15 = move-exception
            if (r6 == 0) goto L_0x0107
            r6.close()
        L_0x0107:
            throw r15
        L_0x0108:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.b.b(java.util.List):java.util.List");
    }

    private static a b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            a aVar = new a();
            aVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            aVar.b = cursor.getLong(cursor.getColumnIndex("_tm"));
            aVar.c = cursor.getString(cursor.getColumnIndex("_s1"));
            boolean z = false;
            aVar.d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            if (cursor.getInt(cursor.getColumnIndex("_me")) == 1) {
                z = true;
            }
            aVar.e = z;
            aVar.f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return aVar;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.util.List<com.tencent.bugly.crashreport.crash.a>] */
    /* JADX WARNING: type inference failed for: r2v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00cc A[Catch:{ all -> 0x00d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d1 A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.tencent.bugly.crashreport.crash.a> b() {
        /*
            r16 = this;
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            java.lang.String r3 = "_id"
            java.lang.String r4 = "_tm"
            java.lang.String r5 = "_s1"
            java.lang.String r6 = "_up"
            java.lang.String r7 = "_me"
            java.lang.String r8 = "_uc"
            java.lang.String[] r11 = new java.lang.String[]{r3, r4, r5, r6, r7, r8}     // Catch:{ all -> 0x00c5 }
            com.tencent.bugly.proguard.p r9 = com.tencent.bugly.proguard.p.a()     // Catch:{ all -> 0x00c5 }
            java.lang.String r10 = "t_cr"
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 1
            android.database.Cursor r3 = r9.a(r10, r11, r12, r13, r14, r15)     // Catch:{ all -> 0x00c5 }
            if (r3 != 0) goto L_0x002c
            if (r3 == 0) goto L_0x002b
            r3.close()
        L_0x002b:
            return r2
        L_0x002c:
            int r0 = r3.getCount()     // Catch:{ all -> 0x00c2 }
            if (r0 > 0) goto L_0x0038
            if (r3 == 0) goto L_0x0037
            r3.close()
        L_0x0037:
            return r1
        L_0x0038:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c2 }
            r0.<init>()     // Catch:{ all -> 0x00c2 }
            java.lang.String r2 = "_id in "
            r0.append(r2)     // Catch:{ all -> 0x00c2 }
            java.lang.String r2 = "("
            r0.append(r2)     // Catch:{ all -> 0x00c2 }
            r2 = 0
            r4 = 0
        L_0x0049:
            boolean r5 = r3.moveToNext()     // Catch:{ all -> 0x00c2 }
            java.lang.String r6 = ","
            if (r5 == 0) goto L_0x0076
            com.tencent.bugly.crashreport.crash.a r5 = b((android.database.Cursor) r3)     // Catch:{ all -> 0x00c2 }
            if (r5 == 0) goto L_0x005b
            r1.add(r5)     // Catch:{ all -> 0x00c2 }
            goto L_0x0049
        L_0x005b:
            java.lang.String r5 = "_id"
            int r5 = r3.getColumnIndex(r5)     // Catch:{ all -> 0x006e }
            long r7 = r3.getLong(r5)     // Catch:{ all -> 0x006e }
            r0.append(r7)     // Catch:{ all -> 0x006e }
            r0.append(r6)     // Catch:{ all -> 0x006e }
            int r4 = r4 + 1
            goto L_0x0049
        L_0x006e:
            java.lang.String r5 = "unknown id!"
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ all -> 0x00c2 }
            com.tencent.bugly.proguard.x.d(r5, r6)     // Catch:{ all -> 0x00c2 }
            goto L_0x0049
        L_0x0076:
            java.lang.String r5 = r0.toString()     // Catch:{ all -> 0x00c2 }
            boolean r5 = r5.contains(r6)     // Catch:{ all -> 0x00c2 }
            if (r5 == 0) goto L_0x008e
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c2 }
            int r6 = r0.lastIndexOf(r6)     // Catch:{ all -> 0x00c2 }
            java.lang.String r0 = r0.substring(r2, r6)     // Catch:{ all -> 0x00c2 }
            r5.<init>(r0)     // Catch:{ all -> 0x00c2 }
            r0 = r5
        L_0x008e:
            java.lang.String r5 = ")"
            r0.append(r5)     // Catch:{ all -> 0x00c2 }
            java.lang.String r8 = r0.toString()     // Catch:{ all -> 0x00c2 }
            r0.setLength(r2)     // Catch:{ all -> 0x00c2 }
            if (r4 <= 0) goto L_0x00bc
            com.tencent.bugly.proguard.p r6 = com.tencent.bugly.proguard.p.a()     // Catch:{ all -> 0x00c2 }
            java.lang.String r7 = "t_cr"
            r9 = 0
            r10 = 0
            r11 = 1
            int r0 = r6.a((java.lang.String) r7, (java.lang.String) r8, (java.lang.String[]) r9, (com.tencent.bugly.proguard.o) r10, (boolean) r11)     // Catch:{ all -> 0x00c2 }
            java.lang.String r4 = "deleted %s illegal data %d"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x00c2 }
            java.lang.String r6 = "t_cr"
            r5[r2] = r6     // Catch:{ all -> 0x00c2 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x00c2 }
            r2 = 1
            r5[r2] = r0     // Catch:{ all -> 0x00c2 }
            com.tencent.bugly.proguard.x.d(r4, r5)     // Catch:{ all -> 0x00c2 }
        L_0x00bc:
            if (r3 == 0) goto L_0x00c1
            r3.close()
        L_0x00c1:
            return r1
        L_0x00c2:
            r0 = move-exception
            r2 = r3
            goto L_0x00c6
        L_0x00c5:
            r0 = move-exception
        L_0x00c6:
            boolean r3 = com.tencent.bugly.proguard.x.a(r0)     // Catch:{ all -> 0x00d5 }
            if (r3 != 0) goto L_0x00cf
            r0.printStackTrace()     // Catch:{ all -> 0x00d5 }
        L_0x00cf:
            if (r2 == 0) goto L_0x00d4
            r2.close()
        L_0x00d4:
            return r1
        L_0x00d5:
            r0 = move-exception
            if (r2 == 0) goto L_0x00db
            r2.close()
        L_0x00db:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.b.b():java.util.List");
    }

    private static void c(List<a> list) {
        if (list != null && list.size() != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("_id in ");
            sb.append("(");
            for (a aVar : list) {
                sb.append(aVar.a);
                sb.append(",");
            }
            StringBuilder sb2 = new StringBuilder(sb.substring(0, sb.lastIndexOf(",")));
            sb2.append(")");
            String sb3 = sb2.toString();
            sb2.setLength(0);
            try {
                x.c("deleted %s data %d", "t_cr", Integer.valueOf(p.a().a("t_cr", sb3, (String[]) null, (o) null, true)));
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private static void d(List<CrashDetailBean> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (CrashDetailBean crashDetailBean : list) {
                        sb.append(" or _id");
                        sb.append(" = ");
                        sb.append(crashDetailBean.a);
                    }
                    String sb2 = sb.toString();
                    if (sb2.length() > 0) {
                        sb2 = sb2.substring(4);
                    }
                    sb.setLength(0);
                    x.c("deleted %s data %d", "t_cr", Integer.valueOf(p.a().a("t_cr", sb2, (String[]) null, (o) null, true)));
                }
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private static ak a(Context context, CrashDetailBean crashDetailBean, com.tencent.bugly.crashreport.common.info.a aVar) {
        aj a2;
        aj a3;
        aj ajVar;
        boolean z = false;
        if (context == null || crashDetailBean == null || aVar == null) {
            x.d("enExp args == null", new Object[0]);
            return null;
        }
        ak akVar = new ak();
        switch (crashDetailBean.b) {
            case 0:
                akVar.a = crashDetailBean.j ? "200" : "100";
                break;
            case 1:
                akVar.a = crashDetailBean.j ? "201" : "101";
                break;
            case 2:
                akVar.a = crashDetailBean.j ? "202" : "102";
                break;
            case 3:
                akVar.a = crashDetailBean.j ? "203" : "103";
                break;
            case 4:
                akVar.a = crashDetailBean.j ? "204" : "104";
                break;
            case 5:
                akVar.a = crashDetailBean.j ? "207" : "107";
                break;
            case 6:
                akVar.a = crashDetailBean.j ? "206" : "106";
                break;
            case 7:
                akVar.a = crashDetailBean.j ? "208" : "108";
                break;
            default:
                x.e("crash type error! %d", Integer.valueOf(crashDetailBean.b));
                break;
        }
        akVar.b = crashDetailBean.r;
        akVar.c = crashDetailBean.n;
        akVar.d = crashDetailBean.o;
        akVar.e = crashDetailBean.p;
        akVar.g = crashDetailBean.q;
        akVar.h = crashDetailBean.z;
        akVar.i = crashDetailBean.c;
        akVar.j = null;
        akVar.l = crashDetailBean.m;
        akVar.m = crashDetailBean.e;
        akVar.f = crashDetailBean.B;
        akVar.t = com.tencent.bugly.crashreport.common.info.a.b().i();
        akVar.n = null;
        x.c("libInfo %s", akVar.o);
        if (crashDetailBean.h != null && crashDetailBean.h.size() > 0) {
            akVar.p = new ArrayList<>();
            for (Map.Entry next : crashDetailBean.h.entrySet()) {
                ah ahVar = new ah();
                ahVar.a = ((PlugInBean) next.getValue()).a;
                ahVar.b = ((PlugInBean) next.getValue()).c;
                ahVar.c = ((PlugInBean) next.getValue()).b;
                akVar.p.add(ahVar);
            }
        }
        if (crashDetailBean.j) {
            akVar.k = crashDetailBean.t;
            if (crashDetailBean.s != null && crashDetailBean.s.length() > 0) {
                if (akVar.q == null) {
                    akVar.q = new ArrayList<>();
                }
                try {
                    akVar.q.add(new aj((byte) 1, "alltimes.txt", crashDetailBean.s.getBytes("utf-8")));
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                    akVar.q = null;
                }
            }
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(akVar.k);
            objArr[1] = Integer.valueOf(akVar.q != null ? akVar.q.size() : 0);
            x.c("crashcount:%d sz:%d", objArr);
        }
        if (crashDetailBean.w != null) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            try {
                akVar.q.add(new aj((byte) 1, "log.txt", crashDetailBean.w.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
                akVar.q = null;
            }
        }
        if (crashDetailBean.x != null) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            try {
                akVar.q.add(new aj((byte) 1, "jniLog.txt", crashDetailBean.x.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e4) {
                e4.printStackTrace();
                akVar.q = null;
            }
        }
        if (!z.a(crashDetailBean.V)) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            try {
                ajVar = new aj((byte) 1, "crashInfos.txt", crashDetailBean.V.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e5) {
                e5.printStackTrace();
                ajVar = null;
            }
            if (ajVar != null) {
                x.c("attach crash infos", new Object[0]);
                akVar.q.add(ajVar);
            }
        }
        if (crashDetailBean.W != null) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            aj a4 = a("backupRecord.zip", context, crashDetailBean.W);
            if (a4 != null) {
                x.c("attach backup record", new Object[0]);
                akVar.q.add(a4);
            }
        }
        if (crashDetailBean.y != null && crashDetailBean.y.length > 0) {
            aj ajVar2 = new aj((byte) 2, "buglylog.zip", crashDetailBean.y);
            x.c("attach user log", new Object[0]);
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            akVar.q.add(ajVar2);
        }
        if (crashDetailBean.b == 3) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            x.c("crashBean.anrMessages:%s", crashDetailBean.P);
            if (crashDetailBean.P != null && crashDetailBean.P.containsKey("BUGLY_CR_01")) {
                try {
                    if (!TextUtils.isEmpty(crashDetailBean.P.get("BUGLY_CR_01"))) {
                        akVar.q.add(new aj((byte) 1, "anrMessage.txt", crashDetailBean.P.get("BUGLY_CR_01").getBytes("utf-8")));
                        x.c("attach anr message", new Object[0]);
                    }
                } catch (UnsupportedEncodingException e6) {
                    e6.printStackTrace();
                    akVar.q = null;
                }
                crashDetailBean.P.remove("BUGLY_CR_01");
            }
            if (!(crashDetailBean.v == null || (a3 = a("trace.zip", context, crashDetailBean.v)) == null)) {
                x.c("attach traces", new Object[0]);
                akVar.q.add(a3);
            }
        }
        if (crashDetailBean.b == 1) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            if (!(crashDetailBean.v == null || (a2 = a("tomb.zip", context, crashDetailBean.v)) == null)) {
                x.c("attach tombs", new Object[0]);
                akVar.q.add(a2);
            }
        }
        if (aVar.E != null && !aVar.E.isEmpty()) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            StringBuilder sb = new StringBuilder();
            for (String append : aVar.E) {
                sb.append(append);
            }
            try {
                akVar.q.add(new aj((byte) 1, "martianlog.txt", sb.toString().getBytes("utf-8")));
                x.c("attach pageTracingList", new Object[0]);
            } catch (UnsupportedEncodingException e7) {
                e7.printStackTrace();
            }
        }
        if (crashDetailBean.U != null && crashDetailBean.U.length > 0) {
            if (akVar.q == null) {
                akVar.q = new ArrayList<>();
            }
            akVar.q.add(new aj((byte) 1, "userExtraByteData", crashDetailBean.U));
            x.c("attach extraData", new Object[0]);
        }
        akVar.r = new HashMap();
        Map<String, String> map = akVar.r;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(crashDetailBean.C);
        map.put("A9", sb2.toString());
        Map<String, String> map2 = akVar.r;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(crashDetailBean.D);
        map2.put("A11", sb3.toString());
        Map<String, String> map3 = akVar.r;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(crashDetailBean.E);
        map3.put("A10", sb4.toString());
        akVar.r.put("A23", crashDetailBean.f);
        akVar.r.put("A7", aVar.g);
        akVar.r.put("A6", aVar.r());
        akVar.r.put("A5", aVar.q());
        akVar.r.put("A22", aVar.h());
        Map<String, String> map4 = akVar.r;
        StringBuilder sb5 = new StringBuilder();
        sb5.append(crashDetailBean.G);
        map4.put("A2", sb5.toString());
        Map<String, String> map5 = akVar.r;
        StringBuilder sb6 = new StringBuilder();
        sb6.append(crashDetailBean.F);
        map5.put("A1", sb6.toString());
        akVar.r.put("A24", aVar.i);
        Map<String, String> map6 = akVar.r;
        StringBuilder sb7 = new StringBuilder();
        sb7.append(crashDetailBean.H);
        map6.put("A17", sb7.toString());
        akVar.r.put("A3", aVar.k());
        akVar.r.put("A16", aVar.m());
        akVar.r.put("A25", aVar.h());
        akVar.r.put("A14", aVar.l());
        akVar.r.put("A15", aVar.u());
        Map<String, String> map7 = akVar.r;
        StringBuilder sb8 = new StringBuilder();
        sb8.append(aVar.v());
        map7.put("A13", sb8.toString());
        akVar.r.put("A34", crashDetailBean.A);
        if (aVar.y != null) {
            akVar.r.put("productIdentify", aVar.y);
        }
        try {
            akVar.r.put("A26", URLEncoder.encode(crashDetailBean.I, "utf-8"));
        } catch (UnsupportedEncodingException e8) {
            e8.printStackTrace();
        }
        if (crashDetailBean.b == 1) {
            akVar.r.put("A27", crashDetailBean.K);
            akVar.r.put("A28", crashDetailBean.J);
            Map<String, String> map8 = akVar.r;
            StringBuilder sb9 = new StringBuilder();
            sb9.append(crashDetailBean.k);
            map8.put("A29", sb9.toString());
        }
        akVar.r.put("A30", crashDetailBean.L);
        Map<String, String> map9 = akVar.r;
        StringBuilder sb10 = new StringBuilder();
        sb10.append(crashDetailBean.M);
        map9.put("A18", sb10.toString());
        Map<String, String> map10 = akVar.r;
        StringBuilder sb11 = new StringBuilder();
        sb11.append(!crashDetailBean.N);
        map10.put("A36", sb11.toString());
        Map<String, String> map11 = akVar.r;
        StringBuilder sb12 = new StringBuilder();
        sb12.append(aVar.r);
        map11.put("F02", sb12.toString());
        Map<String, String> map12 = akVar.r;
        StringBuilder sb13 = new StringBuilder();
        sb13.append(aVar.s);
        map12.put("F03", sb13.toString());
        akVar.r.put("F04", aVar.e());
        Map<String, String> map13 = akVar.r;
        StringBuilder sb14 = new StringBuilder();
        sb14.append(aVar.t);
        map13.put("F05", sb14.toString());
        akVar.r.put("F06", aVar.q);
        akVar.r.put("F08", aVar.w);
        akVar.r.put("F09", aVar.x);
        Map<String, String> map14 = akVar.r;
        StringBuilder sb15 = new StringBuilder();
        sb15.append(aVar.u);
        map14.put("F10", sb15.toString());
        if (crashDetailBean.Q >= 0) {
            Map<String, String> map15 = akVar.r;
            StringBuilder sb16 = new StringBuilder();
            sb16.append(crashDetailBean.Q);
            map15.put("C01", sb16.toString());
        }
        if (crashDetailBean.R >= 0) {
            Map<String, String> map16 = akVar.r;
            StringBuilder sb17 = new StringBuilder();
            sb17.append(crashDetailBean.R);
            map16.put("C02", sb17.toString());
        }
        if (crashDetailBean.S != null && crashDetailBean.S.size() > 0) {
            for (Map.Entry next2 : crashDetailBean.S.entrySet()) {
                akVar.r.put("C03_" + ((String) next2.getKey()), next2.getValue());
            }
        }
        if (crashDetailBean.T != null && crashDetailBean.T.size() > 0) {
            for (Map.Entry next3 : crashDetailBean.T.entrySet()) {
                akVar.r.put("C04_" + ((String) next3.getKey()), next3.getValue());
            }
        }
        akVar.s = null;
        if (crashDetailBean.O != null && crashDetailBean.O.size() > 0) {
            akVar.s = crashDetailBean.O;
            x.a("setted message size %d", Integer.valueOf(akVar.s.size()));
        }
        Object[] objArr2 = new Object[12];
        objArr2[0] = crashDetailBean.n;
        objArr2[1] = crashDetailBean.c;
        objArr2[2] = aVar.e();
        objArr2[3] = Long.valueOf((crashDetailBean.r - crashDetailBean.M) / 1000);
        objArr2[4] = Boolean.valueOf(crashDetailBean.k);
        objArr2[5] = Boolean.valueOf(crashDetailBean.N);
        objArr2[6] = Boolean.valueOf(crashDetailBean.j);
        if (crashDetailBean.b == 1) {
            z = true;
        }
        objArr2[7] = Boolean.valueOf(z);
        objArr2[8] = Integer.valueOf(crashDetailBean.t);
        objArr2[9] = crashDetailBean.s;
        objArr2[10] = Boolean.valueOf(crashDetailBean.d);
        objArr2[11] = Integer.valueOf(akVar.r.size());
        x.c("%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d", objArr2);
        return akVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0090 A[Catch:{ all -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0095 A[SYNTHETIC, Splitter:B:35:0x0095] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a9 A[DONT_GENERATE] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.tencent.bugly.proguard.aj a(java.lang.String r6, android.content.Context r7, java.lang.String r8) {
        /*
            java.lang.String r0 = "del tmp"
            r1 = 0
            r2 = 0
            if (r8 == 0) goto L_0x00d2
            if (r7 != 0) goto L_0x000a
            goto L_0x00d2
        L_0x000a:
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]
            r4[r2] = r8
            java.lang.String r5 = "zip %s"
            com.tencent.bugly.proguard.x.c(r5, r4)
            java.io.File r4 = new java.io.File
            r4.<init>(r8)
            java.io.File r8 = new java.io.File
            java.io.File r7 = r7.getCacheDir()
            r8.<init>(r7, r6)
            r6 = 5000(0x1388, float:7.006E-42)
            boolean r6 = com.tencent.bugly.proguard.z.a((java.io.File) r4, (java.io.File) r8, (int) r6)
            if (r6 != 0) goto L_0x0032
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.String r7 = "zip fail!"
            com.tencent.bugly.proguard.x.d(r7, r6)
            return r1
        L_0x0032:
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream
            r6.<init>()
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ all -> 0x0088 }
            r7.<init>(r8)     // Catch:{ all -> 0x0088 }
            r4 = 4096(0x1000, float:5.74E-42)
            byte[] r4 = new byte[r4]     // Catch:{ all -> 0x0086 }
        L_0x0040:
            int r5 = r7.read(r4)     // Catch:{ all -> 0x0086 }
            if (r5 <= 0) goto L_0x004d
            r6.write(r4, r2, r5)     // Catch:{ all -> 0x0086 }
            r6.flush()     // Catch:{ all -> 0x0086 }
            goto L_0x0040
        L_0x004d:
            byte[] r6 = r6.toByteArray()     // Catch:{ all -> 0x0086 }
            java.lang.String r4 = "read bytes :%d"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0086 }
            int r5 = r6.length     // Catch:{ all -> 0x0086 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0086 }
            r3[r2] = r5     // Catch:{ all -> 0x0086 }
            com.tencent.bugly.proguard.x.c(r4, r3)     // Catch:{ all -> 0x0086 }
            com.tencent.bugly.proguard.aj r3 = new com.tencent.bugly.proguard.aj     // Catch:{ all -> 0x0086 }
            r4 = 2
            java.lang.String r5 = r8.getName()     // Catch:{ all -> 0x0086 }
            r3.<init>(r4, r5, r6)     // Catch:{ all -> 0x0086 }
            r7.close()     // Catch:{ IOException -> 0x006d }
            goto L_0x0077
        L_0x006d:
            r6 = move-exception
            boolean r7 = com.tencent.bugly.proguard.x.a(r6)
            if (r7 != 0) goto L_0x0077
            r6.printStackTrace()
        L_0x0077:
            boolean r6 = r8.exists()
            if (r6 == 0) goto L_0x0085
            java.lang.Object[] r6 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.x.c(r0, r6)
            r8.delete()
        L_0x0085:
            return r3
        L_0x0086:
            r6 = move-exception
            goto L_0x008a
        L_0x0088:
            r6 = move-exception
            r7 = r1
        L_0x008a:
            boolean r3 = com.tencent.bugly.proguard.x.a(r6)     // Catch:{ all -> 0x00b2 }
            if (r3 != 0) goto L_0x0093
            r6.printStackTrace()     // Catch:{ all -> 0x00b2 }
        L_0x0093:
            if (r7 == 0) goto L_0x00a3
            r7.close()     // Catch:{ IOException -> 0x0099 }
            goto L_0x00a3
        L_0x0099:
            r6 = move-exception
            boolean r7 = com.tencent.bugly.proguard.x.a(r6)
            if (r7 != 0) goto L_0x00a3
            r6.printStackTrace()
        L_0x00a3:
            boolean r6 = r8.exists()
            if (r6 == 0) goto L_0x00b1
            java.lang.Object[] r6 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.x.c(r0, r6)
            r8.delete()
        L_0x00b1:
            return r1
        L_0x00b2:
            r6 = move-exception
            if (r7 == 0) goto L_0x00c3
            r7.close()     // Catch:{ IOException -> 0x00b9 }
            goto L_0x00c3
        L_0x00b9:
            r7 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r7)
            if (r1 != 0) goto L_0x00c3
            r7.printStackTrace()
        L_0x00c3:
            boolean r7 = r8.exists()
            if (r7 == 0) goto L_0x00d1
            java.lang.Object[] r7 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.x.c(r0, r7)
            r8.delete()
        L_0x00d1:
            throw r6
        L_0x00d2:
            java.lang.Object[] r6 = new java.lang.Object[r2]
            java.lang.String r7 = "rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}"
            com.tencent.bugly.proguard.x.d(r7, r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.b.a(java.lang.String, android.content.Context, java.lang.String):com.tencent.bugly.proguard.aj");
    }

    public static void a(String str, String str2, String str3, String str4, String str5, CrashDetailBean crashDetailBean) {
        String str6;
        com.tencent.bugly.crashreport.common.info.a b2 = com.tencent.bugly.crashreport.common.info.a.b();
        if (b2 != null) {
            x.e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
            x.e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
            x.e("# PKG NAME: %s", b2.c);
            x.e("# APP VER: %s", b2.k);
            x.e("# SDK VER: %s", b2.f);
            x.e("# LAUNCH TIME: %s", z.a(new Date(com.tencent.bugly.crashreport.common.info.a.b().a)));
            x.e("# CRASH TYPE: %s", str);
            x.e("# CRASH TIME: %s", str2);
            x.e("# CRASH PROCESS: %s", str3);
            x.e("# CRASH THREAD: %s", str4);
            if (crashDetailBean != null) {
                x.e("# REPORT ID: %s", crashDetailBean.c);
                Object[] objArr = new Object[2];
                objArr[0] = b2.h;
                objArr[1] = b2.v().booleanValue() ? "ROOTED" : "UNROOT";
                x.e("# CRASH DEVICE: %s %s", objArr);
                x.e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.C), Long.valueOf(crashDetailBean.D), Long.valueOf(crashDetailBean.E));
                x.e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.F), Long.valueOf(crashDetailBean.G), Long.valueOf(crashDetailBean.H));
                if (!z.a(crashDetailBean.K)) {
                    x.e("# EXCEPTION FIRED BY %s %s", crashDetailBean.K, crashDetailBean.J);
                } else if (crashDetailBean.b == 3) {
                    Object[] objArr2 = new Object[1];
                    if (crashDetailBean.P == null) {
                        str6 = Configurator.NULL;
                    } else {
                        str6 = crashDetailBean.P.get("BUGLY_CR_01");
                    }
                    objArr2[0] = str6;
                    x.e("# EXCEPTION ANR MESSAGE:\n %s", objArr2);
                }
            }
            if (!z.a(str5)) {
                x.e("# CRASH STACK: ", new Object[0]);
                x.e(str5, new Object[0]);
            }
            x.e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
        }
    }
}
