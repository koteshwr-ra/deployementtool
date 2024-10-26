package org.apache.mina.proxy.handlers.socks;

import java.net.InetSocketAddress;
import org.apache.mina.proxy.handlers.ProxyRequest;

public class SocksProxyRequest extends ProxyRequest {
    private byte commandCode;
    private String host;
    private String password;
    private int port;
    private byte protocolVersion;
    private String serviceKerberosName;
    private String userName;

    public SocksProxyRequest(byte b, byte b2, InetSocketAddress inetSocketAddress, String str) {
        super(inetSocketAddress);
        this.protocolVersion = b;
        this.commandCode = b2;
        this.userName = str;
    }

    public SocksProxyRequest(byte b, String str, int i, String str2) {
        this.protocolVersion = 4;
        this.commandCode = b;
        this.userName = str2;
        this.host = str;
        this.port = i;
    }

    public byte[] getIpAddress() {
        if (getEndpointAddress() == null) {
            return SocksProxyConstants.FAKE_IP;
        }
        return getEndpointAddress().getAddress().getAddress();
    }

    public byte[] getPort() {
        byte[] bArr = new byte[2];
        int port2 = getEndpointAddress() == null ? this.port : getEndpointAddress().getPort();
        bArr[1] = (byte) port2;
        bArr[0] = (byte) (port2 >> 8);
        return bArr;
    }

    public byte getCommandCode() {
        return this.commandCode;
    }

    public byte getProtocolVersion() {
        return this.protocolVersion;
    }

    public String getUserName() {
        return this.userName;
    }

    public final synchronized String getHost() {
        InetSocketAddress endpointAddress;
        if (this.host == null && (endpointAddress = getEndpointAddress()) != null && !endpointAddress.isUnresolved()) {
            this.host = getEndpointAddress().getHostName();
        }
        return this.host;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getServiceKerberosName() {
        return this.serviceKerberosName;
    }

    public void setServiceKerberosName(String str) {
        this.serviceKerberosName = str;
    }
}
