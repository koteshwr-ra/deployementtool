package com.squareup.javapoet;

import com.squareup.javapoet.CodeBlock;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import org.apache.commons.lang3.StringUtils;

public final class TypeSpec {
    public final List<AnnotationSpec> annotations;
    public final CodeBlock anonymousTypeArguments;
    public final Map<String, TypeSpec> enumConstants;
    public final List<FieldSpec> fieldSpecs;
    public final CodeBlock initializerBlock;
    public final CodeBlock javadoc;
    public final Kind kind;
    public final List<MethodSpec> methodSpecs;
    public final Set<Modifier> modifiers;
    public final String name;
    public final List<Element> originatingElements;
    public final CodeBlock staticBlock;
    public final TypeName superclass;
    public final List<TypeName> superinterfaces;
    public final List<TypeSpec> typeSpecs;
    public final List<TypeVariableName> typeVariables;

    private TypeSpec(Builder builder) {
        this.kind = builder.kind;
        this.name = builder.name;
        this.anonymousTypeArguments = builder.anonymousTypeArguments;
        this.javadoc = builder.javadoc.build();
        this.annotations = Util.immutableList(builder.annotations);
        this.modifiers = Util.immutableSet(builder.modifiers);
        this.typeVariables = Util.immutableList(builder.typeVariables);
        this.superclass = builder.superclass;
        this.superinterfaces = Util.immutableList(builder.superinterfaces);
        this.enumConstants = Util.immutableMap(builder.enumConstants);
        this.fieldSpecs = Util.immutableList(builder.fieldSpecs);
        this.staticBlock = builder.staticBlock.build();
        this.initializerBlock = builder.initializerBlock.build();
        this.methodSpecs = Util.immutableList(builder.methodSpecs);
        this.typeSpecs = Util.immutableList(builder.typeSpecs);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(builder.originatingElements);
        for (TypeSpec typeSpec : builder.typeSpecs) {
            arrayList.addAll(typeSpec.originatingElements);
        }
        this.originatingElements = Util.immutableList(arrayList);
    }

    public boolean hasModifier(Modifier modifier) {
        return this.modifiers.contains(modifier);
    }

    public static Builder classBuilder(String str) {
        return new Builder(Kind.CLASS, (String) Util.checkNotNull(str, "name == null", new Object[0]), (CodeBlock) null);
    }

    public static Builder classBuilder(ClassName className) {
        return classBuilder(((ClassName) Util.checkNotNull(className, "className == null", new Object[0])).simpleName());
    }

    public static Builder interfaceBuilder(String str) {
        return new Builder(Kind.INTERFACE, (String) Util.checkNotNull(str, "name == null", new Object[0]), (CodeBlock) null);
    }

    public static Builder interfaceBuilder(ClassName className) {
        return interfaceBuilder(((ClassName) Util.checkNotNull(className, "className == null", new Object[0])).simpleName());
    }

    public static Builder enumBuilder(String str) {
        return new Builder(Kind.ENUM, (String) Util.checkNotNull(str, "name == null", new Object[0]), (CodeBlock) null);
    }

    public static Builder enumBuilder(ClassName className) {
        return enumBuilder(((ClassName) Util.checkNotNull(className, "className == null", new Object[0])).simpleName());
    }

    public static Builder anonymousClassBuilder(String str, Object... objArr) {
        return new Builder(Kind.CLASS, (String) null, CodeBlock.builder().add(str, objArr).build());
    }

    public static Builder annotationBuilder(String str) {
        return new Builder(Kind.ANNOTATION, (String) Util.checkNotNull(str, "name == null", new Object[0]), (CodeBlock) null);
    }

    public static Builder annotationBuilder(ClassName className) {
        return annotationBuilder(((ClassName) Util.checkNotNull(className, "className == null", new Object[0])).simpleName());
    }

    public Builder toBuilder() {
        Builder builder = new Builder(this.kind, this.name, this.anonymousTypeArguments);
        builder.javadoc.add(this.javadoc);
        builder.annotations.addAll(this.annotations);
        builder.modifiers.addAll(this.modifiers);
        builder.typeVariables.addAll(this.typeVariables);
        TypeName unused = builder.superclass = this.superclass;
        builder.superinterfaces.addAll(this.superinterfaces);
        builder.enumConstants.putAll(this.enumConstants);
        builder.fieldSpecs.addAll(this.fieldSpecs);
        builder.methodSpecs.addAll(this.methodSpecs);
        builder.typeSpecs.addAll(this.typeSpecs);
        builder.initializerBlock.add(this.initializerBlock);
        builder.staticBlock.add(this.staticBlock);
        return builder;
    }

    /* access modifiers changed from: package-private */
    public void emit(CodeWriter codeWriter, String str, Set<Modifier> set) throws IOException {
        List<TypeName> list;
        List<TypeName> list2;
        int i = codeWriter.statementLine;
        codeWriter.statementLine = -1;
        boolean z = true;
        if (str != null) {
            try {
                codeWriter.emitJavadoc(this.javadoc);
                codeWriter.emitAnnotations(this.annotations, false);
                codeWriter.emit("$L", str);
                if (!this.anonymousTypeArguments.formatParts.isEmpty()) {
                    codeWriter.emit("(");
                    codeWriter.emit(this.anonymousTypeArguments);
                    codeWriter.emit(")");
                }
                if (!this.fieldSpecs.isEmpty() || !this.methodSpecs.isEmpty() || !this.typeSpecs.isEmpty()) {
                    codeWriter.emit(" {\n");
                } else {
                    return;
                }
            } finally {
                codeWriter.statementLine = i;
            }
        } else if (this.anonymousTypeArguments != null) {
            codeWriter.emit("new $T(", !this.superinterfaces.isEmpty() ? this.superinterfaces.get(0) : this.superclass);
            codeWriter.emit(this.anonymousTypeArguments);
            codeWriter.emit(") {\n");
        } else {
            codeWriter.emitJavadoc(this.javadoc);
            codeWriter.emitAnnotations(this.annotations, false);
            codeWriter.emitModifiers(this.modifiers, Util.union(set, this.kind.asMemberModifiers));
            if (this.kind == Kind.ANNOTATION) {
                codeWriter.emit("$L $L", "@interface", this.name);
            } else {
                codeWriter.emit("$L $L", this.kind.name().toLowerCase(Locale.US), this.name);
            }
            codeWriter.emitTypeVariables(this.typeVariables);
            if (this.kind == Kind.INTERFACE) {
                list = this.superinterfaces;
                list2 = Collections.emptyList();
            } else {
                if (this.superclass.equals(ClassName.OBJECT)) {
                    list = Collections.emptyList();
                } else {
                    list = Collections.singletonList(this.superclass);
                }
                list2 = this.superinterfaces;
            }
            if (!list.isEmpty()) {
                codeWriter.emit(" extends");
                boolean z2 = true;
                for (TypeName next : list) {
                    if (!z2) {
                        codeWriter.emit(",");
                    }
                    codeWriter.emit(" $T", next);
                    z2 = false;
                }
            }
            if (!list2.isEmpty()) {
                codeWriter.emit(" implements");
                boolean z3 = true;
                for (TypeName next2 : list2) {
                    if (!z3) {
                        codeWriter.emit(",");
                    }
                    codeWriter.emit(" $T", next2);
                    z3 = false;
                }
            }
            codeWriter.emit(" {\n");
        }
        codeWriter.pushType(this);
        codeWriter.indent();
        Iterator<Map.Entry<String, TypeSpec>> it = this.enumConstants.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next3 = it.next();
            if (!z) {
                codeWriter.emit(StringUtils.LF);
            }
            ((TypeSpec) next3.getValue()).emit(codeWriter, (String) next3.getKey(), Collections.emptySet());
            if (it.hasNext()) {
                codeWriter.emit(",\n");
            } else {
                if (this.fieldSpecs.isEmpty() && this.methodSpecs.isEmpty()) {
                    if (this.typeSpecs.isEmpty()) {
                        codeWriter.emit(StringUtils.LF);
                    }
                }
                codeWriter.emit(";\n");
            }
            z = false;
        }
        for (FieldSpec next4 : this.fieldSpecs) {
            if (next4.hasModifier(Modifier.STATIC)) {
                if (!z) {
                    codeWriter.emit(StringUtils.LF);
                }
                next4.emit(codeWriter, this.kind.implicitFieldModifiers);
                z = false;
            }
        }
        if (!this.staticBlock.isEmpty()) {
            if (!z) {
                codeWriter.emit(StringUtils.LF);
            }
            codeWriter.emit(this.staticBlock);
            z = false;
        }
        for (FieldSpec next5 : this.fieldSpecs) {
            if (!next5.hasModifier(Modifier.STATIC)) {
                if (!z) {
                    codeWriter.emit(StringUtils.LF);
                }
                next5.emit(codeWriter, this.kind.implicitFieldModifiers);
                z = false;
            }
        }
        if (!this.initializerBlock.isEmpty()) {
            if (!z) {
                codeWriter.emit(StringUtils.LF);
            }
            codeWriter.emit(this.initializerBlock);
            z = false;
        }
        for (MethodSpec next6 : this.methodSpecs) {
            if (next6.isConstructor()) {
                if (!z) {
                    codeWriter.emit(StringUtils.LF);
                }
                next6.emit(codeWriter, this.name, this.kind.implicitMethodModifiers);
                z = false;
            }
        }
        for (MethodSpec next7 : this.methodSpecs) {
            if (!next7.isConstructor()) {
                if (!z) {
                    codeWriter.emit(StringUtils.LF);
                }
                next7.emit(codeWriter, this.name, this.kind.implicitMethodModifiers);
                z = false;
            }
        }
        for (TypeSpec next8 : this.typeSpecs) {
            if (!z) {
                codeWriter.emit(StringUtils.LF);
            }
            next8.emit(codeWriter, (String) null, this.kind.implicitTypeModifiers);
            z = false;
        }
        codeWriter.unindent();
        codeWriter.popType();
        codeWriter.emit("}");
        if (str == null && this.anonymousTypeArguments == null) {
            codeWriter.emit(StringUtils.LF);
        }
        codeWriter.statementLine = i;
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
            emit(new CodeWriter(stringWriter), (String) null, Collections.emptySet());
            return stringWriter.toString();
        } catch (IOException unused) {
            throw new AssertionError();
        }
    }

    public enum Kind {
        CLASS(Collections.emptySet(), Collections.emptySet(), Collections.emptySet(), Collections.emptySet()),
        INTERFACE(Util.immutableSet(Arrays.asList(new Modifier[]{Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL})), Util.immutableSet(Arrays.asList(new Modifier[]{Modifier.PUBLIC, Modifier.ABSTRACT})), Util.immutableSet(Arrays.asList(new Modifier[]{Modifier.PUBLIC, Modifier.STATIC})), Util.immutableSet(Arrays.asList(new Modifier[]{Modifier.STATIC}))),
        ENUM(Collections.emptySet(), Collections.emptySet(), Collections.emptySet(), Collections.singleton(Modifier.STATIC)),
        ANNOTATION(Util.immutableSet(Arrays.asList(new Modifier[]{Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL})), Util.immutableSet(Arrays.asList(new Modifier[]{Modifier.PUBLIC, Modifier.ABSTRACT})), Util.immutableSet(Arrays.asList(new Modifier[]{Modifier.PUBLIC, Modifier.STATIC})), Util.immutableSet(Arrays.asList(new Modifier[]{Modifier.STATIC})));
        
        /* access modifiers changed from: private */
        public final Set<Modifier> asMemberModifiers;
        /* access modifiers changed from: private */
        public final Set<Modifier> implicitFieldModifiers;
        /* access modifiers changed from: private */
        public final Set<Modifier> implicitMethodModifiers;
        /* access modifiers changed from: private */
        public final Set<Modifier> implicitTypeModifiers;

        private Kind(Set<Modifier> set, Set<Modifier> set2, Set<Modifier> set3, Set<Modifier> set4) {
            this.implicitFieldModifiers = set;
            this.implicitMethodModifiers = set2;
            this.implicitTypeModifiers = set3;
            this.asMemberModifiers = set4;
        }
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public final List<AnnotationSpec> annotations;
        /* access modifiers changed from: private */
        public final CodeBlock anonymousTypeArguments;
        /* access modifiers changed from: private */
        public final Map<String, TypeSpec> enumConstants;
        /* access modifiers changed from: private */
        public final List<FieldSpec> fieldSpecs;
        /* access modifiers changed from: private */
        public final CodeBlock.Builder initializerBlock;
        /* access modifiers changed from: private */
        public final CodeBlock.Builder javadoc;
        /* access modifiers changed from: private */
        public final Kind kind;
        /* access modifiers changed from: private */
        public final List<MethodSpec> methodSpecs;
        /* access modifiers changed from: private */
        public final List<Modifier> modifiers;
        /* access modifiers changed from: private */
        public final String name;
        /* access modifiers changed from: private */
        public final List<Element> originatingElements;
        /* access modifiers changed from: private */
        public final CodeBlock.Builder staticBlock;
        /* access modifiers changed from: private */
        public TypeName superclass;
        /* access modifiers changed from: private */
        public final List<TypeName> superinterfaces;
        /* access modifiers changed from: private */
        public final List<TypeSpec> typeSpecs;
        /* access modifiers changed from: private */
        public final List<TypeVariableName> typeVariables;

        private Builder(Kind kind2, String str, CodeBlock codeBlock) {
            this.javadoc = CodeBlock.builder();
            this.annotations = new ArrayList();
            this.modifiers = new ArrayList();
            this.typeVariables = new ArrayList();
            this.superclass = ClassName.OBJECT;
            this.superinterfaces = new ArrayList();
            this.enumConstants = new LinkedHashMap();
            this.fieldSpecs = new ArrayList();
            this.staticBlock = CodeBlock.builder();
            this.initializerBlock = CodeBlock.builder();
            this.methodSpecs = new ArrayList();
            this.typeSpecs = new ArrayList();
            this.originatingElements = new ArrayList();
            Util.checkArgument(str == null || SourceVersion.isName(str), "not a valid name: %s", str);
            this.kind = kind2;
            this.name = str;
            this.anonymousTypeArguments = codeBlock;
        }

        public Builder addJavadoc(String str, Object... objArr) {
            this.javadoc.add(str, objArr);
            return this;
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
            return addAnnotation(AnnotationSpec.builder(className).build());
        }

        public Builder addAnnotation(Class<?> cls) {
            return addAnnotation(ClassName.get(cls));
        }

        public Builder addModifiers(Modifier... modifierArr) {
            Util.checkState(this.anonymousTypeArguments == null, "forbidden on anonymous types.", new Object[0]);
            Collections.addAll(this.modifiers, modifierArr);
            return this;
        }

        public Builder addTypeVariables(Iterable<TypeVariableName> iterable) {
            boolean z = true;
            Util.checkState(this.anonymousTypeArguments == null, "forbidden on anonymous types.", new Object[0]);
            if (iterable == null) {
                z = false;
            }
            Util.checkArgument(z, "typeVariables == null", new Object[0]);
            for (TypeVariableName add : iterable) {
                this.typeVariables.add(add);
            }
            return this;
        }

        public Builder addTypeVariable(TypeVariableName typeVariableName) {
            Util.checkState(this.anonymousTypeArguments == null, "forbidden on anonymous types.", new Object[0]);
            this.typeVariables.add(typeVariableName);
            return this;
        }

        public Builder superclass(TypeName typeName) {
            boolean z = this.superclass == ClassName.OBJECT;
            Util.checkState(z, "superclass already set to " + this.superclass, new Object[0]);
            Util.checkArgument(typeName.isPrimitive() ^ true, "superclass may not be a primitive", new Object[0]);
            this.superclass = typeName;
            return this;
        }

        public Builder superclass(Type type) {
            return superclass(TypeName.get(type));
        }

        public Builder addSuperinterfaces(Iterable<? extends TypeName> iterable) {
            Util.checkArgument(iterable != null, "superinterfaces == null", new Object[0]);
            for (TypeName add : iterable) {
                this.superinterfaces.add(add);
            }
            return this;
        }

        public Builder addSuperinterface(TypeName typeName) {
            this.superinterfaces.add(typeName);
            return this;
        }

        public Builder addSuperinterface(Type type) {
            return addSuperinterface(TypeName.get(type));
        }

        public Builder addEnumConstant(String str) {
            return addEnumConstant(str, TypeSpec.anonymousClassBuilder("", new Object[0]).build());
        }

        public Builder addEnumConstant(String str, TypeSpec typeSpec) {
            Util.checkState(this.kind == Kind.ENUM, "%s is not enum", this.name);
            Util.checkArgument(typeSpec.anonymousTypeArguments != null, "enum constants must have anonymous type arguments", new Object[0]);
            Util.checkArgument(SourceVersion.isName(str), "not a valid enum constant: %s", str);
            this.enumConstants.put(str, typeSpec);
            return this;
        }

        public Builder addFields(Iterable<FieldSpec> iterable) {
            Util.checkArgument(iterable != null, "fieldSpecs == null", new Object[0]);
            for (FieldSpec addField : iterable) {
                addField(addField);
            }
            return this;
        }

        public Builder addField(FieldSpec fieldSpec) {
            Util.checkState(this.kind != Kind.ANNOTATION, "%s %s cannot have fields", this.kind, this.name);
            if (this.kind == Kind.INTERFACE) {
                Util.requireExactlyOneOf(fieldSpec.modifiers, Modifier.PUBLIC, Modifier.PRIVATE);
                EnumSet of = EnumSet.of(Modifier.STATIC, Modifier.FINAL);
                Util.checkState(fieldSpec.modifiers.containsAll(of), "%s %s.%s requires modifiers %s", this.kind, this.name, fieldSpec.name, of);
            }
            this.fieldSpecs.add(fieldSpec);
            return this;
        }

        public Builder addField(TypeName typeName, String str, Modifier... modifierArr) {
            return addField(FieldSpec.builder(typeName, str, modifierArr).build());
        }

        public Builder addField(Type type, String str, Modifier... modifierArr) {
            return addField(TypeName.get(type), str, modifierArr);
        }

        public Builder addStaticBlock(CodeBlock codeBlock) {
            this.staticBlock.beginControlFlow("static", new Object[0]).add(codeBlock).endControlFlow();
            return this;
        }

        public Builder addInitializerBlock(CodeBlock codeBlock) {
            if (this.kind == Kind.CLASS || this.kind == Kind.ENUM) {
                this.initializerBlock.add("{\n", new Object[0]).indent().add(codeBlock).unindent().add("}\n", new Object[0]);
                return this;
            }
            throw new UnsupportedOperationException(this.kind + " can't have initializer blocks");
        }

        public Builder addMethods(Iterable<MethodSpec> iterable) {
            Util.checkArgument(iterable != null, "methodSpecs == null", new Object[0]);
            for (MethodSpec addMethod : iterable) {
                addMethod(addMethod);
            }
            return this;
        }

        public Builder addMethod(MethodSpec methodSpec) {
            if (this.kind == Kind.INTERFACE) {
                Util.requireExactlyOneOf(methodSpec.modifiers, Modifier.ABSTRACT, Modifier.STATIC, Util.DEFAULT);
                Util.requireExactlyOneOf(methodSpec.modifiers, Modifier.PUBLIC, Modifier.PRIVATE);
            } else if (this.kind == Kind.ANNOTATION) {
                Util.checkState(methodSpec.modifiers.equals(this.kind.implicitMethodModifiers), "%s %s.%s requires modifiers %s", this.kind, this.name, methodSpec.name, this.kind.implicitMethodModifiers);
            }
            if (this.kind != Kind.ANNOTATION) {
                Util.checkState(methodSpec.defaultValue == null, "%s %s.%s cannot have a default value", this.kind, this.name, methodSpec.name);
            }
            if (this.kind != Kind.INTERFACE) {
                Util.checkState(!Util.hasDefaultModifier(methodSpec.modifiers), "%s %s.%s cannot be default", this.kind, this.name, methodSpec.name);
            }
            this.methodSpecs.add(methodSpec);
            return this;
        }

        public Builder addTypes(Iterable<TypeSpec> iterable) {
            Util.checkArgument(iterable != null, "typeSpecs == null", new Object[0]);
            for (TypeSpec addType : iterable) {
                addType(addType);
            }
            return this;
        }

        public Builder addType(TypeSpec typeSpec) {
            Util.checkArgument(typeSpec.modifiers.containsAll(this.kind.implicitTypeModifiers), "%s %s.%s requires modifiers %s", this.kind, this.name, typeSpec.name, this.kind.implicitTypeModifiers);
            this.typeSpecs.add(typeSpec);
            return this;
        }

        public Builder addOriginatingElement(Element element) {
            this.originatingElements.add(element);
            return this;
        }

        public TypeSpec build() {
            boolean z = true;
            Util.checkArgument(this.kind != Kind.ENUM || !this.enumConstants.isEmpty(), "at least one enum constant is required for %s", this.name);
            boolean z2 = this.modifiers.contains(Modifier.ABSTRACT) || this.kind != Kind.CLASS;
            for (MethodSpec next : this.methodSpecs) {
                Util.checkArgument(z2 || !next.hasModifier(Modifier.ABSTRACT), "non-abstract type %s cannot declare abstract method %s", this.name, next.name);
            }
            int size = (this.superclass.equals(ClassName.OBJECT) ^ true ? 1 : 0) + this.superinterfaces.size();
            if (this.anonymousTypeArguments != null && size > 1) {
                z = false;
            }
            Util.checkArgument(z, "anonymous type has too many supertypes", new Object[0]);
            return new TypeSpec(this);
        }
    }
}
