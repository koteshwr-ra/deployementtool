package org.apache.mina.core.future;

import org.apache.mina.core.session.IoSession;

public class DefaultWriteFuture extends DefaultIoFuture implements WriteFuture {
    public static WriteFuture newWrittenFuture(IoSession ioSession) {
        DefaultWriteFuture defaultWriteFuture = new DefaultWriteFuture(ioSession);
        defaultWriteFuture.setWritten();
        return defaultWriteFuture;
    }

    public static WriteFuture newNotWrittenFuture(IoSession ioSession, Throwable th) {
        DefaultWriteFuture defaultWriteFuture = new DefaultWriteFuture(ioSession);
        defaultWriteFuture.setException(th);
        return defaultWriteFuture;
    }

    public DefaultWriteFuture(IoSession ioSession) {
        super(ioSession);
    }

    public boolean isWritten() {
        if (!isDone()) {
            return false;
        }
        Object value = getValue();
        if (value instanceof Boolean) {
            return ((Boolean) value).booleanValue();
        }
        return false;
    }

    public Throwable getException() {
        if (!isDone()) {
            return null;
        }
        Object value = getValue();
        if (value instanceof Throwable) {
            return (Throwable) value;
        }
        return null;
    }

    public void setWritten() {
        setValue(Boolean.TRUE);
    }

    public void setException(Throwable th) {
        if (th != null) {
            setValue(th);
            return;
        }
        throw new IllegalArgumentException("exception");
    }

    public WriteFuture await() throws InterruptedException {
        return (WriteFuture) super.await();
    }

    public WriteFuture awaitUninterruptibly() {
        return (WriteFuture) super.awaitUninterruptibly();
    }

    public WriteFuture addListener(IoFutureListener<?> ioFutureListener) {
        return (WriteFuture) super.addListener(ioFutureListener);
    }

    public WriteFuture removeListener(IoFutureListener<?> ioFutureListener) {
        return (WriteFuture) super.removeListener(ioFutureListener);
    }
}
