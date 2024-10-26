package org.apache.mina.handler.demux;

import java.lang.Throwable;
import org.apache.mina.core.session.IoSession;

public interface ExceptionHandler<E extends Throwable> {
    public static final ExceptionHandler<Throwable> CLOSE = new ExceptionHandler<Throwable>() {
        public void exceptionCaught(IoSession ioSession, Throwable th) {
            ioSession.close(true);
        }
    };
    public static final ExceptionHandler<Throwable> NOOP = new ExceptionHandler<Throwable>() {
        public void exceptionCaught(IoSession ioSession, Throwable th) {
        }
    };

    void exceptionCaught(IoSession ioSession, E e) throws Exception;
}
