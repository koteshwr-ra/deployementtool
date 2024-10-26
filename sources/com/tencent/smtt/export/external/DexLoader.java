package com.tencent.smtt.export.external;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import dalvik.system.DexClassLoader;
import dalvik.system.VMStack;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class DexLoader {
    private static final String JAVACORE_PACKAGE_PREFIX = "org.chromium";
    private static final String TAF_PACKAGE_PREFIX = "com.taf";
    private static final String TAG = "DexLoader";
    private static final String TBS_FUSION_DEX = "tbs_jars_fusion_dex";
    private static final String TBS_WEBVIEW_DEX = "webview_dex";
    private static final String TENCENT_PACKAGE_PREFIX = "com.tencent";
    static boolean mCanUseDexLoaderProviderService = true;
    /* access modifiers changed from: private */
    public static boolean mMttClassUseCorePrivate = false;
    private static boolean mUseSpeedyClassLoader = false;
    private static boolean mUseTbsCorePrivateClassLoader = false;
    private DexClassLoader mClassLoader;

    private static class TbsCorePrivateClassLoader extends DexClassLoader {
        public TbsCorePrivateClassLoader(String str, String str2, String str3, ClassLoader classLoader) {
            super(str, str2, str3, classLoader);
        }

        /* access modifiers changed from: protected */
        public Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
            ClassLoader parent;
            if (str == null) {
                return super.loadClass(str, z);
            }
            boolean startsWith = str.startsWith(DexLoader.JAVACORE_PACKAGE_PREFIX);
            if (DexLoader.mMttClassUseCorePrivate) {
                startsWith = startsWith || str.startsWith(DexLoader.TENCENT_PACKAGE_PREFIX) || str.startsWith(DexLoader.TAF_PACKAGE_PREFIX);
            }
            if (!startsWith) {
                return super.loadClass(str, z);
            }
            Class<?> findLoadedClass = findLoadedClass(str);
            if (findLoadedClass != null) {
                return findLoadedClass;
            }
            try {
                Log.d(DexLoader.TAG, "WebCoreClassLoader - loadClass(" + str + "," + z + ")...");
                findLoadedClass = findClass(str);
            } catch (ClassNotFoundException unused) {
            }
            return (findLoadedClass != null || (parent = getParent()) == null) ? findLoadedClass : parent.loadClass(str);
        }
    }

    public DexLoader(Context context, String str, String str2) {
        this(context, new String[]{str}, str2);
    }

    public DexLoader(Context context, String[] strArr, String str) {
        this((String) null, context, strArr, str);
    }

    public DexLoader(Context context, String[] strArr, String str, DexLoader dexLoader) {
        DexClassLoader classLoader = dexLoader.getClassLoader();
        for (String createDexClassLoader : strArr) {
            classLoader = createDexClassLoader(createDexClassLoader, str, context.getApplicationInfo().nativeLibraryDir, classLoader, context);
            this.mClassLoader = classLoader;
        }
    }

    public DexLoader(Context context, String[] strArr, String str, String str2) {
        ClassLoader classLoader = context.getClassLoader();
        String str3 = context.getApplicationInfo().nativeLibraryDir;
        if (!TextUtils.isEmpty(str2)) {
            str3 = str3 + File.pathSeparator + str2;
        }
        int i = 0;
        DexClassLoader dexClassLoader = classLoader;
        while (i < strArr.length) {
            DexClassLoader createDexClassLoader = createDexClassLoader(strArr[i], str, str3, dexClassLoader, context);
            this.mClassLoader = createDexClassLoader;
            i++;
            dexClassLoader = createDexClassLoader;
        }
    }

    public DexLoader(String str, Context context, String[] strArr, String str2) {
        this(str, context, strArr, str2, (Map<String, Object>) null);
    }

    public DexLoader(String str, Context context, String[] strArr, String str2, Map<String, Object> map) {
        initTbsSettings(map);
        ClassLoader callingClassLoader = VMStack.getCallingClassLoader();
        callingClassLoader = callingClassLoader == null ? context.getClassLoader() : callingClassLoader;
        Log.d("dexloader", "Set base classLoader for DexClassLoader: " + callingClassLoader);
        int i = 0;
        DexClassLoader dexClassLoader = callingClassLoader;
        while (i < strArr.length) {
            DexClassLoader createDexClassLoader = createDexClassLoader(strArr[i], str2, str, dexClassLoader, context);
            this.mClassLoader = createDexClassLoader;
            i++;
            dexClassLoader = createDexClassLoader;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x01bf, code lost:
        if (r14 == null) goto L_0x01dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x01d0, code lost:
        if (r14 != null) goto L_0x01d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x01d2, code lost:
        r14.e();
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x01d9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private dalvik.system.DexClassLoader createDexClassLoader(java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.ClassLoader r20, android.content.Context r21) {
        /*
            r16 = this;
            r1 = r17
            r2 = r18
            r3 = r19
            r4 = r20
            r5 = r21
            java.lang.String r0 = "DexLoader"
            java.lang.String r6 = ""
            int r7 = android.os.Build.VERSION.SDK_INT
            r8 = 29
            if (r7 < r8) goto L_0x01dd
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r1)
            java.lang.String r8 = "_code"
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r1)
            java.lang.String r9 = "_name"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r1)
            java.lang.String r10 = "_display"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            java.lang.String r10 = "tbs_oat_status"
            r11 = 0
            android.content.SharedPreferences r10 = r5.getSharedPreferences(r10, r11)
            java.io.File r12 = new java.io.File
            r12.<init>(r1)
            java.io.File r13 = new java.io.File
            java.lang.String r14 = "tbs"
            java.io.File r14 = r5.getDir(r14, r11)
            java.lang.String r15 = "core_private"
            r13.<init>(r14, r15)
            r15 = -1
            int r14 = r10.getInt(r7, r15)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            java.lang.String r15 = r10.getString(r8, r6)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            java.lang.String r11 = r10.getString(r9, r6)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            java.lang.String r5 = r21.getPackageName()     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            android.content.pm.PackageManager r2 = r21.getPackageManager()     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            r3 = 0
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r5, r3)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            int r3 = r2.versionCode     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            java.lang.String r2 = r2.versionName     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            java.lang.String r5 = android.os.Build.DISPLAY     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            r4.<init>()     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            java.lang.String r1 = "createDexClassLoader,old VerisonCode="
            r4.append(r1)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            r4.append(r15)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            java.lang.String r1 = ";newVersionCode="
            r4.append(r1)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            r4.append(r3)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            java.lang.String r1 = "oldVersionName"
            r4.append(r1)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            r4.append(r15)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            java.lang.String r1 = ";newVersionName+"
            r4.append(r1)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            r4.append(r2)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            java.lang.String r1 = "oldDisplay"
            r4.append(r1)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            r4.append(r11)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            java.lang.String r1 = ";newDisplay="
            r4.append(r1)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            r4.append(r5)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            java.lang.String r1 = r4.toString()     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            android.util.Log.i(r0, r1)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            if (r3 != r14) goto L_0x00ce
            boolean r1 = r2.equals(r15)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            if (r1 == 0) goto L_0x00ce
            boolean r1 = r5.equals(r11)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            if (r1 != 0) goto L_0x00cb
            goto L_0x00ce
        L_0x00cb:
            r14 = 0
            goto L_0x01bf
        L_0x00ce:
            java.lang.String r1 = "version updated!,clear oat file"
            android.util.Log.e(r0, r1)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            com.tencent.smtt.utils.k r1 = new com.tencent.smtt.utils.k     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            r4.<init>()     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            java.lang.String r11 = r12.getName()     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            r4.append(r11)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            java.lang.String r11 = "_loading.lock"
            r4.append(r11)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            r1.<init>(r13, r4)     // Catch:{ Exception -> 0x01cb, all -> 0x01c8 }
            r1.b()     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r4 = -1
            int r4 = r10.getInt(r7, r4)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r11 = r10.getString(r8, r6)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r6 = r10.getString(r9, r6)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            if (r3 != r4) goto L_0x010b
            boolean r4 = r2.equals(r11)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            if (r4 == 0) goto L_0x010b
            boolean r4 = r5.equals(r6)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            if (r4 != 0) goto L_0x01be
        L_0x010b:
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r6 = r12.getParent()     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r11 = "oat"
            r4.<init>(r6, r11)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r6 = r12.getName()     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r6 = getFileNameNoEx(r6)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.io.File r11 = new java.io.File     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r13.<init>()     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r14 = r12.getName()     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r13.append(r14)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r14 = ".prof"
            r13.append(r14)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r13 = r13.toString()     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r11.<init>(r4, r13)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.io.File r13 = new java.io.File     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r14.<init>()     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r12 = r12.getName()     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r14.append(r12)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r12 = ".cur.prof"
            r14.append(r12)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r12 = r14.toString()     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r13.<init>(r4, r12)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.io.File r12 = new java.io.File     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r14 = "arm"
            r12.<init>(r4, r14)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r14.<init>()     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r14.append(r6)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r15 = ".odex"
            r14.append(r15)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r4.<init>(r12, r14)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.io.File r14 = new java.io.File     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r15.<init>()     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r15.append(r6)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r6 = ".vdex"
            r15.append(r6)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r6 = r15.toString()     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r14.<init>(r12, r6)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            delete(r11)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            delete(r13)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            delete(r4)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            delete(r14)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r6.<init>()     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r12 = "delete file:"
            r6.append(r12)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r6.append(r11)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r6.append(r13)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r6.append(r4)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r6.append(r14)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            java.lang.String r4 = r6.toString()     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            android.util.Log.i(r0, r4)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            android.content.SharedPreferences$Editor r0 = r10.edit()     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r0.putString(r8, r2)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r0.putInt(r7, r3)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r0.putString(r9, r5)     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
            r0.commit()     // Catch:{ Exception -> 0x01c5, all -> 0x01c2 }
        L_0x01be:
            r14 = r1
        L_0x01bf:
            if (r14 == 0) goto L_0x01dd
            goto L_0x01d2
        L_0x01c2:
            r0 = move-exception
            r14 = r1
            goto L_0x01d7
        L_0x01c5:
            r0 = move-exception
            r14 = r1
            goto L_0x01cd
        L_0x01c8:
            r0 = move-exception
            r14 = 0
            goto L_0x01d7
        L_0x01cb:
            r0 = move-exception
            r14 = 0
        L_0x01cd:
            r0.printStackTrace()     // Catch:{ all -> 0x01d6 }
            if (r14 == 0) goto L_0x01dd
        L_0x01d2:
            r14.e()
            goto L_0x01dd
        L_0x01d6:
            r0 = move-exception
        L_0x01d7:
            if (r14 == 0) goto L_0x01dc
            r14.e()
        L_0x01dc:
            throw r0
        L_0x01dd:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "createDexClassLoader: "
            r0.append(r1)
            r1 = r17
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r2 = "dexloader"
            android.util.Log.d(r2, r0)
            boolean r0 = r16.shouldUseTbsCorePrivateClassLoader(r17)
            if (r0 == 0) goto L_0x0207
            com.tencent.smtt.export.external.DexLoader$TbsCorePrivateClassLoader r0 = new com.tencent.smtt.export.external.DexLoader$TbsCorePrivateClassLoader
            r3 = r18
            r4 = r19
            r5 = r20
            r0.<init>(r1, r3, r4, r5)
            goto L_0x0252
        L_0x0207:
            r3 = r18
            r4 = r19
            r5 = r20
            int r0 = android.os.Build.VERSION.SDK_INT
            r6 = 21
            if (r0 < r6) goto L_0x0248
            int r0 = android.os.Build.VERSION.SDK_INT
            r6 = 25
            if (r0 > r6) goto L_0x0248
            boolean r0 = mUseSpeedyClassLoader
            if (r0 == 0) goto L_0x0248
            java.lang.String r0 = "async odex...DexClassLoaderProvider.createDexClassLoader"
            android.util.Log.d(r2, r0)
            dalvik.system.DexClassLoader r0 = com.tencent.smtt.export.external.DexClassLoaderProvider.createDexClassLoader(r17, r18, r19, r20, r21)     // Catch:{ all -> 0x0227 }
            goto L_0x0252
        L_0x0227:
            r0 = move-exception
            r6 = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r7 = "createDexClassLoader exception: "
            r0.append(r7)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r2, r0)
            java.lang.String r0 = "sync odex...new DexClassLoader#2"
            android.util.Log.d(r2, r0)
            dalvik.system.DexClassLoader r0 = new dalvik.system.DexClassLoader
            r0.<init>(r1, r3, r4, r5)
            goto L_0x0252
        L_0x0248:
            java.lang.String r0 = "sync odex...new DexClassLoader"
            android.util.Log.d(r2, r0)
            dalvik.system.DexClassLoader r0 = new dalvik.system.DexClassLoader
            r0.<init>(r1, r3, r4, r5)
        L_0x0252:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "createDexClassLoader result: "
            r1.append(r3)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.export.external.DexLoader.createDexClassLoader(java.lang.String, java.lang.String, java.lang.String, java.lang.ClassLoader, android.content.Context):dalvik.system.DexClassLoader");
    }

    public static void delete(File file) {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File delete : listFiles) {
                    delete(delete);
                }
                file.delete();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r0 = r2.lastIndexOf(46);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getFileNameNoEx(java.lang.String r2) {
        /*
            if (r2 == 0) goto L_0x001c
            int r0 = r2.length()
            if (r0 <= 0) goto L_0x001c
            r0 = 46
            int r0 = r2.lastIndexOf(r0)
            r1 = -1
            if (r0 <= r1) goto L_0x001c
            int r1 = r2.length()
            if (r0 >= r1) goto L_0x001c
            r1 = 0
            java.lang.String r2 = r2.substring(r1, r0)
        L_0x001c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.export.external.DexLoader.getFileNameNoEx(java.lang.String):java.lang.String");
    }

    public static void initTbsSettings(Map<String, Object> map) {
        Log.d(TAG, "initTbsSettings - " + map);
        if (map != null) {
            try {
                Object obj = map.get(TbsCoreSettings.TBS_SETTINGS_USE_PRIVATE_CLASSLOADER);
                if (obj instanceof Boolean) {
                    mUseTbsCorePrivateClassLoader = ((Boolean) obj).booleanValue();
                }
                Object obj2 = map.get(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER);
                if (obj2 instanceof Boolean) {
                    mUseSpeedyClassLoader = ((Boolean) obj2).booleanValue();
                }
                Object obj3 = map.get(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE);
                if (obj3 instanceof Boolean) {
                    mCanUseDexLoaderProviderService = ((Boolean) obj3).booleanValue();
                }
                Object obj4 = map.get(TbsCoreSettings.TBS_SETTINGS_PRAVITE_MTT_CLASSES);
                if (obj4 instanceof Boolean) {
                    mMttClassUseCorePrivate = ((Boolean) obj4).booleanValue();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private boolean shouldUseTbsCorePrivateClassLoader(String str) {
        if (!mUseTbsCorePrivateClassLoader) {
            return false;
        }
        return str.contains(TBS_FUSION_DEX) || str.contains(TBS_WEBVIEW_DEX);
    }

    public DexClassLoader getClassLoader() {
        return this.mClassLoader;
    }

    public Object getStaticField(String str, String str2) {
        try {
            Field field = this.mClassLoader.loadClass(str).getField(str2);
            field.setAccessible(true);
            return field.get((Object) null);
        } catch (Throwable th) {
            String simpleName = getClass().getSimpleName();
            Log.e(simpleName, "'" + str + "' get field '" + str2 + "' failed", th);
            return null;
        }
    }

    public Object invokeMethod(Object obj, String str, String str2, Class<?>[] clsArr, Object... objArr) {
        try {
            Method method = this.mClassLoader.loadClass(str).getMethod(str2, clsArr);
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        } catch (Throwable th) {
            String simpleName = getClass().getSimpleName();
            Log.e(simpleName, "'" + str + "' invoke method '" + str2 + "' failed", th);
            return null;
        }
    }

    public Object invokeStaticMethod(String str, String str2, Class<?>[] clsArr, Object... objArr) {
        try {
            Method method = this.mClassLoader.loadClass(str).getMethod(str2, clsArr);
            method.setAccessible(true);
            return method.invoke((Object) null, objArr);
        } catch (Throwable th) {
            if (str2 == null || !str2.equalsIgnoreCase("initTesRuntimeEnvironment")) {
                String simpleName = getClass().getSimpleName();
                Log.i(simpleName, "'" + str + "' invoke static method '" + str2 + "' failed", th);
                return null;
            }
            String simpleName2 = getClass().getSimpleName();
            Log.e(simpleName2, "'" + str + "' invoke static method '" + str2 + "' failed", th);
            return th;
        }
    }

    public Class<?> loadClass(String str) {
        try {
            return this.mClassLoader.loadClass(str);
        } catch (Throwable th) {
            String simpleName = getClass().getSimpleName();
            Log.e(simpleName, "loadClass '" + str + "' failed", th);
            return null;
        }
    }

    public Object newInstance(String str) {
        try {
            return this.mClassLoader.loadClass(str).newInstance();
        } catch (Throwable th) {
            String simpleName = getClass().getSimpleName();
            Log.e(simpleName, "create " + str + " instance failed", th);
            return null;
        }
    }

    public Object newInstance(String str, Class<?>[] clsArr, Object... objArr) {
        try {
            return this.mClassLoader.loadClass(str).getConstructor(clsArr).newInstance(objArr);
        } catch (Throwable th) {
            if ("com.tencent.smtt.webkit.adapter.X5WebViewAdapter".equalsIgnoreCase(str)) {
                String simpleName = getClass().getSimpleName();
                Log.e(simpleName, "'newInstance " + str + " failed", th);
                return th;
            }
            String simpleName2 = getClass().getSimpleName();
            Log.e(simpleName2, "create '" + str + "' instance failed", th);
            return null;
        }
    }

    public void setStaticField(String str, String str2, Object obj) {
        try {
            Field field = this.mClassLoader.loadClass(str).getField(str2);
            field.setAccessible(true);
            field.set((Object) null, obj);
        } catch (Throwable th) {
            String simpleName = getClass().getSimpleName();
            Log.e(simpleName, "'" + str + "' set field '" + str2 + "' failed", th);
        }
    }
}
