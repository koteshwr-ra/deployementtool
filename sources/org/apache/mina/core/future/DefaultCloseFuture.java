package org.apache.mina.core.future;

import org.apache.mina.core.session.IoSession;

public class DefaultCloseFuture extends DefaultIoFuture implements CloseFuture {
    public DefaultCloseFuture(IoSession ioSession) {
        super(ioSession);
    }

    public boolean isClosed() {
        if (isDone()) {
            return ((Boolean) getValue()).booleanValue();
        }
        return false;
    }

    public void setClosed() {
        setValue(Boolean.TRUE);
    }

    public CloseFuture await() throws InterruptedException {
        return (CloseFuture) super.await();
    }

    public CloseFuture awaitUninterruptibly() {
        return (CloseFuture) super.awaitUninterruptibly();
    }

    public CloseFuture addListener(IoFutureListener<?> ioFutureListener) {
        return (CloseFuture) super.addListener(ioFutureListener);
    }

    public CloseFuture removeListener(IoFutureListener<?> ioFutureListener) {
        return (CloseFuture) super.removeListener(ioFutureListener);
    }
}
