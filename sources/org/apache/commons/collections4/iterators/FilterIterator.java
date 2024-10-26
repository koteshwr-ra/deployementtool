package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Predicate;

public class FilterIterator<E> implements Iterator<E> {
    private Iterator<? extends E> iterator;
    private E nextObject;
    private boolean nextObjectSet = false;
    private Predicate<? super E> predicate;

    public FilterIterator() {
    }

    public FilterIterator(Iterator<? extends E> it) {
        this.iterator = it;
    }

    public FilterIterator(Iterator<? extends E> it, Predicate<? super E> predicate2) {
        this.iterator = it;
        this.predicate = predicate2;
    }

    public boolean hasNext() {
        return this.nextObjectSet || setNextObject();
    }

    public E next() {
        if (this.nextObjectSet || setNextObject()) {
            this.nextObjectSet = false;
            return this.nextObject;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        if (!this.nextObjectSet) {
            this.iterator.remove();
            return;
        }
        throw new IllegalStateException("remove() cannot be called");
    }

    public Iterator<? extends E> getIterator() {
        return this.iterator;
    }

    public void setIterator(Iterator<? extends E> it) {
        this.iterator = it;
        this.nextObject = null;
        this.nextObjectSet = false;
    }

    public Predicate<? super E> getPredicate() {
        return this.predicate;
    }

    public void setPredicate(Predicate<? super E> predicate2) {
        this.predicate = predicate2;
        this.nextObject = null;
        this.nextObjectSet = false;
    }

    private boolean setNextObject() {
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
}
