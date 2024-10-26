package kotlin.reflect.jvm.internal.impl.types;

import com.alibaba.sdk.android.oss.common.OSSHeaders;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;

/* compiled from: TypeWithEnhancement.kt */
public final class FlexibleTypeWithEnhancement extends FlexibleType implements TypeWithEnhancement {
    private final KotlinType enhancement;
    private final FlexibleType origin;

    public FlexibleType getOrigin() {
        return this.origin;
    }

    public KotlinType getEnhancement() {
        return this.enhancement;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FlexibleTypeWithEnhancement(FlexibleType flexibleType, KotlinType kotlinType) {
        super(flexibleType.getLowerBound(), flexibleType.getUpperBound());
        Intrinsics.checkParameterIsNotNull(flexibleType, OSSHeaders.ORIGIN);
        Intrinsics.checkParameterIsNotNull(kotlinType, "enhancement");
        this.origin = flexibleType;
        this.enhancement = kotlinType;
    }

    public UnwrappedType replaceAnnotations(Annotations annotations) {
        Intrinsics.checkParameterIsNotNull(annotations, "newAnnotations");
        return TypeWithEnhancementKt.wrapEnhancement(getOrigin().replaceAnnotations(annotations), getEnhancement());
    }

    public UnwrappedType makeNullableAsSpecified(boolean z) {
        return TypeWithEnhancementKt.wrapEnhancement(getOrigin().makeNullableAsSpecified(z), getEnhancement().unwrap().makeNullableAsSpecified(z));
    }

    public String render(DescriptorRenderer descriptorRenderer, DescriptorRendererOptions descriptorRendererOptions) {
        Intrinsics.checkParameterIsNotNull(descriptorRenderer, "renderer");
        Intrinsics.checkParameterIsNotNull(descriptorRendererOptions, "options");
        if (descriptorRendererOptions.getEnhancedTypes()) {
            return descriptorRenderer.renderType(getEnhancement());
        }
        return getOrigin().render(descriptorRenderer, descriptorRendererOptions);
    }

    public SimpleType getDelegate() {
        return getOrigin().getDelegate();
    }
}
