package com.tencent.smtt.utils;

import com.google.common.base.Ascii;
import com.tencent.smtt.sdk.stat.a;
import java.security.Provider;
import java.security.Security;
import java.util.Random;

public class h {
    private static final char[] a = "0123456789abcdef".toCharArray();
    private static h b;
    private String c = (this.e + String.valueOf(new Random().nextInt(89999999) + 10000000));
    private String d;
    private String e = String.valueOf(new Random().nextInt(89999999) + 10000000);

    private h() {
    }

    public static synchronized h a() {
        h hVar;
        synchronized (h.class) {
            if (b == null) {
                b = new h();
            }
            hVar = b;
        }
        return hVar;
    }

    private String b(byte[] bArr) {
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

    public byte[] a(byte[] bArr) throws Exception {
        return a.a(this.e.getBytes(), bArr, 1);
    }

    public void b() throws Exception {
        Security.addProvider((Provider) Class.forName("com.android.org.bouncycastle.jce.provider.BouncyCastleProvider", true, ClassLoader.getSystemClassLoader()).newInstance());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:?, code lost:
        b();
        r2 = javax.crypto.Cipher.getInstance("RSA/ECB/NoPadding");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001b, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String c() throws java.lang.Exception {
        /*
            r5 = this;
            java.lang.String r0 = "RSA/ECB/NoPadding"
            java.lang.String r1 = r5.d
            if (r1 != 0) goto L_0x0046
            java.lang.String r1 = r5.c
            byte[] r1 = r1.getBytes()
            r2 = 0
            javax.crypto.Cipher r2 = javax.crypto.Cipher.getInstance(r0)     // Catch:{ Exception -> 0x0012 }
            goto L_0x001e
        L_0x0012:
            r5.b()     // Catch:{ Exception -> 0x001a }
            javax.crypto.Cipher r2 = javax.crypto.Cipher.getInstance(r0)     // Catch:{ Exception -> 0x001a }
            goto L_0x001e
        L_0x001a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x001e:
            java.lang.String r0 = "MCwwDQYJKoZIhvcNAQEBBQADGwAwGAIRAMRB/Q0hTCD+XtnQhpQJefUCAwEAAQ=="
            byte[] r0 = r0.getBytes()
            java.security.spec.X509EncodedKeySpec r3 = new java.security.spec.X509EncodedKeySpec
            r4 = 0
            byte[] r0 = android.util.Base64.decode(r0, r4)
            r3.<init>(r0)
            java.lang.String r0 = "RSA"
            java.security.KeyFactory r0 = java.security.KeyFactory.getInstance(r0)
            java.security.PublicKey r0 = r0.generatePublic(r3)
            r3 = 1
            r2.init(r3, r0)
            byte[] r0 = r2.doFinal(r1)
            java.lang.String r0 = r5.b(r0)
            r5.d = r0
        L_0x0046:
            java.lang.String r0 = r5.d
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.h.c():java.lang.String");
    }
}
