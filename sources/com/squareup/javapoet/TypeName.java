package com.squareup.javapoet;

import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.SimpleTypeVisitor7;
import org.apache.commons.lang3.StringUtils;

public class TypeName {
    public static final TypeName BOOLEAN = new TypeName("boolean");
    private static final ClassName BOXED_BOOLEAN = ClassName.get("java.lang", "Boolean", new String[0]);
    private static final ClassName BOXED_BYTE = ClassName.get("java.lang", "Byte", new String[0]);
    private static final ClassName BOXED_CHAR = ClassName.get("java.lang", "Character", new String[0]);
    private static final ClassName BOXED_DOUBLE = ClassName.get("java.lang", "Double", new String[0]);
    private static final ClassName BOXED_FLOAT = ClassName.get("java.lang", "Float", new String[0]);
    private static final ClassName BOXED_INT = ClassName.get("java.lang", "Integer", new String[0]);
    private static final ClassName BOXED_LONG = ClassName.get("java.lang", "Long", new String[0]);
    private static final ClassName BOXED_SHORT = ClassName.get("java.lang", "Short", new String[0]);
    private static final ClassName BOXED_VOID = ClassName.get("java.lang", "Void", new String[0]);
    public static final TypeName BYTE = new TypeName("byte");
    public static final TypeName CHAR = new TypeName("char");
    public static final TypeName DOUBLE = new TypeName("double");
    public static final TypeName FLOAT = new TypeName("float");
    public static final TypeName INT = new TypeName("int");
    public static final TypeName LONG = new TypeName("long");
    public static final ClassName OBJECT = ClassName.get("java.lang", "Object", new String[0]);
    public static final TypeName SHORT = new TypeName("short");
    public static final TypeName VOID = new TypeName("void");
    public final List<AnnotationSpec> annotations;
    private String cachedString;
    private final String keyword;

    private TypeName(String str) {
        this(str, new ArrayList());
    }

    private TypeName(String str, List<AnnotationSpec> list) {
        this.keyword = str;
        this.annotations = Util.immutableList(list);
    }

    TypeName(List<AnnotationSpec> list) {
        this((String) null, list);
    }

    public final TypeName annotated(AnnotationSpec... annotationSpecArr) {
        return annotated((List<AnnotationSpec>) Arrays.asList(annotationSpecArr));
    }

    public TypeName annotated(List<AnnotationSpec> list) {
        Util.checkNotNull(list, "annotations == null", new Object[0]);
        return new TypeName(this.keyword, concatAnnotations(list));
    }

    public TypeName withoutAnnotations() {
        return new TypeName(this.keyword);
    }

    /* access modifiers changed from: protected */
    public final List<AnnotationSpec> concatAnnotations(List<AnnotationSpec> list) {
        ArrayList arrayList = new ArrayList(this.annotations);
        arrayList.addAll(list);
        return arrayList;
    }

    public boolean isAnnotated() {
        return !this.annotations.isEmpty();
    }

    public boolean isPrimitive() {
        return (this.keyword == null || this == VOID) ? false : true;
    }

    public boolean isBoxedPrimitive() {
        return equals(BOXED_BOOLEAN) || equals(BOXED_BYTE) || equals(BOXED_SHORT) || equals(BOXED_INT) || equals(BOXED_LONG) || equals(BOXED_CHAR) || equals(BOXED_FLOAT) || equals(BOXED_DOUBLE);
    }

    public TypeName box() {
        if (this.keyword == null) {
            return this;
        }
        if (this == VOID) {
            return BOXED_VOID;
        }
        if (this == BOOLEAN) {
            return BOXED_BOOLEAN;
        }
        if (this == BYTE) {
            return BOXED_BYTE;
        }
        if (this == SHORT) {
            return BOXED_SHORT;
        }
        if (this == INT) {
            return BOXED_INT;
        }
        if (this == LONG) {
            return BOXED_LONG;
        }
        if (this == CHAR) {
            return BOXED_CHAR;
        }
        if (this == FLOAT) {
            return BOXED_FLOAT;
        }
        if (this == DOUBLE) {
            return BOXED_DOUBLE;
        }
        throw new AssertionError(this.keyword);
    }

    public TypeName unbox() {
        if (this.keyword != null) {
            return this;
        }
        if (equals(BOXED_VOID)) {
            return VOID;
        }
        if (equals(BOXED_BOOLEAN)) {
            return BOOLEAN;
        }
        if (equals(BOXED_BYTE)) {
            return BYTE;
        }
        if (equals(BOXED_SHORT)) {
            return SHORT;
        }
        if (equals(BOXED_INT)) {
            return INT;
        }
        if (equals(BOXED_LONG)) {
            return LONG;
        }
        if (equals(BOXED_CHAR)) {
            return CHAR;
        }
        if (equals(BOXED_FLOAT)) {
            return FLOAT;
        }
        if (equals(BOXED_DOUBLE)) {
            return DOUBLE;
        }
        throw new UnsupportedOperationException("cannot unbox " + this);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return toString().equals(obj.toString());
        }
        return false;
    }

    public final int hashCode() {
        return toString().hashCode();
    }

    public final String toString() {
        String str = this.cachedString;
        if (str != null) {
            return str;
        }
        try {
            StringBuilder sb = new StringBuilder();
            CodeWriter codeWriter = new CodeWriter(sb);
            emitAnnotations(codeWriter);
            emit(codeWriter);
            String sb2 = sb.toString();
            this.cachedString = sb2;
            return sb2;
        } catch (IOException unused) {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: package-private */
    public CodeWriter emit(CodeWriter codeWriter) throws IOException {
        String str = this.keyword;
        if (str != null) {
            return codeWriter.emitAndIndent(str);
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    public CodeWriter emitAnnotations(CodeWriter codeWriter) throws IOException {
        for (AnnotationSpec emit : this.annotations) {
            emit.emit(codeWriter, true);
            codeWriter.emit(StringUtils.SPACE);
        }
        return codeWriter;
    }

    public static TypeName get(TypeMirror typeMirror) {
        return get(typeMirror, (Map<TypeParameterElement, TypeVariableName>) new LinkedHashMap());
    }

    static TypeName get(TypeMirror typeMirror, final Map<TypeParameterElement, TypeVariableName> map) {
        return (TypeName) typeMirror.accept(new SimpleTypeVisitor7<TypeName, Void>() {
            public TypeName visitPrimitive(PrimitiveType primitiveType, Void voidR) {
                switch (AnonymousClass2.$SwitchMap$javax$lang$model$type$TypeKind[primitiveType.getKind().ordinal()]) {
                    case 1:
                        return TypeName.BOOLEAN;
                    case 2:
                        return TypeName.BYTE;
                    case 3:
                        return TypeName.SHORT;
                    case 4:
                        return TypeName.INT;
                    case 5:
                        return TypeName.LONG;
                    case 6:
                        return TypeName.CHAR;
                    case 7:
                        return TypeName.FLOAT;
                    case 8:
                        return TypeName.DOUBLE;
                    default:
                        throw new AssertionError();
                }
            }

            public TypeName visitDeclared(DeclaredType declaredType, Void voidR) {
                ClassName className = ClassName.get(declaredType.asElement());
                TypeMirror enclosingType = declaredType.getEnclosingType();
                TypeName typeName = (enclosingType.getKind() == TypeKind.NONE || declaredType.asElement().getModifiers().contains(Modifier.STATIC)) ? null : (TypeName) enclosingType.accept(this, (Object) null);
                if (declaredType.getTypeArguments().isEmpty() && !(typeName instanceof ParameterizedTypeName)) {
                    return className;
                }
                ArrayList arrayList = new ArrayList();
                for (TypeMirror typeMirror : declaredType.getTypeArguments()) {
                    arrayList.add(TypeName.get(typeMirror, (Map<TypeParameterElement, TypeVariableName>) map));
                }
                return typeName instanceof ParameterizedTypeName ? ((ParameterizedTypeName) typeName).nestedClass(className.simpleName(), arrayList) : new ParameterizedTypeName((ParameterizedTypeName) null, className, arrayList);
            }

            public TypeName visitError(ErrorType errorType, Void voidR) {
                return visitDeclared((DeclaredType) errorType, voidR);
            }

            public ArrayTypeName visitArray(ArrayType arrayType, Void voidR) {
                return ArrayTypeName.get(arrayType, (Map<TypeParameterElement, TypeVariableName>) map);
            }

            public TypeName visitTypeVariable(TypeVariable typeVariable, Void voidR) {
                return TypeVariableName.get(typeVariable, (Map<TypeParameterElement, TypeVariableName>) map);
            }

            public TypeName visitWildcard(WildcardType wildcardType, Void voidR) {
                return WildcardTypeName.get(wildcardType, (Map<TypeParameterElement, TypeVariableName>) map);
            }

            public TypeName visitNoType(NoType noType, Void voidR) {
                if (noType.getKind() == TypeKind.VOID) {
                    return TypeName.VOID;
                }
                return (TypeName) TypeName.super.visitUnknown(noType, voidR);
            }

            /* access modifiers changed from: protected */
            public TypeName defaultAction(TypeMirror typeMirror, Void voidR) {
                throw new IllegalArgumentException("Unexpected type mirror: " + typeMirror);
            }
        }, (Object) null);
    }

    /* renamed from: com.squareup.javapoet.TypeName$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
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
                javax.lang.model.type.TypeKind r1 = javax.lang.model.type.TypeKind.SHORT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$javax$lang$model$type$TypeKind     // Catch:{ NoSuchFieldError -> 0x0033 }
                javax.lang.model.type.TypeKind r1 = javax.lang.model.type.TypeKind.INT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$javax$lang$model$type$TypeKind     // Catch:{ NoSuchFieldError -> 0x003e }
                javax.lang.model.type.TypeKind r1 = javax.lang.model.type.TypeKind.LONG     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$javax$lang$model$type$TypeKind     // Catch:{ NoSuchFieldError -> 0x0049 }
                javax.lang.model.type.TypeKind r1 = javax.lang.model.type.TypeKind.CHAR     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$javax$lang$model$type$TypeKind     // Catch:{ NoSuchFieldError -> 0x0054 }
                javax.lang.model.type.TypeKind r1 = javax.lang.model.type.TypeKind.FLOAT     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$javax$lang$model$type$TypeKind     // Catch:{ NoSuchFieldError -> 0x0060 }
                javax.lang.model.type.TypeKind r1 = javax.lang.model.type.TypeKind.DOUBLE     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.javapoet.TypeName.AnonymousClass2.<clinit>():void");
        }
    }

    public static TypeName get(Type type) {
        return get(type, (Map<Type, TypeVariableName>) new LinkedHashMap());
    }

    static TypeName get(Type type, Map<Type, TypeVariableName> map) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (type == Void.TYPE) {
                return VOID;
            }
            if (type == Boolean.TYPE) {
                return BOOLEAN;
            }
            if (type == Byte.TYPE) {
                return BYTE;
            }
            if (type == Short.TYPE) {
                return SHORT;
            }
            if (type == Integer.TYPE) {
                return INT;
            }
            if (type == Long.TYPE) {
                return LONG;
            }
            if (type == Character.TYPE) {
                return CHAR;
            }
            if (type == Float.TYPE) {
                return FLOAT;
            }
            if (type == Double.TYPE) {
                return DOUBLE;
            }
            if (cls.isArray()) {
                return ArrayTypeName.of(get((Type) cls.getComponentType(), map));
            }
            return ClassName.get((Class<?>) cls);
        } else if (type instanceof ParameterizedType) {
            return ParameterizedTypeName.get((ParameterizedType) type, map);
        } else {
            if (type instanceof java.lang.reflect.WildcardType) {
                return WildcardTypeName.get((java.lang.reflect.WildcardType) type, map);
            }
            if (type instanceof java.lang.reflect.TypeVariable) {
                return TypeVariableName.get((java.lang.reflect.TypeVariable<?>) (java.lang.reflect.TypeVariable) type, map);
            }
            if (type instanceof GenericArrayType) {
                return ArrayTypeName.get((GenericArrayType) type, map);
            }
            throw new IllegalArgumentException("unexpected type: " + type);
        }
    }

    static List<TypeName> list(Type[] typeArr) {
        return list(typeArr, new LinkedHashMap());
    }

    static List<TypeName> list(Type[] typeArr, Map<Type, TypeVariableName> map) {
        ArrayList arrayList = new ArrayList(typeArr.length);
        for (Type type : typeArr) {
            arrayList.add(get(type, map));
        }
        return arrayList;
    }

    static TypeName arrayComponent(TypeName typeName) {
        if (typeName instanceof ArrayTypeName) {
            return ((ArrayTypeName) typeName).componentType;
        }
        return null;
    }
}
