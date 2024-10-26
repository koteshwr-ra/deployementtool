package org.apache.mina.filter.codec.statemachine;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.filter.codec.ProtocolDecoderException;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public abstract class ShortIntegerDecodingState implements DecodingState {
    private int counter;
    private int highByte;

    /* access modifiers changed from: protected */
    public abstract DecodingState finishDecode(short s, ProtocolDecoderOutput protocolDecoderOutput) throws Exception;

    public DecodingState decode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        while (ioBuffer.hasRemaining()) {
            int i = this.counter;
            if (i == 0) {
                this.highByte = ioBuffer.getUnsigned();
                this.counter++;
            } else if (i == 1) {
                this.counter = 0;
                return finishDecode((short) (ioBuffer.getUnsigned() | (this.highByte << 8)), protocolDecoderOutput);
            } else {
                throw new InternalError();
            }
        }
        return this;
    }

    public DecodingState finishDecode(ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        throw new ProtocolDecoderException("Unexpected end of session while waiting for a short integer.");
    }
}
