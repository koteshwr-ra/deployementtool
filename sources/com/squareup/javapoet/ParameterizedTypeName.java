package com.squareup.javapoet;

import com.alibaba.android.arouter.utils.Consts;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ParameterizedTypeName extends TypeName {
    private final ParameterizedTypeName enclosingType;
    public final ClassName rawType;
    public final List<TypeName> typeArguments;

    ParameterizedTypeName(ParameterizedTypeName parameterizedTypeName, ClassName className, List<TypeName> list) {
        this(parameterizedTypeName, className, list, new ArrayList());
    }

    private ParameterizedTypeName(ParameterizedTypeName parameterizedTypeName, ClassName className, List<TypeName> list, List<AnnotationSpec> list2) {
        super(list2);
        this.rawType = (ClassName) Util.checkNotNull(className, "rawType == null", new Object[0]);
        this.enclosingType = parameterizedTypeName;
        List<TypeName> immutableList = Util.immutableList(list);
        this.typeArguments = immutableList;
        Util.checkArgument(!immutableList.isEmpty() || parameterizedTypeName != null, "no type arguments: %s", className);
        Iterator<TypeName> it = this.typeArguments.iterator();
        while (it.hasNext()) {
            TypeName next = it.next();
            Util.checkArgument(!next.isPrimitive() && next != VOID, "invalid type parameter: %s", next);
        }
    }

    public ParameterizedTypeName annotated(List<AnnotationSpec> list) {
        return new ParameterizedTypeName(this.enclosingType, this.rawType, this.typeArguments, concatAnnotations(list));
    }

    public TypeName withoutAnnotations() {
        return new ParameterizedTypeName(this.enclosingType, this.rawType, this.typeArguments, new ArrayList());
    }

    /* access modifiers changed from: package-private */
    public CodeWriter emit(CodeWriter codeWriter) throws IOException {
        ParameterizedTypeName parameterizedTypeName = this.enclosingType;
        if (parameterizedTypeName != null) {
            parameterizedTypeName.emitAnnotations(codeWriter);
            this.enclosingType.emit(codeWriter);
            codeWriter.emit(Consts.DOT + this.rawType.simpleName());
        } else {
            this.rawType.emitAnnotations(codeWriter);
            this.rawType.emit(codeWriter);
        }
        if (!this.typeArguments.isEmpty()) {
            codeWriter.emitAndIndent("<");
            boolean z = true;
            for (TypeName next : this.typeArguments) {
                if (!z) {
                    codeWriter.emitAndIndent(", ");
                }
                next.emitAnnotations(codeWriter);
                next.emit(codeWriter);
                z = false;
            }
            codeWriter.emitAndIndent(">");
        }
        return codeWriter;
    }

    public ParameterizedTypeName nestedClass(String str) {
        Util.checkNotNull(str, "name == null", new Object[0]);
        return new ParameterizedTypeName(this, this.rawType.nestedClass(str), new ArrayList(), new ArrayList());
    }

    public ParameterizedTypeName nestedClass(String str, List<TypeName> list) {
        Util.checkNotNull(str, "name == null", new Object[0]);
        return new ParameterizedTypeName(this, this.rawType.nestedClass(str), list, new ArrayList());
    }

    public static ParameterizedTypeName get(ClassName className, TypeName... typeNameArr) {
        return new ParameterizedTypeName((ParameterizedTypeName) null, className, Arrays.asList(typeNameArr));
    }

    public static ParameterizedTypeName get(Class<?> cls, Type... typeArr) {
        return new ParameterizedTypeName((ParameterizedTypeName) null, ClassName.get(cls), list(typeArr));
    }

    public static ParameterizedTypeName get(ParameterizedType parameterizedType) {
        return get(parameterizedType, (Map<Type, TypeVariableName>) new LinkedHashMap());
    }

    static ParameterizedTypeName get(ParameterizedType parameterizedType, Map<Type, TypeVariableName> map) {
        ClassName className = ClassName.get((Class<?>) (Class) parameterizedType.getRawType());
        ParameterizedType parameterizedType2 = (!(parameterizedType.getOwnerType() instanceof ParameterizedType) || Modifier.isStatic(((Class) parameterizedType.getRawType()).getModifiers())) ? null : (ParameterizedType) parameterizedType.getOwnerType();
        List<TypeName> list = TypeName.list(parameterizedType.getActualTypeArguments(), map);
        return parameterizedType2 != null ? get(parameterizedType2, map).nestedClass(className.simpleName(), list) : new ParameterizedTypeName((ParameterizedTypeName) null, className, list);
    }
}
