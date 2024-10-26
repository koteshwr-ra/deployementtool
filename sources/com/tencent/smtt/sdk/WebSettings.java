package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.utils.i;

public class WebSettings {
    public static final int LOAD_CACHE_ELSE_NETWORK = 1;
    public static final int LOAD_CACHE_ONLY = 3;
    public static final int LOAD_DEFAULT = -1;
    public static final int LOAD_NORMAL = 0;
    public static final int LOAD_NO_CACHE = 2;
    private IX5WebSettings a;
    private android.webkit.WebSettings b;
    private boolean c;

    public enum LayoutAlgorithm {
        NORMAL,
        SINGLE_COLUMN,
        NARROW_COLUMNS
    }

    public enum PluginState {
        ON,
        ON_DEMAND,
        OFF
    }

    public enum RenderPriority {
        NORMAL,
        HIGH,
        LOW
    }

    public enum TextSize {
        SMALLEST(50),
        SMALLER(75),
        NORMAL(100),
        LARGER(TbsListener.ErrorCode.DOWNLOAD_THROWABLE),
        LARGEST(150);
        
        int value;

        private TextSize(int i) {
            this.value = i;
        }
    }

    public enum ZoomDensity {
        FAR(150),
        MEDIUM(100),
        CLOSE(75);
        
        int value;

        private ZoomDensity(int i) {
            this.value = i;
        }
    }

    WebSettings(android.webkit.WebSettings webSettings) {
        this.a = null;
        this.b = null;
        this.c = false;
        this.a = null;
        this.b = webSettings;
        this.c = false;
    }

    WebSettings(IX5WebSettings iX5WebSettings) {
        this.a = null;
        this.b = null;
        this.c = false;
        this.a = iX5WebSettings;
        this.b = null;
        this.c = true;
    }

    public static String getDefaultUserAgent(Context context) {
        Object a2;
        if (w.a().b()) {
            return w.a().c().i(context);
        }
        if (Build.VERSION.SDK_INT >= 17 && (a2 = i.a((Class<?>) android.webkit.WebSettings.class, "getDefaultUserAgent", (Class<?>[]) new Class[]{Context.class}, context)) != null) {
            return (String) a2;
        }
        return null;
    }

    @Deprecated
    public boolean enableSmoothTransition() {
        Object a2;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            return iX5WebSettings.enableSmoothTransition();
        }
        if (this.c || this.b == null || Build.VERSION.SDK_INT < 11 || (a2 = i.a((Object) this.b, "enableSmoothTransition")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public boolean getAllowContentAccess() {
        Object a2;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            return iX5WebSettings.getAllowContentAccess();
        }
        if (this.c || this.b == null || Build.VERSION.SDK_INT < 11 || (a2 = i.a((Object) this.b, "getAllowContentAccess")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public boolean getAllowFileAccess() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            return iX5WebSettings.getAllowFileAccess();
        }
        if (this.c || (webSettings = this.b) == null) {
            return false;
        }
        return webSettings.getAllowFileAccess();
    }

    public synchronized boolean getBlockNetworkImage() {
        if (this.c && this.a != null) {
            return this.a.getBlockNetworkImage();
        } else if (this.c || this.b == null) {
            return false;
        } else {
            return this.b.getBlockNetworkImage();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002b, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean getBlockNetworkLoads() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.c     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x0011
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r3.a     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x0011
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r3.a     // Catch:{ all -> 0x002c }
            boolean r0 = r0.getBlockNetworkLoads()     // Catch:{ all -> 0x002c }
            monitor-exit(r3)
            return r0
        L_0x0011:
            boolean r0 = r3.c     // Catch:{ all -> 0x002c }
            r1 = 0
            if (r0 != 0) goto L_0x002a
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x002a
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x002c }
            r2 = 8
            if (r0 < r2) goto L_0x0028
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x002c }
            boolean r0 = r0.getBlockNetworkLoads()     // Catch:{ all -> 0x002c }
            monitor-exit(r3)
            return r0
        L_0x0028:
            monitor-exit(r3)
            return r1
        L_0x002a:
            monitor-exit(r3)
            return r1
        L_0x002c:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.getBlockNetworkLoads():boolean");
    }

    public boolean getBuiltInZoomControls() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            return iX5WebSettings.getBuiltInZoomControls();
        }
        if (this.c || (webSettings = this.b) == null) {
            return false;
        }
        return webSettings.getBuiltInZoomControls();
    }

    public int getCacheMode() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            return iX5WebSettings.getCacheMode();
        }
        if (this.c || (webSettings = this.b) == null) {
            return 0;
        }
        return webSettings.getCacheMode();
    }

    public synchronized String getCursiveFontFamily() {
        if (this.c && this.a != null) {
            return this.a.getCursiveFontFamily();
        } else if (this.c || this.b == null) {
            return "";
        } else {
            return this.b.getCursiveFontFamily();
        }
    }

    public synchronized boolean getDatabaseEnabled() {
        if (this.c && this.a != null) {
            return this.a.getDatabaseEnabled();
        } else if (this.c || this.b == null) {
            return false;
        } else {
            return this.b.getDatabaseEnabled();
        }
    }

    @Deprecated
    public synchronized String getDatabasePath() {
        if (this.c && this.a != null) {
            return this.a.getDatabasePath();
        } else if (this.c || this.b == null) {
            return "";
        } else {
            return this.b.getDatabasePath();
        }
    }

    public synchronized int getDefaultFixedFontSize() {
        if (this.c && this.a != null) {
            return this.a.getDefaultFixedFontSize();
        } else if (this.c || this.b == null) {
            return 0;
        } else {
            return this.b.getDefaultFixedFontSize();
        }
    }

    public synchronized int getDefaultFontSize() {
        if (this.c && this.a != null) {
            return this.a.getDefaultFontSize();
        } else if (this.c || this.b == null) {
            return 0;
        } else {
            return this.b.getDefaultFontSize();
        }
    }

    public synchronized String getDefaultTextEncodingName() {
        if (this.c && this.a != null) {
            return this.a.getDefaultTextEncodingName();
        } else if (this.c || this.b == null) {
            return "";
        } else {
            return this.b.getDefaultTextEncodingName();
        }
    }

    @Deprecated
    public ZoomDensity getDefaultZoom() {
        android.webkit.WebSettings webSettings;
        String name;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            name = iX5WebSettings.getDefaultZoom().name();
        } else if (this.c || (webSettings = this.b) == null) {
            return null;
        } else {
            name = webSettings.getDefaultZoom().name();
        }
        return ZoomDensity.valueOf(name);
    }

    public boolean getDisplayZoomControls() {
        Object a2;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            return iX5WebSettings.getDisplayZoomControls();
        }
        if (this.c || this.b == null || Build.VERSION.SDK_INT < 11 || (a2 = i.a((Object) this.b, "getDisplayZoomControls")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public synchronized boolean getDomStorageEnabled() {
        if (this.c && this.a != null) {
            return this.a.getDomStorageEnabled();
        } else if (this.c || this.b == null) {
            return false;
        } else {
            return this.b.getDomStorageEnabled();
        }
    }

    public synchronized String getFantasyFontFamily() {
        if (this.c && this.a != null) {
            return this.a.getFantasyFontFamily();
        } else if (this.c || this.b == null) {
            return "";
        } else {
            return this.b.getFantasyFontFamily();
        }
    }

    public synchronized String getFixedFontFamily() {
        if (this.c && this.a != null) {
            return this.a.getFixedFontFamily();
        } else if (this.c || this.b == null) {
            return "";
        } else {
            return this.b.getFixedFontFamily();
        }
    }

    public synchronized boolean getJavaScriptCanOpenWindowsAutomatically() {
        if (this.c && this.a != null) {
            return this.a.getJavaScriptCanOpenWindowsAutomatically();
        } else if (this.c || this.b == null) {
            return false;
        } else {
            return this.b.getJavaScriptCanOpenWindowsAutomatically();
        }
    }

    public synchronized boolean getJavaScriptEnabled() {
        if (this.c && this.a != null) {
            return this.a.getJavaScriptEnabled();
        } else if (this.c || this.b == null) {
            return false;
        } else {
            return this.b.getJavaScriptEnabled();
        }
    }

    public synchronized LayoutAlgorithm getLayoutAlgorithm() {
        if (this.c && this.a != null) {
            return LayoutAlgorithm.valueOf(this.a.getLayoutAlgorithm().name());
        } else if (this.c || this.b == null) {
            return null;
        } else {
            return LayoutAlgorithm.valueOf(this.b.getLayoutAlgorithm().name());
        }
    }

    @Deprecated
    public boolean getLightTouchEnabled() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            return iX5WebSettings.getLightTouchEnabled();
        }
        if (this.c || (webSettings = this.b) == null) {
            return false;
        }
        return webSettings.getLightTouchEnabled();
    }

    public boolean getLoadWithOverviewMode() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            return iX5WebSettings.getLoadWithOverviewMode();
        }
        if (this.c || (webSettings = this.b) == null) {
            return false;
        }
        return webSettings.getLoadWithOverviewMode();
    }

    public synchronized boolean getLoadsImagesAutomatically() {
        if (this.c && this.a != null) {
            return this.a.getLoadsImagesAutomatically();
        } else if (this.c || this.b == null) {
            return false;
        } else {
            return this.b.getLoadsImagesAutomatically();
        }
    }

    public boolean getMediaPlaybackRequiresUserGesture() {
        Object a2;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            return iX5WebSettings.getMediaPlaybackRequiresUserGesture();
        }
        if (this.c || this.b == null || Build.VERSION.SDK_INT < 17 || (a2 = i.a((Object) this.b, "getMediaPlaybackRequiresUserGesture")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public synchronized int getMinimumFontSize() {
        if (this.c && this.a != null) {
            return this.a.getMinimumFontSize();
        } else if (this.c || this.b == null) {
            return 0;
        } else {
            return this.b.getMinimumFontSize();
        }
    }

    public synchronized int getMinimumLogicalFontSize() {
        if (this.c && this.a != null) {
            return this.a.getMinimumLogicalFontSize();
        } else if (this.c || this.b == null) {
            return 0;
        } else {
            return this.b.getMinimumLogicalFontSize();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0037, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getMixedContentMode() {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.c     // Catch:{ all -> 0x0038 }
            r1 = -1
            if (r0 == 0) goto L_0x0018
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r5.a     // Catch:{ all -> 0x0038 }
            if (r0 == 0) goto L_0x0018
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r5.a     // Catch:{ all -> 0x0012 }
            int r0 = r0.getMixedContentMode()     // Catch:{ all -> 0x0012 }
            monitor-exit(r5)
            return r0
        L_0x0012:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0038 }
            monitor-exit(r5)
            return r1
        L_0x0018:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0038 }
            r2 = 21
            if (r0 >= r2) goto L_0x0020
            monitor-exit(r5)
            return r1
        L_0x0020:
            android.webkit.WebSettings r0 = r5.b     // Catch:{ all -> 0x0038 }
            java.lang.String r2 = "getMixedContentMode"
            r3 = 0
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ all -> 0x0038 }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0038 }
            java.lang.Object r0 = com.tencent.smtt.utils.i.a((java.lang.Object) r0, (java.lang.String) r2, (java.lang.Class<?>[]) r4, (java.lang.Object[]) r3)     // Catch:{ all -> 0x0038 }
            if (r0 != 0) goto L_0x0030
            goto L_0x0036
        L_0x0030:
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ all -> 0x0038 }
            int r1 = r0.intValue()     // Catch:{ all -> 0x0038 }
        L_0x0036:
            monitor-exit(r5)
            return r1
        L_0x0038:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.getMixedContentMode():int");
    }

    @Deprecated
    public boolean getNavDump() {
        android.webkit.WebSettings webSettings;
        Object a2;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            return iX5WebSettings.getNavDump();
        }
        if (this.c || (webSettings = this.b) == null || (a2 = i.a((Object) webSettings, "getNavDump")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0043, code lost:
        return null;
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.tencent.smtt.sdk.WebSettings.PluginState getPluginState() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.c     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0019
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r3.a     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0019
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r3.a     // Catch:{ all -> 0x0044 }
            com.tencent.smtt.export.external.interfaces.IX5WebSettings$PluginState r0 = r0.getPluginState()     // Catch:{ all -> 0x0044 }
            java.lang.String r0 = r0.name()     // Catch:{ all -> 0x0044 }
            com.tencent.smtt.sdk.WebSettings$PluginState r0 = com.tencent.smtt.sdk.WebSettings.PluginState.valueOf(r0)     // Catch:{ all -> 0x0044 }
            monitor-exit(r3)
            return r0
        L_0x0019:
            boolean r0 = r3.c     // Catch:{ all -> 0x0044 }
            r1 = 0
            if (r0 != 0) goto L_0x0042
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0042
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0044 }
            r2 = 8
            if (r0 < r2) goto L_0x0040
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x0044 }
            java.lang.String r2 = "getPluginState"
            java.lang.Object r0 = com.tencent.smtt.utils.i.a((java.lang.Object) r0, (java.lang.String) r2)     // Catch:{ all -> 0x0044 }
            if (r0 != 0) goto L_0x0034
            monitor-exit(r3)
            return r1
        L_0x0034:
            android.webkit.WebSettings$PluginState r0 = (android.webkit.WebSettings.PluginState) r0     // Catch:{ all -> 0x0044 }
            java.lang.String r0 = r0.name()     // Catch:{ all -> 0x0044 }
            com.tencent.smtt.sdk.WebSettings$PluginState r0 = com.tencent.smtt.sdk.WebSettings.PluginState.valueOf(r0)     // Catch:{ all -> 0x0044 }
            monitor-exit(r3)
            return r0
        L_0x0040:
            monitor-exit(r3)
            return r1
        L_0x0042:
            monitor-exit(r3)
            return r1
        L_0x0044:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.getPluginState():com.tencent.smtt.sdk.WebSettings$PluginState");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0032, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0045, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0049, code lost:
        return false;
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean getPluginsEnabled() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.c     // Catch:{ all -> 0x004a }
            if (r0 == 0) goto L_0x0011
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r3.a     // Catch:{ all -> 0x004a }
            if (r0 == 0) goto L_0x0011
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r3.a     // Catch:{ all -> 0x004a }
            boolean r0 = r0.getPluginsEnabled()     // Catch:{ all -> 0x004a }
            monitor-exit(r3)
            return r0
        L_0x0011:
            boolean r0 = r3.c     // Catch:{ all -> 0x004a }
            r1 = 0
            if (r0 != 0) goto L_0x0048
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x004a }
            if (r0 == 0) goto L_0x0048
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x004a }
            r2 = 17
            if (r0 > r2) goto L_0x0033
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x004a }
            java.lang.String r2 = "getPluginsEnabled"
            java.lang.Object r0 = com.tencent.smtt.utils.i.a((java.lang.Object) r0, (java.lang.String) r2)     // Catch:{ all -> 0x004a }
            if (r0 != 0) goto L_0x002b
            goto L_0x0031
        L_0x002b:
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x004a }
            boolean r1 = r0.booleanValue()     // Catch:{ all -> 0x004a }
        L_0x0031:
            monitor-exit(r3)
            return r1
        L_0x0033:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x004a }
            r2 = 18
            if (r0 != r2) goto L_0x0046
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x004a }
            android.webkit.WebSettings$PluginState r0 = r0.getPluginState()     // Catch:{ all -> 0x004a }
            android.webkit.WebSettings$PluginState r2 = android.webkit.WebSettings.PluginState.ON     // Catch:{ all -> 0x004a }
            if (r2 != r0) goto L_0x0044
            r1 = 1
        L_0x0044:
            monitor-exit(r3)
            return r1
        L_0x0046:
            monitor-exit(r3)
            return r1
        L_0x0048:
            monitor-exit(r3)
            return r1
        L_0x004a:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.getPluginsEnabled():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002e, code lost:
        return r0 == null ? null : (java.lang.String) r0;
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String getPluginsPath() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.c     // Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0011
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r2.a     // Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0011
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r2.a     // Catch:{ all -> 0x0037 }
            java.lang.String r0 = r0.getPluginsPath()     // Catch:{ all -> 0x0037 }
            monitor-exit(r2)
            return r0
        L_0x0011:
            boolean r0 = r2.c     // Catch:{ all -> 0x0037 }
            if (r0 != 0) goto L_0x0033
            android.webkit.WebSettings r0 = r2.b     // Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0033
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0037 }
            r1 = 17
            if (r0 > r1) goto L_0x002f
            android.webkit.WebSettings r0 = r2.b     // Catch:{ all -> 0x0037 }
            java.lang.String r1 = "getPluginsPath"
            java.lang.Object r0 = com.tencent.smtt.utils.i.a((java.lang.Object) r0, (java.lang.String) r1)     // Catch:{ all -> 0x0037 }
            if (r0 != 0) goto L_0x002b
            r0 = 0
            goto L_0x002d
        L_0x002b:
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0037 }
        L_0x002d:
            monitor-exit(r2)
            return r0
        L_0x002f:
            java.lang.String r0 = ""
            monitor-exit(r2)
            return r0
        L_0x0033:
            java.lang.String r0 = ""
            monitor-exit(r2)
            return r0
        L_0x0037:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.getPluginsPath():java.lang.String");
    }

    public boolean getSafeBrowsingEnabled() {
        IX5WebSettings iX5WebSettings;
        if (this.c || this.b == null) {
            if (!this.c || (iX5WebSettings = this.a) == null) {
                return false;
            }
            try {
                return iX5WebSettings.getSafeBrowsingEnabled();
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        } else if (Build.VERSION.SDK_INT >= 26) {
            return this.b.getSafeBrowsingEnabled();
        } else {
            return false;
        }
    }

    public synchronized String getSansSerifFontFamily() {
        if (this.c && this.a != null) {
            return this.a.getSansSerifFontFamily();
        } else if (this.c || this.b == null) {
            return "";
        } else {
            return this.b.getSansSerifFontFamily();
        }
    }

    @Deprecated
    public boolean getSaveFormData() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            return iX5WebSettings.getSaveFormData();
        }
        if (this.c || (webSettings = this.b) == null) {
            return false;
        }
        return webSettings.getSaveFormData();
    }

    @Deprecated
    public boolean getSavePassword() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            return iX5WebSettings.getSavePassword();
        }
        if (this.c || (webSettings = this.b) == null) {
            return false;
        }
        return webSettings.getSavePassword();
    }

    public synchronized String getSerifFontFamily() {
        if (this.c && this.a != null) {
            return this.a.getSerifFontFamily();
        } else if (this.c || this.b == null) {
            return "";
        } else {
            return this.b.getSerifFontFamily();
        }
    }

    public synchronized String getStandardFontFamily() {
        if (this.c && this.a != null) {
            return this.a.getStandardFontFamily();
        } else if (this.c || this.b == null) {
            return "";
        } else {
            return this.b.getStandardFontFamily();
        }
    }

    @Deprecated
    public TextSize getTextSize() {
        android.webkit.WebSettings webSettings;
        String name;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            name = iX5WebSettings.getTextSize().name();
        } else if (this.c || (webSettings = this.b) == null) {
            return null;
        } else {
            name = webSettings.getTextSize().name();
        }
        return TextSize.valueOf(name);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r0 = com.tencent.smtt.utils.i.a((java.lang.Object) r3.b, "getTextZoom");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0032, code lost:
        if (r0 == null) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0035, code lost:
        r1 = ((java.lang.Integer) r0).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x003c, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x003e, code lost:
        return 0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getTextZoom() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.c     // Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x0011
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r3.a     // Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x0011
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r3.a     // Catch:{ all -> 0x003f }
            int r0 = r0.getTextZoom()     // Catch:{ all -> 0x003f }
            monitor-exit(r3)
            return r0
        L_0x0011:
            boolean r0 = r3.c     // Catch:{ all -> 0x003f }
            r1 = 0
            if (r0 != 0) goto L_0x003d
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x003f }
            if (r0 == 0) goto L_0x003d
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x003f }
            r2 = 14
            if (r0 >= r2) goto L_0x0022
            monitor-exit(r3)
            return r1
        L_0x0022:
            android.webkit.WebSettings r0 = r3.b     // Catch:{ Exception -> 0x002a }
            int r0 = r0.getTextZoom()     // Catch:{ Exception -> 0x002a }
            monitor-exit(r3)
            return r0
        L_0x002a:
            android.webkit.WebSettings r0 = r3.b     // Catch:{ all -> 0x003f }
            java.lang.String r2 = "getTextZoom"
            java.lang.Object r0 = com.tencent.smtt.utils.i.a((java.lang.Object) r0, (java.lang.String) r2)     // Catch:{ all -> 0x003f }
            if (r0 != 0) goto L_0x0035
            goto L_0x003b
        L_0x0035:
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ all -> 0x003f }
            int r1 = r0.intValue()     // Catch:{ all -> 0x003f }
        L_0x003b:
            monitor-exit(r3)
            return r1
        L_0x003d:
            monitor-exit(r3)
            return r1
        L_0x003f:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.getTextZoom():int");
    }

    @Deprecated
    public boolean getUseWebViewBackgroundForOverscrollBackground() {
        android.webkit.WebSettings webSettings;
        Object a2;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            return iX5WebSettings.getUseWebViewBackgroundForOverscrollBackground();
        }
        if (this.c || (webSettings = this.b) == null || (a2 = i.a((Object) webSettings, "getUseWebViewBackgroundForOverscrollBackground")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public synchronized boolean getUseWideViewPort() {
        if (this.c && this.a != null) {
            return this.a.getUseWideViewPort();
        } else if (this.c || this.b == null) {
            return false;
        } else {
            return this.b.getUseWideViewPort();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0011, code lost:
        r0 = r1.b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getUserAgentString() {
        /*
            r1 = this;
            boolean r0 = r1.c
            if (r0 == 0) goto L_0x000d
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a
            if (r0 == 0) goto L_0x000d
            java.lang.String r0 = r0.getUserAgentString()
            return r0
        L_0x000d:
            boolean r0 = r1.c
            if (r0 != 0) goto L_0x001a
            android.webkit.WebSettings r0 = r1.b
            if (r0 == 0) goto L_0x001a
            java.lang.String r0 = r0.getUserAgentString()
            return r0
        L_0x001a:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.getUserAgentString():java.lang.String");
    }

    public void setAllowContentAccess(boolean z) {
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setAllowContentAccess(z);
        } else if (!this.c && this.b != null && Build.VERSION.SDK_INT >= 11) {
            i.a((Object) this.b, "setAllowContentAccess", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void setAllowFileAccess(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setAllowFileAccess(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setAllowFileAccess(z);
        }
    }

    public void setAllowFileAccessFromFileURLs(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setAllowFileAccessFromFileURLs(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            i.a((Object) webSettings, "setAllowFileAccessFromFileURLs", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void setAllowUniversalAccessFromFileURLs(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setAllowUniversalAccessFromFileURLs(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            i.a((Object) webSettings, "setAllowUniversalAccessFromFileURLs", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void setAppCacheEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setAppCacheEnabled(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setAppCacheEnabled(z);
        }
    }

    @Deprecated
    public void setAppCacheMaxSize(long j) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setAppCacheMaxSize(j);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setAppCacheMaxSize(j);
        }
    }

    public void setAppCachePath(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setAppCachePath(str);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setAppCachePath(str);
        }
    }

    public void setBlockNetworkImage(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setBlockNetworkImage(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setBlockNetworkImage(z);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setBlockNetworkLoads(boolean r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.c     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r2.a     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r2.a     // Catch:{ all -> 0x0026 }
            r0.setBlockNetworkLoads(r3)     // Catch:{ all -> 0x0026 }
            goto L_0x0022
        L_0x000f:
            boolean r0 = r2.c     // Catch:{ all -> 0x0026 }
            if (r0 != 0) goto L_0x0024
            android.webkit.WebSettings r0 = r2.b     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0024
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0026 }
            r1 = 8
            if (r0 < r1) goto L_0x0022
            android.webkit.WebSettings r0 = r2.b     // Catch:{ all -> 0x0026 }
            r0.setBlockNetworkLoads(r3)     // Catch:{ all -> 0x0026 }
        L_0x0022:
            monitor-exit(r2)
            return
        L_0x0024:
            monitor-exit(r2)
            return
        L_0x0026:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setBlockNetworkLoads(boolean):void");
    }

    public void setBuiltInZoomControls(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setBuiltInZoomControls(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setBuiltInZoomControls(z);
        }
    }

    public void setCacheMode(int i) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setCacheMode(i);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setCacheMode(i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setCursiveFontFamily(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            r0.setCursiveFontFamily(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setCursiveFontFamily(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setCursiveFontFamily(java.lang.String):void");
    }

    public void setDatabaseEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setDatabaseEnabled(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setDatabaseEnabled(z);
        }
    }

    @Deprecated
    public void setDatabasePath(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setDatabasePath(str);
        } else if (!this.c && (webSettings = this.b) != null) {
            i.a((Object) webSettings, "setDatabasePath", (Class<?>[]) new Class[]{String.class}, str);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setDefaultFixedFontSize(int r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            r0.setDefaultFixedFontSize(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setDefaultFixedFontSize(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setDefaultFixedFontSize(int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setDefaultFontSize(int r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            r0.setDefaultFontSize(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setDefaultFontSize(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setDefaultFontSize(int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setDefaultTextEncodingName(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            r0.setDefaultTextEncodingName(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setDefaultTextEncodingName(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setDefaultTextEncodingName(java.lang.String):void");
    }

    @Deprecated
    public void setDefaultZoom(ZoomDensity zoomDensity) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setDefaultZoom(IX5WebSettings.ZoomDensity.valueOf(zoomDensity.name()));
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.valueOf(zoomDensity.name()));
        }
    }

    public void setDisplayZoomControls(boolean z) {
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setDisplayZoomControls(z);
        } else if (!this.c && this.b != null && Build.VERSION.SDK_INT >= 11) {
            i.a((Object) this.b, "setDisplayZoomControls", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void setDomStorageEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setDomStorageEnabled(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setDomStorageEnabled(z);
        }
    }

    @Deprecated
    public void setEnableSmoothTransition(boolean z) {
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setEnableSmoothTransition(z);
        } else if (!this.c && this.b != null && Build.VERSION.SDK_INT >= 11) {
            i.a((Object) this.b, "setEnableSmoothTransition", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setFantasyFontFamily(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            r0.setFantasyFontFamily(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setFantasyFontFamily(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setFantasyFontFamily(java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setFixedFontFamily(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            r0.setFixedFontFamily(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setFixedFontFamily(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setFixedFontFamily(java.lang.String):void");
    }

    @Deprecated
    public void setGeolocationDatabasePath(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setGeolocationDatabasePath(str);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setGeolocationDatabasePath(str);
        }
    }

    public void setGeolocationEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setGeolocationEnabled(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setGeolocationEnabled(z);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setJavaScriptCanOpenWindowsAutomatically(boolean r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            r0.setJavaScriptCanOpenWindowsAutomatically(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setJavaScriptCanOpenWindowsAutomatically(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setJavaScriptCanOpenWindowsAutomatically(boolean):void");
    }

    @Deprecated
    public void setJavaScriptEnabled(boolean z) {
        try {
            if (this.c && this.a != null) {
                this.a.setJavaScriptEnabled(z);
            } else if (!this.c && this.b != null) {
                this.b.setJavaScriptEnabled(z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setLayoutAlgorithm(LayoutAlgorithm layoutAlgorithm) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setLayoutAlgorithm(IX5WebSettings.LayoutAlgorithm.valueOf(layoutAlgorithm.name()));
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.valueOf(layoutAlgorithm.name()));
        }
    }

    @Deprecated
    public void setLightTouchEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setLightTouchEnabled(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setLightTouchEnabled(z);
        }
    }

    public void setLoadWithOverviewMode(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setLoadWithOverviewMode(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setLoadWithOverviewMode(z);
        }
    }

    public void setLoadsImagesAutomatically(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setLoadsImagesAutomatically(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setLoadsImagesAutomatically(z);
        }
    }

    public void setMediaPlaybackRequiresUserGesture(boolean z) {
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setMediaPlaybackRequiresUserGesture(z);
        } else if (!this.c && this.b != null && Build.VERSION.SDK_INT >= 17) {
            i.a((Object) this.b, "setMediaPlaybackRequiresUserGesture", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setMinimumFontSize(int r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            r0.setMinimumFontSize(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setMinimumFontSize(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setMinimumFontSize(int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setMinimumLogicalFontSize(int r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            r0.setMinimumLogicalFontSize(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setMinimumLogicalFontSize(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setMinimumLogicalFontSize(int):void");
    }

    public void setMixedContentMode(int i) {
        if ((!this.c || this.a == null) && !this.c && this.b != null && Build.VERSION.SDK_INT >= 21) {
            i.a((Object) this.b, "setMixedContentMode", (Class<?>[]) new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    @Deprecated
    public void setNavDump(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setNavDump(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            i.a((Object) webSettings, "setNavDump", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void setNeedInitialFocus(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setNeedInitialFocus(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setNeedInitialFocus(z);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0041, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        return;
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setPluginState(com.tencent.smtt.sdk.WebSettings.PluginState r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.c     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0017
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r6.a     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0017
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r6.a     // Catch:{ all -> 0x0044 }
            java.lang.String r7 = r7.name()     // Catch:{ all -> 0x0044 }
            com.tencent.smtt.export.external.interfaces.IX5WebSettings$PluginState r7 = com.tencent.smtt.export.external.interfaces.IX5WebSettings.PluginState.valueOf(r7)     // Catch:{ all -> 0x0044 }
            r0.setPluginState(r7)     // Catch:{ all -> 0x0044 }
            goto L_0x0040
        L_0x0017:
            boolean r0 = r6.c     // Catch:{ all -> 0x0044 }
            if (r0 != 0) goto L_0x0042
            android.webkit.WebSettings r0 = r6.b     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0042
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0044 }
            r1 = 8
            if (r0 < r1) goto L_0x0040
            java.lang.String r7 = r7.name()     // Catch:{ all -> 0x0044 }
            android.webkit.WebSettings$PluginState r7 = android.webkit.WebSettings.PluginState.valueOf(r7)     // Catch:{ all -> 0x0044 }
            android.webkit.WebSettings r0 = r6.b     // Catch:{ all -> 0x0044 }
            java.lang.String r1 = "setPluginState"
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x0044 }
            java.lang.Class<android.webkit.WebSettings$PluginState> r4 = android.webkit.WebSettings.PluginState.class
            r5 = 0
            r3[r5] = r4     // Catch:{ all -> 0x0044 }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0044 }
            r2[r5] = r7     // Catch:{ all -> 0x0044 }
            com.tencent.smtt.utils.i.a((java.lang.Object) r0, (java.lang.String) r1, (java.lang.Class<?>[]) r3, (java.lang.Object[]) r2)     // Catch:{ all -> 0x0044 }
        L_0x0040:
            monitor-exit(r6)
            return
        L_0x0042:
            monitor-exit(r6)
            return
        L_0x0044:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setPluginState(com.tencent.smtt.sdk.WebSettings$PluginState):void");
    }

    @Deprecated
    public void setPluginsEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setPluginsEnabled(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            i.a((Object) webSettings, "setPluginsEnabled", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        return;
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setPluginsPath(java.lang.String r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.c     // Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r6.a     // Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r6.a     // Catch:{ all -> 0x002e }
            r0.setPluginsPath(r7)     // Catch:{ all -> 0x002e }
            goto L_0x002a
        L_0x000f:
            boolean r0 = r6.c     // Catch:{ all -> 0x002e }
            if (r0 != 0) goto L_0x002c
            android.webkit.WebSettings r0 = r6.b     // Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x002c
            android.webkit.WebSettings r0 = r6.b     // Catch:{ all -> 0x002e }
            java.lang.String r1 = "setPluginsPath"
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x002e }
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            r5 = 0
            r3[r5] = r4     // Catch:{ all -> 0x002e }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x002e }
            r2[r5] = r7     // Catch:{ all -> 0x002e }
            com.tencent.smtt.utils.i.a((java.lang.Object) r0, (java.lang.String) r1, (java.lang.Class<?>[]) r3, (java.lang.Object[]) r2)     // Catch:{ all -> 0x002e }
        L_0x002a:
            monitor-exit(r6)
            return
        L_0x002c:
            monitor-exit(r6)
            return
        L_0x002e:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setPluginsPath(java.lang.String):void");
    }

    @Deprecated
    public void setRenderPriority(RenderPriority renderPriority) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setRenderPriority(IX5WebSettings.RenderPriority.valueOf(renderPriority.name()));
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setRenderPriority(WebSettings.RenderPriority.valueOf(renderPriority.name()));
        }
    }

    public void setSafeBrowsingEnabled(boolean z) {
        IX5WebSettings iX5WebSettings;
        if (this.c || this.b == null) {
            if (this.c && (iX5WebSettings = this.a) != null) {
                try {
                    iX5WebSettings.setSafeBrowsingEnabled(z);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        } else if (Build.VERSION.SDK_INT >= 26) {
            this.b.setSafeBrowsingEnabled(z);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setSansSerifFontFamily(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            r0.setSansSerifFontFamily(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setSansSerifFontFamily(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setSansSerifFontFamily(java.lang.String):void");
    }

    @Deprecated
    public void setSaveFormData(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setSaveFormData(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setSaveFormData(z);
        }
    }

    @Deprecated
    public void setSavePassword(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setSavePassword(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setSavePassword(z);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setSerifFontFamily(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            r0.setSerifFontFamily(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setSerifFontFamily(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setSerifFontFamily(java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setStandardFontFamily(java.lang.String r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r1.a     // Catch:{ all -> 0x0020 }
            r0.setStandardFontFamily(r2)     // Catch:{ all -> 0x0020 }
            goto L_0x001c
        L_0x000f:
            boolean r0 = r1.c     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001e
            android.webkit.WebSettings r0 = r1.b     // Catch:{ all -> 0x0020 }
            r0.setStandardFontFamily(r2)     // Catch:{ all -> 0x0020 }
        L_0x001c:
            monitor-exit(r1)
            return
        L_0x001e:
            monitor-exit(r1)
            return
        L_0x0020:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setStandardFontFamily(java.lang.String):void");
    }

    public void setSupportMultipleWindows(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setSupportMultipleWindows(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setSupportMultipleWindows(z);
        }
    }

    public void setSupportZoom(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setSupportZoom(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setSupportZoom(z);
        }
    }

    @Deprecated
    public void setTextSize(TextSize textSize) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setTextSize(IX5WebSettings.TextSize.valueOf(textSize.name()));
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setTextSize(WebSettings.TextSize.valueOf(textSize.name()));
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:16|17|18|19) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0025 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setTextZoom(int r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.c     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r6.a     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x000f
            com.tencent.smtt.export.external.interfaces.IX5WebSettings r0 = r6.a     // Catch:{ all -> 0x003e }
            r0.setTextZoom(r7)     // Catch:{ all -> 0x003e }
            goto L_0x003c
        L_0x000f:
            boolean r0 = r6.c     // Catch:{ all -> 0x003e }
            if (r0 != 0) goto L_0x003c
            android.webkit.WebSettings r0 = r6.b     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x003c
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x003e }
            r1 = 14
            if (r0 >= r1) goto L_0x001f
            monitor-exit(r6)
            return
        L_0x001f:
            android.webkit.WebSettings r0 = r6.b     // Catch:{ Exception -> 0x0025 }
            r0.setTextZoom(r7)     // Catch:{ Exception -> 0x0025 }
            goto L_0x003c
        L_0x0025:
            android.webkit.WebSettings r0 = r6.b     // Catch:{ all -> 0x003e }
            java.lang.String r1 = "setTextZoom"
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ all -> 0x003e }
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ all -> 0x003e }
            r5 = 0
            r3[r5] = r4     // Catch:{ all -> 0x003e }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x003e }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x003e }
            r2[r5] = r7     // Catch:{ all -> 0x003e }
            com.tencent.smtt.utils.i.a((java.lang.Object) r0, (java.lang.String) r1, (java.lang.Class<?>[]) r3, (java.lang.Object[]) r2)     // Catch:{ all -> 0x003e }
        L_0x003c:
            monitor-exit(r6)
            return
        L_0x003e:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.WebSettings.setTextZoom(int):void");
    }

    @Deprecated
    public void setUseWebViewBackgroundForOverscrollBackground(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setUseWebViewBackgroundForOverscrollBackground(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            i.a((Object) webSettings, "setUseWebViewBackgroundForOverscrollBackground", (Class<?>[]) new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void setUseWideViewPort(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setUseWideViewPort(z);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setUseWideViewPort(z);
        }
    }

    public void setUserAgent(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setUserAgent(str);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setUserAgentString(str);
        }
    }

    public void setUserAgentString(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            iX5WebSettings.setUserAgentString(str);
        } else if (!this.c && (webSettings = this.b) != null) {
            webSettings.setUserAgentString(str);
        }
    }

    public synchronized boolean supportMultipleWindows() {
        if (this.c && this.a != null) {
            return this.a.supportMultipleWindows();
        } else if (this.c || this.b == null) {
            return false;
        } else {
            return this.b.supportMultipleWindows();
        }
    }

    public boolean supportZoom() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.c && (iX5WebSettings = this.a) != null) {
            return iX5WebSettings.supportZoom();
        }
        if (this.c || (webSettings = this.b) == null) {
            return false;
        }
        return webSettings.supportZoom();
    }
}
