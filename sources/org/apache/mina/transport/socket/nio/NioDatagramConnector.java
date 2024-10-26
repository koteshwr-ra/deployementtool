package org.apache.mina.transport.socket.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.Collections;
import java.util.Iterator;
import org.apache.mina.core.polling.AbstractPollingIoConnector;
import org.apache.mina.core.service.IoProcessor;
import org.apache.mina.core.service.TransportMetadata;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.transport.socket.DatagramConnector;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.DefaultDatagramSessionConfig;

public final class NioDatagramConnector extends AbstractPollingIoConnector<NioSession, DatagramChannel> implements DatagramConnector {
    /* access modifiers changed from: protected */
    public void destroy() throws Exception {
    }

    /* access modifiers changed from: protected */
    public void init() throws Exception {
    }

    /* access modifiers changed from: protected */
    public int select(int i) throws Exception {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void wakeup() {
    }

    public NioDatagramConnector() {
        super((IoSessionConfig) new DefaultDatagramSessionConfig(), NioProcessor.class);
    }

    public NioDatagramConnector(int i) {
        super((IoSessionConfig) new DefaultDatagramSessionConfig(), NioProcessor.class, i);
    }

    public NioDatagramConnector(IoProcessor<NioSession> ioProcessor) {
        super((IoSessionConfig) new DefaultDatagramSessionConfig(), ioProcessor);
    }

    public NioDatagramConnector(Class<? extends IoProcessor<NioSession>> cls, int i) {
        super((IoSessionConfig) new DefaultDatagramSessionConfig(), cls, i);
    }

    public NioDatagramConnector(Class<? extends IoProcessor<NioSession>> cls) {
        super((IoSessionConfig) new DefaultDatagramSessionConfig(), cls);
    }

    public TransportMetadata getTransportMetadata() {
        return NioDatagramSession.METADATA;
    }

    public DatagramSessionConfig getSessionConfig() {
        return (DatagramSessionConfig) super.getSessionConfig();
    }

    public InetSocketAddress getDefaultRemoteAddress() {
        return (InetSocketAddress) super.getDefaultRemoteAddress();
    }

    public void setDefaultRemoteAddress(InetSocketAddress inetSocketAddress) {
        super.setDefaultRemoteAddress(inetSocketAddress);
    }

    /* access modifiers changed from: protected */
    public DatagramChannel newHandle(SocketAddress socketAddress) throws Exception {
        DatagramChannel open = DatagramChannel.open();
        if (socketAddress != null) {
            try {
                open.socket().bind(socketAddress);
            } catch (Exception e) {
                open.close();
                throw e;
            }
        }
        return open;
    }

    /* access modifiers changed from: protected */
    public boolean connect(DatagramChannel datagramChannel, SocketAddress socketAddress) throws Exception {
        datagramChannel.connect(socketAddress);
        return true;
    }

    /* access modifiers changed from: protected */
    public NioSession newSession(IoProcessor<NioSession> ioProcessor, DatagramChannel datagramChannel) {
        NioDatagramSession nioDatagramSession = new NioDatagramSession(this, datagramChannel, ioProcessor);
        nioDatagramSession.getConfig().setAll(getSessionConfig());
        return nioDatagramSession;
    }

    /* access modifiers changed from: protected */
    public void close(DatagramChannel datagramChannel) throws Exception {
        datagramChannel.disconnect();
        datagramChannel.close();
    }

    /* access modifiers changed from: protected */
    public Iterator<DatagramChannel> allHandles() {
        return Collections.EMPTY_LIST.iterator();
    }

    /* access modifiers changed from: protected */
    public AbstractPollingIoConnector<NioSession, DatagramChannel>.ConnectionRequest getConnectionRequest(DatagramChannel datagramChannel) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public boolean finishConnect(DatagramChannel datagramChannel) throws Exception {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public void register(DatagramChannel datagramChannel, AbstractPollingIoConnector<NioSession, DatagramChannel>.ConnectionRequest connectionRequest) throws Exception {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public Iterator<DatagramChannel> selectedHandles() {
        return Collections.EMPTY_LIST.iterator();
    }
}
