package org.apache.mina.transport.socket;

public class DefaultDatagramSessionConfig extends AbstractDatagramSessionConfig {
    private static boolean DEFAULT_BROADCAST = false;
    private static int DEFAULT_RECEIVE_BUFFER_SIZE = -1;
    private static boolean DEFAULT_REUSE_ADDRESS = false;
    private static int DEFAULT_SEND_BUFFER_SIZE = -1;
    private static int DEFAULT_TRAFFIC_CLASS;
    private boolean broadcast = DEFAULT_BROADCAST;
    private int receiveBufferSize = DEFAULT_RECEIVE_BUFFER_SIZE;
    private boolean reuseAddress = DEFAULT_REUSE_ADDRESS;
    private int sendBufferSize = DEFAULT_SEND_BUFFER_SIZE;
    private int trafficClass = DEFAULT_TRAFFIC_CLASS;

    public boolean isBroadcast() {
        return this.broadcast;
    }

    public void setBroadcast(boolean z) {
        this.broadcast = z;
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

    /* access modifiers changed from: protected */
    public boolean isBroadcastChanged() {
        return this.broadcast != DEFAULT_BROADCAST;
    }

    /* access modifiers changed from: protected */
    public boolean isReceiveBufferSizeChanged() {
        return this.receiveBufferSize != DEFAULT_RECEIVE_BUFFER_SIZE;
    }

    /* access modifiers changed from: protected */
    public boolean isReuseAddressChanged() {
        return this.reuseAddress != DEFAULT_REUSE_ADDRESS;
    }

    /* access modifiers changed from: protected */
    public boolean isSendBufferSizeChanged() {
        return this.sendBufferSize != DEFAULT_SEND_BUFFER_SIZE;
    }

    /* access modifiers changed from: protected */
    public boolean isTrafficClassChanged() {
        return this.trafficClass != DEFAULT_TRAFFIC_CLASS;
    }
}
