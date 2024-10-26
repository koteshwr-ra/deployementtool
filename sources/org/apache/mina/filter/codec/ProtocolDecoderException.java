package org.apache.mina.filter.codec;

import org.apache.commons.lang3.StringUtils;

public class ProtocolDecoderException extends ProtocolCodecException {
    private static final long serialVersionUID = 3545799879533408565L;
    private String hexdump;

    public ProtocolDecoderException() {
    }

    public ProtocolDecoderException(String str) {
        super(str);
    }

    public ProtocolDecoderException(Throwable th) {
        super(th);
    }

    public ProtocolDecoderException(String str, Throwable th) {
        super(str, th);
    }

    public String getMessage() {
        String message = super.getMessage();
        String str = "";
        if (message == null) {
            message = str;
        }
        if (this.hexdump == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(message);
        if (message.length() > 0) {
            str = StringUtils.SPACE;
        }
        sb.append(str);
        sb.append("(Hexdump: ");
        sb.append(this.hexdump);
        sb.append(')');
        return sb.toString();
    }

    public String getHexdump() {
        return this.hexdump;
    }

    public void setHexdump(String str) {
        if (this.hexdump == null) {
            this.hexdump = str;
            return;
        }
        throw new IllegalStateException("Hexdump cannot be set more than once.");
    }
}
