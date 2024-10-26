package org.apache.commons.collections4.iterators;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableListIterator;

public class ListIteratorWrapper<E> implements ResettableListIterator<E> {
    private static final String CANNOT_REMOVE_MESSAGE = "Cannot remove element at index {0}.";
    private static final String UNSUPPORTED_OPERATION_MESSAGE = "ListIteratorWrapper does not support optional operations of ListIterator.";
    private int currentIndex = 0;
    private final Iterator<? extends E> iterator;
    private final List<E> list = new ArrayList();
    private boolean removeState;
    private int wrappedIteratorIndex = 0;

    public ListIteratorWrapper(Iterator<? extends E> it) {
        if (it != null) {
            this.iterator = it;
            return;
        }
        throw new NullPointerException("Iterator must not be null");
    }

    public void add(E e) throws UnsupportedOperationException {
        Iterator<? extends E> it = this.iterator;
        if (it instanceof ListIterator) {
            ((ListIterator) it).add(e);
            return;
        }
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }

    public boolean hasNext() {
        if (this.currentIndex == this.wrappedIteratorIndex || (this.iterator instanceof ListIterator)) {
            return this.iterator.hasNext();
        }
        return true;
    }

    public boolean hasPrevious() {
        Iterator<? extends E> it = this.iterator;
        if (it instanceof ListIterator) {
            return ((ListIterator) it).hasPrevious();
        }
        return this.currentIndex > 0;
    }

    public E next() throws NoSuchElementException {
        Iterator<? extends E> it = this.iterator;
        if (it instanceof ListIterator) {
            return it.next();
        }
        int i = this.currentIndex;
        if (i < this.wrappedIteratorIndex) {
            int i2 = i + 1;
            this.currentIndex = i2;
            return this.list.get(i2 - 1);
        }
        E next = it.next();
        this.list.add(next);
        this.currentIndex++;
        this.wrappedIteratorIndex++;
        this.removeState = true;
        return next;
    }

    public int nextIndex() {
        Iterator<? extends E> it = this.iterator;
        if (it instanceof ListIterator) {
            return ((ListIterator) it).nextIndex();
        }
        return this.currentIndex;
    }

    public E previous() throws NoSuchElementException {
        Iterator<? extends E> it = this.iterator;
        if (it instanceof ListIterator) {
            return ((ListIterator) it).previous();
        }
        int i = this.currentIndex;
        if (i != 0) {
            this.removeState = this.wrappedIteratorIndex == i;
            List<E> list2 = this.list;
            int i2 = this.currentIndex - 1;
            this.currentIndex = i2;
            return list2.get(i2);
        }
        throw new NoSuchElementException();
    }

    public int previousIndex() {
        Iterator<? extends E> it = this.iterator;
        if (it instanceof ListIterator) {
            return ((ListIterator) it).previousIndex();
        }
        return this.currentIndex - 1;
    }

    public void remove() throws UnsupportedOperationException {
        Iterator<? extends E> it = this.iterator;
        if (it instanceof ListIterator) {
            it.remove();
            return;
        }
        int i = this.currentIndex;
        if (i == this.wrappedIteratorIndex) {
            i--;
        }
        if (!this.removeState || this.wrappedIteratorIndex - this.currentIndex > 1) {
            throw new IllegalStateException(MessageFormat.format(CANNOT_REMOVE_MESSAGE, new Object[]{Integer.valueOf(i)}));
        }
        this.iterator.remove();
        this.list.remove(i);
        this.currentIndex = i;
        this.wrappedIteratorIndex--;
        this.removeState = false;
    }

    public void set(E e) throws UnsupportedOperationException {
        Iterator<? extends E> it = this.iterator;
        if (it instanceof ListIterator) {
            ((ListIterator) it).set(e);
            return;
        }
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }

    public void reset() {
        Iterator<? extends E> it = this.iterator;
        if (it instanceof ListIterator) {
            ListIterator listIterator = (ListIterator) it;
            while (listIterator.previousIndex() >= 0) {
                listIterator.previous();
            }
            return;
        }
        this.currentIndex = 0;
    }
}
