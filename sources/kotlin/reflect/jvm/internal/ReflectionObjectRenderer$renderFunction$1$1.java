package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lkotlin/reflect/jvm/internal/impl/descriptors/ValueParameterDescriptor;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: ReflectionObjectRenderer.kt */
final class ReflectionObjectRenderer$renderFunction$1$1 extends Lambda implements Function1<ValueParameterDescriptor, String> {
    public static final ReflectionObjectRenderer$renderFunction$1$1 INSTANCE = new ReflectionObjectRenderer$renderFunction$1$1();

    ReflectionObjectRenderer$renderFunction$1$1() {
        super(1);
    }

    public final String invoke(ValueParameterDescriptor valueParameterDescriptor) {
        ReflectionObjectRenderer reflectionObjectRenderer = ReflectionObjectRenderer.INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(valueParameterDescriptor, "it");
        KotlinType type = valueParameterDescriptor.getType();
        Intrinsics.checkExpressionValueIsNotNull(type, "it.type");
        return reflectionObjectRenderer.renderType(type);
    }
}
