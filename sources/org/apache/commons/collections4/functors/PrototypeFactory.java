package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FunctorException;

public class PrototypeFactory {
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0045, code lost:
        if ((r7 instanceof java.io.Serializable) != false) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004e, code lost:
        return new org.apache.commons.collections4.functors.PrototypeFactory.PrototypeSerializationFactory((java.io.Serializable) r7, (org.apache.commons.collections4.functors.PrototypeFactory.AnonymousClass1) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0056, code lost:
        throw new java.lang.IllegalArgumentException("The prototype must be cloneable via a public clone method");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r7.getClass().getConstructor(new java.lang.Class[]{r7.getClass()});
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0041, code lost:
        return new org.apache.commons.collections4.functors.InstantiateFactory(r7.getClass(), new java.lang.Class[]{r7.getClass()}, new java.lang.Object[]{r7});
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> org.apache.commons.collections4.Factory<T> prototypeFactory(T r7) {
        /*
            r0 = 0
            if (r7 != 0) goto L_0x0008
            org.apache.commons.collections4.Factory r7 = org.apache.commons.collections4.functors.ConstantFactory.constantFactory(r0)
            return r7
        L_0x0008:
            java.lang.Class r1 = r7.getClass()     // Catch:{ NoSuchMethodException -> 0x001b }
            java.lang.String r2 = "clone"
            r3 = r0
            java.lang.Class[] r3 = (java.lang.Class[]) r3     // Catch:{ NoSuchMethodException -> 0x001b }
            java.lang.reflect.Method r1 = r1.getMethod(r2, r3)     // Catch:{ NoSuchMethodException -> 0x001b }
            org.apache.commons.collections4.functors.PrototypeFactory$PrototypeCloneFactory r2 = new org.apache.commons.collections4.functors.PrototypeFactory$PrototypeCloneFactory     // Catch:{ NoSuchMethodException -> 0x001b }
            r2.<init>(r7, r1)     // Catch:{ NoSuchMethodException -> 0x001b }
            return r2
        L_0x001b:
            java.lang.Class r1 = r7.getClass()     // Catch:{ NoSuchMethodException -> 0x0042 }
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ NoSuchMethodException -> 0x0042 }
            java.lang.Class r4 = r7.getClass()     // Catch:{ NoSuchMethodException -> 0x0042 }
            r5 = 0
            r3[r5] = r4     // Catch:{ NoSuchMethodException -> 0x0042 }
            r1.getConstructor(r3)     // Catch:{ NoSuchMethodException -> 0x0042 }
            org.apache.commons.collections4.functors.InstantiateFactory r1 = new org.apache.commons.collections4.functors.InstantiateFactory     // Catch:{ NoSuchMethodException -> 0x0042 }
            java.lang.Class r3 = r7.getClass()     // Catch:{ NoSuchMethodException -> 0x0042 }
            java.lang.Class[] r4 = new java.lang.Class[r2]     // Catch:{ NoSuchMethodException -> 0x0042 }
            java.lang.Class r6 = r7.getClass()     // Catch:{ NoSuchMethodException -> 0x0042 }
            r4[r5] = r6     // Catch:{ NoSuchMethodException -> 0x0042 }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ NoSuchMethodException -> 0x0042 }
            r2[r5] = r7     // Catch:{ NoSuchMethodException -> 0x0042 }
            r1.<init>(r3, r4, r2)     // Catch:{ NoSuchMethodException -> 0x0042 }
            return r1
        L_0x0042:
            boolean r1 = r7 instanceof java.io.Serializable
            if (r1 == 0) goto L_0x004f
            org.apache.commons.collections4.functors.PrototypeFactory$PrototypeSerializationFactory r1 = new org.apache.commons.collections4.functors.PrototypeFactory$PrototypeSerializationFactory
            java.io.Serializable r7 = (java.io.Serializable) r7
            r1.<init>(r7)
            return r1
        L_0x004f:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "The prototype must be cloneable via a public clone method"
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.functors.PrototypeFactory.prototypeFactory(java.lang.Object):org.apache.commons.collections4.Factory");
    }

    private PrototypeFactory() {
    }

    static class PrototypeCloneFactory<T> implements Factory<T> {
        private transient Method iCloneMethod;
        private final T iPrototype;

        private PrototypeCloneFactory(T t, Method method) {
            this.iPrototype = t;
            this.iCloneMethod = method;
        }

        private void findCloneMethod() {
            try {
                this.iCloneMethod = this.iPrototype.getClass().getMethod("clone", (Class[]) null);
            } catch (NoSuchMethodException unused) {
                throw new IllegalArgumentException("PrototypeCloneFactory: The clone method must exist and be public ");
            }
        }

        public T create() {
            if (this.iCloneMethod == null) {
                findCloneMethod();
            }
            try {
                return this.iCloneMethod.invoke(this.iPrototype, (Object[]) null);
            } catch (IllegalAccessException e) {
                throw new FunctorException("PrototypeCloneFactory: Clone method must be public", e);
            } catch (InvocationTargetException e2) {
                throw new FunctorException("PrototypeCloneFactory: Clone method threw an exception", e2);
            }
        }
    }

    static class PrototypeSerializationFactory<T extends Serializable> implements Factory<T> {
        private final T iPrototype;

        private PrototypeSerializationFactory(T t) {
            this.iPrototype = t;
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(6:13|14|(2:26|27)|28|29|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(9:1|2|3|4|5|6|7|8|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x0050 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0029 */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x004d A[SYNTHETIC, Splitter:B:26:0x004d] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public T create() {
            /*
                r5 = this;
                java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
                r1 = 512(0x200, float:7.175E-43)
                r0.<init>(r1)
                r1 = 0
                java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ ClassNotFoundException -> 0x0040, IOException -> 0x0036, all -> 0x0031 }
                r2.<init>(r0)     // Catch:{ ClassNotFoundException -> 0x0040, IOException -> 0x0036, all -> 0x0031 }
                T r3 = r5.iPrototype     // Catch:{ ClassNotFoundException -> 0x0040, IOException -> 0x0036, all -> 0x0031 }
                r2.writeObject(r3)     // Catch:{ ClassNotFoundException -> 0x0040, IOException -> 0x0036, all -> 0x0031 }
                java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ ClassNotFoundException -> 0x0040, IOException -> 0x0036, all -> 0x0031 }
                byte[] r3 = r0.toByteArray()     // Catch:{ ClassNotFoundException -> 0x0040, IOException -> 0x0036, all -> 0x0031 }
                r2.<init>(r3)     // Catch:{ ClassNotFoundException -> 0x0040, IOException -> 0x0036, all -> 0x0031 }
                java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ ClassNotFoundException -> 0x002f, IOException -> 0x002d }
                r1.<init>(r2)     // Catch:{ ClassNotFoundException -> 0x002f, IOException -> 0x002d }
                java.lang.Object r1 = r1.readObject()     // Catch:{ ClassNotFoundException -> 0x002f, IOException -> 0x002d }
                java.io.Serializable r1 = (java.io.Serializable) r1     // Catch:{ ClassNotFoundException -> 0x002f, IOException -> 0x002d }
                r2.close()     // Catch:{ IOException -> 0x0029 }
            L_0x0029:
                r0.close()     // Catch:{ IOException -> 0x002c }
            L_0x002c:
                return r1
            L_0x002d:
                r1 = move-exception
                goto L_0x003a
            L_0x002f:
                r1 = move-exception
                goto L_0x0044
            L_0x0031:
                r2 = move-exception
                r4 = r2
                r2 = r1
                r1 = r4
                goto L_0x004b
            L_0x0036:
                r2 = move-exception
                r4 = r2
                r2 = r1
                r1 = r4
            L_0x003a:
                org.apache.commons.collections4.FunctorException r3 = new org.apache.commons.collections4.FunctorException     // Catch:{ all -> 0x004a }
                r3.<init>((java.lang.Throwable) r1)     // Catch:{ all -> 0x004a }
                throw r3     // Catch:{ all -> 0x004a }
            L_0x0040:
                r2 = move-exception
                r4 = r2
                r2 = r1
                r1 = r4
            L_0x0044:
                org.apache.commons.collections4.FunctorException r3 = new org.apache.commons.collections4.FunctorException     // Catch:{ all -> 0x004a }
                r3.<init>((java.lang.Throwable) r1)     // Catch:{ all -> 0x004a }
                throw r3     // Catch:{ all -> 0x004a }
            L_0x004a:
                r1 = move-exception
            L_0x004b:
                if (r2 == 0) goto L_0x0050
                r2.close()     // Catch:{ IOException -> 0x0050 }
            L_0x0050:
                r0.close()     // Catch:{ IOException -> 0x0053 }
            L_0x0053:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.functors.PrototypeFactory.PrototypeSerializationFactory.create():java.io.Serializable");
        }
    }
}
