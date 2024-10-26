package org.apache.commons.collections4;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.apache.commons.collections4.map.EntrySetToMapIteratorAdapter;
import org.apache.commons.collections4.map.UnmodifiableEntrySet;
import org.apache.commons.collections4.set.UnmodifiableSet;

public class SplitMapUtils {
    private SplitMapUtils() {
    }

    private static class WrappedGet<K, V> implements IterableMap<K, V>, Unmodifiable {
        private final Get<K, V> get;

        private WrappedGet(Get<K, V> get2) {
            this.get = get2;
        }

        public void clear() {
            throw new UnsupportedOperationException();
        }

        public boolean containsKey(Object obj) {
            return this.get.containsKey(obj);
        }

        public boolean containsValue(Object obj) {
            return this.get.containsValue(obj);
        }

        public Set<Map.Entry<K, V>> entrySet() {
            return UnmodifiableEntrySet.unmodifiableEntrySet(this.get.entrySet());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof WrappedGet) && ((WrappedGet) obj).get.equals(this.get);
        }

        public V get(Object obj) {
            return this.get.get(obj);
        }

        public int hashCode() {
            return this.get.hashCode() | 360074000;
        }

        public boolean isEmpty() {
            return this.get.isEmpty();
        }

        public Set<K> keySet() {
            return UnmodifiableSet.unmodifiableSet(this.get.keySet());
        }

        public V put(K k, V v) {
            throw new UnsupportedOperationException();
        }

        public void putAll(Map<? extends K, ? extends V> map) {
            throw new UnsupportedOperationException();
        }

        public V remove(Object obj) {
            return this.get.remove(obj);
        }

        public int size() {
            return this.get.size();
        }

        public Collection<V> values() {
            return UnmodifiableCollection.unmodifiableCollection(this.get.values());
        }

        public MapIterator<K, V> mapIterator() {
            MapIterator mapIterator;
            Get<K, V> get2 = this.get;
            if (get2 instanceof IterableGet) {
                mapIterator = ((IterableGet) get2).mapIterator();
            } else {
                mapIterator = new EntrySetToMapIteratorAdapter(get2.entrySet());
            }
            return UnmodifiableMapIterator.unmodifiableMapIterator(mapIterator);
        }
    }

    private static class WrappedPut<K, V> implements Map<K, V>, Put<K, V> {
        private final Put<K, V> put;

        private WrappedPut(Put<K, V> put2) {
            this.put = put2;
        }

        public void clear() {
            this.put.clear();
        }

        public boolean containsKey(Object obj) {
            throw new UnsupportedOperationException();
        }

        public boolean containsValue(Object obj) {
            throw new UnsupportedOperationException();
        }

        public Set<Map.Entry<K, V>> entrySet() {
            throw new UnsupportedOperationException();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof WrappedPut) && ((WrappedPut) obj).put.equals(this.put);
        }

        public V get(Object obj) {
            throw new UnsupportedOperationException();
        }

        public int hashCode() {
            return this.put.hashCode() | 360220320;
        }

        public boolean isEmpty() {
            throw new UnsupportedOperationException();
        }

        public Set<K> keySet() {
            throw new UnsupportedOperationException();
        }

        public V put(K k, V v) {
            return this.put.put(k, v);
        }

        public void putAll(Map<? extends K, ? extends V> map) {
            this.put.putAll(map);
        }

        public V remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        public int size() {
            throw new UnsupportedOperationException();
        }

        public Collection<V> values() {
            throw new UnsupportedOperationException();
        }
    }

    public static <K, V> IterableMap<K, V> readableMap(Get<K, V> get) {
        if (get == null) {
            throw new NullPointerException("Get must not be null");
        } else if (get instanceof Map) {
            return get instanceof IterableMap ? (IterableMap) get : MapUtils.iterableMap((Map) get);
        } else {
            return new WrappedGet(get);
        }
    }

    public static <K, V> Map<K, V> writableMap(Put<K, V> put) {
        if (put == null) {
            throw new NullPointerException("Put must not be null");
        } else if (put instanceof Map) {
            return (Map) put;
        } else {
            return new WrappedPut(put);
        }
    }
}
