package org.apache.mina.core.service;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.mina.core.IoUtil;
import org.apache.mina.core.filterchain.DefaultIoFilterChain;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.filterchain.IoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.DefaultIoFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.AbstractIoSession;
import org.apache.mina.core.session.DefaultIoSessionDataStructureFactory;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.core.session.IoSessionDataStructureFactory;
import org.apache.mina.core.session.IoSessionInitializationException;
import org.apache.mina.core.session.IoSessionInitializer;
import org.apache.mina.util.ExceptionMonitor;
import org.apache.mina.util.NamePreservingRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractIoService implements IoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractIoService.class);
    private static final AtomicInteger id = new AtomicInteger();
    private final boolean createdExecutor;
    /* access modifiers changed from: protected */
    public final Object disposalLock = new Object();
    private volatile boolean disposed;
    private volatile boolean disposing;
    private final Executor executor;
    private IoFilterChainBuilder filterChainBuilder = new DefaultIoFilterChainBuilder();
    private IoHandler handler;
    private final IoServiceListenerSupport listeners;
    private final IoServiceListener serviceActivationListener = new IoServiceListener() {
        public void serviceDeactivated(IoService ioService) {
        }

        public void serviceIdle(IoService ioService, IdleStatus idleStatus) {
        }

        public void sessionCreated(IoSession ioSession) {
        }

        public void sessionDestroyed(IoSession ioSession) {
        }

        public void serviceActivated(IoService ioService) {
            AbstractIoService abstractIoService = (AbstractIoService) ioService;
            IoServiceStatistics statistics = abstractIoService.getStatistics();
            statistics.setLastReadTime(abstractIoService.getActivationTime());
            statistics.setLastWriteTime(abstractIoService.getActivationTime());
            statistics.setLastThroughputCalculationTime(abstractIoService.getActivationTime());
        }
    };
    private final IoSessionConfig sessionConfig;
    private IoSessionDataStructureFactory sessionDataStructureFactory = new DefaultIoSessionDataStructureFactory();
    private IoServiceStatistics stats = new IoServiceStatistics(this);
    private final String threadName;

    /* access modifiers changed from: protected */
    public abstract void dispose0() throws Exception;

    /* access modifiers changed from: protected */
    public void finishSessionInitialization0(IoSession ioSession, IoFuture ioFuture) {
    }

    protected AbstractIoService(IoSessionConfig ioSessionConfig, Executor executor2) {
        if (ioSessionConfig == null) {
            throw new IllegalArgumentException("sessionConfig");
        } else if (getTransportMetadata() == null) {
            throw new IllegalArgumentException("TransportMetadata");
        } else if (getTransportMetadata().getSessionConfigType().isAssignableFrom(ioSessionConfig.getClass())) {
            IoServiceListenerSupport ioServiceListenerSupport = new IoServiceListenerSupport(this);
            this.listeners = ioServiceListenerSupport;
            ioServiceListenerSupport.add(this.serviceActivationListener);
            this.sessionConfig = ioSessionConfig;
            ExceptionMonitor.getInstance();
            if (executor2 == null) {
                this.executor = Executors.newCachedThreadPool();
                this.createdExecutor = true;
            } else {
                this.executor = executor2;
                this.createdExecutor = false;
            }
            this.threadName = getClass().getSimpleName() + '-' + id.incrementAndGet();
        } else {
            throw new IllegalArgumentException("sessionConfig type: " + ioSessionConfig.getClass() + " (expected: " + getTransportMetadata().getSessionConfigType() + ")");
        }
    }

    public final IoFilterChainBuilder getFilterChainBuilder() {
        return this.filterChainBuilder;
    }

    public final void setFilterChainBuilder(IoFilterChainBuilder ioFilterChainBuilder) {
        if (ioFilterChainBuilder == null) {
            ioFilterChainBuilder = new DefaultIoFilterChainBuilder();
        }
        this.filterChainBuilder = ioFilterChainBuilder;
    }

    public final DefaultIoFilterChainBuilder getFilterChain() {
        IoFilterChainBuilder ioFilterChainBuilder = this.filterChainBuilder;
        if (ioFilterChainBuilder instanceof DefaultIoFilterChainBuilder) {
            return (DefaultIoFilterChainBuilder) ioFilterChainBuilder;
        }
        throw new IllegalStateException("Current filter chain builder is not a DefaultIoFilterChainBuilder.");
    }

    public final void addListener(IoServiceListener ioServiceListener) {
        this.listeners.add(ioServiceListener);
    }

    public final void removeListener(IoServiceListener ioServiceListener) {
        this.listeners.remove(ioServiceListener);
    }

    public final boolean isActive() {
        return this.listeners.isActive();
    }

    public final boolean isDisposing() {
        return this.disposing;
    }

    public final boolean isDisposed() {
        return this.disposed;
    }

    public final void dispose() {
        dispose(false);
    }

    public final void dispose(boolean z) {
        if (!this.disposed) {
            synchronized (this.disposalLock) {
                if (!this.disposing) {
                    this.disposing = true;
                    try {
                        dispose0();
                    } catch (Exception e) {
                        ExceptionMonitor.getInstance().exceptionCaught(e);
                    }
                }
            }
            if (this.createdExecutor) {
                ExecutorService executorService = (ExecutorService) this.executor;
                executorService.shutdownNow();
                if (z) {
                    try {
                        LOGGER.debug("awaitTermination on {} called by thread=[{}]", (Object) this, (Object) Thread.currentThread().getName());
                        executorService.awaitTermination(2147483647L, TimeUnit.SECONDS);
                        LOGGER.debug("awaitTermination on {} finished", (Object) this);
                    } catch (InterruptedException unused) {
                        LOGGER.warn("awaitTermination on [{}] was interrupted", (Object) this);
                        Thread.currentThread().interrupt();
                    }
                }
            }
            this.disposed = true;
        }
    }

    public final Map<Long, IoSession> getManagedSessions() {
        return this.listeners.getManagedSessions();
    }

    public final int getManagedSessionCount() {
        return this.listeners.getManagedSessionCount();
    }

    public final IoHandler getHandler() {
        return this.handler;
    }

    public final void setHandler(IoHandler ioHandler) {
        if (ioHandler == null) {
            throw new IllegalArgumentException("handler cannot be null");
        } else if (!isActive()) {
            this.handler = ioHandler;
        } else {
            throw new IllegalStateException("handler cannot be set while the service is active.");
        }
    }

    public IoSessionConfig getSessionConfig() {
        return this.sessionConfig;
    }

    public final IoSessionDataStructureFactory getSessionDataStructureFactory() {
        return this.sessionDataStructureFactory;
    }

    public final void setSessionDataStructureFactory(IoSessionDataStructureFactory ioSessionDataStructureFactory) {
        if (ioSessionDataStructureFactory == null) {
            throw new IllegalArgumentException("sessionDataStructureFactory");
        } else if (!isActive()) {
            this.sessionDataStructureFactory = ioSessionDataStructureFactory;
        } else {
            throw new IllegalStateException("sessionDataStructureFactory cannot be set while the service is active.");
        }
    }

    public IoServiceStatistics getStatistics() {
        return this.stats;
    }

    public final long getActivationTime() {
        return this.listeners.getActivationTime();
    }

    public final Set<WriteFuture> broadcast(Object obj) {
        final List<WriteFuture> broadcast = IoUtil.broadcast(obj, getManagedSessions().values());
        return new AbstractSet<WriteFuture>() {
            public Iterator<WriteFuture> iterator() {
                return broadcast.iterator();
            }

            public int size() {
                return broadcast.size();
            }
        };
    }

    public final IoServiceListenerSupport getListeners() {
        return this.listeners;
    }

    /* access modifiers changed from: protected */
    public final void executeWorker(Runnable runnable) {
        executeWorker(runnable, (String) null);
    }

    /* access modifiers changed from: protected */
    public final void executeWorker(Runnable runnable, String str) {
        String str2 = this.threadName;
        if (str != null) {
            str2 = str2 + '-' + str;
        }
        this.executor.execute(new NamePreservingRunnable(runnable, str2));
    }

    /* access modifiers changed from: protected */
    public final void initSession(IoSession ioSession, IoFuture ioFuture, IoSessionInitializer ioSessionInitializer) {
        if (this.stats.getLastReadTime() == 0) {
            this.stats.setLastReadTime(getActivationTime());
        }
        if (this.stats.getLastWriteTime() == 0) {
            this.stats.setLastWriteTime(getActivationTime());
        }
        try {
            ((AbstractIoSession) ioSession).setAttributeMap(ioSession.getService().getSessionDataStructureFactory().getAttributeMap(ioSession));
            try {
                ((AbstractIoSession) ioSession).setWriteRequestQueue(ioSession.getService().getSessionDataStructureFactory().getWriteRequestQueue(ioSession));
                if (ioFuture != null && (ioFuture instanceof ConnectFuture)) {
                    ioSession.setAttribute(DefaultIoFilterChain.SESSION_CREATED_FUTURE, ioFuture);
                }
                if (ioSessionInitializer != null) {
                    ioSessionInitializer.initializeSession(ioSession, ioFuture);
                }
                finishSessionInitialization0(ioSession, ioFuture);
            } catch (IoSessionInitializationException e) {
                throw e;
            } catch (Exception e2) {
                throw new IoSessionInitializationException("Failed to initialize a writeRequestQueue.", e2);
            }
        } catch (IoSessionInitializationException e3) {
            throw e3;
        } catch (Exception e4) {
            throw new IoSessionInitializationException("Failed to initialize an attributeMap.", e4);
        }
    }

    protected static class ServiceOperationFuture extends DefaultIoFuture {
        public ServiceOperationFuture() {
            super((IoSession) null);
        }

        public final boolean isDone() {
            return getValue() == Boolean.TRUE;
        }

        public final void setDone() {
            setValue(Boolean.TRUE);
        }

        public final Exception getException() {
            if (getValue() instanceof Exception) {
                return (Exception) getValue();
            }
            return null;
        }

        public final void setException(Exception exc) {
            if (exc != null) {
                setValue(exc);
                return;
            }
            throw new IllegalArgumentException("exception");
        }
    }

    public int getScheduledWriteBytes() {
        return this.stats.getScheduledWriteBytes();
    }

    public int getScheduledWriteMessages() {
        return this.stats.getScheduledWriteMessages();
    }
}
