package org.apache.commons.collections4.iterators;

import java.util.Iterator;

public abstract class AbstractUntypedIteratorDecorator<I, O> implements Iterator<O> {
    private final Iterator<I> iterator;

    protected AbstractUntypedIteratorDecorator(Iterator<I> it) {
        if (it != null) {
            this.iterator = it;
            return;
        }
        throw new NullPointerException("Iterator must not be null");
    }

    /* access modifiers changed from: protected */
    public Iterator<I> getIterator() {
        return this.iterator;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public void remove() {
        this.iterator.remove();
    }
}
