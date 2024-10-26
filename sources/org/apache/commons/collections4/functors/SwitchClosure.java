package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Predicate;

public class SwitchClosure<E> implements Closure<E>, Serializable {
    private static final long serialVersionUID = 3518477308466486130L;
    private final Closure<? super E>[] iClosures;
    private final Closure<? super E> iDefault;
    private final Predicate<? super E>[] iPredicates;

    public static <E> Closure<E> switchClosure(Predicate<? super E>[] predicateArr, Closure<? super E>[] closureArr, Closure<? super E> closure) {
        FunctorUtils.validate((Predicate<?>[]) predicateArr);
        FunctorUtils.validate((Closure<?>[]) closureArr);
        if (predicateArr.length != closureArr.length) {
            throw new IllegalArgumentException("The predicate and closure arrays must be the same size");
        } else if (predicateArr.length == 0) {
            return closure == null ? NOPClosure.nopClosure() : closure;
        } else {
            return new SwitchClosure(predicateArr, closureArr, closure);
        }
    }

    public static <E> Closure<E> switchClosure(Map<Predicate<E>, Closure<E>> map) {
        if (map != null) {
            Closure<E> remove = map.remove((Object) null);
            int size = map.size();
            if (size == 0) {
                return remove == null ? NOPClosure.nopClosure() : remove;
            }
            Closure[] closureArr = new Closure[size];
            Predicate[] predicateArr = new Predicate[size];
            int i = 0;
            for (Map.Entry next : map.entrySet()) {
                predicateArr[i] = (Predicate) next.getKey();
                closureArr[i] = (Closure) next.getValue();
                i++;
            }
            return new SwitchClosure(false, predicateArr, closureArr, remove);
        }
        throw new NullPointerException("The predicate and closure map must not be null");
    }

    private SwitchClosure(boolean z, Predicate<? super E>[] predicateArr, Closure<? super E>[] closureArr, Closure<? super E> closure) {
        this.iPredicates = z ? FunctorUtils.copy((Predicate<? super T>[]) predicateArr) : predicateArr;
        this.iClosures = z ? FunctorUtils.copy(closureArr) : closureArr;
        this.iDefault = closure == null ? NOPClosure.nopClosure() : closure;
    }

    public SwitchClosure(Predicate<? super E>[] predicateArr, Closure<? super E>[] closureArr, Closure<? super E> closure) {
        this(true, predicateArr, closureArr, closure);
    }

    public void execute(E e) {
        int i = 0;
        while (true) {
            Predicate<? super E>[] predicateArr = this.iPredicates;
            if (i >= predicateArr.length) {
                this.iDefault.execute(e);
                return;
            } else if (predicateArr[i].evaluate(e)) {
                this.iClosures[i].execute(e);
                return;
            } else {
                i++;
            }
        }
    }

    public Predicate<? super E>[] getPredicates() {
        return FunctorUtils.copy((Predicate<? super T>[]) this.iPredicates);
    }

    public Closure<? super E>[] getClosures() {
        return FunctorUtils.copy(this.iClosures);
    }

    public Closure<? super E> getDefaultClosure() {
        return this.iDefault;
    }
}
