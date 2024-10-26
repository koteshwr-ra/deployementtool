package org.apache.mina.filter.codec.statemachine;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public abstract class SkippingState implements DecodingState {
    private int skippedBytes;

    /* access modifiers changed from: protected */
    public abstract boolean canSkip(byte b);

    /* access modifiers changed from: protected */
    public abstract DecodingState finishDecode(int i) throws Exception;

    public DecodingState decode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        int limit = ioBuffer.limit();
        for (int position = ioBuffer.position(); position < limit; position++) {
            if (!canSkip(ioBuffer.get(position))) {
                ioBuffer.position(position);
                int i = this.skippedBytes;
                this.skippedBytes = 0;
                return finishDecode(i);
            }
            this.skippedBytes++;
        }
        ioBuffer.position(limit);
        return this;
    }

    public DecodingState finishDecode(ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        return finishDecode(this.skippedBytes);
    }
}
