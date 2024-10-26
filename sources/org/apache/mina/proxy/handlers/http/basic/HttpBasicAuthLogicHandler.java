package org.apache.mina.proxy.handlers.http.basic;

import com.google.common.net.HttpHeaders;
import java.util.HashMap;
import java.util.Map;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.proxy.ProxyAuthException;
import org.apache.mina.proxy.handlers.http.AbstractAuthLogicHandler;
import org.apache.mina.proxy.handlers.http.HttpProxyConstants;
import org.apache.mina.proxy.handlers.http.HttpProxyRequest;
import org.apache.mina.proxy.handlers.http.HttpProxyResponse;
import org.apache.mina.proxy.session.ProxyIoSession;
import org.apache.mina.proxy.utils.StringUtilities;
import org.apache.mina.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpBasicAuthLogicHandler extends AbstractAuthLogicHandler {
    private static final Logger logger = LoggerFactory.getLogger(HttpBasicAuthLogicHandler.class);

    public HttpBasicAuthLogicHandler(ProxyIoSession proxyIoSession) throws ProxyAuthException {
        super(proxyIoSession);
        ((HttpProxyRequest) this.request).checkRequiredProperties(HttpProxyConstants.USER_PROPERTY, HttpProxyConstants.PWD_PROPERTY);
    }

    /* JADX WARNING: type inference failed for: r7v1, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    public void doHandshake(IoFilter.NextFilter nextFilter) throws ProxyAuthException {
        logger.debug(" doHandshake()");
        if (this.step <= 0) {
            HttpProxyRequest httpProxyRequest = (HttpProxyRequest) this.request;
            Map headers = httpProxyRequest.getHeaders() != null ? httpProxyRequest.getHeaders() : new HashMap();
            StringUtilities.addValueToHeader(headers, HttpHeaders.PROXY_AUTHORIZATION, "Basic " + createAuthorization(httpProxyRequest.getProperties().get(HttpProxyConstants.USER_PROPERTY), httpProxyRequest.getProperties().get(HttpProxyConstants.PWD_PROPERTY)), true);
            addKeepAliveHeaders(headers);
            httpProxyRequest.setHeaders(headers);
            writeRequest(nextFilter, httpProxyRequest);
            this.step++;
            return;
        }
        throw new ProxyAuthException("Authentication request already sent");
    }

    public static String createAuthorization(String str, String str2) {
        return new String(Base64.encodeBase64((str + ":" + str2).getBytes()));
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    public void handleResponse(HttpProxyResponse httpProxyResponse) throws ProxyAuthException {
        if (httpProxyResponse.getStatusCode() != 407) {
            throw new ProxyAuthException("Received error response code (" + httpProxyResponse.getStatusLine() + ").");
        }
    }
}
