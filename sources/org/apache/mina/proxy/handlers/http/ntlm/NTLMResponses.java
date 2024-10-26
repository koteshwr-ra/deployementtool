package org.apache.mina.proxy.handlers.http.ntlm;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang3.CharEncoding;
import org.apache.mina.proxy.handlers.socks.SocksProxyConstants;

public class NTLMResponses {
    public static byte[] LM_HASH_MAGIC_CONSTANT;

    static {
        try {
            LM_HASH_MAGIC_CONSTANT = "KGS!@#$%".getBytes(CharEncoding.US_ASCII);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static byte[] getLMResponse(String str, byte[] bArr) throws Exception {
        return lmResponse(lmHash(str), bArr);
    }

    public static byte[] getNTLMResponse(String str, byte[] bArr) throws Exception {
        return lmResponse(ntlmHash(str), bArr);
    }

    public static byte[] getNTLMv2Response(String str, String str2, String str3, byte[] bArr, byte[] bArr2, byte[] bArr3) throws Exception {
        return getNTLMv2Response(str, str2, str3, bArr, bArr2, bArr3, System.currentTimeMillis());
    }

    public static byte[] getNTLMv2Response(String str, String str2, String str3, byte[] bArr, byte[] bArr2, byte[] bArr3, long j) throws Exception {
        return lmv2Response(ntlmv2Hash(str, str2, str3), createBlob(bArr, bArr3, j), bArr2);
    }

    public static byte[] getLMv2Response(String str, String str2, String str3, byte[] bArr, byte[] bArr2) throws Exception {
        return lmv2Response(ntlmv2Hash(str, str2, str3), bArr2, bArr);
    }

    public static byte[] getNTLM2SessionResponse(String str, byte[] bArr, byte[] bArr2) throws Exception {
        byte[] ntlmHash = ntlmHash(str);
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(bArr);
        instance.update(bArr2);
        byte[] bArr3 = new byte[8];
        System.arraycopy(instance.digest(), 0, bArr3, 0, 8);
        return lmResponse(ntlmHash, bArr3);
    }

    private static byte[] lmHash(String str) throws Exception {
        byte[] bytes = str.toUpperCase().getBytes(CharEncoding.US_ASCII);
        byte[] bArr = new byte[14];
        System.arraycopy(bytes, 0, bArr, 0, Math.min(bytes.length, 14));
        Key createDESKey = createDESKey(bArr, 0);
        Key createDESKey2 = createDESKey(bArr, 7);
        Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
        instance.init(1, createDESKey);
        byte[] doFinal = instance.doFinal(LM_HASH_MAGIC_CONSTANT);
        instance.init(1, createDESKey2);
        byte[] doFinal2 = instance.doFinal(LM_HASH_MAGIC_CONSTANT);
        byte[] bArr2 = new byte[16];
        System.arraycopy(doFinal, 0, bArr2, 0, 8);
        System.arraycopy(doFinal2, 0, bArr2, 8, 8);
        return bArr2;
    }

    private static byte[] ntlmHash(String str) throws Exception {
        return MessageDigest.getInstance("MD4").digest(str.getBytes("UnicodeLittleUnmarked"));
    }

    private static byte[] ntlmv2Hash(String str, String str2, String str3) throws Exception {
        byte[] ntlmHash = ntlmHash(str3);
        return hmacMD5((str2.toUpperCase() + str).getBytes("UnicodeLittleUnmarked"), ntlmHash);
    }

    private static byte[] lmResponse(byte[] bArr, byte[] bArr2) throws Exception {
        byte[] bArr3 = new byte[21];
        System.arraycopy(bArr, 0, bArr3, 0, 16);
        Key createDESKey = createDESKey(bArr3, 0);
        Key createDESKey2 = createDESKey(bArr3, 7);
        Key createDESKey3 = createDESKey(bArr3, 14);
        Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
        instance.init(1, createDESKey);
        byte[] doFinal = instance.doFinal(bArr2);
        instance.init(1, createDESKey2);
        byte[] doFinal2 = instance.doFinal(bArr2);
        instance.init(1, createDESKey3);
        byte[] doFinal3 = instance.doFinal(bArr2);
        byte[] bArr4 = new byte[24];
        System.arraycopy(doFinal, 0, bArr4, 0, 8);
        System.arraycopy(doFinal2, 0, bArr4, 8, 8);
        System.arraycopy(doFinal3, 0, bArr4, 16, 8);
        return bArr4;
    }

    private static byte[] lmv2Response(byte[] bArr, byte[] bArr2, byte[] bArr3) throws Exception {
        byte[] bArr4 = new byte[(bArr3.length + bArr2.length)];
        System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
        System.arraycopy(bArr2, 0, bArr4, bArr3.length, bArr2.length);
        byte[] hmacMD5 = hmacMD5(bArr4, bArr);
        byte[] bArr5 = new byte[(hmacMD5.length + bArr2.length)];
        System.arraycopy(hmacMD5, 0, bArr5, 0, hmacMD5.length);
        System.arraycopy(bArr2, 0, bArr5, hmacMD5.length, bArr2.length);
        return bArr5;
    }

    private static byte[] createBlob(byte[] bArr, byte[] bArr2, long j) {
        byte[] bArr3 = {1, 1, 0, 0};
        byte[] bArr4 = {0, 0, 0, 0};
        byte[] bArr5 = {0, 0, 0, 0};
        byte[] bArr6 = {0, 0, 0, 0};
        long j2 = (j + 11644473600000L) * 10000;
        byte[] bArr7 = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr7[i] = (byte) ((int) j2);
            j2 >>>= 8;
        }
        byte[] bArr8 = new byte[(bArr2.length + 16 + 4 + bArr.length + 4)];
        System.arraycopy(bArr3, 0, bArr8, 0, 4);
        System.arraycopy(bArr4, 0, bArr8, 4, 4);
        System.arraycopy(bArr7, 0, bArr8, 8, 8);
        System.arraycopy(bArr2, 0, bArr8, 16, bArr2.length);
        int length = 16 + bArr2.length;
        System.arraycopy(bArr5, 0, bArr8, length, 4);
        int i2 = length + 4;
        System.arraycopy(bArr, 0, bArr8, i2, bArr.length);
        System.arraycopy(bArr6, 0, bArr8, i2 + bArr.length, 4);
        return bArr8;
    }

    public static byte[] hmacMD5(byte[] bArr, byte[] bArr2) throws Exception {
        byte[] bArr3 = new byte[64];
        byte[] bArr4 = new byte[64];
        for (int i = 0; i < 64; i++) {
            if (i < bArr2.length) {
                bArr3[i] = (byte) (bArr2[i] ^ 54);
                bArr4[i] = (byte) (bArr2[i] ^ SocksProxyConstants.V4_REPLY_REQUEST_FAILED_NO_IDENTD);
            } else {
                bArr3[i] = 54;
                bArr4[i] = SocksProxyConstants.V4_REPLY_REQUEST_FAILED_NO_IDENTD;
            }
        }
        byte[] bArr5 = new byte[(bArr.length + 64)];
        System.arraycopy(bArr3, 0, bArr5, 0, 64);
        System.arraycopy(bArr, 0, bArr5, 64, bArr.length);
        MessageDigest instance = MessageDigest.getInstance("MD5");
        byte[] digest = instance.digest(bArr5);
        byte[] bArr6 = new byte[(digest.length + 64)];
        System.arraycopy(bArr4, 0, bArr6, 0, 64);
        System.arraycopy(digest, 0, bArr6, 64, digest.length);
        return instance.digest(bArr6);
    }

    private static Key createDESKey(byte[] bArr, int i) {
        byte[] bArr2 = new byte[7];
        System.arraycopy(bArr, i, bArr2, 0, 7);
        byte[] bArr3 = {bArr2[0], (byte) ((bArr2[0] << 7) | ((bArr2[1] & 255) >>> 1)), (byte) ((bArr2[1] << 6) | ((bArr2[2] & 255) >>> 2)), (byte) ((bArr2[2] << 5) | ((bArr2[3] & 255) >>> 3)), (byte) ((bArr2[3] << 4) | ((bArr2[4] & 255) >>> 4)), (byte) ((bArr2[4] << 3) | ((bArr2[5] & 255) >>> 5)), (byte) ((bArr2[5] << 2) | ((bArr2[6] & 255) >>> 6)), (byte) (bArr2[6] << 1)};
        oddParity(bArr3);
        return new SecretKeySpec(bArr3, "DES");
    }

    private static void oddParity(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            if ((((b >>> 1) ^ ((((((b >>> 7) ^ (b >>> 6)) ^ (b >>> 5)) ^ (b >>> 4)) ^ (b >>> 3)) ^ (b >>> 2))) & 1) == 0) {
                bArr[i] = (byte) (bArr[i] | 1);
            } else {
                bArr[i] = (byte) (bArr[i] & -2);
            }
        }
    }
}
