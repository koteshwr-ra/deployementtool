package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.Unmodifiable;

public final class UnmodifiableOrderedMapIterator<K, V> implements OrderedMapIterator<K, V>, Unmodifiable {
    private final OrderedMapIterator<? extends K, ? extends V> iterator;

    public static <K, V> OrderedMapIterator<K, V> unmodifiableOrderedMapIterator(OrderedMapIterator<K, ? extends V> orderedMapIterator) {
        if (orderedMapIterator == null) {
            throw new NullPointerException("OrderedMapIterator must not be null");
        } else if (orderedMapIterator instanceof Unmodifiable) {
            return orderedMapIterator;
        } else {
            return new UnmodifiableOrderedMapIterator(orderedMapIterator);
        }
    }

    private UnmodifiableOrderedMapIterator(OrderedMapIterator<K, ? extends V> orderedMapIterator) {
        this.iterator = orderedMapIterator;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public K next() {
        return this.iterator.next();
    }

    public boolean hasPrevious() {
        return this.iterator.hasPrevious();
    }

    public K previous() {
        return this.iterator.previous();
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
