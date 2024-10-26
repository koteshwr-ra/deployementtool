package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Ref;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* compiled from: SafeCollector.common.kt */
public final class FlowKt__TransformKt$withIndex$$inlined$unsafeFlow$1 implements Flow<IndexedValue<? extends T>> {
    final /* synthetic */ Flow $this_withIndex$inlined;

    public FlowKt__TransformKt$withIndex$$inlined$unsafeFlow$1(Flow flow) {
        this.$this_withIndex$inlined = flow;
    }

    public Object collect(FlowCollector flowCollector, Continuation continuation) {
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        Object collect = this.$this_withIndex$inlined.collect(new FlowKt__TransformKt$withIndex$$inlined$unsafeFlow$1$lambda$1(flowCollector, intRef), continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
