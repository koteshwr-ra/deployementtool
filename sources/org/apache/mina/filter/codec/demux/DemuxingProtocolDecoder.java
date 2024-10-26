package org.apache.mina.filter.codec.demux;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderException;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class DemuxingProtocolDecoder extends CumulativeProtocolDecoder {
    private static final Class<?>[] EMPTY_PARAMS = new Class[0];
    private final AttributeKey STATE = new AttributeKey(getClass(), "state");
    /* access modifiers changed from: private */
    public MessageDecoderFactory[] decoderFactories = new MessageDecoderFactory[0];

    public void addMessageDecoder(Class<? extends MessageDecoder> cls) {
        if (cls != null) {
            try {
                cls.getConstructor(EMPTY_PARAMS);
                boolean z = false;
                if (MessageDecoder.class.isAssignableFrom(cls)) {
                    addMessageDecoder((MessageDecoderFactory) new DefaultConstructorMessageDecoderFactory(cls));
                    z = true;
                }
                if (!z) {
                    throw new IllegalArgumentException("Unregisterable type: " + cls);
                }
            } catch (NoSuchMethodException unused) {
                throw new IllegalArgumentException("The specified class doesn't have a public default constructor.");
            }
        } else {
            throw new IllegalArgumentException("decoderClass");
        }
    }

    public void addMessageDecoder(MessageDecoder messageDecoder) {
        addMessageDecoder((MessageDecoderFactory) new SingletonMessageDecoderFactory(messageDecoder));
    }

    public void addMessageDecoder(MessageDecoderFactory messageDecoderFactory) {
        if (messageDecoderFactory != null) {
            MessageDecoderFactory[] messageDecoderFactoryArr = this.decoderFactories;
            MessageDecoderFactory[] messageDecoderFactoryArr2 = new MessageDecoderFactory[(messageDecoderFactoryArr.length + 1)];
            System.arraycopy(messageDecoderFactoryArr, 0, messageDecoderFactoryArr2, 0, messageDecoderFactoryArr.length);
            messageDecoderFactoryArr2[messageDecoderFactoryArr.length] = messageDecoderFactory;
            this.decoderFactories = messageDecoderFactoryArr2;
            return;
        }
        throw new IllegalArgumentException("factory");
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        State state = getState(ioSession);
        if (state.currentDecoder == null) {
            MessageDecoder[] access$300 = state.decoders;
            int length = access$300.length - 1;
            int i = 0;
            while (true) {
                if (length < 0) {
                    break;
                }
                MessageDecoder messageDecoder = access$300[length];
                int limit = ioBuffer.limit();
                int position = ioBuffer.position();
                try {
                    MessageDecoderResult decodable = messageDecoder.decodable(ioSession, ioBuffer);
                    ioBuffer.position(position);
                    ioBuffer.limit(limit);
                    if (decodable == MessageDecoder.OK) {
                        MessageDecoder unused = state.currentDecoder = messageDecoder;
                        break;
                    }
                    if (decodable == MessageDecoder.NOT_OK) {
                        i++;
                    } else if (decodable != MessageDecoder.NEED_DATA) {
                        throw new IllegalStateException("Unexpected decode result (see your decodable()): " + decodable);
                    }
                    length--;
                } catch (Throwable th) {
                    ioBuffer.position(position);
                    ioBuffer.limit(limit);
                    throw th;
                }
            }
            if (i == access$300.length) {
                String hexDump = ioBuffer.getHexDump();
                ioBuffer.position(ioBuffer.limit());
                ProtocolDecoderException protocolDecoderException = new ProtocolDecoderException("No appropriate message decoder: " + hexDump);
                protocolDecoderException.setHexdump(hexDump);
                throw protocolDecoderException;
            } else if (state.currentDecoder == null) {
                return false;
            }
        }
        try {
            MessageDecoderResult decode = state.currentDecoder.decode(ioSession, ioBuffer, protocolDecoderOutput);
            if (decode == MessageDecoder.OK) {
                MessageDecoder unused2 = state.currentDecoder = null;
                return true;
            } else if (decode == MessageDecoder.NEED_DATA) {
                return false;
            } else {
                if (decode == MessageDecoder.NOT_OK) {
                    MessageDecoder unused3 = state.currentDecoder = null;
                    throw new ProtocolDecoderException("Message decoder returned NOT_OK.");
                }
                MessageDecoder unused4 = state.currentDecoder = null;
                throw new IllegalStateException("Unexpected decode result (see your decode()): " + decode);
            }
        } catch (Exception e) {
            MessageDecoder unused5 = state.currentDecoder = null;
            throw e;
        }
    }

    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        super.finishDecode(ioSession, protocolDecoderOutput);
        MessageDecoder access$200 = getState(ioSession).currentDecoder;
        if (access$200 != null) {
            access$200.finishDecode(ioSession, protocolDecoderOutput);
        }
    }

    public void dispose(IoSession ioSession) throws Exception {
        super.dispose(ioSession);
        ioSession.removeAttribute(this.STATE);
    }

    private State getState(IoSession ioSession) throws Exception {
        State state = (State) ioSession.getAttribute(this.STATE);
        if (state != null) {
            return state;
        }
        State state2 = new State();
        State state3 = (State) ioSession.setAttributeIfAbsent(this.STATE, state2);
        return state3 != null ? state3 : state2;
    }

    private class State {
        /* access modifiers changed from: private */
        public MessageDecoder currentDecoder;
        /* access modifiers changed from: private */
        public final MessageDecoder[] decoders;

        private State() throws Exception {
            MessageDecoderFactory[] access$500 = DemuxingProtocolDecoder.this.decoderFactories;
            this.decoders = new MessageDecoder[access$500.length];
            for (int length = access$500.length - 1; length >= 0; length--) {
                this.decoders[length] = access$500[length].getDecoder();
            }
        }
    }

    private static class SingletonMessageDecoderFactory implements MessageDecoderFactory {
        private final MessageDecoder decoder;

        private SingletonMessageDecoderFactory(MessageDecoder messageDecoder) {
            if (messageDecoder != null) {
                this.decoder = messageDecoder;
                return;
            }
            throw new IllegalArgumentException("decoder");
        }

        public MessageDecoder getDecoder() {
            return this.decoder;
        }
    }

    private static class DefaultConstructorMessageDecoderFactory implements MessageDecoderFactory {
        private final Class<?> decoderClass;

        private DefaultConstructorMessageDecoderFactory(Class<?> cls) {
            if (cls == null) {
                throw new IllegalArgumentException("decoderClass");
            } else if (MessageDecoder.class.isAssignableFrom(cls)) {
                this.decoderClass = cls;
            } else {
                throw new IllegalArgumentException("decoderClass is not assignable to MessageDecoder");
            }
        }

        public MessageDecoder getDecoder() throws Exception {
            return (MessageDecoder) this.decoderClass.newInstance();
        }
    }
}
