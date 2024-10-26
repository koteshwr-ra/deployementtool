package kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins;

/* compiled from: BuiltInsResourceLoader.kt */
public final class BuiltInsResourceLoader {
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000f, code lost:
        r0 = r0.getResourceAsStream(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.io.InputStream loadResource(java.lang.String r2) {
        /*
            r1 = this;
            java.lang.String r0 = "path"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            java.lang.Class r0 = r1.getClass()
            java.lang.ClassLoader r0 = r0.getClassLoader()
            if (r0 == 0) goto L_0x0016
            java.io.InputStream r0 = r0.getResourceAsStream(r2)
            if (r0 == 0) goto L_0x0016
            goto L_0x001a
        L_0x0016:
            java.io.InputStream r0 = java.lang.ClassLoader.getSystemResourceAsStream(r2)
        L_0x001a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins.BuiltInsResourceLoader.loadResource(java.lang.String):java.io.InputStream");
    }
}
