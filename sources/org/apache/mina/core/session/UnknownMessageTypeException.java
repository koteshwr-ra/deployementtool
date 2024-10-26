package org.apache.mina.core.session;

public class UnknownMessageTypeException extends RuntimeException {
    private static final long serialVersionUID = 3257290227428047158L;

    public UnknownMessageTypeException() {
    }

    public UnknownMessageTypeException(String str, Throwable th) {
        super(str, th);
    }

    public UnknownMessageTypeException(String str) {
        super(str);
    }

    public UnknownMessageTypeException(Throwable th) {
        super(th);
    }
}
