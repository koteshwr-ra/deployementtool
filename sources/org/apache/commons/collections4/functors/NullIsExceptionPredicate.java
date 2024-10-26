package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.Predicate;

public final class NullIsExceptionPredicate<T> implements PredicateDecorator<T>, Serializable {
    private static final long serialVersionUID = 3243449850504576071L;
    private final Predicate<? super T> iPredicate;

    public static <T> Predicate<T> nullIsExceptionPredicate(Predicate<? super T> predicate) {
        if (predicate != null) {
            return new NullIsExceptionPredicate(predicate);
        }
        throw new NullPointerException("Predicate must not be null");
    }

    public NullIsExceptionPredicate(Predicate<? super T> predicate) {
        this.iPredicate = predicate;
    }

    public boolean evaluate(T t) {
        if (t != null) {
            return this.iPredicate.evaluate(t);
        }
        throw new FunctorException("Input Object must not be null");
    }

    public Predicate<? super T>[] getPredicates() {
        return new Predicate[]{this.iPredicate};
    }
}
