package org.apache.mina.proxy.handlers;

import java.net.InetSocketAddress;

public abstract class ProxyRequest {
    private InetSocketAddress endpointAddress = null;

    public ProxyRequest() {
    }

    public ProxyRequest(InetSocketAddress inetSocketAddress) {
        this.endpointAddress = inetSocketAddress;
    }

    public InetSocketAddress getEndpointAddress() {
        return this.endpointAddress;
    }
}
