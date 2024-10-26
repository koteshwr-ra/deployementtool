package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import androidx.core.os.EnvironmentCompat;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.spi.Configurator;

/* compiled from: BUGLY */
public class b {
    private static final String[] a = {"/su", "/su/bin/su", "/sbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/data/local/su", "/system/xbin/su", "/system/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/system/bin/cufsdosck", "/system/xbin/cufsdosck", "/system/bin/cufsmgr", "/system/xbin/cufsmgr", "/system/bin/cufaevdd", "/system/xbin/cufaevdd", "/system/bin/conbb", "/system/xbin/conbb"};
    private static final String[] b = {"com.ami.duosupdater.ui", "com.ami.launchmetro", "com.ami.syncduosservices", "com.bluestacks.home", "com.bluestacks.windowsfilemanager", "com.bluestacks.settings", "com.bluestacks.bluestackslocationprovider", "com.bluestacks.appsettings", "com.bluestacks.bstfolder", "com.bluestacks.BstCommandProcessor", "com.bluestacks.s2p", "com.bluestacks.setup", "com.kaopu001.tiantianserver", "com.kpzs.helpercenter", "com.kaopu001.tiantianime", "com.android.development_settings", "com.android.development", "com.android.customlocale2", "com.genymotion.superuser", "com.genymotion.clipboardproxy", "com.uc.xxzs.keyboard", "com.uc.xxzs", "com.blue.huang17.agent", "com.blue.huang17.launcher", "com.blue.huang17.ime", "com.microvirt.guide", "com.microvirt.market", "com.microvirt.memuime", "cn.itools.vm.launcher", "cn.itools.vm.proxy", "cn.itools.vm.softkeyboard", "cn.itools.avdmarket", "com.syd.IME", "com.bignox.app.store.hd", "com.bignox.launcher", "com.bignox.app.phone", "com.bignox.app.noxservice", "com.android.noxpush", "com.haimawan.push", "me.haima.helpcenter", "com.windroy.launcher", "com.windroy.superuser", "com.windroy.launcher", "com.windroy.ime", "com.android.flysilkworm", "com.android.emu.inputservice", "com.tiantian.ime", "com.microvirt.launcher", "me.le8.androidassist", "com.vphone.helper", "com.vphone.launcher", "com.duoyi.giftcenter.giftcenter"};
    private static final String[] c = {"/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/qemud", "/dev/qemu_pipe", "/dev/socket/baseband_genyd", "/dev/socket/genyd"};
    private static String d = null;
    private static String e = null;

    public static String d() {
        return Configurator.NULL;
    }

    public static String e() {
        return Configurator.NULL;
    }

    public static String f() {
        return Configurator.NULL;
    }

    public static String a() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (x.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static String b() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Throwable th) {
            if (x.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static int c() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (Throwable th) {
            if (x.a(th)) {
                return -1;
            }
            th.printStackTrace();
            return -1;
        }
    }

    public static String a(Context context) {
        if (context == null) {
            return "fail";
        }
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (string == null) {
                return Configurator.NULL;
            }
            return string.toLowerCase();
        } catch (Throwable th) {
            if (!x.a(th)) {
                x.a("Failed to get Android ID.", new Object[0]);
            }
            return "fail";
        }
    }

    private static boolean t() {
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            if (x.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    public static String a(Context context, boolean z) {
        String str = null;
        if (z) {
            try {
                String a2 = z.a(context, "ro.product.cpu.abilist");
                if (z.a(a2) || a2.equals("fail")) {
                    a2 = z.a(context, "ro.product.cpu.abi");
                }
                if (!z.a(a2)) {
                    if (!a2.equals("fail")) {
                        x.b(b.class, "ABI list: " + a2, new Object[0]);
                        str = a2.split(",")[0];
                    }
                }
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
                return "fail";
            }
        }
        if (str == null) {
            str = System.getProperty("os.arch");
        }
        return str;
    }

    public static long g() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    public static long h() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: java.io.BufferedReader} */
    /* JADX WARNING: type inference failed for: r0v1, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: type inference failed for: r0v13 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0083 A[Catch:{ all -> 0x00a9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0088 A[SYNTHETIC, Splitter:B:47:0x0088] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0098 A[SYNTHETIC, Splitter:B:54:0x0098] */
    /* JADX WARNING: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long i() {
        /*
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ all -> 0x0079 }
            r2.<init>(r0)     // Catch:{ all -> 0x0079 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ all -> 0x0074 }
            r3 = 2048(0x800, float:2.87E-42)
            r0.<init>(r2, r3)     // Catch:{ all -> 0x0074 }
            java.lang.String r1 = r0.readLine()     // Catch:{ all -> 0x0072 }
            if (r1 != 0) goto L_0x0034
            r0.close()     // Catch:{ IOException -> 0x0019 }
            goto L_0x0023
        L_0x0019:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0023
            r0.printStackTrace()
        L_0x0023:
            r2.close()     // Catch:{ IOException -> 0x0027 }
            goto L_0x0031
        L_0x0027:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0031
            r0.printStackTrace()
        L_0x0031:
            r0 = -1
            return r0
        L_0x0034:
            java.lang.String r3 = ":\\s+"
            r4 = 2
            java.lang.String[] r1 = r1.split(r3, r4)     // Catch:{ all -> 0x0072 }
            r3 = 1
            r1 = r1[r3]     // Catch:{ all -> 0x0072 }
            java.lang.String r1 = r1.toLowerCase()     // Catch:{ all -> 0x0072 }
            java.lang.String r3 = "kb"
            java.lang.String r4 = ""
            java.lang.String r1 = r1.replace(r3, r4)     // Catch:{ all -> 0x0072 }
            java.lang.String r1 = r1.trim()     // Catch:{ all -> 0x0072 }
            long r3 = java.lang.Long.parseLong(r1)     // Catch:{ all -> 0x0072 }
            r1 = 10
            long r3 = r3 << r1
            r0.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x0063
        L_0x0059:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0063
            r0.printStackTrace()
        L_0x0063:
            r2.close()     // Catch:{ IOException -> 0x0067 }
            goto L_0x0071
        L_0x0067:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0071
            r0.printStackTrace()
        L_0x0071:
            return r3
        L_0x0072:
            r1 = move-exception
            goto L_0x007d
        L_0x0074:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x007d
        L_0x0079:
            r0 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
        L_0x007d:
            boolean r3 = com.tencent.bugly.proguard.x.a(r1)     // Catch:{ all -> 0x00a9 }
            if (r3 != 0) goto L_0x0086
            r1.printStackTrace()     // Catch:{ all -> 0x00a9 }
        L_0x0086:
            if (r0 == 0) goto L_0x0096
            r0.close()     // Catch:{ IOException -> 0x008c }
            goto L_0x0096
        L_0x008c:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x0096
            r0.printStackTrace()
        L_0x0096:
            if (r2 == 0) goto L_0x00a6
            r2.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x00a6
        L_0x009c:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00a6
            r0.printStackTrace()
        L_0x00a6:
            r0 = -2
            return r0
        L_0x00a9:
            r1 = move-exception
            if (r0 == 0) goto L_0x00ba
            r0.close()     // Catch:{ IOException -> 0x00b0 }
            goto L_0x00ba
        L_0x00b0:
            r0 = move-exception
            boolean r3 = com.tencent.bugly.proguard.x.a(r0)
            if (r3 != 0) goto L_0x00ba
            r0.printStackTrace()
        L_0x00ba:
            if (r2 == 0) goto L_0x00ca
            r2.close()     // Catch:{ IOException -> 0x00c0 }
            goto L_0x00ca
        L_0x00c0:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.a(r0)
            if (r2 != 0) goto L_0x00ca
            r0.printStackTrace()
        L_0x00ca:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.i():long");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: java.io.FileReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.io.FileReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.io.FileReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.io.FileReader} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:111:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00fb A[Catch:{ all -> 0x0121 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0100 A[SYNTHETIC, Splitter:B:81:0x0100] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0110 A[SYNTHETIC, Splitter:B:88:0x0110] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long j() {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "kb"
            java.lang.String r2 = ":\\s+"
            java.lang.String r3 = "/proc/meminfo"
            r4 = 0
            java.io.FileReader r5 = new java.io.FileReader     // Catch:{ all -> 0x00f3 }
            r5.<init>(r3)     // Catch:{ all -> 0x00f3 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ all -> 0x00f1 }
            r6 = 2048(0x800, float:2.87E-42)
            r3.<init>(r5, r6)     // Catch:{ all -> 0x00f1 }
            r3.readLine()     // Catch:{ all -> 0x00ee }
            java.lang.String r4 = r3.readLine()     // Catch:{ all -> 0x00ee }
            r6 = -1
            if (r4 != 0) goto L_0x003d
            r3.close()     // Catch:{ IOException -> 0x0024 }
            goto L_0x002e
        L_0x0024:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x002e
            r0.printStackTrace()
        L_0x002e:
            r5.close()     // Catch:{ IOException -> 0x0032 }
            goto L_0x003c
        L_0x0032:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x003c
            r0.printStackTrace()
        L_0x003c:
            return r6
        L_0x003d:
            r8 = 2
            java.lang.String[] r4 = r4.split(r2, r8)     // Catch:{ all -> 0x00ee }
            r9 = 1
            r4 = r4[r9]     // Catch:{ all -> 0x00ee }
            java.lang.String r4 = r4.toLowerCase()     // Catch:{ all -> 0x00ee }
            java.lang.String r4 = r4.replace(r1, r0)     // Catch:{ all -> 0x00ee }
            java.lang.String r4 = r4.trim()     // Catch:{ all -> 0x00ee }
            r10 = 0
            long r12 = java.lang.Long.parseLong(r4)     // Catch:{ all -> 0x00ee }
            r4 = 10
            long r12 = r12 << r4
            long r12 = r12 + r10
            java.lang.String r10 = r3.readLine()     // Catch:{ all -> 0x00ee }
            if (r10 != 0) goto L_0x007e
            r3.close()     // Catch:{ IOException -> 0x0065 }
            goto L_0x006f
        L_0x0065:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x006f
            r0.printStackTrace()
        L_0x006f:
            r5.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x007d
        L_0x0073:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x007d
            r0.printStackTrace()
        L_0x007d:
            return r6
        L_0x007e:
            java.lang.String[] r10 = r10.split(r2, r8)     // Catch:{ all -> 0x00ee }
            r10 = r10[r9]     // Catch:{ all -> 0x00ee }
            java.lang.String r10 = r10.toLowerCase()     // Catch:{ all -> 0x00ee }
            java.lang.String r10 = r10.replace(r1, r0)     // Catch:{ all -> 0x00ee }
            java.lang.String r10 = r10.trim()     // Catch:{ all -> 0x00ee }
            long r10 = java.lang.Long.parseLong(r10)     // Catch:{ all -> 0x00ee }
            long r10 = r10 << r4
            long r12 = r12 + r10
            java.lang.String r10 = r3.readLine()     // Catch:{ all -> 0x00ee }
            if (r10 != 0) goto L_0x00b9
            r3.close()     // Catch:{ IOException -> 0x00a0 }
            goto L_0x00aa
        L_0x00a0:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00aa
            r0.printStackTrace()
        L_0x00aa:
            r5.close()     // Catch:{ IOException -> 0x00ae }
            goto L_0x00b8
        L_0x00ae:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00b8
            r0.printStackTrace()
        L_0x00b8:
            return r6
        L_0x00b9:
            java.lang.String[] r2 = r10.split(r2, r8)     // Catch:{ all -> 0x00ee }
            r2 = r2[r9]     // Catch:{ all -> 0x00ee }
            java.lang.String r2 = r2.toLowerCase()     // Catch:{ all -> 0x00ee }
            java.lang.String r0 = r2.replace(r1, r0)     // Catch:{ all -> 0x00ee }
            java.lang.String r0 = r0.trim()     // Catch:{ all -> 0x00ee }
            long r0 = java.lang.Long.parseLong(r0)     // Catch:{ all -> 0x00ee }
            long r0 = r0 << r4
            long r12 = r12 + r0
            r3.close()     // Catch:{ IOException -> 0x00d5 }
            goto L_0x00df
        L_0x00d5:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00df
            r0.printStackTrace()
        L_0x00df:
            r5.close()     // Catch:{ IOException -> 0x00e3 }
            goto L_0x00ed
        L_0x00e3:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x00ed
            r0.printStackTrace()
        L_0x00ed:
            return r12
        L_0x00ee:
            r0 = move-exception
            r4 = r3
            goto L_0x00f5
        L_0x00f1:
            r0 = move-exception
            goto L_0x00f5
        L_0x00f3:
            r0 = move-exception
            r5 = r4
        L_0x00f5:
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)     // Catch:{ all -> 0x0121 }
            if (r1 != 0) goto L_0x00fe
            r0.printStackTrace()     // Catch:{ all -> 0x0121 }
        L_0x00fe:
            if (r4 == 0) goto L_0x010e
            r4.close()     // Catch:{ IOException -> 0x0104 }
            goto L_0x010e
        L_0x0104:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x010e
            r0.printStackTrace()
        L_0x010e:
            if (r5 == 0) goto L_0x011e
            r5.close()     // Catch:{ IOException -> 0x0114 }
            goto L_0x011e
        L_0x0114:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.proguard.x.a(r0)
            if (r1 != 0) goto L_0x011e
            r0.printStackTrace()
        L_0x011e:
            r0 = -2
            return r0
        L_0x0121:
            r0 = move-exception
            if (r4 == 0) goto L_0x0132
            r4.close()     // Catch:{ IOException -> 0x0128 }
            goto L_0x0132
        L_0x0128:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.a(r1)
            if (r2 != 0) goto L_0x0132
            r1.printStackTrace()
        L_0x0132:
            if (r5 == 0) goto L_0x0142
            r5.close()     // Catch:{ IOException -> 0x0138 }
            goto L_0x0142
        L_0x0138:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.a(r1)
            if (r2 != 0) goto L_0x0142
            r1.printStackTrace()
        L_0x0142:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.j():long");
    }

    public static long k() {
        if (!t()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (x.a(th)) {
                return -2;
            }
            th.printStackTrace();
            return -2;
        }
    }

    public static long l() {
        if (!t()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (x.a(th)) {
                return -2;
            }
            th.printStackTrace();
            return -2;
        }
    }

    public static String m() {
        try {
            return Locale.getDefault().getCountry();
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static String n() {
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static String b(Context context) {
        TelephonyManager telephonyManager;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            if (activeNetworkInfo.getType() != 0 || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
                return EnvironmentCompat.MEDIA_UNKNOWN;
            }
            int networkType = telephonyManager.getNetworkType();
            switch (networkType) {
                case 1:
                    return "GPRS";
                case 2:
                    return "EDGE";
                case 3:
                    return "UMTS";
                case 4:
                    return "CDMA";
                case 5:
                    return "EVDO_0";
                case 6:
                    return "EVDO_A";
                case 7:
                    return "1xRTT";
                case 8:
                    return "HSDPA";
                case 9:
                    return "HSUPA";
                case 10:
                    return "HSPA";
                case 11:
                    return "iDen";
                case 12:
                    return "EVDO_B";
                case 13:
                    return "LTE";
                case 14:
                    return "eHRPD";
                case 15:
                    return "HSPA+";
                default:
                    return "MOBILE(" + networkType + ")";
            }
        } catch (Exception e2) {
            if (x.a(e2)) {
                return EnvironmentCompat.MEDIA_UNKNOWN;
            }
            e2.printStackTrace();
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    public static String c(Context context) {
        String a2 = z.a(context, "ro.miui.ui.version.name");
        if (z.a(a2) || a2.equals("fail")) {
            String a3 = z.a(context, "ro.build.version.emui");
            if (z.a(a3) || a3.equals("fail")) {
                String a4 = z.a(context, "ro.lenovo.series");
                if (z.a(a4) || a4.equals("fail")) {
                    String a5 = z.a(context, "ro.build.nubia.rom.name");
                    if (z.a(a5) || a5.equals("fail")) {
                        String a6 = z.a(context, "ro.meizu.product.model");
                        if (z.a(a6) || a6.equals("fail")) {
                            String a7 = z.a(context, "ro.build.version.opporom");
                            if (z.a(a7) || a7.equals("fail")) {
                                String a8 = z.a(context, "ro.vivo.os.build.display.id");
                                if (z.a(a8) || a8.equals("fail")) {
                                    String a9 = z.a(context, "ro.aa.romver");
                                    if (z.a(a9) || a9.equals("fail")) {
                                        String a10 = z.a(context, "ro.lewa.version");
                                        if (z.a(a10) || a10.equals("fail")) {
                                            String a11 = z.a(context, "ro.gn.gnromvernumber");
                                            if (z.a(a11) || a11.equals("fail")) {
                                                String a12 = z.a(context, "ro.build.tyd.kbstyle_version");
                                                if (z.a(a12) || a12.equals("fail")) {
                                                    return z.a(context, "ro.build.fingerprint") + "/" + z.a(context, "ro.build.rom.id");
                                                }
                                                return "dido/" + a12;
                                            }
                                            return "amigo/" + a11 + "/" + z.a(context, "ro.build.display.id");
                                        }
                                        return "tcl/" + a10 + "/" + z.a(context, "ro.build.display.id");
                                    }
                                    return "htc/" + a9 + "/" + z.a(context, "ro.build.description");
                                }
                                return "vivo/FUNTOUCH/" + a8;
                            }
                            return "Oppo/COLOROS/" + a7;
                        }
                        return "Meizu/FLYME/" + z.a(context, "ro.build.display.id");
                    }
                    return "Zte/NUBIA/" + a5 + "_" + z.a(context, "ro.build.nubia.rom.code");
                }
                String a13 = z.a(context, "ro.build.version.incremental");
                return "Lenovo/VIBE/" + a13;
            }
            return "HuaWei/EMOTION/" + a3;
        }
        return "XiaoMi/MIUI/" + a2;
    }

    public static String d(Context context) {
        return z.a(context, "ro.board.platform");
    }

    public static boolean o() {
        boolean z;
        String[] strArr = a;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            } else if (new File(strArr[i]).exists()) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        return (Build.TAGS != null && Build.TAGS.contains("test-keys")) || z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x0092 A[SYNTHETIC, Splitter:B:45:0x0092] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String p() {
        /*
            java.lang.String r0 = "/sys/block/mmcblk0/device/cid"
            java.lang.String r1 = "/sys/block/mmcblk0/device/name"
            java.lang.String r2 = ","
            java.lang.String r3 = "/sys/block/mmcblk0/device/type"
            r4 = 0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x008f }
            r5.<init>()     // Catch:{ all -> 0x008f }
            java.io.File r6 = new java.io.File     // Catch:{ all -> 0x008f }
            r6.<init>(r3)     // Catch:{ all -> 0x008f }
            boolean r6 = r6.exists()     // Catch:{ all -> 0x008f }
            if (r6 == 0) goto L_0x0030
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ all -> 0x008f }
            java.io.FileReader r7 = new java.io.FileReader     // Catch:{ all -> 0x008f }
            r7.<init>(r3)     // Catch:{ all -> 0x008f }
            r6.<init>(r7)     // Catch:{ all -> 0x008f }
            java.lang.String r3 = r6.readLine()     // Catch:{ all -> 0x008d }
            if (r3 == 0) goto L_0x002c
            r5.append(r3)     // Catch:{ all -> 0x008d }
        L_0x002c:
            r6.close()     // Catch:{ all -> 0x008d }
            goto L_0x0031
        L_0x0030:
            r6 = r4
        L_0x0031:
            r5.append(r2)     // Catch:{ all -> 0x008d }
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x008d }
            r3.<init>(r1)     // Catch:{ all -> 0x008d }
            boolean r3 = r3.exists()     // Catch:{ all -> 0x008d }
            if (r3 == 0) goto L_0x0059
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ all -> 0x008d }
            java.io.FileReader r7 = new java.io.FileReader     // Catch:{ all -> 0x008d }
            r7.<init>(r1)     // Catch:{ all -> 0x008d }
            r3.<init>(r7)     // Catch:{ all -> 0x008d }
            java.lang.String r1 = r3.readLine()     // Catch:{ all -> 0x0057 }
            if (r1 == 0) goto L_0x0052
            r5.append(r1)     // Catch:{ all -> 0x0057 }
        L_0x0052:
            r3.close()     // Catch:{ all -> 0x0057 }
            r6 = r3
            goto L_0x0059
        L_0x0057:
            r6 = r3
            goto L_0x0090
        L_0x0059:
            r5.append(r2)     // Catch:{ all -> 0x008d }
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x008d }
            r1.<init>(r0)     // Catch:{ all -> 0x008d }
            boolean r1 = r1.exists()     // Catch:{ all -> 0x008d }
            if (r1 == 0) goto L_0x007e
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x008d }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ all -> 0x008d }
            r2.<init>(r0)     // Catch:{ all -> 0x008d }
            r1.<init>(r2)     // Catch:{ all -> 0x008d }
            java.lang.String r0 = r1.readLine()     // Catch:{ all -> 0x007c }
            if (r0 == 0) goto L_0x007a
            r5.append(r0)     // Catch:{ all -> 0x007c }
        L_0x007a:
            r6 = r1
            goto L_0x007e
        L_0x007c:
            r6 = r1
            goto L_0x0090
        L_0x007e:
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x008d }
            if (r6 == 0) goto L_0x008c
            r6.close()     // Catch:{ IOException -> 0x0088 }
            goto L_0x008c
        L_0x0088:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
        L_0x008c:
            return r0
        L_0x008d:
            goto L_0x0090
        L_0x008f:
            r6 = r4
        L_0x0090:
            if (r6 == 0) goto L_0x009a
            r6.close()     // Catch:{ IOException -> 0x0096 }
            goto L_0x009a
        L_0x0096:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        L_0x009a:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.p():java.lang.String");
    }

    public static String e(Context context) {
        StringBuilder sb = new StringBuilder();
        String a2 = z.a(context, "ro.genymotion.version");
        if (a2 != null) {
            sb.append("ro.genymotion.version");
            sb.append("|");
            sb.append(a2);
            sb.append(StringUtils.LF);
        }
        String a3 = z.a(context, "androVM.vbox_dpi");
        if (a3 != null) {
            sb.append("androVM.vbox_dpi");
            sb.append("|");
            sb.append(a3);
            sb.append(StringUtils.LF);
        }
        String a4 = z.a(context, "qemu.sf.fake_camera");
        if (a4 != null) {
            sb.append("qemu.sf.fake_camera");
            sb.append("|");
            sb.append(a4);
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0062 A[Catch:{ all -> 0x0084 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008f A[SYNTHETIC, Splitter:B:34:0x008f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String f(android.content.Context r6) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = d
            java.lang.String r2 = "ro.secure"
            if (r1 != 0) goto L_0x0011
            java.lang.String r1 = com.tencent.bugly.proguard.z.a((android.content.Context) r6, (java.lang.String) r2)
            d = r1
        L_0x0011:
            java.lang.String r1 = d
            java.lang.String r3 = "\n"
            java.lang.String r4 = "|"
            if (r1 == 0) goto L_0x0027
            r0.append(r2)
            r0.append(r4)
            java.lang.String r1 = d
            r0.append(r1)
            r0.append(r3)
        L_0x0027:
            java.lang.String r1 = e
            java.lang.String r2 = "ro.debuggable"
            if (r1 != 0) goto L_0x0033
            java.lang.String r6 = com.tencent.bugly.proguard.z.a((android.content.Context) r6, (java.lang.String) r2)
            e = r6
        L_0x0033:
            java.lang.String r6 = e
            if (r6 == 0) goto L_0x0045
            r0.append(r2)
            r0.append(r4)
            java.lang.String r6 = e
            r0.append(r6)
            r0.append(r3)
        L_0x0045:
            r6 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x0086 }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ all -> 0x0086 }
            java.lang.String r3 = "/proc/self/status"
            r2.<init>(r3)     // Catch:{ all -> 0x0086 }
            r1.<init>(r2)     // Catch:{ all -> 0x0086 }
        L_0x0052:
            java.lang.String r6 = r1.readLine()     // Catch:{ all -> 0x0084 }
            if (r6 == 0) goto L_0x0060
            java.lang.String r2 = "TracerPid:"
            boolean r2 = r6.startsWith(r2)     // Catch:{ all -> 0x0084 }
            if (r2 == 0) goto L_0x0052
        L_0x0060:
            if (r6 == 0) goto L_0x0077
            r2 = 10
            java.lang.String r6 = r6.substring(r2)     // Catch:{ all -> 0x0084 }
            java.lang.String r6 = r6.trim()     // Catch:{ all -> 0x0084 }
            java.lang.String r2 = "tracer_pid"
            r0.append(r2)     // Catch:{ all -> 0x0084 }
            r0.append(r4)     // Catch:{ all -> 0x0084 }
            r0.append(r6)     // Catch:{ all -> 0x0084 }
        L_0x0077:
            java.lang.String r6 = r0.toString()     // Catch:{ all -> 0x0084 }
            r1.close()     // Catch:{ IOException -> 0x007f }
            goto L_0x0083
        L_0x007f:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        L_0x0083:
            return r6
        L_0x0084:
            r6 = move-exception
            goto L_0x008a
        L_0x0086:
            r1 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
        L_0x008a:
            com.tencent.bugly.proguard.x.a(r6)     // Catch:{ all -> 0x009c }
            if (r1 == 0) goto L_0x0097
            r1.close()     // Catch:{ IOException -> 0x0093 }
            goto L_0x0097
        L_0x0093:
            r6 = move-exception
            com.tencent.bugly.proguard.x.a(r6)
        L_0x0097:
            java.lang.String r6 = r0.toString()
            return r6
        L_0x009c:
            r6 = move-exception
            if (r1 == 0) goto L_0x00a7
            r1.close()     // Catch:{ IOException -> 0x00a3 }
            goto L_0x00a7
        L_0x00a3:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        L_0x00a7:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.f(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00a7 A[Catch:{ IOException -> 0x00ab }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String q() {
        /*
            java.lang.String r0 = "/sys/class/power_supply/battery/capacity"
            java.lang.String r1 = "/sys/class/power_supply/usb/online"
            java.lang.String r2 = "\n"
            java.lang.String r3 = "/sys/class/power_supply/ac/online"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r5 = 0
            java.io.File r6 = new java.io.File     // Catch:{ all -> 0x00a4 }
            r6.<init>(r3)     // Catch:{ all -> 0x00a4 }
            boolean r6 = r6.exists()     // Catch:{ all -> 0x00a4 }
            java.lang.String r7 = "|"
            if (r6 == 0) goto L_0x003e
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ all -> 0x00a4 }
            java.io.FileReader r8 = new java.io.FileReader     // Catch:{ all -> 0x00a4 }
            r8.<init>(r3)     // Catch:{ all -> 0x00a4 }
            r6.<init>(r8)     // Catch:{ all -> 0x00a4 }
            java.lang.String r3 = r6.readLine()     // Catch:{ all -> 0x003b }
            if (r3 == 0) goto L_0x0036
            java.lang.String r5 = "ac_online"
            r4.append(r5)     // Catch:{ all -> 0x003b }
            r4.append(r7)     // Catch:{ all -> 0x003b }
            r4.append(r3)     // Catch:{ all -> 0x003b }
        L_0x0036:
            r6.close()     // Catch:{ all -> 0x003b }
            r5 = r6
            goto L_0x003e
        L_0x003b:
            r5 = r6
            goto L_0x00a5
        L_0x003e:
            r4.append(r2)     // Catch:{ all -> 0x00a4 }
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x00a4 }
            r3.<init>(r1)     // Catch:{ all -> 0x00a4 }
            boolean r3 = r3.exists()     // Catch:{ all -> 0x00a4 }
            if (r3 == 0) goto L_0x006e
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ all -> 0x00a4 }
            java.io.FileReader r6 = new java.io.FileReader     // Catch:{ all -> 0x00a4 }
            r6.<init>(r1)     // Catch:{ all -> 0x00a4 }
            r3.<init>(r6)     // Catch:{ all -> 0x00a4 }
            java.lang.String r1 = r3.readLine()     // Catch:{ all -> 0x006c }
            if (r1 == 0) goto L_0x0067
            java.lang.String r5 = "usb_online"
            r4.append(r5)     // Catch:{ all -> 0x006c }
            r4.append(r7)     // Catch:{ all -> 0x006c }
            r4.append(r1)     // Catch:{ all -> 0x006c }
        L_0x0067:
            r3.close()     // Catch:{ all -> 0x006c }
            r5 = r3
            goto L_0x006e
        L_0x006c:
            r5 = r3
            goto L_0x00a5
        L_0x006e:
            r4.append(r2)     // Catch:{ all -> 0x00a4 }
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x00a4 }
            r1.<init>(r0)     // Catch:{ all -> 0x00a4 }
            boolean r1 = r1.exists()     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x009e
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x00a4 }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ all -> 0x00a4 }
            r2.<init>(r0)     // Catch:{ all -> 0x00a4 }
            r1.<init>(r2)     // Catch:{ all -> 0x00a4 }
            java.lang.String r0 = r1.readLine()     // Catch:{ all -> 0x009c }
            if (r0 == 0) goto L_0x0097
            java.lang.String r2 = "battery_capacity"
            r4.append(r2)     // Catch:{ all -> 0x009c }
            r4.append(r7)     // Catch:{ all -> 0x009c }
            r4.append(r0)     // Catch:{ all -> 0x009c }
        L_0x0097:
            r1.close()     // Catch:{ all -> 0x009c }
            r5 = r1
            goto L_0x009e
        L_0x009c:
            r5 = r1
            goto L_0x00a5
        L_0x009e:
            if (r5 == 0) goto L_0x00af
            r5.close()     // Catch:{ IOException -> 0x00ab }
            goto L_0x00af
        L_0x00a4:
        L_0x00a5:
            if (r5 == 0) goto L_0x00af
            r5.close()     // Catch:{ IOException -> 0x00ab }
            goto L_0x00af
        L_0x00ab:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
        L_0x00af:
            java.lang.String r0 = r4.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.q():java.lang.String");
    }

    public static String g(Context context) {
        StringBuilder sb = new StringBuilder();
        String a2 = z.a(context, "gsm.sim.state");
        if (a2 != null) {
            sb.append("gsm.sim.state");
            sb.append("|");
            sb.append(a2);
        }
        sb.append(StringUtils.LF);
        String a3 = z.a(context, "gsm.sim.state2");
        if (a3 != null) {
            sb.append("gsm.sim.state2");
            sb.append("|");
            sb.append(a3);
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003e A[SYNTHETIC, Splitter:B:19:0x003e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long r() {
        /*
            r0 = 0
            r1 = 0
            r2 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ all -> 0x0035 }
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ all -> 0x0035 }
            java.lang.String r5 = "/proc/uptime"
            r4.<init>(r5)     // Catch:{ all -> 0x0035 }
            r3.<init>(r4)     // Catch:{ all -> 0x0035 }
            java.lang.String r2 = r3.readLine()     // Catch:{ all -> 0x0034 }
            if (r2 == 0) goto L_0x002b
            java.lang.String r4 = " "
            java.lang.String[] r2 = r2.split(r4)     // Catch:{ all -> 0x0034 }
            r2 = r2[r0]     // Catch:{ all -> 0x0034 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0034 }
            r6 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 / r6
            float r4 = (float) r4     // Catch:{ all -> 0x0034 }
            float r0 = java.lang.Float.parseFloat(r2)     // Catch:{ all -> 0x0034 }
            float r4 = r4 - r0
            r1 = r4
        L_0x002b:
            r3.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0041
        L_0x002f:
            r0 = move-exception
            com.tencent.bugly.proguard.x.a(r0)
            goto L_0x0041
        L_0x0034:
            r2 = r3
        L_0x0035:
            java.lang.String r3 = "Failed to get boot time of device."
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x0043 }
            com.tencent.bugly.proguard.x.a(r3, r0)     // Catch:{ all -> 0x0043 }
            if (r2 == 0) goto L_0x0041
            r2.close()     // Catch:{ IOException -> 0x002f }
        L_0x0041:
            long r0 = (long) r1
            return r0
        L_0x0043:
            r0 = move-exception
            if (r2 == 0) goto L_0x004e
            r2.close()     // Catch:{ IOException -> 0x004a }
            goto L_0x004e
        L_0x004a:
            r1 = move-exception
            com.tencent.bugly.proguard.x.a(r1)
        L_0x004e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.r():long");
    }

    public static boolean h(Context context) {
        File file;
        if (j(context) != null) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < c.length; i++) {
            String[] strArr = c;
            if (i == 0) {
                String str = strArr[i];
                if (file.exists()) {
                }
            } else {
                file = new File(strArr[i]);
                if (!file.exists()) {
                }
            }
            arrayList.add(Integer.valueOf(i));
        }
        return (arrayList.isEmpty() ? null : arrayList.toString()) != null;
    }

    private static String j(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            String[] strArr = b;
            if (i >= strArr.length) {
                break;
            }
            try {
                packageManager.getPackageInfo(strArr[i], 1);
                arrayList.add(Integer.valueOf(i));
            } catch (PackageManager.NameNotFoundException unused) {
            }
            i++;
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList.toString();
    }

    public static boolean i(Context context) {
        return (((k(context) | v()) | w()) | u()) > 0;
    }

    private static int u() {
        try {
            Method method = Class.forName("android.app.ActivityManagerNative").getMethod("getDefault", new Class[0]);
            method.setAccessible(true);
            if (method.invoke((Object) null, new Object[0]).getClass().getName().startsWith("$Proxy")) {
                return 256;
            }
            return 0;
        } catch (Exception unused) {
            return 256;
        }
    }

    private static int k(Context context) {
        int i;
        PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getInstallerPackageName("de.robv.android.xposed.installer");
            i = 1;
        } catch (Exception unused) {
            i = 0;
        }
        try {
            packageManager.getInstallerPackageName("com.saurik.substrate");
            return i | 2;
        } catch (Exception unused2) {
            return i;
        }
    }

    private static int v() {
        try {
            throw new Exception("detect hook");
        } catch (Exception e2) {
            int i = 0;
            int i2 = 0;
            for (StackTraceElement stackTraceElement : e2.getStackTrace()) {
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge") && stackTraceElement.getMethodName().equals("main")) {
                    i |= 4;
                }
                if (stackTraceElement.getClassName().equals("de.robv.android.xposed.XposedBridge") && stackTraceElement.getMethodName().equals("handleHookedMethod")) {
                    i |= 8;
                }
                if (stackTraceElement.getClassName().equals("com.saurik.substrate.MS$2") && stackTraceElement.getMethodName().equals("invoked")) {
                    i |= 16;
                }
                if (stackTraceElement.getClassName().equals("com.android.internal.os.ZygoteInit") && (i2 = i2 + 1) == 2) {
                    i |= 32;
                }
            }
            return i;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x009a A[SYNTHETIC, Splitter:B:33:0x009a] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a6 A[SYNTHETIC, Splitter:B:40:0x00a6] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00b2 A[SYNTHETIC, Splitter:B:47:0x00b2] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00ba A[SYNTHETIC, Splitter:B:53:0x00ba] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:37:0x00a1=Splitter:B:37:0x00a1, B:30:0x0095=Splitter:B:30:0x0095, B:44:0x00ad=Splitter:B:44:0x00ad} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int w() {
        /*
            r0 = 0
            r1 = 0
            java.util.HashSet r2 = new java.util.HashSet     // Catch:{ UnsupportedEncodingException -> 0x00aa, FileNotFoundException -> 0x009e, IOException -> 0x0092, all -> 0x0090 }
            r2.<init>()     // Catch:{ UnsupportedEncodingException -> 0x00aa, FileNotFoundException -> 0x009e, IOException -> 0x0092, all -> 0x0090 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ UnsupportedEncodingException -> 0x00aa, FileNotFoundException -> 0x009e, IOException -> 0x0092, all -> 0x0090 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ UnsupportedEncodingException -> 0x00aa, FileNotFoundException -> 0x009e, IOException -> 0x0092, all -> 0x0090 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ UnsupportedEncodingException -> 0x00aa, FileNotFoundException -> 0x009e, IOException -> 0x0092, all -> 0x0090 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x00aa, FileNotFoundException -> 0x009e, IOException -> 0x0092, all -> 0x0090 }
            java.lang.String r7 = "/proc/"
            r6.<init>(r7)     // Catch:{ UnsupportedEncodingException -> 0x00aa, FileNotFoundException -> 0x009e, IOException -> 0x0092, all -> 0x0090 }
            int r7 = android.os.Process.myPid()     // Catch:{ UnsupportedEncodingException -> 0x00aa, FileNotFoundException -> 0x009e, IOException -> 0x0092, all -> 0x0090 }
            r6.append(r7)     // Catch:{ UnsupportedEncodingException -> 0x00aa, FileNotFoundException -> 0x009e, IOException -> 0x0092, all -> 0x0090 }
            java.lang.String r7 = "/maps"
            r6.append(r7)     // Catch:{ UnsupportedEncodingException -> 0x00aa, FileNotFoundException -> 0x009e, IOException -> 0x0092, all -> 0x0090 }
            java.lang.String r6 = r6.toString()     // Catch:{ UnsupportedEncodingException -> 0x00aa, FileNotFoundException -> 0x009e, IOException -> 0x0092, all -> 0x0090 }
            r5.<init>(r6)     // Catch:{ UnsupportedEncodingException -> 0x00aa, FileNotFoundException -> 0x009e, IOException -> 0x0092, all -> 0x0090 }
            java.lang.String r6 = "utf-8"
            r4.<init>(r5, r6)     // Catch:{ UnsupportedEncodingException -> 0x00aa, FileNotFoundException -> 0x009e, IOException -> 0x0092, all -> 0x0090 }
            r3.<init>(r4)     // Catch:{ UnsupportedEncodingException -> 0x00aa, FileNotFoundException -> 0x009e, IOException -> 0x0092, all -> 0x0090 }
        L_0x002f:
            java.lang.String r1 = r3.readLine()     // Catch:{ UnsupportedEncodingException -> 0x008e, FileNotFoundException -> 0x008c, IOException -> 0x008a }
            if (r1 == 0) goto L_0x0055
            java.lang.String r4 = ".so"
            boolean r4 = r1.endsWith(r4)     // Catch:{ UnsupportedEncodingException -> 0x008e, FileNotFoundException -> 0x008c, IOException -> 0x008a }
            if (r4 != 0) goto L_0x0045
            java.lang.String r4 = ".jar"
            boolean r4 = r1.endsWith(r4)     // Catch:{ UnsupportedEncodingException -> 0x008e, FileNotFoundException -> 0x008c, IOException -> 0x008a }
            if (r4 == 0) goto L_0x002f
        L_0x0045:
            java.lang.String r4 = " "
            int r4 = r1.lastIndexOf(r4)     // Catch:{ UnsupportedEncodingException -> 0x008e, FileNotFoundException -> 0x008c, IOException -> 0x008a }
            int r4 = r4 + 1
            java.lang.String r1 = r1.substring(r4)     // Catch:{ UnsupportedEncodingException -> 0x008e, FileNotFoundException -> 0x008c, IOException -> 0x008a }
            r2.add(r1)     // Catch:{ UnsupportedEncodingException -> 0x008e, FileNotFoundException -> 0x008c, IOException -> 0x008a }
            goto L_0x002f
        L_0x0055:
            java.util.Iterator r1 = r2.iterator()     // Catch:{ UnsupportedEncodingException -> 0x008e, FileNotFoundException -> 0x008c, IOException -> 0x008a }
        L_0x0059:
            boolean r2 = r1.hasNext()     // Catch:{ UnsupportedEncodingException -> 0x008e, FileNotFoundException -> 0x008c, IOException -> 0x008a }
            if (r2 == 0) goto L_0x0081
            java.lang.Object r2 = r1.next()     // Catch:{ UnsupportedEncodingException -> 0x008e, FileNotFoundException -> 0x008c, IOException -> 0x008a }
            r4 = r2
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ UnsupportedEncodingException -> 0x008e, FileNotFoundException -> 0x008c, IOException -> 0x008a }
            java.lang.String r4 = r4.toLowerCase()     // Catch:{ UnsupportedEncodingException -> 0x008e, FileNotFoundException -> 0x008c, IOException -> 0x008a }
            java.lang.String r5 = "xposed"
            boolean r4 = r4.contains(r5)     // Catch:{ UnsupportedEncodingException -> 0x008e, FileNotFoundException -> 0x008c, IOException -> 0x008a }
            if (r4 == 0) goto L_0x0074
            r0 = r0 | 64
        L_0x0074:
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ UnsupportedEncodingException -> 0x008e, FileNotFoundException -> 0x008c, IOException -> 0x008a }
            java.lang.String r4 = "com.saurik.substrate"
            boolean r2 = r2.contains(r4)     // Catch:{ UnsupportedEncodingException -> 0x008e, FileNotFoundException -> 0x008c, IOException -> 0x008a }
            if (r2 == 0) goto L_0x0059
            r0 = r0 | 128(0x80, float:1.794E-43)
            goto L_0x0059
        L_0x0081:
            r3.close()     // Catch:{ IOException -> 0x0085 }
            goto L_0x00b5
        L_0x0085:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00b5
        L_0x008a:
            r1 = move-exception
            goto L_0x0095
        L_0x008c:
            r1 = move-exception
            goto L_0x00a1
        L_0x008e:
            r1 = move-exception
            goto L_0x00ad
        L_0x0090:
            r0 = move-exception
            goto L_0x00b8
        L_0x0092:
            r2 = move-exception
            r3 = r1
            r1 = r2
        L_0x0095:
            r1.printStackTrace()     // Catch:{ all -> 0x00b6 }
            if (r3 == 0) goto L_0x00b5
            r3.close()     // Catch:{ IOException -> 0x0085 }
            goto L_0x00b5
        L_0x009e:
            r2 = move-exception
            r3 = r1
            r1 = r2
        L_0x00a1:
            r1.printStackTrace()     // Catch:{ all -> 0x00b6 }
            if (r3 == 0) goto L_0x00b5
            r3.close()     // Catch:{ IOException -> 0x0085 }
            goto L_0x00b5
        L_0x00aa:
            r2 = move-exception
            r3 = r1
            r1 = r2
        L_0x00ad:
            r1.printStackTrace()     // Catch:{ all -> 0x00b6 }
            if (r3 == 0) goto L_0x00b5
            r3.close()     // Catch:{ IOException -> 0x0085 }
        L_0x00b5:
            return r0
        L_0x00b6:
            r0 = move-exception
            r1 = r3
        L_0x00b8:
            if (r1 == 0) goto L_0x00c2
            r1.close()     // Catch:{ IOException -> 0x00be }
            goto L_0x00c2
        L_0x00be:
            r1 = move-exception
            r1.printStackTrace()
        L_0x00c2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.w():int");
    }

    public static boolean s() {
        float maxMemory = (float) (((double) Runtime.getRuntime().maxMemory()) / 1048576.0d);
        float f = (float) (((double) Runtime.getRuntime().totalMemory()) / 1048576.0d);
        float f2 = maxMemory - f;
        x.c("maxMemory : %f", Float.valueOf(maxMemory));
        x.c("totalMemory : %f", Float.valueOf(f));
        x.c("freeMemory : %f", Float.valueOf(f2));
        if (f2 < 10.0f) {
            return true;
        }
        return false;
    }
}
