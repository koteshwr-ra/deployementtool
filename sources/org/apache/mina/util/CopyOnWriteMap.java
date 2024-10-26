package org.apache.mina.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CopyOnWriteMap<K, V> implements Map<K, V>, Cloneable {
    private volatile Map<K, V> internalMap;

    public CopyOnWriteMap() {
        this.internalMap = new HashMap();
    }

    public CopyOnWriteMap(int i) {
        this.internalMap = new HashMap(i);
    }

    public CopyOnWriteMap(Map<K, V> map) {
        this.internalMap = new HashMap(map);
    }

    public V put(K k, V v) {
        V put;
        synchronized (this) {
            HashMap hashMap = new HashMap(this.internalMap);
            put = hashMap.put(k, v);
            this.internalMap = hashMap;
        }
        return put;
    }

    public V remove(Object obj) {
        V remove;
        synchronized (this) {
            HashMap hashMap = new HashMap(this.internalMap);
            remove = hashMap.remove(obj);
            this.internalMap = hashMap;
        }
        return remove;
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        synchronized (this) {
            HashMap hashMap = new HashMap(this.internalMap);
            hashMap.putAll(map);
            this.internalMap = hashMap;
        }
    }

    public void clear() {
        synchronized (this) {
            this.internalMap = new HashMap();
        }
    }

    public int size() {
        return this.internalMap.size();
    }

    public boolean isEmpty() {
        return this.internalMap.isEmpty();
    }

    public boolean containsKey(Object obj) {
        return this.internalMap.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.internalMap.containsValue(obj);
    }

    public V get(Object obj) {
        return this.internalMap.get(obj);
    }

    public Set<K> keySet() {
        return this.internalMap.keySet();
    }

    public Collection<V> values() {
        return this.internalMap.values();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return this.internalMap.entrySet();
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }
}
