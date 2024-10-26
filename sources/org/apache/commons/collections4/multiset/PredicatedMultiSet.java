package org.apache.commons.collections4.multiset;

import java.util.Set;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.collection.PredicatedCollection;

public class PredicatedMultiSet<E> extends PredicatedCollection<E> implements MultiSet<E> {
    private static final long serialVersionUID = 20150629;

    public static <E> PredicatedMultiSet<E> predicatedMultiSet(MultiSet<E> multiSet, Predicate<? super E> predicate) {
        return new PredicatedMultiSet<>(multiSet, predicate);
    }

    protected PredicatedMultiSet(MultiSet<E> multiSet, Predicate<? super E> predicate) {
        super(multiSet, predicate);
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

    public int add(E e, int i) {
        validate(e);
        return decorated().add(e, i);
    }

    public int remove(Object obj, int i) {
        return decorated().remove(obj, i);
    }

    public int getCount(Object obj) {
        return decorated().getCount(obj);
    }

    public int setCount(E e, int i) {
        validate(e);
        return decorated().setCount(e, i);
    }

    public Set<E> uniqueSet() {
        return decorated().uniqueSet();
    }

    public Set<MultiSet.Entry<E>> entrySet() {
        return decorated().entrySet();
    }
}
