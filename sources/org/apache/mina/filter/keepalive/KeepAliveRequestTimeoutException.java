package org.apache.mina.filter.keepalive;

public class KeepAliveRequestTimeoutException extends RuntimeException {
    private static final long serialVersionUID = -1985092764656546558L;

    public KeepAliveRequestTimeoutException() {
    }

    public KeepAliveRequestTimeoutException(String str, Throwable th) {
        super(str, th);
    }

    public KeepAliveRequestTimeoutException(String str) {
        super(str);
    }

    public KeepAliveRequestTimeoutException(Throwable th) {
        super(th);
    }
}
