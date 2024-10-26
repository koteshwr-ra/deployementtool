package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.MapIterator;

public class AbstractMapIteratorDecorator<K, V> implements MapIterator<K, V> {
    private final MapIterator<K, V> iterator;

    public AbstractMapIteratorDecorator(MapIterator<K, V> mapIterator) {
        if (mapIterator != null) {
            this.iterator = mapIterator;
            return;
        }
        throw new NullPointerException("MapIterator must not be null");
    }

    /* access modifiers changed from: protected */
    public MapIterator<K, V> getMapIterator() {
        return this.iterator;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public K next() {
        return this.iterator.next();
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
