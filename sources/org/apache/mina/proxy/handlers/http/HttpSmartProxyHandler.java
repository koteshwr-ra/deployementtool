package org.apache.mina.proxy.handlers.http;

import com.google.common.net.HttpHeaders;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.proxy.ProxyAuthException;
import org.apache.mina.proxy.session.ProxyIoSession;
import org.apache.mina.proxy.utils.StringUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpSmartProxyHandler extends AbstractHttpLogicHandler {
    private static final Logger logger = LoggerFactory.getLogger(HttpSmartProxyHandler.class);
    private AbstractAuthLogicHandler authHandler;
    private boolean requestSent = false;

    public HttpSmartProxyHandler(ProxyIoSession proxyIoSession) {
        super(proxyIoSession);
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    public void doHandshake(IoFilter.NextFilter nextFilter) throws ProxyAuthException {
        logger.debug(" doHandshake()");
        AbstractAuthLogicHandler abstractAuthLogicHandler = this.authHandler;
        if (abstractAuthLogicHandler != null) {
            abstractAuthLogicHandler.doHandshake(nextFilter);
        } else if (!this.requestSent) {
            logger.debug("  sending HTTP request");
            HttpProxyRequest httpProxyRequest = (HttpProxyRequest) getProxyIoSession().getRequest();
            Map headers = httpProxyRequest.getHeaders() != null ? httpProxyRequest.getHeaders() : new HashMap();
            AbstractAuthLogicHandler.addKeepAliveHeaders(headers);
            httpProxyRequest.setHeaders(headers);
            writeRequest(nextFilter, httpProxyRequest);
            this.requestSent = true;
        } else {
            throw new ProxyAuthException("Authentication request already sent");
        }
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    private void autoSelectAuthHandler(HttpProxyResponse httpProxyResponse) throws ProxyAuthException {
        List list = httpProxyResponse.getHeaders().get(HttpHeaders.PROXY_AUTHENTICATE);
        ProxyIoSession proxyIoSession = getProxyIoSession();
        if (list != null && list.size() != 0) {
            if (getProxyIoSession().getPreferedOrder() != null) {
                Iterator<HttpAuthenticationMethods> it = proxyIoSession.getPreferedOrder().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    HttpAuthenticationMethods next = it.next();
                    if (this.authHandler == null) {
                        if (next != HttpAuthenticationMethods.NO_AUTH) {
                            Iterator it2 = list.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                String lowerCase = ((String) it2.next()).toLowerCase();
                                try {
                                    if (!lowerCase.contains("basic") || next != HttpAuthenticationMethods.BASIC) {
                                        if (!lowerCase.contains("digest") || next != HttpAuthenticationMethods.DIGEST) {
                                            if (lowerCase.contains("ntlm") && next == HttpAuthenticationMethods.NTLM) {
                                                this.authHandler = HttpAuthenticationMethods.NTLM.getNewHandler(proxyIoSession);
                                                break;
                                            }
                                        } else {
                                            this.authHandler = HttpAuthenticationMethods.DIGEST.getNewHandler(proxyIoSession);
                                            break;
                                        }
                                    } else {
                                        this.authHandler = HttpAuthenticationMethods.BASIC.getNewHandler(proxyIoSession);
                                        break;
                                    }
                                } catch (Exception e) {
                                    logger.debug("Following exception occured:", (Throwable) e);
                                }
                            }
                        } else {
                            this.authHandler = HttpAuthenticationMethods.NO_AUTH.getNewHandler(proxyIoSession);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            } else {
                Iterator it3 = list.iterator();
                int i = -1;
                while (true) {
                    if (!it3.hasNext()) {
                        break;
                    }
                    String lowerCase2 = ((String) it3.next()).toLowerCase();
                    if (lowerCase2.contains("ntlm")) {
                        i = HttpAuthenticationMethods.NTLM.getId();
                        break;
                    } else if (lowerCase2.contains("digest") && i != HttpAuthenticationMethods.NTLM.getId()) {
                        i = HttpAuthenticationMethods.DIGEST.getId();
                    } else if (lowerCase2.contains("basic") && i == -1) {
                        i = HttpAuthenticationMethods.BASIC.getId();
                    }
                }
                if (i != -1) {
                    try {
                        this.authHandler = HttpAuthenticationMethods.getNewHandler(i, proxyIoSession);
                    } catch (Exception e2) {
                        logger.debug("Following exception occured:", (Throwable) e2);
                    }
                }
                if (this.authHandler == null) {
                    this.authHandler = HttpAuthenticationMethods.NO_AUTH.getNewHandler(proxyIoSession);
                }
            }
        } else {
            this.authHandler = HttpAuthenticationMethods.NO_AUTH.getNewHandler(proxyIoSession);
        }
        if (this.authHandler == null) {
            throw new ProxyAuthException("Unknown authentication mechanism(s): " + list);
        }
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Throwable, org.apache.mina.proxy.ProxyAuthException] */
    public void handleResponse(HttpProxyResponse httpProxyResponse) throws ProxyAuthException {
        if (!isHandshakeComplete() && ("close".equalsIgnoreCase(StringUtilities.getSingleValuedHeader(httpProxyResponse.getHeaders(), "Proxy-Connection")) || "close".equalsIgnoreCase(StringUtilities.getSingleValuedHeader(httpProxyResponse.getHeaders(), HttpHeaders.CONNECTION)))) {
            getProxyIoSession().setReconnectionNeeded(true);
        }
        if (httpProxyResponse.getStatusCode() == 407) {
            if (this.authHandler == null) {
                autoSelectAuthHandler(httpProxyResponse);
            }
            this.authHandler.handleResponse(httpProxyResponse);
            return;
        }
        throw new ProxyAuthException("Error: unexpected response code " + httpProxyResponse.getStatusLine() + " received from proxy.");
    }
}
