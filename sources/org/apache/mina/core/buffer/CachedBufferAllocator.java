package org.apache.mina.core.buffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CachedBufferAllocator implements IoBufferAllocator {
    private static final int DEFAULT_MAX_CACHED_BUFFER_SIZE = 262144;
    private static final int DEFAULT_MAX_POOL_SIZE = 8;
    /* access modifiers changed from: private */
    public final ThreadLocal<Map<Integer, Queue<CachedBuffer>>> directBuffers;
    /* access modifiers changed from: private */
    public final ThreadLocal<Map<Integer, Queue<CachedBuffer>>> heapBuffers;
    /* access modifiers changed from: private */
    public final int maxCachedBufferSize;
    /* access modifiers changed from: private */
    public final int maxPoolSize;

    public void dispose() {
    }

    public CachedBufferAllocator() {
        this(8, 262144);
    }

    public CachedBufferAllocator(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("maxPoolSize: " + i);
        } else if (i2 >= 0) {
            this.maxPoolSize = i;
            this.maxCachedBufferSize = i2;
            this.heapBuffers = new ThreadLocal<Map<Integer, Queue<CachedBuffer>>>() {
                /* access modifiers changed from: protected */
                public Map<Integer, Queue<CachedBuffer>> initialValue() {
                    return CachedBufferAllocator.this.newPoolMap();
                }
            };
            this.directBuffers = new ThreadLocal<Map<Integer, Queue<CachedBuffer>>>() {
                /* access modifiers changed from: protected */
                public Map<Integer, Queue<CachedBuffer>> initialValue() {
                    return CachedBufferAllocator.this.newPoolMap();
                }
            };
        } else {
            throw new IllegalArgumentException("maxCachedBufferSize: " + i2);
        }
    }

    public int getMaxPoolSize() {
        return this.maxPoolSize;
    }

    public int getMaxCachedBufferSize() {
        return this.maxCachedBufferSize;
    }

    /* access modifiers changed from: package-private */
    public Map<Integer, Queue<CachedBuffer>> newPoolMap() {
        HashMap hashMap = new HashMap();
        int i = this.maxPoolSize;
        for (int i2 = 0; i2 < 31; i2++) {
            hashMap.put(Integer.valueOf(1 << i2), new ConcurrentLinkedQueue());
        }
        hashMap.put(0, new ConcurrentLinkedQueue());
        hashMap.put(Integer.MAX_VALUE, new ConcurrentLinkedQueue());
        return hashMap;
    }

    public IoBuffer allocate(int i, boolean z) {
        IoBuffer ioBuffer;
        Queue queue;
        int normalizeCapacity = IoBuffer.normalizeCapacity(i);
        int i2 = this.maxCachedBufferSize;
        if (i2 == 0 || normalizeCapacity <= i2) {
            if (z) {
                queue = (Queue) this.directBuffers.get().get(Integer.valueOf(normalizeCapacity));
            } else {
                queue = (Queue) this.heapBuffers.get().get(Integer.valueOf(normalizeCapacity));
            }
            IoBuffer ioBuffer2 = (IoBuffer) queue.poll();
            if (ioBuffer2 != null) {
                ioBuffer2.clear();
                ioBuffer2.setAutoExpand(false);
                ioBuffer2.order(ByteOrder.BIG_ENDIAN);
                ioBuffer = ioBuffer2;
            } else if (z) {
                ioBuffer = wrap(ByteBuffer.allocateDirect(normalizeCapacity));
            } else {
                ioBuffer = wrap(ByteBuffer.allocate(normalizeCapacity));
            }
        } else if (z) {
            ioBuffer = wrap(ByteBuffer.allocateDirect(normalizeCapacity));
        } else {
            ioBuffer = wrap(ByteBuffer.allocate(normalizeCapacity));
        }
        ioBuffer.limit(i);
        return ioBuffer;
    }

    public ByteBuffer allocateNioBuffer(int i, boolean z) {
        return allocate(i, z).buf();
    }

    public IoBuffer wrap(ByteBuffer byteBuffer) {
        return new CachedBuffer(byteBuffer);
    }

    private class CachedBuffer extends AbstractIoBuffer {
        private ByteBuffer buf;
        private final Thread ownerThread = Thread.currentThread();

        protected CachedBuffer(ByteBuffer byteBuffer) {
            super(CachedBufferAllocator.this, byteBuffer.capacity());
            this.buf = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        protected CachedBuffer(CachedBuffer cachedBuffer, ByteBuffer byteBuffer) {
            super(cachedBuffer);
            this.buf = byteBuffer;
        }

        public ByteBuffer buf() {
            ByteBuffer byteBuffer = this.buf;
            if (byteBuffer != null) {
                return byteBuffer;
            }
            throw new IllegalStateException("Buffer has been freed already.");
        }

        /* access modifiers changed from: protected */
        public void buf(ByteBuffer byteBuffer) {
            ByteBuffer byteBuffer2 = this.buf;
            this.buf = byteBuffer;
            free(byteBuffer2);
        }

        /* access modifiers changed from: protected */
        public IoBuffer duplicate0() {
            return new CachedBuffer(this, buf().duplicate());
        }

        /* access modifiers changed from: protected */
        public IoBuffer slice0() {
            return new CachedBuffer(this, buf().slice());
        }

        /* access modifiers changed from: protected */
        public IoBuffer asReadOnlyBuffer0() {
            return new CachedBuffer(this, buf().asReadOnlyBuffer());
        }

        public byte[] array() {
            return buf().array();
        }

        public int arrayOffset() {
            return buf().arrayOffset();
        }

        public boolean hasArray() {
            return buf().hasArray();
        }

        public void free() {
            free(this.buf);
            this.buf = null;
        }

        private void free(ByteBuffer byteBuffer) {
            Queue queue;
            if (byteBuffer == null) {
                return;
            }
            if ((CachedBufferAllocator.this.maxCachedBufferSize == 0 || byteBuffer.capacity() <= CachedBufferAllocator.this.maxCachedBufferSize) && !byteBuffer.isReadOnly() && !isDerived() && Thread.currentThread() == this.ownerThread) {
                if (byteBuffer.isDirect()) {
                    queue = (Queue) ((Map) CachedBufferAllocator.this.directBuffers.get()).get(Integer.valueOf(byteBuffer.capacity()));
                } else {
                    queue = (Queue) ((Map) CachedBufferAllocator.this.heapBuffers.get()).get(Integer.valueOf(byteBuffer.capacity()));
                }
                if (queue != null) {
                    if (CachedBufferAllocator.this.maxPoolSize == 0 || queue.size() < CachedBufferAllocator.this.maxPoolSize) {
                        queue.offer(new CachedBuffer(byteBuffer));
                    }
                }
            }
        }
    }
}
