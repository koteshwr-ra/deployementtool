package org.apache.mina.transport.vmpipe;

import java.net.SocketAddress;

public class VmPipeAddress extends SocketAddress implements Comparable<VmPipeAddress> {
    private static final long serialVersionUID = 3257844376976830515L;
    private final int port;

    public VmPipeAddress(int i) {
        this.port = i;
    }

    public int getPort() {
        return this.port;
    }

    public int hashCode() {
        return this.port;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VmPipeAddress) || this.port != ((VmPipeAddress) obj).port) {
            return false;
        }
        return true;
    }

    public int compareTo(VmPipeAddress vmPipeAddress) {
        return this.port - vmPipeAddress.port;
    }

    public String toString() {
        if (this.port >= 0) {
            return "vm:server:" + this.port;
        }
        return "vm:client:" + (-this.port);
    }
}
