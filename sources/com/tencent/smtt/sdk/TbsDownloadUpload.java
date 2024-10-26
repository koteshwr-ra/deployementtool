package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TbsDownloadUpload {
    private static TbsDownloadUpload b;
    Map<String, Object> a = new HashMap();
    private Context c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    public SharedPreferences mPreferences;

    public interface TbsUploadKey {
        public static final String KEY_LOCAL_CORE_VERSION = "tbs_local_core_version";
        public static final String KEY_NEEDDOWNLOAD_CODE = "tbs_needdownload_code";
        public static final String KEY_NEEDDOWNLOAD_RETURN = "tbs_needdownload_return";
        public static final String KEY_NEEDDOWNLOAD_SENT = "tbs_needdownload_sent";
        public static final String KEY_STARTDOWNLOAD_CODE = "tbs_startdownload_code";
        public static final String KEY_STARTDOWNLOAD_SENT = "tbs_startdownload_sent";
    }

    public TbsDownloadUpload(Context context) {
        this.mPreferences = context.getSharedPreferences("tbs_download_upload", 4);
        Context applicationContext = context.getApplicationContext();
        this.c = applicationContext;
        if (applicationContext == null) {
            this.c = context;
        }
    }

    private static File a(Context context, String str) {
        o.a();
        File t = o.t(context);
        if (t == null) {
            return null;
        }
        File file = new File(t, str);
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

    public static synchronized void clear() {
        synchronized (TbsDownloadUpload.class) {
            b = null;
        }
    }

    public static synchronized TbsDownloadUpload getInstance() {
        TbsDownloadUpload tbsDownloadUpload;
        synchronized (TbsDownloadUpload.class) {
            tbsDownloadUpload = b;
        }
        return tbsDownloadUpload;
    }

    public static synchronized TbsDownloadUpload getInstance(Context context) {
        TbsDownloadUpload tbsDownloadUpload;
        synchronized (TbsDownloadUpload.class) {
            if (b == null) {
                b = new TbsDownloadUpload(context);
            }
            tbsDownloadUpload = b;
        }
        return tbsDownloadUpload;
    }

    public void clearUploadCode() {
        this.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_CODE, 0);
        this.a.put(TbsUploadKey.KEY_STARTDOWNLOAD_CODE, 0);
        this.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_RETURN, 0);
        this.a.put(TbsUploadKey.KEY_NEEDDOWNLOAD_SENT, 0);
        this.a.put(TbsUploadKey.KEY_STARTDOWNLOAD_SENT, 0);
        this.a.put(TbsUploadKey.KEY_LOCAL_CORE_VERSION, 0);
        writeTbsDownloadInfo();
    }

    public synchronized void commit() {
        writeTbsDownloadInfo();
    }

    public synchronized int getLocalCoreVersion() {
        return this.i;
    }

    public synchronized int getNeedDownloadCode() {
        if (this.g == 1) {
            return 148;
        }
        return this.d;
    }

    public synchronized int getNeedDownloadReturn() {
        return this.f;
    }

    public synchronized int getStartDownloadCode() {
        if (this.h == 1) {
            return 168;
        }
        return this.e;
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d1 A[SYNTHETIC, Splitter:B:39:0x00d1] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void readTbsDownloadInfo(android.content.Context r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            r6 = 0
            android.content.Context r0 = r5.c     // Catch:{ all -> 0x00c8 }
            java.lang.String r1 = "download_upload"
            java.io.File r0 = a(r0, r1)     // Catch:{ all -> 0x00c8 }
            if (r0 != 0) goto L_0x000e
            monitor-exit(r5)
            return
        L_0x000e:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x00c8 }
            r1.<init>(r0)     // Catch:{ all -> 0x00c8 }
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ all -> 0x00c8 }
            r0.<init>(r1)     // Catch:{ all -> 0x00c8 }
            java.util.Properties r6 = new java.util.Properties     // Catch:{ all -> 0x00c6 }
            r6.<init>()     // Catch:{ all -> 0x00c6 }
            r6.load(r0)     // Catch:{ all -> 0x00c6 }
            java.lang.String r1 = "tbs_needdownload_code"
            java.lang.String r2 = ""
            java.lang.String r1 = r6.getProperty(r1, r2)     // Catch:{ all -> 0x00c6 }
            java.lang.String r2 = ""
            boolean r2 = r2.equals(r1)     // Catch:{ all -> 0x00c6 }
            r3 = 0
            if (r2 != 0) goto L_0x003b
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ all -> 0x00c6 }
            int r1 = java.lang.Math.max(r1, r3)     // Catch:{ all -> 0x00c6 }
            r5.d = r1     // Catch:{ all -> 0x00c6 }
        L_0x003b:
            java.lang.String r1 = "tbs_startdownload_code"
            java.lang.String r2 = ""
            java.lang.String r1 = r6.getProperty(r1, r2)     // Catch:{ all -> 0x00c6 }
            java.lang.String r2 = ""
            boolean r2 = r2.equals(r1)     // Catch:{ all -> 0x00c6 }
            if (r2 != 0) goto L_0x0055
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ all -> 0x00c6 }
            int r1 = java.lang.Math.max(r1, r3)     // Catch:{ all -> 0x00c6 }
            r5.e = r1     // Catch:{ all -> 0x00c6 }
        L_0x0055:
            java.lang.String r1 = "tbs_needdownload_return"
            java.lang.String r2 = ""
            java.lang.String r1 = r6.getProperty(r1, r2)     // Catch:{ all -> 0x00c6 }
            java.lang.String r2 = ""
            boolean r2 = r2.equals(r1)     // Catch:{ all -> 0x00c6 }
            if (r2 != 0) goto L_0x006f
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ all -> 0x00c6 }
            int r1 = java.lang.Math.max(r1, r3)     // Catch:{ all -> 0x00c6 }
            r5.f = r1     // Catch:{ all -> 0x00c6 }
        L_0x006f:
            java.lang.String r1 = "tbs_needdownload_sent"
            java.lang.String r2 = ""
            java.lang.String r1 = r6.getProperty(r1, r2)     // Catch:{ all -> 0x00c6 }
            java.lang.String r2 = ""
            boolean r2 = r2.equals(r1)     // Catch:{ all -> 0x00c6 }
            if (r2 != 0) goto L_0x0089
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ all -> 0x00c6 }
            int r1 = java.lang.Math.max(r1, r3)     // Catch:{ all -> 0x00c6 }
            r5.g = r1     // Catch:{ all -> 0x00c6 }
        L_0x0089:
            java.lang.String r1 = "tbs_startdownload_sent"
            java.lang.String r2 = ""
            java.lang.String r1 = r6.getProperty(r1, r2)     // Catch:{ all -> 0x00c6 }
            java.lang.String r2 = ""
            boolean r2 = r2.equals(r1)     // Catch:{ all -> 0x00c6 }
            if (r2 != 0) goto L_0x00a3
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ all -> 0x00c6 }
            int r1 = java.lang.Math.max(r1, r3)     // Catch:{ all -> 0x00c6 }
            r5.h = r1     // Catch:{ all -> 0x00c6 }
        L_0x00a3:
            java.lang.String r1 = "tbs_local_core_version"
            java.lang.String r2 = ""
            java.lang.String r6 = r6.getProperty(r1, r2)     // Catch:{ all -> 0x00c6 }
            java.lang.String r1 = ""
            boolean r1 = r1.equals(r6)     // Catch:{ all -> 0x00c6 }
            if (r1 != 0) goto L_0x00bd
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ all -> 0x00c6 }
            int r6 = java.lang.Math.max(r6, r3)     // Catch:{ all -> 0x00c6 }
            r5.i = r6     // Catch:{ all -> 0x00c6 }
        L_0x00bd:
            r0.close()     // Catch:{ Exception -> 0x00c1 }
            goto L_0x00d9
        L_0x00c1:
            r6 = move-exception
        L_0x00c2:
            r6.printStackTrace()     // Catch:{ all -> 0x00d5 }
            goto L_0x00d9
        L_0x00c6:
            r6 = move-exception
            goto L_0x00cc
        L_0x00c8:
            r0 = move-exception
            r4 = r0
            r0 = r6
            r6 = r4
        L_0x00cc:
            r6.printStackTrace()     // Catch:{ all -> 0x00db }
            if (r0 == 0) goto L_0x00d9
            r0.close()     // Catch:{ Exception -> 0x00d7 }
            goto L_0x00d9
        L_0x00d5:
            r6 = move-exception
            goto L_0x00e7
        L_0x00d7:
            r6 = move-exception
            goto L_0x00c2
        L_0x00d9:
            monitor-exit(r5)
            return
        L_0x00db:
            r6 = move-exception
            if (r0 == 0) goto L_0x00e6
            r0.close()     // Catch:{ Exception -> 0x00e2 }
            goto L_0x00e6
        L_0x00e2:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x00d5 }
        L_0x00e6:
            throw r6     // Catch:{ all -> 0x00d5 }
        L_0x00e7:
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloadUpload.readTbsDownloadInfo(android.content.Context):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00bb, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00bc, code lost:
        if (r0 != null) goto L_0x00be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00c2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00c6, code lost:
        if (r1 != null) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00cc, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00d0, code lost:
        throw r2;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:20:0x0088, B:37:0x00a4] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a9 A[SYNTHETIC, Splitter:B:40:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b3 A[SYNTHETIC, Splitter:B:46:0x00b3] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void writeTbsDownloadInfo() {
        /*
            r10 = this;
            monitor-enter(r10)
            java.lang.String r0 = "TbsDownloadUpload"
            java.lang.String r1 = "writeTbsDownloadInfo #1"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch:{ all -> 0x00d1 }
            r0 = 0
            android.content.Context r1 = r10.c     // Catch:{ all -> 0x00a1 }
            java.lang.String r2 = "download_upload"
            java.io.File r1 = a(r1, r2)     // Catch:{ all -> 0x00a1 }
            if (r1 != 0) goto L_0x0015
            monitor-exit(r10)
            return
        L_0x0015:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x00a1 }
            r2.<init>(r1)     // Catch:{ all -> 0x00a1 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ all -> 0x00a1 }
            r3.<init>(r2)     // Catch:{ all -> 0x00a1 }
            java.util.Properties r2 = new java.util.Properties     // Catch:{ all -> 0x009c }
            r2.<init>()     // Catch:{ all -> 0x009c }
            r2.load(r3)     // Catch:{ all -> 0x009c }
            java.util.Map<java.lang.String, java.lang.Object> r4 = r10.a     // Catch:{ all -> 0x009c }
            java.util.Set r4 = r4.keySet()     // Catch:{ all -> 0x009c }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x009c }
        L_0x0031:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x009c }
            if (r5 == 0) goto L_0x0076
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x009c }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x009c }
            java.util.Map<java.lang.String, java.lang.Object> r6 = r10.a     // Catch:{ all -> 0x009c }
            java.lang.Object r6 = r6.get(r5)     // Catch:{ all -> 0x009c }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x009c }
            r7.<init>()     // Catch:{ all -> 0x009c }
            java.lang.String r8 = ""
            r7.append(r8)     // Catch:{ all -> 0x009c }
            r7.append(r6)     // Catch:{ all -> 0x009c }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x009c }
            r2.setProperty(r5, r7)     // Catch:{ all -> 0x009c }
            java.lang.String r7 = "TbsDownloadUpload"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x009c }
            r8.<init>()     // Catch:{ all -> 0x009c }
            java.lang.String r9 = "writeTbsDownloadInfo key is "
            r8.append(r9)     // Catch:{ all -> 0x009c }
            r8.append(r5)     // Catch:{ all -> 0x009c }
            java.lang.String r5 = " value is "
            r8.append(r5)     // Catch:{ all -> 0x009c }
            r8.append(r6)     // Catch:{ all -> 0x009c }
            java.lang.String r5 = r8.toString()     // Catch:{ all -> 0x009c }
            com.tencent.smtt.utils.TbsLog.i(r7, r5)     // Catch:{ all -> 0x009c }
            goto L_0x0031
        L_0x0076:
            java.util.Map<java.lang.String, java.lang.Object> r4 = r10.a     // Catch:{ all -> 0x009c }
            r4.clear()     // Catch:{ all -> 0x009c }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ all -> 0x009c }
            r4.<init>(r1)     // Catch:{ all -> 0x009c }
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x009c }
            r1.<init>(r4)     // Catch:{ all -> 0x009c }
            r2.store(r1, r0)     // Catch:{ all -> 0x0099 }
            r3.close()     // Catch:{ Exception -> 0x008c }
            goto L_0x0090
        L_0x008c:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x00d1 }
        L_0x0090:
            r1.close()     // Catch:{ Exception -> 0x0094 }
            goto L_0x00b9
        L_0x0094:
            r0 = move-exception
        L_0x0095:
            r0.printStackTrace()     // Catch:{ all -> 0x00d1 }
            goto L_0x00b9
        L_0x0099:
            r0 = move-exception
            r2 = r0
            goto L_0x009f
        L_0x009c:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L_0x009f:
            r0 = r3
            goto L_0x00a4
        L_0x00a1:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L_0x00a4:
            r2.printStackTrace()     // Catch:{ all -> 0x00bb }
            if (r0 == 0) goto L_0x00b1
            r0.close()     // Catch:{ Exception -> 0x00ad }
            goto L_0x00b1
        L_0x00ad:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x00d1 }
        L_0x00b1:
            if (r1 == 0) goto L_0x00b9
            r1.close()     // Catch:{ Exception -> 0x00b7 }
            goto L_0x00b9
        L_0x00b7:
            r0 = move-exception
            goto L_0x0095
        L_0x00b9:
            monitor-exit(r10)
            return
        L_0x00bb:
            r2 = move-exception
            if (r0 == 0) goto L_0x00c6
            r0.close()     // Catch:{ Exception -> 0x00c2 }
            goto L_0x00c6
        L_0x00c2:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x00d1 }
        L_0x00c6:
            if (r1 == 0) goto L_0x00d0
            r1.close()     // Catch:{ Exception -> 0x00cc }
            goto L_0x00d0
        L_0x00cc:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x00d1 }
        L_0x00d0:
            throw r2     // Catch:{ all -> 0x00d1 }
        L_0x00d1:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloadUpload.writeTbsDownloadInfo():void");
    }
}
