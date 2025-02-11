package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;

/* compiled from: SpecialTypes.kt */
public final class AbbreviatedType extends DelegatingSimpleType {
    private final SimpleType abbreviation;
    private final SimpleType delegate;

    public AbbreviatedType(SimpleType simpleType, SimpleType simpleType2) {
        Intrinsics.checkParameterIsNotNull(simpleType, "delegate");
        Intrinsics.checkParameterIsNotNull(simpleType2, "abbreviation");
        this.delegate = simpleType;
        this.abbreviation = simpleType2;
    }

    public final SimpleType getAbbreviation() {
        return this.abbreviation;
    }

    /* access modifiers changed from: protected */
    public SimpleType getDelegate() {
        return this.delegate;
    }

    public final SimpleType getExpandedType() {
        return getDelegate();
    }

    public AbbreviatedType replaceAnnotations(Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(annotations, "newAnnotations");
        return new AbbreviatedType(getDelegate().replaceAnnotations(annotations), this.abbreviation);
    }

    public AbbreviatedType makeNullableAsSpecified(boolean z) {
        return new AbbreviatedType(getDelegate().makeNullableAsSpecified(z), this.abbreviation.makeNullableAsSpecified(z));
    }
}
