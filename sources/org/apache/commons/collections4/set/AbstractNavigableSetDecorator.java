package org.apache.commons.collections4.set;

import java.util.Iterator;
import java.util.NavigableSet;

public abstract class AbstractNavigableSetDecorator<E> extends AbstractSortedSetDecorator<E> implements NavigableSet<E> {
    private static final long serialVersionUID = 20150528;

    protected AbstractNavigableSetDecorator() {
    }

    protected AbstractNavigableSetDecorator(NavigableSet<E> navigableSet) {
        super(navigableSet);
    }

    /* access modifiers changed from: protected */
    public NavigableSet<E> decorated() {
        return (NavigableSet) super.decorated();
    }

    public E lower(E e) {
        return decorated().lower(e);
    }

    public E floor(E e) {
        return decorated().floor(e);
    }

    public E ceiling(E e) {
        return decorated().ceiling(e);
    }

    public E higher(E e) {
        return decorated().higher(e);
    }

    public E pollFirst() {
        return decorated().pollFirst();
    }

    public E pollLast() {
        return decorated().pollLast();
    }

    public NavigableSet<E> descendingSet() {
        return decorated().descendingSet();
    }

    public Iterator<E> descendingIterator() {
        return decorated().descendingIterator();
    }

    public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        return decorated().subSet(e, z, e2, z2);
    }

    public NavigableSet<E> headSet(E e, boolean z) {
        return decorated().headSet(e, z);
    }

    public NavigableSet<E> tailSet(E e, boolean z) {
        return decorated().tailSet(e, z);
    }
}
