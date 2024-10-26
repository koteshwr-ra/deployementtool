package org.apache.commons.collections4.multiset;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.set.UnmodifiableSet;

public final class UnmodifiableMultiSet<E> extends AbstractMultiSetDecorator<E> implements Unmodifiable {
    private static final long serialVersionUID = 20150611;

    public static <E> MultiSet<E> unmodifiableMultiSet(MultiSet<? extends E> multiSet) {
        if (multiSet instanceof Unmodifiable) {
            return multiSet;
        }
        return new UnmodifiableMultiSet(multiSet);
    }

    private UnmodifiableMultiSet(MultiSet<? extends E> multiSet) {
        super(multiSet);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(decorated());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        setCollection((Collection) objectInputStream.readObject());
    }

    public Iterator<E> iterator() {
        return UnmodifiableIterator.unmodifiableIterator(decorated().iterator());
    }

    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public int setCount(E e, int i) {
        throw new UnsupportedOperationException();
    }

    public int add(E e, int i) {
        throw new UnsupportedOperationException();
    }

    public int remove(Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    public Set<E> uniqueSet() {
        return UnmodifiableSet.unmodifiableSet(decorated().uniqueSet());
    }

    public Set<MultiSet.Entry<E>> entrySet() {
        return UnmodifiableSet.unmodifiableSet(decorated().entrySet());
    }
}
