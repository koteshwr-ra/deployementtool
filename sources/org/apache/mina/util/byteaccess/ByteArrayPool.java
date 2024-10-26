package org.apache.mina.util.byteaccess;

import java.util.ArrayList;
import java.util.Stack;
import org.apache.mina.core.buffer.IoBuffer;

public class ByteArrayPool implements ByteArrayFactory {
    private final int MAX_BITS = 32;
    private final boolean direct;
    /* access modifiers changed from: private */
    public int freeBufferCount = 0;
    /* access modifiers changed from: private */
    public ArrayList<Stack<DirectBufferByteArray>> freeBuffers;
    /* access modifiers changed from: private */
    public long freeMemory = 0;
    private boolean freed;
    /* access modifiers changed from: private */
    public final int maxFreeBuffers;
    /* access modifiers changed from: private */
    public final int maxFreeMemory;

    /* access modifiers changed from: private */
    public int bits(int i) {
        int i2 = 0;
        while ((1 << i2) < i) {
            i2++;
        }
        return i2;
    }

    static /* synthetic */ int access$208(ByteArrayPool byteArrayPool) {
        int i = byteArrayPool.freeBufferCount;
        byteArrayPool.freeBufferCount = i + 1;
        return i;
    }

    static /* synthetic */ long access$414(ByteArrayPool byteArrayPool, long j) {
        long j2 = byteArrayPool.freeMemory + j;
        byteArrayPool.freeMemory = j2;
        return j2;
    }

    public ByteArrayPool(boolean z, int i, int i2) {
        this.direct = z;
        this.freeBuffers = new ArrayList<>();
        for (int i3 = 0; i3 < 32; i3++) {
            this.freeBuffers.add(new Stack());
        }
        this.maxFreeBuffers = i;
        this.maxFreeMemory = i2;
        this.freed = false;
    }

    public ByteArray create(int i) {
        if (i >= 1) {
            int bits = bits(i);
            synchronized (this) {
                if (!this.freeBuffers.isEmpty()) {
                    DirectBufferByteArray directBufferByteArray = (DirectBufferByteArray) this.freeBuffers.get(bits).pop();
                    directBufferByteArray.setFreed(false);
                    directBufferByteArray.getSingleIoBuffer().limit(i);
                    return directBufferByteArray;
                }
                IoBuffer allocate = IoBuffer.allocate(1 << bits, this.direct);
                allocate.limit(i);
                DirectBufferByteArray directBufferByteArray2 = new DirectBufferByteArray(allocate);
                directBufferByteArray2.setFreed(false);
                return directBufferByteArray2;
            }
        }
        throw new IllegalArgumentException("Buffer size must be at least 1: " + i);
    }

    public void free() {
        synchronized (this) {
            if (!this.freed) {
                this.freed = true;
                this.freeBuffers.clear();
                this.freeBuffers = null;
            } else {
                throw new IllegalStateException("Already freed.");
            }
        }
    }

    private class DirectBufferByteArray extends BufferByteArray {
        public boolean freed;

        public DirectBufferByteArray(IoBuffer ioBuffer) {
            super(ioBuffer);
        }

        public void setFreed(boolean z) {
            this.freed = z;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0064, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void free() {
            /*
                r7 = this;
                monitor-enter(r7)
                boolean r0 = r7.freed     // Catch:{ all -> 0x0070 }
                if (r0 != 0) goto L_0x0068
                r0 = 1
                r7.freed = r0     // Catch:{ all -> 0x0070 }
                monitor-exit(r7)     // Catch:{ all -> 0x0070 }
                org.apache.mina.util.byteaccess.ByteArrayPool r0 = org.apache.mina.util.byteaccess.ByteArrayPool.this
                int r1 = r7.last()
                int r0 = r0.bits(r1)
                org.apache.mina.util.byteaccess.ByteArrayPool r1 = org.apache.mina.util.byteaccess.ByteArrayPool.this
                monitor-enter(r1)
                org.apache.mina.util.byteaccess.ByteArrayPool r2 = org.apache.mina.util.byteaccess.ByteArrayPool.this     // Catch:{ all -> 0x0065 }
                java.util.ArrayList r2 = r2.freeBuffers     // Catch:{ all -> 0x0065 }
                if (r2 == 0) goto L_0x0063
                org.apache.mina.util.byteaccess.ByteArrayPool r2 = org.apache.mina.util.byteaccess.ByteArrayPool.this     // Catch:{ all -> 0x0065 }
                int r2 = r2.freeBufferCount     // Catch:{ all -> 0x0065 }
                org.apache.mina.util.byteaccess.ByteArrayPool r3 = org.apache.mina.util.byteaccess.ByteArrayPool.this     // Catch:{ all -> 0x0065 }
                int r3 = r3.maxFreeBuffers     // Catch:{ all -> 0x0065 }
                if (r2 >= r3) goto L_0x0063
                org.apache.mina.util.byteaccess.ByteArrayPool r2 = org.apache.mina.util.byteaccess.ByteArrayPool.this     // Catch:{ all -> 0x0065 }
                long r2 = r2.freeMemory     // Catch:{ all -> 0x0065 }
                int r4 = r7.last()     // Catch:{ all -> 0x0065 }
                long r4 = (long) r4     // Catch:{ all -> 0x0065 }
                long r2 = r2 + r4
                org.apache.mina.util.byteaccess.ByteArrayPool r4 = org.apache.mina.util.byteaccess.ByteArrayPool.this     // Catch:{ all -> 0x0065 }
                int r4 = r4.maxFreeMemory     // Catch:{ all -> 0x0065 }
                long r4 = (long) r4     // Catch:{ all -> 0x0065 }
                int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r6 > 0) goto L_0x0063
                org.apache.mina.util.byteaccess.ByteArrayPool r2 = org.apache.mina.util.byteaccess.ByteArrayPool.this     // Catch:{ all -> 0x0065 }
                java.util.ArrayList r2 = r2.freeBuffers     // Catch:{ all -> 0x0065 }
                java.lang.Object r0 = r2.get(r0)     // Catch:{ all -> 0x0065 }
                java.util.Stack r0 = (java.util.Stack) r0     // Catch:{ all -> 0x0065 }
                r0.push(r7)     // Catch:{ all -> 0x0065 }
                org.apache.mina.util.byteaccess.ByteArrayPool r0 = org.apache.mina.util.byteaccess.ByteArrayPool.this     // Catch:{ all -> 0x0065 }
                org.apache.mina.util.byteaccess.ByteArrayPool.access$208(r0)     // Catch:{ all -> 0x0065 }
                org.apache.mina.util.byteaccess.ByteArrayPool r0 = org.apache.mina.util.byteaccess.ByteArrayPool.this     // Catch:{ all -> 0x0065 }
                int r2 = r7.last()     // Catch:{ all -> 0x0065 }
                long r2 = (long) r2     // Catch:{ all -> 0x0065 }
                org.apache.mina.util.byteaccess.ByteArrayPool.access$414(r0, r2)     // Catch:{ all -> 0x0065 }
                monitor-exit(r1)     // Catch:{ all -> 0x0065 }
                return
            L_0x0063:
                monitor-exit(r1)     // Catch:{ all -> 0x0065 }
                return
            L_0x0065:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0065 }
                throw r0
            L_0x0068:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0070 }
                java.lang.String r1 = "Already freed."
                r0.<init>(r1)     // Catch:{ all -> 0x0070 }
                throw r0     // Catch:{ all -> 0x0070 }
            L_0x0070:
                r0 = move-exception
                monitor-exit(r7)     // Catch:{ all -> 0x0070 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.util.byteaccess.ByteArrayPool.DirectBufferByteArray.free():void");
        }
    }
}
