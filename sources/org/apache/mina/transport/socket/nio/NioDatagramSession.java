package org.apache.mina.transport.socket.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.DefaultTransportMetadata;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.TransportMetadata;
import org.apache.mina.transport.socket.DatagramSessionConfig;

class NioDatagramSession extends NioSession {
    static final TransportMetadata METADATA = new DefaultTransportMetadata("nio", "datagram", true, false, InetSocketAddress.class, DatagramSessionConfig.class, IoBuffer.class);
    private final InetSocketAddress localAddress;
    private final InetSocketAddress remoteAddress;

    NioDatagramSession(IoService ioService, DatagramChannel datagramChannel, IoProcessor<NioSession> ioProcessor, SocketAddress socketAddress) {
        super(ioProcessor, ioService, datagramChannel);
        this.config = new NioDatagramSessionConfig(datagramChannel);
        this.config.setAll(ioService.getSessionConfig());
        this.remoteAddress = (InetSocketAddress) socketAddress;
        this.localAddress = (InetSocketAddress) datagramChannel.socket().getLocalSocketAddress();
    }

    NioDatagramSession(IoService ioService, DatagramChannel datagramChannel, IoProcessor<NioSession> ioProcessor) {
        this(ioService, datagramChannel, ioProcessor, datagramChannel.socket().getRemoteSocketAddress());
    }

    public DatagramSessionConfig getConfig() {
        return (DatagramSessionConfig) this.config;
    }

    /* access modifiers changed from: package-private */
    public DatagramChannel getChannel() {
        return (DatagramChannel) this.channel;
    }

    public TransportMetadata getTransportMetadata() {
        return METADATA;
    }

    public InetSocketAddress getRemoteAddress() {
        return this.remoteAddress;
    }

    public InetSocketAddress getLocalAddress() {
        return this.localAddress;
    }

    public InetSocketAddress getServiceAddress() {
        return (InetSocketAddress) super.getServiceAddress();
    }
}
