package org.apache.mina.transport.socket;

import org.apache.mina.core.session.AbstractIoSessionConfig;
import org.apache.mina.core.session.IoSessionConfig;

public abstract class AbstractDatagramSessionConfig extends AbstractIoSessionConfig implements DatagramSessionConfig {
    private static final boolean DEFAULT_CLOSE_ON_PORT_UNREACHABLE = true;
    private boolean closeOnPortUnreachable = true;

    /* access modifiers changed from: protected */
    public boolean isBroadcastChanged() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isReceiveBufferSizeChanged() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isReuseAddressChanged() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isSendBufferSizeChanged() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isTrafficClassChanged() {
        return true;
    }

    protected AbstractDatagramSessionConfig() {
    }

    /* access modifiers changed from: protected */
    public void doSetAll(IoSessionConfig ioSessionConfig) {
        if (ioSessionConfig instanceof DatagramSessionConfig) {
            if (ioSessionConfig instanceof AbstractDatagramSessionConfig) {
                AbstractDatagramSessionConfig abstractDatagramSessionConfig = (AbstractDatagramSessionConfig) ioSessionConfig;
                if (abstractDatagramSessionConfig.isBroadcastChanged()) {
                    setBroadcast(abstractDatagramSessionConfig.isBroadcast());
                }
                if (abstractDatagramSessionConfig.isReceiveBufferSizeChanged()) {
                    setReceiveBufferSize(abstractDatagramSessionConfig.getReceiveBufferSize());
                }
                if (abstractDatagramSessionConfig.isReuseAddressChanged()) {
                    setReuseAddress(abstractDatagramSessionConfig.isReuseAddress());
                }
                if (abstractDatagramSessionConfig.isSendBufferSizeChanged()) {
                    setSendBufferSize(abstractDatagramSessionConfig.getSendBufferSize());
                }
                if (abstractDatagramSessionConfig.isTrafficClassChanged() && getTrafficClass() != abstractDatagramSessionConfig.getTrafficClass()) {
                    setTrafficClass(abstractDatagramSessionConfig.getTrafficClass());
                    return;
                }
                return;
            }
            DatagramSessionConfig datagramSessionConfig = (DatagramSessionConfig) ioSessionConfig;
            setBroadcast(datagramSessionConfig.isBroadcast());
            setReceiveBufferSize(datagramSessionConfig.getReceiveBufferSize());
            setReuseAddress(datagramSessionConfig.isReuseAddress());
            setSendBufferSize(datagramSessionConfig.getSendBufferSize());
            if (getTrafficClass() != datagramSessionConfig.getTrafficClass()) {
                setTrafficClass(datagramSessionConfig.getTrafficClass());
            }
        }
    }

    public boolean isCloseOnPortUnreachable() {
        return this.closeOnPortUnreachable;
    }

    public void setCloseOnPortUnreachable(boolean z) {
        this.closeOnPortUnreachable = z;
    }
}
