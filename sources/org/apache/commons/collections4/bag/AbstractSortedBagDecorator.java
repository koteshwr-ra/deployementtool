package org.apache.commons.collections4.bag;

import java.util.Comparator;
import org.apache.commons.collections4.SortedBag;

public abstract class AbstractSortedBagDecorator<E> extends AbstractBagDecorator<E> implements SortedBag<E> {
    private static final long serialVersionUID = -8223473624050467718L;

    protected AbstractSortedBagDecorator() {
    }

    protected AbstractSortedBagDecorator(SortedBag<E> sortedBag) {
        super(sortedBag);
    }

    /* access modifiers changed from: protected */
    public SortedBag<E> decorated() {
        return (SortedBag) super.decorated();
    }

    public E first() {
        return decorated().first();
    }

    public E last() {
        return decorated().last();
    }

    public Comparator<? super E> comparator() {
        return decorated().comparator();
    }
}
