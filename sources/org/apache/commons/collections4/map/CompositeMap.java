package org.apache.commons.collections4.map;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.collection.CompositeCollection;
import org.apache.commons.collections4.set.CompositeSet;

public class CompositeMap<K, V> extends AbstractIterableMap<K, V> implements Serializable {
    private static final long serialVersionUID = -6096931280583808322L;
    private Map<K, V>[] composite;
    private MapMutator<K, V> mutator;

    public interface MapMutator<K, V> extends Serializable {
        V put(CompositeMap<K, V> compositeMap, Map<K, V>[] mapArr, K k, V v);

        void putAll(CompositeMap<K, V> compositeMap, Map<K, V>[] mapArr, Map<? extends K, ? extends V> map);

        void resolveCollision(CompositeMap<K, V> compositeMap, Map<K, V> map, Map<K, V> map2, Collection<K> collection);
    }

    public CompositeMap() {
        this((Map<K, V>[]) new Map[0], (MapMutator) null);
    }

    public CompositeMap(Map<K, V> map, Map<K, V> map2) {
        this((Map<K, V>[]) new Map[]{map, map2}, (MapMutator) null);
    }

    public CompositeMap(Map<K, V> map, Map<K, V> map2, MapMutator<K, V> mapMutator) {
        this((Map<K, V>[]) new Map[]{map, map2}, mapMutator);
    }

    public CompositeMap(Map<K, V>... mapArr) {
        this(mapArr, (MapMutator) null);
    }

    public CompositeMap(Map<K, V>[] mapArr, MapMutator<K, V> mapMutator) {
        this.mutator = mapMutator;
        this.composite = new Map[0];
        for (int length = mapArr.length - 1; length >= 0; length--) {
            addComposited(mapArr[length]);
        }
    }

    public void setMutator(MapMutator<K, V> mapMutator) {
        this.mutator = mapMutator;
    }

    public synchronized void addComposited(Map<K, V> map) throws IllegalArgumentException {
        for (int length = this.composite.length - 1; length >= 0; length--) {
            Collection<O> intersection = CollectionUtils.intersection(this.composite[length].keySet(), map.keySet());
            if (intersection.size() != 0) {
                if (this.mutator != null) {
                    this.mutator.resolveCollision(this, this.composite[length], map, intersection);
                } else {
                    throw new IllegalArgumentException("Key collision adding Map to CompositeMap");
                }
            }
        }
        int length2 = this.composite.length + 1;
        Map<K, V>[] mapArr = new Map[length2];
        System.arraycopy(this.composite, 0, mapArr, 0, this.composite.length);
        mapArr[length2 - 1] = map;
        this.composite = mapArr;
    }

    public synchronized Map<K, V> removeComposited(Map<K, V> map) {
        int length = this.composite.length;
        for (int i = 0; i < length; i++) {
            if (this.composite[i].equals(map)) {
                Map<K, V>[] mapArr = new Map[(length - 1)];
                System.arraycopy(this.composite, 0, mapArr, 0, i);
                System.arraycopy(this.composite, i + 1, mapArr, i, (length - i) - 1);
                this.composite = mapArr;
                return map;
            }
        }
        return null;
    }

    public void clear() {
        for (int length = this.composite.length - 1; length >= 0; length--) {
            this.composite[length].clear();
        }
    }

    public boolean containsKey(Object obj) {
        for (int length = this.composite.length - 1; length >= 0; length--) {
            if (this.composite[length].containsKey(obj)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Object obj) {
        for (int length = this.composite.length - 1; length >= 0; length--) {
            if (this.composite[length].containsValue(obj)) {
                return true;
            }
        }
        return false;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        CompositeSet compositeSet = new CompositeSet();
        for (int length = this.composite.length - 1; length >= 0; length--) {
            compositeSet.addComposited(this.composite[length].entrySet());
        }
        return compositeSet;
    }

    public V get(Object obj) {
        for (int length = this.composite.length - 1; length >= 0; length--) {
            if (this.composite[length].containsKey(obj)) {
                return this.composite[length].get(obj);
            }
        }
        return null;
    }

    public boolean isEmpty() {
        for (int length = this.composite.length - 1; length >= 0; length--) {
            if (!this.composite[length].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public Set<K> keySet() {
        CompositeSet compositeSet = new CompositeSet();
        for (int length = this.composite.length - 1; length >= 0; length--) {
            compositeSet.addComposited(this.composite[length].keySet());
        }
        return compositeSet;
    }

    public V put(K k, V v) {
        MapMutator<K, V> mapMutator = this.mutator;
        if (mapMutator != null) {
            return mapMutator.put(this, this.composite, k, v);
        }
        throw new UnsupportedOperationException("No mutator specified");
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        MapMutator<K, V> mapMutator = this.mutator;
        if (mapMutator != null) {
            mapMutator.putAll(this, this.composite, map);
            return;
        }
        throw new UnsupportedOperationException("No mutator specified");
    }

    public V remove(Object obj) {
        for (int length = this.composite.length - 1; length >= 0; length--) {
            if (this.composite[length].containsKey(obj)) {
                return this.composite[length].remove(obj);
            }
        }
        return null;
    }

    public int size() {
        int i = 0;
        for (int length = this.composite.length - 1; length >= 0; length--) {
            i += this.composite[length].size();
        }
        return i;
    }

    public Collection<V> values() {
        CompositeCollection compositeCollection = new CompositeCollection();
        for (int length = this.composite.length - 1; length >= 0; length--) {
            compositeCollection.addComposited(this.composite[length].values());
        }
        return compositeCollection;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        for (Map.Entry hashCode : entrySet()) {
            i += hashCode.hashCode();
        }
        return i;
    }
}
