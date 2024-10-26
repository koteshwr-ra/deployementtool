package org.apache.commons.collections4.set;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;

public final class UnmodifiableNavigableSet<E> extends AbstractNavigableSetDecorator<E> implements Unmodifiable {
    private static final long serialVersionUID = 20150528;

    public static <E> NavigableSet<E> unmodifiableNavigableSet(NavigableSet<E> navigableSet) {
        if (navigableSet instanceof Unmodifiable) {
            return navigableSet;
        }
        return new UnmodifiableNavigableSet(navigableSet);
    }

    private UnmodifiableNavigableSet(NavigableSet<E> navigableSet) {
        super(navigableSet);
    }

    public Iterator<E> iterator() {
        return UnmodifiableIterator.unmodifiableIterator(decorated().iterator());
    }

    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public SortedSet<E> subSet(E e, E e2) {
        return UnmodifiableSortedSet.unmodifiableSortedSet(decorated().subSet(e, e2));
    }

    public SortedSet<E> headSet(E e) {
        return UnmodifiableSortedSet.unmodifiableSortedSet(decorated().headSet(e));
    }

    public SortedSet<E> tailSet(E e) {
        return UnmodifiableSortedSet.unmodifiableSortedSet(decorated().tailSet(e));
    }

    public NavigableSet<E> descendingSet() {
        return unmodifiableNavigableSet(decorated().descendingSet());
    }

    public Iterator<E> descendingIterator() {
        return UnmodifiableIterator.unmodifiableIterator(decorated().descendingIterator());
    }

    public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        return unmodifiableNavigableSet(decorated().subSet(e, z, e2, z2));
    }

    public NavigableSet<E> headSet(E e, boolean z) {
        return unmodifiableNavigableSet(decorated().headSet(e, z));
    }

    public NavigableSet<E> tailSet(E e, boolean z) {
        return unmodifiableNavigableSet(decorated().tailSet(e, z));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(decorated());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        setCollection((Collection) objectInputStream.readObject());
    }
}
