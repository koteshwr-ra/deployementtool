package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

/* compiled from: NewCapturedType.kt */
public final class NewCapturedTypeConstructor implements CapturedTypeConstructor {
    private final TypeProjection projection;
    private List<? extends UnwrappedType> supertypes;

    public ClassifierDescriptor getDeclarationDescriptor() {
        return null;
    }

    public boolean isDenotable() {
        return false;
    }

    public NewCapturedTypeConstructor(TypeProjection typeProjection, List<? extends UnwrappedType> list) {
        Intrinsics.checkParameterIsNotNull(typeProjection, "projection");
        this.projection = typeProjection;
        this.supertypes = list;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NewCapturedTypeConstructor(TypeProjection typeProjection, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeProjection, (i & 2) != 0 ? null : list);
    }

    public TypeProjection getProjection() {
        return this.projection;
    }

    public final void initializeSupertypes(List<? extends UnwrappedType> list) {
        Intrinsics.checkParameterIsNotNull(list, "supertypes");
        boolean z = this.supertypes == null;
        if (!_Assertions.ENABLED || z) {
            this.supertypes = list;
            return;
        }
        throw new AssertionError("Already initialized! oldValue = " + this.supertypes + ", newValue = " + list);
    }

    public List<UnwrappedType> getSupertypes() {
        List<? extends UnwrappedType> list = this.supertypes;
        return list != null ? list : CollectionsKt.emptyList();
    }

    public List<TypeParameterDescriptor> getParameters() {
        return CollectionsKt.emptyList();
    }

    public KotlinBuiltIns getBuiltIns() {
        KotlinType type = getProjection().getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "projection.type");
        return TypeUtilsKt.getBuiltIns(type);
    }

    public String toString() {
        return "CapturedType(" + getProjection() + ')';
    }
}
