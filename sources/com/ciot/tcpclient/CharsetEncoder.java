package com.ciot.tcpclient;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class CharsetEncoder implements ProtocolEncoder {
    private OnCharsetEncoderListener mOnCharsetEncoderListener;

    public interface OnCharsetEncoderListener {
        void dispose(IoSession ioSession) throws Exception;

        void encode(IoSession ioSession, Object obj, ProtocolEncoderOutput protocolEncoderOutput) throws Exception;
    }

    public void setOnCharsetEncoderListener(OnCharsetEncoderListener onCharsetEncoderListener) {
        this.mOnCharsetEncoderListener = onCharsetEncoderListener;
    }

    public void encode(IoSession ioSession, Object obj, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        this.mOnCharsetEncoderListener.encode(ioSession, obj, protocolEncoderOutput);
    }

    public void dispose(IoSession ioSession) throws Exception {
        this.mOnCharsetEncoderListener.dispose(ioSession);
    }
}
