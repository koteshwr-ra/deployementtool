package com.tencent.smtt.sdk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebStorage;
import com.tencent.smtt.sdk.WebView;

class SystemWebChromeClient extends WebChromeClient {
    private WebView a;
    private WebChromeClient b;

    private static class a implements ConsoleMessage {
        private ConsoleMessage.MessageLevel a;
        private String b;
        private String c;
        private int d;

        a(android.webkit.ConsoleMessage consoleMessage) {
            this.a = ConsoleMessage.MessageLevel.valueOf(consoleMessage.messageLevel().name());
            this.b = consoleMessage.message();
            this.c = consoleMessage.sourceId();
            this.d = consoleMessage.lineNumber();
        }

        a(String str, String str2, int i) {
            this.a = ConsoleMessage.MessageLevel.LOG;
            this.b = str;
            this.c = str2;
            this.d = i;
        }

        public int lineNumber() {
            return this.d;
        }

        public String message() {
            return this.b;
        }

        public ConsoleMessage.MessageLevel messageLevel() {
            return this.a;
        }

        public String sourceId() {
            return this.c;
        }
    }

    class b implements IX5WebChromeClient.CustomViewCallback {
        WebChromeClient.CustomViewCallback a;

        b(WebChromeClient.CustomViewCallback customViewCallback) {
            this.a = customViewCallback;
        }

        public void onCustomViewHidden() {
            this.a.onCustomViewHidden();
        }
    }

    class c implements GeolocationPermissionsCallback {
        GeolocationPermissions.Callback a;

        c(GeolocationPermissions.Callback callback) {
            this.a = callback;
        }

        public void invoke(String str, boolean z, boolean z2) {
            this.a.invoke(str, z, z2);
        }
    }

    private class d implements JsPromptResult {
        android.webkit.JsPromptResult a;

        d(android.webkit.JsPromptResult jsPromptResult) {
            this.a = jsPromptResult;
        }

        public void cancel() {
            this.a.cancel();
        }

        public void confirm() {
            this.a.confirm();
        }

        public void confirm(String str) {
            this.a.confirm(str);
        }
    }

    private class e implements JsResult {
        android.webkit.JsResult a;

        e(android.webkit.JsResult jsResult) {
            this.a = jsResult;
        }

        public void cancel() {
            this.a.cancel();
        }

        public void confirm() {
            this.a.confirm();
        }
    }

    class f implements WebStorage.QuotaUpdater {
        WebStorage.QuotaUpdater a;

        f(WebStorage.QuotaUpdater quotaUpdater) {
            this.a = quotaUpdater;
        }

        public void updateQuota(long j) {
            this.a.updateQuota(j);
        }
    }

    SystemWebChromeClient(WebView webView, WebChromeClient webChromeClient) {
        this.a = webView;
        this.b = webChromeClient;
    }

    public Bitmap getDefaultVideoPoster() {
        Bitmap defaultVideoPoster = this.b.getDefaultVideoPoster();
        if (defaultVideoPoster != null) {
            return defaultVideoPoster;
        }
        try {
            return Build.VERSION.SDK_INT >= 24 ? BitmapFactory.decodeResource(this.a.getResources(), 17301540) : defaultVideoPoster;
        } catch (Exception unused) {
            return defaultVideoPoster;
        }
    }

    public View getVideoLoadingProgressView() {
        return this.b.getVideoLoadingProgressView();
    }

    public void getVisitedHistory(final ValueCallback<String[]> valueCallback) {
        this.b.getVisitedHistory(new ValueCallback<String[]>() {
            /* renamed from: a */
            public void onReceiveValue(String[] strArr) {
                valueCallback.onReceiveValue(strArr);
            }
        });
    }

    public void onCloseWindow(WebView webView) {
        this.a.a(webView);
        this.b.onCloseWindow(this.a);
    }

    public void onConsoleMessage(String str, int i, String str2) {
        this.b.onConsoleMessage(new a(str, str2, i));
    }

    public boolean onConsoleMessage(android.webkit.ConsoleMessage consoleMessage) {
        return this.b.onConsoleMessage(new a(consoleMessage));
    }

    public boolean onCreateWindow(WebView webView, boolean z, boolean z2, final Message message) {
        WebView webView2 = this.a;
        webView2.getClass();
        final WebView.WebViewTransport webViewTransport = new WebView.WebViewTransport();
        Message obtain = Message.obtain(message.getTarget(), new Runnable() {
            public void run() {
                WebView webView = webViewTransport.getWebView();
                if (webView != null) {
                    ((WebView.WebViewTransport) message.obj).setWebView(webView.a());
                }
                message.sendToTarget();
            }
        });
        obtain.obj = webViewTransport;
        return this.b.onCreateWindow(this.a, z, z2, obtain);
    }

    @Deprecated
    public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
        this.b.onExceededDatabaseQuota(str, str2, j, j2, j3, new f(quotaUpdater));
    }

    public void onGeolocationPermissionsHidePrompt() {
        this.b.onGeolocationPermissionsHidePrompt();
    }

    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        this.b.onGeolocationPermissionsShowPrompt(str, new c(callback));
    }

    public void onHideCustomView() {
        this.b.onHideCustomView();
    }

    public boolean onJsAlert(android.webkit.WebView webView, String str, String str2, android.webkit.JsResult jsResult) {
        this.a.a(webView);
        return this.b.onJsAlert(this.a, str, str2, new e(jsResult));
    }

    public boolean onJsBeforeUnload(android.webkit.WebView webView, String str, String str2, android.webkit.JsResult jsResult) {
        this.a.a(webView);
        return this.b.onJsBeforeUnload(this.a, str, str2, new e(jsResult));
    }

    public boolean onJsConfirm(android.webkit.WebView webView, String str, String str2, android.webkit.JsResult jsResult) {
        this.a.a(webView);
        return this.b.onJsConfirm(this.a, str, str2, new e(jsResult));
    }

    public boolean onJsPrompt(android.webkit.WebView webView, String str, String str2, String str3, android.webkit.JsPromptResult jsPromptResult) {
        this.a.a(webView);
        return this.b.onJsPrompt(this.a, str, str2, str3, new d(jsPromptResult));
    }

    public boolean onJsTimeout() {
        return this.b.onJsTimeout();
    }

    public void onPermissionRequest(final PermissionRequest permissionRequest) {
        this.b.onPermissionRequest(new com.tencent.smtt.export.external.interfaces.PermissionRequest() {
            public void deny() {
                if (Build.VERSION.SDK_INT >= 21) {
                    permissionRequest.deny();
                }
            }

            public Uri getOrigin() {
                if (Build.VERSION.SDK_INT >= 21) {
                    return permissionRequest.getOrigin();
                }
                return null;
            }

            public String[] getResources() {
                return Build.VERSION.SDK_INT >= 21 ? permissionRequest.getResources() : new String[0];
            }

            public void grant(String[] strArr) {
                if (Build.VERSION.SDK_INT >= 21) {
                    permissionRequest.grant(strArr);
                }
            }
        });
    }

    public void onPermissionRequestCanceled(final PermissionRequest permissionRequest) {
        this.b.onPermissionRequestCanceled(new com.tencent.smtt.export.external.interfaces.PermissionRequest() {
            public void deny() {
                if (Build.VERSION.SDK_INT >= 21) {
                    permissionRequest.deny();
                }
            }

            public Uri getOrigin() {
                if (Build.VERSION.SDK_INT >= 21) {
                    return permissionRequest.getOrigin();
                }
                return null;
            }

            public String[] getResources() {
                return Build.VERSION.SDK_INT >= 21 ? permissionRequest.getResources() : new String[0];
            }

            public void grant(String[] strArr) {
                if (Build.VERSION.SDK_INT >= 21) {
                    permissionRequest.grant(strArr);
                }
            }
        });
    }

    public void onProgressChanged(android.webkit.WebView webView, int i) {
        this.a.a(webView);
        this.b.onProgressChanged(this.a, i);
    }

    @Deprecated
    public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
        this.b.onReachedMaxAppCacheSize(j, j2, new f(quotaUpdater));
    }

    public void onReceivedIcon(android.webkit.WebView webView, Bitmap bitmap) {
        this.a.a(webView);
        this.b.onReceivedIcon(this.a, bitmap);
    }

    public void onReceivedTitle(android.webkit.WebView webView, String str) {
        this.a.a(webView);
        this.b.onReceivedTitle(this.a, str);
    }

    public void onReceivedTouchIconUrl(android.webkit.WebView webView, String str, boolean z) {
        this.a.a(webView);
        this.b.onReceivedTouchIconUrl(this.a, str, z);
    }

    public void onRequestFocus(android.webkit.WebView webView) {
        this.a.a(webView);
        this.b.onRequestFocus(this.a);
    }

    @Deprecated
    public void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
        this.b.onShowCustomView(view, i, new b(customViewCallback));
    }

    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        this.b.onShowCustomView(view, new b(customViewCallback));
    }

    public boolean onShowFileChooser(android.webkit.WebView webView, final ValueCallback<Uri[]> valueCallback, final WebChromeClient.FileChooserParams fileChooserParams) {
        AnonymousClass4 r0 = new ValueCallback<Uri[]>() {
            /* renamed from: a */
            public void onReceiveValue(Uri[] uriArr) {
                valueCallback.onReceiveValue(uriArr);
            }
        };
        AnonymousClass5 r3 = new WebChromeClient.FileChooserParams() {
            public Intent createIntent() {
                return fileChooserParams.createIntent();
            }

            public String[] getAcceptTypes() {
                return fileChooserParams.getAcceptTypes();
            }

            public String getFilenameHint() {
                return fileChooserParams.getFilenameHint();
            }

            public int getMode() {
                return fileChooserParams.getMode();
            }

            public CharSequence getTitle() {
                return fileChooserParams.getTitle();
            }

            public boolean isCaptureEnabled() {
                return fileChooserParams.isCaptureEnabled();
            }
        };
        this.a.a(webView);
        return this.b.onShowFileChooser(this.a, r0, r3);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback) {
        openFileChooser(valueCallback, (String) null, (String) null);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
        openFileChooser(valueCallback, str, (String) null);
    }

    public void openFileChooser(final ValueCallback<Uri> valueCallback, String str, String str2) {
        this.b.openFileChooser(new ValueCallback<Uri>() {
            /* renamed from: a */
            public void onReceiveValue(Uri uri) {
                valueCallback.onReceiveValue(uri);
            }
        }, str, str2);
    }

    public void setupAutoFill(Message message) {
    }
}
