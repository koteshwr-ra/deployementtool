package org.apache.commons.collections4.iterators;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Predicate;

public class FilterListIterator<E> implements ListIterator<E> {
    private ListIterator<? extends E> iterator;
    private int nextIndex = 0;
    private E nextObject;
    private boolean nextObjectSet = false;
    private Predicate<? super E> predicate;
    private E previousObject;
    private boolean previousObjectSet = false;

    public FilterListIterator() {
    }

    public FilterListIterator(ListIterator<? extends E> listIterator) {
        this.iterator = listIterator;
    }

    public FilterListIterator(ListIterator<? extends E> listIterator, Predicate<? super E> predicate2) {
        this.iterator = listIterator;
        this.predicate = predicate2;
    }

    public FilterListIterator(Predicate<? super E> predicate2) {
        this.predicate = predicate2;
    }

    public void add(E e) {
        throw new UnsupportedOperationException("FilterListIterator.add(Object) is not supported.");
    }

    public boolean hasNext() {
        return this.nextObjectSet || setNextObject();
    }

    public boolean hasPrevious() {
        return this.previousObjectSet || setPreviousObject();
    }

    public E next() {
        if (this.nextObjectSet || setNextObject()) {
            this.nextIndex++;
            E e = this.nextObject;
            clearNextObject();
            return e;
        }
        throw new NoSuchElementException();
    }

    public int nextIndex() {
        return this.nextIndex;
    }

    public E previous() {
        if (this.previousObjectSet || setPreviousObject()) {
            this.nextIndex--;
            E e = this.previousObject;
            clearPreviousObject();
            return e;
        }
        throw new NoSuchElementException();
    }

    public int previousIndex() {
        return this.nextIndex - 1;
    }

    public void remove() {
        throw new UnsupportedOperationException("FilterListIterator.remove() is not supported.");
    }

    public void set(E e) {
        throw new UnsupportedOperationException("FilterListIterator.set(Object) is not supported.");
    }

    public ListIterator<? extends E> getListIterator() {
        return this.iterator;
    }

    public void setListIterator(ListIterator<? extends E> listIterator) {
        this.iterator = listIterator;
    }

    public Predicate<? super E> getPredicate() {
        return this.predicate;
    }

    public void setPredicate(Predicate<? super E> predicate2) {
        this.predicate = predicate2;
    }

    private void clearNextObject() {
        this.nextObject = null;
        this.nextObjectSet = false;
    }

    private boolean setNextObject() {
        if (this.previousObjectSet) {
            clearPreviousObject();
            if (!setNextObject()) {
                return false;
            }
            clearNextObject();
        }
        if (this.iterator == null) {
            return false;
        }
        while (this.iterator.hasNext()) {
            E next = this.iterator.next();
            if (this.predicate.evaluate(next)) {
                this.nextObject = next;
                this.nextObjectSet = true;
                return true;
            }
        }
        return false;
    }

    private void clearPreviousObject() {
        this.previousObject = null;
        this.previousObjectSet = false;
    }

    private boolean setPreviousObject() {
        if (this.nextObjectSet) {
            clearNextObject();
            if (!setPreviousObject()) {
                return false;
            }
            clearPreviousObject();
        }
        if (this.iterator == null) {
            return false;
        }
        while (this.iterator.hasPrevious()) {
            E previous = this.iterator.previous();
            if (this.predicate.evaluate(previous)) {
                this.previousObject = previous;
                this.previousObjectSet = true;
                return true;
            }
        }
        return false;
    }
}
