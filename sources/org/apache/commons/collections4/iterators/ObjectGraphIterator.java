package org.apache.commons.collections4.iterators;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Transformer;

public class ObjectGraphIterator<E> implements Iterator<E> {
    private Iterator<? extends E> currentIterator;
    private E currentValue;
    private boolean hasNext = false;
    private Iterator<? extends E> lastUsedIterator;
    private E root;
    private final Deque<Iterator<? extends E>> stack = new ArrayDeque(8);
    private final Transformer<? super E, ? extends E> transformer;

    public ObjectGraphIterator(E e, Transformer<? super E, ? extends E> transformer2) {
        if (e instanceof Iterator) {
            this.currentIterator = (Iterator) e;
        } else {
            this.root = e;
        }
        this.transformer = transformer2;
    }

    public ObjectGraphIterator(Iterator<? extends E> it) {
        this.currentIterator = it;
        this.transformer = null;
    }

    /* access modifiers changed from: protected */
    public void updateCurrentIterator() {
        if (!this.hasNext) {
            Iterator<? extends E> it = this.currentIterator;
            if (it == null) {
                E e = this.root;
                if (e != null) {
                    Transformer<? super E, ? extends E> transformer2 = this.transformer;
                    if (transformer2 == null) {
                        findNext(e);
                    } else {
                        findNext(transformer2.transform(e));
                    }
                    this.root = null;
                    return;
                }
                return;
            }
            findNextByIterator(it);
        }
    }

    /* access modifiers changed from: protected */
    public void findNext(E e) {
        if (e instanceof Iterator) {
            findNextByIterator((Iterator) e);
            return;
        }
        this.currentValue = e;
        this.hasNext = true;
    }

    /* access modifiers changed from: protected */
    public void findNextByIterator(Iterator<? extends E> it) {
        Iterator<? extends E> it2 = this.currentIterator;
        if (it != it2) {
            if (it2 != null) {
                this.stack.push(it2);
            }
            this.currentIterator = it;
        }
        while (this.currentIterator.hasNext() && !this.hasNext) {
            Object next = this.currentIterator.next();
            Transformer<? super E, ? extends E> transformer2 = this.transformer;
            if (transformer2 != null) {
                next = transformer2.transform(next);
            }
            findNext(next);
        }
        if (!this.hasNext && !this.stack.isEmpty()) {
            Iterator<? extends E> pop = this.stack.pop();
            this.currentIterator = pop;
            findNextByIterator(pop);
        }
    }

    public boolean hasNext() {
        updateCurrentIterator();
        return this.hasNext;
    }

    public E next() {
        updateCurrentIterator();
        if (this.hasNext) {
            this.lastUsedIterator = this.currentIterator;
            E e = this.currentValue;
            this.currentValue = null;
            this.hasNext = false;
            return e;
        }
        throw new NoSuchElementException("No more elements in the iteration");
    }

    public void remove() {
        Iterator<? extends E> it = this.lastUsedIterator;
        if (it != null) {
            it.remove();
            this.lastUsedIterator = null;
            return;
        }
        throw new IllegalStateException("Iterator remove() cannot be called at this time");
    }
}
