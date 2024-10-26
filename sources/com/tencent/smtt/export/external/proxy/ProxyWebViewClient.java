package com.tencent.smtt.export.external.proxy;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import com.tencent.smtt.export.external.interfaces.ClientCertRequest;
import com.tencent.smtt.export.external.interfaces.HttpAuthHandler;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.export.external.interfaces.IX5WebViewClient;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;

public abstract class ProxyWebViewClient implements IX5WebViewClient {
    private boolean mCompatibleOnPageStartedOrFinishedMethod = false;
    protected IX5WebViewClient mWebViewClient;

    public void countPVContentCacheCallBack(String str) {
    }

    public void doUpdateVisitedHistory(IX5WebViewBase iX5WebViewBase, String str, boolean z) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            iX5WebViewClient.doUpdateVisitedHistory(iX5WebViewBase, str, z);
        }
    }

    public void onContentSizeChanged(IX5WebViewBase iX5WebViewBase, int i, int i2) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            iX5WebViewClient.onContentSizeChanged(iX5WebViewBase, i, i2);
        }
    }

    public void onDetectedBlankScreen(IX5WebViewBase iX5WebViewBase, String str, int i) {
    }

    public void onFormResubmission(IX5WebViewBase iX5WebViewBase, Message message, Message message2) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            iX5WebViewClient.onFormResubmission(iX5WebViewBase, message, message2);
        }
    }

    public void onLoadResource(IX5WebViewBase iX5WebViewBase, String str) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            iX5WebViewClient.onLoadResource(iX5WebViewBase, str);
        }
    }

    public void onPageCommitVisible(IX5WebViewBase iX5WebViewBase, String str) {
    }

    public void onPageFinished(IX5WebViewBase iX5WebViewBase, int i, int i2, String str) {
    }

    public void onPageFinished(IX5WebViewBase iX5WebViewBase, String str) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            iX5WebViewClient.onPageFinished(iX5WebViewBase, str);
        }
    }

    public void onPageStarted(IX5WebViewBase iX5WebViewBase, int i, int i2, String str, Bitmap bitmap) {
    }

    public void onPageStarted(IX5WebViewBase iX5WebViewBase, String str, Bitmap bitmap) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            iX5WebViewClient.onPageStarted(iX5WebViewBase, str, bitmap);
        }
    }

    public void onReceivedClientCertRequest(IX5WebViewBase iX5WebViewBase, ClientCertRequest clientCertRequest) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            iX5WebViewClient.onReceivedClientCertRequest(iX5WebViewBase, clientCertRequest);
        }
    }

    public void onReceivedError(IX5WebViewBase iX5WebViewBase, int i, String str, String str2) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            iX5WebViewClient.onReceivedError(iX5WebViewBase, i, str, str2);
        }
    }

    public void onReceivedError(IX5WebViewBase iX5WebViewBase, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            iX5WebViewClient.onReceivedError(iX5WebViewBase, webResourceRequest, webResourceError);
        }
    }

    public void onReceivedHttpAuthRequest(IX5WebViewBase iX5WebViewBase, HttpAuthHandler httpAuthHandler, String str, String str2) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            iX5WebViewClient.onReceivedHttpAuthRequest(iX5WebViewBase, httpAuthHandler, str, str2);
        }
    }

    public void onReceivedHttpError(IX5WebViewBase iX5WebViewBase, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            iX5WebViewClient.onReceivedHttpError(iX5WebViewBase, webResourceRequest, webResourceResponse);
        }
    }

    public void onReceivedLoginRequest(IX5WebViewBase iX5WebViewBase, String str, String str2, String str3) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            iX5WebViewClient.onReceivedLoginRequest(iX5WebViewBase, str, str2, str3);
        }
    }

    public void onReceivedSslError(IX5WebViewBase iX5WebViewBase, SslErrorHandler sslErrorHandler, SslError sslError) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            iX5WebViewClient.onReceivedSslError(iX5WebViewBase, sslErrorHandler, sslError);
        }
    }

    public void onScaleChanged(IX5WebViewBase iX5WebViewBase, float f, float f2) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            iX5WebViewClient.onScaleChanged(iX5WebViewBase, f, f2);
        }
    }

    public void onTooManyRedirects(IX5WebViewBase iX5WebViewBase, Message message, Message message2) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            iX5WebViewClient.onTooManyRedirects(iX5WebViewBase, message, message2);
        }
    }

    public void onUnhandledKeyEvent(IX5WebViewBase iX5WebViewBase, KeyEvent keyEvent) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            iX5WebViewClient.onUnhandledKeyEvent(iX5WebViewBase, keyEvent);
        }
    }

    public void setWebViewClient(IX5WebViewClient iX5WebViewClient) {
        this.mWebViewClient = iX5WebViewClient;
    }

    public WebResourceResponse shouldInterceptRequest(IX5WebViewBase iX5WebViewBase, WebResourceRequest webResourceRequest) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            return iX5WebViewClient.shouldInterceptRequest(iX5WebViewBase, webResourceRequest);
        }
        return null;
    }

    public WebResourceResponse shouldInterceptRequest(IX5WebViewBase iX5WebViewBase, WebResourceRequest webResourceRequest, Bundle bundle) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            return iX5WebViewClient.shouldInterceptRequest(iX5WebViewBase, webResourceRequest, bundle);
        }
        return null;
    }

    public WebResourceResponse shouldInterceptRequest(IX5WebViewBase iX5WebViewBase, String str) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        if (iX5WebViewClient != null) {
            return iX5WebViewClient.shouldInterceptRequest(iX5WebViewBase, str);
        }
        return null;
    }

    public boolean shouldOverrideKeyEvent(IX5WebViewBase iX5WebViewBase, KeyEvent keyEvent) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        return iX5WebViewClient != null && iX5WebViewClient.shouldOverrideKeyEvent(iX5WebViewBase, keyEvent);
    }

    public boolean shouldOverrideUrlLoading(IX5WebViewBase iX5WebViewBase, WebResourceRequest webResourceRequest) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        return iX5WebViewClient != null && iX5WebViewClient.shouldOverrideUrlLoading(iX5WebViewBase, webResourceRequest);
    }

    public boolean shouldOverrideUrlLoading(IX5WebViewBase iX5WebViewBase, String str) {
        IX5WebViewClient iX5WebViewClient = this.mWebViewClient;
        return iX5WebViewClient != null && iX5WebViewClient.shouldOverrideUrlLoading(iX5WebViewBase, str);
    }
}
