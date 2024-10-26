package com.tencent.smtt.sdk;

import android.os.Build;
import android.webkit.ServiceWorkerController;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import com.tencent.smtt.export.external.interfaces.ServiceWorkerClient;
import com.tencent.smtt.export.external.interfaces.ServiceWorkerWebSettings;
import com.tencent.smtt.sdk.SystemWebViewClient;

public class k extends ServiceWorkerController {
    public ServiceWorkerWebSettings getServiceWorkerWebSettings() {
        if (Build.VERSION.SDK_INT < 24) {
            return null;
        }
        final android.webkit.ServiceWorkerWebSettings serviceWorkerWebSettings = ServiceWorkerController.getInstance().getServiceWorkerWebSettings();
        return new ServiceWorkerWebSettings() {
            public boolean getAllowContentAccess() {
                if (Build.VERSION.SDK_INT >= 24) {
                    return serviceWorkerWebSettings.getAllowContentAccess();
                }
                return false;
            }

            public boolean getAllowFileAccess() {
                if (Build.VERSION.SDK_INT >= 24) {
                    return serviceWorkerWebSettings.getAllowFileAccess();
                }
                return false;
            }

            public boolean getBlockNetworkLoads() {
                if (Build.VERSION.SDK_INT >= 24) {
                    return serviceWorkerWebSettings.getBlockNetworkLoads();
                }
                return false;
            }

            public int getCacheMode() {
                if (Build.VERSION.SDK_INT >= 24) {
                    return serviceWorkerWebSettings.getCacheMode();
                }
                return -1;
            }

            public void setAllowContentAccess(boolean z) {
                if (Build.VERSION.SDK_INT >= 24) {
                    serviceWorkerWebSettings.setAllowContentAccess(z);
                }
            }

            public void setAllowFileAccess(boolean z) {
                if (Build.VERSION.SDK_INT >= 24) {
                    serviceWorkerWebSettings.setAllowContentAccess(z);
                }
            }

            public void setBlockNetworkLoads(boolean z) {
                if (Build.VERSION.SDK_INT >= 24) {
                    serviceWorkerWebSettings.setBlockNetworkLoads(z);
                }
            }

            public void setCacheMode(int i) {
                if (Build.VERSION.SDK_INT >= 24) {
                    serviceWorkerWebSettings.setCacheMode(i);
                }
            }
        };
    }

    public void setServiceWorkerClient(final ServiceWorkerClient serviceWorkerClient) {
        if (Build.VERSION.SDK_INT >= 24) {
            ServiceWorkerController.getInstance().setServiceWorkerClient(new android.webkit.ServiceWorkerClient() {
                public WebResourceResponse shouldInterceptRequest(WebResourceRequest webResourceRequest) {
                    com.tencent.smtt.export.external.interfaces.WebResourceResponse shouldInterceptRequest = serviceWorkerClient.shouldInterceptRequest(new SystemWebViewClient.e(webResourceRequest.getUrl().toString(), webResourceRequest.isForMainFrame(), webResourceRequest.isRedirect(), webResourceRequest.hasGesture(), webResourceRequest.getMethod(), webResourceRequest.getRequestHeaders()));
                    if (shouldInterceptRequest == null) {
                        return null;
                    }
                    WebResourceResponse webResourceResponse = new WebResourceResponse(shouldInterceptRequest.getMimeType(), shouldInterceptRequest.getEncoding(), shouldInterceptRequest.getData());
                    webResourceResponse.setResponseHeaders(shouldInterceptRequest.getResponseHeaders());
                    int statusCode = shouldInterceptRequest.getStatusCode();
                    String reasonPhrase = shouldInterceptRequest.getReasonPhrase();
                    if (statusCode != webResourceResponse.getStatusCode() || (reasonPhrase != null && !reasonPhrase.equals(webResourceResponse.getReasonPhrase()))) {
                        webResourceResponse.setStatusCodeAndReasonPhrase(statusCode, reasonPhrase);
                    }
                    return webResourceResponse;
                }
            });
        }
    }
}
