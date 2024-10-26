package kotlin.reflect.jvm.internal.impl.descriptors;

import com.limpoxe.support.servicemanager.ServiceProvider;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.sequences.SequencesKt;

/* compiled from: findClassInModule.kt */
public final class FindClassInModuleKt {
    public static final ClassDescriptor findClassAcrossModuleDependencies(ModuleDescriptor moduleDescriptor, ClassId classId) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "$this$findClassAcrossModuleDependencies");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        FqName packageFqName = classId.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName, "classId.packageFqName");
        PackageViewDescriptor packageViewDescriptor = moduleDescriptor.getPackage(packageFqName);
        List<Name> pathSegments = classId.getRelativeClassName().pathSegments();
        Intrinsics.checkExpressionValueIsNotNull(pathSegments, "classId.relativeClassName.pathSegments()");
        MemberScope memberScope = packageViewDescriptor.getMemberScope();
        Object first = CollectionsKt.first(pathSegments);
        Intrinsics.checkExpressionValueIsNotNull(first, "segments.first()");
        ClassifierDescriptor contributedClassifier = memberScope.getContributedClassifier((Name) first, NoLookupLocation.FROM_DESERIALIZATION);
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) contributedClassifier;
        if (classDescriptor == null) {
            return null;
        }
        for (Name next : pathSegments.subList(1, pathSegments.size())) {
            MemberScope unsubstitutedInnerClassesScope = classDescriptor.getUnsubstitutedInnerClassesScope();
            Intrinsics.checkExpressionValueIsNotNull(next, ServiceProvider.NAME);
            ClassifierDescriptor contributedClassifier2 = unsubstitutedInnerClassesScope.getContributedClassifier(next, NoLookupLocation.FROM_DESERIALIZATION);
            if (!(contributedClassifier2 instanceof ClassDescriptor)) {
                contributedClassifier2 = null;
            }
            classDescriptor = (ClassDescriptor) contributedClassifier2;
            if (classDescriptor == null) {
                return null;
            }
        }
        return classDescriptor;
    }

    public static final ClassDescriptor findNonGenericClassAcrossDependencies(ModuleDescriptor moduleDescriptor, ClassId classId, NotFoundClasses notFoundClasses) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "$this$findNonGenericClassAcrossDependencies");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        Intrinsics.checkParameterIsNotNull(notFoundClasses, "notFoundClasses");
        ClassDescriptor findClassAcrossModuleDependencies = findClassAcrossModuleDependencies(moduleDescriptor, classId);
        if (findClassAcrossModuleDependencies != null) {
            return findClassAcrossModuleDependencies;
        }
        return notFoundClasses.getClass(classId, SequencesKt.toList(SequencesKt.map(SequencesKt.generateSequence(classId, FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$1.INSTANCE), FindClassInModuleKt$findNonGenericClassAcrossDependencies$typeParametersCount$2.INSTANCE)));
    }

    public static final TypeAliasDescriptor findTypeAliasAcrossModuleDependencies(ModuleDescriptor moduleDescriptor, ClassId classId) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "$this$findTypeAliasAcrossModuleDependencies");
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        FqName packageFqName = classId.getPackageFqName();
        Intrinsics.checkExpressionValueIsNotNull(packageFqName, "classId.packageFqName");
        PackageViewDescriptor packageViewDescriptor = moduleDescriptor.getPackage(packageFqName);
        List<Name> pathSegments = classId.getRelativeClassName().pathSegments();
        Intrinsics.checkExpressionValueIsNotNull(pathSegments, "classId.relativeClassName.pathSegments()");
        int size = pathSegments.size() - 1;
        MemberScope memberScope = packageViewDescriptor.getMemberScope();
        Object first = CollectionsKt.first(pathSegments);
        Intrinsics.checkExpressionValueIsNotNull(first, "segments.first()");
        ClassifierDescriptor contributedClassifier = memberScope.getContributedClassifier((Name) first, NoLookupLocation.FROM_DESERIALIZATION);
        TypeAliasDescriptor typeAliasDescriptor = null;
        if (size == 0) {
            if (!(contributedClassifier instanceof TypeAliasDescriptor)) {
                contributedClassifier = null;
            }
            return (TypeAliasDescriptor) contributedClassifier;
        }
        if (!(contributedClassifier instanceof ClassDescriptor)) {
            contributedClassifier = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) contributedClassifier;
        if (classDescriptor == null) {
            return null;
        }
        for (Name next : pathSegments.subList(1, size)) {
            MemberScope unsubstitutedInnerClassesScope = classDescriptor.getUnsubstitutedInnerClassesScope();
            Intrinsics.checkExpressionValueIsNotNull(next, ServiceProvider.NAME);
            ClassifierDescriptor contributedClassifier2 = unsubstitutedInnerClassesScope.getContributedClassifier(next, NoLookupLocation.FROM_DESERIALIZATION);
            if (!(contributedClassifier2 instanceof ClassDescriptor)) {
                contributedClassifier2 = null;
            }
            classDescriptor = (ClassDescriptor) contributedClassifier2;
            if (classDescriptor == null) {
                return null;
            }
        }
        Name name = pathSegments.get(size);
        MemberScope unsubstitutedMemberScope = classDescriptor.getUnsubstitutedMemberScope();
        Intrinsics.checkExpressionValueIsNotNull(name, "lastName");
        ClassifierDescriptor contributedClassifier3 = unsubstitutedMemberScope.getContributedClassifier(name, NoLookupLocation.FROM_DESERIALIZATION);
        if (contributedClassifier3 instanceof TypeAliasDescriptor) {
            typeAliasDescriptor = contributedClassifier3;
        }
        return typeAliasDescriptor;
    }
}
