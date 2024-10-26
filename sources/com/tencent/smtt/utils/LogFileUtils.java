package com.tencent.smtt.utils;

import android.util.Log;
import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class LogFileUtils {
    private static OutputStream a;

    public static void closeOutputStream(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                Log.e("LOG_FILE", "Couldn't close stream!", e);
            }
        }
    }

    public static byte[] createHeaderText(String str, String str2) {
        try {
            byte[] encryptKey = encryptKey(str, str2);
            String format = String.format("%03d", new Object[]{Integer.valueOf(encryptKey.length)});
            byte[] bArr = new byte[(encryptKey.length + 3)];
            bArr[0] = (byte) format.charAt(0);
            bArr[1] = (byte) format.charAt(1);
            bArr[2] = (byte) format.charAt(2);
            System.arraycopy(encryptKey, 0, bArr, 3, encryptKey.length);
            return bArr;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String createKey() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static byte[] encrypt(String str, String str2) {
        try {
            byte[] bytes = str2.getBytes("UTF-8");
            Cipher instance = Cipher.getInstance("RC4");
            instance.init(1, new SecretKeySpec(str.getBytes("UTF-8"), "RC4"));
            return instance.update(bytes);
        } catch (Throwable th) {
            Log.e("LOG_FILE", "encrypt exception:" + th.getMessage());
            return null;
        }
    }

    public static byte[] encryptKey(String str, String str2) {
        try {
            byte[] bytes = str2.getBytes("UTF-8");
            Cipher instance = Cipher.getInstance("RC4");
            instance.init(1, new SecretKeySpec(str.getBytes("UTF-8"), "RC4"));
            return instance.update(bytes);
        } catch (Throwable th) {
            Log.e("LOG_FILE", "encrypt exception:" + th.getMessage());
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0069, code lost:
        if (a != null) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        a.flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0073, code lost:
        if (a == null) goto L_0x0076;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0071 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void writeDataToStorage(java.io.File r6, java.lang.String r7, byte[] r8, java.lang.String r9, boolean r10) {
        /*
            java.lang.Class<com.tencent.smtt.utils.LogFileUtils> r0 = com.tencent.smtt.utils.LogFileUtils.class
            monitor-enter(r0)
            byte[] r7 = encrypt(r7, r9)     // Catch:{ all -> 0x0078 }
            r1 = 0
            if (r7 == 0) goto L_0x000c
            r9 = r1
            goto L_0x000d
        L_0x000c:
            r7 = r1
        L_0x000d:
            java.io.File r1 = r6.getParentFile()     // Catch:{ all -> 0x0071 }
            r1.mkdirs()     // Catch:{ all -> 0x0071 }
            boolean r1 = r6.isFile()     // Catch:{ all -> 0x0071 }
            if (r1 == 0) goto L_0x0031
            boolean r1 = r6.exists()     // Catch:{ all -> 0x0071 }
            if (r1 == 0) goto L_0x0031
            long r1 = r6.length()     // Catch:{ all -> 0x0071 }
            r3 = 2097152(0x200000, double:1.0361308E-317)
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0031
            r6.delete()     // Catch:{ all -> 0x0071 }
            r6.createNewFile()     // Catch:{ all -> 0x0071 }
        L_0x0031:
            java.io.OutputStream r1 = a     // Catch:{ all -> 0x0071 }
            if (r1 != 0) goto L_0x0041
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0071 }
            r1.<init>(r6, r10)     // Catch:{ all -> 0x0071 }
            java.io.BufferedOutputStream r6 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0071 }
            r6.<init>(r1)     // Catch:{ all -> 0x0071 }
            a = r6     // Catch:{ all -> 0x0071 }
        L_0x0041:
            if (r9 == 0) goto L_0x004d
            java.io.OutputStream r6 = a     // Catch:{ all -> 0x0071 }
            byte[] r7 = r9.getBytes()     // Catch:{ all -> 0x0071 }
            r6.write(r7)     // Catch:{ all -> 0x0071 }
            goto L_0x0067
        L_0x004d:
            java.io.OutputStream r6 = a     // Catch:{ all -> 0x0071 }
            r6.write(r8)     // Catch:{ all -> 0x0071 }
            java.io.OutputStream r6 = a     // Catch:{ all -> 0x0071 }
            r6.write(r7)     // Catch:{ all -> 0x0071 }
            java.io.OutputStream r6 = a     // Catch:{ all -> 0x0071 }
            r7 = 2
            byte[] r7 = new byte[r7]     // Catch:{ all -> 0x0071 }
            r8 = 0
            r9 = 10
            r7[r8] = r9     // Catch:{ all -> 0x0071 }
            r8 = 1
            r7[r8] = r9     // Catch:{ all -> 0x0071 }
            r6.write(r7)     // Catch:{ all -> 0x0071 }
        L_0x0067:
            java.io.OutputStream r6 = a     // Catch:{ all -> 0x0078 }
            if (r6 == 0) goto L_0x0076
        L_0x006b:
            java.io.OutputStream r6 = a     // Catch:{ all -> 0x0076 }
            r6.flush()     // Catch:{ all -> 0x0076 }
            goto L_0x0076
        L_0x0071:
            java.io.OutputStream r6 = a     // Catch:{ all -> 0x0078 }
            if (r6 == 0) goto L_0x0076
            goto L_0x006b
        L_0x0076:
            monitor-exit(r0)
            return
        L_0x0078:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.LogFileUtils.writeDataToStorage(java.io.File, java.lang.String, byte[], java.lang.String, boolean):void");
    }
}
