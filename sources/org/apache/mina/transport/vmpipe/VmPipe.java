package org.apache.mina.transport.vmpipe;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoServiceListenerSupport;

class VmPipe {
    private final VmPipeAcceptor acceptor;
    private final VmPipeAddress address;
    private final IoHandler handler;
    private final IoServiceListenerSupport listeners;

    VmPipe(VmPipeAcceptor vmPipeAcceptor, VmPipeAddress vmPipeAddress, IoHandler ioHandler, IoServiceListenerSupport ioServiceListenerSupport) {
        this.acceptor = vmPipeAcceptor;
        this.address = vmPipeAddress;
        this.handler = ioHandler;
        this.listeners = ioServiceListenerSupport;
    }

    public VmPipeAcceptor getAcceptor() {
        return this.acceptor;
    }

    public VmPipeAddress getAddress() {
        return this.address;
    }

    public IoHandler getHandler() {
        return this.handler;
    }

    public IoServiceListenerSupport getListeners() {
        return this.listeners;
    }
}
