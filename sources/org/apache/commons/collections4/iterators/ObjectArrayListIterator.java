package org.apache.commons.collections4.iterators;

import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableListIterator;

public class ObjectArrayListIterator<E> extends ObjectArrayIterator<E> implements ResettableListIterator<E> {
    private int lastItemIndex = -1;

    public ObjectArrayListIterator(E... eArr) {
        super(eArr);
    }

    public ObjectArrayListIterator(E[] eArr, int i) {
        super(eArr, i);
    }

    public ObjectArrayListIterator(E[] eArr, int i, int i2) {
        super(eArr, i, i2);
    }

    public boolean hasPrevious() {
        return this.index > getStartIndex();
    }

    public E previous() {
        if (hasPrevious()) {
            int i = this.index - 1;
            this.index = i;
            this.lastItemIndex = i;
            return this.array[this.index];
        }
        throw new NoSuchElementException();
    }

    public E next() {
        if (hasNext()) {
            this.lastItemIndex = this.index;
            E[] eArr = this.array;
            int i = this.index;
            this.index = i + 1;
            return eArr[i];
        }
        throw new NoSuchElementException();
    }

    public int nextIndex() {
        return this.index - getStartIndex();
    }

    public int previousIndex() {
        return (this.index - getStartIndex()) - 1;
    }

    public void add(E e) {
        throw new UnsupportedOperationException("add() method is not supported");
    }

    public void set(E e) {
        if (this.lastItemIndex != -1) {
            this.array[this.lastItemIndex] = e;
            return;
        }
        throw new IllegalStateException("must call next() or previous() before a call to set()");
    }

    public void reset() {
        super.reset();
        this.lastItemIndex = -1;
    }
}
