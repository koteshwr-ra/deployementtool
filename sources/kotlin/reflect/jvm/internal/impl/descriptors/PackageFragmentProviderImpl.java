package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.sequences.SequencesKt;

/* compiled from: PackageFragmentProviderImpl.kt */
public final class PackageFragmentProviderImpl implements PackageFragmentProvider {
    private final Collection<PackageFragmentDescriptor> packageFragments;

    public PackageFragmentProviderImpl(Collection<? extends PackageFragmentDescriptor> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "packageFragments");
        this.packageFragments = collection;
    }

    public List<PackageFragmentDescriptor> getPackageFragments(FqName fqName) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Collection arrayList = new ArrayList();
        for (Object next : this.packageFragments) {
            if (Intrinsics.areEqual((Object) ((PackageFragmentDescriptor) next).getFqName(), (Object) fqName)) {
                arrayList.add(next);
            }
        }
        return (List) arrayList;
    }

    public Collection<FqName> getSubPackagesOf(FqName fqName, Function1<? super Name, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(fqName, "fqName");
        Intrinsics.checkParameterIsNotNull(function1, "nameFilter");
        return SequencesKt.toList(SequencesKt.filter(SequencesKt.map(CollectionsKt.asSequence(this.packageFragments), PackageFragmentProviderImpl$getSubPackagesOf$1.INSTANCE), new PackageFragmentProviderImpl$getSubPackagesOf$2(fqName)));
    }
}
