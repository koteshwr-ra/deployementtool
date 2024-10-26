package com.ciot.networklib;

import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import java.io.File;
import java.io.IOException;

public class FileUtil {
    public static File getFileByPath(String str) {
        if (isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x004c A[SYNTHETIC, Splitter:B:35:0x004c] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x005f A[SYNTHETIC, Splitter:B:45:0x005f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean writeFileFromIS(java.io.File r4, java.io.InputStream r5, boolean r6) {
        /*
            boolean r0 = createOrExistsFile(r4)
            r1 = 0
            if (r0 == 0) goto L_0x0068
            if (r5 != 0) goto L_0x000a
            goto L_0x0068
        L_0x000a:
            r0 = 0
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x003e }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x003e }
            r3.<init>(r4, r6)     // Catch:{ IOException -> 0x003e }
            r2.<init>(r3)     // Catch:{ IOException -> 0x003e }
            r4 = 8192(0x2000, float:1.14794E-41)
            byte[] r6 = new byte[r4]     // Catch:{ IOException -> 0x0039, all -> 0x0036 }
        L_0x0019:
            int r0 = r5.read(r6, r1, r4)     // Catch:{ IOException -> 0x0039, all -> 0x0036 }
            r3 = -1
            if (r0 == r3) goto L_0x0024
            r2.write(r6, r1, r0)     // Catch:{ IOException -> 0x0039, all -> 0x0036 }
            goto L_0x0019
        L_0x0024:
            r4 = 1
            r5.close()     // Catch:{ IOException -> 0x0029 }
            goto L_0x002d
        L_0x0029:
            r5 = move-exception
            r5.printStackTrace()
        L_0x002d:
            r2.close()     // Catch:{ IOException -> 0x0031 }
            goto L_0x0035
        L_0x0031:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0035:
            return r4
        L_0x0036:
            r4 = move-exception
            r0 = r2
            goto L_0x0055
        L_0x0039:
            r4 = move-exception
            r0 = r2
            goto L_0x003f
        L_0x003c:
            r4 = move-exception
            goto L_0x0055
        L_0x003e:
            r4 = move-exception
        L_0x003f:
            r4.printStackTrace()     // Catch:{ all -> 0x003c }
            r5.close()     // Catch:{ IOException -> 0x0046 }
            goto L_0x004a
        L_0x0046:
            r4 = move-exception
            r4.printStackTrace()
        L_0x004a:
            if (r0 == 0) goto L_0x0054
            r0.close()     // Catch:{ IOException -> 0x0050 }
            goto L_0x0054
        L_0x0050:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0054:
            return r1
        L_0x0055:
            r5.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x005d
        L_0x0059:
            r5 = move-exception
            r5.printStackTrace()
        L_0x005d:
            if (r0 == 0) goto L_0x0067
            r0.close()     // Catch:{ IOException -> 0x0063 }
            goto L_0x0067
        L_0x0063:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0067:
            throw r4
        L_0x0068:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.networklib.FileUtil.writeFileFromIS(java.io.File, java.io.InputStream, boolean):boolean");
    }

    private static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    public static String extension2MediaType(String str) {
        String trim = str.trim();
        if ("mp4".equalsIgnoreCase(trim)) {
            return "video/mpeg4";
        }
        if ("jpg".equalsIgnoreCase(trim)) {
            return "image/jpeg";
        }
        if ("mp3".equalsIgnoreCase(trim)) {
            return "audio/mp3";
        }
        if ("wav".equalsIgnoreCase(trim)) {
            return "audio/x-wav";
        }
        if ("png".equalsIgnoreCase(trim)) {
            return "image/png";
        }
        if ("txt".equalsIgnoreCase(trim)) {
            return "text/plain";
        }
        return "zip".equalsIgnoreCase(trim) ? "application/zip" : OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE;
    }

    public static String getFileExtension(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(File.separator);
        if (lastIndexOf == -1 || lastIndexOf2 >= lastIndexOf) {
            return "";
        }
        return str.substring(lastIndexOf + 1);
    }
}
