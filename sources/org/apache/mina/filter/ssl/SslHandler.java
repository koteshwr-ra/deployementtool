package org.apache.mina.filter.ssl;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterEvent;
import org.apache.mina.core.future.DefaultWriteFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoEventType;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.DefaultWriteRequest;
import org.apache.mina.core.write.WriteRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SslHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SslHandler.class);
    private IoBuffer appBuffer;
    private final IoBuffer emptyBuffer = IoBuffer.allocate(0);
    private final Queue<IoFilterEvent> filterWriteEventQueue = new ConcurrentLinkedQueue();
    private boolean firstSSLNegociation;
    private boolean handshakeComplete;
    private SSLEngineResult.HandshakeStatus handshakeStatus;
    private IoBuffer inNetBuffer;
    private final Queue<IoFilterEvent> messageReceivedEventQueue = new ConcurrentLinkedQueue();
    private IoBuffer outNetBuffer;
    private final Queue<IoFilterEvent> preHandshakeEventQueue = new ConcurrentLinkedQueue();
    private final IoSession session;
    private SSLEngine sslEngine;
    private final SslFilter sslFilter;
    private boolean writingEncryptedData;

    SslHandler(SslFilter sslFilter2, IoSession ioSession) throws SSLException {
        this.sslFilter = sslFilter2;
        this.session = ioSession;
    }

    /* access modifiers changed from: package-private */
    public void init() throws SSLException {
        if (this.sslEngine == null) {
            LOGGER.debug("{} Initializing the SSL Handler", (Object) this.sslFilter.getSessionInfo(this.session));
            InetSocketAddress inetSocketAddress = (InetSocketAddress) this.session.getAttribute(SslFilter.PEER_ADDRESS);
            if (inetSocketAddress == null) {
                this.sslEngine = this.sslFilter.sslContext.createSSLEngine();
            } else {
                this.sslEngine = this.sslFilter.sslContext.createSSLEngine(inetSocketAddress.getHostName(), inetSocketAddress.getPort());
            }
            this.sslEngine.setUseClientMode(this.sslFilter.isUseClientMode());
            if (!this.sslEngine.getUseClientMode()) {
                if (this.sslFilter.isWantClientAuth()) {
                    this.sslEngine.setWantClientAuth(true);
                }
                if (this.sslFilter.isNeedClientAuth()) {
                    this.sslEngine.setNeedClientAuth(true);
                }
            }
            if (this.sslFilter.getEnabledCipherSuites() != null) {
                this.sslEngine.setEnabledCipherSuites(this.sslFilter.getEnabledCipherSuites());
            }
            if (this.sslFilter.getEnabledProtocols() != null) {
                this.sslEngine.setEnabledProtocols(this.sslFilter.getEnabledProtocols());
            }
            this.sslEngine.beginHandshake();
            this.handshakeStatus = this.sslEngine.getHandshakeStatus();
            this.writingEncryptedData = false;
            this.firstSSLNegociation = true;
            this.handshakeComplete = false;
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{} SSL Handler Initialization done.", (Object) this.sslFilter.getSessionInfo(this.session));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void destroy() {
        SSLEngine sSLEngine = this.sslEngine;
        if (sSLEngine != null) {
            try {
                sSLEngine.closeInbound();
            } catch (SSLException e) {
                LOGGER.debug("Unexpected exception from SSLEngine.closeInbound().", (Throwable) e);
            }
            IoBuffer ioBuffer = this.outNetBuffer;
            if (ioBuffer != null) {
                ioBuffer.capacity(this.sslEngine.getSession().getPacketBufferSize());
            } else {
                createOutNetBuffer(0);
            }
            do {
                try {
                    this.outNetBuffer.clear();
                } catch (SSLException unused) {
                } catch (Throwable th) {
                    destroyOutNetBuffer();
                    throw th;
                }
            } while (this.sslEngine.wrap(this.emptyBuffer.buf(), this.outNetBuffer.buf()).bytesProduced() > 0);
            destroyOutNetBuffer();
            this.sslEngine.closeOutbound();
            this.sslEngine = null;
            this.preHandshakeEventQueue.clear();
        }
    }

    private void destroyOutNetBuffer() {
        this.outNetBuffer.free();
        this.outNetBuffer = null;
    }

    /* access modifiers changed from: package-private */
    public SslFilter getSslFilter() {
        return this.sslFilter;
    }

    /* access modifiers changed from: package-private */
    public IoSession getSession() {
        return this.session;
    }

    /* access modifiers changed from: package-private */
    public boolean isWritingEncryptedData() {
        return this.writingEncryptedData;
    }

    /* access modifiers changed from: package-private */
    public boolean isHandshakeComplete() {
        return this.handshakeComplete;
    }

    /* access modifiers changed from: package-private */
    public boolean isInboundDone() {
        SSLEngine sSLEngine = this.sslEngine;
        return sSLEngine == null || sSLEngine.isInboundDone();
    }

    /* access modifiers changed from: package-private */
    public boolean isOutboundDone() {
        SSLEngine sSLEngine = this.sslEngine;
        return sSLEngine == null || sSLEngine.isOutboundDone();
    }

    /* access modifiers changed from: package-private */
    public boolean needToCompleteHandshake() {
        return this.handshakeStatus == SSLEngineResult.HandshakeStatus.NEED_WRAP && !isInboundDone();
    }

    /* access modifiers changed from: package-private */
    public void schedulePreHandshakeWriteRequest(IoFilter.NextFilter nextFilter, WriteRequest writeRequest) {
        this.preHandshakeEventQueue.add(new IoFilterEvent(nextFilter, IoEventType.WRITE, this.session, writeRequest));
    }

    /* access modifiers changed from: package-private */
    public void flushPreHandshakeEvents() throws SSLException {
        while (true) {
            IoFilterEvent poll = this.preHandshakeEventQueue.poll();
            if (poll != null) {
                this.sslFilter.filterWrite(poll.getNextFilter(), this.session, (WriteRequest) poll.getParameter());
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void scheduleFilterWrite(IoFilter.NextFilter nextFilter, WriteRequest writeRequest) {
        this.filterWriteEventQueue.add(new IoFilterEvent(nextFilter, IoEventType.WRITE, this.session, writeRequest));
    }

    /* access modifiers changed from: package-private */
    public void scheduleMessageReceived(IoFilter.NextFilter nextFilter, Object obj) {
        this.messageReceivedEventQueue.add(new IoFilterEvent(nextFilter, IoEventType.MESSAGE_RECEIVED, this.session, obj));
    }

    /* access modifiers changed from: package-private */
    public void flushScheduledEvents() {
        if (!Thread.holdsLock(this)) {
            synchronized (this) {
                while (true) {
                    IoFilterEvent poll = this.filterWriteEventQueue.poll();
                    if (poll != null) {
                        poll.getNextFilter().filterWrite(this.session, (WriteRequest) poll.getParameter());
                    }
                }
                while (true) {
                }
            }
            while (true) {
                IoFilterEvent poll2 = this.messageReceivedEventQueue.poll();
                if (poll2 != null) {
                    poll2.getNextFilter().messageReceived(this.session, poll2.getParameter());
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void messageReceived(IoFilter.NextFilter nextFilter, ByteBuffer byteBuffer) throws SSLException {
        if (LOGGER.isDebugEnabled()) {
            if (!isOutboundDone()) {
                LOGGER.debug("{} Processing the received message", (Object) this.sslFilter.getSessionInfo(this.session));
            } else {
                LOGGER.debug("{} Processing the received message", (Object) this.sslFilter.getSessionInfo(this.session));
            }
        }
        if (this.inNetBuffer == null) {
            this.inNetBuffer = IoBuffer.allocate(byteBuffer.remaining()).setAutoExpand(true);
        }
        this.inNetBuffer.put(byteBuffer);
        if (!this.handshakeComplete) {
            handshake(nextFilter);
        } else {
            this.inNetBuffer.flip();
            if (this.inNetBuffer.hasRemaining()) {
                SSLEngineResult unwrap = unwrap();
                if (this.inNetBuffer.hasRemaining()) {
                    this.inNetBuffer.compact();
                } else {
                    this.inNetBuffer = null;
                }
                checkStatus(unwrap);
                renegotiateIfNeeded(nextFilter, unwrap);
            } else {
                return;
            }
        }
        if (isInboundDone()) {
            IoBuffer ioBuffer = this.inNetBuffer;
            byteBuffer.position(byteBuffer.position() - (ioBuffer == null ? 0 : ioBuffer.position()));
            this.inNetBuffer = null;
        }
    }

    /* access modifiers changed from: package-private */
    public IoBuffer fetchAppBuffer() {
        IoBuffer flip = this.appBuffer.flip();
        this.appBuffer = null;
        return flip;
    }

    /* access modifiers changed from: package-private */
    public IoBuffer fetchOutNetBuffer() {
        IoBuffer ioBuffer = this.outNetBuffer;
        if (ioBuffer == null) {
            return this.emptyBuffer;
        }
        this.outNetBuffer = null;
        return ioBuffer.shrink();
    }

    /* access modifiers changed from: package-private */
    public void encrypt(ByteBuffer byteBuffer) throws SSLException {
        if (!this.handshakeComplete) {
            throw new IllegalStateException();
        } else if (byteBuffer.hasRemaining()) {
            createOutNetBuffer(byteBuffer.remaining());
            while (byteBuffer.hasRemaining()) {
                SSLEngineResult wrap = this.sslEngine.wrap(byteBuffer, this.outNetBuffer.buf());
                if (wrap.getStatus() == SSLEngineResult.Status.OK) {
                    if (wrap.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_TASK) {
                        doTasks();
                    }
                } else if (wrap.getStatus() == SSLEngineResult.Status.BUFFER_OVERFLOW) {
                    IoBuffer ioBuffer = this.outNetBuffer;
                    ioBuffer.capacity(ioBuffer.capacity() << 1);
                    IoBuffer ioBuffer2 = this.outNetBuffer;
                    ioBuffer2.limit(ioBuffer2.capacity());
                } else {
                    throw new SSLException("SSLEngine error during encrypt: " + wrap.getStatus() + " src: " + byteBuffer + "outNetBuffer: " + this.outNetBuffer);
                }
            }
            this.outNetBuffer.flip();
        } else if (this.outNetBuffer == null) {
            this.outNetBuffer = this.emptyBuffer;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean closeOutbound() throws SSLException {
        SSLEngineResult wrap;
        SSLEngine sSLEngine = this.sslEngine;
        if (sSLEngine == null || sSLEngine.isOutboundDone()) {
            return false;
        }
        this.sslEngine.closeOutbound();
        createOutNetBuffer(0);
        while (true) {
            wrap = this.sslEngine.wrap(this.emptyBuffer.buf(), this.outNetBuffer.buf());
            if (wrap.getStatus() != SSLEngineResult.Status.BUFFER_OVERFLOW) {
                break;
            }
            IoBuffer ioBuffer = this.outNetBuffer;
            ioBuffer.capacity(ioBuffer.capacity() << 1);
            IoBuffer ioBuffer2 = this.outNetBuffer;
            ioBuffer2.limit(ioBuffer2.capacity());
        }
        if (wrap.getStatus() == SSLEngineResult.Status.CLOSED) {
            this.outNetBuffer.flip();
            return true;
        }
        throw new SSLException("Improper close state: " + wrap);
    }

    private void checkStatus(SSLEngineResult sSLEngineResult) throws SSLException {
        SSLEngineResult.Status status = sSLEngineResult.getStatus();
        if (status == SSLEngineResult.Status.BUFFER_OVERFLOW) {
            throw new SSLException("SSLEngine error during decrypt: " + status + " inNetBuffer: " + this.inNetBuffer + "appBuffer: " + this.appBuffer);
        }
    }

    /* renamed from: org.apache.mina.filter.ssl.SslHandler$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                javax.net.ssl.SSLEngineResult$HandshakeStatus[] r0 = javax.net.ssl.SSLEngineResult.HandshakeStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus = r0
                javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.FINISHED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus     // Catch:{ NoSuchFieldError -> 0x001d }
                javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_TASK     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus     // Catch:{ NoSuchFieldError -> 0x0028 }
                javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_UNWRAP     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus     // Catch:{ NoSuchFieldError -> 0x0033 }
                javax.net.ssl.SSLEngineResult$HandshakeStatus r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_WRAP     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.filter.ssl.SslHandler.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void handshake(IoFilter.NextFilter nextFilter) throws SSLException {
        SSLEngineResult wrap;
        while (true) {
            int i = AnonymousClass1.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[this.handshakeStatus.ordinal()];
            if (i == 1) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{} processing the FINISHED state", (Object) this.sslFilter.getSessionInfo(this.session));
                }
                this.session.setAttribute(SslFilter.SSL_SESSION, this.sslEngine.getSession());
                this.handshakeComplete = true;
                if (this.firstSSLNegociation && this.session.containsAttribute(SslFilter.USE_NOTIFICATION)) {
                    this.firstSSLNegociation = false;
                    scheduleMessageReceived(nextFilter, SslFilter.SESSION_SECURED);
                }
                if (!LOGGER.isDebugEnabled()) {
                    return;
                }
                if (!isOutboundDone()) {
                    LOGGER.debug("{} is now secured", (Object) this.sslFilter.getSessionInfo(this.session));
                    return;
                } else {
                    LOGGER.debug("{} is not secured yet", (Object) this.sslFilter.getSessionInfo(this.session));
                    return;
                }
            } else if (i == 2) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{} processing the NEED_TASK state", (Object) this.sslFilter.getSessionInfo(this.session));
                }
                this.handshakeStatus = doTasks();
            } else if (i == 3) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{} processing the NEED_UNWRAP state", (Object) this.sslFilter.getSessionInfo(this.session));
                }
                if ((unwrapHandshake(nextFilter) == SSLEngineResult.Status.BUFFER_UNDERFLOW && this.handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED) || isInboundDone()) {
                    return;
                }
            } else if (i == 4) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{} processing the NEED_WRAP state", (Object) this.sslFilter.getSessionInfo(this.session));
                }
                IoBuffer ioBuffer = this.outNetBuffer;
                if (ioBuffer == null || !ioBuffer.hasRemaining()) {
                    createOutNetBuffer(0);
                    while (true) {
                        wrap = this.sslEngine.wrap(this.emptyBuffer.buf(), this.outNetBuffer.buf());
                        if (wrap.getStatus() != SSLEngineResult.Status.BUFFER_OVERFLOW) {
                            break;
                        }
                        IoBuffer ioBuffer2 = this.outNetBuffer;
                        ioBuffer2.capacity(ioBuffer2.capacity() << 1);
                        IoBuffer ioBuffer3 = this.outNetBuffer;
                        ioBuffer3.limit(ioBuffer3.capacity());
                    }
                    this.outNetBuffer.flip();
                    this.handshakeStatus = wrap.getHandshakeStatus();
                    writeNetBuffer(nextFilter);
                } else {
                    return;
                }
            } else {
                String str = "Invalid Handshaking State" + this.handshakeStatus + " while processing the Handshake for session " + this.session.getId();
                LOGGER.error(str);
                throw new IllegalStateException(str);
            }
        }
    }

    private void createOutNetBuffer(int i) {
        int max = Math.max(i, this.sslEngine.getSession().getPacketBufferSize());
        IoBuffer ioBuffer = this.outNetBuffer;
        if (ioBuffer != null) {
            ioBuffer.capacity(max);
        } else {
            this.outNetBuffer = IoBuffer.allocate(max).minimumCapacity(0);
        }
    }

    /* access modifiers changed from: package-private */
    public WriteFuture writeNetBuffer(IoFilter.NextFilter nextFilter) throws SSLException {
        IoBuffer ioBuffer = this.outNetBuffer;
        if (ioBuffer == null || !ioBuffer.hasRemaining()) {
            return null;
        }
        this.writingEncryptedData = true;
        try {
            IoBuffer fetchOutNetBuffer = fetchOutNetBuffer();
            DefaultWriteFuture defaultWriteFuture = new DefaultWriteFuture(this.session);
            this.sslFilter.filterWrite(nextFilter, this.session, new DefaultWriteRequest(fetchOutNetBuffer, defaultWriteFuture));
            while (needToCompleteHandshake()) {
                handshake(nextFilter);
                IoBuffer fetchOutNetBuffer2 = fetchOutNetBuffer();
                if (fetchOutNetBuffer2 != null && fetchOutNetBuffer2.hasRemaining()) {
                    defaultWriteFuture = new DefaultWriteFuture(this.session);
                    this.sslFilter.filterWrite(nextFilter, this.session, new DefaultWriteRequest(fetchOutNetBuffer2, defaultWriteFuture));
                }
            }
            this.writingEncryptedData = false;
            return defaultWriteFuture;
        } catch (SSLException e) {
            SSLHandshakeException sSLHandshakeException = new SSLHandshakeException("SSL handshake failed.");
            sSLHandshakeException.initCause(e);
            throw sSLHandshakeException;
        } catch (Throwable th) {
            this.writingEncryptedData = false;
            throw th;
        }
    }

    private SSLEngineResult.Status unwrapHandshake(IoFilter.NextFilter nextFilter) throws SSLException {
        IoBuffer ioBuffer = this.inNetBuffer;
        if (ioBuffer != null) {
            ioBuffer.flip();
        }
        IoBuffer ioBuffer2 = this.inNetBuffer;
        if (ioBuffer2 == null || !ioBuffer2.hasRemaining()) {
            return SSLEngineResult.Status.BUFFER_UNDERFLOW;
        }
        SSLEngineResult unwrap = unwrap();
        this.handshakeStatus = unwrap.getHandshakeStatus();
        checkStatus(unwrap);
        if (this.handshakeStatus == SSLEngineResult.HandshakeStatus.FINISHED && unwrap.getStatus() == SSLEngineResult.Status.OK && this.inNetBuffer.hasRemaining()) {
            unwrap = unwrap();
            if (this.inNetBuffer.hasRemaining()) {
                this.inNetBuffer.compact();
            } else {
                this.inNetBuffer = null;
            }
            renegotiateIfNeeded(nextFilter, unwrap);
        } else if (this.inNetBuffer.hasRemaining()) {
            this.inNetBuffer.compact();
        } else {
            this.inNetBuffer = null;
        }
        return unwrap.getStatus();
    }

    private void renegotiateIfNeeded(IoFilter.NextFilter nextFilter, SSLEngineResult sSLEngineResult) throws SSLException {
        if (sSLEngineResult.getStatus() != SSLEngineResult.Status.CLOSED && sSLEngineResult.getStatus() != SSLEngineResult.Status.BUFFER_UNDERFLOW && sSLEngineResult.getHandshakeStatus() != SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
            this.handshakeComplete = false;
            this.handshakeStatus = sSLEngineResult.getHandshakeStatus();
            handshake(nextFilter);
        }
    }

    private SSLEngineResult unwrap() throws SSLException {
        SSLEngineResult unwrap;
        IoBuffer ioBuffer = this.appBuffer;
        if (ioBuffer == null) {
            this.appBuffer = IoBuffer.allocate(this.inNetBuffer.remaining());
        } else {
            ioBuffer.expand(this.inNetBuffer.remaining());
        }
        while (true) {
            unwrap = this.sslEngine.unwrap(this.inNetBuffer.buf(), this.appBuffer.buf());
            SSLEngineResult.Status status = unwrap.getStatus();
            SSLEngineResult.HandshakeStatus handshakeStatus2 = unwrap.getHandshakeStatus();
            if (status == SSLEngineResult.Status.BUFFER_OVERFLOW) {
                IoBuffer ioBuffer2 = this.appBuffer;
                ioBuffer2.capacity(ioBuffer2.capacity() << 1);
                IoBuffer ioBuffer3 = this.appBuffer;
                ioBuffer3.limit(ioBuffer3.capacity());
            }
            if ((status == SSLEngineResult.Status.OK || status == SSLEngineResult.Status.BUFFER_OVERFLOW) && (handshakeStatus2 == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING || handshakeStatus2 == SSLEngineResult.HandshakeStatus.NEED_UNWRAP)) {
            }
        }
        return unwrap;
    }

    private SSLEngineResult.HandshakeStatus doTasks() {
        while (true) {
            Runnable delegatedTask = this.sslEngine.getDelegatedTask();
            if (delegatedTask == null) {
                return this.sslEngine.getHandshakeStatus();
            }
            delegatedTask.run();
        }
    }

    static IoBuffer copy(ByteBuffer byteBuffer) {
        IoBuffer allocate = IoBuffer.allocate(byteBuffer.remaining());
        allocate.put(byteBuffer);
        allocate.flip();
        return allocate;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SSLStatus <");
        if (this.handshakeComplete) {
            sb.append("SSL established");
        } else {
            sb.append("Processing Handshake");
            sb.append("; ");
            sb.append("Status : ");
            sb.append(this.handshakeStatus);
            sb.append("; ");
        }
        sb.append(", ");
        sb.append("HandshakeComplete :");
        sb.append(this.handshakeComplete);
        sb.append(", ");
        sb.append(">");
        return sb.toString();
    }
}
