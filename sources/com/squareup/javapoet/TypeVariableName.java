package com.squareup.javapoet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;

public final class TypeVariableName extends TypeName {
    public final List<TypeName> bounds;
    public final String name;

    private TypeVariableName(String str, List<TypeName> list) {
        this(str, list, new ArrayList());
    }

    private TypeVariableName(String str, List<TypeName> list, List<AnnotationSpec> list2) {
        super(list2);
        this.name = (String) Util.checkNotNull(str, "name == null", new Object[0]);
        this.bounds = list;
        Iterator<TypeName> it = list.iterator();
        while (it.hasNext()) {
            TypeName next = it.next();
            Util.checkArgument(!next.isPrimitive() && next != VOID, "invalid bound: %s", next);
        }
    }

    public TypeVariableName annotated(List<AnnotationSpec> list) {
        return new TypeVariableName(this.name, this.bounds, list);
    }

    public TypeName withoutAnnotations() {
        return new TypeVariableName(this.name, this.bounds);
    }

    public TypeVariableName withBounds(Type... typeArr) {
        return withBounds(TypeName.list(typeArr));
    }

    public TypeVariableName withBounds(TypeName... typeNameArr) {
        return withBounds((List<TypeName>) Arrays.asList(typeNameArr));
    }

    public TypeVariableName withBounds(List<TypeName> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.bounds);
        arrayList.addAll(list);
        return new TypeVariableName(this.name, arrayList, this.annotations);
    }

    private static TypeVariableName of(String str, List<TypeName> list) {
        ArrayList arrayList = new ArrayList(list);
        arrayList.remove(OBJECT);
        return new TypeVariableName(str, Collections.unmodifiableList(arrayList));
    }

    /* access modifiers changed from: package-private */
    public CodeWriter emit(CodeWriter codeWriter) throws IOException {
        return codeWriter.emitAndIndent(this.name);
    }

    public static TypeVariableName get(String str) {
        return of(str, Collections.emptyList());
    }

    public static TypeVariableName get(String str, TypeName... typeNameArr) {
        return of(str, Arrays.asList(typeNameArr));
    }

    public static TypeVariableName get(String str, Type... typeArr) {
        return of(str, TypeName.list(typeArr));
    }

    public static TypeVariableName get(TypeVariable typeVariable) {
        return get(typeVariable.asElement());
    }

    static TypeVariableName get(TypeVariable typeVariable, Map<TypeParameterElement, TypeVariableName> map) {
        TypeParameterElement asElement = typeVariable.asElement();
        TypeVariableName typeVariableName = map.get(asElement);
        if (typeVariableName != null) {
            return typeVariableName;
        }
        ArrayList arrayList = new ArrayList();
        TypeVariableName typeVariableName2 = new TypeVariableName(asElement.getSimpleName().toString(), Collections.unmodifiableList(arrayList));
        map.put(asElement, typeVariableName2);
        for (TypeMirror typeMirror : asElement.getBounds()) {
            arrayList.add(TypeName.get(typeMirror, map));
        }
        arrayList.remove(OBJECT);
        return typeVariableName2;
    }

    public static TypeVariableName get(TypeParameterElement typeParameterElement) {
        String obj = typeParameterElement.getSimpleName().toString();
        List<TypeMirror> bounds2 = typeParameterElement.getBounds();
        ArrayList arrayList = new ArrayList();
        for (TypeMirror typeMirror : bounds2) {
            arrayList.add(TypeName.get(typeMirror));
        }
        return of(obj, arrayList);
    }

    public static TypeVariableName get(java.lang.reflect.TypeVariable<?> typeVariable) {
        return get(typeVariable, (Map<Type, TypeVariableName>) new LinkedHashMap());
    }

    static TypeVariableName get(java.lang.reflect.TypeVariable<?> typeVariable, Map<Type, TypeVariableName> map) {
        TypeVariableName typeVariableName = map.get(typeVariable);
        if (typeVariableName != null) {
            return typeVariableName;
        }
        ArrayList arrayList = new ArrayList();
        TypeVariableName typeVariableName2 = new TypeVariableName(typeVariable.getName(), Collections.unmodifiableList(arrayList));
        map.put(typeVariable, typeVariableName2);
        for (Type type : typeVariable.getBounds()) {
            arrayList.add(TypeName.get(type, map));
        }
        arrayList.remove(OBJECT);
        return typeVariableName2;
    }
}
