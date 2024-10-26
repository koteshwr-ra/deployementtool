package kotlin.reflect.jvm.internal.components;

import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.ReflectionTypes;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsPackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.CompositePackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupTracker;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassesTracker;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaPropertyInitializerEvaluator;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.components.SamConversionResolver;
import kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverSettings;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaPackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.SingleModuleClassResolver;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.BinaryClassAnnotationAndConstantLoaderImpl;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializationComponentsForJava;
import kotlin.reflect.jvm.internal.impl.load.kotlin.DeserializedDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JavaClassDataFinder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder;
import kotlin.reflect.jvm.internal.impl.load.kotlin.PackagePartProvider;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.platform.TargetPlatform;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JavaDescriptorResolver;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ContractDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationComponents;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;
import kotlin.text.Typography;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lkotlin/reflect/jvm/internal/components/RuntimeModuleData;", "", "deserialization", "Lkotlin/reflect/jvm/internal/impl/serialization/deserialization/DeserializationComponents;", "packagePartScopeCache", "Lkotlin/reflect/jvm/internal/components/PackagePartScopeCache;", "(Lorg/jetbrains/kotlin/serialization/deserialization/DeserializationComponents;Lkotlin/reflect/jvm/internal/components/PackagePartScopeCache;)V", "getDeserialization", "()Lorg/jetbrains/kotlin/serialization/deserialization/DeserializationComponents;", "module", "Lkotlin/reflect/jvm/internal/impl/descriptors/ModuleDescriptor;", "getModule", "()Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "getPackagePartScopeCache", "()Lkotlin/reflect/jvm/internal/components/PackagePartScopeCache;", "Companion", "descriptors.runtime"}, k = 1, mv = {1, 1, 15})
/* compiled from: RuntimeModuleData.kt */
public final class RuntimeModuleData {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final DeserializationComponents deserialization;
    private final PackagePartScopeCache packagePartScopeCache;

    private RuntimeModuleData(DeserializationComponents deserializationComponents, PackagePartScopeCache packagePartScopeCache2) {
        this.deserialization = deserializationComponents;
        this.packagePartScopeCache = packagePartScopeCache2;
    }

    public /* synthetic */ RuntimeModuleData(DeserializationComponents deserializationComponents, PackagePartScopeCache packagePartScopeCache2, DefaultConstructorMarker defaultConstructorMarker) {
        this(deserializationComponents, packagePartScopeCache2);
    }

    public final DeserializationComponents getDeserialization() {
        return this.deserialization;
    }

    public final PackagePartScopeCache getPackagePartScopeCache() {
        return this.packagePartScopeCache;
    }

    public final ModuleDescriptor getModule() {
        return this.deserialization.getModuleDescriptor();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/reflect/jvm/internal/components/RuntimeModuleData$Companion;", "", "()V", "create", "Lkotlin/reflect/jvm/internal/components/RuntimeModuleData;", "classLoader", "Ljava/lang/ClassLoader;", "descriptors.runtime"}, k = 1, mv = {1, 1, 15})
    /* compiled from: RuntimeModuleData.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final RuntimeModuleData create(ClassLoader classLoader) {
            ClassLoader classLoader2 = classLoader;
            Intrinsics.checkParameterIsNotNull(classLoader2, "classLoader");
            StorageManager lockBasedStorageManager = new LockBasedStorageManager("RuntimeModuleData");
            JvmBuiltIns jvmBuiltIns = new JvmBuiltIns(lockBasedStorageManager, JvmBuiltIns.Kind.FROM_DEPENDENCIES);
            Name special = Name.special("<runtime module for " + classLoader2 + Typography.greater);
            Intrinsics.checkExpressionValueIsNotNull(special, "Name.special(\"<runtime module for $classLoader>\")");
            ModuleDescriptorImpl moduleDescriptorImpl = new ModuleDescriptorImpl(special, lockBasedStorageManager, jvmBuiltIns, (TargetPlatform) null, (Map) null, (Name) null, 56, (DefaultConstructorMarker) null);
            jvmBuiltIns.setBuiltInsModule(moduleDescriptorImpl);
            ReflectKotlinClassFinder reflectKotlinClassFinder = new ReflectKotlinClassFinder(classLoader2);
            DeserializedDescriptorResolver deserializedDescriptorResolver = r10;
            DeserializedDescriptorResolver deserializedDescriptorResolver2 = new DeserializedDescriptorResolver();
            SingleModuleClassResolver singleModuleClassResolver = new SingleModuleClassResolver();
            ModuleDescriptor moduleDescriptor = moduleDescriptorImpl;
            NotFoundClasses notFoundClasses = new NotFoundClasses(lockBasedStorageManager, moduleDescriptor);
            AnnotationTypeQualifierResolver annotationTypeQualifierResolver = r3;
            AnnotationTypeQualifierResolver annotationTypeQualifierResolver2 = new AnnotationTypeQualifierResolver(lockBasedStorageManager, Jsr305State.DISABLED);
            JavaClassFinder reflectJavaClassFinder = new ReflectJavaClassFinder(classLoader2);
            KotlinClassFinder kotlinClassFinder = reflectKotlinClassFinder;
            SignaturePropagator signaturePropagator = SignaturePropagator.DO_NOTHING;
            SignaturePropagator signaturePropagator2 = signaturePropagator;
            ModuleDescriptorImpl moduleDescriptorImpl2 = moduleDescriptorImpl;
            Intrinsics.checkExpressionValueIsNotNull(signaturePropagator, "SignaturePropagator.DO_NOTHING");
            ErrorReporter errorReporter = RuntimeErrorReporter.INSTANCE;
            JavaResolverCache javaResolverCache = JavaResolverCache.EMPTY;
            Intrinsics.checkExpressionValueIsNotNull(javaResolverCache, "JavaResolverCache.EMPTY");
            PackagePartProvider packagePartProvider = PackagePartProvider.Empty.INSTANCE;
            SupertypeLoopChecker supertypeLoopChecker = SupertypeLoopChecker.EMPTY.INSTANCE;
            LookupTracker lookupTracker = LookupTracker.DO_NOTHING.INSTANCE;
            ReflectionTypes reflectionTypes = r5;
            ReflectionTypes reflectionTypes2 = new ReflectionTypes(moduleDescriptor, notFoundClasses);
            SignatureEnhancement signatureEnhancement = r5;
            SingleModuleClassResolver singleModuleClassResolver2 = singleModuleClassResolver;
            SignatureEnhancement signatureEnhancement2 = new SignatureEnhancement(annotationTypeQualifierResolver2, Jsr305State.DISABLED);
            LazyJavaPackageFragmentProvider lazyJavaPackageFragmentProvider = new LazyJavaPackageFragmentProvider(new JavaResolverComponents(lockBasedStorageManager, reflectJavaClassFinder, kotlinClassFinder, deserializedDescriptorResolver, signaturePropagator2, errorReporter, javaResolverCache, JavaPropertyInitializerEvaluator.DoNothing.INSTANCE, SamConversionResolver.Empty.INSTANCE, RuntimeSourceElementFactory.INSTANCE, singleModuleClassResolver, packagePartProvider, supertypeLoopChecker, lookupTracker, moduleDescriptor, reflectionTypes, annotationTypeQualifierResolver, signatureEnhancement, JavaClassesTracker.Default.INSTANCE, JavaResolverSettings.Default.INSTANCE));
            jvmBuiltIns.initialize(moduleDescriptor, true);
            JavaResolverCache javaResolverCache2 = JavaResolverCache.EMPTY;
            Intrinsics.checkExpressionValueIsNotNull(javaResolverCache2, "JavaResolverCache.EMPTY");
            JavaDescriptorResolver javaDescriptorResolver = new JavaDescriptorResolver(lazyJavaPackageFragmentProvider, javaResolverCache2);
            JavaClassDataFinder javaClassDataFinder = new JavaClassDataFinder(kotlinClassFinder, deserializedDescriptorResolver2);
            BinaryClassAnnotationAndConstantLoaderImpl binaryClassAnnotationAndConstantLoaderImpl = new BinaryClassAnnotationAndConstantLoaderImpl(moduleDescriptor, notFoundClasses, lockBasedStorageManager, kotlinClassFinder);
            ModuleDescriptor moduleDescriptor2 = moduleDescriptor;
            NotFoundClasses notFoundClasses2 = notFoundClasses;
            ReflectKotlinClassFinder reflectKotlinClassFinder2 = reflectKotlinClassFinder;
            DeserializedDescriptorResolver deserializedDescriptorResolver3 = deserializedDescriptorResolver2;
            DeserializationComponentsForJava deserializationComponentsForJava = r3;
            DeserializationComponentsForJava deserializationComponentsForJava2 = new DeserializationComponentsForJava(lockBasedStorageManager, moduleDescriptor, DeserializationConfiguration.Default.INSTANCE, javaClassDataFinder, binaryClassAnnotationAndConstantLoaderImpl, lazyJavaPackageFragmentProvider, notFoundClasses2, RuntimeErrorReporter.INSTANCE, LookupTracker.DO_NOTHING.INSTANCE, ContractDeserializer.Companion.getDEFAULT());
            ClassLoader classLoader3 = Unit.class.getClassLoader();
            Intrinsics.checkExpressionValueIsNotNull(classLoader3, "stdlibClassLoader");
            JvmBuiltInsPackageFragmentProvider jvmBuiltInsPackageFragmentProvider = new JvmBuiltInsPackageFragmentProvider(lockBasedStorageManager, new ReflectKotlinClassFinder(classLoader3), moduleDescriptor2, notFoundClasses2, jvmBuiltIns.getSettings(), jvmBuiltIns.getSettings(), DeserializationConfiguration.Default.INSTANCE);
            singleModuleClassResolver2.setResolver(javaDescriptorResolver);
            DeserializationComponentsForJava deserializationComponentsForJava3 = deserializationComponentsForJava;
            deserializedDescriptorResolver3.setComponents(deserializationComponentsForJava3);
            ModuleDescriptorImpl moduleDescriptorImpl3 = moduleDescriptorImpl2;
            moduleDescriptorImpl3.setDependencies(moduleDescriptorImpl3);
            moduleDescriptorImpl3.initialize(new CompositePackageFragmentProvider(CollectionsKt.listOf(javaDescriptorResolver.getPackageFragmentProvider(), jvmBuiltInsPackageFragmentProvider)));
            return new RuntimeModuleData(deserializationComponentsForJava3.getComponents(), new PackagePartScopeCache(deserializedDescriptorResolver3, reflectKotlinClassFinder2), (DefaultConstructorMarker) null);
        }
    }
}
