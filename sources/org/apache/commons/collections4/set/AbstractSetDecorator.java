package org.apache.commons.collections4.set;

import java.util.Set;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;

public abstract class AbstractSetDecorator<E> extends AbstractCollectionDecorator<E> implements Set<E> {
    private static final long serialVersionUID = -4678668309576958546L;

    protected AbstractSetDecorator() {
    }

    protected AbstractSetDecorator(Set<E> set) {
        super(set);
    }

    /* access modifiers changed from: protected */
    public Set<E> decorated() {
        return (Set) super.decorated();
    }

    public boolean equals(Object obj) {
        return obj == this || decorated().equals(obj);
    }

    public int hashCode() {
        return decorated().hashCode();
    }
}
