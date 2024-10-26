package com.tencent.smtt.sdk;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.net.http.SslCertificate;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.alibaba.fastjson.asm.Opcodes;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebSettingsExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.export.external.extension.proxy.X5ProxyWebViewClientExtension;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.stat.MttLoader;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.d;
import com.tencent.smtt.utils.i;
import com.tencent.smtt.utils.n;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class WebView extends FrameLayout implements View.OnLongClickListener {
    public static final int GETPVERROR = -1;
    public static int NIGHT_MODE_ALPHA = Opcodes.IFEQ;
    public static final int NIGHT_MODE_COLOR = -16777216;
    public static final int NORMAL_MODE_ALPHA = 255;
    public static final String SCHEME_GEO = "geo:0,0?q=";
    public static final String SCHEME_MAILTO = "mailto:";
    public static final String SCHEME_TEL = "tel:";
    private static final Lock c = new ReentrantLock();
    private static OutputStream d = null;
    /* access modifiers changed from: private */
    public static Context j = null;
    private static n l = null;
    private static Method m = null;
    public static boolean mSysWebviewCreated = false;
    public static boolean mWebViewCreated = false;
    private static String p = null;
    /* access modifiers changed from: private */
    public static Paint v = null;
    /* access modifiers changed from: private */
    public static boolean w = true;
    volatile int a;
    private final String b;
    /* access modifiers changed from: private */
    public boolean e;
    /* access modifiers changed from: private */
    public IX5WebViewBase f;
    private a g;
    private WebSettings h;
    /* access modifiers changed from: private */
    public Context i;
    /* access modifiers changed from: private */
    public volatile boolean k;
    public WebViewCallbackClient mWebViewCallbackClient;
    private WebViewClient n;
    private WebChromeClient o;
    private final int q;
    private final int r;
    private final int s;
    private final String t;
    private final String u;
    private Object x;
    private View.OnLongClickListener y;

    public static class HitTestResult {
        @Deprecated
        public static final int ANCHOR_TYPE = 1;
        public static final int EDIT_TEXT_TYPE = 9;
        public static final int EMAIL_TYPE = 4;
        public static final int GEO_TYPE = 3;
        @Deprecated
        public static final int IMAGE_ANCHOR_TYPE = 6;
        public static final int IMAGE_TYPE = 5;
        public static final int PHONE_TYPE = 2;
        public static final int SRC_ANCHOR_TYPE = 7;
        public static final int SRC_IMAGE_ANCHOR_TYPE = 8;
        public static final int UNKNOWN_TYPE = 0;
        private IX5WebViewBase.HitTestResult a;
        private WebView.HitTestResult b;

        public HitTestResult() {
            this.b = null;
            this.a = null;
            this.b = null;
        }

        public HitTestResult(WebView.HitTestResult hitTestResult) {
            this.b = null;
            this.a = null;
            this.b = hitTestResult;
        }

        public HitTestResult(IX5WebViewBase.HitTestResult hitTestResult) {
            this.b = null;
            this.a = hitTestResult;
            this.b = null;
        }

        public String getExtra() {
            IX5WebViewBase.HitTestResult hitTestResult = this.a;
            if (hitTestResult != null) {
                return hitTestResult.getExtra();
            }
            WebView.HitTestResult hitTestResult2 = this.b;
            return hitTestResult2 != null ? hitTestResult2.getExtra() : "";
        }

        public int getType() {
            IX5WebViewBase.HitTestResult hitTestResult = this.a;
            if (hitTestResult != null) {
                return hitTestResult.getType();
            }
            WebView.HitTestResult hitTestResult2 = this.b;
            if (hitTestResult2 != null) {
                return hitTestResult2.getType();
            }
            return 0;
        }
    }

    @Deprecated
    public interface PictureListener {
        @Deprecated
        void onNewPicture(WebView webView, Picture picture);
    }

    public class WebViewTransport {
        private WebView b;

        public WebViewTransport() {
        }

        public synchronized WebView getWebView() {
            return this.b;
        }

        public synchronized void setWebView(WebView webView) {
            this.b = webView;
        }
    }

    private class a extends android.webkit.WebView {
        public a(WebView webView, Context context) {
            this(context, (AttributeSet) null);
        }

        public a(Context context, AttributeSet attributeSet) {
            super(WebView.this.d(context), attributeSet);
            if (!QbSdk.getIsSysWebViewForcedByOuter() || !TbsShareManager.isThirdPartyApp(context)) {
                CookieSyncManager.createInstance(WebView.this.i).startSync();
                try {
                    Method declaredMethod = Class.forName("android.webkit.WebViewWorker").getDeclaredMethod("getHandler", new Class[0]);
                    declaredMethod.setAccessible(true);
                    ((Handler) declaredMethod.invoke((Object) null, new Object[0])).getLooper().getThread().setUncaughtExceptionHandler(new g());
                    WebView.mSysWebviewCreated = true;
                } catch (Exception unused) {
                }
            }
        }

        public void a() {
            super.computeScroll();
        }

        public void a(int i, int i2, int i3, int i4) {
            super.onScrollChanged(i, i2, i3, i4);
        }

        public void a(int i, int i2, boolean z, boolean z2) {
            if (Build.VERSION.SDK_INT >= 9) {
                super.onOverScrolled(i, i2, z, z2);
            }
        }

        public boolean a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            if (Build.VERSION.SDK_INT >= 9) {
                return super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            }
            return false;
        }

        public boolean a(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        public boolean b(MotionEvent motionEvent) {
            return super.dispatchTouchEvent(motionEvent);
        }

        public boolean c(MotionEvent motionEvent) {
            return super.onInterceptTouchEvent(motionEvent);
        }

        public void computeScroll() {
            if (WebView.this.mWebViewCallbackClient != null) {
                WebView.this.mWebViewCallbackClient.computeScroll(this);
            } else {
                super.computeScroll();
            }
        }

        /* access modifiers changed from: protected */
        public void dispatchDraw(Canvas canvas) {
            try {
                super.dispatchDraw(canvas);
                if (!WebView.w && WebView.v != null) {
                    canvas.save();
                    canvas.drawPaint(WebView.v);
                    canvas.restore();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            return WebView.this.mWebViewCallbackClient != null ? WebView.this.mWebViewCallbackClient.dispatchTouchEvent(motionEvent, this) : super.dispatchTouchEvent(motionEvent);
        }

        public WebSettings getSettings() {
            try {
                return super.getSettings();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public void invalidate() {
            super.invalidate();
            if (WebView.this.mWebViewCallbackClient != null) {
                WebView.this.mWebViewCallbackClient.invalidate();
            }
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            return WebView.this.mWebViewCallbackClient != null ? WebView.this.mWebViewCallbackClient.onInterceptTouchEvent(motionEvent, this) : super.onInterceptTouchEvent(motionEvent);
        }

        public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
            if (WebView.this.mWebViewCallbackClient != null) {
                WebView.this.mWebViewCallbackClient.onOverScrolled(i, i2, z, z2, this);
            } else if (Build.VERSION.SDK_INT >= 9) {
                super.onOverScrolled(i, i2, z, z2);
            }
        }

        /* access modifiers changed from: protected */
        public void onScrollChanged(int i, int i2, int i3, int i4) {
            if (WebView.this.mWebViewCallbackClient != null) {
                WebView.this.mWebViewCallbackClient.onScrollChanged(i, i2, i3, i4, this);
                return;
            }
            super.onScrollChanged(i, i2, i3, i4);
            WebView.this.onScrollChanged(i, i2, i3, i4);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (!hasFocus()) {
                requestFocus();
            }
            if (WebView.this.mWebViewCallbackClient != null) {
                return WebView.this.mWebViewCallbackClient.onTouchEvent(motionEvent, this);
            }
            try {
                return super.onTouchEvent(motionEvent);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        public boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            if (WebView.this.mWebViewCallbackClient != null) {
                return WebView.this.mWebViewCallbackClient.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z, this);
            }
            if (Build.VERSION.SDK_INT >= 9) {
                return super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            }
            return false;
        }

        public void setOverScrollMode(int i) {
            try {
                super.setOverScrollMode(i);
            } catch (Exception unused) {
            }
        }
    }

    public WebView(Context context) {
        this(context, (AttributeSet) null);
    }

    public WebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WebView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, false);
    }

    public WebView(Context context, AttributeSet attributeSet, int i2, Map<String, Object> map, boolean z) {
        super(context, attributeSet, i2);
        this.b = "WebView";
        this.e = false;
        this.h = null;
        this.i = null;
        this.a = 0;
        this.k = false;
        this.n = null;
        this.o = null;
        this.q = 1;
        this.r = 2;
        this.s = 3;
        this.t = "javascript:document.getElementsByTagName('HEAD').item(0).removeChild(document.getElementById('QQBrowserSDKNightMode'));";
        this.u = "javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);";
        this.x = null;
        this.y = null;
        mWebViewCreated = true;
        if (!QbSdk.getIsSysWebViewForcedByOuter() || !TbsShareManager.isThirdPartyApp(context)) {
            if (TbsShareManager.isThirdPartyApp(context)) {
                TbsLog.setWriteLogJIT(true);
            } else {
                TbsLog.setWriteLogJIT(false);
            }
            TbsLog.initIfNeed(context);
            if (context != null) {
                if (l == null) {
                    l = n.a(context);
                }
                if (l.a) {
                    TbsLog.e("WebView", "sys WebView: debug.conf force syswebview", true);
                    QbSdk.a(context, "debug.conf force syswebview!");
                }
                c(context);
                this.i = context;
                if (context != null) {
                    j = context.getApplicationContext();
                }
                if (!this.e || QbSdk.a) {
                    this.f = null;
                    if (TbsShareManager.isThirdPartyApp(this.i)) {
                        this.g = new a(context, attributeSet);
                    } else {
                        this.g = new a(this, context);
                    }
                    TbsLog.i("WebView", "SystemWebView Created Success! #2");
                    CookieManager.getInstance().a(context, true, false);
                    CookieManager.getInstance().a();
                    this.g.setFocusableInTouchMode(true);
                    addView(this.g, new FrameLayout.LayoutParams(-1, -1));
                    setDownloadListener((DownloadListener) null);
                    TbsLog.writeLogToDisk();
                    o.a(context);
                } else {
                    IX5WebViewBase a2 = w.a().a(true).a(context);
                    this.f = a2;
                    if (a2 == null || a2.getView() == null) {
                        TbsLog.e("WebView", "sys WebView: failed to createTBSWebview", true);
                        this.f = null;
                        this.e = false;
                        QbSdk.a(context, "failed to createTBSWebview!");
                        c(context);
                        if (TbsShareManager.isThirdPartyApp(this.i)) {
                            this.g = new a(context, attributeSet);
                        } else {
                            this.g = new a(this, context);
                        }
                        TbsLog.i("WebView", "SystemWebView Created Success! #1");
                        CookieManager.getInstance().a(context, true, false);
                        CookieManager.getInstance().a();
                        this.g.setFocusableInTouchMode(true);
                        addView(this.g, new FrameLayout.LayoutParams(-1, -1));
                        try {
                            if (Build.VERSION.SDK_INT >= 11) {
                                removeJavascriptInterface("searchBoxJavaBridge_");
                                removeJavascriptInterface("accessibility");
                                removeJavascriptInterface("accessibilityTraversal");
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                        TbsLog.writeLogToDisk();
                        o.a(context);
                        return;
                    }
                    TbsLog.i("WebView", "X5 WebView Created Success!!");
                    this.f.getView().setFocusableInTouchMode(true);
                    a(attributeSet);
                    addView(this.f.getView(), new FrameLayout.LayoutParams(-1, -1));
                    this.f.setDownloadListener(new b(this, (DownloadListener) null, this.e));
                    this.f.getX5WebViewExtension().setWebViewClientExtension(new X5ProxyWebViewClientExtension(w.a().a(true).k()) {
                        public void invalidate() {
                        }

                        public void onScrollChanged(int i, int i2, int i3, int i4) {
                            super.onScrollChanged(i, i2, i3, i4);
                            WebView.this.onScrollChanged(i3, i4, i, i2);
                        }
                    });
                }
                try {
                    if (Build.VERSION.SDK_INT >= 11) {
                        removeJavascriptInterface("searchBoxJavaBridge_");
                        removeJavascriptInterface("accessibility");
                        removeJavascriptInterface("accessibilityTraversal");
                    }
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
                if ((TbsConfig.APP_QQ.equals(this.i.getApplicationInfo().packageName) || TbsConfig.APP_WX.equals(this.i.getApplicationInfo().packageName)) && f.a(true).h() && Build.VERSION.SDK_INT >= 11) {
                    setLayerType(1, (Paint) null);
                }
                if (this.f != null) {
                    TbsLog.writeLogToDisk();
                    if (!TbsShareManager.isThirdPartyApp(context)) {
                        int i3 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
                        if (i3 <= 0 || i3 == o.a().i(context) || i3 != o.a().j(context)) {
                            TbsLog.i("WebView", "webview construction #1 deCoupleCoreVersion is " + i3 + " getTbsCoreShareDecoupleCoreVersion is " + o.a().i(context) + " getTbsCoreInstalledVerInNolock is " + o.a().j(context));
                        } else {
                            o.a().o(context);
                        }
                    }
                }
                QbSdk.continueLoadSo(context);
                return;
            }
            throw new IllegalArgumentException("Invalid context argument");
        }
        this.i = context;
        this.f = null;
        this.e = false;
        QbSdk.a(context, "failed to createTBSWebview!");
        this.g = new a(context, attributeSet);
        CookieManager.getInstance().a(context, true, false);
        CookieSyncManager.createInstance(this.i).startSync();
        try {
            Method declaredMethod = Class.forName("android.webkit.WebViewWorker").getDeclaredMethod("getHandler", new Class[0]);
            declaredMethod.setAccessible(true);
            ((Handler) declaredMethod.invoke((Object) null, new Object[0])).getLooper().getThread().setUncaughtExceptionHandler(new g());
            mSysWebviewCreated = true;
        } catch (Exception unused) {
        }
        CookieManager.getInstance().a();
        this.g.setFocusableInTouchMode(true);
        addView(this.g, new FrameLayout.LayoutParams(-1, -1));
        TbsLog.i("WebView", "SystemWebView Created Success! #3");
        TbsLog.e("WebView", "sys WebView: IsSysWebViewForcedByOuter = true", true);
        TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_FORCE_SYSTEM_WEBVIEW_OUTER, new Throwable());
    }

    @Deprecated
    public WebView(Context context, AttributeSet attributeSet, int i2, boolean z) {
        this(context, attributeSet, i2, (Map<String, Object>) null, z);
    }

    public WebView(Context context, boolean z) {
        super(context);
        this.b = "WebView";
        this.e = false;
        this.h = null;
        this.i = null;
        this.a = 0;
        this.k = false;
        this.n = null;
        this.o = null;
        this.q = 1;
        this.r = 2;
        this.s = 3;
        this.t = "javascript:document.getElementsByTagName('HEAD').item(0).removeChild(document.getElementById('QQBrowserSDKNightMode'));";
        this.u = "javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);";
        this.x = null;
        this.y = null;
    }

    private void a(AttributeSet attributeSet) {
        View view;
        if (attributeSet != null) {
            try {
                int attributeCount = attributeSet.getAttributeCount();
                for (int i2 = 0; i2 < attributeCount; i2++) {
                    if (attributeSet.getAttributeName(i2).equalsIgnoreCase("scrollbars")) {
                        int[] intArray = getResources().getIntArray(16842974);
                        int attributeIntValue = attributeSet.getAttributeIntValue(i2, -1);
                        if (attributeIntValue == intArray[1]) {
                            this.f.getView().setVerticalScrollBarEnabled(false);
                            view = this.f.getView();
                        } else if (attributeIntValue == intArray[2]) {
                            this.f.getView().setVerticalScrollBarEnabled(false);
                        } else if (attributeIntValue == intArray[3]) {
                            view = this.f.getView();
                        }
                        view.setHorizontalScrollBarEnabled(false);
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private boolean a(View view) {
        Object a2;
        Context context = this.i;
        if ((context == null || getTbsCoreVersion(context) <= 36200) && (a2 = i.a(this.x, "onLongClick", (Class<?>[]) new Class[]{View.class}, view)) != null) {
            return ((Boolean) a2).booleanValue();
        }
        return false;
    }

    private boolean b(Context context) {
        try {
            return context.getPackageName().indexOf(TbsConfig.APP_QQ) >= 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    static void c() {
        try {
            new Thread(new Runnable() {
                public void run() {
                    if (WebView.j == null) {
                        TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--mAppContext == null");
                        return;
                    }
                    f a = f.a(true);
                    if (f.b) {
                        TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--needReboot = true");
                        return;
                    }
                    m a2 = m.a(WebView.j);
                    int c = a2.c();
                    TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--installStatus = " + c);
                    if (c == 2) {
                        TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--install setTbsNeedReboot true");
                        a.a(String.valueOf(a2.b()));
                        a.b(true);
                        return;
                    }
                    int b = a2.b("copy_status");
                    TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--copyStatus = " + b);
                    if (b == 1) {
                        TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--copy setTbsNeedReboot true");
                        a.a(String.valueOf(a2.c("copy_core_ver")));
                        a.b(true);
                    } else if (w.a().b()) {
                    } else {
                        if (c == 3 || b == 3) {
                            TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--setTbsNeedReboot true");
                            a.a(String.valueOf(f.d()));
                            a.b(true);
                        }
                    }
                }
            }).start();
        } catch (Throwable th) {
            TbsLog.e("webview", "updateRebootStatus excpetion: " + th);
        }
    }

    private void c(Context context) {
        if (QbSdk.i && TbsShareManager.isThirdPartyApp(context)) {
            TbsExtensionFunctionManager.getInstance().initTbsBuglyIfNeed(context);
        }
        w a2 = w.a();
        a2.a(context);
        this.e = a2.b();
    }

    /* access modifiers changed from: private */
    public Context d(Context context) {
        return (Build.VERSION.SDK_INT < 21 || Build.VERSION.SDK_INT > 22) ? context : context.createConfigurationContext(new Configuration());
    }

    @Deprecated
    public static void disablePlatformNotifications() {
        if (!w.a().b()) {
            i.a("android.webkit.WebView", "disablePlatformNotifications");
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00cd A[SYNTHETIC, Splitter:B:40:0x00cd] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00db A[SYNTHETIC, Splitter:B:45:0x00db] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int e(android.content.Context r9) {
        /*
            r8 = this;
            java.lang.String r0 = "TbsInstaller--getTbsCorePV IOException="
            java.lang.String r1 = "getTbsCorePV"
            r2 = 1
            java.lang.String r3 = "tbslock.txt"
            java.io.FileOutputStream r2 = com.tencent.smtt.utils.FileUtil.b(r9, r2, r3)
            r3 = -1
            if (r2 == 0) goto L_0x0102
            java.nio.channels.FileLock r4 = com.tencent.smtt.utils.FileUtil.a((android.content.Context) r9, (java.io.FileOutputStream) r2)
            if (r4 != 0) goto L_0x0015
            return r3
        L_0x0015:
            java.util.concurrent.locks.Lock r5 = c
            boolean r5 = r5.tryLock()
            if (r5 == 0) goto L_0x00ff
            r5 = 0
            java.io.File r9 = com.tencent.smtt.sdk.QbSdk.getTbsFolderDir(r9)     // Catch:{ Exception -> 0x00b2 }
            java.io.File r6 = new java.io.File     // Catch:{ Exception -> 0x00b2 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b2 }
            r7.<init>()     // Catch:{ Exception -> 0x00b2 }
            r7.append(r9)     // Catch:{ Exception -> 0x00b2 }
            java.lang.String r9 = java.io.File.separator     // Catch:{ Exception -> 0x00b2 }
            r7.append(r9)     // Catch:{ Exception -> 0x00b2 }
            java.lang.String r9 = "core_private"
            r7.append(r9)     // Catch:{ Exception -> 0x00b2 }
            java.lang.String r9 = r7.toString()     // Catch:{ Exception -> 0x00b2 }
            java.lang.String r7 = "pv.db"
            r6.<init>(r9, r7)     // Catch:{ Exception -> 0x00b2 }
            boolean r9 = r6.exists()     // Catch:{ Exception -> 0x00b2 }
            if (r9 != 0) goto L_0x004e
        L_0x0045:
            java.util.concurrent.locks.Lock r9 = c
            r9.unlock()
            com.tencent.smtt.utils.FileUtil.a((java.nio.channels.FileLock) r4, (java.io.FileOutputStream) r2)
            return r3
        L_0x004e:
            java.util.Properties r9 = new java.util.Properties     // Catch:{ Exception -> 0x00b2 }
            r9.<init>()     // Catch:{ Exception -> 0x00b2 }
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00b2 }
            r7.<init>(r6)     // Catch:{ Exception -> 0x00b2 }
            r9.load(r7)     // Catch:{ Exception -> 0x00ad, all -> 0x00aa }
            r7.close()     // Catch:{ Exception -> 0x00ad, all -> 0x00aa }
            java.lang.String r5 = "PV"
            java.lang.String r9 = r9.getProperty(r5)     // Catch:{ Exception -> 0x00ad, all -> 0x00aa }
            if (r9 != 0) goto L_0x0082
            r7.close()     // Catch:{ IOException -> 0x006a }
            goto L_0x0045
        L_0x006a:
            r9 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
        L_0x0070:
            r5.append(r0)
            java.lang.String r9 = r9.toString()
            r5.append(r9)
            java.lang.String r9 = r5.toString()
            com.tencent.smtt.utils.TbsLog.e(r1, r9)
            goto L_0x0045
        L_0x0082:
            int r9 = java.lang.Integer.parseInt(r9)     // Catch:{ Exception -> 0x00ad, all -> 0x00aa }
            r7.close()     // Catch:{ IOException -> 0x008a }
            goto L_0x00a1
        L_0x008a:
            r3 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            java.lang.String r0 = r3.toString()
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            com.tencent.smtt.utils.TbsLog.e(r1, r0)
        L_0x00a1:
            java.util.concurrent.locks.Lock r0 = c
            r0.unlock()
            com.tencent.smtt.utils.FileUtil.a((java.nio.channels.FileLock) r4, (java.io.FileOutputStream) r2)
            return r9
        L_0x00aa:
            r9 = move-exception
            r5 = r7
            goto L_0x00d9
        L_0x00ad:
            r9 = move-exception
            r5 = r7
            goto L_0x00b3
        L_0x00b0:
            r9 = move-exception
            goto L_0x00d9
        L_0x00b2:
            r9 = move-exception
        L_0x00b3:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b0 }
            r6.<init>()     // Catch:{ all -> 0x00b0 }
            java.lang.String r7 = "TbsInstaller--getTbsCorePV Exception="
            r6.append(r7)     // Catch:{ all -> 0x00b0 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x00b0 }
            r6.append(r9)     // Catch:{ all -> 0x00b0 }
            java.lang.String r9 = r6.toString()     // Catch:{ all -> 0x00b0 }
            com.tencent.smtt.utils.TbsLog.e(r1, r9)     // Catch:{ all -> 0x00b0 }
            if (r5 == 0) goto L_0x0045
            r5.close()     // Catch:{ IOException -> 0x00d2 }
            goto L_0x0045
        L_0x00d2:
            r9 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            goto L_0x0070
        L_0x00d9:
            if (r5 == 0) goto L_0x00f6
            r5.close()     // Catch:{ IOException -> 0x00df }
            goto L_0x00f6
        L_0x00df:
            r3 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            java.lang.String r0 = r3.toString()
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            com.tencent.smtt.utils.TbsLog.e(r1, r0)
        L_0x00f6:
            java.util.concurrent.locks.Lock r0 = c
            r0.unlock()
            com.tencent.smtt.utils.FileUtil.a((java.nio.channels.FileLock) r4, (java.io.FileOutputStream) r2)
            throw r9
        L_0x00ff:
            com.tencent.smtt.utils.FileUtil.a((java.nio.channels.FileLock) r4, (java.io.FileOutputStream) r2)
        L_0x0102:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebView.e(android.content.Context):int");
    }

    @Deprecated
    public static void enablePlatformNotifications() {
        if (!w.a().b()) {
            i.a("android.webkit.WebView", "enablePlatformNotifications");
        }
    }

    /* access modifiers changed from: private */
    public void f(Context context) {
        try {
            File tbsFolderDir = QbSdk.getTbsFolderDir(context);
            File file = new File(tbsFolderDir + File.separator + "core_private", "pv.db");
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e2) {
            TbsLog.i("getTbsCorePV", "TbsInstaller--getTbsCorePV Exception=" + e2.toString());
        }
    }

    @Deprecated
    public static String findAddress(String str) {
        if (!w.a().b()) {
            return android.webkit.WebView.findAddress(str);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public long g() {
        long j2;
        synchronized (QbSdk.h) {
            if (QbSdk.e) {
                QbSdk.g += System.currentTimeMillis() - QbSdk.f;
                TbsLog.d("sdkreport", "pv report, WebView.getWifiConnectedTime QbSdk.sWifiConnectedTime=" + QbSdk.g);
            }
            j2 = QbSdk.g / 1000;
            QbSdk.g = 0;
            QbSdk.f = System.currentTimeMillis();
        }
        return j2;
    }

    public static String getCrashExtraMessage(Context context) {
        if (context == null) {
            return "";
        }
        String str = "tbs_core_version:" + QbSdk.getTbsVersionForCrash(context) + ";" + "tbs_sdk_version:" + 43993 + ";";
        boolean z = false;
        if (TbsConfig.APP_WX.equals(context.getApplicationInfo().packageName)) {
            try {
                Class.forName("de.robv.android.xposed.XposedBridge");
                z = true;
            } catch (ClassNotFoundException unused) {
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (z) {
            return str + "isXposed=true;";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(f.a(true).e());
        sb.append(StringUtils.LF);
        sb.append(str);
        if (!TbsShareManager.isThirdPartyApp(context) && QbSdk.n != null && QbSdk.n.containsKey(TbsCoreSettings.TBS_SETTINGS_WEAPP_ID_KEY) && QbSdk.n.containsKey(TbsCoreSettings.TBS_SETTINGS_WEAPP_NAME_KEY)) {
            sb.append(StringUtils.LF);
            sb.append("weapp_id:" + QbSdk.n.get(TbsCoreSettings.TBS_SETTINGS_WEAPP_ID_KEY) + ";" + TbsCoreSettings.TBS_SETTINGS_WEAPP_NAME_KEY + ":" + QbSdk.n.get(TbsCoreSettings.TBS_SETTINGS_WEAPP_NAME_KEY) + ";");
        }
        return sb.length() > 8192 ? sb.substring(sb.length() - 8192) : sb.toString();
    }

    public static PackageInfo getCurrentWebViewPackage() {
        if (w.a().b() || Build.VERSION.SDK_INT < 26) {
            return null;
        }
        try {
            return (PackageInfo) i.a("android.webkit.WebView", "getCurrentWebViewPackage");
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Deprecated
    public static synchronized Object getPluginList() {
        synchronized (WebView.class) {
            if (w.a().b()) {
                return null;
            }
            Object a2 = i.a("android.webkit.WebView", "getPluginList");
            return a2;
        }
    }

    public static int getTbsCoreVersion(Context context) {
        return QbSdk.getTbsVersion(context);
    }

    public static boolean getTbsNeedReboot() {
        c();
        return f.a(true).f();
    }

    public static int getTbsSDKVersion(Context context) {
        return 43993;
    }

    private void h() {
        new Thread(new Runnable() {
            /* JADX WARNING: Code restructure failed: missing block: B:35:0x00de, code lost:
                return;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r11 = this;
                    com.tencent.smtt.sdk.WebView r0 = com.tencent.smtt.sdk.WebView.this
                    boolean r0 = r0.k
                    if (r0 != 0) goto L_0x00e2
                    com.tencent.smtt.sdk.WebView r0 = com.tencent.smtt.sdk.WebView.this
                    int r0 = r0.a
                    if (r0 == 0) goto L_0x00e2
                    java.lang.Class<com.tencent.smtt.sdk.WebView> r0 = com.tencent.smtt.sdk.WebView.class
                    monitor-enter(r0)
                    com.tencent.smtt.sdk.WebView r1 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    boolean r1 = r1.k     // Catch:{ all -> 0x00df }
                    if (r1 != 0) goto L_0x00dd
                    com.tencent.smtt.sdk.WebView r1 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    int r1 = r1.a     // Catch:{ all -> 0x00df }
                    if (r1 != 0) goto L_0x0021
                    goto L_0x00dd
                L_0x0021:
                    com.tencent.smtt.sdk.WebView r1 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    r2 = 1
                    boolean unused = r1.k = r2     // Catch:{ all -> 0x00df }
                    java.lang.String r1 = ""
                    java.lang.String r2 = ""
                    java.lang.String r3 = ""
                    com.tencent.smtt.sdk.WebView r4 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    boolean r4 = r4.e     // Catch:{ all -> 0x00df }
                    if (r4 == 0) goto L_0x0057
                    com.tencent.smtt.sdk.WebView r4 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    com.tencent.smtt.export.external.interfaces.IX5WebViewBase r4 = r4.f     // Catch:{ all -> 0x00df }
                    com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension r4 = r4.getX5WebViewExtension()     // Catch:{ all -> 0x00df }
                    android.os.Bundle r4 = r4.getSdkQBStatisticsInfo()     // Catch:{ all -> 0x00df }
                    if (r4 == 0) goto L_0x0057
                    java.lang.String r1 = "guid"
                    java.lang.String r1 = r4.getString(r1)     // Catch:{ all -> 0x00df }
                    java.lang.String r2 = "qua2"
                    java.lang.String r2 = r4.getString(r2)     // Catch:{ all -> 0x00df }
                    java.lang.String r3 = "lc"
                    java.lang.String r3 = r4.getString(r3)     // Catch:{ all -> 0x00df }
                L_0x0057:
                    r4 = r2
                    r5 = r3
                    r3 = r1
                    java.lang.String r1 = "com.qzone"
                    com.tencent.smtt.sdk.WebView r2 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    android.content.Context r2 = r2.i     // Catch:{ all -> 0x00df }
                    android.content.pm.ApplicationInfo r2 = r2.getApplicationInfo()     // Catch:{ all -> 0x00df }
                    java.lang.String r2 = r2.packageName     // Catch:{ all -> 0x00df }
                    boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x00df }
                    if (r1 == 0) goto L_0x0090
                    com.tencent.smtt.sdk.WebView r1 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    com.tencent.smtt.sdk.WebView r2 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    android.content.Context r2 = r2.i     // Catch:{ all -> 0x00df }
                    int r1 = r1.e((android.content.Context) r2)     // Catch:{ all -> 0x00df }
                    com.tencent.smtt.sdk.WebView r2 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    r6 = -1
                    if (r1 != r6) goto L_0x0083
                    com.tencent.smtt.sdk.WebView r1 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    int r1 = r1.a     // Catch:{ all -> 0x00df }
                L_0x0083:
                    r2.a = r1     // Catch:{ all -> 0x00df }
                    com.tencent.smtt.sdk.WebView r1 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    com.tencent.smtt.sdk.WebView r2 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    android.content.Context r2 = r2.i     // Catch:{ all -> 0x00df }
                    r1.f(r2)     // Catch:{ all -> 0x00df }
                L_0x0090:
                    r1 = 0
                    com.tencent.smtt.sdk.WebView r2 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00a1 }
                    com.tencent.smtt.export.external.interfaces.IX5WebViewBase r2 = r2.f     // Catch:{ all -> 0x00a1 }
                    com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension r2 = r2.getX5WebViewExtension()     // Catch:{ all -> 0x00a1 }
                    boolean r2 = r2.isX5CoreSandboxMode()     // Catch:{ all -> 0x00a1 }
                    r10 = r2
                    goto L_0x00b9
                L_0x00a1:
                    r2 = move-exception
                    java.lang.String r6 = "onVisibilityChanged"
                    java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00df }
                    r7.<init>()     // Catch:{ all -> 0x00df }
                    java.lang.String r8 = "exception: "
                    r7.append(r8)     // Catch:{ all -> 0x00df }
                    r7.append(r2)     // Catch:{ all -> 0x00df }
                    java.lang.String r2 = r7.toString()     // Catch:{ all -> 0x00df }
                    com.tencent.smtt.utils.TbsLog.w(r6, r2)     // Catch:{ all -> 0x00df }
                    r10 = 0
                L_0x00b9:
                    com.tencent.smtt.sdk.WebView r2 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    android.content.Context r2 = r2.i     // Catch:{ all -> 0x00df }
                    com.tencent.smtt.sdk.WebView r6 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    int r6 = r6.a     // Catch:{ all -> 0x00df }
                    com.tencent.smtt.sdk.WebView r7 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    boolean r7 = r7.e     // Catch:{ all -> 0x00df }
                    com.tencent.smtt.sdk.WebView r8 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    long r8 = r8.g()     // Catch:{ all -> 0x00df }
                    com.tencent.smtt.sdk.stat.b.a(r2, r3, r4, r5, r6, r7, r8, r10)     // Catch:{ all -> 0x00df }
                    com.tencent.smtt.sdk.WebView r2 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    r2.a = r1     // Catch:{ all -> 0x00df }
                    com.tencent.smtt.sdk.WebView r2 = com.tencent.smtt.sdk.WebView.this     // Catch:{ all -> 0x00df }
                    boolean unused = r2.k = r1     // Catch:{ all -> 0x00df }
                    monitor-exit(r0)     // Catch:{ all -> 0x00df }
                    goto L_0x00e2
                L_0x00dd:
                    monitor-exit(r0)     // Catch:{ all -> 0x00df }
                    return
                L_0x00df:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x00df }
                    throw r1
                L_0x00e2:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebView.AnonymousClass6.run():void");
            }
        }).start();
    }

    public static void setDataDirectorySuffix(String str) {
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                i.a(Class.forName("android.webkit.WebView"), "setDataDirectorySuffix", (Class<?>[]) new Class[]{String.class}, str);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put("data_directory_suffix", str);
        QbSdk.initTbsSettings(hashMap);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void setSysDayOrNight(boolean r3) {
        /*
            java.lang.Class<com.tencent.smtt.sdk.WebView> r0 = com.tencent.smtt.sdk.WebView.class
            monitor-enter(r0)
            boolean r1 = w     // Catch:{ all -> 0x003e }
            if (r3 != r1) goto L_0x0009
            monitor-exit(r0)
            return
        L_0x0009:
            w = r3     // Catch:{ all -> 0x003e }
            android.graphics.Paint r1 = v     // Catch:{ all -> 0x003e }
            if (r1 != 0) goto L_0x001b
            android.graphics.Paint r1 = new android.graphics.Paint     // Catch:{ all -> 0x003e }
            r1.<init>()     // Catch:{ all -> 0x003e }
            v = r1     // Catch:{ all -> 0x003e }
            r2 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r1.setColor(r2)     // Catch:{ all -> 0x003e }
        L_0x001b:
            if (r3 != 0) goto L_0x002f
            android.graphics.Paint r3 = v     // Catch:{ all -> 0x003e }
            int r3 = r3.getAlpha()     // Catch:{ all -> 0x003e }
            int r1 = NIGHT_MODE_ALPHA     // Catch:{ all -> 0x003e }
            if (r3 == r1) goto L_0x003c
            android.graphics.Paint r3 = v     // Catch:{ all -> 0x003e }
            int r1 = NIGHT_MODE_ALPHA     // Catch:{ all -> 0x003e }
        L_0x002b:
            r3.setAlpha(r1)     // Catch:{ all -> 0x003e }
            goto L_0x003c
        L_0x002f:
            android.graphics.Paint r3 = v     // Catch:{ all -> 0x003e }
            int r3 = r3.getAlpha()     // Catch:{ all -> 0x003e }
            r1 = 255(0xff, float:3.57E-43)
            if (r3 == r1) goto L_0x003c
            android.graphics.Paint r3 = v     // Catch:{ all -> 0x003e }
            goto L_0x002b
        L_0x003c:
            monitor-exit(r0)
            return
        L_0x003e:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebView.setSysDayOrNight(boolean):void");
    }

    public static void setWebContentsDebuggingEnabled(boolean z) {
        w a2 = w.a();
        if (a2 != null && a2.b()) {
            a2.c().a(z);
        } else if (Build.VERSION.SDK_INT >= 19) {
            try {
                Method declaredMethod = Class.forName("android.webkit.WebView").getDeclaredMethod("setWebContentsDebuggingEnabled", new Class[]{Boolean.TYPE});
                m = declaredMethod;
                if (declaredMethod != null) {
                    declaredMethod.setAccessible(true);
                    m.invoke((Object) null, new Object[]{Boolean.valueOf(z)});
                }
            } catch (Exception e2) {
                TbsLog.e("QbSdk", "Exception:" + e2.getStackTrace());
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public android.webkit.WebView a() {
        if (!this.e) {
            return this.g;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void a(Context context) {
        String str;
        int e2 = e(context);
        if (e2 != -1) {
            str = "PV=" + String.valueOf(e2 + 1);
        } else {
            str = "PV=1";
        }
        File file = new File(QbSdk.getTbsFolderDir(context) + File.separator + "core_private", "pv.db");
        try {
            file.getParentFile().mkdirs();
            if (!file.isFile() || !file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            d = fileOutputStream;
            fileOutputStream.write(str.getBytes());
            if (d != null) {
                d.flush();
            }
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void a(android.webkit.WebView webView) {
    }

    /* access modifiers changed from: package-private */
    public void a(IX5WebViewBase iX5WebViewBase) {
        this.f = iX5WebViewBase;
    }

    public void addJavascriptInterface(Object obj, String str) {
        if (!this.e) {
            this.g.addJavascriptInterface(obj, str);
        } else {
            this.f.addJavascriptInterface(obj, str);
        }
    }

    public void addView(View view) {
        if (!this.e) {
            this.g.addView(view);
            return;
        }
        View view2 = this.f.getView();
        try {
            Method a2 = i.a(view2, "addView", View.class);
            a2.setAccessible(true);
            a2.invoke(view2, new Object[]{view});
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public IX5WebViewBase b() {
        return this.f;
    }

    public boolean canGoBack() {
        return !this.e ? this.g.canGoBack() : this.f.canGoBack();
    }

    public boolean canGoBackOrForward(int i2) {
        return !this.e ? this.g.canGoBackOrForward(i2) : this.f.canGoBackOrForward(i2);
    }

    public boolean canGoForward() {
        return !this.e ? this.g.canGoForward() : this.f.canGoForward();
    }

    @Deprecated
    public boolean canZoomIn() {
        Object a2;
        if (this.e) {
            return this.f.canZoomIn();
        }
        if (Build.VERSION.SDK_INT < 11 || (a2 = i.a((Object) this.g, "canZoomIn")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    @Deprecated
    public boolean canZoomOut() {
        Object a2;
        if (this.e) {
            return this.f.canZoomOut();
        }
        if (Build.VERSION.SDK_INT < 11 || (a2 = i.a((Object) this.g, "canZoomOut")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    @Deprecated
    public Picture capturePicture() {
        if (this.e) {
            return this.f.capturePicture();
        }
        Object a2 = i.a((Object) this.g, "capturePicture");
        if (a2 == null) {
            return null;
        }
        return (Picture) a2;
    }

    public void clearCache(boolean z) {
        if (!this.e) {
            this.g.clearCache(z);
        } else {
            this.f.clearCache(z);
        }
    }

    public void clearFormData() {
        if (!this.e) {
            this.g.clearFormData();
        } else {
            this.f.clearFormData();
        }
    }

    public void clearHistory() {
        if (!this.e) {
            this.g.clearHistory();
        } else {
            this.f.clearHistory();
        }
    }

    public void clearMatches() {
        if (!this.e) {
            this.g.clearMatches();
        } else {
            this.f.clearMatches();
        }
    }

    public void clearSslPreferences() {
        if (!this.e) {
            this.g.clearSslPreferences();
        } else {
            this.f.clearSslPreferences();
        }
    }

    @Deprecated
    public void clearView() {
        if (!this.e) {
            i.a((Object) this.g, "clearView");
        } else {
            this.f.clearView();
        }
    }

    public int computeHorizontalScrollExtent() {
        try {
            if (this.e) {
                Method a2 = i.a(this.f.getView(), "computeHorizontalScrollExtent", new Class[0]);
                a2.setAccessible(true);
                return ((Integer) a2.invoke(this.f.getView(), new Object[0])).intValue();
            }
            Method a3 = i.a(this.g, "computeHorizontalScrollExtent", new Class[0]);
            a3.setAccessible(true);
            return ((Integer) a3.invoke(this.g, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public int computeHorizontalScrollOffset() {
        try {
            if (this.e) {
                Method a2 = i.a(this.f.getView(), "computeHorizontalScrollOffset", new Class[0]);
                a2.setAccessible(true);
                return ((Integer) a2.invoke(this.f.getView(), new Object[0])).intValue();
            }
            Method a3 = i.a(this.g, "computeHorizontalScrollOffset", new Class[0]);
            a3.setAccessible(true);
            return ((Integer) a3.invoke(this.g, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public int computeHorizontalScrollRange() {
        try {
            if (this.e) {
                return ((Integer) i.a((Object) this.f.getView(), "computeHorizontalScrollRange", (Class<?>[]) new Class[0], new Object[0])).intValue();
            }
            Method a2 = i.a(this.g, "computeHorizontalScrollRange", new Class[0]);
            a2.setAccessible(true);
            return ((Integer) a2.invoke(this.g, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public void computeScroll() {
        if (!this.e) {
            this.g.computeScroll();
        } else {
            this.f.computeScroll();
        }
    }

    public int computeVerticalScrollExtent() {
        try {
            if (this.e) {
                Method a2 = i.a(this.f.getView(), "computeVerticalScrollExtent", new Class[0]);
                a2.setAccessible(true);
                return ((Integer) a2.invoke(this.f.getView(), new Object[0])).intValue();
            }
            Method a3 = i.a(this.g, "computeVerticalScrollExtent", new Class[0]);
            a3.setAccessible(true);
            return ((Integer) a3.invoke(this.g, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public int computeVerticalScrollOffset() {
        try {
            if (this.e) {
                Method a2 = i.a(this.f.getView(), "computeVerticalScrollOffset", new Class[0]);
                a2.setAccessible(true);
                return ((Integer) a2.invoke(this.f.getView(), new Object[0])).intValue();
            }
            Method a3 = i.a(this.g, "computeVerticalScrollOffset", new Class[0]);
            a3.setAccessible(true);
            return ((Integer) a3.invoke(this.g, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public int computeVerticalScrollRange() {
        try {
            if (this.e) {
                return ((Integer) i.a((Object) this.f.getView(), "computeVerticalScrollRange", (Class<?>[]) new Class[0], new Object[0])).intValue();
            }
            Method a2 = i.a(this.g, "computeVerticalScrollRange", new Class[0]);
            a2.setAccessible(true);
            return ((Integer) a2.invoke(this.g, new Object[0])).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public WebBackForwardList copyBackForwardList() {
        return this.e ? WebBackForwardList.a(this.f.copyBackForwardList()) : WebBackForwardList.a(this.g.copyBackForwardList());
    }

    public Object createPrintDocumentAdapter(String str) {
        if (this.e) {
            try {
                return this.f.createPrintDocumentAdapter(str);
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        } else if (Build.VERSION.SDK_INT < 21) {
            return null;
        } else {
            return i.a((Object) this.g, "createPrintDocumentAdapter", (Class<?>[]) new Class[]{String.class}, str);
        }
    }

    public void customDiskCachePathEnabled(boolean z, String str) {
        if (this.e && getX5WebViewExtension() != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("enabled", z);
            bundle.putString("path", str);
            getX5WebViewExtension().invokeMiscMethod("customDiskCachePathEnabled", bundle);
        }
    }

    public void destroy() {
        synchronized (WebView.class) {
            if (!this.k && this.a != 0) {
                h();
            }
        }
        if (!this.e) {
            this.g.destroy();
            try {
                Field declaredField = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
                declaredField.setAccessible(true);
                ComponentCallbacks componentCallbacks = (ComponentCallbacks) declaredField.get((Object) null);
                if (componentCallbacks != null) {
                    declaredField.set((Object) null, (Object) null);
                    Field declaredField2 = Class.forName("android.view.ViewRoot").getDeclaredField("sConfigCallbacks");
                    declaredField2.setAccessible(true);
                    Object obj = declaredField2.get((Object) null);
                    if (obj != null) {
                        List list = (List) obj;
                        synchronized (list) {
                            list.remove(componentCallbacks);
                        }
                    }
                }
            } catch (Exception unused) {
            }
        } else {
            this.f.destroy();
        }
        TbsLog.i("WebView", "X5 GUID = " + QbSdk.b());
    }

    public void documentHasImages(Message message) {
        if (!this.e) {
            this.g.documentHasImages(message);
        } else {
            this.f.documentHasImages(message);
        }
    }

    public void dumpViewHierarchyWithProperties(BufferedWriter bufferedWriter, int i2) {
        if (!this.e) {
            i.a((Object) this.g, "dumpViewHierarchyWithProperties", (Class<?>[]) new Class[]{BufferedWriter.class, Integer.TYPE}, bufferedWriter, Integer.valueOf(i2));
            return;
        }
        this.f.dumpViewHierarchyWithProperties(bufferedWriter, i2);
    }

    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        if (this.e) {
            try {
                Method a2 = i.a(this.f.getView(), "evaluateJavascript", String.class, ValueCallback.class);
                a2.setAccessible(true);
                a2.invoke(this.f.getView(), new Object[]{str, valueCallback});
            } catch (Exception e2) {
                e2.printStackTrace();
                loadUrl(str);
            }
        } else if (Build.VERSION.SDK_INT >= 19) {
            try {
                Method declaredMethod = Class.forName("android.webkit.WebView").getDeclaredMethod("evaluateJavascript", new Class[]{String.class, ValueCallback.class});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(this.g, new Object[]{str, valueCallback});
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    @Deprecated
    public int findAll(String str) {
        if (this.e) {
            return this.f.findAll(str);
        }
        Object a2 = i.a((Object) this.g, "findAll", (Class<?>[]) new Class[]{String.class}, str);
        if (a2 == null) {
            return 0;
        }
        return ((Integer) a2).intValue();
    }

    public void findAllAsync(String str) {
        if (this.e) {
            this.f.findAllAsync(str);
        } else if (Build.VERSION.SDK_INT >= 16) {
            i.a((Object) this.g, "findAllAsync", (Class<?>[]) new Class[]{String.class}, str);
        }
    }

    public View findHierarchyView(String str, int i2) {
        if (this.e) {
            return this.f.findHierarchyView(str, i2);
        }
        return (View) i.a((Object) this.g, "findHierarchyView", (Class<?>[]) new Class[]{String.class, Integer.TYPE}, str, Integer.valueOf(i2));
    }

    public void findNext(boolean z) {
        if (!this.e) {
            this.g.findNext(z);
        } else {
            this.f.findNext(z);
        }
    }

    public void flingScroll(int i2, int i3) {
        if (!this.e) {
            this.g.flingScroll(i2, i3);
        } else {
            this.f.flingScroll(i2, i3);
        }
    }

    @Deprecated
    public void freeMemory() {
        if (!this.e) {
            i.a((Object) this.g, "freeMemory");
        } else {
            this.f.freeMemory();
        }
    }

    public SslCertificate getCertificate() {
        return !this.e ? this.g.getCertificate() : this.f.getCertificate();
    }

    public int getContentHeight() {
        return !this.e ? this.g.getContentHeight() : this.f.getContentHeight();
    }

    public int getContentWidth() {
        if (this.e) {
            return this.f.getContentWidth();
        }
        Object a2 = i.a((Object) this.g, "getContentWidth");
        if (a2 == null) {
            return 0;
        }
        return ((Integer) a2).intValue();
    }

    public Bitmap getFavicon() {
        return !this.e ? this.g.getFavicon() : this.f.getFavicon();
    }

    public HitTestResult getHitTestResult() {
        return !this.e ? new HitTestResult(this.g.getHitTestResult()) : new HitTestResult(this.f.getHitTestResult());
    }

    public String[] getHttpAuthUsernamePassword(String str, String str2) {
        return !this.e ? this.g.getHttpAuthUsernamePassword(str, str2) : this.f.getHttpAuthUsernamePassword(str, str2);
    }

    public String getOriginalUrl() {
        return !this.e ? this.g.getOriginalUrl() : this.f.getOriginalUrl();
    }

    public int getProgress() {
        return !this.e ? this.g.getProgress() : this.f.getProgress();
    }

    public boolean getRendererPriorityWaivedWhenNotVisible() {
        try {
            if (this.e || Build.VERSION.SDK_INT < 26) {
                return false;
            }
            Object a2 = i.a((Object) this.g, "getRendererPriorityWaivedWhenNotVisible");
            if (a2 == null) {
                return false;
            }
            return ((Boolean) a2).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public int getRendererRequestedPriority() {
        try {
            if (this.e || Build.VERSION.SDK_INT < 26) {
                return 0;
            }
            Object a2 = i.a((Object) this.g, "getRendererRequestedPriority");
            if (a2 == null) {
                return 0;
            }
            return ((Integer) a2).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public float getScale() {
        if (this.e) {
            return this.f.getScale();
        }
        Object a2 = i.a((Object) this.g, "getScale");
        if (a2 == null) {
            return 0.0f;
        }
        return ((Float) a2).floatValue();
    }

    public int getScrollBarDefaultDelayBeforeFade() {
        if (getView() == null) {
            return 0;
        }
        return getView().getScrollBarDefaultDelayBeforeFade();
    }

    public int getScrollBarFadeDuration() {
        if (getView() == null) {
            return 0;
        }
        return getView().getScrollBarFadeDuration();
    }

    public int getScrollBarSize() {
        if (getView() == null) {
            return 0;
        }
        return getView().getScrollBarSize();
    }

    public int getScrollBarStyle() {
        if (getView() == null) {
            return 0;
        }
        return getView().getScrollBarStyle();
    }

    public WebSettings getSettings() {
        WebSettings webSettings = this.h;
        if (webSettings != null) {
            return webSettings;
        }
        WebSettings webSettings2 = this.e ? new WebSettings(this.f.getSettings()) : new WebSettings(this.g.getSettings());
        this.h = webSettings2;
        return webSettings2;
    }

    public IX5WebSettingsExtension getSettingsExtension() {
        if (!this.e) {
            return null;
        }
        return this.f.getX5WebViewExtension().getSettingsExtension();
    }

    public int getSysNightModeAlpha() {
        return NIGHT_MODE_ALPHA;
    }

    public String getTitle() {
        return !this.e ? this.g.getTitle() : this.f.getTitle();
    }

    public String getUrl() {
        return !this.e ? this.g.getUrl() : this.f.getUrl();
    }

    public View getView() {
        return !this.e ? this.g : this.f.getView();
    }

    public int getVisibleTitleHeight() {
        if (this.e) {
            return this.f.getVisibleTitleHeight();
        }
        Object a2 = i.a((Object) this.g, "getVisibleTitleHeight");
        if (a2 == null) {
            return 0;
        }
        return ((Integer) a2).intValue();
    }

    public WebChromeClient getWebChromeClient() {
        return this.o;
    }

    public IX5WebChromeClientExtension getWebChromeClientExtension() {
        if (!this.e) {
            return null;
        }
        return this.f.getX5WebViewExtension().getWebChromeClientExtension();
    }

    public int getWebScrollX() {
        return this.e ? this.f.getView().getScrollX() : this.g.getScrollX();
    }

    public int getWebScrollY() {
        return this.e ? this.f.getView().getScrollY() : this.g.getScrollY();
    }

    public WebViewClient getWebViewClient() {
        return this.n;
    }

    public IX5WebViewClientExtension getWebViewClientExtension() {
        if (!this.e) {
            return null;
        }
        return this.f.getX5WebViewExtension().getWebViewClientExtension();
    }

    public IX5WebViewBase.HitTestResult getX5HitTestResult() {
        if (!this.e) {
            return null;
        }
        return this.f.getHitTestResult();
    }

    public IX5WebViewExtension getX5WebViewExtension() {
        if (!this.e) {
            return null;
        }
        return this.f.getX5WebViewExtension();
    }

    @Deprecated
    public View getZoomControls() {
        return !this.e ? (View) i.a((Object) this.g, "getZoomControls") : this.f.getZoomControls();
    }

    public void goBack() {
        if (!this.e) {
            this.g.goBack();
        } else {
            this.f.goBack();
        }
    }

    public void goBackOrForward(int i2) {
        if (!this.e) {
            this.g.goBackOrForward(i2);
        } else {
            this.f.goBackOrForward(i2);
        }
    }

    public void goForward() {
        if (!this.e) {
            this.g.goForward();
        } else {
            this.f.goForward();
        }
    }

    public void invokeZoomPicker() {
        if (!this.e) {
            this.g.invokeZoomPicker();
        } else {
            this.f.invokeZoomPicker();
        }
    }

    public boolean isDayMode() {
        return w;
    }

    public boolean isPrivateBrowsingEnabled() {
        Object a2;
        if (this.e) {
            return this.f.isPrivateBrowsingEnable();
        }
        if (Build.VERSION.SDK_INT < 11 || (a2 = i.a((Object) this.g, "isPrivateBrowsingEnabled")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public void loadData(String str, String str2, String str3) {
        if (!this.e) {
            this.g.loadData(str, str2, str3);
        } else {
            this.f.loadData(str, str2, str3);
        }
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (!this.e) {
            this.g.loadDataWithBaseURL(str, str2, str3, str4, str5);
        } else {
            this.f.loadDataWithBaseURL(str, str2, str3, str4, str5);
        }
    }

    public void loadUrl(String str) {
        if (str != null && !showDebugView(str)) {
            if (!this.e) {
                this.g.loadUrl(str);
            } else {
                this.f.loadUrl(str);
            }
        }
    }

    public void loadUrl(String str, Map<String, String> map) {
        if (str != null && !showDebugView(str)) {
            if (this.e) {
                this.f.loadUrl(str, map);
            } else if (Build.VERSION.SDK_INT >= 8) {
                this.g.loadUrl(str, map);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!this.k && this.a != 0) {
            h();
        }
    }

    public boolean onLongClick(View view) {
        View.OnLongClickListener onLongClickListener = this.y;
        if (onLongClickListener == null) {
            return a(view);
        }
        if (!onLongClickListener.onLongClick(view)) {
            return a(view);
        }
        return true;
    }

    public void onPause() {
        if (!this.e) {
            i.a((Object) this.g, "onPause");
        } else {
            this.f.onPause();
        }
    }

    public void onResume() {
        if (!this.e) {
            i.a((Object) this.g, "onResume");
        } else {
            this.f.onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (Build.VERSION.SDK_INT >= 21 && b(this.i) && isHardwareAccelerated() && i2 > 0 && i3 > 0) {
            getLayerType();
        }
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i2) {
        Context context = this.i;
        if (context == null) {
            super.onVisibilityChanged(view, i2);
            return;
        }
        if (p == null) {
            p = context.getApplicationInfo().packageName;
        }
        String str = p;
        if (str == null || (!str.equals(TbsConfig.APP_WX) && !p.equals(TbsConfig.APP_QQ))) {
            if (!(i2 == 0 || this.k || this.a == 0)) {
                h();
            }
            super.onVisibilityChanged(view, i2);
            return;
        }
        super.onVisibilityChanged(view, i2);
    }

    public boolean overlayHorizontalScrollbar() {
        return !this.e ? this.g.overlayHorizontalScrollbar() : this.f.overlayHorizontalScrollbar();
    }

    public boolean overlayVerticalScrollbar() {
        return this.e ? this.f.overlayVerticalScrollbar() : this.g.overlayVerticalScrollbar();
    }

    public boolean pageDown(boolean z) {
        return !this.e ? this.g.pageDown(z) : this.f.pageDown(z, -1);
    }

    public boolean pageUp(boolean z) {
        return !this.e ? this.g.pageUp(z) : this.f.pageUp(z, -1);
    }

    public void pauseTimers() {
        if (!this.e) {
            this.g.pauseTimers();
        } else {
            this.f.pauseTimers();
        }
    }

    public void postUrl(String str, byte[] bArr) {
        if (!this.e) {
            this.g.postUrl(str, bArr);
        } else {
            this.f.postUrl(str, bArr);
        }
    }

    @Deprecated
    public void refreshPlugins(boolean z) {
        if (!this.e) {
            i.a((Object) this.g, "refreshPlugins", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
            return;
        }
        this.f.refreshPlugins(z);
    }

    public void reload() {
        if (!this.e) {
            this.g.reload();
        } else {
            this.f.reload();
        }
    }

    public void removeJavascriptInterface(String str) {
        if (this.e) {
            this.f.removeJavascriptInterface(str);
        } else if (Build.VERSION.SDK_INT >= 11) {
            i.a((Object) this.g, "removeJavascriptInterface", (Class<?>[]) new Class[]{String.class}, str);
        }
    }

    public void removeView(View view) {
        if (!this.e) {
            this.g.removeView(view);
            return;
        }
        View view2 = this.f.getView();
        try {
            Method a2 = i.a(view2, "removeView", View.class);
            a2.setAccessible(true);
            a2.invoke(view2, new Object[]{view});
        } catch (Throwable unused) {
        }
    }

    public JSONObject reportInitPerformance(long j2, int i2, long j3, long j4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("IS_X5", this.e);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        if (this.e) {
            View view2 = this.f.getView();
            if (!(view2 instanceof ViewGroup)) {
                return false;
            }
            ViewGroup viewGroup = (ViewGroup) view2;
            if (view == this) {
                view = view2;
            }
            return viewGroup.requestChildRectangleOnScreen(view, rect, z);
        }
        a aVar = this.g;
        if (view == this) {
            view = aVar;
        }
        return aVar.requestChildRectangleOnScreen(view, rect, z);
    }

    public void requestFocusNodeHref(Message message) {
        if (!this.e) {
            this.g.requestFocusNodeHref(message);
        } else {
            this.f.requestFocusNodeHref(message);
        }
    }

    public void requestImageRef(Message message) {
        if (!this.e) {
            this.g.requestImageRef(message);
        } else {
            this.f.requestImageRef(message);
        }
    }

    @Deprecated
    public boolean restorePicture(Bundle bundle, File file) {
        if (this.e) {
            return this.f.restorePicture(bundle, file);
        }
        Object a2 = i.a((Object) this.g, "restorePicture", (Class<?>[]) new Class[]{Bundle.class, File.class}, bundle, file);
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public WebBackForwardList restoreState(Bundle bundle) {
        return !this.e ? WebBackForwardList.a(this.g.restoreState(bundle)) : WebBackForwardList.a(this.f.restoreState(bundle));
    }

    public void resumeTimers() {
        if (!this.e) {
            this.g.resumeTimers();
        } else {
            this.f.resumeTimers();
        }
    }

    @Deprecated
    public void savePassword(String str, String str2, String str3) {
        if (!this.e) {
            i.a((Object) this.g, "savePassword", (Class<?>[]) new Class[]{String.class, String.class, String.class}, str, str2, str3);
            return;
        }
        this.f.savePassword(str, str2, str3);
    }

    @Deprecated
    public boolean savePicture(Bundle bundle, File file) {
        if (this.e) {
            return this.f.savePicture(bundle, file);
        }
        Object a2 = i.a((Object) this.g, "savePicture", (Class<?>[]) new Class[]{Bundle.class, File.class}, bundle, file);
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public WebBackForwardList saveState(Bundle bundle) {
        return !this.e ? WebBackForwardList.a(this.g.saveState(bundle)) : WebBackForwardList.a(this.f.saveState(bundle));
    }

    public void saveWebArchive(String str) {
        if (this.e) {
            this.f.saveWebArchive(str);
        } else if (Build.VERSION.SDK_INT >= 11) {
            i.a((Object) this.g, "saveWebArchive", (Class<?>[]) new Class[]{String.class}, str);
        }
    }

    public void saveWebArchive(String str, boolean z, ValueCallback<String> valueCallback) {
        if (this.e) {
            this.f.saveWebArchive(str, z, valueCallback);
        } else if (Build.VERSION.SDK_INT >= 11) {
            i.a((Object) this.g, "saveWebArchive", (Class<?>[]) new Class[]{String.class, Boolean.TYPE, ValueCallback.class}, str, Boolean.valueOf(z), valueCallback);
        }
    }

    public void setARModeEnable(boolean z) {
        try {
            if (this.e) {
                getSettingsExtension().setARModeEnable(z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setBackgroundColor(int i2) {
        if (!this.e) {
            this.g.setBackgroundColor(i2);
        } else {
            this.f.setBackgroundColor(i2);
        }
        super.setBackgroundColor(i2);
    }

    @Deprecated
    public void setCertificate(SslCertificate sslCertificate) {
        if (!this.e) {
            this.g.setCertificate(sslCertificate);
        } else {
            this.f.setCertificate(sslCertificate);
        }
    }

    public void setDayOrNight(boolean z) {
        try {
            if (this.e) {
                getSettingsExtension().setDayOrNight(z);
            }
            setSysDayOrNight(z);
            getView().postInvalidate();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setDownloadListener(final DownloadListener downloadListener) {
        boolean z = this.e;
        if (!z) {
            this.g.setDownloadListener(new DownloadListener() {
                public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                    DownloadListener downloadListener = downloadListener;
                    if (downloadListener == null) {
                        ApplicationInfo applicationInfo = WebView.this.i == null ? null : WebView.this.i.getApplicationInfo();
                        if (applicationInfo == null || !TbsConfig.APP_WX.equals(applicationInfo.packageName)) {
                            MttLoader.loadUrl(WebView.this.i, str, (HashMap<String, String>) null, (WebView) null);
                            return;
                        }
                        return;
                    }
                    downloadListener.onDownloadStart(str, str2, str3, str4, j);
                }
            });
        } else {
            this.f.setDownloadListener(new b(this, downloadListener, z));
        }
    }

    public void setFindListener(final IX5WebViewBase.FindListener findListener) {
        if (this.e) {
            this.f.setFindListener(findListener);
        } else if (Build.VERSION.SDK_INT >= 16) {
            this.g.setFindListener(new WebView.FindListener() {
                public void onFindResultReceived(int i, int i2, boolean z) {
                    findListener.onFindResultReceived(i, i2, z);
                }
            });
        }
    }

    public void setHorizontalScrollbarOverlay(boolean z) {
        if (!this.e) {
            this.g.setHorizontalScrollbarOverlay(z);
        } else {
            this.f.setHorizontalScrollbarOverlay(z);
        }
    }

    public void setHttpAuthUsernamePassword(String str, String str2, String str3, String str4) {
        if (!this.e) {
            this.g.setHttpAuthUsernamePassword(str, str2, str3, str4);
        } else {
            this.f.setHttpAuthUsernamePassword(str, str2, str3, str4);
        }
    }

    public void setInitialScale(int i2) {
        if (!this.e) {
            this.g.setInitialScale(i2);
        } else {
            this.f.setInitialScale(i2);
        }
    }

    @Deprecated
    public void setMapTrackballToArrowKeys(boolean z) {
        if (!this.e) {
            i.a((Object) this.g, "setMapTrackballToArrowKeys", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
            return;
        }
        this.f.setMapTrackballToArrowKeys(z);
    }

    public void setNetworkAvailable(boolean z) {
        if (this.e) {
            this.f.setNetworkAvailable(z);
        } else if (Build.VERSION.SDK_INT >= 3) {
            this.g.setNetworkAvailable(z);
        }
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        if (!this.e) {
            this.g.setOnLongClickListener(onLongClickListener);
            return;
        }
        View view = this.f.getView();
        try {
            if (this.x == null) {
                Method a2 = i.a(view, "getListenerInfo", new Class[0]);
                a2.setAccessible(true);
                Object invoke = a2.invoke(view, (Object[]) null);
                Field declaredField = invoke.getClass().getDeclaredField("mOnLongClickListener");
                declaredField.setAccessible(true);
                this.x = declaredField.get(invoke);
            }
            this.y = onLongClickListener;
            getView().setOnLongClickListener(this);
        } catch (Throwable unused) {
        }
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        getView().setOnTouchListener(onTouchListener);
    }

    @Deprecated
    public void setPictureListener(final PictureListener pictureListener) {
        if (!this.e) {
            if (pictureListener == null) {
                this.g.setPictureListener((WebView.PictureListener) null);
            } else {
                this.g.setPictureListener(new WebView.PictureListener() {
                    public void onNewPicture(android.webkit.WebView webView, Picture picture) {
                        WebView.this.a(webView);
                        pictureListener.onNewPicture(WebView.this, picture);
                    }
                });
            }
        } else if (pictureListener == null) {
            this.f.setPictureListener((IX5WebViewBase.PictureListener) null);
        } else {
            this.f.setPictureListener(new IX5WebViewBase.PictureListener() {
                public void onNewPicture(IX5WebViewBase iX5WebViewBase, Picture picture, boolean z) {
                    WebView.this.a(iX5WebViewBase);
                    pictureListener.onNewPicture(WebView.this, picture);
                }

                public void onNewPictureIfHaveContent(IX5WebViewBase iX5WebViewBase, Picture picture) {
                }
            });
        }
    }

    public void setRendererPriorityPolicy(int i2, boolean z) {
        try {
            if (!this.e && Build.VERSION.SDK_INT >= 26) {
                i.a((Object) this.g, "setRendererPriorityPolicy", (Class<?>[]) new Class[]{Integer.TYPE, Boolean.TYPE}, Integer.valueOf(i2), Boolean.valueOf(z));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setScrollBarStyle(int i2) {
        if (this.e) {
            this.f.getView().setScrollBarStyle(i2);
        } else {
            this.g.setScrollBarStyle(i2);
        }
    }

    public void setSysNightModeAlpha(int i2) {
        NIGHT_MODE_ALPHA = i2;
    }

    public void setVerticalScrollbarOverlay(boolean z) {
        if (!this.e) {
            this.g.setVerticalScrollbarOverlay(z);
        } else {
            this.f.setVerticalScrollbarOverlay(z);
        }
    }

    public boolean setVideoFullScreen(Context context, boolean z) {
        if (!context.getApplicationInfo().processName.contains("com.tencent.android.qqdownloader") || this.f == null) {
            return false;
        }
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putInt("DefaultVideoScreen", 2);
        } else {
            bundle.putInt("DefaultVideoScreen", 1);
        }
        this.f.getX5WebViewExtension().invokeMiscMethod("setVideoParams", bundle);
        return true;
    }

    public void setVisibility(int i2) {
        super.setVisibility(i2);
        if (getView() != null) {
            getView().setVisibility(i2);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [android.webkit.WebChromeClient] */
    /* JADX WARNING: type inference failed for: r1v3, types: [com.tencent.smtt.export.external.interfaces.IX5WebChromeClient] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setWebChromeClient(com.tencent.smtt.sdk.WebChromeClient r5) {
        /*
            r4 = this;
            boolean r0 = r4.e
            r1 = 0
            if (r0 == 0) goto L_0x0020
            com.tencent.smtt.export.external.interfaces.IX5WebViewBase r0 = r4.f
            if (r5 != 0) goto L_0x000a
            goto L_0x001c
        L_0x000a:
            com.tencent.smtt.sdk.h r1 = new com.tencent.smtt.sdk.h
            com.tencent.smtt.sdk.w r2 = com.tencent.smtt.sdk.w.a()
            r3 = 1
            com.tencent.smtt.sdk.x r2 = r2.a((boolean) r3)
            com.tencent.smtt.export.external.interfaces.IX5WebChromeClient r2 = r2.i()
            r1.<init>(r2, r4, r5)
        L_0x001c:
            r0.setWebChromeClient(r1)
            goto L_0x002d
        L_0x0020:
            com.tencent.smtt.sdk.WebView$a r0 = r4.g
            if (r5 != 0) goto L_0x0025
            goto L_0x002a
        L_0x0025:
            com.tencent.smtt.sdk.SystemWebChromeClient r1 = new com.tencent.smtt.sdk.SystemWebChromeClient
            r1.<init>(r4, r5)
        L_0x002a:
            r0.setWebChromeClient(r1)
        L_0x002d:
            r4.o = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebView.setWebChromeClient(com.tencent.smtt.sdk.WebChromeClient):void");
    }

    public void setWebChromeClientExtension(IX5WebChromeClientExtension iX5WebChromeClientExtension) {
        if (this.e) {
            this.f.getX5WebViewExtension().setWebChromeClientExtension(iX5WebChromeClientExtension);
        }
    }

    public void setWebViewCallbackClient(WebViewCallbackClient webViewCallbackClient) {
        this.mWebViewCallbackClient = webViewCallbackClient;
        if (this.e && getX5WebViewExtension() != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("flag", true);
            getX5WebViewExtension().invokeMiscMethod("setWebViewCallbackClientFlag", bundle);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [android.webkit.WebViewClient] */
    /* JADX WARNING: type inference failed for: r1v3, types: [com.tencent.smtt.export.external.interfaces.IX5WebViewClient] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setWebViewClient(com.tencent.smtt.sdk.WebViewClient r5) {
        /*
            r4 = this;
            boolean r0 = r4.e
            r1 = 0
            if (r0 == 0) goto L_0x0020
            com.tencent.smtt.export.external.interfaces.IX5WebViewBase r0 = r4.f
            if (r5 != 0) goto L_0x000a
            goto L_0x001c
        L_0x000a:
            com.tencent.smtt.sdk.i r1 = new com.tencent.smtt.sdk.i
            com.tencent.smtt.sdk.w r2 = com.tencent.smtt.sdk.w.a()
            r3 = 1
            com.tencent.smtt.sdk.x r2 = r2.a((boolean) r3)
            com.tencent.smtt.export.external.interfaces.IX5WebViewClient r2 = r2.j()
            r1.<init>(r2, r4, r5)
        L_0x001c:
            r0.setWebViewClient(r1)
            goto L_0x002d
        L_0x0020:
            com.tencent.smtt.sdk.WebView$a r0 = r4.g
            if (r5 != 0) goto L_0x0025
            goto L_0x002a
        L_0x0025:
            com.tencent.smtt.sdk.SystemWebViewClient r1 = new com.tencent.smtt.sdk.SystemWebViewClient
            r1.<init>(r4, r5)
        L_0x002a:
            r0.setWebViewClient(r1)
        L_0x002d:
            r4.n = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebView.setWebViewClient(com.tencent.smtt.sdk.WebViewClient):void");
    }

    public void setWebViewClientExtension(IX5WebViewClientExtension iX5WebViewClientExtension) {
        if (this.e) {
            this.f.getX5WebViewExtension().setWebViewClientExtension(iX5WebViewClientExtension);
        }
    }

    public boolean showDebugView(String str) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.startsWith("https://debugtbs.qq.com")) {
            getView().setVisibility(4);
            d.a(this.i).a(lowerCase, this, this.i, n.a().getLooper());
            return true;
        } else if (!lowerCase.startsWith("https://debugx5.qq.com") || this.e) {
            return false;
        } else {
            loadDataWithBaseURL((String) null, "<!DOCTYPE html><html><body>" + "<head>" + "<title>无法打开debugx5</title>" + "<meta name=\"viewport\" content=\"width=device-width, user-scalable=no\" />" + "</head>" + "<br/><br /><h2>debugx5页面仅在使用了X5内核时有效，由于当前没有使用X5内核，无法打开debugx5！</h2><br />" + "尝试<a href=\"https://debugtbs.qq.com?10000\">进入DebugTbs安装或打开X5内核</a>" + "</body></html>", "text/html", "utf-8", (String) null);
            return true;
        }
    }

    public boolean showFindDialog(String str, boolean z) {
        return false;
    }

    public void stopLoading() {
        if (!this.e) {
            this.g.stopLoading();
        } else {
            this.f.stopLoading();
        }
    }

    public void super_computeScroll() {
        if (!this.e) {
            this.g.a();
            return;
        }
        try {
            i.a((Object) this.f.getView(), "super_computeScroll");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean super_dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.e) {
            return this.g.b(motionEvent);
        }
        try {
            Object a2 = i.a((Object) this.f.getView(), "super_dispatchTouchEvent", (Class<?>[]) new Class[]{MotionEvent.class}, motionEvent);
            if (a2 == null) {
                return false;
            }
            return ((Boolean) a2).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean super_onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.e) {
            return this.g.c(motionEvent);
        }
        try {
            Object a2 = i.a((Object) this.f.getView(), "super_onInterceptTouchEvent", (Class<?>[]) new Class[]{MotionEvent.class}, motionEvent);
            if (a2 == null) {
                return false;
            }
            return ((Boolean) a2).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public void super_onOverScrolled(int i2, int i3, boolean z, boolean z2) {
        if (!this.e) {
            this.g.a(i2, i3, z, z2);
            return;
        }
        View view = this.f.getView();
        try {
            i.a((Object) view, "super_onOverScrolled", (Class<?>[]) new Class[]{Integer.TYPE, Integer.TYPE, Boolean.TYPE, Boolean.TYPE}, Integer.valueOf(i2), Integer.valueOf(i3), Boolean.valueOf(z), Boolean.valueOf(z2));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void super_onScrollChanged(int i2, int i3, int i4, int i5) {
        if (!this.e) {
            this.g.a(i2, i3, i4, i5);
            return;
        }
        View view = this.f.getView();
        try {
            i.a((Object) view, "super_onScrollChanged", (Class<?>[]) new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean super_onTouchEvent(MotionEvent motionEvent) {
        if (!this.e) {
            return this.g.a(motionEvent);
        }
        try {
            Object a2 = i.a((Object) this.f.getView(), "super_onTouchEvent", (Class<?>[]) new Class[]{MotionEvent.class}, motionEvent);
            if (a2 == null) {
                return false;
            }
            return ((Boolean) a2).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean super_overScrollBy(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z) {
        if (!this.e) {
            return this.g.a(i2, i3, i4, i5, i6, i7, i8, i9, z);
        }
        View view = this.f.getView();
        try {
            Object a2 = i.a((Object) view, "super_overScrollBy", (Class<?>[]) new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Boolean.TYPE}, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), Integer.valueOf(i7), Integer.valueOf(i8), Integer.valueOf(i9), Boolean.valueOf(z));
            if (a2 == null) {
                return false;
            }
            return ((Boolean) a2).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    public void switchNightMode(boolean z) {
        String str;
        if (z != w) {
            w = z;
            if (z) {
                TbsLog.e("QB_SDK", "deleteNightMode");
                str = "javascript:document.getElementsByTagName('HEAD').item(0).removeChild(document.getElementById('QQBrowserSDKNightMode'));";
            } else {
                TbsLog.e("QB_SDK", "nightMode");
                str = "javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);";
            }
            loadUrl(str);
        }
    }

    public void switchToNightMode() {
        TbsLog.e("QB_SDK", "switchToNightMode 01");
        if (!w) {
            TbsLog.e("QB_SDK", "switchToNightMode");
            loadUrl("javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);");
        }
    }

    public boolean zoomIn() {
        return !this.e ? this.g.zoomIn() : this.f.zoomIn();
    }

    public boolean zoomOut() {
        return !this.e ? this.g.zoomOut() : this.f.zoomOut();
    }
}
