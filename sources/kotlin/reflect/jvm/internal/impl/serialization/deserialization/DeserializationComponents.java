package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

/* compiled from: context.kt */
public final class DeserializationComponents {
    private final AdditionalClassPartsProvider additionalClassPartsProvider;
    private final AnnotationAndConstantLoader<AnnotationDescriptor, ConstantValue<?>> annotationAndConstantLoader;
    private final ClassDataFinder classDataFinder;
    private final ClassDeserializer classDeserializer = new ClassDeserializer(this);
    private final DeserializationConfiguration configuration;
    private final ContractDeserializer contractDeserializer;
    private final ErrorReporter errorReporter;
    private final ExtensionRegistryLite extensionRegistryLite;
    private final Iterable<ClassDescriptorFactory> fictitiousClassDescriptorFactories;
    private final FlexibleTypeDeserializer flexibleTypeDeserializer;
    private final LocalClassifierTypeSettings localClassifierTypeSettings;
    private final LookupTracker lookupTracker;
    private final ModuleDescriptor moduleDescriptor;
    private final NotFoundClasses notFoundClasses;
    private final PackageFragmentProvider packageFragmentProvider;
    private final PlatformDependentDeclarationFilter platformDependentDeclarationFilter;
    private final StorageManager storageManager;

    public DeserializationComponents(StorageManager storageManager2, ModuleDescriptor moduleDescriptor2, DeserializationConfiguration deserializationConfiguration, ClassDataFinder classDataFinder2, AnnotationAndConstantLoader<? extends AnnotationDescriptor, ? extends ConstantValue<?>> annotationAndConstantLoader2, PackageFragmentProvider packageFragmentProvider2, LocalClassifierTypeSettings localClassifierTypeSettings2, ErrorReporter errorReporter2, LookupTracker lookupTracker2, FlexibleTypeDeserializer flexibleTypeDeserializer2, Iterable<? extends ClassDescriptorFactory> iterable, NotFoundClasses notFoundClasses2, ContractDeserializer contractDeserializer2, AdditionalClassPartsProvider additionalClassPartsProvider2, PlatformDependentDeclarationFilter platformDependentDeclarationFilter2, ExtensionRegistryLite extensionRegistryLite2) {
        StorageManager storageManager3 = storageManager2;
        ModuleDescriptor moduleDescriptor3 = moduleDescriptor2;
        DeserializationConfiguration deserializationConfiguration2 = deserializationConfiguration;
        ClassDataFinder classDataFinder3 = classDataFinder2;
        AnnotationAndConstantLoader<? extends AnnotationDescriptor, ? extends ConstantValue<?>> annotationAndConstantLoader3 = annotationAndConstantLoader2;
        PackageFragmentProvider packageFragmentProvider3 = packageFragmentProvider2;
        LocalClassifierTypeSettings localClassifierTypeSettings3 = localClassifierTypeSettings2;
        ErrorReporter errorReporter3 = errorReporter2;
        LookupTracker lookupTracker3 = lookupTracker2;
        FlexibleTypeDeserializer flexibleTypeDeserializer3 = flexibleTypeDeserializer2;
        Iterable<? extends ClassDescriptorFactory> iterable2 = iterable;
        NotFoundClasses notFoundClasses3 = notFoundClasses2;
        ContractDeserializer contractDeserializer3 = contractDeserializer2;
        AdditionalClassPartsProvider additionalClassPartsProvider3 = additionalClassPartsProvider2;
        Intrinsics.checkParameterIsNotNull(storageManager3, "storageManager");
        Intrinsics.checkParameterIsNotNull(moduleDescriptor3, "moduleDescriptor");
        Intrinsics.checkParameterIsNotNull(deserializationConfiguration2, "configuration");
        Intrinsics.checkParameterIsNotNull(classDataFinder3, "classDataFinder");
        Intrinsics.checkParameterIsNotNull(annotationAndConstantLoader3, "annotationAndConstantLoader");
        Intrinsics.checkParameterIsNotNull(packageFragmentProvider3, "packageFragmentProvider");
        Intrinsics.checkParameterIsNotNull(localClassifierTypeSettings3, "localClassifierTypeSettings");
        Intrinsics.checkParameterIsNotNull(errorReporter3, "errorReporter");
        Intrinsics.checkParameterIsNotNull(lookupTracker3, "lookupTracker");
        Intrinsics.checkParameterIsNotNull(flexibleTypeDeserializer3, "flexibleTypeDeserializer");
        Intrinsics.checkParameterIsNotNull(iterable2, "fictitiousClassDescriptorFactories");
        Intrinsics.checkParameterIsNotNull(notFoundClasses3, "notFoundClasses");
        Intrinsics.checkParameterIsNotNull(contractDeserializer3, "contractDeserializer");
        Intrinsics.checkParameterIsNotNull(additionalClassPartsProvider3, "additionalClassPartsProvider");
        Intrinsics.checkParameterIsNotNull(platformDependentDeclarationFilter2, "platformDependentDeclarationFilter");
        ExtensionRegistryLite extensionRegistryLite3 = extensionRegistryLite2;
        Intrinsics.checkParameterIsNotNull(extensionRegistryLite3, "extensionRegistryLite");
        this.storageManager = storageManager3;
        this.moduleDescriptor = moduleDescriptor3;
        this.configuration = deserializationConfiguration2;
        this.classDataFinder = classDataFinder3;
        this.annotationAndConstantLoader = annotationAndConstantLoader3;
        this.packageFragmentProvider = packageFragmentProvider3;
        this.localClassifierTypeSettings = localClassifierTypeSettings3;
        this.errorReporter = errorReporter3;
        this.lookupTracker = lookupTracker3;
        this.flexibleTypeDeserializer = flexibleTypeDeserializer3;
        this.fictitiousClassDescriptorFactories = iterable2;
        this.notFoundClasses = notFoundClasses3;
        this.contractDeserializer = contractDeserializer3;
        this.additionalClassPartsProvider = additionalClassPartsProvider3;
        this.platformDependentDeclarationFilter = platformDependentDeclarationFilter2;
        this.extensionRegistryLite = extensionRegistryLite3;
    }

    public final StorageManager getStorageManager() {
        return this.storageManager;
    }

    public final ModuleDescriptor getModuleDescriptor() {
        return this.moduleDescriptor;
    }

    public final DeserializationConfiguration getConfiguration() {
        return this.configuration;
    }

    public final ClassDataFinder getClassDataFinder() {
        return this.classDataFinder;
    }

    public final AnnotationAndConstantLoader<AnnotationDescriptor, ConstantValue<?>> getAnnotationAndConstantLoader() {
        return this.annotationAndConstantLoader;
    }

    public final PackageFragmentProvider getPackageFragmentProvider() {
        return this.packageFragmentProvider;
    }

    public final LocalClassifierTypeSettings getLocalClassifierTypeSettings() {
        return this.localClassifierTypeSettings;
    }

    public final ErrorReporter getErrorReporter() {
        return this.errorReporter;
    }

    public final LookupTracker getLookupTracker() {
        return this.lookupTracker;
    }

    public final FlexibleTypeDeserializer getFlexibleTypeDeserializer() {
        return this.flexibleTypeDeserializer;
    }

    public final Iterable<ClassDescriptorFactory> getFictitiousClassDescriptorFactories() {
        return this.fictitiousClassDescriptorFactories;
    }

    public final NotFoundClasses getNotFoundClasses() {
        return this.notFoundClasses;
    }

    public final ContractDeserializer getContractDeserializer() {
        return this.contractDeserializer;
    }

    public final AdditionalClassPartsProvider getAdditionalClassPartsProvider() {
        return this.additionalClassPartsProvider;
    }

    public final PlatformDependentDeclarationFilter getPlatformDependentDeclarationFilter() {
        return this.platformDependentDeclarationFilter;
    }

    public final ExtensionRegistryLite getExtensionRegistryLite() {
        return this.extensionRegistryLite;
    }

    public final ClassDeserializer getClassDeserializer() {
        return this.classDeserializer;
    }

    public final ClassDescriptor deserializeClass(ClassId classId) {
        Intrinsics.checkParameterIsNotNull(classId, "classId");
        return ClassDeserializer.deserializeClass$default(this.classDeserializer, classId, (ClassData) null, 2, (Object) null);
    }

    public final DeserializationContext createContext(PackageFragmentDescriptor packageFragmentDescriptor, NameResolver nameResolver, TypeTable typeTable, VersionRequirementTable versionRequirementTable, BinaryVersion binaryVersion, DeserializedContainerSource deserializedContainerSource) {
        Intrinsics.checkParameterIsNotNull(packageFragmentDescriptor, "descriptor");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        VersionRequirementTable versionRequirementTable2 = versionRequirementTable;
        Intrinsics.checkParameterIsNotNull(versionRequirementTable2, "versionRequirementTable");
        BinaryVersion binaryVersion2 = binaryVersion;
        Intrinsics.checkParameterIsNotNull(binaryVersion2, "metadataVersion");
        return new DeserializationContext(this, nameResolver, packageFragmentDescriptor, typeTable, versionRequirementTable2, binaryVersion2, deserializedContainerSource, (TypeDeserializer) null, CollectionsKt.emptyList());
    }
}
