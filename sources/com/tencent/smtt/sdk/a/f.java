package com.tencent.smtt.sdk.a;

import com.tencent.smtt.utils.TbsLog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;

public class f {
    private static String a = "EmergencyManager";
    private final File b;
    private final FileOutputStream c;
    private final FileLock d;

    private f(File file, FileOutputStream fileOutputStream, FileLock fileLock) {
        this.b = file;
        this.c = fileOutputStream;
        this.d = fileLock;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0099 A[SYNTHETIC, Splitter:B:23:0x0099] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.tencent.smtt.sdk.a.f a(java.io.File r7) {
        /*
            java.lang.String r0 = "Failed to close: "
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x006f }
            r2.<init>(r7)     // Catch:{ all -> 0x006f }
            java.nio.channels.FileChannel r3 = r2.getChannel()     // Catch:{ all -> 0x006d }
            java.nio.channels.FileLock r3 = r3.tryLock()     // Catch:{ all -> 0x006d }
            if (r3 == 0) goto L_0x004f
            java.lang.String r4 = a     // Catch:{ all -> 0x006d }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x006d }
            r5.<init>()     // Catch:{ all -> 0x006d }
            java.lang.String r6 = "Created lock file: "
            r5.append(r6)     // Catch:{ all -> 0x006d }
            java.lang.String r6 = r7.getAbsolutePath()     // Catch:{ all -> 0x006d }
            r5.append(r6)     // Catch:{ all -> 0x006d }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x006d }
            com.tencent.smtt.utils.TbsLog.i(r4, r5)     // Catch:{ all -> 0x006d }
            com.tencent.smtt.sdk.a.f r4 = new com.tencent.smtt.sdk.a.f     // Catch:{ all -> 0x006d }
            r4.<init>(r7, r2, r3)     // Catch:{ all -> 0x006d }
            r2.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x004e
        L_0x0035:
            r7 = move-exception
            java.lang.String r1 = a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            java.lang.String r7 = r7.getMessage()
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            com.tencent.smtt.utils.TbsLog.e(r1, r7)
        L_0x004e:
            return r4
        L_0x004f:
            r2.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x00a6
        L_0x0053:
            r7 = move-exception
            java.lang.String r2 = a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
        L_0x005b:
            r3.append(r0)
            java.lang.String r7 = r7.getMessage()
            r3.append(r7)
            java.lang.String r7 = r3.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r7)
            goto L_0x00a6
        L_0x006d:
            r3 = move-exception
            goto L_0x0071
        L_0x006f:
            r3 = move-exception
            r2 = r1
        L_0x0071:
            java.lang.String r4 = a     // Catch:{ all -> 0x00a7 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a7 }
            r5.<init>()     // Catch:{ all -> 0x00a7 }
            java.lang.String r6 = "Failed to try to acquire lock: "
            r5.append(r6)     // Catch:{ all -> 0x00a7 }
            java.lang.String r7 = r7.getAbsolutePath()     // Catch:{ all -> 0x00a7 }
            r5.append(r7)     // Catch:{ all -> 0x00a7 }
            java.lang.String r7 = " error: "
            r5.append(r7)     // Catch:{ all -> 0x00a7 }
            java.lang.String r7 = r3.getMessage()     // Catch:{ all -> 0x00a7 }
            r5.append(r7)     // Catch:{ all -> 0x00a7 }
            java.lang.String r7 = r5.toString()     // Catch:{ all -> 0x00a7 }
            com.tencent.smtt.utils.TbsLog.e(r4, r7)     // Catch:{ all -> 0x00a7 }
            if (r2 == 0) goto L_0x00a6
            r2.close()     // Catch:{ IOException -> 0x009d }
            goto L_0x00a6
        L_0x009d:
            r7 = move-exception
            java.lang.String r2 = a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            goto L_0x005b
        L_0x00a6:
            return r1
        L_0x00a7:
            r7 = move-exception
            if (r2 == 0) goto L_0x00c7
            r2.close()     // Catch:{ IOException -> 0x00ae }
            goto L_0x00c7
        L_0x00ae:
            r1 = move-exception
            java.lang.String r2 = a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = r1.getMessage()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r0)
        L_0x00c7:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.a.f.a(java.io.File):com.tencent.smtt.sdk.a.f");
    }

    public void a() throws IOException {
        String str = a;
        TbsLog.i(str, "Deleting lock file: " + this.b.getAbsolutePath());
        this.d.release();
        this.c.close();
        if (!this.b.delete()) {
            throw new IOException("Failed to delete lock file: " + this.b.getAbsolutePath());
        }
    }

    public void b() {
        try {
            a();
        } catch (IOException unused) {
        }
    }
}
