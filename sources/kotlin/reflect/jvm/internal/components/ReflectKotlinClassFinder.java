package kotlin.reflect.jvm.internal.components;

import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins.BuiltInSerializerProtocol;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lkotlin/reflect/jvm/internal/components/ReflectKotlinClassFinder;", "Lkotlin/reflect/jvm/internal/impl/load/kotlin/KotlinClassFinder;", "classLoader", "Ljava/lang/ClassLoader;", "(Ljava/lang/ClassLoader;)V", "findBuiltInsData", "Ljava/io/InputStream;", "packageFqName", "Lkotlin/reflect/jvm/internal/impl/name/FqName;", "findKotlinClass", "Lkotlin/reflect/jvm/internal/impl/load/kotlin/KotlinClassFinder$Result;", "fqName", "", "findKotlinClassOrContent", "javaClass", "Lkotlin/reflect/jvm/internal/impl/load/java/structure/JavaClass;", "classId", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "findMetadata", "hasMetadataPackage", "", "descriptors.runtime"}, k = 1, mv = {1, 1, 15})
/* compiled from: ReflectKotlinClassFinder.kt */
public final class ReflectKotlinClassFinder implements KotlinClassFinder {
    private final ClassLoader classLoader;

    public ReflectKotlinClassFinder(ClassLoader classLoader2) {
        Intrinsics.checkParameterIsNotNull(classLoader2, "classLoader");
        this.classLoader = classLoader2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r2 = kotlin.reflect.jvm.internal.components.ReflectKotlinClass.Factory.create(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder.Result findKotlinClass(java.lang.String r2) {
        /*
            r1 = this;
            java.lang.ClassLoader r0 = r1.classLoader
            java.lang.Class r2 = kotlin.reflect.jvm.internal.components.ReflectJavaClassFinderKt.tryLoadClass(r0, r2)
            if (r2 == 0) goto L_0x0018
            kotlin.reflect.jvm.internal.components.ReflectKotlinClass$Factory r0 = kotlin.reflect.jvm.internal.components.ReflectKotlinClass.Factory
            kotlin.reflect.jvm.internal.components.ReflectKotlinClass r2 = r0.create(r2)
            if (r2 == 0) goto L_0x0018
            kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass r2 = (kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass) r2
            kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder$Result$KotlinClass r0 = new kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder$Result$KotlinClass
            r0.<init>(r2)
            goto L_0x0019
        L_0x0018:
            r0 = 0
        L_0x0019:
            kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder$Result r0 = (kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder.Result) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.components.ReflectKotlinClassFinder.findKotlinClass(java.lang.String):kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder$Result");
    }

    public KotlinClassFinder.Result findKotlinClassOrContent(ClassId classId) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        return findKotlinClass(ReflectKotlinClassFinderKt.toRuntimeFqName(classId));
    }

    public KotlinClassFinder.Result findKotlinClassOrContent(JavaClass javaClass) {
        String asString;
        Intrinsics.checkParameterIsNotNull(javaClass, "javaClass");
        FqName fqName = javaClass.getFqName();
        if (fqName == null || (asString = fqName.asString()) == null) {
            return null;
        }
        return findKotlinClass(asString);
    }

    public InputStream findBuiltInsData(FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "packageFqName");
        if (!fqName.startsWith(KotlinBuiltIns.BUILT_INS_PACKAGE_NAME)) {
            return null;
        }
        return this.classLoader.getResourceAsStream(BuiltInSerializerProtocol.INSTANCE.getBuiltInsFilePath(fqName));
    }
}
