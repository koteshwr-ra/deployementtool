package com.tencent.smtt.sdk.stat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.ciot.base.constant.NetConstant;
import com.google.common.base.Ascii;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.utils.FileProvider;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.apache.mina.proxy.handlers.http.ntlm.NTLMConstants;

public class MttLoader {
    public static final String CHANNEL_ID = "ChannelID";
    public static final String ENTRY_ID = "entryId";
    @Deprecated
    public static final String KEY_ACTIVITY_NAME = "KEY_ACT";
    @Deprecated
    public static final String KEY_APP_NAME = "KEY_APPNAME";
    public static final String KEY_EUSESTAT = "KEY_EUSESTAT";
    @Deprecated
    public static final String KEY_PACKAGE = "KEY_PKG";
    public static final String KEY_PID = "KEY_PID";
    public static final String MTT_ACTION = "com.tencent.QQBrowser.action.VIEW";
    public static final String MTT_ACTION_SP = "com.tencent.QQBrowser.action.VIEWSP";
    public static final String PID_ARTICLE_NEWS = "21272";
    public static final String PID_MOBILE_QQ = "50079";
    public static final String PID_QQPIM = "50190";
    public static final String PID_QZONE = "10494";
    public static final String PID_WECHAT = "10318";
    public static final String POS_ID = "PosID";
    public static final String QQBROWSER_DIRECT_DOWNLOAD_URL = "https://mdc.html5.qq.com/d/directdown.jsp?channel_id=50079";
    public static final String QQBROWSER_DOWNLOAD_URL = "https://mdc.html5.qq.com/mh?channel_id=50079&u=";
    public static final String QQBROWSER_PARAMS_FROME = ",from=";
    public static final String QQBROWSER_PARAMS_PACKAGENAME = ",packagename=";
    public static final String QQBROWSER_PARAMS_PD = ",product=";
    public static final String QQBROWSER_PARAMS_VERSION = ",version=";
    public static final String QQBROWSER_SCHEME = "mttbrowser://url=";
    public static final int RESULT_INVALID_CONTEXT = 3;
    public static final int RESULT_INVALID_URL = 2;
    public static final int RESULT_NOT_INSTALL_QQBROWSER = 4;
    public static final int RESULT_OK = 0;
    public static final int RESULT_QQBROWSER_LOW = 5;
    public static final int RESULT_UNKNOWN = 1;
    public static final String STAT_KEY = "StatKey";

    public static class BrowserInfo {
        public int browserType = -1;
        public String packageName = null;
        public String quahead = "";
        public int ver = -1;
        public String vn = NetConstant.PAGE_ID_HOME;
    }

    private static class a {
        public String a;
        public String b;

        private a() {
            this.a = "";
            this.b = "";
        }
    }

    private static int a(Context context) {
        String str = context.getApplicationInfo().processName;
        if (str.equals(TbsConfig.APP_QQ)) {
            return 13;
        }
        if (str.equals(TbsConfig.APP_QZONE)) {
            return 14;
        }
        if (str.equals("com.tencent.WBlog")) {
            return 15;
        }
        return str.equals(TbsConfig.APP_WX) ? 24 : 26;
    }

    private static Uri a(Context context, String str) {
        return FileProvider.a(context, str);
    }

    private static a a(Context context, Uri uri) {
        Intent intent = new Intent(MTT_ACTION);
        intent.setData(uri);
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities.size() <= 0) {
            return null;
        }
        a aVar = new a();
        for (ResolveInfo next : queryIntentActivities) {
            String str = next.activityInfo.packageName;
            if (str.contains(TbsConfig.APP_QB)) {
                aVar.a = next.activityInfo.name;
                aVar.b = next.activityInfo.packageName;
                return aVar;
            } else if (str.contains("com.tencent.qbx")) {
                aVar.a = next.activityInfo.name;
                aVar.b = next.activityInfo.packageName;
            }
        }
        return aVar;
    }

    private static String a(Certificate certificate) throws CertificateEncodingException {
        byte[] encoded = certificate.getEncoded();
        int length = encoded.length;
        char[] cArr = new char[(length * 2)];
        for (int i = 0; i < length; i++) {
            byte b = encoded[i];
            int i2 = (b >> 4) & 15;
            int i3 = i * 2;
            cArr[i3] = (char) (i2 >= 10 ? (i2 + 97) - 10 : i2 + 48);
            byte b2 = b & Ascii.SI;
            cArr[i3 + 1] = (char) (b2 >= 10 ? (b2 + 97) - 10 : b2 + 48);
        }
        return new String(cArr);
    }

    private static boolean a(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        String trim = str.trim();
        int indexOf = trim.toLowerCase().indexOf("://");
        int indexOf2 = trim.toLowerCase().indexOf(46);
        if (indexOf <= 0 || indexOf2 <= 0 || indexOf <= indexOf2) {
            return trim.toLowerCase().contains("://");
        }
        return false;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(16:3|4|5|6|7|(2:11|12)|13|14|15|16|17|18|19|20|(2:35|36)|37) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0066 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0073 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0081 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x008c */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00be A[SYNTHETIC, Splitter:B:35:0x00be] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.tencent.smtt.sdk.stat.MttLoader.BrowserInfo getBrowserInfo(android.content.Context r14) {
        /*
            java.lang.String r0 = "com.tencent.mtt.x86"
            java.lang.String r1 = "com.tencent.qbx5"
            java.lang.String r2 = "com.tencent.qbx"
            java.lang.String r3 = ""
            java.lang.String r4 = "\\."
            java.lang.String r5 = "ADRQB_"
            java.lang.String r6 = "com.tencent.mtt"
            android.content.Context r7 = r14.getApplicationContext()
            java.lang.String r8 = "x5_proxy_setting"
            r9 = 0
            android.content.SharedPreferences r7 = r7.getSharedPreferences(r8, r9)
            java.lang.String r8 = "qb_install_status"
            boolean r7 = r7.getBoolean(r8, r9)
            com.tencent.smtt.sdk.stat.MttLoader$BrowserInfo r8 = new com.tencent.smtt.sdk.stat.MttLoader$BrowserInfo
            r8.<init>()
            if (r7 == 0) goto L_0x0027
            return r8
        L_0x0027:
            android.content.pm.PackageManager r7 = r14.getPackageManager()     // Catch:{ Exception -> 0x00e3 }
            r10 = 0
            r11 = 2
            android.content.pm.PackageInfo r10 = r7.getPackageInfo(r6, r9)     // Catch:{ NameNotFoundException -> 0x0066 }
            r8.browserType = r11     // Catch:{ NameNotFoundException -> 0x0066 }
            r8.packageName = r6     // Catch:{ NameNotFoundException -> 0x0066 }
            r8.quahead = r5     // Catch:{ NameNotFoundException -> 0x0066 }
            if (r10 == 0) goto L_0x0066
            int r12 = r10.versionCode     // Catch:{ NameNotFoundException -> 0x0066 }
            r13 = 420000(0x668a0, float:5.88545E-40)
            if (r12 <= r13) goto L_0x0066
            int r12 = r10.versionCode     // Catch:{ NameNotFoundException -> 0x0066 }
            r8.ver = r12     // Catch:{ NameNotFoundException -> 0x0066 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException -> 0x0066 }
            r12.<init>()     // Catch:{ NameNotFoundException -> 0x0066 }
            java.lang.String r13 = r8.quahead     // Catch:{ NameNotFoundException -> 0x0066 }
            r12.append(r13)     // Catch:{ NameNotFoundException -> 0x0066 }
            java.lang.String r13 = r10.versionName     // Catch:{ NameNotFoundException -> 0x0066 }
            java.lang.String r13 = r13.replaceAll(r4, r3)     // Catch:{ NameNotFoundException -> 0x0066 }
            r12.append(r13)     // Catch:{ NameNotFoundException -> 0x0066 }
            java.lang.String r12 = r12.toString()     // Catch:{ NameNotFoundException -> 0x0066 }
            r8.quahead = r12     // Catch:{ NameNotFoundException -> 0x0066 }
            java.lang.String r12 = r10.versionName     // Catch:{ NameNotFoundException -> 0x0066 }
            java.lang.String r12 = r12.replaceAll(r4, r3)     // Catch:{ NameNotFoundException -> 0x0066 }
            r8.vn = r12     // Catch:{ NameNotFoundException -> 0x0066 }
            return r8
        L_0x0066:
            android.content.pm.PackageInfo r10 = r7.getPackageInfo(r2, r9)     // Catch:{ NameNotFoundException -> 0x0073 }
            r8.browserType = r9     // Catch:{ NameNotFoundException -> 0x0073 }
            r8.packageName = r2     // Catch:{ NameNotFoundException -> 0x0073 }
            java.lang.String r2 = "ADRQBX_"
            r8.quahead = r2     // Catch:{ NameNotFoundException -> 0x0073 }
            goto L_0x00bc
        L_0x0073:
            android.content.pm.PackageInfo r10 = r7.getPackageInfo(r1, r9)     // Catch:{ NameNotFoundException -> 0x0081 }
            r2 = 1
            r8.browserType = r2     // Catch:{ NameNotFoundException -> 0x0081 }
            r8.packageName = r1     // Catch:{ NameNotFoundException -> 0x0081 }
            java.lang.String r1 = "ADRQBX5_"
            r8.quahead = r1     // Catch:{ NameNotFoundException -> 0x0081 }
            goto L_0x00bc
        L_0x0081:
            android.content.pm.PackageInfo r10 = r7.getPackageInfo(r6, r9)     // Catch:{ NameNotFoundException -> 0x008c }
            r8.packageName = r6     // Catch:{ NameNotFoundException -> 0x008c }
            r8.browserType = r11     // Catch:{ NameNotFoundException -> 0x008c }
            r8.quahead = r5     // Catch:{ NameNotFoundException -> 0x008c }
            goto L_0x00bc
        L_0x008c:
            android.content.pm.PackageInfo r10 = r7.getPackageInfo(r0, r9)     // Catch:{ Exception -> 0x0097 }
            r8.packageName = r0     // Catch:{ Exception -> 0x0097 }
            r8.browserType = r11     // Catch:{ Exception -> 0x0097 }
            r8.quahead = r5     // Catch:{ Exception -> 0x0097 }
            goto L_0x00bc
        L_0x0097:
            java.lang.String r0 = "https://mdc.html5.qq.com/mh?channel_id=50079&u="
            android.net.Uri r0 = android.net.Uri.parse(r0)     // Catch:{ Exception -> 0x00bb }
            com.tencent.smtt.sdk.stat.MttLoader$a r14 = a((android.content.Context) r14, (android.net.Uri) r0)     // Catch:{ Exception -> 0x00bb }
            if (r14 == 0) goto L_0x00bc
            java.lang.String r0 = r14.b     // Catch:{ Exception -> 0x00bb }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x00bb }
            if (r0 != 0) goto L_0x00bc
            java.lang.String r0 = r14.b     // Catch:{ Exception -> 0x00bb }
            android.content.pm.PackageInfo r0 = r7.getPackageInfo(r0, r9)     // Catch:{ Exception -> 0x00bb }
            java.lang.String r14 = r14.b     // Catch:{ Exception -> 0x00b9 }
            r8.packageName = r14     // Catch:{ Exception -> 0x00b9 }
            r8.browserType = r11     // Catch:{ Exception -> 0x00b9 }
            r8.quahead = r5     // Catch:{ Exception -> 0x00b9 }
        L_0x00b9:
            r10 = r0
            goto L_0x00bc
        L_0x00bb:
        L_0x00bc:
            if (r10 == 0) goto L_0x00e3
            int r14 = r10.versionCode     // Catch:{ Exception -> 0x00e3 }
            r8.ver = r14     // Catch:{ Exception -> 0x00e3 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e3 }
            r14.<init>()     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r0 = r8.quahead     // Catch:{ Exception -> 0x00e3 }
            r14.append(r0)     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r0 = r10.versionName     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r0 = r0.replaceAll(r4, r3)     // Catch:{ Exception -> 0x00e3 }
            r14.append(r0)     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x00e3 }
            r8.quahead = r14     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r14 = r10.versionName     // Catch:{ Exception -> 0x00e3 }
            java.lang.String r14 = r14.replaceAll(r4, r3)     // Catch:{ Exception -> 0x00e3 }
            r8.vn = r14     // Catch:{ Exception -> 0x00e3 }
        L_0x00e3:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.stat.MttLoader.getBrowserInfo(android.content.Context):com.tencent.smtt.sdk.stat.MttLoader$BrowserInfo");
    }

    public static String getDownloadUrlWithQb(String str) {
        try {
            return QQBROWSER_DOWNLOAD_URL + URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return QQBROWSER_DOWNLOAD_URL;
        }
    }

    public static String getValidQBUrl(Context context, String str) {
        if (str.toLowerCase().startsWith("qb://")) {
            boolean z = false;
            BrowserInfo browserInfo = getBrowserInfo(context);
            if (browserInfo.browserType == -1 || (browserInfo.browserType == 2 && browserInfo.ver < 33)) {
                z = true;
            }
            if (z) {
                return getDownloadUrlWithQb(str);
            }
        }
        return str;
    }

    public static boolean isBrowserInstalled(Context context) {
        return getBrowserInfo(context).browserType != -1;
    }

    public static boolean isBrowserInstalledEx(Context context) {
        BrowserInfo browserInfo = getBrowserInfo(context);
        boolean z = false;
        try {
            if (Long.valueOf(browserInfo.vn).longValue() >= 6001500) {
                z = true;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (browserInfo.ver >= 601500) {
            return true;
        }
        return z;
    }

    public static boolean isGreatBrowserVer(Context context, long j, long j2) {
        BrowserInfo browserInfo = getBrowserInfo(context);
        boolean z = false;
        try {
            if (Long.valueOf(browserInfo.vn).longValue() >= j) {
                z = true;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (((long) browserInfo.ver) >= j2) {
            return true;
        }
        return z;
    }

    public static boolean isSupportQBScheme(Context context) {
        BrowserInfo browserInfo = getBrowserInfo(context);
        if (browserInfo.browserType == -1) {
            return false;
        }
        return browserInfo.browserType != 2 || browserInfo.ver >= 42;
    }

    public static boolean isSupportingTbsTips(Context context) {
        BrowserInfo browserInfo = getBrowserInfo(context);
        return browserInfo.browserType == 2 && browserInfo.ver >= 580000;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0080, code lost:
        if (android.text.TextUtils.isEmpty(r0.a) == false) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00cc, code lost:
        if (android.text.TextUtils.isEmpty(r0.a) == false) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00e0, code lost:
        if (android.text.TextUtils.isEmpty(r0.a) == false) goto L_0x0082;
     */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x011e A[Catch:{ ActivityNotFoundException -> 0x0147 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int loadUrl(android.content.Context r7, java.lang.String r8, java.util.HashMap<java.lang.String, java.lang.String> r9, com.tencent.smtt.sdk.WebView r10) {
        /*
            if (r7 != 0) goto L_0x0004
            r7 = 3
            return r7
        L_0x0004:
            boolean r0 = a((java.lang.String) r8)
            if (r0 != 0) goto L_0x001b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "http://"
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
        L_0x001b:
            r0 = 2
            android.net.Uri r8 = android.net.Uri.parse(r8)     // Catch:{ Exception -> 0x0148 }
            if (r8 != 0) goto L_0x0023
            return r0
        L_0x0023:
            com.tencent.smtt.sdk.stat.MttLoader$BrowserInfo r1 = getBrowserInfo(r7)
            int r2 = r1.browserType
            r3 = -1
            r4 = 4
            if (r2 != r3) goto L_0x002e
            return r4
        L_0x002e:
            int r2 = r1.browserType
            r3 = 33
            if (r2 != r0) goto L_0x003a
            int r2 = r1.ver
            if (r2 >= r3) goto L_0x003a
            r7 = 5
            return r7
        L_0x003a:
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r5 = "android.intent.action.VIEW"
            r2.<init>(r5)
            int r5 = r1.browserType
            java.lang.String r6 = "com.tencent.QQBrowser.action.VIEW"
            if (r5 != r0) goto L_0x008a
            int r0 = r1.ver
            java.lang.String r5 = "com.tencent.mtt"
            if (r0 < r3) goto L_0x005a
            int r0 = r1.ver
            r3 = 39
            if (r0 > r3) goto L_0x005a
            java.lang.String r0 = "com.tencent.mtt.MainActivity"
        L_0x0055:
            r2.setClassName(r5, r0)
            goto L_0x00e3
        L_0x005a:
            int r0 = r1.ver
            r3 = 40
            if (r0 < r3) goto L_0x0069
            int r0 = r1.ver
            r3 = 45
            if (r0 > r3) goto L_0x0069
            java.lang.String r0 = "com.tencent.mtt.SplashActivity"
            goto L_0x0055
        L_0x0069:
            int r0 = r1.ver
            r1 = 46
            if (r0 < r1) goto L_0x00e3
            android.content.Intent r2 = new android.content.Intent
            r2.<init>(r6)
            com.tencent.smtt.sdk.stat.MttLoader$a r0 = a((android.content.Context) r7, (android.net.Uri) r8)
            if (r0 == 0) goto L_0x00e3
            java.lang.String r1 = r0.a
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x00e3
        L_0x0082:
            java.lang.String r1 = r0.b
            java.lang.String r0 = r0.a
            r2.setClassName(r1, r0)
            goto L_0x00e3
        L_0x008a:
            int r3 = r1.browserType
            r5 = 1
            if (r3 != r5) goto L_0x00a2
            int r3 = r1.ver
            java.lang.String r6 = "com.tencent.qbx5"
            if (r3 != r5) goto L_0x009b
            java.lang.String r0 = "com.tencent.qbx5.MainActivity"
        L_0x0097:
            r2.setClassName(r6, r0)
            goto L_0x00e3
        L_0x009b:
            int r1 = r1.ver
            if (r1 != r0) goto L_0x00e3
            java.lang.String r0 = "com.tencent.qbx5.SplashActivity"
            goto L_0x0097
        L_0x00a2:
            int r0 = r1.browserType
            if (r0 != 0) goto L_0x00cf
            int r0 = r1.ver
            r3 = 6
            if (r0 < r4) goto L_0x00b7
            int r0 = r1.ver
            if (r0 > r3) goto L_0x00b7
            java.lang.String r0 = "com.tencent.qbx"
            java.lang.String r1 = "com.tencent.qbx.SplashActivity"
            r2.setClassName(r0, r1)
            goto L_0x00e3
        L_0x00b7:
            int r0 = r1.ver
            if (r0 <= r3) goto L_0x00e3
            android.content.Intent r2 = new android.content.Intent
            r2.<init>(r6)
            com.tencent.smtt.sdk.stat.MttLoader$a r0 = a((android.content.Context) r7, (android.net.Uri) r8)
            if (r0 == 0) goto L_0x00e3
            java.lang.String r1 = r0.a
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x00e3
            goto L_0x0082
        L_0x00cf:
            android.content.Intent r2 = new android.content.Intent
            r2.<init>(r6)
            com.tencent.smtt.sdk.stat.MttLoader$a r0 = a((android.content.Context) r7, (android.net.Uri) r8)
            if (r0 == 0) goto L_0x00e3
            java.lang.String r1 = r0.a
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x00e3
            goto L_0x0082
        L_0x00e3:
            r2.setData(r8)
            if (r9 == 0) goto L_0x010e
            java.util.Set r8 = r9.keySet()
            if (r8 == 0) goto L_0x010e
            java.util.Iterator r8 = r8.iterator()
        L_0x00f2:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x010e
            java.lang.Object r0 = r8.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r1 = r9.get(r0)
            java.lang.String r1 = (java.lang.String) r1
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 != 0) goto L_0x00f2
            r2.putExtra(r0, r1)
            goto L_0x00f2
        L_0x010e:
            java.lang.String r8 = "loginType"
            int r9 = a((android.content.Context) r7)     // Catch:{ ActivityNotFoundException -> 0x0147 }
            r2.putExtra(r8, r9)     // Catch:{ ActivityNotFoundException -> 0x0147 }
            r8 = 268435456(0x10000000, float:2.5243549E-29)
            r2.addFlags(r8)     // Catch:{ ActivityNotFoundException -> 0x0147 }
            if (r10 == 0) goto L_0x0142
            android.graphics.Point r8 = new android.graphics.Point     // Catch:{ ActivityNotFoundException -> 0x0147 }
            int r9 = r10.getScrollX()     // Catch:{ ActivityNotFoundException -> 0x0147 }
            int r0 = r10.getScrollY()     // Catch:{ ActivityNotFoundException -> 0x0147 }
            r8.<init>(r9, r0)     // Catch:{ ActivityNotFoundException -> 0x0147 }
            java.lang.String r9 = "AnchorPoint"
            r2.putExtra(r9, r8)     // Catch:{ ActivityNotFoundException -> 0x0147 }
            android.graphics.Point r8 = new android.graphics.Point     // Catch:{ ActivityNotFoundException -> 0x0147 }
            int r9 = r10.getContentWidth()     // Catch:{ ActivityNotFoundException -> 0x0147 }
            int r10 = r10.getContentHeight()     // Catch:{ ActivityNotFoundException -> 0x0147 }
            r8.<init>(r9, r10)     // Catch:{ ActivityNotFoundException -> 0x0147 }
            java.lang.String r9 = "ContentSize"
            r2.putExtra(r9, r8)     // Catch:{ ActivityNotFoundException -> 0x0147 }
        L_0x0142:
            r7.startActivity(r2)     // Catch:{ ActivityNotFoundException -> 0x0147 }
            r7 = 0
            return r7
        L_0x0147:
            return r4
        L_0x0148:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.stat.MttLoader.loadUrl(android.content.Context, java.lang.String, java.util.HashMap, com.tencent.smtt.sdk.WebView):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int loadUrl(android.content.Context r4, java.lang.String r5, java.util.HashMap<java.lang.String, java.lang.String> r6, java.lang.String r7, com.tencent.smtt.sdk.WebView r8) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            android.content.pm.PackageManager r2 = r4.getPackageManager()     // Catch:{ all -> 0x001d }
            if (r2 == 0) goto L_0x001d
            java.lang.String r3 = "com.tencent.mtt"
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r1)     // Catch:{ all -> 0x001d }
            if (r2 == 0) goto L_0x001d
            int r2 = r2.versionCode     // Catch:{ all -> 0x001d }
            r3 = 601000(0x92ba8, float:8.4218E-40)
            if (r2 <= r3) goto L_0x001d
            r2 = 1
            goto L_0x001e
        L_0x001d:
            r2 = 0
        L_0x001e:
            java.lang.String r3 = "UTF-8"
            java.lang.String r1 = java.net.URLEncoder.encode(r5, r3)     // Catch:{ Exception -> 0x0029 }
            if (r2 == 0) goto L_0x0027
            r5 = r1
        L_0x0027:
            r1 = r2
            goto L_0x002a
        L_0x0029:
        L_0x002a:
            if (r1 == 0) goto L_0x002f
            java.lang.String r1 = ",encoded=1"
            goto L_0x0031
        L_0x002f:
            java.lang.String r1 = ""
        L_0x0031:
            java.lang.String r2 = "mttbrowser://url="
            r0.append(r2)
            r0.append(r5)
            java.lang.String r5 = ",product="
            r0.append(r5)
            java.lang.String r5 = "TBS"
            r0.append(r5)
            java.lang.String r5 = ",packagename="
            r0.append(r5)
            java.lang.String r5 = r4.getPackageName()
            r0.append(r5)
            java.lang.String r5 = ",from="
            r0.append(r5)
            r0.append(r7)
            java.lang.String r5 = ",version="
            r0.append(r5)
            java.lang.String r5 = "4.3.0.93"
            r0.append(r5)
            r0.append(r1)
            java.lang.String r5 = r0.toString()
            int r4 = loadUrl(r4, r5, r6, r8)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.stat.MttLoader.loadUrl(android.content.Context, java.lang.String, java.util.HashMap, java.lang.String, com.tencent.smtt.sdk.WebView):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x007c A[Catch:{ Exception -> 0x00a9 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007d A[Catch:{ Exception -> 0x00a9 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean openDocWithQb(android.content.Context r6, java.lang.String r7, int r8, java.lang.String r9, java.lang.String r10, java.util.HashMap<java.lang.String, java.lang.String> r11, android.os.Bundle r12) {
        /*
            r0 = 0
            android.content.Intent r1 = new android.content.Intent     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r2 = "com.tencent.QQBrowser.action.sdk.document"
            r1.<init>(r2)     // Catch:{ Exception -> 0x00a9 }
            if (r11 == 0) goto L_0x0030
            java.util.Set r2 = r11.keySet()     // Catch:{ Exception -> 0x00a9 }
            if (r2 == 0) goto L_0x0030
            java.util.Iterator r2 = r2.iterator()     // Catch:{ Exception -> 0x00a9 }
        L_0x0014:
            boolean r3 = r2.hasNext()     // Catch:{ Exception -> 0x00a9 }
            if (r3 == 0) goto L_0x0030
            java.lang.Object r3 = r2.next()     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Exception -> 0x00a9 }
            java.lang.Object r4 = r11.get(r3)     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x00a9 }
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Exception -> 0x00a9 }
            if (r5 != 0) goto L_0x0014
            r1.putExtra(r3, r4)     // Catch:{ Exception -> 0x00a9 }
            goto L_0x0014
        L_0x0030:
            java.io.File r11 = new java.io.File     // Catch:{ Exception -> 0x00a9 }
            r11.<init>(r7)     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r11 = "key_reader_sdk_id"
            r2 = 3
            r1.putExtra(r11, r2)     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r11 = "key_reader_sdk_type"
            r1.putExtra(r11, r8)     // Catch:{ Exception -> 0x00a9 }
            boolean r11 = android.text.TextUtils.isEmpty(r10)     // Catch:{ Exception -> 0x00a9 }
            if (r11 != 0) goto L_0x004b
            java.lang.String r11 = "big_brother_source_key"
            r1.putExtra(r11, r10)     // Catch:{ Exception -> 0x00a9 }
        L_0x004b:
            r10 = 1
            if (r8 != 0) goto L_0x0054
            java.lang.String r8 = "key_reader_sdk_path"
        L_0x0050:
            r1.putExtra(r8, r7)     // Catch:{ Exception -> 0x00a9 }
            goto L_0x0059
        L_0x0054:
            if (r8 != r10) goto L_0x0059
            java.lang.String r8 = "key_reader_sdk_url"
            goto L_0x0050
        L_0x0059:
            java.lang.String r8 = "key_reader_sdk_format"
            r1.putExtra(r8, r9)     // Catch:{ Exception -> 0x00a9 }
            if (r6 == 0) goto L_0x0071
            android.content.pm.ApplicationInfo r8 = r6.getApplicationInfo()     // Catch:{ Exception -> 0x00a9 }
            int r8 = r8.targetSdkVersion     // Catch:{ Exception -> 0x00a9 }
            r11 = 24
            if (r8 < r11) goto L_0x0071
            int r8 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x00a9 }
            if (r8 < r11) goto L_0x0071
            r1.addFlags(r10)     // Catch:{ Exception -> 0x00a9 }
        L_0x0071:
            r8 = 268435456(0x10000000, float:2.5243549E-29)
            r1.addFlags(r8)     // Catch:{ Exception -> 0x00a9 }
            android.net.Uri r7 = a((android.content.Context) r6, (java.lang.String) r7)     // Catch:{ Exception -> 0x00a9 }
            if (r7 != 0) goto L_0x007d
            return r0
        L_0x007d:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a9 }
            r8.<init>()     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r11 = "mtt/"
            r8.append(r11)     // Catch:{ Exception -> 0x00a9 }
            r8.append(r9)     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x00a9 }
            r1.setDataAndType(r7, r8)     // Catch:{ Exception -> 0x00a9 }
            java.lang.String r7 = "loginType"
            android.content.Context r8 = r6.getApplicationContext()     // Catch:{ Exception -> 0x00a9 }
            int r8 = a((android.content.Context) r8)     // Catch:{ Exception -> 0x00a9 }
            r1.putExtra(r7, r8)     // Catch:{ Exception -> 0x00a9 }
            if (r12 == 0) goto L_0x00a5
            java.lang.String r7 = "key_reader_sdk_extrals"
            r1.putExtra(r7, r12)     // Catch:{ Exception -> 0x00a9 }
        L_0x00a5:
            r6.startActivity(r1)     // Catch:{ Exception -> 0x00a9 }
            return r10
        L_0x00a9:
            r6 = move-exception
            r6.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.stat.MttLoader.openDocWithQb(android.content.Context, java.lang.String, int, java.lang.String, java.lang.String, java.util.HashMap, android.os.Bundle):boolean");
    }

    public static boolean openDocWithQb(Context context, String str, int i, String str2, HashMap<String, String> hashMap) {
        return openDocWithQb(context, str, i, str2, hashMap, (Bundle) null);
    }

    public static boolean openDocWithQb(Context context, String str, int i, String str2, HashMap<String, String> hashMap, Bundle bundle) {
        return openDocWithQb(context, str, i, str2, "", hashMap, (Bundle) null);
    }

    public static boolean openVideoWithQb(Context context, String str, HashMap<String, String> hashMap) {
        boolean z;
        Set<String> keySet;
        Uri parse = Uri.parse(str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(NTLMConstants.FLAG_UNIDENTIFIED_11);
        intent.setDataAndType(parse, "video/*");
        if (!(hashMap == null || (keySet = hashMap.keySet()) == null)) {
            for (String next : keySet) {
                String str2 = hashMap.get(next);
                if (!TextUtils.isEmpty(str2)) {
                    intent.putExtra(next, str2);
                }
            }
        }
        try {
            intent.putExtra("loginType", a(context));
            intent.setComponent(new ComponentName(TbsConfig.APP_QB, "com.tencent.mtt.browser.video.H5VideoThrdcallActivity"));
            context.startActivity(intent);
            z = true;
        } catch (Throwable unused) {
            z = false;
        }
        if (!z) {
            try {
                intent.setComponent((ComponentName) null);
                context.startActivity(intent);
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:(2:21|22)|23|24|25) */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0061, code lost:
        if (r2 != null) goto L_0x0055;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0033 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x004c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:42:0x0055 */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x005c A[SYNTHETIC, Splitter:B:48:0x005c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean verifySignature(java.io.File r7) {
        /*
            r0 = 0
            r1 = 0
            java.util.jar.JarFile r2 = new java.util.jar.JarFile     // Catch:{ all -> 0x0059 }
            r2.<init>(r7)     // Catch:{ all -> 0x0059 }
            java.lang.String r7 = "AndroidManifest.xml"
            java.util.jar.JarEntry r7 = r2.getJarEntry(r7)     // Catch:{ all -> 0x005a }
            if (r7 != 0) goto L_0x0013
            r2.close()     // Catch:{ IOException -> 0x0012 }
        L_0x0012:
            return r1
        L_0x0013:
            r3 = 8192(0x2000, float:1.14794E-41)
            byte[] r4 = new byte[r3]     // Catch:{ all -> 0x005a }
            java.io.InputStream r0 = r2.getInputStream(r7)     // Catch:{ all -> 0x005a }
        L_0x001b:
            int r5 = r0.read(r4, r1, r3)     // Catch:{ all -> 0x005a }
            r6 = -1
            if (r5 == r6) goto L_0x0023
            goto L_0x001b
        L_0x0023:
            r0.close()     // Catch:{ all -> 0x005a }
            java.security.cert.Certificate[] r7 = r7.getCertificates()     // Catch:{ all -> 0x005a }
            int r3 = r7.length     // Catch:{ all -> 0x005a }
            r4 = 1
            if (r3 >= r4) goto L_0x0037
            if (r0 == 0) goto L_0x0033
            r0.close()     // Catch:{ IOException -> 0x0033 }
        L_0x0033:
            r2.close()     // Catch:{ IOException -> 0x0036 }
        L_0x0036:
            return r1
        L_0x0037:
            r7 = r7[r1]     // Catch:{ all -> 0x005a }
            java.lang.String r7 = a((java.security.cert.Certificate) r7)     // Catch:{ all -> 0x005a }
            if (r7 == 0) goto L_0x0050
            java.lang.String r3 = "3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a"
            boolean r7 = r7.equals(r3)     // Catch:{ all -> 0x005a }
            if (r7 == 0) goto L_0x0050
            if (r0 == 0) goto L_0x004c
            r0.close()     // Catch:{ IOException -> 0x004c }
        L_0x004c:
            r2.close()     // Catch:{ IOException -> 0x004f }
        L_0x004f:
            return r4
        L_0x0050:
            if (r0 == 0) goto L_0x0055
            r0.close()     // Catch:{ IOException -> 0x0055 }
        L_0x0055:
            r2.close()     // Catch:{ IOException -> 0x0064 }
            goto L_0x0064
        L_0x0059:
            r2 = r0
        L_0x005a:
            if (r0 == 0) goto L_0x0061
            r0.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x0061
        L_0x0060:
        L_0x0061:
            if (r2 == 0) goto L_0x0064
            goto L_0x0055
        L_0x0064:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.stat.MttLoader.verifySignature(java.io.File):boolean");
    }
}
