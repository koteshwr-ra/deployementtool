package org.apache.commons.collections4.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractCollectionDecorator<E> implements Collection<E>, Serializable {
    private static final long serialVersionUID = 6249888059822088500L;
    private Collection<E> collection;

    protected AbstractCollectionDecorator() {
    }

    protected AbstractCollectionDecorator(Collection<E> collection2) {
        if (collection2 != null) {
            this.collection = collection2;
            return;
        }
        throw new NullPointerException("Collection must not be null.");
    }

    /* access modifiers changed from: protected */
    public Collection<E> decorated() {
        return this.collection;
    }

    /* access modifiers changed from: protected */
    public void setCollection(Collection<E> collection2) {
        this.collection = collection2;
    }

    public boolean add(E e) {
        return decorated().add(e);
    }

    public boolean addAll(Collection<? extends E> collection2) {
        return decorated().addAll(collection2);
    }

    public void clear() {
        decorated().clear();
    }

    public boolean contains(Object obj) {
        return decorated().contains(obj);
    }

    public boolean isEmpty() {
        return decorated().isEmpty();
    }

    public Iterator<E> iterator() {
        return decorated().iterator();
    }

    public boolean remove(Object obj) {
        return decorated().remove(obj);
    }

    public int size() {
        return decorated().size();
    }

    public Object[] toArray() {
        return decorated().toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return decorated().toArray(tArr);
    }

    public boolean containsAll(Collection<?> collection2) {
        return decorated().containsAll(collection2);
    }

    public boolean removeAll(Collection<?> collection2) {
        return decorated().removeAll(collection2);
    }

    public boolean retainAll(Collection<?> collection2) {
        return decorated().retainAll(collection2);
    }

    public String toString() {
        return decorated().toString();
    }
}
