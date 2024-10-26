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
import javax.lang.model.type.WildcardType;
import org.apache.log4j.spi.LocationInfo;

public final class WildcardTypeName extends TypeName {
    public final List<TypeName> lowerBounds;
    public final List<TypeName> upperBounds;

    private WildcardTypeName(List<TypeName> list, List<TypeName> list2) {
        this(list, list2, new ArrayList());
    }

    private WildcardTypeName(List<TypeName> list, List<TypeName> list2, List<AnnotationSpec> list3) {
        super(list3);
        this.upperBounds = Util.immutableList(list);
        this.lowerBounds = Util.immutableList(list2);
        Util.checkArgument(this.upperBounds.size() == 1, "unexpected extends bounds: %s", list);
        Iterator<TypeName> it = this.upperBounds.iterator();
        while (it.hasNext()) {
            TypeName next = it.next();
            Util.checkArgument(!next.isPrimitive() && next != VOID, "invalid upper bound: %s", next);
        }
        Iterator<TypeName> it2 = this.lowerBounds.iterator();
        while (it2.hasNext()) {
            TypeName next2 = it2.next();
            Util.checkArgument(!next2.isPrimitive() && next2 != VOID, "invalid lower bound: %s", next2);
        }
    }

    public WildcardTypeName annotated(List<AnnotationSpec> list) {
        return new WildcardTypeName(this.upperBounds, this.lowerBounds, concatAnnotations(list));
    }

    public TypeName withoutAnnotations() {
        return new WildcardTypeName(this.upperBounds, this.lowerBounds);
    }

    /* access modifiers changed from: package-private */
    public CodeWriter emit(CodeWriter codeWriter) throws IOException {
        if (this.lowerBounds.size() == 1) {
            return codeWriter.emit("? super $T", this.lowerBounds.get(0));
        } else if (this.upperBounds.get(0).equals(TypeName.OBJECT)) {
            return codeWriter.emit(LocationInfo.NA);
        } else {
            return codeWriter.emit("? extends $T", this.upperBounds.get(0));
        }
    }

    public static WildcardTypeName subtypeOf(TypeName typeName) {
        return new WildcardTypeName(Arrays.asList(new TypeName[]{typeName}), Collections.emptyList());
    }

    public static WildcardTypeName subtypeOf(Type type) {
        return subtypeOf(TypeName.get(type));
    }

    public static WildcardTypeName supertypeOf(TypeName typeName) {
        return new WildcardTypeName(Arrays.asList(new TypeName[]{OBJECT}), Arrays.asList(new TypeName[]{typeName}));
    }

    public static WildcardTypeName supertypeOf(Type type) {
        return supertypeOf(TypeName.get(type));
    }

    public static TypeName get(WildcardType wildcardType) {
        return get(wildcardType, (Map<TypeParameterElement, TypeVariableName>) new LinkedHashMap());
    }

    static TypeName get(WildcardType wildcardType, Map<TypeParameterElement, TypeVariableName> map) {
        TypeMirror extendsBound = wildcardType.getExtendsBound();
        if (extendsBound != null) {
            return subtypeOf(TypeName.get(extendsBound, map));
        }
        TypeMirror superBound = wildcardType.getSuperBound();
        if (superBound == null) {
            return subtypeOf((Type) Object.class);
        }
        return supertypeOf(TypeName.get(superBound, map));
    }

    public static TypeName get(java.lang.reflect.WildcardType wildcardType) {
        return get(wildcardType, (Map<Type, TypeVariableName>) new LinkedHashMap());
    }

    static TypeName get(java.lang.reflect.WildcardType wildcardType, Map<Type, TypeVariableName> map) {
        return new WildcardTypeName(list(wildcardType.getUpperBounds(), map), list(wildcardType.getLowerBounds(), map));
    }
}
