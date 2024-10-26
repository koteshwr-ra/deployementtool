package org.apache.commons.collections4.multimap;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiValuedMap;

public abstract class AbstractMultiValuedMapDecorator<K, V> implements MultiValuedMap<K, V>, Serializable {
    private static final long serialVersionUID = 20150612;
    private final MultiValuedMap<K, V> map;

    protected AbstractMultiValuedMapDecorator(MultiValuedMap<K, V> multiValuedMap) {
        if (multiValuedMap != null) {
            this.map = multiValuedMap;
            return;
        }
        throw new NullPointerException("MultiValuedMap must not be null.");
    }

    /* access modifiers changed from: protected */
    public MultiValuedMap<K, V> decorated() {
        return this.map;
    }

    public int size() {
        return decorated().size();
    }

    public boolean isEmpty() {
        return decorated().isEmpty();
    }

    public boolean containsKey(Object obj) {
        return decorated().containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return decorated().containsValue(obj);
    }

    public boolean containsMapping(Object obj, Object obj2) {
        return decorated().containsMapping(obj, obj2);
    }

    public Collection<V> get(K k) {
        return decorated().get(k);
    }

    public Collection<V> remove(Object obj) {
        return decorated().remove(obj);
    }

    public boolean removeMapping(Object obj, Object obj2) {
        return decorated().removeMapping(obj, obj2);
    }

    public void clear() {
        decorated().clear();
    }

    public boolean put(K k, V v) {
        return decorated().put(k, v);
    }

    public Set<K> keySet() {
        return decorated().keySet();
    }

    public Collection<Map.Entry<K, V>> entries() {
        return decorated().entries();
    }

    public MultiSet<K> keys() {
        return decorated().keys();
    }

    public Collection<V> values() {
        return decorated().values();
    }

    public Map<K, Collection<V>> asMap() {
        return decorated().asMap();
    }

    public boolean putAll(K k, Iterable<? extends V> iterable) {
        return decorated().putAll(k, iterable);
    }

    public boolean putAll(Map<? extends K, ? extends V> map2) {
        return decorated().putAll(map2);
    }

    public boolean putAll(MultiValuedMap<? extends K, ? extends V> multiValuedMap) {
        return decorated().putAll(multiValuedMap);
    }

    public MapIterator<K, V> mapIterator() {
        return decorated().mapIterator();
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
