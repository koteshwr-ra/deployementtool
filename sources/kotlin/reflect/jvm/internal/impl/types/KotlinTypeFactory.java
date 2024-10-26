package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

/* compiled from: KotlinTypeFactory.kt */
public final class KotlinTypeFactory {
    public static final KotlinTypeFactory INSTANCE = new KotlinTypeFactory();

    private KotlinTypeFactory() {
    }

    private final MemberScope computeMemberScope(TypeConstructor typeConstructor, List<? extends TypeProjection> list) {
        ClassifierDescriptor declarationDescriptor = typeConstructor.getDeclarationDescriptor();
        if (declarationDescriptor instanceof TypeParameterDescriptor) {
            return declarationDescriptor.getDefaultType().getMemberScope();
        }
        if (declarationDescriptor instanceof ClassDescriptor) {
            if (list.isEmpty()) {
                return ((ClassDescriptor) declarationDescriptor).getDefaultType().getMemberScope();
            }
            MemberScope memberScope = ((ClassDescriptor) declarationDescriptor).getMemberScope(TypeConstructorSubstitution.Companion.create(typeConstructor, list));
            Intrinsics.checkExpressionValueIsNotNull(memberScope, "descriptor.getMemberScop…(constructor, arguments))");
            return memberScope;
        } else if (declarationDescriptor instanceof TypeAliasDescriptor) {
            MemberScope createErrorScope = ErrorUtils.createErrorScope("Scope for abbreviation: " + ((TypeAliasDescriptor) declarationDescriptor).getName(), true);
            Intrinsics.checkExpressionValueIsNotNull(createErrorScope, "ErrorUtils.createErrorSc…{descriptor.name}\", true)");
            return createErrorScope;
        } else {
            throw new IllegalStateException("Unsupported classifier: " + declarationDescriptor + " for constructor: " + typeConstructor);
        }
    }

    @JvmStatic
    public static final SimpleType simpleType(Annotations annotations, TypeConstructor typeConstructor, List<? extends TypeProjection> list, boolean z) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(typeConstructor, "constructor");
        Intrinsics.checkParameterIsNotNull(list, "arguments");
        if (!annotations.isEmpty() || !list.isEmpty() || z || typeConstructor.getDeclarationDescriptor() == null) {
            return simpleTypeWithNonTrivialMemberScope(annotations, typeConstructor, list, z, INSTANCE.computeMemberScope(typeConstructor, list));
        }
        ClassifierDescriptor declarationDescriptor = typeConstructor.getDeclarationDescriptor();
        if (declarationDescriptor == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(declarationDescriptor, "constructor.declarationDescriptor!!");
        SimpleType defaultType = declarationDescriptor.getDefaultType();
        Intrinsics.checkExpressionValueIsNotNull(defaultType, "constructor.declarationDescriptor!!.defaultType");
        return defaultType;
    }

    @JvmStatic
    public static final SimpleType simpleTypeWithNonTrivialMemberScope(Annotations annotations, TypeConstructor typeConstructor, List<? extends TypeProjection> list, boolean z, MemberScope memberScope) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(typeConstructor, "constructor");
        Intrinsics.checkParameterIsNotNull(list, "arguments");
        Intrinsics.checkParameterIsNotNull(memberScope, "memberScope");
        SimpleTypeImpl simpleTypeImpl = new SimpleTypeImpl(typeConstructor, list, z, memberScope);
        if (annotations.isEmpty()) {
            return simpleTypeImpl;
        }
        return new AnnotatedSimpleType(simpleTypeImpl, annotations);
    }

    @JvmStatic
    public static final SimpleType simpleNotNullType(Annotations annotations, ClassDescriptor classDescriptor, List<? extends TypeProjection> list) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(classDescriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(list, "arguments");
        TypeConstructor typeConstructor = classDescriptor.getTypeConstructor();
        Intrinsics.checkExpressionValueIsNotNull(typeConstructor, "descriptor.typeConstructor");
        return simpleType(annotations, typeConstructor, list, false);
    }

    @JvmStatic
    public static final UnwrappedType flexibleType(SimpleType simpleType, SimpleType simpleType2) {
        Intrinsics.checkParameterIsNotNull(simpleType, "lowerBound");
        Intrinsics.checkParameterIsNotNull(simpleType2, "upperBound");
        if (Intrinsics.areEqual((Object) simpleType, (Object) simpleType2)) {
            return simpleType;
        }
        return new FlexibleTypeImpl(simpleType, simpleType2);
    }

    @JvmStatic
    public static final SimpleType integerLiteralType(Annotations annotations, IntegerLiteralTypeConstructor integerLiteralTypeConstructor, boolean z) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        Intrinsics.checkParameterIsNotNull(integerLiteralTypeConstructor, "constructor");
        List emptyList = CollectionsKt.emptyList();
        MemberScope createErrorScope = ErrorUtils.createErrorScope("Scope for integer literal type", true);
        Intrinsics.checkExpressionValueIsNotNull(createErrorScope, "ErrorUtils.createErrorSc…eger literal type\", true)");
        return simpleTypeWithNonTrivialMemberScope(annotations, integerLiteralTypeConstructor, emptyList, z, createErrorScope);
    }
}
