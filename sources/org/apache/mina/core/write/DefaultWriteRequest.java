package org.apache.mina.core.write;

import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;

public class DefaultWriteRequest implements WriteRequest {
    private static final WriteFuture UNUSED_FUTURE = new WriteFuture() {
        public WriteFuture await() throws InterruptedException {
            return this;
        }

        public boolean await(long j) throws InterruptedException {
            return true;
        }

        public boolean await(long j, TimeUnit timeUnit) throws InterruptedException {
            return true;
        }

        public WriteFuture awaitUninterruptibly() {
            return this;
        }

        public boolean awaitUninterruptibly(long j) {
            return true;
        }

        public boolean awaitUninterruptibly(long j, TimeUnit timeUnit) {
            return true;
        }

        public Throwable getException() {
            return null;
        }

        public IoSession getSession() {
            return null;
        }

        public boolean isDone() {
            return true;
        }

        public boolean isWritten() {
            return false;
        }

        public void join() {
        }

        public boolean join(long j) {
            return true;
        }

        public void setException(Throwable th) {
        }

        public void setWritten() {
        }

        public WriteFuture addListener(IoFutureListener<?> ioFutureListener) {
            throw new IllegalStateException("You can't add a listener to a dummy future.");
        }

        public WriteFuture removeListener(IoFutureListener<?> ioFutureListener) {
            throw new IllegalStateException("You can't add a listener to a dummy future.");
        }
    };
    private final SocketAddress destination;
    private final WriteFuture future;
    private final Object message;

    public WriteRequest getOriginalRequest() {
        return this;
    }

    public boolean isEncoded() {
        return false;
    }

    public DefaultWriteRequest(Object obj) {
        this(obj, (WriteFuture) null, (SocketAddress) null);
    }

    public DefaultWriteRequest(Object obj, WriteFuture writeFuture) {
        this(obj, writeFuture, (SocketAddress) null);
    }

    public DefaultWriteRequest(Object obj, WriteFuture writeFuture, SocketAddress socketAddress) {
        if (obj != null) {
            writeFuture = writeFuture == null ? UNUSED_FUTURE : writeFuture;
            this.message = obj;
            this.future = writeFuture;
            this.destination = socketAddress;
            return;
        }
        throw new IllegalArgumentException("message");
    }

    public WriteFuture getFuture() {
        return this.future;
    }

    public Object getMessage() {
        return this.message;
    }

    public SocketAddress getDestination() {
        return this.destination;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WriteRequest: ");
        if (this.message.getClass().getName().equals(Object.class.getName())) {
            sb.append("CLOSE_REQUEST");
        } else if (getDestination() == null) {
            sb.append(this.message);
        } else {
            sb.append(this.message);
            sb.append(" => ");
            sb.append(getDestination());
        }
        return sb.toString();
    }
}
