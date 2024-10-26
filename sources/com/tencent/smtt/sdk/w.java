package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import java.nio.channels.FileLock;

class w {
    private static w a;
    private static FileLock e;
    private x b;
    private boolean c;
    private boolean d;

    private w() {
    }

    public static w a() {
        if (a == null) {
            synchronized (w.class) {
                if (a == null) {
                    a = new w();
                }
            }
        }
        return a;
    }

    public x a(boolean z) {
        return z ? this.b : c();
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0096 A[Catch:{ NoSuchMethodException -> 0x005c, all -> 0x0040 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x019e A[Catch:{ NoSuchMethodException -> 0x005c, all -> 0x0040 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.content.Context r11) {
        /*
            r10 = this;
            monitor-enter(r10)
            java.lang.String r0 = "X5CoreEngine"
            java.lang.String r1 = "init #1"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch:{ all -> 0x01c1 }
            r0 = 1
            com.tencent.smtt.sdk.f r1 = com.tencent.smtt.sdk.f.a((boolean) r0)     // Catch:{ all -> 0x01c1 }
            r2 = 0
            r1.a(r11, r2, r2)     // Catch:{ all -> 0x01c1 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c1 }
            r3.<init>()     // Catch:{ all -> 0x01c1 }
            com.tencent.smtt.sdk.u r4 = r1.a()     // Catch:{ all -> 0x01c1 }
            boolean r5 = r1.b()     // Catch:{ all -> 0x01c1 }
            r6 = 0
            if (r5 == 0) goto L_0x0072
            if (r4 == 0) goto L_0x0072
            boolean r5 = r10.d     // Catch:{ all -> 0x01c1 }
            if (r5 != 0) goto L_0x0079
            com.tencent.smtt.sdk.x r5 = new com.tencent.smtt.sdk.x     // Catch:{ all -> 0x01c1 }
            com.tencent.smtt.export.external.DexLoader r7 = r4.b()     // Catch:{ all -> 0x01c1 }
            r5.<init>(r7)     // Catch:{ all -> 0x01c1 }
            r10.b = r5     // Catch:{ all -> 0x01c1 }
            boolean r5 = r5.a()     // Catch:{ NoSuchMethodException -> 0x005c, all -> 0x0040 }
            r10.c = r5     // Catch:{ NoSuchMethodException -> 0x005c, all -> 0x0040 }
            if (r5 != 0) goto L_0x005e
            java.lang.String r5 = "can not use X5 by x5corewizard return false"
            r3.append(r5)     // Catch:{ NoSuchMethodException -> 0x005c, all -> 0x0040 }
            goto L_0x005e
        L_0x0040:
            r5 = move-exception
            r10.c = r2     // Catch:{ all -> 0x01c1 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c1 }
            r7.<init>()     // Catch:{ all -> 0x01c1 }
            java.lang.String r8 = "can not use x5 by throwable "
            r7.append(r8)     // Catch:{ all -> 0x01c1 }
            java.lang.String r8 = android.util.Log.getStackTraceString(r5)     // Catch:{ all -> 0x01c1 }
            r7.append(r8)     // Catch:{ all -> 0x01c1 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x01c1 }
            r3.append(r7)     // Catch:{ all -> 0x01c1 }
            goto L_0x005f
        L_0x005c:
            r10.c = r0     // Catch:{ all -> 0x01c1 }
        L_0x005e:
            r5 = r6
        L_0x005f:
            boolean r7 = r10.c     // Catch:{ all -> 0x01c1 }
            if (r7 == 0) goto L_0x007a
            com.tencent.smtt.sdk.CookieManager r7 = com.tencent.smtt.sdk.CookieManager.getInstance()     // Catch:{ all -> 0x01c1 }
            r7.a(r11, r0, r0)     // Catch:{ all -> 0x01c1 }
            com.tencent.smtt.sdk.CookieManager r7 = com.tencent.smtt.sdk.CookieManager.getInstance()     // Catch:{ all -> 0x01c1 }
            r7.a()     // Catch:{ all -> 0x01c1 }
            goto L_0x007a
        L_0x0072:
            r10.c = r2     // Catch:{ all -> 0x01c1 }
            java.lang.String r5 = "can not use X5 by !tbs available"
            r3.append(r5)     // Catch:{ all -> 0x01c1 }
        L_0x0079:
            r5 = r6
        L_0x007a:
            java.lang.String r7 = "X5CoreEngine"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c1 }
            r8.<init>()     // Catch:{ all -> 0x01c1 }
            java.lang.String r9 = "init  mCanUseX5 is "
            r8.append(r9)     // Catch:{ all -> 0x01c1 }
            boolean r9 = r10.c     // Catch:{ all -> 0x01c1 }
            r8.append(r9)     // Catch:{ all -> 0x01c1 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x01c1 }
            com.tencent.smtt.utils.TbsLog.i(r7, r8)     // Catch:{ all -> 0x01c1 }
            boolean r7 = r10.c     // Catch:{ all -> 0x01c1 }
            if (r7 != 0) goto L_0x019e
            java.lang.String r7 = "X5CoreEngine"
            java.lang.String r8 = "mCanUseX5 is false --> report"
            com.tencent.smtt.utils.TbsLog.e(r7, r8)     // Catch:{ all -> 0x01c1 }
            boolean r7 = r1.b()     // Catch:{ all -> 0x01c1 }
            if (r7 == 0) goto L_0x015b
            if (r4 == 0) goto L_0x015b
            if (r5 != 0) goto L_0x015b
            com.tencent.smtt.export.external.DexLoader r1 = r4.b()     // Catch:{ all -> 0x0105 }
            if (r1 == 0) goto L_0x00b9
            java.lang.String r4 = "com.tencent.tbs.tbsshell.TBSShell"
            java.lang.String r5 = "getLoadFailureDetails"
            java.lang.Class[] r6 = new java.lang.Class[r2]     // Catch:{ all -> 0x0105 }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0105 }
            java.lang.Object r6 = r1.invokeStaticMethod(r4, r5, r6, r2)     // Catch:{ all -> 0x0105 }
        L_0x00b9:
            boolean r1 = r6 instanceof java.lang.Throwable     // Catch:{ all -> 0x0105 }
            if (r1 == 0) goto L_0x00ec
            r1 = r6
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ all -> 0x0105 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0105 }
            r2.<init>()     // Catch:{ all -> 0x0105 }
            java.lang.String r4 = "#"
            r2.append(r4)     // Catch:{ all -> 0x0105 }
            java.lang.String r4 = r1.getMessage()     // Catch:{ all -> 0x0105 }
            r2.append(r4)     // Catch:{ all -> 0x0105 }
            java.lang.String r4 = "; cause: "
            r2.append(r4)     // Catch:{ all -> 0x0105 }
            java.lang.Throwable r4 = r1.getCause()     // Catch:{ all -> 0x0105 }
            r2.append(r4)     // Catch:{ all -> 0x0105 }
            java.lang.String r4 = "; th: "
            r2.append(r4)     // Catch:{ all -> 0x0105 }
            r2.append(r1)     // Catch:{ all -> 0x0105 }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x0105 }
            r3.append(r1)     // Catch:{ all -> 0x0105 }
        L_0x00ec:
            boolean r1 = r6 instanceof java.lang.String     // Catch:{ all -> 0x0105 }
            if (r1 == 0) goto L_0x0109
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0105 }
            r1.<init>()     // Catch:{ all -> 0x0105 }
            java.lang.String r2 = "failure detail:"
            r1.append(r2)     // Catch:{ all -> 0x0105 }
            r1.append(r6)     // Catch:{ all -> 0x0105 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0105 }
            r3.append(r1)     // Catch:{ all -> 0x0105 }
            goto L_0x0109
        L_0x0105:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x01c1 }
        L_0x0109:
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x01c1 }
            java.lang.String r2 = "isPreloadX5Disabled:-10000"
            boolean r1 = r1.contains(r2)     // Catch:{ all -> 0x01c1 }
            if (r1 == 0) goto L_0x013a
            com.tencent.smtt.sdk.TbsCoreLoadStat r1 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch:{ all -> 0x01c1 }
            r2 = 408(0x198, float:5.72E-43)
            java.lang.Throwable r4 = new java.lang.Throwable     // Catch:{ all -> 0x01c1 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c1 }
            r5.<init>()     // Catch:{ all -> 0x01c1 }
            java.lang.String r6 = "X5CoreEngine::init, mCanUseX5=false, available true, details: "
            r5.append(r6)     // Catch:{ all -> 0x01c1 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x01c1 }
            r5.append(r3)     // Catch:{ all -> 0x01c1 }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x01c1 }
            r4.<init>(r3)     // Catch:{ all -> 0x01c1 }
        L_0x0135:
            r1.a(r11, r2, r4)     // Catch:{ all -> 0x01c1 }
            goto L_0x01bd
        L_0x013a:
            com.tencent.smtt.sdk.TbsCoreLoadStat r1 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch:{ all -> 0x01c1 }
            r2 = 407(0x197, float:5.7E-43)
            java.lang.Throwable r4 = new java.lang.Throwable     // Catch:{ all -> 0x01c1 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c1 }
            r5.<init>()     // Catch:{ all -> 0x01c1 }
            java.lang.String r6 = "X5CoreEngine::init, mCanUseX5=false, available true, details: "
            r5.append(r6)     // Catch:{ all -> 0x01c1 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x01c1 }
            r5.append(r3)     // Catch:{ all -> 0x01c1 }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x01c1 }
            r4.<init>(r3)     // Catch:{ all -> 0x01c1 }
            goto L_0x0135
        L_0x015b:
            boolean r1 = r1.b()     // Catch:{ all -> 0x01c1 }
            if (r1 == 0) goto L_0x0181
            com.tencent.smtt.sdk.TbsCoreLoadStat r1 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch:{ all -> 0x01c1 }
            r2 = 409(0x199, float:5.73E-43)
            java.lang.Throwable r3 = new java.lang.Throwable     // Catch:{ all -> 0x01c1 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c1 }
            r4.<init>()     // Catch:{ all -> 0x01c1 }
            java.lang.String r6 = "mCanUseX5=false, available true, reason: "
            r4.append(r6)     // Catch:{ all -> 0x01c1 }
            r4.append(r5)     // Catch:{ all -> 0x01c1 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x01c1 }
            r3.<init>(r4)     // Catch:{ all -> 0x01c1 }
        L_0x017d:
            r1.a(r11, r2, r3)     // Catch:{ all -> 0x01c1 }
            goto L_0x01bd
        L_0x0181:
            com.tencent.smtt.sdk.TbsCoreLoadStat r1 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch:{ all -> 0x01c1 }
            r2 = 410(0x19a, float:5.75E-43)
            java.lang.Throwable r3 = new java.lang.Throwable     // Catch:{ all -> 0x01c1 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c1 }
            r4.<init>()     // Catch:{ all -> 0x01c1 }
            java.lang.String r6 = "mCanUseX5=false, available false, reason: "
            r4.append(r6)     // Catch:{ all -> 0x01c1 }
            r4.append(r5)     // Catch:{ all -> 0x01c1 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x01c1 }
            r3.<init>(r4)     // Catch:{ all -> 0x01c1 }
            goto L_0x017d
        L_0x019e:
            java.lang.String r1 = "X5CoreEngine"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c1 }
            r2.<init>()     // Catch:{ all -> 0x01c1 }
            java.lang.String r3 = "init  sTbsCoreLoadFileLock is "
            r2.append(r3)     // Catch:{ all -> 0x01c1 }
            java.nio.channels.FileLock r3 = e     // Catch:{ all -> 0x01c1 }
            r2.append(r3)     // Catch:{ all -> 0x01c1 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x01c1 }
            com.tencent.smtt.utils.TbsLog.i(r1, r2)     // Catch:{ all -> 0x01c1 }
            java.nio.channels.FileLock r1 = e     // Catch:{ all -> 0x01c1 }
            if (r1 != 0) goto L_0x01bd
            r10.b(r11)     // Catch:{ all -> 0x01c1 }
        L_0x01bd:
            r10.d = r0     // Catch:{ all -> 0x01c1 }
            monitor-exit(r10)
            return
        L_0x01c1:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.w.a(android.content.Context):void");
    }

    public FileLock b(Context context) {
        String str;
        String str2;
        TbsLog.i("X5CoreEngine", "tryTbsCoreLoadFileLock ##");
        FileLock fileLock = e;
        if (fileLock != null) {
            return fileLock;
        }
        synchronized (w.class) {
            if (e == null) {
                FileLock e2 = FileUtil.e(context);
                e = e2;
                if (e2 == null) {
                    str = "X5CoreEngine";
                    str2 = "init -- sTbsCoreLoadFileLock failed!";
                } else {
                    str = "X5CoreEngine";
                    str2 = "init -- sTbsCoreLoadFileLock succeeded: " + e;
                }
                TbsLog.i(str, str2);
            }
        }
        return e;
    }

    public boolean b() {
        if (QbSdk.a) {
            return false;
        }
        return this.c;
    }

    public x c() {
        if (QbSdk.a) {
            return null;
        }
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        return this.d;
    }
}
