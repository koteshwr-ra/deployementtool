package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.common.net.HttpHeaders;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.a;
import com.tencent.smtt.utils.p;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.mina.proxy.handlers.http.HttpProxyConstants;

class l {
    private static int e = 5;
    private static int f = 1;
    private static final String[] g = {"tbs_downloading_com.tencent.mtt", "tbs_downloading_com.tencent.mm", "tbs_downloading_com.tencent.mobileqq", "tbs_downloading_com.tencent.tbs", "tbs_downloading_com.qzone"};
    private Handler A;
    private Set<String> B;
    private int C = e;
    private boolean D;
    String a;
    String[] b = null;
    int c = 0;
    private boolean d = false;
    private Context h;
    private String i;
    private String j;
    private String k;
    private File l;
    private long m;
    private int n = 30000;
    private int o = 20000;
    private boolean p;
    private int q;
    private int r;
    private boolean s;
    private boolean t;
    private HttpURLConnection u;
    private String v;
    private TbsLogReport.TbsLogInfo w;
    private String x;
    private int y;
    private boolean z;

    public l(Context context) throws NullPointerException {
        Context applicationContext = context.getApplicationContext();
        this.h = applicationContext;
        this.w = TbsLogReport.getInstance(applicationContext).tbsLogInfo();
        this.B = new HashSet();
        this.v = "tbs_downloading_" + this.h.getPackageName();
        o.a();
        File t2 = o.t(this.h);
        this.l = t2;
        if (t2 != null) {
            g();
            this.x = null;
            this.y = -1;
            return;
        }
        throw new NullPointerException("TbsCorePrivateDir is null!");
    }

    private long a(long j2, long j3) {
        long currentTimeMillis = System.currentTimeMillis();
        this.w.setDownConsumeTime(currentTimeMillis - j2);
        this.w.setDownloadSize(j3);
        return currentTimeMillis;
    }

    static File a(Context context) {
        try {
            File file = Build.VERSION.SDK_INT >= 8 ? new File(FileUtil.a(context, 4)) : null;
            if (file != null && !file.exists() && !file.isDirectory()) {
                file.mkdirs();
            }
            return file;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.backupApkPath] Exception is " + e2.getMessage());
            return null;
        }
    }

    private static File a(Context context, int i2) {
        File file = new File(FileUtil.a(context, i2));
        if (file.exists() && file.isDirectory()) {
            if (new File(file, TbsDownloader.getOverSea(context) ? "x5.oversea.tbs.org" : TbsDownloader.getBackupFileName(false)).exists()) {
                return file;
            }
        }
        return null;
    }

    private String a(Throwable th) {
        String stackTraceString = Log.getStackTraceString(th);
        return stackTraceString.length() > 1024 ? stackTraceString.substring(0, 1024) : stackTraceString;
    }

    private String a(URL url) {
        try {
            return InetAddress.getByName(url.getHost()).getHostAddress();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        } catch (Error e3) {
            e3.printStackTrace();
            return "";
        }
    }

    private void a(int i2, String str, boolean z2) {
        if (z2 || this.q > this.C) {
            this.w.setErrorCode(i2);
            this.w.setFailDetail(str);
        }
    }

    private void a(long j2) {
        this.q++;
        if (j2 <= 0) {
            try {
                j2 = n();
            } catch (Exception unused) {
                return;
            }
        }
        Thread.sleep(j2);
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:11|12|(6:14|(1:16)(3:17|(1:19)(1:20)|21)|22|(5:25|(3:27|(2:33|64)|34)|63|35|(4:37|38|39|40)(2:41|42))|43|(4:47|(1:51)|52|(1:54)))|55|56|57) */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x014c, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x0149 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.io.File r11, android.content.Context r12) {
        /*
            java.lang.Class<com.tencent.smtt.utils.a> r0 = com.tencent.smtt.utils.a.class
            monitor-enter(r0)
            if (r11 == 0) goto L_0x014b
            boolean r1 = r11.exists()     // Catch:{ all -> 0x014d }
            if (r1 != 0) goto L_0x000d
            goto L_0x014b
        L_0x000d:
            boolean r1 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r12)     // Catch:{ all -> 0x014d }
            if (r1 == 0) goto L_0x0015
            monitor-exit(r0)     // Catch:{ all -> 0x014d }
            return
        L_0x0015:
            java.io.File r1 = a((android.content.Context) r12)     // Catch:{ Exception -> 0x0149 }
            if (r1 == 0) goto L_0x0149
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r12)     // Catch:{ Exception -> 0x0149 }
            android.content.SharedPreferences r2 = r2.mPreferences     // Catch:{ Exception -> 0x0149 }
            java.lang.String r3 = "tbs_download_version_type"
            r4 = 0
            int r2 = r2.getInt(r3, r4)     // Catch:{ Exception -> 0x0149 }
            r3 = 1
            if (r2 != r3) goto L_0x0035
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0149 }
            java.lang.String r5 = com.tencent.smtt.sdk.TbsDownloader.getBackupFileName(r3)     // Catch:{ Exception -> 0x0149 }
            r2.<init>(r1, r5)     // Catch:{ Exception -> 0x0149 }
            goto L_0x0047
        L_0x0035:
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0149 }
            boolean r5 = com.tencent.smtt.sdk.TbsDownloader.getOverSea(r12)     // Catch:{ Exception -> 0x0149 }
            if (r5 == 0) goto L_0x0040
            java.lang.String r5 = "x5.oversea.tbs.org"
            goto L_0x0044
        L_0x0040:
            java.lang.String r5 = com.tencent.smtt.sdk.TbsDownloader.getBackupFileName(r4)     // Catch:{ Exception -> 0x0149 }
        L_0x0044:
            r2.<init>(r1, r5)     // Catch:{ Exception -> 0x0149 }
        L_0x0047:
            r2.delete()     // Catch:{ Exception -> 0x0149 }
            com.tencent.smtt.utils.FileUtil.b((java.io.File) r11, (java.io.File) r2)     // Catch:{ Exception -> 0x0149 }
            java.lang.String r5 = r2.getName()     // Catch:{ Exception -> 0x0149 }
            java.lang.String r6 = "tbs.org"
            boolean r5 = r5.contains(r6)     // Catch:{ Exception -> 0x0149 }
            java.lang.String r2 = r2.getName()     // Catch:{ Exception -> 0x0149 }
            java.lang.String r6 = "x5.tbs.decouple"
            boolean r2 = r2.contains(r6)     // Catch:{ Exception -> 0x0149 }
            if (r2 != 0) goto L_0x0065
            if (r5 == 0) goto L_0x00e3
        L_0x0065:
            java.io.File[] r5 = r1.listFiles()     // Catch:{ Exception -> 0x0149 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0149 }
            r6.<init>()     // Catch:{ Exception -> 0x0149 }
            java.lang.String r7 = com.tencent.smtt.utils.a.a((boolean) r2)     // Catch:{ Exception -> 0x0149 }
            r6.append(r7)     // Catch:{ Exception -> 0x0149 }
            java.lang.String r7 = "(.*)"
            r6.append(r7)     // Catch:{ Exception -> 0x0149 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0149 }
            java.util.regex.Pattern r6 = java.util.regex.Pattern.compile(r6)     // Catch:{ Exception -> 0x0149 }
            int r7 = r5.length     // Catch:{ Exception -> 0x0149 }
            r8 = 0
        L_0x0084:
            if (r8 >= r7) goto L_0x00a8
            r9 = r5[r8]     // Catch:{ Exception -> 0x0149 }
            java.lang.String r10 = r9.getName()     // Catch:{ Exception -> 0x0149 }
            java.util.regex.Matcher r10 = r6.matcher(r10)     // Catch:{ Exception -> 0x0149 }
            boolean r10 = r10.find()     // Catch:{ Exception -> 0x0149 }
            if (r10 == 0) goto L_0x00a5
            boolean r10 = r9.isFile()     // Catch:{ Exception -> 0x0149 }
            if (r10 == 0) goto L_0x00a5
            boolean r10 = r9.exists()     // Catch:{ Exception -> 0x0149 }
            if (r10 == 0) goto L_0x00a5
            r9.delete()     // Catch:{ Exception -> 0x0149 }
        L_0x00a5:
            int r8 = r8 + 1
            goto L_0x0084
        L_0x00a8:
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r12)     // Catch:{ Exception -> 0x0149 }
            android.content.SharedPreferences r5 = r5.mPreferences     // Catch:{ Exception -> 0x0149 }
            java.lang.String r6 = "tbs_download_version"
            int r5 = r5.getInt(r6, r4)     // Catch:{ Exception -> 0x0149 }
            java.io.File r6 = new java.io.File     // Catch:{ Exception -> 0x0149 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0149 }
            r7.<init>()     // Catch:{ Exception -> 0x0149 }
            java.lang.String r2 = com.tencent.smtt.utils.a.a((boolean) r2)     // Catch:{ Exception -> 0x0149 }
            r7.append(r2)     // Catch:{ Exception -> 0x0149 }
            java.lang.String r2 = "."
            r7.append(r2)     // Catch:{ Exception -> 0x0149 }
            r7.append(r5)     // Catch:{ Exception -> 0x0149 }
            java.lang.String r2 = r7.toString()     // Catch:{ Exception -> 0x0149 }
            r6.<init>(r1, r2)     // Catch:{ Exception -> 0x0149 }
            boolean r2 = r6.exists()     // Catch:{ Exception -> 0x0149 }
            if (r2 == 0) goto L_0x00e0
            java.lang.String r11 = "TbsDownload"
            java.lang.String r12 = "[TbsApkDownloader.backupTbsApk]delete bacup config file error "
            com.tencent.smtt.utils.TbsLog.e(r11, r12)     // Catch:{ Exception -> 0x0149 }
            monitor-exit(r0)     // Catch:{ all -> 0x014d }
            return
        L_0x00e0:
            r6.createNewFile()     // Catch:{ Exception -> 0x0149 }
        L_0x00e3:
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r12)     // Catch:{ Exception -> 0x0149 }
            android.content.SharedPreferences r2 = r2.mPreferences     // Catch:{ Exception -> 0x0149 }
            java.lang.String r5 = "tbs_download_version_type"
            int r2 = r2.getInt(r5, r4)     // Catch:{ Exception -> 0x0149 }
            if (r2 == r3) goto L_0x0149
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r12)     // Catch:{ Exception -> 0x0149 }
            android.content.SharedPreferences r2 = r2.mPreferences     // Catch:{ Exception -> 0x0149 }
            java.lang.String r5 = "tbs_decouplecoreversion"
            int r2 = r2.getInt(r5, r4)     // Catch:{ Exception -> 0x0149 }
            int r5 = com.tencent.smtt.utils.a.a((android.content.Context) r12, (java.io.File) r11)     // Catch:{ Exception -> 0x0149 }
            if (r2 != r5) goto L_0x0149
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r12)     // Catch:{ Exception -> 0x0149 }
            android.content.SharedPreferences r2 = r2.mPreferences     // Catch:{ Exception -> 0x0149 }
            java.lang.String r5 = "tbs_responsecode"
            int r2 = r2.getInt(r5, r4)     // Catch:{ Exception -> 0x0149 }
            r4 = 5
            if (r2 == r4) goto L_0x0115
            r4 = 3
            if (r2 != r4) goto L_0x0130
        L_0x0115:
            java.lang.String r4 = "TbsApkDownloader"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0149 }
            r5.<init>()     // Catch:{ Exception -> 0x0149 }
            java.lang.String r6 = "response code="
            r5.append(r6)     // Catch:{ Exception -> 0x0149 }
            r5.append(r2)     // Catch:{ Exception -> 0x0149 }
            java.lang.String r2 = "return backup decouple apk"
            r5.append(r2)     // Catch:{ Exception -> 0x0149 }
            java.lang.String r2 = r5.toString()     // Catch:{ Exception -> 0x0149 }
            com.tencent.smtt.utils.TbsLog.i(r4, r2)     // Catch:{ Exception -> 0x0149 }
        L_0x0130:
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0149 }
            java.lang.String r3 = com.tencent.smtt.sdk.TbsDownloader.getBackupFileName(r3)     // Catch:{ Exception -> 0x0149 }
            r2.<init>(r1, r3)     // Catch:{ Exception -> 0x0149 }
            int r1 = com.tencent.smtt.utils.a.a((android.content.Context) r12, (java.io.File) r11)     // Catch:{ Exception -> 0x0149 }
            int r12 = com.tencent.smtt.utils.a.a((android.content.Context) r12, (java.io.File) r2)     // Catch:{ Exception -> 0x0149 }
            if (r1 == r12) goto L_0x0149
            r2.delete()     // Catch:{ Exception -> 0x0149 }
            com.tencent.smtt.utils.FileUtil.b((java.io.File) r11, (java.io.File) r2)     // Catch:{ Exception -> 0x0149 }
        L_0x0149:
            monitor-exit(r0)     // Catch:{ all -> 0x014d }
            return
        L_0x014b:
            monitor-exit(r0)     // Catch:{ all -> 0x014d }
            return
        L_0x014d:
            r11 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x014d }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.a(java.io.File, android.content.Context):void");
    }

    private void a(String str) throws Exception {
        URL url = new URL(str);
        HttpURLConnection httpURLConnection = this.u;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Throwable th) {
                TbsLog.e(TbsDownloader.LOGTAG, "[initHttpRequest] mHttpRequest.disconnect() Throwable:" + th.toString());
            }
        }
        HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
        this.u = httpURLConnection2;
        httpURLConnection2.setRequestProperty("User-Agent", TbsDownloader.b(this.h));
        this.u.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "identity");
        this.u.setRequestMethod(HttpProxyConstants.GET);
        this.u.setInstanceFollowRedirects(false);
        this.u.setConnectTimeout(this.o);
        this.u.setReadTimeout(this.n);
    }

    private boolean a(File file) {
        int i2 = TbsDownloadConfig.getInstance(this.h).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_USE_BACKUP_VERSION, 0);
        if (i2 == 0) {
            i2 = TbsDownloadConfig.getInstance(this.h).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
        }
        return a.a(this.h, file, 0, i2);
    }

    static File b(Context context) {
        try {
            if (Build.VERSION.SDK_INT < 8) {
                return null;
            }
            File a2 = a(context, 4);
            if (a2 == null) {
                a2 = a(context, 3);
            }
            if (a2 == null) {
                a2 = a(context, 2);
            }
            return a2 == null ? a(context, 1) : a2;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.backupApkPath] Exception is " + e2.getMessage());
            return null;
        }
    }

    private boolean b(int i2) {
        try {
            File file = new File(this.l, "x5.tbs");
            File a2 = a(this.h);
            if (a2 == null) {
                return false;
            }
            File file2 = new File(a2, TbsDownloader.getOverSea(this.h) ? "x5.oversea.tbs.org" : TbsDownloader.getBackupFileName(false));
            file.delete();
            FileUtil.b(file2, file);
            if (a.a(this.h, file, 0, i2)) {
                return true;
            }
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.copyTbsApkFromBackupToInstall] verifyTbsApk error!!");
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDownloader.copyTbsApkFromBackupToInstall] Exception is " + e2.getMessage());
            return false;
        }
    }

    public static void c(Context context) {
        try {
            o.a();
            File t2 = o.t(context);
            new File(t2, "x5.tbs").delete();
            new File(t2, "x5.tbs.temp").delete();
            File a2 = a(context);
            if (a2 != null) {
                new File(a2, TbsDownloader.getBackupFileName(false)).delete();
                new File(a2, "x5.oversea.tbs.org").delete();
                File[] listFiles = a2.listFiles();
                Pattern compile = Pattern.compile(a.a(true) + "(.*)");
                for (File file : listFiles) {
                    if (compile.matcher(file.getName()).find() && file.isFile() && file.exists()) {
                        file.delete();
                    }
                }
                Pattern compile2 = Pattern.compile(a.a(false) + "(.*)");
                for (File file2 : listFiles) {
                    if (compile2.matcher(file2.getName()).find() && file2.isFile() && file2.exists()) {
                        file2.delete();
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0085, code lost:
        if (r10 != r8) goto L_0x0087;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean c(boolean r13, boolean r14) {
        /*
            r12 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "[TbsApkDownloader.verifyTbsApk] isTempFile="
            r0.append(r1)
            r0.append(r13)
            java.lang.String r0 = r0.toString()
            java.lang.String r2 = "TbsDownload"
            com.tencent.smtt.utils.TbsLog.i(r2, r0)
            java.io.File r0 = new java.io.File
            java.io.File r3 = r12.l
            java.lang.String r4 = "x5.tbs"
            if (r13 != 0) goto L_0x0020
            r5 = r4
            goto L_0x0022
        L_0x0020:
            java.lang.String r5 = "x5.tbs.temp"
        L_0x0022:
            r0.<init>(r3, r5)
            boolean r3 = r0.exists()
            r5 = 0
            if (r3 != 0) goto L_0x002d
            return r5
        L_0x002d:
            android.content.Context r3 = r12.h
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)
            android.content.SharedPreferences r3 = r3.mPreferences
            java.lang.String r6 = "tbs_apk_md5"
            r7 = 0
            java.lang.String r3 = r3.getString(r6, r7)
            java.lang.String r6 = com.tencent.smtt.utils.a.a((java.io.File) r0)
            if (r3 == 0) goto L_0x01c9
            boolean r3 = r3.equals(r6)
            if (r3 != 0) goto L_0x004a
            goto L_0x01c9
        L_0x004a:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r8 = "[TbsApkDownloader.verifyTbsApk] md5("
            r3.append(r8)
            r3.append(r6)
            java.lang.String r6 = ") successful!"
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            r8 = 0
            if (r13 == 0) goto L_0x00bd
            android.content.Context r3 = r12.h
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)
            android.content.SharedPreferences r3 = r3.mPreferences
            java.lang.String r10 = "tbs_apkfilesize"
            long r10 = r3.getLong(r10, r8)
            boolean r3 = r0.exists()
            if (r3 == 0) goto L_0x0087
            int r3 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r3 <= 0) goto L_0x00bd
            long r8 = r0.length()
            int r3 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r3 == 0) goto L_0x00bd
        L_0x0087:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r1)
            r14.append(r13)
            java.lang.String r13 = " filelength failed"
            r14.append(r13)
            java.lang.String r13 = r14.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r13)
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r13 = r12.w
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r0 = "fileLength:"
            r14.append(r0)
            r14.append(r8)
            java.lang.String r0 = ",contentLength:"
            r14.append(r0)
            r14.append(r10)
            java.lang.String r14 = r14.toString()
            r13.setCheckErrorDetail(r14)
            return r5
        L_0x00bd:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r10 = "[TbsApkDownloader.verifyTbsApk] length("
            r3.append(r10)
            r3.append(r8)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            r3 = -1
            if (r14 == 0) goto L_0x0127
            if (r13 != 0) goto L_0x0127
            android.content.Context r3 = r12.h
            int r3 = com.tencent.smtt.utils.a.a((android.content.Context) r3, (java.io.File) r0)
            android.content.Context r8 = r12.h
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8)
            android.content.SharedPreferences r8 = r8.mPreferences
            java.lang.String r9 = "tbs_download_version"
            int r8 = r8.getInt(r9, r5)
            if (r8 == r3) goto L_0x0127
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r1)
            r14.append(r13)
            java.lang.String r0 = " versionCode failed"
            r14.append(r0)
            java.lang.String r14 = r14.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r14)
            if (r13 == 0) goto L_0x0126
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r13 = r12.w
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r0 = "fileVersion:"
            r14.append(r0)
            r14.append(r3)
            java.lang.String r0 = ",configVersion:"
            r14.append(r0)
            r14.append(r8)
            java.lang.String r14 = r14.toString()
            r13.setCheckErrorDetail(r14)
        L_0x0126:
            return r5
        L_0x0127:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "[TbsApkDownloader.verifyTbsApk] tbsApkVersionCode("
            r8.append(r9)
            r8.append(r3)
            r8.append(r6)
            java.lang.String r3 = r8.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r3)
            if (r14 == 0) goto L_0x018d
            if (r13 != 0) goto L_0x018d
            android.content.Context r14 = r12.h
            java.lang.String r14 = com.tencent.smtt.utils.b.a((android.content.Context) r14, (boolean) r5, (java.io.File) r0)
            java.lang.String r3 = "3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a"
            boolean r3 = r3.equals(r14)
            if (r3 != 0) goto L_0x018d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            r0.append(r13)
            java.lang.String r1 = " signature failed"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r0)
            if (r13 == 0) goto L_0x018c
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r13 = r12.w
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "signature:"
            r0.append(r1)
            if (r14 != 0) goto L_0x017a
            java.lang.String r14 = "null"
            goto L_0x0182
        L_0x017a:
            int r14 = r14.length()
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
        L_0x0182:
            r0.append(r14)
            java.lang.String r14 = r0.toString()
            r13.setCheckErrorDetail(r14)
        L_0x018c:
            return r5
        L_0x018d:
            java.lang.String r14 = "[TbsApkDownloader.verifyTbsApk] signature successful!"
            com.tencent.smtt.utils.TbsLog.i(r2, r14)
            r14 = 1
            if (r13 == 0) goto L_0x01b1
            java.io.File r13 = new java.io.File     // Catch:{ Exception -> 0x01a1 }
            java.io.File r1 = r12.l     // Catch:{ Exception -> 0x01a1 }
            r13.<init>(r1, r4)     // Catch:{ Exception -> 0x01a1 }
            boolean r13 = r0.renameTo(r13)     // Catch:{ Exception -> 0x01a1 }
            goto L_0x01a4
        L_0x01a1:
            r13 = move-exception
            r7 = r13
            r13 = 0
        L_0x01a4:
            if (r13 != 0) goto L_0x01b0
            r13 = 109(0x6d, float:1.53E-43)
            java.lang.String r0 = r12.a((java.lang.Throwable) r7)
            r12.a((int) r13, (java.lang.String) r0, (boolean) r14)
            return r5
        L_0x01b0:
            r5 = r13
        L_0x01b1:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r0 = "[TbsApkDownloader.verifyTbsApk] rename("
            r13.append(r0)
            r13.append(r5)
            r13.append(r6)
            java.lang.String r13 = r13.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r13)
            return r14
        L_0x01c9:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r14.append(r1)
            r14.append(r13)
            java.lang.String r0 = " md5 failed"
            r14.append(r0)
            java.lang.String r14 = r14.toString()
            com.tencent.smtt.utils.TbsLog.i(r2, r14)
            if (r13 == 0) goto L_0x01e9
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r13 = r12.w
            java.lang.String r14 = "fileMd5 not match"
            r13.setCheckErrorDetail(r14)
        L_0x01e9:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.c(boolean, boolean):boolean");
    }

    private void d(boolean z2) {
        Bundle a2;
        p.a(this.h);
        TbsDownloadConfig instance = TbsDownloadConfig.getInstance(this.h);
        instance.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_FULL_PACKAGE, false);
        instance.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false);
        instance.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE_REASON, -123);
        instance.commit();
        QbSdk.m.onDownloadFinish(z2 ? 100 : 120);
        int i2 = instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
        boolean a3 = TbsDownloader.a(this.h);
        if (i2 == 5) {
            a2 = a(i2, a3);
            if (a2 == null) {
                return;
            }
        } else if (i2 == 3 || i2 > 10000) {
            File a4 = a(this.h);
            if (a4 != null) {
                a2 = a(i2, a4, a3);
            } else {
                c();
                instance.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, true);
                instance.commit();
                return;
            }
        } else {
            o.a().a(this.h, new File(this.l, "x5.tbs").getAbsolutePath(), instance.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
            a(new File(this.l, "x5.tbs"), this.h);
            return;
        }
        o.a().b(this.h, a2);
    }

    private boolean e(boolean z2) {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.deleteFile] isApk=" + z2);
        File file = z2 ? new File(this.l, "x5.tbs") : new File(this.l, "x5.tbs.temp");
        if (file.exists()) {
            FileUtil.b(file);
        }
        return true;
    }

    private void g() {
        this.q = 0;
        this.r = 0;
        this.m = -1;
        this.k = null;
        this.p = false;
        this.s = false;
        this.t = false;
        this.z = false;
    }

    private void h() {
        TbsLogReport.EventType eventType;
        TbsLogReport tbsLogReport;
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.closeHttpRequest]");
        HttpURLConnection httpURLConnection = this.u;
        if (httpURLConnection != null) {
            if (!this.s) {
                this.w.setResolveIp(a(httpURLConnection.getURL()));
            }
            try {
                this.u.disconnect();
            } catch (Throwable th) {
                TbsLog.e(TbsDownloader.LOGTAG, "[closeHttpRequest] mHttpRequest.disconnect() Throwable:" + th.toString());
            }
            this.u = null;
        }
        int i2 = this.w.a;
        if (!this.s && this.z) {
            this.w.setEventTime(System.currentTimeMillis());
            String apnInfo = Apn.getApnInfo(this.h);
            if (apnInfo == null) {
                apnInfo = "";
            }
            int apnType = Apn.getApnType(this.h);
            this.w.setApn(apnInfo);
            this.w.setNetworkType(apnType);
            if (apnType != this.y || !apnInfo.equals(this.x)) {
                this.w.setNetworkChange(0);
            }
            if ((this.w.a == 0 || this.w.a == 107) && this.w.getDownFinalFlag() == 0 && (!Apn.isNetworkAvailable(this.h) || !m())) {
                a(101, (String) null, true);
            }
            if (TbsDownloader.a(this.h)) {
                tbsLogReport = TbsLogReport.getInstance(this.h);
                eventType = TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE;
            } else {
                tbsLogReport = TbsLogReport.getInstance(this.h);
                eventType = TbsLogReport.EventType.TYPE_DOWNLOAD;
            }
            tbsLogReport.eventReport(eventType, this.w);
            this.w.resetArgs();
            if (i2 != 100) {
                QbSdk.m.onDownloadFinish(i2);
            }
        } else if (!this.d) {
            TbsDownloader.a = false;
        }
    }

    private File i() {
        return TbsDownloader.a(this.h) ? new File(FileUtil.a(this.h, 4), TbsDownloader.getBackupFileName(true)) : new File(FileUtil.a(this.h, 4), TbsDownloader.getOverSea(this.h) ? "x5.oversea.tbs.org" : TbsDownloader.getBackupFileName(false));
    }

    private void j() {
        try {
            File i2 = i();
            if (i2 != null && i2.exists()) {
                FileUtil.b(i2);
                File[] listFiles = i2.getParentFile().listFiles();
                Pattern compile = Pattern.compile(a.a(TbsDownloader.a(this.h)) + "(.*)");
                for (File file : listFiles) {
                    if (compile.matcher(file.getName()).find() && file.isFile() && file.exists()) {
                        FileUtil.b(file);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private boolean k() {
        return new File(this.l, "x5.tbs.temp").exists();
    }

    private long l() {
        File file = new File(this.l, "x5.tbs.temp");
        if (file.exists()) {
            return file.length();
        }
        return 0;
    }

    private boolean m() {
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader;
        InputStream inputStream;
        Throwable th;
        boolean z2 = false;
        try {
            inputStream = Runtime.getRuntime().exec("ping " + "www.qq.com").getInputStream();
            try {
                inputStreamReader = new InputStreamReader(inputStream);
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                    int i2 = 0;
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine != null) {
                                if (!readLine.contains("TTL") && !readLine.contains("ttl")) {
                                    i2++;
                                    if (i2 >= 5) {
                                        break;
                                    }
                                } else {
                                    z2 = true;
                                }
                            } else {
                                break;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                th.printStackTrace();
                                a((Closeable) inputStream);
                                a((Closeable) inputStreamReader);
                                a((Closeable) bufferedReader);
                                return z2;
                            } catch (Throwable th3) {
                                a((Closeable) inputStream);
                                a((Closeable) inputStreamReader);
                                a((Closeable) bufferedReader);
                                throw th3;
                            }
                        }
                    }
                    z2 = true;
                } catch (Throwable th4) {
                    Throwable th5 = th4;
                    bufferedReader = null;
                    th = th5;
                    th.printStackTrace();
                    a((Closeable) inputStream);
                    a((Closeable) inputStreamReader);
                    a((Closeable) bufferedReader);
                    return z2;
                }
            } catch (Throwable th6) {
                bufferedReader = null;
                th = th6;
                inputStreamReader = null;
                th.printStackTrace();
                a((Closeable) inputStream);
                a((Closeable) inputStreamReader);
                a((Closeable) bufferedReader);
                return z2;
            }
        } catch (Throwable th7) {
            inputStreamReader = null;
            bufferedReader = null;
            th = th7;
            inputStream = null;
            th.printStackTrace();
            a((Closeable) inputStream);
            a((Closeable) inputStreamReader);
            a((Closeable) bufferedReader);
            return z2;
        }
        a((Closeable) inputStream);
        a((Closeable) inputStreamReader);
        a((Closeable) bufferedReader);
        return z2;
    }

    private long n() {
        int i2 = this.q;
        return (i2 == 1 || i2 == 2) ? ((long) this.q) * 20000 : (i2 == 3 || i2 == 4) ? 100000 : 200000;
    }

    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v4, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v5, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r3v10 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008e A[SYNTHETIC, Splitter:B:25:0x008e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean o() {
        /*
            r8 = this;
            android.content.Context r0 = r8.h
            int r0 = com.tencent.smtt.utils.Apn.getApnType(r0)
            r1 = 1
            r2 = 0
            r3 = 3
            if (r0 != r3) goto L_0x000d
            r0 = 1
            goto L_0x000e
        L_0x000d:
            r0 = 0
        L_0x000e:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "[TbsApkDwonloader.detectWifiNetworkAvailable] isWifi="
            r3.append(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "TbsDownload"
            com.tencent.smtt.utils.TbsLog.i(r4, r3)
            r3 = 0
            if (r0 == 0) goto L_0x009a
            android.content.Context r0 = r8.h
            java.lang.String r0 = com.tencent.smtt.utils.Apn.getWifiSSID(r0)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "[TbsApkDwonloader.detectWifiNetworkAvailable] localBSSID="
            r5.append(r6)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            com.tencent.smtt.utils.TbsLog.i(r4, r5)
            java.net.URL r5 = new java.net.URL     // Catch:{ all -> 0x0088 }
            java.lang.String r6 = "https://pms.mb.qq.com/rsp204"
            r5.<init>(r6)     // Catch:{ all -> 0x0088 }
            java.net.URLConnection r5 = r5.openConnection()     // Catch:{ all -> 0x0088 }
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ all -> 0x0088 }
            r5.setInstanceFollowRedirects(r2)     // Catch:{ all -> 0x0085 }
            r3 = 10000(0x2710, float:1.4013E-41)
            r5.setConnectTimeout(r3)     // Catch:{ all -> 0x0085 }
            r5.setReadTimeout(r3)     // Catch:{ all -> 0x0085 }
            r5.setUseCaches(r2)     // Catch:{ all -> 0x0085 }
            r5.getInputStream()     // Catch:{ all -> 0x0085 }
            int r3 = r5.getResponseCode()     // Catch:{ all -> 0x0085 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0085 }
            r6.<init>()     // Catch:{ all -> 0x0085 }
            java.lang.String r7 = "[TbsApkDwonloader.detectWifiNetworkAvailable] responseCode="
            r6.append(r7)     // Catch:{ all -> 0x0085 }
            r6.append(r3)     // Catch:{ all -> 0x0085 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0085 }
            com.tencent.smtt.utils.TbsLog.i(r4, r6)     // Catch:{ all -> 0x0085 }
            r4 = 204(0xcc, float:2.86E-43)
            if (r3 != r4) goto L_0x007c
            goto L_0x007d
        L_0x007c:
            r1 = 0
        L_0x007d:
            if (r5 == 0) goto L_0x0082
            r5.disconnect()     // Catch:{ Exception -> 0x0082 }
        L_0x0082:
            r3 = r0
            r2 = r1
            goto L_0x009a
        L_0x0085:
            r1 = move-exception
            r3 = r5
            goto L_0x0089
        L_0x0088:
            r1 = move-exception
        L_0x0089:
            r1.printStackTrace()     // Catch:{ all -> 0x0093 }
            if (r3 == 0) goto L_0x0091
            r3.disconnect()     // Catch:{ Exception -> 0x0091 }
        L_0x0091:
            r3 = r0
            goto L_0x009a
        L_0x0093:
            r0 = move-exception
            if (r3 == 0) goto L_0x0099
            r3.disconnect()     // Catch:{ Exception -> 0x0099 }
        L_0x0099:
            throw r0
        L_0x009a:
            if (r2 != 0) goto L_0x00c2
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L_0x00c2
            java.util.Set<java.lang.String> r0 = r8.B
            boolean r0 = r0.contains(r3)
            if (r0 != 0) goto L_0x00c2
            java.util.Set<java.lang.String> r0 = r8.B
            r0.add(r3)
            r8.p()
            android.os.Handler r0 = r8.A
            r1 = 150(0x96, float:2.1E-43)
            android.os.Message r0 = r0.obtainMessage(r1, r3)
            android.os.Handler r1 = r8.A
            r4 = 120000(0x1d4c0, double:5.9288E-319)
            r1.sendMessageDelayed(r0, r4)
        L_0x00c2:
            if (r2 == 0) goto L_0x00d1
            java.util.Set<java.lang.String> r0 = r8.B
            boolean r0 = r0.contains(r3)
            if (r0 == 0) goto L_0x00d1
            java.util.Set<java.lang.String> r0 = r8.B
            r0.remove(r3)
        L_0x00d1:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.o():boolean");
    }

    private void p() {
        if (this.A == null) {
            this.A = new Handler(n.a().getLooper()) {
                public void handleMessage(Message message) {
                    if (message.what == 150) {
                        boolean unused = l.this.o();
                    }
                }
            };
        }
    }

    public Bundle a(int i2, File file, boolean z2) {
        File file2;
        if (z2) {
            file2 = new File(file, TbsDownloader.getBackupFileName(true));
        } else {
            file2 = new File(file, TbsDownloader.getOverSea(this.h) ? "x5.oversea.tbs.org" : TbsDownloader.getBackupFileName(false));
        }
        int a2 = a.a(this.h, file2);
        File file3 = new File(this.l, "x5.tbs");
        String absolutePath = file3.exists() ? file3.getAbsolutePath() : null;
        int i3 = TbsDownloadConfig.getInstance(this.h).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
        Bundle bundle = new Bundle();
        bundle.putInt("operation", i2);
        bundle.putInt("old_core_ver", a2);
        bundle.putInt("new_core_ver", i3);
        bundle.putString("old_apk_location", file2.getAbsolutePath());
        bundle.putString("new_apk_location", absolutePath);
        bundle.putString("diff_file_location", absolutePath);
        return bundle;
    }

    public Bundle a(int i2, boolean z2) {
        int i3;
        File file;
        o oVar;
        int i4;
        Context context;
        o a2 = o.a();
        Context context2 = this.h;
        if (z2) {
            file = a2.q(context2);
            i3 = o.a().i(this.h);
        } else {
            file = a2.r(context2);
            i3 = o.a().j(this.h);
        }
        File file2 = new File(this.l, "x5.tbs");
        String absolutePath = file2.exists() ? file2.getAbsolutePath() : null;
        if (absolutePath == null) {
            return null;
        }
        int i5 = TbsDownloadConfig.getInstance(this.h).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
        if (z2) {
            oVar = o.a();
            context = this.h;
            i4 = 6;
        } else {
            oVar = o.a();
            context = this.h;
            i4 = 5;
        }
        File f2 = oVar.f(context, i4);
        Bundle bundle = new Bundle();
        bundle.putInt("operation", i2);
        bundle.putInt("old_core_ver", i3);
        bundle.putInt("new_core_ver", i5);
        bundle.putString("old_apk_location", file.getAbsolutePath());
        bundle.putString("new_apk_location", f2.getAbsolutePath());
        bundle.putString("diff_file_location", absolutePath);
        String a3 = FileUtil.a(this.h, 7);
        File file3 = new File(a3);
        if (!file3.exists()) {
            file3.mkdirs();
        }
        bundle.putString("backup_apk", new File(a3, i5 + ".tbs").getAbsolutePath());
        return bundle;
    }

    public void a(int i2) {
        if (o.a().u(this.h)) {
            o.a().b();
            try {
                File file = new File(this.l, "x5.tbs");
                int a2 = a.a(this.h, file);
                if (-1 == a2 || (i2 > 0 && i2 == a2)) {
                    FileUtil.b(file);
                }
            } catch (Exception unused) {
            }
        }
    }

    public void a(boolean z2) {
        b(z2, false);
    }

    public boolean a() {
        TbsLog.i("TbsApkDownloader", "verifyAndInstallDecoupleCoreFromBackup #1");
        try {
            File file = new File(FileUtil.a(this.h, 4), TbsDownloader.getBackupFileName(true));
            if (file.exists()) {
                TbsLog.i("TbsApkDownloader", "verifyAndInstallDecoupleCoreFromBackup #2");
            } else {
                File b2 = TbsDownloader.b(TbsDownloadConfig.getInstance(this.h).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, -1));
                if (b2 != null && b2.exists()) {
                    FileUtil.b(b2, file);
                }
            }
            if (!a.a(this.h, file, 0, TbsDownloadConfig.getInstance(this.h).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, -1))) {
                return false;
            }
            TbsLog.i("TbsApkDownloader", "verifyAndInstallDecoupleCoreFromBackup #3");
            return o.a().f(this.h);
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(boolean r9, boolean r10) {
        /*
            r8 = this;
            int r9 = android.os.Build.VERSION.SDK_INT
            r0 = 0
            r1 = 23
            if (r9 != r1) goto L_0x0008
            return r0
        L_0x0008:
            android.content.Context r9 = r8.h
            com.tencent.smtt.sdk.TbsDownloadConfig r9 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9)
            android.content.SharedPreferences r9 = r9.mPreferences
            java.lang.String r1 = "use_backup_version"
            int r9 = r9.getInt(r1, r0)
            com.tencent.smtt.sdk.o r1 = com.tencent.smtt.sdk.o.a()
            android.content.Context r2 = r8.h
            int r1 = r1.j(r2)
            if (r9 != 0) goto L_0x0033
            android.content.Context r9 = r8.h
            com.tencent.smtt.sdk.TbsDownloadConfig r9 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9)
            android.content.SharedPreferences r9 = r9.mPreferences
            java.lang.String r2 = "tbs_download_version"
            int r9 = r9.getInt(r2, r0)
            java.lang.String r2 = "by default key"
            goto L_0x0035
        L_0x0033:
            java.lang.String r2 = "by new key"
        L_0x0035:
            r8.a = r2
            if (r9 == 0) goto L_0x0156
            if (r9 != r1) goto L_0x003d
            goto L_0x0156
        L_0x003d:
            java.lang.String r1 = "tbs_download_interrupt_code_reason"
            r2 = -214(0xffffffffffffff2a, float:NaN)
            r3 = 1
            if (r10 == 0) goto L_0x0116
            java.io.File r4 = com.tencent.smtt.sdk.TbsDownloader.a((int) r9)
            if (r4 == 0) goto L_0x0084
            boolean r5 = r4.exists()
            if (r5 == 0) goto L_0x0084
            java.io.File r5 = new java.io.File
            android.content.Context r6 = r8.h
            r7 = 4
            java.lang.String r6 = com.tencent.smtt.utils.FileUtil.a((android.content.Context) r6, (int) r7)
            android.content.Context r7 = r8.h
            boolean r7 = com.tencent.smtt.sdk.TbsDownloader.getOverSea(r7)
            if (r7 == 0) goto L_0x0064
            java.lang.String r7 = "x5.oversea.tbs.org"
            goto L_0x0068
        L_0x0064:
            java.lang.String r7 = com.tencent.smtt.sdk.TbsDownloader.getBackupFileName(r0)
        L_0x0068:
            r5.<init>(r6, r7)
            android.content.Context r6 = r8.h     // Catch:{ Exception -> 0x0080 }
            com.tencent.smtt.sdk.TbsDownloadConfig r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r6)     // Catch:{ Exception -> 0x0080 }
            android.content.SharedPreferences r6 = r6.mPreferences     // Catch:{ Exception -> 0x0080 }
            java.lang.String r7 = "tbs_download_version_type"
            int r6 = r6.getInt(r7, r0)     // Catch:{ Exception -> 0x0080 }
            if (r6 == r3) goto L_0x0084
            com.tencent.smtt.utils.FileUtil.b((java.io.File) r4, (java.io.File) r5)     // Catch:{ Exception -> 0x0080 }
            r5 = 1
            goto L_0x0085
        L_0x0080:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0084:
            r5 = 0
        L_0x0085:
            java.io.File r6 = r8.i()
            if (r6 == 0) goto L_0x00f6
            boolean r7 = r6.exists()
            if (r7 == 0) goto L_0x00f6
            boolean r6 = r8.a((java.io.File) r6)
            if (r6 == 0) goto L_0x00f6
            boolean r9 = r8.b((int) r9)
            if (r9 == 0) goto L_0x0116
            android.content.Context r9 = r8.h
            com.tencent.smtt.sdk.TbsDownloadConfig r9 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9)
            java.util.Map<java.lang.String, java.lang.Object> r9 = r9.mSyncMap
            java.lang.Integer r10 = java.lang.Integer.valueOf(r2)
            r9.put(r1, r10)
            android.content.Context r9 = r8.h
            com.tencent.smtt.sdk.TbsDownloadConfig r9 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9)
            r9.setDownloadInterruptCode(r2)
            r8.d(r0)
            if (r5 == 0) goto L_0x00f5
            r9 = 100
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = "use local backup apk in startDownload"
            r10.append(r0)
            java.lang.String r0 = r8.a
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            r8.a((int) r9, (java.lang.String) r10, (boolean) r3)
            android.content.Context r9 = r8.h
            boolean r9 = com.tencent.smtt.sdk.TbsDownloader.a((android.content.Context) r9)
            if (r9 == 0) goto L_0x00e3
            android.content.Context r9 = r8.h
            com.tencent.smtt.sdk.TbsLogReport r9 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r9)
            com.tencent.smtt.sdk.TbsLogReport$EventType r10 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE
            goto L_0x00eb
        L_0x00e3:
            android.content.Context r9 = r8.h
            com.tencent.smtt.sdk.TbsLogReport r9 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r9)
            com.tencent.smtt.sdk.TbsLogReport$EventType r10 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD
        L_0x00eb:
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r0 = r8.w
            r9.eventReport(r10, r0)
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r9 = r8.w
            r9.resetArgs()
        L_0x00f5:
            return r3
        L_0x00f6:
            r8.j()
            if (r4 == 0) goto L_0x0116
            boolean r5 = r4.exists()
            if (r5 == 0) goto L_0x0116
            android.content.Context r5 = r8.h
            r6 = 0
            boolean r9 = com.tencent.smtt.utils.a.a(r5, r4, r6, r9)
            if (r9 != 0) goto L_0x0116
            if (r4 == 0) goto L_0x0116
            boolean r9 = r4.exists()
            if (r9 == 0) goto L_0x0116
            com.tencent.smtt.utils.FileUtil.b((java.io.File) r4)
        L_0x0116:
            boolean r9 = r8.c(r0, r10)
            if (r9 == 0) goto L_0x0138
            android.content.Context r9 = r8.h
            com.tencent.smtt.sdk.TbsDownloadConfig r9 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9)
            java.util.Map<java.lang.String, java.lang.Object> r9 = r9.mSyncMap
            java.lang.Integer r10 = java.lang.Integer.valueOf(r2)
            r9.put(r1, r10)
            android.content.Context r9 = r8.h
            com.tencent.smtt.sdk.TbsDownloadConfig r9 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9)
            r9.setDownloadInterruptCode(r2)
            r8.d(r0)
            return r3
        L_0x0138:
            boolean r9 = r8.e(r3)
            if (r9 != 0) goto L_0x0156
            boolean r9 = r8.e(r3)
            if (r9 != 0) goto L_0x0156
            java.lang.String r9 = "TbsDownload"
            java.lang.String r10 = "[TbsApkDownloader] delete file failed!"
            com.tencent.smtt.utils.TbsLog.e(r9, r10)
            android.content.Context r9 = r8.h
            com.tencent.smtt.sdk.TbsDownloadConfig r9 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9)
            r10 = -301(0xfffffffffffffed3, float:NaN)
            r9.setDownloadInterruptCode(r10)
        L_0x0156:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.a(boolean, boolean):boolean");
    }

    public void b() {
        TbsLogReport.EventType eventType;
        TbsLogReport tbsLogReport;
        this.s = true;
        if (TbsShareManager.isThirdPartyApp(this.h)) {
            TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(this.h).tbsLogInfo();
            tbsLogInfo.setErrorCode(-309);
            tbsLogInfo.setFailDetail((Throwable) new Exception());
            if (TbsDownloader.a(this.h)) {
                tbsLogReport = TbsLogReport.getInstance(this.h);
                eventType = TbsLogReport.EventType.TYPE_DOWNLOAD_DECOUPLE;
            } else {
                tbsLogReport = TbsLogReport.getInstance(this.h);
                eventType = TbsLogReport.EventType.TYPE_DOWNLOAD;
            }
            tbsLogReport.eventReport(eventType, tbsLogInfo);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:145:0x038d, code lost:
        if (r10.equals(r1.x) == false) goto L_0x0393;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x0420, code lost:
        r2.put(r13, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x04db, code lost:
        if (r2 == false) goto L_0x04dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x04dd, code lost:
        r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.h).mSyncMap;
        r3 = java.lang.Long.valueOf(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x04ef, code lost:
        if (r2 == false) goto L_0x04f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x058f, code lost:
        if (r2 != false) goto L_0x069e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:299:0x068b, code lost:
        if (r2 != false) goto L_0x069e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:300:0x068d, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.h).mSyncMap.put(r10, java.lang.Long.valueOf(r8));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:301:0x069e, code lost:
        r11 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:341:?, code lost:
        com.tencent.smtt.utils.TbsLog.i(r11, "STEP 1/2 begin downloading...Canceled!", true);
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.h).setDownloadInterruptCode(-309);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:342:0x0737, code lost:
        r33 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:343:0x0739, code lost:
        r10 = r11;
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:345:0x073d, code lost:
        r11 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:346:0x0740, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:347:0x0741, code lost:
        r5 = r0;
        r7 = r15;
        r8 = r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x0746, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:349:0x0747, code lost:
        r5 = r0;
        r8 = r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:356:0x075a, code lost:
        if (r1.b == null) goto L_0x0778;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:358:0x0761, code lost:
        if (c(true, r4) != false) goto L_0x0778;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:0x0763, code lost:
        if (r2 != false) goto L_0x076e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:361:0x0769, code lost:
        if (b(false) == false) goto L_0x076e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:362:0x076b, code lost:
        r10 = r11;
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:363:0x076e, code lost:
        r1.t = true;
        r10 = r11;
        r5 = false;
        r16 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:364:0x0778, code lost:
        r1.t = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:365:0x077d, code lost:
        if (r1.b == null) goto L_0x0781;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:366:0x077f, code lost:
        r16 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:0x0781, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.h).setDownloadInterruptCode(-311);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:377:0x07b3, code lost:
        r5 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:379:?, code lost:
        com.tencent.smtt.utils.TbsLog.i(r5, r28, true);
        r3 = new java.lang.StringBuilder();
        r3.append("downloadFlow=");
        r3.append(r10);
        r3.append(" downloadMaxflow=");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:380:0x07cd, code lost:
        r6 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:382:?, code lost:
        r3.append(r6);
        a(112, r3.toString(), true);
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.h).setDownloadInterruptCode(-307);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:383:0x07e7, code lost:
        r26 = r6;
        r31 = r10;
        r10 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:384:0x07ee, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:385:0x07ef, code lost:
        r26 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:386:0x07f2, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:387:0x07f3, code lost:
        r26 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:388:0x07f6, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:389:0x07f7, code lost:
        r8 = r10;
        r7 = r15;
        r11 = r4;
        r10 = r5;
        r4 = r33;
        r5 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:390:0x0800, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:391:0x0801, code lost:
        r8 = r10;
        r11 = r4;
        r10 = r5;
        r4 = r33;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:523:0x0adb, code lost:
        if (r2 == false) goto L_0x0aff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:529:0x0afd, code lost:
        if (r2 == false) goto L_0x0aff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0213, code lost:
        if (r2 == false) goto L_0x0215;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0215, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r1.h).mSyncMap.put(r14, java.lang.Long.valueOf(r8));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0250, code lost:
        if (r2 == false) goto L_0x0215;
     */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0299 A[SYNTHETIC, Splitter:B:102:0x0299] */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x02ad  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0349  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x035b A[Catch:{ all -> 0x0b4e }] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x035d A[Catch:{ all -> 0x0b4e }] */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x0371 A[SYNTHETIC, Splitter:B:134:0x0371] */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0387 A[SYNTHETIC, Splitter:B:143:0x0387] */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x03a4 A[SYNTHETIC, Splitter:B:152:0x03a4] */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x03ce A[SYNTHETIC, Splitter:B:157:0x03ce] */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0405 A[SYNTHETIC, Splitter:B:173:0x0405] */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x0425  */
    /* JADX WARNING: Removed duplicated region for block: B:350:0x074d  */
    /* JADX WARNING: Removed duplicated region for block: B:428:0x0908 A[SYNTHETIC, Splitter:B:428:0x0908] */
    /* JADX WARNING: Removed duplicated region for block: B:436:0x0942  */
    /* JADX WARNING: Removed duplicated region for block: B:492:0x0a45  */
    /* JADX WARNING: Removed duplicated region for block: B:524:0x0ade  */
    /* JADX WARNING: Removed duplicated region for block: B:559:0x0b63 A[ADDED_TO_REGION, Catch:{ all -> 0x0ca2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:572:0x0bd9  */
    /* JADX WARNING: Removed duplicated region for block: B:591:0x0c30  */
    /* JADX WARNING: Removed duplicated region for block: B:592:0x0c44  */
    /* JADX WARNING: Removed duplicated region for block: B:598:0x0c6f  */
    /* JADX WARNING: Removed duplicated region for block: B:626:0x0239 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:627:0x0bb7 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:631:0x0726 A[EDGE_INSN: B:631:0x0726->B:339:0x0726 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x026b A[SYNTHETIC, Splitter:B:93:0x026b] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x026e A[SYNTHETIC, Splitter:B:95:0x026e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(boolean r40, boolean r41) {
        /*
            r39 = this;
            r1 = r39
            r2 = r40
            java.lang.String r3 = "tbs_downloadstarttime"
            com.tencent.smtt.sdk.o r4 = com.tencent.smtt.sdk.o.a()
            android.content.Context r5 = r1.h
            boolean r4 = r4.d(r5)
            r5 = 0
            if (r4 == 0) goto L_0x0023
            if (r2 != 0) goto L_0x0023
            com.tencent.smtt.sdk.TbsDownloader.a = r5
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r3 = -322(0xfffffffffffffebe, float:NaN)
            r2.setDownloadInterruptCode(r3)
            return
        L_0x0023:
            android.content.Context r4 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r4)
            android.content.SharedPreferences r4 = r4.mPreferences
            java.lang.String r6 = "tbs_responsecode"
            int r4 = r4.getInt(r6, r5)
            r6 = 2
            r7 = 1
            if (r4 == r7) goto L_0x003d
            if (r4 == r6) goto L_0x003d
            r8 = 4
            if (r4 != r8) goto L_0x003b
            goto L_0x003d
        L_0x003b:
            r4 = 0
            goto L_0x003e
        L_0x003d:
            r4 = 1
        L_0x003e:
            if (r41 != 0) goto L_0x0049
            boolean r8 = r1.a((boolean) r2, (boolean) r4)
            if (r8 == 0) goto L_0x0049
            com.tencent.smtt.sdk.TbsDownloader.a = r5
            return
        L_0x0049:
            r1.D = r2
            android.content.Context r8 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8)
            android.content.SharedPreferences r8 = r8.mPreferences
            java.lang.String r9 = "tbs_downloadurl"
            r10 = 0
            java.lang.String r8 = r8.getString(r9, r10)
            r1.i = r8
            android.content.Context r8 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8)
            android.content.SharedPreferences r8 = r8.mPreferences
            java.lang.String r9 = "tbs_downloadurl_list"
            java.lang.String r8 = r8.getString(r9, r10)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r11 = "backupUrlStrings:"
            r9.append(r11)
            r9.append(r8)
            java.lang.String r9 = r9.toString()
            java.lang.String r11 = "TbsDownload"
            com.tencent.smtt.utils.TbsLog.i(r11, r9, r7)
            r1.b = r10
            r1.c = r5
            if (r2 != 0) goto L_0x00a0
            if (r8 == 0) goto L_0x00a0
            java.lang.String r9 = r8.trim()
            java.lang.String r12 = ""
            boolean r9 = r12.equals(r9)
            if (r9 != 0) goto L_0x00a0
            java.lang.String r9 = r8.trim()
            java.lang.String r12 = ";"
            java.lang.String[] r9 = r9.split(r12)
            r1.b = r9
        L_0x00a0:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r12 = "[TbsApkDownloader.startDownload] mDownloadUrl="
            r9.append(r12)
            java.lang.String r12 = r1.i
            r9.append(r12)
            java.lang.String r12 = " backupUrlStrings="
            r9.append(r12)
            r9.append(r8)
            java.lang.String r8 = " mLocation="
            r9.append(r8)
            java.lang.String r8 = r1.k
            r9.append(r8)
            java.lang.String r8 = " mCanceled="
            r9.append(r8)
            boolean r8 = r1.s
            r9.append(r8)
            java.lang.String r8 = " mHttpRequest="
            r9.append(r8)
            java.net.HttpURLConnection r8 = r1.u
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            com.tencent.smtt.utils.TbsLog.i(r11, r8)
            java.lang.String r8 = r1.i
            r9 = 110(0x6e, float:1.54E-43)
            if (r8 != 0) goto L_0x00f7
            java.lang.String r8 = r1.k
            if (r8 != 0) goto L_0x00f7
            com.tencent.smtt.sdk.TbsListener r2 = com.tencent.smtt.sdk.QbSdk.m
            r2.onDownloadFinish(r9)
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r3 = -302(0xfffffffffffffed2, float:NaN)
            r2.setDownloadInterruptCode(r3)
            return
        L_0x00f7:
            java.net.HttpURLConnection r8 = r1.u
            if (r8 == 0) goto L_0x0110
            boolean r8 = r1.s
            if (r8 != 0) goto L_0x0110
            com.tencent.smtt.sdk.TbsListener r2 = com.tencent.smtt.sdk.QbSdk.m
            r2.onDownloadFinish(r9)
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r3 = -303(0xfffffffffffffed1, float:NaN)
            r2.setDownloadInterruptCode(r3)
            return
        L_0x0110:
            r8 = -304(0xfffffffffffffed0, float:NaN)
            if (r2 != 0) goto L_0x0136
            java.util.Set<java.lang.String> r12 = r1.B
            android.content.Context r13 = r1.h
            java.lang.String r13 = com.tencent.smtt.utils.Apn.getWifiSSID(r13)
            boolean r12 = r12.contains(r13)
            if (r12 == 0) goto L_0x0136
            java.lang.String r2 = "[TbsApkDownloader.startDownload] WIFI Unavailable"
            com.tencent.smtt.utils.TbsLog.i(r11, r2)
            com.tencent.smtt.sdk.TbsListener r2 = com.tencent.smtt.sdk.QbSdk.m
            r2.onDownloadFinish(r9)
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r2.setDownloadInterruptCode(r8)
            return
        L_0x0136:
            r39.g()
            java.lang.String r9 = "STEP 1/2 begin downloading..."
            com.tencent.smtt.utils.TbsLog.i(r11, r9, r7)
            android.content.Context r9 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r9 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9)
            long r12 = r9.getDownloadMaxflow()
            android.content.Context r9 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r9 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r9)
            android.content.SharedPreferences r9 = r9.mPreferences
            java.lang.String r14 = "tbs_downloadflow"
            r5 = 0
            long r16 = r9.getLong(r14, r5)
            if (r2 == 0) goto L_0x015d
            int r9 = f
            goto L_0x015f
        L_0x015d:
            int r9 = e
        L_0x015f:
            r1.C = r9
            r8 = r16
            r16 = 0
        L_0x0165:
            int r15 = r1.q
            int r5 = r1.C
            if (r15 <= r5) goto L_0x016d
            goto L_0x069e
        L_0x016d:
            int r5 = r1.r
            r6 = 8
            if (r5 <= r6) goto L_0x0185
            r2 = 123(0x7b, float:1.72E-43)
            r1.a((int) r2, (java.lang.String) r10, (boolean) r7)
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r3 = -306(0xfffffffffffffece, float:NaN)
            r2.setDownloadInterruptCode(r3)
            goto L_0x069e
        L_0x0185:
            long r5 = java.lang.System.currentTimeMillis()
            java.lang.String r10 = "STEP 1/2 begin downloading...failed because you exceeded max flow!"
            if (r2 != 0) goto L_0x0262
            android.content.Context r15 = r1.h     // Catch:{ all -> 0x0257 }
            com.tencent.smtt.sdk.TbsDownloadConfig r15 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r15)     // Catch:{ all -> 0x0257 }
            android.content.SharedPreferences r15 = r15.mPreferences     // Catch:{ all -> 0x0257 }
            r23 = r8
            r7 = 0
            long r25 = r15.getLong(r3, r7)     // Catch:{ all -> 0x0253 }
            long r7 = r5 - r25
            r25 = 86400000(0x5265c00, double:4.2687272E-316)
            int r9 = (r7 > r25 ? 1 : (r7 == r25 ? 0 : -1))
            if (r9 <= 0) goto L_0x01e4
            java.lang.String r7 = "[TbsApkDownloader.startDownload] OVER DOWNLOAD_PERIOD"
            com.tencent.smtt.utils.TbsLog.i(r11, r7)     // Catch:{ all -> 0x01d9 }
            android.content.Context r7 = r1.h     // Catch:{ all -> 0x01d9 }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ all -> 0x01d9 }
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.mSyncMap     // Catch:{ all -> 0x01d9 }
            java.lang.Long r8 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x01d9 }
            r7.put(r3, r8)     // Catch:{ all -> 0x01d9 }
            android.content.Context r7 = r1.h     // Catch:{ all -> 0x01d9 }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ all -> 0x01d9 }
            java.util.Map<java.lang.String, java.lang.Object> r7 = r7.mSyncMap     // Catch:{ all -> 0x01d9 }
            r8 = 0
            java.lang.Long r15 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x01d9 }
            r7.put(r14, r15)     // Catch:{ all -> 0x01d9 }
            android.content.Context r7 = r1.h     // Catch:{ all -> 0x01d9 }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ all -> 0x01d9 }
            r7.commit()     // Catch:{ all -> 0x01d9 }
            r7 = -307(0xfffffffffffffecd, float:NaN)
            r8 = 0
            goto L_0x0231
        L_0x01d9:
            r0 = move-exception
            r5 = r0
            r10 = r11
            r26 = r12
            r8 = r23
            r23 = r3
            goto L_0x025e
        L_0x01e4:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0253 }
            r7.<init>()     // Catch:{ all -> 0x0253 }
            java.lang.String r8 = "[TbsApkDownloader.startDownload] downloadFlow="
            r7.append(r8)     // Catch:{ all -> 0x0253 }
            r8 = r23
            r7.append(r8)     // Catch:{ all -> 0x0257 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0257 }
            com.tencent.smtt.utils.TbsLog.i(r11, r7)     // Catch:{ all -> 0x0257 }
            int r7 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r7 < 0) goto L_0x022f
            r5 = 1
            com.tencent.smtt.utils.TbsLog.i(r11, r10, r5)     // Catch:{ all -> 0x0257 }
            r6 = 112(0x70, float:1.57E-43)
            r7 = 0
            r1.a((int) r6, (java.lang.String) r7, (boolean) r5)     // Catch:{ all -> 0x0257 }
            android.content.Context r5 = r1.h     // Catch:{ all -> 0x0257 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ all -> 0x0257 }
            r7 = -307(0xfffffffffffffecd, float:NaN)
            r5.setDownloadInterruptCode(r7)     // Catch:{ all -> 0x0257 }
            if (r2 != 0) goto L_0x069e
        L_0x0215:
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.mSyncMap
            java.lang.Long r3 = java.lang.Long.valueOf(r8)
            r2.put(r14, r3)
        L_0x0224:
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r2.commit()
            goto L_0x069e
        L_0x022f:
            r7 = -307(0xfffffffffffffecd, float:NaN)
        L_0x0231:
            android.content.Context r15 = r1.h     // Catch:{ all -> 0x0257 }
            boolean r15 = com.tencent.smtt.utils.FileUtil.b((android.content.Context) r15)     // Catch:{ all -> 0x0257 }
            if (r15 != 0) goto L_0x0264
            java.lang.String r5 = "DownloadBegin FreeSpace too small"
            r6 = 1
            com.tencent.smtt.utils.TbsLog.i(r11, r5, r6)     // Catch:{ all -> 0x0257 }
            r5 = 105(0x69, float:1.47E-43)
            r7 = 0
            r1.a((int) r5, (java.lang.String) r7, (boolean) r6)     // Catch:{ all -> 0x0257 }
            android.content.Context r5 = r1.h     // Catch:{ all -> 0x0257 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ all -> 0x0257 }
            r6 = -308(0xfffffffffffffecc, float:NaN)
            r5.setDownloadInterruptCode(r6)     // Catch:{ all -> 0x0257 }
            if (r2 != 0) goto L_0x069e
            goto L_0x0215
        L_0x0253:
            r0 = move-exception
            r8 = r23
            goto L_0x0258
        L_0x0257:
            r0 = move-exception
        L_0x0258:
            r5 = r0
            r23 = r3
        L_0x025b:
            r10 = r11
            r26 = r12
        L_0x025e:
            r11 = r4
            r4 = r14
            goto L_0x0b5f
        L_0x0262:
            r7 = -307(0xfffffffffffffecd, float:NaN)
        L_0x0264:
            r15 = 1
            r1.z = r15     // Catch:{ all -> 0x0b56 }
            java.lang.String r15 = r1.k     // Catch:{ all -> 0x0b56 }
            if (r15 == 0) goto L_0x026e
            java.lang.String r15 = r1.k     // Catch:{ all -> 0x0257 }
            goto L_0x0270
        L_0x026e:
            java.lang.String r15 = r1.i     // Catch:{ all -> 0x0b56 }
        L_0x0270:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0b56 }
            r7.<init>()     // Catch:{ all -> 0x0b56 }
            r23 = r3
            java.lang.String r3 = "try url:"
            r7.append(r3)     // Catch:{ all -> 0x0b54 }
            r7.append(r15)     // Catch:{ all -> 0x0b54 }
            java.lang.String r3 = ",mRetryTimes:"
            r7.append(r3)     // Catch:{ all -> 0x0b54 }
            int r3 = r1.q     // Catch:{ all -> 0x0b54 }
            r7.append(r3)     // Catch:{ all -> 0x0b54 }
            java.lang.String r3 = r7.toString()     // Catch:{ all -> 0x0b54 }
            r7 = 1
            com.tencent.smtt.utils.TbsLog.i(r11, r3, r7)     // Catch:{ all -> 0x0b54 }
            java.lang.String r3 = r1.j     // Catch:{ all -> 0x0b54 }
            boolean r3 = r15.equals(r3)     // Catch:{ all -> 0x0b54 }
            if (r3 != 0) goto L_0x02a2
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r3 = r1.w     // Catch:{ all -> 0x029f }
            r3.setDownloadUrl(r15)     // Catch:{ all -> 0x029f }
            goto L_0x02a2
        L_0x029f:
            r0 = move-exception
            r5 = r0
            goto L_0x025b
        L_0x02a2:
            r1.j = r15     // Catch:{ all -> 0x0b54 }
            r1.a((java.lang.String) r15)     // Catch:{ all -> 0x0b54 }
            boolean r3 = r1.p     // Catch:{ all -> 0x0b54 }
            java.lang.String r7 = "/"
            if (r3 != 0) goto L_0x0349
            r24 = r5
            long r5 = r39.l()     // Catch:{ all -> 0x029f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x029f }
            r3.<init>()     // Catch:{ all -> 0x029f }
            java.lang.String r15 = "[TbsApkDownloader.startDownload] range="
            r3.append(r15)     // Catch:{ all -> 0x029f }
            r3.append(r5)     // Catch:{ all -> 0x029f }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x029f }
            com.tencent.smtt.utils.TbsLog.i(r11, r3)     // Catch:{ all -> 0x029f }
            r26 = r12
            long r12 = r1.m     // Catch:{ all -> 0x0344 }
            java.lang.String r3 = "-"
            java.lang.String r15 = "bytes="
            r28 = r10
            java.lang.String r10 = "Range"
            r18 = 0
            int r29 = (r12 > r18 ? 1 : (r12 == r18 ? 0 : -1))
            if (r29 > 0) goto L_0x0308
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0344 }
            r12.<init>()     // Catch:{ all -> 0x0344 }
            java.lang.String r13 = "STEP 1/2 begin downloading...current"
            r12.append(r13)     // Catch:{ all -> 0x0344 }
            r12.append(r5)     // Catch:{ all -> 0x0344 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x0344 }
            r13 = 1
            com.tencent.smtt.utils.TbsLog.i(r11, r12, r13)     // Catch:{ all -> 0x0344 }
            java.net.HttpURLConnection r12 = r1.u     // Catch:{ all -> 0x0344 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x0344 }
            r13.<init>()     // Catch:{ all -> 0x0344 }
            r13.append(r15)     // Catch:{ all -> 0x0344 }
            r13.append(r5)     // Catch:{ all -> 0x0344 }
            r13.append(r3)     // Catch:{ all -> 0x0344 }
            java.lang.String r3 = r13.toString()     // Catch:{ all -> 0x0344 }
            r12.setRequestProperty(r10, r3)     // Catch:{ all -> 0x0344 }
            r29 = r14
            goto L_0x0353
        L_0x0308:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0344 }
            r12.<init>()     // Catch:{ all -> 0x0344 }
            java.lang.String r13 = "#1 STEP 1/2 begin downloading...current/total="
            r12.append(r13)     // Catch:{ all -> 0x0344 }
            r12.append(r5)     // Catch:{ all -> 0x0344 }
            r12.append(r7)     // Catch:{ all -> 0x0344 }
            r29 = r14
            long r13 = r1.m     // Catch:{ all -> 0x037b }
            r12.append(r13)     // Catch:{ all -> 0x037b }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x037b }
            r13 = 1
            com.tencent.smtt.utils.TbsLog.i(r11, r12, r13)     // Catch:{ all -> 0x037b }
            java.net.HttpURLConnection r12 = r1.u     // Catch:{ all -> 0x037b }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x037b }
            r13.<init>()     // Catch:{ all -> 0x037b }
            r13.append(r15)     // Catch:{ all -> 0x037b }
            r13.append(r5)     // Catch:{ all -> 0x037b }
            r13.append(r3)     // Catch:{ all -> 0x037b }
            long r14 = r1.m     // Catch:{ all -> 0x037b }
            r13.append(r14)     // Catch:{ all -> 0x037b }
            java.lang.String r3 = r13.toString()     // Catch:{ all -> 0x037b }
            r12.setRequestProperty(r10, r3)     // Catch:{ all -> 0x037b }
            goto L_0x0353
        L_0x0344:
            r0 = move-exception
            r5 = r0
            r10 = r11
            goto L_0x025e
        L_0x0349:
            r24 = r5
            r28 = r10
            r26 = r12
            r29 = r14
            r5 = 0
        L_0x0353:
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r3 = r1.w     // Catch:{ all -> 0x0b4e }
            r12 = 0
            int r10 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r10 != 0) goto L_0x035d
            r15 = 0
            goto L_0x035e
        L_0x035d:
            r15 = 1
        L_0x035e:
            r3.setDownloadCancel(r15)     // Catch:{ all -> 0x0b4e }
            android.content.Context r3 = r1.h     // Catch:{ all -> 0x0b4e }
            int r3 = com.tencent.smtt.utils.Apn.getApnType(r3)     // Catch:{ all -> 0x0b4e }
            android.content.Context r10 = r1.h     // Catch:{ all -> 0x0b4e }
            java.lang.String r10 = com.tencent.smtt.utils.Apn.getApnInfo(r10)     // Catch:{ all -> 0x0b4e }
            java.lang.String r12 = r1.x     // Catch:{ all -> 0x0b4e }
            if (r12 != 0) goto L_0x0383
            int r12 = r1.y     // Catch:{ all -> 0x037b }
            r13 = -1
            if (r12 != r13) goto L_0x0383
            r1.x = r10     // Catch:{ all -> 0x037b }
            r1.y = r3     // Catch:{ all -> 0x037b }
            goto L_0x0390
        L_0x037b:
            r0 = move-exception
            r5 = r0
            r10 = r11
            r11 = r4
            r4 = r29
            goto L_0x0b5f
        L_0x0383:
            int r12 = r1.y     // Catch:{ all -> 0x0b4e }
            if (r3 != r12) goto L_0x0393
            java.lang.String r12 = r1.x     // Catch:{ all -> 0x037b }
            boolean r12 = r10.equals(r12)     // Catch:{ all -> 0x037b }
            if (r12 != 0) goto L_0x0390
            goto L_0x0393
        L_0x0390:
            r12 = -307(0xfffffffffffffecd, float:NaN)
            goto L_0x039f
        L_0x0393:
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r12 = r1.w     // Catch:{ all -> 0x0b4e }
            r13 = 0
            r12.setNetworkChange(r13)     // Catch:{ all -> 0x0b4e }
            r12 = -307(0xfffffffffffffecd, float:NaN)
            r1.x = r10     // Catch:{ all -> 0x0b4e }
            r1.y = r3     // Catch:{ all -> 0x0b4e }
        L_0x039f:
            int r3 = r1.q     // Catch:{ all -> 0x0b4e }
            r10 = 1
            if (r3 < r10) goto L_0x03ad
            java.net.HttpURLConnection r3 = r1.u     // Catch:{ all -> 0x037b }
            java.lang.String r10 = "Referer"
            java.lang.String r14 = r1.i     // Catch:{ all -> 0x037b }
            r3.addRequestProperty(r10, r14)     // Catch:{ all -> 0x037b }
        L_0x03ad:
            java.net.HttpURLConnection r3 = r1.u     // Catch:{ all -> 0x0b4e }
            int r3 = r3.getResponseCode()     // Catch:{ all -> 0x0b4e }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0b4e }
            r10.<init>()     // Catch:{ all -> 0x0b4e }
            java.lang.String r14 = "[TbsApkDownloader.startDownload] responseCode="
            r10.append(r14)     // Catch:{ all -> 0x0b4e }
            r10.append(r3)     // Catch:{ all -> 0x0b4e }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0b4e }
            com.tencent.smtt.utils.TbsLog.i(r11, r10)     // Catch:{ all -> 0x0b4e }
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r10 = r1.w     // Catch:{ all -> 0x0b4e }
            r10.setHttpCode(r3)     // Catch:{ all -> 0x0b4e }
            if (r2 != 0) goto L_0x0401
            android.content.Context r10 = r1.h     // Catch:{ all -> 0x037b }
            boolean r10 = com.tencent.smtt.sdk.TbsDownloader.getOverSea(r10)     // Catch:{ all -> 0x037b }
            if (r10 != 0) goto L_0x0401
            android.content.Context r10 = r1.h     // Catch:{ all -> 0x037b }
            int r10 = com.tencent.smtt.utils.Apn.getApnType(r10)     // Catch:{ all -> 0x037b }
            r14 = 3
            if (r10 != r14) goto L_0x03e7
            android.content.Context r10 = r1.h     // Catch:{ all -> 0x037b }
            int r10 = com.tencent.smtt.utils.Apn.getApnType(r10)     // Catch:{ all -> 0x037b }
            if (r10 != 0) goto L_0x0401
        L_0x03e7:
            boolean r10 = com.tencent.smtt.sdk.QbSdk.getDownloadWithoutWifi()     // Catch:{ all -> 0x037b }
            if (r10 != 0) goto L_0x0401
            r39.b()     // Catch:{ all -> 0x037b }
            com.tencent.smtt.sdk.TbsListener r10 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ all -> 0x037b }
            if (r10 == 0) goto L_0x03fb
            com.tencent.smtt.sdk.TbsListener r10 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ all -> 0x037b }
            r14 = 111(0x6f, float:1.56E-43)
            r10.onDownloadFinish(r14)     // Catch:{ all -> 0x037b }
        L_0x03fb:
            java.lang.String r10 = "Download is canceled due to NOT_WIFI error!"
            r13 = 0
            com.tencent.smtt.utils.TbsLog.i(r11, r10, r13)     // Catch:{ all -> 0x037b }
        L_0x0401:
            boolean r13 = r1.s     // Catch:{ all -> 0x0b4e }
            if (r13 == 0) goto L_0x0425
            android.content.Context r3 = r1.h     // Catch:{ all -> 0x037b }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ all -> 0x037b }
            r5 = -309(0xfffffffffffffecb, float:NaN)
            r3.setDownloadInterruptCode(r5)     // Catch:{ all -> 0x037b }
            if (r2 != 0) goto L_0x069e
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.mSyncMap
            java.lang.Long r3 = java.lang.Long.valueOf(r8)
            r13 = r29
        L_0x0420:
            r2.put(r13, r3)
            goto L_0x0224
        L_0x0425:
            r13 = r29
            r14 = 200(0xc8, float:2.8E-43)
            if (r3 == r14) goto L_0x059a
            r14 = 206(0xce, float:2.89E-43)
            if (r3 != r14) goto L_0x0431
            goto L_0x059a
        L_0x0431:
            r5 = 300(0x12c, float:4.2E-43)
            if (r3 < r5) goto L_0x0469
            r5 = 307(0x133, float:4.3E-43)
            if (r3 > r5) goto L_0x0469
            java.net.HttpURLConnection r3 = r1.u     // Catch:{ all -> 0x0593 }
            java.lang.String r5 = "Location"
            java.lang.String r3 = r3.getHeaderField(r5)     // Catch:{ all -> 0x0593 }
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x0593 }
            if (r5 != 0) goto L_0x0453
            r1.k = r3     // Catch:{ all -> 0x0593 }
            int r3 = r1.r     // Catch:{ all -> 0x0593 }
            r5 = 1
            int r3 = r3 + r5
            r1.r = r3     // Catch:{ all -> 0x0593 }
            if (r2 != 0) goto L_0x0509
            goto L_0x04f1
        L_0x0453:
            r3 = 124(0x7c, float:1.74E-43)
            r5 = 0
            r6 = 1
            r1.a((int) r3, (java.lang.String) r5, (boolean) r6)     // Catch:{ all -> 0x0593 }
            android.content.Context r3 = r1.h     // Catch:{ all -> 0x0593 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ all -> 0x0593 }
            r5 = -312(0xfffffffffffffec8, float:NaN)
            r3.setDownloadInterruptCode(r5)     // Catch:{ all -> 0x0593 }
            if (r2 != 0) goto L_0x069e
            goto L_0x04dd
        L_0x0469:
            r5 = 102(0x66, float:1.43E-43)
            java.lang.String r6 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0593 }
            r7 = 0
            r1.a((int) r5, (java.lang.String) r6, (boolean) r7)     // Catch:{ all -> 0x0593 }
            r6 = 416(0x1a0, float:5.83E-43)
            if (r3 != r6) goto L_0x04c0
            r6 = 1
            boolean r3 = r1.c(r6, r4)     // Catch:{ all -> 0x0593 }
            if (r3 == 0) goto L_0x04ae
            android.content.Context r3 = r1.h     // Catch:{ all -> 0x04a7 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ all -> 0x04a7 }
            r6 = -214(0xffffffffffffff2a, float:NaN)
            r3.setDownloadInterruptCode(r6)     // Catch:{ all -> 0x04a7 }
            if (r2 != 0) goto L_0x04a3
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.mSyncMap
            java.lang.Long r3 = java.lang.Long.valueOf(r8)
            r2.put(r13, r3)
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r2.commit()
        L_0x04a3:
            r11 = r4
            r9 = 1
            goto L_0x0bd5
        L_0x04a7:
            r0 = move-exception
            r5 = r0
            r10 = r11
            r16 = 1
            goto L_0x0596
        L_0x04ae:
            r3 = 0
            r1.e(r3)     // Catch:{ all -> 0x0593 }
            android.content.Context r3 = r1.h     // Catch:{ all -> 0x0593 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ all -> 0x0593 }
            r5 = -313(0xfffffffffffffec7, float:NaN)
            r3.setDownloadInterruptCode(r5)     // Catch:{ all -> 0x0593 }
            if (r2 != 0) goto L_0x069e
            goto L_0x04dd
        L_0x04c0:
            r5 = 403(0x193, float:5.65E-43)
            if (r3 == r5) goto L_0x04c8
            r5 = 406(0x196, float:5.69E-43)
            if (r3 != r5) goto L_0x04eb
        L_0x04c8:
            long r5 = r1.m     // Catch:{ all -> 0x0593 }
            r14 = -1
            int r7 = (r5 > r14 ? 1 : (r5 == r14 ? 0 : -1))
            if (r7 != 0) goto L_0x04eb
            android.content.Context r3 = r1.h     // Catch:{ all -> 0x0593 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ all -> 0x0593 }
            r5 = -314(0xfffffffffffffec6, float:NaN)
            r3.setDownloadInterruptCode(r5)     // Catch:{ all -> 0x0593 }
            if (r2 != 0) goto L_0x069e
        L_0x04dd:
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.mSyncMap
            java.lang.Long r3 = java.lang.Long.valueOf(r8)
            goto L_0x0420
        L_0x04eb:
            r5 = 202(0xca, float:2.83E-43)
            if (r3 != r5) goto L_0x050c
            if (r2 != 0) goto L_0x0509
        L_0x04f1:
            android.content.Context r3 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)
            java.util.Map<java.lang.String, java.lang.Object> r3 = r3.mSyncMap
            java.lang.Long r5 = java.lang.Long.valueOf(r8)
            r3.put(r13, r5)
            android.content.Context r3 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)
            r3.commit()
        L_0x0509:
            r10 = r13
            goto L_0x063c
        L_0x050c:
            int r5 = r1.q     // Catch:{ all -> 0x0593 }
            int r6 = r1.C     // Catch:{ all -> 0x0593 }
            if (r5 >= r6) goto L_0x053a
            r5 = 503(0x1f7, float:7.05E-43)
            if (r3 != r5) goto L_0x053a
            java.net.HttpURLConnection r3 = r1.u     // Catch:{ all -> 0x0593 }
            java.lang.String r5 = "Retry-After"
            java.lang.String r3 = r3.getHeaderField(r5)     // Catch:{ all -> 0x0593 }
            long r5 = java.lang.Long.parseLong(r3)     // Catch:{ all -> 0x0593 }
            r1.a((long) r5)     // Catch:{ all -> 0x0593 }
            boolean r3 = r1.s     // Catch:{ all -> 0x0593 }
            if (r3 == 0) goto L_0x0537
            android.content.Context r3 = r1.h     // Catch:{ all -> 0x0593 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ all -> 0x0593 }
            r5 = -309(0xfffffffffffffecb, float:NaN)
            r3.setDownloadInterruptCode(r5)     // Catch:{ all -> 0x0593 }
            if (r2 != 0) goto L_0x069e
            goto L_0x04dd
        L_0x0537:
            if (r2 != 0) goto L_0x0509
            goto L_0x04f1
        L_0x053a:
            int r5 = r1.q     // Catch:{ all -> 0x0593 }
            int r6 = r1.C     // Catch:{ all -> 0x0593 }
            if (r5 >= r6) goto L_0x056b
            r5 = 408(0x198, float:5.72E-43)
            if (r3 == r5) goto L_0x0550
            r5 = 504(0x1f8, float:7.06E-43)
            if (r3 == r5) goto L_0x0550
            r5 = 502(0x1f6, float:7.03E-43)
            if (r3 == r5) goto L_0x0550
            r5 = 408(0x198, float:5.72E-43)
            if (r3 != r5) goto L_0x056b
        L_0x0550:
            r5 = 0
            r1.a((long) r5)     // Catch:{ all -> 0x0593 }
            boolean r3 = r1.s     // Catch:{ all -> 0x0593 }
            if (r3 == 0) goto L_0x0568
            android.content.Context r3 = r1.h     // Catch:{ all -> 0x0593 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ all -> 0x0593 }
            r5 = -309(0xfffffffffffffecb, float:NaN)
            r3.setDownloadInterruptCode(r5)     // Catch:{ all -> 0x0593 }
            if (r2 != 0) goto L_0x069e
            goto L_0x04dd
        L_0x0568:
            if (r2 != 0) goto L_0x0509
            goto L_0x04f1
        L_0x056b:
            long r5 = r39.l()     // Catch:{ all -> 0x0593 }
            r14 = 0
            int r7 = (r5 > r14 ? 1 : (r5 == r14 ? 0 : -1))
            if (r7 > 0) goto L_0x0584
            boolean r5 = r1.p     // Catch:{ all -> 0x0593 }
            if (r5 != 0) goto L_0x0584
            r5 = 410(0x19a, float:5.75E-43)
            if (r3 == r5) goto L_0x0584
            r3 = 1
            r1.p = r3     // Catch:{ all -> 0x0593 }
            if (r2 != 0) goto L_0x0509
            goto L_0x04f1
        L_0x0584:
            android.content.Context r3 = r1.h     // Catch:{ all -> 0x0593 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ all -> 0x0593 }
            r5 = -315(0xfffffffffffffec5, float:NaN)
            r3.setDownloadInterruptCode(r5)     // Catch:{ all -> 0x0593 }
            if (r2 != 0) goto L_0x069e
            goto L_0x04dd
        L_0x0593:
            r0 = move-exception
            r5 = r0
            r10 = r11
        L_0x0596:
            r11 = r4
            r4 = r13
            goto L_0x0b5f
        L_0x059a:
            java.net.HttpURLConnection r3 = r1.u     // Catch:{ all -> 0x0b49 }
            int r3 = r3.getContentLength()     // Catch:{ all -> 0x0b49 }
            long r14 = (long) r3     // Catch:{ all -> 0x0b49 }
            long r14 = r14 + r5
            r1.m = r14     // Catch:{ all -> 0x0b49 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0b49 }
            r3.<init>()     // Catch:{ all -> 0x0b49 }
            java.lang.String r14 = "[TbsApkDownloader.startDownload] mContentLength="
            r3.append(r14)     // Catch:{ all -> 0x0b49 }
            long r14 = r1.m     // Catch:{ all -> 0x0b49 }
            r3.append(r14)     // Catch:{ all -> 0x0b49 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0b49 }
            com.tencent.smtt.utils.TbsLog.i(r11, r3)     // Catch:{ all -> 0x0b49 }
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r3 = r1.w     // Catch:{ all -> 0x0b49 }
            long r14 = r1.m     // Catch:{ all -> 0x0b49 }
            r3.setPkgSize(r14)     // Catch:{ all -> 0x0b49 }
            android.content.Context r3 = r1.h     // Catch:{ all -> 0x0b49 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ all -> 0x0b49 }
            android.content.SharedPreferences r3 = r3.mPreferences     // Catch:{ all -> 0x0b49 }
            java.lang.String r14 = "tbs_apkfilesize"
            r29 = r13
            r12 = 0
            long r14 = r3.getLong(r14, r12)     // Catch:{ all -> 0x0b4e }
            int r3 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r3 == 0) goto L_0x06ad
            long r12 = r1.m     // Catch:{ all -> 0x037b }
            int r3 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r3 == 0) goto L_0x06ad
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x037b }
            r3.<init>()     // Catch:{ all -> 0x037b }
            java.lang.String r5 = "DownloadBegin tbsApkFileSize="
            r3.append(r5)     // Catch:{ all -> 0x037b }
            r3.append(r14)     // Catch:{ all -> 0x037b }
            java.lang.String r5 = "  but contentLength="
            r3.append(r5)     // Catch:{ all -> 0x037b }
            long r5 = r1.m     // Catch:{ all -> 0x037b }
            r3.append(r5)     // Catch:{ all -> 0x037b }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x037b }
            r5 = 1
            com.tencent.smtt.utils.TbsLog.i(r11, r3, r5)     // Catch:{ all -> 0x037b }
            if (r2 != 0) goto L_0x0676
            boolean r3 = r39.o()     // Catch:{ all -> 0x037b }
            if (r3 != 0) goto L_0x0612
            boolean r3 = com.tencent.smtt.sdk.QbSdk.getDownloadWithoutWifi()     // Catch:{ all -> 0x037b }
            if (r3 == 0) goto L_0x0676
            android.content.Context r3 = r1.h     // Catch:{ all -> 0x037b }
            boolean r3 = com.tencent.smtt.utils.Apn.isNetworkAvailable(r3)     // Catch:{ all -> 0x037b }
            if (r3 == 0) goto L_0x0676
        L_0x0612:
            java.lang.String[] r3 = r1.b     // Catch:{ all -> 0x037b }
            if (r3 == 0) goto L_0x0647
            r3 = 0
            boolean r5 = r1.b((boolean) r3)     // Catch:{ all -> 0x037b }
            if (r5 == 0) goto L_0x0647
            if (r2 != 0) goto L_0x063a
            android.content.Context r5 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)
            java.util.Map<java.lang.String, java.lang.Object> r5 = r5.mSyncMap
            java.lang.Long r6 = java.lang.Long.valueOf(r8)
            r10 = r29
            r5.put(r10, r6)
            android.content.Context r5 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)
            r5.commit()
            goto L_0x063c
        L_0x063a:
            r10 = r29
        L_0x063c:
            r14 = r10
            r3 = r23
            r12 = r26
            r5 = 0
            r7 = 1
        L_0x0644:
            r10 = 0
            goto L_0x0165
        L_0x0647:
            r10 = r29
            r5 = 113(0x71, float:1.58E-43)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x06a3 }
            r6.<init>()     // Catch:{ all -> 0x06a3 }
            java.lang.String r7 = "tbsApkFileSize="
            r6.append(r7)     // Catch:{ all -> 0x06a3 }
            r6.append(r14)     // Catch:{ all -> 0x06a3 }
            java.lang.String r7 = "  but contentLength="
            r6.append(r7)     // Catch:{ all -> 0x06a3 }
            long r12 = r1.m     // Catch:{ all -> 0x06a3 }
            r6.append(r12)     // Catch:{ all -> 0x06a3 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x06a3 }
            r7 = 1
            r1.a((int) r5, (java.lang.String) r6, (boolean) r7)     // Catch:{ all -> 0x06a3 }
            android.content.Context r5 = r1.h     // Catch:{ all -> 0x06a3 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ all -> 0x06a3 }
            r6 = -310(0xfffffffffffffeca, float:NaN)
            r5.setDownloadInterruptCode(r6)     // Catch:{ all -> 0x06a3 }
            goto L_0x068b
        L_0x0676:
            r10 = r29
            r5 = 101(0x65, float:1.42E-43)
            java.lang.String r6 = "WifiNetworkUnAvailable"
            r7 = 1
            r1.a((int) r5, (java.lang.String) r6, (boolean) r7)     // Catch:{ all -> 0x06a3 }
            android.content.Context r5 = r1.h     // Catch:{ all -> 0x06a3 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ all -> 0x06a3 }
            r6 = -304(0xfffffffffffffed0, float:NaN)
            r5.setDownloadInterruptCode(r6)     // Catch:{ all -> 0x06a3 }
        L_0x068b:
            if (r2 != 0) goto L_0x069e
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.mSyncMap
            java.lang.Long r5 = java.lang.Long.valueOf(r8)
            r2.put(r10, r5)
            goto L_0x0224
        L_0x069e:
            r11 = r4
        L_0x069f:
            r9 = r16
            goto L_0x0bd5
        L_0x06a3:
            r0 = move-exception
            r5 = r0
            r38 = r11
            r11 = r4
            r4 = r10
            r10 = r38
            goto L_0x0b5f
        L_0x06ad:
            r10 = r29
            java.lang.String r12 = "[TbsApkDownloader.startDownload] begin readResponse"
            com.tencent.smtt.utils.TbsLog.i(r11, r12)     // Catch:{ all -> 0x0b41 }
            java.net.HttpURLConnection r12 = r1.u     // Catch:{ IOException -> 0x0a2b, all -> 0x0a1e }
            java.io.InputStream r12 = r12.getInputStream()     // Catch:{ IOException -> 0x0a2b, all -> 0x0a1e }
            if (r12 == 0) goto L_0x09e0
            java.net.HttpURLConnection r13 = r1.u     // Catch:{ IOException -> 0x09d5, all -> 0x09cb }
            java.lang.String r13 = r13.getContentEncoding()     // Catch:{ IOException -> 0x09d5, all -> 0x09cb }
            if (r13 == 0) goto L_0x06ec
            java.lang.String r14 = "gzip"
            boolean r14 = r13.contains(r14)     // Catch:{ IOException -> 0x06de, all -> 0x06d2 }
            if (r14 == 0) goto L_0x06ec
            java.util.zip.GZIPInputStream r13 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x06de, all -> 0x06d2 }
            r13.<init>(r12)     // Catch:{ IOException -> 0x06de, all -> 0x06d2 }
            goto L_0x0703
        L_0x06d2:
            r0 = move-exception
            r5 = r0
            r7 = 0
            r13 = 0
        L_0x06d6:
            r38 = r11
            r11 = r4
            r4 = r10
            r10 = r38
            goto L_0x0b35
        L_0x06de:
            r0 = move-exception
            r5 = r0
            r3 = -304(0xfffffffffffffed0, float:NaN)
            r13 = 0
            r15 = 0
        L_0x06e4:
            r38 = r11
            r11 = r4
            r4 = r10
            r10 = r38
            goto L_0x0a38
        L_0x06ec:
            if (r13 == 0) goto L_0x0702
            java.lang.String r14 = "deflate"
            boolean r13 = r13.contains(r14)     // Catch:{ IOException -> 0x06de, all -> 0x06d2 }
            if (r13 == 0) goto L_0x0702
            java.util.zip.InflaterInputStream r13 = new java.util.zip.InflaterInputStream     // Catch:{ IOException -> 0x06de, all -> 0x06d2 }
            java.util.zip.Inflater r14 = new java.util.zip.Inflater     // Catch:{ IOException -> 0x06de, all -> 0x06d2 }
            r15 = 1
            r14.<init>(r15)     // Catch:{ IOException -> 0x06de, all -> 0x06d2 }
            r13.<init>(r12, r14)     // Catch:{ IOException -> 0x06de, all -> 0x06d2 }
            goto L_0x0703
        L_0x0702:
            r13 = r12
        L_0x0703:
            r14 = 8192(0x2000, float:1.14794E-41)
            byte[] r14 = new byte[r14]     // Catch:{ IOException -> 0x09bf, all -> 0x09b4 }
            java.io.FileOutputStream r15 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x09bf, all -> 0x09b4 }
            java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x09bf, all -> 0x09b4 }
            r29 = r5
            java.io.File r5 = r1.l     // Catch:{ IOException -> 0x09bf, all -> 0x09b4 }
            java.lang.String r6 = "x5.tbs.temp"
            r3.<init>(r5, r6)     // Catch:{ IOException -> 0x09bf, all -> 0x09b4 }
            r5 = 1
            r15.<init>(r3, r5)     // Catch:{ IOException -> 0x09bf, all -> 0x09b4 }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x09a8, all -> 0x099f }
            r31 = r8
            r8 = r24
            r24 = r29
        L_0x0722:
            boolean r3 = r1.s     // Catch:{ IOException -> 0x0993, all -> 0x0988 }
            if (r3 == 0) goto L_0x074d
            java.lang.String r3 = "STEP 1/2 begin downloading...Canceled!"
            r5 = 1
            com.tencent.smtt.utils.TbsLog.i(r11, r3, r5)     // Catch:{ IOException -> 0x0746, all -> 0x0740 }
            android.content.Context r3 = r1.h     // Catch:{ IOException -> 0x0746, all -> 0x0740 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ IOException -> 0x0746, all -> 0x0740 }
            r5 = -309(0xfffffffffffffecb, float:NaN)
            r3.setDownloadInterruptCode(r5)     // Catch:{ IOException -> 0x0746, all -> 0x0740 }
            r33 = r10
        L_0x0739:
            r10 = r11
            r5 = 0
        L_0x073b:
            r8 = -304(0xfffffffffffffed0, float:NaN)
        L_0x073d:
            r11 = r4
            goto L_0x0906
        L_0x0740:
            r0 = move-exception
            r5 = r0
            r7 = r15
            r8 = r31
            goto L_0x06d6
        L_0x0746:
            r0 = move-exception
            r5 = r0
            r8 = r31
            r3 = -304(0xfffffffffffffed0, float:NaN)
            goto L_0x06e4
        L_0x074d:
            r3 = 8192(0x2000, float:1.14794E-41)
            r33 = r10
            r10 = 0
            int r3 = r13.read(r14, r10, r3)     // Catch:{ IOException -> 0x097f, all -> 0x0974 }
            if (r3 > 0) goto L_0x07a2
            java.lang.String[] r3 = r1.b     // Catch:{ IOException -> 0x0796, all -> 0x078d }
            if (r3 == 0) goto L_0x0778
            r3 = 1
            boolean r5 = r1.c(r3, r4)     // Catch:{ IOException -> 0x0796, all -> 0x078d }
            if (r5 != 0) goto L_0x0778
            if (r2 != 0) goto L_0x076e
            boolean r3 = r1.b((boolean) r10)     // Catch:{ IOException -> 0x0796, all -> 0x078d }
            if (r3 == 0) goto L_0x076e
            r10 = r11
            r5 = 1
            goto L_0x073b
        L_0x076e:
            r3 = 1
            r1.t = r3     // Catch:{ IOException -> 0x0796, all -> 0x078d }
            r10 = r11
            r5 = 0
            r8 = -304(0xfffffffffffffed0, float:NaN)
            r16 = 0
            goto L_0x073d
        L_0x0778:
            r3 = 1
            r1.t = r3     // Catch:{ IOException -> 0x0796, all -> 0x078d }
            java.lang.String[] r3 = r1.b     // Catch:{ IOException -> 0x0796, all -> 0x078d }
            if (r3 == 0) goto L_0x0781
            r16 = 1
        L_0x0781:
            android.content.Context r3 = r1.h     // Catch:{ IOException -> 0x0796, all -> 0x078d }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ IOException -> 0x0796, all -> 0x078d }
            r6 = -311(0xfffffffffffffec9, float:NaN)
            r3.setDownloadInterruptCode(r6)     // Catch:{ IOException -> 0x0796, all -> 0x078d }
            goto L_0x0739
        L_0x078d:
            r0 = move-exception
            r5 = r0
            r10 = r11
            r7 = r15
            r8 = r31
            r11 = r4
            goto L_0x097b
        L_0x0796:
            r0 = move-exception
            r5 = r0
            r10 = r11
            r8 = r31
            r3 = -304(0xfffffffffffffed0, float:NaN)
            r11 = r4
            r4 = r33
            goto L_0x0a38
        L_0x07a2:
            r15.write(r14, r10, r3)     // Catch:{ IOException -> 0x097f, all -> 0x0974 }
            r15.flush()     // Catch:{ IOException -> 0x097f, all -> 0x0974 }
            if (r2 != 0) goto L_0x086a
            r17 = r11
            long r10 = (long) r3
            long r10 = r31 + r10
            int r31 = (r10 > r26 ? 1 : (r10 == r26 ? 0 : -1))
            if (r31 < 0) goto L_0x080a
            r5 = r17
            r3 = r28
            r6 = 1
            com.tencent.smtt.utils.TbsLog.i(r5, r3, r6)     // Catch:{ IOException -> 0x0800, all -> 0x07f6 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0800, all -> 0x07f6 }
            r3.<init>()     // Catch:{ IOException -> 0x0800, all -> 0x07f6 }
            java.lang.String r6 = "downloadFlow="
            r3.append(r6)     // Catch:{ IOException -> 0x0800, all -> 0x07f6 }
            r3.append(r10)     // Catch:{ IOException -> 0x0800, all -> 0x07f6 }
            java.lang.String r6 = " downloadMaxflow="
            r3.append(r6)     // Catch:{ IOException -> 0x0800, all -> 0x07f6 }
            r6 = r26
            r3.append(r6)     // Catch:{ IOException -> 0x07f2, all -> 0x07ee }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x07f2, all -> 0x07ee }
            r8 = 112(0x70, float:1.57E-43)
            r9 = 1
            r1.a((int) r8, (java.lang.String) r3, (boolean) r9)     // Catch:{ IOException -> 0x07f2, all -> 0x07ee }
            android.content.Context r3 = r1.h     // Catch:{ IOException -> 0x07f2, all -> 0x07ee }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ IOException -> 0x07f2, all -> 0x07ee }
            r8 = -307(0xfffffffffffffecd, float:NaN)
            r3.setDownloadInterruptCode(r8)     // Catch:{ IOException -> 0x07f2, all -> 0x07ee }
            r26 = r6
            r31 = r10
            r10 = r5
            goto L_0x085a
        L_0x07ee:
            r0 = move-exception
            r26 = r6
            goto L_0x07f7
        L_0x07f2:
            r0 = move-exception
            r26 = r6
            goto L_0x0801
        L_0x07f6:
            r0 = move-exception
        L_0x07f7:
            r8 = r10
            r7 = r15
            r11 = r4
            r10 = r5
            r4 = r33
            r5 = r0
            goto L_0x0b35
        L_0x0800:
            r0 = move-exception
        L_0x0801:
            r8 = r10
            r3 = -304(0xfffffffffffffed0, float:NaN)
            r11 = r4
            r10 = r5
            r4 = r33
            goto L_0x09b1
        L_0x080a:
            r31 = r10
            r10 = r17
            r21 = r28
            r17 = -307(0xfffffffffffffecd, float:NaN)
            r20 = 112(0x70, float:1.57E-43)
            android.content.Context r11 = r1.h     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
            boolean r11 = com.tencent.smtt.utils.FileUtil.b((android.content.Context) r11)     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
            if (r11 != 0) goto L_0x0871
            java.lang.String r3 = "DownloadEnd FreeSpace too small "
            r5 = 1
            com.tencent.smtt.utils.TbsLog.i(r10, r3, r5)     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
            r3.<init>()     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
            java.lang.String r5 = "freespace="
            r3.append(r5)     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
            long r5 = com.tencent.smtt.utils.p.a()     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
            r3.append(r5)     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
            java.lang.String r5 = ",and minFreeSpace="
            r3.append(r5)     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
            android.content.Context r5 = r1.h     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
            long r5 = r5.getDownloadMinFreeSpace()     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
            r3.append(r5)     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
            r5 = 105(0x69, float:1.47E-43)
            r6 = 1
            r1.a((int) r5, (java.lang.String) r3, (boolean) r6)     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
            android.content.Context r3 = r1.h     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
            r5 = -308(0xfffffffffffffecc, float:NaN)
            r3.setDownloadInterruptCode(r5)     // Catch:{ IOException -> 0x0865, all -> 0x0860 }
        L_0x085a:
            r11 = r4
            r5 = 0
            r8 = -304(0xfffffffffffffed0, float:NaN)
            goto L_0x0906
        L_0x0860:
            r0 = move-exception
            r5 = r0
            r11 = r4
            goto L_0x0978
        L_0x0865:
            r0 = move-exception
            r5 = r0
            r11 = r4
            goto L_0x0983
        L_0x086a:
            r10 = r11
            r21 = r28
            r17 = -307(0xfffffffffffffecd, float:NaN)
            r20 = 112(0x70, float:1.57E-43)
        L_0x0871:
            r11 = r4
            long r3 = (long) r3
            long r8 = r1.a((long) r8, (long) r3)     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            long r34 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            long r3 = r29 + r3
            long r28 = r34 - r5
            r36 = 1000(0x3e8, double:4.94E-321)
            int r30 = (r28 > r36 ? 1 : (r28 == r36 ? 0 : -1))
            if (r30 <= 0) goto L_0x0958
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            r5.<init>()     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            java.lang.String r6 = "#2 STEP 1/2 begin downloading...current/total="
            r5.append(r6)     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            r5.append(r3)     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            r5.append(r7)     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            r28 = r7
            long r6 = r1.m     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            r5.append(r6)     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            r6 = 1
            com.tencent.smtt.utils.TbsLog.i(r10, r5, r6)     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            com.tencent.smtt.sdk.TbsListener r5 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            if (r5 == 0) goto L_0x08ba
            double r5 = (double) r3     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            r29 = r8
            long r7 = r1.m     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            double r7 = (double) r7     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            double r5 = r5 / r7
            r7 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r5 = r5 * r7
            int r5 = (int) r5     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            com.tencent.smtt.sdk.TbsListener r6 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            r6.onDownloadProgress(r5)     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            goto L_0x08bc
        L_0x08ba:
            r29 = r8
        L_0x08bc:
            if (r2 != 0) goto L_0x0951
            long r5 = r3 - r24
            r7 = 1048576(0x100000, double:5.180654E-318)
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L_0x0951
            android.content.Context r5 = r1.h     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            boolean r5 = com.tencent.smtt.sdk.TbsDownloader.getOverSea(r5)     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            if (r5 != 0) goto L_0x094a
            android.content.Context r5 = r1.h     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            int r5 = com.tencent.smtt.utils.Apn.getApnType(r5)     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            r6 = 3
            if (r5 != r6) goto L_0x08e0
            android.content.Context r5 = r1.h     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            int r5 = com.tencent.smtt.utils.Apn.getApnType(r5)     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            if (r5 != 0) goto L_0x094a
        L_0x08e0:
            boolean r5 = com.tencent.smtt.sdk.QbSdk.getDownloadWithoutWifi()     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            if (r5 != 0) goto L_0x094a
            r39.b()     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            com.tencent.smtt.sdk.TbsListener r3 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            if (r3 == 0) goto L_0x08f4
            com.tencent.smtt.sdk.TbsListener r3 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            r4 = 111(0x6f, float:1.56E-43)
            r3.onDownloadFinish(r4)     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
        L_0x08f4:
            java.lang.String r3 = "Download is paused due to NOT_WIFI error!"
            r4 = 0
            com.tencent.smtt.utils.TbsLog.i(r10, r3, r4)     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            android.content.Context r3 = r1.h     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            r8 = -304(0xfffffffffffffed0, float:NaN)
            r3.setDownloadInterruptCode(r8)     // Catch:{ IOException -> 0x0972, all -> 0x0970 }
            r5 = 0
        L_0x0906:
            if (r5 == 0) goto L_0x0942
            r1.a((java.io.Closeable) r15)     // Catch:{ all -> 0x093a }
            r1.a((java.io.Closeable) r13)     // Catch:{ all -> 0x093a }
            r1.a((java.io.Closeable) r12)     // Catch:{ all -> 0x093a }
            if (r2 != 0) goto L_0x092e
            android.content.Context r3 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)
            java.util.Map<java.lang.String, java.lang.Object> r3 = r3.mSyncMap
            java.lang.Long r4 = java.lang.Long.valueOf(r31)
            r9 = r33
            r3.put(r9, r4)
            android.content.Context r3 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)
            r3.commit()
            goto L_0x0930
        L_0x092e:
            r9 = r33
        L_0x0930:
            r14 = r9
            r4 = r11
            r3 = r23
            r12 = r26
            r8 = r31
            goto L_0x0b1f
        L_0x093a:
            r0 = move-exception
            r5 = r0
            r8 = r31
            r4 = r33
            goto L_0x0b5f
        L_0x0942:
            r8 = r31
            r4 = r33
            r3 = -304(0xfffffffffffffed0, float:NaN)
            goto L_0x09ea
        L_0x094a:
            r9 = r33
            r8 = -304(0xfffffffffffffed0, float:NaN)
            r24 = r3
            goto L_0x0955
        L_0x0951:
            r9 = r33
            r8 = -304(0xfffffffffffffed0, float:NaN)
        L_0x0955:
            r5 = r34
            goto L_0x0960
        L_0x0958:
            r28 = r7
            r29 = r8
            r9 = r33
            r8 = -304(0xfffffffffffffed0, float:NaN)
        L_0x0960:
            r7 = r28
            r28 = r21
            r38 = r10
            r10 = r9
            r8 = r29
            r29 = r3
            r4 = r11
            r11 = r38
            goto L_0x0722
        L_0x0970:
            r0 = move-exception
            goto L_0x0977
        L_0x0972:
            r0 = move-exception
            goto L_0x0982
        L_0x0974:
            r0 = move-exception
            r10 = r11
            r11 = r4
        L_0x0977:
            r5 = r0
        L_0x0978:
            r7 = r15
            r8 = r31
        L_0x097b:
            r4 = r33
            goto L_0x0b35
        L_0x097f:
            r0 = move-exception
            r10 = r11
            r11 = r4
        L_0x0982:
            r5 = r0
        L_0x0983:
            r8 = r31
            r4 = r33
            goto L_0x099b
        L_0x0988:
            r0 = move-exception
            r9 = r10
            r10 = r11
            r11 = r4
            r5 = r0
            r4 = r9
            r7 = r15
            r8 = r31
            goto L_0x0b35
        L_0x0993:
            r0 = move-exception
            r9 = r10
            r10 = r11
            r11 = r4
            r5 = r0
            r4 = r9
            r8 = r31
        L_0x099b:
            r3 = -304(0xfffffffffffffed0, float:NaN)
            goto L_0x0a38
        L_0x099f:
            r0 = move-exception
            r38 = r11
            r11 = r4
            r4 = r10
            r10 = r38
            goto L_0x0b33
        L_0x09a8:
            r0 = move-exception
            r3 = -304(0xfffffffffffffed0, float:NaN)
            r38 = r11
            r11 = r4
            r4 = r10
            r10 = r38
        L_0x09b1:
            r5 = r0
            goto L_0x0a38
        L_0x09b4:
            r0 = move-exception
            r38 = r11
            r11 = r4
            r4 = r10
            r10 = r38
            r5 = r0
            r7 = 0
            goto L_0x0b35
        L_0x09bf:
            r0 = move-exception
            r3 = -304(0xfffffffffffffed0, float:NaN)
            r38 = r11
            r11 = r4
            r4 = r10
            r10 = r38
            r5 = r0
            goto L_0x0a37
        L_0x09cb:
            r0 = move-exception
            r38 = r11
            r11 = r4
            r4 = r10
            r10 = r38
            r5 = r0
            r7 = 0
            goto L_0x0a28
        L_0x09d5:
            r0 = move-exception
            r3 = -304(0xfffffffffffffed0, float:NaN)
            r38 = r11
            r11 = r4
            r4 = r10
            r10 = r38
            r5 = r0
            goto L_0x0a36
        L_0x09e0:
            r3 = -304(0xfffffffffffffed0, float:NaN)
            r38 = r11
            r11 = r4
            r4 = r10
            r10 = r38
            r13 = 0
            r15 = 0
        L_0x09ea:
            r1.a((java.io.Closeable) r15)     // Catch:{ all -> 0x0b3f }
            r1.a((java.io.Closeable) r13)     // Catch:{ all -> 0x0b3f }
            r1.a((java.io.Closeable) r12)     // Catch:{ all -> 0x0b3f }
            boolean r5 = r1.t     // Catch:{ all -> 0x0b3f }
            if (r5 != 0) goto L_0x0a02
            android.content.Context r5 = r1.h     // Catch:{ all -> 0x0b3f }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ all -> 0x0b3f }
            r6 = -319(0xfffffffffffffec1, float:NaN)
            r5.setDownloadInterruptCode(r6)     // Catch:{ all -> 0x0b3f }
        L_0x0a02:
            if (r2 != 0) goto L_0x069f
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.mSyncMap
            java.lang.Long r3 = java.lang.Long.valueOf(r8)
        L_0x0a10:
            r2.put(r4, r3)
        L_0x0a13:
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r2.commit()
            goto L_0x069f
        L_0x0a1e:
            r0 = move-exception
            r38 = r11
            r11 = r4
            r4 = r10
            r10 = r38
            r5 = r0
            r7 = 0
            r12 = 0
        L_0x0a28:
            r13 = 0
            goto L_0x0b35
        L_0x0a2b:
            r0 = move-exception
            r3 = -304(0xfffffffffffffed0, float:NaN)
            r38 = r11
            r11 = r4
            r4 = r10
            r10 = r38
            r5 = r0
            r12 = 0
        L_0x0a36:
            r13 = 0
        L_0x0a37:
            r15 = 0
        L_0x0a38:
            r5.printStackTrace()     // Catch:{ all -> 0x0b30 }
            boolean r6 = r5 instanceof java.net.SocketTimeoutException     // Catch:{ all -> 0x0b30 }
            if (r6 != 0) goto L_0x0ade
            boolean r6 = r5 instanceof java.net.SocketException     // Catch:{ all -> 0x0b30 }
            if (r6 == 0) goto L_0x0a45
            goto L_0x0ade
        L_0x0a45:
            if (r2 != 0) goto L_0x0aaf
            android.content.Context r6 = r1.h     // Catch:{ all -> 0x0aac }
            boolean r6 = com.tencent.smtt.utils.FileUtil.b((android.content.Context) r6)     // Catch:{ all -> 0x0aac }
            if (r6 != 0) goto L_0x0aaf
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0aac }
            r5.<init>()     // Catch:{ all -> 0x0aac }
            java.lang.String r6 = "freespace="
            r5.append(r6)     // Catch:{ all -> 0x0aac }
            r29 = r4
            long r3 = com.tencent.smtt.utils.p.a()     // Catch:{ all -> 0x0aa7 }
            r5.append(r3)     // Catch:{ all -> 0x0aa7 }
            java.lang.String r3 = ",and minFreeSpace="
            r5.append(r3)     // Catch:{ all -> 0x0aa7 }
            android.content.Context r3 = r1.h     // Catch:{ all -> 0x0aa7 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ all -> 0x0aa7 }
            long r3 = r3.getDownloadMinFreeSpace()     // Catch:{ all -> 0x0aa7 }
            r5.append(r3)     // Catch:{ all -> 0x0aa7 }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x0aa7 }
            r4 = 105(0x69, float:1.47E-43)
            r5 = 1
            r1.a((int) r4, (java.lang.String) r3, (boolean) r5)     // Catch:{ all -> 0x0aa7 }
            android.content.Context r3 = r1.h     // Catch:{ all -> 0x0aa7 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)     // Catch:{ all -> 0x0aa7 }
            r4 = -308(0xfffffffffffffecc, float:NaN)
            r3.setDownloadInterruptCode(r4)     // Catch:{ all -> 0x0aa7 }
            r1.a((java.io.Closeable) r15)     // Catch:{ all -> 0x0aa4 }
            r1.a((java.io.Closeable) r13)     // Catch:{ all -> 0x0aa4 }
            r1.a((java.io.Closeable) r12)     // Catch:{ all -> 0x0aa4 }
            if (r2 != 0) goto L_0x069f
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.mSyncMap
            java.lang.Long r3 = java.lang.Long.valueOf(r8)
            r4 = r29
            goto L_0x0a10
        L_0x0aa4:
            r0 = move-exception
            goto L_0x0b51
        L_0x0aa7:
            r0 = move-exception
            r4 = r29
            goto L_0x0b33
        L_0x0aac:
            r0 = move-exception
            goto L_0x0b33
        L_0x0aaf:
            r21 = r8
            r7 = 0
            r1.a((long) r7)     // Catch:{ all -> 0x0b2a }
            boolean r6 = r39.k()     // Catch:{ all -> 0x0b2a }
            if (r6 != 0) goto L_0x0aca
            r6 = 106(0x6a, float:1.49E-43)
            java.lang.String r5 = r1.a((java.lang.Throwable) r5)     // Catch:{ all -> 0x0ac7 }
            r3 = 0
        L_0x0ac3:
            r1.a((int) r6, (java.lang.String) r5, (boolean) r3)     // Catch:{ all -> 0x0b2a }
            goto L_0x0ad2
        L_0x0ac7:
            r0 = move-exception
            r3 = 0
            goto L_0x0b2b
        L_0x0aca:
            r3 = 0
            r6 = 104(0x68, float:1.46E-43)
            java.lang.String r5 = r1.a((java.lang.Throwable) r5)     // Catch:{ all -> 0x0b2a }
            goto L_0x0ac3
        L_0x0ad2:
            r1.a((java.io.Closeable) r15)     // Catch:{ all -> 0x0b25 }
            r1.a((java.io.Closeable) r13)     // Catch:{ all -> 0x0b25 }
            r1.a((java.io.Closeable) r12)     // Catch:{ all -> 0x0b25 }
            if (r2 != 0) goto L_0x0b17
            goto L_0x0aff
        L_0x0ade:
            r21 = r8
            r6 = 100000(0x186a0, float:1.4013E-40)
            r1.n = r6     // Catch:{ all -> 0x0b2a }
            r6 = 0
            r1.a((long) r6)     // Catch:{ all -> 0x0b2a }
            r6 = 103(0x67, float:1.44E-43)
            java.lang.String r5 = r1.a((java.lang.Throwable) r5)     // Catch:{ all -> 0x0b2a }
            r3 = 0
            r1.a((int) r6, (java.lang.String) r5, (boolean) r3)     // Catch:{ all -> 0x0b2a }
            r1.a((java.io.Closeable) r15)     // Catch:{ all -> 0x0b25 }
            r1.a((java.io.Closeable) r13)     // Catch:{ all -> 0x0b25 }
            r1.a((java.io.Closeable) r12)     // Catch:{ all -> 0x0b25 }
            if (r2 != 0) goto L_0x0b17
        L_0x0aff:
            android.content.Context r5 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)
            java.util.Map<java.lang.String, java.lang.Object> r5 = r5.mSyncMap
            java.lang.Long r6 = java.lang.Long.valueOf(r21)
            r5.put(r4, r6)
            android.content.Context r5 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)
            r5.commit()
        L_0x0b17:
            r14 = r4
            r4 = r11
            r8 = r21
            r3 = r23
            r12 = r26
        L_0x0b1f:
            r5 = 0
            r7 = 1
            r11 = r10
            goto L_0x0644
        L_0x0b25:
            r0 = move-exception
            r5 = r0
            r8 = r21
            goto L_0x0b5f
        L_0x0b2a:
            r0 = move-exception
        L_0x0b2b:
            r5 = r0
            r7 = r15
            r8 = r21
            goto L_0x0b35
        L_0x0b30:
            r0 = move-exception
            r21 = r8
        L_0x0b33:
            r5 = r0
            r7 = r15
        L_0x0b35:
            r1.a((java.io.Closeable) r7)     // Catch:{ all -> 0x0b3f }
            r1.a((java.io.Closeable) r13)     // Catch:{ all -> 0x0b3f }
            r1.a((java.io.Closeable) r12)     // Catch:{ all -> 0x0b3f }
            throw r5     // Catch:{ all -> 0x0b3f }
        L_0x0b3f:
            r0 = move-exception
            goto L_0x0b5e
        L_0x0b41:
            r0 = move-exception
            r38 = r11
            r11 = r4
            r4 = r10
            r10 = r38
            goto L_0x0b5e
        L_0x0b49:
            r0 = move-exception
            r10 = r11
            r11 = r4
            r4 = r13
            goto L_0x0b5e
        L_0x0b4e:
            r0 = move-exception
            r10 = r11
            r11 = r4
        L_0x0b51:
            r4 = r29
            goto L_0x0b5e
        L_0x0b54:
            r0 = move-exception
            goto L_0x0b59
        L_0x0b56:
            r0 = move-exception
            r23 = r3
        L_0x0b59:
            r10 = r11
            r26 = r12
            r11 = r4
            r4 = r14
        L_0x0b5e:
            r5 = r0
        L_0x0b5f:
            boolean r6 = r5 instanceof javax.net.ssl.SSLHandshakeException     // Catch:{ all -> 0x0ca2 }
            if (r6 == 0) goto L_0x0ba0
            if (r2 != 0) goto L_0x0ba0
            java.lang.String[] r6 = r1.b     // Catch:{ all -> 0x0ca2 }
            if (r6 == 0) goto L_0x0ba0
            r3 = 0
            boolean r6 = r1.b((boolean) r3)     // Catch:{ all -> 0x0ca2 }
            if (r6 == 0) goto L_0x0ba0
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0ca2 }
            r6.<init>()     // Catch:{ all -> 0x0ca2 }
            java.lang.String r7 = "[startdownload]url:"
            r6.append(r7)     // Catch:{ all -> 0x0ca2 }
            java.lang.String r7 = r1.k     // Catch:{ all -> 0x0ca2 }
            r6.append(r7)     // Catch:{ all -> 0x0ca2 }
            java.lang.String r7 = " download exception："
            r6.append(r7)     // Catch:{ all -> 0x0ca2 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0ca2 }
            r6.append(r5)     // Catch:{ all -> 0x0ca2 }
            java.lang.String r5 = r6.toString()     // Catch:{ all -> 0x0ca2 }
            com.tencent.smtt.utils.TbsLog.e(r10, r5)     // Catch:{ all -> 0x0ca2 }
            r5 = 125(0x7d, float:1.75E-43)
            r6 = 0
            r7 = 1
            r1.a((int) r5, (java.lang.String) r6, (boolean) r7)     // Catch:{ all -> 0x0ca2 }
            r3 = 0
            r5 = 2
            r7 = 1
            r12 = 0
            goto L_0x0c72
        L_0x0ba0:
            r6 = 0
            r5.printStackTrace()     // Catch:{ all -> 0x0ca2 }
            r12 = 0
            r1.a((long) r12)     // Catch:{ all -> 0x0ca2 }
            r7 = 107(0x6b, float:1.5E-43)
            java.lang.String r5 = r1.a((java.lang.Throwable) r5)     // Catch:{ all -> 0x0ca2 }
            r3 = 0
            r1.a((int) r7, (java.lang.String) r5, (boolean) r3)     // Catch:{ all -> 0x0ca2 }
            boolean r5 = r1.s     // Catch:{ all -> 0x0ca2 }
            if (r5 == 0) goto L_0x0c6f
            android.content.Context r5 = r1.h     // Catch:{ all -> 0x0ca2 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r5)     // Catch:{ all -> 0x0ca2 }
            r6 = -309(0xfffffffffffffecb, float:NaN)
            r5.setDownloadInterruptCode(r6)     // Catch:{ all -> 0x0ca2 }
            if (r2 != 0) goto L_0x069f
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.mSyncMap
            java.lang.Long r5 = java.lang.Long.valueOf(r8)
            r2.put(r4, r5)
            goto L_0x0a13
        L_0x0bd5:
            boolean r2 = r1.s
            if (r2 != 0) goto L_0x0c6b
            boolean r2 = r1.t
            if (r2 == 0) goto L_0x0c27
            java.lang.String[] r2 = r1.b
            if (r2 != 0) goto L_0x0be8
            if (r9 != 0) goto L_0x0be8
            r2 = 1
            boolean r9 = r1.c(r2, r11)
        L_0x0be8:
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r2 = r1.w
            r2.setUnpkgFlag(r9)
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r2 = r1.w
            if (r11 != 0) goto L_0x0bfa
            if (r9 == 0) goto L_0x0bf5
            r4 = 1
            goto L_0x0bf6
        L_0x0bf5:
            r4 = 2
        L_0x0bf6:
            r2.setPatchUpdateFlag(r4)
            goto L_0x0bfe
        L_0x0bfa:
            r3 = 0
            r2.setPatchUpdateFlag(r3)
        L_0x0bfe:
            if (r9 == 0) goto L_0x0c17
            r2 = 1
            r1.d(r2)
            android.content.Context r3 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r3)
            r4 = -317(0xfffffffffffffec3, float:NaN)
            r3.setDownloadInterruptCode(r4)
            r3 = 100
            java.lang.String r4 = "success"
            r1.a((int) r3, (java.lang.String) r4, (boolean) r2)
            goto L_0x0c27
        L_0x0c17:
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r3 = -318(0xfffffffffffffec2, float:NaN)
            r2.setDownloadInterruptCode(r3)
            r3 = 0
            r1.e(r3)
            goto L_0x0c28
        L_0x0c27:
            r3 = 0
        L_0x0c28:
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            if (r9 == 0) goto L_0x0c44
            android.content.SharedPreferences r4 = r2.mPreferences
            java.lang.String r5 = "tbs_download_success_retrytimes"
            int r3 = r4.getInt(r5, r3)
            java.util.Map<java.lang.String, java.lang.Object> r4 = r2.mSyncMap
            r7 = 1
            int r3 = r3 + r7
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r4.put(r5, r3)
            goto L_0x0c63
        L_0x0c44:
            r7 = 1
            android.content.SharedPreferences r4 = r2.mPreferences
            java.lang.String r5 = "tbs_download_failed_retrytimes"
            int r3 = r4.getInt(r5, r3)
            java.util.Map<java.lang.String, java.lang.Object> r4 = r2.mSyncMap
            int r3 = r3 + r7
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)
            r4.put(r5, r6)
            int r4 = r2.getDownloadFailedMaxRetrytimes()
            if (r3 != r4) goto L_0x0c63
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r3 = r1.w
            r5 = 2
            r3.setDownloadCancel(r5)
        L_0x0c63:
            r2.commit()
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r2 = r1.w
            r2.setDownFinalFlag(r9)
        L_0x0c6b:
            r39.h()
            return
        L_0x0c6f:
            r3 = 0
            r5 = 2
            r7 = 1
        L_0x0c72:
            android.content.Context r14 = r1.h     // Catch:{ all -> 0x0ca2 }
            com.tencent.smtt.sdk.TbsDownloadConfig r14 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)     // Catch:{ all -> 0x0ca2 }
            r15 = -316(0xfffffffffffffec4, float:NaN)
            r14.setDownloadInterruptCode(r15)     // Catch:{ all -> 0x0ca2 }
            if (r2 != 0) goto L_0x0c97
            android.content.Context r14 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r14 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            java.util.Map<java.lang.String, java.lang.Object> r14 = r14.mSyncMap
            java.lang.Long r15 = java.lang.Long.valueOf(r8)
            r14.put(r4, r15)
            android.content.Context r14 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r14 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r14.commit()
        L_0x0c97:
            r14 = r4
            r4 = r11
            r3 = r23
            r11 = r10
            r10 = r6
            r5 = r12
            r12 = r26
            goto L_0x0165
        L_0x0ca2:
            r0 = move-exception
            r3 = r0
            if (r2 != 0) goto L_0x0cbe
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.mSyncMap
            java.lang.Long r5 = java.lang.Long.valueOf(r8)
            r2.put(r4, r5)
            android.content.Context r2 = r1.h
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r2)
            r2.commit()
        L_0x0cbe:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.l.b(boolean, boolean):void");
    }

    public boolean b(boolean z2) {
        String[] strArr;
        int i2;
        if ((z2 && !o() && (!QbSdk.getDownloadWithoutWifi() || !Apn.isNetworkAvailable(this.h))) || (strArr = this.b) == null || (i2 = this.c) < 0 || i2 >= strArr.length) {
            return false;
        }
        this.c = i2 + 1;
        this.k = strArr[i2];
        this.q = 0;
        this.r = 0;
        this.m = -1;
        this.p = false;
        this.s = false;
        this.t = false;
        this.z = false;
        return true;
    }

    public int c(boolean z2) {
        File a2 = a(this.h);
        if (z2) {
            if (a2 == null) {
                return 0;
            }
            return a.a(this.h, new File(a2, TbsDownloader.getBackupFileName(true)));
        } else if (a2 == null) {
            return 0;
        } else {
            return a.a(this.h, new File(a2, TbsDownloader.getOverSea(this.h) ? "x5.oversea.tbs.org" : TbsDownloader.getBackupFileName(false)));
        }
    }

    public void c() {
        b();
        e(false);
        e(true);
    }

    public boolean d() {
        TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloader.isDownloadForeground] mIsDownloadForeground=" + this.D);
        return this.D;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        TbsLog.i(TbsDownloader.LOGTAG, "pauseDownload,isPause=" + this.d + "isDownloading=" + TbsDownloader.isDownloading());
        if (!this.d && TbsDownloader.isDownloading()) {
            b();
            this.d = true;
            this.z = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void f() {
        TbsLog.i(TbsDownloader.LOGTAG, "resumeDownload,isPause=" + this.d + "isDownloading=" + TbsDownloader.isDownloading());
        if (this.d && TbsDownloader.isDownloading()) {
            this.d = false;
            a(false);
        }
    }
}
