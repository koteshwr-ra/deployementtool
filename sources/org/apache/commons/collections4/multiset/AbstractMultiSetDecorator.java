package org.apache.commons.collections4.multiset;

import java.util.Set;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;

public abstract class AbstractMultiSetDecorator<E> extends AbstractCollectionDecorator<E> implements MultiSet<E> {
    private static final long serialVersionUID = 20150610;

    protected AbstractMultiSetDecorator() {
    }

    protected AbstractMultiSetDecorator(MultiSet<E> multiSet) {
        super(multiSet);
    }

    /* access modifiers changed from: protected */
    public MultiSet<E> decorated() {
        return (MultiSet) super.decorated();
    }

    public boolean equals(Object obj) {
        return obj == this || decorated().equals(obj);
    }

    public int hashCode() {
        return decorated().hashCode();
    }

    public int getCount(Object obj) {
        return decorated().getCount(obj);
    }

    public int setCount(E e, int i) {
        return decorated().setCount(e, i);
    }

    public int add(E e, int i) {
        return decorated().add(e, i);
    }

    public int remove(Object obj, int i) {
        return decorated().remove(obj, i);
    }

    public Set<E> uniqueSet() {
        return decorated().uniqueSet();
    }

    public Set<MultiSet.Entry<E>> entrySet() {
        return decorated().entrySet();
    }
}
