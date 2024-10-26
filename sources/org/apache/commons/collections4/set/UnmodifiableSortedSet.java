package org.apache.commons.collections4.set;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;

public final class UnmodifiableSortedSet<E> extends AbstractSortedSetDecorator<E> implements Unmodifiable {
    private static final long serialVersionUID = -725356885467962424L;

    public static <E> SortedSet<E> unmodifiableSortedSet(SortedSet<E> sortedSet) {
        if (sortedSet instanceof Unmodifiable) {
            return sortedSet;
        }
        return new UnmodifiableSortedSet(sortedSet);
    }

    private UnmodifiableSortedSet(SortedSet<E> sortedSet) {
        super(sortedSet);
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
        return unmodifiableSortedSet(decorated().subSet(e, e2));
    }

    public SortedSet<E> headSet(E e) {
        return unmodifiableSortedSet(decorated().headSet(e));
    }

    public SortedSet<E> tailSet(E e) {
        return unmodifiableSortedSet(decorated().tailSet(e));
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
