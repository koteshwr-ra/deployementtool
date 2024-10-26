package org.apache.mina.proxy.handlers.http.digest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import javax.security.sasl.AuthenticationException;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.proxy.session.ProxyIoSession;
import org.apache.mina.proxy.utils.ByteUtilities;
import org.apache.mina.proxy.utils.StringUtilities;

public class DigestUtilities {
    public static final String SESSION_HA1 = (DigestUtilities.class + ".SessionHA1");
    public static final String[] SUPPORTED_QOPS = {"auth", "auth-int"};
    private static MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String computeResponseValue(IoSession ioSession, HashMap<String, String> hashMap, String str, String str2, String str3, String str4) throws AuthenticationException, UnsupportedEncodingException {
        byte[] bArr;
        byte[] digest;
        byte[] digest2;
        byte[] digest3;
        byte[] digest4;
        byte[] digest5;
        boolean equalsIgnoreCase = "md5-sess".equalsIgnoreCase(StringUtilities.getDirectiveValue(hashMap, "algorithm", false));
        if (!equalsIgnoreCase || ioSession.getAttribute(SESSION_HA1) == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(StringUtilities.stringTo8859_1(StringUtilities.getDirectiveValue(hashMap, "username", true)));
            sb.append(':');
            String stringTo8859_1 = StringUtilities.stringTo8859_1(StringUtilities.getDirectiveValue(hashMap, "realm", false));
            if (stringTo8859_1 != null) {
                sb.append(stringTo8859_1);
            }
            sb.append(':');
            sb.append(str2);
            if (equalsIgnoreCase) {
                synchronized (md5) {
                    md5.reset();
                    digest5 = md5.digest(sb.toString().getBytes(str3));
                }
                synchronized (md5) {
                    md5.reset();
                    bArr = md5.digest((ByteUtilities.asHex(digest5) + ':' + StringUtilities.stringTo8859_1(StringUtilities.getDirectiveValue(hashMap, "nonce", true)) + ':' + StringUtilities.stringTo8859_1(StringUtilities.getDirectiveValue(hashMap, "cnonce", true))).getBytes(str3));
                }
                ioSession.setAttribute(SESSION_HA1, bArr);
            } else {
                synchronized (md5) {
                    md5.reset();
                    digest4 = md5.digest(sb.toString().getBytes(str3));
                }
                bArr = digest4;
            }
        } else {
            bArr = (byte[]) ioSession.getAttribute(SESSION_HA1);
        }
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(':');
        sb2.append(StringUtilities.getDirectiveValue(hashMap, "uri", false));
        String directiveValue = StringUtilities.getDirectiveValue(hashMap, "qop", false);
        if ("auth-int".equalsIgnoreCase(directiveValue)) {
            ProxyIoSession proxyIoSession = (ProxyIoSession) ioSession.getAttribute(ProxyIoSession.PROXY_SESSION);
            synchronized (md5) {
                md5.reset();
                digest3 = md5.digest(str4.getBytes(proxyIoSession.getCharsetName()));
            }
            sb2.append(':');
            sb2.append(digest3);
        }
        synchronized (md5) {
            md5.reset();
            digest = md5.digest(sb2.toString().getBytes(str3));
        }
        synchronized (md5) {
            md5.reset();
            digest2 = md5.digest((ByteUtilities.asHex(bArr) + ':' + StringUtilities.getDirectiveValue(hashMap, "nonce", true) + ":00000001:" + StringUtilities.getDirectiveValue(hashMap, "cnonce", true) + ':' + directiveValue + ':' + ByteUtilities.asHex(digest)).getBytes(str3));
        }
        return ByteUtilities.asHex(digest2);
    }
}
