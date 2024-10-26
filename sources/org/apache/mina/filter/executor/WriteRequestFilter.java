package org.apache.mina.filter.executor;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoEvent;
import org.apache.mina.core.session.IoEventType;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

public class WriteRequestFilter extends IoFilterAdapter {
    /* access modifiers changed from: private */
    public final IoEventQueueHandler queueHandler;

    public WriteRequestFilter() {
        this(new IoEventQueueThrottle());
    }

    public WriteRequestFilter(IoEventQueueHandler ioEventQueueHandler) {
        if (ioEventQueueHandler != null) {
            this.queueHandler = ioEventQueueHandler;
            return;
        }
        throw new IllegalArgumentException("queueHandler");
    }

    public IoEventQueueHandler getQueueHandler() {
        return this.queueHandler;
    }

    public void filterWrite(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        final IoEvent ioEvent = new IoEvent(IoEventType.WRITE, ioSession, writeRequest);
        if (this.queueHandler.accept(this, ioEvent)) {
            nextFilter.filterWrite(ioSession, writeRequest);
            WriteFuture future = writeRequest.getFuture();
            if (future != null) {
                this.queueHandler.offered(this, ioEvent);
                future.addListener(new IoFutureListener<WriteFuture>() {
                    public void operationComplete(WriteFuture writeFuture) {
                        WriteRequestFilter.this.queueHandler.polled(WriteRequestFilter.this, ioEvent);
                    }
                });
            }
        }
    }
}
