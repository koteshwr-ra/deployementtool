package kotlin.reflect.jvm.internal.impl.load.kotlin;

import com.limpoxe.support.servicemanager.ServiceProvider;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.AnnotationValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ClassLiteralValue;

/* compiled from: BinaryClassAnnotationAndConstantLoaderImpl.kt */
public final class BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1$visitAnnotation$1 implements KotlinJvmBinaryClass.AnnotationArgumentVisitor {
    private final /* synthetic */ KotlinJvmBinaryClass.AnnotationArgumentVisitor $$delegate_0;
    final /* synthetic */ ArrayList $list;
    final /* synthetic */ Name $name;
    final /* synthetic */ KotlinJvmBinaryClass.AnnotationArgumentVisitor $visitor;
    final /* synthetic */ BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1 this$0;

    public void visit(Name name, Object obj) {
        this.$$delegate_0.visit(name, obj);
    }

    public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(Name name, ClassId classId) {
        Intrinsics.checkParameterIsNotNull(name, ServiceProvider.NAME);
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        return this.$$delegate_0.visitAnnotation(name, classId);
    }

    public KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor visitArray(Name name) {
        Intrinsics.checkParameterIsNotNull(name, ServiceProvider.NAME);
        return this.$$delegate_0.visitArray(name);
    }

    public void visitClassLiteral(Name name, ClassLiteralValue classLiteralValue) {
        Intrinsics.checkParameterIsNotNull(name, ServiceProvider.NAME);
        Intrinsics.checkParameterIsNotNull(classLiteralValue, "value");
        this.$$delegate_0.visitClassLiteral(name, classLiteralValue);
    }

    public void visitEnum(Name name, ClassId classId, Name name2) {
        Intrinsics.checkParameterIsNotNull(name, ServiceProvider.NAME);
        Intrinsics.checkParameterIsNotNull(classId, "enumClassId");
        Intrinsics.checkParameterIsNotNull(name2, "enumEntryName");
        this.$$delegate_0.visitEnum(name, classId, name2);
    }

    BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1$visitAnnotation$1(BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1 binaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1, KotlinJvmBinaryClass.AnnotationArgumentVisitor annotationArgumentVisitor, Name name, ArrayList arrayList) {
        this.this$0 = binaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1;
        this.$visitor = annotationArgumentVisitor;
        this.$name = name;
        this.$list = arrayList;
        this.$$delegate_0 = annotationArgumentVisitor;
    }

    public void visitEnd() {
        this.$visitor.visitEnd();
        this.this$0.arguments.put(this.$name, new AnnotationValue((AnnotationDescriptor) CollectionsKt.single(this.$list)));
    }
}
