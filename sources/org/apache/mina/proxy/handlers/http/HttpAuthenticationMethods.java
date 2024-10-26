package org.apache.mina.proxy.handlers.http;

import org.apache.mina.proxy.ProxyAuthException;
import org.apache.mina.proxy.handlers.http.basic.HttpBasicAuthLogicHandler;
import org.apache.mina.proxy.handlers.http.basic.HttpNoAuthLogicHandler;
import org.apache.mina.proxy.handlers.http.digest.HttpDigestAuthLogicHandler;
import org.apache.mina.proxy.handlers.http.ntlm.HttpNTLMAuthLogicHandler;
import org.apache.mina.proxy.session.ProxyIoSession;

public enum HttpAuthenticationMethods {
    NO_AUTH(1),
    BASIC(2),
    NTLM(3),
    DIGEST(4);
    
    private final int id;

    private HttpAuthenticationMethods(int i) {
        this.id = i;
    }

    public int getId() {
        return this.id;
    }

    public AbstractAuthLogicHandler getNewHandler(ProxyIoSession proxyIoSession) throws ProxyAuthException {
        return getNewHandler(this.id, proxyIoSession);
    }

    public static AbstractAuthLogicHandler getNewHandler(int i, ProxyIoSession proxyIoSession) throws ProxyAuthException {
        if (i == BASIC.id) {
            return new HttpBasicAuthLogicHandler(proxyIoSession);
        }
        if (i == DIGEST.id) {
            return new HttpDigestAuthLogicHandler(proxyIoSession);
        }
        if (i == NTLM.id) {
            return new HttpNTLMAuthLogicHandler(proxyIoSession);
        }
        if (i == NO_AUTH.id) {
            return new HttpNoAuthLogicHandler(proxyIoSession);
        }
        return null;
    }
}
