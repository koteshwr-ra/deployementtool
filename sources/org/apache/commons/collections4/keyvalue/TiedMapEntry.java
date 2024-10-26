package org.apache.commons.collections4.keyvalue;

import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.KeyValue;

public class TiedMapEntry<K, V> implements Map.Entry<K, V>, KeyValue<K, V>, Serializable {
    private static final long serialVersionUID = -8453869361373831205L;
    private final K key;
    private final Map<K, V> map;

    public TiedMapEntry(Map<K, V> map2, K k) {
        this.map = map2;
        this.key = k;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.map.get(this.key);
    }

    public V setValue(V v) {
        if (v != this) {
            return this.map.put(this.key, v);
        }
        throw new IllegalArgumentException("Cannot set value to this map entry");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object value = getValue();
        K k = this.key;
        if (k != null ? k.equals(entry.getKey()) : entry.getKey() == null) {
            Object value2 = entry.getValue();
            if (value == null) {
                if (value2 == null) {
                    return true;
                }
            } else if (value.equals(value2)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        Object value = getValue();
        int i = 0;
        int hashCode = getKey() == null ? 0 : getKey().hashCode();
        if (value != null) {
            i = value.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        return getKey() + "=" + getValue();
    }
}
