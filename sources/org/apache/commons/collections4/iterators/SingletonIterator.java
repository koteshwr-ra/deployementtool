package org.apache.commons.collections4.iterators;

import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableIterator;

public class SingletonIterator<E> implements ResettableIterator<E> {
    private boolean beforeFirst;
    private E object;
    private final boolean removeAllowed;
    private boolean removed;

    public SingletonIterator(E e) {
        this(e, true);
    }

    public SingletonIterator(E e, boolean z) {
        this.beforeFirst = true;
        this.removed = false;
        this.object = e;
        this.removeAllowed = z;
    }

    public boolean hasNext() {
        return this.beforeFirst && !this.removed;
    }

    public E next() {
        if (!this.beforeFirst || this.removed) {
            throw new NoSuchElementException();
        }
        this.beforeFirst = false;
        return this.object;
    }

    public void remove() {
        if (!this.removeAllowed) {
            throw new UnsupportedOperationException();
        } else if (this.removed || this.beforeFirst) {
            throw new IllegalStateException();
        } else {
            this.object = null;
            this.removed = true;
        }
    }

    public void reset() {
        this.beforeFirst = true;
    }
}
