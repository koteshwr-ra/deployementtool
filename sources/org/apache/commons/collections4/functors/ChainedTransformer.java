package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;

public class ChainedTransformer<T> implements Transformer<T, T>, Serializable {
    private static final long serialVersionUID = 3514945074733160196L;
    private final Transformer<? super T, ? extends T>[] iTransformers;

    public static <T> Transformer<T, T> chainedTransformer(Transformer<? super T, ? extends T>... transformerArr) {
        FunctorUtils.validate((Transformer<?, ?>[]) transformerArr);
        if (transformerArr.length == 0) {
            return NOPTransformer.nopTransformer();
        }
        return new ChainedTransformer(transformerArr);
    }

    public static <T> Transformer<T, T> chainedTransformer(Collection<? extends Transformer<? super T, ? extends T>> collection) {
        if (collection == null) {
            throw new NullPointerException("Transformer collection must not be null");
        } else if (collection.size() == 0) {
            return NOPTransformer.nopTransformer();
        } else {
            Transformer[] transformerArr = (Transformer[]) collection.toArray(new Transformer[collection.size()]);
            FunctorUtils.validate((Transformer<?, ?>[]) transformerArr);
            return new ChainedTransformer(false, transformerArr);
        }
    }

    private ChainedTransformer(boolean z, Transformer<? super T, ? extends T>[] transformerArr) {
        this.iTransformers = z ? FunctorUtils.copy((Transformer<? super I, ? extends O>[]) transformerArr) : transformerArr;
    }

    public ChainedTransformer(Transformer<? super T, ? extends T>... transformerArr) {
        this(true, transformerArr);
    }

    public T transform(T t) {
        for (Transformer transform : this.iTransformers) {
            t = transform.transform(t);
        }
        return t;
    }

    public Transformer<? super T, ? extends T>[] getTransformers() {
        return FunctorUtils.copy((Transformer<? super I, ? extends O>[]) this.iTransformers);
    }
}
