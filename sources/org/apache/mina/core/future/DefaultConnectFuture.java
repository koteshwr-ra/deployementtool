package org.apache.mina.core.future;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.session.IoSession;

public class DefaultConnectFuture extends DefaultIoFuture implements ConnectFuture {
    private static final Object CANCELED = new Object();

    public static ConnectFuture newFailedFuture(Throwable th) {
        DefaultConnectFuture defaultConnectFuture = new DefaultConnectFuture();
        defaultConnectFuture.setException(th);
        return defaultConnectFuture;
    }

    public DefaultConnectFuture() {
        super((IoSession) null);
    }

    public IoSession getSession() {
        Object value = getValue();
        if (value instanceof RuntimeException) {
            throw ((RuntimeException) value);
        } else if (value instanceof Error) {
            throw ((Error) value);
        } else if (value instanceof Throwable) {
            throw ((RuntimeIoException) new RuntimeIoException("Failed to get the session.").initCause((Throwable) value));
        } else if (value instanceof IoSession) {
            return (IoSession) value;
        } else {
            return null;
        }
    }

    public Throwable getException() {
        Object value = getValue();
        if (value instanceof Throwable) {
            return (Throwable) value;
        }
        return null;
    }

    public boolean isConnected() {
        return getValue() instanceof IoSession;
    }

    public boolean isCanceled() {
        return getValue() == CANCELED;
    }

    public void setSession(IoSession ioSession) {
        if (ioSession != null) {
            setValue(ioSession);
            return;
        }
        throw new IllegalArgumentException("session");
    }

    public void setException(Throwable th) {
        if (th != null) {
            setValue(th);
            return;
        }
        throw new IllegalArgumentException("exception");
    }

    public void cancel() {
        setValue(CANCELED);
    }

    public ConnectFuture await() throws InterruptedException {
        return (ConnectFuture) super.await();
    }

    public ConnectFuture awaitUninterruptibly() {
        return (ConnectFuture) super.awaitUninterruptibly();
    }

    public ConnectFuture addListener(IoFutureListener<?> ioFutureListener) {
        return (ConnectFuture) super.addListener(ioFutureListener);
    }

    public ConnectFuture removeListener(IoFutureListener<?> ioFutureListener) {
        return (ConnectFuture) super.removeListener(ioFutureListener);
    }
}
