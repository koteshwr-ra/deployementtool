package org.apache.commons.collections4.iterators;

import java.util.ListIterator;
import org.apache.commons.collections4.Unmodifiable;

public final class UnmodifiableListIterator<E> implements ListIterator<E>, Unmodifiable {
    private final ListIterator<? extends E> iterator;

    public static <E> ListIterator<E> umodifiableListIterator(ListIterator<? extends E> listIterator) {
        if (listIterator == null) {
            throw new NullPointerException("ListIterator must not be null");
        } else if (listIterator instanceof Unmodifiable) {
            return listIterator;
        } else {
            return new UnmodifiableListIterator(listIterator);
        }
    }

    private UnmodifiableListIterator(ListIterator<? extends E> listIterator) {
        this.iterator = listIterator;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public E next() {
        return this.iterator.next();
    }

    public int nextIndex() {
        return this.iterator.nextIndex();
    }

    public boolean hasPrevious() {
        return this.iterator.hasPrevious();
    }

    public E previous() {
        return this.iterator.previous();
    }

    public int previousIndex() {
        return this.iterator.previousIndex();
    }

    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported");
    }

    public void set(E e) {
        throw new UnsupportedOperationException("set() is not supported");
    }

    public void add(E e) {
        throw new UnsupportedOperationException("add() is not supported");
    }
}
