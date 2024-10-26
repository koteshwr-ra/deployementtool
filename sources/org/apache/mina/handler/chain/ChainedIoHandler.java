package org.apache.mina.handler.chain;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.chain.IoHandlerCommand;

public class ChainedIoHandler extends IoHandlerAdapter {
    private final IoHandlerChain chain;

    public ChainedIoHandler() {
        this.chain = new IoHandlerChain();
    }

    public ChainedIoHandler(IoHandlerChain ioHandlerChain) {
        if (ioHandlerChain != null) {
            this.chain = ioHandlerChain;
            return;
        }
        throw new IllegalArgumentException("chain");
    }

    public IoHandlerChain getChain() {
        return this.chain;
    }

    public void messageReceived(IoSession ioSession, Object obj) throws Exception {
        this.chain.execute((IoHandlerCommand.NextCommand) null, ioSession, obj);
    }
}
