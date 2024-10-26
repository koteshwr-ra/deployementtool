package org.apache.mina.transport.socket.nio;

import java.nio.channels.ByteChannel;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import org.apache.mina.core.filterchain.DefaultIoFilterChain;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.session.AbstractIoSession;

public abstract class NioSession extends AbstractIoSession {
    protected final Channel channel;
    private final IoFilterChain filterChain = new DefaultIoFilterChain(this);
    protected SelectionKey key;
    protected final IoProcessor<NioSession> processor;

    /* access modifiers changed from: package-private */
    public abstract ByteChannel getChannel();

    protected NioSession(IoProcessor<NioSession> ioProcessor, IoService ioService, Channel channel2) {
        super(ioService);
        this.channel = channel2;
        this.processor = ioProcessor;
    }

    public IoFilterChain getFilterChain() {
        return this.filterChain;
    }

    /* access modifiers changed from: package-private */
    public SelectionKey getSelectionKey() {
        return this.key;
    }

    /* access modifiers changed from: package-private */
    public void setSelectionKey(SelectionKey selectionKey) {
        this.key = selectionKey;
    }

    public IoProcessor<NioSession> getProcessor() {
        return this.processor;
    }
}
