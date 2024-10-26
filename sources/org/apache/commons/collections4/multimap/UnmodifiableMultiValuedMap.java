package org.apache.commons.collections4.multimap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.apache.commons.collections4.map.UnmodifiableMap;
import org.apache.commons.collections4.multiset.UnmodifiableMultiSet;
import org.apache.commons.collections4.set.UnmodifiableSet;

public final class UnmodifiableMultiValuedMap<K, V> extends AbstractMultiValuedMapDecorator<K, V> implements Unmodifiable {
    private static final long serialVersionUID = 20150612;

    public static <K, V> UnmodifiableMultiValuedMap<K, V> unmodifiableMultiValuedMap(MultiValuedMap<? extends K, ? extends V> multiValuedMap) {
        if (multiValuedMap instanceof Unmodifiable) {
            return (UnmodifiableMultiValuedMap) multiValuedMap;
        }
        return new UnmodifiableMultiValuedMap<>(multiValuedMap);
    }

    private UnmodifiableMultiValuedMap(MultiValuedMap<? extends K, ? extends V> multiValuedMap) {
        super(multiValuedMap);
    }

    public Collection<V> remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean removeMapping(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public Collection<V> get(K k) {
        return UnmodifiableCollection.unmodifiableCollection(decorated().get(k));
    }

    public boolean put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    public Set<K> keySet() {
        return UnmodifiableSet.unmodifiableSet(decorated().keySet());
    }

    public Collection<Map.Entry<K, V>> entries() {
        return UnmodifiableCollection.unmodifiableCollection(decorated().entries());
    }

    public MultiSet<K> keys() {
        return UnmodifiableMultiSet.unmodifiableMultiSet(decorated().keys());
    }

    public Collection<V> values() {
        return UnmodifiableCollection.unmodifiableCollection(decorated().values());
    }

    public Map<K, Collection<V>> asMap() {
        return UnmodifiableMap.unmodifiableMap(decorated().asMap());
    }

    public MapIterator<K, V> mapIterator() {
        return UnmodifiableMapIterator.unmodifiableMapIterator(decorated().mapIterator());
    }

    public boolean putAll(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    public boolean putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    public boolean putAll(MultiValuedMap<? extends K, ? extends V> multiValuedMap) {
        throw new UnsupportedOperationException();
    }
}
