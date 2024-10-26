package kotlin.reflect.jvm.internal.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationOwner;
import kotlin.reflect.jvm.internal.impl.name.FqName;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\r¨\u0006\u0011"}, d2 = {"Lkotlin/reflect/jvm/internal/structure/ReflectJavaAnnotationOwner;", "Lkotlin/reflect/jvm/internal/impl/load/java/structure/JavaAnnotationOwner;", "annotations", "", "Lkotlin/reflect/jvm/internal/structure/ReflectJavaAnnotation;", "getAnnotations", "()Ljava/util/List;", "element", "Ljava/lang/reflect/AnnotatedElement;", "getElement", "()Ljava/lang/reflect/AnnotatedElement;", "isDeprecatedInJavaDoc", "", "()Z", "findAnnotation", "fqName", "Lkotlin/reflect/jvm/internal/impl/name/FqName;", "descriptors.runtime"}, k = 1, mv = {1, 1, 15})
/* compiled from: ReflectJavaAnnotationOwner.kt */
public interface ReflectJavaAnnotationOwner extends JavaAnnotationOwner {
    AnnotatedElement getElement();

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 15})
    /* compiled from: ReflectJavaAnnotationOwner.kt */
    public static final class DefaultImpls {
        public static boolean isDeprecatedInJavaDoc(ReflectJavaAnnotationOwner reflectJavaAnnotationOwner) {
            return false;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
            r0 = kotlin.reflect.jvm.internal.structure.ReflectJavaAnnotationOwnerKt.getAnnotations((r0 = r0.getDeclaredAnnotations()));
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.util.List<kotlin.reflect.jvm.internal.structure.ReflectJavaAnnotation> getAnnotations(kotlin.reflect.jvm.internal.structure.ReflectJavaAnnotationOwner r0) {
            /*
                java.lang.reflect.AnnotatedElement r0 = r0.getElement()
                if (r0 == 0) goto L_0x0013
                java.lang.annotation.Annotation[] r0 = r0.getDeclaredAnnotations()
                if (r0 == 0) goto L_0x0013
                java.util.List r0 = kotlin.reflect.jvm.internal.structure.ReflectJavaAnnotationOwnerKt.getAnnotations(r0)
                if (r0 == 0) goto L_0x0013
                goto L_0x0017
            L_0x0013:
                java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            L_0x0017:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.structure.ReflectJavaAnnotationOwner.DefaultImpls.getAnnotations(kotlin.reflect.jvm.internal.structure.ReflectJavaAnnotationOwner):java.util.List");
        }

        public static ReflectJavaAnnotation findAnnotation(ReflectJavaAnnotationOwner reflectJavaAnnotationOwner, FqName fqName) {
            Annotation[] declaredAnnotations;
            Intrinsics.checkParameterIsNotNull(fqName, "fqName");
            AnnotatedElement element = reflectJavaAnnotationOwner.getElement();
            if (element == null || (declaredAnnotations = element.getDeclaredAnnotations()) == null) {
                return null;
            }
            return ReflectJavaAnnotationOwnerKt.findAnnotation(declaredAnnotations, fqName);
        }
    }
}
