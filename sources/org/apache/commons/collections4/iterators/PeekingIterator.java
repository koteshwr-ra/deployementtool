package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PeekingIterator<E> implements Iterator<E> {
    private boolean exhausted = false;
    private final Iterator<? extends E> iterator;
    private E slot;
    private boolean slotFilled = false;

    public static <E> PeekingIterator<E> peekingIterator(Iterator<? extends E> it) {
        if (it == null) {
            throw new NullPointerException("Iterator must not be null");
        } else if (it instanceof PeekingIterator) {
            return (PeekingIterator) it;
        } else {
            return new PeekingIterator<>(it);
        }
    }

    public PeekingIterator(Iterator<? extends E> it) {
        this.iterator = it;
    }

    private void fill() {
        if (!this.exhausted && !this.slotFilled) {
            if (this.iterator.hasNext()) {
                this.slot = this.iterator.next();
                this.slotFilled = true;
                return;
            }
            this.exhausted = true;
            this.slot = null;
            this.slotFilled = false;
        }
    }

    public boolean hasNext() {
        if (this.exhausted) {
            return false;
        }
        if (this.slotFilled) {
            return true;
        }
        return this.iterator.hasNext();
    }

    public E peek() {
        fill();
        if (this.exhausted) {
            return null;
        }
        return this.slot;
    }

    public E element() {
        fill();
        if (!this.exhausted) {
            return this.slot;
        }
        throw new NoSuchElementException();
    }

    public E next() {
        if (hasNext()) {
            E next = this.slotFilled ? this.slot : this.iterator.next();
            this.slot = null;
            this.slotFilled = false;
            return next;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        if (!this.slotFilled) {
            this.iterator.remove();
            return;
        }
        throw new IllegalStateException("peek() or element() called before remove()");
    }
}
