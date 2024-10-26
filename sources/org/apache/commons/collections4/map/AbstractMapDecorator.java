package org.apache.commons.collections4.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapDecorator<K, V> extends AbstractIterableMap<K, V> {
    transient Map<K, V> map;

    protected AbstractMapDecorator() {
    }

    protected AbstractMapDecorator(Map<K, V> map2) {
        if (map2 != null) {
            this.map = map2;
            return;
        }
        throw new NullPointerException("Map must not be null.");
    }

    /* access modifiers changed from: protected */
    public Map<K, V> decorated() {
        return this.map;
    }

    public void clear() {
        decorated().clear();
    }

    public boolean containsKey(Object obj) {
        return decorated().containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return decorated().containsValue(obj);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return decorated().entrySet();
    }

    public V get(Object obj) {
        return decorated().get(obj);
    }

    public boolean isEmpty() {
        return decorated().isEmpty();
    }

    public Set<K> keySet() {
        return decorated().keySet();
    }

    public V put(K k, V v) {
        return decorated().put(k, v);
    }

    public void putAll(Map<? extends K, ? extends V> map2) {
        decorated().putAll(map2);
    }

    public V remove(Object obj) {
        return decorated().remove(obj);
    }

    public int size() {
        return decorated().size();
    }

    public Collection<V> values() {
        return decorated().values();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return decorated().equals(obj);
    }

    public int hashCode() {
        return decorated().hashCode();
    }

    public String toString() {
        return decorated().toString();
    }
}
