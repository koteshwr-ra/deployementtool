package org.apache.commons.collections4.iterators;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableListIterator;

public class LoopingListIterator<E> implements ResettableListIterator<E> {
    private ListIterator<E> iterator;
    private final List<E> list;

    public LoopingListIterator(List<E> list2) {
        if (list2 != null) {
            this.list = list2;
            _reset();
            return;
        }
        throw new NullPointerException("The list must not be null");
    }

    public boolean hasNext() {
        return !this.list.isEmpty();
    }

    public E next() {
        if (!this.list.isEmpty()) {
            if (!this.iterator.hasNext()) {
                reset();
            }
            return this.iterator.next();
        }
        throw new NoSuchElementException("There are no elements for this iterator to loop on");
    }

    public int nextIndex() {
        if (this.list.isEmpty()) {
            throw new NoSuchElementException("There are no elements for this iterator to loop on");
        } else if (!this.iterator.hasNext()) {
            return 0;
        } else {
            return this.iterator.nextIndex();
        }
    }

    public boolean hasPrevious() {
        return !this.list.isEmpty();
    }

    public E previous() {
        if (this.list.isEmpty()) {
            throw new NoSuchElementException("There are no elements for this iterator to loop on");
        } else if (this.iterator.hasPrevious()) {
            return this.iterator.previous();
        } else {
            E e = null;
            while (this.iterator.hasNext()) {
                e = this.iterator.next();
            }
            this.iterator.previous();
            return e;
        }
    }

    public int previousIndex() {
        if (this.list.isEmpty()) {
            throw new NoSuchElementException("There are no elements for this iterator to loop on");
        } else if (!this.iterator.hasPrevious()) {
            return this.list.size() - 1;
        } else {
            return this.iterator.previousIndex();
        }
    }

    public void remove() {
        this.iterator.remove();
    }

    public void add(E e) {
        this.iterator.add(e);
    }

    public void set(E e) {
        this.iterator.set(e);
    }

    public void reset() {
        _reset();
    }

    private void _reset() {
        this.iterator = this.list.listIterator();
    }

    public int size() {
        return this.list.size();
    }
}
