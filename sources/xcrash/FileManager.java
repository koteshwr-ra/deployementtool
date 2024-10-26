package xcrash;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

class FileManager {
    private static final FileManager instance = new FileManager();
    private int anrLogCountMax = 0;
    private int delayMs = 0;
    private int javaLogCountMax = 0;
    private String logDir = null;
    private int nativeLogCountMax = 0;
    /* access modifiers changed from: private */
    public String placeholderCleanSuffix = ".clean.xcrash";
    private int placeholderCountMax = 0;
    /* access modifiers changed from: private */
    public String placeholderDirtySuffix = ".dirty.xcrash";
    /* access modifiers changed from: private */
    public String placeholderPrefix = "placeholder";
    private int placeholderSizeKb = 0;
    private int traceLogCountMax = 1;
    private AtomicInteger unique = new AtomicInteger();

    private FileManager() {
    }

    static FileManager getInstance() {
        return instance;
    }

    /* access modifiers changed from: package-private */
    public void initialize(String str, int i, int i2, int i3, int i4, int i5, int i6) {
        this.logDir = str;
        this.javaLogCountMax = i;
        this.nativeLogCountMax = i2;
        this.anrLogCountMax = i3;
        this.placeholderCountMax = i4;
        this.placeholderSizeKb = i5;
        this.delayMs = i6;
        try {
            File file = new File(str);
            if (!file.exists()) {
                return;
            }
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    int i7 = 0;
                    int i8 = 0;
                    int i9 = 0;
                    int i10 = 0;
                    int i11 = 0;
                    int i12 = 0;
                    for (File file2 : listFiles) {
                        if (file2.isFile()) {
                            String name = file2.getName();
                            if (!name.startsWith("tombstone_")) {
                                if (name.startsWith(this.placeholderPrefix + "_")) {
                                    if (name.endsWith(this.placeholderCleanSuffix)) {
                                        i11++;
                                    } else if (name.endsWith(this.placeholderDirtySuffix)) {
                                        i12++;
                                    }
                                }
                            } else if (name.endsWith(".java.xcrash")) {
                                i7++;
                            } else if (name.endsWith(".native.xcrash")) {
                                i8++;
                            } else if (name.endsWith(".anr.xcrash")) {
                                i9++;
                            } else if (name.endsWith(".trace.xcrash")) {
                                i10++;
                            }
                        }
                    }
                    if (i7 > this.javaLogCountMax || i8 > this.nativeLogCountMax || i9 > this.anrLogCountMax || i10 > this.traceLogCountMax || i11 != this.placeholderCountMax || i12 != 0) {
                        if (i7 <= this.javaLogCountMax + 10 && i8 <= this.nativeLogCountMax + 10 && i9 <= this.anrLogCountMax + 10 && i10 <= this.traceLogCountMax + 10 && i11 <= this.placeholderCountMax + 10) {
                            if (i12 <= 10) {
                                if (i7 > this.javaLogCountMax || i8 > this.nativeLogCountMax || i9 > this.anrLogCountMax || i10 > this.traceLogCountMax || i11 > this.placeholderCountMax || i12 > 0) {
                                    this.delayMs = 0;
                                    return;
                                }
                                return;
                            }
                        }
                        doMaintain();
                        this.delayMs = -1;
                        return;
                    }
                    this.delayMs = -1;
                }
            }
        } catch (Exception e) {
            XCrash.getLogger().e("xcrash", "FileManager init failed", e);
        }
    }

    /* access modifiers changed from: package-private */
    public void maintain() {
        int i;
        if (this.logDir != null && (i = this.delayMs) >= 0) {
            if (i == 0) {
                try {
                    new Thread(new Runnable() {
                        public void run() {
                            FileManager.this.doMaintain();
                        }
                    }, "xcrash_file_mgr").start();
                } catch (Exception e) {
                    XCrash.getLogger().e("xcrash", "FileManager maintain start failed", e);
                }
            } else {
                new Timer("xcrash_file_mgr").schedule(new TimerTask() {
                    public void run() {
                        FileManager.this.doMaintain();
                    }
                }, (long) this.delayMs);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean maintainAnr() {
        if (!Util.checkAndCreateDir(this.logDir)) {
            return false;
        }
        try {
            return doMaintainTombstoneType(new File(this.logDir), ".anr.xcrash", this.anrLogCountMax);
        } catch (Exception e) {
            XCrash.getLogger().e("xcrash", "FileManager maintainAnr failed", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public File createLogFile(String str) {
        String str2 = this.logDir;
        if (str2 == null || !Util.checkAndCreateDir(str2)) {
            return null;
        }
        File file = new File(str);
        File[] listFiles = new File(this.logDir).listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                StringBuilder sb = new StringBuilder();
                sb.append(FileManager.this.placeholderPrefix);
                sb.append("_");
                return str.startsWith(sb.toString()) && str.endsWith(FileManager.this.placeholderCleanSuffix);
            }
        });
        if (listFiles != null) {
            int length = listFiles.length;
            while (length > 0) {
                File file2 = listFiles[length - 1];
                try {
                    if (file2.renameTo(file)) {
                        return file;
                    }
                    file2.delete();
                    length--;
                } catch (Exception e) {
                    XCrash.getLogger().e("xcrash", "FileManager createLogFile by renameTo failed", e);
                }
            }
        }
        try {
            if (file.createNewFile()) {
                return file;
            }
            XCrash.getLogger().e("xcrash", "FileManager createLogFile by createNewFile failed, file already exists");
            return null;
        } catch (Exception e2) {
            XCrash.getLogger().e("xcrash", "FileManager createLogFile by createNewFile failed", e2);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0060 A[SYNTHETIC, Splitter:B:29:0x0060] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0066 A[SYNTHETIC, Splitter:B:34:0x0066] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean appendText(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x0051 }
            java.lang.String r2 = "rws"
            r1.<init>(r13, r2)     // Catch:{ Exception -> 0x0051 }
            long r2 = r1.length()     // Catch:{ Exception -> 0x004c, all -> 0x0049 }
            r13 = 1
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0039
            java.nio.channels.FileChannel r6 = r1.getChannel()     // Catch:{ Exception -> 0x004c, all -> 0x0049 }
            java.nio.channels.FileChannel$MapMode r7 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ Exception -> 0x004c, all -> 0x0049 }
            r8 = 0
            long r10 = r1.length()     // Catch:{ Exception -> 0x004c, all -> 0x0049 }
            java.nio.MappedByteBuffer r0 = r6.map(r7, r8, r10)     // Catch:{ Exception -> 0x004c, all -> 0x0049 }
            long r2 = r1.length()     // Catch:{ Exception -> 0x004c, all -> 0x0049 }
        L_0x0027:
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x0038
            int r6 = (int) r2     // Catch:{ Exception -> 0x004c, all -> 0x0049 }
            int r6 = r6 - r13
            byte r6 = r0.get(r6)     // Catch:{ Exception -> 0x004c, all -> 0x0049 }
            if (r6 == 0) goto L_0x0034
            goto L_0x0038
        L_0x0034:
            r6 = 1
            long r2 = r2 - r6
            goto L_0x0027
        L_0x0038:
            r4 = r2
        L_0x0039:
            r1.seek(r4)     // Catch:{ Exception -> 0x004c, all -> 0x0049 }
            java.lang.String r0 = "UTF-8"
            byte[] r14 = r14.getBytes(r0)     // Catch:{ Exception -> 0x004c, all -> 0x0049 }
            r1.write(r14)     // Catch:{ Exception -> 0x004c, all -> 0x0049 }
            r1.close()     // Catch:{ Exception -> 0x0048 }
        L_0x0048:
            return r13
        L_0x0049:
            r13 = move-exception
            r0 = r1
            goto L_0x0064
        L_0x004c:
            r13 = move-exception
            r0 = r1
            goto L_0x0052
        L_0x004f:
            r13 = move-exception
            goto L_0x0064
        L_0x0051:
            r13 = move-exception
        L_0x0052:
            xcrash.ILogger r14 = xcrash.XCrash.getLogger()     // Catch:{ all -> 0x004f }
            java.lang.String r1 = "xcrash"
            java.lang.String r2 = "FileManager appendText failed"
            r14.e(r1, r2, r13)     // Catch:{ all -> 0x004f }
            r13 = 0
            if (r0 == 0) goto L_0x0063
            r0.close()     // Catch:{ Exception -> 0x0063 }
        L_0x0063:
            return r13
        L_0x0064:
            if (r0 == 0) goto L_0x0069
            r0.close()     // Catch:{ Exception -> 0x0069 }
        L_0x0069:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: xcrash.FileManager.appendText(java.lang.String, java.lang.String):boolean");
    }

    /* access modifiers changed from: package-private */
    public boolean recycleLogFile(File file) {
        if (file == null) {
            return false;
        }
        if (this.logDir == null || this.placeholderCountMax <= 0) {
            try {
                return file.delete();
            } catch (Exception unused) {
                return false;
            }
        } else {
            try {
                File[] listFiles = new File(this.logDir).listFiles(new FilenameFilter() {
                    public boolean accept(File file, String str) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(FileManager.this.placeholderPrefix);
                        sb.append("_");
                        return str.startsWith(sb.toString()) && str.endsWith(FileManager.this.placeholderCleanSuffix);
                    }
                });
                if (listFiles == null || listFiles.length < this.placeholderCountMax) {
                    File file2 = new File(String.format(Locale.US, "%s/%s_%020d%s", new Object[]{this.logDir, this.placeholderPrefix, Long.valueOf((new Date().getTime() * 1000) + ((long) getNextUnique())), this.placeholderDirtySuffix}));
                    if (file.renameTo(file2)) {
                        return cleanTheDirtyFile(file2);
                    }
                    try {
                        return file.delete();
                    } catch (Exception unused2) {
                        return false;
                    }
                } else {
                    try {
                        return file.delete();
                    } catch (Exception unused3) {
                        return false;
                    }
                }
            } catch (Exception e) {
                XCrash.getLogger().e("xcrash", "FileManager recycleLogFile failed", e);
                try {
                    return file.delete();
                } catch (Exception unused4) {
                    return false;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void doMaintain() {
        if (Util.checkAndCreateDir(this.logDir)) {
            File file = new File(this.logDir);
            try {
                doMaintainTombstone(file);
            } catch (Exception e) {
                XCrash.getLogger().e("xcrash", "FileManager doMaintainTombstone failed", e);
            }
            try {
                doMaintainPlaceholder(file);
            } catch (Exception e2) {
                XCrash.getLogger().e("xcrash", "FileManager doMaintainPlaceholder failed", e2);
            }
        }
    }

    private void doMaintainTombstone(File file) {
        doMaintainTombstoneType(file, ".native.xcrash", this.nativeLogCountMax);
        doMaintainTombstoneType(file, ".java.xcrash", this.javaLogCountMax);
        doMaintainTombstoneType(file, ".anr.xcrash", this.anrLogCountMax);
        doMaintainTombstoneType(file, ".trace.xcrash", this.traceLogCountMax);
    }

    private boolean doMaintainTombstoneType(File file, final String str, int i) {
        File[] listFiles = file.listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.startsWith("tombstone_") && str.endsWith(str);
            }
        });
        boolean z = true;
        if (listFiles != null && listFiles.length > i) {
            if (i > 0) {
                Arrays.sort(listFiles, new Comparator<File>() {
                    public int compare(File file, File file2) {
                        return file.getName().compareTo(file2.getName());
                    }
                });
            }
            for (int i2 = 0; i2 < listFiles.length - i; i2++) {
                if (!recycleLogFile(listFiles[i2])) {
                    z = false;
                }
            }
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x008a A[LOOP:0: B:7:0x0020->B:29:0x008a, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x008e A[EDGE_INSN: B:45:0x008e->B:31:0x008e ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doMaintainPlaceholder(java.io.File r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            xcrash.FileManager$7 r2 = new xcrash.FileManager$7
            r2.<init>()
            java.io.File[] r2 = r1.listFiles(r2)
            if (r2 != 0) goto L_0x0010
            return
        L_0x0010:
            xcrash.FileManager$8 r3 = new xcrash.FileManager$8
            r3.<init>()
            java.io.File[] r3 = r1.listFiles(r3)
            if (r3 != 0) goto L_0x001c
            return
        L_0x001c:
            int r4 = r2.length
            int r5 = r3.length
            r6 = 0
            r7 = 0
        L_0x0020:
            int r8 = r0.placeholderCountMax
            if (r4 >= r8) goto L_0x008c
            r8 = 2
            if (r5 <= 0) goto L_0x0038
            int r9 = r5 + -1
            r9 = r3[r9]
            boolean r9 = r0.cleanTheDirtyFile(r9)
            if (r9 == 0) goto L_0x0033
            int r4 = r4 + 1
        L_0x0033:
            int r5 = r5 + -1
        L_0x0035:
            r17 = r7
            goto L_0x0081
        L_0x0038:
            java.io.File r9 = new java.io.File     // Catch:{ Exception -> 0x0035 }
            java.util.Locale r10 = java.util.Locale.US     // Catch:{ Exception -> 0x0035 }
            java.lang.String r11 = "%s/%s_%020d%s"
            r12 = 4
            java.lang.Object[] r12 = new java.lang.Object[r12]     // Catch:{ Exception -> 0x0035 }
            java.lang.String r13 = r0.logDir     // Catch:{ Exception -> 0x0035 }
            r12[r6] = r13     // Catch:{ Exception -> 0x0035 }
            java.lang.String r13 = r0.placeholderPrefix     // Catch:{ Exception -> 0x0035 }
            r14 = 1
            r12[r14] = r13     // Catch:{ Exception -> 0x0035 }
            java.util.Date r13 = new java.util.Date     // Catch:{ Exception -> 0x0035 }
            r13.<init>()     // Catch:{ Exception -> 0x0035 }
            long r13 = r13.getTime()     // Catch:{ Exception -> 0x0035 }
            r15 = 1000(0x3e8, double:4.94E-321)
            long r13 = r13 * r15
            int r15 = r18.getNextUnique()     // Catch:{ Exception -> 0x0035 }
            r17 = r7
            long r6 = (long) r15
            long r13 = r13 + r6
            java.lang.Long r6 = java.lang.Long.valueOf(r13)     // Catch:{ Exception -> 0x0080 }
            r12[r8] = r6     // Catch:{ Exception -> 0x0080 }
            r6 = 3
            java.lang.String r7 = r0.placeholderDirtySuffix     // Catch:{ Exception -> 0x0080 }
            r12[r6] = r7     // Catch:{ Exception -> 0x0080 }
            java.lang.String r6 = java.lang.String.format(r10, r11, r12)     // Catch:{ Exception -> 0x0080 }
            r9.<init>(r6)     // Catch:{ Exception -> 0x0080 }
            boolean r6 = r9.createNewFile()     // Catch:{ Exception -> 0x0080 }
            if (r6 == 0) goto L_0x0081
            boolean r6 = r0.cleanTheDirtyFile(r9)     // Catch:{ Exception -> 0x0080 }
            if (r6 == 0) goto L_0x0081
            int r4 = r4 + 1
            goto L_0x0081
        L_0x0080:
        L_0x0081:
            int r7 = r17 + 1
            int r6 = r0.placeholderCountMax
            int r6 = r6 * 2
            if (r7 <= r6) goto L_0x008a
            goto L_0x008e
        L_0x008a:
            r6 = 0
            goto L_0x0020
        L_0x008c:
            r17 = r7
        L_0x008e:
            if (r7 <= 0) goto L_0x00a2
            xcrash.FileManager$9 r2 = new xcrash.FileManager$9
            r2.<init>()
            java.io.File[] r2 = r1.listFiles(r2)
            xcrash.FileManager$10 r3 = new xcrash.FileManager$10
            r3.<init>()
            java.io.File[] r3 = r1.listFiles(r3)
        L_0x00a2:
            if (r2 == 0) goto L_0x00b8
            int r1 = r2.length
            int r4 = r0.placeholderCountMax
            if (r1 <= r4) goto L_0x00b8
            r1 = 0
        L_0x00aa:
            int r4 = r2.length
            int r5 = r0.placeholderCountMax
            int r4 = r4 - r5
            if (r1 >= r4) goto L_0x00b8
            r4 = r2[r1]
            r4.delete()
            int r1 = r1 + 1
            goto L_0x00aa
        L_0x00b8:
            if (r3 == 0) goto L_0x00c6
            int r1 = r3.length
            r6 = 0
        L_0x00bc:
            if (r6 >= r1) goto L_0x00c6
            r2 = r3[r6]
            r2.delete()
            int r6 = r6 + 1
            goto L_0x00bc
        L_0x00c6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: xcrash.FileManager.doMaintainPlaceholder(java.io.File):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a1, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a2, code lost:
        r4 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a5, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a6, code lost:
        r3 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a9, code lost:
        r4 = r17;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ac, code lost:
        r3 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a5 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:13:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c1 A[SYNTHETIC, Splitter:B:46:0x00c1] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00c7 A[SYNTHETIC, Splitter:B:51:0x00c7] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00cd A[SYNTHETIC, Splitter:B:56:0x00cd] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean cleanTheDirtyFile(java.io.File r17) {
        /*
            r16 = this;
            r1 = r16
            r0 = 1024(0x400, float:1.435E-42)
            r2 = 0
            r3 = 0
            byte[] r4 = new byte[r0]     // Catch:{ Exception -> 0x00b0 }
            java.util.Arrays.fill(r4, r2)     // Catch:{ Exception -> 0x00b0 }
            int r5 = r1.placeholderSizeKb     // Catch:{ Exception -> 0x00b0 }
            long r5 = (long) r5     // Catch:{ Exception -> 0x00b0 }
            long r7 = r17.length()     // Catch:{ Exception -> 0x00b0 }
            int r9 = r1.placeholderSizeKb     // Catch:{ Exception -> 0x00b0 }
            int r9 = r9 * 1024
            long r9 = (long) r9     // Catch:{ Exception -> 0x00b0 }
            r11 = 0
            r13 = 1024(0x400, double:5.06E-321)
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 <= 0) goto L_0x002a
            long r5 = r7 / r13
            long r9 = r7 % r13
            int r0 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x002a
            r9 = 1
            long r5 = r5 + r9
        L_0x002a:
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x00b0 }
            java.io.File r0 = r17.getAbsoluteFile()     // Catch:{ Exception -> 0x00b0 }
            r9.<init>(r0, r2)     // Catch:{ Exception -> 0x00b0 }
            r0 = 0
        L_0x0034:
            long r2 = (long) r0
            int r15 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r15 >= 0) goto L_0x0057
            int r0 = r0 + 1
            long r2 = (long) r0
            int r15 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r15 != 0) goto L_0x004e
            long r2 = r7 % r13
            int r15 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r15 == 0) goto L_0x004e
            long r2 = r7 % r13
            int r3 = (int) r2     // Catch:{ Exception -> 0x0052, all -> 0x00a5 }
            r2 = 0
            r9.write(r4, r2, r3)     // Catch:{ Exception -> 0x0052, all -> 0x00a5 }
            goto L_0x0034
        L_0x004e:
            r9.write(r4)     // Catch:{ Exception -> 0x0052, all -> 0x00a5 }
            goto L_0x0034
        L_0x0052:
            r0 = move-exception
            r4 = r17
            r3 = r9
            goto L_0x00b3
        L_0x0057:
            r9.flush()     // Catch:{ Exception -> 0x00a8, all -> 0x00a5 }
            java.util.Locale r0 = java.util.Locale.US     // Catch:{ Exception -> 0x00a8, all -> 0x00a5 }
            java.lang.String r2 = "%s/%s_%020d%s"
            r3 = 4
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x00a8, all -> 0x00a5 }
            java.lang.String r4 = r1.logDir     // Catch:{ Exception -> 0x00a8, all -> 0x00a5 }
            r5 = 0
            r3[r5] = r4     // Catch:{ Exception -> 0x00a1, all -> 0x00a5 }
            java.lang.String r4 = r1.placeholderPrefix     // Catch:{ Exception -> 0x00a1, all -> 0x00a5 }
            r6 = 1
            r3[r6] = r4     // Catch:{ Exception -> 0x00a1, all -> 0x00a5 }
            r4 = 2
            java.util.Date r6 = new java.util.Date     // Catch:{ Exception -> 0x00a1, all -> 0x00a5 }
            r6.<init>()     // Catch:{ Exception -> 0x00a1, all -> 0x00a5 }
            long r6 = r6.getTime()     // Catch:{ Exception -> 0x00a1, all -> 0x00a5 }
            r10 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 * r10
            int r8 = r16.getNextUnique()     // Catch:{ Exception -> 0x00a1, all -> 0x00a5 }
            long r10 = (long) r8     // Catch:{ Exception -> 0x00a1, all -> 0x00a5 }
            long r6 = r6 + r10
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Exception -> 0x00a1, all -> 0x00a5 }
            r3[r4] = r6     // Catch:{ Exception -> 0x00a1, all -> 0x00a5 }
            r4 = 3
            java.lang.String r6 = r1.placeholderCleanSuffix     // Catch:{ Exception -> 0x00a1, all -> 0x00a5 }
            r3[r4] = r6     // Catch:{ Exception -> 0x00a1, all -> 0x00a5 }
            java.lang.String r0 = java.lang.String.format(r0, r2, r3)     // Catch:{ Exception -> 0x00a1, all -> 0x00a5 }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x00a1, all -> 0x00a5 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x00a1, all -> 0x00a5 }
            r4 = r17
            boolean r2 = r4.renameTo(r2)     // Catch:{ Exception -> 0x009f, all -> 0x00a5 }
            r9.close()     // Catch:{ Exception -> 0x009d }
            goto L_0x00c5
        L_0x009d:
            goto L_0x00c5
        L_0x009f:
            r0 = move-exception
            goto L_0x00ac
        L_0x00a1:
            r0 = move-exception
            r4 = r17
            goto L_0x00ac
        L_0x00a5:
            r0 = move-exception
            r3 = r9
            goto L_0x00cb
        L_0x00a8:
            r0 = move-exception
            r4 = r17
            r5 = 0
        L_0x00ac:
            r3 = r9
            goto L_0x00b4
        L_0x00ae:
            r0 = move-exception
            goto L_0x00cb
        L_0x00b0:
            r0 = move-exception
            r4 = r17
        L_0x00b3:
            r5 = 0
        L_0x00b4:
            xcrash.ILogger r2 = xcrash.XCrash.getLogger()     // Catch:{ all -> 0x00ae }
            java.lang.String r6 = "xcrash"
            java.lang.String r7 = "FileManager cleanTheDirtyFile failed"
            r2.e(r6, r7, r0)     // Catch:{ all -> 0x00ae }
            if (r3 == 0) goto L_0x00c4
            r3.close()     // Catch:{ Exception -> 0x00c4 }
        L_0x00c4:
            r2 = 0
        L_0x00c5:
            if (r2 != 0) goto L_0x00ca
            r17.delete()     // Catch:{ Exception -> 0x00ca }
        L_0x00ca:
            return r2
        L_0x00cb:
            if (r3 == 0) goto L_0x00d0
            r3.close()     // Catch:{ Exception -> 0x00d0 }
        L_0x00d0:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: xcrash.FileManager.cleanTheDirtyFile(java.io.File):boolean");
    }

    private int getNextUnique() {
        int incrementAndGet = this.unique.incrementAndGet();
        if (incrementAndGet >= 999) {
            this.unique.set(0);
        }
        return incrementAndGet;
    }
}
