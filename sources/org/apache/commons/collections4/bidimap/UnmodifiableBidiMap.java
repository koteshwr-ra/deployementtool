package org.apache.commons.collections4.bidimap;

import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.apache.commons.collections4.map.UnmodifiableEntrySet;
import org.apache.commons.collections4.set.UnmodifiableSet;

public final class UnmodifiableBidiMap<K, V> extends AbstractBidiMapDecorator<K, V> implements Unmodifiable {
    private UnmodifiableBidiMap<V, K> inverse;

    public static <K, V> BidiMap<K, V> unmodifiableBidiMap(BidiMap<? extends K, ? extends V> bidiMap) {
        if (bidiMap instanceof Unmodifiable) {
            return bidiMap;
        }
        return new UnmodifiableBidiMap(bidiMap);
    }

    private UnmodifiableBidiMap(BidiMap<? extends K, ? extends V> bidiMap) {
        super(bidiMap);
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    public V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return UnmodifiableEntrySet.unmodifiableEntrySet(super.entrySet());
    }

    public Set<K> keySet() {
        return UnmodifiableSet.unmodifiableSet(super.keySet());
    }

    public Set<V> values() {
        return UnmodifiableSet.unmodifiableSet(super.values());
    }

    public K removeValue(Object obj) {
        throw new UnsupportedOperationException();
    }

    public MapIterator<K, V> mapIterator() {
        return UnmodifiableMapIterator.unmodifiableMapIterator(decorated().mapIterator());
    }

    public synchronized BidiMap<V, K> inverseBidiMap() {
        if (this.inverse == null) {
            UnmodifiableBidiMap<V, K> unmodifiableBidiMap = new UnmodifiableBidiMap<>(decorated().inverseBidiMap());
            this.inverse = unmodifiableBidiMap;
            unmodifiableBidiMap.inverse = this;
        }
        return this.inverse;
    }
}
