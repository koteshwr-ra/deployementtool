package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;

/* compiled from: typeParameterUtils.kt */
public final class PossiblyInnerType {
    private final List<TypeProjection> arguments;
    private final ClassifierDescriptorWithTypeParameters classifierDescriptor;
    private final PossiblyInnerType outerType;

    public PossiblyInnerType(ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters, List<? extends TypeProjection> list, PossiblyInnerType possiblyInnerType) {
        Intrinsics.checkParameterIsNotNull(classifierDescriptorWithTypeParameters, "classifierDescriptor");
        Intrinsics.checkParameterIsNotNull(list, "arguments");
        this.classifierDescriptor = classifierDescriptorWithTypeParameters;
        this.arguments = list;
        this.outerType = possiblyInnerType;
    }

    public final ClassifierDescriptorWithTypeParameters getClassifierDescriptor() {
        return this.classifierDescriptor;
    }

    public final List<TypeProjection> getArguments() {
        return this.arguments;
    }

    public final PossiblyInnerType getOuterType() {
        return this.outerType;
    }
}
