package org.apache.mina.filter.codec;

import java.net.SocketAddress;
import java.util.Queue;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.file.FileRegion;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.future.DefaultWriteFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.DefaultWriteRequest;
import org.apache.mina.core.write.NothingWrittenException;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.core.write.WriteRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtocolCodecFilter extends IoFilterAdapter {
    /* access modifiers changed from: private */
    public static final IoBuffer EMPTY_BUFFER = IoBuffer.wrap(new byte[0]);
    private static final Class<?>[] EMPTY_PARAMS = new Class[0];
    private static final Logger LOGGER = LoggerFactory.getLogger(ProtocolCodecFilter.class);
    private final AttributeKey DECODER;
    private final AttributeKey DECODER_OUT;
    private final AttributeKey ENCODER;
    private final AttributeKey ENCODER_OUT;
    private final ProtocolCodecFactory factory;

    public ProtocolCodecFilter(ProtocolCodecFactory protocolCodecFactory) {
        Class<ProtocolCodecFilter> cls = ProtocolCodecFilter.class;
        this.ENCODER = new AttributeKey(cls, "encoder");
        this.DECODER = new AttributeKey(cls, "decoder");
        this.DECODER_OUT = new AttributeKey(cls, "decoderOut");
        this.ENCODER_OUT = new AttributeKey(cls, "encoderOut");
        if (protocolCodecFactory != null) {
            this.factory = protocolCodecFactory;
            return;
        }
        throw new IllegalArgumentException("factory");
    }

    public ProtocolCodecFilter(final ProtocolEncoder protocolEncoder, final ProtocolDecoder protocolDecoder) {
        Class<ProtocolCodecFilter> cls = ProtocolCodecFilter.class;
        this.ENCODER = new AttributeKey(cls, "encoder");
        this.DECODER = new AttributeKey(cls, "decoder");
        this.DECODER_OUT = new AttributeKey(cls, "decoderOut");
        this.ENCODER_OUT = new AttributeKey(cls, "encoderOut");
        if (protocolEncoder == null) {
            throw new IllegalArgumentException("encoder");
        } else if (protocolDecoder != null) {
            this.factory = new ProtocolCodecFactory() {
                public ProtocolEncoder getEncoder(IoSession ioSession) {
                    return protocolEncoder;
                }

                public ProtocolDecoder getDecoder(IoSession ioSession) {
                    return protocolDecoder;
                }
            };
        } else {
            throw new IllegalArgumentException("decoder");
        }
    }

    public ProtocolCodecFilter(Class<? extends ProtocolEncoder> cls, Class<? extends ProtocolDecoder> cls2) {
        Class<ProtocolCodecFilter> cls3 = ProtocolCodecFilter.class;
        this.ENCODER = new AttributeKey(cls3, "encoder");
        this.DECODER = new AttributeKey(cls3, "decoder");
        this.DECODER_OUT = new AttributeKey(cls3, "decoderOut");
        this.ENCODER_OUT = new AttributeKey(cls3, "encoderOut");
        if (cls == null) {
            throw new IllegalArgumentException("encoderClass");
        } else if (cls2 == null) {
            throw new IllegalArgumentException("decoderClass");
        } else if (!ProtocolEncoder.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("encoderClass: " + cls.getName());
        } else if (ProtocolDecoder.class.isAssignableFrom(cls2)) {
            try {
                cls.getConstructor(EMPTY_PARAMS);
                try {
                    cls2.getConstructor(EMPTY_PARAMS);
                    try {
                        final ProtocolEncoder protocolEncoder = (ProtocolEncoder) cls.newInstance();
                        try {
                            final ProtocolDecoder protocolDecoder = (ProtocolDecoder) cls2.newInstance();
                            this.factory = new ProtocolCodecFactory() {
                                public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
                                    return protocolEncoder;
                                }

                                public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
                                    return protocolDecoder;
                                }
                            };
                        } catch (Exception unused) {
                            throw new IllegalArgumentException("decoderClass cannot be initialized");
                        }
                    } catch (Exception unused2) {
                        throw new IllegalArgumentException("encoderClass cannot be initialized");
                    }
                } catch (NoSuchMethodException unused3) {
                    throw new IllegalArgumentException("decoderClass doesn't have a public default constructor.");
                }
            } catch (NoSuchMethodException unused4) {
                throw new IllegalArgumentException("encoderClass doesn't have a public default constructor.");
            }
        } else {
            throw new IllegalArgumentException("decoderClass: " + cls2.getName());
        }
    }

    public ProtocolEncoder getEncoder(IoSession ioSession) {
        return (ProtocolEncoder) ioSession.getAttribute(this.ENCODER);
    }

    public void onPreAdd(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws Exception {
        if (ioFilterChain.contains((IoFilter) this)) {
            throw new IllegalArgumentException("You can't add the same filter instance more than once.  Create another instance and add it.");
        }
    }

    public void onPostRemove(IoFilterChain ioFilterChain, String str, IoFilter.NextFilter nextFilter) throws Exception {
        disposeCodec(ioFilterChain.getSession());
    }

    public void messageReceived(IoFilter.NextFilter nextFilter, IoSession ioSession, Object obj) throws Exception {
        ProtocolDecoderException protocolDecoderException;
        LOGGER.debug("Processing a MESSAGE_RECEIVED for session {}", (Object) Long.valueOf(ioSession.getId()));
        if (!(obj instanceof IoBuffer)) {
            nextFilter.messageReceived(ioSession, obj);
            return;
        }
        IoBuffer ioBuffer = (IoBuffer) obj;
        ProtocolDecoder decoder = this.factory.getDecoder(ioSession);
        ProtocolDecoderOutput decoderOut = getDecoderOut(ioSession, nextFilter);
        while (ioBuffer.hasRemaining()) {
            int position = ioBuffer.position();
            try {
                synchronized (decoderOut) {
                    decoder.decode(ioSession, ioBuffer, decoderOut);
                }
                decoderOut.flush(nextFilter, ioSession);
            } catch (Throwable th) {
                if (th instanceof ProtocolDecoderException) {
                    protocolDecoderException = th;
                } else {
                    protocolDecoderException = new ProtocolDecoderException((Throwable) th);
                }
                if (protocolDecoderException.getHexdump() == null) {
                    int position2 = ioBuffer.position();
                    ioBuffer.position(position);
                    protocolDecoderException.setHexdump(ioBuffer.getHexDump());
                    ioBuffer.position(position2);
                }
                decoderOut.flush(nextFilter, ioSession);
                nextFilter.exceptionCaught(ioSession, protocolDecoderException);
                if (!(th instanceof RecoverableProtocolDecoderException) || ioBuffer.position() == position) {
                    return;
                }
            }
        }
    }

    public void messageSent(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        if (!(writeRequest instanceof EncodedWriteRequest)) {
            if (writeRequest instanceof MessageWriteRequest) {
                nextFilter.messageSent(ioSession, ((MessageWriteRequest) writeRequest).getParentRequest());
            } else {
                nextFilter.messageSent(ioSession, writeRequest);
            }
        }
    }

    public void filterWrite(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        ProtocolEncoderException protocolEncoderException;
        Object message = writeRequest.getMessage();
        if ((message instanceof IoBuffer) || (message instanceof FileRegion)) {
            nextFilter.filterWrite(ioSession, writeRequest);
            return;
        }
        ProtocolEncoder encoder = this.factory.getEncoder(ioSession);
        ProtocolEncoderOutput encoderOut = getEncoderOut(ioSession, nextFilter, writeRequest);
        if (encoder == null) {
            throw new ProtocolEncoderException("The encoder is null for the session " + ioSession);
        } else if (encoderOut != null) {
            try {
                encoder.encode(ioSession, message, encoderOut);
                Queue<Object> messageQueue = ((AbstractProtocolEncoderOutput) encoderOut).getMessageQueue();
                while (true) {
                    if (messageQueue.isEmpty()) {
                        break;
                    }
                    Object poll = messageQueue.poll();
                    if (poll == null) {
                        break;
                    } else if (!(poll instanceof IoBuffer) || ((IoBuffer) poll).hasRemaining()) {
                        nextFilter.filterWrite(ioSession, new EncodedWriteRequest(poll, (WriteFuture) null, writeRequest.getDestination()));
                    }
                }
                nextFilter.filterWrite(ioSession, new MessageWriteRequest(writeRequest));
            } catch (Throwable th) {
                if (th instanceof ProtocolEncoderException) {
                    protocolEncoderException = th;
                } else {
                    protocolEncoderException = new ProtocolEncoderException((Throwable) th);
                }
                throw protocolEncoderException;
            }
        } else {
            throw new ProtocolEncoderException("The encoderOut is null for the session " + ioSession);
        }
    }

    public void sessionClosed(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        ProtocolDecoderException protocolDecoderException;
        ProtocolDecoder decoder = this.factory.getDecoder(ioSession);
        ProtocolDecoderOutput decoderOut = getDecoderOut(ioSession, nextFilter);
        try {
            decoder.finishDecode(ioSession, decoderOut);
            disposeCodec(ioSession);
            decoderOut.flush(nextFilter, ioSession);
            nextFilter.sessionClosed(ioSession);
        } catch (Throwable th) {
            disposeCodec(ioSession);
            decoderOut.flush(nextFilter, ioSession);
            throw th;
        }
    }

    private static class EncodedWriteRequest extends DefaultWriteRequest {
        public boolean isEncoded() {
            return true;
        }

        public EncodedWriteRequest(Object obj, WriteFuture writeFuture, SocketAddress socketAddress) {
            super(obj, writeFuture, socketAddress);
        }
    }

    private static class MessageWriteRequest extends WriteRequestWrapper {
        public MessageWriteRequest(WriteRequest writeRequest) {
            super(writeRequest);
        }

        public Object getMessage() {
            return ProtocolCodecFilter.EMPTY_BUFFER;
        }

        public String toString() {
            return "MessageWriteRequest, parent : " + super.toString();
        }
    }

    private static class ProtocolDecoderOutputImpl extends AbstractProtocolDecoderOutput {
        public void flush(IoFilter.NextFilter nextFilter, IoSession ioSession) {
            Queue<Object> messageQueue = getMessageQueue();
            while (!messageQueue.isEmpty()) {
                nextFilter.messageReceived(ioSession, messageQueue.poll());
            }
        }
    }

    private static class ProtocolEncoderOutputImpl extends AbstractProtocolEncoderOutput {
        private final IoFilter.NextFilter nextFilter;
        private final IoSession session;
        private final WriteRequest writeRequest;

        public ProtocolEncoderOutputImpl(IoSession ioSession, IoFilter.NextFilter nextFilter2, WriteRequest writeRequest2) {
            this.session = ioSession;
            this.nextFilter = nextFilter2;
            this.writeRequest = writeRequest2;
        }

        public WriteFuture flush() {
            Object poll;
            Queue<Object> messageQueue = getMessageQueue();
            DefaultWriteFuture defaultWriteFuture = null;
            while (!messageQueue.isEmpty() && (poll = messageQueue.poll()) != null) {
                if (!(poll instanceof IoBuffer) || ((IoBuffer) poll).hasRemaining()) {
                    defaultWriteFuture = new DefaultWriteFuture(this.session);
                    this.nextFilter.filterWrite(this.session, new EncodedWriteRequest(poll, defaultWriteFuture, this.writeRequest.getDestination()));
                }
            }
            return defaultWriteFuture == null ? DefaultWriteFuture.newNotWrittenFuture(this.session, new NothingWrittenException(this.writeRequest)) : defaultWriteFuture;
        }
    }

    private void disposeCodec(IoSession ioSession) {
        disposeEncoder(ioSession);
        disposeDecoder(ioSession);
        disposeDecoderOut(ioSession);
    }

    private void disposeEncoder(IoSession ioSession) {
        ProtocolEncoder protocolEncoder = (ProtocolEncoder) ioSession.removeAttribute(this.ENCODER);
        if (protocolEncoder != null) {
            try {
                protocolEncoder.dispose(ioSession);
            } catch (Throwable unused) {
                Logger logger = LOGGER;
                logger.warn("Failed to dispose: " + protocolEncoder.getClass().getName() + " (" + protocolEncoder + ')');
            }
        }
    }

    private void disposeDecoder(IoSession ioSession) {
        ProtocolDecoder protocolDecoder = (ProtocolDecoder) ioSession.removeAttribute(this.DECODER);
        if (protocolDecoder != null) {
            try {
                protocolDecoder.dispose(ioSession);
            } catch (Throwable unused) {
                Logger logger = LOGGER;
                logger.warn("Failed to dispose: " + protocolDecoder.getClass().getName() + " (" + protocolDecoder + ')');
            }
        }
    }

    private ProtocolDecoderOutput getDecoderOut(IoSession ioSession, IoFilter.NextFilter nextFilter) {
        ProtocolDecoderOutput protocolDecoderOutput = (ProtocolDecoderOutput) ioSession.getAttribute(this.DECODER_OUT);
        if (protocolDecoderOutput != null) {
            return protocolDecoderOutput;
        }
        ProtocolDecoderOutputImpl protocolDecoderOutputImpl = new ProtocolDecoderOutputImpl();
        ioSession.setAttribute(this.DECODER_OUT, protocolDecoderOutputImpl);
        return protocolDecoderOutputImpl;
    }

    private ProtocolEncoderOutput getEncoderOut(IoSession ioSession, IoFilter.NextFilter nextFilter, WriteRequest writeRequest) {
        ProtocolEncoderOutput protocolEncoderOutput = (ProtocolEncoderOutput) ioSession.getAttribute(this.ENCODER_OUT);
        if (protocolEncoderOutput != null) {
            return protocolEncoderOutput;
        }
        ProtocolEncoderOutputImpl protocolEncoderOutputImpl = new ProtocolEncoderOutputImpl(ioSession, nextFilter, writeRequest);
        ioSession.setAttribute(this.ENCODER_OUT, protocolEncoderOutputImpl);
        return protocolEncoderOutputImpl;
    }

    private void disposeDecoderOut(IoSession ioSession) {
        ioSession.removeAttribute(this.DECODER_OUT);
    }
}
