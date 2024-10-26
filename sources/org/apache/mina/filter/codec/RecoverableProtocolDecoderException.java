package org.apache.mina.filter.codec;

public class RecoverableProtocolDecoderException extends ProtocolDecoderException {
    private static final long serialVersionUID = -8172624045024880678L;

    public RecoverableProtocolDecoderException() {
    }

    public RecoverableProtocolDecoderException(String str) {
        super(str);
    }

    public RecoverableProtocolDecoderException(Throwable th) {
        super(th);
    }

    public RecoverableProtocolDecoderException(String str, Throwable th) {
        super(str, th);
    }
}
