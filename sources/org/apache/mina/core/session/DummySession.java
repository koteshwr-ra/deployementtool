package org.apache.mina.core.session;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import java.net.SocketAddress;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import org.apache.log4j.spi.LocationInfo;
import org.apache.mina.core.file.FileRegion;
import org.apache.mina.core.filterchain.DefaultIoFilterChain;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.service.AbstractIoAcceptor;
import org.apache.mina.core.service.DefaultTransportMetadata;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.TransportMetadata;
import org.apache.mina.core.write.WriteRequest;

public class DummySession extends AbstractIoSession {
    private static final SocketAddress ANONYMOUS_ADDRESS = new SocketAddress() {
        private static final long serialVersionUID = -496112902353454179L;

        public String toString() {
            return LocationInfo.NA;
        }
    };
    /* access modifiers changed from: private */
    public static final TransportMetadata TRANSPORT_METADATA = new DefaultTransportMetadata("mina", "dummy", false, false, SocketAddress.class, IoSessionConfig.class, Object.class);
    private volatile IoSessionConfig config = new AbstractIoSessionConfig() {
        /* access modifiers changed from: protected */
        public void doSetAll(IoSessionConfig ioSessionConfig) {
        }
    };
    private final IoFilterChain filterChain = new DefaultIoFilterChain(this);
    private volatile IoHandler handler = new IoHandlerAdapter();
    private volatile SocketAddress localAddress = ANONYMOUS_ADDRESS;
    private final IoProcessor<AbstractIoSession> processor = new IoProcessor<AbstractIoSession>() {
        public void add(AbstractIoSession abstractIoSession) {
        }

        public void dispose() {
        }

        public boolean isDisposed() {
            return false;
        }

        public boolean isDisposing() {
            return false;
        }

        public void updateTrafficControl(AbstractIoSession abstractIoSession) {
        }

        public void flush(AbstractIoSession abstractIoSession) {
            DummySession dummySession = (DummySession) abstractIoSession;
            WriteRequest poll = dummySession.getWriteRequestQueue().poll(abstractIoSession);
            if (poll != null) {
                Object message = poll.getMessage();
                if (message instanceof FileRegion) {
                    FileRegion fileRegion = (FileRegion) message;
                    try {
                        fileRegion.getFileChannel().position(fileRegion.getPosition() + fileRegion.getRemainingBytes());
                        fileRegion.update(fileRegion.getRemainingBytes());
                    } catch (IOException e) {
                        dummySession.getFilterChain().fireExceptionCaught(e);
                    }
                }
                DummySession.this.getFilterChain().fireMessageSent(poll);
            }
        }

        public void remove(AbstractIoSession abstractIoSession) {
            if (!abstractIoSession.getCloseFuture().isClosed()) {
                abstractIoSession.getFilterChain().fireSessionClosed();
            }
        }
    };
    private volatile SocketAddress remoteAddress = ANONYMOUS_ADDRESS;
    private volatile IoService service = super.getService();
    private volatile TransportMetadata transportMetadata = TRANSPORT_METADATA;

    public DummySession() {
        super(new AbstractIoAcceptor(new AbstractIoSessionConfig() {
            /* access modifiers changed from: protected */
            public void doSetAll(IoSessionConfig ioSessionConfig) {
            }
        }, new Executor() {
            public void execute(Runnable runnable) {
            }
        }) {
            /* access modifiers changed from: protected */
            public void dispose0() throws Exception {
            }

            /* access modifiers changed from: protected */
            public Set<SocketAddress> bindInternal(List<? extends SocketAddress> list) throws Exception {
                throw new UnsupportedOperationException();
            }

            /* access modifiers changed from: protected */
            public void unbind0(List<? extends SocketAddress> list) throws Exception {
                throw new UnsupportedOperationException();
            }

            public IoSession newSession(SocketAddress socketAddress, SocketAddress socketAddress2) {
                throw new UnsupportedOperationException();
            }

            public TransportMetadata getTransportMetadata() {
                return DummySession.TRANSPORT_METADATA;
            }
        });
        try {
            DefaultIoSessionDataStructureFactory defaultIoSessionDataStructureFactory = new DefaultIoSessionDataStructureFactory();
            setAttributeMap(defaultIoSessionDataStructureFactory.getAttributeMap(this));
            setWriteRequestQueue(defaultIoSessionDataStructureFactory.getWriteRequestQueue(this));
        } catch (Exception unused) {
            throw new InternalError();
        }
    }

    public IoSessionConfig getConfig() {
        return this.config;
    }

    public void setConfig(IoSessionConfig ioSessionConfig) {
        if (ioSessionConfig != null) {
            this.config = ioSessionConfig;
            return;
        }
        throw new IllegalArgumentException("config");
    }

    public IoFilterChain getFilterChain() {
        return this.filterChain;
    }

    public IoHandler getHandler() {
        return this.handler;
    }

    public void setHandler(IoHandler ioHandler) {
        if (ioHandler != null) {
            this.handler = ioHandler;
            return;
        }
        throw new IllegalArgumentException("handler");
    }

    public SocketAddress getLocalAddress() {
        return this.localAddress;
    }

    public SocketAddress getRemoteAddress() {
        return this.remoteAddress;
    }

    public void setLocalAddress(SocketAddress socketAddress) {
        if (socketAddress != null) {
            this.localAddress = socketAddress;
            return;
        }
        throw new IllegalArgumentException("localAddress");
    }

    public void setRemoteAddress(SocketAddress socketAddress) {
        if (socketAddress != null) {
            this.remoteAddress = socketAddress;
            return;
        }
        throw new IllegalArgumentException("remoteAddress");
    }

    public IoService getService() {
        return this.service;
    }

    public void setService(IoService ioService) {
        if (ioService != null) {
            this.service = ioService;
            return;
        }
        throw new IllegalArgumentException(NotificationCompat.CATEGORY_SERVICE);
    }

    public final IoProcessor<AbstractIoSession> getProcessor() {
        return this.processor;
    }

    public TransportMetadata getTransportMetadata() {
        return this.transportMetadata;
    }

    public void setTransportMetadata(TransportMetadata transportMetadata2) {
        if (transportMetadata2 != null) {
            this.transportMetadata = transportMetadata2;
            return;
        }
        throw new IllegalArgumentException("transportMetadata");
    }

    public void setScheduledWriteBytes(int i) {
        super.setScheduledWriteBytes(i);
    }

    public void setScheduledWriteMessages(int i) {
        super.setScheduledWriteMessages(i);
    }

    public void updateThroughput(boolean z) {
        super.updateThroughput(System.currentTimeMillis(), z);
    }
}
