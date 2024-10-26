package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;

/* compiled from: NewKotlinTypeChecker.kt */
public final class NewKotlinTypeChecker implements KotlinTypeChecker {
    public static final NewKotlinTypeChecker INSTANCE = new NewKotlinTypeChecker();

    private NewKotlinTypeChecker() {
    }

    public boolean isSubtypeOf(KotlinType kotlinType, KotlinType kotlinType2) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "subtype");
        Intrinsics.checkParameterIsNotNull(kotlinType2, "supertype");
        return isSubtypeOf(new ClassicTypeCheckerContext(true, false, 2, (DefaultConstructorMarker) null), kotlinType.unwrap(), kotlinType2.unwrap());
    }

    public boolean equalTypes(KotlinType kotlinType, KotlinType kotlinType2) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "a");
        Intrinsics.checkParameterIsNotNull(kotlinType2, "b");
        return equalTypes(new ClassicTypeCheckerContext(false, false, 2, (DefaultConstructorMarker) null), kotlinType.unwrap(), kotlinType2.unwrap());
    }

    public final boolean equalTypes(ClassicTypeCheckerContext classicTypeCheckerContext, UnwrappedType unwrappedType, UnwrappedType unwrappedType2) {
        Intrinsics.checkParameterIsNotNull(classicTypeCheckerContext, "$this$equalTypes");
        Intrinsics.checkParameterIsNotNull(unwrappedType, "a");
        Intrinsics.checkParameterIsNotNull(unwrappedType2, "b");
        return AbstractTypeChecker.INSTANCE.equalTypes(classicTypeCheckerContext, unwrappedType, unwrappedType2);
    }

    public final boolean isSubtypeOf(ClassicTypeCheckerContext classicTypeCheckerContext, UnwrappedType unwrappedType, UnwrappedType unwrappedType2) {
        Intrinsics.checkParameterIsNotNull(classicTypeCheckerContext, "$this$isSubtypeOf");
        Intrinsics.checkParameterIsNotNull(unwrappedType, "subType");
        Intrinsics.checkParameterIsNotNull(unwrappedType2, "superType");
        return AbstractTypeChecker.INSTANCE.isSubtypeOf(classicTypeCheckerContext, unwrappedType, unwrappedType2);
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [kotlin.reflect.jvm.internal.impl.types.UnwrappedType] */
    /* JADX WARNING: type inference failed for: r3v8, types: [kotlin.reflect.jvm.internal.impl.types.UnwrappedType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final kotlin.reflect.jvm.internal.impl.types.SimpleType transformToNewType(kotlin.reflect.jvm.internal.impl.types.SimpleType r12) {
        /*
            r11 = this;
            java.lang.String r0 = "type"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r0 = r12.getConstructor()
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorImpl
            r2 = 1
            r3 = 0
            r4 = 10
            r5 = 0
            if (r1 == 0) goto L_0x008c
            kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorImpl r0 = (kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedTypeConstructorImpl) r0
            kotlin.reflect.jvm.internal.impl.types.TypeProjection r1 = r0.getProjection()
            kotlin.reflect.jvm.internal.impl.types.Variance r6 = r1.getProjectionKind()
            kotlin.reflect.jvm.internal.impl.types.Variance r7 = kotlin.reflect.jvm.internal.impl.types.Variance.IN_VARIANCE
            if (r6 != r7) goto L_0x0021
            goto L_0x0022
        L_0x0021:
            r2 = 0
        L_0x0022:
            if (r2 == 0) goto L_0x0025
            goto L_0x0026
        L_0x0025:
            r1 = r3
        L_0x0026:
            if (r1 == 0) goto L_0x0032
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = r1.getType()
            if (r1 == 0) goto L_0x0032
            kotlin.reflect.jvm.internal.impl.types.UnwrappedType r3 = r1.unwrap()
        L_0x0032:
            r8 = r3
            kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedTypeConstructor r1 = r0.getNewTypeConstructor()
            if (r1 != 0) goto L_0x0070
            kotlin.reflect.jvm.internal.impl.types.TypeProjection r1 = r0.getProjection()
            java.util.Collection r2 = r0.getSupertypes()
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.ArrayList r3 = new java.util.ArrayList
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r2, r4)
            r3.<init>(r4)
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.Iterator r2 = r2.iterator()
        L_0x0052:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0066
            java.lang.Object r4 = r2.next()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r4 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r4
            kotlin.reflect.jvm.internal.impl.types.UnwrappedType r4 = r4.unwrap()
            r3.add(r4)
            goto L_0x0052
        L_0x0066:
            java.util.List r3 = (java.util.List) r3
            kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedTypeConstructor r2 = new kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedTypeConstructor
            r2.<init>(r1, r3)
            r0.setNewTypeConstructor(r2)
        L_0x0070:
            kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedType r1 = new kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedType
            kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus r6 = kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus.FOR_SUBTYPING
            kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedTypeConstructor r7 = r0.getNewTypeConstructor()
            if (r7 != 0) goto L_0x007d
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x007d:
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r9 = r12.getAnnotations()
            boolean r10 = r12.isMarkedNullable()
            r5 = r1
            r5.<init>(r6, r7, r8, r9, r10)
            kotlin.reflect.jvm.internal.impl.types.SimpleType r1 = (kotlin.reflect.jvm.internal.impl.types.SimpleType) r1
            return r1
        L_0x008c:
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerValueTypeConstructor
            if (r1 == 0) goto L_0x00db
            kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerValueTypeConstructor r0 = (kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerValueTypeConstructor) r0
            java.util.Collection r0 = r0.getSupertypes()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r1 = new java.util.ArrayList
            int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r4)
            r1.<init>(r2)
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r0 = r0.iterator()
        L_0x00a7:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x00bf
            java.lang.Object r2 = r0.next()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r2 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r2
            boolean r3 = r12.isMarkedNullable()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r2 = kotlin.reflect.jvm.internal.impl.types.TypeUtils.makeNullableAsSpecified(r2, r3)
            r1.add(r2)
            goto L_0x00a7
        L_0x00bf:
            java.util.List r1 = (java.util.List) r1
            java.util.Collection r1 = (java.util.Collection) r1
            kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor r0 = new kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor
            r0.<init>(r1)
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r1 = r12.getAnnotations()
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r0 = (kotlin.reflect.jvm.internal.impl.types.TypeConstructor) r0
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
            kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope r12 = r12.getMemberScope()
            kotlin.reflect.jvm.internal.impl.types.SimpleType r12 = kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(r1, r0, r2, r5, r12)
            return r12
        L_0x00db:
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor
            if (r1 == 0) goto L_0x0134
            boolean r1 = r12.isMarkedNullable()
            if (r1 == 0) goto L_0x0134
            kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor r0 = (kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor) r0
            java.util.Collection r1 = r0.getSupertypes()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r6 = new java.util.ArrayList
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r1, r4)
            r6.<init>(r4)
            java.util.Collection r6 = (java.util.Collection) r6
            java.util.Iterator r1 = r1.iterator()
            r4 = 0
        L_0x00fd:
            boolean r7 = r1.hasNext()
            if (r7 == 0) goto L_0x0112
            java.lang.Object r4 = r1.next()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r4 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r4
            kotlin.reflect.jvm.internal.impl.types.KotlinType r4 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.makeNullable(r4)
            r6.add(r4)
            r4 = 1
            goto L_0x00fd
        L_0x0112:
            java.util.List r6 = (java.util.List) r6
            if (r4 != 0) goto L_0x0117
            goto L_0x011e
        L_0x0117:
            kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor r3 = new kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor
            java.util.Collection r6 = (java.util.Collection) r6
            r3.<init>(r6)
        L_0x011e:
            if (r3 == 0) goto L_0x0121
            r0 = r3
        L_0x0121:
            kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r12 = r12.getAnnotations()
            r1 = r0
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r1 = (kotlin.reflect.jvm.internal.impl.types.TypeConstructor) r1
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
            kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope r0 = r0.createScopeForKotlinType()
            kotlin.reflect.jvm.internal.impl.types.SimpleType r12 = kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(r12, r1, r2, r5, r0)
        L_0x0134:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.types.checker.NewKotlinTypeChecker.transformToNewType(kotlin.reflect.jvm.internal.impl.types.SimpleType):kotlin.reflect.jvm.internal.impl.types.SimpleType");
    }

    public final UnwrappedType transformToNewType(UnwrappedType unwrappedType) {
        UnwrappedType unwrappedType2;
        Intrinsics.checkParameterIsNotNull(unwrappedType, "type");
        if (unwrappedType instanceof SimpleType) {
            unwrappedType2 = transformToNewType((SimpleType) unwrappedType);
        } else if (unwrappedType instanceof FlexibleType) {
            FlexibleType flexibleType = (FlexibleType) unwrappedType;
            SimpleType transformToNewType = transformToNewType(flexibleType.getLowerBound());
            SimpleType transformToNewType2 = transformToNewType(flexibleType.getUpperBound());
            if (transformToNewType == flexibleType.getLowerBound() && transformToNewType2 == flexibleType.getUpperBound()) {
                unwrappedType2 = unwrappedType;
            } else {
                unwrappedType2 = KotlinTypeFactory.flexibleType(transformToNewType, transformToNewType2);
            }
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return TypeWithEnhancementKt.inheritEnhancement(unwrappedType2, unwrappedType);
    }
}
