package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope;

/* compiled from: IntersectionTypeConstructor.kt */
public final class IntersectionTypeConstructor implements TypeConstructor {
    private final int hashCode;
    private final LinkedHashSet<KotlinType> intersectedTypes;

    public ClassifierDescriptor getDeclarationDescriptor() {
        return null;
    }

    public boolean isDenotable() {
        return false;
    }

    public IntersectionTypeConstructor(Collection<? extends KotlinType> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "typesToIntersect");
        boolean z = !collection.isEmpty();
        if (!_Assertions.ENABLED || z) {
            LinkedHashSet<KotlinType> linkedHashSet = new LinkedHashSet<>(collection);
            this.intersectedTypes = linkedHashSet;
            this.hashCode = linkedHashSet.hashCode();
            return;
        }
        throw new AssertionError("Attempt to create an empty intersection");
    }

    public List<TypeParameterDescriptor> getParameters() {
        return CollectionsKt.emptyList();
    }

    public Collection<KotlinType> getSupertypes() {
        return this.intersectedTypes;
    }

    public final MemberScope createScopeForKotlinType() {
        TypeIntersectionScope.Companion companion = TypeIntersectionScope.Companion;
        return companion.create("member scope for intersection type " + this, this.intersectedTypes);
    }

    public KotlinBuiltIns getBuiltIns() {
        KotlinBuiltIns builtIns = ((KotlinType) this.intersectedTypes.iterator().next()).getConstructor().getBuiltIns();
        Intrinsics.checkExpressionValueIsNotNull(builtIns, "intersectedTypes.iterato…xt().constructor.builtIns");
        return builtIns;
    }

    public String toString() {
        return makeDebugNameForIntersectionType(this.intersectedTypes);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IntersectionTypeConstructor)) {
            return false;
        }
        return Intrinsics.areEqual((Object) this.intersectedTypes, (Object) ((IntersectionTypeConstructor) obj).intersectedTypes);
    }

    public int hashCode() {
        return this.hashCode;
    }

    private final String makeDebugNameForIntersectionType(Iterable<? extends KotlinType> iterable) {
        return CollectionsKt.joinToString$default(CollectionsKt.sortedWith(iterable, new IntersectionTypeConstructor$makeDebugNameForIntersectionType$$inlined$sortedBy$1()), " & ", "{", "}", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
    }
}
