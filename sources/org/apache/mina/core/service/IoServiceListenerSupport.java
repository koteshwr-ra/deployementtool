package org.apache.mina.core.service;

import androidx.core.app.NotificationCompat;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.util.ExceptionMonitor;

public class IoServiceListenerSupport {
    private final AtomicBoolean activated;
    private volatile long activationTime;
    private volatile long cumulativeManagedSessionCount;
    private volatile int largestManagedSessionCount;
    private final List<IoServiceListener> listeners = new CopyOnWriteArrayList();
    private final ConcurrentMap<Long, IoSession> managedSessions;
    private final Map<Long, IoSession> readOnlyManagedSessions;
    private final IoService service;

    public IoServiceListenerSupport(IoService ioService) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        this.managedSessions = concurrentHashMap;
        this.readOnlyManagedSessions = Collections.unmodifiableMap(concurrentHashMap);
        this.activated = new AtomicBoolean();
        this.largestManagedSessionCount = 0;
        this.cumulativeManagedSessionCount = 0;
        if (ioService != null) {
            this.service = ioService;
            return;
        }
        throw new IllegalArgumentException(NotificationCompat.CATEGORY_SERVICE);
    }

    public void add(IoServiceListener ioServiceListener) {
        if (ioServiceListener != null) {
            this.listeners.add(ioServiceListener);
        }
    }

    public void remove(IoServiceListener ioServiceListener) {
        if (ioServiceListener != null) {
            this.listeners.remove(ioServiceListener);
        }
    }

    public long getActivationTime() {
        return this.activationTime;
    }

    public Map<Long, IoSession> getManagedSessions() {
        return this.readOnlyManagedSessions;
    }

    public int getManagedSessionCount() {
        return this.managedSessions.size();
    }

    public int getLargestManagedSessionCount() {
        return this.largestManagedSessionCount;
    }

    public long getCumulativeManagedSessionCount() {
        return this.cumulativeManagedSessionCount;
    }

    public boolean isActive() {
        return this.activated.get();
    }

    public void fireServiceActivated() {
        if (this.activated.compareAndSet(false, true)) {
            this.activationTime = System.currentTimeMillis();
            for (IoServiceListener serviceActivated : this.listeners) {
                try {
                    serviceActivated.serviceActivated(this.service);
                } catch (Throwable th) {
                    ExceptionMonitor.getInstance().exceptionCaught(th);
                }
            }
        }
    }

    public void fireServiceDeactivated() {
        if (this.activated.compareAndSet(true, false)) {
            try {
                for (IoServiceListener serviceDeactivated : this.listeners) {
                    serviceDeactivated.serviceDeactivated(this.service);
                }
                disconnectSessions();
            } catch (Throwable th) {
                disconnectSessions();
                throw th;
            }
        }
    }

    public void fireSessionCreated(IoSession ioSession) {
        boolean z;
        if (ioSession.getService() instanceof IoConnector) {
            synchronized (this.managedSessions) {
                z = this.managedSessions.isEmpty();
            }
        } else {
            z = false;
        }
        if (this.managedSessions.putIfAbsent(Long.valueOf(ioSession.getId()), ioSession) == null) {
            if (z) {
                fireServiceActivated();
            }
            IoFilterChain filterChain = ioSession.getFilterChain();
            filterChain.fireSessionCreated();
            filterChain.fireSessionOpened();
            int size = this.managedSessions.size();
            if (size > this.largestManagedSessionCount) {
                this.largestManagedSessionCount = size;
            }
            this.cumulativeManagedSessionCount++;
            for (IoServiceListener sessionCreated : this.listeners) {
                try {
                    sessionCreated.sessionCreated(ioSession);
                } catch (Throwable th) {
                    ExceptionMonitor.getInstance().exceptionCaught(th);
                }
            }
        }
    }

    public void fireSessionDestroyed(IoSession ioSession) {
        boolean isEmpty;
        if (this.managedSessions.remove(Long.valueOf(ioSession.getId())) != null) {
            ioSession.getFilterChain().fireSessionClosed();
            try {
                for (IoServiceListener sessionDestroyed : this.listeners) {
                    sessionDestroyed.sessionDestroyed(ioSession);
                }
                if (ioSession.getService() instanceof IoConnector) {
                    synchronized (this.managedSessions) {
                        isEmpty = this.managedSessions.isEmpty();
                    }
                    if (isEmpty) {
                        fireServiceDeactivated();
                    }
                }
            } catch (Throwable th) {
                if (ioSession.getService() instanceof IoConnector) {
                    synchronized (this.managedSessions) {
                        if (this.managedSessions.isEmpty()) {
                            fireServiceDeactivated();
                        }
                    }
                }
                throw th;
            }
        }
    }

    private void disconnectSessions() {
        IoService ioService = this.service;
        if ((ioService instanceof IoAcceptor) && ((IoAcceptor) ioService).isCloseOnDeactivation()) {
            Object obj = new Object();
            LockNotifyingListener lockNotifyingListener = new LockNotifyingListener(obj);
            for (IoSession close : this.managedSessions.values()) {
                close.close(true).addListener(lockNotifyingListener);
            }
            try {
                synchronized (obj) {
                    while (!this.managedSessions.isEmpty()) {
                        obj.wait(500);
                    }
                }
            } catch (InterruptedException unused) {
            }
        }
    }

    private static class LockNotifyingListener implements IoFutureListener<IoFuture> {
        private final Object lock;

        public LockNotifyingListener(Object obj) {
            this.lock = obj;
        }

        public void operationComplete(IoFuture ioFuture) {
            synchronized (this.lock) {
                this.lock.notifyAll();
            }
        }
    }
}
