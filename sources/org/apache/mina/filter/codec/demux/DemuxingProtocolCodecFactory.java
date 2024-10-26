package org.apache.mina.filter.codec.demux;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class DemuxingProtocolCodecFactory implements ProtocolCodecFactory {
    private final DemuxingProtocolDecoder decoder = new DemuxingProtocolDecoder();
    private final DemuxingProtocolEncoder encoder = new DemuxingProtocolEncoder();

    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return this.encoder;
    }

    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return this.decoder;
    }

    public void addMessageEncoder(Class<?> cls, Class<? extends MessageEncoder> cls2) {
        this.encoder.addMessageEncoder(cls, cls2);
    }

    public <T> void addMessageEncoder(Class<T> cls, MessageEncoder<? super T> messageEncoder) {
        this.encoder.addMessageEncoder(cls, messageEncoder);
    }

    public <T> void addMessageEncoder(Class<T> cls, MessageEncoderFactory<? super T> messageEncoderFactory) {
        this.encoder.addMessageEncoder(cls, messageEncoderFactory);
    }

    public void addMessageEncoder(Iterable<Class<?>> iterable, Class<? extends MessageEncoder> cls) {
        for (Class<?> addMessageEncoder : iterable) {
            addMessageEncoder(addMessageEncoder, cls);
        }
    }

    public <T> void addMessageEncoder(Iterable<Class<? extends T>> iterable, MessageEncoder<? super T> messageEncoder) {
        for (Class<? extends T> addMessageEncoder : iterable) {
            addMessageEncoder(addMessageEncoder, messageEncoder);
        }
    }

    public <T> void addMessageEncoder(Iterable<Class<? extends T>> iterable, MessageEncoderFactory<? super T> messageEncoderFactory) {
        for (Class<? extends T> addMessageEncoder : iterable) {
            addMessageEncoder(addMessageEncoder, messageEncoderFactory);
        }
    }

    public void addMessageDecoder(Class<? extends MessageDecoder> cls) {
        this.decoder.addMessageDecoder(cls);
    }

    public void addMessageDecoder(MessageDecoder messageDecoder) {
        this.decoder.addMessageDecoder(messageDecoder);
    }

    public void addMessageDecoder(MessageDecoderFactory messageDecoderFactory) {
        this.decoder.addMessageDecoder(messageDecoderFactory);
    }
}
