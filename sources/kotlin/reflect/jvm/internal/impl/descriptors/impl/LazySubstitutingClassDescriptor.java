package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.SubstitutingScope;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.types.ClassTypeConstructorImpl;
import kotlin.reflect.jvm.internal.impl.types.DescriptorSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;

public class LazySubstitutingClassDescriptor implements ClassDescriptor {
    private List<TypeParameterDescriptor> declaredTypeParameters;
    private TypeSubstitutor newSubstitutor;
    private final ClassDescriptor original;
    private final TypeSubstitutor originalSubstitutor;
    private TypeConstructor typeConstructor;
    private List<TypeParameterDescriptor> typeConstructorParameters;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 4 || i == 14) ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[((i == 2 || i == 4 || i == 14) ? 3 : 2)];
        if (i == 2) {
            objArr[0] = "typeArguments";
        } else if (i == 4) {
            objArr[0] = "typeSubstitution";
        } else if (i != 14) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/LazySubstitutingClassDescriptor";
        } else {
            objArr[0] = "substitutor";
        }
        switch (i) {
            case 2:
            case 4:
            case 14:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/LazySubstitutingClassDescriptor";
                break;
            case 3:
            case 5:
                objArr[1] = "getMemberScope";
                break;
            case 6:
                objArr[1] = "getUnsubstitutedMemberScope";
                break;
            case 7:
                objArr[1] = "getStaticScope";
                break;
            case 8:
                objArr[1] = "getDefaultType";
                break;
            case 9:
                objArr[1] = "getConstructors";
                break;
            case 10:
                objArr[1] = "getAnnotations";
                break;
            case 11:
                objArr[1] = "getName";
                break;
            case 12:
                objArr[1] = "getOriginal";
                break;
            case 13:
                objArr[1] = "getContainingDeclaration";
                break;
            case 15:
                objArr[1] = "substitute";
                break;
            case 16:
                objArr[1] = "getKind";
                break;
            case 17:
                objArr[1] = "getModality";
                break;
            case 18:
                objArr[1] = "getVisibility";
                break;
            case 19:
                objArr[1] = "getUnsubstitutedInnerClassesScope";
                break;
            case 20:
                objArr[1] = "getSource";
                break;
            case 21:
                objArr[1] = "getDeclaredTypeParameters";
                break;
            case 22:
                objArr[1] = "getSealedSubclasses";
                break;
            default:
                objArr[1] = "getTypeConstructor";
                break;
        }
        if (i == 2 || i == 4) {
            objArr[2] = "getMemberScope";
        } else if (i == 14) {
            objArr[2] = "substitute";
        }
        String format = String.format(str, objArr);
        throw ((i == 2 || i == 4 || i == 14) ? new IllegalArgumentException(format) : new IllegalStateException(format));
    }

    public LazySubstitutingClassDescriptor(ClassDescriptor classDescriptor, TypeSubstitutor typeSubstitutor) {
        this.original = classDescriptor;
        this.originalSubstitutor = typeSubstitutor;
    }

    private TypeSubstitutor getSubstitutor() {
        if (this.newSubstitutor == null) {
            if (this.originalSubstitutor.isEmpty()) {
                this.newSubstitutor = this.originalSubstitutor;
            } else {
                List<TypeParameterDescriptor> parameters = this.original.getTypeConstructor().getParameters();
                this.typeConstructorParameters = new ArrayList(parameters.size());
                this.newSubstitutor = DescriptorSubstitutor.substituteTypeParameters(parameters, this.originalSubstitutor.getSubstitution(), this, this.typeConstructorParameters);
                this.declaredTypeParameters = CollectionsKt.filter(this.typeConstructorParameters, new Function1<TypeParameterDescriptor, Boolean>() {
                    public Boolean invoke(TypeParameterDescriptor typeParameterDescriptor) {
                        return Boolean.valueOf(!typeParameterDescriptor.isCapturedFromOuterDeclaration());
                    }
                });
            }
        }
        return this.newSubstitutor;
    }

    public TypeConstructor getTypeConstructor() {
        TypeConstructor typeConstructor2 = this.original.getTypeConstructor();
        if (this.originalSubstitutor.isEmpty()) {
            if (typeConstructor2 == null) {
                $$$reportNull$$$0(0);
            }
            return typeConstructor2;
        }
        if (this.typeConstructor == null) {
            TypeSubstitutor substitutor = getSubstitutor();
            Collection<KotlinType> supertypes = typeConstructor2.getSupertypes();
            ArrayList arrayList = new ArrayList(supertypes.size());
            for (KotlinType substitute : supertypes) {
                arrayList.add(substitutor.substitute(substitute, Variance.INVARIANT));
            }
            this.typeConstructor = new ClassTypeConstructorImpl(this, this.typeConstructorParameters, arrayList, LockBasedStorageManager.NO_LOCKS);
        }
        TypeConstructor typeConstructor3 = this.typeConstructor;
        if (typeConstructor3 == null) {
            $$$reportNull$$$0(1);
        }
        return typeConstructor3;
    }

    public MemberScope getMemberScope(TypeSubstitution typeSubstitution) {
        if (typeSubstitution == null) {
            $$$reportNull$$$0(4);
        }
        MemberScope memberScope = this.original.getMemberScope(typeSubstitution);
        if (!this.originalSubstitutor.isEmpty()) {
            return new SubstitutingScope(memberScope, getSubstitutor());
        }
        if (memberScope == null) {
            $$$reportNull$$$0(5);
        }
        return memberScope;
    }

    public MemberScope getUnsubstitutedMemberScope() {
        MemberScope unsubstitutedMemberScope = this.original.getUnsubstitutedMemberScope();
        if (!this.originalSubstitutor.isEmpty()) {
            return new SubstitutingScope(unsubstitutedMemberScope, getSubstitutor());
        }
        if (unsubstitutedMemberScope == null) {
            $$$reportNull$$$0(6);
        }
        return unsubstitutedMemberScope;
    }

    public MemberScope getStaticScope() {
        MemberScope staticScope = this.original.getStaticScope();
        if (staticScope == null) {
            $$$reportNull$$$0(7);
        }
        return staticScope;
    }

    public SimpleType getDefaultType() {
        SimpleType simpleNotNullType = KotlinTypeFactory.simpleNotNullType(getAnnotations(), this, TypeUtils.getDefaultTypeProjections(getTypeConstructor().getParameters()));
        if (simpleNotNullType == null) {
            $$$reportNull$$$0(8);
        }
        return simpleNotNullType;
    }

    public ReceiverParameterDescriptor getThisAsReceiverParameter() {
        throw new UnsupportedOperationException();
    }

    public Collection<ClassConstructorDescriptor> getConstructors() {
        Collection<ClassConstructorDescriptor> constructors = this.original.getConstructors();
        ArrayList arrayList = new ArrayList(constructors.size());
        for (ClassConstructorDescriptor next : constructors) {
            arrayList.add(((ClassConstructorDescriptor) next.newCopyBuilder().setOriginal(next.getOriginal()).setModality(next.getModality()).setVisibility(next.getVisibility()).setKind(next.getKind()).setCopyOverrides(false).build()).substitute(getSubstitutor()));
        }
        return arrayList;
    }

    public Annotations getAnnotations() {
        Annotations annotations = this.original.getAnnotations();
        if (annotations == null) {
            $$$reportNull$$$0(10);
        }
        return annotations;
    }

    public Name getName() {
        Name name = this.original.getName();
        if (name == null) {
            $$$reportNull$$$0(11);
        }
        return name;
    }

    public ClassDescriptor getOriginal() {
        ClassDescriptor original2 = this.original.getOriginal();
        if (original2 == null) {
            $$$reportNull$$$0(12);
        }
        return original2;
    }

    public DeclarationDescriptor getContainingDeclaration() {
        DeclarationDescriptor containingDeclaration = this.original.getContainingDeclaration();
        if (containingDeclaration == null) {
            $$$reportNull$$$0(13);
        }
        return containingDeclaration;
    }

    public ClassDescriptor substitute(TypeSubstitutor typeSubstitutor) {
        if (typeSubstitutor == null) {
            $$$reportNull$$$0(14);
        }
        if (typeSubstitutor.isEmpty()) {
            return this;
        }
        return new LazySubstitutingClassDescriptor(this, TypeSubstitutor.createChainedSubstitutor(typeSubstitutor.getSubstitution(), getSubstitutor().getSubstitution()));
    }

    public ClassDescriptor getCompanionObjectDescriptor() {
        return this.original.getCompanionObjectDescriptor();
    }

    public ClassKind getKind() {
        ClassKind kind = this.original.getKind();
        if (kind == null) {
            $$$reportNull$$$0(16);
        }
        return kind;
    }

    public Modality getModality() {
        Modality modality = this.original.getModality();
        if (modality == null) {
            $$$reportNull$$$0(17);
        }
        return modality;
    }

    public Visibility getVisibility() {
        Visibility visibility = this.original.getVisibility();
        if (visibility == null) {
            $$$reportNull$$$0(18);
        }
        return visibility;
    }

    public boolean isInner() {
        return this.original.isInner();
    }

    public boolean isData() {
        return this.original.isData();
    }

    public boolean isInline() {
        return this.original.isInline();
    }

    public boolean isExternal() {
        return this.original.isExternal();
    }

    public boolean isCompanionObject() {
        return this.original.isCompanionObject();
    }

    public boolean isExpect() {
        return this.original.isExpect();
    }

    public boolean isActual() {
        return this.original.isActual();
    }

    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        return declarationDescriptorVisitor.visitClassDescriptor(this, d);
    }

    public MemberScope getUnsubstitutedInnerClassesScope() {
        MemberScope unsubstitutedInnerClassesScope = this.original.getUnsubstitutedInnerClassesScope();
        if (unsubstitutedInnerClassesScope == null) {
            $$$reportNull$$$0(19);
        }
        return unsubstitutedInnerClassesScope;
    }

    public ClassConstructorDescriptor getUnsubstitutedPrimaryConstructor() {
        return this.original.getUnsubstitutedPrimaryConstructor();
    }

    public SourceElement getSource() {
        SourceElement sourceElement = SourceElement.NO_SOURCE;
        if (sourceElement == null) {
            $$$reportNull$$$0(20);
        }
        return sourceElement;
    }

    public List<TypeParameterDescriptor> getDeclaredTypeParameters() {
        getSubstitutor();
        List<TypeParameterDescriptor> list = this.declaredTypeParameters;
        if (list == null) {
            $$$reportNull$$$0(21);
        }
        return list;
    }

    public Collection<ClassDescriptor> getSealedSubclasses() {
        Collection<ClassDescriptor> sealedSubclasses = this.original.getSealedSubclasses();
        if (sealedSubclasses == null) {
            $$$reportNull$$$0(22);
        }
        return sealedSubclasses;
    }
}
