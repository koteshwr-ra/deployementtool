package org.apache.commons.collections4.set;

import java.util.Iterator;
import java.util.NavigableSet;
import org.apache.commons.collections4.Transformer;

public class TransformedNavigableSet<E> extends TransformedSortedSet<E> implements NavigableSet<E> {
    private static final long serialVersionUID = 20150528;

    public static <E> TransformedNavigableSet<E> transformingNavigableSet(NavigableSet<E> navigableSet, Transformer<? super E, ? extends E> transformer) {
        return new TransformedNavigableSet<>(navigableSet, transformer);
    }

    public static <E> TransformedNavigableSet<E> transformedNavigableSet(NavigableSet<E> navigableSet, Transformer<? super E, ? extends E> transformer) {
        TransformedNavigableSet<E> transformedNavigableSet = new TransformedNavigableSet<>(navigableSet, transformer);
        if (navigableSet.size() > 0) {
            Object[] array = navigableSet.toArray();
            navigableSet.clear();
            for (Object transform : array) {
                transformedNavigableSet.decorated().add(transformer.transform(transform));
            }
        }
        return transformedNavigableSet;
    }

    protected TransformedNavigableSet(NavigableSet<E> navigableSet, Transformer<? super E, ? extends E> transformer) {
        super(navigableSet, transformer);
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
        return transformingNavigableSet(decorated().descendingSet(), this.transformer);
    }

    public Iterator<E> descendingIterator() {
        return decorated().descendingIterator();
    }

    public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        return transformingNavigableSet(decorated().subSet(e, z, e2, z2), this.transformer);
    }

    public NavigableSet<E> headSet(E e, boolean z) {
        return transformingNavigableSet(decorated().headSet(e, z), this.transformer);
    }

    public NavigableSet<E> tailSet(E e, boolean z) {
        return transformingNavigableSet(decorated().tailSet(e, z), this.transformer);
    }
}
