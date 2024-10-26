package org.apache.mina.core.service;

import java.net.SocketAddress;
import java.util.concurrent.Executor;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.core.session.IoSessionInitializer;

public abstract class AbstractIoConnector extends AbstractIoService implements IoConnector {
    private long connectTimeoutCheckInterval = 50;
    private long connectTimeoutInMillis = 60000;
    private SocketAddress defaultRemoteAddress;

    /* access modifiers changed from: protected */
    public abstract ConnectFuture connect0(SocketAddress socketAddress, SocketAddress socketAddress2, IoSessionInitializer<? extends ConnectFuture> ioSessionInitializer);

    protected AbstractIoConnector(IoSessionConfig ioSessionConfig, Executor executor) {
        super(ioSessionConfig, executor);
    }

    public long getConnectTimeoutCheckInterval() {
        return this.connectTimeoutCheckInterval;
    }

    public void setConnectTimeoutCheckInterval(long j) {
        if (getConnectTimeoutMillis() < j) {
            this.connectTimeoutInMillis = j;
        }
        this.connectTimeoutCheckInterval = j;
    }

    public final int getConnectTimeout() {
        return ((int) this.connectTimeoutInMillis) / 1000;
    }

    public final long getConnectTimeoutMillis() {
        return this.connectTimeoutInMillis;
    }

    public final void setConnectTimeout(int i) {
        setConnectTimeoutMillis(((long) i) * 1000);
    }

    public final void setConnectTimeoutMillis(long j) {
        if (j <= this.connectTimeoutCheckInterval) {
            this.connectTimeoutCheckInterval = j;
        }
        this.connectTimeoutInMillis = j;
    }

    public SocketAddress getDefaultRemoteAddress() {
        return this.defaultRemoteAddress;
    }

    public final void setDefaultRemoteAddress(SocketAddress socketAddress) {
        if (socketAddress == null) {
            throw new IllegalArgumentException("defaultRemoteAddress");
        } else if (getTransportMetadata().getAddressType().isAssignableFrom(socketAddress.getClass())) {
            this.defaultRemoteAddress = socketAddress;
        } else {
            throw new IllegalArgumentException("defaultRemoteAddress type: " + socketAddress.getClass() + " (expected: " + getTransportMetadata().getAddressType() + ")");
        }
    }

    public final ConnectFuture connect() {
        SocketAddress defaultRemoteAddress2 = getDefaultRemoteAddress();
        if (defaultRemoteAddress2 != null) {
            return connect(defaultRemoteAddress2, (SocketAddress) null, (IoSessionInitializer<? extends ConnectFuture>) null);
        }
        throw new IllegalStateException("defaultRemoteAddress is not set.");
    }

    public ConnectFuture connect(IoSessionInitializer<? extends ConnectFuture> ioSessionInitializer) {
        SocketAddress defaultRemoteAddress2 = getDefaultRemoteAddress();
        if (defaultRemoteAddress2 != null) {
            return connect(defaultRemoteAddress2, (SocketAddress) null, ioSessionInitializer);
        }
        throw new IllegalStateException("defaultRemoteAddress is not set.");
    }

    public final ConnectFuture connect(SocketAddress socketAddress) {
        return connect(socketAddress, (SocketAddress) null, (IoSessionInitializer<? extends ConnectFuture>) null);
    }

    public ConnectFuture connect(SocketAddress socketAddress, IoSessionInitializer<? extends ConnectFuture> ioSessionInitializer) {
        return connect(socketAddress, (SocketAddress) null, ioSessionInitializer);
    }

    public ConnectFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2) {
        return connect(socketAddress, socketAddress2, (IoSessionInitializer<? extends ConnectFuture>) null);
    }

    public final ConnectFuture connect(SocketAddress socketAddress, SocketAddress socketAddress2, IoSessionInitializer<? extends ConnectFuture> ioSessionInitializer) {
        if (isDisposing()) {
            throw new IllegalStateException("The connector has been disposed.");
        } else if (socketAddress == null) {
            throw new IllegalArgumentException("remoteAddress");
        } else if (!getTransportMetadata().getAddressType().isAssignableFrom(socketAddress.getClass())) {
            throw new IllegalArgumentException("remoteAddress type: " + socketAddress.getClass() + " (expected: " + getTransportMetadata().getAddressType() + ")");
        } else if (socketAddress2 == null || getTransportMetadata().getAddressType().isAssignableFrom(socketAddress2.getClass())) {
            if (getHandler() == null) {
                if (getSessionConfig().isUseReadOperation()) {
                    setHandler(new IoHandler() {
                        public void exceptionCaught(IoSession ioSession, Throwable th) throws Exception {
                        }

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
                    });
                } else {
                    throw new IllegalStateException("handler is not set.");
                }
            }
            return connect0(socketAddress, socketAddress2, ioSessionInitializer);
        } else {
            throw new IllegalArgumentException("localAddress type: " + socketAddress2.getClass() + " (expected: " + getTransportMetadata().getAddressType() + ")");
        }
    }

    /* access modifiers changed from: protected */
    public final void finishSessionInitialization0(final IoSession ioSession, IoFuture ioFuture) {
        ioFuture.addListener(new IoFutureListener<ConnectFuture>() {
            public void operationComplete(ConnectFuture connectFuture) {
                if (connectFuture.isCanceled()) {
                    ioSession.close(true);
                }
            }
        });
    }

    public String toString() {
        TransportMetadata transportMetadata = getTransportMetadata();
        return '(' + transportMetadata.getProviderName() + ' ' + transportMetadata.getName() + " connector: " + "managedSessionCount: " + getManagedSessionCount() + ')';
    }
}
