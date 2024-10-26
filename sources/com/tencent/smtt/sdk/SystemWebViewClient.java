package com.tencent.smtt.sdk;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.os.Build;
import android.os.Message;
import android.view.KeyEvent;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceError;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tencent.smtt.export.external.interfaces.ClientCertRequest;
import com.tencent.smtt.export.external.interfaces.HttpAuthHandler;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.i;
import com.tencent.smtt.utils.n;
import java.io.InputStream;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Map;

class SystemWebViewClient extends WebViewClient {
    private static String c;
    private WebViewClient a;
    /* access modifiers changed from: private */
    public WebView b;

    private static class a extends ClientCertRequest {
        private android.webkit.ClientCertRequest a;

        public a(android.webkit.ClientCertRequest clientCertRequest) {
            this.a = clientCertRequest;
        }

        public void cancel() {
            this.a.cancel();
        }

        public String getHost() {
            return this.a.getHost();
        }

        public String[] getKeyTypes() {
            return this.a.getKeyTypes();
        }

        public int getPort() {
            return this.a.getPort();
        }

        public Principal[] getPrincipals() {
            return this.a.getPrincipals();
        }

        public void ignore() {
            this.a.ignore();
        }

        public void proceed(PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
            this.a.proceed(privateKey, x509CertificateArr);
        }
    }

    private static class b implements HttpAuthHandler {
        private android.webkit.HttpAuthHandler a;

        b(android.webkit.HttpAuthHandler httpAuthHandler) {
            this.a = httpAuthHandler;
        }

        public void cancel() {
            this.a.cancel();
        }

        public void proceed(String str, String str2) {
            this.a.proceed(str, str2);
        }

        public boolean useHttpAuthUsernamePassword() {
            return this.a.useHttpAuthUsernamePassword();
        }
    }

    private static class c implements SslErrorHandler {
        android.webkit.SslErrorHandler a;

        c(android.webkit.SslErrorHandler sslErrorHandler) {
            this.a = sslErrorHandler;
        }

        public void cancel() {
            this.a.cancel();
        }

        public void proceed() {
            this.a.proceed();
        }
    }

    private static class d implements SslError {
        android.net.http.SslError a;

        d(android.net.http.SslError sslError) {
            this.a = sslError;
        }

        public boolean addError(int i) {
            return this.a.addError(i);
        }

        public SslCertificate getCertificate() {
            return this.a.getCertificate();
        }

        public int getPrimaryError() {
            return this.a.getPrimaryError();
        }

        public String getUrl() {
            return this.a.getUrl();
        }

        public boolean hasError(int i) {
            return this.a.hasError(i);
        }
    }

    static class e implements WebResourceRequest {
        private String a;
        private boolean b;
        private boolean c;
        private boolean d;
        private String e;
        private Map<String, String> f;

        public e(String str, boolean z, boolean z2, boolean z3, String str2, Map<String, String> map) {
            this.a = str;
            this.b = z;
            this.c = z2;
            this.d = z3;
            this.e = str2;
            this.f = map;
        }

        public String getMethod() {
            return this.e;
        }

        public Map<String, String> getRequestHeaders() {
            return this.f;
        }

        public Uri getUrl() {
            return Uri.parse(this.a);
        }

        public boolean hasGesture() {
            return this.d;
        }

        public boolean isForMainFrame() {
            return this.b;
        }

        public boolean isRedirect() {
            return this.c;
        }
    }

    private static class f implements WebResourceRequest {
        android.webkit.WebResourceRequest a;

        f(android.webkit.WebResourceRequest webResourceRequest) {
            this.a = webResourceRequest;
        }

        public String getMethod() {
            return this.a.getMethod();
        }

        public Map<String, String> getRequestHeaders() {
            return this.a.getRequestHeaders();
        }

        public Uri getUrl() {
            return this.a.getUrl();
        }

        public boolean hasGesture() {
            return this.a.hasGesture();
        }

        public boolean isForMainFrame() {
            return this.a.isForMainFrame();
        }

        public boolean isRedirect() {
            if (Build.VERSION.SDK_INT >= 24) {
                Object a2 = i.a((Object) this.a, "isRedirect");
                if (a2 instanceof Boolean) {
                    return ((Boolean) a2).booleanValue();
                }
            }
            return false;
        }
    }

    private static class g extends WebResourceResponse {
        android.webkit.WebResourceResponse a;

        public g(android.webkit.WebResourceResponse webResourceResponse) {
            this.a = webResourceResponse;
        }

        public InputStream getData() {
            return this.a.getData();
        }

        public String getEncoding() {
            return this.a.getEncoding();
        }

        public String getMimeType() {
            return this.a.getMimeType();
        }

        public String getReasonPhrase() {
            return this.a.getReasonPhrase();
        }

        public Map<String, String> getResponseHeaders() {
            return this.a.getResponseHeaders();
        }

        public int getStatusCode() {
            return this.a.getStatusCode();
        }

        public void setData(InputStream inputStream) {
            this.a.setData(inputStream);
        }

        public void setEncoding(String str) {
            this.a.setEncoding(str);
        }

        public void setMimeType(String str) {
            this.a.setMimeType(str);
        }

        public void setResponseHeaders(Map<String, String> map) {
            this.a.setResponseHeaders(map);
        }

        public void setStatusCodeAndReasonPhrase(int i, String str) {
            this.a.setStatusCodeAndReasonPhrase(i, str);
        }
    }

    SystemWebViewClient(WebView webView, WebViewClient webViewClient) {
        this.b = webView;
        this.a = webViewClient;
    }

    public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
        this.b.a(webView);
        this.a.doUpdateVisitedHistory(this.b, str, z);
    }

    public void onFormResubmission(WebView webView, Message message, Message message2) {
        this.b.a(webView);
        this.a.onFormResubmission(this.b, message, message2);
    }

    public void onLoadResource(WebView webView, String str) {
        this.b.a(webView);
        this.a.onLoadResource(this.b, str);
    }

    public void onPageCommitVisible(WebView webView, String str) {
        this.b.a(webView);
        this.a.onPageCommitVisible(this.b, str);
    }

    public void onPageFinished(WebView webView, String str) {
        n a2;
        if (c == null && (a2 = n.a()) != null) {
            a2.a(true);
            c = Boolean.toString(true);
        }
        this.b.a(webView);
        this.b.a++;
        this.a.onPageFinished(this.b, str);
        if (TbsConfig.APP_QZONE.equals(webView.getContext().getApplicationInfo().packageName)) {
            this.b.a(webView.getContext());
        }
        TbsLog.app_extra("SystemWebViewClient", webView.getContext());
        WebView.c();
        if (!TbsShareManager.mHasQueryed && this.b.getContext() != null && TbsShareManager.isThirdPartyApp(this.b.getContext())) {
            TbsShareManager.mHasQueryed = true;
            new Thread(new Runnable() {
                public void run() {
                    if (!TbsShareManager.forceLoadX5FromTBSDemo(SystemWebViewClient.this.b.getContext()) && TbsDownloader.needDownload(SystemWebViewClient.this.b.getContext(), false)) {
                        TbsDownloader.startDownload(SystemWebViewClient.this.b.getContext());
                    }
                }
            }).start();
        }
        if (this.b.getContext() != null && !TbsLogReport.getInstance(this.b.getContext()).getShouldUploadEventReport()) {
            TbsLogReport.getInstance(this.b.getContext()).setShouldUploadEventReport(true);
            TbsLogReport.getInstance(this.b.getContext()).dailyReport();
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.b.a(webView);
        this.a.onPageStarted(this.b, str, bitmap);
    }

    public void onReceivedClientCertRequest(WebView webView, android.webkit.ClientCertRequest clientCertRequest) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.b.a(webView);
            this.a.onReceivedClientCertRequest(this.b, new a(clientCertRequest));
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.b.a(webView);
        this.a.onReceivedError(this.b, i, str, str2);
    }

    public void onReceivedError(WebView webView, android.webkit.WebResourceRequest webResourceRequest, final WebResourceError webResourceError) {
        this.b.a(webView);
        AnonymousClass2 r2 = null;
        f fVar = webResourceRequest != null ? new f(webResourceRequest) : null;
        if (webResourceError != null) {
            r2 = new com.tencent.smtt.export.external.interfaces.WebResourceError() {
                public CharSequence getDescription() {
                    return webResourceError.getDescription();
                }

                public int getErrorCode() {
                    return webResourceError.getErrorCode();
                }
            };
        }
        this.a.onReceivedError(this.b, fVar, r2);
    }

    public void onReceivedHttpAuthRequest(WebView webView, android.webkit.HttpAuthHandler httpAuthHandler, String str, String str2) {
        this.b.a(webView);
        this.a.onReceivedHttpAuthRequest(this.b, new b(httpAuthHandler), str, str2);
    }

    public void onReceivedHttpError(WebView webView, android.webkit.WebResourceRequest webResourceRequest, android.webkit.WebResourceResponse webResourceResponse) {
        this.b.a(webView);
        this.a.onReceivedHttpError(this.b, new f(webResourceRequest), new g(webResourceResponse));
    }

    public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
        if (Build.VERSION.SDK_INT >= 12) {
            this.b.a(webView);
            this.a.onReceivedLoginRequest(this.b, str, str2, str3);
        }
    }

    public void onReceivedSslError(WebView webView, android.webkit.SslErrorHandler sslErrorHandler, android.net.http.SslError sslError) {
        if (Build.VERSION.SDK_INT >= 8) {
            this.b.a(webView);
            this.a.onReceivedSslError(this.b, new c(sslErrorHandler), new d(sslError));
        }
    }

    public boolean onRenderProcessGone(WebView webView, final RenderProcessGoneDetail renderProcessGoneDetail) {
        this.b.a(webView);
        return this.a.onRenderProcessGone(this.b, new WebViewClient.RenderProcessGoneDetail() {
            public boolean didCrash() {
                return renderProcessGoneDetail.didCrash();
            }
        });
    }

    public void onScaleChanged(WebView webView, float f2, float f3) {
        this.b.a(webView);
        this.a.onScaleChanged(this.b, f2, f3);
    }

    public void onTooManyRedirects(WebView webView, Message message, Message message2) {
        this.b.a(webView);
        this.a.onTooManyRedirects(this.b, message, message2);
    }

    public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
        this.b.a(webView);
        this.a.onUnhandledKeyEvent(this.b, keyEvent);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x004d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.webkit.WebResourceResponse shouldInterceptRequest(android.webkit.WebView r9, android.webkit.WebResourceRequest r10) {
        /*
            r8 = this;
            int r9 = android.os.Build.VERSION.SDK_INT
            r0 = 0
            r1 = 21
            if (r9 >= r1) goto L_0x0008
            return r0
        L_0x0008:
            if (r10 != 0) goto L_0x000b
            return r0
        L_0x000b:
            r9 = 0
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 24
            if (r1 < r2) goto L_0x0024
            java.lang.String r1 = "isRedirect"
            java.lang.Object r1 = com.tencent.smtt.utils.i.a((java.lang.Object) r10, (java.lang.String) r1)
            boolean r2 = r1 instanceof java.lang.Boolean
            if (r2 == 0) goto L_0x0024
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r9 = r1.booleanValue()
            r4 = r9
            goto L_0x0025
        L_0x0024:
            r4 = 0
        L_0x0025:
            com.tencent.smtt.sdk.SystemWebViewClient$e r9 = new com.tencent.smtt.sdk.SystemWebViewClient$e
            android.net.Uri r1 = r10.getUrl()
            java.lang.String r2 = r1.toString()
            boolean r3 = r10.isForMainFrame()
            boolean r5 = r10.hasGesture()
            java.lang.String r6 = r10.getMethod()
            java.util.Map r7 = r10.getRequestHeaders()
            r1 = r9
            r1.<init>(r2, r3, r4, r5, r6, r7)
            com.tencent.smtt.sdk.WebViewClient r10 = r8.a
            com.tencent.smtt.sdk.WebView r1 = r8.b
            com.tencent.smtt.export.external.interfaces.WebResourceResponse r9 = r10.shouldInterceptRequest((com.tencent.smtt.sdk.WebView) r1, (com.tencent.smtt.export.external.interfaces.WebResourceRequest) r9)
            if (r9 != 0) goto L_0x004e
            return r0
        L_0x004e:
            android.webkit.WebResourceResponse r10 = new android.webkit.WebResourceResponse
            java.lang.String r0 = r9.getMimeType()
            java.lang.String r1 = r9.getEncoding()
            java.io.InputStream r2 = r9.getData()
            r10.<init>(r0, r1, r2)
            java.util.Map r0 = r9.getResponseHeaders()
            r10.setResponseHeaders(r0)
            int r0 = r9.getStatusCode()
            java.lang.String r9 = r9.getReasonPhrase()
            int r1 = r10.getStatusCode()
            if (r0 != r1) goto L_0x0080
            if (r9 == 0) goto L_0x0083
            java.lang.String r1 = r10.getReasonPhrase()
            boolean r1 = r9.equals(r1)
            if (r1 != 0) goto L_0x0083
        L_0x0080:
            r10.setStatusCodeAndReasonPhrase(r0, r9)
        L_0x0083:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.SystemWebViewClient.shouldInterceptRequest(android.webkit.WebView, android.webkit.WebResourceRequest):android.webkit.WebResourceResponse");
    }

    public android.webkit.WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        WebResourceResponse shouldInterceptRequest;
        if (Build.VERSION.SDK_INT >= 11 && (shouldInterceptRequest = this.a.shouldInterceptRequest(this.b, str)) != null) {
            return new android.webkit.WebResourceResponse(shouldInterceptRequest.getMimeType(), shouldInterceptRequest.getEncoding(), shouldInterceptRequest.getData());
        }
        return null;
    }

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        this.b.a(webView);
        return this.a.shouldOverrideKeyEvent(this.b, keyEvent);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, android.webkit.WebResourceRequest webResourceRequest) {
        boolean z;
        String uri = (webResourceRequest == null || webResourceRequest.getUrl() == null) ? null : webResourceRequest.getUrl().toString();
        if (uri == null || this.b.showDebugView(uri)) {
            return true;
        }
        this.b.a(webView);
        if (Build.VERSION.SDK_INT >= 24) {
            Object a2 = i.a((Object) webResourceRequest, "isRedirect");
            if (a2 instanceof Boolean) {
                z = ((Boolean) a2).booleanValue();
                return this.a.shouldOverrideUrlLoading(this.b, (WebResourceRequest) new e(webResourceRequest.getUrl().toString(), webResourceRequest.isForMainFrame(), z, webResourceRequest.hasGesture(), webResourceRequest.getMethod(), webResourceRequest.getRequestHeaders()));
            }
        }
        z = false;
        return this.a.shouldOverrideUrlLoading(this.b, (WebResourceRequest) new e(webResourceRequest.getUrl().toString(), webResourceRequest.isForMainFrame(), z, webResourceRequest.hasGesture(), webResourceRequest.getMethod(), webResourceRequest.getRequestHeaders()));
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str == null || this.b.showDebugView(str)) {
            return true;
        }
        this.b.a(webView);
        return this.a.shouldOverrideUrlLoading(this.b, str);
    }
}
