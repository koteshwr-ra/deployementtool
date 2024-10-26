package org.apache.mina.util.byteaccess;

import org.apache.mina.util.byteaccess.ByteArray;

abstract class AbstractByteArray implements ByteArray {
    AbstractByteArray() {
    }

    public final int length() {
        return last() - first();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteArray)) {
            return false;
        }
        ByteArray byteArray = (ByteArray) obj;
        if (first() != byteArray.first() || last() != byteArray.last() || !order().equals(byteArray.order())) {
            return false;
        }
        ByteArray.Cursor cursor = cursor();
        ByteArray.Cursor cursor2 = byteArray.cursor();
        int remaining = cursor.getRemaining();
        while (remaining > 0) {
            if (remaining >= 4) {
                if (cursor.getInt() != cursor2.getInt()) {
                    return false;
                }
            } else if (cursor.get() != cursor2.get()) {
                return false;
            }
        }
        return true;
    }
}
