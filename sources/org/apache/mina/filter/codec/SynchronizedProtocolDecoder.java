package org.apache.mina.filter.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class SynchronizedProtocolDecoder implements ProtocolDecoder {
    private final ProtocolDecoder decoder;

    public SynchronizedProtocolDecoder(ProtocolDecoder protocolDecoder) {
        if (protocolDecoder != null) {
            this.decoder = protocolDecoder;
            return;
        }
        throw new IllegalArgumentException("decoder");
    }

    public ProtocolDecoder getDecoder() {
        return this.decoder;
    }

    public void decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        synchronized (this.decoder) {
            this.decoder.decode(ioSession, ioBuffer, protocolDecoderOutput);
        }
    }

    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        synchronized (this.decoder) {
            this.decoder.finishDecode(ioSession, protocolDecoderOutput);
        }
    }

    public void dispose(IoSession ioSession) throws Exception {
        synchronized (this.decoder) {
            this.decoder.dispose(ioSession);
        }
    }
}
