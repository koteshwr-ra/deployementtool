package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.ciot.base.constant.NetConstant;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.WebView;
import org.apache.commons.lang3.StringUtils;

public class j {
    private static String a = null;
    private static String b = "GA";
    private static String c = "GE";
    private static String d = "9422";
    private static String e = "0";
    private static String f = "";
    private static boolean g = false;
    private static boolean h = false;
    private static boolean i = false;

    private static String a() {
        return StringUtils.SPACE + Build.MODEL.replaceAll("[ |\\/|\\_|\\&|\\|]", "") + StringUtils.SPACE;
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        String a2 = a(context, String.valueOf(WebView.getTbsSDKVersion(context)), NetConstant.PAGE_ID_HOME, b, c, d, e, f, g);
        a = a2;
        return a2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x010a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(android.content.Context r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, boolean r19) {
        /*
            java.lang.String r1 = "ISO8859-1"
            java.lang.String r2 = "UTF-8"
            java.lang.String r3 = ""
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            int r5 = b(r11)
            r0.append(r5)
            java.lang.String r5 = "*"
            r0.append(r5)
            int r5 = c(r11)
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            android.content.Context r0 = r11.getApplicationContext()     // Catch:{ NameNotFoundException -> 0x004a }
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo()     // Catch:{ NameNotFoundException -> 0x004a }
            android.content.pm.PackageManager r6 = r11.getPackageManager()     // Catch:{ NameNotFoundException -> 0x004a }
            java.lang.String r7 = r0.packageName     // Catch:{ NameNotFoundException -> 0x004a }
            r8 = 0
            android.content.pm.PackageInfo r6 = r6.getPackageInfo(r7, r8)     // Catch:{ NameNotFoundException -> 0x004a }
            java.lang.String r7 = r0.packageName     // Catch:{ NameNotFoundException -> 0x004a }
            boolean r0 = android.text.TextUtils.isEmpty(r18)     // Catch:{ NameNotFoundException -> 0x0048 }
            if (r0 != 0) goto L_0x0045
            r0 = r18
            goto L_0x0050
        L_0x0045:
            java.lang.String r0 = r6.versionName     // Catch:{ NameNotFoundException -> 0x0048 }
            goto L_0x0050
        L_0x0048:
            r0 = move-exception
            goto L_0x004c
        L_0x004a:
            r0 = move-exception
            r7 = r3
        L_0x004c:
            r0.printStackTrace()
            r0 = r3
        L_0x0050:
            java.lang.String r6 = a((java.lang.String) r7)
            java.lang.String r8 = "QB"
            boolean r8 = r8.equals(r6)
            java.lang.String r9 = "PAD"
            if (r8 == 0) goto L_0x0061
            if (r19 == 0) goto L_0x0068
            goto L_0x006a
        L_0x0061:
            boolean r8 = d(r11)
            if (r8 == 0) goto L_0x0068
            goto L_0x006a
        L_0x0068:
            java.lang.String r9 = "PHONE"
        L_0x006a:
            java.lang.String r8 = "QV"
            r4.append(r8)
            java.lang.String r8 = "="
            r4.append(r8)
            java.lang.String r8 = "3"
            r4.append(r8)
            java.lang.String r8 = "PL"
            java.lang.String r10 = "ADR"
            a(r4, r8, r10)
            java.lang.String r8 = "PR"
            a(r4, r8, r6)
            java.lang.String r6 = "PP"
            a(r4, r6, r7)
            java.lang.String r6 = "PPVN"
            a(r4, r6, r0)
            boolean r0 = android.text.TextUtils.isEmpty(r12)
            if (r0 != 0) goto L_0x009b
            java.lang.String r0 = "TBSVC"
            r6 = r12
            a(r4, r0, r12)
        L_0x009b:
            java.lang.String r0 = "CO"
            java.lang.String r6 = "SYS"
            a(r4, r0, r6)
            boolean r0 = android.text.TextUtils.isEmpty(r13)
            if (r0 != 0) goto L_0x00ae
            java.lang.String r0 = "COVC"
            r6 = r13
            a(r4, r0, r13)
        L_0x00ae:
            java.lang.String r0 = "PB"
            r6 = r15
            a(r4, r0, r15)
            java.lang.String r0 = "VE"
            r6 = r14
            a(r4, r0, r14)
            java.lang.String r0 = "DE"
            a(r4, r0, r9)
            boolean r0 = android.text.TextUtils.isEmpty(r17)
            if (r0 == 0) goto L_0x00c8
            java.lang.String r0 = "0"
            goto L_0x00ca
        L_0x00c8:
            r0 = r17
        L_0x00ca:
            java.lang.String r6 = "CHID"
            a(r4, r6, r0)
            java.lang.String r0 = "LCID"
            r6 = r16
            a(r4, r0, r6)
            java.lang.String r0 = a()
            java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x00e5 }
            byte[] r7 = r0.getBytes(r2)     // Catch:{ Exception -> 0x00e5 }
            r6.<init>(r7, r1)     // Catch:{ Exception -> 0x00e5 }
            r0 = r6
            goto L_0x00e6
        L_0x00e5:
        L_0x00e6:
            boolean r6 = android.text.TextUtils.isEmpty(r0)
            if (r6 != 0) goto L_0x00f1
            java.lang.String r6 = "MO"
            a(r4, r6, r0)
        L_0x00f1:
            java.lang.String r0 = "RL"
            a(r4, r0, r5)
            java.lang.String r0 = android.os.Build.VERSION.RELEASE
            java.lang.String r5 = new java.lang.String     // Catch:{ Exception -> 0x0103 }
            byte[] r2 = r0.getBytes(r2)     // Catch:{ Exception -> 0x0103 }
            r5.<init>(r2, r1)     // Catch:{ Exception -> 0x0103 }
            r0 = r5
            goto L_0x0104
        L_0x0103:
        L_0x0104:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x010f
            java.lang.String r1 = "OS"
            a(r4, r1, r0)
        L_0x010f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            int r1 = android.os.Build.VERSION.SDK_INT
            r0.append(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "API"
            a(r4, r1, r0)
            java.lang.String r0 = r4.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.j.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean):java.lang.String");
    }

    private static String a(String str) {
        return TbsConfig.APP_WX.equals(str) ? "WX" : TbsConfig.APP_QQ.equals(str) ? "QQ" : TbsConfig.APP_QZONE.equals(str) ? "QZ" : TbsConfig.APP_QB.equals(str) ? "QB" : "TRD";
    }

    private static void a(StringBuilder sb, String str, String str2) {
        sb.append("&");
        sb.append(str);
        sb.append("=");
        sb.append(str2);
    }

    private static int b(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay != null) {
            return defaultDisplay.getWidth();
        }
        return -1;
    }

    private static int c(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay != null) {
            return defaultDisplay.getHeight();
        }
        return -1;
    }

    private static boolean d(Context context) {
        if (h) {
            return i;
        }
        try {
            boolean z = (Math.min(b(context), c(context)) * 160) / e(context) >= 700;
            i = z;
            h = true;
            return z;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static int e(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay == null) {
            return 160;
        }
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }
}
