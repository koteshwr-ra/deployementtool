package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.FluentIterable;

public class ZippingIterator<E> implements Iterator<E> {
    private final Iterator<Iterator<? extends E>> iterators;
    private Iterator<? extends E> lastReturned;
    private Iterator<? extends E> nextIterator;

    public ZippingIterator(Iterator<? extends E> it, Iterator<? extends E> it2) {
        this(it, it2);
    }

    public ZippingIterator(Iterator<? extends E> it, Iterator<? extends E> it2, Iterator<? extends E> it3) {
        this(it, it2, it3);
    }

    public ZippingIterator(Iterator<? extends E>... itArr) {
        this.nextIterator = null;
        this.lastReturned = null;
        ArrayList arrayList = new ArrayList();
        int length = itArr.length;
        int i = 0;
        while (i < length) {
            Iterator<? extends E> it = itArr[i];
            if (it != null) {
                arrayList.add(it);
                i++;
            } else {
                throw new NullPointerException("Iterator must not be null.");
            }
        }
        this.iterators = FluentIterable.of(arrayList).loop().iterator();
    }

    public boolean hasNext() {
        if (this.nextIterator != null) {
            return true;
        }
        while (this.iterators.hasNext()) {
            Iterator<? extends E> next = this.iterators.next();
            if (next.hasNext()) {
                this.nextIterator = next;
                return true;
            }
            this.iterators.remove();
        }
        return false;
    }

    public E next() throws NoSuchElementException {
        if (hasNext()) {
            E next = this.nextIterator.next();
            this.lastReturned = this.nextIterator;
            this.nextIterator = null;
            return next;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        Iterator<? extends E> it = this.lastReturned;
        if (it != null) {
            it.remove();
            this.lastReturned = null;
            return;
        }
        throw new IllegalStateException("No value can be removed at present");
    }
}
