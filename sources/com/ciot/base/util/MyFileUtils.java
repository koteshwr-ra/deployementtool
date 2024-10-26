package com.ciot.base.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.mina.proxy.handlers.http.HttpProxyConstants;

public class MyFileUtils {
    private MyFileUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int getLenFromByte(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            int available = byteArrayInputStream.available();
            try {
                byteArrayInputStream.close();
                return available;
            } catch (IOException e) {
                e.printStackTrace();
                return available;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            try {
                byteArrayInputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            return 0;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    public static File getFileByPath(String str) {
        if (MyStringUtils.isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    public static boolean isFileExists(String str) {
        return isFileExists(getFileByPath(str));
    }

    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    public static boolean rename(String str, String str2) {
        return rename(getFileByPath(str), str2);
    }

    public static boolean rename(File file, String str) {
        if (file == null || !file.exists() || MyStringUtils.isSpace(str)) {
            return false;
        }
        if (str.equals(file.getName())) {
            return true;
        }
        File file2 = new File(file.getParent() + File.separator + str);
        if (file2.exists() || !file.renameTo(file2)) {
            return false;
        }
        return true;
    }

    public static boolean isDir(String str) {
        return isDir(getFileByPath(str));
    }

    public static boolean isDir(File file) {
        return isFileExists(file) && file.isDirectory();
    }

    public static boolean isFile(String str) {
        return isFile(getFileByPath(str));
    }

    public static boolean isFile(File file) {
        return isFileExists(file) && file.isFile();
    }

    public static boolean createOrExistsDir(String str) {
        return createOrExistsDir(getFileByPath(str));
    }

    public static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    public static boolean createOrExistsFile(String str) {
        return createOrExistsFile(getFileByPath(str));
    }

    public static boolean createOrExistsFile(File file) {
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

    public static boolean createFileByDeleteOldFile(String str) {
        return createFileByDeleteOldFile(getFileByPath(str));
    }

    public static boolean createFileByDeleteOldFile(File file) {
        if (file == null) {
            return false;
        }
        if ((file.exists() && file.isFile() && !file.delete()) || !createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean copyOrMoveDir(String str, String str2, boolean z) {
        return copyOrMoveDir(getFileByPath(str), getFileByPath(str2), z);
    }

    private static boolean copyOrMoveDir(File file, File file2, boolean z) {
        if (file == null || file2 == null) {
            return false;
        }
        String str = file2.getPath() + File.separator;
        if (str.contains(file.getPath() + File.separator) || !file.exists() || !file.isDirectory() || !createOrExistsDir(file2)) {
            return false;
        }
        for (File file3 : file.listFiles()) {
            File file4 = new File(str + file3.getName());
            if (file3.isFile()) {
                if (!copyOrMoveFile(file3, file4, z)) {
                    return false;
                }
            } else if (file3.isDirectory() && !copyOrMoveDir(file3, file4, z)) {
                return false;
            }
        }
        if (!z || deleteDir(file)) {
            return true;
        }
        return false;
    }

    private static boolean copyOrMoveFile(String str, String str2, boolean z) {
        return copyOrMoveFile(getFileByPath(str), getFileByPath(str2), z);
    }

    private static boolean copyOrMoveFile(File file, File file2, boolean z) {
        if (file != null && file2 != null && file.exists() && file.isFile()) {
            if ((file2.exists() && file2.isFile()) || !createOrExistsDir(file2.getParentFile())) {
                return false;
            }
            try {
                if (!writeFileFromIS(file2, (InputStream) new FileInputStream(file), false)) {
                    return false;
                }
                if (!z || deleteFile(file)) {
                    return true;
                }
                return false;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean copyDir(String str, String str2) {
        return copyDir(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean copyDir(File file, File file2) {
        return copyOrMoveDir(file, file2, false);
    }

    public static boolean copyFile(String str, String str2) {
        return copyFile(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean copyFile(File file, File file2) {
        return copyOrMoveFile(file, file2, false);
    }

    public static boolean moveDir(String str, String str2) {
        return moveDir(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean moveDir(File file, File file2) {
        return copyOrMoveDir(file, file2, true);
    }

    public static boolean moveFile(String str, String str2) {
        return moveFile(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean moveFile(File file, File file2) {
        return copyOrMoveFile(file, file2, true);
    }

    public static boolean deleteDir(String str) {
        return deleteDir(getFileByPath(str));
    }

    public static boolean deleteDir(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    if (!deleteFile(file2)) {
                        return false;
                    }
                } else if (file2.isDirectory() && !deleteDir(file2)) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static boolean deleteFile(String str) {
        return deleteFile(getFileByPath(str));
    }

    public static boolean deleteFile(File file) {
        return file != null && (!file.exists() || (file.isFile() && file.delete()));
    }

    public static boolean deleteFilesInDir(String str) {
        return deleteFilesInDir(getFileByPath(str));
    }

    public static boolean deleteFilesInDir(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    if (!deleteFile(file2)) {
                        return false;
                    }
                } else if (file2.isDirectory() && !deleteDir(file2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static List<File> listFilesInDir(String str, boolean z) {
        return listFilesInDir(getFileByPath(str), z);
    }

    public static List<File> listFilesInDir(File file, boolean z) {
        if (!isDir(file)) {
            return null;
        }
        if (z) {
            return listFilesInDir(file);
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            Collections.addAll(arrayList, listFiles);
        }
        return arrayList;
    }

    public static List<File> listFilesInDir(String str) {
        return listFilesInDir(getFileByPath(str));
    }

    public static List<File> listFilesInDir(File file) {
        if (!isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                arrayList.add(file2);
                if (file2.isDirectory()) {
                    arrayList.addAll(listFilesInDir(file2));
                }
            }
        }
        return arrayList;
    }

    public static List<File> listDirInDir(String str) {
        File fileByPath = getFileByPath(str);
        if (!isDir(fileByPath)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = fileByPath.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    arrayList.add(file);
                }
            }
        }
        return arrayList;
    }

    public static List<File> listFilesInDirWithFilter(String str, String str2, boolean z) {
        return listFilesInDirWithFilter(getFileByPath(str), str2, z);
    }

    public static List<File> listFilesInDirWithFilter(File file, String str, boolean z) {
        if (z) {
            return listFilesInDirWithFilter(file, str);
        }
        if (file == null || !isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (file2.getName().toUpperCase().endsWith(str.toUpperCase())) {
                    arrayList.add(file2);
                }
            }
        }
        return arrayList;
    }

    public static List<File> listFilesInDirWithFilter(String str, String str2) {
        return listFilesInDirWithFilter(getFileByPath(str), str2);
    }

    public static List<File> listFilesInDirWithFilter(File file, String str) {
        if (file == null || !isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (file2.getName().toUpperCase().endsWith(str.toUpperCase())) {
                    arrayList.add(file2);
                }
                if (file2.isDirectory()) {
                    arrayList.addAll(listFilesInDirWithFilter(file2, str));
                }
            }
        }
        return arrayList;
    }

    public static List<File> listFilesInDirWithFilter(String str, FilenameFilter filenameFilter, boolean z) {
        return listFilesInDirWithFilter(getFileByPath(str), filenameFilter, z);
    }

    public static List<File> listFilesInDirWithFilter(File file, FilenameFilter filenameFilter, boolean z) {
        if (z) {
            return listFilesInDirWithFilter(file, filenameFilter);
        }
        if (file == null || !isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (filenameFilter.accept(file2.getParentFile(), file2.getName())) {
                    arrayList.add(file2);
                }
            }
        }
        return arrayList;
    }

    public static List<File> listFilesInDirWithFilter(String str, FilenameFilter filenameFilter) {
        return listFilesInDirWithFilter(getFileByPath(str), filenameFilter);
    }

    public static List<File> listFilesInDirWithFilter(File file, FilenameFilter filenameFilter) {
        if (file == null || !isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (filenameFilter.accept(file2.getParentFile(), file2.getName())) {
                    arrayList.add(file2);
                }
                if (file2.isDirectory()) {
                    arrayList.addAll(listFilesInDirWithFilter(file2, filenameFilter));
                }
            }
        }
        return arrayList;
    }

    public static List<File> searchFileInDir(String str, String str2) {
        return searchFileInDir(getFileByPath(str), str2);
    }

    public static List<File> searchFileInDir(File file, String str) {
        if (file == null || !isDir(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (file2.getName().toUpperCase().equals(str.toUpperCase())) {
                    arrayList.add(file2);
                }
                if (file2.isDirectory()) {
                    arrayList.addAll(searchFileInDir(file2, str));
                }
            }
        }
        return arrayList;
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream, boolean z) {
        return writeFileFromIS(getFileByPath(str), inputStream, z);
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream, boolean z) {
        if (file == null || inputStream == null || !createOrExistsFile(file)) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file, z));
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr, 0, 1024);
                    if (read != -1) {
                        bufferedOutputStream2.write(bArr, 0, read);
                    } else {
                        CloseUtils.closeIO(inputStream, bufferedOutputStream2);
                        return true;
                    }
                }
            } catch (IOException e) {
                e = e;
                bufferedOutputStream = bufferedOutputStream2;
                try {
                    e.printStackTrace();
                    CloseUtils.closeIO(inputStream, bufferedOutputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    CloseUtils.closeIO(inputStream, bufferedOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream = bufferedOutputStream2;
                CloseUtils.closeIO(inputStream, bufferedOutputStream);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            e.printStackTrace();
            CloseUtils.closeIO(inputStream, bufferedOutputStream);
            return false;
        }
    }

    public static boolean writeFileFromString(String str, String str2, boolean z) {
        return writeFileFromString(getFileByPath(str), str2, z);
    }

    public static boolean writeFileFromString(File file, String str, boolean z) {
        if (file == null || str == null || !createOrExistsFile(file)) {
            return false;
        }
        BufferedWriter bufferedWriter = null;
        try {
            BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file, z));
            try {
                bufferedWriter2.write(str);
                CloseUtils.closeIO(bufferedWriter2);
                return true;
            } catch (IOException e) {
                e = e;
                bufferedWriter = bufferedWriter2;
                try {
                    e.printStackTrace();
                    CloseUtils.closeIO(bufferedWriter);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    CloseUtils.closeIO(bufferedWriter);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedWriter = bufferedWriter2;
                CloseUtils.closeIO(bufferedWriter);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            e.printStackTrace();
            CloseUtils.closeIO(bufferedWriter);
            return false;
        }
    }

    public static List<String> readFile2List(String str, String str2) {
        return readFile2List(getFileByPath(str), str2);
    }

    public static List<String> readFile2List(File file, String str) {
        return readFile2List(file, 0, Integer.MAX_VALUE, str);
    }

    public static List<String> readFile2List(String str, int i, int i2, String str2) {
        return readFile2List(getFileByPath(str), i, i2, str2);
    }

    public static List<String> readFile2List(File file, int i, int i2, String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        if (file == null || i > i2) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            if (MyStringUtils.isSpace(str)) {
                bufferedReader = new BufferedReader(new FileReader(file));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), str));
            }
            int i3 = 1;
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (i3 > i2) {
                        break;
                    } else {
                        if (i <= i3 && i3 <= i2) {
                            arrayList.add(readLine);
                        }
                        i3++;
                    }
                } catch (IOException e) {
                    e = e;
                    try {
                        e.printStackTrace();
                        CloseUtils.closeIO(bufferedReader);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader2 = bufferedReader;
                        CloseUtils.closeIO(bufferedReader2);
                        throw th;
                    }
                }
            }
            CloseUtils.closeIO(bufferedReader);
            return arrayList;
        } catch (IOException e2) {
            e = e2;
            bufferedReader = null;
            e.printStackTrace();
            CloseUtils.closeIO(bufferedReader);
            return null;
        } catch (Throwable th2) {
            th = th2;
            CloseUtils.closeIO(bufferedReader2);
            throw th;
        }
    }

    public static String readFile2String(String str, String str2) {
        return readFile2String(getFileByPath(str), str2);
    }

    public static String readFile2String(File file, String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        if (file == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            if (MyStringUtils.isSpace(str)) {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), str));
            }
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                        sb.append(HttpProxyConstants.CRLF);
                    } else {
                        String sb2 = sb.delete(sb.length() - 2, sb.length()).toString();
                        CloseUtils.closeIO(bufferedReader);
                        return sb2;
                    }
                } catch (IOException e) {
                    e = e;
                    try {
                        e.printStackTrace();
                        CloseUtils.closeIO(bufferedReader);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader2 = bufferedReader;
                        CloseUtils.closeIO(bufferedReader2);
                        throw th;
                    }
                }
            }
        } catch (IOException e2) {
            e = e2;
            bufferedReader = null;
            e.printStackTrace();
            CloseUtils.closeIO(bufferedReader);
            return null;
        } catch (Throwable th2) {
            th = th2;
            CloseUtils.closeIO(bufferedReader2);
            throw th;
        }
    }

    public static String getFileCharsetSimple(String str) {
        return getFileCharsetSimple(getFileByPath(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004c A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getFileCharsetSimple(java.io.File r5) {
        /*
            r0 = 1
            r1 = 0
            r2 = 0
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0029 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0029 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0029 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0029 }
            int r5 = r3.read()     // Catch:{ IOException -> 0x0024, all -> 0x0021 }
            int r5 = r5 << 8
            int r2 = r3.read()     // Catch:{ IOException -> 0x0024, all -> 0x0021 }
            int r5 = r5 + r2
            java.io.Closeable[] r0 = new java.io.Closeable[r0]
            r0[r1] = r3
            com.ciot.base.util.CloseUtils.closeIO(r0)
            r1 = r5
            goto L_0x0034
        L_0x0021:
            r5 = move-exception
            r2 = r3
            goto L_0x004f
        L_0x0024:
            r5 = move-exception
            r2 = r3
            goto L_0x002a
        L_0x0027:
            r5 = move-exception
            goto L_0x004f
        L_0x0029:
            r5 = move-exception
        L_0x002a:
            r5.printStackTrace()     // Catch:{ all -> 0x0027 }
            java.io.Closeable[] r5 = new java.io.Closeable[r0]
            r5[r1] = r2
            com.ciot.base.util.CloseUtils.closeIO(r5)
        L_0x0034:
            r5 = 61371(0xefbb, float:8.5999E-41)
            if (r1 == r5) goto L_0x004c
            r5 = 65279(0xfeff, float:9.1475E-41)
            if (r1 == r5) goto L_0x0049
            r5 = 65534(0xfffe, float:9.1833E-41)
            if (r1 == r5) goto L_0x0046
            java.lang.String r5 = "GBK"
            return r5
        L_0x0046:
            java.lang.String r5 = "Unicode"
            return r5
        L_0x0049:
            java.lang.String r5 = "UTF-16BE"
            return r5
        L_0x004c:
            java.lang.String r5 = "UTF-8"
            return r5
        L_0x004f:
            java.io.Closeable[] r0 = new java.io.Closeable[r0]
            r0[r1] = r2
            com.ciot.base.util.CloseUtils.closeIO(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.base.util.MyFileUtils.getFileCharsetSimple(java.io.File):java.lang.String");
    }

    public static int getFileLines(String str) {
        return getFileLines(getFileByPath(str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002f, code lost:
        r9 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0030, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0032, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0033, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0019 A[Catch:{ IOException -> 0x002f, all -> 0x0032 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0032 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0027 A[EDGE_INSN: B:33:0x0027->B:17:0x0027 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getFileLines(java.io.File r9) {
        /*
            r0 = 0
            r1 = 1
            r2 = 0
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x003a }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ IOException -> 0x003a }
            r4.<init>(r9)     // Catch:{ IOException -> 0x003a }
            r3.<init>(r4)     // Catch:{ IOException -> 0x003a }
            r9 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r9]     // Catch:{ IOException -> 0x0035, all -> 0x0032 }
            r4 = 1
        L_0x0012:
            int r5 = r3.read(r2, r0, r9)     // Catch:{ IOException -> 0x002f, all -> 0x0032 }
            r6 = -1
            if (r5 == r6) goto L_0x0027
            r6 = 0
        L_0x001a:
            if (r6 >= r5) goto L_0x0012
            byte r7 = r2[r6]     // Catch:{ IOException -> 0x002f, all -> 0x0032 }
            r8 = 10
            if (r7 != r8) goto L_0x0024
            int r4 = r4 + 1
        L_0x0024:
            int r6 = r6 + 1
            goto L_0x001a
        L_0x0027:
            java.io.Closeable[] r9 = new java.io.Closeable[r1]
            r9[r0] = r3
            com.ciot.base.util.CloseUtils.closeIO(r9)
            goto L_0x0046
        L_0x002f:
            r9 = move-exception
            r2 = r3
            goto L_0x003c
        L_0x0032:
            r9 = move-exception
            r2 = r3
            goto L_0x0047
        L_0x0035:
            r9 = move-exception
            r2 = r3
            goto L_0x003b
        L_0x0038:
            r9 = move-exception
            goto L_0x0047
        L_0x003a:
            r9 = move-exception
        L_0x003b:
            r4 = 1
        L_0x003c:
            r9.printStackTrace()     // Catch:{ all -> 0x0038 }
            java.io.Closeable[] r9 = new java.io.Closeable[r1]
            r9[r0] = r2
            com.ciot.base.util.CloseUtils.closeIO(r9)
        L_0x0046:
            return r4
        L_0x0047:
            java.io.Closeable[] r1 = new java.io.Closeable[r1]
            r1[r0] = r2
            com.ciot.base.util.CloseUtils.closeIO(r1)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.base.util.MyFileUtils.getFileLines(java.io.File):int");
    }

    public static long getDirLength(String str) {
        return getDirLength(getFileByPath(str));
    }

    public static long getDirLength(File file) {
        long j;
        if (!isDir(file)) {
            return -1;
        }
        long j2 = 0;
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    j = getDirLength(file2);
                } else {
                    j = file2.length();
                }
                j2 += j;
            }
        }
        return j2;
    }

    public static long getFileLength(String str) {
        return getFileLength(getFileByPath(str));
    }

    public static long getFileLength(File file) {
        if (!isFile(file)) {
            return -1;
        }
        return file.length();
    }

    public static byte[] getFileMD5(String str) {
        return getFileMD5(MyStringUtils.isSpace(str) ? null : new File(str));
    }

    public static byte[] getFileMD5(File file) {
        DigestInputStream digestInputStream;
        DigestInputStream digestInputStream2 = null;
        if (file == null) {
            return null;
        }
        try {
            digestInputStream = new DigestInputStream(new FileInputStream(file), MessageDigest.getInstance("MD5"));
            try {
                while (digestInputStream.read(new byte[262144]) > 0) {
                }
                byte[] digest = digestInputStream.getMessageDigest().digest();
                CloseUtils.closeIO(digestInputStream);
                return digest;
            } catch (IOException | NoSuchAlgorithmException e) {
                e = e;
                try {
                    e.printStackTrace();
                    CloseUtils.closeIO(digestInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    digestInputStream2 = digestInputStream;
                    CloseUtils.closeIO(digestInputStream2);
                    throw th;
                }
            }
        } catch (IOException | NoSuchAlgorithmException e2) {
            e = e2;
            digestInputStream = null;
            e.printStackTrace();
            CloseUtils.closeIO(digestInputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            CloseUtils.closeIO(digestInputStream2);
            throw th;
        }
    }

    public static String getDirName(File file) {
        if (file == null) {
            return null;
        }
        return getDirName(file.getPath());
    }

    public static String getDirName(String str) {
        if (MyStringUtils.isSpace(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        if (lastIndexOf == -1) {
            return "";
        }
        return str.substring(0, lastIndexOf + 1);
    }

    public static String getFileName(File file) {
        if (file == null) {
            return null;
        }
        return getFileName(file.getPath());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
        r0 = r2.lastIndexOf(java.io.File.separator);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getFileName(java.lang.String r2) {
        /*
            boolean r0 = com.ciot.base.util.MyStringUtils.isSpace(r2)
            if (r0 == 0) goto L_0x0007
            return r2
        L_0x0007:
            java.lang.String r0 = java.io.File.separator
            int r0 = r2.lastIndexOf(r0)
            r1 = -1
            if (r0 != r1) goto L_0x0011
            goto L_0x0017
        L_0x0011:
            int r0 = r0 + 1
            java.lang.String r2 = r2.substring(r0)
        L_0x0017:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.base.util.MyFileUtils.getFileName(java.lang.String):java.lang.String");
    }

    public static String getFileNameNoExtension(File file) {
        if (file == null) {
            return null;
        }
        return getFileNameNoExtension(file.getPath());
    }

    public static String getFileNameNoExtension(String str) {
        if (MyStringUtils.isSpace(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(File.separator);
        if (lastIndexOf2 == -1) {
            return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
        }
        if (lastIndexOf == -1 || lastIndexOf2 > lastIndexOf) {
            return str.substring(lastIndexOf2 + 1);
        }
        return str.substring(lastIndexOf2 + 1, lastIndexOf);
    }

    public static long getLength(String str) {
        return getLength(getFileByPath(str));
    }

    public static long getLength(File file) {
        if (file == null) {
            return 0;
        }
        if (file.isDirectory()) {
            return getDirLength(file);
        }
        return getFileLength(file);
    }

    public static boolean delete(String str) {
        return delete(getFileByPath(str));
    }

    public static boolean delete(File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            return deleteDir(file);
        }
        return deleteFile(file);
    }
}
