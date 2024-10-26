package org.apache.commons.collections4.iterators;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableIterator;

public class ArrayIterator<E> implements ResettableIterator<E> {
    final Object array;
    final int endIndex;
    int index;
    final int startIndex;

    public ArrayIterator(Object obj) {
        this(obj, 0);
    }

    public ArrayIterator(Object obj, int i) {
        this(obj, i, Array.getLength(obj));
    }

    public ArrayIterator(Object obj, int i, int i2) {
        this.index = 0;
        this.array = obj;
        this.startIndex = i;
        this.endIndex = i2;
        this.index = i;
        int length = Array.getLength(obj);
        checkBound(i, length, "start");
        checkBound(i2, length, "end");
        if (i2 < i) {
            throw new IllegalArgumentException("End index must not be less than start index.");
        }
    }

    /* access modifiers changed from: protected */
    public void checkBound(int i, int i2, String str) {
        if (i > i2) {
            throw new ArrayIndexOutOfBoundsException("Attempt to make an ArrayIterator that " + str + "s beyond the end of the array. ");
        } else if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Attempt to make an ArrayIterator that " + str + "s before the start of the array. ");
        }
    }

    public boolean hasNext() {
        return this.index < this.endIndex;
    }

    public E next() {
        if (hasNext()) {
            Object obj = this.array;
            int i = this.index;
            this.index = i + 1;
            return Array.get(obj, i);
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        throw new UnsupportedOperationException("remove() method is not supported");
    }

    public Object getArray() {
        return this.array;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public void reset() {
        this.index = this.startIndex;
    }
}
