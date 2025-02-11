package com.getkeepsafe.relinker;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.getkeepsafe.relinker.ReLinker;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ApkLibraryInstaller implements ReLinker.LibraryInstaller {
    private static final int COPY_BUFFER_SIZE = 4096;
    private static final int MAX_TRIES = 5;

    private String[] sourceDirectories(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (Build.VERSION.SDK_INT < 21 || applicationInfo.splitSourceDirs == null || applicationInfo.splitSourceDirs.length == 0) {
            return new String[]{applicationInfo.sourceDir};
        }
        String[] strArr = new String[(applicationInfo.splitSourceDirs.length + 1)];
        strArr[0] = applicationInfo.sourceDir;
        System.arraycopy(applicationInfo.splitSourceDirs, 0, strArr, 1, applicationInfo.splitSourceDirs.length);
        return strArr;
    }

    private static class ZipFileInZipEntry {
        public ZipEntry zipEntry;
        public ZipFile zipFile;

        public ZipFileInZipEntry(ZipFile zipFile2, ZipEntry zipEntry2) {
            this.zipFile = zipFile2;
            this.zipEntry = zipEntry2;
        }
    }

    private ZipFileInZipEntry findAPKWithLibrary(Context context, String[] strArr, String str, ReLinkerInstance reLinkerInstance) {
        int i;
        String[] strArr2 = strArr;
        String[] sourceDirectories = sourceDirectories(context);
        int length = sourceDirectories.length;
        char c = 0;
        ZipFile zipFile = null;
        int i2 = 0;
        while (i2 < length) {
            String str2 = sourceDirectories[i2];
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                i = 5;
                if (i3 >= 5) {
                    break;
                }
                try {
                    zipFile = new ZipFile(new File(str2), 1);
                    break;
                } catch (IOException unused) {
                    i3 = i4;
                }
            }
            if (zipFile == null) {
                String str3 = str;
                ReLinkerInstance reLinkerInstance2 = reLinkerInstance;
            } else {
                int i5 = 0;
                while (true) {
                    int i6 = i5 + 1;
                    if (i5 < i) {
                        int length2 = strArr2.length;
                        int i7 = 0;
                        while (i7 < length2) {
                            String str4 = "lib" + File.separatorChar + strArr2[i7] + File.separatorChar + str;
                            Object[] objArr = new Object[2];
                            objArr[c] = str4;
                            objArr[1] = str2;
                            reLinkerInstance.log("Looking for %s in APK %s...", objArr);
                            ZipEntry entry = zipFile.getEntry(str4);
                            if (entry != null) {
                                return new ZipFileInZipEntry(zipFile, entry);
                            }
                            i7++;
                            c = 0;
                        }
                        String str5 = str;
                        ReLinkerInstance reLinkerInstance3 = reLinkerInstance;
                        i5 = i6;
                        i = 5;
                    } else {
                        String str6 = str;
                        ReLinkerInstance reLinkerInstance4 = reLinkerInstance;
                        try {
                            zipFile.close();
                            break;
                        } catch (IOException unused2) {
                        }
                    }
                }
            }
            i2++;
            c = 0;
        }
        return null;
    }

    private String[] getSupportedABIs(Context context, String str) {
        Pattern compile = Pattern.compile("lib" + File.separatorChar + "([^\\" + File.separatorChar + "]*)" + File.separatorChar + str);
        HashSet hashSet = new HashSet();
        for (String file : sourceDirectories(context)) {
            try {
                Enumeration<? extends ZipEntry> entries = new ZipFile(new File(file), 1).entries();
                while (entries.hasMoreElements()) {
                    Matcher matcher = compile.matcher(((ZipEntry) entries.nextElement()).getName());
                    if (matcher.matches()) {
                        hashSet.add(matcher.group(1));
                    }
                }
            } catch (IOException unused) {
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.getkeepsafe.relinker.ApkLibraryInstaller$ZipFileInZipEntry} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r0v3, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00b5 A[SYNTHETIC, Splitter:B:75:0x00b5] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void installLibrary(android.content.Context r11, java.lang.String[] r12, java.lang.String r13, java.io.File r14, com.getkeepsafe.relinker.ReLinkerInstance r15) {
        /*
            r10 = this;
            r0 = 0
            com.getkeepsafe.relinker.ApkLibraryInstaller$ZipFileInZipEntry r1 = r10.findAPKWithLibrary(r11, r12, r13, r15)     // Catch:{ all -> 0x00b2 }
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x009a
            r11 = 0
        L_0x000a:
            int r12 = r11 + 1
            r4 = 5
            if (r11 >= r4) goto L_0x0089
            java.lang.String r11 = "Found %s! Extracting..."
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x009f }
            r4[r2] = r13     // Catch:{ all -> 0x009f }
            r15.log(r11, r4)     // Catch:{ all -> 0x009f }
            boolean r11 = r14.exists()     // Catch:{ IOException -> 0x0087 }
            if (r11 != 0) goto L_0x0026
            boolean r11 = r14.createNewFile()     // Catch:{ IOException -> 0x0087 }
            if (r11 != 0) goto L_0x0026
            goto L_0x0087
        L_0x0026:
            java.util.zip.ZipFile r11 = r1.zipFile     // Catch:{ FileNotFoundException -> 0x0081, IOException -> 0x007b, all -> 0x0072 }
            java.util.zip.ZipEntry r4 = r1.zipEntry     // Catch:{ FileNotFoundException -> 0x0081, IOException -> 0x007b, all -> 0x0072 }
            java.io.InputStream r11 = r11.getInputStream(r4)     // Catch:{ FileNotFoundException -> 0x0081, IOException -> 0x007b, all -> 0x0072 }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0070, IOException -> 0x006e, all -> 0x006a }
            r4.<init>(r14)     // Catch:{ FileNotFoundException -> 0x0070, IOException -> 0x006e, all -> 0x006a }
            long r5 = r10.copy(r11, r4)     // Catch:{ FileNotFoundException -> 0x0083, IOException -> 0x007d, all -> 0x0068 }
            java.io.FileDescriptor r7 = r4.getFD()     // Catch:{ FileNotFoundException -> 0x0083, IOException -> 0x007d, all -> 0x0068 }
            r7.sync()     // Catch:{ FileNotFoundException -> 0x0083, IOException -> 0x007d, all -> 0x0068 }
            long r7 = r14.length()     // Catch:{ FileNotFoundException -> 0x0083, IOException -> 0x007d, all -> 0x0068 }
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x004d
            r10.closeSilently(r11)     // Catch:{ all -> 0x009f }
        L_0x0049:
            r10.closeSilently(r4)     // Catch:{ all -> 0x009f }
            goto L_0x0087
        L_0x004d:
            r10.closeSilently(r11)     // Catch:{ all -> 0x009f }
            r10.closeSilently(r4)     // Catch:{ all -> 0x009f }
            r14.setReadable(r3, r2)     // Catch:{ all -> 0x009f }
            r14.setExecutable(r3, r2)     // Catch:{ all -> 0x009f }
            r14.setWritable(r3)     // Catch:{ all -> 0x009f }
            if (r1 == 0) goto L_0x0067
            java.util.zip.ZipFile r11 = r1.zipFile     // Catch:{ IOException -> 0x0067 }
            if (r11 == 0) goto L_0x0067
            java.util.zip.ZipFile r11 = r1.zipFile     // Catch:{ IOException -> 0x0067 }
            r11.close()     // Catch:{ IOException -> 0x0067 }
        L_0x0067:
            return
        L_0x0068:
            r12 = move-exception
            goto L_0x006c
        L_0x006a:
            r12 = move-exception
            r4 = r0
        L_0x006c:
            r0 = r11
            goto L_0x0074
        L_0x006e:
            r4 = r0
            goto L_0x007d
        L_0x0070:
            r4 = r0
            goto L_0x0083
        L_0x0072:
            r12 = move-exception
            r4 = r0
        L_0x0074:
            r10.closeSilently(r0)     // Catch:{ all -> 0x009f }
            r10.closeSilently(r4)     // Catch:{ all -> 0x009f }
            throw r12     // Catch:{ all -> 0x009f }
        L_0x007b:
            r11 = r0
            r4 = r11
        L_0x007d:
            r10.closeSilently(r11)     // Catch:{ all -> 0x009f }
            goto L_0x0049
        L_0x0081:
            r11 = r0
            r4 = r11
        L_0x0083:
            r10.closeSilently(r11)     // Catch:{ all -> 0x009f }
            goto L_0x0049
        L_0x0087:
            r11 = r12
            goto L_0x000a
        L_0x0089:
            java.lang.String r11 = "FATAL! Couldn't extract the library from the APK!"
            r15.log((java.lang.String) r11)     // Catch:{ all -> 0x009f }
            if (r1 == 0) goto L_0x0099
            java.util.zip.ZipFile r11 = r1.zipFile     // Catch:{ IOException -> 0x0099 }
            if (r11 == 0) goto L_0x0099
            java.util.zip.ZipFile r11 = r1.zipFile     // Catch:{ IOException -> 0x0099 }
            r11.close()     // Catch:{ IOException -> 0x0099 }
        L_0x0099:
            return
        L_0x009a:
            java.lang.String[] r11 = r10.getSupportedABIs(r11, r13)     // Catch:{ Exception -> 0x00a2 }
            goto L_0x00ac
        L_0x009f:
            r11 = move-exception
            r0 = r1
            goto L_0x00b3
        L_0x00a2:
            r11 = move-exception
            java.lang.String[] r14 = new java.lang.String[r3]     // Catch:{ all -> 0x009f }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x009f }
            r14[r2] = r11     // Catch:{ all -> 0x009f }
            r11 = r14
        L_0x00ac:
            com.getkeepsafe.relinker.MissingLibraryException r14 = new com.getkeepsafe.relinker.MissingLibraryException     // Catch:{ all -> 0x009f }
            r14.<init>(r13, r12, r11)     // Catch:{ all -> 0x009f }
            throw r14     // Catch:{ all -> 0x009f }
        L_0x00b2:
            r11 = move-exception
        L_0x00b3:
            if (r0 == 0) goto L_0x00be
            java.util.zip.ZipFile r12 = r0.zipFile     // Catch:{ IOException -> 0x00be }
            if (r12 == 0) goto L_0x00be
            java.util.zip.ZipFile r12 = r0.zipFile     // Catch:{ IOException -> 0x00be }
            r12.close()     // Catch:{ IOException -> 0x00be }
        L_0x00be:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getkeepsafe.relinker.ApkLibraryInstaller.installLibrary(android.content.Context, java.lang.String[], java.lang.String, java.io.File, com.getkeepsafe.relinker.ReLinkerInstance):void");
    }

    private long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                outputStream.flush();
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }

    private void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
