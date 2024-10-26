package org.apache.mina.core.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;
import org.apache.mina.core.polling.AbstractPollingIoProcessor;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.util.ExceptionMonitor;

public class DefaultIoFuture implements IoFuture {
    private static final long DEAD_LOCK_CHECK_INTERVAL = 5000;
    private IoFutureListener<?> firstListener;
    private final Object lock = this;
    private List<IoFutureListener<?>> otherListeners;
    private boolean ready;
    private Object result;
    private final IoSession session;
    private int waiters;

    public DefaultIoFuture(IoSession ioSession) {
        this.session = ioSession;
    }

    public IoSession getSession() {
        return this.session;
    }

    @Deprecated
    public void join() {
        awaitUninterruptibly();
    }

    @Deprecated
    public boolean join(long j) {
        return awaitUninterruptibly(j);
    }

    /* JADX INFO: finally extract failed */
    public IoFuture await() throws InterruptedException {
        synchronized (this.lock) {
            while (!this.ready) {
                this.waiters++;
                try {
                    this.lock.wait(5000);
                    this.waiters--;
                    if (!this.ready) {
                        checkDeadLock();
                    }
                } catch (Throwable th) {
                    this.waiters--;
                    if (!this.ready) {
                        checkDeadLock();
                    }
                    throw th;
                }
            }
        }
        return this;
    }

    public boolean await(long j, TimeUnit timeUnit) throws InterruptedException {
        return await(timeUnit.toMillis(j));
    }

    public boolean await(long j) throws InterruptedException {
        return await0(j, true);
    }

    public IoFuture awaitUninterruptibly() {
        try {
            await0(LongCompanionObject.MAX_VALUE, false);
        } catch (InterruptedException unused) {
        }
        return this;
    }

    public boolean awaitUninterruptibly(long j, TimeUnit timeUnit) {
        return awaitUninterruptibly(timeUnit.toMillis(j));
    }

    public boolean awaitUninterruptibly(long j) {
        try {
            return await0(j, false);
        } catch (InterruptedException unused) {
            throw new InternalError();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004b, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0063, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean await0(long r8, boolean r10) throws java.lang.InterruptedException {
        /*
            r7 = this;
            long r0 = java.lang.System.currentTimeMillis()
            long r0 = r0 + r8
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x0010
            r0 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
        L_0x0010:
            java.lang.Object r4 = r7.lock
            monitor-enter(r4)
            boolean r5 = r7.ready     // Catch:{ all -> 0x0072 }
            if (r5 == 0) goto L_0x001b
            boolean r8 = r7.ready     // Catch:{ all -> 0x0072 }
            monitor-exit(r4)     // Catch:{ all -> 0x0072 }
            return r8
        L_0x001b:
            int r5 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r5 > 0) goto L_0x0023
            boolean r8 = r7.ready     // Catch:{ all -> 0x0072 }
            monitor-exit(r4)     // Catch:{ all -> 0x0072 }
            return r8
        L_0x0023:
            int r2 = r7.waiters     // Catch:{ all -> 0x0072 }
            r3 = 1
            int r2 = r2 + r3
            r7.waiters = r2     // Catch:{ all -> 0x0072 }
        L_0x0029:
            r5 = 5000(0x1388, double:2.4703E-320)
            long r5 = java.lang.Math.min(r8, r5)     // Catch:{ InterruptedException -> 0x0037 }
            java.lang.Object r2 = r7.lock     // Catch:{ InterruptedException -> 0x0037 }
            r2.wait(r5)     // Catch:{ InterruptedException -> 0x0037 }
            goto L_0x003a
        L_0x0035:
            r8 = move-exception
            goto L_0x0065
        L_0x0037:
            r2 = move-exception
            if (r10 != 0) goto L_0x0064
        L_0x003a:
            boolean r2 = r7.ready     // Catch:{ all -> 0x0035 }
            if (r2 == 0) goto L_0x004c
            int r8 = r7.waiters     // Catch:{ all -> 0x0072 }
            int r8 = r8 - r3
            r7.waiters = r8     // Catch:{ all -> 0x0072 }
            boolean r8 = r7.ready     // Catch:{ all -> 0x0072 }
            if (r8 != 0) goto L_0x004a
            r7.checkDeadLock()     // Catch:{ all -> 0x0072 }
        L_0x004a:
            monitor-exit(r4)     // Catch:{ all -> 0x0072 }
            return r3
        L_0x004c:
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0035 }
            int r2 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r2 >= 0) goto L_0x0029
            boolean r8 = r7.ready     // Catch:{ all -> 0x0035 }
            int r9 = r7.waiters     // Catch:{ all -> 0x0072 }
            int r9 = r9 - r3
            r7.waiters = r9     // Catch:{ all -> 0x0072 }
            boolean r9 = r7.ready     // Catch:{ all -> 0x0072 }
            if (r9 != 0) goto L_0x0062
            r7.checkDeadLock()     // Catch:{ all -> 0x0072 }
        L_0x0062:
            monitor-exit(r4)     // Catch:{ all -> 0x0072 }
            return r8
        L_0x0064:
            throw r2     // Catch:{ all -> 0x0035 }
        L_0x0065:
            int r9 = r7.waiters     // Catch:{ all -> 0x0072 }
            int r9 = r9 - r3
            r7.waiters = r9     // Catch:{ all -> 0x0072 }
            boolean r9 = r7.ready     // Catch:{ all -> 0x0072 }
            if (r9 != 0) goto L_0x0071
            r7.checkDeadLock()     // Catch:{ all -> 0x0072 }
        L_0x0071:
            throw r8     // Catch:{ all -> 0x0072 }
        L_0x0072:
            r8 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0072 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.core.future.DefaultIoFuture.await0(long, boolean):boolean");
    }

    private void checkDeadLock() {
        if ((this instanceof CloseFuture) || (this instanceof WriteFuture) || (this instanceof ReadFuture) || (this instanceof ConnectFuture)) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            int length = stackTrace.length;
            int i = 0;
            int i2 = 0;
            while (i2 < length) {
                if (!AbstractPollingIoProcessor.class.getName().equals(stackTrace[i2].getClassName())) {
                    i2++;
                } else {
                    new IllegalStateException("t").getStackTrace();
                    throw new IllegalStateException("DEAD LOCK: " + IoFuture.class.getSimpleName() + ".await() was invoked from an I/O processor thread.  " + "Please use " + IoFutureListener.class.getSimpleName() + " or configure a proper thread model alternatively.");
                }
            }
            int length2 = stackTrace.length;
            while (i < length2) {
                try {
                    if (!IoProcessor.class.isAssignableFrom(DefaultIoFuture.class.getClassLoader().loadClass(stackTrace[i].getClassName()))) {
                        i++;
                    } else {
                        throw new IllegalStateException("DEAD LOCK: " + IoFuture.class.getSimpleName() + ".await() was invoked from an I/O processor thread.  " + "Please use " + IoFutureListener.class.getSimpleName() + " or configure a proper thread model alternatively.");
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    public boolean isDone() {
        boolean z;
        synchronized (this.lock) {
            z = this.ready;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        notifyListeners();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setValue(java.lang.Object r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.lock
            monitor-enter(r0)
            boolean r1 = r2.ready     // Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            return
        L_0x0009:
            r2.result = r3     // Catch:{ all -> 0x001c }
            r3 = 1
            r2.ready = r3     // Catch:{ all -> 0x001c }
            int r3 = r2.waiters     // Catch:{ all -> 0x001c }
            if (r3 <= 0) goto L_0x0017
            java.lang.Object r3 = r2.lock     // Catch:{ all -> 0x001c }
            r3.notifyAll()     // Catch:{ all -> 0x001c }
        L_0x0017:
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            r2.notifyListeners()
            return
        L_0x001c:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.core.future.DefaultIoFuture.setValue(java.lang.Object):void");
    }

    /* access modifiers changed from: protected */
    public Object getValue() {
        Object obj;
        synchronized (this.lock) {
            obj = this.result;
        }
        return obj;
    }

    public IoFuture addListener(IoFutureListener<?> ioFutureListener) {
        if (ioFutureListener != null) {
            boolean z = false;
            synchronized (this.lock) {
                if (this.ready) {
                    z = true;
                } else if (this.firstListener == null) {
                    this.firstListener = ioFutureListener;
                } else {
                    if (this.otherListeners == null) {
                        this.otherListeners = new ArrayList(1);
                    }
                    this.otherListeners.add(ioFutureListener);
                }
            }
            if (z) {
                notifyListener(ioFutureListener);
            }
            return this;
        }
        throw new IllegalArgumentException("listener");
    }

    public IoFuture removeListener(IoFutureListener<?> ioFutureListener) {
        if (ioFutureListener != null) {
            synchronized (this.lock) {
                if (!this.ready) {
                    if (ioFutureListener == this.firstListener) {
                        if (this.otherListeners == null || this.otherListeners.isEmpty()) {
                            this.firstListener = null;
                        } else {
                            this.firstListener = this.otherListeners.remove(0);
                        }
                    } else if (this.otherListeners != null) {
                        this.otherListeners.remove(ioFutureListener);
                    }
                }
            }
            return this;
        }
        throw new IllegalArgumentException("listener");
    }

    private void notifyListeners() {
        IoFutureListener<?> ioFutureListener = this.firstListener;
        if (ioFutureListener != null) {
            notifyListener(ioFutureListener);
            this.firstListener = null;
            List<IoFutureListener<?>> list = this.otherListeners;
            if (list != null) {
                for (IoFutureListener<?> notifyListener : list) {
                    notifyListener(notifyListener);
                }
                this.otherListeners = null;
            }
        }
    }

    private void notifyListener(IoFutureListener ioFutureListener) {
        try {
            ioFutureListener.operationComplete(this);
        } catch (Throwable th) {
            ExceptionMonitor.getInstance().exceptionCaught(th);
        }
    }
}
