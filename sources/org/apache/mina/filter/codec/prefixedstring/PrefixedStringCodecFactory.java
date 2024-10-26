package org.apache.mina.filter.codec.prefixedstring;

import java.nio.charset.Charset;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class PrefixedStringCodecFactory implements ProtocolCodecFactory {
    private final PrefixedStringDecoder decoder;
    private final PrefixedStringEncoder encoder;

    public PrefixedStringCodecFactory(Charset charset) {
        this.encoder = new PrefixedStringEncoder(charset);
        this.decoder = new PrefixedStringDecoder(charset);
    }

    public PrefixedStringCodecFactory() {
        this(Charset.defaultCharset());
    }

    public int getEncoderMaxDataLength() {
        return this.encoder.getMaxDataLength();
    }

    public void setEncoderMaxDataLength(int i) {
        this.encoder.setMaxDataLength(i);
    }

    public int getDecoderMaxDataLength() {
        return this.decoder.getMaxDataLength();
    }

    public void setDecoderMaxDataLength(int i) {
        this.decoder.setMaxDataLength(i);
    }

    public void setDecoderPrefixLength(int i) {
        this.decoder.setPrefixLength(i);
    }

    public int getDecoderPrefixLength() {
        return this.decoder.getPrefixLength();
    }

    public void setEncoderPrefixLength(int i) {
        this.encoder.setPrefixLength(i);
    }

    public int getEncoderPrefixLength() {
        return this.encoder.getPrefixLength();
    }

    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return this.encoder;
    }

    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return this.decoder;
    }
}
