package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.limpoxe.support.servicemanager.ServiceProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* compiled from: TypeIntersectionScope.kt */
public final class TypeIntersectionScope extends AbstractScopeAdapter {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final ChainedMemberScope workerScope;

    @JvmStatic
    public static final MemberScope create(String str, Collection<? extends KotlinType> collection) {
        return Companion.create(str, collection);
    }

    private TypeIntersectionScope(ChainedMemberScope chainedMemberScope) {
        this.workerScope = chainedMemberScope;
    }

    public /* synthetic */ TypeIntersectionScope(ChainedMemberScope chainedMemberScope, DefaultConstructorMarker defaultConstructorMarker) {
        this(chainedMemberScope);
    }

    /* access modifiers changed from: protected */
    public ChainedMemberScope getWorkerScope() {
        return this.workerScope;
    }

    public Collection<SimpleFunctionDescriptor> getContributedFunctions(Name name, LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, ServiceProvider.NAME);
        Intrinsics.checkParameterIsNotNull(lookupLocation, RequestParameters.SUBRESOURCE_LOCATION);
        return OverridingUtilsKt.selectMostSpecificInEachOverridableGroup(super.getContributedFunctions(name, lookupLocation), TypeIntersectionScope$getContributedFunctions$1.INSTANCE);
    }

    public Collection<PropertyDescriptor> getContributedVariables(Name name, LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, ServiceProvider.NAME);
        Intrinsics.checkParameterIsNotNull(lookupLocation, RequestParameters.SUBRESOURCE_LOCATION);
        return OverridingUtilsKt.selectMostSpecificInEachOverridableGroup(super.getContributedVariables(name, lookupLocation), TypeIntersectionScope$getContributedVariables$1.INSTANCE);
    }

    public Collection<DeclarationDescriptor> getContributedDescriptors(DescriptorKindFilter descriptorKindFilter, Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object next : super.getContributedDescriptors(descriptorKindFilter, function1)) {
            if (((DeclarationDescriptor) next) instanceof CallableDescriptor) {
                arrayList.add(next);
            } else {
                arrayList2.add(next);
            }
        }
        Pair pair = new Pair(arrayList, arrayList2);
        List list = (List) pair.component1();
        List list2 = (List) pair.component2();
        if (list != null) {
            return CollectionsKt.plus(OverridingUtilsKt.selectMostSpecificInEachOverridableGroup(list, TypeIntersectionScope$getContributedDescriptors$2.INSTANCE), list2);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Collection<org.jetbrains.kotlin.descriptors.CallableDescriptor>");
    }

    /* compiled from: TypeIntersectionScope.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final MemberScope create(String str, Collection<? extends KotlinType> collection) {
            Intrinsics.checkParameterIsNotNull(str, "message");
            Intrinsics.checkParameterIsNotNull(collection, "types");
            Iterable<KotlinType> iterable = collection;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (KotlinType memberScope : iterable) {
                arrayList.add(memberScope.getMemberScope());
            }
            ChainedMemberScope chainedMemberScope = new ChainedMemberScope(str, (List) arrayList);
            if (collection.size() <= 1) {
                return chainedMemberScope;
            }
            return new TypeIntersectionScope(chainedMemberScope, (DefaultConstructorMarker) null);
        }
    }
}
