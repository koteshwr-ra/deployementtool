package org.apache.mina.core.session;

import java.net.SocketAddress;

public interface IoSessionRecycler {
    public static final IoSessionRecycler NOOP = new IoSessionRecycler() {
        public void put(IoSession ioSession) {
        }

        public IoSession recycle(SocketAddress socketAddress, SocketAddress socketAddress2) {
            return null;
        }

        public void remove(IoSession ioSession) {
        }
    };

    void put(IoSession ioSession);

    IoSession recycle(SocketAddress socketAddress, SocketAddress socketAddress2);

    void remove(IoSession ioSession);
}
