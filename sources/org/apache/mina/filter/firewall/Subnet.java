package org.apache.mina.filter.firewall;

import java.net.Inet4Address;
import java.net.InetAddress;

public class Subnet {
    private static final int BYTE_MASK = 255;
    private static final int IP_MASK = Integer.MIN_VALUE;
    private InetAddress subnet;
    private int subnetInt;
    private int subnetMask;
    private int suffix;

    public Subnet(InetAddress inetAddress, int i) {
        if (inetAddress == null) {
            throw new IllegalArgumentException("Subnet address can not be null");
        } else if (!(inetAddress instanceof Inet4Address)) {
            throw new IllegalArgumentException("Only IPv4 supported");
        } else if (i < 0 || i > 32) {
            throw new IllegalArgumentException("Mask has to be an integer between 0 and 32");
        } else {
            this.subnet = inetAddress;
            this.subnetInt = toInt(inetAddress);
            this.suffix = i;
            this.subnetMask = Integer.MIN_VALUE >> (i - 1);
        }
    }

    private int toInt(InetAddress inetAddress) {
        byte b = 0;
        for (byte b2 : inetAddress.getAddress()) {
            b = (b << 8) | (b2 & 255);
        }
        return b;
    }

    private int toSubnet(InetAddress inetAddress) {
        return toInt(inetAddress) & this.subnetMask;
    }

    public boolean inSubnet(InetAddress inetAddress) {
        return toSubnet(inetAddress) == this.subnetInt;
    }

    public String toString() {
        return this.subnet.getHostAddress() + "/" + this.suffix;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Subnet)) {
            return false;
        }
        Subnet subnet2 = (Subnet) obj;
        if (subnet2.subnetInt == this.subnetInt && subnet2.suffix == this.suffix) {
            return true;
        }
        return false;
    }
}
