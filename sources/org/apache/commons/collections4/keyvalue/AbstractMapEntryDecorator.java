package org.apache.commons.collections4.keyvalue;

import java.util.Map;
import org.apache.commons.collections4.KeyValue;

public abstract class AbstractMapEntryDecorator<K, V> implements Map.Entry<K, V>, KeyValue<K, V> {
    private final Map.Entry<K, V> entry;

    public AbstractMapEntryDecorator(Map.Entry<K, V> entry2) {
        if (entry2 != null) {
            this.entry = entry2;
            return;
        }
        throw new NullPointerException("Map Entry must not be null.");
    }

    /* access modifiers changed from: protected */
    public Map.Entry<K, V> getMapEntry() {
        return this.entry;
    }

    public K getKey() {
        return this.entry.getKey();
    }

    public V getValue() {
        return this.entry.getValue();
    }

    public V setValue(V v) {
        return this.entry.setValue(v);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return this.entry.equals(obj);
    }

    public int hashCode() {
        return this.entry.hashCode();
    }

    public String toString() {
        return this.entry.toString();
    }
}
