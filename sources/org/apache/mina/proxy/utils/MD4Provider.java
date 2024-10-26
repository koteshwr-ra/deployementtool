package org.apache.mina.proxy.utils;

import java.security.Provider;

public class MD4Provider extends Provider {
    public static final String INFO = "MINA MD4 Provider v1.0";
    public static final String PROVIDER_NAME = "MINA";
    public static final double VERSION = 1.0d;
    private static final long serialVersionUID = -1616816866935565456L;

    public MD4Provider() {
        super(PROVIDER_NAME, 1.0d, INFO);
        put("MessageDigest.MD4", MD4.class.getName());
    }
}
