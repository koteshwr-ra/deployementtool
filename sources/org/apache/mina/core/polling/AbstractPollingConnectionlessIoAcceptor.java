package org.apache.mina.core.polling;

import com.alibaba.android.arouter.utils.Consts;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
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
import org.apache.log4j.spi.Configurator;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.AbstractIoAcceptor;
import org.apache.mina.core.service.AbstractIoService;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.session.AbstractIoSession;
import org.apache.mina.core.session.ExpiringSessionRecycler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.core.session.IoSessionRecycler;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.core.write.WriteRequestQueue;
import org.apache.mina.util.ExceptionMonitor;

public abstract class AbstractPollingConnectionlessIoAcceptor<S extends AbstractIoSession, H> extends AbstractIoAcceptor {
    private static final IoSessionRecycler DEFAULT_RECYCLER = new ExpiringSessionRecycler();
    private static final long SELECT_TIMEOUT = 1000;
    /* access modifiers changed from: private */
    public AbstractPollingConnectionlessIoAcceptor<S, H>.Acceptor acceptor;
    private final Map<String, H> boundHandles;
    /* access modifiers changed from: private */
    public final Queue<AbstractIoAcceptor.AcceptorOperationFuture> cancelQueue;
    /* access modifiers changed from: private */
    public final AbstractIoService.ServiceOperationFuture disposalFuture;
    private final Queue<S> flushingSessions;
    /* access modifiers changed from: private */
    public long lastIdleCheckTime;
    /* access modifiers changed from: private */
    public final Object lock;
    private final IoProcessor<S> processor;
    /* access modifiers changed from: private */
    public final Queue<AbstractIoAcceptor.AcceptorOperationFuture> registerQueue;
    /* access modifiers changed from: private */
    public volatile boolean selectable;
    private IoSessionRecycler sessionRecycler;

    /* access modifiers changed from: protected */
    public abstract void close(H h) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void destroy() throws Exception;

    /* access modifiers changed from: protected */
    public abstract void init() throws Exception;

    /* access modifiers changed from: protected */
    public abstract boolean isReadable(H h);

    /* access modifiers changed from: protected */
    public abstract boolean isWritable(H h);

    /* access modifiers changed from: protected */
    public abstract SocketAddress localAddress(H h) throws Exception;

    /* access modifiers changed from: protected */
    public abstract S newSession(IoProcessor<S> ioProcessor, H h, SocketAddress socketAddress) throws Exception;

    /* access modifiers changed from: protected */
    public abstract H open(SocketAddress socketAddress) throws Exception;

    /* access modifiers changed from: protected */
    public abstract SocketAddress receive(H h, IoBuffer ioBuffer) throws Exception;

    /* access modifiers changed from: protected */
    public abstract int select() throws Exception;

    /* access modifiers changed from: protected */
    public abstract int select(long j) throws Exception;

    /* access modifiers changed from: protected */
    public abstract Iterator<H> selectedHandles();

    /* access modifiers changed from: protected */
    public abstract int send(S s, IoBuffer ioBuffer, SocketAddress socketAddress) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void setInterestedInWrite(S s, boolean z) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void wakeup();

    private String getAddressAsString(SocketAddress socketAddress) {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
        InetAddress address = inetSocketAddress.getAddress();
        int port = inetSocketAddress.getPort();
        if (address == null) {
            return Configurator.NULL;
        }
        if (address instanceof Inet4Address) {
            return "/" + address.getHostAddress() + ":" + port;
        } else if (!((Inet6Address) address).isIPv4CompatibleAddress()) {
            return address.toString();
        } else {
            byte[] address2 = address.getAddress();
            return "/" + address2[12] + Consts.DOT + address2[13] + Consts.DOT + address2[14] + Consts.DOT + address2[15] + ":" + port;
        }
    }

    protected AbstractPollingConnectionlessIoAcceptor(IoSessionConfig ioSessionConfig) {
        this(ioSessionConfig, (Executor) null);
    }

    protected AbstractPollingConnectionlessIoAcceptor(IoSessionConfig ioSessionConfig, Executor executor) {
        super(ioSessionConfig, executor);
        this.lock = new Object();
        this.processor = new ConnectionlessAcceptorProcessor();
        this.registerQueue = new ConcurrentLinkedQueue();
        this.cancelQueue = new ConcurrentLinkedQueue();
        this.flushingSessions = new ConcurrentLinkedQueue();
        this.boundHandles = Collections.synchronizedMap(new HashMap());
        this.sessionRecycler = DEFAULT_RECYCLER;
        this.disposalFuture = new AbstractIoService.ServiceOperationFuture();
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

    public final IoSession newSession(SocketAddress socketAddress, SocketAddress socketAddress2) {
        IoSession newSessionWithoutLock;
        if (isDisposing()) {
            throw new IllegalStateException("Already disposed.");
        } else if (socketAddress != null) {
            synchronized (this.bindLock) {
                if (isActive()) {
                    try {
                        newSessionWithoutLock = newSessionWithoutLock(socketAddress, socketAddress2);
                    } catch (RuntimeException e) {
                        throw e;
                    } catch (Error e2) {
                        throw e2;
                    } catch (Exception e3) {
                        throw new RuntimeIoException("Failed to create a session.", e3);
                    }
                } else {
                    throw new IllegalStateException("Can't create a session from a unbound service.");
                }
            }
            return newSessionWithoutLock;
        } else {
            throw new IllegalArgumentException("remoteAddress");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
        initSession(r3, (org.apache.mina.core.future.IoFuture) null, (org.apache.mina.core.session.IoSessionInitializer) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        getFilterChainBuilder().buildFilterChain(r3.getFilterChain());
        getListeners().fireSessionCreated(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003e, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003f, code lost:
        org.apache.mina.util.ExceptionMonitor.getInstance().exceptionCaught(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.mina.core.session.IoSession newSessionWithoutLock(java.net.SocketAddress r3, java.net.SocketAddress r4) throws java.lang.Exception {
        /*
            r2 = this;
            java.util.Map<java.lang.String, H> r0 = r2.boundHandles
            java.lang.String r1 = r2.getAddressAsString(r4)
            java.lang.Object r0 = r0.get(r1)
            if (r0 == 0) goto L_0x004a
            org.apache.mina.core.session.IoSessionRecycler r1 = r2.getSessionRecycler()
            monitor-enter(r1)
            org.apache.mina.core.session.IoSession r4 = r1.recycle(r4, r3)     // Catch:{ all -> 0x0047 }
            if (r4 == 0) goto L_0x0019
            monitor-exit(r1)     // Catch:{ all -> 0x0047 }
            return r4
        L_0x0019:
            org.apache.mina.core.service.IoProcessor<S> r4 = r2.processor     // Catch:{ all -> 0x0047 }
            org.apache.mina.core.session.AbstractIoSession r3 = r2.newSession(r4, r0, r3)     // Catch:{ all -> 0x0047 }
            org.apache.mina.core.session.IoSessionRecycler r4 = r2.getSessionRecycler()     // Catch:{ all -> 0x0047 }
            r4.put(r3)     // Catch:{ all -> 0x0047 }
            monitor-exit(r1)     // Catch:{ all -> 0x0047 }
            r4 = 0
            r2.initSession(r3, r4, r4)
            org.apache.mina.core.filterchain.IoFilterChainBuilder r4 = r2.getFilterChainBuilder()     // Catch:{ all -> 0x003e }
            org.apache.mina.core.filterchain.IoFilterChain r0 = r3.getFilterChain()     // Catch:{ all -> 0x003e }
            r4.buildFilterChain(r0)     // Catch:{ all -> 0x003e }
            org.apache.mina.core.service.IoServiceListenerSupport r4 = r2.getListeners()     // Catch:{ all -> 0x003e }
            r4.fireSessionCreated(r3)     // Catch:{ all -> 0x003e }
            goto L_0x0046
        L_0x003e:
            r4 = move-exception
            org.apache.mina.util.ExceptionMonitor r0 = org.apache.mina.util.ExceptionMonitor.getInstance()
            r0.exceptionCaught(r4)
        L_0x0046:
            return r3
        L_0x0047:
            r3 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0047 }
            throw r3
        L_0x004a:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unknown local address: "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.core.polling.AbstractPollingConnectionlessIoAcceptor.newSessionWithoutLock(java.net.SocketAddress, java.net.SocketAddress):org.apache.mina.core.session.IoSession");
    }

    public final IoSessionRecycler getSessionRecycler() {
        return this.sessionRecycler;
    }

    public final void setSessionRecycler(IoSessionRecycler ioSessionRecycler) {
        synchronized (this.bindLock) {
            if (!isActive()) {
                if (ioSessionRecycler == null) {
                    ioSessionRecycler = DEFAULT_RECYCLER;
                }
                this.sessionRecycler = ioSessionRecycler;
            } else {
                throw new IllegalStateException("sessionRecycler can't be set while the acceptor is bound.");
            }
        }
    }

    private class ConnectionlessAcceptorProcessor implements IoProcessor<S> {
        public void add(S s) {
        }

        public void dispose() {
        }

        public boolean isDisposed() {
            return false;
        }

        public boolean isDisposing() {
            return false;
        }

        private ConnectionlessAcceptorProcessor() {
        }

        public void flush(S s) {
            if (AbstractPollingConnectionlessIoAcceptor.this.scheduleFlush(s)) {
                AbstractPollingConnectionlessIoAcceptor.this.wakeup();
            }
        }

        public void remove(S s) {
            AbstractPollingConnectionlessIoAcceptor.this.getSessionRecycler().remove(s);
            AbstractPollingConnectionlessIoAcceptor.this.getListeners().fireSessionDestroyed(s);
        }

        public void updateTrafficControl(S s) {
            throw new UnsupportedOperationException();
        }
    }

    private void startupAcceptor() {
        if (!this.selectable) {
            this.registerQueue.clear();
            this.cancelQueue.clear();
            this.flushingSessions.clear();
        }
        synchronized (this.lock) {
            if (this.acceptor == null) {
                AbstractPollingConnectionlessIoAcceptor<S, H>.Acceptor acceptor2 = new Acceptor();
                this.acceptor = acceptor2;
                executeWorker(acceptor2);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean scheduleFlush(S s) {
        if (!s.setScheduledForFlush(true)) {
            return false;
        }
        this.flushingSessions.add(s);
        return true;
    }

    private class Acceptor implements Runnable {
        private Acceptor() {
        }

        public void run() {
            long unused = AbstractPollingConnectionlessIoAcceptor.this.lastIdleCheckTime = System.currentTimeMillis();
            int i = 0;
            while (true) {
                if (!AbstractPollingConnectionlessIoAcceptor.this.selectable) {
                    break;
                }
                try {
                    int select = AbstractPollingConnectionlessIoAcceptor.this.select(1000);
                    i += AbstractPollingConnectionlessIoAcceptor.this.registerHandles();
                    if (i == 0) {
                        synchronized (AbstractPollingConnectionlessIoAcceptor.this.lock) {
                            if (AbstractPollingConnectionlessIoAcceptor.this.registerQueue.isEmpty() && AbstractPollingConnectionlessIoAcceptor.this.cancelQueue.isEmpty()) {
                                Acceptor unused2 = AbstractPollingConnectionlessIoAcceptor.this.acceptor = null;
                            }
                        }
                    }
                    if (select > 0) {
                        AbstractPollingConnectionlessIoAcceptor.this.processReadySessions(AbstractPollingConnectionlessIoAcceptor.this.selectedHandles());
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    AbstractPollingConnectionlessIoAcceptor.this.flushSessions(currentTimeMillis);
                    i -= AbstractPollingConnectionlessIoAcceptor.this.unregisterHandles();
                    AbstractPollingConnectionlessIoAcceptor.this.notifyIdleSessions(currentTimeMillis);
                } catch (ClosedSelectorException unused3) {
                } catch (Exception e) {
                    ExceptionMonitor.getInstance().exceptionCaught(e);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException unused4) {
                    }
                }
            }
            if (AbstractPollingConnectionlessIoAcceptor.this.selectable && AbstractPollingConnectionlessIoAcceptor.this.isDisposing()) {
                boolean unused5 = AbstractPollingConnectionlessIoAcceptor.this.selectable = false;
                try {
                    AbstractPollingConnectionlessIoAcceptor.this.destroy();
                } catch (Exception e2) {
                    ExceptionMonitor.getInstance().exceptionCaught(e2);
                } catch (Throwable th) {
                    AbstractPollingConnectionlessIoAcceptor.this.disposalFuture.setValue(true);
                    throw th;
                }
                AbstractPollingConnectionlessIoAcceptor.this.disposalFuture.setValue(true);
            }
        }
    }

    /* access modifiers changed from: private */
    public void processReadySessions(Iterator<H> it) {
        while (it.hasNext()) {
            H next = it.next();
            it.remove();
            try {
                if (isReadable(next)) {
                    readHandle(next);
                }
                if (isWritable(next)) {
                    Iterator<IoSession> it2 = getManagedSessions().values().iterator();
                    while (it2.hasNext()) {
                        scheduleFlush((AbstractIoSession) it2.next());
                    }
                }
            } catch (Throwable th) {
                ExceptionMonitor.getInstance().exceptionCaught(th);
            }
        }
    }

    private void readHandle(H h) throws Exception {
        IoBuffer allocate = IoBuffer.allocate(getSessionConfig().getReadBufferSize());
        SocketAddress receive = receive(h, allocate);
        if (receive != null) {
            IoSession newSessionWithoutLock = newSessionWithoutLock(receive, localAddress(h));
            allocate.flip();
            IoBuffer allocate2 = IoBuffer.allocate(allocate.limit());
            allocate2.put(allocate);
            allocate2.flip();
            newSessionWithoutLock.getFilterChain().fireMessageReceived(allocate2);
        }
    }

    /* access modifiers changed from: private */
    public void flushSessions(long j) {
        while (true) {
            AbstractIoSession abstractIoSession = (AbstractIoSession) this.flushingSessions.poll();
            if (abstractIoSession != null) {
                abstractIoSession.unscheduledForFlush();
                try {
                    if (flush(abstractIoSession, j) && !abstractIoSession.getWriteRequestQueue().isEmpty(abstractIoSession) && !abstractIoSession.isScheduledForFlush()) {
                        scheduleFlush(abstractIoSession);
                    }
                } catch (Exception e) {
                    abstractIoSession.getFilterChain().fireExceptionCaught(e);
                }
            } else {
                return;
            }
        }
    }

    private boolean flush(S s, long j) throws Exception {
        setInterestedInWrite(s, false);
        WriteRequestQueue writeRequestQueue = s.getWriteRequestQueue();
        int maxReadBufferSize = s.getConfig().getMaxReadBufferSize() + (s.getConfig().getMaxReadBufferSize() >>> 1);
        int i = 0;
        while (true) {
            try {
                WriteRequest currentWriteRequest = s.getCurrentWriteRequest();
                if (currentWriteRequest == null) {
                    currentWriteRequest = writeRequestQueue.poll(s);
                    if (currentWriteRequest == null) {
                        s.increaseWrittenBytes(i, j);
                        return true;
                    }
                    s.setCurrentWriteRequest(currentWriteRequest);
                }
                IoBuffer ioBuffer = (IoBuffer) currentWriteRequest.getMessage();
                if (ioBuffer.remaining() == 0) {
                    s.setCurrentWriteRequest((WriteRequest) null);
                    ioBuffer.reset();
                    s.getFilterChain().fireMessageSent(currentWriteRequest);
                } else {
                    SocketAddress destination = currentWriteRequest.getDestination();
                    if (destination == null) {
                        destination = s.getRemoteAddress();
                    }
                    int send = send(s, ioBuffer, destination);
                    if (send == 0) {
                        break;
                    } else if (i >= maxReadBufferSize) {
                        break;
                    } else {
                        setInterestedInWrite(s, false);
                        s.setCurrentWriteRequest((WriteRequest) null);
                        i += send;
                        ioBuffer.reset();
                        s.getFilterChain().fireMessageSent(currentWriteRequest);
                    }
                }
            } finally {
                s.increaseWrittenBytes(i, j);
            }
        }
        setInterestedInWrite(s, true);
        return false;
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
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.util.List r2 = r0.getLocalAddresses()
            java.util.Iterator r2 = r2.iterator()     // Catch:{ Exception -> 0x0073 }
        L_0x0019:
            boolean r3 = r2.hasNext()     // Catch:{ Exception -> 0x0073 }
            if (r3 == 0) goto L_0x0035
            java.lang.Object r3 = r2.next()     // Catch:{ Exception -> 0x0073 }
            java.net.SocketAddress r3 = (java.net.SocketAddress) r3     // Catch:{ Exception -> 0x0073 }
            java.lang.Object r3 = r5.open(r3)     // Catch:{ Exception -> 0x0073 }
            java.net.SocketAddress r4 = r5.localAddress(r3)     // Catch:{ Exception -> 0x0073 }
            java.lang.String r4 = r5.getAddressAsString(r4)     // Catch:{ Exception -> 0x0073 }
            r1.put(r4, r3)     // Catch:{ Exception -> 0x0073 }
            goto L_0x0019
        L_0x0035:
            java.util.Map<java.lang.String, H> r2 = r5.boundHandles     // Catch:{ Exception -> 0x0073 }
            r2.putAll(r1)     // Catch:{ Exception -> 0x0073 }
            org.apache.mina.core.service.IoServiceListenerSupport r2 = r5.getListeners()     // Catch:{ Exception -> 0x0073 }
            r2.fireServiceActivated()     // Catch:{ Exception -> 0x0073 }
            r0.setDone()     // Catch:{ Exception -> 0x0073 }
            int r2 = r1.size()     // Catch:{ Exception -> 0x0073 }
            java.lang.Exception r0 = r0.getException()
            if (r0 == 0) goto L_0x0070
            java.util.Collection r0 = r1.values()
            java.util.Iterator r0 = r0.iterator()
        L_0x0056:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x006d
            java.lang.Object r1 = r0.next()
            r5.close(r1)     // Catch:{ Exception -> 0x0064 }
            goto L_0x0056
        L_0x0064:
            r1 = move-exception
            org.apache.mina.util.ExceptionMonitor r3 = org.apache.mina.util.ExceptionMonitor.getInstance()
            r3.exceptionCaught(r1)
            goto L_0x0056
        L_0x006d:
            r5.wakeup()
        L_0x0070:
            return r2
        L_0x0071:
            r2 = move-exception
            goto L_0x00a1
        L_0x0073:
            r2 = move-exception
            r0.setException(r2)     // Catch:{ all -> 0x0071 }
            java.lang.Exception r0 = r0.getException()
            if (r0 == 0) goto L_0x0000
            java.util.Collection r0 = r1.values()
            java.util.Iterator r0 = r0.iterator()
        L_0x0085:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x009c
            java.lang.Object r1 = r0.next()
            r5.close(r1)     // Catch:{ Exception -> 0x0093 }
            goto L_0x0085
        L_0x0093:
            r1 = move-exception
            org.apache.mina.util.ExceptionMonitor r2 = org.apache.mina.util.ExceptionMonitor.getInstance()
            r2.exceptionCaught(r1)
            goto L_0x0085
        L_0x009c:
            r5.wakeup()
            goto L_0x0000
        L_0x00a1:
            java.lang.Exception r0 = r0.getException()
            if (r0 == 0) goto L_0x00c9
            java.util.Collection r0 = r1.values()
            java.util.Iterator r0 = r0.iterator()
        L_0x00af:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00c6
            java.lang.Object r1 = r0.next()
            r5.close(r1)     // Catch:{ Exception -> 0x00bd }
            goto L_0x00af
        L_0x00bd:
            r1 = move-exception
            org.apache.mina.util.ExceptionMonitor r3 = org.apache.mina.util.ExceptionMonitor.getInstance()
            r3.exceptionCaught(r1)
            goto L_0x00af
        L_0x00c6:
            r5.wakeup()
        L_0x00c9:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.core.polling.AbstractPollingConnectionlessIoAcceptor.registerHandles():int");
    }

    /* access modifiers changed from: private */
    public int unregisterHandles() {
        int i = 0;
        while (true) {
            AbstractIoAcceptor.AcceptorOperationFuture poll = this.cancelQueue.poll();
            if (poll == null) {
                return i;
            }
            for (SocketAddress addressAsString : poll.getLocalAddresses()) {
                H remove = this.boundHandles.remove(getAddressAsString(addressAsString));
                if (remove != null) {
                    try {
                        close(remove);
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

    /* access modifiers changed from: private */
    public void notifyIdleSessions(long j) {
        if (j - this.lastIdleCheckTime >= 1000) {
            this.lastIdleCheckTime = j;
            AbstractIoSession.notifyIdleness(getListeners().getManagedSessions().values().iterator(), j);
        }
    }
}
