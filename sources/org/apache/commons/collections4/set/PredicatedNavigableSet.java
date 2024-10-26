package org.apache.commons.collections4.set;

import java.util.Iterator;
import java.util.NavigableSet;
import org.apache.commons.collections4.Predicate;

public class PredicatedNavigableSet<E> extends PredicatedSortedSet<E> implements NavigableSet<E> {
    private static final long serialVersionUID = 20150528;

    public static <E> PredicatedNavigableSet<E> predicatedNavigableSet(NavigableSet<E> navigableSet, Predicate<? super E> predicate) {
        return new PredicatedNavigableSet<>(navigableSet, predicate);
    }

    protected PredicatedNavigableSet(NavigableSet<E> navigableSet, Predicate<? super E> predicate) {
        super(navigableSet, predicate);
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
        return predicatedNavigableSet(decorated().descendingSet(), this.predicate);
    }

    public Iterator<E> descendingIterator() {
        return decorated().descendingIterator();
    }

    public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        return predicatedNavigableSet(decorated().subSet(e, z, e2, z2), this.predicate);
    }

    public NavigableSet<E> headSet(E e, boolean z) {
        return predicatedNavigableSet(decorated().headSet(e, z), this.predicate);
    }

    public NavigableSet<E> tailSet(E e, boolean z) {
        return predicatedNavigableSet(decorated().tailSet(e, z), this.predicate);
    }
}
