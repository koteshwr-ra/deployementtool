package org.apache.commons.collections4.set;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class MapBackedSet<E, V> implements Set<E>, Serializable {
    private static final long serialVersionUID = 6723912213766056587L;
    private final V dummyValue;
    private final Map<E, ? super V> map;

    public static <E, V> MapBackedSet<E, V> mapBackedSet(Map<E, ? super V> map2) {
        return mapBackedSet(map2, (Object) null);
    }

    public static <E, V> MapBackedSet<E, V> mapBackedSet(Map<E, ? super V> map2, V v) {
        return new MapBackedSet<>(map2, v);
    }

    private MapBackedSet(Map<E, ? super V> map2, V v) {
        if (map2 != null) {
            this.map = map2;
            this.dummyValue = v;
            return;
        }
        throw new NullPointerException("The map must not be null");
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public Iterator<E> iterator() {
        return this.map.keySet().iterator();
    }

    public boolean contains(Object obj) {
        return this.map.containsKey(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return this.map.keySet().containsAll(collection);
    }

    public boolean add(E e) {
        int size = this.map.size();
        this.map.put(e, this.dummyValue);
        return this.map.size() != size;
    }

    public boolean addAll(Collection<? extends E> collection) {
        int size = this.map.size();
        for (Object put : collection) {
            this.map.put(put, this.dummyValue);
        }
        return this.map.size() != size;
    }

    public boolean remove(Object obj) {
        int size = this.map.size();
        this.map.remove(obj);
        return this.map.size() != size;
    }

    public boolean removeAll(Collection<?> collection) {
        return this.map.keySet().removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        return this.map.keySet().retainAll(collection);
    }

    public void clear() {
        this.map.clear();
    }

    public Object[] toArray() {
        return this.map.keySet().toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return this.map.keySet().toArray(tArr);
    }

    public boolean equals(Object obj) {
        return this.map.keySet().equals(obj);
    }

    public int hashCode() {
        return this.map.keySet().hashCode();
    }
}
