package org.apache.mina.filter.codec.statemachine;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public abstract class ConsumeToCrLfDecodingState implements DecodingState {
    private static final byte CR = 13;
    private static final byte LF = 10;
    private IoBuffer buffer;
    private boolean lastIsCR;

    /* access modifiers changed from: protected */
    public abstract DecodingState finishDecode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception;

    public DecodingState decode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        IoBuffer ioBuffer2;
        int position = ioBuffer.position();
        int limit = ioBuffer.limit();
        int i = position;
        while (true) {
            if (i >= limit) {
                i = -1;
                break;
            }
            byte b = ioBuffer.get(i);
            if (b != 13) {
                if (b == 10 && this.lastIsCR) {
                    break;
                }
                this.lastIsCR = false;
            } else {
                this.lastIsCR = true;
            }
            i++;
        }
        if (i >= 0) {
            int i2 = i - 1;
            if (position < i2) {
                ioBuffer.limit(i2);
                IoBuffer ioBuffer3 = this.buffer;
                if (ioBuffer3 == null) {
                    ioBuffer2 = ioBuffer.slice();
                } else {
                    ioBuffer3.put(ioBuffer);
                    ioBuffer2 = this.buffer.flip();
                    this.buffer = null;
                }
                ioBuffer.limit(limit);
            } else {
                IoBuffer ioBuffer4 = this.buffer;
                if (ioBuffer4 == null) {
                    ioBuffer2 = IoBuffer.allocate(0);
                } else {
                    ioBuffer2 = ioBuffer4.flip();
                    this.buffer = null;
                }
            }
            ioBuffer.position(i + 1);
            return finishDecode(ioBuffer2, protocolDecoderOutput);
        }
        ioBuffer.position(position);
        if (this.buffer == null) {
            IoBuffer allocate = IoBuffer.allocate(ioBuffer.remaining());
            this.buffer = allocate;
            allocate.setAutoExpand(true);
        }
        this.buffer.put(ioBuffer);
        if (this.lastIsCR) {
            IoBuffer ioBuffer5 = this.buffer;
            ioBuffer5.position(ioBuffer5.position() - 1);
        }
        return this;
    }

    public DecodingState finishDecode(ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        IoBuffer ioBuffer;
        IoBuffer ioBuffer2 = this.buffer;
        if (ioBuffer2 == null) {
            ioBuffer = IoBuffer.allocate(0);
        } else {
            ioBuffer = ioBuffer2.flip();
            this.buffer = null;
        }
        return finishDecode(ioBuffer, protocolDecoderOutput);
    }
}
