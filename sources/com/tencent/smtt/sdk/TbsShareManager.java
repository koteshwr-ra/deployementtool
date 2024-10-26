package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.a;
import com.tencent.smtt.utils.b;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import okhttp3.internal.connection.RealConnection;
import org.apache.commons.lang3.StringUtils;

public class TbsShareManager {
    private static Context a = null;
    private static boolean b = false;
    private static String c = null;
    private static String d = null;
    private static int e = 0;
    private static String f = null;
    private static boolean g = false;
    private static boolean h = false;
    private static boolean i = false;
    private static String j = null;
    private static boolean k = false;
    private static boolean l = false;
    public static boolean mHasQueryed = false;

    static int a(Context context, boolean z) {
        b(context, z);
        return e;
    }

    static String a() {
        return d;
    }

    static void a(Context context) {
        TbsLog.i("TbsShareManager", "shareTbsCore #1");
        try {
            TbsLinuxToolsJni tbsLinuxToolsJni = new TbsLinuxToolsJni(context);
            a(context, tbsLinuxToolsJni, o.a().r(context));
            File s = o.a().s(context);
            TbsLog.i("TbsShareManager", "shareTbsCore tbsShareDir is " + s.getAbsolutePath());
            tbsLinuxToolsJni.a(s.getAbsolutePath(), "755");
        } catch (Throwable th) {
            TbsLog.i("TbsShareManager", "shareTbsCore tbsShareDir error is " + th.getMessage() + " ## " + th.getCause());
            th.printStackTrace();
        }
    }

    private static void a(Context context, int i2) {
        String str;
        File backupDecoupleCoreFile;
        StringBuilder sb;
        String str2;
        if (!TbsPVConfig.getInstance(a).isDisableHostBackupCore() && o.a().u(context)) {
            String[] strArr = {TbsConfig.APP_DEMO, TbsConfig.APP_WX, TbsConfig.APP_QQ, TbsConfig.APP_QZONE, context.getPackageName()};
            TbsLog.i("TbsShareManager", "find host backup core to unzip #1" + Log.getStackTraceString(new Throwable()));
            int i3 = 0;
            while (true) {
                if (i3 >= 5) {
                    break;
                }
                str = strArr[i3];
                if (i2 == getBackupCoreVersion(context, str)) {
                    if (o.a().g(getPackageContext(context, str, false))) {
                        backupDecoupleCoreFile = getBackupCoreFile(context, str);
                        if (a.a(context, backupDecoupleCoreFile, 0, i2)) {
                            sb = new StringBuilder();
                            str2 = "find host backup core to unzip normal coreVersion is ";
                            break;
                        }
                    } else {
                        continue;
                    }
                    i3++;
                } else {
                    if (i2 == getBackupDecoupleCoreVersion(context, str)) {
                        if (o.a().g(getPackageContext(context, str, false))) {
                            backupDecoupleCoreFile = getBackupDecoupleCoreFile(context, str);
                            if (a.a(context, backupDecoupleCoreFile, 0, i2)) {
                                sb = new StringBuilder();
                                str2 = "find host backup core to unzip decouple coreVersion is ";
                                break;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                    i3++;
                }
            }
            sb.append(str2);
            sb.append(i2);
            sb.append(" packageName is ");
            sb.append(str);
            TbsLog.i("TbsShareManager", sb.toString());
            o.a().b(context, backupDecoupleCoreFile, i2);
            o.a().b();
        }
    }

    private static void a(Context context, TbsLinuxToolsJni tbsLinuxToolsJni, File file) {
        TbsLog.i("TbsShareManager", "shareAllDirsAndFiles #1");
        if (file != null && file.exists() && file.isDirectory()) {
            TbsLog.i("TbsShareManager", "shareAllDirsAndFiles dir is " + file.getAbsolutePath());
            tbsLinuxToolsJni.a(file.getAbsolutePath(), "755");
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    int indexOf = file2.getAbsolutePath().indexOf(".so");
                    String absolutePath = file2.getAbsolutePath();
                    if (indexOf > 0) {
                        tbsLinuxToolsJni.a(absolutePath, "755");
                    } else {
                        tbsLinuxToolsJni.a(absolutePath, "644");
                    }
                } else if (file2.isDirectory()) {
                    a(context, tbsLinuxToolsJni, file2);
                } else {
                    TbsLog.e("TbsShareManager", "unknown file type.", true);
                }
            }
        }
    }

    static void b(Context context) {
        try {
            a(context, new TbsLinuxToolsJni(context), o.a().q(context));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    static boolean b(Context context, boolean z) {
        if (i(context)) {
            return true;
        }
        if (!z) {
            return false;
        }
        QbSdk.a(context, "TbsShareManager::isShareTbsCoreAvailable forceSysWebViewInner!");
        return false;
    }

    static String c(Context context) {
        j(context);
        return d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.io.BufferedInputStream} */
    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: Can't wrap try/catch for region: R(12:5|6|7|(1:9)|10|11|12|13|14|15|16|43) */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0063 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0074 A[SYNTHETIC, Splitter:B:26:0x0074] */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void c(android.content.Context r6, boolean r7) {
        /*
            r0 = 0
            java.lang.String r1 = "core_info"
            java.io.File r1 = getTbsShareFile(r6, r1)     // Catch:{ all -> 0x006d }
            if (r1 != 0) goto L_0x000a
            return
        L_0x000a:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x006d }
            r2.<init>(r1)     // Catch:{ all -> 0x006d }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ all -> 0x006d }
            r3.<init>(r2)     // Catch:{ all -> 0x006d }
            java.util.Properties r2 = new java.util.Properties     // Catch:{ all -> 0x0069 }
            r2.<init>()     // Catch:{ all -> 0x0069 }
            r2.load(r3)     // Catch:{ all -> 0x0069 }
            java.lang.String r4 = "core_disabled"
            r5 = 0
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x0069 }
            r2.setProperty(r4, r5)     // Catch:{ all -> 0x0069 }
            if (r7 == 0) goto L_0x0053
            com.tencent.smtt.sdk.o r7 = com.tencent.smtt.sdk.o.a()     // Catch:{ all -> 0x0069 }
            java.io.File r7 = r7.r(r6)     // Catch:{ all -> 0x0069 }
            java.lang.String r7 = r7.getAbsolutePath()     // Catch:{ all -> 0x0069 }
            android.content.Context r4 = r6.getApplicationContext()     // Catch:{ all -> 0x0069 }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ all -> 0x0069 }
            int r6 = com.tencent.smtt.utils.b.d(r6)     // Catch:{ all -> 0x0069 }
            java.lang.String r5 = "core_packagename"
            r2.setProperty(r5, r4)     // Catch:{ all -> 0x0069 }
            java.lang.String r4 = "core_path"
            r2.setProperty(r4, r7)     // Catch:{ all -> 0x0069 }
            java.lang.String r7 = "app_version"
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x0069 }
            r2.setProperty(r7, r6)     // Catch:{ all -> 0x0069 }
        L_0x0053:
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ all -> 0x0069 }
            r6.<init>(r1)     // Catch:{ all -> 0x0069 }
            java.io.BufferedOutputStream r7 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0069 }
            r7.<init>(r6)     // Catch:{ all -> 0x0069 }
            r2.store(r7, r0)     // Catch:{ all -> 0x0067 }
            r3.close()     // Catch:{ Exception -> 0x0063 }
        L_0x0063:
            r7.close()     // Catch:{ Exception -> 0x007c }
            goto L_0x007c
        L_0x0067:
            r6 = move-exception
            goto L_0x006b
        L_0x0069:
            r6 = move-exception
            r7 = r0
        L_0x006b:
            r0 = r3
            goto L_0x006f
        L_0x006d:
            r6 = move-exception
            r7 = r0
        L_0x006f:
            r6.printStackTrace()     // Catch:{ all -> 0x007d }
            if (r0 == 0) goto L_0x0079
            r0.close()     // Catch:{ Exception -> 0x0078 }
            goto L_0x0079
        L_0x0078:
        L_0x0079:
            if (r7 == 0) goto L_0x007c
            goto L_0x0063
        L_0x007c:
            return
        L_0x007d:
            r6 = move-exception
            if (r0 == 0) goto L_0x0085
            r0.close()     // Catch:{ Exception -> 0x0084 }
            goto L_0x0085
        L_0x0084:
        L_0x0085:
            if (r7 == 0) goto L_0x008a
            r7.close()     // Catch:{ Exception -> 0x008a }
        L_0x008a:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.c(android.content.Context, boolean):void");
    }

    static int d(Context context) {
        return a(context, true);
    }

    private static String[] d(Context context, boolean z) {
        if (QbSdk.getOnlyDownload()) {
            return new String[]{context.getApplicationContext().getPackageName()};
        }
        String[] coreProviderAppList = getCoreProviderAppList();
        if (!z) {
            return coreProviderAppList;
        }
        return new String[]{context.getApplicationContext().getPackageName()};
    }

    static Context e(Context context) {
        j(context);
        String str = f;
        Context context2 = null;
        if (str != null) {
            Context packageContext = getPackageContext(context, str, true);
            if (o.a().g(packageContext)) {
                context2 = packageContext;
            }
        }
        return c != null ? a : context2;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x0051 */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0043 A[SYNTHETIC, Splitter:B:29:0x0043] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized java.lang.String f(android.content.Context r5) {
        /*
            java.lang.Class<com.tencent.smtt.sdk.TbsShareManager> r0 = com.tencent.smtt.sdk.TbsShareManager.class
            monitor-enter(r0)
            r1 = 0
            java.lang.String r2 = "core_info"
            java.io.File r5 = getTbsShareFile(r5, r2)     // Catch:{ all -> 0x003c }
            if (r5 != 0) goto L_0x000e
            monitor-exit(r0)
            return r1
        L_0x000e:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x003c }
            r2.<init>(r5)     // Catch:{ all -> 0x003c }
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ all -> 0x003c }
            r5.<init>(r2)     // Catch:{ all -> 0x003c }
            java.util.Properties r2 = new java.util.Properties     // Catch:{ all -> 0x003a }
            r2.<init>()     // Catch:{ all -> 0x003a }
            r2.load(r5)     // Catch:{ all -> 0x003a }
            java.lang.String r3 = "core_packagename"
            java.lang.String r4 = ""
            java.lang.String r2 = r2.getProperty(r3, r4)     // Catch:{ all -> 0x003a }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ all -> 0x003a }
            if (r3 != 0) goto L_0x0035
            r5.close()     // Catch:{ Exception -> 0x0033 }
        L_0x0033:
            monitor-exit(r0)
            return r2
        L_0x0035:
            r5.close()     // Catch:{ Exception -> 0x0038 }
        L_0x0038:
            monitor-exit(r0)
            return r1
        L_0x003a:
            r2 = move-exception
            goto L_0x003e
        L_0x003c:
            r2 = move-exception
            r5 = r1
        L_0x003e:
            r2.printStackTrace()     // Catch:{ all -> 0x004b }
            if (r5 == 0) goto L_0x0049
            r5.close()     // Catch:{ Exception -> 0x0049 }
            goto L_0x0049
        L_0x0047:
            r5 = move-exception
            goto L_0x0052
        L_0x0049:
            monitor-exit(r0)
            return r1
        L_0x004b:
            r1 = move-exception
            if (r5 == 0) goto L_0x0051
            r5.close()     // Catch:{ Exception -> 0x0051 }
        L_0x0051:
            throw r1     // Catch:{ all -> 0x0047 }
        L_0x0052:
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.f(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x009b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int findCoreForThirdPartyApp(android.content.Context r6) {
        /*
            n(r6)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "core_info mAvailableCoreVersion is "
            r0.append(r1)
            int r1 = e
            r0.append(r1)
            java.lang.String r1 = " mAvailableCorePath is "
            r0.append(r1)
            java.lang.String r1 = d
            r0.append(r1)
            java.lang.String r1 = " mSrcPackageName is "
            r0.append(r1)
            java.lang.String r1 = f
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "TbsShareManager"
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
            java.lang.String r0 = f
            if (r0 != 0) goto L_0x0038
            java.lang.String r0 = "mSrcPackageName is null !!!"
            com.tencent.smtt.utils.TbsLog.e(r1, r0)
        L_0x0038:
            java.lang.String r0 = f
            r2 = 0
            r3 = 0
            if (r0 == 0) goto L_0x0080
            java.lang.String r4 = "AppDefined"
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L_0x0080
            int r0 = e
            com.tencent.smtt.sdk.o r4 = com.tencent.smtt.sdk.o.a()
            java.lang.String r5 = c
            int r4 = r4.a((java.lang.String) r5)
            if (r0 == r4) goto L_0x0097
            e = r2
            d = r3
            f = r3
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "check AppDefined core is error src is "
            r0.append(r4)
            int r4 = e
            r0.append(r4)
            java.lang.String r4 = " dest is "
            r0.append(r4)
            com.tencent.smtt.sdk.o r4 = com.tencent.smtt.sdk.o.a()
            java.lang.String r5 = c
            int r4 = r4.a((java.lang.String) r5)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            goto L_0x0094
        L_0x0080:
            boolean r0 = k(r6)
            if (r0 != 0) goto L_0x0097
            boolean r0 = l(r6)
            if (r0 != 0) goto L_0x0097
            e = r2
            d = r3
            f = r3
            java.lang.String r0 = "core_info error checkCoreInfo is false and checkCoreInOthers is false "
        L_0x0094:
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
        L_0x0097:
            int r0 = e
            if (r0 <= 0) goto L_0x00d2
            android.content.pm.ApplicationInfo r0 = r6.getApplicationInfo()
            java.lang.String r4 = r0.packageName
            java.lang.String r5 = "com.tencent.android.qqdownloader"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L_0x00b6
            java.lang.String r0 = r0.packageName
            java.lang.String r4 = "com.jd.jrapp"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x00b4
            goto L_0x00b6
        L_0x00b4:
            r0 = 0
            goto L_0x00b7
        L_0x00b6:
            r0 = 1
        L_0x00b7:
            if (r0 != 0) goto L_0x00c0
            int r0 = e
            boolean r6 = com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r6, (int) r0)
            goto L_0x00c1
        L_0x00c0:
            r6 = 0
        L_0x00c1:
            if (r6 != 0) goto L_0x00c7
            boolean r6 = g
            if (r6 == 0) goto L_0x00d2
        L_0x00c7:
            e = r2
            d = r3
            f = r3
            java.lang.String r6 = "core_info error QbSdk.isX5Disabled "
            com.tencent.smtt.utils.TbsLog.i(r1, r6)
        L_0x00d2:
            int r6 = e
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.findCoreForThirdPartyApp(android.content.Context):int");
    }

    public static boolean forceLoadX5FromTBSDemo(Context context) {
        int sharedTbsCoreVersion;
        if (context == null || o.a().a(context, (File[]) null) || (sharedTbsCoreVersion = getSharedTbsCoreVersion(context, TbsConfig.APP_DEMO)) <= 0) {
            return false;
        }
        writeProperties(context, Integer.toString(sharedTbsCoreVersion), TbsConfig.APP_DEMO, o.a().r(getPackageContext(context, TbsConfig.APP_DEMO, true)).getAbsolutePath(), "1");
        return true;
    }

    public static void forceToLoadX5ForThirdApp(Context context, boolean z) {
        File s;
        try {
            if (!QbSdk.isNeedInitX5FirstTime() || !isThirdPartyApp(context) || QbSdk.getOnlyDownload() || (s = o.a().s(context)) == null) {
                return;
            }
            if (!z || !new File(s, "core_info").exists()) {
                if (c != null) {
                    int a2 = o.a().a(c);
                    if (a2 > 0) {
                        d = c;
                        f = "AppDefined";
                        e = a2;
                        TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp #1 -- mAvailableCoreVersion: " + e + StringUtils.SPACE + Log.getStackTraceString(new Throwable("#")));
                        writeProperties(context, Integer.toString(e), f, d, Integer.toString(1));
                        return;
                    }
                }
                TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp #1");
                int h2 = h(context);
                int j2 = o.a().j(context);
                TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp coreVersionFromConfig is " + h2);
                TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp coreVersionFromCoreShare is " + j2);
                String[] coreProviderAppList = getCoreProviderAppList();
                int i2 = 0;
                for (String str : coreProviderAppList) {
                    int coreShareDecoupleCoreVersion = getCoreShareDecoupleCoreVersion(context, str);
                    if (coreShareDecoupleCoreVersion >= h2) {
                        if (coreShareDecoupleCoreVersion >= j2) {
                            if (coreShareDecoupleCoreVersion > 0) {
                                d = o.a().c(context, getPackageContext(context, str, true)).getAbsolutePath();
                                f = str;
                                e = coreShareDecoupleCoreVersion;
                                TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp #2 -- mAvailableCoreVersion: " + e + StringUtils.SPACE + Log.getStackTraceString(new Throwable("#")));
                                if (QbSdk.canLoadX5FirstTimeThirdApp(context)) {
                                    int d2 = b.d(context);
                                    TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp #2");
                                    writeProperties(context, Integer.toString(e), f, d, Integer.toString(d2));
                                    return;
                                }
                                e = 0;
                                d = null;
                                f = null;
                            } else {
                                continue;
                            }
                        }
                    }
                }
                for (String str2 : coreProviderAppList) {
                    int sharedTbsCoreVersion = getSharedTbsCoreVersion(context, str2);
                    if (sharedTbsCoreVersion >= h2) {
                        if (sharedTbsCoreVersion >= j2) {
                            if (sharedTbsCoreVersion > 0) {
                                d = o.a().b(context, getPackageContext(context, str2, true)).getAbsolutePath();
                                f = str2;
                                e = sharedTbsCoreVersion;
                                TbsLog.i("TbsShareManager", "forceToLoadX5ForThirdApp #3 -- mAvailableCoreVersion: " + e + StringUtils.SPACE + Log.getStackTraceString(new Throwable("#")));
                                if (QbSdk.canLoadX5FirstTimeThirdApp(context)) {
                                    writeProperties(context, Integer.toString(e), f, d, Integer.toString(b.d(context)));
                                    return;
                                }
                                e = 0;
                                d = null;
                                f = null;
                            } else {
                                continue;
                            }
                        }
                    }
                }
                if (TbsPVConfig.getInstance(a).isDisableHostBackupCore()) {
                    return;
                }
                if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                    int length = coreProviderAppList.length;
                    while (i2 < length) {
                        String str3 = coreProviderAppList[i2];
                        int backupCoreVersion = getBackupCoreVersion(context, str3);
                        if (backupCoreVersion < h2 || backupCoreVersion < j2 || backupCoreVersion <= 0) {
                            int backupDecoupleCoreVersion = getBackupDecoupleCoreVersion(context, str3);
                            if (backupDecoupleCoreVersion >= h2) {
                                if (backupDecoupleCoreVersion >= j2) {
                                    if (backupDecoupleCoreVersion > 0) {
                                        TbsLog.i("TbsShareManager", "find host backup core to unzip forceload decouple coreVersion is " + backupDecoupleCoreVersion + " packageName is " + str3);
                                        o.a().a(context, getBackupCoreFile(context, str3), backupDecoupleCoreVersion);
                                        TbsLog.i("TbsShareManager", "find host backup decouple core to unzip forceload after unzip ");
                                        return;
                                    }
                                }
                            }
                            i2++;
                        } else {
                            TbsLog.i("TbsShareManager", "find host backup core to unzip forceload coreVersion is " + backupCoreVersion + " packageName is " + str3);
                            o.a().a(context, getBackupCoreFile(context, str3), backupCoreVersion);
                            TbsLog.i("TbsShareManager", "find host backup core to unzip forceload after unzip ");
                            return;
                        }
                    }
                    return;
                }
                TbsLog.i("TbsShareManager", "in mainthread so do not find host backup core to install ");
            }
        } catch (Exception unused) {
        }
    }

    static String g(Context context) {
        try {
            n(context);
            if (d != null) {
                if (!TextUtils.isEmpty(d)) {
                    return d + File.separator + "res.apk";
                }
            }
            return null;
        } catch (Throwable th) {
            Log.e("", "getTbsResourcesPath exception: " + Log.getStackTraceString(th));
            return null;
        }
    }

    public static File getBackupCoreFile(Context context, String str) {
        try {
            File file = new File(new File(FileUtil.a(getPackageContext(context, str, false), 4)), TbsDownloader.getBackupFileName(false));
            if (file.exists()) {
                return file;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static int getBackupCoreVersion(Context context, String str) {
        try {
            File file = new File(new File(FileUtil.a(getPackageContext(context, str, false), 4)), TbsDownloader.getBackupFileName(false));
            if (file.exists()) {
                return a.b(file);
            }
        } catch (Throwable unused) {
        }
        return 0;
    }

    public static File getBackupDecoupleCoreFile(Context context, String str) {
        try {
            File file = new File(new File(FileUtil.a(getPackageContext(context, str, true), 4)), TbsDownloader.getBackupFileName(true));
            if (file.exists()) {
                return file;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static int getBackupDecoupleCoreVersion(Context context, String str) {
        try {
            File file = new File(new File(FileUtil.a(getPackageContext(context, str, false), 4)), TbsDownloader.getBackupFileName(true));
            if (file.exists()) {
                return a.b(file);
            }
        } catch (Throwable unused) {
        }
        return 0;
    }

    public static boolean getCoreDisabled() {
        return g;
    }

    public static boolean getCoreFormOwn() {
        return k;
    }

    public static String[] getCoreProviderAppList() {
        return new String[]{TbsConfig.APP_DEMO, TbsConfig.APP_WX, TbsConfig.APP_QQ, TbsConfig.APP_QZONE, "com.tencent.qqlite"};
    }

    public static int getCoreShareDecoupleCoreVersion(Context context, String str) {
        Context packageContext = getPackageContext(context, str, true);
        if (packageContext != null) {
            return o.a().i(packageContext);
        }
        return 0;
    }

    public static String getHostCorePathAppDefined() {
        return c;
    }

    public static long getHostCoreVersions(Context context) {
        long sharedTbsCoreVersion;
        long sharedTbsCoreVersion2;
        long j2;
        long j3 = 0;
        for (String str : getCoreProviderAppList()) {
            if (str.equalsIgnoreCase(TbsConfig.APP_WX)) {
                sharedTbsCoreVersion2 = (long) getSharedTbsCoreVersion(context, str);
                j2 = RealConnection.IDLE_CONNECTION_HEALTHY_NS;
            } else if (str.equalsIgnoreCase(TbsConfig.APP_QQ)) {
                sharedTbsCoreVersion2 = (long) getSharedTbsCoreVersion(context, str);
                j2 = 100000;
            } else if (str.equalsIgnoreCase(TbsConfig.APP_QZONE)) {
                sharedTbsCoreVersion = (long) getSharedTbsCoreVersion(context, str);
                j3 += sharedTbsCoreVersion;
            }
            sharedTbsCoreVersion = sharedTbsCoreVersion2 * j2;
            j3 += sharedTbsCoreVersion;
        }
        return j3;
    }

    public static Context getPackageContext(Context context, String str, boolean z) {
        if (z) {
            try {
                if (!context.getPackageName().equals(str)) {
                    TbsLog.i("TbsShareManager", "gray no core app,skip get context");
                    if (TbsPVConfig.getInstance(context).isEnableNoCoreGray() || Build.VERSION.SDK_INT >= 29) {
                        return null;
                    }
                }
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return context.createPackageContext(str, 2);
    }

    public static int getSharedTbsCoreVersion(Context context, String str) {
        Context packageContext = getPackageContext(context, str, true);
        if (packageContext != null) {
            return o.a().j(packageContext);
        }
        return 0;
    }

    public static File getTbsShareFile(Context context, String str) {
        File s = o.a().s(context);
        if (s == null) {
            return null;
        }
        File file = new File(s, str);
        if (file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0052, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r6.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0086, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0087, code lost:
        if (r1 != null) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x008d, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r1.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0091, code lost:
        throw r6;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:18:0x004e, B:38:0x006f] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0074 A[SYNTHETIC, Splitter:B:41:0x0074] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized int h(android.content.Context r6) {
        /*
            java.lang.Class<com.tencent.smtt.sdk.TbsShareManager> r0 = com.tencent.smtt.sdk.TbsShareManager.class
            monitor-enter(r0)
            java.lang.String r1 = "TbsShareManager"
            java.lang.String r2 = "readCoreVersionFromConfig #1"
            com.tencent.smtt.utils.TbsLog.i(r1, r2)     // Catch:{ all -> 0x0092 }
            r1 = 0
            java.lang.String r2 = "core_info"
            java.io.File r6 = getTbsShareFile(r6, r2)     // Catch:{ all -> 0x006e }
            r2 = 0
            if (r6 != 0) goto L_0x001d
            java.lang.String r6 = "TbsShareManager"
            java.lang.String r3 = "readCoreVersionFromConfig #2"
            com.tencent.smtt.utils.TbsLog.i(r6, r3)     // Catch:{ all -> 0x006e }
            monitor-exit(r0)
            return r2
        L_0x001d:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ all -> 0x006e }
            r3.<init>(r6)     // Catch:{ all -> 0x006e }
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ all -> 0x006e }
            r6.<init>(r3)     // Catch:{ all -> 0x006e }
            java.util.Properties r1 = new java.util.Properties     // Catch:{ all -> 0x0069 }
            r1.<init>()     // Catch:{ all -> 0x0069 }
            r1.load(r6)     // Catch:{ all -> 0x0069 }
            java.lang.String r3 = "core_version"
            java.lang.String r4 = ""
            java.lang.String r1 = r1.getProperty(r3, r4)     // Catch:{ all -> 0x0069 }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r1)     // Catch:{ all -> 0x0069 }
            if (r3 != 0) goto L_0x0058
            java.lang.String r3 = "TbsShareManager"
            java.lang.String r4 = "readCoreVersionFromConfig #3"
            com.tencent.smtt.utils.TbsLog.i(r3, r4)     // Catch:{ all -> 0x0069 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ all -> 0x0069 }
            int r1 = java.lang.Math.max(r1, r2)     // Catch:{ all -> 0x0069 }
            r6.close()     // Catch:{ Exception -> 0x0052 }
            goto L_0x0056
        L_0x0052:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ all -> 0x0092 }
        L_0x0056:
            monitor-exit(r0)
            return r1
        L_0x0058:
            java.lang.String r1 = "TbsShareManager"
            java.lang.String r3 = "readCoreVersionFromConfig #4"
            com.tencent.smtt.utils.TbsLog.i(r1, r3)     // Catch:{ all -> 0x0069 }
            r6.close()     // Catch:{ Exception -> 0x0063 }
            goto L_0x0067
        L_0x0063:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ all -> 0x0092 }
        L_0x0067:
            monitor-exit(r0)
            return r2
        L_0x0069:
            r1 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
            goto L_0x006f
        L_0x006e:
            r6 = move-exception
        L_0x006f:
            r6.printStackTrace()     // Catch:{ all -> 0x0086 }
            if (r1 == 0) goto L_0x007c
            r1.close()     // Catch:{ Exception -> 0x0078 }
            goto L_0x007c
        L_0x0078:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ all -> 0x0092 }
        L_0x007c:
            java.lang.String r6 = "TbsShareManager"
            java.lang.String r1 = "readCoreVersionFromConfig #5"
            com.tencent.smtt.utils.TbsLog.i(r6, r1)     // Catch:{ all -> 0x0092 }
            r6 = -2
            monitor-exit(r0)
            return r6
        L_0x0086:
            r6 = move-exception
            if (r1 == 0) goto L_0x0091
            r1.close()     // Catch:{ Exception -> 0x008d }
            goto L_0x0091
        L_0x008d:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0092 }
        L_0x0091:
            throw r6     // Catch:{ all -> 0x0092 }
        L_0x0092:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.h(android.content.Context):int");
    }

    static boolean i(Context context) {
        try {
            if (e == 0) {
                findCoreForThirdPartyApp(context);
            }
            if (e == 0) {
                TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_NO_SHARE_X5CORE, (String) null, new Object[0]);
                return false;
            }
            if (c == null) {
                if (e != 0 && getSharedTbsCoreVersion(context, f) == e) {
                    return true;
                }
            } else if (e != 0 && o.a().a(c) == e) {
                return true;
            }
            if (l(context)) {
                return true;
            }
            TbsCoreLoadStat instance = TbsCoreLoadStat.getInstance();
            instance.a(context, TbsListener.ErrorCode.INFO_CORE_EXIST_NOT_LOAD, new Throwable("mAvailableCoreVersion=" + e + "; mSrcPackageName=" + f + "; getSharedTbsCoreVersion(ctx, mSrcPackageName) is " + getSharedTbsCoreVersion(context, f) + "; getHostCoreVersions is " + getHostCoreVersions(context)));
            d = null;
            e = 0;
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_CONFLICT_X5CORE, (String) null, new Object[0]);
            QbSdk.a(context, "TbsShareManager::isShareTbsCoreAvailableInner forceSysWebViewInner!");
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_UNAVAIL_X5CORE, (String) null, new Object[0]);
            return false;
        }
    }

    public static boolean isThirdPartyApp(Context context) {
        try {
            if (a != null && a.equals(context.getApplicationContext())) {
                return b;
            }
            Context applicationContext = context.getApplicationContext();
            a = applicationContext;
            String packageName = applicationContext.getPackageName();
            for (String equals : getCoreProviderAppList()) {
                if (packageName.equals(equals)) {
                    b = false;
                    return false;
                }
            }
            b = true;
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    static boolean j(Context context) {
        return b(context, true);
    }

    private static boolean k(Context context) {
        String str = f;
        if (str == null) {
            return false;
        }
        return e == getSharedTbsCoreVersion(context, str) || e == getCoreShareDecoupleCoreVersion(context, f);
    }

    private static boolean l(Context context) {
        String str;
        File c2;
        if (QbSdk.getOnlyDownload()) {
            return false;
        }
        String[] coreProviderAppList = getCoreProviderAppList();
        int length = coreProviderAppList.length;
        int i2 = 0;
        while (true) {
            if (i2 < length) {
                str = coreProviderAppList[i2];
                int i3 = e;
                if (i3 > 0 && i3 == getSharedTbsCoreVersion(context, str)) {
                    Context packageContext = getPackageContext(context, str, true);
                    if (o.a().g(context)) {
                        c2 = o.a().b(context, packageContext);
                        break;
                    }
                }
                i2++;
            } else {
                int length2 = coreProviderAppList.length;
                for (int i4 = 0; i4 < length2; i4++) {
                    str = coreProviderAppList[i4];
                    int i5 = e;
                    if (i5 > 0 && i5 == getCoreShareDecoupleCoreVersion(context, str)) {
                        Context packageContext2 = getPackageContext(context, str, true);
                        if (o.a().g(context)) {
                            c2 = o.a().c(context, packageContext2);
                        }
                    }
                }
                return false;
            }
        }
        d = c2.getAbsolutePath();
        f = str;
        return true;
    }

    private static boolean m(Context context) {
        if (context == null) {
            return false;
        }
        writeProperties(context, Integer.toString(0), "", "", Integer.toString(0));
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x00e7 A[SYNTHETIC, Splitter:B:52:0x00e7] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void n(android.content.Context r8) {
        /*
            boolean r0 = l
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            java.lang.Class<com.tencent.smtt.sdk.TbsShareManager> r0 = com.tencent.smtt.sdk.TbsShareManager.class
            monitor-enter(r0)
            boolean r1 = l     // Catch:{ all -> 0x00fb }
            if (r1 == 0) goto L_0x000e
            monitor-exit(r0)     // Catch:{ all -> 0x00fb }
            return
        L_0x000e:
            r1 = 0
            java.lang.String r2 = "core_info"
            java.io.File r8 = getTbsShareFile(r8, r2)     // Catch:{ all -> 0x00de }
            if (r8 != 0) goto L_0x0019
            monitor-exit(r0)     // Catch:{ all -> 0x00fb }
            return
        L_0x0019:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x00de }
            r2.<init>(r8)     // Catch:{ all -> 0x00de }
            java.io.BufferedInputStream r8 = new java.io.BufferedInputStream     // Catch:{ all -> 0x00de }
            r8.<init>(r2)     // Catch:{ all -> 0x00de }
            java.util.Properties r1 = new java.util.Properties     // Catch:{ all -> 0x00dc }
            r1.<init>()     // Catch:{ all -> 0x00dc }
            r1.load(r8)     // Catch:{ all -> 0x00dc }
            java.lang.String r2 = "core_version"
            java.lang.String r3 = ""
            java.lang.String r2 = r1.getProperty(r2, r3)     // Catch:{ all -> 0x00dc }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ all -> 0x00dc }
            r4 = 0
            if (r3 != 0) goto L_0x0071
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ all -> 0x00dc }
            int r2 = java.lang.Math.max(r2, r4)     // Catch:{ all -> 0x00dc }
            e = r2     // Catch:{ all -> 0x00dc }
            java.lang.String r2 = "TbsShareManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00dc }
            r3.<init>()     // Catch:{ all -> 0x00dc }
            java.lang.String r5 = "loadProperties -- mAvailableCoreVersion: "
            r3.append(r5)     // Catch:{ all -> 0x00dc }
            int r5 = e     // Catch:{ all -> 0x00dc }
            r3.append(r5)     // Catch:{ all -> 0x00dc }
            java.lang.String r5 = " "
            r3.append(r5)     // Catch:{ all -> 0x00dc }
            java.lang.Throwable r5 = new java.lang.Throwable     // Catch:{ all -> 0x00dc }
            java.lang.String r6 = "#"
            r5.<init>(r6)     // Catch:{ all -> 0x00dc }
            java.lang.String r5 = android.util.Log.getStackTraceString(r5)     // Catch:{ all -> 0x00dc }
            r3.append(r5)     // Catch:{ all -> 0x00dc }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00dc }
            com.tencent.smtt.utils.TbsLog.i(r2, r3)     // Catch:{ all -> 0x00dc }
        L_0x0071:
            java.lang.String r2 = "core_packagename"
            java.lang.String r3 = ""
            java.lang.String r2 = r1.getProperty(r2, r3)     // Catch:{ all -> 0x00dc }
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r2)     // Catch:{ all -> 0x00dc }
            if (r3 != 0) goto L_0x0083
            f = r2     // Catch:{ all -> 0x00dc }
        L_0x0083:
            java.lang.String r2 = f     // Catch:{ all -> 0x00dc }
            r3 = 1
            if (r2 == 0) goto L_0x009f
            android.content.Context r2 = a     // Catch:{ all -> 0x00dc }
            if (r2 == 0) goto L_0x009f
            java.lang.String r2 = f     // Catch:{ all -> 0x00dc }
            android.content.Context r5 = a     // Catch:{ all -> 0x00dc }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ all -> 0x00dc }
            boolean r2 = r2.equals(r5)     // Catch:{ all -> 0x00dc }
            if (r2 == 0) goto L_0x009d
            k = r3     // Catch:{ all -> 0x00dc }
            goto L_0x009f
        L_0x009d:
            k = r4     // Catch:{ all -> 0x00dc }
        L_0x009f:
            java.lang.String r2 = "core_path"
            java.lang.String r4 = ""
            java.lang.String r2 = r1.getProperty(r2, r4)     // Catch:{ all -> 0x00dc }
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r2)     // Catch:{ all -> 0x00dc }
            if (r4 != 0) goto L_0x00b1
            d = r2     // Catch:{ all -> 0x00dc }
        L_0x00b1:
            java.lang.String r2 = "app_version"
            java.lang.String r4 = ""
            java.lang.String r2 = r1.getProperty(r2, r4)     // Catch:{ all -> 0x00dc }
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r2)     // Catch:{ all -> 0x00dc }
            if (r4 != 0) goto L_0x00c3
            j = r2     // Catch:{ all -> 0x00dc }
        L_0x00c3:
            java.lang.String r2 = "core_disabled"
            java.lang.String r4 = "false"
            java.lang.String r1 = r1.getProperty(r2, r4)     // Catch:{ all -> 0x00dc }
            boolean r1 = java.lang.Boolean.parseBoolean(r1)     // Catch:{ all -> 0x00dc }
            g = r1     // Catch:{ all -> 0x00dc }
            l = r3     // Catch:{ all -> 0x00dc }
            r8.close()     // Catch:{ Exception -> 0x00d7 }
            goto L_0x00ed
        L_0x00d7:
            r8 = move-exception
        L_0x00d8:
            r8.printStackTrace()     // Catch:{ all -> 0x00fb }
            goto L_0x00ed
        L_0x00dc:
            r1 = move-exception
            goto L_0x00e2
        L_0x00de:
            r8 = move-exception
            r7 = r1
            r1 = r8
            r8 = r7
        L_0x00e2:
            r1.printStackTrace()     // Catch:{ all -> 0x00ef }
            if (r8 == 0) goto L_0x00ed
            r8.close()     // Catch:{ Exception -> 0x00eb }
            goto L_0x00ed
        L_0x00eb:
            r8 = move-exception
            goto L_0x00d8
        L_0x00ed:
            monitor-exit(r0)     // Catch:{ all -> 0x00fb }
            return
        L_0x00ef:
            r1 = move-exception
            if (r8 == 0) goto L_0x00fa
            r8.close()     // Catch:{ Exception -> 0x00f6 }
            goto L_0x00fa
        L_0x00f6:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00fb }
        L_0x00fa:
            throw r1     // Catch:{ all -> 0x00fb }
        L_0x00fb:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00fb }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.n(android.content.Context):void");
    }

    public static void setHostCorePathAppDefined(String str) {
        c = str;
    }

    public static synchronized void writeCoreInfoForThirdPartyApp(Context context, int i2, boolean z) {
        synchronized (TbsShareManager.class) {
            TbsLog.i("TbsShareManager", "writeCoreInfoForThirdPartyApp coreVersion is " + i2);
            if (i2 == 0) {
                m(context);
                TbsDownloadConfig.getInstance(a).setDownloadInterruptCode(-401);
                return;
            }
            int h2 = h(context);
            TbsLog.i("TbsShareManager", "writeCoreInfoForThirdPartyApp coreVersionFromConfig is " + h2);
            if (h2 < 0) {
                TbsDownloadConfig.getInstance(a).setDownloadInterruptCode(-402);
                return;
            } else if (i2 == h2) {
                if (d(context) == 0 && !z) {
                    a(context, i2);
                }
                c(context, z);
                TbsDownloadConfig.getInstance(a).setDownloadInterruptCode(-403);
                return;
            } else if (i2 < h2) {
                m(context);
                TbsDownloadConfig.getInstance(a).setDownloadInterruptCode(BaseMultiItemQuickAdapter.TYPE_NOT_FOUND);
                return;
            } else {
                int j2 = o.a().j(context);
                TbsLog.i("TbsShareManager", "writeCoreInfoForThirdPartyApp coreVersionFromCoreShare is " + j2);
                if (i2 < j2) {
                    m(context);
                    TbsDownloadConfig.getInstance(a).setDownloadInterruptCode(BaseMultiItemQuickAdapter.TYPE_NOT_FOUND);
                    return;
                }
                String[] d2 = d(context, z);
                boolean z2 = false;
                if (c != null) {
                    if (i2 == o.a().a(c)) {
                        writeProperties(context, Integer.toString(i2), "AppDefined", c, Integer.toString(1));
                        try {
                            File tbsShareFile = getTbsShareFile(context, "core_info");
                            if (!i && tbsShareFile != null) {
                                TbsLinuxToolsJni tbsLinuxToolsJni = new TbsLinuxToolsJni(a);
                                tbsLinuxToolsJni.a(tbsShareFile.getAbsolutePath(), "644");
                                tbsLinuxToolsJni.a(o.a().s(context).getAbsolutePath(), "755");
                                i = true;
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    } else if (i2 > o.a().a(c)) {
                        for (String str : d2) {
                            if (i2 == getSharedTbsCoreVersion(context, str)) {
                                Context packageContext = getPackageContext(context, str, true);
                                String absolutePath = o.a().r(packageContext).getAbsolutePath();
                                b.d(context);
                                if (o.a().g(packageContext)) {
                                    try {
                                        FileUtil.a(new File(absolutePath), new File(c), (FileFilter) new FileFilter() {
                                            public boolean accept(File file) {
                                                return !file.getName().endsWith(".dex");
                                            }
                                        });
                                        writeProperties(context, Integer.toString(i2), "AppDefined", c, Integer.toString(1));
                                        File tbsShareFile2 = getTbsShareFile(context, "core_info");
                                        if (!i && tbsShareFile2 != null) {
                                            TbsLinuxToolsJni tbsLinuxToolsJni2 = new TbsLinuxToolsJni(a);
                                            tbsLinuxToolsJni2.a(tbsShareFile2.getAbsolutePath(), "644");
                                            tbsLinuxToolsJni2.a(o.a().s(context).getAbsolutePath(), "755");
                                            i = true;
                                        }
                                    } catch (Throwable th2) {
                                        th2.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
                int length = d2.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    String str2 = d2[i3];
                    try {
                        if (i2 == getSharedTbsCoreVersion(context, str2)) {
                            Context packageContext2 = getPackageContext(context, str2, true);
                            if (packageContext2 != null) {
                                String absolutePath2 = o.a().r(packageContext2).getAbsolutePath();
                                int d3 = b.d(context);
                                if (o.a().g(packageContext2)) {
                                    if (!str2.equals(context.getApplicationContext().getPackageName())) {
                                        TbsLog.i("TbsShareManager", "thirdAPP pre--> delete old core_share Directory:" + i2);
                                        m.a(a).a("remove_old_core", 1);
                                    }
                                    writeProperties(context, Integer.toString(i2), str2, absolutePath2, Integer.toString(d3));
                                    try {
                                        File tbsShareFile3 = getTbsShareFile(context, "core_info");
                                        if (!i && tbsShareFile3 != null) {
                                            TbsLinuxToolsJni tbsLinuxToolsJni3 = new TbsLinuxToolsJni(a);
                                            tbsLinuxToolsJni3.a(tbsShareFile3.getAbsolutePath(), "644");
                                            tbsLinuxToolsJni3.a(o.a().s(context).getAbsolutePath(), "755");
                                            i = true;
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        th.printStackTrace();
                                        z2 = true;
                                        a(context, i2);
                                        return;
                                    }
                                }
                            }
                            i3++;
                        } else {
                            if (i2 == getCoreShareDecoupleCoreVersion(context, str2)) {
                                Context packageContext3 = getPackageContext(context, str2, true);
                                String absolutePath3 = o.a().q(packageContext3).getAbsolutePath();
                                int d4 = b.d(context);
                                if (o.a().g(packageContext3)) {
                                    if (!str2.equals(context.getApplicationContext().getPackageName())) {
                                        TbsLog.i("TbsShareManager", "thirdAPP pre--> delete old core_share Directory:" + i2);
                                        try {
                                            FileUtil.b(o.a().r(context));
                                            TbsLog.i("TbsShareManager", "thirdAPP success--> delete old core_share Directory");
                                        } catch (Throwable th4) {
                                            th4.printStackTrace();
                                        }
                                    }
                                    writeProperties(context, Integer.toString(i2), str2, absolutePath3, Integer.toString(d4));
                                    try {
                                        File tbsShareFile4 = getTbsShareFile(context, "core_info");
                                        if (!i && tbsShareFile4 != null) {
                                            TbsLinuxToolsJni tbsLinuxToolsJni4 = new TbsLinuxToolsJni(a);
                                            tbsLinuxToolsJni4.a(tbsShareFile4.getAbsolutePath(), "644");
                                            tbsLinuxToolsJni4.a(o.a().s(context).getAbsolutePath(), "755");
                                            i = true;
                                        }
                                    } catch (Throwable th5) {
                                        th = th5;
                                        th.printStackTrace();
                                        z2 = true;
                                        a(context, i2);
                                        return;
                                    }
                                }
                            } else {
                                continue;
                            }
                            i3++;
                        }
                    } catch (Exception e2) {
                        TbsLog.i(e2);
                    }
                }
                z2 = true;
                if (!z2 && !z) {
                    a(context, i2);
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.io.BufferedInputStream} */
    /* JADX WARNING: type inference failed for: r0v4, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d2 A[SYNTHETIC, Splitter:B:38:0x00d2] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00dc A[SYNTHETIC, Splitter:B:43:0x00dc] */
    /* JADX WARNING: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeProperties(android.content.Context r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "writeProperties coreVersion is "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r1 = " corePackageName is "
            r0.append(r1)
            r0.append(r9)
            java.lang.String r1 = " corePath is "
            r0.append(r1)
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "TbsShareManager"
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "writeProperties -- stack: "
            r0.append(r2)
            java.lang.Throwable r2 = new java.lang.Throwable
            java.lang.String r3 = "#"
            r2.<init>(r3)
            java.lang.String r2 = android.util.Log.getStackTraceString(r2)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
            r0 = 0
            java.lang.String r1 = "core_info"
            java.io.File r7 = getTbsShareFile(r7, r1)     // Catch:{ all -> 0x00cb }
            if (r7 != 0) goto L_0x005a
            android.content.Context r7 = a     // Catch:{ all -> 0x00cb }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ all -> 0x00cb }
            r8 = -405(0xfffffffffffffe6b, float:NaN)
            r7.setDownloadInterruptCode(r8)     // Catch:{ all -> 0x00cb }
            return
        L_0x005a:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x00cb }
            r1.<init>(r7)     // Catch:{ all -> 0x00cb }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ all -> 0x00cb }
            r2.<init>(r1)     // Catch:{ all -> 0x00cb }
            java.util.Properties r1 = new java.util.Properties     // Catch:{ all -> 0x00c7 }
            r1.<init>()     // Catch:{ all -> 0x00c7 }
            r1.load(r2)     // Catch:{ all -> 0x00c7 }
            r3 = 0
            int r4 = java.lang.Integer.parseInt(r8)     // Catch:{ Exception -> 0x0072 }
            goto L_0x0073
        L_0x0072:
            r4 = 0
        L_0x0073:
            java.lang.String r5 = "core_disabled"
            if (r4 == 0) goto L_0x0093
            java.lang.String r4 = "core_version"
            r1.setProperty(r4, r8)     // Catch:{ all -> 0x00c7 }
            java.lang.String r8 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00c7 }
            r1.setProperty(r5, r8)     // Catch:{ all -> 0x00c7 }
            java.lang.String r8 = "core_packagename"
            r1.setProperty(r8, r9)     // Catch:{ all -> 0x00c7 }
            java.lang.String r8 = "core_path"
            r1.setProperty(r8, r10)     // Catch:{ all -> 0x00c7 }
            java.lang.String r8 = "app_version"
            r1.setProperty(r8, r11)     // Catch:{ all -> 0x00c7 }
            goto L_0x009b
        L_0x0093:
            r8 = 1
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x00c7 }
            r1.setProperty(r5, r8)     // Catch:{ all -> 0x00c7 }
        L_0x009b:
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ all -> 0x00c7 }
            r8.<init>(r7)     // Catch:{ all -> 0x00c7 }
            java.io.BufferedOutputStream r7 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x00c7 }
            r7.<init>(r8)     // Catch:{ all -> 0x00c7 }
            r1.store(r7, r0)     // Catch:{ all -> 0x00c1 }
            l = r3     // Catch:{ all -> 0x00c1 }
            android.content.Context r8 = a     // Catch:{ all -> 0x00c1 }
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r8)     // Catch:{ all -> 0x00c1 }
            r9 = -406(0xfffffffffffffe6a, float:NaN)
            r8.setDownloadInterruptCode(r9)     // Catch:{ all -> 0x00c1 }
            r2.close()     // Catch:{ Exception -> 0x00b9 }
            goto L_0x00bd
        L_0x00b9:
            r8 = move-exception
            r8.printStackTrace()
        L_0x00bd:
            r7.close()     // Catch:{ Exception -> 0x00e0 }
            goto L_0x00e4
        L_0x00c1:
            r8 = move-exception
            r0 = r2
            r6 = r8
            r8 = r7
            r7 = r6
            goto L_0x00cd
        L_0x00c7:
            r7 = move-exception
            r8 = r0
            r0 = r2
            goto L_0x00cd
        L_0x00cb:
            r7 = move-exception
            r8 = r0
        L_0x00cd:
            r7.printStackTrace()     // Catch:{ all -> 0x00e5 }
            if (r0 == 0) goto L_0x00da
            r0.close()     // Catch:{ Exception -> 0x00d6 }
            goto L_0x00da
        L_0x00d6:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00da:
            if (r8 == 0) goto L_0x00e4
            r8.close()     // Catch:{ Exception -> 0x00e0 }
            goto L_0x00e4
        L_0x00e0:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00e4:
            return
        L_0x00e5:
            r7 = move-exception
            if (r0 == 0) goto L_0x00f0
            r0.close()     // Catch:{ Exception -> 0x00ec }
            goto L_0x00f0
        L_0x00ec:
            r9 = move-exception
            r9.printStackTrace()
        L_0x00f0:
            if (r8 == 0) goto L_0x00fa
            r8.close()     // Catch:{ Exception -> 0x00f6 }
            goto L_0x00fa
        L_0x00f6:
            r8 = move-exception
            r8.printStackTrace()
        L_0x00fa:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsShareManager.writeProperties(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }
}
