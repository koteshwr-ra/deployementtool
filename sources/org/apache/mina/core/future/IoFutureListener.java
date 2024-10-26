package org.apache.mina.core.future;

import java.util.EventListener;
import org.apache.mina.core.future.IoFuture;

public interface IoFutureListener<F extends IoFuture> extends EventListener {
    public static final IoFutureListener<IoFuture> CLOSE = new IoFutureListener<IoFuture>() {
        public void operationComplete(IoFuture ioFuture) {
            ioFuture.getSession().close(true);
        }
    };

    void operationComplete(F f);
}
