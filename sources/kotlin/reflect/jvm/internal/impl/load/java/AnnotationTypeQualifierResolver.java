package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.NullabilityQualifierWithApplicability;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifier;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.NullabilityQualifierWithMigrationStatus;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.Jsr305State;
import kotlin.reflect.jvm.internal.impl.utils.ReportLevel;

/* compiled from: AnnotationTypeQualifierResolver.kt */
public final class AnnotationTypeQualifierResolver {
    private final boolean disabled = this.jsr305State.getDisabled();
    private final Jsr305State jsr305State;
    private final MemoizedFunctionToNullable<ClassDescriptor, AnnotationDescriptor> resolvedNicknames;

    /* compiled from: AnnotationTypeQualifierResolver.kt */
    public enum QualifierApplicabilityType {
        METHOD_RETURN_TYPE,
        VALUE_PARAMETER,
        FIELD,
        TYPE_USE
    }

    public AnnotationTypeQualifierResolver(StorageManager storageManager, Jsr305State jsr305State2) {
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(jsr305State2, "jsr305State");
        this.jsr305State = jsr305State2;
        this.resolvedNicknames = storageManager.createMemoizedFunctionWithNullableValues(new AnnotationTypeQualifierResolver$resolvedNicknames$1(this));
    }

    /* compiled from: AnnotationTypeQualifierResolver.kt */
    public static final class TypeQualifierWithApplicability {
        private final int applicability;
        private final AnnotationDescriptor typeQualifier;

        public TypeQualifierWithApplicability(AnnotationDescriptor annotationDescriptor, int i) {
            Intrinsics.checkParameterIsNotNull(annotationDescriptor, "typeQualifier");
            this.typeQualifier = annotationDescriptor;
            this.applicability = i;
        }

        public final AnnotationDescriptor component1() {
            return this.typeQualifier;
        }

        public final List<QualifierApplicabilityType> component2() {
            QualifierApplicabilityType[] values = QualifierApplicabilityType.values();
            Collection arrayList = new ArrayList();
            for (QualifierApplicabilityType qualifierApplicabilityType : values) {
                if (isApplicableTo(qualifierApplicabilityType)) {
                    arrayList.add(qualifierApplicabilityType);
                }
            }
            return (List) arrayList;
        }

        private final boolean isApplicableTo(QualifierApplicabilityType qualifierApplicabilityType) {
            return isApplicableConsideringMask(QualifierApplicabilityType.TYPE_USE) || isApplicableConsideringMask(qualifierApplicabilityType);
        }

        private final boolean isApplicableConsideringMask(QualifierApplicabilityType qualifierApplicabilityType) {
            return ((1 << qualifierApplicabilityType.ordinal()) & this.applicability) != 0;
        }
    }

    /* access modifiers changed from: private */
    public final AnnotationDescriptor computeTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (!classDescriptor.getAnnotations().hasAnnotation(AnnotationTypeQualifierResolverKt.getTYPE_QUALIFIER_NICKNAME_FQNAME())) {
            return null;
        }
        for (AnnotationDescriptor resolveTypeQualifierAnnotation : classDescriptor.getAnnotations()) {
            AnnotationDescriptor resolveTypeQualifierAnnotation2 = resolveTypeQualifierAnnotation(resolveTypeQualifierAnnotation);
            if (resolveTypeQualifierAnnotation2 != null) {
                return resolveTypeQualifierAnnotation2;
            }
        }
        return null;
    }

    private final AnnotationDescriptor resolveTypeQualifierNickname(ClassDescriptor classDescriptor) {
        if (classDescriptor.getKind() != ClassKind.ANNOTATION_CLASS) {
            return null;
        }
        return this.resolvedNicknames.invoke(classDescriptor);
    }

    public final AnnotationDescriptor resolveTypeQualifierAnnotation(AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass;
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (this.jsr305State.getDisabled() || (annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor)) == null) {
            return null;
        }
        if (AnnotationTypeQualifierResolverKt.isAnnotatedWithTypeQualifier(annotationClass)) {
            return annotationDescriptor;
        }
        return resolveTypeQualifierNickname(annotationClass);
    }

    public final NullabilityQualifierWithApplicability resolveQualifierBuiltInDefaultAnnotation(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (this.jsr305State.getDisabled()) {
            return null;
        }
        NullabilityQualifierWithApplicability nullabilityQualifierWithApplicability = AnnotationTypeQualifierResolverKt.getBUILT_IN_TYPE_QUALIFIER_DEFAULT_ANNOTATIONS().get(annotationDescriptor.getFqName());
        if (nullabilityQualifierWithApplicability == null) {
            return null;
        }
        NullabilityQualifierWithMigrationStatus component1 = nullabilityQualifierWithApplicability.component1();
        Collection<QualifierApplicabilityType> component2 = nullabilityQualifierWithApplicability.component2();
        ReportLevel resolveJsr305AnnotationState = resolveJsr305AnnotationState(annotationDescriptor);
        if (!(resolveJsr305AnnotationState != ReportLevel.IGNORE)) {
            resolveJsr305AnnotationState = null;
        }
        if (resolveJsr305AnnotationState != null) {
            return new NullabilityQualifierWithApplicability(NullabilityQualifierWithMigrationStatus.copy$default(component1, (NullabilityQualifier) null, resolveJsr305AnnotationState.isWarning(), 1, (Object) null), component2);
        }
        return null;
    }

    public final TypeQualifierWithApplicability resolveTypeQualifierDefaultAnnotation(AnnotationDescriptor annotationDescriptor) {
        ClassDescriptor annotationClass;
        Object obj;
        boolean z;
        List<QualifierApplicabilityType> list;
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        if (!this.jsr305State.getDisabled() && (annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor)) != null) {
            if (!annotationClass.getAnnotations().hasAnnotation(AnnotationTypeQualifierResolverKt.getTYPE_QUALIFIER_DEFAULT_FQNAME())) {
                annotationClass = null;
            }
            if (annotationClass != null) {
                ClassDescriptor annotationClass2 = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
                if (annotationClass2 == null) {
                    Intrinsics.throwNpe();
                }
                AnnotationDescriptor findAnnotation = annotationClass2.getAnnotations().findAnnotation(AnnotationTypeQualifierResolverKt.getTYPE_QUALIFIER_DEFAULT_FQNAME());
                if (findAnnotation == null) {
                    Intrinsics.throwNpe();
                }
                Map<Name, ConstantValue<?>> allValueArguments = findAnnotation.getAllValueArguments();
                Collection arrayList = new ArrayList();
                for (Map.Entry next : allValueArguments.entrySet()) {
                    ConstantValue constantValue = (ConstantValue) next.getValue();
                    if (Intrinsics.areEqual((Object) (Name) next.getKey(), (Object) JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME)) {
                        list = mapConstantToQualifierApplicabilityTypes(constantValue);
                    } else {
                        list = CollectionsKt.emptyList();
                    }
                    CollectionsKt.addAll(arrayList, list);
                }
                int i = 0;
                for (QualifierApplicabilityType ordinal : (List) arrayList) {
                    i |= 1 << ordinal.ordinal();
                }
                Iterator it = annotationClass.getAnnotations().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (resolveTypeQualifierAnnotation((AnnotationDescriptor) obj) != null) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (z) {
                        break;
                    }
                }
                AnnotationDescriptor annotationDescriptor2 = (AnnotationDescriptor) obj;
                if (annotationDescriptor2 != null) {
                    return new TypeQualifierWithApplicability(annotationDescriptor2, i);
                }
            }
        }
        return null;
    }

    public final ReportLevel resolveJsr305AnnotationState(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        ReportLevel resolveJsr305CustomState = resolveJsr305CustomState(annotationDescriptor);
        if (resolveJsr305CustomState != null) {
            return resolveJsr305CustomState;
        }
        return this.jsr305State.getGlobal();
    }

    public final ReportLevel resolveJsr305CustomState(AnnotationDescriptor annotationDescriptor) {
        Intrinsics.checkParameterIsNotNull(annotationDescriptor, "annotationDescriptor");
        Map<String, ReportLevel> user = this.jsr305State.getUser();
        FqName fqName = annotationDescriptor.getFqName();
        ReportLevel reportLevel = user.get(fqName != null ? fqName.asString() : null);
        if (reportLevel != null) {
            return reportLevel;
        }
        ClassDescriptor annotationClass = DescriptorUtilsKt.getAnnotationClass(annotationDescriptor);
        if (annotationClass != null) {
            return migrationAnnotationStatus(annotationClass);
        }
        return null;
    }

    private final ReportLevel migrationAnnotationStatus(ClassDescriptor classDescriptor) {
        AnnotationDescriptor findAnnotation = classDescriptor.getAnnotations().findAnnotation(AnnotationTypeQualifierResolverKt.getMIGRATION_ANNOTATION_FQNAME());
        ConstantValue<?> firstArgument = findAnnotation != null ? DescriptorUtilsKt.firstArgument(findAnnotation) : null;
        if (!(firstArgument instanceof EnumValue)) {
            firstArgument = null;
        }
        EnumValue enumValue = (EnumValue) firstArgument;
        if (enumValue == null) {
            return null;
        }
        ReportLevel migration = this.jsr305State.getMigration();
        if (migration != null) {
            return migration;
        }
        String asString = enumValue.getEnumEntryName().asString();
        int hashCode = asString.hashCode();
        if (hashCode != -2137067054) {
            if (hashCode != -1838656823) {
                if (hashCode == 2656902 && asString.equals("WARN")) {
                    return ReportLevel.WARN;
                }
                return null;
            } else if (asString.equals("STRICT")) {
                return ReportLevel.STRICT;
            } else {
                return null;
            }
        } else if (asString.equals("IGNORE")) {
            return ReportLevel.IGNORE;
        } else {
            return null;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType> mapConstantToQualifierApplicabilityTypes(kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue<?> r3) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue
            if (r0 == 0) goto L_0x0030
            kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue r3 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue) r3
            java.lang.Object r3 = r3.getValue()
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r3 = r3.iterator()
        L_0x0017:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto L_0x002d
            java.lang.Object r1 = r3.next()
            kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue r1 = (kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue) r1
            java.util.List r1 = r2.mapConstantToQualifierApplicabilityTypes(r1)
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            kotlin.collections.CollectionsKt.addAll(r0, r1)
            goto L_0x0017
        L_0x002d:
            java.util.List r0 = (java.util.List) r0
            goto L_0x007c
        L_0x0030:
            boolean r0 = r3 instanceof kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue
            if (r0 == 0) goto L_0x0078
            kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue r3 = (kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue) r3
            kotlin.reflect.jvm.internal.impl.name.Name r3 = r3.getEnumEntryName()
            java.lang.String r3 = r3.getIdentifier()
            int r0 = r3.hashCode()
            switch(r0) {
                case -2024225567: goto L_0x0067;
                case 66889946: goto L_0x005c;
                case 107598562: goto L_0x0051;
                case 446088073: goto L_0x0046;
                default: goto L_0x0045;
            }
        L_0x0045:
            goto L_0x0072
        L_0x0046:
            java.lang.String r0 = "PARAMETER"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0072
            kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver$QualifierApplicabilityType r3 = kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType.VALUE_PARAMETER
            goto L_0x0073
        L_0x0051:
            java.lang.String r0 = "TYPE_USE"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0072
            kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver$QualifierApplicabilityType r3 = kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType.TYPE_USE
            goto L_0x0073
        L_0x005c:
            java.lang.String r0 = "FIELD"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0072
            kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver$QualifierApplicabilityType r3 = kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType.FIELD
            goto L_0x0073
        L_0x0067:
            java.lang.String r0 = "METHOD"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0072
            kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver$QualifierApplicabilityType r3 = kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.QualifierApplicabilityType.METHOD_RETURN_TYPE
            goto L_0x0073
        L_0x0072:
            r3 = 0
        L_0x0073:
            java.util.List r0 = kotlin.collections.CollectionsKt.listOfNotNull(r3)
            goto L_0x007c
        L_0x0078:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L_0x007c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.java.AnnotationTypeQualifierResolver.mapConstantToQualifierApplicabilityTypes(kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue):java.util.List");
    }

    public final boolean getDisabled() {
        return this.disabled;
    }
}
