package org.apache.mina.proxy.handlers.socks;

import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetSocketAddress;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.proxy.session.ProxyIoSession;
import org.apache.mina.proxy.utils.ByteUtilities;
import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Socks5LogicHandler extends AbstractSocksLogicHandler {
    private static final String GSS_CONTEXT;
    private static final String GSS_TOKEN;
    private static final String HANDSHAKE_STEP;
    private static final Logger LOGGER;
    private static final String SELECTED_AUTH_METHOD;

    static {
        Class<Socks5LogicHandler> cls = Socks5LogicHandler.class;
        LOGGER = LoggerFactory.getLogger((Class) cls);
        SELECTED_AUTH_METHOD = cls.getName() + ".SelectedAuthMethod";
        HANDSHAKE_STEP = cls.getName() + ".HandshakeStep";
        GSS_CONTEXT = cls.getName() + ".GSSContext";
        GSS_TOKEN = cls.getName() + ".GSSToken";
    }

    public Socks5LogicHandler(ProxyIoSession proxyIoSession) {
        super(proxyIoSession);
        getSession().setAttribute(HANDSHAKE_STEP, 0);
    }

    public synchronized void doHandshake(IoFilter.NextFilter nextFilter) {
        LOGGER.debug(" doHandshake()");
        writeRequest(nextFilter, this.request, ((Integer) getSession().getAttribute(HANDSHAKE_STEP)).intValue());
    }

    private IoBuffer encodeInitialGreetingPacket(SocksProxyRequest socksProxyRequest) {
        byte length = (byte) SocksProxyConstants.SUPPORTED_AUTH_METHODS.length;
        IoBuffer allocate = IoBuffer.allocate(length + 2);
        allocate.put(socksProxyRequest.getProtocolVersion());
        allocate.put(length);
        allocate.put(SocksProxyConstants.SUPPORTED_AUTH_METHODS);
        return allocate;
    }

    private IoBuffer encodeProxyRequestPacket(SocksProxyRequest socksProxyRequest) throws UnsupportedEncodingException {
        InetSocketAddress endpointAddress = socksProxyRequest.getEndpointAddress();
        byte b = 1;
        byte[] bArr = null;
        int i = 6;
        if (endpointAddress == null || endpointAddress.isUnresolved()) {
            if (socksProxyRequest.getHost() != null) {
                bArr = socksProxyRequest.getHost().getBytes("ASCII");
            }
            if (bArr != null) {
                i = 6 + bArr.length + 1;
                b = 3;
            } else {
                throw new IllegalArgumentException("SocksProxyRequest object has no suitable endpoint information");
            }
        } else if (endpointAddress.getAddress() instanceof Inet6Address) {
            i = 22;
            b = 4;
        } else if (endpointAddress.getAddress() instanceof Inet4Address) {
            i = 10;
        } else {
            b = 0;
        }
        IoBuffer allocate = IoBuffer.allocate(i);
        allocate.put(socksProxyRequest.getProtocolVersion());
        allocate.put(socksProxyRequest.getCommandCode());
        allocate.put((byte) 0);
        allocate.put(b);
        if (bArr == null) {
            allocate.put(socksProxyRequest.getIpAddress());
        } else {
            allocate.put((byte) bArr.length);
            allocate.put(bArr);
        }
        allocate.put(socksProxyRequest.getPort());
        return allocate;
    }

    private IoBuffer encodeAuthenticationPacket(SocksProxyRequest socksProxyRequest) throws UnsupportedEncodingException, GSSException {
        byte byteValue = ((Byte) getSession().getAttribute(SELECTED_AUTH_METHOD)).byteValue();
        if (byteValue == 0) {
            getSession().setAttribute(HANDSHAKE_STEP, 2);
            return null;
        } else if (byteValue == 1) {
            return encodeGSSAPIAuthenticationPacket(socksProxyRequest);
        } else {
            if (byteValue != 2) {
                return null;
            }
            byte[] bytes = socksProxyRequest.getUserName().getBytes("ASCII");
            byte[] bytes2 = socksProxyRequest.getPassword().getBytes("ASCII");
            IoBuffer allocate = IoBuffer.allocate(bytes.length + 3 + bytes2.length);
            allocate.put((byte) 1);
            allocate.put((byte) bytes.length);
            allocate.put(bytes);
            allocate.put((byte) bytes2.length);
            allocate.put(bytes2);
            return allocate;
        }
    }

    private IoBuffer encodeGSSAPIAuthenticationPacket(SocksProxyRequest socksProxyRequest) throws GSSException {
        GSSContext gSSContext = (GSSContext) getSession().getAttribute(GSS_CONTEXT);
        if (gSSContext == null) {
            GSSManager instance = GSSManager.getInstance();
            GSSName createName = instance.createName(socksProxyRequest.getServiceKerberosName(), (Oid) null);
            Oid oid = new Oid(SocksProxyConstants.KERBEROS_V5_OID);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Available mechs:");
                for (Oid oid2 : instance.getMechs()) {
                    if (oid2.equals(oid)) {
                        LOGGER.debug("Found Kerberos V OID available");
                    }
                    LOGGER.debug("{} with oid = {}", (Object) instance.getNamesForMech(oid2), (Object) oid2);
                }
            }
            gSSContext = instance.createContext(createName, oid, (GSSCredential) null, 0);
            gSSContext.requestMutualAuth(true);
            gSSContext.requestConf(false);
            gSSContext.requestInteg(false);
            getSession().setAttribute(GSS_CONTEXT, gSSContext);
        }
        byte[] bArr = (byte[]) getSession().getAttribute(GSS_TOKEN);
        if (bArr != null) {
            LOGGER.debug("  Received Token[{}] = {}", (Object) Integer.valueOf(bArr.length), (Object) ByteUtilities.asHex(bArr));
        }
        if (gSSContext.isEstablished()) {
            return null;
        }
        if (bArr == null) {
            bArr = new byte[32];
        }
        byte[] initSecContext = gSSContext.initSecContext(bArr, 0, bArr.length);
        if (initSecContext == null) {
            return null;
        }
        LOGGER.debug("  Sending Token[{}] = {}", (Object) Integer.valueOf(initSecContext.length), (Object) ByteUtilities.asHex(initSecContext));
        getSession().setAttribute(GSS_TOKEN, initSecContext);
        IoBuffer allocate = IoBuffer.allocate(initSecContext.length + 4);
        allocate.put(new byte[]{1, 1});
        allocate.put(ByteUtilities.intToNetworkByteOrder(initSecContext.length, 2));
        allocate.put(initSecContext);
        return allocate;
    }

    private void writeRequest(IoFilter.NextFilter nextFilter, SocksProxyRequest socksProxyRequest, int i) {
        IoBuffer ioBuffer = null;
        if (i == 0) {
            try {
                ioBuffer = encodeInitialGreetingPacket(socksProxyRequest);
            } catch (Exception e) {
                closeSession("Unable to send Socks request: ", e);
                return;
            }
        } else if (i == 1 && (ioBuffer = encodeAuthenticationPacket(socksProxyRequest)) == null) {
            i = 2;
        }
        if (i == 2) {
            ioBuffer = encodeProxyRequestPacket(socksProxyRequest);
        }
        ioBuffer.flip();
        writeData(nextFilter, ioBuffer);
    }

    public synchronized void messageReceived(IoFilter.NextFilter nextFilter, IoBuffer ioBuffer) {
        try {
            int intValue = ((Integer) getSession().getAttribute(HANDSHAKE_STEP)).intValue();
            if (intValue == 0) {
                if (ioBuffer.get(0) != 5) {
                    throw new IllegalStateException("Wrong socks version running on server");
                }
            }
            if ((intValue == 0 || intValue == 1) && ioBuffer.remaining() >= 2) {
                handleResponse(nextFilter, ioBuffer, intValue);
            } else if (intValue == 2 && ioBuffer.remaining() >= 5) {
                handleResponse(nextFilter, ioBuffer, intValue);
            }
        } catch (Exception e) {
            closeSession("Proxy handshake failed: ", e);
        }
        return;
    }

    /* access modifiers changed from: protected */
    public void handleResponse(IoFilter.NextFilter nextFilter, IoBuffer ioBuffer, int i) throws Exception {
        GSSContext gSSContext;
        int i2;
        boolean z = false;
        int i3 = 2;
        if (i == 0) {
            byte b = ioBuffer.get(1);
            if (b != -1) {
                getSession().setAttribute(SELECTED_AUTH_METHOD, new Byte(b));
            } else {
                throw new IllegalStateException("No acceptable authentication method to use with the socks proxy server");
            }
        } else if (i == 1) {
            if (((Byte) getSession().getAttribute(SELECTED_AUTH_METHOD)).byteValue() == 1) {
                int position = ioBuffer.position();
                if (ioBuffer.get(0) != 1) {
                    throw new IllegalStateException("Authentication failed");
                } else if (ioBuffer.get(1) == 255) {
                    throw new IllegalStateException("Authentication failed: GSS API Security Context Failure");
                } else if (ioBuffer.remaining() >= 2) {
                    byte[] bArr = new byte[2];
                    ioBuffer.get(bArr);
                    int makeIntFromByte2 = ByteUtilities.makeIntFromByte2(bArr);
                    if (ioBuffer.remaining() >= makeIntFromByte2) {
                        byte[] bArr2 = new byte[makeIntFromByte2];
                        ioBuffer.get(bArr2);
                        getSession().setAttribute(GSS_TOKEN, bArr2);
                        i3 = 0;
                    } else {
                        return;
                    }
                } else {
                    ioBuffer.position(position);
                    return;
                }
            } else if (ioBuffer.get(1) != 0) {
                throw new IllegalStateException("Authentication failed");
            }
        } else if (i == 2) {
            byte b2 = ioBuffer.get(3);
            if (b2 == 4) {
                i2 = 22;
            } else if (b2 == 1) {
                i2 = 10;
            } else if (b2 == 3) {
                i2 = ioBuffer.get(4) + 1 + 6;
            } else {
                throw new IllegalStateException("Unknwon address type");
            }
            if (ioBuffer.remaining() >= i2) {
                byte b3 = ioBuffer.get(1);
                LOGGER.debug("  response status: {}", (Object) SocksProxyConstants.getReplyCodeAsString(b3));
                if (b3 == 0) {
                    ioBuffer.position(ioBuffer.position() + i2);
                    setHandshakeComplete();
                    return;
                }
                throw new Exception("Proxy handshake failed - Code: 0x" + ByteUtilities.asHex(new byte[]{b3}));
            }
            return;
        }
        if (i3 > 0) {
            ioBuffer.position(ioBuffer.position() + i3);
        }
        if (i == 1 && ((Byte) getSession().getAttribute(SELECTED_AUTH_METHOD)).byteValue() == 1 && ((gSSContext = (GSSContext) getSession().getAttribute(GSS_CONTEXT)) == null || !gSSContext.isEstablished())) {
            z = true;
        }
        if (!z) {
            getSession().setAttribute(HANDSHAKE_STEP, Integer.valueOf(i + 1));
        }
        doHandshake(nextFilter);
    }

    /* access modifiers changed from: protected */
    public void closeSession(String str) {
        GSSContext gSSContext = (GSSContext) getSession().getAttribute(GSS_CONTEXT);
        if (gSSContext != null) {
            try {
                gSSContext.dispose();
            } catch (GSSException e) {
                e.printStackTrace();
                super.closeSession(str, e);
                return;
            }
        }
        super.closeSession(str);
    }
}
