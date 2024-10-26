package com.google.auto.common;

import com.google.common.base.Equivalence;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.NullType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.TypeVisitor;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.SimpleElementVisitor6;
import javax.lang.model.util.SimpleTypeVisitor6;
import javax.lang.model.util.Types;
import org.apache.log4j.spi.Configurator;

public final class MoreTypes {
    private static final TypeVisitor<Boolean, EqualVisitorParam> EQUAL_VISITOR = new SimpleTypeVisitor6<Boolean, EqualVisitorParam>() {
        /* access modifiers changed from: protected */
        public Boolean defaultAction(TypeMirror typeMirror, EqualVisitorParam equalVisitorParam) {
            return Boolean.valueOf(typeMirror.getKind().equals(equalVisitorParam.type.getKind()));
        }

        public Boolean visitArray(ArrayType arrayType, EqualVisitorParam equalVisitorParam) {
            if (equalVisitorParam.type.getKind().equals(TypeKind.ARRAY)) {
                return Boolean.valueOf(MoreTypes.equal(arrayType.getComponentType(), equalVisitorParam.type.getComponentType(), equalVisitorParam.visiting));
            }
            return false;
        }

        public Boolean visitDeclared(DeclaredType declaredType, EqualVisitorParam equalVisitorParam) {
            boolean z = false;
            if (!equalVisitorParam.type.getKind().equals(TypeKind.DECLARED)) {
                return false;
            }
            DeclaredType declaredType2 = equalVisitorParam.type;
            Element asElement = declaredType.asElement();
            Element asElement2 = declaredType2.asElement();
            ComparedElements comparedElements = new ComparedElements(asElement, asElement2);
            if (equalVisitorParam.visiting.contains(comparedElements)) {
                return true;
            }
            HashSet hashSet = new HashSet(equalVisitorParam.visiting);
            hashSet.add(comparedElements);
            if (asElement.equals(asElement2) && MoreTypes.equal(declaredType.getEnclosingType(), declaredType.getEnclosingType(), hashSet) && MoreTypes.equalLists(declaredType.getTypeArguments(), declaredType2.getTypeArguments(), hashSet)) {
                z = true;
            }
            return Boolean.valueOf(z);
        }

        public Boolean visitError(ErrorType errorType, EqualVisitorParam equalVisitorParam) {
            return Boolean.valueOf(errorType.equals(equalVisitorParam.type));
        }

        public Boolean visitExecutable(ExecutableType executableType, EqualVisitorParam equalVisitorParam) {
            boolean z = false;
            if (!equalVisitorParam.type.getKind().equals(TypeKind.EXECUTABLE)) {
                return false;
            }
            ExecutableType executableType2 = equalVisitorParam.type;
            if (MoreTypes.equalLists(executableType.getParameterTypes(), executableType2.getParameterTypes(), equalVisitorParam.visiting) && MoreTypes.equal(executableType.getReturnType(), executableType2.getReturnType(), equalVisitorParam.visiting) && MoreTypes.equalLists(executableType.getThrownTypes(), executableType2.getThrownTypes(), equalVisitorParam.visiting) && MoreTypes.equalLists(executableType.getTypeVariables(), executableType2.getTypeVariables(), equalVisitorParam.visiting)) {
                z = true;
            }
            return Boolean.valueOf(z);
        }

        public Boolean visitTypeVariable(TypeVariable typeVariable, EqualVisitorParam equalVisitorParam) {
            boolean z = false;
            if (!equalVisitorParam.type.getKind().equals(TypeKind.TYPEVAR)) {
                return false;
            }
            TypeVariable typeVariable2 = equalVisitorParam.type;
            if (MoreTypes.equal(typeVariable.getUpperBound(), typeVariable2.getUpperBound(), equalVisitorParam.visiting) && MoreTypes.equal(typeVariable.getLowerBound(), typeVariable2.getLowerBound(), equalVisitorParam.visiting)) {
                z = true;
            }
            return Boolean.valueOf(z);
        }

        public Boolean visitWildcard(WildcardType wildcardType, EqualVisitorParam equalVisitorParam) {
            boolean z = false;
            if (!equalVisitorParam.type.getKind().equals(TypeKind.WILDCARD)) {
                return false;
            }
            WildcardType wildcardType2 = equalVisitorParam.type;
            if (MoreTypes.equal(wildcardType.getExtendsBound(), wildcardType2.getExtendsBound(), equalVisitorParam.visiting) && MoreTypes.equal(wildcardType.getSuperBound(), wildcardType2.getSuperBound(), equalVisitorParam.visiting)) {
                z = true;
            }
            return Boolean.valueOf(z);
        }

        public Boolean visitUnknown(TypeMirror typeMirror, EqualVisitorParam equalVisitorParam) {
            throw new UnsupportedOperationException();
        }
    };
    private static final int HASH_MULTIPLIER = 31;
    private static final int HASH_SEED = 17;
    private static final TypeVisitor<Integer, Set<Element>> HASH_VISITOR = new SimpleTypeVisitor6<Integer, Set<Element>>() {
        /* access modifiers changed from: package-private */
        public int hashKind(int i, TypeMirror typeMirror) {
            return (i * 31) + typeMirror.getKind().hashCode();
        }

        /* access modifiers changed from: protected */
        public Integer defaultAction(TypeMirror typeMirror, Set<Element> set) {
            return Integer.valueOf(hashKind(17, typeMirror));
        }

        public Integer visitArray(ArrayType arrayType, Set<Element> set) {
            return Integer.valueOf((hashKind(17, arrayType) * 31) + ((Integer) arrayType.getComponentType().accept(this, set)).intValue());
        }

        public Integer visitDeclared(DeclaredType declaredType, Set<Element> set) {
            Element asElement = declaredType.asElement();
            if (set.contains(asElement)) {
                return 0;
            }
            HashSet hashSet = new HashSet(set);
            hashSet.add(asElement);
            return Integer.valueOf((((((hashKind(17, declaredType) * 31) + declaredType.asElement().hashCode()) * 31) + ((Integer) declaredType.getEnclosingType().accept(this, hashSet)).intValue()) * 31) + MoreTypes.hashList(declaredType.getTypeArguments(), hashSet));
        }

        public Integer visitExecutable(ExecutableType executableType, Set<Element> set) {
            return Integer.valueOf((((((((hashKind(17, executableType) * 31) + MoreTypes.hashList(executableType.getParameterTypes(), set)) * 31) + ((Integer) executableType.getReturnType().accept(this, set)).intValue()) * 31) + MoreTypes.hashList(executableType.getThrownTypes(), set)) * 31) + MoreTypes.hashList(executableType.getTypeVariables(), set));
        }

        public Integer visitTypeVariable(TypeVariable typeVariable, Set<Element> set) {
            int hashKind = (hashKind(17, typeVariable) * 31) + ((Integer) typeVariable.getLowerBound().accept(this, set)).intValue();
            for (TypeMirror accept : typeVariable.asElement().getBounds()) {
                hashKind = (hashKind * 31) + ((Integer) accept.accept(this, set)).intValue();
            }
            return Integer.valueOf(hashKind);
        }

        public Integer visitWildcard(WildcardType wildcardType, Set<Element> set) {
            int i = 0;
            int hashKind = ((hashKind(17, wildcardType) * 31) + (wildcardType.getExtendsBound() == null ? 0 : ((Integer) wildcardType.getExtendsBound().accept(this, set)).intValue())) * 31;
            if (wildcardType.getSuperBound() != null) {
                i = ((Integer) wildcardType.getSuperBound().accept(this, set)).intValue();
            }
            return Integer.valueOf(hashKind + i);
        }

        public Integer visitUnknown(TypeMirror typeMirror, Set<Element> set) {
            throw new UnsupportedOperationException();
        }
    };
    private static final Equivalence<TypeMirror> TYPE_EQUIVALENCE = new Equivalence<TypeMirror>() {
        /* access modifiers changed from: protected */
        public boolean doEquivalent(TypeMirror typeMirror, TypeMirror typeMirror2) {
            return MoreTypes.equal(typeMirror, typeMirror2, ImmutableSet.of());
        }

        /* access modifiers changed from: protected */
        public int doHash(TypeMirror typeMirror) {
            return MoreTypes.hash(typeMirror, ImmutableSet.of());
        }
    };

    public static Equivalence<TypeMirror> equivalence() {
        return TYPE_EQUIVALENCE;
    }

    private static final class EqualVisitorParam {
        TypeMirror type;
        Set<ComparedElements> visiting;

        private EqualVisitorParam() {
        }
    }

    private static class ComparedElements {
        final Element a;
        final Element b;

        ComparedElements(Element element, Element element2) {
            this.a = element;
            this.b = element2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ComparedElements)) {
                return false;
            }
            ComparedElements comparedElements = (ComparedElements) obj;
            if (!this.a.equals(comparedElements.a) || !this.b.equals(comparedElements.b)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.a.hashCode() * 31) + this.b.hashCode();
        }
    }

    /* access modifiers changed from: private */
    public static boolean equal(TypeMirror typeMirror, TypeMirror typeMirror2, Set<ComparedElements> set) {
        if (Objects.equal(typeMirror, typeMirror2) && !(typeMirror instanceof ExecutableType)) {
            return true;
        }
        EqualVisitorParam equalVisitorParam = new EqualVisitorParam();
        equalVisitorParam.type = typeMirror2;
        equalVisitorParam.visiting = set;
        if (typeMirror == typeMirror2) {
            return true;
        }
        if (typeMirror == null || typeMirror2 == null || !((Boolean) typeMirror.accept(EQUAL_VISITOR, equalVisitorParam)).booleanValue()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x001a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean equalLists(java.util.List<? extends javax.lang.model.type.TypeMirror> r3, java.util.List<? extends javax.lang.model.type.TypeMirror> r4, java.util.Set<com.google.auto.common.MoreTypes.ComparedElements> r5) {
        /*
            int r0 = r3.size()
            int r1 = r4.size()
            r2 = 0
            if (r0 == r1) goto L_0x000c
            return r2
        L_0x000c:
            java.util.Iterator r3 = r3.iterator()
            java.util.Iterator r4 = r4.iterator()
        L_0x0014:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0034
            boolean r0 = r4.hasNext()
            if (r0 != 0) goto L_0x0021
            return r2
        L_0x0021:
            java.lang.Object r0 = r3.next()
            javax.lang.model.type.TypeMirror r0 = (javax.lang.model.type.TypeMirror) r0
            java.lang.Object r1 = r4.next()
            javax.lang.model.type.TypeMirror r1 = (javax.lang.model.type.TypeMirror) r1
            boolean r0 = equal(r0, r1, r5)
            if (r0 != 0) goto L_0x0014
            return r2
        L_0x0034:
            boolean r3 = r3.hasNext()
            r3 = r3 ^ 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.auto.common.MoreTypes.equalLists(java.util.List, java.util.List, java.util.Set):boolean");
    }

    /* access modifiers changed from: private */
    public static int hashList(List<? extends TypeMirror> list, Set<Element> set) {
        int i = 17;
        for (TypeMirror hash : list) {
            i = (i * 31) + hash(hash, set);
        }
        return i;
    }

    /* access modifiers changed from: private */
    public static int hash(TypeMirror typeMirror, Set<Element> set) {
        if (typeMirror == null) {
            return 0;
        }
        return ((Integer) typeMirror.accept(HASH_VISITOR, set)).intValue();
    }

    public static ImmutableSet<TypeElement> referencedTypes(TypeMirror typeMirror) {
        Preconditions.checkNotNull(typeMirror);
        ImmutableSet.Builder builder = ImmutableSet.builder();
        typeMirror.accept(new SimpleTypeVisitor6<Void, ImmutableSet.Builder<TypeElement>>() {
            public Void visitArray(ArrayType arrayType, ImmutableSet.Builder<TypeElement> builder) {
                arrayType.getComponentType().accept(this, builder);
                return null;
            }

            public Void visitDeclared(DeclaredType declaredType, ImmutableSet.Builder<TypeElement> builder) {
                builder.add((Object) MoreElements.asType(declaredType.asElement()));
                for (TypeMirror accept : declaredType.getTypeArguments()) {
                    accept.accept(this, builder);
                }
                return null;
            }

            public Void visitTypeVariable(TypeVariable typeVariable, ImmutableSet.Builder<TypeElement> builder) {
                typeVariable.getLowerBound().accept(this, builder);
                typeVariable.getUpperBound().accept(this, builder);
                return null;
            }

            public Void visitWildcard(WildcardType wildcardType, ImmutableSet.Builder<TypeElement> builder) {
                TypeMirror extendsBound = wildcardType.getExtendsBound();
                if (extendsBound != null) {
                    extendsBound.accept(this, builder);
                }
                TypeMirror superBound = wildcardType.getSuperBound();
                if (superBound == null) {
                    return null;
                }
                superBound.accept(this, builder);
                return null;
            }
        }, builder);
        return builder.build();
    }

    public static TypeElement asTypeElement(Types types, TypeMirror typeMirror) {
        Preconditions.checkNotNull(types);
        Preconditions.checkNotNull(typeMirror);
        Element asElement = types.asElement(typeMirror);
        Preconditions.checkArgument(asElement != null);
        return (TypeElement) asElement.accept(new SimpleElementVisitor6<TypeElement, Void>() {
            public TypeElement visitType(TypeElement typeElement, Void voidR) {
                return typeElement;
            }

            /* access modifiers changed from: protected */
            public TypeElement defaultAction(Element element, Void voidR) {
                throw new IllegalArgumentException();
            }
        }, (Object) null);
    }

    public static ImmutableSet<TypeElement> asTypeElements(Types types, Iterable<? extends TypeMirror> iterable) {
        Preconditions.checkNotNull(types);
        Preconditions.checkNotNull(iterable);
        ImmutableSet.Builder builder = ImmutableSet.builder();
        for (TypeMirror asTypeElement : iterable) {
            builder.add((Object) asTypeElement(types, asTypeElement));
        }
        return builder.build();
    }

    public static ArrayType asArray(TypeMirror typeMirror) {
        return (ArrayType) typeMirror.accept(new CastingTypeVisitor<ArrayType>() {
            public ArrayType visitArray(ArrayType arrayType, String str) {
                return arrayType;
            }
        }, "primitive array");
    }

    public static DeclaredType asDeclared(TypeMirror typeMirror) {
        return (DeclaredType) typeMirror.accept(new CastingTypeVisitor<DeclaredType>() {
            public DeclaredType visitDeclared(DeclaredType declaredType, String str) {
                return declaredType;
            }
        }, "declared type");
    }

    public static ErrorType asError(TypeMirror typeMirror) {
        return (ErrorType) typeMirror.accept(new CastingTypeVisitor<ErrorType>() {
            public ErrorType visitError(ErrorType errorType, String str) {
                return errorType;
            }
        }, "error type");
    }

    public static ExecutableType asExecutable(TypeMirror typeMirror) {
        return (ExecutableType) typeMirror.accept(new CastingTypeVisitor<ExecutableType>() {
            public ExecutableType visitExecutable(ExecutableType executableType, String str) {
                return executableType;
            }
        }, "executable type");
    }

    public static NoType asNoType(TypeMirror typeMirror) {
        return (NoType) typeMirror.accept(new CastingTypeVisitor<NoType>() {
            public NoType visitNoType(NoType noType, String str) {
                return noType;
            }
        }, "non-type");
    }

    public static NullType asNullType(TypeMirror typeMirror) {
        return (NullType) typeMirror.accept(new CastingTypeVisitor<NullType>() {
            public NullType visitNull(NullType nullType, String str) {
                return nullType;
            }
        }, Configurator.NULL);
    }

    public static PrimitiveType asPrimitiveType(TypeMirror typeMirror) {
        return (PrimitiveType) typeMirror.accept(new CastingTypeVisitor<PrimitiveType>() {
            public PrimitiveType visitPrimitive(PrimitiveType primitiveType, String str) {
                return primitiveType;
            }
        }, "primitive type");
    }

    public static TypeVariable asTypeVariable(TypeMirror typeMirror) {
        return (TypeVariable) typeMirror.accept(new CastingTypeVisitor<TypeVariable>() {
            public TypeVariable visitTypeVariable(TypeVariable typeVariable, String str) {
                return typeVariable;
            }
        }, "type variable");
    }

    public static WildcardType asWildcard(WildcardType wildcardType) {
        return (WildcardType) wildcardType.accept(new CastingTypeVisitor<WildcardType>() {
            public WildcardType visitWildcard(WildcardType wildcardType, String str) {
                return wildcardType;
            }
        }, "wildcard type");
    }

    public static boolean isTypeOf(final Class<?> cls, TypeMirror typeMirror) {
        Preconditions.checkNotNull(cls);
        return ((Boolean) typeMirror.accept(new SimpleTypeVisitor6<Boolean, Void>() {
            /* access modifiers changed from: protected */
            public Boolean defaultAction(TypeMirror typeMirror, Void voidR) {
                throw new IllegalArgumentException(typeMirror + " cannot be represented as a Class<?>.");
            }

            public Boolean visitNoType(NoType noType, Void voidR) {
                if (noType.getKind().equals(TypeKind.VOID)) {
                    return Boolean.valueOf(cls.equals(Void.TYPE));
                }
                throw new IllegalArgumentException(noType + " cannot be represented as a Class<?>.");
            }

            public Boolean visitPrimitive(PrimitiveType primitiveType, Void voidR) {
                switch (AnonymousClass16.$SwitchMap$javax$lang$model$type$TypeKind[primitiveType.getKind().ordinal()]) {
                    case 1:
                        return Boolean.valueOf(cls.equals(Boolean.TYPE));
                    case 2:
                        return Boolean.valueOf(cls.equals(Byte.TYPE));
                    case 3:
                        return Boolean.valueOf(cls.equals(Character.TYPE));
                    case 4:
                        return Boolean.valueOf(cls.equals(Double.TYPE));
                    case 5:
                        return Boolean.valueOf(cls.equals(Float.TYPE));
                    case 6:
                        return Boolean.valueOf(cls.equals(Integer.TYPE));
                    case 7:
                        return Boolean.valueOf(cls.equals(Long.TYPE));
                    case 8:
                        return Boolean.valueOf(cls.equals(Short.TYPE));
                    default:
                        throw new IllegalArgumentException(primitiveType + " cannot be represented as a Class<?>.");
                }
            }

            public Boolean visitArray(ArrayType arrayType, Void voidR) {
                return Boolean.valueOf(cls.isArray() && MoreTypes.isTypeOf(cls.getComponentType(), arrayType.getComponentType()));
            }

            public Boolean visitDeclared(DeclaredType declaredType, Void voidR) {
                try {
                    return Boolean.valueOf(MoreElements.asType(declaredType.asElement()).getQualifiedName().contentEquals(cls.getCanonicalName()));
                } catch (IllegalArgumentException unused) {
                    throw new IllegalArgumentException(declaredType + " does not represent a class or interface.");
                }
            }
        }, (Object) null)).booleanValue();
    }

    /* renamed from: com.google.auto.common.MoreTypes$16  reason: invalid class name */
    static /* synthetic */ class AnonymousClass16 {
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$type$TypeKind;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                javax.lang.model.type.TypeKind[] r0 = javax.lang.model.type.TypeKind.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$javax$lang$model$type$TypeKind = r0
                javax.lang.model.type.TypeKind r1 = javax.lang.model.type.TypeKind.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$javax$lang$model$type$TypeKind     // Catch:{ NoSuchFieldError -> 0x001d }
                javax.lang.model.type.TypeKind r1 = javax.lang.model.type.TypeKind.BYTE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$javax$lang$model$type$TypeKind     // Catch:{ NoSuchFieldError -> 0x0028 }
                javax.lang.model.type.TypeKind r1 = javax.lang.model.type.TypeKind.CHAR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$javax$lang$model$type$TypeKind     // Catch:{ NoSuchFieldError -> 0x0033 }
                javax.lang.model.type.TypeKind r1 = javax.lang.model.type.TypeKind.DOUBLE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$javax$lang$model$type$TypeKind     // Catch:{ NoSuchFieldError -> 0x003e }
                javax.lang.model.type.TypeKind r1 = javax.lang.model.type.TypeKind.FLOAT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$javax$lang$model$type$TypeKind     // Catch:{ NoSuchFieldError -> 0x0049 }
                javax.lang.model.type.TypeKind r1 = javax.lang.model.type.TypeKind.INT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$javax$lang$model$type$TypeKind     // Catch:{ NoSuchFieldError -> 0x0054 }
                javax.lang.model.type.TypeKind r1 = javax.lang.model.type.TypeKind.LONG     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$javax$lang$model$type$TypeKind     // Catch:{ NoSuchFieldError -> 0x0060 }
                javax.lang.model.type.TypeKind r1 = javax.lang.model.type.TypeKind.SHORT     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.auto.common.MoreTypes.AnonymousClass16.<clinit>():void");
        }
    }

    private static class CastingTypeVisitor<T> extends SimpleTypeVisitor6<T, String> {
        private CastingTypeVisitor() {
        }

        /* access modifiers changed from: protected */
        public T defaultAction(TypeMirror typeMirror, String str) {
            throw new IllegalArgumentException(typeMirror + " does not represent a " + str);
        }
    }

    private MoreTypes() {
    }
}
