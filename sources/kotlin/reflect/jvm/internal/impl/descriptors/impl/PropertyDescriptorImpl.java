package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import com.limpoxe.support.servicemanager.ServiceProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.FieldDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ExtensionReceiver;
import kotlin.reflect.jvm.internal.impl.types.DescriptorSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

public class PropertyDescriptorImpl extends VariableDescriptorWithInitializerImpl implements PropertyDescriptor {
    private FieldDescriptor backingField;
    private FieldDescriptor delegateField;
    /* access modifiers changed from: private */
    public ReceiverParameterDescriptor dispatchReceiverParameter;
    private ReceiverParameterDescriptor extensionReceiverParameter;
    private PropertyGetterDescriptorImpl getter;
    private final boolean isActual;
    private final boolean isConst;
    private final boolean isDelegated;
    private final boolean isExpect;
    private final boolean isExternal;
    private final CallableMemberDescriptor.Kind kind;
    private final boolean lateInit;
    private final Modality modality;
    private final PropertyDescriptor original;
    private Collection<? extends PropertyDescriptor> overriddenProperties;
    private PropertySetterDescriptor setter;
    private boolean setterProjectedOut;
    private List<TypeParameterDescriptor> typeParameters;
    private Visibility visibility;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        Throwable th;
        if (!(i == 31 || i == 32 || i == 34 || i == 35)) {
            switch (i) {
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        }
        str = "@NotNull method %s.%s must not return null";
        if (!(i == 31 || i == 32 || i == 34 || i == 35)) {
            switch (i) {
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                    break;
                default:
                    i2 = 3;
                    break;
            }
        }
        i2 = 2;
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case 8:
                objArr[0] = "annotations";
                break;
            case 2:
            case 9:
                objArr[0] = "modality";
                break;
            case 3:
            case 10:
            case 16:
                objArr[0] = "visibility";
                break;
            case 4:
            case 11:
                objArr[0] = ServiceProvider.NAME;
                break;
            case 5:
            case 12:
            case 29:
                objArr[0] = "kind";
                break;
            case 6:
            case 13:
                objArr[0] = "source";
                break;
            case 14:
                objArr[0] = "outType";
                break;
            case 15:
                objArr[0] = "typeParameters";
                break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 31:
            case 32:
            case 34:
            case 35:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl";
                break;
            case 22:
                objArr[0] = "originalSubstitutor";
                break;
            case 23:
                objArr[0] = "copyConfiguration";
                break;
            case 24:
                objArr[0] = "substitutor";
                break;
            case 25:
                objArr[0] = "accessorDescriptor";
                break;
            case 26:
                objArr[0] = "newOwner";
                break;
            case 27:
                objArr[0] = "newModality";
                break;
            case 28:
                objArr[0] = "newVisibility";
                break;
            case 30:
                objArr[0] = "newName";
                break;
            case 33:
                objArr[0] = "overriddenDescriptors";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        if (i == 31) {
            objArr[1] = "getOriginal";
        } else if (i == 32) {
            objArr[1] = "getKind";
        } else if (i == 34) {
            objArr[1] = "getOverriddenDescriptors";
        } else if (i != 35) {
            switch (i) {
                case 17:
                    objArr[1] = "getTypeParameters";
                    break;
                case 18:
                    objArr[1] = "getReturnType";
                    break;
                case 19:
                    objArr[1] = "getModality";
                    break;
                case 20:
                    objArr[1] = "getVisibility";
                    break;
                case 21:
                    objArr[1] = "getAccessors";
                    break;
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl";
                    break;
            }
        } else {
            objArr[1] = "copy";
        }
        switch (i) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                objArr[2] = "create";
                break;
            case 14:
            case 15:
                objArr[2] = "setType";
                break;
            case 16:
                objArr[2] = "setVisibility";
                break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 31:
            case 32:
            case 34:
            case 35:
                break;
            case 22:
                objArr[2] = "substitute";
                break;
            case 23:
                objArr[2] = "doSubstitute";
                break;
            case 24:
            case 25:
                objArr[2] = "getSubstitutedInitialSignatureDescriptor";
                break;
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
                objArr[2] = "createSubstitutedCopy";
                break;
            case 33:
                objArr[2] = "setOverriddenDescriptors";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (!(i == 31 || i == 32 || i == 34 || i == 35)) {
            switch (i) {
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                    break;
                default:
                    th = new IllegalArgumentException(format);
                    break;
            }
        }
        th = new IllegalStateException(format);
        throw th;
    }

    public <V> V getUserData(CallableDescriptor.UserDataKey<V> userDataKey) {
        return null;
    }

    /* JADX WARNING: type inference failed for: r13v0, types: [kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor] */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected PropertyDescriptorImpl(kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r12, kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r13, kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations r14, kotlin.reflect.jvm.internal.impl.descriptors.Modality r15, kotlin.reflect.jvm.internal.impl.descriptors.Visibility r16, boolean r17, kotlin.reflect.jvm.internal.impl.name.Name r18, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind r19, kotlin.reflect.jvm.internal.impl.descriptors.SourceElement r20, boolean r21, boolean r22, boolean r23, boolean r24, boolean r25, boolean r26) {
        /*
            r11 = this;
            r7 = r11
            r8 = r15
            r9 = r16
            r10 = r19
            if (r12 != 0) goto L_0x000c
            r0 = 0
            $$$reportNull$$$0(r0)
        L_0x000c:
            if (r14 != 0) goto L_0x0012
            r0 = 1
            $$$reportNull$$$0(r0)
        L_0x0012:
            if (r8 != 0) goto L_0x0018
            r0 = 2
            $$$reportNull$$$0(r0)
        L_0x0018:
            if (r9 != 0) goto L_0x001e
            r0 = 3
            $$$reportNull$$$0(r0)
        L_0x001e:
            if (r18 != 0) goto L_0x0024
            r0 = 4
            $$$reportNull$$$0(r0)
        L_0x0024:
            if (r10 != 0) goto L_0x002a
            r0 = 5
            $$$reportNull$$$0(r0)
        L_0x002a:
            if (r20 != 0) goto L_0x0030
            r0 = 6
            $$$reportNull$$$0(r0)
        L_0x0030:
            r4 = 0
            r0 = r11
            r1 = r12
            r2 = r14
            r3 = r18
            r5 = r17
            r6 = r20
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r0 = 0
            r7.overriddenProperties = r0
            r7.modality = r8
            r7.visibility = r9
            if (r13 != 0) goto L_0x0048
            r0 = r7
            goto L_0x0049
        L_0x0048:
            r0 = r13
        L_0x0049:
            r7.original = r0
            r7.kind = r10
            r0 = r21
            r7.lateInit = r0
            r0 = r22
            r7.isConst = r0
            r0 = r23
            r7.isExpect = r0
            r0 = r24
            r7.isActual = r0
            r0 = r25
            r7.isExternal = r0
            r0 = r26
            r7.isDelegated = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl.<init>(kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations, kotlin.reflect.jvm.internal.impl.descriptors.Modality, kotlin.reflect.jvm.internal.impl.descriptors.Visibility, boolean, kotlin.reflect.jvm.internal.impl.name.Name, kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor$Kind, kotlin.reflect.jvm.internal.impl.descriptors.SourceElement, boolean, boolean, boolean, boolean, boolean, boolean):void");
    }

    public static PropertyDescriptorImpl create(DeclarationDescriptor declarationDescriptor, Annotations annotations, Modality modality2, Visibility visibility2, boolean z, Name name, CallableMemberDescriptor.Kind kind2, SourceElement sourceElement, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(7);
        }
        if (annotations == null) {
            $$$reportNull$$$0(8);
        }
        if (modality2 == null) {
            $$$reportNull$$$0(9);
        }
        if (visibility2 == null) {
            $$$reportNull$$$0(10);
        }
        if (name == null) {
            $$$reportNull$$$0(11);
        }
        if (kind2 == null) {
            $$$reportNull$$$0(12);
        }
        if (sourceElement == null) {
            $$$reportNull$$$0(13);
        }
        return new PropertyDescriptorImpl(declarationDescriptor, (PropertyDescriptor) null, annotations, modality2, visibility2, z, name, kind2, sourceElement, z2, z3, z4, z5, z6, z7);
    }

    public void setType(KotlinType kotlinType, List<? extends TypeParameterDescriptor> list, ReceiverParameterDescriptor receiverParameterDescriptor, ReceiverParameterDescriptor receiverParameterDescriptor2) {
        if (kotlinType == null) {
            $$$reportNull$$$0(14);
        }
        if (list == null) {
            $$$reportNull$$$0(15);
        }
        setOutType(kotlinType);
        this.typeParameters = new ArrayList(list);
        this.extensionReceiverParameter = receiverParameterDescriptor2;
        this.dispatchReceiverParameter = receiverParameterDescriptor;
    }

    public void initialize(PropertyGetterDescriptorImpl propertyGetterDescriptorImpl, PropertySetterDescriptor propertySetterDescriptor) {
        initialize(propertyGetterDescriptorImpl, propertySetterDescriptor, (FieldDescriptor) null, (FieldDescriptor) null);
    }

    public void initialize(PropertyGetterDescriptorImpl propertyGetterDescriptorImpl, PropertySetterDescriptor propertySetterDescriptor, FieldDescriptor fieldDescriptor, FieldDescriptor fieldDescriptor2) {
        this.getter = propertyGetterDescriptorImpl;
        this.setter = propertySetterDescriptor;
        this.backingField = fieldDescriptor;
        this.delegateField = fieldDescriptor2;
    }

    public void setSetterProjectedOut(boolean z) {
        this.setterProjectedOut = z;
    }

    public void setVisibility(Visibility visibility2) {
        if (visibility2 == null) {
            $$$reportNull$$$0(16);
        }
        this.visibility = visibility2;
    }

    public List<TypeParameterDescriptor> getTypeParameters() {
        List<TypeParameterDescriptor> list = this.typeParameters;
        if (list == null) {
            $$$reportNull$$$0(17);
        }
        return list;
    }

    public ReceiverParameterDescriptor getExtensionReceiverParameter() {
        return this.extensionReceiverParameter;
    }

    public ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return this.dispatchReceiverParameter;
    }

    public KotlinType getReturnType() {
        KotlinType type = getType();
        if (type == null) {
            $$$reportNull$$$0(18);
        }
        return type;
    }

    public Modality getModality() {
        Modality modality2 = this.modality;
        if (modality2 == null) {
            $$$reportNull$$$0(19);
        }
        return modality2;
    }

    public Visibility getVisibility() {
        Visibility visibility2 = this.visibility;
        if (visibility2 == null) {
            $$$reportNull$$$0(20);
        }
        return visibility2;
    }

    public PropertyGetterDescriptorImpl getGetter() {
        return this.getter;
    }

    public PropertySetterDescriptor getSetter() {
        return this.setter;
    }

    public boolean isSetterProjectedOut() {
        return this.setterProjectedOut;
    }

    public boolean isLateInit() {
        return this.lateInit;
    }

    public boolean isConst() {
        return this.isConst;
    }

    public boolean isExternal() {
        return this.isExternal;
    }

    public boolean isDelegated() {
        return this.isDelegated;
    }

    public List<PropertyAccessorDescriptor> getAccessors() {
        ArrayList arrayList = new ArrayList(2);
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl = this.getter;
        if (propertyGetterDescriptorImpl != null) {
            arrayList.add(propertyGetterDescriptorImpl);
        }
        PropertySetterDescriptor propertySetterDescriptor = this.setter;
        if (propertySetterDescriptor != null) {
            arrayList.add(propertySetterDescriptor);
        }
        return arrayList;
    }

    public PropertyDescriptor substitute(TypeSubstitutor typeSubstitutor) {
        if (typeSubstitutor == null) {
            $$$reportNull$$$0(22);
        }
        if (typeSubstitutor.isEmpty()) {
            return this;
        }
        return newCopyBuilder().setSubstitution(typeSubstitutor.getSubstitution()).setOriginal(getOriginal()).build();
    }

    public class CopyConfiguration {
        /* access modifiers changed from: private */
        public boolean copyOverrides = true;
        /* access modifiers changed from: private */
        public ReceiverParameterDescriptor dispatchReceiverParameter = PropertyDescriptorImpl.this.dispatchReceiverParameter;
        /* access modifiers changed from: private */
        public CallableMemberDescriptor.Kind kind = PropertyDescriptorImpl.this.getKind();
        /* access modifiers changed from: private */
        public Modality modality = PropertyDescriptorImpl.this.getModality();
        /* access modifiers changed from: private */
        public Name name = PropertyDescriptorImpl.this.getName();
        /* access modifiers changed from: private */
        public List<TypeParameterDescriptor> newTypeParameters = null;
        /* access modifiers changed from: private */
        public PropertyDescriptor original = null;
        /* access modifiers changed from: private */
        public DeclarationDescriptor owner = PropertyDescriptorImpl.this.getContainingDeclaration();
        /* access modifiers changed from: private */
        public TypeSubstitution substitution = TypeSubstitution.EMPTY;
        /* access modifiers changed from: private */
        public Visibility visibility = PropertyDescriptorImpl.this.getVisibility();

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            int i2 = i;
            String str = (i2 == 1 || i2 == 2 || i2 == 4 || i2 == 6 || i2 == 8 || i2 == 16 || i2 == 10 || i2 == 11 || i2 == 13 || i2 == 14) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
            Object[] objArr = new Object[((i2 == 1 || i2 == 2 || i2 == 4 || i2 == 6 || i2 == 8 || i2 == 16 || i2 == 10 || i2 == 11 || i2 == 13 || i2 == 14) ? 2 : 3)];
            switch (i2) {
                case 1:
                case 2:
                case 4:
                case 6:
                case 8:
                case 10:
                case 11:
                case 13:
                case 14:
                case 16:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl$CopyConfiguration";
                    break;
                case 3:
                    objArr[0] = "modality";
                    break;
                case 5:
                    objArr[0] = "visibility";
                    break;
                case 7:
                    objArr[0] = "kind";
                    break;
                case 9:
                    objArr[0] = "typeParameters";
                    break;
                case 12:
                    objArr[0] = "substitution";
                    break;
                case 15:
                    objArr[0] = ServiceProvider.NAME;
                    break;
                default:
                    objArr[0] = "owner";
                    break;
            }
            if (i2 == 1) {
                objArr[1] = "setOwner";
            } else if (i2 == 2) {
                objArr[1] = "setOriginal";
            } else if (i2 == 4) {
                objArr[1] = "setModality";
            } else if (i2 == 6) {
                objArr[1] = "setVisibility";
            } else if (i2 == 8) {
                objArr[1] = "setKind";
            } else if (i2 == 16) {
                objArr[1] = "setName";
            } else if (i2 == 10) {
                objArr[1] = "setTypeParameters";
            } else if (i2 == 11) {
                objArr[1] = "setDispatchReceiverParameter";
            } else if (i2 == 13) {
                objArr[1] = "setSubstitution";
            } else if (i2 != 14) {
                objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/PropertyDescriptorImpl$CopyConfiguration";
            } else {
                objArr[1] = "setCopyOverrides";
            }
            switch (i2) {
                case 1:
                case 2:
                case 4:
                case 6:
                case 8:
                case 10:
                case 11:
                case 13:
                case 14:
                case 16:
                    break;
                case 3:
                    objArr[2] = "setModality";
                    break;
                case 5:
                    objArr[2] = "setVisibility";
                    break;
                case 7:
                    objArr[2] = "setKind";
                    break;
                case 9:
                    objArr[2] = "setTypeParameters";
                    break;
                case 12:
                    objArr[2] = "setSubstitution";
                    break;
                case 15:
                    objArr[2] = "setName";
                    break;
                default:
                    objArr[2] = "setOwner";
                    break;
            }
            String format = String.format(str, objArr);
            throw ((i2 == 1 || i2 == 2 || i2 == 4 || i2 == 6 || i2 == 8 || i2 == 16 || i2 == 10 || i2 == 11 || i2 == 13 || i2 == 14) ? new IllegalStateException(format) : new IllegalArgumentException(format));
        }

        public CopyConfiguration() {
        }

        public CopyConfiguration setOwner(DeclarationDescriptor declarationDescriptor) {
            if (declarationDescriptor == null) {
                $$$reportNull$$$0(0);
            }
            this.owner = declarationDescriptor;
            return this;
        }

        public CopyConfiguration setOriginal(CallableMemberDescriptor callableMemberDescriptor) {
            this.original = (PropertyDescriptor) callableMemberDescriptor;
            return this;
        }

        public CopyConfiguration setModality(Modality modality2) {
            if (modality2 == null) {
                $$$reportNull$$$0(3);
            }
            this.modality = modality2;
            return this;
        }

        public CopyConfiguration setVisibility(Visibility visibility2) {
            if (visibility2 == null) {
                $$$reportNull$$$0(5);
            }
            this.visibility = visibility2;
            return this;
        }

        public CopyConfiguration setKind(CallableMemberDescriptor.Kind kind2) {
            if (kind2 == null) {
                $$$reportNull$$$0(7);
            }
            this.kind = kind2;
            return this;
        }

        public CopyConfiguration setSubstitution(TypeSubstitution typeSubstitution) {
            if (typeSubstitution == null) {
                $$$reportNull$$$0(12);
            }
            this.substitution = typeSubstitution;
            return this;
        }

        public CopyConfiguration setCopyOverrides(boolean z) {
            this.copyOverrides = z;
            return this;
        }

        public PropertyDescriptor build() {
            return PropertyDescriptorImpl.this.doSubstitute(this);
        }

        /* access modifiers changed from: package-private */
        public PropertyGetterDescriptor getOriginalGetter() {
            PropertyDescriptor propertyDescriptor = this.original;
            if (propertyDescriptor == null) {
                return null;
            }
            return propertyDescriptor.getGetter();
        }

        /* access modifiers changed from: package-private */
        public PropertySetterDescriptor getOriginalSetter() {
            PropertyDescriptor propertyDescriptor = this.original;
            if (propertyDescriptor == null) {
                return null;
            }
            return propertyDescriptor.getSetter();
        }
    }

    public CopyConfiguration newCopyBuilder() {
        return new CopyConfiguration();
    }

    /* access modifiers changed from: protected */
    public PropertyDescriptor doSubstitute(CopyConfiguration copyConfiguration) {
        ReceiverParameterDescriptor receiverParameterDescriptor;
        ReceiverParameterDescriptorImpl receiverParameterDescriptorImpl;
        if (copyConfiguration == null) {
            $$$reportNull$$$0(23);
        }
        PropertyDescriptorImpl createSubstitutedCopy = createSubstitutedCopy(copyConfiguration.owner, copyConfiguration.modality, copyConfiguration.visibility, copyConfiguration.original, copyConfiguration.kind, copyConfiguration.name);
        List<TypeParameterDescriptor> typeParameters2 = copyConfiguration.newTypeParameters == null ? getTypeParameters() : copyConfiguration.newTypeParameters;
        ArrayList arrayList = new ArrayList(typeParameters2.size());
        TypeSubstitutor substituteTypeParameters = DescriptorSubstitutor.substituteTypeParameters(typeParameters2, copyConfiguration.substitution, createSubstitutedCopy, arrayList);
        KotlinType substitute = substituteTypeParameters.substitute(getType(), Variance.OUT_VARIANCE);
        FieldDescriptorImpl fieldDescriptorImpl = null;
        if (substitute == null) {
            return null;
        }
        ReceiverParameterDescriptor access$900 = copyConfiguration.dispatchReceiverParameter;
        if (access$900 != null) {
            receiverParameterDescriptor = access$900.substitute(substituteTypeParameters);
            if (receiverParameterDescriptor == null) {
                return null;
            }
        } else {
            receiverParameterDescriptor = null;
        }
        ReceiverParameterDescriptor receiverParameterDescriptor2 = this.extensionReceiverParameter;
        if (receiverParameterDescriptor2 != null) {
            KotlinType substitute2 = substituteTypeParameters.substitute(receiverParameterDescriptor2.getType(), Variance.IN_VARIANCE);
            if (substitute2 == null) {
                return null;
            }
            receiverParameterDescriptorImpl = new ReceiverParameterDescriptorImpl(createSubstitutedCopy, new ExtensionReceiver(createSubstitutedCopy, substitute2, this.extensionReceiverParameter.getValue()), this.extensionReceiverParameter.getAnnotations());
        } else {
            receiverParameterDescriptorImpl = null;
        }
        createSubstitutedCopy.setType(substitute, arrayList, receiverParameterDescriptor, receiverParameterDescriptorImpl);
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl = this.getter == null ? null : new PropertyGetterDescriptorImpl(createSubstitutedCopy, this.getter.getAnnotations(), copyConfiguration.modality, normalizeVisibility(this.getter.getVisibility(), copyConfiguration.kind), this.getter.isDefault(), this.getter.isExternal(), this.getter.isInline(), copyConfiguration.kind, copyConfiguration.getOriginalGetter(), SourceElement.NO_SOURCE);
        if (propertyGetterDescriptorImpl != null) {
            KotlinType returnType = this.getter.getReturnType();
            propertyGetterDescriptorImpl.setInitialSignatureDescriptor(getSubstitutedInitialSignatureDescriptor(substituteTypeParameters, this.getter));
            propertyGetterDescriptorImpl.initialize(returnType != null ? substituteTypeParameters.substitute(returnType, Variance.OUT_VARIANCE) : null);
        }
        PropertySetterDescriptorImpl propertySetterDescriptorImpl = this.setter == null ? null : new PropertySetterDescriptorImpl(createSubstitutedCopy, this.setter.getAnnotations(), copyConfiguration.modality, normalizeVisibility(this.setter.getVisibility(), copyConfiguration.kind), this.setter.isDefault(), this.setter.isExternal(), this.setter.isInline(), copyConfiguration.kind, copyConfiguration.getOriginalSetter(), SourceElement.NO_SOURCE);
        if (propertySetterDescriptorImpl != null) {
            List<ValueParameterDescriptor> substitutedValueParameters = FunctionDescriptorImpl.getSubstitutedValueParameters(propertySetterDescriptorImpl, this.setter.getValueParameters(), substituteTypeParameters, false, false, (boolean[]) null);
            if (substitutedValueParameters == null) {
                createSubstitutedCopy.setSetterProjectedOut(true);
                substitutedValueParameters = Collections.singletonList(PropertySetterDescriptorImpl.createSetterParameter(propertySetterDescriptorImpl, DescriptorUtilsKt.getBuiltIns(copyConfiguration.owner).getNothingType(), this.setter.getValueParameters().get(0).getAnnotations()));
            }
            if (substitutedValueParameters.size() == 1) {
                propertySetterDescriptorImpl.setInitialSignatureDescriptor(getSubstitutedInitialSignatureDescriptor(substituteTypeParameters, this.setter));
                propertySetterDescriptorImpl.initialize(substitutedValueParameters.get(0));
            } else {
                throw new IllegalStateException();
            }
        }
        FieldDescriptor fieldDescriptor = this.backingField;
        FieldDescriptorImpl fieldDescriptorImpl2 = fieldDescriptor == null ? null : new FieldDescriptorImpl(fieldDescriptor.getAnnotations(), createSubstitutedCopy);
        FieldDescriptor fieldDescriptor2 = this.delegateField;
        if (fieldDescriptor2 != null) {
            fieldDescriptorImpl = new FieldDescriptorImpl(fieldDescriptor2.getAnnotations(), createSubstitutedCopy);
        }
        createSubstitutedCopy.initialize(propertyGetterDescriptorImpl, propertySetterDescriptorImpl, fieldDescriptorImpl2, fieldDescriptorImpl);
        if (copyConfiguration.copyOverrides) {
            SmartSet create = SmartSet.create();
            for (PropertyDescriptor substitute3 : getOverriddenDescriptors()) {
                create.add(substitute3.substitute(substituteTypeParameters));
            }
            createSubstitutedCopy.setOverriddenDescriptors(create);
        }
        if (isConst() && this.compileTimeInitializer != null) {
            createSubstitutedCopy.setCompileTimeInitializer(this.compileTimeInitializer);
        }
        return createSubstitutedCopy;
    }

    private static Visibility normalizeVisibility(Visibility visibility2, CallableMemberDescriptor.Kind kind2) {
        return (kind2 != CallableMemberDescriptor.Kind.FAKE_OVERRIDE || !Visibilities.isPrivate(visibility2.normalize())) ? visibility2 : Visibilities.INVISIBLE_FAKE;
    }

    private static FunctionDescriptor getSubstitutedInitialSignatureDescriptor(TypeSubstitutor typeSubstitutor, PropertyAccessorDescriptor propertyAccessorDescriptor) {
        if (typeSubstitutor == null) {
            $$$reportNull$$$0(24);
        }
        if (propertyAccessorDescriptor == null) {
            $$$reportNull$$$0(25);
        }
        if (propertyAccessorDescriptor.getInitialSignatureDescriptor() != null) {
            return propertyAccessorDescriptor.getInitialSignatureDescriptor().substitute(typeSubstitutor);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public PropertyDescriptorImpl createSubstitutedCopy(DeclarationDescriptor declarationDescriptor, Modality modality2, Visibility visibility2, PropertyDescriptor propertyDescriptor, CallableMemberDescriptor.Kind kind2, Name name) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(26);
        }
        if (modality2 == null) {
            $$$reportNull$$$0(27);
        }
        if (visibility2 == null) {
            $$$reportNull$$$0(28);
        }
        if (kind2 == null) {
            $$$reportNull$$$0(29);
        }
        if (name == null) {
            $$$reportNull$$$0(30);
        }
        return new PropertyDescriptorImpl(declarationDescriptor, propertyDescriptor, getAnnotations(), modality2, visibility2, isVar(), name, kind2, SourceElement.NO_SOURCE, isLateInit(), isConst(), isExpect(), isActual(), isExternal(), isDelegated());
    }

    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        return declarationDescriptorVisitor.visitPropertyDescriptor(this, d);
    }

    public PropertyDescriptor getOriginal() {
        PropertyDescriptor propertyDescriptor = this.original;
        PropertyDescriptor original2 = propertyDescriptor == this ? this : propertyDescriptor.getOriginal();
        if (original2 == null) {
            $$$reportNull$$$0(31);
        }
        return original2;
    }

    public CallableMemberDescriptor.Kind getKind() {
        CallableMemberDescriptor.Kind kind2 = this.kind;
        if (kind2 == null) {
            $$$reportNull$$$0(32);
        }
        return kind2;
    }

    public boolean isExpect() {
        return this.isExpect;
    }

    public boolean isActual() {
        return this.isActual;
    }

    public FieldDescriptor getBackingField() {
        return this.backingField;
    }

    public FieldDescriptor getDelegateField() {
        return this.delegateField;
    }

    public void setOverriddenDescriptors(Collection<? extends CallableMemberDescriptor> collection) {
        if (collection == null) {
            $$$reportNull$$$0(33);
        }
        this.overriddenProperties = collection;
    }

    public Collection<? extends PropertyDescriptor> getOverriddenDescriptors() {
        Collection<? extends PropertyDescriptor> collection = this.overriddenProperties;
        if (collection == null) {
            collection = Collections.emptyList();
        }
        if (collection == null) {
            $$$reportNull$$$0(34);
        }
        return collection;
    }

    public PropertyDescriptor copy(DeclarationDescriptor declarationDescriptor, Modality modality2, Visibility visibility2, CallableMemberDescriptor.Kind kind2, boolean z) {
        PropertyDescriptor build = newCopyBuilder().setOwner(declarationDescriptor).setOriginal((CallableMemberDescriptor) null).setModality(modality2).setVisibility(visibility2).setKind(kind2).setCopyOverrides(z).build();
        if (build == null) {
            $$$reportNull$$$0(35);
        }
        return build;
    }
}
