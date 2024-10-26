package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Predicate;

public final class InstanceofPredicate implements Predicate<Object>, Serializable {
    private static final long serialVersionUID = -6682656911025165584L;
    private final Class<?> iType;

    public static Predicate<Object> instanceOfPredicate(Class<?> cls) {
        if (cls != null) {
            return new InstanceofPredicate(cls);
        }
        throw new NullPointerException("The type to check instanceof must not be null");
    }

    public InstanceofPredicate(Class<?> cls) {
        this.iType = cls;
    }

    public boolean evaluate(Object obj) {
        return this.iType.isInstance(obj);
    }

    public Class<?> getType() {
        return this.iType;
    }
}
