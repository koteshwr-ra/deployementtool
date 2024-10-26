package org.apache.mina.proxy.utils;

import org.apache.mina.core.buffer.IoBuffer;

public class IoBufferDecoder {
    private DecodingContext ctx = new DecodingContext();

    public class DecodingContext {
        private int contentLength = -1;
        private IoBuffer decodedBuffer;
        private IoBuffer delimiter;
        private int matchCount = 0;

        public DecodingContext() {
        }

        public void reset() {
            this.contentLength = -1;
            this.matchCount = 0;
            this.decodedBuffer = null;
        }

        public int getContentLength() {
            return this.contentLength;
        }

        public void setContentLength(int i) {
            this.contentLength = i;
        }

        public int getMatchCount() {
            return this.matchCount;
        }

        public void setMatchCount(int i) {
            this.matchCount = i;
        }

        public IoBuffer getDecodedBuffer() {
            return this.decodedBuffer;
        }

        public void setDecodedBuffer(IoBuffer ioBuffer) {
            this.decodedBuffer = ioBuffer;
        }

        public IoBuffer getDelimiter() {
            return this.delimiter;
        }

        public void setDelimiter(IoBuffer ioBuffer) {
            this.delimiter = ioBuffer;
        }
    }

    public IoBufferDecoder(byte[] bArr) {
        setDelimiter(bArr, true);
    }

    public IoBufferDecoder(int i) {
        setContentLength(i, false);
    }

    public void setContentLength(int i, boolean z) {
        if (i > 0) {
            this.ctx.setContentLength(i);
            if (z) {
                this.ctx.setMatchCount(0);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("contentLength: " + i);
    }

    public void setDelimiter(byte[] bArr, boolean z) {
        if (bArr != null) {
            IoBuffer allocate = IoBuffer.allocate(bArr.length);
            allocate.put(bArr);
            allocate.flip();
            this.ctx.setDelimiter(allocate);
            this.ctx.setContentLength(-1);
            if (z) {
                this.ctx.setMatchCount(0);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Null delimiter not allowed");
    }

    public IoBuffer decodeFully(IoBuffer ioBuffer) {
        int contentLength = this.ctx.getContentLength();
        IoBuffer decodedBuffer = this.ctx.getDecodedBuffer();
        int limit = ioBuffer.limit();
        if (contentLength > -1) {
            if (decodedBuffer == null) {
                decodedBuffer = IoBuffer.allocate(contentLength).setAutoExpand(true);
            }
            if (ioBuffer.remaining() < contentLength) {
                int remaining = ioBuffer.remaining();
                decodedBuffer.put(ioBuffer);
                this.ctx.setDecodedBuffer(decodedBuffer);
                this.ctx.setContentLength(contentLength - remaining);
                return null;
            }
            ioBuffer.limit(ioBuffer.position() + contentLength);
            decodedBuffer.put(ioBuffer);
            decodedBuffer.flip();
            ioBuffer.limit(limit);
            this.ctx.reset();
            return decodedBuffer;
        }
        int position = ioBuffer.position();
        int matchCount = this.ctx.getMatchCount();
        IoBuffer delimiter = this.ctx.getDelimiter();
        while (ioBuffer.hasRemaining()) {
            if (delimiter.get(matchCount) == ioBuffer.get()) {
                matchCount++;
                if (matchCount == delimiter.limit()) {
                    int position2 = ioBuffer.position();
                    ioBuffer.position(position);
                    ioBuffer.limit(position2);
                    if (decodedBuffer == null) {
                        decodedBuffer = IoBuffer.allocate(ioBuffer.remaining()).setAutoExpand(true);
                    }
                    decodedBuffer.put(ioBuffer);
                    decodedBuffer.flip();
                    ioBuffer.limit(limit);
                    this.ctx.reset();
                    return decodedBuffer;
                }
            } else {
                ioBuffer.position(Math.max(0, ioBuffer.position() - matchCount));
                matchCount = 0;
            }
        }
        if (ioBuffer.remaining() > 0) {
            ioBuffer.position(position);
            decodedBuffer.put(ioBuffer);
            ioBuffer.position(ioBuffer.limit());
        }
        this.ctx.setMatchCount(matchCount);
        this.ctx.setDecodedBuffer(decodedBuffer);
        return decodedBuffer;
    }
}
