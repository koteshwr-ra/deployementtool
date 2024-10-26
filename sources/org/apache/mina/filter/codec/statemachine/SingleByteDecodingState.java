package org.apache.mina.filter.codec.statemachine;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.filter.codec.ProtocolDecoderException;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public abstract class SingleByteDecodingState implements DecodingState {
    /* access modifiers changed from: protected */
    public abstract DecodingState finishDecode(byte b, ProtocolDecoderOutput protocolDecoderOutput) throws Exception;

    public DecodingState decode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        return ioBuffer.hasRemaining() ? finishDecode(ioBuffer.get(), protocolDecoderOutput) : this;
    }

    public DecodingState finishDecode(ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        throw new ProtocolDecoderException("Unexpected end of session while waiting for a single byte.");
    }
}
