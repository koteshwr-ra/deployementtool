package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

/* compiled from: ErrorType.kt */
public class ErrorType extends SimpleType {
    private final List<TypeProjection> arguments;
    private final TypeConstructor constructor;
    private final boolean isMarkedNullable;
    private final MemberScope memberScope;

    public ErrorType(TypeConstructor typeConstructor, MemberScope memberScope2) {
        this(typeConstructor, memberScope2, (List) null, false, 12, (DefaultConstructorMarker) null);
    }

    public TypeConstructor getConstructor() {
        return this.constructor;
    }

    public MemberScope getMemberScope() {
        return this.memberScope;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ErrorType(TypeConstructor typeConstructor, MemberScope memberScope2, List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeConstructor, memberScope2, (i & 4) != 0 ? CollectionsKt.emptyList() : list, (i & 8) != 0 ? false : z);
    }

    public List<TypeProjection> getArguments() {
        return this.arguments;
    }

    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    public ErrorType(TypeConstructor typeConstructor, MemberScope memberScope2, List<? extends TypeProjection> list, boolean z) {
        Intrinsics.checkParameterIsNotNull(typeConstructor, "constructor");
        Intrinsics.checkParameterIsNotNull(memberScope2, "memberScope");
        Intrinsics.checkParameterIsNotNull(list, "arguments");
        this.constructor = typeConstructor;
        this.memberScope = memberScope2;
        this.arguments = list;
        this.isMarkedNullable = z;
    }

    public Annotations getAnnotations() {
        return Annotations.Companion.getEMPTY();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getConstructor().toString());
        sb.append(getArguments().isEmpty() ? "" : CollectionsKt.joinToString(getArguments(), ", ", "<", ">", -1, "...", (Function1) null));
        return sb.toString();
    }

    public SimpleType replaceAnnotations(Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(annotations, "newAnnotations");
        return this;
    }

    public SimpleType makeNullableAsSpecified(boolean z) {
        return new ErrorType(getConstructor(), getMemberScope(), getArguments(), z);
    }
}
