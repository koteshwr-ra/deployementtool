package org.apache.mina.filter.ssl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.future.DefaultWriteFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.core.write.WriteRequestWrapper;
import org.apache.mina.core.write.WriteToClosedSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SslFilter extends IoFilterAdapter {
    public static final AttributeKey DISABLE_ENCRYPTION_ONCE;
    private static final Logger LOGGER;
    private static final AttributeKey NEXT_FILTER;
    public static final AttributeKey PEER_ADDRESS;
    public static final SslFilterMessage SESSION_SECURED = new SslFilterMessage("SESSION_SECURED");
    public static final SslFilterMessage SESSION_UNSECURED = new SslFilterMessage("SESSION_UNSECURED");
    private static final AttributeKey SSL_HANDLER;
    public static final AttributeKey SSL_SESSION;
    private static final boolean START_HANDSHAKE = true;
    public static final AttributeKey USE_NOTIFICATION;
    private final boolean autoStart;
    private boolean client;
    private String[] enabledCipherSuites;
    private String[] enabledProtocols;
    private boolean needClientAuth;
    final SSLContext sslContext;
    private boolean wantClientAuth;

    static {
        Class<SslFilter> cls = SslFilter.class;
        LOGGER = LoggerFactory.getLogger((Class) cls);
        SSL_SESSION = new AttributeKey(cls, "session");
        DISABLE_ENCRYPTION_ONCE = new AttributeKey(cls, "disableOnce");
        USE_NOTIFICATION = new AttributeKey(cls, "useNotification");
        PEER_ADDRESS = new AttributeKey(cls, "peerAddress");
        NEXT_FILTER = new AttributeKey(cls, "nextFilter");
        SSL_HANDLER = new AttributeKey(cls, "handler");
    }

    public SslFilter(SSLContext sSLContext) {
        this(sSLContext, true);
    }

    public SslFilter(SSLContext sSLContext, boolean z) {
        if (sSLContext != null) {
            this.sslContext = sSLContext;
            this.autoStart = z;
            return;
        }
        throw new IllegalArgumentException("sslContext");
    }

    public SSLSession getSslSession(IoSession ioSession) {
        return (SSLSession) ioSession.getAttribute(SSL_SESSION);
    }

    public boolean startSsl(IoSession ioSession) throws SSLException {
        boolean z;
        SslHandler sslSessionHandler = getSslSessionHandler(ioSession);
        synchronized (sslSessionHandler) {
            if (sslSessionHandler.isOutboundDone()) {
                sslSessionHandler.destroy();
                sslSessionHandler.init();
                sslSessionHandler.handshake((IoFilter.NextFilter) ioSession.getAttribute(NEXT_FILTER));
                z = true;
            } else {
                z = false;
            }
        }
        sslSessionHandler.flushScheduledEvents();
        return z;
    }

    /* access modifiers changed from: package-private */
    public String getSessionInfo(IoSession ioSession) {
        StringBuilder sb = new StringBuilder();
        if (ioSession.getService() instanceof IoAcceptor) {
            sb.append("Session Server");
        } else {
            sb.append("Session Client");
        }
        sb.append('[');
        sb.append(ioSession.getId());
        sb.append(']');
        SslHandler sslHandler = (SslHandler) ioSession.getAttribute(SSL_HANDLER);
        if (sslHandler == null) {
            sb.append("(no sslEngine)");
        } else if (isSslStarted(ioSession)) {
            if (sslHandler.isHandshakeComplete()) {
                sb.append("(SSL)");
            } else {
                sb.append("(ssl...)");
            }
        }
        return sb.toString();
    }

    public boolean isSslStarted(IoSession ioSession) {
        SslHandler sslHandler = (SslHandler) ioSession.getAttribute(SSL_HANDLER);
        boolean z = false;
        if (sslHandler == null) {
            return false;
        }
        synchronized (sslHandler) {
            if (!sslHandler.isOutboundDone()) {
                z = true;
            }
        }
        return z;
    }

    public WriteFuture stopSsl(IoSession ioSession) throws SSLException {
        WriteFuture initiateClosure;
        SslHandler sslSessionHandler = getSslSessionHandler(ioSession);
        IoFilter.NextFilter nextFilter = (IoFilter.NextFilter) ioSession.getAttribute(NEXT_FILTER);
        synchronized (sslSessionHandler) {
            initiateClosure = initiateClosure(nextFilter, ioSession);
        }
        sslSessionHandler.flushScheduledEvents();
        return initiateClosure;
    }

    public boolean isUseClientMode() {
        return this.client;
    }

    public void setUseClientMode(boolean z) {
        this.client = z;
    }

    public boolean isNeedClientAuth() {
        return this.needClientAuth;
    }

    public void setNeedClientAuth(boolean z) {
        this.needClientAuth = z;
    }

    public boolean isWantClientAuth() {
        return this.wantClientAuth;
    }

    public void setWantClientAuth(boolean z) {
        this.wantClientAuth = z;
    }

    public String[] getEnabledCipherSuites() {
        return this.enabledCipherSuites;
    }

    public void setEnabledCipherSuites(String[] strArr) {
        this.enabledCipherSuites = strArr;
    }

    public String[] getEnabledProtocols() {
        return this.enabledProtocols;
    }

    public void setEnabledProtocols(String[] strArr) {
        this.enabledProtocols = strArr;
    }

    public void onPreAdd(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws SSLException {
        if (!ioFilterChain.contains((Class<? extends IoFilter>) SslFilter.class)) {
            LOGGER.debug("Adding the SSL Filter {} to the chain", (Object) str);
            IoSession session = ioFilterChain.getSession();
            session.setAttribute(NEXT_FILTER, nextFilter);
            SslHandler sslHandler = new SslHandler(this, session);
            sslHandler.init();
            session.setAttribute(SSL_HANDLER, sslHandler);
            return;
        }
        LOGGER.error("Only one SSL filter is permitted in a chain.");
        throw new IllegalStateException("Only one SSL filter is permitted in a chain.");
    }

    public void onPostAdd(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws SSLException {
        if (this.autoStart) {
            initiateHandshake(nextFilter, ioFilterChain.getSession());
        }
    }

    public void onPreRemove(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws SSLException {
        IoSession session = ioFilterChain.getSession();
        stopSsl(session);
        session.removeAttribute(NEXT_FILTER);
        session.removeAttribute(SSL_HANDLER);
    }

    public void sessionClosed(IoFilter.NextFilter nextFilter, IoSession ioSession) throws SSLException {
        SslHandler sslSessionHandler = getSslSessionHandler(ioSession);
        try {
            synchronized (sslSessionHandler) {
                sslSessionHandler.destroy();
            }
            sslSessionHandler.flushScheduledEvents();
            nextFilter.sessionClosed(ioSession);
        } catch (Throwable th) {
            nextFilter.sessionClosed(ioSession);
            throw th;
        }
    }

    public void messageReceived(IoFilter.NextFilter nextFilter, IoSession ioSession, Object obj) throws SSLException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("{}: Message received : {}", (Object) getSessionInfo(ioSession), obj);
        }
        SslHandler sslSessionHandler = getSslSessionHandler(ioSession);
        synchronized (sslSessionHandler) {
            if (isSslStarted(ioSession) || !sslSessionHandler.isInboundDone()) {
                IoBuffer ioBuffer = (IoBuffer) obj;
                try {
                    sslSessionHandler.messageReceived(nextFilter, ioBuffer.buf());
                    handleSslData(nextFilter, sslSessionHandler);
                    if (sslSessionHandler.isInboundDone()) {
                        if (sslSessionHandler.isOutboundDone()) {
                            sslSessionHandler.destroy();
                        } else {
                            initiateClosure(nextFilter, ioSession);
                        }
                        if (ioBuffer.hasRemaining()) {
                            sslSessionHandler.scheduleMessageReceived(nextFilter, ioBuffer);
                        }
                    }
                } catch (SSLException e) {
                    e = e;
                    if (!sslSessionHandler.isHandshakeComplete()) {
                        SSLHandshakeException sSLHandshakeException = new SSLHandshakeException("SSL handshake failed.");
                        sSLHandshakeException.initCause(e);
                        e = sSLHandshakeException;
                    }
                    throw e;
                }
            } else {
                sslSessionHandler.scheduleMessageReceived(nextFilter, obj);
            }
        }
        sslSessionHandler.flushScheduledEvents();
    }

    public void messageSent(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) {
        if (writeRequest instanceof EncryptedWriteRequest) {
            nextFilter.messageSent(ioSession, ((EncryptedWriteRequest) writeRequest).getParentRequest());
        }
    }

    public void exceptionCaught(IoFilter.NextFilter nextFilter, IoSession ioSession, Throwable th) throws Exception {
        if (th instanceof WriteToClosedSessionException) {
            List<WriteRequest> requests = ((WriteToClosedSessionException) th).getRequests();
            boolean z = false;
            Iterator<WriteRequest> it = requests.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (isCloseNotify(it.next().getMessage())) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (z) {
                if (requests.size() != 1) {
                    ArrayList arrayList = new ArrayList(requests.size() - 1);
                    for (WriteRequest next : requests) {
                        if (!isCloseNotify(next.getMessage())) {
                            arrayList.add(next);
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        th = new WriteToClosedSessionException((Collection<WriteRequest>) arrayList, th.getMessage(), th.getCause());
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
        }
        nextFilter.exceptionCaught(ioSession, th);
    }

    private boolean isCloseNotify(Object obj) {
        if (!(obj instanceof IoBuffer)) {
            return false;
        }
        IoBuffer ioBuffer = (IoBuffer) obj;
        int position = ioBuffer.position();
        if (ioBuffer.remaining() == 23 && ioBuffer.get(position + 0) == 21 && ioBuffer.get(position + 1) == 3 && ioBuffer.get(position + 2) == 1 && ioBuffer.get(position + 3) == 0 && ioBuffer.get(position + 4) == 18) {
            return true;
        }
        return false;
    }

    public void filterWrite(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws SSLException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("{}: Writing Message : {}", (Object) getSessionInfo(ioSession), (Object) writeRequest);
        }
        boolean z = true;
        SslHandler sslSessionHandler = getSslSessionHandler(ioSession);
        synchronized (sslSessionHandler) {
            if (!isSslStarted(ioSession)) {
                sslSessionHandler.scheduleFilterWrite(nextFilter, writeRequest);
            } else if (ioSession.containsAttribute(DISABLE_ENCRYPTION_ONCE)) {
                ioSession.removeAttribute(DISABLE_ENCRYPTION_ONCE);
                sslSessionHandler.scheduleFilterWrite(nextFilter, writeRequest);
            } else {
                IoBuffer ioBuffer = (IoBuffer) writeRequest.getMessage();
                if (sslSessionHandler.isWritingEncryptedData()) {
                    sslSessionHandler.scheduleFilterWrite(nextFilter, writeRequest);
                } else if (sslSessionHandler.isHandshakeComplete()) {
                    int position = ioBuffer.position();
                    sslSessionHandler.encrypt(ioBuffer.buf());
                    ioBuffer.position(position);
                    sslSessionHandler.scheduleFilterWrite(nextFilter, new EncryptedWriteRequest(writeRequest, sslSessionHandler.fetchOutNetBuffer()));
                } else {
                    if (ioSession.isConnected()) {
                        sslSessionHandler.schedulePreHandshakeWriteRequest(nextFilter, writeRequest);
                    }
                    z = false;
                }
            }
        }
        if (z) {
            sslSessionHandler.flushScheduledEvents();
        }
    }

    public void filterClose(final IoFilter.NextFilter nextFilter, final IoSession ioSession) throws SSLException {
        SslHandler sslHandler = (SslHandler) ioSession.getAttribute(SSL_HANDLER);
        if (sslHandler == null) {
            nextFilter.filterClose(ioSession);
            return;
        }
        WriteFuture writeFuture = null;
        try {
            synchronized (sslHandler) {
                if (isSslStarted(ioSession)) {
                    writeFuture = initiateClosure(nextFilter, ioSession);
                    writeFuture.addListener(new IoFutureListener<IoFuture>() {
                        public void operationComplete(IoFuture ioFuture) {
                            nextFilter.filterClose(ioSession);
                        }
                    });
                }
            }
            sslHandler.flushScheduledEvents();
            if (writeFuture == null) {
                nextFilter.filterClose(ioSession);
            }
        } catch (Throwable th) {
            if (writeFuture == null) {
                nextFilter.filterClose(ioSession);
            }
            throw th;
        }
    }

    private void initiateHandshake(IoFilter.NextFilter nextFilter, IoSession ioSession) throws SSLException {
        LOGGER.debug("{} : Starting the first handshake", (Object) getSessionInfo(ioSession));
        SslHandler sslSessionHandler = getSslSessionHandler(ioSession);
        synchronized (sslSessionHandler) {
            sslSessionHandler.handshake(nextFilter);
        }
        sslSessionHandler.flushScheduledEvents();
    }

    private WriteFuture initiateClosure(IoFilter.NextFilter nextFilter, IoSession ioSession) throws SSLException {
        SslHandler sslSessionHandler = getSslSessionHandler(ioSession);
        if (!sslSessionHandler.closeOutbound()) {
            return DefaultWriteFuture.newNotWrittenFuture(ioSession, new IllegalStateException("SSL session is shut down already."));
        }
        WriteFuture writeNetBuffer = sslSessionHandler.writeNetBuffer(nextFilter);
        if (writeNetBuffer == null) {
            writeNetBuffer = DefaultWriteFuture.newWrittenFuture(ioSession);
        }
        if (sslSessionHandler.isInboundDone()) {
            sslSessionHandler.destroy();
        }
        if (ioSession.containsAttribute(USE_NOTIFICATION)) {
            sslSessionHandler.scheduleMessageReceived(nextFilter, SESSION_UNSECURED);
        }
        return writeNetBuffer;
    }

    private void handleSslData(IoFilter.NextFilter nextFilter, SslHandler sslHandler) throws SSLException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("{}: Processing the SSL Data ", (Object) getSessionInfo(sslHandler.getSession()));
        }
        if (sslHandler.isHandshakeComplete()) {
            sslHandler.flushPreHandshakeEvents();
        }
        sslHandler.writeNetBuffer(nextFilter);
        handleAppDataRead(nextFilter, sslHandler);
    }

    private void handleAppDataRead(IoFilter.NextFilter nextFilter, SslHandler sslHandler) {
        IoBuffer fetchAppBuffer = sslHandler.fetchAppBuffer();
        if (fetchAppBuffer.hasRemaining()) {
            sslHandler.scheduleMessageReceived(nextFilter, fetchAppBuffer);
        }
    }

    private SslHandler getSslSessionHandler(IoSession ioSession) {
        SslHandler sslHandler = (SslHandler) ioSession.getAttribute(SSL_HANDLER);
        if (sslHandler == null) {
            throw new IllegalStateException();
        } else if (sslHandler.getSslFilter() == this) {
            return sslHandler;
        } else {
            throw new IllegalArgumentException("Not managed by this filter.");
        }
    }

    public static class SslFilterMessage {
        private final String name;

        private SslFilterMessage(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }
    }

    private static class EncryptedWriteRequest extends WriteRequestWrapper {
        private final IoBuffer encryptedMessage;

        private EncryptedWriteRequest(WriteRequest writeRequest, IoBuffer ioBuffer) {
            super(writeRequest);
            this.encryptedMessage = ioBuffer;
        }

        public Object getMessage() {
            return this.encryptedMessage;
        }
    }
}
