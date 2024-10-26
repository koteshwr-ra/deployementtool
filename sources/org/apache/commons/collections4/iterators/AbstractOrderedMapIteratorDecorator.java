package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.OrderedMapIterator;

public class AbstractOrderedMapIteratorDecorator<K, V> implements OrderedMapIterator<K, V> {
    private final OrderedMapIterator<K, V> iterator;

    public AbstractOrderedMapIteratorDecorator(OrderedMapIterator<K, V> orderedMapIterator) {
        if (orderedMapIterator != null) {
            this.iterator = orderedMapIterator;
            return;
        }
        throw new NullPointerException("OrderedMapIterator must not be null");
    }

    /* access modifiers changed from: protected */
    public OrderedMapIterator<K, V> getOrderedMapIterator() {
        return this.iterator;
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

    public void remove() {
        this.iterator.remove();
    }

    public K getKey() {
        return this.iterator.getKey();
    }

    public V getValue() {
        return this.iterator.getValue();
    }

    public V setValue(V v) {
        return this.iterator.setValue(v);
    }
}
