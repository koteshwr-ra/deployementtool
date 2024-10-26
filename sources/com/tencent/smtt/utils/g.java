package com.tencent.smtt.utils;

import android.util.Base64;
import com.google.common.base.Ascii;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class g {
    protected static final char[] a = "0123456789abcdef".toCharArray();
    private static String b = "";
    private static byte[] c;
    private static g f = null;
    private static String g;
    private Cipher d = null;
    private Cipher e = null;

    private g() throws Exception {
        g = String.valueOf(new Random().nextInt(89999999) + 10000000) + String.valueOf(new Random().nextInt(89999999) + 10000000) + String.valueOf(new Random().nextInt(89999999) + 10000000);
        String str = "00000000";
        for (int i = 0; i < 12; i++) {
            str = str + String.valueOf(new Random().nextInt(89999999) + 10000000);
        }
        c = (str + g).getBytes();
        this.d = Cipher.getInstance("RSA/ECB/NoPadding");
        this.d.init(1, KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode((d() + e()).getBytes(), 0))));
        b = b(this.d.doFinal(c));
        SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(g.getBytes()));
        Cipher instance = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        this.e = instance;
        instance.init(1, generateSecret);
    }

    public static g a() {
        try {
            if (f == null) {
                f = new g();
            }
            return f;
        } catch (Exception e2) {
            f = null;
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] a(byte[] bArr, String str) throws Exception {
        SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(str.getBytes()));
        Cipher instance = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        instance.init(1, generateSecret);
        return instance.doFinal(bArr);
    }

    public static String b(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b2 = bArr[i] & 255;
            int i2 = i * 2;
            char[] cArr2 = a;
            cArr[i2] = cArr2[b2 >>> 4];
            cArr[i2 + 1] = cArr2[b2 & Ascii.SI];
        }
        return new String(cArr);
    }

    public static byte[] b(byte[] bArr, String str) {
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(str.getBytes()));
            Cipher instance = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            instance.init(2, generateSecret);
            return instance.doFinal(bArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String c() {
        return g;
    }

    private String d() {
        return "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDcEQ3TCNWPBqgIiY7WQ/IqTOTTV2w8aZ/GPm68FK0";
    }

    private String e() {
        return "fAJBemZKtYR3Li46VJ+Hwnor7ZpQnblGWPFaLv5JoPqvavgB0GInuhm+T+syPs1mw0uPLWaqwvZsCfoaIvUuxy5xHJgmWARrK4/9pHyDxRlZte0PCIoR1ko5B8lVVH1X1dQIDAQAB";
    }

    public byte[] a(byte[] bArr) throws Exception {
        return this.e.doFinal(bArr);
    }

    public String b() {
        return b;
    }

    public byte[] c(byte[] bArr) {
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(g.getBytes()));
            Cipher instance = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            instance.init(2, generateSecret);
            return instance.doFinal(bArr);
        } catch (Exception e2) {
            TbsLog.i(e2);
            return null;
        }
    }
}
