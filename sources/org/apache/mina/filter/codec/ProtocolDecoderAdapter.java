package org.apache.mina.filter.codec;

import org.apache.mina.core.session.IoSession;

public abstract class ProtocolDecoderAdapter implements ProtocolDecoder {
    public void dispose(IoSession ioSession) throws Exception {
    }

    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
    }
}
