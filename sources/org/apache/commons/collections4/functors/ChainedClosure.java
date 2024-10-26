package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Collection;
import org.apache.commons.collections4.Closure;

public class ChainedClosure<E> implements Closure<E>, Serializable {
    private static final long serialVersionUID = -3520677225766901240L;
    private final Closure<? super E>[] iClosures;

    public static <E> Closure<E> chainedClosure(Closure<? super E>... closureArr) {
        FunctorUtils.validate((Closure<?>[]) closureArr);
        if (closureArr.length == 0) {
            return NOPClosure.nopClosure();
        }
        return new ChainedClosure(closureArr);
    }

    public static <E> Closure<E> chainedClosure(Collection<? extends Closure<? super E>> collection) {
        if (collection == null) {
            throw new NullPointerException("Closure collection must not be null");
        } else if (collection.size() == 0) {
            return NOPClosure.nopClosure();
        } else {
            Closure[] closureArr = new Closure[collection.size()];
            int i = 0;
            for (Closure closure : collection) {
                closureArr[i] = closure;
                i++;
            }
            FunctorUtils.validate((Closure<?>[]) closureArr);
            return new ChainedClosure(false, closureArr);
        }
    }

    private ChainedClosure(boolean z, Closure<? super E>... closureArr) {
        this.iClosures = z ? FunctorUtils.copy(closureArr) : closureArr;
    }

    public ChainedClosure(Closure<? super E>... closureArr) {
        this(true, closureArr);
    }

    public void execute(E e) {
        for (Closure<? super E> execute : this.iClosures) {
            execute.execute(e);
        }
    }

    public Closure<? super E>[] getClosures() {
        return FunctorUtils.copy(this.iClosures);
    }
}
