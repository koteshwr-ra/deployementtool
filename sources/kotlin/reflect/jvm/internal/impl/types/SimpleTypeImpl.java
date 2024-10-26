package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.ErrorUtils;

/* compiled from: KotlinTypeFactory.kt */
final class SimpleTypeImpl extends SimpleType {
    private final List<TypeProjection> arguments;
    private final TypeConstructor constructor;
    private final boolean isMarkedNullable;
    private final MemberScope memberScope;

    public TypeConstructor getConstructor() {
        return this.constructor;
    }

    public List<TypeProjection> getArguments() {
        return this.arguments;
    }

    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    public MemberScope getMemberScope() {
        return this.memberScope;
    }

    public SimpleTypeImpl(TypeConstructor typeConstructor, List<? extends TypeProjection> list, boolean z, MemberScope memberScope2) {
        Intrinsics.checkParameterIsNotNull(typeConstructor, "constructor");
        Intrinsics.checkParameterIsNotNull(list, "arguments");
        Intrinsics.checkParameterIsNotNull(memberScope2, "memberScope");
        this.constructor = typeConstructor;
        this.arguments = list;
        this.isMarkedNullable = z;
        this.memberScope = memberScope2;
        if (getMemberScope() instanceof ErrorUtils.ErrorScope) {
            throw new IllegalStateException("SimpleTypeImpl should not be created for error type: " + getMemberScope() + 10 + getConstructor());
        }
    }

    public Annotations getAnnotations() {
        return Annotations.Companion.getEMPTY();
    }

    public SimpleType replaceAnnotations(Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(annotations, "newAnnotations");
        if (annotations.isEmpty()) {
            return this;
        }
        return new AnnotatedSimpleType(this, annotations);
    }

    public SimpleType makeNullableAsSpecified(boolean z) {
        if (z == isMarkedNullable()) {
            return this;
        }
        if (z) {
            return new NullableSimpleType(this);
        }
        return new NotNullSimpleType(this);
    }
}
