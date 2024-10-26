package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.b;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.util.Map;

/* compiled from: BUGLY */
public final class a implements NativeExceptionHandler {
    private final Context a;
    private final b b;
    private final com.tencent.bugly.crashreport.common.info.a c;
    private final com.tencent.bugly.crashreport.common.strategy.a d;

    public a(Context context, com.tencent.bugly.crashreport.common.info.a aVar, b bVar, com.tencent.bugly.crashreport.common.strategy.a aVar2) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = aVar2;
    }

    public final CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, byte[] bArr, Map<String, String> map, boolean z, boolean z2) {
        int i;
        String str12;
        int indexOf;
        String str13 = str;
        String str14 = str8;
        byte[] bArr2 = bArr;
        boolean l = c.a().l();
        if (l) {
            x.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.b = 1;
        crashDetailBean.e = this.c.h();
        crashDetailBean.f = this.c.k;
        crashDetailBean.g = this.c.u();
        crashDetailBean.m = this.c.g();
        crashDetailBean.n = str3;
        String str15 = "";
        crashDetailBean.o = l ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : str15;
        crashDetailBean.p = str4;
        if (str5 != null) {
            str15 = str5;
        }
        crashDetailBean.q = str15;
        crashDetailBean.r = j;
        crashDetailBean.u = z.a(crashDetailBean.q.getBytes());
        crashDetailBean.A = str13;
        crashDetailBean.B = str2;
        crashDetailBean.I = this.c.w();
        crashDetailBean.h = this.c.t();
        crashDetailBean.i = this.c.F();
        crashDetailBean.v = str14;
        NativeCrashHandler instance = NativeCrashHandler.getInstance();
        String dumpFilePath = instance != null ? instance.getDumpFilePath() : null;
        String a2 = b.a(dumpFilePath, str14);
        if (!z.a(a2)) {
            crashDetailBean.V = a2;
        }
        crashDetailBean.W = b.b(dumpFilePath);
        crashDetailBean.w = b.a(str9, c.e, (String) null, false);
        crashDetailBean.x = b.a(str10, c.e, (String) null, true);
        crashDetailBean.J = str7;
        crashDetailBean.K = str6;
        crashDetailBean.L = str11;
        crashDetailBean.F = this.c.o();
        crashDetailBean.G = this.c.n();
        crashDetailBean.H = this.c.p();
        if (z) {
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.j();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.h();
            crashDetailBean.E = com.tencent.bugly.crashreport.common.info.b.l();
            if (crashDetailBean.w == null) {
                crashDetailBean.w = z.a(this.a, c.e, (String) null);
            }
            crashDetailBean.y = y.a();
            crashDetailBean.M = this.c.a;
            crashDetailBean.N = this.c.a();
            crashDetailBean.z = z.a(c.f, false);
            int indexOf2 = crashDetailBean.q.indexOf("java:\n");
            if (indexOf2 > 0 && (i = indexOf2 + 6) < crashDetailBean.q.length()) {
                String substring = crashDetailBean.q.substring(i, crashDetailBean.q.length() - 1);
                if (substring.length() > 0 && crashDetailBean.z.containsKey(crashDetailBean.B) && (indexOf = str12.indexOf(substring)) > 0) {
                    String substring2 = (str12 = crashDetailBean.z.get(crashDetailBean.B)).substring(indexOf);
                    crashDetailBean.z.put(crashDetailBean.B, substring2);
                    crashDetailBean.q = crashDetailBean.q.substring(0, i);
                    crashDetailBean.q += substring2;
                }
            }
            if (str13 == null) {
                crashDetailBean.A = this.c.d;
            }
            this.b.d(crashDetailBean);
            crashDetailBean.Q = this.c.D();
            crashDetailBean.R = this.c.E();
            crashDetailBean.S = this.c.x();
            crashDetailBean.T = this.c.C();
        } else {
            crashDetailBean.C = -1;
            crashDetailBean.D = -1;
            crashDetailBean.E = -1;
            if (crashDetailBean.w == null) {
                crashDetailBean.w = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            }
            crashDetailBean.M = -1;
            crashDetailBean.Q = -1;
            crashDetailBean.R = -1;
            crashDetailBean.S = map;
            crashDetailBean.T = this.c.C();
            crashDetailBean.z = null;
            if (str13 == null) {
                crashDetailBean.A = "unknown(record)";
            }
            if (bArr2 != null) {
                crashDetailBean.y = bArr2;
            }
        }
        return crashDetailBean;
    }

    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        int i7 = i;
        x.a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, (String[]) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00fa A[Catch:{ all -> 0x0291 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01ac A[Catch:{ all -> 0x0291 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x01bf A[SYNTHETIC, Splitter:B:62:0x01bf] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0223 A[Catch:{ all -> 0x028d }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x022c A[Catch:{ all -> 0x028d }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0299  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void handleNativeException2(int r27, int r28, long r29, long r31, java.lang.String r33, java.lang.String r34, java.lang.String r35, java.lang.String r36, int r37, java.lang.String r38, int r39, int r40, int r41, java.lang.String r42, java.lang.String r43, java.lang.String[] r44) {
        /*
            r26 = this;
            r14 = r26
            r0 = r34
            r1 = r39
            r2 = r44
            r13 = 0
            java.lang.Object[] r3 = new java.lang.Object[r13]
            java.lang.String r4 = "Native Crash Happen v2"
            com.tencent.bugly.proguard.x.a(r4, r3)
            java.lang.String r12 = com.tencent.bugly.crashreport.crash.jni.b.a((java.lang.String) r35)     // Catch:{ all -> 0x0291 }
            java.lang.String r3 = "UNKNOWN"
            java.lang.String r4 = ")"
            java.lang.String r5 = "("
            if (r37 <= 0) goto L_0x003b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0291 }
            r1.<init>()     // Catch:{ all -> 0x0291 }
            r6 = r33
            r1.append(r6)     // Catch:{ all -> 0x0291 }
            r1.append(r5)     // Catch:{ all -> 0x0291 }
            r7 = r38
            r1.append(r7)     // Catch:{ all -> 0x0291 }
            r1.append(r4)     // Catch:{ all -> 0x0291 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0291 }
            java.lang.String r6 = "KERNEL"
            r11 = r1
            r10 = r3
            r9 = r6
            goto L_0x0069
        L_0x003b:
            r6 = r33
            r7 = r38
            if (r1 <= 0) goto L_0x0045
            java.lang.String r3 = com.tencent.bugly.crashreport.common.info.AppInfo.a((int) r39)     // Catch:{ all -> 0x0291 }
        L_0x0045:
            java.lang.String r8 = java.lang.String.valueOf(r39)     // Catch:{ all -> 0x0291 }
            boolean r8 = r3.equals(r8)     // Catch:{ all -> 0x0291 }
            if (r8 != 0) goto L_0x0066
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0291 }
            r8.<init>()     // Catch:{ all -> 0x0291 }
            r8.append(r3)     // Catch:{ all -> 0x0291 }
            r8.append(r5)     // Catch:{ all -> 0x0291 }
            r8.append(r1)     // Catch:{ all -> 0x0291 }
            r8.append(r4)     // Catch:{ all -> 0x0291 }
            java.lang.String r1 = r8.toString()     // Catch:{ all -> 0x0291 }
            r10 = r1
            goto L_0x0067
        L_0x0066:
            r10 = r3
        L_0x0067:
            r11 = r6
            r9 = r7
        L_0x0069:
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ all -> 0x0291 }
            r1.<init>()     // Catch:{ all -> 0x0291 }
            if (r2 == 0) goto L_0x00a9
            r3 = 0
        L_0x0071:
            int r6 = r2.length     // Catch:{ all -> 0x0291 }
            if (r3 >= r6) goto L_0x00b0
            r6 = r2[r3]     // Catch:{ all -> 0x0291 }
            if (r6 == 0) goto L_0x00a6
            java.lang.String r7 = "Extra message[%d]: %s"
            r15 = 2
            java.lang.Object[] r8 = new java.lang.Object[r15]     // Catch:{ all -> 0x0291 }
            java.lang.Integer r16 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0291 }
            r8[r13] = r16     // Catch:{ all -> 0x0291 }
            r16 = 1
            r8[r16] = r6     // Catch:{ all -> 0x0291 }
            com.tencent.bugly.proguard.x.a(r7, r8)     // Catch:{ all -> 0x0291 }
            java.lang.String r7 = "="
            java.lang.String[] r7 = r6.split(r7)     // Catch:{ all -> 0x0291 }
            int r8 = r7.length     // Catch:{ all -> 0x0291 }
            if (r8 != r15) goto L_0x009c
            r6 = r7[r13]     // Catch:{ all -> 0x0291 }
            r8 = 1
            r7 = r7[r8]     // Catch:{ all -> 0x0291 }
            r1.put(r6, r7)     // Catch:{ all -> 0x0291 }
            goto L_0x00a6
        L_0x009c:
            java.lang.String r7 = "bad extraMsg %s"
            r8 = 1
            java.lang.Object[] r15 = new java.lang.Object[r8]     // Catch:{ all -> 0x0291 }
            r15[r13] = r6     // Catch:{ all -> 0x0291 }
            com.tencent.bugly.proguard.x.d(r7, r15)     // Catch:{ all -> 0x0291 }
        L_0x00a6:
            int r3 = r3 + 1
            goto L_0x0071
        L_0x00a9:
            java.lang.String r2 = "not found extraMsg"
            java.lang.Object[] r3 = new java.lang.Object[r13]     // Catch:{ all -> 0x0291 }
            com.tencent.bugly.proguard.x.c(r2, r3)     // Catch:{ all -> 0x0291 }
        L_0x00b0:
            java.lang.String r2 = "HasPendingException"
            java.lang.Object r2 = r1.get(r2)     // Catch:{ all -> 0x0291 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0291 }
            if (r2 == 0) goto L_0x00cc
            java.lang.String r3 = "true"
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x0291 }
            if (r2 == 0) goto L_0x00cc
            java.lang.String r2 = "Native crash happened with a Java pending exception."
            java.lang.Object[] r3 = new java.lang.Object[r13]     // Catch:{ all -> 0x0291 }
            com.tencent.bugly.proguard.x.a(r2, r3)     // Catch:{ all -> 0x0291 }
            r18 = 1
            goto L_0x00ce
        L_0x00cc:
            r18 = 0
        L_0x00ce:
            java.lang.String r2 = "ExceptionProcessName"
            java.lang.Object r2 = r1.get(r2)     // Catch:{ all -> 0x0291 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0291 }
            if (r2 == 0) goto L_0x00ea
            int r3 = r2.length()     // Catch:{ all -> 0x0291 }
            if (r3 != 0) goto L_0x00df
            goto L_0x00ea
        L_0x00df:
            java.lang.String r3 = "Name of crash process: %s"
            r6 = 1
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch:{ all -> 0x0291 }
            r7[r13] = r2     // Catch:{ all -> 0x0291 }
            com.tencent.bugly.proguard.x.c(r3, r7)     // Catch:{ all -> 0x0291 }
            goto L_0x00ee
        L_0x00ea:
            com.tencent.bugly.crashreport.common.info.a r2 = r14.c     // Catch:{ all -> 0x0291 }
            java.lang.String r2 = r2.d     // Catch:{ all -> 0x0291 }
        L_0x00ee:
            r19 = r2
            java.lang.String r2 = "ExceptionThreadName"
            java.lang.Object r2 = r1.get(r2)     // Catch:{ all -> 0x0291 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x0291 }
            if (r2 == 0) goto L_0x0165
            int r3 = r2.length()     // Catch:{ all -> 0x0291 }
            if (r3 != 0) goto L_0x0101
            goto L_0x0165
        L_0x0101:
            java.lang.String r3 = "Name of crash thread: %s"
            r8 = 1
            java.lang.Object[] r6 = new java.lang.Object[r8]     // Catch:{ all -> 0x0291 }
            r6[r13] = r2     // Catch:{ all -> 0x0291 }
            com.tencent.bugly.proguard.x.c(r3, r6)     // Catch:{ all -> 0x0291 }
            java.util.Map r3 = java.lang.Thread.getAllStackTraces()     // Catch:{ all -> 0x0291 }
            java.util.Set r3 = r3.keySet()     // Catch:{ all -> 0x0291 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0291 }
        L_0x0117:
            boolean r6 = r3.hasNext()     // Catch:{ all -> 0x0291 }
            if (r6 == 0) goto L_0x0149
            java.lang.Object r6 = r3.next()     // Catch:{ all -> 0x0291 }
            java.lang.Thread r6 = (java.lang.Thread) r6     // Catch:{ all -> 0x0291 }
            java.lang.String r7 = r6.getName()     // Catch:{ all -> 0x0291 }
            boolean r7 = r7.equals(r2)     // Catch:{ all -> 0x0291 }
            if (r7 == 0) goto L_0x0117
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0291 }
            r3.<init>()     // Catch:{ all -> 0x0291 }
            r3.append(r2)     // Catch:{ all -> 0x0291 }
            r3.append(r5)     // Catch:{ all -> 0x0291 }
            long r6 = r6.getId()     // Catch:{ all -> 0x0291 }
            r3.append(r6)     // Catch:{ all -> 0x0291 }
            r3.append(r4)     // Catch:{ all -> 0x0291 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0291 }
            r16 = 1
            goto L_0x014b
        L_0x0149:
            r16 = 0
        L_0x014b:
            if (r16 != 0) goto L_0x0187
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0291 }
            r3.<init>()     // Catch:{ all -> 0x0291 }
            r3.append(r2)     // Catch:{ all -> 0x0291 }
            r3.append(r5)     // Catch:{ all -> 0x0291 }
            r2 = r28
            r3.append(r2)     // Catch:{ all -> 0x0291 }
            r3.append(r4)     // Catch:{ all -> 0x0291 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0291 }
            goto L_0x0187
        L_0x0165:
            r8 = 1
            java.lang.Thread r2 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0291 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0291 }
            r3.<init>()     // Catch:{ all -> 0x0291 }
            java.lang.String r6 = r2.getName()     // Catch:{ all -> 0x0291 }
            r3.append(r6)     // Catch:{ all -> 0x0291 }
            r3.append(r5)     // Catch:{ all -> 0x0291 }
            long r5 = r2.getId()     // Catch:{ all -> 0x0291 }
            r3.append(r5)     // Catch:{ all -> 0x0291 }
            r3.append(r4)     // Catch:{ all -> 0x0291 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0291 }
        L_0x0187:
            r20 = r2
            r2 = 1000(0x3e8, double:4.94E-321)
            long r4 = r29 * r2
            long r2 = r31 / r2
            long r4 = r4 + r2
            java.lang.String r2 = "SysLogPath"
            java.lang.Object r2 = r1.get(r2)     // Catch:{ all -> 0x0291 }
            r21 = r2
            java.lang.String r21 = (java.lang.String) r21     // Catch:{ all -> 0x0291 }
            java.lang.String r2 = "JniLogPath"
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0291 }
            r22 = r1
            java.lang.String r22 = (java.lang.String) r22     // Catch:{ all -> 0x0291 }
            com.tencent.bugly.crashreport.common.strategy.a r1 = r14.d     // Catch:{ all -> 0x0291 }
            boolean r1 = r1.b()     // Catch:{ all -> 0x0291 }
            if (r1 != 0) goto L_0x01b3
            java.lang.String r1 = "no remote but still store!"
            java.lang.Object[] r2 = new java.lang.Object[r13]     // Catch:{ all -> 0x0291 }
            com.tencent.bugly.proguard.x.d(r1, r2)     // Catch:{ all -> 0x0291 }
        L_0x01b3:
            com.tencent.bugly.crashreport.common.strategy.a r1 = r14.d     // Catch:{ all -> 0x0291 }
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r1 = r1.c()     // Catch:{ all -> 0x0291 }
            boolean r1 = r1.e     // Catch:{ all -> 0x0291 }
            java.lang.String r7 = "\n"
            if (r1 != 0) goto L_0x0200
            com.tencent.bugly.crashreport.common.strategy.a r1 = r14.d     // Catch:{ all -> 0x0291 }
            boolean r1 = r1.b()     // Catch:{ all -> 0x0291 }
            if (r1 == 0) goto L_0x0200
            java.lang.String r1 = "crash report was closed by remote , will not upload to Bugly , print local for helpful!"
            java.lang.Object[] r2 = new java.lang.Object[r13]     // Catch:{ all -> 0x0291 }
            com.tencent.bugly.proguard.x.e(r1, r2)     // Catch:{ all -> 0x0291 }
            java.lang.String r1 = "NATIVE_CRASH"
            java.lang.String r2 = com.tencent.bugly.proguard.z.a()     // Catch:{ all -> 0x0291 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0291 }
            r3.<init>()     // Catch:{ all -> 0x0291 }
            r3.append(r11)     // Catch:{ all -> 0x0291 }
            r3.append(r7)     // Catch:{ all -> 0x0291 }
            r3.append(r0)     // Catch:{ all -> 0x0291 }
            r3.append(r7)     // Catch:{ all -> 0x0291 }
            r3.append(r12)     // Catch:{ all -> 0x0291 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0291 }
            r3 = 0
            r28 = r1
            r29 = r2
            r30 = r19
            r31 = r20
            r32 = r0
            r33 = r3
            com.tencent.bugly.crashreport.crash.b.a(r28, r29, r30, r31, r32, r33)     // Catch:{ all -> 0x0291 }
            com.tencent.bugly.proguard.z.b((java.lang.String) r36)     // Catch:{ all -> 0x0291 }
            return
        L_0x0200:
            r15 = 0
            r16 = 0
            r17 = 1
            r1 = r26
            r2 = r19
            r3 = r20
            r6 = r11
            r23 = r7
            r7 = r34
            r8 = r12
            r24 = r11
            r11 = r36
            r25 = r12
            r12 = r21
            r13 = r22
            r14 = r43
            com.tencent.bugly.crashreport.crash.CrashDetailBean r1 = r1.packageCrashDatas(r2, r3, r4, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ all -> 0x028d }
            if (r1 != 0) goto L_0x022c
            java.lang.String r0 = "pkg crash datas fail!"
            r2 = 0
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x028d }
            com.tencent.bugly.proguard.x.e(r0, r1)     // Catch:{ all -> 0x028d }
            return
        L_0x022c:
            r2 = 0
            java.lang.String r3 = "NATIVE_CRASH"
            java.lang.String r4 = com.tencent.bugly.proguard.z.a()     // Catch:{ all -> 0x028d }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x028d }
            r5.<init>()     // Catch:{ all -> 0x028d }
            r6 = r24
            r5.append(r6)     // Catch:{ all -> 0x028d }
            r6 = r23
            r5.append(r6)     // Catch:{ all -> 0x028d }
            r5.append(r0)     // Catch:{ all -> 0x028d }
            r5.append(r6)     // Catch:{ all -> 0x028d }
            r0 = r25
            r5.append(r0)     // Catch:{ all -> 0x028d }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x028d }
            r28 = r3
            r29 = r4
            r30 = r19
            r31 = r20
            r32 = r0
            r33 = r1
            com.tencent.bugly.crashreport.crash.b.a(r28, r29, r30, r31, r32, r33)     // Catch:{ all -> 0x028d }
            r3 = r26
            com.tencent.bugly.crashreport.crash.b r0 = r3.b     // Catch:{ all -> 0x028b }
            boolean r0 = r0.b((com.tencent.bugly.crashreport.crash.CrashDetailBean) r1)     // Catch:{ all -> 0x028b }
            if (r0 != 0) goto L_0x026c
            r13 = 1
            goto L_0x026d
        L_0x026c:
            r13 = 0
        L_0x026d:
            r0 = 0
            com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler r2 = com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.getInstance()     // Catch:{ all -> 0x028b }
            if (r2 == 0) goto L_0x0278
            java.lang.String r0 = r2.getDumpFilePath()     // Catch:{ all -> 0x028b }
        L_0x0278:
            r2 = 1
            com.tencent.bugly.crashreport.crash.jni.b.a((boolean) r2, (java.lang.String) r0)     // Catch:{ all -> 0x028b }
            if (r13 == 0) goto L_0x0285
            com.tencent.bugly.crashreport.crash.b r0 = r3.b     // Catch:{ all -> 0x028b }
            r4 = 3000(0xbb8, double:1.482E-320)
            r0.a((com.tencent.bugly.crashreport.crash.CrashDetailBean) r1, (long) r4, (boolean) r2)     // Catch:{ all -> 0x028b }
        L_0x0285:
            com.tencent.bugly.crashreport.crash.b r0 = r3.b     // Catch:{ all -> 0x028b }
            r0.c((com.tencent.bugly.crashreport.crash.CrashDetailBean) r1)     // Catch:{ all -> 0x028b }
            return
        L_0x028b:
            r0 = move-exception
            goto L_0x0293
        L_0x028d:
            r0 = move-exception
            r3 = r26
            goto L_0x0293
        L_0x0291:
            r0 = move-exception
            r3 = r14
        L_0x0293:
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x029c
            r0.printStackTrace()
        L_0x029c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.a.handleNativeException2(int, int, long, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, int, int, int, java.lang.String, java.lang.String, java.lang.String[]):void");
    }
}
