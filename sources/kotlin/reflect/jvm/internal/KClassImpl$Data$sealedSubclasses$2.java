package kotlin.reflect.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.KClassImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Lkotlin/reflect/jvm/internal/KClassImpl;", "T", "", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: KClassImpl.kt */
final class KClassImpl$Data$sealedSubclasses$2 extends Lambda implements Function0<List<? extends KClassImpl<? extends T>>> {
    final /* synthetic */ KClassImpl.Data this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KClassImpl$Data$sealedSubclasses$2(KClassImpl.Data data) {
        super(0);
        this.this$0 = data;
    }

    public final List<KClassImpl<? extends T>> invoke() {
        Collection<ClassDescriptor> sealedSubclasses = this.this$0.getDescriptor().getSealedSubclasses();
        Intrinsics.checkExpressionValueIsNotNull(sealedSubclasses, "descriptor.sealedSubclasses");
        Collection arrayList = new ArrayList();
        for (ClassDescriptor classDescriptor : sealedSubclasses) {
            if (classDescriptor != null) {
                Class<?> javaClass = UtilKt.toJavaClass(classDescriptor);
                KClassImpl kClassImpl = javaClass != null ? new KClassImpl(javaClass) : null;
                if (kClassImpl != null) {
                    arrayList.add(kClassImpl);
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            }
        }
        return (List) arrayList;
    }
}
