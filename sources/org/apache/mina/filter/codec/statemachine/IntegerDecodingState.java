package org.apache.mina.filter.codec.statemachine;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.filter.codec.ProtocolDecoderException;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public abstract class IntegerDecodingState implements DecodingState {
    private int counter;
    private int firstByte;
    private int secondByte;
    private int thirdByte;

    /* access modifiers changed from: protected */
    public abstract DecodingState finishDecode(int i, ProtocolDecoderOutput protocolDecoderOutput) throws Exception;

    public DecodingState decode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        while (ioBuffer.hasRemaining()) {
            int i = this.counter;
            if (i == 0) {
                this.firstByte = ioBuffer.getUnsigned();
            } else if (i == 1) {
                this.secondByte = ioBuffer.getUnsigned();
            } else if (i == 2) {
                this.thirdByte = ioBuffer.getUnsigned();
            } else if (i == 3) {
                this.counter = 0;
                return finishDecode(ioBuffer.getUnsigned() | (this.firstByte << 24) | (this.secondByte << 16) | (this.thirdByte << 8), protocolDecoderOutput);
            } else {
                throw new InternalError();
            }
            this.counter++;
        }
        return this;
    }

    public DecodingState finishDecode(ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        throw new ProtocolDecoderException("Unexpected end of session while waiting for an integer.");
    }
}
