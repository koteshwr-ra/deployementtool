package com.squareup.javapoet;

import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.ArrayType;

public final class ArrayTypeName extends TypeName {
    public final TypeName componentType;

    private ArrayTypeName(TypeName typeName) {
        this(typeName, new ArrayList());
    }

    private ArrayTypeName(TypeName typeName, List<AnnotationSpec> list) {
        super(list);
        this.componentType = (TypeName) Util.checkNotNull(typeName, "rawType == null", new Object[0]);
    }

    public ArrayTypeName annotated(List<AnnotationSpec> list) {
        return new ArrayTypeName(this.componentType, concatAnnotations(list));
    }

    public TypeName withoutAnnotations() {
        return new ArrayTypeName(this.componentType);
    }

    /* access modifiers changed from: package-private */
    public CodeWriter emit(CodeWriter codeWriter) throws IOException {
        return codeWriter.emit("$T[]", this.componentType);
    }

    public static ArrayTypeName of(TypeName typeName) {
        return new ArrayTypeName(typeName);
    }

    public static ArrayTypeName of(Type type) {
        return of(TypeName.get(type));
    }

    public static ArrayTypeName get(ArrayType arrayType) {
        return get(arrayType, (Map<TypeParameterElement, TypeVariableName>) new LinkedHashMap());
    }

    static ArrayTypeName get(ArrayType arrayType, Map<TypeParameterElement, TypeVariableName> map) {
        return new ArrayTypeName(get(arrayType.getComponentType(), map));
    }

    public static ArrayTypeName get(GenericArrayType genericArrayType) {
        return get(genericArrayType, (Map<Type, TypeVariableName>) new LinkedHashMap());
    }

    static ArrayTypeName get(GenericArrayType genericArrayType, Map<Type, TypeVariableName> map) {
        return of(get(genericArrayType.getGenericComponentType(), map));
    }
}
