package org.apache.commons.collections4.iterators;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableListIterator;

public class ArrayListIterator<E> extends ArrayIterator<E> implements ResettableListIterator<E> {
    private int lastItemIndex = -1;

    public ArrayListIterator(Object obj) {
        super(obj);
    }

    public ArrayListIterator(Object obj, int i) {
        super(obj, i);
    }

    public ArrayListIterator(Object obj, int i, int i2) {
        super(obj, i, i2);
    }

    public boolean hasPrevious() {
        return this.index > this.startIndex;
    }

    public E previous() {
        if (hasPrevious()) {
            int i = this.index - 1;
            this.index = i;
            this.lastItemIndex = i;
            return Array.get(this.array, this.index);
        }
        throw new NoSuchElementException();
    }

    public E next() {
        if (hasNext()) {
            this.lastItemIndex = this.index;
            Object obj = this.array;
            int i = this.index;
            this.index = i + 1;
            return Array.get(obj, i);
        }
        throw new NoSuchElementException();
    }

    public int nextIndex() {
        return this.index - this.startIndex;
    }

    public int previousIndex() {
        return (this.index - this.startIndex) - 1;
    }

    public void add(Object obj) {
        throw new UnsupportedOperationException("add() method is not supported");
    }

    public void set(Object obj) {
        if (this.lastItemIndex != -1) {
            Array.set(this.array, this.lastItemIndex, obj);
            return;
        }
        throw new IllegalStateException("must call next() or previous() before a call to set()");
    }

    public void reset() {
        super.reset();
        this.lastItemIndex = -1;
    }
}
