package com.tencent.bugly.proguard;

import android.content.Context;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* compiled from: BUGLY */
public final class s {
    private static s b;
    public Map<String, String> a = null;
    private Context c;

    private s(Context context) {
        this.c = context;
    }

    public static s a(Context context) {
        if (b == null) {
            b = new s(context);
        }
        return b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0185, code lost:
        if (com.tencent.bugly.proguard.x.a(r4) != false) goto L_0x018a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0187, code lost:
        r4.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0167, code lost:
        if (com.tencent.bugly.proguard.x.a(r4) != false) goto L_0x018a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0178 A[Catch:{ all -> 0x016c, all -> 0x0192 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] a(java.lang.String r21, byte[] r22, com.tencent.bugly.proguard.v r23, java.util.Map<java.lang.String, java.lang.String> r24) {
        /*
            r20 = this;
            r1 = r20
            r2 = r22
            r3 = r23
            r4 = 0
            r5 = 0
            if (r21 != 0) goto L_0x0012
            java.lang.Object[] r0 = new java.lang.Object[r5]
            java.lang.String r2 = "Failed for no URL."
            com.tencent.bugly.proguard.x.e(r2, r0)
            return r4
        L_0x0012:
            if (r2 != 0) goto L_0x0017
            r8 = 0
            goto L_0x0019
        L_0x0017:
            int r0 = r2.length
            long r8 = (long) r0
        L_0x0019:
            r0 = 4
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r0[r5] = r21
            java.lang.Long r10 = java.lang.Long.valueOf(r8)
            r11 = 1
            r0[r11] = r10
            int r10 = android.os.Process.myPid()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r12 = 2
            r0[r12] = r10
            r10 = 3
            int r13 = android.os.Process.myTid()
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            r0[r10] = r13
            java.lang.String r10 = "request: %s, send: %d (pid=%d | tid=%d)"
            com.tencent.bugly.proguard.x.c(r10, r0)
            r10 = r21
            r0 = 0
            r13 = 0
            r14 = 0
        L_0x0045:
            if (r0 > 0) goto L_0x01b2
            if (r13 > 0) goto L_0x01b2
            if (r14 == 0) goto L_0x004e
            r6 = r0
            r14 = 0
            goto L_0x007d
        L_0x004e:
            int r0 = r0 + 1
            if (r0 <= r11) goto L_0x007c
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            java.lang.String r6 = "try time: "
            r15.<init>(r6)
            r15.append(r0)
            java.lang.String r6 = r15.toString()
            java.lang.Object[] r7 = new java.lang.Object[r5]
            com.tencent.bugly.proguard.x.c(r6, r7)
            java.util.Random r6 = new java.util.Random
            long r11 = java.lang.System.currentTimeMillis()
            r6.<init>(r11)
            r11 = 10000(0x2710, float:1.4013E-41)
            int r6 = r6.nextInt(r11)
            long r11 = (long) r6
            r18 = 10000(0x2710, double:4.9407E-320)
            long r11 = r11 + r18
            android.os.SystemClock.sleep(r11)
        L_0x007c:
            r6 = r0
        L_0x007d:
            android.content.Context r0 = r1.c
            java.lang.String r0 = com.tencent.bugly.crashreport.common.info.b.b(r0)
            if (r0 != 0) goto L_0x0090
            java.lang.Object[] r0 = new java.lang.Object[r5]
            java.lang.String r11 = "Failed to request for network not avail"
            com.tencent.bugly.proguard.x.d(r11, r0)
            r0 = r6
        L_0x008d:
            r11 = 1
            r12 = 2
            goto L_0x0045
        L_0x0090:
            r3.a((long) r8)
            r11 = r24
            java.net.HttpURLConnection r12 = r1.a((java.lang.String) r10, (byte[]) r2, (java.lang.String) r0, (java.util.Map<java.lang.String, java.lang.String>) r11)
            if (r12 == 0) goto L_0x019e
            int r0 = r12.getResponseCode()     // Catch:{ IOException -> 0x016f }
            r7 = 200(0xc8, float:2.8E-43)
            if (r0 != r7) goto L_0x00c7
            java.util.Map r0 = a((java.net.HttpURLConnection) r12)     // Catch:{ IOException -> 0x016f }
            r1.a = r0     // Catch:{ IOException -> 0x016f }
            byte[] r7 = b(r12)     // Catch:{ IOException -> 0x016f }
            if (r7 != 0) goto L_0x00b2
            r4 = 0
            goto L_0x00b4
        L_0x00b2:
            int r0 = r7.length     // Catch:{ IOException -> 0x016f }
            long r4 = (long) r0     // Catch:{ IOException -> 0x016f }
        L_0x00b4:
            r3.b(r4)     // Catch:{ IOException -> 0x016f }
            r12.disconnect()     // Catch:{ all -> 0x00bb }
            goto L_0x00c6
        L_0x00bb:
            r0 = move-exception
            r2 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r2)
            if (r0 != 0) goto L_0x00c6
            r2.printStackTrace()
        L_0x00c6:
            return r7
        L_0x00c7:
            r4 = 301(0x12d, float:4.22E-43)
            if (r0 == r4) goto L_0x00da
            r4 = 302(0x12e, float:4.23E-43)
            if (r0 == r4) goto L_0x00da
            r4 = 303(0x12f, float:4.25E-43)
            if (r0 == r4) goto L_0x00da
            r4 = 307(0x133, float:4.3E-43)
            if (r0 != r4) goto L_0x00d8
            goto L_0x00da
        L_0x00d8:
            r4 = 0
            goto L_0x00db
        L_0x00da:
            r4 = 1
        L_0x00db:
            if (r4 == 0) goto L_0x0137
            java.lang.String r4 = "Location"
            java.lang.String r4 = r12.getHeaderField(r4)     // Catch:{ IOException -> 0x0132 }
            if (r4 != 0) goto L_0x0110
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x010b }
            java.lang.String r5 = "Failed to redirect: %d"
            r4.<init>(r5)     // Catch:{ IOException -> 0x010b }
            r4.append(r0)     // Catch:{ IOException -> 0x010b }
            java.lang.String r0 = r4.toString()     // Catch:{ IOException -> 0x010b }
            r4 = 0
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x010b }
            com.tencent.bugly.proguard.x.e(r0, r5)     // Catch:{ IOException -> 0x010b }
            r12.disconnect()     // Catch:{ all -> 0x00fe }
        L_0x00fc:
            r2 = 0
            goto L_0x010a
        L_0x00fe:
            r0 = move-exception
            r2 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r2)
            if (r0 != 0) goto L_0x00fc
            r2.printStackTrace()
            goto L_0x00fc
        L_0x010a:
            return r2
        L_0x010b:
            r0 = move-exception
            r7 = 2
            r14 = 1
            goto L_0x0171
        L_0x0110:
            int r13 = r13 + 1
            java.lang.String r5 = "redirect code: %d ,to:%s"
            r7 = 2
            java.lang.Object[] r6 = new java.lang.Object[r7]     // Catch:{ IOException -> 0x012a }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r0)     // Catch:{ IOException -> 0x012a }
            r14 = 0
            r6[r14] = r10     // Catch:{ IOException -> 0x012a }
            r15 = 1
            r6[r15] = r4     // Catch:{ IOException -> 0x0128 }
            com.tencent.bugly.proguard.x.c(r5, r6)     // Catch:{ IOException -> 0x0128 }
            r10 = r4
            r6 = 0
            r14 = 1
            goto L_0x0139
        L_0x0128:
            r0 = move-exception
            goto L_0x012f
        L_0x012a:
            r0 = move-exception
            goto L_0x012e
        L_0x012c:
            r0 = move-exception
            r7 = 2
        L_0x012e:
            r15 = 1
        L_0x012f:
            r10 = r4
            r6 = 0
            goto L_0x0135
        L_0x0132:
            r0 = move-exception
            r7 = 2
            r15 = 1
        L_0x0135:
            r14 = 1
            goto L_0x0172
        L_0x0137:
            r7 = 2
            r15 = 1
        L_0x0139:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x016a }
            java.lang.String r5 = "response code "
            r4.<init>(r5)     // Catch:{ IOException -> 0x016a }
            r4.append(r0)     // Catch:{ IOException -> 0x016a }
            java.lang.String r0 = r4.toString()     // Catch:{ IOException -> 0x016a }
            r4 = 0
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x016a }
            com.tencent.bugly.proguard.x.d(r0, r5)     // Catch:{ IOException -> 0x016a }
            int r0 = r12.getContentLength()     // Catch:{ IOException -> 0x016a }
            long r4 = (long) r0     // Catch:{ IOException -> 0x016a }
            r16 = 0
            int r0 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r0 >= 0) goto L_0x015a
            r4 = 0
        L_0x015a:
            r3.b(r4)     // Catch:{ IOException -> 0x016a }
            r12.disconnect()     // Catch:{ all -> 0x0161 }
            goto L_0x018a
        L_0x0161:
            r0 = move-exception
            r4 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r4)
            if (r0 != 0) goto L_0x018a
            goto L_0x0187
        L_0x016a:
            r0 = move-exception
            goto L_0x0172
        L_0x016c:
            r0 = move-exception
            r2 = r0
            goto L_0x018e
        L_0x016f:
            r0 = move-exception
            r7 = 2
        L_0x0171:
            r15 = 1
        L_0x0172:
            boolean r4 = com.tencent.bugly.proguard.x.a(r0)     // Catch:{ all -> 0x016c }
            if (r4 != 0) goto L_0x017b
            r0.printStackTrace()     // Catch:{ all -> 0x016c }
        L_0x017b:
            r12.disconnect()     // Catch:{ all -> 0x017f }
            goto L_0x018a
        L_0x017f:
            r0 = move-exception
            r4 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r4)
            if (r0 != 0) goto L_0x018a
        L_0x0187:
            r4.printStackTrace()
        L_0x018a:
            r0 = r6
            r4 = 0
            goto L_0x01ae
        L_0x018e:
            r12.disconnect()     // Catch:{ all -> 0x0192 }
            goto L_0x019d
        L_0x0192:
            r0 = move-exception
            r3 = r0
            boolean r0 = com.tencent.bugly.proguard.x.a(r3)
            if (r0 != 0) goto L_0x019d
            r3.printStackTrace()
        L_0x019d:
            throw r2
        L_0x019e:
            r4 = 0
            r7 = 2
            r15 = 1
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r5 = "Failed to execute post."
            com.tencent.bugly.proguard.x.c(r5, r0)
            r4 = 0
            r3.b(r4)
            r0 = r6
        L_0x01ae:
            r4 = 0
            r5 = 0
            goto L_0x008d
        L_0x01b2:
            r2 = r4
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.s.a(java.lang.String, byte[], com.tencent.bugly.proguard.v, java.util.Map):byte[]");
    }

    private static Map<String, String> a(HttpURLConnection httpURLConnection) {
        HashMap hashMap = new HashMap();
        Map headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null || headerFields.size() == 0) {
            return null;
        }
        for (String str : headerFields.keySet()) {
            List list = (List) headerFields.get(str);
            if (list.size() > 0) {
                hashMap.put(str, list.get(0));
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x003b A[Catch:{ all -> 0x0049, all -> 0x0050 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0040 A[SYNTHETIC, Splitter:B:24:0x0040] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] b(java.net.HttpURLConnection r5) {
        /*
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0033 }
            java.io.InputStream r5 = r5.getInputStream()     // Catch:{ all -> 0x0033 }
            r1.<init>(r5)     // Catch:{ all -> 0x0033 }
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0031 }
            r5.<init>()     // Catch:{ all -> 0x0031 }
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch:{ all -> 0x0031 }
        L_0x0016:
            int r3 = r1.read(r2)     // Catch:{ all -> 0x0031 }
            if (r3 <= 0) goto L_0x0021
            r4 = 0
            r5.write(r2, r4, r3)     // Catch:{ all -> 0x0031 }
            goto L_0x0016
        L_0x0021:
            r5.flush()     // Catch:{ all -> 0x0031 }
            byte[] r5 = r5.toByteArray()     // Catch:{ all -> 0x0031 }
            r1.close()     // Catch:{ all -> 0x002c }
            goto L_0x0030
        L_0x002c:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0030:
            return r5
        L_0x0031:
            r5 = move-exception
            goto L_0x0035
        L_0x0033:
            r5 = move-exception
            r1 = r0
        L_0x0035:
            boolean r2 = com.tencent.bugly.proguard.x.a(r5)     // Catch:{ all -> 0x0049 }
            if (r2 != 0) goto L_0x003e
            r5.printStackTrace()     // Catch:{ all -> 0x0049 }
        L_0x003e:
            if (r1 == 0) goto L_0x0048
            r1.close()     // Catch:{ all -> 0x0044 }
            goto L_0x0048
        L_0x0044:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0048:
            return r0
        L_0x0049:
            r5 = move-exception
            if (r1 == 0) goto L_0x0054
            r1.close()     // Catch:{ all -> 0x0050 }
            goto L_0x0054
        L_0x0050:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0054:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.s.b(java.net.HttpURLConnection):byte[]");
    }

    private HttpURLConnection a(String str, byte[] bArr, String str2, Map<String, String> map) {
        if (str == null) {
            x.e("destUrl is null.", new Object[0]);
            return null;
        }
        TrustManager[] trustManagerArr = {new X509TrustManager() {
            public final X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                x.c("checkClientTrusted", new Object[0]);
            }

            public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                x.c("checkServerTrusted", new Object[0]);
            }
        }};
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init((KeyManager[]) null, trustManagerArr, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpURLConnection a2 = a(str2, str);
        if (a2 == null) {
            x.e("Failed to get HttpURLConnection object.", new Object[0]);
            return null;
        }
        try {
            a2.setRequestProperty("wup_version", "3.0");
            if (map != null) {
                if (map.size() > 0) {
                    for (Map.Entry next : map.entrySet()) {
                        a2.setRequestProperty((String) next.getKey(), URLEncoder.encode((String) next.getValue(), "utf-8"));
                    }
                }
            }
            a2.setRequestProperty("A37", URLEncoder.encode(str2, "utf-8"));
            a2.setRequestProperty("A38", URLEncoder.encode(str2, "utf-8"));
            OutputStream outputStream = a2.getOutputStream();
            if (bArr == null) {
                outputStream.write(0);
            } else {
                outputStream.write(bArr);
            }
            return a2;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            x.e("Failed to upload, please check your network.", new Object[0]);
            return null;
        }
    }

    private static HttpURLConnection a(String str, String str2) {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(str2);
            if (a.b() != null) {
                httpURLConnection = (HttpURLConnection) url.openConnection(a.b());
            } else if (str == null || !str.toLowerCase(Locale.US).contains("wap")) {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(System.getProperty("http.proxyHost"), Integer.parseInt(System.getProperty("http.proxyPort")))));
            }
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(false);
            return httpURLConnection;
        } catch (Throwable th) {
            if (x.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
