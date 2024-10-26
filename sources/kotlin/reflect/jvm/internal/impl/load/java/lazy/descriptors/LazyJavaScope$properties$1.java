package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import com.limpoxe.support.servicemanager.ServiceProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;

/* compiled from: LazyJavaScope.kt */
final class LazyJavaScope$properties$1 extends Lambda implements Function1<Name, List<? extends PropertyDescriptor>> {
    final /* synthetic */ LazyJavaScope this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LazyJavaScope$properties$1(LazyJavaScope lazyJavaScope) {
        super(1);
        this.this$0 = lazyJavaScope;
    }

    public final List<PropertyDescriptor> invoke(Name name) {
        Intrinsics.checkParameterIsNotNull(name, ServiceProvider.NAME);
        ArrayList arrayList = new ArrayList();
        JavaField findFieldByName = ((DeclaredMemberIndex) this.this$0.getDeclaredMemberIndex().invoke()).findFieldByName(name);
        if (findFieldByName != null && !findFieldByName.isEnumEntry()) {
            arrayList.add(this.this$0.resolveProperty(findFieldByName));
        }
        Collection collection = arrayList;
        this.this$0.computeNonDeclaredProperties(name, collection);
        if (DescriptorUtils.isAnnotationClass(this.this$0.getOwnerDescriptor())) {
            return CollectionsKt.toList(arrayList);
        }
        return CollectionsKt.toList(this.this$0.getC().getComponents().getSignatureEnhancement().enhanceSignatures(this.this$0.getC(), collection));
    }
}
