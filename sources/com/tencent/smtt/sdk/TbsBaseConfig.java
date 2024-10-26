package com.tencent.smtt.sdk;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class TbsBaseConfig {
    public static final String TAG = "TbsBaseConfig";
    Map<String, String> a;
    private Context b;

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
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void clear() {
        this.a.clear();
        commit();
    }

    public synchronized void commit() {
        writeTbsDownloadInfo();
    }

    public abstract String getConfigFileName();

    public void init(Context context) {
        this.a = new HashMap();
        Context applicationContext = context.getApplicationContext();
        this.b = applicationContext;
        if (applicationContext == null) {
            this.b = context;
        }
        refreshSyncMap(context);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0059 A[SYNTHETIC, Splitter:B:25:0x0059] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void refreshSyncMap(android.content.Context r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            r7 = 0
            android.content.Context r0 = r6.b     // Catch:{ all -> 0x0050 }
            java.lang.String r1 = r6.getConfigFileName()     // Catch:{ all -> 0x0050 }
            java.io.File r0 = a(r0, r1)     // Catch:{ all -> 0x0050 }
            if (r0 != 0) goto L_0x0010
            monitor-exit(r6)
            return
        L_0x0010:
            java.util.Map<java.lang.String, java.lang.String> r1 = r6.a     // Catch:{ all -> 0x0050 }
            r1.clear()     // Catch:{ all -> 0x0050 }
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0050 }
            r1.<init>(r0)     // Catch:{ all -> 0x0050 }
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0050 }
            r0.<init>(r1)     // Catch:{ all -> 0x0050 }
            java.util.Properties r7 = new java.util.Properties     // Catch:{ all -> 0x004e }
            r7.<init>()     // Catch:{ all -> 0x004e }
            r7.load(r0)     // Catch:{ all -> 0x004e }
            java.util.Set r1 = r7.stringPropertyNames()     // Catch:{ all -> 0x004e }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x004e }
        L_0x002f:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x004e }
            if (r2 == 0) goto L_0x0045
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x004e }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x004e }
            java.util.Map<java.lang.String, java.lang.String> r3 = r6.a     // Catch:{ all -> 0x004e }
            java.lang.String r4 = r7.getProperty(r2)     // Catch:{ all -> 0x004e }
            r3.put(r2, r4)     // Catch:{ all -> 0x004e }
            goto L_0x002f
        L_0x0045:
            r0.close()     // Catch:{ Exception -> 0x0049 }
            goto L_0x0061
        L_0x0049:
            r7 = move-exception
        L_0x004a:
            r7.printStackTrace()     // Catch:{ all -> 0x005d }
            goto L_0x0061
        L_0x004e:
            r7 = move-exception
            goto L_0x0054
        L_0x0050:
            r0 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
        L_0x0054:
            r7.printStackTrace()     // Catch:{ all -> 0x0063 }
            if (r0 == 0) goto L_0x0061
            r0.close()     // Catch:{ Exception -> 0x005f }
            goto L_0x0061
        L_0x005d:
            r7 = move-exception
            goto L_0x006f
        L_0x005f:
            r7 = move-exception
            goto L_0x004a
        L_0x0061:
            monitor-exit(r6)
            return
        L_0x0063:
            r7 = move-exception
            if (r0 == 0) goto L_0x006e
            r0.close()     // Catch:{ Exception -> 0x006a }
            goto L_0x006e
        L_0x006a:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x005d }
        L_0x006e:
            throw r7     // Catch:{ all -> 0x005d }
        L_0x006f:
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsBaseConfig.refreshSyncMap(android.content.Context):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0091, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c0, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00c1, code lost:
        if (r0 != null) goto L_0x00c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00c7, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00cb, code lost:
        if (r1 != null) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00d1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00d5, code lost:
        throw r2;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:20:0x008d, B:37:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ae A[SYNTHETIC, Splitter:B:40:0x00ae] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b8 A[SYNTHETIC, Splitter:B:46:0x00b8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void writeTbsDownloadInfo() {
        /*
            r10 = this;
            monitor-enter(r10)
            java.lang.String r0 = "TbsBaseConfig"
            java.lang.String r1 = "writeTbsDownloadInfo #1"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch:{ all -> 0x00d6 }
            r0 = 0
            android.content.Context r1 = r10.b     // Catch:{ all -> 0x00a6 }
            java.lang.String r2 = r10.getConfigFileName()     // Catch:{ all -> 0x00a6 }
            java.io.File r1 = a(r1, r2)     // Catch:{ all -> 0x00a6 }
            if (r1 != 0) goto L_0x0017
            monitor-exit(r10)
            return
        L_0x0017:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ all -> 0x00a6 }
            r2.<init>(r1)     // Catch:{ all -> 0x00a6 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ all -> 0x00a6 }
            r3.<init>(r2)     // Catch:{ all -> 0x00a6 }
            java.util.Properties r2 = new java.util.Properties     // Catch:{ all -> 0x00a1 }
            r2.<init>()     // Catch:{ all -> 0x00a1 }
            r2.load(r3)     // Catch:{ all -> 0x00a1 }
            r2.clear()     // Catch:{ all -> 0x00a1 }
            java.util.Map<java.lang.String, java.lang.String> r4 = r10.a     // Catch:{ all -> 0x00a1 }
            java.util.Set r4 = r4.keySet()     // Catch:{ all -> 0x00a1 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x00a1 }
        L_0x0036:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x00a1 }
            if (r5 == 0) goto L_0x007b
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x00a1 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x00a1 }
            java.util.Map<java.lang.String, java.lang.String> r6 = r10.a     // Catch:{ all -> 0x00a1 }
            java.lang.Object r6 = r6.get(r5)     // Catch:{ all -> 0x00a1 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a1 }
            r7.<init>()     // Catch:{ all -> 0x00a1 }
            java.lang.String r8 = ""
            r7.append(r8)     // Catch:{ all -> 0x00a1 }
            r7.append(r6)     // Catch:{ all -> 0x00a1 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x00a1 }
            r2.setProperty(r5, r7)     // Catch:{ all -> 0x00a1 }
            java.lang.String r7 = "TbsBaseConfig"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a1 }
            r8.<init>()     // Catch:{ all -> 0x00a1 }
            java.lang.String r9 = "writeTbsDownloadInfo key is "
            r8.append(r9)     // Catch:{ all -> 0x00a1 }
            r8.append(r5)     // Catch:{ all -> 0x00a1 }
            java.lang.String r5 = " value is "
            r8.append(r5)     // Catch:{ all -> 0x00a1 }
            r8.append(r6)     // Catch:{ all -> 0x00a1 }
            java.lang.String r5 = r8.toString()     // Catch:{ all -> 0x00a1 }
            com.tencent.smtt.utils.TbsLog.i(r7, r5)     // Catch:{ all -> 0x00a1 }
            goto L_0x0036
        L_0x007b:
            java.util.Map<java.lang.String, java.lang.String> r4 = r10.a     // Catch:{ all -> 0x00a1 }
            r4.clear()     // Catch:{ all -> 0x00a1 }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ all -> 0x00a1 }
            r4.<init>(r1)     // Catch:{ all -> 0x00a1 }
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x00a1 }
            r1.<init>(r4)     // Catch:{ all -> 0x00a1 }
            r2.store(r1, r0)     // Catch:{ all -> 0x009e }
            r3.close()     // Catch:{ Exception -> 0x0091 }
            goto L_0x0095
        L_0x0091:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x00d6 }
        L_0x0095:
            r1.close()     // Catch:{ Exception -> 0x0099 }
            goto L_0x00be
        L_0x0099:
            r0 = move-exception
        L_0x009a:
            r0.printStackTrace()     // Catch:{ all -> 0x00d6 }
            goto L_0x00be
        L_0x009e:
            r0 = move-exception
            r2 = r0
            goto L_0x00a4
        L_0x00a1:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L_0x00a4:
            r0 = r3
            goto L_0x00a9
        L_0x00a6:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L_0x00a9:
            r2.printStackTrace()     // Catch:{ all -> 0x00c0 }
            if (r0 == 0) goto L_0x00b6
            r0.close()     // Catch:{ Exception -> 0x00b2 }
            goto L_0x00b6
        L_0x00b2:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x00d6 }
        L_0x00b6:
            if (r1 == 0) goto L_0x00be
            r1.close()     // Catch:{ Exception -> 0x00bc }
            goto L_0x00be
        L_0x00bc:
            r0 = move-exception
            goto L_0x009a
        L_0x00be:
            monitor-exit(r10)
            return
        L_0x00c0:
            r2 = move-exception
            if (r0 == 0) goto L_0x00cb
            r0.close()     // Catch:{ Exception -> 0x00c7 }
            goto L_0x00cb
        L_0x00c7:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x00d6 }
        L_0x00cb:
            if (r1 == 0) goto L_0x00d5
            r1.close()     // Catch:{ Exception -> 0x00d1 }
            goto L_0x00d5
        L_0x00d1:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x00d6 }
        L_0x00d5:
            throw r2     // Catch:{ all -> 0x00d6 }
        L_0x00d6:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsBaseConfig.writeTbsDownloadInfo():void");
    }
}
