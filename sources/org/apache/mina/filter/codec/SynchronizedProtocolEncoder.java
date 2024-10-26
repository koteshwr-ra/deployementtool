package org.apache.mina.filter.codec;

import org.apache.mina.core.session.IoSession;

public class SynchronizedProtocolEncoder implements ProtocolEncoder {
    private final ProtocolEncoder encoder;

    public SynchronizedProtocolEncoder(ProtocolEncoder protocolEncoder) {
        if (protocolEncoder != null) {
            this.encoder = protocolEncoder;
            return;
        }
        throw new IllegalArgumentException("encoder");
    }

    public ProtocolEncoder getEncoder() {
        return this.encoder;
    }

    public void encode(IoSession ioSession, Object obj, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        synchronized (this.encoder) {
            this.encoder.encode(ioSession, obj, protocolEncoderOutput);
        }
    }

    public void dispose(IoSession ioSession) throws Exception {
        synchronized (this.encoder) {
            this.encoder.dispose(ioSession);
        }
    }
}
