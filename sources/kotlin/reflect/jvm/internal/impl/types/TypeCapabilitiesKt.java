package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: TypeCapabilities.kt */
public final class TypeCapabilitiesKt {
    public static final boolean isCustomTypeVariable(KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "$this$isCustomTypeVariable");
        UnwrappedType unwrap = kotlinType.unwrap();
        if (!(unwrap instanceof CustomTypeVariable)) {
            unwrap = null;
        }
        CustomTypeVariable customTypeVariable = (CustomTypeVariable) unwrap;
        if (customTypeVariable != null) {
            return customTypeVariable.isTypeVariable();
        }
        return false;
    }

    public static final CustomTypeVariable getCustomTypeVariable(KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "$this$getCustomTypeVariable");
        UnwrappedType unwrap = kotlinType.unwrap();
        if (!(unwrap instanceof CustomTypeVariable)) {
            unwrap = null;
        }
        CustomTypeVariable customTypeVariable = (CustomTypeVariable) unwrap;
        if (customTypeVariable == null || !customTypeVariable.isTypeVariable()) {
            return null;
        }
        return customTypeVariable;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0012, code lost:
        r0 = r0.getSubTypeRepresentative();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlin.reflect.jvm.internal.impl.types.KotlinType getSubtypeRepresentative(kotlin.reflect.jvm.internal.impl.types.KotlinType r2) {
        /*
            java.lang.String r0 = "$this$getSubtypeRepresentative"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            kotlin.reflect.jvm.internal.impl.types.UnwrappedType r0 = r2.unwrap()
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.impl.types.SubtypingRepresentatives
            if (r1 != 0) goto L_0x000e
            r0 = 0
        L_0x000e:
            kotlin.reflect.jvm.internal.impl.types.SubtypingRepresentatives r0 = (kotlin.reflect.jvm.internal.impl.types.SubtypingRepresentatives) r0
            if (r0 == 0) goto L_0x0019
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r0.getSubTypeRepresentative()
            if (r0 == 0) goto L_0x0019
            r2 = r0
        L_0x0019:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.TypeCapabilitiesKt.getSubtypeRepresentative(kotlin.reflect.jvm.internal.impl.types.KotlinType):kotlin.reflect.jvm.internal.impl.types.KotlinType");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0012, code lost:
        r0 = r0.getSuperTypeRepresentative();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlin.reflect.jvm.internal.impl.types.KotlinType getSupertypeRepresentative(kotlin.reflect.jvm.internal.impl.types.KotlinType r2) {
        /*
            java.lang.String r0 = "$this$getSupertypeRepresentative"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            kotlin.reflect.jvm.internal.impl.types.UnwrappedType r0 = r2.unwrap()
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.impl.types.SubtypingRepresentatives
            if (r1 != 0) goto L_0x000e
            r0 = 0
        L_0x000e:
            kotlin.reflect.jvm.internal.impl.types.SubtypingRepresentatives r0 = (kotlin.reflect.jvm.internal.impl.types.SubtypingRepresentatives) r0
            if (r0 == 0) goto L_0x0019
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r0.getSuperTypeRepresentative()
            if (r0 == 0) goto L_0x0019
            r2 = r0
        L_0x0019:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.TypeCapabilitiesKt.getSupertypeRepresentative(kotlin.reflect.jvm.internal.impl.types.KotlinType):kotlin.reflect.jvm.internal.impl.types.KotlinType");
    }

    public static final boolean sameTypeConstructors(KotlinType kotlinType, KotlinType kotlinType2) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "first");
        Intrinsics.checkParameterIsNotNull(kotlinType2, "second");
        UnwrappedType unwrap = kotlinType.unwrap();
        SubtypingRepresentatives subtypingRepresentatives = null;
        if (!(unwrap instanceof SubtypingRepresentatives)) {
            unwrap = null;
        }
        SubtypingRepresentatives subtypingRepresentatives2 = (SubtypingRepresentatives) unwrap;
        if (!(subtypingRepresentatives2 != null ? subtypingRepresentatives2.sameTypeConstructor(kotlinType2) : false)) {
            UnwrappedType unwrap2 = kotlinType2.unwrap();
            if (unwrap2 instanceof SubtypingRepresentatives) {
                subtypingRepresentatives = unwrap2;
            }
            SubtypingRepresentatives subtypingRepresentatives3 = subtypingRepresentatives;
            if (subtypingRepresentatives3 != null ? subtypingRepresentatives3.sameTypeConstructor(kotlinType) : false) {
                return true;
            }
            return false;
        }
        return true;
    }
}
