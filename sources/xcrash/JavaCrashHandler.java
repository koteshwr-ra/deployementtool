package xcrash;

import android.os.Process;
import android.text.TextUtils;
import androidx.core.os.EnvironmentCompat;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

class JavaCrashHandler implements Thread.UncaughtExceptionHandler {
    private static final JavaCrashHandler instance = new JavaCrashHandler();
    private String appId;
    private String appVersion;
    private ICrashCallback callback;
    private Thread.UncaughtExceptionHandler defaultHandler = null;
    private boolean dumpAllThreads;
    private int dumpAllThreadsCountMax;
    private String[] dumpAllThreadsWhiteList;
    private boolean dumpFds;
    private boolean dumpNetworkInfo;
    private String logDir;
    private int logcatEventsLines;
    private int logcatMainLines;
    private int logcatSystemLines;
    private int pid;
    private String processName;
    private boolean rethrow;
    private final Date startTime = new Date();

    private JavaCrashHandler() {
    }

    static JavaCrashHandler getInstance() {
        return instance;
    }

    /* access modifiers changed from: package-private */
    public void initialize(int i, String str, String str2, String str3, String str4, boolean z, int i2, int i3, int i4, boolean z2, boolean z3, boolean z4, int i5, String[] strArr, ICrashCallback iCrashCallback) {
        this.pid = i;
        if (TextUtils.isEmpty(str)) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        this.processName = str;
        this.appId = str2;
        this.appVersion = str3;
        this.rethrow = z;
        this.logDir = str4;
        this.logcatSystemLines = i2;
        this.logcatEventsLines = i3;
        this.logcatMainLines = i4;
        this.dumpFds = z2;
        this.dumpNetworkInfo = z3;
        this.dumpAllThreads = z4;
        this.dumpAllThreadsCountMax = i5;
        this.dumpAllThreadsWhiteList = strArr;
        this.callback = iCrashCallback;
        this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        try {
            Thread.setDefaultUncaughtExceptionHandler(this);
        } catch (Exception e) {
            XCrash.getLogger().e("xcrash", "JavaCrashHandler setDefaultUncaughtExceptionHandler failed", e);
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.defaultHandler;
        if (uncaughtExceptionHandler != null) {
            Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
        try {
            handleException(thread, th);
        } catch (Exception e) {
            XCrash.getLogger().e("xcrash", "JavaCrashHandler handleException failed", e);
        }
        if (this.rethrow) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.defaultHandler;
            if (uncaughtExceptionHandler2 != null) {
                uncaughtExceptionHandler2.uncaughtException(thread, th);
                return;
            }
            return;
        }
        ActivityMonitor.getInstance().finishAllActivities();
        Process.killProcess(this.pid);
        System.exit(10);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.io.RandomAccessFile} */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x011f A[SYNTHETIC, Splitter:B:50:0x011f] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0129 A[SYNTHETIC, Splitter:B:56:0x0129] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleException(java.lang.Thread r12, java.lang.Throwable r13) {
        /*
            r11 = this;
            java.lang.String r0 = "xcrash"
            java.util.Date r1 = new java.util.Date
            r1.<init>()
            xcrash.NativeHandler r2 = xcrash.NativeHandler.getInstance()
            r2.notifyJavaCrashed()
            xcrash.AnrHandler r2 = xcrash.AnrHandler.getInstance()
            r2.notifyJavaCrashed()
            r2 = 0
            java.util.Locale r3 = java.util.Locale.US     // Catch:{ Exception -> 0x0054 }
            java.lang.String r4 = "%s/%s_%020d_%s__%s%s"
            r5 = 6
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x0054 }
            r6 = 0
            java.lang.String r7 = r11.logDir     // Catch:{ Exception -> 0x0054 }
            r5[r6] = r7     // Catch:{ Exception -> 0x0054 }
            r6 = 1
            java.lang.String r7 = "tombstone"
            r5[r6] = r7     // Catch:{ Exception -> 0x0054 }
            r6 = 2
            java.util.Date r7 = r11.startTime     // Catch:{ Exception -> 0x0054 }
            long r7 = r7.getTime()     // Catch:{ Exception -> 0x0054 }
            r9 = 1000(0x3e8, double:4.94E-321)
            long r7 = r7 * r9
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ Exception -> 0x0054 }
            r5[r6] = r7     // Catch:{ Exception -> 0x0054 }
            r6 = 3
            java.lang.String r7 = r11.appVersion     // Catch:{ Exception -> 0x0054 }
            r5[r6] = r7     // Catch:{ Exception -> 0x0054 }
            r6 = 4
            java.lang.String r7 = r11.processName     // Catch:{ Exception -> 0x0054 }
            r5[r6] = r7     // Catch:{ Exception -> 0x0054 }
            r6 = 5
            java.lang.String r7 = ".java.xcrash"
            r5[r6] = r7     // Catch:{ Exception -> 0x0054 }
            java.lang.String r3 = java.lang.String.format(r3, r4, r5)     // Catch:{ Exception -> 0x0054 }
            xcrash.FileManager r4 = xcrash.FileManager.getInstance()     // Catch:{ Exception -> 0x0054 }
            java.io.File r3 = r4.createLogFile(r3)     // Catch:{ Exception -> 0x0054 }
            goto L_0x005f
        L_0x0054:
            r3 = move-exception
            xcrash.ILogger r4 = xcrash.XCrash.getLogger()
            java.lang.String r5 = "JavaCrashHandler createLogFile failed"
            r4.e(r0, r5, r3)
            r3 = r2
        L_0x005f:
            java.lang.String r13 = r11.getEmergency(r1, r12, r13)     // Catch:{ Exception -> 0x0064 }
            goto L_0x006f
        L_0x0064:
            r13 = move-exception
            xcrash.ILogger r1 = xcrash.XCrash.getLogger()
            java.lang.String r4 = "JavaCrashHandler getEmergency failed"
            r1.e(r0, r4, r13)
            r13 = r2
        L_0x006f:
            if (r3 == 0) goto L_0x012d
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x0112, all -> 0x0110 }
            java.lang.String r4 = "rws"
            r1.<init>(r3, r4)     // Catch:{ Exception -> 0x0112, all -> 0x0110 }
            java.lang.String r4 = "UTF-8"
            if (r13 == 0) goto L_0x0087
            byte[] r5 = r13.getBytes(r4)     // Catch:{ Exception -> 0x0084 }
            r1.write(r5)     // Catch:{ Exception -> 0x0084 }
            goto L_0x0087
        L_0x0084:
            r12 = move-exception
            goto L_0x0114
        L_0x0087:
            int r13 = r11.logcatMainLines     // Catch:{ Exception -> 0x010d }
            if (r13 > 0) goto L_0x0093
            int r13 = r11.logcatSystemLines     // Catch:{ Exception -> 0x010d }
            if (r13 > 0) goto L_0x0093
            int r13 = r11.logcatEventsLines     // Catch:{ Exception -> 0x010d }
            if (r13 <= 0) goto L_0x00a4
        L_0x0093:
            int r13 = r11.logcatMainLines     // Catch:{ Exception -> 0x010d }
            int r5 = r11.logcatSystemLines     // Catch:{ Exception -> 0x010d }
            int r6 = r11.logcatEventsLines     // Catch:{ Exception -> 0x010d }
            java.lang.String r13 = xcrash.Util.getLogcat(r13, r5, r6)     // Catch:{ Exception -> 0x010d }
            byte[] r13 = r13.getBytes(r4)     // Catch:{ Exception -> 0x010d }
            r1.write(r13)     // Catch:{ Exception -> 0x010d }
        L_0x00a4:
            boolean r13 = r11.dumpFds     // Catch:{ Exception -> 0x010d }
            if (r13 == 0) goto L_0x00b3
            java.lang.String r13 = xcrash.Util.getFds()     // Catch:{ Exception -> 0x010d }
            byte[] r13 = r13.getBytes(r4)     // Catch:{ Exception -> 0x010d }
            r1.write(r13)     // Catch:{ Exception -> 0x010d }
        L_0x00b3:
            boolean r13 = r11.dumpNetworkInfo     // Catch:{ Exception -> 0x010d }
            if (r13 == 0) goto L_0x00c2
            java.lang.String r13 = xcrash.Util.getNetworkInfo()     // Catch:{ Exception -> 0x010d }
            byte[] r13 = r13.getBytes(r4)     // Catch:{ Exception -> 0x010d }
            r1.write(r13)     // Catch:{ Exception -> 0x010d }
        L_0x00c2:
            java.lang.String r13 = xcrash.Util.getMemoryInfo()     // Catch:{ Exception -> 0x010d }
            byte[] r13 = r13.getBytes(r4)     // Catch:{ Exception -> 0x010d }
            r1.write(r13)     // Catch:{ Exception -> 0x010d }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x010d }
            r13.<init>()     // Catch:{ Exception -> 0x010d }
            java.lang.String r5 = "foreground:\n"
            r13.append(r5)     // Catch:{ Exception -> 0x010d }
            xcrash.ActivityMonitor r5 = xcrash.ActivityMonitor.getInstance()     // Catch:{ Exception -> 0x010d }
            boolean r5 = r5.isApplicationForeground()     // Catch:{ Exception -> 0x010d }
            if (r5 == 0) goto L_0x00e4
            java.lang.String r5 = "yes"
            goto L_0x00e6
        L_0x00e4:
            java.lang.String r5 = "no"
        L_0x00e6:
            r13.append(r5)     // Catch:{ Exception -> 0x010d }
            java.lang.String r5 = "\n\n"
            r13.append(r5)     // Catch:{ Exception -> 0x010d }
            java.lang.String r13 = r13.toString()     // Catch:{ Exception -> 0x010d }
            byte[] r13 = r13.getBytes(r4)     // Catch:{ Exception -> 0x010d }
            r1.write(r13)     // Catch:{ Exception -> 0x010d }
            boolean r13 = r11.dumpAllThreads     // Catch:{ Exception -> 0x010d }
            if (r13 == 0) goto L_0x0108
            java.lang.String r12 = r11.getOtherThreadsInfo(r12)     // Catch:{ Exception -> 0x010d }
            byte[] r12 = r12.getBytes(r4)     // Catch:{ Exception -> 0x010d }
            r1.write(r12)     // Catch:{ Exception -> 0x010d }
        L_0x0108:
            r1.close()     // Catch:{ Exception -> 0x010b }
        L_0x010b:
            r13 = r2
            goto L_0x012d
        L_0x010d:
            r12 = move-exception
            r13 = r2
            goto L_0x0114
        L_0x0110:
            r12 = move-exception
            goto L_0x0127
        L_0x0112:
            r12 = move-exception
            r1 = r2
        L_0x0114:
            xcrash.ILogger r4 = xcrash.XCrash.getLogger()     // Catch:{ all -> 0x0125 }
            java.lang.String r5 = "JavaCrashHandler write log file failed"
            r4.e(r0, r5, r12)     // Catch:{ all -> 0x0125 }
            if (r1 == 0) goto L_0x012d
            r1.close()     // Catch:{ Exception -> 0x0123 }
            goto L_0x012d
        L_0x0123:
            goto L_0x012d
        L_0x0125:
            r12 = move-exception
            r2 = r1
        L_0x0127:
            if (r2 == 0) goto L_0x012c
            r2.close()     // Catch:{ Exception -> 0x012c }
        L_0x012c:
            throw r12
        L_0x012d:
            xcrash.ICrashCallback r12 = r11.callback
            if (r12 == 0) goto L_0x013b
            if (r3 != 0) goto L_0x0134
            goto L_0x0138
        L_0x0134:
            java.lang.String r2 = r3.getAbsolutePath()     // Catch:{ Exception -> 0x013b }
        L_0x0138:
            r12.onCrash(r2, r13)     // Catch:{ Exception -> 0x013b }
        L_0x013b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: xcrash.JavaCrashHandler.handleException(java.lang.Thread, java.lang.Throwable):void");
    }

    private String getEmergency(Date date, Thread thread, Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String stringWriter2 = stringWriter.toString();
        return Util.getLogHeader(this.startTime, date, "java", this.appId, this.appVersion) + "pid: " + this.pid + ", tid: " + Process.myTid() + ", name: " + thread.getName() + "  >>> " + this.processName + " <<<\n\njava stacktrace:\n" + stringWriter2 + StringUtils.LF;
    }

    private String getOtherThreadsInfo(Thread thread) {
        ArrayList arrayList;
        if (this.dumpAllThreadsWhiteList != null) {
            arrayList = new ArrayList();
            for (String compile : this.dumpAllThreadsWhiteList) {
                try {
                    arrayList.add(Pattern.compile(compile));
                } catch (Exception e) {
                    XCrash.getLogger().w("xcrash", "JavaCrashHandler pattern compile failed", e);
                }
            }
        } else {
            arrayList = null;
        }
        StringBuilder sb = new StringBuilder();
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (Map.Entry next : allStackTraces.entrySet()) {
            Thread thread2 = (Thread) next.getKey();
            StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) next.getValue();
            if (!thread2.getName().equals(thread.getName()) && (arrayList == null || matchThreadName(arrayList, thread2.getName()))) {
                i2++;
                int i4 = this.dumpAllThreadsCountMax;
                if (i4 <= 0 || i < i4) {
                    sb.append("--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\n");
                    sb.append("pid: ");
                    sb.append(this.pid);
                    sb.append(", tid: ");
                    sb.append(thread2.getId());
                    sb.append(", name: ");
                    sb.append(thread2.getName());
                    sb.append("  >>> ");
                    sb.append(this.processName);
                    sb.append(" <<<\n");
                    sb.append(StringUtils.LF);
                    sb.append("java stacktrace:\n");
                    for (StackTraceElement stackTraceElement : stackTraceElementArr) {
                        sb.append("    at ");
                        sb.append(stackTraceElement.toString());
                        sb.append(StringUtils.LF);
                    }
                    sb.append(StringUtils.LF);
                    i++;
                } else {
                    i3++;
                }
            }
        }
        if (allStackTraces.size() > 1) {
            if (i == 0) {
                sb.append("--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\n");
            }
            sb.append("total JVM threads (exclude the crashed thread): ");
            sb.append(allStackTraces.size() - 1);
            sb.append(StringUtils.LF);
            if (arrayList != null) {
                sb.append("JVM threads matched whitelist: ");
                sb.append(i2);
                sb.append(StringUtils.LF);
            }
            if (this.dumpAllThreadsCountMax > 0) {
                sb.append("JVM threads ignored by max count limit: ");
                sb.append(i3);
                sb.append(StringUtils.LF);
            }
            sb.append("dumped JVM threads:");
            sb.append(i);
            sb.append(StringUtils.LF);
            sb.append("+++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++\n");
        }
        return sb.toString();
    }

    private boolean matchThreadName(ArrayList<Pattern> arrayList, String str) {
        Iterator<Pattern> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().matcher(str).matches()) {
                return true;
            }
        }
        return false;
    }
}
