package com.tencent.bugly;

import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.c;

/* compiled from: BUGLY */
public class CrashModule extends a {
    public static final int MODULE_ID = 1004;
    private static int c;
    private static CrashModule e = new CrashModule();
    private long a;
    private BuglyStrategy.a b;
    private boolean d = false;

    public static CrashModule getInstance() {
        e.id = 1004;
        return e;
    }

    public synchronized boolean hasInitialized() {
        return this.d;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b3, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void init(android.content.Context r12, boolean r13, com.tencent.bugly.BuglyStrategy r14) {
        /*
            r11 = this;
            monitor-enter(r11)
            if (r12 == 0) goto L_0x00b2
            boolean r0 = r11.d     // Catch:{ all -> 0x00af }
            if (r0 == 0) goto L_0x0009
            goto L_0x00b2
        L_0x0009:
            java.lang.String r0 = "Initializing crash module."
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x00af }
            com.tencent.bugly.proguard.x.a(r0, r2)     // Catch:{ all -> 0x00af }
            com.tencent.bugly.proguard.n r0 = com.tencent.bugly.proguard.n.a()     // Catch:{ all -> 0x00af }
            int r2 = c     // Catch:{ all -> 0x00af }
            r3 = 1
            int r2 = r2 + r3
            c = r2     // Catch:{ all -> 0x00af }
            r4 = 1004(0x3ec, float:1.407E-42)
            r0.a((int) r4, (int) r2)     // Catch:{ all -> 0x00af }
            r11.d = r3     // Catch:{ all -> 0x00af }
            com.tencent.bugly.crashreport.CrashReport.setContext(r12)     // Catch:{ all -> 0x00af }
            r11.a(r12, r14)     // Catch:{ all -> 0x00af }
            r5 = 1004(0x3ec, float:1.407E-42)
            com.tencent.bugly.BuglyStrategy$a r8 = r11.b     // Catch:{ all -> 0x00af }
            r9 = 0
            r10 = 0
            r6 = r12
            r7 = r13
            com.tencent.bugly.crashreport.crash.c r13 = com.tencent.bugly.crashreport.crash.c.a((int) r5, (android.content.Context) r6, (boolean) r7, (com.tencent.bugly.BuglyStrategy.a) r8, (com.tencent.bugly.proguard.o) r9, (java.lang.String) r10)     // Catch:{ all -> 0x00af }
            r13.e()     // Catch:{ all -> 0x00af }
            if (r14 == 0) goto L_0x0047
            int r0 = r14.getCallBackType()     // Catch:{ all -> 0x00af }
            r13.a((int) r0)     // Catch:{ all -> 0x00af }
            boolean r0 = r14.getCloseErrorCallback()     // Catch:{ all -> 0x00af }
            r13.a((boolean) r0)     // Catch:{ all -> 0x00af }
        L_0x0047:
            if (r14 == 0) goto L_0x0052
            boolean r0 = r14.isEnableCatchAnrTrace()     // Catch:{ all -> 0x00af }
            if (r0 == 0) goto L_0x0052
            r13.j()     // Catch:{ all -> 0x00af }
        L_0x0052:
            r13.n()     // Catch:{ all -> 0x00af }
            if (r14 == 0) goto L_0x0069
            boolean r0 = r14.isEnableNativeCrashMonitor()     // Catch:{ all -> 0x00af }
            if (r0 == 0) goto L_0x005e
            goto L_0x0069
        L_0x005e:
            java.lang.String r0 = "[crash] Closed native crash monitor!"
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ all -> 0x00af }
            com.tencent.bugly.proguard.x.a(r0, r2)     // Catch:{ all -> 0x00af }
            r13.f()     // Catch:{ all -> 0x00af }
            goto L_0x006c
        L_0x0069:
            r13.g()     // Catch:{ all -> 0x00af }
        L_0x006c:
            if (r14 == 0) goto L_0x0080
            boolean r0 = r14.isEnableANRCrashMonitor()     // Catch:{ all -> 0x00af }
            if (r0 == 0) goto L_0x0075
            goto L_0x0080
        L_0x0075:
            java.lang.String r0 = "[crash] Closed ANR monitor!"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x00af }
            com.tencent.bugly.proguard.x.a(r0, r1)     // Catch:{ all -> 0x00af }
            r13.i()     // Catch:{ all -> 0x00af }
            goto L_0x0083
        L_0x0080:
            r13.h()     // Catch:{ all -> 0x00af }
        L_0x0083:
            if (r14 == 0) goto L_0x008a
            long r0 = r14.getAppReportDelay()     // Catch:{ all -> 0x00af }
            goto L_0x008c
        L_0x008a:
            r0 = 0
        L_0x008c:
            r13.a((long) r0)     // Catch:{ all -> 0x00af }
            r13.m()     // Catch:{ all -> 0x00af }
            com.tencent.bugly.crashreport.crash.d.a((android.content.Context) r12)     // Catch:{ all -> 0x00af }
            com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver r13 = com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver.getInstance()     // Catch:{ all -> 0x00af }
            java.lang.String r14 = "android.net.conn.CONNECTIVITY_CHANGE"
            r13.addFilter(r14)     // Catch:{ all -> 0x00af }
            r13.register(r12)     // Catch:{ all -> 0x00af }
            com.tencent.bugly.proguard.n r12 = com.tencent.bugly.proguard.n.a()     // Catch:{ all -> 0x00af }
            int r13 = c     // Catch:{ all -> 0x00af }
            int r13 = r13 - r3
            c = r13     // Catch:{ all -> 0x00af }
            r12.a((int) r4, (int) r13)     // Catch:{ all -> 0x00af }
            monitor-exit(r11)
            return
        L_0x00af:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        L_0x00b2:
            monitor-exit(r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.CrashModule.init(android.content.Context, boolean, com.tencent.bugly.BuglyStrategy):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0051, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(android.content.Context r7, com.tencent.bugly.BuglyStrategy r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r8 != 0) goto L_0x0005
            monitor-exit(r6)
            return
        L_0x0005:
            java.lang.String r0 = r8.getLibBuglySOFilePath()     // Catch:{ all -> 0x0052 }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0052 }
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0020
            com.tencent.bugly.crashreport.common.info.a r7 = com.tencent.bugly.crashreport.common.info.a.a((android.content.Context) r7)     // Catch:{ all -> 0x0052 }
            r7.n = r0     // Catch:{ all -> 0x0052 }
            java.lang.String r7 = "setted libBugly.so file path :%s"
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0052 }
            r1[r3] = r0     // Catch:{ all -> 0x0052 }
            com.tencent.bugly.proguard.x.a(r7, r1)     // Catch:{ all -> 0x0052 }
        L_0x0020:
            com.tencent.bugly.BuglyStrategy$a r7 = r8.getCrashHandleCallback()     // Catch:{ all -> 0x0052 }
            if (r7 == 0) goto L_0x0033
            com.tencent.bugly.BuglyStrategy$a r7 = r8.getCrashHandleCallback()     // Catch:{ all -> 0x0052 }
            r6.b = r7     // Catch:{ all -> 0x0052 }
            java.lang.String r7 = "setted CrashHanldeCallback"
            java.lang.Object[] r0 = new java.lang.Object[r3]     // Catch:{ all -> 0x0052 }
            com.tencent.bugly.proguard.x.a(r7, r0)     // Catch:{ all -> 0x0052 }
        L_0x0033:
            long r0 = r8.getAppReportDelay()     // Catch:{ all -> 0x0052 }
            r4 = 0
            int r7 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r7 <= 0) goto L_0x0050
            long r7 = r8.getAppReportDelay()     // Catch:{ all -> 0x0052 }
            r6.a = r7     // Catch:{ all -> 0x0052 }
            java.lang.String r0 = "setted delay: %d"
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0052 }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x0052 }
            r1[r3] = r7     // Catch:{ all -> 0x0052 }
            com.tencent.bugly.proguard.x.a(r0, r1)     // Catch:{ all -> 0x0052 }
        L_0x0050:
            monitor-exit(r6)
            return
        L_0x0052:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.CrashModule.a(android.content.Context, com.tencent.bugly.BuglyStrategy):void");
    }

    public void onServerStrategyChanged(StrategyBean strategyBean) {
        c a2;
        if (strategyBean != null && (a2 = c.a()) != null) {
            a2.a(strategyBean);
        }
    }

    public String[] getTables() {
        return new String[]{"t_cr"};
    }
}
