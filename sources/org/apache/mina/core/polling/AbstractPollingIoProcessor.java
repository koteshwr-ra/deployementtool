package org.apache.mina.core.polling;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.net.PortUnreachableException;
import java.nio.channels.ClosedSelectorException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.file.FileRegion;
import org.apache.mina.core.future.DefaultIoFuture;
import org.apache.mina.core.service.AbstractIoService;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.session.AbstractIoSession;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.core.session.SessionState;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.core.write.WriteRequestQueue;
import org.apache.mina.core.write.WriteToClosedSessionException;
import org.apache.mina.transport.socket.AbstractDatagramSessionConfig;
import org.apache.mina.util.ExceptionMonitor;
import org.apache.mina.util.NamePreservingRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPollingIoProcessor<S extends AbstractIoSession> implements IoProcessor<S> {
    /* access modifiers changed from: private */
    public static final Logger LOG = LoggerFactory.getLogger(IoProcessor.class);
    private static final long SELECT_TIMEOUT = 1000;
    private static final int WRITE_SPIN_COUNT = 256;
    private static final Map<Class<?>, AtomicInteger> threadIds = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public final DefaultIoFuture disposalFuture = new DefaultIoFuture((IoSession) null);
    /* access modifiers changed from: private */
    public final Object disposalLock = new Object();
    private volatile boolean disposed;
    /* access modifiers changed from: private */
    public volatile boolean disposing;
    private final Executor executor;
    private final Queue<S> flushingSessions = new ConcurrentLinkedQueue();
    /* access modifiers changed from: private */
    public long lastIdleCheckTime;
    /* access modifiers changed from: private */
    public final Queue<S> newSessions = new ConcurrentLinkedQueue();
    /* access modifiers changed from: private */
    public final AtomicReference<AbstractPollingIoProcessor<S>.Processor> processorRef = new AtomicReference<>();
    private final Queue<S> removingSessions = new ConcurrentLinkedQueue();
    private final String threadName;
    private final Queue<S> trafficControllingSessions = new ConcurrentLinkedQueue();
    protected AtomicBoolean wakeupCalled = new AtomicBoolean(false);

    /* access modifiers changed from: protected */
    public abstract Iterator<S> allSessions();

    /* access modifiers changed from: protected */
    public abstract void destroy(S s) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void doDispose() throws Exception;

    /* access modifiers changed from: protected */
    public abstract SessionState getState(S s);

    /* access modifiers changed from: protected */
    public abstract void init(S s) throws Exception;

    /* access modifiers changed from: protected */
    public abstract boolean isBrokenConnection() throws IOException;

    /* access modifiers changed from: protected */
    public abstract boolean isInterestedInRead(S s);

    /* access modifiers changed from: protected */
    public abstract boolean isInterestedInWrite(S s);

    /* access modifiers changed from: protected */
    public abstract boolean isReadable(S s);

    /* access modifiers changed from: protected */
    public abstract boolean isSelectorEmpty();

    /* access modifiers changed from: protected */
    public abstract boolean isWritable(S s);

    /* access modifiers changed from: protected */
    public abstract int read(S s, IoBuffer ioBuffer) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void registerNewSelector() throws IOException;

    /* access modifiers changed from: protected */
    public abstract int select() throws Exception;

    /* access modifiers changed from: protected */
    public abstract int select(long j) throws Exception;

    /* access modifiers changed from: protected */
    public abstract Iterator<S> selectedSessions();

    /* access modifiers changed from: protected */
    public abstract void setInterestedInRead(S s, boolean z) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void setInterestedInWrite(S s, boolean z) throws Exception;

    /* access modifiers changed from: protected */
    public abstract int transferFile(S s, FileRegion fileRegion, int i) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void wakeup();

    /* access modifiers changed from: protected */
    public abstract int write(S s, IoBuffer ioBuffer, int i) throws Exception;

    protected AbstractPollingIoProcessor(Executor executor2) {
        if (executor2 != null) {
            this.threadName = nextThreadName();
            this.executor = executor2;
            return;
        }
        throw new IllegalArgumentException("executor");
    }

    private String nextThreadName() {
        int i;
        Class<?> cls = getClass();
        synchronized (threadIds) {
            AtomicInteger atomicInteger = threadIds.get(cls);
            if (atomicInteger == null) {
                i = 1;
                threadIds.put(cls, new AtomicInteger(1));
            } else {
                i = atomicInteger.incrementAndGet();
            }
        }
        return cls.getSimpleName() + '-' + i;
    }

    public final boolean isDisposing() {
        return this.disposing;
    }

    public final boolean isDisposed() {
        return this.disposed;
    }

    public final void dispose() {
        if (!this.disposed && !this.disposing) {
            synchronized (this.disposalLock) {
                this.disposing = true;
                startupProcessor();
            }
            this.disposalFuture.awaitUninterruptibly();
            this.disposed = true;
        }
    }

    public final void add(S s) {
        if (this.disposed || this.disposing) {
            throw new IllegalStateException("Already disposed.");
        }
        this.newSessions.add(s);
        startupProcessor();
    }

    public final void remove(S s) {
        scheduleRemove(s);
        startupProcessor();
    }

    /* access modifiers changed from: private */
    public void scheduleRemove(S s) {
        this.removingSessions.add(s);
    }

    public final void flush(S s) {
        if (s.setScheduledForFlush(true)) {
            this.flushingSessions.add(s);
            wakeup();
        }
    }

    private void scheduleFlush(S s) {
        if (s.setScheduledForFlush(true)) {
            this.flushingSessions.add(s);
        }
    }

    public final void updateTrafficMask(S s) {
        this.trafficControllingSessions.add(s);
        wakeup();
    }

    private void startupProcessor() {
        if (this.processorRef.get() == null) {
            Processor processor = new Processor(this, (AnonymousClass1) null);
            if (this.processorRef.compareAndSet((Object) null, processor)) {
                this.executor.execute(new NamePreservingRunnable(processor, this.threadName));
            }
        }
        wakeup();
    }

    /* access modifiers changed from: private */
    public int handleNewSessions() {
        AbstractIoSession abstractIoSession = (AbstractIoSession) this.newSessions.poll();
        int i = 0;
        while (abstractIoSession != null) {
            if (addNow(abstractIoSession)) {
                i++;
            }
            abstractIoSession = (AbstractIoSession) this.newSessions.poll();
        }
        return i;
    }

    private boolean addNow(S s) {
        try {
            init(s);
            s.getService().getFilterChainBuilder().buildFilterChain(s.getFilterChain());
            ((AbstractIoService) s.getService()).getListeners().fireSessionCreated(s);
            return true;
        } catch (Throwable th) {
            ExceptionMonitor.getInstance().exceptionCaught(th);
            try {
                destroy(s);
                return false;
            } catch (Exception e) {
                ExceptionMonitor.getInstance().exceptionCaught(e);
                return false;
            }
        }
    }

    /* access modifiers changed from: private */
    public int removeSessions() {
        AbstractIoSession abstractIoSession = (AbstractIoSession) this.removingSessions.poll();
        int i = 0;
        while (abstractIoSession != null) {
            SessionState state = getState(abstractIoSession);
            int i2 = AnonymousClass1.$SwitchMap$org$apache$mina$core$session$SessionState[state.ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    continue;
                } else if (i2 == 3) {
                    this.newSessions.remove(abstractIoSession);
                    if (!removeNow(abstractIoSession)) {
                    }
                } else {
                    throw new IllegalStateException(String.valueOf(state));
                }
                abstractIoSession = (AbstractIoSession) this.removingSessions.poll();
            } else if (!removeNow(abstractIoSession)) {
                abstractIoSession = (AbstractIoSession) this.removingSessions.poll();
            }
            i++;
            abstractIoSession = (AbstractIoSession) this.removingSessions.poll();
        }
        return i;
    }

    /* renamed from: org.apache.mina.core.polling.AbstractPollingIoProcessor$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$mina$core$session$SessionState;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.mina.core.session.SessionState[] r0 = org.apache.mina.core.session.SessionState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$mina$core$session$SessionState = r0
                org.apache.mina.core.session.SessionState r1 = org.apache.mina.core.session.SessionState.OPENED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$mina$core$session$SessionState     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.mina.core.session.SessionState r1 = org.apache.mina.core.session.SessionState.CLOSING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$mina$core$session$SessionState     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.mina.core.session.SessionState r1 = org.apache.mina.core.session.SessionState.OPENING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.core.polling.AbstractPollingIoProcessor.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX INFO: finally extract failed */
    private boolean removeNow(S s) {
        clearWriteRequestQueue(s);
        try {
            destroy(s);
            clearWriteRequestQueue(s);
            ((AbstractIoService) s.getService()).getListeners().fireSessionDestroyed(s);
            return true;
        } catch (Exception e) {
            s.getFilterChain().fireExceptionCaught(e);
            clearWriteRequestQueue(s);
            ((AbstractIoService) s.getService()).getListeners().fireSessionDestroyed(s);
            return false;
        } catch (Throwable th) {
            clearWriteRequestQueue(s);
            ((AbstractIoService) s.getService()).getListeners().fireSessionDestroyed(s);
            throw th;
        }
    }

    private void clearWriteRequestQueue(S s) {
        WriteRequestQueue writeRequestQueue = s.getWriteRequestQueue();
        ArrayList<WriteRequest> arrayList = new ArrayList<>();
        WriteRequest poll = writeRequestQueue.poll(s);
        if (poll != null) {
            Object message = poll.getMessage();
            if (message instanceof IoBuffer) {
                IoBuffer ioBuffer = (IoBuffer) message;
                if (ioBuffer.hasRemaining()) {
                    ioBuffer.reset();
                    arrayList.add(poll);
                } else {
                    s.getFilterChain().fireMessageSent(poll);
                }
            } else {
                arrayList.add(poll);
            }
            while (true) {
                WriteRequest poll2 = writeRequestQueue.poll(s);
                if (poll2 == null) {
                    break;
                }
                arrayList.add(poll2);
            }
        }
        if (!arrayList.isEmpty()) {
            WriteToClosedSessionException writeToClosedSessionException = new WriteToClosedSessionException((Collection<WriteRequest>) arrayList);
            for (WriteRequest writeRequest : arrayList) {
                s.decreaseScheduledBytesAndMessages(writeRequest);
                writeRequest.getFuture().setException(writeToClosedSessionException);
            }
            s.getFilterChain().fireExceptionCaught(writeToClosedSessionException);
        }
    }

    /* access modifiers changed from: private */
    public void process() throws Exception {
        Iterator selectedSessions = selectedSessions();
        while (selectedSessions.hasNext()) {
            process((AbstractIoSession) selectedSessions.next());
            selectedSessions.remove();
        }
    }

    private void process(S s) {
        if (isReadable(s) && !s.isReadSuspended()) {
            read(s);
        }
        if (isWritable(s) && !s.isWriteSuspended() && s.setScheduledForFlush(true)) {
            this.flushingSessions.add(s);
        }
    }

    private void read(S s) {
        int i;
        IoSessionConfig config = s.getConfig();
        IoBuffer allocate = IoBuffer.allocate(config.getReadBufferSize());
        boolean hasFragmentation = s.getTransportMetadata().hasFragmentation();
        int i2 = 0;
        if (hasFragmentation) {
            while (true) {
                try {
                    i = read(s, allocate);
                    if (i > 0) {
                        i2 += i;
                        if (!allocate.hasRemaining()) {
                            break;
                        }
                    }
                } catch (Throwable th) {
                    if ((th instanceof IOException) && (!(th instanceof PortUnreachableException) || !AbstractDatagramSessionConfig.class.isAssignableFrom(config.getClass()) || ((AbstractDatagramSessionConfig) config).isCloseOnPortUnreachable())) {
                        scheduleRemove(s);
                    }
                    s.getFilterChain().fireExceptionCaught(th);
                    return;
                }
            }
        } else {
            i = read(s, allocate);
            if (i > 0) {
                i2 = i;
            }
        }
        allocate.flip();
        if (i2 > 0) {
            s.getFilterChain().fireMessageReceived(allocate);
            if (hasFragmentation) {
                if ((i2 << 1) < config.getReadBufferSize()) {
                    s.decreaseReadBufferSize();
                } else if (i2 == config.getReadBufferSize()) {
                    s.increaseReadBufferSize();
                }
            }
        }
        if (i < 0) {
            scheduleRemove(s);
        }
    }

    private static String byteArrayToHex(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            byte b = bArr[i];
            int i3 = (b & 255) >> 4;
            int i4 = i2 + 1;
            cArr[i2] = (char) (i3 > 9 ? i3 + 55 : i3 + 48);
            byte b2 = b & Ascii.SI;
            int i5 = i4 + 1;
            cArr[i4] = (char) (b2 > 9 ? b2 + 55 : b2 + 48);
            if (i5 > 60) {
                break;
            }
            i++;
            i2 = i5;
        }
        return new String(cArr);
    }

    /* access modifiers changed from: private */
    public void notifyIdleSessions(long j) throws Exception {
        if (j - this.lastIdleCheckTime >= 1000) {
            this.lastIdleCheckTime = j;
            AbstractIoSession.notifyIdleness(allSessions(), j);
        }
    }

    /* access modifiers changed from: private */
    public void flush(long j) {
        if (!this.flushingSessions.isEmpty()) {
            do {
                AbstractIoSession abstractIoSession = (AbstractIoSession) this.flushingSessions.poll();
                if (abstractIoSession != null) {
                    abstractIoSession.unscheduledForFlush();
                    SessionState state = getState(abstractIoSession);
                    int i = AnonymousClass1.$SwitchMap$org$apache$mina$core$session$SessionState[state.ordinal()];
                    if (i == 1) {
                        try {
                            if (flushNow(abstractIoSession, j) && !abstractIoSession.getWriteRequestQueue().isEmpty(abstractIoSession) && !abstractIoSession.isScheduledForFlush()) {
                                scheduleFlush(abstractIoSession);
                            }
                        } catch (Exception e) {
                            scheduleRemove(abstractIoSession);
                            abstractIoSession.getFilterChain().fireExceptionCaught(e);
                        }
                    } else if (i != 2) {
                        if (i == 3) {
                            scheduleFlush(abstractIoSession);
                            return;
                        }
                        throw new IllegalStateException(String.valueOf(state));
                    }
                } else {
                    return;
                }
            } while (!this.flushingSessions.isEmpty());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean flushNow(S r17, long r18) {
        /*
            r16 = this;
            r8 = r16
            r9 = r17
            boolean r0 = r17.isConnected()
            r10 = 0
            if (r0 != 0) goto L_0x000f
            r16.scheduleRemove(r17)
            return r10
        L_0x000f:
            org.apache.mina.core.service.TransportMetadata r0 = r17.getTransportMetadata()
            boolean r0 = r0.hasFragmentation()
            org.apache.mina.core.write.WriteRequestQueue r11 = r17.getWriteRequestQueue()
            org.apache.mina.core.session.IoSessionConfig r1 = r17.getConfig()
            int r1 = r1.getMaxReadBufferSize()
            org.apache.mina.core.session.IoSessionConfig r2 = r17.getConfig()
            int r2 = r2.getMaxReadBufferSize()
            r12 = 1
            int r2 = r2 >>> r12
            int r13 = r1 + r2
            r1 = 0
            r8.setInterestedInWrite(r9, r10)     // Catch:{ Exception -> 0x00d0 }
            r14 = 0
        L_0x0034:
            org.apache.mina.core.write.WriteRequest r1 = r17.getCurrentWriteRequest()     // Catch:{ Exception -> 0x00d0 }
            if (r1 != 0) goto L_0x0045
            org.apache.mina.core.write.WriteRequest r1 = r11.poll(r9)     // Catch:{ Exception -> 0x00d0 }
            if (r1 != 0) goto L_0x0042
            goto L_0x00a5
        L_0x0042:
            r9.setCurrentWriteRequest(r1)     // Catch:{ Exception -> 0x00d0 }
        L_0x0045:
            r15 = r1
            java.lang.Object r6 = r15.getMessage()     // Catch:{ Exception -> 0x00cd }
            boolean r1 = r6 instanceof org.apache.mina.core.buffer.IoBuffer     // Catch:{ Exception -> 0x00cd }
            if (r1 == 0) goto L_0x006d
            int r5 = r13 - r14
            r1 = r16
            r2 = r17
            r3 = r15
            r4 = r0
            r10 = r6
            r6 = r18
            int r1 = r1.writeBuffer(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00cd }
            if (r1 <= 0) goto L_0x0094
            r6 = r10
            org.apache.mina.core.buffer.IoBuffer r6 = (org.apache.mina.core.buffer.IoBuffer) r6     // Catch:{ Exception -> 0x00cd }
            boolean r2 = r6.hasRemaining()     // Catch:{ Exception -> 0x00cd }
            if (r2 == 0) goto L_0x0094
            r8.setInterestedInWrite(r9, r12)     // Catch:{ Exception -> 0x00cd }
            r1 = 0
            return r1
        L_0x006d:
            r10 = r6
            boolean r1 = r10 instanceof org.apache.mina.core.file.FileRegion     // Catch:{ Exception -> 0x00cd }
            if (r1 == 0) goto L_0x00a9
            int r5 = r13 - r14
            r1 = r16
            r2 = r17
            r3 = r15
            r4 = r0
            r6 = r18
            int r1 = r1.writeFile(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00cd }
            if (r1 <= 0) goto L_0x0094
            r6 = r10
            org.apache.mina.core.file.FileRegion r6 = (org.apache.mina.core.file.FileRegion) r6     // Catch:{ Exception -> 0x00cd }
            long r2 = r6.getRemainingBytes()     // Catch:{ Exception -> 0x00cd }
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x0094
            r8.setInterestedInWrite(r9, r12)     // Catch:{ Exception -> 0x00cd }
            r1 = 0
            return r1
        L_0x0094:
            if (r1 != 0) goto L_0x009b
            r8.setInterestedInWrite(r9, r12)     // Catch:{ Exception -> 0x00cd }
            r2 = 0
            return r2
        L_0x009b:
            r2 = 0
            int r14 = r14 + r1
            if (r14 < r13) goto L_0x00a3
            r16.scheduleFlush(r17)     // Catch:{ Exception -> 0x00cd }
            return r2
        L_0x00a3:
            if (r14 < r13) goto L_0x00a6
        L_0x00a5:
            return r12
        L_0x00a6:
            r1 = r15
            r10 = 0
            goto L_0x0034
        L_0x00a9:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ Exception -> 0x00cd }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00cd }
            r1.<init>()     // Catch:{ Exception -> 0x00cd }
            java.lang.String r2 = "Don't know how to handle message of type '"
            r1.append(r2)     // Catch:{ Exception -> 0x00cd }
            java.lang.Class r2 = r10.getClass()     // Catch:{ Exception -> 0x00cd }
            java.lang.String r2 = r2.getName()     // Catch:{ Exception -> 0x00cd }
            r1.append(r2)     // Catch:{ Exception -> 0x00cd }
            java.lang.String r2 = "'.  Are you missing a protocol encoder?"
            r1.append(r2)     // Catch:{ Exception -> 0x00cd }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00cd }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00cd }
            throw r0     // Catch:{ Exception -> 0x00cd }
        L_0x00cd:
            r0 = move-exception
            r1 = r15
            goto L_0x00d1
        L_0x00d0:
            r0 = move-exception
        L_0x00d1:
            if (r1 == 0) goto L_0x00da
            org.apache.mina.core.future.WriteFuture r1 = r1.getFuture()
            r1.setException(r0)
        L_0x00da:
            org.apache.mina.core.filterchain.IoFilterChain r1 = r17.getFilterChain()
            r1.fireExceptionCaught(r0)
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.core.polling.AbstractPollingIoProcessor.flushNow(org.apache.mina.core.session.AbstractIoSession, long):boolean");
    }

    private int writeBuffer(S s, WriteRequest writeRequest, boolean z, int i, long j) throws Exception {
        int i2;
        int i3;
        IoBuffer ioBuffer = (IoBuffer) writeRequest.getMessage();
        if (ioBuffer.hasRemaining()) {
            if (z) {
                i3 = Math.min(ioBuffer.remaining(), i);
            } else {
                i3 = ioBuffer.remaining();
            }
            i2 = write(s, ioBuffer, i3);
        } else {
            i2 = 0;
        }
        s.increaseWrittenBytes(i2, j);
        if (!ioBuffer.hasRemaining() || (!z && i2 != 0)) {
            int position = ioBuffer.position();
            ioBuffer.reset();
            fireMessageSent(s, writeRequest);
            ioBuffer.position(position);
        }
        return i2;
    }

    private int writeFile(S s, WriteRequest writeRequest, boolean z, int i, long j) throws Exception {
        int i2;
        long j2;
        FileRegion fileRegion = (FileRegion) writeRequest.getMessage();
        if (fileRegion.getRemainingBytes() > 0) {
            if (z) {
                j2 = Math.min(fileRegion.getRemainingBytes(), (long) i);
            } else {
                j2 = Math.min(2147483647L, fileRegion.getRemainingBytes());
            }
            i2 = transferFile(s, fileRegion, (int) j2);
            fileRegion.update((long) i2);
        } else {
            i2 = 0;
        }
        s.increaseWrittenBytes(i2, j);
        if (fileRegion.getRemainingBytes() <= 0 || (!z && i2 != 0)) {
            fireMessageSent(s, writeRequest);
        }
        return i2;
    }

    private void fireMessageSent(S s, WriteRequest writeRequest) {
        s.setCurrentWriteRequest((WriteRequest) null);
        s.getFilterChain().fireMessageSent(writeRequest);
    }

    /* access modifiers changed from: private */
    public void updateTrafficMask() {
        int size = this.trafficControllingSessions.size();
        while (size > 0) {
            AbstractIoSession abstractIoSession = (AbstractIoSession) this.trafficControllingSessions.poll();
            if (abstractIoSession != null) {
                SessionState state = getState(abstractIoSession);
                int i = AnonymousClass1.$SwitchMap$org$apache$mina$core$session$SessionState[state.ordinal()];
                if (i == 1) {
                    updateTrafficControl(abstractIoSession);
                } else if (i == 2) {
                    continue;
                } else if (i == 3) {
                    this.trafficControllingSessions.add(abstractIoSession);
                } else {
                    throw new IllegalStateException(String.valueOf(state));
                }
                size--;
            } else {
                return;
            }
        }
    }

    public void updateTrafficControl(S s) {
        boolean z = true;
        try {
            setInterestedInRead(s, !s.isReadSuspended());
        } catch (Exception e) {
            s.getFilterChain().fireExceptionCaught(e);
        }
        try {
            if (s.getWriteRequestQueue().isEmpty(s) || s.isWriteSuspended()) {
                z = false;
            }
            setInterestedInWrite(s, z);
        } catch (Exception e2) {
            s.getFilterChain().fireExceptionCaught(e2);
        }
    }

    private class Processor implements Runnable {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        static {
            Class<AbstractPollingIoProcessor> cls = AbstractPollingIoProcessor.class;
        }

        private Processor() {
        }

        /* synthetic */ Processor(AbstractPollingIoProcessor abstractPollingIoProcessor, AnonymousClass1 r2) {
            this();
        }

        public void run() {
            long unused = AbstractPollingIoProcessor.this.lastIdleCheckTime = System.currentTimeMillis();
            int i = 0;
            while (true) {
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    int select = AbstractPollingIoProcessor.this.select(1000);
                    long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                    if (select != 0 || AbstractPollingIoProcessor.this.wakeupCalled.get() || currentTimeMillis2 >= 100) {
                        int access$400 = i + AbstractPollingIoProcessor.this.handleNewSessions();
                        AbstractPollingIoProcessor.this.updateTrafficMask();
                        if (select > 0) {
                            AbstractPollingIoProcessor.this.process();
                        }
                        long currentTimeMillis3 = System.currentTimeMillis();
                        AbstractPollingIoProcessor.this.flush(currentTimeMillis3);
                        i = access$400 - AbstractPollingIoProcessor.this.removeSessions();
                        AbstractPollingIoProcessor.this.notifyIdleSessions(currentTimeMillis3);
                        if (i == 0) {
                            AbstractPollingIoProcessor.this.processorRef.set((Object) null);
                            if (!AbstractPollingIoProcessor.this.newSessions.isEmpty() || !AbstractPollingIoProcessor.this.isSelectorEmpty()) {
                                if (!AbstractPollingIoProcessor.this.processorRef.compareAndSet((Object) null, this)) {
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        if (AbstractPollingIoProcessor.this.isDisposing()) {
                            Iterator allSessions = AbstractPollingIoProcessor.this.allSessions();
                            while (allSessions.hasNext()) {
                                AbstractPollingIoProcessor.this.scheduleRemove((AbstractIoSession) allSessions.next());
                            }
                            AbstractPollingIoProcessor.this.wakeup();
                        }
                    } else if (AbstractPollingIoProcessor.this.isBrokenConnection()) {
                        AbstractPollingIoProcessor.LOG.warn("Broken connection");
                        AbstractPollingIoProcessor.this.wakeupCalled.getAndSet(false);
                    } else {
                        Logger access$300 = AbstractPollingIoProcessor.LOG;
                        access$300.warn("Create a new selector. Selected is 0, delta = " + currentTimeMillis2);
                        AbstractPollingIoProcessor.this.registerNewSelector();
                        AbstractPollingIoProcessor.this.wakeupCalled.getAndSet(false);
                    }
                } catch (ClosedSelectorException unused2) {
                } catch (Throwable th) {
                    ExceptionMonitor.getInstance().exceptionCaught(th);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        ExceptionMonitor.getInstance().exceptionCaught(e);
                    }
                }
            }
            try {
                synchronized (AbstractPollingIoProcessor.this.disposalLock) {
                    if (AbstractPollingIoProcessor.this.disposing) {
                        AbstractPollingIoProcessor.this.doDispose();
                    }
                }
            } catch (Throwable th2) {
                try {
                    ExceptionMonitor.getInstance().exceptionCaught(th2);
                } catch (Throwable th3) {
                    AbstractPollingIoProcessor.this.disposalFuture.setValue(true);
                    throw th3;
                }
            }
            AbstractPollingIoProcessor.this.disposalFuture.setValue(true);
        }
    }
}
