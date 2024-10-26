package com.ciot.tcpclient;

import com.ciot.tcpclient.CharsetDecoder;
import com.ciot.tcpclient.CharsetEncoder;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class CharsetCodecFactory implements ProtocolCodecFactory {
    private CharsetDecoder mCharsetDecoder = new CharsetDecoder();
    private CharsetEncoder mCharsetEncoder = new CharsetEncoder();

    public ProtocolEncoder getEncoder(IoSession ioSession) {
        return this.mCharsetEncoder;
    }

    public ProtocolDecoder getDecoder(IoSession ioSession) {
        return this.mCharsetDecoder;
    }

    public void setOnCharsetDecoderListener(CharsetDecoder.OnCharsetDecoderListener onCharsetDecoderListener) {
        this.mCharsetDecoder.setOnDoDecodeListener(onCharsetDecoderListener);
    }

    public void setOnCharsetEncoderListener(CharsetEncoder.OnCharsetEncoderListener onCharsetEncoderListener) {
        this.mCharsetEncoder.setOnCharsetEncoderListener(onCharsetEncoderListener);
    }
}
