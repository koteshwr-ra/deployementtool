package com.tencent.smtt.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import com.alibaba.android.arouter.utils.Consts;
import com.tencent.smtt.sdk.TbsExtensionFunctionManager;
import com.tencent.smtt.sdk.TbsPVConfig;
import com.tencent.smtt.sdk.TbsShareManager;
import java.io.File;
import java.util.regex.Pattern;

public class a {
    public static int a(Context context, File file) {
        try {
            return a(context, file, Build.VERSION.SDK_INT >= 20 ? !TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.DISABLE_GET_APK_VERSION_SWITCH_FILE_NAME) : false);
        } catch (Exception unused) {
            TbsLog.i("ApkUtil", "getApkVersion Failed");
            return 0;
        }
    }

    public static int a(Context context, File file, boolean z) {
        int b;
        if (file != null) {
            try {
                if (file.exists()) {
                    boolean contains = file.getName().contains("tbs.org");
                    boolean contains2 = file.getName().contains("x5.tbs.decouple");
                    if (contains || contains2) {
                        int a = a(contains2, file);
                        if (a > 0) {
                            return a;
                        }
                        if (!TbsShareManager.isThirdPartyApp(context)) {
                            if (!file.getAbsolutePath().contains(context.getApplicationInfo().packageName)) {
                                return 0;
                            }
                        }
                    }
                    boolean z2 = (Build.VERSION.SDK_INT == 23 || Build.VERSION.SDK_INT == 25) && Build.MANUFACTURER.toLowerCase().contains("mi");
                    TbsPVConfig.releaseInstance();
                    int readApk = TbsPVConfig.getInstance(context).getReadApk();
                    if (readApk == 1) {
                        z = false;
                        z2 = false;
                    } else if (readApk == 2) {
                        return 0;
                    }
                    if ((z || z2) && (b = b(file)) > 0) {
                        return b;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (file == null || !file.exists()) {
            return 0;
        }
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 1);
            if (packageArchiveInfo != null) {
                return packageArchiveInfo.versionCode;
            }
            return 0;
        } catch (Throwable th2) {
            th2.printStackTrace();
            return -1;
        }
    }

    private static int a(boolean z, File file) {
        try {
            File parentFile = file.getParentFile();
            if (parentFile == null) {
                return -1;
            }
            File[] listFiles = parentFile.listFiles();
            Pattern compile = Pattern.compile(a(z) + "(.*)");
            for (File file2 : listFiles) {
                if (compile.matcher(file2.getName()).find() && file2.isFile() && file2.exists()) {
                    return Integer.parseInt(file2.getName().substring(file2.getName().lastIndexOf(Consts.DOT) + 1));
                }
            }
            return -1;
        } catch (Exception unused) {
            return -1;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x005e A[SYNTHETIC, Splitter:B:25:0x005e] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006b A[SYNTHETIC, Splitter:B:33:0x006b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.io.File r10) {
        /*
            r0 = 16
            char[] r1 = new char[r0]
            r1 = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102} // fill-array
            r2 = 32
            char[] r2 = new char[r2]
            r3 = 0
            java.lang.String r4 = "MD5"
            java.security.MessageDigest r4 = java.security.MessageDigest.getInstance(r4)     // Catch:{ Exception -> 0x0057, all -> 0x0055 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0057, all -> 0x0055 }
            r5.<init>(r10)     // Catch:{ Exception -> 0x0057, all -> 0x0055 }
            r10 = 8192(0x2000, float:1.14794E-41)
            byte[] r10 = new byte[r10]     // Catch:{ Exception -> 0x0053 }
        L_0x001b:
            int r6 = r5.read(r10)     // Catch:{ Exception -> 0x0053 }
            r7 = -1
            r8 = 0
            if (r6 == r7) goto L_0x0027
            r4.update(r10, r8, r6)     // Catch:{ Exception -> 0x0053 }
            goto L_0x001b
        L_0x0027:
            byte[] r10 = r4.digest()     // Catch:{ Exception -> 0x0053 }
            r4 = 0
        L_0x002c:
            if (r8 >= r0) goto L_0x0045
            byte r6 = r10[r8]     // Catch:{ Exception -> 0x0053 }
            int r7 = r4 + 1
            int r9 = r6 >>> 4
            r9 = r9 & 15
            char r9 = r1[r9]     // Catch:{ Exception -> 0x0053 }
            r2[r4] = r9     // Catch:{ Exception -> 0x0053 }
            int r4 = r7 + 1
            r6 = r6 & 15
            char r6 = r1[r6]     // Catch:{ Exception -> 0x0053 }
            r2[r7] = r6     // Catch:{ Exception -> 0x0053 }
            int r8 = r8 + 1
            goto L_0x002c
        L_0x0045:
            java.lang.String r10 = new java.lang.String     // Catch:{ Exception -> 0x0053 }
            r10.<init>(r2)     // Catch:{ Exception -> 0x0053 }
            r5.close()     // Catch:{ IOException -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0052:
            return r10
        L_0x0053:
            r10 = move-exception
            goto L_0x0059
        L_0x0055:
            r10 = move-exception
            goto L_0x0069
        L_0x0057:
            r10 = move-exception
            r5 = r3
        L_0x0059:
            r10.printStackTrace()     // Catch:{ all -> 0x0067 }
            if (r5 == 0) goto L_0x0066
            r5.close()     // Catch:{ IOException -> 0x0062 }
            goto L_0x0066
        L_0x0062:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0066:
            return r3
        L_0x0067:
            r10 = move-exception
            r3 = r5
        L_0x0069:
            if (r3 == 0) goto L_0x0073
            r3.close()     // Catch:{ IOException -> 0x006f }
            goto L_0x0073
        L_0x006f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0073:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.a.a(java.io.File):java.lang.String");
    }

    public static final String a(boolean z) {
        return b.c() ? z ? "x5.64.decouple.backup" : "x5.64.backup" : z ? "x5.decouple.backup" : "x5.backup";
    }

    public static boolean a(Context context, File file, long j, int i) {
        if (file != null && file.exists()) {
            if (j > 0 && j != file.length()) {
                return false;
            }
            try {
                return i == a(context, file) && "3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a".equals(b.a(context, true, file));
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:47|(0)|(0)|53|54) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0037, code lost:
        r1 = java.lang.Integer.parseInt(r1[1].trim());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r6.close();
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0049, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0071, code lost:
        if (r2 != null) goto L_0x004d;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0048 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x0082 */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x006b A[SYNTHETIC, Splitter:B:38:0x006b] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x007a A[SYNTHETIC, Splitter:B:49:0x007a] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x007f A[Catch:{ Exception -> 0x0082 }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:53:0x0082=Splitter:B:53:0x0082, B:20:0x0048=Splitter:B:20:0x0048} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int b(java.io.File r6) {
        /*
            java.lang.Class<com.tencent.smtt.utils.a> r0 = com.tencent.smtt.utils.a.class
            monitor-enter(r0)
            r1 = 0
            java.util.jar.JarFile r2 = new java.util.jar.JarFile     // Catch:{ Exception -> 0x0062, all -> 0x005d }
            r2.<init>(r6)     // Catch:{ Exception -> 0x0062, all -> 0x005d }
            java.lang.String r6 = "assets/webkit/tbs.conf"
            java.util.jar.JarEntry r6 = r2.getJarEntry(r6)     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
            java.io.InputStream r6 = r2.getInputStream(r6)     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
            r3.<init>(r6)     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
            r6.<init>(r3)     // Catch:{ Exception -> 0x0058, all -> 0x0053 }
        L_0x001d:
            java.lang.String r1 = r6.readLine()     // Catch:{ Exception -> 0x0051 }
            if (r1 == 0) goto L_0x004a
            java.lang.String r3 = "tbs_core_version"
            boolean r3 = r1.contains(r3)     // Catch:{ Exception -> 0x0051 }
            if (r3 == 0) goto L_0x001d
            java.lang.String r3 = "="
            java.lang.String[] r1 = r1.split(r3)     // Catch:{ Exception -> 0x0051 }
            if (r1 == 0) goto L_0x001d
            int r3 = r1.length     // Catch:{ Exception -> 0x0051 }
            r4 = 2
            if (r3 != r4) goto L_0x001d
            r3 = 1
            r1 = r1[r3]     // Catch:{ Exception -> 0x0051 }
            java.lang.String r1 = r1.trim()     // Catch:{ Exception -> 0x0051 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Exception -> 0x0051 }
            r6.close()     // Catch:{ Exception -> 0x0048 }
            r2.close()     // Catch:{ Exception -> 0x0048 }
        L_0x0048:
            monitor-exit(r0)     // Catch:{ all -> 0x006f }
            return r1
        L_0x004a:
            r6.close()     // Catch:{ Exception -> 0x0074 }
        L_0x004d:
            r2.close()     // Catch:{ Exception -> 0x0074 }
            goto L_0x0074
        L_0x0051:
            r1 = move-exception
            goto L_0x0066
        L_0x0053:
            r6 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
            goto L_0x0078
        L_0x0058:
            r6 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
            goto L_0x0066
        L_0x005d:
            r6 = move-exception
            r2 = r1
            r1 = r6
            r6 = r2
            goto L_0x0078
        L_0x0062:
            r6 = move-exception
            r2 = r1
            r1 = r6
            r6 = r2
        L_0x0066:
            r1.printStackTrace()     // Catch:{ all -> 0x0077 }
            if (r6 == 0) goto L_0x0071
            r6.close()     // Catch:{ Exception -> 0x0074 }
            goto L_0x0071
        L_0x006f:
            r6 = move-exception
            goto L_0x0083
        L_0x0071:
            if (r2 == 0) goto L_0x0074
            goto L_0x004d
        L_0x0074:
            r6 = -1
            monitor-exit(r0)     // Catch:{ all -> 0x006f }
            return r6
        L_0x0077:
            r1 = move-exception
        L_0x0078:
            if (r6 == 0) goto L_0x007d
            r6.close()     // Catch:{ Exception -> 0x0082 }
        L_0x007d:
            if (r2 == 0) goto L_0x0082
            r2.close()     // Catch:{ Exception -> 0x0082 }
        L_0x0082:
            throw r1     // Catch:{ all -> 0x006f }
        L_0x0083:
            monitor-exit(r0)     // Catch:{ all -> 0x006f }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.a.b(java.io.File):int");
    }
}
