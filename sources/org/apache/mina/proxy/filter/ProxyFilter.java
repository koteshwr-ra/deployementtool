package org.apache.mina.proxy.filter;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.proxy.ProxyLogicHandler;
import org.apache.mina.proxy.event.IoSessionEvent;
import org.apache.mina.proxy.event.IoSessionEventType;
import org.apache.mina.proxy.handlers.ProxyRequest;
import org.apache.mina.proxy.handlers.http.HttpSmartProxyHandler;
import org.apache.mina.proxy.handlers.socks.Socks4LogicHandler;
import org.apache.mina.proxy.handlers.socks.Socks5LogicHandler;
import org.apache.mina.proxy.handlers.socks.SocksProxyRequest;
import org.apache.mina.proxy.session.ProxyIoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyFilter extends IoFilterAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyFilter.class);

    public void onPreAdd(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) {
        if (ioFilterChain.contains((Class<? extends IoFilter>) ProxyFilter.class)) {
            throw new IllegalStateException("A filter chain cannot contain more than one ProxyFilter.");
        }
    }

    public void onPreRemove(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) {
        ioFilterChain.getSession().removeAttribute(ProxyIoSession.PROXY_SESSION);
    }

    public void exceptionCaught(IoFilter.NextFilter nextFilter, IoSession ioSession, Throwable th) throws Exception {
        ((ProxyIoSession) ioSession.getAttribute(ProxyIoSession.PROXY_SESSION)).setAuthenticationFailed(true);
        super.exceptionCaught(nextFilter, ioSession, th);
    }

    private ProxyLogicHandler getProxyHandler(IoSession ioSession) {
        ProxyLogicHandler handler = ((ProxyIoSession) ioSession.getAttribute(ProxyIoSession.PROXY_SESSION)).getHandler();
        if (handler == null) {
            throw new IllegalStateException();
        } else if (handler.getProxyIoSession().getProxyFilter() == this) {
            return handler;
        } else {
            throw new IllegalArgumentException("Not managed by this filter.");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0051, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void messageReceived(org.apache.mina.core.filterchain.IoFilter.NextFilter r4, org.apache.mina.core.session.IoSession r5, java.lang.Object r6) throws org.apache.mina.proxy.ProxyAuthException {
        /*
            r3 = this;
            org.apache.mina.proxy.ProxyLogicHandler r0 = r3.getProxyHandler(r5)
            monitor-enter(r0)
            org.apache.mina.core.buffer.IoBuffer r6 = (org.apache.mina.core.buffer.IoBuffer) r6     // Catch:{ all -> 0x0052 }
            boolean r1 = r0.isHandshakeComplete()     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0011
            r4.messageReceived(r5, r6)     // Catch:{ all -> 0x0052 }
            goto L_0x0050
        L_0x0011:
            org.slf4j.Logger r1 = LOGGER     // Catch:{ all -> 0x0052 }
            java.lang.String r2 = " Data Read: {} ({})"
            r1.debug((java.lang.String) r2, (java.lang.Object) r0, (java.lang.Object) r6)     // Catch:{ all -> 0x0052 }
        L_0x0018:
            boolean r1 = r6.hasRemaining()     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0040
            boolean r1 = r0.isHandshakeComplete()     // Catch:{ all -> 0x0052 }
            if (r1 != 0) goto L_0x0040
            org.slf4j.Logger r1 = LOGGER     // Catch:{ all -> 0x0052 }
            java.lang.String r2 = " Pre-handshake - passing to handler"
            r1.debug(r2)     // Catch:{ all -> 0x0052 }
            int r1 = r6.position()     // Catch:{ all -> 0x0052 }
            r0.messageReceived(r4, r6)     // Catch:{ all -> 0x0052 }
            int r2 = r6.position()     // Catch:{ all -> 0x0052 }
            if (r2 == r1) goto L_0x003e
            boolean r1 = r5.isClosing()     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0018
        L_0x003e:
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            return
        L_0x0040:
            boolean r1 = r6.hasRemaining()     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0050
            org.slf4j.Logger r1 = LOGGER     // Catch:{ all -> 0x0052 }
            java.lang.String r2 = " Passing remaining data to next filter"
            r1.debug(r2)     // Catch:{ all -> 0x0052 }
            r4.messageReceived(r5, r6)     // Catch:{ all -> 0x0052 }
        L_0x0050:
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            return
        L_0x0052:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.proxy.filter.ProxyFilter.messageReceived(org.apache.mina.core.filterchain.IoFilter$NextFilter, org.apache.mina.core.session.IoSession, java.lang.Object):void");
    }

    public void filterWrite(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) {
        writeData(nextFilter, ioSession, writeRequest, false);
    }

    public void writeData(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest, boolean z) {
        ProxyLogicHandler proxyHandler = getProxyHandler(ioSession);
        synchronized (proxyHandler) {
            if (proxyHandler.isHandshakeComplete()) {
                nextFilter.filterWrite(ioSession, writeRequest);
            } else if (z) {
                LOGGER.debug("   handshake data: {}", writeRequest.getMessage());
                nextFilter.filterWrite(ioSession, writeRequest);
            } else if (!ioSession.isConnected()) {
                LOGGER.debug(" Write request on closed session. Request ignored.");
            } else {
                LOGGER.debug(" Handshaking is not complete yet. Buffering write request.");
                proxyHandler.enqueueWriteRequest(nextFilter, writeRequest);
            }
        }
    }

    public void messageSent(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        if (writeRequest.getMessage() == null || !(writeRequest.getMessage() instanceof ProxyHandshakeIoBuffer)) {
            nextFilter.messageSent(ioSession, writeRequest);
        }
    }

    public void sessionCreated(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        ProxyLogicHandler proxyLogicHandler;
        Logger logger = LOGGER;
        logger.debug("Session created: " + ioSession);
        ProxyIoSession proxyIoSession = (ProxyIoSession) ioSession.getAttribute(ProxyIoSession.PROXY_SESSION);
        Logger logger2 = LOGGER;
        logger2.debug("  get proxyIoSession: " + proxyIoSession);
        proxyIoSession.setProxyFilter(this);
        if (proxyIoSession.getHandler() == null) {
            ProxyRequest request = proxyIoSession.getRequest();
            if (!(request instanceof SocksProxyRequest)) {
                proxyLogicHandler = new HttpSmartProxyHandler(proxyIoSession);
            } else if (((SocksProxyRequest) request).getProtocolVersion() == 4) {
                proxyLogicHandler = new Socks4LogicHandler(proxyIoSession);
            } else {
                proxyLogicHandler = new Socks5LogicHandler(proxyIoSession);
            }
            proxyIoSession.setHandler(proxyLogicHandler);
            proxyLogicHandler.doHandshake(nextFilter);
        }
        proxyIoSession.getEventQueue().enqueueEventIfNecessary(new IoSessionEvent(nextFilter, ioSession, IoSessionEventType.CREATED));
    }

    public void sessionOpened(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        ((ProxyIoSession) ioSession.getAttribute(ProxyIoSession.PROXY_SESSION)).getEventQueue().enqueueEventIfNecessary(new IoSessionEvent(nextFilter, ioSession, IoSessionEventType.OPENED));
    }

    public void sessionIdle(IoFilter.NextFilter nextFilter, IoSession ioSession, IdleStatus idleStatus) throws Exception {
        ((ProxyIoSession) ioSession.getAttribute(ProxyIoSession.PROXY_SESSION)).getEventQueue().enqueueEventIfNecessary(new IoSessionEvent(nextFilter, ioSession, idleStatus));
    }

    public void sessionClosed(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        ((ProxyIoSession) ioSession.getAttribute(ProxyIoSession.PROXY_SESSION)).getEventQueue().enqueueEventIfNecessary(new IoSessionEvent(nextFilter, ioSession, IoSessionEventType.CLOSED));
    }
}
