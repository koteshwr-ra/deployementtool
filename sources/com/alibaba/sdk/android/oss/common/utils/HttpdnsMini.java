package com.alibaba.sdk.android.oss.common.utils;

import com.alibaba.sdk.android.oss.common.OSSLog;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpdnsMini {
    private static final String ACCOUNT_ID = "181345";
    private static final int EMPTY_RESULT_HOST_TTL = 30;
    private static final int MAX_HOLD_HOST_NUM = 100;
    private static final int MAX_THREAD_NUM = 5;
    private static final int RESOLVE_TIMEOUT_IN_SEC = 10;
    private static final String SERVER_IP = "203.107.1.1";
    private static final String TAG = "HttpDnsMini";
    private static HttpdnsMini instance;
    /* access modifiers changed from: private */
    public ConcurrentMap<String, HostObject> hostManager = new ConcurrentHashMap();
    private ExecutorService pool = Executors.newFixedThreadPool(5);

    private HttpdnsMini() {
    }

    public static HttpdnsMini getInstance() {
        if (instance == null) {
            synchronized (HttpdnsMini.class) {
                if (instance == null) {
                    instance = new HttpdnsMini();
                }
            }
        }
        return instance;
    }

    public String getIpByHostAsync(String str) {
        HostObject hostObject = (HostObject) this.hostManager.get(str);
        if (hostObject == null || hostObject.isExpired()) {
            OSSLog.logDebug("[httpdnsmini] - refresh host: " + str);
            this.pool.submit(new QueryHostTask(str));
        }
        if (hostObject == null || !hostObject.isStillAvailable()) {
            return null;
        }
        return hostObject.getIp();
    }

    class HostObject {
        private String hostName;
        private String ip;
        private long queryTime;
        private long ttl;

        HostObject() {
        }

        public String toString() {
            return "[hostName=" + getHostName() + ", ip=" + this.ip + ", ttl=" + getTtl() + ", queryTime=" + this.queryTime + "]";
        }

        public boolean isExpired() {
            return getQueryTime() + this.ttl < System.currentTimeMillis() / 1000;
        }

        public boolean isStillAvailable() {
            return (getQueryTime() + this.ttl) + 600 > System.currentTimeMillis() / 1000;
        }

        public String getIp() {
            return this.ip;
        }

        public void setIp(String str) {
            this.ip = str;
        }

        public String getHostName() {
            return this.hostName;
        }

        public void setHostName(String str) {
            this.hostName = str;
        }

        public long getTtl() {
            return this.ttl;
        }

        public void setTtl(long j) {
            this.ttl = j;
        }

        public long getQueryTime() {
            return this.queryTime;
        }

        public void setQueryTime(long j) {
            this.queryTime = j;
        }
    }

    class QueryHostTask implements Callable<String> {
        private boolean hasRetryed = false;
        private String hostName;

        public QueryHostTask(String str) {
            this.hostName = str;
        }

        /* JADX WARNING: Removed duplicated region for block: B:45:0x014f A[Catch:{ all -> 0x016c }] */
        /* JADX WARNING: Removed duplicated region for block: B:47:0x0157 A[SYNTHETIC, Splitter:B:47:0x0157] */
        /* JADX WARNING: Removed duplicated region for block: B:53:0x0163  */
        /* JADX WARNING: Removed duplicated region for block: B:55:0x016b A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:59:0x0170 A[SYNTHETIC, Splitter:B:59:0x0170] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String call() {
            /*
                r10 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "https://"
                r0.append(r1)
                java.lang.String r1 = "203.107.1.1"
                r0.append(r1)
                java.lang.String r1 = "/"
                r0.append(r1)
                java.lang.String r1 = "181345"
                r0.append(r1)
                java.lang.String r1 = "/d?host="
                r0.append(r1)
                java.lang.String r1 = r10.hostName
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "[httpdnsmini] - buildUrl: "
                r1.append(r2)
                r1.append(r0)
                java.lang.String r1 = r1.toString()
                com.alibaba.sdk.android.oss.common.OSSLog.logDebug(r1)
                r1 = 0
                java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
                r2.<init>(r0)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
                java.net.URLConnection r0 = r2.openConnection()     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
                java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
                r2 = 10000(0x2710, float:1.4013E-41)
                r0.setConnectTimeout(r2)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
                r0.setReadTimeout(r2)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
                int r2 = r0.getResponseCode()     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
                r3 = 200(0xc8, float:2.8E-43)
                if (r2 == r3) goto L_0x0072
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
                r2.<init>()     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
                java.lang.String r3 = "[httpdnsmini] - responseCodeNot 200, but: "
                r2.append(r3)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
                int r0 = r0.getResponseCode()     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
                r2.append(r0)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
                java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
                com.alibaba.sdk.android.oss.common.OSSLog.logError(r0)     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
                r0 = r1
                goto L_0x0135
            L_0x0072:
                java.io.InputStream r0 = r0.getInputStream()     // Catch:{ Exception -> 0x0147, all -> 0x0145 }
                java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.lang.String r4 = "UTF-8"
                r3.<init>(r0, r4)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                r2.<init>(r3)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                r3.<init>()     // Catch:{ Exception -> 0x0140, all -> 0x013b }
            L_0x0087:
                java.lang.String r4 = r2.readLine()     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                if (r4 == 0) goto L_0x0091
                r3.append(r4)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                goto L_0x0087
            L_0x0091:
                org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                r2.<init>(r3)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.lang.String r3 = "host"
                java.lang.String r3 = r2.getString(r3)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.lang.String r4 = "ttl"
                long r4 = r2.getLong(r4)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.lang.String r6 = "ips"
                org.json.JSONArray r2 = r2.getJSONArray(r6)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                r6.<init>()     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.lang.String r7 = "[httpdnsmini] - ips:"
                r6.append(r7)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.lang.String r7 = r2.toString()     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                r6.append(r7)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                com.alibaba.sdk.android.oss.common.OSSLog.logDebug(r6)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                if (r3 == 0) goto L_0x0135
                if (r2 == 0) goto L_0x0135
                int r6 = r2.length()     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                if (r6 <= 0) goto L_0x0135
                r6 = 0
                int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r8 != 0) goto L_0x00d6
                r4 = 30
            L_0x00d6:
                com.alibaba.sdk.android.oss.common.utils.HttpdnsMini$HostObject r6 = new com.alibaba.sdk.android.oss.common.utils.HttpdnsMini$HostObject     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                com.alibaba.sdk.android.oss.common.utils.HttpdnsMini r7 = com.alibaba.sdk.android.oss.common.utils.HttpdnsMini.this     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                r6.<init>()     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                if (r2 != 0) goto L_0x00e1
                r2 = r1
                goto L_0x00e6
            L_0x00e1:
                r7 = 0
                java.lang.String r2 = r2.getString(r7)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
            L_0x00e6:
                r6.setHostName(r3)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                r6.setTtl(r4)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                r6.setIp(r2)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                r7 = 1000(0x3e8, double:4.94E-321)
                long r3 = r3 / r7
                r6.setQueryTime(r3)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                r3.<init>()     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.lang.String r4 = "[httpdnsmini] - resolve result:"
                r3.append(r4)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.lang.String r4 = r6.toString()     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                r3.append(r4)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                com.alibaba.sdk.android.oss.common.OSSLog.logDebug(r3)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                com.alibaba.sdk.android.oss.common.utils.HttpdnsMini r3 = com.alibaba.sdk.android.oss.common.utils.HttpdnsMini.this     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.util.concurrent.ConcurrentMap r3 = r3.hostManager     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                int r3 = r3.size()     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                r4 = 100
                if (r3 >= r4) goto L_0x012a
                com.alibaba.sdk.android.oss.common.utils.HttpdnsMini r3 = com.alibaba.sdk.android.oss.common.utils.HttpdnsMini.this     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.util.concurrent.ConcurrentMap r3 = r3.hostManager     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                java.lang.String r4 = r10.hostName     // Catch:{ Exception -> 0x0140, all -> 0x013b }
                r3.put(r4, r6)     // Catch:{ Exception -> 0x0140, all -> 0x013b }
            L_0x012a:
                if (r0 == 0) goto L_0x0134
                r0.close()     // Catch:{ IOException -> 0x0130 }
                goto L_0x0134
            L_0x0130:
                r0 = move-exception
                r0.printStackTrace()
            L_0x0134:
                return r2
            L_0x0135:
                if (r0 == 0) goto L_0x015f
                r0.close()     // Catch:{ IOException -> 0x015b }
                goto L_0x015f
            L_0x013b:
                r1 = move-exception
                r9 = r1
                r1 = r0
                r0 = r9
                goto L_0x016e
            L_0x0140:
                r2 = move-exception
                r9 = r2
                r2 = r0
                r0 = r9
                goto L_0x0149
            L_0x0145:
                r0 = move-exception
                goto L_0x016e
            L_0x0147:
                r0 = move-exception
                r2 = r1
            L_0x0149:
                boolean r3 = com.alibaba.sdk.android.oss.common.OSSLog.isEnableLog()     // Catch:{ all -> 0x016c }
                if (r3 == 0) goto L_0x0155
                r0.printStackTrace()     // Catch:{ all -> 0x016c }
                com.alibaba.sdk.android.oss.common.OSSLog.logThrowable2Local(r0)     // Catch:{ all -> 0x016c }
            L_0x0155:
                if (r2 == 0) goto L_0x015f
                r2.close()     // Catch:{ IOException -> 0x015b }
                goto L_0x015f
            L_0x015b:
                r0 = move-exception
                r0.printStackTrace()
            L_0x015f:
                boolean r0 = r10.hasRetryed
                if (r0 != 0) goto L_0x016b
                r0 = 1
                r10.hasRetryed = r0
                java.lang.String r0 = r10.call()
                return r0
            L_0x016b:
                return r1
            L_0x016c:
                r0 = move-exception
                r1 = r2
            L_0x016e:
                if (r1 == 0) goto L_0x0178
                r1.close()     // Catch:{ IOException -> 0x0174 }
                goto L_0x0178
            L_0x0174:
                r1 = move-exception
                r1.printStackTrace()
            L_0x0178:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.common.utils.HttpdnsMini.QueryHostTask.call():java.lang.String");
        }
    }
}
