package org.apache.commons.collections4.functors;

import java.lang.reflect.InvocationTargetException;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.Transformer;

public class InstantiateTransformer<T> implements Transformer<Class<? extends T>, T> {
    private static final Transformer NO_ARG_INSTANCE = new InstantiateTransformer();
    private final Object[] iArgs;
    private final Class<?>[] iParamTypes;

    public static <T> Transformer<Class<? extends T>, T> instantiateTransformer() {
        return NO_ARG_INSTANCE;
    }

    public static <T> Transformer<Class<? extends T>, T> instantiateTransformer(Class<?>[] clsArr, Object[] objArr) {
        if ((clsArr == null && objArr != null) || ((clsArr != null && objArr == null) || (clsArr != null && objArr != null && clsArr.length != objArr.length))) {
            throw new IllegalArgumentException("Parameter types must match the arguments");
        } else if (clsArr == null || clsArr.length == 0) {
            return new InstantiateTransformer();
        } else {
            return new InstantiateTransformer(clsArr, objArr);
        }
    }

    private InstantiateTransformer() {
        this.iParamTypes = null;
        this.iArgs = null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public InstantiateTransformer(java.lang.Class<?>[] r2, java.lang.Object[] r3) {
        /*
            r1 = this;
            r1.<init>()
            r0 = 0
            if (r2 == 0) goto L_0x000d
            java.lang.Object r2 = r2.clone()
            java.lang.Class[] r2 = (java.lang.Class[]) r2
            goto L_0x000e
        L_0x000d:
            r2 = r0
        L_0x000e:
            r1.iParamTypes = r2
            if (r3 == 0) goto L_0x0019
            java.lang.Object r2 = r3.clone()
            r0 = r2
            java.lang.Object[] r0 = (java.lang.Object[]) r0
        L_0x0019:
            r1.iArgs = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.functors.InstantiateTransformer.<init>(java.lang.Class[], java.lang.Object[]):void");
    }

    public T transform(Class<? extends T> cls) {
        if (cls != null) {
            try {
                return cls.getConstructor(this.iParamTypes).newInstance(this.iArgs);
            } catch (NoSuchMethodException unused) {
                throw new FunctorException("InstantiateTransformer: The constructor must exist and be public ");
            } catch (InstantiationException e) {
                throw new FunctorException("InstantiateTransformer: InstantiationException", e);
            } catch (IllegalAccessException e2) {
                throw new FunctorException("InstantiateTransformer: Constructor must be public", e2);
            } catch (InvocationTargetException e3) {
                throw new FunctorException("InstantiateTransformer: Constructor threw an exception", e3);
            }
        } else {
            throw new FunctorException("InstantiateTransformer: Input object was not an instanceof Class, it was a null object");
        }
    }
}
