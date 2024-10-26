package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.Unmodifiable;

public final class UnmodifiableMapIterator<K, V> implements MapIterator<K, V>, Unmodifiable {
    private final MapIterator<? extends K, ? extends V> iterator;

    public static <K, V> MapIterator<K, V> unmodifiableMapIterator(MapIterator<? extends K, ? extends V> mapIterator) {
        if (mapIterator == null) {
            throw new NullPointerException("MapIterator must not be null");
        } else if (mapIterator instanceof Unmodifiable) {
            return mapIterator;
        } else {
            return new UnmodifiableMapIterator(mapIterator);
        }
    }

    private UnmodifiableMapIterator(MapIterator<? extends K, ? extends V> mapIterator) {
        this.iterator = mapIterator;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public K next() {
        return this.iterator.next();
    }

    public K getKey() {
        return this.iterator.getKey();
    }

    public V getValue() {
        return this.iterator.getValue();
    }

    public V setValue(V v) {
        throw new UnsupportedOperationException("setValue() is not supported");
    }

    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported");
    }
}
