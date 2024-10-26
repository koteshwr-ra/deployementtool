package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;

public final class TransformedPredicate<T> implements PredicateDecorator<T>, Serializable {
    private static final long serialVersionUID = -5596090919668315834L;
    private final Predicate<? super T> iPredicate;
    private final Transformer<? super T, ? extends T> iTransformer;

    public static <T> Predicate<T> transformedPredicate(Transformer<? super T, ? extends T> transformer, Predicate<? super T> predicate) {
        if (transformer == null) {
            throw new NullPointerException("The transformer to call must not be null");
        } else if (predicate != null) {
            return new TransformedPredicate(transformer, predicate);
        } else {
            throw new NullPointerException("The predicate to call must not be null");
        }
    }

    public TransformedPredicate(Transformer<? super T, ? extends T> transformer, Predicate<? super T> predicate) {
        this.iTransformer = transformer;
        this.iPredicate = predicate;
    }

    public boolean evaluate(T t) {
        return this.iPredicate.evaluate(this.iTransformer.transform(t));
    }

    public Predicate<? super T>[] getPredicates() {
        return new Predicate[]{this.iPredicate};
    }

    public Transformer<? super T, ? extends T> getTransformer() {
        return this.iTransformer;
    }
}
