package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;

public class SwitchTransformer<I, O> implements Transformer<I, O>, Serializable {
    private static final long serialVersionUID = -6404460890903469332L;
    private final Transformer<? super I, ? extends O> iDefault;
    private final Predicate<? super I>[] iPredicates;
    private final Transformer<? super I, ? extends O>[] iTransformers;

    public static <I, O> Transformer<I, O> switchTransformer(Predicate<? super I>[] predicateArr, Transformer<? super I, ? extends O>[] transformerArr, Transformer<? super I, ? extends O> transformer) {
        FunctorUtils.validate((Predicate<?>[]) predicateArr);
        FunctorUtils.validate((Transformer<?, ?>[]) transformerArr);
        if (predicateArr.length != transformerArr.length) {
            throw new IllegalArgumentException("The predicate and transformer arrays must be the same size");
        } else if (predicateArr.length == 0) {
            return transformer == null ? ConstantTransformer.nullTransformer() : transformer;
        } else {
            return new SwitchTransformer(predicateArr, transformerArr, transformer);
        }
    }

    public static <I, O> Transformer<I, O> switchTransformer(Map<? extends Predicate<? super I>, ? extends Transformer<? super I, ? extends O>> map) {
        if (map == null) {
            throw new NullPointerException("The predicate and transformer map must not be null");
        } else if (map.size() == 0) {
            return ConstantTransformer.nullTransformer();
        } else {
            Transformer<I, O> transformer = (Transformer) map.remove((Object) null);
            int size = map.size();
            if (size == 0) {
                return transformer == null ? ConstantTransformer.nullTransformer() : transformer;
            }
            Transformer[] transformerArr = new Transformer[size];
            Predicate[] predicateArr = new Predicate[size];
            int i = 0;
            for (Map.Entry next : map.entrySet()) {
                predicateArr[i] = (Predicate) next.getKey();
                transformerArr[i] = (Transformer) next.getValue();
                i++;
            }
            return new SwitchTransformer(false, predicateArr, transformerArr, transformer);
        }
    }

    private SwitchTransformer(boolean z, Predicate<? super I>[] predicateArr, Transformer<? super I, ? extends O>[] transformerArr, Transformer<? super I, ? extends O> transformer) {
        this.iPredicates = z ? FunctorUtils.copy((Predicate<? super T>[]) predicateArr) : predicateArr;
        this.iTransformers = z ? FunctorUtils.copy(transformerArr) : transformerArr;
        this.iDefault = transformer == null ? ConstantTransformer.nullTransformer() : transformer;
    }

    public SwitchTransformer(Predicate<? super I>[] predicateArr, Transformer<? super I, ? extends O>[] transformerArr, Transformer<? super I, ? extends O> transformer) {
        this(true, predicateArr, transformerArr, transformer);
    }

    public O transform(I i) {
        int i2 = 0;
        while (true) {
            Predicate<? super I>[] predicateArr = this.iPredicates;
            if (i2 >= predicateArr.length) {
                return this.iDefault.transform(i);
            }
            if (predicateArr[i2].evaluate(i)) {
                return this.iTransformers[i2].transform(i);
            }
            i2++;
        }
    }

    public Predicate<? super I>[] getPredicates() {
        return FunctorUtils.copy((Predicate<? super T>[]) this.iPredicates);
    }

    public Transformer<? super I, ? extends O>[] getTransformers() {
        return FunctorUtils.copy(this.iTransformers);
    }

    public Transformer<? super I, ? extends O> getDefaultTransformer() {
        return this.iDefault;
    }
}
