package org.apache.mina.filter.executor;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoEvent;
import org.apache.mina.core.write.WriteRequest;

public class DefaultIoEventSizeEstimator implements IoEventSizeEstimator {
    private final ConcurrentMap<Class<?>, Integer> class2size;

    public DefaultIoEventSizeEstimator() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        this.class2size = concurrentHashMap;
        concurrentHashMap.put(Boolean.TYPE, 4);
        this.class2size.put(Byte.TYPE, 1);
        this.class2size.put(Character.TYPE, 2);
        this.class2size.put(Integer.TYPE, 4);
        this.class2size.put(Short.TYPE, 2);
        this.class2size.put(Long.TYPE, 8);
        this.class2size.put(Float.TYPE, 4);
        this.class2size.put(Double.TYPE, 8);
        this.class2size.put(Void.TYPE, 0);
    }

    public int estimateSize(IoEvent ioEvent) {
        return estimateSize((Object) ioEvent) + estimateSize(ioEvent.getParameter());
    }

    public int estimateSize(Object obj) {
        int length;
        if (obj == null) {
            return 8;
        }
        int estimateSize = estimateSize(obj.getClass(), (Set<Class<?>>) null) + 8;
        if (obj instanceof IoBuffer) {
            length = ((IoBuffer) obj).remaining();
        } else if (obj instanceof WriteRequest) {
            length = estimateSize(((WriteRequest) obj).getMessage());
        } else if (obj instanceof CharSequence) {
            length = ((CharSequence) obj).length() << 1;
        } else {
            if (obj instanceof Iterable) {
                for (Object estimateSize2 : (Iterable) obj) {
                    estimateSize += estimateSize(estimateSize2);
                }
            }
            return align(estimateSize);
        }
        estimateSize += length;
        return align(estimateSize);
    }

    private int estimateSize(Class<?> cls, Set<Class<?>> set) {
        Integer num = (Integer) this.class2size.get(cls);
        if (num != null) {
            return num.intValue();
        }
        if (set == null) {
            set = new HashSet<>();
        } else if (set.contains(cls)) {
            return 0;
        }
        set.add(cls);
        int i = 8;
        for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            for (Field field : cls2.getDeclaredFields()) {
                if ((field.getModifiers() & 8) == 0) {
                    i += estimateSize(field.getType(), set);
                }
            }
        }
        set.remove(cls);
        int align = align(i);
        this.class2size.putIfAbsent(cls, Integer.valueOf(align));
        return align;
    }

    private static int align(int i) {
        return i % 8 != 0 ? ((i / 8) + 1) * 8 : i;
    }
}
