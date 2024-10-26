package org.apache.commons.collections4.map;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.ResettableIterator;

public class EntrySetToMapIteratorAdapter<K, V> implements MapIterator<K, V>, ResettableIterator<K> {
    transient Map.Entry<K, V> entry;
    Set<Map.Entry<K, V>> entrySet;
    transient Iterator<Map.Entry<K, V>> iterator;

    public EntrySetToMapIteratorAdapter(Set<Map.Entry<K, V>> set) {
        this.entrySet = set;
        reset();
    }

    public K getKey() {
        return current().getKey();
    }

    public V getValue() {
        return current().getValue();
    }

    public V setValue(V v) {
        return current().setValue(v);
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public K next() {
        this.entry = this.iterator.next();
        return getKey();
    }

    public synchronized void reset() {
        this.iterator = this.entrySet.iterator();
    }

    public void remove() {
        this.iterator.remove();
        this.entry = null;
    }

    /* access modifiers changed from: protected */
    public synchronized Map.Entry<K, V> current() {
        if (this.entry != null) {
        } else {
            throw new IllegalStateException();
        }
        return this.entry;
    }
}
