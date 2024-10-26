package kotlin.reflect.jvm.internal.impl.types.checker;

import androidx.core.app.NotificationCompat;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntegerLiteralTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.checker.ClassicTypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.model.CaptureStatus;
import kotlin.reflect.jvm.internal.impl.types.model.CapturedTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.DynamicTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.FlexibleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentListMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeArgumentMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeConstructorMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeParameterMarker;
import kotlin.reflect.jvm.internal.impl.types.model.TypeVariance;

/* compiled from: ClassicTypeCheckerContext.kt */
public class ClassicTypeCheckerContext extends AbstractTypeCheckerContext implements ClassicTypeSystemContext {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final boolean allowedTypeVariable;
    private final boolean errorTypeEqualsToAnything;

    public ClassicTypeCheckerContext(boolean z, boolean z2) {
        this.errorTypeEqualsToAnything = z;
        this.allowedTypeVariable = z2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ClassicTypeCheckerContext(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? true : z2);
    }

    public int argumentsCount(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeMarker, "$this$argumentsCount");
        return ClassicTypeSystemContext.DefaultImpls.argumentsCount(this, kotlinTypeMarker);
    }

    public TypeArgumentListMarker asArgumentList(SimpleTypeMarker simpleTypeMarker) {
        Intrinsics.checkParameterIsNotNull(simpleTypeMarker, "$this$asArgumentList");
        return ClassicTypeSystemContext.DefaultImpls.asArgumentList(this, simpleTypeMarker);
    }

    public CapturedTypeMarker asCapturedType(SimpleTypeMarker simpleTypeMarker) {
        Intrinsics.checkParameterIsNotNull(simpleTypeMarker, "$this$asCapturedType");
        return ClassicTypeSystemContext.DefaultImpls.asCapturedType(this, simpleTypeMarker);
    }

    public DefinitelyNotNullTypeMarker asDefinitelyNotNullType(SimpleTypeMarker simpleTypeMarker) {
        Intrinsics.checkParameterIsNotNull(simpleTypeMarker, "$this$asDefinitelyNotNullType");
        return ClassicTypeSystemContext.DefaultImpls.asDefinitelyNotNullType(this, simpleTypeMarker);
    }

    public DynamicTypeMarker asDynamicType(FlexibleTypeMarker flexibleTypeMarker) {
        Intrinsics.checkParameterIsNotNull(flexibleTypeMarker, "$this$asDynamicType");
        return ClassicTypeSystemContext.DefaultImpls.asDynamicType(this, flexibleTypeMarker);
    }

    public FlexibleTypeMarker asFlexibleType(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeMarker, "$this$asFlexibleType");
        return ClassicTypeSystemContext.DefaultImpls.asFlexibleType(this, kotlinTypeMarker);
    }

    public SimpleTypeMarker asSimpleType(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeMarker, "$this$asSimpleType");
        return ClassicTypeSystemContext.DefaultImpls.asSimpleType(this, kotlinTypeMarker);
    }

    public TypeArgumentMarker asTypeArgument(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeMarker, "$this$asTypeArgument");
        return ClassicTypeSystemContext.DefaultImpls.asTypeArgument(this, kotlinTypeMarker);
    }

    public SimpleTypeMarker captureFromArguments(SimpleTypeMarker simpleTypeMarker, CaptureStatus captureStatus) {
        Intrinsics.checkParameterIsNotNull(simpleTypeMarker, "type");
        Intrinsics.checkParameterIsNotNull(captureStatus, NotificationCompat.CATEGORY_STATUS);
        return ClassicTypeSystemContext.DefaultImpls.captureFromArguments(this, simpleTypeMarker, captureStatus);
    }

    public List<SimpleTypeMarker> fastCorrespondingSupertypes(SimpleTypeMarker simpleTypeMarker, TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkParameterIsNotNull(simpleTypeMarker, "$this$fastCorrespondingSupertypes");
        Intrinsics.checkParameterIsNotNull(typeConstructorMarker, "constructor");
        return ClassicTypeSystemContext.DefaultImpls.fastCorrespondingSupertypes(this, simpleTypeMarker, typeConstructorMarker);
    }

    public TypeArgumentMarker get(TypeArgumentListMarker typeArgumentListMarker, int i) {
        Intrinsics.checkParameterIsNotNull(typeArgumentListMarker, "$this$get");
        return ClassicTypeSystemContext.DefaultImpls.get(this, typeArgumentListMarker, i);
    }

    public TypeArgumentMarker getArgument(KotlinTypeMarker kotlinTypeMarker, int i) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeMarker, "$this$getArgument");
        return ClassicTypeSystemContext.DefaultImpls.getArgument(this, kotlinTypeMarker, i);
    }

    public TypeArgumentMarker getArgumentOrNull(SimpleTypeMarker simpleTypeMarker, int i) {
        Intrinsics.checkParameterIsNotNull(simpleTypeMarker, "$this$getArgumentOrNull");
        return ClassicTypeSystemContext.DefaultImpls.getArgumentOrNull(this, simpleTypeMarker, i);
    }

    public TypeParameterMarker getParameter(TypeConstructorMarker typeConstructorMarker, int i) {
        Intrinsics.checkParameterIsNotNull(typeConstructorMarker, "$this$getParameter");
        return ClassicTypeSystemContext.DefaultImpls.getParameter(this, typeConstructorMarker, i);
    }

    public KotlinTypeMarker getType(TypeArgumentMarker typeArgumentMarker) {
        Intrinsics.checkParameterIsNotNull(typeArgumentMarker, "$this$getType");
        return ClassicTypeSystemContext.DefaultImpls.getType(this, typeArgumentMarker);
    }

    public TypeVariance getVariance(TypeArgumentMarker typeArgumentMarker) {
        Intrinsics.checkParameterIsNotNull(typeArgumentMarker, "$this$getVariance");
        return ClassicTypeSystemContext.DefaultImpls.getVariance((ClassicTypeSystemContext) this, typeArgumentMarker);
    }

    public TypeVariance getVariance(TypeParameterMarker typeParameterMarker) {
        Intrinsics.checkParameterIsNotNull(typeParameterMarker, "$this$getVariance");
        return ClassicTypeSystemContext.DefaultImpls.getVariance((ClassicTypeSystemContext) this, typeParameterMarker);
    }

    public boolean hasFlexibleNullability(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeMarker, "$this$hasFlexibleNullability");
        return ClassicTypeSystemContext.DefaultImpls.hasFlexibleNullability(this, kotlinTypeMarker);
    }

    public boolean identicalArguments(SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2) {
        Intrinsics.checkParameterIsNotNull(simpleTypeMarker, "a");
        Intrinsics.checkParameterIsNotNull(simpleTypeMarker2, "b");
        return ClassicTypeSystemContext.DefaultImpls.identicalArguments(this, simpleTypeMarker, simpleTypeMarker2);
    }

    public KotlinTypeMarker intersectTypes(List<? extends KotlinTypeMarker> list) {
        Intrinsics.checkParameterIsNotNull(list, "types");
        return ClassicTypeSystemContext.DefaultImpls.intersectTypes(this, list);
    }

    public boolean isAnyConstructor(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkParameterIsNotNull(typeConstructorMarker, "$this$isAnyConstructor");
        return ClassicTypeSystemContext.DefaultImpls.isAnyConstructor(this, typeConstructorMarker);
    }

    public boolean isClassType(SimpleTypeMarker simpleTypeMarker) {
        Intrinsics.checkParameterIsNotNull(simpleTypeMarker, "$this$isClassType");
        return ClassicTypeSystemContext.DefaultImpls.isClassType(this, simpleTypeMarker);
    }

    public boolean isClassTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkParameterIsNotNull(typeConstructorMarker, "$this$isClassTypeConstructor");
        return ClassicTypeSystemContext.DefaultImpls.isClassTypeConstructor(this, typeConstructorMarker);
    }

    public boolean isCommonFinalClassConstructor(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkParameterIsNotNull(typeConstructorMarker, "$this$isCommonFinalClassConstructor");
        return ClassicTypeSystemContext.DefaultImpls.isCommonFinalClassConstructor(this, typeConstructorMarker);
    }

    public boolean isDefinitelyNotNullType(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeMarker, "$this$isDefinitelyNotNullType");
        return ClassicTypeSystemContext.DefaultImpls.isDefinitelyNotNullType(this, kotlinTypeMarker);
    }

    public boolean isDenotable(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkParameterIsNotNull(typeConstructorMarker, "$this$isDenotable");
        return ClassicTypeSystemContext.DefaultImpls.isDenotable(this, typeConstructorMarker);
    }

    public boolean isDynamic(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeMarker, "$this$isDynamic");
        return ClassicTypeSystemContext.DefaultImpls.isDynamic(this, kotlinTypeMarker);
    }

    public boolean isEqualTypeConstructors(TypeConstructorMarker typeConstructorMarker, TypeConstructorMarker typeConstructorMarker2) {
        Intrinsics.checkParameterIsNotNull(typeConstructorMarker, "c1");
        Intrinsics.checkParameterIsNotNull(typeConstructorMarker2, "c2");
        return ClassicTypeSystemContext.DefaultImpls.isEqualTypeConstructors(this, typeConstructorMarker, typeConstructorMarker2);
    }

    public boolean isError(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeMarker, "$this$isError");
        return ClassicTypeSystemContext.DefaultImpls.isError(this, kotlinTypeMarker);
    }

    public boolean isIntegerLiteralType(SimpleTypeMarker simpleTypeMarker) {
        Intrinsics.checkParameterIsNotNull(simpleTypeMarker, "$this$isIntegerLiteralType");
        return ClassicTypeSystemContext.DefaultImpls.isIntegerLiteralType(this, simpleTypeMarker);
    }

    public boolean isIntegerLiteralTypeConstructor(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkParameterIsNotNull(typeConstructorMarker, "$this$isIntegerLiteralTypeConstructor");
        return ClassicTypeSystemContext.DefaultImpls.isIntegerLiteralTypeConstructor(this, typeConstructorMarker);
    }

    public boolean isIntersection(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkParameterIsNotNull(typeConstructorMarker, "$this$isIntersection");
        return ClassicTypeSystemContext.DefaultImpls.isIntersection(this, typeConstructorMarker);
    }

    public boolean isMarkedNullable(SimpleTypeMarker simpleTypeMarker) {
        Intrinsics.checkParameterIsNotNull(simpleTypeMarker, "$this$isMarkedNullable");
        return ClassicTypeSystemContext.DefaultImpls.isMarkedNullable(this, simpleTypeMarker);
    }

    public boolean isNothing(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeMarker, "$this$isNothing");
        return ClassicTypeSystemContext.DefaultImpls.isNothing(this, kotlinTypeMarker);
    }

    public boolean isNothingConstructor(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkParameterIsNotNull(typeConstructorMarker, "$this$isNothingConstructor");
        return ClassicTypeSystemContext.DefaultImpls.isNothingConstructor(this, typeConstructorMarker);
    }

    public boolean isNullableType(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeMarker, "$this$isNullableType");
        return ClassicTypeSystemContext.DefaultImpls.isNullableType(this, kotlinTypeMarker);
    }

    public boolean isSingleClassifierType(SimpleTypeMarker simpleTypeMarker) {
        Intrinsics.checkParameterIsNotNull(simpleTypeMarker, "$this$isSingleClassifierType");
        return ClassicTypeSystemContext.DefaultImpls.isSingleClassifierType(this, simpleTypeMarker);
    }

    public boolean isStarProjection(TypeArgumentMarker typeArgumentMarker) {
        Intrinsics.checkParameterIsNotNull(typeArgumentMarker, "$this$isStarProjection");
        return ClassicTypeSystemContext.DefaultImpls.isStarProjection(this, typeArgumentMarker);
    }

    public boolean isStubType(SimpleTypeMarker simpleTypeMarker) {
        Intrinsics.checkParameterIsNotNull(simpleTypeMarker, "$this$isStubType");
        return ClassicTypeSystemContext.DefaultImpls.isStubType(this, simpleTypeMarker);
    }

    public SimpleTypeMarker lowerBound(FlexibleTypeMarker flexibleTypeMarker) {
        Intrinsics.checkParameterIsNotNull(flexibleTypeMarker, "$this$lowerBound");
        return ClassicTypeSystemContext.DefaultImpls.lowerBound(this, flexibleTypeMarker);
    }

    public SimpleTypeMarker lowerBoundIfFlexible(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeMarker, "$this$lowerBoundIfFlexible");
        return ClassicTypeSystemContext.DefaultImpls.lowerBoundIfFlexible(this, kotlinTypeMarker);
    }

    public KotlinTypeMarker lowerType(CapturedTypeMarker capturedTypeMarker) {
        Intrinsics.checkParameterIsNotNull(capturedTypeMarker, "$this$lowerType");
        return ClassicTypeSystemContext.DefaultImpls.lowerType(this, capturedTypeMarker);
    }

    public int parametersCount(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkParameterIsNotNull(typeConstructorMarker, "$this$parametersCount");
        return ClassicTypeSystemContext.DefaultImpls.parametersCount(this, typeConstructorMarker);
    }

    public Collection<KotlinTypeMarker> possibleIntegerTypes(SimpleTypeMarker simpleTypeMarker) {
        Intrinsics.checkParameterIsNotNull(simpleTypeMarker, "$this$possibleIntegerTypes");
        return ClassicTypeSystemContext.DefaultImpls.possibleIntegerTypes(this, simpleTypeMarker);
    }

    public int size(TypeArgumentListMarker typeArgumentListMarker) {
        Intrinsics.checkParameterIsNotNull(typeArgumentListMarker, "$this$size");
        return ClassicTypeSystemContext.DefaultImpls.size(this, typeArgumentListMarker);
    }

    public Collection<KotlinTypeMarker> supertypes(TypeConstructorMarker typeConstructorMarker) {
        Intrinsics.checkParameterIsNotNull(typeConstructorMarker, "$this$supertypes");
        return ClassicTypeSystemContext.DefaultImpls.supertypes(this, typeConstructorMarker);
    }

    public TypeConstructorMarker typeConstructor(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeMarker, "$this$typeConstructor");
        return ClassicTypeSystemContext.DefaultImpls.typeConstructor((ClassicTypeSystemContext) this, kotlinTypeMarker);
    }

    public TypeConstructorMarker typeConstructor(SimpleTypeMarker simpleTypeMarker) {
        Intrinsics.checkParameterIsNotNull(simpleTypeMarker, "$this$typeConstructor");
        return ClassicTypeSystemContext.DefaultImpls.typeConstructor((ClassicTypeSystemContext) this, simpleTypeMarker);
    }

    public SimpleTypeMarker upperBound(FlexibleTypeMarker flexibleTypeMarker) {
        Intrinsics.checkParameterIsNotNull(flexibleTypeMarker, "$this$upperBound");
        return ClassicTypeSystemContext.DefaultImpls.upperBound(this, flexibleTypeMarker);
    }

    public SimpleTypeMarker upperBoundIfFlexible(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeMarker, "$this$upperBoundIfFlexible");
        return ClassicTypeSystemContext.DefaultImpls.upperBoundIfFlexible(this, kotlinTypeMarker);
    }

    public SimpleTypeMarker withNullability(SimpleTypeMarker simpleTypeMarker, boolean z) {
        Intrinsics.checkParameterIsNotNull(simpleTypeMarker, "$this$withNullability");
        return ClassicTypeSystemContext.DefaultImpls.withNullability(this, simpleTypeMarker, z);
    }

    public KotlinTypeMarker prepareType(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeMarker, "type");
        return NewKotlinTypeChecker.INSTANCE.transformToNewType(((KotlinType) kotlinTypeMarker).unwrap());
    }

    public boolean isErrorTypeEqualsToAnything() {
        return this.errorTypeEqualsToAnything;
    }

    public boolean areEqualTypeConstructors(TypeConstructorMarker typeConstructorMarker, TypeConstructorMarker typeConstructorMarker2) {
        Intrinsics.checkParameterIsNotNull(typeConstructorMarker, "a");
        Intrinsics.checkParameterIsNotNull(typeConstructorMarker2, "b");
        if (!(typeConstructorMarker instanceof TypeConstructor)) {
            throw new IllegalArgumentException(ClassicTypeCheckerContextKt.errorMessage(typeConstructorMarker).toString());
        } else if (typeConstructorMarker2 instanceof TypeConstructor) {
            return areEqualTypeConstructors((TypeConstructor) typeConstructorMarker, (TypeConstructor) typeConstructorMarker2);
        } else {
            throw new IllegalArgumentException(ClassicTypeCheckerContextKt.errorMessage(typeConstructorMarker2).toString());
        }
    }

    public boolean areEqualTypeConstructors(TypeConstructor typeConstructor, TypeConstructor typeConstructor2) {
        Intrinsics.checkParameterIsNotNull(typeConstructor, "a");
        Intrinsics.checkParameterIsNotNull(typeConstructor2, "b");
        if (typeConstructor instanceof IntegerLiteralTypeConstructor) {
            return ((IntegerLiteralTypeConstructor) typeConstructor).checkConstructor(typeConstructor2);
        }
        if (typeConstructor2 instanceof IntegerLiteralTypeConstructor) {
            return ((IntegerLiteralTypeConstructor) typeConstructor2).checkConstructor(typeConstructor);
        }
        return Intrinsics.areEqual((Object) typeConstructor, (Object) typeConstructor2);
    }

    public AbstractTypeCheckerContext.SupertypesPolicy.DoCustomTransform substitutionSupertypePolicy(SimpleTypeMarker simpleTypeMarker) {
        Intrinsics.checkParameterIsNotNull(simpleTypeMarker, "type");
        return Companion.classicSubstitutionSupertypePolicy(this, simpleTypeMarker);
    }

    public boolean isAllowedTypeVariable(KotlinTypeMarker kotlinTypeMarker) {
        Intrinsics.checkParameterIsNotNull(kotlinTypeMarker, "$this$isAllowedTypeVariable");
        return (kotlinTypeMarker instanceof UnwrappedType) && this.allowedTypeVariable && (((UnwrappedType) kotlinTypeMarker).getConstructor() instanceof NewTypeVariableConstructor);
    }

    /* compiled from: ClassicTypeCheckerContext.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AbstractTypeCheckerContext.SupertypesPolicy.DoCustomTransform classicSubstitutionSupertypePolicy(ClassicTypeSystemContext classicTypeSystemContext, SimpleTypeMarker simpleTypeMarker) {
            Intrinsics.checkParameterIsNotNull(classicTypeSystemContext, "$this$classicSubstitutionSupertypePolicy");
            Intrinsics.checkParameterIsNotNull(simpleTypeMarker, "type");
            if (simpleTypeMarker instanceof SimpleType) {
                return new ClassicTypeCheckerContext$Companion$classicSubstitutionSupertypePolicy$2(classicTypeSystemContext, TypeConstructorSubstitution.Companion.create((KotlinType) simpleTypeMarker).buildSubstitutor());
            }
            throw new IllegalArgumentException(ClassicTypeCheckerContextKt.errorMessage(simpleTypeMarker).toString());
        }
    }
}
