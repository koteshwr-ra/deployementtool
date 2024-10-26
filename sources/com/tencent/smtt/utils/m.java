package com.tencent.smtt.utils;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

public class m {
    private static m c;
    private Context a = null;
    private File b = null;
    private String d = "https://log.tbs.qq.com/ajax?c=pu&v=2&k=";
    private String e = "https://log.tbs.qq.com/ajax?c=pu&tk=";
    private String f = "https://log.tbs.qq.com/ajax?c=dl&k=";
    private String g = "https://cfg.imtt.qq.com/tbs?v=2&mk=";
    private String h = "https://log.tbs.qq.com/ajax?c=ul&v=2&k=";
    private String i = "https://mqqad.html5.qq.com/adjs";
    private String j = "https://log.tbs.qq.com/ajax?c=ucfu&k=";
    private String k = "https://tbsrecovery.imtt.qq.com/getconfig";

    private m(Context context) {
        TbsLog.w("TbsCommonConfig", "TbsCommonConfig constructing...");
        this.a = context.getApplicationContext();
        h();
    }

    public static synchronized m a() {
        m mVar;
        synchronized (m.class) {
            mVar = c;
        }
        return mVar;
    }

    public static synchronized m a(Context context) {
        m mVar;
        synchronized (m.class) {
            if (c == null) {
                c = new m(context);
            }
            mVar = c;
        }
        return mVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x00eb A[SYNTHETIC, Splitter:B:46:0x00eb] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void h() {
        /*
            r6 = this;
            monitor-enter(r6)
            r0 = 0
            java.io.File r1 = r6.i()     // Catch:{ all -> 0x00c1 }
            if (r1 != 0) goto L_0x0011
            java.lang.String r1 = "TbsCommonConfig"
            java.lang.String r2 = "Config file is null, default values will be applied"
            com.tencent.smtt.utils.TbsLog.e(r1, r2)     // Catch:{ all -> 0x00c1 }
            monitor-exit(r6)
            return
        L_0x0011:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x00c1 }
            r2.<init>(r1)     // Catch:{ all -> 0x00c1 }
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ all -> 0x00c1 }
            r1.<init>(r2)     // Catch:{ all -> 0x00c1 }
            java.util.Properties r0 = new java.util.Properties     // Catch:{ all -> 0x00bc }
            r0.<init>()     // Catch:{ all -> 0x00bc }
            r0.load(r1)     // Catch:{ all -> 0x00bc }
            java.lang.String r2 = "pv_post_url"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ all -> 0x00bc }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ all -> 0x00bc }
            if (r3 != 0) goto L_0x0035
            r6.d = r2     // Catch:{ all -> 0x00bc }
        L_0x0035:
            java.lang.String r2 = "tbs_download_stat_post_url"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ all -> 0x00bc }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ all -> 0x00bc }
            if (r3 != 0) goto L_0x0047
            r6.f = r2     // Catch:{ all -> 0x00bc }
        L_0x0047:
            java.lang.String r2 = "tbs_downloader_post_url"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ all -> 0x00bc }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ all -> 0x00bc }
            if (r3 != 0) goto L_0x0059
            r6.g = r2     // Catch:{ all -> 0x00bc }
        L_0x0059:
            java.lang.String r2 = "tbs_log_post_url"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ all -> 0x00bc }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ all -> 0x00bc }
            if (r3 != 0) goto L_0x006b
            r6.h = r2     // Catch:{ all -> 0x00bc }
        L_0x006b:
            java.lang.String r2 = "tips_url"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ all -> 0x00bc }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ all -> 0x00bc }
            if (r3 != 0) goto L_0x007d
            r6.i = r2     // Catch:{ all -> 0x00bc }
        L_0x007d:
            java.lang.String r2 = "tbs_cmd_post_url"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ all -> 0x00bc }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ all -> 0x00bc }
            if (r3 != 0) goto L_0x008f
            r6.j = r2     // Catch:{ all -> 0x00bc }
        L_0x008f:
            java.lang.String r2 = "tbs_emergency_post_url"
            java.lang.String r3 = ""
            java.lang.String r2 = r0.getProperty(r2, r3)     // Catch:{ all -> 0x00bc }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ all -> 0x00bc }
            if (r3 != 0) goto L_0x00a1
            r6.k = r2     // Catch:{ all -> 0x00bc }
        L_0x00a1:
            java.lang.String r2 = "pv_post_url_tk"
            java.lang.String r3 = ""
            java.lang.String r0 = r0.getProperty(r2, r3)     // Catch:{ all -> 0x00bc }
            java.lang.String r2 = ""
            boolean r2 = r2.equals(r0)     // Catch:{ all -> 0x00bc }
            if (r2 != 0) goto L_0x00b3
            r6.e = r0     // Catch:{ all -> 0x00bc }
        L_0x00b3:
            r1.close()     // Catch:{ IOException -> 0x00b7 }
            goto L_0x00f1
        L_0x00b7:
            r0 = move-exception
        L_0x00b8:
            r0.printStackTrace()     // Catch:{ all -> 0x00ff }
            goto L_0x00f1
        L_0x00bc:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x00c2
        L_0x00c1:
            r1 = move-exception
        L_0x00c2:
            java.io.StringWriter r2 = new java.io.StringWriter     // Catch:{ all -> 0x00f3 }
            r2.<init>()     // Catch:{ all -> 0x00f3 }
            java.io.PrintWriter r3 = new java.io.PrintWriter     // Catch:{ all -> 0x00f3 }
            r3.<init>(r2)     // Catch:{ all -> 0x00f3 }
            r1.printStackTrace(r3)     // Catch:{ all -> 0x00f3 }
            java.lang.String r1 = "TbsCommonConfig"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f3 }
            r3.<init>()     // Catch:{ all -> 0x00f3 }
            java.lang.String r4 = "exceptions occurred1:"
            r3.append(r4)     // Catch:{ all -> 0x00f3 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00f3 }
            r3.append(r2)     // Catch:{ all -> 0x00f3 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x00f3 }
            com.tencent.smtt.utils.TbsLog.e(r1, r2)     // Catch:{ all -> 0x00f3 }
            if (r0 == 0) goto L_0x00f1
            r0.close()     // Catch:{ IOException -> 0x00ef }
            goto L_0x00f1
        L_0x00ef:
            r0 = move-exception
            goto L_0x00b8
        L_0x00f1:
            monitor-exit(r6)
            return
        L_0x00f3:
            r1 = move-exception
            if (r0 == 0) goto L_0x00fe
            r0.close()     // Catch:{ IOException -> 0x00fa }
            goto L_0x00fe
        L_0x00fa:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x00ff }
        L_0x00fe:
            throw r1     // Catch:{ all -> 0x00ff }
        L_0x00ff:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.m.h():void");
    }

    private File i() {
        File file;
        File file2 = null;
        try {
            if (this.b == null) {
                String str = this.a.getApplicationContext().getApplicationInfo().packageName;
                if (!TextUtils.isEmpty(str)) {
                    boolean z = true;
                    boolean z2 = this.a.getPackageManager().checkPermission("android.permission.READ_EXTERNAL_STORAGE", str) == 0;
                    if (this.a.getPackageManager().checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", str) != 0) {
                        z = false;
                    }
                    if (!z2) {
                        if (!z) {
                            file = new File(FileUtil.a(this.a, 8));
                        }
                    }
                    TbsLog.i("TbsCommonConfig", "no permission,use sdcard default folder");
                    file = new File(FileUtil.a(this.a, 5));
                } else {
                    file = new File(FileUtil.a(this.a, 8));
                }
                this.b = file;
                if (this.b == null || !this.b.isDirectory()) {
                    return null;
                }
            }
            File file3 = new File(this.b, "tbsnet.conf");
            if (!file3.exists()) {
                TbsLog.e("TbsCommonConfig", "Get file(" + file3.getCanonicalPath() + ") failed!");
                return null;
            }
            try {
                TbsLog.w("TbsCommonConfig", "pathc:" + file3.getCanonicalPath());
                return file3;
            } catch (Throwable th) {
                File file4 = file3;
                th = th;
                file2 = file4;
            }
        } catch (Throwable th2) {
            th = th2;
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            TbsLog.e("TbsCommonConfig", "exceptions occurred2:" + stringWriter.toString());
            return file2;
        }
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.f;
    }

    public String d() {
        return this.g;
    }

    public String e() {
        return this.h;
    }

    public String f() {
        return this.e;
    }

    public String g() {
        return this.k;
    }
}
