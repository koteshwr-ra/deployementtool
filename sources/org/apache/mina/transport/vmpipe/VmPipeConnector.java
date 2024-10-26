package org.apache.mina.transport.vmpipe;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.DefaultConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.AbstractIoConnector;
import org.apache.mina.core.service.TransportMetadata;
import org.apache.mina.core.session.IdleStatusChecker;
import org.apache.mina.core.session.IoSessionInitializer;
import org.apache.mina.util.ExceptionMonitor;

public final class VmPipeConnector extends AbstractIoConnector {
    private static final IoFutureListener<IoFuture> LOCAL_ADDRESS_RECLAIMER = new LocalAddressReclaimer();
    /* access modifiers changed from: private */
    public static final Set<VmPipeAddress> TAKEN_LOCAL_ADDRESSES = new HashSet();
    private static int nextLocalPort = -1;
    private IdleStatusChecker idleChecker;

    public VmPipeConnector() {
        this((Executor) null);
    }

    public VmPipeConnector(Executor executor) {
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

    /* access modifiers changed from: protected */
    public ConnectFuture connect0(SocketAddress socketAddress, SocketAddress socketAddress2, IoSessionInitializer<? extends ConnectFuture> ioSessionInitializer) {
        VmPipe vmPipe = VmPipeAcceptor.boundHandlers.get(socketAddress);
        if (vmPipe == null) {
            return DefaultConnectFuture.newFailedFuture(new IOException("Endpoint unavailable: " + socketAddress));
        }
        DefaultConnectFuture defaultConnectFuture = new DefaultConnectFuture();
        try {
            VmPipeSession vmPipeSession = new VmPipeSession(this, getListeners(), nextLocalAddress(), getHandler(), vmPipe);
            initSession(vmPipeSession, defaultConnectFuture, ioSessionInitializer);
            vmPipeSession.getCloseFuture().addListener(LOCAL_ADDRESS_RECLAIMER);
            try {
                getFilterChainBuilder().buildFilterChain(vmPipeSession.getFilterChain());
                getListeners().fireSessionCreated(vmPipeSession);
                this.idleChecker.addSession(vmPipeSession);
                VmPipeSession remoteSession = vmPipeSession.getRemoteSession();
                ((VmPipeAcceptor) remoteSession.getService()).doFinishSessionInitialization(remoteSession, (IoFuture) null);
                try {
                    vmPipe.getAcceptor().getFilterChainBuilder().buildFilterChain(remoteSession.getFilterChain());
                    vmPipe.getListeners().fireSessionCreated(remoteSession);
                    this.idleChecker.addSession(remoteSession);
                } catch (Throwable th) {
                    ExceptionMonitor.getInstance().exceptionCaught(th);
                    remoteSession.close(true);
                }
                ((VmPipeFilterChain) vmPipeSession.getFilterChain()).start();
                ((VmPipeFilterChain) remoteSession.getFilterChain()).start();
                return defaultConnectFuture;
            } catch (Throwable th2) {
                defaultConnectFuture.setException(th2);
                return defaultConnectFuture;
            }
        } catch (IOException e) {
            return DefaultConnectFuture.newFailedFuture(e);
        }
    }

    /* access modifiers changed from: protected */
    public void dispose0() throws Exception {
        this.idleChecker.getNotifyingTask().cancel();
    }

    private static VmPipeAddress nextLocalAddress() throws IOException {
        synchronized (TAKEN_LOCAL_ADDRESSES) {
            if (nextLocalPort >= 0) {
                nextLocalPort = -1;
            }
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                int i2 = nextLocalPort;
                nextLocalPort = i2 - 1;
                VmPipeAddress vmPipeAddress = new VmPipeAddress(i2);
                if (!TAKEN_LOCAL_ADDRESSES.contains(vmPipeAddress)) {
                    TAKEN_LOCAL_ADDRESSES.add(vmPipeAddress);
                    return vmPipeAddress;
                }
            }
            throw new IOException("Can't assign a local VM pipe port.");
        }
    }

    private static class LocalAddressReclaimer implements IoFutureListener<IoFuture> {
        private LocalAddressReclaimer() {
        }

        public void operationComplete(IoFuture ioFuture) {
            synchronized (VmPipeConnector.TAKEN_LOCAL_ADDRESSES) {
                VmPipeConnector.TAKEN_LOCAL_ADDRESSES.remove(ioFuture.getSession().getLocalAddress());
            }
        }
    }
}
