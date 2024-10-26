package org.apache.commons.collections4.iterators;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationIterator<E> implements Iterator<E> {
    private final Collection<? super E> collection;
    private Enumeration<? extends E> enumeration;
    private E last;

    public EnumerationIterator() {
        this((Enumeration) null, (Collection) null);
    }

    public EnumerationIterator(Enumeration<? extends E> enumeration2) {
        this(enumeration2, (Collection) null);
    }

    public EnumerationIterator(Enumeration<? extends E> enumeration2, Collection<? super E> collection2) {
        this.enumeration = enumeration2;
        this.collection = collection2;
        this.last = null;
    }

    public boolean hasNext() {
        return this.enumeration.hasMoreElements();
    }

    public E next() {
        E nextElement = this.enumeration.nextElement();
        this.last = nextElement;
        return nextElement;
    }

    public void remove() {
        Collection<? super E> collection2 = this.collection;
        if (collection2 != null) {
            E e = this.last;
            if (e != null) {
                collection2.remove(e);
                return;
            }
            throw new IllegalStateException("next() must have been called for remove() to function");
        }
        throw new UnsupportedOperationException("No Collection associated with this Iterator");
    }

    public Enumeration<? extends E> getEnumeration() {
        return this.enumeration;
    }

    public void setEnumeration(Enumeration<? extends E> enumeration2) {
        this.enumeration = enumeration2;
    }
}
