package org.apache.mina.core.session;

import java.net.SocketAddress;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.log4j.spi.Configurator;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.DefaultCloseFuture;
import org.apache.mina.core.future.DefaultReadFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.AbstractIoService;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.TransportMetadata;
import org.apache.mina.core.write.DefaultWriteRequest;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.core.write.WriteRequestQueue;
import org.apache.mina.core.write.WriteTimeoutException;

public abstract class AbstractIoSession implements IoSession {
    /* access modifiers changed from: private */
    public static final WriteRequest CLOSE_REQUEST = new DefaultWriteRequest(new Object());
    private static final AttributeKey READY_READ_FUTURES_KEY;
    private static final IoFutureListener<CloseFuture> SCHEDULED_COUNTER_RESETTER = new IoFutureListener<CloseFuture>() {
        public void operationComplete(CloseFuture closeFuture) {
            AbstractIoSession abstractIoSession = (AbstractIoSession) closeFuture.getSession();
            abstractIoSession.scheduledWriteBytes.set(0);
            abstractIoSession.scheduledWriteMessages.set(0);
            double unused = abstractIoSession.readBytesThroughput = 0.0d;
            double unused2 = abstractIoSession.readMessagesThroughput = 0.0d;
            double unused3 = abstractIoSession.writtenBytesThroughput = 0.0d;
            double unused4 = abstractIoSession.writtenMessagesThroughput = 0.0d;
        }
    };
    private static final AttributeKey WAITING_READ_FUTURES_KEY;
    private static AtomicLong idGenerator = new AtomicLong(0);
    private IoSessionAttributeMap attributes;
    private final CloseFuture closeFuture = new DefaultCloseFuture(this);
    private volatile boolean closing;
    protected IoSessionConfig config;
    private final long creationTime;
    private WriteRequest currentWriteRequest;
    private boolean deferDecreaseReadBuffer = true;
    private final IoHandler handler;
    private AtomicInteger idleCountForBoth = new AtomicInteger();
    private AtomicInteger idleCountForRead = new AtomicInteger();
    private AtomicInteger idleCountForWrite = new AtomicInteger();
    private long lastIdleTimeForBoth;
    private long lastIdleTimeForRead;
    private long lastIdleTimeForWrite;
    private long lastReadBytes;
    private long lastReadMessages;
    private long lastReadTime;
    private long lastThroughputCalculationTime;
    private long lastWriteTime;
    private long lastWrittenBytes;
    private long lastWrittenMessages;
    private final Object lock = new Object();
    private long readBytes;
    /* access modifiers changed from: private */
    public double readBytesThroughput;
    private long readMessages;
    /* access modifiers changed from: private */
    public double readMessagesThroughput;
    private boolean readSuspended = false;
    private final AtomicBoolean scheduledForFlush = new AtomicBoolean();
    /* access modifiers changed from: private */
    public final AtomicInteger scheduledWriteBytes = new AtomicInteger();
    /* access modifiers changed from: private */
    public final AtomicInteger scheduledWriteMessages = new AtomicInteger();
    private final IoService service;
    private long sessionId;
    private WriteRequestQueue writeRequestQueue;
    private boolean writeSuspended = false;
    private long writtenBytes;
    /* access modifiers changed from: private */
    public double writtenBytesThroughput;
    private long writtenMessages;
    /* access modifiers changed from: private */
    public double writtenMessagesThroughput;

    public abstract IoProcessor getProcessor();

    static {
        Class<AbstractIoSession> cls = AbstractIoSession.class;
        READY_READ_FUTURES_KEY = new AttributeKey(cls, "readyReadFutures");
        WAITING_READ_FUTURES_KEY = new AttributeKey(cls, "waitingReadFutures");
    }

    protected AbstractIoSession(IoService ioService) {
        this.service = ioService;
        this.handler = ioService.getHandler();
        long currentTimeMillis = System.currentTimeMillis();
        this.creationTime = currentTimeMillis;
        this.lastThroughputCalculationTime = currentTimeMillis;
        this.lastReadTime = currentTimeMillis;
        this.lastWriteTime = currentTimeMillis;
        this.lastIdleTimeForBoth = currentTimeMillis;
        this.lastIdleTimeForRead = currentTimeMillis;
        this.lastIdleTimeForWrite = currentTimeMillis;
        this.closeFuture.addListener(SCHEDULED_COUNTER_RESETTER);
        this.sessionId = idGenerator.incrementAndGet();
    }

    public final long getId() {
        return this.sessionId;
    }

    public final boolean isConnected() {
        return !this.closeFuture.isClosed();
    }

    public final boolean isClosing() {
        return this.closing || this.closeFuture.isClosed();
    }

    public final CloseFuture getCloseFuture() {
        return this.closeFuture;
    }

    public final boolean isScheduledForFlush() {
        return this.scheduledForFlush.get();
    }

    public final void scheduledForFlush() {
        this.scheduledForFlush.set(true);
    }

    public final void unscheduledForFlush() {
        this.scheduledForFlush.set(false);
    }

    public final boolean setScheduledForFlush(boolean z) {
        if (z) {
            return this.scheduledForFlush.compareAndSet(false, z);
        }
        this.scheduledForFlush.set(z);
        return true;
    }

    public final CloseFuture close(boolean z) {
        if (z) {
            return close();
        }
        return closeOnFlush();
    }

    public final CloseFuture close() {
        synchronized (this.lock) {
            if (isClosing()) {
                CloseFuture closeFuture2 = this.closeFuture;
                return closeFuture2;
            }
            this.closing = true;
            getFilterChain().fireFilterClose();
            return this.closeFuture;
        }
    }

    private final CloseFuture closeOnFlush() {
        getWriteRequestQueue().offer(this, CLOSE_REQUEST);
        getProcessor().flush(this);
        return this.closeFuture;
    }

    public IoHandler getHandler() {
        return this.handler;
    }

    public IoSessionConfig getConfig() {
        return this.config;
    }

    public final ReadFuture read() {
        ReadFuture poll;
        if (getConfig().isUseReadOperation()) {
            Queue<ReadFuture> readyReadFutures = getReadyReadFutures();
            synchronized (readyReadFutures) {
                poll = readyReadFutures.poll();
                if (poll == null) {
                    poll = new DefaultReadFuture(this);
                    getWaitingReadFutures().offer(poll);
                } else if (poll.isClosed()) {
                    readyReadFutures.offer(poll);
                }
            }
            return poll;
        }
        throw new IllegalStateException("useReadOperation is not enabled.");
    }

    public final void offerReadFuture(Object obj) {
        newReadFuture().setRead(obj);
    }

    public final void offerFailedReadFuture(Throwable th) {
        newReadFuture().setException(th);
    }

    public final void offerClosedReadFuture() {
        synchronized (getReadyReadFutures()) {
            newReadFuture().setClosed();
        }
    }

    private ReadFuture newReadFuture() {
        ReadFuture poll;
        Queue<ReadFuture> readyReadFutures = getReadyReadFutures();
        Queue<ReadFuture> waitingReadFutures = getWaitingReadFutures();
        synchronized (readyReadFutures) {
            poll = waitingReadFutures.poll();
            if (poll == null) {
                poll = new DefaultReadFuture(this);
                readyReadFutures.offer(poll);
            }
        }
        return poll;
    }

    private Queue<ReadFuture> getReadyReadFutures() {
        Queue<ReadFuture> queue = (Queue) getAttribute(READY_READ_FUTURES_KEY);
        if (queue != null) {
            return queue;
        }
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        Queue<ReadFuture> queue2 = (Queue) setAttributeIfAbsent(READY_READ_FUTURES_KEY, concurrentLinkedQueue);
        return queue2 != null ? queue2 : concurrentLinkedQueue;
    }

    private Queue<ReadFuture> getWaitingReadFutures() {
        Queue<ReadFuture> queue = (Queue) getAttribute(WAITING_READ_FUTURES_KEY);
        if (queue != null) {
            return queue;
        }
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        Queue<ReadFuture> queue2 = (Queue) setAttributeIfAbsent(WAITING_READ_FUTURES_KEY, concurrentLinkedQueue);
        return queue2 != null ? queue2 : concurrentLinkedQueue;
    }

    public WriteFuture write(Object obj) {
        return write(obj, (SocketAddress) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: java.lang.Object} */
    /* JADX WARNING: type inference failed for: r10v13 */
    /* JADX WARNING: type inference failed for: r2v3, types: [org.apache.mina.core.file.FilenameFileRegion] */
    /* JADX WARNING: type inference failed for: r2v4, types: [org.apache.mina.core.file.DefaultFileRegion] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.mina.core.future.WriteFuture write(java.lang.Object r10, java.net.SocketAddress r11) {
        /*
            r9 = this;
            if (r10 == 0) goto L_0x00a7
            org.apache.mina.core.service.TransportMetadata r0 = r9.getTransportMetadata()
            boolean r0 = r0.isConnectionless()
            if (r0 != 0) goto L_0x0015
            if (r11 != 0) goto L_0x000f
            goto L_0x0015
        L_0x000f:
            java.lang.UnsupportedOperationException r10 = new java.lang.UnsupportedOperationException
            r10.<init>()
            throw r10
        L_0x0015:
            boolean r0 = r9.isClosing()
            if (r0 != 0) goto L_0x0094
            boolean r0 = r9.isConnected()
            if (r0 != 0) goto L_0x0023
            goto L_0x0094
        L_0x0023:
            r0 = 0
            boolean r1 = r10 instanceof org.apache.mina.core.buffer.IoBuffer     // Catch:{ IOException -> 0x0087 }
            if (r1 == 0) goto L_0x003a
            r1 = r10
            org.apache.mina.core.buffer.IoBuffer r1 = (org.apache.mina.core.buffer.IoBuffer) r1     // Catch:{ IOException -> 0x0087 }
            boolean r1 = r1.hasRemaining()     // Catch:{ IOException -> 0x0087 }
            if (r1 == 0) goto L_0x0032
            goto L_0x003a
        L_0x0032:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ IOException -> 0x0087 }
            java.lang.String r11 = "message is empty. Forgot to call flip()?"
            r10.<init>(r11)     // Catch:{ IOException -> 0x0087 }
            throw r10     // Catch:{ IOException -> 0x0087 }
        L_0x003a:
            boolean r1 = r10 instanceof java.nio.channels.FileChannel     // Catch:{ IOException -> 0x0087 }
            if (r1 == 0) goto L_0x004e
            r3 = r10
            java.nio.channels.FileChannel r3 = (java.nio.channels.FileChannel) r3     // Catch:{ IOException -> 0x0087 }
            org.apache.mina.core.file.DefaultFileRegion r10 = new org.apache.mina.core.file.DefaultFileRegion     // Catch:{ IOException -> 0x0087 }
            r4 = 0
            long r6 = r3.size()     // Catch:{ IOException -> 0x0087 }
            r2 = r10
            r2.<init>(r3, r4, r6)     // Catch:{ IOException -> 0x0087 }
            goto L_0x006b
        L_0x004e:
            boolean r1 = r10 instanceof java.io.File     // Catch:{ IOException -> 0x0087 }
            if (r1 == 0) goto L_0x006b
            r3 = r10
            java.io.File r3 = (java.io.File) r3     // Catch:{ IOException -> 0x0087 }
            java.io.FileInputStream r10 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0087 }
            r10.<init>(r3)     // Catch:{ IOException -> 0x0087 }
            java.nio.channels.FileChannel r0 = r10.getChannel()     // Catch:{ IOException -> 0x0087 }
            org.apache.mina.core.file.FilenameFileRegion r10 = new org.apache.mina.core.file.FilenameFileRegion     // Catch:{ IOException -> 0x0087 }
            r5 = 0
            long r7 = r0.size()     // Catch:{ IOException -> 0x0087 }
            r2 = r10
            r4 = r0
            r2.<init>(r3, r4, r5, r7)     // Catch:{ IOException -> 0x0087 }
        L_0x006b:
            org.apache.mina.core.future.DefaultWriteFuture r1 = new org.apache.mina.core.future.DefaultWriteFuture
            r1.<init>(r9)
            org.apache.mina.core.write.DefaultWriteRequest r2 = new org.apache.mina.core.write.DefaultWriteRequest
            r2.<init>(r10, r1, r11)
            org.apache.mina.core.filterchain.IoFilterChain r10 = r9.getFilterChain()
            r10.fireFilterWrite(r2)
            if (r0 == 0) goto L_0x0086
            org.apache.mina.core.session.AbstractIoSession$2 r10 = new org.apache.mina.core.session.AbstractIoSession$2
            r10.<init>(r0)
            r1.addListener(r10)
        L_0x0086:
            return r1
        L_0x0087:
            r10 = move-exception
            org.apache.mina.util.ExceptionMonitor r11 = org.apache.mina.util.ExceptionMonitor.getInstance()
            r11.exceptionCaught(r10)
            org.apache.mina.core.future.WriteFuture r10 = org.apache.mina.core.future.DefaultWriteFuture.newNotWrittenFuture(r9, r10)
            return r10
        L_0x0094:
            org.apache.mina.core.future.DefaultWriteFuture r0 = new org.apache.mina.core.future.DefaultWriteFuture
            r0.<init>(r9)
            org.apache.mina.core.write.DefaultWriteRequest r1 = new org.apache.mina.core.write.DefaultWriteRequest
            r1.<init>(r10, r0, r11)
            org.apache.mina.core.write.WriteToClosedSessionException r10 = new org.apache.mina.core.write.WriteToClosedSessionException
            r10.<init>((org.apache.mina.core.write.WriteRequest) r1)
            r0.setException(r10)
            return r0
        L_0x00a7:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.String r11 = "message"
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.core.session.AbstractIoSession.write(java.lang.Object, java.net.SocketAddress):org.apache.mina.core.future.WriteFuture");
    }

    public final Object getAttachment() {
        return getAttribute("");
    }

    public final Object setAttachment(Object obj) {
        return setAttribute("", obj);
    }

    public final Object getAttribute(Object obj) {
        return getAttribute(obj, (Object) null);
    }

    public final Object getAttribute(Object obj, Object obj2) {
        return this.attributes.getAttribute(this, obj, obj2);
    }

    public final Object setAttribute(Object obj, Object obj2) {
        return this.attributes.setAttribute(this, obj, obj2);
    }

    public final Object setAttribute(Object obj) {
        return setAttribute(obj, Boolean.TRUE);
    }

    public final Object setAttributeIfAbsent(Object obj, Object obj2) {
        return this.attributes.setAttributeIfAbsent(this, obj, obj2);
    }

    public final Object setAttributeIfAbsent(Object obj) {
        return setAttributeIfAbsent(obj, Boolean.TRUE);
    }

    public final Object removeAttribute(Object obj) {
        return this.attributes.removeAttribute(this, obj);
    }

    public final boolean removeAttribute(Object obj, Object obj2) {
        return this.attributes.removeAttribute(this, obj, obj2);
    }

    public final boolean replaceAttribute(Object obj, Object obj2, Object obj3) {
        return this.attributes.replaceAttribute(this, obj, obj2, obj3);
    }

    public final boolean containsAttribute(Object obj) {
        return this.attributes.containsAttribute(this, obj);
    }

    public final Set<Object> getAttributeKeys() {
        return this.attributes.getAttributeKeys(this);
    }

    public final IoSessionAttributeMap getAttributeMap() {
        return this.attributes;
    }

    public final void setAttributeMap(IoSessionAttributeMap ioSessionAttributeMap) {
        this.attributes = ioSessionAttributeMap;
    }

    public final void setWriteRequestQueue(WriteRequestQueue writeRequestQueue2) {
        this.writeRequestQueue = new CloseAwareWriteQueue(writeRequestQueue2);
    }

    public final void suspendRead() {
        this.readSuspended = true;
        if (!isClosing() && isConnected()) {
            getProcessor().updateTrafficControl(this);
        }
    }

    public final void suspendWrite() {
        this.writeSuspended = true;
        if (!isClosing() && isConnected()) {
            getProcessor().updateTrafficControl(this);
        }
    }

    public final void resumeRead() {
        this.readSuspended = false;
        if (!isClosing() && isConnected()) {
            getProcessor().updateTrafficControl(this);
        }
    }

    public final void resumeWrite() {
        this.writeSuspended = false;
        if (!isClosing() && isConnected()) {
            getProcessor().updateTrafficControl(this);
        }
    }

    public boolean isReadSuspended() {
        return this.readSuspended;
    }

    public boolean isWriteSuspended() {
        return this.writeSuspended;
    }

    public final long getReadBytes() {
        return this.readBytes;
    }

    public final long getWrittenBytes() {
        return this.writtenBytes;
    }

    public final long getReadMessages() {
        return this.readMessages;
    }

    public final long getWrittenMessages() {
        return this.writtenMessages;
    }

    public final double getReadBytesThroughput() {
        return this.readBytesThroughput;
    }

    public final double getWrittenBytesThroughput() {
        return this.writtenBytesThroughput;
    }

    public final double getReadMessagesThroughput() {
        return this.readMessagesThroughput;
    }

    public final double getWrittenMessagesThroughput() {
        return this.writtenMessagesThroughput;
    }

    public final void updateThroughput(long j, boolean z) {
        long j2 = j;
        int i = (int) (j2 - this.lastThroughputCalculationTime);
        long throughputCalculationIntervalInMillis = getConfig().getThroughputCalculationIntervalInMillis();
        if ((throughputCalculationIntervalInMillis != 0 && ((long) i) >= throughputCalculationIntervalInMillis) || z) {
            long j3 = this.readBytes;
            double d = (double) i;
            this.readBytesThroughput = (((double) (j3 - this.lastReadBytes)) * 1000.0d) / d;
            long j4 = this.writtenBytes;
            this.writtenBytesThroughput = (((double) (j4 - this.lastWrittenBytes)) * 1000.0d) / d;
            long j5 = this.readMessages;
            this.readMessagesThroughput = (((double) (j5 - this.lastReadMessages)) * 1000.0d) / d;
            long j6 = this.writtenMessages;
            this.writtenMessagesThroughput = (((double) (j6 - this.lastWrittenMessages)) * 1000.0d) / d;
            this.lastReadBytes = j3;
            this.lastWrittenBytes = j4;
            this.lastReadMessages = j5;
            this.lastWrittenMessages = j6;
            this.lastThroughputCalculationTime = j2;
        }
    }

    public final long getScheduledWriteBytes() {
        return (long) this.scheduledWriteBytes.get();
    }

    public final int getScheduledWriteMessages() {
        return this.scheduledWriteMessages.get();
    }

    /* access modifiers changed from: protected */
    public void setScheduledWriteBytes(int i) {
        this.scheduledWriteBytes.set(i);
    }

    /* access modifiers changed from: protected */
    public void setScheduledWriteMessages(int i) {
        this.scheduledWriteMessages.set(i);
    }

    public final void increaseReadBytes(long j, long j2) {
        if (j > 0) {
            this.readBytes += j;
            this.lastReadTime = j2;
            this.idleCountForBoth.set(0);
            this.idleCountForRead.set(0);
            if (getService() instanceof AbstractIoService) {
                ((AbstractIoService) getService()).getStatistics().increaseReadBytes(j, j2);
            }
        }
    }

    public final void increaseReadMessages(long j) {
        this.readMessages++;
        this.lastReadTime = j;
        this.idleCountForBoth.set(0);
        this.idleCountForRead.set(0);
        if (getService() instanceof AbstractIoService) {
            ((AbstractIoService) getService()).getStatistics().increaseReadMessages(j);
        }
    }

    public final void increaseWrittenBytes(int i, long j) {
        if (i > 0) {
            this.writtenBytes += (long) i;
            this.lastWriteTime = j;
            this.idleCountForBoth.set(0);
            this.idleCountForWrite.set(0);
            if (getService() instanceof AbstractIoService) {
                ((AbstractIoService) getService()).getStatistics().increaseWrittenBytes(i, j);
            }
            increaseScheduledWriteBytes(-i);
        }
    }

    public final void increaseWrittenMessages(WriteRequest writeRequest, long j) {
        Object message = writeRequest.getMessage();
        if (!(message instanceof IoBuffer) || !((IoBuffer) message).hasRemaining()) {
            this.writtenMessages++;
            this.lastWriteTime = j;
            if (getService() instanceof AbstractIoService) {
                ((AbstractIoService) getService()).getStatistics().increaseWrittenMessages(j);
            }
            decreaseScheduledWriteMessages();
        }
    }

    public final void increaseScheduledWriteBytes(int i) {
        this.scheduledWriteBytes.addAndGet(i);
        if (getService() instanceof AbstractIoService) {
            ((AbstractIoService) getService()).getStatistics().increaseScheduledWriteBytes(i);
        }
    }

    public final void increaseScheduledWriteMessages() {
        this.scheduledWriteMessages.incrementAndGet();
        if (getService() instanceof AbstractIoService) {
            ((AbstractIoService) getService()).getStatistics().increaseScheduledWriteMessages();
        }
    }

    private void decreaseScheduledWriteMessages() {
        this.scheduledWriteMessages.decrementAndGet();
        if (getService() instanceof AbstractIoService) {
            ((AbstractIoService) getService()).getStatistics().decreaseScheduledWriteMessages();
        }
    }

    public final void decreaseScheduledBytesAndMessages(WriteRequest writeRequest) {
        Object message = writeRequest.getMessage();
        if (message instanceof IoBuffer) {
            IoBuffer ioBuffer = (IoBuffer) message;
            if (ioBuffer.hasRemaining()) {
                increaseScheduledWriteBytes(-ioBuffer.remaining());
            } else {
                decreaseScheduledWriteMessages();
            }
        } else {
            decreaseScheduledWriteMessages();
        }
    }

    public final WriteRequestQueue getWriteRequestQueue() {
        WriteRequestQueue writeRequestQueue2 = this.writeRequestQueue;
        if (writeRequestQueue2 != null) {
            return writeRequestQueue2;
        }
        throw new IllegalStateException();
    }

    public final WriteRequest getCurrentWriteRequest() {
        return this.currentWriteRequest;
    }

    public final Object getCurrentWriteMessage() {
        WriteRequest currentWriteRequest2 = getCurrentWriteRequest();
        if (currentWriteRequest2 == null) {
            return null;
        }
        return currentWriteRequest2.getMessage();
    }

    public final void setCurrentWriteRequest(WriteRequest writeRequest) {
        this.currentWriteRequest = writeRequest;
    }

    public final void increaseReadBufferSize() {
        int readBufferSize = getConfig().getReadBufferSize() << 1;
        if (readBufferSize <= getConfig().getMaxReadBufferSize()) {
            getConfig().setReadBufferSize(readBufferSize);
        } else {
            getConfig().setReadBufferSize(getConfig().getMaxReadBufferSize());
        }
        this.deferDecreaseReadBuffer = true;
    }

    public final void decreaseReadBufferSize() {
        if (this.deferDecreaseReadBuffer) {
            this.deferDecreaseReadBuffer = false;
            return;
        }
        if (getConfig().getReadBufferSize() > getConfig().getMinReadBufferSize()) {
            getConfig().setReadBufferSize(getConfig().getReadBufferSize() >>> 1);
        }
        this.deferDecreaseReadBuffer = true;
    }

    public final long getCreationTime() {
        return this.creationTime;
    }

    public final long getLastIoTime() {
        return Math.max(this.lastReadTime, this.lastWriteTime);
    }

    public final long getLastReadTime() {
        return this.lastReadTime;
    }

    public final long getLastWriteTime() {
        return this.lastWriteTime;
    }

    public final boolean isIdle(IdleStatus idleStatus) {
        if (idleStatus == IdleStatus.BOTH_IDLE) {
            if (this.idleCountForBoth.get() > 0) {
                return true;
            }
            return false;
        } else if (idleStatus == IdleStatus.READER_IDLE) {
            if (this.idleCountForRead.get() > 0) {
                return true;
            }
            return false;
        } else if (idleStatus != IdleStatus.WRITER_IDLE) {
            throw new IllegalArgumentException("Unknown idle status: " + idleStatus);
        } else if (this.idleCountForWrite.get() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public final boolean isBothIdle() {
        return isIdle(IdleStatus.BOTH_IDLE);
    }

    public final boolean isReaderIdle() {
        return isIdle(IdleStatus.READER_IDLE);
    }

    public final boolean isWriterIdle() {
        return isIdle(IdleStatus.WRITER_IDLE);
    }

    public final int getIdleCount(IdleStatus idleStatus) {
        if (getConfig().getIdleTime(idleStatus) == 0) {
            if (idleStatus == IdleStatus.BOTH_IDLE) {
                this.idleCountForBoth.set(0);
            }
            if (idleStatus == IdleStatus.READER_IDLE) {
                this.idleCountForRead.set(0);
            }
            if (idleStatus == IdleStatus.WRITER_IDLE) {
                this.idleCountForWrite.set(0);
            }
        }
        if (idleStatus == IdleStatus.BOTH_IDLE) {
            return this.idleCountForBoth.get();
        }
        if (idleStatus == IdleStatus.READER_IDLE) {
            return this.idleCountForRead.get();
        }
        if (idleStatus == IdleStatus.WRITER_IDLE) {
            return this.idleCountForWrite.get();
        }
        throw new IllegalArgumentException("Unknown idle status: " + idleStatus);
    }

    public final long getLastIdleTime(IdleStatus idleStatus) {
        if (idleStatus == IdleStatus.BOTH_IDLE) {
            return this.lastIdleTimeForBoth;
        }
        if (idleStatus == IdleStatus.READER_IDLE) {
            return this.lastIdleTimeForRead;
        }
        if (idleStatus == IdleStatus.WRITER_IDLE) {
            return this.lastIdleTimeForWrite;
        }
        throw new IllegalArgumentException("Unknown idle status: " + idleStatus);
    }

    public final void increaseIdleCount(IdleStatus idleStatus, long j) {
        if (idleStatus == IdleStatus.BOTH_IDLE) {
            this.idleCountForBoth.incrementAndGet();
            this.lastIdleTimeForBoth = j;
        } else if (idleStatus == IdleStatus.READER_IDLE) {
            this.idleCountForRead.incrementAndGet();
            this.lastIdleTimeForRead = j;
        } else if (idleStatus == IdleStatus.WRITER_IDLE) {
            this.idleCountForWrite.incrementAndGet();
            this.lastIdleTimeForWrite = j;
        } else {
            throw new IllegalArgumentException("Unknown idle status: " + idleStatus);
        }
    }

    public final int getBothIdleCount() {
        return getIdleCount(IdleStatus.BOTH_IDLE);
    }

    public final long getLastBothIdleTime() {
        return getLastIdleTime(IdleStatus.BOTH_IDLE);
    }

    public final long getLastReaderIdleTime() {
        return getLastIdleTime(IdleStatus.READER_IDLE);
    }

    public final long getLastWriterIdleTime() {
        return getLastIdleTime(IdleStatus.WRITER_IDLE);
    }

    public final int getReaderIdleCount() {
        return getIdleCount(IdleStatus.READER_IDLE);
    }

    public final int getWriterIdleCount() {
        return getIdleCount(IdleStatus.WRITER_IDLE);
    }

    public SocketAddress getServiceAddress() {
        IoService service2 = getService();
        if (service2 instanceof IoAcceptor) {
            return ((IoAcceptor) service2).getLocalAddress();
        }
        return getRemoteAddress();
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public String toString() {
        String str;
        String str2;
        if (isConnected() || isClosing()) {
            try {
                str = String.valueOf(getRemoteAddress());
            } catch (Throwable th) {
                str = "Cannot get the remote address informations: " + th.getMessage();
            }
            try {
                str2 = String.valueOf(getLocalAddress());
            } catch (Throwable th2) {
                str2 = "Cannot get the local address informations: " + th2.getMessage();
            }
            if (getService() instanceof IoAcceptor) {
                return "(" + getIdAsString() + ": " + getServiceName() + ", server, " + str + " => " + str2 + ')';
            }
            return "(" + getIdAsString() + ": " + getServiceName() + ", client, " + str2 + " => " + str + ')';
        }
        return "(" + getIdAsString() + ") Session disconnected ...";
    }

    private String getIdAsString() {
        String upperCase = Long.toHexString(getId()).toUpperCase();
        while (upperCase.length() < 8) {
            upperCase = '0' + upperCase;
        }
        return "0x" + upperCase;
    }

    private String getServiceName() {
        TransportMetadata transportMetadata = getTransportMetadata();
        if (transportMetadata == null) {
            return Configurator.NULL;
        }
        return transportMetadata.getProviderName() + ' ' + transportMetadata.getName();
    }

    public IoService getService() {
        return this.service;
    }

    public static void notifyIdleness(Iterator<? extends IoSession> it, long j) {
        while (it.hasNext()) {
            notifyIdleSession((IoSession) it.next(), j);
        }
    }

    public static void notifyIdleSession(IoSession ioSession, long j) {
        notifyIdleSession0(ioSession, j, ioSession.getConfig().getIdleTimeInMillis(IdleStatus.BOTH_IDLE), IdleStatus.BOTH_IDLE, Math.max(ioSession.getLastIoTime(), ioSession.getLastIdleTime(IdleStatus.BOTH_IDLE)));
        notifyIdleSession0(ioSession, j, ioSession.getConfig().getIdleTimeInMillis(IdleStatus.READER_IDLE), IdleStatus.READER_IDLE, Math.max(ioSession.getLastReadTime(), ioSession.getLastIdleTime(IdleStatus.READER_IDLE)));
        notifyIdleSession0(ioSession, j, ioSession.getConfig().getIdleTimeInMillis(IdleStatus.WRITER_IDLE), IdleStatus.WRITER_IDLE, Math.max(ioSession.getLastWriteTime(), ioSession.getLastIdleTime(IdleStatus.WRITER_IDLE)));
        notifyWriteTimeout(ioSession, j);
    }

    private static void notifyIdleSession0(IoSession ioSession, long j, long j2, IdleStatus idleStatus, long j3) {
        if (j2 > 0 && j3 != 0 && j - j3 >= j2) {
            ioSession.getFilterChain().fireSessionIdle(idleStatus);
        }
    }

    private static void notifyWriteTimeout(IoSession ioSession, long j) {
        WriteRequest currentWriteRequest2;
        long writeTimeoutInMillis = ioSession.getConfig().getWriteTimeoutInMillis();
        if (writeTimeoutInMillis > 0 && j - ioSession.getLastWriteTime() >= writeTimeoutInMillis && !ioSession.getWriteRequestQueue().isEmpty(ioSession) && (currentWriteRequest2 = ioSession.getCurrentWriteRequest()) != null) {
            ioSession.setCurrentWriteRequest((WriteRequest) null);
            WriteTimeoutException writeTimeoutException = new WriteTimeoutException(currentWriteRequest2);
            currentWriteRequest2.getFuture().setException(writeTimeoutException);
            ioSession.getFilterChain().fireExceptionCaught(writeTimeoutException);
            ioSession.close(true);
        }
    }

    private class CloseAwareWriteQueue implements WriteRequestQueue {
        private final WriteRequestQueue queue;

        public CloseAwareWriteQueue(WriteRequestQueue writeRequestQueue) {
            this.queue = writeRequestQueue;
        }

        public synchronized WriteRequest poll(IoSession ioSession) {
            WriteRequest poll;
            poll = this.queue.poll(ioSession);
            if (poll == AbstractIoSession.CLOSE_REQUEST) {
                AbstractIoSession.this.close();
                dispose(ioSession);
                poll = null;
            }
            return poll;
        }

        public void offer(IoSession ioSession, WriteRequest writeRequest) {
            this.queue.offer(ioSession, writeRequest);
        }

        public boolean isEmpty(IoSession ioSession) {
            return this.queue.isEmpty(ioSession);
        }

        public void clear(IoSession ioSession) {
            this.queue.clear(ioSession);
        }

        public void dispose(IoSession ioSession) {
            this.queue.dispose(ioSession);
        }
    }
}
