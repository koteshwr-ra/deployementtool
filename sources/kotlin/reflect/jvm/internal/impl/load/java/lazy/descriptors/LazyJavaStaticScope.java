package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import com.limpoxe.support.servicemanager.ServiceProvider;
import com.limpoxe.support.servicemanager.util.ParamUtil;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* compiled from: LazyJavaStaticScope.kt */
public abstract class LazyJavaStaticScope extends LazyJavaScope {
    /* access modifiers changed from: protected */
    public void computeNonDeclaredProperties(Name name, Collection<PropertyDescriptor> collection) {
        Intrinsics.checkParameterIsNotNull(name, ServiceProvider.NAME);
        Intrinsics.checkParameterIsNotNull(collection, ParamUtil.result);
    }

    /* access modifiers changed from: protected */
    public ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LazyJavaStaticScope(LazyJavaResolverContext lazyJavaResolverContext) {
        super(lazyJavaResolverContext);
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "c");
    }

    /* access modifiers changed from: protected */
    public LazyJavaScope.MethodSignatureData resolveMethodSignature(JavaMethod javaMethod, List<? extends TypeParameterDescriptor> list, KotlinType kotlinType, List<? extends ValueParameterDescriptor> list2) {
        Intrinsics.checkParameterIsNotNull(javaMethod, "method");
        Intrinsics.checkParameterIsNotNull(list, "methodTypeParameters");
        Intrinsics.checkParameterIsNotNull(kotlinType, "returnType");
        Intrinsics.checkParameterIsNotNull(list2, "valueParameters");
        return new LazyJavaScope.MethodSignatureData(kotlinType, (KotlinType) null, list2, list, false, CollectionsKt.emptyList());
    }
}
