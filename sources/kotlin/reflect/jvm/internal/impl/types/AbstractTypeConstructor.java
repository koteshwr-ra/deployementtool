package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

/* compiled from: AbstractTypeConstructor.kt */
public abstract class AbstractTypeConstructor implements TypeConstructor {
    private final NotNullLazyValue<Supertypes> supertypes;

    /* access modifiers changed from: protected */
    public abstract Collection<KotlinType> computeSupertypes();

    /* access modifiers changed from: protected */
    public KotlinType defaultSupertypeIfEmpty() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract SupertypeLoopChecker getSupertypeLoopChecker();

    /* access modifiers changed from: protected */
    public void reportScopesLoopError(KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
    }

    /* access modifiers changed from: protected */
    public void reportSupertypeLoopError(KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
    }

    public AbstractTypeConstructor(StorageManager storageManager) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        this.supertypes = storageManager.createLazyValueWithPostCompute(new AbstractTypeConstructor$supertypes$1(this), AbstractTypeConstructor$supertypes$2.INSTANCE, new AbstractTypeConstructor$supertypes$3(this));
    }

    public List<KotlinType> getSupertypes() {
        return ((Supertypes) this.supertypes.invoke()).getSupertypesWithoutCycles();
    }

    /* compiled from: AbstractTypeConstructor.kt */
    private static final class Supertypes {
        private final Collection<KotlinType> allSupertypes;
        private List<? extends KotlinType> supertypesWithoutCycles = CollectionsKt.listOf(ErrorUtils.ERROR_TYPE_FOR_LOOP_IN_SUPERTYPES);

        public Supertypes(Collection<? extends KotlinType> collection) {
            Intrinsics.checkParameterIsNotNull(collection, "allSupertypes");
            this.allSupertypes = collection;
        }

        public final Collection<KotlinType> getAllSupertypes() {
            return this.allSupertypes;
        }

        public final List<KotlinType> getSupertypesWithoutCycles() {
            return this.supertypesWithoutCycles;
        }

        public final void setSupertypesWithoutCycles(List<? extends KotlinType> list) {
            Intrinsics.checkParameterIsNotNull(list, "<set-?>");
            this.supertypesWithoutCycles = list;
        }
    }

    /* access modifiers changed from: private */
    public final Collection<KotlinType> computeNeighbours(TypeConstructor typeConstructor, boolean z) {
        List<T> plus;
        AbstractTypeConstructor abstractTypeConstructor = (AbstractTypeConstructor) (!(typeConstructor instanceof AbstractTypeConstructor) ? null : typeConstructor);
        if (abstractTypeConstructor != null && (plus = CollectionsKt.plus(((Supertypes) abstractTypeConstructor.supertypes.invoke()).getAllSupertypes(), abstractTypeConstructor.getAdditionalNeighboursInSupertypeGraph(z))) != null) {
            return plus;
        }
        Collection<KotlinType> supertypes2 = typeConstructor.getSupertypes();
        Intrinsics.checkExpressionValueIsNotNull(supertypes2, "supertypes");
        return supertypes2;
    }

    /* access modifiers changed from: protected */
    public Collection<KotlinType> getAdditionalNeighboursInSupertypeGraph(boolean z) {
        return CollectionsKt.emptyList();
    }
}
