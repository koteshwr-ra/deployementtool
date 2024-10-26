package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

final class ListenerCallQueue<L> implements Runnable {
    private static final Logger logger = Logger.getLogger(ListenerCallQueue.class.getName());
    private final Executor executor;
    private boolean isThreadScheduled;
    private final L listener;
    private final Queue<Callback<L>> waitQueue = Queues.newArrayDeque();

    static abstract class Callback<L> {
        /* access modifiers changed from: private */
        public final String methodCall;

        /* access modifiers changed from: package-private */
        public abstract void call(L l);

        Callback(String str) {
            this.methodCall = str;
        }

        /* access modifiers changed from: package-private */
        public void enqueueOn(Iterable<ListenerCallQueue<L>> iterable) {
            for (ListenerCallQueue<L> add : iterable) {
                add.add(this);
            }
        }
    }

    ListenerCallQueue(L l, Executor executor2) {
        this.listener = Preconditions.checkNotNull(l);
        this.executor = (Executor) Preconditions.checkNotNull(executor2);
    }

    /* access modifiers changed from: package-private */
    public synchronized void add(Callback<L> callback) {
        this.waitQueue.add(callback);
    }

    /* access modifiers changed from: package-private */
    public void execute() {
        boolean z;
        synchronized (this) {
            z = true;
            if (!this.isThreadScheduled) {
                this.isThreadScheduled = true;
            } else {
                z = false;
            }
        }
        if (z) {
            try {
                this.executor.execute(this);
            } catch (RuntimeException e) {
                synchronized (this) {
                    this.isThreadScheduled = false;
                    Logger logger2 = logger;
                    Level level = Level.SEVERE;
                    String valueOf = String.valueOf(String.valueOf(this.listener));
                    String valueOf2 = String.valueOf(String.valueOf(this.executor));
                    StringBuilder sb = new StringBuilder(valueOf.length() + 42 + valueOf2.length());
                    sb.append("Exception while running callbacks for ");
                    sb.append(valueOf);
                    sb.append(" on ");
                    sb.append(valueOf2);
                    logger2.log(level, sb.toString(), e);
                    throw e;
                }
            }
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processHandlersOutBlocks(RegionMaker.java:1008)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:978)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    public void run() {
        /*
            r11 = this;
        L_0x0000:
            r0 = 0
            r1 = 1
            monitor-enter(r11)     // Catch:{ all -> 0x006f }
            boolean r2 = r11.isThreadScheduled     // Catch:{ all -> 0x0063 }
            com.google.common.base.Preconditions.checkState(r2)     // Catch:{ all -> 0x0063 }
            java.util.Queue<com.google.common.util.concurrent.ListenerCallQueue$Callback<L>> r2 = r11.waitQueue     // Catch:{ all -> 0x0063 }
            java.lang.Object r2 = r2.poll()     // Catch:{ all -> 0x0063 }
            com.google.common.util.concurrent.ListenerCallQueue$Callback r2 = (com.google.common.util.concurrent.ListenerCallQueue.Callback) r2     // Catch:{ all -> 0x0063 }
            if (r2 != 0) goto L_0x0019
            r11.isThreadScheduled = r0     // Catch:{ all -> 0x0063 }
            monitor-exit(r11)     // Catch:{ all -> 0x0016 }
            return
        L_0x0016:
            r1 = move-exception
            r2 = 0
            goto L_0x0066
        L_0x0019:
            monitor-exit(r11)     // Catch:{ all -> 0x0063 }
            L r3 = r11.listener     // Catch:{ RuntimeException -> 0x0020 }
            r2.call(r3)     // Catch:{ RuntimeException -> 0x0020 }
            goto L_0x0000
        L_0x0020:
            r3 = move-exception
            java.util.logging.Logger r4 = logger     // Catch:{ all -> 0x006f }
            java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch:{ all -> 0x006f }
            L r6 = r11.listener     // Catch:{ all -> 0x006f }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x006f }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x006f }
            java.lang.String r2 = r2.methodCall     // Catch:{ all -> 0x006f }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x006f }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x006f }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x006f }
            int r8 = r6.length()     // Catch:{ all -> 0x006f }
            int r8 = r8 + 37
            int r9 = r2.length()     // Catch:{ all -> 0x006f }
            int r8 = r8 + r9
            r7.<init>(r8)     // Catch:{ all -> 0x006f }
            java.lang.String r8 = "Exception while executing callback: "
            r7.append(r8)     // Catch:{ all -> 0x006f }
            r7.append(r6)     // Catch:{ all -> 0x006f }
            java.lang.String r6 = "."
            r7.append(r6)     // Catch:{ all -> 0x006f }
            r7.append(r2)     // Catch:{ all -> 0x006f }
            java.lang.String r2 = r7.toString()     // Catch:{ all -> 0x006f }
            r4.log(r5, r2, r3)     // Catch:{ all -> 0x006f }
            goto L_0x0000
        L_0x0063:
            r2 = move-exception
            r1 = r2
            r2 = 1
        L_0x0066:
            monitor-exit(r11)     // Catch:{ all -> 0x006d }
            throw r1     // Catch:{ all -> 0x0068 }
        L_0x0068:
            r1 = move-exception
            r10 = r2
            r2 = r1
            r1 = r10
            goto L_0x0070
        L_0x006d:
            r1 = move-exception
            goto L_0x0066
        L_0x006f:
            r2 = move-exception
        L_0x0070:
            if (r1 == 0) goto L_0x007a
            monitor-enter(r11)
            r11.isThreadScheduled = r0     // Catch:{ all -> 0x0077 }
            monitor-exit(r11)     // Catch:{ all -> 0x0077 }
            goto L_0x007a
        L_0x0077:
            r0 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x0077 }
            throw r0
        L_0x007a:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ListenerCallQueue.run():void");
    }
}
