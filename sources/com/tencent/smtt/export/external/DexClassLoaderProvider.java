package com.tencent.smtt.export.external;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;

public class DexClassLoaderProvider extends DexClassLoader {
    private static final String IS_FIRST_LOAD_DEX_FLAG_FILE = "is_first_load_dex_flag_file";
    private static final String LAST_DEX_NAME = "tbs_jars_fusion_dex.jar";
    private static final long LOAD_DEX_DELAY = 3000;
    private static final String LOGTAG = "dexloader";
    protected static DexClassLoader mClassLoaderOriginal = null;
    /* access modifiers changed from: private */
    public static Context mContext = null;
    /* access modifiers changed from: private */
    public static boolean mForceLoadDexFlag = false;
    private static DexClassLoaderProvider mInstance;
    private static String mRealDexPath;
    protected static Service mService;
    private SpeedyDexClassLoader mClassLoader = null;

    private static class SpeedyDexClassLoader extends BaseDexClassLoader {
        public SpeedyDexClassLoader(String str, File file, String str2, ClassLoader classLoader) {
            super(str, (File) null, str2, classLoader);
        }

        public Package definePackage(String str, String str2, String str3, String str4, String str5, String str6, String str7, URL url) throws IllegalArgumentException {
            return super.definePackage(str, str2, str3, str4, str5, str6, str7, url);
        }

        public Class<?> findClass(String str) throws ClassNotFoundException {
            return super.findClass(str);
        }

        public URL findResource(String str) {
            return super.findResource(str);
        }

        public Enumeration<URL> findResources(String str) {
            return super.findResources(str);
        }

        public synchronized Package getPackage(String str) {
            return super.getPackage(str);
        }

        public Package[] getPackages() {
            return super.getPackages();
        }

        public Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
            return super.loadClass(str, z);
        }
    }

    private DexClassLoaderProvider(String str, String str2, String str3, ClassLoader classLoader, boolean z) {
        super(str, str2, str3, classLoader);
        if (z) {
            Log.e(LOGTAG, "SpeedyDexClassLoader: " + mRealDexPath);
            this.mClassLoader = new SpeedyDexClassLoader(mRealDexPath, (File) null, str3, classLoader);
            return;
        }
        Log.e(LOGTAG, "DexClassLoader: " + mRealDexPath);
        this.mClassLoader = null;
    }

    public static DexClassLoader createDexClassLoader(String str, String str2, String str3, ClassLoader classLoader, Context context) {
        Log.i(LOGTAG, "new DexClassLoaderDelegate: " + str + ", context: " + context);
        mContext = context.getApplicationContext();
        mRealDexPath = str;
        int lastIndexOf = str.lastIndexOf("/") + 1;
        String str4 = str.substring(0, lastIndexOf) + "fake_dex.jar";
        String substring = str.substring(lastIndexOf);
        if (!supportSpeedyClassLoader() || !is_first_load_tbs_dex(str2, substring)) {
            Log.d(LOGTAG, "new DexClassLoaderDelegate -- real: " + str);
            mInstance = new DexClassLoaderProvider(str, str2, str3, classLoader, false);
        } else {
            Log.d(LOGTAG, "new DexClassLoaderDelegate -- fake: " + str4);
            set_first_load_tbs_dex(str2, substring);
            mInstance = new DexClassLoaderProvider(str4, str2, str3, classLoader, true);
            doAsyncDexLoad(substring, str, str2, str3, classLoader);
        }
        return mInstance;
    }

    private static void doAsyncDexLoad(final String str, final String str2, final String str3, final String str4, ClassLoader classLoader) {
        if (shouldUseDexLoaderService()) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    try {
                        ArrayList arrayList = new ArrayList(4);
                        arrayList.add(0, str);
                        arrayList.add(1, str2);
                        arrayList.add(2, str3);
                        arrayList.add(3, str4);
                        Intent intent = new Intent(DexClassLoaderProvider.mContext, DexClassLoaderProviderService.class);
                        intent.putStringArrayListExtra("dex2oat", arrayList);
                        DexClassLoaderProvider.mContext.startService(intent);
                        Log.d(DexClassLoaderProvider.LOGTAG, "shouldUseDexLoaderService(" + str + ", " + intent + ")");
                    } catch (SecurityException e) {
                        Log.e(DexClassLoaderProvider.LOGTAG, "start DexLoaderService exception", e);
                    } catch (Throwable th) {
                        Log.e(DexClassLoaderProvider.LOGTAG, "after shouldUseDexLoaderService exception: " + th);
                    }
                }
            }, LOAD_DEX_DELAY);
            return;
        }
        Log.d(LOGTAG, "Background real dex loading(" + str + ")");
        final String str5 = str2;
        final String str6 = str3;
        final String str7 = str4;
        final ClassLoader classLoader2 = classLoader;
        final String str8 = str;
        new Timer().schedule(new TimerTask() {
            /* JADX WARNING: Removed duplicated region for block: B:14:0x0078 A[Catch:{ all -> 0x0111 }] */
            /* JADX WARNING: Removed duplicated region for block: B:15:0x0079 A[Catch:{ all -> 0x0111 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r12 = this;
                    java.lang.String r0 = ", "
                    java.lang.String r1 = "dexloader"
                    java.lang.String r2 = r4     // Catch:{ all -> 0x0111 }
                    java.lang.String r3 = ".jar"
                    java.lang.String r4 = ".dex"
                    java.lang.String r2 = r2.replace(r3, r4)     // Catch:{ all -> 0x0111 }
                    java.io.File r3 = new java.io.File     // Catch:{ all -> 0x0111 }
                    r3.<init>(r2)     // Catch:{ all -> 0x0111 }
                    boolean r2 = r3.exists()     // Catch:{ all -> 0x0111 }
                    r4 = 1
                    r5 = 0
                    java.lang.String r6 = ""
                    if (r2 == 0) goto L_0x0040
                    long r7 = r3.length()     // Catch:{ all -> 0x0111 }
                    r9 = 0
                    int r2 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
                    if (r2 == 0) goto L_0x0040
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0111 }
                    r2.<init>()     // Catch:{ all -> 0x0111 }
                    r2.append(r6)     // Catch:{ all -> 0x0111 }
                    r2.append(r3)     // Catch:{ all -> 0x0111 }
                    java.lang.String r3 = " existed!"
                    r2.append(r3)     // Catch:{ all -> 0x0111 }
                    java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0111 }
                    android.util.Log.d(r1, r2)     // Catch:{ all -> 0x0111 }
                    r2 = 1
                    goto L_0x0058
                L_0x0040:
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0111 }
                    r2.<init>()     // Catch:{ all -> 0x0111 }
                    r2.append(r6)     // Catch:{ all -> 0x0111 }
                    r2.append(r3)     // Catch:{ all -> 0x0111 }
                    java.lang.String r3 = " does not existed!"
                    r2.append(r3)     // Catch:{ all -> 0x0111 }
                    java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0111 }
                    android.util.Log.d(r1, r2)     // Catch:{ all -> 0x0111 }
                    r2 = 0
                L_0x0058:
                    java.io.File r3 = new java.io.File     // Catch:{ all -> 0x0111 }
                    java.lang.String r7 = r5     // Catch:{ all -> 0x0111 }
                    r3.<init>(r7)     // Catch:{ all -> 0x0111 }
                    java.io.File r7 = new java.io.File     // Catch:{ all -> 0x0111 }
                    java.lang.String r8 = r4     // Catch:{ all -> 0x0111 }
                    r7.<init>(r8)     // Catch:{ all -> 0x0111 }
                    boolean r8 = r3.exists()     // Catch:{ all -> 0x0111 }
                    boolean r3 = r3.isDirectory()     // Catch:{ all -> 0x0111 }
                    boolean r7 = r7.exists()     // Catch:{ all -> 0x0111 }
                    if (r8 == 0) goto L_0x00eb
                    if (r3 == 0) goto L_0x00eb
                    if (r7 != 0) goto L_0x0079
                    goto L_0x00eb
                L_0x0079:
                    long r7 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0111 }
                    dalvik.system.DexClassLoader r0 = new dalvik.system.DexClassLoader     // Catch:{ all -> 0x0111 }
                    java.lang.String r3 = r4     // Catch:{ all -> 0x0111 }
                    java.lang.String r9 = r5     // Catch:{ all -> 0x0111 }
                    java.lang.String r10 = r6     // Catch:{ all -> 0x0111 }
                    java.lang.ClassLoader r11 = r7     // Catch:{ all -> 0x0111 }
                    r0.<init>(r3, r9, r10, r11)     // Catch:{ all -> 0x0111 }
                    long r9 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0111 }
                    long r9 = r9 - r7
                    java.lang.String r0 = "load_dex completed -- cl_cost: %d, existed: %b"
                    r3 = 2
                    java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0111 }
                    java.lang.Long r7 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x0111 }
                    r3[r5] = r7     // Catch:{ all -> 0x0111 }
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x0111 }
                    r3[r4] = r2     // Catch:{ all -> 0x0111 }
                    java.lang.String r0 = java.lang.String.format(r0, r3)     // Catch:{ all -> 0x0111 }
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0111 }
                    r2.<init>()     // Catch:{ all -> 0x0111 }
                    r2.append(r6)     // Catch:{ all -> 0x0111 }
                    r2.append(r0)     // Catch:{ all -> 0x0111 }
                    java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0111 }
                    android.util.Log.d(r1, r0)     // Catch:{ all -> 0x0111 }
                    boolean r0 = com.tencent.smtt.export.external.DexClassLoaderProvider.mForceLoadDexFlag     // Catch:{ all -> 0x0111 }
                    if (r0 == 0) goto L_0x0126
                    java.lang.String r0 = "tbs_jars_fusion_dex.jar"
                    java.lang.String r2 = r8     // Catch:{ all -> 0x0111 }
                    boolean r0 = r0.equals(r2)     // Catch:{ all -> 0x0111 }
                    if (r0 == 0) goto L_0x0126
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0111 }
                    r0.<init>()     // Catch:{ all -> 0x0111 }
                    java.lang.String r2 = "Stop provider service after loading "
                    r0.append(r2)     // Catch:{ all -> 0x0111 }
                    java.lang.String r2 = r8     // Catch:{ all -> 0x0111 }
                    r0.append(r2)     // Catch:{ all -> 0x0111 }
                    java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0111 }
                    android.util.Log.d(r1, r0)     // Catch:{ all -> 0x0111 }
                    android.app.Service r0 = com.tencent.smtt.export.external.DexClassLoaderProvider.mService     // Catch:{ all -> 0x0111 }
                    if (r0 == 0) goto L_0x0126
                    java.lang.String r0 = "##Stop service##... "
                    android.util.Log.d(r1, r0)     // Catch:{ all -> 0x0111 }
                    android.app.Service r0 = com.tencent.smtt.export.external.DexClassLoaderProvider.mService     // Catch:{ all -> 0x0111 }
                    r0.stopSelf()     // Catch:{ all -> 0x0111 }
                    goto L_0x0126
                L_0x00eb:
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0111 }
                    r2.<init>()     // Catch:{ all -> 0x0111 }
                    java.lang.String r4 = "dex loading exception("
                    r2.append(r4)     // Catch:{ all -> 0x0111 }
                    r2.append(r8)     // Catch:{ all -> 0x0111 }
                    r2.append(r0)     // Catch:{ all -> 0x0111 }
                    r2.append(r3)     // Catch:{ all -> 0x0111 }
                    r2.append(r0)     // Catch:{ all -> 0x0111 }
                    r2.append(r7)     // Catch:{ all -> 0x0111 }
                    java.lang.String r0 = ")"
                    r2.append(r0)     // Catch:{ all -> 0x0111 }
                    java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0111 }
                    android.util.Log.d(r1, r0)     // Catch:{ all -> 0x0111 }
                    return
                L_0x0111:
                    r0 = move-exception
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "@AsyncDexLoad task exception: "
                    r2.append(r3)
                    r2.append(r0)
                    java.lang.String r0 = r2.toString()
                    android.util.Log.e(r1, r0)
                L_0x0126:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.export.external.DexClassLoaderProvider.AnonymousClass2.run():void");
            }
        }, LOAD_DEX_DELAY);
    }

    private static boolean is_first_load_tbs_dex(String str, String str2) {
        if (mForceLoadDexFlag) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append("_");
        sb.append(IS_FIRST_LOAD_DEX_FLAG_FILE);
        return !new File(str, sb.toString()).exists();
    }

    static void setForceLoadDexFlag(boolean z, Service service) {
        mForceLoadDexFlag = z;
        mService = service;
    }

    private static void set_first_load_tbs_dex(String str, String str2) {
        File file = new File(str, str2 + "_" + IS_FIRST_LOAD_DEX_FLAG_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static boolean shouldUseDexLoaderService() {
        return !mForceLoadDexFlag && DexLoader.mCanUseDexLoaderProviderService;
    }

    private static boolean supportSpeedyClassLoader() {
        return Build.VERSION.SDK_INT != 21 || DexLoader.mCanUseDexLoaderProviderService;
    }

    private boolean useSelfClassloader() {
        return this.mClassLoader == null;
    }

    public void clearAssertionStatus() {
        if (useSelfClassloader()) {
            super.clearAssertionStatus();
        } else {
            this.mClassLoader.clearAssertionStatus();
        }
    }

    /* access modifiers changed from: protected */
    public Package definePackage(String str, String str2, String str3, String str4, String str5, String str6, String str7, URL url) throws IllegalArgumentException {
        return useSelfClassloader() ? super.definePackage(str, str2, str3, str4, str5, str6, str7, url) : this.mClassLoader.definePackage(str, str2, str3, str4, str5, str6, str7, url);
    }

    /* access modifiers changed from: protected */
    public Class<?> findClass(String str) throws ClassNotFoundException {
        return useSelfClassloader() ? super.findClass(str) : this.mClassLoader.findClass(str);
    }

    public String findLibrary(String str) {
        return useSelfClassloader() ? super.findLibrary(str) : this.mClassLoader.findLibrary(str);
    }

    /* access modifiers changed from: protected */
    public URL findResource(String str) {
        return useSelfClassloader() ? super.findResource(str) : this.mClassLoader.findResource(str);
    }

    /* access modifiers changed from: protected */
    public Enumeration<URL> findResources(String str) {
        return useSelfClassloader() ? super.findResources(str) : this.mClassLoader.findResources(str);
    }

    /* access modifiers changed from: protected */
    public synchronized Package getPackage(String str) {
        if (useSelfClassloader()) {
            return super.getPackage(str);
        }
        return this.mClassLoader.getPackage(str);
    }

    /* access modifiers changed from: protected */
    public Package[] getPackages() {
        return useSelfClassloader() ? super.getPackages() : this.mClassLoader.getPackages();
    }

    public URL getResource(String str) {
        return useSelfClassloader() ? super.getResource(str) : this.mClassLoader.getResource(str);
    }

    public InputStream getResourceAsStream(String str) {
        return useSelfClassloader() ? getResourceAsStream(str) : this.mClassLoader.getResourceAsStream(str);
    }

    public Enumeration<URL> getResources(String str) throws IOException {
        return useSelfClassloader() ? super.getResources(str) : this.mClassLoader.getResources(str);
    }

    public Class<?> loadClass(String str) throws ClassNotFoundException {
        return useSelfClassloader() ? super.loadClass(str) : this.mClassLoader.loadClass(str);
    }

    /* access modifiers changed from: protected */
    public Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
        return useSelfClassloader() ? super.loadClass(str, z) : this.mClassLoader.loadClass(str, z);
    }

    public void setClassAssertionStatus(String str, boolean z) {
        if (useSelfClassloader()) {
            super.setClassAssertionStatus(str, z);
        } else {
            this.mClassLoader.setClassAssertionStatus(str, z);
        }
    }

    public void setDefaultAssertionStatus(boolean z) {
        if (useSelfClassloader()) {
            super.setDefaultAssertionStatus(z);
        } else {
            this.mClassLoader.setDefaultAssertionStatus(z);
        }
    }

    public void setPackageAssertionStatus(String str, boolean z) {
        if (useSelfClassloader()) {
            super.setPackageAssertionStatus(str, z);
        } else {
            this.mClassLoader.setPackageAssertionStatus(str, z);
        }
    }

    public String toString() {
        return useSelfClassloader() ? super.toString() : this.mClassLoader.toString();
    }
}
