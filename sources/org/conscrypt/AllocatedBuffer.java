package org.conscrypt;

import java.nio.ByteBuffer;

public abstract class AllocatedBuffer {
    public abstract ByteBuffer nioBuffer();

    public abstract AllocatedBuffer release();

    @Deprecated
    public AllocatedBuffer retain() {
        return this;
    }

    public static AllocatedBuffer wrap(final ByteBuffer byteBuffer) {
        Preconditions.checkNotNull(byteBuffer, "buffer");
        return new AllocatedBuffer() {
            public AllocatedBuffer release() {
                return this;
            }

            public ByteBuffer nioBuffer() {
                return byteBuffer;
            }
        };
    }
}
