package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import android.os.Build;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.b;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import com.tencent.smtt.utils.TbsLog;
import java.io.File;
import org.apache.commons.lang3.time.DateUtils;

/* compiled from: BUGLY */
public class NativeCrashHandler implements a {
    private static NativeCrashHandler a = null;
    private static int b = 1;
    private static boolean m = false;
    private static boolean n = false;
    /* access modifiers changed from: private */
    public static boolean p = true;
    /* access modifiers changed from: private */
    public final Context c;
    private final com.tencent.bugly.crashreport.common.info.a d;
    private final w e;
    /* access modifiers changed from: private */
    public NativeExceptionHandler f;
    /* access modifiers changed from: private */
    public String g;
    private final boolean h;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private boolean l = false;
    /* access modifiers changed from: private */
    public b o;

    /* access modifiers changed from: protected */
    public native boolean appendNativeLog(String str, String str2, String str3);

    /* access modifiers changed from: protected */
    public native boolean appendWholeNativeLog(String str);

    /* access modifiers changed from: protected */
    public native String getNativeKeyValueList();

    /* access modifiers changed from: protected */
    public native String getNativeLog();

    /* access modifiers changed from: protected */
    public native boolean putNativeKeyValue(String str, String str2);

    /* access modifiers changed from: protected */
    public native String regist(String str, boolean z, int i2);

    /* access modifiers changed from: protected */
    public native String removeNativeKeyValue(String str);

    /* access modifiers changed from: protected */
    public native void setNativeInfo(int i2, String str);

    /* access modifiers changed from: protected */
    public native void testCrash();

    /* access modifiers changed from: protected */
    public native String unregist();

    private NativeCrashHandler(Context context, com.tencent.bugly.crashreport.common.info.a aVar, b bVar, w wVar, boolean z, String str) {
        this.c = z.a(context);
        try {
            if (z.a(str)) {
                str = context.getDir("bugly", 0).getAbsolutePath();
            }
        } catch (Throwable unused) {
            str = "/data/data/" + com.tencent.bugly.crashreport.common.info.a.a(context).c + "/app_bugly";
        }
        this.o = bVar;
        this.g = str;
        this.d = aVar;
        this.e = wVar;
        this.h = z;
        this.f = new a(context, aVar, bVar, com.tencent.bugly.crashreport.common.strategy.a.a());
    }

    public static synchronized NativeCrashHandler getInstance(Context context, com.tencent.bugly.crashreport.common.info.a aVar, b bVar, com.tencent.bugly.crashreport.common.strategy.a aVar2, w wVar, boolean z, String str) {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            if (a == null) {
                a = new NativeCrashHandler(context, aVar, bVar, wVar, z, str);
            }
            nativeCrashHandler = a;
        }
        return nativeCrashHandler;
    }

    public static synchronized NativeCrashHandler getInstance() {
        NativeCrashHandler nativeCrashHandler;
        synchronized (NativeCrashHandler.class) {
            nativeCrashHandler = a;
        }
        return nativeCrashHandler;
    }

    public synchronized String getDumpFilePath() {
        return this.g;
    }

    public synchronized void setDumpFilePath(String str) {
        this.g = str;
    }

    public static void setShouldHandleInJava(boolean z) {
        p = z;
        NativeCrashHandler nativeCrashHandler = a;
        if (nativeCrashHandler != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(z);
            nativeCrashHandler.a((int) TbsLog.TBSLOG_CODE_SDK_INIT, sb.toString());
        }
    }

    public static boolean isShouldHandleInJava() {
        return p;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(18:14|(1:16)(17:18|(1:20)|21|22|(1:24)|25|(1:27)|28|29|(1:31)(1:32)|33|(1:35)(1:36)|37|(1:39)|40|41|42)|17|21|22|(0)|25|(0)|28|29|(0)(0)|33|(0)(0)|37|(0)|40|41|42) */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:7|8|9|(3:11|12|(18:14|(1:16)(17:18|(1:20)|21|22|(1:24)|25|(1:27)|28|29|(1:31)(1:32)|33|(1:35)(1:36)|37|(1:39)|40|41|42)|17|21|22|(0)|25|(0)|28|29|(0)(0)|33|(0)(0)|37|(0)|40|41|42))(2:47|(7:49|50|51|(1:53)(1:54)|55|(1:57)|(7:59|(1:61)|62|(1:64)|65|66|67)))|68|69|70|71) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x008c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:68:0x01c5 */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007e A[Catch:{ all -> 0x008c }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008a A[Catch:{ all -> 0x008c }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0090 A[Catch:{ all -> 0x00f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0098 A[Catch:{ all -> 0x00f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a3 A[Catch:{ all -> 0x00f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ab A[Catch:{ all -> 0x00f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ca A[Catch:{ all -> 0x00f1 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(boolean r11) {
        /*
            r10 = this;
            monitor-enter(r10)
            boolean r0 = r10.k     // Catch:{ all -> 0x01cb }
            r1 = 0
            if (r0 == 0) goto L_0x000f
            java.lang.String r11 = "[Native] Native crash report has already registered."
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ all -> 0x01cb }
            com.tencent.bugly.proguard.x.d(r11, r0)     // Catch:{ all -> 0x01cb }
            monitor-exit(r10)
            return
        L_0x000f:
            boolean r0 = r10.j     // Catch:{ all -> 0x01cb }
            r2 = 2
            r3 = 1
            if (r0 == 0) goto L_0x00fa
            java.lang.String r0 = r10.g     // Catch:{ all -> 0x00f1 }
            int r4 = b     // Catch:{ all -> 0x00f1 }
            java.lang.String r11 = r10.regist(r0, r11, r4)     // Catch:{ all -> 0x00f1 }
            if (r11 == 0) goto L_0x01c5
            java.lang.String r0 = "[Native] Native Crash Report enable."
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ all -> 0x00f1 }
            com.tencent.bugly.proguard.x.a(r0, r4)     // Catch:{ all -> 0x00f1 }
            java.lang.String r0 = "[Native] Check extra jni for Bugly NDK v%s"
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x00f1 }
            r4[r1] = r11     // Catch:{ all -> 0x00f1 }
            com.tencent.bugly.proguard.x.c(r0, r4)     // Catch:{ all -> 0x00f1 }
            java.lang.String r0 = "2.1.1"
            java.lang.String r4 = "."
            java.lang.String r5 = ""
            java.lang.String r0 = r0.replace(r4, r5)     // Catch:{ all -> 0x00f1 }
            java.lang.String r4 = "2.3.0"
            java.lang.String r5 = "."
            java.lang.String r6 = ""
            java.lang.String r4 = r4.replace(r5, r6)     // Catch:{ all -> 0x00f1 }
            java.lang.String r5 = "."
            java.lang.String r6 = ""
            java.lang.String r5 = r11.replace(r5, r6)     // Catch:{ all -> 0x00f1 }
            int r6 = r5.length()     // Catch:{ all -> 0x00f1 }
            if (r6 != r2) goto L_0x0063
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f1 }
            r2.<init>()     // Catch:{ all -> 0x00f1 }
            r2.append(r5)     // Catch:{ all -> 0x00f1 }
            java.lang.String r5 = "0"
        L_0x005b:
            r2.append(r5)     // Catch:{ all -> 0x00f1 }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x00f1 }
            goto L_0x0074
        L_0x0063:
            int r2 = r5.length()     // Catch:{ all -> 0x00f1 }
            if (r2 != r3) goto L_0x0074
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f1 }
            r2.<init>()     // Catch:{ all -> 0x00f1 }
            r2.append(r5)     // Catch:{ all -> 0x00f1 }
            java.lang.String r5 = "00"
            goto L_0x005b
        L_0x0074:
            int r2 = java.lang.Integer.parseInt(r5)     // Catch:{ all -> 0x008c }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ all -> 0x008c }
            if (r2 < r0) goto L_0x0080
            m = r3     // Catch:{ all -> 0x008c }
        L_0x0080:
            int r0 = java.lang.Integer.parseInt(r5)     // Catch:{ all -> 0x008c }
            int r2 = java.lang.Integer.parseInt(r4)     // Catch:{ all -> 0x008c }
            if (r0 < r2) goto L_0x008c
            n = r3     // Catch:{ all -> 0x008c }
        L_0x008c:
            boolean r0 = n     // Catch:{ all -> 0x00f1 }
            if (r0 == 0) goto L_0x0098
            java.lang.String r0 = "[Native] Info setting jni can be accessed."
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x00f1 }
            com.tencent.bugly.proguard.x.a(r0, r2)     // Catch:{ all -> 0x00f1 }
            goto L_0x009f
        L_0x0098:
            java.lang.String r0 = "[Native] Info setting jni can not be accessed."
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x00f1 }
            com.tencent.bugly.proguard.x.d(r0, r2)     // Catch:{ all -> 0x00f1 }
        L_0x009f:
            boolean r0 = m     // Catch:{ all -> 0x00f1 }
            if (r0 == 0) goto L_0x00ab
            java.lang.String r0 = "[Native] Extra jni can be accessed."
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x00f1 }
            com.tencent.bugly.proguard.x.a(r0, r2)     // Catch:{ all -> 0x00f1 }
            goto L_0x00b2
        L_0x00ab:
            java.lang.String r0 = "[Native] Extra jni can not be accessed."
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x00f1 }
            com.tencent.bugly.proguard.x.d(r0, r2)     // Catch:{ all -> 0x00f1 }
        L_0x00b2:
            com.tencent.bugly.crashreport.common.info.a r0 = r10.d     // Catch:{ all -> 0x00f1 }
            r0.o = r11     // Catch:{ all -> 0x00f1 }
            java.lang.String r11 = "-"
            com.tencent.bugly.crashreport.common.info.a r0 = r10.d     // Catch:{ all -> 0x00f1 }
            java.lang.String r0 = r0.o     // Catch:{ all -> 0x00f1 }
            java.lang.String r11 = r11.concat(r0)     // Catch:{ all -> 0x00f1 }
            com.tencent.bugly.crashreport.common.info.a r0 = r10.d     // Catch:{ all -> 0x00f1 }
            java.lang.String r0 = r0.f     // Catch:{ all -> 0x00f1 }
            boolean r11 = r0.contains(r11)     // Catch:{ all -> 0x00f1 }
            if (r11 != 0) goto L_0x00e0
            com.tencent.bugly.crashreport.common.info.a r11 = r10.d     // Catch:{ all -> 0x00f1 }
            com.tencent.bugly.crashreport.common.info.a r0 = r10.d     // Catch:{ all -> 0x00f1 }
            java.lang.String r0 = r0.f     // Catch:{ all -> 0x00f1 }
            java.lang.String r2 = "-"
            java.lang.String r0 = r0.concat(r2)     // Catch:{ all -> 0x00f1 }
            com.tencent.bugly.crashreport.common.info.a r2 = r10.d     // Catch:{ all -> 0x00f1 }
            java.lang.String r2 = r2.o     // Catch:{ all -> 0x00f1 }
            java.lang.String r0 = r0.concat(r2)     // Catch:{ all -> 0x00f1 }
            r11.f = r0     // Catch:{ all -> 0x00f1 }
        L_0x00e0:
            java.lang.String r11 = "comInfo.sdkVersion %s"
            java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch:{ all -> 0x00f1 }
            com.tencent.bugly.crashreport.common.info.a r2 = r10.d     // Catch:{ all -> 0x00f1 }
            java.lang.String r2 = r2.f     // Catch:{ all -> 0x00f1 }
            r0[r1] = r2     // Catch:{ all -> 0x00f1 }
            com.tencent.bugly.proguard.x.a(r11, r0)     // Catch:{ all -> 0x00f1 }
            r10.k = r3     // Catch:{ all -> 0x00f1 }
            monitor-exit(r10)
            return
        L_0x00f1:
            java.lang.String r11 = "[Native] Failed to load Bugly SO file."
            java.lang.Object[] r0 = new java.lang.Object[r1]     // Catch:{ all -> 0x01cb }
            com.tencent.bugly.proguard.x.c(r11, r0)     // Catch:{ all -> 0x01cb }
            goto L_0x01c5
        L_0x00fa:
            boolean r0 = r10.i     // Catch:{ all -> 0x01cb }
            if (r0 == 0) goto L_0x01c5
            java.lang.String r0 = "com.tencent.feedback.eup.jni.NativeExceptionUpload"
            java.lang.String r4 = "registNativeExceptionHandler2"
            r5 = 4
            java.lang.Class[] r6 = new java.lang.Class[r5]     // Catch:{ all -> 0x01c5 }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r1] = r7     // Catch:{ all -> 0x01c5 }
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r3] = r7     // Catch:{ all -> 0x01c5 }
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ all -> 0x01c5 }
            r6[r2] = r7     // Catch:{ all -> 0x01c5 }
            java.lang.Class r7 = java.lang.Integer.TYPE     // Catch:{ all -> 0x01c5 }
            r8 = 3
            r6[r8] = r7     // Catch:{ all -> 0x01c5 }
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x01c5 }
            java.lang.String r7 = r10.g     // Catch:{ all -> 0x01c5 }
            r5[r1] = r7     // Catch:{ all -> 0x01c5 }
            android.content.Context r7 = r10.c     // Catch:{ all -> 0x01c5 }
            java.lang.String r7 = com.tencent.bugly.crashreport.common.info.b.a(r7, r1)     // Catch:{ all -> 0x01c5 }
            r5[r3] = r7     // Catch:{ all -> 0x01c5 }
            r7 = 5
            if (r11 == 0) goto L_0x0129
            r9 = 1
            goto L_0x012a
        L_0x0129:
            r9 = 5
        L_0x012a:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x01c5 }
            r5[r2] = r9     // Catch:{ all -> 0x01c5 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x01c5 }
            r5[r8] = r9     // Catch:{ all -> 0x01c5 }
            r9 = 0
            java.lang.Object r0 = com.tencent.bugly.proguard.z.a(r0, r4, r9, r6, r5)     // Catch:{ all -> 0x01c5 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x01c5 }
            if (r0 != 0) goto L_0x0172
            java.lang.String r0 = "com.tencent.feedback.eup.jni.NativeExceptionUpload"
            java.lang.String r4 = "registNativeExceptionHandler"
            java.lang.Class[] r5 = new java.lang.Class[r8]     // Catch:{ all -> 0x01c5 }
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r5[r1] = r6     // Catch:{ all -> 0x01c5 }
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r5[r3] = r6     // Catch:{ all -> 0x01c5 }
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch:{ all -> 0x01c5 }
            r5[r2] = r6     // Catch:{ all -> 0x01c5 }
            java.lang.Object[] r6 = new java.lang.Object[r8]     // Catch:{ all -> 0x01c5 }
            java.lang.String r8 = r10.g     // Catch:{ all -> 0x01c5 }
            r6[r1] = r8     // Catch:{ all -> 0x01c5 }
            android.content.Context r8 = r10.c     // Catch:{ all -> 0x01c5 }
            java.lang.String r8 = com.tencent.bugly.crashreport.common.info.b.a(r8, r1)     // Catch:{ all -> 0x01c5 }
            r6[r3] = r8     // Catch:{ all -> 0x01c5 }
            com.tencent.bugly.crashreport.common.info.a.b()     // Catch:{ all -> 0x01c5 }
            int r8 = com.tencent.bugly.crashreport.common.info.a.G()     // Catch:{ all -> 0x01c5 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x01c5 }
            r6[r2] = r8     // Catch:{ all -> 0x01c5 }
            java.lang.Object r0 = com.tencent.bugly.proguard.z.a(r0, r4, r9, r5, r6)     // Catch:{ all -> 0x01c5 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x01c5 }
        L_0x0172:
            if (r0 == 0) goto L_0x01c5
            r10.k = r3     // Catch:{ all -> 0x01c5 }
            com.tencent.bugly.crashreport.common.info.a r2 = r10.d     // Catch:{ all -> 0x01c5 }
            r2.o = r0     // Catch:{ all -> 0x01c5 }
            java.lang.String r2 = "com.tencent.feedback.eup.jni.NativeExceptionUpload"
            java.lang.String r4 = "checkExtraJni"
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch:{ all -> 0x01c5 }
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r5[r1] = r6     // Catch:{ all -> 0x01c5 }
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ all -> 0x01c5 }
            r6[r1] = r0     // Catch:{ all -> 0x01c5 }
            java.lang.Object r0 = com.tencent.bugly.proguard.z.a(r2, r4, r9, r5, r6)     // Catch:{ all -> 0x01c5 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01c5 }
            if (r0 == 0) goto L_0x0196
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01c5 }
            m = r0     // Catch:{ all -> 0x01c5 }
        L_0x0196:
            java.lang.String r0 = "com.tencent.feedback.eup.jni.NativeExceptionUpload"
            java.lang.String r2 = "enableHandler"
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch:{ all -> 0x01c5 }
            java.lang.Class r5 = java.lang.Boolean.TYPE     // Catch:{ all -> 0x01c5 }
            r4[r1] = r5     // Catch:{ all -> 0x01c5 }
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ all -> 0x01c5 }
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x01c5 }
            r5[r1] = r6     // Catch:{ all -> 0x01c5 }
            com.tencent.bugly.proguard.z.a(r0, r2, r9, r4, r5)     // Catch:{ all -> 0x01c5 }
            if (r11 == 0) goto L_0x01ae
            r7 = 1
        L_0x01ae:
            java.lang.String r11 = "com.tencent.feedback.eup.jni.NativeExceptionUpload"
            java.lang.String r0 = "setLogMode"
            java.lang.Class[] r2 = new java.lang.Class[r3]     // Catch:{ all -> 0x01c5 }
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ all -> 0x01c5 }
            r2[r1] = r4     // Catch:{ all -> 0x01c5 }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x01c5 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x01c5 }
            r3[r1] = r4     // Catch:{ all -> 0x01c5 }
            com.tencent.bugly.proguard.z.a(r11, r0, r9, r2, r3)     // Catch:{ all -> 0x01c5 }
            monitor-exit(r10)
            return
        L_0x01c5:
            r10.j = r1     // Catch:{ all -> 0x01cb }
            r10.i = r1     // Catch:{ all -> 0x01cb }
            monitor-exit(r10)
            return
        L_0x01cb:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.a(boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006c, code lost:
        return;
     */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:21:0x0034=Splitter:B:21:0x0034, B:27:0x006d=Splitter:B:27:0x006d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void startNativeMonitor() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.j     // Catch:{ all -> 0x0074 }
            if (r0 != 0) goto L_0x006d
            boolean r0 = r3.i     // Catch:{ all -> 0x0074 }
            if (r0 == 0) goto L_0x000a
            goto L_0x006d
        L_0x000a:
            java.lang.String r0 = "Bugly"
            com.tencent.bugly.crashreport.common.info.a r1 = r3.d     // Catch:{ all -> 0x0074 }
            java.lang.String r1 = r1.n     // Catch:{ all -> 0x0074 }
            boolean r1 = com.tencent.bugly.proguard.z.a((java.lang.String) r1)     // Catch:{ all -> 0x0074 }
            if (r1 != 0) goto L_0x0018
            r1 = 1
            goto L_0x0019
        L_0x0018:
            r1 = 0
        L_0x0019:
            com.tencent.bugly.crashreport.common.info.a r2 = r3.d     // Catch:{ all -> 0x0074 }
            java.lang.String r2 = r2.n     // Catch:{ all -> 0x0074 }
            if (r1 != 0) goto L_0x0025
            com.tencent.bugly.crashreport.common.info.a r2 = r3.d     // Catch:{ all -> 0x0074 }
            r2.getClass()     // Catch:{ all -> 0x0074 }
            goto L_0x0026
        L_0x0025:
            r0 = r2
        L_0x0026:
            boolean r0 = a((java.lang.String) r0, (boolean) r1)     // Catch:{ all -> 0x0074 }
            r3.j = r0     // Catch:{ all -> 0x0074 }
            if (r0 != 0) goto L_0x0034
            boolean r0 = r3.i     // Catch:{ all -> 0x0074 }
            if (r0 != 0) goto L_0x0034
            monitor-exit(r3)
            return
        L_0x0034:
            boolean r0 = r3.h     // Catch:{ all -> 0x0074 }
            r3.a((boolean) r0)     // Catch:{ all -> 0x0074 }
            boolean r0 = m     // Catch:{ all -> 0x0074 }
            if (r0 == 0) goto L_0x006b
            com.tencent.bugly.crashreport.common.info.a r0 = r3.d     // Catch:{ all -> 0x0074 }
            java.lang.String r0 = r0.k     // Catch:{ all -> 0x0074 }
            r3.setNativeAppVersion(r0)     // Catch:{ all -> 0x0074 }
            com.tencent.bugly.crashreport.common.info.a r0 = r3.d     // Catch:{ all -> 0x0074 }
            java.lang.String r0 = r0.m     // Catch:{ all -> 0x0074 }
            r3.setNativeAppChannel(r0)     // Catch:{ all -> 0x0074 }
            com.tencent.bugly.crashreport.common.info.a r0 = r3.d     // Catch:{ all -> 0x0074 }
            java.lang.String r0 = r0.c     // Catch:{ all -> 0x0074 }
            r3.setNativeAppPackage(r0)     // Catch:{ all -> 0x0074 }
            com.tencent.bugly.crashreport.common.info.a r0 = r3.d     // Catch:{ all -> 0x0074 }
            java.lang.String r0 = r0.g()     // Catch:{ all -> 0x0074 }
            r3.setNativeUserId(r0)     // Catch:{ all -> 0x0074 }
            com.tencent.bugly.crashreport.common.info.a r0 = r3.d     // Catch:{ all -> 0x0074 }
            boolean r0 = r0.a()     // Catch:{ all -> 0x0074 }
            r3.setNativeIsAppForeground(r0)     // Catch:{ all -> 0x0074 }
            com.tencent.bugly.crashreport.common.info.a r0 = r3.d     // Catch:{ all -> 0x0074 }
            long r0 = r0.a     // Catch:{ all -> 0x0074 }
            r3.setNativeLaunchTime(r0)     // Catch:{ all -> 0x0074 }
        L_0x006b:
            monitor-exit(r3)
            return
        L_0x006d:
            boolean r0 = r3.h     // Catch:{ all -> 0x0074 }
            r3.a((boolean) r0)     // Catch:{ all -> 0x0074 }
            monitor-exit(r3)
            return
        L_0x0074:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.startNativeMonitor():void");
    }

    public void checkUploadRecordCrash() {
        this.e.a(new Runnable() {
            public final void run() {
                if (!z.a(NativeCrashHandler.this.c, "native_record_lock", 10000)) {
                    x.a("[Native] Failed to lock file for handling native crash record.", new Object[0]);
                    return;
                }
                if (!NativeCrashHandler.p) {
                    boolean unused = NativeCrashHandler.this.a((int) TbsLog.TBSLOG_CODE_SDK_INIT, Bugly.SDK_IS_DEV);
                }
                CrashDetailBean a2 = b.a(NativeCrashHandler.this.c, NativeCrashHandler.this.g, NativeCrashHandler.this.f);
                if (a2 != null) {
                    x.a("[Native] Get crash from native record.", new Object[0]);
                    if (!NativeCrashHandler.this.o.a(a2)) {
                        NativeCrashHandler.this.o.a(a2, 3000, false);
                    }
                    b.a(false, NativeCrashHandler.this.g);
                }
                NativeCrashHandler.this.a();
                z.b(NativeCrashHandler.this.c, "native_record_lock");
            }
        });
    }

    private static boolean a(String str, boolean z) {
        boolean z2;
        try {
            x.a("[Native] Trying to load so: %s", str);
            if (z) {
                System.load(str);
            } else {
                System.loadLibrary(str);
            }
            try {
                x.a("[Native] Successfully loaded SO: %s", str);
                return true;
            } catch (Throwable th) {
                th = th;
                z2 = true;
            }
        } catch (Throwable th2) {
            th = th2;
            z2 = false;
            x.d(th.getMessage(), new Object[0]);
            x.d("[Native] Failed to load so: %s", str);
            return z2;
        }
    }

    private synchronized void c() {
        if (!this.k) {
            x.d("[Native] Native crash report has already unregistered.", new Object[0]);
            return;
        }
        try {
            if (unregist() != null) {
                x.a("[Native] Successfully closed native crash report.", new Object[0]);
                this.k = false;
                return;
            }
        } catch (Throwable unused) {
            x.c("[Native] Failed to close native crash report.", new Object[0]);
        }
        try {
            z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", (Object) null, new Class[]{Boolean.TYPE}, new Object[]{false});
            this.k = false;
            x.a("[Native] Successfully closed native crash report.", new Object[0]);
            return;
        } catch (Throwable unused2) {
            x.c("[Native] Failed to close native crash report.", new Object[0]);
            this.j = false;
            this.i = false;
            return;
        }
    }

    public void testNativeCrash() {
        if (!this.j) {
            x.d("[Native] Bugly SO file has not been load.", new Object[0]);
        } else {
            testCrash();
        }
    }

    public void testNativeCrash(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        sb.append(z);
        a(16, sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(z2);
        a(17, sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(z3);
        a(18, sb3.toString());
        testNativeCrash();
    }

    public NativeExceptionHandler getNativeExceptionHandler() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        long b2 = z.b() - c.g;
        long b3 = z.b() + DateUtils.MILLIS_PER_DAY;
        File file = new File(this.g);
        if (file.exists() && file.isDirectory()) {
            try {
                File[] listFiles = file.listFiles();
                if (listFiles == null) {
                    return;
                }
                if (listFiles.length != 0) {
                    int i2 = 0;
                    int i3 = 0;
                    for (File file2 : listFiles) {
                        long lastModified = file2.lastModified();
                        if (lastModified < b2 || lastModified >= b3) {
                            x.a("[Native] Delete record file: %s", file2.getAbsolutePath());
                            i2++;
                            if (file2.delete()) {
                                i3++;
                            }
                        }
                    }
                    x.c("[Native] Number of record files overdue: %d, has deleted: %d", Integer.valueOf(i2), Integer.valueOf(i3));
                }
            } catch (Throwable th) {
                x.a(th);
            }
        }
    }

    public void removeEmptyNativeRecordFiles() {
        b.c(this.g);
    }

    private synchronized void b(boolean z) {
        if (z) {
            startNativeMonitor();
        } else {
            c();
        }
    }

    public synchronized boolean isUserOpened() {
        return this.l;
    }

    private synchronized void c(boolean z) {
        if (this.l != z) {
            x.a("user change native %b", Boolean.valueOf(z));
            this.l = z;
        }
    }

    public synchronized void setUserOpened(boolean z) {
        c(z);
        boolean isUserOpened = isUserOpened();
        com.tencent.bugly.crashreport.common.strategy.a a2 = com.tencent.bugly.crashreport.common.strategy.a.a();
        if (a2 != null) {
            isUserOpened = isUserOpened && a2.c().e;
        }
        if (isUserOpened != this.k) {
            x.a("native changed to %b", Boolean.valueOf(isUserOpened));
            b(isUserOpened);
        }
    }

    public synchronized void onStrategyChanged(StrategyBean strategyBean) {
        if (strategyBean != null) {
            if (strategyBean.e != this.k) {
                x.d("server native changed to %b", Boolean.valueOf(strategyBean.e));
            }
        }
        boolean z = com.tencent.bugly.crashreport.common.strategy.a.a().c().e && this.l;
        if (z != this.k) {
            x.a("native changed to %b", Boolean.valueOf(z));
            b(z);
        }
    }

    public boolean appendLogToNative(String str, String str2, String str3) {
        if (!((!this.i && !this.j) || !m || str == null || str2 == null || str3 == null)) {
            try {
                if (this.j) {
                    return appendNativeLog(str, str2, str3);
                }
                Boolean bool = (Boolean) z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "appendNativeLog", (Object) null, new Class[]{String.class, String.class, String.class}, new Object[]{str, str2, str3});
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } catch (UnsatisfiedLinkError unused) {
                m = false;
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }

    public String getLogFromNative() {
        if ((!this.i && !this.j) || !m) {
            return null;
        }
        try {
            if (this.j) {
                return getNativeLog();
            }
            return (String) z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "getNativeLog", (Object) null, (Class<?>[]) null, (Object[]) null);
        } catch (UnsatisfiedLinkError unused) {
            m = false;
            return null;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public boolean putKeyValueToNative(String str, String str2) {
        if ((this.i || this.j) && m && str != null && str2 != null) {
            try {
                if (this.j) {
                    return putNativeKeyValue(str, str2);
                }
                Boolean bool = (Boolean) z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "putNativeKeyValue", (Object) null, new Class[]{String.class, String.class}, new Object[]{str, str2});
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } catch (UnsatisfiedLinkError unused) {
                m = false;
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean a(int i2, String str) {
        if (this.j && n) {
            try {
                setNativeInfo(i2, str);
                return true;
            } catch (UnsatisfiedLinkError unused) {
                n = false;
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }

    public boolean filterSigabrtSysLog() {
        return a((int) TbsLog.TBSLOG_CODE_SDK_LOAD_ERROR, "true");
    }

    public boolean setNativeAppVersion(String str) {
        return a(10, str);
    }

    public boolean setNativeAppChannel(String str) {
        return a(12, str);
    }

    public boolean setNativeAppPackage(String str) {
        return a(13, str);
    }

    public boolean setNativeUserId(String str) {
        return a(11, str);
    }

    public boolean setNativeIsAppForeground(boolean z) {
        return a(14, z ? "true" : Bugly.SDK_IS_DEV);
    }

    public boolean setNativeLaunchTime(long j2) {
        try {
            return a(15, String.valueOf(j2));
        } catch (NumberFormatException e2) {
            if (x.a(e2)) {
                return false;
            }
            e2.printStackTrace();
            return false;
        }
    }

    public void enableCatchAnrTrace() {
        if (Build.VERSION.SDK_INT <= 29 && Build.VERSION.SDK_INT >= 26 && com.tencent.bugly.crashreport.common.info.b.c(this.c).contains("Oppo")) {
            b |= 2;
        }
    }
}
