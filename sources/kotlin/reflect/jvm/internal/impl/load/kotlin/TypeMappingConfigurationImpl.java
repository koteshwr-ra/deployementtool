package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingConfiguration;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

/* compiled from: methodSignatureMapping.kt */
public final class TypeMappingConfigurationImpl implements TypeMappingConfiguration<JvmType> {
    public static final TypeMappingConfigurationImpl INSTANCE = new TypeMappingConfigurationImpl();

    public String getPredefinedInternalNameForClass(ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
        return null;
    }

    public JvmType getPredefinedTypeForClass(ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
        return null;
    }

    public void processErrorType(KotlinType kotlinType, ClassDescriptor classDescriptor) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "kotlinType");
        Intrinsics.checkParameterIsNotNull(classDescriptor, "descriptor");
    }

    private TypeMappingConfigurationImpl() {
    }

    public KotlinType preprocessType(KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "kotlinType");
        return TypeMappingConfiguration.DefaultImpls.preprocessType(this, kotlinType);
    }

    public boolean releaseCoroutines() {
        return TypeMappingConfiguration.DefaultImpls.releaseCoroutines(this);
    }

    public KotlinType commonSupertype(Collection<? extends KotlinType> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "types");
        throw new AssertionError("There should be no intersection type in existing descriptors, but found: " + CollectionsKt.joinToString$default(collection, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null));
    }
}
