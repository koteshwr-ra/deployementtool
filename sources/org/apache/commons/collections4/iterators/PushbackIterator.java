package org.apache.commons.collections4.iterators;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class PushbackIterator<E> implements Iterator<E> {
    private Deque<E> items = new ArrayDeque();
    private final Iterator<? extends E> iterator;

    public static <E> PushbackIterator<E> pushbackIterator(Iterator<? extends E> it) {
        if (it == null) {
            throw new NullPointerException("Iterator must not be null");
        } else if (it instanceof PushbackIterator) {
            return (PushbackIterator) it;
        } else {
            return new PushbackIterator<>(it);
        }
    }

    public PushbackIterator(Iterator<? extends E> it) {
        this.iterator = it;
    }

    public void pushback(E e) {
        this.items.push(e);
    }

    public boolean hasNext() {
        if (!this.items.isEmpty()) {
            return true;
        }
        return this.iterator.hasNext();
    }

    public E next() {
        return !this.items.isEmpty() ? this.items.pop() : this.iterator.next();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
