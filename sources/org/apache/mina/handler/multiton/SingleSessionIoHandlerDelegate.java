package org.apache.mina.handler.multiton;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

@Deprecated
public class SingleSessionIoHandlerDelegate implements IoHandler {
    public static final AttributeKey HANDLER = new AttributeKey(SingleSessionIoHandlerDelegate.class, "handler");
    private final SingleSessionIoHandlerFactory factory;

    public SingleSessionIoHandlerDelegate(SingleSessionIoHandlerFactory singleSessionIoHandlerFactory) {
        if (singleSessionIoHandlerFactory != null) {
            this.factory = singleSessionIoHandlerFactory;
            return;
        }
        throw new IllegalArgumentException("factory");
    }

    public SingleSessionIoHandlerFactory getFactory() {
        return this.factory;
    }

    public void sessionCreated(IoSession ioSession) throws Exception {
        SingleSessionIoHandler handler = this.factory.getHandler(ioSession);
        ioSession.setAttribute(HANDLER, handler);
        handler.sessionCreated();
    }

    public void sessionOpened(IoSession ioSession) throws Exception {
        ((SingleSessionIoHandler) ioSession.getAttribute(HANDLER)).sessionOpened();
    }

    public void sessionClosed(IoSession ioSession) throws Exception {
        ((SingleSessionIoHandler) ioSession.getAttribute(HANDLER)).sessionClosed();
    }

    public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) throws Exception {
        ((SingleSessionIoHandler) ioSession.getAttribute(HANDLER)).sessionIdle(idleStatus);
    }

    public void exceptionCaught(IoSession ioSession, Throwable th) throws Exception {
        ((SingleSessionIoHandler) ioSession.getAttribute(HANDLER)).exceptionCaught(th);
    }

    public void messageReceived(IoSession ioSession, Object obj) throws Exception {
        ((SingleSessionIoHandler) ioSession.getAttribute(HANDLER)).messageReceived(obj);
    }

    public void messageSent(IoSession ioSession, Object obj) throws Exception {
        ((SingleSessionIoHandler) ioSession.getAttribute(HANDLER)).messageSent(obj);
    }
}
