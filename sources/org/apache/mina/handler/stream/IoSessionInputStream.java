package org.apache.mina.handler.stream;

import java.io.IOException;
import java.io.InputStream;
import org.apache.mina.core.buffer.IoBuffer;

class IoSessionInputStream extends InputStream {
    private final IoBuffer buf;
    private volatile boolean closed;
    private IOException exception;
    private final Object mutex = new Object();
    private volatile boolean released;

    public IoSessionInputStream() {
        IoBuffer allocate = IoBuffer.allocate(16);
        this.buf = allocate;
        allocate.setAutoExpand(true);
        this.buf.limit(0);
    }

    public int available() {
        int remaining;
        if (this.released) {
            return 0;
        }
        synchronized (this.mutex) {
            remaining = this.buf.remaining();
        }
        return remaining;
    }

    public void close() {
        if (!this.closed) {
            synchronized (this.mutex) {
                this.closed = true;
                releaseBuffer();
                this.mutex.notifyAll();
            }
        }
    }

    public int read() throws IOException {
        synchronized (this.mutex) {
            if (!waitForData()) {
                return -1;
            }
            byte b = this.buf.get() & 255;
            return b;
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        synchronized (this.mutex) {
            if (!waitForData()) {
                return -1;
            }
            if (i2 > this.buf.remaining()) {
                i2 = this.buf.remaining();
            }
            this.buf.get(bArr, i, i2);
            return i2;
        }
    }

    private boolean waitForData() throws IOException {
        if (this.released) {
            return false;
        }
        synchronized (this.mutex) {
            while (!this.released && this.buf.remaining() == 0 && this.exception == null) {
                try {
                    this.mutex.wait();
                } catch (InterruptedException e) {
                    IOException iOException = new IOException("Interrupted while waiting for more data");
                    iOException.initCause(e);
                    throw iOException;
                }
            }
        }
        if (this.exception != null) {
            releaseBuffer();
            throw this.exception;
        } else if (!this.closed || this.buf.remaining() != 0) {
            return true;
        } else {
            releaseBuffer();
            return false;
        }
    }

    private void releaseBuffer() {
        if (!this.released) {
            this.released = true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0036, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(org.apache.mina.core.buffer.IoBuffer r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.mutex
            monitor-enter(r0)
            boolean r1 = r2.closed     // Catch:{ all -> 0x0037 }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            return
        L_0x0009:
            org.apache.mina.core.buffer.IoBuffer r1 = r2.buf     // Catch:{ all -> 0x0037 }
            boolean r1 = r1.hasRemaining()     // Catch:{ all -> 0x0037 }
            if (r1 == 0) goto L_0x0021
            org.apache.mina.core.buffer.IoBuffer r1 = r2.buf     // Catch:{ all -> 0x0037 }
            r1.compact()     // Catch:{ all -> 0x0037 }
            org.apache.mina.core.buffer.IoBuffer r1 = r2.buf     // Catch:{ all -> 0x0037 }
            r1.put((org.apache.mina.core.buffer.IoBuffer) r3)     // Catch:{ all -> 0x0037 }
            org.apache.mina.core.buffer.IoBuffer r3 = r2.buf     // Catch:{ all -> 0x0037 }
            r3.flip()     // Catch:{ all -> 0x0037 }
            goto L_0x0035
        L_0x0021:
            org.apache.mina.core.buffer.IoBuffer r1 = r2.buf     // Catch:{ all -> 0x0037 }
            r1.clear()     // Catch:{ all -> 0x0037 }
            org.apache.mina.core.buffer.IoBuffer r1 = r2.buf     // Catch:{ all -> 0x0037 }
            r1.put((org.apache.mina.core.buffer.IoBuffer) r3)     // Catch:{ all -> 0x0037 }
            org.apache.mina.core.buffer.IoBuffer r3 = r2.buf     // Catch:{ all -> 0x0037 }
            r3.flip()     // Catch:{ all -> 0x0037 }
            java.lang.Object r3 = r2.mutex     // Catch:{ all -> 0x0037 }
            r3.notifyAll()     // Catch:{ all -> 0x0037 }
        L_0x0035:
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            return
        L_0x0037:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0037 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.mina.handler.stream.IoSessionInputStream.write(org.apache.mina.core.buffer.IoBuffer):void");
    }

    public void throwException(IOException iOException) {
        synchronized (this.mutex) {
            if (this.exception == null) {
                this.exception = iOException;
                this.mutex.notifyAll();
            }
        }
    }
}
