package org.apache.mina.proxy.handlers.socks;

import java.util.Arrays;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.proxy.session.ProxyIoSession;
import org.apache.mina.proxy.utils.ByteUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Socks4LogicHandler extends AbstractSocksLogicHandler {
    private static final Logger logger = LoggerFactory.getLogger(Socks4LogicHandler.class);

    public Socks4LogicHandler(ProxyIoSession proxyIoSession) {
        super(proxyIoSession);
    }

    public void doHandshake(IoFilter.NextFilter nextFilter) {
        logger.debug(" doHandshake()");
        writeRequest(nextFilter, this.request);
    }

    /* access modifiers changed from: protected */
    public void writeRequest(IoFilter.NextFilter nextFilter, SocksProxyRequest socksProxyRequest) {
        try {
            boolean equals = Arrays.equals(socksProxyRequest.getIpAddress(), SocksProxyConstants.FAKE_IP);
            byte[] bytes = socksProxyRequest.getUserName().getBytes("ASCII");
            byte[] bytes2 = equals ? socksProxyRequest.getHost().getBytes("ASCII") : null;
            int length = bytes.length + 9;
            if (equals) {
                length += bytes2.length + 1;
            }
            IoBuffer allocate = IoBuffer.allocate(length);
            allocate.put(socksProxyRequest.getProtocolVersion());
            allocate.put(socksProxyRequest.getCommandCode());
            allocate.put(socksProxyRequest.getPort());
            allocate.put(socksProxyRequest.getIpAddress());
            allocate.put(bytes);
            allocate.put((byte) 0);
            if (equals) {
                allocate.put(bytes2);
                allocate.put((byte) 0);
            }
            if (equals) {
                logger.debug("  sending SOCKS4a request");
            } else {
                logger.debug("  sending SOCKS4 request");
            }
            allocate.flip();
            writeData(nextFilter, allocate);
        } catch (Exception e) {
            closeSession("Unable to send Socks request: ", e);
        }
    }

    public void messageReceived(IoFilter.NextFilter nextFilter, IoBuffer ioBuffer) {
        try {
            if (ioBuffer.remaining() >= 8) {
                handleResponse(ioBuffer);
            }
        } catch (Exception e) {
            closeSession("Proxy handshake failed: ", e);
        }
    }

    /* access modifiers changed from: protected */
    public void handleResponse(IoBuffer ioBuffer) throws Exception {
        if (ioBuffer.get(0) == 0) {
            byte b = ioBuffer.get(1);
            ioBuffer.position(ioBuffer.position() + 8);
            if (b == 90) {
                setHandshakeComplete();
                return;
            }
            throw new Exception("Proxy handshake failed - Code: 0x" + ByteUtilities.asHex(new byte[]{b}) + " (" + SocksProxyConstants.getReplyCodeAsString(b) + ")");
        }
        throw new Exception("Socks response seems to be malformed");
    }
}
