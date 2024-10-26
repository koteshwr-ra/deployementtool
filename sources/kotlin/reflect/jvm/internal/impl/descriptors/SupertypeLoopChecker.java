package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;

/* compiled from: SupertypeLoopChecker.kt */
public interface SupertypeLoopChecker {
    Collection<KotlinType> findLoopsInSupertypesAndDisconnect(TypeConstructor typeConstructor, Collection<? extends KotlinType> collection, Function1<? super TypeConstructor, ? extends Iterable<? extends KotlinType>> function1, Function1<? super KotlinType, Unit> function12);

    /* compiled from: SupertypeLoopChecker.kt */
    public static final class EMPTY implements SupertypeLoopChecker {
        public static final EMPTY INSTANCE = new EMPTY();

        public Collection<KotlinType> findLoopsInSupertypesAndDisconnect(TypeConstructor typeConstructor, Collection<? extends KotlinType> collection, Function1<? super TypeConstructor, ? extends Iterable<? extends KotlinType>> function1, Function1<? super KotlinType, Unit> function12) {
            Intrinsics.checkParameterIsNotNull(typeConstructor, "currentTypeConstructor");
            Intrinsics.checkParameterIsNotNull(collection, "superTypes");
            Intrinsics.checkParameterIsNotNull(function1, "neighbors");
            Intrinsics.checkParameterIsNotNull(function12, "reportLoop");
            return collection;
        }

        private EMPTY() {
        }
    }
}
