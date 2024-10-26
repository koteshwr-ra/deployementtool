package com.google.common.collect;

import javax.annotation.Nullable;

final class Hashing {
    private static final int C1 = -862048943;
    private static final int C2 = 461845907;
    private static int MAX_TABLE_SIZE = 1073741824;

    private Hashing() {
    }

    static int smear(int i) {
        return Integer.rotateLeft(i * C1, 15) * C2;
    }

    static int smearedHash(@Nullable Object obj) {
        return smear(obj == null ? 0 : obj.hashCode());
    }

    static int closedTableSize(int i, double d) {
        int max = Math.max(i, 2);
        int highestOneBit = Integer.highestOneBit(max);
        if (max <= ((int) (d * ((double) highestOneBit)))) {
            return highestOneBit;
        }
        int i2 = highestOneBit << 1;
        return i2 > 0 ? i2 : MAX_TABLE_SIZE;
    }

    static boolean needsResizing(int i, int i2, double d) {
        return ((double) i) > d * ((double) i2) && i2 < MAX_TABLE_SIZE;
    }
}
