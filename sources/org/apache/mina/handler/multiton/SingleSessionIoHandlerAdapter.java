package org.apache.mina.handler.multiton;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

@Deprecated
public class SingleSessionIoHandlerAdapter implements SingleSessionIoHandler {
    private final IoSession session;

    public void exceptionCaught(Throwable th) throws Exception {
    }

    public void messageReceived(Object obj) throws Exception {
    }

    public void messageSent(Object obj) throws Exception {
    }

    public void sessionClosed() throws Exception {
    }

    public void sessionCreated() throws Exception {
    }

    public void sessionIdle(IdleStatus idleStatus) throws Exception {
    }

    public void sessionOpened() throws Exception {
    }

    public SingleSessionIoHandlerAdapter(IoSession ioSession) {
        if (ioSession != null) {
            this.session = ioSession;
            return;
        }
        throw new IllegalArgumentException("session");
    }

    /* access modifiers changed from: protected */
    public IoSession getSession() {
        return this.session;
    }
}
