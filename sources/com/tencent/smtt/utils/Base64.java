package com.tencent.smtt.utils;

import java.io.UnsupportedEncodingException;
import org.apache.commons.lang3.CharEncoding;
import org.apache.mina.proxy.handlers.socks.SocksProxyConstants;

public class Base64 {
    static final /* synthetic */ boolean a = (!Base64.class.desiredAssertionStatus());

    static abstract class a {
        public byte[] a;
        public int b;

        a() {
        }
    }

    static class b extends a {
        static final /* synthetic */ boolean g = (!Base64.class.desiredAssertionStatus());
        private static final byte[] h = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, SocksProxyConstants.V4_REPLY_REQUEST_GRANTED, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        private static final byte[] i = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, SocksProxyConstants.V4_REPLY_REQUEST_GRANTED, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        int c;
        public final boolean d;
        public final boolean e;
        public final boolean f;
        private final byte[] j;
        private int k;
        private final byte[] l;

        public b(int i2, byte[] bArr) {
            this.a = bArr;
            boolean z = true;
            this.d = (i2 & 1) == 0;
            this.e = (i2 & 2) == 0;
            this.f = (i2 & 4) == 0 ? false : z;
            this.l = (i2 & 8) == 0 ? h : i;
            this.j = new byte[2];
            this.c = 0;
            this.k = this.e ? 19 : -1;
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0094  */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x00e8  */
        /* JADX WARNING: Removed duplicated region for block: B:79:0x01c5  */
        /* JADX WARNING: Removed duplicated region for block: B:81:0x01cb  */
        /* JADX WARNING: Removed duplicated region for block: B:86:0x01d8  */
        /* JADX WARNING: Removed duplicated region for block: B:95:0x00e6 A[SYNTHETIC] */
        public boolean a(byte[] r18, int r19, int r20, boolean r21) {
            /*
                r17 = this;
                r0 = r17
                byte[] r1 = r0.l
                byte[] r2 = r0.a
                int r3 = r0.k
                int r4 = r20 + r19
                int r5 = r0.c
                r6 = -1
                r7 = 0
                r8 = 2
                r9 = 1
                if (r5 == r9) goto L_0x0031
                if (r5 == r8) goto L_0x0015
                goto L_0x0050
            L_0x0015:
                int r5 = r19 + 1
                if (r5 > r4) goto L_0x0050
                byte[] r10 = r0.j
                byte r11 = r10[r7]
                r11 = r11 & 255(0xff, float:3.57E-43)
                int r11 = r11 << 16
                byte r10 = r10[r9]
                r10 = r10 & 255(0xff, float:3.57E-43)
                int r10 = r10 << 8
                r10 = r10 | r11
                byte r11 = r18[r19]
                r11 = r11 & 255(0xff, float:3.57E-43)
                r10 = r10 | r11
                r0.c = r7
                r11 = r5
                goto L_0x0053
            L_0x0031:
                int r5 = r19 + 2
                if (r5 > r4) goto L_0x0050
                byte[] r5 = r0.j
                byte r5 = r5[r7]
                r5 = r5 & 255(0xff, float:3.57E-43)
                int r5 = r5 << 16
                int r10 = r19 + 1
                byte r11 = r18[r19]
                r11 = r11 & 255(0xff, float:3.57E-43)
                int r11 = r11 << 8
                r5 = r5 | r11
                int r11 = r10 + 1
                byte r10 = r18[r10]
                r10 = r10 & 255(0xff, float:3.57E-43)
                r10 = r10 | r5
                r0.c = r7
                goto L_0x0053
            L_0x0050:
                r11 = r19
                r10 = -1
            L_0x0053:
                r12 = 4
                r13 = 13
                r14 = 10
                if (r10 == r6) goto L_0x008f
                int r6 = r10 >> 18
                r6 = r6 & 63
                byte r6 = r1[r6]
                r2[r7] = r6
                int r6 = r10 >> 12
                r6 = r6 & 63
                byte r6 = r1[r6]
                r2[r9] = r6
                int r6 = r10 >> 6
                r6 = r6 & 63
                byte r6 = r1[r6]
                r2[r8] = r6
                r6 = r10 & 63
                byte r6 = r1[r6]
                r10 = 3
                r2[r10] = r6
                int r3 = r3 + -1
                if (r3 != 0) goto L_0x008d
                boolean r3 = r0.f
                if (r3 == 0) goto L_0x0085
                r3 = 5
                r2[r12] = r13
                goto L_0x0086
            L_0x0085:
                r3 = 4
            L_0x0086:
                int r6 = r3 + 1
                r2[r3] = r14
            L_0x008a:
                r3 = 19
                goto L_0x0090
            L_0x008d:
                r6 = 4
                goto L_0x0090
            L_0x008f:
                r6 = 0
            L_0x0090:
                int r10 = r11 + 3
                if (r10 > r4) goto L_0x00e6
                byte r15 = r18[r11]
                r15 = r15 & 255(0xff, float:3.57E-43)
                int r15 = r15 << 16
                int r16 = r11 + 1
                byte r5 = r18[r16]
                r5 = r5 & 255(0xff, float:3.57E-43)
                int r5 = r5 << 8
                r5 = r5 | r15
                int r11 = r11 + 2
                byte r11 = r18[r11]
                r11 = r11 & 255(0xff, float:3.57E-43)
                r5 = r5 | r11
                int r11 = r5 >> 18
                r11 = r11 & 63
                byte r11 = r1[r11]
                r2[r6] = r11
                int r11 = r6 + 1
                int r15 = r5 >> 12
                r15 = r15 & 63
                byte r15 = r1[r15]
                r2[r11] = r15
                int r11 = r6 + 2
                int r15 = r5 >> 6
                r15 = r15 & 63
                byte r15 = r1[r15]
                r2[r11] = r15
                int r11 = r6 + 3
                r5 = r5 & 63
                byte r5 = r1[r5]
                r2[r11] = r5
                int r6 = r6 + 4
                int r3 = r3 + -1
                if (r3 != 0) goto L_0x00e4
                boolean r3 = r0.f
                if (r3 == 0) goto L_0x00dd
                int r3 = r6 + 1
                r2[r6] = r13
                r6 = r3
            L_0x00dd:
                int r3 = r6 + 1
                r2[r6] = r14
                r6 = r3
                r11 = r10
                goto L_0x008a
            L_0x00e4:
                r11 = r10
                goto L_0x0090
            L_0x00e6:
                if (r21 == 0) goto L_0x01d8
                int r5 = r0.c
                int r10 = r11 - r5
                int r15 = r4 + -1
                r16 = 61
                if (r10 != r15) goto L_0x0139
                if (r5 <= 0) goto L_0x00fa
                byte[] r5 = r0.j
                byte r5 = r5[r7]
                r7 = 1
                goto L_0x0100
            L_0x00fa:
                int r5 = r11 + 1
                byte r8 = r18[r11]
                r11 = r5
                r5 = r8
            L_0x0100:
                r5 = r5 & 255(0xff, float:3.57E-43)
                int r5 = r5 << r12
                int r8 = r0.c
                int r8 = r8 - r7
                r0.c = r8
                int r7 = r6 + 1
                int r8 = r5 >> 6
                r8 = r8 & 63
                byte r8 = r1[r8]
                r2[r6] = r8
                int r6 = r7 + 1
                r5 = r5 & 63
                byte r1 = r1[r5]
                r2[r7] = r1
                boolean r1 = r0.d
                if (r1 == 0) goto L_0x0126
                int r1 = r6 + 1
                r2[r6] = r16
                int r6 = r1 + 1
                r2[r1] = r16
            L_0x0126:
                boolean r1 = r0.e
                if (r1 == 0) goto L_0x01bc
                boolean r1 = r0.f
                if (r1 == 0) goto L_0x0133
                int r1 = r6 + 1
                r2[r6] = r13
                r6 = r1
            L_0x0133:
                int r1 = r6 + 1
                r2[r6] = r14
                goto L_0x01bb
            L_0x0139:
                int r10 = r11 - r5
                int r12 = r4 + -2
                if (r10 != r12) goto L_0x01a4
                if (r5 <= r9) goto L_0x0147
                byte[] r5 = r0.j
                byte r5 = r5[r7]
                r7 = 1
                goto L_0x014d
            L_0x0147:
                int r5 = r11 + 1
                byte r10 = r18[r11]
                r11 = r5
                r5 = r10
            L_0x014d:
                r5 = r5 & 255(0xff, float:3.57E-43)
                int r5 = r5 << r14
                int r10 = r0.c
                if (r10 <= 0) goto L_0x015b
                byte[] r10 = r0.j
                int r12 = r7 + 1
                byte r7 = r10[r7]
                goto L_0x0162
            L_0x015b:
                int r10 = r11 + 1
                byte r11 = r18[r11]
                r12 = r7
                r7 = r11
                r11 = r10
            L_0x0162:
                r7 = r7 & 255(0xff, float:3.57E-43)
                int r7 = r7 << r8
                r5 = r5 | r7
                int r7 = r0.c
                int r7 = r7 - r12
                r0.c = r7
                int r7 = r6 + 1
                int r8 = r5 >> 12
                r8 = r8 & 63
                byte r8 = r1[r8]
                r2[r6] = r8
                int r6 = r7 + 1
                int r8 = r5 >> 6
                r8 = r8 & 63
                byte r8 = r1[r8]
                r2[r7] = r8
                int r7 = r6 + 1
                r5 = r5 & 63
                byte r1 = r1[r5]
                r2[r6] = r1
                boolean r1 = r0.d
                if (r1 == 0) goto L_0x0190
                int r1 = r7 + 1
                r2[r7] = r16
                r7 = r1
            L_0x0190:
                boolean r1 = r0.e
                if (r1 == 0) goto L_0x01a2
                boolean r1 = r0.f
                if (r1 == 0) goto L_0x019d
                int r1 = r7 + 1
                r2[r7] = r13
                r7 = r1
            L_0x019d:
                int r1 = r7 + 1
                r2[r7] = r14
                r7 = r1
            L_0x01a2:
                r6 = r7
                goto L_0x01bc
            L_0x01a4:
                boolean r1 = r0.e
                if (r1 == 0) goto L_0x01bc
                if (r6 <= 0) goto L_0x01bc
                r1 = 19
                if (r3 == r1) goto L_0x01bc
                boolean r1 = r0.f
                if (r1 == 0) goto L_0x01b7
                int r1 = r6 + 1
                r2[r6] = r13
                r6 = r1
            L_0x01b7:
                int r1 = r6 + 1
                r2[r6] = r14
            L_0x01bb:
                r6 = r1
            L_0x01bc:
                boolean r1 = g
                if (r1 != 0) goto L_0x01cb
                int r1 = r0.c
                if (r1 != 0) goto L_0x01c5
                goto L_0x01cb
            L_0x01c5:
                java.lang.AssertionError r1 = new java.lang.AssertionError
                r1.<init>()
                throw r1
            L_0x01cb:
                boolean r1 = g
                if (r1 != 0) goto L_0x0201
                if (r11 != r4) goto L_0x01d2
                goto L_0x0201
            L_0x01d2:
                java.lang.AssertionError r1 = new java.lang.AssertionError
                r1.<init>()
                throw r1
            L_0x01d8:
                int r1 = r4 + -1
                if (r11 != r1) goto L_0x01e9
                byte[] r1 = r0.j
                int r2 = r0.c
                int r4 = r2 + 1
                r0.c = r4
                byte r4 = r18[r11]
                r1[r2] = r4
                goto L_0x0201
            L_0x01e9:
                int r4 = r4 - r8
                if (r11 != r4) goto L_0x0201
                byte[] r1 = r0.j
                int r2 = r0.c
                int r4 = r2 + 1
                r0.c = r4
                byte r5 = r18[r11]
                r1[r2] = r5
                int r2 = r4 + 1
                r0.c = r2
                int r11 = r11 + r9
                byte r2 = r18[r11]
                r1[r4] = r2
            L_0x0201:
                r0.b = r6
                r0.k = r3
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.Base64.b.a(byte[], int, int, boolean):boolean");
        }
    }

    private Base64() {
    }

    public static byte[] a(byte[] bArr, int i) {
        return a(bArr, 0, bArr.length, i);
    }

    public static byte[] a(byte[] bArr, int i, int i2, int i3) {
        b bVar = new b(i3, (byte[]) null);
        int i4 = (i2 / 3) * 4;
        int i5 = 2;
        if (!bVar.d) {
            int i6 = i2 % 3;
            if (i6 == 1) {
                i4 += 2;
            } else if (i6 == 2) {
                i4 += 3;
            }
        } else if (i2 % 3 > 0) {
            i4 += 4;
        }
        if (bVar.e && i2 > 0) {
            int i7 = ((i2 - 1) / 57) + 1;
            if (!bVar.f) {
                i5 = 1;
            }
            i4 += i7 * i5;
        }
        bVar.a = new byte[i4];
        bVar.a(bArr, i, i2, true);
        if (a || bVar.b == i4) {
            return bVar.a;
        }
        throw new AssertionError();
    }

    public static String encodeToString(byte[] bArr, int i) {
        try {
            return new String(a(bArr, i), CharEncoding.US_ASCII);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
