package com.squareup.javapoet;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;

public final class ParameterSpec {
    public final List<AnnotationSpec> annotations;
    public final Set<Modifier> modifiers;
    public final String name;
    public final TypeName type;

    private ParameterSpec(Builder builder) {
        this.name = (String) Util.checkNotNull(builder.name, "name == null", new Object[0]);
        this.annotations = Util.immutableList(builder.annotations);
        this.modifiers = Util.immutableSet(builder.modifiers);
        this.type = (TypeName) Util.checkNotNull(builder.type, "type == null", new Object[0]);
    }

    public boolean hasModifier(Modifier modifier) {
        return this.modifiers.contains(modifier);
    }

    /* access modifiers changed from: package-private */
    public void emit(CodeWriter codeWriter, boolean z) throws IOException {
        codeWriter.emitAnnotations(this.annotations, true);
        codeWriter.emitModifiers(this.modifiers);
        if (z) {
            codeWriter.emit("$T... $L", TypeName.arrayComponent(this.type), this.name);
            return;
        }
        codeWriter.emit("$T $L", this.type, this.name);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return toString().equals(obj.toString());
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter();
        try {
            emit(new CodeWriter(stringWriter), false);
            return stringWriter.toString();
        } catch (IOException unused) {
            throw new AssertionError();
        }
    }

    public static Builder builder(TypeName typeName, String str, Modifier... modifierArr) {
        Util.checkNotNull(typeName, "type == null", new Object[0]);
        Util.checkArgument(SourceVersion.isName(str), "not a valid name: %s", str);
        return new Builder(typeName, str).addModifiers(modifierArr);
    }

    public static Builder builder(Type type2, String str, Modifier... modifierArr) {
        return builder(TypeName.get(type2), str, modifierArr);
    }

    public Builder toBuilder() {
        return toBuilder(this.type, this.name);
    }

    /* access modifiers changed from: package-private */
    public Builder toBuilder(TypeName typeName, String str) {
        Builder builder = new Builder(typeName, str);
        builder.annotations.addAll(this.annotations);
        builder.modifiers.addAll(this.modifiers);
        return builder;
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public final List<AnnotationSpec> annotations;
        /* access modifiers changed from: private */
        public final List<Modifier> modifiers;
        /* access modifiers changed from: private */
        public final String name;
        /* access modifiers changed from: private */
        public final TypeName type;

        private Builder(TypeName typeName, String str) {
            this.annotations = new ArrayList();
            this.modifiers = new ArrayList();
            this.type = typeName;
            this.name = str;
        }

        public Builder addAnnotations(Iterable<AnnotationSpec> iterable) {
            Util.checkArgument(iterable != null, "annotationSpecs == null", new Object[0]);
            for (AnnotationSpec add : iterable) {
                this.annotations.add(add);
            }
            return this;
        }

        public Builder addAnnotation(AnnotationSpec annotationSpec) {
            this.annotations.add(annotationSpec);
            return this;
        }

        public Builder addAnnotation(ClassName className) {
            this.annotations.add(AnnotationSpec.builder(className).build());
            return this;
        }

        public Builder addAnnotation(Class<?> cls) {
            return addAnnotation(ClassName.get(cls));
        }

        public Builder addModifiers(Modifier... modifierArr) {
            Collections.addAll(this.modifiers, modifierArr);
            return this;
        }

        public ParameterSpec build() {
            return new ParameterSpec(this);
        }
    }
}
