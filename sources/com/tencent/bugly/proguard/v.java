package com.tencent.bugly.proguard;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.a;
import java.util.Map;
import java.util.UUID;

/* compiled from: BUGLY */
public final class v implements Runnable {
    private int a;
    private int b;
    private final Context c;
    private final int d;
    private final byte[] e;
    private final a f;
    private final com.tencent.bugly.crashreport.common.strategy.a g;
    private final s h;
    private final u i;
    private final int j;
    private final t k;
    private final t l;
    private String m;
    private final String n;
    private final Map<String, String> o;
    private int p;
    private long q;
    private long r;
    private boolean s;

    public v(Context context, int i2, int i3, byte[] bArr, String str, String str2, t tVar, boolean z, boolean z2) {
        this(context, i2, i3, bArr, str, str2, tVar, 2, 30000, z2, (Map<String, String>) null);
    }

    public v(Context context, int i2, int i3, byte[] bArr, String str, String str2, t tVar, int i4, int i5, boolean z, Map<String, String> map) {
        this.a = 2;
        this.b = 30000;
        this.m = null;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = false;
        this.c = context;
        this.f = a.a(context);
        this.e = bArr;
        this.g = com.tencent.bugly.crashreport.common.strategy.a.a();
        this.h = s.a(context);
        this.i = u.a();
        this.j = i2;
        this.m = str;
        this.n = str2;
        this.k = tVar;
        this.l = null;
        this.d = i3;
        if (i4 > 0) {
            this.a = i4;
        }
        if (i5 > 0) {
            this.b = i5;
        }
        this.s = z;
        this.o = map;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.tencent.bugly.proguard.an r4, boolean r5, int r6, java.lang.String r7) {
        /*
            r3 = this;
            int r4 = r3.d
            r0 = 630(0x276, float:8.83E-43)
            if (r4 == r0) goto L_0x001a
            r0 = 640(0x280, float:8.97E-43)
            if (r4 == r0) goto L_0x0017
            r0 = 830(0x33e, float:1.163E-42)
            if (r4 == r0) goto L_0x001a
            r0 = 840(0x348, float:1.177E-42)
            if (r4 == r0) goto L_0x0017
            java.lang.String r4 = java.lang.String.valueOf(r4)
            goto L_0x001c
        L_0x0017:
            java.lang.String r4 = "userinfo"
            goto L_0x001c
        L_0x001a:
            java.lang.String r4 = "crash"
        L_0x001c:
            r0 = 1
            r1 = 0
            if (r5 == 0) goto L_0x002a
            java.lang.Object[] r6 = new java.lang.Object[r0]
            r6[r1] = r4
            java.lang.String r4 = "[Upload] Success: %s"
            com.tencent.bugly.proguard.x.a(r4, r6)
            goto L_0x003d
        L_0x002a:
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r2[r1] = r6
            r2[r0] = r4
            r4 = 2
            r2[r4] = r7
            java.lang.String r4 = "[Upload] Failed to upload(%d) %s: %s"
            com.tencent.bugly.proguard.x.e(r4, r2)
        L_0x003d:
            long r6 = r3.q
            long r0 = r3.r
            long r6 = r6 + r0
            r0 = 0
            int r4 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r4 <= 0) goto L_0x005d
            com.tencent.bugly.proguard.u r4 = r3.i
            boolean r6 = r3.s
            long r6 = r4.a((boolean) r6)
            long r0 = r3.q
            long r6 = r6 + r0
            long r0 = r3.r
            long r6 = r6 + r0
            com.tencent.bugly.proguard.u r4 = r3.i
            boolean r0 = r3.s
            r4.a((long) r6, (boolean) r0)
        L_0x005d:
            com.tencent.bugly.proguard.t r4 = r3.k
            if (r4 == 0) goto L_0x0064
            r4.a(r5)
        L_0x0064:
            com.tencent.bugly.proguard.t r4 = r3.l
            if (r4 == 0) goto L_0x006b
            r4.a(r5)
        L_0x006b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.v.a(com.tencent.bugly.proguard.an, boolean, int, java.lang.String):void");
    }

    private static boolean a(an anVar, a aVar, com.tencent.bugly.crashreport.common.strategy.a aVar2) {
        if (anVar == null) {
            x.d("resp == null!", new Object[0]);
            return false;
        } else if (anVar.a != 0) {
            x.e("resp result error %d", Byte.valueOf(anVar.a));
            return false;
        } else {
            try {
                if (!z.a(anVar.d)) {
                    if (!a.b().i().equals(anVar.d)) {
                        p.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, "gateway", anVar.d.getBytes("UTF-8"), (o) null, true);
                        aVar.d(anVar.d);
                    }
                }
                if (!z.a(anVar.f) && !a.b().j().equals(anVar.f)) {
                    p.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, "device", anVar.f.getBytes("UTF-8"), (o) null, true);
                    aVar.e(anVar.f);
                }
            } catch (Throwable th) {
                x.a(th);
            }
            aVar.j = anVar.e;
            if (anVar.b == 510) {
                if (anVar.c == null) {
                    x.e("[Upload] Strategy data is null. Response cmd: %d", Integer.valueOf(anVar.b));
                    return false;
                }
                ap apVar = (ap) a.a(anVar.c, ap.class);
                if (apVar == null) {
                    x.e("[Upload] Failed to decode strategy from server. Response cmd: %d", Integer.valueOf(anVar.b));
                    return false;
                }
                aVar2.a(apVar);
            }
            return true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x02b7, code lost:
        r11 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x02b8, code lost:
        r0 = com.tencent.bugly.proguard.a.b(r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x02bc, code lost:
        if (r0 != null) goto L_0x02c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x02be, code lost:
        a((com.tencent.bugly.proguard.an) null, false, 1, "failed to decode response package");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x02c4, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x02c5, code lost:
        r5 = new java.lang.Object[2];
        r5[0] = java.lang.Integer.valueOf(r0.b);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x02d4, code lost:
        if (r0.c != null) goto L_0x02d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x02d6, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x02d8, code lost:
        r4 = r0.c.length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x02db, code lost:
        r5[1] = java.lang.Integer.valueOf(r4);
        com.tencent.bugly.proguard.x.c("[Upload] Response cmd is: %d, length of sBuffer is: %d", r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x02ec, code lost:
        if (a(r0, r1.f, r1.g) != false) goto L_0x02f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x02ee, code lost:
        a(r0, false, 2, "failed to process response package");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x02f4, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x02f5, code lost:
        a(r0, true, 2, "successfully uploaded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x02fb, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0251, code lost:
        if (r2 == 0) goto L_0x0266;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:?, code lost:
        a((com.tencent.bugly.proguard.an) null, false, 1, "status of server is " + r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0265, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0266, code lost:
        com.tencent.bugly.proguard.x.c("[Upload] Received %d bytes", java.lang.Integer.valueOf(r11.length));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0275, code lost:
        if (r11.length != 0) goto L_0x02a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0277, code lost:
        r0 = r14.entrySet().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0283, code lost:
        if (r0.hasNext() == false) goto L_0x02a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0285, code lost:
        r2 = r0.next();
        com.tencent.bugly.proguard.x.c("[Upload] HTTP headers from server: key = %s, value = %s", r2.getKey(), r2.getValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x02a0, code lost:
        a((com.tencent.bugly.proguard.an) null, false, 1, "response data from server is empty");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x02a6, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x02a7, code lost:
        if (r11 != null) goto L_0x02b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x02a9, code lost:
        a((com.tencent.bugly.proguard.an) null, false, 1, "failed to decrypt response from server");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x02af, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x02b0, code lost:
        r2 = com.tencent.bugly.proguard.z.b(r11, 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x02b5, code lost:
        if (r2 == null) goto L_0x02b8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01be A[Catch:{ all -> 0x02fc, all -> 0x0338 }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0224 A[SYNTHETIC, Splitter:B:78:0x0224] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r18 = this;
            r1 = r18
            java.lang.String r0 = "[Upload] Failed to upload for no status header."
            java.lang.String r2 = "Bugly-Version"
            r3 = 0
            r1.p = r3     // Catch:{ all -> 0x0338 }
            r4 = 0
            r1.q = r4     // Catch:{ all -> 0x0338 }
            r1.r = r4     // Catch:{ all -> 0x0338 }
            byte[] r4 = r1.e     // Catch:{ all -> 0x0338 }
            android.content.Context r5 = r1.c     // Catch:{ all -> 0x0338 }
            java.lang.String r5 = com.tencent.bugly.crashreport.common.info.b.b(r5)     // Catch:{ all -> 0x0338 }
            r6 = 0
            if (r5 != 0) goto L_0x0020
            java.lang.String r0 = "network is not available"
            r1.a(r6, r3, r3, r0)     // Catch:{ all -> 0x0338 }
            return
        L_0x0020:
            if (r4 == 0) goto L_0x0331
            int r5 = r4.length     // Catch:{ all -> 0x0338 }
            if (r5 != 0) goto L_0x0027
            goto L_0x0331
        L_0x0027:
            java.lang.String r5 = "[Upload] Run upload task with cmd: %d"
            r7 = 1
            java.lang.Object[] r8 = new java.lang.Object[r7]     // Catch:{ all -> 0x0338 }
            int r9 = r1.d     // Catch:{ all -> 0x0338 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0338 }
            r8[r3] = r9     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.c(r5, r8)     // Catch:{ all -> 0x0338 }
            android.content.Context r5 = r1.c     // Catch:{ all -> 0x0338 }
            if (r5 == 0) goto L_0x032a
            com.tencent.bugly.crashreport.common.info.a r5 = r1.f     // Catch:{ all -> 0x0338 }
            if (r5 == 0) goto L_0x032a
            com.tencent.bugly.crashreport.common.strategy.a r5 = r1.g     // Catch:{ all -> 0x0338 }
            if (r5 == 0) goto L_0x032a
            com.tencent.bugly.proguard.s r5 = r1.h     // Catch:{ all -> 0x0338 }
            if (r5 != 0) goto L_0x0049
            goto L_0x032a
        L_0x0049:
            com.tencent.bugly.crashreport.common.strategy.a r5 = r1.g     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r5 = r5.c()     // Catch:{ all -> 0x0338 }
            if (r5 != 0) goto L_0x0057
            java.lang.String r0 = "illegal local strategy"
            r1.a(r6, r3, r3, r0)     // Catch:{ all -> 0x0338 }
            return
        L_0x0057:
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ all -> 0x0338 }
            r8.<init>()     // Catch:{ all -> 0x0338 }
            java.lang.String r9 = "tls"
            java.lang.String r10 = "1"
            r8.put(r9, r10)     // Catch:{ all -> 0x0338 }
            java.lang.String r9 = "prodId"
            com.tencent.bugly.crashreport.common.info.a r10 = r1.f     // Catch:{ all -> 0x0338 }
            java.lang.String r10 = r10.f()     // Catch:{ all -> 0x0338 }
            r8.put(r9, r10)     // Catch:{ all -> 0x0338 }
            java.lang.String r9 = "bundleId"
            com.tencent.bugly.crashreport.common.info.a r10 = r1.f     // Catch:{ all -> 0x0338 }
            java.lang.String r10 = r10.c     // Catch:{ all -> 0x0338 }
            r8.put(r9, r10)     // Catch:{ all -> 0x0338 }
            java.lang.String r9 = "appVer"
            com.tencent.bugly.crashreport.common.info.a r10 = r1.f     // Catch:{ all -> 0x0338 }
            java.lang.String r10 = r10.k     // Catch:{ all -> 0x0338 }
            r8.put(r9, r10)     // Catch:{ all -> 0x0338 }
            java.util.Map<java.lang.String, java.lang.String> r9 = r1.o     // Catch:{ all -> 0x0338 }
            if (r9 == 0) goto L_0x0089
            java.util.Map<java.lang.String, java.lang.String> r9 = r1.o     // Catch:{ all -> 0x0338 }
            r8.putAll(r9)     // Catch:{ all -> 0x0338 }
        L_0x0089:
            java.lang.String r9 = "cmd"
            int r10 = r1.d     // Catch:{ all -> 0x0338 }
            java.lang.String r10 = java.lang.Integer.toString(r10)     // Catch:{ all -> 0x0338 }
            r8.put(r9, r10)     // Catch:{ all -> 0x0338 }
            java.lang.String r9 = "platformId"
            java.lang.String r10 = java.lang.Byte.toString(r7)     // Catch:{ all -> 0x0338 }
            r8.put(r9, r10)     // Catch:{ all -> 0x0338 }
            java.lang.String r9 = "sdkVer"
            com.tencent.bugly.crashreport.common.info.a r10 = r1.f     // Catch:{ all -> 0x0338 }
            java.lang.String r10 = r10.f     // Catch:{ all -> 0x0338 }
            r8.put(r9, r10)     // Catch:{ all -> 0x0338 }
            java.lang.String r9 = "strategylastUpdateTime"
            long r10 = r5.n     // Catch:{ all -> 0x0338 }
            java.lang.String r5 = java.lang.Long.toString(r10)     // Catch:{ all -> 0x0338 }
            r8.put(r9, r5)     // Catch:{ all -> 0x0338 }
            r5 = 2
            byte[] r4 = com.tencent.bugly.proguard.z.a((byte[]) r4, (int) r5)     // Catch:{ all -> 0x0338 }
            if (r4 != 0) goto L_0x00be
            java.lang.String r0 = "failed to zip request body"
            r1.a(r6, r3, r3, r0)     // Catch:{ all -> 0x0338 }
            return
        L_0x00be:
            if (r4 != 0) goto L_0x00c6
            java.lang.String r0 = "failed to encrypt request body"
            r1.a(r6, r3, r3, r0)     // Catch:{ all -> 0x0338 }
            return
        L_0x00c6:
            com.tencent.bugly.proguard.u r9 = r1.i     // Catch:{ all -> 0x0338 }
            int r10 = r1.j     // Catch:{ all -> 0x0338 }
            long r11 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0338 }
            r9.a((int) r10, (long) r11)     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.t r9 = r1.k     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.t r9 = r1.l     // Catch:{ all -> 0x0338 }
            java.lang.String r9 = r1.m     // Catch:{ all -> 0x0338 }
            r10 = -1
            r11 = 0
            r12 = 0
        L_0x00da:
            int r13 = r11 + 1
            int r14 = r1.a     // Catch:{ all -> 0x0338 }
            if (r11 >= r14) goto L_0x0323
            if (r13 <= r7) goto L_0x0106
            java.lang.String r11 = "[Upload] Failed to upload last time, wait and try(%d) again."
            java.lang.Object[] r12 = new java.lang.Object[r7]     // Catch:{ all -> 0x0338 }
            java.lang.Integer r14 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x0338 }
            r12[r3] = r14     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.d(r11, r12)     // Catch:{ all -> 0x0338 }
            int r11 = r1.b     // Catch:{ all -> 0x0338 }
            long r11 = (long) r11     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.z.b((long) r11)     // Catch:{ all -> 0x0338 }
            int r11 = r1.a     // Catch:{ all -> 0x0338 }
            if (r13 != r11) goto L_0x0106
            java.lang.String r9 = "[Upload] Use the back-up url at the last time: %s"
            java.lang.Object[] r11 = new java.lang.Object[r7]     // Catch:{ all -> 0x0338 }
            java.lang.String r12 = r1.n     // Catch:{ all -> 0x0338 }
            r11[r3] = r12     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.d(r9, r11)     // Catch:{ all -> 0x0338 }
            java.lang.String r9 = r1.n     // Catch:{ all -> 0x0338 }
        L_0x0106:
            java.lang.String r11 = "[Upload] Send %d bytes"
            java.lang.Object[] r12 = new java.lang.Object[r7]     // Catch:{ all -> 0x0338 }
            int r14 = r4.length     // Catch:{ all -> 0x0338 }
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)     // Catch:{ all -> 0x0338 }
            r12[r3] = r14     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.c(r11, r12)     // Catch:{ all -> 0x0338 }
            java.lang.String r9 = a((java.lang.String) r9)     // Catch:{ all -> 0x0338 }
            java.lang.String r11 = "[Upload] Upload to %s with cmd %d (pid=%d | tid=%d)."
            r12 = 4
            java.lang.Object[] r12 = new java.lang.Object[r12]     // Catch:{ all -> 0x0338 }
            r12[r3] = r9     // Catch:{ all -> 0x0338 }
            int r14 = r1.d     // Catch:{ all -> 0x0338 }
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)     // Catch:{ all -> 0x0338 }
            r12[r7] = r14     // Catch:{ all -> 0x0338 }
            int r14 = android.os.Process.myPid()     // Catch:{ all -> 0x0338 }
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)     // Catch:{ all -> 0x0338 }
            r12[r5] = r14     // Catch:{ all -> 0x0338 }
            int r14 = android.os.Process.myTid()     // Catch:{ all -> 0x0338 }
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)     // Catch:{ all -> 0x0338 }
            r15 = 3
            r12[r15] = r14     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.c(r11, r12)     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.s r11 = r1.h     // Catch:{ all -> 0x0338 }
            byte[] r11 = r11.a((java.lang.String) r9, (byte[]) r4, (com.tencent.bugly.proguard.v) r1, (java.util.Map<java.lang.String, java.lang.String>) r8)     // Catch:{ all -> 0x0338 }
            java.lang.String r12 = "[Upload] Failed to upload(%d): %s"
            if (r11 != 0) goto L_0x015c
            java.lang.String r11 = "Failed to upload for no response!"
            java.lang.Object[] r14 = new java.lang.Object[r5]     // Catch:{ all -> 0x0338 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x0338 }
            r14[r3] = r15     // Catch:{ all -> 0x0338 }
            r14[r7] = r11     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.e(r12, r14)     // Catch:{ all -> 0x0338 }
            r11 = r13
        L_0x0159:
            r12 = 1
            goto L_0x00da
        L_0x015c:
            com.tencent.bugly.proguard.s r14 = r1.h     // Catch:{ all -> 0x0338 }
            java.util.Map<java.lang.String, java.lang.String> r14 = r14.a     // Catch:{ all -> 0x0338 }
            java.lang.String r6 = "status"
            if (r14 == 0) goto L_0x01b2
            int r16 = r14.size()     // Catch:{ all -> 0x0338 }
            if (r16 != 0) goto L_0x016b
            goto L_0x01b2
        L_0x016b:
            boolean r16 = r14.containsKey(r6)     // Catch:{ all -> 0x0338 }
            java.lang.String r15 = "[Upload] Headers does not contain %s"
            if (r16 != 0) goto L_0x017d
            java.lang.Object[] r5 = new java.lang.Object[r7]     // Catch:{ all -> 0x0338 }
            r5[r3] = r6     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.d(r15, r5)     // Catch:{ all -> 0x0338 }
        L_0x017a:
            r17 = r2
            goto L_0x01bb
        L_0x017d:
            boolean r5 = r14.containsKey(r2)     // Catch:{ all -> 0x0338 }
            if (r5 != 0) goto L_0x018b
            java.lang.Object[] r5 = new java.lang.Object[r7]     // Catch:{ all -> 0x0338 }
            r5[r3] = r2     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.d(r15, r5)     // Catch:{ all -> 0x0338 }
            goto L_0x017a
        L_0x018b:
            java.lang.Object r5 = r14.get(r2)     // Catch:{ all -> 0x0338 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x0338 }
            java.lang.String r15 = "bugly"
            boolean r15 = r5.contains(r15)     // Catch:{ all -> 0x0338 }
            if (r15 != 0) goto L_0x01a5
            java.lang.String r15 = "[Upload] Bugly version is not valid: %s"
            r17 = r2
            java.lang.Object[] r2 = new java.lang.Object[r7]     // Catch:{ all -> 0x0338 }
            r2[r3] = r5     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.d(r15, r2)     // Catch:{ all -> 0x0338 }
            goto L_0x01bb
        L_0x01a5:
            r17 = r2
            java.lang.String r2 = "[Upload] Bugly version from headers is: %s"
            java.lang.Object[] r15 = new java.lang.Object[r7]     // Catch:{ all -> 0x0338 }
            r15[r3] = r5     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.c(r2, r15)     // Catch:{ all -> 0x0338 }
            r2 = 1
            goto L_0x01bc
        L_0x01b2:
            r17 = r2
            java.lang.String r2 = "[Upload] Headers is empty."
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.d(r2, r5)     // Catch:{ all -> 0x0338 }
        L_0x01bb:
            r2 = 0
        L_0x01bc:
            if (r2 != 0) goto L_0x0224
            java.lang.String r2 = "[Upload] Headers from server is not valid, just try again (pid=%d | tid=%d)."
            r5 = 2
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ all -> 0x0338 }
            int r5 = android.os.Process.myPid()     // Catch:{ all -> 0x0338 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0338 }
            r6[r3] = r5     // Catch:{ all -> 0x0338 }
            int r5 = android.os.Process.myTid()     // Catch:{ all -> 0x0338 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0338 }
            r6[r7] = r5     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.c(r2, r6)     // Catch:{ all -> 0x0338 }
            r2 = 2
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ all -> 0x0338 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x0338 }
            r5[r3] = r2     // Catch:{ all -> 0x0338 }
            r5[r7] = r0     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.e(r12, r5)     // Catch:{ all -> 0x0338 }
            if (r14 == 0) goto L_0x0219
            java.util.Set r2 = r14.entrySet()     // Catch:{ all -> 0x0338 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0338 }
        L_0x01f2:
            boolean r5 = r2.hasNext()     // Catch:{ all -> 0x0338 }
            if (r5 == 0) goto L_0x0219
            java.lang.Object r5 = r2.next()     // Catch:{ all -> 0x0338 }
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5     // Catch:{ all -> 0x0338 }
            java.lang.String r6 = "[key]: %s, [value]: %s"
            r11 = 2
            java.lang.Object[] r12 = new java.lang.Object[r11]     // Catch:{ all -> 0x0338 }
            java.lang.Object r11 = r5.getKey()     // Catch:{ all -> 0x0338 }
            r12[r3] = r11     // Catch:{ all -> 0x0338 }
            java.lang.Object r5 = r5.getValue()     // Catch:{ all -> 0x0338 }
            r12[r7] = r5     // Catch:{ all -> 0x0338 }
            java.lang.String r5 = java.lang.String.format(r6, r12)     // Catch:{ all -> 0x0338 }
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.c(r5, r6)     // Catch:{ all -> 0x0338 }
            goto L_0x01f2
        L_0x0219:
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.c(r0, r2)     // Catch:{ all -> 0x0338 }
            r11 = r13
            r2 = r17
            r5 = 2
            goto L_0x0320
        L_0x0224:
            java.lang.Object r2 = r14.get(r6)     // Catch:{ all -> 0x02fd }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x02fd }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ all -> 0x02fd }
            java.lang.String r5 = "[Upload] Status from server is %d (pid=%d | tid=%d)."
            r6 = 3
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ all -> 0x02fc }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x02fc }
            r6[r3] = r10     // Catch:{ all -> 0x02fc }
            int r10 = android.os.Process.myPid()     // Catch:{ all -> 0x02fc }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x02fc }
            r6[r7] = r10     // Catch:{ all -> 0x02fc }
            int r10 = android.os.Process.myTid()     // Catch:{ all -> 0x02fc }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x02fc }
            r15 = 2
            r6[r15] = r10     // Catch:{ all -> 0x02fc }
            com.tencent.bugly.proguard.x.c(r5, r6)     // Catch:{ all -> 0x02fc }
            if (r2 == 0) goto L_0x0266
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0338 }
            java.lang.String r4 = "status of server is "
            r0.<init>(r4)     // Catch:{ all -> 0x0338 }
            r0.append(r2)     // Catch:{ all -> 0x0338 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0338 }
            r2 = 0
            r1.a(r2, r3, r7, r0)     // Catch:{ all -> 0x0338 }
            return
        L_0x0266:
            java.lang.String r0 = "[Upload] Received %d bytes"
            java.lang.Object[] r2 = new java.lang.Object[r7]     // Catch:{ all -> 0x0338 }
            int r4 = r11.length     // Catch:{ all -> 0x0338 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0338 }
            r2[r3] = r4     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.c(r0, r2)     // Catch:{ all -> 0x0338 }
            int r0 = r11.length     // Catch:{ all -> 0x0338 }
            if (r0 != 0) goto L_0x02a7
            java.util.Set r0 = r14.entrySet()     // Catch:{ all -> 0x0338 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0338 }
        L_0x027f:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x0338 }
            if (r2 == 0) goto L_0x02a0
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x0338 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x0338 }
            java.lang.String r4 = "[Upload] HTTP headers from server: key = %s, value = %s"
            r5 = 2
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ all -> 0x0338 }
            java.lang.Object r5 = r2.getKey()     // Catch:{ all -> 0x0338 }
            r6[r3] = r5     // Catch:{ all -> 0x0338 }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x0338 }
            r6[r7] = r2     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.c(r4, r6)     // Catch:{ all -> 0x0338 }
            goto L_0x027f
        L_0x02a0:
            java.lang.String r0 = "response data from server is empty"
            r2 = 0
            r1.a(r2, r3, r7, r0)     // Catch:{ all -> 0x0338 }
            return
        L_0x02a7:
            if (r11 != 0) goto L_0x02b0
            java.lang.String r0 = "failed to decrypt response from server"
            r2 = 0
            r1.a(r2, r3, r7, r0)     // Catch:{ all -> 0x0338 }
            return
        L_0x02b0:
            r0 = 2
            byte[] r2 = com.tencent.bugly.proguard.z.b((byte[]) r11, (int) r0)     // Catch:{ all -> 0x0338 }
            if (r2 == 0) goto L_0x02b8
            r11 = r2
        L_0x02b8:
            com.tencent.bugly.proguard.an r0 = com.tencent.bugly.proguard.a.b(r11)     // Catch:{ all -> 0x0338 }
            if (r0 != 0) goto L_0x02c5
            java.lang.String r0 = "failed to decode response package"
            r2 = 0
            r1.a(r2, r3, r7, r0)     // Catch:{ all -> 0x0338 }
            return
        L_0x02c5:
            java.lang.String r2 = "[Upload] Response cmd is: %d, length of sBuffer is: %d"
            r4 = 2
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x0338 }
            int r4 = r0.b     // Catch:{ all -> 0x0338 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0338 }
            r5[r3] = r4     // Catch:{ all -> 0x0338 }
            byte[] r4 = r0.c     // Catch:{ all -> 0x0338 }
            if (r4 != 0) goto L_0x02d8
            r4 = 0
            goto L_0x02db
        L_0x02d8:
            byte[] r4 = r0.c     // Catch:{ all -> 0x0338 }
            int r4 = r4.length     // Catch:{ all -> 0x0338 }
        L_0x02db:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0338 }
            r5[r7] = r4     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.c(r2, r5)     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.crashreport.common.info.a r2 = r1.f     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.crashreport.common.strategy.a r4 = r1.g     // Catch:{ all -> 0x0338 }
            boolean r2 = a(r0, r2, r4)     // Catch:{ all -> 0x0338 }
            if (r2 != 0) goto L_0x02f5
            java.lang.String r2 = "failed to process response package"
            r4 = 2
            r1.a(r0, r3, r4, r2)     // Catch:{ all -> 0x0338 }
            return
        L_0x02f5:
            r4 = 2
            java.lang.String r2 = "successfully uploaded"
            r1.a(r0, r7, r4, r2)     // Catch:{ all -> 0x0338 }
            return
        L_0x02fc:
            r10 = r2
        L_0x02fd:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0338 }
            java.lang.String r5 = "[Upload] Failed to upload for format of status header is invalid: "
            r2.<init>(r5)     // Catch:{ all -> 0x0338 }
            java.lang.String r5 = java.lang.Integer.toString(r10)     // Catch:{ all -> 0x0338 }
            r2.append(r5)     // Catch:{ all -> 0x0338 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0338 }
            r5 = 2
            java.lang.Object[] r6 = new java.lang.Object[r5]     // Catch:{ all -> 0x0338 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x0338 }
            r6[r3] = r11     // Catch:{ all -> 0x0338 }
            r6[r7] = r2     // Catch:{ all -> 0x0338 }
            com.tencent.bugly.proguard.x.e(r12, r6)     // Catch:{ all -> 0x0338 }
            r11 = r13
            r2 = r17
        L_0x0320:
            r6 = 0
            goto L_0x0159
        L_0x0323:
            java.lang.String r0 = "failed after many attempts"
            r2 = 0
            r1.a(r2, r3, r12, r0)     // Catch:{ all -> 0x0338 }
            return
        L_0x032a:
            java.lang.String r0 = "illegal access error"
            r2 = 0
            r1.a(r2, r3, r3, r0)     // Catch:{ all -> 0x0338 }
            return
        L_0x0331:
            java.lang.String r0 = "request package is empty!"
            r2 = 0
            r1.a(r2, r3, r3, r0)     // Catch:{ all -> 0x0338 }
            return
        L_0x0338:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.a(r0)
            if (r2 != 0) goto L_0x0342
            r0.printStackTrace()
        L_0x0342:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.v.run():void");
    }

    public final void a(long j2) {
        this.p++;
        this.q += j2;
    }

    public final void b(long j2) {
        this.r += j2;
    }

    private static String a(String str) {
        if (z.a(str)) {
            return str;
        }
        try {
            return String.format("%s?aid=%s", new Object[]{str, UUID.randomUUID().toString()});
        } catch (Throwable th) {
            x.a(th);
            return str;
        }
    }
}
