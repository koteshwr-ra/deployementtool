package org.apache.mina.core.buffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class SimpleBufferAllocator implements IoBufferAllocator {
    public void dispose() {
    }

    public IoBuffer allocate(int i, boolean z) {
        return wrap(allocateNioBuffer(i, z));
    }

    public ByteBuffer allocateNioBuffer(int i, boolean z) {
        if (z) {
            return ByteBuffer.allocateDirect(i);
        }
        return ByteBuffer.allocate(i);
    }

    public IoBuffer wrap(ByteBuffer byteBuffer) {
        return new SimpleBuffer(byteBuffer);
    }

    private class SimpleBuffer extends AbstractIoBuffer {
        private ByteBuffer buf;

        public void free() {
        }

        protected SimpleBuffer(ByteBuffer byteBuffer) {
            super(SimpleBufferAllocator.this, byteBuffer.capacity());
            this.buf = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        protected SimpleBuffer(SimpleBuffer simpleBuffer, ByteBuffer byteBuffer) {
            super(simpleBuffer);
            this.buf = byteBuffer;
        }

        public ByteBuffer buf() {
            return this.buf;
        }

        /* access modifiers changed from: protected */
        public void buf(ByteBuffer byteBuffer) {
            this.buf = byteBuffer;
        }

        /* access modifiers changed from: protected */
        public IoBuffer duplicate0() {
            return new SimpleBuffer(this, this.buf.duplicate());
        }

        /* access modifiers changed from: protected */
        public IoBuffer slice0() {
            return new SimpleBuffer(this, this.buf.slice());
        }

        /* access modifiers changed from: protected */
        public IoBuffer asReadOnlyBuffer0() {
            return new SimpleBuffer(this, this.buf.asReadOnlyBuffer());
        }

        public byte[] array() {
            return this.buf.array();
        }

        public int arrayOffset() {
            return this.buf.arrayOffset();
        }

        public boolean hasArray() {
            return this.buf.hasArray();
        }
    }
}
