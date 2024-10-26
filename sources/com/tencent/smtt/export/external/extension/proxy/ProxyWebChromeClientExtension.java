package com.tencent.smtt.export.external.extension.proxy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.MediaAccessPermissionsCallback;
import java.util.HashMap;

public class ProxyWebChromeClientExtension implements IX5WebChromeClientExtension {
    private static boolean sCompatibleNewOnSavePassword = true;
    private static boolean sCompatibleOpenFileChooser = true;
    protected IX5WebChromeClientExtension mWebChromeClient;

    public void acquireWakeLock() {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            iX5WebChromeClientExtension.acquireWakeLock();
        }
    }

    public void addFlashView(View view, ViewGroup.LayoutParams layoutParams) {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            iX5WebChromeClientExtension.addFlashView(view, layoutParams);
        }
    }

    public void exitFullScreenFlash() {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            iX5WebChromeClientExtension.exitFullScreenFlash();
        }
    }

    public Context getApplicationContex() {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            return iX5WebChromeClientExtension.getApplicationContex();
        }
        return null;
    }

    public View getVideoLoadingProgressView() {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            return iX5WebChromeClientExtension.getVideoLoadingProgressView();
        }
        return null;
    }

    public Object getX5WebChromeClientInstance() {
        return this.mWebChromeClient;
    }

    public IX5WebChromeClientExtension getmWebChromeClient() {
        return this.mWebChromeClient;
    }

    public void h5videoExitFullScreen(String str) {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            iX5WebChromeClientExtension.h5videoExitFullScreen(str);
        }
    }

    public void h5videoRequestFullScreen(String str) {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            iX5WebChromeClientExtension.h5videoRequestFullScreen(str);
        }
    }

    public void jsExitFullScreen() {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            iX5WebChromeClientExtension.jsExitFullScreen();
        }
    }

    public void jsRequestFullScreen() {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            iX5WebChromeClientExtension.jsRequestFullScreen();
        }
    }

    public boolean onAddFavorite(IX5WebViewExtension iX5WebViewExtension, String str, String str2, JsResult jsResult) {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            return iX5WebChromeClientExtension.onAddFavorite(iX5WebViewExtension, str, str2, jsResult);
        }
        return false;
    }

    public void onAllMetaDataFinished(IX5WebViewExtension iX5WebViewExtension, HashMap<String, String> hashMap) {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            iX5WebChromeClientExtension.onAllMetaDataFinished(iX5WebViewExtension, hashMap);
        }
    }

    public void onBackforwardFinished(int i) {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            iX5WebChromeClientExtension.onBackforwardFinished(i);
        }
    }

    public void onColorModeChanged(long j) {
    }

    public void onHitTestResultFinished(IX5WebViewExtension iX5WebViewExtension, IX5WebViewBase.HitTestResult hitTestResult) {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            iX5WebChromeClientExtension.onHitTestResultFinished(iX5WebViewExtension, hitTestResult);
        }
    }

    public void onHitTestResultForPluginFinished(IX5WebViewExtension iX5WebViewExtension, IX5WebViewBase.HitTestResult hitTestResult, Bundle bundle) {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            iX5WebChromeClientExtension.onHitTestResultForPluginFinished(iX5WebViewExtension, hitTestResult, bundle);
        }
    }

    public Object onMiscCallBack(String str, Bundle bundle) {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            return iX5WebChromeClientExtension.onMiscCallBack(str, bundle);
        }
        return null;
    }

    public boolean onPageNotResponding(Runnable runnable) {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            return iX5WebChromeClientExtension.onPageNotResponding(runnable);
        }
        return false;
    }

    public boolean onPermissionRequest(String str, long j, MediaAccessPermissionsCallback mediaAccessPermissionsCallback) {
        return false;
    }

    public void onPrepareX5ReadPageDataFinished(IX5WebViewExtension iX5WebViewExtension, HashMap<String, String> hashMap) {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            iX5WebChromeClientExtension.onPrepareX5ReadPageDataFinished(iX5WebViewExtension, hashMap);
        }
    }

    public void onPrintPage() {
    }

    public void onPromptNotScalable(IX5WebViewExtension iX5WebViewExtension) {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            iX5WebChromeClientExtension.onPromptNotScalable(iX5WebViewExtension);
        }
    }

    public void onPromptScaleSaved(IX5WebViewExtension iX5WebViewExtension) {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            iX5WebChromeClientExtension.onPromptScaleSaved(iX5WebViewExtension);
        }
    }

    public boolean onSavePassword(ValueCallback<String> valueCallback, String str, String str2, String str3, String str4, String str5, boolean z) {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null && sCompatibleNewOnSavePassword) {
            try {
                return iX5WebChromeClientExtension.onSavePassword(valueCallback, str, str2, str3, str4, str5, z);
            } catch (NoSuchMethodError e) {
                NoSuchMethodError noSuchMethodError = e;
                if (noSuchMethodError.getMessage() == null || !noSuchMethodError.getMessage().contains("onSavePassword")) {
                    throw noSuchMethodError;
                }
                Log.d("incompatible-oldcore", "IX5WebChromeClientExtension.onSavePassword");
                sCompatibleNewOnSavePassword = false;
            }
        }
        return false;
    }

    public boolean onSavePassword(String str, String str2, String str3, boolean z, Message message) {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension == null) {
            return false;
        }
        try {
            return iX5WebChromeClientExtension.onSavePassword(str, str2, str3, z, message);
        } catch (NoSuchMethodError e) {
            e.printStackTrace();
            return false;
        }
    }

    public void onX5ReadModeAvailableChecked(HashMap<String, String> hashMap) {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            iX5WebChromeClientExtension.onX5ReadModeAvailableChecked(hashMap);
        }
    }

    public void openFileChooser(ValueCallback<Uri[]> valueCallback, String str, String str2) {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null && sCompatibleOpenFileChooser) {
            try {
                iX5WebChromeClientExtension.openFileChooser(valueCallback, str, str2);
            } catch (NoSuchMethodError e) {
                if (e.getMessage() == null || !e.getMessage().contains("openFileChooser")) {
                    throw e;
                }
                Log.d("incompatible-oldcore", "IX5WebChromeClientExtension.openFileChooser");
                sCompatibleOpenFileChooser = false;
            }
        }
    }

    public void releaseWakeLock() {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            iX5WebChromeClientExtension.releaseWakeLock();
        }
    }

    public void requestFullScreenFlash() {
        IX5WebChromeClientExtension iX5WebChromeClientExtension = this.mWebChromeClient;
        if (iX5WebChromeClientExtension != null) {
            iX5WebChromeClientExtension.requestFullScreenFlash();
        }
    }

    public void setWebChromeClientExtend(IX5WebChromeClientExtension iX5WebChromeClientExtension) {
        this.mWebChromeClient = iX5WebChromeClientExtension;
    }
}
