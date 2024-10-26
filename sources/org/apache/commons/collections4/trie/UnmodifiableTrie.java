package org.apache.commons.collections4.trie;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableOrderedMapIterator;

public class UnmodifiableTrie<K, V> implements Trie<K, V>, Serializable, Unmodifiable {
    private static final long serialVersionUID = -7156426030315945159L;
    private final Trie<K, V> delegate;

    public static <K, V> Trie<K, V> unmodifiableTrie(Trie<K, ? extends V> trie) {
        if (trie instanceof Unmodifiable) {
            return trie;
        }
        return new UnmodifiableTrie(trie);
    }

    public UnmodifiableTrie(Trie<K, ? extends V> trie) {
        if (trie != null) {
            this.delegate = trie;
            return;
        }
        throw new NullPointerException("Trie must not be null");
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return Collections.unmodifiableSet(this.delegate.entrySet());
    }

    public Set<K> keySet() {
        return Collections.unmodifiableSet(this.delegate.keySet());
    }

    public Collection<V> values() {
        return Collections.unmodifiableCollection(this.delegate.values());
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean containsKey(Object obj) {
        return this.delegate.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.delegate.containsValue(obj);
    }

    public V get(Object obj) {
        return this.delegate.get(obj);
    }

    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    public V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    public V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return this.delegate.size();
    }

    public K firstKey() {
        return this.delegate.firstKey();
    }

    public SortedMap<K, V> headMap(K k) {
        return Collections.unmodifiableSortedMap(this.delegate.headMap(k));
    }

    public K lastKey() {
        return this.delegate.lastKey();
    }

    public SortedMap<K, V> subMap(K k, K k2) {
        return Collections.unmodifiableSortedMap(this.delegate.subMap(k, k2));
    }

    public SortedMap<K, V> tailMap(K k) {
        return Collections.unmodifiableSortedMap(this.delegate.tailMap(k));
    }

    public SortedMap<K, V> prefixMap(K k) {
        return Collections.unmodifiableSortedMap(this.delegate.prefixMap(k));
    }

    public Comparator<? super K> comparator() {
        return this.delegate.comparator();
    }

    public OrderedMapIterator<K, V> mapIterator() {
        return UnmodifiableOrderedMapIterator.unmodifiableOrderedMapIterator(this.delegate.mapIterator());
    }

    public K nextKey(K k) {
        return this.delegate.nextKey(k);
    }

    public K previousKey(K k) {
        return this.delegate.previousKey(k);
    }

    public int hashCode() {
        return this.delegate.hashCode();
    }

    public boolean equals(Object obj) {
        return this.delegate.equals(obj);
    }

    public String toString() {
        return this.delegate.toString();
    }
}
