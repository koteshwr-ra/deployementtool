package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import org.apache.commons.collections4.Transformer;

public class TransformIterator<I, O> implements Iterator<O> {
    private Iterator<? extends I> iterator;
    private Transformer<? super I, ? extends O> transformer;

    public TransformIterator() {
    }

    public TransformIterator(Iterator<? extends I> it) {
        this.iterator = it;
    }

    public TransformIterator(Iterator<? extends I> it, Transformer<? super I, ? extends O> transformer2) {
        this.iterator = it;
        this.transformer = transformer2;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public O next() {
        return transform(this.iterator.next());
    }

    public void remove() {
        this.iterator.remove();
    }

    public Iterator<? extends I> getIterator() {
        return this.iterator;
    }

    public void setIterator(Iterator<? extends I> it) {
        this.iterator = it;
    }

    public Transformer<? super I, ? extends O> getTransformer() {
        return this.transformer;
    }

    public void setTransformer(Transformer<? super I, ? extends O> transformer2) {
        this.transformer = transformer2;
    }

    /* access modifiers changed from: protected */
    public O transform(I i) {
        return this.transformer.transform(i);
    }
}
