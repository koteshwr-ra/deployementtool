package org.apache.mina.core.polling;

import java.net.SocketAddress;
import java.nio.channels.ClosedSelectorException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.service.AbstractIoAcceptor;
import org.apache.mina.core.service.AbstractIoService;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.service.SimpleIoProcessorPool;
import org.apache.mina.core.session.AbstractIoSession;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.core.session.IoSessionInitializer;
import org.apache.mina.util.ExceptionMonitor;

public abstract class AbstractPollingIoAcceptor<S extends AbstractIoSession, H> extends AbstractIoAcceptor {
    /* access modifiers changed from: private */
    public AtomicReference<AbstractPollingIoAcceptor<S, H>.Acceptor> acceptorRef;
    protected int backlog;
    private final Map<SocketAddress, H> boundHandles;
    /* access modifiers changed from: private */
    public final Queue<AbstractIoAcceptor.AcceptorOperationFuture> cancelQueue;
    /* access modifiers changed from: private */
    public final boolean createdProcessor;
    /* access modifiers changed from: private */
    public final AbstractIoService.ServiceOperationFuture disposalFuture;
    /* access modifiers changed from: private */
    public final IoProcessor<S> processor;
    /* access modifiers changed from: private */
    public final Queue<AbstractIoAcceptor.AcceptorOperationFuture> registerQueue;
    protected boolean reuseAddress;
    /* access modifiers changed from: private */
    public volatile boolean selectable;

    /* access modifiers changed from: protected */
    public abstract S accept(IoProcessor<S> ioProcessor, H h) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void close(H h) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void destroy() throws Exception;

    /* access modifiers changed from: protected */
    public abstract void init() throws Exception;

    /* access modifiers changed from: protected */
    public abstract SocketAddress localAddress(H h) throws Exception;

    /* access modifiers changed from: protected */
    public abstract H open(SocketAddress socketAddress) throws Exception;

    /* access modifiers changed from: protected */
    public abstract int select() throws Exception;

    /* access modifiers changed from: protected */
    public abstract Iterator<H> selectedHandles();

    /* access modifiers changed from: protected */
    public abstract void wakeup();

    protected AbstractPollingIoAcceptor(IoSessionConfig ioSessionConfig, Class<? extends IoProcessor<S>> cls) {
        this(ioSessionConfig, (Executor) null, new SimpleIoProcessorPool(cls), true);
    }

    protected AbstractPollingIoAcceptor(IoSessionConfig ioSessionConfig, Class<? extends IoProcessor<S>> cls, int i) {
        this(ioSessionConfig, (Executor) null, new SimpleIoProcessorPool(cls, i), true);
    }

    protected AbstractPollingIoAcceptor(IoSessionConfig ioSessionConfig, IoProcessor<S> ioProcessor) {
        this(ioSessionConfig, (Executor) null, ioProcessor, false);
    }

    protected AbstractPollingIoAcceptor(IoSessionConfig ioSessionConfig, Executor executor, IoProcessor<S> ioProcessor) {
        this(ioSessionConfig, executor, ioProcessor, false);
    }

    private AbstractPollingIoAcceptor(IoSessionConfig ioSessionConfig, Executor executor, IoProcessor<S> ioProcessor, boolean z) {
        super(ioSessionConfig, executor);
        this.registerQueue = new ConcurrentLinkedQueue();
        this.cancelQueue = new ConcurrentLinkedQueue();
        this.boundHandles = Collections.synchronizedMap(new HashMap());
        this.disposalFuture = new AbstractIoService.ServiceOperationFuture();
        this.acceptorRef = new AtomicReference<>();
        this.reuseAddress = false;
        this.backlog = 50;
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
    public void dispose0() throws Exception {
        unbind();
        startupAcceptor();
        wakeup();
    }

    /* access modifiers changed from: protected */
    public final Set<SocketAddress> bindInternal(List<? extends SocketAddress> list) throws Exception {
        AbstractIoAcceptor.AcceptorOperationFuture acceptorOperationFuture = new AbstractIoAcceptor.AcceptorOperationFuture(list);
        this.registerQueue.add(acceptorOperationFuture);
        startupAcceptor();
        wakeup();
        acceptorOperationFuture.awaitUninterruptibly();
        if (acceptorOperationFuture.getException() == null) {
            HashSet hashSet = new HashSet();
            for (H localAddress : this.boundHandles.values()) {
                hashSet.add(localAddress(localAddress));
            }
            return hashSet;
        }
        throw acceptorOperationFuture.getException();
    }

    private void startupAcceptor() {
        if (!this.selectable) {
            this.registerQueue.clear();
            this.cancelQueue.clear();
        }
        if (this.acceptorRef.get() == null) {
            Acceptor acceptor = new Acceptor();
            if (this.acceptorRef.compareAndSet((Object) null, acceptor)) {
                executeWorker(acceptor);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void unbind0(List<? extends SocketAddress> list) throws Exception {
        AbstractIoAcceptor.AcceptorOperationFuture acceptorOperationFuture = new AbstractIoAcceptor.AcceptorOperationFuture(list);
        this.cancelQueue.add(acceptorOperationFuture);
        startupAcceptor();
        wakeup();
        acceptorOperationFuture.awaitUninterruptibly();
        if (acceptorOperationFuture.getException() != null) {
            throw acceptorOperationFuture.getException();
        }
    }

    private class Acceptor implements Runnable {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<AbstractPollingIoAcceptor> cls = AbstractPollingIoAcceptor.class;
        }

        private Acceptor() {
        }

        public void run() {
            int i = 0;
            while (true) {
                if (!AbstractPollingIoAcceptor.this.selectable) {
                    break;
                }
                try {
                    int select = AbstractPollingIoAcceptor.this.select();
                    int access$300 = i + AbstractPollingIoAcceptor.this.registerHandles();
                    if (access$300 == 0) {
                        AbstractPollingIoAcceptor.this.acceptorRef.set((Object) null);
                        if (!AbstractPollingIoAcceptor.this.registerQueue.isEmpty() || !AbstractPollingIoAcceptor.this.cancelQueue.isEmpty()) {
                            if (!AbstractPollingIoAcceptor.this.acceptorRef.compareAndSet((Object) null, this)) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (select > 0) {
                        processHandles(AbstractPollingIoAcceptor.this.selectedHandles());
                    }
                    i = access$300 - AbstractPollingIoAcceptor.this.unregisterHandles();
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
            if (AbstractPollingIoAcceptor.this.selectable && AbstractPollingIoAcceptor.this.isDisposing()) {
                boolean unused2 = AbstractPollingIoAcceptor.this.selectable = false;
                try {
                    if (AbstractPollingIoAcceptor.this.createdProcessor) {
                        AbstractPollingIoAcceptor.this.processor.dispose();
                    }
                    try {
                        synchronized (AbstractPollingIoAcceptor.this.disposalLock) {
                            if (AbstractPollingIoAcceptor.this.isDisposing()) {
                                AbstractPollingIoAcceptor.this.destroy();
                            }
                        }
                    } catch (Exception e2) {
                        try {
                            ExceptionMonitor.getInstance().exceptionCaught(e2);
                        } catch (Throwable th2) {
                            AbstractPollingIoAcceptor.this.disposalFuture.setDone();
                            throw th2;
                        }
                    }
                    AbstractPollingIoAcceptor.this.disposalFuture.setDone();
                } catch (Throwable th3) {
                    try {
                        synchronized (AbstractPollingIoAcceptor.this.disposalLock) {
                            if (AbstractPollingIoAcceptor.this.isDisposing()) {
                                AbstractPollingIoAcceptor.this.destroy();
                            }
                            AbstractPollingIoAcceptor.this.disposalFuture.setDone();
                            throw th3;
                        }
                    } catch (Exception e3) {
                        try {
                            ExceptionMonitor.getInstance().exceptionCaught(e3);
                        } catch (Throwable th4) {
                            AbstractPollingIoAcceptor.this.disposalFuture.setDone();
                            throw th4;
                        }
                    }
                }
            }
        }

        private void processHandles(Iterator<H> it) throws Exception {
            while (it.hasNext()) {
                H next = it.next();
                it.remove();
                AbstractPollingIoAcceptor abstractPollingIoAcceptor = AbstractPollingIoAcceptor.this;
                AbstractIoSession accept = abstractPollingIoAcceptor.accept(abstractPollingIoAcceptor.processor, next);
                if (accept != null) {
                    AbstractPollingIoAcceptor.this.initSession(accept, (IoFuture) null, (IoSessionInitializer) null);
                    accept.getProcessor().add(accept);
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public int registerHandles() {
        /*
            r5 = this;
        L_0x0000:
            java.util.Queue<org.apache.mina.core.service.AbstractIoAcceptor$AcceptorOperationFuture> r0 = r5.registerQueue
            java.lang.Object r0 = r0.poll()
            org.apache.mina.core.service.AbstractIoAcceptor$AcceptorOperationFuture r0 = (org.apache.mina.core.service.AbstractIoAcceptor.AcceptorOperationFuture) r0
            if (r0 != 0) goto L_0x000c
            r0 = 0
            return r0
        L_0x000c:
            java.util.concurrent.ConcurrentHashMap r1 = new java.util.concurrent.ConcurrentHashMap
            r1.<init>()
            java.util.List r2 = r0.getLocalAddresses()
            java.util.Iterator r2 = r2.iterator()     // Catch:{ Exception -> 0x0068 }
        L_0x0019:
            boolean r3 = r2.hasNext()     // Catch:{ Exception -> 0x0068 }
            if (r3 == 0) goto L_0x0031
            java.lang.Object r3 = r2.next()     // Catch:{ Exception -> 0x0068 }
            java.net.SocketAddress r3 = (java.net.SocketAddress) r3     // Catch:{ Exception -> 0x0068 }
            java.lang.Object r3 = r5.open(r3)     // Catch:{ Exception -> 0x0068 }
            java.net.SocketAddress r4 = r5.localAddress(r3)     // Catch:{ Exception -> 0x0068 }
            r1.put(r4, r3)     // Catch:{ Exception -> 0x0068 }
            goto L_0x0019
        L_0x0031:
            java.util.Map<java.net.SocketAddress, H> r2 = r5.boundHandles     // Catch:{ Exception -> 0x0068 }
            r2.putAll(r1)     // Catch:{ Exception -> 0x0068 }
            r0.setDone()     // Catch:{ Exception -> 0x0068 }
            int r2 = r1.size()     // Catch:{ Exception -> 0x0068 }
            java.lang.Exception r0 = r0.getException()
            if (r0 == 0) goto L_0x0065
            java.util.Collection r0 = r1.values()
            java.util.Iterator r0 = r0.iterator()
        L_0x004b:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0062
            java.lang.Object r1 = r0.next()
            r5.close(r1)     // Catch:{ Exception -> 0x0059 }
            goto L_0x004b
        L_0x0059:
            r1 = move-exception
            org.apache.mina.util.ExceptionMonitor r3 = org.apache.mina.util.ExceptionMonitor.getInstance()
            r3.exceptionCaught(r1)
            goto L_0x004b
        L_0x0062:
            r5.wakeup()
        L_0x0065:
            return r2
        L_0x0066:
            r2 = move-exception
            goto L_0x0096
        L_0x0068:
            r2 = move-exception
            r0.setException(r2)     // Catch:{ all -> 0x0066 }
            java.lang.Exception r0 = r0.getException()
            if (r0 == 0) goto L_0x0000
            java.util.Collection r0 = r1.values()
            java.util.Iterator r0 = r0.iterator()
        L_0x007a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0091
            java.lang.Object r1 = r0.next()
            r5.close(r1)     // Catch:{ Exception -> 0x0088 }
            goto L_0x007a
        L_0x0088:
            r1 = move-exception
            org.apache.mina.util.ExceptionMonitor r2 = org.apache.mina.util.ExceptionMonitor.getInstance()
            r2.exceptionCaught(r1)
            goto L_0x007a
        L_0x0091:
            r5.wakeup()
            goto L_0x0000
        L_0x0096:
            java.lang.Exception r0 = r0.getException()
            if (r0 == 0) goto L_0x00be
            java.util.Collection r0 = r1.values()
            java.util.Iterator r0 = r0.iterator()
        L_0x00a4:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00bb
            java.lang.Object r1 = r0.next()
            r5.close(r1)     // Catch:{ Exception -> 0x00b2 }
            goto L_0x00a4
        L_0x00b2:
            r1 = move-exception
            org.apache.mina.util.ExceptionMonitor r3 = org.apache.mina.util.ExceptionMonitor.getInstance()
            r3.exceptionCaught(r1)
            goto L_0x00a4
        L_0x00bb:
            r5.wakeup()
        L_0x00be:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.core.polling.AbstractPollingIoAcceptor.registerHandles():int");
    }

    /* access modifiers changed from: private */
    public int unregisterHandles() {
        int i = 0;
        while (true) {
            AbstractIoAcceptor.AcceptorOperationFuture poll = this.cancelQueue.poll();
            if (poll == null) {
                return i;
            }
            for (SocketAddress remove : poll.getLocalAddresses()) {
                H remove2 = this.boundHandles.remove(remove);
                if (remove2 != null) {
                    try {
                        close(remove2);
                        wakeup();
                    } catch (Throwable th) {
                        ExceptionMonitor.getInstance().exceptionCaught(th);
                    }
                    i++;
                }
            }
            poll.setDone();
        }
    }

    public final IoSession newSession(SocketAddress socketAddress, SocketAddress socketAddress2) {
        throw new UnsupportedOperationException();
    }

    public int getBacklog() {
        return this.backlog;
    }

    public void setBacklog(int i) {
        synchronized (this.bindLock) {
            if (!isActive()) {
                this.backlog = i;
            } else {
                throw new IllegalStateException("backlog can't be set while the acceptor is bound.");
            }
        }
    }

    public boolean isReuseAddress() {
        return this.reuseAddress;
    }

    public void setReuseAddress(boolean z) {
        synchronized (this.bindLock) {
            if (!isActive()) {
                this.reuseAddress = z;
            } else {
                throw new IllegalStateException("backlog can't be set while the acceptor is bound.");
            }
        }
    }
}
