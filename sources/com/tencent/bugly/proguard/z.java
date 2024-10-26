package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import com.ciot.base.constant.NetConstant;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.info.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.helpers.DateLayout;
import xcrash.TombstoneParser;

/* compiled from: BUGLY */
public class z {
    private static Map<String, String> a;

    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (Throwable th2) {
            if (x.a(th2)) {
                return "fail";
            }
            th2.printStackTrace();
            return "fail";
        }
    }

    public static String a() {
        return a(System.currentTimeMillis());
    }

    public static String a(long j) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(j));
        } catch (Exception unused) {
            return new Date().toString();
        }
    }

    public static String a(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
        } catch (Exception unused) {
            return new Date().toString();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x006a A[Catch:{ all -> 0x007d }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006f A[SYNTHETIC, Splitter:B:28:0x006f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(java.io.File r5, java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.String r5 = "rqdp{  ZF end}"
            r0 = 0
            if (r6 == 0) goto L_0x008e
            int r1 = r6.length()
            if (r1 != 0) goto L_0x000d
            goto L_0x008e
        L_0x000d:
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "rqdp{  ZF start}"
            com.tencent.bugly.proguard.x.c(r3, r2)
            java.lang.String r2 = "UTF-8"
            byte[] r6 = r6.getBytes(r2)     // Catch:{ all -> 0x0062 }
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x0062 }
            r2.<init>(r6)     // Catch:{ all -> 0x0062 }
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0062 }
            r6.<init>()     // Catch:{ all -> 0x0062 }
            java.util.zip.ZipOutputStream r3 = new java.util.zip.ZipOutputStream     // Catch:{ all -> 0x0062 }
            r3.<init>(r6)     // Catch:{ all -> 0x0062 }
            r4 = 8
            r3.setMethod(r4)     // Catch:{ all -> 0x0060 }
            java.util.zip.ZipEntry r4 = new java.util.zip.ZipEntry     // Catch:{ all -> 0x0060 }
            r4.<init>(r7)     // Catch:{ all -> 0x0060 }
            r3.putNextEntry(r4)     // Catch:{ all -> 0x0060 }
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch:{ all -> 0x0060 }
        L_0x003b:
            int r4 = r2.read(r7)     // Catch:{ all -> 0x0060 }
            if (r4 <= 0) goto L_0x0045
            r3.write(r7, r1, r4)     // Catch:{ all -> 0x0060 }
            goto L_0x003b
        L_0x0045:
            r3.closeEntry()     // Catch:{ all -> 0x0060 }
            r3.flush()     // Catch:{ all -> 0x0060 }
            r3.finish()     // Catch:{ all -> 0x0060 }
            byte[] r6 = r6.toByteArray()     // Catch:{ all -> 0x0060 }
            r3.close()     // Catch:{ IOException -> 0x0056 }
            goto L_0x005a
        L_0x0056:
            r7 = move-exception
            r7.printStackTrace()
        L_0x005a:
            java.lang.Object[] r7 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.c(r5, r7)
            return r6
        L_0x0060:
            r6 = move-exception
            goto L_0x0064
        L_0x0062:
            r6 = move-exception
            r3 = r0
        L_0x0064:
            boolean r7 = com.tencent.bugly.proguard.x.a(r6)     // Catch:{ all -> 0x007d }
            if (r7 != 0) goto L_0x006d
            r6.printStackTrace()     // Catch:{ all -> 0x007d }
        L_0x006d:
            if (r3 == 0) goto L_0x0077
            r3.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x0077
        L_0x0073:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0077:
            java.lang.Object[] r6 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.c(r5, r6)
            return r0
        L_0x007d:
            r6 = move-exception
            if (r3 == 0) goto L_0x0088
            r3.close()     // Catch:{ IOException -> 0x0084 }
            goto L_0x0088
        L_0x0084:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0088:
            java.lang.Object[] r7 = new java.lang.Object[r1]
            com.tencent.bugly.proguard.x.c(r5, r7)
            throw r6
        L_0x008e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.z.a(java.io.File, java.lang.String, java.lang.String):byte[]");
    }

    public static byte[] a(byte[] bArr, int i) {
        if (bArr == null) {
            return bArr;
        }
        x.c("[Util] Zip %d bytes data with type %s", Integer.valueOf(bArr.length), "Gzip");
        try {
            ae a2 = ad.a(2);
            if (a2 == null) {
                return null;
            }
            return a2.a(bArr);
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static byte[] b(byte[] bArr, int i) {
        if (bArr == null) {
            return bArr;
        }
        x.c("[Util] Unzip %d bytes data with type %s", Integer.valueOf(bArr.length), "Gzip");
        try {
            ae a2 = ad.a(2);
            if (a2 == null) {
                return null;
            }
            return a2.b(bArr);
        } catch (Throwable th) {
            if (th.getMessage() != null && th.getMessage().contains("Not in GZIP format")) {
                x.d(th.getMessage(), new Object[0]);
            } else if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static long b() {
        try {
            return (((System.currentTimeMillis() + ((long) TimeZone.getDefault().getRawOffset())) / DateUtils.MILLIS_PER_DAY) * DateUtils.MILLIS_PER_DAY) - ((long) TimeZone.getDefault().getRawOffset());
        } catch (Throwable th) {
            if (x.a(th)) {
                return -1;
            }
            th.printStackTrace();
            return -1;
        }
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return DateLayout.NULL_DATE_FORMAT;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(bArr);
            byte[] digest = instance.digest();
            if (digest == null) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    stringBuffer.append(NetConstant.PAGE_ID_HOME);
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString().toUpperCase();
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.io.FileInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.util.zip.ZipOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.io.FileInputStream} */
    /* JADX WARNING: type inference failed for: r3v0, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARNING: type inference failed for: r3v1 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00bc A[Catch:{ all -> 0x00d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00c1 A[SYNTHETIC, Splitter:B:59:0x00c1] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00cb A[SYNTHETIC, Splitter:B:64:0x00cb] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.io.File r6, java.io.File r7, int r8) {
        /*
            java.lang.String r8 = "rqdp{  ZF end}"
            r0 = 0
            java.lang.Object[] r1 = new java.lang.Object[r0]
            java.lang.String r2 = "rqdp{  ZF start}"
            com.tencent.bugly.proguard.x.c(r2, r1)
            if (r6 == 0) goto L_0x00fd
            if (r7 == 0) goto L_0x00fd
            boolean r1 = r6.equals(r7)
            if (r1 == 0) goto L_0x0016
            goto L_0x00fd
        L_0x0016:
            boolean r1 = r6.exists()
            if (r1 == 0) goto L_0x00f5
            boolean r1 = r6.canRead()
            if (r1 != 0) goto L_0x0024
            goto L_0x00f5
        L_0x0024:
            java.io.File r1 = r7.getParentFile()     // Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x003b
            java.io.File r1 = r7.getParentFile()     // Catch:{ all -> 0x0045 }
            boolean r1 = r1.exists()     // Catch:{ all -> 0x0045 }
            if (r1 != 0) goto L_0x003b
            java.io.File r1 = r7.getParentFile()     // Catch:{ all -> 0x0045 }
            r1.mkdirs()     // Catch:{ all -> 0x0045 }
        L_0x003b:
            boolean r1 = r7.exists()     // Catch:{ all -> 0x0045 }
            if (r1 != 0) goto L_0x004f
            r7.createNewFile()     // Catch:{ all -> 0x0045 }
            goto L_0x004f
        L_0x0045:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.proguard.x.a(r1)
            if (r2 != 0) goto L_0x004f
            r1.printStackTrace()
        L_0x004f:
            boolean r1 = r7.exists()
            if (r1 == 0) goto L_0x00f4
            boolean r1 = r7.canRead()
            if (r1 != 0) goto L_0x005d
            goto L_0x00f4
        L_0x005d:
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x00b4 }
            r2.<init>(r6)     // Catch:{ all -> 0x00b4 }
            java.util.zip.ZipOutputStream r3 = new java.util.zip.ZipOutputStream     // Catch:{ all -> 0x00b0 }
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x00b0 }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ all -> 0x00b0 }
            r5.<init>(r7)     // Catch:{ all -> 0x00b0 }
            r4.<init>(r5)     // Catch:{ all -> 0x00b0 }
            r3.<init>(r4)     // Catch:{ all -> 0x00b0 }
            r7 = 8
            r3.setMethod(r7)     // Catch:{ all -> 0x00ae }
            java.util.zip.ZipEntry r7 = new java.util.zip.ZipEntry     // Catch:{ all -> 0x00ae }
            java.lang.String r6 = r6.getName()     // Catch:{ all -> 0x00ae }
            r7.<init>(r6)     // Catch:{ all -> 0x00ae }
            r3.putNextEntry(r7)     // Catch:{ all -> 0x00ae }
            r6 = 5000(0x1388, float:7.006E-42)
            byte[] r6 = new byte[r6]     // Catch:{ all -> 0x00ae }
        L_0x0087:
            int r7 = r2.read(r6)     // Catch:{ all -> 0x00ae }
            if (r7 <= 0) goto L_0x0091
            r3.write(r6, r0, r7)     // Catch:{ all -> 0x00ae }
            goto L_0x0087
        L_0x0091:
            r3.flush()     // Catch:{ all -> 0x00ae }
            r3.closeEntry()     // Catch:{ all -> 0x00ae }
            r2.close()     // Catch:{ IOException -> 0x009b }
            goto L_0x009f
        L_0x009b:
            r6 = move-exception
            r6.printStackTrace()
        L_0x009f:
            r3.close()     // Catch:{ IOException -> 0x00a3 }
            goto L_0x00a7
        L_0x00a3:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00a7:
            java.lang.Object[] r6 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.x.c(r8, r6)
            r6 = 1
            return r6
        L_0x00ae:
            r6 = move-exception
            goto L_0x00b2
        L_0x00b0:
            r6 = move-exception
            r3 = r1
        L_0x00b2:
            r1 = r2
            goto L_0x00b6
        L_0x00b4:
            r6 = move-exception
            r3 = r1
        L_0x00b6:
            boolean r7 = com.tencent.bugly.proguard.x.a(r6)     // Catch:{ all -> 0x00d9 }
            if (r7 != 0) goto L_0x00bf
            r6.printStackTrace()     // Catch:{ all -> 0x00d9 }
        L_0x00bf:
            if (r1 == 0) goto L_0x00c9
            r1.close()     // Catch:{ IOException -> 0x00c5 }
            goto L_0x00c9
        L_0x00c5:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00c9:
            if (r3 == 0) goto L_0x00d3
            r3.close()     // Catch:{ IOException -> 0x00cf }
            goto L_0x00d3
        L_0x00cf:
            r6 = move-exception
            r6.printStackTrace()
        L_0x00d3:
            java.lang.Object[] r6 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.x.c(r8, r6)
            return r0
        L_0x00d9:
            r6 = move-exception
            if (r1 == 0) goto L_0x00e4
            r1.close()     // Catch:{ IOException -> 0x00e0 }
            goto L_0x00e4
        L_0x00e0:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00e4:
            if (r3 == 0) goto L_0x00ee
            r3.close()     // Catch:{ IOException -> 0x00ea }
            goto L_0x00ee
        L_0x00ea:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00ee:
            java.lang.Object[] r7 = new java.lang.Object[r0]
            com.tencent.bugly.proguard.x.c(r8, r7)
            throw r6
        L_0x00f4:
            return r0
        L_0x00f5:
            java.lang.Object[] r6 = new java.lang.Object[r0]
            java.lang.String r7 = "rqdp{  !sFile.exists() || !sFile.canRead(),pls check ,return!}"
            com.tencent.bugly.proguard.x.d(r7, r6)
            return r0
        L_0x00fd:
            java.lang.Object[] r6 = new java.lang.Object[r0]
            java.lang.String r7 = "rqdp{  err ZF 1R!}"
            com.tencent.bugly.proguard.x.d(r7, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.z.a(java.io.File, java.io.File, int):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0073 A[Catch:{ all -> 0x008b }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0078 A[SYNTHETIC, Splitter:B:35:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0082 A[SYNTHETIC, Splitter:B:40:0x0082] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.ArrayList<java.lang.String> a(android.content.Context r4, java.lang.String[] r5) {
        /*
            boolean r4 = com.tencent.bugly.crashreport.common.info.AppInfo.e(r4)
            if (r4 == 0) goto L_0x0016
            java.util.ArrayList r4 = new java.util.ArrayList
            java.lang.String r5 = "unknown(low memory)"
            java.lang.String[] r5 = new java.lang.String[]{r5}
            java.util.List r5 = java.util.Arrays.asList(r5)
            r4.<init>(r5)
            return r4
        L_0x0016:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ all -> 0x006a }
            java.lang.Process r5 = r1.exec(r5)     // Catch:{ all -> 0x006a }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x006a }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ all -> 0x006a }
            java.io.InputStream r3 = r5.getInputStream()     // Catch:{ all -> 0x006a }
            r2.<init>(r3)     // Catch:{ all -> 0x006a }
            r1.<init>(r2)     // Catch:{ all -> 0x006a }
        L_0x0032:
            java.lang.String r2 = r1.readLine()     // Catch:{ all -> 0x0067 }
            if (r2 == 0) goto L_0x003c
            r4.add(r2)     // Catch:{ all -> 0x0067 }
            goto L_0x0032
        L_0x003c:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ all -> 0x0067 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x0067 }
            java.io.InputStream r5 = r5.getErrorStream()     // Catch:{ all -> 0x0067 }
            r3.<init>(r5)     // Catch:{ all -> 0x0067 }
            r2.<init>(r3)     // Catch:{ all -> 0x0067 }
        L_0x004a:
            java.lang.String r5 = r2.readLine()     // Catch:{ all -> 0x0065 }
            if (r5 == 0) goto L_0x0054
            r4.add(r5)     // Catch:{ all -> 0x0065 }
            goto L_0x004a
        L_0x0054:
            r1.close()     // Catch:{ IOException -> 0x0058 }
            goto L_0x005c
        L_0x0058:
            r5 = move-exception
            r5.printStackTrace()
        L_0x005c:
            r2.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x0064
        L_0x0060:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0064:
            return r4
        L_0x0065:
            r4 = move-exception
            goto L_0x006d
        L_0x0067:
            r4 = move-exception
            r2 = r0
            goto L_0x006d
        L_0x006a:
            r4 = move-exception
            r1 = r0
            r2 = r1
        L_0x006d:
            boolean r5 = com.tencent.bugly.proguard.x.a(r4)     // Catch:{ all -> 0x008b }
            if (r5 != 0) goto L_0x0076
            r4.printStackTrace()     // Catch:{ all -> 0x008b }
        L_0x0076:
            if (r1 == 0) goto L_0x0080
            r1.close()     // Catch:{ IOException -> 0x007c }
            goto L_0x0080
        L_0x007c:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0080:
            if (r2 == 0) goto L_0x008a
            r2.close()     // Catch:{ IOException -> 0x0086 }
            goto L_0x008a
        L_0x0086:
            r4 = move-exception
            r4.printStackTrace()
        L_0x008a:
            return r0
        L_0x008b:
            r4 = move-exception
            if (r1 == 0) goto L_0x0096
            r1.close()     // Catch:{ IOException -> 0x0092 }
            goto L_0x0096
        L_0x0092:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0096:
            if (r2 == 0) goto L_0x00a0
            r2.close()     // Catch:{ IOException -> 0x009c }
            goto L_0x00a0
        L_0x009c:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00a0:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.z.a(android.content.Context, java.lang.String[]):java.util.ArrayList");
    }

    public static String a(Context context, String str) {
        Class<z> cls = z.class;
        if (str == null || str.trim().equals("")) {
            return "";
        }
        if (a == null) {
            a = new HashMap();
            String str2 = "/system/bin/sh";
            if (!new File(str2).exists() || !new File(str2).canExecute()) {
                str2 = "sh";
            }
            ArrayList<String> a2 = a(context, new String[]{str2, "-c", "getprop"});
            if (a2 != null && a2.size() > 0) {
                x.b(cls, "Successfully get 'getprop' list.", new Object[0]);
                Pattern compile = Pattern.compile("\\[(.+)\\]: \\[(.*)\\]");
                for (String matcher : a2) {
                    Matcher matcher2 = compile.matcher(matcher);
                    if (matcher2.find()) {
                        a.put(matcher2.group(1), matcher2.group(2));
                    }
                }
                x.b(cls, "Systems properties number: %d.", Integer.valueOf(a.size()));
            }
        }
        return a.containsKey(str) ? a.get(str) : "fail";
    }

    public static void b(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean a(String str) {
        return str == null || str.trim().length() <= 0;
    }

    public static void b(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.isFile() && file.exists() && file.canWrite()) {
                file.delete();
            }
        }
    }

    public static byte[] c(long j) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(j);
            return sb.toString().getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long b(byte[] bArr) {
        if (bArr == null) {
            return -1;
        }
        try {
            return Long.parseLong(new String(bArr, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r0 = r1.getApplicationContext();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.Context a(android.content.Context r1) {
        /*
            if (r1 != 0) goto L_0x0003
            return r1
        L_0x0003:
            android.content.Context r0 = r1.getApplicationContext()
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.z.a(android.content.Context):android.content.Context");
    }

    public static String b(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    public static void a(Class<?> cls, String str, Object obj, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set((Object) null, obj);
        } catch (Exception unused) {
        }
    }

    public static Object a(String str, String str2, Object obj, Class<?>[] clsArr, Object[] objArr) {
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke((Object) null, objArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void a(Parcel parcel, Map<String, PlugInBean> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle((Bundle) null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(next.getKey());
            arrayList2.add(next.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putInt("pluginNum", arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            bundle.putString("pluginKey" + i, (String) arrayList.get(i));
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            bundle.putString("pluginVal" + i2 + "plugInId", ((PlugInBean) arrayList2.get(i2)).a);
            bundle.putString("pluginVal" + i2 + "plugInUUID", ((PlugInBean) arrayList2.get(i2)).c);
            bundle.putString("pluginVal" + i2 + "plugInVersion", ((PlugInBean) arrayList2.get(i2)).b);
        }
        parcel.writeBundle(bundle);
    }

    public static Map<String, PlugInBean> a(Parcel parcel) {
        Bundle readBundle = parcel.readBundle();
        HashMap hashMap = null;
        if (readBundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int intValue = ((Integer) readBundle.get("pluginNum")).intValue();
        for (int i = 0; i < intValue; i++) {
            arrayList.add(readBundle.getString("pluginKey" + i));
        }
        for (int i2 = 0; i2 < intValue; i2++) {
            String string = readBundle.getString("pluginVal" + i2 + "plugInId");
            String string2 = readBundle.getString("pluginVal" + i2 + "plugInUUID");
            arrayList2.add(new PlugInBean(string, readBundle.getString("pluginVal" + i2 + "plugInVersion"), string2));
        }
        if (arrayList.size() == arrayList2.size()) {
            hashMap = new HashMap(arrayList.size());
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                hashMap.put(arrayList.get(i3), PlugInBean.class.cast(arrayList2.get(i3)));
            }
        } else {
            x.e("map plugin parcel error!", new Object[0]);
        }
        return hashMap;
    }

    public static void b(Parcel parcel, Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            parcel.writeBundle((Bundle) null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(next.getKey());
            arrayList2.add(next.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("keys", arrayList);
        bundle.putStringArrayList("values", arrayList2);
        parcel.writeBundle(bundle);
    }

    public static Map<String, String> b(Parcel parcel) {
        Bundle readBundle = parcel.readBundle();
        HashMap hashMap = null;
        if (readBundle == null) {
            return null;
        }
        ArrayList<String> stringArrayList = readBundle.getStringArrayList("keys");
        ArrayList<String> stringArrayList2 = readBundle.getStringArrayList("values");
        if (stringArrayList == null || stringArrayList2 == null || stringArrayList.size() != stringArrayList2.size()) {
            x.e("map parcel error!", new Object[0]);
        } else {
            hashMap = new HashMap(stringArrayList.size());
            for (int i = 0; i < stringArrayList.size(); i++) {
                hashMap.put(stringArrayList.get(i), stringArrayList2.get(i));
            }
        }
        return hashMap;
    }

    public static byte[] a(Parcelable parcelable) {
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    public static <T> T a(byte[] bArr, Parcelable.Creator<T> creator) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        try {
            T createFromParcel = creator.createFromParcel(obtain);
            if (obtain != null) {
                obtain.recycle();
            }
            return createFromParcel;
        } catch (Throwable th) {
            if (obtain != null) {
                obtain.recycle();
            }
            throw th;
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Process, java.lang.String] */
    public static String a(Context context, int i, String str) {
        String[] strArr;
        ? r0 = 0;
        if (!AppInfo.a(context, "android.permission.READ_LOGS")) {
            x.d("no read_log permission!", new Object[0]);
            return r0;
        }
        if (str == null) {
            strArr = new String[]{TombstoneParser.keyLogcat, "-d", "-v", "threadtime"};
        } else {
            strArr = new String[]{TombstoneParser.keyLogcat, "-d", "-v", "threadtime", "-s", str};
        }
        StringBuilder sb = new StringBuilder();
        try {
            Process exec = Runtime.getRuntime().exec(strArr);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append(StringUtils.LF);
                if (i > 0 && sb.length() > i) {
                    sb.delete(0, sb.length() - i);
                }
            }
            String sb2 = sb.toString();
            if (exec != null) {
                try {
                    exec.getOutputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    exec.getInputStream().close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                try {
                    exec.getErrorStream().close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            return sb2;
        } catch (Throwable th) {
            if (r0 != 0) {
                try {
                    r0.getOutputStream().close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                try {
                    r0.getInputStream().close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                try {
                    r0.getErrorStream().close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static Map<String, String> a(int i, boolean z) {
        HashMap hashMap = new HashMap(12);
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        if (allStackTraces == null) {
            return null;
        }
        Thread thread = Looper.getMainLooper().getThread();
        if (!allStackTraces.containsKey(thread)) {
            allStackTraces.put(thread, thread.getStackTrace());
        }
        Thread.currentThread().getId();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : allStackTraces.entrySet()) {
            int i2 = 0;
            sb.setLength(0);
            if (!(next.getValue() == null || ((StackTraceElement[]) next.getValue()).length == 0)) {
                StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) next.getValue();
                int length = stackTraceElementArr.length;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTraceElementArr[i2];
                    if (i > 0 && sb.length() >= i) {
                        sb.append("\n[Stack over limit size :" + i + " , has been cut!]");
                        break;
                    }
                    sb.append(stackTraceElement.toString());
                    sb.append(StringUtils.LF);
                    i2++;
                }
                hashMap.put(((Thread) next.getKey()).getName() + "(" + ((Thread) next.getKey()).getId() + ")", sb.toString());
            }
        }
        return hashMap;
    }

    public static boolean a(Context context, String str, long j) {
        x.c("[Util] Try to lock file:%s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (file.exists()) {
                if (System.currentTimeMillis() - file.lastModified() < 10000) {
                    return false;
                }
                x.c("[Util] Lock file (%s) is expired, unlock it.", str);
                b(context, str);
            }
            if (file.createNewFile()) {
                x.c("[Util] Successfully locked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                return true;
            }
            x.c("[Util] Failed to locked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return false;
        } catch (Throwable th) {
            x.a(th);
            return false;
        }
    }

    public static boolean b(Context context, String str) {
        x.c("[Util] Try to unlock file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + str);
            if (!file.exists()) {
                return true;
            }
            if (!file.delete()) {
                return false;
            }
            x.c("[Util] Successfully unlocked file: %s (pid=%d | tid=%d)", str, Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return true;
        } catch (Throwable th) {
            x.a(th);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0067 A[SYNTHETIC, Splitter:B:30:0x0067] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.io.File r5, int r6, boolean r7) {
        /*
            r0 = 0
            if (r5 == 0) goto L_0x007c
            boolean r1 = r5.exists()
            if (r1 == 0) goto L_0x007c
            boolean r1 = r5.canRead()
            if (r1 != 0) goto L_0x0011
            goto L_0x007c
        L_0x0011:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0060 }
            r1.<init>()     // Catch:{ all -> 0x0060 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ all -> 0x0060 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x0060 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ all -> 0x0060 }
            r4.<init>(r5)     // Catch:{ all -> 0x0060 }
            java.lang.String r5 = "utf-8"
            r3.<init>(r4, r5)     // Catch:{ all -> 0x0060 }
            r2.<init>(r3)     // Catch:{ all -> 0x0060 }
        L_0x0027:
            java.lang.String r5 = r2.readLine()     // Catch:{ all -> 0x005e }
            if (r5 == 0) goto L_0x0051
            r1.append(r5)     // Catch:{ all -> 0x005e }
            java.lang.String r5 = "\n"
            r1.append(r5)     // Catch:{ all -> 0x005e }
            if (r6 <= 0) goto L_0x0027
            int r5 = r1.length()     // Catch:{ all -> 0x005e }
            if (r5 <= r6) goto L_0x0027
            if (r7 == 0) goto L_0x0047
            int r5 = r1.length()     // Catch:{ all -> 0x005e }
            r1.delete(r6, r5)     // Catch:{ all -> 0x005e }
            goto L_0x0051
        L_0x0047:
            r5 = 0
            int r3 = r1.length()     // Catch:{ all -> 0x005e }
            int r3 = r3 - r6
            r1.delete(r5, r3)     // Catch:{ all -> 0x005e }
            goto L_0x0027
        L_0x0051:
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x005e }
            r2.close()     // Catch:{ Exception -> 0x0059 }
            goto L_0x005d
        L_0x0059:
            r6 = move-exception
            com.tencent.bugly.proguard.x.a(r6)
        L_0x005d:
            return r5
        L_0x005e:
            r5 = move-exception
            goto L_0x0062
        L_0x0060:
            r5 = move-exception
            r2 = r0
        L_0x0062:
            com.tencent.bugly.proguard.x.a(r5)     // Catch:{ all -> 0x0070 }
            if (r2 == 0) goto L_0x006f
            r2.close()     // Catch:{ Exception -> 0x006b }
            goto L_0x006f
        L_0x006b:
            r5 = move-exception
            com.tencent.bugly.proguard.x.a(r5)
        L_0x006f:
            return r0
        L_0x0070:
            r5 = move-exception
            if (r2 == 0) goto L_0x007b
            r2.close()     // Catch:{ Exception -> 0x0077 }
            goto L_0x007b
        L_0x0077:
            r6 = move-exception
            com.tencent.bugly.proguard.x.a(r6)
        L_0x007b:
            throw r5
        L_0x007c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.z.a(java.io.File, int, boolean):java.lang.String");
    }

    private static BufferedReader a(File file) {
        if (file != null && file.exists() && file.canRead()) {
            try {
                return new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            } catch (Throwable th) {
                x.a(th);
            }
        }
        return null;
    }

    public static BufferedReader a(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            File file = new File(str, str2);
            if (file.exists()) {
                if (file.canRead()) {
                    return a(file);
                }
            }
            return null;
        } catch (NullPointerException e) {
            x.a(e);
            return null;
        }
    }

    public static Thread a(Runnable runnable, String str) {
        try {
            Thread thread = new Thread(runnable);
            thread.setName(str);
            thread.start();
            return thread;
        } catch (Throwable th) {
            x.e("[Util] Failed to start a thread to execute task with message: %s", th.getMessage());
            return null;
        }
    }

    public static boolean a(Runnable runnable) {
        if (runnable == null) {
            return false;
        }
        w a2 = w.a();
        if (a2 != null) {
            return a2.a(runnable);
        }
        String[] split = runnable.getClass().getName().split("\\.");
        return a(runnable, split[split.length - 1]) != null;
    }

    public static boolean c(String str) {
        if (str == null || str.trim().length() <= 0) {
            return false;
        }
        if (str.length() > 255) {
            x.a("URL(%s)'s length is larger than 255.", str);
            return false;
        } else if (str.toLowerCase().startsWith("http")) {
            return true;
        } else {
            x.a("URL(%s) is not start with \"http\".", str);
            return false;
        }
    }

    public static SharedPreferences a(String str, Context context) {
        if (context != null) {
            return context.getSharedPreferences(str, 0);
        }
        return null;
    }

    public static String b(String str, String str2) {
        return (a.b() == null || a.b().G == null) ? "" : a.b().G.getString(str, str2);
    }
}
