package org.apache.mina.proxy.utils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.security.sasl.AuthenticationException;
import javax.security.sasl.SaslException;

public class StringUtilities {
    public static boolean isLws(byte b) {
        return b == 9 || b == 10 || b == 13 || b == 32;
    }

    public static String getDirectiveValue(HashMap<String, String> hashMap, String str, boolean z) throws AuthenticationException {
        String str2 = hashMap.get(str);
        if (str2 != null) {
            return str2;
        }
        if (!z) {
            return "";
        }
        throw new AuthenticationException("\"" + str + "\" mandatory directive is missing");
    }

    public static void copyDirective(HashMap<String, String> hashMap, StringBuilder sb, String str) {
        String str2 = hashMap.get(str);
        if (str2 != null) {
            sb.append(str);
            sb.append(" = \"");
            sb.append(str2);
            sb.append("\", ");
        }
    }

    public static String copyDirective(HashMap<String, String> hashMap, HashMap<String, String> hashMap2, String str) {
        String str2 = hashMap.get(str);
        if (str2 != null) {
            hashMap2.put(str, str2);
        }
        return str2;
    }

    public static HashMap<String, String> parseDirectives(byte[] bArr) throws SaslException {
        int i;
        HashMap<String, String> hashMap = new HashMap<>();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(10);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(10);
        int skipLws = skipLws(bArr, 0);
        while (true) {
            boolean z = false;
            boolean z2 = true;
            boolean z3 = false;
            while (i < bArr.length) {
                byte b = bArr[i];
                if (z2) {
                    if (b == 44) {
                        if (byteArrayOutputStream.size() == 0) {
                            i = skipLws(bArr, i + 1);
                        } else {
                            throw new SaslException("Directive key contains a ',':" + byteArrayOutputStream);
                        }
                    } else if (b == 61) {
                        if (byteArrayOutputStream.size() != 0) {
                            i = skipLws(bArr, i + 1);
                            if (i < bArr.length) {
                                if (bArr[i] == 34) {
                                    i++;
                                    z = true;
                                }
                                z2 = false;
                            } else {
                                throw new SaslException("Valueless directive found: " + byteArrayOutputStream.toString());
                            }
                        } else {
                            throw new SaslException("Empty directive key");
                        }
                    } else if (isLws(b)) {
                        i = skipLws(bArr, i + 1);
                        if (i >= bArr.length) {
                            throw new SaslException("'=' expected after key: " + byteArrayOutputStream.toString());
                        } else if (bArr[i] != 61) {
                            throw new SaslException("'=' expected after key: " + byteArrayOutputStream.toString());
                        }
                    } else {
                        byteArrayOutputStream.write(b);
                    }
                } else if (z) {
                    if (b == 92) {
                        i++;
                        if (i < bArr.length) {
                            byteArrayOutputStream2.write(bArr[i]);
                        } else {
                            throw new SaslException("Unmatched quote found for directive: " + byteArrayOutputStream.toString() + " with value: " + byteArrayOutputStream2.toString());
                        }
                    } else if (b == 34) {
                        i++;
                        z = false;
                        z3 = true;
                    } else {
                        byteArrayOutputStream2.write(b);
                    }
                } else if (isLws(b) || b == 44) {
                    extractDirective(hashMap, byteArrayOutputStream.toString(), byteArrayOutputStream2.toString());
                    byteArrayOutputStream.reset();
                    byteArrayOutputStream2.reset();
                    skipLws = skipLws(bArr, i + 1);
                } else if (!z3) {
                    byteArrayOutputStream2.write(b);
                } else {
                    throw new SaslException("Expecting comma or linear whitespace after quoted string: \"" + byteArrayOutputStream2.toString() + "\"");
                }
                i++;
            }
            if (!z) {
                if (byteArrayOutputStream.size() > 0) {
                    extractDirective(hashMap, byteArrayOutputStream.toString(), byteArrayOutputStream2.toString());
                }
                return hashMap;
            }
            throw new SaslException("Unmatched quote found for directive: " + byteArrayOutputStream.toString() + " with value: " + byteArrayOutputStream2.toString());
        }
    }

    private static void extractDirective(HashMap<String, String> hashMap, String str, String str2) throws SaslException {
        if (hashMap.get(str) == null) {
            hashMap.put(str, str2);
            return;
        }
        throw new SaslException("Peer sent more than one " + str + " directive");
    }

    private static int skipLws(byte[] bArr, int i) {
        while (i < bArr.length && isLws(bArr[i])) {
            i++;
        }
        return i;
    }

    public static String stringTo8859_1(String str) throws UnsupportedEncodingException {
        return str == null ? "" : new String(str.getBytes("UTF8"), "8859_1");
    }

    public static String getSingleValuedHeader(Map<String, List<String>> map, String str) {
        List list = map.get(str);
        if (list == null) {
            return null;
        }
        if (list.size() <= 1) {
            return (String) list.get(0);
        }
        throw new IllegalArgumentException("Header with key [\"" + str + "\"] isn't single valued !");
    }

    public static void addValueToHeader(Map<String, List<String>> map, String str, String str2, boolean z) {
        List list = map.get(str);
        if (list == null) {
            list = new ArrayList(1);
            map.put(str, list);
        }
        if (!z || list.size() != 1) {
            list.add(str2);
        } else {
            list.set(0, str2);
        }
    }
}
