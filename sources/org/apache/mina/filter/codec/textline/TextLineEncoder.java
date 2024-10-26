package org.apache.mina.filter.codec.textline;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class TextLineEncoder extends ProtocolEncoderAdapter {
    private final AttributeKey ENCODER;
    private final Charset charset;
    private final LineDelimiter delimiter;
    private int maxLineLength;

    public void dispose() throws Exception {
    }

    public TextLineEncoder() {
        this(Charset.defaultCharset(), LineDelimiter.UNIX);
    }

    public TextLineEncoder(String str) {
        this(new LineDelimiter(str));
    }

    public TextLineEncoder(LineDelimiter lineDelimiter) {
        this(Charset.defaultCharset(), lineDelimiter);
    }

    public TextLineEncoder(Charset charset2) {
        this(charset2, LineDelimiter.UNIX);
    }

    public TextLineEncoder(Charset charset2, String str) {
        this(charset2, new LineDelimiter(str));
    }

    public TextLineEncoder(Charset charset2, LineDelimiter lineDelimiter) {
        this.ENCODER = new AttributeKey(getClass(), "encoder");
        this.maxLineLength = Integer.MAX_VALUE;
        if (charset2 == null) {
            throw new IllegalArgumentException("charset");
        } else if (lineDelimiter == null) {
            throw new IllegalArgumentException(RequestParameters.DELIMITER);
        } else if (!LineDelimiter.AUTO.equals(lineDelimiter)) {
            this.charset = charset2;
            this.delimiter = lineDelimiter;
        } else {
            throw new IllegalArgumentException("AUTO delimiter is not allowed for encoder.");
        }
    }

    public int getMaxLineLength() {
        return this.maxLineLength;
    }

    public void setMaxLineLength(int i) {
        if (i > 0) {
            this.maxLineLength = i;
            return;
        }
        throw new IllegalArgumentException("maxLineLength: " + i);
    }

    public void encode(IoSession ioSession, Object obj, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        String str;
        CharsetEncoder charsetEncoder = (CharsetEncoder) ioSession.getAttribute(this.ENCODER);
        if (charsetEncoder == null) {
            charsetEncoder = this.charset.newEncoder();
            ioSession.setAttribute(this.ENCODER, charsetEncoder);
        }
        if (obj == null) {
            str = "";
        } else {
            str = obj.toString();
        }
        IoBuffer autoExpand = IoBuffer.allocate(str.length()).setAutoExpand(true);
        autoExpand.putString(str, charsetEncoder);
        if (autoExpand.position() <= this.maxLineLength) {
            autoExpand.putString(this.delimiter.getValue(), charsetEncoder);
            autoExpand.flip();
            protocolEncoderOutput.write(autoExpand);
            return;
        }
        throw new IllegalArgumentException("Line length: " + autoExpand.position());
    }
}
