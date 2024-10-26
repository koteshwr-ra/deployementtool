package org.apache.mina.core.session;

import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.core.write.WriteRequestQueue;

public class DefaultIoSessionDataStructureFactory implements IoSessionDataStructureFactory {
    public IoSessionAttributeMap getAttributeMap(IoSession ioSession) throws Exception {
        return new DefaultIoSessionAttributeMap();
    }

    public WriteRequestQueue getWriteRequestQueue(IoSession ioSession) throws Exception {
        return new DefaultWriteRequestQueue();
    }

    private static class DefaultIoSessionAttributeMap implements IoSessionAttributeMap {
        private final Map<Object, Object> attributes = new ConcurrentHashMap(4);

        public void dispose(IoSession ioSession) throws Exception {
        }

        public Object getAttribute(IoSession ioSession, Object obj, Object obj2) {
            if (obj != null) {
                Object obj3 = this.attributes.get(obj);
                return obj3 == null ? obj2 : obj3;
            }
            throw new IllegalArgumentException("key");
        }

        public Object setAttribute(IoSession ioSession, Object obj, Object obj2) {
            if (obj == null) {
                throw new IllegalArgumentException("key");
            } else if (obj2 == null) {
                return this.attributes.remove(obj);
            } else {
                return this.attributes.put(obj, obj2);
            }
        }

        public Object setAttributeIfAbsent(IoSession ioSession, Object obj, Object obj2) {
            Object obj3;
            if (obj == null) {
                throw new IllegalArgumentException("key");
            } else if (obj2 == null) {
                return null;
            } else {
                synchronized (this.attributes) {
                    obj3 = this.attributes.get(obj);
                    if (obj3 == null) {
                        this.attributes.put(obj, obj2);
                    }
                }
                return obj3;
            }
        }

        public Object removeAttribute(IoSession ioSession, Object obj) {
            if (obj != null) {
                return this.attributes.remove(obj);
            }
            throw new IllegalArgumentException("key");
        }

        public boolean removeAttribute(IoSession ioSession, Object obj, Object obj2) {
            if (obj == null) {
                throw new IllegalArgumentException("key");
            } else if (obj2 == null) {
                return false;
            } else {
                synchronized (this.attributes) {
                    if (!obj2.equals(this.attributes.get(obj))) {
                        return false;
                    }
                    this.attributes.remove(obj);
                    return true;
                }
            }
        }

        public boolean replaceAttribute(IoSession ioSession, Object obj, Object obj2, Object obj3) {
            synchronized (this.attributes) {
                Object obj4 = this.attributes.get(obj);
                if (obj4 == null) {
                    return false;
                }
                if (!obj4.equals(obj2)) {
                    return false;
                }
                this.attributes.put(obj, obj3);
                return true;
            }
        }

        public boolean containsAttribute(IoSession ioSession, Object obj) {
            return this.attributes.containsKey(obj);
        }

        public Set<Object> getAttributeKeys(IoSession ioSession) {
            HashSet hashSet;
            synchronized (this.attributes) {
                hashSet = new HashSet(this.attributes.keySet());
            }
            return hashSet;
        }
    }

    private static class DefaultWriteRequestQueue implements WriteRequestQueue {
        private final Queue<WriteRequest> q = new ConcurrentLinkedQueue();

        public void dispose(IoSession ioSession) {
        }

        public void clear(IoSession ioSession) {
            this.q.clear();
        }

        public synchronized boolean isEmpty(IoSession ioSession) {
            return this.q.isEmpty();
        }

        public synchronized void offer(IoSession ioSession, WriteRequest writeRequest) {
            this.q.offer(writeRequest);
        }

        public synchronized WriteRequest poll(IoSession ioSession) {
            return this.q.poll();
        }

        public String toString() {
            return this.q.toString();
        }
    }
}
