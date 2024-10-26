package org.apache.mina.proxy;

import java.util.LinkedList;
import java.util.Queue;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.future.DefaultWriteFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.DefaultWriteRequest;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.proxy.filter.ProxyFilter;
import org.apache.mina.proxy.filter.ProxyHandshakeIoBuffer;
import org.apache.mina.proxy.session.ProxyIoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractProxyLogicHandler implements ProxyLogicHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractProxyLogicHandler.class);
    private boolean handshakeComplete = false;
    private ProxyIoSession proxyIoSession;
    private Queue<Event> writeRequestQueue = null;

    public AbstractProxyLogicHandler(ProxyIoSession proxyIoSession2) {
        this.proxyIoSession = proxyIoSession2;
    }

    /* access modifiers changed from: protected */
    public ProxyFilter getProxyFilter() {
        return this.proxyIoSession.getProxyFilter();
    }

    /* access modifiers changed from: protected */
    public IoSession getSession() {
        return this.proxyIoSession.getSession();
    }

    public ProxyIoSession getProxyIoSession() {
        return this.proxyIoSession;
    }

    /* access modifiers changed from: protected */
    public WriteFuture writeData(IoFilter.NextFilter nextFilter, IoBuffer ioBuffer) {
        ProxyHandshakeIoBuffer proxyHandshakeIoBuffer = new ProxyHandshakeIoBuffer(ioBuffer);
        LOGGER.debug("   session write: {}", (Object) proxyHandshakeIoBuffer);
        DefaultWriteFuture defaultWriteFuture = new DefaultWriteFuture(getSession());
        getProxyFilter().writeData(nextFilter, getSession(), new DefaultWriteRequest(proxyHandshakeIoBuffer, defaultWriteFuture), true);
        return defaultWriteFuture;
    }

    public boolean isHandshakeComplete() {
        boolean z;
        synchronized (this) {
            z = this.handshakeComplete;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public final void setHandshakeComplete() {
        synchronized (this) {
            this.handshakeComplete = true;
        }
        ProxyIoSession proxyIoSession2 = getProxyIoSession();
        proxyIoSession2.getConnector().fireConnected(proxyIoSession2.getSession()).awaitUninterruptibly();
        LOGGER.debug("  handshake completed");
        try {
            proxyIoSession2.getEventQueue().flushPendingSessionEvents();
            flushPendingWriteRequests();
        } catch (Exception e) {
            LOGGER.error("Unable to flush pending write requests", (Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void flushPendingWriteRequests() throws Exception {
        LOGGER.debug(" flushPendingWriteRequests()");
        if (this.writeRequestQueue != null) {
            while (true) {
                Event poll = this.writeRequestQueue.poll();
                if (poll != null) {
                    LOGGER.debug(" Flushing buffered write request: {}", poll.data);
                    getProxyFilter().filterWrite(poll.nextFilter, getSession(), (WriteRequest) poll.data);
                } else {
                    this.writeRequestQueue = null;
                    return;
                }
            }
        }
    }

    public synchronized void enqueueWriteRequest(IoFilter.NextFilter nextFilter, WriteRequest writeRequest) {
        if (this.writeRequestQueue == null) {
            this.writeRequestQueue = new LinkedList();
        }
        this.writeRequestQueue.offer(new Event(nextFilter, writeRequest));
    }

    /* access modifiers changed from: protected */
    public void closeSession(String str, Throwable th) {
        if (th != null) {
            LOGGER.error(str, th);
            this.proxyIoSession.setAuthenticationFailed(true);
        } else {
            LOGGER.error(str);
        }
        getSession().close(true);
    }

    /* access modifiers changed from: protected */
    public void closeSession(String str) {
        closeSession(str, (Throwable) null);
    }

    private static final class Event {
        /* access modifiers changed from: private */
        public final Object data;
        /* access modifiers changed from: private */
        public final IoFilter.NextFilter nextFilter;

        Event(IoFilter.NextFilter nextFilter2, Object obj) {
            this.nextFilter = nextFilter2;
            this.data = obj;
        }

        public Object getData() {
            return this.data;
        }

        public IoFilter.NextFilter getNextFilter() {
            return this.nextFilter;
        }
    }
}
