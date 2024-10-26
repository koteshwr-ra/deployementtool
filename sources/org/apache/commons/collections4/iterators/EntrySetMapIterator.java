package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import java.util.Map;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.ResettableIterator;

public class EntrySetMapIterator<K, V> implements MapIterator<K, V>, ResettableIterator<K> {
    private boolean canRemove = false;
    private Iterator<Map.Entry<K, V>> iterator;
    private Map.Entry<K, V> last;
    private final Map<K, V> map;

    public EntrySetMapIterator(Map<K, V> map2) {
        this.map = map2;
        this.iterator = map2.entrySet().iterator();
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public K next() {
        Map.Entry<K, V> next = this.iterator.next();
        this.last = next;
        this.canRemove = true;
        return next.getKey();
    }

    public void remove() {
        if (this.canRemove) {
            this.iterator.remove();
            this.last = null;
            this.canRemove = false;
            return;
        }
        throw new IllegalStateException("Iterator remove() can only be called once after next()");
    }

    public K getKey() {
        Map.Entry<K, V> entry = this.last;
        if (entry != null) {
            return entry.getKey();
        }
        throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
    }

    public V getValue() {
        Map.Entry<K, V> entry = this.last;
        if (entry != null) {
            return entry.getValue();
        }
        throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
    }

    public V setValue(V v) {
        Map.Entry<K, V> entry = this.last;
        if (entry != null) {
            return entry.setValue(v);
        }
        throw new IllegalStateException("Iterator setValue() can only be called after next() and before remove()");
    }

    public void reset() {
        this.iterator = this.map.entrySet().iterator();
        this.last = null;
        this.canRemove = false;
    }

    public String toString() {
        if (this.last == null) {
            return "MapIterator[]";
        }
        return "MapIterator[" + getKey() + "=" + getValue() + "]";
    }
}
