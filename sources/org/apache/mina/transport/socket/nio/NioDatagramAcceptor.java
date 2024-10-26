package org.apache.mina.transport.socket.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Executor;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.polling.AbstractPollingConnectionlessIoAcceptor;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.service.TransportMetadata;
import org.apache.mina.transport.socket.DatagramAcceptor;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.DefaultDatagramSessionConfig;

public final class NioDatagramAcceptor extends AbstractPollingConnectionlessIoAcceptor<NioSession, DatagramChannel> implements DatagramAcceptor {
    private volatile Selector selector;

    public NioDatagramAcceptor() {
        super(new DefaultDatagramSessionConfig());
    }

    public NioDatagramAcceptor(Executor executor) {
        super(new DefaultDatagramSessionConfig(), executor);
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
        return NioDatagramSession.METADATA;
    }

    public DatagramSessionConfig getSessionConfig() {
        return (DatagramSessionConfig) super.getSessionConfig();
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
    public DatagramChannel open(SocketAddress socketAddress) throws Exception {
        DatagramChannel open = DatagramChannel.open();
        try {
            new NioDatagramSessionConfig(open).setAll(getSessionConfig());
            open.configureBlocking(false);
            open.socket().bind(socketAddress);
            open.register(this.selector, 1);
            return open;
        } catch (Throwable th) {
            close(open);
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isReadable(DatagramChannel datagramChannel) {
        SelectionKey keyFor = datagramChannel.keyFor(this.selector);
        if (keyFor == null || !keyFor.isValid()) {
            return false;
        }
        return keyFor.isReadable();
    }

    /* access modifiers changed from: protected */
    public boolean isWritable(DatagramChannel datagramChannel) {
        SelectionKey keyFor = datagramChannel.keyFor(this.selector);
        if (keyFor == null || !keyFor.isValid()) {
            return false;
        }
        return keyFor.isWritable();
    }

    /* access modifiers changed from: protected */
    public SocketAddress localAddress(DatagramChannel datagramChannel) throws Exception {
        return datagramChannel.socket().getLocalSocketAddress();
    }

    /* access modifiers changed from: protected */
    public NioSession newSession(IoProcessor<NioSession> ioProcessor, DatagramChannel datagramChannel, SocketAddress socketAddress) {
        SelectionKey keyFor = datagramChannel.keyFor(this.selector);
        if (keyFor == null || !keyFor.isValid()) {
            return null;
        }
        NioDatagramSession nioDatagramSession = new NioDatagramSession(this, datagramChannel, ioProcessor, socketAddress);
        nioDatagramSession.setSelectionKey(keyFor);
        return nioDatagramSession;
    }

    /* access modifiers changed from: protected */
    public SocketAddress receive(DatagramChannel datagramChannel, IoBuffer ioBuffer) throws Exception {
        return datagramChannel.receive(ioBuffer.buf());
    }

    /* access modifiers changed from: protected */
    public int select() throws Exception {
        return this.selector.select();
    }

    /* access modifiers changed from: protected */
    public int select(long j) throws Exception {
        return this.selector.select(j);
    }

    /* access modifiers changed from: protected */
    public Iterator<DatagramChannel> selectedHandles() {
        return new DatagramChannelIterator(this.selector.selectedKeys());
    }

    /* access modifiers changed from: protected */
    public int send(NioSession nioSession, IoBuffer ioBuffer, SocketAddress socketAddress) throws Exception {
        return ((DatagramChannel) nioSession.getChannel()).send(ioBuffer.buf(), socketAddress);
    }

    /* access modifiers changed from: protected */
    public void setInterestedInWrite(NioSession nioSession, boolean z) throws Exception {
        SelectionKey selectionKey = nioSession.getSelectionKey();
        if (selectionKey != null) {
            int interestOps = selectionKey.interestOps();
            selectionKey.interestOps(z ? interestOps | 4 : interestOps & -5);
        }
    }

    /* access modifiers changed from: protected */
    public void close(DatagramChannel datagramChannel) throws Exception {
        SelectionKey keyFor = datagramChannel.keyFor(this.selector);
        if (keyFor != null) {
            keyFor.cancel();
        }
        datagramChannel.disconnect();
        datagramChannel.close();
    }

    /* access modifiers changed from: protected */
    public void wakeup() {
        this.selector.wakeup();
    }

    private static class DatagramChannelIterator implements Iterator<DatagramChannel> {
        private final Iterator<SelectionKey> i;

        private DatagramChannelIterator(Collection<SelectionKey> collection) {
            this.i = collection.iterator();
        }

        public boolean hasNext() {
            return this.i.hasNext();
        }

        public DatagramChannel next() {
            return (DatagramChannel) this.i.next().channel();
        }

        public void remove() {
            this.i.remove();
        }
    }
}
