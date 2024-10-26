package org.apache.commons.collections4.iterators;

import java.util.Iterator;

public class SkippingIterator<E> extends AbstractIteratorDecorator<E> {
    private final long offset;
    private long pos;

    public SkippingIterator(Iterator<E> it, long j) {
        super(it);
        if (j >= 0) {
            this.offset = j;
            this.pos = 0;
            init();
            return;
        }
        throw new IllegalArgumentException("Offset parameter must not be negative.");
    }

    private void init() {
        while (this.pos < this.offset && hasNext()) {
            next();
        }
    }

    public E next() {
        E next = super.next();
        this.pos++;
        return next;
    }

    public void remove() {
        if (this.pos > this.offset) {
            super.remove();
            return;
        }
        throw new IllegalStateException("remove() can not be called before calling next()");
    }
}
