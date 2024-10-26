package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloader;
import com.tencent.smtt.sdk.TbsLogReport;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.zip.ZipEntry;
import kotlin.jvm.internal.LongCompanionObject;

public class FileUtil {
    public static String a = null;
    public static final a b = new a() {
        public boolean a(File file, File file2) {
            return file.length() == file2.length() && file.lastModified() == file2.lastModified();
        }
    };
    private static final int c = 4;
    private static RandomAccessFile d;

    public interface a {
        boolean a(File file, File file2);
    }

    public interface b {
        boolean a(InputStream inputStream, ZipEntry zipEntry, String str) throws Exception;
    }

    public static long a(InputStream inputStream, OutputStream outputStream) throws IOException, OutOfMemoryError {
        if (inputStream == null) {
            return -1;
        }
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }

    public static File a(Context context, String str) {
        String str2;
        File file = new File(context.getFilesDir(), "tbs");
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file.canWrite()) {
            str2 = "getPermanentTbsFile -- no permission!";
        } else {
            File file2 = new File(file, str);
            if (!file2.exists()) {
                try {
                    file2.createNewFile();
                } catch (IOException e) {
                    str2 = "getPermanentTbsFile -- exception: " + e;
                }
            }
            return file2;
        }
        TbsLog.e("FileHelper", str2);
        return null;
    }

    public static File a(Context context, boolean z, String str) {
        String d2 = z ? d(context) : c(context);
        if (d2 == null) {
            return null;
        }
        File file = new File(d2);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file.canWrite()) {
            return null;
        }
        File file2 = new File(file, str);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file2;
    }

    public static String a(Context context, int i) {
        return a(context, context.getApplicationInfo().packageName, i, true);
    }

    public static String a(Context context, String str, int i, boolean z) {
        String str2;
        if (context == null) {
            return "";
        }
        try {
            str2 = Environment.getExternalStorageDirectory() + File.separator;
        } catch (Exception e) {
            e.printStackTrace();
            str2 = "";
        }
        switch (i) {
            case 1:
                if (str2.equals("")) {
                    return str2;
                }
                return str2 + "tencent" + File.separator + "tbs" + File.separator + str;
            case 2:
                if (str2.equals("")) {
                    return str2;
                }
                return str2 + "tbs" + File.separator + "backup" + File.separator + str;
            case 3:
                if (str2.equals("")) {
                    return str2;
                }
                return str2 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + str;
            case 4:
                if (str2.equals("")) {
                    return b(context, "backup");
                }
                String str3 = str2 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + str;
                if (!z) {
                    return str3;
                }
                File file = new File(str3);
                if (file.exists() && file.canWrite()) {
                    return str3;
                }
                if (file.exists()) {
                    return b(context, "backup");
                }
                file.mkdirs();
                return !file.canWrite() ? b(context, "backup") : str3;
            case 5:
                if (str2.equals("")) {
                    return str2;
                }
                return str2 + "tencent" + File.separator + "tbs" + File.separator + str;
            case 6:
                String str4 = a;
                if (str4 != null) {
                    return str4;
                }
                String b2 = b(context, "tbslog");
                a = b2;
                return b2;
            case 7:
                if (str2.equals("")) {
                    return str2;
                }
                return str2 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + "core";
            case 8:
                return b(context, "env");
            default:
                return "";
        }
    }

    public static FileLock a(Context context, FileOutputStream fileOutputStream) {
        if (fileOutputStream == null) {
            return null;
        }
        try {
            FileLock tryLock = fileOutputStream.getChannel().tryLock();
            if (tryLock.isValid()) {
                return tryLock;
            }
            return null;
        } catch (OverlappingFileLockException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static synchronized void a(Context context, FileLock fileLock) {
        synchronized (FileUtil.class) {
            TbsLog.i("FileHelper", "releaseTbsCoreRenameFileLock -- lock: " + fileLock);
            FileChannel channel = fileLock.channel();
            if (channel != null && channel.isOpen()) {
                try {
                    fileLock.release();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }

    public static void a(File file, boolean z) {
        a(file, z, false);
    }

    public static void a(File file, boolean z, String str) {
        TbsLog.i("FileUtils", "delete file,ignore=" + z + "except" + str + file + Log.getStackTraceString(new Throwable()));
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (!file2.getName().equals(str)) {
                        a(file2, z);
                    }
                }
                if (!z) {
                    file.delete();
                }
            }
        }
    }

    public static void a(File file, boolean z, boolean z2) {
        TbsLog.i("FileUtils", "delete file,ignore=" + z + "isSoftLink=" + z2);
        if (file != null) {
            if (!z2 && !file.exists()) {
                return;
            }
            if ((!z2 || file.isDirectory()) && !file.isFile()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File a2 : listFiles) {
                        a(a2, z, z2);
                    }
                    if (!z) {
                        file.delete();
                        return;
                    }
                    return;
                }
                return;
            }
            file.delete();
        }
    }

    public static void a(FileLock fileLock, FileOutputStream fileOutputStream) {
        if (fileLock != null) {
            try {
                FileChannel channel = fileLock.channel();
                if (channel != null && channel.isOpen()) {
                    fileLock.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean a(Context context) {
        boolean z = true;
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        if (context == null) {
            return false;
        }
        if (context.getApplicationContext().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            z = false;
        }
        return z;
    }

    public static boolean a(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        b(file);
        return file.mkdirs();
    }

    public static boolean a(File file, File file2) throws Exception {
        return a(file.getPath(), file2.getPath());
    }

    public static boolean a(File file, File file2, FileFilter fileFilter) throws Exception {
        return a(file, file2, fileFilter, b);
    }

    public static boolean a(File file, File file2, FileFilter fileFilter, a aVar) throws Exception {
        if (file == null || file2 == null || !file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return b(file, file2, fileFilter, aVar);
        }
        File[] listFiles = file.listFiles(fileFilter);
        if (listFiles == null) {
            return false;
        }
        boolean z = true;
        for (File file3 : listFiles) {
            if (!a(file3, new File(file2, file3.getName()), fileFilter)) {
                z = false;
            }
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.lang.String r4, long r5, long r7, long r9) throws java.lang.Exception {
        /*
            java.io.File r7 = new java.io.File
            r7.<init>(r4)
            long r0 = r7.length()
            r4 = 1
            java.lang.String r8 = "FileHelper"
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 == 0) goto L_0x0031
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "file size doesn't match: "
            r9.append(r10)
            long r0 = r7.length()
            r9.append(r0)
            java.lang.String r7 = " vs "
            r9.append(r7)
            r9.append(r5)
            java.lang.String r5 = r9.toString()
            com.tencent.smtt.utils.TbsLog.e(r8, r5)
            return r4
        L_0x0031:
            r5 = 0
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ all -> 0x0083 }
            r6.<init>(r7)     // Catch:{ all -> 0x0083 }
            java.util.zip.CRC32 r5 = new java.util.zip.CRC32     // Catch:{ all -> 0x0080 }
            r5.<init>()     // Catch:{ all -> 0x0080 }
            r0 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r0]     // Catch:{ all -> 0x0080 }
        L_0x0040:
            int r1 = r6.read(r0)     // Catch:{ all -> 0x0080 }
            r2 = 0
            if (r1 <= 0) goto L_0x004b
            r5.update(r0, r2, r1)     // Catch:{ all -> 0x0080 }
            goto L_0x0040
        L_0x004b:
            long r0 = r5.getValue()     // Catch:{ all -> 0x0080 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0080 }
            r5.<init>()     // Catch:{ all -> 0x0080 }
            java.lang.String r3 = ""
            r5.append(r3)     // Catch:{ all -> 0x0080 }
            java.lang.String r7 = r7.getName()     // Catch:{ all -> 0x0080 }
            r5.append(r7)     // Catch:{ all -> 0x0080 }
            java.lang.String r7 = ": crc = "
            r5.append(r7)     // Catch:{ all -> 0x0080 }
            r5.append(r0)     // Catch:{ all -> 0x0080 }
            java.lang.String r7 = ", zipCrc = "
            r5.append(r7)     // Catch:{ all -> 0x0080 }
            r5.append(r9)     // Catch:{ all -> 0x0080 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0080 }
            com.tencent.smtt.utils.TbsLog.i(r8, r5)     // Catch:{ all -> 0x0080 }
            int r5 = (r0 > r9 ? 1 : (r0 == r9 ? 0 : -1))
            r6.close()
            if (r5 == 0) goto L_0x007f
            return r4
        L_0x007f:
            return r2
        L_0x0080:
            r4 = move-exception
            r5 = r6
            goto L_0x0084
        L_0x0083:
            r4 = move-exception
        L_0x0084:
            if (r5 == 0) goto L_0x0089
            r5.close()
        L_0x0089:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.FileUtil.a(java.lang.String, long, long, long):boolean");
    }

    public static boolean a(String str, String str2) throws Exception {
        return a(str, str2, Build.CPU_ABI, Build.VERSION.SDK_INT >= 8 ? Build.CPU_ABI2 : null, PropertyUtils.getQuickly("ro.product.cpu.upgradeabi", "armeabi"));
    }

    /* JADX WARNING: Removed duplicated region for block: B:63:0x00d7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, com.tencent.smtt.utils.FileUtil.b r15) throws java.lang.Exception {
        /*
            r0 = 0
            java.util.zip.ZipFile r1 = new java.util.zip.ZipFile     // Catch:{ all -> 0x00d4 }
            r1.<init>(r11)     // Catch:{ all -> 0x00d4 }
            java.util.Enumeration r11 = r1.entries()     // Catch:{ all -> 0x00d1 }
            r0 = 1
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x000e:
            boolean r5 = r11.hasMoreElements()     // Catch:{ all -> 0x00d1 }
            if (r5 == 0) goto L_0x00cd
            java.lang.Object r5 = r11.nextElement()     // Catch:{ all -> 0x00d1 }
            java.util.zip.ZipEntry r5 = (java.util.zip.ZipEntry) r5     // Catch:{ all -> 0x00d1 }
            java.lang.String r6 = r5.getName()     // Catch:{ all -> 0x00d1 }
            if (r6 != 0) goto L_0x0021
            goto L_0x000e
        L_0x0021:
            java.lang.String r7 = "../"
            boolean r7 = r6.contains(r7)     // Catch:{ all -> 0x00d1 }
            if (r7 == 0) goto L_0x002a
            goto L_0x000e
        L_0x002a:
            java.lang.String r7 = "lib/"
            boolean r7 = r6.startsWith(r7)     // Catch:{ all -> 0x00d1 }
            if (r7 != 0) goto L_0x003b
            java.lang.String r7 = "assets/"
            boolean r7 = r6.startsWith(r7)     // Catch:{ all -> 0x00d1 }
            if (r7 != 0) goto L_0x003b
            goto L_0x000e
        L_0x003b:
            r7 = 47
            int r8 = r6.lastIndexOf(r7)     // Catch:{ all -> 0x00d1 }
            java.lang.String r8 = r6.substring(r8)     // Catch:{ all -> 0x00d1 }
            java.lang.String r9 = ".so"
            boolean r9 = r8.endsWith(r9)     // Catch:{ all -> 0x00d1 }
            if (r9 == 0) goto L_0x00a8
            int r9 = c     // Catch:{ all -> 0x00d1 }
            int r10 = r12.length()     // Catch:{ all -> 0x00d1 }
            boolean r9 = r6.regionMatches(r9, r12, r2, r10)     // Catch:{ all -> 0x00d1 }
            if (r9 == 0) goto L_0x0068
            int r9 = c     // Catch:{ all -> 0x00d1 }
            int r10 = r12.length()     // Catch:{ all -> 0x00d1 }
            int r9 = r9 + r10
            char r9 = r6.charAt(r9)     // Catch:{ all -> 0x00d1 }
            if (r9 != r7) goto L_0x0068
            r3 = 1
            goto L_0x00a8
        L_0x0068:
            if (r13 == 0) goto L_0x0087
            int r9 = c     // Catch:{ all -> 0x00d1 }
            int r10 = r13.length()     // Catch:{ all -> 0x00d1 }
            boolean r9 = r6.regionMatches(r9, r13, r2, r10)     // Catch:{ all -> 0x00d1 }
            if (r9 == 0) goto L_0x0087
            int r9 = c     // Catch:{ all -> 0x00d1 }
            int r10 = r13.length()     // Catch:{ all -> 0x00d1 }
            int r9 = r9 + r10
            char r9 = r6.charAt(r9)     // Catch:{ all -> 0x00d1 }
            if (r9 != r7) goto L_0x0087
            r4 = 1
            if (r3 == 0) goto L_0x00a8
            goto L_0x000e
        L_0x0087:
            if (r14 == 0) goto L_0x000e
            int r9 = c     // Catch:{ all -> 0x00d1 }
            int r10 = r14.length()     // Catch:{ all -> 0x00d1 }
            boolean r9 = r6.regionMatches(r9, r14, r2, r10)     // Catch:{ all -> 0x00d1 }
            if (r9 == 0) goto L_0x000e
            int r9 = c     // Catch:{ all -> 0x00d1 }
            int r10 = r14.length()     // Catch:{ all -> 0x00d1 }
            int r9 = r9 + r10
            char r6 = r6.charAt(r9)     // Catch:{ all -> 0x00d1 }
            if (r6 != r7) goto L_0x000e
            if (r3 != 0) goto L_0x000e
            if (r4 == 0) goto L_0x00a8
            goto L_0x000e
        L_0x00a8:
            java.io.InputStream r6 = r1.getInputStream(r5)     // Catch:{ all -> 0x00d1 }
            java.lang.String r7 = r8.substring(r0)     // Catch:{ all -> 0x00c6 }
            boolean r5 = r15.a(r6, r5, r7)     // Catch:{ all -> 0x00c6 }
            if (r5 != 0) goto L_0x00bf
            if (r6 == 0) goto L_0x00bb
            r6.close()     // Catch:{ all -> 0x00d1 }
        L_0x00bb:
            r1.close()
            return r2
        L_0x00bf:
            if (r6 == 0) goto L_0x000e
            r6.close()     // Catch:{ all -> 0x00d1 }
            goto L_0x000e
        L_0x00c6:
            r11 = move-exception
            if (r6 == 0) goto L_0x00cc
            r6.close()     // Catch:{ all -> 0x00d1 }
        L_0x00cc:
            throw r11     // Catch:{ all -> 0x00d1 }
        L_0x00cd:
            r1.close()
            return r0
        L_0x00d1:
            r11 = move-exception
            r0 = r1
            goto L_0x00d5
        L_0x00d4:
            r11 = move-exception
        L_0x00d5:
            if (r0 == 0) goto L_0x00da
            r0.close()
        L_0x00da:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.FileUtil.a(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.tencent.smtt.utils.FileUtil$b):boolean");
    }

    private static boolean a(String str, final String str2, String str3, String str4, String str5) throws Exception {
        return a(str, str3, str4, str5, (b) new b() {
            public boolean a(InputStream inputStream, ZipEntry zipEntry, String str) throws Exception {
                try {
                    return FileUtil.b(inputStream, zipEntry, str2, str);
                } catch (Exception e) {
                    throw new Exception("copyFileIfChanged Exception", e);
                }
            }
        });
    }

    public static FileOutputStream b(Context context, boolean z, String str) {
        File a2 = a(context, z, str);
        if (a2 == null) {
            return null;
        }
        try {
            return new FileOutputStream(a2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String b(Context context, String str) {
        if (context == null) {
            return "";
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        try {
            return context.getExternalFilesDir(str).getAbsolutePath();
        } catch (Throwable th) {
            th.printStackTrace();
            try {
                return Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + context.getApplicationInfo().packageName + File.separator + "files" + File.separator + str;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static void b(File file) {
        a(file, false);
    }

    public static boolean b(Context context) {
        long a2 = p.a();
        boolean z = a2 >= TbsDownloadConfig.getInstance(context).getDownloadMinFreeSpace();
        if (!z) {
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDwonloader.hasEnoughFreeSpace] freeSpace too small,  freeSpace = " + a2);
        }
        return z;
    }

    public static boolean b(File file, File file2) throws Exception {
        return a(file, file2, (FileFilter) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0098  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean b(java.io.File r12, java.io.File r13, java.io.FileFilter r14, com.tencent.smtt.utils.FileUtil.a r15) throws java.lang.Exception {
        /*
            r0 = 0
            if (r12 == 0) goto L_0x009c
            if (r13 != 0) goto L_0x0007
            goto L_0x009c
        L_0x0007:
            if (r14 == 0) goto L_0x0010
            boolean r14 = r14.accept(r12)
            if (r14 != 0) goto L_0x0010
            return r0
        L_0x0010:
            r14 = 0
            boolean r1 = r12.exists()     // Catch:{ all -> 0x008f }
            if (r1 == 0) goto L_0x008e
            boolean r1 = r12.isFile()     // Catch:{ all -> 0x008f }
            if (r1 != 0) goto L_0x001f
            goto L_0x008e
        L_0x001f:
            boolean r1 = r13.exists()     // Catch:{ all -> 0x008f }
            r2 = 1
            if (r1 == 0) goto L_0x0032
            if (r15 == 0) goto L_0x002f
            boolean r15 = r15.a(r12, r13)     // Catch:{ all -> 0x008f }
            if (r15 == 0) goto L_0x002f
            return r2
        L_0x002f:
            b((java.io.File) r13)     // Catch:{ all -> 0x008f }
        L_0x0032:
            java.io.File r15 = r13.getParentFile()     // Catch:{ all -> 0x008f }
            boolean r1 = r15.isFile()     // Catch:{ all -> 0x008f }
            if (r1 == 0) goto L_0x003f
            b((java.io.File) r15)     // Catch:{ all -> 0x008f }
        L_0x003f:
            boolean r1 = r15.exists()     // Catch:{ all -> 0x008f }
            if (r1 != 0) goto L_0x004c
            boolean r15 = r15.mkdirs()     // Catch:{ all -> 0x008f }
            if (r15 != 0) goto L_0x004c
            return r0
        L_0x004c:
            java.io.FileInputStream r15 = new java.io.FileInputStream     // Catch:{ all -> 0x008f }
            r15.<init>(r12)     // Catch:{ all -> 0x008f }
            java.nio.channels.FileChannel r12 = r15.getChannel()     // Catch:{ all -> 0x008f }
            java.io.FileOutputStream r15 = new java.io.FileOutputStream     // Catch:{ all -> 0x0088 }
            r15.<init>(r13)     // Catch:{ all -> 0x0088 }
            java.nio.channels.FileChannel r14 = r15.getChannel()     // Catch:{ all -> 0x0088 }
            long r9 = r12.size()     // Catch:{ all -> 0x0088 }
            r5 = 0
            r3 = r14
            r4 = r12
            r7 = r9
            long r3 = r3.transferFrom(r4, r5, r7)     // Catch:{ all -> 0x0088 }
            int r15 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r15 == 0) goto L_0x007d
            b((java.io.File) r13)     // Catch:{ all -> 0x0088 }
            if (r12 == 0) goto L_0x0077
            r12.close()
        L_0x0077:
            if (r14 == 0) goto L_0x007c
            r14.close()
        L_0x007c:
            return r0
        L_0x007d:
            if (r12 == 0) goto L_0x0082
            r12.close()
        L_0x0082:
            if (r14 == 0) goto L_0x0087
            r14.close()
        L_0x0087:
            return r2
        L_0x0088:
            r13 = move-exception
            r11 = r14
            r14 = r12
            r12 = r13
            r13 = r11
            goto L_0x0091
        L_0x008e:
            return r0
        L_0x008f:
            r12 = move-exception
            r13 = r14
        L_0x0091:
            if (r14 == 0) goto L_0x0096
            r14.close()
        L_0x0096:
            if (r13 == 0) goto L_0x009b
            r13.close()
        L_0x009b:
            throw r12
        L_0x009c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.FileUtil.b(java.io.File, java.io.File, java.io.FileFilter, com.tencent.smtt.utils.FileUtil$a):boolean");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(java.io.InputStream r9, java.util.zip.ZipEntry r10, java.lang.String r11, java.lang.String r12) throws java.lang.Exception {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r11)
            a((java.io.File) r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r11)
            java.lang.String r11 = java.io.File.separator
            r0.append(r11)
            r0.append(r12)
            java.lang.String r11 = r0.toString()
            java.io.File r12 = new java.io.File
            r12.<init>(r11)
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x008b }
            r1.<init>(r12)     // Catch:{ IOException -> 0x008b }
            r0 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x0086, all -> 0x0083 }
        L_0x002b:
            int r2 = r9.read(r0)     // Catch:{ IOException -> 0x0086, all -> 0x0083 }
            r8 = 0
            if (r2 <= 0) goto L_0x0036
            r1.write(r0, r8, r2)     // Catch:{ IOException -> 0x0086, all -> 0x0083 }
            goto L_0x002b
        L_0x0036:
            r1.close()
            long r2 = r10.getSize()
            long r4 = r10.getTime()
            long r6 = r10.getCrc()
            r1 = r11
            boolean r9 = a((java.lang.String) r1, (long) r2, (long) r4, (long) r6)
            java.lang.String r0 = "FileHelper"
            if (r9 == 0) goto L_0x0063
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "file is different: "
            r9.append(r10)
            r9.append(r11)
            java.lang.String r9 = r9.toString()
            com.tencent.smtt.utils.TbsLog.e(r0, r9)
            return r8
        L_0x0063:
            long r9 = r10.getTime()
            boolean r9 = r12.setLastModified(r9)
            if (r9 != 0) goto L_0x0081
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Couldn't set time for dst file "
            r9.append(r10)
            r9.append(r12)
            java.lang.String r9 = r9.toString()
            com.tencent.smtt.utils.TbsLog.e(r0, r9)
        L_0x0081:
            r9 = 1
            return r9
        L_0x0083:
            r9 = move-exception
            r0 = r1
            goto L_0x00a6
        L_0x0086:
            r9 = move-exception
            r0 = r1
            goto L_0x008c
        L_0x0089:
            r9 = move-exception
            goto L_0x00a6
        L_0x008b:
            r9 = move-exception
        L_0x008c:
            b((java.io.File) r12)     // Catch:{ all -> 0x0089 }
            java.io.IOException r10 = new java.io.IOException     // Catch:{ all -> 0x0089 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0089 }
            r11.<init>()     // Catch:{ all -> 0x0089 }
            java.lang.String r1 = "Couldn't write dst file "
            r11.append(r1)     // Catch:{ all -> 0x0089 }
            r11.append(r12)     // Catch:{ all -> 0x0089 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0089 }
            r10.<init>(r11, r9)     // Catch:{ all -> 0x0089 }
            throw r10     // Catch:{ all -> 0x0089 }
        L_0x00a6:
            if (r0 == 0) goto L_0x00ab
            r0.close()
        L_0x00ab:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.FileUtil.b(java.io.InputStream, java.util.zip.ZipEntry, java.lang.String, java.lang.String):boolean");
    }

    public static String c(Context context) {
        return Environment.getExternalStorageDirectory() + File.separator + "tbs" + File.separator + "file_locks";
    }

    public static boolean c(File file) {
        return file != null && file.exists() && file.isFile() && file.length() > 0;
    }

    public static int copy(InputStream inputStream, OutputStream outputStream) throws IOException, OutOfMemoryError {
        long a2 = a(inputStream, outputStream);
        if (a2 > 2147483647L) {
            return -1;
        }
        return (int) a2;
    }

    public static FileOutputStream d(File file) throws IOException {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                throw new IOException("File '" + file + "' could not be created");
            }
        } else if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        } else if (!file.canWrite()) {
            throw new IOException("File '" + file + "' cannot be written to");
        }
        return new FileOutputStream(file);
    }

    static String d(Context context) {
        File file = new File(QbSdk.getTbsFolderDir(context), "core_private");
        if (file.isDirectory() || file.mkdir()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public static FileLock e(Context context) {
        boolean z;
        StringBuilder sb;
        String str;
        String str2;
        TbsLog.i("FileHelper", "getTbsCoreLoadFileLock #1");
        try {
            z = TbsDownloadConfig.getInstance().getTbsCoreLoadRenameFileLockEnable();
        } catch (Throwable unused) {
            z = true;
        }
        FileLock fileLock = null;
        if (!z) {
            FileOutputStream b2 = b(context, true, "tbs_rename_lock");
            if (b2 == null) {
                str2 = "init -- failed to get rename fileLock#1!";
            } else {
                fileLock = a(context, b2);
                str2 = fileLock == null ? "init -- failed to get rename fileLock#2!" : "init -- get rename fileLock success!";
            }
            TbsLog.i("FileHelper", str2);
            TbsLog.i("FileHelper", "getTbsCoreLoadFileLock #2 renameFileLock is " + fileLock);
            return fileLock;
        }
        TbsLog.i("FileHelper", "getTbsCoreLoadFileLock #3");
        File a2 = a(context, "tbs_rename_lock");
        TbsLog.i("FileHelper", "getTbsCoreLoadFileLock #4 " + a2);
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(a2.getAbsolutePath(), "r");
            d = randomAccessFile;
            fileLock = randomAccessFile.getChannel().tryLock(0, LongCompanionObject.MAX_VALUE, true);
        } catch (Throwable th) {
            TbsLog.e("FileHelper", "getTbsCoreLoadFileLock -- exception: " + th);
        }
        if (fileLock == null) {
            fileLock = g(context);
        }
        if (fileLock == null) {
            str = "getTbsCoreLoadFileLock -- failed: ";
        } else {
            sb = new StringBuilder();
            str = "getTbsCoreLoadFileLock -- success: ";
        }
        sb.append(str);
        sb.append("tbs_rename_lock");
        TbsLog.i("FileHelper", sb.toString());
        return fileLock;
    }

    public static FileLock f(Context context) {
        FileLock fileLock;
        StringBuilder sb;
        String str;
        File a2 = a(context, "tbs_rename_lock");
        TbsLog.i("FileHelper", "getTbsCoreRenameFileLock #1 " + a2);
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(a2.getAbsolutePath(), "rw");
            d = randomAccessFile;
            fileLock = randomAccessFile.getChannel().tryLock(0, LongCompanionObject.MAX_VALUE, false);
        } catch (Throwable unused) {
            TbsLog.e("FileHelper", "getTbsCoreRenameFileLock -- excpetion: " + "tbs_rename_lock");
            fileLock = null;
        }
        if (fileLock == null) {
            str = "getTbsCoreRenameFileLock -- failed: ";
        } else {
            sb = new StringBuilder();
            str = "getTbsCoreRenameFileLock -- success: ";
        }
        sb.append(str);
        sb.append("tbs_rename_lock");
        TbsLog.i("FileHelper", sb.toString());
        return fileLock;
    }

    private static FileLock g(Context context) {
        FileLock fileLock = null;
        try {
            TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(context).tbsLogInfo();
            tbsLogInfo.setErrorCode(803);
            File a2 = a(context, "tbs_rename_lock");
            if (TbsDownloadConfig.getInstance(context).getTbsCoreLoadRenameFileLockWaitEnable()) {
                boolean z = false;
                int i = 0;
                while (i < 20 && fileLock == null) {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } catch (Throwable unused) {
                    }
                    RandomAccessFile randomAccessFile = new RandomAccessFile(a2.getAbsolutePath(), "r");
                    d = randomAccessFile;
                    fileLock = randomAccessFile.getChannel().tryLock(0, LongCompanionObject.MAX_VALUE, true);
                    i++;
                }
                if (fileLock != null) {
                    tbsLogInfo.setErrorCode(802);
                } else {
                    tbsLogInfo.setErrorCode(801);
                }
                TbsLogReport.getInstance(context).eventReport(TbsLogReport.EventType.TYPE_SDK_REPORT_INFO, tbsLogInfo);
                StringBuilder sb = new StringBuilder();
                sb.append("getTbsCoreLoadFileLock,retry num=");
                sb.append(i);
                sb.append("success=");
                if (fileLock == null) {
                    z = true;
                }
                sb.append(z);
                TbsLog.i("FileHelper", sb.toString());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return fileLock;
    }
}
