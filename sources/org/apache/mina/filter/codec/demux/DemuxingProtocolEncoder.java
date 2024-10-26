package org.apache.mina.filter.codec.demux;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.UnknownMessageTypeException;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.util.CopyOnWriteMap;
import org.apache.mina.util.IdentityHashSet;

public class DemuxingProtocolEncoder implements ProtocolEncoder {
    private static final Class<?>[] EMPTY_PARAMS = new Class[0];
    private final AttributeKey STATE = new AttributeKey(getClass(), "state");
    /* access modifiers changed from: private */
    public final Map<Class<?>, MessageEncoderFactory> type2encoderFactory = new CopyOnWriteMap();

    public void addMessageEncoder(Class<?> cls, Class<? extends MessageEncoder> cls2) {
        if (cls2 != null) {
            try {
                cls2.getConstructor(EMPTY_PARAMS);
                boolean z = false;
                if (MessageEncoder.class.isAssignableFrom(cls2)) {
                    addMessageEncoder(cls, new DefaultConstructorMessageEncoderFactory(cls2));
                    z = true;
                }
                if (!z) {
                    throw new IllegalArgumentException("Unregisterable type: " + cls2);
                }
            } catch (NoSuchMethodException unused) {
                throw new IllegalArgumentException("The specified class doesn't have a public default constructor.");
            }
        } else {
            throw new IllegalArgumentException("encoderClass");
        }
    }

    public <T> void addMessageEncoder(Class<T> cls, MessageEncoder<? super T> messageEncoder) {
        addMessageEncoder(cls, new SingletonMessageEncoderFactory(messageEncoder));
    }

    public <T> void addMessageEncoder(Class<T> cls, MessageEncoderFactory<? super T> messageEncoderFactory) {
        if (cls == null) {
            throw new IllegalArgumentException("messageType");
        } else if (messageEncoderFactory != null) {
            synchronized (this.type2encoderFactory) {
                if (!this.type2encoderFactory.containsKey(cls)) {
                    this.type2encoderFactory.put(cls, messageEncoderFactory);
                } else {
                    throw new IllegalStateException("The specified message type (" + cls.getName() + ") is registered already.");
                }
            }
        } else {
            throw new IllegalArgumentException("factory");
        }
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

    public void encode(IoSession ioSession, Object obj, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        MessageEncoder<Object> findEncoder = findEncoder(getState(ioSession), obj.getClass());
        if (findEncoder != null) {
            findEncoder.encode(ioSession, obj, protocolEncoderOutput);
            return;
        }
        throw new UnknownMessageTypeException("No message encoder found for message: " + obj);
    }

    /* access modifiers changed from: protected */
    public MessageEncoder<Object> findEncoder(State state, Class<?> cls) {
        return findEncoder(state, cls, (Set<Class>) null);
    }

    private MessageEncoder<Object> findEncoder(State state, Class cls, Set<Class> set) {
        Class superclass;
        if (set != null && set.contains(cls)) {
            return null;
        }
        MessageEncoder<Object> messageEncoder = (MessageEncoder) state.findEncoderCache.get(cls);
        if (messageEncoder != null) {
            return messageEncoder;
        }
        MessageEncoder<Object> messageEncoder2 = (MessageEncoder) state.type2encoder.get(cls);
        if (messageEncoder2 == null) {
            if (set == null) {
                set = new IdentityHashSet<>();
            }
            set.add(cls);
            for (Class findEncoder : cls.getInterfaces()) {
                messageEncoder2 = findEncoder(state, findEncoder, set);
                if (messageEncoder2 != null) {
                    break;
                }
            }
        }
        if (messageEncoder2 == null && (superclass = cls.getSuperclass()) != null) {
            messageEncoder2 = findEncoder(state, superclass);
        }
        if (messageEncoder2 != null) {
            state.findEncoderCache.put(cls, messageEncoder2);
        }
        return messageEncoder2;
    }

    public void dispose(IoSession ioSession) throws Exception {
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
        public final Map<Class<?>, MessageEncoder> findEncoderCache;
        /* access modifiers changed from: private */
        public final Map<Class<?>, MessageEncoder> type2encoder;

        private State() throws Exception {
            this.findEncoderCache = new ConcurrentHashMap();
            this.type2encoder = new ConcurrentHashMap();
            for (Map.Entry entry : DemuxingProtocolEncoder.this.type2encoderFactory.entrySet()) {
                this.type2encoder.put(entry.getKey(), ((MessageEncoderFactory) entry.getValue()).getEncoder());
            }
        }
    }

    private static class SingletonMessageEncoderFactory<T> implements MessageEncoderFactory<T> {
        private final MessageEncoder<T> encoder;

        private SingletonMessageEncoderFactory(MessageEncoder<T> messageEncoder) {
            if (messageEncoder != null) {
                this.encoder = messageEncoder;
                return;
            }
            throw new IllegalArgumentException("encoder");
        }

        public MessageEncoder<T> getEncoder() {
            return this.encoder;
        }
    }

    private static class DefaultConstructorMessageEncoderFactory<T> implements MessageEncoderFactory<T> {
        private final Class<MessageEncoder<T>> encoderClass;

        private DefaultConstructorMessageEncoderFactory(Class<MessageEncoder<T>> cls) {
            if (cls == null) {
                throw new IllegalArgumentException("encoderClass");
            } else if (MessageEncoder.class.isAssignableFrom(cls)) {
                this.encoderClass = cls;
            } else {
                throw new IllegalArgumentException("encoderClass is not assignable to MessageEncoder");
            }
        }

        public MessageEncoder<T> getEncoder() throws Exception {
            return this.encoderClass.newInstance();
        }
    }
}
