package org.apache.mina.transport.socket;

import java.net.InetSocketAddress;
import org.apache.mina.core.service.IoConnector;

public interface DatagramConnector extends IoConnector {
    InetSocketAddress getDefaultRemoteAddress();

    void setDefaultRemoteAddress(InetSocketAddress inetSocketAddress);
}
