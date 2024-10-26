package org.apache.mina.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LazyInitializedCacheMap<K, V> implements Map<K, V> {
    private ConcurrentMap<K, LazyInitializer<V>> cache;

    public class NoopInitializer extends LazyInitializer<V> {
        private V value;

        public NoopInitializer(V v) {
            this.value = v;
        }

        public V init() {
            return this.value;
        }
    }

    public LazyInitializedCacheMap() {
        this.cache = new ConcurrentHashMap();
    }

    public LazyInitializedCacheMap(ConcurrentHashMap<K, LazyInitializer<V>> concurrentHashMap) {
        this.cache = concurrentHashMap;
    }

    public V get(Object obj) {
        LazyInitializer lazyInitializer = (LazyInitializer) this.cache.get(obj);
        if (lazyInitializer != null) {
            return lazyInitializer.get();
        }
        return null;
    }

    public V remove(Object obj) {
        LazyInitializer lazyInitializer = (LazyInitializer) this.cache.remove(obj);
        if (lazyInitializer != null) {
            return lazyInitializer.get();
        }
        return null;
    }

    public V putIfAbsent(K k, LazyInitializer<V> lazyInitializer) {
        LazyInitializer lazyInitializer2 = (LazyInitializer) this.cache.get(k);
        if (lazyInitializer2 == null && (lazyInitializer2 = (LazyInitializer) this.cache.putIfAbsent(k, lazyInitializer)) == null) {
            return lazyInitializer.get();
        }
        return lazyInitializer2.get();
    }

    public V put(K k, V v) {
        LazyInitializer lazyInitializer = (LazyInitializer) this.cache.put(k, new NoopInitializer(v));
        if (lazyInitializer != null) {
            return lazyInitializer.get();
        }
        return null;
    }

    public boolean containsValue(Object obj) {
        throw new UnsupportedOperationException();
    }

    public Collection<V> values() {
        throw new UnsupportedOperationException();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry next : map.entrySet()) {
            this.cache.put(next.getKey(), new NoopInitializer(next.getValue()));
        }
    }

    public Collection<LazyInitializer<V>> getValues() {
        return this.cache.values();
    }

    public void clear() {
        this.cache.clear();
    }

    public boolean containsKey(Object obj) {
        return this.cache.containsKey(obj);
    }

    public boolean isEmpty() {
        return this.cache.isEmpty();
    }

    public Set<K> keySet() {
        return this.cache.keySet();
    }

    public int size() {
        return this.cache.size();
    }
}
