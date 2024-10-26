package org.apache.mina.transport.socket.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Executor;
import org.apache.mina.core.polling.AbstractPollingIoAcceptor;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.service.TransportMetadata;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.transport.socket.DefaultSocketSessionConfig;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.SocketSessionConfig;

public final class NioSocketAcceptor extends AbstractPollingIoAcceptor<NioSession, ServerSocketChannel> implements SocketAcceptor {
    private volatile Selector selector;

    public NioSocketAcceptor() {
        super((IoSessionConfig) new DefaultSocketSessionConfig(), NioProcessor.class);
        ((DefaultSocketSessionConfig) getSessionConfig()).init(this);
    }

    public NioSocketAcceptor(int i) {
        super((IoSessionConfig) new DefaultSocketSessionConfig(), NioProcessor.class, i);
        ((DefaultSocketSessionConfig) getSessionConfig()).init(this);
    }

    public NioSocketAcceptor(IoProcessor<NioSession> ioProcessor) {
        super((IoSessionConfig) new DefaultSocketSessionConfig(), ioProcessor);
        ((DefaultSocketSessionConfig) getSessionConfig()).init(this);
    }

    public NioSocketAcceptor(Executor executor, IoProcessor<NioSession> ioProcessor) {
        super((IoSessionConfig) new DefaultSocketSessionConfig(), executor, ioProcessor);
        ((DefaultSocketSessionConfig) getSessionConfig()).init(this);
    }

    /* access modifiers changed from: protected */
    public void init() throws Exception {
        this.selector = Selector.open();
    }

    /* access modifiers changed from: protected */
    public void destroy() throws Exception {
        if (this.selector != null) {
            this.selector.close();
        }
    }

    public TransportMetadata getTransportMetadata() {
        return NioSocketSession.METADATA;
    }

    public SocketSessionConfig getSessionConfig() {
        return (SocketSessionConfig) super.getSessionConfig();
    }

    public InetSocketAddress getLocalAddress() {
        return (InetSocketAddress) super.getLocalAddress();
    }

    public InetSocketAddress getDefaultLocalAddress() {
        return (InetSocketAddress) super.getDefaultLocalAddress();
    }

    public void setDefaultLocalAddress(InetSocketAddress inetSocketAddress) {
        setDefaultLocalAddress(inetSocketAddress);
    }

    /* access modifiers changed from: protected */
    public NioSession accept(IoProcessor<NioSession> ioProcessor, ServerSocketChannel serverSocketChannel) throws Exception {
        SocketChannel accept;
        SelectionKey keyFor = serverSocketChannel.keyFor(this.selector);
        if (keyFor == null || !keyFor.isValid() || !keyFor.isAcceptable() || (accept = serverSocketChannel.accept()) == null) {
            return null;
        }
        return new NioSocketSession(this, ioProcessor, accept);
    }

    /* access modifiers changed from: protected */
    public ServerSocketChannel open(SocketAddress socketAddress) throws Exception {
        ServerSocketChannel open = ServerSocketChannel.open();
        try {
            open.configureBlocking(false);
            ServerSocket socket = open.socket();
            socket.setReuseAddress(isReuseAddress());
            socket.bind(socketAddress, getBacklog());
            open.register(this.selector, 16);
            return open;
        } catch (Throwable th) {
            close(open);
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public SocketAddress localAddress(ServerSocketChannel serverSocketChannel) throws Exception {
        return serverSocketChannel.socket().getLocalSocketAddress();
    }

    /* access modifiers changed from: protected */
    public int select() throws Exception {
        return this.selector.select();
    }

    /* access modifiers changed from: protected */
    public Iterator<ServerSocketChannel> selectedHandles() {
        return new ServerSocketChannelIterator(this.selector.selectedKeys());
    }

    /* access modifiers changed from: protected */
    public void close(ServerSocketChannel serverSocketChannel) throws Exception {
        SelectionKey keyFor = serverSocketChannel.keyFor(this.selector);
        if (keyFor != null) {
            keyFor.cancel();
        }
        serverSocketChannel.close();
    }

    /* access modifiers changed from: protected */
    public void wakeup() {
        this.selector.wakeup();
    }

    private static class ServerSocketChannelIterator implements Iterator<ServerSocketChannel> {
        private final Iterator<SelectionKey> iterator;

        private ServerSocketChannelIterator(Collection<SelectionKey> collection) {
            this.iterator = collection.iterator();
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public ServerSocketChannel next() {
            SelectionKey next = this.iterator.next();
            if (!next.isValid() || !next.isAcceptable()) {
                return null;
            }
            return (ServerSocketChannel) next.channel();
        }

        public void remove() {
            this.iterator.remove();
        }
    }
}
