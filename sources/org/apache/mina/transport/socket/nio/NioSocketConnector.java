package org.apache.mina.transport.socket.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Executor;
import org.apache.mina.core.polling.AbstractPollingIoConnector;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.service.TransportMetadata;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.transport.socket.DefaultSocketSessionConfig;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.SocketSessionConfig;

public final class NioSocketConnector extends AbstractPollingIoConnector<NioSession, SocketChannel> implements SocketConnector {
    private volatile Selector selector;

    public NioSocketConnector() {
        super((IoSessionConfig) new DefaultSocketSessionConfig(), NioProcessor.class);
        ((DefaultSocketSessionConfig) getSessionConfig()).init(this);
    }

    public NioSocketConnector(int i) {
        super((IoSessionConfig) new DefaultSocketSessionConfig(), NioProcessor.class, i);
        ((DefaultSocketSessionConfig) getSessionConfig()).init(this);
    }

    public NioSocketConnector(IoProcessor<NioSession> ioProcessor) {
        super((IoSessionConfig) new DefaultSocketSessionConfig(), ioProcessor);
        ((DefaultSocketSessionConfig) getSessionConfig()).init(this);
    }

    public NioSocketConnector(Executor executor, IoProcessor<NioSession> ioProcessor) {
        super((IoSessionConfig) new DefaultSocketSessionConfig(), executor, ioProcessor);
        ((DefaultSocketSessionConfig) getSessionConfig()).init(this);
    }

    public NioSocketConnector(Class<? extends IoProcessor<NioSession>> cls, int i) {
        super((IoSessionConfig) new DefaultSocketSessionConfig(), cls, i);
    }

    public NioSocketConnector(Class<? extends IoProcessor<NioSession>> cls) {
        super((IoSessionConfig) new DefaultSocketSessionConfig(), cls);
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

    public InetSocketAddress getDefaultRemoteAddress() {
        return (InetSocketAddress) super.getDefaultRemoteAddress();
    }

    public void setDefaultRemoteAddress(InetSocketAddress inetSocketAddress) {
        super.setDefaultRemoteAddress(inetSocketAddress);
    }

    /* access modifiers changed from: protected */
    public Iterator<SocketChannel> allHandles() {
        return new SocketChannelIterator(this.selector.keys());
    }

    /* access modifiers changed from: protected */
    public boolean connect(SocketChannel socketChannel, SocketAddress socketAddress) throws Exception {
        return socketChannel.connect(socketAddress);
    }

    /* access modifiers changed from: protected */
    public AbstractPollingIoConnector<NioSession, SocketChannel>.ConnectionRequest getConnectionRequest(SocketChannel socketChannel) {
        SelectionKey keyFor = socketChannel.keyFor(this.selector);
        if (keyFor == null || !keyFor.isValid()) {
            return null;
        }
        return (AbstractPollingIoConnector.ConnectionRequest) keyFor.attachment();
    }

    /* access modifiers changed from: protected */
    public void close(SocketChannel socketChannel) throws Exception {
        SelectionKey keyFor = socketChannel.keyFor(this.selector);
        if (keyFor != null) {
            keyFor.cancel();
        }
        socketChannel.close();
    }

    /* access modifiers changed from: protected */
    public boolean finishConnect(SocketChannel socketChannel) throws Exception {
        if (!socketChannel.finishConnect()) {
            return false;
        }
        SelectionKey keyFor = socketChannel.keyFor(this.selector);
        if (keyFor == null) {
            return true;
        }
        keyFor.cancel();
        return true;
    }

    /* access modifiers changed from: protected */
    public SocketChannel newHandle(SocketAddress socketAddress) throws Exception {
        SocketChannel open = SocketChannel.open();
        int receiveBufferSize = getSessionConfig().getReceiveBufferSize();
        if (receiveBufferSize > 65535) {
            open.socket().setReceiveBufferSize(receiveBufferSize);
        }
        if (socketAddress != null) {
            open.socket().bind(socketAddress);
        }
        open.configureBlocking(false);
        return open;
    }

    /* access modifiers changed from: protected */
    public NioSession newSession(IoProcessor<NioSession> ioProcessor, SocketChannel socketChannel) {
        return new NioSocketSession(this, ioProcessor, socketChannel);
    }

    /* access modifiers changed from: protected */
    public void register(SocketChannel socketChannel, AbstractPollingIoConnector<NioSession, SocketChannel>.ConnectionRequest connectionRequest) throws Exception {
        socketChannel.register(this.selector, 8, connectionRequest);
    }

    /* access modifiers changed from: protected */
    public int select(int i) throws Exception {
        return this.selector.select((long) i);
    }

    /* access modifiers changed from: protected */
    public Iterator<SocketChannel> selectedHandles() {
        return new SocketChannelIterator(this.selector.selectedKeys());
    }

    /* access modifiers changed from: protected */
    public void wakeup() {
        this.selector.wakeup();
    }

    private static class SocketChannelIterator implements Iterator<SocketChannel> {
        private final Iterator<SelectionKey> i;

        private SocketChannelIterator(Collection<SelectionKey> collection) {
            this.i = collection.iterator();
        }

        public boolean hasNext() {
            return this.i.hasNext();
        }

        public SocketChannel next() {
            return (SocketChannel) this.i.next().channel();
        }

        public void remove() {
            this.i.remove();
        }
    }
}
