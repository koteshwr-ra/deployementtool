package org.apache.mina.util;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class MapBackedSet<E> extends AbstractSet<E> implements Serializable {
    private static final long serialVersionUID = -8347878570391674042L;
    protected final Map<E, Boolean> map;

    public MapBackedSet(Map<E, Boolean> map2) {
        this.map = map2;
    }

    public MapBackedSet(Map<E, Boolean> map2, Collection<E> collection) {
        this.map = map2;
        addAll(collection);
    }

    public int size() {
        return this.map.size();
    }

    public boolean contains(Object obj) {
        return this.map.containsKey(obj);
    }

    public Iterator<E> iterator() {
        return this.map.keySet().iterator();
    }

    public boolean add(E e) {
        return this.map.put(e, Boolean.TRUE) == null;
    }

    public boolean remove(Object obj) {
        return this.map.remove(obj) != null;
    }

    public void clear() {
        this.map.clear();
    }
}
