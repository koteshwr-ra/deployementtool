package org.apache.mina.transport.socket.nio;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.file.FileRegion;
import org.apache.mina.core.polling.AbstractPollingIoProcessor;
import org.apache.mina.core.session.SessionState;

public final class NioProcessor extends AbstractPollingIoProcessor<NioSession> {
    private Selector selector;

    public NioProcessor(Executor executor) {
        super(executor);
        try {
            this.selector = Selector.open();
        } catch (IOException e) {
            throw new RuntimeIoException("Failed to open a selector.", e);
        }
    }

    /* access modifiers changed from: protected */
    public void doDispose() throws Exception {
        this.selector.close();
    }

    /* access modifiers changed from: protected */
    public int select(long j) throws Exception {
        return this.selector.select(j);
    }

    /* access modifiers changed from: protected */
    public int select() throws Exception {
        return this.selector.select();
    }

    /* access modifiers changed from: protected */
    public boolean isSelectorEmpty() {
        return this.selector.keys().isEmpty();
    }

    /* access modifiers changed from: protected */
    public void wakeup() {
        this.wakeupCalled.getAndSet(true);
        this.selector.wakeup();
    }

    /* access modifiers changed from: protected */
    public Iterator<NioSession> allSessions() {
        return new IoSessionIterator(this.selector.keys());
    }

    /* access modifiers changed from: protected */
    public Iterator<NioSession> selectedSessions() {
        return new IoSessionIterator(this.selector.selectedKeys());
    }

    /* access modifiers changed from: protected */
    public void init(NioSession nioSession) throws Exception {
        SelectableChannel selectableChannel = (SelectableChannel) nioSession.getChannel();
        selectableChannel.configureBlocking(false);
        nioSession.setSelectionKey(selectableChannel.register(this.selector, 1, nioSession));
    }

    /* access modifiers changed from: protected */
    public void destroy(NioSession nioSession) throws Exception {
        ByteChannel channel = nioSession.getChannel();
        SelectionKey selectionKey = nioSession.getSelectionKey();
        if (selectionKey != null) {
            selectionKey.cancel();
        }
        channel.close();
    }

    /* access modifiers changed from: protected */
    public void registerNewSelector() throws IOException {
        synchronized (this.selector) {
            Set<SelectionKey> keys = this.selector.keys();
            Selector open = Selector.open();
            for (SelectionKey next : keys) {
                SelectableChannel channel = next.channel();
                NioSession nioSession = (NioSession) next.attachment();
                nioSession.setSelectionKey(channel.register(open, next.interestOps(), nioSession));
            }
            this.selector.close();
            this.selector = open;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isBrokenConnection() throws IOException {
        boolean z;
        synchronized (this.selector) {
            z = false;
            for (SelectionKey next : this.selector.keys()) {
                SelectableChannel channel = next.channel();
                if (((channel instanceof DatagramChannel) && ((DatagramChannel) channel).isConnected()) || ((channel instanceof SocketChannel) && ((SocketChannel) channel).isConnected())) {
                    next.cancel();
                    z = true;
                }
            }
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public SessionState getState(NioSession nioSession) {
        SelectionKey selectionKey = nioSession.getSelectionKey();
        if (selectionKey == null) {
            return SessionState.OPENING;
        }
        if (selectionKey.isValid()) {
            return SessionState.OPENED;
        }
        return SessionState.CLOSING;
    }

    /* access modifiers changed from: protected */
    public boolean isReadable(NioSession nioSession) {
        SelectionKey selectionKey = nioSession.getSelectionKey();
        return selectionKey.isValid() && selectionKey.isReadable();
    }

    /* access modifiers changed from: protected */
    public boolean isWritable(NioSession nioSession) {
        SelectionKey selectionKey = nioSession.getSelectionKey();
        return selectionKey.isValid() && selectionKey.isWritable();
    }

    /* access modifiers changed from: protected */
    public boolean isInterestedInRead(NioSession nioSession) {
        SelectionKey selectionKey = nioSession.getSelectionKey();
        return selectionKey.isValid() && (selectionKey.interestOps() & 1) != 0;
    }

    /* access modifiers changed from: protected */
    public boolean isInterestedInWrite(NioSession nioSession) {
        SelectionKey selectionKey = nioSession.getSelectionKey();
        return selectionKey.isValid() && (selectionKey.interestOps() & 4) != 0;
    }

    /* access modifiers changed from: protected */
    public void setInterestedInRead(NioSession nioSession, boolean z) throws Exception {
        SelectionKey selectionKey = nioSession.getSelectionKey();
        int interestOps = selectionKey.interestOps();
        int i = z ? interestOps | 1 : interestOps & -2;
        if (interestOps != i) {
            selectionKey.interestOps(i);
        }
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
    public int read(NioSession nioSession, IoBuffer ioBuffer) throws Exception {
        nioSession.getChannel();
        return nioSession.getChannel().read(ioBuffer.buf());
    }

    /* access modifiers changed from: protected */
    public int write(NioSession nioSession, IoBuffer ioBuffer, int i) throws Exception {
        if (ioBuffer.remaining() <= i) {
            return nioSession.getChannel().write(ioBuffer.buf());
        }
        int limit = ioBuffer.limit();
        ioBuffer.limit(ioBuffer.position() + i);
        try {
            return nioSession.getChannel().write(ioBuffer.buf());
        } finally {
            ioBuffer.limit(limit);
        }
    }

    /* access modifiers changed from: protected */
    public int transferFile(NioSession nioSession, FileRegion fileRegion, int i) throws Exception {
        try {
            return (int) fileRegion.getFileChannel().transferTo(fileRegion.getPosition(), (long) i, nioSession.getChannel());
        } catch (IOException e) {
            String message = e.getMessage();
            if (message != null && message.contains("temporarily unavailable")) {
                return 0;
            }
            throw e;
        }
    }

    protected static class IoSessionIterator<NioSession> implements Iterator<NioSession> {
        private final Iterator<SelectionKey> iterator;

        private IoSessionIterator(Set<SelectionKey> set) {
            this.iterator = set.iterator();
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public NioSession next() {
            return this.iterator.next().attachment();
        }

        public void remove() {
            this.iterator.remove();
        }
    }
}
