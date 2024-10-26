package org.apache.mina.util.byteaccess;

import org.apache.mina.core.buffer.IoBuffer;

public class SimpleByteArrayFactory implements ByteArrayFactory {
    public ByteArray create(int i) {
        if (i >= 0) {
            return new BufferByteArray(IoBuffer.allocate(i)) {
                public void free() {
                }
            };
        }
        throw new IllegalArgumentException("Buffer size must not be negative:" + i);
    }
}
