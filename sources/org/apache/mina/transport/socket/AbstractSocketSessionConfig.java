package org.apache.mina.transport.socket;

import org.apache.mina.core.session.AbstractIoSessionConfig;
import org.apache.mina.core.session.IoSessionConfig;

public abstract class AbstractSocketSessionConfig extends AbstractIoSessionConfig implements SocketSessionConfig {
    /* access modifiers changed from: protected */
    public boolean isKeepAliveChanged() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isOobInlineChanged() {
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
    public boolean isSoLingerChanged() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isTcpNoDelayChanged() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isTrafficClassChanged() {
        return true;
    }

    protected AbstractSocketSessionConfig() {
    }

    /* access modifiers changed from: protected */
    public final void doSetAll(IoSessionConfig ioSessionConfig) {
        if (ioSessionConfig instanceof SocketSessionConfig) {
            if (ioSessionConfig instanceof AbstractSocketSessionConfig) {
                AbstractSocketSessionConfig abstractSocketSessionConfig = (AbstractSocketSessionConfig) ioSessionConfig;
                if (abstractSocketSessionConfig.isKeepAliveChanged()) {
                    setKeepAlive(abstractSocketSessionConfig.isKeepAlive());
                }
                if (abstractSocketSessionConfig.isOobInlineChanged()) {
                    setOobInline(abstractSocketSessionConfig.isOobInline());
                }
                if (abstractSocketSessionConfig.isReceiveBufferSizeChanged()) {
                    setReceiveBufferSize(abstractSocketSessionConfig.getReceiveBufferSize());
                }
                if (abstractSocketSessionConfig.isReuseAddressChanged()) {
                    setReuseAddress(abstractSocketSessionConfig.isReuseAddress());
                }
                if (abstractSocketSessionConfig.isSendBufferSizeChanged()) {
                    setSendBufferSize(abstractSocketSessionConfig.getSendBufferSize());
                }
                if (abstractSocketSessionConfig.isSoLingerChanged()) {
                    setSoLinger(abstractSocketSessionConfig.getSoLinger());
                }
                if (abstractSocketSessionConfig.isTcpNoDelayChanged()) {
                    setTcpNoDelay(abstractSocketSessionConfig.isTcpNoDelay());
                }
                if (abstractSocketSessionConfig.isTrafficClassChanged() && getTrafficClass() != abstractSocketSessionConfig.getTrafficClass()) {
                    setTrafficClass(abstractSocketSessionConfig.getTrafficClass());
                    return;
                }
                return;
            }
            SocketSessionConfig socketSessionConfig = (SocketSessionConfig) ioSessionConfig;
            setKeepAlive(socketSessionConfig.isKeepAlive());
            setOobInline(socketSessionConfig.isOobInline());
            setReceiveBufferSize(socketSessionConfig.getReceiveBufferSize());
            setReuseAddress(socketSessionConfig.isReuseAddress());
            setSendBufferSize(socketSessionConfig.getSendBufferSize());
            setSoLinger(socketSessionConfig.getSoLinger());
            setTcpNoDelay(socketSessionConfig.isTcpNoDelay());
            if (getTrafficClass() != socketSessionConfig.getTrafficClass()) {
                setTrafficClass(socketSessionConfig.getTrafficClass());
            }
        }
    }
}
