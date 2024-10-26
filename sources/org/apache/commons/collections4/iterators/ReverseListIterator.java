package org.apache.commons.collections4.iterators;

import java.util.List;
import java.util.ListIterator;
import org.apache.commons.collections4.ResettableListIterator;

public class ReverseListIterator<E> implements ResettableListIterator<E> {
    private ListIterator<E> iterator;
    private final List<E> list;
    private boolean validForUpdate = true;

    public ReverseListIterator(List<E> list2) {
        if (list2 != null) {
            this.list = list2;
            this.iterator = list2.listIterator(list2.size());
            return;
        }
        throw new NullPointerException("List must not be null.");
    }

    public boolean hasNext() {
        return this.iterator.hasPrevious();
    }

    public E next() {
        E previous = this.iterator.previous();
        this.validForUpdate = true;
        return previous;
    }

    public int nextIndex() {
        return this.iterator.previousIndex();
    }

    public boolean hasPrevious() {
        return this.iterator.hasNext();
    }

    public E previous() {
        E next = this.iterator.next();
        this.validForUpdate = true;
        return next;
    }

    public int previousIndex() {
        return this.iterator.nextIndex();
    }

    public void remove() {
        if (this.validForUpdate) {
            this.iterator.remove();
            return;
        }
        throw new IllegalStateException("Cannot remove from list until next() or previous() called");
    }

    public void set(E e) {
        if (this.validForUpdate) {
            this.iterator.set(e);
            return;
        }
        throw new IllegalStateException("Cannot set to list until next() or previous() called");
    }

    public void add(E e) {
        if (this.validForUpdate) {
            this.validForUpdate = false;
            this.iterator.add(e);
            this.iterator.previous();
            return;
        }
        throw new IllegalStateException("Cannot add to list until next() or previous() called");
    }

    public void reset() {
        List<E> list2 = this.list;
        this.iterator = list2.listIterator(list2.size());
    }
}
