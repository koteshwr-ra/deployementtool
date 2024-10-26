package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;

public class PassiveExpiringMap<K, V> extends AbstractMapDecorator<K, V> implements Serializable {
    private static final long serialVersionUID = 1;
    private final Map<Object, Long> expirationMap;
    private final ExpirationPolicy<K, V> expiringPolicy;

    public interface ExpirationPolicy<K, V> extends Serializable {
        long expirationTime(K k, V v);
    }

    public static class ConstantTimeToLiveExpirationPolicy<K, V> implements ExpirationPolicy<K, V> {
        private static final long serialVersionUID = 1;
        private final long timeToLiveMillis;

        public ConstantTimeToLiveExpirationPolicy() {
            this(-1);
        }

        public ConstantTimeToLiveExpirationPolicy(long j) {
            this.timeToLiveMillis = j;
        }

        public ConstantTimeToLiveExpirationPolicy(long j, TimeUnit timeUnit) {
            this(PassiveExpiringMap.validateAndConvertToMillis(j, timeUnit));
        }

        public long expirationTime(K k, V v) {
            if (this.timeToLiveMillis < 0) {
                return -1;
            }
            long currentTimeMillis = System.currentTimeMillis();
            long j = this.timeToLiveMillis;
            if (currentTimeMillis > LongCompanionObject.MAX_VALUE - j) {
                return -1;
            }
            return currentTimeMillis + j;
        }
    }

    /* access modifiers changed from: private */
    public static long validateAndConvertToMillis(long j, TimeUnit timeUnit) {
        if (timeUnit != null) {
            return TimeUnit.MILLISECONDS.convert(j, timeUnit);
        }
        throw new NullPointerException("Time unit must not be null");
    }

    public PassiveExpiringMap() {
        this(-1);
    }

    public PassiveExpiringMap(ExpirationPolicy<K, V> expirationPolicy) {
        this(expirationPolicy, new HashMap());
    }

    public PassiveExpiringMap(ExpirationPolicy<K, V> expirationPolicy, Map<K, V> map) {
        super(map);
        this.expirationMap = new HashMap();
        if (expirationPolicy != null) {
            this.expiringPolicy = expirationPolicy;
            return;
        }
        throw new NullPointerException("Policy must not be null.");
    }

    public PassiveExpiringMap(long j) {
        this(new ConstantTimeToLiveExpirationPolicy(j), new HashMap());
    }

    public PassiveExpiringMap(long j, Map<K, V> map) {
        this(new ConstantTimeToLiveExpirationPolicy(j), map);
    }

    public PassiveExpiringMap(long j, TimeUnit timeUnit) {
        this(validateAndConvertToMillis(j, timeUnit));
    }

    public PassiveExpiringMap(long j, TimeUnit timeUnit, Map<K, V> map) {
        this(validateAndConvertToMillis(j, timeUnit), map);
    }

    public PassiveExpiringMap(Map<K, V> map) {
        this(-1, map);
    }

    public void clear() {
        super.clear();
        this.expirationMap.clear();
    }

    public boolean containsKey(Object obj) {
        removeIfExpired(obj, now());
        return super.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        removeAllExpired(now());
        return super.containsValue(obj);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        removeAllExpired(now());
        return super.entrySet();
    }

    public V get(Object obj) {
        removeIfExpired(obj, now());
        return super.get(obj);
    }

    public boolean isEmpty() {
        removeAllExpired(now());
        return super.isEmpty();
    }

    private boolean isExpired(long j, Long l) {
        if (l == null) {
            return false;
        }
        long longValue = l.longValue();
        return longValue >= 0 && j >= longValue;
    }

    public Set<K> keySet() {
        removeAllExpired(now());
        return super.keySet();
    }

    private long now() {
        return System.currentTimeMillis();
    }

    public V put(K k, V v) {
        this.expirationMap.put(k, Long.valueOf(this.expiringPolicy.expirationTime(k, v)));
        return super.put(k, v);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry next : map.entrySet()) {
            put(next.getKey(), next.getValue());
        }
    }

    public V remove(Object obj) {
        this.expirationMap.remove(obj);
        return super.remove(obj);
    }

    private void removeAllExpired(long j) {
        Iterator<Map.Entry<Object, Long>> it = this.expirationMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (isExpired(j, (Long) next.getValue())) {
                super.remove(next.getKey());
                it.remove();
            }
        }
    }

    private void removeIfExpired(Object obj, long j) {
        if (isExpired(j, this.expirationMap.get(obj))) {
            remove(obj);
        }
    }

    public int size() {
        removeAllExpired(now());
        return super.size();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.map = (Map) objectInputStream.readObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.map);
    }

    public Collection<V> values() {
        removeAllExpired(now());
        return super.values();
    }
}
