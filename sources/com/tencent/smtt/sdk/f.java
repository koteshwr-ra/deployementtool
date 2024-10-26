package com.tencent.smtt.sdk;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

class f {
    static int a = 0;
    static boolean b = false;
    private static f e = null;
    private static int h = 0;
    private static int i = 3;
    private static String k;
    private u c = null;
    private u d = null;
    private boolean f = false;
    private boolean g = false;
    private File j = null;

    private f() {
    }

    public static f a(boolean z) {
        if (e == null && z) {
            synchronized (f.class) {
                if (e == null) {
                    e = new f();
                }
            }
        }
        return e;
    }

    static void a(int i2) {
        h = i2;
    }

    private void b(int i2) {
        String valueOf = String.valueOf(i2);
        Properties properties = new Properties();
        properties.setProperty(k, valueOf);
        try {
            properties.store(new FileOutputStream(new File(this.j, "count.prop")), (String) null);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public static int d() {
        return h;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x004a A[SYNTHETIC, Splitter:B:20:0x004a] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0057 A[SYNTHETIC, Splitter:B:28:0x0057] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int i() {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0041, all -> 0x003f }
            java.io.File r3 = r6.j     // Catch:{ Exception -> 0x0041, all -> 0x003f }
            java.lang.String r4 = "count.prop"
            r2.<init>(r3, r4)     // Catch:{ Exception -> 0x0041, all -> 0x003f }
            boolean r3 = r2.exists()     // Catch:{ Exception -> 0x0041, all -> 0x003f }
            if (r3 != 0) goto L_0x0012
            return r0
        L_0x0012:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0041, all -> 0x003f }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0041, all -> 0x003f }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0041, all -> 0x003f }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0041, all -> 0x003f }
            java.util.Properties r1 = new java.util.Properties     // Catch:{ Exception -> 0x003d }
            r1.<init>()     // Catch:{ Exception -> 0x003d }
            r1.load(r2)     // Catch:{ Exception -> 0x003d }
            java.lang.String r3 = k     // Catch:{ Exception -> 0x003d }
            java.lang.String r4 = "1"
            java.lang.String r1 = r1.getProperty(r3, r4)     // Catch:{ Exception -> 0x003d }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x003d }
            int r0 = r1.intValue()     // Catch:{ Exception -> 0x003d }
            r2.close()     // Catch:{ IOException -> 0x0038 }
            goto L_0x003c
        L_0x0038:
            r1 = move-exception
            r1.printStackTrace()
        L_0x003c:
            return r0
        L_0x003d:
            r1 = move-exception
            goto L_0x0045
        L_0x003f:
            r0 = move-exception
            goto L_0x0055
        L_0x0041:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L_0x0045:
            r1.printStackTrace()     // Catch:{ all -> 0x0053 }
            if (r2 == 0) goto L_0x0052
            r2.close()     // Catch:{ IOException -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0052:
            return r0
        L_0x0053:
            r0 = move-exception
            r1 = r2
        L_0x0055:
            if (r1 == 0) goto L_0x005f
            r1.close()     // Catch:{ IOException -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r1 = move-exception
            r1.printStackTrace()
        L_0x005f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.f.i():int");
    }

    public u a() {
        if (this.f) {
            return this.c;
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x00fd A[Catch:{ all -> 0x016c }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0103 A[Catch:{ all -> 0x016c }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0106 A[Catch:{ all -> 0x016c }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x010f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.content.Context r11, boolean r12, boolean r13) {
        /*
            r10 = this;
            monitor-enter(r10)
            r0 = 999(0x3e7, float:1.4E-42)
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x01fc }
            r3 = 0
            com.tencent.smtt.utils.TbsLog.addLog(r0, r3, r2)     // Catch:{ all -> 0x01fc }
            com.tencent.smtt.utils.TbsLog.initIfNeed(r11)     // Catch:{ all -> 0x01fc }
            java.lang.String r0 = "SDKEngine"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x01fc }
            r2.<init>()     // Catch:{ all -> 0x01fc }
            java.lang.String r4 = "init -- context: "
            r2.append(r4)     // Catch:{ all -> 0x01fc }
            r2.append(r11)     // Catch:{ all -> 0x01fc }
            java.lang.String r4 = ", isPreIniting: "
            r2.append(r4)     // Catch:{ all -> 0x01fc }
            r2.append(r13)     // Catch:{ all -> 0x01fc }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x01fc }
            com.tencent.smtt.utils.TbsLog.i(r0, r2)     // Catch:{ all -> 0x01fc }
            int r0 = a     // Catch:{ all -> 0x01fc }
            r2 = 1
            int r0 = r0 + r2
            a = r0     // Catch:{ all -> 0x01fc }
            com.tencent.smtt.sdk.TbsCoreLoadStat r0 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch:{ all -> 0x01fc }
            r0.a()     // Catch:{ all -> 0x01fc }
            com.tencent.smtt.sdk.o r0 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x01fc }
            int r4 = a     // Catch:{ all -> 0x01fc }
            if (r4 != r2) goto L_0x0042
            r4 = 1
            goto L_0x0043
        L_0x0042:
            r4 = 0
        L_0x0043:
            r0.b((android.content.Context) r11, (boolean) r4)     // Catch:{ all -> 0x01fc }
            com.tencent.smtt.sdk.o r0 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x01fc }
            r0.l(r11)     // Catch:{ all -> 0x01fc }
            com.tencent.smtt.sdk.TbsShareManager.forceToLoadX5ForThirdApp(r11, r2)     // Catch:{ all -> 0x01fc }
            boolean r12 = com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r11, (boolean) r12, (boolean) r13)     // Catch:{ all -> 0x01fc }
            int r13 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x01fc }
            r0 = 7
            if (r13 < r0) goto L_0x005b
            r13 = 1
            goto L_0x005c
        L_0x005b:
            r13 = 0
        L_0x005c:
            if (r12 == 0) goto L_0x0062
            if (r13 == 0) goto L_0x0062
            r0 = 1
            goto L_0x0063
        L_0x0062:
            r0 = 0
        L_0x0063:
            if (r0 == 0) goto L_0x0098
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x01fc }
            com.tencent.smtt.sdk.o r0 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x01fc }
            int r6 = d()     // Catch:{ all -> 0x01fc }
            boolean r0 = r0.g((android.content.Context) r11, (int) r6)     // Catch:{ all -> 0x01fc }
            java.lang.String r6 = "SDKEngine"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x01fc }
            r7.<init>()     // Catch:{ all -> 0x01fc }
            java.lang.String r8 = "isTbsCoreLegal: "
            r7.append(r8)     // Catch:{ all -> 0x01fc }
            r7.append(r0)     // Catch:{ all -> 0x01fc }
            java.lang.String r8 = "; cost: "
            r7.append(r8)     // Catch:{ all -> 0x01fc }
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x01fc }
            long r8 = r8 - r4
            r7.append(r8)     // Catch:{ all -> 0x01fc }
            java.lang.String r4 = r7.toString()     // Catch:{ all -> 0x01fc }
            com.tencent.smtt.utils.TbsLog.i(r6, r4)     // Catch:{ all -> 0x01fc }
        L_0x0098:
            if (r0 == 0) goto L_0x01a3
            boolean r12 = r10.f     // Catch:{ all -> 0x01fc }
            if (r12 == 0) goto L_0x00a0
            monitor-exit(r10)
            return
        L_0x00a0:
            boolean r12 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r11)     // Catch:{ all -> 0x016c }
            if (r12 == 0) goto L_0x00dc
            r12 = 995(0x3e3, float:1.394E-42)
            java.lang.Object[] r13 = new java.lang.Object[r1]     // Catch:{ all -> 0x016c }
            com.tencent.smtt.utils.TbsLog.addLog(r12, r3, r13)     // Catch:{ all -> 0x016c }
            boolean r12 = com.tencent.smtt.sdk.TbsShareManager.j(r11)     // Catch:{ all -> 0x016c }
            if (r12 == 0) goto L_0x00d3
            java.io.File r12 = new java.io.File     // Catch:{ all -> 0x016c }
            java.lang.String r13 = com.tencent.smtt.sdk.TbsShareManager.c(r11)     // Catch:{ all -> 0x016c }
            r12.<init>(r13)     // Catch:{ all -> 0x016c }
            com.tencent.smtt.sdk.o r13 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x016c }
            java.io.File r13 = r13.r(r11)     // Catch:{ all -> 0x016c }
            android.content.Context r0 = com.tencent.smtt.sdk.TbsShareManager.e(r11)     // Catch:{ all -> 0x016c }
            if (r13 != 0) goto L_0x0110
            r10.f = r1     // Catch:{ all -> 0x016c }
            java.lang.String r12 = "SDKEngine::useSystemWebView by error_tbs_core_dexopt_dir null!"
            com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r11, (java.lang.String) r12)     // Catch:{ all -> 0x016c }
            monitor-exit(r10)
            return
        L_0x00d3:
            r10.f = r1     // Catch:{ all -> 0x016c }
            java.lang.String r12 = "SDKEngine::useSystemWebView by error_host_unavailable"
            com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r11, (java.lang.String) r12)     // Catch:{ all -> 0x016c }
            monitor-exit(r10)
            return
        L_0x00dc:
            r12 = 996(0x3e4, float:1.396E-42)
            java.lang.Object[] r13 = new java.lang.Object[r1]     // Catch:{ all -> 0x016c }
            com.tencent.smtt.utils.TbsLog.addLog(r12, r3, r13)     // Catch:{ all -> 0x016c }
            com.tencent.smtt.sdk.o r12 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x016c }
            java.io.File r12 = r12.r(r11)     // Catch:{ all -> 0x016c }
            int r13 = h     // Catch:{ all -> 0x016c }
            r0 = 25436(0x635c, float:3.5643E-41)
            if (r13 == r0) goto L_0x00fa
            int r13 = h     // Catch:{ all -> 0x016c }
            r0 = 25437(0x635d, float:3.5645E-41)
            if (r13 != r0) goto L_0x00f8
            goto L_0x00fa
        L_0x00f8:
            r13 = 0
            goto L_0x00fb
        L_0x00fa:
            r13 = 1
        L_0x00fb:
            if (r13 == 0) goto L_0x0103
            android.content.Context r13 = r11.getApplicationContext()     // Catch:{ all -> 0x016c }
            r0 = r13
            goto L_0x0104
        L_0x0103:
            r0 = r11
        L_0x0104:
            if (r12 != 0) goto L_0x010f
            r10.f = r1     // Catch:{ all -> 0x016c }
            java.lang.String r12 = "SDKEngine::useSystemWebView by tbs_core_share_dir null!"
            com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r11, (java.lang.String) r12)     // Catch:{ all -> 0x016c }
            monitor-exit(r10)
            return
        L_0x010f:
            r13 = r12
        L_0x0110:
            r5 = r0
            java.lang.String r0 = r12.getAbsolutePath()     // Catch:{ all -> 0x016c }
            java.lang.String[] r8 = com.tencent.smtt.sdk.QbSdk.getDexLoaderFileList(r11, r5, r0)     // Catch:{ all -> 0x016c }
            r0 = 0
        L_0x011a:
            int r3 = r8.length     // Catch:{ all -> 0x016c }
            if (r0 >= r3) goto L_0x0120
            int r0 = r0 + 1
            goto L_0x011a
        L_0x0120:
            java.lang.String r0 = com.tencent.smtt.sdk.TbsShareManager.getHostCorePathAppDefined()     // Catch:{ all -> 0x016c }
            if (r0 == 0) goto L_0x012b
            java.lang.String r13 = com.tencent.smtt.sdk.TbsShareManager.getHostCorePathAppDefined()     // Catch:{ all -> 0x016c }
            goto L_0x012f
        L_0x012b:
            java.lang.String r13 = r13.getAbsolutePath()     // Catch:{ all -> 0x016c }
        L_0x012f:
            r7 = r13
            java.lang.String r13 = "SDKEngine"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x016c }
            r0.<init>()     // Catch:{ all -> 0x016c }
            java.lang.String r3 = "SDKEngine init optDir is "
            r0.append(r3)     // Catch:{ all -> 0x016c }
            r0.append(r7)     // Catch:{ all -> 0x016c }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x016c }
            com.tencent.smtt.utils.TbsLog.i(r13, r0)     // Catch:{ all -> 0x016c }
            com.tencent.smtt.sdk.u r13 = r10.d     // Catch:{ all -> 0x016c }
            if (r13 == 0) goto L_0x0159
            com.tencent.smtt.sdk.u r3 = r10.d     // Catch:{ all -> 0x016c }
            r10.c = r3     // Catch:{ all -> 0x016c }
            java.lang.String r6 = r12.getAbsolutePath()     // Catch:{ all -> 0x016c }
            java.lang.String r9 = com.tencent.smtt.sdk.QbSdk.d     // Catch:{ all -> 0x016c }
            r4 = r11
            r3.a(r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x016c }
            goto L_0x0168
        L_0x0159:
            com.tencent.smtt.sdk.u r13 = new com.tencent.smtt.sdk.u     // Catch:{ all -> 0x016c }
            java.lang.String r6 = r12.getAbsolutePath()     // Catch:{ all -> 0x016c }
            java.lang.String r9 = com.tencent.smtt.sdk.QbSdk.d     // Catch:{ all -> 0x016c }
            r3 = r13
            r4 = r11
            r3.<init>(r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x016c }
            r10.c = r13     // Catch:{ all -> 0x016c }
        L_0x0168:
            r10.f = r2     // Catch:{ all -> 0x016c }
            goto L_0x01eb
        L_0x016c:
            r12 = move-exception
            java.lang.String r13 = "SDKEngine"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01fc }
            r0.<init>()     // Catch:{ all -> 0x01fc }
            java.lang.String r3 = "useSystemWebView by exception: "
            r0.append(r3)     // Catch:{ all -> 0x01fc }
            r0.append(r12)     // Catch:{ all -> 0x01fc }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x01fc }
            com.tencent.smtt.utils.TbsLog.e(r13, r0)     // Catch:{ all -> 0x01fc }
            com.tencent.smtt.sdk.TbsCoreLoadStat r13 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch:{ all -> 0x01fc }
            r0 = 327(0x147, float:4.58E-43)
            r13.a(r11, r0, r12)     // Catch:{ all -> 0x01fc }
            r10.f = r1     // Catch:{ all -> 0x01fc }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x01fc }
            r13.<init>()     // Catch:{ all -> 0x01fc }
            java.lang.String r0 = "SDKEngine::useSystemWebView by exception: "
            r13.append(r0)     // Catch:{ all -> 0x01fc }
            r13.append(r12)     // Catch:{ all -> 0x01fc }
            java.lang.String r12 = r13.toString()     // Catch:{ all -> 0x01fc }
            com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r11, (java.lang.String) r12)     // Catch:{ all -> 0x01fc }
            goto L_0x01eb
        L_0x01a3:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01fc }
            r0.<init>()     // Catch:{ all -> 0x01fc }
            java.lang.String r3 = "can_load_x5="
            r0.append(r3)     // Catch:{ all -> 0x01fc }
            r0.append(r12)     // Catch:{ all -> 0x01fc }
            java.lang.String r12 = "; is_compatible="
            r0.append(r12)     // Catch:{ all -> 0x01fc }
            r0.append(r13)     // Catch:{ all -> 0x01fc }
            java.lang.String r12 = r0.toString()     // Catch:{ all -> 0x01fc }
            java.lang.String r13 = "SDKEngine"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01fc }
            r0.<init>()     // Catch:{ all -> 0x01fc }
            java.lang.String r3 = "SDKEngine.init canLoadTbs=false; failure: "
            r0.append(r3)     // Catch:{ all -> 0x01fc }
            r0.append(r12)     // Catch:{ all -> 0x01fc }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x01fc }
            com.tencent.smtt.utils.TbsLog.e(r13, r0)     // Catch:{ all -> 0x01fc }
            boolean r13 = com.tencent.smtt.sdk.QbSdk.a     // Catch:{ all -> 0x01fc }
            if (r13 == 0) goto L_0x01db
            boolean r13 = r10.f     // Catch:{ all -> 0x01fc }
            if (r13 == 0) goto L_0x01db
            goto L_0x01eb
        L_0x01db:
            r10.f = r1     // Catch:{ all -> 0x01fc }
            com.tencent.smtt.sdk.TbsCoreLoadStat r13 = com.tencent.smtt.sdk.TbsCoreLoadStat.getInstance()     // Catch:{ all -> 0x01fc }
            r0 = 405(0x195, float:5.68E-43)
            java.lang.Throwable r1 = new java.lang.Throwable     // Catch:{ all -> 0x01fc }
            r1.<init>(r12)     // Catch:{ all -> 0x01fc }
            r13.a(r11, r0, r1)     // Catch:{ all -> 0x01fc }
        L_0x01eb:
            com.tencent.smtt.sdk.d r12 = com.tencent.smtt.sdk.d.a()     // Catch:{ all -> 0x01fc }
            r12.a(r11)     // Catch:{ all -> 0x01fc }
            java.io.File r11 = com.tencent.smtt.sdk.o.t(r11)     // Catch:{ all -> 0x01fc }
            r10.j = r11     // Catch:{ all -> 0x01fc }
            r10.g = r2     // Catch:{ all -> 0x01fc }
            monitor-exit(r10)
            return
        L_0x01fc:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.f.a(android.content.Context, boolean, boolean):void");
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        k = str;
    }

    public boolean b() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public boolean b(boolean z) {
        b = z;
        return z;
    }

    /* access modifiers changed from: package-private */
    public u c() {
        return this.c;
    }

    public String e() {
        return (this.c == null || QbSdk.a) ? "system webview get nothing..." : this.c.a();
    }

    /* access modifiers changed from: package-private */
    public boolean f() {
        if (b) {
            if (k == null) {
                return false;
            }
            int i2 = i();
            if (i2 == 0) {
                b(1);
            } else {
                int i3 = i2 + 1;
                if (i3 > i) {
                    return false;
                }
                b(i3);
            }
        }
        return b;
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return this.g;
    }

    public boolean h() {
        return QbSdk.useSoftWare();
    }
}
