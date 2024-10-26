package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import org.apache.commons.collections4.BoundedMap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.set.UnmodifiableSet;

public class FixedSizeSortedMap<K, V> extends AbstractSortedMapDecorator<K, V> implements BoundedMap<K, V>, Serializable {
    private static final long serialVersionUID = 3126019624511683653L;

    public boolean isFull() {
        return true;
    }

    public static <K, V> FixedSizeSortedMap<K, V> fixedSizeSortedMap(SortedMap<K, V> sortedMap) {
        return new FixedSizeSortedMap<>(sortedMap);
    }

    protected FixedSizeSortedMap(SortedMap<K, V> sortedMap) {
        super(sortedMap);
    }

    /* access modifiers changed from: protected */
    public SortedMap<K, V> getSortedMap() {
        return (SortedMap) this.map;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.map);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.map = (Map) objectInputStream.readObject();
    }

    public V put(K k, V v) {
        if (this.map.containsKey(k)) {
            return this.map.put(k, v);
        }
        throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        if (!CollectionUtils.isSubCollection(map.keySet(), keySet())) {
            this.map.putAll(map);
            return;
        }
        throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
    }

    public void clear() {
        throw new UnsupportedOperationException("Map is fixed size");
    }

    public V remove(Object obj) {
        throw new UnsupportedOperationException("Map is fixed size");
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return UnmodifiableSet.unmodifiableSet(this.map.entrySet());
    }

    public Set<K> keySet() {
        return UnmodifiableSet.unmodifiableSet(this.map.keySet());
    }

    public Collection<V> values() {
        return UnmodifiableCollection.unmodifiableCollection(this.map.values());
    }

    public SortedMap<K, V> subMap(K k, K k2) {
        return new FixedSizeSortedMap(getSortedMap().subMap(k, k2));
    }

    public SortedMap<K, V> headMap(K k) {
        return new FixedSizeSortedMap(getSortedMap().headMap(k));
    }

    public SortedMap<K, V> tailMap(K k) {
        return new FixedSizeSortedMap(getSortedMap().tailMap(k));
    }

    public int maxSize() {
        return size();
    }
}
