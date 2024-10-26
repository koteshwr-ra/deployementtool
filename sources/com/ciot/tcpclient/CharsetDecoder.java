package com.ciot.tcpclient;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class CharsetDecoder extends CumulativeProtocolDecoder {
    private OnCharsetDecoderListener mOnCharsetDecoderListener;

    public interface OnCharsetDecoderListener {
        boolean doDecode(IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput);
    }

    public void setOnDoDecodeListener(OnCharsetDecoderListener onCharsetDecoderListener) {
        this.mOnCharsetDecoderListener = onCharsetDecoderListener;
    }

    /* access modifiers changed from: protected */
    public boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        return this.mOnCharsetDecoderListener.doDecode(ioBuffer, protocolDecoderOutput);
    }
}
