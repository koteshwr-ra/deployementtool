package org.apache.mina.transport.socket;

import org.apache.mina.core.service.IoService;

public class DefaultSocketSessionConfig extends AbstractSocketSessionConfig {
    private static boolean DEFAULT_KEEP_ALIVE = false;
    private static boolean DEFAULT_OOB_INLINE = false;
    private static boolean DEFAULT_REUSE_ADDRESS = false;
    private static int DEFAULT_SO_LINGER = -1;
    private static boolean DEFAULT_TCP_NO_DELAY = false;
    private static int DEFAULT_TRAFFIC_CLASS;
    private boolean defaultReuseAddress;
    private boolean keepAlive = DEFAULT_KEEP_ALIVE;
    private boolean oobInline = DEFAULT_OOB_INLINE;
    protected IoService parent;
    private int receiveBufferSize = -1;
    private boolean reuseAddress;
    private int sendBufferSize = -1;
    private int soLinger = DEFAULT_SO_LINGER;
    private boolean tcpNoDelay = DEFAULT_TCP_NO_DELAY;
    private int trafficClass = DEFAULT_TRAFFIC_CLASS;

    public void init(IoService ioService) {
        this.parent = ioService;
        if (ioService instanceof SocketAcceptor) {
            this.defaultReuseAddress = true;
        } else {
            this.defaultReuseAddress = DEFAULT_REUSE_ADDRESS;
        }
        this.reuseAddress = this.defaultReuseAddress;
    }

    public boolean isReuseAddress() {
        return this.reuseAddress;
    }

    public void setReuseAddress(boolean z) {
        this.reuseAddress = z;
    }

    public int getReceiveBufferSize() {
        return this.receiveBufferSize;
    }

    public void setReceiveBufferSize(int i) {
        this.receiveBufferSize = i;
    }

    public int getSendBufferSize() {
        return this.sendBufferSize;
    }

    public void setSendBufferSize(int i) {
        this.sendBufferSize = i;
    }

    public int getTrafficClass() {
        return this.trafficClass;
    }

    public void setTrafficClass(int i) {
        this.trafficClass = i;
    }

    public boolean isKeepAlive() {
        return this.keepAlive;
    }

    public void setKeepAlive(boolean z) {
        this.keepAlive = z;
    }

    public boolean isOobInline() {
        return this.oobInline;
    }

    public void setOobInline(boolean z) {
        this.oobInline = z;
    }

    public int getSoLinger() {
        return this.soLinger;
    }

    public void setSoLinger(int i) {
        this.soLinger = i;
    }

    public boolean isTcpNoDelay() {
        return this.tcpNoDelay;
    }

    public void setTcpNoDelay(boolean z) {
        this.tcpNoDelay = z;
    }

    /* access modifiers changed from: protected */
    public boolean isKeepAliveChanged() {
        return this.keepAlive != DEFAULT_KEEP_ALIVE;
    }

    /* access modifiers changed from: protected */
    public boolean isOobInlineChanged() {
        return this.oobInline != DEFAULT_OOB_INLINE;
    }

    /* access modifiers changed from: protected */
    public boolean isReceiveBufferSizeChanged() {
        return this.receiveBufferSize != -1;
    }

    /* access modifiers changed from: protected */
    public boolean isReuseAddressChanged() {
        return this.reuseAddress != this.defaultReuseAddress;
    }

    /* access modifiers changed from: protected */
    public boolean isSendBufferSizeChanged() {
        return this.sendBufferSize != -1;
    }

    /* access modifiers changed from: protected */
    public boolean isSoLingerChanged() {
        return this.soLinger != DEFAULT_SO_LINGER;
    }

    /* access modifiers changed from: protected */
    public boolean isTcpNoDelayChanged() {
        return this.tcpNoDelay != DEFAULT_TCP_NO_DELAY;
    }

    /* access modifiers changed from: protected */
    public boolean isTrafficClassChanged() {
        return this.trafficClass != DEFAULT_TRAFFIC_CLASS;
    }
}
