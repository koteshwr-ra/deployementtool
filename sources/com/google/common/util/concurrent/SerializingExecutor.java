package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

final class SerializingExecutor implements Executor {
    /* access modifiers changed from: private */
    public static final Logger log = Logger.getLogger(SerializingExecutor.class.getName());
    private final Executor executor;
    /* access modifiers changed from: private */
    public final Object internalLock = new Object() {
        public String toString() {
            String valueOf = String.valueOf(super.toString());
            return valueOf.length() != 0 ? "SerializingExecutor lock: ".concat(valueOf) : new String("SerializingExecutor lock: ");
        }
    };
    /* access modifiers changed from: private */
    public boolean isThreadScheduled = false;
    private final TaskRunner taskRunner = new TaskRunner();
    /* access modifiers changed from: private */
    public final Queue<Runnable> waitQueue = new ArrayDeque();

    public SerializingExecutor(Executor executor2) {
        Preconditions.checkNotNull(executor2, "'executor' must not be null.");
        this.executor = executor2;
    }

    public void execute(Runnable runnable) {
        boolean z;
        Preconditions.checkNotNull(runnable, "'r' must not be null.");
        synchronized (this.internalLock) {
            this.waitQueue.add(runnable);
            z = true;
            if (!this.isThreadScheduled) {
                this.isThreadScheduled = true;
            } else {
                z = false;
            }
        }
        if (z) {
            try {
                this.executor.execute(this.taskRunner);
            } catch (Throwable th) {
                synchronized (this.internalLock) {
                    this.isThreadScheduled = false;
                    throw th;
                }
            }
        }
    }

    private class TaskRunner implements Runnable {
        private TaskRunner() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            r3.run();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x005e, code lost:
            r2 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x005f, code lost:
            r1 = r3;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r8 = this;
            L_0x0000:
                r0 = 0
                r1 = 1
                com.google.common.util.concurrent.SerializingExecutor r2 = com.google.common.util.concurrent.SerializingExecutor.this     // Catch:{ all -> 0x0063 }
                boolean r2 = r2.isThreadScheduled     // Catch:{ all -> 0x0063 }
                com.google.common.base.Preconditions.checkState(r2)     // Catch:{ all -> 0x0063 }
                com.google.common.util.concurrent.SerializingExecutor r2 = com.google.common.util.concurrent.SerializingExecutor.this     // Catch:{ all -> 0x0063 }
                java.lang.Object r2 = r2.internalLock     // Catch:{ all -> 0x0063 }
                monitor-enter(r2)     // Catch:{ all -> 0x0063 }
                com.google.common.util.concurrent.SerializingExecutor r3 = com.google.common.util.concurrent.SerializingExecutor.this     // Catch:{ all -> 0x0059 }
                java.util.Queue r3 = r3.waitQueue     // Catch:{ all -> 0x0059 }
                java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x0059 }
                java.lang.Runnable r3 = (java.lang.Runnable) r3     // Catch:{ all -> 0x0059 }
                if (r3 != 0) goto L_0x002a
                com.google.common.util.concurrent.SerializingExecutor r3 = com.google.common.util.concurrent.SerializingExecutor.this     // Catch:{ all -> 0x0059 }
                boolean unused = r3.isThreadScheduled = r0     // Catch:{ all -> 0x0059 }
                monitor-exit(r2)     // Catch:{ all -> 0x0027 }
                return
            L_0x0027:
                r1 = move-exception
                r3 = 0
                goto L_0x005c
            L_0x002a:
                monitor-exit(r2)     // Catch:{ all -> 0x0059 }
                r3.run()     // Catch:{ RuntimeException -> 0x002f }
                goto L_0x0000
            L_0x002f:
                r2 = move-exception
                java.util.logging.Logger r4 = com.google.common.util.concurrent.SerializingExecutor.log     // Catch:{ all -> 0x0063 }
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch:{ all -> 0x0063 }
                java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0063 }
                java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0063 }
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0063 }
                int r7 = r3.length()     // Catch:{ all -> 0x0063 }
                int r7 = r7 + 35
                r6.<init>(r7)     // Catch:{ all -> 0x0063 }
                java.lang.String r7 = "Exception while executing runnable "
                r6.append(r7)     // Catch:{ all -> 0x0063 }
                r6.append(r3)     // Catch:{ all -> 0x0063 }
                java.lang.String r3 = r6.toString()     // Catch:{ all -> 0x0063 }
                r4.log(r5, r3, r2)     // Catch:{ all -> 0x0063 }
                goto L_0x0000
            L_0x0059:
                r3 = move-exception
                r1 = r3
                r3 = 1
            L_0x005c:
                monitor-exit(r2)     // Catch:{ all -> 0x0061 }
                throw r1     // Catch:{ all -> 0x005e }
            L_0x005e:
                r2 = move-exception
                r1 = r3
                goto L_0x0064
            L_0x0061:
                r1 = move-exception
                goto L_0x005c
            L_0x0063:
                r2 = move-exception
            L_0x0064:
                if (r1 == 0) goto L_0x0077
                com.google.common.util.concurrent.SerializingExecutor r1 = com.google.common.util.concurrent.SerializingExecutor.this
                java.lang.Object r1 = r1.internalLock
                monitor-enter(r1)
                com.google.common.util.concurrent.SerializingExecutor r3 = com.google.common.util.concurrent.SerializingExecutor.this     // Catch:{ all -> 0x0074 }
                boolean unused = r3.isThreadScheduled = r0     // Catch:{ all -> 0x0074 }
                monitor-exit(r1)     // Catch:{ all -> 0x0074 }
                goto L_0x0077
            L_0x0074:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0074 }
                throw r0
            L_0x0077:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.SerializingExecutor.TaskRunner.run():void");
        }
    }
}
