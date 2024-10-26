package org.apache.mina.proxy;

import javax.security.sasl.SaslException;

public class ProxyAuthException extends SaslException {
    private static final long serialVersionUID = -6511596809517532988L;

    public ProxyAuthException(String str) {
        super(str);
    }

    public ProxyAuthException(String str, Throwable th) {
        super(str, th);
    }
}
