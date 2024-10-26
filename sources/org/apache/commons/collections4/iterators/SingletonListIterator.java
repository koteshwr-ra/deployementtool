package org.apache.commons.collections4.iterators;

import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableListIterator;

public class SingletonListIterator<E> implements ResettableListIterator<E> {
    private boolean beforeFirst = true;
    private boolean nextCalled = false;
    private E object;
    private boolean removed = false;

    public SingletonListIterator(E e) {
        this.object = e;
    }

    public boolean hasNext() {
        return this.beforeFirst && !this.removed;
    }

    public boolean hasPrevious() {
        return !this.beforeFirst && !this.removed;
    }

    public int nextIndex() {
        return this.beforeFirst ^ true ? 1 : 0;
    }

    public int previousIndex() {
        return this.beforeFirst ? -1 : 0;
    }

    public E next() {
        if (!this.beforeFirst || this.removed) {
            throw new NoSuchElementException();
        }
        this.beforeFirst = false;
        this.nextCalled = true;
        return this.object;
    }

    public E previous() {
        if (this.beforeFirst || this.removed) {
            throw new NoSuchElementException();
        }
        this.beforeFirst = true;
        return this.object;
    }

    public void remove() {
        if (!this.nextCalled || this.removed) {
            throw new IllegalStateException();
        }
        this.object = null;
        this.removed = true;
    }

    public void add(E e) {
        throw new UnsupportedOperationException("add() is not supported by this iterator");
    }

    public void set(E e) {
        if (!this.nextCalled || this.removed) {
            throw new IllegalStateException();
        }
        this.object = e;
    }

    public void reset() {
        this.beforeFirst = true;
        this.nextCalled = false;
    }
}
