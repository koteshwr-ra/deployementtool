package com.google.common.primitives;

import com.ciot.base.constant.NetConstant;
import com.google.common.base.Preconditions;
import java.math.BigInteger;
import java.util.Comparator;

public final class UnsignedLongs {
    public static final long MAX_VALUE = -1;
    private static final int[] maxSafeDigits = new int[37];
    private static final long[] maxValueDivs = new long[37];
    private static final int[] maxValueMods = new int[37];

    private static long flip(long j) {
        return j ^ Long.MIN_VALUE;
    }

    private UnsignedLongs() {
    }

    public static int compare(long j, long j2) {
        return Longs.compare(flip(j), flip(j2));
    }

    public static long min(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long flip = flip(jArr[0]);
        for (int i = 1; i < jArr.length; i++) {
            long flip2 = flip(jArr[i]);
            if (flip2 < flip) {
                flip = flip2;
            }
        }
        return flip(flip);
    }

    public static long max(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long flip = flip(jArr[0]);
        for (int i = 1; i < jArr.length; i++) {
            long flip2 = flip(jArr[i]);
            if (flip2 > flip) {
                flip = flip2;
            }
        }
        return flip(flip);
    }

    public static String join(String str, long... jArr) {
        Preconditions.checkNotNull(str);
        if (jArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(jArr.length * 5);
        sb.append(toString(jArr[0]));
        for (int i = 1; i < jArr.length; i++) {
            sb.append(str);
            sb.append(toString(jArr[i]));
        }
        return sb.toString();
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        public int compare(long[] jArr, long[] jArr2) {
            int min = Math.min(jArr.length, jArr2.length);
            for (int i = 0; i < min; i++) {
                if (jArr[i] != jArr2[i]) {
                    return UnsignedLongs.compare(jArr[i], jArr2[i]);
                }
            }
            return jArr.length - jArr2.length;
        }
    }

    public static long divide(long j, long j2) {
        if (j2 < 0) {
            return compare(j, j2) < 0 ? 0 : 1;
        }
        if (j >= 0) {
            return j / j2;
        }
        int i = 1;
        long j3 = ((j >>> 1) / j2) << 1;
        if (compare(j - (j3 * j2), j2) < 0) {
            i = 0;
        }
        return j3 + ((long) i);
    }

    public static long remainder(long j, long j2) {
        if (j2 < 0) {
            return compare(j, j2) < 0 ? j : j - j2;
        }
        if (j >= 0) {
            return j % j2;
        }
        long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
        if (compare(j3, j2) < 0) {
            j2 = 0;
        }
        return j3 - j2;
    }

    public static long parseUnsignedLong(String str) {
        return parseUnsignedLong(str, 10);
    }

    public static long decode(String str) {
        ParseRequest fromString = ParseRequest.fromString(str);
        try {
            return parseUnsignedLong(fromString.rawValue, fromString.radix);
        } catch (NumberFormatException e) {
            String valueOf = String.valueOf(str);
            NumberFormatException numberFormatException = new NumberFormatException(valueOf.length() != 0 ? "Error parsing value: ".concat(valueOf) : new String("Error parsing value: "));
            numberFormatException.initCause(e);
            throw numberFormatException;
        }
    }

    public static long parseUnsignedLong(String str, int i) {
        Preconditions.checkNotNull(str);
        if (str.length() == 0) {
            throw new NumberFormatException("empty string");
        } else if (i < 2 || i > 36) {
            StringBuilder sb = new StringBuilder(26);
            sb.append("illegal radix: ");
            sb.append(i);
            throw new NumberFormatException(sb.toString());
        } else {
            int i2 = maxSafeDigits[i] - 1;
            long j = 0;
            int i3 = 0;
            while (i3 < str.length()) {
                int digit = Character.digit(str.charAt(i3), i);
                if (digit == -1) {
                    throw new NumberFormatException(str);
                } else if (i3 <= i2 || !overflowInParse(j, digit, i)) {
                    j = (j * ((long) i)) + ((long) digit);
                    i3++;
                } else {
                    String valueOf = String.valueOf(str);
                    throw new NumberFormatException(valueOf.length() != 0 ? "Too large for unsigned long: ".concat(valueOf) : new String("Too large for unsigned long: "));
                }
            }
            return j;
        }
    }

    private static boolean overflowInParse(long j, int i, int i2) {
        if (j < 0) {
            return true;
        }
        long[] jArr = maxValueDivs;
        if (j < jArr[i2]) {
            return false;
        }
        if (j <= jArr[i2] && i <= maxValueMods[i2]) {
            return false;
        }
        return true;
    }

    public static String toString(long j) {
        return toString(j, 10);
    }

    public static String toString(long j, int i) {
        Preconditions.checkArgument(i >= 2 && i <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", Integer.valueOf(i));
        int i2 = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i2 == 0) {
            return NetConstant.PAGE_ID_HOME;
        }
        int i3 = 64;
        char[] cArr = new char[64];
        if (i2 < 0) {
            long j2 = (long) i;
            long divide = divide(j, j2);
            i3 = 63;
            cArr[63] = Character.forDigit((int) (j - (j2 * divide)), i);
            j = divide;
        }
        while (j > 0) {
            i3--;
            long j3 = (long) i;
            cArr[i3] = Character.forDigit((int) (j % j3), i);
            j /= j3;
        }
        return new String(cArr, i3, 64 - i3);
    }

    static {
        BigInteger bigInteger = new BigInteger("10000000000000000", 16);
        for (int i = 2; i <= 36; i++) {
            long j = (long) i;
            maxValueDivs[i] = divide(-1, j);
            maxValueMods[i] = (int) remainder(-1, j);
            maxSafeDigits[i] = bigInteger.toString(i).length() - 1;
        }
    }
}
