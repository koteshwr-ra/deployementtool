package org.apache.mina.core.service;

import java.util.EventListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public interface IoServiceListener extends EventListener {
    void serviceActivated(IoService ioService) throws Exception;

    void serviceDeactivated(IoService ioService) throws Exception;

    void serviceIdle(IoService ioService, IdleStatus idleStatus) throws Exception;

    void sessionCreated(IoSession ioSession) throws Exception;

    void sessionDestroyed(IoSession ioSession) throws Exception;
}
