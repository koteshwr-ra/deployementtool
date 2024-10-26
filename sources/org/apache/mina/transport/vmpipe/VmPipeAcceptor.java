package org.apache.mina.transport.vmpipe;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.service.AbstractIoAcceptor;
import org.apache.mina.core.service.TransportMetadata;
import org.apache.mina.core.session.IdleStatusChecker;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionInitializer;

public final class VmPipeAcceptor extends AbstractIoAcceptor {
    static final Map<VmPipeAddress, VmPipe> boundHandlers = new HashMap();
    private IdleStatusChecker idleChecker;

    public VmPipeAcceptor() {
        this((Executor) null);
    }

    public VmPipeAcceptor(Executor executor) {
        super(new DefaultVmPipeSessionConfig(), executor);
        IdleStatusChecker idleStatusChecker = new IdleStatusChecker();
        this.idleChecker = idleStatusChecker;
        executeWorker(idleStatusChecker.getNotifyingTask(), "idleStatusChecker");
    }

    public TransportMetadata getTransportMetadata() {
        return VmPipeSession.METADATA;
    }

    public VmPipeSessionConfig getSessionConfig() {
        return (VmPipeSessionConfig) super.getSessionConfig();
    }

    public VmPipeAddress getLocalAddress() {
        return (VmPipeAddress) super.getLocalAddress();
    }

    public VmPipeAddress getDefaultLocalAddress() {
        return (VmPipeAddress) super.getDefaultLocalAddress();
    }

    public void setDefaultLocalAddress(VmPipeAddress vmPipeAddress) {
        super.setDefaultLocalAddress(vmPipeAddress);
    }

    /* access modifiers changed from: protected */
    public void dispose0() throws Exception {
        this.idleChecker.getNotifyingTask().cancel();
        unbind();
    }

    /* access modifiers changed from: protected */
    public Set<SocketAddress> bindInternal(List<? extends SocketAddress> list) throws IOException {
        HashSet<SocketAddress> hashSet = new HashSet<>();
        synchronized (boundHandlers) {
            for (SocketAddress socketAddress : list) {
                VmPipeAddress vmPipeAddress = (VmPipeAddress) socketAddress;
                if (vmPipeAddress != null) {
                    if (vmPipeAddress.getPort() != 0) {
                        if (vmPipeAddress.getPort() < 0) {
                            throw new IOException("Bind port number must be 0 or above.");
                        } else if (!boundHandlers.containsKey(vmPipeAddress)) {
                            hashSet.add(vmPipeAddress);
                        } else {
                            throw new IOException("Address already bound: " + vmPipeAddress);
                        }
                    }
                }
                vmPipeAddress = null;
                int i = 10000;
                while (true) {
                    if (i >= Integer.MAX_VALUE) {
                        break;
                    }
                    VmPipeAddress vmPipeAddress2 = new VmPipeAddress(i);
                    if (!boundHandlers.containsKey(vmPipeAddress2) && !hashSet.contains(vmPipeAddress2)) {
                        vmPipeAddress = vmPipeAddress2;
                        break;
                    }
                    i++;
                }
                if (vmPipeAddress != null) {
                    hashSet.add(vmPipeAddress);
                } else {
                    throw new IOException("No port available.");
                }
            }
            for (SocketAddress socketAddress2 : hashSet) {
                VmPipeAddress vmPipeAddress3 = (VmPipeAddress) socketAddress2;
                if (!boundHandlers.containsKey(vmPipeAddress3)) {
                    boundHandlers.put(vmPipeAddress3, new VmPipe(this, vmPipeAddress3, getHandler(), getListeners()));
                } else {
                    for (SocketAddress remove : hashSet) {
                        boundHandlers.remove(remove);
                    }
                    throw new IOException("Duplicate local address: " + socketAddress2);
                }
            }
        }
        return hashSet;
    }

    /* access modifiers changed from: protected */
    public void unbind0(List<? extends SocketAddress> list) {
        synchronized (boundHandlers) {
            for (SocketAddress remove : list) {
                boundHandlers.remove(remove);
            }
        }
    }

    public IoSession newSession(SocketAddress socketAddress, SocketAddress socketAddress2) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public void doFinishSessionInitialization(IoSession ioSession, IoFuture ioFuture) {
        initSession(ioSession, ioFuture, (IoSessionInitializer) null);
    }
}
