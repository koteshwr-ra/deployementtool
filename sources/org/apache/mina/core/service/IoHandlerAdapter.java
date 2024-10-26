package org.apache.mina.core.service;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IoHandlerAdapter implements IoHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(IoHandlerAdapter.class);

    public void messageReceived(IoSession ioSession, Object obj) throws Exception {
    }

    public void messageSent(IoSession ioSession, Object obj) throws Exception {
    }

    public void sessionClosed(IoSession ioSession) throws Exception {
    }

    public void sessionCreated(IoSession ioSession) throws Exception {
    }

    public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) throws Exception {
    }

    public void sessionOpened(IoSession ioSession) throws Exception {
    }

    public void exceptionCaught(IoSession ioSession, Throwable th) throws Exception {
        if (LOGGER.isWarnEnabled()) {
            Logger logger = LOGGER;
            logger.warn("EXCEPTION, please implement " + getClass().getName() + ".exceptionCaught() for proper handling:", th);
        }
    }
}
