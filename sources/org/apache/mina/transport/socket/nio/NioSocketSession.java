package org.apache.mina.transport.socket.nio;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.file.FileRegion;
import org.apache.mina.core.service.DefaultTransportMetadata;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.TransportMetadata;
import org.apache.mina.transport.socket.AbstractSocketSessionConfig;
import org.apache.mina.transport.socket.SocketSessionConfig;

class NioSocketSession extends NioSession {
    static final TransportMetadata METADATA = new DefaultTransportMetadata("nio", "socket", false, true, InetSocketAddress.class, SocketSessionConfig.class, IoBuffer.class, FileRegion.class);

    /* access modifiers changed from: private */
    public Socket getSocket() {
        return ((SocketChannel) this.channel).socket();
    }

    public NioSocketSession(IoService ioService, IoProcessor<NioSession> ioProcessor, SocketChannel socketChannel) {
        super(ioProcessor, ioService, socketChannel);
        this.config = new SessionConfigImpl();
        this.config.setAll(ioService.getSessionConfig());
    }

    public TransportMetadata getTransportMetadata() {
        return METADATA;
    }

    public SocketSessionConfig getConfig() {
        return (SocketSessionConfig) this.config;
    }

    /* access modifiers changed from: package-private */
    public SocketChannel getChannel() {
        return (SocketChannel) this.channel;
    }

    public InetSocketAddress getRemoteAddress() {
        Socket socket;
        if (this.channel == null || (socket = getSocket()) == null) {
            return null;
        }
        return (InetSocketAddress) socket.getRemoteSocketAddress();
    }

    public InetSocketAddress getLocalAddress() {
        Socket socket;
        if (this.channel == null || (socket = getSocket()) == null) {
            return null;
        }
        return (InetSocketAddress) socket.getLocalSocketAddress();
    }

    public InetSocketAddress getServiceAddress() {
        return (InetSocketAddress) super.getServiceAddress();
    }

    private class SessionConfigImpl extends AbstractSocketSessionConfig {
        private SessionConfigImpl() {
        }

        public boolean isKeepAlive() {
            try {
                return NioSocketSession.this.getSocket().getKeepAlive();
            } catch (SocketException e) {
                throw new RuntimeIoException((Throwable) e);
            }
        }

        public void setKeepAlive(boolean z) {
            try {
                NioSocketSession.this.getSocket().setKeepAlive(z);
            } catch (SocketException e) {
                throw new RuntimeIoException((Throwable) e);
            }
        }

        public boolean isOobInline() {
            try {
                return NioSocketSession.this.getSocket().getOOBInline();
            } catch (SocketException e) {
                throw new RuntimeIoException((Throwable) e);
            }
        }

        public void setOobInline(boolean z) {
            try {
                NioSocketSession.this.getSocket().setOOBInline(z);
            } catch (SocketException e) {
                throw new RuntimeIoException((Throwable) e);
            }
        }

        public boolean isReuseAddress() {
            try {
                return NioSocketSession.this.getSocket().getReuseAddress();
            } catch (SocketException e) {
                throw new RuntimeIoException((Throwable) e);
            }
        }

        public void setReuseAddress(boolean z) {
            try {
                NioSocketSession.this.getSocket().setReuseAddress(z);
            } catch (SocketException e) {
                throw new RuntimeIoException((Throwable) e);
            }
        }

        public int getSoLinger() {
            try {
                return NioSocketSession.this.getSocket().getSoLinger();
            } catch (SocketException e) {
                throw new RuntimeIoException((Throwable) e);
            }
        }

        public void setSoLinger(int i) {
            if (i < 0) {
                try {
                    NioSocketSession.this.getSocket().setSoLinger(false, 0);
                } catch (SocketException e) {
                    throw new RuntimeIoException((Throwable) e);
                }
            } else {
                NioSocketSession.this.getSocket().setSoLinger(true, i);
            }
        }

        public boolean isTcpNoDelay() {
            if (!NioSocketSession.this.isConnected()) {
                return false;
            }
            try {
                return NioSocketSession.this.getSocket().getTcpNoDelay();
            } catch (SocketException e) {
                throw new RuntimeIoException((Throwable) e);
            }
        }

        public void setTcpNoDelay(boolean z) {
            try {
                NioSocketSession.this.getSocket().setTcpNoDelay(z);
            } catch (SocketException e) {
                throw new RuntimeIoException((Throwable) e);
            }
        }

        public int getTrafficClass() {
            try {
                return NioSocketSession.this.getSocket().getTrafficClass();
            } catch (SocketException e) {
                throw new RuntimeIoException((Throwable) e);
            }
        }

        public void setTrafficClass(int i) {
            try {
                NioSocketSession.this.getSocket().setTrafficClass(i);
            } catch (SocketException e) {
                throw new RuntimeIoException((Throwable) e);
            }
        }

        public int getSendBufferSize() {
            try {
                return NioSocketSession.this.getSocket().getSendBufferSize();
            } catch (SocketException e) {
                throw new RuntimeIoException((Throwable) e);
            }
        }

        public void setSendBufferSize(int i) {
            try {
                NioSocketSession.this.getSocket().setSendBufferSize(i);
            } catch (SocketException e) {
                throw new RuntimeIoException((Throwable) e);
            }
        }

        public int getReceiveBufferSize() {
            try {
                return NioSocketSession.this.getSocket().getReceiveBufferSize();
            } catch (SocketException e) {
                throw new RuntimeIoException((Throwable) e);
            }
        }

        public void setReceiveBufferSize(int i) {
            try {
                NioSocketSession.this.getSocket().setReceiveBufferSize(i);
            } catch (SocketException e) {
                throw new RuntimeIoException((Throwable) e);
            }
        }
    }
}
