package org.apache.mina.proxy.handlers.http;

import java.util.List;
import java.util.Map;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.proxy.ProxyAuthException;
import org.apache.mina.proxy.handlers.ProxyRequest;
import org.apache.mina.proxy.session.ProxyIoSession;
import org.apache.mina.proxy.utils.StringUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractAuthLogicHandler {
    private static final Logger logger = LoggerFactory.getLogger(AbstractAuthLogicHandler.class);
    protected ProxyIoSession proxyIoSession;
    protected ProxyRequest request;
    protected int step = 0;

    public abstract void doHandshake(IoFilter.NextFilter nextFilter) throws ProxyAuthException;

    public abstract void handleResponse(HttpProxyResponse httpProxyResponse) throws ProxyAuthException;

    protected AbstractAuthLogicHandler(ProxyIoSession proxyIoSession2) throws ProxyAuthException {
        this.proxyIoSession = proxyIoSession2;
        ProxyRequest request2 = proxyIoSession2.getRequest();
        this.request = request2;
        if (request2 == null || !(request2 instanceof HttpProxyRequest)) {
            throw new IllegalArgumentException("request parameter should be a non null HttpProxyRequest instance");
        }
    }

    /* access modifiers changed from: protected */
    public void writeRequest(IoFilter.NextFilter nextFilter, HttpProxyRequest httpProxyRequest) throws ProxyAuthException {
        logger.debug("  sending HTTP request");
        ((AbstractHttpLogicHandler) this.proxyIoSession.getHandler()).writeRequest(nextFilter, httpProxyRequest);
    }

    public static void addKeepAliveHeaders(Map<String, List<String>> map) {
        StringUtilities.addValueToHeader(map, "Keep-Alive", HttpProxyConstants.DEFAULT_KEEP_ALIVE_TIME, true);
        StringUtilities.addValueToHeader(map, "Proxy-Connection", "keep-Alive", true);
    }
}
