package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import org.apache.commons.collections4.Unmodifiable;

public final class UnmodifiableIterator<E> implements Iterator<E>, Unmodifiable {
    private final Iterator<? extends E> iterator;

    public static <E> Iterator<E> unmodifiableIterator(Iterator<? extends E> it) {
        if (it == null) {
            throw new NullPointerException("Iterator must not be null");
        } else if (it instanceof Unmodifiable) {
            return it;
        } else {
            return new UnmodifiableIterator(it);
        }
    }

    private UnmodifiableIterator(Iterator<? extends E> it) {
        this.iterator = it;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public E next() {
        return this.iterator.next();
    }

    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported");
    }
}
