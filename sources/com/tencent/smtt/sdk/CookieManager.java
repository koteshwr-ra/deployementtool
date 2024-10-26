package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.tencent.smtt.utils.i;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class CookieManager {
    public static String LOGTAG = "CookieManager";
    private static CookieManager d;
    CopyOnWriteArrayList<b> a;
    String b;
    a c = a.MODE_NONE;
    private boolean e = false;
    private boolean f = false;

    public enum a {
        MODE_NONE,
        MODE_KEYS,
        MODE_ALL
    }

    class b {
        int a;
        String b;
        String c;
        ValueCallback<Boolean> d;

        b() {
        }
    }

    private CookieManager() {
    }

    public static CookieManager getInstance() {
        if (d == null) {
            synchronized (CookieManager.class) {
                if (d == null) {
                    d = new CookieManager();
                }
            }
        }
        return d;
    }

    public static int getROMCookieDBVersion(Context context) {
        return context.getSharedPreferences("cookiedb_info", Build.VERSION.SDK_INT >= 11 ? 4 : 0).getInt("db_version", -1);
    }

    public static void setROMCookieDBVersion(Context context, int i) {
        SharedPreferences.Editor edit = context.getSharedPreferences("cookiedb_info", Build.VERSION.SDK_INT >= 11 ? 4 : 0).edit();
        edit.putInt("db_version", i);
        edit.commit();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a5, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a() {
        /*
            r10 = this;
            monitor-enter(r10)
            r0 = 1
            r10.f = r0     // Catch:{ all -> 0x00a6 }
            java.util.concurrent.CopyOnWriteArrayList<com.tencent.smtt.sdk.CookieManager$b> r1 = r10.a     // Catch:{ all -> 0x00a6 }
            if (r1 == 0) goto L_0x00a4
            java.util.concurrent.CopyOnWriteArrayList<com.tencent.smtt.sdk.CookieManager$b> r1 = r10.a     // Catch:{ all -> 0x00a6 }
            int r1 = r1.size()     // Catch:{ all -> 0x00a6 }
            if (r1 != 0) goto L_0x0012
            goto L_0x00a4
        L_0x0012:
            com.tencent.smtt.sdk.w r1 = com.tencent.smtt.sdk.w.a()     // Catch:{ all -> 0x00a6 }
            r2 = 2
            if (r1 == 0) goto L_0x004a
            boolean r1 = r1.b()     // Catch:{ all -> 0x00a6 }
            if (r1 == 0) goto L_0x004a
            java.util.concurrent.CopyOnWriteArrayList<com.tencent.smtt.sdk.CookieManager$b> r1 = r10.a     // Catch:{ all -> 0x00a6 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00a6 }
        L_0x0025:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x00a6 }
            if (r3 == 0) goto L_0x009d
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x00a6 }
            com.tencent.smtt.sdk.CookieManager$b r3 = (com.tencent.smtt.sdk.CookieManager.b) r3     // Catch:{ all -> 0x00a6 }
            int r4 = r3.a     // Catch:{ all -> 0x00a6 }
            if (r4 == r0) goto L_0x0040
            if (r4 == r2) goto L_0x0038
            goto L_0x0025
        L_0x0038:
            java.lang.String r4 = r3.b     // Catch:{ all -> 0x00a6 }
            java.lang.String r3 = r3.c     // Catch:{ all -> 0x00a6 }
            r10.setCookie(r4, r3)     // Catch:{ all -> 0x00a6 }
            goto L_0x0025
        L_0x0040:
            java.lang.String r4 = r3.b     // Catch:{ all -> 0x00a6 }
            java.lang.String r5 = r3.c     // Catch:{ all -> 0x00a6 }
            com.tencent.smtt.sdk.ValueCallback<java.lang.Boolean> r3 = r3.d     // Catch:{ all -> 0x00a6 }
            r10.setCookie((java.lang.String) r4, (java.lang.String) r5, (com.tencent.smtt.sdk.ValueCallback<java.lang.Boolean>) r3)     // Catch:{ all -> 0x00a6 }
            goto L_0x0025
        L_0x004a:
            java.util.concurrent.CopyOnWriteArrayList<com.tencent.smtt.sdk.CookieManager$b> r1 = r10.a     // Catch:{ all -> 0x00a6 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00a6 }
        L_0x0050:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x00a6 }
            if (r3 == 0) goto L_0x009d
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x00a6 }
            com.tencent.smtt.sdk.CookieManager$b r3 = (com.tencent.smtt.sdk.CookieManager.b) r3     // Catch:{ all -> 0x00a6 }
            int r4 = r3.a     // Catch:{ all -> 0x00a6 }
            if (r4 == r0) goto L_0x006f
            if (r4 == r2) goto L_0x0063
            goto L_0x0050
        L_0x0063:
            android.webkit.CookieManager r4 = android.webkit.CookieManager.getInstance()     // Catch:{ all -> 0x00a6 }
            java.lang.String r5 = r3.b     // Catch:{ all -> 0x00a6 }
            java.lang.String r3 = r3.c     // Catch:{ all -> 0x00a6 }
            r4.setCookie(r5, r3)     // Catch:{ all -> 0x00a6 }
            goto L_0x0050
        L_0x006f:
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x00a6 }
            r5 = 21
            if (r4 < r5) goto L_0x0050
            android.webkit.CookieManager r4 = android.webkit.CookieManager.getInstance()     // Catch:{ all -> 0x00a6 }
            java.lang.String r5 = "setCookie"
            r6 = 3
            java.lang.Class[] r7 = new java.lang.Class[r6]     // Catch:{ all -> 0x00a6 }
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            r9 = 0
            r7[r9] = r8     // Catch:{ all -> 0x00a6 }
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            r7[r0] = r8     // Catch:{ all -> 0x00a6 }
            java.lang.Class<android.webkit.ValueCallback> r8 = android.webkit.ValueCallback.class
            r7[r2] = r8     // Catch:{ all -> 0x00a6 }
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ all -> 0x00a6 }
            java.lang.String r8 = r3.b     // Catch:{ all -> 0x00a6 }
            r6[r9] = r8     // Catch:{ all -> 0x00a6 }
            java.lang.String r8 = r3.c     // Catch:{ all -> 0x00a6 }
            r6[r0] = r8     // Catch:{ all -> 0x00a6 }
            com.tencent.smtt.sdk.ValueCallback<java.lang.Boolean> r3 = r3.d     // Catch:{ all -> 0x00a6 }
            r6[r2] = r3     // Catch:{ all -> 0x00a6 }
            com.tencent.smtt.utils.i.a((java.lang.Object) r4, (java.lang.String) r5, (java.lang.Class<?>[]) r7, (java.lang.Object[]) r6)     // Catch:{ all -> 0x00a6 }
            goto L_0x0050
        L_0x009d:
            java.util.concurrent.CopyOnWriteArrayList<com.tencent.smtt.sdk.CookieManager$b> r0 = r10.a     // Catch:{ all -> 0x00a6 }
            r0.clear()     // Catch:{ all -> 0x00a6 }
            monitor-exit(r10)
            return
        L_0x00a4:
            monitor-exit(r10)
            return
        L_0x00a6:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.CookieManager.a():void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v18, resolved type: int} */
    /* JADX WARNING: type inference failed for: r6v9 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0150, code lost:
        return;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ed A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0100  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.content.Context r10, boolean r11, boolean r12) {
        /*
            r9 = this;
            monitor-enter(r9)
            com.tencent.smtt.sdk.CookieManager$a r0 = r9.c     // Catch:{ all -> 0x0151 }
            com.tencent.smtt.sdk.CookieManager$a r1 = com.tencent.smtt.sdk.CookieManager.a.MODE_NONE     // Catch:{ all -> 0x0151 }
            if (r0 == r1) goto L_0x014f
            if (r10 == 0) goto L_0x014f
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r0 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ all -> 0x0151 }
            java.lang.String r1 = "cookie_switch.txt"
            boolean r0 = r0.canUseFunction(r10, r1)     // Catch:{ all -> 0x0151 }
            if (r0 == 0) goto L_0x014f
            boolean r0 = r9.e     // Catch:{ all -> 0x0151 }
            if (r0 == 0) goto L_0x001b
            goto L_0x014f
        L_0x001b:
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0151 }
            r2 = 0
            java.lang.String r4 = LOGTAG     // Catch:{ all -> 0x0151 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0151 }
            r5.<init>()     // Catch:{ all -> 0x0151 }
            java.lang.String r6 = "compatiableCookieDatabaseIfNeed,isX5Inited:"
            r5.append(r6)     // Catch:{ all -> 0x0151 }
            r5.append(r11)     // Catch:{ all -> 0x0151 }
            java.lang.String r6 = ",useX5:"
            r5.append(r6)     // Catch:{ all -> 0x0151 }
            r5.append(r12)     // Catch:{ all -> 0x0151 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0151 }
            com.tencent.smtt.utils.TbsLog.i(r4, r5)     // Catch:{ all -> 0x0151 }
            if (r11 != 0) goto L_0x0055
            boolean r11 = com.tencent.smtt.sdk.QbSdk.getIsSysWebViewForcedByOuter()     // Catch:{ all -> 0x0151 }
            if (r11 != 0) goto L_0x0055
            boolean r11 = com.tencent.smtt.sdk.QbSdk.a     // Catch:{ all -> 0x0151 }
            if (r11 == 0) goto L_0x004c
            goto L_0x0055
        L_0x004c:
            com.tencent.smtt.sdk.w r11 = com.tencent.smtt.sdk.w.a()     // Catch:{ all -> 0x0151 }
            r11.a((android.content.Context) r10)     // Catch:{ all -> 0x0151 }
            monitor-exit(r9)
            return
        L_0x0055:
            boolean r11 = com.tencent.smtt.sdk.QbSdk.getIsSysWebViewForcedByOuter()     // Catch:{ all -> 0x0151 }
            r4 = 0
            if (r11 != 0) goto L_0x0060
            boolean r11 = com.tencent.smtt.sdk.QbSdk.a     // Catch:{ all -> 0x0151 }
            if (r11 == 0) goto L_0x0061
        L_0x0060:
            r12 = 0
        L_0x0061:
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r11 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ all -> 0x0151 }
            java.lang.String r5 = "usex5.txt"
            boolean r11 = r11.canUseFunction(r10, r5)     // Catch:{ all -> 0x0151 }
            java.lang.String r5 = LOGTAG     // Catch:{ all -> 0x0151 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0151 }
            r6.<init>()     // Catch:{ all -> 0x0151 }
            java.lang.String r7 = "usex5 : mUseX5LastProcess->"
            r6.append(r7)     // Catch:{ all -> 0x0151 }
            r6.append(r11)     // Catch:{ all -> 0x0151 }
            java.lang.String r7 = ",useX5:"
            r6.append(r7)     // Catch:{ all -> 0x0151 }
            r6.append(r12)     // Catch:{ all -> 0x0151 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0151 }
            com.tencent.smtt.utils.TbsLog.i(r5, r6)     // Catch:{ all -> 0x0151 }
            com.tencent.smtt.sdk.TbsExtensionFunctionManager r5 = com.tencent.smtt.sdk.TbsExtensionFunctionManager.getInstance()     // Catch:{ all -> 0x0151 }
            java.lang.String r6 = "usex5.txt"
            r5.setFunctionEnable(r10, r6, r12)     // Catch:{ all -> 0x0151 }
            if (r11 != r12) goto L_0x0096
            monitor-exit(r9)
            return
        L_0x0096:
            com.tencent.smtt.sdk.TbsLogReport r5 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r10)     // Catch:{ all -> 0x0151 }
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r5 = r5.tbsLogInfo()     // Catch:{ all -> 0x0151 }
            java.lang.String r6 = r9.b     // Catch:{ all -> 0x0151 }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x0151 }
            if (r6 != 0) goto L_0x0112
            com.tencent.smtt.sdk.o r6 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x0151 }
            int r6 = r6.j(r10)     // Catch:{ all -> 0x0151 }
            if (r6 <= 0) goto L_0x00bf
            com.tencent.smtt.sdk.o r6 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x0151 }
            int r6 = r6.j(r10)     // Catch:{ all -> 0x0151 }
            r7 = 36001(0x8ca1, float:5.0448E-41)
            if (r6 >= r7) goto L_0x00bf
            monitor-exit(r9)
            return
        L_0x00bf:
            if (r11 == 0) goto L_0x00cf
            int r6 = com.tencent.smtt.sdk.j.d(r10)     // Catch:{ all -> 0x0151 }
            if (r6 <= 0) goto L_0x00ea
            int r7 = getROMCookieDBVersion(r10)     // Catch:{ all -> 0x0151 }
            if (r7 > 0) goto L_0x00eb
            r4 = 1
            goto L_0x00eb
        L_0x00cf:
            int r6 = com.tencent.smtt.sdk.j.d(r10)     // Catch:{ all -> 0x0151 }
            if (r6 <= 0) goto L_0x00ea
            com.tencent.smtt.sdk.o r7 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x0151 }
            java.lang.String r8 = "cookies_database_version"
            java.lang.String r7 = r7.d((android.content.Context) r10, (java.lang.String) r8)     // Catch:{ all -> 0x0151 }
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x0151 }
            if (r8 != 0) goto L_0x00ea
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ Exception -> 0x00ea }
            goto L_0x00eb
        L_0x00ea:
            r7 = 0
        L_0x00eb:
            if (r4 != 0) goto L_0x00f7
            if (r6 <= 0) goto L_0x00f1
            if (r7 > 0) goto L_0x00f7
        L_0x00f1:
            r12 = 702(0x2be, float:9.84E-43)
            r5.setErrorCode(r12)     // Catch:{ all -> 0x0151 }
            goto L_0x00fe
        L_0x00f7:
            if (r7 < r6) goto L_0x0100
            r12 = 703(0x2bf, float:9.85E-43)
            r5.setErrorCode(r12)     // Catch:{ all -> 0x0151 }
        L_0x00fe:
            r4 = r6
            goto L_0x0118
        L_0x0100:
            com.tencent.smtt.sdk.CookieManager$a r2 = r9.c     // Catch:{ all -> 0x0151 }
            java.lang.String r3 = r9.b     // Catch:{ all -> 0x0151 }
            com.tencent.smtt.sdk.j.a(r10, r2, r3, r4, r12)     // Catch:{ all -> 0x0151 }
            r12 = 704(0x2c0, float:9.87E-43)
            r5.setErrorCode(r12)     // Catch:{ all -> 0x0151 }
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0151 }
            long r2 = r2 - r0
            goto L_0x00fe
        L_0x0112:
            r12 = 701(0x2bd, float:9.82E-43)
            r5.setErrorCode(r12)     // Catch:{ all -> 0x0151 }
            r7 = 0
        L_0x0118:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0151 }
            r12.<init>()     // Catch:{ all -> 0x0151 }
            java.lang.String r0 = "x5->sys:"
            r12.append(r0)     // Catch:{ all -> 0x0151 }
            r12.append(r11)     // Catch:{ all -> 0x0151 }
            java.lang.String r11 = " from:"
            r12.append(r11)     // Catch:{ all -> 0x0151 }
            r12.append(r4)     // Catch:{ all -> 0x0151 }
            java.lang.String r11 = " to:"
            r12.append(r11)     // Catch:{ all -> 0x0151 }
            r12.append(r7)     // Catch:{ all -> 0x0151 }
            java.lang.String r11 = ",timeused:"
            r12.append(r11)     // Catch:{ all -> 0x0151 }
            r12.append(r2)     // Catch:{ all -> 0x0151 }
            java.lang.String r11 = r12.toString()     // Catch:{ all -> 0x0151 }
            r5.setFailDetail((java.lang.String) r11)     // Catch:{ all -> 0x0151 }
            com.tencent.smtt.sdk.TbsLogReport r10 = com.tencent.smtt.sdk.TbsLogReport.getInstance(r10)     // Catch:{ all -> 0x0151 }
            com.tencent.smtt.sdk.TbsLogReport$EventType r11 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_COOKIE_DB_SWITCH     // Catch:{ all -> 0x0151 }
            r10.eventReport(r11, r5)     // Catch:{ all -> 0x0151 }
            monitor-exit(r9)
            return
        L_0x014f:
            monitor-exit(r9)
            return
        L_0x0151:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.CookieManager.a(android.content.Context, boolean, boolean):void");
    }

    public boolean acceptCookie() {
        w a2 = w.a();
        return (a2 == null || !a2.b()) ? android.webkit.CookieManager.getInstance().acceptCookie() : a2.c().d();
    }

    public synchronized boolean acceptThirdPartyCookies(WebView webView) {
        w a2 = w.a();
        if (a2 != null && a2.b()) {
            Object invokeStaticMethod = a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_acceptThirdPartyCookies", new Class[]{Object.class}, webView.getView());
            if (invokeStaticMethod == null) {
                return true;
            }
            return ((Boolean) invokeStaticMethod).booleanValue();
        } else if (Build.VERSION.SDK_INT < 21) {
            return true;
        } else {
            Object a3 = i.a((Object) android.webkit.CookieManager.getInstance(), "acceptThirdPartyCookies", (Class<?>[]) new Class[]{WebView.class}, webView.getView());
            if (a3 == null) {
                return false;
            }
            return ((Boolean) a3).booleanValue();
        }
    }

    public void flush() {
        w a2 = w.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_flush", new Class[0], new Object[0]);
        } else if (Build.VERSION.SDK_INT >= 21) {
            i.a((Object) android.webkit.CookieManager.getInstance(), "flush", (Class<?>[]) new Class[0], new Object[0]);
        }
    }

    public String getCookie(String str) {
        w a2 = w.a();
        if (a2 != null && a2.b()) {
            return a2.c().a(str);
        }
        try {
            return android.webkit.CookieManager.getInstance().getCookie(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public boolean hasCookies() {
        w a2 = w.a();
        return (a2 == null || !a2.b()) ? android.webkit.CookieManager.getInstance().hasCookies() : a2.c().h();
    }

    @Deprecated
    public void removeAllCookie() {
        CopyOnWriteArrayList<b> copyOnWriteArrayList = this.a;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.clear();
        }
        w a2 = w.a();
        if (a2 == null || !a2.b()) {
            android.webkit.CookieManager.getInstance().removeAllCookie();
        } else {
            a2.c().e();
        }
    }

    public void removeAllCookies(ValueCallback<Boolean> valueCallback) {
        CopyOnWriteArrayList<b> copyOnWriteArrayList = this.a;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.clear();
        }
        w a2 = w.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeAllCookies", new Class[]{ValueCallback.class}, valueCallback);
        } else if (Build.VERSION.SDK_INT >= 21) {
            i.a((Object) android.webkit.CookieManager.getInstance(), "removeAllCookies", (Class<?>[]) new Class[]{ValueCallback.class}, valueCallback);
        }
    }

    @Deprecated
    public void removeExpiredCookie() {
        w a2 = w.a();
        if (a2 == null || !a2.b()) {
            android.webkit.CookieManager.getInstance().removeExpiredCookie();
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeExpiredCookie", new Class[0], new Object[0]);
        }
    }

    @Deprecated
    public void removeSessionCookie() {
        w a2 = w.a();
        if (a2 == null || !a2.b()) {
            android.webkit.CookieManager.getInstance().removeSessionCookie();
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookie", new Class[0], new Object[0]);
        }
    }

    public void removeSessionCookies(ValueCallback<Boolean> valueCallback) {
        w a2 = w.a();
        if (a2 != null && a2.b()) {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookies", new Class[]{ValueCallback.class}, valueCallback);
        } else if (Build.VERSION.SDK_INT >= 21) {
            i.a((Object) android.webkit.CookieManager.getInstance(), "removeSessionCookies", (Class<?>[]) new Class[]{ValueCallback.class}, valueCallback);
        }
    }

    public synchronized void setAcceptCookie(boolean z) {
        w a2 = w.a();
        if (a2 == null || !a2.b()) {
            try {
                android.webkit.CookieManager.getInstance().setAcceptCookie(z);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptCookie", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
        return;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0062, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setAcceptThirdPartyCookies(com.tencent.smtt.sdk.WebView r9, boolean r10) {
        /*
            r8 = this;
            monitor-enter(r8)
            com.tencent.smtt.sdk.w r0 = com.tencent.smtt.sdk.w.a()     // Catch:{ all -> 0x0063 }
            r1 = 1
            r2 = 0
            r3 = 2
            if (r0 == 0) goto L_0x0038
            boolean r4 = r0.b()     // Catch:{ all -> 0x0063 }
            if (r4 == 0) goto L_0x0038
            com.tencent.smtt.sdk.x r0 = r0.c()     // Catch:{ all -> 0x0063 }
            com.tencent.smtt.export.external.DexLoader r0 = r0.b()     // Catch:{ all -> 0x0063 }
            java.lang.String r4 = "com.tencent.tbs.tbsshell.WebCoreProxy"
            java.lang.String r5 = "cookieManager_setAcceptThirdPartyCookies"
            java.lang.Class[] r6 = new java.lang.Class[r3]     // Catch:{ all -> 0x0063 }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r6[r2] = r7     // Catch:{ all -> 0x0063 }
            java.lang.Class r7 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x0063 }
            r6[r1] = r7     // Catch:{ all -> 0x0063 }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0063 }
            android.view.View r9 = r9.getView()     // Catch:{ all -> 0x0063 }
            r3[r2] = r9     // Catch:{ all -> 0x0063 }
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r10)     // Catch:{ all -> 0x0063 }
            r3[r1] = r9     // Catch:{ all -> 0x0063 }
            r0.invokeStaticMethod(r4, r5, r6, r3)     // Catch:{ all -> 0x0063 }
            goto L_0x0061
        L_0x0038:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0063 }
            r4 = 21
            if (r0 >= r4) goto L_0x0040
            monitor-exit(r8)
            return
        L_0x0040:
            android.webkit.CookieManager r0 = android.webkit.CookieManager.getInstance()     // Catch:{ all -> 0x0063 }
            java.lang.String r4 = "setAcceptThirdPartyCookies"
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch:{ all -> 0x0063 }
            java.lang.Class<android.webkit.WebView> r6 = android.webkit.WebView.class
            r5[r2] = r6     // Catch:{ all -> 0x0063 }
            java.lang.Class r6 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x0063 }
            r5[r1] = r6     // Catch:{ all -> 0x0063 }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0063 }
            android.view.View r9 = r9.getView()     // Catch:{ all -> 0x0063 }
            r3[r2] = r9     // Catch:{ all -> 0x0063 }
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r10)     // Catch:{ all -> 0x0063 }
            r3[r1] = r9     // Catch:{ all -> 0x0063 }
            com.tencent.smtt.utils.i.a((java.lang.Object) r0, (java.lang.String) r4, (java.lang.Class<?>[]) r5, (java.lang.Object[]) r3)     // Catch:{ all -> 0x0063 }
        L_0x0061:
            monitor-exit(r8)
            return
        L_0x0063:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.CookieManager.setAcceptThirdPartyCookies(com.tencent.smtt.sdk.WebView, boolean):void");
    }

    public synchronized void setCookie(String str, String str2) {
        setCookie(str, str2, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setCookie(java.lang.String r10, java.lang.String r11, com.tencent.smtt.sdk.ValueCallback<java.lang.Boolean> r12) {
        /*
            r9 = this;
            monitor-enter(r9)
            com.tencent.smtt.sdk.w r0 = com.tencent.smtt.sdk.w.a()     // Catch:{ all -> 0x008b }
            r1 = 2
            r2 = 0
            r3 = 3
            r4 = 1
            if (r0 == 0) goto L_0x0037
            boolean r5 = r0.b()     // Catch:{ all -> 0x008b }
            if (r5 == 0) goto L_0x0037
            com.tencent.smtt.sdk.x r0 = r0.c()     // Catch:{ all -> 0x008b }
            com.tencent.smtt.export.external.DexLoader r0 = r0.b()     // Catch:{ all -> 0x008b }
            java.lang.String r5 = "com.tencent.tbs.tbsshell.WebCoreProxy"
            java.lang.String r6 = "cookieManager_setCookie"
            java.lang.Class[] r7 = new java.lang.Class[r3]     // Catch:{ all -> 0x008b }
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            r7[r2] = r8     // Catch:{ all -> 0x008b }
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            r7[r4] = r8     // Catch:{ all -> 0x008b }
            java.lang.Class<android.webkit.ValueCallback> r8 = android.webkit.ValueCallback.class
            r7[r1] = r8     // Catch:{ all -> 0x008b }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x008b }
            r3[r2] = r10     // Catch:{ all -> 0x008b }
            r3[r4] = r11     // Catch:{ all -> 0x008b }
            r3[r1] = r12     // Catch:{ all -> 0x008b }
            r0.invokeStaticMethod(r5, r6, r7, r3)     // Catch:{ all -> 0x008b }
            goto L_0x0089
        L_0x0037:
            com.tencent.smtt.sdk.w r0 = com.tencent.smtt.sdk.w.a()     // Catch:{ all -> 0x008b }
            boolean r0 = r0.d()     // Catch:{ all -> 0x008b }
            if (r0 != 0) goto L_0x005e
            com.tencent.smtt.sdk.CookieManager$b r0 = new com.tencent.smtt.sdk.CookieManager$b     // Catch:{ all -> 0x008b }
            r0.<init>()     // Catch:{ all -> 0x008b }
            r0.a = r4     // Catch:{ all -> 0x008b }
            r0.b = r10     // Catch:{ all -> 0x008b }
            r0.c = r11     // Catch:{ all -> 0x008b }
            r0.d = r12     // Catch:{ all -> 0x008b }
            java.util.concurrent.CopyOnWriteArrayList<com.tencent.smtt.sdk.CookieManager$b> r5 = r9.a     // Catch:{ all -> 0x008b }
            if (r5 != 0) goto L_0x0059
            java.util.concurrent.CopyOnWriteArrayList r5 = new java.util.concurrent.CopyOnWriteArrayList     // Catch:{ all -> 0x008b }
            r5.<init>()     // Catch:{ all -> 0x008b }
            r9.a = r5     // Catch:{ all -> 0x008b }
        L_0x0059:
            java.util.concurrent.CopyOnWriteArrayList<com.tencent.smtt.sdk.CookieManager$b> r5 = r9.a     // Catch:{ all -> 0x008b }
            r5.add(r0)     // Catch:{ all -> 0x008b }
        L_0x005e:
            boolean r0 = r9.f     // Catch:{ all -> 0x008b }
            if (r0 == 0) goto L_0x0089
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x008b }
            r5 = 21
            if (r0 >= r5) goto L_0x006a
            monitor-exit(r9)
            return
        L_0x006a:
            android.webkit.CookieManager r0 = android.webkit.CookieManager.getInstance()     // Catch:{ all -> 0x008b }
            java.lang.String r5 = "setCookie"
            java.lang.Class[] r6 = new java.lang.Class[r3]     // Catch:{ all -> 0x008b }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r2] = r7     // Catch:{ all -> 0x008b }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r4] = r7     // Catch:{ all -> 0x008b }
            java.lang.Class<android.webkit.ValueCallback> r7 = android.webkit.ValueCallback.class
            r6[r1] = r7     // Catch:{ all -> 0x008b }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x008b }
            r3[r2] = r10     // Catch:{ all -> 0x008b }
            r3[r4] = r11     // Catch:{ all -> 0x008b }
            r3[r1] = r12     // Catch:{ all -> 0x008b }
            com.tencent.smtt.utils.i.a((java.lang.Object) r0, (java.lang.String) r5, (java.lang.Class<?>[]) r6, (java.lang.Object[]) r3)     // Catch:{ all -> 0x008b }
        L_0x0089:
            monitor-exit(r9)
            return
        L_0x008b:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.CookieManager.setCookie(java.lang.String, java.lang.String, com.tencent.smtt.sdk.ValueCallback):void");
    }

    public synchronized void setCookie(String str, String str2, boolean z) {
        w a2 = w.a();
        if (a2 == null || !a2.b()) {
            if (this.f || z) {
                android.webkit.CookieManager.getInstance().setCookie(str, str2);
            }
            if (!w.a().d()) {
                b bVar = new b();
                bVar.a = 2;
                bVar.b = str;
                bVar.c = str2;
                bVar.d = null;
                if (this.a == null) {
                    this.a = new CopyOnWriteArrayList<>();
                }
                this.a.add(bVar);
            }
        } else {
            a2.c().b().invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class}, str, str2);
        }
    }

    public boolean setCookieCompatialbeMode(Context context, a aVar, String str, boolean z) {
        System.currentTimeMillis();
        if (context == null || !TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.COOKIE_SWITCH_FILE_NAME)) {
            return false;
        }
        this.c = aVar;
        if (str != null) {
            this.b = str;
        }
        if (this.c == a.MODE_NONE || !z || w.a().d()) {
            return true;
        }
        w.a().a(context);
        return true;
    }

    public void setCookies(Map<String, String[]> map) {
        w a2 = w.a();
        if (!((a2 == null || !a2.b()) ? false : a2.c().a(map))) {
            for (String next : map.keySet()) {
                for (String cookie : map.get(next)) {
                    setCookie(next, cookie);
                }
            }
        }
    }
}
