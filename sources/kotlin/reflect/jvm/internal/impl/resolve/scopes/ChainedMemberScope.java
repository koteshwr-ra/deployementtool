package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.limpoxe.support.servicemanager.ServiceProvider;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.util.collectionUtils.ScopeUtilsKt;

/* compiled from: ChainedMemberScope.kt */
public final class ChainedMemberScope implements MemberScope {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String debugName;
    private final List<MemberScope> scopes;

    public ChainedMemberScope(String str, List<? extends MemberScope> list) {
        Intrinsics.checkParameterIsNotNull(str, "debugName");
        Intrinsics.checkParameterIsNotNull(list, "scopes");
        this.debugName = str;
        this.scopes = list;
    }

    public ClassifierDescriptor getContributedClassifier(Name name, LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, ServiceProvider.NAME);
        Intrinsics.checkParameterIsNotNull(lookupLocation, RequestParameters.SUBRESOURCE_LOCATION);
        ClassifierDescriptor classifierDescriptor = null;
        for (MemberScope contributedClassifier : this.scopes) {
            ClassifierDescriptor contributedClassifier2 = contributedClassifier.getContributedClassifier(name, lookupLocation);
            if (contributedClassifier2 != null) {
                if (!(contributedClassifier2 instanceof ClassifierDescriptorWithTypeParameters) || !((ClassifierDescriptorWithTypeParameters) contributedClassifier2).isExpect()) {
                    return contributedClassifier2;
                }
                if (classifierDescriptor == null) {
                    classifierDescriptor = contributedClassifier2;
                }
            }
        }
        return classifierDescriptor;
    }

    public Collection<PropertyDescriptor> getContributedVariables(Name name, LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, ServiceProvider.NAME);
        Intrinsics.checkParameterIsNotNull(lookupLocation, RequestParameters.SUBRESOURCE_LOCATION);
        List<MemberScope> list = this.scopes;
        if (list.isEmpty()) {
            return SetsKt.emptySet();
        }
        Collection<T> collection = null;
        for (MemberScope contributedVariables : list) {
            collection = ScopeUtilsKt.concat(collection, contributedVariables.getContributedVariables(name, lookupLocation));
        }
        return collection != null ? collection : SetsKt.emptySet();
    }

    public Collection<SimpleFunctionDescriptor> getContributedFunctions(Name name, LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, ServiceProvider.NAME);
        Intrinsics.checkParameterIsNotNull(lookupLocation, RequestParameters.SUBRESOURCE_LOCATION);
        List<MemberScope> list = this.scopes;
        if (list.isEmpty()) {
            return SetsKt.emptySet();
        }
        Collection<T> collection = null;
        for (MemberScope contributedFunctions : list) {
            collection = ScopeUtilsKt.concat(collection, contributedFunctions.getContributedFunctions(name, lookupLocation));
        }
        return collection != null ? collection : SetsKt.emptySet();
    }

    public Collection<DeclarationDescriptor> getContributedDescriptors(DescriptorKindFilter descriptorKindFilter, Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        List<MemberScope> list = this.scopes;
        if (list.isEmpty()) {
            return SetsKt.emptySet();
        }
        Collection<DeclarationDescriptor> collection = null;
        for (MemberScope contributedDescriptors : list) {
            collection = ScopeUtilsKt.concat(collection, contributedDescriptors.getContributedDescriptors(descriptorKindFilter, function1));
        }
        return collection != null ? collection : SetsKt.emptySet();
    }

    public Set<Name> getFunctionNames() {
        Collection linkedHashSet = new LinkedHashSet();
        for (MemberScope functionNames : this.scopes) {
            CollectionsKt.addAll(linkedHashSet, functionNames.getFunctionNames());
        }
        return (Set) linkedHashSet;
    }

    public Set<Name> getVariableNames() {
        Collection linkedHashSet = new LinkedHashSet();
        for (MemberScope variableNames : this.scopes) {
            CollectionsKt.addAll(linkedHashSet, variableNames.getVariableNames());
        }
        return (Set) linkedHashSet;
    }

    public String toString() {
        return this.debugName;
    }

    /* compiled from: ChainedMemberScope.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final MemberScope create(String str, List<? extends MemberScope> list) {
            Intrinsics.checkParameterIsNotNull(str, "debugName");
            Intrinsics.checkParameterIsNotNull(list, "scopes");
            int size = list.size();
            if (size == 0) {
                return MemberScope.Empty.INSTANCE;
            }
            if (size != 1) {
                return new ChainedMemberScope(str, list);
            }
            return (MemberScope) CollectionsKt.single(list);
        }
    }
}
