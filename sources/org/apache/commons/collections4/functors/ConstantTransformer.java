package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Transformer;

public class ConstantTransformer<I, O> implements Transformer<I, O>, Serializable {
    public static final Transformer NULL_INSTANCE = new ConstantTransformer((Object) null);
    private static final long serialVersionUID = 6374440726369055124L;
    private final O iConstant;

    public static <I, O> Transformer<I, O> nullTransformer() {
        return NULL_INSTANCE;
    }

    public static <I, O> Transformer<I, O> constantTransformer(O o) {
        if (o == null) {
            return nullTransformer();
        }
        return new ConstantTransformer(o);
    }

    public ConstantTransformer(O o) {
        this.iConstant = o;
    }

    public O transform(I i) {
        return this.iConstant;
    }

    public O getConstant() {
        return this.iConstant;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConstantTransformer)) {
            return false;
        }
        Object constant = ((ConstantTransformer) obj).getConstant();
        if (constant == getConstant()) {
            return true;
        }
        if (constant == null || !constant.equals(getConstant())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        if (getConstant() != null) {
            return -144463148 | getConstant().hashCode();
        }
        return -144463148;
    }
}
