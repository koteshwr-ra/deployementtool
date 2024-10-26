package org.apache.mina.proxy.utils;

import com.google.common.base.Ascii;
import java.security.DigestException;
import java.security.MessageDigestSpi;

public class MD4 extends MessageDigestSpi {
    private static final int A = 1732584193;
    private static final int B = -271733879;
    public static final int BYTE_BLOCK_LENGTH = 64;
    public static final int BYTE_DIGEST_LENGTH = 16;
    private static final int C = -1732584194;
    private static final int D = 271733878;
    private int a = A;
    private int b = B;
    private final byte[] buffer = new byte[64];
    private int c = C;
    private int d = D;
    private long msgLength;

    /* access modifiers changed from: protected */
    public int engineGetDigestLength() {
        return 16;
    }

    /* access modifiers changed from: protected */
    public void engineUpdate(byte b2) {
        long j = this.msgLength;
        int i = (int) (j % 64);
        byte[] bArr = this.buffer;
        bArr[i] = b2;
        this.msgLength = j + 1;
        if (i == 63) {
            process(bArr, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void engineUpdate(byte[] bArr, int i, int i2) {
        long j = this.msgLength;
        int i3 = (int) (j % 64);
        int i4 = 64 - i3;
        this.msgLength = j + ((long) i2);
        int i5 = 0;
        if (i2 >= i4) {
            System.arraycopy(bArr, i, this.buffer, i3, i4);
            process(this.buffer, 0);
            while (true) {
                int i6 = i4 + 64;
                if (i6 - 1 >= i2) {
                    break;
                }
                process(bArr, i4 + i);
                i4 = i6;
            }
            i5 = i4;
            i3 = 0;
        }
        if (i5 < i2) {
            System.arraycopy(bArr, i + i5, this.buffer, i3, i2 - i5);
        }
    }

    /* access modifiers changed from: protected */
    public byte[] engineDigest() {
        byte[] pad = pad();
        engineUpdate(pad, 0, pad.length);
        int i = this.a;
        int i2 = this.b;
        int i3 = this.c;
        int i4 = this.d;
        byte[] bArr = {(byte) i, (byte) (i >>> 8), (byte) (i >>> 16), (byte) (i >>> 24), (byte) i2, (byte) (i2 >>> 8), (byte) (i2 >>> 16), (byte) (i2 >>> 24), (byte) i3, (byte) (i3 >>> 8), (byte) (i3 >>> 16), (byte) (i3 >>> 24), (byte) i4, (byte) (i4 >>> 8), (byte) (i4 >>> 16), (byte) (i4 >>> 24)};
        engineReset();
        return bArr;
    }

    /* access modifiers changed from: protected */
    public int engineDigest(byte[] bArr, int i, int i2) throws DigestException {
        if (i < 0 || i + i2 >= bArr.length) {
            throw new DigestException("Wrong offset or not enough space to store the digest");
        }
        int min = Math.min(i2, 16);
        System.arraycopy(engineDigest(), 0, bArr, i, min);
        return min;
    }

    /* access modifiers changed from: protected */
    public void engineReset() {
        this.a = A;
        this.b = B;
        this.c = C;
        this.d = D;
        this.msgLength = 0;
    }

    private byte[] pad() {
        int i = (int) (this.msgLength % 64);
        int i2 = i < 56 ? 64 - i : 128 - i;
        byte[] bArr = new byte[i2];
        int i3 = 0;
        bArr[0] = Byte.MIN_VALUE;
        long j = this.msgLength << 3;
        int i4 = i2 - 8;
        while (i3 < 8) {
            bArr[i4] = (byte) ((int) (j >>> (i3 << 3)));
            i3++;
            i4++;
        }
        return bArr;
    }

    private void process(byte[] bArr, int i) {
        int i2 = this.a;
        int i3 = this.b;
        int i4 = this.c;
        int i5 = this.d;
        int[] iArr = new int[16];
        int i6 = i;
        int i7 = 0;
        while (i7 < 16) {
            int i8 = i6 + 1;
            int i9 = i8 + 1;
            int i10 = i9 + 1;
            iArr[i7] = (bArr[i6] & 255) | ((bArr[i8] & 255) << 8) | ((bArr[i9] & 255) << Ascii.DLE) | ((bArr[i10] & 255) << Ascii.CAN);
            i7++;
            i6 = i10 + 1;
        }
        int i11 = this.a;
        int i12 = this.b;
        int i13 = this.c;
        int i14 = ~i12;
        int i15 = this.d;
        int i16 = i11 + ((i12 & i13) | (i14 & i15)) + iArr[0];
        this.a = i16;
        int i17 = (i16 >>> 29) | (i16 << 3);
        this.a = i17;
        int i18 = i15 + ((i17 & i12) | ((~i17) & i13)) + iArr[1];
        this.d = i18;
        int i19 = (i18 << 7) | (i18 >>> 25);
        this.d = i19;
        int i20 = i13 + ((i19 & i17) | ((~i19) & i12)) + iArr[2];
        this.c = i20;
        int i21 = (i20 >>> 21) | (i20 << 11);
        this.c = i21;
        int i22 = i12 + ((i21 & i19) | ((~i21) & i17)) + iArr[3];
        this.b = i22;
        int i23 = (i22 >>> 13) | (i22 << 19);
        this.b = i23;
        int i24 = i17 + ((i23 & i21) | ((~i23) & i19)) + iArr[4];
        this.a = i24;
        int i25 = (i24 >>> 29) | (i24 << 3);
        this.a = i25;
        int i26 = i19 + ((i25 & i23) | ((~i25) & i21)) + iArr[5];
        this.d = i26;
        int i27 = (i26 >>> 25) | (i26 << 7);
        this.d = i27;
        int i28 = i21 + ((i27 & i25) | ((~i27) & i23)) + iArr[6];
        this.c = i28;
        int i29 = (i28 >>> 21) | (i28 << 11);
        this.c = i29;
        int i30 = i23 + ((i29 & i27) | ((~i29) & i25)) + iArr[7];
        this.b = i30;
        int i31 = (i30 >>> 13) | (i30 << 19);
        this.b = i31;
        int i32 = i25 + ((i31 & i29) | ((~i31) & i27)) + iArr[8];
        this.a = i32;
        int i33 = (i32 >>> 29) | (i32 << 3);
        this.a = i33;
        int i34 = i27 + ((i33 & i31) | ((~i33) & i29)) + iArr[9];
        this.d = i34;
        int i35 = (i34 >>> 25) | (i34 << 7);
        this.d = i35;
        int i36 = i29 + ((i35 & i33) | ((~i35) & i31)) + iArr[10];
        this.c = i36;
        int i37 = (i36 >>> 21) | (i36 << 11);
        this.c = i37;
        int i38 = i31 + ((i37 & i35) | ((~i37) & i33)) + iArr[11];
        this.b = i38;
        int i39 = (i38 >>> 13) | (i38 << 19);
        this.b = i39;
        int i40 = i33 + ((i39 & i37) | ((~i39) & i35)) + iArr[12];
        this.a = i40;
        int i41 = (i40 >>> 29) | (i40 << 3);
        this.a = i41;
        int i42 = i35 + ((i41 & i39) | ((~i41) & i37)) + iArr[13];
        this.d = i42;
        int i43 = (i42 >>> 25) | (i42 << 7);
        this.d = i43;
        int i44 = i37 + ((i43 & i41) | ((~i43) & i39)) + iArr[14];
        this.c = i44;
        int i45 = (i44 >>> 21) | (i44 << 11);
        this.c = i45;
        int i46 = i39 + ((i45 & i43) | ((~i45) & i41)) + iArr[15];
        this.b = i46;
        int i47 = (i46 >>> 13) | (i46 << 19);
        this.b = i47;
        int i48 = i41 + (((i45 | i43) & i47) | (i45 & i43)) + iArr[0] + 1518500249;
        this.a = i48;
        int i49 = (i48 >>> 29) | (i48 << 3);
        this.a = i49;
        int i50 = i43 + (((i47 | i45) & i49) | (i47 & i45)) + iArr[4] + 1518500249;
        this.d = i50;
        int i51 = (i50 >>> 27) | (i50 << 5);
        this.d = i51;
        int i52 = i45 + (((i49 | i47) & i51) | (i49 & i47)) + iArr[8] + 1518500249;
        this.c = i52;
        int i53 = (i52 >>> 23) | (i52 << 9);
        this.c = i53;
        int i54 = i47 + (((i51 | i49) & i53) | (i51 & i49)) + iArr[12] + 1518500249;
        this.b = i54;
        int i55 = (i54 >>> 19) | (i54 << 13);
        this.b = i55;
        int i56 = i49 + (((i53 | i51) & i55) | (i53 & i51)) + iArr[1] + 1518500249;
        this.a = i56;
        int i57 = (i56 >>> 29) | (i56 << 3);
        this.a = i57;
        int i58 = i51 + (((i55 | i53) & i57) | (i55 & i53)) + iArr[5] + 1518500249;
        this.d = i58;
        int i59 = (i58 >>> 27) | (i58 << 5);
        this.d = i59;
        int i60 = i53 + (((i57 | i55) & i59) | (i57 & i55)) + iArr[9] + 1518500249;
        this.c = i60;
        int i61 = (i60 >>> 23) | (i60 << 9);
        this.c = i61;
        int i62 = i55 + (((i59 | i57) & i61) | (i59 & i57)) + iArr[13] + 1518500249;
        this.b = i62;
        int i63 = (i62 >>> 19) | (i62 << 13);
        this.b = i63;
        int i64 = i57 + (((i61 | i59) & i63) | (i61 & i59)) + iArr[2] + 1518500249;
        this.a = i64;
        int i65 = (i64 >>> 29) | (i64 << 3);
        this.a = i65;
        int i66 = i59 + (((i63 | i61) & i65) | (i63 & i61)) + iArr[6] + 1518500249;
        this.d = i66;
        int i67 = (i66 >>> 27) | (i66 << 5);
        this.d = i67;
        int i68 = i61 + (((i65 | i63) & i67) | (i65 & i63)) + iArr[10] + 1518500249;
        this.c = i68;
        int i69 = (i68 >>> 23) | (i68 << 9);
        this.c = i69;
        int i70 = i63 + (((i67 | i65) & i69) | (i67 & i65)) + iArr[14] + 1518500249;
        this.b = i70;
        int i71 = (i70 >>> 19) | (i70 << 13);
        this.b = i71;
        int i72 = i65 + (((i69 | i67) & i71) | (i69 & i67)) + iArr[3] + 1518500249;
        this.a = i72;
        int i73 = (i72 >>> 29) | (i72 << 3);
        this.a = i73;
        int i74 = i67 + (((i71 | i69) & i73) | (i71 & i69)) + iArr[7] + 1518500249;
        this.d = i74;
        int i75 = (i74 >>> 27) | (i74 << 5);
        this.d = i75;
        int i76 = i69 + (((i73 | i71) & i75) | (i73 & i71)) + iArr[11] + 1518500249;
        this.c = i76;
        int i77 = (i76 >>> 23) | (i76 << 9);
        this.c = i77;
        int i78 = i71 + (((i75 | i73) & i77) | (i75 & i73)) + iArr[15] + 1518500249;
        this.b = i78;
        int i79 = (i78 >>> 19) | (i78 << 13);
        this.b = i79;
        int i80 = i73 + ((i79 ^ i77) ^ i75) + iArr[0] + 1859775393;
        this.a = i80;
        int i81 = (i80 >>> 29) | (i80 << 3);
        this.a = i81;
        int i82 = i75 + ((i81 ^ i79) ^ i77) + iArr[8] + 1859775393;
        this.d = i82;
        int i83 = (i82 << 9) | (i82 >>> 23);
        this.d = i83;
        int i84 = i77 + ((i83 ^ i81) ^ i79) + iArr[4] + 1859775393;
        this.c = i84;
        int i85 = (i84 >>> 21) | (i84 << 11);
        this.c = i85;
        int i86 = i79 + ((i85 ^ i83) ^ i81) + iArr[12] + 1859775393;
        this.b = i86;
        int i87 = (i86 >>> 17) | (i86 << 15);
        this.b = i87;
        int i88 = i81 + ((i87 ^ i85) ^ i83) + iArr[2] + 1859775393;
        this.a = i88;
        int i89 = (i88 >>> 29) | (i88 << 3);
        this.a = i89;
        int i90 = i83 + ((i89 ^ i87) ^ i85) + iArr[10] + 1859775393;
        this.d = i90;
        int i91 = (i90 >>> 23) | (i90 << 9);
        this.d = i91;
        int i92 = i85 + ((i91 ^ i89) ^ i87) + iArr[6] + 1859775393;
        this.c = i92;
        int i93 = (i92 >>> 21) | (i92 << 11);
        this.c = i93;
        int i94 = i87 + ((i93 ^ i91) ^ i89) + iArr[14] + 1859775393;
        this.b = i94;
        int i95 = (i94 >>> 17) | (i94 << 15);
        this.b = i95;
        int i96 = i89 + ((i95 ^ i93) ^ i91) + iArr[1] + 1859775393;
        this.a = i96;
        int i97 = (i96 >>> 29) | (i96 << 3);
        this.a = i97;
        int i98 = i91 + ((i97 ^ i95) ^ i93) + iArr[9] + 1859775393;
        this.d = i98;
        int i99 = (i98 >>> 23) | (i98 << 9);
        this.d = i99;
        int i100 = i93 + ((i99 ^ i97) ^ i95) + iArr[5] + 1859775393;
        this.c = i100;
        int i101 = (i100 >>> 21) | (i100 << 11);
        this.c = i101;
        int i102 = i95 + ((i101 ^ i99) ^ i97) + iArr[13] + 1859775393;
        this.b = i102;
        int i103 = (i102 >>> 17) | (i102 << 15);
        this.b = i103;
        int i104 = i97 + ((i103 ^ i101) ^ i99) + iArr[3] + 1859775393;
        this.a = i104;
        int i105 = (i104 >>> 29) | (i104 << 3);
        this.a = i105;
        int i106 = i99 + ((i105 ^ i103) ^ i101) + iArr[11] + 1859775393;
        this.d = i106;
        int i107 = (i106 >>> 23) | (i106 << 9);
        this.d = i107;
        int i108 = i101 + ((i107 ^ i105) ^ i103) + iArr[7] + 1859775393;
        this.c = i108;
        int i109 = (i108 >>> 21) | (i108 << 11);
        this.c = i109;
        int i110 = i103 + ((i109 ^ i107) ^ i105) + iArr[15] + 1859775393;
        this.b = i110;
        int i111 = (i110 << 15) | (i110 >>> 17);
        this.b = i111;
        this.a = i105 + i2;
        this.b = i111 + i3;
        this.c = i109 + i4;
        this.d = i107 + i5;
    }
}
