package com.tencent.smtt.sdk.stat;

import MTT.ThirdAppInfoNew;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloadUpload;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.sdk.TbsPVConfig;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.g;
import com.tencent.smtt.utils.m;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import org.json.JSONObject;

public class b {
    public static byte[] a;

    static {
        try {
            a = "65dRa93L".getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
        }
    }

    private static String a(Context context) {
        try {
            byte[] byteArray = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray();
            if (byteArray != null) {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                instance.update(byteArray);
                byte[] digest = instance.digest();
                if (digest != null) {
                    StringBuilder sb = new StringBuilder("");
                    if (digest != null) {
                        if (digest.length > 0) {
                            for (int i = 0; i < digest.length; i++) {
                                String upperCase = Integer.toHexString(digest[i] & 255).toUpperCase();
                                if (i > 0) {
                                    sb.append(":");
                                }
                                if (upperCase.length() < 2) {
                                    sb.append(0);
                                }
                                sb.append(upperCase);
                            }
                            return sb.toString();
                        }
                    }
                    return null;
                }
            }
        } catch (Exception e) {
            TbsLog.i(e);
        }
        return null;
    }

    public static void a(final ThirdAppInfoNew thirdAppInfoNew, final Context context) {
        new Thread("HttpUtils") {
            public void run() {
                String str;
                String str2;
                String str3;
                com.tencent.smtt.utils.b.b(context, thirdAppInfoNew.sGuid);
                thirdAppInfoNew.sCpu = com.tencent.smtt.utils.b.b();
                if (Build.VERSION.SDK_INT >= 8) {
                    JSONObject jSONObject = null;
                    if (b.a == null) {
                        try {
                            b.a = "65dRa93L".getBytes("utf-8");
                        } catch (UnsupportedEncodingException unused) {
                            b.a = null;
                            TbsLog.e("sdkreport", "Post failed -- get POST_DATA_KEY failed!");
                        }
                    }
                    if (b.a == null) {
                        TbsLog.e("sdkreport", "Post failed -- POST_DATA_KEY is null!");
                        return;
                    }
                    String string = TbsDownloadConfig.getInstance(context).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_DESkEY_TOKEN, "");
                    if (!TextUtils.isEmpty(string)) {
                        str = string.substring(0, string.indexOf("&"));
                        str2 = string.substring(string.indexOf("&") + 1, string.length());
                    } else {
                        str2 = "";
                        str = str2;
                    }
                    boolean z = TextUtils.isEmpty(str) || str.length() != 96 || TextUtils.isEmpty(str2) || str2.length() != 24;
                    try {
                        m a2 = m.a();
                        if (z) {
                            str3 = a2.b() + g.a().b();
                        } else {
                            str3 = a2.f() + str;
                        }
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str3).openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setUseCaches(false);
                        httpURLConnection.setConnectTimeout(20000);
                        if (Build.VERSION.SDK_INT > 13) {
                            httpURLConnection.setRequestProperty(HttpHeaders.CONNECTION, "close");
                        }
                        try {
                            jSONObject = b.c(thirdAppInfoNew, context);
                        } catch (Exception e) {
                            TbsLog.i(e);
                        }
                        if (jSONObject == null) {
                            TbsLog.e("sdkreport", "post -- jsonData is null!");
                            return;
                        }
                        try {
                            byte[] bytes = jSONObject.toString().getBytes("utf-8");
                            byte[] a3 = z ? g.a().a(bytes) : g.a(bytes, str2);
                            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(a3.length));
                            try {
                                OutputStream outputStream = httpURLConnection.getOutputStream();
                                outputStream.write(a3);
                                outputStream.flush();
                                if (httpURLConnection.getResponseCode() == 200) {
                                    TbsLog.i("sdkreport", "Post successful!");
                                    TbsLog.i("sdkreport", "SIGNATURE is " + jSONObject.getString("SIGNATURE"));
                                    b.b(context, b.b(httpURLConnection, str2, z));
                                    new TbsDownloadUpload(context).clearUploadCode();
                                    return;
                                }
                                TbsLog.e("sdkreport", "Post failed -- not 200 code is " + httpURLConnection.getResponseCode());
                                TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(context).tbsLogInfo();
                                tbsLogInfo.setErrorCode(126);
                                tbsLogInfo.setFailDetail("" + httpURLConnection.getResponseCode());
                                TbsLogReport.getInstance(context).eventReport(TbsLogReport.EventType.TYPE_DOWNLOAD, tbsLogInfo);
                            } catch (Throwable th) {
                                TbsLog.e("sdkreport", "Post failed -- exceptions:" + th.getMessage());
                                TbsLogReport.TbsLogInfo tbsLogInfo2 = TbsLogReport.getInstance(context).tbsLogInfo();
                                tbsLogInfo2.setErrorCode(126);
                                tbsLogInfo2.setFailDetail(th);
                                TbsLogReport.getInstance(context).eventReport(TbsLogReport.EventType.TYPE_DOWNLOAD, tbsLogInfo2);
                            }
                        } catch (Throwable unused2) {
                        }
                    } catch (IOException e2) {
                        TbsLog.e("sdkreport", "Post failed -- IOException:" + e2);
                    } catch (AssertionError e3) {
                        TbsLog.e("sdkreport", "Post failed -- AssertionError:" + e3);
                    } catch (NoClassDefFoundError e4) {
                        TbsLog.e("sdkreport", "Post failed -- NoClassDefFoundError:" + e4);
                    }
                }
            }
        }.start();
    }

    private static void a(Context context, String str, String str2) {
        if ("reset".equals(str) && "true".equals(str2)) {
            QbSdk.reset(context);
        } else if (str.startsWith("rmfile")) {
            try {
                SharedPreferences sharedPreferences = context.getSharedPreferences("tbs_status", 0);
                if (!sharedPreferences.getBoolean(str, false)) {
                    File file = new File(str2);
                    if (str2 != null && file.exists()) {
                        TbsLog.i("HttpUtils", "received command,delete" + str2);
                        FileUtil.b(file);
                    }
                    sharedPreferences.edit().putBoolean(str, true).apply();
                }
            } catch (Exception e) {
                TbsLog.i(e);
            }
        } else {
            TbsPVConfig.getInstance(context).putData(str, str2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0119, code lost:
        if (r14 != false) goto L_0x011b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00b7 A[Catch:{ all -> 0x0145 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00bd A[Catch:{ all -> 0x0145 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00c2 A[Catch:{ all -> 0x0145 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00f4 A[Catch:{ all -> 0x0145 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x010b A[Catch:{ all -> 0x0145 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0121 A[Catch:{ all -> 0x0145 }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0135 A[Catch:{ all -> 0x0145 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, int r10, boolean r11, long r12, boolean r14) {
        /*
            java.lang.String r0 = ""
            java.util.Map r1 = com.tencent.smtt.sdk.QbSdk.getSettings()
            if (r1 == 0) goto L_0x002c
            java.util.Map r1 = com.tencent.smtt.sdk.QbSdk.getSettings()
            java.lang.String r2 = "SET_SENDREQUEST_AND_UPLOAD"
            boolean r1 = r1.containsKey(r2)
            if (r1 == 0) goto L_0x002c
            java.util.Map r1 = com.tencent.smtt.sdk.QbSdk.getSettings()
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r2 = "false"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x002c
            java.lang.String r6 = "sdkreport"
            java.lang.String r7 = "[HttpUtils.doReport] -- SET_SENDREQUEST_AND_UPLOAD is false"
            com.tencent.smtt.utils.TbsLog.i(r6, r7)
            return
        L_0x002c:
            r1 = 0
            android.content.pm.ApplicationInfo r2 = r6.getApplicationInfo()     // Catch:{ Exception -> 0x006e }
            java.lang.String r3 = "com.tencent.mobileqq"
            java.lang.String r4 = r2.packageName     // Catch:{ Exception -> 0x006e }
            boolean r3 = r3.equals(r4)     // Catch:{ Exception -> 0x006e }
            if (r3 == 0) goto L_0x006c
            android.content.pm.PackageManager r3 = r6.getPackageManager()     // Catch:{ Exception -> 0x006e }
            java.lang.String r2 = r2.packageName     // Catch:{ Exception -> 0x006e }
            android.content.pm.PackageInfo r2 = r3.getPackageInfo(r2, r1)     // Catch:{ Exception -> 0x006e }
            java.lang.String r2 = r2.versionName     // Catch:{ Exception -> 0x006e }
            java.lang.String r3 = com.tencent.smtt.sdk.QbSdk.getQQBuildNumber()     // Catch:{ Exception -> 0x006a }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x006a }
            if (r3 != 0) goto L_0x0073
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x006a }
            r3.<init>()     // Catch:{ Exception -> 0x006a }
            r3.append(r2)     // Catch:{ Exception -> 0x006a }
            java.lang.String r4 = "."
            r3.append(r4)     // Catch:{ Exception -> 0x006a }
            java.lang.String r4 = com.tencent.smtt.sdk.QbSdk.getQQBuildNumber()     // Catch:{ Exception -> 0x006a }
            r3.append(r4)     // Catch:{ Exception -> 0x006a }
            java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x006a }
            goto L_0x0073
        L_0x006a:
            r3 = move-exception
            goto L_0x0070
        L_0x006c:
            r2 = r0
            goto L_0x0073
        L_0x006e:
            r3 = move-exception
            r2 = r0
        L_0x0070:
            com.tencent.smtt.utils.TbsLog.i(r3)
        L_0x0073:
            MTT.ThirdAppInfoNew r3 = new MTT.ThirdAppInfoNew     // Catch:{ all -> 0x0145 }
            r3.<init>()     // Catch:{ all -> 0x0145 }
            android.content.Context r4 = r6.getApplicationContext()     // Catch:{ all -> 0x0145 }
            android.content.pm.ApplicationInfo r4 = r4.getApplicationInfo()     // Catch:{ all -> 0x0145 }
            java.lang.String r4 = r4.packageName     // Catch:{ all -> 0x0145 }
            r3.sAppName = r4     // Catch:{ all -> 0x0145 }
            com.tencent.smtt.utils.m.a(r6)     // Catch:{ all -> 0x0145 }
            java.text.SimpleDateFormat r4 = new java.text.SimpleDateFormat     // Catch:{ all -> 0x0145 }
            java.lang.String r5 = "yyyy-MM-dd hh:mm:ss"
            r4.<init>(r5)     // Catch:{ all -> 0x0145 }
            java.lang.String r5 = "GMT+08"
            java.util.TimeZone r5 = java.util.TimeZone.getTimeZone(r5)     // Catch:{ all -> 0x0145 }
            r4.setTimeZone(r5)     // Catch:{ all -> 0x0145 }
            java.util.Calendar r5 = java.util.Calendar.getInstance()     // Catch:{ all -> 0x0145 }
            java.util.Date r5 = r5.getTime()     // Catch:{ all -> 0x0145 }
            java.lang.String r4 = r4.format(r5)     // Catch:{ all -> 0x0145 }
            r3.sTime = r4     // Catch:{ all -> 0x0145 }
            int r4 = com.tencent.smtt.utils.b.d(r6)     // Catch:{ all -> 0x0145 }
            r3.sVersionCode = r4     // Catch:{ all -> 0x0145 }
            java.lang.String r4 = "com.tencent.mm.BuildInfo.CLIENT_VERSION"
            java.lang.String r4 = com.tencent.smtt.utils.b.a((android.content.Context) r6, (java.lang.String) r4)     // Catch:{ all -> 0x0145 }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0145 }
            if (r5 != 0) goto L_0x00b9
            r3.sMetaData = r4     // Catch:{ all -> 0x0145 }
        L_0x00b9:
            r3.sGuid = r7     // Catch:{ all -> 0x0145 }
            if (r11 == 0) goto L_0x00c2
            r3.sQua2 = r8     // Catch:{ all -> 0x0145 }
            r3.bIsSandboxMode = r14     // Catch:{ all -> 0x0145 }
            goto L_0x00c8
        L_0x00c2:
            java.lang.String r7 = com.tencent.smtt.utils.j.a((android.content.Context) r6)     // Catch:{ all -> 0x0145 }
            r3.sQua2 = r7     // Catch:{ all -> 0x0145 }
        L_0x00c8:
            r3.sLc = r9     // Catch:{ all -> 0x0145 }
            java.lang.String r7 = com.tencent.smtt.utils.b.h(r6)     // Catch:{ all -> 0x0145 }
            java.lang.String r8 = com.tencent.smtt.utils.b.f(r6)     // Catch:{ all -> 0x0145 }
            java.lang.String r9 = com.tencent.smtt.utils.b.g(r6)     // Catch:{ all -> 0x0145 }
            java.lang.String r4 = com.tencent.smtt.utils.b.i(r6)     // Catch:{ all -> 0x0145 }
            if (r8 == 0) goto L_0x00e4
            boolean r5 = r0.equals(r8)     // Catch:{ all -> 0x0145 }
            if (r5 != 0) goto L_0x00e4
            r3.sImei = r8     // Catch:{ all -> 0x0145 }
        L_0x00e4:
            if (r9 == 0) goto L_0x00ee
            boolean r8 = r0.equals(r9)     // Catch:{ all -> 0x0145 }
            if (r8 != 0) goto L_0x00ee
            r3.sImsi = r9     // Catch:{ all -> 0x0145 }
        L_0x00ee:
            boolean r8 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0145 }
            if (r8 != 0) goto L_0x00f6
            r3.sAndroidID = r4     // Catch:{ all -> 0x0145 }
        L_0x00f6:
            if (r7 == 0) goto L_0x0100
            boolean r8 = r0.equals(r7)     // Catch:{ all -> 0x0145 }
            if (r8 != 0) goto L_0x0100
            r3.sMac = r7     // Catch:{ all -> 0x0145 }
        L_0x0100:
            long r7 = (long) r10     // Catch:{ all -> 0x0145 }
            r3.iPv = r7     // Catch:{ all -> 0x0145 }
            boolean r7 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r6)     // Catch:{ all -> 0x0145 }
            r8 = 3
            r9 = 1
            if (r7 == 0) goto L_0x0121
            if (r11 == 0) goto L_0x011e
            boolean r7 = com.tencent.smtt.sdk.TbsShareManager.getCoreFormOwn()     // Catch:{ all -> 0x0145 }
            if (r7 == 0) goto L_0x0117
            r7 = 2
            r3.iCoreType = r7     // Catch:{ all -> 0x0145 }
            goto L_0x0119
        L_0x0117:
            r3.iCoreType = r9     // Catch:{ all -> 0x0145 }
        L_0x0119:
            if (r14 == 0) goto L_0x012b
        L_0x011b:
            r3.iCoreType = r8     // Catch:{ all -> 0x0145 }
            goto L_0x012b
        L_0x011e:
            r3.iCoreType = r1     // Catch:{ all -> 0x0145 }
            goto L_0x012b
        L_0x0121:
            if (r11 == 0) goto L_0x0124
            r1 = 1
        L_0x0124:
            r3.iCoreType = r1     // Catch:{ all -> 0x0145 }
            if (r11 == 0) goto L_0x012b
            if (r14 == 0) goto L_0x012b
            goto L_0x011b
        L_0x012b:
            r3.sAppVersionName = r2     // Catch:{ all -> 0x0145 }
            java.lang.String r7 = a(r6)     // Catch:{ all -> 0x0145 }
            r3.sAppSignature = r7     // Catch:{ all -> 0x0145 }
            if (r11 != 0) goto L_0x013d
            r3.sWifiConnectedTime = r12     // Catch:{ all -> 0x0145 }
            int r7 = com.tencent.smtt.sdk.QbSdk.getTbsVersion(r6)     // Catch:{ all -> 0x0145 }
            r3.localCoreVersion = r7     // Catch:{ all -> 0x0145 }
        L_0x013d:
            android.content.Context r6 = r6.getApplicationContext()     // Catch:{ all -> 0x0145 }
            a((MTT.ThirdAppInfoNew) r3, (android.content.Context) r6)     // Catch:{ all -> 0x0145 }
            goto L_0x0149
        L_0x0145:
            r6 = move-exception
            com.tencent.smtt.utils.TbsLog.i(r6)
        L_0x0149:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.stat.b.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, int, boolean, long, boolean):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0040 A[Catch:{ Exception -> 0x007e, all -> 0x007a }, LOOP:0: B:17:0x0039->B:19:0x0040, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0047 A[Catch:{ Exception -> 0x007e, all -> 0x007a }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0059 A[Catch:{ Exception -> 0x007e, all -> 0x007a }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0071 A[SYNTHETIC, Splitter:B:29:0x0071] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x008e A[SYNTHETIC, Splitter:B:45:0x008e] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0098 A[SYNTHETIC, Splitter:B:50:0x0098] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00c4 A[SYNTHETIC, Splitter:B:59:0x00c4] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00ce A[SYNTHETIC, Splitter:B:64:0x00ce] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0045 A[EDGE_INSN: B:69:0x0045->B:20:0x0045 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(java.net.HttpURLConnection r4, java.lang.String r5, boolean r6) {
        /*
            r0 = 0
            java.io.InputStream r1 = r4.getInputStream()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            java.lang.String r4 = r4.getContentEncoding()     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            if (r4 == 0) goto L_0x001a
            java.lang.String r2 = "gzip"
            boolean r2 = r4.equalsIgnoreCase(r2)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            if (r2 == 0) goto L_0x001a
            java.util.zip.GZIPInputStream r4 = new java.util.zip.GZIPInputStream     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r4.<init>(r1)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
        L_0x0018:
            r1 = r4
            goto L_0x0030
        L_0x001a:
            if (r4 == 0) goto L_0x0030
            java.lang.String r2 = "deflate"
            boolean r4 = r4.equalsIgnoreCase(r2)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            if (r4 == 0) goto L_0x0030
            java.util.zip.InflaterInputStream r4 = new java.util.zip.InflaterInputStream     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            java.util.zip.Inflater r2 = new java.util.zip.Inflater     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r3 = 1
            r2.<init>(r3)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            r4.<init>(r1, r2)     // Catch:{ Exception -> 0x0087, all -> 0x0084 }
            goto L_0x0018
        L_0x0030:
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0082 }
            r4.<init>()     // Catch:{ Exception -> 0x0082 }
            r0 = 128(0x80, float:1.794E-43)
            byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x007e, all -> 0x007a }
        L_0x0039:
            int r2 = r1.read(r0)     // Catch:{ Exception -> 0x007e, all -> 0x007a }
            r3 = -1
            if (r2 == r3) goto L_0x0045
            r3 = 0
            r4.write(r0, r3, r2)     // Catch:{ Exception -> 0x007e, all -> 0x007a }
            goto L_0x0039
        L_0x0045:
            if (r6 == 0) goto L_0x0059
            java.lang.String r5 = new java.lang.String     // Catch:{ Exception -> 0x007e, all -> 0x007a }
            com.tencent.smtt.utils.g r0 = com.tencent.smtt.utils.g.a()     // Catch:{ Exception -> 0x007e, all -> 0x007a }
            byte[] r2 = r4.toByteArray()     // Catch:{ Exception -> 0x007e, all -> 0x007a }
            byte[] r0 = r0.c(r2)     // Catch:{ Exception -> 0x007e, all -> 0x007a }
            r5.<init>(r0)     // Catch:{ Exception -> 0x007e, all -> 0x007a }
            goto L_0x0067
        L_0x0059:
            java.lang.String r0 = new java.lang.String     // Catch:{ Exception -> 0x007e, all -> 0x007a }
            byte[] r2 = r4.toByteArray()     // Catch:{ Exception -> 0x007e, all -> 0x007a }
            byte[] r5 = com.tencent.smtt.utils.g.b(r2, r5)     // Catch:{ Exception -> 0x007e, all -> 0x007a }
            r0.<init>(r5)     // Catch:{ Exception -> 0x007e, all -> 0x007a }
            r5 = r0
        L_0x0067:
            r4.close()     // Catch:{ IOException -> 0x006b }
            goto L_0x006f
        L_0x006b:
            r4 = move-exception
            com.tencent.smtt.utils.TbsLog.i(r4)
        L_0x006f:
            if (r1 == 0) goto L_0x00a2
            r1.close()     // Catch:{ IOException -> 0x0075 }
            goto L_0x00a2
        L_0x0075:
            r4 = move-exception
            com.tencent.smtt.utils.TbsLog.i(r4)
            goto L_0x00a2
        L_0x007a:
            r5 = move-exception
            r0 = r4
            r4 = r5
            goto L_0x00c2
        L_0x007e:
            r5 = move-exception
            r0 = r4
            r4 = r5
            goto L_0x0089
        L_0x0082:
            r4 = move-exception
            goto L_0x0089
        L_0x0084:
            r4 = move-exception
            r1 = r0
            goto L_0x00c2
        L_0x0087:
            r4 = move-exception
            r1 = r0
        L_0x0089:
            com.tencent.smtt.utils.TbsLog.i(r4)     // Catch:{ all -> 0x00c1 }
            if (r0 == 0) goto L_0x0096
            r0.close()     // Catch:{ IOException -> 0x0092 }
            goto L_0x0096
        L_0x0092:
            r4 = move-exception
            com.tencent.smtt.utils.TbsLog.i(r4)
        L_0x0096:
            if (r1 == 0) goto L_0x00a0
            r1.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x00a0
        L_0x009c:
            r4 = move-exception
            com.tencent.smtt.utils.TbsLog.i(r4)
        L_0x00a0:
            java.lang.String r5 = ""
        L_0x00a2:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "getResponseFromConnection,response="
            r4.append(r0)
            r4.append(r5)
            java.lang.String r0 = ";isUseRSA="
            r4.append(r0)
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            java.lang.String r6 = "HttpUtils"
            com.tencent.smtt.utils.TbsLog.i(r6, r4)
            return r5
        L_0x00c1:
            r4 = move-exception
        L_0x00c2:
            if (r0 == 0) goto L_0x00cc
            r0.close()     // Catch:{ IOException -> 0x00c8 }
            goto L_0x00cc
        L_0x00c8:
            r5 = move-exception
            com.tencent.smtt.utils.TbsLog.i(r5)
        L_0x00cc:
            if (r1 == 0) goto L_0x00d6
            r1.close()     // Catch:{ IOException -> 0x00d2 }
            goto L_0x00d6
        L_0x00d2:
            r5 = move-exception
            com.tencent.smtt.utils.TbsLog.i(r5)
        L_0x00d6:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.stat.b.b(java.net.HttpURLConnection, java.lang.String, boolean):java.lang.String");
    }

    /* access modifiers changed from: private */
    public static void b(Context context, String str) {
        try {
            TbsPVConfig.releaseInstance();
            TbsPVConfig.getInstance(context).clear();
            if (!TextUtils.isEmpty(str)) {
                for (String split : str.split("\\|")) {
                    try {
                        String[] split2 = split.split("=");
                        if (split2.length == 2) {
                            a(context, split2[0], split2[1]);
                        }
                    } catch (Exception e) {
                        TbsLog.i(e);
                    }
                }
                TbsPVConfig.getInstance(context).commit();
            }
        } catch (Exception e2) {
            TbsLog.i(e2);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0104 A[Catch:{ Exception -> 0x0174 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x012a A[SYNTHETIC, Splitter:B:29:0x012a] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0136 A[Catch:{ Exception -> 0x0174 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x013c A[Catch:{ Exception -> 0x0174 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x014e A[Catch:{ Exception -> 0x0173 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject c(MTT.ThirdAppInfoNew r12, android.content.Context r13) {
        /*
            java.lang.String r0 = "com.tencent.mm"
            java.lang.String r1 = "sdkreport"
            java.lang.String r2 = ":"
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x0174 }
            r3.<init>()     // Catch:{ Exception -> 0x0174 }
            java.lang.String r4 = "APPNAME"
            java.lang.String r5 = r12.sAppName     // Catch:{ Exception -> 0x0174 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r4 = "TIME"
            java.lang.String r5 = r12.sTime     // Catch:{ Exception -> 0x0174 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r4 = "QUA2"
            java.lang.String r5 = r12.sQua2     // Catch:{ Exception -> 0x0174 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r4 = "LC"
            java.lang.String r5 = r12.sLc     // Catch:{ Exception -> 0x0174 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r4 = "GUID"
            java.lang.String r5 = r12.sGuid     // Catch:{ Exception -> 0x0174 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r4 = "IMEI"
            java.lang.String r5 = r12.sImei     // Catch:{ Exception -> 0x0174 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r4 = "IMSI"
            java.lang.String r5 = r12.sImsi     // Catch:{ Exception -> 0x0174 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r4 = "MAC"
            java.lang.String r5 = r12.sMac     // Catch:{ Exception -> 0x0174 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r4 = "PV"
            long r5 = r12.iPv     // Catch:{ Exception -> 0x0174 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r4 = "CORETYPE"
            int r5 = r12.iCoreType     // Catch:{ Exception -> 0x0174 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r4 = "APPVN"
            java.lang.String r5 = r12.sAppVersionName     // Catch:{ Exception -> 0x0174 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r4 = "APPMETADATA"
            java.lang.String r5 = r12.sMetaData     // Catch:{ Exception -> 0x0174 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r4 = "VERSION_CODE"
            int r5 = r12.sVersionCode     // Catch:{ Exception -> 0x0174 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r4 = "CPU"
            java.lang.String r5 = r12.sCpu     // Catch:{ Exception -> 0x0174 }
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r4 = r12.sAppName     // Catch:{ Exception -> 0x0174 }
            boolean r4 = r0.equals(r4)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r5 = "com.tencent.mobileqq"
            java.lang.String r6 = "SIGNATURE"
            if (r4 != 0) goto L_0x0099
            java.lang.String r4 = r12.sAppName     // Catch:{ Exception -> 0x0174 }
            boolean r4 = r5.equals(r4)     // Catch:{ Exception -> 0x0174 }
            if (r4 != 0) goto L_0x0099
            java.lang.String r4 = "com.tencent.tbs"
            java.lang.String r7 = r12.sAppName     // Catch:{ Exception -> 0x0174 }
            boolean r4 = r4.equals(r7)     // Catch:{ Exception -> 0x0174 }
            if (r4 == 0) goto L_0x008c
            goto L_0x0099
        L_0x008c:
            java.lang.String r2 = r12.sAppSignature     // Catch:{ Exception -> 0x0174 }
            if (r2 != 0) goto L_0x0096
            java.lang.String r2 = "0"
        L_0x0092:
            r3.put(r6, r2)     // Catch:{ Exception -> 0x0174 }
            goto L_0x00d5
        L_0x0096:
            java.lang.String r2 = r12.sAppSignature     // Catch:{ Exception -> 0x0174 }
            goto L_0x0092
        L_0x0099:
            com.tencent.smtt.sdk.TbsDownloadUpload r4 = new com.tencent.smtt.sdk.TbsDownloadUpload     // Catch:{ Exception -> 0x0174 }
            r4.<init>(r13)     // Catch:{ Exception -> 0x0174 }
            r4.readTbsDownloadInfo(r13)     // Catch:{ Exception -> 0x0174 }
            int r7 = r4.getNeedDownloadCode()     // Catch:{ Exception -> 0x0174 }
            int r8 = r4.getStartDownloadCode()     // Catch:{ Exception -> 0x0174 }
            int r9 = r4.getNeedDownloadReturn()     // Catch:{ Exception -> 0x0174 }
            int r4 = r4.getLocalCoreVersion()     // Catch:{ Exception -> 0x0174 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0174 }
            r10.<init>()     // Catch:{ Exception -> 0x0174 }
            java.lang.String r11 = ""
            r10.append(r11)     // Catch:{ Exception -> 0x0174 }
            r10.append(r7)     // Catch:{ Exception -> 0x0174 }
            r10.append(r2)     // Catch:{ Exception -> 0x0174 }
            r10.append(r8)     // Catch:{ Exception -> 0x0174 }
            r10.append(r2)     // Catch:{ Exception -> 0x0174 }
            r10.append(r9)     // Catch:{ Exception -> 0x0174 }
            r10.append(r2)     // Catch:{ Exception -> 0x0174 }
            r10.append(r4)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r2 = r10.toString()     // Catch:{ Exception -> 0x0174 }
            goto L_0x0092
        L_0x00d5:
            java.lang.String r2 = "PROTOCOL_VERSION"
            r4 = 3
            r3.put(r2, r4)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r2 = "ANDROID_ID"
            java.lang.String r4 = r12.sAndroidID     // Catch:{ Exception -> 0x0174 }
            r3.put(r2, r4)     // Catch:{ Exception -> 0x0174 }
            boolean r2 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r13)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r4 = "HOST_COREVERSION"
            if (r2 == 0) goto L_0x0104
            java.lang.String r2 = "com.xunmeng.pinduoduo"
            android.content.Context r6 = r13.getApplicationContext()     // Catch:{ Exception -> 0x0174 }
            android.content.pm.ApplicationInfo r6 = r6.getApplicationInfo()     // Catch:{ Exception -> 0x0174 }
            java.lang.String r6 = r6.packageName     // Catch:{ Exception -> 0x0174 }
            boolean r2 = r2.equals(r6)     // Catch:{ Exception -> 0x0174 }
            if (r2 != 0) goto L_0x0114
            long r6 = com.tencent.smtt.sdk.TbsShareManager.getHostCoreVersions(r13)     // Catch:{ Exception -> 0x0174 }
            r3.put(r4, r6)     // Catch:{ Exception -> 0x0174 }
            goto L_0x0114
        L_0x0104:
            int r2 = com.tencent.smtt.sdk.TbsDownloader.getCoreShareDecoupleCoreVersionByContext(r13)     // Catch:{ Exception -> 0x0174 }
            r3.put(r4, r2)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r2 = "DECOUPLE_COREVERSION"
            int r4 = com.tencent.smtt.sdk.TbsDownloader.getCoreShareDecoupleCoreVersionByContext(r13)     // Catch:{ Exception -> 0x0174 }
            r3.put(r2, r4)     // Catch:{ Exception -> 0x0174 }
        L_0x0114:
            java.lang.String r2 = "WIFICONNECTEDTIME"
            long r6 = r12.sWifiConnectedTime     // Catch:{ Exception -> 0x0174 }
            r3.put(r2, r6)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r2 = "CORE_EXIST"
            int r4 = r12.localCoreVersion     // Catch:{ Exception -> 0x0174 }
            r3.put(r2, r4)     // Catch:{ Exception -> 0x0174 }
            int r2 = com.tencent.smtt.sdk.TbsCoreLoadStat.mLoadErrorCode     // Catch:{ Exception -> 0x0174 }
            int r4 = r12.localCoreVersion     // Catch:{ Exception -> 0x0174 }
            java.lang.String r6 = "TBS_ERROR_CODE"
            if (r4 > 0) goto L_0x0136
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ Exception -> 0x0174 }
            int r4 = r4.getDownloadInterruptCode()     // Catch:{ Exception -> 0x0174 }
            r3.put(r6, r4)     // Catch:{ Exception -> 0x0174 }
            goto L_0x0139
        L_0x0136:
            r3.put(r6, r2)     // Catch:{ Exception -> 0x0174 }
        L_0x0139:
            r4 = -1
            if (r2 != r4) goto L_0x0141
            java.lang.String r2 = "ATTENTION: Load errorCode missed!"
            com.tencent.smtt.utils.TbsLog.e(r1, r2)     // Catch:{ Exception -> 0x0174 }
        L_0x0141:
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r13)     // Catch:{ Exception -> 0x0174 }
            r2.uploadDownloadInterruptCodeIfNeeded(r13)     // Catch:{ Exception -> 0x0174 }
            java.lang.String r13 = com.tencent.smtt.sdk.QbSdk.getTID()     // Catch:{ Exception -> 0x0173 }
            if (r13 == 0) goto L_0x0173
            java.lang.String r13 = r12.sAppName     // Catch:{ Exception -> 0x0173 }
            boolean r13 = r13.equals(r5)     // Catch:{ Exception -> 0x0173 }
            r1 = 0
            java.lang.String r2 = "TIDTYPE"
            java.lang.String r4 = "TID"
            if (r13 == 0) goto L_0x0166
            java.lang.String r12 = com.tencent.smtt.sdk.QbSdk.getTID()     // Catch:{ Exception -> 0x0173 }
        L_0x015f:
            r3.put(r4, r12)     // Catch:{ Exception -> 0x0173 }
            r3.put(r2, r1)     // Catch:{ Exception -> 0x0173 }
            goto L_0x0173
        L_0x0166:
            java.lang.String r12 = r12.sAppName     // Catch:{ Exception -> 0x0173 }
            boolean r12 = r12.equals(r0)     // Catch:{ Exception -> 0x0173 }
            if (r12 == 0) goto L_0x0173
            java.lang.String r12 = com.tencent.smtt.sdk.QbSdk.getTID()     // Catch:{ Exception -> 0x0173 }
            goto L_0x015f
        L_0x0173:
            return r3
        L_0x0174:
            java.lang.String r12 = "getPostData exception!"
            com.tencent.smtt.utils.TbsLog.e(r1, r12)
            r12 = 0
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.stat.b.c(MTT.ThirdAppInfoNew, android.content.Context):org.json.JSONObject");
    }
}
