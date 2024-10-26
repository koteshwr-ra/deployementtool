package com.tencent.smtt.sdk;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

class m {
    private static m a;
    private static Context b;

    private m() {
    }

    static m a(Context context) {
        if (a == null) {
            synchronized (m.class) {
                if (a == null) {
                    a = new m();
                }
            }
        }
        b = context.getApplicationContext();
        return a;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002e, code lost:
        r4 = r1;
        r1 = null;
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0032, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0033, code lost:
        r2 = null;
        r0 = r1;
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0040, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x002d A[ExcHandler: all (r1v5 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:1:0x0001] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x003b A[SYNTHETIC, Splitter:B:25:0x003b] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0047 A[SYNTHETIC, Splitter:B:32:0x0047] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Properties e() {
        /*
            r5 = this;
            r0 = 0
            java.io.File r1 = r5.a()     // Catch:{ Exception -> 0x0032, all -> 0x002d }
            java.util.Properties r2 = new java.util.Properties     // Catch:{ Exception -> 0x0032, all -> 0x002d }
            r2.<init>()     // Catch:{ Exception -> 0x0032, all -> 0x002d }
            if (r1 == 0) goto L_0x0022
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x001d, all -> 0x002d }
            r3.<init>(r1)     // Catch:{ Exception -> 0x001d, all -> 0x002d }
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x001d, all -> 0x002d }
            r1.<init>(r3)     // Catch:{ Exception -> 0x001d, all -> 0x002d }
            r2.load(r1)     // Catch:{ Exception -> 0x001b }
            r0 = r1
            goto L_0x0022
        L_0x001b:
            r0 = move-exception
            goto L_0x0036
        L_0x001d:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x0036
        L_0x0022:
            if (r0 == 0) goto L_0x002c
            r0.close()     // Catch:{ IOException -> 0x0028 }
            goto L_0x002c
        L_0x0028:
            r0 = move-exception
            r0.printStackTrace()
        L_0x002c:
            return r2
        L_0x002d:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x0045
        L_0x0032:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L_0x0036:
            r0.printStackTrace()     // Catch:{ all -> 0x0044 }
            if (r1 == 0) goto L_0x0043
            r1.close()     // Catch:{ IOException -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0043:
            return r2
        L_0x0044:
            r0 = move-exception
        L_0x0045:
            if (r1 == 0) goto L_0x004f
            r1.close()     // Catch:{ IOException -> 0x004b }
            goto L_0x004f
        L_0x004b:
            r1 = move-exception
            r1.printStackTrace()
        L_0x004f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.m.e():java.util.Properties");
    }

    /* access modifiers changed from: package-private */
    public File a() {
        o.a();
        File file = new File(o.t(b), "tbscoreinstall.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file;
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        a("dexopt_retry_num", i);
    }

    /* access modifiers changed from: package-private */
    public void a(int i, int i2) {
        a("copy_core_ver", i);
        a("copy_status", i2);
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        a("install_apk_path", str);
    }

    /* access modifiers changed from: package-private */
    public void a(String str, int i) {
        a(str, String.valueOf(i));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0049 A[SYNTHETIC, Splitter:B:24:0x0049] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004f A[SYNTHETIC, Splitter:B:27:0x004f] */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            r0 = 0
            java.util.Properties r1 = r3.e()     // Catch:{ Exception -> 0x0043 }
            if (r1 == 0) goto L_0x0036
            r1.setProperty(r4, r5)     // Catch:{ Exception -> 0x0043 }
            java.io.File r5 = r3.a()     // Catch:{ Exception -> 0x0043 }
            if (r5 == 0) goto L_0x0036
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0043 }
            r2.<init>(r5)     // Catch:{ Exception -> 0x0043 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            r5.<init>()     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            java.lang.String r0 = "update "
            r5.append(r0)     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            r5.append(r4)     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            java.lang.String r4 = " and status!"
            r5.append(r4)     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            java.lang.String r4 = r5.toString()     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            r1.store(r2, r4)     // Catch:{ Exception -> 0x0033, all -> 0x0030 }
            r0 = r2
            goto L_0x0036
        L_0x0030:
            r4 = move-exception
            r0 = r2
            goto L_0x004d
        L_0x0033:
            r4 = move-exception
            r0 = r2
            goto L_0x0044
        L_0x0036:
            if (r0 == 0) goto L_0x004c
            r0.close()     // Catch:{ IOException -> 0x003c }
            goto L_0x004c
        L_0x003c:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x004c
        L_0x0041:
            r4 = move-exception
            goto L_0x004d
        L_0x0043:
            r4 = move-exception
        L_0x0044:
            r4.printStackTrace()     // Catch:{ all -> 0x0041 }
            if (r0 == 0) goto L_0x004c
            r0.close()     // Catch:{ IOException -> 0x003c }
        L_0x004c:
            return
        L_0x004d:
            if (r0 == 0) goto L_0x0057
            r0.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x0057
        L_0x0053:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0057:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.m.a(java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return c("install_core_ver");
    }

    /* access modifiers changed from: package-private */
    public int b(String str) {
        Properties e = e();
        if (e == null || e.getProperty(str) == null) {
            return -1;
        }
        return Integer.parseInt(e.getProperty(str));
    }

    /* access modifiers changed from: package-private */
    public void b(int i) {
        a("unzip_retry_num", i);
    }

    /* access modifiers changed from: package-private */
    public void b(int i, int i2) {
        a("tpatch_ver", i);
        a("tpatch_status", i2);
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return b("install_status");
    }

    /* access modifiers changed from: package-private */
    public int c(String str) {
        Properties e = e();
        if (e == null || e.getProperty(str) == null) {
            return 0;
        }
        return Integer.parseInt(e.getProperty(str));
    }

    /* access modifiers changed from: package-private */
    public void c(int i) {
        a("incrupdate_status", i);
    }

    /* access modifiers changed from: package-private */
    public void c(int i, int i2) {
        a("install_core_ver", i);
        a("install_status", i2);
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return b("incrupdate_status");
    }

    /* access modifiers changed from: package-private */
    public String d(String str) {
        Properties e = e();
        if (e == null || e.getProperty(str) == null) {
            return null;
        }
        return e.getProperty(str);
    }

    /* access modifiers changed from: package-private */
    public void d(int i) {
        a("unlzma_status", i);
    }
}
