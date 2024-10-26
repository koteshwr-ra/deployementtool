package org.apache.mina.filter.codec.statemachine;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class DecodingStateProtocolDecoder implements ProtocolDecoder {
    private IoSession session;
    private final DecodingState state;
    private final Queue<IoBuffer> undecodedBuffers = new ConcurrentLinkedQueue();

    public void dispose(IoSession ioSession) throws Exception {
    }

    public DecodingStateProtocolDecoder(DecodingState decodingState) {
        if (decodingState != null) {
            this.state = decodingState;
            return;
        }
        throw new IllegalArgumentException("state");
    }

    public void decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        IoSession ioSession2 = this.session;
        if (ioSession2 == null) {
            this.session = ioSession;
        } else if (ioSession2 != ioSession) {
            throw new IllegalStateException(getClass().getSimpleName() + " is a stateful decoder.  " + "You have to create one per session.");
        }
        this.undecodedBuffers.offer(ioBuffer);
        while (true) {
            IoBuffer peek = this.undecodedBuffers.peek();
            if (peek != null) {
                int remaining = peek.remaining();
                this.state.decode(peek, protocolDecoderOutput);
                int remaining2 = peek.remaining();
                if (remaining2 == 0) {
                    this.undecodedBuffers.poll();
                } else if (remaining == remaining2) {
                    throw new IllegalStateException(DecodingState.class.getSimpleName() + " must " + "consume at least one byte per decode().");
                }
            } else {
                return;
            }
        }
    }

    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        this.state.finishDecode(protocolDecoderOutput);
    }
}
