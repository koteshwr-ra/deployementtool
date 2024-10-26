package com.alibaba.fastjson.util;

import com.tencent.smtt.sdk.TbsListener;
import java.lang.reflect.Array;
import java.math.BigInteger;

public final class RyuDouble {
    private static final int[][] POW5_INV_SPLIT;
    private static final int[][] POW5_SPLIT;

    static {
        int i;
        Class<int> cls = int.class;
        POW5_SPLIT = (int[][]) Array.newInstance(cls, new int[]{TbsListener.ErrorCode.TEST_THROWABLE_IS_NULL, 4});
        POW5_INV_SPLIT = (int[][]) Array.newInstance(cls, new int[]{291, 4});
        BigInteger subtract = BigInteger.ONE.shiftLeft(31).subtract(BigInteger.ONE);
        BigInteger subtract2 = BigInteger.ONE.shiftLeft(31).subtract(BigInteger.ONE);
        int i2 = 0;
        while (i2 < 326) {
            BigInteger pow = BigInteger.valueOf(5).pow(i2);
            int bitLength = pow.bitLength();
            if (i2 == 0) {
                i = 1;
            } else {
                i = (int) ((((((long) i2) * 23219280) + 10000000) - 1) / 10000000);
            }
            if (i == bitLength) {
                if (i2 < POW5_SPLIT.length) {
                    for (int i3 = 0; i3 < 4; i3++) {
                        POW5_SPLIT[i2][i3] = pow.shiftRight((bitLength - 121) + ((3 - i3) * 31)).and(subtract).intValue();
                    }
                }
                if (i2 < POW5_INV_SPLIT.length) {
                    BigInteger add = BigInteger.ONE.shiftLeft(bitLength + TbsListener.ErrorCode.THREAD_INIT_ERROR).divide(pow).add(BigInteger.ONE);
                    for (int i4 = 0; i4 < 4; i4++) {
                        if (i4 == 0) {
                            POW5_INV_SPLIT[i2][i4] = add.shiftRight((3 - i4) * 31).intValue();
                        } else {
                            POW5_INV_SPLIT[i2][i4] = add.shiftRight((3 - i4) * 31).and(subtract2).intValue();
                        }
                    }
                }
                i2++;
            } else {
                throw new IllegalStateException(bitLength + " != " + i);
            }
        }
    }

    public static String toString(double d) {
        char[] cArr = new char[24];
        return new String(cArr, 0, toString(d, cArr, 0));
    }

    /* JADX WARNING: Removed duplicated region for block: B:237:0x05d6  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x05d8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int toString(double r41, char[] r43, int r44) {
        /*
            boolean r0 = java.lang.Double.isNaN(r41)
            if (r0 == 0) goto L_0x001b
            int r0 = r44 + 1
            r1 = 78
            r43[r44] = r1
            int r1 = r0 + 1
            r2 = 97
            r43[r0] = r2
            int r0 = r1 + 1
            r2 = 78
            r43[r1] = r2
        L_0x0018:
            int r0 = r0 - r44
            return r0
        L_0x001b:
            r0 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            r2 = 105(0x69, float:1.47E-43)
            r3 = 110(0x6e, float:1.54E-43)
            int r4 = (r41 > r0 ? 1 : (r41 == r0 ? 0 : -1))
            if (r4 != 0) goto L_0x0050
            int r0 = r44 + 1
            r1 = 73
            r43[r44] = r1
            int r1 = r0 + 1
            r43[r0] = r3
            int r0 = r1 + 1
            r4 = 102(0x66, float:1.43E-43)
            r43[r1] = r4
            int r1 = r0 + 1
            r43[r0] = r2
            int r0 = r1 + 1
            r43[r1] = r3
            int r1 = r0 + 1
            r43[r0] = r2
            int r0 = r1 + 1
            r2 = 116(0x74, float:1.63E-43)
            r43[r1] = r2
            int r1 = r0 + 1
            r2 = 121(0x79, float:1.7E-43)
            r43[r0] = r2
        L_0x004d:
            int r1 = r1 - r44
            return r1
        L_0x0050:
            r0 = -4503599627370496(0xfff0000000000000, double:-Infinity)
            r4 = 45
            int r5 = (r41 > r0 ? 1 : (r41 == r0 ? 0 : -1))
            if (r5 != 0) goto L_0x0085
            int r0 = r44 + 1
            r43[r44] = r4
            int r1 = r0 + 1
            r4 = 73
            r43[r0] = r4
            int r0 = r1 + 1
            r43[r1] = r3
            int r1 = r0 + 1
            r4 = 102(0x66, float:1.43E-43)
            r43[r0] = r4
            int r0 = r1 + 1
            r43[r1] = r2
            int r1 = r0 + 1
            r43[r0] = r3
            int r0 = r1 + 1
            r43[r1] = r2
            int r1 = r0 + 1
            r2 = 116(0x74, float:1.63E-43)
            r43[r0] = r2
            int r0 = r1 + 1
            r2 = 121(0x79, float:1.7E-43)
            r43[r1] = r2
            goto L_0x0018
        L_0x0085:
            long r0 = java.lang.Double.doubleToLongBits(r41)
            r2 = 46
            r3 = 48
            r5 = 0
            int r7 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x00a1
            int r0 = r44 + 1
            r43[r44] = r3
            int r1 = r0 + 1
            r43[r0] = r2
            int r0 = r1 + 1
            r43[r1] = r3
            goto L_0x0018
        L_0x00a1:
            r8 = -9223372036854775808
            int r10 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x00b8
            int r0 = r44 + 1
            r43[r44] = r4
            int r1 = r0 + 1
            r43[r0] = r3
            int r0 = r1 + 1
            r43[r1] = r2
            int r1 = r0 + 1
            r43[r0] = r3
            goto L_0x004d
        L_0x00b8:
            r8 = 52
            long r8 = r0 >>> r8
            r10 = 2047(0x7ff, double:1.0114E-320)
            long r8 = r8 & r10
            int r9 = (int) r8
            r10 = 4503599627370495(0xfffffffffffff, double:2.225073858507201E-308)
            long r0 = r0 & r10
            if (r9 != 0) goto L_0x00cb
            r8 = -1074(0xfffffffffffffbce, float:NaN)
            goto L_0x00d2
        L_0x00cb:
            int r8 = r9 + -1023
            int r8 = r8 + -52
            r10 = 4503599627370496(0x10000000000000, double:2.2250738585072014E-308)
            long r0 = r0 | r10
        L_0x00d2:
            r10 = 0
            r11 = 1
            if (r7 >= 0) goto L_0x00d8
            r7 = 1
            goto L_0x00d9
        L_0x00d8:
            r7 = 0
        L_0x00d9:
            r12 = 1
            long r14 = r0 & r12
            int r16 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r16 != 0) goto L_0x00e3
            r14 = 1
            goto L_0x00e4
        L_0x00e3:
            r14 = 0
        L_0x00e4:
            r15 = 4
            long r15 = r15 * r0
            r17 = 2
            long r17 = r15 + r17
            r19 = 4503599627370496(0x10000000000000, double:2.2250738585072014E-308)
            int r21 = (r0 > r19 ? 1 : (r0 == r19 ? 0 : -1))
            if (r21 != 0) goto L_0x00f7
            if (r9 > r11) goto L_0x00f5
            goto L_0x00f7
        L_0x00f5:
            r0 = 0
            goto L_0x00f8
        L_0x00f7:
            r0 = 1
        L_0x00f8:
            long r19 = r15 - r12
            long r2 = (long) r0
            long r19 = r19 - r2
            int r8 = r8 + -2
            r2 = 2147483647(0x7fffffff, double:1.060997895E-314)
            r21 = 10000000(0x989680, double:4.9406565E-317)
            r9 = 21
            r23 = 3
            r24 = 2
            r25 = 31
            if (r8 < 0) goto L_0x0320
            long r4 = (long) r8
            r26 = 3010299(0x2deefb, double:1.4872853E-317)
            long r4 = r4 * r26
            long r4 = r4 / r21
            int r0 = (int) r4
            int r0 = r0 - r11
            int r0 = java.lang.Math.max(r10, r0)
            if (r0 != 0) goto L_0x0121
            r5 = 1
            goto L_0x012d
        L_0x0121:
            long r4 = (long) r0
            r26 = 23219280(0x1624c50, double:1.14718486E-316)
            long r4 = r4 * r26
            long r4 = r4 + r21
            long r4 = r4 - r12
            long r4 = r4 / r21
            int r5 = (int) r4
        L_0x012d:
            int r5 = r5 + 122
            int r5 = r5 - r11
            int r4 = -r8
            int r4 = r4 + r0
            int r4 = r4 + r5
            int r4 = r4 + -93
            int r4 = r4 - r9
            if (r4 < 0) goto L_0x0307
            int[][] r5 = POW5_INV_SPLIT
            r5 = r5[r0]
            long r26 = r15 >>> r25
            long r28 = r15 & r2
            r8 = r5[r10]
            r30 = r7
            long r6 = (long) r8
            long r6 = r6 * r26
            r8 = r5[r10]
            long r12 = (long) r8
            long r12 = r12 * r28
            r8 = r5[r11]
            long r2 = (long) r8
            long r2 = r2 * r26
            r8 = r5[r11]
            long r10 = (long) r8
            long r10 = r10 * r28
            r8 = r5[r24]
            r34 = r2
            long r1 = (long) r8
            long r1 = r1 * r26
            r3 = r5[r24]
            r36 = r10
            long r9 = (long) r3
            long r9 = r9 * r28
            r3 = r5[r23]
            r11 = r14
            r38 = r15
            long r14 = (long) r3
            long r26 = r26 * r14
            r3 = r5[r23]
            long r14 = (long) r3
            long r28 = r28 * r14
            long r14 = r28 >>> r25
            long r14 = r14 + r9
            long r14 = r14 + r26
            long r8 = r14 >>> r25
            long r8 = r8 + r36
            long r8 = r8 + r1
            long r1 = r8 >>> r25
            long r1 = r1 + r12
            long r1 = r1 + r34
            r3 = 21
            long r1 = r1 >>> r3
            r3 = 10
            long r6 = r6 << r3
            long r1 = r1 + r6
            long r1 = r1 >>> r4
            long r6 = r17 >>> r25
            r8 = 2147483647(0x7fffffff, double:1.060997895E-314)
            long r12 = r17 & r8
            r3 = 0
            r8 = r5[r3]
            long r8 = (long) r8
            long r8 = r8 * r6
            r10 = r5[r3]
            long r14 = (long) r10
            long r14 = r14 * r12
            r3 = 1
            r10 = r5[r3]
            r26 = r1
            long r1 = (long) r10
            long r1 = r1 * r6
            r10 = r5[r3]
            r3 = r11
            long r10 = (long) r10
            long r10 = r10 * r12
            r16 = r3
            r3 = r5[r24]
            r28 = r4
            long r3 = (long) r3
            long r3 = r3 * r6
            r29 = r0
            r0 = r5[r24]
            r34 = r8
            long r8 = (long) r0
            long r8 = r8 * r12
            r0 = r5[r23]
            r36 = r1
            long r0 = (long) r0
            long r6 = r6 * r0
            r0 = r5[r23]
            long r0 = (long) r0
            long r12 = r12 * r0
            long r0 = r12 >>> r25
            long r0 = r0 + r8
            long r0 = r0 + r6
            long r0 = r0 >>> r25
            long r0 = r0 + r10
            long r0 = r0 + r3
            long r0 = r0 >>> r25
            long r0 = r0 + r14
            long r0 = r0 + r36
            r2 = 21
            long r0 = r0 >>> r2
            r2 = 10
            long r3 = r34 << r2
            long r0 = r0 + r3
            long r0 = r0 >>> r28
            long r2 = r19 >>> r25
            r6 = 2147483647(0x7fffffff, double:1.060997895E-314)
            long r6 = r19 & r6
            r4 = 0
            r8 = r5[r4]
            long r8 = (long) r8
            long r8 = r8 * r2
            r10 = r5[r4]
            long r10 = (long) r10
            long r10 = r10 * r6
            r4 = 1
            r12 = r5[r4]
            long r12 = (long) r12
            long r12 = r12 * r2
            r14 = r5[r4]
            long r14 = (long) r14
            long r14 = r14 * r6
            r4 = r5[r24]
            r31 = r0
            long r0 = (long) r4
            long r0 = r0 * r2
            r4 = r5[r24]
            r34 = r8
            long r8 = (long) r4
            long r8 = r8 * r6
            r4 = r5[r23]
            r36 = r12
            long r12 = (long) r4
            long r2 = r2 * r12
            r4 = r5[r23]
            long r4 = (long) r4
            long r6 = r6 * r4
            long r4 = r6 >>> r25
            long r4 = r4 + r8
            long r4 = r4 + r2
            long r2 = r4 >>> r25
            long r2 = r2 + r14
            long r2 = r2 + r0
            long r0 = r2 >>> r25
            long r0 = r0 + r10
            long r0 = r0 + r36
            r2 = 21
            long r0 = r0 >>> r2
            r3 = 10
            long r4 = r34 << r3
            long r0 = r0 + r4
            long r0 = r0 >>> r28
            r3 = r29
            if (r3 > r2) goto L_0x02fc
            r4 = 5
            long r6 = r38 % r4
            r8 = 625(0x271, double:3.09E-321)
            r10 = 0
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 != 0) goto L_0x0278
            if (r2 == 0) goto L_0x023f
            r2 = 0
            goto L_0x026f
        L_0x023f:
            r6 = 25
            long r6 = r38 % r6
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x0249
            r2 = 1
            goto L_0x026f
        L_0x0249:
            r6 = 125(0x7d, double:6.2E-322)
            long r6 = r38 % r6
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x0253
            r2 = 2
            goto L_0x026f
        L_0x0253:
            long r6 = r38 % r8
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x025b
            r2 = 3
            goto L_0x026f
        L_0x025b:
            long r6 = r38 / r8
            r2 = 4
        L_0x025e:
            int r8 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r8 <= 0) goto L_0x026f
            long r8 = r6 % r4
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 == 0) goto L_0x0269
            goto L_0x026f
        L_0x0269:
            long r6 = r6 / r4
            int r2 = r2 + 1
            r10 = 0
            goto L_0x025e
        L_0x026f:
            if (r2 < r3) goto L_0x0273
            r2 = 1
            goto L_0x0274
        L_0x0273:
            r2 = 0
        L_0x0274:
            r4 = r2
            r2 = 0
            goto L_0x02fe
        L_0x0278:
            if (r16 == 0) goto L_0x02b9
            long r6 = r19 % r4
            r10 = 0
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x0284
            r2 = 0
            goto L_0x02b5
        L_0x0284:
            r6 = 25
            long r6 = r19 % r6
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x028e
            r2 = 1
            goto L_0x02b5
        L_0x028e:
            r6 = 125(0x7d, double:6.2E-322)
            long r6 = r19 % r6
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x0298
            r2 = 2
            goto L_0x02b5
        L_0x0298:
            long r6 = r19 % r8
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x02a0
            r2 = 3
            goto L_0x02b5
        L_0x02a0:
            long r19 = r19 / r8
            r2 = 4
        L_0x02a3:
            int r6 = (r19 > r10 ? 1 : (r19 == r10 ? 0 : -1))
            if (r6 <= 0) goto L_0x02b5
            long r6 = r19 % r4
            int r8 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r8 == 0) goto L_0x02ae
            goto L_0x02b5
        L_0x02ae:
            long r19 = r19 / r4
            int r2 = r2 + 1
            r10 = 0
            goto L_0x02a3
        L_0x02b5:
            if (r2 < r3) goto L_0x02fc
            r2 = 1
            goto L_0x02fd
        L_0x02b9:
            long r6 = r17 % r4
            r10 = 0
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x02c3
            r2 = 0
            goto L_0x02f4
        L_0x02c3:
            r6 = 25
            long r6 = r17 % r6
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x02cd
            r2 = 1
            goto L_0x02f4
        L_0x02cd:
            r6 = 125(0x7d, double:6.2E-322)
            long r6 = r17 % r6
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x02d7
            r2 = 2
            goto L_0x02f4
        L_0x02d7:
            long r6 = r17 % r8
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x02df
            r2 = 3
            goto L_0x02f4
        L_0x02df:
            long r17 = r17 / r8
            r2 = 4
        L_0x02e2:
            int r6 = (r17 > r10 ? 1 : (r17 == r10 ? 0 : -1))
            if (r6 <= 0) goto L_0x02f4
            long r6 = r17 % r4
            int r8 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r8 == 0) goto L_0x02ed
            goto L_0x02f4
        L_0x02ed:
            long r17 = r17 / r4
            int r2 = r2 + 1
            r10 = 0
            goto L_0x02e2
        L_0x02f4:
            if (r2 < r3) goto L_0x02fc
            r4 = 1
            long r4 = r31 - r4
            r31 = r4
        L_0x02fc:
            r2 = 0
        L_0x02fd:
            r4 = 0
        L_0x02fe:
            r17 = r26
            r40 = r3
            r3 = r2
            r2 = r40
            goto L_0x048d
        L_0x0307:
            r28 = r4
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = ""
            r1.append(r2)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0320:
            r30 = r7
            r38 = r15
            r16 = r14
            int r1 = -r8
            long r2 = (long) r1
            r4 = 6989700(0x6aa784, double:3.4533706E-317)
            long r2 = r2 * r4
            long r2 = r2 / r21
            int r3 = (int) r2
            r2 = 1
            int r3 = r3 - r2
            r2 = 0
            int r3 = java.lang.Math.max(r2, r3)
            int r1 = r1 - r3
            if (r1 != 0) goto L_0x033c
            r2 = 1
            goto L_0x034a
        L_0x033c:
            long r4 = (long) r1
            r6 = 23219280(0x1624c50, double:1.14718486E-316)
            long r4 = r4 * r6
            long r4 = r4 + r21
            r6 = 1
            long r4 = r4 - r6
            long r4 = r4 / r21
            int r2 = (int) r4
        L_0x034a:
            int r2 = r2 + -121
            int r2 = r3 - r2
            int r2 = r2 + -93
            r4 = 21
            int r2 = r2 - r4
            if (r2 < 0) goto L_0x0723
            int[][] r4 = POW5_SPLIT
            r1 = r4[r1]
            long r4 = r38 >>> r25
            r6 = 2147483647(0x7fffffff, double:1.060997895E-314)
            long r9 = r38 & r6
            r6 = 0
            r7 = r1[r6]
            long r11 = (long) r7
            long r11 = r11 * r4
            r7 = r1[r6]
            long r6 = (long) r7
            long r6 = r6 * r9
            r13 = 1
            r14 = r1[r13]
            long r14 = (long) r14
            long r14 = r14 * r4
            r26 = r0
            r0 = r1[r13]
            r27 = r2
            r13 = r3
            long r2 = (long) r0
            long r2 = r2 * r9
            r0 = r1[r24]
            r28 = r11
            long r11 = (long) r0
            long r11 = r11 * r4
            r0 = r1[r24]
            r34 = r14
            r15 = r13
            long r13 = (long) r0
            long r13 = r13 * r9
            r0 = r1[r23]
            r36 = r6
            long r6 = (long) r0
            long r4 = r4 * r6
            r0 = r1[r23]
            long r6 = (long) r0
            long r9 = r9 * r6
            long r6 = r9 >>> r25
            long r6 = r6 + r13
            long r6 = r6 + r4
            long r4 = r6 >>> r25
            long r4 = r4 + r2
            long r4 = r4 + r11
            long r2 = r4 >>> r25
            long r2 = r2 + r36
            long r2 = r2 + r34
            r0 = 21
            long r2 = r2 >>> r0
            r0 = 10
            long r4 = r28 << r0
            long r2 = r2 + r4
            long r2 = r2 >>> r27
            long r4 = r17 >>> r25
            r6 = 2147483647(0x7fffffff, double:1.060997895E-314)
            long r9 = r17 & r6
            r0 = 0
            r6 = r1[r0]
            long r6 = (long) r6
            long r6 = r6 * r4
            r11 = r1[r0]
            long r11 = (long) r11
            long r11 = r11 * r9
            r0 = 1
            r13 = r1[r0]
            long r13 = (long) r13
            long r13 = r13 * r4
            r17 = r2
            r2 = r1[r0]
            long r2 = (long) r2
            long r2 = r2 * r9
            r0 = r1[r24]
            r28 = r6
            long r6 = (long) r0
            long r6 = r6 * r4
            r0 = r1[r24]
            r34 = r13
            long r13 = (long) r0
            long r13 = r13 * r9
            r0 = r1[r23]
            r36 = r11
            long r11 = (long) r0
            long r4 = r4 * r11
            r0 = r1[r23]
            long r11 = (long) r0
            long r9 = r9 * r11
            long r9 = r9 >>> r25
            long r9 = r9 + r13
            long r9 = r9 + r4
            long r4 = r9 >>> r25
            long r4 = r4 + r2
            long r4 = r4 + r6
            long r2 = r4 >>> r25
            long r2 = r2 + r36
            long r2 = r2 + r34
            r0 = 21
            long r2 = r2 >>> r0
            r0 = 10
            long r4 = r28 << r0
            long r2 = r2 + r4
            long r2 = r2 >>> r27
            long r4 = r19 >>> r25
            r6 = 2147483647(0x7fffffff, double:1.060997895E-314)
            long r6 = r19 & r6
            r0 = 0
            r9 = r1[r0]
            long r9 = (long) r9
            long r9 = r9 * r4
            r11 = r1[r0]
            long r11 = (long) r11
            long r11 = r11 * r6
            r13 = 1
            r14 = r1[r13]
            r19 = r2
            long r2 = (long) r14
            long r2 = r2 * r4
            r14 = r1[r13]
            long r13 = (long) r14
            long r13 = r13 * r6
            r0 = r1[r24]
            r28 = r8
            r31 = r9
            long r8 = (long) r0
            long r8 = r8 * r4
            r0 = r1[r24]
            r34 = r2
            long r2 = (long) r0
            long r2 = r2 * r6
            r0 = r1[r23]
            r36 = r11
            long r10 = (long) r0
            long r4 = r4 * r10
            r0 = r1[r23]
            long r0 = (long) r0
            long r6 = r6 * r0
            long r0 = r6 >>> r25
            long r0 = r0 + r2
            long r0 = r0 + r4
            long r0 = r0 >>> r25
            long r0 = r0 + r13
            long r0 = r0 + r8
            long r0 = r0 >>> r25
            long r0 = r0 + r36
            long r0 = r0 + r34
            r2 = 21
            long r0 = r0 >>> r2
            r2 = 10
            long r3 = r31 << r2
            long r0 = r0 + r3
            long r0 = r0 >>> r27
            int r2 = r15 + r28
            r4 = r15
            r3 = 1
            if (r4 > r3) goto L_0x046f
            if (r16 == 0) goto L_0x0468
            r10 = r26
            if (r10 != r3) goto L_0x0461
            r33 = 1
            goto L_0x0463
        L_0x0461:
            r33 = 0
        L_0x0463:
            r31 = r19
            r3 = r33
            goto L_0x046d
        L_0x0468:
            r5 = 1
            long r31 = r19 - r5
            r3 = 0
        L_0x046d:
            r4 = 1
            goto L_0x048d
        L_0x046f:
            r5 = 1
            r7 = 63
            if (r4 >= r7) goto L_0x0489
            int r4 = r4 - r3
            long r3 = r5 << r4
            long r3 = r3 - r5
            long r3 = r38 & r3
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0483
            r3 = 1
            goto L_0x0484
        L_0x0483:
            r3 = 0
        L_0x0484:
            r4 = r3
            r31 = r19
            r3 = 0
            goto L_0x048d
        L_0x0489:
            r31 = r19
            r3 = 0
            r4 = 0
        L_0x048d:
            r5 = 1000000000000000000(0xde0b6b3a7640000, double:7.832953389245686E-242)
            r7 = 5
            r8 = 100
            r10 = 10
            int r12 = (r31 > r5 ? 1 : (r31 == r5 ? 0 : -1))
            if (r12 < 0) goto L_0x049f
            r23 = 19
            goto L_0x0554
        L_0x049f:
            r5 = 100000000000000000(0x16345785d8a0000, double:5.620395787888205E-302)
            int r12 = (r31 > r5 ? 1 : (r31 == r5 ? 0 : -1))
            if (r12 < 0) goto L_0x04ac
            r23 = 18
            goto L_0x0554
        L_0x04ac:
            r5 = 10000000000000000(0x2386f26fc10000, double:5.431165199810528E-308)
            int r12 = (r31 > r5 ? 1 : (r31 == r5 ? 0 : -1))
            if (r12 < 0) goto L_0x04b9
            r23 = 17
            goto L_0x0554
        L_0x04b9:
            r5 = 1000000000000000(0x38d7ea4c68000, double:4.940656458412465E-309)
            int r12 = (r31 > r5 ? 1 : (r31 == r5 ? 0 : -1))
            if (r12 < 0) goto L_0x04c6
            r23 = 16
            goto L_0x0554
        L_0x04c6:
            r5 = 100000000000000(0x5af3107a4000, double:4.94065645841247E-310)
            int r12 = (r31 > r5 ? 1 : (r31 == r5 ? 0 : -1))
            if (r12 < 0) goto L_0x04d3
            r23 = 15
            goto L_0x0554
        L_0x04d3:
            r5 = 10000000000000(0x9184e72a000, double:4.9406564584125E-311)
            int r12 = (r31 > r5 ? 1 : (r31 == r5 ? 0 : -1))
            if (r12 < 0) goto L_0x04e0
            r23 = 14
            goto L_0x0554
        L_0x04e0:
            r5 = 1000000000000(0xe8d4a51000, double:4.94065645841E-312)
            int r12 = (r31 > r5 ? 1 : (r31 == r5 ? 0 : -1))
            if (r12 < 0) goto L_0x04ed
            r23 = 13
            goto L_0x0554
        L_0x04ed:
            r5 = 100000000000(0x174876e800, double:4.9406564584E-313)
            int r12 = (r31 > r5 ? 1 : (r31 == r5 ? 0 : -1))
            if (r12 < 0) goto L_0x04f9
            r23 = 12
            goto L_0x0554
        L_0x04f9:
            r5 = 10000000000(0x2540be400, double:4.9406564584E-314)
            int r12 = (r31 > r5 ? 1 : (r31 == r5 ? 0 : -1))
            if (r12 < 0) goto L_0x0505
            r23 = 11
            goto L_0x0554
        L_0x0505:
            r5 = 1000000000(0x3b9aca00, double:4.94065646E-315)
            int r12 = (r31 > r5 ? 1 : (r31 == r5 ? 0 : -1))
            if (r12 < 0) goto L_0x050f
            r23 = 10
            goto L_0x0554
        L_0x050f:
            r5 = 100000000(0x5f5e100, double:4.94065646E-316)
            int r12 = (r31 > r5 ? 1 : (r31 == r5 ? 0 : -1))
            if (r12 < 0) goto L_0x0519
            r23 = 9
            goto L_0x0554
        L_0x0519:
            int r5 = (r31 > r21 ? 1 : (r31 == r21 ? 0 : -1))
            if (r5 < 0) goto L_0x0520
            r23 = 8
            goto L_0x0554
        L_0x0520:
            r5 = 1000000(0xf4240, double:4.940656E-318)
            int r12 = (r31 > r5 ? 1 : (r31 == r5 ? 0 : -1))
            if (r12 < 0) goto L_0x052a
            r23 = 7
            goto L_0x0554
        L_0x052a:
            r5 = 100000(0x186a0, double:4.94066E-319)
            int r12 = (r31 > r5 ? 1 : (r31 == r5 ? 0 : -1))
            if (r12 < 0) goto L_0x0534
            r23 = 6
            goto L_0x0554
        L_0x0534:
            r5 = 10000(0x2710, double:4.9407E-320)
            int r12 = (r31 > r5 ? 1 : (r31 == r5 ? 0 : -1))
            if (r12 < 0) goto L_0x053d
            r23 = 5
            goto L_0x0554
        L_0x053d:
            r5 = 1000(0x3e8, double:4.94E-321)
            int r12 = (r31 > r5 ? 1 : (r31 == r5 ? 0 : -1))
            if (r12 < 0) goto L_0x0546
            r23 = 4
            goto L_0x0554
        L_0x0546:
            int r5 = (r31 > r8 ? 1 : (r31 == r8 ? 0 : -1))
            if (r5 < 0) goto L_0x054b
            goto L_0x0554
        L_0x054b:
            int r5 = (r31 > r10 ? 1 : (r31 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x0552
            r23 = 2
            goto L_0x0554
        L_0x0552:
            r23 = 1
        L_0x0554:
            int r2 = r2 + r23
            r5 = 1
            int r2 = r2 - r5
            r5 = -3
            if (r2 < r5) goto L_0x0561
            r5 = 7
            if (r2 < r5) goto L_0x055f
            goto L_0x0561
        L_0x055f:
            r5 = 0
            goto L_0x0562
        L_0x0561:
            r5 = 1
        L_0x0562:
            if (r3 != 0) goto L_0x0592
            if (r4 == 0) goto L_0x0567
            goto L_0x0592
        L_0x0567:
            r3 = 0
            r4 = 0
        L_0x0569:
            long r12 = r31 / r10
            long r14 = r0 / r10
            int r6 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r6 <= 0) goto L_0x0583
            int r6 = (r31 > r8 ? 1 : (r31 == r8 ? 0 : -1))
            if (r6 >= 0) goto L_0x0578
            if (r5 == 0) goto L_0x0578
            goto L_0x0583
        L_0x0578:
            long r0 = r17 % r10
            int r4 = (int) r0
            long r17 = r17 / r10
            int r3 = r3 + 1
            r31 = r12
            r0 = r14
            goto L_0x0569
        L_0x0583:
            int r6 = (r17 > r0 ? 1 : (r17 == r0 ? 0 : -1))
            if (r6 == 0) goto L_0x058c
            if (r4 < r7) goto L_0x058a
            goto L_0x058c
        L_0x058a:
            r0 = 0
            goto L_0x058d
        L_0x058c:
            r0 = 1
        L_0x058d:
            long r0 = (long) r0
            long r17 = r17 + r0
            goto L_0x0605
        L_0x0592:
            r12 = r4
            r4 = 0
            r6 = 0
        L_0x0595:
            long r13 = r31 / r10
            long r19 = r0 / r10
            int r15 = (r13 > r19 ? 1 : (r13 == r19 ? 0 : -1))
            if (r15 <= 0) goto L_0x05c1
            int r15 = (r31 > r8 ? 1 : (r31 == r8 ? 0 : -1))
            if (r15 >= 0) goto L_0x05a4
            if (r5 == 0) goto L_0x05a4
            goto L_0x05c1
        L_0x05a4:
            long r0 = r0 % r10
            r21 = 0
            int r15 = (r0 > r21 ? 1 : (r0 == r21 ? 0 : -1))
            if (r15 != 0) goto L_0x05ad
            r0 = 1
            goto L_0x05ae
        L_0x05ad:
            r0 = 0
        L_0x05ae:
            r3 = r3 & r0
            if (r4 != 0) goto L_0x05b3
            r0 = 1
            goto L_0x05b4
        L_0x05b3:
            r0 = 0
        L_0x05b4:
            r12 = r12 & r0
            long r0 = r17 % r10
            int r4 = (int) r0
            long r17 = r17 / r10
            int r6 = r6 + 1
            r31 = r13
            r0 = r19
            goto L_0x0595
        L_0x05c1:
            if (r3 == 0) goto L_0x05e5
            if (r16 == 0) goto L_0x05e5
        L_0x05c5:
            long r13 = r0 % r10
            r19 = 0
            int r15 = (r13 > r19 ? 1 : (r13 == r19 ? 0 : -1))
            if (r15 != 0) goto L_0x05e5
            int r13 = (r31 > r8 ? 1 : (r31 == r8 ? 0 : -1))
            if (r13 >= 0) goto L_0x05d4
            if (r5 == 0) goto L_0x05d4
            goto L_0x05e5
        L_0x05d4:
            if (r4 != 0) goto L_0x05d8
            r4 = 1
            goto L_0x05d9
        L_0x05d8:
            r4 = 0
        L_0x05d9:
            r12 = r12 & r4
            long r13 = r17 % r10
            int r4 = (int) r13
            long r31 = r31 / r10
            long r17 = r17 / r10
            long r0 = r0 / r10
            int r6 = r6 + 1
            goto L_0x05c5
        L_0x05e5:
            if (r12 == 0) goto L_0x05f4
            if (r4 != r7) goto L_0x05f4
            r8 = 2
            long r8 = r17 % r8
            r12 = 0
            int r14 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r14 != 0) goto L_0x05f4
            r4 = 4
        L_0x05f4:
            int r8 = (r17 > r0 ? 1 : (r17 == r0 ? 0 : -1))
            if (r8 != 0) goto L_0x05fc
            if (r3 == 0) goto L_0x05fe
            if (r16 == 0) goto L_0x05fe
        L_0x05fc:
            if (r4 < r7) goto L_0x0600
        L_0x05fe:
            r0 = 1
            goto L_0x0601
        L_0x0600:
            r0 = 0
        L_0x0601:
            long r0 = (long) r0
            long r17 = r17 + r0
            r3 = r6
        L_0x0605:
            int r0 = r23 - r3
            if (r30 == 0) goto L_0x0610
            int r1 = r44 + 1
            r3 = 45
            r43[r44] = r3
            goto L_0x0612
        L_0x0610:
            r1 = r44
        L_0x0612:
            if (r5 == 0) goto L_0x068a
            r3 = 0
        L_0x0615:
            int r4 = r0 + -1
            if (r3 >= r4) goto L_0x062a
            long r4 = r17 % r10
            int r5 = (int) r4
            long r17 = r17 / r10
            int r4 = r1 + r0
            int r4 = r4 - r3
            r7 = 48
            int r5 = r5 + r7
            char r5 = (char) r5
            r43[r4] = r5
            int r3 = r3 + 1
            goto L_0x0615
        L_0x062a:
            r3 = 48
            long r17 = r17 % r10
            long r3 = r17 + r3
            int r4 = (int) r3
            char r3 = (char) r4
            r43[r1] = r3
            int r3 = r1 + 1
            r4 = 46
            r43[r3] = r4
            int r3 = r0 + 1
            int r1 = r1 + r3
            r3 = 1
            if (r0 != r3) goto L_0x0647
            int r0 = r1 + 1
            r3 = 48
            r43[r1] = r3
            r1 = r0
        L_0x0647:
            int r0 = r1 + 1
            r3 = 69
            r43[r1] = r3
            if (r2 >= 0) goto L_0x0657
            int r1 = r0 + 1
            r3 = 45
            r43[r0] = r3
            int r2 = -r2
            r0 = r1
        L_0x0657:
            r1 = 100
            if (r2 < r1) goto L_0x0670
            int r1 = r0 + 1
            int r3 = r2 / 100
            r4 = 48
            int r3 = r3 + r4
            char r3 = (char) r3
            r43[r0] = r3
            int r2 = r2 % 100
            int r0 = r1 + 1
            int r3 = r2 / 10
            int r3 = r3 + r4
            char r3 = (char) r3
            r43[r1] = r3
            goto L_0x067f
        L_0x0670:
            r1 = 10
            r4 = 48
            if (r2 < r1) goto L_0x067f
            int r1 = r0 + 1
            int r3 = r2 / 10
            int r3 = r3 + r4
            char r3 = (char) r3
            r43[r0] = r3
            r0 = r1
        L_0x067f:
            int r1 = r0 + 1
            r3 = 10
            int r2 = r2 % r3
            int r2 = r2 + r4
            char r2 = (char) r2
            r43[r0] = r2
            goto L_0x004d
        L_0x068a:
            r4 = 48
            if (r2 >= 0) goto L_0x06be
            int r3 = r1 + 1
            r43[r1] = r4
            int r1 = r3 + 1
            r5 = 46
            r43[r3] = r5
            r3 = -1
        L_0x0699:
            if (r3 <= r2) goto L_0x06a5
            int r5 = r1 + 1
            r43[r1] = r4
            int r3 = r3 + -1
            r1 = r5
            r4 = 48
            goto L_0x0699
        L_0x06a5:
            r3 = r1
            r2 = 0
        L_0x06a7:
            if (r2 >= r0) goto L_0x0720
            int r4 = r1 + r0
            int r4 = r4 - r2
            r5 = 1
            int r4 = r4 - r5
            r5 = 48
            long r7 = r17 % r10
            long r7 = r7 + r5
            int r5 = (int) r7
            char r5 = (char) r5
            r43[r4] = r5
            long r17 = r17 / r10
            int r3 = r3 + 1
            int r2 = r2 + 1
            goto L_0x06a7
        L_0x06be:
            int r3 = r2 + 1
            if (r3 < r0) goto L_0x06f2
            r2 = 0
        L_0x06c3:
            if (r2 >= r0) goto L_0x06d8
            int r4 = r1 + r0
            int r4 = r4 - r2
            r5 = 1
            int r4 = r4 - r5
            r5 = 48
            long r7 = r17 % r10
            long r7 = r7 + r5
            int r5 = (int) r7
            char r5 = (char) r5
            r43[r4] = r5
            long r17 = r17 / r10
            int r2 = r2 + 1
            goto L_0x06c3
        L_0x06d8:
            int r1 = r1 + r0
        L_0x06d9:
            if (r0 >= r3) goto L_0x06e5
            int r2 = r1 + 1
            r4 = 48
            r43[r1] = r4
            int r0 = r0 + 1
            r1 = r2
            goto L_0x06d9
        L_0x06e5:
            r4 = 48
            int r0 = r1 + 1
            r2 = 46
            r43[r1] = r2
            int r3 = r0 + 1
            r43[r0] = r4
            goto L_0x0720
        L_0x06f2:
            int r3 = r1 + 1
            r4 = 0
        L_0x06f5:
            if (r4 >= r0) goto L_0x071c
            int r5 = r0 - r4
            r6 = 1
            int r5 = r5 - r6
            if (r5 != r2) goto L_0x0708
            int r5 = r3 + r0
            int r5 = r5 - r4
            int r5 = r5 - r6
            r7 = 46
            r43[r5] = r7
            int r3 = r3 + -1
            goto L_0x070a
        L_0x0708:
            r7 = 46
        L_0x070a:
            int r5 = r3 + r0
            int r5 = r5 - r4
            int r5 = r5 - r6
            r8 = 48
            long r12 = r17 % r10
            long r12 = r12 + r8
            int r8 = (int) r12
            char r8 = (char) r8
            r43[r5] = r8
            long r17 = r17 / r10
            int r4 = r4 + 1
            goto L_0x06f5
        L_0x071c:
            r6 = 1
            int r0 = r0 + r6
            int r3 = r1 + r0
        L_0x0720:
            int r3 = r3 - r44
            return r3
        L_0x0723:
            r27 = r2
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = ""
            r1.append(r2)
            r2 = r27
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.RyuDouble.toString(double, char[], int):int");
    }
}
