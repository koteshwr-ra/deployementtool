package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Predicate;

public final class NullPredicate<T> implements Predicate<T>, Serializable {
    public static final Predicate INSTANCE = new NullPredicate();
    private static final long serialVersionUID = 7533784454832764388L;

    public boolean evaluate(T t) {
        return t == null;
    }

    public static <T> Predicate<T> nullPredicate() {
        return INSTANCE;
    }

    private NullPredicate() {
    }

    private Object readResolve() {
        return INSTANCE;
    }
}
