package org.apache.mina.proxy.filter;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.IoBufferWrapper;

public class ProxyHandshakeIoBuffer extends IoBufferWrapper {
    public ProxyHandshakeIoBuffer(IoBuffer ioBuffer) {
        super(ioBuffer);
    }
}
