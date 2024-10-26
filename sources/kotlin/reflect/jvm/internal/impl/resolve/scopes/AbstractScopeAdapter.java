package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.limpoxe.support.servicemanager.ServiceProvider;
import java.util.Collection;
import java.util.Set;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* compiled from: AbstractScopeAdapter.kt */
public abstract class AbstractScopeAdapter implements MemberScope {
    /* access modifiers changed from: protected */
    public abstract MemberScope getWorkerScope();

    public Collection<SimpleFunctionDescriptor> getContributedFunctions(Name name, LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, ServiceProvider.NAME);
        Intrinsics.checkParameterIsNotNull(lookupLocation, RequestParameters.SUBRESOURCE_LOCATION);
        return getWorkerScope().getContributedFunctions(name, lookupLocation);
    }

    public ClassifierDescriptor getContributedClassifier(Name name, LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, ServiceProvider.NAME);
        Intrinsics.checkParameterIsNotNull(lookupLocation, RequestParameters.SUBRESOURCE_LOCATION);
        return getWorkerScope().getContributedClassifier(name, lookupLocation);
    }

    public Collection<PropertyDescriptor> getContributedVariables(Name name, LookupLocation lookupLocation) {
        Intrinsics.checkParameterIsNotNull(name, ServiceProvider.NAME);
        Intrinsics.checkParameterIsNotNull(lookupLocation, RequestParameters.SUBRESOURCE_LOCATION);
        return getWorkerScope().getContributedVariables(name, lookupLocation);
    }

    public Collection<DeclarationDescriptor> getContributedDescriptors(DescriptorKindFilter descriptorKindFilter, Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(descriptorKindFilter, "kindFilter");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        return getWorkerScope().getContributedDescriptors(descriptorKindFilter, function1);
    }

    public Set<Name> getFunctionNames() {
        return getWorkerScope().getFunctionNames();
    }

    public Set<Name> getVariableNames() {
        return getWorkerScope().getVariableNames();
    }
}
