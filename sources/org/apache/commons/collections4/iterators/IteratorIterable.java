package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import org.apache.commons.collections4.ResettableIterator;

public class IteratorIterable<E> implements Iterable<E> {
    private final Iterator<? extends E> iterator;
    private final Iterator<E> typeSafeIterator;

    private static <E> Iterator<E> createTypesafeIterator(final Iterator<? extends E> it) {
        return new Iterator<E>() {
            public boolean hasNext() {
                return it.hasNext();
            }

            public E next() {
                return it.next();
            }

            public void remove() {
                it.remove();
            }
        };
    }

    public IteratorIterable(Iterator<? extends E> it) {
        this(it, false);
    }

    public IteratorIterable(Iterator<? extends E> it, boolean z) {
        if (!z || (it instanceof ResettableIterator)) {
            this.iterator = it;
        } else {
            this.iterator = new ListIteratorWrapper(it);
        }
        this.typeSafeIterator = createTypesafeIterator(this.iterator);
    }

    public Iterator<E> iterator() {
        Iterator<? extends E> it = this.iterator;
        if (it instanceof ResettableIterator) {
            ((ResettableIterator) it).reset();
        }
        return this.typeSafeIterator;
    }
}
