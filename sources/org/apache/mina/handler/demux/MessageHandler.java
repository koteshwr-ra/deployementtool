package org.apache.mina.handler.demux;

import org.apache.mina.core.session.IoSession;

public interface MessageHandler<E> {
    public static final MessageHandler<Object> NOOP = new MessageHandler<Object>() {
        public void handleMessage(IoSession ioSession, Object obj) {
        }
    };

    void handleMessage(IoSession ioSession, E e) throws Exception;
}
