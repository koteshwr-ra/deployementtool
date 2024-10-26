package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: StarProjectionImpl.kt */
public final class TypeBasedStarProjectionImpl extends TypeProjectionBase {
    private final KotlinType _type;

    public boolean isStarProjection() {
        return true;
    }

    public TypeBasedStarProjectionImpl(KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "_type");
        this._type = kotlinType;
    }

    public Variance getProjectionKind() {
        return Variance.OUT_VARIANCE;
    }

    public KotlinType getType() {
        return this._type;
    }
}
