package com.ciot.base.util;

import com.ciot.base.constant.NetConstant;
import com.google.common.base.Ascii;
import org.apache.commons.lang3.StringUtils;

public class ByteUtils {
    public static byte[] changeByteBig(int i) {
        return new byte[]{(byte) (i >> 24), (byte) ((i << 8) >> 24), (byte) ((i << 16) >> 24), (byte) ((i << 24) >> 24)};
    }

    public static byte[] changeByteSmall(int i) {
        return new byte[]{(byte) ((i << 24) >> 24), (byte) ((i << 16) >> 24), (byte) ((i << 8) >> 24), (byte) (i >> 24)};
    }

    public static byte[] int2bytes(int i) {
        byte[] bArr = new byte[4];
        bArr[3] = (byte) (i & 255);
        bArr[2] = (byte) ((65280 & i) >> 8);
        bArr[1] = (byte) ((16711680 & i) >> 16);
        bArr[0] = (byte) ((i & -16777216) >> 24);
        return bArr;
    }

    public static byte[] short2Bytes(short s) {
        byte[] bArr = new byte[2];
        bArr[1] = (byte) (s & 255);
        bArr[0] = (byte) ((s >> 8) & 255);
        return bArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: short} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: short} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] short2bytes(short r6) {
        /*
            r0 = 2
            byte[] r1 = new byte[r0]
            r2 = 0
        L_0x0004:
            if (r2 >= r0) goto L_0x001a
            int r3 = 1 - r2
            java.lang.Integer r4 = new java.lang.Integer
            r5 = r6 & 255(0xff, float:3.57E-43)
            r4.<init>(r5)
            byte r4 = r4.byteValue()
            r1[r3] = r4
            int r6 = r6 >> 8
            int r2 = r2 + 1
            goto L_0x0004
        L_0x001a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ciot.base.util.ByteUtils.short2bytes(short):byte[]");
    }

    public static short bytes2short(byte[] bArr) {
        return (short) (((short) (((short) (bArr[0] & 255)) << 8)) | ((short) (bArr[1] & 255)));
    }

    public static int bytes2int(byte[] bArr) {
        return ((bArr[0] << Ascii.CAN) & -16777216) | (bArr[3] & 255) | ((bArr[2] << 8) & 65280) | ((bArr[1] << Ascii.DLE) & 16711680);
    }

    public static String byte2hex(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = NetConstant.PAGE_ID_HOME + hexString;
            }
            str = str + StringUtils.SPACE + hexString;
        }
        return str;
    }

    public static byte getCheck(byte[] bArr) {
        int i = 0;
        if (bArr != null) {
            for (int i2 = 1; i2 < bArr.length - 2; i2++) {
                i += bArr[i2];
            }
        }
        return (byte) (i & 255);
    }

    public static byte[] concat(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static byte[] long2Bytes(long j) {
        byte[] bArr = new byte[8];
        int i = 0;
        while (i < 8) {
            int i2 = i + 1;
            bArr[i] = (byte) ((int) ((j >> (64 - (i2 * 8))) & 255));
            i = i2;
        }
        return bArr;
    }

    public static long bytes2Long(byte[] bArr) {
        long j = 0;
        for (int i = 0; i < 8; i++) {
            j = (j << 8) | ((long) (bArr[i] & 255));
        }
        return j;
    }

    public static short bytes2Short(byte[] bArr) {
        return (short) (((bArr[0] & 255) << 8) | (bArr[1] & 255));
    }
}
