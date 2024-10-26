package org.apache.mina.proxy.session;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.proxy.ProxyConnector;
import org.apache.mina.proxy.ProxyLogicHandler;
import org.apache.mina.proxy.event.IoSessionEventQueue;
import org.apache.mina.proxy.filter.ProxyFilter;
import org.apache.mina.proxy.handlers.ProxyRequest;
import org.apache.mina.proxy.handlers.http.HttpAuthenticationMethods;

public class ProxyIoSession {
    private static final String DEFAULT_ENCODING = "ISO-8859-1";
    public static final String PROXY_SESSION = (ProxyConnector.class.getName() + ".ProxySession");
    private boolean authenticationFailed;
    private String charsetName;
    private ProxyConnector connector;
    private IoSessionEventQueue eventQueue = new IoSessionEventQueue(this);
    private ProxyLogicHandler handler;
    private List<HttpAuthenticationMethods> preferedOrder;
    private InetSocketAddress proxyAddress = null;
    private ProxyFilter proxyFilter;
    private boolean reconnectionNeeded = false;
    private ProxyRequest request;
    private IoSession session;

    public ProxyIoSession(InetSocketAddress inetSocketAddress, ProxyRequest proxyRequest) {
        setProxyAddress(inetSocketAddress);
        setRequest(proxyRequest);
    }

    public IoSessionEventQueue getEventQueue() {
        return this.eventQueue;
    }

    public List<HttpAuthenticationMethods> getPreferedOrder() {
        return this.preferedOrder;
    }

    public void setPreferedOrder(List<HttpAuthenticationMethods> list) {
        this.preferedOrder = list;
    }

    public ProxyLogicHandler getHandler() {
        return this.handler;
    }

    public void setHandler(ProxyLogicHandler proxyLogicHandler) {
        this.handler = proxyLogicHandler;
    }

    public ProxyFilter getProxyFilter() {
        return this.proxyFilter;
    }

    public void setProxyFilter(ProxyFilter proxyFilter2) {
        this.proxyFilter = proxyFilter2;
    }

    public ProxyRequest getRequest() {
        return this.request;
    }

    private void setRequest(ProxyRequest proxyRequest) {
        if (proxyRequest != null) {
            this.request = proxyRequest;
            return;
        }
        throw new IllegalArgumentException("request cannot be null");
    }

    public IoSession getSession() {
        return this.session;
    }

    public void setSession(IoSession ioSession) {
        this.session = ioSession;
    }

    public ProxyConnector getConnector() {
        return this.connector;
    }

    public void setConnector(ProxyConnector proxyConnector) {
        this.connector = proxyConnector;
    }

    public InetSocketAddress getProxyAddress() {
        return this.proxyAddress;
    }

    private void setProxyAddress(InetSocketAddress inetSocketAddress) {
        if (inetSocketAddress != null) {
            this.proxyAddress = inetSocketAddress;
            return;
        }
        throw new IllegalArgumentException("proxyAddress object cannot be null");
    }

    public boolean isReconnectionNeeded() {
        return this.reconnectionNeeded;
    }

    public void setReconnectionNeeded(boolean z) {
        this.reconnectionNeeded = z;
    }

    public Charset getCharset() {
        return Charset.forName(getCharsetName());
    }

    public String getCharsetName() {
        if (this.charsetName == null) {
            this.charsetName = "ISO-8859-1";
        }
        return this.charsetName;
    }

    public void setCharsetName(String str) {
        this.charsetName = str;
    }

    public boolean isAuthenticationFailed() {
        return this.authenticationFailed;
    }

    public void setAuthenticationFailed(boolean z) {
        this.authenticationFailed = z;
    }
}
