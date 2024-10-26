package org.apache.mina.core.polling;

import java.net.ConnectException;
import java.net.SocketAddress;
import java.nio.channels.ClosedSelectorException;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.DefaultConnectFuture;
import org.apache.mina.core.service.AbstractIoConnector;
import org.apache.mina.core.service.AbstractIoService;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.service.SimpleIoProcessorPool;
import org.apache.mina.core.session.AbstractIoSession;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.core.session.IoSessionInitializer;
import org.apache.mina.util.ExceptionMonitor;

public abstract class AbstractPollingIoConnector<T extends AbstractIoSession, H> extends AbstractIoConnector {
    /* access modifiers changed from: private */
    public final Queue<AbstractPollingIoConnector<T, H>.ConnectionRequest> cancelQueue;
    /* access modifiers changed from: private */
    public final Queue<AbstractPollingIoConnector<T, H>.ConnectionRequest> connectQueue;
    /* access modifiers changed from: private */
    public final AtomicReference<AbstractPollingIoConnector<T, H>.Connector> connectorRef;
    /* access modifiers changed from: private */
    public final boolean createdProcessor;
    /* access modifiers changed from: private */
    public final AbstractIoService.ServiceOperationFuture disposalFuture;
    /* access modifiers changed from: private */
    public final IoProcessor<T> processor;
    /* access modifiers changed from: private */
    public volatile boolean selectable;

    /* access modifiers changed from: protected */
    public abstract Iterator<H> allHandles();

    /* access modifiers changed from: protected */
    public abstract void close(H h) throws Exception;

    /* access modifiers changed from: protected */
    public abstract boolean connect(H h, SocketAddress socketAddress) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void destroy() throws Exception;

    /* access modifiers changed from: protected */
    public abstract boolean finishConnect(H h) throws Exception;

    /* access modifiers changed from: protected */
    public abstract AbstractPollingIoConnector<T, H>.ConnectionRequest getConnectionRequest(H h);

    /* access modifiers changed from: protected */
    public abstract void init() throws Exception;

    /* access modifiers changed from: protected */
    public abstract H newHandle(SocketAddress socketAddress) throws Exception;

    /* access modifiers changed from: protected */
    public abstract T newSession(IoProcessor<T> ioProcessor, H h) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void register(H h, AbstractPollingIoConnector<T, H>.ConnectionRequest connectionRequest) throws Exception;

    /* access modifiers changed from: protected */
    public abstract int select(int i) throws Exception;

    /* access modifiers changed from: protected */
    public abstract Iterator<H> selectedHandles();

    /* access modifiers changed from: protected */
    public abstract void wakeup();

    protected AbstractPollingIoConnector(IoSessionConfig ioSessionConfig, Class<? extends IoProcessor<T>> cls) {
        this(ioSessionConfig, (Executor) null, new SimpleIoProcessorPool(cls), true);
    }

    protected AbstractPollingIoConnector(IoSessionConfig ioSessionConfig, Class<? extends IoProcessor<T>> cls, int i) {
        this(ioSessionConfig, (Executor) null, new SimpleIoProcessorPool(cls, i), true);
    }

    protected AbstractPollingIoConnector(IoSessionConfig ioSessionConfig, IoProcessor<T> ioProcessor) {
        this(ioSessionConfig, (Executor) null, ioProcessor, false);
    }

    protected AbstractPollingIoConnector(IoSessionConfig ioSessionConfig, Executor executor, IoProcessor<T> ioProcessor) {
        this(ioSessionConfig, executor, ioProcessor, false);
    }

    private AbstractPollingIoConnector(IoSessionConfig ioSessionConfig, Executor executor, IoProcessor<T> ioProcessor, boolean z) {
        super(ioSessionConfig, executor);
        this.connectQueue = new ConcurrentLinkedQueue();
        this.cancelQueue = new ConcurrentLinkedQueue();
        this.disposalFuture = new AbstractIoService.ServiceOperationFuture();
        this.connectorRef = new AtomicReference<>();
        if (ioProcessor != null) {
            this.processor = ioProcessor;
            this.createdProcessor = z;
            try {
                init();
                this.selectable = true;
                if (!this.selectable) {
                    try {
                        destroy();
                    } catch (Exception e) {
                        ExceptionMonitor.getInstance().exceptionCaught(e);
                    }
                }
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new RuntimeIoException("Failed to initialize.", e3);
            } catch (Throwable th) {
                if (!this.selectable) {
                    try {
                        destroy();
                    } catch (Exception e4) {
                        ExceptionMonitor.getInstance().exceptionCaught(e4);
                    }
                }
                throw th;
            }
        } else {
            throw new IllegalArgumentException("processor");
        }
    }

    /* access modifiers changed from: protected */
    public final void dispose0() throws Exception {
        startupWorker();
        wakeup();
    }

    /* access modifiers changed from: protected */
    public final ConnectFuture connect0(SocketAddress socketAddress, SocketAddress socketAddress2, IoSessionInitializer<? extends ConnectFuture> ioSessionInitializer) {
        try {
            Object newHandle = newHandle(socketAddress2);
            if (connect(newHandle, socketAddress)) {
                DefaultConnectFuture defaultConnectFuture = new DefaultConnectFuture();
                AbstractIoSession newSession = newSession(this.processor, newHandle);
                initSession(newSession, defaultConnectFuture, ioSessionInitializer);
                newSession.getProcessor().add(newSession);
                return defaultConnectFuture;
            }
            ConnectionRequest connectionRequest = new ConnectionRequest(newHandle, ioSessionInitializer);
            this.connectQueue.add(connectionRequest);
            startupWorker();
            wakeup();
            return connectionRequest;
        } catch (Exception e) {
            ConnectFuture newFailedFuture = DefaultConnectFuture.newFailedFuture(e);
            if (0 != 0) {
                try {
                    close((Object) null);
                } catch (Exception e2) {
                    ExceptionMonitor.getInstance().exceptionCaught(e2);
                }
            }
            return newFailedFuture;
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    close((Object) null);
                } catch (Exception e3) {
                    ExceptionMonitor.getInstance().exceptionCaught(e3);
                }
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public void startupWorker() {
        if (!this.selectable) {
            this.connectQueue.clear();
            this.cancelQueue.clear();
        }
        if (this.connectorRef.get() == null) {
            Connector connector = new Connector();
            if (this.connectorRef.compareAndSet((Object) null, connector)) {
                executeWorker(connector);
            }
        }
    }

    /* access modifiers changed from: private */
    public int registerNew() {
        int i = 0;
        while (true) {
            ConnectionRequest poll = this.connectQueue.poll();
            if (poll == null) {
                return i;
            }
            Object access$100 = poll.handle;
            try {
                register(access$100, poll);
                i++;
            } catch (Exception e) {
                poll.setException(e);
                try {
                    close(access$100);
                } catch (Exception e2) {
                    ExceptionMonitor.getInstance().exceptionCaught(e2);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public int cancelKeys() {
        int i = 0;
        while (true) {
            ConnectionRequest poll = this.cancelQueue.poll();
            if (poll == null) {
                if (i > 0) {
                    wakeup();
                }
                return i;
            }
            try {
                close(poll.handle);
            } catch (Exception e) {
                ExceptionMonitor.getInstance().exceptionCaught(e);
            }
            i++;
        }
    }

    /* access modifiers changed from: private */
    public int processConnections(Iterator<H> it) {
        int i = 0;
        while (it.hasNext()) {
            H next = it.next();
            it.remove();
            AbstractPollingIoConnector<T, H>.ConnectionRequest connectionRequest = getConnectionRequest(next);
            if (connectionRequest != null) {
                try {
                    if (finishConnect(next)) {
                        AbstractIoSession newSession = newSession(this.processor, next);
                        initSession(newSession, connectionRequest, connectionRequest.getSessionInitializer());
                        newSession.getProcessor().add(newSession);
                        i++;
                    }
                } catch (Throwable th) {
                    this.cancelQueue.offer(connectionRequest);
                    throw th;
                }
            }
        }
        return i;
    }

    /* access modifiers changed from: private */
    public void processTimedOutSessions(Iterator<H> it) {
        long currentTimeMillis = System.currentTimeMillis();
        while (it.hasNext()) {
            AbstractPollingIoConnector<T, H>.ConnectionRequest connectionRequest = getConnectionRequest(it.next());
            if (connectionRequest != null && currentTimeMillis >= connectionRequest.deadline) {
                connectionRequest.setException(new ConnectException("Connection timed out."));
                this.cancelQueue.offer(connectionRequest);
            }
        }
    }

    private class Connector implements Runnable {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<AbstractPollingIoConnector> cls = AbstractPollingIoConnector.class;
        }

        private Connector() {
        }

        public void run() {
            int i = 0;
            while (true) {
                if (!AbstractPollingIoConnector.this.selectable) {
                    break;
                }
                try {
                    int select = AbstractPollingIoConnector.this.select((int) Math.min(AbstractPollingIoConnector.this.getConnectTimeoutMillis(), 1000));
                    int access$500 = i + AbstractPollingIoConnector.this.registerNew();
                    if (access$500 == 0) {
                        AbstractPollingIoConnector.this.connectorRef.set((Object) null);
                        if (!AbstractPollingIoConnector.this.connectQueue.isEmpty()) {
                            if (!AbstractPollingIoConnector.this.connectorRef.compareAndSet((Object) null, this)) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (select > 0) {
                        access$500 -= AbstractPollingIoConnector.this.processConnections(AbstractPollingIoConnector.this.selectedHandles());
                    }
                    AbstractPollingIoConnector.this.processTimedOutSessions(AbstractPollingIoConnector.this.allHandles());
                    i = access$500 - AbstractPollingIoConnector.this.cancelKeys();
                } catch (ClosedSelectorException unused) {
                } catch (Throwable th) {
                    ExceptionMonitor.getInstance().exceptionCaught(th);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        ExceptionMonitor.getInstance().exceptionCaught(e);
                    }
                }
            }
            if (AbstractPollingIoConnector.this.selectable && AbstractPollingIoConnector.this.isDisposing()) {
                boolean unused2 = AbstractPollingIoConnector.this.selectable = false;
                try {
                    if (AbstractPollingIoConnector.this.createdProcessor) {
                        AbstractPollingIoConnector.this.processor.dispose();
                    }
                    try {
                        synchronized (AbstractPollingIoConnector.this.disposalLock) {
                            if (AbstractPollingIoConnector.this.isDisposing()) {
                                AbstractPollingIoConnector.this.destroy();
                            }
                        }
                    } catch (Exception e2) {
                        try {
                            ExceptionMonitor.getInstance().exceptionCaught(e2);
                        } catch (Throwable th2) {
                            AbstractPollingIoConnector.this.disposalFuture.setDone();
                            throw th2;
                        }
                    }
                    AbstractPollingIoConnector.this.disposalFuture.setDone();
                } catch (Throwable th3) {
                    try {
                        synchronized (AbstractPollingIoConnector.this.disposalLock) {
                            if (AbstractPollingIoConnector.this.isDisposing()) {
                                AbstractPollingIoConnector.this.destroy();
                            }
                            AbstractPollingIoConnector.this.disposalFuture.setDone();
                            throw th3;
                        }
                    } catch (Exception e3) {
                        try {
                            ExceptionMonitor.getInstance().exceptionCaught(e3);
                        } catch (Throwable th4) {
                            AbstractPollingIoConnector.this.disposalFuture.setDone();
                            throw th4;
                        }
                    }
                }
            }
        }
    }

    public final class ConnectionRequest extends DefaultConnectFuture {
        /* access modifiers changed from: private */
        public final long deadline;
        /* access modifiers changed from: private */
        public final H handle;
        private final IoSessionInitializer<? extends ConnectFuture> sessionInitializer;

        public ConnectionRequest(H h, IoSessionInitializer<? extends ConnectFuture> ioSessionInitializer) {
            this.handle = h;
            long connectTimeoutMillis = AbstractPollingIoConnector.this.getConnectTimeoutMillis();
            if (connectTimeoutMillis <= 0) {
                this.deadline = LongCompanionObject.MAX_VALUE;
            } else {
                this.deadline = System.currentTimeMillis() + connectTimeoutMillis;
            }
            this.sessionInitializer = ioSessionInitializer;
        }

        public H getHandle() {
            return this.handle;
        }

        public long getDeadline() {
            return this.deadline;
        }

        public IoSessionInitializer<? extends ConnectFuture> getSessionInitializer() {
            return this.sessionInitializer;
        }

        public void cancel() {
            if (!isDone()) {
                super.cancel();
                AbstractPollingIoConnector.this.cancelQueue.add(this);
                AbstractPollingIoConnector.this.startupWorker();
                AbstractPollingIoConnector.this.wakeup();
            }
        }
    }
}
