package org.apache.mina.filter.executor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.DummySession;
import org.apache.mina.core.session.IoEvent;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderedThreadPoolExecutor extends ThreadPoolExecutor {
    private static final int DEFAULT_INITIAL_THREAD_POOL_SIZE = 0;
    private static final int DEFAULT_KEEP_ALIVE = 30;
    private static final int DEFAULT_MAX_THREAD_POOL = 16;
    /* access modifiers changed from: private */
    public static final IoSession EXIT_SIGNAL = new DummySession();
    static Logger LOGGER = LoggerFactory.getLogger(OrderedThreadPoolExecutor.class);
    private final AttributeKey TASKS_QUEUE;
    private long completedTaskCount;
    /* access modifiers changed from: private */
    public final IoEventQueueHandler eventQueueHandler;
    /* access modifiers changed from: private */
    public final AtomicInteger idleWorkers;
    private volatile int largestPoolSize;
    private volatile boolean shutdown;
    /* access modifiers changed from: private */
    public final BlockingQueue<IoSession> waitingSessions;
    /* access modifiers changed from: private */
    public final Set<Worker> workers;

    public void purge() {
    }

    public void setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
    }

    static /* synthetic */ long access$914(OrderedThreadPoolExecutor orderedThreadPoolExecutor, long j) {
        long j2 = orderedThreadPoolExecutor.completedTaskCount + j;
        orderedThreadPoolExecutor.completedTaskCount = j2;
        return j2;
    }

    public OrderedThreadPoolExecutor() {
        this(0, 16, 30, TimeUnit.SECONDS, Executors.defaultThreadFactory(), (IoEventQueueHandler) null);
    }

    public OrderedThreadPoolExecutor(int i) {
        this(0, i, 30, TimeUnit.SECONDS, Executors.defaultThreadFactory(), (IoEventQueueHandler) null);
    }

    public OrderedThreadPoolExecutor(int i, int i2) {
        this(i, i2, 30, TimeUnit.SECONDS, Executors.defaultThreadFactory(), (IoEventQueueHandler) null);
    }

    public OrderedThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit) {
        this(i, i2, j, timeUnit, Executors.defaultThreadFactory(), (IoEventQueueHandler) null);
    }

    public OrderedThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, IoEventQueueHandler ioEventQueueHandler) {
        this(i, i2, j, timeUnit, Executors.defaultThreadFactory(), ioEventQueueHandler);
    }

    public OrderedThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
        this(i, i2, j, timeUnit, threadFactory, (IoEventQueueHandler) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OrderedThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, ThreadFactory threadFactory, IoEventQueueHandler ioEventQueueHandler) {
        super(0, 1, j, timeUnit, new SynchronousQueue(), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        int i3 = i;
        int i4 = i2;
        IoEventQueueHandler ioEventQueueHandler2 = ioEventQueueHandler;
        this.TASKS_QUEUE = new AttributeKey(getClass(), "tasksQueue");
        this.waitingSessions = new LinkedBlockingQueue();
        this.workers = new HashSet();
        this.idleWorkers = new AtomicInteger();
        if (i3 < 0) {
            throw new IllegalArgumentException("corePoolSize: " + i);
        } else if (i4 == 0 || i4 < i3) {
            throw new IllegalArgumentException("maximumPoolSize: " + i2);
        } else {
            super.setCorePoolSize(i);
            super.setMaximumPoolSize(i2);
            if (ioEventQueueHandler2 == null) {
                this.eventQueueHandler = IoEventQueueHandler.NOOP;
            } else {
                this.eventQueueHandler = ioEventQueueHandler2;
            }
        }
    }

    /* access modifiers changed from: private */
    public SessionTasksQueue getSessionTasksQueue(IoSession ioSession) {
        SessionTasksQueue sessionTasksQueue = (SessionTasksQueue) ioSession.getAttribute(this.TASKS_QUEUE);
        if (sessionTasksQueue != null) {
            return sessionTasksQueue;
        }
        SessionTasksQueue sessionTasksQueue2 = new SessionTasksQueue();
        SessionTasksQueue sessionTasksQueue3 = (SessionTasksQueue) ioSession.setAttributeIfAbsent(this.TASKS_QUEUE, sessionTasksQueue2);
        return sessionTasksQueue3 != null ? sessionTasksQueue3 : sessionTasksQueue2;
    }

    public IoEventQueueHandler getQueueHandler() {
        return this.eventQueueHandler;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void addWorker() {
        /*
            r4 = this;
            java.util.Set<org.apache.mina.filter.executor.OrderedThreadPoolExecutor$Worker> r0 = r4.workers
            monitor-enter(r0)
            java.util.Set<org.apache.mina.filter.executor.OrderedThreadPoolExecutor$Worker> r1 = r4.workers     // Catch:{ all -> 0x0040 }
            int r1 = r1.size()     // Catch:{ all -> 0x0040 }
            int r2 = super.getMaximumPoolSize()     // Catch:{ all -> 0x0040 }
            if (r1 < r2) goto L_0x0011
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            return
        L_0x0011:
            org.apache.mina.filter.executor.OrderedThreadPoolExecutor$Worker r1 = new org.apache.mina.filter.executor.OrderedThreadPoolExecutor$Worker     // Catch:{ all -> 0x0040 }
            r2 = 0
            r1.<init>()     // Catch:{ all -> 0x0040 }
            java.util.concurrent.ThreadFactory r2 = r4.getThreadFactory()     // Catch:{ all -> 0x0040 }
            java.lang.Thread r2 = r2.newThread(r1)     // Catch:{ all -> 0x0040 }
            java.util.concurrent.atomic.AtomicInteger r3 = r4.idleWorkers     // Catch:{ all -> 0x0040 }
            r3.incrementAndGet()     // Catch:{ all -> 0x0040 }
            r2.start()     // Catch:{ all -> 0x0040 }
            java.util.Set<org.apache.mina.filter.executor.OrderedThreadPoolExecutor$Worker> r2 = r4.workers     // Catch:{ all -> 0x0040 }
            r2.add(r1)     // Catch:{ all -> 0x0040 }
            java.util.Set<org.apache.mina.filter.executor.OrderedThreadPoolExecutor$Worker> r1 = r4.workers     // Catch:{ all -> 0x0040 }
            int r1 = r1.size()     // Catch:{ all -> 0x0040 }
            int r2 = r4.largestPoolSize     // Catch:{ all -> 0x0040 }
            if (r1 <= r2) goto L_0x003e
            java.util.Set<org.apache.mina.filter.executor.OrderedThreadPoolExecutor$Worker> r1 = r4.workers     // Catch:{ all -> 0x0040 }
            int r1 = r1.size()     // Catch:{ all -> 0x0040 }
            r4.largestPoolSize = r1     // Catch:{ all -> 0x0040 }
        L_0x003e:
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            return
        L_0x0040:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.filter.executor.OrderedThreadPoolExecutor.addWorker():void");
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
            if (this.workers.size() > super.getCorePoolSize()) {
                this.waitingSessions.offer(EXIT_SIGNAL);
            }
        }
    }

    public int getMaximumPoolSize() {
        return super.getMaximumPoolSize();
    }

    public void setMaximumPoolSize(int i) {
        if (i <= 0 || i < super.getCorePoolSize()) {
            throw new IllegalArgumentException("maximumPoolSize: " + i);
        }
        synchronized (this.workers) {
            super.setMaximumPoolSize(i);
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
                    this.waitingSessions.offer(EXIT_SIGNAL);
                }
            }
        }
    }

    public List<Runnable> shutdownNow() {
        shutdown();
        ArrayList arrayList = new ArrayList();
        while (true) {
            IoSession ioSession = (IoSession) this.waitingSessions.poll();
            if (ioSession == null) {
                return arrayList;
            }
            IoSession ioSession2 = EXIT_SIGNAL;
            if (ioSession == ioSession2) {
                this.waitingSessions.offer(ioSession2);
                Thread.yield();
            } else {
                SessionTasksQueue sessionTasksQueue = (SessionTasksQueue) ioSession.getAttribute(this.TASKS_QUEUE);
                synchronized (sessionTasksQueue.tasksQueue) {
                    for (Runnable runnable : sessionTasksQueue.tasksQueue) {
                        getQueueHandler().polled(this, (IoEvent) runnable);
                        arrayList.add(runnable);
                    }
                    sessionTasksQueue.tasksQueue.clear();
                }
            }
        }
    }

    private void print(Queue<Runnable> queue, IoEvent ioEvent) {
        StringBuilder sb = new StringBuilder();
        sb.append("Adding event ");
        sb.append(ioEvent.getType());
        sb.append(" to session ");
        sb.append(ioEvent.getSession().getId());
        sb.append("\nQueue : [");
        boolean z = true;
        for (Runnable runnable : queue) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(((IoEvent) runnable).getType());
            sb.append(", ");
        }
        sb.append("]\n");
        LOGGER.debug(sb.toString());
    }

    public void execute(Runnable runnable) {
        if (this.shutdown) {
            rejectTask(runnable);
        }
        checkTaskType(runnable);
        IoEvent ioEvent = (IoEvent) runnable;
        IoSession session = ioEvent.getSession();
        SessionTasksQueue sessionTasksQueue = getSessionTasksQueue(session);
        Queue access$200 = sessionTasksQueue.tasksQueue;
        boolean accept = this.eventQueueHandler.accept(this, ioEvent);
        boolean z = false;
        if (accept) {
            synchronized (access$200) {
                access$200.offer(ioEvent);
                if (sessionTasksQueue.processingCompleted) {
                    boolean unused = sessionTasksQueue.processingCompleted = false;
                    z = true;
                }
                if (LOGGER.isDebugEnabled()) {
                    print(access$200, ioEvent);
                }
            }
        }
        if (z) {
            this.waitingSessions.offer(session);
        }
        addWorkerIfNecessary();
        if (accept) {
            this.eventQueueHandler.offered(this, ioEvent);
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
            for (Worker access$400 : this.workers) {
                j += access$400.completedTaskCount;
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
            for (int corePoolSize = super.getCorePoolSize() - this.workers.size(); corePoolSize > 0; corePoolSize--) {
                addWorker();
                i++;
            }
        }
        return i;
    }

    public boolean prestartCoreThread() {
        synchronized (this.workers) {
            if (this.workers.size() >= super.getCorePoolSize()) {
                return false;
            }
            addWorker();
            return true;
        }
    }

    public BlockingQueue<Runnable> getQueue() {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Runnable runnable) {
        boolean remove;
        checkTaskType(runnable);
        IoEvent ioEvent = (IoEvent) runnable;
        SessionTasksQueue sessionTasksQueue = (SessionTasksQueue) ioEvent.getSession().getAttribute(this.TASKS_QUEUE);
        Queue access$200 = sessionTasksQueue.tasksQueue;
        if (sessionTasksQueue == null) {
            return false;
        }
        synchronized (access$200) {
            remove = access$200.remove(runnable);
        }
        if (remove) {
            getQueueHandler().polled(this, ioEvent);
        }
        return remove;
    }

    public int getCorePoolSize() {
        return super.getCorePoolSize();
    }

    public void setCorePoolSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("corePoolSize: " + i);
        } else if (i <= super.getMaximumPoolSize()) {
            synchronized (this.workers) {
                if (super.getCorePoolSize() > i) {
                    for (int corePoolSize = super.getCorePoolSize() - i; corePoolSize > 0; corePoolSize--) {
                        removeWorker();
                    }
                }
                super.setCorePoolSize(i);
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
                    IoSession fetchSession = fetchSession();
                    OrderedThreadPoolExecutor.this.idleWorkers.decrementAndGet();
                    if (fetchSession == null) {
                        synchronized (OrderedThreadPoolExecutor.this.workers) {
                            if (OrderedThreadPoolExecutor.this.workers.size() > OrderedThreadPoolExecutor.this.getCorePoolSize()) {
                                OrderedThreadPoolExecutor.this.workers.remove(this);
                                break;
                            }
                        }
                    }
                    if (fetchSession == OrderedThreadPoolExecutor.EXIT_SIGNAL) {
                        break;
                    }
                    if (fetchSession != null) {
                        runTasks(OrderedThreadPoolExecutor.this.getSessionTasksQueue(fetchSession));
                    }
                    OrderedThreadPoolExecutor.this.idleWorkers.incrementAndGet();
                } catch (Throwable th) {
                    synchronized (OrderedThreadPoolExecutor.this.workers) {
                        OrderedThreadPoolExecutor.this.workers.remove(this);
                        OrderedThreadPoolExecutor.access$914(OrderedThreadPoolExecutor.this, this.completedTaskCount);
                        OrderedThreadPoolExecutor.this.workers.notifyAll();
                        throw th;
                    }
                }
            }
            synchronized (OrderedThreadPoolExecutor.this.workers) {
                OrderedThreadPoolExecutor.this.workers.remove(this);
                OrderedThreadPoolExecutor.access$914(OrderedThreadPoolExecutor.this, this.completedTaskCount);
                OrderedThreadPoolExecutor.this.workers.notifyAll();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
            return r5;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private org.apache.mina.core.session.IoSession fetchSession() {
            /*
                r10 = this;
                long r0 = java.lang.System.currentTimeMillis()
                org.apache.mina.filter.executor.OrderedThreadPoolExecutor r2 = org.apache.mina.filter.executor.OrderedThreadPoolExecutor.this
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
                org.apache.mina.filter.executor.OrderedThreadPoolExecutor r7 = org.apache.mina.filter.executor.OrderedThreadPoolExecutor.this     // Catch:{ all -> 0x002f }
                java.util.concurrent.BlockingQueue r7 = r7.waitingSessions     // Catch:{ all -> 0x002f }
                java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x002f }
                java.lang.Object r5 = r7.poll(r5, r8)     // Catch:{ all -> 0x002f }
                org.apache.mina.core.session.IoSession r5 = (org.apache.mina.core.session.IoSession) r5     // Catch:{ all -> 0x002f }
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.filter.executor.OrderedThreadPoolExecutor.Worker.fetchSession():org.apache.mina.core.session.IoSession");
        }

        private void runTasks(SessionTasksQueue sessionTasksQueue) {
            Runnable runnable;
            while (true) {
                Queue access$200 = sessionTasksQueue.tasksQueue;
                synchronized (access$200) {
                    runnable = (Runnable) access$200.poll();
                    if (runnable == null) {
                        boolean unused = sessionTasksQueue.processingCompleted = true;
                        return;
                    }
                }
                OrderedThreadPoolExecutor.this.eventQueueHandler.polled(OrderedThreadPoolExecutor.this, (IoEvent) runnable);
                runTask(runnable);
            }
            while (true) {
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x001f  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void runTask(java.lang.Runnable r6) {
            /*
                r5 = this;
                org.apache.mina.filter.executor.OrderedThreadPoolExecutor r0 = org.apache.mina.filter.executor.OrderedThreadPoolExecutor.this
                java.lang.Thread r1 = r5.thread
                r0.beforeExecute(r1, r6)
                r6.run()     // Catch:{ RuntimeException -> 0x001b }
                r0 = 1
                org.apache.mina.filter.executor.OrderedThreadPoolExecutor r1 = org.apache.mina.filter.executor.OrderedThreadPoolExecutor.this     // Catch:{ RuntimeException -> 0x0019 }
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
                org.apache.mina.filter.executor.OrderedThreadPoolExecutor r0 = org.apache.mina.filter.executor.OrderedThreadPoolExecutor.this
                r0.afterExecute(r6, r1)
            L_0x0024:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.filter.executor.OrderedThreadPoolExecutor.Worker.runTask(java.lang.Runnable):void");
        }
    }

    private class SessionTasksQueue {
        /* access modifiers changed from: private */
        public boolean processingCompleted;
        /* access modifiers changed from: private */
        public final Queue<Runnable> tasksQueue;

        private SessionTasksQueue() {
            this.tasksQueue = new ConcurrentLinkedQueue();
            this.processingCompleted = true;
        }
    }
}
