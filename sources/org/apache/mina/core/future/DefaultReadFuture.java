package org.apache.mina.core.future;

import java.io.IOException;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.session.IoSession;

public class DefaultReadFuture extends DefaultIoFuture implements ReadFuture {
    private static final Object CLOSED = new Object();

    public DefaultReadFuture(IoSession ioSession) {
        super(ioSession);
    }

    public Object getMessage() {
        Object value;
        if (!isDone() || (value = getValue()) == CLOSED) {
            return null;
        }
        if (value instanceof ExceptionHolder) {
            value = ((ExceptionHolder) value).exception;
            if (value instanceof RuntimeException) {
                throw ((RuntimeException) value);
            } else if (value instanceof Error) {
                throw ((Error) value);
            } else if ((value instanceof IOException) || (value instanceof Exception)) {
                throw new RuntimeIoException((Throwable) (Exception) value);
            }
        }
        return value;
    }

    public boolean isRead() {
        Object value;
        if (!isDone() || (value = getValue()) == CLOSED || (value instanceof ExceptionHolder)) {
            return false;
        }
        return true;
    }

    public boolean isClosed() {
        if (!isDone() || getValue() != CLOSED) {
            return false;
        }
        return true;
    }

    public Throwable getException() {
        if (!isDone()) {
            return null;
        }
        Object value = getValue();
        if (value instanceof ExceptionHolder) {
            return ((ExceptionHolder) value).exception;
        }
        return null;
    }

    public void setClosed() {
        setValue(CLOSED);
    }

    public void setRead(Object obj) {
        if (obj != null) {
            setValue(obj);
            return;
        }
        throw new IllegalArgumentException("message");
    }

    public void setException(Throwable th) {
        if (th != null) {
            setValue(new ExceptionHolder(th));
            return;
        }
        throw new IllegalArgumentException("exception");
    }

    public ReadFuture await() throws InterruptedException {
        return (ReadFuture) super.await();
    }

    public ReadFuture awaitUninterruptibly() {
        return (ReadFuture) super.awaitUninterruptibly();
    }

    public ReadFuture addListener(IoFutureListener<?> ioFutureListener) {
        return (ReadFuture) super.addListener(ioFutureListener);
    }

    public ReadFuture removeListener(IoFutureListener<?> ioFutureListener) {
        return (ReadFuture) super.removeListener(ioFutureListener);
    }

    private static class ExceptionHolder {
        /* access modifiers changed from: private */
        public final Throwable exception;

        private ExceptionHolder(Throwable th) {
            this.exception = th;
        }
    }
}
