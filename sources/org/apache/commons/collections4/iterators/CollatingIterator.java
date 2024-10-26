package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.list.UnmodifiableList;

public class CollatingIterator<E> implements Iterator<E> {
    private Comparator<? super E> comparator;
    private List<Iterator<? extends E>> iterators;
    private int lastReturned;
    private BitSet valueSet;
    private List<E> values;

    public CollatingIterator() {
        this((Comparator) null, 2);
    }

    public CollatingIterator(Comparator<? super E> comparator2) {
        this(comparator2, 2);
    }

    public CollatingIterator(Comparator<? super E> comparator2, int i) {
        this.comparator = null;
        this.iterators = null;
        this.values = null;
        this.valueSet = null;
        this.lastReturned = -1;
        this.iterators = new ArrayList(i);
        setComparator(comparator2);
    }

    public CollatingIterator(Comparator<? super E> comparator2, Iterator<? extends E> it, Iterator<? extends E> it2) {
        this(comparator2, 2);
        addIterator(it);
        addIterator(it2);
    }

    public CollatingIterator(Comparator<? super E> comparator2, Iterator<? extends E>[] itArr) {
        this(comparator2, itArr.length);
        for (Iterator<? extends E> addIterator : itArr) {
            addIterator(addIterator);
        }
    }

    public CollatingIterator(Comparator<? super E> comparator2, Collection<Iterator<? extends E>> collection) {
        this(comparator2, collection.size());
        for (Iterator<? extends E> addIterator : collection) {
            addIterator(addIterator);
        }
    }

    public void addIterator(Iterator<? extends E> it) {
        checkNotStarted();
        if (it != null) {
            this.iterators.add(it);
            return;
        }
        throw new NullPointerException("Iterator must not be null");
    }

    public void setIterator(int i, Iterator<? extends E> it) {
        checkNotStarted();
        if (it != null) {
            this.iterators.set(i, it);
            return;
        }
        throw new NullPointerException("Iterator must not be null");
    }

    public List<Iterator<? extends E>> getIterators() {
        return UnmodifiableList.unmodifiableList(this.iterators);
    }

    public Comparator<? super E> getComparator() {
        return this.comparator;
    }

    public void setComparator(Comparator<? super E> comparator2) {
        checkNotStarted();
        this.comparator = comparator2;
    }

    public boolean hasNext() {
        start();
        return anyValueSet(this.valueSet) || anyHasNext(this.iterators);
    }

    public E next() throws NoSuchElementException {
        if (hasNext()) {
            int least = least();
            if (least != -1) {
                E e = this.values.get(least);
                clear(least);
                this.lastReturned = least;
                return e;
            }
            throw new NoSuchElementException();
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        int i = this.lastReturned;
        if (i != -1) {
            this.iterators.get(i).remove();
            return;
        }
        throw new IllegalStateException("No value can be removed at present");
    }

    public int getIteratorIndex() {
        int i = this.lastReturned;
        if (i != -1) {
            return i;
        }
        throw new IllegalStateException("No value has been returned yet");
    }

    private void start() {
        if (this.values == null) {
            this.values = new ArrayList(this.iterators.size());
            this.valueSet = new BitSet(this.iterators.size());
            for (int i = 0; i < this.iterators.size(); i++) {
                this.values.add((Object) null);
                this.valueSet.clear(i);
            }
        }
    }

    private boolean set(int i) {
        Iterator it = this.iterators.get(i);
        if (it.hasNext()) {
            this.values.set(i, it.next());
            this.valueSet.set(i);
            return true;
        }
        this.values.set(i, (Object) null);
        this.valueSet.clear(i);
        return false;
    }

    private void clear(int i) {
        this.values.set(i, (Object) null);
        this.valueSet.clear(i);
    }

    private void checkNotStarted() throws IllegalStateException {
        if (this.values != null) {
            throw new IllegalStateException("Can't do that after next or hasNext has been called.");
        }
    }

    private int least() {
        E e = null;
        int i = -1;
        for (int i2 = 0; i2 < this.values.size(); i2++) {
            if (!this.valueSet.get(i2)) {
                set(i2);
            }
            if (this.valueSet.get(i2)) {
                if (i == -1) {
                    e = this.values.get(i2);
                    i = i2;
                } else {
                    E e2 = this.values.get(i2);
                    Comparator<? super E> comparator2 = this.comparator;
                    if (comparator2 == null) {
                        throw new NullPointerException("You must invoke setComparator() to set a comparator first.");
                    } else if (comparator2.compare(e2, e) < 0) {
                        i = i2;
                        e = e2;
                    }
                }
            }
        }
        return i;
    }

    private boolean anyValueSet(BitSet bitSet) {
        for (int i = 0; i < bitSet.size(); i++) {
            if (bitSet.get(i)) {
                return true;
            }
        }
        return false;
    }

    private boolean anyHasNext(List<Iterator<? extends E>> list) {
        for (Iterator<? extends E> hasNext : list) {
            if (hasNext.hasNext()) {
                return true;
            }
        }
        return false;
    }
}
