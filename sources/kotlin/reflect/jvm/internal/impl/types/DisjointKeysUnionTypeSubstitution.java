package kotlin.reflect.jvm.internal.impl.types;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;

/* compiled from: DisjointKeysUnionTypeSubstitution.kt */
public final class DisjointKeysUnionTypeSubstitution extends TypeSubstitution {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final TypeSubstitution first;
    private final TypeSubstitution second;

    @JvmStatic
    public static final TypeSubstitution create(TypeSubstitution typeSubstitution, TypeSubstitution typeSubstitution2) {
        return Companion.create(typeSubstitution, typeSubstitution2);
    }

    public boolean isEmpty() {
        return false;
    }

    public /* synthetic */ DisjointKeysUnionTypeSubstitution(TypeSubstitution typeSubstitution, TypeSubstitution typeSubstitution2, DefaultConstructorMarker defaultConstructorMarker) {
        this(typeSubstitution, typeSubstitution2);
    }

    /* compiled from: DisjointKeysUnionTypeSubstitution.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final TypeSubstitution create(TypeSubstitution typeSubstitution, TypeSubstitution typeSubstitution2) {
            Intrinsics.checkParameterIsNotNull(typeSubstitution, "first");
            Intrinsics.checkParameterIsNotNull(typeSubstitution2, "second");
            if (typeSubstitution.isEmpty()) {
                return typeSubstitution2;
            }
            if (typeSubstitution2.isEmpty()) {
                return typeSubstitution;
            }
            return new DisjointKeysUnionTypeSubstitution(typeSubstitution, typeSubstitution2, (DefaultConstructorMarker) null);
        }
    }

    private DisjointKeysUnionTypeSubstitution(TypeSubstitution typeSubstitution, TypeSubstitution typeSubstitution2) {
        this.first = typeSubstitution;
        this.second = typeSubstitution2;
    }

    public TypeProjection get(KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "key");
        TypeProjection typeProjection = this.first.get(kotlinType);
        return typeProjection != null ? typeProjection : this.second.get(kotlinType);
    }

    public KotlinType prepareTopLevelType(KotlinType kotlinType, Variance variance) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "topLevelType");
        Intrinsics.checkParameterIsNotNull(variance, RequestParameters.POSITION);
        return this.second.prepareTopLevelType(this.first.prepareTopLevelType(kotlinType, variance), variance);
    }

    public boolean approximateCapturedTypes() {
        return this.first.approximateCapturedTypes() || this.second.approximateCapturedTypes();
    }

    public boolean approximateContravariantCapturedTypes() {
        return this.first.approximateContravariantCapturedTypes() || this.second.approximateContravariantCapturedTypes();
    }

    public Annotations filterAnnotations(Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(annotations, "annotations");
        return this.second.filterAnnotations(this.first.filterAnnotations(annotations));
    }
}
