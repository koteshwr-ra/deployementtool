package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Looper;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsError;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsVirtualMachine;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

public final class JsVirtualMachine {
    private final Context a;
    private final IX5JsVirtualMachine b;
    private final HashSet<WeakReference<a>> c;

    private static class a implements IX5JsContext {
        private WebView a;

        public a(Context context) {
            WebView webView = new WebView(context);
            this.a = webView;
            webView.getSettings().setJavaScriptEnabled(true);
        }

        public void a() {
            WebView webView = this.a;
            if (webView != null) {
                webView.onPause();
            }
        }

        public void addJavascriptInterface(Object obj, String str) {
            WebView webView = this.a;
            if (webView != null) {
                webView.addJavascriptInterface(obj, str);
                this.a.loadUrl("about:blank");
            }
        }

        public void b() {
            WebView webView = this.a;
            if (webView != null) {
                webView.onResume();
            }
        }

        public void destroy() {
            WebView webView = this.a;
            if (webView != null) {
                webView.clearHistory();
                this.a.clearCache(true);
                this.a.loadUrl("about:blank");
                this.a.freeMemory();
                this.a.pauseTimers();
                this.a.destroy();
                this.a = null;
            }
        }

        public void evaluateJavascript(String str, final ValueCallback<String> valueCallback, URL url) {
            WebView webView = this.a;
            if (webView != null) {
                webView.evaluateJavascript(str, valueCallback == null ? null : new ValueCallback<String>() {
                    /* renamed from: a */
                    public void onReceiveValue(String str) {
                        valueCallback.onReceiveValue(str);
                    }
                });
            }
        }

        public IX5JsValue evaluateScript(String str, URL url) {
            WebView webView = this.a;
            if (webView == null) {
                return null;
            }
            webView.evaluateJavascript(str, (ValueCallback<String>) null);
            return null;
        }

        public void evaluateScriptAsync(String str, final ValueCallback<IX5JsValue> valueCallback, URL url) {
            WebView webView = this.a;
            if (webView != null) {
                webView.evaluateJavascript(str, valueCallback == null ? null : new ValueCallback<String>() {
                    /* renamed from: a */
                    public void onReceiveValue(String str) {
                        valueCallback.onReceiveValue((Object) null);
                    }
                });
            }
        }

        public byte[] getNativeBuffer(int i) {
            return null;
        }

        public int getNativeBufferId() {
            return -1;
        }

        public void removeJavascriptInterface(String str) {
            WebView webView = this.a;
            if (webView != null) {
                webView.removeJavascriptInterface(str);
            }
        }

        public void setExceptionHandler(ValueCallback<IX5JsError> valueCallback) {
        }

        public void setName(String str) {
        }

        public int setNativeBuffer(int i, byte[] bArr) {
            return -1;
        }

        public void setPerContextData(Object obj) {
        }

        public void stealValueFromOtherCtx(String str, IX5JsContext iX5JsContext, String str2) {
        }
    }

    public JsVirtualMachine(Context context) {
        this(context, (Looper) null);
    }

    public JsVirtualMachine(Context context, Looper looper) {
        this.c = new HashSet<>();
        this.a = context;
        this.b = X5JsCore.a(context, looper);
    }

    /* access modifiers changed from: protected */
    public IX5JsContext a() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.b;
        if (iX5JsVirtualMachine != null) {
            return iX5JsVirtualMachine.createJsContext();
        }
        a aVar = new a(this.a);
        this.c.add(new WeakReference(aVar));
        return aVar;
    }

    public void destroy() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.b;
        if (iX5JsVirtualMachine != null) {
            iX5JsVirtualMachine.destroy();
            return;
        }
        Iterator<WeakReference<a>> it = this.c.iterator();
        while (it.hasNext()) {
            WeakReference next = it.next();
            if (next.get() != null) {
                ((a) next.get()).destroy();
            }
        }
    }

    public Looper getLooper() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.b;
        return iX5JsVirtualMachine != null ? iX5JsVirtualMachine.getLooper() : Looper.myLooper();
    }

    public boolean isFallback() {
        return this.b == null;
    }

    public void onPause() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.b;
        if (iX5JsVirtualMachine != null) {
            iX5JsVirtualMachine.onPause();
            return;
        }
        Iterator<WeakReference<a>> it = this.c.iterator();
        while (it.hasNext()) {
            WeakReference next = it.next();
            if (next.get() != null) {
                ((a) next.get()).a();
            }
        }
    }

    public void onResume() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.b;
        if (iX5JsVirtualMachine != null) {
            iX5JsVirtualMachine.onResume();
            return;
        }
        Iterator<WeakReference<a>> it = this.c.iterator();
        while (it.hasNext()) {
            WeakReference next = it.next();
            if (next.get() != null) {
                ((a) next.get()).b();
            }
        }
    }
}
