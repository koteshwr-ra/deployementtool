package org.apache.mina.proxy.utils;

import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import org.apache.commons.lang3.CharEncoding;

public class ByteUtilities {
    public static final boolean isFlagSet(int i, int i2) {
        return (i & i2) > 0;
    }

    public static int networkByteOrderToInt(byte[] bArr, int i, int i2) {
        if (i2 <= 4) {
            byte b = 0;
            for (int i3 = 0; i3 < i2; i3++) {
                b = (b << 8) | (bArr[i + i3] & 255);
            }
            return b;
        }
        throw new IllegalArgumentException("Cannot handle more than 4 bytes");
    }

    public static byte[] intToNetworkByteOrder(int i, int i2) {
        byte[] bArr = new byte[i2];
        intToNetworkByteOrder(i, bArr, 0, i2);
        return bArr;
    }

    public static void intToNetworkByteOrder(int i, byte[] bArr, int i2, int i3) {
        if (i3 <= 4) {
            for (int i4 = i3 - 1; i4 >= 0; i4--) {
                bArr[i2 + i4] = (byte) (i & 255);
                i >>>= 8;
            }
            return;
        }
        throw new IllegalArgumentException("Cannot handle more than 4 bytes");
    }

    public static final byte[] writeShort(short s) {
        return writeShort(s, new byte[2], 0);
    }

    public static final byte[] writeShort(short s, byte[] bArr, int i) {
        bArr[i] = (byte) s;
        bArr[i + 1] = (byte) (s >> 8);
        return bArr;
    }

    public static final byte[] writeInt(int i) {
        return writeInt(i, new byte[4], 0);
    }

    public static final byte[] writeInt(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >> 8);
        bArr[i2 + 2] = (byte) (i >> 16);
        bArr[i2 + 3] = (byte) (i >> 24);
        return bArr;
    }

    public static final void changeWordEndianess(byte[] bArr, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3 += 4) {
            byte b = bArr[i3];
            int i4 = i3 + 3;
            bArr[i3] = bArr[i4];
            bArr[i4] = b;
            int i5 = i3 + 1;
            byte b2 = bArr[i5];
            int i6 = i3 + 2;
            bArr[i5] = bArr[i6];
            bArr[i6] = b2;
        }
    }

    public static final void changeByteEndianess(byte[] bArr, int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3 += 2) {
            byte b = bArr[i3];
            int i4 = i3 + 1;
            bArr[i3] = bArr[i4];
            bArr[i4] = b;
        }
    }

    public static final byte[] getOEMStringAsByteArray(String str) throws UnsupportedEncodingException {
        return str.getBytes("ASCII");
    }

    public static final byte[] getUTFStringAsByteArray(String str) throws UnsupportedEncodingException {
        return str.getBytes(CharEncoding.UTF_16LE);
    }

    public static final byte[] encodeString(String str, boolean z) throws UnsupportedEncodingException {
        if (z) {
            return getUTFStringAsByteArray(str);
        }
        return getOEMStringAsByteArray(str);
    }

    public static String asHex(byte[] bArr) {
        return asHex(bArr, (String) null);
    }

    public static String asHex(byte[] bArr, String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i] & 255);
            if ((bArr[i] & 255) < 16) {
                sb.append('0');
            }
            sb.append(hexString);
            if (str != null && i < bArr.length - 1) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static byte[] asByteArray(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }

    public static final int makeIntFromByte4(byte[] bArr) {
        return makeIntFromByte4(bArr, 0);
    }

    public static final int makeIntFromByte4(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | (bArr[i] << Ascii.CAN) | ((bArr[i + 1] & 255) << Ascii.DLE) | ((bArr[i + 2] & 255) << 8);
    }

    public static final int makeIntFromByte2(byte[] bArr) {
        return makeIntFromByte2(bArr, 0);
    }

    public static final int makeIntFromByte2(byte[] bArr, int i) {
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }
}
