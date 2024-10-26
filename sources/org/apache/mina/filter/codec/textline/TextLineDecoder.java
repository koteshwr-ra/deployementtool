package org.apache.mina.filter.codec.textline;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderException;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.RecoverableProtocolDecoderException;

public class TextLineDecoder implements ProtocolDecoder {
    private final AttributeKey CONTEXT;
    private int bufferLength;
    /* access modifiers changed from: private */
    public final Charset charset;
    private IoBuffer delimBuf;
    private final LineDelimiter delimiter;
    /* access modifiers changed from: private */
    public int maxLineLength;

    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
    }

    public TextLineDecoder() {
        this(LineDelimiter.AUTO);
    }

    public TextLineDecoder(String str) {
        this(new LineDelimiter(str));
    }

    public TextLineDecoder(LineDelimiter lineDelimiter) {
        this(Charset.defaultCharset(), lineDelimiter);
    }

    public TextLineDecoder(Charset charset2) {
        this(charset2, LineDelimiter.AUTO);
    }

    public TextLineDecoder(Charset charset2, String str) {
        this(charset2, new LineDelimiter(str));
    }

    public TextLineDecoder(Charset charset2, LineDelimiter lineDelimiter) {
        this.CONTEXT = new AttributeKey(getClass(), "context");
        this.maxLineLength = 1024;
        this.bufferLength = 128;
        if (charset2 == null) {
            throw new IllegalArgumentException("charset parameter shuld not be null");
        } else if (lineDelimiter != null) {
            this.charset = charset2;
            this.delimiter = lineDelimiter;
            if (this.delimBuf == null) {
                IoBuffer autoExpand = IoBuffer.allocate(2).setAutoExpand(true);
                try {
                    autoExpand.putString(lineDelimiter.getValue(), charset2.newEncoder());
                } catch (CharacterCodingException unused) {
                }
                autoExpand.flip();
                this.delimBuf = autoExpand;
            }
        } else {
            throw new IllegalArgumentException("delimiter parameter should not be null");
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
        throw new IllegalArgumentException("maxLineLength (" + i + ") should be a positive value");
    }

    public void setBufferLength(int i) {
        if (i > 0) {
            this.bufferLength = i;
            return;
        }
        throw new IllegalArgumentException("bufferLength (" + this.maxLineLength + ") should be a positive value");
    }

    public int getBufferLength() {
        return this.bufferLength;
    }

    public void decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        Context context = getContext(ioSession);
        if (LineDelimiter.AUTO.equals(this.delimiter)) {
            decodeAuto(context, ioSession, ioBuffer, protocolDecoderOutput);
        } else {
            decodeNormal(context, ioSession, ioBuffer, protocolDecoderOutput);
        }
    }

    private Context getContext(IoSession ioSession) {
        Context context = (Context) ioSession.getAttribute(this.CONTEXT);
        if (context != null) {
            return context;
        }
        Context context2 = new Context(this.bufferLength);
        ioSession.setAttribute(this.CONTEXT, context2);
        return context2;
    }

    public void dispose(IoSession ioSession) throws Exception {
        if (((Context) ioSession.getAttribute(this.CONTEXT)) != null) {
            ioSession.removeAttribute(this.CONTEXT);
        }
    }

    /* JADX INFO: finally extract failed */
    private void decodeAuto(Context context, IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws CharacterCodingException, ProtocolDecoderException {
        boolean z;
        int matchCount = context.getMatchCount();
        int position = ioBuffer.position();
        int limit = ioBuffer.limit();
        while (ioBuffer.hasRemaining()) {
            byte b = ioBuffer.get();
            if (b != 10) {
                matchCount = b != 13 ? 0 : matchCount + 1;
                z = false;
            } else {
                matchCount++;
                z = true;
            }
            if (z) {
                int position2 = ioBuffer.position();
                ioBuffer.limit(position2);
                ioBuffer.position(position);
                context.append(ioBuffer);
                ioBuffer.limit(limit);
                ioBuffer.position(position2);
                if (context.getOverflowPosition() == 0) {
                    IoBuffer buffer = context.getBuffer();
                    buffer.flip();
                    buffer.limit(buffer.limit() - matchCount);
                    try {
                        writeText(ioSession, buffer.getString(context.getDecoder()), protocolDecoderOutput);
                        buffer.clear();
                        position = position2;
                        matchCount = 0;
                    } catch (Throwable th) {
                        buffer.clear();
                        throw th;
                    }
                } else {
                    int overflowPosition = context.getOverflowPosition();
                    context.reset();
                    throw new RecoverableProtocolDecoderException("Line is too long: " + overflowPosition);
                }
            }
        }
        ioBuffer.position(position);
        context.append(ioBuffer);
        context.setMatchCount(matchCount);
    }

    /* JADX INFO: finally extract failed */
    private void decodeNormal(Context context, IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws CharacterCodingException, ProtocolDecoderException {
        int matchCount = context.getMatchCount();
        int position = ioBuffer.position();
        int limit = ioBuffer.limit();
        while (ioBuffer.hasRemaining()) {
            if (this.delimBuf.get(matchCount) == ioBuffer.get()) {
                matchCount++;
                if (matchCount == this.delimBuf.limit()) {
                    int position2 = ioBuffer.position();
                    ioBuffer.limit(position2);
                    ioBuffer.position(position);
                    context.append(ioBuffer);
                    ioBuffer.limit(limit);
                    ioBuffer.position(position2);
                    if (context.getOverflowPosition() == 0) {
                        IoBuffer buffer = context.getBuffer();
                        buffer.flip();
                        buffer.limit(buffer.limit() - matchCount);
                        try {
                            writeText(ioSession, buffer.getString(context.getDecoder()), protocolDecoderOutput);
                            buffer.clear();
                            position = position2;
                        } catch (Throwable th) {
                            buffer.clear();
                            throw th;
                        }
                    } else {
                        int overflowPosition = context.getOverflowPosition();
                        context.reset();
                        throw new RecoverableProtocolDecoderException("Line is too long: " + overflowPosition);
                    }
                } else {
                    continue;
                }
            } else {
                ioBuffer.position(Math.max(0, ioBuffer.position() - matchCount));
            }
            matchCount = 0;
        }
        ioBuffer.position(position);
        context.append(ioBuffer);
        context.setMatchCount(matchCount);
    }

    /* access modifiers changed from: protected */
    public void writeText(IoSession ioSession, String str, ProtocolDecoderOutput protocolDecoderOutput) {
        protocolDecoderOutput.write(str);
    }

    private class Context {
        private final IoBuffer buf;
        private final CharsetDecoder decoder;
        private int matchCount;
        private int overflowPosition;

        private Context(int i) {
            this.matchCount = 0;
            this.overflowPosition = 0;
            this.decoder = TextLineDecoder.this.charset.newDecoder();
            this.buf = IoBuffer.allocate(i).setAutoExpand(true);
        }

        public CharsetDecoder getDecoder() {
            return this.decoder;
        }

        public IoBuffer getBuffer() {
            return this.buf;
        }

        public int getOverflowPosition() {
            return this.overflowPosition;
        }

        public int getMatchCount() {
            return this.matchCount;
        }

        public void setMatchCount(int i) {
            this.matchCount = i;
        }

        public void reset() {
            this.overflowPosition = 0;
            this.matchCount = 0;
            this.decoder.reset();
        }

        public void append(IoBuffer ioBuffer) {
            if (this.overflowPosition != 0) {
                discard(ioBuffer);
            } else if (this.buf.position() > TextLineDecoder.this.maxLineLength - ioBuffer.remaining()) {
                this.overflowPosition = this.buf.position();
                this.buf.clear();
                discard(ioBuffer);
            } else {
                getBuffer().put(ioBuffer);
            }
        }

        private void discard(IoBuffer ioBuffer) {
            int remaining = Integer.MAX_VALUE - ioBuffer.remaining();
            int i = this.overflowPosition;
            if (remaining < i) {
                this.overflowPosition = Integer.MAX_VALUE;
            } else {
                this.overflowPosition = i + ioBuffer.remaining();
            }
            ioBuffer.position(ioBuffer.limit());
        }
    }
}
