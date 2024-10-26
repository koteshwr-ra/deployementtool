package xcrash;

import android.content.Context;
import android.os.Build;
import android.os.FileObserver;
import android.text.TextUtils;
import androidx.core.os.EnvironmentCompat;
import java.util.Date;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

class AnrHandler {
    private static final AnrHandler instance = new AnrHandler();
    private final long anrTimeoutMs = 15000;
    private String appId;
    private String appVersion;
    private ICrashCallback callback;
    private boolean checkProcessState;
    private Context ctx;
    private boolean dumpFds;
    private boolean dumpNetworkInfo;
    private FileObserver fileObserver = null;
    private long lastTime = 0;
    private String logDir;
    private int logcatEventsLines;
    private int logcatMainLines;
    private int logcatSystemLines;
    private final Pattern patPidTime = Pattern.compile("^-----\\spid\\s(\\d+)\\sat\\s(.*)\\s-----$");
    private final Pattern patProcessName = Pattern.compile("^Cmd\\sline:\\s+(.*)$");
    private int pid;
    private String processName;
    private final Date startTime = new Date();

    private AnrHandler() {
    }

    static AnrHandler getInstance() {
        return instance;
    }

    /* access modifiers changed from: package-private */
    public void initialize(Context context, int i, String str, String str2, String str3, String str4, boolean z, int i2, int i3, int i4, boolean z2, boolean z3, ICrashCallback iCrashCallback) {
        if (Build.VERSION.SDK_INT < 21) {
            this.ctx = context;
            this.pid = i;
            if (TextUtils.isEmpty(str)) {
                str = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            this.processName = str;
            this.appId = str2;
            this.appVersion = str3;
            this.logDir = str4;
            this.checkProcessState = z;
            this.logcatSystemLines = i2;
            this.logcatEventsLines = i3;
            this.logcatMainLines = i4;
            this.dumpFds = z2;
            this.dumpNetworkInfo = z3;
            this.callback = iCrashCallback;
            AnonymousClass1 r3 = new FileObserver("/data/anr/", 8) {
                public void onEvent(int i, String str) {
                    if (str != null) {
                        try {
                            String str2 = "/data/anr/" + str;
                            if (str2.contains("trace")) {
                                AnrHandler.this.handleAnr(str2);
                            }
                        } catch (Exception e) {
                            XCrash.getLogger().e("xcrash", "AnrHandler fileObserver onEvent failed", e);
                        }
                    }
                }
            };
            this.fileObserver = r3;
            try {
                r3.startWatching();
            } catch (Exception e) {
                this.fileObserver = null;
                XCrash.getLogger().e("xcrash", "AnrHandler fileObserver startWatching failed", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyJavaCrashed() {
        FileObserver fileObserver2 = this.fileObserver;
        if (fileObserver2 != null) {
            try {
                fileObserver2.stopWatching();
            } catch (Exception e) {
                XCrash.getLogger().e("xcrash", "AnrHandler fileObserver stopWatching failed", e);
            } catch (Throwable th) {
                this.fileObserver = null;
                throw th;
            }
            this.fileObserver = null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: java.io.RandomAccessFile} */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v12 */
    /* JADX WARNING: type inference failed for: r2v15 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x010e A[SYNTHETIC, Splitter:B:58:0x010e] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0118 A[SYNTHETIC, Splitter:B:64:0x0118] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleAnr(java.lang.String r12) {
        /*
            r11 = this;
            java.lang.String r0 = "xcrash"
            java.util.Date r1 = new java.util.Date
            r1.<init>()
            long r2 = r1.getTime()
            long r4 = r11.lastTime
            long r2 = r2 - r4
            r4 = 15000(0x3a98, double:7.411E-320)
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x0015
            return
        L_0x0015:
            boolean r2 = r11.checkProcessState
            if (r2 == 0) goto L_0x0022
            android.content.Context r2 = r11.ctx
            boolean r2 = xcrash.Util.checkProcessAnrState(r2, r4)
            if (r2 != 0) goto L_0x0022
            return
        L_0x0022:
            long r2 = r1.getTime()
            java.lang.String r12 = r11.getTrace(r12, r2)
            boolean r2 = android.text.TextUtils.isEmpty(r12)
            if (r2 == 0) goto L_0x0031
            return
        L_0x0031:
            long r2 = r1.getTime()
            r11.lastTime = r2
            xcrash.FileManager r2 = xcrash.FileManager.getInstance()
            boolean r2 = r2.maintainAnr()
            if (r2 != 0) goto L_0x0042
            return
        L_0x0042:
            r2 = 0
            java.lang.String r12 = r11.getEmergency(r1, r12)     // Catch:{ Exception -> 0x0048 }
            goto L_0x0053
        L_0x0048:
            r12 = move-exception
            xcrash.ILogger r3 = xcrash.XCrash.getLogger()
            java.lang.String r4 = "AnrHandler getEmergency failed"
            r3.e(r0, r4, r12)
            r12 = r2
        L_0x0053:
            java.util.Locale r3 = java.util.Locale.US     // Catch:{ Exception -> 0x008f }
            java.lang.String r4 = "%s/%s_%020d_%s__%s%s"
            r5 = 6
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x008f }
            r6 = 0
            java.lang.String r7 = r11.logDir     // Catch:{ Exception -> 0x008f }
            r5[r6] = r7     // Catch:{ Exception -> 0x008f }
            r6 = 1
            java.lang.String r7 = "tombstone"
            r5[r6] = r7     // Catch:{ Exception -> 0x008f }
            r6 = 2
            long r7 = r1.getTime()     // Catch:{ Exception -> 0x008f }
            r9 = 1000(0x3e8, double:4.94E-321)
            long r7 = r7 * r9
            java.lang.Long r1 = java.lang.Long.valueOf(r7)     // Catch:{ Exception -> 0x008f }
            r5[r6] = r1     // Catch:{ Exception -> 0x008f }
            r1 = 3
            java.lang.String r6 = r11.appVersion     // Catch:{ Exception -> 0x008f }
            r5[r1] = r6     // Catch:{ Exception -> 0x008f }
            r1 = 4
            java.lang.String r6 = r11.processName     // Catch:{ Exception -> 0x008f }
            r5[r1] = r6     // Catch:{ Exception -> 0x008f }
            r1 = 5
            java.lang.String r6 = ".anr.xcrash"
            r5[r1] = r6     // Catch:{ Exception -> 0x008f }
            java.lang.String r1 = java.lang.String.format(r3, r4, r5)     // Catch:{ Exception -> 0x008f }
            xcrash.FileManager r3 = xcrash.FileManager.getInstance()     // Catch:{ Exception -> 0x008f }
            java.io.File r1 = r3.createLogFile(r1)     // Catch:{ Exception -> 0x008f }
            goto L_0x009a
        L_0x008f:
            r1 = move-exception
            xcrash.ILogger r3 = xcrash.XCrash.getLogger()
            java.lang.String r4 = "AnrHandler createLogFile failed"
            r3.e(r0, r4, r1)
            r1 = r2
        L_0x009a:
            if (r1 == 0) goto L_0x011c
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            java.lang.String r4 = "rws"
            r3.<init>(r1, r4)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            java.lang.String r4 = "UTF-8"
            if (r12 == 0) goto L_0x00b1
            byte[] r5 = r12.getBytes(r4)     // Catch:{ Exception -> 0x00af }
            r3.write(r5)     // Catch:{ Exception -> 0x00af }
            goto L_0x00b1
        L_0x00af:
            r4 = move-exception
            goto L_0x0103
        L_0x00b1:
            int r12 = r11.logcatMainLines     // Catch:{ Exception -> 0x00fc }
            if (r12 > 0) goto L_0x00bd
            int r12 = r11.logcatSystemLines     // Catch:{ Exception -> 0x00fc }
            if (r12 > 0) goto L_0x00bd
            int r12 = r11.logcatEventsLines     // Catch:{ Exception -> 0x00fc }
            if (r12 <= 0) goto L_0x00ce
        L_0x00bd:
            int r12 = r11.logcatMainLines     // Catch:{ Exception -> 0x00fc }
            int r5 = r11.logcatSystemLines     // Catch:{ Exception -> 0x00fc }
            int r6 = r11.logcatEventsLines     // Catch:{ Exception -> 0x00fc }
            java.lang.String r12 = xcrash.Util.getLogcat(r12, r5, r6)     // Catch:{ Exception -> 0x00fc }
            byte[] r12 = r12.getBytes(r4)     // Catch:{ Exception -> 0x00fc }
            r3.write(r12)     // Catch:{ Exception -> 0x00fc }
        L_0x00ce:
            boolean r12 = r11.dumpFds     // Catch:{ Exception -> 0x00fc }
            if (r12 == 0) goto L_0x00dd
            java.lang.String r12 = xcrash.Util.getFds()     // Catch:{ Exception -> 0x00fc }
            byte[] r12 = r12.getBytes(r4)     // Catch:{ Exception -> 0x00fc }
            r3.write(r12)     // Catch:{ Exception -> 0x00fc }
        L_0x00dd:
            boolean r12 = r11.dumpNetworkInfo     // Catch:{ Exception -> 0x00fc }
            if (r12 == 0) goto L_0x00ec
            java.lang.String r12 = xcrash.Util.getNetworkInfo()     // Catch:{ Exception -> 0x00fc }
            byte[] r12 = r12.getBytes(r4)     // Catch:{ Exception -> 0x00fc }
            r3.write(r12)     // Catch:{ Exception -> 0x00fc }
        L_0x00ec:
            java.lang.String r12 = xcrash.Util.getMemoryInfo()     // Catch:{ Exception -> 0x00fc }
            byte[] r12 = r12.getBytes(r4)     // Catch:{ Exception -> 0x00fc }
            r3.write(r12)     // Catch:{ Exception -> 0x00fc }
            r3.close()     // Catch:{ Exception -> 0x00fa }
        L_0x00fa:
            r12 = r2
            goto L_0x011c
        L_0x00fc:
            r4 = move-exception
            r12 = r2
            goto L_0x0103
        L_0x00ff:
            r12 = move-exception
            goto L_0x0116
        L_0x0101:
            r4 = move-exception
            r3 = r2
        L_0x0103:
            xcrash.ILogger r5 = xcrash.XCrash.getLogger()     // Catch:{ all -> 0x0114 }
            java.lang.String r6 = "AnrHandler write log file failed"
            r5.e(r0, r6, r4)     // Catch:{ all -> 0x0114 }
            if (r3 == 0) goto L_0x011c
            r3.close()     // Catch:{ Exception -> 0x0112 }
            goto L_0x011c
        L_0x0112:
            goto L_0x011c
        L_0x0114:
            r12 = move-exception
            r2 = r3
        L_0x0116:
            if (r2 == 0) goto L_0x011b
            r2.close()     // Catch:{ Exception -> 0x011b }
        L_0x011b:
            throw r12
        L_0x011c:
            xcrash.ICrashCallback r0 = r11.callback
            if (r0 == 0) goto L_0x012a
            if (r1 != 0) goto L_0x0123
            goto L_0x0127
        L_0x0123:
            java.lang.String r2 = r1.getAbsolutePath()     // Catch:{ Exception -> 0x012a }
        L_0x0127:
            r0.onCrash(r2, r12)     // Catch:{ Exception -> 0x012a }
        L_0x012a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: xcrash.AnrHandler.handleAnr(java.lang.String):void");
    }

    private String getEmergency(Date date, String str) {
        return Util.getLogHeader(this.startTime, date, "anr", this.appId, this.appVersion) + "pid: " + this.pid + "  >>> " + this.processName + " <<<\n\n" + "--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---" + StringUtils.LF + str + StringUtils.LF + "+++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++" + "\n\n";
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:0x00c8 A[SYNTHETIC, Splitter:B:58:0x00c8] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00cf A[SYNTHETIC, Splitter:B:65:0x00cf] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getTrace(java.lang.String r12, long r13) {
        /*
            r11 = this;
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat
            java.util.Locale r1 = java.util.Locale.US
            java.lang.String r2 = "yyyy-MM-dd HH:mm:ss"
            r0.<init>(r2, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00cc, all -> 0x00c5 }
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ Exception -> 0x00cc, all -> 0x00c5 }
            r4.<init>(r12)     // Catch:{ Exception -> 0x00cc, all -> 0x00c5 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x00cc, all -> 0x00c5 }
            r12 = 1
            r4 = 0
        L_0x001b:
            java.lang.String r5 = r3.readLine()     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            if (r5 == 0) goto L_0x00b8
            r6 = 10
            if (r4 != 0) goto L_0x00a5
            java.lang.String r7 = "----- pid "
            boolean r7 = r5.startsWith(r7)     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            if (r7 == 0) goto L_0x00a5
            java.util.regex.Pattern r7 = r11.patPidTime     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            java.util.regex.Matcher r5 = r7.matcher(r5)     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            boolean r7 = r5.find()     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            if (r7 == 0) goto L_0x001b
            int r7 = r5.groupCount()     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            r8 = 2
            if (r7 == r8) goto L_0x0041
            goto L_0x001b
        L_0x0041:
            java.lang.String r7 = r5.group(r12)     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            java.lang.String r5 = r5.group(r8)     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            if (r7 == 0) goto L_0x001b
            if (r5 != 0) goto L_0x004e
            goto L_0x001b
        L_0x004e:
            int r8 = r11.pid     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            if (r8 == r7) goto L_0x0057
            goto L_0x001b
        L_0x0057:
            java.util.Date r5 = r0.parse(r5)     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            if (r5 != 0) goto L_0x005e
            goto L_0x001b
        L_0x005e:
            long r7 = r5.getTime()     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            long r7 = r7 - r13
            long r7 = java.lang.Math.abs(r7)     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            r9 = 15000(0x3a98, double:7.411E-320)
            int r5 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r5 <= 0) goto L_0x006e
            goto L_0x001b
        L_0x006e:
            java.lang.String r5 = r3.readLine()     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            if (r5 != 0) goto L_0x0075
            goto L_0x00b8
        L_0x0075:
            java.util.regex.Pattern r7 = r11.patProcessName     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            java.util.regex.Matcher r7 = r7.matcher(r5)     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            boolean r8 = r7.find()     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            if (r8 == 0) goto L_0x001b
            int r8 = r7.groupCount()     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            if (r8 == r12) goto L_0x0088
            goto L_0x001b
        L_0x0088:
            java.lang.String r7 = r7.group(r12)     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            if (r7 == 0) goto L_0x001b
            java.lang.String r8 = r11.processName     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            boolean r7 = r7.equals(r8)     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            if (r7 != 0) goto L_0x0097
            goto L_0x001b
        L_0x0097:
            r1.append(r5)     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            r1.append(r6)     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            java.lang.String r4 = "Mode: Watching /data/anr/*\n"
            r1.append(r4)     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            r4 = 1
            goto L_0x001b
        L_0x00a5:
            if (r4 == 0) goto L_0x001b
            java.lang.String r7 = "----- end "
            boolean r7 = r5.startsWith(r7)     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            if (r7 == 0) goto L_0x00b0
            goto L_0x00b8
        L_0x00b0:
            r1.append(r5)     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            r1.append(r6)     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            goto L_0x001b
        L_0x00b8:
            java.lang.String r12 = r1.toString()     // Catch:{ Exception -> 0x00c3, all -> 0x00c0 }
            r3.close()     // Catch:{ Exception -> 0x00bf }
        L_0x00bf:
            return r12
        L_0x00c0:
            r12 = move-exception
            r2 = r3
            goto L_0x00c6
        L_0x00c3:
            goto L_0x00cd
        L_0x00c5:
            r12 = move-exception
        L_0x00c6:
            if (r2 == 0) goto L_0x00cb
            r2.close()     // Catch:{ Exception -> 0x00cb }
        L_0x00cb:
            throw r12
        L_0x00cc:
            r3 = r2
        L_0x00cd:
            if (r3 == 0) goto L_0x00d2
            r3.close()     // Catch:{ Exception -> 0x00d2 }
        L_0x00d2:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: xcrash.AnrHandler.getTrace(java.lang.String, long):java.lang.String");
    }
}
