package org.apache.mina.proxy.session;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionInitializer;

public class ProxyIoSessionInitializer<T extends ConnectFuture> implements IoSessionInitializer<T> {
    private final ProxyIoSession proxyIoSession;
    private final IoSessionInitializer<T> wrappedSessionInitializer;

    public ProxyIoSessionInitializer(IoSessionInitializer<T> ioSessionInitializer, ProxyIoSession proxyIoSession2) {
        this.wrappedSessionInitializer = ioSessionInitializer;
        this.proxyIoSession = proxyIoSession2;
    }

    public ProxyIoSession getProxySession() {
        return this.proxyIoSession;
    }

    public void initializeSession(IoSession ioSession, T t) {
        IoSessionInitializer<T> ioSessionInitializer = this.wrappedSessionInitializer;
        if (ioSessionInitializer != null) {
            ioSessionInitializer.initializeSession(ioSession, t);
        }
        ProxyIoSession proxyIoSession2 = this.proxyIoSession;
        if (proxyIoSession2 != null) {
            proxyIoSession2.setSession(ioSession);
            ioSession.setAttribute(ProxyIoSession.PROXY_SESSION, this.proxyIoSession);
        }
    }
}
