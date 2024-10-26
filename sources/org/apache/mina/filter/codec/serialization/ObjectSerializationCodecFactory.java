package org.apache.mina.filter.codec.serialization;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class ObjectSerializationCodecFactory implements ProtocolCodecFactory {
    private final ObjectSerializationDecoder decoder;
    private final ObjectSerializationEncoder encoder;

    public ObjectSerializationCodecFactory() {
        this(Thread.currentThread().getContextClassLoader());
    }

    public ObjectSerializationCodecFactory(ClassLoader classLoader) {
        this.encoder = new ObjectSerializationEncoder();
        this.decoder = new ObjectSerializationDecoder(classLoader);
    }

    public ProtocolEncoder getEncoder(IoSession ioSession) {
        return this.encoder;
    }

    public ProtocolDecoder getDecoder(IoSession ioSession) {
        return this.decoder;
    }

    public int getEncoderMaxObjectSize() {
        return this.encoder.getMaxObjectSize();
    }

    public void setEncoderMaxObjectSize(int i) {
        this.encoder.setMaxObjectSize(i);
    }

    public int getDecoderMaxObjectSize() {
        return this.decoder.getMaxObjectSize();
    }

    public void setDecoderMaxObjectSize(int i) {
        this.decoder.setMaxObjectSize(i);
    }
}
