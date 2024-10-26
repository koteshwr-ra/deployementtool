package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.TypeEnhancementKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: typeSignatureMapping.kt */
public final class TypeSignatureMappingKt {
    private static final <T> T boxTypeIfNeeded(JvmTypeFactory<T> jvmTypeFactory, T t, boolean z) {
        return z ? jvmTypeFactory.boxType(t) : t;
    }

    public static /* synthetic */ Object mapType$default(KotlinType kotlinType, JvmTypeFactory jvmTypeFactory, TypeMappingMode typeMappingMode, TypeMappingConfiguration typeMappingConfiguration, JvmDescriptorTypeWriter jvmDescriptorTypeWriter, Function3<Object, Object, Object, Unit> function3, boolean z, int i, Object obj) {
        if ((i & 32) != 0) {
            function3 = FunctionsKt.getDO_NOTHING_3();
        }
        return mapType(kotlinType, jvmTypeFactory, typeMappingMode, typeMappingConfiguration, jvmDescriptorTypeWriter, function3, z);
    }

    /* JADX WARNING: type inference failed for: r13v0, types: [kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingConfiguration<? extends T>, java.lang.Object, kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingConfiguration] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> T mapType(kotlin.reflect.jvm.internal.impl.types.KotlinType r10, kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory<T> r11, kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode r12, kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingConfiguration<? extends T> r13, kotlin.reflect.jvm.internal.impl.load.kotlin.JvmDescriptorTypeWriter<T> r14, kotlin.jvm.functions.Function3<? super kotlin.reflect.jvm.internal.impl.types.KotlinType, ? super T, ? super kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode, kotlin.Unit> r15, boolean r16) {
        /*
            r0 = r10
            r7 = r11
            r2 = r12
            r3 = r13
            r8 = r14
            r5 = r15
            java.lang.String r1 = "kotlinType"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r1)
            java.lang.String r1 = "factory"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r1)
            java.lang.String r1 = "mode"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r1)
            java.lang.String r1 = "typeMappingConfiguration"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r13, r1)
            java.lang.String r1 = "writeGenericType"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r15, r1)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = r13.preprocessType(r10)
            if (r1 == 0) goto L_0x0032
            r0 = r1
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            java.lang.Object r0 = mapType(r0, r1, r2, r3, r4, r5, r6)
            return r0
        L_0x0032:
            boolean r1 = kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt.isSuspendFunctionType(r10)
            if (r1 == 0) goto L_0x004e
            boolean r1 = r13.releaseCoroutines()
            kotlin.reflect.jvm.internal.impl.types.SimpleType r0 = kotlin.reflect.jvm.internal.impl.builtins.SuspendFunctionTypesKt.transformSuspendFunctionToRuntimeFunctionType(r10, r1)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = (kotlin.reflect.jvm.internal.impl.types.KotlinType) r0
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            java.lang.Object r0 = mapType(r0, r1, r2, r3, r4, r5, r6)
            return r0
        L_0x004e:
            java.lang.Object r1 = mapBuiltInType(r10, r11, r12)
            if (r1 == 0) goto L_0x0060
            boolean r3 = r12.getNeedPrimitiveBoxing()
            java.lang.Object r1 = boxTypeIfNeeded(r11, r1, r3)
            r15.invoke(r10, r1, r12)
            return r1
        L_0x0060:
            kotlin.reflect.jvm.internal.impl.types.TypeConstructor r1 = r10.getConstructor()
            boolean r4 = r1 instanceof kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor
            if (r4 == 0) goto L_0x0082
            kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor r1 = (kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor) r1
            java.util.Collection r0 = r1.getSupertypes()
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = r13.commonSupertype(r0)
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.replaceArgumentsWithStarProjections(r0)
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            java.lang.Object r0 = mapType(r0, r1, r2, r3, r4, r5, r6)
            return r0
        L_0x0082:
            kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor r9 = r1.getDeclarationDescriptor()
            if (r9 == 0) goto L_0x01f3
            java.lang.String r1 = "constructor.declarationD…structor of $kotlinType\")"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r1)
            r1 = r9
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r1 = (kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r1
            boolean r1 = kotlin.reflect.jvm.internal.impl.types.ErrorUtils.isError(r1)
            java.lang.String r4 = "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor"
            if (r1 == 0) goto L_0x00b1
            java.lang.String r1 = "error/NonExistentClass"
            java.lang.Object r1 = r11.createObjectType(r1)
            if (r9 == 0) goto L_0x00ab
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r9 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r9
            r13.processErrorType(r10, r9)
            if (r8 == 0) goto L_0x00aa
            r14.writeClass(r1)
        L_0x00aa:
            return r1
        L_0x00ab:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            r0.<init>(r4)
            throw r0
        L_0x00b1:
            boolean r1 = r9 instanceof kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
            if (r1 == 0) goto L_0x013a
            boolean r6 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isArray(r10)
            if (r6 == 0) goto L_0x013a
            java.util.List r1 = r10.getArguments()
            int r1 = r1.size()
            r4 = 1
            if (r1 != r4) goto L_0x0130
            java.util.List r0 = r10.getArguments()
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            kotlin.reflect.jvm.internal.impl.types.TypeProjection r0 = (kotlin.reflect.jvm.internal.impl.types.TypeProjection) r0
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = r0.getType()
            java.lang.String r4 = "memberProjection.type"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
            kotlin.reflect.jvm.internal.impl.types.Variance r4 = r0.getProjectionKind()
            kotlin.reflect.jvm.internal.impl.types.Variance r6 = kotlin.reflect.jvm.internal.impl.types.Variance.IN_VARIANCE
            if (r4 != r6) goto L_0x00f4
            java.lang.String r0 = "java/lang/Object"
            java.lang.Object r0 = r11.createObjectType(r0)
            if (r8 == 0) goto L_0x0116
            r14.writeArrayType()
            r14.writeClass(r0)
            r14.writeArrayEnd()
            goto L_0x0116
        L_0x00f4:
            if (r8 == 0) goto L_0x00f9
            r14.writeArrayType()
        L_0x00f9:
            kotlin.reflect.jvm.internal.impl.types.Variance r0 = r0.getProjectionKind()
            java.lang.String r4 = "memberProjection.projectionKind"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)
            kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode r2 = r12.toGenericArgumentMode(r0)
            r0 = r1
            r1 = r11
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            java.lang.Object r0 = mapType(r0, r1, r2, r3, r4, r5, r6)
            if (r8 == 0) goto L_0x0116
            r14.writeArrayEnd()
        L_0x0116:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "["
            r1.append(r2)
            java.lang.String r0 = r11.toString(r0)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.Object r0 = r11.createFromString(r0)
            return r0
        L_0x0130:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.String r1 = "arrays must have one type argument"
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x013a:
            if (r1 == 0) goto L_0x01b0
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r9 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r9
            boolean r1 = r9.isInline()
            if (r1 == 0) goto L_0x0160
            boolean r1 = r12.getNeedInlineClassWrapping()
            if (r1 != 0) goto L_0x0160
            kotlin.reflect.jvm.internal.impl.types.KotlinType r1 = computeExpandedTypeForInlineClass(r10)
            if (r1 == 0) goto L_0x0160
            kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode r2 = r12.wrapInlineClassesMode()
            r0 = r1
            r1 = r11
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            java.lang.Object r0 = mapType(r0, r1, r2, r3, r4, r5, r6)
            return r0
        L_0x0160:
            boolean r1 = r12.isForAnnotationParameter()
            if (r1 == 0) goto L_0x0171
            boolean r1 = kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns.isKClass(r9)
            if (r1 == 0) goto L_0x0171
            java.lang.Object r1 = r11.getJavaLangClassType()
            goto L_0x01ac
        L_0x0171:
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r1 = r9.getOriginal()
            java.lang.String r6 = "descriptor.original"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r6)
            java.lang.Object r1 = r13.getPredefinedTypeForClass(r1)
            if (r1 == 0) goto L_0x0181
            goto L_0x01ac
        L_0x0181:
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r1 = r9.getKind()
            kotlin.reflect.jvm.internal.impl.descriptors.ClassKind r6 = kotlin.reflect.jvm.internal.impl.descriptors.ClassKind.ENUM_ENTRY
            if (r1 != r6) goto L_0x0199
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r1 = r9.getContainingDeclaration()
            if (r1 == 0) goto L_0x0193
            r9 = r1
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r9 = (kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor) r9
            goto L_0x0199
        L_0x0193:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            r0.<init>(r4)
            throw r0
        L_0x0199:
            kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor r1 = r9.getOriginal()
            java.lang.String r4 = "enumClassIfEnumEntry.original"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
            r6 = r16
            java.lang.String r1 = computeInternalName(r1, r13, r6)
            java.lang.Object r1 = r11.createObjectType(r1)
        L_0x01ac:
            r15.invoke(r10, r1, r12)
            return r1
        L_0x01b0:
            r6 = r16
            boolean r1 = r9 instanceof kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
            if (r1 == 0) goto L_0x01da
            r0 = r9
            kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor) r0
            kotlin.reflect.jvm.internal.impl.types.KotlinType r0 = kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt.getRepresentativeUpperBound(r0)
            kotlin.jvm.functions.Function3 r5 = kotlin.reflect.jvm.internal.impl.utils.FunctionsKt.getDO_NOTHING_3()
            r4 = 0
            r1 = r11
            r2 = r12
            r3 = r13
            r6 = r16
            java.lang.Object r0 = mapType(r0, r1, r2, r3, r4, r5, r6)     // Catch:{ all -> 0x020c }
            if (r8 == 0) goto L_0x01d9
            kotlin.reflect.jvm.internal.impl.name.Name r1 = r9.getName()
            java.lang.String r2 = "descriptor.getName()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            r14.writeTypeVariable(r1, r0)
        L_0x01d9:
            return r0
        L_0x01da:
            java.lang.UnsupportedOperationException r1 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unknown type "
            r2.append(r3)
            r2.append(r10)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        L_0x01f3:
            java.lang.UnsupportedOperationException r1 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "no descriptor for type constructor of "
            r2.append(r3)
            r2.append(r10)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        L_0x020c:
            r0 = move-exception
            r1 = r0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.kotlin.TypeSignatureMappingKt.mapType(kotlin.reflect.jvm.internal.impl.types.KotlinType, kotlin.reflect.jvm.internal.impl.load.kotlin.JvmTypeFactory, kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingMode, kotlin.reflect.jvm.internal.impl.load.kotlin.TypeMappingConfiguration, kotlin.reflect.jvm.internal.impl.load.kotlin.JvmDescriptorTypeWriter, kotlin.jvm.functions.Function3, boolean):java.lang.Object");
    }

    public static final boolean hasVoidReturnType(CallableDescriptor callableDescriptor) {
        Intrinsics.checkParameterIsNotNull(callableDescriptor, "descriptor");
        if (callableDescriptor instanceof ConstructorDescriptor) {
            return true;
        }
        KotlinType returnType = callableDescriptor.getReturnType();
        if (returnType == null) {
            Intrinsics.throwNpe();
        }
        if (KotlinBuiltIns.isUnit(returnType)) {
            KotlinType returnType2 = callableDescriptor.getReturnType();
            if (returnType2 == null) {
                Intrinsics.throwNpe();
            }
            if (TypeUtils.isNullableType(returnType2) || (callableDescriptor instanceof PropertyGetterDescriptor)) {
                return false;
            }
            return true;
        }
        return false;
    }

    private static final <T> T mapBuiltInType(KotlinType kotlinType, JvmTypeFactory<T> jvmTypeFactory, TypeMappingMode typeMappingMode) {
        ClassId mapKotlinToJava;
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (!(declarationDescriptor instanceof ClassDescriptor)) {
            declarationDescriptor = null;
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) declarationDescriptor;
        if (classDescriptor != null) {
            DeclarationDescriptor declarationDescriptor2 = classDescriptor;
            PrimitiveType primitiveType = KotlinBuiltIns.getPrimitiveType(declarationDescriptor2);
            boolean z = true;
            if (primitiveType != null) {
                JvmPrimitiveType jvmPrimitiveType = JvmPrimitiveType.get(primitiveType);
                Intrinsics.checkExpressionValueIsNotNull(jvmPrimitiveType, "JvmPrimitiveType.get(primitiveType)");
                String desc = jvmPrimitiveType.getDesc();
                Intrinsics.checkExpressionValueIsNotNull(desc, "JvmPrimitiveType.get(primitiveType).desc");
                T createFromString = jvmTypeFactory.createFromString(desc);
                if (!TypeUtils.isNullableType(kotlinType) && !TypeEnhancementKt.hasEnhancedNullability(kotlinType)) {
                    z = false;
                }
                return boxTypeIfNeeded(jvmTypeFactory, createFromString, z);
            }
            PrimitiveType primitiveArrayType = KotlinBuiltIns.getPrimitiveArrayType(declarationDescriptor2);
            if (primitiveArrayType != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                JvmPrimitiveType jvmPrimitiveType2 = JvmPrimitiveType.get(primitiveArrayType);
                Intrinsics.checkExpressionValueIsNotNull(jvmPrimitiveType2, "JvmPrimitiveType.get(arrayElementType)");
                sb.append(jvmPrimitiveType2.getDesc());
                return jvmTypeFactory.createFromString(sb.toString());
            } else if (KotlinBuiltIns.isUnderKotlinPackage(declarationDescriptor2) && (mapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(DescriptorUtilsKt.getFqNameUnsafe(declarationDescriptor2))) != null) {
                if (!typeMappingMode.getKotlinCollectionsToJavaCollections()) {
                    Iterable mutabilityMappings = JavaToKotlinClassMap.INSTANCE.getMutabilityMappings();
                    if (!(mutabilityMappings instanceof Collection) || !((Collection) mutabilityMappings).isEmpty()) {
                        Iterator it = mutabilityMappings.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (Intrinsics.areEqual((Object) ((JavaToKotlinClassMap.PlatformMutabilityMapping) it.next()).getJavaClass(), (Object) mapKotlinToJava)) {
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                    z = false;
                    if (z) {
                        return null;
                    }
                }
                JvmClassName byClassId = JvmClassName.byClassId(mapKotlinToJava);
                Intrinsics.checkExpressionValueIsNotNull(byClassId, "JvmClassName.byClassId(classId)");
                String internalName = byClassId.getInternalName();
                Intrinsics.checkExpressionValueIsNotNull(internalName, "JvmClassName.byClassId(classId).internalName");
                return jvmTypeFactory.createObjectType(internalName);
            }
        }
        return null;
    }

    public static final KotlinType computeExpandedTypeForInlineClass(KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(kotlinType, "inlineClassType");
        return computeExpandedTypeInner(kotlinType, new HashSet());
    }

    public static final KotlinType computeExpandedTypeInner(KotlinType kotlinType, HashSet<ClassifierDescriptor> hashSet) {
        KotlinType kotlinType2;
        Intrinsics.checkParameterIsNotNull(kotlinType, "kotlinType");
        Intrinsics.checkParameterIsNotNull(hashSet, "visitedClassifiers");
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor != null) {
            Intrinsics.checkExpressionValueIsNotNull(declarationDescriptor, "kotlinType.constructor.d…n expected: $kotlinType\")");
            if (!hashSet.add(declarationDescriptor)) {
                return null;
            }
            if (declarationDescriptor instanceof TypeParameterDescriptor) {
                kotlinType2 = computeExpandedTypeInner(TypeUtilsKt.getRepresentativeUpperBound((TypeParameterDescriptor) declarationDescriptor), hashSet);
                if (kotlinType2 == null) {
                    return null;
                }
                if (!KotlinTypeKt.isNullable(kotlinType2) && kotlinType.isMarkedNullable()) {
                    return TypeUtilsKt.makeNullable(kotlinType2);
                }
            } else if (!(declarationDescriptor instanceof ClassDescriptor) || !((ClassDescriptor) declarationDescriptor).isInline()) {
                return kotlinType;
            } else {
                KotlinType substitutedUnderlyingType = InlineClassesUtilsKt.substitutedUnderlyingType(kotlinType);
                if (substitutedUnderlyingType == null || (kotlinType2 = computeExpandedTypeInner(substitutedUnderlyingType, hashSet)) == null) {
                    return null;
                }
                if (KotlinTypeKt.isNullable(kotlinType)) {
                    if (!KotlinTypeKt.isNullable(kotlinType2) && !KotlinBuiltIns.isPrimitiveType(kotlinType2)) {
                        return TypeUtilsKt.makeNullable(kotlinType2);
                    }
                    return kotlinType;
                }
            }
            return kotlinType2;
        }
        throw new AssertionError("Type with a declaration expected: " + kotlinType);
    }

    public static /* synthetic */ String computeInternalName$default(ClassDescriptor classDescriptor, TypeMappingConfiguration typeMappingConfiguration, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            typeMappingConfiguration = TypeMappingConfigurationImpl.INSTANCE;
        }
        return computeInternalName(classDescriptor, typeMappingConfiguration, z);
    }

    public static final String computeInternalName(ClassDescriptor classDescriptor, TypeMappingConfiguration<?> typeMappingConfiguration, boolean z) {
        Intrinsics.checkParameterIsNotNull(classDescriptor, "klass");
        Intrinsics.checkParameterIsNotNull(typeMappingConfiguration, "typeMappingConfiguration");
        DeclarationDescriptor containingDeclaration = classDescriptor.getContainingDeclaration();
        if (z) {
            containingDeclaration = getContainer(containingDeclaration);
        }
        Name safeIdentifier = SpecialNames.safeIdentifier(classDescriptor.getName());
        Intrinsics.checkExpressionValueIsNotNull(safeIdentifier, "SpecialNames.safeIdentifier(klass.name)");
        String identifier = safeIdentifier.getIdentifier();
        Intrinsics.checkExpressionValueIsNotNull(identifier, "SpecialNames.safeIdentifier(klass.name).identifier");
        if (containingDeclaration instanceof PackageFragmentDescriptor) {
            FqName fqName = ((PackageFragmentDescriptor) containingDeclaration).getFqName();
            if (fqName.isRoot()) {
                return identifier;
            }
            StringBuilder sb = new StringBuilder();
            String asString = fqName.asString();
            Intrinsics.checkExpressionValueIsNotNull(asString, "fqName.asString()");
            sb.append(StringsKt.replace$default(asString, (char) ClassUtils.PACKAGE_SEPARATOR_CHAR, '/', false, 4, (Object) null));
            sb.append('/');
            sb.append(identifier);
            return sb.toString();
        }
        ClassDescriptor classDescriptor2 = (ClassDescriptor) (!(containingDeclaration instanceof ClassDescriptor) ? null : containingDeclaration);
        if (classDescriptor2 != null) {
            String predefinedInternalNameForClass = typeMappingConfiguration.getPredefinedInternalNameForClass(classDescriptor2);
            if (predefinedInternalNameForClass == null) {
                predefinedInternalNameForClass = computeInternalName(classDescriptor2, typeMappingConfiguration, z);
            }
            return predefinedInternalNameForClass + '$' + identifier;
        }
        throw new IllegalArgumentException("Unexpected container: " + containingDeclaration + " for " + classDescriptor);
    }

    private static final DeclarationDescriptor getContainer(DeclarationDescriptor declarationDescriptor) {
        DeclarationDescriptor declarationDescriptor2 = (ClassDescriptor) (!(declarationDescriptor instanceof ClassDescriptor) ? null : declarationDescriptor);
        if (declarationDescriptor2 == null) {
            declarationDescriptor2 = (PackageFragmentDescriptor) (!(declarationDescriptor instanceof PackageFragmentDescriptor) ? null : declarationDescriptor);
        }
        DeclarationDescriptor declarationDescriptor3 = declarationDescriptor2;
        if (declarationDescriptor3 != null) {
            return declarationDescriptor3;
        }
        if (declarationDescriptor != null) {
            return getContainer(declarationDescriptor.getContainingDeclaration());
        }
        return null;
    }
}
