package com.tencent.bugly.crashreport.crash.anr;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.FileObserver;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.aa;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.ac;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.StringUtils;

/* compiled from: BUGLY */
public final class b implements ac {
    private static b m;
    private AtomicInteger a = new AtomicInteger(0);
    private long b = -1;
    private final Context c;
    private final a d;
    private final w e;
    private String f;
    private final com.tencent.bugly.crashreport.crash.b g;
    private FileObserver h;
    private boolean i = true;
    /* access modifiers changed from: private */
    public ab j;
    private int k;
    private ActivityManager.ProcessErrorStateInfo l;

    public static b a(Context context, com.tencent.bugly.crashreport.common.strategy.a aVar, a aVar2, w wVar, p pVar, com.tencent.bugly.crashreport.crash.b bVar, BuglyStrategy.a aVar3) {
        if (m == null) {
            m = new b(context, aVar, aVar2, wVar, bVar);
        }
        return m;
    }

    private b(Context context, com.tencent.bugly.crashreport.common.strategy.a aVar, a aVar2, w wVar, com.tencent.bugly.crashreport.crash.b bVar) {
        this.c = z.a(context);
        this.f = context.getDir("bugly", 0).getAbsolutePath();
        this.d = aVar2;
        this.e = wVar;
        this.g = bVar;
        this.l = new ActivityManager.ProcessErrorStateInfo();
    }

    private ActivityManager.ProcessErrorStateInfo a(Context context, long j2) {
        try {
            x.c("to find!", new Object[0]);
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            int i2 = 0;
            while (true) {
                x.c("waiting!", new Object[0]);
                List<ActivityManager.ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
                if (processesInErrorState != null) {
                    for (ActivityManager.ProcessErrorStateInfo next : processesInErrorState) {
                        if (next.condition == 2) {
                            x.c("found!", new Object[0]);
                            return next;
                        }
                    }
                }
                z.b(500);
                int i3 = i2 + 1;
                if (((long) i2) >= 20) {
                    x.c("end!", new Object[0]);
                    return null;
                }
                i2 = i3;
            }
        } catch (Exception e2) {
            x.b(e2);
            return null;
        } catch (OutOfMemoryError e3) {
            this.l.pid = Process.myPid();
            ActivityManager.ProcessErrorStateInfo processErrorStateInfo = this.l;
            processErrorStateInfo.shortMsg = "bugly sdk waitForAnrProcessStateChanged encount error:" + e3.getMessage();
            return this.l;
        }
    }

    private CrashDetailBean a(a aVar) {
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        try {
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.j();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.h();
            crashDetailBean.E = com.tencent.bugly.crashreport.common.info.b.l();
            crashDetailBean.F = this.d.o();
            crashDetailBean.G = this.d.n();
            crashDetailBean.H = this.d.p();
            if (!com.tencent.bugly.crashreport.common.info.b.s()) {
                crashDetailBean.w = z.a(this.c, c.e, (String) null);
            }
            crashDetailBean.b = 3;
            crashDetailBean.e = this.d.h();
            crashDetailBean.f = this.d.k;
            crashDetailBean.g = this.d.u();
            crashDetailBean.m = this.d.g();
            crashDetailBean.n = "ANR_EXCEPTION";
            crashDetailBean.o = aVar.f;
            crashDetailBean.q = aVar.g;
            crashDetailBean.P = new HashMap();
            crashDetailBean.P.put("BUGLY_CR_01", aVar.e);
            int i2 = -1;
            if (crashDetailBean.q != null) {
                i2 = crashDetailBean.q.indexOf(StringUtils.LF);
            }
            crashDetailBean.p = i2 > 0 ? crashDetailBean.q.substring(0, i2) : "GET_FAIL";
            crashDetailBean.r = aVar.c;
            if (crashDetailBean.q != null) {
                crashDetailBean.u = z.a(crashDetailBean.q.getBytes());
            }
            crashDetailBean.z = aVar.b;
            crashDetailBean.A = aVar.a;
            crashDetailBean.B = "main(1)";
            crashDetailBean.I = this.d.w();
            crashDetailBean.h = this.d.t();
            crashDetailBean.i = this.d.F();
            crashDetailBean.v = aVar.d;
            crashDetailBean.L = this.d.o;
            crashDetailBean.M = this.d.a;
            crashDetailBean.N = this.d.a();
            if (!com.tencent.bugly.crashreport.common.info.b.s()) {
                this.g.d(crashDetailBean);
            }
            crashDetailBean.Q = this.d.D();
            crashDetailBean.R = this.d.E();
            crashDetailBean.S = this.d.x();
            crashDetailBean.T = this.d.C();
            crashDetailBean.y = y.a();
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
        return crashDetailBean;
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x0133 A[Catch:{ all -> 0x0129 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x015f A[SYNTHETIC, Splitter:B:60:0x015f] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0171 A[SYNTHETIC, Splitter:B:68:0x0171] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.lang.String r16, java.lang.String r17, java.lang.String r18) {
        /*
            r1 = r17
            r0 = r18
            java.lang.String r2 = "main"
            java.lang.String r3 = ":"
            r4 = 1
            r5 = r16
            com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$a r5 = com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readTargetDumpInfo(r0, r5, r4)
            r6 = 0
            if (r5 == 0) goto L_0x01bf
            java.util.Map<java.lang.String, java.lang.String[]> r7 = r5.d
            if (r7 == 0) goto L_0x01bf
            java.util.Map<java.lang.String, java.lang.String[]> r7 = r5.d
            int r7 = r7.size()
            if (r7 > 0) goto L_0x0020
            goto L_0x01bf
        L_0x0020:
            java.io.File r0 = new java.io.File
            r0.<init>(r1)
            r7 = 2
            boolean r8 = r0.exists()     // Catch:{ Exception -> 0x018b }
            if (r8 != 0) goto L_0x0040
            java.io.File r8 = r0.getParentFile()     // Catch:{ Exception -> 0x018b }
            boolean r8 = r8.exists()     // Catch:{ Exception -> 0x018b }
            if (r8 != 0) goto L_0x003d
            java.io.File r8 = r0.getParentFile()     // Catch:{ Exception -> 0x018b }
            r8.mkdirs()     // Catch:{ Exception -> 0x018b }
        L_0x003d:
            r0.createNewFile()     // Catch:{ Exception -> 0x018b }
        L_0x0040:
            boolean r8 = r0.exists()
            if (r8 == 0) goto L_0x0181
            boolean r8 = r0.canWrite()
            if (r8 != 0) goto L_0x004e
            goto L_0x0181
        L_0x004e:
            r1 = 0
            java.io.BufferedWriter r8 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x012c }
            java.io.FileWriter r9 = new java.io.FileWriter     // Catch:{ IOException -> 0x012c }
            r9.<init>(r0, r6)     // Catch:{ IOException -> 0x012c }
            r8.<init>(r9)     // Catch:{ IOException -> 0x012c }
            java.util.Map<java.lang.String, java.lang.String[]> r0 = r5.d     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.Object r0 = r0.get(r2)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.String[] r0 = (java.lang.String[]) r0     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.String r1 = "\n\n"
            java.lang.String r9 = "\n"
            java.lang.String r10 = " :\n"
            r11 = 3
            if (r0 == 0) goto L_0x0096
            int r12 = r0.length     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            if (r12 < r11) goto L_0x0096
            r12 = r0[r6]     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r13 = r0[r4]     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r0 = r0[r7]     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.String r15 = "\"main\" tid="
            r14.<init>(r15)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r14.append(r0)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r14.append(r10)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r14.append(r12)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r14.append(r9)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r14.append(r13)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r14.append(r1)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.String r0 = r14.toString()     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r8.write(r0)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r8.flush()     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
        L_0x0096:
            java.util.Map<java.lang.String, java.lang.String[]> r0 = r5.d     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.util.Set r0 = r0.entrySet()     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
        L_0x00a0:
            boolean r5 = r0.hasNext()     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            if (r5 == 0) goto L_0x0112
            java.lang.Object r5 = r0.next()     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.Object r12 = r5.getKey()     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            boolean r12 = r12.equals(r2)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            if (r12 != 0) goto L_0x00a0
            java.lang.Object r12 = r5.getValue()     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            if (r12 == 0) goto L_0x0110
            java.lang.Object r12 = r5.getValue()     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.String[] r12 = (java.lang.String[]) r12     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            int r12 = r12.length     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            if (r12 < r11) goto L_0x0110
            java.lang.Object r12 = r5.getValue()     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.String[] r12 = (java.lang.String[]) r12     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r12 = r12[r6]     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.Object r13 = r5.getValue()     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.String[] r13 = (java.lang.String[]) r13     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r13 = r13[r4]     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.Object r14 = r5.getValue()     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.String[] r14 = (java.lang.String[]) r14     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r14 = r14[r7]     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.String r11 = "\""
            r15.<init>(r11)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.Object r5 = r5.getKey()     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r15.append(r5)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.String r5 = "\" tid="
            r15.append(r5)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r15.append(r14)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r15.append(r10)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r15.append(r12)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r15.append(r9)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r15.append(r13)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r15.append(r1)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            java.lang.String r5 = r15.toString()     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r8.write(r5)     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
            r8.flush()     // Catch:{ IOException -> 0x0126, all -> 0x0122 }
        L_0x0110:
            r11 = 3
            goto L_0x00a0
        L_0x0112:
            r8.close()     // Catch:{ IOException -> 0x0116 }
            goto L_0x0121
        L_0x0116:
            r0 = move-exception
            r1 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r1)
            if (r0 != 0) goto L_0x0121
            r1.printStackTrace()
        L_0x0121:
            return r4
        L_0x0122:
            r0 = move-exception
            r2 = r0
            r1 = r8
            goto L_0x016f
        L_0x0126:
            r0 = move-exception
            r1 = r8
            goto L_0x012d
        L_0x0129:
            r0 = move-exception
            r2 = r0
            goto L_0x016f
        L_0x012c:
            r0 = move-exception
        L_0x012d:
            boolean r2 = com.tencent.bugly.proguard.x.a(r0)     // Catch:{ all -> 0x0129 }
            if (r2 != 0) goto L_0x0136
            r0.printStackTrace()     // Catch:{ all -> 0x0129 }
        L_0x0136:
            java.lang.String r2 = "dump trace fail %s"
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0129 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0129 }
            r5.<init>()     // Catch:{ all -> 0x0129 }
            java.lang.Class r7 = r0.getClass()     // Catch:{ all -> 0x0129 }
            java.lang.String r7 = r7.getName()     // Catch:{ all -> 0x0129 }
            r5.append(r7)     // Catch:{ all -> 0x0129 }
            r5.append(r3)     // Catch:{ all -> 0x0129 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0129 }
            r5.append(r0)     // Catch:{ all -> 0x0129 }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x0129 }
            r4[r6] = r0     // Catch:{ all -> 0x0129 }
            com.tencent.bugly.proguard.x.e(r2, r4)     // Catch:{ all -> 0x0129 }
            if (r1 == 0) goto L_0x016e
            r1.close()     // Catch:{ IOException -> 0x0163 }
            goto L_0x016e
        L_0x0163:
            r0 = move-exception
            r1 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r1)
            if (r0 != 0) goto L_0x016e
            r1.printStackTrace()
        L_0x016e:
            return r6
        L_0x016f:
            if (r1 == 0) goto L_0x0180
            r1.close()     // Catch:{ IOException -> 0x0175 }
            goto L_0x0180
        L_0x0175:
            r0 = move-exception
            r1 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r1)
            if (r0 != 0) goto L_0x0180
            r1.printStackTrace()
        L_0x0180:
            throw r2
        L_0x0181:
            java.lang.Object[] r0 = new java.lang.Object[r4]
            r0[r6] = r1
            java.lang.String r1 = "backup file create fail %s"
            com.tencent.bugly.proguard.x.e(r1, r0)
            return r6
        L_0x018b:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.a(r0)
            if (r2 != 0) goto L_0x0195
            r0.printStackTrace()
        L_0x0195:
            java.lang.Object[] r2 = new java.lang.Object[r7]
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.Class r7 = r0.getClass()
            java.lang.String r7 = r7.getName()
            r5.append(r7)
            r5.append(r3)
            java.lang.String r0 = r0.getMessage()
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r2[r6] = r0
            r2[r4] = r1
            java.lang.String r0 = "backup file create error! %s  %s"
            com.tencent.bugly.proguard.x.e(r0, r2)
            return r6
        L_0x01bf:
            java.lang.Object[] r1 = new java.lang.Object[r4]
            r1[r6] = r0
            java.lang.String r0 = "not found trace dump for %s"
            com.tencent.bugly.proguard.x.e(r0, r1)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.b.a(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    public final boolean a() {
        return this.a.get() != 0;
    }

    private boolean a(Context context, String str, ActivityManager.ProcessErrorStateInfo processErrorStateInfo, long j2, Map<String, String> map) {
        a aVar = new a();
        aVar.c = j2;
        aVar.a = processErrorStateInfo != null ? processErrorStateInfo.processName : AppInfo.a(Process.myPid());
        String str2 = "";
        aVar.f = processErrorStateInfo != null ? processErrorStateInfo.shortMsg : str2;
        if (processErrorStateInfo != null) {
            str2 = processErrorStateInfo.longMsg;
        }
        aVar.e = str2;
        aVar.b = map;
        Thread thread = Looper.getMainLooper().getThread();
        if (map != null) {
            Iterator<String> it = map.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (next.startsWith(thread.getName())) {
                    aVar.g = map.get(next);
                    break;
                }
            }
        }
        if (TextUtils.isEmpty(aVar.g)) {
            aVar.g = "main stack is null , some error may be encountered.";
        }
        Object[] objArr = new Object[7];
        objArr[0] = Long.valueOf(aVar.c);
        objArr[1] = aVar.d;
        objArr[2] = aVar.a;
        objArr[3] = aVar.g;
        objArr[4] = aVar.f;
        objArr[5] = aVar.e;
        objArr[6] = Integer.valueOf(aVar.b == null ? 0 : aVar.b.size());
        x.c("anr tm:%d\ntr:%s\nproc:%s\nmain stack:%s\nsMsg:%s\n lMsg:%s\n threads:%d", objArr);
        x.a("found visiable anr , start to upload!", new Object[0]);
        CrashDetailBean a2 = a(aVar);
        if (a2 == null) {
            x.e("pack anr fail!", new Object[0]);
            return false;
        }
        c.a().a(a2);
        if (a2.a >= 0) {
            x.a("backup anr record success!", new Object[0]);
        } else {
            x.d("backup anr record fail!", new Object[0]);
        }
        if (str == null || !new File(str).exists()) {
            File h2 = h();
            x.a("traceFile is %s", h2);
            if (h2 != null) {
                a2.v = h2.getAbsolutePath();
            }
        } else {
            String str3 = this.f;
            aVar.d = new File(str3, "bugly_trace_" + j2 + ".txt").getAbsolutePath();
            this.a.set(3);
            if (a(str, aVar.d, aVar.a)) {
                x.a("backup trace success", new Object[0]);
            }
        }
        com.tencent.bugly.crashreport.crash.b.a("ANR", z.a(), aVar.a, "main", aVar.g, a2);
        if (!this.g.a(a2)) {
            this.g.a(a2, 3000, true);
        }
        this.g.c(a2);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        com.tencent.bugly.proguard.x.c("read trace first dump for create time!", new java.lang.Object[0]);
        r0 = com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readFirstDumpInfo(r11, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0027, code lost:
        if (r0 == null) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
        r5 = r0.c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        r5 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        if (r5 != -1) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
        com.tencent.bugly.proguard.x.d("trace dump fail could not get time!", new java.lang.Object[0]);
        r5 = java.lang.System.currentTimeMillis();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003c, code lost:
        r7 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0049, code lost:
        if (java.lang.Math.abs(r7 - r10.b) >= 10000) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004b, code lost:
        com.tencent.bugly.proguard.x.d("should not process ANR too Fre in %d", 10000);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r10.b = r7;
        r10.a.set(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r9 = com.tencent.bugly.proguard.z.a(com.tencent.bugly.crashreport.crash.c.f, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006d, code lost:
        if (r9 == null) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0073, code lost:
        if (r9.size() > 0) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0076, code lost:
        r0 = a(r10.c, 10000);
        r10.l = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007e, code lost:
        if (r0 != null) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0080, code lost:
        com.tencent.bugly.proguard.x.c("proc state is unvisiable!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008e, code lost:
        if (r0.pid == android.os.Process.myPid()) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0090, code lost:
        com.tencent.bugly.proguard.x.c("not mind proc!", r10.l.processName);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009e, code lost:
        com.tencent.bugly.proguard.x.a("found visiable anr , start to process!", new java.lang.Object[0]);
        a(r10.c, r11, r10.l, r7, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00af, code lost:
        com.tencent.bugly.proguard.x.d("can't get all thread skip this anr", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b7, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b8, code lost:
        com.tencent.bugly.proguard.x.a(r11);
        com.tencent.bugly.proguard.x.e("get all thread stack fail!", new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c3, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c8, code lost:
        if (com.tencent.bugly.proguard.x.a(r11) == false) goto L_0x00ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ca, code lost:
        r11.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00cd, code lost:
        com.tencent.bugly.proguard.x.e("handle anr error %s", r11.getClass().toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e0, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e1, code lost:
        r10.a.set(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e6, code lost:
        throw r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r11) {
        /*
            r10 = this;
            monitor-enter(r10)
            java.util.concurrent.atomic.AtomicInteger r0 = r10.a     // Catch:{ all -> 0x00e7 }
            int r0 = r0.get()     // Catch:{ all -> 0x00e7 }
            r1 = 0
            if (r0 == 0) goto L_0x0013
            java.lang.String r11 = "trace started return "
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ all -> 0x00e7 }
            com.tencent.bugly.proguard.x.c(r11, r0)     // Catch:{ all -> 0x00e7 }
            monitor-exit(r10)     // Catch:{ all -> 0x00e7 }
            return
        L_0x0013:
            java.util.concurrent.atomic.AtomicInteger r0 = r10.a     // Catch:{ all -> 0x00e7 }
            r2 = 1
            r0.set(r2)     // Catch:{ all -> 0x00e7 }
            monitor-exit(r10)     // Catch:{ all -> 0x00e7 }
            java.lang.String r0 = "read trace first dump for create time!"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ all -> 0x00c3 }
            com.tencent.bugly.proguard.x.c(r0, r3)     // Catch:{ all -> 0x00c3 }
            com.tencent.bugly.crashreport.crash.anr.TraceFileHelper$a r0 = com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readFirstDumpInfo(r11, r1)     // Catch:{ all -> 0x00c3 }
            r3 = -1
            if (r0 == 0) goto L_0x002c
            long r5 = r0.c     // Catch:{ all -> 0x00c3 }
            goto L_0x002d
        L_0x002c:
            r5 = r3
        L_0x002d:
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 != 0) goto L_0x003c
            java.lang.String r0 = "trace dump fail could not get time!"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ all -> 0x00c3 }
            com.tencent.bugly.proguard.x.d(r0, r3)     // Catch:{ all -> 0x00c3 }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00c3 }
        L_0x003c:
            r7 = r5
            long r3 = r10.b     // Catch:{ all -> 0x00c3 }
            long r3 = r7 - r3
            long r3 = java.lang.Math.abs(r3)     // Catch:{ all -> 0x00c3 }
            r5 = 10000(0x2710, double:4.9407E-320)
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x0060
            java.lang.String r11 = "should not process ANR too Fre in %d"
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch:{ all -> 0x00c3 }
            r3 = 10000(0x2710, float:1.4013E-41)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x00c3 }
            r0[r1] = r3     // Catch:{ all -> 0x00c3 }
            com.tencent.bugly.proguard.x.d(r11, r0)     // Catch:{ all -> 0x00c3 }
        L_0x005a:
            java.util.concurrent.atomic.AtomicInteger r11 = r10.a
            r11.set(r1)
            return
        L_0x0060:
            r10.b = r7     // Catch:{ all -> 0x00c3 }
            java.util.concurrent.atomic.AtomicInteger r0 = r10.a     // Catch:{ all -> 0x00c3 }
            r0.set(r2)     // Catch:{ all -> 0x00c3 }
            int r0 = com.tencent.bugly.crashreport.crash.c.f     // Catch:{ all -> 0x00b7 }
            java.util.Map r9 = com.tencent.bugly.proguard.z.a((int) r0, (boolean) r1)     // Catch:{ all -> 0x00b7 }
            if (r9 == 0) goto L_0x00af
            int r0 = r9.size()     // Catch:{ all -> 0x00c3 }
            if (r0 > 0) goto L_0x0076
            goto L_0x00af
        L_0x0076:
            android.content.Context r0 = r10.c     // Catch:{ all -> 0x00c3 }
            android.app.ActivityManager$ProcessErrorStateInfo r0 = r10.a(r0, r5)     // Catch:{ all -> 0x00c3 }
            r10.l = r0     // Catch:{ all -> 0x00c3 }
            if (r0 != 0) goto L_0x0088
            java.lang.String r11 = "proc state is unvisiable!"
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ all -> 0x00c3 }
            com.tencent.bugly.proguard.x.c(r11, r0)     // Catch:{ all -> 0x00c3 }
            goto L_0x005a
        L_0x0088:
            int r0 = r0.pid     // Catch:{ all -> 0x00c3 }
            int r3 = android.os.Process.myPid()     // Catch:{ all -> 0x00c3 }
            if (r0 == r3) goto L_0x009e
            java.lang.String r11 = "not mind proc!"
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch:{ all -> 0x00c3 }
            android.app.ActivityManager$ProcessErrorStateInfo r3 = r10.l     // Catch:{ all -> 0x00c3 }
            java.lang.String r3 = r3.processName     // Catch:{ all -> 0x00c3 }
            r0[r1] = r3     // Catch:{ all -> 0x00c3 }
            com.tencent.bugly.proguard.x.c(r11, r0)     // Catch:{ all -> 0x00c3 }
            goto L_0x005a
        L_0x009e:
            java.lang.String r0 = "found visiable anr , start to process!"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch:{ all -> 0x00c3 }
            com.tencent.bugly.proguard.x.a(r0, r3)     // Catch:{ all -> 0x00c3 }
            android.content.Context r4 = r10.c     // Catch:{ all -> 0x00c3 }
            android.app.ActivityManager$ProcessErrorStateInfo r6 = r10.l     // Catch:{ all -> 0x00c3 }
            r3 = r10
            r5 = r11
            r3.a(r4, r5, r6, r7, r9)     // Catch:{ all -> 0x00c3 }
            goto L_0x005a
        L_0x00af:
            java.lang.String r11 = "can't get all thread skip this anr"
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ all -> 0x00c3 }
            com.tencent.bugly.proguard.x.d(r11, r0)     // Catch:{ all -> 0x00c3 }
            goto L_0x005a
        L_0x00b7:
            r11 = move-exception
            com.tencent.bugly.proguard.x.a(r11)     // Catch:{ all -> 0x00c3 }
            java.lang.String r11 = "get all thread stack fail!"
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ all -> 0x00c3 }
            com.tencent.bugly.proguard.x.e(r11, r0)     // Catch:{ all -> 0x00c3 }
            goto L_0x005a
        L_0x00c3:
            r11 = move-exception
            boolean r0 = com.tencent.bugly.proguard.x.a(r11)     // Catch:{ all -> 0x00e0 }
            if (r0 != 0) goto L_0x00cd
            r11.printStackTrace()     // Catch:{ all -> 0x00e0 }
        L_0x00cd:
            java.lang.String r0 = "handle anr error %s"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x00e0 }
            java.lang.Class r11 = r11.getClass()     // Catch:{ all -> 0x00e0 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x00e0 }
            r2[r1] = r11     // Catch:{ all -> 0x00e0 }
            com.tencent.bugly.proguard.x.e(r0, r2)     // Catch:{ all -> 0x00e0 }
            goto L_0x005a
        L_0x00e0:
            r11 = move-exception
            java.util.concurrent.atomic.AtomicInteger r0 = r10.a
            r0.set(r1)
            throw r11
        L_0x00e7:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.b.a(java.lang.String):void");
    }

    private synchronized void d() {
        if (f()) {
            x.d("start when started!", new Object[0]);
            return;
        }
        AnonymousClass1 r0 = new FileObserver("/data/anr/", 8) {
            public final void onEvent(int i, String str) {
                if (str != null) {
                    String str2 = "/data/anr/" + str;
                    x.d("watching file %s", str2);
                    if (!str2.contains("trace")) {
                        x.d("not anr file %s", str2);
                        return;
                    }
                    b.this.a(str2);
                }
            }
        };
        this.h = r0;
        try {
            r0.startWatching();
            x.a("start anr monitor!", new Object[0]);
            this.e.a(new Runnable() {
                public final void run() {
                    b.this.b();
                }
            });
        } catch (Throwable th) {
            this.h = null;
            x.d("start anr monitor failed!", new Object[0]);
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
    }

    private synchronized void e() {
        if (!f()) {
            x.d("close when closed!", new Object[0]);
            return;
        }
        try {
            this.h.stopWatching();
            this.h = null;
            x.d("close anr monitor!", new Object[0]);
        } catch (Throwable th) {
            x.d("stop anr monitor failed!", new Object[0]);
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
    }

    private synchronized boolean f() {
        return this.h != null;
    }

    private synchronized void b(boolean z) {
        if (Build.VERSION.SDK_INT <= 19) {
            if (z) {
                d();
            } else {
                e();
            }
        } else if (z) {
            i();
        } else {
            j();
        }
    }

    private synchronized boolean g() {
        return this.i;
    }

    private synchronized void c(boolean z) {
        if (this.i != z) {
            x.a("user change anr %b", Boolean.valueOf(z));
            this.i = z;
        }
    }

    public final void a(boolean z) {
        c(z);
        boolean g2 = g();
        com.tencent.bugly.crashreport.common.strategy.a a2 = com.tencent.bugly.crashreport.common.strategy.a.a();
        if (a2 != null) {
            g2 = g2 && a2.c().e;
        }
        if (g2 != f()) {
            x.a("anr changed to %b", Boolean.valueOf(g2));
            b(g2);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:20|21) */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        com.tencent.bugly.proguard.x.c("Trace file that has invalid format: " + r11, new java.lang.Object[0]);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0062 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b() {
        /*
            r14 = this;
            long r0 = com.tencent.bugly.proguard.z.b()
            long r2 = com.tencent.bugly.crashreport.crash.c.g
            long r0 = r0 - r2
            java.io.File r2 = new java.io.File
            java.lang.String r3 = r14.f
            r2.<init>(r3)
            boolean r3 = r2.exists()
            if (r3 == 0) goto L_0x0098
            boolean r3 = r2.isDirectory()
            if (r3 == 0) goto L_0x0098
            java.io.File[] r2 = r2.listFiles()     // Catch:{ all -> 0x0094 }
            if (r2 == 0) goto L_0x0093
            int r3 = r2.length     // Catch:{ all -> 0x0094 }
            if (r3 != 0) goto L_0x0024
            goto L_0x0093
        L_0x0024:
            java.lang.String r3 = "bugly_trace_"
            java.lang.String r4 = ".txt"
            r5 = 12
            int r6 = r2.length     // Catch:{ all -> 0x0094 }
            r7 = 0
            r8 = 0
            r9 = 0
        L_0x002e:
            if (r8 >= r6) goto L_0x0080
            r10 = r2[r8]     // Catch:{ all -> 0x0094 }
            java.lang.String r11 = r10.getName()     // Catch:{ all -> 0x0094 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0094 }
            java.lang.String r13 = "Number Trace file : "
            r12.<init>(r13)     // Catch:{ all -> 0x0094 }
            r12.append(r11)     // Catch:{ all -> 0x0094 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0094 }
            java.lang.Object[] r13 = new java.lang.Object[r7]     // Catch:{ all -> 0x0094 }
            com.tencent.bugly.proguard.x.c(r12, r13)     // Catch:{ all -> 0x0094 }
            boolean r12 = r11.startsWith(r3)     // Catch:{ all -> 0x0094 }
            if (r12 == 0) goto L_0x007d
            int r12 = r11.indexOf(r4)     // Catch:{ all -> 0x0062 }
            if (r12 <= 0) goto L_0x0075
            java.lang.String r12 = r11.substring(r5, r12)     // Catch:{ all -> 0x0062 }
            long r11 = java.lang.Long.parseLong(r12)     // Catch:{ all -> 0x0062 }
            int r13 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r13 < 0) goto L_0x0075
            goto L_0x007d
        L_0x0062:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0094 }
            java.lang.String r13 = "Trace file that has invalid format: "
            r12.<init>(r13)     // Catch:{ all -> 0x0094 }
            r12.append(r11)     // Catch:{ all -> 0x0094 }
            java.lang.String r11 = r12.toString()     // Catch:{ all -> 0x0094 }
            java.lang.Object[] r12 = new java.lang.Object[r7]     // Catch:{ all -> 0x0094 }
            com.tencent.bugly.proguard.x.c(r11, r12)     // Catch:{ all -> 0x0094 }
        L_0x0075:
            boolean r10 = r10.delete()     // Catch:{ all -> 0x0094 }
            if (r10 == 0) goto L_0x007d
            int r9 = r9 + 1
        L_0x007d:
            int r8 = r8 + 1
            goto L_0x002e
        L_0x0080:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0094 }
            java.lang.String r1 = "Number of overdue trace files that has deleted: "
            r0.<init>(r1)     // Catch:{ all -> 0x0094 }
            r0.append(r9)     // Catch:{ all -> 0x0094 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0094 }
            java.lang.Object[] r1 = new java.lang.Object[r7]     // Catch:{ all -> 0x0094 }
            com.tencent.bugly.proguard.x.c(r0, r1)     // Catch:{ all -> 0x0094 }
        L_0x0093:
            return
        L_0x0094:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        L_0x0098:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.b.b():void");
    }

    public final synchronized void c() {
        x.d("customer decides whether to open or close.", new Object[0]);
    }

    public final boolean a(aa aaVar) {
        Map hashMap = new HashMap();
        if (aaVar.e().equals(Looper.getMainLooper())) {
            try {
                hashMap = z.a(200000, false);
            } catch (Throwable th) {
                x.b(th);
                hashMap.put("main", th.getMessage());
            }
            Map map = hashMap;
            x.c("onThreadBlock found visiable anr , start to process!", new Object[0]);
            a(this.c, "", (ActivityManager.ProcessErrorStateInfo) null, System.currentTimeMillis(), map);
        } else {
            x.c("anr handler onThreadBlock only care main thread ,current thread is: %s", aaVar.d());
        }
        return true;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:21|22|29) */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        com.tencent.bugly.proguard.x.c("Trace file that has invalid format: " + r12, new java.lang.Object[0]);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.io.File h() {
        /*
            r20 = this;
            long r0 = java.lang.System.currentTimeMillis()
            java.io.File r2 = new java.io.File
            r3 = r20
            java.lang.String r4 = r3.f
            r2.<init>(r4)
            boolean r4 = r2.exists()
            if (r4 == 0) goto L_0x0098
            boolean r4 = r2.isDirectory()
            if (r4 == 0) goto L_0x0098
            java.io.File[] r2 = r2.listFiles()     // Catch:{ all -> 0x0092 }
            if (r2 == 0) goto L_0x0090
            int r4 = r2.length     // Catch:{ all -> 0x0092 }
            if (r4 != 0) goto L_0x0023
            goto L_0x0090
        L_0x0023:
            java.lang.String r4 = "bugly_trace_"
            java.lang.String r6 = ".txt"
            r7 = 12
            int r8 = r2.length     // Catch:{ all -> 0x0092 }
            r9 = 0
            r10 = 0
        L_0x002c:
            if (r10 >= r8) goto L_0x0098
            r11 = r2[r10]     // Catch:{ all -> 0x0092 }
            java.lang.String r12 = r11.getName()     // Catch:{ all -> 0x0092 }
            boolean r13 = r12.startsWith(r4)     // Catch:{ all -> 0x0092 }
            if (r13 == 0) goto L_0x008b
            int r13 = r12.indexOf(r6)     // Catch:{ all -> 0x0078 }
            if (r13 <= 0) goto L_0x008b
            java.lang.String r13 = r12.substring(r7, r13)     // Catch:{ all -> 0x0078 }
            long r13 = java.lang.Long.parseLong(r13)     // Catch:{ all -> 0x0078 }
            long r15 = r0 - r13
            r17 = 1000(0x3e8, double:4.94E-321)
            long r15 = r15 / r17
            java.lang.String r7 = "current time %d trace time is %d s"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x0078 }
            java.lang.Long r19 = java.lang.Long.valueOf(r0)     // Catch:{ all -> 0x0078 }
            r5[r9] = r19     // Catch:{ all -> 0x0078 }
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x0078 }
            r14 = 1
            r5[r14] = r13     // Catch:{ all -> 0x0078 }
            com.tencent.bugly.proguard.x.c(r7, r5)     // Catch:{ all -> 0x0078 }
            java.lang.String r5 = "current time minus trace time is %d s"
            java.lang.Object[] r7 = new java.lang.Object[r14]     // Catch:{ all -> 0x0078 }
            java.lang.Long r13 = java.lang.Long.valueOf(r15)     // Catch:{ all -> 0x0078 }
            r7[r9] = r13     // Catch:{ all -> 0x0078 }
            com.tencent.bugly.proguard.x.c(r5, r7)     // Catch:{ all -> 0x0078 }
            r12 = 30
            int r5 = (r15 > r12 ? 1 : (r15 == r12 ? 0 : -1))
            if (r5 < 0) goto L_0x0077
            goto L_0x008b
        L_0x0077:
            return r11
        L_0x0078:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0092 }
            java.lang.String r7 = "Trace file that has invalid format: "
            r5.<init>(r7)     // Catch:{ all -> 0x0092 }
            r5.append(r12)     // Catch:{ all -> 0x0092 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0092 }
            java.lang.Object[] r7 = new java.lang.Object[r9]     // Catch:{ all -> 0x0092 }
            com.tencent.bugly.proguard.x.c(r5, r7)     // Catch:{ all -> 0x0092 }
        L_0x008b:
            int r10 = r10 + 1
            r7 = 12
            goto L_0x002c
        L_0x0090:
            r1 = 0
            return r1
        L_0x0092:
            r0 = move-exception
            r1 = 0
            com.tencent.bugly.proguard.x.a(r0)
            goto L_0x0099
        L_0x0098:
            r1 = 0
        L_0x0099:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.b.h():java.io.File");
    }

    private synchronized void i() {
        if (f()) {
            x.d("start when started!", new Object[0]);
        } else if (!TextUtils.isEmpty(this.f)) {
            if (this.j == null || !this.j.isAlive()) {
                ab abVar = new ab();
                this.j = abVar;
                StringBuilder sb = new StringBuilder("Bugly-ThreadMonitor");
                int i2 = this.k;
                this.k = i2 + 1;
                sb.append(i2);
                abVar.setName(sb.toString());
                this.j.a();
                this.j.a((ac) this);
                this.j.d();
                this.e.a(new Runnable() {
                    public final void run() {
                        b.this.b();
                    }
                });
            }
            AnonymousClass4 r0 = new FileObserver(this.f, 256) {
                public final void onEvent(int i, String str) {
                    if (str != null) {
                        x.d("startWatchingPrivateAnrDir %s", str);
                        String str2 = "/data/anr/" + str;
                        if (!str2.contains("trace")) {
                            x.d("not anr file %s", str2);
                        } else if (b.this.j != null) {
                            b.this.j.a(true);
                        }
                    }
                }
            };
            this.h = r0;
            try {
                r0.startWatching();
                x.a("startWatchingPrivateAnrDir! dumFilePath is %s", this.f);
                this.e.a(new Runnable() {
                    public final void run() {
                        b.this.b();
                    }
                });
            } catch (Throwable th) {
                this.h = null;
                x.d("startWatchingPrivateAnrDir failed!", new Object[0]);
                if (!x.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    private synchronized void j() {
        if (!f()) {
            x.d("close when closed!", new Object[0]);
            return;
        }
        if (this.j != null) {
            this.j.c();
            this.j.b();
            this.j.b(this);
            this.j = null;
        }
        x.a("stopWatchingPrivateAnrDir", new Object[0]);
        try {
            this.h.stopWatching();
            this.h = null;
            x.d("close anr monitor!", new Object[0]);
        } catch (Throwable th) {
            x.d("stop anr monitor failed!", new Object[0]);
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
    }
}
