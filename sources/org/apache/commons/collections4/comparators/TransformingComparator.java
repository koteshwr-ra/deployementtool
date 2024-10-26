package org.apache.commons.collections4.comparators;

import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.collections4.ComparatorUtils;
import org.apache.commons.collections4.Transformer;

public class TransformingComparator<I, O> implements Comparator<I>, Serializable {
    private static final long serialVersionUID = 3456940356043606220L;
    private final Comparator<O> decorated;
    private final Transformer<? super I, ? extends O> transformer;

    public TransformingComparator(Transformer<? super I, ? extends O> transformer2) {
        this(transformer2, ComparatorUtils.NATURAL_COMPARATOR);
    }

    public TransformingComparator(Transformer<? super I, ? extends O> transformer2, Comparator<O> comparator) {
        this.decorated = comparator;
        this.transformer = transformer2;
    }

    public int compare(I i, I i2) {
        return this.decorated.compare(this.transformer.transform(i), this.transformer.transform(i2));
    }

    public int hashCode() {
        Comparator<O> comparator = this.decorated;
        int i = 0;
        int hashCode = (629 + (comparator == null ? 0 : comparator.hashCode())) * 37;
        Transformer<? super I, ? extends O> transformer2 = this.transformer;
        if (transformer2 != null) {
            i = transformer2.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        TransformingComparator transformingComparator = (TransformingComparator) obj;
        Comparator<O> comparator = this.decorated;
        if (comparator != null ? comparator.equals(transformingComparator.decorated) : transformingComparator.decorated == null) {
            Transformer<? super I, ? extends O> transformer2 = this.transformer;
            Transformer<? super I, ? extends O> transformer3 = transformingComparator.transformer;
            if (transformer2 == null) {
                if (transformer3 == null) {
                    return true;
                }
            } else if (transformer2.equals(transformer3)) {
                return true;
            }
        }
        return false;
    }
}
