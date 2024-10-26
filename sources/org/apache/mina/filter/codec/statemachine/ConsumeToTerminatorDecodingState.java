package org.apache.mina.filter.codec.statemachine;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public abstract class ConsumeToTerminatorDecodingState implements DecodingState {
    private IoBuffer buffer;
    private final byte terminator;

    /* access modifiers changed from: protected */
    public abstract DecodingState finishDecode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception;

    public ConsumeToTerminatorDecodingState(byte b) {
        this.terminator = b;
    }

    public DecodingState decode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        IoBuffer ioBuffer2;
        int indexOf = ioBuffer.indexOf(this.terminator);
        if (indexOf >= 0) {
            int limit = ioBuffer.limit();
            if (ioBuffer.position() < indexOf) {
                ioBuffer.limit(indexOf);
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
            ioBuffer.position(indexOf + 1);
            return finishDecode(ioBuffer2, protocolDecoderOutput);
        }
        if (this.buffer == null) {
            IoBuffer allocate = IoBuffer.allocate(ioBuffer.remaining());
            this.buffer = allocate;
            allocate.setAutoExpand(true);
        }
        this.buffer.put(ioBuffer);
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
