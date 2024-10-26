package org.apache.mina.handler.demux;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.session.UnknownMessageTypeException;
import org.apache.mina.util.IdentityHashSet;

public class DemuxingIoHandler extends IoHandlerAdapter {
    private final Map<Class<?>, ExceptionHandler<?>> exceptionHandlerCache = new ConcurrentHashMap();
    private final Map<Class<?>, ExceptionHandler<?>> exceptionHandlers = new ConcurrentHashMap();
    private final Map<Class<?>, MessageHandler<?>> receivedMessageHandlerCache = new ConcurrentHashMap();
    private final Map<Class<?>, MessageHandler<?>> receivedMessageHandlers = new ConcurrentHashMap();
    private final Map<Class<?>, MessageHandler<?>> sentMessageHandlerCache = new ConcurrentHashMap();
    private final Map<Class<?>, MessageHandler<?>> sentMessageHandlers = new ConcurrentHashMap();

    public <E> MessageHandler<? super E> addReceivedMessageHandler(Class<E> cls, MessageHandler<? super E> messageHandler) {
        this.receivedMessageHandlerCache.clear();
        return this.receivedMessageHandlers.put(cls, messageHandler);
    }

    public <E> MessageHandler<? super E> removeReceivedMessageHandler(Class<E> cls) {
        this.receivedMessageHandlerCache.clear();
        return this.receivedMessageHandlers.remove(cls);
    }

    public <E> MessageHandler<? super E> addSentMessageHandler(Class<E> cls, MessageHandler<? super E> messageHandler) {
        this.sentMessageHandlerCache.clear();
        return this.sentMessageHandlers.put(cls, messageHandler);
    }

    public <E> MessageHandler<? super E> removeSentMessageHandler(Class<E> cls) {
        this.sentMessageHandlerCache.clear();
        return this.sentMessageHandlers.remove(cls);
    }

    public <E extends Throwable> ExceptionHandler<? super E> addExceptionHandler(Class<E> cls, ExceptionHandler<? super E> exceptionHandler) {
        this.exceptionHandlerCache.clear();
        return this.exceptionHandlers.put(cls, exceptionHandler);
    }

    public <E extends Throwable> ExceptionHandler<? super E> removeExceptionHandler(Class<E> cls) {
        this.exceptionHandlerCache.clear();
        return this.exceptionHandlers.remove(cls);
    }

    public <E> MessageHandler<? super E> getMessageHandler(Class<E> cls) {
        return this.receivedMessageHandlers.get(cls);
    }

    public Map<Class<?>, MessageHandler<?>> getReceivedMessageHandlerMap() {
        return Collections.unmodifiableMap(this.receivedMessageHandlers);
    }

    public Map<Class<?>, MessageHandler<?>> getSentMessageHandlerMap() {
        return Collections.unmodifiableMap(this.sentMessageHandlers);
    }

    public Map<Class<?>, ExceptionHandler<?>> getExceptionHandlerMap() {
        return Collections.unmodifiableMap(this.exceptionHandlers);
    }

    public void messageReceived(IoSession ioSession, Object obj) throws Exception {
        MessageHandler<Object> findReceivedMessageHandler = findReceivedMessageHandler(obj.getClass());
        if (findReceivedMessageHandler != null) {
            findReceivedMessageHandler.handleMessage(ioSession, obj);
            return;
        }
        throw new UnknownMessageTypeException("No message handler found for message type: " + obj.getClass().getSimpleName());
    }

    public void messageSent(IoSession ioSession, Object obj) throws Exception {
        MessageHandler<Object> findSentMessageHandler = findSentMessageHandler(obj.getClass());
        if (findSentMessageHandler != null) {
            findSentMessageHandler.handleMessage(ioSession, obj);
            return;
        }
        throw new UnknownMessageTypeException("No handler found for message type: " + obj.getClass().getSimpleName());
    }

    public void exceptionCaught(IoSession ioSession, Throwable th) throws Exception {
        ExceptionHandler<Throwable> findExceptionHandler = findExceptionHandler(th.getClass());
        if (findExceptionHandler != null) {
            findExceptionHandler.exceptionCaught(ioSession, th);
            return;
        }
        throw new UnknownMessageTypeException("No handler found for exception type: " + th.getClass().getSimpleName());
    }

    /* access modifiers changed from: protected */
    public MessageHandler<Object> findReceivedMessageHandler(Class<?> cls) {
        return findReceivedMessageHandler(cls, (Set<Class>) null);
    }

    /* access modifiers changed from: protected */
    public MessageHandler<Object> findSentMessageHandler(Class<?> cls) {
        return findSentMessageHandler(cls, (Set<Class>) null);
    }

    /* access modifiers changed from: protected */
    public ExceptionHandler<Throwable> findExceptionHandler(Class<? extends Throwable> cls) {
        return findExceptionHandler(cls, (Set<Class>) null);
    }

    private MessageHandler<Object> findReceivedMessageHandler(Class cls, Set<Class> set) {
        return (MessageHandler) findHandler(this.receivedMessageHandlers, this.receivedMessageHandlerCache, cls, set);
    }

    private MessageHandler<Object> findSentMessageHandler(Class cls, Set<Class> set) {
        return (MessageHandler) findHandler(this.sentMessageHandlers, this.sentMessageHandlerCache, cls, set);
    }

    private ExceptionHandler<Throwable> findExceptionHandler(Class cls, Set<Class> set) {
        return (ExceptionHandler) findHandler(this.exceptionHandlers, this.exceptionHandlerCache, cls, set);
    }

    private Object findHandler(Map map, Map map2, Class cls, Set<Class> set) {
        Class superclass;
        if (set != null && set.contains(cls)) {
            return null;
        }
        Object obj = map2.get(cls);
        if (obj != null) {
            return obj;
        }
        Object obj2 = map.get(cls);
        if (obj2 == null) {
            if (set == null) {
                set = new IdentityHashSet<>();
            }
            set.add(cls);
            for (Class findHandler : cls.getInterfaces()) {
                obj2 = findHandler(map, map2, findHandler, set);
                if (obj2 != null) {
                    break;
                }
            }
        }
        if (obj2 == null && (superclass = cls.getSuperclass()) != null) {
            obj2 = findHandler(map, map2, superclass, (Set<Class>) null);
        }
        if (obj2 != null) {
            map2.put(cls, obj2);
        }
        return obj2;
    }
}
