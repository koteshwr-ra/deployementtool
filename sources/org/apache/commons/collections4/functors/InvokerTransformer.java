package org.apache.commons.collections4.functors;

import java.lang.reflect.InvocationTargetException;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.Transformer;

public class InvokerTransformer<I, O> implements Transformer<I, O> {
    private final Object[] iArgs;
    private final String iMethodName;
    private final Class<?>[] iParamTypes;

    public static <I, O> Transformer<I, O> invokerTransformer(String str) {
        if (str != null) {
            return new InvokerTransformer(str);
        }
        throw new NullPointerException("The method to invoke must not be null");
    }

    public static <I, O> Transformer<I, O> invokerTransformer(String str, Class<?>[] clsArr, Object[] objArr) {
        if (str == null) {
            throw new NullPointerException("The method to invoke must not be null");
        } else if ((clsArr == null && objArr != null) || ((clsArr != null && objArr == null) || (clsArr != null && objArr != null && clsArr.length != objArr.length))) {
            throw new IllegalArgumentException("The parameter types must match the arguments");
        } else if (clsArr == null || clsArr.length == 0) {
            return new InvokerTransformer(str);
        } else {
            return new InvokerTransformer(str, clsArr, objArr);
        }
    }

    private InvokerTransformer(String str) {
        this.iMethodName = str;
        this.iParamTypes = null;
        this.iArgs = null;
    }

    public InvokerTransformer(String str, Class<?>[] clsArr, Object[] objArr) {
        this.iMethodName = str;
        Object[] objArr2 = null;
        this.iParamTypes = clsArr != null ? (Class[]) clsArr.clone() : null;
        this.iArgs = objArr != null ? (Object[]) objArr.clone() : objArr2;
    }

    public O transform(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj.getClass().getMethod(this.iMethodName, this.iParamTypes).invoke(obj, this.iArgs);
        } catch (NoSuchMethodException unused) {
            throw new FunctorException("InvokerTransformer: The method '" + this.iMethodName + "' on '" + obj.getClass() + "' does not exist");
        } catch (IllegalAccessException unused2) {
            throw new FunctorException("InvokerTransformer: The method '" + this.iMethodName + "' on '" + obj.getClass() + "' cannot be accessed");
        } catch (InvocationTargetException e) {
            throw new FunctorException("InvokerTransformer: The method '" + this.iMethodName + "' on '" + obj.getClass() + "' threw an exception", e);
        }
    }
}
