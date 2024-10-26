package org.apache.mina.util;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashSet<E> extends MapBackedSet<E> {
    private static final long serialVersionUID = 8518578988740277828L;

    public ConcurrentHashSet() {
        super(new ConcurrentHashMap());
    }

    public ConcurrentHashSet(Collection<E> collection) {
        super(new ConcurrentHashMap(), collection);
    }

    public boolean add(E e) {
        return ((Boolean) ((ConcurrentMap) this.map).putIfAbsent(e, Boolean.TRUE)) == null;
    }
}
