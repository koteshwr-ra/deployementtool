package com.tencent.smtt.export.external.proxy;

import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.QuotaUpdater;

public class ProxyWebChromeClient implements IX5WebChromeClient {
    protected IX5WebChromeClient mWebChromeClient;

    public Bitmap getDefaultVideoPoster() {
        return null;
    }

    public void getVisitedHistory(ValueCallback<String[]> valueCallback) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.getVisitedHistory(valueCallback);
        }
    }

    public IX5WebChromeClient getmWebChromeClient() {
        return this.mWebChromeClient;
    }

    public void onCloseWindow(IX5WebViewBase iX5WebViewBase) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.onCloseWindow(iX5WebViewBase);
        }
    }

    public void onConsoleMessage(String str, int i, String str2) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.onConsoleMessage(str, i, str2);
        }
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            return iX5WebChromeClient.onConsoleMessage(consoleMessage);
        }
        return false;
    }

    public boolean onCreateWindow(IX5WebViewBase iX5WebViewBase, boolean z, boolean z2, Message message) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            return iX5WebChromeClient.onCreateWindow(iX5WebViewBase, z, z2, message);
        }
        return false;
    }

    public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, QuotaUpdater quotaUpdater) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
        }
    }

    public void onGeolocationPermissionsHidePrompt() {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.onGeolocationPermissionsHidePrompt();
        }
    }

    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissionsCallback geolocationPermissionsCallback) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.onGeolocationPermissionsShowPrompt(str, geolocationPermissionsCallback);
        }
    }

    public void onGeolocationStartUpdating(ValueCallback<Location> valueCallback, ValueCallback<Bundle> valueCallback2) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.onGeolocationStartUpdating(valueCallback, valueCallback2);
        }
    }

    public void onGeolocationStopUpdating() {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.onGeolocationStopUpdating();
        }
    }

    public void onHideCustomView() {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.onHideCustomView();
        }
    }

    public boolean onJsAlert(IX5WebViewBase iX5WebViewBase, String str, String str2, JsResult jsResult) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            return iX5WebChromeClient.onJsAlert(iX5WebViewBase, str, str2, jsResult);
        }
        return false;
    }

    public boolean onJsBeforeUnload(IX5WebViewBase iX5WebViewBase, String str, String str2, JsResult jsResult) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            return iX5WebChromeClient.onJsBeforeUnload(iX5WebViewBase, str, str2, jsResult);
        }
        return false;
    }

    public boolean onJsConfirm(IX5WebViewBase iX5WebViewBase, String str, String str2, JsResult jsResult) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            return iX5WebChromeClient.onJsConfirm(iX5WebViewBase, str, str2, jsResult);
        }
        return false;
    }

    public boolean onJsPrompt(IX5WebViewBase iX5WebViewBase, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            return iX5WebChromeClient.onJsPrompt(iX5WebViewBase, str, str2, str3, jsPromptResult);
        }
        return false;
    }

    public boolean onJsTimeout() {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            return iX5WebChromeClient.onJsTimeout();
        }
        return false;
    }

    public void onProgressChanged(IX5WebViewBase iX5WebViewBase, int i) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.onProgressChanged(iX5WebViewBase, i);
        }
    }

    public void onReachedMaxAppCacheSize(long j, long j2, QuotaUpdater quotaUpdater) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
        }
    }

    public void onReceivedIcon(IX5WebViewBase iX5WebViewBase, Bitmap bitmap) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.onReceivedIcon(iX5WebViewBase, bitmap);
        }
    }

    public void onReceivedTitle(IX5WebViewBase iX5WebViewBase, String str) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.onReceivedTitle(iX5WebViewBase, str);
        }
    }

    public void onReceivedTouchIconUrl(IX5WebViewBase iX5WebViewBase, String str, boolean z) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.onReceivedTouchIconUrl(iX5WebViewBase, str, z);
        }
    }

    public void onRequestFocus(IX5WebViewBase iX5WebViewBase) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.onRequestFocus(iX5WebViewBase);
        }
    }

    public void onShowCustomView(View view, int i, IX5WebChromeClient.CustomViewCallback customViewCallback) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.onShowCustomView(view, customViewCallback);
        }
    }

    public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.onShowCustomView(view, customViewCallback);
        }
    }

    public boolean onShowFileChooser(IX5WebViewBase iX5WebViewBase, ValueCallback<Uri[]> valueCallback, IX5WebChromeClient.FileChooserParams fileChooserParams) {
        return false;
    }

    public void openFileChooser(ValueCallback<Uri[]> valueCallback, String str, String str2, boolean z) {
        IX5WebChromeClient iX5WebChromeClient = this.mWebChromeClient;
        if (iX5WebChromeClient != null) {
            iX5WebChromeClient.openFileChooser(valueCallback, str, str2, z);
        }
    }

    public void setWebChromeClient(IX5WebChromeClient iX5WebChromeClient) {
        this.mWebChromeClient = iX5WebChromeClient;
    }
}
