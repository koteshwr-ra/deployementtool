package org.apache.mina.filter.executor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.mina.core.session.IoEvent;

public class UnorderedThreadPoolExecutor extends ThreadPoolExecutor {
    /* access modifiers changed from: private */
    public static final Runnable EXIT_SIGNAL = new Runnable() {
        public void run() {
            throw new Error("This method shouldn't be called. Please file a bug report.");
        }
    };
    private long completedTaskCount;
    /* access modifiers changed from: private */
    public volatile int corePoolSize;
    /* access modifiers changed from: private */
    public final AtomicInteger idleWorkers;
    private volatile int largestPoolSize;
    private volatile int maximumPoolSize;
    /* access modifiers changed from: private */
    public final IoEventQueueHandler queueHandler;
    private volatile boolean shutdown;
    /* access modifiers changed from: private */
    public final Set<Worker> workers;

    public void purge() {
    }

    public void setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
    }

    static /* synthetic */ long access$714(UnorderedThreadPoolExecutor unorderedThreadPoolExecutor, long j) {
        long j2 = unorderedThreadPoolExecutor.completedTaskCount + j;
        unorderedThreadPoolExecutor.completedTaskCount = j2;
        return j2;
    }

    public UnorderedThreadPoolExecutor() {
        this(16);
    }

    public UnorderedThreadPoolExecutor(int i) {
        this(0, i);
    }

    public UnorderedThreadPoolExecutor(int i, int i2) {
        this(i, i2, 30, TimeUnit.SECONDS);
    }

    public UnorderedThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit) {
        this(i, i2, j, timeUnit, Executors.defaultThreadFactory());
    }

    public UnorderedThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, IoEventQueueHandler ioEventQueueHandler) {
        this(i, i2, j, timeUnit, Executors.defaultThreadFactory(), ioEventQueueHandler);
    }

    public UnorderedThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
        this(i, i2, j, timeUnit, threadFactory, (IoEventQueueHandler) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UnorderedThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, ThreadFactory threadFactory, IoEventQueueHandler ioEventQueueHandler) {
        super(0, 1, j, timeUnit, new LinkedBlockingQueue(), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        int i3 = i;
        int i4 = i2;
        this.workers = new HashSet();
        this.idleWorkers = new AtomicInteger();
        if (i3 < 0) {
            throw new IllegalArgumentException("corePoolSize: " + i);
        } else if (i4 == 0 || i4 < i3) {
            throw new IllegalArgumentException("maximumPoolSize: " + i2);
        } else {
            IoEventQueueHandler ioEventQueueHandler2 = ioEventQueueHandler == null ? IoEventQueueHandler.NOOP : ioEventQueueHandler;
            this.corePoolSize = i3;
            this.maximumPoolSize = i4;
            this.queueHandler = ioEventQueueHandler2;
        }
    }

    public IoEventQueueHandler getQueueHandler() {
        return this.queueHandler;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void addWorker() {
        /*
            r4 = this;
            java.util.Set<org.apache.mina.filter.executor.UnorderedThreadPoolExecutor$Worker> r0 = r4.workers
            monitor-enter(r0)
            java.util.Set<org.apache.mina.filter.executor.UnorderedThreadPoolExecutor$Worker> r1 = r4.workers     // Catch:{ all -> 0x003e }
            int r1 = r1.size()     // Catch:{ all -> 0x003e }
            int r2 = r4.maximumPoolSize     // Catch:{ all -> 0x003e }
            if (r1 < r2) goto L_0x000f
            monitor-exit(r0)     // Catch:{ all -> 0x003e }
            return
        L_0x000f:
            org.apache.mina.filter.executor.UnorderedThreadPoolExecutor$Worker r1 = new org.apache.mina.filter.executor.UnorderedThreadPoolExecutor$Worker     // Catch:{ all -> 0x003e }
            r2 = 0
            r1.<init>()     // Catch:{ all -> 0x003e }
            java.util.concurrent.ThreadFactory r2 = r4.getThreadFactory()     // Catch:{ all -> 0x003e }
            java.lang.Thread r2 = r2.newThread(r1)     // Catch:{ all -> 0x003e }
            java.util.concurrent.atomic.AtomicInteger r3 = r4.idleWorkers     // Catch:{ all -> 0x003e }
            r3.incrementAndGet()     // Catch:{ all -> 0x003e }
            r2.start()     // Catch:{ all -> 0x003e }
            java.util.Set<org.apache.mina.filter.executor.UnorderedThreadPoolExecutor$Worker> r2 = r4.workers     // Catch:{ all -> 0x003e }
            r2.add(r1)     // Catch:{ all -> 0x003e }
            java.util.Set<org.apache.mina.filter.executor.UnorderedThreadPoolExecutor$Worker> r1 = r4.workers     // Catch:{ all -> 0x003e }
            int r1 = r1.size()     // Catch:{ all -> 0x003e }
            int r2 = r4.largestPoolSize     // Catch:{ all -> 0x003e }
            if (r1 <= r2) goto L_0x003c
            java.util.Set<org.apache.mina.filter.executor.UnorderedThreadPoolExecutor$Worker> r1 = r4.workers     // Catch:{ all -> 0x003e }
            int r1 = r1.size()     // Catch:{ all -> 0x003e }
            r4.largestPoolSize = r1     // Catch:{ all -> 0x003e }
        L_0x003c:
            monitor-exit(r0)     // Catch:{ all -> 0x003e }
            return
        L_0x003e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.filter.executor.UnorderedThreadPoolExecutor.addWorker():void");
    }

    private void addWorkerIfNecessary() {
        if (this.idleWorkers.get() == 0) {
            synchronized (this.workers) {
                if (this.workers.isEmpty() || this.idleWorkers.get() == 0) {
                    addWorker();
                }
            }
        }
    }

    private void removeWorker() {
        synchronized (this.workers) {
            if (this.workers.size() > this.corePoolSize) {
                getQueue().offer(EXIT_SIGNAL);
            }
        }
    }

    public int getMaximumPoolSize() {
        return this.maximumPoolSize;
    }

    public void setMaximumPoolSize(int i) {
        if (i <= 0 || i < this.corePoolSize) {
            throw new IllegalArgumentException("maximumPoolSize: " + i);
        }
        synchronized (this.workers) {
            this.maximumPoolSize = i;
            for (int size = this.workers.size() - i; size > 0; size--) {
                removeWorker();
            }
        }
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis() + timeUnit.toMillis(j);
        synchronized (this.workers) {
            while (true) {
                if (isTerminated()) {
                    break;
                }
                long currentTimeMillis2 = currentTimeMillis - System.currentTimeMillis();
                if (currentTimeMillis2 <= 0) {
                    break;
                }
                this.workers.wait(currentTimeMillis2);
            }
        }
        return isTerminated();
    }

    public boolean isShutdown() {
        return this.shutdown;
    }

    public boolean isTerminated() {
        boolean isEmpty;
        if (!this.shutdown) {
            return false;
        }
        synchronized (this.workers) {
            isEmpty = this.workers.isEmpty();
        }
        return isEmpty;
    }

    public void shutdown() {
        if (!this.shutdown) {
            this.shutdown = true;
            synchronized (this.workers) {
                for (int size = this.workers.size(); size > 0; size--) {
                    getQueue().offer(EXIT_SIGNAL);
                }
            }
        }
    }

    public List<Runnable> shutdownNow() {
        shutdown();
        ArrayList arrayList = new ArrayList();
        while (true) {
            Runnable runnable = (Runnable) getQueue().poll();
            if (runnable == null) {
                return arrayList;
            }
            if (runnable == EXIT_SIGNAL) {
                getQueue().offer(EXIT_SIGNAL);
                Thread.yield();
            } else {
                getQueueHandler().polled(this, (IoEvent) runnable);
                arrayList.add(runnable);
            }
        }
    }

    public void execute(Runnable runnable) {
        if (this.shutdown) {
            rejectTask(runnable);
        }
        checkTaskType(runnable);
        IoEvent ioEvent = (IoEvent) runnable;
        boolean accept = this.queueHandler.accept(this, ioEvent);
        if (accept) {
            getQueue().offer(ioEvent);
        }
        addWorkerIfNecessary();
        if (accept) {
            this.queueHandler.offered(this, ioEvent);
        }
    }

    private void rejectTask(Runnable runnable) {
        getRejectedExecutionHandler().rejectedExecution(runnable, this);
    }

    private void checkTaskType(Runnable runnable) {
        if (!(runnable instanceof IoEvent)) {
            throw new IllegalArgumentException("task must be an IoEvent or its subclass.");
        }
    }

    public int getActiveCount() {
        int size;
        synchronized (this.workers) {
            size = this.workers.size() - this.idleWorkers.get();
        }
        return size;
    }

    public long getCompletedTaskCount() {
        long j;
        synchronized (this.workers) {
            j = this.completedTaskCount;
            for (Worker access$100 : this.workers) {
                j += access$100.completedTaskCount;
            }
        }
        return j;
    }

    public int getLargestPoolSize() {
        return this.largestPoolSize;
    }

    public int getPoolSize() {
        int size;
        synchronized (this.workers) {
            size = this.workers.size();
        }
        return size;
    }

    public long getTaskCount() {
        return getCompletedTaskCount();
    }

    public boolean isTerminating() {
        boolean z;
        synchronized (this.workers) {
            z = isShutdown() && !isTerminated();
        }
        return z;
    }

    public int prestartAllCoreThreads() {
        int i;
        synchronized (this.workers) {
            i = 0;
            for (int size = this.corePoolSize - this.workers.size(); size > 0; size--) {
                addWorker();
                i++;
            }
        }
        return i;
    }

    public boolean prestartCoreThread() {
        synchronized (this.workers) {
            if (this.workers.size() >= this.corePoolSize) {
                return false;
            }
            addWorker();
            return true;
        }
    }

    public boolean remove(Runnable runnable) {
        boolean remove = super.remove(runnable);
        if (remove) {
            getQueueHandler().polled(this, (IoEvent) runnable);
        }
        return remove;
    }

    public int getCorePoolSize() {
        return this.corePoolSize;
    }

    public void setCorePoolSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("corePoolSize: " + i);
        } else if (i <= this.maximumPoolSize) {
            synchronized (this.workers) {
                if (this.corePoolSize > i) {
                    for (int i2 = this.corePoolSize - i; i2 > 0; i2--) {
                        removeWorker();
                    }
                }
                this.corePoolSize = i;
            }
        } else {
            throw new IllegalArgumentException("corePoolSize exceeds maximumPoolSize");
        }
    }

    private class Worker implements Runnable {
        /* access modifiers changed from: private */
        public volatile long completedTaskCount;
        private Thread thread;

        private Worker() {
        }

        public void run() {
            this.thread = Thread.currentThread();
            while (true) {
                try {
                    Runnable fetchTask = fetchTask();
                    UnorderedThreadPoolExecutor.this.idleWorkers.decrementAndGet();
                    if (fetchTask == null) {
                        synchronized (UnorderedThreadPoolExecutor.this.workers) {
                            if (UnorderedThreadPoolExecutor.this.workers.size() > UnorderedThreadPoolExecutor.this.corePoolSize) {
                                UnorderedThreadPoolExecutor.this.workers.remove(this);
                                break;
                            }
                        }
                    }
                    if (fetchTask == UnorderedThreadPoolExecutor.EXIT_SIGNAL) {
                        break;
                    }
                    if (fetchTask != null) {
                        UnorderedThreadPoolExecutor.this.queueHandler.polled(UnorderedThreadPoolExecutor.this, (IoEvent) fetchTask);
                        runTask(fetchTask);
                    }
                    UnorderedThreadPoolExecutor.this.idleWorkers.incrementAndGet();
                } catch (Throwable th) {
                    synchronized (UnorderedThreadPoolExecutor.this.workers) {
                        UnorderedThreadPoolExecutor.this.workers.remove(this);
                        UnorderedThreadPoolExecutor.access$714(UnorderedThreadPoolExecutor.this, this.completedTaskCount);
                        UnorderedThreadPoolExecutor.this.workers.notifyAll();
                        throw th;
                    }
                }
            }
            synchronized (UnorderedThreadPoolExecutor.this.workers) {
                UnorderedThreadPoolExecutor.this.workers.remove(this);
                UnorderedThreadPoolExecutor.access$714(UnorderedThreadPoolExecutor.this, this.completedTaskCount);
                UnorderedThreadPoolExecutor.this.workers.notifyAll();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
            return r5;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.Runnable fetchTask() {
            /*
                r10 = this;
                long r0 = java.lang.System.currentTimeMillis()
                org.apache.mina.filter.executor.UnorderedThreadPoolExecutor r2 = org.apache.mina.filter.executor.UnorderedThreadPoolExecutor.this
                java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS
                long r2 = r2.getKeepAliveTime(r3)
                long r2 = r2 + r0
                r4 = 0
            L_0x000e:
                long r5 = r2 - r0
                r7 = 0
                int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r9 > 0) goto L_0x0017
                goto L_0x002e
            L_0x0017:
                org.apache.mina.filter.executor.UnorderedThreadPoolExecutor r7 = org.apache.mina.filter.executor.UnorderedThreadPoolExecutor.this     // Catch:{ all -> 0x002f }
                java.util.concurrent.BlockingQueue r7 = r7.getQueue()     // Catch:{ all -> 0x002f }
                java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x002f }
                java.lang.Object r5 = r7.poll(r5, r8)     // Catch:{ all -> 0x002f }
                java.lang.Runnable r5 = (java.lang.Runnable) r5     // Catch:{ all -> 0x002f }
                if (r5 != 0) goto L_0x002d
                java.lang.System.currentTimeMillis()     // Catch:{ InterruptedException -> 0x002b }
                goto L_0x002d
            L_0x002b:
                r4 = r5
                goto L_0x000e
            L_0x002d:
                r4 = r5
            L_0x002e:
                return r4
            L_0x002f:
                r5 = move-exception
                if (r4 != 0) goto L_0x0036
                long r0 = java.lang.System.currentTimeMillis()     // Catch:{ InterruptedException -> 0x0037 }
            L_0x0036:
                throw r5     // Catch:{ InterruptedException -> 0x0037 }
            L_0x0037:
                goto L_0x000e
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.filter.executor.UnorderedThreadPoolExecutor.Worker.fetchTask():java.lang.Runnable");
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x001f  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void runTask(java.lang.Runnable r6) {
            /*
                r5 = this;
                org.apache.mina.filter.executor.UnorderedThreadPoolExecutor r0 = org.apache.mina.filter.executor.UnorderedThreadPoolExecutor.this
                java.lang.Thread r1 = r5.thread
                r0.beforeExecute(r1, r6)
                r6.run()     // Catch:{ RuntimeException -> 0x001b }
                r0 = 1
                org.apache.mina.filter.executor.UnorderedThreadPoolExecutor r1 = org.apache.mina.filter.executor.UnorderedThreadPoolExecutor.this     // Catch:{ RuntimeException -> 0x0019 }
                r2 = 0
                r1.afterExecute(r6, r2)     // Catch:{ RuntimeException -> 0x0019 }
                long r1 = r5.completedTaskCount     // Catch:{ RuntimeException -> 0x0019 }
                r3 = 1
                long r1 = r1 + r3
                r5.completedTaskCount = r1     // Catch:{ RuntimeException -> 0x0019 }
                return
            L_0x0019:
                r1 = move-exception
                goto L_0x001d
            L_0x001b:
                r1 = move-exception
                r0 = 0
            L_0x001d:
                if (r0 != 0) goto L_0x0024
                org.apache.mina.filter.executor.UnorderedThreadPoolExecutor r0 = org.apache.mina.filter.executor.UnorderedThreadPoolExecutor.this
                r0.afterExecute(r6, r1)
            L_0x0024:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.filter.executor.UnorderedThreadPoolExecutor.Worker.runTask(java.lang.Runnable):void");
        }
    }
}
