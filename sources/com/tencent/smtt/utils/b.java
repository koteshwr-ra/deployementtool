package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.google.common.base.Ascii;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class b {
    public static String a = "";
    public static String b = "";
    public static String c = "";
    public static String d = "";
    public static String e = "";
    public static String f = "";
    private static boolean g = false;
    private static boolean h = false;
    private static boolean i = false;
    private static boolean j = false;
    private static boolean k = false;

    public static String a() {
        if (!k) {
            String str = Build.MODEL;
            try {
                f = new String(str.getBytes("UTF-8"), "ISO8859-1");
            } catch (Exception unused) {
                f = str;
            }
            k = true;
        }
        return f;
    }

    public static String a(Context context) {
        try {
            return context.getPackageName();
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002a A[Catch:{ Exception -> 0x002f }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(android.content.Context r4, java.io.File r5) {
        /*
            java.lang.String r0 = "AppUtil"
            r1 = 0
            android.content.pm.PackageManager r4 = r4.getPackageManager()     // Catch:{ Exception -> 0x002f }
            java.lang.String r2 = r5.getAbsolutePath()     // Catch:{ Exception -> 0x002f }
            r3 = 65
            android.content.pm.PackageInfo r4 = r4.getPackageArchiveInfo(r2, r3)     // Catch:{ Exception -> 0x002f }
            if (r4 == 0) goto L_0x0027
            android.content.pm.Signature[] r2 = r4.signatures     // Catch:{ Exception -> 0x002f }
            if (r2 == 0) goto L_0x0022
            android.content.pm.Signature[] r2 = r4.signatures     // Catch:{ Exception -> 0x002f }
            int r2 = r2.length     // Catch:{ Exception -> 0x002f }
            if (r2 <= 0) goto L_0x0022
            android.content.pm.Signature[] r4 = r4.signatures     // Catch:{ Exception -> 0x002f }
            r2 = 0
            r4 = r4[r2]     // Catch:{ Exception -> 0x002f }
            goto L_0x0028
        L_0x0022:
            java.lang.String r4 = "[getSignatureFromApk] pkgInfo is not null BUT signatures is null!"
            com.tencent.smtt.utils.TbsLog.w(r0, r4)     // Catch:{ Exception -> 0x002f }
        L_0x0027:
            r4 = r1
        L_0x0028:
            if (r4 == 0) goto L_0x0048
            java.lang.String r1 = r4.toCharsString()     // Catch:{ Exception -> 0x002f }
            goto L_0x0048
        L_0x002f:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r2 = "getSign "
            r4.append(r2)
            r4.append(r5)
            java.lang.String r5 = "failed"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r4)
        L_0x0048:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.b.a(android.content.Context, java.io.File):java.lang.String");
    }

    public static String a(Context context, String str) {
        try {
            String valueOf = String.valueOf(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get(str));
            try {
                return String.valueOf(Integer.toHexString(Integer.parseInt(valueOf)));
            } catch (Exception unused) {
                return valueOf;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x0066 A[Catch:{ all -> 0x0077 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r5, boolean r6, java.io.File r7) {
        /*
            java.lang.String r0 = "AppUtil"
            java.lang.String r1 = ""
            if (r7 == 0) goto L_0x00b4
            boolean r2 = r7.exists()
            if (r2 != 0) goto L_0x000e
            goto L_0x00b4
        L_0x000e:
            if (r6 == 0) goto L_0x0056
            r6 = 0
            r2 = 2
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x003c, all -> 0x003a }
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x003c, all -> 0x003a }
            java.lang.String r4 = "r"
            r3.<init>(r7, r4)     // Catch:{ Exception -> 0x003c, all -> 0x003a }
            r3.read(r2)     // Catch:{ Exception -> 0x0038 }
            java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x0038 }
            r6.<init>(r2)     // Catch:{ Exception -> 0x0038 }
            java.lang.String r2 = "PK"
            boolean r6 = r6.equalsIgnoreCase(r2)     // Catch:{ Exception -> 0x0038 }
            if (r6 != 0) goto L_0x0034
            r3.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0033
        L_0x002f:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0033:
            return r1
        L_0x0034:
            r3.close()     // Catch:{ IOException -> 0x0046 }
            goto L_0x0056
        L_0x0038:
            r6 = move-exception
            goto L_0x003f
        L_0x003a:
            r5 = move-exception
            goto L_0x004d
        L_0x003c:
            r1 = move-exception
            r3 = r6
            r6 = r1
        L_0x003f:
            r6.printStackTrace()     // Catch:{ all -> 0x004b }
            r3.close()     // Catch:{ IOException -> 0x0046 }
            goto L_0x0056
        L_0x0046:
            r6 = move-exception
            r6.printStackTrace()
            goto L_0x0056
        L_0x004b:
            r5 = move-exception
            r6 = r3
        L_0x004d:
            r6.close()     // Catch:{ IOException -> 0x0051 }
            goto L_0x0055
        L_0x0051:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0055:
            throw r5
        L_0x0056:
            android.content.Context r6 = r5.getApplicationContext()     // Catch:{ all -> 0x0077 }
            java.lang.String r6 = r6.getPackageName()     // Catch:{ all -> 0x0077 }
            java.lang.String r1 = "com.jd.jrapp"
            boolean r6 = r6.contains(r1)     // Catch:{ all -> 0x0077 }
            if (r6 == 0) goto L_0x007c
            java.lang.String r6 = "[AppUtil.getSignatureFromApk]  #1"
            com.tencent.smtt.utils.TbsLog.i(r0, r6)     // Catch:{ all -> 0x0077 }
            java.lang.String r6 = a((java.io.File) r7)     // Catch:{ all -> 0x0077 }
            if (r6 == 0) goto L_0x007c
            java.lang.String r1 = "[AppUtil.getSignatureFromApk]  #2"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch:{ all -> 0x0077 }
            return r6
        L_0x0077:
            java.lang.String r6 = "[AppUtil.getSignatureFromApk]  #3"
            com.tencent.smtt.utils.TbsLog.i(r0, r6)
        L_0x007c:
            java.lang.String r6 = "[AppUtil.getSignatureFromApk]  #4"
            com.tencent.smtt.utils.TbsLog.i(r0, r6)
            java.lang.String r5 = a((android.content.Context) r5, (java.io.File) r7)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r1 = "[AppUtil.getSignatureFromApk]  android api signature="
            r6.append(r1)
            r6.append(r5)
            java.lang.String r6 = r6.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r6)
            if (r5 != 0) goto L_0x00b3
            java.lang.String r5 = a((java.io.File) r7)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "[AppUtil.getSignatureFromApk]  java get signature="
            r6.append(r7)
            r6.append(r5)
            java.lang.String r6 = r6.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r6)
        L_0x00b3:
            return r5
        L_0x00b4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.b.a(android.content.Context, boolean, java.io.File):java.lang.String");
    }

    private static String a(File file) {
        try {
            JarFile jarFile = new JarFile(file);
            byte[] bArr = new byte[8192];
            String a2 = a(a(jarFile, jarFile.getJarEntry("AndroidManifest.xml"), bArr)[0].getEncoded());
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry nextElement = entries.nextElement();
                String name = nextElement.getName();
                if (name != null) {
                    Certificate[] a3 = a(jarFile, nextElement, bArr);
                    String a4 = a3 != null ? a(a3[0].getEncoded()) : null;
                    if (a4 == null) {
                        if (!name.startsWith("META-INF/")) {
                            return null;
                        }
                    } else if (!a4.equals(a2)) {
                        return null;
                    }
                }
            }
            return a2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }

    private static String a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[(length * 2)];
        for (int i2 = 0; i2 < length; i2++) {
            byte b2 = bArr[i2];
            int i3 = (b2 >> 4) & 15;
            int i4 = i2 * 2;
            cArr[i4] = (char) (i3 >= 10 ? (i3 + 97) - 10 : i3 + 48);
            byte b3 = b2 & Ascii.SI;
            cArr[i4 + 1] = (char) (b3 >= 10 ? (b3 + 97) - 10 : b3 + 48);
        }
        return new String(cArr);
    }

    private static Certificate[] a(JarFile jarFile, JarEntry jarEntry, byte[] bArr) throws Exception {
        InputStream inputStream = jarFile.getInputStream(jarEntry);
        do {
        } while (inputStream.read(bArr, 0, bArr.length) != -1);
        inputStream.close();
        if (jarEntry != null) {
            return jarEntry.getCertificates();
        }
        return null;
    }

    public static int b(Context context) {
        return Build.VERSION.SDK_INT;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(16:2|3|4|5|6|7|8|(1:10)(1:12)|11|13|(1:15)|16|17|18|19|35) */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0068, code lost:
        if (r3 == null) goto L_0x006b;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0042 */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0063 A[SYNTHETIC, Splitter:B:31:0x0063] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b() {
        /*
            java.lang.String r0 = "os.arch"
            boolean r1 = i
            if (r1 != 0) goto L_0x0085
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ all -> 0x004b }
            java.lang.String r3 = "getprop ro.product.cpu.abi"
            java.lang.Process r2 = r2.exec(r3)     // Catch:{ all -> 0x004b }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x004b }
            java.io.InputStream r2 = r2.getInputStream()     // Catch:{ all -> 0x004b }
            r3.<init>(r2)     // Catch:{ all -> 0x004b }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ all -> 0x0048 }
            r2.<init>(r3)     // Catch:{ all -> 0x0048 }
            java.lang.String r4 = r2.readLine()     // Catch:{ all -> 0x0046 }
            java.lang.String r5 = "x86"
            boolean r4 = r4.contains(r5)     // Catch:{ all -> 0x0046 }
            if (r4 == 0) goto L_0x0032
            java.lang.String r4 = "i686"
        L_0x002d:
            java.lang.String r0 = a((java.lang.String) r4)     // Catch:{ all -> 0x0046 }
            goto L_0x0037
        L_0x0032:
            java.lang.String r4 = java.lang.System.getProperty(r0)     // Catch:{ all -> 0x0046 }
            goto L_0x002d
        L_0x0037:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x003f
            c = r0
        L_0x003f:
            r2.close()     // Catch:{ IOException -> 0x0042 }
        L_0x0042:
            r3.close()     // Catch:{ IOException -> 0x006b }
            goto L_0x006b
        L_0x0046:
            r4 = move-exception
            goto L_0x004e
        L_0x0048:
            r4 = move-exception
            r2 = r1
            goto L_0x004e
        L_0x004b:
            r4 = move-exception
            r2 = r1
            r3 = r2
        L_0x004e:
            java.lang.String r0 = java.lang.System.getProperty(r0)     // Catch:{ all -> 0x006f }
            java.lang.String r1 = a((java.lang.String) r0)     // Catch:{ all -> 0x006f }
            r4.printStackTrace()     // Catch:{ all -> 0x006f }
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 == 0) goto L_0x0061
            c = r1
        L_0x0061:
            if (r2 == 0) goto L_0x0068
            r2.close()     // Catch:{ IOException -> 0x0067 }
            goto L_0x0068
        L_0x0067:
        L_0x0068:
            if (r3 == 0) goto L_0x006b
            goto L_0x0042
        L_0x006b:
            r0 = 1
            i = r0
            goto L_0x0085
        L_0x006f:
            r0 = move-exception
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 == 0) goto L_0x0078
            c = r1
        L_0x0078:
            if (r2 == 0) goto L_0x007f
            r2.close()     // Catch:{ IOException -> 0x007e }
            goto L_0x007f
        L_0x007e:
        L_0x007f:
            if (r3 == 0) goto L_0x0084
            r3.close()     // Catch:{ IOException -> 0x0084 }
        L_0x0084:
            throw r0
        L_0x0085:
            java.lang.String r0 = c
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.b.b():java.lang.String");
    }

    public static void b(Context context, String str) {
        Log.d("0816", "saveGuid guid is " + str);
        try {
            TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
            instance.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_GUID, str);
            instance.commit();
        } catch (Exception unused) {
        }
    }

    public static String c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean c() {
        Class<?> cls;
        Method declaredMethod;
        Object invoke;
        Method declaredMethod2;
        try {
            if (Build.VERSION.SDK_INT < 21 || (cls = Class.forName("dalvik.system.VMRuntime")) == null || (declaredMethod = cls.getDeclaredMethod("getRuntime", new Class[0])) == null || (invoke = declaredMethod.invoke((Object) null, new Object[0])) == null || (declaredMethod2 = cls.getDeclaredMethod("is64Bit", new Class[0])) == null) {
                return false;
            }
            Object invoke2 = declaredMethod2.invoke(invoke, new Object[0]);
            if (invoke2 instanceof Boolean) {
                return ((Boolean) invoke2).booleanValue();
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static int d(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String e(Context context) {
        String str = "";
        try {
            str = TbsDownloadConfig.getInstance(context).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_GUID, str);
        } catch (Exception unused) {
        }
        Log.d("0816", "getGuid guid is " + str);
        return str;
    }

    public static String f(Context context) {
        if (!QbSdk.isEnableSensitiveApi()) {
            TbsLog.i("AppUtil", "getImei isEnableSensitiveApi = false");
            return "";
        }
        if (!g) {
            try {
                a = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            } catch (Exception e2) {
                TbsLog.i(e2);
            }
            g = true;
        }
        return a;
    }

    public static String g(Context context) {
        if (!QbSdk.isEnableSensitiveApi()) {
            TbsLog.i("AppUtil", "getImsi isEnableSensitiveApi = false");
            return "";
        }
        if (!h) {
            try {
                b = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            } catch (Exception e2) {
                TbsLog.i(e2);
            }
            h = true;
        }
        return b;
    }

    public static String h(Context context) {
        return "02:00:00:00:00:00";
    }

    public static String i(Context context) {
        if (!QbSdk.isEnableSensitiveApi()) {
            TbsLog.i("AppUtil", "getAndroidID isEnableSensitiveApi = false");
            return "";
        }
        if (!j) {
            try {
                e = Settings.Secure.getString(context.getContentResolver(), "android_id");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            j = true;
        }
        return e;
    }
}
