package com.alibaba.fastjson.util;

import okhttp3.internal.http2.Http2Connection;
import org.apache.commons.lang3.ClassUtils;
import org.apache.mina.proxy.handlers.http.ntlm.NTLMConstants;

public final class RyuFloat {
    private static final int[][] POW5_INV_SPLIT = {new int[]{NTLMConstants.FLAG_UNIDENTIFIED_11, 1}, new int[]{214748364, 1717986919}, new int[]{171798691, 1803886265}, new int[]{137438953, 1013612282}, new int[]{219902325, 1192282922}, new int[]{175921860, 953826338}, new int[]{140737488, 763061070}, new int[]{225179981, 791400982}, new int[]{180143985, 203624056}, new int[]{144115188, 162899245}, new int[]{230584300, 1978625710}, new int[]{184467440, 1582900568}, new int[]{147573952, 1266320455}, new int[]{236118324, 308125809}, new int[]{188894659, 675997377}, new int[]{151115727, 970294631}, new int[]{241785163, 1981968139}, new int[]{193428131, 297084323}, new int[]{154742504, 1955654377}, new int[]{247588007, 1840556814}, new int[]{198070406, 613451992}, new int[]{158456325, 61264864}, new int[]{253530120, 98023782}, new int[]{202824096, 78419026}, new int[]{162259276, 1780722139}, new int[]{259614842, 1990161963}, new int[]{207691874, 733136111}, new int[]{166153499, 1016005619}, new int[]{265845599, 337118801}, new int[]{212676479, 699191770}, new int[]{170141183, 988850146}};
    private static final int[][] POW5_SPLIT = {new int[]{NTLMConstants.FLAG_NEGOTIATE_128_BIT_ENCRYPTION, 0}, new int[]{671088640, 0}, new int[]{838860800, 0}, new int[]{1048576000, 0}, new int[]{655360000, 0}, new int[]{819200000, 0}, new int[]{1024000000, 0}, new int[]{640000000, 0}, new int[]{800000000, 0}, new int[]{Http2Connection.DEGRADED_PONG_TIMEOUT_NS, 0}, new int[]{625000000, 0}, new int[]{781250000, 0}, new int[]{976562500, 0}, new int[]{610351562, 1073741824}, new int[]{762939453, NTLMConstants.FLAG_UNIDENTIFIED_11}, new int[]{953674316, 872415232}, new int[]{596046447, 1619001344}, new int[]{745058059, 1486880768}, new int[]{931322574, 1321730048}, new int[]{582076609, 289210368}, new int[]{727595761, 898383872}, new int[]{909494701, 1659850752}, new int[]{568434188, 1305842176}, new int[]{710542735, 1632302720}, new int[]{888178419, 1503507488}, new int[]{555111512, 671256724}, new int[]{693889390, 839070905}, new int[]{867361737, 2122580455}, new int[]{542101086, 521306416}, new int[]{677626357, 1725374844}, new int[]{847032947, 546105819}, new int[]{1058791184, 145761362}, new int[]{661744490, 91100851}, new int[]{827180612, 1187617888}, new int[]{1033975765, 1484522360}, new int[]{646234853, 1196261931}, new int[]{807793566, 2032198326}, new int[]{1009741958, 1466506084}, new int[]{631088724, 379695390}, new int[]{788860905, 474619238}, new int[]{986076131, 1130144959}, new int[]{616297582, 437905143}, new int[]{770371977, 1621123253}, new int[]{962964972, 415791331}, new int[]{601853107, 1333611405}, new int[]{752316384, 1130143345}, new int[]{940395480, 1412679181}};

    public static String toString(float f) {
        char[] cArr = new char[15];
        return new String(cArr, 0, toString(f, cArr, 0));
    }

    public static int toString(float f, char[] cArr, int i) {
        int i2;
        boolean z;
        boolean z2;
        boolean z3;
        int i3;
        int i4;
        int i5;
        boolean z4;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        if (Float.isNaN(f)) {
            int i18 = i + 1;
            cArr[i] = 'N';
            int i19 = i18 + 1;
            cArr[i18] = 'a';
            i17 = i19 + 1;
            cArr[i19] = 'N';
        } else if (f == Float.POSITIVE_INFINITY) {
            int i20 = i + 1;
            cArr[i] = 'I';
            int i21 = i20 + 1;
            cArr[i20] = 'n';
            int i22 = i21 + 1;
            cArr[i21] = 'f';
            int i23 = i22 + 1;
            cArr[i22] = 'i';
            int i24 = i23 + 1;
            cArr[i23] = 'n';
            int i25 = i24 + 1;
            cArr[i24] = 'i';
            int i26 = i25 + 1;
            cArr[i25] = 't';
            cArr[i26] = 'y';
            return (i26 + 1) - i;
        } else if (f == Float.NEGATIVE_INFINITY) {
            int i27 = i + 1;
            cArr[i] = '-';
            int i28 = i27 + 1;
            cArr[i27] = 'I';
            int i29 = i28 + 1;
            cArr[i28] = 'n';
            int i30 = i29 + 1;
            cArr[i29] = 'f';
            int i31 = i30 + 1;
            cArr[i30] = 'i';
            int i32 = i31 + 1;
            cArr[i31] = 'n';
            int i33 = i32 + 1;
            cArr[i32] = 'i';
            int i34 = i33 + 1;
            cArr[i33] = 't';
            i17 = i34 + 1;
            cArr[i34] = 'y';
        } else {
            int floatToIntBits = Float.floatToIntBits(f);
            if (floatToIntBits == 0) {
                int i35 = i + 1;
                cArr[i] = '0';
                int i36 = i35 + 1;
                cArr[i35] = ClassUtils.PACKAGE_SEPARATOR_CHAR;
                i17 = i36 + 1;
                cArr[i36] = '0';
            } else if (floatToIntBits == Integer.MIN_VALUE) {
                int i37 = i + 1;
                cArr[i] = '-';
                int i38 = i37 + 1;
                cArr[i37] = '0';
                int i39 = i38 + 1;
                cArr[i38] = ClassUtils.PACKAGE_SEPARATOR_CHAR;
                cArr[i39] = '0';
                return (i39 + 1) - i;
            } else {
                int i40 = (floatToIntBits >> 23) & 255;
                int i41 = 8388607 & floatToIntBits;
                if (i40 == 0) {
                    i2 = -149;
                } else {
                    i2 = (i40 - 127) - 23;
                    i41 |= 8388608;
                }
                boolean z5 = floatToIntBits < 0;
                boolean z6 = (i41 & 1) == 0;
                int i42 = i41 * 4;
                int i43 = i42 + 2;
                int i44 = i42 - ((((long) i41) != 8388608 || i40 <= 1) ? 2 : 1);
                int i45 = i2 - 2;
                if (i45 >= 0) {
                    i4 = (int) ((((long) i45) * 3010299) / 10000000);
                    if (i4 == 0) {
                        i15 = 1;
                    } else {
                        i15 = (int) ((((((long) i4) * 23219280) + 10000000) - 1) / 10000000);
                    }
                    int i46 = (-i45) + i4;
                    int[][] iArr = POW5_INV_SPLIT;
                    long j = (long) iArr[i4][0];
                    z = z6;
                    long j2 = (long) iArr[i4][1];
                    long j3 = (long) i42;
                    int i47 = (((i15 + 59) - 1) + i46) - 31;
                    long j4 = j3;
                    i5 = (int) (((j3 * j) + ((j3 * j2) >> 31)) >> i47);
                    long j5 = (long) i43;
                    i3 = (int) (((j5 * j) + ((j5 * j2) >> 31)) >> i47);
                    long j6 = (long) i44;
                    i6 = (int) (((j * j6) + ((j6 * j2) >> 31)) >> i47);
                    if (i4 == 0 || (i3 - 1) / 10 > i6 / 10) {
                        i7 = 0;
                    } else {
                        int i48 = i4 - 1;
                        if (i48 == 0) {
                            i16 = 1;
                        } else {
                            i16 = (int) ((((((long) i48) * 23219280) + 10000000) - 1) / 10000000);
                        }
                        int i49 = (i46 - 1) + ((i16 + 59) - 1);
                        int[][] iArr2 = POW5_INV_SPLIT;
                        i7 = (int) ((((((long) iArr2[i48][0]) * j4) + ((((long) iArr2[i48][1]) * j4) >> 31)) >> (i49 - 31)) % 10);
                    }
                    int i50 = 0;
                    while (i43 > 0 && i43 % 5 == 0) {
                        i43 /= 5;
                        i50++;
                    }
                    int i51 = 0;
                    while (i42 > 0 && i42 % 5 == 0) {
                        i42 /= 5;
                        i51++;
                    }
                    int i52 = 0;
                    while (i44 > 0 && i44 % 5 == 0) {
                        i44 /= 5;
                        i52++;
                    }
                    z4 = i50 >= i4;
                    z3 = i51 >= i4;
                    z2 = i52 >= i4;
                } else {
                    z = z6;
                    int i53 = -i45;
                    int i54 = (int) ((((long) i53) * 6989700) / 10000000);
                    int i55 = i53 - i54;
                    if (i55 == 0) {
                        i11 = 1;
                    } else {
                        i11 = (int) ((((((long) i55) * 23219280) + 10000000) - 1) / 10000000);
                    }
                    int i56 = i54 - (i11 - 61);
                    int[][] iArr3 = POW5_SPLIT;
                    long j7 = (long) iArr3[i55][0];
                    long j8 = (long) iArr3[i55][1];
                    int i57 = i56 - 31;
                    long j9 = (long) i42;
                    long j10 = j7;
                    int i58 = i42;
                    long j11 = (long) i43;
                    int i59 = (int) (((j11 * j10) + ((j11 * j8) >> 31)) >> i57);
                    int i60 = (int) (((j9 * j7) + ((j9 * j8) >> 31)) >> i57);
                    long j12 = (long) i44;
                    int i61 = (int) (((j12 * j10) + ((j12 * j8) >> 31)) >> i57);
                    if (i54 == 0 || (i59 - 1) / 10 > i61 / 10) {
                        i12 = i59;
                        i13 = 0;
                    } else {
                        int i62 = i55 + 1;
                        int i63 = i54 - 1;
                        if (i62 == 0) {
                            i14 = 1;
                        } else {
                            i14 = (int) ((((((long) i62) * 23219280) + 10000000) - 1) / 10000000);
                        }
                        int i64 = i63 - (i14 - 61);
                        int[][] iArr4 = POW5_SPLIT;
                        i12 = i59;
                        i13 = (int) ((((((long) iArr4[i62][0]) * j9) + ((j9 * ((long) iArr4[i62][1])) >> 31)) >> (i64 - 31)) % 10);
                    }
                    i4 = i54 + i45;
                    boolean z7 = 1 >= i54;
                    boolean z8 = i54 < 23 && (i58 & ((1 << (i54 + -1)) - 1)) == 0;
                    boolean z9 = (i44 % 2 == 1 ? 0 : 1) >= i54;
                    z4 = z7;
                    i5 = i60;
                    i3 = i12;
                    boolean z10 = z8;
                    z2 = z9;
                    i6 = i61;
                    z3 = z10;
                }
                int i65 = Http2Connection.DEGRADED_PONG_TIMEOUT_NS;
                int i66 = 10;
                while (i66 > 0 && i3 < i65) {
                    i65 /= 10;
                    i66--;
                }
                int i67 = (i4 + i66) - 1;
                boolean z11 = i67 < -3 || i67 >= 7;
                if (z4 && !z) {
                    i3--;
                }
                int i68 = 0;
                while (true) {
                    int i69 = i3 / 10;
                    int i70 = i6 / 10;
                    if (i69 > i70 && (i3 >= 100 || !z11)) {
                        z2 &= i6 % 10 == 0;
                        i7 = i5 % 10;
                        i5 /= 10;
                        i68++;
                        i3 = i69;
                        i6 = i70;
                    } else if (z2 && z) {
                        while (i6 % 10 == 0 && (i3 >= 100 || !z11)) {
                            i3 /= 10;
                            i7 = i5 % 10;
                            i5 /= 10;
                            i6 /= 10;
                            i68++;
                        }
                    }
                }
                while (i6 % 10 == 0) {
                    i3 /= 10;
                    i7 = i5 % 10;
                    i5 /= 10;
                    i6 /= 10;
                    i68++;
                }
                if (z3 && i7 == 5 && i5 % 2 == 0) {
                    i7 = 4;
                }
                int i71 = i5 + (((i5 != i6 || (z2 && z)) && i7 < 5) ? 0 : 1);
                int i72 = i66 - i68;
                if (z5) {
                    i8 = i + 1;
                    cArr[i] = '-';
                } else {
                    i8 = i;
                }
                if (z11) {
                    for (int i73 = 0; i73 < i72 - 1; i73++) {
                        int i74 = i71 % 10;
                        i71 /= 10;
                        cArr[(i8 + i72) - i73] = (char) (i74 + 48);
                    }
                    cArr[i8] = (char) ((i71 % 10) + 48);
                    cArr[i8 + 1] = ClassUtils.PACKAGE_SEPARATOR_CHAR;
                    int i75 = i8 + i72 + 1;
                    if (i72 == 1) {
                        cArr[i75] = '0';
                        i75++;
                    }
                    int i76 = i75 + 1;
                    cArr[i75] = 'E';
                    if (i67 < 0) {
                        cArr[i76] = '-';
                        i67 = -i67;
                        i76++;
                    }
                    if (i67 >= 10) {
                        i10 = 48;
                        cArr[i76] = (char) ((i67 / 10) + 48);
                        i76++;
                    } else {
                        i10 = 48;
                    }
                    i9 = i76 + 1;
                    cArr[i76] = (char) ((i67 % 10) + i10);
                } else {
                    int i77 = 48;
                    if (i67 < 0) {
                        int i78 = i8 + 1;
                        cArr[i8] = '0';
                        int i79 = i78 + 1;
                        cArr[i78] = ClassUtils.PACKAGE_SEPARATOR_CHAR;
                        int i80 = -1;
                        while (i80 > i67) {
                            cArr[i79] = '0';
                            i80--;
                            i79++;
                        }
                        int i81 = i79;
                        int i82 = 0;
                        while (i82 < i72) {
                            cArr[((i79 + i72) - i82) - 1] = (char) ((i71 % 10) + i77);
                            i71 /= 10;
                            i81++;
                            i82++;
                            i77 = 48;
                        }
                        i9 = i81;
                    } else {
                        int i83 = i67 + 1;
                        if (i83 >= i72) {
                            for (int i84 = 0; i84 < i72; i84++) {
                                cArr[((i8 + i72) - i84) - 1] = (char) ((i71 % 10) + 48);
                                i71 /= 10;
                            }
                            int i85 = i8 + i72;
                            while (i72 < i83) {
                                cArr[i85] = '0';
                                i72++;
                                i85++;
                            }
                            int i86 = i85 + 1;
                            cArr[i85] = ClassUtils.PACKAGE_SEPARATOR_CHAR;
                            i9 = i86 + 1;
                            cArr[i86] = '0';
                        } else {
                            int i87 = i8 + 1;
                            for (int i88 = 0; i88 < i72; i88++) {
                                if ((i72 - i88) - 1 == i67) {
                                    cArr[((i87 + i72) - i88) - 1] = ClassUtils.PACKAGE_SEPARATOR_CHAR;
                                    i87--;
                                }
                                cArr[((i87 + i72) - i88) - 1] = (char) ((i71 % 10) + 48);
                                i71 /= 10;
                            }
                            i9 = i8 + i72 + 1;
                        }
                    }
                }
                return i9 - i;
            }
        }
        return i17 - i;
    }
}
