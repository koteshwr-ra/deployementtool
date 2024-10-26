package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Closure;

public class ForClosure<E> implements Closure<E> {
    private final Closure<? super E> iClosure;
    private final int iCount;

    public static <E> Closure<E> forClosure(int i, Closure<? super E> closure) {
        if (i <= 0 || closure == null) {
            return NOPClosure.nopClosure();
        }
        if (i == 1) {
            return closure;
        }
        return new ForClosure(i, closure);
    }

    public ForClosure(int i, Closure<? super E> closure) {
        this.iCount = i;
        this.iClosure = closure;
    }

    public void execute(E e) {
        for (int i = 0; i < this.iCount; i++) {
            this.iClosure.execute(e);
        }
    }

    public Closure<? super E> getClosure() {
        return this.iClosure;
    }

    public int getCount() {
        return this.iCount;
    }
}
