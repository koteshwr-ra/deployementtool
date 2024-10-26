package org.apache.mina.transport.socket.nio;

import java.net.SocketException;
import java.nio.channels.DatagramChannel;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.transport.socket.AbstractDatagramSessionConfig;

class NioDatagramSessionConfig extends AbstractDatagramSessionConfig {
    private final DatagramChannel channel;

    NioDatagramSessionConfig(DatagramChannel datagramChannel) {
        this.channel = datagramChannel;
    }

    public int getReceiveBufferSize() {
        try {
            return this.channel.socket().getReceiveBufferSize();
        } catch (SocketException e) {
            throw new RuntimeIoException((Throwable) e);
        }
    }

    public void setReceiveBufferSize(int i) {
        try {
            this.channel.socket().setReceiveBufferSize(i);
        } catch (SocketException e) {
            throw new RuntimeIoException((Throwable) e);
        }
    }

    public boolean isBroadcast() {
        try {
            return this.channel.socket().getBroadcast();
        } catch (SocketException e) {
            throw new RuntimeIoException((Throwable) e);
        }
    }

    public void setBroadcast(boolean z) {
        try {
            this.channel.socket().setBroadcast(z);
        } catch (SocketException e) {
            throw new RuntimeIoException((Throwable) e);
        }
    }

    public int getSendBufferSize() {
        try {
            return this.channel.socket().getSendBufferSize();
        } catch (SocketException e) {
            throw new RuntimeIoException((Throwable) e);
        }
    }

    public void setSendBufferSize(int i) {
        try {
            this.channel.socket().setSendBufferSize(i);
        } catch (SocketException e) {
            throw new RuntimeIoException((Throwable) e);
        }
    }

    public boolean isReuseAddress() {
        try {
            return this.channel.socket().getReuseAddress();
        } catch (SocketException e) {
            throw new RuntimeIoException((Throwable) e);
        }
    }

    public void setReuseAddress(boolean z) {
        try {
            this.channel.socket().setReuseAddress(z);
        } catch (SocketException e) {
            throw new RuntimeIoException((Throwable) e);
        }
    }

    public int getTrafficClass() {
        try {
            return this.channel.socket().getTrafficClass();
        } catch (SocketException e) {
            throw new RuntimeIoException((Throwable) e);
        }
    }

    public void setTrafficClass(int i) {
        try {
            this.channel.socket().setTrafficClass(i);
        } catch (SocketException e) {
            throw new RuntimeIoException((Throwable) e);
        }
    }
}
