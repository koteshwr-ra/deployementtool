package org.apache.commons.collections4;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public interface MultiSet<E> extends Collection<E> {

    public interface Entry<E> {
        boolean equals(Object obj);

        int getCount();

        E getElement();

        int hashCode();
    }

    int add(E e, int i);

    boolean add(E e);

    boolean containsAll(Collection<?> collection);

    Set<Entry<E>> entrySet();

    boolean equals(Object obj);

    int getCount(Object obj);

    int hashCode();

    Iterator<E> iterator();

    int remove(Object obj, int i);

    boolean remove(Object obj);

    boolean removeAll(Collection<?> collection);

    boolean retainAll(Collection<?> collection);

    int setCount(E e, int i);

    int size();

    Set<E> uniqueSet();
}
