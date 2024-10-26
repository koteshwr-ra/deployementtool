package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;

/* compiled from: DescriptorEquivalenceForOverrides.kt */
public final class DescriptorEquivalenceForOverrides {
    public static final DescriptorEquivalenceForOverrides INSTANCE = new DescriptorEquivalenceForOverrides();

    private DescriptorEquivalenceForOverrides() {
    }

    public final boolean areEquivalent(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
        if ((declarationDescriptor instanceof ClassDescriptor) && (declarationDescriptor2 instanceof ClassDescriptor)) {
            return areClassesEquivalent((ClassDescriptor) declarationDescriptor, (ClassDescriptor) declarationDescriptor2);
        }
        if ((declarationDescriptor instanceof TypeParameterDescriptor) && (declarationDescriptor2 instanceof TypeParameterDescriptor)) {
            return areTypeParametersEquivalent$default(this, (TypeParameterDescriptor) declarationDescriptor, (TypeParameterDescriptor) declarationDescriptor2, (Function2) null, 4, (Object) null);
        } else if ((declarationDescriptor instanceof CallableDescriptor) && (declarationDescriptor2 instanceof CallableDescriptor)) {
            return areCallableDescriptorsEquivalent$default(this, (CallableDescriptor) declarationDescriptor, (CallableDescriptor) declarationDescriptor2, false, 4, (Object) null);
        } else if (!(declarationDescriptor instanceof PackageFragmentDescriptor) || !(declarationDescriptor2 instanceof PackageFragmentDescriptor)) {
            return Intrinsics.areEqual((Object) declarationDescriptor, (Object) declarationDescriptor2);
        } else {
            return Intrinsics.areEqual((Object) ((PackageFragmentDescriptor) declarationDescriptor).getFqName(), (Object) ((PackageFragmentDescriptor) declarationDescriptor2).getFqName());
        }
    }

    private final boolean areClassesEquivalent(ClassDescriptor classDescriptor, ClassDescriptor classDescriptor2) {
        return Intrinsics.areEqual((Object) classDescriptor.getTypeConstructor(), (Object) classDescriptor2.getTypeConstructor());
    }

    static /* synthetic */ boolean areTypeParametersEquivalent$default(DescriptorEquivalenceForOverrides descriptorEquivalenceForOverrides, TypeParameterDescriptor typeParameterDescriptor, TypeParameterDescriptor typeParameterDescriptor2, Function2 function2, int i, Object obj) {
        if ((i & 4) != 0) {
            function2 = DescriptorEquivalenceForOverrides$areTypeParametersEquivalent$1.INSTANCE;
        }
        return descriptorEquivalenceForOverrides.areTypeParametersEquivalent(typeParameterDescriptor, typeParameterDescriptor2, function2);
    }

    /* access modifiers changed from: private */
    public final boolean areTypeParametersEquivalent(TypeParameterDescriptor typeParameterDescriptor, TypeParameterDescriptor typeParameterDescriptor2, Function2<? super DeclarationDescriptor, ? super DeclarationDescriptor, Boolean> function2) {
        if (Intrinsics.areEqual((Object) typeParameterDescriptor, (Object) typeParameterDescriptor2)) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) typeParameterDescriptor.getContainingDeclaration(), (Object) typeParameterDescriptor2.getContainingDeclaration()) && ownersEquivalent(typeParameterDescriptor, typeParameterDescriptor2, function2) && typeParameterDescriptor.getIndex() == typeParameterDescriptor2.getIndex()) {
            return true;
        }
        return false;
    }

    public static /* synthetic */ boolean areCallableDescriptorsEquivalent$default(DescriptorEquivalenceForOverrides descriptorEquivalenceForOverrides, CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return descriptorEquivalenceForOverrides.areCallableDescriptorsEquivalent(callableDescriptor, callableDescriptor2, z);
    }

    public final boolean areCallableDescriptorsEquivalent(CallableDescriptor callableDescriptor, CallableDescriptor callableDescriptor2, boolean z) {
        Intrinsics.checkParameterIsNotNull(callableDescriptor, "a");
        Intrinsics.checkParameterIsNotNull(callableDescriptor2, "b");
        if (Intrinsics.areEqual((Object) callableDescriptor, (Object) callableDescriptor2)) {
            return true;
        }
        if ((!Intrinsics.areEqual((Object) callableDescriptor.getName(), (Object) callableDescriptor2.getName())) || Intrinsics.areEqual((Object) callableDescriptor.getContainingDeclaration(), (Object) callableDescriptor2.getContainingDeclaration())) {
            return false;
        }
        DeclarationDescriptor declarationDescriptor = callableDescriptor;
        if (!DescriptorUtils.isLocal(declarationDescriptor)) {
            DeclarationDescriptor declarationDescriptor2 = callableDescriptor2;
            if (DescriptorUtils.isLocal(declarationDescriptor2) || !ownersEquivalent(declarationDescriptor, declarationDescriptor2, DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$1.INSTANCE)) {
                return false;
            }
            OverridingUtil createWithEqualityAxioms = OverridingUtil.createWithEqualityAxioms(new DescriptorEquivalenceForOverrides$areCallableDescriptorsEquivalent$overridingUtil$1(callableDescriptor, callableDescriptor2));
            Intrinsics.checkExpressionValueIsNotNull(createWithEqualityAxioms, "OverridingUtil.createWit…= a && y == b})\n        }");
            OverridingUtil.OverrideCompatibilityInfo isOverridableBy = createWithEqualityAxioms.isOverridableBy(callableDescriptor, callableDescriptor2, (ClassDescriptor) null, !z);
            Intrinsics.checkExpressionValueIsNotNull(isOverridableBy, "overridingUtil.isOverrid… null, !ignoreReturnType)");
            if (isOverridableBy.getResult() == OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE) {
                OverridingUtil.OverrideCompatibilityInfo isOverridableBy2 = createWithEqualityAxioms.isOverridableBy(callableDescriptor2, callableDescriptor, (ClassDescriptor) null, !z);
                Intrinsics.checkExpressionValueIsNotNull(isOverridableBy2, "overridingUtil.isOverrid… null, !ignoreReturnType)");
                if (isOverridableBy2.getResult() == OverridingUtil.OverrideCompatibilityInfo.Result.OVERRIDABLE) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private final boolean ownersEquivalent(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2, Function2<? super DeclarationDescriptor, ? super DeclarationDescriptor, Boolean> function2) {
        DeclarationDescriptor containingDeclaration = declarationDescriptor.getContainingDeclaration();
        DeclarationDescriptor containingDeclaration2 = declarationDescriptor2.getContainingDeclaration();
        if ((containingDeclaration instanceof CallableMemberDescriptor) || (containingDeclaration2 instanceof CallableMemberDescriptor)) {
            return function2.invoke(containingDeclaration, containingDeclaration2).booleanValue();
        }
        return areEquivalent(containingDeclaration, containingDeclaration2);
    }
}
