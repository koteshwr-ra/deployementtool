package org.apache.mina.filter.codec.statemachine;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public abstract class FixedLengthDecodingState implements DecodingState {
    private IoBuffer buffer;
    private final int length;

    /* access modifiers changed from: protected */
    public abstract DecodingState finishDecode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception;

    public FixedLengthDecodingState(int i) {
        this.length = i;
    }

    public DecodingState decode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        if (this.buffer == null) {
            int remaining = ioBuffer.remaining();
            int i = this.length;
            if (remaining >= i) {
                int limit = ioBuffer.limit();
                ioBuffer.limit(ioBuffer.position() + this.length);
                IoBuffer slice = ioBuffer.slice();
                ioBuffer.position(ioBuffer.position() + this.length);
                ioBuffer.limit(limit);
                return finishDecode(slice, protocolDecoderOutput);
            }
            IoBuffer allocate = IoBuffer.allocate(i);
            this.buffer = allocate;
            allocate.put(ioBuffer);
            return this;
        } else if (ioBuffer.remaining() >= this.length - this.buffer.position()) {
            int limit2 = ioBuffer.limit();
            ioBuffer.limit((ioBuffer.position() + this.length) - this.buffer.position());
            this.buffer.put(ioBuffer);
            ioBuffer.limit(limit2);
            IoBuffer ioBuffer2 = this.buffer;
            this.buffer = null;
            return finishDecode(ioBuffer2.flip(), protocolDecoderOutput);
        } else {
            this.buffer.put(ioBuffer);
            return this;
        }
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
